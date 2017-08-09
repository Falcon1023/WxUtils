package org.trier.wechat.pojo.pay;

import org.trier.wechat.common.SignMap;
import org.trier.wechat.pojo.order.UnifiedOrderResult;
import org.trier.wechat.util.NonceStrUtil;

public class BrandWCPay extends SignMap {
    public BrandWCPay(UnifiedOrderResult unifiedOrderResult) {
        super();
        put("appId", unifiedOrderResult.get("appid"));
        put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        put("nonceStr", NonceStrUtil.getNonceStr(32));
        put("package", "prepay_id=" + unifiedOrderResult.get("prepay_id"));
        put("signType", "MD5");
        put("paySign", makeSign());
    }
}