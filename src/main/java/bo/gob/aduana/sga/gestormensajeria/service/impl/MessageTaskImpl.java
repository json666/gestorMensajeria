package bo.gob.aduana.sga.gestormensajeria.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;

import bo.gob.aduana.sga.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.service.MessageTask;

public class MessageTaskImpl implements MessageTask<Tarea> {
	
	@Autowired
	@Qualifier("jmsTemplateTarea")
	JmsTemplate jmsTemplateTarea;
	/*
	@Autowired
	@Qualifier("jmsTemplateTareaCopia")
	JmsTemplate jmsTemplateTareaCopia;
*/
	public MessageTaskImpl(JmsTemplate jmsTemplateTarea) {
		this.jmsTemplateTarea = jmsTemplateTarea;
	}

	@Override
	public void send(Tarea msg) {
		System.out.println("Tarea="+msg.getNroTramite());
		System.out.print(jmsTemplateTarea);
		//jmsTemplateTarea.convertAndSend(msg);
		jmsTemplateTarea.convertAndSend("sga.tarea.queue", msg);
		//jmsTemplateTareaCopia.convertAndSend(msg);
		
	}

}
