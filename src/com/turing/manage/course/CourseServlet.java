package com.turing.manage.course;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @desc 课程信息模块的C层
 * @author HYZ
 * @time  2020年12月16日
 */
@SuppressWarnings("serial")
public class CourseServlet extends HttpServlet{
	ICourseService courseService = new CourseServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CourseServlet-->service()");
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
			}else if ("delete_radio".equals(method)) {
				this.delete_radio(request,response);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("类没有找到异常...");
		} catch (SQLException e) {
			System.out.println("SQL语句异常...");
		}	
	}
	
/**
 * @desc  删除单个
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void delete_radio(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
	System.out.println("CourseServlet-->delete()");
	//接值
	String course_id = request.getParameter("course_id");
	System.out.println("course_id:"+course_id);
	
	//调用M层实现删除
	courseService.deleteByCourseId(course_id);
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/course.do?method=query");
}
	/**
	 * @desc 批量删除
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
private void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
	System.out.println("CourseServlet-->delete()");
	//接值:多行的试卷ID
	String[] delIdArray = request.getParameterValues("delIdArray");
	System.out.println("课程id:"+delIdArray);
	
	//调用M层实现批量删除
	courseService.deleteByIdArray(delIdArray);
	
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/course.do?method=query");
}
private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
	System.out.println("CourseServlet-->add()");
	
	//接值
	String course_id = request.getParameter("course_id");
	String course_name = request.getParameter("course_name");
	System.out.println("course_name:"+course_name);
	System.out.println("course_id:"+course_id);
	//保存
	courseService.edit(course_id,course_name);
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/course.do?method=query");
}
/**
 * @desc  进入修改页面
 * @param request
 * @param response
 * @throws IOException 
 * @throws ServletException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void editPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	System.out.println("CourseServlet-->editPage()");
	//接值
	String course_id = request.getParameter("course_id");
	System.out.println("course_id:"+course_id);
	//查询
	Map<String, Object> map_course = courseService.queryCourse(course_id);
	System.out.println("map_course:"+map_course);
	
	request.setAttribute("map_course", map_course);
	
	//转向
	request.getRequestDispatcher("/manage/course/edit.jsp").forward(request, response);
}
/**
 * @desc  保存添加页面数据
 * @param request
 * @param response
 * @throws IOException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
	System.out.println("CourseServlet-->add()");
	//接值
	String course_name = request.getParameter("course_name");
	System.out.println("course_name:"+course_name);
	//保存
	courseService.save(course_name);
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/course.do?method=query");
}
/**
 * @desc  进入添加页面
 * @param request
 * @param response
 * @throws IOException 
 * @throws ServletException 
 */
private void addPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("CourseServlet-->addPage()");
	request.getRequestDispatcher("/manage/course/add.jsp").forward(request, response);
}

/**
 * @desc  查询
 * @param request
 * @param response
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
 * @throws ServletException 
 */
private void query(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
	System.out.println("CourseServlet-->query()");	
	
	List<Map<String, Object>> list_course = courseService.queryCourse();
	System.out.println("课程:"+list_course);
	request.setAttribute("list_course", list_course);
	
	request.getRequestDispatcher("/manage/course/list.jsp").forward(request, response);
	
}
	
}

	
