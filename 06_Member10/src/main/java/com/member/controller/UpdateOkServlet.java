package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;


@WebServlet("/update_ok.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 수정 폼 페이지에서 넘어온 데이터들을 받아서 DB에 MEMBER10 테이블의 회원번호에 해당하는 회원의
		// 정보를 수정하는 비지니스 로직
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String member_id = request.getParameter("mem_id").trim();
		String member_name = request.getParameter("mem_name").trim();
		String member_pwd = request.getParameter("mem_pwd").trim();
		int member_age = Integer.parseInt(request.getParameter("mem_age").trim());
		int member_mileage = Integer.parseInt(request.getParameter("mem_mileage").trim());
		String member_job = request.getParameter("mem_mileage").trim();
		String member_addr= request.getParameter("mem_addr").trim();
		
		// type="hidden" 으로 넘어온 데이터도 받아주어야 한다.
		int member_num = Integer.parseInt(request.getParameter("num").trim());
		
		// 2단계 : DB에 전송할 DTO 객체에 해당 데이터들을 저장
		MemberDTO dto = new MemberDTO();
		dto.setNum(member_num);
		dto.setMemid(member_id);
		dto.setPwd(member_pwd);
		dto.setMemname(member_name);
		dto.setAge(member_age);
		dto.setMileage(member_mileage);
		dto.setJob(member_job);
		dto.setAddr(member_addr);
		
		MemberDAO dao = new MemberDAO();
		int check = dao.updateMember(dto);
		
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('수정 완료')");
			out.println("location.href='content.do?num="+ dto.getNum() + "'");
			out.println("</script>");
		} else if(check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인 부탁드려요')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원 정보 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}
