package bo.gob.aduana.sga.gestormensajeria.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import org.springframework.data.mongodb.core.mapping.Document;

import bo.gob.aduana.sga.core.domain.document.BaseDocument;
import bo.gob.aduana.sga.core.domain.document.Documento;

/**
 * 
 * @author jsanchez
 * 
 */
/*
 * autor: jsanchez
 * fecha:	21/02/2014
 * descripcion:adicionando atributos Nro. tramite, tambien se modifico el atributo fecha de tipo Date por String. 	
 */
@Document(collection = "mensajes")
public class Mensaje extends BaseDocument implements Serializable {

    private String tipo;
    private String remitente;
    private String descripcion;
    private String destinatario;
    private String id_usuario;
    private String numeroTramite;
    private String tipoTramite;
    private String asuntoMensaje;
    private String fechaRegistro;
    private String estadoMensaje;
    /**
     *
     */
    public static final long serialVersionUID = 1L;

    public Mensaje(String tipo,
                   String remitente,
                   String descripcion,
                   String destinatario,
                   String numeroTramite,
                   String tipoTramite,
                   String asuntoMensaje,
                   String fechaRegistro,
                   String id_usuario,
                   String estadoMensaje) {
        this.tipo = tipo;
        this.remitente = remitente;
        this.descripcion = descripcion;
        this.estadoMensaje = estadoMensaje;
        this.destinatario = destinatario;
        this.id_usuario = id_usuario;
        this.numeroTramite = numeroTramite;
        this.tipoTramite = tipoTramite;
        this.asuntoMensaje = asuntoMensaje;
        this.fechaRegistro = fechaRegistro;
    }

    public Mensaje() {

    }


    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getAsuntoMensaje() {
        return asuntoMensaje;
    }

    public void setAsuntoMensaje(String asuntoMensaje) {
        this.asuntoMensaje = asuntoMensaje;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getNumeroTramite() {
        return numeroTramite;
    }

    public void setNumeroTramite(String numeroTramite) {
        this.numeroTramite = numeroTramite;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getEstadoMensaje() {
        return estadoMensaje;
    }

    public void setEstadoMensaje(String estadoMensaje) {
        this.estadoMensaje = estadoMensaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}
