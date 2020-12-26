package com.turing.manage.listeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * @desc  统计在线人数
 * @author HYZ
 * @time  2020年12月14日
 */
public class SessionListener implements HttpSessionListener{
	
	public SessionListener() {
		System.out.println("SessionListerer加载了");
	}
/**
 * @desc 1.session创建的时候
 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session已经创建，且sessionId="+se.getSession().getId());
		ServletContext ctx = se.getSession().getServletContext();
		Integer numSessions = (Integer)ctx.getAttribute("numSessions");
		if (numSessions==null) {
			numSessions = new Integer(1);
		}else {
			int count = numSessions.intValue();
			numSessions = new Integer(count+1);
		}
		ctx.setAttribute("numSessions", numSessions);
	}
/**
 * @desc  2.session销毁的时候
 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session销毁");
		ServletContext ctx = se.getSession().getServletContext();
		 Integer numSessions = (Integer) ctx.getAttribute("numSessions");
			
		 if (numSessions==null){
		   numSessions=new Integer(0);	
		 }else{
			   int count=numSessions.intValue();
			   numSessions=new Integer(count-2);
		  }
			
		 ctx.setAttribute("numSessions", numSessions);
	}
}


