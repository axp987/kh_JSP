package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class Udate2
 */
@WebServlet("/update2.do")
public class Udate2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Udate2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_no = Integer.parseInt(request.getParameter("num").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO content =  dao.getContentList(board_no);
		
		request.setAttribute("UpdateContent", content);
		RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
		rd.forward(request, response);
	}


}
