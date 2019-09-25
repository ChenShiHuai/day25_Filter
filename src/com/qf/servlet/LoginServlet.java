package com.qf.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if("zhangsan".equals(username)&&"123".equals(password)){
            //登录成功了
            //将用户名存入session
            request.getSession().setAttribute("user",username);
            //没有数据传递,那么用重定向
            /*
             1,没有数据传递
             2,我希望登录成功之后，地址栏的地址有改变
             注意点：重定向的时候，路径一定要加上项目访问路径request.getContextPath()；
             */
            response.sendRedirect(request.getContextPath()+"/success.jsp");
        }else{


            request.setAttribute("login_error","用户名或密码错误");
            //转发到登录页面
            /*
            1,有数据传递的需求
            2，可以通过reqeuest共享数据
            3，地址栏不需要改变，
            注意：
            转发的时候，不要加上项目访问路径，因为服务器默认会加上，
             */
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
