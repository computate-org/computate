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
 * classeNomCanonique_enUS: org.computate.enUS.java.SiteConfig
 * enUS: Loads the properties in the application configuration file into specific fields. 
 * frFR: Charge les propriétés dans le fichier de configuration de l'application dans des champs spécifiques. 
 */      
public class ConfigSite {   

	/**	
	 * champVar_enUS: appName
	 * frFR: Le nom de l'lappli. 
	 * enUS: The name of the application. 
	 **/ 
	public String appliNom;
	/**	
	 * methodeVar_enUS: _appName
	 * r.enUS: appliNom
	 * appName
	 **/ 
	protected void _appliNom() throws Exception {
		appliNom = System.getenv("appliNom"); 
	}

	/**	
	 * champVar_enUS: appPath
	 * frFR: Le chemin vers l'lappli. 
	 * enUS: The path to the application. 
	 * **/
	public String appliChemin;
	/**	
	 * methodeVar_enUS: _appPath
	 * r.enUS: appliChemin
	 * appPath
	 **/ 
	protected void _appliChemin() throws Exception {
		appliChemin = System.getenv("appliChemin"); 
	}

	/**
	 * champVar_enUS: srcMainJavaPath
	 * enUS: The absolute path to the /src/main/java directory. 
	 */
	public String cheminSrcMainJava;
	/**	
	 * methodeVar_enUS: _srcMainJavaPath
	 * r.enUS: cheminSrcMainJava
	 * srcMainJavaPath
	 * r.enUS: appliChemin
	 * appPath
	 **/ 
	protected void _cheminSrcMainJava() throws Exception {
		cheminSrcMainJava = appliChemin + "/src/main/java";
	}

	/**
	 * champVar_enUS: srcGenJavaPath
	 * enUS: The absolute path to the /src/gen/java directory. 
	 */
	public String cheminSrcGenJava;
	/**	
	 * methodeVar_enUS: _srcGenJavaPath
	 * r.enUS: cheminSrcGenJava
	 * srcGenJavaPath
	 * r.enUS: appliChemin
	 * appPath
	 **/ 
	protected void _cheminSrcGenJava() throws Exception {
		cheminSrcGenJava = appliChemin + "/src/gen/java";
	}

	/**
	 * champVar_enUS: configurationPath
	 * enUS: The absolute path to the app config file. 
	 */
	public String cheminConfiguration;
	/**	
	 * methodeVar_enUS: _configurationPath
	 * r.enUS: cheminConfiguration
	 * configurationPath
	 * r.enUS: appliChemin
	 * appPath
	 * r.enUS: appliNom
	 * appName
	 **/ 
	protected void _cheminConfiguration() throws Exception {
		cheminConfiguration = appliChemin + "/config/" + appliNom + ".config";
		System.out.println("cheminConfiguration: " + cheminConfiguration);  
	}

	/**
	 * champVar_enUS: configurationFile
	 * enUS: The File Object for the app config file. 
	 */
	public File fichierConfiguration;
	/**	
	 * methodeVar_enUS: _configurationFile
	 * r.enUS: fichierConfiguration
	 * configurationFile
	 * r.enUS: cheminConfiguration
	 * configurationPath
	 **/ 
	protected void _fichierConfiguration() throws Exception {
		fichierConfiguration = new File(cheminConfiguration);
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
	 * r.enUS: fichierConfiguration
	 * configurationFile
	 **/ 
	protected void _config() throws Exception {
		config = configurations.ini(fichierConfiguration);
	}

	/**
	 * champVar_enUS: languageName
	 * enUS: The configured language name for this app. 
	 */
	public String langueNom;
	/**	
	 * methodeVar_enUS: _languageName
	 * r.enUS: langueNom
	 * languageName
	 * r.enUS: appliNom
	 * appName
	 **/ 
	protected void _langueNom() throws Exception {
		langueNom = config.getString(appliNom + ".langueNom");
	} 

	/**
	 * champVar_enUS: languageActualName
	 * enUS: The actual language of the code. 
	 * enUS: If the languageName is configured as "tout", then the languageActualName would be "frFR". 
	 */
	public String langueNomActuel;
	/**	
	 * methodeVar_enUS: _languageActualName
	 * r.enUS: langueNomActuel
	 * languageActualName
	 * r.enUS: langueNom
	 * languageName
	 **/ 
	protected void _langueNomActuel() throws Exception {
		if(StringUtils.equals(langueNom, "tout"))
			langueNomActuel = "frFR";
		else
			langueNomActuel = langueNom;
	} 

	/**
	 * champVar_enUS: allLanguages
	 * enUS: All language names supported in this application. 
	 */
	public String[] toutesLangues;
	/**	
	 * methodeVar_enUS: _allLanguages
	 * r.enUS: toutesLangues
	 * allLanguages
	 * r.enUS: appliNom
	 * appName
	 **/ 
	protected void _toutesLangues() throws Exception {
		toutesLangues = config.getStringArray(appliNom + ".toutesLangues");
	}

	/**
	 * champVar_enUS: otherLanguages
	 * enUS: Other languages supported by this app besides the actual language name. 
	 */
	public String[] autresLangues;
	/**	
	 * methodeVar_enUS: _otherLanguages
	 * r.enUS: autresLangues
	 * otherLanguages
	 * r.enUS: toutesLangues
	 * allLanguages
	 * r.enUS: langueNom
	 * languageName
	 **/ 
	protected void _autresLangues() throws Exception {
		autresLangues = ArrayUtils.removeElement(toutesLangues, langueNom);
	}

	/**
	 * champVar_enUS: languageIndexed
	 * enUS: True if the languageName is an actual locale that can be indexed. 
	 * enUS: If the languageName is configured as "tout", then that language would not be indexed. 
	 */
	public Boolean langueIndexe;
	/**	
	 * methodeVar_enUS: _languageIndexed
	 * r.enUS: langueIndexe
	 * languageIndexed
	 * r.enUS: toutesLangues
	 * allLanguages
	 * r.enUS: langueNom
	 * languageName
	 **/ 
	protected void _langueIndexe() throws Exception {
		langueIndexe = ArrayUtils.contains(toutesLangues, langueNom);
	}

	/**
	 * champVar_enUS: domainName
	 * enUS: The domain name of the website where this will be deployed (like "example.com"). 
	 */
	public String nomDomaine;
	/**	
	 * methodeVar_enUS: _domainName
	 * r.enUS: nomDomaine
	 * domainName
	 * r.enUS: appliNom
	 * appName
	 **/ 
	protected void _nomDomaine() throws Exception {
		nomDomaine = config.getString(appliNom + ".nomDomaine");
	}
	
	/**
	 * champVar_enUS: domainPackageName
	 * enUS: The Java package name for the domain (example.com would have a package name of "com.example"). 
	 */
	public String nomEnsembleDomaine;
	/**	
	 * methodeVar_enUS: _domainPackageName
	 * r.enUS: nomEnsembleDomaine
	 * domainPackageName
	 * r.enUS: nomDomaine
	 * domainName
	 * r.enUS: partis
	 * parts
	 **/ 
	protected void _nomEnsembleDomaine() throws Exception {
		String[] partis = StringUtils.split(nomDomaine, ".");
		ArrayUtils.reverse(partis);
		nomEnsembleDomaine = StringUtils.join(partis, ".");
	}

	/**
	 * champVar_enUS: configFileName
	 * enUS: The name of the configuration file which defaults to the appName followed by ".config". 
	 */
	public String nomFichierConfig;
	/**	
	 * methodeVar_enUS: _configFileName
	 * r.enUS: nomFichierConfig
	 * configFileName
	 * r.enUS: appliNom
	 * appName
	 **/ 
	protected void _nomFicherConfig() throws Exception {
		nomFichierConfig = config.getString(appliNom + ".nomFichierConfig", appliNom + ".config");
	}

	/**
	 * champVar_enUS: configPath
	 * enUS: The absolute path to the config file. 
	 */
	public String cheminConfig;
	/**	
	 * methodeVar_enUS: _configPath
	 * r.enUS: cheminConfig
	 * configPath
	 * r.enUS: cheminConfig
	 * configPath
	 * r.enUS: appliNom
	 * appName
	 * r.enUS: appliChemin
	 * appPath
	 * r.enUS: nomFichierConfig
	 * configFileName
	 **/ 
	protected void _cheminConfig() throws Exception {
		cheminConfig = config.getString(appliNom + ".cheminConfig", appliChemin + "/config/" + nomFichierConfig);
	}

	/**
	 * champVar_enUS: mavenVersion
	 * enUS: The version of maven being used. 
	 */
	public String versionMaven;
	/**	
	 * methodeVar_enUS: _mavenVersion
	 * r.enUS: versionMaven
	 * mavenVersion
	 **/ 
	protected void _versionMaven() throws Exception {
		versionMaven = config.getString("maven.versionMaven", "3.5.3");
	}

	/**
	 * champVar_enUS: zookeeperVersion
	 * enUS: The version of Zookeeper being used. 
	 */
	public String versionZookeeper;
	/**	
	 * methodeVar_enUS: _zookeeperVersion
	 * r.enUS: versionZookeeper
	 * zookeeperVersion
	 **/ 
	protected void _versionZookeeper() throws Exception {
		versionZookeeper = config.getString("maven.versionZookeeper", "3.5.4");
	}

	/**
	 * champVar_enUS: zookeeperPortPrefix
	 * enUS: The port prefix for zookeeper (default is "102"). 
	 */
	public String prefixePortZookeeper;
	/**	
	 * methodeVar_enUS: _zookeeperPortPrefix
	 * r.enUS: prefixePortZookeeper
	 * zookeeperPortPrefix
	 **/ 
	protected void _prefixePortZookeeper() throws Exception {
		prefixePortZookeeper = config.getString("zookeeper.prefixePortZookeeper", "102");
	}

	/**
	 * champVar_enUS: zookeeperClientPort
	 * enUS: The Zookeeper client port (default is "10281"). 
	 */
	public String portClientZookeeper;
	/**	
	 * methodeVar_enUS: _zookeeperClientPort
	 * r.enUS: portClientZookeeper
	 * zookeeperClientPort
	 * r.enUS: prefixePortZookeeper
	 * zookeeperPortPrefix
	 **/ 
	protected void _portClientZookeeper() throws Exception {
		portClientZookeeper = config.getString("zookeeper.portClientZookeeper", prefixePortZookeeper + "81");
	}

	/**
	 * champVar_enUS: zookeeperAdminPort
	 * enUS: The Zookeeper admin port (default is "10280"). 
	 */
	public String portAdminZookeeper;
	/**	
	 * methodeVar_enUS: _zookeeperAdminPort
	 * r.enUS: portAdminZookeeper
	 * zookeeperAdminPort
	 * r.enUS: prefixePortZookeeper
	 * zookeeperPortPrefix
	 **/ 
	protected void _portAdminZookeeper() throws Exception {
		portAdminZookeeper = config.getString("zookeeper.portAdminZookeeper", prefixePortZookeeper + "80");
	}

	/**
	 * champVar_enUS: solrVersion
	 * enUS: The version of Solr being used. 
	 */
	public String versionSolr;
	/**	
	 * methodeVar_enUS: _solrVersion
	 * r.enUS: versionSolr
	 * solrVersion
	 **/ 
	protected void _versionSolr() throws Exception {
		versionSolr = config.getString("solr.versionSolr", "7.3.1");
	}

	/**
	 * champVar_enUS: solrPortPrefix
	 * enUS: The port prefix for Solr (default is "103"). 
	 */
	public String prefixePortSolr;
	/**	
	 * methodeVar_enUS: _solrPortPrefix
	 * r.enUS: prefixePortSolr
	 * solrPortPrefix
	 **/ 
	protected void _prefixePortSolr() throws Exception {
		prefixePortSolr = config.getString("solr.prefixePortSolr", "103");
	}

	/**
	 * champVar_enUS: solrPort
	 * enUS: The Solr web port (default is "10383"). 
	 */ 
	public String portSolr;
	/**	
	 * methodeVar_enUS: _solrPort
	 * r.enUS: portSolr
	 * solrPort
	 * r.enUS: prefixePortSolr
	 * solrPortPrefix
	 **/ 
	protected void _portSolr() throws Exception {
		portSolr = config.getString("solr.portSolr", prefixePortSolr + "83");
	}

	/**
	 * champVar_enUS: solrUrlComputate
	 * enUS: The Solr web URL for the "computate" index. 
	 */ 
	public String urlSolrComputate;
	/**	
	 * methodeVar_enUS: _solrUrlComputate
	 * r.enUS: urlSolrComputate
	 * solrUrlComputate
	 * r.enUS: urlSolr
	 * solrUrl
	 * r.enUS: portSolr
	 * solrPort
	 **/ 
	protected void _urlSolrComputate() throws Exception {
		urlSolrComputate = config.getString("solr.urlSolr", "http://localhost:" + portSolr + "/solr/computate");
	}

	/**
	 * champVar_enUS: solrClientComputate
	 * enUS: The Solrj client for the "computate" index. 
	 */ 
	public SolrClient clientSolrComputate;
	/**	
	 * methodeVar_enUS: _solrClientComputate
	 * r.enUS: clientSolrComputate
	 * solrClientComputate
	 * r.enUS: urlSolrComputate
	 * solrUrlComputate
	 **/ 
	protected void _clientSolrComputate() throws Exception {
		clientSolrComputate = new HttpSolrClient.Builder(urlSolrComputate).build();
	}

	/**
	 * champVar_enUS: pathsToWatch
	 * enUS: The absolute paths to watch for changes. 
	 */ 
	public ArrayList<String> cheminsARegarder = new ArrayList<String>();
	/**	
	 * methodeVar_enUS: _pathsToWatch
	 * r.enUS: cheminsARegarder
	 * pathsToWatch
	 * r.enUS: cheminSrcMainJava
	 * srcMainJavaPath
	 **/ 
	protected void _cheminsARegarder() throws Exception {
		cheminsARegarder.add(cheminSrcMainJava);
	}

	/**
	 * champVar_enUS: sourcePaths
	 * enUS: The absolute paths to source code directories to watch for changes. 
	 */ 
	public ArrayList<String> cheminsSource = new ArrayList<String>();
	/**	
	 * methodeVar_enUS: _sourcePaths
	 * r.enUS: cheminsSource
	 * sourcePaths
	 * r.enUS: cheminSrcMainJava
	 * srcMainJavaPath
	 * r.enUS: cheminSrcGenJava
	 * srcGenJavaPath
	 **/ 
	protected void _cheminsSource() throws Exception {
		cheminsSource.add(cheminSrcMainJava);
		cheminsSource.add(cheminSrcGenJava);
	}

	public ArrayList<String> toutCheminsSource = new ArrayList<String>();
	protected void _toutCheminsSource() throws Exception {
		toutCheminsSource.add(cheminSrcMainJava);
		toutCheminsSource.add(cheminSrcGenJava);
	}

	public ArrayList<String> nomsMethodeTest = new ArrayList<String>();
	protected void _nomsMethodeTest() throws Exception {
	}

	public void initConfigSite() throws Exception {
		_appliNom();
		_appliChemin();
		_cheminSrcMainJava();
		_cheminSrcGenJava();
		_cheminConfiguration();
		_fichierConfiguration();
		_configurations();
		_config();
		_langueNom();
		_langueNomActuel();
		_toutesLangues();
		_autresLangues();
		_langueIndexe();
		_nomDomaine();
		_nomEnsembleDomaine();
		_nomFicherConfig();
		_cheminConfig();
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
		_cheminsARegarder();
		_cheminsSource();
		_toutCheminsSource();
		_nomsMethodeTest();
	}

	public String regex(String motif, String texte) {
		String o = regex(motif, texte, 1);
		return o;
	}

	public String regex(String motif, String texte, String valeurDefaut) {
		String o = regex(motif, texte, 1);
		if(StringUtils.isEmpty(o))
			return valeurDefaut;
		else
			return o;
	}

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

	public String regexRemplacerTout(String commentaire, String codeSource, String langueNom) throws Exception {
		String codeSourceLangue = codeSource;
		if(!StringUtils.isEmpty(commentaire)) {
			Matcher m = Pattern.compile("^r." + langueNom + ": (.*\\n.*)", Pattern.MULTILINE).matcher(commentaire);
			boolean trouve = m.find();
			
			while(trouve) {
				String texteRechercheRemplacement = m.group(1);
				String[] partisRechercheRemplacement = StringUtils.split(texteRechercheRemplacement, "\n");
				if(partisRechercheRemplacement.length == 2) {
					String texteRecherche = partisRechercheRemplacement[0];
					String texteRemplacement = partisRechercheRemplacement[1];

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

	public String concat(String...valeurs) throws Exception { 
		String resultat = Stream.of(valeurs).collect(Collectors.joining());
		return resultat;
	}  
}
