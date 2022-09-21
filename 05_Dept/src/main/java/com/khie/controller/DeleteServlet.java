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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글로 변환
//		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 삭제 버튼을 눌면 get방식으로 넘어온 deptno를 통해 DB 내역 삭제
		
		// 1단계: 삭제 버튼을 눌렀을 때 get 방식으로 넘어온 부서번호를 받아야 한다.
		int dno = Integer.parseInt(request.getParameter("deptno"));
		
		// 2단계: 삭제할 부서 번호를 DB에 넘겨주어야 한다.
//		DeptDTO dto = new DeptDTO();
//		dto.setDeptno(deptNo);
		
		DeptDAO dao = new DeptDAO();
		int result = dao.deleteDept(dno);
		
		PrintWriter out = response.getWriter();
		if(result > 0 ) {
			out.println("<script>");
			out.println("alert('삭제 완료')");
			out.println("location.href='select'");
			//out.println("location.reload()");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
