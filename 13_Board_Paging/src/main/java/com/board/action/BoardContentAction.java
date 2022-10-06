package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

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
		String checking = request.getParameter("check");
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO content = null;
		boolean ch = true;
		
		if(checking.equals("conTent")) {
			content = dao.getBoardCont(no);
			// 글제목 클릭 시 조회수 증가시키는 메소드 호출
			if(ch == true) {
				dao.getBoardHit(no);				
			}
			// 글번호에 해당하는 상세내역을 조회하는 메소드 호출
			request.setAttribute("Cont", content);
			request.setAttribute("Page", board_page);			
			
		} else if (checking.equals("upDate")) {
			//dao.setBoardUpdate(no);
			content = dao.getBoardCont(no);
			request.setAttribute("Cont", content);
			request.setAttribute("Page", board_page);
			
		} else if(checking.equals("upDateOk")) {
			// no, writer, title, cont, pwd 
			String []arr = request.getParameterValues("all");
			BoardDTO dto = new BoardDTO();
			dto.setNo(Integer.parseInt(arr[0]));
			dto.setTitle(arr[1]);
			dto.setCont(arr[2]);
			dto.setPwd(arr[3]);
			
			//dto.setA(arr);
			int check = dao.getBoardUpdate(dto);
			System.out.println("check: " + check);
			PrintWriter out = response.getWriter();
			if(check > 0) {
				ch = false;
				out.println("<script>"
						+ "alert('수정 완료');"
						+ "location.href='board_content.do?num=" + dto.getNo() + "&page=" + board_page + "&check=conTent';"
						+ "</script>");
			} else if(check == -1) {
				out.println("<script>"
						+ "alert('패스워드를 확인해주세요.');"
						+ "history.back();"
						+ "</script>");
			}
		} //if
	}
}
