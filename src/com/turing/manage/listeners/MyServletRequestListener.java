package com.turing.manage.listeners;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
/**
 * @desc   通过监听器实现记录ip--->实现黑白名单--->db,excel中
 * @author HYZ
 * @time  2020年12月14日
 */
public class MyServletRequestListener implements ServletRequestListener {

@Override
public void requestDestroyed(ServletRequestEvent sre) {
	   ServletRequest request = sre.getServletRequest();
       System.out.println("requestDestroyed:remoteIp:"+request.getRemoteAddr());
}

@Override
public void requestInitialized(ServletRequestEvent sre) {
   ServletRequest request = sre.getServletRequest();
   System.out.println("requestInitialized:remoteIp:"+request.getRemoteAddr());
}

}
