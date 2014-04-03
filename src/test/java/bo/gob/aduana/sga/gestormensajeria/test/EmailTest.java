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

import javax.mail.MessagingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testDispatcher.xml"})
public class EmailTest {

    @Autowired
    private EmailNotificationService emailNotificationService;


    @Test
    public void enviaMailWithVelocityTest() {
        System.out.println("EnviaMailWithVelocity...");

        MessageEmailBean messageEmailBean = new MessageEmailBean();
        try {
            List<String> to = new ArrayList<String>();
            to.add("osanchez@aduana.gob.bo");
            to.add("jheysonsanchez@gmail.com");
            messageEmailBean.setTo(to);
            messageEmailBean.setSubject("Test con Plantilla Velocity!");
            messageEmailBean.setContent("Mensaje de Prueba.");
            messageEmailBean.setNameTemplate("templateEmail.vm");

            messageEmailBean = emailNotificationService.sendMailByVelocity(messageEmailBean);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        Assert.assertTrue("No se logro enviar el Mail Velocity", messageEmailBean.getStatus().equals("OK"));
        System.out.println("EnviaMailWithVelocity...");
    }

}