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
	} // getBoardCont
	
	
	// 작성자, 글제목, 내용, 패스워드
	public int getBoardUpdate(BoardDTO dto) {
		String pwCheck = "";
		int result = 0;
		openConn();
		
		try {
			sql = "select board_pwd from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pwCheck = rs.getString(1);
			}
			
			if(pwCheck.equals(dto.getPwd())) {
				sql = "update board set board_title = ?, board_cont = ?, board_update = sysdate where board_no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getCont());
				pstmt.setInt(3, dto.getNo());
				result = pstmt.executeUpdate();
			} else {
				result = -1;
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} //getBoardUpdate
	
	// 안쓰는 메소드
	// 강사님: board 테이블의 게시물 번호에 해당하는 게시물을 수정하는 메소드
	public int updateBoard(BoardDTO dto) {
		int result = 0;
		openConn();
		
		try {
			sql = "update board set board_title = ?, board_cont = ?, board_update = sysdate where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getCont());
			pstmt.setInt(3, dto.getNo());
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
		return result;
		
	} //updateBoard
	
	public int setBoardDelete(BoardDTO dto) {
		int check = 0;
		openConn();
		
		try {
			sql = "delete from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			
			check = pstmt.executeUpdate();
			
			if(check > 0) {
				sql = "update board set board_no = board_no -1 where board_no > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getNo());
				pstmt.executeUpdate();
			} else {
				return check;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
		return check;
	} // setBoardDelete()
	
	// 강사님 삭제 메서드 >> param을 이용하여 가져오 데이터들
	// 해당 글번호에 게시글 삭제
	public int deleteBoard(int no, String pwd) {
		int result = 0;
		openConn();
		
		try {
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(pwd.equals(rs.getString("board_pwd"))) {
					sql = "delete from board "
							+ "where            board_no = ?"; // 띄어쓰기 여러개여도 노상관
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					result = pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} //deleteBoard
	
	// 강사님 : 삭제된 번호 초기화 시키기
	public void updateSequence(int no) {
		openConn();
		
		try {
			sql = "update board set board_no = board_no - 1 where board_no > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
	}
	
	// board 테이블에서 검색어에 해당하는 게시물의 수를 조회하는 메소드
	public int searchListCount(String field, String keyword) {
		int count = 0;
		openConn();
		
		if(field.equals("title")) {
			try {
				sql = "select count(*) from board where board_title like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
		} else if(field.equals("cont")) {
			try {
				sql = "select count(*) from board where board_cont like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
		} else if(field.equals("title_cont")) {
			try {
				sql = "select count(*) from board where board_title like ? OR board_cont like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, pstmt, con);
			}
		} else if(field.equals("writer")) {
			try {
				sql = "select count(*) from board where board_writer like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
		}
		return count;
	} // searchListCount();
	
	
	// board 테이블에서 검색한 내용을 가지고
	// 페이징 처리를 하는 메소드
	public List<BoardDTO> searchBoardList(String field, String keyword, int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		// 해당 페이지에서 마지막 번호
		int endNo = (page * rowsize);
		
		openConn();
		
		if(field.equals("title")) {
			try {
				sql = "select * from (select row_number() "
						+ "over(order by board_no desc) rnum, "
						+ "b.* from board b where board_title like ?) "
						+ "where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
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
		} else if (field.equals("cont")) {
			try {
				sql = "select * from (select row_number() "
						+ "over(order by board_no desc) rnum, "
						+ "b.* from board b where board_cont like ?) "
						+ "where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
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
		} else if(field.equals("title_cont")) {
			try {
				sql = "select * from (select row_number() "
						+ "over(order by board_no desc) rnum, "
						+ "b.* from board b where board_title like ? or board_cont like ?) "
						+ "where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, startNo);
				pstmt.setInt(4, endNo);
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
		} else if(field.equals("writer")) {
			try {
				sql = "select * from (select row_number() "
						+ "over(order by board_no desc) rnum, "
						+ "b.* from board b where board_writer like ?) "
						+ "where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
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
		}
		return list;
	} // searchBoardList()
}
