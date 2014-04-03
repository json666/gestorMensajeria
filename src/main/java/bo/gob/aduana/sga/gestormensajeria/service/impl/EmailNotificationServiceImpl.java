package bo.gob.aduana.sga.gestormensajeria.service.impl;

import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bo.gob.aduana.sga.gestormensajeria.bean.MailSessionBean;
import bo.gob.aduana.sga.param.oce.util.VelocityHelper;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import bo.gob.aduana.sga.core.gestormensajeria.bean.MessageEmailBean;
import bo.gob.aduana.sga.gestormensajeria.service.EmailNotificationService;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

	@Autowired
	public VelocityEngine velocityEngine;

    @Autowired
    MailSessionBean mailSession;

	public MessageEmailBean sendMailByVelocity(MessageEmailBean mail) throws MessagingException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("mail",mail);

        MimeMessage mime= new MimeMessage(mailSession.getMailSession());

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mime, true);
			helper.setFrom("nsga@aduana.gob.bo");
			if (mail.getTo() != null) {
				String[] toArr = new String[mail.getTo().size()];
				toArr = mail.getTo().toArray(toArr);
				helper.setTo(toArr);
			}
			if (mail.getToCc() != null) {
				String[] toCcArr = new String[mail.getToCc().size()];
				toCcArr = mail.getTo().toArray(toCcArr);
				helper.setCc(toCcArr);
			}
			if (mail.getToCco() != null) {
				String[] toCcoArr = new String[mail.getToCco().size()];
				toCcoArr = mail.getTo().toArray(toCcoArr);
				helper.setBcc(toCcoArr);
			}
			helper.setSentDate(new Date());
			helper.setSubject(mail.getSubject());

			String text = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, mail.getNameTemplate(), "UTF-8", model);

			helper.setText(text, true);
			mail.setStatus("OK");
            Transport.send(mime);
		} catch (MessagingException e) {
			mail.setStatus("NOK");
			e.printStackTrace();
		}catch(Exception e){
            e.printStackTrace();
        }
        return mail;
	}


}
