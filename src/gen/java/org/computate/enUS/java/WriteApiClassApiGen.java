package org.computate.enUS.java;

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
import org.computate.enUS.java.WriteGenClass;

/**	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 */
public abstract class WriteApiClassApiGen extends HttpServlet {

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
