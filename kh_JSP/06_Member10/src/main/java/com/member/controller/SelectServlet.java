package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 >> MEMBER10 테이블의 전체 회원 목록을 화면에 보여주달라고 클라이언트가 요청
		// 응답 >> DB에서 MEMBER10 테이블의 회원 전체 목록을 조회하여 view page로 넘겨준다.
		
		// 1단계: 오라클 데이터베이스와 연결하기
		MemberDAO dao = new MemberDAO(); // DB
		
		List<MemberDTO> memberlist = dao.getMemberList(); // CLASS
		
		// 3단계: DB에서 가져온 정보를 view page로 이동시켜야 함.
		request.setAttribute("member", memberlist);
		
		// 4단계: DB의 정보를 member_list.jsp로 이동시키자
		RequestDispatcher rd = request.getRequestDispatcher("view/member_list.jsp");
		rd.forward(request, response);	
	}

}
