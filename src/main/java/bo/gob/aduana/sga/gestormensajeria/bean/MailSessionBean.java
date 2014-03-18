package bo.gob.aduana.sga.gestormensajeria.bean;

import javax.mail.Session;

import org.springframework.stereotype.Service;
@Service
public class MailSessionBean {
	private Session mailSession;

	public Session getMailSession() {
		return mailSession;
	}

	public void setMailSession(Session mailSession) {
		this.mailSession = mailSession;
	}

}
