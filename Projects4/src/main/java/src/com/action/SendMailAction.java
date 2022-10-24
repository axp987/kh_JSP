package src.com.action;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.com.controller.Action;
import src.com.controller.ActionForward;
import src.com.model.userTokenDTO;

public class SendMailAction implements Action {
	private String mailTitle;
	private String mailContent;
	String user = "axp987@naver.com";
	String password = "qoa1@akfl";
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 메일을 보내는 클래스
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String memberEmail = request.getParameter("email"); // 수신자 이메일
		String domain_Address = request.getParameter("domain");
	
		int ranNum = numberToken();
		
		mailTitle = "안녕하세요. 4조 인증번호입니다.";
		mailContent = "아래의 인증번호를 입력해주세요.\n\n" + ranNum + "\n\n감사합니다.";
		
		Properties p = new Properties();
		p.put("mail.smtp.host","smtp.naver.com"); 
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.ssl.trust", "smtp.naver.com");
		p.put("mail.smtp.socketFactory.fallback", "true");
		
		 ActionForward forward = new ActionForward();
		 try {
			 Session sessi = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(user, password);
		            }
		        });
			 
	         MimeMessage message = new MimeMessage(sessi);
	         String fullEmail = memberEmail + "@" + domain_Address;
	         message.setFrom(new InternetAddress(user)); // 발신자 주소            
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(fullEmail)); //수신자 주소

	         // Subject
	         message.setSubject(mailTitle); //메일 제목을 입력

	         // Text
	         message.setText(mailContent);    //메일 내용을 입력

	         // send the message
	         Transport.send(message); ////전송
	         System.out.println("message sent successfully");
	         
	         PrintWriter out = response.getWriter();
	         out.println("<script>"
	         		+ "alert('인증번호 발송을 완료했습니다.');"
	         		+ "</script>");
	         
	         userTokenDTO save = new userTokenDTO();
	         save.setEmail(fullEmail);
	         save.setToken(ranNum);
	         
	         HttpSession session = request.getSession();
	         session.setAttribute("user", save);
	         
	         forward.setRedirect(false);
	         forward.setPath("tokenCheck.do");
	         
		} catch(Exception e){
			PrintWriter out = response.getWriter();
	         out.println("<script>"
	         		+ "alert('통신 오류입니다..');"
	         		+ "history.back();"
	         		+ "</script>");
		    e.printStackTrace();
		}
		 
		
		return forward;
	}
	
	public int numberToken() {
		int userToken = (int)(Math.random()*1000000);
		
		return userToken;
	}
}
