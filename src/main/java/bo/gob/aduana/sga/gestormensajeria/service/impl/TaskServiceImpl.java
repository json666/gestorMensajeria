package bo.gob.aduana.sga.gestormensajeria.service.impl;

import java.util.List;
import java.util.UUID;

import bo.gob.aduana.sga.core.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import bo.gob.aduana.sga.gestormensajeria.excepciones.NullDeclaracionException;
import bo.gob.aduana.sga.gestormensajeria.excepciones.ValidacionException;
import bo.gob.aduana.sga.gestormensajeria.repository.TareaRepository;
import bo.gob.aduana.sga.gestormensajeria.service.TareaService;

@Service
public class TaskServiceImpl implements TareaService {
	private final Logger logger = LoggerFactory
			.getLogger(TaskServiceImpl.class);

	@Autowired
	TareaRepository tareaRepository;


    public Tarea getByAsunto(String asunto) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tarea crear(Tarea tarea) throws ValidacionException {

		try {

			System.out.println("Creando Objeto");
			tarea.setId(UUID.randomUUID().toString());
			tareaRepository.save(tarea);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return tarea;
	}

	public Tarea modificar(Tarea tarea) throws ValidacionException {

		try {

			System.out.println("Modificando Objecto");
			tareaRepository.save(tarea);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tarea;
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

	public List<Tarea> findByUserAndRolSucursal(String suc, String rol,
			String id) {
		return (List<Tarea>) tareaRepository.findByUserAndRolSucursal(suc, rol,
				id);
	}

	// @Override
	// public JsonResult findAll(int pagina) {
	// // logger.debug("Pagina solicitada {}", pagina);
	//
	// String ordenCampo = "descripcion";
	// PageRequest page = new PageRequest(pagina, 50, Direction.ASC,
	// ordenCampo);
	// List<Tarea> lista = tareaRepository.findByDisabledFalse(page);
	// // logger.debug("Registros recuperados {}", lista.size());
	// if (lista == null || lista.size() == 0) {
	// return new JsonResult(false, "No existen registros.");
	// }
	// return new JsonResult(true, tareaRepository.findByUser(rol, pageable));
	// }

    @Override
    public JsonResult findAll(String id_usuario, int pagina) {
        // TODO Auto-generated method stub
        logger.debug("Pagina solicitada {}", pagina);
        String ordenCampo = "id_usuario";
        PageRequest page = new PageRequest(pagina,10, Direction.ASC,ordenCampo);
        List<Tarea> lista = tareaRepository.findByRol(id_usuario, page);
        logger.debug("Registros recuperados {}", lista.size());
        if (lista == null || lista.size() == 0) {
            return new JsonResult(false, "No existen registros.");
        }
        return new JsonResult(true, lista);
    }

    @Override
    public JsonResult findByRolPaging(String rol, int pagina) {
        String ordenCampo = "id_usuario";
        PageRequest page = new PageRequest(pagina,10, Direction.ASC,ordenCampo);
        List<Tarea> lista = tareaRepository.findByRolPaging(rol.toUpperCase(), page);
        logger.debug("Registros recuperados {}", lista.size());
        if (lista == null || lista.size() == 0) {
            return new JsonResult(false, "No existen registros.");
        }
        return new JsonResult(true, lista);
    }

	@Override
	public List<Tarea> findByRol(String rol) {
        return (List<Tarea>) tareaRepository.findByRolIgnoreCase(rol);
	}


	@Override
	public Tarea findById(String id) {
		Tarea tarea=null;
		tarea=tareaRepository.findOne(id);
		return tarea;
	}






}
