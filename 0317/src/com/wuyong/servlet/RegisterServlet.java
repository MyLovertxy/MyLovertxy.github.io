package com.wuyong.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wuyong.dao.UsersDao;
import com.wuyong.entity.UserInfo;

public class RegisterServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 设置相应模式 解决中文乱码问题
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String stunumber = request.getParameter("username");
		String stuname = request.getParameter("password");
		String stusex = request.getParameter("sex");
		int stuage = Integer.parseInt(request.getParameter("age"));
		UserInfo stu=new UserInfo(stunumber, stuname, stusex, stuage);
		UsersDao dao =new UsersDao();
		int result = dao.insert(stu);
		if(result>0){
			/**
			 * 重定向是一种客户端行为,是发送了两次请求 前一次请求的request对象是不会做保存,两个servlet的request对象不是同一个
			 * 		地址栏url会变为目标的地址
			 */
			response.sendRedirect("login.jsp");//重定向  使用相应对象来实现跳转到servlet
			
		}else{
			response.sendRedirect("Register.jsp");
		}
	}
	
}
