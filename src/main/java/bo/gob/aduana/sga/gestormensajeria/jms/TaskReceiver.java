package bo.gob.aduana.sga.gestormensajeria.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;

import bo.gob.aduana.sga.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.service.impl.TaskServiceImpl;

public class TaskReceiver implements MessageListener {

	@Autowired
	TaskServiceImpl taskimpl;
	
//	public TaskReceiver(){
//		try {
//			System.out.print("MODIFICANDO ADVISORYSUPPORT...................................");
//            //This message broker is embedded
//            BrokerService broker = new BrokerService();
//            broker.setPersistent(true);
//            broker.setUseJmx(true);
//            broker.setAdvisorySupport(false);
//            broker.addConnector("tcp://localhost:61616");
//            broker.start();
//            System.out.print("ADVISORYSUPPORT INICIANDO...................................");
//        } catch (Exception e) {
//            //Handle the exception appropriately
//        }
//	}

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
