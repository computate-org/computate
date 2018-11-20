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
					classPathApiGen = (String)doc.get("classPathApiGen_" + languageName + "_stored_string"); 
					classPathPageGen = (String)doc.get("classPathPageGen_" + languageName + "_stored_string"); 
					classDirGen = new File(classGenDirPath);
					classDirGen.mkdirs();
					classFileGen = new File(classPathGen);
					classFileApi = new File(classPathApiGen);
					classFilePage = new File(classPathPageGen);
					o = new PrintWriter(classFileGen);
					classSimpleName = (String)doc.get("classSimpleName_" + languageName + "_stored_string");
					classSimpleNameGen = (String)doc.get("classSimpleNameGen_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleName = (String)doc.get("classSuperSimpleName_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleNameGeneric = (String)doc.get("classSuperSimpleNameGeneric_" + languageName + "_stored_string");
					classSuperCanonicalNameGeneric = (String)doc.get("classSuperCanonicalNameGeneric_" + languageName + "_stored_string");
					classPackageName = (String)doc.get("classPackageName_" + languageName + "_stored_string");
					classSimpleNameApi = (String)doc.get("classSimpleNameApi_" + languageName + "_stored_string");
					classSimpleNameApiGen = (String)doc.get("classSimpleNameApiGen_" + languageName + "_stored_string");
					classePageUri = (String)doc.get("classePageUri_" + languageName + "_stored_string");
					classeApiUri = (String)doc.get("classeApiUri_" + languageName + "_stored_string");
					classComment = (String)doc.get("classComment_" + languageName + "_stored_string");
					classImportsGen = (List<String>)doc.get("classImportsGen_" + languageName + "_stored_strings");
					if(classImportsGen == null)
						classImportsGen = new ArrayList<String>();
					classImportsGenApi = (List<String>)doc.get("classImportsGenApi_" + languageName + "_stored_strings");
					if(classImportsGenApi == null)
						classImportsGenApi = new ArrayList<String>();
					classImportsGenPage = (List<String>)doc.get("classImportsGenPage_" + languageName + "_stored_strings");
					if(classImportsGenPage == null)
						classImportsGenPage = new ArrayList<String>();
					classTypeParameterNames = (List<String>)doc.get("classTypeParameterNames_stored_strings");
					classSuperTypeParameterNames = (List<String>)doc.get("classSuperTypeParameterNames_stored_strings");
					classExtendsGen = (Boolean)doc.get("classExtendsGen_stored_boolean");
					classeBaseEtendGen = (Boolean)doc.get("classeBaseEtendGen_stored_boolean");
					classeEtendBase = (Boolean)doc.get("classeEtendBase_stored_boolean");
					classeEstBase = (Boolean)doc.get("classeEstBase_stored_boolean");
					classeInitLoin = (Boolean)doc.get("classeInitLoin_stored_boolean");
					classeSauvegarde = BooleanUtils.isTrue((Boolean)doc.get("classeSauvegarde_stored_boolean"));
					classeIndexe = BooleanUtils.isTrue((Boolean)doc.get("classeIndexe_stored_boolean"));
					classeModele = BooleanUtils.isTrue((Boolean)doc.get("classeModele_stored_boolean"));
					classeApi = BooleanUtils.isTrue((Boolean)doc.get("classeApi_stored_boolean"));
					classePage = BooleanUtils.isTrue((Boolean)doc.get("classePage_stored_boolean"));
					classeRolesTrouve = BooleanUtils.isTrue((Boolean)doc.get("classeRolesTrouve_stored_boolean"));

					auteurGenClasse = new PrintWriter(classFileGen);
					if(classeApi)
						auteurApiGenClasse = new PrintWriter(classFileApi);
//					auteurPageClasse = new PrintWriter(classFilePage);

					genCodeInit();
					o = auteurGenClasse;

					genCodeInitialiserLoin(languageName);
					genCodeRequeteSite(languageName);
					genCodeIndexer(languageName);
					genCodeObtenir(languageName);
					genCodeAttribuer(languageName);
					genCodeDefinir(languageName);
					genCodePeupler(languageName);
					genCodeExiste(languageName);
					genCodeSauvegardes(languageName);
					genCodeSauvegarder(languageName);
					genCodeClasseDebut(languageName);
					if(classeApi)
						apiCodeClasseDebut(languageName);
				} 
				else {
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partIsEntity = (Boolean)doc.get("partIsEntity_stored_boolean");
	
					if(BooleanUtils.isTrue(partIsEntity)) {
						genCodeEntite(languageName);
					}
				}
			}
			if(o != null) {
				if(searchList.size() > 0 && !StringUtils.equals(classAbsolutePath, classPathGen)) {
					genCodeClasseFin(languageName);
					if(classeApi)
						apiCodeClasseFin(languageName);
				}
			}
		} 
	}
}
