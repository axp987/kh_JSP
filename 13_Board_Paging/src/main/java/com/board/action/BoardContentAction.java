package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상세내역 표시
		// get 방식으로 넘어온 글번호에 해당하는 게시글의
		// 상세 내역 DB에서 조회하여 view page로 이동시키는 비지니스 로직
		int no = Integer.parseInt(request.getParameter("num"));
		int board_page = Integer.parseInt(request.getParameter("page")); 
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 글제목 클릭 시 조회수 증가시키는 메소드 호출
		dao.getBoardHit(no);
		
		// 글번호에 해당하는 상세내역을 조회하는 메소드 호출
		BoardDTO content = dao.getBoardCont(no);
		
		request.setAttribute("Cont", content);
		request.setAttribute("Page", board_page);
	}
}
