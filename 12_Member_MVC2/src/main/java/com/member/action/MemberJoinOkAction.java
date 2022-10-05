package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberJoinOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 회원등록 폼 페이지에서 넘어온 데이터들을
		// MEMBER10 테이블에 회원으로 등록하는 비지니스 로직
		
		// 1단계: 회원등록 폼 페이지에서 넘어온 데이터들을 받아 주자.
		String []arr = request.getParameterValues("all");
		
		// 2단계: 회원 등록 폼 페이지에서 넘어온 데이터들을 DTO 객체의 setter() 메소드를 인자로
		// 넘겨 주어서 멤버변수에 초기값으로 할당해 주자 
		MemberDTO dto = new MemberDTO();
		dto.setMemid(arr[0]);
		dto.setMemname(arr[1]);
		dto.setPwd(arr[2]);
		dto.setAge(Integer.parseInt(arr[3]));
		dto.setMileage(Integer.parseInt(arr[4]));
		dto.setJob(arr[5]);
		dto.setAddr(arr[6]);
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		// 3단계: DTO 객체를 DAO 객체의 호출 메소드의 인자로 넘겨 MEMBER10 테이블의 회원으로
		// 저장을 하자
		MemberDAO dao = MemberDAO.getInstance();
		int check = dao.insertMember(dto);
		
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>"
					+ "alert('추가 완료')"
					+ "location.href='select.do'"
					+ "</script>");
		} else {
			out.println("<script>"
					+ "alert('추가 실패')"
					+ "history.back()"
					+ "</script>");	
		}
		return null;
	}

}
