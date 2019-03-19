<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'insert.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   		<h1>添加新员工</h1>
   			<!--  添加数据 都会做为参数提交到servlet 
   					表单提交一般为post ,get方式传递中文会出现乱码问题而且get模式会将参数显示在url,数据不安全,post会隐藏
   			-->
   		<form action="InsertServlet" method="post">
   				ename:<input name="ename"/><br/>
   				job:<input name="job"/><br/>
   				mgr:<input name="mgr"/><br/>
   				hiredata:<select name="year">
   										<option value="2000">2000</option>
   										<option value="2001">2001</option>
   										<option value="2002">2002</option>
   										<option value="2003">2003</option>
   										<option value="2004">2004</option>
   										<option value="2005">2005</option>
   								</select>-
   								<select name="month">
   										<option value="1">1</option>
   										<option value="2">2</option>
   										<option value="3">3</option>
   										<option value="4">4</option>
   										<option value="5">5</option>
   										<option value="6">6</option>
   										<option value="7">7</option>
   										<option value="8">8</option>
   										<option value="9">9</option>
   										<option value="10">10</option>
   										<option value="11">11</option>
   										<option value="12">12</option>
   								</select>-
   								<select name="date">
   										<option value="1">1</option>
   										<option value="2">2</option>
   										<option value="3">3</option>
   										<option value="4">4</option>
   										<option value="5">5</option>
   										<option value="6">6</option>
   										<option value="7">7</option>
   										<option value="8">8</option>
   										<option value="9">9</option>
   										<option value="10">10</option>
   										<option value="11">11</option>
   										<option value="12">12</option>
   								</select><br/>
   				sal:<input name="sal"/><br/>
   				comm:<input name="comm"/><br/>
   				deptno:<select name="deptno">
   								<option value="10">10</option>
   								<option value="20">20</option>
   								<option value="30">30</option>
   								<option value="40">40</option>
   							</select><br/>
   							
   							<input type="submit" value="提交"/>
   		</form>
   		
   		<a href="JdbcDemo">返回员工列表</a>
   			
  </body>
</html>
