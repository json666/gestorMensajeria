package bo.gob.aduana.sga.gestormensajeria.service.impl;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;

import bo.gob.aduana.sga.core.gestormensajeria.model.Mensaje;
import bo.gob.aduana.sga.gestormensajeria.service.MessageSender;

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

	public void send(Mensaje msg) {

		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		try {
			String jsonMsg = ow.writeValueAsString(msg);
			jmsTemplate.convertAndSend(msg);
			jmsTemplateCopy.convertAndSend(jsonMsg);

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
