<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.turing.utils.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL表达式和JSTL标签</title>
</head>
<body>

<%
//1.向pageContext域存入数据
pageContext.setAttribute("company", "pageContext域");


//2.向request域中存入数据
request.setAttribute("company", "request域");

//3.向Session域中存入数据：存储一个对象
User user = new User();
user.setId(1);
user.setName("Jack");
user.setPassword("123");
session.setAttribute("user", user);


//4.向application域中存入数据:存储一个集合
List<User> list = new ArrayList<User>();
User user1 = new User();
user1.setId(2);
user1.setName("杨过");
user1.setPassword("123");
list.add(user1);
User user2 = new User();
user2.setId(3);
user2.setName("郭襄");
user2.setPassword("123");
list.add(user2);
application.setAttribute("list", list);


%>
脚本法取值：
<table border="2px" bordercolor="green" align="center">
   <tr>
	   <td>方法</td>
	   <td>值</td>
   </tr>
   <tr>
	    <td>pageContext作用域：</td>
	   <td><input type="text" value="<%=pageContext.getAttribute("company")%>"/></td>
   </tr>
    <tr>
	  <td>request作用域：</td>
	   <td><input type="text" value="<%=request.getAttribute("company") %>"/></td>
   </tr>
   
   <tr>
	  <td>session作用域：</td>
	   <td><input type="text" value="<%
		    User sessionUser = (User)session.getAttribute("user");
		    out.write(sessionUser.getName());
		   %>"/>
   </td>
   </tr>
    <tr>
	   <td>application作用域：</td>
	   <td><input type="text" value="<%
			  List listApplication=(List)application.getAttribute("list");
	          User userObj=(User)listApplication.get(0);
	          out.write(userObj.toString());
	   %>"/></td>
   </tr>

</table>

<hr/>
使用EL表达式获得域中的值
<table border="2px" bordercolor="green" align="center">
   <tr>
	   <td>方法</td>
	   <td>值</td>
   </tr>
   <tr>
	   <td>pageContext作用域：</td>
	   <td><input type="text" value="${pageScope.company}"/></td>
   </tr>
    <tr>
	   <td>request作用域：</td>
	   <td><input type="text" value="${requestScope.company}"/></td>
   </tr>

    <tr>
	   <td>session作用域：</td>
	   <td><input type="text" value="${sessionScope.user.name }"/>
   </td>
   </tr>
       <tr>
	   <td>application作用域：</td>
	   <td><input type="text" value="${applicationScope.list[1].name}"/></td>
   </tr>
     
</table>

<br/>
${1+"1" } 
<br/><br/>
2.2 关系运算 == >= <= != <br/><br/>
关系运算：${5=="5"}  其结果为"true"
<br/><br/>
2.3 逻辑运算  ! && ||<br/><br/>
逻辑运算：${2>1&&4<5} 其结果为"false"<br/><br/>

<br/>
<br/>
<%
List list2=new ArrayList();
list2.add(1);
request.setAttribute("list2", list2);
request.setAttribute("a", "");
request.setAttribute("b", null);
%>

${empty list2 }   其结果为"false"
${empty a }       其结果为 "true"
${empty b }       其结果为 "true"
${empty user}     其结果为"false"

<br/>
<br/>
<hr/>
JSTL中常用的标签<br/>
<c:set  scope="session" value="4000"  var="money" /><!-- 用于保存数据 -->
<c:if test="${money>2000}">     <!-- 与我们在一般程序中用的if一样 -->
  <c:out  value="${money}"/>    <%--  用于在JSP中显示数据，就像<%= ... > --%>
</c:if> 
3.迭代标签的深入讲解
<br/>
	先添加测试数据：
<br/>
<%
    //1.模拟List<String> strList
    List<String> strList = new ArrayList<String>();
    strList.add("dfd");
    strList.add("ret");
    strList.add("yty");
    strList.add("hk");
    request.setAttribute("strList", strList);

    //2.遍历List<User>的值
    List<User> userList = new ArrayList<User>();
    User user7 = new User();
    user7.setId(2);
    user7.setName("路人甲");
    user7.setPassword("123");
    userList.add(user7);
    
    User user8 = new User();
    user8.setId(3);
    user8.setName("路人乙");
    user8.setPassword("123");
    userList.add(user8);
    application.setAttribute("userList", userList);
    
    

    //3.遍历Map<String,String>的值
    Map strMap = new HashMap();
    strMap.put("name", "lucy");
    strMap.put("age", "18");
    strMap.put("addr", "保定");
    strMap.put("email", "lucy@163.com");
    session.setAttribute("strMap", strMap);

    //4.遍历List<Map<String,Object>>的值
    Map<String,Object> map01 = new HashMap();
    map01.put("id", "001");
    map01.put("name", "裘千尺");
    Map<String,Object> map02 = new HashMap();
    map02.put("id", "002");
    map02.put("name", "裘千刃");
    List<Map<String,Object>> listOfmap=new ArrayList<Map<String,Object>>();
    listOfmap.add(map01);
    listOfmap.add(map02);
    request.setAttribute("listOfmap", listOfmap);   
%>

<h1>取出strList的数据</h1>
<c:forEach items="${strList}" var="str">
    ${str }<br/>
</c:forEach>

<h1>取出userList的数据</h1>
<c:forEach items="${userList}" var="user">
    user的name：${user.name }------user的password：${user.password }<br/>
</c:forEach>

<h1>取出strMap的数据</h1>
<c:forEach items="${strMap}" var="entry">
    ${entry.key }====${entry.value }<br/>
</c:forEach>

<h1>取出listOfmap的数据</h1>
<c:forEach items="${listOfmap}" var="map">
    ${map.id}:${map.name}<br/>
</c:forEach>

</body>
</html>