package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dbCon {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	int result = 0;
	
	public dbCon() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "web";
		String password = "1234";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user,  password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<vari> getMemberList() {
		List<vari> list = new ArrayList<vari>(); 
		
		try {
			sql = "select * from member10";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vari va = new vari();
				va.setNum(rs.getInt("num"));
				va.setId(rs.getString("memid"));
				va.setName(rs.getString("memname"));
				va.setPwd(rs.getString("pwd"));
				va.setAge(rs.getInt("age"));
				va.setMileage(rs.getInt("MILEAGE"));
				va.setJob(rs.getString("job"));
				va.setAddr(rs.getString("ADDR"));
				va.setdate(rs.getString("regdate"));
				
				list.add(va);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertDB(vari va) {
		int count = 0;
		int result = 0;
		try {
			sql = "select max(num) from MEMBER10";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1); // 첫번째 인덱스
			}
			
			 sql = "insert into MEMBER10 VALUES (?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			 pstmt.setInt(1, count+1);
			 pstmt.setString(2, va.getId());
			 pstmt.setString(3, va.getName());
			 pstmt.setString(4, va.getPwd());
			 pstmt.setInt(5, va.getAge());
			 pstmt.setInt(6, va.getMileage());
			 pstmt.setString(7, va.getJob());
			 pstmt.setString(8, va.getAddr());
			 
			 result = pstmt.executeUpdate();
			 
			 rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public vari getContentMember(int no) {
		vari va = null;
		
		try {
			sql = "select * from MEMBER10 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				va = new vari();
				
				va.setNum(rs.getInt("num"));
				va.setId(rs.getString("memid"));
				va.setName(rs.getString("memname"));
				va.setPwd(rs.getString("pwd"));
				va.setAge(rs.getInt("age"));
				va.setMileage(rs.getInt("MILEAGE"));
				va.setJob(rs.getString("job"));
				va.setAddr(rs.getString("ADDR"));
				va.setdate(rs.getString("regdate"));
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return va;
	}
}
