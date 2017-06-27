package me.trierbo.wechat.common;

import com.keessi.wechat.config.WeChatConfig;
import com.keessi.wechat.exception.WxException;
import com.keessi.wechat.util.MD5Util;
import com.keessi.wechat.util.NumericUtil;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 重写TreeMap，使之适合产生签名
 */
public class SignMap extends TreeMap<String,String> {
    private final static String SIGN="sign";

    /**
     * 设置签名，并返回签名的值
     * @return
     */
    public String setSign(){
        if(get(SIGN)==null)
            put(SIGN,makeSign());
        return get(SIGN);
    }

    /**
     * 获取签名
     * @return
     */
    public String getSign(){
        return get(SIGN);
    }

    /**
     * 判断签名是否设置
     * @return
     */
    public boolean isSignSet(){
        return containsKey(SIGN);
    }

    /**
     * 签名算法，该函数
     * @return
     */
    protected String makeSign(){
        //签名生成第一步：按字典序排序参数
        StringBuilder sb=new StringBuilder();
        sb.append(toString());
        //签名生成第二步：在字符串后面加上KEY
        sb.append("&key="+ WeChatConfig.KEY);
        //签名生成第三步：MD5加密
        String str= MD5Util.getMessageDigest(sb.toString());
        //签名生成第四步：所有字符转为大写
        return str.toUpperCase();
    }

    /**
     * 将订单数据转成xml
     * @return
     */
    public String toXml() throws WxException {
        if(size()==0)
            throw new WxException("订单数据异常");
        StringBuilder sb=new StringBuilder();
        sb.append("<xml>");
        Iterator<Map.Entry<String,String>> iterator=entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry=iterator.next();
            if(NumericUtil.isNumeric(entry.getValue())){
                sb.append("<");
                sb.append(entry.getKey());
                sb.append(">");
                sb.append(entry.getValue());
                sb.append("</");
                sb.append(entry.getKey());
                sb.append(">");
            }else{
                sb.append("<");
                sb.append(entry.getKey());
                sb.append("><![CDATA[");
                sb.append(entry.getValue());
                sb.append("]]></");
                sb.append(entry.getKey());
                sb.append(">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    @Override
    public String toString() {
        Iterator<Map.Entry<String,String>> i = entrySet().iterator();
        if (! i.hasNext())
            return "";
        StringBuilder sb = new StringBuilder();
        for (;;) {
            Map.Entry<String,String> e = i.next();
            String key = e.getKey();
            if(key.equals(SIGN)){
                if (! i.hasNext()){
                    sb.deleteCharAt(sb.length()-1);
                    return sb.toString();
                }
                continue;
            }
            String value = e.getValue();
            sb.append(key);
            sb.append('=');
            sb.append(value);
            if (! i.hasNext())
                return sb.toString();
            sb.append('&');
        }
    }
}
