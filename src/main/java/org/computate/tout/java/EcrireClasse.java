package org.computate.tout.java; 

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class EcrireClasse extends IndexerClasse { 

	protected void ecrireClasseGen(String classeCheminAbsolu, String nomLangue) throws Exception { 

		SolrQuery rechercheSolr = new SolrQuery(); 
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeCheminAbsolu_indexe_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addFilterQuery("classeEstClasse_indexe_boolean:true"); 

		QueryResponse reponseRecherche = clientSolr.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		if(listeRecherche.size() > 0) {
			SolrDocument document = listeRecherche.get(0); 
			String classeCheminRepertoireGen = (String)document.get("classeCheminRepertoireGen_" + nomLangue + "_stocke_string");
			String classeCheminGen = (String)document.get("classeCheminGen_" + nomLangue + "_stocke_string"); 
			File classeRepertoire = new File(classeCheminRepertoireGen);
			classeRepertoire.mkdirs();
			File classeFichier = new File(classeCheminGen);
			StringBuilder s = new StringBuilder();
			
			String classeNomSimpleGen = (String)document.get("classeNomSimpleGen_" + nomLangue + "_stocke_string");
			String classeNomSimpleSuper = (String)document.get("classeNomSimpleSuper_" + nomLangue + "_stocke_string");

			s.append("public class ").append(classeNomSimpleGen).append(" extends ").append(classeNomSimpleSuper);
			s.append(" {\n");
			s.append("\n"); 
			s.append("}\n");

			FileUtils.write(classeFichier, s, Charset.forName("UTF-8")); 
		}
	}  
}
