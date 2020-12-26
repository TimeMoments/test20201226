package com.turing.manage.login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @desc  登录模块的C层
 * @author HYZ
 * @time  2020年12月1日
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet{
//	ILoginService loginService = new LoginServiceImpl();
//	HttpSession session;
//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		request.setCharacterEncoding("utf-8");
//		System.out.println("LoginServlet-->service()");
//		String method = request.getParameter("method");
//		try {
//			if ("login".equals(method)) {
//				this.login(request,response);
//			}else if ("logout".equals(method)) {
//				this.logout(request,response);
//			}
//		} catch (ClassNotFoundException e) {
//			System.out.println("类没有找到异常...");
//		} catch (SQLException e) {
//			System.out.println("SQL异常...");
//		}
//	}
//	/**
//	 * @desc  2.退出登录
//	 * @param request
//	 * @param response
//	 * @throws IOException 
//	 * @throws ServletException 
//	 */
//	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//销毁session
//		session.invalidate();
//		request.getRequestDispatcher("/manage/login.jsp").forward(request, response);
//	}
//	/**
//	 * @desc 1.登录
//	 * @param request
//	 * @param response
//	 * @throws IOException 
//	 * @throws ServletException 
//	 * @throws SQLException 
//	 * @throws ClassNotFoundException 
//	 */
//	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
//		System.out.println("loginServlet-->login()");
//		//1.接值
//		String name = request.getParameter("name");
//		String pass = request.getParameter("pass");
//		System.out.println("name:"+name);
//		System.out.println("pass:"+pass);
//		//2.调用M层查询该用户是否存在
//		Map<String, Object> map = loginService.queryManagerByNameAndPass(name,pass);
//		//3.判断登录是否成功
//		if (map==null) {
//			System.out.println("登录失败");
//			//4.转向:转向到错误展示页面
//			String errorMsg="登录失败!用户名或者密码错误...";
//			request.setAttribute("error", errorMsg);
//			request.getRequestDispatcher("/manage/error.jsp").forward(request, response);
//		}else {
//			System.out.println("登陆成功了！");
//			//5.获取session，并将查询得到的该条数据信息存入到session中
//			session = request.getSession();
//			session.setAttribute("user", map);
//			String sessionId = session.getId();
//			System.out.println("sessionId:"+sessionId);
//			//4.转向:转向到管理员列表页面
//			request.getRequestDispatcher("/manage/manager/list.jsp").forward(request, response);
//		}
//	}
	
	
	
	
//	ILoginService loginService = new LoginServiceImpl();
//	HttpSession session;
//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		request.setCharacterEncoding("utf-8");
//		System.out.println("LoginServlet-->service()");
//		String method = request.getParameter("method");
//		try {
//			if ("login".equals(method)) {
//				this.login(request,response);
//			}else if ("logout".equals(method)) {
//				this.logout(request,response);
//			}
//		} catch (ClassNotFoundException e) {
//			System.out.println("类没有找到异常...");
//		} catch (SQLException e) {
//			System.out.println("SQL异常...");
//		}
//	}
//	/**
//	 * @desc  2.退出登录
//	 * @param request
//	 * @param response
//	 * @throws IOException 
//	 * @throws ServletException 
//	 */
//	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//销毁session
//		session.invalidate();
//		request.getRequestDispatcher("/manage/login.jsp").forward(request, response);
//	}
//	/**
//	 * @desc 1.登录
//	 * @param request
//	 * @param response
//	 * @throws IOException 
//	 * @throws ServletException 
//	 * @throws SQLException 
//	 * @throws ClassNotFoundException 
//	 */
//	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
//		System.out.println("loginServlet-->login()");
//		//1.接值
//		String name = request.getParameter("name");
//		String pass = request.getParameter("pass");
//		System.out.println("name:"+name);
//		System.out.println("pass:"+pass);
//		//2.调用M层查询该用户是否存在
//		Map<String, Object> map = loginService.queryManagerByNameAndPass(name,pass);
//		//3.判断登录是否成功
//		if (map==null) {
//			System.out.println("登录失败");
//			//4.转向:转向到错误展示页面
//			String errorMsg="登录失败!用户名或者密码错误...";
//			request.setAttribute("error", errorMsg);
//			request.getRequestDispatcher("/manage/error.jsp").forward(request, response);
//		}else {
//			System.out.println("登陆成功了！");
//			//5.获取session，并将查询得到的该条数据信息存入到session中
//			session = request.getSession();
//			session.setAttribute("user", map);
//			String sessionId = session.getId();
//			System.out.println("sessionId:"+sessionId);
//			//4.转向:转向到管理员列表页面
//			request.getRequestDispatcher("/manage/manager/list.jsp").forward(request, response);
//		}
//	}
	
	
	
	
	
	ILoginService loginService = new LoginServiceImpl();
	HttpSession session;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		System.out.println("LoginServlet-->service()");
		String method = request.getParameter("method");
		try {
			if ("login".equals(method)) {
				this.login(request,response);
			}else if ("logout".equals(method)) {
				this.logout(request,response);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("类没有找到异常...");
		} catch (SQLException e) {
			System.out.println("SQL异常...");
		}
	}
	/**
	 * @desc  2.退出登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//销毁session
		session.invalidate();
		request.getRequestDispatcher("/manage/login.jsp").forward(request, response);

	}
	/**
	 * @desc 1.登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		System.out.println("loginServlet-->login()");
		//1.接值
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		System.out.println("name:"+name);
		System.out.println("pass:"+pass);
		//2.调用M层查询该用户是否存在
		Map<String, Object> map = loginService.queryManagerByNameAndPass(name,pass);
		//3.判断登录是否成功
		if (map==null) {
			System.out.println("登录失败");
			//4.转向:转向到错误展示页面
			String errorMsg="登录失败!用户名或者密码错误...";
			request.setAttribute("error", errorMsg);
			request.getRequestDispatcher("/manage/error.jsp").forward(request, response);
		}else {
			System.out.println("登陆成功了！");
			//5.获取session，并将查询得到的该条数据信息存入到session中
			session = request.getSession();
			session.setAttribute("user", map);
			String sessionId = session.getId();
			System.out.println("sessionId:"+sessionId);
			//4.转向:转向到管理员列表页面
//			request.getRequestDispatcher("/manage/manager/list.jsp").forward(request, response);
//			request.getRequestDispatcher("/manage/examinee/list.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/manage/manager.do?method=query");
		
		}
	}
	
}
