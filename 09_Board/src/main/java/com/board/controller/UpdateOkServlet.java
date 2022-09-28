package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class UpdateOkServlet
 */
@WebServlet("/update_ok.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 수정 폼페이지에서 넘어온 정보들을 DB에 저장시키는 비지니스 로직
		// 한글 처리 작업 진행
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		int no = Integer.parseInt(request.getParameter("num"));
		String pwd = request.getParameter("pw");
		
		String []alls = request.getParameterValues("all");
		String result = "";
		for(int i=0; i<alls.length; i++) {
			String obj = alls[i];
			result += (obj + "⁴");
		}
		// 작성자, 제목, 내용
		BoardDTO dto = new BoardDTO();
		dto.setNo(no);
		dto.setPwd(pwd);
		dto.setWriter(alls[0]);
		dto.setTitle(alls[1]);
		dto.setCont(alls[2]);
		
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.setUpdate2(dto);
		
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('수정 완료')");
			out.println("location.href='content.do?num=" + no + "';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('패스워드를 확인해주세요')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
