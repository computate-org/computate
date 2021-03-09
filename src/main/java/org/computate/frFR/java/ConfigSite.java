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
 * NomCanonique.enUS: org.computate.enUS.java.SiteConfig
 * enUS: Loads the properties in the application config file into specific fields. 
 * frFR: Charge les propriétés dans le fichier de config de l'application dans des champs spécifiques. 
 */ 
public class ConfigSite { 

	protected final Logger log = LoggerFactory.getLogger(getClass());

	Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");
	
	public ConfigSite() {
	}

	public String str_appliNom(String langueNom) {
		if("frFR".equals(langueNom))
			return "appliNom";
		else
			return "appName";
	}

	public String str_appliChemin(String langueNom) {
		if("frFR".equals(langueNom))
			return "appliChemin";
		else
			return "appPath";
	}

	public String str_classeCheminAbsolu(String langueNom) {
		if("frFR".equals(langueNom))
			return "classeCheminAbsolu";
		else
			return "classAbsolutePath";
	}

	public String str_nomDomaine(String langueNom) {
		if("frFR".equals(langueNom))
			return "str_nomDomaine";
		else
			return "domainName";
	}

	public String str_autresLangues(String langueNom) {
		if("frFR".equals(langueNom))
			return "autresLangues";
		else
			return "otherLanguages";
	}

	public String str_siteUrlBase(String langueNom) {
		if("frFR".equals(langueNom))
			return "siteUrlBase";
		else
			return "siteBaseUrl";
	}

	public String str_nomEnsembleDomaine(String langueNom) {
		if("frFR".equals(langueNom))
			return "nomEnsembleDomaine";
		else
			return "domainPackageName";
	}

	public String str_fichier(String langueNom) {
		if("frFR".equals(langueNom))
			return "fichier";
		else
			return "file";
	}

	public String str_nomFichierConfig(String langueNom) {
		if("frFR".equals(langueNom))
			return "nomFichierConfig";
		else
			return "configFileName";
	}

	public String str_solrUrl(String langueNom) {
		if("frFR".equals(langueNom))
			return "solrUrl";
		else
			return "solrUrl";
	}

	public String str_siteCrypte(String langueNom) {
		if("frFR".equals(langueNom))
			return "siteCrypte";
		else
			return "siteEncrypted";
	}

	public String str_siteEcrireMethodes(String langueNom) {
		if("frFR".equals(langueNom))
			return "siteEcrireMethodes";
		else
			return "siteWriteMethods";
	}

	public String str_ecrireApi(String langueNom) {
		if("frFR".equals(langueNom))
			return "ecrireApi";
		else
			return "writeApi";
	}

	public String str_roleLires(String langueNom) {
		if("frFR".equals(langueNom))
			return "roleLires";
		else
			return "roleReads";
	}

	/**
	 * Var.enUS: languageName
	 * enUS: The configured language name for this app. 
	 */
	public String langueNom;
	/**	
	 * Var.enUS: _languageName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _langueNom() throws Exception {
		langueNom = System.getenv("lang");
	} 

	/**	
	 * Var.enUS: appName
	 * frFR: Le nom de l'lappli. 
	 * enUS: The name of the application. 
	 **/ 
	public String appliNom;
	/**	
	 * Var.enUS: _appName
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _appliNom() throws Exception {
		if(appliNom == null)
			appliNom = System.getenv(str_appliNom(langueNom)); 
	}

	/**	
	 * Var.enUS: appPath
	 * frFR: Le chemin vers l'lappli. 
	 * enUS: The path to the application. 
	 * **/
	public String appliChemin;
	/**	
	 * Var.enUS: _appPath
	 * r: appliChemin
	 * r.enUS: appPath
	 **/ 
	protected void _appliChemin() throws Exception {
		if(appliChemin == null)
			appliChemin = System.getenv(str_appliChemin(langueNom)); 
	} 

	/**	
	 * Var.enUS: appPathVertx
	 * frFR: Le chemin vers l'lappli. 
	 * enUS: The path to the application. 
	 * **/
	public String appliCheminVertx;
	/**	
	 * Var.enUS: _appPathVertx
	 * r: appliCheminVertx
	 * r.enUS: appPathVertx
	 **/ 
	protected void _appliCheminVertx() throws Exception {
		if(appliCheminVertx == null)
			appliCheminVertx = System.getenv(str_appliChemin(langueNom) + "Vertx"); 
	} 

	/**
	 * Var.enUS: srcMainJavaPath
	 * enUS: The absolute path to the /src/main/java directory. 
	 */
	public String cheminSrcMainJava;
	/**	
	 * Var.enUS: _srcMainJavaPath
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: appliChemin
	 * r.enUS: appPath
	 **/ 
	protected void _cheminSrcMainJava() throws Exception {
		cheminSrcMainJava = appliChemin + "/src/main/java";
	}

	/**
	 * Var.enUS: srcMainResourcesPath
	 * enUS: The absolute path to the /src/main/resources directory. 
	 */
	public String cheminSrcMainResources;
	/**	
	 * Var.enUS: _srcMainResourcesPath
	 * r: cheminSrcMainResources
	 * r.enUS: srcMainResourcesPath
	 * r: appliChemin
	 * r.enUS: appPath
	 **/ 
	protected void _cheminSrcMainResources() throws Exception {
		cheminSrcMainResources = appliChemin + "/src/main/resources";
	}

	/**
	 * Var.enUS: srcGenJavaPath
	 * enUS: The absolute path to the /src/gen/java directory. 
	 */
	public String cheminSrcGenJava;
	/**	
	 * Var.enUS: _srcGenJavaPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 * r: appliChemin
	 * r.enUS: appPath
	 **/ 
	protected void _cheminSrcGenJava() throws Exception {
		cheminSrcGenJava = appliChemin + "/src/gen/java";
	}

	/**
	 * Var.enUS: configPath
	 * enUS: The absolute path to the app config file. 
	 */
	public String configChemin;
	/**	
	 * Var.enUS: _configPath
	 * r: configChemin
	 * r.enUS: configPath
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: appliNom
	 * r.enUS: appName
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
	 * Var.enUS: configFile
	 * enUS: The File Object for the app config file. 
	 */
	public File fichierConfig;
	/**	
	 * Var.enUS: _configFile
	 * r: fichierConfig
	 * r.enUS: configFile
	 * r: configChemin
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
	 * r: fichierConfig
	 * r.enUS: configFile
	 **/ 
	protected void _config() throws Exception {
		config = configurations.ini(fichierConfig);
	}

	/**
	 * Var.enUS: languageActualName
	 * enUS: The actual language of the code. 
	 * enUS: If the languageName is configured as "tout", then the languageActualName would be "frFR". 
	 */
	public String langueNomActuel;
	/**	
	 * Var.enUS: _languageActualName
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: langueNom
	 * r.enUS: languageName
	 **/ 
	protected void _langueNomActuel() throws Exception {
//		if(StringUtils.equals(langueNom, "tout"))
//			langueNomActuel = "frFR";
//		else
			langueNomActuel = langueNom;
	} 

	/**
	 * Var.enUS: otherLanguages
	 * enUS: Other languages supported by this app besides the actual language name. 
	 */
	public String[] autresLangues;
	/**	
	 * Var.enUS: _otherLanguages
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: toutesLangues
	 * r.enUS: allLanguages
	 * r: langueNom
	 * r.enUS: languageName
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _autresLangues() throws Exception {
		autresLangues = config.getStringArray(StringUtils.replace(appliNom, ".", "..") + "." + str_autresLangues(langueNom));
	}

	/**
	 * Var.enUS: allLanguages
	 * enUS: All language names supported in this application. 
	 */
	public String[] toutesLangues;

	/**
	 * Var.enUS:classOtherLanguages
	 * enUS: Other language names supported in this application. 
	 */
	public String[] classeAutresLangues;

	/**	
	 * Var.enUS: _allLanguages
	 * r: toutesLangues
	 * r.enUS: allLanguages
	 * r: appliNom
	 * r.enUS: appName
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: langueNom
	 * r.enUS: languageName
	 **/ 
	protected void _toutesLangues() throws Exception {
		toutesLangues = ArrayUtils.add(ArrayUtils.addAll(autresLangues), langueNom);
	}

	/**
	 * Var.enUS: languageIndexed
	 * enUS: True if the languageName is an actual locale that can be indexed. 
	 * enUS: If the languageName is configured as "tout", then that language would not be indexed. 
	 */
	public Boolean langueIndexe;
	/**	
	 * Var.enUS: _languageIndexed
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: toutesLangues
	 * r.enUS: allLanguages
	 * r: langueNom
	 * r.enUS: languageName
	 **/ 
	protected void _langueIndexe() throws Exception {
		langueIndexe = ArrayUtils.contains(toutesLangues, langueNom);
	}

	/**
	 * Var.enUS: domainName
	 * enUS: The domain name of the website where this will be deployed (like "example.com"). 
	 */
	public String nomDomaine;
	/**	
	 * Var.enUS: _domainName
	 * r: nomDomaine
	 * r.enUS: domainName
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _nomDomaine() throws Exception {
		nomDomaine = config.getString(StringUtils.replace(appliNom, ".", "..") + "." + str_nomDomaine(langueNom));
	}

	/**
	 * Var.enUS: siteBaseUrl
	 * enUS: The base URL to the site when deployed. 
	 */
	public String siteUrlBase;
	/**	
	 * Var.enUS: _siteBaseUrl
	 * r: appliNom
	 * r.enUS: appName
	 * r: nomDomaine
	 * r.enUS: domainName
	 * r: siteUrlBase
	 * r.enUS: siteBaseUrl
	 **/  
	protected void _siteUrlBase() throws Exception {
		siteUrlBase = config.getString(StringUtils.replace(appliNom, ".", "..") + "." + str_siteUrlBase(langueNom));
	}
	
	/**
	 * Var.enUS: domainPackageName
	 * enUS: The Java package name for the domain (example.com would have a package name of "com.example"). 
	 */
	public String nomEnsembleDomaine;
	/**	
	 * Var.enUS: _domainPackageName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: nomDomaine
	 * r.enUS: domainName
	 * r: partis
	 * r.enUS: parts
	 * r: appliNom
	 * r.enUS: appName
	 **/  
	protected void _nomEnsembleDomaine() throws Exception {
		nomEnsembleDomaine = config.getString(StringUtils.replace(appliNom, ".", "..") + "." + str_nomEnsembleDomaine(langueNom));
		if(StringUtils.isEmpty(nomEnsembleDomaine)) {
			String[] partis = StringUtils.split(nomDomaine, ".");
			ArrayUtils.reverse(partis);
			nomEnsembleDomaine = StringUtils.join(partis, ".");
		}
	}

	/**
	 * Var.enUS: configFileName
	 * enUS: The name of the config file which defaults to the appName followed by ".config". 
	 */
	public String nomFichierConfig;
	/**	
	 * Var.enUS: _configFileName
	 * r: nomFichierConfig
	 * r.enUS: configFileName
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _nomFichierConfig() throws Exception {
		nomFichierConfig = config.getString(StringUtils.replace(appliNom, ".", "..") + "." + str_nomFichierConfig(langueNom), appliNom + ".config");
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
	 * Var.enUS: solrUrlComputate
	 * enUS: The Solr web URL for the "computate" index. 
	 */ 
	public String solrUrlComputate;
	/**	
	 * Var.enUS: _solrUrlComputate
	 * r: solrUrlComputate
	 * r.enUS: solrUrlComputate
	 * r: solrUrl
	 * r.enUS: solrUrl
	 * r: portSolr
	 * r.enUS: solrPort
	 **/ 
	protected void _solrUrlComputate() throws Exception {
//		solrUrlComputate = config.getString("solr.solrUrl", "http://localhost:" + portSolr + "/solr/computate");
		solrUrlComputate = config.getString(StringUtils.replace(appliNom, ".", "..") + "." + str_solrUrl(langueNom) + "Computate");
	}

	/**
	 * Var.enUS: solrClientComputate
	 * enUS: The Solrj client for the "computate" index. 
	 */ 
	public SolrClient clientSolrComputate;
	/**	
	 * Var.enUS: _solrClientComputate
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: solrUrlComputate
	 * r.enUS: solrUrlComputate
	 **/ 
	protected void _clientSolrComputate() throws Exception {
		clientSolrComputate = new HttpSolrClient.Builder(solrUrlComputate).build();
	}

	/**
	 * Var.enUS: sourcePaths
	 * enUS: The absolute paths to source code directories in the app to watch for changes. 
	 */ 
	public ArrayList<String> cheminsSource = new ArrayList<String>();
	/**	
	 * Var.enUS: _sourcePaths
	 * r: cheminsSource
	 * r.enUS: sourcePaths
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 **/ 
	protected void _cheminsSource() throws Exception {
		cheminsSource.add(cheminSrcMainJava);
		cheminsSource.add(cheminSrcGenJava);
	}

	/**
	 * Var.enUS: allSourcePaths
	 * enUS: The absolute paths to source code directories in all apps to watch for changes. 
	 */ 
	public ArrayList<String> toutCheminsSource = new ArrayList<String>();
	/**	
	 * Var.enUS: _allSourcePaths
	 * r: toutCheminsSource
	 * r.enUS: allSourcePaths
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 **/ 
	protected void _toutCheminsSource() throws Exception {
		toutCheminsSource.add(cheminSrcMainJava);
		toutCheminsSource.add(cheminSrcGenJava);
	}

	/**
	 * Var.enUS: testMethodNames
	 * enUS: The names of methods that will be tested when a source file is updated. 
	 */ 
	public ArrayList<String> nomsMethodeTest = new ArrayList<String>();
	/**	
	 * Var.enUS: _testMethodNames
	 **/ 
	protected void _nomsMethodeTest() throws Exception {
	}

	/**
	 * Var.enUS: siteEncrypted
	 * enUS: True if the data for the site should be encrypted. 
	 */
	public Boolean siteCrypte;
	/**	
	 * Var.enUS: _siteEncrypted
	 * r: siteCrypte
	 * r.enUS: siteEncrypted
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _siteCrypte() throws Exception {
		siteCrypte = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + str_siteCrypte(langueNom), false);
	}

	public Boolean customerProfileId1;
	protected void _customerProfileId1() throws Exception {
		customerProfileId1 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId1", false);
	}

	public Boolean customerProfileId2;
	protected void _customerProfileId2() throws Exception {
		customerProfileId2 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId2", false);
	}

	public Boolean customerProfileId3;
	protected void _customerProfileId3() throws Exception {
		customerProfileId3 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId3", false);
	}

	public Boolean customerProfileId4;
	protected void _customerProfileId4() throws Exception {
		customerProfileId4 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId4", false);
	}

	public Boolean customerProfileId5;
	protected void _customerProfileId5() throws Exception {
		customerProfileId5 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId5", false);
	}

	public Boolean customerProfileId6;
	protected void _customerProfileId6() throws Exception {
		customerProfileId6 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId6", false);
	}

	public Boolean customerProfileId7;
	protected void _customerProfileId7() throws Exception {
		customerProfileId7 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId7", false);
	}

	public Boolean customerProfileId8;
	protected void _customerProfileId8() throws Exception {
		customerProfileId8 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId8", false);
	}

	public Boolean customerProfileId9;
	protected void _customerProfileId9() throws Exception {
		customerProfileId9 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId9", false);
	}

	public Boolean customerProfileId10;
	protected void _customerProfileId10() throws Exception {
		customerProfileId10 = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "customerProfileId10", false);
	}

	/**
	 * Var.enUS: siteWriteMethods
	 */ 
	public ArrayList<String> siteEcrireMethodes = new ArrayList<String>();
	/**	
	 * Var.enUS: _siteWriteMethods
	 * r: siteEcrireMethodes
	 * r.enUS: siteWriteMethods
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _siteEcrireMethodes() throws Exception {
		List<String> o = config.getList(String.class, StringUtils.replace(appliNom, ".", "..") + "." + str_siteEcrireMethodes(langueNom));
		if(o != null)
			siteEcrireMethodes.addAll(o);
	}

	/**
	 * Var.enUS: writeApi
	 */
	public Boolean ecrireApi;
	/**	
	 * Var.enUS: _writeApi
	 * r: ecrireApi
	 * r.enUS: writeApi
	 * r: appliNom
	 * r.enUS: appName
	 **/  
	protected void _ecrireApi() throws Exception {
		ecrireApi = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + str_ecrireApi(langueNom), true);
	}

	public Boolean sqlTables;
	/**	
	 * Var.enUS: _sqlTables
	 **/  
	protected void _sqlTables() throws Exception {
		sqlTables = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + "." + "sqlTables", false);
	}

	/**	
	 * Var.enUS: initSiteConfig
	 * r: fichierConfig
	 * r.enUS: configFile
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: toutesLangues
	 * r.enUS: allLanguages
	 * r: langueNom
	 * r.enUS: languageName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: nomDomaine
	 * r.enUS: domainName
	 * r: nomFichierConfig
	 * r.enUS: configFileName
	 * r: configChemin
	 * r.enUS: configPath
	 * r: configChemin
	 * r.enUS: configPath
	 * r: appliNom
	 * r.enUS: appName
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: nomFichierConfig
	 * r.enUS: configFileName
	 * r: versionMaven
	 * r.enUS: mavenVersion
	 * r: versionZookeeper
	 * r.enUS: zookeeperVersion
	 * r: prefixePortZookeeper
	 * r.enUS: zookeeperPortPrefix
	 * r: portAdminZookeeper
	 * r.enUS: zookeeperAdminPort
	 * r: portClientZookeeper
	 * r.enUS: zookeeperClientPort
	 * r: versionSolr
	 * r.enUS: solrVersion
	 * r: prefixePortSolr
	 * r.enUS: solrPortPrefix
	 * r: portSolr
	 * r.enUS: solrPort
	 * r: solrUrlComputate
	 * r.enUS: solrUrlComputate
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: cheminsSource
	 * r.enUS: sourcePaths
	 * r: toutCheminsSource
	 * r.enUS: allSourcePaths
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: cheminSrcMainResources
	 * r.enUS: srcMainResourcesPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 * r: nomsMethodeTest
	 * r.enUS: testMethodNames
	 * r: siteCrypte
	 * r.enUS: siteEncrypted
	 * r: siteEcrireMethodes
	 * r.enUS: siteWriteMethods
	 * r: ecrireApi
	 * r.enUS: writeApi
	 * r: siteUrlBase
	 * r.enUS: siteBaseUrl
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
		_sqlTables();
	}

	/**
	 * Param1.var.enUS: pattern
	 * Param2.var.enUS: text
	 * r: motif
	 * r.enUS: pattern
	 * r: texte
	 * r.enUS: text
	 */
	public String regex(String motif, String texte) {
		String o = null;
		if(motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			if(trouve)
				o = m.group(m.groupCount());
		}
		return o;
	}
	
	/**
	 * Var.enUS: regexLanguage
	 * Param1.var.enUS: languageName
	 * Param2.var.enUS: fieldNameRegex
	 * Param3.var.enUS: comment
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 * r: commentaire
	 * r.enUS: comment
	 * r: langueNom
	 * r.enUS: languageName
	 * r: regexLangue
	 * r.enUS: regexLanguage
	 */
	protected String regexLangue(String langueNom, String nomChampRegex, String commentaire) throws Exception {
		return regexLangue(langueNom, nomChampRegex, commentaire, null);
	}
	
	/**
	 * Var.enUS: regexLanguage
	 * Param1.var.enUS: languageName
	 * Param2.var.enUS: fieldNameRegex
	 * Param3.var.enUS: comment
	 * Param4.var.enUS: defaultValue
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: valeurChamp
	 * r.enUS: fieldValue
	 * r: commentaire
	 * r.enUS: comment
	 * r: langueNom
	 * r.enUS: languageName
	 * r: valeurDefaut
	 * r.enUS: defaultValue
	 */
	protected String regexLangue(String langueNom, String nomChampRegex, String commentaire, String valeurDefaut) throws Exception {
		String valeurChamp = null;
		if(nomChampRegex != null && commentaire != null) {
			Matcher m = Pattern.compile("^" + nomChampRegex + "(." + langueNom + ")?:\\s*(.*)", Pattern.MULTILINE).matcher(commentaire);
			if(m.find()) {
				valeurChamp = m.group(m.groupCount());
			}
		}
		if(valeurChamp == null)
			valeurChamp = valeurDefaut;
		return valeurChamp;
	}

	/**
	 * Param1.var.enUS: pattern
	 * Param2.var.enUS: text
	 * Param3.var.enUS: defaultValue
	 * r: motif
	 * r.enUS: pattern
	 * r: texte
	 * r.enUS: text
	 * r: valeurDefaut
	 * r.enUS: defaultValue
	 */
	public String regex(String motif, String texte, String valeurDefaut) {
		String o = null;
		if(motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			if(trouve)
				o = m.group(m.groupCount());
		}
		if(StringUtils.isEmpty(o))
			return valeurDefaut;
		else
			return o;
	}

	/** 
	 * Param1.var.enUS: pattern
	 * Param2.var.enUS: text
	 * Param3.var.enUS: group
	 * r: motif
	 * r.enUS: pattern
	 * r: texte
	 * r.enUS: text
	 * r: groupe
	 * r.enUS: group
	 * r: trouve
	 * r.enUS: found
	 */
	public String regex(String motif, String texte, Integer groupe) {
		String o = null;
		if(motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			if(trouve)
				o = m.group(groupe);
		}
		return o;
	}  

	/** 
	 * Var.enUS: regexFound
	 * Param1.var.enUS: pattern
	 * Param2.var.enUS: text
	 * r: motif
	 * r.enUS: pattern
	 * r: texte
	 * r.enUS: text
	 * r: trouve
	 * r.enUS: found
	 */
	public boolean regexTrouve(String motif, String texte) {
		boolean trouve = false;
		if(motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			trouve = m.find();
		}
		return trouve;
	}  

	/**
	 * Var.enUS: regexList
	 * Param1.var.enUS: pattern
	 * Param2.var.enUS: text
	 * Param3.var.enUS: group
	 * r: motif
	 * r.enUS: pattern
	 * r: texte
	 * r.enUS: text
	 * r: resultats
	 * r.enUS: results
	 * r: trouve
	 * r.enUS: found
	 */
	public ArrayList<String> regexListe(String motif, String texte) {
		ArrayList<String> resultats = new ArrayList<String>();
		String o = null;
		if(motif != null && texte != null) {
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			while(trouve) {
				o = m.group(m.groupCount());
				resultats.add(o); 
				trouve = m.find();
			}
		}
		return resultats;
	}    

	protected ArrayList<String> regexLangueListe(String langueNom, String nomChampRegex, String commentaire) throws Exception {
		ArrayList<String> resultats = new ArrayList<String>();
		String o = null;
		if(nomChampRegex != null && commentaire != null) {
			Matcher m = Pattern.compile("^" + nomChampRegex + "(." + langueNom + ")?:\\s*(.*)", Pattern.MULTILINE).matcher(commentaire);
			boolean trouve = m.find();
			while(trouve) {
				o = m.group(m.groupCount());
				resultats.add(o); 
				trouve = m.find();
			}
		}
		return resultats;
	}

	/**
	 * Var.enUS: regexReplaceAll
	 * Param1.var.enUS: comment
	 * Param2.var.enUS: sourceCode
	 * Param3.var.enUS: languageName
	 * r: commentaire
	 * r.enUS: comment
	 * r: codeSourceLangue
	 * r.enUS: sourceCodeLanguage
	 * r: codeSource
	 * r.enUS: sourceCode
	 * r: langueNom
	 * r.enUS: languageName
	 * r: trouve2
	 * r.enUS: found2
	 * r: sortie2
	 * r.enUS: end2
	 * r: trouve
	 * r.enUS: found
	 * r: texteRechercheRemplacement
	 * r.enUS: searchReplacementText
	 * r: partisRechercheRemplacement
	 * r.enUS: searchReplacementParts
	 * r: texteRecherche
	 * r.enUS: searchText
	 * r: texteRemplacement
	 * r.enUS: replacementText
	 * r: motifRegex
	 * r.enUS: patternRegex
	 * r: texteRegex
	 * r.enUS: textRegex
	 */  
	public String regexRemplacerTout(String commentaire, String codeSource, String langueNom) throws Exception {
		String codeSourceLangue = codeSource;
		if(!StringUtils.isEmpty(commentaire)) {
			Matcher m = Pattern.compile("^[Rr](egex)?:\\s*(.*)((?!\\nr:)[\\s\\S])*?\\nr\\." + langueNom + ":\\s*(.*)", Pattern.MULTILINE).matcher(commentaire);
			boolean trouve = m.find();
			
			while(trouve) {
				String texteRegex = m.group(1);
				String texteRecherche = m.group(2);
				String texteRemplacement = m.group(4);
				String motifRemplacment = StringUtils.replaceEach(texteRemplacement
						, new String[] {"$"}
						, new String[] {"\\$"}
						);
				if(texteRecherche != null && texteRemplacement != null) {
					String motifRegex = null;

					if("egex".equals(texteRegex))
						motifRegex = texteRecherche;
					else
//						motifRegex = Pattern.quote(texteRecherche);
//						motifRegex = texteRecherche.replaceAll("[\\W]", "\\\\$0");
						motifRegex = StringUtils.replaceEach(texteRecherche
								, new String[] {"<",">","{","}","[","]","(",")",".","^","$","|","*","?","+","\\"}
								, new String[] {"\\<","\\>","\\{","\\}","\\[","\\]","\\(","\\)","\\.","\\^","\\$","\\|","\\*","\\?","\\+","\\\\"}
								);

					Matcher m2 = Pattern.compile(motifRegex, Pattern.MULTILINE).matcher(codeSourceLangue);
					boolean trouve2 = m2.find();
					StringBuffer sortie2 = new StringBuffer();
					
					while(trouve2) {
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
	 * Param1.var.enUS: values
	 * r: valeurs
	 * r.enUS: values
	 * r: resultat
	 * r.enUS: result
	 */
	public String concat(String...valeurs) throws Exception { 
		String resultat = Stream.of(valeurs).collect(Collectors.joining());
		return resultat;
	}  
}
