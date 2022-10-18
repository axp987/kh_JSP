package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;
import com.shop.model.CartDTO;
import com.shop.model.ProductDTO;

public class UserCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 제품상세 내역에서 장바구니 버튼을 클릭 하면
		// 폼 태그의 데이터들을 장바구니 테이블에 저장하는 비지니스 로직
		String name = request.getParameter("p_name");
		int price = Integer.parseInt(request.getParameter("p_price"));
		int qty = Integer.parseInt(request.getParameter("p_qty"));
		
	
		// no, spec, image, userid
		String []arr = request.getParameterValues("all");
		
		CartDTO dto = new CartDTO();
		dto.setName(name);
		dto.setPrice(price);
		dto.setQty(qty);
		dto.setPnum(Integer.parseInt(arr[0]));
		dto.setSpec(arr[1]);
		dto.setImage(arr[2]);
		dto.setId(arr[3]);
		
		CartDAO dao = CartDAO.getInstance();
		int check = dao.insertCart(dto);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("user_cart_list.do");
		} else {
			out.println("<script>"
					+ "alert('저장 실패')()"
					+ "history.back()"
					+ "</script>");
		}
		
		
		return forward;
	}

}
