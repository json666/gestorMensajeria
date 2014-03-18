package bo.gob.aduana.sga.gestormensajeria.schema;

import java.io.IOException;

import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;

import com.github.fge.jsonschema.exceptions.ProcessingException;

public interface ValidaSchema {
    JsonResult datosExportacion(String dec) throws IOException,
            ProcessingException;

    JsonResult datosExportacionCabecera(String dec) throws IOException,
            ProcessingException;

    JsonResult datosExportacionSerie(String serie) throws IOException,
            ProcessingException;

    JsonResult datosExportacionDocumento(String doc) throws IOException,
            ProcessingException;
}
