package bo.gob.aduana.sga.gestormensajeria.service.impl;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.aduana.sga.gestormensajeria.bean.MailSessionBean;
import bo.gob.aduana.sga.gestormensajeria.bean.MessageEmailBean;
import bo.gob.aduana.sga.gestormensajeria.service.EmailNotificationService;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {
	
	
	@Autowired
	MailSessionBean mailSession;
	
	@Override
	public void sendEmail(MessageEmailBean messageEmailBean) {
		try {
			MimeMessage m = new MimeMessage(mailSession.getMailSession());
			Address from = new InternetAddress("aduana@aduana.gob.bo");
			m.setFrom(from);
			m.setRecipients(Message.RecipientType.TO,InternetAddress.parse(messageEmailBean.getTo()));
			System.out.println("Message To:"+messageEmailBean.getTo());
			m.setSubject(messageEmailBean.getSubject());
			m.setSentDate(new java.util.Date());
			m.setContent(messageEmailBean.getContent(), "text/html");
			Transport.send(m);
			System.out.println("Mail sent!");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
			System.out.println("Error in Sending Mail: " + e);
		}
	}

}
