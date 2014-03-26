package bo.gob.aduana.sga.gestormensajeria.service;

import java.util.List;


import bo.gob.aduana.sga.core.gestormensajeria.model.Tarea;
import bo.gob.aduana.sga.gestormensajeria.excepciones.NullDeclaracionException;
import bo.gob.aduana.sga.gestormensajeria.excepciones.ValidacionException;
import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;


public interface TareaService {
	 /**
     * Buscar un mensaje por un asunto dado 
     *
     * @param  asunto de mensaje
     * @return Mensaje encontrado o null si no existe
     */
    public Tarea getByAsunto(String asunto);
    /**
     * Crear nuevo mensaje 
     *
     * @param mensaje envia la solicitud de Mensaje en JSON
     */
    public Tarea crear(Tarea tarea) throws ValidacionException;

    /**
     * Modificar mensaje existente
     *
     * @param asunto existente en formato JSON
     */
    public Tarea modificar(Tarea tarea) throws ValidacionException, NullDeclaracionException;

    /**
     * Eliminar un mensaje
     *
     * @param declaracion mensaje que se desea eliminar
     */
    public void eliminar(Tarea tarea) throws NullDeclaracionException;

    /**
     * Eliminar un mensaje por Asunto
     *
     * @param Asunto o Referencia
     * @throws NullDeclaracionException
     */
    public void eliminarMensaje(String asunto) throws NullDeclaracionException;

    /**
     * Retorna la lista de todos los documentos de la coleccion
     *
     * @return lista de documentos
     */
    public List<Tarea> listAll();
    
    /**
     * Retorna la lista de todos los documentos de la coleccion de un usuario en especifico.
     *
     * @return lista de documentos por usuario
     */
    public List<Tarea> listbyIdUser(String id);

    /**
     * Retorna la lista de Mensajes que tengan un estado especificado
     *
     * @param estado El estado del mensaje como string
     * @return lista de declaraciones
     */
    public List<Tarea> listByAsunto(String asunto,String cuerpo, String pie);
    
    /**
     * Retorna la lista de Mensajes de un usuario, rol y sucursal en especifico
     *
     * @param resultado mensaje como string
     * @return lista las tareas de un usuario, rol y sucursal
     */
    public List<Tarea> findByUserAndRolSucursal(String id_usuario, String rol, String suc);
    
    /**
     * Retorna la lista de todos los documentos de la coleccion
     *
     * @return lista de documentos
     */
        public JsonResult findAll(String id, int pagina);
    /**
     * Retorna la lista de todos los documentos de la coleccion con el tipo rol
     *
     * @return lista de documentos del filtro rol
     */
    public List<Tarea> findByRol(String rol);

    
    public  Tarea findById(String id);

    /*
     *Author: Jheyson Sanchez
     *Descripcion: Metodo para la busqueda por Rol mas la paginacion
     *Fecha: 26/03/2014
     *
     */
    public JsonResult findByRolPaging(String rol, int pagina);

}
