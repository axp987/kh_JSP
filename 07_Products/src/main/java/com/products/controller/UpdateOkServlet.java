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
		// 수정폼 페이지에서 넘어온 정보들을 가지고
		// DB에 반영하여 수정하는 비지니스 로직
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ProductDTO dto = new ProductDTO();
		
		int num = Integer.parseInt(request.getParameter("product_num"));
		String category = request.getParameter("product_category");
		String name = request.getParameter("product_name");
		String code = request.getParameter("product_code");
		int input = Integer.parseInt(request.getParameter("product_input"));
		int output = Integer.parseInt(request.getParameter("product_output"));
		int trans = Integer.parseInt(request.getParameter("product_trans"));
		int mileage = Integer.parseInt(request.getParameter("product_mileage"));
		String company = request.getParameter("product_company");
		
		dto.setPnum(num);
		dto.setCategory_fk(category);
		dto.setProductName(name);
		dto.setEp_code_fk(code);
		dto.setInput_price(input);
		dto.setOutput_price(output);
		dto.setTrans_cost(trans);
		dto.setMileage(mileage);
		dto.setCompany(company);
		
		ProductDAO dao = ProductDAO.getInstance();
		
		int check = dao.getUpdateProduct(dto);
		
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>");
			out.println("alert('수정 성공')");
			out.println("location.href='content.do?num=" + dto.getPnum() + "'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
	}

}
