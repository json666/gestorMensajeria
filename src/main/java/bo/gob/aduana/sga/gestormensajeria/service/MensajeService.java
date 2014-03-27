package bo.gob.aduana.sga.gestormensajeria.service;

import java.util.List;

import bo.gob.aduana.sga.core.gestormensajeria.model.Mensaje;
import bo.gob.aduana.sga.gestormensajeria.excepciones.NullDeclaracionException;
import bo.gob.aduana.sga.gestormensajeria.excepciones.ValidacionException;
import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;


public interface MensajeService {

	/**
	 * Buscar un mensaje por un asunto dado
	 * 
	 * @param asunto
	 *            de mensaje
	 * @return Mensaje encontrado o null si no existe
	 */
	public Mensaje getByAsunto(String asunto);

	/**
	 * Crear nuevo mensaje
	 * 
	 * @param mensaje
	 *            envia la solicitud de Mensaje en JSON
	 */
	public Mensaje crear(Mensaje mensaje) throws ValidacionException;

	/**
	 * Modificar mensaje existente
	 * 
	 * asunto
	 *            existente en formato JSON
	 */
	public Mensaje modificar(Mensaje mensaje) throws ValidacionException,
			NullDeclaracionException;

	/**
	 * Eliminar un mensaje
	 * 
	 * declaracion
	 *            mensaje que se desea eliminar
	 */
	public void eliminar(Mensaje mensaje) throws NullDeclaracionException;

	/**
	 * Eliminar un mensaje por Asunto
	 * 
	 * Asunto
	 *            o Referencia
	 * @throws NullDeclaracionException
	 */
	public void eliminarMensaje(String asunto) throws NullDeclaracionException;

	/**
	 * Retorna la lista de todos los documentos de la coleccion
	 * 
	 * @return lista de documentos
	 */
	public List<Mensaje> listAll();

	/**
	 * Retorna la lista de Mensajes que tengan un estado especificado
	 * 
	 * estado
	 *            El estado del mensaje como string
	 * @return lista de declaraciones
	 */
	public List<Mensaje> listByAsunto(String asunto, String cuerpo, String pie);

	/**
	 * Retorna la lista de todos los documentos de la coleccion de un usuario en
	 * especifico.
	 * 
	 * @return lista de documentos por usuario
	 */
	public List<Mensaje> findByUser(String id);

    public JsonResult findAll(String id, int pagina);

}
