/*
 * Copyright (c) 2018-2022 Computate Limited Liability Company in Utah, USA. 
 *
 * This program and the accompanying materials are made available under the
 * terms of the GNU GENERAL PUBLIC LICENSE Version 3 which is available at
 * 
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 * 
 * You may not propagate or modify a covered work except as expressly provided 
 * under this License. Any attempt otherwise to propagate or modify it is void, 
 * and will automatically terminate your rights under this License (including 
 * any patent licenses granted under the third paragraph of section 11).
 */
package org.computate.frFR.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.PreemptiveAuth;
import org.apache.solr.client.solrj.impl.PreemptiveBasicAuthClientBuilderFactory;
import org.apache.solr.client.solrj.impl.SolrHttpClientBuilder;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.JinjavaConfig;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.lib.filter.Filter;
import com.hubspot.jinjava.lib.fn.ELFunctionDefinition;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.kubernetes.client.common.KubernetesObject;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Secret;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;

import org.computate.i18n.I18n;


/**
 * NomCanonique.enUS: org.computate.enUS.java.SiteConfig enUS: Loads the
 * properties in the application config file into specific fields. frFR: Charge
 * les propriétés dans le fichier de config de l'application dans des champs
 * spécifiques.
 */
public class ConfigSite {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public static String lookup(String type, String arg1) {
		if("env".equals(type))
			return System.getenv(arg1);
		return null;
	}

	public static KubernetesObject[] query(String type, String kind, String resource_name, String namespace) {
		Logger LOG = LoggerFactory.getLogger(ConfigSite.class);
		try {
			if("kubernetes.core.k8s".equals(type)) {
				ApiClient client;
				try {
					client = ClientBuilder.cluster().build();
				} catch(Throwable ex) {
					client = Config.defaultClient();
				}
				Configuration.setDefaultApiClient(client);
				CoreV1Api api = new CoreV1Api();
				if("Secret".equals(kind)) {
					V1Secret secret = api.readNamespacedSecret(resource_name, namespace).execute();
					secret.getData().keySet().forEach(key -> {
						secret.getData().put(key, Base64.getEncoder().encode(secret.getData().get(key)));
					});
					return new KubernetesObject[] {secret};
				}
			}
		} catch(Throwable ex) {
			throw new RuntimeException(String.format("Unable to query oc get -n %s %s/%s: %s", namespace, kind, resource_name, ex.getMessage()), ex);
		}
		return null;
	}

	public static JsonObject getConfiguration(Jinjava jinjava, JsonObject classeLangueConfig) {
		JsonObject configuration = null;

		try {
			String configChemin = System.getenv(classeLangueConfig.getString("var_VARS_CHEMIN"));

			File configFichier = new File(configChemin);
			String template = Files.readString(configFichier.toPath());
			HashMap<String, Object> ctx = new HashMap<>();
			configuration = new JsonObject();
			Yaml yaml = new Yaml();
			Map<String, Object> map = yaml.load(template);
			for(String key : map.keySet()) {
				Object val = map.get(key);
				if(val instanceof String) {
					String rendered = jinjava.render(val.toString(), ctx);
					ctx.put(key, rendered);
					configuration.put(key, rendered);
				} else if(val instanceof ArrayList) {
					List<Object> list1 = (List<Object>)val;
					JsonArray list2 = new JsonArray();
					for(Object item : list1) {
						if(item instanceof String) {
							String rendered = jinjava.render(item.toString(), ctx);
							list2.add(rendered);
						} else {
							list2.add(item);
						}
						configuration.put(key, list2);
					}
				} else {
					ctx.put(key, val);
					configuration.put(key, val);
				}
			}
		} catch(Exception ex) {
			ExceptionUtils.rethrow(ex);
		}
		return configuration;
	}

	Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");

	public ConfigSite() {
	}

	public String classeLangueNom;

	public JsonObject i18nGlobale;

	public String langueNomGlobale;
	protected void _langueNomGlobale() throws Exception {
		langueNomGlobale = System.getenv("SITE_LANG");
	}

	public String appComputate;
	protected void _appComputate() throws Exception {
		appComputate = System.getenv("COMPUTATE_SRC");
	}

	public String appComputateVertx;
	protected void _appComputateVertx() throws Exception {
		appComputateVertx = System.getenv("COMPUTATE_VERTX_SRC");
	}

	public static JsonObject getLangueConfigGlobale(Jinjava jinjava, String appComputateVertx, String langueNomGlobale) throws Exception {
		File configFichier = new File(String.format("%s/src/main/resources/org/computate/i18n/i18n_%s.yaml", appComputateVertx, langueNomGlobale));
		String template = Files.readString(configFichier.toPath());
		Yaml yaml = new Yaml();
		HashMap<String, Object> ctx = new HashMap<>();
		Map<String, Object> map = yaml.load(template);
		JsonObject configuration = new JsonObject();
		for(String key : map.keySet()) {
			Object val = map.get(key);
			if(val instanceof String) {
				String rendered = jinjava.render(val.toString(), ctx);
				ctx.put(key, rendered);
				configuration.put(key, rendered);
			} else if(val instanceof ArrayList) {
				List<Object> list1 = (List<Object>)val;
				JsonArray list2 = new JsonArray();
				for(Object item : list1) {
					if(item instanceof String) {
						String rendered = jinjava.render(item.toString(), ctx);
						list2.add(rendered);
					} else {
						list2.add(item);
					}
					configuration.put(key, list2);
				}
			} else {
				ctx.put(key, val);
				configuration.put(key, val);
			}
		}
		return configuration;
	}

	public JsonObject langueConfigGlobale;
	protected void _langueConfigGlobale() throws Exception {
		langueConfigGlobale = getLangueConfigGlobale(jinjava, appComputateVertx, langueNomGlobale);
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
		configChemin = System.getenv(i18nGlobale.getString("var_VARS_CHEMIN"));
	}

	/**
	 * The INI Configuration Object for the config file.
	 */
	public JsonObject config;

	/**
	 * r: fichierConfig r.enUS: configFile
	 **/
	protected void _config() throws Exception {
		config = getConfiguration(jinjava, i18nGlobale);
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
			siteNom = config.getString(langueConfigGlobale.getString(I18n.var_SITE_NOM));
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
			siteChemin = config.getString(langueConfigGlobale.getString(I18n.var_SITE_SRC));
	}

	public String cheminStatique;
	protected void _cheminStatique() throws Exception {
		if (cheminStatique == null)
			cheminStatique = config.getString(langueConfigGlobale.getString(I18n.var_CHEMIN_STATIQUE));
	}

	public String templateChemin;
	protected void _templateChemin() throws Exception {
		if (templateChemin == null)
			templateChemin = config.getString(langueConfigGlobale.getString(I18n.var_TEMPLATE_CHEMIN));
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
	
	public static Jinjava getJinjava() {
		JinjavaConfig jinjavaConfig = new JinjavaConfig();
		Jinjava jinjava = new Jinjava(jinjavaConfig);
		
		jinjava.registerFunction(new ELFunctionDefinition("", "lookup", ConfigSite.class, "lookup", String.class, String.class));
		jinjava.registerFunction(new ELFunctionDefinition("", "query", ConfigSite.class, "query", String.class, String.class, String.class, String.class));

		jinjava.registerFilter(new Filter() {
			@Override
			public String getName() {
				return "b64decode";
			}
			@Override
			public Object filter(Object var, JinjavaInterpreter interpreter, String... args) {
				try {
					if(var instanceof String) {
						return new String(Base64.getDecoder().decode(var.toString()));
					} else if(var instanceof byte[]) {
						return new String(Base64.getDecoder().decode((byte[])var), "UTF-8");
					}
				} catch(Exception ex) {
					try {
						return new String(new String((byte[])var, "UTF-8"));
					} catch(Exception ex2) {
						ExceptionUtils.rethrow(ex2);
					}
				}
				return null;
			}
		});

		jinjava.registerFilter(new Filter() {
			@Override
			public String getName() {
				return "basename";
			}
			@Override
			public Object filter(Object var, JinjavaInterpreter interpreter, String... args) {
				if(var != null) {
					return new File(var.toString()).getName();
				}
				return null;
			}
		});

		jinjava.registerFilter(new Filter() {
			@Override
			public String getName() {
				return "dirname";
			}
			@Override
			public Object filter(Object var, JinjavaInterpreter interpreter, String... args) {
				if(var != null) {
					return Paths.get(var.toString()).getParent().normalize().toAbsolutePath().toString();
				}
				return null;
			}
		});

		jinjava.registerFilter(new Filter() {
			@Override
			public String getName() {
				return "realpath";
			}
			@Override
			public Object filter(Object var, JinjavaInterpreter interpreter, String... args) {
				if(var != null) {
					return Paths.get(var.toString()).normalize().toAbsolutePath().toString();
				}
				return null;
			}
		});
		return jinjava;
	}

	private Jinjava jinjava;

	protected void _jinjava() throws Exception {
		jinjava = getJinjava();
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
		autresLangues = Optional.ofNullable(config
				.getJsonArray(langueConfigGlobale.getString(I18n.var_AUTRES_LANGUES)))
				.orElse(new JsonArray())
				.stream().map(o -> o.toString()).toArray(String[]::new);
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
		nomDomaine = config.getString(langueConfigGlobale.getString(I18n.var_NOM_DOMAINE));
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
		siteUrlBase = config.getString(langueConfigGlobale.getString(I18n.var_SITE_URL_BASE));
	}

	/**
	 */
	public String computateEnsembleRecherchePrefixe;

	/**
	 **/
	protected void _computateEnsembleRecherchePrefixe() throws Exception {
		computateEnsembleRecherchePrefixe = config.getString(langueConfigGlobale.getString(I18n.var_COMPUTATE_ENSEMBLE_RECHERCHE_PREFIXE), "org.computate.search org.computate.vertx ");
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
				.getString(langueConfigGlobale.getString(I18n.var_SITE_JAVA_ENSEMBLE));
		if (StringUtils.isEmpty(nomEnsembleDomaine)) {
			String[] partis = StringUtils.split(nomDomaine, ".");
			ArrayUtils.reverse(partis);
			nomEnsembleDomaine = StringUtils.join(partis, ".");
		}
	}

	public String composantsWebPrefixe;
	protected void _composantsWebPrefixe() throws Exception {
		composantsWebPrefixe = config
				.getString(langueConfigGlobale.getString(I18n.var_COMPOSANTS_WEB_PREFIXE));
		if (StringUtils.isEmpty(composantsWebPrefixe)) {
			composantsWebPrefixe = "sl-";
		}
	}

	/**
	 * Var.enUS: configFileName enUS: The name of the config file which defaults to
	 * the siteName followed by ".yaml".
	 */
	public String nomFichierConfig;

	/**
	 * Var.enUS: _configFileName r: nomFichierConfig r.enUS: configFileName r:
	 * siteNom r.enUS: siteName
	 **/
	protected void _nomFichierConfig() throws Exception {
		nomFichierConfig = config.getString(
				langueConfigGlobale.getString(I18n.var_NOM_FICHIER_CONFIG), siteNom + ".yaml");
	}

	public String siteZone;

	protected void _siteZone() throws Exception {
		siteZone = config.getString(
				langueConfigGlobale.getString(I18n.var_SITE_ZONE));
	}

	/**
	 * Var.enUS: solrUrlComputate enUS: The Solr web URL for the "computate" index.
	 */
	public String solrUrlComputate;

	public String getSolrUrlComputate() {
		return solrUrlComputate;
	}

	public void setSolrUrlComputate(String solrUrlComputate) {
		this.solrUrlComputate = solrUrlComputate;
	}

	public String solrUtilisateur;

	protected void _solrUtilisateur() throws Exception {
		solrUtilisateur = config
				.getString(langueConfigGlobale.getString(I18n.var_SOLR_UTILISATEUR));
	}

	public String solrMotDePasse;

	protected void _solrMotDePasse() throws Exception {
		solrMotDePasse = config
				.getString(langueConfigGlobale.getString(I18n.var_SOLR_MOT_DE_PASSE));
	}

	/**
	 * Var.enUS: _solrUrlComputate r: solrUrlComputate r.enUS: solrUrlComputate r:
	 * solrUrl r.enUS: solrUrl r: portSolr r.enUS: solrPort
	 **/
	protected void _solrUrlComputate() throws Exception {
		solrUrlComputate = config
				.getString(langueConfigGlobale.getString(I18n.var_SOLR_URL_COMPUTATE));
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
		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(solrUtilisateur, solrMotDePasse);
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		PreemptiveAuth requestInterceptor = new PreemptiveAuth(new BasicScheme());
		credentialsProvider.setCredentials(AuthScope.ANY, credentials);
		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(sslsf)
				.setDefaultCredentialsProvider(credentialsProvider)
				.addInterceptorLast(requestInterceptor)
				.build();
		clientSolrComputate = new HttpSolrClient.Builder(solrUrlComputate).withHttpClient(httpClient).build();
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
		siteCrypte = config.getBoolean(langueConfigGlobale.getString(I18n.var_SITE_CRYPTE),
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
		List<String> o = Optional.ofNullable(config.getJsonArray(langueConfigGlobale.getString(I18n.var_SITE_ECRIRE_METHODES)))
				.orElse(new JsonArray())
				.stream().map(i -> i.toString()).collect(Collectors.toList());
		if (o != null)
			siteEcrireMethodes.addAll(o);
	}

	public Boolean authPolitiqueGranulee;
	/**
	 **/
	protected void _authPolitiqueGranulee() throws Exception {
		authPolitiqueGranulee = config.getBoolean(langueConfigGlobale.getString(I18n.var_AUTH_POLITIQUE_GRANULEE), false);
	}

	/**
	 * Var.enUS: writeApi
	 */
	public Boolean ecrireApi;

	/**
	 * Var.enUS: _writeApi r: ecrireApi r.enUS: writeApi r: siteNom r.enUS: siteName
	 **/
	protected void _ecrireApi() throws Exception {
		ecrireApi = config.getBoolean(langueConfigGlobale.getString(I18n.var_ECRIRE_API), true);
	}

	public Boolean ecrireCommentaire;

	protected void _ecrireCommentaire() throws Exception {
		ecrireCommentaire = config
				.getBoolean(langueConfigGlobale.getString(I18n.var_ECRIRE_COMMENTAIRE), true);
	}

	public Boolean activerLog;

	protected void _activerLog() throws Exception {
		activerLog = config.getBoolean( langueConfigGlobale.getString(I18n.var_ACTIVER_LOG), true);
	}

	public Boolean activerSupprime;

	protected void _activerSupprime() throws Exception {
		activerSupprime = config.getBoolean( langueConfigGlobale.getString(I18n.var_ACTIVER_SUPPRIME), true);
	}

	public Boolean activerArchive;

	protected void _activerArchive() throws Exception {
		activerArchive = config.getBoolean(
				langueConfigGlobale.getString(I18n.var_ACTIVER_ARCHIVE), true);
	}

	public Boolean activerUtilisateurCle;

	protected void _activerUtilisateurCle() throws Exception {
		activerUtilisateurCle = config.getBoolean(
				langueConfigGlobale.getString(I18n.var_ACTIVER_UTILISATEUR_CLE),
				true);
	}

	public Boolean activerSessionId;

	protected void _activerSessionId() throws Exception {
		activerSessionId = config.getBoolean(
				langueConfigGlobale.getString(I18n.var_ACTIVER_SESSION_ID),
				true);
	}

	public Boolean activerRoleAdmin;

	protected void _activerRoleAdmin() throws Exception {
		activerRoleAdmin = config.getBoolean(langueConfigGlobale.getString(I18n.var_ACTIVER_ROLE_ADMIN), true);
	}

	public Boolean activerOpenIdConnect;

	protected void _activerOpenIdConnect() throws Exception {
		activerOpenIdConnect = config.getBoolean(
				langueConfigGlobale.getString(I18n.var_ACTIVER_OPENID_CONNECT), true);
	}

	public Boolean activerQuarkus;

	protected void _activerQuarkus() throws Exception {
		activerQuarkus = config.getBoolean(
				langueConfigGlobale.getString(I18n.var_ACTIVER_QUARKUS), false);
	}

	public Boolean activerVertx;

	protected void _activerVertx() throws Exception {
		activerVertx = config.getBoolean(
				langueConfigGlobale.getString(I18n.var_ACTIVER_VERTX), true);
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
		_langueNomGlobale();
		_appComputateVertx();
		_appComputate();
		_jinjava();
		_langueConfigGlobale();
		_configChemin();
		_config();
		_siteNom();
		_siteChemin();
		_cheminStatique();
		_templateChemin();
		_cheminSrcMainJava();
		_cheminSrcMainResources();
		_cheminSrcGenJava();
		_fichierConfig();
		_langueNomActuel();
		_autresLangues();
		_toutesLangues();
		_langueIndexe();
		_nomDomaine();
		_siteUrlBase();
		_computateEnsembleRecherchePrefixe();
		_nomDomaine();
		_nomEnsembleDomaine();
		_composantsWebPrefixe();
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
		_solrUtilisateur();
		_solrMotDePasse();
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
		_authPolitiqueGranulee();
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

	public JsonObject regexYamlObject(String champ, String texte) {
		Yaml yaml = new Yaml();
		String str = regexYamlString(champ, texte);
		if(str != null) {
			Map<String, Object> map = yaml.load(str);
			return new JsonObject(map);
		}
		return null;
	}

	public JsonArray regexYamlArray(String champ, String texte) {
		Yaml yaml = new Yaml();
		String str = regexYamlString(champ, texte);
		if(str != null) {
			List<Object> list = yaml.load(str);
			return new JsonArray(list);
		}
		return null;
	}

	public String regexYamlString(String champ, String texte) {
		String o = null;
		if (champ != null && texte != null) {
			String motif = "^" + champ + ": ?([>|-]{0,2}(\\d*)\\n)?([\\s\\S]*?)(?=\\n^\\w|\\Z)";
			Matcher m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
			boolean trouve = m.find();
			if (trouve) {
				String groupe1 = m.group(1);
				String groupe2 = m.group(2);
				String groupe3 = m.group(3);
				Integer spaces = 2;
				if(groupe2.length() > 0)
					spaces = Integer.parseInt(groupe2);
				o = groupe3.replaceAll("^" + String.join("", Collections.nCopies(spaces, " ")), "").replaceAll("\n" + String.join("", Collections.nCopies(spaces, " ")), "\n");

				if(groupe1.contains(">"))
					o = o.replaceAll("\\n([^\\n])", " $1");

				if(groupe1.contains("-"))
					o = o.replaceAll("\\n+\\Z", "");
				else if(!groupe1.contains("+"))
					o = o.replaceAll("\\n\\Z", "");
			} else {
				motif = "^" + champ + ": (.*)";
				m = Pattern.compile(motif, Pattern.MULTILINE).matcher(texte);
				trouve = m.find();
				if (trouve) {
					String groupe1 = m.group(1);
					o = groupe1;
				}
			}
		}
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
