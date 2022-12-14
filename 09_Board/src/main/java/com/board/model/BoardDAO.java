package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
	} // getContentList()
	
	// ????????? ???????????? ?????????
	public void getCount(int no) {
		
		int result = 0;
		int count = 0;
		openConn();
		//update board set board_hit = board_hit + 1 where board_no = ?
		try {
//			sql = "select board_hit from board where board_no = ?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, no);
//			
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				count = rs.getInt(1);
//			}
			
			//sql = "update board set board_hit = ? where board_no = ?";
			sql = "update board set board_hit = board_hit + 1 where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			//pstmt.setInt(2, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
	} // getCount() 
	
	

	
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
	
	// ????????? ????????????
	public int setUpdate(int no, String result, String pwd) {
		StringTokenizer st = new StringTokenizer(result, "???");
		openConn();
		int ch = 0;
		String pwCheck = ""; 
		try {
			sql = "select board_pwd from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pwCheck = rs.getString(1);
			}
			
			if(pwCheck.equals(pwd)) {
				sql = "update board set board_writer = ?, board_title = ?, board_cont = ?, board_update = sysdate where board_no = ?";
				pstmt = con.prepareStatement(sql);
				// ?????????, ??????, ?????? 
				while(st.hasMoreTokens()) {
					pstmt.setString(1, st.nextToken()); // ?????????
					pstmt.setString(2, st.nextToken()); // ?????????
					pstmt.setString(3, st.nextToken());
				}
				pstmt.setInt(4, no);
				
				ch = pstmt.executeUpdate();
			} else {
				return ch;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return ch;
	} // setUpdate

	public int setUpdate2(BoardDTO dto) {
		int result = 0;
		openConn();
		try {
			sql ="select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPwd().equals(rs.getString("board_pwd"))) {
					sql = "update board set board_title = ?, board_cont = ?, board_update = sysdate where board_no = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getTitle()); // ?????????
					pstmt.setString(2, dto.getCont()); // ??????
					pstmt.setInt(3, dto.getNo());
					
					result = pstmt.executeUpdate();
				} else { // ??????????????? ????????? ???
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
	}
	
	
	// ????????? ??????
	public int setInsertList(String result) {
		StringTokenizer st = new StringTokenizer(result, "???");
		openConn();
		int count = 0;
		int check = 0;
		
		try {
			sql = "select max(board_no) from board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			System.out.println(count);
			
			sql = "insert into board values (?, ?, ?, ?, ?, default, sysdate, sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count+1);
			// ?????????, ??????, ??????, ????????????, 
			while(st.hasMoreTokens()) {
				pstmt.setString(2, st.nextToken());
				pstmt.setString(3, st.nextToken());
				pstmt.setString(4, st.nextToken());
				pstmt.setString(5, st.nextToken());
			}
			
			check = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return check;
	} // setInsertList()
	
	// board ??????????????? ????????? ????????? ???????????? ???????????? ???????????? ?????????
	public int deleteBoard(String []result) {
		int check = 0;
		openConn();
		try {
			sql ="select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(result[0]));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(result[1].equals(rs.getString("board_pwd"))) {
					sql = "delete from board where board_no = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(result[0]));
					check = pstmt.executeUpdate();
				} else {
					check = -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return check;
		
	} //deleteBoard()
	
	// board ??????????????? ????????? ????????? ?????? ??? ????????? ??????????????? ?????????
	public void boardSquence(String []result) {
		openConn();
		
		try {
			sql = "update board set board_no = board-1 where board_no > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(result[0]));
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
	}
	
	public List<BoardDTO> searchBoard(String []arr) {
		//?????????, ?????????
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		openConn();
		
		try {
			if(arr[0].equals("title")) { // ?????? ???????????? ????????? ??????
				sql = "select * from board where board_title like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+arr[1]+"%");
				
			} else if(arr[0].equals("cont")) {
				sql = "select * from board where board_cont like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+arr[1]+"%");
				
			} else if(arr[0].equals("title_cont")) {
				sql = "select * from board where board_title like ? OR board_cont like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+arr[1]+"%");
				pstmt.setString(2, "%"+arr[1]+"%");
		
			} else {
				sql = "select * from board where board_writer like ? order by board_no desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+arr[1]+"%");
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
	} // searchBoard()
}
