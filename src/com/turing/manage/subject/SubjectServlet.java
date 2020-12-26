package com.turing.manage.subject;

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
 * @desc  试题查询模块的C层
 * @author HYZ
 * @time  2020年12月17日
 */
@SuppressWarnings("serial")
public class SubjectServlet extends HttpServlet{
	ISubjectService subjectService = new SubjectServiceImpl();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SubjectServlet-->service()");
		
		String method = request.getParameter("method");
		try {
			if ("query".equals(method)) {
				this.query(request,response);
			}else if ("queryByType".equals(method)) {
				this.queryByType(request,response);
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
			}
		} catch (ClassNotFoundException e) {
			System.out.println("类没有找到异常...");
		} catch (SQLException e) {
			System.out.println("SQL语句异常...");
		}	
	}



private void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
	System.out.println("SubjectServlet--->delete()");
	//接值:多行的试卷ID
	String[] delIdArray = request.getParameterValues("delIdArray");
	System.out.println("试卷ID:"+delIdArray);
	//调用M层实现批量删除
	subjectService.deleteByIdArray(delIdArray);
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/subject.do?method=query");
}

private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
	System.out.println("SubjectServlet-->edit()");
	//接值
	String subject_id = request.getParameter("subject_id");
	System.out.println("subject_id:"+subject_id);
	String course_id = request.getParameter("course_id");
	System.out.println("course_id:"+course_id);
	String subject_name = request.getParameter("subject_name");
	System.out.println("subject_name:"+subject_name);
	String subject_type = request.getParameter("subject_type");
	System.out.println("subject_type:"+subject_type);
	String subject_A = request.getParameter("subject_A");
	System.out.println("subject_A:"+subject_A);
	String subject_B = request.getParameter("subject_B");
	System.out.println("subject_B:"+subject_B);
	String subject_C = request.getParameter("subject_C");
	System.out.println("subject_C:"+subject_C);
	String subject_D = request.getParameter("subject_D");
	System.out.println("subject_D:"+subject_D);
	
	String subject_answer = "";
	if ("单选题".equals(subject_answer)) {
		subject_answer =  request.getParameter("subject_answer");
	}else{
		String[] subject_answer_arr = request.getParameterValues("answerArr");
		for (String string : subject_answer_arr) {
			subject_answer+=(string+",");
		}
	}
	System.out.println("subject_answer:"+subject_answer);
	String subject_remark = request.getParameter("subject_remark");
	System.out.println("subject_remark:"+subject_remark);
	
	//修改数据
	subjectService.edit(subject_id,course_id,subject_name,subject_type,subject_A,subject_B,subject_C,subject_D,subject_answer,subject_remark);
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/subject.do?method=query");
}

private void editPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	System.out.println("SubjectServlet-->editPage()");
	//接值id
	String subject_id = request.getParameter("subject_id");
	//查询
	Map<String , Object> map_subject = subjectService.querySubject(subject_id);
	System.out.println("map_subject:"+map_subject);
	List<Map<String, Object>> list_course = subjectService.queryCourse();
	System.out.println("list_course:"+list_course);
	//存入作用域
	request.setAttribute("map_subject",map_subject);
	request.setAttribute("list_course",list_course);
	//转向
	request.getRequestDispatcher("/manage/subject/edit.jsp").forward(request, response);
}

private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
	System.out.println("SubjectServlet-->add()");
	//接值
	String course_id = request.getParameter("course_id");
	System.out.println("course_id:"+course_id);
	String subject_name = request.getParameter("subject_name");
	System.out.println("subject_name:"+subject_name);
	String subject_type = request.getParameter("subject_type");
	System.out.println("subject_type:"+subject_type);
	String subject_A = request.getParameter("subject_A");
	System.out.println("subject_A:"+subject_A);
	String subject_B = request.getParameter("subject_B");
	System.out.println("subject_B:"+subject_B);
	String subject_C = request.getParameter("subject_C");
	System.out.println("subject_C:"+subject_C);
	String subject_D = request.getParameter("subject_D");
	System.out.println("subject_D:"+subject_D);
	String subject_answer = request.getParameter("subject_answer");
	System.out.println("subject_answer:"+subject_answer);
	String subject_remark = request.getParameter("subject_remark");
	System.out.println("subject_remark:"+subject_remark);
	//存储
	subjectService.save(course_id,subject_name,subject_type,subject_A,subject_B,subject_C,subject_D,subject_answer,subject_remark);
	//重定向
	response.sendRedirect(request.getContextPath()+"/manage/subject.do?method=query");
	
}

private void addPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	System.out.println("SubjectServlet-->addPage()");
	//查询课程
	List<Map<String, Object>> list_course = subjectService.queryCourse();
	System.out.println("list_course:"+list_course);
	//存入作用域
	request.setAttribute("list_course", list_course);
	
	request.getRequestDispatcher("/manage/subject/add.jsp").forward(request, response);
	
}
/**
 * @desc  条件查询
 * @param request
 * @param response
 * @throws IOException 
 * @throws ServletException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
private void queryByType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	System.out.println("SubjectServlet-->queryByType()");
	//接值
	String selectType = request.getParameter("selectType");
	String key = request.getParameter("key");
	System.out.println("selectType:"+selectType);
	System.out.println("key:"+key);
	//查询
	 List<Map<String, Object>> list_subject = subjectService.querySubjectBySelectType(selectType,key);
	request.setAttribute("list_subject", list_subject);
	//转向
	request.getRequestDispatcher("/manage/subject/list.jsp").forward(request, response);
	
	
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
	System.out.println("SubjectServlet-->query()");
	//查询语句
	List<Map<String, Object>> list_subject = subjectService.querySubject();
	System.out.println(list_subject);
	
	//存入作用域
	request.setAttribute("list_subject", list_subject);
	//转向
	request.getRequestDispatcher("/manage/subject/list.jsp").forward(request, response);
		
}
	
}
