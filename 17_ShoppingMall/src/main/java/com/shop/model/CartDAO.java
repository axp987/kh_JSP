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

public class CartDAO {
private static CartDAO instance;
	
	private CartDAO() {
		
	}
	
	public static CartDAO getInstance() {
		if(instance == null) {
			instance = new CartDAO();
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
	// shop_cart 테이블에 상품 정보를 저장하는 메서드
	public int insertCart(CartDTO dto) {
		int result = 0, count = 0;
		openConn();
		
		try {
			sql = "select max(cart_num) from shop_cart";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into shop_cart values(?, ?, ?, ?, ?, ? ,?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, dto.getPnum());
			pstmt.setString(3, dto.getId());
			pstmt.setString(4, dto.getName());
			pstmt.setInt(5, dto.getQty());
			pstmt.setInt(6, dto.getPrice());
			pstmt.setString(7, dto.getSpec());
			pstmt.setString(8, dto.getImage());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} //insertCart
	
	public List<CartDTO> getCartList(String id) {
		List<CartDTO> list = new ArrayList<CartDTO>();
		
		openConn();
		
		try {
			sql = "select * from shop_cart where cart_userId = ? order by cart_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setNum(rs.getInt("cart_num"));
				dto.setPnum(rs.getInt("cart_pnum"));
				dto.setId(rs.getString("cart_userid"));
				dto.setName(rs.getString("cart_pname"));
				dto.setQty(rs.getInt("cart_pqty"));
				dto.setPrice(rs.getInt("cart_price"));
				dto.setSpec(rs.getString("cart_pspec"));
				dto.setImage(rs.getString("cart_pimage"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	} //getCartList
}
