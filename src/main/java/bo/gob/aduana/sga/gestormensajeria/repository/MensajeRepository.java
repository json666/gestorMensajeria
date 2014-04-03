package bo.gob.aduana.sga.gestormensajeria.repository;
/**
 * jsanchez
 */

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import bo.gob.aduana.sga.core.gestormensajeria.model.Mensaje;


@Repository
 public interface MensajeRepository extends PagingAndSortingRepository<Mensaje, String> {

    @Query("{asunto : ?0, cuerpo : ?1, pie : ?2}")
    public List<Mensaje> findByAsunto(String asunto, String cuerpo, String pie);

    @Query("{id_usuario:?0}")
    public List<Mensaje> findByUser(String id_usuario);

    @Query("{id_usuario:?0}")
    public List<Mensaje> findByDisabledFalse(String id_usuario, Pageable pageable);


}
