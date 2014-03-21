package bo.gob.aduana.sga.gestormensajeria.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import bo.gob.aduana.sga.gestormensajeria.model.Mensaje;
import bo.gob.aduana.sga.gestormensajeria.service.MessageSender;
								

public class MessageSenderEmailImpl implements MessageSender<Mensaje> {
	
	@Autowired
	@Qualifier("jmsTemplate")
	JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("jmsTemplateCopy")
	JmsTemplate jmsTemplateCopy;

	
		
	public MessageSenderEmailImpl(final JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;

	}

	public void send(Mensaje msg) {
		System.out.print("Enviando mensaje=="+msg+"TEM="+jmsTemplate);
		jmsTemplate.convertAndSend(msg);
		jmsTemplateCopy.convertAndSend(msg);
		
		
	}

}
