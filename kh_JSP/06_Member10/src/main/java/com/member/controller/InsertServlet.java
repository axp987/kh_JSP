package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// member_join.jsp 페이지에서 넘어온 회원등록
		// 관련 정보들을 DB 상의 MEMBER10 테이블에 저장하는 비지니스 로직
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계: 회원등록 폼 페이지에서 넘어온 정보들을 받아주어야 한다.
		String member_id = request.getParameter("mem_id").trim();
		String member_name = request.getParameter("mem_name").trim();
		String member_pwd = request.getParameter("mem_pwd").trim();
		int member_age = Integer.parseInt(request.getParameter("mem_age").trim());
		int member_mileage = Integer.parseInt(request.getParameter("mem_mileage").trim());
		String member_job = request.getParameter("mem_mileage").trim();
		String member_addr= request.getParameter("mem_addr").trim();
		
		// 2단계: 데이터베이스에 전송할 DTO 객체에 데이터들을 저장해주기
		MemberDTO dto = new MemberDTO();
		dto.setMemid(member_id);
		dto.setMemname(member_name);
		dto.setPwd(member_pwd);
		dto.setAge(member_age);
		dto.setMileage(member_mileage);
		dto.setJob(member_job);
		dto.setAddr(member_addr);
		
		// 3단계: DB에 전송할 dto 객체를 메소드 호출 시 해당 메소드의 인자로 정보를 넘겨 주자
		// dto 객체를 그냥 넘겨주자
		MemberDAO dao = new MemberDAO();
		int result = dao.insertMember(dto);
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('회원등록성공');");
			out.println("location.href='select.do';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원등록실패');");
			out.println("history.back();");
			out.println("</script>");
		}
	}

}
