package org.computate.frFR.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.configuration2.YAMLConfiguration;
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


	/**
	 * enUS: The Apache Commons Configurations object for reading config files.
	 */
	public static Configurations configurations;

	protected void _configurations() throws Exception {
		configurations = new Configurations();
	}

	public String langueNomGlobale;
	protected void _langueNomGlobale() throws Exception {
		langueNomGlobale = System.getenv("SITE_LANG");
	}

	public String appComputate;
	protected void _appComputate() throws Exception {
		appComputate = System.getenv("COMPUTATE_SRC");
	}

	public YAMLConfiguration langueConfigGlobale;
	protected void _langueConfigGlobale() throws Exception {
		langueConfigGlobale = configurations.fileBased(YAMLConfiguration.class, String.format("%s/src/main/resources/i18n/i18n_%s.yml", appComputate, langueNomGlobale));
	}

	/**
	 * Var.enUS: siteName frFR: Le nom de l'lappli. enUS: The name of the
	 * application.
	 **/
	public String siteNom;

	/**
	 * Var.enUS: _siteName r: siteNom r.enUS: siteName
	 **/
	protected void _siteNom() throws Exception {
		if (siteNom == null)
			siteNom = System.getenv(langueConfigGlobale.getString(ConfigCles.var_SITE_NOM));
	}

	/**
	 * Var.enUS: sitePath frFR: Le chemin vers l'lappli. enUS: The path to the
	 * application.
	 **/
	public String siteChemin;

	/**
	 * Var.enUS: _sitePath r: siteChemin r.enUS: sitePath
	 **/
	protected void _siteChemin() throws Exception {
		if (siteChemin == null)
			siteChemin = System.getenv(langueConfigGlobale.getString(ConfigCles.var_SITE_CHEMIN));
	}

	/**
	 * Var.enUS: sitePathVertx frFR: Le chemin vers l'lappli. enUS: The path to the
	 * application.
	 **/
	public String siteCheminVertx;

	/**
	 * Var.enUS: _sitePathVertx r: siteCheminVertx r.enUS: sitePathVertx
	 **/
	protected void _siteCheminVertx() throws Exception {
		if (siteCheminVertx == null)
			siteCheminVertx = System.getenv(langueConfigGlobale.getString(ConfigCles.var_SITE_CHEMIN_VERTX));
	}

	/**
	 * Var.enUS: srcMainJavaPath enUS: The absolute path to the /src/main/java
	 * directory.
	 */
	public String cheminSrcMainJava;

	/**
	 * Var.enUS: _srcMainJavaPath r: cheminSrcMainJava r.enUS: srcMainJavaPath r:
	 * siteChemin r.enUS: sitePath
	 **/
	protected void _cheminSrcMainJava() throws Exception {
		cheminSrcMainJava = siteChemin + "/src/main/java";
	}

	/**
	 * Var.enUS: srcMainResourcesPath enUS: The absolute path to the
	 * /src/main/resources directory.
	 */
	public String cheminSrcMainResources;

	/**
	 * Var.enUS: _srcMainResourcesPath r: cheminSrcMainResources r.enUS:
	 * srcMainResourcesPath r: siteChemin r.enUS: sitePath
	 **/
	protected void _cheminSrcMainResources() throws Exception {
		cheminSrcMainResources = siteChemin + "/src/main/resources";
	}

	/**
	 * Var.enUS: srcGenJavaPath enUS: The absolute path to the /src/gen/java
	 * directory.
	 */
	public String cheminSrcGenJava;

	/**
	 * Var.enUS: _srcGenJavaPath r: cheminSrcGenJava r.enUS: srcGenJavaPath r:
	 * siteChemin r.enUS: sitePath
	 **/
	protected void _cheminSrcGenJava() throws Exception {
		cheminSrcGenJava = siteChemin + "/src/gen/java";
	}

	/**
	 * Var.enUS: configPath enUS: The absolute path to the app config file.
	 */
	public String configChemin;

	/**
	 * Var.enUS: _configPath r: configChemin r.enUS: configPath r: siteChemin
	 * r.enUS: sitePath r: siteNom r.enUS: siteName
	 **/
	protected void _configChemin() throws Exception {
		configChemin = siteChemin + "/config/" + siteNom + ".yml";
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
//	 * r.enUS: siteNom
//	 * siteName
//	 * r.enUS: siteChemin
//	 * sitePath
//	 * r.enUS: nomFichierConfig
//	 * configFileName
//	 **/ 
//	protected void _configChemin() throws Exception {
//		configChemin = config.getString(StringUtils.replace(siteNom, ".", "..") + ".configChemin", siteChemin + "/config/" + nomFichierConfig);
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
	 * The INI Configuration Object for the config file.
	 */
	public YAMLConfiguration config;

	/**
	 * r: fichierConfig r.enUS: configFile
	 **/
	protected void _config() throws Exception {
		config = configurations.fileBased(YAMLConfiguration.class, fichierConfig);
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
		langueNomActuel = langueNomGlobale;
	}

	/**
	 * Var.enUS: otherLanguages enUS: Other languages supported by this app besides
	 * the actual language name.
	 */
	public String[] autresLangues;

	/**
	 * Var.enUS: _otherLanguages r: autresLangues r.enUS: otherLanguages r:
	 * toutesLangues r.enUS: allLanguages r: langueNom r.enUS: languageName r:
	 * siteNom r.enUS: siteName
	 **/
	protected void _autresLangues() throws Exception {
		autresLangues = config
				.getStringArray(langueConfigGlobale.getString(ConfigCles.var_AUTRES_LANGUES));
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
	 * Var.enUS: _allLanguages r: toutesLangues r.enUS: allLanguages r: siteNom
	 * r.enUS: siteName r: autresLangues r.enUS: otherLanguages r: langueNom r.enUS:
	 * languageName
	 **/
	protected void _toutesLangues() throws Exception {
		toutesLangues = ArrayUtils.add(ArrayUtils.addAll(autresLangues), langueNomGlobale);
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
		langueIndexe = ArrayUtils.contains(toutesLangues, langueNomGlobale);
	}

	/**
	 * Var.enUS: domainName enUS: The domain name of the website where this will be
	 * deployed (like "example.com").
	 */
	public String nomDomaine;

	/**
	 * Var.enUS: _domainName r: nomDomaine r.enUS: domainName r: siteNom r.enUS:
	 * siteName
	 **/
	protected void _nomDomaine() throws Exception {
		nomDomaine = config.getString(langueConfigGlobale.getString(ConfigCles.var_NOM_DOMAINE));
	}

	/**
	 * Var.enUS: siteBaseUrl enUS: The base URL to the site when deployed.
	 */
	public String siteUrlBase;

	/**
	 * Var.enUS: _siteBaseUrl r: siteNom r.enUS: siteName r: nomDomaine r.enUS:
	 * domainName r: siteUrlBase r.enUS: siteBaseUrl
	 **/
	protected void _siteUrlBase() throws Exception {
		siteUrlBase = config.getString(langueConfigGlobale.getString(ConfigCles.var_SITE_URL_BASE));
	}

	/**
	 */
	public String computateEnsembleRecherchePrefixe;

	/**
	 **/
	protected void _computateEnsembleRecherchePrefixe() throws Exception {
		computateEnsembleRecherchePrefixe = config.getString(langueConfigGlobale.getString(ConfigCles.var_COMPUTATE_ENSEMBLE_RECHERCHE_PREFIXE), "org.computate.search org.computate.vertx ");
	}

	/**
	 * Var.enUS: domainPackageName enUS: The Java package name for the domain
	 * (example.com would have a package name of "com.example").
	 */
	public String nomEnsembleDomaine;

	/**
	 * Var.enUS: _domainPackageName r: nomEnsembleDomaine r.enUS: domainPackageName
	 * r: nomDomaine r.enUS: domainName r: partis r.enUS: parts r: siteNom r.enUS:
	 * siteName
	 **/
	protected void _nomEnsembleDomaine() throws Exception {
		nomEnsembleDomaine = config
				.getString(langueConfigGlobale.getString(ConfigCles.var_NOM_ENSEMBLE_DOMAINE));
		if (StringUtils.isEmpty(nomEnsembleDomaine)) {
			String[] partis = StringUtils.split(nomDomaine, ".");
			ArrayUtils.reverse(partis);
			nomEnsembleDomaine = StringUtils.join(partis, ".");
		}
	}

	/**
	 * Var.enUS: configFileName enUS: The name of the config file which defaults to
	 * the siteName followed by ".yml".
	 */
	public String nomFichierConfig;

	/**
	 * Var.enUS: _configFileName r: nomFichierConfig r.enUS: configFileName r:
	 * siteNom r.enUS: siteName
	 **/
	protected void _nomFichierConfig() throws Exception {
		nomFichierConfig = config.getString(
				langueConfigGlobale.getString(ConfigCles.var_NOM_FICHIER_CONFIG), siteNom + ".yml");
	}

	public String siteZone;

	protected void _siteZone() throws Exception {
		siteZone = config.getString(
				langueConfigGlobale.getString(ConfigCles.var_SITE_ZONE));
	}

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
				.getString(langueConfigGlobale.getString(ConfigCles.var_SOLR_URL_COMPUTATE));
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
	 * Var.enUS: _siteEncrypted r: siteCrypte r.enUS: siteEncrypted r: siteNom
	 * r.enUS: siteName
	 **/
	protected void _siteCrypte() throws Exception {
		siteCrypte = config.getBoolean(langueConfigGlobale.getString(ConfigCles.var_SITE_CRYPTE),
				false);
	}

	public Boolean customerProfileId1;

	protected void _customerProfileId1() throws Exception {
		customerProfileId1 = config.getBoolean("customerProfileId1",
				false);
	}

	public Boolean customerProfileId2;

	protected void _customerProfileId2() throws Exception {
		customerProfileId2 = config.getBoolean("customerProfileId2",
				false);
	}

	public Boolean customerProfileId3;

	protected void _customerProfileId3() throws Exception {
		customerProfileId3 = config.getBoolean("customerProfileId3",
				false);
	}

	public Boolean customerProfileId4;

	protected void _customerProfileId4() throws Exception {
		customerProfileId4 = config.getBoolean("customerProfileId4",
				false);
	}

	public Boolean customerProfileId5;

	protected void _customerProfileId5() throws Exception {
		customerProfileId5 = config.getBoolean("customerProfileId5",
				false);
	}

	public Boolean customerProfileId6;

	protected void _customerProfileId6() throws Exception {
		customerProfileId6 = config.getBoolean("customerProfileId6",
				false);
	}

	public Boolean customerProfileId7;

	protected void _customerProfileId7() throws Exception {
		customerProfileId7 = config.getBoolean("customerProfileId7",
				false);
	}

	public Boolean customerProfileId8;

	protected void _customerProfileId8() throws Exception {
		customerProfileId8 = config.getBoolean("customerProfileId8",
				false);
	}

	public Boolean customerProfileId9;

	protected void _customerProfileId9() throws Exception {
		customerProfileId9 = config.getBoolean("customerProfileId9",
				false);
	}

	public Boolean customerProfileId10;

	protected void _customerProfileId10() throws Exception {
		customerProfileId10 = config.getBoolean("customerProfileId10",
				false);
	}

	/**
	 * Var.enUS: siteWriteMethods
	 */
	public ArrayList<String> siteEcrireMethodes = new ArrayList<String>();

	/**
	 * Var.enUS: _siteWriteMethods r: siteEcrireMethodes r.enUS: siteWriteMethods r:
	 * siteNom r.enUS: siteName
	 **/
	protected void _siteEcrireMethodes() throws Exception {
		List<String> o = config.getList(String.class,
				langueConfigGlobale.getString(ConfigCles.var_SITE_ECRIRE_METHODES));
		if (o != null)
			siteEcrireMethodes.addAll(o);
	}

	public ArrayList<String> authRolesAdmin = new ArrayList<String>();

	protected void _authRolesAdmin() throws Exception {
		List<String> o = config.getList(String.class, langueConfigGlobale.getString(ConfigCles.var_AUTH_ROLES_ADMIN));
		if (o != null)
			authRolesAdmin.addAll(o);
	}

	/**
	 * Var.enUS: writeApi
	 */
	public Boolean ecrireApi;

	/**
	 * Var.enUS: _writeApi r: ecrireApi r.enUS: writeApi r: siteNom r.enUS: siteName
	 **/
	protected void _ecrireApi() throws Exception {
		ecrireApi = config.getBoolean(langueConfigGlobale.getString(ConfigCles.var_ECRIRE_API), true);
	}

	public Boolean ecrireCommentaire;

	protected void _ecrireCommentaire() throws Exception {
		ecrireCommentaire = config
				.getBoolean(langueConfigGlobale.getString(ConfigCles.var_ECRIRE_COMMENTAIRE), true);
	}

	public Boolean activerLog;

	protected void _activerLog() throws Exception {
		activerLog = config.getBoolean( langueConfigGlobale.getString(ConfigCles.var_ACTIVER_LOG), true);
	}

	public Boolean activerSupprime;

	protected void _activerSupprime() throws Exception {
		activerSupprime = config.getBoolean( langueConfigGlobale.getString(ConfigCles.var_ACTIVER_SUPPRIME), true);
	}

	public Boolean activerArchive;

	protected void _activerArchive() throws Exception {
		activerArchive = config.getBoolean(
				langueConfigGlobale.getString(ConfigCles.var_ACTIVER_ARCHIVE), true);
	}

	public Boolean activerUtilisateurCle;

	protected void _activerUtilisateurCle() throws Exception {
		activerUtilisateurCle = config.getBoolean(
				langueConfigGlobale.getString(ConfigCles.var_ACTIVER_UTILISATEUR_CLE),
				true);
	}

	public Boolean activerSessionId;

	protected void _activerSessionId() throws Exception {
		activerSessionId = config.getBoolean(
				langueConfigGlobale.getString(ConfigCles.var_ACTIVER_SESSION_ID),
				true);
	}

	public Boolean activerRoleAdmin;

	protected void _activerRoleAdmin() throws Exception {
		activerRoleAdmin = config.getBoolean(langueConfigGlobale.getString(ConfigCles.var_ACTIVER_ROLE_ADMIN), true);
	}

	public Boolean activerOpenIdConnect;

	protected void _activerOpenIdConnect() throws Exception {
		activerOpenIdConnect = config.getBoolean(
				langueConfigGlobale.getString(ConfigCles.var_ACTIVER_OPENID_CONNECT), true);
	}

	public Boolean activerQuarkus;

	protected void _activerQuarkus() throws Exception {
		activerQuarkus = config.getBoolean(
				langueConfigGlobale.getString(ConfigCles.var_ACTIVER_QUARKUS), false);
	}

	public Boolean activerVertx;

	protected void _activerVertx() throws Exception {
		activerVertx = config.getBoolean(
				langueConfigGlobale.getString(ConfigCles.var_ACTIVER_VERTX), !activerQuarkus);
	}

	/**
	 * Var.enUS: initSiteConfig r: fichierConfig r.enUS: configFile r:
	 * langueNomActuel r.enUS: languageActualName r: langueIndexe r.enUS:
	 * languageIndexed r: autresLangues r.enUS: otherLanguages r: toutesLangues
	 * r.enUS: allLanguages r: langueNom r.enUS: languageName r: nomEnsembleDomaine
	 * r.enUS: domainPackageName r: nomDomaine r.enUS: domainName r:
	 * nomFichierConfig r.enUS: configFileName r: configChemin r.enUS: configPath r:
	 * configChemin r.enUS: configPath r: siteNom r.enUS: siteName r: siteChemin
	 * r.enUS: sitePath r: nomFichierConfig r.enUS: configFileName r: versionMaven
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
		_configurations();
		_langueNomGlobale();
		_appComputate();
		_langueConfigGlobale();
		_siteNom();
		_siteChemin();
		_siteCheminVertx();
		_cheminSrcMainJava();
		_cheminSrcMainResources();
		_cheminSrcGenJava();
		_configChemin();
		_fichierConfig();
		_config();
		_langueNomActuel();
		_autresLangues();
		_toutesLangues();
		_langueIndexe();
		_nomDomaine();
		_siteUrlBase();
		_computateEnsembleRecherchePrefixe();
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
		_authRolesAdmin();
		_ecrireApi();
		_ecrireCommentaire();
		_activerLog();
		_activerSupprime();
		_activerArchive();
		_activerUtilisateurCle();
		_activerSessionId();
		_activerRoleAdmin();
		_activerOpenIdConnect();
		_activerQuarkus();
		_activerVertx();
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
