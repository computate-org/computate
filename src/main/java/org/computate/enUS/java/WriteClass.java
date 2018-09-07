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
public class WriteClass extends IndexClass {

	protected void  writeClass(String classAbsolutePath, String languageName) throws Exception { 
		SolrQuery solrSearch = new SolrQuery();   
		solrSearch.setQuery("*:*");
		solrSearch.setRows(1000000);
		solrSearch.addFilterQuery("classAbsolutePath_indexed_string:" + ClientUtils.escapeQueryChars(classAbsolutePath));
		solrSearch.addSort("partNumber_indexed_int", ORDER.asc);

		QueryResponse searchResponse = solrClientComputate.query(solrSearch);
		writeClass(classAbsolutePath, languageName, searchResponse);
	}

	public void  writeComment(StringBuilder s, String comment, Integer tabs) {
		String tabsStr = StringUtils.repeat("\t", tabs);
		if(StringUtils.isNotEmpty(comment)) {
			String[] parts = StringUtils.split(comment, "\n");
			for(int j = 0; j < parts.length; j++) { 
				String ligne = parts[j];
				if(j == 0)
					s.append(tabsStr).append("/**\t").append(ligne).append("\n");
				else
					s.append(tabsStr).append(" *\t").append(ligne).append("\n");
			}
			s.append(tabsStr).append(" */\n");  
		} 
	}

	/**	Retrieve the records for the class from the search engine, 
	 *	process them and write them into class files for each supported language.
	 */
	protected void  writeClass(String classAbsolutePath, String languageName, QueryResponse searchResponse) throws Exception { 
		SolrDocumentList searchList = searchResponse.getResults(); 

		if(searchList.size() > 0) {
			String classDirPath = null;
			String classPath = null; 
			File classDir = null;
			File classFile = null;
			StringBuilder s = new StringBuilder();
			
			String classSimpleName = null;
			String classSuperSimpleName = null;    
			String classSuperCanonicalName = null;    
			String classPackageName = null;
			String classComment = null;      
			List<String> classImports = null;  
			List<String> classTypeParameterNames = null;  
			List<String> classSuperTypeParameterNames = null;  
			Boolean classExtendsGen = null;
	
			for(int i = 0; i < searchList.size(); i++) { 
				SolrDocument doc = searchList.get(i); 
				Integer partNumber = (Integer)doc.get("partNumber_stored_int");
				if(partNumber == null)
					partNumber = 2;
				if(partNumber.equals(1)) {
					classDirPath = (String)doc.get("classDirPath_" + languageName + "_stored_string");
					classPath = (String)doc.get("classPath_" + languageName + "_stored_string"); 
					classDir = new File(classDirPath);
					classDir.mkdirs();
					classFile = new File(classPath);
					classSimpleName = (String)doc.get("classSimpleName_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleName = (String)doc.get("classSuperSimpleName_" + languageName + "_stored_string");
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
					s.append("public class ").append(classSimpleName);
					if(classTypeParameterNames != null && classTypeParameterNames.size() > 0) {
						s.append("<");
						for(int j = 0; j < classTypeParameterNames.size(); j++) {
							String classTypeParameterName = classTypeParameterNames.get(j);
							if(j > 0)
								s.append(", ");
							s.append(classTypeParameterName);
						}
						s.append(">");
					}
					if(!"java.lang.Object".equals(classSuperCanonicalName)) {
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
					Boolean partIsField = (Boolean)doc.get("partIsField_stored_boolean");
					Boolean partIsMethod = (Boolean)doc.get("partIsMethod_stored_boolean");
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partIsEntity = (Boolean)doc.get("partIsEntity_stored_boolean");
	
					if(BooleanUtils.isTrue(partIsField)) {
						String fieldComment = (String)doc.get("fieldComment_" + languageName + "_stored_string");
						String fieldVar = (String)doc.get("fieldVar_" + languageName + "_stored_string");
						String fieldSimpleNameComplete = (String)doc.get("fieldSimpleNameComplete_" + languageName + "_stored_string");
						String fieldSourceCode = (String)doc.get("fieldSourceCode_" + languageName + "_stored_string");

						s.append("\n"); 
						writeComment(s, fieldComment, 1);
						s.append("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsPublic_stored_boolean")))
							s.append("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsProtected_stored_boolean")))
							s.append("protected ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsPrivate_stored_boolean")))
							s.append("private ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsStatic_stored_boolean")))
							s.append("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsFinal_stored_boolean")))
							s.append("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsAbstract_stored_boolean")))
							s.append("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsNative_stored_boolean")))
							s.append("native ");
						
						s.append(fieldSimpleNameComplete).append(" ").append(fieldVar);
						if(StringUtils.isNotEmpty(fieldSourceCode))
							s.append(" = ").append(fieldSourceCode);
						s.append(";\n");
					}     
	
					if(BooleanUtils.isTrue(partIsMethod)) {
						String methodVar = (String)doc.get("methodVar_" + languageName + "_stored_string");
						String methodSourceCode = (String)doc.get("methodSourceCode_" + languageName + "_stored_string");
						String methodComment = (String)doc.get("methodComment_" + languageName + "_stored_string");
						List<String> methodExceptionSimpleNameCompleteList = (List<String>)doc.get("methodExceptionSimpleNameComplete_stored_strings");
						List<String> methodTypeParameterNames = (List<String>)doc.get("methodTypeParameterNames_stored_strings");
						List<String> methodAnnotationsSimpleNameCompleteList = (List<String>)doc.get("methodAnnotationsSimpleNameComplete_" + languageName + "_stored_strings");
						List<String> methodAnnotationsCodeBlockList = (List<String>)doc.get("methodAnnotationsCodeBlock_" + languageName + "_stored_strings");

						s.append("\n"); 
						writeComment(s, methodComment, 1);
						if(methodAnnotationsSimpleNameCompleteList != null && methodAnnotationsCodeBlockList != null) {
							for(int j = 0; j < methodAnnotationsSimpleNameCompleteList.size(); j++) {
								String methodAnnotationSimpleNameComplete = methodAnnotationsSimpleNameCompleteList.get(j);
								String methodAnnotationCodeBlock = methodAnnotationsCodeBlockList.get(j);
								s.append("\t@").append(methodAnnotationSimpleNameComplete).append(methodAnnotationCodeBlock).append("\n");
							}
						}
						s.append("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsPublic_stored_boolean")))
							s.append("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsProtected_stored_boolean")))
							s.append("protected ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsPrivate_stored_boolean")))
							s.append("private ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsStatic_stored_boolean")))
							s.append("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsFinal_stored_boolean")))
							s.append("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsAbstract_stored_boolean")))
							s.append("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsNative_stored_boolean")))
							s.append("native ");


						if(methodTypeParameterNames != null && methodTypeParameterNames.size() > 0) {
							s.append("<");
							for(int j = 0; j < methodTypeParameterNames.size(); j++) {
								String methodTypeParameterName = methodTypeParameterNames.get(j);
								if(j > 0)
									s.append(", ");
								s.append(methodTypeParameterName);
							}
							s.append("> ");
						}

						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsVoid_stored_boolean")))
							s.append("void ");
						else
							s.append((String)doc.get("methodReturnSimpleNameComplete_" + languageName + "_stored_string"));
						s.append(" ");
						s.append(methodVar);
						s.append("(");
						List<String> methodParamSimpleNameCompleteList = (List<String>)doc.get("methodParamSimpleNameComplete_" + languageName + "_stored_strings"); 
						List<String> methodParamVarList = (List<String>)doc.get("methodParamVar_" + languageName + "_stored_strings");
						List<Boolean> methodParamVariableArgsList = (List<Boolean>)doc.get("methodParamVariableArgs_stored_booleans");
						if(methodParamSimpleNameCompleteList != null && methodParamVarList != null && methodParamSimpleNameCompleteList.size() == methodParamVarList.size()) {
							for(int j = 0; j < methodParamVarList.size(); j++) {
								String methodParamSimpleNameComplete = methodParamSimpleNameCompleteList.get(j);
								String methodParamVar = methodParamVarList.get(j);
								Boolean methodParamVariableArgs = methodParamVariableArgsList.get(j);
								if(j > 0)
									s.append(", ");
								s.append(methodParamSimpleNameComplete);

								if(methodParamVariableArgs)
									s.append("...");
								else
									s.append(" ");

								s.append(methodParamVar);
							}
						}    
						s.append(")");
						if(methodExceptionSimpleNameCompleteList != null && methodExceptionSimpleNameCompleteList.size() > 0) {
							s.append(" throws ");
							for(int j = 0; j < methodExceptionSimpleNameCompleteList.size(); j++) {
								String methodExceptionSimpleNameComplete = methodExceptionSimpleNameCompleteList.get(j);
								if(j > 0)
									s.append(", ");
								s.append(methodExceptionSimpleNameComplete);
							}
						}
						s.append(" {");
						s.append(methodSourceCode);
						s.append("}\n");
					} 
					else if(BooleanUtils.isTrue(partIsEntity)) {
						
					}
				}
			}
			s.append("}\n"); 
			if(searchList.size() > 0 && !StringUtils.equals(classAbsolutePath, classPath)) {
				System.out.println("Write: " + classPath); 
				FileUtils.write(classFile, s, Charset.forName("UTF-8")); 
			}
		}
		else {
			System.err.println("No file was found in the search engine. ");
		}
	}
}
