package com.products.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.ProductDAO;
import com.products.model.ProductDTO;

/**
 * Servlet implementation class insertOkServlet
 */
@WebServlet("/insert_Ok.do")
public class insertOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 제품 등록 폼 페이지에서 넘어온 데이터들을 
		// DB의 products 테이블에 저장하는 비지니스 로직.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String product_category = request.getParameter("product_category").trim();
		String product_name = request.getParameter("product_name").trim();
		int product_input = Integer.parseInt(request.getParameter("product_input").trim());
		int product_output = Integer.parseInt(request.getParameter("product_output").trim());
		int product_transpost = Integer.parseInt(request.getParameter("product_transpost").trim());
		int product_mileage = Integer.parseInt(request.getParameter("product_mileage").trim());
		String product_code = request.getParameter("product_code").trim();
		String product_company = request.getParameter("product_company").trim();
		
		ProductDTO dto = new ProductDTO();
		dto.setCategory_fk(product_category);
		dto.setProductName(product_name);
		dto.setInput_price(product_input);
		dto.setOutput_price(product_output);
		dto.setTrans_cost(product_transpost);
		dto.setMileage(product_mileage);
		dto.setEp_code_fk(product_code);
		dto.setCompany(product_company);
		
		ProductDAO dao = ProductDAO.getInstance();
		
		System.out.println("제품 등록 시 dao >>> " + dao);
		
		int result_insert = dao.insertProduct(dto);
		
		PrintWriter out = response.getWriter();
		if(result_insert > 0) {
			out.println("<script>");
			out.println("alert('추가 완료')");
			out.println("location.href='select.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('추가 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
