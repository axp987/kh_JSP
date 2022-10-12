package com.khie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;


@WebServlet("/updateOk")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정폼 페이지에서 넘어온 정보들을 DB에서 부서번호에 해당하는 정보를 수정하는 비지니스 로직
		
		// 한글 인코딩 설정 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계: SQL문 작성 >> 수정 폼 페이지에서 넘어온 정보들을 받기
		int deptno = Integer.parseInt(request.getParameter("deptNo"));
		String name = request.getParameter("reName").trim();
		String loc = request.getParameter("reLoc").trim();
		
		DeptDTO dto = new DeptDTO();
//		dto.setDeptno(Integer.parseInt(request.getParameter("deptno").trim()));
//		dto.setDname(request.getParameter("reName").trim());
//		dto.setLOC(request.getParameter("reLOC").trim());
		
		dto.setDeptno(deptno);
		dto.setDname(name);
		dto.setLOC(loc);
		
		// 3단계: DB에 DTO 객체를 전송
		DeptDAO dao = new DeptDAO();
		int result = dao.updateDept(dto);
		
		PrintWriter out = response.getWriter();
		if(result > 0 ) {
			out.println("<script>");
			out.println("alert('수정 완료')");
			out.println("location.href='select'");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
