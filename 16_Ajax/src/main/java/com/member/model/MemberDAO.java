package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private static MemberDAO instance;
	
	private MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	public Connection con = null;
	public ResultSet rs = null;
	public PreparedStatement pstmt = null;

	String sql = null;

	// DB 연동 작업을 진행하는 메소드
	public void openConn() {
		try {
			// 1단계: JNDI 서버 객체 생성
			Context ctx = new InitialContext();

			// 2단계: lookup() 메소드를 이용하여 매칭되는 커넥션을 찾는다
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");

			// 3단계: DataSource 객체를 이용하여 커넥션을 하나 가져옴
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if(rs != null) {
				rs.close();
			}

			if(pstmt != null) {
				pstmt.close();
			}

			if(con != null) {
				con.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	} // closeConn

	public void closeConn(PreparedStatement pstmt, Connection con) {
		try {
			if(pstmt != null) {
				pstmt.close();
			}

			if(con != null) {
				con.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	} // closeConn
	
	// 회원 가입 시 중복아이디 체크를 처리하는 메서드
	public int checkMemeberId(String id) {
		int result = 0;
		openConn();
		
		try {
			sql = "select * from customer where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		System.out.println(result);
		return result;
	} //checkMemeberId()
	
	public String getCustomerList() {
		String result = "";
		
		openConn();
		
		try {
			sql = "select * from customer order by no desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			result += "<customers>"; // xml의 <customers> 태그 생성
			while(rs.next()) {
				result += "<customer>";
				result += "<no>" + rs.getInt("no") + "</no>";
				result += "<id>" + rs.getString("id") + "</id>";
				result += "<name>" + rs.getString("name") + "</name>";
				result += "<age>" + rs.getInt("age") + "</age>";
				result += "<phone>" + rs.getString("phone") + "</phone>";
				result += "<addr>" + rs.getString("addr") + "</addr>";
				result += "</customer>";
			}
			result += "</customers>";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
}
