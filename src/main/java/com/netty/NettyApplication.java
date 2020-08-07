package com.netty;

import com.netty.config.MyComponentScan;
//import com.netty.config.SpringMvcConfig;
import com.netty.controller.ConvertController;
import com.netty.netty.NettyHttpServer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 基于netty 做服务器
 * springmvc 做mvc框架
 */
public class NettyApplication {
    public static void main(String[] args) throws Exception {
        //        ApplicationContext context = new AnnotationConfigApplicationContext(MyComponentScan.class);
//        BeanFactory parentBeanFactory = context.getParentBeanFactory();

        Integer port = 6001;

        NettyHttpServer server = new NettyHttpServer(port,getDispatcherServlet());
        server.start();


    }
    public static DispatcherServlet getDispatcherServlet() throws Exception{
        /**
         * 基于注解启动springmvc
         */
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();

        MockServletContext servletContext = new MockServletContext();
        MockServletConfig servletConfig = new MockServletConfig(servletContext);

        webApplicationContext.scan("com.netty");//扫描包
        webApplicationContext.setServletContext(servletContext);
        webApplicationContext.setServletConfig(servletConfig);
        webApplicationContext.refresh();

        DispatcherServlet dispatcherServlet2 = new DispatcherServlet(webApplicationContext);
        dispatcherServlet2.init(servletConfig);

        return dispatcherServlet2;

    }
}
