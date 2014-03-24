package bo.gob.aduana.sga.gestormensajeria.service.impl;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import bo.gob.aduana.sga.core.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.service.MessageTask;

public class MessageTaskImpl implements MessageTask<Tarea> {
	
	@Autowired
	@Qualifier("jmsTemplateTaskQueue")
	JmsTemplate jmsTemplateTarea;
	
	@Autowired
	@Qualifier("jmsTemplateTaskTopic")
	JmsTemplate jmsTemplateTareaCopia;

	
	public MessageTaskImpl(JmsTemplate jmsTemplateTarea) {
		this.jmsTemplateTarea = jmsTemplateTarea;
	}

	@Override
	public void send(final Tarea msg) {
		System.out.print(msg.getUrls());
		
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {		
			
			final String jsonMsg = ow.writeValueAsString(msg);
			jmsTemplateTarea. send(new MessageCreator() {
				
				@Override
				public Message createMessage(Session session) throws JMSException {
					ObjectMessage message=session.createObjectMessage();
					message.setObject(msg);
					return message;
				}
			});
			jmsTemplateTareaCopia.convertAndSend(jsonMsg);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
