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
 * Servlet implementation class PasswordServlet
 */
@WebServlet("/password.do")
public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO dao = BoardDAO.getInstance();
		String check = dao.searchPwd(no);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("let pwd = prompt('패스워드를 입력해주세요.');");
		out.println("if(pwd == null) {");
		out.println("history.back();");
		out.println("} else {");
		//out.println("if(pwd ==" + check + ") {");
		//out.println("location.href='" + request.getContextPath() + "/insert.do'");
		out.println("}");
		out.println("</script>");
	}

}
