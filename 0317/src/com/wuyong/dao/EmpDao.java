package com.wuyong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.wuyong.dbutil.Dbutil;
import com.wuyong.entity.Empinfo;


//dao �����ݲ������� ���ĳ�����ṩ�� ɾ ��  �� �ķ���
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
			while (rs.next()) {// �����ƶ�һ�� �������Ƿ������ݵ� �жϽ��
				//ÿ�����ݾ���һ��Empinfo���󣬽�ÿ��������֮����ӵ�������
				Empinfo emp = new Empinfo();
				//��ÿ���ֶε�������䵽stu��
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
	
	// query  single  ����һ��id��Ϊ��ѯ����������һ��Empinfo
	public Empinfo singleQuery(int empno){
		Empinfo emp =null;
		Connection cn = Dbutil.getConn();
		String sql = "select * from emp where empno = ?";
		try {
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, empno);
			
			ResultSet  rs = ps.executeQuery();
			if(rs.next()){
				//��ѯ��һ������ �ͷ�װ��һ������
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
			Dbutil.closeConn(cn, ps, null, null);//�ر�����
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
			Dbutil.closeConn(cn, ps, null, null);//�ر�����
		}
		return 0;
	}
//public static void main(String[] args) {
//	EmpDao em=new EmpDao();
//	int n=em.insert(new Empinfo("www", "emem", 8000, "2010-10-10", "800", "300", "10"));
//	System.out.println(n);
//}
	
}
