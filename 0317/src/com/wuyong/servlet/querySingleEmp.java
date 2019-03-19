package com.wuyong.servlet;


import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuyong.dao.EmpDao;
import com.wuyong.entity.Empinfo;
import com.wuyong.entity.UserInfo;



public class querySingleEmp extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
			HttpServletRequest request=(HttpServletRequest)req;
			HttpServletResponse response=(HttpServletResponse)res;
		//������Ӧģʽ  ���������������
		response.setContentType("text/html;charset=utf-8");//������Ӧ���ݵ�����
		HttpSession session = request.getSession();

		UserInfo user = (UserInfo) session.getAttribute("user");
		if(user==null){//δ��¼
					response.sendRedirect("login.jsp");
					return;
		}
		response.getWriter().println("<h1>uname:"+user.getUsername()+"</h1>");

		int empno = Integer.parseInt(request.getParameter("empno"));
		EmpDao emp=new EmpDao();
		Empinfo empinfo=emp.singleQuery(empno);
		response.getWriter().println(empinfo.getEmpno()+"<br/>");
		response.getWriter().println(empinfo.getEname()+"<br/>");
		response.getWriter().println(empinfo.getJob()+"<br/>");
		response.getWriter().println(empinfo.getMgr()+"<br/>");
		
		response.getWriter().println("<a href='JdbcDemo'>������һ��</a>");
	}
		
}
