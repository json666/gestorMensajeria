package bo.gob.aduana.sga.gestormensajeria.test;

import java.io.File;




import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
 

public class ParseJson {
	public static void main(String args[]){
		
		File file= new File("d:\\formato.json");
		
		String jsonStr=" {\"tipo\": \"ACTUALIZACIPON DE DATOS\",\"remitente\": \"jheyson sanchez\",\"url\":[{\"url\":\"http://127.0.0.1/oce/listener.html#/listener/verCarpeta&123456789/oce/\",\"linkText\":\"Ver Tarea\"},{	\"url\":\"http://127.0.0.1/oce/listener.html#/listener/verProceso&123456789/oce/\",\"linkText\":\"Ver Proceso\"}]}";
		JSONObject json=new JSONObject(jsonStr);
		JSONArray arra=json.getJSONArray("url");
		//Iterator i=
		//System.out.println(jsonStr);
		for (int i = 0; i < arra.length(); i++) {
			JSONObject jsonUser=arra.getJSONObject(i);
			System.out.println(jsonUser.get("url"));
			System.out.println(jsonUser.get("linkText"));
		}
		
	}
}
