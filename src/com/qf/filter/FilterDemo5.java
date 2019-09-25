package com.qf.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/*@WebFilter("/*")*/
public class FilterDemo5 implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("FilterDemo5 放行之前的代码。。。。。");
        chain.doFilter(req, resp);
        System.out.println("FilterDemo5 放行之后的代码。。。。。");
    }

    public void destroy() {
    }


}
