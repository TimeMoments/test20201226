package com.turing.manage.classs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @desc 班級管理模块的C层
 * @author HYZ
 * @time  2020年12月7日
 */
@SuppressWarnings("serial")
public class ClasssServlet extends HttpServlet{
	IClasssService classsService = new ClasssServiceImpl();
	private int i = 1;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ClasssServlet--->service()");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		try {
			if ("query".equals(method)) {
				this.query(request,response);
			}else if ("addPage".equals(method)) {
				this.addPage(request,response);
			}else if ("add".equals(method)) {
				this.add(request,response);
			}else if ("editPage".equals(method)) {
				this.editPage(request,response);
			}else if ("edit".equals(method)) {
				this.edit(request,response);
			}else if ("delete".equals(method)) {
				this.delete(request,response);
			}else if ("doSy".equals(method)) {
				this.doSy(request,response);	
			}
		} catch (ClassNotFoundException e) {
			System.out.println("异常信息为:"+e.getMessage());
			System.out.println("异常发生的内存地址:"+e.getStackTrace());
			request.setAttribute("error", "类没有找到异常");
			request.getRequestDispatcher("/manage/error.jsp").forward(request, response);
		} catch (SQLException e) {
			System.out.println("异常的信息为:"+e.getMessage());
			System.out.println("异常发生的内存地址:"+e.getStackTrace());
			request.setAttribute("error", "SQL语句异常");
			request.getRequestDispatcher("/manage/error.jsp").forward(request, response);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

private void doSy(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, IOException {
	response.setContentType("text/html;charset=utf-8");
	synchronized (ClasssServlet.class) {
		response.getWriter().write("这是第"+i+"次访问...");
		Thread.sleep(5000);
		i++;
	}
}
/**
 * @desc  删除（批量删除）
 * @param request
 * @param response
 * @throws IOException
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
	//1.接值
	String[] delIdArray = request.getParameterValues("delIdArray");
	//2.调用M层中的方法实现删除
	classsService.deleteByIdArray(delIdArray);
	//3.重定向
	response.sendRedirect(request.getContextPath()+"/manage/classs.do?method=query");
}
/**
 * @desc  将修改页面数据更新数据库
 * @param request
 * @param response
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws SQLException
 */
private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
	//1.接值
	String classs_id = request.getParameter("classs_id");
	System.out.println("classs_id:"+classs_id);
	String classs_name = request.getParameter("classs_name");
	System.out.println("classs_name:"+classs_name);
	synchronized (this) {
		//2.调用M层中的方法实现更新
		classsService.update(classs_id,classs_name);
	}
	//3.重定向
	response.sendRedirect(request.getContextPath()+"/manage/classs.do?method=query");
}
/**
 * @desc  进入修改页面
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void editPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	//1.接值
	String classs_id = request.getParameter("classs_id");
	System.out.println("classs_id:"+classs_id);
	//2.查询该条信息
	Map<String, Object> map = classsService.queryClasssById(classs_id);
	//3.存值
	request.setAttribute("map", map);
	//4.转向
	request.getRequestDispatcher("/manage/classs/edit.jsp").forward(request, response);
}
/**
 * @desc 保存添加页面的内容
 * @param request
 * @param response
 * @throws IOException
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
		//1.接值
		String classs_name=request.getParameter("classs_name");
		System.out.println("classs_name:"+classs_name);
		//2.调用M层中的方法实现保存
		classsService.save(classs_name);
		//3.重定向
		response.sendRedirect(request.getContextPath()+"/manage/classs.do?method=query");
}
/**
 * @desc  进入添加页面
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
private void addPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/manage/classs/add.jsp").forward(request, response);
}
/**
 * @desc 查询
 * @param request
 * @param response
 * @throws ClassNotFoundException
 * @throws SQLException
 * @throws ServletException
 * @throws IOException
 */
private void query(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
	List<Map<String, Object>> list = classsService.queryAll();
	request.setAttribute("list", list);
	request.getRequestDispatcher("/manage/classs/list.jsp").forward(request, response);
}
	
}
