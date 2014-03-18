package bo.gob.aduana.sga.param.oce.util;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;

/**
 * Clase singleton que se utiliza para obtener una sola instancia de VelocityEngine
 * @author flimachi
 *
 */
public class VelocityHelper {

	private static VelocityEngine velocityEngine;

	public static VelocityEngine getVelocityEngine() {
		if (velocityEngine == null)
			init();
		return velocityEngine;
	}

	private static void init() {
		velocityEngine = new VelocityEngine();
		Properties velocityProperties = new Properties();
		velocityProperties.put("resource.loader", "class");
		velocityProperties.put("class.resource.loader.description", "Velocity Classpath Resource Loader");
		velocityProperties.put("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // this is for disable velocity.log
        velocityProperties.put("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
        velocityEngine.init(velocityProperties);
	}
}
