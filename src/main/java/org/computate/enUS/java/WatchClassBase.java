package org.computate.enUS.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;
import org.computate.enUS.java.SiteConfig;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaType;

public class WatchClassBase extends SiteConfig {

	protected String[] args;

	protected String absoluteClassPath;

	protected HashMap<String, String> appPaths = new HashMap<String, String>();

	protected HashMap<String, SolrDocument> classDocs = new HashMap<String, SolrDocument>();

	protected HashMap<String, ClasseParts> classParts = new HashMap<String, ClassParts>();

	protected JavaProjectBuilder builder;

	protected void  _appPath() throws Exception {
		appPath = args[0]; 
	}

	protected void  _absoluteClassPath() throws Exception {
		absoluteClassPath = args[1];
	}

	protected void  _appPaths() throws Exception {
		for(String languageName : otherLanguages) { 
			String appPathLanguage = config.getString(appName + ".appPath_" + languageName); 
			if(StringUtils.isEmpty(appPathLanguage)) {
				appPaths.put(languageName, appPath);
			}
			else {
				appPaths.put(languageName, appPathLanguage);
			}
		}
	}

	protected void  _classDocs() throws Exception {
	}

	protected void  _classParts() throws Exception {
	}

	protected void  _builder() throws Exception { 
		builder = new JavaProjectBuilder();
		for(String sourcePath : allSourcePaths) {
			File sourceDir = new File(sourcePath);
			builder.addSourceFolder(sourceDir);
		}
	}

	public void  initWatchClassBase() throws Exception {
		initSiteConfig();
		_absoluteClassPath();
		_appPaths();
		_classDocs();
		_classParts();
		_builder();
	}

	protected boolean extendClass(JavaClass classQdox, String canonicalName) {
		boolean result = extendClass(canonicalName, classQdox);
		return result;
	}

	protected boolean extendClass(String canonicalNameSearch, String canonicalNameActual) {
		JavaClass classQdox = builder.getClassByName(canonicalNameActual);
		return extendClass(canonicalNameSearch, classQdox);
	}

	protected boolean extendClass(String canonicalName, JavaClass classQdox) {
		if(canonicalName != null && classQdox != null) {
			if(classQdox.getCanonicalName().equals(Object.class.getCanonicalName()))
				return false;
			if(classQdox.getCanonicalName().equals(canonicalName))
				return true;
			else if(!classQdox.equals(classQdox.getSuperClass()))
				return extendClass(canonicalName, classQdox.getSuperJavaClass());
		}
		return false;
	}

	public Boolean containsField(List<JavaClass> qdoxSuperClassesAndMe, String fieldName, Class<?> c) {
		JavaClass classQdox = builder.getClassByName(c.getCanonicalName());
		Boolean o = containsField(qdoxSuperClassesAndMe, fieldName, classQdox);
		return o;
	}

	public Boolean containsField(List<JavaClass> qdoxSuperClassesAndMe, String fieldName, JavaClass...paramsArray) {
		ArrayList<JavaType> paramsList = new ArrayList<JavaType>();
		Collections.addAll(paramsList, paramsArray);
		for(JavaClass superClass : qdoxSuperClassesAndMe) {
			JavaField qdoxField = superClass.getFieldByName(fieldName);
			JavaMethod qdoxMethod = superClass.getMethod("_" + fieldName, paramsList, false);
			Boolean o = qdoxField != null || qdoxMethod != null;
			if(o)
				return true;
		}
		return false;
	}

	public Boolean containsMethodAlone(JavaClass classQdox, String methodName, JavaClass...paramsArray) {
		JavaMethod o = obtainMethodAlone(classQdox, methodName, paramsArray);
		return o != null;
	}

	public Boolean containsMethod(JavaClass classQdox, String methodName, JavaClass...paramsArray) {
		JavaMethod o = obtainMethod(classQdox, methodName, paramsArray);
		return o != null;
	}

	public Boolean containsAttribute(String domainPackageName, String attributeName, JavaClass attributeClassQdox) {
		Boolean o = false;
		if(attributeClassQdox.getCanonicalName().startsWith(domainPackageName.toString())) {
			JavaField attributeQdox = attributeClassQdox.getFieldByName(attributeName);
			if(attributeQdox == null) {
				o = containsAttribute(domainPackageName, attributeName, attributeClassQdox.getSuperJavaClass());
			}
			else {
				o = true;
			}
		}
		else {
			o = false;
		}
		return o;
	}

	public JavaMethod obtainMethod(JavaClass classQdox, String methodName, JavaClass...paramsArray) {
		ArrayList<JavaType> paramsList = new ArrayList<JavaType>();
		Collections.addAll(paramsList, paramsArray);
		JavaMethod methode = classQdox.getMethodBySignature(methodName, paramsList, true);
		return methode;
	}

	public JavaMethod obtainMethod(List<JavaClass> superClassAndMeQdox, String methodName, JavaClass...paramsArray) {
		ArrayList<JavaType> paramsList = new ArrayList<JavaType>();
		Collections.addAll(paramsList, paramsArray);
		for(JavaClass classeSuperQdox : superClassAndMeQdox) {
			JavaMethod methode = classeSuperQdox.getMethodBySignature(methodName, paramsList);
			if(methode != null)
				return methode;
		}
		return null;
	}

	public JavaMethod obtainMethodAlone(JavaClass classQdox, String methodName, JavaClass...paramsArray) {
		ArrayList<JavaType> paramsList = new ArrayList<JavaType>();
		Collections.addAll(paramsList, paramsArray);
		JavaMethod methode = classQdox.getMethodBySignature(methodName, paramsList, false);
		return methode;
	}
}
