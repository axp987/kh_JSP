package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberDeleteAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no = Integer.parseInt(request.getParameter("num"));
		String pw = request.getParameter("pwd");
		
//		MemberDTO dto = new MemberDTO();
//		dto.setNum(no);
//		dto.setPwd(pw);
//		MemberDAO dao = MemberDAO.getInstance();
//		dao.deleteMember(dto);
		
		request.setAttribute("No", no);
		
		return "view/member_delete.jsp";
	}
}
