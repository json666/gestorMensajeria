package bo.gob.aduana.sga.gestormensajeria.utils;

/**
 * Created with IntelliJ IDEA.
 * User: esalamanca
 * Date: 10-12-13
 * Time: 08:38 PM
 * Respuesta de todas las llamadas REST
 */
public class JsonResult {

    private Boolean success;
    private Object result;

    public JsonResult(Boolean success, Object result) {
        this.success = success;
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
    
    public JsonResult(){
    	
    }


}
