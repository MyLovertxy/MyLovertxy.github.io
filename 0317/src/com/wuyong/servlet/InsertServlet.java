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
import com.wuyong.entity.Empinfo;
import com.wuyong.entity.UserInfo;
public class InsertServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		UserInfo user = (UserInfo) session.getAttribute("user");
		if(user==null){//未登录
					response.sendRedirect("login.jsp");
					return;
		}
		response.getWriter().println("<h1>uname:"+user.getUsername()+"</h1>");

		String ename = request.getParameter("ename");
		String job = request.getParameter("job");
		int mgr = Integer.parseInt(request.getParameter("mgr"));
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int date = Integer.parseInt(request.getParameter("date"));
		String hiredate=year+"-"+month+"-"+date;
		int sal = Integer.parseInt(request.getParameter("sal"));
		int comm =Integer.parseInt(request.getParameter("comm"));
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		Empinfo emp=new Empinfo(ename, job, mgr, hiredate, sal, comm, deptno);
		EmpDao dao =new EmpDao();
		int result = dao.insert(emp);
		//数据库添加
		if(result>0){
			response.sendRedirect("JdbcDemo");//重定向  使用相应对象来实现跳转到servlet
			
		}else{
			response.sendRedirect("insert.jsp");
		}
		
	}

}
