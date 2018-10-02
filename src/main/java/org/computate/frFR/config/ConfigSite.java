package org.computate.frFR.config;    

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
 * nomCanonique.enUS: org.computate.enUS.java.SiteConfig
 * enUS: Loads the properties in the application config file into specific fields. 
 * frFR: Charge les propriétés dans le fichier de config de l'application dans des champs spécifiques. 
 */ 
public class ConfigSite { 
	
	public ConfigSite() {
	}

	/**	
	 * var.enUS: appName
	 * frFR: Le nom de l'lappli. 
	 * enUS: The name of the application. 
	 **/ 
	public String appliNom;
	/**	
	 * var.enUS: _appName
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _appliNom() throws Exception {
		if(appliNom == null)
			appliNom = System.getenv("appliNom"); 
	}

	/**	
	 * var.enUS: appPath
	 * frFR: Le chemin vers l'lappli. 
	 * enUS: The path to the application. 
	 * **/
	public String appliChemin;
	/**	
	 * var.enUS: _appPath
	 * r: appliChemin
	 * r.enUS: appPath
	 **/ 
	protected void _appliChemin() throws Exception {
		if(appliChemin == null)
			appliChemin = System.getenv("appliChemin"); 
	} 

	/**
	 * var.enUS: srcMainJavaPath
	 * enUS: The absolute path to the /src/main/java directory. 
	 */
	public String cheminSrcMainJava;
	/**	
	 * var.enUS: _srcMainJavaPath
	 * r: cheminSrcMainJava
	 * r.enUS: srcMainJavaPath
	 * r: appliChemin
	 * r.enUS: appPath
	 **/ 
	protected void _cheminSrcMainJava() throws Exception {
		cheminSrcMainJava = appliChemin + "/src/main/java";
	}

	/**
	 * var.enUS: srcGenJavaPath
	 * enUS: The absolute path to the /src/gen/java directory. 
	 */
	public String cheminSrcGenJava;
	/**	
	 * var.enUS: _srcGenJavaPath
	 * r: cheminSrcGenJava
	 * r.enUS: srcGenJavaPath
	 * r: appliChemin
	 * r.enUS: appPath
	 **/ 
	protected void _cheminSrcGenJava() throws Exception {
		cheminSrcGenJava = appliChemin + "/src/gen/java";
	}

	/**
	 * var.enUS: configPath
	 * enUS: The absolute path to the app config file. 
	 */
	public String cheminConfig;
	/**	
	 * var.enUS: _configPath
	 * r: cheminConfig
	 * r.enUS: configPath
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _cheminConfig() throws Exception {
		cheminConfig = appliChemin + "/config/" + appliNom + ".config";
	}
//
//	/**
//	 * var.enUS: configPath
//	 * enUS: The absolute path to the config file. 
//	 */
//	public String cheminConfig;
//	/**	
//	 * var.enUS: _configPath
//	 * r.enUS: cheminConfig
//	 * configPath
//	 * r.enUS: cheminConfig
//	 * configPath
//	 * r.enUS: appliNom
//	 * appName
//	 * r.enUS: appliChemin
//	 * appPath
//	 * r.enUS: nomFichierConfig
//	 * configFileName
//	 **/ 
//	protected void _cheminConfig() throws Exception {
//		cheminConfig = config.getString(StringUtils.replace(appliNom, ".", "..") + ".cheminConfig", appliChemin + "/config/" + nomFichierConfig);
//	}

	/**
	 * var.enUS: configFile
	 * enUS: The File Object for the app config file. 
	 */
	public File fichierConfig;
	/**	
	 * var.enUS: _configFile
	 * r: fichierConfig
	 * r.enUS: configFile
	 * r: cheminConfig
	 * r.enUS: configPath
	 **/ 
	protected void _fichierConfig() throws Exception {
		fichierConfig = new File(cheminConfig);
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
	 * var.enUS: languageName
	 * enUS: The configured language name for this app. 
	 */
	public String langueNom;
	/**	
	 * var.enUS: _languageName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _langueNom() throws Exception {
		langueNom = config.getString(StringUtils.replace(appliNom, ".", "..") + ".langueNom");
	} 

	/**
	 * var.enUS: languageActualName
	 * enUS: The actual language of the code. 
	 * enUS: If the languageName is configured as "tout", then the languageActualName would be "frFR". 
	 */
	public String langueNomActuel;
	/**	
	 * var.enUS: _languageActualName
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
	 * var.enUS: otherLanguages
	 * enUS: Other languages supported by this app besides the actual language name. 
	 */
	public String[] autresLangues;
	/**	
	 * var.enUS: _otherLanguages
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
		System.out.println("autresLangues: " + autresLangues);
	}

	/**
	 * var.enUS: allLanguages
	 * enUS: All language names supported in this application. 
	 */
	public String[] toutesLangues;
	/**	
	 * var.enUS: _allLanguages
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
	 * var.enUS: languageIndexed
	 * enUS: True if the languageName is an actual locale that can be indexed. 
	 * enUS: If the languageName is configured as "tout", then that language would not be indexed. 
	 */
	public Boolean langueIndexe;
	/**	
	 * var.enUS: _languageIndexed
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
	 * var.enUS: domainName
	 * enUS: The domain name of the website where this will be deployed (like "example.com"). 
	 */
	public String nomDomaine;
	/**	
	 * var.enUS: _domainName
	 * r: nomDomaine
	 * r.enUS: domainName
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _nomDomaine() throws Exception {
		nomDomaine = config.getString(StringUtils.replace(appliNom, ".", "..") + ".nomDomaine");
	}
	
	/**
	 * var.enUS: domainPackageName
	 * enUS: The Java package name for the domain (example.com would have a package name of "com.example"). 
	 */
	public String nomEnsembleDomaine;
	/**	
	 * var.enUS: _domainPackageName
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: nomDomaine
	 * r.enUS: domainName
	 * r: partis
	 * r.enUS: parts
	 **/ 
	protected void _nomEnsembleDomaine() throws Exception {
		String[] partis = StringUtils.split(nomDomaine, ".");
		ArrayUtils.reverse(partis);
		nomEnsembleDomaine = StringUtils.join(partis, ".");
	}

	/**
	 * var.enUS: configFileName
	 * enUS: The name of the config file which defaults to the appName followed by ".config". 
	 */
	public String nomFichierConfig;
	/**	
	 * var.enUS: _configFileName
	 * r: nomFichierConfig
	 * r.enUS: configFileName
	 * r: appliNom
	 * r.enUS: appName
	 **/ 
	protected void _nomFichierConfig() throws Exception {
		nomFichierConfig = config.getString(StringUtils.replace(appliNom, ".", "..") + ".nomFichierConfig", appliNom + ".config");
	}

	/**
	 * var.enUS: mavenVersion
	 * enUS: The version of maven being used. 
	 */
	public String versionMaven;
	/**	
	 * var.enUS: _mavenVersion
	 * r: versionMaven
	 * r.enUS: mavenVersion
	 **/ 
	protected void _versionMaven() throws Exception {
		versionMaven = config.getString("maven.versionMaven", "3.5.3");
	}

	/**
	 * var.enUS: zookeeperVersion
	 * enUS: The version of Zookeeper being used. 
	 */
	public String versionZookeeper;
	/**	
	 * var.enUS: _zookeeperVersion
	 * r: versionZookeeper
	 * r.enUS: zookeeperVersion
	 **/ 
	protected void _versionZookeeper() throws Exception {
		versionZookeeper = config.getString("maven.versionZookeeper", "3.5.4");
	}

	/**
	 * var.enUS: zookeeperPortPrefix
	 * enUS: The port prefix for zookeeper (default is "102"). 
	 */
	public String prefixePortZookeeper;
	/**	
	 * var.enUS: _zookeeperPortPrefix
	 * r: prefixePortZookeeper
	 * r.enUS: zookeeperPortPrefix
	 **/ 
	protected void _prefixePortZookeeper() throws Exception {
		prefixePortZookeeper = config.getString("zookeeper.prefixePortZookeeper", "102");
	}

	/**
	 * var.enUS: zookeeperClientPort
	 * enUS: The Zookeeper client port (default is "10281"). 
	 */
	public String portClientZookeeper;
	/**	
	 * var.enUS: _zookeeperClientPort
	 * r: portClientZookeeper
	 * r.enUS: zookeeperClientPort
	 * r: prefixePortZookeeper
	 * r.enUS: zookeeperPortPrefix
	 **/ 
	protected void _portClientZookeeper() throws Exception {
		portClientZookeeper = config.getString("zookeeper.portClientZookeeper", prefixePortZookeeper + "81");
	}

	/**
	 * var.enUS: zookeeperAdminPort
	 * enUS: The Zookeeper admin port (default is "10280"). 
	 */
	public String portAdminZookeeper;
	/**	
	 * var.enUS: _zookeeperAdminPort
	 * r: portAdminZookeeper
	 * r.enUS: zookeeperAdminPort
	 * r: prefixePortZookeeper
	 * r.enUS: zookeeperPortPrefix
	 **/ 
	protected void _portAdminZookeeper() throws Exception {
		portAdminZookeeper = config.getString("zookeeper.portAdminZookeeper", prefixePortZookeeper + "80");
	}

	/**
	 * var.enUS: solrVersion
	 * enUS: The version of Solr being used. 
	 */
	public String versionSolr;
	/**	
	 * var.enUS: _solrVersion
	 * r: versionSolr
	 * r.enUS: solrVersion
	 **/ 
	protected void _versionSolr() throws Exception {
		versionSolr = config.getString("solr.versionSolr", "7.3.1");
	}

	/**
	 * var.enUS: solrPortPrefix
	 * enUS: The port prefix for Solr (default is "103"). 
	 */
	public String prefixePortSolr;
	/**	
	 * var.enUS: _solrPortPrefix
	 * r: prefixePortSolr
	 * r.enUS: solrPortPrefix
	 **/ 
	protected void _prefixePortSolr() throws Exception {
		prefixePortSolr = config.getString("solr.prefixePortSolr", "103");
	}

	/**
	 * var.enUS: solrPort
	 * enUS: The Solr web port (default is "10383"). 
	 */ 
	public String portSolr;
	/**	
	 * var.enUS: _solrPort
	 * r: portSolr
	 * r.enUS: solrPort
	 * r: prefixePortSolr
	 * r.enUS: solrPortPrefix
	 **/ 
	protected void _portSolr() throws Exception {
		portSolr = config.getString("solr.portSolr", prefixePortSolr + "83");
	}

	/**
	 * var.enUS: solrUrlComputate
	 * enUS: The Solr web URL for the "computate" index. 
	 */ 
	public String urlSolrComputate;
	/**	
	 * var.enUS: _solrUrlComputate
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
	 * var.enUS: solrClientComputate
	 * enUS: The Solrj client for the "computate" index. 
	 */ 
	public SolrClient clientSolrComputate;
	/**	
	 * var.enUS: _solrClientComputate
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: urlSolrComputate
	 * r.enUS: solrUrlComputate
	 **/ 
	protected void _clientSolrComputate() throws Exception {
		clientSolrComputate = new HttpSolrClient.Builder(urlSolrComputate).build();
	}

	/**
	 * var.enUS: sourcePaths
	 * enUS: The absolute paths to source code directories in the app to watch for changes. 
	 */ 
	public ArrayList<String> cheminsSource = new ArrayList<String>();
	/**	
	 * var.enUS: _sourcePaths
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
	 * var.enUS: allSourcePaths
	 * enUS: The absolute paths to source code directories in all apps to watch for changes. 
	 */ 
	public ArrayList<String> toutCheminsSource = new ArrayList<String>();
	/**	
	 * var.enUS: _allSourcePaths
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
	 * var.enUS: testMethodNames
	 * enUS: The names of methods that will be tested when a source file is updated. 
	 */ 
	public ArrayList<String> nomsMethodeTest = new ArrayList<String>();
	/**	
	 * var.enUS: _testMethodNames
	 **/ 
	protected void _nomsMethodeTest() throws Exception {
	}

	/**	
	 * var.enUS: initSiteConfig
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
	 * r: cheminConfig
	 * r.enUS: configPath
	 * r: cheminConfig
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
	 **/ 
	public void initConfigSite() throws Exception {
		_appliNom();
		_appliChemin();
		_cheminSrcMainJava();
		_cheminSrcGenJava();
		_cheminConfig();
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
	}

	/**
	 * param1.var.enUS: pattern
	 * param2.var.enUS: text
	 * r: motif
	 * r.enUS: pattern
	 * r: texte
	 * r.enUS: text
	 */
	public String regex(String motif, String texte) {
		String o = regex(motif, texte, 1);
		return o;
	}

	/**
	 * param1.var.enUS: pattern
	 * param2.var.enUS: text
	 * param3.var.enUS: defaultValue
	 * r: motif
	 * r.enUS: pattern
	 * r: texte
	 * r.enUS: text
	 * r: valeurDefaut
	 * r.enUS: defaultValue
	 */
	public String regex(String motif, String texte, String valeurDefaut) {
		String o = regex(motif, texte, 1);
		if(StringUtils.isEmpty(o))
			return valeurDefaut;
		else
			return o;
	}

	/** 
	 * param1.var.enUS: pattern
	 * param2.var.enUS: text
	 * param3.var.enUS: group
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
	 * var.enUS: regexFound
	 * param1.var.enUS: pattern
	 * param2.var.enUS: text
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
	 * var.enUS: regexList
	 * param1.var.enUS: pattern
	 * param2.var.enUS: text
	 * param3.var.enUS: group
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
				o = m.group(1);
				resultats.add(o); 
				trouve = m.find();
			}
		}
		return resultats;
	}    

	/**
	 * var.enUS: regexReplaceAll
	 * param1.var.enUS: comment
	 * param2.var.enUS: sourceCode
	 * param3.var.enUS: languageName
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
	 */  
	public String regexRemplacerTout(String commentaire, String codeSource, String langueNom) throws Exception {
		String codeSourceLangue = codeSource;
		if(!StringUtils.isEmpty(commentaire)) {
			Matcher m = Pattern.compile("^r:\\s*(.*)((?!\\nr:)[\\s\\S])*?\\nr\\." + langueNom + ":\\s*(.*)", Pattern.MULTILINE).matcher(commentaire);
			boolean trouve = m.find();
			
			while(trouve) {
				String texteRecherche = m.group(1);
				String texteRemplacement = m.group(3);
				if(texteRecherche != null && texteRemplacement != null) {
					Matcher m2 = Pattern.compile(Pattern.quote(texteRecherche), Pattern.MULTILINE).matcher(codeSourceLangue);
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
	 * param1.var.enUS: values
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
