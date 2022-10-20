package com.reply1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply1.controller.Action;
import com.reply1.controller.ActionForward;
import com.reply1.model.BoardDAO;
import com.reply1.model.ReplyDAO;
import com.reply1.model.ReplyDTO;

public class BoardReplyInsertOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 답변글 폼에서 넘어온 데이터들을 가지고 DB에 저장하는 비지니스 로직
		
		String reply_writer = request.getParameter("writer").trim();
		String reply_content = request.getParameter("content").trim();
		int reply_bno = Integer.parseInt(request.getParameter("bno"));
		
		ReplyDTO dto = new ReplyDTO();
		
		dto.setBno(reply_bno);
		dto.setRewriter(reply_writer);
		dto.setRecont(reply_content);
		ReplyDAO dao = ReplyDAO.getInstance();
		int check = dao.getReplyInsert(dto);
		
		PrintWriter out = response.getWriter();
		// 결과값을 클라이언트(ajax)에게 보내주면 됨
		out.println(check);
		
		return null;
	}

}
