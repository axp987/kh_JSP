package com.admin.action;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.AdminDTO;
import com.shop.model.CategoryDAO;
import com.shop.model.CategoryDTO;

public class AdminCategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 카테고리 테이블에 있는 전체 카테고리 리스트를
		// 조회하여 view page로 
		CategoryDAO dao = CategoryDAO.getInstance();
		List<CategoryDTO> categoryList = dao.getCategoryList();
		
		
		request.setAttribute("List", categoryList);
		System.out.println(categoryList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_category_list.jsp");
		
		return forward;
	}

}
