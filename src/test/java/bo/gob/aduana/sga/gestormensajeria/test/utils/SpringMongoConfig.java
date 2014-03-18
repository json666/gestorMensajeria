package bo.gob.aduana.sga.gestormensajeria.test.utils;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created with IntelliJ IDEA.
 * User: esalamanca
 * Date: 06-12-13
 * Time: 09:20 AM
 * Configuracion de MongoDB con Spring Data.
 */
@Configuration
public class SpringMongoConfig {

    @Bean
    public MongoDbFactory mongoDBFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), "mensajeria");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDBFactory());
    }
}
