package bo.gob.aduana.sga.gestormensajeria.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import bo.gob.aduana.sga.gestormensajeria.bean.MessageEmailBean;
import bo.gob.aduana.sga.gestormensajeria.service.EmailNotificationService;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

	@Autowired
	public MailSender mailSender;
	@Autowired
	public SimpleMailMessage templateMessage;
	@Autowired
	public JavaMailSenderImpl mailSenderFormat;
	@Autowired
	public VelocityEngine velocityEngine;


	@Override
	public MessageEmailBean sendEmail(MessageEmailBean mail) {

		SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
		if (mail.getTo() != null) {
			String[] toArr = new String[mail.getTo().size()];
			toArr = mail.getTo().toArray(toArr);
			msg.setTo(toArr);
		}
		if (mail.getToCc() != null) {
			String[] toCcArr = new String[mail.getToCc().size()];
			toCcArr = mail.getTo().toArray(toCcArr);
			msg.setCc(toCcArr);
		}
		if (mail.getToCco() != null) {
			String[] toCcoArr = new String[mail.getToCco().size()];
			toCcoArr = mail.getTo().toArray(toCcoArr);
			msg.setBcc(toCcoArr);
		}
		msg.setSentDate(new Date());
		msg.setSubject(mail.getSubject());
		msg.setText(mail.getContent());

		try {
			mailSender.send(msg);
			mail.setStatus("OK");
		} catch (MailException ex) {
			// System.err.println(ex.getMessage());
			System.out.println(ex);
			mail.setStatus("NOK");
		}
		return mail;
	}

	public MessageEmailBean sendMailFormat(MessageEmailBean mail) {

		MimeMessage message = mailSenderFormat.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom("aduana@aduana.gob.bo");
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

			// use the true flag to indicate the text included is HTML
			helper.setText(mail.getContent(), true);
			mail.setStatus("OK");
		} catch (MessagingException e) {
			mail.setStatus("NOK");
			e.printStackTrace();
		}
		mailSenderFormat.send(message);
		return mail;
	}

	public MessageEmailBean sendMailByVelocity(MessageEmailBean mail) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("mail",mail);

		MimeMessage message = mailSenderFormat.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom("aduana@aduana.gob.bo");
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
		} catch (MessagingException e) {
			mail.setStatus("NOK");
			e.printStackTrace();
		}
		mailSenderFormat.send(message);

		return mail;
	}

}
