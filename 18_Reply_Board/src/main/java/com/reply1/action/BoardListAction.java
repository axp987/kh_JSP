package com.reply1.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply1.controller.Action;
import com.reply1.controller.ActionForward;
import com.reply1.model.BoardDAO;
import com.reply1.model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// tbl_board 테이블 상의 전체 레코드를
		// 조회하여 view page로 이동시키는 비지니스 로직
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> list = dao.getBoardList();
		
		request.setAttribute("List", list);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/board_list.jsp");
		
		return forward;
	}

}
