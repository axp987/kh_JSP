package com.board1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// arr = {no, writer, select, title, cont, password }
		String []arr = request.getParameterValues("all");
		
		BoardDTO dto = new BoardDTO();
		dto.setNo(Integer.parseInt(arr[0]));
		dto.setWriter(arr[1]);
		dto.setCol(Integer.parseInt(arr[2]));
		dto.setTitle(arr[3]);
		dto.setCont(arr[4]);
		dto.setPwd(arr[5]);
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.setUpdate(dto);
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>"
					+ "alert('수정 완료.');"
					+ "location.href='content.do?num=" + dto.getNo() + "';"
					+ "</script>");
		} else {
			out.println("<script>"
					+ "alert('수정 실패.');"
					+ "history.back();"
					+ "</script>");
		}
	}

}
