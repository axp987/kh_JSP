package com.member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.action.*;

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
		
		Action action = null;
		//MemberListAction mem = null;
		if(command.equals("select.do")) {
			action = new MemberListAction();
			//mem = new MemberListAction();
		} else if(command.equals("insert.do")) {
			action = new MemberJoinAction();
		} else if(command.equals("insert_ok.do")) {
			action = new MemberJoinOkAction();
		} else if(command.equals("content.do")) {
			action = new MemberContent();
		} else if(command.equals("modify.do")) {
			action = new MemberModifyAction();
		} else if(command.equals("modify_ok.do")) {
			action = new MemeberModifyOkAction();
		}
		
		// MemberListAction 에서 모든 메소드를 호출하면 안되나?
		// path1에 해당 *.do에 해당하는 서블릿이 들어감
		String path1 = action.execute(request, response);
		
		//String path1 = mem.execute(request, response);
		RequestDispatcher rd = request.getRequestDispatcher(path1);
		rd.forward(request, response);
	}
}
