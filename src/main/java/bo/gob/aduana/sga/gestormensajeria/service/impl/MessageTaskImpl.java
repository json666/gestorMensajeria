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
import bo.gob.aduana.sga.gestormensajeria.utils.TipoCriterio;
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
                jmsTemplateTarea.convertAndSend(tarea);
                jmsTemplateTareaCopia.convertAndSend(jsonMsg);
                jsonResult.setResult(TipoCriterio.CRITERIO_MENSAJE_TAREA_EXITOSA);
                jsonResult.setSuccess(true);
            } else {
                System.out.println("sin URL...");
                jsonResult.setResult(TipoCriterio.CRITERIO_VALIDACION_URL_TAREA);
                jsonResult.setSuccess(false);
            }
		} catch (JsonGenerationException e) {
            jsonResult.setResult(TipoCriterio.CRITERIO_MENSAJE_TAREA_ERROR);
            jsonResult.setSuccess(false);
			e.printStackTrace();
		} catch (JsonMappingException e) {
            jsonResult.setResult(TipoCriterio.CRITERIO_MENSAJE_TAREA_ERROR);
            jsonResult.setSuccess(false);
			e.printStackTrace();
		} catch (IOException e) {
            jsonResult.setResult(TipoCriterio.CRITERIO_MENSAJE_TAREA_ERROR);
            jsonResult.setSuccess(false);
			e.printStackTrace();
		} catch (Exception e) {
            jsonResult.setResult(TipoCriterio.CRITERIO_MENSAJE_TAREA_ERROR);
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
            System.out.println("-----------------ID TAREA:" + msg.getId() + "--------------------------");
            Tarea tareaPrincipal = (Tarea) tareaService.findById(msg.getId());
            if (tareaPrincipal.getId() != null) {
                if (msg.getEstado() != null) {
                    tareaPrincipal.setEstado(msg.getEstado());
                }
                if(msg.isDisabled()!=tareaPrincipal.isDisabled()){
                    tareaPrincipal.setDisabled(msg.isDisabled());
                }
                jsonStr = ow.writeValueAsString(tareaPrincipal);
                jmsTemplateTarea.convertAndSend(tareaPrincipal);
                jmsTemplateTareaCopia.convertAndSend(jsonStr);
                jsonResult = new JsonResult(true, TipoCriterio.CRITERIO_MENSAJE_TAREA_MODIFICACION_EXISTOSA);
            } else {
                jsonResult = new JsonResult(false, TipoCriterio.CRITERIO_MENSAJE_TAREA_MODIFICACION_ERROR_ID);
            }

        } catch (IOException e) {
            jsonResult = new JsonResult(false, TipoCriterio.CRITERIO_MENSAJE_TAREA_MODIFICACION_ERROR);
            e.printStackTrace();
        } catch (Exception e) {
            jsonResult = new JsonResult(false, TipoCriterio.CRITERIO_MENSAJE_TAREA_MODIFICACION_ERROR);
            e.printStackTrace();
        }
        return jsonResult;
    }

    @Override
    public JsonResult disableTask(String id) {
        JsonResult jsonResult = null;
        try {

            if (id != null) {
                Tarea tareaPrincipal = (Tarea) tareaService.findById(id);
                tareaPrincipal.setDisabled(true);
                jsonResult=tareaService.disableTask(tareaPrincipal);
            }else{
                jsonResult= new JsonResult(false,"El Registro no puede ser desabilitado.");
            }
        } catch (Exception e) {
            jsonResult= new JsonResult(false,"La Transaccion no puede ser procesada.");
            e.printStackTrace();
        }

        return jsonResult;
    }


}
