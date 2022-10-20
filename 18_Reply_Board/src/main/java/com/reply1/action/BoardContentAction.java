package com.reply1.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply1.controller.Action;
import com.reply1.controller.ActionForward;
import com.reply1.model.BoardDAO;
import com.reply1.model.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// DB에서 조회하여 해당 상세내역을 view page로 이동시키는 비지니스 로직
		
		int board_no = Integer.parseInt(request.getParameter("num"));
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO list = dao.getBoardContent(board_no);
		
		request.setAttribute("contentList", list);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/board_content.jsp");
		
		return forward;
	}

}
