package com.qf.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@WebFilter("/*")*/
public class FilterTest implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将父接口强制转换为子接口
        //目的是用子接口新增的方法，HttpServletRequest新增的方法
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;


        //1,获取请求请求的uri
        String requestURI = request.getRequestURI();
        System.out.println("requestURI:"+requestURI);
        if(requestURI.contains("/login.jsp")||requestURI.contains("/loginServlet")){

            //放行，让请求去访问登录页面或提交登录请求
            chain.doFilter(req, resp);
        }else{
            Object user = request.getSession().getAttribute("user");
            if(user!=null){
                //登录过，直接放行
                chain.doFilter(req, resp);
            }else{
                //设置一下提示
                //request.setAttribute("filter_error","您尚未登录，请登录");
                //转发到登录页面
                //request.getRequestDispatcher("/login.jsp").forward(request,response);
                //希望地址栏变化
                request.getSession().setAttribute("login_error","您尚未登录，请登录");
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        }



    }

    public void destroy() {
    }


}
