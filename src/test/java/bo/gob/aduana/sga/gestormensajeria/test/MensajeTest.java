package bo.gob.aduana.sga.gestormensajeria.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bo.gob.aduana.sga.gestormensajeria.model.Mensaje;
import bo.gob.aduana.sga.gestormensajeria.service.MensajeService;
import bo.gob.aduana.sga.gestormensajeria.service.MessageSender;
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

}
