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
		// ������Ӧģʽ ���������������
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		// ���ܴӱ����ݹ����� username ��password
		String username = request.getParameter("username");
		String upassword = request.getParameter("upassword");
		UsersDao dao =new UsersDao();
		UserInfo user = dao.login(username, upassword);
		if(user!=null){
			//ʹ������ת�� ��ת����ͬʱ�������ϰ󶨶���
//			request.setAttribute("user", user);//ֻ�ܴ��ݵ���һ��servlet��jsp,�������������ʹ����й���
			//���session
			HttpSession session = request.getSession();
			//�����ݶ��� ����session,����������������������ʹ����й���
			session.setAttribute("user", user);
			//����seesion�Ĵ��ʱ��10��
//			session.setMaxInactiveInterval(10);
			response.sendRedirect("JdbcDemo");
//			request.getRequestDispatcher("JdbcDemo").forward(request, response);
		}else{
			response.sendRedirect("login.jsp");

		}
	}

}
