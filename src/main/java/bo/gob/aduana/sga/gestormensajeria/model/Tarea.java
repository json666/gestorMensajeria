package bo.gob.aduana.sga.gestormensajeria.model;

import java.util.HashMap;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tareas")
public class Tarea extends BaseEntity {
	
	public String tipo;
	private String remitente;
	private Long tiempo;
	private String fecha;
	private String cuerpo;
	private HashMap<String, Object> objetoProcesado;
	private String destinatario;
	private String url;
	private String evento;
	private String proceso;
	private String tipoTramite;
	private String nroTramite;
	private String estado;
	
	
	
	public Tarea(String tipo, 
				String remitente, 
				Long tiempo, 
				String fecha,
				String cuerpo, 
				HashMap<String, Object> objetoProcesado,
				String destinatario, 
				String url, 
				String evento, 
				String proceso,
				String tipoTramite, 
				String nroTramite, 
				String estado) {
		this.tipo = tipo;
		this.remitente = remitente;
		this.tiempo = tiempo;
		this.fecha = fecha;
		this.setCuerpo(cuerpo);
		this.objetoProcesado = objetoProcesado;
		this.destinatario = destinatario;
		this.url = url;
		this.setEvento(evento);
		this.proceso = proceso;
		this.tipoTramite = tipoTramite;
		this.nroTramite = nroTramite;
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getTipoTramite() {
		return tipoTramite;
	}

	public void setTipoTramite(String tipoTramite) {
		this.tipoTramite = tipoTramite;
	}

	public String getNroTramite() {
		return nroTramite;
	}

	public void setNroTramite(String nroTramite) {
		this.nroTramite = nroTramite;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

}
