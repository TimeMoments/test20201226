package com.turing.manage.grade;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @desc 考生成绩查询模块的C层
 * @author HYZ
 * @time  2020年12月16日
 */
@SuppressWarnings("serial")
public class GradeServlet extends HttpServlet{
	IGradeService gradeService = new GradeServiceImpl();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GradeServlet-->service()");
		String method = request.getParameter("method");
		try {
			if ("query".equals(method)) {
				this.query(request,response);
			}else if ("querySelect".equals(method)) {
				this.querySelect(request,response);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("类没有找到异常...");
		} catch (SQLException e) {
			System.out.println("SQL语句异常...");
		}	
	}
/**
 * @desc  条件查询
 * @param request
 * @param response
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IOException 
 * @throws ServletException 
 */
private void querySelect(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
	String sid = request.getParameter("sid");
	String key = request.getParameter("key");
	System.out.println(sid);
	System.out.println(key);
	List<Map<String, Object>> list = null;
	if("1".equals(sid)){
		list = gradeService.queryByExaminee_id(key);
	}
	else if("2".equals(sid)){
		list = gradeService.queryByCourse_name(key);
	}
	else if("3".equals(sid)){
		list = gradeService.queryByGrade_minute_use(key);
	}
	request.setAttribute("list_grade",list);
	request.getRequestDispatcher("/manage/grade/list.jsp").forward(request, response);
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
	System.out.println("GradeServlet-->query()");	
	
	List<Map<String, Object>> list_grade = gradeService.queryGrade();
	System.out.println(list_grade);
	request.setAttribute("list_grade", list_grade);
	
	request.getRequestDispatcher("/manage/grade/list.jsp").forward(request, response);
	
	
	
	
}
	
}

	
