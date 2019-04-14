package org.computate.enUS.java;

import java.awt.image.BufferedImage;
import java.io.File;
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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.lang3.ArrayUtils;
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

	ClassParts classPartsSiteRequest;

	ClassParts classPartsChain;

	ClassParts classPartsSiteConfig;

	ClassParts classPartsSiteUser;

	ClassParts classPartsCluster;

	ClassParts classPartsSearchResult;

	ClassParts classPartsAllWriter;

	ClassParts classPartsWrap;

	ClassParts classPartsSearchList;

	ClassParts classPartsPageLayout;

	ClassParts classPartsPagePart;

	String CONTEXTE_frFR_UnMasculin = "un ";

	String CONTEXTE_frFR_UneFeminin = "une ";

	String CONTEXTE_enUS_Une = "an ";

	String CONTEXTE_frFR_CetMasculinVoyelle = "cet ";

	String CONTEXTE_frFR_CetteFemininVoyelle = "cette ";

	String CONTEXTE_enUS_CetteVoyelle = "this ";

	String CONTEXTE_frFR_CeMasculinConsonne = "ce ";

	String CONTEXTE_frFR_CetteFemininConsonne = "cette ";

	String CONTEXTE_enUS_CetteConsonne = "this ";

	String CONTEXTE_frFR_CesPluriel = "ces ";

	String CONTEXTE_enUS_CesPluriel = "these ";

	String CONTEXTE_frFR_CreeMasculin = "créé ";

	String CONTEXTE_frFR_CreeeFeminin = "créée ";

	String CONTEXTE_enUS_Creee = "created ";

	String CONTEXTE_frFR_ModifieMasculin = "modifié ";

	String CONTEXTE_frFR_ModifieeFeminin = "modifiée ";

	String CONTEXTE_enUS_Modifiee = "modified ";

	String CONTEXTE_frFR_LMasculinVoyelle = "l'";

	String CONTEXTE_frFR_LFemininVoyelle = "l'";

	String CONTEXTE_enUS_LVoyelle = "the";

	String CONTEXTE_frFR_LeMasculinConsonne = "le ";

	String CONTEXTE_frFR_LaFemininConsonne = "la ";

	String CONTEXTE_enUS_LaConsonne = "the ";

	String CONTEXTE_frFR_LesPluriel = "les ";

	String CONTEXTE_enUS_LesPluriel = "the ";

	String CONTEXTE_frFR_Rechercher = "rechercher ";

	String CONTEXTE_enUS_Rechercher = "search ";

	String CONTEXTE_frFR_Par = " par ";

	String CONTEXTE_enUS_Par = " by ";

	String CONTEXTE_frFR_ActuelMasculinAvant = "";

	String CONTEXTE_frFR_ActuelleFemininAvant = "";

	String CONTEXTE_enUS_ActuelleAvant = "current ";

	String CONTEXTE_frFR_ActuelMasculinApres = " actuel";

	String CONTEXTE_frFR_ActuelleFemininApres = " actuelle";

	String CONTEXTE_enUS_ActuelleApres = "";

	String CONTEXTE_frFR_TousMasculinPluriel = "tous les ";

	String CONTEXTE_frFR_ToutesFemininPluriel = "toutes les ";

	String CONTEXTE_enUS_ToutesPluriel = "all the ";

	String CONTEXTE_frFR_AucunTrouveMasculinAvant = "aucun ";

	String CONTEXTE_frFR_AucuneTrouveFemininAvant = "aucune ";

	String CONTEXTE_enUS_AucuneTrouveAvant = "no ";

	String CONTEXTE_frFR_AucunTrouveMasculinApres = " trouvé";

	String CONTEXTE_frFR_AucuneTrouveFemininApres = " trouvée";

	String CONTEXTE_enUS_AucuneTrouveApres = " found";

	String CONTEXTE_frFR_DVoyelle = "d'";

	String CONTEXTE_enUS_DVoyelle = "of ";

	String CONTEXTE_frFR_DeConsonne = "de ";

	String CONTEXTE_enUS_DeConsonne = "of ";

	String CONTEXTE_frFR_AdjectifPluriel = "s";

	String CONTEXTE_enUS_AdjectifPluriel = "";

	Boolean CONTEXTE_frFR_AdjectifAvant = false;

	Boolean CONTEXTE_enUS_AdjectifAvant = true;

	protected LinkedHashMap<String, ClassParts> classPartsGenApi = new LinkedHashMap<String, ClassParts>();

	protected LinkedHashMap<String, ClassParts> classPartsGenPage = new LinkedHashMap<String, ClassParts>();

	private String contextVideoId;

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

	private String contextSearchAllNameBy;

	private String contextSearchAllName;

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

	protected Boolean storeSolr(String langueNom, SolrInputDocument fieldName, String languageName, Boolean fieldValue) throws Exception, Exception {
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

	protected String storeSolr(String langueNom, SolrInputDocument fieldName, String languageName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String storeListSolr(String langueNom, SolrInputDocument fieldName, String languageName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> storeSolr(String langueNom, SolrInputDocument fieldName, String languageName, List<String> fieldValues) throws Exception, Exception {
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

	protected Boolean indexSolr(String langueNom, SolrInputDocument fieldName, String languageName, Boolean fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected String indexSolr(String langueNom, SolrInputDocument fieldName, String languageName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexListSolr(String langueNom, SolrInputDocument fieldName, String languageName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> indexSolr(String langueNom, SolrInputDocument fieldName, String languageName, List<String> fieldValues) throws Exception, Exception {
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

	protected Long indexStoreSolr(String langueNom, SolrInputDocument fieldName, String fieldValue, Long valeurChamp) throws Exception, Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(fieldName, "_", langueNom, "_stored_long"), fieldValue);
			doc.addField(concat(fieldName, "_", langueNom, "_indexed_long"), fieldValue);
		}
		return fieldValue;
	}

	protected Double indexStoreSolr(String langueNom, SolrInputDocument fieldName, String fieldValue, Double valeurChamp) throws Exception, Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(fieldName, "_", langueNom, "_stored_double"), fieldValue);
			doc.addField(concat(fieldName, "_", langueNom, "_indexed_double"), fieldValue);
		}
		return fieldValue;
	}

	protected Integer indexStoreSolr(String langueNom, SolrInputDocument fieldName, String fieldValue, Integer valeurChamp) throws Exception, Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(fieldName, "_", langueNom, "_stored_int"), fieldValue);
			doc.addField(concat(fieldName, "_", langueNom, "_indexed_int"), fieldValue);
		}
		return fieldValue;
	}

	protected Boolean indexStoreSolr(String langueNom, SolrInputDocument fieldName, String fieldValue, Boolean valeurChamp) throws Exception, Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(fieldName, "_", langueNom, "_stored_boolean"), fieldValue);
			doc.addField(concat(fieldName, "_", langueNom, "_indexed_boolean"), fieldValue);
		}
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

	protected String indexStoreSolrRegex(String langueNom, SolrInputDocument languageName, String fieldName, String fieldNameRegex, String comment) throws Exception, Exception {
		return indexStoreSolrRegex(languageName, doc, fieldName, fieldNameRegex, comment, null);
	}

	protected String indexStoreSolrRegex(String langueNom, SolrInputDocument languageName, String fieldName, String fieldNameRegex, String comment, String defaultValue) throws Exception, Exception {
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

	protected String indexStoreSolrRegex(SolrInputDocument doc, String fieldName, String fieldNameRegex, String comment) throws Exception, Exception {
		return indexStoreSolrRegex(doc, fieldName, fieldNameRegex, comment, null);
	}

	protected String indexStoreSolrRegex(SolrInputDocument doc, String fieldName, String fieldNameRegex, String comment, String defaultValue) throws Exception, Exception {
		String fieldValue = null;
		if(fieldNameRegex != null && comment != null) {
			Matcher m = Pattern.compile("^" + fieldNameRegex + ":\\s*(.*)", Pattern.MULTILINE).matcher(comment);
			if(m.find()) {
				fieldValue = m.group(1);
			}
		}
		if(StringUtils.isBlank(fieldValue)) {
			fieldValue = defaultValue;
		}
		if(StringUtils.isNotBlank(fieldValue)) {
			doc.addField(concat(fieldName, "_stored_string"), fieldValue);
			doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexStoreListSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_stored_strings"), fieldValue);
		doc.addField(concat(fieldName, "_indexed_strings"), fieldValue);
		return fieldValue;
	}

	protected String indexStoreSolr(String langueNom, SolrInputDocument fieldName, String languageName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexStoreListSolr(String langueNom, SolrInputDocument fieldName, String languageName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> indexStoreSolr(String langueNom, SolrInputDocument fieldName, String languageName, List<String> fieldValues) throws Exception, Exception {
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

	protected ClassParts classPartsForSimpleName(String domainPackageName, String simpleName, String classeLangueNom) throws Exception, Exception {
		ClassParts classParts = null;
		SolrDocument doc = null;
		SolrQuery solrSearch = new SolrQuery();   
		solrSearch.setQuery("*:*");
		solrSearch.setRows(1);
		solrSearch.addFilterQuery("classeMotsCles_indexed_strings:" + ClientUtils.escapeQueryChars("classSimpleName" + simpleName));
		solrSearch.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
		solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
		QueryResponse searchResponse = solrClientComputate.query(solrSearch);
		SolrDocumentList searchList = searchResponse.getResults();
		if(searchList.size() > 0) {
			doc = searchList.get(0);
			String nomCanonique = (String)doc.get("classCanonicalName_" + classeLangueNom + "_stored_string");
			classParts = ClassParts.initClassParts(this, nomCanonique, classeLangueNom);
		}
		return classParts;
	}

	protected ClassParts classPartsWrap(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "Wrap", classeLangueNom);
	}

	protected ClassParts classPartsChain(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "Chain", classeLangueNom);
	}

	protected ClassParts classPartsSiteRequest(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteRequest", classeLangueNom);
	}

	protected ClassParts classPartsSiteContext(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteContext", classeLangueNom);
	}

	protected ClassParts classPartsSiteConfig(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteConfig", classeLangueNom);
	}

	protected ClassParts classPartsSiteUser(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteUser", classeLangueNom);
	}

	protected ClassParts classPartsCluster(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "Cluster", classeLangueNom);
	}

	protected ClassParts classPartsSearchResult(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SearchResult", classeLangueNom);
	}

	protected ClassParts classPartsAllWriter(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "AllWriter", classeLangueNom);
	}

	protected ClassParts classPartsSearchList(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SearchList", classeLangueNom);
	}

	protected ClassParts classPartsPageLayout(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "PageLayout", classeLangueNom);
	}

	protected ClassParts classPartsPagePart(String domainPackageName, String classeLangueNom) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "PagePart", classeLangueNom);
	}

	public String storeRegexComments(String comment, SolrInputDocument languageName, String varEntite, String entityVar) throws Exception, Exception {
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
				storeSolr(languageName, doc, entityVar, b.toString());
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

	public SolrInputDocument indexClass(String classAbsolutePath, SolrInputDocument classDoc, String classeLangueNom) throws Exception, Exception { 

		String[] classOtherLanguages = ArrayUtils.removeAllOccurences(toutesLangues, classLangueNom);
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
			classSuperCompleteName = indexStoreSolr(classLangueNom, classDoc, "classSuperCompleteName", classSuperQdox.getGenericCanonicalName());
		} catch (Exception e) {
			if(classSuperQdox != null && classSuperQdox.getGenericFullyQualifiedName().contains("<"))
				classSuperCompleteName = indexStoreSolr(classLangueNom, classDoc, "classSuperCompleteName", classSuperQdox.getGenericFullyQualifiedName());
		}
		for(JavaClass cImplements : classQdox.getImplementedInterfaces()) {
			ClassParts classPartsImplements = ClassParts.initClassParts(this, cImplements, classLangueNom);
			indexStoreListSolr(classLangueNom, classDoc, "classImplementsSimpleNameComplete", classPartsImplements.simpleNameComplete);
		}

		String classSuperCompleteNameGeneric = StringUtils.substringBeforeLast(StringUtils.substringAfter(classSuperCompleteName, "<"), ">");
		String classSuperCanonicalNameGeneric = null;
		String classSuperSimpleNameGeneric = null;
		Boolean baseClassExtendsGen = false;
		if(StringUtils.isNotEmpty(classSuperCompleteName)) {
			indexStoreSolr(classLangueNom, classDoc, "classSuperCompleteNameGeneric", classSuperCompleteNameGeneric);
			if(classSuperCompleteName.contains("<")) {
				classSuperCanonicalNameGeneric = StringUtils.substringAfter(StringUtils.substringBeforeLast(classSuperCompleteName, ">"), "<");
				classSuperCanonicalNameGeneric = classSuperCanonicalNameGeneric.contains(",") ? StringUtils.substringBefore(classSuperCanonicalNameGeneric, ",") : classSuperCanonicalNameGeneric;
				indexStoreSolr(classLangueNom, classDoc, "classSuperCanonicalNameGeneric", classSuperCanonicalNameGeneric);
				classSuperCompleteNameGeneric = classSuperCanonicalNameGeneric;

				if(classSuperCanonicalNameGeneric.contains("."))
					classSuperSimpleNameGeneric = StringUtils.substringAfterLast(classSuperCanonicalNameGeneric, ".");
				else
					classSuperSimpleNameGeneric = classSuperCanonicalNameGeneric;
				indexStoreSolr(classLangueNom, classDoc, "classSuperSimpleNameGeneric", classSuperSimpleNameGeneric);

				ClassParts classPartsBase = ClassParts.initClassParts(this, classSuperCanonicalNameGeneric, classLangueNom, classLangueNom);
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
			indexStoreSolr(classDoc, "classContainsSiteRequest", classQdox.getMethodBySignature("getRequestSite_", new ArrayList<JavaType>(), true) != null);
		} catch (Exception e) {
			// TODO ctate fix this to pull from solr. 
		}
		
		String classComment = storeRegexComments(classLangueNom, classDoc, "classComment", classQdox.getComment());
		String classPackageName = StringUtils.substringBeforeLast(classCanonicalName, ".");
		String classPath = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), ".java");
		String classDirPath = StringUtils.substringBeforeLast(classPath, "/");
		String classPathGen = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalName, ".", "/"), "Gen.java");

		String classDirPathGen = StringUtils.substringBeforeLast(classPathGen, "/");
		String classKey = classAbsolutePath;
		Instant modified = Instant.now();
		Date modifiedDate = Date.from(modified);
		Boolean classContainsWrap = false;

		Boolean classTranslate = indexStoreSolr(classDoc, "classTranslate", !regexFound("^(class)?Translate: \\s*(false)$", classComment));
		Boolean classExtendsGen = StringUtils.endsWith(classSuperSimpleName, "Gen");
		if(superClassError || !classExtendsGen && regexFound("^(class)?Gen:\\s*(true)$", classComment)) {
			classExtendsGen = true;
		}
		if(regexFound("^(class)?Gen:\\s*(false)$", classComment) || classQdox.isInterface())
			classExtendsGen = false;

		if(classTranslate) {
			for(String languageName : classOtherLanguages) {
				String appPathLanguage = appPaths.get(languageName);
				storeRegexComments(languageName, classDoc, "classComment", classComment);
				String srcMainJavaPathLanguage = appPathLanguage + "/src/main/java";
				String srcGenJavaPathLanguage = appPathLanguage + "/src/gen/java";
				String classCanonicalNameLanguage = regex("^(class)?NomCanonique\\." + languageName + ":\\s*(.*)", classComment);
	
				if(classCanonicalNameLanguage == null)
					classCanonicalNameLanguage = classCanonicalName.replace(this.languageName, languageName);
				String classSimpleNameLanguage = StringUtils.substringAfterLast(classCanonicalNameLanguage, ".");
				String classPackageNameLanguage = StringUtils.substringBeforeLast(classCanonicalNameLanguage, ".");
				String classCanonicalNameGenLanguage = classCanonicalNameLanguage + "Gen";
				String classSimpleNameGenLanguage = classSimpleNameLanguage + "Gen";
				String classPathLanguage = indexStoreSolr(languageName, classDoc, "classPath", concat(srcMainJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameLanguage, ".", "/"), ".java"));
				String classDirPathLanguage = storeSolr(languageName, classDoc, "classDirPath", StringUtils.substringBeforeLast(classPathLanguage, "/"));
				String classPathGenLanguage = indexStoreSolr(languageName, classDoc, "classPathGen", concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameGenLanguage, ".", "/"), ".java"));
				String classDirPathGenLanguage = storeSolr(languageName, classDoc, "classDirPathGen", StringUtils.substringBeforeLast(classPathGenLanguage, "/"));
	
				indexStoreSolr(languageName, classDoc, "classCanonicalName", classCanonicalNameLanguage); 
				indexStoreSolr(languageName, classDoc, "classSimpleName", classSimpleNameLanguage); 
				indexStoreSolr(languageName, classDoc, "classCanonicalNameGen", classCanonicalNameGenLanguage); 
				indexStoreSolr(languageName, classDoc, "classSimpleNameGen", classSimpleNameGenLanguage); 
				indexStoreSolr(languageName, classDoc, "classPackageName", classPackageNameLanguage); 
	
				String classSuperCompleteNameLanguage;
				ClassParts classSuperPartsLanguage = null;
	
				if(classExtendsGen) {
					classSuperPartsLanguage = ClassParts.initClassParts(this, classCanonicalNameLanguage + "Gen", languageName);
				}
				else if(classSuperQdox != null) {
					classSuperPartsLanguage = ClassParts.initClassParts(this, classSuperQdox, languageName);
				}
	
				if(classSuperPartsLanguage != null) {
					indexStoreSolr(languageName, classDoc, "classSuperCanonicalName", classSuperPartsLanguage.canonicalName); 
					indexStoreSolr(languageName, classDoc, "classSuperSimpleName", classSuperPartsLanguage.simpleName); 
					indexStoreSolr(languageName, classDoc, "classCanonicalNameCompletSuper", classSuperPartsLanguage.canonicalNameComplete);
					indexStoreSolr(languageName, classDoc, "classSimpleNameCompletSuper", classSuperPartsLanguage.simpleNameComplete);
					if(StringUtils.isNotEmpty(classSuperCompleteNameGeneric)) {
						ClassParts classPartsSuperGenericLanguage = ClassParts.initClassParts(this, classSuperCompleteNameGeneric, languageName);
						indexStoreSolr(languageName, classDoc, "classSuperCanonicalNameGeneric", classPartsSuperGenericLanguage.canonicalNameComplete);
						indexStoreSolr(languageName, classDoc, "classSuperSimpleNameGeneric", classPartsSuperGenericLanguage.simpleNameComplete);
					}
				}
				for(JavaClass cImplements : classQdox.getImplementedInterfaces()) {
					ClassParts classPartsImplements = ClassParts.initClassParts(this, cImplements, languageName);
					indexStoreListSolr(languageName, classDoc, "classImplementsSimpleNameComplete", classPartsImplements.simpleNameComplete);
				}
			}
		}

		classPartsSolrInputDocument = ClassParts.initClassParts(this, "org.apache.solr.common.SolrInputDocument", classLangueNom);
		classPartsSolrDocument = ClassParts.initClassParts(this, "org.apache.solr.common.SolrDocument", classLangueNom);
		classPartsSolrClient = ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrClient", classLangueNom);
		classPartsTest = ClassParts.initClassParts(this, "org.junit.Test", classLangueNom);
		classPartsList = ClassParts.initClassParts(this, List.class.getCanonicalName(), classLangueNom);
		classPartsArrayList = ClassParts.initClassParts(this, ArrayList.class.getCanonicalName(), classLangueNom);
		classPartsSiteContext = classPartsSiteContext(domainPackageName, classLangueNom);
		classPartsSiteConfig = classPartsSiteConfig(domainPackageName, classLangueNom);
		classPartsSiteUser = classPartsSiteUser(domainPackageName, classLangueNom);
		classPartsCluster = classPartsCluster(domainPackageName, classLangueNom);
		classPartsSearchResult = classPartsSearchResult(domainPackageName, classLangueNom);
		classPartsAllWriter = classPartsAllWriter(domainPackageName, classLangueNom);
		classPartsSearchList = classPartsSearchList(domainPackageName, classLangueNom);
		classPartsWrap = classPartsWrap(domainPackageName, classLangueNom);
		classPartsPageLayout = classPartsPageLayout(domainPackageName, classLangueNom);
		classPartsPagePart = classPartsPagePart(domainPackageName, classLangueNom);
		classPartsChain = classPartsChain(domainPackageName, classLangueNom);
		classPartsSiteRequest = classPartsSiteRequest(domainPackageName, classLangueNom);

		Boolean classInitDeep = !regexFound("^(class)?InitLoin:\\s*(false)$", classComment);
		if(classInitDeep)
			classInitDeep = classExtendsBase || classIsBase;
		classInitDeep = storeSolr(classDoc, "classInitDeep", classInitDeep);
		if(classInitDeep)
			classPartsGenAdd(classPartsSiteRequest);
		indexStoreSolr(classDoc, "classExtendsGen", classExtendsGen);

		indexStoreSolr(classDoc, "languageName", classLangueNom); 
		indexStoreSolr(classDoc, "modified", modifiedDate); 
		indexStoreSolr(classLangueNom, classDoc, "classCanonicalName", classCanonicalName); 
		indexStoreSolr(classLangueNom, classDoc, "classSimpleName", classSimpleName); 
		indexStoreSolr(classLangueNom, classDoc, "classPackageName", classPackageName); 
		indexStoreSolr(classLangueNom, classDoc, "classCanonicalNameGen", classCanonicalNameGen); 
		indexStoreSolr(classLangueNom, classDoc, "classSimpleNameGen", classSimpleNameGen); 
		indexStoreSolr(classLangueNom, classDoc, "classSuperCanonicalName", classSuperCanonicalName); 
		indexStoreSolr(classLangueNom, classDoc, "classSuperSimpleName", classSuperSimpleName); 
		indexStoreSolr(classDoc, "classAbsolutePath", classAbsolutePath);
		indexStoreSolr(classLangueNom, classDoc, "classPath", classPath); 
		indexStoreSolr(classLangueNom, classDoc, "classDirPath", classDirPath);  
		indexStoreSolr(classLangueNom, classDoc, "classPathGen", classPathGen); 
		indexStoreSolr(classLangueNom, classDoc, "classDirPathGen", classDirPathGen); 
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

		indexStoreSolr(classDoc, "classIsAbstract", classQdox.isAbstract()); 
		Boolean classModel = indexStoreSolr(classDoc, "classModel", regexFound("^(class)?Model: \\s*(true)$", classComment));
		Boolean classApi = indexStoreSolr(classDoc, "classApi", regexFound("^(class)?Api: \\s*(true)$", classComment) || classModel);
		Boolean classPage = regexFound("^(class)?Page: \\s*(true)$", classComment);
		Boolean classPageSimple = indexStoreSolr(classDoc, "classPageSimple", regexFound("^(class)?PageSimple: \\s*(true)$", classComment));
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
		String classSimpleNamePage = indexStoreSolr(classLangueNom, classDoc, "classSimpleNamePage", classSimpleName + "Page");
		String classSimpleNameGenPage = indexStoreSolr(classLangueNom, classDoc, "classSimpleNameGenPage", classSimpleName + "PageGen");

		String classPathApiPackageInfo;
		String classPathGenApiServiceImpl;
		String classPathApiServiceImpl;
		String classPathGenApiService;

		if(classApi) {
			classSimpleNameApiPackageInfo = indexStoreSolr(classLangueNom, classDoc, "classSimpleNameApiPackageInfo", "package-info");
			classSimpleNameGenApiServiceImpl = indexStoreSolr(classLangueNom, classDoc, "classSimpleNameGenApiServiceImpl", classSimpleName + StringUtils.capitalize(classLangueNom) + "GenApiServiceImpl");
			classSimpleNameApiServiceImpl = indexStoreSolr(classLangueNom, classDoc, "classSimpleNameApiServiceImpl", classSimpleName + StringUtils.capitalize(classLangueNom) + "ApiServiceImpl");
			classSimpleNameGenApiService = indexStoreSolr(classLangueNom, classDoc, "classSimpleNameGenApiService", classSimpleName + StringUtils.capitalize(classLangueNom) + "GenApiService");

			classCanonicalNameApiPackageInfo = indexStoreSolr(classLangueNom, classDoc, "classCanonicalNameApiPackageInfo", classPackageName + "." + classSimpleNameApiPackageInfo);
			classCanonicalNameGenApiServiceImpl = indexStoreSolr(classLangueNom, classDoc, "classCanonicalNameGenApiServiceImpl", classPackageName + "." + classSimpleNameGenApiServiceImpl);
			classCanonicalNameApiServiceImpl = indexStoreSolr(classLangueNom, classDoc, "classCanonicalNameApiServiceImpl", classPackageName + "." + classSimpleNameApiServiceImpl);
			classCanonicalNameGenApiService = indexStoreSolr(classLangueNom, classDoc, "classCanonicalNameGenApiService", classPackageName + "." + classSimpleNameGenApiService);

			classPathApiPackageInfo = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalNameApiPackageInfo, ".", "/"), ".java");
			classPathGenApiServiceImpl = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalNameGenApiServiceImpl, ".", "/"), ".java");
			classPathApiServiceImpl = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalNameApiServiceImpl, ".", "/"), ".java");
			classPathGenApiService = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalNameGenApiService, ".", "/"), ".java");

			indexStoreSolr(classLangueNom, classDoc, "classPathApiPackageInfo", classPathApiPackageInfo); 
			indexStoreSolr(classLangueNom, classDoc, "classPathGenApiServiceImpl", classPathGenApiServiceImpl); 
			indexStoreSolr(classLangueNom, classDoc, "classPathApiServiceImpl", classPathApiServiceImpl); 
			indexStoreSolr(classLangueNom, classDoc, "classPathGenApiService", classPathGenApiService); 
		}

		if(classTranslate) {
			for(String languageName : classOtherLanguages) {
				String appPathLanguage = appPaths.get(languageName);
				String srcMainJavaPathLanguage = appPathLanguage + "/src/main/java";
				String srcGenJavaPathLanguage = appPathLanguage + "/src/gen/java";
				String classCanonicalNameLanguage = (String)classDoc.get("classCanonicalName_" + languageName + "_indexed_string").getValue();
				String classSimpleNameLanguage = (String)classDoc.get("classSimpleName_" + languageName + "_indexed_string").getValue();
				String classPackageNameLanguage = (String)classDoc.get("classPackageName_" + languageName + "_indexed_string").getValue();
	//			String classCanonicalNameGenLanguage = (String)classDoc.get("classCanonicalNameGen_" + languageName + "_indexed_string").getValue();
	//			String classSimpleNameGenLanguage = (String)classDoc.get("classSimpleNameGen_" + languageName + "_indexed_string").getValue();
	
				String classSimpleNameApiLangue = classSimpleNameLanguage + "Api";
				String classSimpleNamePageLanguage = classSimpleNameLanguage + "Page";
				String classSimpleNameGenPageLangue = classSimpleNameLanguage + "PageGen";
				String classCanonicalNameApiLanguage = classCanonicalNameLanguage + "Api";
				String classCanonicalNameApiGenLanguage = classCanonicalNameLanguage + "ApiGen";
				String classCanonicalNamePageLanguage = classCanonicalNameLanguage + "Page";
				String classCanonicalNamePageGenLanguage = classCanonicalNameLanguage + "PageGen";
				String classPathApiGenLangue = indexStoreSolr(languageName, classDoc, "classPathApiGen", concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNameApiGenLanguage, ".", "/"), ".java"));
				String classPathGenPageLangue = indexStoreSolr(languageName, classDoc, "classPathGenPage", concat(srcGenJavaPathLanguage, "/", StringUtils.replace(classCanonicalNamePageGenLanguage, ".", "/"), ".java"));
				indexStoreSolr(languageName, classDoc, "classSimpleNameApi", classSimpleNameApiLangue); 
				indexStoreSolr(languageName, classDoc, "classSimpleNamePage", classSimpleNamePageLanguage); 
				indexStoreSolr(languageName, classDoc, "classSimpleNameGenPage", classSimpleNameGenPageLangue); 
	
				if(classApi) {
					String classSimpleNameApiPackageInfoLangue = indexStoreSolr(languageName, classDoc, "classSimpleNameApiPackageInfo", "package-info");
					String classSimpleNameGenApiServiceImplLangue = indexStoreSolr(languageName, classDoc, "classSimpleNameGenApiServiceImpl", classSimpleNameLanguage + StringUtils.capitalize(languageName) + "GenApiServiceImpl");
					String classSimpleNameApiServiceImplLangue = indexStoreSolr(languageName, classDoc, "classSimpleNameApiServiceImpl", classSimpleNameLanguage + StringUtils.capitalize(languageName) + "ApiServiceImpl");
					String classSimpleNameGenApiServiceLangue = indexStoreSolr(languageName, classDoc, "classSimpleNameGenApiService", classSimpleNameLanguage + StringUtils.capitalize(languageName) + "GenApiService");
		
					String classCanonicalNameApiPackageInfoLangue = indexStoreSolr(languageName, classDoc, "classCanonicalNameApiPackageInfo", classPackageNameLanguage + "." + classSimpleNameApiPackageInfoLangue);
					String classCanonicalNameGenApiServiceImplLangue = indexStoreSolr(languageName, classDoc, "classCanonicalNameGenApiServiceImpl", classPackageNameLanguage + "." + classSimpleNameGenApiServiceImplLangue);
					String classCanonicalNameApiServiceImplLangue = indexStoreSolr(languageName, classDoc, "classCanonicalNameApiServiceImpl", classPackageNameLanguage + "." + classSimpleNameApiServiceImplLangue);
					String classCanonicalNameGenApiServiceLangue = indexStoreSolr(languageName, classDoc, "classCanonicalNameGenApiService", classPackageNameLanguage + "." + classSimpleNameGenApiServiceLangue);
		
					String classPathApiPackageInfoLangue = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalNameApiPackageInfoLangue, ".", "/"), ".java");
					String classPathGenApiServiceImplLangue = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalNameGenApiServiceImplLangue, ".", "/"), ".java");
					String classPathApiServiceImplLangue = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalNameApiServiceImplLangue, ".", "/"), ".java");
					String classPathGenApiServiceLangue = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalNameGenApiServiceLangue, ".", "/"), ".java");
		
					indexStoreSolr(languageName, classDoc, "classPathApiPackageInfo", classPathApiPackageInfoLangue); 
					indexStoreSolr(languageName, classDoc, "classPathGenApiServiceImpl", classPathGenApiServiceImplLangue); 
					indexStoreSolr(languageName, classDoc, "classPathApiServiceImpl", classPathApiServiceImplLangue); 
					indexStoreSolr(languageName, classDoc, "classPathGenApiService", classPathGenApiServiceLangue); 
				}
			}
		}

		if(classApi) {
			classPartsGenApiAdd(classPartsSiteConfig);
			classPartsGenApiAdd(classPartsSiteRequest);
			classPartsGenApiAdd(classPartsSiteContext);
			classPartsGenApiAdd(classPartsSiteUser);
			classPartsGenApiAdd(classPartsSearchResult);
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.io.IOException", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.servlet.http.HttpServlet", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.servlet.http.HttpServerRequest", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.servlet.http.HttpServerResponse", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Collections", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Map", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.servlet.ServletException", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.concurrent.TimeUnit", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.stream.Collectors", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.json.Json", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.json.JsonArray", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.json.JsonObject", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.json.JsonReader", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrQuery", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrQuery.ORDER", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.response.QueryResponse", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.util.ClientUtils", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.StringUtils", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.keycloak.KeycloakPrincipal", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.keycloak.KeycloakSecurityContext", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.keycloak.representations.AccessToken", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.keycloak.representations.AccessToken.Access", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.security.Principal", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.mail.Message", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.mail.Session", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.mail.Transport", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.mail.internet.InternetAddress", classLangueNom));
//			classPartsGenApiAdd(ClassParts.initClassParts(this, "javax.mail.internet.MimeMessage", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.io.PrintWriter", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.common.SolrDocumentList", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.common.SolrDocument", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Collection", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.math.BigDecimal", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Date", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.time.ZoneId", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.List", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.ArrayList", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Arrays", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Set", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Handler", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.RoutingContext", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.Router", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Vertx", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.reactivestreams.ReactiveReadStream", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.reactivestreams.ReactiveWriteStream", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.MultiMap", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.auth.oauth2.OAuth2Auth", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.netty.handler.codec.http.HttpResponseStatus", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.Logger", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.LoggerFactory", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.validation.HTTPRequestValidationHandler", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.validation.ParameterTypeValidator", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.validation.ValidationException", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLClient", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLConnection", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonArray", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonObject", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.time.LocalDateTime", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.sql.Timestamp", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Future", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.http.CaseInsensitiveHeaders", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.AsyncResult", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Handler", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.buffer.Buffer", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.OperationResponse", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.CompositeFuture", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.http.client.utils.URLEncodedUtils", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.nio.charset.Charset", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.http.NameValuePair", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.OperationRequest", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.auth.oauth2.KeycloakHelper", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLConnection", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Optional", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.stream.Stream", classLangueNom));
			classPartsGenApiAdd(ClassParts.initClassParts(this, "java.net.URLDecoder", classLangueNom));
			classPartsGenApiAdd(classPartsSearchList);
			classPartsGenApiAdd(classPartsAllWriter);
		}
		if(classExtendsBase || classIsBase) {
			classPartsGenAdd(classPartsCluster);
			classPartsGenAdd(classPartsAllWriter);
		}
		if(classSaved) {
			classPartsGenAdd(classPartsSiteContext);
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.Logger", classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.LoggerFactory", classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLClient", classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLConnection", classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonObject", classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, VAL_canonicalNameDate, classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, "java.util.Set", classLangueNom));
		}
		classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonArray", classLangueNom));
		classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.commons.text.StringEscapeUtils", classLangueNom));
		classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.StringUtils", classLangueNom));
		classPartsGenAdd(ClassParts.initClassParts(this, "java.util.Objects", classLangueNom));

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
				storeListSolr(classRoleLanguage, classDoc, "classRoles", classRoleValue);
				classRolesFound = true;
				classRolesFoundCurrent = classRolesSearch.find();
			}
			indexStoreSolr(classDoc, "classRolesFound", classRolesFound); 

			Matcher classFiltersSearch = Pattern.compile("^(class)?Filtre:\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classComment);
			boolean classFiltersFound = classFiltersSearch.find();
			boolean classFiltersFoundCurrent = classFiltersFound;
			while(classFiltersFoundCurrent) {
				String classFilterValue = classFiltersSearch.group(2);
				storeListSolr(classDoc, "classFilters", classFilterValue);
				classFiltersFound = true;
				classFiltersFoundCurrent = classFiltersSearch.find();
			}
			indexStoreSolr(classDoc, "classFiltersFound", classFiltersFound); 

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
			ClassParts classPartsSuperGeneric = ClassParts.initClassParts(this, classSuperCompleteNameGeneric, classLangueNom);
			classPartsGenAdd(classPartsSuperGeneric);

			if(StringUtils.startsWith(classSuperCanonicalName, domainPackageName)) {
				SolrQuery solrSearch = new SolrQuery();   
				solrSearch.setQuery("*:*");
				solrSearch.setRows(1);
				solrSearch.addFilterQuery("classCanonicalName_" + classLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classSuperCanonicalNameGeneric));
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
				solrSearch.addFilterQuery("classCanonicalName_" + classLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classSuperCanonicalNameGeneric));
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
			ClassParts classImportClassParts = ClassParts.initClassParts(this, classImport, classLangueNom);
			indexStoreListSolr(classLangueNom, classDoc, "classImports", classImportClassParts.canonicalName(classLangueNom));

			for(String languageName : classOtherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classImportClassParts, languageName);
				indexStoreListSolr(languageName, classDoc, "classImports", classImportClassPartsLanguage.canonicalName(languageName));
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
				String fieldString = regex("^(champ)?String\\." + classLangueNom + ":(.*)", fieldComment);
				if(fieldString != null) {
					fieldSourceCode = "\"" + StringUtils.replace(StringUtils.replace(fieldString, "\\", "\\\\"), "\"", "\\\"") + "\"";
					indexStoreSolr(classLangueNom, fieldDoc, "fieldString", fieldString); 
				}

				// Champs Solr du champ. 

				indexStoreSolr(classLangueNom, fieldDoc, "fieldVar", fieldVar); 
				indexStoreSolr(fieldDoc, "partIsField", true);
				indexStoreSolr(fieldDoc, "partNumber", partNumber);
				indexStoreSolr(fieldDoc, "fieldIsPublic", fieldQdox.isPublic()); 
				indexStoreSolr(fieldDoc, "fieldIsProtected", fieldQdox.isProtected()); 
				indexStoreSolr(fieldDoc, "fieldIsPrivate", fieldQdox.isPrivate()); 
				indexStoreSolr(fieldDoc, "fieldIsStatic", fieldQdox.isStatic()); 
				indexStoreSolr(fieldDoc, "fieldIsFinal", fieldQdox.isFinal()); 
				indexStoreSolr(fieldDoc, "fieldIsAbstract", fieldQdox.isAbstract()); 
				indexStoreSolr(fieldDoc, "fieldIsNative", fieldQdox.isNative()); 
				indexStoreSolr(fieldDoc, "champTranslate", !regexFound("^(champ)?Translate: \\s*(false)$", fieldComment));
	
				/////////////////
				// Annotations //
				/////////////////
				List<JavaAnnotation> annotations = fieldQdox.getAnnotations(); 
				ArrayList<String> annotationsLanguage = new ArrayList<String>();
				Boolean fieldIsTest = false;
				Boolean fieldIsOverride = false;
				for(JavaAnnotation annotation : annotations) {
					String fieldAnnotationLanguage = annotation.getType().getCanonicalName();
					indexStoreSolr(classLangueNom, fieldDoc, "champAnnotation", fieldAnnotationLanguage); 

					if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
						fieldIsTest = true;
					}
					if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
						fieldIsOverride = true;
					}
				}
				indexStoreSolr(fieldDoc, "fieldIsTest", fieldIsTest); 
				indexStoreSolr(fieldDoc, "fieldIsOverride", fieldIsOverride); 

				ClassParts fieldClassParts = ClassParts.initClassParts(this, fieldQdox.getType(), classLangueNom);
	
				storeRegexComments(classLangueNom, fieldDoc, "fieldComment", fieldComment);
				storeSolr(classLangueNom, fieldDoc, "fieldSimpleNameComplete", fieldClassParts.simpleNameComplete);
				String fieldCanonicalNameComplete = storeSolr(classLangueNom, fieldDoc, "fieldCanonicalNameComplete", fieldClassParts.canonicalNameComplete);
				storeSolr(classLangueNom, fieldDoc, "fieldSourceCode", fieldSourceCode);
				fieldDoc.addField("id", fieldCanonicalNameComplete + " " + fieldKey);

				if(classTranslate) {
					for(String languageName : classOtherLanguages) { 
						ClassParts fieldClassPartsLanguage = ClassParts.initClassParts(this, fieldClassParts, languageName);
						String fieldVarLanguage = regex("^(champ)?Var\\." + languageName + ": (.*)", fieldComment);
						fieldVarLanguage = fieldVarLanguage == null ? fieldVar : fieldVarLanguage;
						String fieldSourceCodeLanguage = regexReplaceAll(fieldComment, fieldSourceCode, languageName);
						String fieldStringLanguage = regex("^(champ)?String\\." + languageName + ":(.*)", fieldComment);
						if(fieldStringLanguage != null) {
							fieldSourceCodeLanguage = "\"" + StringUtils.replace(StringUtils.replace(fieldStringLanguage, "\\", "\\\\"), "\"", "\\\"") + "\"";
							indexStoreSolr(languageName, fieldDoc, "fieldString", fieldString); 
						}
	
						indexStoreSolr(languageName, fieldDoc, "fieldVar", fieldVarLanguage); 
						storeSolr(languageName, fieldDoc, "fieldSimpleNameComplete", fieldClassPartsLanguage.simpleNameComplete);
						storeSolr(languageName, fieldDoc, "fieldCanonicalNameComplete", fieldClassPartsLanguage.canonicalNameComplete);
						storeRegexComments(languageName, fieldDoc, "fieldComment", fieldComment);
						storeSolr(languageName, fieldDoc, "fieldSourceCode", fieldSourceCodeLanguage);
					}  
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
					storeListSolr(classLangueNom, constructorDoc, "constructorParamsVar", constructorParamVar);
					ClassParts constructorParamClassParts = ClassParts.initClassParts(this, constructorParamQdox.getJavaClass(), classLangueNom);
					classPartsGenAdd(constructorParamClassParts);
					storeListSolr(classLangueNom, constructorDoc, "constructorParamsSimpleNameComplete", constructorParamClassParts.simpleNameComplete);
					storeListSolr(constructorDoc, "constructorParamsVariableArgs", constructorParamQdox.isVarArgs());
					if(classTranslate) {
						for(String languageName : classOtherLanguages) { 
							String constructorParamVarLanguage = regex("^(constructeur)?Param" + constructorParamNum + "\\.var\\." + languageName + ": (.*)", constructorComment);
							if(constructorParamVarLanguage == null)
								constructorParamVarLanguage = constructorParamVar;
							ClassParts constructorParamClassPartsLanguage = ClassParts.initClassParts(this, constructorParamClassParts, languageName);
	
							classPartsGenAdd(constructorParamClassPartsLanguage);
							storeListSolr(languageName, constructorDoc, "constructorParamsSimpleNameComplete", constructorParamClassPartsLanguage.simpleNameComplete);
							storeListSolr(languageName, constructorDoc, "constructorParamsVar", constructorParamVarLanguage);
						}  
					}
				}
				for(JavaAnnotation constructorAnnotation : constructorAnnotations) {
//					String constructorAnnotationCodeBlock = storeListSolr(languageName, constructorDoc, "constructorAnnotationCodeBlock", annotation.toString());
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
				storeRegexComments(classLangueNom, constructorDoc, "constructorComment", constructorComment);

				String constructorSourceCode = constructorQdox.getSourceCode();
				String constructorSourceCodeLanguage = constructorSourceCode;
				ArrayList<String> replaceKeysLanguage = regexList("^r." + classLangueNom + "\\s*=\\s*(.*)\\n.*", constructorComment);
				ArrayList<String> replaceValuesLanguage = regexList("^r." + classLangueNom + "\\s*=\\s*.*\\n(.*)", constructorComment);
				for(int i = 0; i < replaceKeysLanguage.size(); i++) {
					String cle = replaceKeysLanguage.get(i);
					String valeur = replaceValuesLanguage.get(i);
					StringUtils.replace(constructorSourceCodeLanguage, cle, valeur);
				}
				storeSolr(classLangueNom, constructorDoc, "constructorSourceCode", constructorSourceCodeLanguage);

				if(classTranslate) {
					for(String languageName : classOtherLanguages) {  
						constructorSourceCodeLanguage = regexReplaceAll(constructorComment, constructorSourceCode, languageName);
						storeSolr(languageName, constructorDoc, "constructorSourceCode", constructorSourceCodeLanguage);
						storeRegexComments(languageName, constructorDoc, "constructorComment", constructorComment);
					} 
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
						String entityVar = indexStoreSolr(classLangueNom, entityDoc, "entityVar", StringUtils.substringAfter(methodQdox.getName(), "_"));
						indexStoreListSolr(classLangueNom, classDoc, "classEntityVars", entityVar);
						String entityVarCapitalized = indexStoreSolr(classLangueNom, entityDoc, "entityVarCapitalized", StringUtils.capitalize(entityVar));
						JavaClass entityClassQdox = methodParamsQdox.get(0).getJavaClass();
						ClassParts entityClassParts = ClassParts.initClassParts(this, entityClassQdox, classLangueNom, classLangueNom);
						Boolean entityWrap = false;

						if(entityClassParts.simpleName.equals("Wrap")) {
							entityClassParts = ClassParts.initClassParts(this, entityClassParts.canonicalNameGeneric, classLangueNom, classLangueNom);
							entityWrap = true;
							classContainsWrap = true;
						}

						classPartsGenAdd(entityClassParts);
						classPartsGenPageAdd(entityClassParts);
						List<String> entityCanonicalNamesSuperAndMeWithoutGen = new ArrayList<String>();
						if(StringUtils.isNotEmpty(entityClassParts.canonicalNameGeneric)) {
							ClassParts classPartsGeneric = ClassParts.initClassParts(this, entityClassParts.canonicalNameGeneric, classLangueNom);
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

						String entityCanonicalName = indexStoreSolr(classLangueNom, entityDoc, "entityCanonicalName", entityClassParts.canonicalName);
						String entitySimpleName = indexStoreSolr(classLangueNom, entityDoc, "entitySimpleName", entityClassParts.simpleName);
						String entityCompleteNameGeneric = indexStoreSolr(classLangueNom, entityDoc, "entityCompleteNameGeneric", entityClassParts.canonicalNameGeneric);
						String entityCanonicalNameGeneric = indexStoreSolr(classLangueNom, entityDoc, "entityCanonicalNameGeneric", entityClassParts.canonicalNameGeneric);
						String entitySimpleNameGeneric = indexStoreSolr(classLangueNom, entityDoc, "entitySimpleNameGeneric", entityClassParts.simpleNameGeneric);
						String entityCanonicalNameActuel = entityCanonicalNameGeneric == null ? entityCanonicalName : entityCanonicalNameGeneric;
						String entitySimpleNameActuel = entitySimpleNameGeneric == null ? entitySimpleName : entitySimpleNameGeneric;
						indexStoreSolr(classLangueNom, entityDoc, "entityCanonicalNameComplete", entityClassParts.canonicalNameComplete);
						indexStoreSolr(classLangueNom, entityDoc, "entitySimpleNameComplete", entityClassParts.simpleNameComplete);
						indexStoreSolr(classLangueNom, entityDoc, "entitySimpleNameCompleteGeneric", entityClassParts.simpleNameGeneric);

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
						indexStoreSolr(classLangueNom, entityDoc, "entityCanonicalNameBase", entityCanonicalNameBase);
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
						indexStoreSolr(classLangueNom, entityDoc, "entitySimpleNameBase", entitySimpleNameBase);
						
						String entityVarParam = methodParamsQdox.get(0).getName();
//						if(entityClassParts.canonicalName.equals(ArrayList.class.getCanonicalName()) || entityClassParts.canonicalName.equals(List.class.getCanonicalName()))
//							entityVarParam = "l";
//						else
//							entityVarParam = "o";
						indexStoreSolr(classLangueNom, entityDoc, "entityVarParam", entityVarParam);
						
						String entityVarWrap = indexStoreSolr(classLangueNom, entityDoc, "entityVarWrap", entityVar + "Wrap");

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
							solrSearchMethodBefore.addFilterQuery("methodVar_" + classLangueNom + "_indexed_string:" + fqMethodBefore);
							QueryResponse searchResponseMethodBefore = solrClientComputate.query(solrSearchMethodBefore);
							SolrDocumentList searchListMethodBefore = searchResponseMethodBefore.getResults();
	
							for(SolrDocument solrDocument : searchListMethodBefore) {
								String methodVarCurrent = (String)solrDocument.get("methodVar_" + classLangueNom + "_stored_string");
								String methodClassCanonicalName = (String)solrDocument.get("classCanonicalName_" + classLangueNom + "_stored_string");
								List<String> methodParamsSimpleNameComplete = (List<String>)solrDocument.get("methodParamsSimpleNameComplete_" + classLangueNom + "_stored_strings");
								String methodParamSimpleNameComplete = methodParamsSimpleNameComplete.get(0);
								List<String> methodParamsVar = (List<String>)solrDocument.get("methodParamsVar_" + classLangueNom + "_stored_strings");
								String methodeParamVar = methodParamsVar.get(0);
								storeListSolr(entityDoc, "entityMethodsBeforeVisibility", BooleanUtils.isTrue((Boolean)solrDocument.get("methodIsPublic_stored_boolean")) ? "public" : "protected");
								storeListSolr(entityDoc, "entityMethodsBeforeVar", methodVarCurrent);
								storeListSolr(entityDoc, "entityMethodsBeforeParamVar", methodeParamVar);
								storeListSolr(entityDoc, "entityMethodsBeforeSimpleName", methodParamSimpleNameComplete);
								storeListSolr(entityDoc, "entityMethodsBeforeParamName", methodParamsVar.size() > 1);
								Boolean entityMethodsBeforeWrite = (StringUtils.equals(methodClassCanonicalName, classCanonicalName)) && !classMethodsWritten.contains(methodVarCurrent);
								storeListSolr(entityDoc, "entityMethodsBeforeWrite", entityMethodsBeforeWrite);
								classMethodsWritten.add(methodVarCurrent);
								List<String> methodParamCanonicalNames = (List<String>)solrDocument.get("methodParamCanonicalNames_" + classLangueNom + "_stored_strings");
								if(methodParamCanonicalNames != null) {
									String methodParamCanonicalName = methodParamCanonicalNames.get(0);
									classPartsGenAdd(ClassParts.initClassParts(this, methodParamCanonicalName, classLangueNom));
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
							solrSearchMethodAfter.addFilterQuery("methodVar_" + classLangueNom + "_indexed_string:" + fqMethodAfter);
							QueryResponse searchResponseMethodAfter = solrClientComputate.query(solrSearchMethodAfter);
							SolrDocumentList searchListMethodAfter = searchResponseMethodAfter.getResults();
	
							for(SolrDocument solrDocument : searchListMethodAfter) {
								String methodVarCurrent = (String)solrDocument.get("methodVar_" + classLangueNom + "_stored_string");
								String methodClassCanonicalName = (String)solrDocument.get("classCanonicalName_" + classLangueNom + "_stored_string");
								List<String> methodParamsSimpleNameComplete = (List<String>)solrDocument.get("methodParamsSimpleNameComplete_" + classLangueNom + "_stored_strings");
								String methodParamSimpleNameComplete = methodParamsSimpleNameComplete.get(0);
								List<String> methodParamsVar = (List<String>)solrDocument.get("methodParamsVar_" + classLangueNom + "_stored_strings");
								String methodeParamVar = methodParamsVar.get(0);
								storeListSolr(entityDoc, "entityMethodsAfterVisibility", BooleanUtils.isTrue((Boolean)solrDocument.get("methodIsPublic_stored_boolean")) ? "public" : "protected");
								storeListSolr(entityDoc, "entityMethodsAfterVar", methodVarCurrent);
								storeListSolr(entityDoc, "entityMethodsAfterParamVar", methodeParamVar);
								storeListSolr(entityDoc, "entityMethodsAfterSimpleName", methodParamSimpleNameComplete);
								storeListSolr(entityDoc, "entityMethodsAfterParamName", methodParamsVar.size() > 1);
								Boolean entityMethodsBeforeWrite = (StringUtils.equals(methodClassCanonicalName, classCanonicalName)) && !classMethodsWritten.contains(methodVarCurrent);
								storeListSolr(entityDoc, "entityMethodsBeforeWrite", entityMethodsBeforeWrite);
								classMethodsWritten.add(methodVarCurrent);
								List<String> methodParamCanonicalNames = (List<String>)solrDocument.get("methodParamCanonicalNames_" + classLangueNom + "_stored_strings");
								if(methodParamCanonicalNames != null) {
									String methodParamCanonicalName = methodParamCanonicalNames.get(0);
									classPartsGenAdd(ClassParts.initClassParts(this, methodParamCanonicalName, classLangueNom));
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

							Matcher entityValsSearch = Pattern.compile("^(entity)?Val(\\.(\\w+))?\\.(\\w+):(.*)", Pattern.MULTILINE).matcher(methodComment);
							boolean entityValsFound = entityValsSearch.find();
							while(entityValsFound) {
								String entityValLanguage = entityValsSearch.group(2);
								String entityValVar = entityValsSearch.group(4);
								String entityValValue = entityValsSearch.group(5);
								if(entityValLanguage == null) {
									for(String languageName : toutesLangues) {
										storeListSolr(entityDoc, "entityValsVar", entityValVar);
										storeListSolr(entityDoc, "entityValsLanguage", languageName);
										storeListSolr(entityDoc, "entityValsValue", entityValValue);
									}
								}
								else {
									storeListSolr(entityDoc, "entityValsVar", entityValVar);
									storeListSolr(entityDoc, "entityValsLanguage", entityValLanguage);
									storeListSolr(entityDoc, "entityValsValue", entityValValue);
								}
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
						Boolean entityIncremented = indexStoreSolr(entityDoc, "entityIncremented", regexFound("^(entity)?Incremented:\\s*(true)$", methodComment));
						Boolean entityIndexed = indexStoreSolr(entityDoc, "entityIndexed", regexFound("^(entity)?Indexed:\\s*(true)$", methodComment) || entityUniqueKey || entityEncrypted || entitySuggested || entityPrimaryKey || entityIncremented);
						Boolean entityStored = indexStoreSolr(entityDoc, "entityStored", regexFound("^(entity)?Stored:\\s*(true)$", methodComment));
						Boolean entityText = indexStoreSolr(entityDoc, "entityText", regexFound("^(entity)?Text:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityIndexedOrStored", entityUniqueKey || entityEncrypted || entitySuggested || entityIndexed || entityStored || entityIncremented || entityText);
						indexStoreSolr(entityDoc, "entityIgnored", regexFound("^(entity)?Ignored:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityDeclared", regexFound("^(entity)?Declared:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entitySearch", regexFound("^(entity)?Search:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityAdd", regexFound("^(entity)?Add:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityDelete", regexFound("^(entity)?Delete:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityModify", !regexFound("^(entity)?Modify:\\s*(false)$", methodComment));
						indexStoreSolr(entityDoc, "entityRefresh", regexFound("^(entity)?Refresh:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityMultiline", regexFound("^(entity)?Multiline:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityKeys", regexFound("^(entity)?Keys:\\s*(true)$", methodComment));

						String entityLanguage = regex("^(entity)?Language:\\s*(.*)$", methodComment);
						if(entityLanguage != null)
							indexStoreSolr(entityDoc, "entityLanguage", entityLanguage);

						indexStoreSolr(classLangueNom, entityDoc, "entityDisplayName", regexLanguage(classLangueNom, "(entity)?DisplayName", methodComment));
						indexStoreSolr(classLangueNom, entityDoc, "entityDescription", regexLanguage(classLangueNom, "(entity)?Description", methodComment));
						indexStoreSolr(entityDoc, "entityOptional", regexFound("^(entity)?Optional:\\s*(true)$", methodComment));
						indexStoreSolr(classLangueNom, entityDoc, "entityHtmlTooltip", regexLanguage(classLangueNom, "(entity)?HtmlTooltip", methodComment));
//						indexStoreSolr(entityDoc, "entityVarApi", regex("^(entity)?EntiteVarApi:\\s*(.*)$", methodComment));
						indexStoreSolrRegex(classLangueNom, entityDoc, "entityVarApi", "VarApi", methodComment);
						indexStoreSolr(classLangueNom, entityDoc, "entityEnumSimpleName", regexLanguage(classLangueNom, "(entity)?EnumSimpleName", methodComment));
						indexStoreSolr(classLangueNom, entityDoc, "entityEnumVar", regexLanguage(classLangueNom, "(entity)?EnumVar", methodComment));
						indexStoreSolr(classLangueNom, entityDoc, "entityEnumVarDescription", regexLanguage(classLangueNom, "(entity)?EnumVarDescription", methodComment));

						Boolean entityHighlighting = indexStoreSolr(entityDoc, "entityHighlighting", regexFound("^(entity)?Highlighting:\\s*(true)$", methodComment));
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

						if(classTranslate) {
							for(String languageName : classOtherLanguages) {  
								indexStoreSolr(languageName, entityDoc, "entityDisplayName", regexLanguage(languageName, "(entity)?DisplayName", methodComment));
								indexStoreSolr(languageName, entityDoc, "entityEnumVarDescription", regexLanguage(languageName, "(entity)?EnumVarDescription", methodComment));
								indexStoreSolr(languageName, entityDoc, "entityHtmlTooltip", regexLanguage(languageName, "(entity)?HtmlTooltip", methodComment));
	//							indexStoreSolr(languageName, entityDoc, "entityVarApi", regex("^(entity)?EntiteVarApi." + languageName + ":\\s*(.*)$", methodComment, 1));
								indexStoreSolrRegex(languageName, entityDoc, "entityVarApi", "VarApi", methodComment);
								indexStoreSolr(languageName, entityDoc, "entityEnumVar", regexLanguage(languageName, "(entity)?EnumVar", methodComment));
							}
						}

						Matcher entityAttributeSearch = methodComment == null ? null : Pattern.compile("^(entity)?Attribuer:\\s*([^\\.]+)\\.(.*)\\s*", Pattern.MULTILINE).matcher(methodComment);
						boolean entityAttributeTrouve = entityAttributeSearch == null ? false : entityAttributeSearch.find();
						if(entityAttributeTrouve) {
							String entityAttributeSimpleName = entityAttributeSearch.group(2);
							String entityAttributeVar = entityAttributeSearch.group(3);

							SolrQuery solrSearchClasse = new SolrQuery();   
							solrSearchClasse.setQuery("*:*");
							solrSearchClasse.setRows(1);
							solrSearchClasse.addFilterQuery("classSimpleName_" + classLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entityAttributeSimpleName));
							solrSearchClasse.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
							solrSearchClasse.addFilterQuery("partIsClass_indexed_boolean:true");
							QueryResponse searchResponseClasse = solrClientComputate.query(solrSearchClasse);
							SolrDocumentList searchListClasse = searchResponseClasse.getResults();

							if(searchListClasse.size() > 0) {
								SolrDocument docClasse = searchListClasse.get(0);
								String entityAttributeCanonicalName = (String)docClasse.get("classCanonicalName_" + classLangueNom + "_stored_string");

								SolrQuery solrSearchVar = new SolrQuery();   
								solrSearchVar.setQuery("*:*");
								solrSearchVar.setRows(1);
								solrSearchVar.addFilterQuery("classCanonicalName_" + classLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entityAttributeCanonicalName));
								solrSearchVar.addFilterQuery("entityVar_" + classLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entityAttributeVar));
								solrSearchVar.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
								solrSearchVar.addFilterQuery("partIsEntity_indexed_boolean:true");
								QueryResponse searchResponseVar = solrClientComputate.query(solrSearchVar);
								SolrDocumentList searchListVar = searchResponseVar.getResults();

								if(searchListVar.size() > 0) {
									SolrDocument docEntite = searchListClasse.get(0);
									entityDefined = false;

									indexStoreSolr(entityDoc, "entityAttribute", true);
									indexStoreSolr(classLangueNom, entityDoc, "entityAttributeSimpleName", entityAttributeSimpleName);
									indexStoreSolr(classLangueNom, entityDoc, "entityAttributeCanonicalName", entityAttributeCanonicalName);
									indexStoreSolr(classLangueNom, entityDoc, "entityAttributeVar", entityAttributeVar);

									if(classTranslate) {
										for(String languageName : classOtherLanguages) {  
											String entityAttributeCanonicalNameLangue = (String)docClasse.get("classCanonicalName_" + languageName + "_stored_string");
											String entityAttributeSimpleNameLangue = (String)docClasse.get("classCanonicalName_" + languageName + "_stored_string");
											String entityAttributeVarLangue = (String)docEntite.get("entityVar_" + languageName + "_stored_string");
	
											indexStoreSolr(languageName, entityDoc, "entityAttributeSimpleName", entityAttributeSimpleNameLangue);
											indexStoreSolr(languageName, entityDoc, "entityAttributeCanonicalName", entityAttributeCanonicalNameLangue);
											indexStoreSolr(languageName, entityDoc, "entityAttributeVar", entityAttributeVarLangue);
										}
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
							String entityAnnotationLanguage = indexStoreSolr(classLangueNom, entityDoc, "entityAnnotations", annotation.getType().getCanonicalName());
						}
//						if(classReturnQdox != null && !classReturnQdox.getCanonicalName().equals("void")) {
//							entityCanonicalNameRetourComplet = indexerStocker(entityDoc, "entityCanonicalNameRetourComplet", classLangueNom, classReturnQdox.getGenericCanonicalName());
//							entityCanonicalNameRetour = indexerStocker(entityDoc, "entityCanonicalNameRetour", classLangueNom, classReturnQdox.getCanonicalName());
//							String entitySimpleNameRetour = indexerStocker(entityDoc, "entitySimpleNameRetour", classLangueNom, StringUtils.substringAfterLast(entityCanonicalNameRetour, "."));
//							String listeNomTypeOrigineRetourGenerique = entityCanonicalNameRetourComplet;
//							String entityCanonicalNameRetourGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineRetourGenerique, "<"), ">");
//							String entitySimpleNameRetourComplet;
//							String entitySimpleNameRetourGenerique;
//							entityCanonicalNameRetourGenerique = entityCanonicalNameRetourGenerique.contains("<") ? StringUtils.substringBefore(entityCanonicalNameRetourGenerique, "<") : entityCanonicalNameRetourGenerique;
//							entityCanonicalNameRetourGenerique = entityCanonicalNameRetourGenerique.contains(",") ? StringUtils.substringBefore(entityCanonicalNameRetourGenerique, ",") : entityCanonicalNameRetourGenerique;
//							if(StringUtils.isNotEmpty(entityCanonicalNameRetourGenerique)) {
//								indexerStocker(entityDoc, "entityCanonicalNameRetourGenerique", classLangueNom, entityCanonicalNameRetourGenerique);
//	
//								if(StringUtils.contains(entityCanonicalNameRetourGenerique, "."))
//									entitySimpleNameRetourGenerique = indexerStocker(entityDoc, "entitySimpleNameRetourGenerique", classLangueNom, StringUtils.substringAfterLast(entityCanonicalNameRetourGenerique, "."));
//								else
//									entitySimpleNameRetourGenerique = indexerStocker(entityDoc, "entitySimpleNameRetourGenerique", classLangueNom, entityCanonicalNameRetourGenerique);
//	
//								if(StringUtils.contains(entitySimpleNameRetourGenerique, ".")) {
//									entitySimpleNameRetourComplet = indexerStocker(entityDoc, "entitySimpleNameRetourComplet", classLangueNom, concat(StringUtils.substringAfterLast(entitySimpleNameRetour, "."), "<", entitySimpleNameRetourGenerique, ">"));
//								}
//								else {
//									entitySimpleNameRetourComplet = indexerStocker(entityDoc, "entitySimpleNameRetourComplet", classLangueNom, concat(entitySimpleNameRetour, "<", entitySimpleNameRetourGenerique, ">"));
//								}
//							}
//							else {
//								entitySimpleNameRetourComplet = indexerStocker(entityDoc, "entityCanonicalNameRetourComplet", classLangueNom, entitySimpleNameRetour);
//							}
//						}
	
						String entityKey = classAbsolutePath + "." + entityVar;
		
						// Entites Solr du entity. 
		
						entityDoc.addField("id", entityKey);
						indexStoreSolr(entityDoc, "partIsEntity", true);
						indexStoreSolr(entityDoc, "partNumber", partNumber);

						String entitySourceCode = methodQdox.getSourceCode();
						String entityString = regex("^(entity)?String\\." + classLangueNom + ":(.*)", methodComment);
						if(entityString != null) {
							entitySourceCode = "\n\t\tc.o(\"" + StringUtils.replace(StringUtils.replace(entityString, "\\", "\\\\"), "\"", "\\\"") + "\");\n\t";
							indexStoreSolr(classLangueNom, entityDoc, "entityString", entityString); 
						}
						storeSolr(classLangueNom, entityDoc, "entitySourceCode", entitySourceCode); 

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
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneId", classLangueNom));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneOffset", classLangueNom));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.LocalDateTime", classLangueNom));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZonedDateTime", classLangueNom));
							classPartsGenAdd(ClassParts.initClassParts(this, VAL_canonicalNameDate, classLangueNom));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", classLangueNom));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", classLangueNom));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.util.Locale", classLangueNom));
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLocalDate)) {
							entitySimpleNameVertxJson = "Instant";
							entityCanonicalNameVertxJson = VAL_canonicalNameInstant;
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneId", classLangueNom));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneOffset", classLangueNom));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.LocalDate", classLangueNom));
							classPartsGenAdd(ClassParts.initClassParts(this, VAL_canonicalNameDate, classLangueNom));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", classLangueNom));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", classLangueNom));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.util.Locale", classLangueNom));
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLong)) {
							entitySimpleNameVertxJson = "Long";
							entityCanonicalNameVertxJson = VAL_canonicalNameLong;
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBigDecimal)) {
							entitySimpleNameVertxJson = "Double";
							entityCanonicalNameVertxJson = VAL_canonicalNameLong;
							classPartsGenAdd(ClassParts.initClassParts(this, NumberUtils.class.getCanonicalName(), classLangueNom));
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
								classPartsGenAdd(ClassParts.initClassParts(this, NumberUtils.class.getCanonicalName(), classLangueNom));
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
							classPartsGenAdd(ClassParts.initClassParts(this, entityListCanonicalNameVertxJson, classLangueNom));
						}
						else if(classPartsChain != null && StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameString, classPartsChain.canonicalName)) {
							entitySimpleNameVertxJson = "String";
							entityCanonicalNameVertxJson = VAL_canonicalNameString;
						}
						classPartsGenAdd(ClassParts.initClassParts(this, entityCanonicalNameVertxJson, classLangueNom));
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
							classVarPrimaryKey = storeSolr(classLangueNom, classDoc, "classVarPrimaryKey", entityVar);
						}
						if(entityUniqueKey) {
							classVarUniqueKey = storeSolr(classLangueNom, classDoc, "classVarUniqueKey", entityVar);
						}
				
						if(classTranslate) {
							for(String languageName : classOtherLanguages) {  
								ClassParts entityClassPartsLangue = ClassParts.initClassParts(this, entityClassParts, languageName);
	//							String entityCanonicalNameLangue = regex("^(entity)?NomCanonique\\." + languageName + ":\\s*(.*)", entityComment, entityCanonicalName);
	//							String entitySimpleNameLangue = StringUtils.substringAfterLast(entityCanonicalNameLangue, ".");
	//							String entityNomEnsembleLangue = StringUtils.substringBeforeLast(entityCanonicalNameLangue, ".");
					
								indexStoreSolr(languageName, entityDoc, "entityCanonicalName", entityClassPartsLangue.canonicalName); 
								indexStoreSolr(languageName, entityDoc, "entitySimpleName", entityClassPartsLangue.simpleName); 
								indexStoreSolr(languageName, entityDoc, "entityCanonicalNameComplete", entityClassPartsLangue.canonicalNameComplete); 
								indexStoreSolr(languageName, entityDoc, "entitySimpleNameComplete", entityClassPartsLangue.simpleNameComplete); 
								indexStoreSolr(languageName, entityDoc, "entityCanonicalNameGeneric", entityClassPartsLangue.canonicalNameGeneric); 
								indexStoreSolr(languageName, entityDoc, "entitySimpleNameGeneric", entityClassPartsLangue.simpleNameGeneric); 
								indexStoreSolr(languageName, entityDoc, "entitySimpleNameCompleteGeneric", entityClassPartsLangue.simpleNameGeneric); 
	
								indexStoreSolr(languageName, entityDoc, "entityVarParam", entityVarParam); 
	
								String entityVarLangue = regex("^(entity)?Var\\." + languageName + ": (.*)", methodComment);
								entityVarLangue = indexStoreSolr(languageName, entityDoc, "entityVar", entityVarLangue == null ? entityVar : entityVarLangue);
								indexStoreSolr(languageName, entityDoc, "entityVarCapitalized", StringUtils.capitalize(entityVarLangue));
								indexStoreListSolr(languageName, classDoc, "classEntityVars", entityVarLangue);
								if(entityPrimaryKey) {
									storeSolr(languageName, classDoc, "classVarPrimaryKey", entityVarLangue);
								}
								if(entityUniqueKey) {
									storeSolr(languageName, classDoc, "classVarUniqueKey", entityVarLangue);
								}
		
								String entitySourceCodeLangue = entitySourceCode;
								entitySourceCodeLangue = regexReplaceAll(methodComment, entitySourceCode, languageName);
								String entityStringLangue = regex("^(entity)?String\\." + languageName + ":(.*)", methodComment);
								if(entityStringLangue != null) {
									entitySourceCodeLangue = "\n\t\tc.o(\"" + StringUtils.replace(StringUtils.replace(entityStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\");\n\t";
									indexStoreSolr(languageName, entityDoc, "entityString", entityStringLangue); 
								}
								storeSolr(languageName, entityDoc, "entitySourceCode", entitySourceCodeLangue); 
		
								storeRegexComments(languageName, entityDoc, "entityComment", methodComment);
							}
						}

						for(JavaClass methodExceptionQdox : methodExceptionsQdox) { 
							String methodExceptionSimpleNameComplete = StringUtils.substringAfterLast(methodExceptionQdox.getCanonicalName(), ".");
							ClassParts methodeExceptionClassParts = ClassParts.initClassParts(this, methodExceptionQdox.getCanonicalName(), classLangueNom);
							if(!classInitDeepExceptions.contains(methodeExceptionClassParts.canonicalNameComplete))
								classInitDeepExceptions.add(methodeExceptionClassParts.canonicalNameComplete);
							storeListSolr(entityDoc, "methodExceptionsSimpleNameComplete", methodExceptionSimpleNameComplete);
							classPartsGenAdd(methodeExceptionClassParts);
							if(classTranslate) {
								for(String languageName : classOtherLanguages) {  
									ClassParts methodeExceptionClassPartsLangue = ClassParts.initClassParts(this, methodExceptionQdox.getCanonicalName(), languageName);
									storeListSolr(entityDoc, "methodExceptionsSimpleNameComplete", methodeExceptionClassPartsLangue.simpleNameComplete);
								}
							}
						}
			
						if(methodComment != null) {
							Matcher entityValsSearch = Pattern.compile("^(entity)?Val\\.(\\w+)\\.(\\w+):(.*)", Pattern.MULTILINE).matcher(methodComment);
							boolean entityValsFound = entityValsSearch.find();
							while(entityValsFound) {
								String entityValVar = entityValsSearch.group(2);
								String entityValLanguage = entityValsSearch.group(3);
								String entityValValue = entityValsSearch.group(4);
								storeListSolr(entityDoc, "entityValsVar", entityValVar);
								storeListSolr(entityDoc, "entityValsLanguage", entityValLanguage);
								storeListSolr(entityDoc, "entityValsValue", entityValValue);
								entityValsFound = entityValsSearch.find();
							}
						}

						solrClientComputate.add(entityDoc); 
					}
					else {  
						// est Methode. 
						
						SolrInputDocument methodDoc = classDocClone.deepCopy();
						indexStoreSolr(classLangueNom, methodDoc, "methodVar", methodVar);
						indexStoreListSolr(classLangueNom, classDoc, "classMethodVars", methodVar);
						for(Integer methodParamNum = 1; methodParamNum <= methodParamsQdox.size(); methodParamNum++) {
							JavaParameter methodParamQdox = methodParamsQdox.get(methodParamNum - 1);
							String methodeParamVar = methodParamQdox.getName();
							storeListSolr(classLangueNom, methodDoc, "methodParamsVar", methodeParamVar);
							ClassParts methodeParamsClassePart = ClassParts.initClassParts(this, methodParamQdox.getJavaClass(), classLangueNom);
							storeListSolr(classLangueNom, methodDoc, "methodParamCanonicalNames", methodeParamsClassePart.canonicalName);
							storeListSolr(classLangueNom, methodDoc, "methodParamsSimpleNameComplete", methodeParamsClassePart.simpleNameComplete);
							storeListSolr(methodDoc, "methodParamsVariableArgs", methodParamQdox.isVarArgs());
							if(classTranslate) {
								for(String languageName : classOtherLanguages) { 
									String methodParamVarLanguage = regex("^(methode)?Param" + methodParamNum + "\\.var\\." + languageName + ": (.*)", methodComment);
									if(methodParamVarLanguage == null)
										methodParamVarLanguage = methodeParamVar;
									ClassParts methodParamClassPartsLanguage = ClassParts.initClassParts(this, methodeParamsClassePart, languageName);
	
									storeListSolr(languageName, methodDoc, "methodParamCanonicalNames", methodParamClassPartsLanguage.canonicalName);
									storeListSolr(languageName, methodDoc, "methodParamsSimpleNameComplete", methodParamClassPartsLanguage.simpleNameComplete);
									storeListSolr(languageName, methodDoc, "methodParamsVar", methodParamVarLanguage);
								}  
							}
						}

						List<JavaTypeVariable<JavaGenericDeclaration>> methodParamsType = methodQdox.getTypeParameters();
						for(JavaTypeVariable<JavaGenericDeclaration> methodParamType : methodParamsType) {
							String methodParamTypeNom = methodParamType.getName();
							storeListSolr(methodDoc, "methodParamsTypeName", methodParamTypeNom);
						}

						for(JavaAnnotation annotation : annotations) {
							ClassParts methodAnnotationClassParts = ClassParts.initClassParts(this, annotation.getType(), classLangueNom);
							storeListSolr(classLangueNom, methodDoc, "methodAnnotationsNomSimpleComplet", methodAnnotationClassParts.simpleNameComplete);
							storeListSolr(classLangueNom, methodDoc, "methodAnnotationsBlocCode", StringUtils.substringAfter(annotation.toString(), methodAnnotationClassParts.simpleName));
							if(classTranslate) {
								for(String languageName : classOtherLanguages) {  
									ClassParts methodAnnotationClassPartsLangue = ClassParts.initClassParts(this, methodAnnotationClassParts, languageName);
									storeListSolr(languageName, methodDoc, "methodAnnotationsNomSimpleComplet", methodAnnotationClassPartsLangue.simpleNameComplete);
									storeListSolr(languageName, methodDoc, "methodAnnotationsBlocCode", StringUtils.substringAfter(annotation.toString(), methodAnnotationClassParts.simpleName));
								}
							}
						}

						for(JavaClass methodExceptionQdox : methodExceptionsQdox) { 
							String methodExceptionSimpleNameComplete = StringUtils.substringAfterLast(methodExceptionQdox.getCanonicalName(), ".");
							storeListSolr(methodDoc, "methodExceptionsSimpleNameComplete", methodExceptionSimpleNameComplete);
							if(classTranslate) {
								for(String languageName : classOtherLanguages) {  
									ClassParts methodeExceptionClassPartsLangue = ClassParts.initClassParts(this, methodExceptionQdox.getCanonicalName(), languageName);
									storeListSolr(methodDoc, "methodExceptionsSimpleNameComplete", methodeExceptionClassPartsLangue.simpleNameComplete);
								}
							}
						}

						Boolean methodIsVoid = false;
						if(classReturnQdox != null && !classReturnQdox.getCanonicalName().equals("void")) {
							ClassParts methodReturnClassParts = ClassParts.initClassParts(this, methodQdox.getReturns(), classLangueNom);
							storeSolr(classLangueNom, methodDoc, "methodReturnSimpleNameComplete", methodReturnClassParts.simpleNameComplete);
							if(classTranslate) {
								for(String languageName : classOtherLanguages) { 
									ClassParts methodReturnClassPartsLanguage = ClassParts.initClassParts(this, methodReturnClassParts, languageName);
									storeSolr(languageName, methodDoc, "methodReturnSimpleNameComplete", methodReturnClassPartsLanguage.simpleNameComplete);
								}
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
						storeRegexComments(classLangueNom, methodDoc, "methodComment", methodComment);
	
						String methodVarLanguage = regex("^(methode)?Var\\." + classLangueNom + ": (.*)", methodComment);
						methodVarLanguage =  methodVarLanguage == null ? methodVar : methodVarLanguage;
	
						regexList("^" + classLangueNom + ":\\s*([^\n]+)", methodComment);

						String methodSourceCode = methodQdox.getSourceCode();
						String methodSourceCodeLanguage = methodSourceCode;
//						ArrayList<String> replaceKeysLanguage = regexList("^r." + classLangueNom + "\\s*=\\s*(.*)\\n.*", methodComment);
//						ArrayList<String> replaceValuesLanguage = regexList("^r." + classLangueNom + "\\s*=\\s*.*\\n(.*)", methodComment);
//						for(int i = 0; i < replaceKeysLanguage.size(); i++) {
//							String regexKey = replaceKeysLanguage.get(i);
//							String regexValue = replaceValuesLanguage.get(i);
//							StringUtils.replace(methodSourceCodeLanguage, regexKey, regexValue);
//						}
						String methodString = regex("^(methode)?String\\." + classLangueNom + ":(.*)", methodComment);
						if(methodString != null) {
							methodSourceCode = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodString, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
							indexStoreSolr(classLangueNom, methodDoc, "methodString", methodString); 
						}
						storeSolr(classLangueNom, methodDoc, "methodSourceCode", methodSourceCode);

						if(classTranslate) {
							for(String languageName : classOtherLanguages) {  
								methodVarLanguage = regex("^(methode)?Var\\." + languageName + ":\\s*([^\n]+)", methodComment);
								methodVarLanguage = indexStoreSolr(languageName, methodDoc, "methodVar", methodVarLanguage == null ? methodVar : methodVarLanguage);
								regexList("^" + languageName + ":\\s*([^\n]+)", methodComment);
								methodSourceCodeLanguage = regexReplaceAll(methodComment, methodSourceCode, languageName);
								indexStoreListSolr(languageName, classDoc, "classMethodVars", methodVarLanguage);
								String methodStringLangue = regex("^(methode)?String\\." + languageName + ":(.*)", methodComment);
								if(methodStringLangue != null) {
									methodSourceCodeLanguage = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
									indexStoreSolr(languageName, methodDoc, "methodString", methodStringLangue); 
								}
								storeSolr(languageName, methodDoc, "methodSourceCode", methodSourceCodeLanguage);
								storeRegexComments(languageName, methodDoc, "methodComment", methodComment);
							} 
						}
	
						solrClientComputate.add(methodDoc); 
					}
				}
			}
		}

		if(classVarPrimaryKey == null && classSuperDoc != null) {
			classVarPrimaryKey = (String)classSuperDoc.get("classVarPrimaryKey_" + classLangueNom + "_stored_string");
			if(classVarPrimaryKey != null) {
				storeSolr(classLangueNom, classDoc, "classVarPrimaryKey", classVarPrimaryKey);
				if(classTranslate) {
					for(String languageName : classOtherLanguages) {  
						String classVarPrimaryKeyLangue = (String)classSuperDoc.get("classVarPrimaryKey_" + languageName + "_stored_string");
						if(classVarPrimaryKeyLangue != null) {
							storeSolr(languageName, classDoc, "classVarPrimaryKey", classVarPrimaryKeyLangue);
						}
					}
				}
			}
		}
		if(classVarUniqueKey == null && classSuperDoc != null) {
			classVarUniqueKey = (String)classSuperDoc.get("classVarUniqueKey_" + classLangueNom + "_stored_string");
			if(classVarUniqueKey != null) {
				storeSolr(classLangueNom, classDoc, "classVarUniqueKey", classVarUniqueKey);
				if(classTranslate) {
					for(String languageName : classOtherLanguages) {  
						String classVarUniqueKeyLangue = (String)classSuperDoc.get("classVarUniqueKey_" + languageName + "_stored_string");
						if(classVarUniqueKeyLangue != null) {
							storeSolr(languageName, classDoc, "classVarUniqueKey", classVarUniqueKeyLangue);
						}
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
		
		ClassParts classPartsWrap = classPartsWrap(domainPackageName, classLangueNom);
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
			String classApiUri = indexStoreSolrRegex(classDoc, "classApiUri", "ApiUri", classComment);
			String classApiTag = indexStoreSolrRegex(classDoc, "classApiTag", "ApiTag", classComment);

			for(String classApiMethod : classApiMethods) {
				indexStoreListSolr(classDoc, "classApiMethods", classApiMethod); 

				String classApiMethodMethode;
				if(StringUtils.contains(classApiMethod, "POST"))
					classApiMethodMethode = "POST";
				else if(StringUtils.contains(classApiMethod, "PATCH"))
					classApiMethodMethode = "PATCH";
				else if(StringUtils.contains(classApiMethod, "DELETE"))
					classApiMethodMethode = "DELETE";
				else if(StringUtils.contains(classApiMethod, "PUT"))
					classApiMethodMethode = "PUT";
				else
					classApiMethodMethode = "GET";

				indexStoreSolr(classDoc, "classApiMethod" + classApiMethod, regex("^(class)?ApiMethode" + classApiMethod + ":\\s*(.*)", classComment, classApiMethodMethode));

				String classApiUriMethode = regexLanguage(classLangueNom, "(class)?ApiUri" + classApiMethod, classComment);

				indexStoreSolrRegex(classDoc, "classApiOperationId" + classApiMethod, "ApiOperationId" + classApiMethod, classComment, StringUtils.lowerCase(classApiMethod) + classSimpleName);
				indexStoreSolrRegex(classDoc, "classApiOperationId" + classApiMethod + "Request", "ApiOperationId" + classApiMethod + "Request", classComment, classApiMethod + classSimpleName + "Request");
				indexStoreSolrRegex(classDoc, "classApiOperationId" + classApiMethod + "Response", "ApiOperationId" + classApiMethod + "Response", classComment, classApiMethod + classSimpleName + "Response");
				indexStoreSolrRegex(classDoc, "classApiDescription" + classApiMethod, "ApiDescription" + classApiMethod, classComment, regexLanguage(classLangueNom, "(class)?Description" + classApiMethod + "", classComment));

				if(classExtendsBase && classSuperDoc != null) {
					indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod, (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "_" + classLangueNom + "_stored_string"));
					indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod + "Request", (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "Request" + "_" + classLangueNom + "_stored_string"));
					indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod + "Response", (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "Response" + "_" + classLangueNom + "_stored_string"));
				}

				String classPageNomSimpleMethode = regex("^(class)?Page" + classApiMethod + ":\\s*(.*)", classComment);
				String classPageSuperNomSimpleMethode = regex("^(class)?PageSuper" + classApiMethod + ":\\s*(.*)", classComment);
				String classApiMediaType200Methode = regex("^(class)?ApiTypeMedia200" + classApiMethod + ":\\s*(.*)", classComment, classPageNomSimpleMethode == null ? "application/json" : "text/html");
				String classApiKeywordMethod = regexLanguage(classLangueNom, "(class)?ApiKeyword" + classApiMethod, classComment);
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
						classApiUriMethode = classApiUri + "/{id}";
				}
				if(this.languageName.equals(classLangueNom))
					indexStoreSolr(classDoc, "classApiMediaType200" + classApiMethod, classApiMediaType200Methode);
				indexStoreSolr(classDoc, "classApiKeyword" + classApiMethod, classApiKeywordMethod);
				indexStoreSolr(classDoc, "classApiUri" + classApiMethod, classApiUriMethode);
				if(classPageNomSimpleMethode != null) {
					String classPageLangueNom = null;

					if(classTranslate) {
						for(String languageName : toutesLangues) {
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
								String classPackageNameLanguage = (String)classDoc.get("classPackageName_" + languageName + "_indexed_string").getValue();
								String classPageNomCanoniqueMethode = (String)docEntite.get("classCanonicalName_" + languageName + "_stored_string");
		//							String classPageNomSimpleMethode = (String)docEntite.get("classSimpleName_" + languageName + "_stored_string");
								indexStoreSolr(classDoc, "classPageNomCanonique" + classApiMethod, classPageNomCanoniqueMethode);
								indexStoreSolr(classDoc, "classPageNomSimple" + classApiMethod, classPageNomSimpleMethode);
								classPartsGenApiAdd(ClassParts.initClassParts(this, classPageNomCanoniqueMethode, languageName));
	
								String classGenPageNomSimple;
								String classPageCheminGen;
								if(StringUtils.contains(classPageNomSimpleMethode, "Page")) {
									classGenPageNomSimple = StringUtils.substringBeforeLast(classPageNomSimpleMethode, "Page") + "GenPage" + StringUtils.substringAfterLast(classPageNomSimpleMethode, "Page");
									classPageCheminGen = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classGenPageNomSimple, ".java");
								}
								else {
									classGenPageNomSimple = "Gen" + classPageNomSimpleMethode;
									classPageCheminGen = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classGenPageNomSimple, ".java");
								}
								indexStoreSolr(classDoc, "classGenPageNomSimple" + classApiMethod, classGenPageNomSimple);
								String classPageChemin = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classPageNomSimpleMethode, ".java");
								indexStoreSolr(classDoc, "classPageCheminGen" + classApiMethod, classPageCheminGen); 
								indexStoreSolr(classDoc, "classPageChemin" + classApiMethod, classPageChemin); 
								classPageLangueNom = languageName;
								break;
							}
						}
					}
					if(classPageLangueNom == null) {

						if(classTranslate) {
							for(String languageName : toutesLangues) {
								if(StringUtils.containsIgnoreCase(classPageNomSimpleMethode, languageName)) {
									String classPackageNameLanguage = (String)classDoc.get("classPackageName_" + languageName + "_indexed_string").getValue();
	
									String classGenPageNomSimple;
									String classPageCheminGen;
									String classPageChemin;
									if(StringUtils.contains(classPageNomSimpleMethode, "Page")) {
										classGenPageNomSimple = StringUtils.substringBeforeLast(classPageNomSimpleMethode, "Page") + "GenPage" + StringUtils.substringAfterLast(classPageNomSimpleMethode, "Page");
										classPageCheminGen = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classGenPageNomSimple, ".java");
										classPageChemin = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classPageNomSimpleMethode, ".java");
									}
									else {
										classGenPageNomSimple = "Gen" + classPageNomSimpleMethode;
										classPageCheminGen = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classGenPageNomSimple, ".java");
										classPageChemin = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classPageNomSimpleMethode, ".java");
									}
									indexStoreSolr(classDoc, "classGenPageNomSimple" + classApiMethod, classGenPageNomSimple);
									indexStoreSolr(classDoc, "classPageCheminGen" + classApiMethod, classPageCheminGen); 
									indexStoreSolr(classDoc, "classPageChemin" + classApiMethod, classPageChemin); 
									classPageLangueNom = languageName;
									break;
								}
							}
						}
						if(classPageLangueNom != null) {
							String classPageNomCanoniqueMethode = classPackageName + "." + classPageNomSimpleMethode;
							
							indexStoreSolr(classDoc, "classPageNomCanonique" + classApiMethod, classPageNomCanoniqueMethode);
							indexStoreSolr(classDoc, "classPageNomSimple" + classApiMethod, classPageNomSimpleMethode);
							classPartsGenApiAdd(ClassParts.initClassParts(this, classPageNomCanoniqueMethode, classPageLangueNom));
						}
					}

					if(classPageLangueNom != null) {
						if(classPageSuperNomSimpleMethode != null) {
							SolrQuery searchPage = new SolrQuery();   
							searchPage.setQuery("*:*");
							searchPage.setRows(1);
							searchPage.addFilterQuery("classSimpleName_" + classPageLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classPageSuperNomSimpleMethode));
							searchPage.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
							searchPage.addFilterQuery("partIsClass_indexed_boolean:true");
							QueryResponse searchResponsePage = solrClientComputate.query(searchPage);
							SolrDocumentList searchListPage = searchResponsePage.getResults();
		
							if(searchListPage.size() > 0) {
								SolrDocument docEntite = searchListPage.get(0);
								String classPageSuperNomCanoniqueMethode = (String)docEntite.get("classCanonicalName_" + classPageLangueNom + "_stored_string");
								indexStoreSolr(classDoc, "classPageSuperNomCanonique" + classApiMethod, classPageSuperNomCanoniqueMethode);
								indexStoreSolr(classDoc, "classPageSuperNomSimple" + classApiMethod, classPageSuperNomSimpleMethode);
								classPartsGenPageAdd(ClassParts.initClassParts(this, classPageSuperNomCanoniqueMethode, classPageLangueNom));
							}
							else {
								indexStoreSolr(classDoc, "classPageSuperNomCanonique" + classApiMethod, (String)classPartsPageLayout.solrDocument.get("classCanonicalName_" + classPageLangueNom + "_stored_string"));
								indexStoreSolr(classDoc, "classPageSuperNomSimple" + classApiMethod, (String)classPartsPageLayout.solrDocument.get("classSimpleName_" + classPageLangueNom + "_stored_string"));
//								classPartsGenPageAdd(ClassParts.initClassParts(this, (String)classPartsPageLayout.solrDocument.get("classCanonicalName_" + classPageLangueNom + "_stored_string"), classPageLangueNom));
								classPartsGenPageAdd(classPartsPageLayout);
							}
						}
						else {
							indexStoreSolr(classDoc, "classPageSuperNomCanonique" + classApiMethod, (String)classPartsPageLayout.solrDocument.get("classCanonicalName_" + classPageLangueNom + "_stored_string"));
							indexStoreSolr(classDoc, "classPageSuperNomSimple" + classApiMethod, (String)classPartsPageLayout.solrDocument.get("classSimpleName_" + classPageLangueNom + "_stored_string"));
//							classPartsGenPageAdd(ClassParts.initClassParts(this, (String)classPartsPageLayout.solrDocument.get("classCanonicalName_" + classPageLangueNom + "_stored_string"), classPageLangueNom));
							classPartsGenPageAdd(classPartsPageLayout);
						}

						String classPageCheminCss = concat(appPath, "-static/css/", classPageNomSimpleMethode, ".css");
						String classPageCheminJs = concat(appPath, "-static/js/", classPageNomSimpleMethode, ".js");
			
						indexStoreSolr(classDoc, "classPageCheminCss" + classApiMethod, classPageCheminCss); 
						indexStoreSolr(classDoc, "classPageCheminJs" + classApiMethod, classPageCheminJs); 
						indexStoreSolr(classDoc, "classPageLangueNom" + classApiMethod, classPageLangueNom); 
						classPage = true;
					}
					else {
						indexStoreSolr(classDoc, "classPageSuperNomCanonique" + classApiMethod, (String)classPartsPageLayout.solrDocument.get("classCanonicalName_" + classPageLangueNom + "_stored_string"));
						indexStoreSolr(classDoc, "classPageSuperNomSimple" + classApiMethod, (String)classPartsPageLayout.solrDocument.get("classSimpleName_" + classPageLangueNom + "_stored_string"));
//						classPartsGenPageAdd(ClassParts.initClassParts(this, (String)classPartsPageLayout.solrDocument.get("classCanonicalName_" + classPageLangueNom + "_stored_string"), classPageLangueNom));
						classPartsGenPageAdd(classPartsPageLayout);
					}
				}
//				}
			}
//			if(classTranslate) {
//			for(String languageName : classOtherLanguages) {  
//				String classApiUriLangue = indexStoreSolrRegex(classDoc, "classApiUri", "ApiUri", classComment);
//				String classApiTagLangue = indexStoreSolrRegex(classDoc, "classApiTag", "ApiTag", classComment);
//
//				for(String classApiMethod : classApiMethods) {
//					String classApiUriMethodeLangue = regexLanguage(languageName, "apiUri" + classApiMethod, classComment);
//					indexStoreSolr(classDoc, "classApiMethod" + classApiMethod, regex("^(class)?ApiMethode" + classApiMethod + ":\\s*(.*)", classComment, classApiMethod));
//					indexStoreSolrRegex(classDoc, "classApiOperationId" + classApiMethod, "ApiOperationId" + classApiMethod, classComment, StringUtils.lowerCase(classApiMethod) + classSimpleName);
//					indexStoreSolrRegex(classDoc, "classApiOperationId" + classApiMethod + "Request", "ApiOperationId" + classApiMethod + "Request", classComment, classApiMethod + classSimpleName + "Request");
//					indexStoreSolrRegex(classDoc, "classApiOperationId" + classApiMethod + "Response", "ApiOperationId" + classApiMethod + "Response", classComment, classApiMethod + classSimpleName + "Response");
//					indexStoreSolrRegex(classDoc, "classApiDescription" + classApiMethod, "ApiDescription" + classApiMethod, classComment, regexLanguage(languageName, "(class)?Description" + classApiMethod + "", classComment));
//
//					if(classExtendsBase) {
//						indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod, (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "_" + languageName + "_stored_string"));
//						indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod + "Request", (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "Request" + "_" + languageName + "_stored_string"));
//						indexStoreSolr(classDoc, "classSuperApiOperationId" + classApiMethod + "Response", (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "Response" + "_" + languageName + "_stored_string"));
//					}
//
//					String classApiKeywordMethodLangue = regexLanguage(languageName, "(class)?ApiKeyword" + classApiMethod, classComment);
//					if(StringUtils.contains(classApiMethod, "POST")
//							|| StringUtils.contains(classApiMethod, "Search")
//							|| StringUtils.contains(classApiMethod, "PATCH")
//							) {
//						if(StringUtils.isBlank(classApiKeywordMethodLangue))
//							classApiKeywordMethodLangue = StringUtils.substringAfterLast(classApiUriMethodeLangue, "/");
//						if(StringUtils.isBlank(classApiUriMethodeLangue))
//							classApiUriMethodeLangue = classApiUriLangue;
//					}
//					else {
//						if(StringUtils.isBlank(classApiKeywordMethodLangue))
//							classApiKeywordMethodLangue = StringUtils.substringAfterLast(StringUtils.substringBeforeLast(classApiUriMethodeLangue, "/"), "/");
//						if(StringUtils.isBlank(classApiUriMethodeLangue))
//							classApiUriMethodeLangue = classApiUriLangue + "/{id}";
//					}
//					indexStoreSolr(classDoc, "classApiKeyword" + classApiMethod, classApiKeywordMethodLangue);
//					indexStoreSolr(classDoc, "classApiUri" + classApiMethod, classApiUriMethodeLangue);
//				}
//			}
//			}
		}

		if(classPage) {
			classPartsGenPageAdd(classPartsSiteConfig);
			classPartsGenPageAdd(classPartsSiteRequest);
			classPartsGenPageAdd(classPartsSiteContext);
			classPartsGenPageAdd(classPartsSiteUser);
			classPartsGenPageAdd(ClassParts.initClassParts(this, "java.io.IOException", classLangueNom));
//			classPartsGenPageAdd(ClassParts.initClassParts(this, "javax.servlet.http.HttpServlet", classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.core.http.HttpServerRequest", classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.core.http.HttpServerResponse", classLangueNom));
			classPartsGenPageAdd(classPartsSearchList);
			classPartsGenPageAdd(classPartsWrap);
			classPartsGenPageAdd(classPartsPageLayout);
			classPartsGenPageAdd(ClassParts.initClassParts(this, LocalDateTime.class.getCanonicalName(), classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, LocalDate.class.getCanonicalName(), classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, ZonedDateTime.class.getCanonicalName(), classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, DateTimeFormatter.class.getCanonicalName(), classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, Locale.class.getCanonicalName(), classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonObject", classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.OperationRequest", classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonArray", classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, "java.net.URLDecoder", classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, StringUtils.class.getCanonicalName(), classLangueNom));
			classPartsGenPageAdd(ClassParts.initClassParts(this, Map.class.getCanonicalName(), classLangueNom));
		}

		for(ClassParts classPartGenPage : classPartsGenPage.values()) {
			if(classPartGenPage.languageName == null || classPartGenPage.languageName.equals(languageName))
				indexStoreListSolr(classLangueNom, classDoc, "classImportsGenPage", classPartGenPage.canonicalName);
			if(classTranslate) {
				for(String languageName : classOtherLanguages) {  
					ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classPartGenPage, languageName);
					if(classImportClassPartsLanguage.languageName == null || classImportClassPartsLanguage.languageName.equals(languageName))
						indexStoreListSolr(languageName, classDoc, "classImportsGenPage", classImportClassPartsLanguage.canonicalName);
				}
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

			for(String languageName : toutesLangues) {
				contextVideoId = regexLanguage(languageName, "(context)?VideoId", classComment);
				if(contextVideoId != null)
					indexStoreSolr(languageName, classDoc, "contextVideoId", contextVideoId); 

				String contextImageWidthStr = regexLanguage(languageName, "^(context)?ImageLargeur", classComment);
				if(NumberUtils.isCreatable(contextImageWidthStr))
					indexStoreSolr(languageName, classDoc, "contextImageWidth", Integer.parseInt(contextImageWidthStr));

				String contextImageHeightStr = regexLanguage(languageName, "^(context)?ImageHauteur", classComment);
				if(NumberUtils.isCreatable(contextImageHeightStr))
					indexStoreSolr(languageName, classDoc, "contextImageHeight", Integer.parseInt(contextImageHeightStr));
					
				contextAName = regexLanguage(languageName, "(context)?ANameLowercase", classComment);
				if(contextAName != null) {
					if("frFR".equals(languageName)) {
						indexStoreSolr(languageName, classDoc, "contextAName", contextAName); 
						contextNameSingular = indexStoreSolr(languageName, classDoc, "contextNameSingular", regexLanguage(languageName, "(context)?NomSingulier", classComment, StringUtils.substringAfter(contextAName, " ")));
						contextNamePlural = indexStoreSolr(languageName, classDoc, "contextNamePlural", regexLanguage(languageName, "(context)?NomPluriel", classComment, contextNameSingular + "s"));
						contextNameVar = indexStoreSolr(languageName, classDoc, "contextNameVar", regexLanguage(languageName, "(context)?NomVar", classComment, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contextNameSingular, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
						contextTheNames = indexStoreSolr(languageName, classDoc, "contextTheNames", regexLanguage(languageName, "(context)?TheName", classComment, CONTEXT_frFR_ThePlural + contextNamePlural));
		
						contextAdjective = regexLanguage(languageName, "(context)?Adjective", classComment);
						if(contextAdjective != null) {
							contextAdjectivePlural = indexStoreSolr(languageName, classDoc, "contextAdjectivePlural", regexLanguage(languageName, "(context)?AdjectivePlural", classComment, contextAdjective + CONTEXT_frFR_AdjectivePlural));
							contextAdjectiveVar = indexStoreSolr(languageName, classDoc, "contextAdjectiveVar", regexLanguage(languageName, "(context)?AdjectiveVar", classComment, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contextAdjective, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
							if(CONTEXT_frFR_AdjectiveBefore) {
								contextNameAdjectiveSingular = indexStoreSolr(languageName, classDoc, "contextNameAdjectiveSingular", regexLanguage(languageName, "(context)?NameAdjectiveSingular", classComment, contextAdjective + " " + contextNameSingular));
								contextNameAdjectivePlural = indexStoreSolr(languageName, classDoc, "contextNameAdjectivePlural", regexLanguage(languageName, "(context)?NomAdjectivePlural", classComment, contextAdjectivePlural + " " + contextNamePlural));
							}
							else {
								contextNameAdjectiveSingular = indexStoreSolr(languageName, classDoc, "contextNameAdjectiveSingular", regexLanguage(languageName, "(context)?NameAdjectiveSingular", classComment, contextNameSingular + " " + contextAdjective));
								contextNameAdjectivePlural = indexStoreSolr(languageName, classDoc, "contextNameSingularPluriel", regexLanguage(languageName, "(context)?NomSingulierPluriel", classComment, contextNamePlural + " " + contextAdjectivePlural));
							}
		
						}
		
						if(contextAName.startsWith(CONTEXT_frFR_AFemale)) {
							contextThis = indexStoreSolr(languageName, classDoc, "contextThis", regexLanguage(languageName, "(context)?Ce", classComment, CONTEXT_frFR_ThisFemaleConsonant));
							contextA = indexStoreSolr(languageName, classDoc, "contextA", regexLanguage(languageName, "(context)?Un", classComment, CONTEXT_frFR_AFemale));
							contextCreated = indexStoreSolr(languageName, classDoc, "contextCreated", regexLanguage(languageName, "(context)?Cree", classComment, CONTEXT_frFR_CreeeFeminin));
							contextModified = indexStoreSolr(languageName, classDoc, "contextModified", regexLanguage(languageName, "(context)?Modifie", classComment, CONTEXT_frFR_ModifieeFeminin));
							contextActualName = indexStoreSolr(languageName, classDoc, "contextActualName", regexLanguage(languageName, "(context)?NomActuel", classComment, CONTEXT_frFR_CurrentFemaleBefore + contextNameSingular + CONTEXT_frFR_CurrentFemaleAfter));
		//					contextAll = indexStoreSolr(languageName, classDoc, "contextAll", regexLanguage(languageName, "(context)?Tous", classComment, CONTEXT_frFR_AllFemalePlural));
							contextAllName = indexStoreSolr(languageName, classDoc, "contextAllName", regexLanguage(languageName, "(context)?TousNom", classComment, CONTEXT_frFR_ThePlural + contextNamePlural));
							contextSearchrTousNomPar = indexStoreSolr(languageName, classDoc, "contextSearchrTousNomPar", regexLanguage(languageName, "(context)?SearchTousNomPar", classComment, CONTEXT_frFR_Searchr + contextNamePlural + CONTEXT_frFR_Par));
							contextSearchrTousNom = indexStoreSolr(languageName, classDoc, "contextSearchrTousNom", regexLanguage(languageName, "(context)?SearchTousNomPar", classComment, CONTEXT_frFR_Searchr + contextNamePlural));
							contextNoneNameFound = indexStoreSolr(languageName, classDoc, "contextNoneNameFound", regexLanguage(languageName, "(context)?AucunNomTrouve", classComment, CONTEXT_frFR_NoneFemaleBefore + contextNameSingular + CONTEXT_frFR_NoneFemaleAfter));
							if(contextAdjective != null) {
								if(CONTEXT_frFR_AdjectiveBefore)
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_frFR_AFemale + " " + contextAdjective + " " + contextNameSingular));
								else
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_frFR_AFemale + " " + contextNameSingular + " " + contextAdjective));
							}
		
							String suffixe = StringUtils.substringAfter(contextAName, " ");
							String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
								contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?CeNom", classComment, CONTEXT_frFR_ThisFemaleVowel + suffixe));
								contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?LeNom", classComment, CONTEXT_frFR_TheFemaleVowel + suffixe));
								contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?DeNom", classComment, CONTEXT_frFR_OfVowel + suffixe));
								if(contextAdjective != null) {
									if(CONTEXT_frFR_AdjectiveBefore)
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheFemaleVowel + " " + contextAdjective + " " + contextNameSingular));
									else
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheFemaleVowel + " " + contextNameSingular + " " + contextAdjective));
								}
							}
							else {
								contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?CeNom", classComment, CONTEXT_frFR_ThisFemaleConsonant + suffixe));
								contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?LeNom", classComment, CONTEXT_frFR_TheFemaleConsonant + suffixe));
								contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?DeNom", classComment, CONTEXT_frFR_OfConsonant + suffixe));
								if(contextAdjective != null) {
									if(CONTEXT_frFR_AdjectiveBefore)
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheFemaleConsonant + " " + contextAdjective + " " + contextNameSingular));
									else
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheFemaleConsonant + " " + contextNameSingular + " " + contextAdjective));
								}
							}
						}
						else if(contextAName.startsWith(CONTEXT_frFR_AMale)) {
							contextThis = indexStoreSolr(languageName, classDoc, "contextThis", regexLanguage(languageName, "(context)?Ce", classComment, CONTEXT_frFR_ThisMaleConsonant));
							contextA = indexStoreSolr(languageName, classDoc, "contextA", regexLanguage(languageName, "(context)?Un", classComment, CONTEXT_frFR_AMale));
							contextCreated = indexStoreSolr(languageName, classDoc, "contextCreated", regexLanguage(languageName, "(context)?Cree", classComment, CONTEXT_frFR_CreeMasculin));
							contextModified = indexStoreSolr(languageName, classDoc, "contextModified", regexLanguage(languageName, "(context)?Modifie", classComment, CONTEXT_frFR_ModifieMasculin));
							contextActualName = indexStoreSolr(languageName, classDoc, "contextActualName", regexLanguage(languageName, "(context)?NomActuel", classComment, CONTEXT_frFR_CurrentMaleBefore + contextNameSingular + CONTEXT_frFR_CurrentMaleAfter));
		//					contextAll = indexStoreSolr(languageName, classDoc, "contextAll", regexLanguage(languageName, "(context)?Tous", classComment, CONTEXT_frFR_AllMalePlural));
							contextAllName = indexStoreSolr(languageName, classDoc, "contextAllName", regexLanguage(languageName, "(context)?TousNom", classComment, CONTEXT_frFR_AllMalePlural + contextNamePlural));
							contextSearchrTousNomPar = indexStoreSolr(languageName, classDoc, "contextSearchrTousNomPar", regexLanguage(languageName, "(context)?SearchTousNomPar", classComment, CONTEXT_frFR_Searchr + contextNamePlural + CONTEXT_frFR_Par));
							contextSearchrTousNom = indexStoreSolr(languageName, classDoc, "contextSearchrTousNom", regexLanguage(languageName, "(context)?SearchTousNomPar", classComment, CONTEXT_frFR_Searchr + contextNamePlural));
							contextNoneNameFound = indexStoreSolr(languageName, classDoc, "contextNoneNameFound", regexLanguage(languageName, "(context)?AucunNomTrouve", classComment, CONTEXT_frFR_NoneFoundMaleBefore + contextNameSingular + CONTEXT_frFR_NoneFoundMaleAfter));
							if(contextAdjective != null) {
								if(CONTEXT_frFR_AdjectiveBefore)
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_frFR_AMale + " " + contextAdjective + " " + contextNameSingular));
								else
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_frFR_AMale + " " + contextNameSingular + " " + contextAdjective));
							}
		
							String suffixe = StringUtils.substringAfter(contextAName, " ");
							String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
								contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?CeNom", classComment, CONTEXT_frFR_ThisMaleVowel + suffixe));
								contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?LeNom", classComment, CONTEXT_frFR_TheMaleVowel + suffixe));
								contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?DeNom", classComment, CONTEXT_frFR_OfVowel + suffixe));
								if(contextAdjective != null) {
									if(CONTEXT_frFR_AdjectiveBefore)
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheMaleVowel + " " + contextAdjective + " " + contextNameSingular));
									else
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheMaleVowel + " " + contextNameSingular + " " + contextAdjective));
								}
							}
							else {
								contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?CeNom", classComment, CONTEXT_frFR_ThisMaleConsonant + suffixe));
								contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?LeNom", classComment, CONTEXT_frFR_TheMaleConsonant + suffixe));
								contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?DeNom", classComment, CONTEXT_frFR_OfConsonant + suffixe));
								if(contextAdjective != null) {
									if(CONTEXT_frFR_AdjectiveBefore)
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheMaleConsonant + " " + contextAdjective + " " + contextNameSingular));
									else
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheMaleConsonant + " " + contextNameSingular + " " + contextAdjective));
								}
							}
						}
						indexStoreSolr(languageName, classDoc, "contextThisLowercase", contextThis); 
					}
					else if("enUS".equals(languageName)) {
						indexStoreSolr(languageName, classDoc, "contextAName", contextAName); 
						contextNameSingular = indexStoreSolr(languageName, classDoc, "contextNameSingular", regexLanguage(languageName, "(context)?NomSingulier", classComment, StringUtils.substringAfter(contextAName, " ")));
						contextNamePlural = indexStoreSolr(languageName, classDoc, "contextNamePlural", regexLanguage(languageName, "(context)?NomPluriel", classComment, contextNameSingular + "s"));
						contextNameVar = indexStoreSolr(languageName, classDoc, "contextNameVar", regexLanguage(languageName, "(context)?NomVar", classComment, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contextNameSingular, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
						contextTheNames = indexStoreSolr(languageName, classDoc, "contextTheNames", regexLanguage(languageName, "(context)?TheName", classComment, CONTEXT_enUS_ThePlural + contextNamePlural));
		
						contextAdjective = regexLanguage(languageName, "(context)?Adjective", classComment);
						if(contextAdjective != null) {
							contextAdjectivePlural = indexStoreSolr(languageName, classDoc, "contextAdjectivePlural", regexLanguage(languageName, "(context)?AdjectivePlural", classComment, contextAdjective + CONTEXT_enUS_AdjectivePlural));
							contextAdjectiveVar = indexStoreSolr(languageName, classDoc, "contextAdjectiveVar", regexLanguage(languageName, "(context)?AdjectiveVar", classComment, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contextAdjective, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
							if(CONTEXT_enUS_AdjectiveBefore) {
								contextNameAdjectiveSingular = indexStoreSolr(languageName, classDoc, "contextNameAdjectiveSingular", regexLanguage(languageName, "(context)?NameAdjectiveSingular", classComment, contextAdjective + " " + contextNameSingular));
								contextNameAdjectivePlural = indexStoreSolr(languageName, classDoc, "contextNameAdjectivePlural", regexLanguage(languageName, "(context)?NomAdjectivePlural", classComment, contextAdjectivePlural + " " + contextNamePlural));
							}
							else {
								contextNameAdjectiveSingular = indexStoreSolr(languageName, classDoc, "contextNameAdjectiveSingular", regexLanguage(languageName, "(context)?NameAdjectiveSingular", classComment, contextNameSingular + " " + contextAdjective));
								contextNameAdjectivePlural = indexStoreSolr(languageName, classDoc, "contextNameSingularPluriel", regexLanguage(languageName, "(context)?NomSingulierPluriel", classComment, contextNamePlural + " " + contextAdjectivePlural));
							}
		
						}
		
						contextThis = indexStoreSolr(languageName, classDoc, "contextThis", regexLanguage(languageName, "(context)?Ce", classComment, CONTEXT_enUS_CetteConsonne));
						contextA = indexStoreSolr(languageName, classDoc, "contextA", regexLanguage(languageName, "(context)?Un", classComment, CONTEXT_enUS_Une));
						contextCreated = indexStoreSolr(languageName, classDoc, "contextCreated", regexLanguage(languageName, "(context)?Cree", classComment, CONTEXT_enUS_Creee));
						contextModified = indexStoreSolr(languageName, classDoc, "contextModified", regexLanguage(languageName, "(context)?Modifie", classComment, CONTEXT_enUS_Modifiee));
						contextActualName = indexStoreSolr(languageName, classDoc, "contextActualName", regexLanguage(languageName, "(context)?NomActuel", classComment, CONTEXT_enUS_ActuelleBefore + contextNameSingular + CONTEXT_enUS_ActuelleAfter));
	//					contextAll = indexStoreSolr(languageName, classDoc, "contextAll", regexLanguage(languageName, "(context)?Tous", classComment, CONTEXT_enUS_ToutesPluriel));
						contextAllName = indexStoreSolr(languageName, classDoc, "contextAllName", regexLanguage(languageName, "(context)?TousNom", classComment, CONTEXT_enUS_ThePlural + contextNamePlural));
						contextSearchrTousNomPar = indexStoreSolr(languageName, classDoc, "contextSearchrTousNomPar", regexLanguage(languageName, "(context)?SearchTousNomPar", classComment, CONTEXT_enUS_Searchr + contextNamePlural + CONTEXT_enUS_Par));
						contextSearchrTousNom = indexStoreSolr(languageName, classDoc, "contextSearchrTousNomr", regexLanguage(languageName, "(context)?SearchTousNom", classComment, CONTEXT_enUS_Searchr + contextNamePlural));
						contextNoneNameFound = indexStoreSolr(languageName, classDoc, "contextNoneNameFound", regexLanguage(languageName, "(context)?AucunNomTrouve", classComment, CONTEXT_enUS_AucuneTrouveBefore + contextNameSingular + CONTEXT_enUS_AucuneTrouveAfter));
						if(contextAdjective != null) {
							if(CONTEXT_enUS_AdjectiveBefore)
								contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_enUS_Une + " " + contextAdjective + " " + contextNameSingular));
							else
								contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_enUS_Une + " " + contextNameSingular + " " + contextAdjective));
						}
	
						String suffixe = StringUtils.substringAfter(contextAName, " ");
						String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
						if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
							contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?CeNom", classComment, CONTEXT_enUS_CetteVoyelle + suffixe));
							contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?LeNom", classComment, CONTEXT_enUS_LVoyelle + suffixe));
							contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?DeNom", classComment, CONTEXT_enUS_OfVowel + suffixe));
							if(contextAdjective != null) {
								if(CONTEXT_enUS_AdjectiveBefore)
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_enUS_LVoyelle + " " + contextAdjective + " " + contextNameSingular));
								else
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_enUS_LVoyelle + " " + contextNameSingular + " " + contextAdjective));
							}
						}
						else {
							contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?CeNom", classComment, CONTEXT_enUS_CetteConsonne + suffixe));
							contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?LeNom", classComment, CONTEXT_enUS_LaConsonne + suffixe));
							contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?DeNom", classComment, CONTEXT_enUS_OfConsonant + suffixe));
							if(contextAdjective != null) {
								if(CONTEXT_enUS_AdjectiveBefore)
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_enUS_LaConsonne + " " + contextAdjective + " " + contextNameSingular));
								else
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_enUS_LaConsonne + " " + contextNameSingular + " " + contextAdjective));
							}
						}
						indexStoreSolr(languageName, classDoc, "contextThisLowercase", contextThis); 
					}
				}
	
				contextTitle = regexLanguage(languageName, "(context)?Title", classComment);
				if(contextTitle != null)
					indexStoreSolr(languageName, classDoc, "contextTitle", contextTitle); 
	
				contextH1 = regexLanguage(languageName, "(context)?H1", classComment);
				if(contextH1 != null)
					indexStoreSolr(languageName, classDoc, "contextH1", contextH1); 
	
				contextH2 = regexLanguage(languageName, "(context)?H2", classComment);
				if(contextH2 != null)
					indexStoreSolr(languageName, classDoc, "contextH2", contextH2); 
	
				contextH3 = regexLanguage(languageName, "(context)?H3", classComment);
				if(contextH3 != null)
					indexStoreSolr(languageName, classDoc, "contextH3", contextH3); 
			}
		}

		Boolean classIndexed = indexStoreSolr(classDoc, "classIndexed", regexFound("^(class)?Indexed:\\s*(true)$", classComment) || classSaved || classModel || classPage);
		Boolean classImage = indexStoreSolr(classDoc, "classImage", regexFound("^(class)?Image:\\s*(true)$", classComment));

		if(classIndexed) {
			classPartsGenAdd(classPartsSolrInputDocument);
			classPartsGenAdd(classPartsSolrClient);
//			classPartsGenAdd(classPartsTest);
			classPartsGenAdd(classPartsSiteContext);
			classPartsGenAdd(classPartsSolrDocument);
			classPartsGenAdd(classPartsList);
			classPartsGenAdd(classPartsArrayList);
			classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrQuery", classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.response.QueryResponse", classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.util.ClientUtils", classLangueNom));
		}

		if(classImage) {
			classPartsGenAdd(ClassParts.initClassParts(this, DefaultExecutor.class.getCanonicalName(), classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, CommandLine.class.getCanonicalName(), classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, File.class.getCanonicalName(), classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, BufferedImage.class.getCanonicalName(), classLangueNom));
			classPartsGenAdd(ClassParts.initClassParts(this, ImageIO.class.getCanonicalName(), classLangueNom));
		}

		for(ClassParts classPartGen : classPartsGen.values()) {
			indexStoreListSolr(classLangueNom, classDoc, "classImportsGen", classPartGen.canonicalName);
			for(String languageName : classOtherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classPartGen, languageName);
				indexStoreListSolr(languageName, classDoc, "classImportsGen", classImportClassPartsLanguage.canonicalName);
			}
		}

		for(ClassParts classPartGenApi : classPartsGenApi.values()) {
			if(classPartGenApi.languageName == null || classPartGenApi.languageName.equals(languageName))
				indexStoreListSolr(classLangueNom, classDoc, "classImportsGenApi", classPartGenApi.canonicalName);
			if(classTranslate) {
				for(String languageName : classOtherLanguages) {  
					ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classPartGenApi, languageName);
					if(classImportClassPartsLanguage.languageName == null || classImportClassPartsLanguage.languageName.equals(languageName))
						indexStoreListSolr(languageName, classDoc, "classImportsGenApi", classImportClassPartsLanguage.canonicalName);
				}
			}
		}

		if(classSuperDoc != null) {
			for(String languageName : toutesLangues) {
				List<String> classSuperEntityVars = (List<String>)classSuperDoc.get("classEntityVars_" + languageName + "_stored_strings");
				if(classSuperEntityVars != null) {
					for(String classSuperEntityVar : classSuperEntityVars)
						indexStoreListSolr(languageName, classDoc, "classEntityVars", classSuperEntityVar);
				}
			}
		}

		if(classSuperDoc != null) {
			for(String languageName : toutesLangues) {
				List<String> classSuperMethodVars = (List<String>)classSuperDoc.get("classMethodVars_" + languageName + "_stored_strings");
				if(classSuperMethodVars != null) {
					for(String classSuperMethodeVar : classSuperMethodVars)
						indexStoreListSolr(languageName, classDoc, "classMethodVars", classSuperMethodeVar);
				}
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
