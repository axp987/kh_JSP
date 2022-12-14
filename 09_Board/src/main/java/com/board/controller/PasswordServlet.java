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
		
		String pwd = request.getParameter("pw");  
		BoardDAO dao = BoardDAO.getInstance();
		String check = dao.searchPwd(no);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("if(pwd == null) {");
		out.println("location.href='content.do?num=" + no + "';");
		out.println("} else {");
		out.println("if("+ pwd +" ==" + check + ") {");	
		out.println("location.href='update.do?num2=" + no + "';");
		out.println("} else {");
		out.println("alert('패스워드 확인해주세요.');");
		out.println("location.href='content.do?num=" + no + "';");
		out.println("}");
		out.println("}");
		out.println("</script>");
	}

}
