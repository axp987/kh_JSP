package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemeberModifyOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정폼 페이지에서 넘어온 데이터들을 MEMBER10 테이블의 회원번호에 해당하는 회원의 정보를 수정하는 비지니스 로직
		String []arr = request.getParameterValues("all");
		// 넘버 입력비밀번호 아이디 이름 비밀번호 나이 마일리지 직업 주소
		MemberDTO dto = new MemberDTO();
		PrintWriter out = response.getWriter();
		System.out.print(arr[1] + " " + arr[4]);
		if(arr[1].equals(arr[4])) {
			dto.setNum(Integer.parseInt(arr[0]));
			dto.setMemid(arr[2]);
			dto.setMemname(arr[3]);
			dto.setPwd(arr[4]);
			dto.setAge(Integer.parseInt(arr[5]));
			dto.setMileage(Integer.parseInt(arr[6]));
			dto.setJob(arr[7]);
			dto.setAddr(arr[8]);
			
			int check = updateMember(dto);
			if(check > 0) {
				out.println("<script>"
						+ "alert('수정 성공.');"
						+ "location.href='content.do?num=" + dto.getNum()+ "';"
						+ "</script>");
			} else {
				out.println("<script>"
						+ "alert('수정 실패.');"
						+ "history.back();"
						+ "</script>");
			}
		} else {
			out.println("<script>"
					+ "alert('패스워드가 다릅니다.');"
					+ "history.back();"
					+ "</script>");
		}
		
		return null;
	}

	public int updateMember(MemberDTO dto) {

		String sql = null;
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.openConn();
		int result = 0;
		
		try {
			sql = "update member10 set memname = ?, age = ?, mileage = ?, job = ?, addr = ? where num = ?";
			dao.pstmt = dao.con.prepareStatement(sql);
			dao.pstmt.setString(1, dto.getMemname());
			
			dao.pstmt = dao.con.prepareStatement(sql);
			dao.pstmt.setString(1, dto.getMemname());
			dao.pstmt.setInt(2, dto.getAge());
			dao.pstmt.setInt(3, dto.getMileage());
			dao.pstmt.setString(4, dto.getJob());
			dao.pstmt.setString(5, dto.getAddr());
			dao.pstmt.setInt(6, dto.getNum());
			
			result = dao.pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dao.closeConn(dao.rs, dao.pstmt, dao.con);
		}
		return result;
	}
}
