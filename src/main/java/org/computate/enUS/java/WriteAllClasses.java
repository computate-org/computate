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
					classApiUri = (String)doc.get("classApiUri_" + languageName + "_stored_string");
					classComment = (String)doc.get("classComment_" + languageName + "_stored_string");
					classImportsGen = (List<String>)doc.get("classImportsGen_" + languageName + "_stored_strings");
					classWriteMethods = (List<String>)doc.get("classWriteMethods_stored_strings");
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
					entitySuperClassesAndMeWithoutGen = (List<String>)doc.get("entitySuperClassesAndMeWithoutGen_stored_strings");
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
					classEntityVars = (List<String>)doc.get("classEntityVars_" + languageName + "_stored_strings");
					if(classApiMethods == null)
						classApiMethods = new ArrayList<>();
					entityIndex = 0;   

					classContext = (Boolean)doc.get("classContext_stored_boolean");
					contextColor = (String)doc.get("contextColor_stored_string");
					contextIconGroup = (String)doc.get("contextIconGroup_stored_string");
					contextIconName = (String)doc.get("contextIconName_stored_string");

					contextAName = (String)doc.get("contextAName" + "_" + languageName + "_stored_string");
					contextNameSingular = (String)doc.get("contextNameSingular" + "_" + languageName + "_stored_string");
					contextNamePlural = (String)doc.get("contextNamePlural" + "_" + languageName + "_stored_string");
					contextNameVar = (String)doc.get("contextNameVar" + "_" + languageName + "_stored_string");
					contextAdjective = (String)doc.get("contextAdjective" + "_" + languageName + "_stored_string");
					contextAdjectivePlural = (String)doc.get("contextAdjectivePlural" + "_" + languageName + "_stored_string");
					contextAdjectiveVar = (String)doc.get("contextAdjectiveVar" + "_" + languageName + "_stored_string");
					contextNameAdjectiveSingular = (String)doc.get("contextNameAdjectiveSingular" + "_" + languageName + "_stored_string");
					contextNameAdjectivePlural = (String)doc.get("contextNameAdjectivePlural" + "_" + languageName + "_stored_string");
					contextThis = (String)doc.get("contextThis" + "_" + languageName + "_stored_string");
					contextA = (String)doc.get("contextA" + "_" + languageName + "_stored_string");
					contextActualName = (String)doc.get("contextActualName" + "_" + languageName + "_stored_string");
					contextAllName = (String)doc.get("contextAllName" + "_" + languageName + "_stored_string");
					contextTheName = (String)doc.get("contextTheName" + "_" + languageName + "_stored_string");
					contextTitle = (String)doc.get("contextTitle" + "_" + languageName + "_stored_string");
					contextH1 = (String)doc.get("contextH1" + "_" + languageName + "_stored_string");
					contextH2 = (String)doc.get("contextH2" + "_" + languageName + "_stored_string");
					contextH3 = (String)doc.get("contextH3" + "_" + languageName + "_stored_string");
					contextNoneNameFound = (String)doc.get("contextNoneNameFound" + "_" + languageName + "_stored_string");
					contextANameAdjective = (String)doc.get("contextANameAdjective" + "_" + languageName + "_stored_string");
					contextThisName = (String)doc.get("contextThisName" + "_" + languageName + "_stored_string");
					contextTheName = (String)doc.get("contextTheName" + "_" + languageName + "_stored_string");
					contextOfName = (String)doc.get("contextOfName" + "_" + languageName + "_stored_string");

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
//						pageCodeClassDebut(languageName);
						pageCodeClass(languageName);
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
