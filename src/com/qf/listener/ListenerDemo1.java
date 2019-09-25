package com.qf.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerDemo1 implements ServletContextListener {
    /**
     * 监视当ServletContext对象创建时候，会执行这个方法
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //加载资源
        ServletContext servletContext = servletContextEvent.getServletContext();
        String contextLoadConfig = servletContext.getInitParameter("contextLoadConfig");
        String realPath = servletContext.getRealPath(contextLoadConfig);
        System.out.println("contextLoadConfig:"+contextLoadConfig);
        System.out.println("realPath:"+realPath);


        System.out.println("ServletContext对象呗创建了。。。");
    }

    /**
     * 监视当ServletContext对象销毁的时候，会执行这个方法
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        System.out.println("ServletContext对象呗销毁了。。。");
    }
}
