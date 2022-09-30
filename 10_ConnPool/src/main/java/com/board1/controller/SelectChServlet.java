package com.board1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

/**
 * Servlet implementation class SelectChServlet
 */
@WebServlet("/selectCh.do")
public class SelectChServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectChServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("val"));
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> list = dao.searchCol(no);
		
		request.setAttribute("select", list);
		RequestDispatcher rd = request.getRequestDispatcher("view/Select.jsp");
		rd.forward(request, response);
		
	}

}
