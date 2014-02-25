package bo.gob.aduana.sga.gestormensajeria.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bo.gob.aduana.sga.gestormensajeria.model.Mensaje;
import bo.gob.aduana.sga.gestormensajeria.model.Tarea;

public interface TareaRepository extends CrudRepository<Tarea, String>{
	
	@Query("{asunto : ?0, cuerpo : ?1, pie : ?2}")
	public 	List<Tarea> findByAsunto(String asunto, String cuerpo, String pie);


}
