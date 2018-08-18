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
 * enUS: Loads the properties in the application configuration file into specific fields. 
 * frFR: Charge les propriétés dans le fichier de configuration de l'application dans des champs spécifiques. 
 */
public class ConfigSite {

	/**	Le nom de l'lappli. **/
	protected String appliNom;
	protected void _appliNom() throws Exception {
		appliNom = System.getenv("appliNom"); 
	}

	/**	
	 * var.enUS: appPath
	 * frFR: Le chemin vers l'lappli. 
	 * enUS: The path to the application. 
	 * **/
	protected String appliChemin;
	protected void _appliChemin() throws Exception {
		appliChemin = System.getenv("appliChemin"); 
	}

	protected String cheminSrcMainJava;
	protected void _cheminSrcMainJava() throws Exception {
		cheminSrcMainJava = appliChemin + "/src/main/java";
	}

	protected String cheminSrcGenJava;
	protected void _cheminSrcGenJava() throws Exception {
		cheminSrcGenJava = appliChemin + "/src/gen/java";
	}

	protected String cheminConfiguration;
	protected void _cheminConfiguration() throws Exception {
		cheminConfiguration = appliChemin + "/config/" + appliNom + ".config";
		System.out.println("cheminConfiguration: " + cheminConfiguration);  
	}

	protected File fichierConfiguration;
	protected void _fichierConfiguration() throws Exception {
		fichierConfiguration = new File(cheminConfiguration);
	}

	/**  **/  
	protected Configurations configurations;
	protected void _configurations() throws Exception {
		configurations = new Configurations();
	}

	/**  **/
	protected INIConfiguration config;
	protected void _config() throws Exception {
		config = configurations.ini(fichierConfiguration);
	}

	/**	Le nom de la langueNom. **/
	protected String langueNom;
	protected void _langueNom() throws Exception {
		langueNom = config.getString(appliNom + ".langueNom");
	} 

	/**	Le nom de la langueNom. **/
	protected String langueNomActuel;
	protected void _langueNomActuel() throws Exception {
		if(StringUtils.equals(langueNom, "tout"))
			langueNomActuel = "frFR";
		else
			langueNomActuel = langueNom;
	} 

	protected String[] toutesLangues;
	protected void _toutesLangues() throws Exception {
		toutesLangues = config.getStringArray(appliNom + ".toutesLangues");
	}

	protected String[] autresLangues;
	protected void _autresLangues() throws Exception {
		autresLangues = ArrayUtils.removeElement(toutesLangues, langueNom);
	}

	protected Boolean langueIndexe;
	protected void _langueIndexe() throws Exception {
		langueIndexe = ArrayUtils.contains(toutesLangues, langueNom);
	}

	protected String nomDomaine;
	protected void _nomDomaine() throws Exception {
		nomDomaine = config.getString(appliNom + ".nomDomaine");
	}
	
	protected String nomEnsembleDomaine;
	protected void _nomEnsembleDomaine() throws Exception {
		String[] partis = StringUtils.split(nomDomaine, ".");
		ArrayUtils.reverse(partis);
		nomEnsembleDomaine = StringUtils.join(partis, ".");
	}

	/**	 **/
	protected String nomFichierConfig;
	protected void _nomFicherConfig() throws Exception {
		nomFichierConfig = config.getString(appliNom + ".nomFichierConfig", appliNom + ".config");
	}

	/**	 **/
	protected String cheminConfig;
	protected void _cheminConfig() throws Exception {
		cheminConfig = config.getString(appliNom + ".cheminConfig", appliChemin + "/config/" + nomFichierConfig);
	}

	/**	 **/
	protected String versionMaven;
	protected void _versionMaven() throws Exception {
		versionMaven = config.getString("maven.versionMaven", "3.5.3");
	}

	/**	 **/
	protected String versionZookeeper;
	protected void _versionZookeeper() throws Exception {
		versionZookeeper = config.getString("maven.versionZookeeper", "3.5.4");
	}

	/**	 **/
	protected String prefixePortZookeeper;
	protected void _prefixePortZookeeper() throws Exception {
		prefixePortZookeeper = config.getString("zookeeper.prefixePortZookeeper", "102");
	}

	/**	 **/
	protected String portClientZookeeper;
	protected void _portClientZookeeper() throws Exception {
		portClientZookeeper = config.getString("zookeeper.portClientZookeeper", prefixePortZookeeper + "81");
	}

	/**	 **/
	protected String portAdminZookeeper;
	protected void _portAdminZookeeper() throws Exception {
		portAdminZookeeper = config.getString("zookeeper.portAdminZookeeper", prefixePortZookeeper + "80");
	}

	/**	 **/
	protected String versionSolr;
	protected void _versionSolr() throws Exception {
		versionSolr = config.getString("zookeeper.versionSolr", "7.3.1");
	}

	/**	 **/
	protected String prefixePortSolr;
	protected void _prefixePortSolr() throws Exception {
		prefixePortSolr = config.getString("zookeeper.prefixePortSolr", "103");
	}

	/**	 **/
	protected String portSolr;
	protected void _portSolr() throws Exception {
		portSolr = config.getString("zookeeper.portSolr", prefixePortSolr + "83");
	}

	/**	 **/
	protected String urlSolrComputate;
	protected void _urlSolrComputate() throws Exception {
		urlSolrComputate = config.getString("zookeeper.urlSolr", "http://localhost:" + portSolr + "/solr/computate");
	}

	protected SolrClient clientSolrComputate;
	protected void _clientSolrComputate() throws Exception {
		clientSolrComputate = new HttpSolrClient.Builder(urlSolrComputate).build();
	}

	protected ArrayList<String> cheminsARegarder = new ArrayList<String>();
	protected void _cheminsARegarder() throws Exception {
		cheminsARegarder.add(cheminSrcMainJava);
	}

	protected ArrayList<String> cheminsSource = new ArrayList<String>();
	protected void _cheminsSource() throws Exception {
		cheminsSource.add(cheminSrcMainJava);
		cheminsSource.add(cheminSrcGenJava);
	}

	protected ArrayList<String> toutCheminsSource = new ArrayList<String>();
	protected void _toutCheminsSource() throws Exception {
		toutCheminsSource.add(cheminSrcMainJava);
		toutCheminsSource.add(cheminSrcGenJava);
	}

	protected ArrayList<String> nomsMethodeTest = new ArrayList<String>();
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
