package com.reboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reboard.model.ReDAO;
import com.reboard.model.ReDTO;

public class selectAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ReDAO dao = ReDAO.getInstance();
		
		int rowitem = 3; // 1개의 페이지에 게시물 갯수
		int totalRecord = dao.getListCount(); // 전체 게시물
		
		int page = 0; //현재 페이지를 가지고 와야함
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		} // 페이지가 선택되어 있지 안되었다면, 첫번째 페이지부터 시작
		
		
		List<ReDTO> list = dao.getSelectList(totalRecord, page);
		
		
		
		request.setAttribute("List", list);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("page", page);
		
	}
}
