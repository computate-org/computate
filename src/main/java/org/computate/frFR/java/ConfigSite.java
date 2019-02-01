package org.computate.frFR.java;    

import java.io.File;
import java.util.ArrayList;
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

/**
 * NomCanonique.enUS: org.computate.enUS.java.SiteConfig
 * enUS: Loads the properties in the application config file into specific fields. 
 * frFR: Charge les propriétés dans le fichier de config de l'application dans des champs spécifiques. 
 */ 
public class ConfigSite { 
	
	public ConfigSite() {
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
			appliNom = System.getenv("appliNom"); 
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
			appliChemin = System.getenv("appliChemin"); 
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
		langueNom = config.getString(StringUtils.replace(appliNom, ".", "..") + ".langueNom");
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
		autresLangues = config.getStringArray(StringUtils.replace(appliNom, ".", "..") + ".autresLangues");
	}

	/**
	 * Var.enUS: allLanguages
	 * enUS: All language names supported in this application. 
	 */
	public String[] toutesLangues;
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
		nomDomaine = config.getString(StringUtils.replace(appliNom, ".", "..") + ".nomDomaine");
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
		nomEnsembleDomaine = config.getString(StringUtils.replace(appliNom, ".", "..") + ".nomEnsembleDomaine");
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
		nomFichierConfig = config.getString(StringUtils.replace(appliNom, ".", "..") + ".nomFichierConfig", appliNom + ".config");
	}

	/**
	 * Var.enUS: mavenVersion
	 * enUS: The version of maven being used. 
	 */
	public String versionMaven;
	/**	
	 * Var.enUS: _mavenVersion
	 * r: versionMaven
	 * r.enUS: mavenVersion
	 **/ 
	protected void _versionMaven() throws Exception {
		versionMaven = config.getString("maven.versionMaven", "3.5.3");
	}

	/**
	 * Var.enUS: zookeeperVersion
	 * enUS: The version of Zookeeper being used. 
	 */
	public String versionZookeeper;
	/**	
	 * Var.enUS: _zookeeperVersion
	 * r: versionZookeeper
	 * r.enUS: zookeeperVersion
	 **/ 
	protected void _versionZookeeper() throws Exception {
		versionZookeeper = config.getString("maven.versionZookeeper", "3.5.4");
	}

	/**
	 * Var.enUS: zookeeperPortPrefix
	 * enUS: The port prefix for zookeeper (default is "102"). 
	 */
	public String prefixePortZookeeper;
	/**	
	 * Var.enUS: _zookeeperPortPrefix
	 * r: prefixePortZookeeper
	 * r.enUS: zookeeperPortPrefix
	 **/ 
	protected void _prefixePortZookeeper() throws Exception {
		prefixePortZookeeper = config.getString("zookeeper.prefixePortZookeeper", "102");
	}

	/**
	 * Var.enUS: zookeeperClientPort
	 * enUS: The Zookeeper client port (default is "10281"). 
	 */
	public String portClientZookeeper;
	/**	
	 * Var.enUS: _zookeeperClientPort
	 * r: portClientZookeeper
	 * r.enUS: zookeeperClientPort
	 * r: prefixePortZookeeper
	 * r.enUS: zookeeperPortPrefix
	 **/ 
	protected void _portClientZookeeper() throws Exception {
		portClientZookeeper = config.getString("zookeeper.portClientZookeeper", prefixePortZookeeper + "81");
	}

	/**
	 * Var.enUS: zookeeperAdminPort
	 * enUS: The Zookeeper admin port (default is "10280"). 
	 */
	public String portAdminZookeeper;
	/**	
	 * Var.enUS: _zookeeperAdminPort
	 * r: portAdminZookeeper
	 * r.enUS: zookeeperAdminPort
	 * r: prefixePortZookeeper
	 * r.enUS: zookeeperPortPrefix
	 **/ 
	protected void _portAdminZookeeper() throws Exception {
		portAdminZookeeper = config.getString("zookeeper.portAdminZookeeper", prefixePortZookeeper + "80");
	}

	/**
	 * Var.enUS: solrVersion
	 * enUS: The version of Solr being used. 
	 */
	public String versionSolr;
	/**	
	 * Var.enUS: _solrVersion
	 * r: versionSolr
	 * r.enUS: solrVersion
	 **/ 
	protected void _versionSolr() throws Exception {
		versionSolr = config.getString("solr.versionSolr", "7.3.1");
	}

	/**
	 * Var.enUS: solrPortPrefix
	 * enUS: The port prefix for Solr (default is "103"). 
	 */
	public String prefixePortSolr;
	/**	
	 * Var.enUS: _solrPortPrefix
	 * r: prefixePortSolr
	 * r.enUS: solrPortPrefix
	 **/ 
	protected void _prefixePortSolr() throws Exception {
		prefixePortSolr = config.getString("solr.prefixePortSolr", "103");
	}

	/**
	 * Var.enUS: solrPort
	 * enUS: The Solr web port (default is "10383"). 
	 */ 
	public String portSolr;
	/**	
	 * Var.enUS: _solrPort
	 * r: portSolr
	 * r.enUS: solrPort
	 * r: prefixePortSolr
	 * r.enUS: solrPortPrefix
	 **/ 
	protected void _portSolr() throws Exception {
		portSolr = config.getString("solr.portSolr", prefixePortSolr + "83");
	}

	/**
	 * Var.enUS: solrUrlComputate
	 * enUS: The Solr web URL for the "computate" index. 
	 */ 
	public String urlSolrComputate;
	/**	
	 * Var.enUS: _solrUrlComputate
	 * r: urlSolrComputate
	 * r.enUS: solrUrlComputate
	 * r: urlSolr
	 * r.enUS: solrUrl
	 * r: portSolr
	 * r.enUS: solrPort
	 **/ 
	protected void _urlSolrComputate() throws Exception {
		urlSolrComputate = config.getString("solr.urlSolr", "http://localhost:" + portSolr + "/solr/computate");
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
	 * r: urlSolrComputate
	 * r.enUS: solrUrlComputate
	 **/ 
	protected void _clientSolrComputate() throws Exception {
		clientSolrComputate = new HttpSolrClient.Builder(urlSolrComputate).build();
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
		siteCrypte = config.getBoolean(StringUtils.replace(appliNom, ".", "..") + ".siteCrypte", false);
	}

	/**
	 * Var.enUS: siteWriteMethods
	 */ 
	public ArrayList<String> siteEcrireMethodes = new ArrayList<String>();
	/**	
	 * Var.enUS: _siteWriteMethods
	 * r: siteEcrireMethodes
	 * r.enUS: siteWriteMethods
	 **/ 
	protected void _siteEcrireMethodes() throws Exception {
		siteEcrireMethodes.addAll(config.getList(String.class, StringUtils.replace(appliNom, ".", "..") + ".siteEcrireMethodes"));
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
	 * r: urlSolrComputate
	 * r.enUS: solrUrlComputate
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: cheminsSource
	 * r.enUS: sourcePaths
	 * r: toutCheminsSource
	 * r.enUS: allSourcePaths
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 * r: nomsMethodeTest
	 * r.enUS: testMethodNames
	 * r: siteCrypte
	 * r.enUS: siteEncrypted
	 * r: siteEcrireMethodes
	 * r.enUS: siteWriteMethods
	 **/ 
	public void initConfigSite() throws Exception {
		_appliNom();
		_appliChemin();
		_cheminSrcMainJava();
		_cheminSrcGenJava();
		_configChemin();
		_fichierConfig();
		_configurations();
		_config();
		_langueNom();
		_langueNomActuel();
		_autresLangues();
		_toutesLangues();
		_langueIndexe();
		_nomDomaine();
		_nomEnsembleDomaine();
		_nomFichierConfig();
		_versionMaven();
		_versionZookeeper();
		_prefixePortZookeeper();
		_portClientZookeeper();
		_portAdminZookeeper();
		_versionSolr();
		_prefixePortSolr();
		_portSolr();
		_urlSolrComputate();
		_clientSolrComputate();
		_cheminsSource();
		_toutCheminsSource();
		_nomsMethodeTest();
		_siteCrypte();
		_siteEcrireMethodes();
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
				valeurChamp = m.group(2);
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
			Matcher m = Pattern.compile("^r(egex)?:\\s*(.*)((?!\\nr:)[\\s\\S])*?\\nr\\." + langueNom + ":\\s*(.*)", Pattern.MULTILINE).matcher(commentaire);
			boolean trouve = m.find();
			
			while(trouve) {
				String texteRegex = m.group(1);
				String texteRecherche = m.group(2);
				String texteRemplacement = m.group(4);
				if(texteRecherche != null && texteRemplacement != null) {
					String motifRegex = null;

					if("egex".equals(texteRegex))
						motifRegex = texteRecherche;
					else
						motifRegex = Pattern.quote(texteRecherche);

					Matcher m2 = Pattern.compile(motifRegex, Pattern.MULTILINE).matcher(codeSourceLangue);
					boolean trouve2 = m2.find();
					StringBuffer sortie2 = new StringBuffer();
					
					while(trouve2) {
						m2.appendReplacement(sortie2, texteRemplacement);
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
