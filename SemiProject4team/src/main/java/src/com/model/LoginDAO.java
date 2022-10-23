package src.com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginDAO {
	private static LoginDAO instance;
	
	private LoginDAO() {
		
	}
	
	public static LoginDAO getInstance() {
		if(instance == null) {
			instance = new LoginDAO();
		}
		return instance;
	}
	
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	
	// DB 연동 작업을 진행하는 메소드
		public void openConn() {
			try {
				// 1단계: JNDI 서버 객체 생성
				Context ctx = new InitialContext();

				// 2단계: lookup() 메소드를 이용하여 매칭되는 커넥션을 찾는다
				DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
				System.out.println("연결성공");
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
		
		public void test() {
			openConn();
			System.out.println("성공");
		}
}
