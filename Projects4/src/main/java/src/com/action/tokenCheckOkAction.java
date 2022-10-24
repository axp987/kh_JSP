package src.com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.com.controller.Action;
import src.com.controller.ActionForward;
import src.com.model.LoginDAO;
import src.com.model.pmemberDTO;

public class tokenCheckOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 유저가 입력한 인증코드를 비교하는 클래스
		HttpSession session = request.getSession();
		String user = request.getParameter("email");
		int sysToken =Integer.parseInt(request.getParameter("token"));
		int inToken = Integer.parseInt(request.getParameter("inputToken"));
		
		
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(sysToken != inToken) {
			out.println("<script>"
					+ "alert('인증번호가 일치하지 않습니다.');"
					+ "history.back();"
					+ "</script>");
		} else if(sysToken == inToken) {
			LoginDAO loginDB = LoginDAO.getInstance();
			int memberCheck = loginDB.MemberCheck(user);
			
			if(memberCheck == 1) { // 로그인
				pmemberDTO signIn = new pmemberDTO();
				
				request.setAttribute("sign", signIn);
				
				forward.setRedirect(false);
				forward.setPath(""); // 로그인시 메인 >> 회원등급 판단도 해야함
			} else if(memberCheck == 0) { // 회원가입
				forward.setRedirect(true);
				forward.setPath("view/memberSignUp.jsp");
			}
		}
		
		return forward;
	}
}
