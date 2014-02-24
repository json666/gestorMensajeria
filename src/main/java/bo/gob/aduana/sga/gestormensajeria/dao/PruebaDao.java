package bo.gob.aduana.sga.gestormensajeria.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import bo.gob.aduana.sga.gestormensajeria.utils.Prueba;

/**
 * Created with IntelliJ IDEA.
 * User: esalamanca
 * Date: 06-12-13
 * Time: 09:53 AM
 * Repositorio y servidios DAO para Prueba.
 */
@Repository
public class PruebaDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String COLLECCION = "pruebas";

    /**
     * Proxy
     */
    public PruebaDao() {
    }

    /**
     * Constructor para testing
     *
     * @param mongoOperations conexion a MongoDB para tests
     */
    public PruebaDao(MongoOperations mongoOperations) {
        mongoTemplate = (MongoTemplate) mongoOperations;
    }

    /**
     * Insertar una nueva prueba en mongo
     *
     * @param prueba documento que se desea persistir
     */
    public void crear(Prueba prueba) {
        prueba.setId(UUID.randomUUID().toString());
        mongoTemplate.insert(prueba, COLLECCION);
    }

    /**
     * Recuperar un documento prueba de mongo
     *
     * @param valor el valor para Codigo
     */
    public Prueba getByCodigo(String valor) {
        Query pruebaQuery = new Query(Criteria.where("codigo").is(valor));
        return mongoTemplate.findOne(pruebaQuery, Prueba.class);
    }

    /**
     * Modificar una prueba
     *
     * @param prueba el nuevo documento q reemplaza al anterior
     */
    public void modificar(Prueba prueba) {
        //Sacar el id de la prueba existente por su codigo y ponerlo en la prueba nueva
        prueba.setId(getByCodigo(prueba.getCodigo()).getId());
        mongoTemplate.save(prueba);
    }

    /**
     * Eliminar un documento de la collecion
     *
     * @param prueba documento que se desea eliminar
     */
    public void eliminar(Prueba prueba) {
        //Sacar el id de la prueba existente por su codigo y ponerlo en la prueba nueva
        prueba.setId(getByCodigo(prueba.getCodigo()).getId());
        mongoTemplate.remove(prueba);
    }

    /**
     * Listar todos los documentos de la coleccion
     *
     * @return lista de documentos
     */
    public List<Prueba> listAll() {
        return mongoTemplate.findAll(Prueba.class);
    }
}
