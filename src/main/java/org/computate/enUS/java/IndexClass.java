package org.computate.enUS.java;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.text.WordUtils;
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

	public static final String VAL_canonicalNameZonedDateTime = ZonedDateTime.class.getCanonicalName();

	public static final String VAL_canonicalNameLocalDate = LocalDate.class.getCanonicalName();

	public static final String VAL_canonicalNameList = List.class.getCanonicalName();

	public static final String VAL_canonicalNameArrayList = ArrayList.class.getCanonicalName();

	public static final String VAL_canonicalNameSet = Set.class.getCanonicalName();

	public static final String VAL_canonicalNameHashSet = HashSet.class.getCanonicalName();

	public static final String VAL_canonicalNameInstant = Instant.class.getCanonicalName();

	public static final String VAL_canonicalNameVertxJsonArray = "io.vertx.core.json.JsonArray";

	public static final String VAL_canonicalNameVertxJsonObject = "io.vertx.core.json.JsonObject";

	ClassParts classPartsSolrInputDocument;

	ClassParts classPartsSolrDocument;

	ClassParts classPartsSolrClient;

	ClassParts classPartsTest;

	ClassParts classPartsList;

	ClassParts classPartsArrayList;

	ClassParts classPartsHashSet;

	ClassParts classPartsSiteContext;

	ClassParts classPartsSiteConfig;

	ClassParts classPartsSiteUser;

	ClassParts classPartsCluster;

	ClassParts classPartsSearchResult;

	ClassParts classPartsAllWriter;

	ClassParts classPartsWrap;

	ClassParts classPartsSearchList;

	ClassParts classPartsPageLayout;

	String CONTEXT_AMale = "an ";

	String CONTEXT_AFemale = "an ";

	String CONTEXT_ThisMaleVowel = "this ";

	String CONTEXT_ThisFemaleVowel = "this ";

	String CONTEXT_ThisMaleConsonant = "this ";

	String CONTEXT_ThisFemaleConsonant = "this ";

	String CONTEXT_ThesePlural = "this ";

	String CONTEXT_CreatedMale = "this ";

	String CONTEXT_CreatedFemale = "this ";

	String CONTEXT_ModifiedMale = "this ";

	String CONTEXT_ModifiedFemale = "this ";

	String CONTEXT_TheMaleVowel = "the ";

	String CONTEXT_TheFemaleVowel = "the ";

	String CONTEXT_TheMaleConsonant = "the ";

	String CONTEXT_TheFemaleConsonant = "the ";

	String CONTEXT_ThePlural = "the ";

	String CONTEXT_CurrentMaleBefore = "current ";

	String CONTEXT_CurrentFemaleBefore = "current ";

	String CONTEXT_CurrentMaleAfter = "";

	String CONTEXT_CurrentFemaleAfter = "";

	String CONTEXT_AllMalePlural = "all ";

	String CONTEXT_AllFemalePlural = "all ";

	String CONTEXT_NoneFoundMaleBefore = "no ";

	String CONTEXT_NoneFemaleBefore = "no ";

	String CONTEXT_NoneFoundMaleAfter = " found";

	String CONTEXT_NoneFemaleAfter = " found";

	String CONTEXT_OfVowel = "of ";

	String CONTEXT_OfConsonant = "of ";

	String CONTEXT_AdjectivePlural = "";

	Boolean CONTEXT_AdjectiveBefore = true;

	protected HashMap<String, ClassParts> classPartsGenApi = new HashMap<String, ClassParts>();

	protected HashMap<String, ClassParts> classPartsGenPage = new HashMap<String, ClassParts>();

	private String contextAName;

	private String contextThis;

	private String contextThisName;

	private String contextA;

	private String contextCreated;

	private String contextModified;

	private String contextTheName;

	private String contextNameSingular;

	private String contextNamePlural;

	private String contextActualName;

	private String contextAll;

	private String contextAllName;

	private String contextTheNames;

	private String contextNoneNameFound;

	private String contextNameVar;

	private String contextOfName;

	private String contextAdjective;

	private String contextAdjectivePlural;

	private String contextAdjectiveVar;

	private String contextANameAdjective;

	private String contextNameAdjectiveSingular;

	private String contextNameAdjectivePlural;

	private String contextTitle;

	private String contextH1;

	private String contextH2;

	private String contextH3;

	private String contextColor;

	private String contextIconGroup;

	private String contextIconName;

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
		doc.addField(concat(fieldName, "_indexed_booleans"), fieldValue);
		return fieldValue;
	}

	protected String indexListSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
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

	protected Double indexStoreSolr(SolrInputDocument doc, String fieldName, Double fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_double"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_double"), fieldValue);
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

	protected String indexStoreSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_string"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected String indexStoreSolrRegex(SolrInputDocument doc, String languageName, String fieldName, String fieldNameRegex, String comment) throws Exception, Exception {
		return indexStoreSolrRegex(doc, languageName, fieldName, fieldNameRegex, comment, null);
	}

	protected String indexStoreSolrRegex(SolrInputDocument doc, String languageName, String fieldName, String fieldNameRegex, String comment, String defaultValue) throws Exception, Exception {
		String fieldValue = null;
		if(fieldNameRegex != null && comment != null) {
			Matcher m = Pattern.compile("^" + fieldNameRegex + "(." + languageName + ")?:\\s*(.*)", Pattern.MULTILINE).matcher(comment);
			if(m.find()) {
				fieldValue = m.group(2);
			}
		}
		if(StringUtils.isBlank(fieldValue)) {
			fieldValue = defaultValue;
		}
		if(StringUtils.isNotBlank(fieldValue)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
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

	protected String indexStoreListSolr(SolrInputDocument doc, String fieldName, String languageName, String fieldValue) throws Exception, Exception {
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

	protected ClassParts classPartsAllWriter(String domainPackageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "AllWriter");
	}

	protected ClassParts classPartsSearchList(String domainPackageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SearchList");
	}

	protected ClassParts classPartsPageLayout(String domainPackageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "PageLayout");
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
		if(classPartsGen != null && classParts != null && !classPartsGen.containsKey(classParts.canonicalName) && StringUtils.contains(classParts.canonicalName, ".") && !StringUtils.contains(classParts.canonicalName, ","))
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

	public SolrInputDocument indexClass(String classAbsolutePath, SolrInputDocument classDoc) throws Exception, Exception { 

		String classCanonicalName = StringUtils.replace(StringUtils.substringAfter(StringUtils.substringBeforeLast(classAbsolutePath, "."), srcMainJavaPath + "/"), "/", ".");
		String classSimpleName = StringUtils.substringAfterLast(classCanonicalName, ".");
		String classCanonicalNameGen = classCanonicalName + "Gen";
		String classSimpleNameGen = classSimpleName + "Gen";

		indexStoreSolr(classDoc, "appPath", appPath);
		indexStoreSolr(classDoc, "appName", appName);
		JavaClass classQdox = builder.getClassByName(classCanonicalName.toString());
		JavaClass classSuperQdox = classQdox.getSuperJavaClass();
		JavaClass classQdoxString = builder.getClassByName(String.class.getCanonicalName());
		Boolean classKeywordsFound = false;
		List<String> classKeywords = new ArrayList<String>();
		List<String> classInitDeepExceptions = new ArrayList<String>(); 
		String classVarPrimaryKey = null;
		String classVarUniqueKey = null;

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
//		classQdox.getSuperClass().getGenericCanonicalName(); // returns WrapGen<DEV>
		
		String classSuperCompleteName = Object.class.getCanonicalName();
		try {
			classSuperCompleteName = indexStoreSolr(classDoc, "classSuperCompleteName", languageName, classSuperQdox.getGenericCanonicalName());
		} catch (Exception e) {
			if(classSuperQdox.getGenericFullyQualifiedName().contains("<"))
				classSuperCompleteName = indexStoreSolr(classDoc, "classSuperCompleteName", languageName, classSuperQdox.getGenericFullyQualifiedName());
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

				ClassParts classPartsBase = ClassParts.initClassParts(this, classSuperCanonicalNameGeneric, languageName);
				baseClassExtendsGen = classPartsBase.extendsGen;
				if(baseClassExtendsGen == null)
					baseClassExtendsGen = false;
			}
		}
		Boolean classIsBase = storeSolr(classDoc, "classIsBase", !baseClassExtendsGen || StringUtils.isEmpty(classSuperCompleteNameGeneric) || StringUtils.equals(classSuperCompleteNameGeneric, "java.lang.Object"));
		Boolean classExtendsBase = storeSolr(classDoc, "classExtendsBase", !classIsBase && baseClassExtendsGen && !StringUtils.equals(classSuperCompleteNameGeneric, "java.lang.Object"));
		indexStoreSolr(classDoc, "baseClassExtendsGen", baseClassExtendsGen);
		Boolean classContainsSiteRequest = false;
		try {
			indexStoreSolr(classDoc, "classContainsSiteRequest", classQdox.getMethodBySignature("getRequestSite", new ArrayList<JavaType>(), true) != null);
		} catch (Exception e) {
			// TODO ctate fix this to pull from solr. 
		}
		
		String classComment = storeRegexComments(classQdox.getComment(), languageName, classDoc, "classComment");
		String classPackageName = StringUtils.substringBeforeLast(classCanonicalName, ".");
		String classPath = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), ".java");
		String classDirPath = StringUtils.substringBeforeLast(classPath, "/");
		String classPathGen = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), "Gen.java");

		String classDirPathGen = StringUtils.substringBeforeLast(classPathGen, "/");
		String classKey = classAbsolutePath;
		Instant modified = Instant.now();
		Date modifiedDate = Date.from(modified);
		Boolean classContainsWrap = false;

		ClassParts classPartsChain = classPartsChain(domainPackageName);
		Boolean classExtendsGen = StringUtils.endsWith(classSuperSimpleName, "Gen");
		ClassParts classPartsSiteRequest = classPartsSiteRequest(domainPackageName);
		if(superClassError || !classExtendsGen && regexFound("^(class)?Gen:\\s*(true)$", classComment)) {
			classExtendsGen = true;
		}

		for(String languageName : otherLanguages) {
			String appPathLanguage = appPaths.get(languageName);
			storeRegexComments(classComment, languageName, classDoc, "classComment");
			String srcMainJavaPathLanguage = appPathLanguage + "/src/main/java";
			String srcGenJavaPathLanguage = appPathLanguage + "/src/gen/java";
			String classCanonicalNameLanguage = regex("^(class)?NomCanonique\\." + languageName + ":\\s*(.*)", classComment, classCanonicalName);

			String classSimpleNameLanguage = StringUtils.substringAfterLast(classCanonicalNameLanguage, ".");
			String classPackageNameLanguage = StringUtils.substringBeforeLast(classCanonicalNameLanguage, ".");
			String classCanonicalNameGenLanguage = classCanonicalNameLanguage + "Gen";
			String classSimpleNameGenLanguage = classSimpleNameLanguage + "Gen";
			String classPathLanguage = indexStoreSolr(classDoc, "classPath", languageName, concat(srcMainJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameLanguage, ".", "/"), ".java"));
			String classDirPathLanguage = storeSolr(classDoc, "classDirPath", languageName, StringUtils.substringBeforeLast(classPathLanguage, "/"));
			String classPathGenLanguage = indexStoreSolr(classDoc, "classPathGen", languageName, concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameGenLanguage, ".", "/"), ".java"));
			String classDirPathGenLanguage = storeSolr(classDoc, "classDirPathGen", languageName, StringUtils.substringBeforeLast(classPathGenLanguage, "/"));

			indexStoreSolr(classDoc, "classCanonicalName", languageName, classCanonicalNameLanguage); 
			indexStoreSolr(classDoc, "classSimpleName", languageName, classSimpleNameLanguage); 
			indexStoreSolr(classDoc, "classCanonicalNameGen", languageName, classCanonicalNameGenLanguage); 
			indexStoreSolr(classDoc, "classSimpleNameGen", languageName, classSimpleNameGenLanguage); 
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
		}

		Boolean classInitDeep = !regexFound("^(class)?InitLoin:\\s*(false)$", classComment);
		if(classInitDeep)
			classInitDeep = classExtendsBase || classIsBase;
		classInitDeep = storeSolr(classDoc, "classInitDeep", classInitDeep);
		if(classInitDeep)
			classPartsGenAdd(classPartsSiteRequest);
		indexStoreSolr(classDoc, "classExtendsGen", classExtendsGen);

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
		indexStoreSolr(classDoc, "classPathGen", languageName, classPathGen); 
		indexStoreSolr(classDoc, "classDirPathGen", languageName, classDirPathGen); 
		indexStoreSolr(classDoc, "domainPackageName", domainPackageName); 

		ArrayList<JavaClass> qdoxSuperClasses = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesAndMe = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesAndMeWithoutGen = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesAndInterfaces = new ArrayList<JavaClass>();
		ArrayList<JavaClass> qdoxSuperClassesInterfacesAndMe = new ArrayList<JavaClass>();
		populateQdoxSuperClassesInterfacesAndMe(classQdox, qdoxSuperClasses, qdoxSuperClassesAndMe, qdoxSuperClassesAndMeWithoutGen, qdoxSuperClassesAndInterfaces, qdoxSuperClassesInterfacesAndMe);

		for(JavaClass c : qdoxSuperClassesAndMeWithoutGen) {
			indexStoreListSolr(classDoc, "entitySuperClassesAndMeWithoutGen", c.getCanonicalName()); 

		}

		SolrInputDocument classDocClone = classDoc.deepCopy();
		Integer partNumber = 1;

		Boolean classModel = indexStoreSolr(classDoc, "classModel", regexFound("^(class)?Model: \\s*(true)$", classComment));
		Boolean classApi = indexStoreSolr(classDoc, "classApi", regexFound("^(class)?Api: \\s*(true)$", classComment) || classModel);
		Boolean classPage = regexFound("^(class)?Page: \\s*(true)$", classComment);
		Boolean classSaved = indexStoreSolr(classDoc, "classSaved", regexFound("^(class)?Saved:\\s*(true)$", classComment) || classModel);
		ArrayList<String> classApiMethods = regexList("^(class)?ApiMethode:\\s*(.*)", classComment);

		for(String siteEcrireMethode : siteWriteMethods) {
			if(classQdox.getMethodBySignature(siteEcrireMethode, new ArrayList<JavaType>()) != null
					|| classQdox.getMethodBySignature(siteEcrireMethode + classSimpleName, new ArrayList<JavaType>()) != null)
				indexStoreListSolr(classDoc, "classWriteMethods",  siteEcrireMethode);
		}

		String classSimpleNameApiPackageInfo;
		String classSimpleNameGenApiServiceImpl;
		String classSimpleNameApiServiceImpl;
		String classSimpleNameGenApiService;

		String classCanonicalNameApiPackageInfo;
		String classCanonicalNameGenApiServiceImpl;
		String classCanonicalNameApiServiceImpl;
		String classCanonicalNameGenApiService;

		String classCanonicalNamePageGen = classCanonicalName + "PageGen";
		String classSimpleNamePage = indexStoreSolr(classDoc, "classSimpleNamePage", languageName, classSimpleName + "Page");
		String classSimpleNameGenPage = indexStoreSolr(classDoc, "classSimpleNameGenPage", languageName, classSimpleName + "PageGen");

		String classPathApiPackageInfo;
		String classPathGenApiServiceImpl;
		String classPathApiServiceImpl;
		String classPathGenApiService;

		if(classApi) {
			classSimpleNameApiPackageInfo = indexStoreSolr(classDoc, "classSimpleNameApiPackageInfo", languageName, "package-info");
			classSimpleNameGenApiServiceImpl = indexStoreSolr(classDoc, "classSimpleNameGenApiServiceImpl", languageName, classSimpleName + "GenApiServiceImpl");
			classSimpleNameApiServiceImpl = indexStoreSolr(classDoc, "classSimpleNameApiServiceImpl", languageName, classSimpleName + "ApiServiceImpl");
			classSimpleNameGenApiService = indexStoreSolr(classDoc, "classSimpleNameGenApiService", languageName, classSimpleName + "GenApiService");

			classCanonicalNameApiPackageInfo = indexStoreSolr(classDoc, "classCanonicalNameApiPackageInfo", languageName, classPackageName + "." + classSimpleNameApiPackageInfo);
			classCanonicalNameGenApiServiceImpl = indexStoreSolr(classDoc, "classCanonicalNameGenApiServiceImpl", languageName, classPackageName + "." + classSimpleNameGenApiServiceImpl);
			classCanonicalNameApiServiceImpl = indexStoreSolr(classDoc, "classCanonicalNameApiServiceImpl", languageName, classPackageName + "." + classSimpleNameApiServiceImpl);
			classCanonicalNameGenApiService = indexStoreSolr(classDoc, "classCanonicalNameGenApiService", languageName, classPackageName + "." + classSimpleNameGenApiService);

			classPathApiPackageInfo = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalNameApiPackageInfo, ".", "/"), ".java");
			classPathGenApiServiceImpl = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalNameGenApiServiceImpl, ".", "/"), ".java");
			classPathApiServiceImpl = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalNameApiServiceImpl, ".", "/"), ".java");
			classPathGenApiService = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalNameGenApiService, ".", "/"), ".java");

			indexStoreSolr(classDoc, "classPathApiPackageInfo", languageName, classPathApiPackageInfo); 
			indexStoreSolr(classDoc, "classPathGenApiServiceImpl", languageName, classPathGenApiServiceImpl); 
			indexStoreSolr(classDoc, "classPathApiServiceImpl", languageName, classPathApiServiceImpl); 
			indexStoreSolr(classDoc, "classPathGenApiService", languageName, classPathGenApiService); 
		}

		for(String languageName : otherLanguages) {
			String appPathLanguage = appPaths.get(languageName);
			String srcMainJavaPathLanguage = appPathLanguage + "/src/main/java";
			String srcGenJavaPathLanguage = appPathLanguage + "/src/gen/java";
			String classCanonicalNameLanguage = regex("^(class)?NomCanonique\\." + languageName + ":\\s*(.*)", classComment, classCanonicalName);
			String classSimpleNameLanguage = StringUtils.substringAfterLast(classCanonicalNameLanguage, ".");
//			String classPackageNameLanguage = StringUtils.substringBeforeLast(classCanonicalNameLanguage, ".");
//			String classCanonicalNameGenLanguage = classCanonicalNameLanguage + "Gen";
//			String classSimpleNameGenLanguage = classSimpleNameLanguage + "Gen";

			String classSimpleNameApiLangue = classSimpleNameLanguage + "Api";
			String classSimpleNamePageLanguage = classSimpleNameLanguage + "Page";
			String classSimpleNameGenApiServiceImplLangue = classSimpleNameLanguage + "ApiGen";
			String classSimpleNameGenPageLangue = classSimpleNameLanguage + "PageGen";
			String classCanonicalNameApiLanguage = classCanonicalNameLanguage + "Api";
			String classCanonicalNameApiGenLanguage = classCanonicalNameLanguage + "ApiGen";
			String classCanonicalNamePageLanguage = classCanonicalNameLanguage + "Page";
			String classCanonicalNamePageGenLanguage = classCanonicalNameLanguage + "PageGen";
			String classPathApiGenLangue = indexStoreSolr(classDoc, "classPathApiGen", languageName, concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameApiGenLanguage, ".", "/"), ".java"));
			String classPathGenPageLangue = indexStoreSolr(classDoc, "classPathGenPage", languageName, concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNamePageGenLanguage, ".", "/"), ".java"));
			indexStoreSolr(classDoc, "classSimpleNameApi", languageName, classSimpleNameApiLangue); 
			indexStoreSolr(classDoc, "classSimpleNamePage", languageName, classSimpleNamePageLanguage); 
			indexStoreSolr(classDoc, "classSimpleNameGenApiServiceImpl", languageName, classSimpleNameGenApiServiceImplLangue); 
			indexStoreSolr(classDoc, "classSimpleNameGenPage", languageName, classSimpleNameGenPageLangue); 
		}

		classPartsSolrInputDocument = ClassParts.initClassParts(this, "org.apache.solr.common.SolrInputDocument", languageName);
		classPartsSolrDocument = ClassParts.initClassParts(this, "org.apache.solr.common.SolrDocument", languageName);
		classPartsSolrClient = ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrClient", languageName);
		classPartsTest = ClassParts.initClassParts(this, "org.junit.Test", languageName);
		classPartsList = ClassParts.initClassParts(this, List.class.getCanonicalName(), languageName);
		classPartsArrayList = ClassParts.initClassParts(this, ArrayList.class.getCanonicalName(), languageName);
		classPartsSiteContext = classPartsSiteContext(domainPackageName);
		classPartsSiteConfig = classPartsSiteConfig(domainPackageName);
		classPartsSiteUser = classPartsSiteUser(domainPackageName);
		classPartsCluster = classPartsCluster(domainPackageName);
		classPartsSearchResult = classPartsSearchResult(domainPackageName);
		classPartsAllWriter = classPartsAllWriter(domainPackageName);
		classPartsSearchList = classPartsSearchList(domainPackageName);
		classPartsWrap = classPartsWrap(domainPackageName);
		classPartsPageLayout = classPartsPageLayout(domainPackageName);

		if(classApi) {
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
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Future", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.http.CaseInsensitiveHeaders", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.AsyncResult", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Handler", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.buffer.Buffer", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.OperationResponse", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.CompositeFuture", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.http.client.utils.URLEncodedUtils", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.nio.charset.Charset", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.http.NameValuePair", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.OperationRequest", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLConnection", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Optional", languageName));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.stream.Stream", languageName));
			classPartsGenApiAdd(classPartsSearchList);
		}
		if(classExtendsBase || classIsBase) {
			classPartsGenAdd(classPartsCluster);
			classPartsGenAdd(classPartsAllWriter);
		}
		if(classSaved) {
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
		classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.StringUtils", languageName));
		classPartsGenAdd(ClassParts.initClassParts(this, "java.util.Objects", languageName));

		if(classComment != null) {

			Matcher classValsSearch = Pattern.compile("^(class)?Val\\.(\\w+)\\.(\\w+):(.*)", Pattern.MULTILINE).matcher(classComment);
			boolean classValsFound = classValsSearch.find();
			while(classValsFound) {
				String classValVar = classValsSearch.group(2);
				String classValLanguage = classValsSearch.group(3);
				String classValValue = classValsSearch.group(4);
				storeListSolr(classDoc, "classValsVar", classValVar);
				storeListSolr(classDoc, "classValsLanguage", classValLanguage);
				storeListSolr(classDoc, "classValsValue", classValValue);
				classValsFound = classValsSearch.find();
			}

			Matcher classRolesSearch = Pattern.compile("^(class)?Role\\.(\\w+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classComment);
			boolean classRolesFound = classRolesSearch.find();
			boolean classRolesFoundCurrent = classRolesFound;
			while(classRolesFoundCurrent) {
				String classRoleLanguage = classRolesSearch.group(2);
				String classRoleValue = classRolesSearch.group(3);
				storeListSolr(classDoc, "classRoles", classRoleLanguage, classRoleValue);
				classRolesFound = true;
				classRolesFoundCurrent = classRolesSearch.find();
			}
			indexStoreSolr(classDoc, "classRolesFound", classRolesFound); 

			Matcher classKeywordsSearch = Pattern.compile("^(class)?Keyword:\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classComment);
			boolean classKeywordsFoundActual = classKeywordsSearch.find();
			while(classKeywordsFoundActual) {
				String classKeywordValue = classKeywordsSearch.group(2);
				classKeywordsFoundActual = classKeywordsSearch.find();
				if(!classKeywords.contains(classKeywordValue))
					classKeywords.add(classKeywordValue);
				classKeywordsFound = true;
			}

			String sqlString = regex("^(class)?Sql:\\s*(.*)$", classComment, 2);
			if(NumberUtils.isCreatable(sqlString)) {
				Integer sqlInteger = Integer.parseInt(sqlString);
				Integer sqlMigration = Math.abs(sqlInteger);
				Boolean sqlCreate = sqlInteger > 0;
				Boolean sqlDrop = sqlInteger < 0;
				indexStoreSolr(classDoc, "sqlMigration", sqlMigration);
				if(sqlCreate)
					indexStoreSolr(classDoc, "sqlCreate", sqlCreate);
				if(sqlDrop)
					indexStoreSolr(classDoc, "sqlDrop", sqlDrop);
			}

			Matcher classMapSearch = Pattern.compile("^(class)?Map\\.([^:]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classComment);
			boolean classMapFoundActual = classMapSearch.find();
			while(classMapFoundActual) {
				String classMapKey = classMapSearch.group(2);
				String classMapValue = classMapSearch.group(3);
				String[] classMapKeyParts = StringUtils.split(classMapKey, ".");
				if(classMapKeyParts.length == 2) {
					String classMapKeyType = classMapKeyParts[0];
					if("Integer".equals(classMapKeyType) && NumberUtils.isCreatable(classMapValue)) {
						try {
							indexStoreSolr(classDoc, classMapKeyParts[1], Integer.parseInt(classMapValue));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("Double".equals(classMapKeyType) && NumberUtils.isCreatable(classMapValue)) {
						try {
							indexStoreSolr(classDoc, classMapKeyParts[1], Double.parseDouble(classMapValue));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("Long".equals(classMapKeyType) && NumberUtils.isCreatable(classMapValue)) {
						try {
							indexStoreSolr(classDoc, classMapKeyParts[1], Long.parseLong(classMapValue));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("ZonedDateTime".equals(classMapKeyType) && NumberUtils.isCreatable(classMapValue)) {
						try {
							indexStoreSolr(classDoc, classMapKeyParts[1], Date.from(ZonedDateTime.parse(classMapValue, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("LocalDateTime".equals(classMapKeyType) && NumberUtils.isCreatable(classMapValue)) {
						try {
							indexStoreSolr(classDoc, classMapKeyParts[1], Date.from(LocalDateTime.parse(classMapValue, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("LocalDate".equals(classMapKeyType) && NumberUtils.isCreatable(classMapValue)) {
						try {
							indexStoreSolr(classDoc, classMapKeyParts[1], Date.from(LocalDate.parse(classMapValue, DateTimeFormatter.ISO_OFFSET_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else {
						indexStoreSolr(classDoc, classMapKey, classMapValue);
					}
				}
				else {
					indexStoreSolr(classDoc, classMapKey, classMapValue);
				}
				classMapFoundActual = classMapSearch.find();
			}
		}

		SolrDocument classSuperDoc = null;   
		if(classExtendsGen) {
			ClassParts classPartsSuperGeneric = ClassParts.initClassParts(this, classSuperCompleteNameGeneric, languageName);
			classPartsGenAdd(classPartsSuperGeneric);

			if(StringUtils.startsWith(classSuperCanonicalName, domainPackageName)) {
				SolrQuery solrSearch = new SolrQuery();   
				solrSearch.setQuery("*:*");
				solrSearch.setRows(1);
				solrSearch.addFilterQuery("classCanonicalName_" + languageActualName + "_indexed_string:" + ClientUtils.escapeQueryChars(classSuperCanonicalNameGeneric));
				solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
				solrSearch.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
				QueryResponse searchResponse = solrClientComputate.query(solrSearch);
				SolrDocumentList searchList = searchResponse.getResults();
				if(searchList.size() > 0) { 
					classSuperDoc = searchList.get(0);
				}
			} 
			else if(!StringUtils.contains(classSuperCanonicalName, ".") && StringUtils.isNotBlank(classSuperCanonicalName)) {
				SolrQuery solrSearch = new SolrQuery();   
				solrSearch.setQuery("*:*");
				solrSearch.setRows(1);
				solrSearch.addFilterQuery("classCanonicalName_" + languageActualName + "_indexed_string:" + ClientUtils.escapeQueryChars(classSuperCanonicalNameGeneric));
				solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
				solrSearch.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
				QueryResponse searchResponse = solrClientComputate.query(solrSearch);
				SolrDocumentList searchList = searchResponse.getResults();
				if(searchList.size() > 0) { 
					classSuperDoc = searchList.get(0);
				}
			}
		}
		if(classSuperDoc != null) {
			List<String> classSuperEcrireMethodes = (List<String>)classSuperDoc.get("classWriteMethods_stored_strings");
			if(classSuperEcrireMethodes != null) {
				for(String classSuperEcrireMethode : classSuperEcrireMethodes) {
					indexStoreListSolr(classDoc, "classSuperEcrireMethodes",  classSuperEcrireMethode);
				}
			}
		}

		if(classDoc.getField("id") == null)
			classDoc.addField("id", classKey);  

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

		List<JavaMember> membersQdox = classQdox.getMembers();
//		List<JavaMember> membersQdox = new ArrayList<JavaMember>();
//		membersQdox.addAll(classQdox.getFields());
//		membersQdox.addAll(classQdox.getConstructors());
//		membersQdox.addAll(classQdox.getMethods());
		for(JavaMember memberQdox : membersQdox) {  
			partNumber++;

			if(memberQdox instanceof JavaField) {    
				SolrInputDocument fieldDoc = classDocClone.deepCopy();
				JavaField fieldQdox = (JavaField)memberQdox;
				String fieldComment = fieldQdox.getComment();
				String fieldVar = fieldQdox.getName();
				String fieldKey = classAbsolutePath + "." + fieldVar;
				String fieldSourceCode = StringUtils.substringBeforeLast(StringUtils.trim(regex("\\s+" + fieldVar + "\\s*=([\\s\\S]*)", fieldQdox.getCodeBlock(), 1)), ";");
				String fieldString = regex("^(champ)?String\\." + languageName + ":(.*)", fieldComment);
				if(fieldString != null) {
					fieldSourceCode = "\"" + StringUtils.replace(StringUtils.replace(fieldString, "\\", "\\\\"), "\"", "\\\"") + "\"";
					indexStoreSolr(fieldDoc, "fieldString", languageName, fieldString); 
				}

				// Champs Solr du champ. 

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
				indexStoreSolr(fieldDoc, "fieldIsTest", fieldIsTest); 
				indexStoreSolr(fieldDoc, "fieldIsOverride", fieldIsOverride); 

				ClassParts fieldClassParts = ClassParts.initClassParts(this, fieldQdox.getType(), languageName);
	
				storeRegexComments(fieldComment, languageName, fieldDoc, "fieldComment");
				storeSolr(fieldDoc, "fieldSimpleNameComplete", languageName, fieldClassParts.simpleNameComplete);
				String fieldCanonicalNameComplete = storeSolr(fieldDoc, "fieldCanonicalNameComplete", languageName, fieldClassParts.canonicalNameComplete);
				storeSolr(fieldDoc, "fieldSourceCode", languageName, fieldSourceCode);
				fieldDoc.addField("id", fieldCanonicalNameComplete + " " + fieldKey);

				for(String languageName : otherLanguages) { 
					ClassParts fieldClassPartsLanguage = ClassParts.initClassParts(this, fieldClassParts, languageName);
					String fieldVarLanguage = regex("^(champ)?Var\\." + languageName + ": (.*)", fieldComment);
					fieldVarLanguage = fieldVarLanguage == null ? fieldVar : fieldVarLanguage;
					String fieldSourceCodeLanguage = regexReplaceAll(fieldComment, fieldSourceCode, languageName);
					String fieldStringLanguage = regex("^(champ)?String\\." + languageName + ":(.*)", fieldComment);
					if(fieldStringLanguage != null) {
						fieldSourceCodeLanguage = "\"" + StringUtils.replace(StringUtils.replace(fieldStringLanguage, "\\", "\\\\"), "\"", "\\\"") + "\"";
						indexStoreSolr(fieldDoc, "fieldString", languageName, fieldString); 
					}

					indexStoreSolr(fieldDoc, "fieldVar", languageName, fieldVarLanguage); 
					storeSolr(fieldDoc, "fieldSimpleNameComplete", languageName, fieldClassPartsLanguage.simpleNameComplete);
					storeSolr(fieldDoc, "fieldCanonicalNameComplete", languageName, fieldClassPartsLanguage.canonicalNameComplete);
					storeRegexComments(fieldComment, languageName, fieldDoc, "fieldComment");
					storeSolr(fieldDoc, "fieldSourceCode", languageName, fieldSourceCodeLanguage);
				}  

				solrClientComputate.add(fieldDoc); 
				solrClientComputate.commit();
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
					classPartsGenAdd(constructorParamClassParts);
					storeListSolr(constructorDoc, "constructorParamsSimpleNameComplete", languageName, constructorParamClassParts.simpleNameComplete);
					storeListSolr(constructorDoc, "constructorParamsVariableArgs", constructorParamQdox.isVarArgs());
					for(String languageName : otherLanguages) { 
						String constructorParamVarLanguage = regex("^(constructeur)?Param" + constructorParamNum + "\\.var\\." + languageName + ": (.*)", constructorComment);
						if(constructorParamVarLanguage == null)
							constructorParamVarLanguage = constructorParamVar;
						ClassParts constructorParamClassPartsLanguage = ClassParts.initClassParts(this, constructorParamClassParts, languageName);

						classPartsGenAdd(constructorParamClassPartsLanguage);
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

				constructorDoc.addField("id", constructorKey);
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
	
					if(classExtendsGen && !methodIsOverride && !methodQdox.isStatic() && !methodQdox.isFinal() && methodQdox.getDeclaringClass().equals(classQdox) 
							&& methodQdox.isProtected() && methodParamsQdox.size() == 1 && classReturnQdox.isVoid()
							&& StringUtils.startsWith(methodQdox.getName(), "_")) {

						// is Entity. 
						SolrInputDocument entityDoc = classDocClone.deepCopy();
						String entityVar = indexStoreSolr(entityDoc, "entityVar", languageName, StringUtils.substringAfter(methodQdox.getName(), "_"));
						indexStoreListSolr(classDoc, "classEntityVars", languageName, entityVar);
						String entityVarCapitalized = indexStoreSolr(entityDoc, "entityVarCapitalized", languageName, StringUtils.capitalize(entityVar));
						JavaClass entityClassQdox = methodParamsQdox.get(0).getJavaClass();
						ClassParts entityClassParts = ClassParts.initClassParts(this, entityClassQdox, languageName);
						Boolean entityWrap = false;

						if(entityClassParts.simpleName.equals("Wrap")) {
							entityClassParts = ClassParts.initClassParts(this, entityClassParts.canonicalNameGeneric, entityVar);
							entityWrap = true;
							classContainsWrap = true;
						}

						classPartsGenAdd(entityClassParts);
						classPartsGenPageAdd(entityClassParts);
						List<String> entityCanonicalNamesSuperAndMeWithoutGen = new ArrayList<String>();
						if(StringUtils.isNotEmpty(entityClassParts.canonicalNameGeneric)) {
							ClassParts classPartsGeneric = ClassParts.initClassParts(this, entityClassParts.canonicalNameGeneric, languageName);
							classPartsGenAdd(classPartsGeneric);
							classPartsGenPageAdd(classPartsGeneric);
							classPartsGenAdd(entityClassParts);

							if(classPartsGeneric.solrDocument != null) {
								List<String> entitySuperClassesAndMeWithoutGen = (List<String>)classPartsGeneric.solrDocument.get("entitySuperClassesAndMeWithoutGen_stored_strings");
								if(entitySuperClassesAndMeWithoutGen != null) {
									for(String canonicalName : entitySuperClassesAndMeWithoutGen) {
										entityCanonicalNamesSuperAndMeWithoutGen.add(canonicalName);
										indexStoreListSolr(entityDoc, "entitySuperClassesAndMeWithoutGen", canonicalName); 
									}
								}
							}
						}
						else if(entityClassParts != null && entityClassParts.solrDocument != null) {

							List<String> entitySuperClassesAndMeWithoutGen = (List<String>)entityClassParts.solrDocument.get("entitySuperClassesAndMeWithoutGen_stored_strings");
							if(entitySuperClassesAndMeWithoutGen != null) {
								for(String canonicalName : entitySuperClassesAndMeWithoutGen) {
									entityCanonicalNamesSuperAndMeWithoutGen.add(canonicalName);
									indexStoreListSolr(entityDoc, "entitySuperClassesAndMeWithoutGen", canonicalName); 
								}
							}
						}

						indexStoreSolr(entityDoc, "entityWrap", entityWrap);
						Boolean entityInitialized = indexStoreSolr(entityDoc, "entityInitialized", !entityVar.endsWith("_") && BooleanUtils.isTrue(entityClassParts.extendsGen));

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

						Boolean entityDefined = 
								entityCanonicalName.equals(VAL_canonicalNameString)
								|| classPartsChain != null && entityCanonicalName.equals(classPartsChain.canonicalName)
								|| entityCanonicalName.equals(VAL_canonicalNameBoolean)
								|| entityCanonicalName.equals(VAL_canonicalNameInteger)
								|| entityCanonicalName.equals(VAL_canonicalNameBigDecimal)
								|| entityCanonicalName.equals(VAL_canonicalNameDouble)
								|| entityCanonicalName.equals(VAL_canonicalNameFloat)
								|| entityCanonicalName.equals(VAL_canonicalNameLong)
								|| entityCanonicalName.equals(VAL_canonicalNameZonedDateTime)
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
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameZonedDateTime.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameLocalDateTime.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameLocalDate.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameTimestamp.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameList) && VAL_canonicalNameDate.equals(entityCanonicalNameGeneric)
								|| classPartsChain != null && entityCanonicalName.equals(VAL_canonicalNameHashSet) && classPartsChain.canonicalName.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameString.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameBoolean.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameInteger.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameBigDecimal.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameDouble.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameFloat.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameLong.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameZonedDateTime.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameLocalDateTime.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameLocalDate.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameTimestamp.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameDate.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameHashSet) && VAL_canonicalNameLong.equals(entityCanonicalNameGeneric)
								|| classPartsChain != null && entityCanonicalName.equals(VAL_canonicalNameArrayList) && classPartsChain.canonicalName.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameString.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameBoolean.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameInteger.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameBigDecimal.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameDouble.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameFloat.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLong.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameZonedDateTime.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLocalDateTime.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLocalDate.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameTimestamp.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameDate.equals(entityCanonicalNameGeneric)
								|| entityCanonicalName.equals(VAL_canonicalNameArrayList) && VAL_canonicalNameLong.equals(entityCanonicalNameGeneric)
								|| entitySetter != null
								;
						
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
						if(entityWrap)
							entityClassQdox = builder.getClassByName(entityCanonicalName);

						for(String siteEcrireMethode : siteWriteMethods) {
							if(entityClassQdox.getMethodBySignature(siteEcrireMethode, new ArrayList<JavaType>()) != null
									|| entityClassQdox.getMethodBySignature(siteEcrireMethode + classSimpleName, new ArrayList<JavaType>()) != null)
								indexStoreListSolr(entityDoc, "entityWriteMethods",  siteEcrireMethode);
						}
						
						String entitySimpleNameBase = null;
						if(StringUtils.isNotEmpty(entityCanonicalNameBase)) {
							entitySimpleNameBase = StringUtils.substringAfterLast(entityCanonicalNameBase, ".");
						}
						indexStoreSolr(entityDoc, "entitySimpleNameBase", languageName, entitySimpleNameBase);
						
						String entityVarParam = methodParamsQdox.get(0).getName();
//						if(entityClassParts.canonicalName.equals(ArrayList.class.getCanonicalName()) || entityClassParts.canonicalName.equals(List.class.getCanonicalName()))
//							entityVarParam = "l";
//						else
//							entityVarParam = "o";
						indexStoreSolr(entityDoc, "entityVarParam", languageName, entityVarParam);
						
						String entityVarWrap = indexStoreSolr(entityDoc, "entityVarWrap", languageName, entityVar + "Wrap");

						Boolean entityInitDeep = indexStoreSolr(entityDoc, "entityInitDeep", !entityVar.endsWith("_"));
						
//						String entityParamVar = StringUtils.equalsAny(entityClassQdox, "");
//						indexStoreSolr(entityDoc, "entityParamVar", regexFound("^(entity)?exact:\\s*(true)$", methodComment));
//							if(canonicalName.equals(class_.canonicalNameArrayList) || canonicalName.equals(class_.canonicalNameList))
//								o.tout("l");
//							else if(o.estVide())
//								o.tout("o");

						if(entityCanonicalNamesSuperAndMeWithoutGen.size() > 0) {
							String fqSuperClassesAndMe = "(" + qdoxSuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c.getCanonicalName())).collect(Collectors.joining(" OR ")) + ")";

							SolrQuery solrSearchMethodBefore = new SolrQuery();   
							solrSearchMethodBefore.setQuery("*:*");
							solrSearchMethodBefore.setRows(10);
							String fqMethodBefore = "(" + entityCanonicalNamesSuperAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars("before" + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
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
	//						entityMethodsBefore.add(classQdox.getMethodBySignature(entityVar + "Before", new ArrayList<JavaType>() {{ add(entityClassQdox); }}, true));
	//						for(JavaClass c : qdoxSuperClassesAndMe) {
	//							String cNomSimple = StringUtils.substringAfterLast(c.getCanonicalName(), ".");
	//							entityMethodsBefore.add(classQdox.getMethodBySignature("before" + cNomSimple, new ArrayList<JavaType>() {{ add(c); }}, true));
	//							entityMethodsBefore.add(classQdox.getMethodBySignature("before" + cNomSimple, new ArrayList<JavaType>() {{ add(c); add(classQdoxString); }}, true));
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
							String fqMethodAfter = "(" + entityCanonicalNamesSuperAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars("after" + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
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
//						entityMethodsAfter.add(classQdox.getMethodBySignature(entityVar + "After", new ArrayList<JavaType>() {{ add(entityClassQdox); }}, true));
//						for(JavaClass c : qdoxSuperClassesAndMe) {
//							String cNomSimple = StringUtils.substringAfterLast(c.getCanonicalName(), ".");
//							entityMethodsAfter.add(classQdox.getMethodBySignature("after" + cNomSimple, new ArrayList<JavaType>() {{ add(c); }}, true));
//							entityMethodsAfter.add(classQdox.getMethodBySignature("after" + cNomSimple, new ArrayList<JavaType>() {{ add(c); add(classQdoxString); }}, true));
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

							Matcher entityValsSearch = Pattern.compile("^(entity)?Val\\.(\\w+)\\.(\\w+):(.*)", Pattern.MULTILINE).matcher(methodComment);
							boolean entityValsFound = entityValsSearch.find();
							while(entityValsFound) {
								String entityValLanguage = entityValsSearch.group(2);
								String entityValVar = entityValsSearch.group(3);
								String entityValValue = entityValsSearch.group(4);
								storeListSolr(entityDoc, "entityValsVar", entityValVar);
								storeListSolr(entityDoc, "entityValsLanguage", entityValLanguage);
								storeListSolr(entityDoc, "entityValsValue", entityValValue);
								entityValsFound = entityValsSearch.find();
							}

							Matcher entityOptionsSearch = Pattern.compile("^(entity)?Option\\.(\\w+)\\.([^:\\n]+)(:([^:\\n]+))?(:([^\\n]+))?", Pattern.MULTILINE).matcher(methodComment);
							boolean entityOptionsFound = entityOptionsSearch.find();
							while(entityOptionsFound) {
								String entityOptionLanguage = entityOptionsSearch.group(2);
								String entityOptionVar = entityOptionsSearch.group(3);
								String entityOptionDescription = entityOptionsSearch.group(5);
								String entityOptionValues = entityOptionsSearch.group(7);
								if(StringUtils.isBlank(entityOptionDescription))
									entityOptionDescription = entityOptionVar;
								storeListSolr(entityDoc, "entityOptionsVar", entityOptionVar);
								storeListSolr(entityDoc, "entityOptionsLanguage", entityOptionLanguage);
								storeListSolr(entityDoc, "entityOptionsDescription", entityOptionDescription);
								storeListSolr(entityDoc, "entityOptionsValues", StringUtils.isNotBlank(entityOptionValues) ? entityOptionValues : "");
								entityOptionsFound = entityOptionsSearch.find();
							}
							if(entityOptionsFound)
								storeSolr(entityDoc, "entityOptions", true);

							Matcher entityKeywordsSearch = Pattern.compile("^(entity)?Keyword:\\s*(.*)\\s*", Pattern.MULTILINE).matcher(methodComment);
							boolean entityKeywordsFound = entityKeywordsSearch.find();
							boolean entityKeywordsFoundCurrent = entityKeywordsFound;
							while(entityKeywordsFoundCurrent) {
								String entityKeywordValue = entityKeywordsSearch.group(2);
								indexStoreListSolr(entityDoc, "entityKeywords", entityKeywordValue);
								entityKeywordsFound = true;
								entityKeywordsFoundCurrent = entityKeywordsSearch.find();
								if(!classKeywords.contains(entityKeywordValue))
									classKeywords.add(entityKeywordValue);
								classKeywordsFound = true;
							}
							indexStoreSolr(entityDoc, "entityKeywordsFound", entityKeywordsFound); 

							String sqlString = regex("^(entity)?Sql:\\s*(.*)$", methodComment, 2);
							if(NumberUtils.isCreatable(sqlString)) {
								Integer sqlInteger = Integer.parseInt(sqlString);
								Integer sqlMigration = Math.abs(sqlInteger);
								Boolean sqlCreate = sqlInteger > 0;
								Boolean sqlDrop = sqlInteger < 0;
								indexStoreSolr(entityDoc, "sqlMigration", sqlMigration);
								if(sqlCreate)
									indexStoreSolr(entityDoc, "sqlCreate", sqlCreate);
								if(sqlDrop)
									indexStoreSolr(entityDoc, "sqlDrop", sqlDrop);
							}

							Matcher entityMapSearch = Pattern.compile("^(entity)?Map.([^:]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(methodComment);
							boolean entityMapFound = entityMapSearch.find();
							boolean entityMapFoundCurrent = entityMapFound;
							while(entityMapFoundCurrent) {
								String entityMapKey = entityMapSearch.group(2);
								String entityMapValue = entityMapSearch.group(3);
								String[] entityMapKeyParts = StringUtils.split(entityMapKey, ".");
								if(entityMapKeyParts.length == 2) {
									String entityMapKeyType = entityMapKeyParts[0];
									if("Integer".equals(entityMapKeyType) && NumberUtils.isCreatable(entityMapValue)) {
										try {
											indexStoreSolr(entityDoc, entityMapKeyParts[1], Integer.parseInt(entityMapValue));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("Double".equals(entityMapKeyType) && NumberUtils.isCreatable(entityMapValue)) {
										try {
											indexStoreSolr(entityDoc, entityMapKeyParts[1], Double.parseDouble(entityMapValue));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("Long".equals(entityMapKeyType) && NumberUtils.isCreatable(entityMapValue)) {
										try {
											indexStoreSolr(entityDoc, entityMapKeyParts[1], Long.parseLong(entityMapValue));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("ZonedDateTime".equals(entityMapKeyType) && NumberUtils.isCreatable(entityMapValue)) {
										try {
											indexStoreSolr(entityDoc, entityMapKeyParts[1], Date.from(ZonedDateTime.parse(entityMapValue, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("LocalDateTime".equals(entityMapKeyType) && NumberUtils.isCreatable(entityMapValue)) {
										try {
											indexStoreSolr(entityDoc, entityMapKeyParts[1], Date.from(LocalDateTime.parse(entityMapValue, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("LocalDate".equals(entityMapKeyType) && NumberUtils.isCreatable(entityMapValue)) {
										try {
											indexStoreSolr(entityDoc, entityMapKeyParts[1], Date.from(LocalDate.parse(entityMapValue, DateTimeFormatter.ISO_OFFSET_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else {
										indexStoreSolr(entityDoc, entityMapKey, entityMapValue);
									}
								}
								else {
									indexStoreSolr(entityDoc, entityMapKey, entityMapValue);
								}
								entityMapFoundCurrent = entityMapSearch.find();
							}
						}

						indexStoreSolr(entityDoc, "entityExact", regexFound("^(entity)?Exact:\\s*(true)$", methodComment));
						Boolean entityPrimaryKey = indexStoreSolr(entityDoc, "entityPrimaryKey", regexFound("^(entity)?PrimaryKey:\\s*(true)$", methodComment));
						Boolean entityUniqueKey = indexStoreSolr(entityDoc, "entityUniqueKey", regexFound("^(entity)?UniqueKey:\\s*(true)$", methodComment));
						Boolean entityEncrypted = indexStoreSolr(entityDoc, "entityEncrypted", regexFound("^(entity)?Encrypted:\\s*(true)$", methodComment));
						Boolean entitySuggested = indexStoreSolr(entityDoc, "entitySuggested", regexFound("^(entity)?Suggested:\\s*(true)$", methodComment));
						Boolean entitySaved = indexStoreSolr(entityDoc, "entitySaved", regexFound("^(entity)?Saved:\\s*(true)$", methodComment));
						Boolean entityIndexed = indexStoreSolr(entityDoc, "entityIndexed", regexFound("^(entity)?Indexed:\\s*(true)$", methodComment));
						Boolean entityIncremented = indexStoreSolr(entityDoc, "entityIncremented", regexFound("^(entity)?Incremented:\\s*(true)$", methodComment));
						Boolean entityStored = indexStoreSolr(entityDoc, "entityStored", regexFound("^(entity)?Stored:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityIndexedOrStored", entityUniqueKey || entityEncrypted || entitySuggested || entityIndexed || entityStored || entityIncremented);
						indexStoreSolr(entityDoc, "entityText", regexFound("^(entity)?Text:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityIgnored", regexFound("^(entity)?Ignored:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityDeclared", regexFound("^(entity)?Declared:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entitySearch", regexFound("^(entity)?Search:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityAdd", regexFound("^(entity)?Add:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityDelete", regexFound("^(entity)?Delete:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityModify", regexFound("^(entity)?Modify:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityRefresh", regexFound("^(entity)?Refresh:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityMultiline", regexFound("^(entity)?Multiline:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityKeys", regexFound("^(entity)?Keys:\\s*(true)$", methodComment));

						indexStoreSolr(entityDoc, "entityDisplayName", languageName, regexLanguage(languageName, "(entity)?DisplayName", methodComment));
						indexStoreSolr(entityDoc, "entityDescription", languageName, regexLanguage(languageName, "(entity)?Description", methodComment));
						indexStoreSolr(entityDoc, "entityOptional", regexFound("^(entity)?Optional:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityHtmlTooltip", languageName, regexLanguage(languageName, "(entity)?HtmlTooltip", methodComment));
//						indexStoreSolr(entityDoc, "entityVarApi", regex("^(entity)?EntiteVarApi:\\s*(.*)$", methodComment));
						indexStoreSolrRegex(entityDoc, languageName, "entityVarApi", "VarApi", methodComment);
						indexStoreSolr(entityDoc, "entityEnumSimpleName", languageName, regexLanguage(languageName, "(entity)?EnumSimpleName", methodComment));
						indexStoreSolr(entityDoc, "entityEnumVar", languageName, regexLanguage(languageName, "(entity)?EnumVar", methodComment));
						indexStoreSolr(entityDoc, "entityEnumVarDescription", languageName, regexLanguage(languageName, "(entity)?EnumVarDescription", methodComment));

						Boolean entityHtml = regexFound("^(entity)?Html:\\s*(true)$", methodComment);

						{ 
							String str = regex("^(entity)?HtmlColumn:\\s*(.*)$", methodComment);
							if(NumberUtils.isCreatable(str)) {
								indexStoreSolr(entityDoc, "entityHtmlColumn", Double.parseDouble(str));
								entityHtml = true;
							}
						}
						{ 
							String str = regex("^(entity)?HtmlRow:\\s*(.*)$", methodComment);
							if(NumberUtils.isCreatable(str)) {
								indexStoreSolr(entityDoc, "entityHtmlRow", Integer.parseInt(str));
								entityHtml = true;
							}
						}
						{ 
							String str = regex("^(entity)?HtmlCell:\\s*(.*)$", methodComment);
							if(NumberUtils.isCreatable(str)) {
								indexStoreSolr(entityDoc, "entityHtmlCell", Integer.parseInt(str));
								entityHtml = true;
							}
						}

						indexStoreSolr(entityDoc, "entityHtml", entityHtml);

						{ 
							String str = regex("^(entity)?MinLength:\\s*(.*)$", methodComment, 2);
							Integer num = NumberUtils.isCreatable(str) ? Integer.parseInt(str) : null;
							if(num != null)
								indexStoreSolr(entityDoc, "entityMinLength", num);
						}

						{
							String str = regex("^(entity)?MaxLength:\\s*(.*)$", methodComment, 2);
							Integer num = NumberUtils.isCreatable(str) ? Integer.parseInt(str) : null;
							if(num != null)
								indexStoreSolr(entityDoc, "entityMaxLength", num);
						}

						{
							String str = regex("^(entity)?Min:\\s*(.*)$", methodComment, 2);
							Double num = NumberUtils.isCreatable(str) ? Double.parseDouble(str) : null;
							if(num != null)
								indexStoreSolr(entityDoc, "entityMin", num);
						}

						{
							String str = regex("^(entity)?Max:\\s*(.*)$", methodComment, 2);
							Double num = NumberUtils.isCreatable(str) ? Double.parseDouble(str) : null;
							if(num != null)
								indexStoreSolr(entityDoc, "entityMax", num);
						}

						for(String languageName : otherLanguages) {  
							indexStoreSolr(entityDoc, "entityDisplayName", languageName, regexLanguage(languageName, "(entity)?DisplayName", methodComment));
							indexStoreSolr(entityDoc, "entityEnumVarDescription", languageName, regexLanguage(languageName, "(entity)?EnumVarDescription", methodComment));
							indexStoreSolr(entityDoc, "entityHtmlTooltip", languageName, regexLanguage(languageName, "(entity)?HtmlTooltip", methodComment));
//							indexStoreSolr(entityDoc, "entityVarApi", languageName, regex("^(entity)?EntiteVarApi." + languageName + ":\\s*(.*)$", methodComment, 1));
							indexStoreSolrRegex(entityDoc, languageName, "entityVarApi", "VarApi", methodComment);
							indexStoreSolr(entityDoc, "entityEnumVar", languageName, regexLanguage(languageName, "(entity)?EnumVar", methodComment));
						}

						Matcher entityAttributeSearch = methodComment == null ? null : Pattern.compile("^(entity)?Attribuer:\\s*([^\\.]+)\\.(.*)\\s*", Pattern.MULTILINE).matcher(methodComment);
						boolean entityAttributeTrouve = entityAttributeSearch == null ? false : entityAttributeSearch.find();
						if(entityAttributeTrouve) {
							String entityAttributeSimpleName = entityAttributeSearch.group(2);
							String entityAttributeVar = entityAttributeSearch.group(3);

							SolrQuery solrSearchClasse = new SolrQuery();   
							solrSearchClasse.setQuery("*:*");
							solrSearchClasse.setRows(1);
							solrSearchClasse.addFilterQuery("classSimpleName_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(entityAttributeSimpleName));
							solrSearchClasse.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
							solrSearchClasse.addFilterQuery("partIsClass_indexed_boolean:true");
							QueryResponse searchResponseClasse = solrClientComputate.query(solrSearchClasse);
							SolrDocumentList searchListClasse = searchResponseClasse.getResults();

							if(searchListClasse.size() > 0) {
								SolrDocument docClasse = searchListClasse.get(0);
								String entityAttributeCanonicalName = (String)docClasse.get("classCanonicalName_" + languageName + "_stored_string");

								SolrQuery solrSearchVar = new SolrQuery();   
								solrSearchVar.setQuery("*:*");
								solrSearchVar.setRows(1);
								solrSearchVar.addFilterQuery("classCanonicalName_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(entityAttributeCanonicalName));
								solrSearchVar.addFilterQuery("entityVar_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(entityAttributeVar));
								solrSearchVar.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
								solrSearchVar.addFilterQuery("partIsEntity_indexed_boolean:true");
								QueryResponse searchResponseVar = solrClientComputate.query(solrSearchVar);
								SolrDocumentList searchListVar = searchResponseVar.getResults();

								if(searchListVar.size() > 0) {
									SolrDocument docEntite = searchListClasse.get(0);
									entityDefined = false;

									indexStoreSolr(entityDoc, "entityAttribute", true);
									indexStoreSolr(entityDoc, "entityAttributeSimpleName", languageName, entityAttributeSimpleName);
									indexStoreSolr(entityDoc, "entityAttributeCanonicalName", languageName, entityAttributeCanonicalName);
									indexStoreSolr(entityDoc, "entityAttributeVar", languageName, entityAttributeVar);

									for(String languageName : otherLanguages) {  
										String entityAttributeCanonicalNameLangue = (String)docClasse.get("classCanonicalName_" + languageName + "_stored_string");
										String entityAttributeSimpleNameLangue = (String)docClasse.get("classCanonicalName_" + languageName + "_stored_string");
										String entityAttributeVarLangue = (String)docEntite.get("entityVar_" + languageName + "_stored_string");

										indexStoreSolr(entityDoc, "entityAttributeSimpleName", languageName, entityAttributeSimpleNameLangue);
										indexStoreSolr(entityDoc, "entityAttributeCanonicalName", languageName, entityAttributeCanonicalNameLangue);
										indexStoreSolr(entityDoc, "entityAttributeVar", languageName, entityAttributeVarLangue);
									}
								}
							}
						}
						storeSolr(entityDoc, "entityDefined", entityDefined);

//						boolean entityWrap = false;
//	
//						String varEntiteEnUS = regex("^(entity)?Var.enUS: (.*)", methodQdox.getComment());
//						entity.var.frFR(entityVar);
//						entity.var.enUS(StringUtils.isEmpty(varEntiteEnUS) ? entityVar : StringUtils.substringAfter(varEntiteEnUS, "_"));
//	
//						regexCommentaires(methodQdox.getComment(), entity.comment);
//						regexReplaceAll(methodQdox.getComment(), methodQdox.getSourceCode(), entity.codeSource);
//			
//						if(entityClassQdox.getFullyQualifiedName().equals(canonicalNameWrapActuel)) {
//							entityType = StringUtils.substringBeforeLast(StringUtils.substringAfter(entityType, "<"), ">");
//							if(StringUtils.contains(entityType, "<"))
//								entityClassQdox = typeBricoleur.getClassByName(StringUtils.substringBefore(entityType, "<"));
//							else
//								entityClassQdox = typeBricoleur.getClassByName(entityType);
//							entityCanonicalName = entityClassQdox.getCanonicalName();
//							entityWrap = true;
//							entity.couverture(true);
//						} 
//						if(entity.clePrimaire)
//							varCleUniqueActuel.tout(entity.var);
//						if(entity.suggere)
//							varSuggereActuel.tout(entity.var);
//	
//						if(!entitysTout.contains(entity))
//							entitysTout.add(entity);
//	
//						tout.add(entity);
//						importationsAjouter(new Chaine().tout(entityCanonicalName));
//						importationsGenAjouter(new Chaine().tout(entityCanonicalName));
//						if(entityListeTypeGenerique != null) {
//							Chaine importation = new Chaine().tout(entityListeTypeGenerique);
//							importationsAjouter(importation);
//							importationsGenAjouter(importation);
//						}
//	
//						boolean etendCluster = etendClasse(entityCanonicalNameClusterActuel);
//						entity.etendCluster(etendCluster);
//						if(!etendCluster && entity.canonicalNameGeneric.pasVide()) {
//							boolean listeCluster = etendClasse(canonicalNameClusterActuel, entity.canonicalNameGeneric.toString());
//							entity.listeCluster(listeCluster);
//						}
//	
//						boolean etendPageXml = entity.etendClasse(canonicalNamePageXmlActuel);
//						entity.etendPageXml(etendPageXml);
//						if(!etendPageXml && entity.canonicalNameGeneric.pasVide()) {
//							boolean listePageXml = etendClasse(canonicalNamePageXmlActuel, entity.canonicalNameGeneric.toString());
//							entity.listePageXml(listePageXml);
//						}
//	
//						boolean etendPagePart = entity.etendClasse(canonicalNamePagePartActuel);
//						entity.etendPagePart(etendPagePart);
//						if(!etendPagePart && entity.canonicalNameGeneric.pasVide()) {
//							boolean listePagePart = etendClasse(canonicalNamePagePartActuel, entity.canonicalNameGeneric.toString());
//							entity.listePagePart(listePagePart);
//						}
//	
//	
//						boolean contientRequestSite = contientChamp(varRequestSite.toString(), entity.classQdox);
//						entity.contientRequestSite(contientRequestSite);
//	
//						boolean contientSetterString = contientMethode(entity.var.toString(), classQdoxString);
//						entity.contientSetterString(contientSetterString);
//	
//						entityEstCmd(entity);
						
						
						
						
						
						
						
						
						
						
						
						
						
						for(JavaAnnotation annotation : annotations) {
							String entityAnnotationLanguage = indexStoreSolr(entityDoc, "entityAnnotations", languageName, annotation.getType().getCanonicalName());
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
	
						String entityKey = classAbsolutePath + "." + entityVar;
		
						// Entites Solr du entity. 
		
						entityDoc.addField("id", entityKey);
						indexStoreSolr(entityDoc, "partIsEntity", true);
						indexStoreSolr(entityDoc, "partNumber", partNumber);

						String entitySourceCode = methodQdox.getSourceCode();
						String entityString = regex("^(entity)?String\\." + languageName + ":(.*)", methodComment);
						if(entityString != null) {
							entitySourceCode = "\n\t\tc.o(\"" + StringUtils.replace(StringUtils.replace(entityString, "\\", "\\\\"), "\"", "\\\"") + "\");\n\t";
							indexStoreSolr(entityDoc, "entityString", languageName, entityString); 
						}
						storeSolr(entityDoc, "entitySourceCode", languageName, entitySourceCode); 

						/////////////////////////
						// entityTypeVertxJson //
						/////////////////////////
						String entitySimpleNameVertxJson = null;
						String entityCanonicalNameVertxJson = null;
						String entityListSimpleNameVertxJson = null;
						String entityListCanonicalNameVertxJson = null;
						if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBoolean)) {
							entitySimpleNameVertxJson = "Boolean";
							entityCanonicalNameVertxJson = VAL_canonicalNameBoolean;
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameDate, VAL_canonicalNameZonedDateTime)) {
							entitySimpleNameVertxJson = "Instant";
							entityCanonicalNameVertxJson = VAL_canonicalNameInstant;
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneId", languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.LocalDateTime", languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZonedDateTime", languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, VAL_canonicalNameDate, languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", languageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", languageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.util.Locale", languageName));
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLocalDate)) {
							entitySimpleNameVertxJson = "Instant";
							entityCanonicalNameVertxJson = VAL_canonicalNameInstant;
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneId", languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.LocalDate", languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, VAL_canonicalNameDate, languageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", languageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", languageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.util.Locale", languageName));
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLong)) {
							entitySimpleNameVertxJson = "Long";
							entityCanonicalNameVertxJson = VAL_canonicalNameLong;
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBigDecimal)) {
							entitySimpleNameVertxJson = "Double";
							entityCanonicalNameVertxJson = VAL_canonicalNameLong;
							classPartsGenAdd(ClassParts.initClassParts(this, NumberUtils.class.getCanonicalName(), languageName));
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
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameList, VAL_canonicalNameArrayList, VAL_canonicalNameSet, VAL_canonicalNameHashSet)) {
							if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameBoolean)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entityListSimpleNameVertxJson = "Boolean";
								entityListCanonicalNameVertxJson = VAL_canonicalNameBoolean;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameDate, VAL_canonicalNameZonedDateTime)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entityListSimpleNameVertxJson = "Instant";
								entityListCanonicalNameVertxJson = VAL_canonicalNameInstant;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameLocalDate)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entityListSimpleNameVertxJson = "Instant";
								entityListCanonicalNameVertxJson = VAL_canonicalNameInstant;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameLong)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entityListSimpleNameVertxJson = "Long";
								entityListCanonicalNameVertxJson = VAL_canonicalNameLong;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameBigDecimal)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entityListSimpleNameVertxJson = "Long";
								entityListCanonicalNameVertxJson = VAL_canonicalNameLong;
								classPartsGenAdd(ClassParts.initClassParts(this, NumberUtils.class.getCanonicalName(), languageName));
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameDouble)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entityListSimpleNameVertxJson = "Double";
								entityListCanonicalNameVertxJson = VAL_canonicalNameDouble;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameFloat)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entityListSimpleNameVertxJson = "Float";
								entityListCanonicalNameVertxJson = VAL_canonicalNameFloat;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameInteger)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entityListSimpleNameVertxJson = "Integer";
								entityListCanonicalNameVertxJson = VAL_canonicalNameInteger;
							}
							storeSolr(entityDoc, "entityListSimpleNameVertxJson", entityListSimpleNameVertxJson);
							storeSolr(entityDoc, "entityListCanonicalNameVertxJson", entityListCanonicalNameVertxJson);
							classPartsGenAdd(ClassParts.initClassParts(this, entityListCanonicalNameVertxJson, languageName));
						}
						else if(classPartsChain != null && StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameString, classPartsChain.canonicalName)) {
							entitySimpleNameVertxJson = "String";
							entityCanonicalNameVertxJson = VAL_canonicalNameString;
						}
						classPartsGenAdd(ClassParts.initClassParts(this, entityCanonicalNameVertxJson, languageName));
						storeSolr(entityDoc, "entitySimpleNameVertxJson", entitySimpleNameVertxJson);
						storeSolr(entityDoc, "entityCanonicalNameVertxJson", entityCanonicalNameVertxJson);

						////////////////////
						// entitySolrCanonicalName //
						////////////////////
						String entitySolrCanonicalName = null;
						String entitySolrSimpleName = null;
						String entityTypeSuffix = null;
						if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBoolean)) {
							entitySolrCanonicalName = VAL_canonicalNameBoolean;
							entitySolrSimpleName = StringUtils.substringAfterLast(entitySolrCanonicalName, ".");
							entityTypeSuffix = "_boolean";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameLocalDate, VAL_canonicalNameDate, VAL_canonicalNameZonedDateTime)) {
							entitySolrCanonicalName = VAL_canonicalNameDate;
							entitySolrSimpleName = StringUtils.substringAfterLast(entitySolrCanonicalName, ".");
							entityTypeSuffix = "_date";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLong)) {
							entitySolrCanonicalName = VAL_canonicalNameLong;
							entitySolrSimpleName = StringUtils.substringAfterLast(entitySolrCanonicalName, ".");
							entityTypeSuffix = "_long";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBigDecimal)) {
							entitySolrCanonicalName = VAL_canonicalNameDouble;
							entitySolrSimpleName = StringUtils.substringAfterLast(entitySolrCanonicalName, ".");
							entityTypeSuffix = "_double";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameDouble)) {
							entitySolrCanonicalName = VAL_canonicalNameDouble;
							entitySolrSimpleName = StringUtils.substringAfterLast(entitySolrCanonicalName, ".");
							entityTypeSuffix = "_double";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameFloat)) {
							entitySolrCanonicalName = VAL_canonicalNameFloat;
							entitySolrSimpleName = StringUtils.substringAfterLast(entitySolrCanonicalName, ".");
							entityTypeSuffix = "_float";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameInteger)) {
							entitySolrCanonicalName = VAL_canonicalNameInteger;
							entitySolrSimpleName = StringUtils.substringAfterLast(entitySolrCanonicalName, ".");
							entityTypeSuffix = "_int";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameList, VAL_canonicalNameArrayList, VAL_canonicalNameSet, VAL_canonicalNameHashSet)) {
							if(entityCanonicalNameGeneric.equals(VAL_canonicalNameBoolean)) {
								entitySolrCanonicalName = VAL_canonicalNameList + "<" + VAL_canonicalNameBoolean + ">";
								entitySolrSimpleName = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameBoolean, ".") + ">";
								entityTypeSuffix = "_booleans";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameLocalDate, VAL_canonicalNameZonedDateTime)) {
								entitySolrCanonicalName = VAL_canonicalNameList + "<" + VAL_canonicalNameDate + ">";
								entitySolrSimpleName = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameDate, ".") + ">";
								entityTypeSuffix = "_dates";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameLong)) {
								entitySolrCanonicalName = VAL_canonicalNameList + "<" + VAL_canonicalNameLong + ">";
								entitySolrSimpleName = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameLong, ".") + ">";
								entityTypeSuffix = "_longs";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameBigDecimal)) {
								entitySolrCanonicalName = VAL_canonicalNameList + "<" + VAL_canonicalNameBigDecimal + ">";
								entitySolrSimpleName = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameBigDecimal, ".") + ">";
								entityTypeSuffix = "_doubles";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameDouble)) {
								entitySolrCanonicalName = VAL_canonicalNameList + "<" + VAL_canonicalNameDouble + ">";
								entitySolrSimpleName = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameDouble, ".") + ">";
								entityTypeSuffix = "_doubles";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameFloat)) {
								entitySolrCanonicalName = VAL_canonicalNameList + "<" + VAL_canonicalNameFloat + ">";
								entitySolrSimpleName = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameFloat, ".") + ">";
								entityTypeSuffix = "_floats";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameInteger)) {
								entitySolrCanonicalName = VAL_canonicalNameList + "<" + VAL_canonicalNameInteger + ">";
								entitySolrSimpleName = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameInteger, ".") + ">";
								entityTypeSuffix = "_ints";
							}
							else if(classPartsChain != null && StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameString, classPartsChain.canonicalName)) {
								entitySolrCanonicalName = VAL_canonicalNameList + "<" + VAL_canonicalNameString + ">";
								entitySolrSimpleName = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameString, ".") + ">";
								entityTypeSuffix = "_strings";
							}
						}
						else if(classPartsChain != null && StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameString, classPartsChain.canonicalName)) {
							entitySolrCanonicalName = VAL_canonicalNameString;
							entitySolrSimpleName = StringUtils.substringAfterLast(entitySolrCanonicalName, ".");
							entityTypeSuffix = "_string";
////								if(videDernier)
////									suffixeType += "_videDernier";
						}
						storeSolr(entityDoc, "entitySolrCanonicalName", entitySolrCanonicalName);
						storeSolr(entityDoc, "entitySolrSimpleName", entitySolrSimpleName);
						storeSolr(entityDoc, "entityTypeSuffix", entityTypeSuffix);

						////////////////////
						// entityJsonType //
						////////////////////
						String entityJsonType = null;
						String entityJsonFormat = null;
						String entityListJsonType = null;
						if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBoolean)) {
							entityJsonType = "boolean";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameDate, VAL_canonicalNameZonedDateTime)) {
							entityJsonType = "string";
							entityJsonFormat = "date-time";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLocalDate)) {
							entityJsonType = "string";
							entityJsonFormat = "date";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLong)) {
							entityJsonType = "number";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBigDecimal)) {
							entityJsonType = "number";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameDouble)) {
							entityJsonType = "number";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameFloat)) {
							entityJsonType = "number";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameInteger)) {
							entityJsonType = "number";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameList, VAL_canonicalNameArrayList, VAL_canonicalNameSet, VAL_canonicalNameHashSet)) {
							if(entityCanonicalNameGeneric.equals(VAL_canonicalNameBoolean)) {
								entityJsonType = "array";
								entityListJsonType = "boolean";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameTimestamp, VAL_canonicalNameLocalDateTime, VAL_canonicalNameLocalDate, VAL_canonicalNameZonedDateTime)) {
								entityJsonType = "array";
								entityListJsonType = "string";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameLong)) {
								entityJsonType = "array";
								entityListJsonType = "number";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameBigDecimal)) {
								entityJsonType = "array";
								entityListJsonType = "number";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameDouble)) {
								entityJsonType = "array";
								entityListJsonType = "number";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameFloat)) {
								entityJsonType = "array";
								entityListJsonType = "number";
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameInteger)) {
								entityJsonType = "array";
								entityListJsonType = "number";
							}
							else if(classPartsChain != null && StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameString, classPartsChain.canonicalName)) {
								entityJsonType = "array";
								entityListJsonType = "string";
							}
							storeSolr(entityDoc, "entityListJsonType", entityListJsonType);
						}
						else if(classPartsChain != null && StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameString, classPartsChain.canonicalName)) {
							entityJsonType = "string";
						}
						storeSolr(entityDoc, "entityJsonType", entityJsonType);
						if(entityJsonFormat != null)
							storeSolr(entityDoc, "entityJsonFormat", entityJsonFormat);
//						
//						if(entityUniqueKey)
//							storeSolr(entityDoc, "entityVarCleUnique", entityVar);
//						if(entityPrimaryKey)
//							storeSolr(entityDoc, "entityVarClePrimaire", entityVar);
//						if(entitySuggested)
//							storeSolr(entityDoc, "entityVarSuggere", entityVar + "_suggere");
//						if(entityIncremented)
//							storeSolr(entityDoc, "entityVarIncremente", entityVar + "_incremente");
//						if(entityEncrypted)
//							storeSolr(entityDoc, "entityVarCrypte", entityVar + "_crypte");
//						if(entityIndexed)
//							storeSolr(entityDoc, "entityVarIndexe", entityVar + "_indexe" + entityTypeSuffix);
//						if(entityStored)
//							storeSolr(entityDoc, "entityVarStocke", entityVar + "_stocke" + entityTypeSuffix);

						if(entityPrimaryKey) {
							classVarPrimaryKey = storeSolr(classDoc, "classVarPrimaryKey", languageName, entityVar);
						}
						if(entityUniqueKey) {
							classVarUniqueKey = storeSolr(classDoc, "classVarUniqueKey", languageName, entityVar);
						}
				
						for(String languageName : otherLanguages) {  
							ClassParts entityClassPartsLangue = ClassParts.initClassParts(this, entityClassParts, languageName);
//							String entityCanonicalNameLangue = regex("^(entity)?NomCanonique\\." + languageName + ":\\s*(.*)", entityComment, entityCanonicalName);
//							String entitySimpleNameLangue = StringUtils.substringAfterLast(entityCanonicalNameLangue, ".");
//							String entityNomEnsembleLangue = StringUtils.substringBeforeLast(entityCanonicalNameLangue, ".");
				
							indexStoreSolr(entityDoc, "entityCanonicalName", languageName, entityClassPartsLangue.canonicalName); 
							indexStoreSolr(entityDoc, "entitySimpleName", languageName, entityClassPartsLangue.simpleName); 
							indexStoreSolr(entityDoc, "entityCanonicalNameComplete", languageName, entityClassPartsLangue.canonicalNameComplete); 
							indexStoreSolr(entityDoc, "entitySimpleNameComplete", languageName, entityClassPartsLangue.simpleNameComplete); 
							indexStoreSolr(entityDoc, "entityCanonicalNameGeneric", languageName, entityClassPartsLangue.canonicalNameGeneric); 
							indexStoreSolr(entityDoc, "entitySimpleNameGeneric", languageName, entityClassPartsLangue.simpleNameGeneric); 

							indexStoreSolr(entityDoc, "entityVarParam", languageName, entityVarParam); 

							String entityVarLangue = regex("^(entity)?Var\\." + languageName + ": (.*)", methodComment);
							entityVarLangue = indexStoreSolr(entityDoc, "entityVar", languageName, entityVarLangue == null ? entityVar : entityVarLangue);
							indexStoreListSolr(classDoc, "classEntityVars", languageName, entityVarLangue);
							if(entityPrimaryKey) {
								storeSolr(classDoc, "classVarPrimaryKey", languageName, entityVarLangue);
							}
							if(entityUniqueKey) {
								storeSolr(classDoc, "classVarUniqueKey", languageName, entityVarLangue);
							}
	
							String entitySourceCodeLangue = entitySourceCode;
							ArrayList<String> replaceKeysLanguage = regexList("^r." + languageName + "\\s*=\\s*(.*)\\n.*", methodComment);
							ArrayList<String> replaceValuesLanguage = regexList("^r." + languageName + "\\s*=\\s*.*\\n(.*)", methodComment);
							for(int i = 0; i < replaceKeysLanguage.size(); i++) {
								String cle = replaceKeysLanguage.get(i);
								String valeur = replaceValuesLanguage.get(i);
								StringUtils.replace(entitySourceCodeLangue, cle, valeur);
							}
							String entityStringLangue = regex("^(entity)?String\\." + languageName + ":(.*)", methodComment);
							if(entityStringLangue != null) {
								entitySourceCodeLangue = "\n\t\tc.o(\"" + StringUtils.replace(StringUtils.replace(entityStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\");\n\t";
								indexStoreSolr(entityDoc, "entityString", languageName, entityStringLangue); 
							}
							storeSolr(entityDoc, "entitySourceCode", languageName, entitySourceCodeLangue); 
	
							storeRegexComments(methodComment, languageName, entityDoc, "entityComment");
						}

						for(JavaClass methodExceptionQdox : methodExceptionsQdox) { 
							String methodExceptionSimpleNameComplete = StringUtils.substringAfterLast(methodExceptionQdox.getCanonicalName(), ".");
							ClassParts methodeExceptionClassParts = ClassParts.initClassParts(this, methodExceptionQdox.getCanonicalName(), languageName);
							if(!classInitDeepExceptions.contains(methodeExceptionClassParts.canonicalNameComplete))
								classInitDeepExceptions.add(methodeExceptionClassParts.canonicalNameComplete);
							storeListSolr(entityDoc, "methodExceptionsSimpleNameComplete", methodExceptionSimpleNameComplete);
							classPartsGenAdd(methodeExceptionClassParts);
							for(String languageName : otherLanguages) {  
								ClassParts methodeExceptionClassPartsLangue = ClassParts.initClassParts(this, methodExceptionQdox.getCanonicalName(), languageName);
								storeListSolr(entityDoc, "methodExceptionsSimpleNameComplete", methodeExceptionClassPartsLangue.simpleNameComplete);
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
							storeListSolr(methodDoc, "methodParamsVariableArgs", methodParamQdox.isVarArgs());
							for(String languageName : otherLanguages) { 
								String methodParamVarLanguage = regex("^(methode)?Param" + methodParamNum + "\\.var\\." + languageName + ": (.*)", methodComment);
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
							storeListSolr(methodDoc, "methodExceptionsSimpleNameComplete", methodExceptionSimpleNameComplete);
							for(String languageName : otherLanguages) {  
								ClassParts methodeExceptionClassPartsLangue = ClassParts.initClassParts(this, methodExceptionQdox.getCanonicalName(), languageName);
								storeListSolr(methodDoc, "methodExceptionsSimpleNameComplete", methodeExceptionClassPartsLangue.simpleNameComplete);
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
		
						methodDoc.addField("id", methodKey);
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
	
						String methodVarLanguage = regex("^(methode)?Var\\." + languageName + ": (.*)", methodComment);
						methodVarLanguage =  methodVarLanguage == null ? methodVar : methodVarLanguage;
	
						regexList("^" + languageName + ":\\s*([^\n]+)", methodComment);

						String methodSourceCode = methodQdox.getSourceCode();
						String methodSourceCodeLanguage = methodSourceCode;
//						ArrayList<String> replaceKeysLanguage = regexList("^r." + languageName + "\\s*=\\s*(.*)\\n.*", methodComment);
//						ArrayList<String> replaceValuesLanguage = regexList("^r." + languageName + "\\s*=\\s*.*\\n(.*)", methodComment);
//						for(int i = 0; i < replaceKeysLanguage.size(); i++) {
//							String regexKey = replaceKeysLanguage.get(i);
//							String regexValue = replaceValuesLanguage.get(i);
//							StringUtils.replace(methodSourceCodeLanguage, regexKey, regexValue);
//						}
						String methodString = regex("^(methode)?String\\." + languageName + ":(.*)", methodComment);
						if(methodString != null) {
							methodSourceCode = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodString, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
							indexStoreSolr(methodDoc, "methodString", languageName, methodString); 
						}
						storeSolr(methodDoc, "methodSourceCode", languageName, methodSourceCode);

						for(String languageName : otherLanguages) {  
							methodVarLanguage = regex("^(methode)?Var\\." + languageName + ":\\s*([^\n]+)", methodComment);
							methodVarLanguage = indexStoreSolr(methodDoc, "methodVar", languageName, methodVarLanguage == null ? methodVar : methodVarLanguage);
							regexList("^" + languageName + ":\\s*([^\n]+)", methodComment);
							methodSourceCodeLanguage = regexReplaceAll(methodComment, methodSourceCode, languageName);
							String methodStringLangue = regex("^(methode)?String\\." + languageName + ":(.*)", methodComment);
							if(methodStringLangue != null) {
								methodSourceCodeLanguage = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
								indexStoreSolr(methodDoc, "methodString", languageName, methodStringLangue); 
							}
							storeSolr(methodDoc, "methodSourceCode", languageName, methodSourceCodeLanguage);
							storeRegexComments(methodComment, languageName, methodDoc, "methodComment");
						} 
	
						solrClientComputate.add(methodDoc); 
					}
				}
			}
		}

		if(classVarPrimaryKey == null && classSuperDoc != null) {
			classVarPrimaryKey = (String)classSuperDoc.get("classVarPrimaryKey_" + languageName + "_stored_string");
			if(classVarPrimaryKey != null) {
				storeSolr(classDoc, "classVarPrimaryKey", languageName, classVarPrimaryKey);
				for(String languageName : otherLanguages) {  
					String classVarPrimaryKeyLangue = (String)classSuperDoc.get("classVarPrimaryKey_" + languageName + "_stored_string");
					if(classVarPrimaryKeyLangue != null) {
						storeSolr(classDoc, "classVarPrimaryKey", languageName, classVarPrimaryKeyLangue);
					}
				}
			}
		}
		if(classVarUniqueKey == null && classSuperDoc != null) {
			classVarUniqueKey = (String)classSuperDoc.get("classVarUniqueKey_" + languageName + "_stored_string");
			if(classVarUniqueKey != null) {
				storeSolr(classDoc, "classVarUniqueKey", languageName, classVarUniqueKey);
				for(String languageName : otherLanguages) {  
					String classVarUniqueKeyLangue = (String)classSuperDoc.get("classVarUniqueKey_" + languageName + "_stored_string");
					if(classVarUniqueKeyLangue != null) {
						storeSolr(classDoc, "classVarUniqueKey", languageName, classVarUniqueKeyLangue);
					}
				}
			}
		}

		for(String classInitDeepException : classInitDeepExceptions) {
			indexListSolr(classDoc, "classInitDeepExceptions", classInitDeepException); 
			storeListSolr(classDoc, "classInitDeepExceptions", classInitDeepException); 
		}

		indexStoreSolr(classDoc, "classKeywordsFound", classKeywordsFound); 
		for(String classKeywordValue : classKeywords) {
			indexListSolr(classDoc, "classKeywords", classKeywordValue); 
			storeListSolr(classDoc, "classKeywords", classKeywordValue); 
		}
		
		ClassParts classPartsWrap = classPartsWrap(domainPackageName);
		classPartsGenAdd(classPartsWrap);

		if(!classApiMethods.contains("Search") && classKeywordsFound && (classKeywords.contains("Search.request") || classKeywords.contains("Search.response")))
			classApiMethods.add("Search");
		if(!classApiMethods.contains("POST") && classKeywordsFound && (classKeywords.contains("POST.request") || classKeywords.contains("POST.response")))
			classApiMethods.add("POST");
		if(!classApiMethods.contains("PATCH") && classKeywordsFound && (classKeywords.contains("PATCH.request") || classKeywords.contains("PATCH.response")))
			classApiMethods.add("PATCH");
		if(!classApiMethods.contains("GET") && classKeywordsFound && (classKeywords.contains("GET.request") || classKeywords.contains("GET.response")))
			classApiMethods.add("GET");
		if(!classApiMethods.contains("PUT") && classKeywordsFound && (classKeywords.contains("PUT.request") || classKeywords.contains("PUT.response")))
			classApiMethods.add("PUT");
		if(!classApiMethods.contains("DELETE") && classKeywordsFound && (classKeywords.contains("DELETE.request") || classKeywords.contains("DELETE.response")))
			classApiMethods.add("DELETE");

		if(classApi) {
			String classApiUri = indexStoreSolrRegex(classDoc, languageName, "classApiUri", "ApiUri", classComment);
			String classApiTag = indexStoreSolrRegex(classDoc, languageName, "classApiTag", "ApiTag", classComment);

			for(String classApiMethod : classApiMethods) {
				indexStoreListSolr(classDoc, "classApiMethods", classApiMethod); 
//				if(classKeywordsFound && (classKeywords.contains(classApiMethod + ".request") || classKeywords.contains(classApiMethod + ".response"))) {
				String classApiUriMethode = regexLanguage(languageName, "(class)?ApiUri" + classApiMethod, classComment);

				if(classApiMethod.contains("Search"))
					indexStoreSolr(classDoc, "classApiMethod" + classApiMethod, regex("^(class)?ApiMethode" + classApiMethod + ":\\s*(.*)", classComment, "GET"));
				else if(classApiMethod.contains("GET"))
					indexStoreSolr(classDoc, "classApiMethod" + classApiMethod, regex("^(class)?ApiMethode" + classApiMethod + ":\\s*(.*)", classComment, "GET"));
				else if(classApiMethod.contains("POST"))
					indexStoreSolr(classDoc, "classApiMethod" + classApiMethod, regex("^(class)?ApiMethode" + classApiMethod + ":\\s*(.*)", classComment, "POST"));
				else if(classApiMethod.contains("PUT"))
					indexStoreSolr(classDoc, "classApiMethod" + classApiMethod, regex("^(class)?ApiMethode" + classApiMethod + ":\\s*(.*)", classComment, "PUT"));
				else if(classApiMethod.contains("PATCH"))
					indexStoreSolr(classDoc, "classApiMethod" + classApiMethod, regex("^(class)?ApiMethode" + classApiMethod + ":\\s*(.*)", classComment, "PATCH"));
				else if(classApiMethod.contains("DELETE"))
					indexStoreSolr(classDoc, "classApiMethod" + classApiMethod, regex("^(class)?ApiMethode" + classApiMethod + ":\\s*(.*)", classComment, "DELETE"));
				else
					indexStoreSolr(classDoc, "classApiMethod" + classApiMethod, regex("^(class)?ApiMethode" + classApiMethod + ":\\s*(.*)", classComment, classApiMethod));

				indexStoreSolrRegex(classDoc, languageName, "classApiOperationId" + classApiMethod, "ApiOperationId" + classApiMethod, classComment, StringUtils.lowerCase(classApiMethod) + classSimpleName);
				indexStoreSolrRegex(classDoc, languageName, "classApiOperationId" + classApiMethod + "Request", "ApiOperationId" + classApiMethod + "Request", classComment, classApiMethod + classSimpleName + "Request");
				indexStoreSolrRegex(classDoc, languageName, "classApiOperationId" + classApiMethod + "Response", "ApiOperationId" + classApiMethod + "Response", classComment, classApiMethod + classSimpleName + "Response");
				indexStoreSolrRegex(classDoc, languageName, "classApiDescription" + classApiMethod, "ApiDescription" + classApiMethod, classComment, regexLanguage(languageName, "(class)?Description" + classApiMethod + "", classComment));

				if(classExtendsBase) {
					indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod, languageName, (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "_" + languageName + "_stored_string"));
					indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod + "Request", languageName, (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "Request" + "_" + languageName + "_stored_string"));
					indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod + "Response", languageName, (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "Response" + "_" + languageName + "_stored_string"));
				}

				String classPageNomSimpleMethode = regex("^(class)?Page" + classApiMethod + ":\\s*(.*)", classComment);
				String classPageSuperNomSimpleMethode = regex("^(class)?PageSuper" + classApiMethod + ":\\s*(.*)", classComment);
				String classApiMediaType200Methode = regex("^(class)?ApiTypeMedia200" + classApiMethod + ":\\s*(.*)", classComment, classPageNomSimpleMethode == null ? "application/json" : "text/html");
				String classApiKeywordMethod = regexLanguage(languageName, "(class)?ApiKeyword" + classApiMethod, classComment);
				if(StringUtils.contains(classApiMethod, "POST")
						|| StringUtils.contains(classApiMethod, "Search")
						|| StringUtils.contains(classApiMethod, "PATCH")
						) {
					if(StringUtils.isBlank(classApiKeywordMethod))
						classApiKeywordMethod = StringUtils.substringAfterLast(classApiUriMethode, "/");
					if(StringUtils.isBlank(classApiUriMethode))
						classApiUriMethode = classApiUri;
				}
				else {
					if(StringUtils.isBlank(classApiKeywordMethod))
						classApiKeywordMethod = StringUtils.substringAfterLast(StringUtils.substringBeforeLast(classApiUriMethode, "/"), "/");
					if(StringUtils.isBlank(classApiUriMethode))
						classApiUriMethode = classApiUri + "/{pk}";
				}
				indexStoreSolr(classDoc, "classApiMediaType200" + classApiMethod, classApiMediaType200Methode);
				indexStoreSolr(classDoc, "classApiKeyword" + classApiMethod, languageName, classApiKeywordMethod);
				indexStoreSolr(classDoc, "classApiUri" + classApiMethod, languageName, classApiUriMethode);
				if(classPageNomSimpleMethode != null) {
					{
						SolrQuery searchPage = new SolrQuery();   
						searchPage.setQuery("*:*");
						searchPage.setRows(1);
						searchPage.addFilterQuery("classSimpleName_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(classPageNomSimpleMethode));
						searchPage.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
						searchPage.addFilterQuery("partIsClass_indexed_boolean:true");
						QueryResponse searchResponsePage = solrClientComputate.query(searchPage);
						SolrDocumentList searchListPage = searchResponsePage.getResults();
	
						if(searchListPage.size() > 0) {
							SolrDocument docEntite = searchListPage.get(0);
							String classPageNomCanoniqueMethode = (String)docEntite.get("classCanonicalName_frFR_stored_string");
	//							String classPageNomSimpleMethode = (String)docEntite.get("classSimpleName_frFR_stored_string");
							indexStoreSolr(classDoc, "classPageNomCanonique" + classApiMethod, languageName, classPageNomCanoniqueMethode);
							indexStoreSolr(classDoc, "classPageNomSimple" + classApiMethod, languageName, classPageNomSimpleMethode);
							classPartsGenApiAdd(ClassParts.initClassParts(this, classPageNomCanoniqueMethode, languageName));
						}
						else {
							String classPageNomCanoniqueMethode = classCanonicalName + "Page";
							indexStoreSolr(classDoc, "classPageNomCanonique" + classApiMethod, languageName, classPageNomCanoniqueMethode);
							indexStoreSolr(classDoc, "classPageNomSimple" + classApiMethod, languageName, classPageNomSimpleMethode);
							classPartsGenApiAdd(ClassParts.initClassParts(this, classPageNomCanoniqueMethode, languageName));
						}
					}
					if(classPageSuperNomSimpleMethode != null) {
						SolrQuery searchPage = new SolrQuery();   
						searchPage.setQuery("*:*");
						searchPage.setRows(1);
						searchPage.addFilterQuery("classSimpleName_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(classPageSuperNomSimpleMethode));
						searchPage.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
						searchPage.addFilterQuery("partIsClass_indexed_boolean:true");
						QueryResponse searchResponsePage = solrClientComputate.query(searchPage);
						SolrDocumentList searchListPage = searchResponsePage.getResults();
	
						if(searchListPage.size() > 0) {
							SolrDocument docEntite = searchListPage.get(0);
							String classPageSuperNomCanoniqueMethode = (String)docEntite.get("classCanonicalName_frFR_stored_string");
							indexStoreSolr(classDoc, "classPageSuperNomCanonique" + classApiMethod, languageName, classPageSuperNomCanoniqueMethode);
							indexStoreSolr(classDoc, "classPageSuperNomSimple" + classApiMethod, languageName, classPageSuperNomSimpleMethode);
							classPartsGenPageAdd(ClassParts.initClassParts(this, classPageSuperNomCanoniqueMethode, languageName));
						}
					}
		
					String classPageCheminGen = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), "GenPage.java");
					String classPageChemin = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), "Page.java");
					String classPageCheminCss = concat(srcMainResourcesPath, "/webroot/css/", classSimpleName, "Page.css");
					String classPageCheminJs = concat(srcMainResourcesPath, "/webroot/js/", classSimpleName, "Page.js");
		
					indexStoreSolr(classDoc, "classPageCheminGen" + classApiMethod, languageName, classPageCheminGen); 
					indexStoreSolr(classDoc, "classPageChemin" + classApiMethod, languageName, classPageChemin); 
					indexStoreSolr(classDoc, "classPageCheminCss" + classApiMethod, languageName, classPageCheminCss); 
					indexStoreSolr(classDoc, "classPageCheminJs" + classApiMethod, languageName, classPageCheminJs); 
					classPage = true;
				}
//				}
			}
			for(String languageName : otherLanguages) {  
				String classApiUriLangue = indexStoreSolrRegex(classDoc, languageName, "classApiUri", "ApiUri", classComment);
				String classApiTagLangue = indexStoreSolrRegex(classDoc, languageName, "classApiTag", "ApiTag", classComment);

				for(String classApiMethod : classApiMethods) {
	//				if(classKeywordsFound && (classKeywords.contains(classApiMethod + ".request") || classKeywords.contains(classApiMethod + ".response"))) {
						String classApiUriMethodeLangue = regexLanguage(languageName, "apiUri" + classApiMethod, classComment);
						indexStoreSolr(classDoc, "classApiMethod" + classApiMethod, regex("^(class)?ApiMethode" + classApiMethod + ":\\s*(.*)", classComment, classApiMethod));
						indexStoreSolrRegex(classDoc, languageName, "classApiOperationId" + classApiMethod, "ApiOperationId" + classApiMethod, classComment, StringUtils.lowerCase(classApiMethod) + classSimpleName);
						indexStoreSolrRegex(classDoc, languageName, "classApiOperationId" + classApiMethod + "Request", "ApiOperationId" + classApiMethod + "Request", classComment, classApiMethod + classSimpleName + "Request");
						indexStoreSolrRegex(classDoc, languageName, "classApiOperationId" + classApiMethod + "Response", "ApiOperationId" + classApiMethod + "Response", classComment, classApiMethod + classSimpleName + "Response");
						indexStoreSolrRegex(classDoc, languageName, "classApiDescription" + classApiMethod, "ApiDescription" + classApiMethod, classComment, regexLanguage(languageName, "(class)?Description" + classApiMethod + "", classComment));

						if(classExtendsBase) {
							indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod, languageName, (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "_" + languageName + "_stored_string"));
							indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod + "Request", languageName, (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "Request" + "_" + languageName + "_stored_string"));
							indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod + "Response", languageName, (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "Response" + "_" + languageName + "_stored_string"));
						}

						String classApiKeywordMethodLangue = regexLanguage(languageName, "(class)?ApiKeyword" + classApiMethod, classComment);
						if(StringUtils.contains(classApiMethod, "POST")
								|| StringUtils.contains(classApiMethod, "Search")
								|| StringUtils.contains(classApiMethod, "PATCH")
								) {
							if(StringUtils.isBlank(classApiKeywordMethodLangue))
								classApiKeywordMethodLangue = StringUtils.substringAfterLast(classApiUriMethodeLangue, "/");
							if(StringUtils.isBlank(classApiUriMethodeLangue))
								classApiUriMethodeLangue = classApiUriLangue;
						}
						else {
							if(StringUtils.isBlank(classApiKeywordMethodLangue))
								classApiKeywordMethodLangue = StringUtils.substringAfterLast(StringUtils.substringBeforeLast(classApiUriMethodeLangue, "/"), "/");
							if(StringUtils.isBlank(classApiUriMethodeLangue))
								classApiUriMethodeLangue = classApiUriLangue + "/{pk}";
						}
						indexStoreSolr(classDoc, "classApiKeyword" + classApiMethod, languageName, classApiKeywordMethodLangue);
						indexStoreSolr(classDoc, "classApiUri" + classApiMethod, languageName, classApiUriMethodeLangue);
	//				}
				}
			}
		}

		if(classPage) {
			classPartsGenPageAdd(classPartsSiteConfig);
			classPartsGenPageAdd(classPartsSiteRequest);
			classPartsGenPageAdd(classPartsSiteContext);
			classPartsGenPageAdd(classPartsSiteUser);
			classPartsGenPageAdd(ClassParts.initClassParts(this, "java.io.IOException", languageName));
//			classPartsGenPageAdd(ClassParts.initClassParts(this, "javax.servlet.http.HttpServlet", languageName));
			classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.core.http.HttpServerRequest", languageName));
			classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.core.http.HttpServerResponse", languageName));
			classPartsGenPageAdd(classPartsSearchList);
			classPartsGenPageAdd(classPartsWrap);
			classPartsGenPageAdd(classPartsPageLayout);
			classPartsGenPageAdd(ClassParts.initClassParts(this, LocalDateTime.class.getCanonicalName(), languageName));
			classPartsGenPageAdd(ClassParts.initClassParts(this, LocalDate.class.getCanonicalName(), languageName));
			classPartsGenPageAdd(ClassParts.initClassParts(this, ZonedDateTime.class.getCanonicalName(), languageName));
			classPartsGenPageAdd(ClassParts.initClassParts(this, DateTimeFormatter.class.getCanonicalName(), languageName));
			classPartsGenPageAdd(ClassParts.initClassParts(this, Locale.class.getCanonicalName(), languageName));
		}

		for(ClassParts classPartGenPage : classPartsGenPage.values()) {
			indexStoreListSolr(classDoc, "classImportsGenPage", languageName, classPartGenPage.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classPartGenPage, languageName);
				indexStoreListSolr(classDoc, "classImportsGenPage", languageName, classImportClassPartsLanguage.canonicalName);
			}
		}

		Boolean classContexte = indexStoreSolr(classDoc, "classContexte", regexFound("^(class)?Contexte: \\s*(true)$", classComment) || classPage);

		if(classContexte) {

			contextColor = regex("^(context)?Color:\\s*(.*)", classComment);
			if(contextColor != null)
				indexStoreSolr(classDoc, "contextColor", contextColor); 

			contextIconGroup = regex("^(context)?IconGroup:\\s*(.*)", classComment);
			if(contextIconGroup != null)
				indexStoreSolr(classDoc, "contextIconGroup", contextIconGroup); 

			contextIconName = regex("^(context)?IconName:\\s*(.*)", classComment);
			if(contextIconName != null)
				indexStoreSolr(classDoc, "contextIconName", contextIconName); 

			contextAName = regexLanguage(languageName, "(context)?ANameLowercase", classComment);
			if(contextAName != null) {
				indexStoreSolr(classDoc, "contextAName", languageName, contextAName); 
				contextNameSingular = indexStoreSolr(classDoc, "contextNameSingular", languageName, regexLanguage(languageName, "(context)?NomSingulier", classComment, StringUtils.substringAfter(contextAName, " ")));
				contextNamePlural = indexStoreSolr(classDoc, "contextNamePlural", languageName, regexLanguage(languageName, "(context)?NomPluriel", classComment, contextNameSingular + "s"));
				contextNameVar = indexStoreSolr(classDoc, "contextNameVar", languageName, regexLanguage(languageName, "(context)?NomVar", classComment, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contextNameSingular, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
				contextTheNames = indexStoreSolr(classDoc, "contextTheNames", languageName, regexLanguage(languageName, "(context)?TheName", classComment, CONTEXT_ThePlural + contextNamePlural));

				contextAdjective = regexLanguage(languageName, "(context)?Adjective", classComment);
				if(contextAdjective != null) {
					contextAdjectivePlural = indexStoreSolr(classDoc, "contextAdjectivePlural", languageName, regexLanguage(languageName, "(context)?AdjectivePlural", classComment, contextAdjective + CONTEXT_AdjectivePlural));
					contextAdjectiveVar = indexStoreSolr(classDoc, "contextAdjectiveVar", languageName, regexLanguage(languageName, "(context)?AdjectiveVar", classComment, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contextAdjective, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
					if(CONTEXT_AdjectiveBefore) {
						contextNameAdjectiveSingular = indexStoreSolr(classDoc, "contextNameAdjectiveSingular", languageName, regexLanguage(languageName, "(context)?NameAdjectiveSingular", classComment, contextAdjective + " " + contextNameSingular));
						contextNameAdjectivePlural = indexStoreSolr(classDoc, "contextNameAdjectivePlural", languageName, regexLanguage(languageName, "(context)?NomAdjectivePlural", classComment, contextAdjectivePlural + " " + contextNamePlural));
					}
					else {
						contextNameAdjectiveSingular = indexStoreSolr(classDoc, "contextNameAdjectiveSingular", languageName, regexLanguage(languageName, "(context)?NameAdjectiveSingular", classComment, contextNameSingular + " " + contextAdjective));
						contextNameAdjectivePlural = indexStoreSolr(classDoc, "contextNameSingularPluriel", languageName, regexLanguage(languageName, "(context)?NomSingulierPluriel", classComment, contextNamePlural + " " + contextAdjectivePlural));
					}

				}

				if(contextAName.startsWith(CONTEXT_AFemale)) {
					contextThis = indexStoreSolr(classDoc, "contextThis", languageName, regexLanguage(languageName, "(context)?Ce", classComment, CONTEXT_ThisFemaleConsonant));
					contextA = indexStoreSolr(classDoc, "contextA", languageName, regexLanguage(languageName, "(context)?Un", classComment, CONTEXT_AFemale));
					contextCreated = indexStoreSolr(classDoc, "contextCreated", languageName, regexLanguage(languageName, "(context)?Cree", classComment, CONTEXT_CreatedFemale));
					contextModified = indexStoreSolr(classDoc, "contextModified", languageName, regexLanguage(languageName, "(context)?Modifie", classComment, CONTEXT_ModifiedFemale));
					contextActualName = indexStoreSolr(classDoc, "contextActualName", languageName, regexLanguage(languageName, "(context)?NomActuel", classComment, CONTEXT_CurrentFemaleBefore + contextNameSingular + CONTEXT_CurrentFemaleAfter));
//					contextAll = indexStoreSolr(classDoc, "contextAll", languageName, regexLanguage(languageName, "(context)?Tous", classComment, CONTEXT_AllFemalePlural));
					contextAllName = indexStoreSolr(classDoc, "contextAllName", languageName, regexLanguage(languageName, "(context)?TousNom", classComment, CONTEXT_ThePlural + contextNamePlural));
					contextNoneNameFound = indexStoreSolr(classDoc, "contextNoneNameFound", languageName, regexLanguage(languageName, "(context)?AucunNomTrouve", classComment, CONTEXT_NoneFemaleBefore + contextNameSingular + CONTEXT_NoneFemaleAfter));
					if(contextAdjective != null) {
						if(CONTEXT_AdjectiveBefore)
							contextANameAdjective = indexStoreSolr(classDoc, "contextANameAdjective", languageName, regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_AFemale + " " + contextAdjective + " " + contextNameSingular));
						else
							contextANameAdjective = indexStoreSolr(classDoc, "contextANameAdjective", languageName, regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_AFemale + " " + contextNameSingular + " " + contextAdjective));
					}

					String suffixe = StringUtils.substringAfter(contextAName, " ");
					String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
					if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
						contextThisName = indexStoreSolr(classDoc, "contextThisName", languageName, regexLanguage(languageName, "(context)?CeNom", classComment, CONTEXT_ThisFemaleVowel + suffixe));
						contextTheName = indexStoreSolr(classDoc, "contextTheName", languageName, regexLanguage(languageName, "(context)?LeNom", classComment, CONTEXT_TheFemaleVowel + suffixe));
						contextOfName = indexStoreSolr(classDoc, "contextOfName", languageName, regexLanguage(languageName, "(context)?DeNom", classComment, CONTEXT_OfVowel + suffixe));
						if(contextAdjective != null) {
							if(CONTEXT_AdjectiveBefore)
								contextANameAdjective = indexStoreSolr(classDoc, "contextTheNameAdjective", languageName, regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_TheFemaleVowel + " " + contextAdjective + " " + contextNameSingular));
							else
								contextANameAdjective = indexStoreSolr(classDoc, "contextTheNameAdjective", languageName, regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_TheFemaleVowel + " " + contextNameSingular + " " + contextAdjective));
						}
					}
					else {
						contextThisName = indexStoreSolr(classDoc, "contextThisName", languageName, regexLanguage(languageName, "(context)?CeNom", classComment, CONTEXT_ThisFemaleConsonant + suffixe));
						contextTheName = indexStoreSolr(classDoc, "contextTheName", languageName, regexLanguage(languageName, "(context)?LeNom", classComment, CONTEXT_TheFemaleConsonant + suffixe));
						contextOfName = indexStoreSolr(classDoc, "contextOfName", languageName, regexLanguage(languageName, "(context)?DeNom", classComment, CONTEXT_OfConsonant + suffixe));
						if(contextAdjective != null) {
							if(CONTEXT_AdjectiveBefore)
								contextANameAdjective = indexStoreSolr(classDoc, "contextTheNameAdjective", languageName, regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_TheFemaleConsonant + " " + contextAdjective + " " + contextNameSingular));
							else
								contextANameAdjective = indexStoreSolr(classDoc, "contextTheNameAdjective", languageName, regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_TheFemaleConsonant + " " + contextNameSingular + " " + contextAdjective));
						}
					}
				}
				else if(contextAName.startsWith(CONTEXT_AMale)) {
					contextThis = indexStoreSolr(classDoc, "contextThis", languageName, regexLanguage(languageName, "(context)?Ce", classComment, CONTEXT_ThisMaleConsonant));
					contextA = indexStoreSolr(classDoc, "contextA", languageName, regexLanguage(languageName, "(context)?Un", classComment, CONTEXT_AMale));
					contextCreated = indexStoreSolr(classDoc, "contextCreated", languageName, regexLanguage(languageName, "(context)?Cree", classComment, CONTEXT_CreatedMale));
					contextModified = indexStoreSolr(classDoc, "contextModified", languageName, regexLanguage(languageName, "(context)?Modifie", classComment, CONTEXT_ModifiedMale));
					contextActualName = indexStoreSolr(classDoc, "contextActualName", languageName, regexLanguage(languageName, "(context)?NomActuel", classComment, CONTEXT_CurrentMaleBefore + contextNameSingular + CONTEXT_CurrentMaleAfter));
//					contextAll = indexStoreSolr(classDoc, "contextAll", languageName, regexLanguage(languageName, "(context)?Tous", classComment, CONTEXT_AllMalePlural));
					contextAllName = indexStoreSolr(classDoc, "contextAllName", languageName, regexLanguage(languageName, "(context)?TousNom", classComment, CONTEXT_AllMalePlural + contextNamePlural));
					contextNoneNameFound = indexStoreSolr(classDoc, "contextNoneNameFound", languageName, regexLanguage(languageName, "(context)?AucunNomTrouve", classComment, CONTEXT_NoneFoundMaleBefore + contextNameSingular + CONTEXT_NoneFoundMaleAfter));
					if(contextAdjective != null) {
						if(CONTEXT_AdjectiveBefore)
							contextANameAdjective = indexStoreSolr(classDoc, "contextANameAdjective", languageName, regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_AMale + " " + contextAdjective + " " + contextNameSingular));
						else
							contextANameAdjective = indexStoreSolr(classDoc, "contextANameAdjective", languageName, regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_AMale + " " + contextNameSingular + " " + contextAdjective));
					}

					String suffixe = StringUtils.substringAfter(contextAName, " ");
					String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
					if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
						contextThisName = indexStoreSolr(classDoc, "contextThisName", languageName, regexLanguage(languageName, "(context)?CeNom", classComment, CONTEXT_ThisMaleVowel + suffixe));
						contextTheName = indexStoreSolr(classDoc, "contextTheName", languageName, regexLanguage(languageName, "(context)?LeNom", classComment, CONTEXT_TheMaleVowel + suffixe));
						contextOfName = indexStoreSolr(classDoc, "contextOfName", languageName, regexLanguage(languageName, "(context)?DeNom", classComment, CONTEXT_OfVowel + suffixe));
						if(contextAdjective != null) {
							if(CONTEXT_AdjectiveBefore)
								contextANameAdjective = indexStoreSolr(classDoc, "contextTheNameAdjective", languageName, regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_TheMaleVowel + " " + contextAdjective + " " + contextNameSingular));
							else
								contextANameAdjective = indexStoreSolr(classDoc, "contextTheNameAdjective", languageName, regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_TheMaleVowel + " " + contextNameSingular + " " + contextAdjective));
						}
					}
					else {
						contextThisName = indexStoreSolr(classDoc, "contextThisName", languageName, regexLanguage(languageName, "(context)?CeNom", classComment, CONTEXT_ThisMaleConsonant + suffixe));
						contextTheName = indexStoreSolr(classDoc, "contextTheName", languageName, regexLanguage(languageName, "(context)?LeNom", classComment, CONTEXT_TheMaleConsonant + suffixe));
						contextOfName = indexStoreSolr(classDoc, "contextOfName", languageName, regexLanguage(languageName, "(context)?DeNom", classComment, CONTEXT_OfConsonant + suffixe));
						if(contextAdjective != null) {
							if(CONTEXT_AdjectiveBefore)
								contextANameAdjective = indexStoreSolr(classDoc, "contextTheNameAdjective", languageName, regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_TheMaleConsonant + " " + contextAdjective + " " + contextNameSingular));
							else
								contextANameAdjective = indexStoreSolr(classDoc, "contextTheNameAdjective", languageName, regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_TheMaleConsonant + " " + contextNameSingular + " " + contextAdjective));
						}
					}
				}
				indexStoreSolr(classDoc, "contextThisLowercase", languageName, contextThis); 
			}

			contextTitle = regex("^(context)?Title:\\s*(.*)", classComment, contextTheNames);
			if(contextTitle != null)
				indexStoreSolr(classDoc, "contextTitle", languageName, contextTitle); 

			contextH1 = regex("^(context)?H1:\\s*(.*)", classComment, contextTheNames);
			if(contextH1 != null)
				indexStoreSolr(classDoc, "contextH1", languageName, contextH1); 

			contextH2 = regex("^(context)?H2:\\s*(.*)", classComment);
			if(contextH2 != null)
				indexStoreSolr(classDoc, "contextH2", languageName, contextH2); 

			contextH3 = regex("^(context)?H3:\\s*(.*)", classComment);
			if(contextH3 != null)
				indexStoreSolr(classDoc, "contextH3", languageName, contextH3); 
		}

		Boolean classIndexed = indexStoreSolr(classDoc, "classIndexed", regexFound("^(class)?Indexed:\\s*(true)$", classComment) || classSaved || classModel || classPage);

		if(classIndexed) {
			classPartsGenAdd(classPartsSolrInputDocument);
			classPartsGenAdd(classPartsSolrClient);
//			classPartsGenAdd(classPartsTest);
			classPartsGenAdd(classPartsSiteContext);
			classPartsGenAdd(classPartsSolrDocument);
			classPartsGenAdd(classPartsList);
			classPartsGenAdd(classPartsArrayList);
			classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", languageName));
		}

		for(ClassParts classPartGen : classPartsGen.values()) {
			indexStoreListSolr(classDoc, "classImportsGen", languageName, classPartGen.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classPartGen, languageName);
				indexStoreListSolr(classDoc, "classImportsGen", languageName, classImportClassPartsLanguage.canonicalName);
			}
		}

		for(ClassParts classPartGenApi : classPartsGenApi.values()) {
			indexStoreListSolr(classDoc, "classImportsGenApi", languageName, classPartGenApi.canonicalName);
			for(String languageName : otherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classPartGenApi, languageName);
				indexStoreListSolr(classDoc, "classImportsGenApi", languageName, classImportClassPartsLanguage.canonicalName);
			}
		}

		if(classSuperDoc != null) {
			List<String> classSuperEntityVars = (List<String>)classSuperDoc.get("classEntityVars_" + languageName + "_stored_strings");
			if(classSuperEntityVars != null) {
				for(String classSuperEntityVar : classSuperEntityVars)
					indexStoreListSolr(classDoc, "classEntityVars", languageName, classSuperEntityVar);
			}
		}

		indexStoreSolr(classDoc, "classPage", classPage);

		solrClientComputate.add(classDoc);
		solrClientComputate.commit();
		String qDelete = concat("classAbsolutePath_indexed_string", ":\"", classPath, "\" AND (modified_indexed_date:[* TO ", modified.toString(), "-1MILLI] OR (*:* NOT modified_indexed_date:*))");
		solrClientComputate.deleteByQuery(qDelete);
		solrClientComputate.commit(); 
		return classDoc;
	}
}
