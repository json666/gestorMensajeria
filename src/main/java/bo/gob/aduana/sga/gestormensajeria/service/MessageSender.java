package bo.gob.aduana.sga.gestormensajeria.service;

import org.springframework.stereotype.Service;

public interface MessageSender<T> {

	public void send(T object);
}
