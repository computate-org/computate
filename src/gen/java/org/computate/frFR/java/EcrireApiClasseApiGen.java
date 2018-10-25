package org.computate.frFR.java;

import javax.servlet.http.HttpServlet;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import ;
import ;
import ;
import ;
import ;
import ;
import org.computate.enUS.java.WriteGenClass;
import org.computate.frFR.java.EcrireGenClasse;

/**	Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue.
 */
public abstract class EcrireApiClasseApiGen extends HttpServlet {

	@Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> paramMap = (Map<String, String[]>)Collections.list(req.getParameterNames()).stream()
				.collect(Collectors.toMap(parameterName -> parameterName, req::getParameterValues));
		for(String paramCle : paramMap.keySet()) {
			String[] paramValeurs = paramMap.get(paramCle);
			for(String paramValeur : paramValeurs) {
				
			}
		}
	}
}
