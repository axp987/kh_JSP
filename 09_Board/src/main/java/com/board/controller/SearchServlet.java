package com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search.do")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색 폼 창에서 넘어온 검색어를 가지고 DB에서 검색어에 해당하는 모든 게시물을 가져와서
		// view page로 이동시키는 비지니스 로직
		
		// 한글 처리 작업 진행
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String []find_arr = request.getParameterValues("find");
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> searchList1 = dao.searchBoard(find_arr);
		
		request.setAttribute("Search", searchList1);
		RequestDispatcher rd = request.getRequestDispatcher("searchList.jsp");
		rd.forward(request, response);
	}
}
