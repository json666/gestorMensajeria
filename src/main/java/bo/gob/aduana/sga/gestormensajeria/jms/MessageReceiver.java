package bo.gob.aduana.sga.gestormensajeria.jms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import bo.gob.aduana.sga.gestormensajeria.service.impl.MensajeServiceImpl;


/*
 * Autor:jheyson sanchez
 * fecha: 21/02/2014
 * descripcion:Formato de Fecha
 * 
 */
public class MessageReceiver implements MessageListener {

	@Autowired
	MensajeServiceImpl mensajeimpl;

	public void onMessage(final Message message) {

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
			bo.gob.aduana.sga.gestormensajeria.model.Mensaje messageE;
			
			try {
				
				Calendar c = Calendar.getInstance();
				Date date=c.getTime();
				SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String currentDate=format.format(date);
				
				messageE = new bo.gob.aduana.sga.gestormensajeria.model.Mensaje(
						json.get("tipo").toString(), 
						json.get("remitente").toString(), 
						null, 
						currentDate, 
						json.get("cuerpo").toString(), 
						null, 
						json.get("estado").toString(), 
						json.get("destinatario").toString(), 
						json.get("ntramite").toString());
				try {
					mensajeimpl.crear(messageE);
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
