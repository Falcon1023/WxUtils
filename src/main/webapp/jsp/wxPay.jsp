<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.trier.wechat.pojo.pay.BrandWCPay" %>
<%@ page import="org.trier.wechat.pojo.order.UnifiedOrder" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no"/>
    <script type="text/javascript" src="/WxUtils/js/adapt.js"></script>
    <link rel="stylesheet" type="text/css" href="/WxUtils/css/weuix.min.css">
    <link rel="stylesheet" href="/WxUtils/css/wxPay.css"/>
    <title>支付</title>
</head>

<body>
<%
    BrandWCPay brandWCPay = (BrandWCPay) request.getAttribute("wcpay");
    UnifiedOrder unifiedOrder = (UnifiedOrder) request.getAttribute("order");
%>
<div class="topbar">
    <a href="javascript:history.go(-1)">
        <i class="icon icon-109"></i>
        <span>返回</span>
    </a>
</div>
<div class="order-info">
    <div class="order-id">活动订单-<%= unifiedOrder.getOutTradeNo() %>
    </div>
    <div class="order-price">￥<%= unifiedOrder.getTotalFee() %>
    </div>
</div>
<div class="order-receiver clearfloat">
    <div class="receiver-txt">收款方</div>
    <div class="receiver-name">TEST</div>
</div>
<div class="pay">
    <a class="pay-btn" onclick="callpay()">立即支付</a>
</div>
<script type="text/javascript">
    function onBridgeReady() {
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId": "<%= brandWCPay.get("appId") %>",
                "timeStamp": "<%= brandWCPay.get("timeStamp") %>",
                "nonceStr":"<%= brandWCPay.get("nonceStr") %>",
                "package":"<%= brandWCPay.get("package") %>",
                "signType":"<%= brandWCPay.get("signType") %>",
                "paySign":"<%= brandWCPay.get("paySign") %>"
            },
            function (res) {
                if (res.err_msg == "get_brand_wcpay_request:fail") {
                }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
            }
        );
    }
    function callpay() {
        if (typeof WeixinJSBridge == "undefined") {
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        } else {
            onBridgeReady();
        }
    }
</script>
</body>

</html>
