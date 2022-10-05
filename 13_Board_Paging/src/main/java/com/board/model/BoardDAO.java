package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.board.model.BoardDAO;

public class BoardDAO {
	private static BoardDAO instance;


	public Connection con = null;
	public ResultSet rs = null;
	public PreparedStatement pstmt = null;

	String sql = null;


	private BoardDAO() {

	}

	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}

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
	
	// board 테이블의 전체 게시물의 수를 확인하는 메소드
	public int getBoardCount() {
		int count = 0;
		openConn();
		
		try {
			sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	} // getBoardCount
	
	// board 테이블에서 현재 페이지에 해당하는 게시물을 조회하는 메소드
	public List<BoardDTO> getBoardList(int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		 // 해당 페이지에서 시작번호
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지에서 끝 번호
		int endNo = (page * rowsize);
		
		openConn();
		try {
			sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b) where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			
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
	}
	
	public int setBoardList(BoardDTO dto) {
		int count = 0;
		int result = 0;
		openConn();
		
		try {
			sql = "select max(board_no) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			sql = "insert into board values (?, ?, ?, ?, ?, default, sysdate, sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count+1);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getCont());
			pstmt.setString(5, dto.getPwd());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // setBoardList 게시글 추가 
	
	// board 테이블의 게시물 번호에 해당하는 게시글의
	// 조회수를 증가시키는 메소드
	public void getBoardHit(int no) {
		openConn();
		
		try {
			sql = "update board set board_hit = board_hit+1 where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
	} // getBoardHit(int no) 메소드 end
	
	// board 테이블에서 게시글 번호에 해당하는 게시글을 조회하는 메소드
	public BoardDTO getBoardCont(int no) {
		BoardDTO dto = null;
		
		openConn();
		
		try {
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	
				dto = new BoardDTO();
				dto.setNo(rs.getInt("board_no"));
				dto.setWriter(rs.getString("board_writer"));
				dto.setTitle(rs.getString("board_title"));
				dto.setCont(rs.getString("board_cont"));
				dto.setPwd(rs.getString("board_pwd"));
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
	}
}
