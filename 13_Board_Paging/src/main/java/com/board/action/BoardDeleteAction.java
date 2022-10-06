package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardDeleteAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no = Integer.parseInt(request.getParameter("num"));
		// nowPage 받아줘야함
		
		BoardDTO dto = new BoardDTO();
		dto.setNo(no);
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.setBoardDelete(dto);
		
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>"
					+ "alert('삭제 완료');"
					+ "location.href='" + request.getContextPath() + "/board_list.do';"
					+ "</script>");
		} else {
			out.println("<script>"
					+ "alert('삭제 실패');"
					+ "history.back();"
					+ "</script>");
		}
	}
}
