package bo.gob.aduana.sga.gestormensajeria.jms;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.chemistry.opencmis.commons.impl.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import bo.gob.aduana.sga.gestormensajeria.excepciones.ValidacionException;
import bo.gob.aduana.sga.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.service.impl.MensajeServiceImpl;
import bo.gob.aduana.sga.gestormensajeria.service.impl.TaskServiceImpl;

public class TaskReceiver implements MessageListener {

	@Autowired
	TaskServiceImpl taskimpl;

	public void onMessage(Message message) {
		System.out.println("Message="+message);
		
		try {
			ObjectMessage objectMessage=(ObjectMessage)message;
			System.out.println("Test..........");
				
				Tarea tarea = (Tarea) objectMessage.getObject();
				System.out.println("tarea:"+tarea.getId_usuario());

				taskimpl.crear(tarea);
				

			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		

	}

}
