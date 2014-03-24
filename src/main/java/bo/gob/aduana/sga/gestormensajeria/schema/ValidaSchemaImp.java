package bo.gob.aduana.sga.gestormensajeria.schema;

import java.io.IOException;

import org.springframework.stereotype.Repository;

import bo.gob.aduana.sga.gestormensajeria.utils.JsonResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.report.ProcessingReport;

//import com.github.fge.jsonschema.

@Repository
public class ValidaSchemaImp implements ValidaSchema {
    private static String SCHEMA_URI = "http://localhost/aduana/schemas/";
    private JsonResult jsonResult;

    public JsonResult datosExportacion(String dec) throws IOException,
            ProcessingException {
        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        final JsonSchema schema = factory.getJsonSchema(SCHEMA_URI
                + "exportacionSchema.json");

        JsonNode jsonFile = JsonLoader.fromString(dec);
        ProcessingReport report;
        report = schema.validate(jsonFile);

        jsonResult = new JsonResult();

        if (report.isSuccess()) {
             //jsonsetStatus("Success");
        } else {
//            jsonResult.setStatus("Fail");
//            jsonResult.setMessage(report.toString());
        }

        return jsonResult;
    }

   
    public JsonResult datosExportacionCabecera(String dec) throws IOException,
            ProcessingException {
        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        final JsonSchema schema = factory.getJsonSchema(SCHEMA_URI
                + "exportacionCabeceraSchema.json");

        JsonNode jsonFile = JsonLoader.fromString(dec);
        ProcessingReport report;
        report = schema.validate(jsonFile);

        jsonResult = new JsonResult();

        if (report.isSuccess()) {
//            jsonResult.setStatus("Success");
        } else {
//            jsonResult.setStatus("Fail");
//            jsonResult.setMessage(report.toString());
        }

        return jsonResult;
    }

   
    public JsonResult datosExportacionSerie(String serie) throws IOException,
            ProcessingException {
        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        final JsonSchema schema = factory.getJsonSchema(SCHEMA_URI
                + "exportacionSerieSchema.json");

        JsonNode jsonFile = JsonLoader.fromString(serie);
        ProcessingReport report;
        report = schema.validate(jsonFile);

        jsonResult = new JsonResult();

        if (report.isSuccess()) {
//            jsonResult.setStatus("Success");
        } else {
//            jsonResult.setStatus("Fail");
//            jsonResult.setMessage(report.toString());
        }

        return jsonResult;
    }

   
    public JsonResult datosExportacionDocumento(String doc) throws IOException,
            ProcessingException {
        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        final JsonSchema schema = factory.getJsonSchema(SCHEMA_URI
                + "exportacionDocumentoSchema.json");

        JsonNode jsonFile = JsonLoader.fromString(doc);
        ProcessingReport report;
        report = schema.validate(jsonFile);

        jsonResult = new JsonResult();

        if (report.isSuccess()) {
            //jsonResult.setStatus("Success");
        } else {
//            jsonResult.setStatus("Fail");
//            jsonResult.setMessage(report.toString());
        }

        return jsonResult;
    }
}
