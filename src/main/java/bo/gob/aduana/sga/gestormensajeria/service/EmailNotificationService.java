package bo.gob.aduana.sga.gestormensajeria.service;

import bo.gob.aduana.sga.core.gestormensajeria.bean.MessageEmailBean;

import javax.mail.MessagingException;


public interface EmailNotificationService {

	
	public MessageEmailBean sendMailByVelocity(MessageEmailBean mail) throws MessagingException;

}
