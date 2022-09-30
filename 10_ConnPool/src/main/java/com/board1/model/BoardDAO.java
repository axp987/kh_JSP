package com.board1.model;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.board1.model.BoardDTO;
import com.board1.model.BoardDAO;

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
	
	// board 테이블의 전체 레코드를 조회하는 메소드
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		openConn();
		try {
			sql = "select * from board2 order by board_no desc";
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
				dto.setCol(rs.getInt("board_col"));
				dto.setDate(rs.getString("board_date"));
				dto.setUpdate(rs.getString("BOARD_UPDATE"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	} // getBoardList()
	
	public int setInsert(BoardDTO dto) {
		int result = 0;
		openConn();
		try {
			sql = "select max(board_no) from board2";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 작성자, 분류, 제목, 내용, 패스워드
				sql = "insert into board2 values (?, ?, ?, ?, ?, default, ?, sysdate, sysdate)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt(1)+1);
				pstmt.setString(2, dto.getWriter());
				pstmt.setString(3, dto.getTitle());
				pstmt.setString(4, dto.getCont());
				pstmt.setString(5, dto.getPwd());
				pstmt.setInt(6, dto.getCol());
				
				result = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // setInsert()
	
	public BoardDTO getContentList(int no) {
		BoardDTO dto = new BoardDTO(); 
		openConn();
		
		try {
			sql = "select * from board2 where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("board_no"));
				dto.setWriter(rs.getString("board_writer"));
				dto.setTitle(rs.getString("board_title"));
				dto.setCont(rs.getString("board_cont"));
				dto.setPwd(rs.getString("board_pwd"));
				dto.setHit(rs.getInt("board_hit"));
				dto.setCol(rs.getInt("board_col"));
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
	
	public int getUpdateCheck(BoardDTO dto) {
		int result = 0;
		openConn();
		try {
			sql = "select board_pwd from board2 where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(dto.getPwd().equals(rs.getString(1))) {
					result = 1;
				} else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // getUpdate()
	
	// 패스워드가 맞을시
	public BoardDTO getUpdateOkList(BoardDTO no) {
		BoardDTO dto = new BoardDTO();
		try {
			sql = "select * from board2 where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no.getNo());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("board_no"));
				dto.setWriter(rs.getString("board_writer"));
				dto.setTitle(rs.getString("board_title"));
				dto.setCont(rs.getString("board_cont"));
				dto.setPwd(rs.getString("board_pwd"));
				dto.setHit(rs.getInt("board_hit"));
				dto.setCol(rs.getInt("board_col"));
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
	} //getUpdateOkList
	
	public int setUpdate(BoardDTO dto) {
		openConn();
		int check = 0;
		try {
			sql = "update board2 set board_writer = ?, board_title = ?, board_cont = ?, board_pwd = ?, board_col = ?, board_update = sysdate where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getCont());
			pstmt.setString(4, dto.getPwd());
			pstmt.setInt(5, dto.getCol());
			pstmt.setInt(6, dto.getNo());
			
			check = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
		return check;
	}
	
	public List<BoardDTO> getSearch(String search, String key) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		openConn();
		
		try {
			if(search.equals("title")) {
				sql = "select * from board2 where board_title like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + key + "%");
			} else if(search.equals("cont")) {
				sql = "select * from board2 where board_cont like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + key + "%");
			} else if(search.equals("title_cont")) {
				sql = "select * from board2 where board_title like ? OR board_cont like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + key + "%");
				pstmt.setString(2, "%" + key + "%");
			} else {
				sql = "select * from board2 where board_writer like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + key + "%");
			}
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("board_no"));
				dto.setWriter(rs.getString("board_writer"));
				dto.setTitle(rs.getString("board_title"));
				dto.setCont(rs.getString("board_cont"));
				dto.setPwd(rs.getString("board_pwd"));
				dto.setHit(rs.getInt("board_hit"));
				dto.setCol(rs.getInt("board_col"));
				dto.setDate(rs.getString("board_date"));
				dto.setUpdate(rs.getString("BOARD_UPDATE"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	} // getSearch
	
	public int setDelete(int no) {
		openConn();
		int check = 0;
		try {
			sql = "delete from board2 where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			check = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return check;
	} //setDelete
	
	public void setDelteUpdate(int no) {
		openConn();
		try {
			sql = "update board2 set board_no = board_no - 1 where board_no > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

	}
	
	public List<BoardDTO> searchCol(int no) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		openConn();
		
		try {
			sql = "select * from board2 where board_col = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("board_no"));
				dto.setWriter(rs.getString("board_writer"));
				dto.setTitle(rs.getString("board_title"));
				dto.setCont(rs.getString("board_cont"));
				dto.setPwd(rs.getString("board_pwd"));
				dto.setHit(rs.getInt("board_hit"));
				dto.setCol(rs.getInt("board_col"));
				dto.setDate(rs.getString("board_date"));
				dto.setUpdate(rs.getString("BOARD_UPDATE"));
				
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
}
