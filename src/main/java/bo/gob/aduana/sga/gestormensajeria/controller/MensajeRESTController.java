package bo.gob.aduana.sga.gestormensajeria.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bo.gob.aduana.sga.core.gestormensajeria.model.Mensaje;
import bo.gob.aduana.sga.core.gestormensajeria.model.Opcion;
import bo.gob.aduana.sga.core.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.service.MensajeService;
import bo.gob.aduana.sga.gestormensajeria.service.MessageSender;
import bo.gob.aduana.sga.gestormensajeria.service.MessageTask;
import bo.gob.aduana.sga.gestormensajeria.service.TareaService;
import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;

@Controller
@RequestMapping(value = "/rest")
public class MensajeRESTController {

	@Autowired
	private MensajeService mensajesService;

	@Autowired(required = true)
	private MessageSender<Mensaje> messageSender;

	@Autowired
	private TareaService tareaService;

	@Autowired(required = true)
	private MessageTask<Tarea> messageTask;


	@RequestMapping(value = "/mensajes", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listAll() {
		return new JsonResult(true, mensajesService.listAll());
	}

	@RequestMapping(value = "/mensajes/cliente/{id_user}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listEmailByUser(@PathVariable String id_user) {
		return new JsonResult(true, mensajesService.findByUser(id_user));
	}

	@RequestMapping(value = "/enviar", method = RequestMethod.POST, headers = "Content-Type=application/json")
	public @ResponseBody
	JsonResult create(@RequestBody Mensaje message) {
		System.out
				.println("************************Enviando Mensaje**************");
		messageSender.send(message);
		return new JsonResult(true, null);
	}

	@RequestMapping(value = "/enviar/tarea", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult createTask(@RequestBody String message) {
		System.out
				.println("************************Enviando Mensaje**************");
		// messageTask.send(message);
		return new JsonResult(true, null);
	}

	@RequestMapping(value = "/tareas", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listAllTask() {

		List<Tarea> list = tareaService.listAll();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Tarea tarea = (Tarea) iterator.next();
			System.out.println("URL=" + tarea.getUrls() + "++++++++++");
		}

		return new JsonResult(true, tareaService.listAll());
	}

	@RequestMapping(value = "/tareas/cliente/{id_user}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listByUser(@PathVariable String id_user) {
		return new JsonResult(true, tareaService.listbyIdUser(id_user));
	}

	@RequestMapping(value = "/tareas/cliente/{sucursal}/{rol}/{id_user}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listByUser(@PathVariable String sucursal,
			@PathVariable String rol, @PathVariable String id_user) {
		return new JsonResult(true, tareaService.findByUserAndRolSucursal(
				sucursal, rol, id_user));
	}

	@RequestMapping(value = "/tarea", method = RequestMethod.POST, headers = "Content-Type=application/json")
	@ResponseBody
	public JsonResult creaTarea(@RequestBody Tarea tarea) {
		System.out.println("ingresando al servicio..........................");
		if (tarea.getUrls() != null) {

			List<Opcion> opciones = tarea.getUrls();
			for (Iterator iterator = opciones.iterator(); iterator.hasNext();) {
				Opcion opcion = (Opcion) iterator.next();
				System.out.println("URL" + opcion.getLink());

			}
			System.out.println("Tarea:" + tarea.getUrls());
			messageTask.send(tarea);
			return new JsonResult(true, null);
		} else {
			System.out.println("sin URL...");
			messageTask.send(tarea);
			return new JsonResult(false, null);
		}
	}
	
	/*
	 * Descripcion:Filtrar tarea por Rol
	 * Author:Jheyson Sanchez
	 * Fecha:10/03/2014
	 */
	@RequestMapping(value = "/tareas/rol/{rol}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listByRol(@PathVariable String rol) {
		return new JsonResult(true, tareaService.findByRol(rol));
	}
	
	

	@RequestMapping(value = "/edit/rol/tarea", method = RequestMethod.POST, headers = "Content-Type=application/json")
	@ResponseBody
	public JsonResult modificarTarea(@RequestBody Tarea tarea) {
		System.out.println("ingresando al servicio..........................");
		JsonResult jsonResult=null;
		if (tarea.getId() != null) {

			System.out.println("Tarea:" + tarea.getUrls());
			messageTask.send(tarea);
			jsonResult= new JsonResult(true, null);
			
		} else {
			System.out.println("sin URL...");
			messageTask.send(tarea);
			jsonResult= new JsonResult(false, null);

		}
		return jsonResult;
	}
	
	/*
	 * Author:Jheyson Sanchez
	 * Descripcion: Actualiza el estado de la TAREA
	 */
	
	@RequestMapping(value = "/edit/tarea",  method = RequestMethod.POST, headers = "Content-Type=application/json")
	@ResponseBody
	public JsonResult modificarTareaEstado(@RequestBody Tarea tarea) {
		System.out.println("ingresando al servicio..........................");
		Tarea tareaPrincipal=(Tarea)tareaService.findById(tarea.getId());
		JsonResult jsonResult=null;
		if (tareaPrincipal.getId() != null) {
			tareaPrincipal.setEstado(tarea.getEstado().toUpperCase());
			messageTask.send(tareaPrincipal);
			jsonResult= new JsonResult(true, null);
			
		} else {
			jsonResult= new JsonResult(false, null);

		}
		return jsonResult;
	}
	

	
	

}
