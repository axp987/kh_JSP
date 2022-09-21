package com.khie.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * DAO(Data Access Object)
 * 	- 데이터 접근 객체. ==> DB에 접속(연동)하는 객체
 *  - DAO란 데이터베이스에 접속해서 데이터 추가, 수정, 삭제, 조회 등의 작업을 하는 클래스
 *  - 일반적으로 JSP 또는 Servlet에서 위의 작업들을 같이 사용할 수 있지만, 유지보수, 코드의 모듈화 등을
 *  위해서 DAO 클래스를 따로 만들어서 사용을 함
 *  
 */
public class DeptDAO {
	// C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib 라이브러리를 WEB-INF에 넣기
	// DB와 연동하는 객체
	Connection con = null;
	
	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;
	
	// SQL문을 실행한 후 결과 값을 가지고 있는 객체
	ResultSet rs = null;
	
	// 쿼리문을 저장할 변수
	String sql = null;
	
	int result = 0;
	
	
	public DeptDAO() { // 기본 생성자
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
	}//기본 생성자
	
	// selectList() 메소드를 만들기
	// DEPT 테이블에서 부서 목록 전체 리스트를 조회 메소드
	public List<DeptDTO> selectList() {
		List<DeptDTO> list = new ArrayList<DeptDTO>(); // 다형성
		
		
		try {
			// 3단계: 데이터베이스에 전송할 SQL문을 작성
			sql = "select * from dept order by deptno";
			
			// 4단계: sql문을 데이터베이스에 전송객체에 저장
			pstmt = con.prepareStatement(sql);
			
			// 5단계: sql문을 데이터베이스에 전송 및 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DeptDTO dto = new DeptDTO(); // DB에서 IN, OUT을 담당
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLOC(rs.getString("LOC"));
				
				list.add(dto); // dto 객체의 값을 저장
			}
			
			// 6단계: 연결되어 있는 자원 종료하기
			rs.close();
			pstmt.close();
			con.close();
			
			return list; // dto 객체의 값을 전송
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; // dto 객체의 값을 전송
	} // selectList
	
	
	public int insertDept(DeptDTO dto) {
		// 3단계: 데이터베이스에 전송할 SQL문 작성
		sql = "insert into dept values(?, ?, ?)";
		
		// 4단계: SQL문을 데이터베이스 전송 객체에 저장
		try {
			pstmt = con.prepareStatement(sql);
			
			// insert문의 ? 을 채워주자
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getDname());
			pstmt.setString(3, dto.getLOC());
			
			//
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	} // insertDept()
	
	public int updateDept(DeptDTO dto) {
		// 3단계: 데이터베이스에 SQL 작성
		sql = "update dept set dname = ?, loc = ? where deptno = ?";
		
		try {
			// 4단계: SQL문을 데이터베이스 전송 객체에 저장
			pstmt = con.prepareStatement(sql);
			
			// ?(플레이스 홀더)에 저장될 정보 저장
			pstmt.setString(1, dto.getDname());
			pstmt.setString(2, dto.getLOC());
			pstmt.setInt(3, dto.getDeptno());
			
			// 5단계: SQL문을 데이터베이스에 전송 및 실행
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 매개변수로 넘어온 부서번호를 삭제하는 메소드
	public int deleteDept(int dno) {
		int result = 0;
		
		// 3단계: 쿼리문 작성
		sql ="delete from dept where deptno = ?";
		
		try {
			// 4단계: sql문을 데이터베이스에 전송 객체에 저장
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dno);
			
			// 5단계: sql문을 데이터베이스에 전송 및 실행
			result = pstmt.executeUpdate();
			
			// 6단계: 사용되었던 자원 종료시키기
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // deleteDept()
}
