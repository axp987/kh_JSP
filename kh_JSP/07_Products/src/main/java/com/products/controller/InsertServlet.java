package com.products.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.CategoryDTO;
import com.products.model.ProductDAO;

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

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB에서 category 테이블의 전체 리스트를 조회하여
		// 카테고리 전체 리스트를 제품 등록 폼페이지로 이동시키는 비지니스 로직
		ProductDAO dao = ProductDAO.getInstance();
		
		System.out.println("카테고리 목록 dao >>> " + dao);
		
		List<CategoryDTO> category = dao.getCategoryList();
		
		
		request.setAttribute("cList", category);
		RequestDispatcher rd = request.getRequestDispatcher("view/product_insert.jsp");
		rd.forward(request, response);
	}

}
