package bo.gob.aduana.sga.gestormensajeria.test;

import bo.gob.aduana.sga.gestormensajeria.bean.MessageEmailBean;
import bo.gob.aduana.sga.gestormensajeria.service.impl.EmailNotificationServiceImpl;

public class EmailTest {
	
	
	public static void main(String args []){
		MessageEmailBean beanMail;
		beanMail= new MessageEmailBean();
		beanMail.setTo("osanchez@aduana.gob.bo");
		beanMail.setSubject("Esto es una prueba de correo");
		beanMail.setContent("Contenido minimo");
		EmailNotificationServiceImpl email= new EmailNotificationServiceImpl();
		email.sendEmail(beanMail);
		
		
		
	}
}
