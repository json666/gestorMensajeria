package bo.gob.aduana.sga.gestormensajeria.utils;

import java.io.StringWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Clase abstracta que envia un email
 * 
 * @author flimachi
 * 
 */

public abstract class EmailNotification {

	public abstract String subject();

	public abstract String content();

	public abstract String to();

	public void send(Session mailSession) {
		try {
			MimeMessage m = new MimeMessage(mailSession);
			Address from = new InternetAddress("aduana@aduana.gob.bo");
			m.setFrom(from);
			m.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to()));
			m.setSubject(subject());
			m.setSentDate(new java.util.Date());
			m.setContent(content(), "text/html");
			Transport.send(m);
			System.out.println("Mail sent!");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
			System.out.println("Error in Sending Mail: " + e);
		}
	}

	public static class ValidateEmailAddress extends EmailNotification {

		private String to;

		private String key;

		
		private String url;

		public ValidateEmailAddress(String to, String key,String url) {
			super();
			this.to = to;
			this.key = key;
			this.url = url;
		}

		@Override
		public String subject() {
			return "Correo electrónico de verificación de ingreso a Seguridad";
		}

		@Override
		public String content() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Hemos recibido su solicitud de registro como usuario del sistema.<br>");
			stringBuilder.append("Su cuenta fue creada y debe ser activada antes de que pueda utilizarla.<br>");
			stringBuilder.append("Para activarla haga clic en el siguiente enlace: ");
			stringBuilder.append("<a href='"+url+"#/confirm/"	+ key + "'>Pulse aqu&#237;</a>");
			System.out.println("string builder: "+stringBuilder.toString());
			VelocityContext context = new VelocityContext();
			context.put("message", stringBuilder.toString());
			VelocityEngine ve = VelocityHelper.getVelocityEngine();
			Template template = ve.getTemplate("templateEmail.vm");
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			return writer.toString();

		}

		@Override
		public String to() {
			return to;
		}
	}

	public static class ChangePassword extends EmailNotification {

		private String to;
		private String token;
		private String codigoModulo;
		private String user;
		private String url;

		public ChangePassword(String to, String user, String token,String codigo,String url) {
			super();
			this.to = to;
			this.token = token;
			this.user = user;
			this.codigoModulo = codigo;
			this.url = url;
		}

		@Override
		public String subject() {
			return "Correo electrónico de verificación de cambio de contraseña";
		}

		@Override
		public String content() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Este correo electr&#243;nico se env&#237;a para cambiar su contrase&#241;a");
			stringBuilder.append(", por favor haga clic en el siguiente enlace: ");
			stringBuilder.append("<a href='"+url+"#/reset/" + user + "/" + token + "/" + codigoModulo + "/'>Pulse aqu&#237;</a>");
			stringBuilder.append("<br>Gracias por utilizar el sistema de seguridad.");
			System.out.println("string builder: "+stringBuilder.toString());
			VelocityContext context = new VelocityContext();
			context.put("message", stringBuilder.toString());
			VelocityEngine ve = VelocityHelper.getVelocityEngine();
			Template template = ve.getTemplate("templateEmail.vm");
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			return writer.toString();
		}

		@Override
		public String to() {
			return to;
		}

	}
}
