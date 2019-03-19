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
		// ������Ӧģʽ ���������������
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
			 * �ض�����һ�ֿͻ�����Ϊ,�Ƿ������������� ǰһ�������request�����ǲ���������,����servlet��request������ͬһ��
			 * 		��ַ��url���ΪĿ��ĵ�ַ
			 */
			response.sendRedirect("login.jsp");//�ض���  ʹ����Ӧ������ʵ����ת��servlet
			
		}else{
			response.sendRedirect("Register.jsp");
		}
	}
	
}
