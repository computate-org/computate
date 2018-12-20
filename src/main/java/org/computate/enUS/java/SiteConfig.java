package org.computate.enUS.java;

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
 *	Loads the properties in the application config file into specific fields. 
 **/
public class SiteConfig {

	/**	
	 *	The name of the application.
	 **/
	public String appName;

	/**	
	 *	The path to the application.
	 **/
	public String appPath;

	/**	
	 *	The absolute path to the /src/main/java directory.
	 **/
	public String srcMainJavaPath;

	/**	
	 *	The absolute path to the /src/gen/java directory.
	 **/
	public String srcGenJavaPath;

	/**	
	 *	The absolute path to the app config file.
	 **/
	public String configPath;

	/**	
	 *	The File Object for the app config file.
	 **/
	public File configFile;

	/**	
	 *	The Apache Commons Configurations object for reading config files.
	 **/
	public Configurations configurations;

	public INIConfiguration config;

	/**	
	 *	The configured language name for this app.
	 **/
	public String languageName;

	/**	
	 *	The actual language of the code. 
	 *	If the languageName is configured as "tout", then the languageActualName would be "frFR".
	 **/
	public String languageActualName;

	/**	
	 *	Other languages supported by this app besides the actual language name.
	 **/
	public String[] otherLanguages;

	/**	
	 *	All language names supported in this application.
	 **/
	public String[] allLanguages;

	/**	
	 *	True if the languageName is an actual locale that can be indexed. 
	 *	If the languageName is configured as "tout", then that language would not be indexed.
	 **/
	public Boolean languageIndexed;

	/**	
	 *	The domain name of the website where this will be deployed (like "example.com").
	 **/
	public String domainName;

	/**	
	 *	The Java package name for the domain (example.com would have a package name of "com.example").
	 **/
	public String domainPackageName;

	/**	
	 *	The name of the config file which defaults to the appName followed by ".config".
	 **/
	public String configFileName;

	/**	
	 *	The version of maven being used.
	 **/
	public String mavenVersion;

	/**	
	 *	The version of Zookeeper being used.
	 **/
	public String zookeeperVersion;

	/**	
	 *	The port prefix for zookeeper (default is "102").
	 **/
	public String zookeeperPortPrefix;

	/**	
	 *	The Zookeeper client port (default is "10281").
	 **/
	public String zookeeperClientPort;

	/**	
	 *	The Zookeeper admin port (default is "10280").
	 **/
	public String zookeeperAdminPort;

	/**	
	 *	The version of Solr being used.
	 **/
	public String solrVersion;

	/**	
	 *	The port prefix for Solr (default is "103").
	 **/
	public String solrPortPrefix;

	/**	
	 *	The Solr web port (default is "10383").
	 **/
	public String solrPort;

	/**	
	 *	The Solr web URL for the "computate" index.
	 **/
	public String solrUrlComputate;

	/**	
	 *	The Solrj client for the "computate" index.
	 **/
	public SolrClient solrClientComputate;

	/**	
	 *	The absolute paths to source code directories in the app to watch for changes.
	 **/
	public ArrayList<String> sourcePaths = new ArrayList<String>();

	/**	
	 *	The absolute paths to source code directories in all apps to watch for changes.
	 **/
	public ArrayList<String> allSourcePaths = new ArrayList<String>();

	/**	
	 *	The names of methods that will be tested when a source file is updated.
	 **/
	public ArrayList<String> testMethodNames = new ArrayList<String>();

	/**	
	 *	True if the data for the site should be encrypted.
	 **/
	public Boolean siteEncrypted;

	protected void  _appName() throws Exception, Exception {
		if(appName == null)
			appName = System.getenv("appName"); 
	}

	protected void  _appPath() throws Exception, Exception {
		if(appPath == null)
			appPath = System.getenv("appPath"); 
	}

	protected void  _srcMainJavaPath() throws Exception, Exception {
		srcMainJavaPath = appPath + "/src/main/java";
	}

	protected void  _srcGenJavaPath() throws Exception, Exception {
		srcGenJavaPath = appPath + "/src/gen/java";
	}

	protected void  _configPath() throws Exception, Exception {
		configPath = appPath + "/config/" + appName + ".config";
	}

	protected void  _configFile() throws Exception, Exception {
		configFile = new File(configPath);
	}

	protected void  _configurations() throws Exception, Exception {
		configurations = new Configurations();
	}

	protected void  _config() throws Exception, Exception {
		config = configurations.ini(configFile);
	}

	protected void  _languageName() throws Exception, Exception {
		languageName = config.getString(StringUtils.replace(appName, ".", "..") + ".languageName");
	}

	protected void  _languageActualName() throws Exception, Exception {
//		if(StringUtils.equals(languageName, "tout"))
//			languageActualName = "frFR";
//		else
			languageActualName = languageName;
	}

	protected void  _otherLanguages() throws Exception, Exception {
		otherLanguages = config.getStringArray(StringUtils.replace(appName, ".", "..") + ".otherLanguages");
	}

	protected void  _allLanguages() throws Exception, Exception {
		allLanguages = ArrayUtils.add(ArrayUtils.addAll(otherLanguages), languageName);
	}

	protected void  _languageIndexed() throws Exception, Exception {
		languageIndexed = ArrayUtils.contains(allLanguages, languageName);
	}

	protected void  _domainName() throws Exception, Exception {
		domainName = config.getString(StringUtils.replace(appName, ".", "..") + ".domainName");
	}

	protected void  _domainPackageName() throws Exception, Exception {
		domainPackageName = config.getString(StringUtils.replace(appName, ".", "..") + ".domainPackageName");
		if(StringUtils.isEmpty(domainPackageName)) {
			String[] parts = StringUtils.split(domainName, ".");
			ArrayUtils.reverse(parts);
			domainPackageName = StringUtils.join(parts, ".");
		}
	}

	protected void  _configFileName() throws Exception, Exception {
		configFileName = config.getString(StringUtils.replace(appName, ".", "..") + ".configFileName", appName + ".config");
	}

	protected void  _mavenVersion() throws Exception, Exception {
		mavenVersion = config.getString("maven.mavenVersion", "3.5.3");
	}

	protected void  _zookeeperVersion() throws Exception, Exception {
		zookeeperVersion = config.getString("maven.zookeeperVersion", "3.5.4");
	}

	protected void  _zookeeperPortPrefix() throws Exception, Exception {
		zookeeperPortPrefix = config.getString("zookeeper.zookeeperPortPrefix", "102");
	}

	protected void  _zookeeperClientPort() throws Exception, Exception {
		zookeeperClientPort = config.getString("zookeeper.zookeeperClientPort", zookeeperPortPrefix + "81");
	}

	protected void  _zookeeperAdminPort() throws Exception, Exception {
		zookeeperAdminPort = config.getString("zookeeper.zookeeperAdminPort", zookeeperPortPrefix + "80");
	}

	protected void  _solrVersion() throws Exception, Exception {
		solrVersion = config.getString("solr.solrVersion", "7.3.1");
	}

	protected void  _solrPortPrefix() throws Exception, Exception {
		solrPortPrefix = config.getString("solr.solrPortPrefix", "103");
	}

	protected void  _solrPort() throws Exception, Exception {
		solrPort = config.getString("solr.solrPort", solrPortPrefix + "83");
	}

	protected void  _solrUrlComputate() throws Exception, Exception {
		solrUrlComputate = config.getString("solr.solrUrl", "http://localhost:" + solrPort + "/solr/computate");
	}

	protected void  _solrClientComputate() throws Exception, Exception {
		solrClientComputate = new HttpSolrClient.Builder(solrUrlComputate).build();
	}

	protected void  _sourcePaths() throws Exception, Exception {
		sourcePaths.add(srcMainJavaPath);
		sourcePaths.add(srcGenJavaPath);
	}

	protected void  _allSourcePaths() throws Exception, Exception {
		allSourcePaths.add(srcMainJavaPath);
		allSourcePaths.add(srcGenJavaPath);
	}

	protected void  _testMethodNames() throws Exception, Exception {
	}

	protected void  _siteEncrypted() throws Exception, Exception {
		siteEncrypted = config.getBoolean(StringUtils.replace(appName, ".", "..") + ".siteEncrypted", false);
	}

	public void  initSiteConfig() throws Exception, Exception {
		_appName();
		_appPath();
		_srcMainJavaPath();
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
	}

	public String regex(String pattern, String text) {
		String o = regex(pattern, text, 1);
		return o;
	}

	public String regex(String pattern, String text, String defaultValue) {
		String o = regex(pattern, text, 1);
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
				o = m.group(1);
				results.add(o); 
				found = m.find();
			}
		}
		return results;
	}

	public String regexReplaceAll(String comment, String sourceCode, String languageName) throws Exception, Exception {
		String sourceCodeLanguage = sourceCode;
		if(!StringUtils.isEmpty(comment)) {
			Matcher m = Pattern.compile("^r:\\s*(.*)((?!\\nr:)[\\s\\S])*?\\nr\\." + languageName + ":\\s*(.*)", Pattern.MULTILINE).matcher(comment);
			boolean found = m.find();
			
			while(found) {
				String searchText = m.group(1);
				String replacementText = m.group(3);
				if(searchText != null && replacementText != null) {
					Matcher m2 = Pattern.compile(Pattern.quote(searchText), Pattern.MULTILINE).matcher(sourceCodeLanguage);
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
