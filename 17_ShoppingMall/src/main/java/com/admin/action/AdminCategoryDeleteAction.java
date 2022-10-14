package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;

public class AdminCategoryDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 카테고리 번호에 해당하는
		// 카테고리를 DB에서 삭제하는 비지니스 로직
		
		int category_num = Integer.parseInt(request.getParameter("cnum"));
		
		CategoryDAO dao = CategoryDAO.getInstance();
		int check = dao.deleteCategory(category_num);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_category_list.do");
		} else {
			out.println("<script>"
					+ "alert('카테고리 삭제 실패');"
					+ "history.back();"
					+ "</script>");
		}
		
		return forward;
	}

}
