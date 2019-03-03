package org.computate.frFR.java; 

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

/**
 * NomCanonique.enUS: org.computate.enUS.java.IndexClass
 */ 
public class IndexerClasse extends RegarderClasseBase { 
	/**
	 * Var.enUS: VAL_canonicalNameString
	 */
	public static final String VAL_nomCanoniqueString = String.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameBoolean
	 */
	public static final String VAL_nomCanoniqueBoolean = Boolean.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameDate
	 */
	public static final String VAL_nomCanoniqueDate = Date.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameLong
	 */
	public static final String VAL_nomCanoniqueLong = Long.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameDouble
	 */
	public static final String VAL_nomCanoniqueDouble = Double.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameFloat
	 */
	public static final String VAL_nomCanoniqueFloat = Float.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameBigDecimal
	 */
	public static final String VAL_nomCanoniqueBigDecimal = BigDecimal.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameInteger
	 */
	public static final String VAL_nomCanoniqueInteger = Integer.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameTimestamp
	 */
	public static final String VAL_nomCanoniqueTimestamp = Timestamp.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameLocalDateTime
	 */
	public static final String VAL_nomCanoniqueLocalDateTime = LocalDateTime.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameZonedDateTime
	 */
	public static final String VAL_nomCanoniqueZonedDateTime = ZonedDateTime.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameLocalDate
	 */
	public static final String VAL_nomCanoniqueLocalDate = LocalDate.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameList
	 */
	public static final String VAL_nomCanoniqueList = List.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameArrayList
	 */
	public static final String VAL_nomCanoniqueArrayList = ArrayList.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameSet
	 */
	public static final String VAL_nomCanoniqueSet = Set.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameHashSet
	 */
	public static final String VAL_nomCanoniqueHashSet = HashSet.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameInstant
	 */
	public static final String VAL_nomCanoniqueInstant = Instant.class.getCanonicalName();
	/**
	 * Var.enUS: VAL_canonicalNameVertxJsonArray
	 */
	public static final String VAL_nomCanoniqueVertxJsonArray = "io.vertx.core.json.JsonArray";
	/**
	 * Var.enUS: VAL_canonicalNameVertxJsonObject
	 */
	public static final String VAL_nomCanoniqueVertxJsonObject = "io.vertx.core.json.JsonObject";

	/**
	 * Var.enUS: classPartsSolrInputDocument
	 */
	ClasseParts classePartsSolrInputDocument;
	/**
	 * Var.enUS: classPartsSolrDocument
	 */
	ClasseParts classePartsSolrDocument;
	/**
	 * Var.enUS: classPartsSolrClient
	 */
	ClasseParts classePartsSolrClient;
	/**
	 * Var.enUS: classPartsTest
	 */
	ClasseParts classePartsTest;
	/**
	 * Var.enUS: classPartsList
	 */
	ClasseParts classePartsList;
	/**
	 * Var.enUS: classPartsArrayList
	 */
	ClasseParts classePartsArrayList;
	/**
	 * Var.enUS: classPartsHashSet
	 */
	ClasseParts classePartsHashSet;
	/**
	 * Var.enUS: classPartsSiteContext
	 */
	ClasseParts classePartsSiteContexte;
	/**
	 * Var.enUS: classPartsSiteConfig
	 */
	ClasseParts classePartsConfigSite;
	/**
	 * Var.enUS: classPartsSiteUser
	 */
	ClasseParts classePartsUtilisateurSite;
	/**
	 * Var.enUS: classPartsCluster
	 */
	ClasseParts classePartsCluster;
	/**
	 * Var.enUS: classPartsSearchResult
	 */
	ClasseParts classePartsResultatRecherche;
	/**
	 * Var.enUS: classPartsAllWriter
	 */
	ClasseParts classePartsToutEcrivain;
	/**
	 * Var.enUS: classPartsWrap
	 */
	ClasseParts classePartsCouverture;
	/**
	 * Var.enUS: classPartsSearchList
	 */
	ClasseParts classePartsListeRecherche;
	/**
	 * Var.enUS: classPartsPageLayout
	 */
	ClasseParts classePartsMiseEnPage;

	/**
	 * Var.enUS: CONTEXT_AMale
	 * String.enUS:an 
	 */ 
	String CONTEXTE_UnMasculin = "un ";
	/**
	 * Var.enUS: CONTEXT_AFemale
	 * String.enUS:an 
	 */
	String CONTEXTE_UneFeminin = "une ";

	/**
	 * Var.enUS: CONTEXT_ThisMaleVowel
	 * String.enUS:this 
	 */ 
	String CONTEXTE_CetMasculinVoyelle = "cet ";
	/**
	 * Var.enUS: CONTEXT_ThisFemaleVowel
	 * String.enUS:this 
	 */
	String CONTEXTE_CetteFemininVoyelle = "cette ";
	/**
	 * Var.enUS: CONTEXT_ThisMaleConsonant
	 * String.enUS:this 
	 */  
	String CONTEXTE_CeMasculinConsonne = "ce ";
	/**
	 * Var.enUS: CONTEXT_ThisFemaleConsonant
	 * String.enUS:this 
	 */
	String CONTEXTE_CetteFemininConsonne = "cette ";
	/**
	 * Var.enUS: CONTEXT_ThesePlural
	 * String.enUS:this 
	 */
	String CONTEXTE_CesPluriel = "ces ";

	/**
	 * Var.enUS: CONTEXT_CreatedMale
	 * String.enUS:this 
	 */  
	String CONTEXTE_CreeMasculin = "créé ";
	/**
	 * Var.enUS: CONTEXT_CreatedFemale
	 * String.enUS:this 
	 */
	String CONTEXTE_CreeeFeminin = "créée ";

	/**
	 * Var.enUS: CONTEXT_ModifiedMale
	 * String.enUS:this 
	 */  
	String CONTEXTE_ModifieMasculin = "modifié ";
	/**
	 * Var.enUS: CONTEXT_ModifiedFemale
	 * String.enUS:this 
	 */
	String CONTEXTE_ModifieeFeminin = "modifiée ";

	/** 
	 * Var.enUS: CONTEXT_TheMaleVowel
	 * String.enUS:the 
	 */ 
	String CONTEXTE_LMasculinVoyelle = "l'";
	/**
	 * Var.enUS: CONTEXT_TheFemaleVowel
	 * String.enUS:the 
	 */
	String CONTEXTE_LFemininVoyelle = "l'";
	/**
	 * Var.enUS: CONTEXT_TheMaleConsonant
	 * String.enUS:the 
	 */ 
	String CONTEXTE_LeMasculinConsonne = "le ";
	/**
	 * Var.enUS: CONTEXT_TheFemaleConsonant
	 * String.enUS:the 
	 */
	String CONTEXTE_LaFemininConsonne = "la ";
	/**
	 * Var.enUS: CONTEXT_ThePlural
	 * String.enUS:the 
	 */
	String CONTEXTE_LesPluriel = "les ";

	/**
	 * Var.enUS: CONTEXT_CurrentMaleBefore
	 * String.enUS:current 
	 */ 
	String CONTEXTE_ActuelMasculinAvant = "";
	/**
	 * Var.enUS: CONTEXT_CurrentFemaleBefore
	 * String.enUS:current 
	 */
	String CONTEXTE_ActuelleFemininAvant = "";
	/**
	 * Var.enUS: CONTEXT_CurrentMaleAfter
	 * String.enUS:
	 */ 
	String CONTEXTE_ActuelMasculinApres = " actuel";
	/**
	 * Var.enUS: CONTEXT_CurrentFemaleAfter
	 * String.enUS:
	 */ 
	String CONTEXTE_ActuelleFemininApres = " actuelle";

	/**
	 * Var.enUS: CONTEXT_AllMalePlural
	 * String.enUS:all 
	 */
	String CONTEXTE_TousMasculinPluriel = "tous les ";
	/**
	 * Var.enUS: CONTEXT_AllFemalePlural
	 * String.enUS:all 
	 */
	String CONTEXTE_ToutesFemininPluriel = "toutes les ";

	/**
	 * Var.enUS: CONTEXT_NoneFoundMaleBefore
	 * String.enUS:no 
	 */
	String CONTEXTE_AucunTrouveMasculinAvant = "aucun ";
	/**
	 * Var.enUS: CONTEXT_NoneFemaleBefore
	 * String.enUS:no 
	 */
	String CONTEXTE_AucuneTrouveFemininAvant = "aucune ";
	/**
	 * Var.enUS: CONTEXT_NoneFoundMaleAfter
	 * String.enUS: found
	 */
	String CONTEXTE_AucunTrouveMasculinApres = " trouvé";
	/**
	 * Var.enUS: CONTEXT_NoneFemaleAfter
	 * String.enUS: found
	 */
	String CONTEXTE_AucuneTrouveFemininApres = " trouvée";

	/**
	 * Var.enUS: CONTEXT_OfVowel
	 * String.enUS:of 
	 */
	String CONTEXTE_DVoyelle = "d'";
	/**
	 * Var.enUS: CONTEXT_OfConsonant
	 * String.enUS:of 
	 */  
	String CONTEXTE_DeConsonne = "de ";

	/**
	 * Var.enUS: CONTEXT_AdjectivePlural
	 * String.enUS:
	 */  
	String CONTEXTE_AdjectifPluriel = "s";

	/**
	 * Var.enUS: CONTEXT_AdjectiveBefore
	 * r: false
	 * r.enUS: true
	 */  
	Boolean CONTEXTE_AdjectifAvant = false;

	/**
	 * Var.enUS: classPartsGenApi
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 */
	protected HashMap<String, ClasseParts> classePartsGenApi = new HashMap<String, ClasseParts>();
	/**
	 * Var.enUS: classPartsGenPage
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 */ 
	protected HashMap<String, ClasseParts> classePartsGenPage = new HashMap<String, ClasseParts>();

	/**
	 * Var.enUS: contextAName
	 */
	private String contexteUnNom;

	/**
	 * Var.enUS: contextThis
	 */
	private String contexteCe;

	/**
	 * Var.enUS: contextThisName
	 */
	private String contexteCeNom;

	/**
	 * Var.enUS: contextA
	 */
	private String contexteUn;

	/**
	 * Var.enUS: contextCreated
	 */
	private String contexteCree;

	/**
	 * Var.enUS: contextModified
	 */
	private String contexteModifie;

	/**
	 * Var.enUS: contextTheName
	 */
	private String contexteLeNom;

	/**
	 * Var.enUS: contextNameSingular
	 */
	private String contexteNomSingulier;

	/**
	 * Var.enUS: contextNamePlural
	 */
	private String contexteNomPluriel;

	/**
	 * Var.enUS: contextActualName
	 */
	private String contexteNomActuel;

	/**
	 * Var.enUS: contextAll
	 */
	private String contexteTous;

	/**
	 * Var.enUS: contextAllName
	 */
	private String contexteTousNom;

	/**
	 * Var.enUS: contextTheNames
	 */
	private String contexteLesNoms;

	/**
	 * Var.enUS: contextNoneNameFound
	 */
	private String contexteAucunNomTrouve;

	/**
	 * Var.enUS: contextNameVar
	 */
	private String contexteNomVar;

	/**
	 * Var.enUS: contextOfName
	 */
	private String contexteDeNom;

	/**
	 * Var.enUS: contextAdjective
	 */
	private String contexteAdjectif;

	/**
	 * Var.enUS: contextAdjectivePlural
	 */
	private String contexteAdjectifPluriel;

	/**
	 * Var.enUS: contextAdjectiveVar
	 */
	private String contexteAdjectifVar;

	/**
	 * Var.enUS: contextANameAdjective
	 */
	private String contexteUnNomAdjectif;

	/**
	 * Var.enUS: contextNameAdjectiveSingular
	 */
	private String contexteNomAdjectifSingulier;

	/**
	 * Var.enUS: contextNameAdjectivePlural
	 */
	private String contexteNomAdjectifPluriel;

	/**
	 * Var.enUS: contextTitle
	 */
	private String contexteTitre;

	/**
	 * Var.enUS: contextH1
	 */
	private String contexteH1;

	/**
	 * Var.enUS: contextH2
	 */
	private String contexteH2;

	/**
	 * Var.enUS: contextH3
	 */
	private String contexteH3;

	/**
	 * Var.enUS: contextColor
	 */
	private String contexteCouleur;

	/**
	 * Var.enUS: contextIconGroup
	 */
	private String contexteIconeGroupe;

	/**
	 * Var.enUS: contextIconName
	 */
	private String contexteIconeNom;

	/**
	 * Var.enUS: populateQdoxSuperClassesInterfacesAndMe
	 * Param2.var.enUS: qdoxSuperClasses
	 * Param3.var.enUS: qdoxSuperClassesAndMe
	 * Param4.var.enUS: qdoxSuperClassesAndMeWithoutGen
	 * Param5.var.enUS: qdoxSuperClassesAndInterfaces
	 * Param6.var.enUS: qdoxSuperClassesInterfacesAndMe
	 * r: classeSuper
	 * r.enUS: superClass
	 * r: interfacesImplementees
	 * r.enUS: interfacesImplemented
	 * r: classesSuperQdoxInterfacesEtMoi
	 * r.enUS: qdoxSuperClassesInterfacesAndMe
	 * r: classesSuperQdoxEtInterfaces
	 * r.enUS: qdoxSuperClassesAndInterfaces
	 * r: classesSuperQdoxEtMoiSansGen
	 * r.enUS: qdoxSuperClassesAndMeWithoutGen
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: qdoxSuperClassesAndMe
	 * r: classesSuperQdox
	 * r.enUS: qdoxSuperClasses
	 * r: peuplerClassesSuperQdoxInterfacesEtMoi
	 * r.enUS: populateQdoxSuperClassesInterfacesAndMe
	 */       
	public void peuplerClassesSuperQdoxInterfacesEtMoi (
			JavaClass c
			, ArrayList<JavaClass> classesSuperQdox
			, ArrayList<JavaClass> classesSuperQdoxEtMoi
			, ArrayList<JavaClass> classesSuperQdoxEtMoiSansGen
			, ArrayList<JavaClass> classesSuperQdoxEtInterfaces
			, ArrayList<JavaClass> classesSuperQdoxInterfacesEtMoi
			) throws Exception { 
		if(c != null) {
			JavaClass classeSuper = c.getSuperJavaClass();
			List<JavaClass> interfacesImplementees = c.getInterfaces();

			for(JavaClass interfaceQdox : interfacesImplementees) {
				if(interfaceQdox != null && !interfaceQdox.getCanonicalName().equals("java.lang.Object") && !c.equals(interfaceQdox)) {
					classesSuperQdoxInterfacesEtMoi.add(interfaceQdox);
					classesSuperQdoxEtInterfaces.add(classeSuper);
					peuplerClassesSuperQdoxInterfacesEtMoi(interfaceQdox, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtMoiSansGen, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi); // Doesn't seem to work for interfaces that extend other interfaces.
				}
			}
			classesSuperQdoxInterfacesEtMoi.add(c);
			classesSuperQdoxEtMoi.add(c);
			if(!StringUtils.endsWith(c.getCanonicalName(), "Gen"))
				classesSuperQdoxEtMoiSansGen.add(c);
			try {
				if(classeSuper != null && !classeSuper.getCanonicalName().equals("java.lang.Object") && !c.equals(classeSuper)) {
					classesSuperQdoxEtInterfaces.add(classeSuper);
					classesSuperQdox.add(classeSuper);
					peuplerClassesSuperQdoxInterfacesEtMoi(classeSuper, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtMoiSansGen, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi);
				}
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * Var.enUS: storeSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean stockerSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_boolean"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean stockerSolr(SolrInputDocument doc, String nomChamp, String langueNom, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_boolean"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_string"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerListeSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_strings"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean stockerListeSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_booleans"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String stockerListeSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: fieldValues
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeursChamp
	 * r.enUS: fieldValues
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected List<String> stockerSolr(SolrInputDocument doc, String nomChamp, String langueNom, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	}
	
	/**
	 * Var.enUS: indexSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexed_string"), valeurChamp);
		return valeurChamp;
	} 
	
	/**
	 * Var.enUS: indexListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerListeSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexed_booleans"), valeurChamp);
		return valeurChamp;
	} 
	
	/**
	 * Var.enUS: indexListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */ 
	protected String indexerListeSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexed_strings"), valeurChamp);
		return valeurChamp;
	} 
	
	/**
	 * Var.enUS: indexSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerSolr(SolrInputDocument doc, String nomChamp, String langueNom, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_indexed_string"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerListeSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: fieldValues
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeursChamp
	 * r.enUS: fieldValues
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected List<String> indexerSolr(SolrInputDocument doc, String nomChamp, String langueNom, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	} 
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Long indexerStockerSolr(SolrInputDocument doc, String nomChamp, Long valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_long"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_long"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Double indexerStockerSolr(SolrInputDocument doc, String nomChamp, Double valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_double"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_double"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Integer indexerStockerSolr(SolrInputDocument doc, String nomChamp, Integer valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_int"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_int"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerStockerSolr(SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_boolean"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_boolean"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Date indexerStockerSolr(SolrInputDocument doc, String nomChamp, Date valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_date"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_date"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_string"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_string"), valeurChamp);
		return valeurChamp;
	} 
	
	/**
	 * Var.enUS: indexStoreSolrRegex
	 * Param2.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldNameRegex
	 * Param5.var.enUS: comment
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 * r: valeurDefaut
	 * r.enUS: defaultValue
	 * r: commentaire
	 * r.enUS: comment
	 * r: langueNom
	 * r.enUS: languageName
	 * r: indexerStocker
	 * r.enUS: indexStore
	 */
	protected String indexerStockerSolrRegex(SolrInputDocument doc, String langueNom, String nomChamp, String nomChampRegex, String commentaire) throws Exception {
		return indexerStockerSolrRegex(doc, langueNom, nomChamp, nomChampRegex, commentaire, null);
	}
	
	/**
	 * Var.enUS: indexStoreSolrRegex
	 * Param2.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldNameRegex
	 * Param5.var.enUS: comment
	 * Param6.var.enUS: defaultValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 * r: valeurDefaut
	 * r.enUS: defaultValue
	 * r: commentaire
	 * r.enUS: comment
	 * r: langueNom
	 * r.enUS: languageName
	 */
	protected String indexerStockerSolrRegex(SolrInputDocument doc, String langueNom, String nomChamp, String nomChampRegex, String commentaire, String valeurDefaut) throws Exception {
		String valeurChamp = null;
		if(nomChampRegex != null && commentaire != null) {
			Matcher m = Pattern.compile("^" + nomChampRegex + "(." + langueNom + ")?:\\s*(.*)", Pattern.MULTILINE).matcher(commentaire);
			if(m.find()) {
				valeurChamp = m.group(2);
			}
		}
		if(StringUtils.isBlank(valeurChamp)) {
			valeurChamp = valeurDefaut;
		}
		if(StringUtils.isNotBlank(valeurChamp)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}

	
	/**
	 * Var.enUS: indexStoreListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerListeSolr(SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		doc.addField(concat(nomChamp, "_stored_strings"), valeurChamp);
		doc.addField(concat(nomChamp, "_indexed_strings"), valeurChamp);
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreListSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: fieldValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected String indexerStockerListeSolr(SolrInputDocument doc, String nomChamp, String langueNom, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: languageName
	 * Param4.var.enUS: fieldValues
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: valeursChamp
	 * r.enUS: fieldValues
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected List<String> indexerStockerSolr(SolrInputDocument doc, String nomChamp, String langueNom, List<String> valeursChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			for(String valeurChamp : valeursChamp) {
				doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
				doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
			}
		}
		return valeursChamp;
	}

	/**
	 * Var.enUS: classDocsAdd
	 * Param1.var.enUS: canonicalName
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: partEstClasse
	 * r.enUS: partIsClass
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeDocs
	 * r.enUS: classDocs
	 */
	protected SolrDocument classeDocsAjouter(String nomCanonique) throws Exception {
		SolrDocument doc = null;
		if(StringUtils.startsWith(nomEnsembleDomaine, nomCanonique)) {
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1);
			rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(nomCanonique));
			rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
			rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
			QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
			SolrDocumentList listeRecherche = reponseRecherche.getResults();
			if(listeRecherche.size() > 0) {
				doc = listeRecherche.get(0);
				classeDocs.put(nomCanonique, doc);
			}
		}
		return doc;
	}
	
	/**
	 * Var.enUS: searchCanonicalName
	 * Param1.var.enUS: languageName
	 * Param2.var.enUS: canonicalName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: classeDocsAjouter
	 * r.enUS: classDocsAdd
	 */
	protected String rechercherNomCanonique(String langueNom, String nomCanonique) throws Exception {
		SolrDocument doc = classeDocsAjouter(nomCanonique);
		String val = null;
		if(doc != null) {
			val = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
		}
		if(StringUtils.isEmpty(val)) {
			val = nomCanonique;  
		}
		return val;
	} 

	////////////
	// autres //
	////////////

	/**
	 * Var.enUS: classPartsForSimpleName
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: simpleName
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: classeParts
	 * r.enUS: classParts
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: partEstClasse
	 * r.enUS: partIsClass
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: nomSimple
	 * r.enUS: simpleName
	 */
	protected ClasseParts classePartsPourNomSimple(String nomEnsembleDomaine, String nomSimple) throws Exception {
		ClasseParts classeParts = null;
		SolrDocument doc = null;
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:" + nomSimple);
		rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
		rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		if(listeRecherche.size() > 0) {
			doc = listeRecherche.get(0);
			String nomCanonique = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
			classeParts = ClasseParts.initClasseParts(this, nomCanonique, langueNom);
		}
		return classeParts;
	}

	/**
	 * Var.enUS: classPartsWrap
	 * Param1.var.enUS: domainPackageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: Couverture
	 * r.enUS: Wrap
	 */
	protected ClasseParts classePartsCouverture(String nomEnsembleDomaine) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "Couverture");
	}

	/**
	 * Var.enUS: classPartsChain
	 * Param1.var.enUS: domainPackageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: Chaine
	 * r.enUS: Chain
	 */
	protected ClasseParts classePartsChaine(String nomEnsembleDomaine) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "Chaine");
	}

	/**
	 * Var.enUS: classPartsSiteRequest
	 * Param1.var.enUS: domainPackageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 */
	protected ClasseParts classePartsRequeteSite(String nomEnsembleDomaine) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "RequeteSite");
	}

	/**
	 * Var.enUS: classPartsSiteContext
	 * Param1.var.enUS: domainPackageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 */
	protected ClasseParts classePartsSiteContexte(String nomEnsembleDomaine) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "SiteContexte");
	}

	/**
	 * Var.enUS: classPartsSiteConfig
	 * Param1.var.enUS: domainPackageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 */
	protected ClasseParts classePartsConfigSite(String nomEnsembleDomaine) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "ConfigSite");
	}

	/**
	 * Var.enUS: classPartsSiteUser
	 * Param1.var.enUS: domainPackageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: UtilisateurSite
	 * r.enUS: SiteUser
	 */
	protected ClasseParts classePartsUtilisateurSite(String nomEnsembleDomaine) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "UtilisateurSite");
	}

	/**
	 * Var.enUS: classPartsCluster
	 * Param1.var.enUS: domainPackageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: Cluster
	 * r.enUS: Cluster
	 */
	protected ClasseParts classePartsCluster(String nomEnsembleDomaine) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "Cluster");
	}

	/**
	 * Var.enUS: classPartsSearchResult
	 * Param1.var.enUS: domainPackageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ResultatRecherche
	 * r.enUS: SearchResult
	 */
	protected ClasseParts classePartsResultatRecherche(String nomEnsembleDomaine) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "ResultatRecherche");
	}

	/**
	 * Var.enUS: classPartsAllWriter
	 * Param1.var.enUS: domainPackageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 */
	protected ClasseParts classePartsToutEcrivain(String nomEnsembleDomaine) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "ToutEcrivain");
	}

	/**
	 * Var.enUS: classPartsSearchList
	 * Param1.var.enUS: domainPackageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 */
	protected ClasseParts classePartsListeRecherche(String nomEnsembleDomaine) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "ListeRecherche");
	}

	/**
	 * Var.enUS: classPartsPageLayout
	 * Param1.var.enUS: domainPackageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 */
	protected ClasseParts classePartsMiseEnPage(String nomEnsembleDomaine) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "MiseEnPage");
	}

	/**
	 * Var.enUS: storeRegexComments
	 * Param1.var.enUS: comment
	 * Param2.var.enUS: languageName
	 * Param4.var.enUS: entityVar
	 * r: langueNom
	 * r.enUS: languageName
	 * r: varEntite
	 * r.enUS: entityVar
	 * r: commentaire
	 * r.enUS: comment
	 * r: trouve
	 * r.enUS: found
	 * r: langue
	 * r.enUS: language
	 * r: texte
	 * r.enUS: text
	 * r: stockerSolr
	 * r.enUS: storeSolr
	 */
	public String stockerRegexCommentaires(String commentaire, String langueNom, SolrInputDocument doc, String varEntite) throws Exception {
		if(!StringUtils.isEmpty(commentaire)) {
			Matcher m = Pattern.compile("^(enUS|frFR): (.*)", Pattern.MULTILINE).matcher(commentaire);
			boolean trouve = m.find();
			StringBuilder b = new StringBuilder();
			
			while(trouve) {
				String langue = m.group(1);
				String texte = m.group(2);
				if(langueNom.equals(langue))
					b.append(texte).append("\n");

				trouve = m.find();
			}
			if(StringUtils.isNotEmpty(b))
				stockerSolr(doc, varEntite, langueNom, b.toString());
		}
		return commentaire;
	}  

	/**
	 * Var.enUS: classPartsGenAdd
	 * Param1.var.enUS: classParts
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 */
	public void classePartsGenAjouter(ClasseParts classeParts) {
		if(classePartsGen != null && classeParts != null && !classePartsGen.containsKey(classeParts.nomCanonique) && StringUtils.contains(classeParts.nomCanonique, ".") && !StringUtils.contains(classeParts.nomCanonique, ","))
			classePartsGen.put(classeParts.nomCanonique, classeParts);
	}

	/**
	 * Var.enUS: classPartsGenApiAdd
	 * Param1.var.enUS: classParts
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 */
	public void classePartsGenApiAjouter(ClasseParts classeParts) {
		if(classePartsGenApi != null && classeParts != null && !classePartsGenApi.containsKey(classeParts.nomCanonique) && StringUtils.contains(classeParts.nomCanonique, "."))
			classePartsGenApi.put(classeParts.nomCanonique, classeParts);
	}

	/**
	 * Var.enUS: classPartsGenPageAdd
	 * Param1.var.enUS: classParts
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 */
	public void classePartsGenPageAjouter(ClasseParts classeParts) {
		if(classePartsGenPage != null && classeParts != null && !classePartsGenPage.containsKey(classeParts.nomCanonique) && StringUtils.contains(classeParts.nomCanonique, "."))
			classePartsGenPage.put(classeParts.nomCanonique, classeParts);
	}

//
//	public SolrDocument documentSolr(ClasseParts classeParts) throws Exception {
//		SolrDocument documentSolr = classeParts.documentSolr(this);
//		return documentSolr;
//	}
//
//	public ClasseParts classeParts(JavaClass classeQdox, String langueNom) throws Exception {
//		String nomCanoniqueGenerique = classeQdox.getGenericCanonicalName();
//		ClasseParts resultat = classeParts.get(nomCanoniqueGenerique + "." + langueNom);
//		if(resultat == null) {
//			resultat = ClasseParts.initClasseParts(this, classeQdox, langueNom);
//			SolrDocument documentSolr = resultat.documentSolr(this);
//			resultat.documentSolr = documentSolr;
//			classeParts.put(nomCanoniqueGenerique + "." + langueNom, resultat);
//		}
//		return resultat;
//	}
//
//	public ClasseParts classeParts(ClasseParts classeParts, String langueNom) throws Exception {
//		ClasseParts resultat = ClasseParts.initClasseParts(this, classeParts, langueNom);
//		return resultat;
//	} 

	/**
	 * Var.enUS: indexClass
	 * Param1.var.enUS: classAbsolutePath
	 * Param2.var.enUS: classDoc
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: classeNomCanoniqueSuperGeneriqueLangue
	 * r.enUS: classSuperCanonicalNameGenericLanguage
	 * r: classeNomCanoniqueSuperGenerique
	 * r.enUS: classSuperCanonicalNameGeneric
	 * r: classeNomCanoniqueSuper
	 * r.enUS: classSuperCanonicalName
	 * r: classeNomCanoniqueGenLangue
	 * r.enUS: classCanonicalNameGenLanguage
	 * r: classeNomCanoniqueGen
	 * r.enUS: classCanonicalNameGen
	 * r: classeNomCanoniqueSuperDoc
	 * r.enUS: classSuperCanonicalNameDoc
	 * r: classeNomCanoniqueLangue
	 * r.enUS: classCanonicalNameLanguage
	 * r: classeNomCanoniquePageLangue
	 * r.enUS: classCanonicalNamePageLanguage
	 * r: classeNomCanoniqueApiLangue
	 * r.enUS: classCanonicalNameApiLanguage
	 * r: classeNomCanoniquePageGenLangue
	 * r.enUS: classCanonicalNamePageGenLanguage
	 * r: classeNomCanoniqueApiGenLangue
	 * r.enUS: classCanonicalNameApiGenLanguage
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: classeNomSimpleSuperGeneriqueLangue
	 * r.enUS: classSuperSimpleNameGenericLanguage
	 * r: classeNomSimpleSuperGenerique
	 * r.enUS: classSuperSimpleNameGeneric
	 * r: classeNomSimpleSuper
	 * r.enUS: classSuperSimpleName
	 * r: classeNomSimpleGenLangue
	 * r.enUS: classSimpleNameGenLanguage
	 * r: classeNomSimpleGen
	 * r.enUS: classSimpleNameGen
	 * r: classeNomSimpleSuperDoc
	 * r.enUS: classSuperSimpleNameDoc
	 * r: classeNomSimpleGenLangue
	 * r.enUS: classSimpleNameGenLanguage
	 * r: classeNomSimpleLangue
	 * r.enUS: classSimpleNameLanguage
	 * r: classeNomSimplePageLangue
	 * r.enUS: classSimpleNamePageLanguage
	 * r: classeNomSimpleGenPageLangue
	 * r.enUS: classSimpleNameGenPageLanguage
	 * r: classeNomSimpleGenApiServiceImplLangue
	 * r.enUS: classSimpleNameGenApiServiceImplLanguage
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: bricoleur
	 * r.enUS: builder
	 * r: classeQdoxSuper
	 * r.enUS: classSuperQdox
	 * r: classeQdoxRetour
	 * r.enUS: classReturnQdox
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 * r:  indexerStockerSolr
	 * r.enUS: indexStoreSolr
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeNomCompletSuperGeneriqueLangue
	 * r.enUS: classSuperCompleteNameGenericLanguage
	 * r: classeNomCompletSuperGenerique
	 * r.enUS: classSuperCompleteNameGeneric
	 * r: classeNomCompletSuperLangue
	 * r.enUS: classSuperCompleteNameLanguage
	 * r: classeNomCompletSuper
	 * r.enUS: classSuperCompleteName
	 * r: stockerRegexCommentaires
	 * r.enUS: storeRegexComments
	 * r: cheminSrcMainJavaLangue
	 * r.enUS: srcMainJavaPathLanguage
	 * r: cheminSrcGenJavaLangue
	 * r.enUS: srcGenJavaPathLanguage
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: cheminSrcMainResources
	 * r.enUS: srcMainResourcesPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 * r: commentaire
	 * r.enUS: comment
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: classeNomEnsembleLangue
	 * r.enUS: classPackageNameLanguage
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: classeCheminRepertoireGenLangue
	 * r.enUS: classDirPathGenLanguage
	 * r: classeCheminRepertoireGen
	 * r.enUS: classDirPathGen
	 * r: classeCheminRepertoireLangue
	 * r.enUS: classDirPathLanguage
	 * r: classeCheminRepertoire
	 * r.enUS: classDirPath
	 * r: classeCheminGenLangue
	 * r.enUS: classPathGenLanguage
	 * r: classeCheminGen
	 * r.enUS: classPathGen
	 * r: classeCheminAbsolu
	 * r.enUS: classPathAbsolute
	 * r: classeCheminLangue
	 * r.enUS: classPathLanguage
	 * r: classeChemin
	 * r.enUS: classPath
	 * r: peuplerClassesSuperQdoxInterfacesEtMoi
	 * r.enUS: populateQdoxSuperClassesInterfacesAndMe
	 * r: classeCle
	 * r.enUS: classKey
	 * r: modifieeDate
	 * r.enUS: modifiedDate
	 * r: classesSuperQdoxInterfacesEtMoi
	 * r.enUS: qdoxSuperClassesInterfacesAndMe
	 * r: classesSuperQdoxEtInterfaces
	 * r.enUS: qdoxSuperClassesAndInterfaces
	 * r: classesSuperQdoxEtMoiSansGen
	 * r.enUS: qdoxSuperClassesAndMeWithoutGen
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: qdoxSuperClassesAndMe
	 * r: classesSuperQdox
	 * r.enUS: qdoxSuperClasses
	 * r: modifiee
	 * r.enUS: modified
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: partEstClasse
	 * r.enUS: partIsClass
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: appliCheminLangue
	 * r.enUS: appPathLanguage
	 * r: appliChemins
	 * r.enUS: appPaths
	 * r: stockerSolr
	 * r.enUS: storeSolr
	 * r: rechercherNomCanonique
	 * r.enUS: searchCanonicalName
	 * r: regexRemplacerTout
	 * r.enUS: regexReplaceAll
	 * r: classeDocClone
	 * r.enUS: classDocClone
	 * r: partNumero
	 * r.enUS: partNumber
	 * r: classeImportationClassePartsLangue
	 * r.enUS: classImportClassPartsLanguage
	 * r: classeImportationClasseParts
	 * r.enUS: classeImportClassParts
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 * r: classeImportations
	 * r.enUS: classImports
	 * r: indexerStockerListeSolr
	 * r.enUS: indexStoreListSolr
	 * r: membresQdox
	 * r.enUS: membersQdox
	 * r: membreQdox
	 * r.enUS: memberQdox
	 * r: champDoc
	 * r.enUS: fieldDoc
	 * r: champQdox
	 * r.enUS: fieldQdox
	 * r: champCommentaire
	 * r.enUS: fieldComment
	 * r: champCle
	 * r.enUS: fieldKey
	 * r: champCodeSourceLangue
	 * r.enUS: fieldSourceCodeLanguage
	 * r: champCodeSource
	 * r.enUS: fieldSourceCode
	 * r: partEstChamp
	 * r.enUS: partIsField
	 * r: champEstPublic
	 * r.enUS: fieldIsPublic
	 * r: champEstProtege
	 * r.enUS: fieldIsProtected
	 * r: champEstPrive
	 * r.enUS: fieldIsPrivate
	 * r: champEstStatique
	 * r.enUS: fieldIsStatic
	 * r: champEstFinale
	 * r.enUS: fieldIsFinal
	 * r: champEstAbstrait
	 * r.enUS: fieldIsAbstract
	 * r: champEstNatif
	 * r.enUS: fieldIsNative
	 * r: annotationsLangue
	 * r.enUS: annotationsLanguage
	 * r: annotations
	 * r.enUS: annotations
	 * r: champEstTest
	 * r.enUS: fieldIsTest
	 * r: champEstSubstitue
	 * r.enUS: fieldIsOverride
	 * r: champAnnotationLangue
	 * r.enUS: fieldAnnotationLanguage
	 * r: champClassePartsLangue
	 * r.enUS: fieldClassPartsLanguage
	 * r: methodeString
	 * r.enUS: methodString
	 * r: methodeStringLangue
	 * r.enUS: methodStringLanguage
	 * r: entiteString
	 * r.enUS: entityString
	 * r: entiteStringLangue
	 * r.enUS: entityStringLanguage
	 * r: champStringLangue
	 * r.enUS: fieldStringLanguage
	 * r: champString
	 * r.enUS: fieldString
	 * r: champClasseParts
	 * r.enUS: fieldClassParts
	 * r: champVarLangue
	 * r.enUS: fieldVarLanguage
	 * r: champVar
	 * r.enUS: fieldVar
	 * r: champNomCanoniqueComplet
	 * r.enUS: fieldCanonicalNameComplete
	 * r: champNomSimpleComplet
	 * r.enUS: fieldSimpleNameComplete
	 * r: indexerListeSolr
	 * r.enUS: indexListSolr
	 * r: stockerListeSolr
	 * r.enUS: storeListSolr
	 * r: constructeurDoc
	 * r.enUS: constructorDoc
	 * r: constructeurQdox
	 * r.enUS: constructorQdox
	 * r: constructeurCommentaire
	 * r.enUS: constructorComment
	 * r: constructeurCle
	 * r.enUS: constructorKey
	 * r: constructeurCodeSourceLangue
	 * r.enUS: constructorSourceCodeLanguage
	 * r: constructeurCodeSource
	 * r.enUS: constructorSourceCode
	 * r: constructeurEstPublic
	 * r.enUS: constructorIsPublic
	 * r: constructeurEstProtege
	 * r.enUS: constructorIsProtected
	 * r: constructeurEstPrive
	 * r.enUS: constructorIsPrivate
	 * r: constructeurEstStatique
	 * r.enUS: constructorIsStatic
	 * r: constructeurEstFinale
	 * r.enUS: constructorIsFinal
	 * r: constructeurEstAbstrait
	 * r.enUS: constructorIsAbstract
	 * r: constructeurEstNatif
	 * r.enUS: constructorIsNative
	 * r: constructeurAnnotations
	 * r.enUS: constructorAnnotations
	 * r: constructeurAnnotationLangue
	 * r.enUS: constructorAnnotationLanguage
	 * r: constructeurParamsQdox
	 * r.enUS: constructorParamsQdox
	 * r: constructeurExceptionsQdox
	 * r.enUS: constructorExceptionsQdox
	 * r: constructeurParamNum
	 * r.enUS: constructorParamNum
	 * r: constructeurParamQdox
	 * r.enUS: constructorParamQdox
	 * r: constructeurParamsVarLangue
	 * r.enUS: constructorParamsVarLanguage
	 * r: constructeurParamVarLangue
	 * r.enUS: constructorParamVarLanguage
	 * r: constructeurParamsVarLangue
	 * r.enUS: constructorParamsVar
	 * r: constructeurParamsNomSimpleComplet
	 * r.enUS: constructorParamsSimpleNameComplete
	 * r: constructeurParamsVar
	 * r.enUS: constructorParamsVar
	 * r: constructeurParamsArgsVariables
	 * r.enUS: constructorParamsVariableArgs
	 * r: constructeurParamVarLangue
	 * r.enUS: constructorParamVar
	 * r: constructeurParamClassePartsLangue
	 * r.enUS: constructorParamClassPartsLanguage
	 * r: constructeurParamClasseParts
	 * r.enUS: constructorParamClassParts
	 * r: constructeurParamNomSimpleComplet
	 * r.enUS: constructorParamSimpleNameComplete
	 * r: constructeurParamVar
	 * r.enUS: constructeurParamVar
	 * r: constructeurParamArgsVariables
	 * r.enUS: constructorParamVariableArgs
	 * r: constructeurAnnotationBlocCode
	 * r.enUS: constructorAnnotationCodeBlock
	 * r: constructeurExceptionQdox
	 * r.enUS: constructorExceptionQdox
	 * r: constructeurExceptionNomSimpleComplet
	 * r.enUS: constructorExceptionSimpleNameComplete
	 * r: constructeurAnnotation
	 * r.enUS: constructorAnnotation
	 * r: partEstConstructeur
	 * r.enUS: partIsConstructor
	 * r: methodeVarLangue
	 * r.enUS: methodVarLanguage
	 * r: methodeVar
	 * r.enUS: methodVar
	 * r: methodeDoc
	 * r.enUS: methodDoc
	 * r: methodeQdox
	 * r.enUS: methodQdox
	 * r: methodeCommentaire
	 * r.enUS: methodComment
	 * r: methodeCle
	 * r.enUS: methodKey
	 * r: methodeCodeSourceLangue
	 * r.enUS: methodSourceCodeLanguage
	 * r: methodeCodeSource
	 * r.enUS: methodSourceCode
	 * r: methodeEstPublic
	 * r.enUS: methodIsPublic
	 * r: methodeEstProtege
	 * r.enUS: methodIsProtected
	 * r: methodeEstPrive
	 * r.enUS: methodIsPrivate
	 * r: methodeEstStatique
	 * r.enUS: methodIsStatic
	 * r: methodeEstFinale
	 * r.enUS: methodIsFinal
	 * r: methodeEstAbstrait
	 * r.enUS: methodIsAbstract
	 * r: methodeEstNatif
	 * r.enUS: methodIsNative
	 * r: methodeAnnotations
	 * r.enUS: methodAnnotations
	 * r: methodeAnnotationLangue
	 * r.enUS: methodAnnotationLanguage
	 * r: methodeParamsQdox
	 * r.enUS: methodParamsQdox
	 * r: methodeExceptionsQdox
	 * r.enUS: methodExceptionsQdox
	 * r: methodeParamNum
	 * r.enUS: methodParamNum
	 * r: methodeParamQdox
	 * r.enUS: methodParamQdox
	 * r: methodeParamVarLangue
	 * r.enUS: methodParamVarLanguage
	 * r: methodeParamsVar
	 * r.enUS: methodParamsVar
	 * r: methodeParamClassePartsLangue
	 * r.enUS: methodParamClassPartsLanguage
	 * r: methodeParamsNomSimpleComplet
	 * r.enUS: methodParamsSimpleNameComplete
	 * r: methodeParamNomSimpleComplet
	 * r.enUS: methodParamSimpleNameComplete
	 * r: methodeParamsArgsVariables
	 * r.enUS: methodParamsVariableArgs
	 * r: methodeParamVar
	 * r.enUS: methodeParamVar
	 * r: methodeParamArgsVariables
	 * r.enUS: methodParamVariableArgs
	 * r: methodeAnnotationBlocCode
	 * r.enUS: methodAnnotationCodeBlock
	 * r: methodeExceptionQdox
	 * r.enUS: methodExceptionQdox
	 * r: methodeExceptionNomSimpleComplet
	 * r.enUS: methodExceptionSimpleNameComplete
	 * r: methodeAnnotation
	 * r.enUS: methodAnnotation
	 * r: partEstMethode
	 * r.enUS: partIsMethod
	 * r: partEstEntite
	 * r.enUS: partIsEntity
	 * r: methodeRetourNomSimpleComplet
	 * r.enUS: methodReturnSimpleNameComplete
	 * r: methodeRetourClassePartsLangue
	 * r.enUS: methodReturnClassPartsLanguage
	 * r: methodeRetourClasseParts
	 * r.enUS: methodReturnClassParts
	 * r: methodeEstVide
	 * r.enUS: methodIsVoid
	 * r: methodeNomSimpleRetourComplet
	 * r.enUS: methodReturnSimpleNameComplete
	 * r: methodeEstTest
	 * r.enUS: methodIsTest
	 * r: methodeEstSubstitue
	 * r.enUS: methodIsOverride
	 * r: methodeExceptionsNomSimpleComplet
	 * r.enUS: methodExceptionsSimpleNameComplete
	 * r: methodeExceptionNomSimpleComplet
	 * r.enUS: methodExceptionSimpleNameComplete
	 * r: regexCle
	 * r.enUS: regexKey
	 * r: regexValeur
	 * r.enUS: regexValue
	 * r: qSupprimer
	 * r.enUS: qDelete
	 * r: nomCanoniqueGenerique
	 * r.enUS: canonicalNameGeneric
	 * r: nomCanoniqueComplet
	 * r.enUS: canonicalNameComplete
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: nomSimpleGenerique
	 * r.enUS: simpleNameGeneric
	 * r: nomSimpleComplet
	 * r.enUS: simpleNameComplete
	 * r: nomSimple
	 * r.enUS: simpleName
	 * r: regexListe
	 * r.enUS: regexList
	 * r: remplacerClesLangue
	 * r.enUS: replaceKeysLanguage
	 * r: remplacerValeursLangue
	 * r.enUS: replaceValuesLanguage
	 * r: annotationsLangue
	 * r.enUS: annotationsLanguage
	 * r: methodeParamsTypeNom
	 * r.enUS: methodParamsTypeName
	 * r: classeSuperParamsType
	 * r.enUS: classSuperTypeParameters
	 * r: classeSuperParamTypeNom
	 * r.enUS: classSuperTypeParameterName
	 * r: classeSuperParamType
	 * r.enUS: classSuperTypeParameter
	 * r: classeSuperParamsTypeNom
	 * r.enUS: classSuperTypeParameterNames
	 * r: classeParamsTypeNom
	 * r.enUS: classTypeParameterNames
	 * r: classeParamTypeNom
	 * r.enUS: classTypeParameterName
	 * r: classeParamsType
	 * r.enUS: classTypeParameters
	 * r: classeParamType
	 * r.enUS: classTypeParameter
	 * r: methodeParamsTypeNom
	 * r.enUS: methodParamsTypeName
	 * r: methodeParamsType
	 * r.enUS: methodParamsType
	 * r: methodeParamType
	 * r.enUS: methodParamType
	 * r: classePartsSuperLangue
	 * r.enUS: classSuperPartsLanguage
	 * r: regexTrouve
	 * r.enUS: regexFound
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: appliNom
	 * r.enUS: appName
	 * r: classeMethodesEcrites
	 * r.enUS: classMethodsWritten
	 * r: classeSuperGeneriqueQdox
	 * r.enUS: superClassGenericQdox
	 * r: classeBaseEtendGen
	 * r.enUS: baseClassExtendsGen
	 * r: etendGen
	 * r.enUS: extendsGen
	 * r: classeEstBase
	 * r.enUS: classIsBase
	 * r: classeEtendBase
	 * r.enUS: classExtendsBase
	 * r: classeContientRequeteSite
	 * r.enUS: classContainsSiteRequest
	 * r: classeContientCouverture
	 * r.enUS: classContainsWrap
	 * r: classePartsChaine
	 * r.enUS: classPartsChain
	 * r: classePartsRequeteSite
	 * r.enUS: classPartsSiteRequest
	 * r: classeModele
	 * r.enUS: classModel
	 * r: classeInitLoin
	 * r.enUS: classInitDeep
	 * r: classePartsGenAjouter
	 * r.enUS: classPartsGenAdd
	 * r: classePartsGenPageAjouter
	 * r.enUS: classPartsGenPageAdd
	 * r: classePartsGenApiAjouter
	 * r.enUS: classPartsGenApiAdd
	 * r: classePartsSiteContexte
	 * r.enUS: classPartsSiteContext
	 * r: classePartsConfigSite
	 * r.enUS: classPartsSiteConfig
	 * r: classePartsUtilisateurSite
	 * r.enUS: classPartsSiteUser
	 * r: classePartsCluster
	 * r.enUS: classPartsCluster
	 * r: classePartsResultatRecherche
	 * r.enUS: classPartsSearchResult
	 * r: classePartsSolrInputDocument
	 * r.enUS: classPartsSolrInputDocument
	 * r: classePartsSolrClient
	 * r.enUS: classPartsSolrClient
	 * r: classePartsToutEcrivain
	 * r.enUS: classPartsAllWriter
	 * r: classePartsListeRecherche
	 * r.enUS: classPartsSearchList
	 * r: classePartsMiseEnPage
	 * r.enUS: classPartsPageLayout
	 * 
	 * r: classeValsRecherche
	 * r.enUS: classValsSearch
	 * r: classeValsTrouves
	 * r.enUS: classValsFound
	 * r: classeValsVar
	 * r.enUS: classValsVar
	 * r: classeValsLangue
	 * r.enUS: classValsLanguage
	 * r: classeValsValeur
	 * r.enUS: classValsValue
	 * r: classeValVar
	 * r.enUS: classValVar
	 * r: classeValLangue
	 * r.enUS: classValLanguage
	 * r: classeValValeur
	 * r.enUS: classValValue
	 * 
	 * r: entiteValsRecherche
	 * r.enUS: entityValsSearch
	 * r: entiteValsTrouves
	 * r.enUS: entityValsFound
	 * r: entiteValsVar
	 * r.enUS: entityValsVar
	 * r: entiteValsLangue
	 * r.enUS: entityValsLanguage
	 * r: entiteValsValeur
	 * r.enUS: entityValsValue
	 * r: entiteValVar
	 * r.enUS: entityValVar
	 * r: entiteValLangue
	 * r.enUS: entityValLanguage
	 * r: entiteValValeur
	 * r.enUS: entityValValue
	 * 
	 * r: classeRolesRecherche
	 * r.enUS: classRolesSearch
	 * r: classeRolesTrouvesActuel
	 * r.enUS: classRolesFoundCurrent
	 * r: classeRolesTrouves
	 * r.enUS: classRolesFound
	 * r: classeRolesVar
	 * r.enUS: classRolesVar
	 * r: classeRolesLangue
	 * r.enUS: classRolesLanguage
	 * r: classeRolesRoleValeur
	 * r.enUS: classRolesValue
	 * r: classeRoleLangue
	 * r.enUS: classRoleLanguage
	 * r: classeRoleValeur
	 * r.enUS: classRoleValue
	 * r: classeRoles
	 * r.enUS: classRoles
	 * 
	 * r: classeMotsClesRecherche
	 * r.enUS: classKeywordsSearch
	 * r: classeMotsClesTrouvesActuel
	 * r.enUS: classKeywordsFoundActual
	 * r: classeMotsClesTrouves
	 * r.enUS: classKeywordsFound
	 * r: classeMotsCles
	 * r.enUS: classKeywords
	 * r: classeMotCleValeur
	 * r.enUS: classKeywordValue
	 * r: entiteMotsClesRecherche
	 * r.enUS: entityKeywordsSearch
	 * r: entiteMotsClesTrouvesActuel
	 * r.enUS: entityKeywordsFoundCurrent
	 * r: entiteMotsClesTrouves
	 * r.enUS: entityKeywordsFound
	 * r: entiteMotsClesVar
	 * r.enUS: entityKeywordsVar
	 * r: entiteMotsClesLangue
	 * r.enUS: entityKeywordsLanguage
	 * r: entiteMotsClesMotCleValeur
	 * r.enUS: entityKeywordsValue
	 * r: entiteMotCleLangue
	 * r.enUS: entityKeywordLanguage
	 * r: entiteMotCleValeur
	 * r.enUS: entityKeywordValue
	 * r: entiteMotsCles
	 * r.enUS: entityKeywords
	 * 
	 * r: classeMapRecherche
	 * r.enUS: classMapSearch
	 * r: classeMapTrouveActuel
	 * r.enUS: classMapFoundActual
	 * r: classeMapCle
	 * r.enUS: classMapKey
	 * r: classeMapValeur
	 * r.enUS: classMapValue
	 * r: classeMap
	 * r.enUS: classMap
	 * r: entiteMapRecherche
	 * r.enUS: entityMapSearch
	 * r: entiteMapTrouveActuel
	 * r.enUS: entityMapFoundCurrent
	 * r: entiteMapTrouve
	 * r.enUS: entityMapFound
	 * r: entiteMapVar
	 * r.enUS: entityMapVar
	 * r: entiteMapLangue
	 * r.enUS: entityMapLanguage
	 * r: entiteMapCle
	 * r.enUS: entityMapKey
	 * r: entiteMapMapValeur
	 * r.enUS: entityMapValue
	 * r: entiteMapLangue
	 * r.enUS: entityMapLanguage
	 * r: entiteMapValeur
	 * r.enUS: entityMapValue
	 * r: entiteMap
	 * r.enUS: entityMap
	 * 
	 * r: classePartsSuperGeneriqueLangue
	 * r.enUS: classPartsSuperGenericLanguage
	 * r: classePartsSuperGenerique
	 * r.enUS: classPartsSuperGeneric
	 * 
	 * r: apiMethode
	 * r.enUS: apiMethod
	 * r: classeApiMotCleMethode
	 * r.enUS: classApiKeywordMethod
	 * r: classeApiMotCle
	 * r.enUS: classApiKeyword
	 * r: ApiMotCle
	 * r.enUS: ApiKeyword
	 * r: classeApiTypeMedia
	 * r.enUS: classApiMediaType
	 * r: apiMotCle
	 * r.enUS: apiKeyword
	 * r: apiTypeMedia
	 * r.enUS: apiMediaType
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: apiUriRecherche
	 * r.enUS: apiUriSearch
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * r: classeApiUriLangue
	 * r.enUS: classApiUriLanguage
	 * 
	 * r: classeImportClassParts
	 * r.enUS: classImportClassParts
	 * r: classeImportation
	 * r.enUS: classImport
	 * r: constructeurParamVar
	 * r.enUS: constructorParamVar
	 * r: ignorer
	 * r.enUS: ignore
	 * r: methodeClasseQdoxRetour
	 * r.enUS: methodClassQdoxReturn
	 * r: methodeNomCanoniqueRetourComplet
	 * r.enUS: methodCanonicalNameReturnComplete
	 * r: methodeNomCanoniqueRetour
	 * r.enUS: methodCanonicalNameReturn
	 * r: est Entite. 
	 * r.enUS: is Entity. 
	 * r: entiteDoc
	 * r.enUS: entityDoc
	 * r: entiteVarCapitalise
	 * r.enUS: entityVarCapitalized
	 * r: entiteVarCouverture
	 * r.enUS: entityVarWrap
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: entiteCommentaire
	 * r.enUS: entityComment
	 * r: entiteClasseQdox
	 * r.enUS: entityClassQdox
	 * r: entiteClasseParts
	 * r.enUS: entityClassParts
	 * r: entiteCouverture
	 * r.enUS: entityWrap
	 * r: entiteNomsCanoniquesSuperEtMoiSansGen
	 * r.enUS: entityCanonicalNamesSuperAndMeWithoutGen
	 * r: classePartsGenerique
	 * r.enUS: classPartsGeneric
	 * r: documentSolr
	 * r.enUS: solrDocument
	 * r: entiteClassesSuperEtMoiSansGen
	 * r.enUS: entitySuperClassesAndMeWithoutGen
	 * r: entiteInitialise
	 * r.enUS: entityInitialized
	 * r: entiteNomCanoniqueCompletGenerique
	 * r.enUS: entityCanonicalNameCompleteGeneric
	 * r: entiteNomCanoniqueComplet
	 * r.enUS: entityCanonicalNameComplete
	 * r: entiteNomCanoniqueGenerique
	 * r.enUS: entityCanonicalNameGeneric
	 * r: entiteNomCanonique
	 * r.enUS: entityCanonicalName
	 * r: entiteNomSimpleCompletGenerique
	 * r.enUS: entitySimpleNameCompleteGeneric
	 * r: entiteNomSimpleComplet
	 * r.enUS: entitySimpleNameComplete
	 * r: entiteNomSimpleGenerique
	 * r.enUS: entitySimpleNameGeneric
	 * r: entiteNomSimple
	 * r.enUS: entitySimpleName
	 * r: entiteNomCompletGenerique
	 * r.enUS: entityCompleteNameGeneric
	 * r: entiteSetter
	 * r.enUS: entitySetter
	 * r: entiteDefinir
	 * r.enUS: entityDefined
	 * r: entiteClasseSuperQdox
	 * r.enUS: entitySuperClassQdox
	 * r: entiteInitLoin
	 * r.enUS: entityInitDeep
	 * r: entiteParamVar
	 * r.enUS: entityParamVar
	 * r: fqClassesSuperEtMoi
	 * r.enUS: fqSuperClassesAndMe
	 * r: rechercheSolrMethodeAvant
	 * r.enUS: solrSearchMethodBefore
	 * r: reponseRechercheMethodeAvant
	 * r.enUS: searchResponseMethodBefore
	 * r: listeRechercheMethodeAvant
	 * r.enUS: searchListMethodBefore
	 * r: rechercheSolrMethodeApres
	 * r.enUS: solrSearchMethodAfter
	 * r: reponseRechercheMethodeApres
	 * r.enUS: searchResponseMethodAfter
	 * r: listeRechercheMethodeApres
	 * r.enUS: searchListMethodAfter
	 * r: fqMethodeAvant
	 * r.enUS: fqMethodBefore
	 * r: fqMethodeApres
	 * r.enUS: fqMethodAfter
	 * r: methodVarActuel
	 * r.enUS: methodVarCurrent
	 * r: methodeClasseNomCanonique
	 * r.enUS: methodClassCanonicalName
	 * r: entiteMethodesAvantVisibilite
	 * r.enUS: entityMethodsBeforeVisibility
	 * r: entiteMethodesAvantVar
	 * r.enUS: entityMethodsBeforeVar
	 * r: entiteMethodesAvantParamVar
	 * r.enUS: entityMethodsBeforeParamVar
	 * r: entiteMethodesAvantParamNomSimple
	 * r.enUS: entityMethodsBeforeSimpleName
	 * r: entiteMethodesAvantNomParam
	 * r.enUS: entityMethodsBeforeParamName
	 * r: entiteMethodesAvantEcrire
	 * r.enUS: entityMethodsBeforeWrite
	 * r: entiteMethodesApresVisibilite
	 * r.enUS: entityMethodsAfterVisibility
	 * r: entiteMethodesApresVar
	 * r.enUS: entityMethodsAfterVar
	 * r: entiteMethodesApresParamVar
	 * r.enUS: entityMethodsAfterParamVar
	 * r: entiteMethodesApresParamNomSimple
	 * r.enUS: entityMethodsAfterSimpleName
	 * r: entiteMethodesApresNomParam
	 * r.enUS: entityMethodsAfterParamName
	 * r: entiteMethodesApresEcrire
	 * r.enUS: entityMethodsAfterWrite
	 * r: methodeParamsNomCanonique
	 * r.enUS: methodParamCanonicalNames
	 * r: methodeParamNomCanonique
	 * r.enUS: methodParamCanonicalName
	 * r: entiteMethodesAvant
	 * r.enUS: entityMethodsBefore
	 * r: entiteMethodesApres
	 * r.enUS: entityMethodsAfter
	 * r: entiteOptionsRecherche
	 * r.enUS: entityOptionsSearch
	 * r: entiteOptionsTrouves
	 * r.enUS: entityOptionsFound
	 * r: entiteOptionsVar
	 * r.enUS: entityOptionsVar
	 * r: entiteOptionsLangue
	 * r.enUS: entityOptionsLanguage
	 * r: entiteOptionsDescription
	 * r.enUS: entityOptionsDescription
	 * r: entiteOptionsValeurs
	 * r.enUS: entityOptionsValues
	 * r: entiteOptionVar
	 * r.enUS: entityOptionVar
	 * r: entiteOptionLangue
	 * r.enUS: entityOptionLanguage
	 * r: entiteOptionDescription
	 * r.enUS: entityOptionDescription
	 * r: entiteOptionValeurs
	 * r.enUS: entityOptionValues
	 * r: entiteOptions
	 * r.enUS: entityOptions
	 * r: entiteIndexeOuStocke
	 * r.enUS: entityIndexedOrStored
	 * r: entiteExact
	 * r.enUS: entityExact
	 * r: entiteClePrimaire
	 * r.enUS: entityPrimaryKey
	 * r: entiteCleUnique
	 * r.enUS: entityUniqueKey
	 * r: entiteCrypte
	 * r.enUS: entityEncrypted
	 * r: entiteSuggere
	 * r.enUS: entitySuggested
	 * r: entiteSauvegarde
	 * r.enUS: entitySaved
	 * r: entiteIndexe
	 * r.enUS: entityIndexed
	 * r: entiteIncremente
	 * r.enUS: entityIncremented
	 * r: entiteStocke
	 * r.enUS: entityStored
	 * r: entiteTexte
	 * r.enUS: entityText
	 * r: entiteNomAffichage
	 * r.enUS: entityDisplayName
	 * r: entiteDescription
	 * r.enUS: entityDescription
	 * r: entiteLongeurMin
	 * r.enUS: entityMinLength
	 * r: entiteLongeurMax
	 * r.enUS: entityMaxLength
	 * r: entiteVarApi
	 * r.enUS: entityVarApi
	 * r: EnumNomSimple
	 * r.enUS: EnumSimpleName
	 * r: entiteMin
	 * r.enUS: entityMin
	 * r: entiteMax
	 * r.enUS: entityMax
	 * r: entiteOptionnel
	 * r.enUS: entityOptional
	 * r: entiteIgnorer
	 * r.enUS: entityIgnored
	 * r: entiteDeclarer
	 * r.enUS: entityDeclared
	 * r: entiteRechercher
	 * r.enUS: entitySearch
	 * r: entiteAjouter
	 * r.enUS: entityAdd
	 * r: entiteSupprimer
	 * r.enUS: entityDelete
	 * r: entiteModifier
	 * r.enUS: entityModify
	 * r: entiteRecharger
	 * r.enUS: entityRefresh
	 * r: entiteMultiligne
	 * r.enUS: entityMultiline
	 * r: entiteCles
	 * r.enUS: entityKeys
	 * r: entiteAttribuerNomCanonique
	 * r.enUS: entityAttributeCanonicalName
	 * r: entiteAttribuerNomSimple
	 * r.enUS: entityAttributeSimpleName
	 * r: entiteAttribuerVar
	 * r.enUS: entityAttributeVar
	 * r: entiteAttribuer
	 * r.enUS: entityAttribute
	 * r: entiteHtmlTooltip
	 * r.enUS: entityHtmlTooltip
	 * r: HtmlLigne
	 * r.enUS: HtmlRow
	 * r: HtmlColonne
	 * r.enUS: HtmlColumn
	 * r: HtmlCellule
	 * r.enUS: HtmlCell
	 * r: entiteHtml
	 * r.enUS: entityHtml
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: entiteClassesSuperEtMoiSansGen
	 * r.enUS: entitySuperClassesAndMeWithoutGen
	 * r: entiteAnnotations
	 * r.enuS: entityAnnotations
	 * r: entiteCle
	 * r.enUS: entityKey
	 * r: entiteCodeSource
	 * r.enUS: entitySourceCode
	 * r: entiteListeNomSimpleVertxJson
	 * r.enUS: entityListSimpleNameVertxJson
	 * r: entiteListeNomCanoniqueVertxJson
	 * r.enUS: entityListCanonicalNameVertxJson
	 * r: entiteSolrNomCanonique
	 * r.enUS: entitySolrCanonicalName
	 * r: entiteSolrNomSimple
	 * r.enUS: entitySolrSimpleName
	 * r: entiteSuffixeType
	 * r.enUS: entityTypeSuffix
	 * r: entiteTypeJson
	 * r.enUS: entityJsonType
	 * r: entiteFormatJson
	 * r.enUS: entityJsonFormat
	 * r: entiteListeTypeJson
	 * r.enUS: entityListJsonType
	 * r: entiteAnnotationLangue
	 * r.enUS: entityAnnotationLanguage
	 * r: entiteAnnotations
	 * r.enUS: entityAnnotations
	 * r: classePartsBase
	 * r.enUS: classPartsBase
	 * r: classeApi
	 * r.enUS: classApi
	 * r: classePage
	 * r.enUS: classPage
	 * r: classePartsTest
	 * r.enUS: classPartsTest
	 * r: classeSauvegarde
	 * r.enUS: classSaved
	 * r: classeIndexe
	 * r.enUS: classIndexed
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: classeVarCleUnique
	 * r.enUS: classVarUniqueKey
	 *   
	 * r: ?Modele
	 * r.enUS: ?Model
	 * r: ?ClePrimaire
	 * r.enUS: ?PrimaryKey
	 * r: ?CleUnique
	 * r.enUS: ?UniqueKey
	 * r: ?Crypte
	 * r.enUS: ?Encrypted
	 * r: ?Suggere
	 * r.enUS: ?Suggested
	 * r: ?Sauvegarde
	 * r.enUS: ?Saved
	 * r: ?Indexe
	 * r.enUS: ?Indexed
	 * r: ?Incremente
	 * r.enUS: ?Incremented
	 * r: ?Stocke
	 * r.enUS: ?Stored
	 * r: ?Texte
	 * r.enUS: ?Text
	 * r: ?LongeurMin
	 * r.enUS: ?MinLength
	 * r: ?LongeurMax
	 * r.enUS: ?MaxLength
	 * r: ?Optionnel
	 * r.enUS: ?Optional
	 * r: ?NomAffichage
	 * r.enUS: ?DisplayName
	 * r: ?Titre
	 * r.enUS: ?Title
	 * r: ?Ignorer
	 * r.enUS: ?Ignored
	 * r: ?Declarer
	 * r.enUS: ?Declared
	 * r: ?Rechercher
	 * r.enUS: ?Search
	 * r: ?Ajouter
	 * r.enUS: ?Add
	 * r: ?Supprimer
	 * r.enUS: ?Delete
	 * r: ?Modifier
	 * r.enUS: ?Modify
	 * r: ?Recharger
	 * r.enUS: ?Refresh
	 * r: ?Multiligne
	 * r.enUS: ?Multiline
	 * r: ?Cles
	 * r.enUS: ?Keys
	 * r: ?MotCle
	 * r.enUS: ?Keyword
	 * r: classeSuperErreur
	 * r.enUS: superClassError
	 * r: classePartsCouverture
	 * r.enUS: classPartsWrap
	 * r: classePartsGen
	 * r.enUS: classPartsGen
	 * r: classePartGen
	 * r.enUS: classPartGen
	 * r: classeInitLoinExceptions
	 * r.enUS: classInitDeepExceptions
	 * r: classeInitLoinException
	 * r.enUS: classInitDeepException
	 * r: siteEcrireMethodes
	 * r.enUS: siteWriteMethods
	 * r: classeEcrireMethode
	 * r.enUS: classWriteMethod
	 * r: entiteEcrireMethode
	 * r.enUS: entityWriteMethod
	 * r: classeEntiteVars
	 * r.enUS: classEntityVars
	 * r: classeSuperEntiteVar
	 * r.enUS: classSuperEntityVar
	 * 
	 * r: classeApiOperationIdRechercheRequete
	 * r.enUS: classApiOperationIdSearchRequest
	 * r: classeApiOperationIdRequete
	 * r.enUS: classApiOperationIdRequest
	 * r: classeApiOperationIdRechercheReponse
	 * r.enUS: classApiOperationIdSearchResponse
	 * r: classeApiOperationIdReponse
	 * r.enUS: classApiOperationIdResponse
	 * 
	 * r: regexLangue
	 * r.enUS: regexLanguage
	 * 
	 * r: UnNomMinuscule
	 * r.enUS: ANameLowercase
	 * r: CeMinuscule
	 * r.enUS: ThisLowercase
	 * r: CeNomMinuscule
	 * r.enUS: ThisNameLowercase
	 * r: UnMinuscule
	 * r.enUS: ALowercase
	 * r: LeNomMinuscule
	 * r.enUS: TheNameLowercase
	 * r: SingulierNomMinuscule
	 * r.enUS: SingularNameLowercase
	 * r: ActuelNomMinuscule
	 * r.enUS: ActualNameLowercase
	 * r: ToutMinuscule
	 * r.enUS: AllLowercase
	 * r: ToutNomMinuscule
	 * r.enUS: AllNameLowercase
	 * r: AucunTrouveNomMinuscule
	 * r.enUS: NoneFoundNameLowercase
	 * r: DeNomMinuscule
	 * r.enUS: OfNameLowercase
	 * r: NomVarMinuscule
	 * r.enUS: NameVarLowercase
	 * r: AdjectifMinuscule
	 * r.enUS: AdjectiveLowercase
	 * r: AdjectifVarMinuscule
	 * r.enUS: AdjectiveVarLowercase
	 * r: UnNomAdjectifMinuscule
	 * r.enUS: ANameAdjectiveLowercase
	 * r: LeNomAdjectifMinuscule
	 * r.enUS: TheNameAdjectiveLowercase
	 * r: SingulierNomAdjectifMinuscule
	 * r.enUS: SingularNameAdjectiveLowercase
	 * r: PlurielNomAdjectifMinuscule
	 * r.enUS: PluralNameAdjectiveLowercase
	 * r: Couleur
	 * r.enUS: Color
	 * r: IconeGroupe
	 * r.enUS: IconGroup
	 * r: IconeNom
	 * r.enUS: IconName
	 * 
	 * r: UnMasculin
	 * r.enUS: AMale
	 * r: UneFeminin
	 * r.enUS: AFemale
	 * r: CetMasculinVoyelle
	 * r.enUS: ThisMaleVowel
	 * r: CetteFemininVoyelle
	 * r.enUS: ThisFemaleVowel
	 * r: CeMasculinConsonne
	 * r.enUS: ThisMaleConsonant
	 * r: CetteFemininConsonne
	 * r.enUS: ThisFemaleConsonant
	 * r: CesPluriel
	 * r.enUS: ThesePlural
	 * r: LMasculinVoyelle
	 * r.enUS: TheMaleVowel
	 * r: LFemininVoyelle
	 * r.enUS: TheFemaleVowel
	 * r: LeMasculinConsonne
	 * r.enUS: TheMaleConsonant
	 * r: LaFemininConsonne
	 * r.enUS: TheFemaleConsonant
	 * r: LesPluriel
	 * r.enUS: ThePlural
	 * r: ActuelMasculinAvant
	 * r.enUS: CurrentMaleBefore
	 * r: ActuelleFemininAvant
	 * r.enUS: CurrentFemaleBefore
	 * r: ActuelMasculinApres
	 * r.enUS: CurrentMaleAfter
	 * r: ActuelleFemininApres
	 * r.enUS: CurrentFemaleAfter
	 * r: TousMasculinPluriel
	 * r.enUS: AllMalePlural
	 * r: ToutesFemininPluriel
	 * r.enUS: AllFemalePlural
	 * r: AucunTrouveMasculinAvant
	 * r.enUS: NoneFoundMaleBefore
	 * r: AucuneTrouveFemininAvant
	 * r.enUS: NoneFemaleBefore
	 * r: AucunTrouveMasculinApres
	 * r.enUS: NoneFoundMaleAfter
	 * r: AucuneTrouveFemininApres
	 * r.enUS: NoneFemaleAfter
	 * r: DVoyelle
	 * r.enUS: OfVowel
	 * r: DeConsonne
	 * r.enUS: OfConsonant
	 * r: AdjectifPluriel
	 * r.enUS: AdjectivePlural
	 * 
	 * r: contexteNomAdjectifSingulier
	 * r.enUS: contextNameAdjectiveSingular
	 * r: contexteCeNom
	 * r.enUS: contextThisName
	 * r: contexteCe
	 * r.enUS: contextThis
	 * r: contexteUnNom
	 * r.enUS: contextAName
	 * r: contexteUn
	 * r.enUS: contextA
	 * r: contexteLeNom
	 * r.enUS: contextTheName
	 * r: contexteLesNoms
	 * r.enUS: contextTheNames
	 * r: contexteCree
	 * r.enUS: contextCreated
	 * r: contexteModifie
	 * r.enUS: contextModified
	 * r: contexteNomSingulier
	 * r.enUS: contextNameSingular
	 * r: contexteNomPluriel
	 * r.enUS: contextNamePlural
	 * r: contexteNomActuel
	 * r.enUS: contextActualName
	 * r: contexteTousNom
	 * r.enUS: contextAllName
	 * r: LesNom
	 * r.enUS: TheName
	 * r: contexteTous
	 * r.enUS: contextAll
	 * r: contexteAucunNomTrouve
	 * r.enUS: contextNoneNameFound
	 * r: contexteNomVar
	 * r.enUS: contextNameVar
	 * r: contexteDeNom
	 * r.enUS: contextOfName
	 * r: contexteNom
	 * r.enUS: contextName
	 * r: UnNomAdjectif
	 * r.enUS: ANameAdjective
	 * r: LeNomAdjectif
	 * r.enUS: TheNameAdjective
	 * r: AdjectifAvant
	 * r.enUS: AdjectiveBefore
	 * r: NomAdjectifSingulier
	 * r.enUS: NameAdjectiveSingular
	 * r: NomAdjectifPluriel
	 * r.enUS: NameAdjectivePlural
	 * r: PlurielNomAdjectif
	 * r.enUS: PluralNameAdjective
	 * r: SingulierNomAdjectif
	 * r.enUS: SingularNameAdjective
	 * r: LeNomAdjectif
	 * r.enUS: TheAdjectiveName
	 * r: UnNomAdjectif
	 * r.enUS: AnAdjectiveName
	 * r: AdjectifVar
	 * r.enUS: AdjectiveVar
	 * r: Adjectif
	 * r.enUS: Adjective
	 * r: CONTEXTE_UnMasculinVoyelle
	 * r.enUS: CONTEXT_AMaleVowel
	 * r: CONTEXTE_UnFemininVoyelle
	 * r.enUS: CONTEXT_AFemaleVowel
	 * r: CONTEXTE_UnMasculinConsonne
	 * r.enUS: CONTEXT_AMaleConsonant
	 * r: CONTEXTE_UnFemininConsonne
	 * r.enUS: CONTEXT_AFemaleConsonant
	 * r: CONTEXTE_CetMasculinVoyelle
	 * r.enUS: CONTEXT_ThisMaleVowel
	 * r: CONTEXTE_CetteFemininVoyelle
	 * r.enUS: CONTEXT_ThisFemaleVowel
	 * r: CONTEXTE_CeMasculinConsonne
	 * r.enUS: CONTEXT_ThisMaleConsonant
	 * r: CONTEXTE_CetteFemininConsonne
	 * r.enUS: CONTEXT_ThisFemaleConsonant
	 * r: CONTEXTE_CesPluriel
	 * r.enUS: CONTEXT_ThesePlural
	 * r: CONTEXTE_LMasculinVoyelle
	 * r.enUS: CONTEXT_TheMaleVowel
	 * r: CONTEXTE_LFemininVoyelle
	 * r.enUS: CONTEXT_TheFemaleVowel
	 * r: CONTEXTE_LeMasculinConsonne
	 * r.enUS: CONTEXT_TheMaleConsonant
	 * r: CONTEXTE_LaFemininConsonne
	 * r.enUS: CONTEXT_TheFemaleConsonant
	 * r: CONTEXTE_LesPluriel
	 * r.enUS: CONTEXT_ThePlural
	 * r: CONTEXTE_ActuelMasculinAvant
	 * r.enUS: CONTEXT_CurrentMaleBefore
	 * r: CONTEXTE_ActuelleFemininAvant
	 * r.enUS: CONTEXT_CurrentFemaleBefore
	 * r: CONTEXTE_ActuelMasculinApres
	 * r.enUS: CONTEXT_CurrentMaleAfter
	 * r: CONTEXTE_ActuelleFemininApres
	 * r.enUS: CONTEXT_CurrentFemaleAfter
	 * r: CONTEXTE_TousMasculinPluriel
	 * r.enUS: CONTEXT_AllMalePlural
	 * r: CONTEXTE_ToutesFemininPluriel
	 * r.enUS: CONTEXT_AllFemalePlural
	 * r: CONTEXTE_AucunTrouveMasculinAvant
	 * r.enUS: CONTEXT_NoneFoundMaleBefore
	 * r: CONTEXTE_AucuneTrouveFemininAvant
	 * r.enUS: CONTEXT_NoneFemaleBefore
	 * r: CONTEXTE_AucunTrouveMasculinApres
	 * r.enUS: CONTEXT_NoneFoundMaleAfter
	 * r: CONTEXTE_AucuneTrouveFemininApres
	 * r.enUS: CONTEXT_NoneFemaleAfter
	 * r: CONTEXTE_DVoyelle
	 * r.enUS: CONTEXT_OfVowel
	 * r: CONTEXTE_DeConsonne
	 * r.enUS: CONTEXT_OfConsonant
	 * r: CONTEXTE_AdjectifPluriel
	 * r.enUS: CONTEXT_AdjectivePlural
	 * r: CONTEXTE_CreeMasculin
	 * r.enUS: CONTEXT_CreatedMale
	 * r: CONTEXTE_CreeeFeminin
	 * r.enUS: CONTEXT_CreatedFemale
	 * r: CONTEXTE_ModifieMasculin
	 * r.enUS: CONTEXT_ModifiedMale
	 * r: CONTEXTE_ModifieeFeminin
	 * r.enUS: CONTEXT_ModifiedFemale
	 * 
	 * r: motCle
	 * r.enUS: keyword
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: recherche
	 * r.enUS: search
	 * r: Recherche
	 * r.enUS: Search
	 * r: Requete
	 * r.enUS: Request
	 * r: Reponse
	 * r.enUS: Response
	 * r: Couverture
	 * r.enUS: Wrap
	 * r: Avant
	 * r.enUS: Before
	 * r: avant
	 * r.enUS: before
	 * r: Apres
	 * r.enUS: After
	 * r: apres
	 * r.enUS: after
	 * r: classe
	 * r.enUS: class
	 * r: EnsembleInfo
	 * r.enUS: PackageInfo
	 * r: requete
	 * r.enUS: request
	 * r: reponse
	 * r.enUS: response
	 * r: entite
	 * r.enUS: entity
	 * r: contexte
	 * r.enUS: context
	 * r: CONTEXTE
	 * r.enUS: CONTEXT
	 * r: Titre
	 * r.enUS: Title
	 */        
	public SolrInputDocument indexerClasse(String classeCheminAbsolu, SolrInputDocument classeDoc) throws Exception { 

		String classeNomCanonique = StringUtils.replace(StringUtils.substringAfter(StringUtils.substringBeforeLast(classeCheminAbsolu, "."), cheminSrcMainJava + "/"), "/", ".");
		String classeNomSimple = StringUtils.substringAfterLast(classeNomCanonique, ".");
		String classeNomCanoniqueGen = classeNomCanonique + "Gen";
		String classeNomSimpleGen = classeNomSimple + "Gen";

		indexerStockerSolr(classeDoc, "appliChemin", appliChemin);
		indexerStockerSolr(classeDoc, "appliNom", appliNom);
		JavaClass classeQdox = bricoleur.getClassByName(classeNomCanonique.toString());
		JavaClass classeQdoxSuper = classeQdox.getSuperJavaClass();
		JavaClass classeQdoxString = bricoleur.getClassByName(String.class.getCanonicalName());
		Boolean classeMotsClesTrouves = false;
		List<String> classeMotsCles = new ArrayList<String>();
		List<String> classeInitLoinExceptions = new ArrayList<String>(); 
		String classeVarClePrimaire = null;
		String classeVarCleUnique = null;

		String classeNomCanoniqueSuper = Object.class.getCanonicalName();
		Boolean classeSuperErreur = false;
		try {
			classeNomCanoniqueSuper = classeQdoxSuper.getCanonicalName();
		} catch (Exception e) {
			classeSuperErreur = true;
		}

		String classeNomSimpleSuper = StringUtils.substringAfterLast(classeNomCanoniqueSuper, ".");
		if(StringUtils.isEmpty(classeNomSimpleSuper))
			classeNomSimpleSuper = classeNomCanoniqueSuper;

		List<String> classeMethodesEcrites = new ArrayList<String>();

		List<JavaTypeVariable<JavaGenericDeclaration>> classeParamsType = classeQdox.getTypeParameters();
		for(JavaTypeVariable<JavaGenericDeclaration> classeParamType : classeParamsType) {
			String classeParamTypeNom = classeParamType.getName();
			stockerListeSolr(classeDoc, "classeParamsTypeNom", classeParamTypeNom);
		}

		if(classeQdoxSuper instanceof DefaultJavaParameterizedType) {
			DefaultJavaParameterizedType typeSuper = (DefaultJavaParameterizedType)classeQdoxSuper;
			List<JavaType> classeSuperParamsType = typeSuper.getActualTypeArguments();
			for(JavaType classeSuperParamType : classeSuperParamsType) {
				String classeSuperParamTypeNom = classeSuperParamType.getValue();
				stockerListeSolr(classeDoc, "classeSuperParamsTypeNom", classeSuperParamTypeNom);
			}
		}
//		classeParamsType.get(0).getGenericFullyQualifiedName(); // returns <DEV>
//		classeQdox.getSuperClass().getGenericCanonicalName(); // returns CouvertureGen<DEV>
		
		String classeNomCompletSuper = Object.class.getCanonicalName();
		try {
			classeNomCompletSuper = indexerStockerSolr(classeDoc, "classeNomCompletSuper", langueNom, classeQdoxSuper.getGenericCanonicalName());
		} catch (Exception e) {
			if(classeQdoxSuper.getGenericFullyQualifiedName().contains("<"))
				classeNomCompletSuper = indexerStockerSolr(classeDoc, "classeNomCompletSuper", langueNom, classeQdoxSuper.getGenericFullyQualifiedName());
		}

		String classeNomCompletSuperGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(classeNomCompletSuper, "<"), ">");
		String classeNomCanoniqueSuperGenerique = null;
		String classeNomSimpleSuperGenerique = null;
		JavaClass classeSuperGeneriqueQdox = null;
		Boolean classeBaseEtendGen = false;
		if(StringUtils.isNotEmpty(classeNomCompletSuper)) {
			indexerStockerSolr(classeDoc, "classeNomCompletSuperGenerique", langueNom, classeNomCompletSuperGenerique);
			if(classeNomCompletSuper.contains("<")) {
				classeNomCanoniqueSuperGenerique = StringUtils.substringAfter(StringUtils.substringBeforeLast(classeNomCompletSuper, ">"), "<");
				classeNomCanoniqueSuperGenerique = classeNomCanoniqueSuperGenerique.contains(",") ? StringUtils.substringBefore(classeNomCanoniqueSuperGenerique, ",") : classeNomCanoniqueSuperGenerique;
				indexerStockerSolr(classeDoc, "classeNomCanoniqueSuperGenerique", langueNom, classeNomCanoniqueSuperGenerique);
				classeSuperGeneriqueQdox = bricoleur.getClassByName(classeNomCanoniqueSuperGenerique);
				classeNomCompletSuperGenerique = classeNomCanoniqueSuperGenerique;

				if(classeNomCanoniqueSuperGenerique.contains("."))
					classeNomSimpleSuperGenerique = StringUtils.substringAfterLast(classeNomCanoniqueSuperGenerique, ".");
				else
					classeNomSimpleSuperGenerique = classeNomCanoniqueSuperGenerique;
				indexerStockerSolr(classeDoc, "classeNomSimpleSuperGenerique", langueNom, classeNomSimpleSuperGenerique);

				ClasseParts classePartsBase = ClasseParts.initClasseParts(this, classeNomCanoniqueSuperGenerique, langueNom);
				classeBaseEtendGen = classePartsBase.etendGen;
				if(classeBaseEtendGen == null)
					classeBaseEtendGen = false;
			}
		}
		Boolean classeEstBase = stockerSolr(classeDoc, "classeEstBase", !classeBaseEtendGen || StringUtils.isEmpty(classeNomCompletSuperGenerique) || StringUtils.equals(classeNomCompletSuperGenerique, "java.lang.Object"));
		Boolean classeEtendBase = stockerSolr(classeDoc, "classeEtendBase", !classeEstBase && classeBaseEtendGen && !StringUtils.equals(classeNomCompletSuperGenerique, "java.lang.Object"));
		indexerStockerSolr(classeDoc, "classeBaseEtendGen", classeBaseEtendGen);
		Boolean classeContientRequeteSite = false;
		try {
			indexerStockerSolr(classeDoc, "classeContientRequeteSite", classeQdox.getMethodBySignature("getRequeteSite", new ArrayList<JavaType>(), true) != null);
		} catch (Exception e) {
			// TODO ctate fix this to pull from solr. 
		}
		
		String classeCommentaire = stockerRegexCommentaires(classeQdox.getComment(), langueNom, classeDoc, "classeCommentaire");
		String classeNomEnsemble = StringUtils.substringBeforeLast(classeNomCanonique, ".");
		String classeChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), ".java");
		String classeCheminRepertoire = StringUtils.substringBeforeLast(classeChemin, "/");
		String classeCheminGen = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "Gen.java");

		String classeCheminRepertoireGen = StringUtils.substringBeforeLast(classeCheminGen, "/");
		String classeCle = classeCheminAbsolu;
		Instant modifiee = Instant.now();
		Date modifieeDate = Date.from(modifiee);
		Boolean classeContientCouverture = false;

		ClasseParts classePartsChaine = classePartsChaine(nomEnsembleDomaine);
		Boolean classeEtendGen = StringUtils.endsWith(classeNomSimpleSuper, "Gen");
		ClasseParts classePartsRequeteSite = classePartsRequeteSite(nomEnsembleDomaine);
		if(classeSuperErreur || !classeEtendGen && regexTrouve("^(classe)?Gen:\\s*(true)$", classeCommentaire)) {
			classeEtendGen = true;
		}

		for(String langueNom : autresLangues) {
			String appliCheminLangue = appliChemins.get(langueNom);
			stockerRegexCommentaires(classeCommentaire, langueNom, classeDoc, "classeCommentaire");
			String cheminSrcMainJavaLangue = appliCheminLangue + "/src/main/java";
			String cheminSrcGenJavaLangue = appliCheminLangue + "/src/gen/java";
			String classeNomCanoniqueLangue = regex("^(classe)?NomCanonique\\." + langueNom + ":\\s*(.*)", classeCommentaire, classeNomCanonique);

			String classeNomSimpleLangue = StringUtils.substringAfterLast(classeNomCanoniqueLangue, ".");
			String classeNomEnsembleLangue = StringUtils.substringBeforeLast(classeNomCanoniqueLangue, ".");
			String classeNomCanoniqueGenLangue = classeNomCanoniqueLangue + "Gen";
			String classeNomSimpleGenLangue = classeNomSimpleLangue + "Gen";
			String classeCheminLangue = indexerStockerSolr(classeDoc, "classeChemin", langueNom, concat(cheminSrcMainJavaLangue, "/", StringUtils.replace(classeNomCanoniqueLangue, ".", "/"), ".java"));
			String classeCheminRepertoireLangue = stockerSolr(classeDoc, "classeCheminRepertoire", langueNom, StringUtils.substringBeforeLast(classeCheminLangue, "/"));
			String classeCheminGenLangue = indexerStockerSolr(classeDoc, "classeCheminGen", langueNom, concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniqueGenLangue, ".", "/"), ".java"));
			String classeCheminRepertoireGenLangue = stockerSolr(classeDoc, "classeCheminRepertoireGen", langueNom, StringUtils.substringBeforeLast(classeCheminGenLangue, "/"));

			indexerStockerSolr(classeDoc, "classeNomCanonique", langueNom, classeNomCanoniqueLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimple", langueNom, classeNomSimpleLangue); 
			indexerStockerSolr(classeDoc, "classeNomCanoniqueGen", langueNom, classeNomCanoniqueGenLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimpleGen", langueNom, classeNomSimpleGenLangue); 
			indexerStockerSolr(classeDoc, "classeNomEnsemble", langueNom, classeNomEnsembleLangue); 

			String classeNomCompletSuperLangue;
			ClasseParts classePartsSuperLangue;

			if(classeEtendGen) {
				classePartsSuperLangue = ClasseParts.initClasseParts(this, classeNomCanoniqueLangue + "Gen", langueNom);
			}
			else {
				classePartsSuperLangue = ClasseParts.initClasseParts(this, classeQdoxSuper, langueNom);
			}

			indexerStockerSolr(classeDoc, "classeNomCanoniqueSuper", langueNom, classePartsSuperLangue.nomCanonique); 
			indexerStockerSolr(classeDoc, "classeNomSimpleSuper", langueNom, classePartsSuperLangue.nomSimple); 
			indexerStockerSolr(classeDoc, "classeNomCanoniqueCompletSuper", langueNom, classePartsSuperLangue.nomCanoniqueComplet);
			indexerStockerSolr(classeDoc, "classeNomSimpleCompletSuper", langueNom, classePartsSuperLangue.nomSimpleComplet);
			if(StringUtils.isNotEmpty(classeNomCompletSuperGenerique)) {
				ClasseParts classePartsSuperGeneriqueLangue = ClasseParts.initClasseParts(this, classeNomCompletSuperGenerique, langueNom);
				indexerStockerSolr(classeDoc, "classeNomCanoniqueSuperGenerique", langueNom, classePartsSuperGeneriqueLangue.nomCanoniqueComplet);
				indexerStockerSolr(classeDoc, "classeNomSimpleSuperGenerique", langueNom, classePartsSuperGeneriqueLangue.nomSimpleComplet);
				if(classeEtendGen) {
					classePartsGenAjouter(classePartsSuperGeneriqueLangue);
				}
			}
		}

		Boolean classeInitLoin = !regexTrouve("^(classe)?InitLoin:\\s*(false)$", classeCommentaire);
		if(classeInitLoin)
			classeInitLoin = classeEtendBase || classeEstBase;
		classeInitLoin = stockerSolr(classeDoc, "classeInitLoin", classeInitLoin);
		if(classeInitLoin)
			classePartsGenAjouter(classePartsRequeteSite);
		indexerStockerSolr(classeDoc, "classeEtendGen", classeEtendGen);

		indexerStockerSolr(classeDoc, "langueNom", langueNom); 
		indexerStockerSolr(classeDoc, "modifiee", modifieeDate); 
		indexerStockerSolr(classeDoc, "classeNomCanonique", langueNom, classeNomCanonique); 
		indexerStockerSolr(classeDoc, "classeNomSimple", langueNom, classeNomSimple); 
		indexerStockerSolr(classeDoc, "classeNomEnsemble", langueNom, classeNomEnsemble); 
		indexerStockerSolr(classeDoc, "classeNomCanoniqueGen", langueNom, classeNomCanoniqueGen); 
		indexerStockerSolr(classeDoc, "classeNomSimpleGen", langueNom, classeNomSimpleGen); 
		indexerStockerSolr(classeDoc, "classeNomCanoniqueSuper", langueNom, classeNomCanoniqueSuper); 
		indexerStockerSolr(classeDoc, "classeNomSimpleSuper", langueNom, classeNomSimpleSuper); 
		indexerStockerSolr(classeDoc, "classeCheminAbsolu", classeCheminAbsolu);
		indexerStockerSolr(classeDoc, "classeChemin", langueNom, classeChemin); 
		indexerStockerSolr(classeDoc, "classeCheminRepertoire", langueNom, classeCheminRepertoire);  
		indexerStockerSolr(classeDoc, "classeCheminGen", langueNom, classeCheminGen); 
		indexerStockerSolr(classeDoc, "classeCheminRepertoireGen", langueNom, classeCheminRepertoireGen); 
		indexerStockerSolr(classeDoc, "nomEnsembleDomaine", nomEnsembleDomaine); 

		ArrayList<JavaClass> classesSuperQdox = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtMoi = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtMoiSansGen = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxEtInterfaces = new ArrayList<JavaClass>();
		ArrayList<JavaClass> classesSuperQdoxInterfacesEtMoi = new ArrayList<JavaClass>();
		peuplerClassesSuperQdoxInterfacesEtMoi(classeQdox, classesSuperQdox, classesSuperQdoxEtMoi, classesSuperQdoxEtMoiSansGen, classesSuperQdoxEtInterfaces, classesSuperQdoxInterfacesEtMoi);

		for(JavaClass c : classesSuperQdoxEtMoiSansGen) {
			indexerStockerListeSolr(classeDoc, "entiteClassesSuperEtMoiSansGen", c.getCanonicalName()); 

		}

		SolrInputDocument classeDocClone = classeDoc.deepCopy();
		Integer partNumero = 1;

		Boolean classeModele = indexerStockerSolr(classeDoc, "classeModele", regexTrouve("^(classe)?Modele: \\s*(true)$", classeCommentaire));
		Boolean classeApi = indexerStockerSolr(classeDoc, "classeApi", regexTrouve("^(classe)?Api: \\s*(true)$", classeCommentaire) || classeModele);
		Boolean classePage = regexTrouve("^(classe)?Page: \\s*(true)$", classeCommentaire);
		Boolean classeSauvegarde = indexerStockerSolr(classeDoc, "classeSauvegarde", regexTrouve("^(classe)?Sauvegarde:\\s*(true)$", classeCommentaire) || classeModele);
		ArrayList<String> classeApiMethodes = regexListe("^(classe)?ApiMethode:\\s*(.*)", classeCommentaire);

		for(String siteEcrireMethode : siteEcrireMethodes) {
			if(classeQdox.getMethodBySignature(siteEcrireMethode, new ArrayList<JavaType>()) != null
					|| classeQdox.getMethodBySignature(siteEcrireMethode + classeNomSimple, new ArrayList<JavaType>()) != null)
				indexerStockerListeSolr(classeDoc, "classeEcrireMethodes",  siteEcrireMethode);
		}

		String classeNomSimpleApiEnsembleInfo;
		String classeNomSimpleGenApiServiceImpl;
		String classeNomSimpleApiServiceImpl;
		String classeNomSimpleGenApiService;

		String classeNomCanoniqueApiEnsembleInfo;
		String classeNomCanoniqueGenApiServiceImpl;
		String classeNomCanoniqueApiServiceImpl;
		String classeNomCanoniqueGenApiService;

		String classeNomCanoniquePageGen = classeNomCanonique + "PageGen";
		String classeNomSimplePage = indexerStockerSolr(classeDoc, "classeNomSimplePage", langueNom, classeNomSimple + "Page");
		String classeNomSimpleGenPage = indexerStockerSolr(classeDoc, "classeNomSimpleGenPage", langueNom, classeNomSimple + "PageGen");

		String classeCheminApiEnsembleInfo;
		String classeCheminGenApiServiceImpl;
		String classeCheminApiServiceImpl;
		String classeCheminGenApiService;

		if(classeApi) {
			classeNomSimpleApiEnsembleInfo = indexerStockerSolr(classeDoc, "classeNomSimpleApiEnsembleInfo", langueNom, "package-info");
			classeNomSimpleGenApiServiceImpl = indexerStockerSolr(classeDoc, "classeNomSimpleGenApiServiceImpl", langueNom, classeNomSimple + "GenApiServiceImpl");
			classeNomSimpleApiServiceImpl = indexerStockerSolr(classeDoc, "classeNomSimpleApiServiceImpl", langueNom, classeNomSimple + "ApiServiceImpl");
			classeNomSimpleGenApiService = indexerStockerSolr(classeDoc, "classeNomSimpleGenApiService", langueNom, classeNomSimple + "GenApiService");

			classeNomCanoniqueApiEnsembleInfo = indexerStockerSolr(classeDoc, "classeNomCanoniqueApiEnsembleInfo", langueNom, classeNomEnsemble + "." + classeNomSimpleApiEnsembleInfo);
			classeNomCanoniqueGenApiServiceImpl = indexerStockerSolr(classeDoc, "classeNomCanoniqueGenApiServiceImpl", langueNom, classeNomEnsemble + "." + classeNomSimpleGenApiServiceImpl);
			classeNomCanoniqueApiServiceImpl = indexerStockerSolr(classeDoc, "classeNomCanoniqueApiServiceImpl", langueNom, classeNomEnsemble + "." + classeNomSimpleApiServiceImpl);
			classeNomCanoniqueGenApiService = indexerStockerSolr(classeDoc, "classeNomCanoniqueGenApiService", langueNom, classeNomEnsemble + "." + classeNomSimpleGenApiService);

			classeCheminApiEnsembleInfo = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanoniqueApiEnsembleInfo, ".", "/"), ".java");
			classeCheminGenApiServiceImpl = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceImpl, ".", "/"), ".java");
			classeCheminApiServiceImpl = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueApiServiceImpl, ".", "/"), ".java");
			classeCheminGenApiService = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanoniqueGenApiService, ".", "/"), ".java");

			indexerStockerSolr(classeDoc, "classeCheminApiEnsembleInfo", langueNom, classeCheminApiEnsembleInfo); 
			indexerStockerSolr(classeDoc, "classeCheminGenApiServiceImpl", langueNom, classeCheminGenApiServiceImpl); 
			indexerStockerSolr(classeDoc, "classeCheminApiServiceImpl", langueNom, classeCheminApiServiceImpl); 
			indexerStockerSolr(classeDoc, "classeCheminGenApiService", langueNom, classeCheminGenApiService); 
		}

		for(String langueNom : autresLangues) {
			String appliCheminLangue = appliChemins.get(langueNom);
			String cheminSrcMainJavaLangue = appliCheminLangue + "/src/main/java";
			String cheminSrcGenJavaLangue = appliCheminLangue + "/src/gen/java";
			String classeNomCanoniqueLangue = regex("^(classe)?NomCanonique\\." + langueNom + ":\\s*(.*)", classeCommentaire, classeNomCanonique);
			String classeNomSimpleLangue = StringUtils.substringAfterLast(classeNomCanoniqueLangue, ".");
//			String classeNomEnsembleLangue = StringUtils.substringBeforeLast(classeNomCanoniqueLangue, ".");
//			String classeNomCanoniqueGenLangue = classeNomCanoniqueLangue + "Gen";
//			String classeNomSimpleGenLangue = classeNomSimpleLangue + "Gen";

			String classeNomSimpleApiLangue = classeNomSimpleLangue + "Api";
			String classeNomSimplePageLangue = classeNomSimpleLangue + "Page";
			String classeNomSimpleGenApiServiceImplLangue = classeNomSimpleLangue + "ApiGen";
			String classeNomSimpleGenPageLangue = classeNomSimpleLangue + "PageGen";
			String classeNomCanoniqueApiLangue = classeNomCanoniqueLangue + "Api";
			String classeNomCanoniqueApiGenLangue = classeNomCanoniqueLangue + "ApiGen";
			String classeNomCanoniquePageLangue = classeNomCanoniqueLangue + "Page";
			String classeNomCanoniquePageGenLangue = classeNomCanoniqueLangue + "PageGen";
			String classeCheminApiGenLangue = indexerStockerSolr(classeDoc, "classeCheminApiGen", langueNom, concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniqueApiGenLangue, ".", "/"), ".java"));
			String classeCheminGenPageLangue = indexerStockerSolr(classeDoc, "classeCheminGenPage", langueNom, concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniquePageGenLangue, ".", "/"), ".java"));
			indexerStockerSolr(classeDoc, "classeNomSimpleApi", langueNom, classeNomSimpleApiLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimplePage", langueNom, classeNomSimplePageLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimpleGenApiServiceImpl", langueNom, classeNomSimpleGenApiServiceImplLangue); 
			indexerStockerSolr(classeDoc, "classeNomSimpleGenPage", langueNom, classeNomSimpleGenPageLangue); 
		}

		classePartsSolrInputDocument = ClasseParts.initClasseParts(this, "org.apache.solr.common.SolrInputDocument", langueNom);
		classePartsSolrDocument = ClasseParts.initClasseParts(this, "org.apache.solr.common.SolrDocument", langueNom);
		classePartsSolrClient = ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.SolrClient", langueNom);
		classePartsTest = ClasseParts.initClasseParts(this, "org.junit.Test", langueNom);
		classePartsList = ClasseParts.initClasseParts(this, List.class.getCanonicalName(), langueNom);
		classePartsArrayList = ClasseParts.initClasseParts(this, ArrayList.class.getCanonicalName(), langueNom);
		classePartsSiteContexte = classePartsSiteContexte(nomEnsembleDomaine);
		classePartsConfigSite = classePartsConfigSite(nomEnsembleDomaine);
		classePartsUtilisateurSite = classePartsUtilisateurSite(nomEnsembleDomaine);
		classePartsCluster = classePartsCluster(nomEnsembleDomaine);
		classePartsResultatRecherche = classePartsResultatRecherche(nomEnsembleDomaine);
		classePartsToutEcrivain = classePartsToutEcrivain(nomEnsembleDomaine);
		classePartsListeRecherche = classePartsListeRecherche(nomEnsembleDomaine);
		classePartsCouverture = classePartsCouverture(nomEnsembleDomaine);
		classePartsMiseEnPage = classePartsMiseEnPage(nomEnsembleDomaine);

		if(classeApi) {
			classePartsGenApiAjouter(classePartsConfigSite);
			classePartsGenApiAjouter(classePartsRequeteSite);
			classePartsGenApiAjouter(classePartsSiteContexte);
			classePartsGenApiAjouter(classePartsUtilisateurSite);
			classePartsGenApiAjouter(classePartsResultatRecherche);
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.io.IOException", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.servlet.http.HttpServlet", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.servlet.http.HttpServerRequest", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.servlet.http.HttpServerResponse", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Collections", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Map", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.servlet.ServletException", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.concurrent.TimeUnit", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.stream.Collectors", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.Json", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.json.JsonArray", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.json.JsonObject", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.json.JsonReader", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.SolrQuery", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.SolrQuery.ORDER", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.response.QueryResponse", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.util.ClientUtils", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.StringUtils", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.keycloak.KeycloakPrincipal", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.keycloak.KeycloakSecurityContext", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.keycloak.representations.AccessToken", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.keycloak.representations.AccessToken.Access", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.security.Principal", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.mail.Message", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.mail.Session", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.mail.Transport", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.mail.internet.InternetAddress", langueNom));
//			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "javax.mail.internet.MimeMessage", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.io.PrintWriter", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.common.SolrDocumentList", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.common.SolrDocument", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Collection", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.math.BigDecimal", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Date", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.List", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.ArrayList", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Arrays", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Set", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Handler", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.RoutingContext", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.Router", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Vertx", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.reactivestreams.ReactiveReadStream", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.reactivestreams.ReactiveWriteStream", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.MultiMap", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.auth.oauth2.OAuth2Auth", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.netty.handler.codec.http.HttpResponseStatus", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.logging.Logger", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.logging.LoggerFactory", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.validation.HTTPRequestValidationHandler", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.validation.ParameterTypeValidator", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.validation.ValidationException", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.sql.SQLClient", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.sql.SQLConnection", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonArray", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonObject", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.LocalDateTime", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.sql.Timestamp", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Future", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.CaseInsensitiveHeaders", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.AsyncResult", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Handler", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.buffer.Buffer", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.OperationResponse", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.CompositeFuture", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.http.client.utils.URLEncodedUtils", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.nio.charset.Charset", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.http.NameValuePair", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.OperationRequest", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.sql.SQLConnection", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Optional", langueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.stream.Stream", langueNom));
			classePartsGenApiAjouter(classePartsListeRecherche);
		}
		if(classeEtendBase || classeEstBase) {
			classePartsGenAjouter(classePartsCluster);
			classePartsGenAjouter(classePartsToutEcrivain);
		}
		if(classeSauvegarde) {
			classePartsGenAjouter(classePartsSiteContexte);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.logging.Logger", langueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.logging.LoggerFactory", langueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.sql.SQLClient", langueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.sql.SQLConnection", langueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonArray", langueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonObject", langueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, VAL_nomCanoniqueDate, langueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.util.Set", langueNom));
		}
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.text.StringEscapeUtils", langueNom));
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.StringUtils", langueNom));
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.util.Objects", langueNom));

		if(classeCommentaire != null) {

			Matcher classeValsRecherche = Pattern.compile("^(classe)?Val\\.(\\w+)\\.(\\w+):(.*)", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeValsTrouves = classeValsRecherche.find();
			while(classeValsTrouves) {
				String classeValVar = classeValsRecherche.group(2);
				String classeValLangue = classeValsRecherche.group(3);
				String classeValValeur = classeValsRecherche.group(4);
				stockerListeSolr(classeDoc, "classeValsVar", classeValVar);
				stockerListeSolr(classeDoc, "classeValsLangue", classeValLangue);
				stockerListeSolr(classeDoc, "classeValsValeur", classeValValeur);
				classeValsTrouves = classeValsRecherche.find();
			}

			Matcher classeRolesRecherche = Pattern.compile("^(classe)?Role\\.(\\w+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeRolesTrouves = classeRolesRecherche.find();
			boolean classeRolesTrouvesActuel = classeRolesTrouves;
			while(classeRolesTrouvesActuel) {
				String classeRoleLangue = classeRolesRecherche.group(2);
				String classeRoleValeur = classeRolesRecherche.group(3);
				stockerListeSolr(classeDoc, "classeRoles", classeRoleLangue, classeRoleValeur);
				classeRolesTrouves = true;
				classeRolesTrouvesActuel = classeRolesRecherche.find();
			}
			indexerStockerSolr(classeDoc, "classeRolesTrouves", classeRolesTrouves); 

			Matcher classeMotsClesRecherche = Pattern.compile("^(classe)?MotCle:\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeMotsClesTrouvesActuel = classeMotsClesRecherche.find();
			while(classeMotsClesTrouvesActuel) {
				String classeMotCleValeur = classeMotsClesRecherche.group(2);
				classeMotsClesTrouvesActuel = classeMotsClesRecherche.find();
				if(!classeMotsCles.contains(classeMotCleValeur))
					classeMotsCles.add(classeMotCleValeur);
				classeMotsClesTrouves = true;
			}

			String sqlString = regex("^(classe)?Sql:\\s*(.*)$", classeCommentaire, 2);
			if(NumberUtils.isCreatable(sqlString)) {
				Integer sqlInteger = Integer.parseInt(sqlString);
				Integer sqlMigration = Math.abs(sqlInteger);
				Boolean sqlCreate = sqlInteger > 0;
				Boolean sqlDrop = sqlInteger < 0;
				indexerStockerSolr(classeDoc, "sqlMigration", sqlMigration);
				if(sqlCreate)
					indexerStockerSolr(classeDoc, "sqlCreate", sqlCreate);
				if(sqlDrop)
					indexerStockerSolr(classeDoc, "sqlDrop", sqlDrop);
			}

			Matcher classeMapRecherche = Pattern.compile("^(classe)?Map\\.([^:]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeMapTrouveActuel = classeMapRecherche.find();
			while(classeMapTrouveActuel) {
				String classeMapCle = classeMapRecherche.group(2);
				String classeMapValeur = classeMapRecherche.group(3);
				String[] classeMapCleParts = StringUtils.split(classeMapCle, ".");
				if(classeMapCleParts.length == 2) {
					String classeMapCleType = classeMapCleParts[0];
					if("Integer".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Integer.parseInt(classeMapValeur));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("Double".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Double.parseDouble(classeMapValeur));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("Long".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Long.parseLong(classeMapValeur));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("ZonedDateTime".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Date.from(ZonedDateTime.parse(classeMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("LocalDateTime".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Date.from(LocalDateTime.parse(classeMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else if("LocalDate".equals(classeMapCleType) && NumberUtils.isCreatable(classeMapValeur)) {
						try {
							indexerStockerSolr(classeDoc, classeMapCleParts[1], Date.from(LocalDate.parse(classeMapValeur, DateTimeFormatter.ISO_OFFSET_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()));
						} catch (Exception e) {
							System.err.println(ExceptionUtils.getStackTrace(e));
						}
					}
					else {
						indexerStockerSolr(classeDoc, classeMapCle, classeMapValeur);
					}
				}
				else {
					indexerStockerSolr(classeDoc, classeMapCle, classeMapValeur);
				}
				classeMapTrouveActuel = classeMapRecherche.find();
			}
		}

		SolrDocument classeSuperDoc = null;   
		if(classeEtendGen) {
			ClasseParts classePartsSuperGenerique = ClasseParts.initClasseParts(this, classeNomCompletSuperGenerique, langueNom);
			classePartsGenAjouter(classePartsSuperGenerique);

			if(StringUtils.startsWith(classeNomCanoniqueSuper, nomEnsembleDomaine)) {
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1);
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + ClientUtils.escapeQueryChars(classeNomCanoniqueSuperGenerique));
				rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
				rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
				QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList listeRecherche = reponseRecherche.getResults();
				if(listeRecherche.size() > 0) { 
					classeSuperDoc = listeRecherche.get(0);
				}
			} 
			else if(!StringUtils.contains(classeNomCanoniqueSuper, ".") && StringUtils.isNotBlank(classeNomCanoniqueSuper)) {
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1);
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + ClientUtils.escapeQueryChars(classeNomCanoniqueSuperGenerique));
				rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
				rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
				QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList listeRecherche = reponseRecherche.getResults();
				if(listeRecherche.size() > 0) { 
					classeSuperDoc = listeRecherche.get(0);
				}
			}
		}
		if(classeSuperDoc != null) {
			List<String> classeSuperEcrireMethodes = (List<String>)classeSuperDoc.get("classeEcrireMethodes_stored_strings");
			if(classeSuperEcrireMethodes != null) {
				for(String classeSuperEcrireMethode : classeSuperEcrireMethodes) {
					indexerStockerListeSolr(classeDoc, "classeSuperEcrireMethodes",  classeSuperEcrireMethode);
				}
			}
		}

		if(classeDoc.getField("id") == null)
			classeDoc.addField("id", classeCle);  

		indexerStockerSolr(classeDoc, "partEstClasse", true);
		indexerStockerSolr(classeDoc, "partNumero", partNumero);
		
		for(String classeImportation : classeQdox.getSource().getImports()) {
			ClasseParts classeImportationClasseParts = ClasseParts.initClasseParts(this, classeImportation, langueNom);
			indexerStockerListeSolr(classeDoc, "classeImportations", langueNom, classeImportationClasseParts.nomCanonique);
			for(String langueNom : autresLangues) {  
				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classeImportationClasseParts, langueNom);
				indexerStockerListeSolr(classeDoc, "classeImportations", langueNom, classeImportationClassePartsLangue.nomCanonique);
			}
		}

		List<JavaMember> membresQdox = classeQdox.getMembers();
//		List<JavaMember> membresQdox = new ArrayList<JavaMember>();
//		membresQdox.addAll(classeQdox.getFields());
//		membresQdox.addAll(classeQdox.getConstructors());
//		membresQdox.addAll(classeQdox.getMethods());
		for(JavaMember membreQdox : membresQdox) {  
			partNumero++;

			if(membreQdox instanceof JavaField) {    
				SolrInputDocument champDoc = classeDocClone.deepCopy();
				JavaField champQdox = (JavaField)membreQdox;
				String champCommentaire = champQdox.getComment();
				String champVar = champQdox.getName();
				String champCle = classeCheminAbsolu + "." + champVar;
				String champCodeSource = StringUtils.substringBeforeLast(StringUtils.trim(regex("\\s+" + champVar + "\\s*=([\\s\\S]*)", champQdox.getCodeBlock(), 1)), ";");
				String champString = regex("^(champ)?String\\." + langueNom + ":(.*)", champCommentaire);
				if(champString != null) {
					champCodeSource = "\"" + StringUtils.replace(StringUtils.replace(champString, "\\", "\\\\"), "\"", "\\\"") + "\"";
					indexerStockerSolr(champDoc, "champString", langueNom, champString); 
				}

				// Champs Solr du champ. 

				indexerStockerSolr(champDoc, "champVar", langueNom, champVar); 
				indexerStockerSolr(champDoc, "partEstChamp", true);
				indexerStockerSolr(champDoc, "partNumero", partNumero);
				indexerStockerSolr(champDoc, "champEstPublic", champQdox.isPublic()); 
				indexerStockerSolr(champDoc, "champEstProtege", champQdox.isProtected()); 
				indexerStockerSolr(champDoc, "champEstPrive", champQdox.isPrivate()); 
				indexerStockerSolr(champDoc, "champEstStatique", champQdox.isStatic()); 
				indexerStockerSolr(champDoc, "champEstFinale", champQdox.isFinal()); 
				indexerStockerSolr(champDoc, "champEstAbstrait", champQdox.isAbstract()); 
				indexerStockerSolr(champDoc, "champEstNatif", champQdox.isNative()); 
	
				/////////////////
				// Annotations //
				/////////////////
				List<JavaAnnotation> annotations = champQdox.getAnnotations(); 
				ArrayList<String> annotationsLangue = new ArrayList<String>();
				Boolean champEstTest = false;
				Boolean champEstSubstitue = false;
				for(JavaAnnotation annotation : annotations) {
					String champAnnotationLangue = annotation.getType().getCanonicalName();
					indexerStockerSolr(champDoc, "champAnnotation", langueNom, champAnnotationLangue); 

					if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
						champEstTest = true;
					}
					if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
						champEstSubstitue = true;
					}
				}
				indexerStockerSolr(champDoc, "champEstTest", champEstTest); 
				indexerStockerSolr(champDoc, "champEstSubstitue", champEstSubstitue); 

				ClasseParts champClasseParts = ClasseParts.initClasseParts(this, champQdox.getType(), langueNom);
	
				stockerRegexCommentaires(champCommentaire, langueNom, champDoc, "champCommentaire");
				stockerSolr(champDoc, "champNomSimpleComplet", langueNom, champClasseParts.nomSimpleComplet);
				String champNomCanoniqueComplet = stockerSolr(champDoc, "champNomCanoniqueComplet", langueNom, champClasseParts.nomCanoniqueComplet);
				stockerSolr(champDoc, "champCodeSource", langueNom, champCodeSource);
				champDoc.addField("id", champNomCanoniqueComplet + " " + champCle);

				for(String langueNom : autresLangues) { 
					ClasseParts champClassePartsLangue = ClasseParts.initClasseParts(this, champClasseParts, langueNom);
					String champVarLangue = regex("^(champ)?Var\\." + langueNom + ": (.*)", champCommentaire);
					champVarLangue = champVarLangue == null ? champVar : champVarLangue;
					String champCodeSourceLangue = regexRemplacerTout(champCommentaire, champCodeSource, langueNom);
					String champStringLangue = regex("^(champ)?String\\." + langueNom + ":(.*)", champCommentaire);
					if(champStringLangue != null) {
						champCodeSourceLangue = "\"" + StringUtils.replace(StringUtils.replace(champStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\"";
						indexerStockerSolr(champDoc, "champString", langueNom, champString); 
					}

					indexerStockerSolr(champDoc, "champVar", langueNom, champVarLangue); 
					stockerSolr(champDoc, "champNomSimpleComplet", langueNom, champClassePartsLangue.nomSimpleComplet);
					stockerSolr(champDoc, "champNomCanoniqueComplet", langueNom, champClassePartsLangue.nomCanoniqueComplet);
					stockerRegexCommentaires(champCommentaire, langueNom, champDoc, "champCommentaire");
					stockerSolr(champDoc, "champCodeSource", langueNom, champCodeSourceLangue);
				}  

				clientSolrComputate.add(champDoc); 
				clientSolrComputate.commit();
			}
			else if(membreQdox instanceof JavaConstructor) { 
				SolrInputDocument constructeurDoc = classeDocClone.deepCopy();
				JavaConstructor constructeurQdox = (JavaConstructor)membreQdox;
				String constructeurCommentaire = constructeurQdox.getComment();
				List<JavaParameter> constructeurParamsQdox = constructeurQdox.getParameters();
				List<JavaAnnotation> constructeurAnnotations = constructeurQdox.getAnnotations(); 
				List<JavaClass> constructeurExceptionsQdox = constructeurQdox.getExceptions();
				for(Integer constructeurParamNum = 1; constructeurParamNum <= constructeurParamsQdox.size(); constructeurParamNum++) {
					JavaParameter constructeurParamQdox = constructeurParamsQdox.get(constructeurParamNum - 1);
					String constructeurParamVar = constructeurParamQdox.getName();
					stockerListeSolr(constructeurDoc, "constructeurParamsVar", langueNom, constructeurParamVar);
					ClasseParts constructeurParamClasseParts = ClasseParts.initClasseParts(this, constructeurParamQdox.getJavaClass(), langueNom);
					classePartsGenAjouter(constructeurParamClasseParts);
					stockerListeSolr(constructeurDoc, "constructeurParamsNomSimpleComplet", langueNom, constructeurParamClasseParts.nomSimpleComplet);
					stockerListeSolr(constructeurDoc, "constructeurParamsArgsVariables", constructeurParamQdox.isVarArgs());
					for(String langueNom : autresLangues) { 
						String constructeurParamVarLangue = regex("^(constructeur)?Param" + constructeurParamNum + "\\.var\\." + langueNom + ": (.*)", constructeurCommentaire);
						if(constructeurParamVarLangue == null)
							constructeurParamVarLangue = constructeurParamVar;
						ClasseParts constructeurParamClassePartsLangue = ClasseParts.initClasseParts(this, constructeurParamClasseParts, langueNom);

						classePartsGenAjouter(constructeurParamClassePartsLangue);
						stockerListeSolr(constructeurDoc, "constructeurParamsNomSimpleComplet", langueNom, constructeurParamClassePartsLangue.nomSimpleComplet);
						stockerListeSolr(constructeurDoc, "constructeurParamsVar", langueNom, constructeurParamVarLangue);
					}  
				}
				for(JavaAnnotation constructeurAnnotation : constructeurAnnotations) {
//					String constructeurAnnotationBlocCode = stockerListeSolr(constructeurDoc, "constructeurAnnotationBlocCode", langueNom, annotation.toString());
				}
				for(JavaClass constructeurExceptionQdox : constructeurExceptionsQdox) {
					String constructeurExceptionNomSimpleComplet = StringUtils.substringAfterLast(constructeurExceptionQdox.getCanonicalName(), ".");
					stockerListeSolr(constructeurDoc, "constructeurExceptionNomSimpleComplet", constructeurExceptionNomSimpleComplet);
				}
				String constructeurCle = classeCheminAbsolu + "." + classeNomSimple + "(";

				for(int i = 0; i < constructeurParamsQdox.size(); i++) {
					JavaParameter paramQdox = constructeurParamsQdox.get(i);
					if(i > 0)
						constructeurCle += ", ";
					constructeurCle += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
				}
				constructeurCle += ")"; 

				constructeurDoc.addField("id", constructeurCle);
				indexerStockerSolr(constructeurDoc, "partEstConstructeur", true);
				indexerStockerSolr(constructeurDoc, "partNumero", partNumero);

				indexerStockerSolr(constructeurDoc, "constructeurEstPublic", constructeurQdox.isPublic());
				indexerStockerSolr(constructeurDoc, "constructeurEstProtege", constructeurQdox.isProtected());
				indexerStockerSolr(constructeurDoc, "constructeurEstPrive", constructeurQdox.isPrivate());
				indexerStockerSolr(constructeurDoc, "constructeurEstStatique", constructeurQdox.isStatic());
				indexerStockerSolr(constructeurDoc, "constructeurEstFinale", constructeurQdox.isFinal());
				indexerStockerSolr(constructeurDoc, "constructeurEstAbstrait", constructeurQdox.isAbstract());
				indexerStockerSolr(constructeurDoc, "constructeurEstNatif", constructeurQdox.isNative());
				stockerRegexCommentaires(constructeurCommentaire, langueNom, constructeurDoc, "constructeurCommentaire");

				String constructeurCodeSource = constructeurQdox.getSourceCode();
				String constructeurCodeSourceLangue = constructeurCodeSource;
				ArrayList<String> remplacerClesLangue = regexListe("^r." + langueNom + "\\s*=\\s*(.*)\\n.*", constructeurCommentaire);
				ArrayList<String> remplacerValeursLangue = regexListe("^r." + langueNom + "\\s*=\\s*.*\\n(.*)", constructeurCommentaire);
				for(int i = 0; i < remplacerClesLangue.size(); i++) {
					String cle = remplacerClesLangue.get(i);
					String valeur = remplacerValeursLangue.get(i);
					StringUtils.replace(constructeurCodeSourceLangue, cle, valeur);
				}
				stockerSolr(constructeurDoc, "constructeurCodeSource", langueNom, constructeurCodeSourceLangue);

				for(String langueNom : autresLangues) {  
					constructeurCodeSourceLangue = regexRemplacerTout(constructeurCommentaire, constructeurCodeSource, langueNom);
					stockerSolr(constructeurDoc, "constructeurCodeSource", langueNom, constructeurCodeSourceLangue);
					stockerRegexCommentaires(constructeurCommentaire, langueNom, constructeurDoc, "constructeurCommentaire");
				} 

				clientSolrComputate.add(constructeurDoc); 
			}
			else if(membreQdox instanceof JavaMethod) {   
				JavaMethod methodeQdox = (JavaMethod)membreQdox;
				String methodeCommentaire = methodeQdox.getComment();
				Boolean ignorer = "true".equals(regex("ignorer: (.*)", methodeCommentaire));
				if(!ignorer) {
					JavaClass methodeClasseQdoxRetour = methodeQdox.getReturns();
					String methodeNomCanoniqueRetourComplet = null;
					String methodeNomCanoniqueRetour = null;
					JavaClass classeQdoxRetour = methodeQdox.getReturns();
					List<JavaParameter> methodeParamsQdox = methodeQdox.getParameters();
		
					List<JavaAnnotation> annotations = methodeQdox.getAnnotations(); 
					ArrayList<String> annotationsLangue = new ArrayList<String>();
					Boolean methodeEstTest = false;
					Boolean methodeEstSubstitue = false;
					String methodeVar = methodeQdox.getName();
					for(JavaAnnotation annotation : annotations) {
						String methodeAnnotationLangue = annotation.getType().getCanonicalName();
	
						if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
							methodeEstTest = true;
						}
						if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
							methodeEstSubstitue = true;
						}
					}

					List<JavaClass> methodeExceptionsQdox = methodeQdox.getExceptions();
	
					if(classeEtendGen && !methodeEstSubstitue && !methodeQdox.isStatic() && !methodeQdox.isFinal() && methodeQdox.getDeclaringClass().equals(classeQdox) 
							&& methodeQdox.isProtected() && methodeParamsQdox.size() == 1 && classeQdoxRetour.isVoid()
							&& StringUtils.startsWith(methodeQdox.getName(), "_")) {

						// est Entite. 
						SolrInputDocument entiteDoc = classeDocClone.deepCopy();
						String entiteVar = indexerStockerSolr(entiteDoc, "entiteVar", langueNom, StringUtils.substringAfter(methodeQdox.getName(), "_"));
						indexerStockerListeSolr(classeDoc, "classeEntiteVars", langueNom, entiteVar);
						String entiteVarCapitalise = indexerStockerSolr(entiteDoc, "entiteVarCapitalise", langueNom, StringUtils.capitalize(entiteVar));
						JavaClass entiteClasseQdox = methodeParamsQdox.get(0).getJavaClass();
						ClasseParts entiteClasseParts = ClasseParts.initClasseParts(this, entiteClasseQdox, langueNom);
						Boolean entiteCouverture = false;

						if(entiteClasseParts.nomSimple.equals("Couverture")) {
							entiteClasseParts = ClasseParts.initClasseParts(this, entiteClasseParts.nomCanoniqueGenerique, entiteVar);
							entiteCouverture = true;
							classeContientCouverture = true;
						}

						classePartsGenAjouter(entiteClasseParts);
						classePartsGenPageAjouter(entiteClasseParts);
						List<String> entiteNomsCanoniquesSuperEtMoiSansGen = new ArrayList<String>();
						if(StringUtils.isNotEmpty(entiteClasseParts.nomCanoniqueGenerique)) {
							ClasseParts classePartsGenerique = ClasseParts.initClasseParts(this, entiteClasseParts.nomCanoniqueGenerique, langueNom);
							classePartsGenAjouter(classePartsGenerique);
							classePartsGenPageAjouter(classePartsGenerique);
							classePartsGenAjouter(entiteClasseParts);

							if(classePartsGenerique.documentSolr != null) {
								List<String> entiteClassesSuperEtMoiSansGen = (List<String>)classePartsGenerique.documentSolr.get("entiteClassesSuperEtMoiSansGen_stored_strings");
								if(entiteClassesSuperEtMoiSansGen != null) {
									for(String nomCanonique : entiteClassesSuperEtMoiSansGen) {
										entiteNomsCanoniquesSuperEtMoiSansGen.add(nomCanonique);
										indexerStockerListeSolr(entiteDoc, "entiteClassesSuperEtMoiSansGen", nomCanonique); 
									}
								}
							}
						}
						else if(entiteClasseParts != null && entiteClasseParts.documentSolr != null) {

							List<String> entiteClassesSuperEtMoiSansGen = (List<String>)entiteClasseParts.documentSolr.get("entiteClassesSuperEtMoiSansGen_stored_strings");
							if(entiteClassesSuperEtMoiSansGen != null) {
								for(String nomCanonique : entiteClassesSuperEtMoiSansGen) {
									entiteNomsCanoniquesSuperEtMoiSansGen.add(nomCanonique);
									indexerStockerListeSolr(entiteDoc, "entiteClassesSuperEtMoiSansGen", nomCanonique); 
								}
							}
						}

						indexerStockerSolr(entiteDoc, "entiteCouverture", entiteCouverture);
						Boolean entiteInitialise = indexerStockerSolr(entiteDoc, "entiteInitialise", !entiteVar.endsWith("_") && BooleanUtils.isTrue(entiteClasseParts.etendGen));

						String entiteNomCanonique = indexerStockerSolr(entiteDoc, "entiteNomCanonique", langueNom, entiteClasseParts.nomCanonique);
						String entiteNomSimple = indexerStockerSolr(entiteDoc, "entiteNomSimple", langueNom, entiteClasseParts.nomSimple);
						String entiteNomCompletGenerique = indexerStockerSolr(entiteDoc, "entiteNomCompletGenerique", langueNom, entiteClasseParts.nomCanoniqueGenerique);
						String entiteNomCanoniqueGenerique = indexerStockerSolr(entiteDoc, "entiteNomCanoniqueGenerique", langueNom, entiteClasseParts.nomCanoniqueGenerique);
						String entiteNomSimpleGenerique = indexerStockerSolr(entiteDoc, "entiteNomSimpleGenerique", langueNom, entiteClasseParts.nomSimpleGenerique);
						String entiteNomCanoniqueActuel = entiteNomCanoniqueGenerique == null ? entiteNomCanonique : entiteNomCanoniqueGenerique;
						String entiteNomSimpleActuel = entiteNomSimpleGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique;
						indexerStockerSolr(entiteDoc, "entiteNomCanoniqueComplet", langueNom, entiteClasseParts.nomCanoniqueComplet);
						indexerStockerSolr(entiteDoc, "entiteNomSimpleComplet", langueNom, entiteClasseParts.nomSimpleComplet);
						indexerStockerSolr(entiteDoc, "entiteNomSimpleCompletGenerique", langueNom, entiteClasseParts.nomSimpleGenerique);

						JavaMethod entiteSetter = null;
						try {
							entiteSetter = classeQdox.getMethodBySignature("set" + entiteVarCapitalise, new ArrayList<JavaType>() {{ add(classeQdoxString); }}, true);
						} catch (Exception e) {
						}

						Boolean entiteDefinir = 
								entiteNomCanonique.equals(VAL_nomCanoniqueString)
								|| classePartsChaine != null && entiteNomCanonique.equals(classePartsChaine.nomCanonique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueBoolean)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueInteger)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueBigDecimal)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueDouble)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueFloat)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueLong)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueZonedDateTime)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueLocalDateTime)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueLocalDate)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueTimestamp)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueDate)
								|| classePartsChaine != null && entiteNomCanonique.equals(VAL_nomCanoniqueList) && classePartsChaine.nomCanonique.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueString.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueBoolean.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueLocalDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueTimestamp.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueList) && VAL_nomCanoniqueDate.equals(entiteNomCanoniqueGenerique)
								|| classePartsChaine != null && entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && classePartsChaine.nomCanonique.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueString.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueBoolean.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueLocalDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueTimestamp.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueHashSet) && VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)
								|| classePartsChaine != null && entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && classePartsChaine.nomCanonique.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueString.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueBoolean.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueLocalDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueTimestamp.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueDate.equals(entiteNomCanoniqueGenerique)
								|| entiteNomCanonique.equals(VAL_nomCanoniqueArrayList) && VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)
								|| entiteSetter != null
								;
						
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
						indexerStockerSolr(entiteDoc, "entiteNomCanoniqueBase", langueNom, entiteNomCanoniqueBase);
						if(entiteCouverture)
							entiteClasseQdox = bricoleur.getClassByName(entiteNomCanonique);

						for(String siteEcrireMethode : siteEcrireMethodes) {
							if(entiteClasseQdox.getMethodBySignature(siteEcrireMethode, new ArrayList<JavaType>()) != null
									|| entiteClasseQdox.getMethodBySignature(siteEcrireMethode + classeNomSimple, new ArrayList<JavaType>()) != null)
								indexerStockerListeSolr(entiteDoc, "entiteEcrireMethodes",  siteEcrireMethode);
						}
						
						String entiteNomSimpleBase = null;
						if(StringUtils.isNotEmpty(entiteNomCanoniqueBase)) {
							entiteNomSimpleBase = StringUtils.substringAfterLast(entiteNomCanoniqueBase, ".");
						}
						indexerStockerSolr(entiteDoc, "entiteNomSimpleBase", langueNom, entiteNomSimpleBase);
						
						String entiteVarParam = methodeParamsQdox.get(0).getName();
//						if(entiteClasseParts.nomCanonique.equals(ArrayList.class.getCanonicalName()) || entiteClasseParts.nomCanonique.equals(List.class.getCanonicalName()))
//							entiteVarParam = "l";
//						else
//							entiteVarParam = "o";
						indexerStockerSolr(entiteDoc, "entiteVarParam", langueNom, entiteVarParam);
						
						String entiteVarCouverture = indexerStockerSolr(entiteDoc, "entiteVarCouverture", langueNom, entiteVar + "Couverture");

						Boolean entiteInitLoin = indexerStockerSolr(entiteDoc, "entiteInitLoin", !entiteVar.endsWith("_"));
						
//						String entiteParamVar = StringUtils.equalsAny(entiteClasseQdox, "");
//						indexerStockerSolr(entiteDoc, "entiteParamVar", regexTrouve("^(entite)?exact:\\s*(true)$", methodeCommentaire));
//							if(nomCanonique.equals(classe_.nomCanoniqueArrayList) || nomCanonique.equals(classe_.nomCanoniqueList))
//								o.tout("l");
//							else if(o.estVide())
//								o.tout("o");

						if(entiteNomsCanoniquesSuperEtMoiSansGen.size() > 0) {
							String fqClassesSuperEtMoi = "(" + classesSuperQdoxEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c.getCanonicalName())).collect(Collectors.joining(" OR ")) + ")";

							SolrQuery rechercheSolrMethodeAvant = new SolrQuery();   
							rechercheSolrMethodeAvant.setQuery("*:*");
							rechercheSolrMethodeAvant.setRows(10);
							String fqMethodeAvant = "(" + entiteNomsCanoniquesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars("avant" + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							rechercheSolrMethodeAvant.addFilterQuery("entiteClassesSuperEtMoiSansGen_indexed_strings:" + fqClassesSuperEtMoi);
							rechercheSolrMethodeAvant.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
							rechercheSolrMethodeAvant.addFilterQuery("partEstMethode_indexed_boolean:true");
							rechercheSolrMethodeAvant.addFilterQuery("methodeVar_" + langueNom + "_indexed_string:" + fqMethodeAvant);
							QueryResponse reponseRechercheMethodeAvant = clientSolrComputate.query(rechercheSolrMethodeAvant);
							SolrDocumentList listeRechercheMethodeAvant = reponseRechercheMethodeAvant.getResults();
	
							for(SolrDocument documentSolr : listeRechercheMethodeAvant) {
								String methodeVarActuel = (String)documentSolr.get("methodeVar_" + langueNom + "_stored_string");
								String methodeClasseNomCanonique = (String)documentSolr.get("classeNomCanonique_frFR_stored_string");
								List<String> methodeParamsNomSimpleComplet = (List<String>)documentSolr.get("methodeParamsNomSimpleComplet_" + langueNom + "_stored_strings");
								String methodeParamNomSimpleComplet = methodeParamsNomSimpleComplet.get(0);
								List<String> methodeParamsVar = (List<String>)documentSolr.get("methodeParamsVar_" + langueNom + "_stored_strings");
								String methodeParamVar = methodeParamsVar.get(0);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantVisibilite", BooleanUtils.isTrue((Boolean)documentSolr.get("methodeEstPublic_stored_boolean")) ? "public" : "protected");
								stockerListeSolr(entiteDoc, "entiteMethodesAvantVar", methodeVarActuel);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantParamVar", methodeParamVar);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantParamNomSimple", methodeParamNomSimpleComplet);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantNomParam", methodeParamsVar.size() > 1);
								Boolean entiteMethodesAvantEcrire = (StringUtils.equals(methodeClasseNomCanonique, classeNomCanonique)) && !classeMethodesEcrites.contains(methodeVarActuel);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantEcrire", entiteMethodesAvantEcrire);
								classeMethodesEcrites.add(methodeVarActuel);
								List<String> methodeParamsNomCanonique = (List<String>)documentSolr.get("methodeParamsNomCanonique_" + langueNom + "_stored_strings");
								if(methodeParamsNomCanonique != null) {
									String methodeParamNomCanonique = methodeParamsNomCanonique.get(0);
									classePartsGenAjouter(ClasseParts.initClasseParts(this, methodeParamNomCanonique, langueNom));
								}
							}
	
	//						List<JavaMethod> entiteMethodesAvant = new ArrayList<JavaMethod>();
	//						entiteMethodesAvant.add(classeQdox.getMethodBySignature(entiteVar + "Avant", new ArrayList<JavaType>() {{ add(entiteClasseQdox); }}, true));
	//						for(JavaClass c : classesSuperQdoxEtMoi) {
	//							String cNomSimple = StringUtils.substringAfterLast(c.getCanonicalName(), ".");
	//							entiteMethodesAvant.add(classeQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); }}, true));
	//							entiteMethodesAvant.add(classeQdox.getMethodBySignature("avant" + cNomSimple, new ArrayList<JavaType>() {{ add(c); add(classeQdoxString); }}, true));
	//						}
	//						for(JavaMethod methode : entiteMethodesAvant) {
	//							if(methode != null) {
	//								JavaParameter param = methode.getParameters().get(0);
	//								stockerListeSolr(entiteDoc, "entiteMethodesAvantVisibilite", methode.isPublic() ? "public" : "protected");
	//								stockerListeSolr(entiteDoc, "entiteMethodesAvantVar", methode.getName());
	//								stockerListeSolr(entiteDoc, "entiteMethodesAvantParamVar", param.getName());
	//								stockerListeSolr(entiteDoc, "entiteMethodesAvantParamNomSimple", StringUtils.substringAfterLast(param.getCanonicalName(), "."));
	//								stockerListeSolr(entiteDoc, "entiteMethodesAvantNomParam", methode.getParameters().size() > 1);
	//							}
	//						}
	
							SolrQuery rechercheSolrMethodeApres = new SolrQuery();   
							rechercheSolrMethodeApres.setQuery("*:*");
							rechercheSolrMethodeApres.setRows(10);
							String fqMethodeApres = "(" + entiteNomsCanoniquesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars("apres" + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							rechercheSolrMethodeApres.addFilterQuery("entiteClassesSuperEtMoiSansGen_indexed_strings:" + fqClassesSuperEtMoi);
							rechercheSolrMethodeApres.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
							rechercheSolrMethodeApres.addFilterQuery("partEstMethode_indexed_boolean:true");
							rechercheSolrMethodeApres.addFilterQuery("methodeVar_" + langueNom + "_indexed_string:" + fqMethodeApres);
							QueryResponse reponseRechercheMethodeApres = clientSolrComputate.query(rechercheSolrMethodeApres);
							SolrDocumentList listeRechercheMethodeApres = reponseRechercheMethodeApres.getResults();
	
							for(SolrDocument documentSolr : listeRechercheMethodeApres) {
								String methodeVarActuel = (String)documentSolr.get("methodeVar_" + langueNom + "_stored_string");
								String methodeClasseNomCanonique = (String)documentSolr.get("classeNomCanonique_frFR_stored_string");
								List<String> methodeParamsNomSimpleComplet = (List<String>)documentSolr.get("methodeParamsNomSimpleComplet_" + langueNom + "_stored_strings");
								String methodeParamNomSimpleComplet = methodeParamsNomSimpleComplet.get(0);
								List<String> methodeParamsVar = (List<String>)documentSolr.get("methodeParamsVar_" + langueNom + "_stored_strings");
								String methodeParamVar = methodeParamsVar.get(0);
								stockerListeSolr(entiteDoc, "entiteMethodesApresVisibilite", BooleanUtils.isTrue((Boolean)documentSolr.get("methodeEstPublic_stored_boolean")) ? "public" : "protected");
								stockerListeSolr(entiteDoc, "entiteMethodesApresVar", methodeVarActuel);
								stockerListeSolr(entiteDoc, "entiteMethodesApresParamVar", methodeParamVar);
								stockerListeSolr(entiteDoc, "entiteMethodesApresParamNomSimple", methodeParamNomSimpleComplet);
								stockerListeSolr(entiteDoc, "entiteMethodesApresNomParam", methodeParamsVar.size() > 1);
								Boolean entiteMethodesAvantEcrire = (StringUtils.equals(methodeClasseNomCanonique, classeNomCanonique)) && !classeMethodesEcrites.contains(methodeVarActuel);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantEcrire", entiteMethodesAvantEcrire);
								classeMethodesEcrites.add(methodeVarActuel);
								List<String> methodeParamsNomCanonique = (List<String>)documentSolr.get("methodeParamsNomCanonique_" + langueNom + "_stored_strings");
								if(methodeParamsNomCanonique != null) {
									String methodeParamNomCanonique = methodeParamsNomCanonique.get(0);
									classePartsGenAjouter(ClasseParts.initClasseParts(this, methodeParamNomCanonique, langueNom));
								}
							}
						}

//						List<JavaMethod> entiteMethodesApres = new ArrayList<JavaMethod>();
//						entiteMethodesApres.add(classeQdox.getMethodBySignature(entiteVar + "Apres", new ArrayList<JavaType>() {{ add(entiteClasseQdox); }}, true));
//						for(JavaClass c : classesSuperQdoxEtMoi) {
//							String cNomSimple = StringUtils.substringAfterLast(c.getCanonicalName(), ".");
//							entiteMethodesApres.add(classeQdox.getMethodBySignature("apres" + cNomSimple, new ArrayList<JavaType>() {{ add(c); }}, true));
//							entiteMethodesApres.add(classeQdox.getMethodBySignature("apres" + cNomSimple, new ArrayList<JavaType>() {{ add(c); add(classeQdoxString); }}, true));
//						}
//						for(JavaMethod methode : entiteMethodesApres) {
//							if(methode != null) {
//								JavaParameter param = methode.getParameters().get(0);
//								stockerListeSolr(entiteDoc, "entiteMethodesApresVar", methode.getName());
//								stockerListeSolr(entiteDoc, "entiteMethodesApresParamVar", param.getName());
//								stockerListeSolr(entiteDoc, "entiteMethodesApresParamNomSimple", StringUtils.substringAfterLast(param.getCanonicalName(), "."));
//								stockerListeSolr(entiteDoc, "entiteMethodesApresNomParam", methode.getParameters().size() > 1);
//							}
//						}

						if(methodeCommentaire != null) {

							Matcher entiteValsRecherche = Pattern.compile("^(entite)?Val\\.(\\w+)\\.(\\w+):(.*)", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteValsTrouves = entiteValsRecherche.find();
							while(entiteValsTrouves) {
								String entiteValLangue = entiteValsRecherche.group(2);
								String entiteValVar = entiteValsRecherche.group(3);
								String entiteValValeur = entiteValsRecherche.group(4);
								stockerListeSolr(entiteDoc, "entiteValsVar", entiteValVar);
								stockerListeSolr(entiteDoc, "entiteValsLangue", entiteValLangue);
								stockerListeSolr(entiteDoc, "entiteValsValeur", entiteValValeur);
								entiteValsTrouves = entiteValsRecherche.find();
							}

							Matcher entiteOptionsRecherche = Pattern.compile("^(entite)?Option\\.(\\w+)\\.([^:\\n]+)(:([^:\\n]+))?(:([^\\n]+))?", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteOptionsTrouves = entiteOptionsRecherche.find();
							while(entiteOptionsTrouves) {
								String entiteOptionLangue = entiteOptionsRecherche.group(2);
								String entiteOptionVar = entiteOptionsRecherche.group(3);
								String entiteOptionDescription = entiteOptionsRecherche.group(5);
								String entiteOptionValeurs = entiteOptionsRecherche.group(7);
								if(StringUtils.isBlank(entiteOptionDescription))
									entiteOptionDescription = entiteOptionVar;
								stockerListeSolr(entiteDoc, "entiteOptionsVar", entiteOptionVar);
								stockerListeSolr(entiteDoc, "entiteOptionsLangue", entiteOptionLangue);
								stockerListeSolr(entiteDoc, "entiteOptionsDescription", entiteOptionDescription);
								stockerListeSolr(entiteDoc, "entiteOptionsValeurs", StringUtils.isNotBlank(entiteOptionValeurs) ? entiteOptionValeurs : "");
								entiteOptionsTrouves = entiteOptionsRecherche.find();
							}
							if(entiteOptionsTrouves)
								stockerSolr(entiteDoc, "entiteOptions", true);

							Matcher entiteMotsClesRecherche = Pattern.compile("^(entite)?MotCle:\\s*(.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteMotsClesTrouves = entiteMotsClesRecherche.find();
							boolean entiteMotsClesTrouvesActuel = entiteMotsClesTrouves;
							while(entiteMotsClesTrouvesActuel) {
								String entiteMotCleValeur = entiteMotsClesRecherche.group(2);
								indexerStockerListeSolr(entiteDoc, "entiteMotsCles", entiteMotCleValeur);
								entiteMotsClesTrouves = true;
								entiteMotsClesTrouvesActuel = entiteMotsClesRecherche.find();
								if(!classeMotsCles.contains(entiteMotCleValeur))
									classeMotsCles.add(entiteMotCleValeur);
								classeMotsClesTrouves = true;
							}
							indexerStockerSolr(entiteDoc, "entiteMotsClesTrouves", entiteMotsClesTrouves); 

							String sqlString = regex("^(entite)?Sql:\\s*(.*)$", methodeCommentaire, 2);
							if(NumberUtils.isCreatable(sqlString)) {
								Integer sqlInteger = Integer.parseInt(sqlString);
								Integer sqlMigration = Math.abs(sqlInteger);
								Boolean sqlCreate = sqlInteger > 0;
								Boolean sqlDrop = sqlInteger < 0;
								indexerStockerSolr(entiteDoc, "sqlMigration", sqlMigration);
								if(sqlCreate)
									indexerStockerSolr(entiteDoc, "sqlCreate", sqlCreate);
								if(sqlDrop)
									indexerStockerSolr(entiteDoc, "sqlDrop", sqlDrop);
							}

							Matcher entiteMapRecherche = Pattern.compile("^(entite)?Map.([^:]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteMapTrouve = entiteMapRecherche.find();
							boolean entiteMapTrouveActuel = entiteMapTrouve;
							while(entiteMapTrouveActuel) {
								String entiteMapCle = entiteMapRecherche.group(2);
								String entiteMapValeur = entiteMapRecherche.group(3);
								String[] entiteMapCleParts = StringUtils.split(entiteMapCle, ".");
								if(entiteMapCleParts.length == 2) {
									String entiteMapCleType = entiteMapCleParts[0];
									if("Integer".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Integer.parseInt(entiteMapValeur));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("Double".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Double.parseDouble(entiteMapValeur));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("Long".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Long.parseLong(entiteMapValeur));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("ZonedDateTime".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Date.from(ZonedDateTime.parse(entiteMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant()));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("LocalDateTime".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Date.from(LocalDateTime.parse(entiteMapValeur, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else if("LocalDate".equals(entiteMapCleType) && NumberUtils.isCreatable(entiteMapValeur)) {
										try {
											indexerStockerSolr(entiteDoc, entiteMapCleParts[1], Date.from(LocalDate.parse(entiteMapValeur, DateTimeFormatter.ISO_OFFSET_DATE).atStartOfDay(ZoneId.systemDefault()).toInstant()));
										} catch (Exception e) {
											System.err.println(ExceptionUtils.getStackTrace(e));
										}
									}
									else {
										indexerStockerSolr(entiteDoc, entiteMapCle, entiteMapValeur);
									}
								}
								else {
									indexerStockerSolr(entiteDoc, entiteMapCle, entiteMapValeur);
								}
								entiteMapTrouveActuel = entiteMapRecherche.find();
							}
						}

						indexerStockerSolr(entiteDoc, "entiteExact", regexTrouve("^(entite)?Exact:\\s*(true)$", methodeCommentaire));
						Boolean entiteClePrimaire = indexerStockerSolr(entiteDoc, "entiteClePrimaire", regexTrouve("^(entite)?ClePrimaire:\\s*(true)$", methodeCommentaire));
						Boolean entiteCleUnique = indexerStockerSolr(entiteDoc, "entiteCleUnique", regexTrouve("^(entite)?CleUnique:\\s*(true)$", methodeCommentaire));
						Boolean entiteCrypte = indexerStockerSolr(entiteDoc, "entiteCrypte", regexTrouve("^(entite)?Crypte:\\s*(true)$", methodeCommentaire));
						Boolean entiteSuggere = indexerStockerSolr(entiteDoc, "entiteSuggere", regexTrouve("^(entite)?Suggere:\\s*(true)$", methodeCommentaire));
						Boolean entiteSauvegarde = indexerStockerSolr(entiteDoc, "entiteSauvegarde", regexTrouve("^(entite)?Sauvegarde:\\s*(true)$", methodeCommentaire));
						Boolean entiteIndexe = indexerStockerSolr(entiteDoc, "entiteIndexe", regexTrouve("^(entite)?Indexe:\\s*(true)$", methodeCommentaire));
						Boolean entiteIncremente = indexerStockerSolr(entiteDoc, "entiteIncremente", regexTrouve("^(entite)?Incremente:\\s*(true)$", methodeCommentaire));
						Boolean entiteStocke = indexerStockerSolr(entiteDoc, "entiteStocke", regexTrouve("^(entite)?Stocke:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteIndexeOuStocke", entiteCleUnique || entiteCrypte || entiteSuggere || entiteIndexe || entiteStocke || entiteIncremente);
						indexerStockerSolr(entiteDoc, "entiteTexte", regexTrouve("^(entite)?Texte:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteIgnorer", regexTrouve("^(entite)?Ignorer:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteDeclarer", regexTrouve("^(entite)?Declarer:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteRechercher", regexTrouve("^(entite)?Rechercher:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteAjouter", regexTrouve("^(entite)?Ajouter:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSupprimer", regexTrouve("^(entite)?Supprimer:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteModifier", regexTrouve("^(entite)?Modifier:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteRecharger", regexTrouve("^(entite)?Recharger:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteMultiligne", regexTrouve("^(entite)?Multiligne:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteCles", regexTrouve("^(entite)?Cles:\\s*(true)$", methodeCommentaire));

						indexerStockerSolr(entiteDoc, "entiteNomAffichage", langueNom, regexLangue(langueNom, "(entite)?NomAffichage", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteDescription", langueNom, regexLangue(langueNom, "(entite)?Description", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteOptionnel", regexTrouve("^(entite)?Optionnel:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteHtmlTooltip", langueNom, regexLangue(langueNom, "(entite)?HtmlTooltip", methodeCommentaire));
//						indexerStockerSolr(entiteDoc, "entiteVarApi", regex("^(entite)?EntiteVarApi:\\s*(.*)$", methodeCommentaire));
						indexerStockerSolrRegex(entiteDoc, langueNom, "entiteVarApi", "VarApi", methodeCommentaire);
						indexerStockerSolr(entiteDoc, "entiteEnumNomSimple", langueNom, regexLangue(langueNom, "(entite)?EnumNomSimple", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteEnumVar", langueNom, regexLangue(langueNom, "(entite)?EnumVar", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteEnumVarDescription", langueNom, regexLangue(langueNom, "(entite)?EnumVarDescription", methodeCommentaire));

						Boolean entiteHtml = regexTrouve("^(entite)?Html:\\s*(true)$", methodeCommentaire);

						{ 
							String str = regex("^(entite)?HtmlColonne:\\s*(.*)$", methodeCommentaire);
							if(NumberUtils.isCreatable(str)) {
								indexerStockerSolr(entiteDoc, "entiteHtmlColonne", Double.parseDouble(str));
								entiteHtml = true;
							}
						}
						{ 
							String str = regex("^(entite)?HtmlLigne:\\s*(.*)$", methodeCommentaire);
							if(NumberUtils.isCreatable(str)) {
								indexerStockerSolr(entiteDoc, "entiteHtmlLigne", Integer.parseInt(str));
								entiteHtml = true;
							}
						}
						{ 
							String str = regex("^(entite)?HtmlCellule:\\s*(.*)$", methodeCommentaire);
							if(NumberUtils.isCreatable(str)) {
								indexerStockerSolr(entiteDoc, "entiteHtmlCellule", Integer.parseInt(str));
								entiteHtml = true;
							}
						}

						indexerStockerSolr(entiteDoc, "entiteHtml", entiteHtml);

						{ 
							String str = regex("^(entite)?LongeurMin:\\s*(.*)$", methodeCommentaire, 2);
							Integer num = NumberUtils.isCreatable(str) ? Integer.parseInt(str) : null;
							if(num != null)
								indexerStockerSolr(entiteDoc, "entiteLongeurMin", num);
						}

						{
							String str = regex("^(entite)?LongeurMax:\\s*(.*)$", methodeCommentaire, 2);
							Integer num = NumberUtils.isCreatable(str) ? Integer.parseInt(str) : null;
							if(num != null)
								indexerStockerSolr(entiteDoc, "entiteLongeurMax", num);
						}

						{
							String str = regex("^(entite)?Min:\\s*(.*)$", methodeCommentaire, 2);
							Double num = NumberUtils.isCreatable(str) ? Double.parseDouble(str) : null;
							if(num != null)
								indexerStockerSolr(entiteDoc, "entiteMin", num);
						}

						{
							String str = regex("^(entite)?Max:\\s*(.*)$", methodeCommentaire, 2);
							Double num = NumberUtils.isCreatable(str) ? Double.parseDouble(str) : null;
							if(num != null)
								indexerStockerSolr(entiteDoc, "entiteMax", num);
						}

						for(String langueNom : autresLangues) {  
							indexerStockerSolr(entiteDoc, "entiteNomAffichage", langueNom, regexLangue(langueNom, "(entite)?NomAffichage", methodeCommentaire));
							indexerStockerSolr(entiteDoc, "entiteEnumVarDescription", langueNom, regexLangue(langueNom, "(entite)?EnumVarDescription", methodeCommentaire));
							indexerStockerSolr(entiteDoc, "entiteHtmlTooltip", langueNom, regexLangue(langueNom, "(entite)?HtmlTooltip", methodeCommentaire));
//							indexerStockerSolr(entiteDoc, "entiteVarApi", langueNom, regex("^(entite)?EntiteVarApi." + langueNom + ":\\s*(.*)$", methodeCommentaire, 1));
							indexerStockerSolrRegex(entiteDoc, langueNom, "entiteVarApi", "VarApi", methodeCommentaire);
							indexerStockerSolr(entiteDoc, "entiteEnumVar", langueNom, regexLangue(langueNom, "(entite)?EnumVar", methodeCommentaire));
						}

						Matcher entiteAttribuerRecherche = methodeCommentaire == null ? null : Pattern.compile("^(entite)?Attribuer:\\s*([^\\.]+)\\.(.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
						boolean entiteAttribuerTrouve = entiteAttribuerRecherche == null ? false : entiteAttribuerRecherche.find();
						if(entiteAttribuerTrouve) {
							String entiteAttribuerNomSimple = entiteAttribuerRecherche.group(2);
							String entiteAttribuerVar = entiteAttribuerRecherche.group(3);

							SolrQuery rechercheSolrClasse = new SolrQuery();   
							rechercheSolrClasse.setQuery("*:*");
							rechercheSolrClasse.setRows(1);
							rechercheSolrClasse.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerNomSimple));
							rechercheSolrClasse.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
							rechercheSolrClasse.addFilterQuery("partEstClasse_indexed_boolean:true");
							QueryResponse reponseRechercheClasse = clientSolrComputate.query(rechercheSolrClasse);
							SolrDocumentList listeRechercheClasse = reponseRechercheClasse.getResults();

							if(listeRechercheClasse.size() > 0) {
								SolrDocument docClasse = listeRechercheClasse.get(0);
								String entiteAttribuerNomCanonique = (String)docClasse.get("classeNomCanonique_" + langueNom + "_stored_string");

								SolrQuery rechercheSolrVar = new SolrQuery();   
								rechercheSolrVar.setQuery("*:*");
								rechercheSolrVar.setRows(1);
								rechercheSolrVar.addFilterQuery("classeNomCanonique_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerNomCanonique));
								rechercheSolrVar.addFilterQuery("entiteVar_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerVar));
								rechercheSolrVar.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
								rechercheSolrVar.addFilterQuery("partEstEntite_indexed_boolean:true");
								QueryResponse reponseRechercheVar = clientSolrComputate.query(rechercheSolrVar);
								SolrDocumentList listeRechercheVar = reponseRechercheVar.getResults();

								if(listeRechercheVar.size() > 0) {
									SolrDocument docEntite = listeRechercheClasse.get(0);
									entiteDefinir = false;

									indexerStockerSolr(entiteDoc, "entiteAttribuer", true);
									indexerStockerSolr(entiteDoc, "entiteAttribuerNomSimple", langueNom, entiteAttribuerNomSimple);
									indexerStockerSolr(entiteDoc, "entiteAttribuerNomCanonique", langueNom, entiteAttribuerNomCanonique);
									indexerStockerSolr(entiteDoc, "entiteAttribuerVar", langueNom, entiteAttribuerVar);

									for(String langueNom : autresLangues) {  
										String entiteAttribuerNomCanoniqueLangue = (String)docClasse.get("classeNomCanonique_" + langueNom + "_stored_string");
										String entiteAttribuerNomSimpleLangue = (String)docClasse.get("classeNomCanonique_" + langueNom + "_stored_string");
										String entiteAttribuerVarLangue = (String)docEntite.get("entiteVar_" + langueNom + "_stored_string");

										indexerStockerSolr(entiteDoc, "entiteAttribuerNomSimple", langueNom, entiteAttribuerNomSimpleLangue);
										indexerStockerSolr(entiteDoc, "entiteAttribuerNomCanonique", langueNom, entiteAttribuerNomCanoniqueLangue);
										indexerStockerSolr(entiteDoc, "entiteAttribuerVar", langueNom, entiteAttribuerVarLangue);
									}
								}
							}
						}
						stockerSolr(entiteDoc, "entiteDefinir", entiteDefinir);

//						boolean entiteCouverture = false;
//	
//						String varEntiteEnUS = regex("^(entite)?Var.enUS: (.*)", methodeQdox.getComment());
//						entite.var.frFR(entiteVar);
//						entite.var.enUS(StringUtils.isEmpty(varEntiteEnUS) ? entiteVar : StringUtils.substringAfter(varEntiteEnUS, "_"));
//	
//						regexCommentaires(methodeQdox.getComment(), entite.commentaire);
//						regexRemplacerTout(methodeQdox.getComment(), methodeQdox.getSourceCode(), entite.codeSource);
//			
//						if(entiteClasseQdox.getFullyQualifiedName().equals(nomCanoniqueCouvertureActuel)) {
//							entiteType = StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteType, "<"), ">");
//							if(StringUtils.contains(entiteType, "<"))
//								entiteClasseQdox = typeBricoleur.getClassByName(StringUtils.substringBefore(entiteType, "<"));
//							else
//								entiteClasseQdox = typeBricoleur.getClassByName(entiteType);
//							entiteNomCanonique = entiteClasseQdox.getCanonicalName();
//							entiteCouverture = true;
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
//						if(!etendCluster && entite.nomCanoniqueGenerique.pasVide()) {
//							boolean listeCluster = etendClasse(nomCanoniqueClusterActuel, entite.nomCanoniqueGenerique.toString());
//							entite.listeCluster(listeCluster);
//						}
//	
//						boolean etendPageXml = entite.etendClasse(nomCanoniquePageXmlActuel);
//						entite.etendPageXml(etendPageXml);
//						if(!etendPageXml && entite.nomCanoniqueGenerique.pasVide()) {
//							boolean listePageXml = etendClasse(nomCanoniquePageXmlActuel, entite.nomCanoniqueGenerique.toString());
//							entite.listePageXml(listePageXml);
//						}
//	
//						boolean etendPagePart = entite.etendClasse(nomCanoniquePagePartActuel);
//						entite.etendPagePart(etendPagePart);
//						if(!etendPagePart && entite.nomCanoniqueGenerique.pasVide()) {
//							boolean listePagePart = etendClasse(nomCanoniquePagePartActuel, entite.nomCanoniqueGenerique.toString());
//							entite.listePagePart(listePagePart);
//						}
//	
//	
//						boolean contientRequeteSite = contientChamp(varRequeteSite.toString(), entite.classeQdox);
//						entite.contientRequeteSite(contientRequeteSite);
//	
//						boolean contientSetterString = contientMethode(entite.var.toString(), classeQdoxString);
//						entite.contientSetterString(contientSetterString);
//	
//						entiteEstCmd(entite);
						
						
						
						
						
						
						
						
						
						
						
						
						
						for(JavaAnnotation annotation : annotations) {
							String entiteAnnotationLangue = indexerStockerSolr(entiteDoc, "entiteAnnotations", langueNom, annotation.getType().getCanonicalName());
						}
//						if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void")) {
//							entiteNomCanoniqueRetourComplet = indexerStocker(entiteDoc, "entiteNomCanoniqueRetourComplet", langueNom, classeQdoxRetour.getGenericCanonicalName());
//							entiteNomCanoniqueRetour = indexerStocker(entiteDoc, "entiteNomCanoniqueRetour", langueNom, classeQdoxRetour.getCanonicalName());
//							String entiteNomSimpleRetour = indexerStocker(entiteDoc, "entiteNomSimpleRetour", langueNom, StringUtils.substringAfterLast(entiteNomCanoniqueRetour, "."));
//							String listeNomTypeOrigineRetourGenerique = entiteNomCanoniqueRetourComplet;
//							String entiteNomCanoniqueRetourGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(listeNomTypeOrigineRetourGenerique, "<"), ">");
//							String entiteNomSimpleRetourComplet;
//							String entiteNomSimpleRetourGenerique;
//							entiteNomCanoniqueRetourGenerique = entiteNomCanoniqueRetourGenerique.contains("<") ? StringUtils.substringBefore(entiteNomCanoniqueRetourGenerique, "<") : entiteNomCanoniqueRetourGenerique;
//							entiteNomCanoniqueRetourGenerique = entiteNomCanoniqueRetourGenerique.contains(",") ? StringUtils.substringBefore(entiteNomCanoniqueRetourGenerique, ",") : entiteNomCanoniqueRetourGenerique;
//							if(StringUtils.isNotEmpty(entiteNomCanoniqueRetourGenerique)) {
//								indexerStocker(entiteDoc, "entiteNomCanoniqueRetourGenerique", langueNom, entiteNomCanoniqueRetourGenerique);
//	
//								if(StringUtils.contains(entiteNomCanoniqueRetourGenerique, "."))
//									entiteNomSimpleRetourGenerique = indexerStocker(entiteDoc, "entiteNomSimpleRetourGenerique", langueNom, StringUtils.substringAfterLast(entiteNomCanoniqueRetourGenerique, "."));
//								else
//									entiteNomSimpleRetourGenerique = indexerStocker(entiteDoc, "entiteNomSimpleRetourGenerique", langueNom, entiteNomCanoniqueRetourGenerique);
//	
//								if(StringUtils.contains(entiteNomSimpleRetourGenerique, ".")) {
//									entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomSimpleRetourComplet", langueNom, concat(StringUtils.substringAfterLast(entiteNomSimpleRetour, "."), "<", entiteNomSimpleRetourGenerique, ">"));
//								}
//								else {
//									entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomSimpleRetourComplet", langueNom, concat(entiteNomSimpleRetour, "<", entiteNomSimpleRetourGenerique, ">"));
//								}
//							}
//							else {
//								entiteNomSimpleRetourComplet = indexerStocker(entiteDoc, "entiteNomCanoniqueRetourComplet", langueNom, entiteNomSimpleRetour);
//							}
//						}
	
						String entiteCle = classeCheminAbsolu + "." + entiteVar;
		
						// Entites Solr du entite. 
		
						entiteDoc.addField("id", entiteCle);
						indexerStockerSolr(entiteDoc, "partEstEntite", true);
						indexerStockerSolr(entiteDoc, "partNumero", partNumero);

						String entiteCodeSource = methodeQdox.getSourceCode();
						String entiteString = regex("^(entite)?String\\." + langueNom + ":(.*)", methodeCommentaire);
						if(entiteString != null) {
							entiteCodeSource = "\n\t\tc.o(\"" + StringUtils.replace(StringUtils.replace(entiteString, "\\", "\\\\"), "\"", "\\\"") + "\");\n\t";
							indexerStockerSolr(entiteDoc, "entiteString", langueNom, entiteString); 
						}
						stockerSolr(entiteDoc, "entiteCodeSource", langueNom, entiteCodeSource); 

						/////////////////////////
						// entiteTypeVertxJson //
						/////////////////////////
						String entiteNomSimpleVertxJson = null;
						String entiteNomCanoniqueVertxJson = null;
						String entiteListeNomSimpleVertxJson = null;
						String entiteListeNomCanoniqueVertxJson = null;
						if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBoolean)) {
							entiteNomSimpleVertxJson = "Boolean";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueBoolean;
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueDate, VAL_nomCanoniqueZonedDateTime)) {
							entiteNomSimpleVertxJson = "Instant";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueInstant;
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", langueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.LocalDateTime", langueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZonedDateTime", langueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, VAL_nomCanoniqueDate, langueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", langueNom));
							classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", langueNom));
							classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.util.Locale", langueNom));
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalDate)) {
							entiteNomSimpleVertxJson = "Instant";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueInstant;
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", langueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.LocalDate", langueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, VAL_nomCanoniqueDate, langueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", langueNom));
							classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", langueNom));
							classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.util.Locale", langueNom));
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLong)) {
							entiteNomSimpleVertxJson = "Long";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueLong;
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBigDecimal)) {
							entiteNomSimpleVertxJson = "Double";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueLong;
							classePartsGenAjouter(ClasseParts.initClasseParts(this, NumberUtils.class.getCanonicalName(), langueNom));
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueDouble)) {
							entiteNomSimpleVertxJson = "Double";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueDouble;
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueFloat)) {
							entiteNomSimpleVertxJson = "Float";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueFloat;
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueInteger)) {
							entiteNomSimpleVertxJson = "Integer";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueInteger;
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList, VAL_nomCanoniqueSet, VAL_nomCanoniqueHashSet)) {
							if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBoolean)) {
								entiteNomSimpleVertxJson = "JsonArray";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Boolean";
								entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueBoolean;
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueDate, VAL_nomCanoniqueZonedDateTime)) {
								entiteNomSimpleVertxJson = "JsonArray";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Instant";
								entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueInstant;
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLocalDate)) {
								entiteNomSimpleVertxJson = "JsonArray";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Instant";
								entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueInstant;
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLong)) {
								entiteNomSimpleVertxJson = "JsonArray";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Long";
								entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueLong;
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBigDecimal)) {
								entiteNomSimpleVertxJson = "JsonArray";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Long";
								entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueLong;
								classePartsGenAjouter(ClasseParts.initClasseParts(this, NumberUtils.class.getCanonicalName(), langueNom));
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueDouble)) {
								entiteNomSimpleVertxJson = "JsonArray";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Double";
								entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueDouble;
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueFloat)) {
								entiteNomSimpleVertxJson = "JsonArray";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Float";
								entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueFloat;
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueInteger)) {
								entiteNomSimpleVertxJson = "JsonArray";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
								entiteListeNomSimpleVertxJson = "Integer";
								entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueInteger;
							}
							stockerSolr(entiteDoc, "entiteListeNomSimpleVertxJson", entiteListeNomSimpleVertxJson);
							stockerSolr(entiteDoc, "entiteListeNomCanoniqueVertxJson", entiteListeNomCanoniqueVertxJson);
							classePartsGenAjouter(ClasseParts.initClasseParts(this, entiteListeNomCanoniqueVertxJson, langueNom));
						}
						else if(classePartsChaine != null && StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueString, classePartsChaine.nomCanonique)) {
							entiteNomSimpleVertxJson = "String";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
						}
						classePartsGenAjouter(ClasseParts.initClasseParts(this, entiteNomCanoniqueVertxJson, langueNom));
						stockerSolr(entiteDoc, "entiteNomSimpleVertxJson", entiteNomSimpleVertxJson);
						stockerSolr(entiteDoc, "entiteNomCanoniqueVertxJson", entiteNomCanoniqueVertxJson);

						////////////////////
						// entiteSolrNomCanonique //
						////////////////////
						String entiteSolrNomCanonique = null;
						String entiteSolrNomSimple = null;
						String entiteSuffixeType = null;
						if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBoolean)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueBoolean;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_boolean";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate, VAL_nomCanoniqueDate, VAL_nomCanoniqueZonedDateTime)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueDate;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_date";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLong)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueLong;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_long";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBigDecimal)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueDouble;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_double";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueDouble)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueDouble;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_double";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueFloat)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueFloat;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_float";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueInteger)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueInteger;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_int";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList, VAL_nomCanoniqueSet, VAL_nomCanoniqueHashSet)) {
							if(entiteNomCanoniqueGenerique.equals(VAL_nomCanoniqueBoolean)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueBoolean + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueBoolean, ".") + ">";
								entiteSuffixeType = "_booleans";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate, VAL_nomCanoniqueZonedDateTime)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueDate + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueDate, ".") + ">";
								entiteSuffixeType = "_dates";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLong)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueLong + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueLong, ".") + ">";
								entiteSuffixeType = "_longs";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBigDecimal)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueBigDecimal + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueBigDecimal, ".") + ">";
								entiteSuffixeType = "_doubles";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueDouble)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueDouble + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueDouble, ".") + ">";
								entiteSuffixeType = "_doubles";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueFloat)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueFloat + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueFloat, ".") + ">";
								entiteSuffixeType = "_floats";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueInteger)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueInteger + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueInteger, ".") + ">";
								entiteSuffixeType = "_ints";
							}
							else if(classePartsChaine != null && StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueString, classePartsChaine.nomCanonique)) {
								entiteSolrNomCanonique = VAL_nomCanoniqueList + "<" + VAL_nomCanoniqueString + ">";
								entiteSolrNomSimple = "List<" + StringUtils.substringAfterLast(VAL_nomCanoniqueString, ".") + ">";
								entiteSuffixeType = "_strings";
							}
						}
						else if(classePartsChaine != null && StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueString, classePartsChaine.nomCanonique)) {
							entiteSolrNomCanonique = VAL_nomCanoniqueString;
							entiteSolrNomSimple = StringUtils.substringAfterLast(entiteSolrNomCanonique, ".");
							entiteSuffixeType = "_string";
////								if(videDernier)
////									suffixeType += "_videDernier";
						}
						stockerSolr(entiteDoc, "entiteSolrNomCanonique", entiteSolrNomCanonique);
						stockerSolr(entiteDoc, "entiteSolrNomSimple", entiteSolrNomSimple);
						stockerSolr(entiteDoc, "entiteSuffixeType", entiteSuffixeType);

						////////////////////
						// entiteTypeJson //
						////////////////////
						String entiteTypeJson = null;
						String entiteFormatJson = null;
						String entiteListeTypeJson = null;
						if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBoolean)) {
							entiteTypeJson = "boolean";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueDate, VAL_nomCanoniqueZonedDateTime)) {
							entiteTypeJson = "string";
							entiteFormatJson = "date-time";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalDate)) {
							entiteTypeJson = "string";
							entiteFormatJson = "date";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLong)) {
							entiteTypeJson = "number";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBigDecimal)) {
							entiteTypeJson = "number";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueDouble)) {
							entiteTypeJson = "number";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueFloat)) {
							entiteTypeJson = "number";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueInteger)) {
							entiteTypeJson = "number";
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList, VAL_nomCanoniqueSet, VAL_nomCanoniqueHashSet)) {
							if(entiteNomCanoniqueGenerique.equals(VAL_nomCanoniqueBoolean)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "boolean";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueTimestamp, VAL_nomCanoniqueLocalDateTime, VAL_nomCanoniqueLocalDate, VAL_nomCanoniqueZonedDateTime)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueLong)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "number";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueBigDecimal)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "number";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueDouble)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "number";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueFloat)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "number";
							}
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueInteger)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "number";
							}
							else if(classePartsChaine != null && StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueString, classePartsChaine.nomCanonique)) {
								entiteTypeJson = "array";
								entiteListeTypeJson = "string";
							}
							stockerSolr(entiteDoc, "entiteListeTypeJson", entiteListeTypeJson);
						}
						else if(classePartsChaine != null && StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueString, classePartsChaine.nomCanonique)) {
							entiteTypeJson = "string";
						}
						stockerSolr(entiteDoc, "entiteTypeJson", entiteTypeJson);
						if(entiteFormatJson != null)
							stockerSolr(entiteDoc, "entiteFormatJson", entiteFormatJson);
//						
//						if(entiteCleUnique)
//							stockerSolr(entiteDoc, "entiteVarCleUnique", entiteVar);
//						if(entiteClePrimaire)
//							stockerSolr(entiteDoc, "entiteVarClePrimaire", entiteVar);
//						if(entiteSuggere)
//							stockerSolr(entiteDoc, "entiteVarSuggere", entiteVar + "_suggere");
//						if(entiteIncremente)
//							stockerSolr(entiteDoc, "entiteVarIncremente", entiteVar + "_incremente");
//						if(entiteCrypte)
//							stockerSolr(entiteDoc, "entiteVarCrypte", entiteVar + "_crypte");
//						if(entiteIndexe)
//							stockerSolr(entiteDoc, "entiteVarIndexe", entiteVar + "_indexe" + entiteSuffixeType);
//						if(entiteStocke)
//							stockerSolr(entiteDoc, "entiteVarStocke", entiteVar + "_stocke" + entiteSuffixeType);

						if(entiteClePrimaire) {
							classeVarClePrimaire = stockerSolr(classeDoc, "classeVarClePrimaire", langueNom, entiteVar);
						}
						if(entiteCleUnique) {
							classeVarCleUnique = stockerSolr(classeDoc, "classeVarCleUnique", langueNom, entiteVar);
						}
				
						for(String langueNom : autresLangues) {  
							ClasseParts entiteClassePartsLangue = ClasseParts.initClasseParts(this, entiteClasseParts, langueNom);
//							String entiteNomCanoniqueLangue = regex("^(entite)?NomCanonique\\." + langueNom + ":\\s*(.*)", entiteCommentaire, entiteNomCanonique);
//							String entiteNomSimpleLangue = StringUtils.substringAfterLast(entiteNomCanoniqueLangue, ".");
//							String entiteNomEnsembleLangue = StringUtils.substringBeforeLast(entiteNomCanoniqueLangue, ".");
				
							indexerStockerSolr(entiteDoc, "entiteNomCanonique", langueNom, entiteClassePartsLangue.nomCanonique); 
							indexerStockerSolr(entiteDoc, "entiteNomSimple", langueNom, entiteClassePartsLangue.nomSimple); 
							indexerStockerSolr(entiteDoc, "entiteNomCanoniqueComplet", langueNom, entiteClassePartsLangue.nomCanoniqueComplet); 
							indexerStockerSolr(entiteDoc, "entiteNomSimpleComplet", langueNom, entiteClassePartsLangue.nomSimpleComplet); 
							indexerStockerSolr(entiteDoc, "entiteNomCanoniqueGenerique", langueNom, entiteClassePartsLangue.nomCanoniqueGenerique); 
							indexerStockerSolr(entiteDoc, "entiteNomSimpleGenerique", langueNom, entiteClassePartsLangue.nomSimpleGenerique); 

							indexerStockerSolr(entiteDoc, "entiteVarParam", langueNom, entiteVarParam); 

							String entiteVarLangue = regex("^(entite)?Var\\." + langueNom + ": (.*)", methodeCommentaire);
							entiteVarLangue = indexerStockerSolr(entiteDoc, "entiteVar", langueNom, entiteVarLangue == null ? entiteVar : entiteVarLangue);
							indexerStockerListeSolr(classeDoc, "classeEntiteVars", langueNom, entiteVarLangue);
							if(entiteClePrimaire) {
								stockerSolr(classeDoc, "classeVarClePrimaire", langueNom, entiteVarLangue);
							}
							if(entiteCleUnique) {
								stockerSolr(classeDoc, "classeVarCleUnique", langueNom, entiteVarLangue);
							}
	
							String entiteCodeSourceLangue = entiteCodeSource;
							ArrayList<String> remplacerClesLangue = regexListe("^r." + langueNom + "\\s*=\\s*(.*)\\n.*", methodeCommentaire);
							ArrayList<String> remplacerValeursLangue = regexListe("^r." + langueNom + "\\s*=\\s*.*\\n(.*)", methodeCommentaire);
							for(int i = 0; i < remplacerClesLangue.size(); i++) {
								String cle = remplacerClesLangue.get(i);
								String valeur = remplacerValeursLangue.get(i);
								StringUtils.replace(entiteCodeSourceLangue, cle, valeur);
							}
							String entiteStringLangue = regex("^(entite)?String\\." + langueNom + ":(.*)", methodeCommentaire);
							if(entiteStringLangue != null) {
								entiteCodeSourceLangue = "\n\t\tc.o(\"" + StringUtils.replace(StringUtils.replace(entiteStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\");\n\t";
								indexerStockerSolr(entiteDoc, "entiteString", langueNom, entiteStringLangue); 
							}
							stockerSolr(entiteDoc, "entiteCodeSource", langueNom, entiteCodeSourceLangue); 
	
							stockerRegexCommentaires(methodeCommentaire, langueNom, entiteDoc, "entiteCommentaire");
						}

						for(JavaClass methodeExceptionQdox : methodeExceptionsQdox) { 
							String methodeExceptionNomSimpleComplet = StringUtils.substringAfterLast(methodeExceptionQdox.getCanonicalName(), ".");
							ClasseParts methodeExceptionClasseParts = ClasseParts.initClasseParts(this, methodeExceptionQdox.getCanonicalName(), langueNom);
							if(!classeInitLoinExceptions.contains(methodeExceptionClasseParts.nomCanoniqueComplet))
								classeInitLoinExceptions.add(methodeExceptionClasseParts.nomCanoniqueComplet);
							stockerListeSolr(entiteDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionNomSimpleComplet);
							classePartsGenAjouter(methodeExceptionClasseParts);
							for(String langueNom : autresLangues) {  
								ClasseParts methodeExceptionClassePartsLangue = ClasseParts.initClasseParts(this, methodeExceptionQdox.getCanonicalName(), langueNom);
								stockerListeSolr(entiteDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionClassePartsLangue.nomSimpleComplet);
							}
						}

						clientSolrComputate.add(entiteDoc); 
					}
					else {  
						// est Methode. 
						
						SolrInputDocument methodeDoc = classeDocClone.deepCopy();
						indexerStockerSolr(methodeDoc, "methodeVar", langueNom, methodeVar);
						for(Integer methodeParamNum = 1; methodeParamNum <= methodeParamsQdox.size(); methodeParamNum++) {
							JavaParameter methodeParamQdox = methodeParamsQdox.get(methodeParamNum - 1);
							String methodeParamVar = methodeParamQdox.getName();
							stockerListeSolr(methodeDoc, "methodeParamsVar", langueNom, methodeParamVar);
							ClasseParts methodeParamsClassePart = ClasseParts.initClasseParts(this, methodeParamQdox.getJavaClass(), langueNom);
							stockerListeSolr(methodeDoc, "methodeParamsNomCanonique", langueNom, methodeParamsClassePart.nomCanonique);
							stockerListeSolr(methodeDoc, "methodeParamsNomSimpleComplet", langueNom, methodeParamsClassePart.nomSimpleComplet);
							stockerListeSolr(methodeDoc, "methodeParamsArgsVariables", methodeParamQdox.isVarArgs());
							for(String langueNom : autresLangues) { 
								String methodeParamVarLangue = regex("^(methode)?Param" + methodeParamNum + "\\.var\\." + langueNom + ": (.*)", methodeCommentaire);
								if(methodeParamVarLangue == null)
									methodeParamVarLangue = methodeParamVar;
								ClasseParts methodeParamClassePartsLangue = ClasseParts.initClasseParts(this, methodeParamsClassePart, langueNom);

								stockerListeSolr(methodeDoc, "methodeParamsNomCanonique", langueNom, methodeParamClassePartsLangue.nomCanonique);
								stockerListeSolr(methodeDoc, "methodeParamsNomSimpleComplet", langueNom, methodeParamClassePartsLangue.nomSimpleComplet);
								stockerListeSolr(methodeDoc, "methodeParamsVar", langueNom, methodeParamVarLangue);
							}  
						}

						List<JavaTypeVariable<JavaGenericDeclaration>> methodeParamsType = methodeQdox.getTypeParameters();
						for(JavaTypeVariable<JavaGenericDeclaration> methodeParamType : methodeParamsType) {
							String methodeParamTypeNom = methodeParamType.getName();
							stockerListeSolr(methodeDoc, "methodeParamsTypeNom", methodeParamTypeNom);
						}

						for(JavaAnnotation annotation : annotations) {
							ClasseParts methodeAnnotationClasseParts = ClasseParts.initClasseParts(this, annotation.getType(), langueNom);
							stockerListeSolr(methodeDoc, "methodeAnnotationsNomSimpleComplet", langueNom, methodeAnnotationClasseParts.nomSimpleComplet);
							stockerListeSolr(methodeDoc, "methodeAnnotationsBlocCode", langueNom, StringUtils.substringAfter(annotation.toString(), methodeAnnotationClasseParts.nomSimple));
							for(String langueNom : autresLangues) {  
								ClasseParts methodeAnnotationClassePartsLangue = ClasseParts.initClasseParts(this, methodeAnnotationClasseParts, langueNom);
								stockerListeSolr(methodeDoc, "methodeAnnotationsNomSimpleComplet", langueNom, methodeAnnotationClassePartsLangue.nomSimpleComplet);
								stockerListeSolr(methodeDoc, "methodeAnnotationsBlocCode", langueNom, StringUtils.substringAfter(annotation.toString(), methodeAnnotationClasseParts.nomSimple));
							}
						}

						for(JavaClass methodeExceptionQdox : methodeExceptionsQdox) { 
							String methodeExceptionNomSimpleComplet = StringUtils.substringAfterLast(methodeExceptionQdox.getCanonicalName(), ".");
							stockerListeSolr(methodeDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionNomSimpleComplet);
							for(String langueNom : autresLangues) {  
								ClasseParts methodeExceptionClassePartsLangue = ClasseParts.initClasseParts(this, methodeExceptionQdox.getCanonicalName(), langueNom);
								stockerListeSolr(methodeDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionClassePartsLangue.nomSimpleComplet);
							}
						}

						Boolean methodeEstVide = false;
						if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void")) {
							ClasseParts methodeRetourClasseParts = ClasseParts.initClasseParts(this, methodeQdox.getReturns(), langueNom);
							stockerSolr(methodeDoc, "methodeRetourNomSimpleComplet", langueNom, methodeRetourClasseParts.nomSimpleComplet);
							for(String langueNom : autresLangues) { 
								ClasseParts methodeRetourClassePartsLangue = ClasseParts.initClasseParts(this, methodeRetourClasseParts, langueNom);
								stockerSolr(methodeDoc, "methodeRetourNomSimpleComplet", langueNom, methodeRetourClassePartsLangue.nomSimpleComplet);
							}
						}
						else {
							methodeEstVide = true;
						}
						methodeEstVide = indexerStockerSolr(methodeDoc, "methodeEstVide", methodeEstVide);
	
						String methodeCle = classeCheminAbsolu + "." + methodeVar + "(";
						for(int i = 0; i < methodeParamsQdox.size(); i++) {
							JavaParameter paramQdox = methodeParamsQdox.get(i);
							if(i > 0)
								methodeCle += ", ";
							methodeCle += paramQdox.getGenericCanonicalName() + " " + paramQdox.getName();
						}
						methodeCle += ")";  
		
						// Methodes Solr du methode. 
		
						methodeDoc.addField("id", methodeCle);
						indexerStockerSolr(methodeDoc, "partEstMethode", true);
						indexerStockerSolr(methodeDoc, "partNumero", partNumero);

						indexerStockerSolr(methodeDoc, "methodeEstPublic", methodeQdox.isPublic());
						indexerStockerSolr(methodeDoc, "methodeEstProtege", methodeQdox.isProtected());
						indexerStockerSolr(methodeDoc, "methodeEstPrive", methodeQdox.isPrivate());
						indexerStockerSolr(methodeDoc, "methodeEstStatique", methodeQdox.isStatic());
						indexerStockerSolr(methodeDoc, "methodeEstFinale", methodeQdox.isFinal());
						indexerStockerSolr(methodeDoc, "methodeEstAbstrait", methodeQdox.isAbstract());
						indexerStockerSolr(methodeDoc, "methodeEstNatif", methodeQdox.isNative());
						indexerStockerSolr(methodeDoc, "methodeEstTest", methodeEstTest);
						indexerStockerSolr(methodeDoc, "methodeEstSubstitue", methodeEstSubstitue);
						stockerRegexCommentaires(methodeCommentaire, langueNom, methodeDoc, "methodeCommentaire");
	
						String methodeVarLangue = regex("^(methode)?Var\\." + langueNom + ": (.*)", methodeCommentaire);
						methodeVarLangue =  methodeVarLangue == null ? methodeVar : methodeVarLangue;
	
						regexListe("^" + langueNom + ":\\s*([^\n]+)", methodeCommentaire);

						String methodeCodeSource = methodeQdox.getSourceCode();
						String methodeCodeSourceLangue = methodeCodeSource;
//						ArrayList<String> remplacerClesLangue = regexListe("^r." + langueNom + "\\s*=\\s*(.*)\\n.*", methodeCommentaire);
//						ArrayList<String> remplacerValeursLangue = regexListe("^r." + langueNom + "\\s*=\\s*.*\\n(.*)", methodeCommentaire);
//						for(int i = 0; i < remplacerClesLangue.size(); i++) {
//							String regexCle = remplacerClesLangue.get(i);
//							String regexValeur = remplacerValeursLangue.get(i);
//							StringUtils.replace(methodeCodeSourceLangue, regexCle, regexValeur);
//						}
						String methodeString = regex("^(methode)?String\\." + langueNom + ":(.*)", methodeCommentaire);
						if(methodeString != null) {
							methodeCodeSource = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodeString, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
							indexerStockerSolr(methodeDoc, "methodeString", langueNom, methodeString); 
						}
						stockerSolr(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSource);

						for(String langueNom : autresLangues) {  
							methodeVarLangue = regex("^(methode)?Var\\." + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
							methodeVarLangue = indexerStockerSolr(methodeDoc, "methodeVar", langueNom, methodeVarLangue == null ? methodeVar : methodeVarLangue);
							regexListe("^" + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
							methodeCodeSourceLangue = regexRemplacerTout(methodeCommentaire, methodeCodeSource, langueNom);
							String methodeStringLangue = regex("^(methode)?String\\." + langueNom + ":(.*)", methodeCommentaire);
							if(methodeStringLangue != null) {
								methodeCodeSourceLangue = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodeStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
								indexerStockerSolr(methodeDoc, "methodeString", langueNom, methodeStringLangue); 
							}
							stockerSolr(methodeDoc, "methodeCodeSource", langueNom, methodeCodeSourceLangue);
							stockerRegexCommentaires(methodeCommentaire, langueNom, methodeDoc, "methodeCommentaire");
						} 
	
						clientSolrComputate.add(methodeDoc); 
					}
				}
			}
		}

		if(classeVarClePrimaire == null && classeSuperDoc != null) {
			classeVarClePrimaire = (String)classeSuperDoc.get("classeVarClePrimaire_" + langueNom + "_stored_string");
			if(classeVarClePrimaire != null) {
				stockerSolr(classeDoc, "classeVarClePrimaire", langueNom, classeVarClePrimaire);
				for(String langueNom : autresLangues) {  
					String classeVarClePrimaireLangue = (String)classeSuperDoc.get("classeVarClePrimaire_" + langueNom + "_stored_string");
					if(classeVarClePrimaireLangue != null) {
						stockerSolr(classeDoc, "classeVarClePrimaire", langueNom, classeVarClePrimaireLangue);
					}
				}
			}
		}
		if(classeVarCleUnique == null && classeSuperDoc != null) {
			classeVarCleUnique = (String)classeSuperDoc.get("classeVarCleUnique_" + langueNom + "_stored_string");
			if(classeVarCleUnique != null) {
				stockerSolr(classeDoc, "classeVarCleUnique", langueNom, classeVarCleUnique);
				for(String langueNom : autresLangues) {  
					String classeVarCleUniqueLangue = (String)classeSuperDoc.get("classeVarCleUnique_" + langueNom + "_stored_string");
					if(classeVarCleUniqueLangue != null) {
						stockerSolr(classeDoc, "classeVarCleUnique", langueNom, classeVarCleUniqueLangue);
					}
				}
			}
		}

		for(String classeInitLoinException : classeInitLoinExceptions) {
			indexerListeSolr(classeDoc, "classeInitLoinExceptions", classeInitLoinException); 
			stockerListeSolr(classeDoc, "classeInitLoinExceptions", classeInitLoinException); 
		}

		indexerStockerSolr(classeDoc, "classeMotsClesTrouves", classeMotsClesTrouves); 
		for(String classeMotCleValeur : classeMotsCles) {
			indexerListeSolr(classeDoc, "classeMotsCles", classeMotCleValeur); 
			stockerListeSolr(classeDoc, "classeMotsCles", classeMotCleValeur); 
		}
		
		ClasseParts classePartsCouverture = classePartsCouverture(nomEnsembleDomaine);
		classePartsGenAjouter(classePartsCouverture);

		if(!classeApiMethodes.contains("Recherche") && classeMotsClesTrouves && (classeMotsCles.contains("Recherche.requete") || classeMotsCles.contains("Recherche.reponse")))
			classeApiMethodes.add("Recherche");
		if(!classeApiMethodes.contains("POST") && classeMotsClesTrouves && (classeMotsCles.contains("POST.requete") || classeMotsCles.contains("POST.reponse")))
			classeApiMethodes.add("POST");
		if(!classeApiMethodes.contains("PATCH") && classeMotsClesTrouves && (classeMotsCles.contains("PATCH.requete") || classeMotsCles.contains("PATCH.reponse")))
			classeApiMethodes.add("PATCH");
		if(!classeApiMethodes.contains("GET") && classeMotsClesTrouves && (classeMotsCles.contains("GET.requete") || classeMotsCles.contains("GET.reponse")))
			classeApiMethodes.add("GET");
		if(!classeApiMethodes.contains("PUT") && classeMotsClesTrouves && (classeMotsCles.contains("PUT.requete") || classeMotsCles.contains("PUT.reponse")))
			classeApiMethodes.add("PUT");
		if(!classeApiMethodes.contains("DELETE") && classeMotsClesTrouves && (classeMotsCles.contains("DELETE.requete") || classeMotsCles.contains("DELETE.reponse")))
			classeApiMethodes.add("DELETE");

		if(classeApi) {
			String classeApiUri = indexerStockerSolrRegex(classeDoc, langueNom, "classeApiUri", "ApiUri", classeCommentaire);
			String classeApiTag = indexerStockerSolrRegex(classeDoc, langueNom, "classeApiTag", "ApiTag", classeCommentaire);

			for(String classeApiMethode : classeApiMethodes) {
				indexerStockerListeSolr(classeDoc, "classeApiMethodes", classeApiMethode); 
//				if(classeMotsClesTrouves && (classeMotsCles.contains(classeApiMethode + ".request") || classeMotsCles.contains(classeApiMethode + ".response"))) {
				String classeApiUriMethode = regexLangue(langueNom, "(classe)?ApiUri" + classeApiMethode, classeCommentaire);

				if(classeApiMethode.contains("Recherche"))
					indexerStockerSolr(classeDoc, "classeApiMethode" + classeApiMethode, regex("^(classe)?ApiMethode" + classeApiMethode + ":\\s*(.*)", classeCommentaire, "GET"));
				else if(classeApiMethode.contains("GET"))
					indexerStockerSolr(classeDoc, "classeApiMethode" + classeApiMethode, regex("^(classe)?ApiMethode" + classeApiMethode + ":\\s*(.*)", classeCommentaire, "GET"));
				else if(classeApiMethode.contains("POST"))
					indexerStockerSolr(classeDoc, "classeApiMethode" + classeApiMethode, regex("^(classe)?ApiMethode" + classeApiMethode + ":\\s*(.*)", classeCommentaire, "POST"));
				else if(classeApiMethode.contains("PUT"))
					indexerStockerSolr(classeDoc, "classeApiMethode" + classeApiMethode, regex("^(classe)?ApiMethode" + classeApiMethode + ":\\s*(.*)", classeCommentaire, "PUT"));
				else if(classeApiMethode.contains("PATCH"))
					indexerStockerSolr(classeDoc, "classeApiMethode" + classeApiMethode, regex("^(classe)?ApiMethode" + classeApiMethode + ":\\s*(.*)", classeCommentaire, "PATCH"));
				else if(classeApiMethode.contains("DELETE"))
					indexerStockerSolr(classeDoc, "classeApiMethode" + classeApiMethode, regex("^(classe)?ApiMethode" + classeApiMethode + ":\\s*(.*)", classeCommentaire, "DELETE"));
				else
					indexerStockerSolr(classeDoc, "classeApiMethode" + classeApiMethode, regex("^(classe)?ApiMethode" + classeApiMethode + ":\\s*(.*)", classeCommentaire, classeApiMethode));

				indexerStockerSolrRegex(classeDoc, langueNom, "classeApiOperationId" + classeApiMethode, "ApiOperationId" + classeApiMethode, classeCommentaire, StringUtils.lowerCase(classeApiMethode) + classeNomSimple);
				indexerStockerSolrRegex(classeDoc, langueNom, "classeApiOperationId" + classeApiMethode + "Requete", "ApiOperationId" + classeApiMethode + "Requete", classeCommentaire, classeApiMethode + classeNomSimple + "Requete");
				indexerStockerSolrRegex(classeDoc, langueNom, "classeApiOperationId" + classeApiMethode + "Reponse", "ApiOperationId" + classeApiMethode + "Reponse", classeCommentaire, classeApiMethode + classeNomSimple + "Reponse");
				indexerStockerSolrRegex(classeDoc, langueNom, "classeApiDescription" + classeApiMethode, "ApiDescription" + classeApiMethode, classeCommentaire, regexLangue(langueNom, "(classe)?Description" + classeApiMethode + "", classeCommentaire));

				if(classeEtendBase && classeSuperDoc != null) {
					indexerStockerSolr(classeDoc, "classeSuperApiOperationId" + classeApiMethode, langueNom, (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string"));
					indexerStockerSolr(classeDoc, "classeSuperApiOperationId" + classeApiMethode + "Requete", langueNom, (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "Requete" + "_" + langueNom + "_stored_string"));
					indexerStockerSolr(classeDoc, "classeSuperApiOperationId" + classeApiMethode + "Reponse", langueNom, (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "Reponse" + "_" + langueNom + "_stored_string"));
				}

				String classePageNomSimpleMethode = regex("^(classe)?Page" + classeApiMethode + ":\\s*(.*)", classeCommentaire);
				String classePageSuperNomSimpleMethode = regex("^(classe)?PageSuper" + classeApiMethode + ":\\s*(.*)", classeCommentaire);
				String classeApiTypeMedia200Methode = regex("^(classe)?ApiTypeMedia200" + classeApiMethode + ":\\s*(.*)", classeCommentaire, classePageNomSimpleMethode == null ? "application/json" : "text/html");
				String classeApiMotCleMethode = regexLangue(langueNom, "(classe)?ApiMotCle" + classeApiMethode, classeCommentaire);
				if(StringUtils.contains(classeApiMethode, "POST")
						|| StringUtils.contains(classeApiMethode, "Recherche")
						|| StringUtils.contains(classeApiMethode, "PATCH")
						) {
					if(StringUtils.isBlank(classeApiMotCleMethode))
						classeApiMotCleMethode = StringUtils.substringAfterLast(classeApiUriMethode, "/");
					if(StringUtils.isBlank(classeApiUriMethode))
						classeApiUriMethode = classeApiUri;
				}
				else {
					if(StringUtils.isBlank(classeApiMotCleMethode))
						classeApiMotCleMethode = StringUtils.substringAfterLast(StringUtils.substringBeforeLast(classeApiUriMethode, "/"), "/");
					if(StringUtils.isBlank(classeApiUriMethode))
						classeApiUriMethode = classeApiUri + "/{pk}";
				}
				indexerStockerSolr(classeDoc, "classeApiTypeMedia200" + classeApiMethode, classeApiTypeMedia200Methode);
				indexerStockerSolr(classeDoc, "classeApiMotCle" + classeApiMethode, langueNom, classeApiMotCleMethode);
				indexerStockerSolr(classeDoc, "classeApiUri" + classeApiMethode, langueNom, classeApiUriMethode);
				if(classePageNomSimpleMethode != null) {
					{
						SolrQuery recherchePage = new SolrQuery();   
						recherchePage.setQuery("*:*");
						recherchePage.setRows(1);
						recherchePage.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classePageNomSimpleMethode));
						recherchePage.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
						recherchePage.addFilterQuery("partEstClasse_indexed_boolean:true");
						QueryResponse reponseRecherchePage = clientSolrComputate.query(recherchePage);
						SolrDocumentList listeRecherchePage = reponseRecherchePage.getResults();
	
						if(listeRecherchePage.size() > 0) {
							SolrDocument docEntite = listeRecherchePage.get(0);
							String classePageNomCanoniqueMethode = (String)docEntite.get("classeNomCanonique_frFR_stored_string");
	//							String classePageNomSimpleMethode = (String)docEntite.get("classeNomSimple_frFR_stored_string");
							indexerStockerSolr(classeDoc, "classePageNomCanonique" + classeApiMethode, langueNom, classePageNomCanoniqueMethode);
							indexerStockerSolr(classeDoc, "classePageNomSimple" + classeApiMethode, langueNom, classePageNomSimpleMethode);
							classePartsGenApiAjouter(ClasseParts.initClasseParts(this, classePageNomCanoniqueMethode, langueNom));
						}
						else {
							String classePageNomCanoniqueMethode = classeNomCanonique + "Page";
							indexerStockerSolr(classeDoc, "classePageNomCanonique" + classeApiMethode, langueNom, classePageNomCanoniqueMethode);
							indexerStockerSolr(classeDoc, "classePageNomSimple" + classeApiMethode, langueNom, classePageNomSimpleMethode);
							classePartsGenApiAjouter(ClasseParts.initClasseParts(this, classePageNomCanoniqueMethode, langueNom));
						}
					}
					if(classePageSuperNomSimpleMethode != null) {
						SolrQuery recherchePage = new SolrQuery();   
						recherchePage.setQuery("*:*");
						recherchePage.setRows(1);
						recherchePage.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classePageSuperNomSimpleMethode));
						recherchePage.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
						recherchePage.addFilterQuery("partEstClasse_indexed_boolean:true");
						QueryResponse reponseRecherchePage = clientSolrComputate.query(recherchePage);
						SolrDocumentList listeRecherchePage = reponseRecherchePage.getResults();
	
						if(listeRecherchePage.size() > 0) {
							SolrDocument docEntite = listeRecherchePage.get(0);
							String classePageSuperNomCanoniqueMethode = (String)docEntite.get("classeNomCanonique_frFR_stored_string");
							indexerStockerSolr(classeDoc, "classePageSuperNomCanonique" + classeApiMethode, langueNom, classePageSuperNomCanoniqueMethode);
							indexerStockerSolr(classeDoc, "classePageSuperNomSimple" + classeApiMethode, langueNom, classePageSuperNomSimpleMethode);
							classePartsGenPageAjouter(ClasseParts.initClasseParts(this, classePageSuperNomCanoniqueMethode, langueNom));
						}
					}
		
					String classePageCheminGen = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "GenPage.java");
					String classePageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "Page.java");
					String classePageCheminCss = concat(cheminSrcMainResources, "/webroot/css/", classeNomSimple, "Page.css");
					String classePageCheminJs = concat(cheminSrcMainResources, "/webroot/js/", classeNomSimple, "Page.js");
		
					indexerStockerSolr(classeDoc, "classePageCheminGen" + classeApiMethode, langueNom, classePageCheminGen); 
					indexerStockerSolr(classeDoc, "classePageChemin" + classeApiMethode, langueNom, classePageChemin); 
					indexerStockerSolr(classeDoc, "classePageCheminCss" + classeApiMethode, langueNom, classePageCheminCss); 
					indexerStockerSolr(classeDoc, "classePageCheminJs" + classeApiMethode, langueNom, classePageCheminJs); 
					classePage = true;
				}
//				}
			}
			for(String langueNom : autresLangues) {  
				String classeApiUriLangue = indexerStockerSolrRegex(classeDoc, langueNom, "classeApiUri", "ApiUri", classeCommentaire);
				String classeApiTagLangue = indexerStockerSolrRegex(classeDoc, langueNom, "classeApiTag", "ApiTag", classeCommentaire);

				for(String classeApiMethode : classeApiMethodes) {
	//				if(classeMotsClesTrouves && (classeMotsCles.contains(classeApiMethode + ".request") || classeMotsCles.contains(classeApiMethode + ".response"))) {
						String classeApiUriMethodeLangue = regexLangue(langueNom, "apiUri" + classeApiMethode, classeCommentaire);
						indexerStockerSolr(classeDoc, "classeApiMethode" + classeApiMethode, regex("^(classe)?ApiMethode" + classeApiMethode + ":\\s*(.*)", classeCommentaire, classeApiMethode));
						indexerStockerSolrRegex(classeDoc, langueNom, "classeApiOperationId" + classeApiMethode, "ApiOperationId" + classeApiMethode, classeCommentaire, StringUtils.lowerCase(classeApiMethode) + classeNomSimple);
						indexerStockerSolrRegex(classeDoc, langueNom, "classeApiOperationId" + classeApiMethode + "Requete", "ApiOperationId" + classeApiMethode + "Requete", classeCommentaire, classeApiMethode + classeNomSimple + "Requete");
						indexerStockerSolrRegex(classeDoc, langueNom, "classeApiOperationId" + classeApiMethode + "Reponse", "ApiOperationId" + classeApiMethode + "Reponse", classeCommentaire, classeApiMethode + classeNomSimple + "Reponse");
						indexerStockerSolrRegex(classeDoc, langueNom, "classeApiDescription" + classeApiMethode, "ApiDescription" + classeApiMethode, classeCommentaire, regexLangue(langueNom, "(classe)?Description" + classeApiMethode + "", classeCommentaire));

						if(classeEtendBase) {
							indexerStockerSolr(classeDoc, "classeSuperApiOperationId" + classeApiMethode, langueNom, (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string"));
							indexerStockerSolr(classeDoc, "classeSuperApiOperationId" + classeApiMethode + "Requete", langueNom, (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "Requete" + "_" + langueNom + "_stored_string"));
							indexerStockerSolr(classeDoc, "classeSuperApiOperationId" + classeApiMethode + "Reponse", langueNom, (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "Reponse" + "_" + langueNom + "_stored_string"));
						}

						String classeApiMotCleMethodeLangue = regexLangue(langueNom, "(classe)?ApiMotCle" + classeApiMethode, classeCommentaire);
						if(StringUtils.contains(classeApiMethode, "POST")
								|| StringUtils.contains(classeApiMethode, "Recherche")
								|| StringUtils.contains(classeApiMethode, "PATCH")
								) {
							if(StringUtils.isBlank(classeApiMotCleMethodeLangue))
								classeApiMotCleMethodeLangue = StringUtils.substringAfterLast(classeApiUriMethodeLangue, "/");
							if(StringUtils.isBlank(classeApiUriMethodeLangue))
								classeApiUriMethodeLangue = classeApiUriLangue;
						}
						else {
							if(StringUtils.isBlank(classeApiMotCleMethodeLangue))
								classeApiMotCleMethodeLangue = StringUtils.substringAfterLast(StringUtils.substringBeforeLast(classeApiUriMethodeLangue, "/"), "/");
							if(StringUtils.isBlank(classeApiUriMethodeLangue))
								classeApiUriMethodeLangue = classeApiUriLangue + "/{pk}";
						}
						indexerStockerSolr(classeDoc, "classeApiMotCle" + classeApiMethode, langueNom, classeApiMotCleMethodeLangue);
						indexerStockerSolr(classeDoc, "classeApiUri" + classeApiMethode, langueNom, classeApiUriMethodeLangue);
	//				}
				}
			}
		}

		if(classePage) {
			classePartsGenPageAjouter(classePartsConfigSite);
			classePartsGenPageAjouter(classePartsRequeteSite);
			classePartsGenPageAjouter(classePartsSiteContexte);
			classePartsGenPageAjouter(classePartsUtilisateurSite);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.io.IOException", langueNom));
//			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "javax.servlet.http.HttpServlet", langueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.HttpServerRequest", langueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.HttpServerResponse", langueNom));
			classePartsGenPageAjouter(classePartsListeRecherche);
			classePartsGenPageAjouter(classePartsCouverture);
			classePartsGenPageAjouter(classePartsMiseEnPage);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, LocalDateTime.class.getCanonicalName(), langueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, LocalDate.class.getCanonicalName(), langueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, ZonedDateTime.class.getCanonicalName(), langueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, DateTimeFormatter.class.getCanonicalName(), langueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Locale.class.getCanonicalName(), langueNom));
		}

		for(ClasseParts classePartGenPage : classePartsGenPage.values()) {
			indexerStockerListeSolr(classeDoc, "classeImportationsGenPage", langueNom, classePartGenPage.nomCanonique);
			for(String langueNom : autresLangues) {  
				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGenPage, langueNom);
				indexerStockerListeSolr(classeDoc, "classeImportationsGenPage", langueNom, classeImportationClassePartsLangue.nomCanonique);
			}
		}

		Boolean classeContexte = indexerStockerSolr(classeDoc, "classeContexte", regexTrouve("^(classe)?Contexte: \\s*(true)$", classeCommentaire) || classePage);

		if(classeContexte) {

			contexteCouleur = regex("^(contexte)?Couleur:\\s*(.*)", classeCommentaire);
			if(contexteCouleur != null)
				indexerStockerSolr(classeDoc, "contexteCouleur", contexteCouleur); 

			contexteIconeGroupe = regex("^(contexte)?IconeGroupe:\\s*(.*)", classeCommentaire);
			if(contexteIconeGroupe != null)
				indexerStockerSolr(classeDoc, "contexteIconeGroupe", contexteIconeGroupe); 

			contexteIconeNom = regex("^(contexte)?IconeNom:\\s*(.*)", classeCommentaire);
			if(contexteIconeNom != null)
				indexerStockerSolr(classeDoc, "contexteIconeNom", contexteIconeNom); 

			contexteUnNom = regexLangue(langueNom, "(contexte)?UnNomMinuscule", classeCommentaire);
			if(contexteUnNom != null) {
				indexerStockerSolr(classeDoc, "contexteUnNom", langueNom, contexteUnNom); 
				contexteNomSingulier = indexerStockerSolr(classeDoc, "contexteNomSingulier", langueNom, regexLangue(langueNom, "(contexte)?NomSingulier", classeCommentaire, StringUtils.substringAfter(contexteUnNom, " ")));
				contexteNomPluriel = indexerStockerSolr(classeDoc, "contexteNomPluriel", langueNom, regexLangue(langueNom, "(contexte)?NomPluriel", classeCommentaire, contexteNomSingulier + "s"));
				contexteNomVar = indexerStockerSolr(classeDoc, "contexteNomVar", langueNom, regexLangue(langueNom, "(contexte)?NomVar", classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contexteNomSingulier, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
				contexteLesNoms = indexerStockerSolr(classeDoc, "contexteLesNoms", langueNom, regexLangue(langueNom, "(contexte)?LesNom", classeCommentaire, CONTEXTE_LesPluriel + contexteNomPluriel));

				contexteAdjectif = regexLangue(langueNom, "(contexte)?Adjectif", classeCommentaire);
				if(contexteAdjectif != null) {
					contexteAdjectifPluriel = indexerStockerSolr(classeDoc, "contexteAdjectifPluriel", langueNom, regexLangue(langueNom, "(contexte)?AdjectifPluriel", classeCommentaire, contexteAdjectif + CONTEXTE_AdjectifPluriel));
					contexteAdjectifVar = indexerStockerSolr(classeDoc, "contexteAdjectifVar", langueNom, regexLangue(langueNom, "(contexte)?AdjectifVar", classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contexteAdjectif, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
					if(CONTEXTE_AdjectifAvant) {
						contexteNomAdjectifSingulier = indexerStockerSolr(classeDoc, "contexteNomAdjectifSingulier", langueNom, regexLangue(langueNom, "(contexte)?NomAdjectifSingulier", classeCommentaire, contexteAdjectif + " " + contexteNomSingulier));
						contexteNomAdjectifPluriel = indexerStockerSolr(classeDoc, "contexteNomAdjectifPluriel", langueNom, regexLangue(langueNom, "(contexte)?NomAdjectifPluriel", classeCommentaire, contexteAdjectifPluriel + " " + contexteNomPluriel));
					}
					else {
						contexteNomAdjectifSingulier = indexerStockerSolr(classeDoc, "contexteNomAdjectifSingulier", langueNom, regexLangue(langueNom, "(contexte)?NomAdjectifSingulier", classeCommentaire, contexteNomSingulier + " " + contexteAdjectif));
						contexteNomAdjectifPluriel = indexerStockerSolr(classeDoc, "contexteNomSingulierPluriel", langueNom, regexLangue(langueNom, "(contexte)?NomSingulierPluriel", classeCommentaire, contexteNomPluriel + " " + contexteAdjectifPluriel));
					}

				}

				if(contexteUnNom.startsWith(CONTEXTE_UneFeminin)) {
					contexteCe = indexerStockerSolr(classeDoc, "contexteCe", langueNom, regexLangue(langueNom, "(contexte)?Ce", classeCommentaire, CONTEXTE_CetteFemininConsonne));
					contexteUn = indexerStockerSolr(classeDoc, "contexteUn", langueNom, regexLangue(langueNom, "(contexte)?Un", classeCommentaire, CONTEXTE_UneFeminin));
					contexteCree = indexerStockerSolr(classeDoc, "contexteCree", langueNom, regexLangue(langueNom, "(contexte)?Cree", classeCommentaire, CONTEXTE_CreeeFeminin));
					contexteModifie = indexerStockerSolr(classeDoc, "contexteModifie", langueNom, regexLangue(langueNom, "(contexte)?Modifie", classeCommentaire, CONTEXTE_ModifieeFeminin));
					contexteNomActuel = indexerStockerSolr(classeDoc, "contexteNomActuel", langueNom, regexLangue(langueNom, "(contexte)?NomActuel", classeCommentaire, CONTEXTE_ActuelleFemininAvant + contexteNomSingulier + CONTEXTE_ActuelleFemininApres));
//					contexteTous = indexerStockerSolr(classeDoc, "contexteTous", langueNom, regexLangue(langueNom, "(contexte)?Tous", classeCommentaire, CONTEXTE_ToutesFemininPluriel));
					contexteTousNom = indexerStockerSolr(classeDoc, "contexteTousNom", langueNom, regexLangue(langueNom, "(contexte)?TousNom", classeCommentaire, CONTEXTE_LesPluriel + contexteNomPluriel));
					contexteAucunNomTrouve = indexerStockerSolr(classeDoc, "contexteAucunNomTrouve", langueNom, regexLangue(langueNom, "(contexte)?AucunNomTrouve", classeCommentaire, CONTEXTE_AucuneTrouveFemininAvant + contexteNomSingulier + CONTEXTE_AucuneTrouveFemininApres));
					if(contexteAdjectif != null) {
						if(CONTEXTE_AdjectifAvant)
							contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteUnNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?UnNomAdjectif", classeCommentaire, CONTEXTE_UneFeminin + " " + contexteAdjectif + " " + contexteNomSingulier));
						else
							contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteUnNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?UnNomAdjectif", classeCommentaire, CONTEXTE_UneFeminin + " " + contexteNomSingulier + " " + contexteAdjectif));
					}

					String suffixe = StringUtils.substringAfter(contexteUnNom, " ");
					String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
					if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
						contexteCeNom = indexerStockerSolr(classeDoc, "contexteCeNom", langueNom, regexLangue(langueNom, "(contexte)?CeNom", classeCommentaire, CONTEXTE_CetteFemininVoyelle + suffixe));
						contexteLeNom = indexerStockerSolr(classeDoc, "contexteLeNom", langueNom, regexLangue(langueNom, "(contexte)?LeNom", classeCommentaire, CONTEXTE_LFemininVoyelle + suffixe));
						contexteDeNom = indexerStockerSolr(classeDoc, "contexteDeNom", langueNom, regexLangue(langueNom, "(contexte)?DeNom", classeCommentaire, CONTEXTE_DVoyelle + suffixe));
						if(contexteAdjectif != null) {
							if(CONTEXTE_AdjectifAvant)
								contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteLeNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_LFemininVoyelle + " " + contexteAdjectif + " " + contexteNomSingulier));
							else
								contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteLeNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_LFemininVoyelle + " " + contexteNomSingulier + " " + contexteAdjectif));
						}
					}
					else {
						contexteCeNom = indexerStockerSolr(classeDoc, "contexteCeNom", langueNom, regexLangue(langueNom, "(contexte)?CeNom", classeCommentaire, CONTEXTE_CetteFemininConsonne + suffixe));
						contexteLeNom = indexerStockerSolr(classeDoc, "contexteLeNom", langueNom, regexLangue(langueNom, "(contexte)?LeNom", classeCommentaire, CONTEXTE_LaFemininConsonne + suffixe));
						contexteDeNom = indexerStockerSolr(classeDoc, "contexteDeNom", langueNom, regexLangue(langueNom, "(contexte)?DeNom", classeCommentaire, CONTEXTE_DeConsonne + suffixe));
						if(contexteAdjectif != null) {
							if(CONTEXTE_AdjectifAvant)
								contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteLeNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_LaFemininConsonne + " " + contexteAdjectif + " " + contexteNomSingulier));
							else
								contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteLeNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_LaFemininConsonne + " " + contexteNomSingulier + " " + contexteAdjectif));
						}
					}
				}
				else if(contexteUnNom.startsWith(CONTEXTE_UnMasculin)) {
					contexteCe = indexerStockerSolr(classeDoc, "contexteCe", langueNom, regexLangue(langueNom, "(contexte)?Ce", classeCommentaire, CONTEXTE_CeMasculinConsonne));
					contexteUn = indexerStockerSolr(classeDoc, "contexteUn", langueNom, regexLangue(langueNom, "(contexte)?Un", classeCommentaire, CONTEXTE_UnMasculin));
					contexteCree = indexerStockerSolr(classeDoc, "contexteCree", langueNom, regexLangue(langueNom, "(contexte)?Cree", classeCommentaire, CONTEXTE_CreeMasculin));
					contexteModifie = indexerStockerSolr(classeDoc, "contexteModifie", langueNom, regexLangue(langueNom, "(contexte)?Modifie", classeCommentaire, CONTEXTE_ModifieMasculin));
					contexteNomActuel = indexerStockerSolr(classeDoc, "contexteNomActuel", langueNom, regexLangue(langueNom, "(contexte)?NomActuel", classeCommentaire, CONTEXTE_ActuelMasculinAvant + contexteNomSingulier + CONTEXTE_ActuelMasculinApres));
//					contexteTous = indexerStockerSolr(classeDoc, "contexteTous", langueNom, regexLangue(langueNom, "(contexte)?Tous", classeCommentaire, CONTEXTE_TousMasculinPluriel));
					contexteTousNom = indexerStockerSolr(classeDoc, "contexteTousNom", langueNom, regexLangue(langueNom, "(contexte)?TousNom", classeCommentaire, CONTEXTE_TousMasculinPluriel + contexteNomPluriel));
					contexteAucunNomTrouve = indexerStockerSolr(classeDoc, "contexteAucunNomTrouve", langueNom, regexLangue(langueNom, "(contexte)?AucunNomTrouve", classeCommentaire, CONTEXTE_AucunTrouveMasculinAvant + contexteNomSingulier + CONTEXTE_AucunTrouveMasculinApres));
					if(contexteAdjectif != null) {
						if(CONTEXTE_AdjectifAvant)
							contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteUnNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?UnNomAdjectif", classeCommentaire, CONTEXTE_UnMasculin + " " + contexteAdjectif + " " + contexteNomSingulier));
						else
							contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteUnNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?UnNomAdjectif", classeCommentaire, CONTEXTE_UnMasculin + " " + contexteNomSingulier + " " + contexteAdjectif));
					}

					String suffixe = StringUtils.substringAfter(contexteUnNom, " ");
					String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
					if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
						contexteCeNom = indexerStockerSolr(classeDoc, "contexteCeNom", langueNom, regexLangue(langueNom, "(contexte)?CeNom", classeCommentaire, CONTEXTE_CetMasculinVoyelle + suffixe));
						contexteLeNom = indexerStockerSolr(classeDoc, "contexteLeNom", langueNom, regexLangue(langueNom, "(contexte)?LeNom", classeCommentaire, CONTEXTE_LMasculinVoyelle + suffixe));
						contexteDeNom = indexerStockerSolr(classeDoc, "contexteDeNom", langueNom, regexLangue(langueNom, "(contexte)?DeNom", classeCommentaire, CONTEXTE_DVoyelle + suffixe));
						if(contexteAdjectif != null) {
							if(CONTEXTE_AdjectifAvant)
								contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteLeNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_LMasculinVoyelle + " " + contexteAdjectif + " " + contexteNomSingulier));
							else
								contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteLeNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_LMasculinVoyelle + " " + contexteNomSingulier + " " + contexteAdjectif));
						}
					}
					else {
						contexteCeNom = indexerStockerSolr(classeDoc, "contexteCeNom", langueNom, regexLangue(langueNom, "(contexte)?CeNom", classeCommentaire, CONTEXTE_CeMasculinConsonne + suffixe));
						contexteLeNom = indexerStockerSolr(classeDoc, "contexteLeNom", langueNom, regexLangue(langueNom, "(contexte)?LeNom", classeCommentaire, CONTEXTE_LeMasculinConsonne + suffixe));
						contexteDeNom = indexerStockerSolr(classeDoc, "contexteDeNom", langueNom, regexLangue(langueNom, "(contexte)?DeNom", classeCommentaire, CONTEXTE_DeConsonne + suffixe));
						if(contexteAdjectif != null) {
							if(CONTEXTE_AdjectifAvant)
								contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteLeNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_LeMasculinConsonne + " " + contexteAdjectif + " " + contexteNomSingulier));
							else
								contexteUnNomAdjectif = indexerStockerSolr(classeDoc, "contexteLeNomAdjectif", langueNom, regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_LeMasculinConsonne + " " + contexteNomSingulier + " " + contexteAdjectif));
						}
					}
				}
				indexerStockerSolr(classeDoc, "contexteCeMinuscule", langueNom, contexteCe); 
			}

			contexteTitre = regexLangue(langueNom, "(contexte)?Titre", classeCommentaire);
			if(contexteTitre != null)
				indexerStockerSolr(classeDoc, "contexteTitre", langueNom, contexteTitre); 

			contexteH1 = regexLangue(langueNom, "(contexte)?H1", classeCommentaire);
			if(contexteH1 != null)
				indexerStockerSolr(classeDoc, "contexteH1", langueNom, contexteH1); 

			contexteH2 = regexLangue(langueNom, "(contexte)?H2", classeCommentaire);
			if(contexteH2 != null)
				indexerStockerSolr(classeDoc, "contexteH2", langueNom, contexteH2); 

			contexteH3 = regexLangue(langueNom, "(contexte)?H3", classeCommentaire);
			if(contexteH3 != null)
				indexerStockerSolr(classeDoc, "contexteH3", langueNom, contexteH3); 
		}

		Boolean classeIndexe = indexerStockerSolr(classeDoc, "classeIndexe", regexTrouve("^(classe)?Indexe:\\s*(true)$", classeCommentaire) || classeSauvegarde || classeModele || classePage);

		if(classeIndexe) {
			classePartsGenAjouter(classePartsSolrInputDocument);
			classePartsGenAjouter(classePartsSolrClient);
//			classePartsGenAjouter(classePartsTest);
			classePartsGenAjouter(classePartsSiteContexte);
			classePartsGenAjouter(classePartsSolrDocument);
			classePartsGenAjouter(classePartsList);
			classePartsGenAjouter(classePartsArrayList);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", langueNom));
		}

		for(ClasseParts classePartGen : classePartsGen.values()) {
			indexerStockerListeSolr(classeDoc, "classeImportationsGen", langueNom, classePartGen.nomCanonique);
			for(String langueNom : autresLangues) {  
				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGen, langueNom);
				indexerStockerListeSolr(classeDoc, "classeImportationsGen", langueNom, classeImportationClassePartsLangue.nomCanonique);
			}
		}

		for(ClasseParts classePartGenApi : classePartsGenApi.values()) {
			indexerStockerListeSolr(classeDoc, "classeImportationsGenApi", langueNom, classePartGenApi.nomCanonique);
			for(String langueNom : autresLangues) {  
				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGenApi, langueNom);
				indexerStockerListeSolr(classeDoc, "classeImportationsGenApi", langueNom, classeImportationClassePartsLangue.nomCanonique);
			}
		}

		if(classeSuperDoc != null) {
			List<String> classeSuperEntiteVars = (List<String>)classeSuperDoc.get("classeEntiteVars_" + langueNom + "_stored_strings");
			if(classeSuperEntiteVars != null) {
				for(String classeSuperEntiteVar : classeSuperEntiteVars)
					indexerStockerListeSolr(classeDoc, "classeEntiteVars", langueNom, classeSuperEntiteVar);
			}
		}

		indexerStockerSolr(classeDoc, "classePage", classePage);

		clientSolrComputate.add(classeDoc);
		clientSolrComputate.commit();
		String qSupprimer = concat("classeCheminAbsolu_indexed_string", ":\"", classeChemin, "\" AND (modifiee_indexed_date:[* TO ", modifiee.toString(), "-1MILLI] OR (*:* NOT modifiee_indexed_date:*))");
		clientSolrComputate.deleteByQuery(qSupprimer);
		clientSolrComputate.commit(); 
		return classeDoc;
	}
}
