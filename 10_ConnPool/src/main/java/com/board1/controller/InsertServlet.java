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
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		String []arr = request.getParameterValues("all");
		
		//작성자, 분류, 제목, 내용, 패스워드
		BoardDTO dto = new BoardDTO();
		dto.setWriter(arr[0]); // 가 >> 다
		dto.setCol(Integer.parseInt(arr[1]));
		dto.setTitle(arr[2]);
		dto.setCont(arr[3]);
		dto.setPwd(arr[4]);
		
		System.out.println(arr[1]);
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.setInsert(dto);
		
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>"
					+ "alert('등록 완료');"
					+ "location.href='select.do';"
					+ "</script>");
		} else {
			out.println("<script>"
					+ "alert('등록 실패');"
					+ "history.back();"
					+ "</script>");
		}
	}

}
