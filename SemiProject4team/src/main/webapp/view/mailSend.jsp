<%@page import="src.com.model.LoginDAO"%>
<%@page import="javax.mail.PasswordAuthentication"%>
<%@page import="smtp.SMTPAuthenticatior"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="smtp.SMTPAuthenticatior"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
	p.put("mail.smtp.port", "465");
	p.put("mail.smtp.auth", "true");
	p.put("mail.smtp.starttls.enable", "true");
	p.put("mail.smtp.debug", "true");
	p.put("mail.smtp.ssl.enable", "true");
	p.put("mail.smtp.ssl.trust", "smtp.naver.com");
	p.put("mail.smtp.socketFactory.fallback", "true");
	LoginDAO dao = LoginDAO.getInstance();
	dao.test();
	 try {
		 Session sessi = Session.getInstance(p, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(user, password);
	            }
	        });
		 
         MimeMessage message = new MimeMessage(sessi);
         message.setFrom(new InternetAddress(user));            //수신자메일주소
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 

         // Subject
         message.setSubject(subject); //메일 제목을 입력

         // Text
         message.setText(content);    //메일 내용을 입력

         // send the message
         Transport.send(message); ////전송
         System.out.println("message sent successfully...");
	} catch(Exception e){
	    e.printStackTrace();
	    return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>