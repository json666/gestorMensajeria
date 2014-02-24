package bo.gob.aduana.sga.gestormensajeria.excepciones;

/**
 * Created with IntelliJ IDEA.
 * User: esalamanca
 * Date: 13-12-13
 * Time: 02:59 PM
 * Excepcion para la validacio json.schema.
 */
public class ValidacionException extends Exception {

    /**
     * Parameterless
     */
    public ValidacionException() {
    }

    /**
     * Con mensaje
     *
     * @param message
     */
    public ValidacionException(String message) {
        super(message);
    }
}
