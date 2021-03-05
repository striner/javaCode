/**
 * @author striner
 * @create 2018/5/11 20:29
 * @Description:　
 */
package com.striner.spring_boot_servlet;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter process ...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
