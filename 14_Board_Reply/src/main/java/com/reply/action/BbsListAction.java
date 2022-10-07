package com.reply.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// DB 상의 전체 레코드를 조회하여 view page로 이동시키는 비지니스 로직
		BbsDAO dao = BbsDAO.getInstance();
		List<BbsDTO> list = dao.getBbsList();
		
		request.setAttribute("List", list);
		
		
		ActionForward forward = new ActionForward();
		// true: *.do 이동
		// false: *.jsp 이동
		forward.setRedirect(false); 
		forward.setPath("view/bbs_list.jsp");
		
		return forward;
	}
}
