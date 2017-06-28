package org.trier.wechat.service;

import com.alibaba.fastjson.JSON;
import org.trier.wechat.config.WeChatConfig;
import org.trier.wechat.pojo.Token;
import org.trier.wechat.util.HttpUtil;

import java.io.*;
import java.net.URLEncoder;

public class UserInfoService {

    public static String getCode(String scope, String redirect_uri) {
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

    public static Token getToken(String code) {
        Token token = null;
        ObjectInputStream in=null;
        try {
            in = new ObjectInputStream(new FileInputStream("access_token"));
            token = (Token) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
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
            token.setRecord_time(System.currentTimeMillis());
            if (token.getErrcode() == 0) {
                ObjectOutputStream out = null;
                try {
                    out = new ObjectOutputStream(new FileOutputStream("access_token"));
                    out.writeObject(token);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(out != null)
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        }
        return token;
    }

    public static Token refreshToken(String refresh_token){
        StringBuilder sb=new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=");
        sb.append(WeChatConfig.APPID);
        sb.append("&grant_type=refresh_token&refresh_token=");
        sb.append(refresh_token);
        String result=HttpUtil.doGet(sb.toString());
        Token token=JSON.parseObject(result,Token.class);
        token.setRecord_time(System.currentTimeMillis());
        ObjectOutputStream out=null;

        return token;
    }
}
