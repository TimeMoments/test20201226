package com.turing.manage.listeners;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * @desc  利用监听器实现预先加载一些数据
 * @author HYZ
 * @time  2020年12月14日
 */
public class MyLicenesListener implements ServletContextListener {

@Override
public void contextInitialized(ServletContextEvent sce) {
     System.out.println("===============servlet服务初始化(也就是tomcat正式启动成功)==================");
     System.out.println("1.可以预先加载一些数据");
     Map<String, Object> map_listener=new HashMap<String, Object>();
     map_listener.put("username", "root");
     map_listener.put("password", "root");
     ServletContext ctx = sce.getServletContext();
     ctx.setAttribute("map_listener", map_listener); 
     System.out.println("2.可以在此处做一些初始化项目中对象的内容");
     
}
    
@Override
public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("===============servlet服务销毁(也就是tomcat重新部署)==================");
}

}
