package com.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class UserCategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 카테고리 코드에 해당하는
		// 제품 리스트를 조회하여 view page로 이동시키는 비지니스 로직
		String product_code = request.getParameter("code").trim();
		ProductDAO dao = ProductDAO.getInstnace();
		List<ProductDTO> dto = dao.getProductList(product_code);
		
		request.setAttribute("productList", dto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("user/user_main.jsp");
		
		
		return forward;
	}

}
