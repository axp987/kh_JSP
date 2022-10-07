package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsReplyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글번호에 해당하는 게시글을
		// 조회하여 해당 게시글의 상세내역을 답변글 폼
		// 페이지로 이동시키는 비지니스 로직
		int no = Integer.parseInt(request.getParameter("num"));
		
		BbsDAO dao = BbsDAO.getInstance();
		BbsDTO list = dao.getBbsContent(no);
		
		request.setAttribute("reply", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/bbs_reply.jsp");
		
		return forward;
	}
}
