package org.trier.servlet;

import org.trier.wechat.pojo.Token;
import org.trier.wechat.service.UserInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code=request.getParameter("code");
        if(code == null){
            String redirect_uri = "http://"+request.getServerName()
                    +request.getContextPath()
                    +request.getServletPath();
            String url=UserInfoService.getCode("snsapi_userinfo",redirect_uri);
            response.sendRedirect(url);
        }else{
            log(code);
            Token token=UserInfoService.getToken(code);
            log(token.toString());
        }
    }
}
