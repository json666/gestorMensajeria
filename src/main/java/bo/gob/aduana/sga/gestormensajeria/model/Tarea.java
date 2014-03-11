package bo.gob.aduana.sga.gestormensajeria.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import bo.gob.aduana.sga.gestormensajeria.model.Opcion;
import bo.gob.aduana.sga.gestormensajeria.model.TipoMensaje;

@Document(collection = "tareas")
public class Tarea extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1383697429230517241L;
	
	private String tipo;
	private String remitente;
	private  String fecha;
	private String cuerpo;
	private String destinatario;
	private List<Opcion> urls;
	private String proceso;
	private String tipoTramite;
	private String nroTramite;
	private String estado;
	private String rol;
	private String sucursal;
	private String id_usuario;
	
	public Tarea(String tipo, String remitente, String fecha, String cuerpo,
			String destinatario, List<Opcion> urls, String proceso,
			String tipoTramite, String nroTramite, String estado, String rol,
			String sucursal, String id_usuario) {
		this.tipo = tipo;
		this.remitente = remitente;
		this.fecha = fecha;
		this.cuerpo = cuerpo;
		this.destinatario = destinatario;
		this.urls = urls;
		this.proceso = proceso;
		this.tipoTramite = tipoTramite;
		this.nroTramite = nroTramite;
		this.estado = estado;
		this.rol = rol;
		this.sucursal = sucursal;
		this.id_usuario = id_usuario;
	}

	public Tarea(){
		
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


	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}


	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
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



	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}


	public String getId_usuario() {
		return id_usuario;
	}


	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public List<Opcion> getUrls() {
		return urls;
	}

	public void setUrls(List<Opcion> urls) {
		this.urls = urls;
	}

}
