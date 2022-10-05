package com.board.controller;

import java.io.IOException;

import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.*;

public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// getRequestURI(): "/프로젝트명/파일명(*.do)" 라는
		//					문자열을 반환해 주는 메소드
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		
		// getContextPath(): 현재 프로젝트명을 문자열로 반환해 주는 메소드
		String path = request.getContextPath();
		System.out.println("Path >>> " + path);
		
		String command = uri.substring(path.length()+1);
		System.out.println("Command >>>" + command);
		
		String viewPage = null;
		Action action = null;
		if(command.equals("board_list.do")) {
			action = new BoardListAction();
			action.execute(request, response);
			viewPage = "view/board_list.jsp";
		} else if(command.equals("board_write.do")) {
			viewPage = "view/board_write.jsp";
		} else if(command.equals("board_write_Ok.do")) {
			action = new BoardWriteOkAction();
			action.execute(request, response);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
}
