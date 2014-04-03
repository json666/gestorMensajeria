package bo.gob.aduana.sga.gestormensajeria.service.impl;

import java.io.IOException;

import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;


import bo.gob.aduana.sga.core.gestormensajeria.model.Mensaje;
import bo.gob.aduana.sga.gestormensajeria.service.MessageSender;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class MessageSenderEmailImpl implements MessageSender<Mensaje> {

	@Autowired
	@Qualifier("jmsTemplateEmailQueue")
	JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("jmsTemplateEmailTopic")
	JmsTemplate jmsTemplateCopy;

	public MessageSenderEmailImpl(final JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;

	}

    public JsonResult send(Mensaje msg) {
        final Mensaje mensaje = msg;
        JsonResult jsonResult = new JsonResult();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String jsonMsg = ow.writeValueAsString(msg);
            jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    ObjectMessage message = session.createObjectMessage();
                    message.setObject(mensaje);
                    return message;
                }
            });
            jmsTemplateCopy.convertAndSend(jsonMsg);
            jsonResult.setResult("La Notificacion fue enviada Satisfactoriamente.");
            jsonResult.setSuccess(true);
        } catch (JsonGenerationException e) {
            jsonResult.setResult("Mensaje  no fue enviado");
            jsonResult.setSuccess(false);
            e.printStackTrace();
        } catch (JsonMappingException e) {
            jsonResult.setResult("Mensaje  no fue enviado");
            jsonResult.setSuccess(false);
            e.printStackTrace();
        } catch (IOException e) {
            jsonResult.setResult("Mensaje no fue enviado");
            jsonResult.setSuccess(false);
            e.printStackTrace();
        } catch (Exception e) {
            jsonResult.setResult("Mensaje no fue enviado");
            jsonResult.setSuccess(false);
            e.printStackTrace();
        }
        return jsonResult;

    }

}
