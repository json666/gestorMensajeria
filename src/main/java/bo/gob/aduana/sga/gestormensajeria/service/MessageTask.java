package bo.gob.aduana.sga.gestormensajeria.service;

public interface MessageTask<T> {
	public void send(T object);
}
