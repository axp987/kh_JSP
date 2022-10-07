package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsModifyOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String []bbs = request.getParameterValues("all");
		
		BbsDTO dto = new BbsDTO();
		dto.setNo(Integer.parseInt(bbs[0]));
		dto.setTitle(bbs[1]);
		dto.setCont(bbs[2]);
		dto.setPwd(bbs[3]);
		
		BbsDAO dao = BbsDAO.getInstance();
		int res = dao.setBbsUpdate(dto);
		//request.setAttribute("Res", res);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		System.out.println(res);
		
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("bbs_content.do?num=" + dto.getNo());
			
		} else {
			out.println("<script>"
					+ "alert('게시물 답변 등록 실패');"
					+ "history.back();"
					+ "</script>");
		}
		
		return forward;
	}
}
