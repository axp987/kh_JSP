package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 게시글 작성 폼 페이지에서 넘어온 데이터들을
		// DB에 저장하는 비지니스 로직
		String []bbs = request.getParameterValues("all");
		// 작성자, 글제목, 글내용, 글비밀번호
		
		BbsDTO dto = new BbsDTO();
		dto.setWriter(bbs[0]);
		dto.setTitle(bbs[1]);
		dto.setCont(bbs[2]);
		dto.setPwd(bbs[3]);
		
		BbsDAO dao = BbsDAO.getInstance();
		int check = dao.insertBbs(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		if(check > 0) {
			out.println("<script>"
					+ "alert('게시물 추가 성공')" // 웹브라우저가 멈추는게 아니고 다른건가?
					+ "</script>"); // 포워드로 바로 이동하기 때문에 나오긴하는데 보이진 않음
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		} else {
			out.println("<script>"
					+ "alert('게시물 추가 실패')"
					+ "</script>");
			forward.setRedirect(false);
			forward.setPath("view/bbs_write.jsp");
		}
		
		return forward;
	}

}
