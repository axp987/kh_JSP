package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardSearchAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 검색 폼 페이지에서 넘어온 데이터를 가지고 검색어에
		// 해당하는 게시물들을 DB에서 조회하여 view page로
		// 이동시키는 비지니스 로직
		String search_field = request.getParameter("search_field").trim();
		String search_keyword = request.getParameter("search_keyword").trim();
		System.out.println(search_field + " " + search_keyword);
		// 페이징 처리 작업 진행

		// 한 페이지당 보여질 게시물의 수
		int rowsize = 3;

		// 아래에 보여질 페이지의 최대 블럭 수 - 예) [1][2][3] / [4][5][6] / [7][8][9]
		int block = 3;

		// DB 상의 게시물의 전체 수
		int totalRecord = 0;

		// 전체 페이지 수 - 전체 게시물 의 수 / 한 페이지 당 보여질 게시물의 수
		int allPage = 0;

		//현재 페이지 변수
		int page = 1;

		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}

		// 해당 페이지에서 시작번호
		int startNo = (page * rowsize) - (rowsize - 1);
		// (현재 페이지 - 한 페이지 게시물 수) - (한 페이지 게시물 수 - 1);
		// 해당 페이지에서 끝 번호
		int endNo = (page * rowsize);

		// 해당 페이지에서 시작 블럭
		int startBlock =  (((page-1) / block) * block) + 1;

		// 해당 페이지에서 끝 블럭
		int endBlock =  (((page-1) / block) * block) + block;

		BoardDAO dao = BoardDAO.getInstance();
		
		// DB 상의 검색 게시물의 수를 확인하는 메소드 호출
		totalRecord = dao.searchListCount(search_field, search_keyword);
		System.out.println("total: " + totalRecord);
		// 검색 게시물의 수를 한 페이지당 보여질 게시물의 수로
		// 나누기
		// 검색 전체 페이지가 수가 나옴
		// 전체 페이지 수가 나올 때 나머지가 있으면 무조건 페이지 수를 +1 해줘야함
		// 전체 페이지의 수
		allPage = (int)Math.ceil(totalRecord / (double)rowsize);
		// 블록 작업
		if(endBlock > allPage ) {
			endBlock = allPage;
		}

		// 현재 페이지에 해당하는 게시물을 가져오는 메소드 호출
		List<BoardDTO> searchList = dao.searchBoardList(search_field, search_keyword, page, rowsize);
		
		System.out.println("list >>> " + searchList);

		// 지금까지 페이징 처리 시 작업했던 모든 값들을
		// view page로 이동시키자
		request.setAttribute("Page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("field", search_field);
		request.setAttribute("keyword", search_keyword);
		request.setAttribute("List", searchList);
	}
}
