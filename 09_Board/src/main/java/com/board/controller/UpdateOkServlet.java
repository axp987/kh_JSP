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
		
		
		int no = Integer.parseInt(request.getParameter("num"));
		String pwd = request.getParameter("pw");
		
		// 현재 패스워드와 입력한 패스워드 비교
		
		
		String []alls = request.getParameterValues("all");
		String result = "";
		for(int i=0; i<alls.length; i++) {
			Object obj = alls[i];
			result += (obj + "^");
		}
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.setUpdate(no, result);
		
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('수정 완료')");
			out.println("location.href='content.do?num=" + no + "';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
