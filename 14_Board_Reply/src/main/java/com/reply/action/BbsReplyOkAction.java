package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsReplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String []bbs = request.getParameterValues("reply_all");
		// no, group, step, indent, 
		
		BbsDTO dto = new BbsDTO();
		
		dto.setNo(Integer.parseInt(bbs[0]));
		dto.setGroup(Integer.parseInt(bbs[1]));
		dto.setStep(Integer.parseInt(bbs[2]));
		dto.setIndent(Integer.parseInt(bbs[3]));
		dto.setWriter(bbs[4]);
		dto.setTitle(bbs[5]);
		dto.setCont(bbs[6]);
		dto.setPwd(bbs[7]);
		
		BbsDAO dao = BbsDAO.getInstance();
		
		// 만약에 원글에 기존에 작성했던 답변글이 존재하는 경우
		// 해당 답변글에 step을 하나 증가시켜 주는 메서드 호출
		dao.replyUpdate(dto);
		
		int res = dao.replyBbs(dto);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		} else {
			out.println("<script>"
					+ "alert('게시물 답변 등록 실패');"
					+ "history.back();"
					+ "</script>");
		}
		
		return forward;
	}

}
