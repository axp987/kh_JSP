package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardFormDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제 폼 페이지에서 넘어온 글번호와 글 비밀번호를
		// 가지고 DB에서 해당 게시글을 삭제하는 비지니스 로직
		String board_pwd = request.getParameter("pwd").trim();
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		int res = dao.deleteBoard(board_no, board_pwd);
		
		PrintWriter out = response.getWriter();
		if(res > 0) {
			dao.updateSequence(board_no);
			out.println("<script>"
					+ "alert('삭제 완료')"
					+ "location.href='board_list.do?page=" + nowPage + "'");
		} else {
			
		}
	}

}
