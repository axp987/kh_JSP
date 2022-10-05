package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberContent implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberDAO dao = MemberDAO.getInstance();
		
		int no = Integer.parseInt(request.getParameter("num"));
		MemberDTO contentList = dao.contentMember(no);
		
		request.setAttribute("list", contentList);
		
		return "view/member_content.jsp";
	}
}
