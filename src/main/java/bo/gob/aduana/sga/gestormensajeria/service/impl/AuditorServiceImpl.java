package bo.gob.aduana.sga.gestormensajeria.service.impl;

import org.springframework.data.domain.AuditorAware;

/**
 * User: esalamanca
 * Date: 18-12-13
 * Time: 04:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuditorServiceImpl implements AuditorAware<String> {

    public String getCurrentAuditor() {
        return "esalamanca";
    }
}
