package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.DeptDTO;
import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB에 접속하여 EMP 테이블의 전체 리스트를 가져와서
		// view Page로 이동시키는 비지니스 로직
		
		EmpDAO dao = EmpDAO.getInstance();
		List<EmpDTO> EmpList = dao.getEmpList();
		
		request.setAttribute("List", EmpList);
		RequestDispatcher rd = request.getRequestDispatcher("view/Emp_list.jsp");
		rd.forward(request, response);
	}

}
