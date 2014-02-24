package bo.gob.aduana.sga.gestormensajeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;





import bo.gob.aduana.sga.gestormensajeria.service.MensajeService;
import bo.gob.aduana.sga.gestormensajeria.service.MessageSender;
import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;




@Controller
@RequestMapping(value = "/rest")
public class MensajeRESTController {

	@Autowired
	private MensajeService mensajes;
	
	@Autowired(required=true)
	private MessageSender<String> messageSender;
	
	@RequestMapping(value = "/mensajes", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult listAll() {
        return new JsonResult("success", mensajes.listAll(), null);
    }
	
	@RequestMapping(value = "/enviar", method = RequestMethod.POST)
	public @ResponseBody
	JsonResult create(@RequestBody String message) {
		System.out.println("************************Enviando Mensaje**************");
		messageSender.send(message);
		return new JsonResult("success", null, "Mensaje Enviado");
	}

}
