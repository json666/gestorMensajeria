package bo.gob.aduana.sga.gestormensajeria.utils;

/**
 * Created with IntelliJ IDEA.
 * User: marcelo
 * Date: 12-12-13
 * Time: 07:42 AM
 * Bean basico con id Integer y dsc String
 */
public class IntegerBean {

    private Integer id;

    private String dsc;

    /**
     * Proxy
     */
    public IntegerBean() {
    }

    /**
     * Constructor
     *
     * @param id
     * @param dsc
     */
    public IntegerBean(Integer id, String dsc) {
        this.id = id;
        this.dsc = dsc;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id +
                ", \"dsc\": \"" + dsc + "\"" +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }
}
