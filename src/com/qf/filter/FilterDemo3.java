package com.qf.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo3 implements Filter {
    /**
     * 服务器启动的时候，会执行init()方法，创建一个过滤器对象。只执行一次。
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("这是 init().......");
    }

    /**
     * 过滤请求路径，会执行多次。只要匹配路径了，就会多次执行。。。
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("这是 doFilter().......");
    }

    /**
     * 服务器正常的关闭的时候，会执行destroy(),也是只执行一次。会销毁filter对象
     *
     *
     */
    @Override
    public void destroy() {
        System.out.println("这是 destroy().......");
    }
}
