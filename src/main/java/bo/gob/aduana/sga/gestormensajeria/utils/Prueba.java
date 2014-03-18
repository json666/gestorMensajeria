package bo.gob.aduana.sga.gestormensajeria.utils;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created with IntelliJ IDEA.
 * User: esalamanca
 * Date: 06-12-13
 * Time: 09:46 AM
 * Modelo de prueba, solo para pruebas.
 */
@Document(collection = "pruebas")
public class Prueba {

    @Id
    private String id;

    private String codigo;

    private String descripcion;

    //Proxy
    public Prueba() {
    }

    public Prueba(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
