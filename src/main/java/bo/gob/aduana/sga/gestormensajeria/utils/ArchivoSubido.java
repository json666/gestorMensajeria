package bo.gob.aduana.sga.gestormensajeria.utils;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * User: esalamanca
 * Date: 23-12-13
 * Time: 09:19 AM
 * Bean para los archivos subidos al servidor.
 */
@JsonIgnoreProperties({"bytes"})
public class ArchivoSubido {

    private String nombreArchivo;
    private String tamanoArchivo;
    private String tipoArchivo;
    private byte[] bytes;

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTamanoArchivo() {
        return tamanoArchivo;
    }

    public void setTamanoArchivo(String tamanoArchivo) {
        this.tamanoArchivo = tamanoArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
