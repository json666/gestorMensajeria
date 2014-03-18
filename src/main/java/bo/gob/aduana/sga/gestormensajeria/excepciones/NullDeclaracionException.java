package bo.gob.aduana.sga.gestormensajeria.excepciones;

/**
 * Created with IntelliJ IDEA.
 * User: esalamanca
 * Date: 19-12-13
 * Time: 05:12 PM
 * Excepcion para cuando nos pasan una declaracion q no existe.
 */
public class NullDeclaracionException extends Exception {

    /**
     * Constructs a new exception with <code>null</code> as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public NullDeclaracionException() {
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NullDeclaracionException(String message) {
        super(message);
    }
}
