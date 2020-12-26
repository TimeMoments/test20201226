package com.turing.manage.classs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ClasssServlet02 extends HttpServlet{
	IClasssService02 classsService02 = new ClasssServiceImpl02();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ClasssServlet-->service()");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		try {
			if (method.equals("query")) {
				this.query(request,response);
			}else if (method.equals("addPage")) {
				this.addPage(request,response);
			}else if (method.equals("add")) {
				this.add(request, response);
			}else if (method.equals("editPage")) {
				this.editPage(request,response);
			}else if (method.equals("edit")) {
				this.edit(request, response);
			}else if (method.equals("delete")) {
				this.delete(request,response);
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
		}
	}
/**
 * @desc 删除（批量删除）
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws FileNotFoundException 
 * @throws ClassNotFoundException 
 */
private void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
	//接值
	String [] delIdArray = request.getParameterValues("delIdArray");
	//调用M层中的方法实现删除
	classsService02.deleteByIdArray(delIdArray);
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/classs.do?method=query");
}
/**
 * @desc  将修改页面数据更新数据库
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws FileNotFoundException 
 * @throws ClassNotFoundException 
 */
private void edit(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
	//接值
	String classs_id = request.getParameter("classs_id");
	System.out.println("classs_id:"+classs_id);
	String classs_name = request.getParameter("classs_name");
	System.out.println("classs_name:"+classs_name);
	synchronized (this) {
		//调用M层中的方法实现更新
		classsService02.update(classs_id, classs_name);
	}
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/classs.do?method=query");
}
/**
 * @desc 进入修改页面
 * @param request
 * @param response
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
 * @throws ServletException 
 */
private void editPage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
	//接值
	String classs_id = request.getParameter("classs_id");
	System.out.println("classs_id:"+classs_id);
	//查询该条信息
	Map<String, Object> map = classsService02.queryClasssById(classs_id);
	//存值
	request.setAttribute("map", map);
	//转向
	request.getRequestDispatcher("/manage/classs/edit.jsp").forward(request, response);
}
/**
 * @desc  保存添加页面的内容
 * @param request
 * @param response
 * @throws ClassNotFoundException
 * @throws FileNotFoundException
 * @throws SQLException
 * @throws IOException
 */
private void add(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
	//接值
	String classs_name = request.getParameter("classs_name");
	System.out.println("classs_name:"+classs_name);
	//调用M层中的方法实现保存
	classsService02.save(classs_name);
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/classs.do?method=query");
}
/**
 * @desc 进入添加页面
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
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
 * @throws ServletException 
 */
private void query(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
	List<Map<String, Object>> list = classsService02.queryAll();
	request.setAttribute("list", list);
	request.getRequestDispatcher("/manager/classs/list.jsp").forward(request, response);
}
	
}
