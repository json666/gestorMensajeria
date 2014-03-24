package bo.gob.aduana.sga.gestormensajeria.service;


public interface MessageSender<T> {

	public void send(T object);
}
