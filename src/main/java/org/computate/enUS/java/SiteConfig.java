package org.computate.enUS.java;

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
 *	Loads the properties in the application config file into specific fields. 
 **/
public class SiteConfig {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	public SiteConfig() {
	}

	/**	
	 *	The name of the application. 
	 **/
	public String appName;

	protected void  _appName() throws Exception, Exception {
		if(appName == null)
			appName = System.getenv("appName"); 
	}

	/**	
	 *	The path to the application. 
	 **/
	public String appPath;

	protected void  _appPath() throws Exception, Exception {
		if(appPath == null)
			appPath = System.getenv("appPath"); 
	}

	/**	
	 *	The absolute path to the /src/main/java directory. 
	 **/
	public String srcMainJavaPath;

	protected void  _srcMainJavaPath() throws Exception, Exception {
		srcMainJavaPath = appPath + "/src/main/java";
	}

	/**	
	 *	The absolute path to the /src/main/resources directory. 
	 **/
	public String srcMainResourcesPath;

	protected void  _srcMainResourcesPath() throws Exception, Exception {
		srcMainResourcesPath = appPath + "/src/main/resources";
	}

	/**	
	 *	The absolute path to the /src/gen/java directory. 
	 **/
	public String srcGenJavaPath;

	protected void  _srcGenJavaPath() throws Exception, Exception {
		srcGenJavaPath = appPath + "/src/gen/java";
	}

	/**	
	 *	The absolute path to the app config file. 
	 **/
	public String configPath;

	protected void  _configPath() throws Exception, Exception {
		configPath = appPath + "/config/" + appName + ".config";
	}

	/**	
	 *	The File Object for the app config file. 
	 **/
	public File configFile;

	protected void  _configFile() throws Exception, Exception {
		configFile = new File(configPath);
	}

	/**	
	 *	The Apache Commons Configurations object for reading config files. 
	 **/
	public Configurations configurations;

	protected void  _configurations() throws Exception, Exception {
		configurations = new Configurations();
	}

	public INIConfiguration config;

	protected void  _config() throws Exception, Exception {
		config = configurations.ini(configFile);
	}

	/**	
	 *	The configured language name for this app. 
	 **/
	public String languageName;

	protected void  _languageName() throws Exception, Exception {
		languageName = config.getString(StringUtils.replace(appName, ".", "..") + ".languageName");
	}

	/**	
	 *	The actual language of the code. 
	 *	If the languageName is configured as "tout", then the languageActualName would be "frFR". 
	 **/
	public String languageActualName;

	protected void  _languageActualName() throws Exception, Exception {
//		if(StringUtils.equals(languageName, "tout"))
//			languageActualName = "frFR";
//		else
			languageActualName = languageName;
	}

	/**	
	 *	Other languages supported by this app besides the actual language name. 
	 **/
	public String[] otherLanguages;

	protected void  _otherLanguages() throws Exception, Exception {
		otherLanguages = config.getStringArray(StringUtils.replace(appName, ".", "..") + ".otherLanguages");
	}

	/**	
	 *	All language names supported in this application. 
	 **/
	public String[] allLanguages;

	protected void  _allLanguages() throws Exception, Exception {
		allLanguages = ArrayUtils.add(ArrayUtils.addAll(otherLanguages), languageName);
	}

	/**	
	 *	True if the languageName is an actual locale that can be indexed. 
	 *	If the languageName is configured as "tout", then that language would not be indexed. 
	 **/
	public Boolean languageIndexed;

	protected void  _languageIndexed() throws Exception, Exception {
		languageIndexed = ArrayUtils.contains(allLanguages, languageName);
	}

	/**	
	 *	The domain name of the website where this will be deployed (like "example.com"). 
	 **/
	public String domainName;

	protected void  _domainName() throws Exception, Exception {
		domainName = config.getString(StringUtils.replace(appName, ".", "..") + ".domainName");
	}

	/**	
	 *	The base URL to the site when deployed. 
	 **/
	public String siteBaseUrl;

	protected void  _siteBaseUrl() throws Exception, Exception {
		siteBaseUrl = config.getString(StringUtils.replace(appName, ".", "..") + ".siteBaseUrl");
	}

	/**	
	 *	The Java package name for the domain (example.com would have a package name of "com.example"). 
	 **/
	public String domainPackageName;

	protected void  _domainPackageName() throws Exception, Exception {
		domainPackageName = config.getString(StringUtils.replace(appName, ".", "..") + ".domainPackageName");
		if(StringUtils.isEmpty(domainPackageName)) {
			String[] parts = StringUtils.split(domainName, ".");
			ArrayUtils.reverse(parts);
			domainPackageName = StringUtils.join(parts, ".");
		}
	}

	/**	
	 *	The name of the config file which defaults to the appName followed by ".config". 
	 **/
	public String configFileName;

	protected void  _configFileName() throws Exception, Exception {
		configFileName = config.getString(StringUtils.replace(appName, ".", "..") + ".configFileName", appName + ".config");
	}

	/**	
	 *	The version of maven being used. 
	 **/
	public String mavenVersion;

	protected void  _mavenVersion() throws Exception, Exception {
		mavenVersion = config.getString("maven.mavenVersion", "3.5.3");
	}

	/**	
	 *	The version of Zookeeper being used. 
	 **/
	public String zookeeperVersion;

	protected void  _zookeeperVersion() throws Exception, Exception {
		zookeeperVersion = config.getString("maven.zookeeperVersion", "3.5.4");
	}

	/**	
	 *	The port prefix for zookeeper (default is "102"). 
	 **/
	public String zookeeperPortPrefix;

	protected void  _zookeeperPortPrefix() throws Exception, Exception {
		zookeeperPortPrefix = config.getString("zookeeper.zookeeperPortPrefix", "102");
	}

	/**	
	 *	The Zookeeper client port (default is "10281"). 
	 **/
	public String zookeeperClientPort;

	protected void  _zookeeperClientPort() throws Exception, Exception {
		zookeeperClientPort = config.getString("zookeeper.zookeeperClientPort", zookeeperPortPrefix + "81");
	}

	/**	
	 *	The Zookeeper admin port (default is "10280"). 
	 **/
	public String zookeeperAdminPort;

	protected void  _zookeeperAdminPort() throws Exception, Exception {
		zookeeperAdminPort = config.getString("zookeeper.zookeeperAdminPort", zookeeperPortPrefix + "80");
	}

	/**	
	 *	The version of Solr being used. 
	 **/
	public String solrVersion;

	protected void  _solrVersion() throws Exception, Exception {
		solrVersion = config.getString("solr.solrVersion", "7.3.1");
	}

	/**	
	 *	The port prefix for Solr (default is "103"). 
	 **/
	public String solrPortPrefix;

	protected void  _solrPortPrefix() throws Exception, Exception {
		solrPortPrefix = config.getString("solr.solrPortPrefix", "103");
	}

	/**	
	 *	The Solr web port (default is "10383"). 
	 **/
	public String solrPort;

	protected void  _solrPort() throws Exception, Exception {
		solrPort = config.getString("solr.solrPort", solrPortPrefix + "83");
	}

	/**	
	 *	The Solr web URL for the "computate" index. 
	 **/
	public String solrUrlComputate;

	protected void  _solrUrlComputate() throws Exception, Exception {
		solrUrlComputate = config.getString("solr.solrUrl", "http://localhost:" + solrPort + "/solr/computate");
	}

	/**	
	 *	The Solrj client for the "computate" index. 
	 **/
	public SolrClient solrClientComputate;

	protected void  _solrClientComputate() throws Exception, Exception {
		solrClientComputate = new HttpSolrClient.Builder(solrUrlComputate).build();
	}

	/**	
	 *	The absolute paths to source code directories in the app to watch for changes. 
	 **/
	public ArrayList<String> sourcePaths = new ArrayList<String>();

	protected void  _sourcePaths() throws Exception, Exception {
		sourcePaths.add(srcMainJavaPath);
		sourcePaths.add(srcGenJavaPath);
	}

	/**	
	 *	The absolute paths to source code directories in all apps to watch for changes. 
	 **/
	public ArrayList<String> allSourcePaths = new ArrayList<String>();

	protected void  _allSourcePaths() throws Exception, Exception {
		allSourcePaths.add(srcMainJavaPath);
		allSourcePaths.add(srcGenJavaPath);
	}

	/**	
	 *	The names of methods that will be tested when a source file is updated. 
	 **/
	public ArrayList<String> testMethodNames = new ArrayList<String>();

	protected void  _testMethodNames() throws Exception, Exception {
	}

	/**	
	 *	True if the data for the site should be encrypted. 
	 **/
	public Boolean siteEncrypted;

	protected void  _siteEncrypted() throws Exception, Exception {
		siteEncrypted = config.getBoolean(StringUtils.replace(appName, ".", "..") + ".siteEncrypted", false);
	}

	public ArrayList<String> siteWriteMethods = new ArrayList<String>();

	protected void  _siteWriteMethods() throws Exception, Exception {
		List<String> o = config.getList(String.class, StringUtils.replace(appName, ".", "..") + ".siteWriteMethods");
		if(o != null)
			siteWriteMethods.addAll(o);
	}

	public Boolean writeApi;

	protected void  _writeApi() throws Exception, Exception {
		writeApi = config.getBoolean(StringUtils.replace(appName, ".", "..") + ".writeApi", true);
	}

	public void  initSiteConfig() throws Exception, Exception {
		_appName();
		_appPath();
		_srcMainJavaPath();
		_srcMainResourcesPath();
		_srcGenJavaPath();
		_configPath();
		_configFile();
		_configurations();
		_config();
		_languageName();
		_languageActualName();
		_otherLanguages();
		_allLanguages();
		_languageIndexed();
		_domainName();
		_siteBaseUrl();
		_domainName();
		_domainPackageName();
		_configFileName();
		_mavenVersion();
		_zookeeperVersion();
		_zookeeperPortPrefix();
		_zookeeperClientPort();
		_zookeeperAdminPort();
		_solrVersion();
		_solrPortPrefix();
		_solrPort();
		_solrUrlComputate();
		_solrClientComputate();
		_sourcePaths();
		_allSourcePaths();
		_testMethodNames();
		_siteEncrypted();
		_siteWriteMethods();
		_writeApi();
	}

	public String regex(String pattern, String text) {
		String o = null;
		if(pattern != null && text != null) {
			Matcher m = Pattern.compile(pattern, Pattern.MULTILINE).matcher(text);
			boolean trouve = m.find();
			if(trouve)
				o = m.group(m.groupCount());
		}
		return o;
	}

	protected String regexLanguage(String languageName, String fieldNameRegex, String comment) throws Exception, Exception {
		return regexLanguage(languageName, fieldNameRegex, comment, null);
	}

	protected String regexLanguage(String languageName, String fieldNameRegex, String comment, String defaultValue) throws Exception, Exception {
		String fieldValue = null;
		if(fieldNameRegex != null && comment != null) {
			Matcher m = Pattern.compile("^" + fieldNameRegex + "(." + languageName + ")?:\\s*(.*)", Pattern.MULTILINE).matcher(comment);
			if(m.find()) {
				fieldValue = m.group(m.groupCount());
			}
		}
		if(fieldValue == null)
			fieldValue = defaultValue;
		return fieldValue;
	}

	public String regex(String pattern, String text, String defaultValue) {
		String o = null;
		if(pattern != null && text != null) {
			Matcher m = Pattern.compile(pattern, Pattern.MULTILINE).matcher(text);
			boolean trouve = m.find();
			if(trouve)
				o = m.group(m.groupCount());
		}
		if(StringUtils.isEmpty(o))
			return defaultValue;
		else
			return o;
	}

	public String regex(String pattern, String text, Integer group) {
		String o = null;
		if(pattern != null && text != null) {
			Matcher m = Pattern.compile(pattern, Pattern.MULTILINE).matcher(text);
			boolean found = m.find();
			if(found)
				o = m.group(group);
		}
		return o;
	}

	public boolean regexFound(String pattern, String text) {
		boolean found = false;
		if(pattern != null && text != null) {
			Matcher m = Pattern.compile(pattern, Pattern.MULTILINE).matcher(text);
			found = m.find();
		}
		return found;
	}

	public ArrayList<String> regexList(String pattern, String text) {
		ArrayList<String> results = new ArrayList<String>();
		String o = null;
		if(pattern != null && text != null) {
			Matcher m = Pattern.compile(pattern, Pattern.MULTILINE).matcher(text);
			boolean found = m.find();
			while(found) {
				o = m.group(m.groupCount());
				results.add(o); 
				found = m.find();
			}
		}
		return results;
	}

	public String regexReplaceAll(String comment, String sourceCode, String languageName) throws Exception, Exception {
		String sourceCodeLanguage = sourceCode;
		if(!StringUtils.isEmpty(comment)) {
			Matcher m = Pattern.compile("^[Rr](egex)?:\\s*(.*)((?!\\nr:)[\\s\\S])*?\\nr\\." + languageName + ":\\s*(.*)", Pattern.MULTILINE).matcher(comment);
			boolean found = m.find();
			
			while(found) {
				String textRegex = m.group(1);
				String searchText = m.group(2);
				String replacementText = m.group(4);
				if(searchText != null && replacementText != null) {
					String patternRegex = null;

					if("egex".equals(textRegex))
						patternRegex = searchText;
					else
						patternRegex = Pattern.quote(searchText);

					Matcher m2 = Pattern.compile(patternRegex, Pattern.MULTILINE).matcher(sourceCodeLanguage);
					boolean found2 = m2.find();
					StringBuffer end2 = new StringBuffer();
					
					while(found2) {
						m2.appendReplacement(end2, replacementText);
						found2 = m2.find();
					}
					m2.appendTail(end2);
					sourceCodeLanguage = end2.toString();
				}

				found = m.find();
			}
		}
		return sourceCodeLanguage;
	}

	public String concat(String...values) throws Exception, Exception { 
		String result = Stream.of(values).collect(Collectors.joining());
		return result;
	}
}
