package bo.gob.aduana.sga.gestormensajeria.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import bo.gob.aduana.sga.core.gestormensajeria.model.Tarea;
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
				System.out.println("ID="+tarea.getId());
				
				if(tarea.getId()!=null){
					System.out.println("Modificando objeto............................");
					taskimpl.modificar(tarea);
				}else{
					System.out.println("Creando objeto............................");
					taskimpl.crear(tarea);
				}
				
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		

	}

}
