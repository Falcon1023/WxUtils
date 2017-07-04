package org.trier.wechat.service;

import org.trier.wechat.config.WeChatConfig;
import org.trier.wechat.exception.WxException;
import org.trier.wechat.pojo.order.UnifiedOrder;
import org.trier.wechat.pojo.order.UnifiedOrderResult;
import org.trier.wechat.util.HttpUtil;
import org.trier.wechat.util.NonceStrUtil;

/**
 * 同一下单
 */
public class UnifiedOrderService {

    private UnifiedOrder unifiedOrder;

    public UnifiedOrderService(UnifiedOrder unifiedOrder) {
        this.unifiedOrder = unifiedOrder;
    }

    public UnifiedOrderResult unifiedOrder() {
        UnifiedOrderResult unifiedOrderResult = new UnifiedOrderResult();
        try {
            if (!unifiedOrder.isOutTradeNoSet())
                throw new WxException("缺少统一支付接口必填参数out_trade_no!");
            if (!unifiedOrder.isBodySet())
                throw new WxException("缺少统一支付接口必填参数body!");
            if (!unifiedOrder.isTotalFeeSet())
                throw new WxException("缺少统一支付接口必填参数total_fee!");
            if (!unifiedOrder.isTradeTypeSet())
                throw new WxException("缺少统一支付接口必填参数trade_type!");
            if (!unifiedOrder.isSpbillCreateIpSet())
                throw new WxException("缺少统一支付接口必填参数spill_create_ip!");
            if (unifiedOrder.getTradeType().equals("JSAPI") && !unifiedOrder.isOpenIdSet())
                throw new WxException("统一支付接口中，缺少必填参数openid！trade_type为JSAPI时，openid为必填参数!");
        } catch (WxException e) {
            e.printStackTrace();
            return null;
        }
        unifiedOrder.setAppid(WeChatConfig.APPID);
        unifiedOrder.setMchId(WeChatConfig.MCHID);
        unifiedOrder.setNonceStr(NonceStrUtil.getNonceStr(32));
        unifiedOrder.setSign();
        String resultXml;
        try {
            resultXml = HttpUtil.doPostXml(WeChatConfig.UNIFIEDORDERURL, unifiedOrder.toXml());
            unifiedOrderResult.fromXml(resultXml);
        } catch (WxException e) {
            e.printStackTrace();
            return null;
        }
        return unifiedOrderResult;
    }
}