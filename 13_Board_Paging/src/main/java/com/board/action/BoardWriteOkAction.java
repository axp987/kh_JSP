package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardWriteOkAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String []arr = request.getParameterValues("all");
		
		BoardDTO dto = new BoardDTO(arr[0], arr[1], arr[2], arr[3]);
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.setBoardList(dto);

		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>"
					+ "alert('추가 완료');"
					+ "location.href='" + request.getContextPath() +"/board_list.do';"
					+ "</script>");
		} else {
			out.println("<script>"
					+ "alert('추가 실패');"
					+ "history.back();"
					+ "</script>");
		}
		
	}
}
