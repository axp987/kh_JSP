package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.AdminDAO;
import com.shop.model.AdminDTO;

public class AdminLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 관리자 로그인 페이지에서 입력한 관리자 아이디와
		// 관리자 비밀번호가 DB상에 있는 아이디, 비밀번호와
		// 일치하는지 여부를 확인하는 비지니스 로직
		String admin_id = request.getParameter("admin_id").trim();
		String admin_pwd = request.getParameter("admin_pwd").trim();
		
		AdminDAO dao = AdminDAO.getInstance();
		int check = dao.adminCheck(admin_id, admin_pwd);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		// 세션을 설정하는 방법
		// HttpSession이 존재하면 현재 HttpSession을 반환하고
		// 존재하지 않으면 세션을 생성하는 메서드
		HttpSession session =  request.getSession();
		System.out.println(admin_id + " " + admin_pwd + " " + check);
		if(check > 0) {
			AdminDTO dto = dao.getAdmin(admin_id); // 아이디를 가져오는 메서드
			session.setAttribute("adminId", dto.getId());
			session.setAttribute("adminName", dto.getName());
			
			// 세션에 저장된 정보를 가지고 view page로 이동
			forward.setRedirect(false);
			forward.setPath("admin/admin_main.jsp");
			
		} else if(check == -1) {
			out.println("<script>"
					+ "alert('패스워드를 확인해주세요.');"
					+ "history.back();"
					+ "</script>");
		} else {
			out.println("<script>"
					+ "alert('아이디 또는 패스워드를 확인해주세요.');"
					+ "history.back();"
					+ "</script>");
		}
		
		return forward;
	}

}
