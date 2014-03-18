package bo.gob.aduana.sga.gestormensajeria.service;

import bo.gob.aduana.sga.gestormensajeria.bean.MessageEmailBean;



public interface EmailNotificationService {
	
	public MessageEmailBean sendEmail(MessageEmailBean mail);
	
	public MessageEmailBean sendMailFormat(MessageEmailBean mail);
	
	public MessageEmailBean sendMailByVelocity(MessageEmailBean mail);

}
