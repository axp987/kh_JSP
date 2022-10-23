package src.com.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.Action.Action;
import src.com.Action.ActionForward;

public class sendMail implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String user = "axp987@naver.com";
		String password = "qoa1@akfl";
		
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		System.out.print("파라미터값: " + email + " " + subject + " " + content);
		
		Properties p = new Properties();
		p.put("mail.smtp.host","smtp.naver.com"); 
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		
		 try {
			 Session sessi = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(user, password);
		            }
		        });
			 
	         MimeMessage message = new MimeMessage(sessi);
	         message.setFrom(new InternetAddress(email));            //수신자메일주소
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress("axp987@naver.com")); 

	         // Subject
	         message.setSubject(subject); //메일 제목을 입력

	         // Text
	         message.setText(content);    //메일 내용을 입력

	         // send the message
	         Transport.send(message); ////전송
	         System.out.println("message sent successfully...");
		} catch(Exception e){
		    e.printStackTrace();
		}
		return null;
	}
}
