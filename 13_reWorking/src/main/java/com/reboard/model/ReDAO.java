package com.reboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.reboard.model.ReDAO;

public class ReDAO {
	private static ReDAO instance;
	
	public Connection con = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	
	String sql = null;
	
	
	private ReDAO() {
		
	}
	
	public static ReDAO getInstance() {
		if(instance == null) {
			instance = new ReDAO();
		}
		return instance;
	}

	public void openConn() {
		try {
			// JNDI 서버 객체
			Context ctx = new InitialContext();
			
			// lookup 메소드를 통해 커넥션 찾기
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			// DataSource 으로 커넥션 하나를 가져옴
			con = ds.getConnection();
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
	
	public List<ReDTO> getSelectList(int page_item, int page) {
		List<ReDTO> list = new ArrayList<ReDTO>();
		openConn();
		try {
			sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b) where rnum >= ? and rnum <= ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, );
			pstmt.setInt(2, );
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReDTO dto = new ReDTO();
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
	} // 게시물 조회하는 메소드
	
	public int getListCount() {
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
	} // 전체 게시물 조회하는 메소드
}
