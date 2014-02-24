package bo.gob.aduana.sga.gestormensajeria.repository;
/**
 * mcardenas
 */

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bo.gob.aduana.sga.gestormensajeria.model.Mensaje;

@Repository
public interface MensajeRepository extends CrudRepository<Mensaje, String>{

@Query("{asunto : ?0, cuerpo : ?1, pie : ?2}")
public 	List<Mensaje> findByAsunto(String asunto, String cuerpo, String pie);
	
}
