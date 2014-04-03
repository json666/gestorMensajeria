package bo.gob.aduana.sga.gestormensajeria.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import bo.gob.aduana.sga.core.gestormensajeria.model.Tarea;




public interface TareaRepository extends PagingAndSortingRepository<Tarea, String>{
	
	@Query("{asunto : ?0, cuerpo : ?1, pie : ?2}")
	public 	List<Tarea> findByAsunto(String asunto, String cuerpo, String pie);

	@Query("{id_usuario:?0}")
	public List<Tarea> findByUsuario(String id_usuario);
	
	@Query("{sucursal:?0, rol:?1, id_usuario:?2}")
	public List<Tarea> findByUserAndRolSucursal(String suc, String rol, String id_usuario);

    @Query("{id_usuario:?0,disabled:false}")
    public List<Tarea> findByRol(String id_usuario, Pageable pageable);

    @Query("{rol:?0, disabled:false}")
    public List<Tarea> findByRolPaging(String rol, Pageable pageable);


    @Query("{rol:?0, disabled:false}")
	public List<Tarea> findByRolAndDisabled(String rol, Sort sort);







    /*
    *   Author: Jheyson Sanchez
    *   Descripcion: Se adiciona la funcionalidad para filtrar por usuario y estado registro
    *                estados disponibles: "REGISTRADO - OBSERVADO ANALISTA - OBSERVADO PLATAFORMA"
    *                                     "VERIFICADO ANALISTA - VERIFICADO PLATAFORMA - EN ESPERA"
    *   Fecha: 31 de Marzo de 2014
     */

    @Query("{rol:?0, estado:?1, disabled:false}")
    public List<Tarea> findByRolAndEstado(String rol, String estado);

    @Query("{rol:?0, estado:?1, disabled:false}")
    public List<Tarea> findByRolStatusPaging(String rol, String estado, Pageable pageable);

}
