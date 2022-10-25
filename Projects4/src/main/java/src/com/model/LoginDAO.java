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
				System.out.println("연결성공");
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
		
		// 회원인지 아닌지 확인하는 쿼리문
		public int MemberCheck(String user) {
			int result = 0;
			openConn();
			
			try {
				sql = "select * from pmember";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					if(user.equals(rs.getString("pmember_email"))) {
						result = 1;
					} else {
						result = 0;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return result;
		}
		
		public void test() {
			openConn();
		} // MemberCheck
		
		// 유저코드 중복 방지 메서드
		public boolean memberCodeCheck(long code) {
			boolean check = false;
			openConn();
			
			try {
				sql = "select pmember_code from pmember";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					if(code == rs.getInt("pmember_code")) { // int 또는 long
						check = true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return check;
		} // memberCodeCheck
		
		// 회원가입 메서드
		public int memberSignUp(LoginDTO userInfor) {
			int result = 0;
			openConn();
			
			try {
				sql = "insert into pmember values (?, ?, ?, ?, ?, ?, sysdate, 3)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userInfor.getPmember_email());
				pstmt.setString(2, userInfor.getPmember_domain());
				pstmt.setLong(3, userInfor.getPmember_code());
				pstmt.setString(4, userInfor.getPmember_name());
				pstmt.setInt(5, userInfor.getPmember_birth());
				pstmt.setInt(6, userInfor.getPmember_phone());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return result;
		} //memberSignUp
}
