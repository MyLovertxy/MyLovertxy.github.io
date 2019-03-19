package com.wuyong.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuyong.dao.EmpDao;
import com.wuyong.entity.Empinfo;
import com.wuyong.entity.UserInfo;
public class JdbcDemo extends GenericServlet{
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		 
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		//设置相应模式  解决中文乱码问题
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		if(user==null){//未登录
					response.sendRedirect("login.jsp");
					return;
		}
		//访问数据库
		response.getWriter().println("<h1>uname:"+user.getUsername()+"</h1>");
		response.getWriter().println("<a href='outLoginServlet'>退出</a>");
		response.getWriter().println("<a href='insert.jsp'>添加</a>");

		response.getWriter().println(session.getId()+"<br/>");
		EmpDao em =new EmpDao();
		ArrayList<Empinfo> list =em.query_all();		
		//输出页面显示 输出内容中文 输出到表格
		response.getWriter().println("<table border='1'>");
		for (Empinfo emp : list) {
			response.getWriter().println("<tr>");
			response.getWriter().println("<td><a href='querySingleEmp?empno="+emp.getEmpno()+"'>"+emp.getEmpno()+"</a></td>");
			response.getWriter().println("<td>"+emp.getEname()+"</td>");
			response.getWriter().println("<td>"+emp.getJob()+"</td>");
			response.getWriter().println("<td>"+emp.getMgr()+"</td>");
			response.getWriter().println("<td><a href='deleteServlet?empno="+emp.getEmpno()+"'>删除</a></td>");

			response.getWriter().println("</tr>");
		}
		response.getWriter().println("</table>");
		
	}

}
