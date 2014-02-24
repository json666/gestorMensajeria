package bo.gob.aduana.sga.gestormensajeria.utils;

/**
 * Created with IntelliJ IDEA.
 * User: esalamanca
 * Date: 10-12-13
 * Time: 08:38 PM
 * Respuesta de todas las llamadas REST
 */
public class JsonResult {

    private String status;

    private Object data;

    private String message;

    /**
     * Proxy
     */
    public JsonResult() {
    }

    /**
     * constructor
     *
     * @param status  Estado de la respuesta del servicio consumido
     * @param data    Datos devueltos por el servicio consumido
     * @param message Mensaje devuelto por el servicio consumido
     */
    public JsonResult(String status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
