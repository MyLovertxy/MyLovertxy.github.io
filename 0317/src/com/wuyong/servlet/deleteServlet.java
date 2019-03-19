package com.wuyong.servlet;


import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuyong.dao.EmpDao;
import com.wuyong.entity.UserInfo;

public class deleteServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		UserInfo user = (UserInfo) session.getAttribute("user");
		if(user==null){//Î´µÇÂ¼
					response.sendRedirect("login.jsp");
					return;
		}
		int empno = Integer.parseInt(request.getParameter("empno"));
		//Êý¾Ý¿â²Ù×÷É¾³ý
		EmpDao dao =new EmpDao();
		int n=dao.delete(empno);
		request.getRequestDispatcher("JdbcDemo").forward(request, response);


	}

}
