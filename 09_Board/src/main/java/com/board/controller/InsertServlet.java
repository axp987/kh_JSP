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
		
		BoardDAO dao = BoardDAO.getInstance();
		
		
		String []arr = request.getParameterValues("all");
		String result = "";
		
		for(int i=0; i<arr.length; i++) {
			String all = arr[i];
			result += (all + "⁴");
		}
		
		int check = dao.setInsertList(result);
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('작성 완료')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('작성 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}
