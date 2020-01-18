package mvcMem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StudentDAO {
	private static StudentDAO instance = null;
	private StudentDAO() {}
	public static StudentDAO getInstance() {
		if(instance == null) {
			synchronized (StudentDAO.class) {
				instance = new StudentDAO();
			}
		}
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/myOracle");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println("Connection 积己角菩");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection 积己角菩");
			e.printStackTrace();
		}
		return conn;
	}
	public boolean idCheck(String id) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("Select * from student where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt !=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();;} catch(SQLException e) {}			
		}
		return result;
	}
	
	public Vector<ZipCodeVO> zipcodeRead(String dong){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<ZipCodeVO> vecList = new Vector<ZipCodeVO>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from zipcode where dong like '"+dong+"%'");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ZipCodeVO tempZipcode = new ZipCodeVO();
				tempZipcode.setZipcode(rs.getString("zipcode"));
				tempZipcode.setSido(rs.getString("sido"));
				tempZipcode.setGugun(rs.getString("gugun"));
				tempZipcode.setDong(rs.getString("dong"));
				tempZipcode.setRi(rs.getString("ri"));
				tempZipcode.setBunji(rs.getString("bunji"));
				vecList.addElement(tempZipcode);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException sqle1) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException sqle2) {}
			if(conn != null) try {conn.close();} catch (SQLException sqle3) {}
		}		
		return vecList;
	}
	
	public boolean memberInsert(StudentVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getZipcode());
			pstmt.setString(9, vo.getAddress1());
			pstmt.setString(10, vo.getAddress2());
			if(pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			if(pstmt != null) try {pstmt.close();} catch (SQLException sqle2) {}
			if(conn != null) try {conn.close();} catch (SQLException sqle3) {}
		}
		return result;
	}
	
	public int loginCheck(String id, String pass) {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select pass from student where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pass").equals(pass)) {
					result = 1;
				} else {
					result = 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException sqle1) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException sqle2) {}
			if(conn != null) try {conn.close();} catch (SQLException sqle3) {}
		}	
		return result;
	}
	public StudentVo getMember(String id) {
		StudentVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from student where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new StudentVo();
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setPhone1(rs.getString("phone1"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setPhone3(rs.getString("phone3"));
				vo.setEmail(rs.getString("email"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress1(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException sqle1) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException sqle2) {}
			if(conn != null) try {conn.close();} catch (SQLException sqle3) {}
		}	
		return vo;
	}
	
	public boolean updateMember(StudentVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update student set pass=?, phone1=?, phone2=?, phone3=?, email=?,"
					+ " zipcode=?, address1=?, address2=? where id = ?");
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getPhone1());
			pstmt.setString(3, vo.getPhone2());
			pstmt.setString(4, vo.getPhone3());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipcode());
			pstmt.setString(7, vo.getAddress1());
			pstmt.setString(8, vo.getAddress2());
			pstmt.setString(9, vo.getId());
			if(pstmt.executeUpdate()>0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException sqle1) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException sqle2) {}
			if(conn != null) try {conn.close();} catch (SQLException sqle3) {}
		}	
		return result;
	}
	
	public int deleteMember(String id, String pass) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select pass from student where id = ?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pass").equals(pass)) {
					pstmt = conn.prepareStatement("delete from student where id= ?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					result = 1;
				} else {
					result = 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch (SQLException sqle1) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException sqle2) {}
			if(conn != null) try {conn.close();} catch (SQLException sqle3) {}
		}	
		return result;
	}
}













