package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;

public class AdminProductDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get방식으로 넘어온 제품번호에 해당하는 제품을 DB에서 삭제하는 비지니스 로직
		int product_num = Integer.parseInt(request.getParameter("pnum"));
		ProductDAO dao = ProductDAO.getInstnace();
		int res = dao.deleteProduct(product_num);
		
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {
			forward.setRedirect(false);
			forward.setPath("admin_product_list.do");
		} else {
			out.println("<script>"
					+ "alert('삭제 실패');"
					+ "history.back();"
					+ "</script>");
		}
		return forward;
	}

}
