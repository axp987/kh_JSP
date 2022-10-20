package smtp;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticatior extends Authenticator{	
	@Override
    protected PasswordAuthentication getPasswordAuthentication() {
		String user = "axp987@naver.com";
		String password = "qoa1@akfl";
        return new PasswordAuthentication(user,password);
    }
}
// getPasswordAuthentication