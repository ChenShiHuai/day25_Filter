package com.qf.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class FilterDemo2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {




//        1. 执行过滤器里面放行前面 代码
        System.out.println("FilterDemo2.....执行了。。");
//        2. 执行放行后的资源，你的请求部分
        filterChain.doFilter(servletRequest,servletResponse);
//        3. 回来执行过滤器放行后面的的代码
        System.out.println("FilterDemo2.....回来了。。");
    }

    @Override
    public void destroy() {

    }
}
