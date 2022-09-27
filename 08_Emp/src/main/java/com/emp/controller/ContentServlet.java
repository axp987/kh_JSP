package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;

/**
 * Servlet implementation class ContentServlet
 */
@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 사원등록 폼으로 넘어가기 전에 담당업무 리스트와
		// 관리자 리스트, 부서번호 리스트를 DB에서 조회하여
		// 사원 등록 폼 페이지로 넘겨주는 비지니스 로직
		
		EmpDAO dao = EmpDAO.getInstance();
		
		
		//int num = Integer.parseInt(request.getParameter("no"));
		
		//EmpDTO dto = dao.getContentList(num);
		
	
		RequestDispatcher rd = request.getRequestDispatcher("view/Emp_content.jsp");
		rd.forward(request, response);
		
		
		
		
	}

}
