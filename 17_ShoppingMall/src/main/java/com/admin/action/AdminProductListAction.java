package com.admin.action;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ProductDAO dao = ProductDAO.getInstnace();
		
		List<ProductDTO> list = dao.getProductList();
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.toString());
		}
		
		request.setAttribute("List", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_product_list.jsp");
		
		return forward;
	}

}
