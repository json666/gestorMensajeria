package bo.gob.aduana.sga.gestormensajeria.service;

import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;

public interface MessageTask<T> {
	public JsonResult send(T object);
    public JsonResult edit(T object);
    public JsonResult disableTask(String id);

}
