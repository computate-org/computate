package org.computate.frFR.java;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**	Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */
public abstract class EcrireClasseGen<DEV> {
	public static final String VAL_entiteCommentaireLigne1Part1 = "Le champ \u00AB ";
}
