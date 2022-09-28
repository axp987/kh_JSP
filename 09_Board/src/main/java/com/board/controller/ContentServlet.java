package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.Border;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class ContentServlet
 */
@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 넘어온 글번호에 해당하는 게시글을
		// DB에서 조회하여 게시글 상세정보를 view page로
		// 이동시키는 비지니스 로직
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.getCount(no);
		BoardDTO conList = dao.getContentList(no);
		
		PrintWriter out = response.getWriter();
		
		request.setAttribute("content", conList);
		RequestDispatcher rd = request.getRequestDispatcher("content.jsp");
		rd.forward(request, response);
	}

}
