package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.dbCon;
import com.member.model.vari;

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

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("mem_id");
		String name = request.getParameter("mem_name");
		String pwd = request.getParameter("mem_pwd");
		int age = Integer.parseInt(request.getParameter("mem_age"));
		int mil = Integer.parseInt(request.getParameter("mem_mileage"));
		String job = request.getParameter("mem_job");
		String addr = request.getParameter("mem_addr");
		String date = request.getParameter("mem_date");
	
		
		vari va = new vari();
		va.setId(id);
		va.setName(name);
		va.setPwd(pwd);
		va.setAge(age);
		va.setMileage(mil);
		va.setJob(job);
		va.setAddr(addr);
		va.setdate(date);
		
		
		dbCon db = new dbCon();
		int result = db.insertDB(va);
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('회원등록성공');");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원등록실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
	}

}
