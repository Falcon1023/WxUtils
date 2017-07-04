package org.trier.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 字符编码UTF-8
 */
public class CharacterFilter implements Filter {
    //字符编码
    private String encoding = null;

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (encoding != null) {
            //设置request字符编码
            request.setCharacterEncoding(encoding);
            //设置response字符编码
            response.setContentType("text/html;charset=" + encoding);
        }
        //传递给下一个过滤器
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        //获取初始化参数
        encoding = filterConfig.getInitParameter("encoding");
    }

    public void destroy() {
        encoding = null;
    }
}
