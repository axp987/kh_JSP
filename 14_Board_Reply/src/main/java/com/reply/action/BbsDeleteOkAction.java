package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;

public class BbsDeleteOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bbs_no = Integer.parseInt(request.getParameter("bbs_no"));
		String bbs_pwd = request.getParameter("pwd").trim();
		
		BbsDAO dao = BbsDAO.getInstance();
		int res = dao.deleteBbs(bbs_no, bbs_pwd);
		
		PrintWriter out = response.getWriter();
		if(res > 0) {
			out.println("<script>");
			out.println("alert('댓글이 삭제 되었습니다.')");
			out.println("location.href='bbs_list.do'");
			out.println("</script>");
		} else if(res == -2) {
			out.println("<script>"
					+ "alert('댓글이 삭제 되었습니다.');"
					+ "location.href='bbs_list.do';"
					+ "</script>");
		} else if(res == -1) {
			out.println("<script>"
					+ "alert('패스워드를 확인해주세요.');"
					+ "history.back();"
					+ "</script>");
		} else {
			out.println("<script>"
					+ "alert('게시글 삭제 실패');"
					+ "history.back();"
					+ "</script>");
		}
		
		return null;
	}
}
