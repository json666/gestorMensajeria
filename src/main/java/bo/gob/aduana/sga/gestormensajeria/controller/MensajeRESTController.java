package bo.gob.aduana.sga.gestormensajeria.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bo.gob.aduana.sga.gestormensajeria.bean.MessageEmailBean;
import bo.gob.aduana.sga.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.service.EmailNotificationService;
import bo.gob.aduana.sga.gestormensajeria.service.MensajeService;
import bo.gob.aduana.sga.gestormensajeria.service.MessageSender;
import bo.gob.aduana.sga.gestormensajeria.service.MessageTask;
import bo.gob.aduana.sga.gestormensajeria.service.TareaService;
import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;

@Controller
@RequestMapping(value = "/rest")
public class MensajeRESTController {

	@Autowired
	private MensajeService mensajes;

	@Autowired(required = true)
	private MessageSender<String> messageSender;

	@Autowired
	private TareaService tarea;

	@Autowired(required = true)
	private MessageTask<Tarea> messageTask;

	/*@Autowired
	EmailNotificationService emailService;*/

	@RequestMapping(value = "/mensajes", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listAll() {
		return new JsonResult("success", mensajes.listAll(), null);
	}

	@RequestMapping(value = "/mensajes/cliente/{id_user}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listEmailByUser(@PathVariable String id_user) {
		return new JsonResult("success", mensajes.findByUser(id_user),
				"Procesado");
	}

	@RequestMapping(value = "/enviar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult create(@RequestBody String message) {
		System.out
				.println("************************Enviando Mensaje**************");
		messageSender.send(message);
		return new JsonResult("success", null, "Mensaje Enviado");
	}

	@RequestMapping(value = "/enviar/tarea", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult createTask(@RequestBody String message) {
		System.out
				.println("************************Enviando Mensaje**************");
		// messageTask.send(message);
		return new JsonResult("success", null, "Mensaje Enviado");
	}

	@RequestMapping(value = "/tareas", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listAllTask() {

		List<Tarea> list = tarea.listAll();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Tarea tarea = (Tarea) iterator.next();
			System.out.println("URL=" + tarea.getUrl() + "++++++++++");
		}

		return new JsonResult("success", tarea.listAll(), null);
	}

	@RequestMapping(value = "/tareas/cliente/{id_user}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listByUser(@PathVariable String id_user) {
		return new JsonResult("success", tarea.listbyIdUser(id_user),
				"Procesado");
	}

	@RequestMapping(value = "/tareas/cliente/{sucursal}/{rol}/{id_user}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listByUser(@PathVariable String sucursal,
			@PathVariable String rol, @PathVariable String id_user) {
		return new JsonResult("success", tarea.findByUserAndRolSucursal(
				sucursal, rol, id_user), "Procesado");
	}

	/*@RequestMapping(value = "/enviar/email", method = RequestMethod.POST, headers = "Content-Type=application/json")
	@ResponseBody
	public JsonResult sendEMail(MessageEmailBean email) {
		try {
			//emailService.sendEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult("success", null, "Correo Enviado");
	}*/

}
