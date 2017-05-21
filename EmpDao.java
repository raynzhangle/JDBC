package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.Emp;
import util.DBUtil;

public class EmpDao implements Serializable {

	/**
	 * 增加一个员工
	 */
	public void save(Emp emp) {
		
	}
	
	/**
	 * 根据ID修改员工
	 * */
	public void update(Emp emp) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = 
				"update emps set "
				+ "ename=?,"
				+ "job=?,"
				+ "mgr=?,"
				+ "hiredate=?,"
				+ "sal=?,"
				+ "comm=?,"
				+ "deptno=? "
				+ "where empno=?";
			PreparedStatement ps = 
				conn.prepareStatement(sql);
			ps.setString(1, emp.getEname());
			ps.setString(2, emp.getJob());
			ps.setInt(3, emp.getMgr());
			ps.setDate(4, emp.getHiredate());
			ps.setDouble(5, emp.getSal());
			ps.setDouble(6, emp.getComm());
			ps.setInt(7, emp.getDeptno());
			ps.setInt(8, emp.getEmpno());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(
				"修改员工失败", e);
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 根据ID删除一个员工
	 * */
	public void delete(int id) {
		
	}
	
	/**
	 * 根据ID查询一个员工
	 * */
	public Emp findById(int id) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = 
				"select * from emps "
				+ "where empno=?";
			PreparedStatement ps = 
				conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Emp e = new Emp();
				e.setEmpno(rs.getInt("empno"));
				e.setEname(rs.getString("ename"));
				e.setJob(rs.getString("job"));
				e.setMgr(rs.getInt("mgr"));
				e.setHiredate(rs.getDate("hiredate"));
				e.setSal(rs.getDouble("sal"));
				e.setComm(rs.getDouble("comm"));
				e.setDeptno(rs.getInt("deptno"));
				return e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(
				"查询员工失败", e);
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}
	
	/**
	 * 根据部门ID查询员工
	 * */
	public List<Emp> findByDept(int deptno) {
		return null;
	}
	
}
