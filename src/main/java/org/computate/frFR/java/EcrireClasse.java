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
 * classeNomCanonique_enUS: org.computate.enUS.java.WriteClass
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */     
public class EcrireClasse extends IndexerClasse { 

	/**
	 * methodeVar_enUS: writeClass
	 * methodeParamVar_enUS_1: absoluteClassPath
	 * methodeParamVar_enUS_2: languageName
	 */    
	protected void ecrireClasse(String classeCheminAbsolu, String langueNom) throws Exception { 
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addFilterQuery("classeCheminAbsolu_indexe_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addSort("partNumero_indexe_int", ORDER.asc);

		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		ecrireClasse(classeCheminAbsolu, langueNom, reponseRecherche);
	}  

	public void ecrireCommentaire(StringBuilder s, String commentaire, Integer tabulations) {
		String tabulationsStr = StringUtils.repeat("\t", tabulations);
		if(StringUtils.isNotEmpty(commentaire)) {
			String[] partis = StringUtils.split(commentaire, "\n");
			for(int j = 0; j < partis.length; j++) { 
				String ligne = partis[j];
				if(j == 0)
					s.append(tabulationsStr).append("/**\t").append(ligne).append("\n");
				else
					s.append(tabulationsStr).append(" *\t").append(ligne).append("\n");
			}   
			s.append(tabulationsStr).append(" */\n");  
		} 
	}

	/**
	 * methodeVar_enUS: writeClass
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 * r.enUS: listeRecherche
	 * searchList
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
	protected void ecrireClasse(String classeCheminAbsolu, String langueNom, QueryResponse reponseRecherche) throws Exception { 
		SolrDocumentList listeRecherche = reponseRecherche.getResults(); 

		if(listeRecherche.size() > 0) {
			String classeCheminRepertoire = null;
			String classeChemin = null; 
			File classeRepertoire = null;
			File classeFichier = null;
			StringBuilder s = new StringBuilder();
			
			String classeNomSimple = null;
			String classeNomSimpleSuper = null;    
			String classeNomCanoniqueSuper = null;    
			String classeNomEnsemble = null;
			String classeCommentaire = null;      
			List<String> classeImportations = null;  
	
			for(int i = 0; i < listeRecherche.size(); i++) { 
				SolrDocument doc = listeRecherche.get(i); 
				Integer partNumero = (Integer)doc.get("partNumero_stocke_int");
				if(partNumero == null)
					partNumero = 2;
				if(partNumero.equals(1)) {
					classeCheminRepertoire = (String)doc.get("classeCheminRepertoire_" + langueNom + "_stocke_string");
					classeChemin = (String)doc.get("classeChemin_" + langueNom + "_stocke_string"); 
					classeRepertoire = new File(classeCheminRepertoire);
					classeRepertoire.mkdirs();
					classeFichier = new File(classeChemin);
					classeNomSimple = (String)doc.get("classeNomSimple_" + langueNom + "_stocke_string");
					classeNomCanoniqueSuper = (String)doc.get("classeNomCanoniqueSuper_" + langueNom + "_stocke_string");
					classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + langueNom + "_stocke_string");
					classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + langueNom + "_stocke_string");
					classeCommentaire = (String)doc.get("classeCommentaire_" + langueNom + "_stocke_string");
					classeImportations = (List<String>)doc.get("classeImportations_" + langueNom + "_stocke_strings");
		
					s.append("package ").append(classeNomEnsemble).append(";\n\n");
					if(classeImportations.size() > 0) { 
						for(String classeImportation : classeImportations) {
							s.append("import ").append(classeImportation).append(";\n");
						} 
						s.append("\n");  
					}
					ecrireCommentaire(s, classeCommentaire, 0); 
					s.append("public class ").append(classeNomSimple);
					if(!"java.lang.Object".equals(classeNomCanoniqueSuper))
						s.append(" extends ").append(classeNomSimpleSuper);
					s.append(" {\n");
				} 
				else {     
					Boolean partEstChamp = (Boolean)doc.get("partEstChamp_stocke_boolean");
					Boolean partEstMethode = (Boolean)doc.get("partEstMethode_stocke_boolean");
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stocke_boolean");
					Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stocke_boolean");
	
					if(BooleanUtils.isTrue(partEstChamp)) {
						String champCommentaire = (String)doc.get("champCommentaire_" + langueNom + "_stocke_string");
						String champVar = (String)doc.get("champVar_" + langueNom + "_stocke_string");
						String champNomSimpleComplet = (String)doc.get("champNomSimpleComplet_" + langueNom + "_stocke_string");

						s.append("\n"); 
						ecrireCommentaire(s, champCommentaire, 1);
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
						
						s.append(champNomSimpleComplet).append(" ").append(champVar);
						s.append(";\n");
					}     
	
					if(BooleanUtils.isTrue(partEstMethode)) {
						String methodeVar = (String)doc.get("methodeVar_" + langueNom + "_stocke_string");
						String methodeCodeSource = (String)doc.get("methodeCodeSource_" + langueNom + "_stocke_string");
						String methodeCommentaire = (String)doc.get("methodeCommentaire_" + langueNom + "_stocke_string");
						List<String> methodeExceptionNomSimpleCompletListe = (List<String>)doc.get("methodeExceptionNomSimpleComplet_stocke_strings");

						s.append("\n"); 
						ecrireCommentaire(s, methodeCommentaire, 1);
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
							s.append((String)doc.get("methodeNomSimpleComplet_" + langueNom + "_stocke_string")).append(" ");
						s.append(methodeVar);
						s.append("(");
						List<String> methodeParamNomSimpleCompletListe = (List<String>)doc.get("methodeParamNomSimpleComplet_" + langueNom + "_stocke_strings"); 
						List<String> methodeParamVarListe = (List<String>)doc.get("methodeParamVar_" + langueNom + "_stocke_strings");
						if(methodeParamNomSimpleCompletListe != null && methodeParamVarListe != null && methodeParamNomSimpleCompletListe.size() == methodeParamVarListe.size()) {
							for(int j = 0; j < methodeParamVarListe.size(); j++) {
								String methodeParamNomSimpleComplet = methodeParamNomSimpleCompletListe.get(j);
								String methodeParamVar = methodeParamVarListe.get(j);
								if(j > 0)
									s.append(", ");
								s.append(methodeParamNomSimpleComplet).append(" ").append(methodeParamVar);
							}
						}    
						s.append(")");
						if(methodeExceptionNomSimpleCompletListe != null && methodeExceptionNomSimpleCompletListe.size() > 0) {
							s.append(" throws ");
							for(int j = 0; j < methodeExceptionNomSimpleCompletListe.size(); j++) {
								String methodeExceptionNomSimpleComplet = methodeExceptionNomSimpleCompletListe.get(j);
								if(j > 0)
									s.append(", ");
								s.append(methodeExceptionNomSimpleComplet);
							}
						}
						s.append(" {");
						s.append(methodeCodeSource);
						s.append("}\n");
					} 
					else if(BooleanUtils.isTrue(partEstEntite)) {
						
					}
				}
			}
			s.append("}\n"); 
			if(listeRecherche.size() > 0 && !StringUtils.equals(classeCheminAbsolu, classeChemin)) {
				System.out.println("Ecrire: " + classeChemin); 
				FileUtils.write(classeFichier, s, Charset.forName("UTF-8")); 
			}
		}
		else {
			System.err.println("No file was found in the search engine. ");
		}
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
	protected void ecrireClasseGen(String classeCheminAbsolu, String langueNom) throws Exception { 

		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addFilterQuery("classeCheminAbsolu_indexe_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addFilterQuery("classeEtendGen_indexe_boolean:true");
		rechercheSolr.addSort("partNumero_indexe_int", ORDER.asc);

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
			File classeRepertoire = null;
			File classeFichier = null;
			StringBuilder s = new StringBuilder();
			
			String classeNomSimpleGen = null;
			String classeNomSimpleSuper = null;    
			String classeNomEnsemble = null;      
	
			for(int i = 0; i < listeRecherche.size(); i++) {
				SolrDocument doc = listeRecherche.get(i); 
				Integer partNumero = (Integer)doc.get("partNumero_stocke_int");
				if(partNumero.equals(1)) {
					classeCheminRepertoireGen = (String)doc.get("classeCheminRepertoireGen_" + langueNom + "_stocke_string");
					classeCheminGen = (String)doc.get("classeCheminGen_" + langueNom + "_stocke_string"); 
					classeRepertoire = new File(classeCheminRepertoireGen);
					classeRepertoire.mkdirs();
					classeFichier = new File(classeCheminGen);
					classeNomSimpleGen = (String)doc.get("classeNomSimpleGen_" + langueNom + "_stocke_string");
					classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + langueNom + "_stocke_string");
					classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + langueNom + "_stocke_string");
		
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
					String champVar = (String)doc.get("champVar_" + langueNom + "_stocke_string");
	
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
						String methodeVar = (String)doc.get("methodeVar_" + langueNom + "_stocke_string");
						String methodeCodeSource = (String)doc.get("methodeCodeSource_" + langueNom + "_stocke_string");
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
}
