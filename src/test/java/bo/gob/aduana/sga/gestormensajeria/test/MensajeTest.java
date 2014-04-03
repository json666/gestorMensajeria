package bo.gob.aduana.sga.gestormensajeria.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import bo.gob.aduana.sga.gestormensajeria.utils.JsonRest;
import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;
import com.google.inject.internal.util.$SourceProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bo.gob.aduana.sga.core.gestormensajeria.model.Mensaje;
import bo.gob.aduana.sga.core.gestormensajeria.model.Opcion;
import bo.gob.aduana.sga.core.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.core.gestormensajeria.model.TipoMensaje;
import bo.gob.aduana.sga.gestormensajeria.service.EmailNotificationService;
import bo.gob.aduana.sga.gestormensajeria.service.MensajeService;
import bo.gob.aduana.sga.gestormensajeria.service.MessageSender;
import bo.gob.aduana.sga.gestormensajeria.service.MessageTask;
import bo.gob.aduana.sga.gestormensajeria.service.TareaService;
import bo.gob.aduana.sga.gestormensajeria.test.utils.SpringMongoConfig;

/**
 * @author jsanchez
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testDispatcher.xml"})
public class MensajeTest {
    // Carga de la conexion a MongoDB y cargar el DAO de prueba
    private final ApplicationContext context = new AnnotationConfigApplicationContext(
            SpringMongoConfig.class);
    private final MongoOperations mongoOperations = (MongoOperations) context
            .getBean("mongoTemplate");

    @Autowired
    MessageSender<Mensaje> msg;
//
//	@Autowired
//	MensajeService msl;

    @Autowired
    MessageTask<Tarea> task;

    @Autowired
    TareaService tsl;

    @Autowired
    EmailNotificationService emailService;


    @Test
    public void testListTarea() throws ClassNotFoundException, SQLException {

        List<Tarea> mensajeListTarea = tsl.listAll();
        System.out.println("LISTA DE TAREAS debe contener elementos: "
                + mensajeListTarea.size());
        for (Iterator iterator = mensajeListTarea.iterator(); iterator
                .hasNext(); ) {
            Tarea tarea = (Tarea) iterator.next();
            System.out.println("-------------------o----------------------");
            System.out.println("value 1:" + tarea.getId_usuario());
            System.out.println("value 2:" + tarea.getTipo().toString());

        }
    }

    @Test
    public void testListarPorUsuario() {
        List<Tarea> mensajeListTarea = tsl.listbyIdUser("1111");
        System.out.println("LISTA DE TAREAS debe contener elementos: "
                + mensajeListTarea.size());
        for (Iterator iterator = mensajeListTarea.iterator(); iterator
                .hasNext(); ) {
            Tarea tarea = (Tarea) iterator.next();
            System.out.println("-------------------o----------------------");
            System.out.println("value 1:" + tarea.getId_usuario());
            System.out.println("value 2:" + tarea.getTipo());

        }
    }


    @Test
    public void testListarUsuarioSucursal() {
        List<Tarea> mensajeListTarea = tsl.findByUserAndRolSucursal("SANTA CRUZ", "ANALISTA", "A123456");
        System.out.println("LISTA DE TAREAS debe contener elementos: "
                + mensajeListTarea.size());
        for (Iterator iterator = mensajeListTarea.iterator(); iterator
                .hasNext(); ) {
            Tarea tarea = (Tarea) iterator.next();
            System.out.println("-------------------o----------------------");
            System.out.println("value 1:" + tarea.getId_usuario());
            System.out.println("value 2:" + tarea.getSucursal());
            System.out.println("value 3:" + tarea.getRol());
            System.out.println("value 4:" + tarea.getRemitente());
            System.out.println("value 5:" + tarea.getTipoTramite());

        }
    }

    @Test
    public void testSendTarea() {
        List<Opcion> opciones = new ArrayList<Opcion>();
        Opcion opc = new Opcion();
        opc.setLink("http://127.0.0.1/oce/listener.html#/listener/verCarpeta&123456789/oce/");
        opc.setTextLink("Ver Tarea");
        opciones.add(opc);
        Opcion opc1 = new Opcion();
        opc1.setLink("http://127.0.0.1/oce/listener.html#/listener/procesar&123766789/oce/");
        opc1.setTextLink("Procesar");
        opciones.add(opc1);

        Tarea tarea = new Tarea(TipoMensaje.NOTIFICACION,
                "juan adolfo",
                "31/03/2013",
                "esto es una prueba de registro tarea",
                "oscar calderon",
                opciones,
                "Padron de Operadores",
                "Actualizacion de datos",
                "",
                TipoMensaje.ESTADO_EN_ESPERA,
                "analista",
                "beni",
                "9999CC",
                "IMPORTADORES MUNDO SHOW");
        System.out.println("OBJETO TAREA");
        for(int i=0;i<=5;i++){
            task.send(tarea);
        }

        System.out.println("Mensaje creado es: " + task);
    }


    @Test
    public void sendNotificacion() {
        Mensaje mensaje = new Mensaje(TipoMensaje.CORREO,
                "remitente",
                "Esto es una prueba de envio de notificacion",
                "destinatario",
                "nro_tramite",
                "tipo tramite",
                "asunto mensaje",
                "02/04/2014",
                "588533333444",
                "Por definir");
        for (int i = 1; i < 20; i++) {
            JsonResult jsonResult = msg.send(mensaje);
            System.out.println("Respuesta:" + jsonResult.getSuccess());
        }

    }

    @Test
    public void listarRol() {
        List<Tarea> mensajeListTarea = (List<Tarea>) tsl.findByRol("ANALISTA");
        System.out.println("LISTA DE TAREAS debe contener elementos: "
                + mensajeListTarea.size());
        for (Iterator iterator = mensajeListTarea.iterator(); iterator
                .hasNext(); ) {
            Tarea tarea = (Tarea) iterator.next();
            System.out.println("-------------------o----------------------");
            System.out.println("value 1:" + tarea.getId_usuario());
            System.out.println("value 2:" + tarea.getSucursal());
            System.out.println("value 3:" + tarea.getRol());
            System.out.println("value 4:" + tarea.getRemitente());
            System.out.println("value 5:" + tarea.getTipoTramite());

        }

    }

    //@Test
    public void testModificarTarea() {
        List<Opcion> opciones = new ArrayList<Opcion>();
        Opcion opc = new Opcion();
        opc.setLink("http://127.0.0.1/oce/listener.html#/listener/verCarpeta&123456789/oce/");
        opc.setTextLink("Ver Tarea");
        opciones.add(opc);
        Opcion opc1 = new Opcion();
        opc1.setLink("http://127.0.0.1/oce/listener.html#/listener/procesar&123766789/oce/");
        opc1.setTextLink("Procesar");
        opciones.add(opc1);
        System.out.println("OBJETO TAREA");
        Tarea tareaPrincipal = (Tarea) tsl.findById("d920cb98-7736-4021-8a8c-77b902cc4b6e");
        tareaPrincipal.setEstado("OBSERVADO");
        tareaPrincipal.setRol("plataforma");
        System.out.println("DES:" + tareaPrincipal.getDestinatario());
        System.out.println("EST:" + tareaPrincipal.getEstado());
        System.out.println("ID:" + tareaPrincipal.getId());
        System.out.println("PROC:" + tareaPrincipal.getProceso());
        System.out.println("ROL:" + tareaPrincipal.getRol());
        task.send(tareaPrincipal);

    }


    //@Test
    public void modificarTareaEstado() {
        Tarea tarea = tsl.findById("49cf426e-feb3-4cae-a9a7-f4a52d117831");
//		Tarea tarea = new Tarea(
//				TipoMensaje.NOTIFICACION,
//				"osvalo contreras", 
//				"13/03/2013",
//				"marco cardenas ",
//				"gerson veramendi", 
//				opciones,
//				"ver proceso, ver carpeta",
//				"PROCESAR", 
//				"343434", 
//				"REVISADO", 
//				"analista", 
//				"LA PAZ", 
//				"12aa33bb00cv");
//		tarea.setId("18905437-7fd9-4bda-8518-bd3f2bca3d45");
        tarea.setEstado("PENDIENTE");
        System.out.println("OBJETO TAREA");
        task.send(tarea);
        System.out.println("Mensaje creado es: " + task);
    }

    @Test
    public void paginadorTarea() {
        System.out.println("Cargando........");
        JsonResult jsonResult = null;
        //List<Tarea> listaTarea=tsl.findAll("GGGGG32323235555555551111G33",3);
        jsonResult = tsl.findAll("223vv", 0);
        if (jsonResult.getSuccess()) {
            List<Tarea> listaTarea = (List<Tarea>) jsonResult.getResult();
            System.out.println("Tamanio:"+listaTarea.size());
            for (Tarea tarea : listaTarea) {
                System.out.println("+-+" + tarea.getId_usuario() + "+-+" + "+-+" + tarea.getId() + "+-+" + "+-+" + "+-+" + tarea.getDestinatario() + "+-+"+ "+-+" + tarea.getRol() + "+-+"+ tarea.getEstado() + "+-+"+ tarea.getFechaRegistro() + "+-+");
            }
        }else{
            System.out.println(jsonResult.getResult().toString());
        }
    }

    @Test
    public void paginadorTareaRol() {
        System.out.println("Cargando........");
        JsonResult jsonResult = null;
        //List<Tarea> listaTarea=tsl.findAll("GGGGG32323235555555551111G33",3);
        jsonResult = tsl.findByRolPaging("analista", 0);
        if (jsonResult.getSuccess()) {
            List<Tarea> listaTarea = (List<Tarea>) jsonResult.getResult();
            System.out.println("Tamanio:"+listaTarea.size());
            for (Tarea tarea : listaTarea) {
                System.out.println("+-+" + tarea.getId_usuario() + "+-+" + "+-+" + tarea.getId() + "+-+" + "+-+" + "+-+" + tarea.getDestinatario() + "+-+"+ "+-+" + tarea.getRol() + "+-+"+ tarea.getEstado() + "+-+"+ tarea.getFechaRegistro() + "+-+");
            }
        }else{
            System.out.println(jsonResult.getResult().toString());
        }
    }

    @Test
    public void listarTareaRolEstado() {
        System.out.println("Cargando........");
        JsonResult jsonResult = null;
        List<Tarea> listaTarea = tsl.findByRolEstado("ANALISTA", "registrado");
        if (listaTarea.size() > 0)
            for (Tarea tarea : listaTarea) {
                System.out.println("+-+" + tarea.getId_usuario() + "+-+" + "+-+" + tarea.getId() + "+-+" + "+-+" + "+-+" + tarea.getDestinatario() + "+-+" + "+-+" + tarea.getRol() + "+-+" + tarea.getEstado() + "+-+" + tarea.getFechaRegistro() + "+-+" + tarea.getRazonSocial());
            }
        else {
            System.out.println("No se encontraron registros.....");
        }


    }
    @Test
    public void paginadorTareaRolEstado() {
        System.out.println("Cargando........");
        JsonResult jsonResult = null;
        //List<Tarea> listaTarea=tsl.findAll("GGGGG32323235555555551111G33",3);
        jsonResult = tsl. findByRolEstadoPaginado("plataformo", TipoMensaje.ESTADO_EN_ESPERA, 1);
        if (jsonResult.getSuccess()) {
            List<Tarea> listaTarea = (List<Tarea>) jsonResult.getResult();
            System.out.println("Tamanio:"+listaTarea.size());
            for (Tarea tarea : listaTarea) {
                System.out.println("+-+" + tarea.getId_usuario() + "+-+" + "+-+" + tarea.getId() + "+-+" + "+-+" + "+-+" + tarea.getDestinatario() + "+-+"+ "+-+" + tarea.getRol() + "+-+"+ tarea.getEstado() + "+-+"+ tarea.getFechaRegistro() + "+-+");
            }
        }else{
            System.out.println(jsonResult.getResult().toString());
        }
    }

    @Test
    public void desabilitarTarea() {
        System.out.println("Cargando........");
        JsonResult jsonResult = null;
        jsonResult=task.disableTask("4abcfe43-9106-4cdb-af64-1e7aa7ac62f9");
        System.out.println(jsonResult.getResult());
    }

    /*@Test
    public void paginadorMensajes() {
        System.out.println("Cargando........");
        JsonResult jsonResult = null;
        //List<Tarea> listaTarea=tsl.findAll("GGGGG32323235555555551111G33",3);
        jsonResult = tsl.findAll("GGGGG32323235555555551111G33", 1);
        if (jsonResult.getSuccess()) {
            List<Mensaje> listaMensaje = (List<Mensaje>) jsonResult.getResult();
            for (Mensaje mensaje : listaMensaje) {
                System.out.println("+-+" + mensaje.getId_usuario() + "+-+" + "+-+" + mensaje.getId() + "+-+" + "+-+" + "+-+" + mensaje.getCuerpo() + "+-+");
            }
        }else{
            System.out.println(jsonResult.getResult().toString());
        }
    }*/

    /*@Test
    public void paginadorMensajes() {
        System.out.println("Cargando........");
        JsonResult jsonResult = null;
        //List<Tarea> listaTarea=tsl.findAll("GGGGG32323235555555551111G33",1);
        jsonResult = tsl.findByRolPaging("ANALISTA", 7);
        if (jsonResult.getSuccess()) {
            List<Tarea> listaTarea = (List<Tarea>) jsonResult.getResult();
            for (Tarea tarea : listaTarea) {
                System.out.println("+-+" + tarea.getId_usuario() + "+-+" + "+-+" + tarea.getId() + "+-+" + "+-+" + "+-+" + tarea.getDestinatario() + "+-+"+ "+-+" + tarea.getRol() + "+-+"+ tarea.getEstado() + "+-+"+ tarea.getFecha() + "+-+");
            }
        }else{
            System.out.println(jsonResult.getResult().toString());
        }
    }*/

}
