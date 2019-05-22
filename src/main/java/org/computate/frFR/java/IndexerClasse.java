package org.computate.frFR.java; 

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
	 * Var.enUS: classPartsSiteRequest
	 */ 
	ClasseParts classePartsRequeteSite;
	/**
	 * Var.enUS: classPartsChain
	 */
	ClasseParts classePartsChaine;
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
	 * Var.enUS: classPartsPagePart
	 */
	ClasseParts classePartsPagePart;

	/**
	 * Var.enUS: CONTEXT_frFR_AMale
	 */
	String CONTEXTE_frFR_UnMasculin = "un ";
	/**
	 * Var.enUS: CONTEXT_frFR_AFemale
	 */
	String CONTEXTE_frFR_UneFeminin = "une ";
	/**
	 * Var.enUS: CONTEXT_enUS_A
	 */
	String CONTEXTE_enUS_Une = "an ";

	/**
	 * Var.enUS: CONTEXT_frFR_ThisMaleVowel
	 */
	String CONTEXTE_frFR_CetMasculinVoyelle = "cet ";
	/**
	 * Var.enUS: CONTEXT_frFR_ThisFemaleVowel
	 */
	String CONTEXTE_frFR_CetteFemininVoyelle = "cette ";
	/**
	 * Var.enUS: CONTEXT_enUS_ThisVowel
	 */
	String CONTEXTE_enUS_CetteVoyelle = "this ";

	/**
	 * Var.enUS: CONTEXT_frFR_ThisMaleConsonant
	 */
	String CONTEXTE_frFR_CeMasculinConsonne = "ce ";
	/**
	 * Var.enUS: CONTEXT_frFR_ThisFemaleConsonant
	 */
	String CONTEXTE_frFR_CetteFemininConsonne = "cette ";
	/**
	 * Var.enUS: CONTEXT_enUS_ThisConsonant
	 */
	String CONTEXTE_enUS_CetteConsonne = "this ";

	/**
	 * Var.enUS: CONTEXT_frFR_ThesePlural
	 */
	String CONTEXTE_frFR_CesPluriel = "ces ";
	/**
	 * Var.enUS: CONTEXT_enUS_ThesePlural
	 */
	String CONTEXTE_enUS_CesPluriel = "these ";

	/**
	 * Var.enUS: CONTEXT_frFR_CreatedMale
	 */
	String CONTEXTE_frFR_CreeMasculin = "créé ";
	/**
	 * Var.enUS: CONTEXT_frFR_CreatedFemale
	 */
	String CONTEXTE_frFR_CreeeFeminin = "créée ";
	/**
	 * Var.enUS: CONTEXT_enUS_Created
	 */
	String CONTEXTE_enUS_Creee = "created ";

	/**
	 * Var.enUS: CONTEXT_frFR_ModifiedMale
	 */
	String CONTEXTE_frFR_ModifieMasculin = "modifié ";
	/**
	 * Var.enUS: CONTEXT_frFR_ModifiedFemale
	 */
	String CONTEXTE_frFR_ModifieeFeminin = "modifiée ";
	/**
	 * Var.enUS: CONTEXT_enUS_Modified
	 */
	String CONTEXTE_enUS_Modifiee = "modified ";

	/**
	 * Var.enUS: CONTEXT_frFR_TheMaleVowel
	 */
	String CONTEXTE_frFR_LMasculinVoyelle = "l'";
	/**
	 * Var.enUS: CONTEXT_frFR_TheFemaleVowel
	 */
	String CONTEXTE_frFR_LFemininVoyelle = "l'";
	/**
	 * Var.enUS: CONTEXT_enUS_TheVowel
	 */
	String CONTEXTE_enUS_LVoyelle = "the";

	/**
	 * Var.enUS: CONTEXT_frFR_TheMaleConsonant
	 */
	String CONTEXTE_frFR_LeMasculinConsonne = "le ";
	/**
	 * Var.enUS: CONTEXT_frFR_TheFemaleConsonant
	 */
	String CONTEXTE_frFR_LaFemininConsonne = "la ";
	/**
	 * Var.enUS: CONTEXT_enUS_TheConsonant
	 */
	String CONTEXTE_enUS_LaConsonne = "the ";

	/**
	 * Var.enUS: CONTEXT_frFR_ThePlural
	 */
	String CONTEXTE_frFR_LesPluriel = "les ";
	/**
	 * Var.enUS: CONTEXT_enUS_ThePlural
	 */
	String CONTEXTE_enUS_LesPluriel = "the ";

	/**
	 * Var.enUS: CONTEXT_frFR_Search
	 */
	String CONTEXTE_frFR_Rechercher = "rechercher ";
	/**
	 * Var.enUS: CONTEXT_enUS_Search
	 */
	String CONTEXTE_enUS_Rechercher = "search ";

	/**
	 * Var.enUS: CONTEXT_frFR_By
	 */
	String CONTEXTE_frFR_Par = " par ";
	/**
	 * Var.enUS: CONTEXT_enUS_By
	 */
	String CONTEXTE_enUS_Par = " by ";

	/**
	 * Var.enUS: CONTEXT_frFR_CurrentMaleBefore
	 */
	String CONTEXTE_frFR_ActuelMasculinAvant = "";
	/**
	 * Var.enUS: CONTEXT_frFR_CurrentFemaleBefore
	 */
	String CONTEXTE_frFR_ActuelleFemininAvant = "";
	/**
	 * Var.enUS: CONTEXT_enUS_CurrentBefore
	 */
	String CONTEXTE_enUS_ActuelleAvant = "current ";

	/**
	 * Var.enUS: CONTEXT_frFR_CurrentMaleAfter
	 */
	String CONTEXTE_frFR_ActuelMasculinApres = " actuel";
	/**
	 * Var.enUS: CONTEXT_frFR_CurrentFemaleAfter
	 */
	String CONTEXTE_frFR_ActuelleFemininApres = " actuelle";
	/**
	 * Var.enUS: CONTEXT_enUS_CurrentAfter
	 */
	String CONTEXTE_enUS_ActuelleApres = "";

	/**
	 * Var.enUS: CONTEXT_frFR_AllMalePlural
	 */
	String CONTEXTE_frFR_TousMasculinPluriel = "tous les ";
	/**
	 * Var.enUS: CONTEXT_frFR_AllFemalePlural
	 */
	String CONTEXTE_frFR_ToutesFemininPluriel = "toutes les ";
	/**
	 * Var.enUS: CONTEXT_enUS_AllPlural
	 */
	String CONTEXTE_enUS_ToutesPluriel = "all the ";

	/**
	 * Var.enUS: CONTEXT_frFR_NoneFoundMaleBefore
	 */
	String CONTEXTE_frFR_AucunTrouveMasculinAvant = "aucun ";
	/**
	 * Var.enUS: CONTEXT_frFR_NoneFoundFemaleBefore
	 */
	String CONTEXTE_frFR_AucuneTrouveFemininAvant = "aucune ";
	/**
	 * Var.enUS: CONTEXT_enUS_NoneFoundBefore
	 */
	String CONTEXTE_enUS_AucuneTrouveAvant = "no ";

	/**
	 * Var.enUS: CONTEXT_frFR_NoneFoundMaleAfter
	 */
	String CONTEXTE_frFR_AucunTrouveMasculinApres = " trouvé";
	/**
	 * Var.enUS: CONTEXT_frFR_NoneFoundFemaleAfter
	 */
	String CONTEXTE_frFR_AucuneTrouveFemininApres = " trouvée";
	/**
	 * Var.enUS: CONTEXT_enUS_NoneFoundAfter
	 */
	String CONTEXTE_enUS_AucuneTrouveApres = " found";

	/**
	 * Var.enUS: CONTEXT_frFR_OfVowel
	 */
	String CONTEXTE_frFR_DVoyelle = "d'";
	/**
	 * Var.enUS: CONTEXT_enUS_OfVowel
	 */
	String CONTEXTE_enUS_DVoyelle = "of ";

	/**
	 * Var.enUS: CONTEXT_frFR_OfConsonant
	 */
	String CONTEXTE_frFR_DeConsonne = "de ";
	/**
	 * Var.enUS: CONTEXT_enUS_OfConsonant
	 */
	String CONTEXTE_enUS_DeConsonne = "of ";

	/**
	 * Var.enUS: CONTEXT_frFR_AdjectivePlural
	 */
	String CONTEXTE_frFR_AdjectifPluriel = "s";
	/**
	 * Var.enUS: CONTEXT_enUS_AdjectivePlural
	 */
	String CONTEXTE_enUS_AdjectifPluriel = "";

	/**
	 * Var.enUS: CONTEXT_frFR_AdjectiveBefore
	 */
	Boolean CONTEXTE_frFR_AdjectifAvant = false;
	/**
	 * Var.enUS: CONTEXT_enUS_AdjectiveBefore
	 */
	Boolean CONTEXTE_enUS_AdjectifAvant = true;

	/**
	 * Var.enUS: classPartsGenApi
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 */
	protected LinkedHashMap<String, ClasseParts> classePartsGenApi = new LinkedHashMap<String, ClasseParts>();
	/**
	 * Var.enUS: classPartsGenPage
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 */ 
	protected LinkedHashMap<String, ClasseParts> classePartsGenPage = new LinkedHashMap<String, ClasseParts>();

	/**
	 * Var.enUS: contextVideoId
	 */
	private String contexteVideoId;

	/**
	 * Var.enUS: contextDescription
	 */
	private String contexteDescription;

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
	 * Var.enUS: contextSearchAllNameBy
	 */
	private String contexteRechercherTousNomPar;

	/**
	 * Var.enUS: contextSearchAllName
	 */
	private String contexteRechercherTousNom;

	/**
	 * Var.enUS: contextTheNames
	 */
	private String contexteLesNoms;

	/**
	 * Var.enUS: contextNoNameFound
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
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
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
	protected Boolean stockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
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
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
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
	protected String stockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeListSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
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
	protected String stockerListeSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: storeSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
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
	protected List<String> stockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, List<String> valeursChamp) throws Exception {
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
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
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
	protected Boolean indexerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
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
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
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
	protected String indexerSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexListSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
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
	protected String indexerListeSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
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
	protected List<String> indexerSolr(String langueNom, SolrInputDocument doc, String nomChamp, List<String> valeursChamp) throws Exception {
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
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Long indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Long valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_long"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_long"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Double indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Double valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_double"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_double"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Integer indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Integer valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_int"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_int"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
	 * Param4.var.enUS: fieldValue
	 * r: langueNom
	 * r.enUS: languageName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 */
	protected Boolean indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, Boolean valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_boolean"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_boolean"), valeurChamp);
		}
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
	 * Param1.var.enUS: languageName
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
	protected String indexerStockerSolrRegex(String langueNom, SolrInputDocument doc, String nomChamp, String nomChampRegex, String commentaire) throws Exception {
		return indexerStockerSolrRegex(langueNom, doc, nomChamp, nomChampRegex, commentaire, null);
	}
	
	/**
	 * Var.enUS: indexStoreSolrRegex
	 * Param1.var.enUS: languageName
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
	protected String indexerStockerSolrRegex(String langueNom, SolrInputDocument doc, String nomChamp, String nomChampRegex, String commentaire, String valeurDefaut) throws Exception {
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
	 * Var.enUS: indexStoreSolrRegex
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldNameRegex
	 * Param4.var.enUS: comment
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
	protected String indexerStockerSolrRegex(SolrInputDocument doc, String nomChamp, String nomChampRegex, String commentaire) throws Exception {
		return indexerStockerSolrRegex(doc, nomChamp, nomChampRegex, commentaire, null);
	}
	
	/**
	 * Var.enUS: indexStoreSolrRegex
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: fieldNameRegex
	 * Param4.var.enUS: comment
	 * Param5.var.enUS: defaultValue
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
	protected String indexerStockerSolrRegex(SolrInputDocument doc, String nomChamp, String nomChampRegex, String commentaire, String valeurDefaut) throws Exception {
		String valeurChamp = null;
		if(nomChampRegex != null && commentaire != null) {
			Matcher m = Pattern.compile("^" + nomChampRegex + ":\\s*(.*)", Pattern.MULTILINE).matcher(commentaire);
			if(m.find()) {
				valeurChamp = m.group(1);
			}
		}
		if(StringUtils.isBlank(valeurChamp)) {
			valeurChamp = valeurDefaut;
		}
		if(StringUtils.isNotBlank(valeurChamp)) {
			doc.addField(concat(nomChamp, "_stored_string"), valeurChamp);
			doc.addField(concat(nomChamp, "_indexed_string"), valeurChamp);
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
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
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
	protected String indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_string"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_string"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreListSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
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
	protected String indexerStockerListeSolr(String langueNom, SolrInputDocument doc, String nomChamp, String valeurChamp) throws Exception {
		if(langueIndexe || !StringUtils.equals(langueNom, this.langueNom)) {
			doc.addField(concat(nomChamp, "_", langueNom, "_stored_strings"), valeurChamp);
			doc.addField(concat(nomChamp, "_", langueNom, "_indexed_strings"), valeurChamp);
		}
		return valeurChamp;
	}
	
	/**
	 * Var.enUS: indexStoreSolr
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: fieldName
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
	protected List<String> indexerStockerSolr(String langueNom, SolrInputDocument doc, String nomChamp, List<String> valeursChamp) throws Exception {
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
	 * Param3.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classeMotsCles
	 * r.enUS: classKeywords
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
	protected ClasseParts classePartsPourNomSimple(String nomEnsembleDomaine, String nomSimple, String classeLangueNom) throws Exception {
		ClasseParts classeParts = null;
		SolrDocument doc = null;
		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1);
		rechercheSolr.addFilterQuery("classeMotsCles_indexed_strings:" + ClientUtils.escapeQueryChars("classeNomSimple" + nomSimple));
		rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
		rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		SolrDocumentList listeRecherche = reponseRecherche.getResults();
		if(listeRecherche.size() > 0) {
			doc = listeRecherche.get(0);
			String nomCanonique = (String)doc.get("classeNomCanonique_" + classeLangueNom + "_stored_string");
			classeParts = ClasseParts.initClasseParts(this, nomCanonique, classeLangueNom);
		}
		return classeParts;
	}

	/**
	 * Var.enUS: classPartsWrap
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: Couverture
	 * r.enUS: Wrap
	 */
	protected ClasseParts classePartsCouverture(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "Couverture", classeLangueNom);
	}

	/**
	 * Var.enUS: classPartsChain
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: Chaine
	 * r.enUS: Chain
	 */
	protected ClasseParts classePartsChaine(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "Chaine", classeLangueNom);
	}

	/**
	 * Var.enUS: classPartsSiteRequest
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 */
	protected ClasseParts classePartsRequeteSite(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "RequeteSite", classeLangueNom);
	}

	/**
	 * Var.enUS: classPartsSiteContext
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 */
	protected ClasseParts classePartsSiteContexte(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "SiteContexte", classeLangueNom);
	}

	/**
	 * Var.enUS: classPartsSiteConfig
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 */
	protected ClasseParts classePartsConfigSite(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "ConfigSite", classeLangueNom);
	}

	/**
	 * Var.enUS: classPartsSiteUser
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: UtilisateurSite
	 * r.enUS: SiteUser
	 */
	protected ClasseParts classePartsUtilisateurSite(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "UtilisateurSite", classeLangueNom);
	}

	/**
	 * Var.enUS: classPartsCluster
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: Cluster
	 * r.enUS: Cluster
	 */
	protected ClasseParts classePartsCluster(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "Cluster", classeLangueNom);
	}

	/**
	 * Var.enUS: classPartsSearchResult
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ResultatRecherche
	 * r.enUS: SearchResult
	 */
	protected ClasseParts classePartsResultatRecherche(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "ResultatRecherche", classeLangueNom);
	}

	/**
	 * Var.enUS: classPartsAllWriter
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 */
	protected ClasseParts classePartsToutEcrivain(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "ToutEcrivain", classeLangueNom);
	}

	/**
	 * Var.enUS: classPartsSearchList
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 */
	protected ClasseParts classePartsListeRecherche(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "ListeRecherche", classeLangueNom);
	}

	/**
	 * Var.enUS: classPartsPageLayout
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 */
	protected ClasseParts classePartsMiseEnPage(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "MiseEnPage", classeLangueNom);
	}

	/**
	 * Var.enUS: classPartsPagePart
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: classLanguageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: classePartsPourNomSimple
	 * r.enUS: classPartsForSimpleName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 */
	protected ClasseParts classePartsPagePart(String nomEnsembleDomaine, String classeLangueNom) throws Exception {
		return classePartsPourNomSimple(nomEnsembleDomaine, "PagePart", classeLangueNom);
	}

	/**
	 * Var.enUS: storeRegexComments
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: entityVar
	 * Param4.var.enUS: comment
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
	public String stockerRegexCommentaires(String langueNom, SolrInputDocument doc, String varEntite, String commentaire) throws Exception {
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
				stockerSolr(langueNom, doc, varEntite, b.toString());
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

	/**
	 * Var.enUS: indexClass
	 * Param1.var.enUS: classAbsolutePath
	 * Param2.var.enUS: classDoc
	 * Param3.var.enUS: classLanguageName
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: classeEstAbstrait
	 * r.enUS: classIsAbstract
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: classeNomCanoniqueSuperGeneriqueLangue
	 * r: classeSuperEcrireMethodes
	 * r.enUS: classSuperWriteMethods
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
	 * r: classeAutresLangues
	 * r.enUS: classOtherLanguages
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
	 * r: champTraduire
	 * r.enUS: fieldTranslate
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
	 * r: methodeParamsClassePart
	 * r.enUS: methodParamsClassPart
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
	 * r: classeImplementsNomSimpleCompletLangue
	 * r.enUS: classImplementsSimpleNameCompleteLanguage
	 * r: classeImplementsNomSimpleComplet
	 * r.enUS: classImplementsSimpleNameComplete
	 * r: classePageLangueNom
	 * r.enUS: classPageLanguageName
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
	 * r: classeFiltresRecherche
	 * r.enUS: classFiltersSearch
	 * r: classeFiltresTrouvesActuel
	 * r.enUS: classFiltersFoundCurrent
	 * r: classeFiltresTrouves
	 * r.enUS: classFiltersFound
	 * r: classeFiltresVar
	 * r.enUS: classFiltersVar
	 * r: classeFiltresLangue
	 * r.enUS: classFiltersLanguage
	 * r: classeFiltresFiltreValeur
	 * r.enUS: classFiltersValue
	 * r: classeFiltreValeur
	 * r.enUS: classFilterValue
	 * r: classeFiltres
	 * r.enUS: classFilters
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
	 * r: entiteLangue
	 * r.enUS: entityLanguage
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
	 * r: classeImage
	 * r.enUS: classImage
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: classeVarCleUnique
	 * r.enUS: classVarUniqueKey
	 * r: entiteEtendPagePart
	 * r.enUS: entityExtendsPagePart
	 * r: docClasse
	 * r.enUS: docClass
	 * r: docEntite
	 * r.enUS: docEntity
	 * 
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
	 * r: classeSuperEntiteVars
	 * r.enUS: classSuperEntityVars
	 * r: classeMethodeVars
	 * r.enUS: classMethodVars
	 * r: classeSuperMethodeVars
	 * r.enUS: classSuperMethodVars
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
	 * r: ?Ce"
	 * r.enUS: ?This"
	 * r: ?Un"
	 * r.enUS: ?A"
	 * r: _Un)
	 * r.enUS: _A)
	 * r: ?Cree"
	 * r.enUS: ?Created"
	 * r: _Creee)
	 * r.enUS: _Created)
	 * r: ?Modifie"
	 * r.enUS: ?Modified"
	 * r: _Modifiee)
	 * r.enUS: _Modified)
	 * r: ?NomActuel"
	 * r.enUS: ?ActualName"
	 * r: ?TousNom"
	 * r.enUS: ?AllName"
	 * r: ?RechercherTousNomPar"
	 * r.enUS: ?SearchAllNameBy"
	 * r: ?RechercherTousNom"
	 * r.enUS: ?SearchAllName"
	 * r: ?AucunNomTrouve"
	 * r.enUS: ?NoNameFound"
	 * r: ?CeNom"
	 * r.enUS: ?ThisName"
	 * r: ?LeNom"
	 * r.enUS: ?TheName"
	 * r: ?DeNom"
	 * r.enUS: ?OfName"
	 * r: ?Par"
	 * r.enUS: ?By"
	 * r: _Par
	 * r.enUS: _By
	 * r: ?Une"
	 * r.enUS: ?A"
	 * r: _UneFeminin
	 * r.enUS: _AFemale
	 * r: _UnMasculin
	 * r.enUS: _AMale
	 * r: _Une
	 * r.enUS: _A
	 * r: _ActuelleAvant
	 * r.enUS: _CurrentBefore
	 * r: _ActuelleApres
	 * r.enUS: _CurrentAfter
	 * r: _AucuneTrouveAvant
	 * r.enUS: _NoneFoundBefore
	 * r: _AucuneTrouveApres
	 * r.enUS: _NoneFoundAfter
	 * r: _CetteVoyelle
	 * r.enUS: _ThisVowel
	 * r: _LVoyelle
	 * r.enUS: _TheVowel
	 * r: _LaConsonne
	 * r.enUS: _TheConsonant
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
	 * r.enUS: NoneFoundFemaleBefore
	 * r: AucunTrouveMasculinApres
	 * r.enUS: NoneFoundMaleAfter
	 * r: AucuneTrouveFemininApres
	 * r.enUS: NoneFoundFemaleAfter
	 * r: DVoyelle
	 * r.enUS: OfVowel
	 * r: DeConsonne
	 * r.enUS: OfConsonant
	 * r: AdjectifPluriel
	 * r.enUS: AdjectivePlural
	 * r: UnNomAdjectif
	 * r.enUS: ANameAdjective
	 * r: LeNomAdjectif
	 * r.enUS: TheNameAdjective
	 * r: AdjectifAvant
	 * r.enUS: AdjectiveBefore
	 * r: NomSingulierAdjectifPluriel
	 * r.enUS: NameSingularAdjectivePlural
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
	 * r: CreeMasculin
	 * r.enUS: CreatedMale
	 * r: CreeeFeminin
	 * r.enUS: CreatedFemale
	 * r: ModifieMasculin
	 * r.enUS: ModifiedMale
	 * r: ModifieeFeminin
	 * r.enUS: ModifiedFemale
	 * r: RechercherTousNomPar
	 * r.enUS: SearchAllNameBy
	 * r: RechercherTousNom
	 * r.enUS: SearchAllName
	 * r: CetteConsonne
	 * r.enUS: ThisConsonant
	 * 
	 * r: Rechercher
	 * r.enUS: Search
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
	 * r: contexteImageUri
	 * r.enUS: contextImageUri
	 * r: contexteDescription
	 * r.enUS: contextDescription
	 * r: contexteImageLargeur
	 * r.enUS: contextImageWidth
	 * r: contexteImageHauteur
	 * r.enUS: contextImageHeight
	 * r: ImageLargeur
	 * r.enUS: ImageWidth
	 * r: ImageHauteur
	 * r.enUS: ImageHeight
	 * r: NomSingulier
	 * r.enUS: NameSingular
	 * r: NomPluriel
	 * r.enUS: NamePlural
	 * r: NomVar
	 * r.enUS: NameVar
	 * r: LesNom
	 * r.enUS: TheName
	 * r: contexteTous
	 * r.enUS: contextAll
	 * r: contexteAucunNomTrouve
	 * r.enUS: contextNoNameFound
	 * r: contexteNomVar
	 * r.enUS: contextNameVar
	 * r: contexteDeNom
	 * r.enUS: contextOfName
	 * r: contexteNom
	 * r.enUS: contextName
	 * r: classeContexte
	 * r.enUS: classContext
	 * 
	 * r: Chaine
	 * r.enUS: Chain
	 * r: NomSimple
	 * r.enUS: SimpleName
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
	 * r: ?Langue
	 * r.enUS: ?Language
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
	 * r: siteEcrireMethode
	 * r.enUS: siteWriteMethod
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 * r: toutesLangues
	 * r.enUS: allLanguages
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: NomCanonique
	 * r.enUS: CanonicalName
	 * r: ApiMethode
	 * r.enUS: ApiMethod
	 * r: InitLoin
	 * r.enUS: InitDeep
	 * r: motCle
	 * r.enUS: keyword
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 * r: rechercheSolrClasse
	 * r.enUS: solrSearchClass
	 * r.enUS: solrSearch
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: reponseRechercheClasse
	 * r.enUS: searchResponseClass
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: listeRechercheClasse
	 * r.enUS: searchListClass
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: champ
	 * r.enUS: field
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
	 * r: Traduire
	 * r.enUS: Translate
	 * r: methode
	 * r.enUS: method
	 * r: Contexte
	 * r.enUS: Context
	 * r: suffixe
	 * r.enUS: suffix
	 */        
	public SolrInputDocument indexerClasse(String classeCheminAbsolu, SolrInputDocument classeDoc, String classeLangueNom) throws Exception { 

		String[] classeAutresLangues = ArrayUtils.removeAllOccurences(toutesLangues, classeLangueNom);
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
		
		String classeNomCompletSuper = Object.class.getCanonicalName();
		try {
			classeNomCompletSuper = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCompletSuper", classeQdoxSuper.getGenericCanonicalName());
		} catch (Exception e) {
			if(classeQdoxSuper != null && classeQdoxSuper.getGenericFullyQualifiedName().contains("<"))
				classeNomCompletSuper = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCompletSuper", classeQdoxSuper.getGenericFullyQualifiedName());
		}
		for(JavaClass cImplements : classeQdox.getImplementedInterfaces()) {
			ClasseParts classePartsImplements = ClasseParts.initClasseParts(this, cImplements, classeLangueNom);
			indexerStockerListeSolr(classeLangueNom, classeDoc, "classeImplementsNomSimpleComplet", classePartsImplements.nomSimpleComplet);
		}

		String classeNomCompletSuperGenerique = StringUtils.substringBeforeLast(StringUtils.substringAfter(classeNomCompletSuper, "<"), ">");
		String classeNomCanoniqueSuperGenerique = null;
		String classeNomSimpleSuperGenerique = null;
		Boolean classeBaseEtendGen = false;
		if(StringUtils.isNotEmpty(classeNomCompletSuper)) {
			indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCompletSuperGenerique", classeNomCompletSuperGenerique);
			if(classeNomCompletSuper.contains("<")) {
				classeNomCanoniqueSuperGenerique = StringUtils.substringAfter(StringUtils.substringBeforeLast(classeNomCompletSuper, ">"), "<");
				classeNomCanoniqueSuperGenerique = classeNomCanoniqueSuperGenerique.contains(",") ? StringUtils.substringBefore(classeNomCanoniqueSuperGenerique, ",") : classeNomCanoniqueSuperGenerique;
				indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueSuperGenerique", classeNomCanoniqueSuperGenerique);
				classeNomCompletSuperGenerique = classeNomCanoniqueSuperGenerique;

				if(classeNomCanoniqueSuperGenerique.contains("."))
					classeNomSimpleSuperGenerique = StringUtils.substringAfterLast(classeNomCanoniqueSuperGenerique, ".");
				else
					classeNomSimpleSuperGenerique = classeNomCanoniqueSuperGenerique;
				indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleSuperGenerique", classeNomSimpleSuperGenerique);

				ClasseParts classePartsBase = ClasseParts.initClasseParts(this, classeNomCanoniqueSuperGenerique, classeLangueNom, classeLangueNom);
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
			indexerStockerSolr(classeDoc, "classeContientRequeteSite", classeQdox.getMethodBySignature("getRequeteSite_", new ArrayList<JavaType>(), true) != null);
		} catch (Exception e) {
			// TODO ctate fix this to pull from solr. 
		}
		
		String classeCommentaire = stockerRegexCommentaires(classeLangueNom, classeDoc, "classeCommentaire", classeQdox.getComment());
		String classeNomEnsemble = StringUtils.substringBeforeLast(classeNomCanonique, ".");
		String classeChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), ".java");
		String classeCheminRepertoire = StringUtils.substringBeforeLast(classeChemin, "/");
		String classeCheminGen = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanonique, ".", "/"), "Gen.java");

		String classeCheminRepertoireGen = StringUtils.substringBeforeLast(classeCheminGen, "/");
		String classeCle = classeCheminAbsolu;
		Instant modifiee = Instant.now();
		Date modifieeDate = Date.from(modifiee);
		Boolean classeContientCouverture = false;

		Boolean classeTraduire = indexerStockerSolr(classeDoc, "classeTraduire", !regexTrouve("^(classe)?Traduire: \\s*(false)$", classeCommentaire));
		Boolean classeEtendGen = StringUtils.endsWith(classeNomSimpleSuper, "Gen");
		if(classeSuperErreur || !classeEtendGen && regexTrouve("^(classe)?Gen:\\s*(true)$", classeCommentaire)) {
			classeEtendGen = true;
		}
		if(regexTrouve("^(classe)?Gen:\\s*(false)$", classeCommentaire) || classeQdox.isInterface())
			classeEtendGen = false;

		if(classeTraduire) {
			for(String langueNom : classeAutresLangues) {
				String appliCheminLangue = appliChemins.get(langueNom);
				stockerRegexCommentaires(langueNom, classeDoc, "classeCommentaire", classeCommentaire);
				String cheminSrcMainJavaLangue = appliCheminLangue + "/src/main/java";
				String cheminSrcGenJavaLangue = appliCheminLangue + "/src/gen/java";
				String classeNomCanoniqueLangue = regex("^(classe)?NomCanonique\\." + langueNom + ":\\s*(.*)", classeCommentaire);
	
				if(classeNomCanoniqueLangue == null)
					classeNomCanoniqueLangue = classeNomCanonique.replace(this.langueNom, langueNom);
				String classeNomSimpleLangue = StringUtils.substringAfterLast(classeNomCanoniqueLangue, ".");
				String classeNomEnsembleLangue = StringUtils.substringBeforeLast(classeNomCanoniqueLangue, ".");
				String classeNomCanoniqueGenLangue = classeNomCanoniqueLangue + "Gen";
				String classeNomSimpleGenLangue = classeNomSimpleLangue + "Gen";
				String classeCheminLangue = indexerStockerSolr(langueNom, classeDoc, "classeChemin", concat(cheminSrcMainJavaLangue, "/", StringUtils.replace(classeNomCanoniqueLangue, ".", "/"), ".java"));
				String classeCheminRepertoireLangue = stockerSolr(langueNom, classeDoc, "classeCheminRepertoire", StringUtils.substringBeforeLast(classeCheminLangue, "/"));
				String classeCheminGenLangue = indexerStockerSolr(langueNom, classeDoc, "classeCheminGen", concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniqueGenLangue, ".", "/"), ".java"));
				String classeCheminRepertoireGenLangue = stockerSolr(langueNom, classeDoc, "classeCheminRepertoireGen", StringUtils.substringBeforeLast(classeCheminGenLangue, "/"));
	
				indexerStockerSolr(langueNom, classeDoc, "classeNomCanonique", classeNomCanoniqueLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomSimple", classeNomSimpleLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueGen", classeNomCanoniqueGenLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleGen", classeNomSimpleGenLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomEnsemble", classeNomEnsembleLangue); 
	
				String classeNomCompletSuperLangue;
				ClasseParts classePartsSuperLangue = null;
	
				if(classeEtendGen) {
					classePartsSuperLangue = ClasseParts.initClasseParts(this, classeNomCanoniqueLangue + "Gen", langueNom);
				}
				else if(classeQdoxSuper != null) {
					classePartsSuperLangue = ClasseParts.initClasseParts(this, classeQdoxSuper, langueNom);
				}
	
				if(classePartsSuperLangue != null) {
					indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueSuper", classePartsSuperLangue.nomCanonique); 
					indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleSuper", classePartsSuperLangue.nomSimple); 
					indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueCompletSuper", classePartsSuperLangue.nomCanoniqueComplet);
					indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleCompletSuper", classePartsSuperLangue.nomSimpleComplet);
					if(StringUtils.isNotEmpty(classeNomCompletSuperGenerique)) {
						ClasseParts classePartsSuperGeneriqueLangue = ClasseParts.initClasseParts(this, classeNomCompletSuperGenerique, langueNom);
						indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueSuperGenerique", classePartsSuperGeneriqueLangue.nomCanoniqueComplet);
						indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleSuperGenerique", classePartsSuperGeneriqueLangue.nomSimpleComplet);
					}
				}
				for(JavaClass cImplements : classeQdox.getImplementedInterfaces()) {
					ClasseParts classePartsImplements = ClasseParts.initClasseParts(this, cImplements, langueNom);
					indexerStockerListeSolr(langueNom, classeDoc, "classeImplementsNomSimpleComplet", classePartsImplements.nomSimpleComplet);
				}
			}
		}

		classePartsSolrInputDocument = ClasseParts.initClasseParts(this, "org.apache.solr.common.SolrInputDocument", classeLangueNom);
		classePartsSolrDocument = ClasseParts.initClasseParts(this, "org.apache.solr.common.SolrDocument", classeLangueNom);
		classePartsSolrClient = ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.SolrClient", classeLangueNom);
		classePartsTest = ClasseParts.initClasseParts(this, "org.junit.Test", classeLangueNom);
		classePartsList = ClasseParts.initClasseParts(this, List.class.getCanonicalName(), classeLangueNom);
		classePartsArrayList = ClasseParts.initClasseParts(this, ArrayList.class.getCanonicalName(), classeLangueNom);
		classePartsSiteContexte = classePartsSiteContexte(nomEnsembleDomaine, classeLangueNom);
		classePartsConfigSite = classePartsConfigSite(nomEnsembleDomaine, classeLangueNom);
		classePartsUtilisateurSite = classePartsUtilisateurSite(nomEnsembleDomaine, classeLangueNom);
		classePartsCluster = classePartsCluster(nomEnsembleDomaine, classeLangueNom);
		classePartsResultatRecherche = classePartsResultatRecherche(nomEnsembleDomaine, classeLangueNom);
		classePartsToutEcrivain = classePartsToutEcrivain(nomEnsembleDomaine, classeLangueNom);
		classePartsListeRecherche = classePartsListeRecherche(nomEnsembleDomaine, classeLangueNom);
		classePartsCouverture = classePartsCouverture(nomEnsembleDomaine, classeLangueNom);
		classePartsMiseEnPage = classePartsMiseEnPage(nomEnsembleDomaine, classeLangueNom);
		classePartsPagePart = classePartsPagePart(nomEnsembleDomaine, classeLangueNom);
		classePartsChaine = classePartsChaine(nomEnsembleDomaine, classeLangueNom);
		classePartsRequeteSite = classePartsRequeteSite(nomEnsembleDomaine, classeLangueNom);

		Boolean classeInitLoin = !regexTrouve("^(classe)?InitLoin:\\s*(false)$", classeCommentaire);
		if(classeInitLoin)
			classeInitLoin = classeEtendBase || classeEstBase;
		classeInitLoin = stockerSolr(classeDoc, "classeInitLoin", classeInitLoin);
		if(classeInitLoin)
			classePartsGenAjouter(classePartsRequeteSite);
		indexerStockerSolr(classeDoc, "classeEtendGen", classeEtendGen);

		indexerStockerSolr(classeDoc, "langueNom", classeLangueNom); 
		indexerStockerSolr(classeDoc, "modifiee", modifieeDate); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanonique", classeNomCanonique); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimple", classeNomSimple); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomEnsemble", classeNomEnsemble); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueGen", classeNomCanoniqueGen); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleGen", classeNomSimpleGen); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueSuper", classeNomCanoniqueSuper); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleSuper", classeNomSimpleSuper); 
		indexerStockerSolr(classeDoc, "classeCheminAbsolu", classeCheminAbsolu);
		indexerStockerSolr(classeLangueNom, classeDoc, "classeChemin", classeChemin); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminRepertoire", classeCheminRepertoire);  
		indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminGen", classeCheminGen); 
		indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminRepertoireGen", classeCheminRepertoireGen); 
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

		indexerStockerSolr(classeDoc, "classeEstAbstrait", classeQdox.isAbstract()); 
		Boolean classeModele = indexerStockerSolr(classeDoc, "classeModele", regexTrouve("^(classe)?Modele: \\s*(true)$", classeCommentaire));
		Boolean classeApi = indexerStockerSolr(classeDoc, "classeApi", regexTrouve("^(classe)?Api: \\s*(true)$", classeCommentaire) || classeModele);
		Boolean classePage = regexTrouve("^(classe)?Page: \\s*(true)$", classeCommentaire);
		Boolean classePageSimple = indexerStockerSolr(classeDoc, "classePageSimple", regexTrouve("^(classe)?PageSimple: \\s*(true)$", classeCommentaire));
		Boolean classeSauvegarde = indexerStockerSolr(classeDoc, "classeSauvegarde", regexTrouve("^(classe)?Sauvegarde:\\s*(true)$", classeCommentaire) || classeModele);
		ArrayList<String> classeApiMethodes = regexListe("^(classe)?ApiMethode:\\s*(.*)", classeCommentaire);

		String classeNomSimpleApiEnsembleInfo;
		String classeNomSimpleGenApiServiceImpl;
		String classeNomSimpleApiServiceImpl;
		String classeNomSimpleGenApiService;

		String classeNomCanoniqueApiEnsembleInfo;
		String classeNomCanoniqueGenApiServiceImpl;
		String classeNomCanoniqueApiServiceImpl;
		String classeNomCanoniqueGenApiService;

		String classeNomCanoniquePageGen = classeNomCanonique + "PageGen";
		String classeNomSimplePage = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimplePage", classeNomSimple + "Page");
		String classeNomSimpleGenPage = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleGenPage", classeNomSimple + "PageGen");

		String classeCheminApiEnsembleInfo;
		String classeCheminGenApiServiceImpl;
		String classeCheminApiServiceImpl;
		String classeCheminGenApiService;

		if(classeApi) {
			classeNomSimpleApiEnsembleInfo = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleApiEnsembleInfo", "package-info");
			classeNomSimpleGenApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleGenApiServiceImpl", classeNomSimple + StringUtils.capitalize(classeLangueNom) + "GenApiServiceImpl");
			classeNomSimpleApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleApiServiceImpl", classeNomSimple + StringUtils.capitalize(classeLangueNom) + "ApiServiceImpl");
			classeNomSimpleGenApiService = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomSimpleGenApiService", classeNomSimple + StringUtils.capitalize(classeLangueNom) + "GenApiService");

			classeNomCanoniqueApiEnsembleInfo = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueApiEnsembleInfo", classeNomEnsemble + "." + classeNomSimpleApiEnsembleInfo);
			classeNomCanoniqueGenApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueGenApiServiceImpl", classeNomEnsemble + "." + classeNomSimpleGenApiServiceImpl);
			classeNomCanoniqueApiServiceImpl = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueApiServiceImpl", classeNomEnsemble + "." + classeNomSimpleApiServiceImpl);
			classeNomCanoniqueGenApiService = indexerStockerSolr(classeLangueNom, classeDoc, "classeNomCanoniqueGenApiService", classeNomEnsemble + "." + classeNomSimpleGenApiService);

			classeCheminApiEnsembleInfo = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanoniqueApiEnsembleInfo, ".", "/"), ".java");
			classeCheminGenApiServiceImpl = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceImpl, ".", "/"), ".java");
			classeCheminApiServiceImpl = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueApiServiceImpl, ".", "/"), ".java");
			classeCheminGenApiService = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueGenApiService, ".", "/"), ".java");

			indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminApiEnsembleInfo", classeCheminApiEnsembleInfo); 
			indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminGenApiServiceImpl", classeCheminGenApiServiceImpl); 
			indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminApiServiceImpl", classeCheminApiServiceImpl); 
			indexerStockerSolr(classeLangueNom, classeDoc, "classeCheminGenApiService", classeCheminGenApiService); 
		}

		if(classeTraduire) {
			for(String langueNom : classeAutresLangues) {
				String appliCheminLangue = appliChemins.get(langueNom);
				String cheminSrcMainJavaLangue = appliCheminLangue + "/src/main/java";
				String cheminSrcGenJavaLangue = appliCheminLangue + "/src/gen/java";
				String classeNomCanoniqueLangue = (String)classeDoc.get("classeNomCanonique_" + langueNom + "_indexed_string").getValue();
				String classeNomSimpleLangue = (String)classeDoc.get("classeNomSimple_" + langueNom + "_indexed_string").getValue();
				String classeNomEnsembleLangue = (String)classeDoc.get("classeNomEnsemble_" + langueNom + "_indexed_string").getValue();
	
				String classeNomSimpleApiLangue = classeNomSimpleLangue + "Api";
				String classeNomSimplePageLangue = classeNomSimpleLangue + "Page";
				String classeNomSimpleGenPageLangue = classeNomSimpleLangue + "PageGen";
				String classeNomCanoniqueApiLangue = classeNomCanoniqueLangue + "Api";
				String classeNomCanoniqueApiGenLangue = classeNomCanoniqueLangue + "ApiGen";
				String classeNomCanoniquePageLangue = classeNomCanoniqueLangue + "Page";
				String classeNomCanoniquePageGenLangue = classeNomCanoniqueLangue + "PageGen";
				String classeCheminApiGenLangue = indexerStockerSolr(langueNom, classeDoc, "classeCheminApiGen", concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniqueApiGenLangue, ".", "/"), ".java"));
				String classeCheminGenPageLangue = indexerStockerSolr(langueNom, classeDoc, "classeCheminGenPage", concat(cheminSrcGenJavaLangue, "/", StringUtils.replace(classeNomCanoniquePageGenLangue, ".", "/"), ".java"));
				indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleApi", classeNomSimpleApiLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomSimplePage", classeNomSimplePageLangue); 
				indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleGenPage", classeNomSimpleGenPageLangue); 
	
				if(classeApi) {
					String classeNomSimpleApiEnsembleInfoLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleApiEnsembleInfo", "package-info");
					String classeNomSimpleGenApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleGenApiServiceImpl", classeNomSimpleLangue + StringUtils.capitalize(langueNom) + "GenApiServiceImpl");
					String classeNomSimpleApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleApiServiceImpl", classeNomSimpleLangue + StringUtils.capitalize(langueNom) + "ApiServiceImpl");
					String classeNomSimpleGenApiServiceLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomSimpleGenApiService", classeNomSimpleLangue + StringUtils.capitalize(langueNom) + "GenApiService");
		
					String classeNomCanoniqueApiEnsembleInfoLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueApiEnsembleInfo", classeNomEnsembleLangue + "." + classeNomSimpleApiEnsembleInfoLangue);
					String classeNomCanoniqueGenApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueGenApiServiceImpl", classeNomEnsembleLangue + "." + classeNomSimpleGenApiServiceImplLangue);
					String classeNomCanoniqueApiServiceImplLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueApiServiceImpl", classeNomEnsembleLangue + "." + classeNomSimpleApiServiceImplLangue);
					String classeNomCanoniqueGenApiServiceLangue = indexerStockerSolr(langueNom, classeDoc, "classeNomCanoniqueGenApiService", classeNomEnsembleLangue + "." + classeNomSimpleGenApiServiceLangue);
		
					String classeCheminApiEnsembleInfoLangue = concat(cheminSrcGenJava, "/", StringUtils.replace(classeNomCanoniqueApiEnsembleInfoLangue, ".", "/"), ".java");
					String classeCheminGenApiServiceImplLangue = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceImplLangue, ".", "/"), ".java");
					String classeCheminApiServiceImplLangue = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueApiServiceImplLangue, ".", "/"), ".java");
					String classeCheminGenApiServiceLangue = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomCanoniqueGenApiServiceLangue, ".", "/"), ".java");
		
					indexerStockerSolr(langueNom, classeDoc, "classeCheminApiEnsembleInfo", classeCheminApiEnsembleInfoLangue); 
					indexerStockerSolr(langueNom, classeDoc, "classeCheminGenApiServiceImpl", classeCheminGenApiServiceImplLangue); 
					indexerStockerSolr(langueNom, classeDoc, "classeCheminApiServiceImpl", classeCheminApiServiceImplLangue); 
					indexerStockerSolr(langueNom, classeDoc, "classeCheminGenApiService", classeCheminGenApiServiceLangue); 
				}
			}
		}

		if(classeApi) {
			classePartsGenApiAjouter(classePartsConfigSite);
			classePartsGenApiAjouter(classePartsRequeteSite);
			classePartsGenApiAjouter(classePartsSiteContexte);
			classePartsGenApiAjouter(classePartsUtilisateurSite);
			classePartsGenApiAjouter(classePartsResultatRecherche);
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.io.IOException", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Collections", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Map", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.concurrent.TimeUnit", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.stream.Collectors", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.Json", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.SolrQuery", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.SolrQuery.ORDER", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.response.QueryResponse", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.util.ClientUtils", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.StringUtils", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.security.Principal", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.io.PrintWriter", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.common.SolrDocumentList", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.common.SolrDocument", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Collection", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.math.BigDecimal", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Date", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.List", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.ArrayList", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Arrays", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Set", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Handler", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.RoutingContext", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.Router", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Vertx", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.reactivestreams.ReactiveReadStream", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.reactivestreams.ReactiveWriteStream", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.MultiMap", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.auth.oauth2.OAuth2Auth", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.netty.handler.codec.http.HttpResponseStatus", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.logging.Logger", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.logging.LoggerFactory", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.validation.HTTPRequestValidationHandler", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.validation.ParameterTypeValidator", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.validation.ValidationException", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.sql.SQLClient", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.sql.SQLConnection", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonArray", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonObject", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.time.LocalDateTime", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.sql.Timestamp", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Future", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.CaseInsensitiveHeaders", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.AsyncResult", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.Handler", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.buffer.Buffer", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.OperationResponse", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.CompositeFuture", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.http.client.utils.URLEncodedUtils", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.nio.charset.Charset", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "org.apache.http.NameValuePair", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.OperationRequest", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.auth.oauth2.KeycloakHelper", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.sql.SQLConnection", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.Optional", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.util.stream.Stream", classeLangueNom));
			classePartsGenApiAjouter(ClasseParts.initClasseParts(this, "java.net.URLDecoder", classeLangueNom));
			classePartsGenApiAjouter(classePartsListeRecherche);
			classePartsGenApiAjouter(classePartsToutEcrivain);
		}
		if(classeEtendBase || classeEstBase) {
			classePartsGenAjouter(classePartsCluster);
			classePartsGenAjouter(classePartsToutEcrivain);
		}
		if(classeSauvegarde) {
			classePartsGenAjouter(classePartsSiteContexte);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.logging.Logger", classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.logging.LoggerFactory", classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.sql.SQLClient", classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.sql.SQLConnection", classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonObject", classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, VAL_nomCanoniqueDate, classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.util.Set", classeLangueNom));
		}
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonArray", classeLangueNom));
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.text.StringEscapeUtils", classeLangueNom));
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.StringUtils", classeLangueNom));
		classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.util.Objects", classeLangueNom));

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
				stockerListeSolr(classeRoleLangue, classeDoc, "classeRoles", classeRoleValeur);
				classeRolesTrouves = true;
				classeRolesTrouvesActuel = classeRolesRecherche.find();
			}
			indexerStockerSolr(classeDoc, "classeRolesTrouves", classeRolesTrouves); 

			Matcher classeFiltresRecherche = Pattern.compile("^(classe)?Filtre:\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classeCommentaire);
			boolean classeFiltresTrouves = classeFiltresRecherche.find();
			boolean classeFiltresTrouvesActuel = classeFiltresTrouves;
			while(classeFiltresTrouvesActuel) {
				String classeFiltreValeur = classeFiltresRecherche.group(2);
				stockerListeSolr(classeDoc, "classeFiltres", classeFiltreValeur);
				classeFiltresTrouves = true;
				classeFiltresTrouvesActuel = classeFiltresRecherche.find();
			}
			indexerStockerSolr(classeDoc, "classeFiltresTrouves", classeFiltresTrouves); 

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
			ClasseParts classePartsSuperGenerique = ClasseParts.initClasseParts(this, classeNomCompletSuperGenerique, classeLangueNom);
			classePartsGenAjouter(classePartsSuperGenerique);

			if(StringUtils.startsWith(classeNomCanoniqueSuper, nomEnsembleDomaine)) {
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1);
				rechercheSolr.addFilterQuery("classeNomCanonique_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classeNomCanoniqueSuperGenerique));
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
				rechercheSolr.addFilterQuery("classeNomCanonique_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classeNomCanoniqueSuperGenerique));
				rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
				rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
				QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList listeRecherche = reponseRecherche.getResults();
				if(listeRecherche.size() > 0) { 
					classeSuperDoc = listeRecherche.get(0);
				}
			}
		}

		List<String> classeSuperEcrireMethodes;
		List<String> classeEcrireMethodes = new ArrayList<>();
		if(classeSuperDoc != null) 
			classeSuperEcrireMethodes = (List<String>)classeSuperDoc.get("classeEcrireMethodes_stored_strings");
		else
			classeSuperEcrireMethodes = new ArrayList<>();

		for(String siteEcrireMethode : siteEcrireMethodes) {
			if(classeQdox.getMethodBySignature(siteEcrireMethode, new ArrayList<JavaType>()) != null
					|| classeQdox.getMethodBySignature(siteEcrireMethode + classeNomSimple, new ArrayList<JavaType>()) != null) {
				if(!classeEcrireMethodes.contains(siteEcrireMethode)) {
					indexerStockerListeSolr(classeDoc, "classeEcrireMethodes",  siteEcrireMethode);
					classeEcrireMethodes.add(siteEcrireMethode);
				}
			}
		}

		if(classeSuperDoc != null) {
			if(classeSuperEcrireMethodes != null) {
				for(String classeSuperEcrireMethode : classeSuperEcrireMethodes) {
					indexerStockerListeSolr(classeDoc, "classeSuperEcrireMethodes",  classeSuperEcrireMethode);
					if(!classeEcrireMethodes.contains(classeSuperEcrireMethode)) {
						indexerStockerListeSolr(classeDoc, "classeEcrireMethodes",  classeSuperEcrireMethode);
						classeEcrireMethodes.add(classeSuperEcrireMethode);
					}
				}
			}
		}

		if(classeDoc.getField("id") == null)
			classeDoc.addField("id", classeCle);  

		indexerStockerSolr(classeDoc, "partEstClasse", true);
		indexerStockerSolr(classeDoc, "partNumero", partNumero);
		
		for(String classeImportation : classeQdox.getSource().getImports()) {
			ClasseParts classeImportationClasseParts = ClasseParts.initClasseParts(this, classeImportation, classeLangueNom);
			indexerStockerListeSolr(classeLangueNom, classeDoc, "classeImportations", classeImportationClasseParts.nomCanonique(classeLangueNom));

			for(String langueNom : classeAutresLangues) {  
				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classeImportationClasseParts, langueNom);
				indexerStockerListeSolr(langueNom, classeDoc, "classeImportations", classeImportationClassePartsLangue.nomCanonique(langueNom));
			}
		}

		List<JavaMember> membresQdox = classeQdox.getMembers();
		for(JavaMember membreQdox : membresQdox) {  
			partNumero++;

			if(membreQdox instanceof JavaField) {    
				SolrInputDocument champDoc = classeDocClone.deepCopy();
				JavaField champQdox = (JavaField)membreQdox;
				String champCommentaire = champQdox.getComment();
				String champVar = champQdox.getName();
				String champCle = classeCheminAbsolu + "." + champVar;
				String champCodeSource = StringUtils.substringBeforeLast(StringUtils.trim(regex("\\s+" + champVar + "\\s*=([\\s\\S]*)", champQdox.getCodeBlock(), 1)), ";");
				String champString = regex("^(champ)?String\\." + classeLangueNom + ":(.*)", champCommentaire);
				if(champString != null) {
					champCodeSource = "\"" + StringUtils.replace(StringUtils.replace(champString, "\\", "\\\\"), "\"", "\\\"") + "\"";
					indexerStockerSolr(classeLangueNom, champDoc, "champString", champString); 
				}

				// Champs Solr du champ. 

				indexerStockerSolr(classeLangueNom, champDoc, "champVar", champVar); 
				indexerStockerSolr(champDoc, "partEstChamp", true);
				indexerStockerSolr(champDoc, "partNumero", partNumero);
				indexerStockerSolr(champDoc, "champEstPublic", champQdox.isPublic()); 
				indexerStockerSolr(champDoc, "champEstProtege", champQdox.isProtected()); 
				indexerStockerSolr(champDoc, "champEstPrive", champQdox.isPrivate()); 
				indexerStockerSolr(champDoc, "champEstStatique", champQdox.isStatic()); 
				indexerStockerSolr(champDoc, "champEstFinale", champQdox.isFinal()); 
				indexerStockerSolr(champDoc, "champEstAbstrait", champQdox.isAbstract()); 
				indexerStockerSolr(champDoc, "champEstNatif", champQdox.isNative()); 
				indexerStockerSolr(champDoc, "champTraduire", !regexTrouve("^(champ)?Traduire: \\s*(false)$", champCommentaire));
	
				/////////////////
				// Annotations //
				/////////////////
				List<JavaAnnotation> annotations = champQdox.getAnnotations(); 
				ArrayList<String> annotationsLangue = new ArrayList<String>();
				Boolean champEstTest = false;
				Boolean champEstSubstitue = false;
				for(JavaAnnotation annotation : annotations) {
					String champAnnotationLangue = annotation.getType().getCanonicalName();
					indexerStockerSolr(classeLangueNom, champDoc, "champAnnotation", champAnnotationLangue); 

					if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
						champEstTest = true;
					}
					if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
						champEstSubstitue = true;
					}
				}
				indexerStockerSolr(champDoc, "champEstTest", champEstTest); 
				indexerStockerSolr(champDoc, "champEstSubstitue", champEstSubstitue); 

				ClasseParts champClasseParts = ClasseParts.initClasseParts(this, champQdox.getType(), classeLangueNom);
	
				stockerRegexCommentaires(classeLangueNom, champDoc, "champCommentaire", champCommentaire);
				stockerSolr(classeLangueNom, champDoc, "champNomSimpleComplet", champClasseParts.nomSimpleComplet);
				String champNomCanoniqueComplet = stockerSolr(classeLangueNom, champDoc, "champNomCanoniqueComplet", champClasseParts.nomCanoniqueComplet);
				stockerSolr(classeLangueNom, champDoc, "champCodeSource", champCodeSource);
				champDoc.addField("id", champNomCanoniqueComplet + " " + champCle);

				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) { 
						ClasseParts champClassePartsLangue = ClasseParts.initClasseParts(this, champClasseParts, langueNom);
						String champVarLangue = regex("^(champ)?Var\\." + langueNom + ": (.*)", champCommentaire);
						champVarLangue = champVarLangue == null ? champVar : champVarLangue;
						String champCodeSourceLangue = regexRemplacerTout(champCommentaire, champCodeSource, langueNom);
						String champStringLangue = regex("^(champ)?String\\." + langueNom + ":(.*)", champCommentaire);
						if(champStringLangue != null) {
							champCodeSourceLangue = "\"" + StringUtils.replace(StringUtils.replace(champStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\"";
							indexerStockerSolr(langueNom, champDoc, "champString", champString); 
						}
	
						indexerStockerSolr(langueNom, champDoc, "champVar", champVarLangue); 
						stockerSolr(langueNom, champDoc, "champNomSimpleComplet", champClassePartsLangue.nomSimpleComplet);
						stockerSolr(langueNom, champDoc, "champNomCanoniqueComplet", champClassePartsLangue.nomCanoniqueComplet);
						stockerRegexCommentaires(langueNom, champDoc, "champCommentaire", champCommentaire);
						stockerSolr(langueNom, champDoc, "champCodeSource", champCodeSourceLangue);
					}  
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
					stockerListeSolr(classeLangueNom, constructeurDoc, "constructeurParamsVar", constructeurParamVar);
					ClasseParts constructeurParamClasseParts = ClasseParts.initClasseParts(this, constructeurParamQdox.getJavaClass(), classeLangueNom);
					classePartsGenAjouter(constructeurParamClasseParts);
					stockerListeSolr(classeLangueNom, constructeurDoc, "constructeurParamsNomSimpleComplet", constructeurParamClasseParts.nomSimpleComplet);
					stockerListeSolr(constructeurDoc, "constructeurParamsArgsVariables", constructeurParamQdox.isVarArgs());
					if(classeTraduire) {
						for(String langueNom : classeAutresLangues) { 
							String constructeurParamVarLangue = regex("^(constructeur)?Param" + constructeurParamNum + "\\.var\\." + langueNom + ": (.*)", constructeurCommentaire);
							if(constructeurParamVarLangue == null)
								constructeurParamVarLangue = constructeurParamVar;
							ClasseParts constructeurParamClassePartsLangue = ClasseParts.initClasseParts(this, constructeurParamClasseParts, langueNom);
	
							classePartsGenAjouter(constructeurParamClassePartsLangue);
							stockerListeSolr(langueNom, constructeurDoc, "constructeurParamsNomSimpleComplet", constructeurParamClassePartsLangue.nomSimpleComplet);
							stockerListeSolr(langueNom, constructeurDoc, "constructeurParamsVar", constructeurParamVarLangue);
						}  
					}
				}
				for(JavaAnnotation constructeurAnnotation : constructeurAnnotations) {
//					String constructeurAnnotationBlocCode = stockerListeSolr(langueNom, constructeurDoc, "constructeurAnnotationBlocCode", annotation.toString());
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
				stockerRegexCommentaires(classeLangueNom, constructeurDoc, "constructeurCommentaire", constructeurCommentaire);

				String constructeurCodeSource = constructeurQdox.getSourceCode();
				String constructeurCodeSourceLangue = constructeurCodeSource;
				ArrayList<String> remplacerClesLangue = regexListe("^r." + classeLangueNom + "\\s*=\\s*(.*)\\n.*", constructeurCommentaire);
				ArrayList<String> remplacerValeursLangue = regexListe("^r." + classeLangueNom + "\\s*=\\s*.*\\n(.*)", constructeurCommentaire);
				for(int i = 0; i < remplacerClesLangue.size(); i++) {
					String cle = remplacerClesLangue.get(i);
					String valeur = remplacerValeursLangue.get(i);
					StringUtils.replace(constructeurCodeSourceLangue, cle, valeur);
				}
				stockerSolr(classeLangueNom, constructeurDoc, "constructeurCodeSource", constructeurCodeSourceLangue);

				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						constructeurCodeSourceLangue = regexRemplacerTout(constructeurCommentaire, constructeurCodeSource, langueNom);
						stockerSolr(langueNom, constructeurDoc, "constructeurCodeSource", constructeurCodeSourceLangue);
						stockerRegexCommentaires(langueNom, constructeurDoc, "constructeurCommentaire", constructeurCommentaire);
					} 
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
						String entiteVar = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVar", StringUtils.substringAfter(methodeQdox.getName(), "_"));
						indexerStockerListeSolr(classeLangueNom, classeDoc, "classeEntiteVars", entiteVar);
						String entiteVarCapitalise = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVarCapitalise", StringUtils.capitalize(entiteVar));
						JavaClass entiteClasseQdox = methodeParamsQdox.get(0).getJavaClass();
						ClasseParts entiteClasseParts = ClasseParts.initClasseParts(this, entiteClasseQdox, classeLangueNom, classeLangueNom);
						Boolean entiteCouverture = false;

						if(entiteClasseParts.nomSimple.equals("Couverture")) {
							entiteClasseParts = ClasseParts.initClasseParts(this, entiteClasseParts.nomCanoniqueGenerique, classeLangueNom, classeLangueNom);
							entiteCouverture = true;
							classeContientCouverture = true;
						}

						classePartsGenAjouter(entiteClasseParts);
						classePartsGenPageAjouter(entiteClasseParts);
						List<String> entiteNomsCanoniquesSuperEtMoiSansGen = new ArrayList<String>();
						if(StringUtils.isNotEmpty(entiteClasseParts.nomCanoniqueGenerique)) {
							ClasseParts classePartsGenerique = ClasseParts.initClasseParts(this, entiteClasseParts.nomCanoniqueGenerique, classeLangueNom);
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

						String entiteNomCanonique = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomCanonique", entiteClasseParts.nomCanonique);
						String entiteNomSimple = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomSimple", entiteClasseParts.nomSimple);
						String entiteNomCompletGenerique = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomCompletGenerique", entiteClasseParts.nomCanoniqueGenerique);
						String entiteNomCanoniqueGenerique = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomCanoniqueGenerique", entiteClasseParts.nomCanoniqueGenerique);
						String entiteNomSimpleGenerique = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomSimpleGenerique", entiteClasseParts.nomSimpleGenerique);
						String entiteNomCanoniqueActuel = entiteNomCanoniqueGenerique == null ? entiteNomCanonique : entiteNomCanoniqueGenerique;
						String entiteNomSimpleActuel = entiteNomSimpleGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique;
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomCanoniqueComplet", entiteClasseParts.nomCanoniqueComplet);
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomSimpleComplet", entiteClasseParts.nomSimpleComplet);
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomSimpleCompletGenerique", entiteClasseParts.nomSimpleGenerique);

						JavaMethod entiteSetter = null;
						try {
							entiteSetter = classeQdox.getMethodBySignature("set" + entiteVarCapitalise, new ArrayList<JavaType>() {{ add(classeQdoxString); }}, true);
						} catch (Exception e) {
						}
						
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
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomCanoniqueBase", entiteNomCanoniqueBase);
						if(entiteCouverture)
							entiteClasseQdox = bricoleur.getClassByName(entiteNomCanonique);

						boolean entiteEtendPagePart = classePartsPagePart != null && entiteNomsCanoniquesSuperEtMoiSansGen.contains(classePartsPagePart.nomCanonique);
								
						for(String siteEcrireMethode : siteEcrireMethodes) {
							if(entiteClasseQdox.getMethodBySignature(siteEcrireMethode, new ArrayList<JavaType>()) != null
									|| entiteClasseQdox.getMethodBySignature(siteEcrireMethode + classeNomSimple, new ArrayList<JavaType>()) != null
									|| entiteEtendPagePart && siteEcrireMethode.equals("htmlBody"))
								indexerStockerListeSolr(entiteDoc, "entiteEcrireMethodes",  siteEcrireMethode);
							if(entiteEtendPagePart && siteEcrireMethode.equals("htmlBody")) {
								for(String langueNom : toutesLangues) {
									indexerStockerListeSolr(langueNom, classeDoc, "classeMethodeVars", "htmlBody" + entiteVarCapitalise);
								}
							}
						}
						
						String entiteNomSimpleBase = null;
						if(StringUtils.isNotEmpty(entiteNomCanoniqueBase)) {
							entiteNomSimpleBase = StringUtils.substringAfterLast(entiteNomCanoniqueBase, ".");
						}
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomSimpleBase", entiteNomSimpleBase);
						
						String entiteVarParam = methodeParamsQdox.get(0).getName();
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVarParam", entiteVarParam);
						
						String entiteVarCouverture = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteVarCouverture", entiteVar + "Couverture");

						Boolean entiteInitLoin = indexerStockerSolr(entiteDoc, "entiteInitLoin", !entiteVar.endsWith("_"));

						if(entiteNomsCanoniquesSuperEtMoiSansGen.size() > 0) {
							String fqClassesSuperEtMoi = "(" + classesSuperQdoxEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c.getCanonicalName())).collect(Collectors.joining(" OR ")) + ")";

							SolrQuery rechercheSolrMethodeAvant = new SolrQuery();   
							rechercheSolrMethodeAvant.setQuery("*:*");
							rechercheSolrMethodeAvant.setRows(10);
							String fqMethodeAvant = "(" + entiteNomsCanoniquesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars("avant" + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							rechercheSolrMethodeAvant.addFilterQuery("entiteClassesSuperEtMoiSansGen_indexed_strings:" + fqClassesSuperEtMoi);
							rechercheSolrMethodeAvant.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
							rechercheSolrMethodeAvant.addFilterQuery("partEstMethode_indexed_boolean:true");
							rechercheSolrMethodeAvant.addFilterQuery("methodeVar_" + classeLangueNom + "_indexed_string:" + fqMethodeAvant);
							QueryResponse reponseRechercheMethodeAvant = clientSolrComputate.query(rechercheSolrMethodeAvant);
							SolrDocumentList listeRechercheMethodeAvant = reponseRechercheMethodeAvant.getResults();
	
							for(SolrDocument documentSolr : listeRechercheMethodeAvant) {
								String methodeVarActuel = (String)documentSolr.get("methodeVar_" + classeLangueNom + "_stored_string");
								String methodeClasseNomCanonique = (String)documentSolr.get("classeNomCanonique_" + classeLangueNom + "_stored_string");
								List<String> methodeParamsNomSimpleComplet = (List<String>)documentSolr.get("methodeParamsNomSimpleComplet_" + classeLangueNom + "_stored_strings");
								String methodeParamNomSimpleComplet = methodeParamsNomSimpleComplet.get(0);
								List<String> methodeParamsVar = (List<String>)documentSolr.get("methodeParamsVar_" + classeLangueNom + "_stored_strings");
								String methodeParamVar = methodeParamsVar.get(0);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantVisibilite", BooleanUtils.isTrue((Boolean)documentSolr.get("methodeEstPublic_stored_boolean")) ? "public" : "protected");
								stockerListeSolr(entiteDoc, "entiteMethodesAvantVar", methodeVarActuel);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantParamVar", methodeParamVar);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantParamNomSimple", methodeParamNomSimpleComplet);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantNomParam", methodeParamsVar.size() > 1);
								Boolean entiteMethodesAvantEcrire = (StringUtils.equals(methodeClasseNomCanonique, classeNomCanonique)) && !classeMethodesEcrites.contains(methodeVarActuel);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantEcrire", entiteMethodesAvantEcrire);
								classeMethodesEcrites.add(methodeVarActuel);
								List<String> methodeParamsNomCanonique = (List<String>)documentSolr.get("methodeParamsNomCanonique_" + classeLangueNom + "_stored_strings");
								if(methodeParamsNomCanonique != null) {
									String methodeParamNomCanonique = methodeParamsNomCanonique.get(0);
									classePartsGenAjouter(ClasseParts.initClasseParts(this, methodeParamNomCanonique, classeLangueNom));
								}
							}
	
							SolrQuery rechercheSolrMethodeApres = new SolrQuery();   
							rechercheSolrMethodeApres.setQuery("*:*");
							rechercheSolrMethodeApres.setRows(10);
							String fqMethodeApres = "(" + entiteNomsCanoniquesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars("apres" + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							rechercheSolrMethodeApres.addFilterQuery("entiteClassesSuperEtMoiSansGen_indexed_strings:" + fqClassesSuperEtMoi);
							rechercheSolrMethodeApres.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
							rechercheSolrMethodeApres.addFilterQuery("partEstMethode_indexed_boolean:true");
							rechercheSolrMethodeApres.addFilterQuery("methodeVar_" + classeLangueNom + "_indexed_string:" + fqMethodeApres);
							QueryResponse reponseRechercheMethodeApres = clientSolrComputate.query(rechercheSolrMethodeApres);
							SolrDocumentList listeRechercheMethodeApres = reponseRechercheMethodeApres.getResults();
	
							for(SolrDocument documentSolr : listeRechercheMethodeApres) {
								String methodeVarActuel = (String)documentSolr.get("methodeVar_" + classeLangueNom + "_stored_string");
								String methodeClasseNomCanonique = (String)documentSolr.get("classeNomCanonique_" + classeLangueNom + "_stored_string");
								List<String> methodeParamsNomSimpleComplet = (List<String>)documentSolr.get("methodeParamsNomSimpleComplet_" + classeLangueNom + "_stored_strings");
								String methodeParamNomSimpleComplet = methodeParamsNomSimpleComplet.get(0);
								List<String> methodeParamsVar = (List<String>)documentSolr.get("methodeParamsVar_" + classeLangueNom + "_stored_strings");
								String methodeParamVar = methodeParamsVar.get(0);
								stockerListeSolr(entiteDoc, "entiteMethodesApresVisibilite", BooleanUtils.isTrue((Boolean)documentSolr.get("methodeEstPublic_stored_boolean")) ? "public" : "protected");
								stockerListeSolr(entiteDoc, "entiteMethodesApresVar", methodeVarActuel);
								stockerListeSolr(entiteDoc, "entiteMethodesApresParamVar", methodeParamVar);
								stockerListeSolr(entiteDoc, "entiteMethodesApresParamNomSimple", methodeParamNomSimpleComplet);
								stockerListeSolr(entiteDoc, "entiteMethodesApresNomParam", methodeParamsVar.size() > 1);
								Boolean entiteMethodesAvantEcrire = (StringUtils.equals(methodeClasseNomCanonique, classeNomCanonique)) && !classeMethodesEcrites.contains(methodeVarActuel);
								stockerListeSolr(entiteDoc, "entiteMethodesAvantEcrire", entiteMethodesAvantEcrire);
								classeMethodesEcrites.add(methodeVarActuel);
								List<String> methodeParamsNomCanonique = (List<String>)documentSolr.get("methodeParamsNomCanonique_" + classeLangueNom + "_stored_strings");
								if(methodeParamsNomCanonique != null) {
									String methodeParamNomCanonique = methodeParamsNomCanonique.get(0);
									classePartsGenAjouter(ClasseParts.initClasseParts(this, methodeParamNomCanonique, classeLangueNom));
								}
							}
						}

						if(methodeCommentaire != null) {

							Matcher entiteValsRecherche = Pattern.compile("^(entite)?Val\\.(\\w+)(\\.(\\w+))?:(.*)", Pattern.MULTILINE).matcher(methodeCommentaire);
							boolean entiteValsTrouves = entiteValsRecherche.find();
							while(entiteValsTrouves) {
								String entiteValVar = entiteValsRecherche.group(2);
								String entiteValLangue = entiteValsRecherche.group(4);
								String entiteValValeur = entiteValsRecherche.group(5);
								if(entiteValLangue == null) {
									stockerListeSolr(entiteDoc, "entiteValsVar", entiteValVar);
									stockerListeSolr(entiteDoc, "entiteValsLangue", "");
									stockerListeSolr(entiteDoc, "entiteValsValeur", entiteValValeur);
								}
								else {
									stockerListeSolr(entiteDoc, "entiteValsVar", entiteValVar);
									stockerListeSolr(entiteDoc, "entiteValsLangue", entiteValLangue);
									stockerListeSolr(entiteDoc, "entiteValsValeur", entiteValValeur);
								}
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
						Boolean entiteIncremente = indexerStockerSolr(entiteDoc, "entiteIncremente", regexTrouve("^(entite)?Incremente:\\s*(true)$", methodeCommentaire));
						Boolean entiteIndexe = indexerStockerSolr(entiteDoc, "entiteIndexe", regexTrouve("^(entite)?Indexe:\\s*(true)$", methodeCommentaire) || entiteCleUnique || entiteCrypte || entiteSuggere || entiteClePrimaire || entiteIncremente);
						Boolean entiteStocke = indexerStockerSolr(entiteDoc, "entiteStocke", regexTrouve("^(entite)?Stocke:\\s*(true)$", methodeCommentaire));
						Boolean entiteTexte = indexerStockerSolr(entiteDoc, "entiteTexte", regexTrouve("^(entite)?Texte:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteIndexeOuStocke", entiteCleUnique || entiteCrypte || entiteSuggere || entiteIndexe || entiteStocke || entiteIncremente || entiteTexte);
						indexerStockerSolr(entiteDoc, "entiteIgnorer", regexTrouve("^(entite)?Ignorer:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteDeclarer", regexTrouve("^(entite)?Declarer:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteRechercher", regexTrouve("^(entite)?Rechercher:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteAjouter", regexTrouve("^(entite)?Ajouter:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteSupprimer", regexTrouve("^(entite)?Supprimer:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteDefinir", regexTrouve("^(entite)?Definir:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteRecharger", regexTrouve("^(entite)?Recharger:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteMultiligne", regexTrouve("^(entite)?Multiligne:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteCles", regexTrouve("^(entite)?Cles:\\s*(true)$", methodeCommentaire));

						String entiteLangue = regex("^(entite)?Langue:\\s*(.*)$", methodeCommentaire);
						if(entiteLangue != null)
							indexerStockerSolr(entiteDoc, "entiteLangue", entiteLangue);

						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteNomAffichage", regexLangue(classeLangueNom, "(entite)?NomAffichage", methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteDescription", regexLangue(classeLangueNom, "(entite)?Description", methodeCommentaire));
						indexerStockerSolr(entiteDoc, "entiteOptionnel", regexTrouve("^(entite)?Optionnel:\\s*(true)$", methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteHtmlTooltip", regexLangue(classeLangueNom, "(entite)?HtmlTooltip", methodeCommentaire));
						indexerStockerSolrRegex(classeLangueNom, entiteDoc, "entiteVarApi", "VarApi", methodeCommentaire);
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteEnumNomSimple", regexLangue(classeLangueNom, "(entite)?EnumNomSimple", methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteEnumVar", regexLangue(classeLangueNom, "(entite)?EnumVar", methodeCommentaire));
						indexerStockerSolr(classeLangueNom, entiteDoc, "entiteEnumVarDescription", regexLangue(classeLangueNom, "(entite)?EnumVarDescription", methodeCommentaire));

						Boolean entiteHighlighting = indexerStockerSolr(entiteDoc, "entiteHighlighting", regexTrouve("^(entite)?Highlighting:\\s*(true)$", methodeCommentaire));
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

						if(classeTraduire) {
							for(String langueNom : classeAutresLangues) {  
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomAffichage", regexLangue(langueNom, "(entite)?NomAffichage", methodeCommentaire));
								indexerStockerSolr(langueNom, entiteDoc, "entiteEnumVarDescription", regexLangue(langueNom, "(entite)?EnumVarDescription", methodeCommentaire));
								indexerStockerSolr(langueNom, entiteDoc, "entiteHtmlTooltip", regexLangue(langueNom, "(entite)?HtmlTooltip", methodeCommentaire));
								indexerStockerSolrRegex(langueNom, entiteDoc, "entiteVarApi", "VarApi", methodeCommentaire);
								indexerStockerSolr(langueNom, entiteDoc, "entiteEnumVar", regexLangue(langueNom, "(entite)?EnumVar", methodeCommentaire));
							}
						}

						Matcher entiteAttribuerRecherche = methodeCommentaire == null ? null : Pattern.compile("^(entite)?Attribuer:\\s*([^\\.]+)\\.(.*)\\s*", Pattern.MULTILINE).matcher(methodeCommentaire);
						boolean entiteAttribuerTrouve = entiteAttribuerRecherche == null ? false : entiteAttribuerRecherche.find();
						if(entiteAttribuerTrouve) {
							String entiteAttribuerNomSimple = entiteAttribuerRecherche.group(2);
							String entiteAttribuerVar = entiteAttribuerRecherche.group(3);

							SolrQuery rechercheSolrClasse = new SolrQuery();   
							rechercheSolrClasse.setQuery("*:*");
							rechercheSolrClasse.setRows(1);
							rechercheSolrClasse.addFilterQuery("classeNomSimple_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerNomSimple));
							rechercheSolrClasse.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
							rechercheSolrClasse.addFilterQuery("partEstClasse_indexed_boolean:true");
							QueryResponse reponseRechercheClasse = clientSolrComputate.query(rechercheSolrClasse);
							SolrDocumentList listeRechercheClasse = reponseRechercheClasse.getResults();

							if(listeRechercheClasse.size() > 0) {
								SolrDocument docClasse = listeRechercheClasse.get(0);
								String entiteAttribuerNomCanonique = (String)docClasse.get("classeNomCanonique_" + classeLangueNom + "_stored_string");

								SolrQuery rechercheSolrVar = new SolrQuery();   
								rechercheSolrVar.setQuery("*:*");
								rechercheSolrVar.setRows(1);
								rechercheSolrVar.addFilterQuery("classeNomCanonique_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerNomCanonique));
								rechercheSolrVar.addFilterQuery("entiteVar_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(entiteAttribuerVar));
								rechercheSolrVar.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
								rechercheSolrVar.addFilterQuery("partEstEntite_indexed_boolean:true");
								QueryResponse reponseRechercheVar = clientSolrComputate.query(rechercheSolrVar);
								SolrDocumentList listeRechercheVar = reponseRechercheVar.getResults();

								if(listeRechercheVar.size() > 0) {
									SolrDocument docEntite = listeRechercheClasse.get(0);

									indexerStockerSolr(entiteDoc, "entiteAttribuer", true);
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerNomSimple", entiteAttribuerNomSimple);
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerNomCanonique", entiteAttribuerNomCanonique);
									indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAttribuerVar", entiteAttribuerVar);

									if(classeTraduire) {
										for(String langueNom : classeAutresLangues) {  
											String entiteAttribuerNomCanoniqueLangue = (String)docClasse.get("classeNomCanonique_" + langueNom + "_stored_string");
											String entiteAttribuerNomSimpleLangue = (String)docClasse.get("classeNomCanonique_" + langueNom + "_stored_string");
											String entiteAttribuerVarLangue = (String)docEntite.get("entiteVar_" + langueNom + "_stored_string");
	
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerNomSimple", entiteAttribuerNomSimpleLangue);
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerNomCanonique", entiteAttribuerNomCanoniqueLangue);
											indexerStockerSolr(langueNom, entiteDoc, "entiteAttribuerVar", entiteAttribuerVarLangue);
										}
									}
								}
							}
						}
						
						for(JavaAnnotation annotation : annotations) {
							String entiteAnnotationLangue = indexerStockerSolr(classeLangueNom, entiteDoc, "entiteAnnotations", annotation.getType().getCanonicalName());
						}
	
						String entiteCle = classeCheminAbsolu + "." + entiteVar;
		
						// Entites Solr du entite. 
		
						entiteDoc.addField("id", entiteCle);
						indexerStockerSolr(entiteDoc, "partEstEntite", true);
						indexerStockerSolr(entiteDoc, "partNumero", partNumero);

						String entiteCodeSource = methodeQdox.getSourceCode();
						String entiteString = regex("^(entite)?String\\." + classeLangueNom + ":(.*)", methodeCommentaire);
						if(entiteString != null) {
							entiteCodeSource = "\n\t\tc.o(\"" + StringUtils.replace(StringUtils.replace(entiteString, "\\", "\\\\"), "\"", "\\\"") + "\");\n\t";
							indexerStockerSolr(classeLangueNom, entiteDoc, "entiteString", entiteString); 
						}
						stockerSolr(classeLangueNom, entiteDoc, "entiteCodeSource", entiteCodeSource); 

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
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", classeLangueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneOffset", classeLangueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.LocalDateTime", classeLangueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZonedDateTime", classeLangueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, VAL_nomCanoniqueDate, classeLangueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", classeLangueNom));
							classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", classeLangueNom));
							classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.util.Locale", classeLangueNom));
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLocalDate)) {
							entiteNomSimpleVertxJson = "Instant";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueInstant;
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneId", classeLangueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.ZoneOffset", classeLangueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.LocalDate", classeLangueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, VAL_nomCanoniqueDate, classeLangueNom));
							classePartsGenAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", classeLangueNom));
							classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.time.format.DateTimeFormatter", classeLangueNom));
							classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.util.Locale", classeLangueNom));
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueLong)) {
							entiteNomSimpleVertxJson = "Long";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueLong;
						}
						else if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueBigDecimal)) {
							entiteNomSimpleVertxJson = "Double";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueLong;
							classePartsGenAjouter(ClasseParts.initClasseParts(this, NumberUtils.class.getCanonicalName(), classeLangueNom));
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
								classePartsGenAjouter(ClasseParts.initClasseParts(this, NumberUtils.class.getCanonicalName(), classeLangueNom));
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
							else if(StringUtils.equalsAny(entiteNomCanoniqueGenerique, VAL_nomCanoniqueString)) {
								entiteNomSimpleVertxJson = "JsonArray";
								entiteNomCanoniqueVertxJson = VAL_nomCanoniqueVertxJsonArray;
								entiteListeNomSimpleVertxJson = "String";
								entiteListeNomCanoniqueVertxJson = VAL_nomCanoniqueString;
							}
							stockerSolr(entiteDoc, "entiteListeNomSimpleVertxJson", entiteListeNomSimpleVertxJson);
							stockerSolr(entiteDoc, "entiteListeNomCanoniqueVertxJson", entiteListeNomCanoniqueVertxJson);
							classePartsGenAjouter(ClasseParts.initClasseParts(this, entiteListeNomCanoniqueVertxJson, classeLangueNom));
						}
						else if(classePartsChaine != null && StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueString, classePartsChaine.nomCanonique)) {
							entiteNomSimpleVertxJson = "String";
							entiteNomCanoniqueVertxJson = VAL_nomCanoniqueString;
						}
						classePartsGenAjouter(ClasseParts.initClasseParts(this, entiteNomCanoniqueVertxJson, classeLangueNom));
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

						if(entiteClePrimaire) {
							classeVarClePrimaire = stockerSolr(classeLangueNom, classeDoc, "classeVarClePrimaire", entiteVar);
						}
						if(entiteCleUnique) {
							classeVarCleUnique = stockerSolr(classeLangueNom, classeDoc, "classeVarCleUnique", entiteVar);
						}
				
						if(classeTraduire) {
							for(String langueNom : classeAutresLangues) {  
								ClasseParts entiteClassePartsLangue = ClasseParts.initClasseParts(this, entiteClasseParts, langueNom);
					
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomCanonique", entiteClassePartsLangue.nomCanonique); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomSimple", entiteClassePartsLangue.nomSimple); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomCanoniqueComplet", entiteClassePartsLangue.nomCanoniqueComplet); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomSimpleComplet", entiteClassePartsLangue.nomSimpleComplet); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomCanoniqueGenerique", entiteClassePartsLangue.nomCanoniqueGenerique); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomSimpleGenerique", entiteClassePartsLangue.nomSimpleGenerique); 
								indexerStockerSolr(langueNom, entiteDoc, "entiteNomSimpleCompletGenerique", entiteClassePartsLangue.nomSimpleGenerique); 
	
								indexerStockerSolr(langueNom, entiteDoc, "entiteVarParam", entiteVarParam); 
	
								String entiteVarLangue = regex("^(entite)?Var\\." + langueNom + ": (.*)", methodeCommentaire);
								entiteVarLangue = indexerStockerSolr(langueNom, entiteDoc, "entiteVar", entiteVarLangue == null ? entiteVar : entiteVarLangue);
								indexerStockerSolr(langueNom, entiteDoc, "entiteVarCapitalise", StringUtils.capitalize(entiteVarLangue));
								indexerStockerListeSolr(langueNom, classeDoc, "classeEntiteVars", entiteVarLangue);
								if(entiteClePrimaire) {
									stockerSolr(langueNom, classeDoc, "classeVarClePrimaire", entiteVarLangue);
								}
								if(entiteCleUnique) {
									stockerSolr(langueNom, classeDoc, "classeVarCleUnique", entiteVarLangue);
								}
		
								String entiteCodeSourceLangue = entiteCodeSource;
								entiteCodeSourceLangue = regexRemplacerTout(methodeCommentaire, entiteCodeSource, langueNom);
								String entiteStringLangue = regex("^(entite)?String\\." + langueNom + ":(.*)", methodeCommentaire);
								if(entiteStringLangue != null) {
									entiteCodeSourceLangue = "\n\t\tc.o(\"" + StringUtils.replace(StringUtils.replace(entiteStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\");\n\t";
									indexerStockerSolr(langueNom, entiteDoc, "entiteString", entiteStringLangue); 
								}
								stockerSolr(langueNom, entiteDoc, "entiteCodeSource", entiteCodeSourceLangue); 
		
								stockerRegexCommentaires(langueNom, entiteDoc, "entiteCommentaire", methodeCommentaire);
							}
						}

						for(JavaClass methodeExceptionQdox : methodeExceptionsQdox) { 
							String methodeExceptionNomSimpleComplet = StringUtils.substringAfterLast(methodeExceptionQdox.getCanonicalName(), ".");
							ClasseParts methodeExceptionClasseParts = ClasseParts.initClasseParts(this, methodeExceptionQdox.getCanonicalName(), classeLangueNom);
							if(!classeInitLoinExceptions.contains(methodeExceptionClasseParts.nomCanoniqueComplet))
								classeInitLoinExceptions.add(methodeExceptionClasseParts.nomCanoniqueComplet);
							stockerListeSolr(entiteDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionNomSimpleComplet);
							classePartsGenAjouter(methodeExceptionClasseParts);
							if(classeTraduire) {
								for(String langueNom : classeAutresLangues) {  
									ClasseParts methodeExceptionClassePartsLangue = ClasseParts.initClasseParts(this, methodeExceptionQdox.getCanonicalName(), langueNom);
									stockerListeSolr(entiteDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionClassePartsLangue.nomSimpleComplet);
								}
							}
						}

						clientSolrComputate.add(entiteDoc); 
					}
					else {  
						// est Methode. 
						
						SolrInputDocument methodeDoc = classeDocClone.deepCopy();
						indexerStockerSolr(classeLangueNom, methodeDoc, "methodeVar", methodeVar);
						indexerStockerListeSolr(classeLangueNom, classeDoc, "classeMethodeVars", methodeVar);
						for(Integer methodeParamNum = 1; methodeParamNum <= methodeParamsQdox.size(); methodeParamNum++) {
							JavaParameter methodeParamQdox = methodeParamsQdox.get(methodeParamNum - 1);
							String methodeParamVar = methodeParamQdox.getName();
							stockerListeSolr(classeLangueNom, methodeDoc, "methodeParamsVar", methodeParamVar);
							ClasseParts methodeParamsClassePart = ClasseParts.initClasseParts(this, methodeParamQdox.getJavaClass(), classeLangueNom);
							stockerListeSolr(classeLangueNom, methodeDoc, "methodeParamsNomCanonique", methodeParamsClassePart.nomCanonique);
							stockerListeSolr(classeLangueNom, methodeDoc, "methodeParamsNomSimpleComplet", methodeParamsClassePart.nomSimpleComplet);
							stockerListeSolr(methodeDoc, "methodeParamsArgsVariables", methodeParamQdox.isVarArgs());
							if(classeTraduire) {
								for(String langueNom : classeAutresLangues) { 
									String methodeParamVarLangue = regex("^(methode)?Param" + methodeParamNum + "\\.var\\." + langueNom + ": (.*)", methodeCommentaire);
									if(methodeParamVarLangue == null)
										methodeParamVarLangue = methodeParamVar;
									ClasseParts methodeParamClassePartsLangue = ClasseParts.initClasseParts(this, methodeParamsClassePart, langueNom);
	
									stockerListeSolr(langueNom, methodeDoc, "methodeParamsNomCanonique", methodeParamClassePartsLangue.nomCanonique);
									stockerListeSolr(langueNom, methodeDoc, "methodeParamsNomSimpleComplet", methodeParamClassePartsLangue.nomSimpleComplet);
									stockerListeSolr(langueNom, methodeDoc, "methodeParamsVar", methodeParamVarLangue);
								}  
							}
						}

						List<JavaTypeVariable<JavaGenericDeclaration>> methodeParamsType = methodeQdox.getTypeParameters();
						for(JavaTypeVariable<JavaGenericDeclaration> methodeParamType : methodeParamsType) {
							String methodeParamTypeNom = methodeParamType.getName();
							stockerListeSolr(methodeDoc, "methodeParamsTypeNom", methodeParamTypeNom);
						}

						for(JavaAnnotation annotation : annotations) {
							ClasseParts methodeAnnotationClasseParts = ClasseParts.initClasseParts(this, annotation.getType(), classeLangueNom);
							stockerListeSolr(classeLangueNom, methodeDoc, "methodeAnnotationsNomSimpleComplet", methodeAnnotationClasseParts.nomSimpleComplet);
							stockerListeSolr(classeLangueNom, methodeDoc, "methodeAnnotationsBlocCode", StringUtils.substringAfter(annotation.toString(), methodeAnnotationClasseParts.nomSimple));
							if(classeTraduire) {
								for(String langueNom : classeAutresLangues) {  
									ClasseParts methodeAnnotationClassePartsLangue = ClasseParts.initClasseParts(this, methodeAnnotationClasseParts, langueNom);
									stockerListeSolr(langueNom, methodeDoc, "methodeAnnotationsNomSimpleComplet", methodeAnnotationClassePartsLangue.nomSimpleComplet);
									stockerListeSolr(langueNom, methodeDoc, "methodeAnnotationsBlocCode", StringUtils.substringAfter(annotation.toString(), methodeAnnotationClasseParts.nomSimple));
								}
							}
						}

						for(JavaClass methodeExceptionQdox : methodeExceptionsQdox) { 
							String methodeExceptionNomSimpleComplet = StringUtils.substringAfterLast(methodeExceptionQdox.getCanonicalName(), ".");
							stockerListeSolr(methodeDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionNomSimpleComplet);
							if(classeTraduire) {
								for(String langueNom : classeAutresLangues) {  
									ClasseParts methodeExceptionClassePartsLangue = ClasseParts.initClasseParts(this, methodeExceptionQdox.getCanonicalName(), langueNom);
									stockerListeSolr(methodeDoc, "methodeExceptionsNomSimpleComplet", methodeExceptionClassePartsLangue.nomSimpleComplet);
								}
							}
						}

						Boolean methodeEstVide = false;
						if(classeQdoxRetour != null && !classeQdoxRetour.getCanonicalName().equals("void")) {
							ClasseParts methodeRetourClasseParts = ClasseParts.initClasseParts(this, methodeQdox.getReturns(), classeLangueNom);
							stockerSolr(classeLangueNom, methodeDoc, "methodeRetourNomSimpleComplet", methodeRetourClasseParts.nomSimpleComplet);
							if(classeTraduire) {
								for(String langueNom : classeAutresLangues) { 
									ClasseParts methodeRetourClassePartsLangue = ClasseParts.initClasseParts(this, methodeRetourClasseParts, langueNom);
									stockerSolr(langueNom, methodeDoc, "methodeRetourNomSimpleComplet", methodeRetourClassePartsLangue.nomSimpleComplet);
								}
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
						stockerRegexCommentaires(classeLangueNom, methodeDoc, "methodeCommentaire", methodeCommentaire);
	
						String methodeVarLangue = regex("^(methode)?Var\\." + classeLangueNom + ": (.*)", methodeCommentaire);
						methodeVarLangue =  methodeVarLangue == null ? methodeVar : methodeVarLangue;
	
						regexListe("^" + classeLangueNom + ":\\s*([^\n]+)", methodeCommentaire);

						String methodeCodeSource = methodeQdox.getSourceCode();
						String methodeCodeSourceLangue = methodeCodeSource;
						String methodeString = regex("^(methode)?String\\." + classeLangueNom + ":(.*)", methodeCommentaire);
						if(methodeString != null) {
							methodeCodeSource = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodeString, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
							indexerStockerSolr(classeLangueNom, methodeDoc, "methodeString", methodeString); 
						}
						stockerSolr(classeLangueNom, methodeDoc, "methodeCodeSource", methodeCodeSource);

						if(classeTraduire) {
							for(String langueNom : classeAutresLangues) {  
								methodeVarLangue = regex("^(methode)?Var\\." + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
								methodeVarLangue = indexerStockerSolr(langueNom, methodeDoc, "methodeVar", methodeVarLangue == null ? methodeVar : methodeVarLangue);
								regexListe("^" + langueNom + ":\\s*([^\n]+)", methodeCommentaire);
								methodeCodeSourceLangue = regexRemplacerTout(methodeCommentaire, methodeCodeSource, langueNom);
								indexerStockerListeSolr(langueNom, classeDoc, "classeMethodeVars", methodeVarLangue);
								String methodeStringLangue = regex("^(methode)?String\\." + langueNom + ":(.*)", methodeCommentaire);
								if(methodeStringLangue != null) {
									methodeCodeSourceLangue = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodeStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
									indexerStockerSolr(langueNom, methodeDoc, "methodeString", methodeStringLangue); 
								}
								stockerSolr(langueNom, methodeDoc, "methodeCodeSource", methodeCodeSourceLangue);
								stockerRegexCommentaires(langueNom, methodeDoc, "methodeCommentaire", methodeCommentaire);
							} 
						}
	
						clientSolrComputate.add(methodeDoc); 
					}
				}
			}
		}

		if(classeVarClePrimaire == null && classeSuperDoc != null) {
			classeVarClePrimaire = (String)classeSuperDoc.get("classeVarClePrimaire_" + classeLangueNom + "_stored_string");
			if(classeVarClePrimaire != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarClePrimaire", classeVarClePrimaire);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarClePrimaireLangue = (String)classeSuperDoc.get("classeVarClePrimaire_" + langueNom + "_stored_string");
						if(classeVarClePrimaireLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarClePrimaire", classeVarClePrimaireLangue);
						}
					}
				}
			}
		}
		if(classeVarCleUnique == null && classeSuperDoc != null) {
			classeVarCleUnique = (String)classeSuperDoc.get("classeVarCleUnique_" + classeLangueNom + "_stored_string");
			if(classeVarCleUnique != null) {
				stockerSolr(classeLangueNom, classeDoc, "classeVarCleUnique", classeVarCleUnique);
				if(classeTraduire) {
					for(String langueNom : classeAutresLangues) {  
						String classeVarCleUniqueLangue = (String)classeSuperDoc.get("classeVarCleUnique_" + langueNom + "_stored_string");
						if(classeVarCleUniqueLangue != null) {
							stockerSolr(langueNom, classeDoc, "classeVarCleUnique", classeVarCleUniqueLangue);
						}
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
		
		ClasseParts classePartsCouverture = classePartsCouverture(nomEnsembleDomaine, classeLangueNom);
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
			String classeApiUri = indexerStockerSolrRegex(classeDoc, "classeApiUri", "ApiUri", classeCommentaire);
			String classeApiTag = indexerStockerSolrRegex(classeDoc, "classeApiTag", "ApiTag", classeCommentaire);

			for(String classeApiMethode : classeApiMethodes) {
				indexerStockerListeSolr(classeDoc, "classeApiMethodes", classeApiMethode); 

				String classeApiMethodeMethode;
				if(StringUtils.contains(classeApiMethode, "POST"))
					classeApiMethodeMethode = "POST";
				else if(StringUtils.contains(classeApiMethode, "PATCH"))
					classeApiMethodeMethode = "PATCH";
				else if(StringUtils.contains(classeApiMethode, "DELETE"))
					classeApiMethodeMethode = "DELETE";
				else if(StringUtils.contains(classeApiMethode, "PUT"))
					classeApiMethodeMethode = "PUT";
				else
					classeApiMethodeMethode = "GET";

				indexerStockerSolr(classeDoc, "classeApiMethode" + classeApiMethode, regex("^(classe)?ApiMethode" + classeApiMethode + ":\\s*(.*)", classeCommentaire, classeApiMethodeMethode));

				String classeApiUriMethode = regexLangue(classeLangueNom, "(classe)?ApiUri" + classeApiMethode, classeCommentaire);

				indexerStockerSolrRegex(classeDoc, "classeApiOperationId" + classeApiMethode, "ApiOperationId" + classeApiMethode, classeCommentaire, StringUtils.lowerCase(classeApiMethode) + classeNomSimple);
				indexerStockerSolrRegex(classeDoc, "classeApiOperationId" + classeApiMethode + "Requete", "ApiOperationId" + classeApiMethode + "Requete", classeCommentaire, classeApiMethode + classeNomSimple + "Requete");
				indexerStockerSolrRegex(classeDoc, "classeApiOperationId" + classeApiMethode + "Reponse", "ApiOperationId" + classeApiMethode + "Reponse", classeCommentaire, classeApiMethode + classeNomSimple + "Reponse");
				indexerStockerSolrRegex(classeDoc, "classeApiDescription" + classeApiMethode, "ApiDescription" + classeApiMethode, classeCommentaire, regexLangue(classeLangueNom, "(classe)?Description" + classeApiMethode + "", classeCommentaire));

				if(classeEtendBase && classeSuperDoc != null) {
					indexerStockerSolr(classeDoc, "classeSuperApiOperationId" + classeApiMethode, (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "_stored_string"));
					indexerStockerSolr(classeDoc, "classeSuperApiOperationId" + classeApiMethode + "Requete", (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "Requete" + "_stored_string"));
					indexerStockerSolr(classeDoc, "classeSuperApiOperationId" + classeApiMethode + "Reponse", (String)classeSuperDoc.get("classeApiOperationId" + classeApiMethode + "Reponse" + "_stored_string"));
				}

				String classePageNomSimpleMethode = regex("^(classe)?Page" + classeApiMethode + ":\\s*(.*)", classeCommentaire);
				String classePageSuperNomSimpleMethode = regex("^(classe)?PageSuper" + classeApiMethode + ":\\s*(.*)", classeCommentaire);
				String classeApiTypeMedia200Methode = regex("^(classe)?ApiTypeMedia200" + classeApiMethode + ":\\s*(.*)", classeCommentaire, classePageNomSimpleMethode == null ? "application/json" : "text/html");
				String classeApiMotCleMethode = regexLangue(classeLangueNom, "(classe)?ApiMotCle" + classeApiMethode, classeCommentaire);
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
						classeApiUriMethode = classeApiUri + "/{id}";
				}
				if(this.langueNom.equals(classeLangueNom))
					indexerStockerSolr(classeDoc, "classeApiTypeMedia200" + classeApiMethode, classeApiTypeMedia200Methode);
				indexerStockerSolr(classeDoc, "classeApiMotCle" + classeApiMethode, classeApiMotCleMethode);
				indexerStockerSolr(classeDoc, "classeApiUri" + classeApiMethode, classeApiUriMethode);
				if(classePageNomSimpleMethode != null) {
					String classePageLangueNom = null;

					if(classeTraduire) {
						for(String langueNom : toutesLangues) {
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
								String classeNomEnsembleLangue = (String)classeDoc.get("classeNomEnsemble_" + langueNom + "_indexed_string").getValue();
								String classePageNomCanoniqueMethode = (String)docEntite.get("classeNomCanonique_" + langueNom + "_stored_string");
								indexerStockerSolr(classeDoc, "classePageNomCanonique" + classeApiMethode, classePageNomCanoniqueMethode);
								indexerStockerSolr(classeDoc, "classePageNomSimple" + classeApiMethode, classePageNomSimpleMethode);
								classePartsGenApiAjouter(ClasseParts.initClasseParts(this, classePageNomCanoniqueMethode, langueNom));
	
								String classeGenPageNomSimple;
								String classePageCheminGen;
								if(StringUtils.contains(classePageNomSimpleMethode, "Page")) {
									classeGenPageNomSimple = StringUtils.substringBeforeLast(classePageNomSimpleMethode, "Page") + "GenPage" + StringUtils.substringAfterLast(classePageNomSimpleMethode, "Page");
									classePageCheminGen = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
								}
								else {
									classeGenPageNomSimple = "Gen" + classePageNomSimpleMethode;
									classePageCheminGen = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
								}
								indexerStockerSolr(classeDoc, "classeGenPageNomSimple" + classeApiMethode, classeGenPageNomSimple);
								String classePageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classePageNomSimpleMethode, ".java");
								indexerStockerSolr(classeDoc, "classePageCheminGen" + classeApiMethode, classePageCheminGen); 
								indexerStockerSolr(classeDoc, "classePageChemin" + classeApiMethode, classePageChemin); 
								classePageLangueNom = langueNom;
								break;
							}
						}
					}
					if(classePageLangueNom == null) {

						if(classeTraduire) {
							for(String langueNom : toutesLangues) {
								if(StringUtils.containsIgnoreCase(classePageNomSimpleMethode, langueNom)) {
									String classeNomEnsembleLangue = (String)classeDoc.get("classeNomEnsemble_" + langueNom + "_indexed_string").getValue();
	
									String classeGenPageNomSimple;
									String classePageCheminGen;
									String classePageChemin;
									if(StringUtils.contains(classePageNomSimpleMethode, "Page")) {
										classeGenPageNomSimple = StringUtils.substringBeforeLast(classePageNomSimpleMethode, "Page") + "GenPage" + StringUtils.substringAfterLast(classePageNomSimpleMethode, "Page");
										classePageCheminGen = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
										classePageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classePageNomSimpleMethode, ".java");
									}
									else {
										classeGenPageNomSimple = "Gen" + classePageNomSimpleMethode;
										classePageCheminGen = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classeGenPageNomSimple, ".java");
										classePageChemin = concat(cheminSrcMainJava, "/", StringUtils.replace(classeNomEnsembleLangue, ".", "/"), "/", classePageNomSimpleMethode, ".java");
									}
									indexerStockerSolr(classeDoc, "classeGenPageNomSimple" + classeApiMethode, classeGenPageNomSimple);
									indexerStockerSolr(classeDoc, "classePageCheminGen" + classeApiMethode, classePageCheminGen); 
									indexerStockerSolr(classeDoc, "classePageChemin" + classeApiMethode, classePageChemin); 
									classePageLangueNom = langueNom;
									break;
								}
							}
						}
						if(classePageLangueNom != null) {
							String classePageNomCanoniqueMethode = classeNomEnsemble + "." + classePageNomSimpleMethode;
							
							indexerStockerSolr(classeDoc, "classePageNomCanonique" + classeApiMethode, classePageNomCanoniqueMethode);
							indexerStockerSolr(classeDoc, "classePageNomSimple" + classeApiMethode, classePageNomSimpleMethode);
							classePartsGenApiAjouter(ClasseParts.initClasseParts(this, classePageNomCanoniqueMethode, classePageLangueNom));
						}
					}

					if(classePageLangueNom != null) {
						if(classePageSuperNomSimpleMethode != null) {
							SolrQuery recherchePage = new SolrQuery();   
							recherchePage.setQuery("*:*");
							recherchePage.setRows(1);
							recherchePage.addFilterQuery("classeNomSimple_" + classePageLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classePageSuperNomSimpleMethode));
							recherchePage.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(nomEnsembleDomaine));
							recherchePage.addFilterQuery("partEstClasse_indexed_boolean:true");
							QueryResponse reponseRecherchePage = clientSolrComputate.query(recherchePage);
							SolrDocumentList listeRecherchePage = reponseRecherchePage.getResults();
		
							if(listeRecherchePage.size() > 0) {
								SolrDocument docEntite = listeRecherchePage.get(0);
								String classePageSuperNomCanoniqueMethode = (String)docEntite.get("classeNomCanonique_" + classePageLangueNom + "_stored_string");
								indexerStockerSolr(classeDoc, "classePageSuperNomCanonique" + classeApiMethode, classePageSuperNomCanoniqueMethode);
								indexerStockerSolr(classeDoc, "classePageSuperNomSimple" + classeApiMethode, classePageSuperNomSimpleMethode);
								classePartsGenPageAjouter(ClasseParts.initClasseParts(this, classePageSuperNomCanoniqueMethode, classePageLangueNom));
							}
							else {
								indexerStockerSolr(classeDoc, "classePageSuperNomCanonique" + classeApiMethode, (String)classePartsMiseEnPage.documentSolr.get("classeNomCanonique_" + classePageLangueNom + "_stored_string"));
								indexerStockerSolr(classeDoc, "classePageSuperNomSimple" + classeApiMethode, (String)classePartsMiseEnPage.documentSolr.get("classeNomSimple_" + classePageLangueNom + "_stored_string"));
								classePartsGenPageAjouter(classePartsMiseEnPage);
							}
						}
						else {
							indexerStockerSolr(classeDoc, "classePageSuperNomCanonique" + classeApiMethode, (String)classePartsMiseEnPage.documentSolr.get("classeNomCanonique_" + classePageLangueNom + "_stored_string"));
							indexerStockerSolr(classeDoc, "classePageSuperNomSimple" + classeApiMethode, (String)classePartsMiseEnPage.documentSolr.get("classeNomSimple_" + classePageLangueNom + "_stored_string"));
							classePartsGenPageAjouter(classePartsMiseEnPage);
						}

						String classePageCheminCss = concat(appliChemin, "-static/css/", classePageNomSimpleMethode, ".css");
						String classePageCheminJs = concat(appliChemin, "-static/js/", classePageNomSimpleMethode, ".js");
			
						indexerStockerSolr(classeDoc, "classePageCheminCss" + classeApiMethode, classePageCheminCss); 
						indexerStockerSolr(classeDoc, "classePageCheminJs" + classeApiMethode, classePageCheminJs); 
						indexerStockerSolr(classeDoc, "classePageLangueNom" + classeApiMethode, classePageLangueNom); 
						classePage = true;
					}
					else {
						indexerStockerSolr(classeDoc, "classePageSuperNomCanonique" + classeApiMethode, (String)classePartsMiseEnPage.documentSolr.get("classeNomCanonique_" + classePageLangueNom + "_stored_string"));
						indexerStockerSolr(classeDoc, "classePageSuperNomSimple" + classeApiMethode, (String)classePartsMiseEnPage.documentSolr.get("classeNomSimple_" + classePageLangueNom + "_stored_string"));
						classePartsGenPageAjouter(classePartsMiseEnPage);
					}
				}
			}
		}

		if(classePage) {
			classePartsGenPageAjouter(classePartsConfigSite);
			classePartsGenPageAjouter(classePartsRequeteSite);
			classePartsGenPageAjouter(classePartsSiteContexte);
			classePartsGenPageAjouter(classePartsUtilisateurSite);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.io.IOException", classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.HttpServerRequest", classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.http.HttpServerResponse", classeLangueNom));
			classePartsGenPageAjouter(classePartsListeRecherche);
			classePartsGenPageAjouter(classePartsCouverture);
			classePartsGenPageAjouter(classePartsMiseEnPage);
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, LocalDateTime.class.getCanonicalName(), classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, LocalDate.class.getCanonicalName(), classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, ZonedDateTime.class.getCanonicalName(), classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, DateTimeFormatter.class.getCanonicalName(), classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Locale.class.getCanonicalName(), classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonObject", classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.ext.web.api.OperationRequest", classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "io.vertx.core.json.JsonArray", classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "java.net.URLDecoder", classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, StringUtils.class.getCanonicalName(), classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, Map.class.getCanonicalName(), classeLangueNom));
			classePartsGenPageAjouter(ClasseParts.initClasseParts(this, List.class.getCanonicalName(), classeLangueNom));
		}

		for(ClasseParts classePartGenPage : classePartsGenPage.values()) {
			if(classePartGenPage.langueNom == null || classePartGenPage.langueNom.equals(langueNom))
				indexerStockerListeSolr(classeLangueNom, classeDoc, "classeImportationsGenPage", classePartGenPage.nomCanonique);
			if(classeTraduire) {
				for(String langueNom : classeAutresLangues) {  
					ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGenPage, langueNom);
					if(classeImportationClassePartsLangue.langueNom == null || classeImportationClassePartsLangue.langueNom.equals(langueNom))
						indexerStockerListeSolr(langueNom, classeDoc, "classeImportationsGenPage", classeImportationClassePartsLangue.nomCanonique);
				}
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

			for(String langueNom : toutesLangues) {

				contexteVideoId = regexLangue(langueNom, "(contexte)?VideoId", classeCommentaire);
				if(contexteVideoId != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteVideoId", contexteVideoId); 

				contexteDescription = regexLangue(langueNom, "(contexte)?Description", classeCommentaire);
				if(contexteDescription != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteDescription", contexteDescription); 

				String contexteImageLargeurStr = regexLangue(langueNom, "^(contexte)?ImageLargeur", classeCommentaire);
				if(NumberUtils.isCreatable(contexteImageLargeurStr))
					indexerStockerSolr(langueNom, classeDoc, "contexteImageLargeur", Integer.parseInt(contexteImageLargeurStr));

				String contexteImageHauteurStr = regexLangue(langueNom, "^(contexte)?ImageHauteur", classeCommentaire);
				if(NumberUtils.isCreatable(contexteImageHauteurStr))
					indexerStockerSolr(langueNom, classeDoc, "contexteImageHauteur", Integer.parseInt(contexteImageHauteurStr));
					
				contexteUnNom = regexLangue(langueNom, "(contexte)?UnNom", classeCommentaire);
				if(contexteUnNom != null) {
					if("frFR".equals(langueNom)) {
						indexerStockerSolr(langueNom, classeDoc, "contexteUnNom", contexteUnNom); 
						contexteNomSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomSingulier", regexLangue(langueNom, "(contexte)?NomSingulier", classeCommentaire, StringUtils.substringAfter(contexteUnNom, " ")));
						contexteNomPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomPluriel", regexLangue(langueNom, "(contexte)?NomPluriel", classeCommentaire, contexteNomSingulier + "s"));
						contexteNomVar = indexerStockerSolr(langueNom, classeDoc, "contexteNomVar", regexLangue(langueNom, "(contexte)?NomVar", classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contexteNomSingulier, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
						contexteLesNoms = indexerStockerSolr(langueNom, classeDoc, "contexteLesNoms", regexLangue(langueNom, "(contexte)?LesNom", classeCommentaire, CONTEXTE_frFR_LesPluriel + contexteNomPluriel));
		
						contexteAdjectif = regexLangue(langueNom, "(contexte)?Adjectif", classeCommentaire);
						if(contexteAdjectif != null) {
							contexteAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteAdjectifPluriel", regexLangue(langueNom, "(contexte)?AdjectifPluriel", classeCommentaire, contexteAdjectif + CONTEXTE_frFR_AdjectifPluriel));
							contexteAdjectifVar = indexerStockerSolr(langueNom, classeDoc, "contexteAdjectifVar", regexLangue(langueNom, "(contexte)?AdjectifVar", classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contexteAdjectif, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
							if(CONTEXTE_frFR_AdjectifAvant) {
								contexteNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifSingulier", regexLangue(langueNom, "(contexte)?NomAdjectifSingulier", classeCommentaire, contexteAdjectif + " " + contexteNomSingulier));
								contexteNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifPluriel", regexLangue(langueNom, "(contexte)?NomAdjectifPluriel", classeCommentaire, contexteAdjectifPluriel + " " + contexteNomPluriel));
							}
							else {
								contexteNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifSingulier", regexLangue(langueNom, "(contexte)?NomAdjectifSingulier", classeCommentaire, contexteNomSingulier + " " + contexteAdjectif));
								contexteNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomSingulierAdjectifPluriel", regexLangue(langueNom, "(contexte)?NomSingulierAdjectifPluriel", classeCommentaire, contexteNomPluriel + " " + contexteAdjectifPluriel));
							}
		
						}
		
						if(contexteUnNom.startsWith(CONTEXTE_frFR_UneFeminin)) {
							contexteCe = indexerStockerSolr(langueNom, classeDoc, "contexteCe", regexLangue(langueNom, "(contexte)?Ce", classeCommentaire, CONTEXTE_frFR_CetteFemininConsonne));
							contexteUn = indexerStockerSolr(langueNom, classeDoc, "contexteUn", regexLangue(langueNom, "(contexte)?Un", classeCommentaire, CONTEXTE_frFR_UneFeminin));
							contexteCree = indexerStockerSolr(langueNom, classeDoc, "contexteCree", regexLangue(langueNom, "(contexte)?Cree", classeCommentaire, CONTEXTE_frFR_CreeeFeminin));
							contexteModifie = indexerStockerSolr(langueNom, classeDoc, "contexteModifie", regexLangue(langueNom, "(contexte)?Modifie", classeCommentaire, CONTEXTE_frFR_ModifieeFeminin));
							contexteNomActuel = indexerStockerSolr(langueNom, classeDoc, "contexteNomActuel", regexLangue(langueNom, "(contexte)?NomActuel", classeCommentaire, CONTEXTE_frFR_ActuelleFemininAvant + contexteNomSingulier + CONTEXTE_frFR_ActuelleFemininApres));
							contexteTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteTousNom", regexLangue(langueNom, "(contexte)?TousNom", classeCommentaire, CONTEXTE_frFR_LesPluriel + contexteNomPluriel));
							contexteRechercherTousNomPar = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNomPar", regexLangue(langueNom, "(contexte)?RechercherTousNomPar", classeCommentaire, CONTEXTE_frFR_Rechercher + contexteNomPluriel + CONTEXTE_frFR_Par));
							contexteRechercherTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNom", regexLangue(langueNom, "(contexte)?RechercherTousNom", classeCommentaire, CONTEXTE_frFR_Rechercher + contexteNomPluriel));
							contexteAucunNomTrouve = indexerStockerSolr(langueNom, classeDoc, "contexteAucunNomTrouve", regexLangue(langueNom, "(contexte)?AucunNomTrouve", classeCommentaire, CONTEXTE_frFR_AucuneTrouveFemininAvant + contexteNomSingulier + CONTEXTE_frFR_AucuneTrouveFemininApres));
							if(contexteAdjectif != null) {
								if(CONTEXTE_frFR_AdjectifAvant)
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "(contexte)?UnNomAdjectif", classeCommentaire, CONTEXTE_frFR_UneFeminin + " " + contexteAdjectif + " " + contexteNomSingulier));
								else
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "(contexte)?UnNomAdjectif", classeCommentaire, CONTEXTE_frFR_UneFeminin + " " + contexteNomSingulier + " " + contexteAdjectif));
							}
		
							String suffixe = StringUtils.substringAfter(contexteUnNom, " ");
							String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
								contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "(contexte)?CeNom", classeCommentaire, CONTEXTE_frFR_CetteFemininVoyelle + suffixe));
								contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "(contexte)?LeNom", classeCommentaire, CONTEXTE_frFR_LFemininVoyelle + suffixe));
								contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "(contexte)?DeNom", classeCommentaire, CONTEXTE_frFR_DVoyelle + suffixe));
								if(contexteAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant)
										contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_frFR_LFemininVoyelle + " " + contexteAdjectif + " " + contexteNomSingulier));
									else
										contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_frFR_LFemininVoyelle + " " + contexteNomSingulier + " " + contexteAdjectif));
								}
							}
							else {
								contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "(contexte)?CeNom", classeCommentaire, CONTEXTE_frFR_CetteFemininConsonne + suffixe));
								contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "(contexte)?LeNom", classeCommentaire, CONTEXTE_frFR_LaFemininConsonne + suffixe));
								contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "(contexte)?DeNom", classeCommentaire, CONTEXTE_frFR_DeConsonne + suffixe));
								if(contexteAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant)
										contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_frFR_LaFemininConsonne + " " + contexteAdjectif + " " + contexteNomSingulier));
									else
										contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_frFR_LaFemininConsonne + " " + contexteNomSingulier + " " + contexteAdjectif));
								}
							}
						}
						else if(contexteUnNom.startsWith(CONTEXTE_frFR_UnMasculin)) {
							contexteCe = indexerStockerSolr(langueNom, classeDoc, "contexteCe", regexLangue(langueNom, "(contexte)?Ce", classeCommentaire, CONTEXTE_frFR_CeMasculinConsonne));
							contexteUn = indexerStockerSolr(langueNom, classeDoc, "contexteUn", regexLangue(langueNom, "(contexte)?Un", classeCommentaire, CONTEXTE_frFR_UnMasculin));
							contexteCree = indexerStockerSolr(langueNom, classeDoc, "contexteCree", regexLangue(langueNom, "(contexte)?Cree", classeCommentaire, CONTEXTE_frFR_CreeMasculin));
							contexteModifie = indexerStockerSolr(langueNom, classeDoc, "contexteModifie", regexLangue(langueNom, "(contexte)?Modifie", classeCommentaire, CONTEXTE_frFR_ModifieMasculin));
							contexteNomActuel = indexerStockerSolr(langueNom, classeDoc, "contexteNomActuel", regexLangue(langueNom, "(contexte)?NomActuel", classeCommentaire, CONTEXTE_frFR_ActuelMasculinAvant + contexteNomSingulier + CONTEXTE_frFR_ActuelMasculinApres));
							contexteTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteTousNom", regexLangue(langueNom, "(contexte)?TousNom", classeCommentaire, CONTEXTE_frFR_TousMasculinPluriel + contexteNomPluriel));
							contexteRechercherTousNomPar = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNomPar", regexLangue(langueNom, "(contexte)?RechercherTousNomPar", classeCommentaire, CONTEXTE_frFR_Rechercher + contexteNomPluriel + CONTEXTE_frFR_Par));
							contexteRechercherTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNom", regexLangue(langueNom, "(contexte)?RechercherTousNom", classeCommentaire, CONTEXTE_frFR_Rechercher + contexteNomPluriel));
							contexteAucunNomTrouve = indexerStockerSolr(langueNom, classeDoc, "contexteAucunNomTrouve", regexLangue(langueNom, "(contexte)?AucunNomTrouve", classeCommentaire, CONTEXTE_frFR_AucunTrouveMasculinAvant + contexteNomSingulier + CONTEXTE_frFR_AucunTrouveMasculinApres));
							if(contexteAdjectif != null) {
								if(CONTEXTE_frFR_AdjectifAvant)
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "(contexte)?UnNomAdjectif", classeCommentaire, CONTEXTE_frFR_UnMasculin + " " + contexteAdjectif + " " + contexteNomSingulier));
								else
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "(contexte)?UnNomAdjectif", classeCommentaire, CONTEXTE_frFR_UnMasculin + " " + contexteNomSingulier + " " + contexteAdjectif));
							}
		
							String suffixe = StringUtils.substringAfter(contexteUnNom, " ");
							String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
								contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "(contexte)?CeNom", classeCommentaire, CONTEXTE_frFR_CetMasculinVoyelle + suffixe));
								contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "(contexte)?LeNom", classeCommentaire, CONTEXTE_frFR_LMasculinVoyelle + suffixe));
								contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "(contexte)?DeNom", classeCommentaire, CONTEXTE_frFR_DVoyelle + suffixe));
								if(contexteAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant)
										contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_frFR_LMasculinVoyelle + " " + contexteAdjectif + " " + contexteNomSingulier));
									else
										contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_frFR_LMasculinVoyelle + " " + contexteNomSingulier + " " + contexteAdjectif));
								}
							}
							else {
								contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "(contexte)?CeNom", classeCommentaire, CONTEXTE_frFR_CeMasculinConsonne + suffixe));
								contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "(contexte)?LeNom", classeCommentaire, CONTEXTE_frFR_LeMasculinConsonne + suffixe));
								contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "(contexte)?DeNom", classeCommentaire, CONTEXTE_frFR_DeConsonne + suffixe));
								if(contexteAdjectif != null) {
									if(CONTEXTE_frFR_AdjectifAvant)
										contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_frFR_LeMasculinConsonne + " " + contexteAdjectif + " " + contexteNomSingulier));
									else
										contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_frFR_LeMasculinConsonne + " " + contexteNomSingulier + " " + contexteAdjectif));
								}
							}
						}
						indexerStockerSolr(langueNom, classeDoc, "contexteCeMinuscule", contexteCe); 
					}
					else if("enUS".equals(langueNom)) {
						indexerStockerSolr(langueNom, classeDoc, "contexteUnNom", contexteUnNom); 
						contexteNomSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomSingulier", regexLangue(langueNom, "(contexte)?NomSingulier", classeCommentaire, StringUtils.substringAfter(contexteUnNom, " ")));
						contexteNomPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomPluriel", regexLangue(langueNom, "(contexte)?NomPluriel", classeCommentaire, contexteNomSingulier + "s"));
						contexteNomVar = indexerStockerSolr(langueNom, classeDoc, "contexteNomVar", regexLangue(langueNom, "(contexte)?NomVar", classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contexteNomSingulier, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
						contexteLesNoms = indexerStockerSolr(langueNom, classeDoc, "contexteLesNoms", regexLangue(langueNom, "(contexte)?LesNom", classeCommentaire, CONTEXTE_enUS_LesPluriel + contexteNomPluriel));
		
						contexteAdjectif = regexLangue(langueNom, "(contexte)?Adjectif", classeCommentaire);
						if(contexteAdjectif != null) {
							contexteAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteAdjectifPluriel", regexLangue(langueNom, "(contexte)?AdjectifPluriel", classeCommentaire, contexteAdjectif + CONTEXTE_enUS_AdjectifPluriel));
							contexteAdjectifVar = indexerStockerSolr(langueNom, classeDoc, "contexteAdjectifVar", regexLangue(langueNom, "(contexte)?AdjectifVar", classeCommentaire, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contexteAdjectif, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
							if(CONTEXTE_enUS_AdjectifAvant) {
								contexteNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifSingulier", regexLangue(langueNom, "(contexte)?NomAdjectifSingulier", classeCommentaire, contexteAdjectif + " " + contexteNomSingulier));
								contexteNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifPluriel", regexLangue(langueNom, "(contexte)?NomAdjectifPluriel", classeCommentaire, contexteAdjectifPluriel + " " + contexteNomPluriel));
							}
							else {
								contexteNomAdjectifSingulier = indexerStockerSolr(langueNom, classeDoc, "contexteNomAdjectifSingulier", regexLangue(langueNom, "(contexte)?NomAdjectifSingulier", classeCommentaire, contexteNomSingulier + " " + contexteAdjectif));
								contexteNomAdjectifPluriel = indexerStockerSolr(langueNom, classeDoc, "contexteNomSingulierAdjectifPluriel", regexLangue(langueNom, "(contexte)?NomSingulierAdjectifPluriel", classeCommentaire, contexteNomPluriel + " " + contexteAdjectifPluriel));
							}
		
						}
		
						contexteCe = indexerStockerSolr(langueNom, classeDoc, "contexteCe", regexLangue(langueNom, "(contexte)?Ce", classeCommentaire, CONTEXTE_enUS_CetteConsonne));
						contexteUn = indexerStockerSolr(langueNom, classeDoc, "contexteUn", regexLangue(langueNom, "(contexte)?Un", classeCommentaire, CONTEXTE_enUS_Une));
						contexteCree = indexerStockerSolr(langueNom, classeDoc, "contexteCree", regexLangue(langueNom, "(contexte)?Cree", classeCommentaire, CONTEXTE_enUS_Creee));
						contexteModifie = indexerStockerSolr(langueNom, classeDoc, "contexteModifie", regexLangue(langueNom, "(contexte)?Modifie", classeCommentaire, CONTEXTE_enUS_Modifiee));
						contexteNomActuel = indexerStockerSolr(langueNom, classeDoc, "contexteNomActuel", regexLangue(langueNom, "(contexte)?NomActuel", classeCommentaire, CONTEXTE_enUS_ActuelleAvant + contexteNomSingulier + CONTEXTE_enUS_ActuelleApres));
						contexteTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteTousNom", regexLangue(langueNom, "(contexte)?TousNom", classeCommentaire, CONTEXTE_enUS_LesPluriel + contexteNomPluriel));
						contexteRechercherTousNomPar = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNomPar", regexLangue(langueNom, "(contexte)?RechercherTousNomPar", classeCommentaire, CONTEXTE_enUS_Rechercher + contexteNomPluriel + CONTEXTE_enUS_Par));
						contexteRechercherTousNom = indexerStockerSolr(langueNom, classeDoc, "contexteRechercherTousNomr", regexLangue(langueNom, "(contexte)?RechercherTousNom", classeCommentaire, CONTEXTE_enUS_Rechercher + contexteNomPluriel));
						contexteAucunNomTrouve = indexerStockerSolr(langueNom, classeDoc, "contexteAucunNomTrouve", regexLangue(langueNom, "(contexte)?AucunNomTrouve", classeCommentaire, CONTEXTE_enUS_AucuneTrouveAvant + contexteNomSingulier + CONTEXTE_enUS_AucuneTrouveApres));
						if(contexteAdjectif != null) {
							if(CONTEXTE_enUS_AdjectifAvant)
								contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "(contexte)?UnNomAdjectif", classeCommentaire, CONTEXTE_enUS_Une + " " + contexteAdjectif + " " + contexteNomSingulier));
							else
								contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteUnNomAdjectif", regexLangue(langueNom, "(contexte)?UnNomAdjectif", classeCommentaire, CONTEXTE_enUS_Une + " " + contexteNomSingulier + " " + contexteAdjectif));
						}
	
						String suffixe = StringUtils.substringAfter(contexteUnNom, " ");
						String c = Normalizer.normalize(StringUtils.substring(suffixe.toLowerCase(), 0, 1), Normalizer.Form.NFD);
						if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
							contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "(contexte)?CeNom", classeCommentaire, CONTEXTE_enUS_CetteVoyelle + suffixe));
							contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "(contexte)?LeNom", classeCommentaire, CONTEXTE_enUS_LVoyelle + suffixe));
							contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "(contexte)?DeNom", classeCommentaire, CONTEXTE_enUS_DVoyelle + suffixe));
							if(contexteAdjectif != null) {
								if(CONTEXTE_enUS_AdjectifAvant)
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_enUS_LVoyelle + " " + contexteAdjectif + " " + contexteNomSingulier));
								else
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_enUS_LVoyelle + " " + contexteNomSingulier + " " + contexteAdjectif));
							}
						}
						else {
							contexteCeNom = indexerStockerSolr(langueNom, classeDoc, "contexteCeNom", regexLangue(langueNom, "(contexte)?CeNom", classeCommentaire, CONTEXTE_enUS_CetteConsonne + suffixe));
							contexteLeNom = indexerStockerSolr(langueNom, classeDoc, "contexteLeNom", regexLangue(langueNom, "(contexte)?LeNom", classeCommentaire, CONTEXTE_enUS_LaConsonne + suffixe));
							contexteDeNom = indexerStockerSolr(langueNom, classeDoc, "contexteDeNom", regexLangue(langueNom, "(contexte)?DeNom", classeCommentaire, CONTEXTE_enUS_DeConsonne + suffixe));
							if(contexteAdjectif != null) {
								if(CONTEXTE_enUS_AdjectifAvant)
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_enUS_LaConsonne + " " + contexteAdjectif + " " + contexteNomSingulier));
								else
									contexteUnNomAdjectif = indexerStockerSolr(langueNom, classeDoc, "contexteLeNomAdjectif", regexLangue(langueNom, "(contexte)?LeNomAdjectif", classeCommentaire, CONTEXTE_enUS_LaConsonne + " " + contexteNomSingulier + " " + contexteAdjectif));
							}
						}
						indexerStockerSolr(langueNom, classeDoc, "contexteCeMinuscule", contexteCe); 
					}
				}
	
				contexteTitre = regexLangue(langueNom, "(contexte)?Titre", classeCommentaire);
				if(contexteTitre != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteTitre", contexteTitre); 
	
				contexteH1 = regexLangue(langueNom, "(contexte)?H1", classeCommentaire);
				if(contexteH1 != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteH1", contexteH1); 
	
				contexteH2 = regexLangue(langueNom, "(contexte)?H2", classeCommentaire);
				if(contexteH2 != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteH2", contexteH2); 
	
				contexteH3 = regexLangue(langueNom, "(contexte)?H3", classeCommentaire);
				if(contexteH3 != null)
					indexerStockerSolr(langueNom, classeDoc, "contexteH3", contexteH3); 
			}
		}

		Boolean classeIndexe = indexerStockerSolr(classeDoc, "classeIndexe", regexTrouve("^(classe)?Indexe:\\s*(true)$", classeCommentaire) || classeSauvegarde || classeModele || classePage);
		Boolean classeImage = indexerStockerSolr(classeDoc, "classeImage", regexTrouve("^(classe)?Image:\\s*(true)$", classeCommentaire));

		if(classeIndexe) {
			classePartsGenAjouter(classePartsSolrInputDocument);
			classePartsGenAjouter(classePartsSolrClient);
			classePartsGenAjouter(classePartsSiteContexte);
			classePartsGenAjouter(classePartsSolrDocument);
			classePartsGenAjouter(classePartsList);
			classePartsGenAjouter(classePartsArrayList);
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.SolrQuery", classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.response.QueryResponse", classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, "org.apache.solr.client.solrj.util.ClientUtils", classeLangueNom));
		}

		if(classeImage) {
			classePartsGenAjouter(ClasseParts.initClasseParts(this, DefaultExecutor.class.getCanonicalName(), classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, CommandLine.class.getCanonicalName(), classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, File.class.getCanonicalName(), classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, BufferedImage.class.getCanonicalName(), classeLangueNom));
			classePartsGenAjouter(ClasseParts.initClasseParts(this, ImageIO.class.getCanonicalName(), classeLangueNom));
		}

		for(ClasseParts classePartGen : classePartsGen.values()) {
			indexerStockerListeSolr(classeLangueNom, classeDoc, "classeImportationsGen", classePartGen.nomCanonique);
			for(String langueNom : classeAutresLangues) {  
				ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGen, langueNom);
				indexerStockerListeSolr(langueNom, classeDoc, "classeImportationsGen", classeImportationClassePartsLangue.nomCanonique);
			}
		}

		for(ClasseParts classePartGenApi : classePartsGenApi.values()) {
			if(classePartGenApi.langueNom == null || classePartGenApi.langueNom.equals(langueNom))
				indexerStockerListeSolr(classeLangueNom, classeDoc, "classeImportationsGenApi", classePartGenApi.nomCanonique);
			if(classeTraduire) {
				for(String langueNom : classeAutresLangues) {  
					ClasseParts classeImportationClassePartsLangue = ClasseParts.initClasseParts(this, classePartGenApi, langueNom);
					if(classeImportationClassePartsLangue.langueNom == null || classeImportationClassePartsLangue.langueNom.equals(langueNom))
						indexerStockerListeSolr(langueNom, classeDoc, "classeImportationsGenApi", classeImportationClassePartsLangue.nomCanonique);
				}
			}
		}

		if(classeSuperDoc != null) {
			for(String langueNom : toutesLangues) {
				List<String> classeSuperEntiteVars = (List<String>)classeSuperDoc.get("classeEntiteVars_" + langueNom + "_stored_strings");
				if(classeSuperEntiteVars != null) {
					for(String classeSuperEntiteVar : classeSuperEntiteVars)
						indexerStockerListeSolr(langueNom, classeDoc, "classeEntiteVars", classeSuperEntiteVar);
				}
			}
		}

		if(classeSuperDoc != null) {
			for(String langueNom : toutesLangues) {
				List<String> classeSuperMethodeVars = (List<String>)classeSuperDoc.get("classeMethodeVars_" + langueNom + "_stored_strings");
				if(classeSuperMethodeVars != null) {
					for(String classeSuperMethodeVar : classeSuperMethodeVars)
						indexerStockerListeSolr(langueNom, classeDoc, "classeMethodeVars", classeSuperMethodeVar);
				}
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
