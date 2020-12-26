<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>网络在线考试――后台管理</title>
		<link href="<%=request.getContextPath() %>/CSS/style.css" rel="stylesheet"></link>
	</head>
	<body>
	
		<%@ include file="/manage/top.jsp"%>
		
		<table width="960" border="0" align="center" cellspacing="0" cellpadding="0">
			<tr>
				<td width="176" align="center" valign="top" bgcolor="#FFFFFF">
				
					<%@ include file="/manage/left.jsp"%>
					
				</td>
				<td align="right" valign="top" bgcolor="#FFFFFF">
					<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
						<tr>
							<td height="30" bgcolor="#EEEEEE" class="tableBorder_thin">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="78%" class="word_grey" align="left">
											&nbsp;当前位置：<span class="word_darkGrey">试卷信息管理 &gt;&gt;修改试卷信息&gt;&gt;</span>
										</td>
										<td align="right">
											<img src="<%=request.getContextPath() %>/Images/m_ico1.gif" width="5" height="9">&nbsp;
											当前管理员：<%=((Map)session.getAttribute("user")).get("manager_name")%>&nbsp;
										</td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<td align="center" valign="top">
								<table width="100%"  border="0" cellspacing="0" cellpadding="0">
									<tr>
								   		<td width="84%">&nbsp;      </td>
									</tr>
								</table> 
								
								<form name="form1" method="post" action="<%=request.getContextPath() %>/manage/testpaper.do?method=edit" onsubmit="return checkForm(form1)">
									<table width="96%"  border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#D2E3E6" bordercolorlight="#FFFFFF">
										<tr align="center">
								    		<td width="17%" height="30" align="left" style="padding:5px;">试卷名称：</td>
								    		<td align="left">
								    			<input type="hidden" name="testpaper_id" value="${map_testpaper.testpaper_id}"/>
								      			<input type="text" name="testpaper_name" size="30" value="${map_testpaper.testpaper_name }">
									  		</td>
										</tr>
										<tr align="center">
								    		<td width="17%" height="30" align="left" style="padding:5px;">单选题数量：</td>
								    		<td align="left">
								      			<input type="text" name="testpaper_radio_num" size="30" value="${map_testpaper.testpaper_radio_num }">
									  		</td>
										</tr>
										<tr align="center">
								    		<td width="17%" height="30" align="left" style="padding:5px;">多选题数量：</td>
								    		<td align="left">
								      			<input type="text" name="testpaper_check_num" size="30" value="${map_testpaper.testpaper_check_num }">
									  		</td>
										</tr>
										<tr align="center">
								    		<td width="17%" height="30" align="left" style="padding:5px;">考试用时(分钟)：</td>
								    		<td align="left">
								      			<input type="text" name="testpaper_time_use" size="30" value="${map_testpaper.testpaper_time_use }">
									  		</td>
										</tr>
										   <tr align="center">
								    		<td width="17%" height="30" align="left" style="padding:5px;">所属班级：</td>
								    		<td align="left">
												<select name="classs_id">
													<option value="%">---请选择---</option>
													<%
														String classs_from_id=(String)request.getAttribute("classs_id");
														List list_classs = (List)request.getAttribute("list_classs");
														for(int i = 0;i<list_classs.size();i++){
															Map map_classs = (Map)list_classs.get(i);
															String classs_name=(String)map_classs.get("classs_name");
															String classs_id=(String)map_classs.get("classs_id");
													%>
													<option value="<%=classs_id %>" <%=(classs_id.equals(classs_from_id)?"selected":"")%> ><%=classs_name %></option>
													<%
														}
													%>
												
												</select>
									  		</td>
										</tr>
										<tr align="center">
								    		<td width="17%" height="30" align="left" style="padding:5px;">所属套题：</td>
								    		<td align="left">
												<select name="course_id"  >
													<option value="%">---请选择---</option>
													<%
														String course_from_id=(String)request.getAttribute("course_id");
														List list_course = (List)request.getAttribute("list_course");
														for(int i = 0;i<list_course.size();i++){
															Map map_course = (Map)list_course.get(i);
															String course_name=(String)map_course.get("course_name");
															String course_id=(String)map_course.get("course_id");
													%>
													<option value="<%=course_id %>"  <%=(course_id.equals(course_from_id)?"selected":"")%> ><%=course_name %></option>
													<%
														}
													%>
												</select>
									  		</td>
										</tr>
										
								    	<tr>
								      		<td height="65" align="left" style="padding:5px;">&nbsp;</td>
								      		<td align="left">
								      			<input type="submit" name="submit" value=" 保存 " class="btn_grey">
												&nbsp;
												<input type="button" name="button" value=" 返回 " onclick="window.location.href='<%=request.getContextPath() %>/manage/testpaper/list.jsp'" class="btn_grey">		
											</td>
								    	</tr>
									</table>
								</form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<%@ include file="/manage/copyright.jsp"%>
	</body>
</html>
