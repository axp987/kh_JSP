package com.reply.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.reply.model.BbsDTO;

public class BbsDAO {
	private static BbsDAO instance;

	private BbsDAO() {

	}

	public static BbsDAO getInstance() {
		if(instance == null) {
			instance = new BbsDAO();	
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

	// jsp_bbs 테이블의 전체 레코드를 조회하는 메서드
	public List<BbsDTO> getBbsList() {
		List<BbsDTO> list = new ArrayList<BbsDTO>();
		openConn();

		try {
			sql = "select * from jsp_bbs order by board_group desc, board_step asc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BbsDTO dto = new BbsDTO();
				dto.setNo(rs.getInt("board_no"));
				dto.setWriter(rs.getString("board_writer"));
				dto.setTitle(rs.getString("board_title"));
				dto.setCont(rs.getString("board_cont"));
				dto.setPwd(rs.getString("board_pwd"));
				dto.setHit(rs.getInt("board_hit"));
				dto.setDate(rs.getString("board_date"));
				dto.setUpdate(rs.getString("board_update"));
				dto.setGroup(rs.getInt("board_group"));
				dto.setStep(rs.getInt("board_step"));
				dto.setIndent(rs.getInt("board_indent"));

				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	} // getBbsList(0

	// jsp_bbs 테이블에 게시글을 추가하는 메서드
	public int insertBbs(BbsDTO dto) {
		int result = 0, count = 0;

		openConn();
		try {
			sql = "select max(board_no) from jsp_bbs";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}

			sql = "insert into jsp_bbs values (?, ?, ?, ?, ?, default, sysdate, '', ?, 0, 0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getCont());
			pstmt.setString(5, dto.getPwd());
			pstmt.setInt(6, count);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // insertBbs()

	public BbsDTO getBbsContent(int no) {
		BbsDTO dto = new BbsDTO();
		BbsDTO dto2 = null;
		openConn();

		try {
			sql = "select * from jsp_bbs where board_no = ?";
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
				dto.setGroup(rs.getInt("board_group"));
				dto.setStep(rs.getInt("board_step"));
				dto.setIndent(rs.getInt("board_indent"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	} // getContent()

	public void bbsHit(int no) {
		openConn();
		try {
			sql = "update jsp_bbs set board_hit = board_hit+1 where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
	} // bbsHit
	
	public int setBbsUpdate(BbsDTO dto) {
		int check = 0;
		openConn();
		
		try {
			sql= "select board_pwd from jsp_bbs where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(dto.getPwd())) {
					sql = "update jsp_bbs set board_title = ?, board_cont = ?, board_update = sysdate where board_no = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getTitle());
					pstmt.setString(2, dto.getCont());
					pstmt.setInt(3, dto.getNo());
					
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
	}
	
	// jsp_bbs 테이블 게시판 답변 글의 step을 하나 증가시켜 주는 메서드
	public void replyUpdate(BbsDTO dto) {
		openConn();
		
		try {
			sql = "update jsp_bbs set board_step = board_step + 1 where board_group = ? and board_step > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getGroup());
			pstmt.setInt(2, dto.getStep());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
	} //replyUpdate
	
	// jsp_bbs 테이블의 게시글 원글에 답변글을 추가하는 메서드
	public int replyBbs(BbsDTO dto) {
		int result = 0;
		int count = 0;
		
		openConn();
		
		try {
			sql = "select max(board_no) from jsp_bbs";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into jsp_bbs values (?, ?, ?, ?, ?, default, sysdate, '', ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getCont());
			pstmt.setString(5, dto.getPwd());
			
			pstmt.setInt(6, dto.getGroup());
			pstmt.setInt(7, dto.getStep() + 1); // replyUpdate 메소드에서 답변 추가시 1번글이 2번이 되는 매직
			pstmt.setInt(8, dto.getIndent() + 1); // 들여쓰기
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
		
	} // replyBbs()
	
	public int deleteBbs(int no, String pwd) {
		int result = 0;
		openConn();
		
		try {
			sql = "select * from jsp_bbs where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(pwd.equals(rs.getString("board_pwd"))) {
					if(rs.getInt("board_step") == 0) {
						sql = "update jsp_bbs set board_title = ? where board_no = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, "(원글) 삭제된 게시글 입니다.");
						pstmt.setInt(2, no);
						result = pstmt.executeUpdate();
					} else if(rs.getInt("board_step") != 0){
						sql = "delete from jsp_bbs where board_no = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, no);
						pstmt.executeUpdate();
						
						sql = "update jsp_bbs set board_no = board_no - 1 where board_no > ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, no);
						pstmt.executeUpdate();
						
						result = -2;
					}
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
	} // deleteBbs()
}
