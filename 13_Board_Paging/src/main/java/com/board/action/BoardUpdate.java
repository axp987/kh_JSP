package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardUpdate implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_no = Integer.parseInt(request.getParameter("no"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		
		
		
	}
}
