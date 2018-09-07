package org.computate.enUS.java;

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

/**	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 */
public class WriteGenClass extends WriteClass {

	/**	Retrieve the records for the class from the search engine, 
	 *	process them and write them into class files for each supported language.
	 */
	protected void  writeGenClass(String classAbsolutePath, String languageName) throws Exception { 

		SolrQuery solrSearch = new SolrQuery();   
		solrSearch.setQuery("*:*");
		solrSearch.setRows(1000000);
		solrSearch.addFilterQuery("classAbsolutePath_indexed_string:" + ClientUtils.escapeQueryChars(classAbsolutePath));
		solrSearch.addFilterQuery("classExtendsGen_indexed_boolean:true");
		solrSearch.addSort("partNumber_indexed_int", ORDER.asc);

		QueryResponse searchResponse = solrClientComputate.query(solrSearch);
		writeGenClass(searchResponse, languageName);
	}

	/**	Retrieve the records for the class from the search engine, 
	 *	process them and write them into class files for each supported language.
	 */
	protected void  writeGenClass(QueryResponse searchResponse, String languageName) throws Exception { 
		SolrDocumentList searchList = searchResponse.getResults();

		if(searchList.size() > 0 && (languageIndexed || !StringUtils.equals(languageName, this.languageName))) {    
			String classGenDirPath = null;
			String classPathGen = null; 
			File classDirGen = null;
			File classFileGen = null;
			StringBuilder s = new StringBuilder();
			
			String classSimpleNameGen = null;
			String classSuperSimpleName = null;    
			String classSuperSimpleNameGeneric = null;    
			String classPackageName = null;      
			String classSimpleName = null;
			String classSuperCanonicalName = null;    
			String classComment = null;      
			List<String> classImports = null;  
			List<String> classTypeParameterNames = null;  
			List<String> classSuperTypeParameterNames = null;  
			Boolean classExtendsGen = null;
	
			for(int i = 0; i < searchList.size(); i++) {
				SolrDocument doc = searchList.get(i); 
				Integer partNumber = (Integer)doc.get("partNumber_stored_int");
				if(partNumber.equals(1)) {
					classGenDirPath = (String)doc.get("classGenDirPath_" + languageName + "_stored_string");
					classPathGen = (String)doc.get("classPathGen_" + languageName + "_stored_string"); 
					classDirGen = new File(classGenDirPath);
					classDirGen.mkdirs();
					classFileGen = new File(classPathGen);
					classSimpleNameGen = (String)doc.get("classSimpleNameGen_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleName = (String)doc.get("classSuperSimpleName_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleNameGeneric = (String)doc.get("classSuperSimpleNameGeneric_" + languageName + "_stored_string");
					classPackageName = (String)doc.get("classPackageName_" + languageName + "_stored_string");
					classComment = (String)doc.get("classComment_" + languageName + "_stored_string");
					classImports = (List<String>)doc.get("classImports_" + languageName + "_stored_strings");
					classTypeParameterNames = (List<String>)doc.get("classTypeParameterNames_stored_strings");
					classSuperTypeParameterNames = (List<String>)doc.get("classSuperTypeParameterNames_stored_strings");
					classExtendsGen = (Boolean)doc.get("classExtendsGen_stored_boolean");
		
					s.append("package ").append(classPackageName).append(";\n\n");
					if(classImports.size() > 0) { 
						for(String classImport : classImports) {
							s.append("import ").append(classImport).append(";\n");
						} 
						s.append("\n");  
					}
					writeComment(s, classComment, 0); 
					s.append("public class ").append(classSimpleNameGen);
					if(classTypeParameterNames != null && classTypeParameterNames.size() > 0) {
						s.append("<");
						for(int j = 0; j < classTypeParameterNames.size(); j++) {
							String classTypeParameterName = classTypeParameterNames.get(j);
							if(i > 0)
								s.append(", ");
							s.append(classTypeParameterName);
						}
						s.append(">");
					}
					if(classSuperSimpleNameGeneric != null && !"java.lang.Object".equals(classSuperSimpleNameGeneric) && !"DEV".equals(classSuperSimpleNameGeneric)) {
						s.append(" extends ");
						if(classExtendsGen) {
							s.append(classSimpleName).append("Gen");
						} 
						else {
							s.append(classSuperSimpleName);
						}
						if(classSuperTypeParameterNames != null && classSuperTypeParameterNames.size() > 0) {
							s.append("<");
							for(int j = 0; j < classSuperTypeParameterNames.size(); j++) {
								String classSuperTypeParameterName = classSuperTypeParameterNames.get(j);
								if(i > 0)
									s.append(", ");
								s.append(classSuperTypeParameterName);
							}
							s.append(">");
						}
					}
					s.append(" {\n");
				} 
				else {
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partIsEntity = (Boolean)doc.get("partIsEntity_stored_boolean");
					String entiteVar = (String)doc.get("entiteVar_" + languageName + "_stored_string");
					String entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + languageName + "_stored_string");
	
					if(BooleanUtils.isTrue(partIsEntity)) {
						s.append("\t");
						s.append(entiteNomSimpleComplet).append(" ").append(entiteVar);
						s.append(";\n");
					}     
				}
			}
			s.append("}\n");
			FileUtils.write(classFileGen, s, Charset.forName("UTF-8"));  
		} 
	}
}
