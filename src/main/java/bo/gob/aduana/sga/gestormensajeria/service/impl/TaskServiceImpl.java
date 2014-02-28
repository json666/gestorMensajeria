package bo.gob.aduana.sga.gestormensajeria.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.gob.aduana.sga.gestormensajeria.excepciones.NullDeclaracionException;
import bo.gob.aduana.sga.gestormensajeria.excepciones.ValidacionException;
import bo.gob.aduana.sga.gestormensajeria.model.Mensaje;
import bo.gob.aduana.sga.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.repository.MensajeRepository;
import bo.gob.aduana.sga.gestormensajeria.repository.TareaRepository;
import bo.gob.aduana.sga.gestormensajeria.service.MensajeService;
import bo.gob.aduana.sga.gestormensajeria.service.TareaService;

@Service
public class TaskServiceImpl implements TareaService {

	@Autowired
	TareaRepository tareaRepository;

	public Tarea getByAsunto(String asunto) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tarea crear(Tarea tarea) throws ValidacionException {
		tarea.setId(UUID.randomUUID().toString());
		tareaRepository.save(tarea);
		return tarea;
	}

	public Tarea modificar(Tarea tarea) throws ValidacionException,
			NullDeclaracionException {
		// TODO Auto-generated method stub
		return null;
	}

	public void eliminar(Tarea tarea) throws NullDeclaracionException {
		// TODO Auto-generated method stub

	}

	public void eliminarMensaje(String asunto) throws NullDeclaracionException {
		// TODO Auto-generated method stub

	}

	public List<Tarea> listAll() {
		return (List<Tarea>) tareaRepository.findAll();
	}

	public List<Tarea> listByAsunto(String asunto, String cuerpo, String pie) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Tarea> listbyIdUser(String id_usuario) {
		return (List<Tarea>) tareaRepository.findByUsuario(id_usuario);
	}

	public List<Tarea> findByUserAndRolSucursal(String suc, String rol, String id){
		return (List<Tarea>) tareaRepository.findByUserAndRolSucursal(suc,rol,id);	
	}
}
