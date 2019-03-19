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
		//������Ӧģʽ  ���������������
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		if(user==null){//δ��¼
					response.sendRedirect("login.jsp");
					return;
		}
		//�������ݿ�
		response.getWriter().println("<h1>uname:"+user.getUsername()+"</h1>");
		response.getWriter().println("<a href='outLoginServlet'>�˳�</a>");
		response.getWriter().println("<a href='insert.jsp'>���</a>");

		response.getWriter().println(session.getId()+"<br/>");
		EmpDao em =new EmpDao();
		ArrayList<Empinfo> list =em.query_all();		
		//���ҳ����ʾ ����������� ��������
		response.getWriter().println("<table border='1'>");
		for (Empinfo emp : list) {
			response.getWriter().println("<tr>");
			response.getWriter().println("<td><a href='querySingleEmp?empno="+emp.getEmpno()+"'>"+emp.getEmpno()+"</a></td>");
			response.getWriter().println("<td>"+emp.getEname()+"</td>");
			response.getWriter().println("<td>"+emp.getJob()+"</td>");
			response.getWriter().println("<td>"+emp.getMgr()+"</td>");
			response.getWriter().println("<td><a href='deleteServlet?empno="+emp.getEmpno()+"'>ɾ��</a></td>");

			response.getWriter().println("</tr>");
		}
		response.getWriter().println("</table>");
		
	}

}
