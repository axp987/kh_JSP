package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;

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

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int no1 = Integer.parseInt(request.getParameter("no"));
		
		String []num = request.getParameterValues("t");
		String result = "";
		for(int i=0; i<num.length; i++) {
			Object Onum = num[i];
			result += (Onum+ " ");
		}
		System.out.println(result);
		
		EmpDAO dao = EmpDAO.getInstance();
		int check = dao.setUpdateList(result, no1);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('수정 성공')");
//			out.println("location.href='select.do'");
			out.println("location.href='content.do?no=" + no1 + "'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
	}
}
