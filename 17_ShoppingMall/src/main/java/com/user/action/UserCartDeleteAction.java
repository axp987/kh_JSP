package com.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;

public class UserCartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 장바구니 번호에 해당하는
		// 장바구니 내역을 DB에서 삭제하는 비지니스 로직
		
		int cart_num = Integer.parseInt(request.getParameter("num").trim());
		
		CartDAO dao = CartDAO.getInstance();
		int res = dao.deleteCart(cart_num);
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("user_cart_list.do");
		} else {
			out.println("<script>"
					+ "alert('삭제 실패');"
					+ "history.back();"
					+ "</script>");
			
		}
		
		return forward;
	}

}
