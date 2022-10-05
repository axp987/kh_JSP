package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberDeleteOkAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no = Integer.parseInt(request.getParameter("num"));
		String pw = request.getParameter("pwd");
		
		MemberDTO dto = new MemberDTO();
		dto.setNum(no);
		dto.setPwd(pw);
		MemberDAO dao = MemberDAO.getInstance();
		int check = dao.deleteMember(dto);
		
		PrintWriter out = response.getWriter();
		System.out.println("check= " + check);
		if(check >= 1) {
			dao.deleteUpdate(dto);
			out.println("<script>"
					+ "alert('삭제 완료');"
					+ "location.href='select.do';"
					+ "</script>");
		} else {
			out.println("<script>"
					+ "alert('패스워드를 확인해주세요.');"
					+ "history.back();"
					+ "</script>");
		}
		
		
		return null;
	}
}
