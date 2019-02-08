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

/**	
 *	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 **/
public class WriteAllClasses extends WritePageClass {

	/**	
	 *	Retrieve the records for the class from the search engine, 
	 *	process them and write them into class files for each supported language.
	 **/
	public void  writeGenClasses(String classAbsolutePath, String languageName) throws Exception, Exception { 

		SolrQuery solrSearch = new SolrQuery();   
		solrSearch.setQuery("*:*");
		solrSearch.setRows(1000000);
		solrSearch.addFilterQuery("classAbsolutePath_indexed_string:" + ClientUtils.escapeQueryChars(classAbsolutePath));
		solrSearch.addFilterQuery("classExtendsGen_indexed_boolean:true");
		solrSearch.addSort("partNumber_indexed_int", ORDER.asc);

		QueryResponse searchResponse = solrClientComputate.query(solrSearch);
		writeGenClasses(searchResponse, languageName);
	}

	/**	
	 *	Retrieve the records for the class from the search engine, 
	 *	process them and write them into class files for each supported language.
	 **/
	public void  writeGenClasses(QueryResponse searchResponse, String languageName) throws Exception, Exception { 
		SolrDocumentList searchList = searchResponse.getResults();

		if(searchList.size() > 0 && (languageIndexed || !StringUtils.equals(languageName, this.languageName))) {    
			for(int i = 0; i < searchList.size(); i++) {
				doc = searchList.get(i); 
				Integer partNumber = (Integer)doc.get("partNumber_stored_int");
				if(partNumber.equals(1)) {
					classDoc = doc;

					classDirPathGen = (String)doc.get("classDirPathGen_" + languageName + "_stored_string");
					classPathGen = (String)doc.get("classPathGen_" + languageName + "_stored_string"); 
					classPathApiPackageInfo = (String)doc.get("classPathApiPackageInfo_" + languageName + "_stored_string"); 
					classPathGenApiServiceImpl = (String)doc.get("classPathGenApiServiceImpl_" + languageName + "_stored_string"); 
					classPathApiServiceImpl = (String)doc.get("classPathApiServiceImpl_" + languageName + "_stored_string"); 
					classPathGenApiService = (String)doc.get("classPathGenApiService_" + languageName + "_stored_string"); 
					classPathGenPage = (String)doc.get("classPathGenPage_" + languageName + "_stored_string"); 
					classPathPage = (String)doc.get("classPathPage_" + languageName + "_stored_string"); 
					classPathPageCss = (String)doc.get("classPathPageCss_" + languageName + "_stored_string"); 
					classPathPageJs = (String)doc.get("classPathPageJs_" + languageName + "_stored_string"); 

					classDirGen = new File(classDirPathGen);
					classDirGen.mkdirs();

					classFileGen = new File(classPathGen);
					if(classPathApiPackageInfo != null)
						classFileApiPackageInfo = new File(classPathApiPackageInfo);
					if(classPathGenApiServiceImpl != null)
						classFileGenApiServiceImpl = new File(classPathGenApiServiceImpl);
					if(classPathApiServiceImpl != null)
						classFileApiServiceImpl = new File(classPathApiServiceImpl);
					if(classPathGenApiService != null)
						classFileGenApiService = new File(classPathGenApiService);
					if(classPathGenPage != null)
						classFilePageGen = new File(classPathGenPage);
					if(classPathPage != null)
						classFilePage = new File(classPathPage);
					if(classPathPageCss != null)
						classFilePageCss = new File(classPathPageCss);
					if(classPathPageJs != null)
						classFilePageJs = new File(classPathPageJs);

					o = AllWriter.create(classFileGen);
					classSimpleName = (String)doc.get("classSimpleName_" + languageName + "_stored_string");
					classSimpleNameGen = (String)doc.get("classSimpleNameGen_" + languageName + "_stored_string");
					classCanonicalName = (String)doc.get("classCanonicalName_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleName = (String)doc.get("classSuperSimpleName_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleNameGeneric = (String)doc.get("classSuperSimpleNameGeneric_" + languageName + "_stored_string");
					classSuperCanonicalNameGeneric = (String)doc.get("classSuperCanonicalNameGeneric_" + languageName + "_stored_string");
					classPackageName = (String)doc.get("classPackageName_" + languageName + "_stored_string");
					classSimpleNameApiServiceImpl = (String)doc.get("classSimpleNameApiServiceImpl_" + languageName + "_stored_string");
					classSimpleNameGenApiServiceImpl = (String)doc.get("classSimpleNameGenApiServiceImpl_" + languageName + "_stored_string");
					classSimpleNameGenApiService = (String)doc.get("classSimpleNameGenApiService_" + languageName + "_stored_string");
					classVarPrimaryKey = (String)doc.get("classVarPrimaryKey_" + languageName + "_stored_string");
					classVarUniqueKey = (String)doc.get("classVarUniqueKey_" + languageName + "_stored_string");
					classPageUri = (String)doc.get("classPageUri_" + languageName + "_stored_string");
					classApiUri = (String)doc.get("classApiUri_" + languageName + "_stored_string");
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
					classInitDeepExceptions = (List<String>)doc.get("classInitDeepExceptions_stored_strings");
					if(classInitDeepExceptions == null)
						classInitDeepExceptions = new ArrayList<String>();
					classParameterTypeNames = (List<String>)doc.get("classParameterTypeNames_stored_strings");
					classSuperParameterTypeNames = (List<String>)doc.get("classSuperParameterTypeNames_stored_strings");
					classExtendsGen = (Boolean)doc.get("classExtendsGen_stored_boolean");
					classBaseExtendsGen = (Boolean)doc.get("classBaseExtendsGen_stored_boolean");
					classExtendsBase = (Boolean)doc.get("classExtendsBase_stored_boolean");
					classIsBase = (Boolean)doc.get("classIsBase_stored_boolean");
					classInitDeep = (Boolean)doc.get("classInitDeep_stored_boolean");
					classSaved = BooleanUtils.isTrue((Boolean)doc.get("classSaved_stored_boolean"));
					classIndexed = BooleanUtils.isTrue((Boolean)doc.get("classIndexed_stored_boolean"));
					classModel = BooleanUtils.isTrue((Boolean)doc.get("classModel_stored_boolean"));
					classApi = BooleanUtils.isTrue((Boolean)doc.get("classApi_stored_boolean"));
					classPage = BooleanUtils.isTrue((Boolean)doc.get("classPage_stored_boolean"));
					classRolesFound = BooleanUtils.isTrue((Boolean)doc.get("classRolesFound_stored_boolean"));
					classRoles = (List<String>)doc.get("classRoles_" + languageName + "_stored_strings");
					classApiMethods = (List<String>)doc.get("classApiMethods_stored_strings");
					if(classApiMethods == null)
						classApiMethods = new ArrayList<>();
					entityIndex = 0;   

					writerGenClass = AllWriter.create(classFileGen);
					if(classApi && writeApi) {
//						if(classFileApiPackageInfo != null && !classFileApiPackageInfo.exists())
//							writerApiPackageInfo = AllWriter.create(classFileApiPackageInfo);
						if(classFileGenApiServiceImpl != null)
							writerGenApiServiceImpl = AllWriter.create(classFileGenApiServiceImpl);
						if(classFileApiServiceImpl != null && !classFileApiServiceImpl.exists())
//						if(classFileApiServiceImpl != null)
							writerApiServiceImpl = AllWriter.create(classFileApiServiceImpl);
						if(classFileGenApiService != null)
							writerGenApiService = AllWriter.create(classFileGenApiService);
					}
//					writerPageClass = new PrintWriter(classFilePage);
					if(classPage && writeApi) {
						if(classFilePage != null && !classFilePage.exists())
							writerPageClass = AllWriter.create(classFilePage);
						if(classFilePageGen != null)
							writerGenPageClass = AllWriter.create(classFilePageGen);
						if(classFilePageCss != null)
							writerPageCss = AllWriter.create(classFilePageCss);
						if(classFilePageJs != null)
							writerPageJs = AllWriter.create(classFilePageJs);
					}

					genCodeInit();
					o = writerGenClass;

					genCodeInitDeep(languageName);
					genCodeSiteRequest(languageName);
					genCodeIndex(languageName);
					genCodeObtain(languageName);
					genCodeAttribute(languageName);
					genCodePut(languageName);
					genCodePopulate(languageName);
					genCodeExists(languageName); 
					genCodeSaves(languageName);
					genCodeClassBegin(languageName);
					if(classApi)
						apiCodeClassBegin(languageName);
					if(classPage)
//						pageCodeClasseDebut(languageName);
						pageCodeClasse(languageName);
				} 
				else {
					Boolean partIsConstructor = (Boolean)doc.get("partIsConstructor_stored_boolean");
					Boolean partIsEntity = (Boolean)doc.get("partIsEntity_stored_boolean");
	
					if(BooleanUtils.isTrue(partIsConstructor)) {
						genCodeConstructor(languageName);
					}
					else if(BooleanUtils.isTrue(partIsEntity)) {
						genCodeEntity(languageName);
					}
				}
			}
			if(o != null) {
				if(searchList.size() > 0 && !StringUtils.equals(classAbsolutePath, classPathGen)) {
					genCodeClassEnd(languageName);
					if(classApi) {
//						writeApiPackageInfo(languageName);
						writeGenApiService(languageName);
						writeGenApiServiceImpl(languageName);
						writeApiServiceImpl(languageName);
					}
				}
			}
		} 
	}
}
