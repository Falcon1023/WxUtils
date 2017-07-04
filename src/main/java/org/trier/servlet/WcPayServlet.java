package org.trier.servlet;

import org.trier.wechat.pojo.auth.Token;
import org.trier.wechat.pojo.order.UnifiedOrder;
import org.trier.wechat.pojo.order.UnifiedOrderResult;
import org.trier.wechat.pojo.pay.BrandWCPay;
import org.trier.wechat.service.AuthService;
import org.trier.wechat.service.UnifiedOrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WcPayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null) {
            String redirect_uri = "http://" + request.getServerName()
                    + request.getContextPath()
                    + request.getServletPath();
            String url = AuthService.getCodeUrl("snsapi_userinfo", redirect_uri);
            response.sendRedirect(url);
        } else {
            Token token = AuthService.getToken(code);
            PrintWriter out = response.getWriter();
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
            BrandWCPay brandWCPay = new BrandWCPay(unifiedOrderResult);
            request.setAttribute("order",unifiedOrder);
            request.setAttribute("wcpay", brandWCPay);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/wxPay.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
