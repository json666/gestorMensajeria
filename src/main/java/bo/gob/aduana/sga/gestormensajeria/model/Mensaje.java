package bo.gob.aduana.sga.gestormensajeria.model;

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
 * descripcion:adicionando atributos "Nº tramite, tambien se modifico el atributo fecha de tipo Date por String." 	
 */
@Document(collection = "mensajes")
public class Mensaje extends BaseEntity {

	private String tipo;
	private String remitente;
	private Long tiempo;
	private String fecha;
	private String cuerpo;
	private HashMap<String, Object> objetoProcesado;
	private String estado;
	private String destinatario;
	//private String url;
	
	private String ntramite;
	
	
	
	public Mensaje(String tipo, 
					String remitente, 
					Long tiempo, 
					String fecha,
					String cuerpo, 
					HashMap<String, Object> objetoProcesado,
					String estado, 
					String destinatario,
					String ntramite
		) 
	{
		this.tipo = tipo;
		this.remitente = remitente;
		this.tiempo = tiempo;
		this.fecha = fecha;
		this.cuerpo = cuerpo;
		this.objetoProcesado = objetoProcesado;
		this.estado = estado;
		this.destinatario = destinatario;
		this.ntramite=ntramite;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public Long getTiempo() {
		return tiempo;
	}
	public void setTiempo(Long tiempo) {
		this.tiempo = tiempo;
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
	
	public HashMap<String, Object> getObjetoProcesado() {
		return objetoProcesado;
	}
	public void setObjetoProcesado(HashMap<String, Object> objetoProcesado) {
		this.objetoProcesado = objetoProcesado;
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
	
	

}
