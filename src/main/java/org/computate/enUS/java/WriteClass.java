package org.computate.enUS.java;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
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

	protected PrintWriter auteurClasse;

	PrintWriter o;

	String languageName;

	protected void  writeClass(String classAbsolutePath, String languageName) throws Exception, Exception { 
		SolrQuery solrSearch = new SolrQuery();   
		solrSearch.setQuery("*:*");
		solrSearch.setRows(1000000);
		solrSearch.addFilterQuery("classAbsolutePath_indexed_string:" + ClientUtils.escapeQueryChars(classAbsolutePath));
		solrSearch.addSort("partNumber_indexed_int", ORDER.asc);

		QueryResponse searchResponse = solrClientComputate.query(solrSearch);
		writeClass(classAbsolutePath, languageName, searchResponse);
	}

	public void  writeComment(String comment, Integer tabs) {
		String tabsStr = StringUtils.repeat("\t", tabs);
		if(StringUtils.isNotEmpty(comment)) {
			String[] parts = StringUtils.split(comment, "\n");
			for(int j = 0; j < parts.length; j++) { 
				String ligne = parts[j];
				if(j == 0)
					l(tabsStr, "/**\t", ligne);
				else
					l(tabsStr, " *\t", ligne);
			}
			l(tabsStr, " */");  
		} 
	}

	/**	Retrieve the records for the class from the search engine, 
	 *	process them and write them into class files for each supported language.
	 */
	protected void  writeClass(String classAbsolutePath, String languageName, QueryResponse searchResponse) throws Exception, Exception { 
		SolrDocumentList searchList = searchResponse.getResults(); 

		if(searchList.size() > 0) {
			String classDirPath = null;
			String classPath = null; 
			File classDir = null;
			File classFile = null;
			
			String classSimpleName = null;
			String classSuperSimpleName = null;    
			String classSuperSimpleNameGeneric = null;    
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
					classAbsolutePath = (String)doc.get("classAbsolutePath_stored_string"); 
					if(StringUtils.equals(classPath, classAbsolutePath))
						break;
					classDir = new File(classDirPath);
					classDir.mkdirs();
					classFile = new File(classPath);
					classSimpleName = (String)doc.get("classSimpleName_" + languageName + "_stored_string");
					classSuperCanonicalName = (String)doc.get("classSuperCanonicalName_" + languageName + "_stored_string");
					classSuperSimpleName = (String)doc.get("classSuperSimpleName_" + languageName + "_stored_string");
					classSuperSimpleNameGeneric = (String)doc.get("classSuperSimpleNameGeneric_" + languageName + "_stored_string");
					classPackageName = (String)doc.get("classPackageName_" + languageName + "_stored_string");
					classComment = (String)doc.get("classComment_" + languageName + "_stored_string");
					classImports = (List<String>)doc.get("classImports_" + languageName + "_stored_strings");
					classTypeParameterNames = (List<String>)doc.get("classTypeParameterNames_stored_strings");
					classSuperTypeParameterNames = (List<String>)doc.get("classSuperTypeParameterNames_stored_strings");
					classExtendsGen = (Boolean)doc.get("classExtendsGen_stored_boolean");

					auteurClasse = new PrintWriter(classFile);
					o = auteurClasse;
		
					l("package ", classPackageName, ";"); 
					l();
					if(classImports != null && classImports.size() > 0) { 
						for(String classImport : classImports) {
							l("import ", classImport, ";");
						} 
						l();  
					}
					writeComment(classComment, 0); 
					s("public class ", classSimpleName);

					if(classTypeParameterNames != null && classTypeParameterNames.size() > 0) {
						s("<");
						for(int j = 0; j < classTypeParameterNames.size(); j++) {
							String classTypeParameterName = classTypeParameterNames.get(j);
							if(j > 0)
								s(", ");
							s(classTypeParameterName);
						}
						s(">");
					}

					if(!"java.lang.Object".equals(classSuperCanonicalName)) {
						s(" extends ");
						if(classExtendsGen) {
							s(classSimpleName, "Gen");
						} 
						else {
							s(classSuperSimpleName);
						}

						if(StringUtils.isNotEmpty(classSuperSimpleNameGeneric)) {
							s("<", classSuperSimpleNameGeneric, ">");
						}
						else if(classSuperTypeParameterNames != null && classSuperTypeParameterNames.size() > 0) {
							s("<");
							for(int j = 0; j < classSuperTypeParameterNames.size(); j++) {
								String classSuperTypeParameterName = classSuperTypeParameterNames.get(j);
								if(i > 0)
									s(", ");
								s(classSuperTypeParameterName);
							}
							s(">");
						}
					}
					l(" {");
				} 
				else {     
					Boolean partIsField = (Boolean)doc.get("partIsField_stored_boolean");
					Boolean partIsMethod = (Boolean)doc.get("partIsMethod_stored_boolean");
					Boolean partIsConstructor = (Boolean)doc.get("partIsConstructor_stored_boolean");
					Boolean partIsEntity = (Boolean)doc.get("partIsEntity_stored_boolean");
	
					if(BooleanUtils.isTrue(partIsField)) {
						String fieldComment = (String)doc.get("fieldComment_" + languageName + "_stored_string");
						String fieldVar = (String)doc.get("fieldVar_" + languageName + "_stored_string");
						String fieldSimpleNameComplete = (String)doc.get("fieldSimpleNameComplete_" + languageName + "_stored_string");
						String fieldSourceCode = (String)doc.get("fieldSourceCode_" + languageName + "_stored_string");

						l(); 
						writeComment(fieldComment, 1);
						s("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsPublic_stored_boolean")))
							s("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsProtected_stored_boolean")))
							s("protected ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsPrivate_stored_boolean")))
							s("private ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsStatic_stored_boolean")))
							s("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsFinal_stored_boolean")))
							s("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsAbstract_stored_boolean")))
							s("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("fieldIsNative_stored_boolean")))
							s("native ");
						
						s(fieldSimpleNameComplete, " ", fieldVar);
						if(StringUtils.isNotEmpty(fieldSourceCode))
							s(" = ", fieldSourceCode);
						l(";");
					}     
	
					if(BooleanUtils.isTrue(partIsMethod)) {
						String methodVar = (String)doc.get("methodVar_" + languageName + "_stored_string");
						String methodSourceCode = (String)doc.get("methodSourceCode_" + languageName + "_stored_string");
						String methodComment = (String)doc.get("methodComment_" + languageName + "_stored_string");
						List<String> methodExceptionsSimpleNameComplete = (List<String>)doc.get("methodExceptionsSimpleNameComplete_stored_strings");
						List<String> methodTypeParameterNames = (List<String>)doc.get("methodTypeParameterNames_" + languageName + "_stored_strings");
						List<String> methodAnnotationsSimpleNameCompleteList = (List<String>)doc.get("methodAnnotationsSimpleNameComplete_" + languageName + "_stored_strings");
						List<String> methodAnnotationsCodeBlockList = (List<String>)doc.get("methodAnnotationsCodeBlock_" + languageName + "_stored_strings");

						l(""); 
						writeComment(methodComment, 1);
						if(methodAnnotationsSimpleNameCompleteList != null && methodAnnotationsCodeBlockList != null) {
							for(int j = 0; j < methodAnnotationsSimpleNameCompleteList.size(); j++) {
								String methodAnnotationSimpleNameComplete = methodAnnotationsSimpleNameCompleteList.get(j);
								String methodAnnotationCodeBlock = methodAnnotationsCodeBlockList.get(j);
								l("\t@", methodAnnotationSimpleNameComplete, methodAnnotationCodeBlock, "");
							}
						}
						s("\t");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsPublic_stored_boolean")))
							s("public ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsProtected_stored_boolean")))
							s("protected ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsPrivate_stored_boolean")))
							s("private ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsStatic_stored_boolean")))
							s("static ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsFinal_stored_boolean")))
							s("final ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsAbstract_stored_boolean")))
							s("abstract ");
						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsNative_stored_boolean")))
							s("native ");


						if(methodTypeParameterNames != null && methodTypeParameterNames.size() > 0) {
							s("<");
							for(int j = 0; j < methodTypeParameterNames.size(); j++) {
								String methodTypeParameterName = methodTypeParameterNames.get(j);
								if(j > 0)
									s(", ");
								s(methodTypeParameterName);
							}
							s("> ");
						}

						if(BooleanUtils.isTrue((Boolean)doc.get("methodIsVoid_stored_boolean")))
							s("void ");
						else
							s((String)doc.get("methodReturnSimpleNameComplete_" + languageName + "_stored_string"));
						s(" ");
						s(methodVar);
						s("(");
						List<String> methodParamsSimpleNameComplete = (List<String>)doc.get("methodParamsSimpleNameComplete_" + languageName + "_stored_strings"); 
						List<String> methodParamsVar = (List<String>)doc.get("methodParamsVar_" + languageName + "_stored_strings");
						List<Boolean> methodParamsVariableArgs = (List<Boolean>)doc.get("methodParamsVariableArgs_stored_booleans");
						if(methodParamsSimpleNameComplete != null && methodParamsVar != null && methodParamsSimpleNameComplete.size() == methodParamsVar.size()) {
							for(int j = 0; j < methodParamsVar.size(); j++) {
								String methodParamSimpleNameComplete = methodParamsSimpleNameComplete.get(j);
								String methodParamVar = methodParamsVar.get(j);
								Boolean methodParamVariableArgs = methodParamsVariableArgs.get(j);
								if(j > 0)
									s(", ");
								s(methodParamSimpleNameComplete);

								if(methodParamVariableArgs)
									s("...");
								else
									s(" ");

								s(methodParamVar);
							}
						}    
						s(")");
						if(methodExceptionsSimpleNameComplete != null && methodExceptionsSimpleNameComplete.size() > 0) {
							s(" throws ");
							for(int j = 0; j < methodExceptionsSimpleNameComplete.size(); j++) {
								String methodExceptionSimpleNameComplete = methodExceptionsSimpleNameComplete.get(j);
								if(j > 0)
									s(", ");
								s(methodExceptionSimpleNameComplete);
							}
						}
						s(" {");
						s(methodSourceCode);
						l("}");
					} 
					else if(BooleanUtils.isTrue(partIsEntity)) {
						
					}
				}
			}
			if(o != null) {
				if(searchList.size() > 0 && !StringUtils.equals(classAbsolutePath, classPath)) {
					l("}"); 

					System.out.println("Write: " + classPath); 
					o.flush();
					o.close();
				}
			}
		}
		else {
			System.err.println("No file was found in the search engine. ");
		}
	}

	public WriteClass o(PrintWriter o) {
		this.o = o;
		return this;
	}

	public WriteClass languageName(String languageName) {
		this.languageName = languageName;
		return this;
	}

	public void  s(Object...objects) {
		for(Object object : objects)
			if(object != null)
				o.append(object.toString());
	}

	public void  t(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			o.append("\t");
		for(Object object : objects)
			if(object != null)
				o.append(object.toString());
	}

	public void  l(Object...objects) {
		for(Object object : objects)
			if(object != null)
				o.append(object.toString());
		o.append("\n");
	}

	public void  tl(int numberTabs, Object...objects) {
		for(int i = 0; i < numberTabs; i++)
			o.append("\t");
		for(Object object : objects)
			if(object != null)
				o.append(object.toString());
		o.append("\n");
	}
}
