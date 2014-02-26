package bo.gob.aduana.sga.gestormensajeria.jms;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import bo.gob.aduana.sga.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.service.impl.MensajeServiceImpl;
import bo.gob.aduana.sga.gestormensajeria.service.impl.TaskServiceImpl;

public class TaskReceiver implements MessageListener {

	@Autowired
	TaskServiceImpl taskimpl;

	public void onMessage(Message message) {

		if (message instanceof TextMessage) {
			final TextMessage textMessage = (TextMessage) message;
			String text = null;
			try {
				text = textMessage.getText();
			} catch (JMSException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			JSONObject json = null;

			try {
				json = new JSONObject(text);
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Tarea tarea;
			try {

				Calendar c = Calendar.getInstance();
				Date date = c.getTime();
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				String currentDate = format.format(date);
				
				String eventStr=json.get("accion").toString();
				StringTokenizer eventoTkn=new StringTokenizer(eventStr,",");
				String componenteButtonInit="<div class='btn-group'><button type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>Accion<span class='caret'></span></button><ul class='dropdown-menu' role='menu'>";
				String componenteButtonEnd="</ul></div>";
				String componenteURLInit="<li><a href='#'>";
				String componenteURLEnd="</a></li>";
				
				String object = "";
				while (eventoTkn.hasMoreElements()) {
					object=object.concat(componenteURLInit+(String)eventoTkn.nextElement());
					object=object.concat(componenteURLEnd);
					
				}
				String botonStr=object.concat(componenteButtonEnd);
				String accionStr=componenteButtonInit.concat(botonStr);

				tarea = new Tarea(json.get("tipo").toString().toUpperCase(), 
						json.get("remitente").toString(), 
						null, 
						currentDate, 
						json.get("cuerpo").toString(), 
						null, 
						json.get("destinatario").toString(), 
						json.get("url").toString(), 
						accionStr, 
						json.get("proceso").toString(),
						json.get("tipoTramite").toString(), 
						json.get("nroTramite").toString(), 
						json.get("estado").toString(),
						null,
						null);
				try {
					taskimpl.crear(tarea);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
