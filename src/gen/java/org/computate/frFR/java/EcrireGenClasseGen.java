package org.computate.frFR.java;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**	Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue.
 */
public abstract class EcrireGenClasseGen<DEV> extends EcrireClasse {
	public static final String VAL_entiteCommentaireLigne1Part1 = "L'entit\u00E9 \u00AB ";
	public static final String VAL_entiteCommentaireLigne1Part2 = " \u00BB";
	public static final String VAL_entiteCouvertureLigne1Part1 = " est d\u00E9fini comme null avant d'\u00EAtre initialis\u00E9. ";
	public static final String VAL_entiteCouvertureLigne2Part1 = " est pour envelopper une valeur \u00E0 assigner \u00E0 ce champ lors de l'initialisation. ";
	public static final String VAL_entiteConstruitLigne1Part1 = "Il est construit avant d'\u00EAtre initialis\u00E9 avec le constructeur par d\u00E9faut ";
	public static final String VAL_entiteConstruitLigne1Part2 = "(). ";
	public static final String VAL_entiteConstruitLigne2Part1 = " est le champ d\u00E9j\u00E0 construit. ";
	public static final String VAL_entiteThrowsLigne2Part1 = " afin que toute exception lors de l'initialisation est g\u00E9r\u00E9e par le servlet. ";

	protected boolean dejaInitialiseEcrireGenClasse = false;

	public void initLoinEcrireGenClasse() throws Exception {
		if(!dejaInitialiseEcrireGenClasse) {
			dejaInitialiseEcrireGenClasse = true;
		}
	}
}
