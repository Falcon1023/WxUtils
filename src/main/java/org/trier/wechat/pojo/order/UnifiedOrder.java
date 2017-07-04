package org.trier.wechat.pojo.order;

import org.trier.wechat.common.SignMap;

/**
 * 微信统一下单对象
 */
public class UnifiedOrder extends SignMap {
    /**
     * 字段名：公众账号ID
     * 必填：是
     * 示例值：wxd678efh567hg6787
     * 描述：微信支付分配的公众账号ID（企业号corpid即为此appId）
     */
    private final static String APPID="appid";

    /**
     * 字段名：商户号
     * 必填：是
     * 示例值：1230000109
     * 描述：微信支付分配的商户号
     */
    private final static String MCH_ID="mch_id";

    /**
     * 字段名：设备号
     * 必填：否
     * 示例值：013467007045764
     * 描述：自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    private final static String DEVICE_INFO="device_info";

    /**
     * 字段名：随机字符串
     * 必填：是
     * 示例值：5K8264ILTKCH16CQ2502SI8ZNMTM67VS
     * 描述：随机字符串，长度要求在32位以内。推荐随机数生成算法
     */
    private final static String NONCE_STR="nonce_str";

    /**
     * 字段名：签名
     * 必填：是
     * 示例值：5K8264ILTKCH16CQ2502SI8ZNMTM67VS
     * 描述：通过签名算法计算得出的签名值，详见签名生成算法
     * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
     */
    private final static String SIGN="sign";

    /**
     * 字段名：签名类型
     * 必填：否
     * 示例值：HMAC-SHA256
     * 描述：签名类型，默认为MD5，支持HMAC-SHA256和MD5。
     */
    private final static String SIGN_TYPE="sign_type";

    /**
     * 字段名：商品描述
     * 必填：是
     * 示例值：腾讯充值中心-QQ会员充值
     * 描述：商品简单描述，该字段请按照规范传递，具体请见参数规定
     */
    private final static String BODY="body";

    /**
     * 字段名：商品详情
     * 必填：否
     * 描述：单品优惠字段(暂未上线)
     */
    private final static String DETAIL="detail";

    /**
     * 字段名：附加数据
     * 必填：否
     * 示例值：深圳分店
     * 描述：附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
     */
    private final static String ATTACH="attach";

    /**
     * 字段名：商品订单号
     * 必填：是
     * 示例值：20150806125346
     * 描述：商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
     */
    private final static String OUT_TRADE_NO="out_trade_no";

    /**
     * 字段名：标价币种
     * 必填：否
     * 示例值：CNY
     * 描述：符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
     */
    private final static String FEE_TYPE="fee_type";

    /**
     * 字段名：标价金额
     * 必填：是
     * 示例值：88
     * 描述：订单总金额，单位为分，详见支付金额
     */
    private final static String TOTAL_FEE="total_fee";

    /**
     * 字段名：终端IP
     * 必填：是
     * 示例值：123.12.12.123
     * 描述：	APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
     */
    private final static String SPBILL_CREATE_IP="spbill_create_ip";

    /**
     * 字段名：交易起始时间
     * 必填：否
     * 示例值：20091225091010
     * 描述：订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    private final static String TIME_START="time_start";

    /**
     * 字段名：交易结束时间
     * 必填：否
     * 示例值：20091227091010
     * 描述：订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
     */
    private final static String TIME_EXPIRE="time_expire";

    /**
     * 字段名：订单优惠标记
     * 必填：否
     * 示例值：WXG
     * 描述：订单优惠标记，使用代金券或立减优惠功能时需要的参数，说明详见代金券或立减优惠
     */
    private final static String GOODS_TAG="goods_tag";

    /**
     * 字段名：通知地址
     * 必填：是
     * 示例值：http://www.weixin.qq.com/wxpay/pay.php
     * 描述：异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
     */
    private final static String NOTIFY_URL="notify_url";

    /**
     * 字段名：交易类型
     * 必填：是
     * 示例值：JSAPI
     * 描述：取值如下：JSAPI，NATIVE，APP等，说明详见参数规定
     */
    private final static String TRADE_TYPE="trade_type";

    /**
     * 字段名：商品ID
     * 必填：否
     * 示例值：12235413214070356458058
     * 描述：trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
     */
    private final static String PRODUCT_ID="product_id";

    /**
     * 字段名：指定支付方式
     * 必填：否
     * 示例值：no_credit
     * 描述：上传此参数no_credit--可限制用户不能使用信用卡支付
     */
    private final static String LIMIT_PAY="limit_pay";

    /**
     * 字段名：用户标识
     * 必填：否
     * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * 描述：trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
     */
    private final static String OPENID="openid";

    public void setAppid(String appid){
        put(APPID,appid);
    }

    public String getAppid(){
        return get(APPID);
    }

    public boolean isAppidSet(){
        return containsKey(APPID);
    }

    public void setMchId(String mch_id){
        put(MCH_ID,mch_id);
    }

    public String getMchId(){
        return get(MCH_ID);
    }

    public boolean isMchIdSet(){
        return containsKey(MCH_ID);
    }

    public void setDeviceInfo(String deviceInfo){
        put(DEVICE_INFO,deviceInfo);
    }

    public String getDeviceInfo(){
        return get(DEVICE_INFO);
    }

    public boolean isDeviceInfoSet(){
        return containsKey(DEVICE_INFO);
    }

    public void setNonceStr(String nonce_str){
        put(NONCE_STR,nonce_str);
    }

    public String getNonceStr(){
        return get(NONCE_STR);
    }

    public boolean isNonceStrSet(){
        return containsKey(NONCE_STR);
    }

    public void setSign(String sign){
        put(SIGN,sign);
    }

    public String getSign(){
        return get(SIGN);
    }

    public boolean isSignSet(){
        return containsKey(SIGN);
    }

    public void setSignType(String signType){
        put(SIGN_TYPE,signType);
    }

    public boolean isSignTypeSet(){
        return containsKey(SIGN_TYPE);
    }

    public void setBody(String body){
        put(BODY,body);
    }

    public String getBody(){
        return get(BODY);
    }

    public boolean isBodySet(){
        return containsKey(BODY);
    }

    /**
     * @param detail { "goods_detail":[ { "goods_id":"iphone6s_16G", "wxpay_goods_id":"1001", "goods_name":"iPhone6s 16G", "quantity":1, "price":528800, "goods_category":"123456", "body":"苹果手机" }, { "goods_id":"iphone6s_32G", "wxpay_goods_id":"1002", "goods_name":"iPhone6s 32G", "quantity":1, "price":608800, "goods_category":"123789", "body":"苹果手机" } ] }
     */
    public void setDetail(String detail){
        put(DETAIL,detail);
    }

    public String getDetail(){
        return get(DETAIL);
    }

    public boolean isDetailSet(){
        return containsKey(DETAIL);
    }

    public void setAttach(String attach){
        put(ATTACH,attach);
    }

    public boolean isAttachSet(){
        return containsKey(ATTACH);
    }

    public void setOutTradeNo(String outTradeNo){
        put(OUT_TRADE_NO,outTradeNo);
    }

    public String getOutTradeNo(){
        return get(OUT_TRADE_NO);
    }

    public boolean isOutTradeNoSet(){
        return containsKey(OUT_TRADE_NO);
    }

    public void setFeeType(String feeType){
        put(FEE_TYPE,feeType);
    }

    public String getFeeType(){
        return get(FEE_TYPE);
    }

    public boolean isFeeTypeSet(){
        return containsKey(FEE_TYPE);
    }

    public void setTotalFee(int totalFee){
        put(TOTAL_FEE,String.valueOf(totalFee));
    }

    public int getTotalFee(){
        return Integer.parseInt(get(TOTAL_FEE));
    }

    public boolean isTotalFeeSet(){
        return containsKey(TOTAL_FEE);
    }

    public void setSpbillCreateIp(String spbillCreateIp){
        put(SPBILL_CREATE_IP,spbillCreateIp);
    }

    public String getSpbillCreateIp(){
        return get(SPBILL_CREATE_IP);
    }

    public boolean isSpbillCreateIpSet(){
        return containsKey(SPBILL_CREATE_IP);
    }

    public void setTimeStart(String timeStart){
        put(TIME_START,timeStart);
    }

    public String getTimeStart(){
        return get(TIME_START);
    }

    public boolean isTimeStartSet(){
        return containsKey(TIME_START);
    }

    public void setTimeExpire(String timeExpire){
        put(TIME_EXPIRE,timeExpire);
    }

    public String getTimeExpire(){
        return get(TIME_EXPIRE);
    }

    public boolean isTimeExpireSet(){
        return containsKey(TIME_EXPIRE);
    }

    public void setGoodsTag(String goodsTag){
        put(GOODS_TAG,goodsTag);
    }

    public String getGoodsTag(){
        return get(GOODS_TAG);
    }

    public boolean isGoodsTagSet(){
        return containsKey(GOODS_TAG);
    }

    public void setNotifyUrl(String notifyUrl){
        put(NOTIFY_URL,notifyUrl);
    }

    public String getNotifyUrl(){
        return get(NOTIFY_URL);
    }

    public boolean isNotifyUrlSet(){
        return containsKey(NOTIFY_URL);
    }

    public void setTradeType(String tradeType){
        put(TRADE_TYPE,tradeType);
    }

    public String getTradeType(){
        return get(TRADE_TYPE);
    }

    public boolean isTradeTypeSet(){
        return containsKey(TRADE_TYPE);
    }

    public void setProductId(String productId){
        put(PRODUCT_ID,productId);
    }

    public String getProductId(){
        return get(PRODUCT_ID);
    }

    public boolean isProductIdSet(){
        return containsKey(PRODUCT_ID);
    }

    public void setLimitPay(String limitPay){
        put(LIMIT_PAY,limitPay);
    }

    public String getLimitPay(){
        return get(LIMIT_PAY);
    }

    public boolean isLimitPaySet(){
        return containsKey(LIMIT_PAY);
    }

    public void setOpenid(String openid){
        put(OPENID,openid);
    }

    public String getOpenid(){
        return get(OPENID);
    }

    public boolean isOpenIdSet(){
        return containsKey(OPENID);
    }
}