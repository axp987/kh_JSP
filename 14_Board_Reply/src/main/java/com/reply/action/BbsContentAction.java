package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsContentAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no = Integer.parseInt(request.getParameter("num"));
		
		BbsDAO dao = BbsDAO.getInstance();
		dao.bbsHit(no);
		BbsDTO list = dao.getBbsContent(no);

		// 글번호 view page로 이동시키기
		request.setAttribute("List", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/bbs_content.jsp");
		
		return forward;
	}
}
