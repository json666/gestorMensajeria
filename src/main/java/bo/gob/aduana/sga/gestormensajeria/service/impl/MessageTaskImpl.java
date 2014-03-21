package bo.gob.aduana.sga.gestormensajeria.service.impl;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;

import bo.gob.aduana.sga.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.service.MessageTask;

public class MessageTaskImpl implements MessageTask<Tarea> {
	
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
	public void send(Tarea msg) {
		System.out.print(msg.getUrls());
		
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
//			BrokerService broker = new BrokerService();
//			broker.setAdvisorySupport(false);
//			broker.start();
			
			String jsonMsg = ow.writeValueAsString(msg);
			jmsTemplateTarea.convertAndSend(msg);
			jmsTemplateTareaCopia.convertAndSend(msg);
			
			;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
