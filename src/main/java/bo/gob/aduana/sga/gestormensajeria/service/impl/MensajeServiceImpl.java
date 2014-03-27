package bo.gob.aduana.sga.gestormensajeria.service.impl;

import java.util.List;
import java.util.UUID;



import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import bo.gob.aduana.sga.core.gestormensajeria.model.Mensaje;


import bo.gob.aduana.sga.gestormensajeria.excepciones.NullDeclaracionException;
import bo.gob.aduana.sga.gestormensajeria.repository.MensajeRepository;
import bo.gob.aduana.sga.gestormensajeria.service.MensajeService;

@Service
public class MensajeServiceImpl implements MensajeService{
	
	@Autowired
	MensajeRepository mensajeRepository;

	public Mensaje getByAsunto(String asunto) {
		//1ro: Buscar por asunto de mensaje
        Mensaje mensaje = getByAsunto(asunto);

        return mensaje;
	}

	public Mensaje crear(Mensaje mensaje){
       mensaje.setId(UUID.randomUUID().toString());

      mensajeRepository.save(mensaje);
      return mensaje;
	}

	public Mensaje modificar(Mensaje mensaje) {
		Mensaje oldMensjae = mensajeRepository.findOne(mensaje.getId());

        if (oldMensjae == null) {
            mensaje = null;
            System.out.println("Error: Persona inexistente.");
        } else {
            System.out.println("mensaje.getAsunto() = " + mensaje);
            mensajeRepository.save(mensaje);
            System.out.println("Success: Persona actualizada.");
        }

        return mensaje;
	}

	

	public void eliminar(Mensaje mensaje) throws NullDeclaracionException {
		// TODO Auto-generated method stub
		
	}

	public void eliminarMensaje(String asunto) throws NullDeclaracionException {
		//Sacar el id del documento existente
        Mensaje mensaje = getByAsunto(asunto);

        if (mensaje != null) {
            mensajeRepository.delete(mensaje);
        } else {
            throw new NullDeclaracionException("Error: La Declaracion ingresada no existe");
        }
		
	}

	public List<Mensaje> listAll() {
		return (List<Mensaje>) mensajeRepository.findAll();
	}

	public List<Mensaje> listByAsunto(String asunto, String cuerpo, String pie) {
		//Si no existe estado, pasar todas las declaraciones
        if (StringUtils.isBlank(asunto)) {
            return (List<Mensaje>) mensajeRepository.findAll();
        } else {
            return mensajeRepository.findByAsunto(asunto, cuerpo, pie);
        }
	}

	@Override
	public List<Mensaje> findByUser(String id_usuario) {
		return (List<Mensaje>) mensajeRepository.findByUser(id_usuario);
	}

    @Override
    public JsonResult findAll(String id, int pagina) {
        String ordenCampo = "id_usuario";
        PageRequest page = new PageRequest(pagina, 7, Direction.ASC,
                ordenCampo);
        List<Mensaje> lista = mensajeRepository.findByDisabledFalse(id,page);
        if (lista == null || lista.size() == 0) {
            return new JsonResult(false, "No existen registros.");
        }
        return new JsonResult(true, lista);
    }


}
