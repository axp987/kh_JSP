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

@WebServlet("/insertOk")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// insert.jsp 페이지에서 넘어온 데이터들을 DB에 저장시키는 비지니스 로직
		
		// 한글 인코딩 처리 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계: 부서등록 폼 페이지에서 넘어온 데이터들을 받아주어야 한다.
		int deptno = Integer.parseInt(request.getParameter("deptNo").trim());
		String deptName = request.getParameter("deptName").trim();
		String deptLoc = request.getParameter("deptLoc").trim();
		
		// 2단계: DTO 객체를 이용하여 DB에 데이터를 전송해야 한다.
		DeptDTO dto = new DeptDTO(); // DB
		
		dto.setDeptno(deptno);
		dto.setDname(deptName);
		dto.setLOC(deptLoc);
		// UTF-8로 하면 한글자당 3byte로 인식
		System.out.println(dto.getDname());
		// 3단계: 데이터베이스에 DTO(getter, setter) 객체를 전송
		DeptDAO dao = new DeptDAO();
		int res = dao.insertDept(dto);
		PrintWriter out = response.getWriter();
		if(res > 0) { // 부서 추가 완료 상태
			out.println("<script>");
			out.println("alert('부서 추가 성공')");
			out.println("location.href='select'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('부서 추가 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
