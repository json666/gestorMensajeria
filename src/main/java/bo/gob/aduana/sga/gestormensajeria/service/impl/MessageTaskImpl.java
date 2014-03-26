package bo.gob.aduana.sga.gestormensajeria.service.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import bo.gob.aduana.sga.core.gestormensajeria.model.Opcion;
import bo.gob.aduana.sga.gestormensajeria.service.TareaService;
import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;
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

    @Autowired
    private TareaService tareaService;

	
	public MessageTaskImpl(JmsTemplate jmsTemplateTarea) {
		this.jmsTemplateTarea = jmsTemplateTarea;
	}

	@Override
	public JsonResult send(Tarea msg) {
        String rolStr=msg.getRol().toUpperCase();
        final Tarea tarea=msg;
        tarea.setRol(rolStr);
        tarea.setTipo(msg.getTipo().toUpperCase());
        JsonResult jsonResult= new JsonResult();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
            if (tarea.getUrls() != null) {
                final String jsonMsg = ow.writeValueAsString(tarea);
                jmsTemplateTarea. send(new MessageCreator() {

                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        ObjectMessage message=session.createObjectMessage();
                        message.setObject(tarea);
                        return message;
                    }
                });
                jmsTemplateTareaCopia.convertAndSend(jsonMsg);
                jsonResult.setResult("Mensaje Tarea fue enviado Satisfactoriamene.");
                jsonResult.setSuccess(true);
            } else {
                System.out.println("sin URL...");
                jsonResult.setResult("Por lo menos deberia tenerse un URL para realizar una accion.");
                jsonResult.setSuccess(false);
            }
		} catch (JsonGenerationException e) {
            jsonResult.setResult("Mensaje Tarea no fue enviado");
            jsonResult.setSuccess(false);
			e.printStackTrace();
		} catch (JsonMappingException e) {
            jsonResult.setResult("Mensaje Tarea no fue enviado");
            jsonResult.setSuccess(false);
			e.printStackTrace();
		} catch (IOException e) {
            jsonResult.setResult("Mensaje Tarea no fue enviado");
            jsonResult.setSuccess(false);
			e.printStackTrace();
		} catch (Exception e) {
            jsonResult.setResult("Mensaje Tarea no fue enviado");
            jsonResult.setSuccess(false);
			e.printStackTrace();
		}
		return jsonResult;
		
	}

    @Override
    public JsonResult edit(Tarea msg) {
        System.out.println("modificando tarea..........................");
        JsonResult jsonResult = null;
        String jsonStr = null;
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            Tarea tareaPrincipal = (Tarea) tareaService.findById(msg.getId());
            if (tareaPrincipal.getId() != null) {
                tareaPrincipal.setEstado(msg.getEstado().toUpperCase());
                jsonStr = ow.writeValueAsString(tareaPrincipal);
                jmsTemplateTarea.convertAndSend(tareaPrincipal);
                jmsTemplateTareaCopia.convertAndSend(jsonStr);
                jsonResult = new JsonResult(true, "La modificacion se realizo satisfactoriamente");
            } else {
                jsonResult = new JsonResult(false, "No se puede realizar los cambios de estado, ya que no se cuenta con un ID.");
            }

        } catch (IOException e) {
            jsonResult = new JsonResult(false, "Existen problemas para realizar la modificacion de la tarea.");
            e.printStackTrace();
        } catch (Exception e) {
            jsonResult = new JsonResult(false, "Existen problemas para realizar la modificacion de la tarea.");
            e.printStackTrace();
        }
        return jsonResult;
    }

}
