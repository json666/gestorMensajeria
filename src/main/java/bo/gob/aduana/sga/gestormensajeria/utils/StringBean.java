package bo.gob.aduana.sga.gestormensajeria.utils;

/**
 * Created with IntelliJ IDEA.
 * User: esalamanca
 * Date: 05-12-13
 * Time: 12:14 PM
 * Bean basico con id String y descripcion String
 */
public class StringBean {

    private String id;
    private String dsc;

    /**
     * Proxy
     */
    public StringBean() {
    }

    /**
     * Constructor
     *
     * @param id
     * @param dsc
     */
    public StringBean(String id, String dsc) {
        this.id = id;
        this.dsc = dsc;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id + "\"" +
                ", \"dsc\": \"" + dsc + "\"" +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }
}
