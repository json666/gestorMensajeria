package bo.gob.aduana.sga.gestormensajeria.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bo.gob.aduana.sga.gestormensajeria.model.Mensaje;
import bo.gob.aduana.sga.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.service.MensajeService;
import bo.gob.aduana.sga.gestormensajeria.service.MessageSender;
import bo.gob.aduana.sga.gestormensajeria.service.MessageTask;
import bo.gob.aduana.sga.gestormensajeria.service.TareaService;
import bo.gob.aduana.sga.gestormensajeria.test.utils.SpringMongoConfig;

/**
 * 
 * @author mcardenas
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/testDispatcher.xml" })
public class MensajeTest {
	// Carga de la conexion a MongoDB y cargar el DAO de prueba
	private final ApplicationContext context = new AnnotationConfigApplicationContext(
			SpringMongoConfig.class);
	private final MongoOperations mongoOperations = (MongoOperations) context
			.getBean("mongoTemplate");
	// private final PruebaDao pruebaDao = new PruebaDao(mongoOperations);

	// Cargar Conexion a MongoDB y Services para DECLARACION
	@Autowired
	MessageSender<String> msg;
	// private MessageReceiver msgreciver;

	@Autowired
	MensajeService msl;

	@Autowired
	MessageTask<String> task;

	@Autowired
	TareaService tsl;

	@Test
	public void sendMensaje() {

		// String mg=
		// "{\"remitente\" : \"Marco Test\",\"cuerpo\" : \"Esto es una prueba\",\"time\" : null,\"estado\" : \"ok\",\"fecha\" : \"2014-01-23 17:19:57\",\"tipo\" : \"email\",\"destinatario\" : \"Proyecto\"}";
		String mg = "{\"remitente\" : \"Marco Test\",\"cuerpo\" : \"Esto es una prueba\",\"time\" : null,\"estado\" : \"ok\",\"fecha\" : \"2014-01-23 17:19:57\",\"tipo\" : \"email\",\"destinatario\" : \"Proyecto\",\"ntramite\":\"2014/201/C-1011\"}";
		msg.send(mg);

		// assertNotNull(true);
		System.out.println("Mensaje creado es: " + msg);

	}

	@Test
	public void testList() throws ClassNotFoundException, SQLException {

		// Listar los documentos
		System.out.print("sssssssssssssssssssssssssss" + msl);
		List<Mensaje> mensajeList = msl.listAll();
		// assertTrue("La lista debe estar vacia", msgli.size() == 0);
		System.out.println("LISTA DE PRUEBAS debe contener 0 elementos: "
				+ mensajeList.size());

	}

	/*
	 * tarea = new Tarea(json.get("tipo").toString(),
	 * json.get("remitente").toString(), null, currentDate,
	 * json.get("cuerpo").toString(), null, json.get("destinatario").toString(),
	 * json.get("url").toString(), json.get("evento").toString(),
	 * json.get("proceso").toString(), json.get("tipoTramite").toString(),
	 * json.get("nroTramite").toString(), json.get("estado").toString());
	 */
	@Test
	public void enviarMensajeTarea() {
		String mg = "{\"tipo\" : \"Actualizacipon de datos\",\"remitente\" : \"jheyson sanchez\",\"tiempo\" : null,\"fecha\" : \"\",\"cuerpo\":\"queue-topic\",\"destinatario\" : \"Banco ganadera S.A.\",\"url\" : \"www.altavista.com\",\"accion\" : \"procesar\",\"proceso\" : \"Padron de Operadores\",\"tipoTramite\" : \"Actualizacion de Operadores\",\"nroTramite\" : \"2014-231-0156\",\"estado\" : \"Observado\",\"rol\":\"analista\",\"sucursal\":\"la paz\",\"id_usuario\":\"A1234567\"}";
		task.send(mg);
	}

	@Test
	public void testListTarea() throws ClassNotFoundException, SQLException {

		List<Tarea> mensajeListTarea = tsl.listAll();
		System.out.println("LISTA DE TAREAS debe contener elementos: "
				+ mensajeListTarea.size());
		for (Iterator iterator = mensajeListTarea.iterator(); iterator
				.hasNext();) {
			Tarea tarea = (Tarea) iterator.next();
			System.out.println("-------------------o----------------------");
			System.out.println("value 1:" + tarea.getId_usuario());
			System.out.println("value 2:" + tarea.getAccion());
			System.out.println("value 2:" + tarea.getAccion());

		}

		
	}
	
	@Test
	public void testListarPorUsuario(){
		List<Tarea> mensajeListTarea = tsl.listbyIdUser("");
		System.out.println("LISTA DE TAREAS debe contener elementos: "
				+ mensajeListTarea.size());
		for (Iterator iterator = mensajeListTarea.iterator(); iterator
				.hasNext();) {
			Tarea tarea = (Tarea) iterator.next();
			System.out.println("-------------------o----------------------");
			System.out.println("value 1:" + tarea.getId_usuario());
			System.out.println("value 2:" + tarea.getAccion());
			System.out.println("value 2:" + tarea.getAccion());

		}
	}

}
