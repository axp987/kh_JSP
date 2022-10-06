package com.reboard.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reboard.action.Action;
import com.reboard.action.selectAction;
import com.reboard.model.ReDAO;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		System.out.println("uri: " + uri);
		String path = request.getContextPath();
		System.out.println("path: " + path);
		String command = uri.substring(path.length()+1);
		
		Action action = null;
		String viewPage = null;
		if(command.equals("select.do")) {
			action = new selectAction();
			action.execute(request, response);
			viewPage = "view/select_page.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
}
