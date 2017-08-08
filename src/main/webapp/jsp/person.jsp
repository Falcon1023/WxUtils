<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.trier.wechat.pojo.auth.UserInfo" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>微信个人信息</title>
    <script type="text/javascript" src="/WxUtils/js/adapt.js"></script>
    <link rel="stylesheet" type="text/css" href="/WxUtils/css/weuix.min.css">
    <link rel="stylesheet" type="text/css" href="/WxUtils/css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="/WxUtils/css/main.min.css">
</head>

<body>
<% UserInfo userInfo = (UserInfo) request.getAttribute("userInfo"); %>
<div class="content">
    <div class="person-header">
        <img src="<%= userInfo.getHeadimgurl() %>" class="person-photo"/>
        <div class="person-name"><%= userInfo.getNickname() %></div>
    </div>
</div>
</body>

</html>
