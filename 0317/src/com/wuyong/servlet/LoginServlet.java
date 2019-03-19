package com.wuyong.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wuyong.dao.UsersDao;
import com.wuyong.entity.UserInfo;

//@WebServlet(value = "/loginServlet")
public class LoginServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 设置相应模式 解决中文乱码问题
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		// 接受从表单传递过来的 username 和password
		String username = request.getParameter("username");
		String upassword = request.getParameter("upassword");
		UsersDao dao =new UsersDao();
		UserInfo user = dao.login(username, upassword);
		if(user!=null){
			//使用请求转发 在转发的同时在请求上绑定对象
//			request.setAttribute("user", user);//只能传递到下一个servlet或jsp,不能再整个访问过程中共享
			//获得session
			HttpSession session = request.getSession();
			//将数据对象 绑定上session,这样这个对象能在整个访问过程中共享
			session.setAttribute("user", user);
			//设置seesion的存活时间10秒
//			session.setMaxInactiveInterval(10);
			response.sendRedirect("JdbcDemo");
//			request.getRequestDispatcher("JdbcDemo").forward(request, response);
		}else{
			response.sendRedirect("login.jsp");

		}
	}

}
