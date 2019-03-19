package com.wuyong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.wuyong.dbutil.Dbutil;
import com.wuyong.entity.UserInfo;

public class UsersDao {
	public UserInfo login(String username, String password) {
		UserInfo users = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection cn = null;
		try {
			cn = Dbutil.getConn();
			String sql = "select * from userinfo where username= ?  and upassword =? ";
			
			ps = cn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs =ps.executeQuery();
			if (rs.next()) {
				users = new UserInfo(rs.getInt("userid"), rs.getString("username"), rs.getString("upassword"),
						rs.getString("sex"),rs.getInt("age"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbutil.closeConn(cn, ps, rs, null);
		}
		//假如登录失败 返回的就是null
		return users;
	}
	public int insert(UserInfo stu) {

		Connection cn=null;
		PreparedStatement ps=null;
		try {
			 cn = Dbutil.getConn();
			String sql = "insert into userinfo values(seq_stu.nextval,?,?,?,?)";
		    ps = cn.prepareStatement(sql);
			ps.setString(1, stu.getUsername());
			ps.setString(2, stu.getUpassword());
			ps.setString(3,stu.getSex());
			ps.setInt(4,stu.getAge());
			int result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Dbutil.closeConn(cn, ps, null, null);//关闭链接
		}
		return 0;
	
	}
}
