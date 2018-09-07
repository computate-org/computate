package org.computate.frFR.java;      

import java.io.File;
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

/**
 * nomCanonique.enUS: org.computate.enUS.java.WriteGenClass
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */     
public class EcrireGenClasse extends EcrireClasse {

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
	protected void ecrireClasseGen(String classeCheminAbsolu, String langueNom) throws Exception { 

		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addFilterQuery("classeCheminAbsolu_indexed_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addFilterQuery("classeEtendGen_indexed_boolean:true");
		rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);

		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		ecrireClasseGen(reponseRecherche, langueNom);
	}

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
	protected void ecrireClasseGen(QueryResponse reponseRecherche, String langueNom) throws Exception { 
		SolrDocumentList listeRecherche = reponseRecherche.getResults();

		if(listeRecherche.size() > 0 && (langueIndexe || !StringUtils.equals(langueNom, this.langueNom))) {    
			String classeCheminRepertoireGen = null;
			String classeCheminGen = null; 
			File classeRepertoireGen = null;
			File classeFichierGen = null;
			StringBuilder s = new StringBuilder();
			
			String classeNomSimpleGen = null;
			String classeNomSimpleSuper = null;    
			String classeNomSimpleSuperGenerique = null;    
			String classeNomEnsemble = null;      
			String classeNomSimple = null;
			String classeNomCanoniqueSuper = null;    
			String classeCommentaire = null;      
			List<String> classeImportations = null;  
			List<String> classeParametreTypeNoms = null;  
			List<String> classeSuperParametreTypeNoms = null;  
			Boolean classeEtendGen = null;
	
			for(int i = 0; i < listeRecherche.size(); i++) {
				SolrDocument doc = listeRecherche.get(i); 
				Integer partNumero = (Integer)doc.get("partNumero_stored_int");
				if(partNumero.equals(1)) {
					classeCheminRepertoireGen = (String)doc.get("classeCheminRepertoireGen_" + langueNom + "_stored_string");
					classeCheminGen = (String)doc.get("classeCheminGen_" + langueNom + "_stored_string"); 
					classeRepertoireGen = new File(classeCheminRepertoireGen);
					classeRepertoireGen.mkdirs();
					classeFichierGen = new File(classeCheminGen);
					classeNomSimpleGen = (String)doc.get("classeNomSimpleGen_" + langueNom + "_stored_string");
					classeNomCanoniqueSuper = (String)doc.get("classeNomCanoniqueSuper_" + langueNom + "_stored_string");
					classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + langueNom + "_stored_string");
					classeNomCanoniqueSuper = (String)doc.get("classeNomCanoniqueSuper_" + langueNom + "_stored_string");
					classeNomSimpleSuperGenerique = (String)doc.get("classeNomSimpleSuperGenerique_" + langueNom + "_stored_string");
					classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + langueNom + "_stored_string");
					classeCommentaire = (String)doc.get("classeCommentaire_" + langueNom + "_stored_string");
					classeImportations = (List<String>)doc.get("classeImportations_" + langueNom + "_stored_strings");
					classeParametreTypeNoms = (List<String>)doc.get("classeParametreTypeNoms_stored_strings");
					classeSuperParametreTypeNoms = (List<String>)doc.get("classeSuperParametreTypeNoms_stored_strings");
					classeEtendGen = (Boolean)doc.get("classeEtendGen_stored_boolean");
		
					s.append("package ").append(classeNomEnsemble).append(";\n\n");
					if(classeImportations.size() > 0) { 
						for(String classeImportation : classeImportations) {
							s.append("import ").append(classeImportation).append(";\n");
						} 
						s.append("\n");  
					}
					ecrireCommentaire(s, classeCommentaire, 0); 
					s.append("public class ").append(classeNomSimpleGen);
					if(classeParametreTypeNoms != null && classeParametreTypeNoms.size() > 0) {
						s.append("<");
						for(int j = 0; j < classeParametreTypeNoms.size(); j++) {
							String classeParametreTypeNom = classeParametreTypeNoms.get(j);
							if(i > 0)
								s.append(", ");
							s.append(classeParametreTypeNom);
						}
						s.append(">");
					}
					if(classeNomSimpleSuperGenerique != null && !"java.lang.Object".equals(classeNomSimpleSuperGenerique) && !"DEV".equals(classeNomSimpleSuperGenerique)) {
						s.append(" extends ");
						if(classeEtendGen) {
							s.append(classeNomSimple).append("Gen");
						} 
						else {
							s.append(classeNomSimpleSuper);
						}
						if(classeSuperParametreTypeNoms != null && classeSuperParametreTypeNoms.size() > 0) {
							s.append("<");
							for(int j = 0; j < classeSuperParametreTypeNoms.size(); j++) {
								String classeSuperParametreTypeNom = classeSuperParametreTypeNoms.get(j);
								if(i > 0)
									s.append(", ");
								s.append(classeSuperParametreTypeNom);
							}
							s.append(">");
						}
					}
					s.append(" {\n");
				} 
				else {
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stored_boolean");
					String entiteVar = (String)doc.get("entiteVar_" + langueNom + "_stored_string");
					String entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
	
					if(BooleanUtils.isTrue(partEstEntite)) {
						s.append("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstPublic_stored_boolean")))
							s.append("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstProtege_stored_boolean")))
							s.append("protege ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstPrive_stored_boolean")))
							s.append("prive ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstStatique_stored_boolean")))
							s.append("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstFinale_stored_boolean")))
							s.append("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstAbstrait_stored_boolean")))
							s.append("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("entiteEstNatif_stored_boolean")))
							s.append("native ");
						s.append(entiteNomSimpleComplet).append(" ").append(entiteVar);
						s.append(";\n");
					}     
				}
			}
			s.append("}\n");
			FileUtils.write(classeFichierGen, s, Charset.forName("UTF-8"));  
		} 
	} 
}
