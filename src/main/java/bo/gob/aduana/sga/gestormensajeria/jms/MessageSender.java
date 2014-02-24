package bo.gob.aduana.sga.gestormensajeria.jms;

import org.springframework.jms.core.JmsTemplate;
 
public class MessageSender {
 
    private final JmsTemplate jmsTemplate;
 
        
    public MessageSender(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
 
    public void send(final String msg) {
        jmsTemplate.convertAndSend(msg);
        
    }
    
 
 
}