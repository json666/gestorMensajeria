package bo.gob.aduana.sga.gestormensajeria.jms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import bo.gob.aduana.sga.gestormensajeria.model.Mensaje;
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

		try {
			ObjectMessage objectMessage=(ObjectMessage)message;
			System.out.println("Test..........");
				
				Mensaje mensaje = (Mensaje) objectMessage.getObject();
				System.out.println("tarea:"+mensaje.getId_usuario());

				mensajeimpl.crear(mensaje);
				

			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
