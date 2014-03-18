package bo.gob.aduana.sga.gestormensajeria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bo.gob.aduana.sga.gestormensajeria.bean.MessageEmailBean;
import bo.gob.aduana.sga.gestormensajeria.service.EmailNotificationService;
import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;


@Controller
@RequestMapping(value = "/rest")
public class EmailNotificationRESTController {
	
	private final Logger logger = LoggerFactory.getLogger(EmailNotificationRESTController.class);

	
	@Autowired
	EmailNotificationService emailNotificationService;

	@RequestMapping(value = "/enviar/email", method = RequestMethod.POST, headers = "Content-Type=application/json")
	@ResponseBody
	public JsonResult sendEMail(@RequestBody MessageEmailBean email) {
		logger.debug("Objeto Email ", email);
		MessageEmailBean mail=null;
		try {
			
			mail=emailNotificationService.sendMailByVelocity(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(true,"Mensaje enviado");
	}


	

	
	

}
