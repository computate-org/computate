package org.computate.frFR.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NomCanonique.enUS: org.computate.enUS.java.SiteConfig enUS: Loads the
 * properties in the application config file into specific fields. frFR: Charge
 * les propriétés dans le fichier de config de l'application dans des champs
 * spécifiques.
 */
public class ConfigSite {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");

	public ConfigSite() {
	}

	public String str_appliNom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "appliNom";
		else
			return "appName";
	}

	public String str_appliChemin(String langueNom) {
		if ("frFR".equals(langueNom))
			return "appliChemin";
		else
			return "appPath";
	}

	public String str_classeCheminAbsolu(String langueNom) {
		if ("frFR".equals(langueNom))
			return "classeCheminAbsolu";
		else
			return "classAbsolutePath";
	}

	public String str_nomDomaine(String langueNom) {
		if ("frFR".equals(langueNom))
			return "str_nomDomaine";
		else
			return "domainName";
	}

	public String str_autresLangues(String langueNom) {
		if ("frFR".equals(langueNom))
			return "autresLangues";
		else
			return "otherLanguages";
	}

	public String str_SITE_URL_BASE(String langueNom) {
		if ("frFR".equals(langueNom))
			return "SITE_URL_BASE";
		else
			return "SITE_BASE_URL";
	}

	public String str_siteUrlBase(String langueNom) {
		if ("frFR".equals(langueNom))
			return "siteUrlBase";
		else
			return "siteBaseUrl";
	}

	public String str_nomEnsembleDomaine(String langueNom) {
		if ("frFR".equals(langueNom))
			return "nomEnsembleDomaine";
		else
			return "domainPackageName";
	}

	public String str_fichier(String langueNom) {
		if ("frFR".equals(langueNom))
			return "fichier";
		else
			return "file";
	}

	public String str_nomFichierConfig(String langueNom) {
		if ("frFR".equals(langueNom))
			return "nomFichierConfig";
		else
			return "configFileName";
	}

	public String str_URL_SOLR(String langueNom) {
		if ("frFR".equals(langueNom))
			return "URL_SOLR";
		else
			return "SOLR_URL";
	}

	public String str_solrUrl(String langueNom) {
		if ("frFR".equals(langueNom))
			return "solrUrl";
		else
			return "solrUrl";
	}

	public String str_siteCrypte(String langueNom) {
		if ("frFR".equals(langueNom))
			return "siteCrypte";
		else
			return "siteEncrypted";
	}

	public String str_siteEcrireMethodes(String langueNom) {
		if ("frFR".equals(langueNom))
			return "siteEcrireMethodes";
		else
			return "siteWriteMethods";
	}

	public String str_ecrireApi(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ecrireApi";
		else
			return "writeApi";
	}

	public String str_ecrireCommentaire(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ecrireCommentaire";
		else
			return "writeComments";
	}

	public String str_roleLires(String langueNom) {
		if ("frFR".equals(langueNom))
			return "roleLires";
		else
			return "roleReads";
	}

	public String str_activer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "activer";
		else
			return "enable";
	}

	public String str_Supprime(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Supprime";
		else
			return "Deleted";
	}

	public String str_Archive(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Archive";
		else
			return "Archived";
	}

	/**
	 * Var.enUS: str_alreadyInitialized
	 */
	public String str_dejaInitialise(String langueNom) {
		if ("frFR".equals(langueNom))
			return "dejaInitialise";
		else
			return "alreadyInitialized";
	}

	public String str_promesse(String langueNom) {
		if ("frFR".equals(langueNom))
			return "promesse";
		else
			return "promise";
	}

	public String str_promesseLoin(String langueNom) {
		if ("frFR".equals(langueNom))
			return "promesseLoin";
		else
			return "promiseDeep";
	}

	/**
	 * Var.enUS: str_initDeep
	 */
	public String str_initLoin(String langueNom) {
		if ("frFR".equals(langueNom))
			return "initLoin";
		else
			return "initDeep";
	}

	/**
	 * Var.enUS: str_in
	 */
	public String str_dans(String langueNom) {
		if ("frFR".equals(langueNom))
			return "dans";
		else
			return "in";
	}

	public String str_MailVerticle(String langueNom) {
		if ("frFR".equals(langueNom))
			return "MailVerticle";
		else
			return "MailVerticle";
	}

	/**
	 * Var.enUS: str_Wrap
	 */
	public String str_Couverture(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Couverture";
		else
			return "Wrap";
	}

	/**
	 * Var.enUS: str_class
	 */
	public String str_classe(String langueNom) {
		if ("frFR".equals(langueNom))
			return "classe";
		else
			return "class";
	}

	/**
	 * Var.enUS: str_manquante
	 */
	public String str_manquante(String langueNom) {
		if ("frFR".equals(langueNom))
			return "manquante";
		else
			return "missing";
	}

	/**
	 * Var.enUS: str_ForClass
	 */
	public String str_PourClasse(String langueNom) {
		if ("frFR".equals(langueNom))
			return "PourClasse";
		else
			return "ForClass";
	}

	/**
	 * Var.enUS: str_obtain
	 */
	public String str_obtenir(String langueNom) {
		if ("frFR".equals(langueNom))
			return "obtenir";
		else
			return "obtain";
	}

	/**
	 * Var.enUS: str_attribute
	 */
	public String str_attribuer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "attribuer";
		else
			return "attribute";
	}

	/**
	 * Var.enUS: str_put
	 */
	public String str_put(String langueNom) {
		if ("frFR".equals(langueNom))
			return "put";
		else
			return "put";
	}

	public String str_Indexer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Indexer";
		else
			return "Index";
	}

	/**
	 * Var.enUS: str_index
	 */
	public String str_indexer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "indexer";
		else
			return "index";
	}

	/**
	 * Var.enUS: str_Indexed
	 */
	public String str_Indexe(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Indexe";
		else
			return "Indexed";
	}

	/**
	 * Var.enUS: str_store
	 */
	public String str_stocker(String langueNom) {
		if ("frFR".equals(langueNom))
			return "stocker";
		else
			return "store";
	}

	/**
	 * Var.enUS: str_populate
	 */
	public String str_peupler(String langueNom) {
		if ("frFR".equals(langueNom))
			return "peupler";
		else
			return "populate";
	}

	/**
	 * Var.enUS: str_deconnexion
	 */
	public String str_deconnexion(String langueNom) {
		if ("frFR".equals(langueNom))
			return "deconnexion";
		else
			return "logout";
	}

	/**
	 * Var.enUS: str_de
	 */
	public String str_de(String langueNom) {
		if ("frFR".equals(langueNom))
			return "de";
		else
			return "of";
	}

	/**
	 * Var.enUS: str_define
	 */
	public String str_definir(String langueNom) {
		if ("frFR".equals(langueNom))
			return "definir";
		else
			return "define";
	}

	public String str_Definir(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Definir";
		else
			return "Define";
	}

	/**
	 * Var.enUS: str_siteRequest
	 */
	public String str_requeteSite(String langueNom) {
		if ("frFR".equals(langueNom))
			return "requeteSite";
		else
			return "siteRequest";
	}

	public String str_roles_requis(String langueNom) {
		if ("frFR".equals(langueNom))
			return "rôles requis : ";
		else
			return "roles required: ";
	}

	/**
	 * Var.enUS: str_SiteRequest
	 */
	public String str_RequeteSite(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RequeteSite";
		else
			return "SiteRequest";
	}

	public String str_Avant(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Avant";
		else
			return "Before";
	}

	public String str_avant(String langueNom) {
		if ("frFR".equals(langueNom))
			return "avant";
		else
			return "before";
	}

	public String str_Apres(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Apres";
		else
			return "After";
	}

	/**
	 * Var.enUS: str_after
	 */
	public String str_apres(String langueNom) {
		if ("frFR".equals(langueNom))
			return "apres";
		else
			return "after";
	}

	/**
	 * Var.enUS: str_Rights
	 */
	public String str_Droits(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Droits";
		else
			return "Rights";
	}

	public String str_Config(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Config";
		else
			return "Config";
	}

	public String str_ConfigSite(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ConfigSite";
		else
			return "SiteConfig";
	}

	public String str_configSite(String langueNom) {
		if ("frFR".equals(langueNom))
			return "configSite";
		else
			return "siteConfig";
	}

	/**
	 * Var.enUS: str_search
	 */
	public String str_rechercher(String langueNom) {
		if ("frFR".equals(langueNom))
			return "rechercher";
		else
			return "search";
	}

	/**
	 * Var.enUS: str_aSearch
	 */
	public String str_recherche(String langueNom) {
		if ("frFR".equals(langueNom))
			return "recherche";
		else
			return "aSearch";
	}

	/**
	 * Var.enUS: str_SearchPage
	 */
	public String str_PageRecherche(String langueNom) {
		if ("frFR".equals(langueNom))
			return "PageRecherche";
		else
			return "SearchPage";
	}

	public String str_PUTFusion(String langueNom) {
		if ("frFR".equals(langueNom))
			return "PUTFusion";
		else
			return "PUTMerge";
	}

	public String str_Fusion(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Fusion";
		else
			return "Merge";
	}

	public String str_fusion(String langueNom) {
		if ("frFR".equals(langueNom))
			return "fusion";
		else
			return "merge";
	}

	public String str_PUTCopie(String langueNom) {
		if ("frFR".equals(langueNom))
			return "PUTCopie";
		else
			return "PUTCopy";
	}

	public String str_Copie(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Copie";
		else
			return "Copy";
	}

	public String str_copie(String langueNom) {
		if ("frFR".equals(langueNom))
			return "copie";
		else
			return "copy";
	}

	public String str_Recherche(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Recherche";
		else
			return "Search";
	}

	/**
	 * Var.enUS: str_solrQuery
	 */
	public String str_rechercheSolr(String langueNom) {
		if ("frFR".equals(langueNom))
			return "rechercheSolr";
		else
			return "solrQuery";
	}

	/**
	 * Var.enUS: str_queryResponse
	 */
	public String str_reponseRecherche(String langueNom) {
		if ("frFR".equals(langueNom))
			return "reponseRecherche";
		else
			return "queryResponse";
	}

	/**
	 * Var.enUS: str_SolrDocument
	 */
	public String str_DocumentSolr(String langueNom) {
		if ("frFR".equals(langueNom))
			return "DocumentSolr";
		else
			return "SolrDocument";
	}

	/**
	 * Var.enUS: str_requestHeaders
	 */
	public String str_requeteEnTetes(String langueNom) {
		if ("frFR".equals(langueNom))
			return "requeteEnTetes";
		else
			return "requestHeaders";
	}

	/**
	 * Var.enUS: str_RequestHeaders
	 */
	public String str_RequeteEnTetes(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RequeteEnTetes";
		else
			return "RequestHeaders";
	}

	public String str_SITE_ZONE(String langueNom) {
		if ("frFR".equals(langueNom))
			return "SITE_ZONE";
		else
			return "SITE_ZONE";
	}

	public String str_siteZone(String langueNom) {
		if ("frFR".equals(langueNom))
			return "siteZone";
		else
			return "siteZone";
	}

	public String str_siteContexte(String langueNom) {
		if ("frFR".equals(langueNom))
			return "siteContexte";
		else
			return "siteContext";
	}

	/**
	 * Var.enUS: str_SiteContext
	 */
	public String str_SiteContexte(String langueNom) {
		if ("frFR".equals(langueNom))
			return "SiteContexte";
		else
			return "SiteContext";
	}

	public String str_MailDe(String langueNom) {
		if ("frFR".equals(langueNom))
			return "MailDe";
		else
			return "EmailFrom";
	}

	public String str_Mail(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Mail";
		else
			return "Email";
	}

	public String str_MailAdmin(String langueNom) {
		if ("frFR".equals(langueNom))
			return "MailAdmin";
		else
			return "EmailAdmin";
	}

	public String str_SiteUrlBase(String langueNom) {
		if ("frFR".equals(langueNom))
			return "SiteUrlBase";
		else
			return "SiteBaseUrl";
	}

	/**
	 * Var.enUS: str_ConfigPath
	 */
	public String str_ConfigChemin(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ConfigChemin";
		else
			return "ConfigPath";
	}

	public String str_routeur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "routeur";
		else
			return "router";
	}

	public String str_clientWeb(String langueNom) {
		if ("frFR".equals(langueNom))
			return "clientWeb";
		else
			return "webClient";
	}

	/**
	 * Var.enUS: str_solrClient
	 */
	public String str_clientSolr(String langueNom) {
		if ("frFR".equals(langueNom))
			return "clientSolr";
		else
			return "solrClient";
	}

	/**
	 * Var.enUS: str_SolrClient
	 */
	public String str_ClientSolr(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ClientSolr";
		else
			return "SolrClient";
	}

	/**
	 * Var.enUS: str_list
	 */
	public String str_liste(String langueNom) {
		if ("frFR".equals(langueNom))
			return "liste";
		else
			return "list";
	}

	/**
	 * Var.enUS: str_workerExecutor
	 */
	public String str_executeurTravailleur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "executeurTravailleur";
		else
			return "workerExecutor";
	}

	/**
	 * Var.enUS: str_WorkerExecutor
	 */
	public String str_ExecuteurTravailleur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ExecuteurTravailleur";
		else
			return "WorkerExecutor";
	}

	/**
	 * Var.enUS: str_Title
	 */
	public String str_Titre(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Titre";
		else
			return "Title";
	}

	/**
	 * Var.enUS: str_Translate
	 */
	public String str_Traduire(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Traduire";
		else
			return "Translate";
	}

	public String str_MiseEnPage(String langueNom) {
		if ("frFR".equals(langueNom))
			return "MiseEnPage";
		else
			return "PageLayout";
	}

	public String str_PagePart(String langueNom) {
		if ("frFR".equals(langueNom))
			return "PagePart";
		else
			return "PagePart";
	}

	public String str_BaseApiServiceImpl(String langueNom) {
		if ("frFR".equals(langueNom))
			return "BaseApiServiceImpl";
		else
			return "BaseApiServiceImpl";
	}

	public String str_ResultatRecherche(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ResultatRecherche";
		else
			return "SearchResult";
	}

	public String str_ToutEcrivain(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ToutEcrivain";
		else
			return "AllWriter";
	}

	/**
	 * Var.enUS: str_SearchList
	 */
	public String str_ListeRecherche(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ListeRecherche";
		else
			return "SearchList";
	}

	/**
	 * Var.enUS: str_searchList
	 */
	public String str_listeRecherche(String langueNom) {
		if ("frFR".equals(langueNom))
			return "listeRecherche";
		else
			return "searchList";
	}

	/**
	 * Var.enUS: str_Width
	 */
	public String str_Largeur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Largeur";
		else
			return "Width";
	}

	/**
	 * Var.enUS: str_Height
	 */
	public String str_Hauteur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Hauteur";
		else
			return "Height";
	}

	/**
	 * Var.enUS: str_Group
	 */
	public String str_Groupe(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Groupe";
		else
			return "Group";
	}

	/**
	 * Var.enUS: str_Name
	 */
	public String str_Nom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Nom";
		else
			return "Name";
	}

	/**
	 * Var.enUS: str_context
	 */
	public String str_contexte(String langueNom) {
		if ("frFR".equals(langueNom))
			return "contexte";
		else
			return "context";
	}

	/**
	 * Var.enUS: str_staticBaseUrl
	 */
	public String str_statiqueUrlBase(String langueNom) {
		if ("frFR".equals(langueNom))
			return "statiqueUrlBase";
		else
			return "staticBaseUrl";
	}

	/**
	 * Var.enUS: str_Icon
	 */
	public String str_Icone(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Icone";
		else
			return "Icon";
	}

	/**
	 * Var.enUS: str_filter
	 */
	public String str_filtre(String langueNom) {
		if ("frFR".equals(langueNom))
			return "filtre";
		else
			return "filter";
	}

	public String str_Colonne(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Colonne";
		else
			return "Column";
	}

	/**
	 * Var.enUS: str_filters
	 */
	public String str_filtres(String langueNom) {
		if ("frFR".equals(langueNom))
			return "filtres";
		else
			return "filters";
	}

	/**
	 * Var.enUS: str_Filters
	 */
	public String str_Filtres(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Filtres";
		else
			return "Filters";
	}

	/**
	 * Var.enUS: str_formFilters
	 */
	public String str_formulaireFiltres(String langueNom) {
		if ("frFR".equals(langueNom))
			return "formulaireFiltres";
		else
			return "formFilters";
	}

	/**
	 * Var.enUS: str_FormFilters
	 */
	public String str_FormulaireFiltres(String langueNom) {
		if ("frFR".equals(langueNom))
			return "FormulaireFiltres";
		else
			return "FormFilters";
	}

	/**
	 * Var.enUS: str_FormValues
	 */
	public String str_FormulaireValeurs(String langueNom) {
		if ("frFR".equals(langueNom))
			return "FormulaireValeurs";
		else
			return "FormValues";
	}

	/**
	 * Var.enUS: str_formValue
	 */
	public String str_formulaireValeur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "formulaireValeur";
		else
			return "formValue";
	}

	/**
	 * Var.enUS: str_formValues
	 */
	public String str_formulaireValeurs(String langueNom) {
		if ("frFR".equals(langueNom))
			return "formulaireValeurs";
		else
			return "formValues";
	}

	/**
	 * Var.enUS: str_Values
	 */
	public String str_Valeurs(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Valeurs";
		else
			return "Values";
	}

	/**
	 * Var.enUS: str_values
	 */
	public String str_valeurs(String langueNom) {
		if ("frFR".equals(langueNom))
			return "valeurs";
		else
			return "values";
	}

	public String str_deuxPoints(String langueNom) {
		if ("frFR".equals(langueNom))
			return " : ";
		else
			return ": ";
	}

	public String str_relations(String langueNom) {
		if ("frFR".equals(langueNom))
			return "relations";
		else
			return "relations";
	}

	public String str_Recharger(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Recharger";
		else
			return "Refresh";
	}

	public String str_recharger(String langueNom) {
		if ("frFR".equals(langueNom))
			return "recharger";
		else
			return "refresh";
	}

	public String str_relier(String langueNom) {
		if ("frFR".equals(langueNom))
			return "relier";
		else
			return "relate";
	}

	public String str_a(String langueNom) {
		if ("frFR".equals(langueNom))
			return "a";
		else
			return "to";
	}

	public String str_Télécharger_image(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Télécharger image";
		else
			return "Upload image";
	}

	/**
	 * Var.enUS: str_value
	 */
	public String str_valeur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "valeur";
		else
			return "value";
	}

	/**
	 * Var.enUS: str_glow
	 */
	public String str_lueur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "lueur";
		else
			return "glow";
	}

	/**
	 * Var.enUS: str_Error
	 */
	public String str_Erreur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Erreur";
		else
			return "Error";
	}

	/**
	 * Var.enUS: str_Success
	 */
	public String str_Succes(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Succes";
		else
			return "Success";
	}

	/**
	 * Var.enUS: str_serviceRequest
	 */
	public String str_requeteService(String langueNom) {
		if ("frFR".equals(langueNom))
			return "requeteService";
		else
			return "serviceRequest";
	}

	/**
	 * Var.enUS: str_ServiceRequest
	 */
	public String str_RequeteService(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RequeteService";
		else
			return "ServiceRequest";
	}

	public String str_vider(String langueNom) {
		if ("frFR".equals(langueNom))
			return "vider";
		else
			return "clear";
	}

	/**
	 * Var.enUS: str_Request
	 */
	public String str_Requete(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Requete";
		else
			return "Request";
	}

	/**
	 * Var.enUS: str_Response
	 */
	public String str_Reponse(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Reponse";
		else
			return "Response";
	}

	/**
	 * Var.enUS: str_ObjectValues
	 */
	public String str_ValeursObjet(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ValeursObjet";
		else
			return "ObjectValues";
	}

	public String str_objetTexte(String langueNom) {
		if ("frFR".equals(langueNom))
			return "objetTexte";
		else
			return "objectText";
	}

	public String str_objetSuggere(String langueNom) {
		if ("frFR".equals(langueNom))
			return "objetSuggere";
		else
			return "objectSuggest";
	}

	/**
	 * Var.enUS: str_Object
	 */
	public String str_Objet(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Objet";
		else
			return "Object";
	}

	/**
	 * Var.enUS: str_Objects
	 */
	public String str_Objets(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Objets";
		else
			return "Objects";
	}

	/**
	 * Var.enUS: str_Method
	 */
	public String str_Methode(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Methode";
		else
			return "Method";
	}

	/**
	 * Var.enUS: str_Modal
	 */
	public String str_Modale(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Modale";
		else
			return "Modal";
	}

	/**
	 * Var.enUS: str_Short
	 */
	public String str_Court(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Court";
		else
			return "Short";
	}

	/**
	 * Var.enUS: str_Write
	 */
	public String str_Ecrire(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Ecrire";
		else
			return "Write";
	}

	/**
	 * Var.enUS: str_contextIconCssClasses
	 */
	public String str_contexteIconeClassesCss(String langueNom) {
		if ("frFR".equals(langueNom))
			return "contexteIconeClassesCss";
		else
			return "contextIconCssClasses";
	}

	/**
	 * Var.enUS: str_register
	 */
	public String str_enregistrer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "enregistrer";
		else
			return "register";
	}

	/**
	 * Var.enUS: str_Create_
	 */
	public String str_Creer_(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Créer ";
		else
			return "Create ";
	}

	/**
	 * Var.enUS: str_Search_the_
	 */
	public String str_Rechercher_(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Rechercher ";
		else
			return "Search ";
	}

	/**
	 * Var.enUS: str_Replace_
	 */
	public String str_Remplacer_(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Remplacer ";
		else
			return "Replace ";
	}

	public String str_Dupliquer_(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Dupliquer ";
		else
			return "Duplicate ";
	}

	public String str_Fusionner_(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Fusionner ";
		else
			return "Merge ";
	}

	public String str_Importer_(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Importer ";
		else
			return "Import ";
	}

	/**
	 * Var.enUS: str_Modify_the_
	 */
	public String str_Modifier_des_(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Modifier ";
		else
			return "Modify ";
	}

	/**
	 * Var.enUS: str_Delete_the_
	 */
	public String str_Supprimer_(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Supprimer ";
		else
			return "Delete ";
	}

	/**
	 * Var.enUS: str_is_not_an_indexed_entity
	 */
	public String str_nest_pas_une_entite_indexe(String langueNom) {
		if ("frFR".equals(langueNom))
			return "n'est pas une entité indexé";
		else
			return "is not an indexed entity";
	}

	/**
	 * Var.enUS: str_create
	 */
	public String str_creer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "creer";
		else
			return "create";
	}

	/**
	 * Var.enUS: str_address
	 */
	public String str_addresse(String langueNom) {
		if ("frFR".equals(langueNom))
			return "addresse";
		else
			return "address";
	}

	public String str_GestionnaireEvenements(String langueNom) {
		if ("frFR".equals(langueNom))
			return "GestionnaireEvenements";
		else
			return "EventHandler";
	}

	public String str_gestionnaireEvenements(String langueNom) {
		if ("frFR".equals(langueNom))
			return "gestionnaireEvenements";
		else
			return "eventHandler";
	}

	/**
	 * Var.enUS: str_entity
	 */
	public String str_entite(String langueNom) {
		if ("frFR".equals(langueNom))
			return "entite";
		else
			return "entity";
	}

	/**
	 * Var.enUS: str_Number
	 */
	public String str_Numero(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Numero";
		else
			return "Number";
	}

	/**
	 * Var.enUS: str_Value
	 */
	public String str_Valeur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Valeur";
		else
			return "Value";
	}

	/**
	 * Var.enUS: str_request
	 */
	public String str_requete(String langueNom) {
		if ("frFR".equals(langueNom))
			return "requete";
		else
			return "request";
	}

	public String str_Cluster(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Cluster";
		else
			return "Cluster";
	}

	/**
	 * Var.enUS: str_ApiRequest
	 */
	public String str_RequeteApi(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RequeteApi";
		else
			return "ApiRequest";
	}

	/**
	 * Var.enUS: str_apiRequest
	 */
	public String str_requeteApi(String langueNom) {
		if ("frFR".equals(langueNom))
			return "requeteApi";
		else
			return "apiRequest";
	}

	public String str_suivant(String langueNom) {
		if ("frFR".equals(langueNom))
			return "suivant";
		else
			return "next";
	}

	/**
	 * Var.enUS: str_methodName
	 */
	public String str_methodeNom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "methodeNom";
		else
			return "methodName";
	}

	/**
	 * Var.enUS: str_generate
	 */
	public String str_generer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "generer";
		else
			return "generate";
	}

	public String str_Connexion(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Connexion";
		else
			return "Connection";
	}

	/**
	 * Var.enUS: str_SqlConnection
	 */
	public String str_ConnexionSql(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ConnexionSql";
		else
			return "SqlConnection";
	}

	/**
	 * Var.enUS: str_response
	 */
	public String str_reponse(String langueNom) {
		if ("frFR".equals(langueNom))
			return "reponse";
		else
			return "response";
	}

	public String str_Fermer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Fermer";
		else
			return "Close";
	}

	/**
	 * Var.enUS: str_sqlConnection
	 */
	public String str_connexionSql(String langueNom) {
		if ("frFR".equals(langueNom))
			return "connexionSql";
		else
			return "sqlConnection";
	}

	/**
	 * Var.enUS: str_error
	 */
	public String str_erreur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "erreur";
		else
			return "error";
	}

	public String str_utilisateur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "utilisateur";
		else
			return "user";
	}

	public String str_utilisateurId(String langueNom) {
		if ("frFR".equals(langueNom))
			return "utilisateurId";
		else
			return "userId";
	}

	public String str_UtilisateurCles(String langueNom) {
		if ("frFR".equals(langueNom))
			return "UtilisateurCles";
		else
			return "UserKeys";
	}

	public String str_utilisateurCles(String langueNom) {
		if ("frFR".equals(langueNom))
			return "utilisateurCles";
		else
			return "userKeys";
	}

	public String str_utilisateurCle(String langueNom) {
		if ("frFR".equals(langueNom))
			return "utilisateurCle";
		else
			return "userKey";
	}

	public String str_sessionId(String langueNom) {
		if ("frFR".equals(langueNom))
			return "sessionId";
		else
			return "sessionId";
	}

	public String str_UtilisateurId(String langueNom) {
		if ("frFR".equals(langueNom))
			return "UtilisateurId";
		else
			return "UserId";
	}

	public String str_UtilisateurCle(String langueNom) {
		if ("frFR".equals(langueNom))
			return "UtilisateurCle";
		else
			return "UserKey";
	}

	public String str_SessionId(String langueNom) {
		if ("frFR".equals(langueNom))
			return "SessionId";
		else
			return "SessionId";
	}

	/**
	 * Var.enUS: str_For
	 */
	public String str_Pour(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Pour";
		else
			return "For";
	}

	/**
	 * Var.enUS: str_delete
	 */
	public String str_supprimer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "supprimer";
		else
			return "delete";
	}

	/**
	 * Var.enUS: str_User
	 */
	public String str_utilisateurNomComplet(String langueNom) {
		if ("frFR".equals(langueNom))
			return "utilisateurNomComplet";
		else
			return "userFullName";
	}

	public String str_requeteUri(String langueNom) {
		if ("frFR".equals(langueNom))
			return "requeteUri";
		else
			return "requestUri";
	}

	public String str_requeteMethode(String langueNom) {
		if ("frFR".equals(langueNom))
			return "requeteMethode";
		else
			return "requestMethod";
	}

	public String str_RequeteUri(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RequeteUri";
		else
			return "RequestUri";
	}

	public String str_RequeteMethode(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RequeteMethode";
		else
			return "RequestMethod";
	}

	/**
	 * Var.enUS: str_User
	 */
	public String str_utilisateurNom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "utilisateurNom";
		else
			return "userName";
	}

	/**
	 * Var.enUS: str_User
	 */
	public String str_UtilisateurNomComplet(String langueNom) {
		if ("frFR".equals(langueNom))
			return "UtilisateurNomComplet";
		else
			return "UserFullName";
	}

	/**
	 * Var.enUS: str_User
	 */
	public String str_UtilisateurNom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "UtilisateurNom";
		else
			return "UserName";
	}

	/**
	 * Var.enUS: str_User
	 */
	public String str_Utilisateur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Utilisateur";
		else
			return "User";
	}

	public String str_Cle(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Cle";
		else
			return "Key";
	}

	/**
	 * Var.enUS: str_UserResourceRoles
	 */
	public String str_UtilisateurRolesRessource(String langueNom) {
		if ("frFR".equals(langueNom))
			return "UtilisateurRolesRessource";
		else
			return "UserResourceRoles";
	}

	/**
	 * Var.enUS: str_UserRealmRoles
	 */
	public String str_UtilisateurRolesRoyaume(String langueNom) {
		if ("frFR".equals(langueNom))
			return "UtilisateurRolesRoyaume";
		else
			return "UserRealmRoles";
	}

	/**
	 * Var.enUS: str_Line
	 */
	public String str_Ligne(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Ligne";
		else
			return "Line";
	}

	/**
	 * Var.enUS: str_methodNames
	 */
	public String str_methodeNoms(String langueNom) {
		if ("frFR".equals(langueNom))
			return "methodeNoms";
		else
			return "methodNames";
	}

	/**
	 * Var.enUS: str_JsonObject
	 */
	public String str_ObjetJson(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ObjetJson";
		else
			return "JsonObject";
	}

	/**
	 * Var.enUS: str_modify
	 */
	public String str_modifier(String langueNom) {
		if ("frFR".equals(langueNom))
			return "modifier";
		else
			return "modify";
	}

	/**
	 * Var.enUS: str_solrDocuments
	 */
	public String str_documentsSolr(String langueNom) {
		if ("frFR".equals(langueNom))
			return "documentsSolr";
		else
			return "solrDocuments";
	}

	/**
	 * Var.enUS: str_List
	 */
	public String str_Liste(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Liste";
		else
			return "List";
	}

	/**
	 * Var.enUS: str_searchInMillis
	 */
	public String str_millisRecherche(String langueNom) {
		if ("frFR".equals(langueNom))
			return "millisRecherche";
		else
			return "searchInMillis";
	}

	/**
	 * Var.enUS: str_transmissionInMillis
	 */
	public String str_millisTransmission(String langueNom) {
		if ("frFR".equals(langueNom))
			return "millisTransmission";
		else
			return "transmissionInMillis";
	}

	/**
	 * Var.enUS: str_startNum
	 */
	public String str_numCommence(String langueNom) {
		if ("frFR".equals(langueNom))
			return "numCommence";
		else
			return "startNum";
	}

	/**
	 * Var.enUS: str_foundNum
	 */
	public String str_numTrouve(String langueNom) {
		if ("frFR".equals(langueNom))
			return "numTrouve";
		else
			return "foundNum";
	}

	/**
	 * Var.enUS: str_returnedNum
	 */
	public String str_numRetourne(String langueNom) {
		if ("frFR".equals(langueNom))
			return "numRetourne";
		else
			return "returnedNum";
	}

	/**
	 * Var.enUS: str_searchTime
	 */
	public String str_tempsRecherche(String langueNom) {
		if ("frFR".equals(langueNom))
			return "tempsRecherche";
		else
			return "searchTime";
	}

	/**
	 * Var.enUS: str_transmissionTime
	 */
	public String str_tempsTransmission(String langueNom) {
		if ("frFR".equals(langueNom))
			return "tempsTransmission";
		else
			return "transmissionTime";
	}

	/**
	 * Var.enUS: str_solrDocument
	 */
	public String str_documentSolr(String langueNom) {
		if ("frFR".equals(langueNom))
			return "documentSolr";
		else
			return "solrDocument";
	}

	/**
	 * Var.enUS: str_result
	 */
	public String str_resultat(String langueNom) {
		if ("frFR".equals(langueNom))
			return "resultat";
		else
			return "result";
	}

	/**
	 * Var.enUS: str_sqlClient
	 */
	public String str_clientSql(String langueNom) {
		if ("frFR".equals(langueNom))
			return "clientSql";
		else
			return "sqlClient";
	}

	/**
	 * Var.enUS: str_SqlClient
	 */
	public String str_ClientSql(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ClientSql";
		else
			return "SqlClient";
	}

	/**
	 * Var.enUS: str_siteUser
	 */
	public String str_utilisateurSite(String langueNom) {
		if ("frFR".equals(langueNom))
			return "utilisateurSite";
		else
			return "siteUser";
	}

	public String str_UtilisateurSite(String langueNom) {
		if ("frFR".equals(langueNom))
			return "UtilisateurSite";
		else
			return "SiteUser";
	}

	public String str_PrincipalJson(String langueNom) {
		if ("frFR".equals(langueNom))
			return "PrincipalJson";
		else
			return "JsonPrincipal";
	}

	public String str_principalJson(String langueNom) {
		if ("frFR".equals(langueNom))
			return "principalJson";
		else
			return "jsonPrincipal";
	}

	/**
	 * Var.enUS: str_FirstName
	 */
	public String str_Prenom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Prenom";
		else
			return "FirstName";
	}

	/**
	 * Var.enUS: str_LastName
	 */
	public String str_NomFamille(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomFamille";
		else
			return "LastName";
	}

	public String str_NomComplet(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomComplet";
		else
			return "CompleteName";
	}

	public String str_Cree(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Cree";
		else
			return "Created";
	}

	public String str_cree(String langueNom) {
		if ("frFR".equals(langueNom))
			return "cree";
		else
			return "created";
	}

	public String str_modifie(String langueNom) {
		if ("frFR".equals(langueNom))
			return "modifie";
		else
			return "modified";
	}

	public String str_debut(String langueNom) {
		if ("frFR".equals(langueNom))
			return "debut";
		else
			return "start";
	}

	public String str_rechargé(String langueNom) {
		if ("frFR".equals(langueNom))
			return "rechargé";
		else
			return "refreshed";
	}

	public String str_a_démarré(String langueNom) {
		if ("frFR".equals(langueNom))
			return "a démarré";
		else
			return "started";
	}

	public String str_a_réussi(String langueNom) {
		if ("frFR".equals(langueNom))
			return "a réussi";
		else
			return "succeeded";
	}

	public String str_a_échoué(String langueNom) {
		if ("frFR".equals(langueNom))
			return "a échoué";
		else
			return "failed";
	}


	public String str_créé_nouveau(String langueNom) {
		if ("frFR".equals(langueNom))
			return "créé nouveau";
		else
			return "created new";
	}

	public String str_modifié(String langueNom) {
		if ("frFR".equals(langueNom))
			return "modifié";
		else
			return "modified";
	}

	/**
	 * Var.enUS: str_Populate
	 */
	public String str_Peupler(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Peupler";
		else
			return "Populate";
	}

	/**
	 * Var.enUS: str_Store
	 */
	public String str_Stocker(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Stocker";
		else
			return "Store";
	}

	/**
	 * Var.enUS: str_archived
	 */
	public String str_archive(String langueNom) {
		if ("frFR".equals(langueNom))
			return "archive";
		else
			return "archived";
	}

	/**
	 * Var.enUS: str_deleted
	 */
	public String str_supprime(String langueNom) {
		if ("frFR".equals(langueNom))
			return "supprime";
		else
			return "deleted";
	}

	/**
	 * Var.enUS: str_CanonicalName
	 */
	public String str_NomCanonique(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomCanonique";
		else
			return "CanonicalName";
	}

	public String str_classeNomSimple(String langueNom) {
		if ("frFR".equals(langueNom))
			return "classeNomSimple";
		else
			return "classSimpleName";
	}

	/**
	 * Var.enUS: str_classCanonicalName
	 */
	public String str_classeNomCanonique(String langueNom) {
		if ("frFR".equals(langueNom))
			return "classeNomCanonique";
		else
			return "classCanonicalName";
	}

	/**
	 * Var.enUS: str_classCanonicalNames
	 */
	public String str_classeNomsCanoniques(String langueNom) {
		if ("frFR".equals(langueNom))
			return "classeNomsCanoniques";
		else
			return "classCanonicalNames";
	}

	/**
	 * Var.enUS: str_SeeDeleted
	 */
	public String str_VoirSupprime(String langueNom) {
		if ("frFR".equals(langueNom))
			return "VoirSupprime";
		else
			return "SeeDeleted";
	}

	/**
	 * Var.enUS: str_See
	 */
	public String str_Vider(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Vider";
		else
			return "Clear";
	}

	/**
	 * Var.enUS: str_See
	 */
	public String str_Valider(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Accepter";
		else
			return "Accept";
	}

	/**
	 * Var.enUS: str_See
	 */
	public String str_ValiderLaSignature(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Accepter la signature";
		else
			return "Accept the signature";
	}

	/**
	 * Var.enUS: str_See
	 */
	public String str_voir(String langueNom) {
		if ("frFR".equals(langueNom))
			return "voir";
		else
			return "see";
	}

	/**
	 * Var.enUS: str_SeeArchived
	 */
	public String str_VoirArchive(String langueNom) {
		if ("frFR".equals(langueNom))
			return "VoirArchive";
		else
			return "SeeArchived";
	}

	/**
	 * Var.enUS: str_classApiUriMethod
	 */
	public String str_classeApiUriMethode(String langueNom) {
		if ("frFR".equals(langueNom))
			return "classeApiUriMethode";
		else
			return "classApiUriMethod";
	}

	public String str_Envoi(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Envoi";
		else
			return "Sending";
	}

	/**
	 * Var.enUS: str_classApiMethodMethod
	 */
	public String str_classeApiMethodeMethode(String langueNom) {
		if ("frFR".equals(langueNom))
			return "classeApiMethodeMethode";
		else
			return "classApiMethodMethod";
	}

	/**
	 * Var.enUS: str_sort
	 */
	public String str_tri(String langueNom) {
		if ("frFR".equals(langueNom))
			return "tri";
		else
			return "sort";
	}

	/**
	 * Var.enUS: str_Sort
	 */
	public String str_Tri(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Tri";
		else
			return "Sort";
	}

	/**
	 * Var.enUS: str_Start
	 */
	public String str_Debut(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Debut";
		else
			return "Start";
	}

	/**
	 * Var.enUS: str_suggere
	 */
	public String str_suggere(String langueNom) {
		if ("frFR".equals(langueNom))
			return "suggere";
		else
			return "suggest";
	}

	public String str_texte(String langueNom) {
		if ("frFR".equals(langueNom))
			return "texte";
		else
			return "text";
	}

	/**
	 * Var.enUS: str_Suggere
	 */
	public String str_Suggere(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Suggere";
		else
			return "Suggested";
	}

	/**
	 * Var.enUS: str_saves
	 */
	public String str_sauvegardes(String langueNom) {
		if ("frFR".equals(langueNom))
			return "sauvegardes";
		else
			return "saves";
	}

	/**
	 * Var.enUS: str_unindex
	 */
	public String str_desindexer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "desindexer";
		else
			return "unindex";
	}

	/**
	 * Var.enUS: str_addGlow
	 */
	public String str_ajouterLueur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ajouterLueur";
		else
			return "addGlow";
	}

	/**
	 * Var.enUS: str_add
	 */
	public String str_ajouter(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ajouter";
		else
			return "add";
	}

	/**
	 * Var.enUS: str_addError
	 */
	public String str_ajouterErreur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ajouterErreur";
		else
			return "addError";
	}

	/**
	 * Var.enUS: str_removeGlow
	 */
	public String str_enleverLueur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "enleverLueur";
		else
			return "removeGlow";
	}

	/**
	 */
	public String str_DDDashMMDashYYYY(String langueNom) {
		if ("frFR".equals(langueNom))
			return "DD-MM-YYYY";
		else
			return "MM/DD/YYYY";
	}

	/**
	 */
	public String str_DDDashMMDashYYYY_HHColonMM(String langueNom) {
		if ("frFR".equals(langueNom))
			return "DD-MM-YYYY HH:MM";
		else
			return "MM/DD/YYYY HH:MM AM";
	}

	/**
	 */
	public String str_HHColonMM(String langueNom) {
		if ("frFR".equals(langueNom))
			return "HH:MM";
		else
			return "HH:MM A";
	}

	/**
	 */
	public String str_ddDashMMDashyyyy(String langueNom) {
		if ("frFR".equals(langueNom))
			return "dd-MM-yyyy";
		else
			return "MM/dd/yyyy";
	}

	/**
	 */
	public String str_HAposhAposmm(String langueNom) {
		if ("frFR".equals(langueNom))
			return "H'h'mm";
		else
			return "h:mm a";
	}

	/**
	 */
	public String str_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz_VV(String langueNom) {
		if ("frFR".equals(langueNom))
			return "EEE d MMM yyyy H'h'mm:ss zz VV";
		else
			return "EEE d MMM yyyy H:mm:ss a zz";
	}

	/**
	 */
	public String str_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz(String langueNom) {
		if ("frFR".equals(langueNom))
			return "EEE d MMM yyyy H'h'mm:ss a zz";
		else
			return "EEE MMM d, yyyy H:mm:ss a zz";
	}

	/**
	 */
	public String str_EEE_d_MMM_yyyy(String langueNom) {
		if ("frFR".equals(langueNom))
			return "EEE d MMM yyyy";
		else
			return "EEE MMM d, yyyy";
	}

	/**
	 * Var.enUS: str_enDashUS
	 */
	public String str_frDashFR(String langueNom) {
		if ("frFR".equals(langueNom))
			return "fr-FR";
		else
			return "en-US";
	}

	public String str_Interne(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Interne";
		else
			return "Internal";
	}

	public String str_Contexte(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Contexte";
		else
			return "Context";
	}

	public String str_InitLoin(String langueNom) {
		if ("frFR".equals(langueNom))
			return "InitLoin";
		else
			return "InitDeep";
	}

	public String str_Api(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Api";
		else
			return "Api";
	}

	public String str_Modele(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Modele";
		else
			return "Model";
	}

	public String str_PageSuper(String langueNom) {
		if ("frFR".equals(langueNom))
			return "PageSuper";
		else
			return "SuperPage";
	}

	public String str_Page(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Page";
		else
			return "Page";
	}

	public String str_TypeContenu(String langueNom) {
		if ("frFR".equals(langueNom))
			return "TypeContenu";
		else
			return "ContentType";
	}

	public String str_PageSimple(String langueNom) {
		if ("frFR".equals(langueNom))
			return "PageSimple";
		else
			return "SimplePage";
	}

	public String str_Sauvegarde(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Sauvegarde";
		else
			return "Saved";
	}

	public String str_RoleSession(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RoleSession";
		else
			return "RoleSession";
	}

	public String str_ApiTag(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ApiTag";
		else
			return "ApiTag";
	}

	public String str_ApiUri(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ApiUri";
		else
			return "ApiUri";
	}

	public String str_Future(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Future";
		else
			return "Future";
	}

	public String str_Promesse(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Promesse";
		else
			return "Promise";
	}

	public String str_Admin(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Admin";
		else
			return "Admin";
	}

	public String str_RoleUtilisateur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RoleUtilisateur";
		else
			return "RoleUser";
	}

	public String str_RoleChacun(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RoleChacun";
		else
			return "RoleAll";
	}

	public String str_Role(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Role";
		else
			return "Role";
	}

	public String str_RoleLire(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RoleLire";
		else
			return "RoleRead";
	}

	public String str_PublicLire(String langueNom) {
		if ("frFR".equals(langueNom))
			return "PublicLire";
		else
			return "PublicRead";
	}

	public String str_Filtre(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Filtre";
		else
			return "Filter";
	}

	public String str_MotCle(String langueNom) {
		if ("frFR".equals(langueNom))
			return "MotCle";
		else
			return "Keyword";
	}

	public String str_ApiMethode(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ApiMethode";
		else
			return "ApiMethod";
	}

	public String str_apiMethode(String langueNom) {
		if ("frFR".equals(langueNom))
			return "apiMethode";
		else
			return "apiMethod";
	}

	public String str_Couleur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Couleur";
		else
			return "Color";
	}

	public String str_IconeGroupe(String langueNom) {
		if ("frFR".equals(langueNom))
			return "IconeGroupe";
		else
			return "IconGroup";
	}

	public String str_IconeNom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "IconeNom";
		else
			return "IconName";
	}

	public String str_Lignes(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Lignes";
		else
			return "Rows";
	}

	public String str_VideoId(String langueNom) {
		if ("frFR".equals(langueNom))
			return "VideoId";
		else
			return "VideoId";
	}

	public String str_Description(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Description";
		else
			return "Description";
	}

	public String str_ImageLargeur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ImageLargeur";
		else
			return "ImageWidth";
	}

	public String str_ImageHauteur(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ImageHauteur";
		else
			return "ImageHeight";
	}

	public String str_Trouver_la_classe_(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Trouver la classe ";
		else
			return "Find the class ";
	}

	public String str_Trouver_l_entité_(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Trouver l'entité ";
		else
			return "Find the entity ";
	}

	public String str__dans_Solr(String langueNom) {
		if ("frFR".equals(langueNom))
			return " dans Solr";
		else
			return " in Solr";
	}

	public String str_tempsRestant(String langueNom) {
		if ("frFR".equals(langueNom))
			return "tempsRestant";
		else
			return "timeRemaining";
	}

	public String str__est_défini_comme_null_avant_d_être_initialisé__(String langueNom) {
		if ("frFR".equals(langueNom))
			return " est défini comme null avant d'être initialisé. ";
		else
			return " is defined as null before being initialized. ";
	}

	public String str_Il_est_construit_avant_d_être_initialisé_avec_le_constructeur_par_défaut_(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Il est construit avant d'être initialisé avec le constructeur par défaut ";
		else
			return " It is constructed before being initialized with the constructor by default ";
	}

	public String str__est_pour_envelopper_une_valeur_à_assigner_à_cette_entité_lors_de_l_initialisation__(
			String langueNom) {
		if ("frFR".equals(langueNom))
			return " est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. ";
		else
			return " is for wrapping a value to assign to this entity during initialization. ";
	}

	public String str__est_l_entité_déjà_construit__(String langueNom) {
		if ("frFR".equals(langueNom))
			return " est l'entité déjà construit. ";
		else
			return " is the entity already constructed. ";
	}

	public String str_L_entité_(String langueNom) {
		if ("frFR".equals(langueNom))
			return " L'entité ";
		else
			return " The entity ";
	}

	public String str_UnNom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "UnNom";
		else
			return "AName";
	}

	public String str_Adjectif(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Adjectif";
		else
			return "Adjective";
	}

	public String str_NomSingulier(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomSingulier";
		else
			return "NameSingular";
	}

	public String str_NomPluriel(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomPluriel";
		else
			return "NamePlural";
	}

	public String str_NomVar(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomVar";
		else
			return "NameVar";
	}

	public String str_LesNoms(String langueNom) {
		if ("frFR".equals(langueNom))
			return "LesNom";
		else
			return "ThePluralName";
	}

	public String str_AdjectifPluriel(String langueNom) {
		if ("frFR".equals(langueNom))
			return "AdjectifPluriel";
		else
			return "AdjectivePlural";
	}

	public String str_Image(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Image";
		else
			return "Image";
	}

	public String str_AdjectifVar(String langueNom) {
		if ("frFR".equals(langueNom))
			return "AdjectifVar";
		else
			return "AdjectiveVar";
	}

	public String str_NomAdjectifSingulier(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomAdjectifSingulier";
		else
			return "NameAdjectiveSingular";
	}

	public String str_NomAdjectifPluriel(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomAdjectifPluriel";
		else
			return "NameAdjectivePlural";
	}

	public String str_NomSingulierAdjectifPluriel(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomSingulierAdjectifPluriel";
		else
			return "NameSingularAdjectivePlural";
	}

	public String str_Ce(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Ce";
		else
			return "This";
	}

	public String str_Un(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Un";
		else
			return "A";
	}

	public String str_Modifie(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Modifie";
		else
			return "Modified";
	}

	public String str_NomActual(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomActuel";
		else
			return "NameCurrent";
	}

	public String str_Tous(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Tous";
		else
			return "All";
	}

	public String str_TousNom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "TousNom";
		else
			return "AllName";
	}

	public String str_RechercherTousNomPar(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RechercherTousNomPar";
		else
			return "SearchAllNameBy";
	}

	public String str_RechercherTousNom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "RechercherTousNom";
		else
			return "SearchAllName";
	}

	public String str_AucunNomTrouve(String langueNom) {
		if ("frFR".equals(langueNom))
			return "AucunNomTrouve";
		else
			return "NoNameFound";
	}

	public String str_UnNomAdjectif(String langueNom) {
		if ("frFR".equals(langueNom))
			return "UnNomAdjectif";
		else
			return "ANameAdjective";
	}

	public String str_NomActuel(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomActuel";
		else
			return "NameActual";
	}

	public String str_CeNom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "CeNom";
		else
			return "ThisName";
	}

	public String str_LeNom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "LeNom";
		else
			return "TheName";
	}

	public String str_DeNom(String langueNom) {
		if ("frFR".equals(langueNom))
			return "DeNom";
		else
			return "OfName";
	}

	public String str_LeNomAdjectif(String langueNom) {
		if ("frFR".equals(langueNom))
			return "LeNomAdjectif";
		else
			return "TheNameAdjective";
	}

	public String str_Exact(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Exact";
		else
			return "Exact";
	}

	public String str_InheritClePrimaire(String langueNom) {
		if ("frFR".equals(langueNom))
			return "InheritClePrimaire";
		else
			return "InheritPrimaryKey";
	}

	public String str_ClePrimaire(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ClePrimaire";
		else
			return "PrimaryKey";
	}

	public String str_Sauvegardes(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Sauvegardes";
		else
			return "Saves";
	}

	public String str_CleUnique(String langueNom) {
		if ("frFR".equals(langueNom))
			return "CleUnique";
		else
			return "UniqueKey";
	}

	public String str_Crypte(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Crypte";
		else
			return "Encrypted";
	}

	public String str_VarTitre(String langueNom) {
		if ("frFR".equals(langueNom))
			return "VarTitre";
		else
			return "VarTitle";
	}

	public String str_VarDescription(String langueNom) {
		if ("frFR".equals(langueNom))
			return "VarDescription";
		else
			return "VarDescription";
	}

	public String str_VarModifie(String langueNom) {
		if ("frFR".equals(langueNom))
			return "VarModifie";
		else
			return "VarModified";
	}

	public String str_VarCree(String langueNom) {
		if ("frFR".equals(langueNom))
			return "VarCree";
		else
			return "VarCreated";
	}

	public String str_Incremente(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Incremente";
		else
			return "Incremented";
	}

	public String str_Texte(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Texte";
		else
			return "Text";
	}

	public String str_Stocke(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Stocke";
		else
			return "Stored";
	}

	public String str_Ignorer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Ignorer";
		else
			return "Ignore";
	}

	public String str_Declarer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Declarer";
		else
			return "Declare";
	}

	public String str_Rechercher(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Rechercher";
		else
			return "Search";
	}

	public String str_Ajouter(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Ajouter";
		else
			return "Add";
	}

	public String str_Supprimer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Supprimer";
		else
			return "Delete";
	}

	public String str_Modifier(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Modifier";
		else
			return "Modify";
	}

	public String str_Multiligne(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Multiligne";
		else
			return "Multiline";
	}

	public String str_Signature(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Signature";
		else
			return "Signature";
	}

	public String str_ConfigCles(String langueNom) {
		if ("frFR".equals(langueNom))
			return "ConfigCles";
		else
			return "ConfigKeys";
	}

	public String str_Cles(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Cles";
		else
			return "Keys";
	}

	public String str_Langue(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Langue";
		else
			return "Language";
	}

	public String str_NomAffichage(String langueNom) {
		if ("frFR".equals(langueNom))
			return "NomAffichage";
		else
			return "DisplayName";
	}

	public String str_Optionnel(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Optionnel";
		else
			return "Optional";
	}

	public String str_EnumNomSimple(String langueNom) {
		if ("frFR".equals(langueNom))
			return "EnumNomSimple";
		else
			return "EnumSimpleName";
	}

	public String str_EnumVarDescription(String langueNom) {
		if ("frFR".equals(langueNom))
			return "EnumVarDescription";
		else
			return "EnumVarDescription";
	}

	public String str_HtmlColonne(String langueNom) {
		if ("frFR".equals(langueNom))
			return "HtmlColonne";
		else
			return "HtmlColumn";
	}

	public String str_HtmlLigne(String langueNom) {
		if ("frFR".equals(langueNom))
			return "HtmlLigne";
		else
			return "HtmlRow";
	}

	public String str_HtmlCellule(String langueNom) {
		if ("frFR".equals(langueNom))
			return "HtmlCellule";
		else
			return "HtmlCell";
	}

	public String str_LongeurMin(String langueNom) {
		if ("frFR".equals(langueNom))
			return "LongeurMin";
		else
			return "LengthMin";
	}

	public String str_LongeurMax(String langueNom) {
		if ("frFR".equals(langueNom))
			return "LongeurMax";
		else
			return "LengthMax";
	}

	public String str_Attribuer(String langueNom) {
		if ("frFR".equals(langueNom))
			return "Attribuer";
		else
			return "Attribute";
	}

	/**
	 * Var.enUS: languageName enUS: The configured language name for this app.
	 */
	public String langueNom;

	/**
	 * Var.enUS: _languageName r: langueNom r.enUS: languageName r: appliNom r.enUS:
	 * appName
	 **/
	protected void _langueNom() throws Exception {
		langueNom = System.getenv("lang");
	}

	/**
	 * Var.enUS: appName frFR: Le nom de l'lappli. enUS: The name of the
	 * application.
	 **/
	public String appliNom;

	/**
	 * Var.enUS: _appName r: appliNom r.enUS: appName
	 **/
	protected void _appliNom() throws Exception {
		if (appliNom == null)
			appliNom = System.getenv(str_appliNom(langueNom));
	}

	/**
	 * Var.enUS: appPath frFR: Le chemin vers l'lappli. enUS: The path to the
	 * application.
	 **/
	public String appliChemin;

	/**
	 * Var.enUS: _appPath r: appliChemin r.enUS: appPath
	 **/
	protected void _appliChemin() throws Exception {
		if (appliChemin == null)
			appliChemin = System.getenv(str_appliChemin(langueNom));
	}

	/**
	 * Var.enUS: appPathVertx frFR: Le chemin vers l'lappli. enUS: The path to the
	 * application.
	 **/
	public String appliCheminVertx;

	/**
	 * Var.enUS: _appPathVertx r: appliCheminVertx r.enUS: appPathVertx
	 **/
	protected void _appliCheminVertx() throws Exception {
		if (appliCheminVertx == null)
			appliCheminVertx = System.getenv(str_appliChemin(langueNom) + "Vertx");
	}

	/**
	 * Var.enUS: srcMainJavaPath enUS: The absolute path to the /src/main/java
	 * directory.
	 */
	public String cheminSrcMainJava;

	/**
	 * Var.enUS: _srcMainJavaPath r: cheminSrcMainJava r.enUS: srcMainJavaPath r:
	 * appliChemin r.enUS: appPath
	 **/
	protected void _cheminSrcMainJava() throws Exception {
		cheminSrcMainJava = appliChemin + "/src/main/java";
	}

	/**
	 * Var.enUS: srcMainResourcesPath enUS: The absolute path to the
	 * /src/main/resources directory.
	 */
	public String cheminSrcMainResources;

	/**
	 * Var.enUS: _srcMainResourcesPath r: cheminSrcMainResources r.enUS:
	 * srcMainResourcesPath r: appliChemin r.enUS: appPath
	 **/
	protected void _cheminSrcMainResources() throws Exception {
		cheminSrcMainResources = appliChemin + "/src/main/resources";
	}

	/**
	 * Var.enUS: srcGenJavaPath enUS: The absolute path to the /src/gen/java
	 * directory.
	 */
	public String cheminSrcGenJava;

	/**
	 * Var.enUS: _srcGenJavaPath r: cheminSrcGenJava r.enUS: srcGenJavaPath r:
	 * appliChemin r.enUS: appPath
	 **/
	protected void _cheminSrcGenJava() throws Exception {
		cheminSrcGenJava = appliChemin + "/src/gen/java";
	}

	/**
	 * Var.enUS: configPath enUS: The absolute path to the app config file.
	 */
	public String configChemin;

	/**
	 * Var.enUS: _configPath r: configChemin r.enUS: configPath r: appliChemin
	 * r.enUS: appPath r: appliNom r.enUS: appName
	 **/
	protected void _configChemin() throws Exception {
		configChemin = appliChemin + "/config/" + appliNom + ".config";
	}
//
//	/**
//	 * Var.enUS: configPath
//	 * enUS: The absolute path to the config file. 
//	 */
//	public String configChemin;
//	/**	
//	 * Var.enUS: _configPath
//	 * r.enUS: configChemin
//	 * configPath
//	 * r.enUS: configChemin
//	 * configPath
//	 * r.enUS: appliNom
//	 * appName
//	 * r.enUS: appliChemin
//	 * appPath
//	 * r.enUS: nomFichierConfig
//	 * configFileName
//	 **/ 
//	protected void _configChemin() throws Exception {
//		configChemin = config.getString(StringUtils.replace(appliNom, ".", "..") + ".configChemin", appliChemin + "/config/" + nomFichierConfig);
//	}

	/**
	 * Var.enUS: configFile enUS: The File Object for the app config file.
	 */
	public File fichierConfig;

	/**
	 * Var.enUS: _configFile r: fichierConfig r.enUS: configFile r: configChemin
	 * r.enUS: configPath
	 **/
	protected void _fichierConfig() throws Exception {
		fichierConfig = new File(configChemin);
	}

	/**
	 * enUS: The Apache Commons Configurations object for reading config files.
	 */
	public Configurations configurations;

	protected void _configurations() throws Exception {
		configurations = new Configurations();
	}

	/**
	 * The INI Configuration Object for the config file.
	 */
	public INIConfiguration config;

	/**
	 * r: fichierConfig r.enUS: configFile
	 **/
	protected void _config() throws Exception {
		config = configurations.ini(fichierConfig);
	}

	/**
	 * Var.enUS: languageActualName enUS: The actual language of the code. enUS: If
	 * the languageName is configured as "tout", then the languageActualName would
	 * be "frFR".
	 */
	public String langueNomActuel;

	/**
	 * Var.enUS: _languageActualName r: langueNomActuel r.enUS: languageActualName
	 * r: langueNom r.enUS: languageName
	 **/
	protected void _langueNomActuel() throws Exception {
//		if(StringUtils.equals(langueNom, "tout"))
//			langueNomActuel = "frFR";
//		else
		langueNomActuel = langueNom;
	}

	/**
	 * Var.enUS: otherLanguages enUS: Other languages supported by this app besides
	 * the actual language name.
	 */
	public String[] autresLangues;

	/**
	 * Var.enUS: _otherLanguages r: autresLangues r.enUS: otherLanguages r:
	 * toutesLangues r.enUS: allLanguages r: langueNom r.enUS: languageName r:
	 * appliNom r.enUS: appName
	 **/
	protected void _autresLangues() throws Exception {
		autresLangues = config
				.getStringArray(StringUtils.replace(appliNom, ".", "..") + "." + str_autresLangues(langueNom));
	}

	/**
	 * Var.enUS: allLanguages enUS: All language names supported in this
	 * application.
	 */
	public String[] toutesLangues;

	/**
	 * Var.enUS:classOtherLanguages enUS: Other language names supported in this
	 * application.
	 */
	public String[] classeAutresLangues;

	/**
	 * Var.enUS: _allLanguages r: toutesLangues r.enUS: allLanguages r: appliNom
	 * r.enUS: appName r: autresLangues r.enUS: otherLanguages r: langueNom r.enUS:
	 * languageName
	 **/
	protected void _toutesLangues() throws Exception {
		toutesLangues = ArrayUtils.add(ArrayUtils.addAll(autresLangues), langueNom);
	}

	/**
	 * Var.enUS: languageIndexed enUS: True if the languageName is an actual locale
	 * that can be indexed. enUS: If the languageName is configured as "tout", then
	 * that language would not be indexed.
	 */
	public Boolean langueIndexe;

	/**
	 * Var.enUS: _languageIndexed r: langueIndexe r.enUS: languageIndexed r:
	 * toutesLangues r.enUS: allLanguages r: langueNom r.enUS: languageName
	 **/
	protected void _langueIndexe() throws Exception {
		langueIndexe = ArrayUtils.contains(toutesLangues, langueNom);
	}

	/**
	 * Var.enUS: domainName enUS: The domain name of the website where this will be
	 * deployed (like "example.com").
	 */
	public String nomDomaine;

	/**
	 * Var.enUS: _domainName r: nomDomaine r.enUS: domainName r: appliNom r.enUS:
	 * appName
	 **/
	protected void _nomDomaine() throws Exception {
		nomDomaine = config.getString(StringUtils.replace(appliNom, ".", "..") + "." + str_nomDomaine(langueNom));
	}

	/**
	 * Var.enUS: siteBaseUrl enUS: The base URL to the site when deployed.
	 */
	public String siteUrlBase;

	/**
	 * Var.enUS: _siteBaseUrl r: appliNom r.enUS: appName r: nomDomaine r.enUS:
	 * domainName r: siteUrlBase r.enUS: siteBaseUrl
	 **/
	protected void _siteUrlBase() throws Exception {
		siteUrlBase = config.getString(StringUtils.replace(appliNom, ".", "..") + "." + str_siteUrlBase(langueNom));
	}

	/**
	 * Var.enUS: domainPackageName enUS: The Java package name for the domain
	 * (example.com would have a package name of "com.example").
	 */
	public String nomEnsembleDomaine;

	/**
	 * Var.enUS: _domainPackageName r: nomEnsembleDomaine r.enUS: domainPackageName
	 * r: nomDomaine r.enUS: domainName r: partis r.enUS: parts r: appliNom r.enUS:
	 * appName
	 **/
	protected void _nomEnsembleDomaine() throws Exception {
		nomEnsembleDomaine = config
				.getString(StringUtils.replace(appliNom, ".", "..") + "." + str_nomEnsembleDomaine(langueNom));
		if (StringUtils.isEmpty(nomEnsembleDomaine)) {
			String[] partis = StringUtils.split(nomDomaine, ".");
			ArrayUtils.reverse(partis);
			nomEnsembleDomaine = StringUtils.join(partis, ".");
		}
	}

	/**
	 * Var.enUS: configFileName enUS: The name of the config file which defaults to
	 * the appName followed by ".config".
	 */
	public String nomFichierConfig;

	/**
	 * Var.enUS: _configFileName r: nomFichierConfig r.enUS: configFileName r:
	 * appliNom r.enUS: appName
	 **/
	protected void _nomFichierConfig() throws Exception {
		nomFichierConfig = config.getString(
				StringUtils.replace(appliNom, ".", "..") + "." + str_nomFichierConfig(langueNom), appliNom + ".config");
	}

	public String siteZone;

	protected void _siteZone() throws Exception {
		siteZone = config.getString(
				StringUtils.replace(appliNom, ".", "..") + "." + str_siteZone(langueNom), appliNom + ".config");
	}
//
//	/**
//	 * Var.enUS: mavenVersion
//	 * enUS: The version of maven being used. 
//	 */
//	public String versionMaven;
//	/**	
//	 * Var.enUS: _mavenVersion
//	 * r: versionMaven
//	 * r.enUS: mavenVersion
//	 **/ 
//	protected void _versionMaven() throws Exception {
//		versionMaven = config.getString("maven.versionMaven", "3.5.3");
//	}
//
//	/**
//	 * Var.enUS: zookeeperVersion
//	 * enUS: The version of Zookeeper being used. 
//	 */
//	public String versionZookeeper;
//	/**	
//	 * Var.enUS: _zookeeperVersion
//	 * r: versionZookeeper
//	 * r.enUS: zookeeperVersion
//	 **/ 
//	protected void _versionZookeeper() throws Exception {
//		versionZookeeper = config.getString("maven.versionZookeeper", "3.5.4");
//	}
//
//	/**
//	 * Var.enUS: zookeeperPortPrefix
//	 * enUS: The port prefix for zookeeper (default is "102"). 
//	 */
//	public String prefixePortZookeeper;
//	/**	
//	 * Var.enUS: _zookeeperPortPrefix
//	 * r: prefixePortZookeeper
//	 * r.enUS: zookeeperPortPrefix
//	 **/ 
//	protected void _prefixePortZookeeper() throws Exception {
//		prefixePortZookeeper = config.getString("zookeeper.prefixePortZookeeper", "102");
//	}
//
//	/**
//	 * Var.enUS: zookeeperClientPort
//	 * enUS: The Zookeeper client port (default is "10281"). 
//	 */
//	public String portClientZookeeper;
//	/**	
//	 * Var.enUS: _zookeeperClientPort
//	 * r: portClientZookeeper
//	 * r.enUS: zookeeperClientPort
//	 * r: prefixePortZookeeper
//	 * r.enUS: zookeeperPortPrefix
//	 **/ 
//	protected void _portClientZookeeper() throws Exception {
//		portClientZookeeper = config.getString("zookeeper.portClientZookeeper", prefixePortZookeeper + "81");
//	}
//
//	/**
//	 * Var.enUS: zookeeperAdminPort
//	 * enUS: The Zookeeper admin port (default is "10280"). 
//	 */
//	public String portAdminZookeeper;
//	/**	
//	 * Var.enUS: _zookeeperAdminPort
//	 * r: portAdminZookeeper
//	 * r.enUS: zookeeperAdminPort
//	 * r: prefixePortZookeeper
//	 * r.enUS: zookeeperPortPrefix
//	 **/ 
//	protected void _portAdminZookeeper() throws Exception {
//		portAdminZookeeper = config.getString("zookeeper.portAdminZookeeper", prefixePortZookeeper + "80");
//	}
//
//	/**
//	 * Var.enUS: solrVersion
//	 * enUS: The version of Solr being used. 
//	 */
//	public String versionSolr;
//	/**	
//	 * Var.enUS: _solrVersion
//	 * r: versionSolr
//	 * r.enUS: solrVersion
//	 **/ 
//	protected void _versionSolr() throws Exception {
//		versionSolr = config.getString("solr.versionSolr", "7.1.0");
//	}
//
//	/**
//	 * Var.enUS: solrPortPrefix
//	 * enUS: The port prefix for Solr (default is "103"). 
//	 */
//	public String prefixePortSolr;
//	/**	
//	 * Var.enUS: _solrPortPrefix
//	 * r: prefixePortSolr
//	 * r.enUS: solrPortPrefix
//	 **/ 
//	protected void _prefixePortSolr() throws Exception {
//		prefixePortSolr = config.getString("solr.prefixePortSolr", "103");
//	}
//
//	/**
//	 * Var.enUS: solrPort
//	 * enUS: The Solr web port (default is "10383"). 
//	 */ 
//	public String portSolr;
//	/**	
//	 * Var.enUS: _solrPort
//	 * r: portSolr
//	 * r.enUS: solrPort
//	 * r: prefixePortSolr
//	 * r.enUS: solrPortPrefix
//	 **/ 
//	protected void _portSolr() throws Exception {
//		portSolr = config.getString("solr.portSolr", prefixePortSolr + "83");
//	}

	/**
	 * Var.enUS: solrUrlComputate enUS: The Solr web URL for the "computate" index.
	 */
	public String solrUrlComputate;

	/**
	 * Var.enUS: _solrUrlComputate r: solrUrlComputate r.enUS: solrUrlComputate r:
	 * solrUrl r.enUS: solrUrl r: portSolr r.enUS: solrPort
	 **/
	protected void _solrUrlComputate() throws Exception {
//		solrUrlComputate = config.getString("solr.solrUrl", "http://localhost:" + portSolr + "/solr/computate");
		solrUrlComputate = config
				.getString(StringUtils.replace(appliNom, ".", "..") + "." + str_solrUrl(langueNom) + "Computate");
	}

	/**
	 * Var.enUS: solrClientComputate enUS: The Solrj client for the "computate"
	 * index.
	 */
	public SolrClient clientSolrComputate;

	/**
	 * Var.enUS: _solrClientComputate r: clientSolrComputate r.enUS:
	 * solrClientComputate r: solrUrlComputate r.enUS: solrUrlComputate
	 **/
	protected void _clientSolrComputate() throws Exception {
		clientSolrComputate = new HttpSolrClient.Builder(solrUrlComputate).build();
	}

	/**
	 * Var.enUS: sourcePaths enUS: The absolute paths to source code directories in
	 * the app to watch for changes.
	 */
	public ArrayList<String> cheminsSource = new ArrayList<String>();

	/**
	 * Var.enUS: _sourcePaths r: cheminsSource r.enUS: sourcePaths r:
	 * cheminSrcMainJava r.enUS: srcMainJavaPath r: cheminSrcGenJava r.enUS:
	 * srcGenJavaPath
	 **/
	protected void _cheminsSource() throws Exception {
		cheminsSource.add(cheminSrcMainJava);
		cheminsSource.add(cheminSrcGenJava);
	}

	/**
	 * Var.enUS: allSourcePaths enUS: The absolute paths to source code directories
	 * in all apps to watch for changes.
	 */
	public ArrayList<String> toutCheminsSource = new ArrayList<String>();

	/**
	 * Var.enUS: _allSourcePaths r: toutCheminsSource r.enUS: allSourcePaths r:
	 * cheminSrcMainJava r.enUS: srcMainJavaPath r: cheminSrcGenJava r.enUS:
	 * srcGenJavaPath
	 **/
	protected void _toutCheminsSource() throws Exception {
		toutCheminsSource.add(cheminSrcMainJava);
		toutCheminsSource.add(cheminSrcGenJava);
	}

	/**
	 * Var.enUS: testMethodNames enUS: The names of methods that will be tested when
	 * a source file is updated.
	 */
	public ArrayList<String> nomsMethodeTest = new ArrayList<String>();

	/**
	 * Var.enUS: _testMethodNames
	 **/
	protected void _nomsMethodeTest() throws Exception {
	}

	/**
	 * Var.enUS: siteEncrypted enUS: True if the data for the site should be
	 * encrypted.
	 */
	public Boolean siteCrypte;

	/**
	 * Var.enUS: _siteEncrypted r: siteCrypte r.enUS: siteEncrypted r: appliNom
	 * r.enUS: appName
	 **/
	protected void _siteCrypte() throws Exception {
		siteCrypte = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + str_siteCrypte(langueNom),
				false);
	}

	public Boolean customerProfileId1;

	protected void _customerProfileId1() throws Exception {
		customerProfileId1 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId1",
				false);
	}

	public Boolean customerProfileId2;

	protected void _customerProfileId2() throws Exception {
		customerProfileId2 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId2",
				false);
	}

	public Boolean customerProfileId3;

	protected void _customerProfileId3() throws Exception {
		customerProfileId3 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId3",
				false);
	}

	public Boolean customerProfileId4;

	protected void _customerProfileId4() throws Exception {
		customerProfileId4 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId4",
				false);
	}

	public Boolean customerProfileId5;

	protected void _customerProfileId5() throws Exception {
		customerProfileId5 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId5",
				false);
	}

	public Boolean customerProfileId6;

	protected void _customerProfileId6() throws Exception {
		customerProfileId6 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId6",
				false);
	}

	public Boolean customerProfileId7;

	protected void _customerProfileId7() throws Exception {
		customerProfileId7 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId7",
				false);
	}

	public Boolean customerProfileId8;

	protected void _customerProfileId8() throws Exception {
		customerProfileId8 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId8",
				false);
	}

	public Boolean customerProfileId9;

	protected void _customerProfileId9() throws Exception {
		customerProfileId9 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId9",
				false);
	}

	public Boolean customerProfileId10;

	protected void _customerProfileId10() throws Exception {
		customerProfileId10 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId10",
				false);
	}

	/**
	 * Var.enUS: siteWriteMethods
	 */
	public ArrayList<String> siteEcrireMethodes = new ArrayList<String>();

	/**
	 * Var.enUS: _siteWriteMethods r: siteEcrireMethodes r.enUS: siteWriteMethods r:
	 * appliNom r.enUS: appName
	 **/
	protected void _siteEcrireMethodes() throws Exception {
		List<String> o = config.getList(String.class,
				StringUtils.replace(appliNom, ".", "..") + "." + str_siteEcrireMethodes(langueNom));
		if (o != null)
			siteEcrireMethodes.addAll(o);
	}

	/**
	 * Var.enUS: writeApi
	 */
	public Boolean ecrireApi;

	/**
	 * Var.enUS: _writeApi r: ecrireApi r.enUS: writeApi r: appliNom r.enUS: appName
	 **/
	protected void _ecrireApi() throws Exception {
		ecrireApi = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + str_ecrireApi(langueNom), true);
	}

	public Boolean ecrireCommentaire;

	protected void _ecrireCommentaire() throws Exception {
		ecrireCommentaire = config
				.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + str_ecrireCommentaire(langueNom), true);
	}

	public Boolean activerSupprime;

	protected void _activerSupprime() throws Exception {
		activerSupprime = config.getBoolean(
				StringUtils.replace(appliNom, ".", "..") + "." + str_activer(langueNom) + str_Supprime(langueNom),
				true);
	}

	public Boolean activerArchive;

	protected void _activerArchive() throws Exception {
		activerArchive = config.getBoolean(
				StringUtils.replace(appliNom, ".", "..") + "." + str_activer(langueNom) + str_Archive(langueNom), true);
	}

	public Boolean activerUtilisateurCle;

	protected void _activerUtilisateurCle() throws Exception {
		activerUtilisateurCle = config.getBoolean(
				StringUtils.replace(appliNom, ".", "..") + "." + str_activer(langueNom) + str_UtilisateurCle(langueNom),
				true);
	}

	public Boolean activerSessionId;

	protected void _activerSessionId() throws Exception {
		activerSessionId = config.getBoolean(
				StringUtils.replace(appliNom, ".", "..") + "." + str_activer(langueNom) + str_SessionId(langueNom),
				true);
	}

	public Boolean activerRoleAdmin;

	protected void _activerRoleAdmin() throws Exception {
		activerRoleAdmin = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + str_activer(langueNom)
				+ str_Role(langueNom) + str_Admin(langueNom), true);
	}

	public Boolean activerOpenIdConnect;

	protected void _activerOpenIdConnect() throws Exception {
		activerOpenIdConnect = config.getBoolean(
				StringUtils.replace(appliNom, ".", "..") + "." + str_activer(langueNom) + "OpenIdConnect", true);
	}

	/**
	 * Var.enUS: initSiteConfig r: fichierConfig r.enUS: configFile r:
	 * langueNomActuel r.enUS: languageActualName r: langueIndexe r.enUS:
	 * languageIndexed r: autresLangues r.enUS: otherLanguages r: toutesLangues
	 * r.enUS: allLanguages r: langueNom r.enUS: languageName r: nomEnsembleDomaine
	 * r.enUS: domainPackageName r: nomDomaine r.enUS: domainName r:
	 * nomFichierConfig r.enUS: configFileName r: configChemin r.enUS: configPath r:
	 * configChemin r.enUS: configPath r: appliNom r.enUS: appName r: appliChemin
	 * r.enUS: appPath r: nomFichierConfig r.enUS: configFileName r: versionMaven
	 * r.enUS: mavenVersion r: versionZookeeper r.enUS: zookeeperVersion r:
	 * prefixePortZookeeper r.enUS: zookeeperPortPrefix r: portAdminZookeeper
	 * r.enUS: zookeeperAdminPort r: portClientZookeeper r.enUS: zookeeperClientPort
	 * r: versionSolr r.enUS: solrVersion r: prefixePortSolr r.enUS: solrPortPrefix
	 * r: portSolr r.enUS: solrPort r: solrUrlComputate r.enUS: solrUrlComputate r:
	 * clientSolrComputate r.enUS: solrClientComputate r: cheminsSource r.enUS:
	 * sourcePaths r: toutCheminsSource r.enUS: allSourcePaths r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath r: cheminSrcMainResources r.enUS:
	 * srcMainResourcesPath r: cheminSrcGenJava r.enUS: srcGenJavaPath r:
	 * nomsMethodeTest r.enUS: testMethodNames r: siteCrypte r.enUS: siteEncrypted
	 * r: siteEcrireMethodes r.enUS: siteWriteMethods r: ecrireApi r.enUS: writeApi
	 * r: siteUrlBase r.enUS: siteBaseUrl
	 **/
	public void initConfigSite() throws Exception {
		_langueNom();
		_appliNom();
		_appliChemin();
		_appliCheminVertx();
		_cheminSrcMainJava();
		_cheminSrcMainResources();
		_cheminSrcGenJava();
		_configChemin();
		_fichierConfig();
		_configurations();
		_config();
		_langueNomActuel();
		_autresLangues();
		_toutesLangues();
		_langueIndexe();
		_nomDomaine();
		_siteUrlBase();
		_nomDomaine();
		_nomEnsembleDomaine();
		_nomFichierConfig();
		_siteZone();
//		_versionMaven();
//		_versionZookeeper();
//		_prefixePortZookeeper();
//		_portClientZookeeper();
//		_portAdminZookeeper();
//		_versionSolr();
//		_prefixePortSolr();
//		_portSolr();
		_solrUrlComputate();
		_clientSolrComputate();
		_cheminsSource();
		_toutCheminsSource();
		_nomsMethodeTest();
		_siteCrypte();
		_customerProfileId1();
		_customerProfileId2();
		_customerProfileId3();
		_customerProfileId4();
		_customerProfileId5();
		_customerProfileId6();
		_customerProfileId7();
		_customerProfileId8();
		_customerProfileId9();
		_customerProfileId10();
		_siteEcrireMethodes();
		_ecrireApi();
		_ecrireCommentaire();
		_activerSupprime();
		_activerArchive();
		_activerUtilisateurCle();
		_activerSessionId();
		_activerRoleAdmin();
		_activerOpenIdConnect();
	}

	/**
	 * Param1.var.enUS: pattern Param2.var.enUS: text r: motif r.enUS: pattern r:
	 * texte r.enUS: text
	 */
	public String regex(String motif, String texte) {
		String o = null;
		if (motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			if (trouve)
				o = m.group(m.groupCount());
		}
		return o;
	}

	/**
	 * Var.enUS: regexLanguage Param1.var.enUS: languageName Param2.var.enUS:
	 * fieldNameRegex Param3.var.enUS: comment r: nomChamp r.enUS: fieldName r:
	 * valeurChamp r.enUS: fieldValue r: commentaire r.enUS: comment r: langueNom
	 * r.enUS: languageName r: regexLangue r.enUS: regexLanguage
	 */
	protected String regexLangue(String langueNom, String nomChampRegex, String commentaire) throws Exception {
		return regexLangue(langueNom, nomChampRegex, commentaire, null);
	}

	/**
	 * Var.enUS: regexLanguage Param1.var.enUS: languageName Param2.var.enUS:
	 * fieldNameRegex Param3.var.enUS: comment Param4.var.enUS: defaultValue r:
	 * nomChamp r.enUS: fieldName r: valeurChamp r.enUS: fieldValue r: commentaire
	 * r.enUS: comment r: langueNom r.enUS: languageName r: valeurDefaut r.enUS:
	 * defaultValue
	 */
	protected String regexLangue(String langueNom, String nomChampRegex, String commentaire, String valeurDefaut)
			throws Exception {
		String valeurChamp = null;
		if (nomChampRegex != null && commentaire != null) {
			Matcher m = Pattern.compile("^" + nomChampRegex + "(." + langueNom + ")?:\\s*(.*)", Pattern.MULTILINE)
					.matcher(commentaire);
			if (m.find()) {
				valeurChamp = m.group(m.groupCount());
			}
		}
		if (valeurChamp == null)
			valeurChamp = valeurDefaut;
		return valeurChamp;
	}

	/**
	 * Param1.var.enUS: pattern Param2.var.enUS: text Param3.var.enUS: defaultValue
	 * r: motif r.enUS: pattern r: texte r.enUS: text r: valeurDefaut r.enUS:
	 * defaultValue
	 */
	public String regex(String motif, String texte, String valeurDefaut) {
		String o = null;
		if (motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			if (trouve)
				o = m.group(m.groupCount());
		}
		if (StringUtils.isEmpty(o))
			return valeurDefaut;
		else
			return o;
	}

	/**
	 * Param1.var.enUS: pattern Param2.var.enUS: text Param3.var.enUS: group r:
	 * motif r.enUS: pattern r: texte r.enUS: text r: groupe r.enUS: group r: trouve
	 * r.enUS: found
	 */
	public String regex(String motif, String texte, Integer groupe) {
		String o = null;
		if (motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			if (trouve)
				o = m.group(groupe);
		}
		return o;
	}

	/**
	 * Var.enUS: regexFound Param1.var.enUS: pattern Param2.var.enUS: text r: motif
	 * r.enUS: pattern r: texte r.enUS: text r: trouve r.enUS: found
	 */
	public boolean regexTrouve(String motif, String texte) {
		boolean trouve = false;
		if (motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			trouve = m.find();
		}
		return trouve;
	}

	/**
	 * Var.enUS: regexList Param1.var.enUS: pattern Param2.var.enUS: text
	 * Param3.var.enUS: group r: motif r.enUS: pattern r: texte r.enUS: text r:
	 * resultats r.enUS: results r: trouve r.enUS: found
	 */
	public ArrayList<String> regexListe(String motif, String texte) {
		ArrayList<String> resultats = new ArrayList<String>();
		String o = null;
		if (motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			while (trouve) {
				o = m.group(m.groupCount());
				resultats.add(o);
				trouve = m.find();
			}
		}
		return resultats;
	}

	protected ArrayList<String> regexLangueListe(String langueNom, String nomChampRegex, String commentaire)
			throws Exception {
		ArrayList<String> resultats = new ArrayList<String>();
		String o = null;
		if (nomChampRegex != null && commentaire != null) {
			Matcher m = Pattern.compile("^" + nomChampRegex + "(." + langueNom + ")?:\\s*(.*)", Pattern.MULTILINE)
					.matcher(commentaire);
			boolean trouve = m.find();
			while (trouve) {
				o = m.group(m.groupCount());
				resultats.add(o);
				trouve = m.find();
			}
		}
		return resultats;
	}

	/**
	 * Var.enUS: regexReplaceAll Param1.var.enUS: comment Param2.var.enUS:
	 * sourceCode Param3.var.enUS: languageName r: commentaire r.enUS: comment r:
	 * codeSourceLangue r.enUS: sourceCodeLanguage r: codeSource r.enUS: sourceCode
	 * r: langueNom r.enUS: languageName r: trouve2 r.enUS: found2 r: sortie2
	 * r.enUS: end2 r: trouve r.enUS: found r: texteRechercheRemplacement r.enUS:
	 * searchReplacementText r: partisRechercheRemplacement r.enUS:
	 * searchReplacementParts r: texteRecherche r.enUS: searchText r:
	 * texteRemplacement r.enUS: replacementText r: motifRegex r.enUS: patternRegex
	 * r: texteRegex r.enUS: textRegex
	 */
	public String regexRemplacerTout(String commentaire, String codeSource, String langueNom) throws Exception {
		String codeSourceLangue = codeSource;
		if (!StringUtils.isEmpty(commentaire)) {
			Matcher m = Pattern.compile("^[Rr](egex)?:\\s*(.*)((?!\\nr:)[\\s\\S])*?\\nr\\." + langueNom + ":\\s*(.*)",
					Pattern.MULTILINE).matcher(commentaire);
			boolean trouve = m.find();

			while (trouve) {
				String texteRegex = m.group(1);
				String texteRecherche = m.group(2);
				String texteRemplacement = m.group(4);
				String motifRemplacment = StringUtils.replaceEach(texteRemplacement, new String[] { "$" },
						new String[] { "\\$" });
				if (texteRecherche != null && texteRemplacement != null) {
					String motifRegex = null;

					if ("egex".equals(texteRegex))
						motifRegex = texteRecherche;
					else
//						motifRegex = Pattern.quote(texteRecherche);
//						motifRegex = texteRecherche.replaceAll("[\\W]", "\\\\$0");
						motifRegex = StringUtils.replaceEach(texteRecherche,
								new String[] { "<", ">", "{", "}", "[", "]", "(", ")", ".", "^", "$", "|", "*", "?",
										"+", "\\" },
								new String[] { "\\<", "\\>", "\\{", "\\}", "\\[", "\\]", "\\(", "\\)", "\\.", "\\^",
										"\\$", "\\|", "\\*", "\\?", "\\+", "\\\\" });

					Matcher m2 = Pattern.compile(motifRegex, Pattern.MULTILINE).matcher(codeSourceLangue);
					boolean trouve2 = m2.find();
					StringBuffer sortie2 = new StringBuffer();

					while (trouve2) {
						m2.appendReplacement(sortie2, motifRemplacment);
						trouve2 = m2.find();
					}
					m2.appendTail(sortie2);
					codeSourceLangue = sortie2.toString();
				}

				trouve = m.find();
			}
		}
		return codeSourceLangue;
	}

	/**
	 * Param1.var.enUS: values r: valeurs r.enUS: values r: resultat r.enUS: result
	 */
	public String concat(String... valeurs) throws Exception {
		String resultat = Stream.of(valeurs).collect(Collectors.joining());
		return resultat;
	}
}
