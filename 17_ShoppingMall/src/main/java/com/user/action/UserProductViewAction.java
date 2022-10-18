package com.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class UserProductViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 제품번호에 해당하는 제품의
		// 상세 정보를 조회하여 view page로 이동시키는 비지니스 로직
		
		int product_no = Integer.parseInt(request.getParameter("pnum").trim());
		ProductDAO dao = ProductDAO.getInstnace();
		
		ProductDTO dto = dao.productContent(product_no);
		request.setAttribute("productCont", dto);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_product_detail.jsp");
		
		return forward;
	}

}
