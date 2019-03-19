package com.wuyong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.wuyong.dbutil.Dbutil;
import com.wuyong.entity.Empinfo;


//dao ：数据操作对象 针对某个表提供增 删 改  查 的方法
public class EmpDao {
	// query  all
	public ArrayList<Empinfo> query_all(){
		Connection cn = null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<Empinfo> list = new ArrayList<Empinfo>();
		try {
			cn = Dbutil.getConn();
			st = cn.createStatement();
			rs = st.executeQuery(" select * from emp ");
			while (rs.next()) {// 往下移动一行 并返回是否有数据的 判断结果
				//每行数据就是一个Empinfo对象，将每个对象获得之后添加到集合内
				Empinfo emp = new Empinfo();
				//将每个字段的数据填充到stu内
				emp.setEmpno(rs.getInt("EMPNO"));
				emp.setEname(rs.getString("ENAME"));
				emp.setJob(rs.getString("JOB"));
				emp.setMgr(rs.getInt("MGR"));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Dbutil.closeConn(cn, null, rs, st);
		}
		
		return list;
	}
	
	// query  single  传入一个id作为查询条件，返回一个Empinfo
	public Empinfo singleQuery(int empno){
		Empinfo emp =null;
		Connection cn = Dbutil.getConn();
		String sql = "select * from emp where empno = ?";
		try {
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, empno);
			
			ResultSet  rs = ps.executeQuery();
			if(rs.next()){
				//查询出一行数据 就封装成一个对象
				emp = new Empinfo();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	public int delete(int empno) {

		Connection cn = Dbutil.getConn();
		PreparedStatement ps=null;
		String sql = "delete from emp where empno = ? ";
		try {
			 ps = cn.prepareStatement(sql);
			ps.setInt(1, empno);
			int result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			Dbutil.closeConn(cn, ps, null, null);//关闭链接
		}
		
		return 0;
	
	}

	public int insert(Empinfo emp) {
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			 cn = Dbutil.getConn();
			String sql = "insert into emp values(seq_emp.nextval,?,?,?,to_date(?,'yyyy-MM-dd'),?,?,?)";
		    ps = cn.prepareStatement(sql);
			ps.setString(1, emp.getEname());
			ps.setString(2, emp.getJob());
			ps.setInt(3,emp.getMgr());
			ps.setString(4,emp.getHiredate());
			ps.setInt(5,emp.getSal());
			ps.setInt(6, emp.getComm());
			ps.setInt(7, emp.getDeptno());
			int result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Dbutil.closeConn(cn, ps, null, null);//关闭链接
		}
		return 0;
	}
//public static void main(String[] args) {
//	EmpDao em=new EmpDao();
//	int n=em.insert(new Empinfo("www", "emem", 8000, "2010-10-10", "800", "300", "10"));
//	System.out.println(n);
//}
	
}
