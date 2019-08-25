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
import java.util.Optional;
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
import org.apache.solr.common.SolrInputField;
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

	ClassParts classPartsSiteConfig;

	ClassParts classPartsSiteUser;

	ClassParts classPartsCluster;

	ClassParts classPartsSearchResult;

	ClassParts classPartsAllWriter;

	ClassParts classPartsWrap;

	ClassParts classPartsSearchList;

	ClassParts classPartsPageLayout;

	ClassParts classPartsPagePart;

	String CONTEXT_frFR_AMale = "un ";

	String CONTEXT_frFR_AFemale = "une ";

	String CONTEXT_enUS_A = "an ";

	String CONTEXT_frFR_ThisMaleVowel = "cet ";

	String CONTEXT_frFR_ThisFemaleVowel = "cette ";

	String CONTEXT_enUS_ThisVowel = "this ";

	String CONTEXT_frFR_ThisMaleConsonant = "ce ";

	String CONTEXT_frFR_ThisFemaleConsonant = "cette ";

	String CONTEXT_enUS_ThisConsonant = "this ";

	String CONTEXT_frFR_ThesePlural = "ces ";

	String CONTEXT_enUS_ThesePlural = "these ";

	String CONTEXT_frFR_CreatedMale = "créé ";

	String CONTEXT_frFR_CreatedFemale = "créée ";

	String CONTEXT_enUS_Created = "created ";

	String CONTEXT_frFR_ModifiedMale = "modifié ";

	String CONTEXT_frFR_ModifiedFemale = "modifiée ";

	String CONTEXT_enUS_Modified = "modified ";

	String CONTEXT_frFR_TheMaleVowel = "l'";

	String CONTEXT_frFR_TheFemaleVowel = "l'";

	String CONTEXT_enUS_TheVowel = "the";

	String CONTEXT_frFR_TheMaleConsonant = "le ";

	String CONTEXT_frFR_TheFemaleConsonant = "la ";

	String CONTEXT_enUS_TheConsonant = "the ";

	String CONTEXT_frFR_ThePlural = "les ";

	String CONTEXT_enUS_ThePlural = "the ";

	String CONTEXT_frFR_Search = "rechercher ";

	String CONTEXT_enUS_Search = "search ";

	String CONTEXT_frFR_By = " par ";

	String CONTEXT_enUS_By = " by ";

	String CONTEXT_frFR_CurrentMaleBefore = "";

	String CONTEXT_frFR_CurrentFemaleBefore = "";

	String CONTEXT_enUS_CurrentBefore = "current ";

	String CONTEXT_frFR_CurrentMaleAfter = " actuel";

	String CONTEXT_frFR_CurrentFemaleAfter = " actuelle";

	String CONTEXT_enUS_CurrentAfter = "";

	String CONTEXT_frFR_AllMalePlural = "tous les ";

	String CONTEXT_frFR_AllFemalePlural = "toutes les ";

	String CONTEXT_enUS_AllPlural = "all the ";

	String CONTEXT_frFR_NoneFoundMaleBefore = "aucun ";

	String CONTEXT_frFR_NoneFoundFemaleBefore = "aucune ";

	String CONTEXT_enUS_NoneFoundBefore = "no ";

	String CONTEXT_frFR_NoneFoundMaleAfter = " trouvé";

	String CONTEXT_frFR_NoneFoundFemaleAfter = " trouvée";

	String CONTEXT_enUS_NoneFoundAfter = " found";

	String CONTEXT_frFR_OfVowel = "d'";

	String CONTEXT_enUS_OfVowel = "of ";

	String CONTEXT_frFR_OfConsonant = "de ";

	String CONTEXT_enUS_OfConsonant = "of ";

	String CONTEXT_frFR_AdjectivePlural = "s";

	String CONTEXT_enUS_AdjectivePlural = "";

	Boolean CONTEXT_frFR_AdjectiveBefore = false;

	Boolean CONTEXT_enUS_AdjectiveBefore = true;

	protected LinkedHashMap<String, ClassParts> classPartsGenApi = new LinkedHashMap<String, ClassParts>();

	protected LinkedHashMap<String, ClassParts> classPartsGenPage = new LinkedHashMap<String, ClassParts>();

	private String contextVideoId;

	private String contextDescription;

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

	private String contextNoNameFound;

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

	public String str_alreadyInitialized(String langueNom) {
		if("frFR".equals(langueNom))
			return "dejaInitialise";
		else
			return "alreadyInitialized";
	}

	public String str_initDeep(String langueNom) {
		if("frFR".equals(langueNom))
			return "initLoin";
		else
			return "initDeep";
	}

	public String str_ForClass(String langueNom) {
		if("frFR".equals(langueNom))
			return "PourClasse";
		else
			return "ForClass";
	}

	public String str_obtain(String langueNom) {
		if("frFR".equals(langueNom))
			return "obtenir";
		else
			return "obtain";
	}

	public String str_attribute(String langueNom) {
		if("frFR".equals(langueNom))
			return "attribuer";
		else
			return "attribute";
	}

	public String str_put(String langueNom) {
		if("frFR".equals(langueNom))
			return "put";
		else
			return "put";
	}

	public String str_index(String langueNom) {
		if("frFR".equals(langueNom))
			return "indexer";
		else
			return "index";
	}

	public String str_Indexed(String langueNom) {
		if("frFR".equals(langueNom))
			return "Indexe";
		else
			return "Indexed";
	}

	public String str_store(String langueNom) {
		if("frFR".equals(langueNom))
			return "stocker";
		else
			return "store";
	}

	public String str_populate(String langueNom) {
		if("frFR".equals(langueNom))
			return "peupler";
		else
			return "populate";
	}

	public String str_define(String langueNom) {
		if("frFR".equals(langueNom))
			return "definir";
		else
			return "define";
	}

	public String str_siteRequest(String langueNom) {
		if("frFR".equals(langueNom))
			return "requeteSite";
		else
			return "siteRequest";
	}

	public String str_SiteRequest(String langueNom) {
		if("frFR".equals(langueNom))
			return "RequeteSite";
		else
			return "SiteRequest";
	}

	public String str_before(String langueNom) {
		if("frFR".equals(langueNom))
			return "avant";
		else
			return "before";
	}

	public String str_after(String langueNom) {
		if("frFR".equals(langueNom))
			return "apres";
		else
			return "after";
	}

	public String str_Rights(String langueNom) {
		if("frFR".equals(langueNom))
			return "Droits";
		else
			return "Rights";
	}

	public String str_SiteConfig(String langueNom) {
		if("frFR".equals(langueNom))
			return "ConfigSite";
		else
			return "SiteConfig";
	}

	public String str_search(String langueNom) {
		if("frFR".equals(langueNom))
			return "rechercher";
		else
			return "search";
	}

	public String str_aSearch(String langueNom) {
		if("frFR".equals(langueNom))
			return "recherche";
		else
			return "aSearch";
	}

	public String str_SearchPage(String langueNom) {
		if("frFR".equals(langueNom))
			return "PageRecherche";
		else
			return "SearchPage";
	}

	public String str_Search(String langueNom) {
		if("frFR".equals(langueNom))
			return "Recherche";
		else
			return "Search";
	}

	public String str_solrQuery(String langueNom) {
		if("frFR".equals(langueNom))
			return "rechercheSolr";
		else
			return "solrQuery";
	}

	public String str_queryResponse(String langueNom) {
		if("frFR".equals(langueNom))
			return "reponseRecherche";
		else
			return "queryResponse";
	}

	public String str_SolrDocument(String langueNom) {
		if("frFR".equals(langueNom))
			return "DocumentSolr";
		else
			return "SolrDocument";
	}

	public String str_siteContext(String langueNom) {
		if("frFR".equals(langueNom))
			return "siteContexte";
		else
			return "siteContext";
	}

	public String str_SiteContext(String langueNom) {
		if("frFR".equals(langueNom))
			return "SiteContexte";
		else
			return "SiteContext";
	}

	public String str_ConfigPath(String langueNom) {
		if("frFR".equals(langueNom))
			return "ConfigChemin";
		else
			return "ConfigPath";
	}

	public String str_solrClient(String langueNom) {
		if("frFR".equals(langueNom))
			return "clientSolr";
		else
			return "solrClient";
	}

	public String str_SolrClient(String langueNom) {
		if("frFR".equals(langueNom))
			return "ClientSolr";
		else
			return "SolrClient";
	}

	public String str_list(String langueNom) {
		if("frFR".equals(langueNom))
			return "liste";
		else
			return "list";
	}

	public String str_Title(String langueNom) {
		if("frFR".equals(langueNom))
			return "Titre";
		else
			return "Title";
	}

	public String str_Translate(String langueNom) {
		if("frFR".equals(langueNom))
			return "Traduire";
		else
			return "Translate";
	}

	public String str_SearchList(String langueNom) {
		if("frFR".equals(langueNom))
			return "ListeRecherche";
		else
			return "SearchList";
	}

	public String str_Width(String langueNom) {
		if("frFR".equals(langueNom))
			return "Largeur";
		else
			return "Width";
	}

	public String str_Height(String langueNom) {
		if("frFR".equals(langueNom))
			return "Hauteur";
		else
			return "Height";
	}

	public String str_Group(String langueNom) {
		if("frFR".equals(langueNom))
			return "Groupe";
		else
			return "Group";
	}

	public String str_Name(String langueNom) {
		if("frFR".equals(langueNom))
			return "Nom";
		else
			return "Name";
	}

	public String str_context(String langueNom) {
		if("frFR".equals(langueNom))
			return "contexte";
		else
			return "context";
	}

	public String str_staticBaseUrl(String langueNom) {
		if("frFR".equals(langueNom))
			return "statiqueUrlBase";
		else
			return "staticBaseUrl";
	}

	public String str_Icon(String langueNom) {
		if("frFR".equals(langueNom))
			return "Icone";
		else
			return "Icon";
	}

	public String str_filter(String langueNom) {
		if("frFR".equals(langueNom))
			return "filtre";
		else
			return "filter";
	}

	public String str_filters(String langueNom) {
		if("frFR".equals(langueNom))
			return "filtres";
		else
			return "filters";
	}

	public String str_Filters(String langueNom) {
		if("frFR".equals(langueNom))
			return "Filtres";
		else
			return "Filters";
	}

	public String str_formFilters(String langueNom) {
		if("frFR".equals(langueNom))
			return "formulaireFiltres";
		else
			return "formFilters";
	}

	public String str_FormFilters(String langueNom) {
		if("frFR".equals(langueNom))
			return "FormulaireFiltres";
		else
			return "FormFilters";
	}

	public String str_FormValues(String langueNom) {
		if("frFR".equals(langueNom))
			return "FormulaireValeurs";
		else
			return "FormValues";
	}

	public String str_formValue(String langueNom) {
		if("frFR".equals(langueNom))
			return "formulaireValeur";
		else
			return "formValue";
	}

	public String str_formValues(String langueNom) {
		if("frFR".equals(langueNom))
			return "formulaireValeurs";
		else
			return "formValues";
	}

	public String str_Values(String langueNom) {
		if("frFR".equals(langueNom))
			return "Valeurs";
		else
			return "Values";
	}

	public String str_values(String langueNom) {
		if("frFR".equals(langueNom))
			return "valeurs";
		else
			return "values";
	}

	public String str_value(String langueNom) {
		if("frFR".equals(langueNom))
			return "valeur";
		else
			return "value";
	}

	public String str_glow(String langueNom) {
		if("frFR".equals(langueNom))
			return "lueur";
		else
			return "glow";
	}

	public String str_Error(String langueNom) {
		if("frFR".equals(langueNom))
			return "Erreur";
		else
			return "Error";
	}

	public String str_Success(String langueNom) {
		if("frFR".equals(langueNom))
			return "Succes";
		else
			return "Success";
	}

	public String str_operationRequest(String langueNom) {
		if("frFR".equals(langueNom))
			return "operationRequete";
		else
			return "operationRequest";
	}

	public String str_OperationRequest(String langueNom) {
		if("frFR".equals(langueNom))
			return "OperationRequete";
		else
			return "OperationRequest";
	}

	public String str_Request(String langueNom) {
		if("frFR".equals(langueNom))
			return "Requete";
		else
			return "Request";
	}

	public String str_Response(String langueNom) {
		if("frFR".equals(langueNom))
			return "Reponse";
		else
			return "Response";
	}

	public String str_ObjectValues(String langueNom) {
		if("frFR".equals(langueNom))
			return "ValeursObjet";
		else
			return "ObjectValues";
	}

	public String str_Object(String langueNom) {
		if("frFR".equals(langueNom))
			return "Objet";
		else
			return "Object";
	}

	public String str_Objects(String langueNom) {
		if("frFR".equals(langueNom))
			return "Objets";
		else
			return "Objects";
	}

	public String str_Method(String langueNom) {
		if("frFR".equals(langueNom))
			return "Methode";
		else
			return "Method";
	}

	public String str_Modal(String langueNom) {
		if("frFR".equals(langueNom))
			return "Modale";
		else
			return "Modal";
	}

	public String str_Short(String langueNom) {
		if("frFR".equals(langueNom))
			return "Court";
		else
			return "Short";
	}

	public String str_Write(String langueNom) {
		if("frFR".equals(langueNom))
			return "Ecrire";
		else
			return "Write";
	}

	public String str_contextIconCssClasses(String langueNom) {
		if("frFR".equals(langueNom))
			return "contexteIconeClassesCss";
		else
			return "contextIconCssClasses";
	}

	public String str_register(String langueNom) {
		if("frFR".equals(langueNom))
			return "enregistrer";
		else
			return "register";
	}

	public String str_Create_(String langueNom) {
		if("frFR".equals(langueNom))
			return "Créer ";
		else
			return "Create ";
	}

	public String str_Search_the_(String langueNom) {
		if("frFR".equals(langueNom))
			return "Rechercher des ";
		else
			return "Search the ";
	}

	public String str_Replace_(String langueNom) {
		if("frFR".equals(langueNom))
			return "Remplacer ";
		else
			return "Replace ";
	}

	public String str_Modify_the_(String langueNom) {
		if("frFR".equals(langueNom))
			return "Modifier des ";
		else
			return "Modify the ";
	}

	public String str_Delete_the_(String langueNom) {
		if("frFR".equals(langueNom))
			return "Supprimer des ";
		else
			return "Delete the ";
	}

	public String str_is_not_an_indexed_entity(String langueNom) {
		if("frFR".equals(langueNom))
			return "n'est pas une entité indexé";
		else
			return "is not an indexed entity";
	}

	public String str_create(String langueNom) {
		if ("frFR".equals(langueNom))
			return "creer";
		else
			return "create";
	}

	public String str_address(String langueNom) {
		if ("frFR".equals(langueNom))
			return "addresse";
		else
			return "address";
	}

	public String str_eventHandler(String langueNom) {
		if ("frFR".equals(langueNom))
			return "gestionnaireEvenements";
		else
			return "eventHandler";
	}

	public String str_entity(String langueNom) {
		if ("frFR".equals(langueNom))
			return "entite";
		else
			return "entity";
	}

	public String str_Number(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Numero";
		else
			return "Number";
	}

	public String str_Value(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Valeur";
		else
			return "Value";
	}

	public String str_request(String langueNom) {
		if ("frFR".equals(langueNom))
			return "requete";
		else
			return "request";
	}

	public String str_methodName(String langueNom) {
		if ("frFR".equals(langueNom))
			return "methodeNom";
		else
			return "methodName";
	}

	public String str_generate(String langueNom) {
		if ("frFR".equals(langueNom))
			return "generer";
		else
			return "generate";
	}

	public String str_SqlConnection(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ConnexionSql";
		else
			return "SqlConnection";
	}

	public String str_response(String langueNom) {
		if ("frFR".equals(langueNom))
			return "reponse";
		else
			return "response";
	}

	public String str_sqlConnection(String langueNom) {
		if ("frFR".equals(langueNom))
			return "connexionSql";
		else
			return "sqlConnection";
	}

	public String str_error(String langueNom) {
		if ("frFR".equals(langueNom))
			return "erreur";
		else
			return "error";
	}

	public String str_user(String langueNom) {
		if ("frFR".equals(langueNom))
			return "utilisateur";
		else
			return "user";
	}

	public String str_For(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Pour";
		else
			return "For";
	}

	public String str_delete(String langueNom) {
		if ("frFR".equals(langueNom))
			return "supprimer";
		else
			return "delete";
	}

	public String str_User(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Utilisateur";
		else
			return "User";
	}

	public String str_Line(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Ligne";
		else
			return "Line";
	}

	public String str_methodNames(String langueNom) {
		if ("frFR".equals(langueNom))
			return "methodeNoms";
		else
			return "methodNames";
	}

	public String str_JsonObject(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ObjetJson";
		else
			return "JsonObject";
	}

	public String str_modify(String langueNom) {
		if ("frFR".equals(langueNom))
			return "modifier";
		else
			return "modify";
	}

	public String str_solrDocuments(String langueNom) {
		if ("frFR".equals(langueNom))
			return "documentsSolr";
		else
			return "solrDocuments";
	}

	public String str_List(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Liste";
		else
			return "List";
	}

	public String str_searchInMillis(String langueNom) {
		if ("frFR".equals(langueNom))
			return "millisRecherche";
		else
			return "searchInMillis";
	}

	public String str_transmissionInMillis(String langueNom) {
		if ("frFR".equals(langueNom))
			return "millisTransmission";
		else
			return "transmissionInMillis";
	}

	public String str_startNum(String langueNom) {
		if ("frFR".equals(langueNom))
			return "numCommence";
		else
			return "startNum";
	}

	public String str_foundNum(String langueNom) {
		if ("frFR".equals(langueNom))
			return "numTrouve";
		else
			return "foundNum";
	}

	public String str_returnedNum(String langueNom) {
		if ("frFR".equals(langueNom))
			return "numRetourne";
		else
			return "returnedNum";
	}

	public String str_searchTime(String langueNom) {
		if ("frFR".equals(langueNom))
			return "tempsRecherche";
		else
			return "searchTime";
	}

	public String str_transmissionTime(String langueNom) {
		if ("frFR".equals(langueNom))
			return "tempsTransmission";
		else
			return "transmissionTime";
	}

	public String str_solrDocument(String langueNom) {
		if ("frFR".equals(langueNom))
			return "documentSolr";
		else
			return "solrDocument";
	}

	public String str_result(String langueNom) {
		if ("frFR".equals(langueNom))
			return "resultat";
		else
			return "result";
	}

	public String str_sqlClient(String langueNom) {
		if ("frFR".equals(langueNom))
			return "clientSql";
		else
			return "sqlClient";
	}

	public String str_SqlClient(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ClientSql";
		else
			return "SqlClient";
	}

	public String str_siteUser(String langueNom) {
		if ("frFR".equals(langueNom))
			return "utilisateurSite";
		else
			return "siteUser";
	}

	public String str_jsonPrincipal(String langueNom) {
		if ("frFR".equals(langueNom))
			return "principalJson";
		else
			return "jsonPrincipal";
	}

	public String str_FirstName(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Prenom";
		else
			return "FirstName";
	}

	public String str_LastName(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomFamille";
		else
			return "LastName";
	}

	public String str_Populate(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Peupler";
		else
			return "Populate";
	}

	public String str_Store(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Stocker";
		else
			return "Store";
	}

	public String str_archived(String langueNom) {
		if ("frFR".equals(langueNom))
			return "archive";
		else
			return "archived";
	}

	public String str_deleted(String langueNom) {
		if ("frFR".equals(langueNom))
			return "supprime";
		else
			return "deleted";
	}

	public String str_classCanonicalNames(String langueNom) {
		if ("frFR".equals(langueNom))
			return "classeNomsCanoniques";
		else
			return "classCanonicalNames";
	}

	public String str_SeeDeleted(String langueNom) {
		if ("frFR".equals(langueNom))
			return "VoirSupprime";
		else
			return "SeeDeleted";
	}

	public String str_SeeArchived(String langueNom) {
		if ("frFR".equals(langueNom))
			return "VoirArchive";
		else
			return "SeeArchived";
	}

	public String str_classApiUriMethod(String langueNom) {
		if ("frFR".equals(langueNom))
			return "classeApiUriMethode";
		else
			return "classApiUriMethod";
	}

	public String str_Sort(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Tri";
		else
			return "Sort";
	}

	public String str_Start(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Debut";
		else
			return "Start";
	}

	public String str_suggere(String langueNom) {
		if ("frFR".equals(langueNom))
			return "suggere";
		else
			return "suggest";
	}

	public String str_Suggere(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Suggere";
		else
			return "Suggest";
	}

	public String str_saves(String langueNom) {
		if ("frFR".equals(langueNom))
			return "sauvegardes";
		else
			return "saves";
	}

	public String str_unindex(String langueNom) {
		if ("frFR".equals(langueNom))
			return "desindexer";
		else
			return "unindex";
	}

	public String str_removeGlow(String langueNom) {
		if ("frFR".equals(langueNom))
			return "enleverLueur";
		else
			return "removeGlow";
	}

	public String str_DDDashMMDashYYYY(String langueNom) {
		if ("frFR".equals(langueNom))
			return "DD-MM-YYYY";
		else
			return "MM/DD/YYYY";
	}

	public String str_ddDashMMDashyyyy(String langueNom) {
		if ("frFR".equals(langueNom))
			return "dd-MM-yyyy";
		else
			return "MM/dd/yyyy";
	}

	public String str_enDashUS(String langueNom) {
		if ("frFR".equals(langueNom))
			return "fr-FR";
		else
			return "en-US";
	}

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

	protected Boolean storeSolr(String languageName, SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception, Exception {
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

	protected String storeSolr(String languageName, SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String storeListSolr(String languageName, SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> storeSolr(String languageName, SolrInputDocument doc, String fieldName, List<String> fieldValues) throws Exception, Exception {
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

	protected Boolean indexSolr(String languageName, SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexSolr(SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		doc.addField(concat(fieldName, "_indexed_string"), fieldValue);
		return fieldValue;
	}

	protected String indexSolr(String languageName, SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexListSolr(String languageName, SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> indexSolr(String languageName, SolrInputDocument doc, String fieldName, List<String> fieldValues) throws Exception, Exception {
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

	protected Long indexStoreSolr(String languageName, SolrInputDocument doc, String fieldName, Long fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_long"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_long"), fieldValue);
		}
		return fieldValue;
	}

	protected Double indexStoreSolr(String languageName, SolrInputDocument doc, String fieldName, Double fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_double"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_double"), fieldValue);
		}
		return fieldValue;
	}

	protected Integer indexStoreSolr(String languageName, SolrInputDocument doc, String fieldName, Integer fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_int"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_int"), fieldValue);
		}
		return fieldValue;
	}

	protected Boolean indexStoreSolr(String languageName, SolrInputDocument doc, String fieldName, Boolean fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_boolean"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_boolean"), fieldValue);
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

	protected String indexStoreSolrRegex(String languageName, SolrInputDocument doc, String fieldName, String fieldNameRegex, String comment) throws Exception, Exception {
		return indexStoreSolrRegex(languageName, doc, fieldName, fieldNameRegex, comment, null);
	}

	protected String indexStoreSolrRegex(String languageName, SolrInputDocument doc, String fieldName, String fieldNameRegex, String comment, String defaultValue) throws Exception, Exception {
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

	protected String indexStoreSolr(String languageName, SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_string"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_string"), fieldValue);
		}
		return fieldValue;
	}

	protected String indexStoreListSolr(String languageName, SolrInputDocument doc, String fieldName, String fieldValue) throws Exception, Exception {
		if(languageIndexed || !StringUtils.equals(languageName, this.languageName)) {
			doc.addField(concat(fieldName, "_", languageName, "_stored_strings"), fieldValue);
			doc.addField(concat(fieldName, "_", languageName, "_indexed_strings"), fieldValue);
		}
		return fieldValue;
	}

	protected List<String> indexStoreSolr(String languageName, SolrInputDocument doc, String fieldName, List<String> fieldValues) throws Exception, Exception {
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

	protected ClassParts classPartsForSimpleName(String domainPackageName, String simpleName, String classLanguageName) throws Exception, Exception {
		ClassParts classParts = null;
		SolrDocument doc = null;
		SolrQuery solrSearch = new SolrQuery();   
		solrSearch.setQuery("*:*");
		solrSearch.setRows(1);
		solrSearch.addFilterQuery("classKeywords_indexed_strings:" + ClientUtils.escapeQueryChars("classSimpleName" + simpleName));
		solrSearch.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
		solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
		QueryResponse searchResponse = solrClientComputate.query(solrSearch);
		SolrDocumentList searchList = searchResponse.getResults();
		if(searchList.size() > 0) {
			doc = searchList.get(0);
			String nomCanonique = (String)doc.get("classCanonicalName_" + classLanguageName + "_stored_string");
			classParts = ClassParts.initClassParts(this, nomCanonique, classLanguageName);
		}
		return classParts;
	}

	protected ClassParts classPartsWrap(String domainPackageName, String classLanguageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "Wrap", classLanguageName);
	}

	protected ClassParts classPartsSiteRequest(String domainPackageName, String classLanguageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteRequest", classLanguageName);
	}

	protected ClassParts classPartsSiteContext(String domainPackageName, String classLanguageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteContext", classLanguageName);
	}

	protected ClassParts classPartsSiteConfig(String domainPackageName, String classLanguageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteConfig", classLanguageName);
	}

	protected ClassParts classPartsSiteUser(String domainPackageName, String classLanguageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SiteUser", classLanguageName);
	}

	protected ClassParts classPartsCluster(String domainPackageName, String classLanguageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "Cluster", classLanguageName);
	}

	protected ClassParts classPartsSearchResult(String domainPackageName, String classLanguageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SearchResult", classLanguageName);
	}

	protected ClassParts classPartsAllWriter(String domainPackageName, String classLanguageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "AllWriter", classLanguageName);
	}

	protected ClassParts classPartsSearchList(String domainPackageName, String classLanguageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "SearchList", classLanguageName);
	}

	protected ClassParts classPartsPageLayout(String domainPackageName, String classLanguageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "PageLayout", classLanguageName);
	}

	protected ClassParts classPartsPagePart(String domainPackageName, String classLanguageName) throws Exception, Exception {
		return classPartsForSimpleName(domainPackageName, "PagePart", classLanguageName);
	}

	public String storeRegexComments(String languageName, SolrInputDocument doc, String entityVar, String comment) throws Exception, Exception {
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

	public SolrInputDocument indexClass(String classAbsolutePath, SolrInputDocument classDoc, String classLanguageName) throws Exception, Exception { 

		String[] classOtherLanguages = ArrayUtils.removeAllOccurences(allLanguages, classLanguageName);
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
		String classVarSuggest = null;
		String classVarUrl = null;
		String classVarTitle = null;
		String classVarDescription = null;
		String classVarImageUrl = null;
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
		
		String classSuperCompleteName = Object.class.getCanonicalName();
		try {
			classSuperCompleteName = indexStoreSolr(classLanguageName, classDoc, "classSuperCompleteName", classSuperQdox.getGenericCanonicalName());
		} catch (Exception e) {
			if(classSuperQdox != null && classSuperQdox.getGenericFullyQualifiedName().contains("<"))
				classSuperCompleteName = indexStoreSolr(classLanguageName, classDoc, "classSuperCompleteName", classSuperQdox.getGenericFullyQualifiedName());
		}
		for(JavaClass cImplements : classQdox.getImplementedInterfaces()) {
			ClassParts classPartsImplements = ClassParts.initClassParts(this, cImplements, classLanguageName);
			indexStoreListSolr(classLanguageName, classDoc, "classImplementsSimpleNameComplete", classPartsImplements.simpleNameComplete);
		}

		String classSuperCompleteNameGeneric = StringUtils.substringBeforeLast(StringUtils.substringAfter(classSuperCompleteName, "<"), ">");
		String classSuperCanonicalNameGeneric = null;
		String classSuperSimpleNameGeneric = null;
		Boolean baseClassExtendsGen = false;
		ClassParts classPartsBase = null;
		if(StringUtils.isNotEmpty(classSuperCompleteName)) {
			indexStoreSolr(classLanguageName, classDoc, "classSuperCompleteNameGeneric", classSuperCompleteNameGeneric);
			if(classSuperCompleteName.contains("<")) {
				classSuperCanonicalNameGeneric = StringUtils.substringAfter(StringUtils.substringBeforeLast(classSuperCompleteName, ">"), "<");
				classSuperCanonicalNameGeneric = classSuperCanonicalNameGeneric.contains(",") ? StringUtils.substringBefore(classSuperCanonicalNameGeneric, ",") : classSuperCanonicalNameGeneric;
				indexStoreSolr(classLanguageName, classDoc, "classSuperCanonicalNameGeneric", classSuperCanonicalNameGeneric);
				classSuperCompleteNameGeneric = classSuperCanonicalNameGeneric;

				if(classSuperCanonicalNameGeneric.contains("."))
					classSuperSimpleNameGeneric = StringUtils.substringAfterLast(classSuperCanonicalNameGeneric, ".");
				else
					classSuperSimpleNameGeneric = classSuperCanonicalNameGeneric;
				indexStoreSolr(classLanguageName, classDoc, "classSuperSimpleNameGeneric", classSuperSimpleNameGeneric);

				classPartsBase = ClassParts.initClassParts(this, classSuperCanonicalNameGeneric, classLanguageName, classLanguageName);
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
			classContainsSiteRequest = classQdox.getMethodBySignature("get" + str_SiteRequest(classLanguageName) +"_", new ArrayList<JavaType>(), true) != null
					|| classPartsBase != null && BooleanUtils.isTrue((Boolean)classPartsBase.solrDocument.get("classContainsSiteRequest_stored_boolean"));
		} catch (Exception e) {
			// TODO ctate fix this to pull from solr. 
		}
		indexStoreSolr(classDoc, "classContainsSiteRequest", classContainsSiteRequest);
		
		String classComment = storeRegexComments(classLanguageName, classDoc, "classComment", classQdox.getComment());
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
				String classCanonicalNameLanguage = regex("^(class)?CanonicalName\\." + languageName + ":\\s*(.*)", classComment);
	
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

		classPartsSolrInputDocument = ClassParts.initClassParts(this, "org.apache.solr.common.SolrInputDocument", classLanguageName);
		classPartsSolrDocument = ClassParts.initClassParts(this, "org.apache.solr.common.SolrDocument", classLanguageName);
		classPartsSolrClient = ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrClient", classLanguageName);
		classPartsTest = ClassParts.initClassParts(this, "org.junit.Test", classLanguageName);
		classPartsList = ClassParts.initClassParts(this, List.class.getCanonicalName(), classLanguageName);
		classPartsArrayList = ClassParts.initClassParts(this, ArrayList.class.getCanonicalName(), classLanguageName);
		classPartsSiteContext = classPartsSiteContext(domainPackageName, classLanguageName);
		classPartsSiteConfig = classPartsSiteConfig(domainPackageName, classLanguageName);
		classPartsSiteUser = classPartsSiteUser(domainPackageName, classLanguageName);
		classPartsCluster = classPartsCluster(domainPackageName, classLanguageName);
		classPartsSearchResult = classPartsSearchResult(domainPackageName, classLanguageName);
		classPartsAllWriter = classPartsAllWriter(domainPackageName, classLanguageName);
		classPartsSearchList = classPartsSearchList(domainPackageName, classLanguageName);
		classPartsWrap = classPartsWrap(domainPackageName, classLanguageName);
		classPartsPageLayout = classPartsPageLayout(domainPackageName, classLanguageName);
		classPartsPagePart = classPartsPagePart(domainPackageName, classLanguageName);
		classPartsSiteRequest = classPartsSiteRequest(domainPackageName, classLanguageName);

		Boolean classInitDeep = !regexFound("^(class)?InitDeep:\\s*(false)$", classComment);
		if(classInitDeep)
			classInitDeep = classExtendsBase || classIsBase;
//			classInitDeep = classContainsSiteRequest;
		classInitDeep = storeSolr(classDoc, "classInitDeep", classInitDeep);
		if(classInitDeep)
			classPartsGenAdd(classPartsSiteRequest);
		indexStoreSolr(classDoc, "classExtendsGen", classExtendsGen);

		indexStoreSolr(classDoc, "languageName", classLanguageName); 
		indexStoreSolr(classDoc, "modified", modifiedDate); 
		indexStoreSolr(classLanguageName, classDoc, "classCanonicalName", classCanonicalName); 
		indexStoreSolr(classLanguageName, classDoc, "classSimpleName", classSimpleName); 
		indexStoreSolr(classLanguageName, classDoc, "classPackageName", classPackageName); 
		indexStoreSolr(classLanguageName, classDoc, "classCanonicalNameGen", classCanonicalNameGen); 
		indexStoreSolr(classLanguageName, classDoc, "classSimpleNameGen", classSimpleNameGen); 
		indexStoreSolr(classLanguageName, classDoc, "classSuperCanonicalName", classSuperCanonicalName); 
		indexStoreSolr(classLanguageName, classDoc, "classSuperSimpleName", classSuperSimpleName); 
		indexStoreSolr(classDoc, "classAbsolutePath", classAbsolutePath);
		indexStoreSolr(classLanguageName, classDoc, "classPath", classPath); 
		indexStoreSolr(classLanguageName, classDoc, "classDirPath", classDirPath);  
		indexStoreSolr(classLanguageName, classDoc, "classPathGen", classPathGen); 
		indexStoreSolr(classLanguageName, classDoc, "classDirPathGen", classDirPathGen); 
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

		String classSimpleNameApiPackageInfo;
		String classSimpleNameGenApiServiceImpl;
		String classSimpleNameApiServiceImpl;
		String classSimpleNameGenApiService;

		String classCanonicalNameApiPackageInfo;
		String classCanonicalNameGenApiServiceImpl;
		String classCanonicalNameApiServiceImpl;
		String classCanonicalNameGenApiService;

		String classPathApiPackageInfo;
		String classPathGenApiServiceImpl;
		String classPathApiServiceImpl;
		String classPathGenApiService;

		if(classApi) {
			classSimpleNameApiPackageInfo = indexStoreSolr(classLanguageName, classDoc, "classSimpleNameApiPackageInfo", "package-info");
			classSimpleNameGenApiServiceImpl = indexStoreSolr(classLanguageName, classDoc, "classSimpleNameGenApiServiceImpl", classSimpleName + StringUtils.capitalize(classLanguageName) + "GenApiServiceImpl");
			classSimpleNameApiServiceImpl = indexStoreSolr(classLanguageName, classDoc, "classSimpleNameApiServiceImpl", classSimpleName + StringUtils.capitalize(classLanguageName) + "ApiServiceImpl");
			classSimpleNameGenApiService = indexStoreSolr(classLanguageName, classDoc, "classSimpleNameGenApiService", classSimpleName + StringUtils.capitalize(classLanguageName) + "GenApiService");

			classCanonicalNameApiPackageInfo = indexStoreSolr(classLanguageName, classDoc, "classCanonicalNameApiPackageInfo", classPackageName + "." + classSimpleNameApiPackageInfo);
			classCanonicalNameGenApiServiceImpl = indexStoreSolr(classLanguageName, classDoc, "classCanonicalNameGenApiServiceImpl", classPackageName + "." + classSimpleNameGenApiServiceImpl);
			classCanonicalNameApiServiceImpl = indexStoreSolr(classLanguageName, classDoc, "classCanonicalNameApiServiceImpl", classPackageName + "." + classSimpleNameApiServiceImpl);
			classCanonicalNameGenApiService = indexStoreSolr(classLanguageName, classDoc, "classCanonicalNameGenApiService", classPackageName + "." + classSimpleNameGenApiService);

			classPathApiPackageInfo = concat(srcGenJavaPath, "/", StringUtils.replace(classCanonicalNameApiPackageInfo, ".", "/"), ".java");
			classPathGenApiServiceImpl = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalNameGenApiServiceImpl, ".", "/"), ".java");
			classPathApiServiceImpl = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalNameApiServiceImpl, ".", "/"), ".java");
			classPathGenApiService = concat(srcMainJavaPath, "/", StringUtils.replace(classCanonicalNameGenApiService, ".", "/"), ".java");

			indexStoreSolr(classLanguageName, classDoc, "classPathApiPackageInfo", classPathApiPackageInfo); 
			indexStoreSolr(classLanguageName, classDoc, "classPathGenApiServiceImpl", classPathGenApiServiceImpl); 
			indexStoreSolr(classLanguageName, classDoc, "classPathApiServiceImpl", classPathApiServiceImpl); 
			indexStoreSolr(classLanguageName, classDoc, "classPathGenApiService", classPathGenApiService); 
		}

		if(classTranslate) {
			for(String languageName : classOtherLanguages) {
				String appPathLanguage = appPaths.get(languageName);
				String srcMainJavaPathLanguage = appPathLanguage + "/src/main/java";
				String srcGenJavaPathLanguage = appPathLanguage + "/src/gen/java";
				String classCanonicalNameLanguage = (String)classDoc.get("classCanonicalName_" + languageName + "_indexed_string").getValue();
				String classSimpleNameLanguage = (String)classDoc.get("classSimpleName_" + languageName + "_indexed_string").getValue();
				String classPackageNameLanguage = (String)classDoc.get("classPackageName_" + languageName + "_indexed_string").getValue();
	
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

		if(classExtendsBase || classIsBase) {
			classPartsGenAdd(classPartsCluster);
			classPartsGenAdd(classPartsAllWriter);
		}
		if(classSaved) {
			classPartsGenAdd(classPartsSiteContext);
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.Logger", classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.LoggerFactory", classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLClient", classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLConnection", classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonObject", classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, VAL_canonicalNameDate, classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "java.util.Set", classLanguageName));
		}
		classPartsGenAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonArray", classLanguageName));
		classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.commons.text.StringEscapeUtils", classLanguageName));
		classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.StringUtils", classLanguageName));
		classPartsGenAdd(ClassParts.initClassParts(this, "java.util.Objects", classLanguageName));
		classPartsGenAdd(ClassParts.initClassParts(this, "com.fasterxml.jackson.annotation.JsonIgnore", classLanguageName));

		if(classComment != null) {

			Matcher classValsSearch = Pattern.compile("^(class)?Val(:([^:\n]+):)?\\.(\\w+)\\.([^:\n]+):(.*)", Pattern.MULTILINE).matcher(classComment);
			boolean classValsFound = classValsSearch.find();
			while(classValsFound) {
				String classValVar = classValsSearch.group(4);
				String classValLanguage = classValsSearch.group(5);
				String classValCode = classValsSearch.group(3);
				String classValValue = classValsSearch.group(6);
				if(classValCode == null)
					classValCode = "";
				storeListSolr(classDoc, "classValsVar", classValVar);
				storeListSolr(classDoc, "classValsLanguage", classValLanguage);
				storeListSolr(classDoc, "classValsCode", classValCode);
				storeListSolr(classDoc, "classValsValue", classValValue);
				classValsFound = classValsSearch.find();
			}

			Matcher classRolesSearch = Pattern.compile("^(class)?Role\\.([^:\n]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classComment);
			boolean classRolesFound = classRolesSearch.find();
			boolean classRolesFoundCurrent = classRolesFound;
			while(classRolesFoundCurrent) {
				String classRoleValue = classRolesSearch.group(3);
				String classRoleLanguage = classRolesSearch.group(2);
				storeListSolr(classDoc, "classRoles", classRoleValue);
				storeListSolr(classDoc, "classRolesLanguage", classRoleLanguage);
				classRolesFound = true;
				classRolesFoundCurrent = classRolesSearch.find();
			}
			indexStoreSolr(classDoc, "classRolesFound", classRolesFound); 

			Matcher classFiltersSearch = Pattern.compile("^(class)?Filtre\\.([^:\n]+):\\s*(.*)\\s*", Pattern.MULTILINE).matcher(classComment);
			boolean classFiltersFound = classFiltersSearch.find();
			boolean classFiltersFoundCurrent = classFiltersFound;
			while(classFiltersFoundCurrent) {
				String classFilterValue = classFiltersSearch.group(3);
				String classFiltreLangue = classRolesSearch.group(2);
				storeListSolr(classDoc, "classFilters", classFilterValue);
				storeListSolr(classDoc, "classFiltersLanguage", classFiltreLangue);
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
			ClassParts classPartsSuperGeneric = ClassParts.initClassParts(this, classSuperCompleteNameGeneric, classLanguageName);
			classPartsGenAdd(classPartsSuperGeneric);

			if(StringUtils.startsWith(classSuperCanonicalName, domainPackageName)) {
				SolrQuery solrSearch = new SolrQuery();   
				solrSearch.setQuery("*:*");
				solrSearch.setRows(1);
				solrSearch.addFilterQuery("classCanonicalName_" + classLanguageName + "_indexed_string:" + ClientUtils.escapeQueryChars(classSuperCanonicalNameGeneric));
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
				solrSearch.addFilterQuery("classCanonicalName_" + classLanguageName + "_indexed_string:" + ClientUtils.escapeQueryChars(classSuperCanonicalNameGeneric));
				solrSearch.addFilterQuery("partIsClass_indexed_boolean:true");
				solrSearch.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
				QueryResponse searchResponse = solrClientComputate.query(solrSearch);
				SolrDocumentList searchList = searchResponse.getResults();
				if(searchList.size() > 0) { 
					classSuperDoc = searchList.get(0);
				}
			}
		}

		List<String> classSuperWriteMethods;
		List<String> classWriteMethods = new ArrayList<>();
		if(classSuperDoc != null) 
			classSuperWriteMethods = (List<String>)classSuperDoc.get("classWriteMethods_stored_strings");
		else
			classSuperWriteMethods = new ArrayList<>();

		for(String siteWriteMethod : siteWriteMethods) {
			if(classQdox.getMethodBySignature(siteWriteMethod, new ArrayList<JavaType>()) != null
					|| classQdox.getMethodBySignature(siteWriteMethod + classSimpleName, new ArrayList<JavaType>()) != null) {
				if(!classWriteMethods.contains(siteWriteMethod)) {
					indexStoreListSolr(classDoc, "classWriteMethods",  siteWriteMethod);
					classWriteMethods.add(siteWriteMethod);
				}
			}
		}

		if(classSuperDoc != null) {
			if(classSuperWriteMethods != null) {
				for(String classSuperEcrireMethode : classSuperWriteMethods) {
					indexStoreListSolr(classDoc, "classSuperWriteMethods",  classSuperEcrireMethode);
					if(!classWriteMethods.contains(classSuperEcrireMethode)) {
						indexStoreListSolr(classDoc, "classWriteMethods",  classSuperEcrireMethode);
						classWriteMethods.add(classSuperEcrireMethode);
					}
				}
			}
		}

		if(classDoc.getField("id") == null)
			classDoc.addField("id", classKey);  

		indexStoreSolr(classDoc, "partIsClass", true);
		indexStoreSolr(classDoc, "partNumber", partNumber);
		
		for(String classImport : classQdox.getSource().getImports()) {
			ClassParts classImportClassParts = ClassParts.initClassParts(this, classImport, classLanguageName);
			indexStoreListSolr(classLanguageName, classDoc, "classImports", classImportClassParts.canonicalName(classLanguageName));

			for(String languageName : classOtherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classImportClassParts, languageName);
				indexStoreListSolr(languageName, classDoc, "classImports", classImportClassPartsLanguage.canonicalName(languageName));
			}
		}

		List<JavaMember> membersQdox = classQdox.getMembers();
		for(JavaMember memberQdox : membersQdox) {  
			partNumber++;

			if(memberQdox instanceof JavaField) {    
				SolrInputDocument fieldDoc = classDocClone.deepCopy();
				JavaField fieldQdox = (JavaField)memberQdox;
				String fieldComment = fieldQdox.getComment();
				String fieldVar = fieldQdox.getName();
				String fieldKey = classAbsolutePath + "." + fieldVar;
				String fieldSourceCode = StringUtils.substringBeforeLast(StringUtils.trim(regex("\\s+" + fieldVar + "\\s*=([\\s\\S]*)", fieldQdox.getCodeBlock(), 1)), ";");
				String fieldString = regex("^(field)?String\\." + classLanguageName + ":(.*)", fieldComment);
				if(fieldString != null) {
					fieldSourceCode = "\"" + StringUtils.replace(StringUtils.replace(fieldString, "\\", "\\\\"), "\"", "\\\"") + "\"";
					indexStoreSolr(classLanguageName, fieldDoc, "fieldString", fieldString); 
				}

				// Champs Solr du field. 

				indexStoreSolr(classLanguageName, fieldDoc, "fieldVar", fieldVar); 
				indexStoreSolr(fieldDoc, "partIsField", true);
				indexStoreSolr(fieldDoc, "partNumber", partNumber);
				indexStoreSolr(fieldDoc, "fieldIsPublic", fieldQdox.isPublic()); 
				indexStoreSolr(fieldDoc, "fieldIsProtected", fieldQdox.isProtected()); 
				indexStoreSolr(fieldDoc, "fieldIsPrivate", fieldQdox.isPrivate()); 
				indexStoreSolr(fieldDoc, "fieldIsStatic", fieldQdox.isStatic()); 
				indexStoreSolr(fieldDoc, "fieldIsFinal", fieldQdox.isFinal()); 
				indexStoreSolr(fieldDoc, "fieldIsAbstract", fieldQdox.isAbstract()); 
				indexStoreSolr(fieldDoc, "fieldIsNative", fieldQdox.isNative()); 
				indexStoreSolr(fieldDoc, "fieldTranslate", !regexFound("^(field)?Translate: \\s*(false)$", fieldComment));
	
				/////////////////
				// Annotations //
				/////////////////
				List<JavaAnnotation> annotations = fieldQdox.getAnnotations(); 
				ArrayList<String> annotationsLanguage = new ArrayList<String>();
				Boolean fieldIsTest = false;
				Boolean fieldIsOverride = false;
				for(JavaAnnotation annotation : annotations) {
					String fieldAnnotationLanguage = annotation.getType().getCanonicalName();
					indexStoreSolr(classLanguageName, fieldDoc, "fieldAnnotation", fieldAnnotationLanguage); 

					if("org.junit.Test".equals(annotation.getType().getCanonicalName())) {
						fieldIsTest = true;
					}
					if("java.lang.Override".equals(annotation.getType().getCanonicalName())) {
						fieldIsOverride = true;
					}
				}
				indexStoreSolr(fieldDoc, "fieldIsTest", fieldIsTest); 
				indexStoreSolr(fieldDoc, "fieldIsOverride", fieldIsOverride); 

				ClassParts fieldClassParts = ClassParts.initClassParts(this, fieldQdox.getType(), classLanguageName);
	
				storeRegexComments(classLanguageName, fieldDoc, "fieldComment", fieldComment);
				storeSolr(classLanguageName, fieldDoc, "fieldSimpleNameComplete", fieldClassParts.simpleNameComplete);
				String fieldCanonicalNameComplete = storeSolr(classLanguageName, fieldDoc, "fieldCanonicalNameComplete", fieldClassParts.canonicalNameComplete);
				storeSolr(classLanguageName, fieldDoc, "fieldSourceCode", fieldSourceCode);
				fieldDoc.addField("id", fieldCanonicalNameComplete + " " + fieldKey);

				if(classTranslate) {
					for(String languageName : classOtherLanguages) { 
						ClassParts fieldClassPartsLanguage = ClassParts.initClassParts(this, fieldClassParts, languageName);
						String fieldVarLanguage = regex("^(field)?Var\\." + languageName + ": (.*)", fieldComment);
						fieldVarLanguage = fieldVarLanguage == null ? fieldVar : fieldVarLanguage;
						String fieldSourceCodeLanguage = regexReplaceAll(fieldComment, fieldSourceCode, languageName);
						String fieldStringLanguage = regex("^(field)?String\\." + languageName + ":(.*)", fieldComment);
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
					storeListSolr(classLanguageName, constructorDoc, "constructorParamsVar", constructorParamVar);
					ClassParts constructorParamClassParts = ClassParts.initClassParts(this, constructorParamQdox.getJavaClass(), classLanguageName);
					classPartsGenAdd(constructorParamClassParts);
					storeListSolr(classLanguageName, constructorDoc, "constructorParamsSimpleNameComplete", constructorParamClassParts.simpleNameComplete);
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
				storeRegexComments(classLanguageName, constructorDoc, "constructorComment", constructorComment);

				String constructorSourceCode = constructorQdox.getSourceCode();
				String constructorSourceCodeLanguage = constructorSourceCode;
				ArrayList<String> replaceKeysLanguage = regexList("^r." + classLanguageName + "\\s*=\\s*(.*)\\n.*", constructorComment);
				ArrayList<String> replaceValuesLanguage = regexList("^r." + classLanguageName + "\\s*=\\s*.*\\n(.*)", constructorComment);
				for(int i = 0; i < replaceKeysLanguage.size(); i++) {
					String cle = replaceKeysLanguage.get(i);
					String valeur = replaceValuesLanguage.get(i);
					StringUtils.replace(constructorSourceCodeLanguage, cle, valeur);
				}
				storeSolr(classLanguageName, constructorDoc, "constructorSourceCode", constructorSourceCodeLanguage);

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
						String entityVar = indexStoreSolr(classLanguageName, entityDoc, "entityVar", StringUtils.substringAfter(methodQdox.getName(), "_"));
						indexStoreListSolr(classLanguageName, classDoc, "classEntityVars", entityVar);
						String entityVarCapitalized = indexStoreSolr(classLanguageName, entityDoc, "entityVarCapitalized", StringUtils.capitalize(entityVar));
						JavaClass entityClassQdox = methodParamsQdox.get(0).getJavaClass();
						ClassParts entityClassParts = ClassParts.initClassParts(this, entityClassQdox, classLanguageName, classLanguageName);
						Boolean entityWrap = false;

						if(entityClassParts.simpleName.equals(classPartsWrap.simpleName(languageName))) {
							entityClassParts = ClassParts.initClassParts(this, entityClassParts.canonicalNameGeneric, classLanguageName, classLanguageName);
							entityWrap = true;
							classContainsWrap = true;
						}

						classPartsGenAdd(entityClassParts);
						classPartsGenPageAdd(entityClassParts);
						List<String> entityCanonicalNamesSuperAndMeWithoutGen = new ArrayList<String>();
						if(StringUtils.isNotEmpty(entityClassParts.canonicalNameGeneric)) {
							ClassParts classPartsGeneric = ClassParts.initClassParts(this, entityClassParts.canonicalNameGeneric, classLanguageName);
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

						String entityCanonicalName = indexStoreSolr(classLanguageName, entityDoc, "entityCanonicalName", entityClassParts.canonicalName);
						String entitySimpleName = indexStoreSolr(classLanguageName, entityDoc, "entitySimpleName", entityClassParts.simpleName);
						String entityCompleteNameGeneric = indexStoreSolr(classLanguageName, entityDoc, "entityCompleteNameGeneric", entityClassParts.canonicalNameGeneric);
						String entityCanonicalNameGeneric = indexStoreSolr(classLanguageName, entityDoc, "entityCanonicalNameGeneric", entityClassParts.canonicalNameGeneric);
						String entitySimpleNameGeneric = indexStoreSolr(classLanguageName, entityDoc, "entitySimpleNameGeneric", entityClassParts.simpleNameGeneric);
						String entityCanonicalNameActuel = entityCanonicalNameGeneric == null ? entityCanonicalName : entityCanonicalNameGeneric;
						String entitySimpleNameActuel = entitySimpleNameGeneric == null ? entitySimpleName : entitySimpleNameGeneric;
						indexStoreSolr(classLanguageName, entityDoc, "entityCanonicalNameComplete", entityClassParts.canonicalNameComplete);
						indexStoreSolr(classLanguageName, entityDoc, "entitySimpleNameComplete", entityClassParts.simpleNameComplete);
						indexStoreSolr(classLanguageName, entityDoc, "entitySimpleNameCompleteGeneric", entityClassParts.simpleNameGeneric);

						JavaMethod entitySetter = null;
						try {
							entitySetter = classQdox.getMethodBySignature("set" + entityVarCapitalized, new ArrayList<JavaType>() {{ add(classQdoxString); }}, true);
						} catch (Exception e) {
						}
						
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
						indexStoreSolr(classLanguageName, entityDoc, "entityCanonicalNameBase", entityCanonicalNameBase);
						if(entityWrap)
							entityClassQdox = builder.getClassByName(entityCanonicalName);

						boolean entityExtendsPagePart = classPartsPagePart != null && entityCanonicalNamesSuperAndMeWithoutGen.contains(classPartsPagePart.canonicalName);
								
						for(String siteWriteMethod : siteWriteMethods) {
							if(entityClassQdox.getMethodBySignature(siteWriteMethod, new ArrayList<JavaType>()) != null
									|| entityClassQdox.getMethodBySignature(siteWriteMethod + classSimpleName, new ArrayList<JavaType>()) != null
									|| entityExtendsPagePart && siteWriteMethod.equals("htmlBody"))
								indexStoreListSolr(entityDoc, "entityWriteMethods",  siteWriteMethod);
							if(entityExtendsPagePart && siteWriteMethod.equals("htmlBody")) {
								for(String languageName : allLanguages) {
									indexStoreListSolr(languageName, classDoc, "classMethodVars", "htmlBody" + entityVarCapitalized);
								}
							}
						}
						
						String entitySimpleNameBase = null;
						if(StringUtils.isNotEmpty(entityCanonicalNameBase)) {
							entitySimpleNameBase = StringUtils.substringAfterLast(entityCanonicalNameBase, ".");
						}
						indexStoreSolr(classLanguageName, entityDoc, "entitySimpleNameBase", entitySimpleNameBase);
						
						String entityVarParam = methodParamsQdox.get(0).getName();
						indexStoreSolr(classLanguageName, entityDoc, "entityVarParam", entityVarParam);
						
						String entityVarWrap = indexStoreSolr(classLanguageName, entityDoc, "entityVarWrap", entityVar + "Wrap");

						Boolean entityInitDeep = indexStoreSolr(entityDoc, "entityInitDeep", !entityVar.endsWith("_"));

						if(entityCanonicalNamesSuperAndMeWithoutGen.size() > 0) {
							String fqSuperClassesAndMe = "(" + qdoxSuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c.getCanonicalName())).collect(Collectors.joining(" OR ")) + ")";

							SolrQuery solrSearchMethodBefore = new SolrQuery();   
							solrSearchMethodBefore.setQuery("*:*");
							solrSearchMethodBefore.setRows(10);
							String fqMethodBefore = "(" + entityCanonicalNamesSuperAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(str_before(languageName) + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							solrSearchMethodBefore.addFilterQuery("entitySuperClassesAndMeWithoutGen_indexed_strings:" + fqSuperClassesAndMe);
							solrSearchMethodBefore.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
							solrSearchMethodBefore.addFilterQuery("partIsMethod_indexed_boolean:true");
							solrSearchMethodBefore.addFilterQuery("methodVar_" + classLanguageName + "_indexed_string:" + fqMethodBefore);
							QueryResponse searchResponseMethodBefore = solrClientComputate.query(solrSearchMethodBefore);
							SolrDocumentList searchListMethodBefore = searchResponseMethodBefore.getResults();
	
							for(SolrDocument solrDocument : searchListMethodBefore) {
								String methodVarCurrent = (String)solrDocument.get("methodVar_" + classLanguageName + "_stored_string");
								String methodClassCanonicalName = (String)solrDocument.get("classCanonicalName_" + classLanguageName + "_stored_string");
								List<String> methodParamsSimpleNameComplete = (List<String>)solrDocument.get("methodParamsSimpleNameComplete_" + classLanguageName + "_stored_strings");
								String methodParamSimpleNameComplete = methodParamsSimpleNameComplete.get(0);
								List<String> methodParamsVar = (List<String>)solrDocument.get("methodParamsVar_" + classLanguageName + "_stored_strings");
								String methodParamVar = methodParamsVar.get(0);
								Boolean entityMethodsBeforeWrite = (StringUtils.equals(methodClassCanonicalName, classCanonicalName)) && !classMethodsWritten.contains(methodVarCurrent);
								storeListSolr(entityDoc, "entityMethodsBeforeVisibility", BooleanUtils.isTrue((Boolean)solrDocument.get("methodIsPublic_stored_boolean")) ? "public" : "protected");
								storeListSolr(entityDoc, "entityMethodsBeforeParamName", methodParamsVar.size() > 1);
								storeListSolr(entityDoc, "entityMethodsBeforeWrite", entityMethodsBeforeWrite);
								classMethodsWritten.add(methodVarCurrent);
								List<String> methodParamCanonicalNames = (List<String>)solrDocument.get("methodParamCanonicalNames_" + classLanguageName + "_stored_strings");
								if(methodParamCanonicalNames != null) {
									String methodParamCanonicalName = methodParamCanonicalNames.get(0);
									classPartsGenAdd(ClassParts.initClassParts(this, methodParamCanonicalName, classLanguageName));
								}
								for(String languageName : allLanguages) {  
									methodVarCurrent = (String)solrDocument.get("methodVar_" + languageName + "_stored_string");
									methodClassCanonicalName = (String)solrDocument.get("classCanonicalName_" + languageName + "_stored_string");
									methodParamsSimpleNameComplete = (List<String>)solrDocument.get("methodParamsSimpleNameComplete_" + languageName + "_stored_strings");
									methodParamSimpleNameComplete = methodParamsSimpleNameComplete.get(0);
									methodParamsVar = (List<String>)solrDocument.get("methodParamsVar_" + languageName + "_stored_strings");
									methodParamVar = methodParamsVar.get(0);
									storeListSolr(languageName, entityDoc, "entityMethodsBeforeVar", methodVarCurrent);
									storeListSolr(languageName, entityDoc, "entityMethodsBeforeParamVar", methodParamVar);
									storeListSolr(languageName, entityDoc, "entityMethodsBeforeSimpleName", methodParamSimpleNameComplete);
								}
							}
	
							SolrQuery solrSearchMethodAfter = new SolrQuery();   
							solrSearchMethodAfter.setQuery("*:*");
							solrSearchMethodAfter.setRows(10);
							String fqMethodAfter = "(" + entityCanonicalNamesSuperAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(str_after(languageName) + StringUtils.substringAfterLast(c, "."))).collect(Collectors.joining(" OR ")) + ")";
							solrSearchMethodAfter.addFilterQuery("entitySuperClassesAndMeWithoutGen_indexed_strings:" + fqSuperClassesAndMe);
							solrSearchMethodAfter.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
							solrSearchMethodAfter.addFilterQuery("partIsMethod_indexed_boolean:true");
							solrSearchMethodAfter.addFilterQuery("methodVar_" + classLanguageName + "_indexed_string:" + fqMethodAfter);
							QueryResponse searchResponseMethodAfter = solrClientComputate.query(solrSearchMethodAfter);
							SolrDocumentList searchListMethodAfter = searchResponseMethodAfter.getResults();
	
							for(SolrDocument solrDocument : searchListMethodAfter) {
								String methodVarCurrent = (String)solrDocument.get("methodVar_" + classLanguageName + "_stored_string");
								String methodClassCanonicalName = (String)solrDocument.get("classCanonicalName_" + classLanguageName + "_stored_string");
								List<String> methodParamsSimpleNameComplete = (List<String>)solrDocument.get("methodParamsSimpleNameComplete_" + classLanguageName + "_stored_strings");
								String methodParamSimpleNameComplete = methodParamsSimpleNameComplete.get(0);
								List<String> methodParamsVar = (List<String>)solrDocument.get("methodParamsVar_" + classLanguageName + "_stored_strings");
								String methodParamVar = methodParamsVar.get(0);
								storeListSolr(entityDoc, "entityMethodsAfterVisibility", BooleanUtils.isTrue((Boolean)solrDocument.get("methodIsPublic_stored_boolean")) ? "public" : "protected");
								storeListSolr(entityDoc, "entityMethodsAfterVar", methodVarCurrent);
								storeListSolr(entityDoc, "entityMethodsAfterParamVar", methodParamVar);
								storeListSolr(entityDoc, "entityMethodsAfterSimpleName", methodParamSimpleNameComplete);
								storeListSolr(entityDoc, "entityMethodsAfterParamName", methodParamsVar.size() > 1);
								Boolean entityMethodsBeforeWrite = (StringUtils.equals(methodClassCanonicalName, classCanonicalName)) && !classMethodsWritten.contains(methodVarCurrent);
								storeListSolr(entityDoc, "entityMethodsBeforeWrite", entityMethodsBeforeWrite);
								classMethodsWritten.add(methodVarCurrent);
								List<String> methodParamCanonicalNames = (List<String>)solrDocument.get("methodParamCanonicalNames_" + classLanguageName + "_stored_strings");
								if(methodParamCanonicalNames != null) {
									String methodParamCanonicalName = methodParamCanonicalNames.get(0);
									classPartsGenAdd(ClassParts.initClassParts(this, methodParamCanonicalName, classLanguageName));
								}
								for(String languageName : allLanguages) {  
									methodVarCurrent = (String)solrDocument.get("methodVar_" + languageName + "_stored_string");
									methodClassCanonicalName = (String)solrDocument.get("classCanonicalName_" + languageName + "_stored_string");
									methodParamsSimpleNameComplete = (List<String>)solrDocument.get("methodParamsSimpleNameComplete_" + languageName + "_stored_strings");
									methodParamSimpleNameComplete = methodParamsSimpleNameComplete.get(0);
									methodParamsVar = (List<String>)solrDocument.get("methodParamsVar_" + languageName + "_stored_strings");
									methodParamVar = methodParamsVar.get(0);
									storeListSolr(languageName, entityDoc, "entityMethodsAfterVar", methodVarCurrent);
									storeListSolr(languageName, entityDoc, "entityMethodsAfterParamVar", methodParamVar);
									storeListSolr(languageName, entityDoc, "entityMethodsAfterSimpleName", methodParamSimpleNameComplete);
								}
							}
						}

						if(methodComment != null) {

							Matcher entityValsSearch = Pattern.compile("^(entity)?Val(:([^:\n]+):)?\\.(\\w+)(\\.([^:\n]+))?:(.*)", Pattern.MULTILINE).matcher(methodComment);
							boolean entityValsFound = entityValsSearch.find();
							while(entityValsFound) {
								String entityValVar = entityValsSearch.group(4);
								String entityValLanguage = entityValsSearch.group(6);
								String entityValCode = entityValsSearch.group(3);
								String entityValValue = entityValsSearch.group(7);
								if(entityValCode == null)
									entityValCode = "";
								if(entityValLanguage == null) {
									storeListSolr(entityDoc, "entityValsVar", entityValVar);
									storeListSolr(entityDoc, "entityValsLanguage", "");
									storeListSolr(entityDoc, "entityValsCode", entityValCode);
									storeListSolr(entityDoc, "entityValsValue", entityValValue);
								}
								else {
									storeListSolr(entityDoc, "entityValsVar", entityValVar);
									storeListSolr(entityDoc, "entityValsLanguage", entityValLanguage);
									storeListSolr(entityDoc, "entityValsCode", entityValCode);
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

						indexStoreSolr(entityDoc, "entityContientSiteRequest", entityClassParts != null && entityClassParts.solrDocument != null && (Boolean)entityClassParts.solrDocument.get("classContainsSiteRequest_stored_boolean"));
						indexStoreSolr(entityDoc, "entityExact", regexFound("^(entity)?Exact:\\s*(true)$", methodComment));
						Boolean entityPrimaryKey = indexStoreSolr(entityDoc, "entityPrimaryKey", regexFound("^(entity)?PrimaryKey:\\s*(true)$", methodComment));
						Boolean entityUniqueKey = indexStoreSolr(entityDoc, "entityUniqueKey", regexFound("^(entity)?UniqueKey:\\s*(true)$", methodComment));
						Boolean entityEncrypted = indexStoreSolr(entityDoc, "entityEncrypted", regexFound("^(entity)?Encrypted:\\s*(true)$", methodComment));
						Boolean entitySuggested = indexStoreSolr(entityDoc, "entitySuggested", regexFound("^(entity)?Suggested:\\s*(true)$", methodComment));
						Boolean entityVarUrl = indexStoreSolr(entityDoc, "entityVarUrl", regexFound("^(entity)?VarUrl:\\s*(true)$", methodComment));
						Boolean entityVarTitle = indexStoreSolr(entityDoc, "entityVarTitle", regexFound("^(entity)?VarTitle:\\s*(true)$", methodComment));
						Boolean entityVarDescription = indexStoreSolr(entityDoc, "entityVarDescription", regexFound("^(entity)?VarDescription:\\s*(true)$", methodComment));
						Boolean entityVarImageUrl = indexStoreSolr(entityDoc, "entityVarImageUrl", regexFound("^(entity)?VarImageUrl:\\s*(true)$", methodComment));
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
						indexStoreSolr(entityDoc, "entityDefined", regexFound("^(entity)?Definir:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityModifier", !regexFound("^(entity)?Modify:\\s*(false)$", methodComment));
						indexStoreSolr(entityDoc, "entityRefresh", regexFound("^(entity)?Refresh:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityMultiline", regexFound("^(entity)?Multiline:\\s*(true)$", methodComment));
						indexStoreSolr(entityDoc, "entityKeys", regexFound("^(entity)?Keys:\\s*(true)$", methodComment));

						String entityLanguage = regex("^(entity)?Language:\\s*(.*)$", methodComment);
						if(entityLanguage != null)
							indexStoreSolr(entityDoc, "entityLanguage", entityLanguage);

						indexStoreSolr(classLanguageName, entityDoc, "entityDisplayName", regexLanguage(classLanguageName, "(entity)?DisplayName", methodComment));
						indexStoreSolr(classLanguageName, entityDoc, "entityDescription", regexLanguage(classLanguageName, "(entity)?Description", methodComment));
						indexStoreSolr(entityDoc, "entityOptional", regexFound("^(entity)?Optional:\\s*(true)$", methodComment));
						indexStoreSolr(classLanguageName, entityDoc, "entityHtmlTooltip", regexLanguage(classLanguageName, "(entity)?HtmlTooltip", methodComment));
						indexStoreSolrRegex(classLanguageName, entityDoc, "entityVarApi", "VarApi", methodComment);
						indexStoreSolr(classLanguageName, entityDoc, "entityEnumSimpleName", regexLanguage(classLanguageName, "(entity)?EnumSimpleName", methodComment));
						indexStoreSolr(classLanguageName, entityDoc, "entityEnumVar", regexLanguage(classLanguageName, "(entity)?EnumVar", methodComment));
						indexStoreSolr(classLanguageName, entityDoc, "entityEnumVarDescription", regexLanguage(classLanguageName, "(entity)?EnumVarDescription", methodComment));

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
								indexStoreSolrRegex(languageName, entityDoc, "entityVarApi", "VarApi", methodComment);
								indexStoreSolr(languageName, entityDoc, "entityEnumVar", regexLanguage(languageName, "(entity)?EnumVar", methodComment));
							}
						}

						Matcher entityAttributeSearch = methodComment == null ? null : Pattern.compile("^(entity)?Attribuer:\\s*([^\\.]+)\\.(.*)\\s*", Pattern.MULTILINE).matcher(methodComment);
						boolean entityAttributeTrouve = entityAttributeSearch == null ? false : entityAttributeSearch.find();
						if(entityAttributeTrouve) {
							String entityAttributeSimpleName = entityAttributeSearch.group(2);
							String entityAttributeVar = entityAttributeSearch.group(3);

							SolrQuery solrSearchClass = new SolrQuery();   
							solrSearchClass.setQuery("*:*");
							solrSearchClass.setRows(1);
							solrSearchClass.addFilterQuery("classSimpleName_" + classLanguageName + "_indexed_string:" + ClientUtils.escapeQueryChars(entityAttributeSimpleName));
							solrSearchClass.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
							solrSearchClass.addFilterQuery("partIsClass_indexed_boolean:true");
							QueryResponse searchResponseClass = solrClientComputate.query(solrSearchClass);
							SolrDocumentList searchListClass = searchResponseClass.getResults();

							if(searchListClass.size() > 0) {
								SolrDocument docClass = searchListClass.get(0);
								String entityAttributeCanonicalName = (String)docClass.get("classCanonicalName_" + classLanguageName + "_stored_string");

								SolrQuery solrSearchVar = new SolrQuery();   
								solrSearchVar.setQuery("*:*");
								solrSearchVar.setRows(1);
								solrSearchVar.addFilterQuery("classCanonicalName_" + classLanguageName + "_indexed_string:" + ClientUtils.escapeQueryChars(entityAttributeCanonicalName));
								solrSearchVar.addFilterQuery("entityVar_" + classLanguageName + "_indexed_string:" + ClientUtils.escapeQueryChars(entityAttributeVar));
								solrSearchVar.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
								solrSearchVar.addFilterQuery("partIsEntity_indexed_boolean:true");
								QueryResponse searchResponseVar = solrClientComputate.query(solrSearchVar);
								SolrDocumentList searchListVar = searchResponseVar.getResults();

								if(searchListVar.size() > 0) {
									SolrDocument docEntity = searchListVar.get(0);

									indexStoreSolr(entityDoc, "entityAttribute", true);
									indexStoreSolr(classLanguageName, entityDoc, "entityAttributeSimpleName", entityAttributeSimpleName);
									indexStoreSolr(classLanguageName, entityDoc, "entityAttributeCanonicalName", entityAttributeCanonicalName);
									indexStoreSolr(classLanguageName, entityDoc, "entityAttributeVar", entityAttributeVar);
									indexStoreSolr(classLanguageName, entityDoc, "entityAttributeVarSuggest", (String)docClass.get("classVarSuggest_" + classLanguageName + "_stored_string"));
									indexStoreSolr(classLanguageName, entityDoc, "entityAttributeVarUrl", (String)docClass.get("classVarUrl_" + classLanguageName + "_stored_string"));
									indexStoreSolr(classLanguageName, entityDoc, "entityAttributeVarTitle", (String)docClass.get("classVarTitle_" + classLanguageName + "_stored_string"));
									indexStoreSolr(classLanguageName, entityDoc, "entityAttributeVarDescription", (String)docClass.get("classVarDescription_" + classLanguageName + "_stored_string"));
									indexStoreSolr(classLanguageName, entityDoc, "entityAttributeVarImageUrl", (String)docClass.get("classVarImageUrl_" + classLanguageName + "_stored_string"));

									
									String entityOperationIdPATCH = regexLanguage(classLanguageName, "(class)?ApiOperationIdPATCH", classComment, "patch" + classSimpleName);
									if(entityOperationIdPATCH != null)
										indexStoreSolr(classLanguageName, entityDoc, "entityOperationIdPATCH", entityOperationIdPATCH);

									String entityAttributeTypeJson = (String)docEntity.get("entityJsonType_stored_string");
									if(entityAttributeTypeJson != null)
										indexStoreSolr(entityDoc, "entityAttributeTypeJson", entityAttributeTypeJson);

									String entityAttributeOperationIdPATCH = (String)docClass.get("classApiOperationIdPATCH_" + classLanguageName + "_stored_string");
									if(entityAttributeOperationIdPATCH != null)
										indexStoreSolr(classLanguageName, entityDoc, "entityAttributeOperationIdPATCH", entityAttributeOperationIdPATCH);
									String entityAttributeOperationIdSearch = (String)docClass.get("classApiOperationId" + str_Search(classLanguageName) + "_" + classLanguageName + "_stored_string");
									if(entityAttributeOperationIdSearch != null)
										indexStoreSolr(classLanguageName, entityDoc, "entityAttributeOperationId" + str_Search(classLanguageName), entityAttributeOperationIdSearch);

									String classAttributeSimpleNamePage = (String)docClass.get("classPageSimpleName" + str_PageSearch(classLanguageName) + "_" + classLanguageName + "_stored_string");
									if(classAttributeSimpleNamePage != null)
										indexStoreListSolr(classLanguageName, classDoc, "classAttributeSimpleNamePages", classAttributeSimpleNamePage);

									if(classTranslate) {
										for(String languageName : classOtherLanguages) {  
											String entityAttributeCanonicalNameLangue = (String)docEntity.get("classCanonicalName_" + languageName + "_stored_string");
											String entityAttributeSimpleNameLangue = (String)docEntity.get("classSimpleName_" + languageName + "_stored_string");
											String entityAttributeVarLangue = (String)docEntity.get("entityVar_" + languageName + "_stored_string");
											String classSimpleNameLanguage = (String)Optional.ofNullable(classDoc.get("classSimpleName_" + languageName + "_stored_string")).map(SolrInputField::getValue).orElse(null);
	
											indexStoreSolr(languageName, entityDoc, "entityAttributeSimpleName", entityAttributeSimpleNameLangue);
											indexStoreSolr(languageName, entityDoc, "entityAttributeCanonicalName", entityAttributeCanonicalNameLangue);
											indexStoreSolr(languageName, entityDoc, "entityAttributeVar", entityAttributeVarLangue);
											indexStoreSolr(languageName, entityDoc, "entityAttributeVarSuggest", (String)docClass.get("classVarSuggest_" + languageName + "_stored_string"));
											indexStoreSolr(languageName, entityDoc, "entityAttributeVarUrl", (String)docClass.get("classVarUrl_" + languageName + "_stored_string"));
											indexStoreSolr(languageName, entityDoc, "entityAttributeVarTitle", (String)docClass.get("classVarTitle_" + languageName + "_stored_string"));
											indexStoreSolr(languageName, entityDoc, "entityAttributeVarDescription", (String)docClass.get("classVarDescription_" + languageName + "_stored_string"));
											indexStoreSolr(languageName, entityDoc, "entityAttributeVarImageUrl", (String)docClass.get("classVarImageUrl_" + languageName + "_stored_string"));
											
											entityOperationIdPATCH = regexLanguage(languageName, "(class)?ApiOperationIdPATCH", classComment, "patch" + classSimpleNameLanguage);
											if(entityOperationIdPATCH != null)
												indexStoreSolr(languageName, entityDoc, "entityOperationIdPATCH", entityOperationIdPATCH);

											entityAttributeOperationIdPATCH = (String)docClass.get("classApiOperationIdPATCH_" + languageName + "_stored_string");

											classAttributeSimpleNamePage = (String)docClass.get("classPageSimpleName" + str_PageSearch(languageName) + "_" + languageName + "_stored_string");
											if(classAttributeSimpleNamePage != null)
												indexStoreListSolr(languageName, classDoc, "classAttributeSimpleNamePages", classAttributeSimpleNamePage);

											if(entityAttributeOperationIdPATCH != null)
												indexStoreSolr(languageName, entityDoc, "entityAttributeOperationIdPATCH", entityAttributeOperationIdPATCH);
											entityAttributeOperationIdSearch = (String)docClass.get("classApiOperationId" + str_Search(languageName) + "_" + languageName + "_stored_string");
											if(entityAttributeOperationIdSearch != null)
												indexStoreSolr(languageName, entityDoc, "entityAttributeOperationId" + str_Search(languageName), entityAttributeOperationIdSearch);
										}
									}
								}
							}
						}
						
						for(JavaAnnotation annotation : annotations) {
							String entityAnnotationLanguage = indexStoreSolr(classLanguageName, entityDoc, "entityAnnotations", annotation.getType().getCanonicalName());
						}
	
						String entityKey = classAbsolutePath + "." + entityVar;
		
						// Entites Solr du entity. 
		
						entityDoc.addField("id", entityKey);
						indexStoreSolr(entityDoc, "partIsEntity", true);
						indexStoreSolr(entityDoc, "partNumber", partNumber);

						String entitySourceCode = methodQdox.getSourceCode();
						String entityString = regex("^(entity)?String\\." + classLanguageName + ":(.*)", methodComment);
						if(entityString != null) {
							entitySourceCode = "\n\t\tc.o(\"" + StringUtils.replace(StringUtils.replace(entityString, "\\", "\\\\"), "\"", "\\\"") + "\");\n\t";
							indexStoreSolr(classLanguageName, entityDoc, "entityString", entityString); 
						}
						storeSolr(classLanguageName, entityDoc, "entitySourceCode", entitySourceCode); 

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
							entitySimpleNameVertxJson = "String";
							entityCanonicalNameVertxJson = VAL_canonicalNameInstant;
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneId", classLanguageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneOffset", classLanguageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.LocalDateTime", classLanguageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZonedDateTime", classLanguageName));
							classPartsGenAdd(ClassParts.initClassParts(this, VAL_canonicalNameDate, classLanguageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", classLanguageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.util.Locale", classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.util.Locale", classLanguageName));
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLocalDate)) {
							entitySimpleNameVertxJson = "String";
							entityCanonicalNameVertxJson = VAL_canonicalNameInstant;
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneId", classLanguageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.ZoneOffset", classLanguageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.LocalDate", classLanguageName));
							classPartsGenAdd(ClassParts.initClassParts(this, VAL_canonicalNameDate, classLanguageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", classLanguageName));
							classPartsGenAdd(ClassParts.initClassParts(this, "java.util.Locale", classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.util.Locale", classLanguageName));
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLong)) {
							entitySimpleNameVertxJson = "Long";
							entityCanonicalNameVertxJson = VAL_canonicalNameLong;
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameBigDecimal)) {
							entitySimpleNameVertxJson = "Double";
							entityCanonicalNameVertxJson = VAL_canonicalNameLong;
							classPartsGenAdd(ClassParts.initClassParts(this, NumberUtils.class.getCanonicalName(), classLanguageName));
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
								entityListSimpleNameVertxJson = "String";
								entityListCanonicalNameVertxJson = VAL_canonicalNameInstant;
							}
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameLocalDate)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entityListSimpleNameVertxJson = "String";
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
								classPartsGenAdd(ClassParts.initClassParts(this, NumberUtils.class.getCanonicalName(), classLanguageName));
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
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameString)) {
								entitySimpleNameVertxJson = "JsonArray";
								entityCanonicalNameVertxJson = VAL_canonicalNameVertxJsonArray;
								entityListSimpleNameVertxJson = "String";
								entityListCanonicalNameVertxJson = VAL_canonicalNameString;
							}
							storeSolr(entityDoc, "entityListSimpleNameVertxJson", entityListSimpleNameVertxJson);
							storeSolr(entityDoc, "entityListCanonicalNameVertxJson", entityListCanonicalNameVertxJson);
							classPartsGenAdd(ClassParts.initClassParts(this, entityListCanonicalNameVertxJson, classLanguageName));
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameString)) {
							entitySimpleNameVertxJson = "String";
							entityCanonicalNameVertxJson = VAL_canonicalNameString;
						}
						classPartsGenAdd(ClassParts.initClassParts(this, entityCanonicalNameVertxJson, classLanguageName));
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
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameString)) {
								entitySolrCanonicalName = VAL_canonicalNameList + "<" + VAL_canonicalNameString + ">";
								entitySolrSimpleName = "List<" + StringUtils.substringAfterLast(VAL_canonicalNameString, ".") + ">";
								entityTypeSuffix = "_strings";
							}
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameString)) {
							entitySolrCanonicalName = VAL_canonicalNameString;
							entitySolrSimpleName = StringUtils.substringAfterLast(entitySolrCanonicalName, ".");
							entityTypeSuffix = "_string";
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
							//TODO: ctate disabled until vertx fix is made. 
//							entityJsonFormat = "date-time";
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameLocalDate)) {
							entityJsonType = "string";
							//TODO: ctate disabled until vertx fix is made. 
//							entityJsonFormat = "date";
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
							else if(StringUtils.equalsAny(entityCanonicalNameGeneric, VAL_canonicalNameString)) {
								entityJsonType = "array";
								entityListJsonType = "string";
							}
							storeSolr(entityDoc, "entityListJsonType", entityListJsonType);
						}
						else if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameString)) {
							entityJsonType = "string";
						}
						storeSolr(entityDoc, "entityJsonType", entityJsonType);
						if(entityJsonFormat != null)
							storeSolr(entityDoc, "entityJsonFormat", entityJsonFormat);

						if(entityPrimaryKey) {
							classVarPrimaryKey = storeSolr(classLanguageName, classDoc, "classVarPrimaryKey", entityVar);
						}
						if(entityUniqueKey) {
							classVarUniqueKey = storeSolr(classLanguageName, classDoc, "classVarUniqueKey", entityVar);
						}
						if(entitySuggested) {
							classVarSuggest = storeSolr(classLanguageName, classDoc, "classVarSuggest", entityVar);
						}
						if(entityVarUrl) {
							classVarUrl = storeSolr(classLanguageName, classDoc, "classVarUrl", entityVar);
						}
						if(entityVarTitle) {
							classVarTitle = storeSolr(classLanguageName, classDoc, "classVarTitle", entityVar);
						}
						if(entityVarDescription) {
							classVarDescription = storeSolr(classLanguageName, classDoc, "classVarDescription", entityVar);
						}
						if(entityVarImageUrl) {
							classVarImageUrl = storeSolr(classLanguageName, classDoc, "classVarImageUrl", entityVar);
						}
				
						if(classTranslate) {
							for(String languageName : classOtherLanguages) {  
								ClassParts entityClassPartsLangue = ClassParts.initClassParts(this, entityClassParts, languageName);
					
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
								if(entitySuggested) {
									storeSolr(languageName, classDoc, "classVarSuggest", entityVarLangue);
								}
								if(entityPrimaryKey) {
									storeSolr(languageName, classDoc, "classVarPrimaryKey", entityVarLangue);
								}
								if(entityUniqueKey) {
									storeSolr(languageName, classDoc, "classVarUniqueKey", entityVarLangue);
								}
								if(entityVarUrl) {
									classVarUrl = storeSolr(languageName, classDoc, "classVarUrl", entityVarLangue);
								}
								if(entityVarTitle) {
									classVarTitle = storeSolr(languageName, classDoc, "classVarTitle", entityVarLangue);
								}
								if(entityVarDescription) {
									classVarDescription = storeSolr(languageName, classDoc, "classVarDescription", entityVarLangue);
								}
								if(entityVarImageUrl) {
									classVarImageUrl = storeSolr(languageName, classDoc, "classVarImageUrl", entityVarLangue);
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
							ClassParts methodExceptionClassParts = ClassParts.initClassParts(this, methodExceptionQdox.getCanonicalName(), classLanguageName);
							if(!classInitDeepExceptions.contains(methodExceptionClassParts.canonicalNameComplete))
								classInitDeepExceptions.add(methodExceptionClassParts.canonicalNameComplete);
							storeListSolr(entityDoc, "methodExceptionsSimpleNameComplete", methodExceptionSimpleNameComplete);
							classPartsGenAdd(methodExceptionClassParts);
							if(classTranslate) {
								for(String languageName : classOtherLanguages) {  
									ClassParts methodExceptionClassPartsLangue = ClassParts.initClassParts(this, methodExceptionQdox.getCanonicalName(), languageName);
									storeListSolr(entityDoc, "methodExceptionsSimpleNameComplete", methodExceptionClassPartsLangue.simpleNameComplete);
								}
							}
						}

						solrClientComputate.add(entityDoc); 
					}
					else {  
						// est Methode. 
						
						SolrInputDocument methodDoc = classDocClone.deepCopy();
						indexStoreSolr(classLanguageName, methodDoc, "methodVar", methodVar);
						indexStoreListSolr(classLanguageName, classDoc, "classMethodVars", methodVar);
						for(Integer methodParamNum = 1; methodParamNum <= methodParamsQdox.size(); methodParamNum++) {
							JavaParameter methodParamQdox = methodParamsQdox.get(methodParamNum - 1);
							String methodParamVar = methodParamQdox.getName();
							storeListSolr(classLanguageName, methodDoc, "methodParamsVar", methodParamVar);
							ClassParts methodParamsClassPart = ClassParts.initClassParts(this, methodParamQdox.getJavaClass(), classLanguageName);
							storeListSolr(classLanguageName, methodDoc, "methodParamCanonicalNames", methodParamsClassPart.canonicalName);
							storeListSolr(classLanguageName, methodDoc, "methodParamsSimpleNameComplete", methodParamsClassPart.simpleNameComplete);
							storeListSolr(methodDoc, "methodParamsVariableArgs", methodParamQdox.isVarArgs());
							if(classTranslate) {
								for(String languageName : classOtherLanguages) { 
									String methodParamVarLanguage = regex("^(method)?Param" + methodParamNum + "\\.var\\." + languageName + ": (.*)", methodComment);
									if(methodParamVarLanguage == null)
										methodParamVarLanguage = methodParamVar;
									ClassParts methodParamClassPartsLanguage = ClassParts.initClassParts(this, methodParamsClassPart, languageName);
	
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
							ClassParts methodAnnotationClassParts = ClassParts.initClassParts(this, annotation.getType(), classLanguageName);
							storeListSolr(classLanguageName, methodDoc, "methodAnnotationsSimpleNameComplet", methodAnnotationClassParts.simpleNameComplete);
							storeListSolr(classLanguageName, methodDoc, "methodAnnotationsBlocCode", StringUtils.substringAfter(annotation.toString(), methodAnnotationClassParts.simpleName));
							if(classTranslate) {
								for(String languageName : classOtherLanguages) {  
									ClassParts methodAnnotationClassPartsLangue = ClassParts.initClassParts(this, methodAnnotationClassParts, languageName);
									storeListSolr(languageName, methodDoc, "methodAnnotationsSimpleNameComplet", methodAnnotationClassPartsLangue.simpleNameComplete);
									storeListSolr(languageName, methodDoc, "methodAnnotationsBlocCode", StringUtils.substringAfter(annotation.toString(), methodAnnotationClassParts.simpleName));
								}
							}
						}

						for(JavaClass methodExceptionQdox : methodExceptionsQdox) { 
							String methodExceptionSimpleNameComplete = StringUtils.substringAfterLast(methodExceptionQdox.getCanonicalName(), ".");
							storeListSolr(methodDoc, "methodExceptionsSimpleNameComplete", methodExceptionSimpleNameComplete);
							if(classTranslate) {
								for(String languageName : classOtherLanguages) {  
									ClassParts methodExceptionClassPartsLangue = ClassParts.initClassParts(this, methodExceptionQdox.getCanonicalName(), languageName);
									storeListSolr(methodDoc, "methodExceptionsSimpleNameComplete", methodExceptionClassPartsLangue.simpleNameComplete);
								}
							}
						}

						Boolean methodIsVoid = false;
						if(classReturnQdox != null && !classReturnQdox.getCanonicalName().equals("void")) {
							ClassParts methodReturnClassParts = ClassParts.initClassParts(this, methodQdox.getReturns(), classLanguageName);
							storeSolr(classLanguageName, methodDoc, "methodReturnSimpleNameComplete", methodReturnClassParts.simpleNameComplete);
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
		
						// Methodes Solr du method. 
		
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
						storeRegexComments(classLanguageName, methodDoc, "methodComment", methodComment);
	
						String methodVarLanguage = regex("^(method)?Var\\." + classLanguageName + ": (.*)", methodComment);
						methodVarLanguage =  methodVarLanguage == null ? methodVar : methodVarLanguage;
	
						regexList("^" + classLanguageName + ":\\s*([^\n]+)", methodComment);

						String methodSourceCode = methodQdox.getSourceCode();
						String methodSourceCodeLanguage = methodSourceCode;
						String methodString = regex("^(method)?String\\." + classLanguageName + ":(.*)", methodComment);
						if(methodString != null) {
							methodSourceCode = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodString, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
							indexStoreSolr(classLanguageName, methodDoc, "methodString", methodString); 
						}
						storeSolr(classLanguageName, methodDoc, "methodSourceCode", methodSourceCode);

						if(classTranslate) {
							for(String languageName : classOtherLanguages) {  
								methodVarLanguage = regex("^(method)?Var\\." + languageName + ":\\s*([^\n]+)", methodComment);
								methodVarLanguage = indexStoreSolr(languageName, methodDoc, "methodVar", methodVarLanguage == null ? methodVar : methodVarLanguage);
								regexList("^" + languageName + ":\\s*([^\n]+)", methodComment);
								methodSourceCodeLanguage = regexReplaceAll(methodComment, methodSourceCode, languageName);
								indexStoreListSolr(languageName, classDoc, "classMethodVars", methodVarLanguage);
								String methodStringLangue = regex("^(method)?String\\." + languageName + ":(.*)", methodComment);
								if(methodStringLangue != null) {
									methodSourceCodeLanguage = "\n\t\treturn \"" + StringUtils.replace(StringUtils.replace(methodStringLangue, "\\", "\\\\"), "\"", "\\\"") + "\";\n\t";
									indexStoreSolr(languageName, methodDoc, "methodString", methodStringLangue); 
								}
								storeSolr(languageName, methodDoc, "methodSourceCode", methodSourceCodeLanguage);
								storeRegexComments(languageName, methodDoc, "methodComment", methodComment);
							} 
						}

						if(methodComment != null) {

							Matcher methodValsSearch = Pattern.compile("^(method)?Val(:([^:\n]+):)?\\.(\\w+)(\\.([^:\n]+))?:(.*)", Pattern.MULTILINE).matcher(methodComment);
							boolean methodValsFound = methodValsSearch.find();
							while(methodValsFound) {
								String methodValVar = methodValsSearch.group(4);
								String methodValLanguage = methodValsSearch.group(6);
								String methodValCode = methodValsSearch.group(3);
								String methodValValue = methodValsSearch.group(7);
								if(methodValCode == null)
									methodValCode = "";
								if(methodValLanguage == null) {
									storeListSolr(methodDoc, "methodValsVar", methodValVar);
									storeListSolr(methodDoc, "methodValsLanguage", "");
									storeListSolr(methodDoc, "methodValsCode", methodValCode);
									storeListSolr(methodDoc, "methodValsValue", methodValValue);
								}
								else {
									storeListSolr(methodDoc, "methodValsVar", methodValVar);
									storeListSolr(methodDoc, "methodValsLanguage", methodValLanguage);
									storeListSolr(methodDoc, "methodValsCode", methodValCode);
									storeListSolr(methodDoc, "methodValsValue", methodValValue);
								}
								methodValsFound = methodValsSearch.find();
							}
						}
	
						solrClientComputate.add(methodDoc); 
					}
				}
			}
		}

		if(classVarSuggest == null && classSuperDoc != null) {
			classVarSuggest = (String)classSuperDoc.get("classVarSuggest_" + classLanguageName + "_stored_string");
			if(classVarSuggest != null) {
				storeSolr(classLanguageName, classDoc, "classVarSuggest", classVarSuggest);
				if(classTranslate) {
					for(String languageName : classOtherLanguages) {  
						String classVarSuggestLangue = (String)classSuperDoc.get("classVarSuggest_" + languageName + "_stored_string");
						if(classVarSuggestLangue != null) {
							storeSolr(languageName, classDoc, "classVarSuggest", classVarSuggestLangue);
						}
					}
				}
			}
		}
		if(classVarPrimaryKey == null && classSuperDoc != null) {
			classVarPrimaryKey = (String)classSuperDoc.get("classVarPrimaryKey_" + classLanguageName + "_stored_string");
			if(classVarPrimaryKey != null) {
				storeSolr(classLanguageName, classDoc, "classVarPrimaryKey", classVarPrimaryKey);
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
			classVarUniqueKey = (String)classSuperDoc.get("classVarUniqueKey_" + classLanguageName + "_stored_string");
			if(classVarUniqueKey != null) {
				storeSolr(classLanguageName, classDoc, "classVarUniqueKey", classVarUniqueKey);
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
		
		ClassParts classPartsWrap = classPartsWrap(domainPackageName, classLanguageName);
		classPartsGenAdd(classPartsWrap);

		ArrayList<String> classApiMethods = regexList("^(class)?ApiMethod:\\s*(.*)", classComment);
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

			for(String languageName : allLanguages) {
				String classApiUri = indexStoreSolrRegex(languageName, classDoc, "classApiUri", "ApiUri", classComment);
				String classApiTag = indexStoreSolrRegex(languageName, classDoc, "classApiTag", "ApiTag", classComment);
				String classSimpleNameLanguage = (String)classDoc.get("classSimpleName_" + languageName + "_stored_string").getValue();

				classPartsGenApi.clear();
		
				classPartsGenApiAdd(classPartsSiteConfig);
				classPartsGenApiAdd(classPartsSiteRequest);
				classPartsGenApiAdd(classPartsSiteContext);
				classPartsGenApiAdd(classPartsSiteUser);
				classPartsGenApiAdd(classPartsSearchResult);
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.io.IOException", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Collections", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Map", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.concurrent.TimeUnit", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.stream.Collectors", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.json.Json", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrQuery", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrQuery.ORDER", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.response.QueryResponse", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.util.ClientUtils", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.StringUtils", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.security.Principal", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.io.PrintWriter", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.common.SolrDocumentList", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.solr.common.SolrDocument", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Collection", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.math.BigDecimal", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Date", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.time.format.DateTimeFormatter", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.time.ZoneId", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.List", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.ArrayList", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Arrays", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Set", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Handler", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.RoutingContext", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.Router", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Vertx", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.reactivestreams.ReactiveReadStream", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.reactivestreams.ReactiveWriteStream", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.MultiMap", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.auth.oauth2.OAuth2Auth", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.netty.handler.codec.http.HttpResponseStatus", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.Logger", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.logging.LoggerFactory", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.validation.HTTPRequestValidationHandler", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.validation.ParameterTypeValidator", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.validation.ValidationException", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLClient", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLConnection", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonArray", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonObject", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.time.LocalDateTime", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.sql.Timestamp", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Future", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.http.CaseInsensitiveHeaders", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.AsyncResult", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.Handler", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.buffer.Buffer", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.OperationResponse", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.core.CompositeFuture", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.http.client.utils.URLEncodedUtils", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.nio.charset.Charset", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "org.apache.http.NameValuePair", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.OperationRequest", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.auth.oauth2.KeycloakHelper", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "io.vertx.ext.sql.SQLConnection", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.Optional", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.util.stream.Stream", classLanguageName));
				classPartsGenApiAdd(ClassParts.initClassParts(this, "java.net.URLDecoder", classLanguageName));
				classPartsGenApiAdd(classPartsSearchList);
				classPartsGenApiAdd(classPartsAllWriter);

				Matcher classApiMethodsRegex = Pattern.compile("^(class)?ApiMethod(\\.([^:\n]+))?:\\s*(.*)", Pattern.MULTILINE).matcher(classComment);
				boolean classApiMethodsTrouves = classApiMethodsRegex.find();
				while(classApiMethodsTrouves) {
					String classApiMethod = classApiMethodsRegex.group(4);
					String classApiMethodLangue = classApiMethodsRegex.group(3);
	
					if(classApiMethodLangue == null || StringUtils.equals(classApiMethodLangue, languageName)) {

						indexStoreListSolr(languageName, classDoc, "classApiMethods", classApiMethod); 
		
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
		
						indexStoreSolr(languageName, classDoc, "classApiMethod" + classApiMethod, regex("^(class)?ApiMethod" + classApiMethod + "." + languageName + ":\\s*(.*)", classComment, classApiMethodMethode));
		
						String classApiUriMethode = regexLanguage(languageName, "(class)?ApiUri" + classApiMethod + "." + languageName, classComment);
		
						indexStoreSolrRegex(languageName, classDoc, "classApiOperationId" + classApiMethod, "ApiOperationId" + classApiMethod + "." + languageName, classComment, StringUtils.lowerCase(classApiMethod) + classSimpleNameLanguage);
						indexStoreSolrRegex(languageName, classDoc, "classApiOperationId" + classApiMethod + "Request", "ApiOperationId" + classApiMethod + "Request" + "." + languageName, classComment, classApiMethod + classSimpleNameLanguage + str_Request(languageName));
						indexStoreSolrRegex(languageName, classDoc, "classApiOperationId" + classApiMethod + "Response", "ApiOperationId" + classApiMethod + "Response" + "." + languageName, classComment, classApiMethod + classSimpleNameLanguage + str_Response(languageName));
						indexStoreSolrRegex(languageName, classDoc, "classApiDescription" + classApiMethod, "ApiDescription" + classApiMethod + "." + languageName, classComment, regexLanguage(languageName, "(class)?Description" + classApiMethod, classComment));
		
						if(classExtendsBase && classSuperDoc != null) {
							indexStoreSolr(languageName, classDoc, "classSuperApiOperationId" + classApiMethod, (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "_" + languageName + "_stored_string"));
							indexStoreSolr(languageName, classDoc, "classSuperApiOperationId" + classApiMethod + "Request", (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "Request" + "_" + languageName + "_stored_string"));
							indexStoreSolr(languageName, classDoc, "classSuperApiOperationId" + classApiMethod + "Response", (String)classSuperDoc.get("classApiOperationId" + classApiMethod + "Response" + "_" + languageName + "_stored_string"));
						}
		
						String classPageSimpleNameMethode = regexLanguage(languageName, "^(class)?Page" + classApiMethod, classComment);
						String classPageSuperSimpleNameMethode = regexLanguage(languageName, "^(class)?PageSuper" + classApiMethod, classComment);
						String classApiMediaType200Methode = regexLanguage(languageName, "^(class)?ApiTypeMedia200" + classApiMethod, classComment, classPageSimpleNameMethode == null ? "application/json" : "text/html");
						String classApiKeywordMethod = regexLanguage(languageName, "(class)?ApiKeyword" + classApiMethod, classComment);
						if(StringUtils.contains(classApiMethod, "POST")
								|| StringUtils.contains(classApiMethod, str_Search(languageName))
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

						if(this.languageName.equals(classLanguageName))
							indexStoreSolr(languageName, classDoc, "classApiMediaType200" + classApiMethod, classApiMediaType200Methode);
						indexStoreSolr(languageName, classDoc, "classApiKeyword" + classApiMethod, classApiKeywordMethod);
						indexStoreSolr(languageName, classDoc, "classApiUri" + classApiMethod, classApiUriMethode);
						if(classPageSimpleNameMethode != null) {
							String classPageLanguageName = null;

							classPartsGenPage.clear();
		
							SolrQuery searchPage = new SolrQuery();   
							searchPage.setQuery("*:*");
							searchPage.setRows(1);
							searchPage.addFilterQuery("classSimpleName_" + languageName + "_indexed_string:" + ClientUtils.escapeQueryChars(classPageSimpleNameMethode));
							searchPage.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
							searchPage.addFilterQuery("partIsClass_indexed_boolean:true");
							QueryResponse searchResponsePage = solrClientComputate.query(searchPage);
							SolrDocumentList searchListPage = searchResponsePage.getResults();
		
							if(searchListPage.size() > 0) {
								SolrDocument docEntity = searchListPage.get(0);
								String classPackageNameLanguage = (String)classDoc.get("classPackageName_" + languageName + "_indexed_string").getValue();
								String classPageCanonicalNameMethode = (String)docEntity.get("classCanonicalName_" + languageName + "_stored_string");
								indexStoreSolr(languageName, classDoc, "classPageCanonicalName" + classApiMethod, classPageCanonicalNameMethode);
								indexStoreSolr(languageName, classDoc, "classPageSimpleName" + classApiMethod, classPageSimpleNameMethode);
	
								String classGenPageSimpleName;
								String classPageCheminGen;
								if(StringUtils.contains(classPageSimpleNameMethode, "Page")) {
									classGenPageSimpleName = StringUtils.substringBeforeLast(classPageSimpleNameMethode, "Page") + "GenPage" + StringUtils.substringAfterLast(classPageSimpleNameMethode, "Page");
									classPageCheminGen = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classGenPageSimpleName, ".java");
								}
								else {
									classGenPageSimpleName = "Gen" + classPageSimpleNameMethode;
									classPageCheminGen = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classGenPageSimpleName, ".java");
								}
								indexStoreSolr(languageName, classDoc, "classGenPageSimpleName" + classApiMethod, classGenPageSimpleName);
								String classPageChemin = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classPageSimpleNameMethode, ".java");
								indexStoreSolr(languageName, classDoc, "classPageCheminGen" + classApiMethod, classPageCheminGen); 
								indexStoreSolr(languageName, classDoc, "classPageChemin" + classApiMethod, classPageChemin); 
								classPageLanguageName = languageName;

//								classPartsGenApiAdd(ClassParts.initClassParts(this, classPageCanonicalNameMethode, classPageLanguageName));
							}

							if(classPageLanguageName == null) {
								String classPackageNameLanguage = (String)classDoc.get("classPackageName_" + languageName + "_indexed_string").getValue();
								String classGenPageSimpleName;
								String classPageCheminGen;
								String classPageChemin;

								if(StringUtils.contains(classPageSimpleNameMethode, "Page")) {
									classGenPageSimpleName = StringUtils.substringBeforeLast(classPageSimpleNameMethode, "Page") + "GenPage" + StringUtils.substringAfterLast(classPageSimpleNameMethode, "Page");
									classPageCheminGen = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classGenPageSimpleName, ".java");
									classPageChemin = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classPageSimpleNameMethode, ".java");
								}
								else {
									classGenPageSimpleName = "Gen" + classPageSimpleNameMethode;
									classPageCheminGen = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classGenPageSimpleName, ".java");
									classPageChemin = concat(srcMainJavaPath, "/", StringUtils.replace(classPackageNameLanguage, ".", "/"), "/", classPageSimpleNameMethode, ".java");
								}
								indexStoreSolr(languageName, classDoc, "classGenPageSimpleName" + classApiMethod, classGenPageSimpleName);
								indexStoreSolr(languageName, classDoc, "classPageCheminGen" + classApiMethod, classPageCheminGen); 
								indexStoreSolr(languageName, classDoc, "classPageChemin" + classApiMethod, classPageChemin); 
								classPageLanguageName = languageName;

								String classPageCanonicalNameMethode = classPackageName + "." + classPageSimpleNameMethode;
								
								indexStoreSolr(languageName, classDoc, "classPageCanonicalName" + classApiMethod, classPageCanonicalNameMethode);
								indexStoreSolr(languageName, classDoc, "classPageSimpleName" + classApiMethod, classPageSimpleNameMethode);
							}

							if(classPageSuperSimpleNameMethode != null) {
								SolrQuery searchPageSuper = new SolrQuery();   
								searchPageSuper.setQuery("*:*");
								searchPageSuper.setRows(1);
								searchPageSuper.addFilterQuery("classSimpleName_" + classPageLanguageName + "_indexed_string:" + ClientUtils.escapeQueryChars(classPageSuperSimpleNameMethode));
								searchPageSuper.addFilterQuery("domainPackageName_indexed_string:" + ClientUtils.escapeQueryChars(domainPackageName));
								searchPageSuper.addFilterQuery("partIsClass_indexed_boolean:true");
								searchPageSuper.addFilterQuery("languageName_indexed_string:" + ClientUtils.escapeQueryChars(classPageLanguageName));
								QueryResponse searchResponsePageSuper = solrClientComputate.query(searchPageSuper);
								SolrDocumentList searchListPageSuper = searchResponsePageSuper.getResults();
			
								if(searchListPageSuper.size() > 0) {
									SolrDocument docPageSuper = searchListPageSuper.get(0);
									String classPageSuperCanonicalNameMethode = (String)docPageSuper.get("classCanonicalName_" + classPageLanguageName + "_stored_string");
									indexStoreSolr(languageName, classDoc, "classPageSuperCanonicalName" + classApiMethod, classPageSuperCanonicalNameMethode);
									indexStoreSolr(languageName, classDoc, "classPageSuperSimpleName" + classApiMethod, classPageSuperSimpleNameMethode);
									classPartsGenPageAdd(ClassParts.initClassParts(this, classPageSuperCanonicalNameMethode, classPageLanguageName));
								}
								else {
									indexStoreSolr(languageName, classDoc, "classPageSuperCanonicalName" + classApiMethod, (String)classPartsPageLayout.solrDocument.get("classCanonicalName_" + classPageLanguageName + "_stored_string"));
									indexStoreSolr(languageName, classDoc, "classPageSuperSimpleName" + classApiMethod, (String)classPartsPageLayout.solrDocument.get("classSimpleName_" + classPageLanguageName + "_stored_string"));
									classPartsGenPageAdd(classPartsPageLayout);
								}
							}
							else {
								indexStoreSolr(languageName, classDoc, "classPageSuperCanonicalName" + classApiMethod, (String)classPartsPageLayout.solrDocument.get("classCanonicalName_" + classPageLanguageName + "_stored_string"));
								indexStoreSolr(languageName, classDoc, "classPageSuperSimpleName" + classApiMethod, (String)classPartsPageLayout.solrDocument.get("classSimpleName_" + classPageLanguageName + "_stored_string"));
								classPartsGenPageAdd(classPartsPageLayout);
							}
	
							String classPageCheminCss = concat(appPath, "-static/css/", languageName, "/", classPageSimpleNameMethode, ".css");
							String classPageCheminJs = concat(appPath, "-static/js/", languageName, "/", classPageSimpleNameMethode, ".js");
				
							indexStoreSolr(languageName, classDoc, "classPageCheminCss" + classApiMethod, classPageCheminCss); 
							indexStoreSolr(languageName, classDoc, "classPageCheminJs" + classApiMethod, classPageCheminJs); 
							indexStoreSolr(languageName, classDoc, "classPageLanguageName" + classApiMethod, classPageLanguageName); 
							classPage = true;
					
							classPartsGenPageAdd(classPartsSiteConfig);
							classPartsGenPageAdd(classPartsSiteRequest);
							classPartsGenPageAdd(classPartsSiteContext);
							classPartsGenPageAdd(classPartsSiteUser);
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.io.IOException", classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.core.http.HttpServerRequest", classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.core.http.HttpServerResponse", classLanguageName));
							classPartsGenPageAdd(classPartsSearchList);
							classPartsGenPageAdd(classPartsWrap);
							classPartsGenPageAdd(classPartsPageLayout);
							classPartsGenPageAdd(ClassParts.initClassParts(this, LocalDateTime.class.getCanonicalName(), classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, LocalDate.class.getCanonicalName(), classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, ZonedDateTime.class.getCanonicalName(), classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, DateTimeFormatter.class.getCanonicalName(), classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, Locale.class.getCanonicalName(), classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonObject", classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.ext.web.api.OperationRequest", classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "io.vertx.core.json.JsonArray", classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "java.net.URLDecoder", classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, StringUtils.class.getCanonicalName(), classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, Map.class.getCanonicalName(), classLanguageName));
							classPartsGenPageAdd(ClassParts.initClassParts(this, List.class.getCanonicalName(), classLanguageName));
					
							for(ClassParts classPartGenPage : classPartsGenPage.values()) {
//								if(classPartGenPage.languageName == null || classPartGenPage.languageName.equals(languageName))
//									indexStoreListSolr(classLanguageName, classDoc, "classImportsGenPage", classPartGenPage.canonicalName);
//								if(classTranslate) {
								ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classPartGenPage, languageName);
								if(classImportClassPartsLanguage.languageName == null || classImportClassPartsLanguage.languageName.equals(languageName))
									indexStoreListSolr(languageName, classDoc, "classImportsGenPage", classImportClassPartsLanguage.canonicalName);
//								}
							}
						}
					}
					classApiMethodsTrouves = classApiMethodsRegex.find();
				}
		
				for(ClassParts classPartGenApi : classPartsGenApi.values()) {
					ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classPartGenApi, languageName);
					if(classImportClassPartsLanguage.languageName == null || classImportClassPartsLanguage.languageName.equals(languageName))
						indexStoreListSolr(languageName, classDoc, "classImportsGenApi", classImportClassPartsLanguage.canonicalName);
				}
			}
		}

		Boolean classContext = indexStoreSolr(classDoc, "classContext", regexFound("^(class)?Context: \\s*(true)$", classComment) || classPage);

		if(classContext) {
			contextColor = regex("^(context)?Color:\\s*(.*)", classComment);
			if(contextColor != null)
				indexStoreSolr(classDoc, "contextColor", contextColor); 

			contextIconGroup = regex("^(context)?IconGroup:\\s*(.*)", classComment);
			if(contextIconGroup != null)
				indexStoreSolr(classDoc, "contextIconGroup", contextIconGroup); 

			contextIconName = regex("^(context)?IconName:\\s*(.*)", classComment);
			if(contextIconName != null)
				indexStoreSolr(classDoc, "contextIconName", contextIconName); 

			for(String languageName : allLanguages) {

				contextVideoId = regexLanguage(languageName, "(context)?VideoId", classComment);
				if(contextVideoId != null)
					indexStoreSolr(languageName, classDoc, "contextVideoId", contextVideoId); 

				contextDescription = regexLanguage(languageName, "(context)?Description", classComment);
				if(contextDescription != null)
					indexStoreSolr(languageName, classDoc, "contextDescription", contextDescription); 

				String contextImageWidthStr = regexLanguage(languageName, "^(context)?ImageWidth", classComment);
				if(NumberUtils.isCreatable(contextImageWidthStr))
					indexStoreSolr(languageName, classDoc, "contextImageWidth", Integer.parseInt(contextImageWidthStr));

				String contextImageHeightStr = regexLanguage(languageName, "^(context)?ImageHeight", classComment);
				if(NumberUtils.isCreatable(contextImageHeightStr))
					indexStoreSolr(languageName, classDoc, "contextImageHeight", Integer.parseInt(contextImageHeightStr));
					
				contextAName = regexLanguage(languageName, "(context)?UnNom", classComment);
				if(contextAName != null) {
					if("frFR".equals(languageName)) {
						indexStoreSolr(languageName, classDoc, "contextAName", contextAName); 
						contextNameSingular = indexStoreSolr(languageName, classDoc, "contextNameSingular", regexLanguage(languageName, "(context)?NameSingular", classComment, StringUtils.substringAfter(contextAName, " ")));
						contextNamePlural = indexStoreSolr(languageName, classDoc, "contextNamePlural", regexLanguage(languageName, "(context)?NamePlural", classComment, contextNameSingular + "s"));
						contextNameVar = indexStoreSolr(languageName, classDoc, "contextNameVar", regexLanguage(languageName, "(context)?NameVar", classComment, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contextNameSingular, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
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
								contextNameAdjectivePlural = indexStoreSolr(languageName, classDoc, "contextNameSingularAdjectivePlural", regexLanguage(languageName, "(context)?NameSingularAdjectivePlural", classComment, contextNamePlural + " " + contextAdjectivePlural));
							}
		
						}
		
						if(contextAName.startsWith(CONTEXT_frFR_AFemale)) {
							contextThis = indexStoreSolr(languageName, classDoc, "contextThis", regexLanguage(languageName, "(context)?This", classComment, CONTEXT_frFR_ThisFemaleConsonant));
							contextA = indexStoreSolr(languageName, classDoc, "contextA", regexLanguage(languageName, "(context)?A", classComment, CONTEXT_frFR_AFemale));
							contextCreated = indexStoreSolr(languageName, classDoc, "contextCreated", regexLanguage(languageName, "(context)?Created", classComment, CONTEXT_frFR_CreatedFemale));
							contextModified = indexStoreSolr(languageName, classDoc, "contextModified", regexLanguage(languageName, "(context)?Modified", classComment, CONTEXT_frFR_ModifiedFemale));
							contextActualName = indexStoreSolr(languageName, classDoc, "contextActualName", regexLanguage(languageName, "(context)?ActualName", classComment, CONTEXT_frFR_CurrentFemaleBefore + contextNameSingular + CONTEXT_frFR_CurrentFemaleAfter));
							contextAllName = indexStoreSolr(languageName, classDoc, "contextAllName", regexLanguage(languageName, "(context)?AllName", classComment, CONTEXT_frFR_ThePlural + contextNamePlural));
							contextSearchAllNameBy = indexStoreSolr(languageName, classDoc, "contextSearchAllNameBy", regexLanguage(languageName, "(context)?SearchAllNameBy", classComment, CONTEXT_frFR_Search + contextNamePlural + CONTEXT_frFR_By));
							contextSearchAllName = indexStoreSolr(languageName, classDoc, "contextSearchAllName", regexLanguage(languageName, "(context)?SearchAllName", classComment, CONTEXT_frFR_Search + contextNamePlural));
							contextNoNameFound = indexStoreSolr(languageName, classDoc, "contextNoNameFound", regexLanguage(languageName, "(context)?NoNameFound", classComment, CONTEXT_frFR_NoneFoundFemaleBefore + contextNameSingular + CONTEXT_frFR_NoneFoundFemaleAfter));
							if(contextAdjective != null) {
								if(CONTEXT_frFR_AdjectiveBefore)
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_frFR_AFemale + " " + contextAdjective + " " + contextNameSingular));
								else
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_frFR_AFemale + " " + contextNameSingular + " " + contextAdjective));
							}
		
							String suffix = StringUtils.substringAfter(contextAName, " ");
							String c = Normalizer.normalize(StringUtils.substring(suffix.toLowerCase(), 0, 1), Normalizer.Form.NFD);
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
								contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?ThisName", classComment, CONTEXT_frFR_ThisFemaleVowel + suffix));
								contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?TheName", classComment, CONTEXT_frFR_TheFemaleVowel + suffix));
								contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?OfName", classComment, CONTEXT_frFR_OfVowel + suffix));
								if(contextAdjective != null) {
									if(CONTEXT_frFR_AdjectiveBefore)
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheFemaleVowel + " " + contextAdjective + " " + contextNameSingular));
									else
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheFemaleVowel + " " + contextNameSingular + " " + contextAdjective));
								}
							}
							else {
								contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?ThisName", classComment, CONTEXT_frFR_ThisFemaleConsonant + suffix));
								contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?TheName", classComment, CONTEXT_frFR_TheFemaleConsonant + suffix));
								contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?OfName", classComment, CONTEXT_frFR_OfConsonant + suffix));
								if(contextAdjective != null) {
									if(CONTEXT_frFR_AdjectiveBefore)
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheFemaleConsonant + " " + contextAdjective + " " + contextNameSingular));
									else
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheFemaleConsonant + " " + contextNameSingular + " " + contextAdjective));
								}
							}
						}
						else if(contextAName.startsWith(CONTEXT_frFR_AMale)) {
							contextThis = indexStoreSolr(languageName, classDoc, "contextThis", regexLanguage(languageName, "(context)?This", classComment, CONTEXT_frFR_ThisMaleConsonant));
							contextA = indexStoreSolr(languageName, classDoc, "contextA", regexLanguage(languageName, "(context)?A", classComment, CONTEXT_frFR_AMale));
							contextCreated = indexStoreSolr(languageName, classDoc, "contextCreated", regexLanguage(languageName, "(context)?Created", classComment, CONTEXT_frFR_CreatedMale));
							contextModified = indexStoreSolr(languageName, classDoc, "contextModified", regexLanguage(languageName, "(context)?Modified", classComment, CONTEXT_frFR_ModifiedMale));
							contextActualName = indexStoreSolr(languageName, classDoc, "contextActualName", regexLanguage(languageName, "(context)?ActualName", classComment, CONTEXT_frFR_CurrentMaleBefore + contextNameSingular + CONTEXT_frFR_CurrentMaleAfter));
							contextAllName = indexStoreSolr(languageName, classDoc, "contextAllName", regexLanguage(languageName, "(context)?AllName", classComment, CONTEXT_frFR_AllMalePlural + contextNamePlural));
							contextSearchAllNameBy = indexStoreSolr(languageName, classDoc, "contextSearchAllNameBy", regexLanguage(languageName, "(context)?SearchAllNameBy", classComment, CONTEXT_frFR_Search + contextNamePlural + CONTEXT_frFR_By));
							contextSearchAllName = indexStoreSolr(languageName, classDoc, "contextSearchAllName", regexLanguage(languageName, "(context)?SearchAllName", classComment, CONTEXT_frFR_Search + contextNamePlural));
							contextNoNameFound = indexStoreSolr(languageName, classDoc, "contextNoNameFound", regexLanguage(languageName, "(context)?NoNameFound", classComment, CONTEXT_frFR_NoneFoundMaleBefore + contextNameSingular + CONTEXT_frFR_NoneFoundMaleAfter));
							if(contextAdjective != null) {
								if(CONTEXT_frFR_AdjectiveBefore)
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_frFR_AMale + " " + contextAdjective + " " + contextNameSingular));
								else
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_frFR_AMale + " " + contextNameSingular + " " + contextAdjective));
							}
		
							String suffix = StringUtils.substringAfter(contextAName, " ");
							String c = Normalizer.normalize(StringUtils.substring(suffix.toLowerCase(), 0, 1), Normalizer.Form.NFD);
							if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
								contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?ThisName", classComment, CONTEXT_frFR_ThisMaleVowel + suffix));
								contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?TheName", classComment, CONTEXT_frFR_TheMaleVowel + suffix));
								contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?OfName", classComment, CONTEXT_frFR_OfVowel + suffix));
								if(contextAdjective != null) {
									if(CONTEXT_frFR_AdjectiveBefore)
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheMaleVowel + " " + contextAdjective + " " + contextNameSingular));
									else
										contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_frFR_TheMaleVowel + " " + contextNameSingular + " " + contextAdjective));
								}
							}
							else {
								contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?ThisName", classComment, CONTEXT_frFR_ThisMaleConsonant + suffix));
								contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?TheName", classComment, CONTEXT_frFR_TheMaleConsonant + suffix));
								contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?OfName", classComment, CONTEXT_frFR_OfConsonant + suffix));
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
						contextNameSingular = indexStoreSolr(languageName, classDoc, "contextNameSingular", regexLanguage(languageName, "(context)?NameSingular", classComment, StringUtils.substringAfter(contextAName, " ")));
						contextNamePlural = indexStoreSolr(languageName, classDoc, "contextNamePlural", regexLanguage(languageName, "(context)?NamePlural", classComment, contextNameSingular + "s"));
						contextNameVar = indexStoreSolr(languageName, classDoc, "contextNameVar", regexLanguage(languageName, "(context)?NameVar", classComment, StringUtils.uncapitalize(Normalizer.normalize(StringUtils.replace(WordUtils.capitalize(StringUtils.join(StringUtils.split(contextNameSingular, "-"), " ")), " ", ""), Normalizer.Form.NFD))));
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
								contextNameAdjectivePlural = indexStoreSolr(languageName, classDoc, "contextNameSingularAdjectivePlural", regexLanguage(languageName, "(context)?NameSingularAdjectivePlural", classComment, contextNamePlural + " " + contextAdjectivePlural));
							}
		
						}
		
						contextThis = indexStoreSolr(languageName, classDoc, "contextThis", regexLanguage(languageName, "(context)?This", classComment, CONTEXT_enUS_ThisConsonant));
						contextA = indexStoreSolr(languageName, classDoc, "contextA", regexLanguage(languageName, "(context)?A", classComment, CONTEXT_enUS_A));
						contextCreated = indexStoreSolr(languageName, classDoc, "contextCreated", regexLanguage(languageName, "(context)?Created", classComment, CONTEXT_enUS_Created));
						contextModified = indexStoreSolr(languageName, classDoc, "contextModified", regexLanguage(languageName, "(context)?Modified", classComment, CONTEXT_enUS_Modified));
						contextActualName = indexStoreSolr(languageName, classDoc, "contextActualName", regexLanguage(languageName, "(context)?ActualName", classComment, CONTEXT_enUS_CurrentBefore + contextNameSingular + CONTEXT_enUS_CurrentAfter));
						contextAllName = indexStoreSolr(languageName, classDoc, "contextAllName", regexLanguage(languageName, "(context)?AllName", classComment, CONTEXT_enUS_ThePlural + contextNamePlural));
						contextSearchAllNameBy = indexStoreSolr(languageName, classDoc, "contextSearchAllNameBy", regexLanguage(languageName, "(context)?SearchAllNameBy", classComment, CONTEXT_enUS_Search + contextNamePlural + CONTEXT_enUS_By));
						contextSearchAllName = indexStoreSolr(languageName, classDoc, "contextSearchAllNamer", regexLanguage(languageName, "(context)?SearchAllName", classComment, CONTEXT_enUS_Search + contextNamePlural));
						contextNoNameFound = indexStoreSolr(languageName, classDoc, "contextNoNameFound", regexLanguage(languageName, "(context)?NoNameFound", classComment, CONTEXT_enUS_NoneFoundBefore + contextNameSingular + CONTEXT_enUS_NoneFoundAfter));
						if(contextAdjective != null) {
							if(CONTEXT_enUS_AdjectiveBefore)
								contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_enUS_A + " " + contextAdjective + " " + contextNameSingular));
							else
								contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextANameAdjective", regexLanguage(languageName, "(context)?ANameAdjective", classComment, CONTEXT_enUS_A + " " + contextNameSingular + " " + contextAdjective));
						}
	
						String suffix = StringUtils.substringAfter(contextAName, " ");
						String c = Normalizer.normalize(StringUtils.substring(suffix.toLowerCase(), 0, 1), Normalizer.Form.NFD);
						if(StringUtils.containsAny(c, 'a', 'e', 'i', 'o', 'u', 'h', 'y')) {
							contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?ThisName", classComment, CONTEXT_enUS_ThisVowel + suffix));
							contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?TheName", classComment, CONTEXT_enUS_TheVowel + suffix));
							contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?OfName", classComment, CONTEXT_enUS_OfVowel + suffix));
							if(contextAdjective != null) {
								if(CONTEXT_enUS_AdjectiveBefore)
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_enUS_TheVowel + " " + contextAdjective + " " + contextNameSingular));
								else
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_enUS_TheVowel + " " + contextNameSingular + " " + contextAdjective));
							}
						}
						else {
							contextThisName = indexStoreSolr(languageName, classDoc, "contextThisName", regexLanguage(languageName, "(context)?ThisName", classComment, CONTEXT_enUS_ThisConsonant + suffix));
							contextTheName = indexStoreSolr(languageName, classDoc, "contextTheName", regexLanguage(languageName, "(context)?TheName", classComment, CONTEXT_enUS_TheConsonant + suffix));
							contextOfName = indexStoreSolr(languageName, classDoc, "contextOfName", regexLanguage(languageName, "(context)?OfName", classComment, CONTEXT_enUS_OfConsonant + suffix));
							if(contextAdjective != null) {
								if(CONTEXT_enUS_AdjectiveBefore)
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_enUS_TheConsonant + " " + contextAdjective + " " + contextNameSingular));
								else
									contextANameAdjective = indexStoreSolr(languageName, classDoc, "contextTheNameAdjective", regexLanguage(languageName, "(context)?TheNameAdjective", classComment, CONTEXT_enUS_TheConsonant + " " + contextNameSingular + " " + contextAdjective));
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
			classPartsGenAdd(classPartsSiteContext);
			classPartsGenAdd(classPartsSolrDocument);
			classPartsGenAdd(classPartsList);
			classPartsGenAdd(classPartsArrayList);
			classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.commons.lang3.exception.ExceptionUtils", classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.SolrQuery", classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.response.QueryResponse", classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, "org.apache.solr.client.solrj.util.ClientUtils", classLanguageName));
		}

		if(classImage) {
			classPartsGenAdd(ClassParts.initClassParts(this, DefaultExecutor.class.getCanonicalName(), classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, CommandLine.class.getCanonicalName(), classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, File.class.getCanonicalName(), classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, BufferedImage.class.getCanonicalName(), classLanguageName));
			classPartsGenAdd(ClassParts.initClassParts(this, ImageIO.class.getCanonicalName(), classLanguageName));
		}

		for(ClassParts classPartGen : classPartsGen.values()) {
			indexStoreListSolr(classLanguageName, classDoc, "classImportsGen", classPartGen.canonicalName);
			for(String languageName : classOtherLanguages) {  
				ClassParts classImportClassPartsLanguage = ClassParts.initClassParts(this, classPartGen, languageName);
				indexStoreListSolr(languageName, classDoc, "classImportsGen", classImportClassPartsLanguage.canonicalName);
			}
		}

		if(classSuperDoc != null) {
			for(String languageName : allLanguages) {
				List<String> classSuperEntityVars = (List<String>)classSuperDoc.get("classEntityVars_" + languageName + "_stored_strings");
				if(classSuperEntityVars != null) {
					for(String classSuperEntityVar : classSuperEntityVars)
						indexStoreListSolr(languageName, classDoc, "classEntityVars", classSuperEntityVar);
				}
			}
		}

		if(classSuperDoc != null) {
			for(String languageName : allLanguages) {
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
