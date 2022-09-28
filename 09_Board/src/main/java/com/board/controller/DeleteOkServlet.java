package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete_ok.do")
public class DeleteOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제 폼 페이지에서 넘어온 글번호에 해당하는 게시글을 DB에서 삭제하는 비지니스 로직
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 글번호, 패스워드
		String[]result = request.getParameterValues("all");
		
		BoardDAO dao = BoardDAO.getInstance();
		int res = dao.deleteBoard(result);
		
		PrintWriter out = response.getWriter();
		if(res > 0) {
			dao.boardSquence(result);
			out.println("<script>"
					+ "alert('삭제 성공');"
					+ "location.href='select.do';"
					+ "</script>");
		} else if(res == -1) {
			out.println("<script>"
					+ "alert('패스워드를 확인해주세요.');"
					+ "history.back();"
					+ "</script>");
		} else {
			out.println("<script>"
					+ "alert('삭제 실패');"
					+ "history.back();"
					+ "</script>");
		}
	}

}
