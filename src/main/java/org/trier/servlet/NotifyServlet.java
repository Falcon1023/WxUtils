package org.trier.servlet;

import org.trier.wechat.util.NotifyUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 回调servlet
 */
public class NotifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log("SUCCESS");
        PrintWriter out = response.getWriter();
        out.print(NotifyUtil.setXML("SUCCESS",""));
    }
}
