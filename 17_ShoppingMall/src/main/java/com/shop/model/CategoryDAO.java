package com.shop.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CategoryDAO {
private static CategoryDAO instance;
	
	private CategoryDAO() {
		
	}
	
	public static CategoryDAO getInstance() {
		if(instance == null) {
			instance = new CategoryDAO();
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
	
	public int insertCategory(String code, String name) {
		int count = 0, result = 0;
		openConn();
		try {
			sql = "select count(category_num) from shop_category";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into shop_category values (?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, code);
			pstmt.setString(3, name);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} //insertCategory
	
	public List<CategoryDTO> categoryList() {
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		openConn();
		
		try {
			sql = "select * from shop_category";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setNo(rs.getInt("category_num"));
				dto.setCode(rs.getString("category_code"));
				dto.setName(rs.getString("category_name"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	} //categoryList
	
	// 카테고리 테이블에 있는 전체 리스트를 조회하는 메서드
	public List<CategoryDTO> getCategoryList() {
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		openConn();
		
		try {
			sql = "select * from shop_category";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setNo(rs.getInt("category_num"));
				dto.setCode(rs.getString("category_code"));
				dto.setName(rs.getString("category_name"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	} //getCategoryList
	
	// 카테고리 번호에 해당하는 카테고리를 DB에서 삭제하는 메서드
	public int deleteCategory(int no) {
		int result = 0;
		openConn();
		
		try {
			sql = "delete from shop_category where category_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
			sql = "update shop_category set category_num = category_num -1 where category_num > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
}
