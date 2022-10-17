package com.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class UserMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 제품 전체 리스트를 DB에서 조회하여
		// view page로 이동시키는 비지니스 로직
		ProductDAO dao = ProductDAO.getInstnace();
		List<ProductDTO> list = dao.getProductList();
		request.setAttribute("productList", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_main.jsp");
		
		return forward;
	}

}
