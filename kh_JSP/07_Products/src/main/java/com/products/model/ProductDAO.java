package com.products.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO 객체를 싱글턴 방식으로 만들어서 사용하기

public class ProductDAO {
	Connection con = null;
	
	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;
	
	// SQL문을 실행한 후 결과 값을 가지고 있는 객체
	ResultSet rs = null;
	
	// 쿼리문을 저장할 변수
	String sql = null;
	
	// 2단계: ProductDAO 객체를 정적(static) 멤버로 선언 해 주어야 한다.
	private static ProductDAO instance;
		
		
	// ProductDAO 객체를 싱글턴 방식으로 만들기
	// 1단계: 싱글턴 방식으로 객체를 생성하기 위해 우선적으로 기본생성자의 접근제어자를 public이 아닌
	// private로 생성한다. 즉 외부에서 직접적으로 기본생성자를 호출을 못하게 한다.
	private  ProductDAO() { // 기본생성자 ( public >> private ) 
		
	}
	
	// 3단계: 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는 메소드를 만들어서
	// 		해당 getInstance() 라는 메소드를 외부에서 접근할 수 있도록 해 주면 됨.
	public static ProductDAO getInstance() {
		if(instance == null) {
			instance = new ProductDAO();
		}
		return instance;
	} // 
	
	// DB를 연동하는 작업을 진행하는 메소드
	public void openConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "web";
		String password = "1234";
		
		try {
			// 1단계: 오라클 드라이버를 메모리로 로딩
			Class.forName(driver);
			
			// 2단계: 오라클 데이터베이스와 연결 작업
			con = DriverManager.getConnection(url, user,  password);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	} //openConn
	
	// DB에 연결된 자원 종료하는 메소드
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
	
	public List<ProductDTO> getProductList() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		// 오라클 드라이버 로딩 및 DB 연결작업 진행
		openConn();
		
		try {
			sql = "select * from PRODUCTS ORDER BY PNUM DESC";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setPnum(rs.getInt("PNUM"));
				dto.setCategory_fk(rs.getString("CATEGORY_FK"));
				dto.setProductName(rs.getString("PRODUCTS_NAME"));
				dto.setEp_code_fk(rs.getString("EP_CODE_FK"));
				dto.setInput_price(rs.getInt("INPUT_PRICE"));
				dto.setOutput_price(rs.getInt("OUTPUT_PRICE"));
				dto.setTrans_cost(rs.getInt("TRANS_COST"));
				dto.setMileage(rs.getInt("MILEAGE"));
				dto.setCompany(rs.getString("COMPANY"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return list;
		
	} // getProductList()
	
	// category 테이블의 전체 리스트 조회
	public List<CategoryDTO> getCategoryList() {
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();

		openConn();
		try {
			sql = "select * from category order by CATEGORY_CODE";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				
				dto.setCnum(rs.getInt("CNUM"));
				dto.setCategory_code(rs.getString("CATEGORY_CODE"));
				dto.setCategory_name(rs.getString("CATEGORY_NAME"));
				
				list.add(dto);
			}
			closeConn(rs, pstmt, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} // getCategoryList()
	
	// products 테이블에 제품을 등록하는 메소드
	public int insertProduct(ProductDTO dto) {
		int count = 0;
		int result = 0;
		openConn();
		try {			
			sql = "select max(pnum) from products";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) +1; 
			}
			
			sql = "insert into products values (?, ?"
					+ ", ?, ?, ?, "
					+ "?, ?, ?, ?)"; //총 9개
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getCategory_fk());
			pstmt.setString(3, dto.getProductName());
			pstmt.setString(4, dto.getEp_code_fk());
			pstmt.setInt(5, dto.getInput_price());
			pstmt.setInt(6, dto.getOutput_price());
			pstmt.setInt(7, dto.getTrans_cost());
			pstmt.setInt(8, dto.getMileage());
			pstmt.setString(9, dto.getCompany());
			System.out.println(dto.getEp_code_fk());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // insertProduct
	
	public int deleteProduct() {
		return 0;
	}
	
	// 제품번호에 해당하는 제품을 조회하는 메소드
	public ProductDTO getContentList(int num) {
		ProductDTO dto = new ProductDTO();
		openConn();
		try {
			sql = "select * from products where pnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setPnum(rs.getInt("pnum"));
				dto.setCategory_fk(rs.getString("CATEGORY_FK"));
				dto.setProductName(rs.getString("PRODUCTS_NAME"));
				dto.setEp_code_fk(rs.getString("EP_CODE_FK"));
				dto.setInput_price(rs.getInt("INPUT_PRICE"));
				dto.setOutput_price(rs.getInt("OUTPUT_PRICE"));
				dto.setTrans_cost(rs.getInt("TRANS_COST"));
				dto.setMileage(rs.getInt("MILEAGE"));
				dto.setCompany(rs.getString("COMPANY"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}// getContentList();
	
	public int deleteProduct(int num) {
		int count = 0;
		int result = 0;
		
		openConn();
		
		try {
			sql = "delete from products where pnum = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // deleteProduct
	// 매개변수로 받은 제품번호에 해당하는 제품을 DB에서 삭제하는 메소드
	public void deleteUpdate(int num) {
		int count = 0;
		
		try {
			sql = "update products set pnum = pnum - 1 where pnum > ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
		
	} //delteUpdate
	
	public ProductDTO getContentProduct(int no) {
		ProductDTO dto = new ProductDTO();
		openConn();
		try {
			sql = "select * from products where pnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setPnum(rs.getInt("pnum"));
				dto.setCategory_fk(rs.getString("CATEGORY_FK"));
				dto.setProductName(rs.getString("PRODUCTS_NAME"));
				dto.setEp_code_fk(rs.getString("EP_CODE_FK"));
				dto.setInput_price(rs.getInt("INPUT_PRICE"));
				dto.setOutput_price(rs.getInt("OUTPUT_PRICE"));
				dto.setTrans_cost(rs.getInt("TRANS_COST"));
				dto.setMileage(rs.getInt("MILEAGE"));
				dto.setCompany(rs.getString("COMPANY"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}
	
	public int getUpdateProduct(ProductDTO dto) {
		int result = 0;
		
		try {
			openConn();
			sql = "update products set INPUT_PRICE = ?, OUTPUT_PRICE = ?, TRANS_COST = ?, MILEAGE = ? where pnum = ?"; 
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getInput_price());
			pstmt.setInt(2, dto.getOutput_price());
			pstmt.setInt(3, dto.getTrans_cost());
			pstmt.setInt(4, dto.getMileage());
			pstmt.setInt(5, dto.getPnum());
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(pstmt, con);
		}
		
		return result;
	}
}
