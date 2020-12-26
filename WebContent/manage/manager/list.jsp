<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>网络在线考试――后台管理</title>
		<link href="<%=request.getContextPath() %>/CSS/style.css" rel="stylesheet"></link>
		
	</head>
	
		<style type="text/css"> 
			.show{
				position:absolute;
				display:none;
			}
			.image:hover+.show{
				display:block;
			}
			
	
		</style>  
	<!--
		<script type="text/javascript">
		
		//显示图片  
        function displayImg() {  
        	var n = document.getElementById()
           var img = document.getElementById("image");
      
            //var x = event.clientX + document.body.scrollLeft + 20;  
            //var y = event.clientY + document.body.scrollTop - 5;   
           var e = event || window.event;
           var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
           var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
           var x = e.pageX || e.clientX + scrollX;
           var y = e.pageY || e.clientY + scrollY;  
           img.style.left = x + 10 + "px";  
           img.style.top = y + 10 + "px";  
           img.style.display = "block";  
       
		}
   //图片消失
    function vanishImg(){ //theEvent用来传入事件，Firefox的方式
		let imgId = "image";
		if(typeof(HTMLElement)!="undefined"){    //给firefox定义contains()方法，ie下不起作用     
          HTMLElement.prototype.contains=function(obj){
                 while(obj!=null&&typeof(obj.tagName)!="undefind"){ //通过循环对比来判断是不是obj的父元素
                    if(obj==this) return true;
                    obj=obj.parentNode;
                  }
                  return false;
              };
        }
         var theEvent = arguments.callee.caller.arguments[0] || window.event
         if (theEvent){
              var browser=navigator.userAgent;   //取得浏览器属性
              if (browser.indexOf("Firefox")>0){ //如果是Firefox
               if (document.getElementById(imgId).contains(theEvent.relatedTarget)) { //如果是子元素
                  return;   //结束函式
                }
            }
         }
        /*要执行的操作*/
         var img = document.getElementById(imgId);
         img.style.display = "none";
    }
	

		</script>
		 -->
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
											&nbsp;当前位置：<span class="word_darkGrey">管理员信息管理 &gt;&gt;&gt;</span>
										</td>
										<td align="right">
											<img src="<%=request.getContextPath()%>/Images/m_ico1.gif" width="5" height="9">&nbsp;
											当前管理员：<%=((Map)session.getAttribute("user")).get("manager_name")%>&nbsp;<br/>
											当前在线人数为:<font color="red"><%=application.getAttribute("numSessions") %>个人</font>
										</td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<td align="center" valign="top">
								<table width="96%" height="40" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td  height="30" align="right">
											<img src="<%=request.getContextPath() %>/Images/add.gif" width="19" height="18">&nbsp;
											<a href="<%=request.getContextPath()%>/manage/manager/add.jsp">添加管理员信息</a>&nbsp;&nbsp;
										</td>
									</tr>
								</table>
								<table width="96%" border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#FFFFFF" bordercolorlight="#67A8DB">
									<tr align="center" bgcolor="#A8D8FC">
									    <td width="5%" height="26" bgcolor="#B2D6F1">序号</td>
										<td width="5%" height="26" bgcolor="#B2D6F1">管理员名称</td>
										<!-- <td width="7%" height="26" bgcolor="#B2D6F1">管理员头像</td> -->
										<td width="5%" height="26" bgcolor="#B2D6F1">修改</td>
										<td width="5%" height="26" bgcolor="#B2D6F1">删除</td>
									</tr>
                                       
                                          <%
                                           //List list=(List)request.getAttribute("list");
                                             //for(int i =0;i<list.size();i++)
                                             //{
                                            	// Map map=(Map)list.get(i);
                                            	 //System.out.println(map);
                                          %>

  										<%
  											int i = 1;
  										%>
									<c:forEach var="map" items="${list}">
									<tr>
									    <td style="padding:5px;text-align:center"><%=i++ %></td>
									    <td style="padding:5px;text-align:center">
									    	
									    	<div class="image">
									    		<!--  
									    		 <a href="#" onmouseover="displayImg()" onmouseout="vanishImg()" onmousemove="displayImg()"></a>
									    		-->
									    		${map.manager_name}
									    		
									    	</div>
									    	<div class="show" >
									     		<img style="width:175px;height:100px" src="<%=request.getContextPath()%>/manage/manager.do?method=download&manager_imgpath=${map.manager_imgpath}" />
									     	</div>
									     	
									     	
									    </td>
									    
									    <!--   <td style="padding:5px;text-align:center"><img  style="width:210px;height:100px" src="<%=request.getContextPath()%>/manage/manager.do?method=download&manager_imgpath=${map.manager_imgpath}"/></td>-->
										<!-- 
										<div id="image" name="<%=i%>" style="position: absolute;display: none;  ">  
									        <img  alt="" style="width:210px;height:100px" src="<%=request.getContextPath()%>/manage/manager.do?method=download&manager_imgpath=${map.manager_imgpath}%>"/>  
									    </div> 
										 -->
										 
									     
										 
										<td style="text-align:center">&nbsp;
											<a href="<%=request.getContextPath() %>/manage/manager.do?method=editPage&manager_id=${map.manager_id}">修改</a>
										</td>
									    <td style="text-align:center">&nbsp;
											<a href="<%=request.getContextPath() %>/manage/manager.do?method=delete&manager_id=${map.manager_id}">删除</a>
										</td>
									</tr>
									</c:forEach> 
									 <%
                                             //}
									 %>
									
								</table>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
		<%@ include file="/manage/copyright.jsp"%>
	</body>
</html>
