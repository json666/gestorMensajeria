package bo.gob.aduana.sga.gestormensajeria.bean;

import javax.mail.Session;

public class MailSessionBean {
	private Session mailSession;

	public Session getMailSession() {
		return mailSession;
	}

	public void setMailSession(Session mailSession) {
		this.mailSession = mailSession;
	}

}
