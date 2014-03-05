package bo.gob.aduana.sga.gestormensajeria.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.aduana.sga.gestormensajeria.excepciones.NullDeclaracionException;
import bo.gob.aduana.sga.gestormensajeria.excepciones.ValidacionException;
import bo.gob.aduana.sga.gestormensajeria.model.Mensaje;
import bo.gob.aduana.sga.gestormensajeria.model.Tarea;
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
//		//Validamos la declaracion
//        ValidaSchemaImp valida = new ValidaSchemaImp();
//        try {
//
//            //Validar datos crear json schema para mensajes
//            JsonResult json = valida.datosExportacion(mensaje.toString());
//            System.out.println(json.getStatus());
//            System.out.println(json.getMessage());
//
//            //Si valido correctamente
//            if (json.getStatus().equals("Success")) {
//
//                //Crear la nuevo mensaje
//                mensaje.setId(UUID.randomUUID().toString());
//
//                mensajeRepository.save(mensaje);
//                return mensaje;
//            } else {
//                throw new ValidacionException("Error de Validacion: Los datos enviados son incorrectos. " + json.getMessage());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new ValidacionException("Error de Validacion: No se encuentra el archivo json.schema");
//        } catch (ProcessingException e) {
//            e.printStackTrace();
//            throw new ValidacionException("Error de Validacion: Excepcion desconocida de json.schema");
//        }
      //Crear el nuevo mensaje
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
	
	
}
