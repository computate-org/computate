package org.computate.enUS.java;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaConstructor;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaGenericDeclaration;
import com.thoughtworks.qdox.model.JavaMember;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.JavaType;
import com.thoughtworks.qdox.model.JavaTypeVariable;
import com.thoughtworks.qdox.model.impl.DefaultJavaParameterizedType;

public class IndexClass extends RegarderClasseBase {

	public void  populateQdoxSuperClassesInterfacesAndMe(JavaClass c, ArrayList<JavaClass> qdoxSuperClasses, ArrayList<JavaClass> qdoxSuperClassesAndMe, ArrayList<JavaClass> qdoxSuperClassesAndInterfaces, ArrayList<JavaClass> qdoxSuperClassesInterfacesAndMe) throws Exception { 
		if(c != null) {
			JavaClass superClass = c.getSuperJavaClass();
			List<JavaClass> interfacesImplemented = c.getInterfaces();

			for(JavaClass interfaceQdox : interfacesImplemented) {
				if(interfaceQdox != null && !interfaceQdox.getCanonicalName().equals("java.lang.Object") && !c.equals(interfaceQdox)) {
					qdoxSuperClassesInterfacesAndMe.add(interfaceQdox);
					qdoxSuperClassesAndInterfaces.add(superClass);
					populateQdoxSuperClassesInterfacesAndMe(interfaceQdox, qdoxSuperClasses, qdoxSuperClassesAndMe, qdoxSuperClassesAndInterfaces, qdoxSuperClassesInterfacesAndMe); // Doesn't seem to work for interfaces that extend other interfaces.
				}
			}
			qdoxSuperClassesAndMe.add(c);
			if(superClass != null && !superClass.getCanonicalName().equals("java.lang.Object") && !c.equals(superClass)) {
				qdoxSuperClassesInterfacesAndMe.add(superClass);
				qdoxSuperClassesAndMe.add(superClass);
				qdoxSuperClassesAndInterfaces.add(superClass);
				qdoxSuperClasses.add(superClass);
				populateQdoxSuperClassesInterfacesAndMe(superClass, qdoxSuperClasses, qdoxSuperClassesAndMe, qdoxSuperClassesAndInterfaces, qdoxSuperClassesInterfacesAndMe);
			}
		}
	}

	protected Boolean storeSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_string"), fieldValue);
		return fieldValue;
	}

	protected Boolean storeSolr(SolrInputDocument doc, String fieldName, String languageName, Boolean fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String storeSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_string"), fieldValue);
		return fieldValue;
	}

	protected String storeListSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_strings"), fieldValue);
		return fieldValue;
	}

	protected Boolean storeListSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_booleans"), fieldValue);
		return fieldValue;
	}

	protected String storeSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String storeListSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> storeSolr(SolrInputDocument doc, String fieldName, String languageName, List<String> fieldValues) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			for(String fieldValue : fieldValues) {
				doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
			}
		}
		return fieldValues;
	}

	protected Boolean indexSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexListSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_indexed_strings"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexSolr(SolrInputDocument doc, String fieldName, String languageName, Boolean fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected String indexSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexListSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> indexSolr(SolrInputDocument doc, String fieldName, String languageName, List<String> fieldValues) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			for(String fieldValue : fieldValues) {
				doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
			}
		}
		return fieldValues;
	}

	protected Long indexStoreSolr(SolrInputDocument doc, String fieldName, Long fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_long"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_long"), fieldValue);
		return fieldValue;
	}

	protected Integer indexStoreSolr(SolrInputDocument doc, String fieldName, Integer fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_int"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_int"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexStoreSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_boolean"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_boolean"), fieldValue);
		return fieldValue;
	}

	protected Date indexStoreSolr(SolrInputDocument doc, String fieldName, Date fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_date"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_date"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexStoreSolr(SolrInputDocument doc, String fieldName, String languageName, Boolean fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexStoreSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_string"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected String indexStoreListSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception {
		doc.addField(concat(fieldName, "_stored_strings"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_strings"), fieldValue);
		return fieldValue;
	}

	protected String indexStoreSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexStoreListSolr(SolrInputDocument doc, String fieldName, String fieldValue, String valeurChamp) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> indexStoreSolr(SolrInputDocument doc, String fieldName, String languageName, List<String> fieldValues) throws Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			for(String fieldValue : fieldValues) {
				doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
				doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
			}
		}
		return fieldValues;
	}

	protected SolrDocument classDocsAdd(String canonicalName) throws Exception {
		SolrDocument doc = null;
		if(StringUtils.startsWith(domainPackageName, canonicalName)) {
			SolrQuery solrSearch = new SolrQuery();   
			solrSearch.setQuery("*:*");
			solrSearch.setRows(1);
			solrSearch.addFilterQuery("classCanonicalName_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(canonicalName));
			solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
			QueryResponse searchResponse = solrClientComputate.query(solrSearch);
			SolrDocumentList searchList = searchResponse.getResults();
			if(searchList.size() > 0) {
				doc = searchList.get(0);
				classDocs.put(canonicalName, doc);
			}
		}
		return doc;
	}

	protected String searchCanonicalName(String languageName, String canonicalName) throws Exception {
		SolrDocument doc = classDocsAdd(canonicalName);
		String val = null;
		if(doc != null) {
			val = (String)doc.get("classCanonicalName_" + languageName + "_stored_string");
		}
		if(StringUtils.isEmpty(val)) {
			val = canonicalName;  
		}
		return val;
	}

	public String storeRegexComments(String comment, String languageName, SolrInputDocument doc, String entityVar) throws Exception {
		if(!StringUtils.isEmpty(comment)) {
			Matcher m = Pattern.compile("^(enUS|frFR): (.*)", Pattern.MULTILINE).matcher(comment);
			boolean found = m.find();
			StringBuilder b = new StringBuilder();
			
			while(found) {
				String language = m.group(1);
				String text = m.group(2);
				if(languageName.equals(language))
					b.append(text).append("\n");

				found = m.find();
			}
			if(StringUtils.isNotEmpty(b))
				storeSolr(doc, entityVar, languageName, b.toString());
		}
		return comment;
	}

	protected void  indexClass(String classAbsolutePath) throws Exception { 
		SolrInputDocument classDoc = new SolrInputDocument();
		String classCanonicalName = StringUtils.replace(StringUtils.substringAfter(StringUtils.substringBeforeLast(classAbsolutePath, "."), srcMainJavaPath + "/"), "/", ".");
		String classSimpleName = StringUtils.substringAfterLast(classCanonicalName, ".");
		String classCanonicalNameGen = classCanonicalName + "Gen";
		String classSimpleNameGen = classSimpleName + "Gen";
		JavaClass classQdox = builder.getClassByName(classCanonicalName.toString());
		JavaClass classSuperQdox = classQdox.getSuperJavaClass();
		String classSuperCanonicalName = classSuperQdox.getCanonicalName();
		String classSuperSimpleName = StringUtils.substringAfterLast(classSuperCanonicalName, ".");
		if(StringUtils.isEmpty(classSuperSimpleName))
			classSuperSimpleName = classSuperCanonicalName;
		Boolean classExtendsGen = indexStoreSolr(classDoc, "classExtendsGen", StringUtils.endsWith(classSuperSimpleName, "Gen"));

		List<JavaTypeVariable<JavaGenericDeclaration>> classTypeParameters = classQdox.getTypeParameters();
		for(JavaTypeVariable<JavaGenericDeclaration> classTypeParameter : classTypeParameters) {
			String classTypeParameterNom = classTypeParameter.getName();
			storeListSolr(classDoc, "classTypeParameterNames", classTypeParameterNom);
		}

		if(classSuperQdox instanceof DefaultJavaParameterizedType) {
			DefaultJavaParameterizedType typeSuper = (DefaultJavaParameterizedType)classSuperQdox;
			List<JavaType> classeSuperParametresType = typeSuper.getActualTypeArguments();
			for(JavaType classeSuperParametreType : classeSuperParametresType) {
				String classeSuperParametreTypeNom = classeSuperParametreType.getValue();
				storeListSolr(classDoc, "classeSuperParametreTypeNoms", classeSuperParametreTypeNom);
			}
		}
//		classTypeParameters.get(0).getGenericFullyQualifiedName(); // returns <DEV>
//		classQdox.getSuperClass().getGenericCanonicalName(); // returns CouvertureGen<DEV>
		
		String classSuperCompleteName = indexStoreSolr(classDoc, "classSuperCompleteName", languageName, classSuperQdox.getGenericCanonicalName());
		String classSuperCompleteNameGeneric = StringUtils.substringBeforeLast(StringUtils.substringAfter(classSuperCompleteName, "<"), ">");
		String classSuperCanonicalNameGeneric = null;
		String classSuperSimpleNameGeneric = null;
		JavaClass classeSuperGeneriqueQdox = null;
		if(StringUtils.isNotEmpty(classSuperCompleteName)) {
			indexStoreSolr(classDoc, "classSuperCompleteNameGeneric", languageName, classSuperCompleteNameGeneric);
			classSuperCanonicalNameGeneric = classSuperCompleteName.contains("<") ? StringUtils.substringBefore(classSuperCompleteName, "<") : classSuperCompleteName;
			classSuperCanonicalNameGeneric = classSuperCompleteName.contains(",") ? StringUtils.substringBefore(classSuperCompleteName, ",") : classSuperCompleteName;
			if(StringUtils.isNotEmpty(classSuperCanonicalNameGeneric)) {
				indexStoreSolr(classDoc, "classSuperCanonicalNameGeneric", languageName, classSuperCanonicalNameGeneric);
				classeSuperGeneriqueQdox = builder.getClassByName(classSuperCanonicalNameGeneric);

				if(classSuperCanonicalNameGeneric.contains("."))
					classSuperSimpleNameGeneric = StringUtils.substringAfterLast(classSuperCanonicalNameGeneric, ".");
				else
					classSuperSimpleNameGeneric = classSuperCanonicalNameGeneric;
				indexStoreSolr(classDoc, "classSuperSimpleNameGeneric", languageName, classSuperSimpleNameGeneric);
			}
		}
		
		
		
		
		String comment = storeRegexComments(classQdox.getComment(), languageName, classDoc, "classComment");
		String classPackageName = StringUtils.substringBeforeLast(classCanonicalName, ".");
		String classPath = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), ".java");
		String classDirPath = StringUtils.substringBeforeLast(classPath, "/");
		String classGenPath = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), "Gen.java");
		String classGenDirPath = StringUtils.substringBeforeLast(classGenPath, "/");
		String classKey = classAbsolutePath;
		Instant modified = Instant.now();
		Date modifiedDate = Date.from(modified);
		
		ArrayList<JavaClass> qdoxSuperClasses = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesAndMe = new ArrayList<JavaClass>();
		qdoxSuperClassesAndMe.add(classQdox);
		ArrayList<JavaClass> qdoxSuperClassesAndInterfaces = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesInterfacesAndMe = new ArrayList<JavaClass>();
		qdoxSuperClassesInterfacesAndMe.add(classQdox);
		populateQdoxSuperClassesInterfacesAndMe(classQdox, qdoxSuperClasses, qdoxSuperClassesAndMe, qdoxSuperClassesAndInterfaces, qdoxSuperClassesInterfacesAndMe);

		indexStoreSolr(classDoc, "languageName", languageName); 
		indexStoreSolr(classDoc, "modified", modifiedDate); 
		indexStoreSolr(classDoc, "classCanonicalName", languageName, classCanonicalName); 
		indexStoreSolr(classDoc, "classSimpleName", languageName, classSimpleName); 
		indexStoreSolr(classDoc, "classPackageName", languageName, classPackageName); 
		indexStoreSolr(classDoc, "classCanonicalNameGen", languageName, classCanonicalNameGen); 
		indexStoreSolr(classDoc, "classSimpleNameGen", languageName, classSimpleNameGen); 
		indexStoreSolr(classDoc, "classSuperCanonicalName", languageName, classSuperCanonicalName); 
		indexStoreSolr(classDoc, "classSuperSimpleName", languageName, classSuperSimpleName); 
		indexStoreSolr(classDoc, "classAbsolutePath", classAbsolutePath);
		indexStoreSolr(classDoc, "classPath", languageName, classPath); 
		indexStoreSolr(classDoc, "classDirPath", languageName, classDirPath);  
		indexStoreSolr(classDoc, "classGenPath", languageName, classGenPath); 
		indexStoreSolr(classDoc, "classGenDirPath", languageName, classGenDirPath); 

		SolrDocument classSuperCanonicalNameDoc = null;   
		if(StringUtils.startsWith(classSuperCanonicalName, domainPackageName)) {
			SolrQuery solrSearch = new SolrQuery();   
			solrSearch.setQuery("*:*");
			solrSearch.setRows(1);
			solrSearch.addFilterQuery("classCanonicalName_" + languageActualName + "_indexed_string:" + ClientUtils.escapeQueryChars(classSuperCanonicalName));
			solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
			QueryResponse searchResponse = solrClientComputate.query(solrSearch);
			SolrDocumentList searchList = searchResponse.getResults();
			if(searchList.size() > 0) { 
				classSuperCanonicalNameDoc = searchList.get(0);
			}
		}  
		for(String languageName : otherLanguages) { 
			String appPathLanguage = appPaths.get(languageName);
			storeRegexComments(comment, languageName, classDoc, "classComment");
			String srcMainJavaPathLanguage = appPathLanguage + "/src/main/java";
			String srcGenJavaPathLanguage = appPathLanguage + "/src/gen/java";
			String classCanonicalNameLanguage = regex("^canonicalName\\." + languageName + ":\\s*(.*)", comment, classCanonicalName);
			String classSimpleNameLanguage = StringUtils.substringAfterLast(classCanonicalNameLanguage, ".");
			String classPackageNameLanguage = StringUtils.substringBeforeLast(classCanonicalNameLanguage, ".");
			String classCanonicalNameGenLanguage = classCanonicalNameLanguage + "Gen";
			String classSimpleNameGenLanguage = classSimpleNameLanguage + "Gen";
			String classPathLanguage = indexStoreSolr(classDoc, "classPath", languageName, concat(srcMainJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameLanguage, ".", "/"), ".java"));
			String classDirPathLanguage = storeSolr(classDoc, "classDirPath", languageName, StringUtils.substringBeforeLast(classPathLanguage, "/"));
			String classGenPathLanguage = indexStoreSolr(classDoc, "classGenPath", languageName, concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameGenLanguage, ".", "/"), ".java"));
			String classGenDirPathLanguage = storeSolr(classDoc, "classGenDirPath", languageName, StringUtils.substringBeforeLast(classGenPathLanguage, "/"));

			indexStoreSolr(classDoc, "classCanonicalName", languageName, classCanonicalNameLanguage); 
			indexStoreSolr(classDoc, "classSimpleName", languageName, classSimpleNameLanguage); 
			indexStoreSolr(classDoc, "classCanonicalNameGen", languageName, classCanonicalNameGenLanguage); 
			indexStoreSolr(classDoc, "classSimpleNameGen", languageName, classSimpleNameGenLanguage); 
			indexStoreSolr(classDoc, "classPackageName", languageName, classPackageNameLanguage); 

			String classSuperCompleteNameLanguage;
			ClassParts classePartsSuperLangue;

			if(classExtendsGen)
				classePartsSuperLangue = ClassParts.initClassParts(this, classCanonicalNameLanguage + "Gen", languageName);
			else
				classePartsSuperLangue = ClassParts.initClassParts(this, classSuperQdox, languageName);

			indexStoreSolr(classDoc, "classSuperCanonicalName", languageName, classePartsSuperLangue.canonicalName); 
			indexStoreSolr(classDoc, "classSuperSimpleName", languageName, classePartsSuperLangue.simpleName); 
			indexStoreSolr(classDoc, "classCanonicalNameCompletSuper", languageName, classePartsSuperLangue.canonicalNameComplete);
			indexStoreSolr(classDoc, "classSimpleNameCompletSuper", languageName, classePartsSuperLangue.simpleNameComplete);
			if(StringUtils.isNotEmpty(classePartsSuperLangue.canonicalNameGenerique)) {
				indexStoreSolr(classDoc, "classSuperCanonicalNameGeneric", languageName, classePartsSuperLangue.canonicalNameGenerique);
				indexStoreSolr(classDoc, "classSuperSimpleNameGeneric", languageName, classePartsSuperLangue.simpleNameGenerique);
			}





//			if(classSuperCanonicalNameDoc == null) {
//				indexStoreSolr(classDoc, "classSuperCanonicalName", languageName, classSuperCanonicalName); 
//				indexStoreSolr(classDoc, "classSuperSimpleName", languageName, classSuperSimpleName); 
//			}
//			else {
//				indexStoreSolr(classDoc, "classSuperCanonicalName", languageName, (String)classSuperCanonicalNameDoc.get("classCanonicalName_" + languageName + "_stored_string"));
//				indexStoreSolr(classDoc, "classSuperSimpleName", languageName, (String)classSuperCanonicalNameDoc.get("classSimpleName_" + languageName + "_stored_string"));
//			}
//			String classSuperCompleteNameLanguage = searchCanonicalName(languageName, classSuperCompleteName);
//			indexStoreSolr(classDoc, "classSuperCompleteName", languageName, classSuperCompleteNameLanguage);
//			if(StringUtils.isNotEmpty(classSuperCompleteNameGeneric)) {
//				String classSuperCompleteNameGenericLanguage = indexStoreSolr(classDoc, "classSuperCompleteNameGeneric", languageName, searchCanonicalName(languageName, classSuperCompleteNameGeneric));
//				String classSuperCanonicalNameGenericLanguage = indexStoreSolr(classDoc, "classSuperCanonicalNameGeneric", languageName, searchCanonicalName(languageName, classSuperCanonicalNameGeneric));
//				String classSuperSimpleNameGenericLanguage = indexStoreSolr(classDoc, "classSuperSimpleNameGeneric", languageName, searchCanonicalName(languageName, classSuperSimpleNameGeneric));
//			}
		} 

		SolrInputDocument classDocClone = classDoc.deepCopy();
		Integer partNumber = 1;

		classDoc.addField("key", classKey);  

		indexStoreSolr(classDoc, "partIsClass", true);
		indexStoreSolr(classDoc, "partNumber", partNumber);
		
		for(String classeImportation : classQdox.getSource().getImports()) {
			ClassParts classeImportClassParts = ClassParts.initClassParts(this, classeImportation, languageName);
			indexStoreListSolr(classDoc, "classImports", languageName, classeImportClassParts.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classeImportClassParts, languageName);
				indexStoreListSolr(classDoc, "classImports", languageName, classImportClassPartsLanguage.canonicalName);
			}
		}

		List<JavaMember> membersQdox = new ArrayList<JavaMember>();
		membersQdox.addAll(classQdox.getFields());
		membersQdox.addAll(classQdox.getConstructors());
		membersQdox.addAll(classQdox.getMethods());
		for(JavaMember memberQdox : membersQdox) {  
			partNumber++;

			if(memberQdox instanceof JavaField) {    
				SolrInputDocument fieldDoc = classDocClone.deepCopy();
				JavaField fieldQdox = (JavaField)memberQdox;
				String fieldComment = fieldQdox.getComment();
				String fieldVar = fieldQdox.getName();
				String fieldKey = classAbsolutePath + "." + fieldVar;
				String fieldSourceCode = StringUtils.substringBeforeLast(StringUtils.trim(regex("\\s+" + fieldVar + "\\s*=([\\s\\S]*)", fieldQdox.getCodeBlock(), 1)), ";");

				// Champs Solr du champ. 

				fieldDoc.addField("key", fieldKey);
				indexStoreSolr(fieldDoc, "fieldVar", languageName, fieldVar); 
				indexStoreSolr(fieldDoc, "partIsField", true);
				indexStoreSolr(fieldDoc, "partNumber", partNumber);
				indexStoreSolr(fieldDoc, "fieldIsPublic", fieldQdox.isPublic()); 
				indexStoreSolr(fieldDoc, "fieldIsProtected", fieldQdox.isProtected()); 
				indexStoreSolr(fieldDoc, "fieldIsPrivate", fieldQdox.isPrivate()); 
				indexStoreSolr(fieldDoc, "fieldIsStatic", fieldQdox.isStatic()); 
				indexStoreSolr(fieldDoc, "fieldIsFinal", fieldQdox.isFinal()); 
				indexStoreSolr(fieldDoc, "fieldIsAbstract", fieldQdox.isAbstract()); 
				indexStoreSolr(fieldDoc, "fieldIsNative", fieldQdox.isNative()); 
	
				/////////////////
				// Annotations //
				/////////////////
				List<JavaAnnotation> annotations = fieldQdox.getAnnotations(); 
				ArrayList<String> annotationsLanguage = new ArrayList<String>();
				Boolean fieldIsTest = false;
				Boolean fieldIsOverride = false;
				for(JavaAnnotation annotation : annotations) {
					String fieldAnnotationLanguage = annotation.getType().getCanonicalName();
					indexStoreSolr(fieldDoc, "champAnnotation", languageName, fieldAnnotationLanguage); 

					if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
						fieldIsTest = true;
					}
					if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
						fieldIsOverride = true;
					}
				}
				indexStoreSolr(fieldDoc, "fieldIsTest", languageName, fieldIsTest); 
				indexStoreSolr(fieldDoc, "fieldIsOverride", languageName, fieldIsOverride); 

				ClassParts fieldClassParts = ClassParts.initClassParts(this, fieldQdox.getType(), languageName);
	
				storeRegexComments(fieldComment, languageName, fieldDoc, "fieldComment");
				storeSolr(fieldDoc, "fieldSimpleNameComplete", languageName, fieldClassParts.simpleNameComplete);
				storeSolr(fieldDoc, "fieldCanonicalNameComplete", languageName, fieldClassParts.canonicalNameComplete);
				storeSolr(fieldDoc, "fieldSourceCode", languageName, fieldSourceCode);

				for(String languageName : otherLanguages) { 
					ClassParts fieldClassPartsLanguage = ClassParts.initClassParts(this, fieldClassParts, languageName);
					String fieldVarLanguage = regex("^var\\." + languageName + ": (.*)", fieldComment);
					fieldVarLanguage = fieldVarLanguage == null ? fieldVar : fieldVarLanguage;
					String fieldSourceCodeLanguage = regexReplaceAll(fieldComment, fieldSourceCode, languageName);

					indexStoreSolr(fieldDoc, "fieldVar", languageName, fieldVarLanguage); 
					storeSolr(fieldDoc, "fieldSimpleNameComplete", languageName, fieldClassPartsLanguage.simpleNameComplete);
					storeSolr(fieldDoc, "fieldCanonicalNameComplete", languageName, fieldClassPartsLanguage.canonicalNameComplete);
					storeRegexComments(fieldComment, languageName, fieldDoc, "fieldComment");
					storeSolr(fieldDoc, "fieldSourceCode", languageName, fieldSourceCodeLanguage);
				}  

				solrClientComputate.add(fieldDoc); 
			}
			else if(memberQdox instanceof JavaConstructor) { 
				SolrInputDocument constructorDoc = classDocClone.deepCopy();
				JavaConstructor constructorQdox = (JavaConstructor)memberQdox;
				String constructorComment = constructorQdox.getComment();
				List<JavaParameter> constructorParamsQdox = constructorQdox.getParameters();
				List<JavaAnnotation> constructorAnnotations = constructorQdox.getAnnotations(); 
				List<JavaClass> constructorExceptionsQdox = constructorQdox.getExceptions();
				for(Integer constructorParamNum = 1; constructorParamNum <= constructorParamsQdox.size(); constructorParamNum++) {
					JavaParameter constructorParamQdox = constructorParamsQdox.get(constructorParamNum - 1);
					String constructeurParamVar = constructorParamQdox.getName();
					storeListSolr(constructorDoc, "constructeurParamVar", languageName, constructeurParamVar);
					ClassParts constructeurParamClassParts = ClassParts.initClassParts(this, constructorParamQdox.getJavaClass(), languageName);
					storeListSolr(constructorDoc, "constructorParamSimpleNameComplete", languageName, constructeurParamClassParts.simpleNameComplete);
					storeListSolr(constructorDoc, "constructorParamVariableArgs", constructorParamQdox.isVarArgs());
					for(String languageName : otherLanguages) { 
						String constructorParamVarLanguage = regex("param" + constructorParamNum + "\\.var\\." + languageName + ": (.*)", constructorComment);
						if(constructorParamVarLanguage == null)
							constructorParamVarLanguage = constructeurParamVar;
						ClassParts constructorParamClassPartsLanguage = ClassParts.initClassParts(this, constructeurParamClassParts, languageName);

						storeListSolr(constructorDoc, "constructorParamSimpleNameComplete", languageName, constructorParamClassPartsLanguage.simpleNameComplete);
						storeListSolr(constructorDoc, "constructeurParamVar", languageName, constructorParamVarLanguage);
					}  
				}
				for(JavaAnnotation constructorAnnotation : constructorAnnotations) {
//					String constructorAnnotationCodeBlock = storeListSolr(constructorDoc, "constructorAnnotationCodeBlock", languageName, annotation.toString());
				}
				for(JavaClass constructorExceptionQdox : constructorExceptionsQdox) {
					String constructorExceptionSimpleNameComplete = StringUtils.substringAfterLast(constructorExceptionQdox.getCanonicalName(), ".");
					storeListSolr(constructorDoc, "constructorExceptionSimpleNameComplete", constructorExceptionSimpleNameComplete);
				}
				String constructorKey = classAbsolutePath + "." + classSimpleName + "(";

				for(int i = 0; i < constructorParamsQdox.size(); i++) {
					JavaParameter paramQdox = constructorParamsQdox.get(i);
					if(i > 0)
						constructorKey += ", ";
					constructorKey += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
				}
				constructorKey += ")"; 

				constructorDoc.addField("key", constructorKey);
				indexStoreSolr(constructorDoc, "partIsConstructor", true);
				indexStoreSolr(constructorDoc, "partNumber", partNumber);

				indexStoreSolr(constructorDoc, "constructorIsPublic", constructorQdox.isPublic());
				indexStoreSolr(constructorDoc, "constructorIsProtected", constructorQdox.isProtected());
				indexStoreSolr(constructorDoc, "constructorIsPrivate", constructorQdox.isPrivate());
				indexStoreSolr(constructorDoc, "constructorIsStatic", constructorQdox.isStatic());
				indexStoreSolr(constructorDoc, "constructorIsFinal", constructorQdox.isFinal());
				indexStoreSolr(constructorDoc, "constructorIsAbstract", constructorQdox.isAbstract());
				indexStoreSolr(constructorDoc, "constructorIsNative", constructorQdox.isNative());
				storeRegexComments(constructorComment, languageName, constructorDoc, "constructorComment");

				String constructorSourceCode = constructorQdox.getSourceCode();
				String constructorSourceCodeLanguage = constructorSourceCode;
				ArrayList<String> replaceKeysLanguage = regexList("^r." + languageName + "\\s*=\\s*(.*)\\n.*", constructorComment);
				ArrayList<String> replaceValuesLanguage = regexList("^r." + languageName + "\\s*=\\s*.*\\n(.*)", constructorComment);
				for(int i = 0; i < replaceKeysLanguage.size(); i++) {
					String cle = replaceKeysLanguage.get(i);
					String valeur = replaceValuesLanguage.get(i);
					StringUtils.replace(constructorSourceCodeLanguage, cle, valeur);
				}
				storeSolr(constructorDoc, "constructorSourceCode", languageName, constructorSourceCodeLanguage);

				for(String languageName : otherLanguages) {  
					constructorSourceCodeLanguage = regexReplaceAll(constructorComment, constructorSourceCode, languageName);
					storeSolr(constructorDoc, "constructorSourceCode", languageName, constructorSourceCodeLanguage);
					storeRegexComments(constructorComment, languageName, constructorDoc, "constructorComment");
				} 

				solrClientComputate.add(constructorDoc); 
			}
			else if(memberQdox instanceof JavaMethod) {
				JavaMethod methodQdox = (JavaMethod)memberQdox;
				String methodComment = methodQdox.getComment();
				Boolean ignorer = "true".equals(regex("ignorer: (.*)", methodComment));
				if(!ignorer) {
					JavaClass methodeClasseQdoxRetour = methodQdox.getReturns();
					String methodeNomCanoniqueRetourComplet = null;
					String methodeNomCanoniqueRetour = null;
					JavaClass classReturnQdox = methodQdox.getReturns();
					List<JavaParameter> methodParamsQdox = methodQdox.getParameters();
		
					List<JavaAnnotation> annotations = methodQdox.getAnnotations(); 
					ArrayList<String> annotationsLanguage = new ArrayList<String>();
					Boolean methodIsTest = false;
					Boolean methodIsOverride = false;
					String methodVar = methodQdox.getName();
					for(JavaAnnotation annotation : annotations) {
						String methodAnnotationLanguage = annotation.getType().getCanonicalName();
	
						if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
							methodIsTest = true;
						}
						if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
							methodIsOverride = true;
						}
					}

					List<JavaClass> methodExceptionsQdox = methodQdox.getExceptions();
	
					if(!methodIsOverride && !methodQdox.isStatic() && !methodQdox.isFinal() && methodQdox.getDeclaringClass().equals(classQdox) 
							&& methodQdox.isProtected() && methodParamsQdox.size() == 1 && classReturnQdox.isVoid()
							&& StringUtils.startsWith(methodQdox.getName(), "_")) {
						// est Entite. 
						SolrInputDocument entiteDoc = classDocClone.deepCopy();
						String entiteVar = indexStoreSolr(entiteDoc, "entiteVar", languageName, StringUtils.substringAfter(methodQdox.getName(), "_"));
						JavaClass entiteClasseQdox = methodParamsQdox.get(0).getJavaClass();
						boolean entiteCouverture = false;
						String entiteNomCanonique = indexStoreSolr(entiteDoc, "entiteNomCanonique", languageName, entiteClasseQdox.getCanonicalName());

						String entiteNomSimple;
						if(entiteNomCanonique.contains("."))
							entiteNomSimple = StringUtils.substringBefore(StringUtils.substringAfterLast(entiteNomCanonique, "."), ">");
						else
							entiteNomSimple = StringUtils.substringBefore(entiteNomCanonique.toString(), ">");
						indexStoreSolr(entiteDoc, "entiteNomSimple", languageName, entiteNomSimple);

						String entiteTypeOrigine = indexStoreSolr(entiteDoc, "entiteTypeOrigine", languageName, entiteClasseQdox.getGenericCanonicalName());

						String entiteNomCompletGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteTypeOrigine, "<"), ">");
						String entiteNomCanoniqueGenerique = null;
						JavaClass entiteClasseGeneriqueQdox = null;
						String entiteNomSimpleGenerique = null;
						if(StringUtils.isNotEmpty(entiteNomCompletGenerique)) {
							indexStoreSolr(entiteDoc, "entiteNomCompletGenerique", languageName, entiteNomCompletGenerique);
							entiteNomCanoniqueGenerique = entiteNomCompletGenerique.contains("<") ? StringUtils.substringBefore(entiteNomCompletGenerique, "<") : entiteNomCompletGenerique;
							entiteNomCanoniqueGenerique = entiteNomCompletGenerique.contains(",") ? StringUtils.substringBefore(entiteNomCompletGenerique, ",") : entiteNomCompletGenerique;
							if(StringUtils.isNotEmpty(entiteNomCanoniqueGenerique)) {
								indexStoreSolr(entiteDoc, "entiteNomCanoniqueGenerique", languageName, entiteNomCanoniqueGenerique);
								entiteClasseGeneriqueQdox = builder.getClassByName(entiteNomCanoniqueGenerique);
	//							String canonicalNameGeneriqueEnUS = classe_.regex("canonicalName.enUS:\\s*(.*)", comment, 1);
	//							o.enUS(StringUtils.isEmpty(canonicalNameGeneriqueEnUS) ? o.frFR() : canonicalNameGeneriqueEnUS);
	//							if(canonicalNameGenerique.frFR().contains(".")) {
	//								classe_.importationsAjouter(canonicalNameGenerique);
	//								classe_.importationsGenAjouter(canonicalNameGenerique);
	//							}
	
								if(entiteNomCanoniqueGenerique.contains("."))
									entiteNomSimpleGenerique = StringUtils.substringAfterLast(entiteNomCanoniqueGenerique, ".");
								else
									entiteNomSimpleGenerique = entiteNomCanoniqueGenerique;
								indexStoreSolr(entiteDoc, "entiteNomSimpleGenerique", languageName, entiteNomSimpleGenerique);
							}
						}
						
						String entiteNomCanoniqueComplet;
						if(StringUtils.isNotEmpty(entiteNomCompletGenerique))
							entiteNomCanoniqueComplet = entiteNomCanonique + "<" + entiteNomCompletGenerique + ">";
						else
							entiteNomCanoniqueComplet = entiteNomCanonique;
						indexStoreSolr(entiteDoc, "entiteNomCanoniqueComplet", languageName, entiteNomCanoniqueComplet);
						
						String entiteNomSimpleComplet = entiteNomSimple;
						if(StringUtils.isNotEmpty(entiteNomCompletGenerique)) {
							entiteNomSimpleComplet += "<";
							if(entiteNomCompletGenerique.contains(".")) {
								entiteNomSimpleComplet += StringUtils.substringAfterLast(entiteNomCompletGenerique, ".");
							}
							else {
								entiteNomSimpleComplet += entiteNomCompletGenerique;
							}
							entiteNomSimpleComplet += ">";
						}
						indexStoreSolr(entiteDoc, "entiteNomSimpleComplet", languageName, entiteNomSimpleComplet);
						
						String entiteNomSimpleCompletGenerique = null;
						if(StringUtils.isNotEmpty(entiteNomCompletGenerique)) {
							if(entiteNomCompletGenerique.contains(".")) {
								entiteNomSimpleCompletGenerique = StringUtils.substringAfterLast(entiteNomCompletGenerique, ".");
							}
							else {
								entiteNomSimpleCompletGenerique = entiteNomCompletGenerique;
							}
						}
						indexStoreSolr(entiteDoc, "entiteNomSimpleCompletGenerique", languageName, entiteNomSimpleCompletGenerique);
						
						JavaClass entiteClasseQdoxBase = null;
						JavaClass entiteClasseSuperQdox = entiteClasseQdox.getSuperJavaClass();
						if(entiteClasseSuperQdox != null) {
							String entiteNomCanoniqueSuperQdox = entiteClasseSuperQdox.getCanonicalName();
							if(entiteNomCanoniqueSuperQdox.endsWith("Gen")) {
								entiteClasseQdoxBase = entiteClasseSuperQdox.getSuperJavaClass();
							}
						}
						
						String entiteNomCanoniqueBase = null;
						if(entiteClasseQdoxBase != null) {
							String s = entiteClasseQdoxBase.getCanonicalName();
							if(s.contains("."))
								entiteNomCanoniqueBase = s;
						}
						indexStoreSolr(entiteDoc, "entiteNomCanoniqueBase", languageName, entiteNomCanoniqueBase);
						
						String entiteNomSimpleBase = null;
						if(StringUtils.isNotEmpty(entiteNomCanoniqueBase)) {
							entiteNomSimpleBase = StringUtils.substringAfterLast(entiteNomCanoniqueBase, ".");
						}
						indexStoreSolr(entiteDoc, "entiteNomSimpleBase", languageName, entiteNomSimpleBase);
						
						String entiteVarParam;
						if(entiteNomCanonique.equals(ArrayList.class.getCanonicalName()) || entiteNomCanonique.equals(List.class.getCanonicalName()))
							entiteVarParam = "l";
						else
							entiteVarParam = "o";
						indexStoreSolr(entiteDoc, "entiteVarParam", languageName, entiteVarParam);
						
						String entiteVarCouverture = indexStoreSolr(entiteDoc, "entiteVarCouverture", languageName, entiteVar + "Couverture");
//						boolean entiteCouverture = false;
//	
//						String varEntiteEnUS = regex("^var.enUS: (.*)", methodQdox.getComment());
//						entite.var.frFR(entiteVar);
//						entite.var.enUS(StringUtils.isEmpty(varEntiteEnUS) ? entiteVar : StringUtils.substringAfter(varEntiteEnUS, "_"));
//	
//						regexCommentaires(methodQdox.getComment(), entite.comment);
//						regexReplaceAll(methodQdox.getComment(), methodQdox.getSourceCode(), entite.codeSource);
//			
//						if(entiteClasseQdox.getFullyQualifiedName().equals(canonicalNameCouvertureActuel)) {
//							entiteType = StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteType, "<"), ">");
//							if(StringUtils.contains(entiteType, "<"))
//								entiteClasseQdox = typeBricoleur.getClassByName(StringUtils.substringBefore(entiteType, "<"));
//							else
//								entiteClasseQdox = typeBricoleur.getClassByName(entiteType);
//							entiteNomCanonique = entiteClasseQdox.getCanonicalName();
//							entiteCouverture = true;
//							entite.couverture(true);
//						} 
//						if(entite.cleUnique)
//							varCleUniqueActuel.tout(entite.var);
//						if(entite.suggere)
//							varSuggereActuel.tout(entite.var);
//	
//						if(!entitesTout.contains(entite))
//							entitesTout.add(entite);
//	
//						tout.add(entite);
//						importationsAjouter(new Chaine().tout(entiteNomCanonique));
//						importationsGenAjouter(new Chaine().tout(entiteNomCanonique));
//						if(entiteListeTypeGenerique != null) {
//							Chaine importation = new Chaine().tout(entiteListeTypeGenerique);
//							importationsAjouter(importation);
//							importationsGenAjouter(importation);
//						}
//	
//						boolean etendCluster = etendClasse(entiteNomCanoniqueClusterActuel);
//						entite.etendCluster(etendCluster);
//						if(!etendCluster && entite.canonicalNameGenerique.pasVide()) {
//							boolean listeCluster = etendClasse(canonicalNameClusterActuel, entite.canonicalNameGenerique.toString());
//							entite.listeCluster(listeCluster);
//						}
//	
//						boolean etendPageXml = entite.etendClasse(canonicalNamePageXmlActuel);
//						entite.etendPageXml(etendPageXml);
//						if(!etendPageXml && entite.canonicalNameGenerique.pasVide()) {
//							boolean listePageXml = etendClasse(canonicalNamePageXmlActuel, entite.canonicalNameGenerique.toString());
//							entite.listePageXml(listePageXml);
//						}
//	
//						boolean etendPagePart = entite.etendClasse(canonicalNamePagePartActuel);
//						entite.etendPagePart(etendPagePart);
//						if(!etendPagePart && entite.canonicalNameGenerique.pasVide()) {
//							boolean listePagePart = etendClasse(canonicalNamePagePartActuel, entite.canonicalNameGenerique.toString());
//							entite.listePagePart(listePagePart);
//						}
//	
//	
//						boolean contientRequeteSite = contientChamp(varRequeteSite.toString(), entite.classQdox);
//						entite.contientRequeteSite(contientRequeteSite);
//	
//						boolean contientSetterString = contientMethode(entite.var.toString(), classQdoxString);
//						entite.contientSetterString(contientSetterString);
//	
//						entiteEstCmd(entite);
						
						
						
						
						
						
						
						
						
						
						
						
						
						indexStoreSolr(entiteDoc, "entiteVar", languageName, entiteVar);
						for(JavaAnnotation annotation : annotations) {
							String entiteAnnotationLangue = indexStoreSolr(entiteDoc, "entiteAnnotations", languageName, annotation.getType().getCanonicalName());
						}
//						if(classReturnQdox != null && !classReturnQdox.getCanonicalName().equals("void")) {
//							entiteNomCanoniqueRetourComplet = indexerStocker(entiteDoc, "entiteNomCanoniqueRetourComplet", languageName, classReturnQdox.getGenericCanonicalName());
//							entiteNomCanoniqueRetour = indexerStocker(entiteDoc, "entiteNomCanoniqueRetour", languageName, classReturnQdox.getCanonicalName());
//							String entiteNomSimpleRetour = indexerStocker(entiteDoc, "entiteNomSimpleRetour", languageName, StringUtils.substringAfterLast(entiteNomCanoniqueRetour, "."));
//							String listeNomTypeOrigineRetourGenerique = entiteNomCanoniqueRetourComplet;
//							String entiteNomCanoniqueRetourGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineRetourGenerique, "<"), ">");
//							String entiteNomSimpleRetourComplet;
//							String entiteNomSimpleRetourGenerique;
//							entiteNomCanoniqueRetourGenerique = entiteNomCanoniqueRetourGenerique.contains("<") ? StringUtils.substringBefore(entiteNomCanoniqueRetourGenerique, "<") : entiteNomCanoniqueRetourGenerique;
//							entiteNomCanoniqueRetourGenerique = entiteNomCanoniqueRetourGenerique.contains(",") ? StringUtils.substringBefore(entiteNomCanoniqueRetourGenerique, ",") : entiteNomCanoniqueRetourGenerique;
//							if(StringUtils.isNotEmpty(entiteNomCanoniqueRetourGenerique)) {
//								indexerStocker(entiteDoc, "entiteNomCanoniqueRetourGenerique", languageName, entiteNomCanoniqueRetourGenerique);
//	
//								if(StringUtils.contains(entiteNomCanoniqueRetourGenerique, "."))
//									entiteNomSimpleRetourGenerique = indexerStocker(entiteDoc, "entiteNomSimpleRetourGenerique", languageName, StringUtils.substringAfterLast(entiteNomCanoniqueRetourGenerique, "."));
//								else
//									entiteNomSimpleRetourGenerique = indexerStocker(entiteDoc, "entiteNomSimpleRetourGenerique", languageName, entiteNomCanoniqueRetourGenerique);
//	
//								if(StringUtils.contains(entiteNomSimpleRetourGenerique, ".")) {
//									entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomSimpleRetourComplet", languageName, concat(StringUtils.substringAfterLast(entiteNomSimpleRetour, "."), "<", entiteNomSimpleRetourGenerique, ">"));
//								}
//								else {
//									entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomSimpleRetourComplet", languageName, concat(entiteNomSimpleRetour, "<", entiteNomSimpleRetourGenerique, ">"));
//								}
//							}
//							else {
//								entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomCanoniqueRetourComplet", languageName, entiteNomSimpleRetour);
//							}
//						}
	
						String entiteCle = classAbsolutePath + "." + entiteVar;
		
						// Entites Solr du entite. 
		
						entiteDoc.addField("key", entiteCle);
						indexStoreSolr(entiteDoc, "partEstEntite", true);
						indexStoreSolr(entiteDoc, "partNumber", partNumber);
	
						String entiteVarLangue = regex("^var\\." + languageName + ": (.*)", methodComment);
						entiteVarLangue = indexStoreSolr(entiteDoc, "entiteVar", languageName, entiteVarLangue == null ? entiteVar : entiteVarLangue);
	
						List<String> entiteCommentairesLangue = regexList("(.*)", methodComment);
						String entiteCommentaireLangue = indexStoreSolr(entiteDoc, "entiteCommentaire", languageName, StringUtils.join(entiteCommentairesLangue, "\n"));
	
						String entiteBlocCode = methodQdox.getCodeBlock();
						String entiteBlocCodeLangue = entiteBlocCode;
						ArrayList<String> replaceKeysLanguage = regexList("^r." + languageName + "\\s*=\\s*(.*)\\n.*", methodComment);
						ArrayList<String> replaceValuesLanguage = regexList("^r." + languageName + "\\s*=\\s*.*\\n(.*)", methodComment);
						for(int i = 0; i < replaceKeysLanguage.size(); i++) {
							String cle = replaceKeysLanguage.get(i);
							String valeur = replaceValuesLanguage.get(i);
							StringUtils.replace(entiteBlocCodeLangue, cle, valeur);
						}
						storeSolr(entiteDoc, "entiteBlocCode", languageName, entiteBlocCodeLangue); 

						storeRegexComments(methodComment, languageName, entiteDoc, "entiteCommentaire");

						solrClientComputate.add(entiteDoc); 
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					}
					else {
						// est Methode. 
						
						SolrInputDocument methodDoc = classDocClone.deepCopy();
						indexStoreSolr(methodDoc, "methodVar", languageName, methodVar);
						for(Integer methodParamNum = 1; methodParamNum <= methodParamsQdox.size(); methodParamNum++) {
							JavaParameter methodParamQdox = methodParamsQdox.get(methodParamNum - 1);
							String methodeParamVar = methodParamQdox.getName();
							storeListSolr(methodDoc, "methodeParamVar", languageName, methodeParamVar);
							ClassParts methodeParamClassParts = ClassParts.initClassParts(this, methodParamQdox.getJavaClass(), languageName);
							storeListSolr(methodDoc, "methodParamSimpleNameComplete", languageName, methodeParamClassParts.simpleNameComplete);
							storeListSolr(methodDoc, "methodParamVariableArgs", methodParamQdox.isVarArgs());
							for(String languageName : otherLanguages) { 
								String methodParamVarLanguage = regex("param" + methodParamNum + "\\.var\\." + languageName + ": (.*)", methodComment);
								if(methodParamVarLanguage == null)
									methodParamVarLanguage = methodeParamVar;
								ClassParts methodParamClassPartsLanguage = ClassParts.initClassParts(this, methodeParamClassParts, languageName);

								storeListSolr(methodDoc, "methodParamSimpleNameComplete", languageName, methodParamClassPartsLanguage.simpleNameComplete);
								storeListSolr(methodDoc, "methodeParamVar", languageName, methodParamVarLanguage);
							}  
						}

						List<JavaTypeVariable<JavaGenericDeclaration>> methodTypeParameters = methodQdox.getTypeParameters();
						for(JavaTypeVariable<JavaGenericDeclaration> methodTypeParameter : methodTypeParameters) {
							String methodTypeParameterNom = methodTypeParameter.getName();
							storeListSolr(methodDoc, "methodTypeParameterNames", methodTypeParameterNom);
						}

						for(JavaAnnotation annotation : annotations) {
							ClassParts methodAnnotationClassParts = ClassParts.initClassParts(this, annotation.getType(), languageName);
							storeListSolr(methodDoc, "methodAnnotationsNomSimpleComplet", languageName, methodAnnotationClassParts.simpleNameComplete);
							storeListSolr(methodDoc, "methodAnnotationsBlocCode", languageName, StringUtils.substringAfter(annotation.toString(), methodAnnotationClassParts.simpleName));
							for(String languageName : otherLanguages) {  
								ClassParts methodAnnotationClassPartsLangue = ClassParts.initClassParts(this, methodAnnotationClassParts, languageName);
								storeListSolr(methodDoc, "methodAnnotationsNomSimpleComplet", languageName, methodAnnotationClassPartsLangue.simpleNameComplete);
								storeListSolr(methodDoc, "methodAnnotationsBlocCode", languageName, StringUtils.substringAfter(annotation.toString(), methodAnnotationClassParts.simpleName));
							}
						}

						for(JavaClass methodExceptionQdox : methodExceptionsQdox) {
							String methodExceptionSimpleNameComplete = StringUtils.substringAfterLast(methodExceptionQdox.getCanonicalName(), ".");
							storeListSolr(methodDoc, "methodExceptionSimpleNameComplete", methodExceptionSimpleNameComplete);
						}
						Boolean methodIsVoid = false;
						if(classReturnQdox != null && !classReturnQdox.getCanonicalName().equals("void")) {
							ClassParts methodReturnClassParts = ClassParts.initClassParts(this, methodQdox.getReturns(), languageName);
							storeSolr(methodDoc, "methodReturnSimpleNameComplete", languageName, methodReturnClassParts.simpleNameComplete);
							for(String languageName : otherLanguages) { 
								ClassParts methodReturnClassPartsLanguage = ClassParts.initClassParts(this, methodReturnClassParts, languageName);
								storeSolr(methodDoc, "methodReturnSimpleNameComplete", languageName, methodReturnClassPartsLanguage.simpleNameComplete);
							}
						}
						else {
							methodIsVoid = true;
						}
						methodIsVoid = indexStoreSolr(methodDoc, "methodIsVoid", methodIsVoid);
	
						String methodKey = classAbsolutePath + "." + methodVar + "(";
						for(int i = 0; i < methodParamsQdox.size(); i++) {
							JavaParameter paramQdox = methodParamsQdox.get(i);
							if(i > 0)
								methodKey += ", ";
							methodKey += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
						}
						methodKey += ")"; 
		
						// Methodes Solr du methode. 
		
						methodDoc.addField("key", methodKey);
						indexStoreSolr(methodDoc, "partIsMethod", true);
						indexStoreSolr(methodDoc, "partNumber", partNumber);

						indexStoreSolr(methodDoc, "methodIsPublic", methodQdox.isPublic());
						indexStoreSolr(methodDoc, "methodIsProtected", methodQdox.isProtected());
						indexStoreSolr(methodDoc, "methodIsPrivate", methodQdox.isPrivate());
						indexStoreSolr(methodDoc, "methodIsStatic", methodQdox.isStatic());
						indexStoreSolr(methodDoc, "methodIsFinal", methodQdox.isFinal());
						indexStoreSolr(methodDoc, "methodIsAbstract", methodQdox.isAbstract());
						indexStoreSolr(methodDoc, "methodIsNative", methodQdox.isNative());
						indexStoreSolr(methodDoc, "methodIsTest", methodIsTest);
						indexStoreSolr(methodDoc, "methodIsOverride", methodIsOverride);
						storeRegexComments(methodComment, languageName, methodDoc, "methodComment");
	
						String methodVarLanguage = regex("^var\\." + languageName + ": (.*)", methodComment);
						methodVarLanguage =  methodVarLanguage == null ? methodVar : methodVarLanguage;
	
						regexList("^" + languageName + ":\\s*([^\n]+)", methodComment);

						String methodSourceCode = methodQdox.getSourceCode();
						String methodSourceCodeLanguage = methodSourceCode;
						ArrayList<String> replaceKeysLanguage = regexList("^r." + languageName + "\\s*=\\s*(.*)\\n.*", methodComment);
						ArrayList<String> replaceValuesLanguage = regexList("^r." + languageName + "\\s*=\\s*.*\\n(.*)", methodComment);
						for(int i = 0; i < replaceKeysLanguage.size(); i++) {
							String regexKey = replaceKeysLanguage.get(i);
							String regexValue = replaceValuesLanguage.get(i);
							StringUtils.replace(methodSourceCodeLanguage, regexKey, regexValue);
						}
						storeSolr(methodDoc, "methodSourceCode", languageName, methodSourceCodeLanguage);

						for(String languageName : otherLanguages) {  
							methodVarLanguage = regex("^var\\." + languageName + ":\\s*([^\n]+)", methodComment);
							methodVarLanguage = indexStoreSolr(methodDoc, "methodVar", languageName, methodVarLanguage == null ? methodVar : methodVarLanguage);
							regexList("^" + languageName + ":\\s*([^\n]+)", methodComment);
							methodSourceCodeLanguage = regexReplaceAll(methodComment, methodSourceCode, languageName);
							storeSolr(methodDoc, "methodSourceCode", languageName, methodSourceCodeLanguage);
							storeRegexComments(methodComment, languageName, methodDoc, "methodComment");
						} 
	
						solrClientComputate.add(methodDoc); 
					}
				}
			}
		}
		solrClientComputate.add(classDoc);
		solrClientComputate.commit();
		String qDelete = concat("classAbsolutePath_indexed_string", ":\"", classPath, "\" AND (modified_indexed_date:[* TO ", modified.toString(), "-1MILLI] OR (*:* NOT modified_indexed_date:*))");
		solrClientComputate.deleteByQuery(qDelete);
		solrClientComputate.commit(); 
	}
}
