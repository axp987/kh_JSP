package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardModifyOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정 폼 페이지에서 넘어온 데이터들을
		// DB에 전송하여 게시글을 수정하는 비지니스 로직
		String board_writer = request.getParameter("writer").trim();
		String board_title = request.getParameter("title").trim();
		String board_content = request.getParameter("area").trim();
		String board_pwd = request.getParameter("pwd").trim();
		
		// 히든으로 넘어온 데이터들
		
		int board_no = Integer.parseInt(request.getParameter("board_no").trim());
		String db_pwd = request.getParameter("db_pwd").trim();
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		
		BoardDTO dto = new BoardDTO();
		dto.setNo(board_no);
		dto.setWriter(board_writer);
		dto.setTitle(board_title);
		dto.setCont(board_content);
		dto.setPwd(db_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		PrintWriter out = response.getWriter();
		
		if(db_pwd.equals(board_pwd)) {
			int res = dao.updateBoard(dto);
			if(res > 0) {
				out.println("<script>"
						+ "alert('게시물 수정 성공')"
						+ "location.href='board_content.do?no=" + dto.getNo() + "&page=" + nowPage + "'"
						+ "</script>");
			} else {
				out.println("<script>"
						+ "alert('게시물 수정 실패');"
						+ "history.back();"
						+ "</script>");
			}
		}
		
		
	}

}
