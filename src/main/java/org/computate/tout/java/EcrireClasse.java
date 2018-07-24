package org.computate.tout.java; 

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
 * classeNomCanonique_enUS: org.computate.enUS.java.WriteClass
 */
public class EcrireClasse extends IndexerClasse { 

	/**
	 * methodeVar_enUS: writeClassGen
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 * r.enUS: rechercheSolr
	 * solrSearch
	 * r.enUS: reponseRecherche
	 * searchResponse
	 * r.enUS: classeCheminAbsolu
	 * classAbsolutePath
	 * r.enUS: _indexe
	 * _indexed
	 * r.enUS: _stocke
	 * _stored
	 * r.enUS: partNumero
	 * partNumber
	 */     
	protected void ecrireClasseGen(String classeCheminAbsolu, String nomLangue) throws Exception { 

		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addFilterQuery("classeCheminAbsolu_indexe_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addSort("partNumero_indexe_int", ORDER.asc);

		QueryResponse reponseRecherche = clientSolr.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		System.out.println("listeRecherche.size(): " + listeRecherche.size());

		String classeCheminRepertoireGen = null;
		String classeCheminGen = null; 
		File classeRepertoire = null;
		File classeFichier = null;
		StringBuilder s = new StringBuilder();
		
		String classeNomSimpleGen = null;
		String classeNomSimpleSuper = null;    
		String classeNomEnsemble = null;

		for(int i = 0; i < listeRecherche.size(); i++) {
			SolrDocument doc = listeRecherche.get(i); 
			Integer partNumero = (Integer)doc.get("partNumero_stocke_int");
			System.out.println("nomLangue: " + nomLangue);
			if(partNumero.equals(1)) {
				classeCheminRepertoireGen = (String)doc.get("classeCheminRepertoireGen_" + nomLangue + "_stocke_string");
				classeCheminGen = (String)doc.get("classeCheminGen_" + nomLangue + "_stocke_string"); 
				classeRepertoire = new File(classeCheminRepertoireGen);
				classeRepertoire.mkdirs();
				classeFichier = new File(classeCheminGen);
				classeNomSimpleGen = (String)doc.get("classeNomSimpleGen_" + nomLangue + "_stocke_string");
				classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + nomLangue + "_stocke_string");
				classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + nomLangue + "_stocke_string");
	
				s.append("package ").append(classeNomEnsemble).append(";\n\n");
				s.append("public class ").append(classeNomSimpleGen).append(" extends ").append(classeNomSimpleSuper);
				s.append(" {\n");
				s.append("\n"); 
			} 
			else {
				Boolean partEstChamp = (Boolean)doc.get("partEstChamp_stocke_boolean");
				Boolean partEstMethode = (Boolean)doc.get("partEstMethode_stocke_boolean");
				Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stocke_boolean");
				Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stocke_boolean");
				String champVar = (String)doc.get("champVar_" + nomLangue + "_stocke_string");

				if(BooleanUtils.isTrue(partEstChamp)) {
					s.append("\t");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstPublic_stocke_boolean")))
						s.append("public ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstProtege_stocke_boolean")))
						s.append("protege ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstPrive_stocke_boolean")))
						s.append("prive ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstStatique_stocke_boolean")))
						s.append("static ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstFinale_stocke_boolean")))
						s.append("final ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstAbstrait_stocke_boolean")))
						s.append("abstract ");
					if(BooleanUtils.isTrue((Boolean)doc.get("champEstNatif_stocke_boolean")))
						s.append("native ");
					s.append(";\n");
				}     

				if(BooleanUtils.isTrue(partEstMethode)) {
					String methodeVar = (String)doc.get("methodeVar_" + nomLangue + "_stocke_string");
					String methodeCodeSource = (String)doc.get("methodeCodeSource_" + nomLangue + "_stocke_string");
					s.append("\t");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPublic_stocke_boolean")))
						s.append("public ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstProtege_stocke_boolean")))
						s.append("protected ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstPrive_stocke_boolean")))
						s.append("private ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstStatique_stocke_boolean")))
						s.append("static ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstFinale_stocke_boolean")))
						s.append("final ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstAbstrait_stocke_boolean")))
						s.append("abstract ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstNatif_stocke_boolean")))
						s.append("native ");
					if(BooleanUtils.isTrue((Boolean)doc.get("methodeEstVide_stocke_boolean")))
						s.append("void ");
					else
						s.append((String)doc.get("methodeNomSimpleComplet_stocke_string")).append(" ");
					s.append(methodeVar);
					s.append("(");
					s.append(")");
					s.append(" {");
					s.append(methodeCodeSource);
					s.append("}\n\n");
				} 
			}
		}
		s.append("}\n");
		FileUtils.write(classeFichier, s, Charset.forName("UTF-8")); 
	}  
}
