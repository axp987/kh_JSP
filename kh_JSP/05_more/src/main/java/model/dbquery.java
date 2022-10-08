package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dbquery {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	public dbquery() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "web";
		String password = "1234";
		
		try {
			// 1단계: 오라클 드라이버를 메모리로 로딩
			Class.forName(driver);
			
			// 2단계: 오라클 데이터베이스와 연결 작업
			con = DriverManager.getConnection(url, user,  password);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<vari> selectList() {
		List<vari> list = new ArrayList<vari>();
		sql = "select * from dept";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vari v = new vari();
				v.setDeptno(rs.getInt("deptno"));
				v.setDname(rs.getString("dname"));
				v.setLOC(rs.getString("LOC"));
				
				list.add(v);
			}
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
