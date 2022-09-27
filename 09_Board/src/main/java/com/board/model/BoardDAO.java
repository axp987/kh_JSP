package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.model.BoardDAO;

public class BoardDAO {
	private static BoardDAO instance;


	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;

	String sql = null;


	private BoardDAO() {

	}

	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}

	public void openConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "web";
		String password = "1234";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
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
	
	
	public List<BoardDTO> getSelectList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		openConn();
		
		try {
			sql = "select * from board order by board_no desc";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("board_no"));
				dto.setWriter(rs.getString("board_writer"));
				dto.setTitle(rs.getString("board_title"));
				dto.setCont(rs.getString("board_cont"));
				dto.setPwd(rs.getString("board_pwd"));
				dto.setHit(rs.getInt("board_hit"));
				dto.setDate(rs.getString("board_date"));
				dto.setUpdate(rs.getString("board_update"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	} // getSelectList()
	
	public BoardDTO getContentList(int no) {
		BoardDTO dto = new BoardDTO();
		openConn();
		
		
		try {
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("board_no"));
				dto.setWriter(rs.getString("board_writer"));
				dto.setTitle(rs.getString("board_title"));
				dto.setCont(rs.getString("board_cont"));
				dto.setHit(rs.getInt("board_hit"));
				dto.setDate(rs.getString("board_date"));
				dto.setUpdate(rs.getString("board_update"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	} // getContentList()
	
	// 카운트 올려주는 메소드
	public void getCount(int no) {
		
		int result = 0;
		int count = 0;
		openConn();
		
		try {
			sql = "select board_hit from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			sql = "update board set board_hit = ? where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count+1);
			pstmt.setInt(2, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
	} // getCount() 
	
	
//	public int setUpdateList(int no) {
//		BoardDTO dto = new BoardDTO();
//		openConn();
//		
//		try {
//			sql = "select * from board where board_no = ?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, no);
//			
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				dto.setNo(rs.getInt("board_no"));
//				dto.setWriter(rs.getString("board_writer"));
//				dto.setTitle(rs.getString("board_title"));
//				dto.setCont(rs.getString("board_cont"));
//				dto.setHit(rs.getInt("board_hit"));
//				dto.setDate(rs.getString("board_date"));
//				dto.setUpdate(rs.getString("board_update"));
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			closeConn(rs, pstmt, con);
//		}
//		return dto;
//		
//	} // setUpdateList();
	
	public String searchPwd(int no) {
		openConn();
		String result = null;
		try {
			sql = "select board_pwd from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
}
