package org.computate.enUS.java;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
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

public class IndexClass extends WatchClassBase {

	public static final String VAL_canonicalNameString = String.class.getCanonicalName();

	public static final String VAL_canonicalNameBoolean = Boolean.class.getCanonicalName();

	public static final String VAL_canonicalNameDate = Date.class.getCanonicalName();

	public static final String VAL_canonicalNameLong = Long.class.getCanonicalName();

	public static final String VAL_canonicalNameDouble = Double.class.getCanonicalName();

	public static final String VAL_canonicalNameFloat = Float.class.getCanonicalName();

	public static final String VAL_canonicalNameBigDecimal = BigDecimal.class.getCanonicalName();

	public static final String VAL_canonicalNameInteger = Integer.class.getCanonicalName();

	public static final String VAL_canonicalNameTimestamp = Timestamp.class.getCanonicalName();

	public static final String VAL_canonicalNameLocalDateTime = LocalDateTime.class.getCanonicalName();

	public static final String VAL_canonicalNameLocalDate = LocalDate.class.getCanonicalName();

	public static final String VAL_canonicalNameList = List.class.getCanonicalName();

	public static final String VAL_canonicalNameArrayList = ArrayList.class.getCanonicalName();

	public static final String VAL_canonicalNameInstant = Instant.class.getCanonicalName();

	public static final String VAL_canonicalNameVertxJsonArray = "io.vertx.core.json.JsonArray";

	public static final String VAL_canonicalNameVertxJsonObject = "io.vertx.core.json.JsonObject";

	protected HashMap<String, ClassParts> classPartsGenApi = new HashMap<String, ClassParts>();

	protected HashMap<String, ClassParts> classPartsGenPage = new HashMap<String, ClassParts>();

	public void  populateQdoxSuperClassesInterfacesAndMe(JavaClass c, ArrayList<JavaClass> qdoxSuperClasses, ArrayList<JavaClass> qdoxSuperClassesAndMe, ArrayList<JavaClass> qdoxSuperClassesAndMeWithoutGen, ArrayList<JavaClass> qdoxSuperClassesAndInterfaces, ArrayList<JavaClass> qdoxSuperClassesInterfacesAndMe) throws Exception, Exception { 
		if(c != null) {
			JavaClass superClass = c.getSuperJavaClass();
			List<JavaClass> interfacesImplemented = c.getInterfaces();

			for(JavaClass interfaceQdox : interfacesImplemented) {
				if(interfaceQdox != null && !interfaceQdox.getCanonicalName().equals("java.lang.Object") && !c.equals(interfaceQdox)) {
					qdoxSuperClassesInterfacesAndMe.add(interfaceQdox);
					qdoxSuperClassesAndInterfaces.add(superClass);
					populateQdoxSuperClassesInterfacesAndMe(interfaceQdox, qdoxSuperClasses, qdoxSuperClassesAndMe, qdoxSuperClassesAndMeWithoutGen, qdoxSuperClassesAndInterfaces, qdoxSuperClassesInterfacesAndMe); // Doesn't seem to work for interfaces that extend other interfaces.
				}
			}
			qdoxSuperClassesInterfacesAndMe.add(c);
			qdoxSuperClassesAndMe.add(c);
			if(!StringUtils.endsWith(c.getCanonicalName(), "Gen"))
				qdoxSuperClassesAndMeWithoutGen.add(c);
			try {
				if(superClass != null && !superClass.getCanonicalName().equals("java.lang.Object") && !c.equals(superClass)) {
					qdoxSuperClassesAndInterfaces.add(superClass);
					qdoxSuperClasses.add(superClass);
					populateQdoxSuperClassesInterfacesAndMe(superClass, qdoxSuperClasses, qdoxSuperClassesAndMe, qdoxSuperClassesAndMeWithoutGen, qdoxSuperClassesAndInterfaces, qdoxSuperClassesInterfacesAndMe);
				}
			} catch (Exception e) {
			}
		}
	}

	protected Boolean storeSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_boolean"), fieldValue);
		return fieldValue;
	}

	protected Boolean storeSolr(SolrInputDocument doc, String fieldName, String languageName, Boolean fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_boolean"), fieldValue);
		}
		return fieldValue;
	}

	protected String storeSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_string"), fieldValue);
		return fieldValue;
	}

	protected String storeListSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_strings"), fieldValue);
		return fieldValue;
	}

	protected Boolean storeListSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_booleans"), fieldValue);
		return fieldValue;
	}

	protected String storeSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String storeListSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> storeSolr(SolrInputDocument doc, String fieldName, String languageName, List<String> fieldValues) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			for(String fieldValue : fieldValues) {
				doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
			}
		}
		return fieldValues;
	}

	protected Boolean indexSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexListSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_indexed_strings"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexSolr(SolrInputDocument doc, String fieldName, String languageName, Boolean fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected String indexSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexListSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> indexSolr(SolrInputDocument doc, String fieldName, String languageName, List<String> fieldValues) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			for(String fieldValue : fieldValues) {
				doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
			}
		}
		return fieldValues;
	}

	protected Long indexStoreSolr(SolrInputDocument doc, String fieldName, Long fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_long"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_long"), fieldValue);
		return fieldValue;
	}

	protected Integer indexStoreSolr(SolrInputDocument doc, String fieldName, Integer fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_int"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_int"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexStoreSolr(SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_boolean"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_boolean"), fieldValue);
		return fieldValue;
	}

	protected Date indexStoreSolr(SolrInputDocument doc, String fieldName, Date fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_date"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_date"), fieldValue);
		return fieldValue;
	}

	protected Boolean indexStoreSolr(SolrInputDocument doc, String fieldName, String languageName, Boolean fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexStoreSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_string"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected String indexStoreListSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_strings"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_strings"), fieldValue);
		return fieldValue;
	}

	protected String indexStoreSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexStoreListSolr(SolrInputDocument doc, String fieldName, String fieldValue, String valeurChamp) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> indexStoreSolr(SolrInputDocument doc, String fieldName, String languageName, List<String> fieldValues) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			for(String fieldValue : fieldValues) {
				doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
				doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
			}
		}
		return fieldValues;
	}

	protected SolrDocument classDocsAdd(String canonicalName) throws Exception, Exception {
		SolrDocument doc = null;
		if(StringUtils.startsWith(domainPackageName, canonicalName)) {
			SolrQuery solrSearch = new SolrQuery();   
			solrSearch.setQuery("*:*");
			solrSearch.setRows(1);
			solrSearch.addFilterQuery("classCanonicalName_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(canonicalName));
			solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
			solrSearch.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
			QueryResponse searchResponse = solrClientComputate.query(solrSearch);
			SolrDocumentList searchList = searchResponse.getResults();
			if(searchList.size() > 0) {
				doc = searchList.get(0);
				classDocs.put(canonicalName, doc);
			}
		}
		return doc;
	}

	protected String searchCanonicalName(String languageName, String canonicalName) throws Exception, Exception {
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

	protected ClassParts classPartsForSimpleName(String domainPackageName, String simpleName) throws Exception, Exception {
		ClassParts classParts = null;
		SolrDocument doc = null;
		SolrQuery solrSearch = new SolrQuery();   
		solrSearch.setQuery("*:*");
		solrSearch.setRows(1);
		solrSearch.addFilterQuery("classSimpleName_" + languageName + "_indexed_string:" + simpleName);
		solrSearch.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
		solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
		QueryResponse searchResponse = solrClientComputate.query(solrSearch);
		SolrDocumentList searchList = searchResponse.getResults();
		if(searchList.size() > 0) {
			doc = searchList.get(0);
			String nomCanonique = (String)doc.get("classCanonicalName_" + languageName + "_stored_string");
			classParts = ClassParts.initClassParts(this, nomCanonique, languageName);
		}
		return classParts;
	}

	protected ClassParts classPartsWrap(String domainPackageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "Wrap");
	}

	protected ClassParts classPartsChain(String domainPackageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "Chain");
	}

	protected ClassParts classPartsSiteRequest(String domainPackageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteRequest");
	}

	protected ClassParts classPartsSiteContext(String domainPackageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteContext");
	}

	protected ClassParts classPartsSiteConfig(String domainPackageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteConfig");
	}

	protected ClassParts classPartsSiteUser(String domainPackageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteUser");
	}

	protected ClassParts classPartsCluster(String domainPackageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "Cluster");
	}

	protected ClassParts classPartsSearchResult(String domainPackageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SearchResult");
	}

	public String storeRegexComments(String comment, String languageName, SolrInputDocument doc, String entityVar) throws Exception, Exception {
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

	public void  classPartsGenAdd(ClassParts classParts) {
		if(classPartsGen != null && classParts != null && !classPartsGen.containsKey(classParts.canonicalName) && StringUtils.contains(classParts.canonicalName, "."))
			classPartsGen.put(classParts.canonicalName, classParts);
	}

	public void  classPartsGenApiAdd(ClassParts classParts) {
		if(classPartsGenApi != null && classParts != null && !classPartsGenApi.containsKey(classParts.canonicalName) && StringUtils.contains(classParts.canonicalName, "."))
			classPartsGenApi.put(classParts.canonicalName, classParts);
	}

	public void  classPartsGenPageAdd(ClassParts classParts) {
		if(classPartsGenPage != null && classParts != null && !classPartsGenPage.containsKey(classParts.canonicalName) && StringUtils.contains(classParts.canonicalName, "."))
			classPartsGenPage.put(classParts.canonicalName, classParts);
	}

	protected SolrInputDocument indexClass(String classAbsolutePath) throws Exception, Exception { 

		SolrInputDocument classDoc = new SolrInputDocument();
		String classCanonicalName = StringUtils.replace(StringUtils.substringAfter(StringUtils.substringBeforeLast(classAbsolutePath, "."), srcMainJavaPath + "/"), "/", ".");
		String classSimpleName = StringUtils.substringAfterLast(classCanonicalName, ".");
		String classCanonicalNameGen = classCanonicalName + "Gen";
		String classSimpleNameGen = classSimpleName + "Gen";
		String classCanonicalNameApiGen = classCanonicalName + "ApiGen";
		String classSimpleNameApi = indexStoreSolr(classDoc, "classSimpleNameApi", languageName, classSimpleName + "Api");
		String classSimpleNameApiGen = indexStoreSolr(classDoc, "classSimpleNameApiGen", languageName, classSimpleName + "ApiGen");
		String classCanonicalNamePageGen = classCanonicalName + "PageGen";
		String classSimpleNamePage = indexStoreSolr(classDoc, "classSimpleNamePage", languageName, classSimpleName + "Page");
		String classSimpleNamePageGen = indexStoreSolr(classDoc, "classSimpleNamePageGen", languageName, classSimpleName + "PageGen");
		indexStoreSolr(classDoc, "appPath", appPath);
		indexStoreSolr(classDoc, "appName", appName);
		JavaClass classQdox = builder.getClassByName(classCanonicalName.toString());
		JavaClass classSuperQdox = classQdox.getSuperJavaClass();
		JavaClass classQdoxString = builder.getClassByName(String.class.getCanonicalName());

		String classSuperCanonicalName = Object.class.getCanonicalName();
		Boolean superClassError = false;
		try {
			classSuperCanonicalName = classSuperQdox.getCanonicalName();
		} catch (Exception e) {
			superClassError = true;
		}

		String classSuperSimpleName = StringUtils.substringAfterLast(classSuperCanonicalName, ".");
		if(StringUtils.isEmpty(classSuperSimpleName))
			classSuperSimpleName = classSuperCanonicalName;

		List<String> classMethodsWritten = new ArrayList<String>();

		List<JavaTypeVariable<JavaGenericDeclaration>> classTypeParameters = classQdox.getTypeParameters();
		for(JavaTypeVariable<JavaGenericDeclaration> classTypeParameter : classTypeParameters) {
			String classTypeParameterName = classTypeParameter.getName();
			storeListSolr(classDoc, "classTypeParameterNames", classTypeParameterName);
		}

		if(classSuperQdox instanceof DefaultJavaParameterizedType) {
			DefaultJavaParameterizedType typeSuper = (DefaultJavaParameterizedType)classSuperQdox;
			List<JavaType> classSuperTypeParameters = typeSuper.getActualTypeArguments();
			for(JavaType classSuperTypeParameter : classSuperTypeParameters) {
				String classSuperTypeParameterName = classSuperTypeParameter.getValue();
				storeListSolr(classDoc, "classSuperTypeParametersNom", classSuperTypeParameterName);
			}
		}
//		classTypeParameters.get(0).getGenericFullyQualifiedName(); // returns <DEV>
//		classQdox.getSuperClass().getGenericCanonicalName(); // returns CouvertureGen<DEV>
		
		String classSuperCompleteName = Object.class.getCanonicalName();
		try {
			classSuperCompleteName = indexStoreSolr(classDoc, "classSuperCompleteName", languageName, classSuperQdox.getGenericCanonicalName());
		} catch (Exception e) {
		}

		String classSuperCompleteNameGeneric = StringUtils.substringBeforeLast(StringUtils.substringAfter(classSuperCompleteName, "<"), ">");
		String classSuperCanonicalNameGeneric = null;
		String classSuperSimpleNameGeneric = null;
		JavaClass superClassGenericQdox = null;
		Boolean baseClassExtendsGen = false;
		if(StringUtils.isNotEmpty(classSuperCompleteName)) {
			indexStoreSolr(classDoc, "classSuperCompleteNameGeneric", languageName, classSuperCompleteNameGeneric);
			if(classSuperCompleteName.contains("<")) {
				classSuperCanonicalNameGeneric = StringUtils.substringAfter(StringUtils.substringBeforeLast(classSuperCompleteName, ">"), "<");
				classSuperCanonicalNameGeneric = classSuperCanonicalNameGeneric.contains(",") ? StringUtils.substringBefore(classSuperCanonicalNameGeneric, ",") : classSuperCanonicalNameGeneric;
				indexStoreSolr(classDoc, "classSuperCanonicalNameGeneric", languageName, classSuperCanonicalNameGeneric);
				superClassGenericQdox = builder.getClassByName(classSuperCanonicalNameGeneric);
				classSuperCompleteNameGeneric = classSuperCanonicalNameGeneric;

				if(classSuperCanonicalNameGeneric.contains("."))
					classSuperSimpleNameGeneric = StringUtils.substringAfterLast(classSuperCanonicalNameGeneric, ".");
				else
					classSuperSimpleNameGeneric = classSuperCanonicalNameGeneric;
				indexStoreSolr(classDoc, "classSuperSimpleNameGeneric", languageName, classSuperSimpleNameGeneric);

				ClassParts classePartsBase = ClassParts.initClassParts(this, classSuperCanonicalNameGeneric, languageName);
				baseClassExtendsGen = classePartsBase.extendsGen;
				if(baseClassExtendsGen == null)
					baseClassExtendsGen = false;
			}
		}
		Boolean classIsBase = storeSolr(classDoc, "classIsBase", !baseClassExtendsGen || StringUtils.isEmpty(classSuperCompleteNameGeneric) || StringUtils.equals(classSuperCompleteNameGeneric, "java.lang.Object"));
		Boolean classExtendsBase = storeSolr(classDoc, "classExtendsBase", !classIsBase && baseClassExtendsGen && !StringUtils.equals(classSuperCompleteNameGeneric, "java.lang.Object"));
		indexStoreSolr(classDoc, "baseClassExtendsGen", baseClassExtendsGen);
		Boolean classContainsSiteRequest = false;
		try {
			indexStoreSolr(classDoc, "classContainsSiteRequest", classQdox.getMethodBySignature("getRequeteSite", new ArrayList<JavaType>(), true) != null);
		} catch (Exception e) {
			// TODO ctate fix this to pull from solr. 
		}
		
		String classComment = storeRegexComments(classQdox.getComment(), languageName, classDoc, "classComment");
		String classPackageName = StringUtils.substringBeforeLast(classCanonicalName, ".");
		String classPath = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), ".java");
		String classDirPath = StringUtils.substringBeforeLast(classPath, "/");
		String classGenPath = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), "Gen.java");
		String classPathApiGen = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), "ApiGen.java");
		String classPathPageGen = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), "PageGen.java");
		String classGenDirPath = StringUtils.substringBeforeLast(classGenPath, "/");
		String classKey = classAbsolutePath;
		Instant modified = Instant.now();
		Date modifiedDate = Date.from(modified);
		Boolean classContainsWrap = false;

		ClassParts classPartsChain = classPartsChain(domainPackageName);
		Boolean classExtendsGen = StringUtils.endsWith(classSuperSimpleName, "Gen");
		ClassParts classPartsSiteRequest = classPartsSiteRequest(domainPackageName);
		if(superClassError || !classExtendsGen && regexFound("^gen:\\s*(true)$", classComment)) {
			classExtendsGen = true;
		}
		Boolean classModel = indexStoreSolr(classDoc, "classModel", regexFound("^modele: \\s*(true)$", classComment));
		Boolean classeApi = indexStoreSolr(classDoc, "classeApi", regexFound("^api: \\s*(true)$", classComment) || classModel);
		Boolean classePage = indexStoreSolr(classDoc, "classePage", regexFound("^page: \\s*(true)$", classComment) || classModel);
		Boolean classInitDeep = !regexFound("^initLoin:\\s*(false)$", classComment);
		if(classInitDeep)
			classInitDeep = classExtendsBase || classIsBase;
		classInitDeep = storeSolr(classDoc, "classInitDeep", classInitDeep);
		if(classInitDeep)
			classPartsGenAdd(classPartsSiteRequest);
		indexStoreSolr(classDoc, "classExtendsGen", classExtendsGen);
		Boolean classeSauvegarde = indexStoreSolr(classDoc, "classeSauvegarde", regexFound("^saved:\\s*(true)$", classComment) || classModel);
		Boolean classeIndexe = indexStoreSolr(classDoc, "classeIndexe", regexFound("^indexed:\\s*(true)$", classComment) || classeSauvegarde || classModel);

		ClassParts classPartsSolrInputDocument = ClassParts.initClassParts(this, "org.apache.solr.common.SolrInputDocument", languageName);
		ClassParts classPartsSolrClient = ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrClient", languageName);
		ClassParts classePartsTest = ClassParts.initClassParts(this, "org.junit.Test", languageName);
		ClassParts classPartsSiteContext = classPartsSiteContext(domainPackageName);
		ClassParts classPartsSiteConfig = classPartsSiteConfig(domainPackageName);
		ClassParts classPartsSiteUser = classPartsSiteUser(domainPackageName);
		ClassParts classPartsCluster = classPartsCluster(domainPackageName);
		ClassParts classPartsSearchResult = classPartsSearchResult(domainPackageName);

		if(classePage) {
			classPartsGenPageAdd(classPartsSiteConfig);
			classPartsGenPageAdd(classPartsSiteRequest);
			classPartsGenPageAdd(classPartsSiteContext);
			classPartsGenPageAdd(classPartsSiteUser);
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.io.IOException", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.servlet.http.HttpServlet", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.http.HttpServerRequest", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.http.HttpServerResponse", languageName));
		}

		if(classeApi) {
			classPartsGenApiAdd(classPartsSiteConfig);
			classPartsGenApiAdd(classPartsSiteRequest);
			classPartsGenApiAdd(classPartsSiteContext);
			classPartsGenApiAdd(classPartsSiteUser);
			classPartsGenApiAdd(classPartsSearchResult);
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.io.IOException", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.servlet.http.HttpServlet", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.servlet.http.HttpServerRequest", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.servlet.http.HttpServerResponse", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Collections", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Map", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.servlet.ServletException", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.concurrent.TimeUnit", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.stream.Collectors", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.json.Json", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.json.JsonArray", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.json.JsonObject", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.json.JsonReader", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrQuery", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrQuery.ORDER", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.response.QueryResponse", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.util.ClientUtils", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.StringUtils", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.keycloak.KeycloakPrincipal", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.keycloak.KeycloakSecurityContext", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.keycloak.representations.AccessToken", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.keycloak.representations.AccessToken.Access", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.security.Principal", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.mail.Message", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.mail.Session", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.mail.Transport", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.mail.internet.InternetAddress", languageName));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.mail.internet.MimeMessage", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.io.PrintWriter", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.common.SolrDocumentList", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.common.SolrDocument", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Collection", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.math.BigDecimal", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Date", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.time.ZoneId", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.List", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.ArrayList", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Arrays", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Set", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Handler", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.RoutingContext", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.Router", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Vertx", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.reactivestreams.ReactiveReadStream", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.reactivestreams.ReactiveWriteStream", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.MultiMap", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.auth.oauth2.OAuth2Auth", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.netty.handler.codec.http.HttpResponseStatus", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.Logger", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.LoggerFactory", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.validation.HTTPRequestValidationHandler", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.validation.ParameterTypeValidator", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.validation.ValidationException", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLClient", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLConnection", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonArray", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonObject", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.time.LocalDateTime", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.sql.Timestamp", languageName));
		}
		if(classeIndexe) {
			classPartsGenAdd(classPartsSolrInputDocument);
			classPartsGenAdd(classPartsSolrClient);
//			classPartsGenAdd(classePartsTest);
			classPartsGenAdd(classPartsSiteContext);
		}
		if(classExtendsBase || classIsBase) {
			classPartsGenAdd(classPartsCluster);
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.http.HttpServerResponse", languageName));
		}
		if(classeSauvegarde) {
			classPartsGenAdd(classPartsSiteContext);
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.Logger", languageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.LoggerFactory", languageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLClient", languageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLConnection", languageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonArray", languageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonObject", languageName));
			classPartsGenAdd(ClassParts.initClassParts(this, VAL_canonicalNameDate, languageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "java.util.Set", languageName));
		}
		classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.commons.text.StringEscapeUtils", languageName));

		
		ArrayList<JavaClass> qdoxSuperClasses = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesAndMe = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesAndMeWithoutGen = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesAndInterfaces = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesInterfacesAndMe = new ArrayList<JavaClass>();
		populateQdoxSuperClassesInterfacesAndMe(classQdox, qdoxSuperClasses, qdoxSuperClassesAndMe, qdoxSuperClassesAndMeWithoutGen, qdoxSuperClassesAndInterfaces, qdoxSuperClassesInterfacesAndMe);

		for(JavaClass c : qdoxSuperClassesAndMeWithoutGen) {
			indexStoreListSolr(classDoc, "entitySuperClassesAndMeWithoutGen", c.getCanonicalName()); 

		}

		indexStoreSolr(classDoc, "languageName", languageName); 
		indexStoreSolr(classDoc, "modified", modifiedDate); 
		indexStoreSolr(classDoc, "classCanonicalName", languageName, classCanonicalName); 
		indexStoreSolr(classDoc, "classePageUri", languageName, regex("^pageUri\\." + languageName + ":\\s*(.*)", classComment));
		indexStoreSolr(classDoc, "classeApiUri", languageName, regex("^apiUri\\." + languageName + ":\\s*(.*)", classComment));
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
		indexStoreSolr(classDoc, "classPathApiGen", languageName, classPathApiGen); 
		indexStoreSolr(classDoc, "classPathPageGen", languageName, classPathPageGen); 
		indexStoreSolr(classDoc, "classGenDirPath", languageName, classGenDirPath); 
		indexStoreSolr(classDoc, "domainPackageName", domainPackageName); 

		if(classComment != null) {

			Matcher classValsSearch = Pattern.compile("^val\\.(\\w+)\\.(\\w+):(.*)", Pattern.MULTILINE).matcher(classComment);
			boolean classValsFound = classValsSearch.find();
			while(classValsFound) {
				String classValVar = classValsSearch.group(1);
				String classValLanguage = classValsSearch.group(2);
				String classValValue = classValsSearch.group(3);
				storeListSolr(classDoc, "classValsVar", classValVar);
				storeListSolr(classDoc, "classValsLanguage", classValLanguage);
				storeListSolr(classDoc, "classValsValue", classValValue);
				classValsFound = classValsSearch.find();
			}

			Matcher classRolesSearch = Pattern.compile("^role\\.(\\w+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classComment);
			boolean classRolesFound = classRolesSearch.find();
			boolean classRolesFoundCurrent = classRolesFound;
			while(classRolesFoundCurrent) {
				String classRoleLanguage = classRolesSearch.group(1);
				String classRoleValue = classRolesSearch.group(2);
				storeListSolr(classDoc, "classRoles", classRoleLanguage, classRoleValue);
				classRolesFound = true;
				classRolesFoundCurrent = classRolesSearch.find();
			}
			indexStoreSolr(classDoc, "classRolesFound", classRolesFound); 
		}

		SolrDocument classSuperCanonicalNameDoc = null;   
		if(StringUtils.startsWith(classSuperCanonicalName, domainPackageName)) {
			SolrQuery solrSearch = new SolrQuery();   
			solrSearch.setQuery("*:*");
			solrSearch.setRows(1);
			solrSearch.addFilterQuery("classCanonicalName_" + languageActualName + "_indexed_string:" + ClientUtils.escapeQueryChars(classSuperCanonicalName));
			solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
			solrSearch.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
			QueryResponse searchResponse = solrClientComputate.query(solrSearch);
			SolrDocumentList searchList = searchResponse.getResults();
			if(searchList.size() > 0) { 
				classSuperCanonicalNameDoc = searchList.get(0);
			}
		}  

		if(classExtendsGen) {
			ClassParts classPartsSuperGeneric = ClassParts.initClassParts(this, classSuperCompleteNameGeneric, languageName);
			classPartsGenAdd(classPartsSuperGeneric);
		}

		for(String languageName : otherLanguages) { 
			String appPathLanguage = appPaths.get(languageName);
			storeRegexComments(classComment, languageName, classDoc, "classComment");
			String srcMainJavaPathLanguage = appPathLanguage + "/src/main/java";
			String srcGenJavaPathLanguage = appPathLanguage + "/src/gen/java";
			String classCanonicalNameLanguage = regex("^canonicalName\\." + languageName + ":\\s*(.*)", classComment, classCanonicalName);
			String classPageUriLanguage = indexStoreSolr(classDoc, "classePageUri", languageName, regex("^pageUri\\." + languageName + ":\\s*(.*)", classComment));
			String classApiUriLanguage = indexStoreSolr(classDoc, "classeApiUri", languageName, regex("^apiUri\\." + languageName + ":\\s*(.*)", classComment));
			String classSimpleNameLanguage = StringUtils.substringAfterLast(classCanonicalNameLanguage, ".");
			String classPackageNameLanguage = StringUtils.substringBeforeLast(classCanonicalNameLanguage, ".");
			String classCanonicalNameGenLanguage = classCanonicalNameLanguage + "Gen";
			String classCanonicalNameApiLanguage = classCanonicalNameLanguage + "Api";
			String classCanonicalNameApiGenLanguage = classCanonicalNameLanguage + "ApiGen";
			String classCanonicalNamePageLanguage = classCanonicalNameLanguage + "Page";
			String classCanonicalNamePageGenLanguage = classCanonicalNameLanguage + "PageGen";
			String classSimpleNameGenLanguage = classSimpleNameLanguage + "Gen";
			String classSimpleNameApiLanguage = classSimpleNameLanguage + "Api";
			String classSimpleNamePageLanguage = classSimpleNameLanguage + "Page";
			String classSimpleNameApiGenLanguage = classSimpleNameLanguage + "ApiGen";
			String classSimpleNamePageGenLanguage = classSimpleNameLanguage + "PageGen";
			String classPathLanguage = indexStoreSolr(classDoc, "classPath", languageName, concat(srcMainJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameLanguage, ".", "/"), ".java"));
			String classDirPathLanguage = storeSolr(classDoc, "classDirPath", languageName, StringUtils.substringBeforeLast(classPathLanguage, "/"));
			String classGenPathLanguage = indexStoreSolr(classDoc, "classGenPath", languageName, concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameGenLanguage, ".", "/"), ".java"));
			String classPathApiGenLangue = indexStoreSolr(classDoc, "classPathApiGen", languageName, concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameApiGenLanguage, ".", "/"), ".java"));
			String classPathPageGenLangue = indexStoreSolr(classDoc, "classPathPageGen", languageName, concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNamePageGenLanguage, ".", "/"), ".java"));
			String classGenDirPathLanguage = storeSolr(classDoc, "classGenDirPath", languageName, StringUtils.substringBeforeLast(classGenPathLanguage, "/"));

			indexStoreSolr(classDoc, "classCanonicalName", languageName, classCanonicalNameLanguage); 
			indexStoreSolr(classDoc, "classSimpleName", languageName, classSimpleNameLanguage); 
			indexStoreSolr(classDoc, "classCanonicalNameGen", languageName, classCanonicalNameGenLanguage); 
			indexStoreSolr(classDoc, "classSimpleNameGen", languageName, classSimpleNameGenLanguage); 
			indexStoreSolr(classDoc, "classSimpleNameApi", languageName, classSimpleNameApiLanguage); 
			indexStoreSolr(classDoc, "classSimpleNamePage", languageName, classSimpleNamePageLanguage); 
			indexStoreSolr(classDoc, "classSimpleNameApiGen", languageName, classSimpleNameApiGenLanguage); 
			indexStoreSolr(classDoc, "classSimpleNamePageGen", languageName, classSimpleNamePageGenLanguage); 
			indexStoreSolr(classDoc, "classPackageName", languageName, classPackageNameLanguage); 

			String classSuperCompleteNameLanguage;
			ClassParts classSuperPartsLanguage;

			if(classExtendsGen) {
				classSuperPartsLanguage = ClassParts.initClassParts(this, classCanonicalNameLanguage + "Gen", languageName);
			}
			else {
				classSuperPartsLanguage = ClassParts.initClassParts(this, classSuperQdox, languageName);
			}

			indexStoreSolr(classDoc, "classSuperCanonicalName", languageName, classSuperPartsLanguage.canonicalName); 
			indexStoreSolr(classDoc, "classSuperSimpleName", languageName, classSuperPartsLanguage.simpleName); 
			indexStoreSolr(classDoc, "classCanonicalNameCompletSuper", languageName, classSuperPartsLanguage.canonicalNameComplete);
			indexStoreSolr(classDoc, "classSimpleNameCompletSuper", languageName, classSuperPartsLanguage.simpleNameComplete);
			if(StringUtils.isNotEmpty(classSuperCompleteNameGeneric)) {
				ClassParts classPartsSuperGenericLanguage = ClassParts.initClassParts(this, classSuperCompleteNameGeneric, languageName);
				indexStoreSolr(classDoc, "classSuperCanonicalNameGeneric", languageName, classPartsSuperGenericLanguage.canonicalNameComplete);
				indexStoreSolr(classDoc, "classSuperSimpleNameGeneric", languageName, classPartsSuperGenericLanguage.simpleNameComplete);
				if(classExtendsGen) {
					classPartsGenAdd(classPartsSuperGenericLanguage);
				}
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
		
		for(String classImport : classQdox.getSource().getImports()) {
			ClassParts classImportClassParts = ClassParts.initClassParts(this, classImport, languageName);
			indexStoreListSolr(classDoc, "classImports", languageName, classImportClassParts.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classImportClassParts, languageName);
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
					String constructorParamVar = constructorParamQdox.getName();
					storeListSolr(constructorDoc, "constructorParamsVar", languageName, constructorParamVar);
					ClassParts constructorParamClassParts = ClassParts.initClassParts(this, constructorParamQdox.getJavaClass(), languageName);
					storeListSolr(constructorDoc, "constructorParamsSimpleNameComplete", languageName, constructorParamClassParts.simpleNameComplete);
					storeListSolr(constructorDoc, "constructorParamsVariableArgs", constructorParamQdox.isVarArgs());
					for(String languageName : otherLanguages) { 
						String constructorParamVarLanguage = regex("param" + constructorParamNum + "\\.var\\." + languageName + ": (.*)", constructorComment);
						if(constructorParamVarLanguage == null)
							constructorParamVarLanguage = constructorParamVar;
						ClassParts constructorParamClassPartsLanguage = ClassParts.initClassParts(this, constructorParamClassParts, languageName);

						storeListSolr(constructorDoc, "constructorParamsSimpleNameComplete", languageName, constructorParamClassPartsLanguage.simpleNameComplete);
						storeListSolr(constructorDoc, "constructorParamsVar", languageName, constructorParamVarLanguage);
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
				Boolean ignore = "true".equals(regex("ignore: (.*)", methodComment));
				if(!ignore) {
					JavaClass methodClassQdoxReturn = methodQdox.getReturns();
					String methodCanonicalNameReturnComplete = null;
					String methodCanonicalNameReturn = null;
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

						// is Entity. 
						SolrInputDocument entityDoc = classDocClone.deepCopy();
						String entityVar = indexStoreSolr(entityDoc, "entityVar", languageName, StringUtils.substringAfter(methodQdox.getName(), "_"));
						String entityVarCapitalized = indexStoreSolr(entityDoc, "entityVarCapitalized", languageName, StringUtils.capitalize(entityVar));
						JavaClass entityClassQdox = methodParamsQdox.get(0).getJavaClass();
						ClassParts entityClassParts = ClassParts.initClassParts(this, entityClassQdox, languageName);
						Boolean entityWrap = false;

						if(entityClassParts.simpleName.equals("Couverture")) {
							entityClassParts = ClassParts.initClassParts(this, entityClassParts.canonicalNameGeneric, entityVar);
							entityWrap = true;
							classContainsWrap = true;
						}

						classPartsGenAdd(entityClassParts);
						List<String> entityCanonicalNamesSuperAndMeWithoutGen = new ArrayList<String>();
						if(StringUtils.isNotEmpty(entityClassParts.canonicalNameGeneric)) {
							ClassParts classPartsGeneric = ClassParts.initClassParts(this, entityClassParts.canonicalNameGeneric, languageName);
							classPartsGenAdd(classPartsGeneric);

							if(classPartsGeneric.solrDocument != null) {
								List<String> entitySuperClassesAndMeWithoutGen = (List<String>)classPartsGeneric.solrDocument.get("entitySuperClassesAndMeWithoutGen_stored_strings");
								if(entitySuperClassesAndMeWithoutGen != null) {
									for(String canonicalName : entitySuperClassesAndMeWithoutGen) {
										entityCanonicalNamesSuperAndMeWithoutGen.add(canonicalName);
										indexStoreListSolr(entityDoc, "entiteClassesSuperQdoxEtMoiSansGen", canonicalName); 
									}
								}
							}
						}
						else if(entityClassParts != null && entityClassParts.solrDocument != null) {

							List<String> entitySuperClassesAndMeWithoutGen = (List<String>)entityClassParts.solrDocument.get("entitySuperClassesAndMeWithoutGen_stored_strings");
							if(entitySuperClassesAndMeWithoutGen != null) {
								for(String canonicalName : entitySuperClassesAndMeWithoutGen) {
									entityCanonicalNamesSuperAndMeWithoutGen.add(canonicalName);
									indexStoreListSolr(entityDoc, "entiteClassesSuperQdoxEtMoiSansGen", canonicalName); 
								}
							}
						}

						indexStoreSolr(entityDoc, "entityWrap", entityWrap);
						indexStoreSolr(entityDoc, "entityInitialized", true);

						String entityCanonicalName = indexStoreSolr(entityDoc, "entityCanonicalName", languageName, entityClassParts.canonicalName);
						String entitySimpleName = indexStoreSolr(entityDoc, "entitySimpleName", languageName, entityClassParts.simpleName);
						String entityCompleteNameGeneric = indexStoreSolr(entityDoc, "entityCompleteNameGeneric", languageName, entityClassParts.canonicalNameGeneric);
						String entityCanonicalNameGeneric = indexStoreSolr(entityDoc, "entityCanonicalNameGeneric", languageName, entityClassParts.canonicalNameGeneric);
						String entitySimpleNameGeneric = indexStoreSolr(entityDoc, "entitySimpleNameGeneric", languageName, entityClassParts.simpleNameGeneric);
						String entityCanonicalNameActuel = entityCanonicalNameGeneric == null ? entityCanonicalName : entityCanonicalNameGeneric;
						String entitySimpleNameActuel = entitySimpleNameGeneric == null ? entitySimpleName : entitySimpleNameGeneric;
						indexStoreSolr(entityDoc, "entityCanonicalNameComplete", languageName, entityClassParts.canonicalNameComplete);
						indexStoreSolr(entityDoc, "entitySimpleNameComplete", languageName, entityClassParts.simpleNameComplete);
						indexStoreSolr(entityDoc, "entitySimpleNameCompleteGeneric", languageName, entityClassParts.simpleNameGeneric);

						JavaMethod entitySetter = null;
						try {
							entitySetter = classQdox.getMethodBySignature("set" + entityVarCapitalized, new ArrayList<JavaType>() {{ add(classQdoxString); }}, true);
						} catch (Exception e) {
						}

						Boolean entityDefined = storeSolr(entityDoc, "entityDefined", 
								entityCanonicalName.equals(VAL_canonicalNameString)
								|| classPartsChain != null && entityCanonicalName.equals(classPartsChain.canonicalName)
								|| entityCanonicalName.equals(VAL_canonicalNameBoolean)
								|| entityCanonicalName.equals(VAL_canonicalNameInteger)
								|| entityCanonicalName.equals(VAL_canonicalNameBigDecimal)
								|| entityCanonicalName.equals(VAL_canonicalNameDouble)
								|| entityCanonicalName.equals(VAL_canonicalNameFloat)
								|| entityCanonicalName.equals(VAL_canonicalNameLong)
								|| entityCanonicalName.equals(VAL_canonicalNameLocalDateTime)
								|| entityCanonicalName.equals(VAL_canonicalNameLocalDate)
								|| entityCanonicalName.equals(VAL_canonicalNameTimestamp)
								|| entityCanonicalName.equals(VAL_canonicalNameDate)
								|| classPartsChain != null && entityCanonicalName.equals(VAL_canonicalNameList) && classPartsChain.canonicalName.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameString.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameBoolean.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameInteger.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameBigDecimal.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameDouble.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameFloat.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameLong.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameLocalDateTime.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameLocalDate.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameTimestamp.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameDate.equals(entityCanonicalNameGeneric)
								|| classPartsChain != null && entityCanonicalName.equals(VAL_canonicalNameArrayList) && classPartsChain.canonicalName.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameString.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameBoolean.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameInteger.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameBigDecimal.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameDouble.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameFloat.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLong.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLocalDateTime.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLocalDate.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameTimestamp.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameDate.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLong.equals(entityCanonicalNameGeneric)
								|| entitySetter != null
								);
						
						JavaClass entityClassQdoxBase = null;
						JavaClass entitySuperClassQdox = entityClassQdox.getSuperJavaClass();
						if(entitySuperClassQdox != null) {
							String entityCanonicalNameSuperQdox = entitySuperClassQdox.getCanonicalName();
							if(entityCanonicalNameSuperQdox.endsWith("Gen")) {
								entityClassQdoxBase = entitySuperClassQdox.getSuperJavaClass();
							}
						}
						
						String entityCanonicalNameBase = null;
						if(entityClassQdoxBase != null) {
							String s = entityClassQdoxBase.getCanonicalName();
							if(s.contains("."))
								entityCanonicalNameBase = s;
						}
						indexStoreSolr(entityDoc, "entityCanonicalNameBase", languageName, entityCanonicalNameBase);
						
						String entitySimpleNameBase = null;
						if(StringUtils.isNotEmpty(entityCanonicalNameBase)) {
							entitySimpleNameBase = StringUtils.substringAfterLast(entityCanonicalNameBase, ".");
						}
						indexStoreSolr(entityDoc, "entitySimpleNameBase", languageName, entitySimpleNameBase);
						
						String entityVarParam;
						if(entityClassParts.canonicalName.equals(ArrayList.class.getCanonicalName()) || entityClassParts.canonicalName.equals(List.class.getCanonicalName()))
							entityVarParam = "l";
						else
							entityVarParam = "o";
						indexStoreSolr(entityDoc, "entityVarParam", languageName, entityVarParam);
						
						String entityVarCouverture = indexStoreSolr(entityDoc, "entityVarCouverture", languageName, entityVar + "Couverture");

						Boolean entityInitDeep = indexStoreSolr(entityDoc, "entityInitDeep", !entityVar.endsWith("_") && BooleanUtils.isTrue(entityClassParts.extendsGen));
						
//						String entityParamVar = StringUtils.equalsAny(entityClassQdox, "");
//						indexStoreSolr(entityDoc, "entityParamVar", regexFound("^exact:\\s*(true)$", methodComment));
//							if(canonicalName.equals(classe_.canonicalNameArrayList) || canonicalName.equals(classe_.canonicalNameList))
//								o.tout("l");
//							else if(o.estVide())
//								o.tout("o");

						if(entityCanonicalNamesSuperAndMeWithoutGen.size() > 0) {
							String fqSuperClassesAndMe = "(" + qdoxSuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c.getCanonicalName())).collect(Collectors.joining(" OR ")) + ")";

							SolrQuery solrSearchMethodBefore = new SolrQuery();   
							solrSearchMethodBefore.setQuery("*:*");
							solrSearchMethodBefore.setRows(10);
							String fqMethodBefore = "(" + entityCanonicalNamesSuperAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars("avant" + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							solrSearchMethodBefore.addFilterQuery("entitySuperClassesAndMeWithoutGen_indexed_strings:" + fqSuperClassesAndMe);
							solrSearchMethodBefore.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
							solrSearchMethodBefore.addFilterQuery("partIsMethod_indexed_boolean:true");
							solrSearchMethodBefore.addFilterQuery("methodVar_" + languageName + "_indexed_string:" + fqMethodBefore);
							QueryResponse searchResponseMethodBefore = solrClientComputate.query(solrSearchMethodBefore);
							SolrDocumentList searchListMethodBefore = searchResponseMethodBefore.getResults();
	
							for(SolrDocument solrDocument : searchListMethodBefore) {
								String methodVarCurrent = (String)solrDocument.get("methodVar_" + languageName + "_stored_string");
								String methodClassCanonicalName = (String)solrDocument.get("classCanonicalName_frFR_stored_string");
								List<String> methodParamsSimpleNameComplete = (List<String>)solrDocument.get("methodParamsSimpleNameComplete_" + languageName + "_stored_strings");
								String methodParamSimpleNameComplete = methodParamsSimpleNameComplete.get(0);
								List<String> methodParamsVar = (List<String>)solrDocument.get("methodParamsVar_" + languageName + "_stored_strings");
								String methodeParamVar = methodParamsVar.get(0);
								storeListSolr(entityDoc, "entityMethodsBeforeVisibility", BooleanUtils.isTrue((Boolean)solrDocument.get("methodIsPublic_stored_boolean")) ? "public" : "protected");
								storeListSolr(entityDoc, "entityMethodsBeforeVar", methodVarCurrent);
								storeListSolr(entityDoc, "entityMethodsBeforeParamVar", methodeParamVar);
								storeListSolr(entityDoc, "entityMethodsBeforeSimpleName", methodParamSimpleNameComplete);
								storeListSolr(entityDoc, "entityMethodsBeforeParamName", methodParamsVar.size() > 1);
								Boolean entityMethodsBeforeWrite = (StringUtils.equals(methodClassCanonicalName, classCanonicalName)) && !classMethodsWritten.contains(methodVarCurrent);
								storeListSolr(entityDoc, "entityMethodsBeforeWrite", entityMethodsBeforeWrite);
								classMethodsWritten.add(methodVarCurrent);
								List<String> methodParamCanonicalNames = (List<String>)solrDocument.get("methodParamCanonicalNames_" + languageName + "_stored_strings");
								if(methodParamCanonicalNames != null) {
									String methodParamCanonicalName = methodParamCanonicalNames.get(0);
									classPartsGenAdd(ClassParts.initClassParts(this, methodParamCanonicalName, languageName));
								}
							}
	
	//						List<JavaMethod> entityMethodsBefore = new ArrayList<JavaMethod>();
	//						entityMethodsBefore.add(classQdox.getMethodBySignature(entityVar + "Avant", new ArrayList<JavaType>() {{ add(entityClassQdox); }}, true));
	//						for(JavaClass c : qdoxSuperClassesAndMe) {
	//							String cNomSimple = StringUtils.substringAfterLast(c.getCanonicalName(), ".");
	//							entityMethodsBefore.add(classQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); }}, true));
	//							entityMethodsBefore.add(classQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); add(classQdoxString); }}, true));
	//						}
	//						for(JavaMethod methode : entityMethodsBefore) {
	//							if(methode != null) {
	//								JavaParameter param = methode.getParameters().get(0);
	//								storeListSolr(entityDoc, "entityMethodsBeforeVisibility", methode.isPublic() ? "public" : "protected");
	//								storeListSolr(entityDoc, "entityMethodsBeforeVar", methode.getName());
	//								storeListSolr(entityDoc, "entityMethodsBeforeParamVar", param.getName());
	//								storeListSolr(entityDoc, "entityMethodsBeforeSimpleName", StringUtils.substringAfterLast(param.getCanonicalName(), "."));
	//								storeListSolr(entityDoc, "entityMethodsBeforeParamName", methode.getParameters().size() > 1);
	//							}
	//						}
	
							SolrQuery solrSearchMethodAfter = new SolrQuery();   
							solrSearchMethodAfter.setQuery("*:*");
							solrSearchMethodAfter.setRows(10);
							String fqMethodAfter = "(" + entityCanonicalNamesSuperAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars("apres" + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							solrSearchMethodAfter.addFilterQuery("entitySuperClassesAndMeWithoutGen_indexed_strings:" + fqSuperClassesAndMe);
							solrSearchMethodAfter.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
							solrSearchMethodAfter.addFilterQuery("partIsMethod_indexed_boolean:true");
							solrSearchMethodAfter.addFilterQuery("methodVar_" + languageName + "_indexed_string:" + fqMethodAfter);
							QueryResponse searchResponseMethodAfter = solrClientComputate.query(solrSearchMethodAfter);
							SolrDocumentList searchListMethodAfter = searchResponseMethodAfter.getResults();
	
							for(SolrDocument solrDocument : searchListMethodAfter) {
								String methodVarCurrent = (String)solrDocument.get("methodVar_" + languageName + "_stored_string");
								String methodClassCanonicalName = (String)solrDocument.get("classCanonicalName_frFR_stored_string");
								List<String> methodParamsSimpleNameComplete = (List<String>)solrDocument.get("methodParamsSimpleNameComplete_" + languageName + "_stored_strings");
								String methodParamSimpleNameComplete = methodParamsSimpleNameComplete.get(0);
								List<String> methodParamsVar = (List<String>)solrDocument.get("methodParamsVar_" + languageName + "_stored_strings");
								String methodeParamVar = methodParamsVar.get(0);
								storeListSolr(entityDoc, "entityMethodsAfterVisibility", BooleanUtils.isTrue((Boolean)solrDocument.get("methodIsPublic_stored_boolean")) ? "public" : "protected");
								storeListSolr(entityDoc, "entityMethodsAfterVar", methodVarCurrent);
								storeListSolr(entityDoc, "entityMethodsAfterParamVar", methodeParamVar);
								storeListSolr(entityDoc, "entityMethodsAfterSimpleName", methodParamSimpleNameComplete);
								storeListSolr(entityDoc, "entityMethodsAfterParamName", methodParamsVar.size() > 1);
								Boolean entityMethodsBeforeWrite = (StringUtils.equals(methodClassCanonicalName, classCanonicalName)) && !classMethodsWritten.contains(methodVarCurrent);
								storeListSolr(entityDoc, "entityMethodsBeforeWrite", entityMethodsBeforeWrite);
								classMethodsWritten.add(methodVarCurrent);
								List<String> methodParamCanonicalNames = (List<String>)solrDocument.get("methodParamCanonicalNames_" + languageName + "_stored_strings");
								if(methodParamCanonicalNames != null) {
									String methodParamCanonicalName = methodParamCanonicalNames.get(0);
									classPartsGenAdd(ClassParts.initClassParts(this, methodParamCanonicalName, languageName));
								}
							}
						}

//						List<JavaMethod> entityMethodsAfter = new ArrayList<JavaMethod>();
//						entityMethodsAfter.add(classQdox.getMethodBySignature(entityVar + "Apres", new ArrayList<JavaType>() {{ add(entityClassQdox); }}, true));
//						for(JavaClass c : qdoxSuperClassesAndMe) {
//							String cNomSimple = StringUtils.substringAfterLast(c.getCanonicalName(), ".");
//							entityMethodsAfter.add(classQdox.getMethodBySignature("apres" + cNomSimple, new ArrayList<JavaType>() {{ add(c); }}, true));
//							entityMethodsAfter.add(classQdox.getMethodBySignature("apres" + cNomSimple, new ArrayList<JavaType>() {{ add(c); add(classQdoxString); }}, true));
//						}
//						for(JavaMethod methode : entityMethodsAfter) {
//							if(methode != null) {
//								JavaParameter param = methode.getParameters().get(0);
//								storeListSolr(entityDoc, "entityMethodsAfterVar", methode.getName());
//								storeListSolr(entityDoc, "entityMethodsAfterParamVar", param.getName());
//								storeListSolr(entityDoc, "entityMethodsAfterSimpleName", StringUtils.substringAfterLast(param.getCanonicalName(), "."));
//								storeListSolr(entityDoc, "entityMethodsAfterParamName", methode.getParameters().size() > 1);
//							}
//						}

						if(methodComment != null) {
							Matcher entityOptionsSearch = Pattern.compile("^option\\.(\\w+)\\.(\\w+):(.*)", Pattern.MULTILINE).matcher(methodComment);
							boolean entityOptionsFound = entityOptionsSearch.find();
							while(entityOptionsFound) {
								String entityOptionVar = entityOptionsSearch.group(1);
								String entityOptionLanguage = entityOptionsSearch.group(2);
								String entityOptionValue = entityOptionsSearch.group(3);
								storeListSolr(entityDoc, "entityOptionsVar", entityOptionVar);
								storeListSolr(entityDoc, "entityOptionsLanguage", entityOptionLanguage);
								storeListSolr(entityDoc, "entityOptionsValue", entityOptionValue);
								entityOptionsFound = entityOptionsSearch.find();
							}
							if(entityOptionsFound)
								storeSolr(entityDoc, "entityOptions", true);
						}

						indexStoreSolr(entityDoc, "entityExact", regexFound("^exact:\\s*(true)$", methodComment));
						Boolean entityUniqueKey = indexStoreSolr(entityDoc, "entityUniqueKey", regexFound("^primaryKey:\\s*(true)$", methodComment));
						Boolean entityEncrypted = indexStoreSolr(entityDoc, "entityEncrypted", regexFound("^encrypted:\\s*(true)$", methodComment));
						Boolean entitySuggested = indexStoreSolr(entityDoc, "entitySuggested", regexFound("^suggested:\\s*(true)$", methodComment));
						Boolean entitySaved = indexStoreSolr(entityDoc, "entitySaved", regexFound("^saved:\\s*(true)$", methodComment));
						Boolean entityIndexed = indexStoreSolr(entityDoc, "entityIndexed", regexFound("^indexed:\\s*(true)$", methodComment));
						Boolean entityIncremented = indexStoreSolr(entityDoc, "entityIncremented", regexFound("^incremented:\\s*(true)$", methodComment));
						Boolean entityStored = indexStoreSolr(entityDoc, "entityStored", regexFound("^stored:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityIndexedOrStored", entityUniqueKey || entityEncrypted || entitySuggested || entityIndexed || entityStored || entityIncremented);
						indexStoreSolr(entityDoc, "entityText", regexFound("^text:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityIgnored", regexFound("^ignore:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityDeclared", regexFound("^declared:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entitySearch", regexFound("^search:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityAdd", regexFound("^add:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityDelete", regexFound("^delete:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityModify", regexFound("^modify:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityRefresh", regexFound("^refresh:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityMultiLine", regexFound("^multiline:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityKeys", regexFound("^keys:\\s*(true)$", methodComment));

						indexStoreSolr(entityDoc, "entityDisplayName", languageName, regex("^displayName." + languageName + ":\\s*(.*)$", methodComment, 1));
						indexStoreSolr(entityDoc, "entiteHtmlTooltip", languageName, regex("^htmlTooltip." + languageName + ":\\s*(.*)$", methodComment, 1));
						for(String languageName : otherLanguages) {  
							indexStoreSolr(entityDoc, "entityDisplayName", languageName, regex("^displayName." + languageName + ":\\s*(.*)$", methodComment, 1));
							indexStoreSolr(entityDoc, "entiteHtmlTooltip", languageName, regex("^htmlTooltip." + languageName + ":\\s*(.*)$", methodComment, 1));
						}

						Matcher entiteAttribuerRecherche = methodComment == null ? null : Pattern.compile("^attribuer:\\s*([^\\.]+)\\.(.*)\\s*", Pattern.MULTILINE).matcher(methodComment);
						boolean entiteAttribuerTrouve = entiteAttribuerRecherche == null ? false : entiteAttribuerRecherche.find();
						if(entiteAttribuerTrouve) {
							String entiteAttribuerNomSimple = entiteAttribuerRecherche.group(1);
							String entiteAttribuerVar = entiteAttribuerRecherche.group(2);

							SolrQuery solrSearchClasse = new SolrQuery();   
							solrSearchClasse.setQuery("*:*");
							solrSearchClasse.setRows(1);
							solrSearchClasse.addFilterQuery("classSimpleName_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerNomSimple));
							solrSearchClasse.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
							solrSearchClasse.addFilterQuery("partIsClass_indexed_boolean:true");
							QueryResponse searchResponseClasse = solrClientComputate.query(solrSearchClasse);
							SolrDocumentList searchListClasse = searchResponseClasse.getResults();

							if(searchListClasse.size() > 0) {
								SolrDocument docClasse = searchListClasse.get(0);
								String entiteAttribuerNomCanonique = (String)docClasse.get("classCanonicalName_" + languageName + "_stored_string");

								SolrQuery solrSearchVar = new SolrQuery();   
								solrSearchVar.setQuery("*:*");
								solrSearchVar.setRows(1);
								solrSearchVar.addFilterQuery("classCanonicalName_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerNomCanonique));
								solrSearchVar.addFilterQuery("entityVar_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerVar));
								solrSearchVar.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
								solrSearchVar.addFilterQuery("partEstEntite_indexed_boolean:true");
								QueryResponse searchResponseVar = solrClientComputate.query(solrSearchVar);
								SolrDocumentList searchListVar = searchResponseVar.getResults();

								if(searchListVar.size() > 0) {
									SolrDocument docEntite = searchListClasse.get(0);

									indexStoreSolr(entityDoc, "entiteAttribuer", true);
									indexStoreSolr(entityDoc, "entiteAttribuerNomSimple", languageName, entiteAttribuerNomSimple);
									indexStoreSolr(entityDoc, "entiteAttribuerNomCanonique", languageName, entiteAttribuerNomCanonique);
									indexStoreSolr(entityDoc, "entiteAttribuerVar", languageName, entiteAttribuerVar);

									for(String languageName : otherLanguages) {  
										String entiteAttribuerNomCanoniqueLangue = (String)docClasse.get("classCanonicalName_" + languageName + "_stored_string");
										String entiteAttribuerNomSimpleLangue = (String)docClasse.get("classCanonicalName_" + languageName + "_stored_string");
										String entiteAttribuerVarLangue = (String)docEntite.get("entityVar_" + languageName + "_stored_string");

										indexStoreSolr(entityDoc, "entiteAttribuerNomSimple", languageName, entiteAttribuerNomSimpleLangue);
										indexStoreSolr(entityDoc, "entiteAttribuerNomCanonique", languageName, entiteAttribuerNomCanoniqueLangue);
										indexStoreSolr(entityDoc, "entiteAttribuerVar", languageName, entiteAttribuerVarLangue);
									}
								}
							}
						}

//						boolean entityWrap = false;
//	
//						String varEntiteEnUS = regex("^var.enUS: (.*)", methodQdox.getComment());
//						entite.var.frFR(entityVar);
//						entite.var.enUS(StringUtils.isEmpty(varEntiteEnUS) ? entityVar : StringUtils.substringAfter(varEntiteEnUS, "_"));
//	
//						regexCommentaires(methodQdox.getComment(), entite.comment);
//						regexReplaceAll(methodQdox.getComment(), methodQdox.getSourceCode(), entite.codeSource);
//			
//						if(entityClassQdox.getFullyQualifiedName().equals(canonicalNameCouvertureActuel)) {
//							entiteType = StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteType, "<"), ">");
//							if(StringUtils.contains(entiteType, "<"))
//								entityClassQdox = typeBricoleur.getClassByName(StringUtils.substringBefore(entiteType, "<"));
//							else
//								entityClassQdox = typeBricoleur.getClassByName(entiteType);
//							entityCanonicalName = entityClassQdox.getCanonicalName();
//							entityWrap = true;
//							entite.couverture(true);
//						} 
//						if(entite.clePrimaire)
//							varCleUniqueActuel.tout(entite.var);
//						if(entite.suggere)
//							varSuggereActuel.tout(entite.var);
//	
//						if(!entitesTout.contains(entite))
//							entitesTout.add(entite);
//	
//						tout.add(entite);
//						importationsAjouter(new Chaine().tout(entityCanonicalName));
//						importationsGenAjouter(new Chaine().tout(entityCanonicalName));
//						if(entiteListeTypeGenerique != null) {
//							Chaine importation = new Chaine().tout(entiteListeTypeGenerique);
//							importationsAjouter(importation);
//							importationsGenAjouter(importation);
//						}
//	
//						boolean etendCluster = etendClasse(entityCanonicalNameClusterActuel);
//						entite.etendCluster(etendCluster);
//						if(!etendCluster && entite.canonicalNameGeneric.pasVide()) {
//							boolean listeCluster = etendClasse(canonicalNameClusterActuel, entite.canonicalNameGeneric.toString());
//							entite.listeCluster(listeCluster);
//						}
//	
//						boolean etendPageXml = entite.etendClasse(canonicalNamePageXmlActuel);
//						entite.etendPageXml(etendPageXml);
//						if(!etendPageXml && entite.canonicalNameGeneric.pasVide()) {
//							boolean listePageXml = etendClasse(canonicalNamePageXmlActuel, entite.canonicalNameGeneric.toString());
//							entite.listePageXml(listePageXml);
//						}
//	
//						boolean etendPagePart = entite.etendClasse(canonicalNamePagePartActuel);
//						entite.etendPagePart(etendPagePart);
//						if(!etendPagePart && entite.canonicalNameGeneric.pasVide()) {
//							boolean listePagePart = etendClasse(canonicalNamePagePartActuel, entite.canonicalNameGeneric.toString());
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
						
						
						
						
						
						
						
						
						
						
						
						
						
						for(JavaAnnotation annotation : annotations) {
							String entiteAnnotationLangue = indexStoreSolr(entityDoc, "entiteAnnotations", languageName, annotation.getType().getCanonicalName());
						}
//						if(classReturnQdox != null && !classReturnQdox.getCanonicalName().equals("void")) {
//							entityCanonicalNameRetourComplet = indexerStocker(entityDoc, "entityCanonicalNameRetourComplet", languageName, classReturnQdox.getGenericCanonicalName());
//							entityCanonicalNameRetour = indexerStocker(entityDoc, "entityCanonicalNameRetour", languageName, classReturnQdox.getCanonicalName());
//							String entitySimpleNameRetour = indexerStocker(entityDoc, "entitySimpleNameRetour", languageName, StringUtils.substringAfterLast(entityCanonicalNameRetour, "."));
//							String listeNomTypeOrigineRetourGenerique = entityCanonicalNameRetourComplet;
//							String entityCanonicalNameRetourGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineRetourGenerique, "<"), ">");
//							String entitySimpleNameRetourComplet;
//							String entitySimpleNameRetourGenerique;
//							entityCanonicalNameRetourGenerique = entityCanonicalNameRetourGenerique.contains("<") ? StringUtils.substringBefore(entityCanonicalNameRetourGenerique, "<") : entityCanonicalNameRetourGenerique;
//							entityCanonicalNameRetourGenerique = entityCanonicalNameRetourGenerique.contains(",") ? StringUtils.substringBefore(entityCanonicalNameRetourGenerique, ",") : entityCanonicalNameRetourGenerique;
//							if(StringUtils.isNotEmpty(entityCanonicalNameRetourGenerique)) {
//								indexerStocker(entityDoc, "entityCanonicalNameRetourGenerique", languageName, entityCanonicalNameRetourGenerique);
//	
//								if(StringUtils.contains(entityCanonicalNameRetourGenerique, "."))
//									entitySimpleNameRetourGenerique = indexerStocker(entityDoc, "entitySimpleNameRetourGenerique", languageName, StringUtils.substringAfterLast(entityCanonicalNameRetourGenerique, "."));
//								else
//									entitySimpleNameRetourGenerique = indexerStocker(entityDoc, "entitySimpleNameRetourGenerique", languageName, entityCanonicalNameRetourGenerique);
//	
//								if(StringUtils.contains(entitySimpleNameRetourGenerique, ".")) {
//									entitySimpleNameRetourComplet = indexerStocker(entityDoc, "entitySimpleNameRetourComplet", languageName, concat(StringUtils.substringAfterLast(entitySimpleNameRetour, "."), "<", entitySimpleNameRetourGenerique, ">"));
//								}
//								else {
//									entitySimpleNameRetourComplet = indexerStocker(entityDoc, "entitySimpleNameRetourComplet", languageName, concat(entitySimpleNameRetour, "<", entitySimpleNameRetourGenerique, ">"));
//								}
//							}
//							else {
//								entitySimpleNameRetourComplet = indexerStocker(entityDoc, "entityCanonicalNameRetourComplet", languageName, entitySimpleNameRetour);
//							}
//						}
	
						String entiteCle = classAbsolutePath + "." + entityVar;
		
						// Entites Solr du entite. 
		
						entityDoc.addField("key", entiteCle);
						indexStoreSolr(entityDoc, "partEstEntite", true);
						indexStoreSolr(entityDoc, "partNumber", partNumber);

						String entiteBlocCode = methodQdox.getCodeBlock();

						/////////////////////////
						// entiteTypeVertxJson //
						/////////////////////////
						String entitySimpleNameVertxJson = null;
						String entityCanonicalNameVertxJson = null;
						String entiteListeNomSimpleVertxJson = null;
						String entiteListeNomCanoniqueVertxJson = null;
						if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBoolean)) {
							entitySimpleNameVertxJson = "Boolean";
							entityCanonicalNameVertxJson = VAL_canonicalNameBoolean;
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameDate)) {
							entitySimpleNameVertxJson = "Instant";
							entityCanonicalNameVertxJson = VAL_canonicalNameInstant;
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneId", languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.LocalDateTime", languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, VAL_canonicalNameDate, languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", languageName));
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLocalDate)) {
							entitySimpleNameVertxJson = "Instant";
							entityCanonicalNameVertxJson = VAL_canonicalNameInstant;
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneId", languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.LocalDate", languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, VAL_canonicalNameDate, languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", languageName));
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLong)) {
							entitySimpleNameVertxJson = "Long";
							entityCanonicalNameVertxJson = VAL_canonicalNameLong;
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBigDecimal)) {
							entitySimpleNameVertxJson = "Double";
							entityCanonicalNameVertxJson = VAL_canonicalNameLong;
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameDouble)) {
							entitySimpleNameVertxJson = "Double";
							entityCanonicalNameVertxJson = VAL_canonicalNameDouble;
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameFloat)) {
							entitySimpleNameVertxJson = "Float";
							entityCanonicalNameVertxJson = VAL_canonicalNameFloat;
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameInteger)) {
							entitySimpleNameVertxJson = "Integer";
							entityCanonicalNameVertxJson = VAL_canonicalNameInteger;
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameList, VAL_canonicalNameArrayList)) {
							if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameBoolean)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Boolean";
								entiteListeNomCanoniqueVertxJson = VAL_canonicalNameBoolean;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameDate)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Instant";
								entiteListeNomCanoniqueVertxJson = VAL_canonicalNameInstant;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameLocalDate)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Instant";
								entiteListeNomCanoniqueVertxJson = VAL_canonicalNameInstant;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameLong)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Long";
								entiteListeNomCanoniqueVertxJson = VAL_canonicalNameLong;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameBigDecimal)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Long";
								entiteListeNomCanoniqueVertxJson = VAL_canonicalNameLong;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameDouble)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Double";
								entiteListeNomCanoniqueVertxJson = VAL_canonicalNameDouble;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameFloat)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Float";
								entiteListeNomCanoniqueVertxJson = VAL_canonicalNameFloat;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameInteger)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Integer";
								entiteListeNomCanoniqueVertxJson = VAL_canonicalNameInteger;
							}
							storeSolr(entityDoc, "entiteListeNomSimpleVertxJson", entiteListeNomSimpleVertxJson);
							storeSolr(entityDoc, "entiteListeNomCanoniqueVertxJson", entiteListeNomCanoniqueVertxJson);
							classPartsGenAdd(ClassParts.initClassParts(this, entiteListeNomCanoniqueVertxJson, languageName));
						}
						else if(classPartsChain != null && StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameString, classPartsChain.canonicalName)) {
							entitySimpleNameVertxJson = "String";
							entityCanonicalNameVertxJson = VAL_canonicalNameString;
						}
						classPartsGenAdd(ClassParts.initClassParts(this, entityCanonicalNameVertxJson, languageName));
						storeSolr(entityDoc, "entitySimpleNameVertxJson", entitySimpleNameVertxJson);
						storeSolr(entityDoc, "entityCanonicalNameVertxJson", entityCanonicalNameVertxJson);

						////////////////////
						// entiteSolrNomCanonique //
						////////////////////
						String entiteSolrNomCanonique = null;
						String entiteSolrNomSimple = null;
						String entiteSuffixeType = null;
						if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBoolean)) {
							entiteSolrNomCanonique = VAL_canonicalNameBoolean;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_boolean";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameLocalDate, VAL_canonicalNameDate)) {
							entiteSolrNomCanonique = VAL_canonicalNameDate;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_date";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLong)) {
							entiteSolrNomCanonique = VAL_canonicalNameLong;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_long";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBigDecimal)) {
							entiteSolrNomCanonique = VAL_canonicalNameDouble;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_double";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameDouble)) {
							entiteSolrNomCanonique = VAL_canonicalNameDouble;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_double";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameFloat)) {
							entiteSolrNomCanonique = VAL_canonicalNameFloat;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_float";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameInteger)) {
							entiteSolrNomCanonique = VAL_canonicalNameInteger;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_int";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameList, VAL_canonicalNameArrayList)) {
							if(entityCanonicalNameGeneric.equals(VAL_canonicalNameBoolean)) {
								entiteSolrNomCanonique = VAL_canonicalNameList + "<" + VAL_canonicalNameBoolean + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameBoolean, ".") + ">";
								entiteSuffixeType = "_booleans";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameLocalDate)) {
								entiteSolrNomCanonique = VAL_canonicalNameList + "<" + VAL_canonicalNameDate + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameDate, ".") + ">";
								entiteSuffixeType = "_dates";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameLong)) {
								entiteSolrNomCanonique = VAL_canonicalNameList + "<" + VAL_canonicalNameLong + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameLong, ".") + ">";
								entiteSuffixeType = "_longs";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameBigDecimal)) {
								entiteSolrNomCanonique = VAL_canonicalNameList + "<" + VAL_canonicalNameBigDecimal + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameBigDecimal, ".") + ">";
								entiteSuffixeType = "_doubles";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameDouble)) {
								entiteSolrNomCanonique = VAL_canonicalNameList + "<" + VAL_canonicalNameDouble + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameDouble, ".") + ">";
								entiteSuffixeType = "_doubles";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameFloat)) {
								entiteSolrNomCanonique = VAL_canonicalNameList + "<" + VAL_canonicalNameFloat + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameFloat, ".") + ">";
								entiteSuffixeType = "_floats";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameInteger)) {
								entiteSolrNomCanonique = VAL_canonicalNameList + "<" + VAL_canonicalNameInteger + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameInteger, ".") + ">";
								entiteSuffixeType = "_ints";
							}
							else if(classPartsChain != null && StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameString, classPartsChain.canonicalName)) {
								entiteSolrNomCanonique = VAL_canonicalNameList + "<" + VAL_canonicalNameString + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameString, ".") + ">";
								entiteSuffixeType = "_strings";
							}
						}
						else if(classPartsChain != null && StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameString, classPartsChain.canonicalName)) {
							entiteSolrNomCanonique = VAL_canonicalNameString;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_string";
////								if(videDernier)
////									suffixeType += "_videDernier";
						}
						storeSolr(entityDoc, "entiteSolrNomCanonique", entiteSolrNomCanonique);
						storeSolr(entityDoc, "entiteSolrNomSimple", entiteSolrNomSimple);

						////////////////////
						// entiteTypeJson //
						////////////////////
						String entiteTypeJson = null;
						String entiteFormatJson = null;
						String entiteListeTypeJson = null;
						if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBoolean)) {
							entiteTypeJson = "boolean";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameDate)) {
							entiteTypeJson = "string";
							entiteFormatJson = "date-time";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLocalDate)) {
							entiteTypeJson = "string";
							entiteFormatJson = "date";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLong)) {
							entiteTypeJson = "number";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBigDecimal)) {
							entiteTypeJson = "number";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameDouble)) {
							entiteTypeJson = "number";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameFloat)) {
							entiteTypeJson = "number";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameInteger)) {
							entiteTypeJson = "number";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameList, VAL_canonicalNameArrayList)) {
							if(entityCanonicalNameGeneric.equals(VAL_canonicalNameBoolean)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "boolean";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameLocalDate)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameLong)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "number";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameBigDecimal)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "number";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameDouble)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "number";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameFloat)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "number";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameInteger)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "number";
							}
							else if(classPartsChain != null && StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameString, classPartsChain.canonicalName)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							}
							storeSolr(entityDoc, "entiteListeTypeJson", entiteListeTypeJson);
						}
						else if(classPartsChain != null && StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameString, classPartsChain.canonicalName)) {
							entiteTypeJson = "string";
						}
						storeSolr(entityDoc, "entiteTypeJson", entiteTypeJson);
						if(entiteFormatJson != null)
							storeSolr(entityDoc, "entiteFormatJson", entiteFormatJson);
//						
//						if(entityUniqueKey)
//							storeSolr(entityDoc, "entityVarCleUnique", entityVar);
//						if(entitySuggested)
//							storeSolr(entityDoc, "entityVarSuggere", entityVar + "_suggere");
//						if(entityIncremented)
//							storeSolr(entityDoc, "entityVarIncremente", entityVar + "_incremente");
//						if(entityEncrypted)
//							storeSolr(entityDoc, "entityVarCrypte", entityVar + "_crypte");
//						if(entityIndexed)
//							storeSolr(entityDoc, "entityVarIndexe", entityVar + "_indexe" + entiteSuffixeType);
//						if(entityStored)
//							storeSolr(entityDoc, "entityVarStocke", entityVar + "_stocke" + entiteSuffixeType);

						if(entityUniqueKey) {
							storeSolr(classDoc, "classeVarCleUnique", languageName, entityVar);
						}

						for(String languageName : otherLanguages) {  
							String entityVarLangue = regex("^var\\." + languageName + ": (.*)", methodComment);
							entityVarLangue = indexStoreSolr(entityDoc, "entityVar", languageName, entityVarLangue == null ? entityVar : entityVarLangue);
							if(entityUniqueKey) {
								storeSolr(classDoc, "classeVarCleUnique", languageName, entityVarLangue);
							}
//		
//							List<String> entiteCommentairesLangue = regexList("(.*)", methodComment);
//							String entiteCommentaireLangue = indexStoreSolr(entityDoc, "entiteCommentaire", languageName, StringUtils.join(entiteCommentairesLangue, "\n"));
	
							String entiteBlocCodeLangue = entiteBlocCode;
							ArrayList<String> replaceKeysLanguage = regexList("^r." + languageName + "\\s*=\\s*(.*)\\n.*", methodComment);
							ArrayList<String> replaceValuesLanguage = regexList("^r." + languageName + "\\s*=\\s*.*\\n(.*)", methodComment);
							for(int i = 0; i < replaceKeysLanguage.size(); i++) {
								String cle = replaceKeysLanguage.get(i);
								String valeur = replaceValuesLanguage.get(i);
								StringUtils.replace(entiteBlocCodeLangue, cle, valeur);
							}
							storeSolr(entityDoc, "entiteBlocCode", languageName, entiteBlocCodeLangue); 
	
							storeRegexComments(methodComment, languageName, entityDoc, "entiteCommentaire");
						}

						for(JavaClass methodExceptionQdox : methodExceptionsQdox) { 
							String methodExceptionSimpleNameComplete = StringUtils.substringAfterLast(methodExceptionQdox.getCanonicalName(), ".");
							storeListSolr(entityDoc, "methodeExceptionsNomSimpleComplet", methodExceptionSimpleNameComplete);
							for(String languageName : otherLanguages) {  
								ClassParts methodeExceptionClassPartsLangue = ClassParts.initClassParts(this, methodExceptionQdox.getCanonicalName(), languageName);
								storeListSolr(entityDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionClassPartsLangue.simpleNameComplete);
							}
						}

						solrClientComputate.add(entityDoc); 
					}
					else { 
						// est Methode. 
						
						SolrInputDocument methodDoc = classDocClone.deepCopy();
						indexStoreSolr(methodDoc, "methodVar", languageName, methodVar);
						for(Integer methodParamNum = 1; methodParamNum <= methodParamsQdox.size(); methodParamNum++) {
							JavaParameter methodParamQdox = methodParamsQdox.get(methodParamNum - 1);
							String methodeParamVar = methodParamQdox.getName();
							storeListSolr(methodDoc, "methodParamsVar", languageName, methodeParamVar);
							ClassParts methodeParamsClassePart = ClassParts.initClassParts(this, methodParamQdox.getJavaClass(), languageName);
							storeListSolr(methodDoc, "methodParamCanonicalNames", languageName, methodeParamsClassePart.canonicalName);
							storeListSolr(methodDoc, "methodParamsSimpleNameComplete", languageName, methodeParamsClassePart.simpleNameComplete);
							storeListSolr(methodDoc, "methodeParamsArgsVariables", methodParamQdox.isVarArgs());
							for(String languageName : otherLanguages) { 
								String methodParamVarLanguage = regex("param" + methodParamNum + "\\.var\\." + languageName + ": (.*)", methodComment);
								if(methodParamVarLanguage == null)
									methodParamVarLanguage = methodeParamVar;
								ClassParts methodParamClassPartsLanguage = ClassParts.initClassParts(this, methodeParamsClassePart, languageName);

								storeListSolr(methodDoc, "methodParamCanonicalNames", languageName, methodParamClassPartsLanguage.canonicalName);
								storeListSolr(methodDoc, "methodParamsSimpleNameComplete", languageName, methodParamClassPartsLanguage.simpleNameComplete);
								storeListSolr(methodDoc, "methodParamsVar", languageName, methodParamVarLanguage);
							}  
						}

						List<JavaTypeVariable<JavaGenericDeclaration>> methodParamsType = methodQdox.getTypeParameters();
						for(JavaTypeVariable<JavaGenericDeclaration> methodParamType : methodParamsType) {
							String methodParamTypeNom = methodParamType.getName();
							storeListSolr(methodDoc, "methodParamsTypeName", methodParamTypeNom);
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
							storeListSolr(methodDoc, "methodeExceptionsNomSimpleComplet", methodExceptionSimpleNameComplete);
							for(String languageName : otherLanguages) {  
								ClassParts methodeExceptionClassPartsLangue = ClassParts.initClassParts(this, methodExceptionQdox.getCanonicalName(), languageName);
								storeListSolr(methodDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionClassPartsLangue.simpleNameComplete);
							}
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
		
		ClassParts classePartsCouverture = classePartsCouverture(domainPackageName);
		classPartsGenAdd(classePartsCouverture);

		for(ClassParts classePartGen : classePartsGen.values()) {
			indexStoreListSolr(classDoc, "classImportsGen", languageName, classePartGen.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classePartGen, languageName);
				indexStoreListSolr(classDoc, "classImportsGen", languageName, classImportClassPartsLanguage.canonicalName);
			}
		}

		for(ClassParts classePartGenApi : classePartsGenApi.values()) {
			indexStoreListSolr(classDoc, "classImportsGenApi", languageName, classePartGenApi.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classePartGenApi, languageName);
				indexStoreListSolr(classDoc, "classImportsGenApi", languageName, classImportClassPartsLanguage.canonicalName);
			}
		}

		for(ClassParts classePartGenPage : classePartsGenPage.values()) {
			indexStoreListSolr(classDoc, "classImportsGenPage", languageName, classePartGenPage.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classePartGenPage, languageName);
				indexStoreListSolr(classDoc, "classImportsGenPage", languageName, classImportClassPartsLanguage.canonicalName);
			}
		}

		solrClientComputate.add(classDoc);
		solrClientComputate.commit();
		String qDelete = concat("classAbsolutePath_indexed_string", ":\"", classPath, "\" AND (modified_indexed_date:[* TO ", modified.toString(), "-1MILLI] OR (*:* NOT modified_indexed_date:*))");
		solrClientComputate.deleteByQuery(qDelete);
		solrClientComputate.commit(); 
		return classDoc;
	}
}
