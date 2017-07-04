package org.trier.wechat.pojo.order;

import org.trier.wechat.common.SignMap;
import org.trier.wechat.exception.WxException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;

/**
 * 接口调用结果类
 */
public class UnifiedOrderResult extends SignMap {
    /**
     * 检查统一订单数据的签名
     * @return
     */
    public boolean checkSign(){
        if(isSignSet())
            try {
                throw new WxException("统一订单返回数据签名未设置");
            } catch (WxException e) {
                return false;
            }
        String sign=makeSign();
        if(getSign().equals(sign))
            return true;
        else
            try {
                throw new WxException("统一订单返回数据签名错误");
            } catch (WxException e) {
                return false;
            }
    }

    public boolean fromXml(String xml){
        if(xml==null||xml.equals(""))
            try {
                throw new WxException("xml数据异常");
            } catch (WxException e) {
                return false;
            }
        try {
            Document document= DocumentHelper.parseText(xml);
            Element root=document.getRootElement();
            for(Iterator it = root.elementIterator(); it.hasNext();){
                Element element = (Element) it.next();
                put(element.getName(),element.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
