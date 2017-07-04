package org.trier.servlet;

import org.trier.wechat.pojo.auth.Token;
import org.trier.wechat.pojo.auth.UserInfo;
import org.trier.wechat.pojo.order.UnifiedOrder;
import org.trier.wechat.pojo.order.UnifiedOrderResult;
import org.trier.wechat.service.AuthService;
import org.trier.wechat.service.UnifiedOrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 网页授权servlet
 */
public class AuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null) {
            String redirect_uri = "http://" + request.getServerName()
                    + request.getContextPath()
                    + request.getServletPath();
            String url = AuthService.getCodeUrl("snsapi_userinfo", redirect_uri);
            response.sendRedirect(url);
        } else {
            log(code);
            Token token = AuthService.getToken(code);
            PrintWriter out = response.getWriter();
            UserInfo userInfo = AuthService.getUserInfo(token.getAccess_token(), token.getOpenid());
            out.println(userInfo.toString());
            UnifiedOrder unifiedOrder = new UnifiedOrder();
            unifiedOrder.setOpenid(token.getOpenid());
            unifiedOrder.setBody("Test");
            unifiedOrder.setOutTradeNo(String.valueOf(System.currentTimeMillis()));
            unifiedOrder.setTotalFee(1);
            unifiedOrder.setSpbillCreateIp(request.getRemoteAddr());
            unifiedOrder.setNotifyUrl("http://coyote.wpengtao.com/notify");
            unifiedOrder.setTradeType("JSAPI");
            UnifiedOrderService unifiedOrderService = new UnifiedOrderService(unifiedOrder);
            UnifiedOrderResult unifiedOrderResult = unifiedOrderService.unifiedOrder();
            boolean b = unifiedOrderResult.checkSign();
            out.println(b);
            out.println(unifiedOrderResult.toString());
        }
    }
}
