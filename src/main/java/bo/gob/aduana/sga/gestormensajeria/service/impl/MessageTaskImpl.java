package bo.gob.aduana.sga.gestormensajeria.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;

import bo.gob.aduana.sga.gestormensajeria.service.MessageTask;

public class MessageTaskImpl implements MessageTask<String> {
	
	@Autowired
	@Qualifier("jmsTemplateTarea")
	JmsTemplate jmsTemplateTarea;
	
	@Autowired
	@Qualifier("jmsTemplateTareaCopia")
	JmsTemplate jmsTemplateTareaCopia;

	public MessageTaskImpl(JmsTemplate jmsTemplateTarea) {
		this.jmsTemplateTarea = jmsTemplateTarea;
	}

	@Override
	public void send(String msg) {
		jmsTemplateTareaCopia.convertAndSend(msg);
		jmsTemplateTarea.convertAndSend(msg);
		
	}

}
