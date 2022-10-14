package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.AdminDAO;
import com.shop.model.CategoryDAO;

public class AdminCategoryInputOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String code = request.getParameter("category_code").trim();
		String name = request.getParameter("category_name").trim();
		
		CategoryDAO dao = CategoryDAO.getInstance();
		int check = dao.insertCategory(code, name);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_category_list.do");
		} else {
			out.println("<script>"
					+ "alert('카테고리 등록 실패');"
					+ "history.back();"
					+ "</script>");
		}
		
		return forward;
	}

}
