package bo.gob.aduana.sga.gestormensajeria.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author mcardenas
 * 
 */
/*
 * autor: jsanchez
 * fecha:	21/02/2014
 * descripcion:adicionando atributos Nro. tramite, tambien se modifico el atributo fecha de tipo Date por String. 	
 */
@Document(collection = "mensajes")
public class Mensaje extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoMensaje tipo;
	private String remitente;
	private String fecha;
	private String cuerpo;
	private String estado;
	private String destinatario;
	private String id_usuario;
<<<<<<< HEAD
=======
	
>>>>>>> branch 'master' of https://github.com/json666/gestorMensajeria.git
	private String ntramite;
	
	
	
	
	public Mensaje(TipoMensaje tipo, String remitente,String fecha,
			String cuerpo,
			String estado, String destinatario, String id_usuario,
			String ntramite) {
		this.tipo = tipo;
		this.remitente = remitente;
		this.fecha = fecha;
		this.cuerpo = cuerpo;
		this.estado = estado;
		this.destinatario = destinatario;
		this.id_usuario = id_usuario;
		this.ntramite = ntramite;
	}
	
	public Mensaje(){
		
	}
	public TipoMensaje getTipo() {
		return tipo;
	}
	public void setTipo(TipoMensaje tipo) {
		this.tipo = tipo;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getNtramite() {
		return ntramite;
	}
	public void setNtramite(String ntramite) {
		this.ntramite = ntramite;
	}
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	

}
