package com.board1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//arr = {No, select, cont, pwd }
		String []arr = request.getParameterValues("all");
		BoardDTO dto = new BoardDTO();
		dto.setNo(Integer.parseInt(arr[0]));
		dto.setPwd(arr[3]);
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.getUpdateCheck(dto);
		PrintWriter out = response.getWriter();
		if(check == 1) {
			BoardDTO updatelist = dao.getUpdateOkList(dto);
			request.setAttribute("update", updatelist);
			RequestDispatcher rd = request.getRequestDispatcher("view/update.jsp");
			rd.forward(request, response);
		} else {
			out.println("<script>"
					+ "alert('패스워드를 확인해주세요.');"
					+ "history.back();"
					+ "</script>");
		}
		
		
	}

}
