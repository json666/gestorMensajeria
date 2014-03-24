package bo.gob.aduana.sga.gestormensajeria.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bo.gob.aduana.sga.core.gestormensajeria.bean.MessageEmailBean;
import bo.gob.aduana.sga.gestormensajeria.service.EmailNotificationService;
import bo.gob.aduana.sga.gestormensajeria.service.impl.EmailNotificationServiceImpl;
import bo.gob.aduana.sga.gestormensajeria.test.utils.SpringMongoConfig;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/testDispatcher.xml" })
public class EmailTest {

//	private final ApplicationContext context = new AnnotationConfigApplicationContext(
//			SpringMongoConfig.class);
//
//	@Autowired
//	private EmailNotificationService emailNotificationService;
//
//	//@Test
//	public void enviaMailFormatTest() {
//		System.out.println("EnviaMailFormat...");
//		MessageEmailBean messageEmailBean = new MessageEmailBean();
//		List<String> to = new ArrayList<String>();
//		to.add("osanchez@aduana.gob.bo");
//		to.add("rloza@aduana.gob.bo");
//		messageEmailBean.setTo(to);
//		messageEmailBean.setSubject("Test con Formato Html!");
//		messageEmailBean.setContent("Mensaje de Prueba <b>negrita</b><br>.");
//		messageEmailBean = emailNotificationService
//				.sendMailFormat(messageEmailBean);
//		Assert.assertTrue("No se logro enviar el Mail con formato Html",
//				messageEmailBean.getStatus().equals("OK"));
//		System.out.println("EnviaMailFormat...");
//	}
//
//	//@Test
//	public void enviaMailWithVelocityTest() {
//		System.out.println("EnviaMailWithVelocity...");
//		MessageEmailBean messageEmailBean = new MessageEmailBean();
//		List<String> to = new ArrayList<String>();
//		to.add("osanchez@aduana.gob.bo");
//		to.add("jheysonsanchez@gmail.com");
//		messageEmailBean.setTo(to);
//		messageEmailBean.setSubject("Test con Plantilla Velocity!");
//		messageEmailBean.setContent("Mensaje de Prueba.");
//		messageEmailBean.setNameTemplate("templateEmail.vm");
//		messageEmailBean = emailNotificationService.sendMailByVelocity(messageEmailBean);
//		Assert.assertTrue("No se logro enviar el Mail Velocity", messageEmailBean.getStatus().equals("OK"));
//		System.out.println("EnviaMailWithVelocity...");
//	}

}
