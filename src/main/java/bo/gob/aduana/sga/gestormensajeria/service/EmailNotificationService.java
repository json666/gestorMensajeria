package bo.gob.aduana.sga.gestormensajeria.service;

import bo.gob.aduana.sga.gestormensajeria.bean.MessageEmailBean;



public interface EmailNotificationService {
	/**
	 * Metodo para enviar un correo electronico
	 * @param MessageEmailBean datos para enviar el correo electronico
	 */
	public void sendEmail(MessageEmailBean MessageEmailBean);
	
	/**
	 * Metodo que envia un correo electronico  
	 * @param emailNotification Clase abstracta donde se personaliza los detalles para enviar el correo electronico
	 */
	//public void sendEmail(EmailNotification emailNotification);

}
