package bo.gob.aduana.sga.gestormensajeria.service;


import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;

public interface MessageSender<T> {

	public JsonResult send(T object);
}
