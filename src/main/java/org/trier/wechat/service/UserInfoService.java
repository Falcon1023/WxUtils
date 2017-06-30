package org.trier.wechat.service;

import com.alibaba.fastjson.JSON;
import org.trier.wechat.config.WeChatConfig;
import org.trier.wechat.pojo.Token;
import org.trier.wechat.pojo.UserInfo;
import org.trier.wechat.util.HttpUtil;
import org.trier.wechat.util.IOUtil;

import java.io.*;
import java.net.URLEncoder;

/**
 * 微信网页授权
 */
public class UserInfoService {

    /**
     * 用户同意授权获取code的URL
     * @param scope
     * 以snsapi_base为scope发起的网页授权，是用来获取进入页面的用户的openid的，并且是静默授权并自动跳转到回调页的
     * 以snsapi_userinfo为scope发起的网页授权，是用来获取用户的基本信息的，这种授权需要用户手动同意
     * @param redirect_uri 回调URL地址
     * @return
     */
    public static String getCodeUrl(String scope, String redirect_uri) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
        sb.append(WeChatConfig.APPID);
        sb.append("&redirect_uri=");
        try {
            sb.append(URLEncoder.encode(redirect_uri, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&response_type=code&scope=");
        sb.append(scope);
        sb.append("&state=STATE#wechat_redirect");
        return sb.toString();
    }

    /**
     * 通过code换取网页授权的access_token
     * @param code
     * @return
     */
    public static Token getToken(String code) {
        Token token = IOUtil.readObject(WeChatConfig.TOKEN_FILE);
        long current_time = System.currentTimeMillis();
        if (current_time > (token.getRecord_time() + token.getExpires_in())) {
            StringBuilder sb = new StringBuilder();
            sb.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
            sb.append(WeChatConfig.APPID);
            sb.append("&secret=");
            sb.append(WeChatConfig.APPSECRET);
            sb.append("&code=");
            sb.append(code);
            sb.append("&grant_type=authorization_code");
            String result = HttpUtil.doGet(sb.toString());
            token = JSON.parseObject(result, Token.class);
            token.setRecord_time(System.currentTimeMillis()-200);
            if (token.getErrcode() == 0) {
                IOUtil.writeObject(WeChatConfig.TOKEN_FILE,token);
            }
        }
        return token;
    }

    /**
     * 刷新access_token
     * access_token 有效期为7200 refresh_token有效期为30天
     * refresh_token失效后需要用户重新授权
     * @param refresh_token
     * @return
     */
    public static Token refreshToken(String refresh_token){
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=");
        sb.append(WeChatConfig.APPID);
        sb.append("&grant_type=refresh_token&refresh_token=");
        sb.append(refresh_token);
        String result=HttpUtil.doGet(sb.toString());
        Token token=JSON.parseObject(result,Token.class);
        token.setRecord_time(System.currentTimeMillis());
        if(token.getErrcode() == 0){
            IOUtil.writeObject(WeChatConfig.TOKEN_FILE,token);
        }
        return token;
    }

    /**
     * 获取用户基本信息，scope需要为snsapi_userinfo
     * @param token
     * @param openid
     * @return
     */
    public static UserInfo getUserInfo(String token,String openid){
        UserInfo userInfo = null;
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/userinfo?access_token=");
        sb.append(token);
        sb.append("&openid=");
        sb.append(openid);
        sb.append("&lang=zh_CN");
        String result = HttpUtil.doGet(sb.toString());
        userInfo = JSON.parseObject(result, UserInfo.class);
        return userInfo;
    }
}
