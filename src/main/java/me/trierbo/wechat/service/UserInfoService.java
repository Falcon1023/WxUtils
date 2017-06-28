package me.trierbo.wechat.service;

import me.trierbo.wechat.config.WeChatConfig;
import me.trierbo.wechat.util.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UserInfoService {

    public static void getCode(String scope,String redirect_uri){
        StringBuilder sb=new StringBuilder();
        sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
        sb.append(WeChatConfig.APPID);
        sb.append("&redirect_uri=");
        try {
            sb.append(URLEncoder.encode(redirect_uri,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&response_type=code&scope=");
        sb.append(scope);
        sb.append("&state=STATE#wechat_redirect");
        HttpUtil.doGet(sb.toString());
    }
}
