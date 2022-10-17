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

public class ProductDAO {
	private static ProductDAO instance;
	
	private ProductDAO() {
		
	}
	
	public static ProductDAO getInstnace() {
		if(instance == null) {
			instance = new ProductDAO();
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
	
	public int insertProduct(ProductDTO dto) {
		int count=0, result=0;
		openConn();
		
		try {
			sql = "select count(pnum) from shop_products";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into shop_products values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getCode());
			pstmt.setString(4, dto.getCompany());
			pstmt.setString(5, dto.getImage());
			pstmt.setInt(6, dto.getQty());
			pstmt.setInt(7, dto.getPrice());
			pstmt.setString(8, dto.getSpec());
			pstmt.setString(9, dto.getCont());
			pstmt.setInt(10, dto.getPoint());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	public List<ProductDTO> getProductList() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		openConn();
		
		try {
			sql = "select * from shop_products";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setNo(rs.getInt("pnum"));
				dto.setName(rs.getString("pname"));
				dto.setCode(rs.getString("pcategory_fk"));
				dto.setCompany(rs.getString("pcompany"));
				dto.setImage(rs.getString("pimage"));
				dto.setQty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setSpec(rs.getString("pspec"));
				dto.setCont(rs.getString("pcontents"));
				dto.setPoint(rs.getInt("point"));
				dto.setDate(rs.getString("pinputdate"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	} //getProductList
	
	public ProductDTO productContent(int no) {
		ProductDTO dto = null;
		openConn();
		
		try {
			sql = "select * from shop_products where pnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new ProductDTO();
				dto.setNo(rs.getInt("pnum"));
				dto.setName(rs.getString("pname"));
				dto.setCode(rs.getString("pcategory_fk"));
				dto.setCompany(rs.getString("pcompany"));
				dto.setImage(rs.getString("pimage"));
				dto.setQty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setSpec(rs.getString("pspec"));
				dto.setCont(rs.getString("pcontents"));
				dto.setPoint(rs.getInt("point"));
				dto.setDate(rs.getString("pinputdate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	} // productContent
	
	public int updateProduct(ProductDTO dto) {
		int result = 0;
		openConn();
		
		try {
			sql = "update shop_product set (pimage = ?, pqty = ?, price = ?, pspec = ?, pcontents = ?, point = ?, where pnum = ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getImage());
			pstmt.setInt(2, dto.getQty());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setString(4, dto.getSpec());
			pstmt.setString(5, dto.getCont());
			pstmt.setInt(6, dto.getPoint());
			pstmt.setInt(7, dto.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // updateProduct
	
	// 제품번호에 해당하는 제품을 DB에서 삭제하는 메서드
	public int deleteProduct(int no) {
		int result = 0;
		openConn();
		
		try {
			sql = "delete from shop_products where pnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				sql = "update shop_products set pnum = pnum - 1 where pnum > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // deleteProduct
	
	// 유저에서 오는 카테고리 코드에 해당하는 제품의 전체 리스트를 조회하는 메서드
	public List<ProductDTO> getProductList(String code) {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		openConn();
		
		try {
			sql = "select * from shop_products where pcategory_fk = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setNo(rs.getInt("pnum"));
				dto.setName(rs.getString("pname"));
				dto.setCode(rs.getString("pcategory_fk"));
				dto.setCompany(rs.getString("pcompany"));
				dto.setImage(rs.getString("pimage"));
				dto.setQty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setSpec(rs.getString("pspec"));
				dto.setCont(rs.getString("pcontents"));
				dto.setPoint(rs.getInt("point"));
				dto.setDate(rs.getString("pinputdate"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	} //getProductList
}
