package org.computate.enUS.java;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 */
public class WriteAllClasses extends WriteAllClassesGen<WriteApiClass> {

	/**	Retrieve the records for the class from the search engine, 
	 *	process them and write them into class files for each supported language.
	 */
	protected void  writeAllClasses(String classAbsolutePath, String languageName) throws Exception { 

		SolrQuery solrSearch = new SolrQuery();   
		solrSearch.setQuery("*:*");
		solrSearch.setRows(1000000);
		solrSearch.addFilterQuery("classAbsolutePath_indexed_string:" + ClientUtils.escapeQueryChars(classAbsolutePath));
		solrSearch.addFilterQuery("classExtendsGen_indexed_boolean:true");
		solrSearch.addSort("partNumber_indexed_int", ORDER.asc);

		QueryResponse searchResponse = solrClientComputate.query(solrSearch);
		ecrireGenClasses(searchResponse, languageName);
	}

	/**	Retrieve the records for the class from the search engine, 
	 *	process them and write them into class files for each supported language.
	 */
	protected void  writeAllClasses(QueryResponse searchResponse, String languageName) throws Exception { 
		SolrDocumentList searchList = searchResponse.getResults();

		if(searchList.size() > 0 && (languageIndexed || !StringUtils.equals(languageName, this.languageName))) {    
			for(int i = 0; i < searchList.size(); i++) {
				doc = searchList.get(i); 
				Integer partNumber = (Integer)doc.get("partNumber_stored_int");
				if(partNumber.equals(1)) {
					classGenDirPath = (String)doc.get("classGenDirPath_" + languageName + "_stored_string");
					classPathGen = (String)doc.get("classPathGen_" + languageName + "_stored_string"); 
					classPathApi = (String)doc.get("classPathApi_" + languageName + "_stored_string"); 
					classPathPage = (String)doc.get("classPathPage_" + languageName + "_stored_string"); 
					classDirGen = new File(classGenDirPath);
					classDirGen.mkdirs();
					classFileGen = new File(classPathGen);
					classFileApi = new File(classPathApi);
					o = new PrintWriter(classFileGen);
					classSimpleName = (String)doc.get("classSimpleName_" + languageName + "_stored_string");
					classSimpleNameGen = (String)doc.get("classSimpleNameGen_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleName = (String)doc.get("classSuperSimpleName_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleNameGeneric = (String)doc.get("classSuperSimpleNameGeneric_" + languageName + "_stored_string");
					classSuperCanonicalNameGeneric = (String)doc.get("classSuperCanonicalNameGeneric_" + languageName + "_stored_string");
					classPackageName = (String)doc.get("classPackageName_" + languageName + "_stored_string");
					classComment = (String)doc.get("classComment_" + languageName + "_stored_string");
					classImportsGen = (List<String>)doc.get("classImportsGen_" + languageName + "_stored_strings");
					if(classImportsGen == null)
						classImportsGen = new ArrayList<String>();
					classTypeParameterNames = (List<String>)doc.get("classTypeParameterNames_stored_strings");
					classSuperTypeParameterNames = (List<String>)doc.get("classSuperTypeParameterNames_stored_strings");
					classExtendsGen = (Boolean)doc.get("classExtendsGen_stored_boolean");
					classeEtendBase = classExtendsGen;
					classeBaseEtendGen = (Boolean)doc.get("classeBaseEtendGen_stored_boolean");
					classeEstBase = !BooleanUtils.isNotTrue(classeBaseEtendGen);
					classeContientRequeteSite = (Boolean)doc.get("classeContientRequeteSite_stored_boolean");
					classeSauvegarde = BooleanUtils.isTrue((Boolean)doc.get("classeSauvegarde_stored_boolean"));
					classeIndexe = BooleanUtils.isTrue((Boolean)doc.get("classeIndexe_stored_boolean"));
					classeModele = BooleanUtils.isTrue((Boolean)doc.get("classeModele_stored_boolean"));
					classeApi = BooleanUtils.isTrue((Boolean)doc.get("classeApi_stored_boolean"));
					classePage = BooleanUtils.isTrue((Boolean)doc.get("classePage_stored_boolean"));

					genCodeInitialiserLoin(languageName);
					genCodeIndexer(languageName);
					genCodeObtenir(languageName);
					genCodeAttribuer(languageName);
					genCodeDefinir(languageName);
					genCodePeupler(languageName);
					genCodeExiste(languageName);
					genCodeSauvegardes(languageName);
					genCodeSauvegarder(languageName);
					genCodeClasseDebut(languageName);
				} 
				else {
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partIsEntity = (Boolean)doc.get("partIsEntity_stored_boolean");
	
					if(BooleanUtils.isTrue(partIsEntity)) {
						String entiteVar = (String)doc.get("entiteVar_" + languageName + "_stored_string");
						String entiteVarCapitalise = (String)doc.get("entiteVarCapitalise_" + languageName + "_stored_string");
						String entiteNomCanonique = (String)doc.get("entiteNomCanonique_" + languageName + "_stored_string");
						String entiteNomCanoniqueGenerique = (String)doc.get("entiteNomCanoniqueGenerique_" + languageName + "_stored_string");
						String entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + languageName + "_stored_string");
						String entiteNomSimpleCompletGenerique = (String)doc.get("entiteNomSimpleCompletGenerique_" + languageName + "_stored_string");
						String entiteNomSimple = (String)doc.get("entiteNomSimple_" + languageName + "_stored_string");
						String entiteCommentaire = (String)doc.get("entiteCommentaire_" + languageName + "_stored_string");
						String entiteVarParam = (String)doc.get("entiteVarParam_" + languageName + "_stored_string");
						Boolean entiteCouverture = (Boolean)doc.get("entiteCouverture_stored_boolean");
						Boolean entiteInitialise = (Boolean)doc.get("entiteInitialise_stored_boolean");
						Boolean entiteInitLoin = (Boolean)doc.get("entiteInitLoin_stored_boolean");

						String entiteVarCleUniqueActuel = (String)doc.get("entiteVarCleUnique_stored_boolean");
						if(StringUtils.isNotEmpty(entiteVarCleUniqueActuel))
							entiteVarCleUnique = entiteVarCleUniqueActuel;
						String entiteVarSuggere = (String)doc.get("entiteVarSuggere_stored_boolean");
						String entiteVarIncremente = (String)doc.get("entiteVarIncremente_stored_boolean");
						String entiteVarCrypte = (String)doc.get("entiteVarCrypte_stored_boolean");
						String entiteVarIndexe = (String)doc.get("entiteVarIndexe_stored_boolean");
						String entiteVarStocke = (String)doc.get("entiteVarStocke_stored_boolean");
						String entiteTypeSolr = (String)doc.get("entiteTypeSolr_stored_boolean");

						Boolean entiteExact = (Boolean)doc.get("entiteExact_stored_boolean");
						Boolean entiteCleUnique = (Boolean)doc.get("entiteCleUnique_stored_boolean");
						Boolean entiteCrypte = (Boolean)doc.get("entiteCrypte_stored_boolean");
						Boolean entiteSuggere = (Boolean)doc.get("entiteSuggere_stored_boolean");
						Boolean entiteSauvegarde = (Boolean)doc.get("entiteSauvegarde_stored_boolean");
						Boolean entiteIndexe = (Boolean)doc.get("entiteIndexe_stored_boolean");
						Boolean entiteStocke = (Boolean)doc.get("entiteStocke_stored_boolean");
						Boolean entitetexte = (Boolean)doc.get("entitetexte_stored_boolean");
						Boolean entiteIncremente = (Boolean)doc.get("entiteIncremente_stored_boolean");
						Boolean entiteNomAffichage = (Boolean)doc.get("entiteNomAffichage_stored_boolean");
						Boolean entiteIgnorer = (Boolean)doc.get("entiteIgnorer_stored_boolean");
						Boolean entiteDeclarer = (Boolean)doc.get("entiteDeclarer_stored_boolean");
						Boolean entiteRechercher = (Boolean)doc.get("entiteRechercher_stored_boolean");
						Boolean entiteAttribuer = (Boolean)doc.get("entiteAttribuer_stored_boolean");
						Boolean entiteAjouter = (Boolean)doc.get("entiteAjouter_stored_boolean");
						Boolean entiteSupprimer = (Boolean)doc.get("entiteSupprimer_stored_boolean");
						Boolean entiteModifier = (Boolean)doc.get("entiteModifier_stored_boolean");
						Boolean entiteRecharger = (Boolean)doc.get("entiteRecharger_stored_boolean");
						Boolean entiteMultiligne = (Boolean)doc.get("entiteMultiligne_stored_boolean");
						Boolean entiteCles = (Boolean)doc.get("entiteCles_stored_boolean");
						Boolean entiteIndexeOuStocke = (Boolean)doc.get("entiteIndexeOuStocke_stored_boolean");

						List<String> entiteMethodesAvantVisibilite = (List<String>)doc.get("entiteMethodesAvantVisibilite_stored_strings");
						List<String> entiteMethodesAvantVar = (List<String>)doc.get("entiteMethodesAvantVar_stored_strings");
						List<String> entiteMethodesAvantParamVar = (List<String>)doc.get("entiteMethodesAvantParamVar_stored_strings");
						List<String> entiteMethodesAvantParamNomSimple = (List<String>)doc.get("entiteMethodesAvantParamNomSimple_stored_strings");
						List<Boolean> entiteMethodesAvantNomParam = (List<Boolean>)doc.get("entiteMethodesAvantNomParam_stored_booleans");

						List<String> entiteMethodesApresVisibilite = (List<String>)doc.get("entiteMethodesApresVisibilite_stored_strings");
						List<String> entiteMethodesApresVar = (List<String>)doc.get("entiteMethodesApresVar_stored_strings");
						List<String> entiteMethodesApresParamVar = (List<String>)doc.get("entiteMethodesApresParamVar_stored_strings");
						List<String> entiteMethodesApresParamNomSimple = (List<String>)doc.get("entiteMethodesApresParamNomSimple_stored_strings");
						List<Boolean> entiteMethodesApresNomParam = (List<Boolean>)doc.get("entiteMethodesApresNomParam_stored_booleans");

						genCodeEntite(languageName);
					}
				}
			}
			if(o != null) {
				if(searchList.size() > 0 && !StringUtils.equals(classAbsolutePath, classPathGen)) {
					genCodeClasseFin(languageName);
					apiCodeClasseFin(languageName);
				}
			}
		} 
	}
}
