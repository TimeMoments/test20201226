<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>网络在线考试――后台管理</title>
		<link href="<%=request.getContextPath() %>/CSS/style.css" rel="stylesheet"></link>
		<script type="text/javascript">
			function goBack(){
				//alert("回退");
				window.history.back();
			}
	
		</script>
	
	</head>
	<body>
	
		<%@ include file="/manage/top.jsp"%>
		
		<table width="960" border="0" align="center" cellspacing="0" cellpadding="0">
			<tr>
				<td width="176" align="center" valign="top" bgcolor="#FFFFFF">
					<%@ include file="/manage/left.jsp"%>
				</td>
				<td valign="top" bgcolor="#FFFFFF">
					<table width="99%" border="0" cellpadding="0" cellspacing="0" align="center">
						<tr>
							<td height="30" bgcolor="#EEEEEE" class="tableBorder_thin">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="78%" class="word_grey">
											&nbsp;当前位置：
											<span class="word_darkGrey">考生信息管理 &gt;&gt; 修改考生信息&gt;&gt;</span>
										</td>
										<td align="right">
											<img src="<%=request.getContextPath() %>/Images/m_ico1.gif" width="5" height="9">&nbsp;
											当前管理员：<%=((Map)session.getAttribute("user")).get("manager_name")%>&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="center" valign="top">
								<table width="100%"  border="0" cellspacing="0" cellpadding="0">
									<tr>
								   		<td width="84%">&nbsp;      </td>
									</tr>
								</table> 
								<form name="form1" method="post" action="<%=request.getContextPath() %>/manage/examinee.do?method=edit" onsubmit="return checkForm(form1)  style="margin: 0px">
									<table width="96%"  border="0" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#D2E3E6" bordercolorlight="#FFFFFF">
										
										<tr align="center">
								    		
								    		<c:forEach var="map_examinee" items="${list_examinee}">
								    		<td width="17%" height="30" align="left" style="padding:5px;">准考证号:&nbsp;&nbsp;&nbsp;${map_examinee.examinee_id} </td>
								    		<td align="left">
								    			
									  		</td>
									  		</c:forEach>
										</tr>
										<tr align="center">
								    		<td width="17%" height="30" align="left" style="padding:5px;">考生姓名: </td>
								    		
								    		<td align="left">
								    			<input type="hidden" name="examinee_id" size="30" value="${map_examinee.examinee_id}"/>
								      			<input type="text" name="examinee_name" size="30" value="${map_examinee.examinee_name}"/>
									  		</td>
										</tr>
										<tr align="center">
								    		<td width="17%" height="30" align="left" style="padding:5px;">性别: </td>
								    		
								    		<td align="left">
								    			<input type="hidden" name="examinee_id" size="30" value="${map_examinee.examinee_id}"/>
								      			<input type="text" name="examinee_sex" size="30" value="${map_examinee.examinee_sex}"/>
									  		</td>
										</tr>
										<tr align="center">
								    		<td width="17%" height="30" align="left" style="padding:5px;">加入时间: </td>
								    		
								    		<td align="left">
								    			<input type="hidden" name="examinee_id" size="30" value="${map_examinee.examinee_id}"/>
								      			<input type="text" name="examinee_time" size="30" value="${map_examinee.examinee_time}"/>
									  		</td>
										</tr>
										<tr align="center">
								    		<td width="17%" height="30" align="left" style="padding:5px;">密码问题: </td>
								    		
								    		<td align="left">
								    			<input type="hidden" name="examinee_id" size="30" value="${map_examinee.examinee_id}"/>
								      			<input type="text" name="examinee_question" size="30" value="${map_examinee.examinee_question}"/>
									  		</td>
										</tr>
										<tr align="center">
								    		<td width="17%" height="30" align="left" style="padding:5px;">身份证号: </td>
								    		
								    		<td align="left">
								    			<input type="hidden" name="examinee_id" size="30" value="${map_examinee.examinee_id}"/>
								      			<input type="text" name="examinee_identity" size="30" value="${map_examinee.examinee_identity}"/>
									  		</td>
										</tr>
										
								    	<tr>
								      		<td height="65" align="left" style="padding:5px;">&nbsp;</td>
								      		<td align="left">
								      			<input type="submit" name="submit" value=" 保存 " class="btn_grey">
												&nbsp;
												<input type="button" name="button" value=" 返回 " onclick="goBack()" class="btn_grey">		
											</td>
								    	</tr>
										
								
										
									</table>
									
								</form>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
		<%@ include file="/manage/copyright.jsp"%>
	</body>
</html>
