package com.member.model;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	Connection con = null;
	
	// DB에 SQL문을 전송하는 객체
	PreparedStatement pstmt = null;
	
	// SQL문을 실행한 후 결과 값을 가지고 있는 객체
	ResultSet rs = null;
	
	// 쿼리문을 저장할 변수
	String sql = null;
	
	public MemberDAO() {
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
	} // 기본 생성자
	
	// MEMBER10 테이블에서 회원 전체 목록을 조회하는 메소드
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		// 1. DB에 전송할 SQL문 작성
		sql = "select * from member10 order by num desc";
		
		try {
			// 2. SQL문을 데이터베이스 전송 객체에 저장
			pstmt = con.prepareStatement(sql);
			// 3. 데이터베이스에 SQL문 전송 및 실행
			rs = pstmt.executeQuery();
			
			// 4. SQL문 실행 결과 값을 DTO 객체에 저장하여 데이터가 없을 때 까지 반복하여
			// list에 추가
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setDate(rs.getString("regdate"));
				
				list.add(dto);
			}
			
			pstmt.close(); rs.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	// MEMBER10 테이블에 회원을 등록하는 메소드
	public int insertMember(MemberDTO dto) {
		int result = 0;
		int count = 0;
		
		try {
			sql = "select max(num) from MEMBER10";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
				
			}
			
			sql = "insert into member10 values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count+1);
			pstmt.setString(2, dto.getMemid());
			pstmt.setString(3, dto.getMemname());
			pstmt.setString(4, dto.getPwd());
			pstmt.setInt(5, dto.getAge());
			pstmt.setInt(6, dto.getMileage());
			pstmt.setString(7, dto.getJob());
			pstmt.setString(8, dto.getAddr());
			
			result = pstmt.executeUpdate();
			
			//DB와 연결되어 있는 자원 종료 시키기
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	} //insertMemeber()
	
	// 회원번호에 해당하는 회원의 정보를 조회하는 메소드
	public MemberDTO getContentMember(int no) {
		MemberDTO dto = null;
		
		try {
			sql = "select * from MEMBER10 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setDate(rs.getString("regdate"));
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e) {
			
		}
		return dto;
	} //getContentMember
	
	// MEMBER10 테이블에 회원번호에 해당하는 회원의 정보를 수정하는 메소드
	public int updateMember(MemberDTO dto) {
		int result = 0;
		
		
		try {
			sql = "select * from member10 where num = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPwd().equals(rs.getString("pwd"))) {
					
					// 현재 입력된 비밀번호와 DB에 있는 비밀번호를 비교
					sql = "update MEMBER10 set age = ?, mileage = ?, job = ?, addr = ? where num = ?";
					pstmt = con.prepareStatement(sql);

					pstmt.setInt(1, dto.getAge());
					pstmt.setInt(2, dto.getMileage());
					pstmt.setString(3, dto.getJob());
					pstmt.setString(4, dto.getAddr());
					pstmt.setInt(5, dto.getNum());
					
					result = pstmt.executeUpdate();
				} else { // 비밀번호가 틀린 경우
					result = -1;
				}
			}
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	} // updateMember
	
	// 회원번호에 해당하는 회원을 DB에서 삭제하는 메소드
	public int deleteMember(int member_no) {
		int result = 0;
		
		try {
			sql = "delete from MEMBER10 WHERE num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); //con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // delete
	
	// 회원 번호 순번 다시 작업해 주는 메소드
	public void updateNum(int member_no) {
		try {
			sql = "update member10 set num = num - 1 where num > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
// update member10 set num = num -1 where num > x
