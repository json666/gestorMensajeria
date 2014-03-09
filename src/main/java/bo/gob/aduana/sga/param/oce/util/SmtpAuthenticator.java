package bo.gob.aduana.sga.param.oce.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
public class SmtpAuthenticator extends Authenticator{
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username,password);
	}
}
