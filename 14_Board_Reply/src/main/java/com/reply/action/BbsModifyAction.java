package com.reply.action;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no = Integer.parseInt(request.getParameter("num"));
		
		BbsDAO dao = BbsDAO.getInstance();
		BbsDTO list = dao.getBbsContent(no);
		
		request.setAttribute("List", list);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/bbs_modify.jsp");
		
		return forward;
	}

}
