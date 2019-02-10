package org.computate.enUS.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaType;

public class WatchClassBase extends SiteConfig {

	/**
	 * r: contexteCe
	 * r.enUS: contextThis
	 * r: contexteCeNom
	 * r.enUS: contextThisName
	 * r: contexteUn
	 * r.enUS: contextA
	 * r: contexteUnNom
	 * r.enUS: contextAName
	 * r: contexteLeNom
	 * r.enUS: contextTheName
	 * r: contexteNomSingulier
	 * r.enUS: contextNameSingular
	 * r: contexteNomPluriel
	 * r.enUS: contextNamePlural
	 * r: contexteNomActuel
	 * r.enUS: contextActualName
	 * r: contexteTous
	 * r.enUS: contextAll
	 * r: contexteTousNom
	 * r.enUS: contextAllName
	 * r: contexteAucunNomTrouve
	 * r.enUS: contextNoneNameFound
	 * r: contexteNomVar
	 * r.enUS: contextNameVar
	 * r: contexteDeNom
	 * r.enUS: contextOfName
	 * r: contexteNom
	 * r.enUS: contextName
	 * r: UnNomAdjectif
	 * r.enUS: ANameAdjective
	 * r: LeNomAdjectif
	 * r.enUS: TheNameAdjective
	 * r: AdjectifAvant
	 * r.enUS: AdjectiveBefore
	 * r: NomAdjectifSingulier
	 * r.enUS: NameAdjectiveSingular
	 * r: NomAdjectifPluriel
	 * r.enUS: NameAdjectivePlural
	 * r: PlurielNomAdjectif
	 * r.enUS: PluralNameAdjective
	 * r: SingulierNomAdjectif
	 * r.enUS: SingularNameAdjective
	 * r: LeNomAdjectif
	 * r.enUS: TheAdjectiveName
	 * r: UnNomAdjectif
	 * r.enUS: AnAdjectiveName
	 * r: AdjectifVar
	 * r.enUS: AdjectiveVar
	 * r: Adjectif
	 * r.enUS: Adjective
	 * r: CONTEXTE_UnMasculinVoyelle
	 * r.enUS: CONTEXT_AMaleVowel
	 * r: CONTEXTE_UnFemininVoyelle
	 * r.enUS: CONTEXT_AFemaleVowel
	 * r: CONTEXTE_UnMasculinConsonne
	 * r.enUS: CONTEXT_AMaleConsonant
	 * r: CONTEXTE_UnFemininConsonne
	 * r.enUS: CONTEXT_AFemaleConsonant
	 * r: CONTEXTE_CetMasculinVoyelle
	 * r.enUS: CONTEXT_ThisMaleVowel
	 * r: CONTEXTE_CetteFemininVoyelle
	 * r.enUS: CONTEXT_ThisFemaleVowel
	 * r: CONTEXTE_CeMasculinConsonne
	 * r.enUS: CONTEXT_ThisMaleConsonant
	 * r: CONTEXTE_CetteFemininConsonne
	 * r.enUS: CONTEXT_ThisFemaleConsonant
	 * r: CONTEXTE_CesPluriel
	 * r.enUS: CONTEXT_ThesePlural
	 * r: CONTEXTE_LMasculinVoyelle
	 * r.enUS: CONTEXT_TheMaleVowel
	 * r: CONTEXTE_LFemininVoyelle
	 * r.enUS: CONTEXT_TheFemaleVowel
	 * r: CONTEXTE_LeMasculinConsonne
	 * r.enUS: CONTEXT_TheMaleConsonant
	 * r: CONTEXTE_LaFemininConsonne
	 * r.enUS: CONTEXT_TheFemaleConsonant
	 * r: CONTEXTE_LesPluriel
	 * r.enUS: CONTEXT_ThePlural
	 * r: CONTEXTE_ActuelMasculinAvant
	 * r.enUS: CONTEXT_CurrentMaleBefore
	 * r: CONTEXTE_ActuelleFemininAvant
	 * r.enUS: CONTEXT_CurrentFemaleBefore
	 * r: CONTEXTE_ActuelMasculinApres
	 * r.enUS: CONTEXT_CurrentMaleAfter
	 * r: CONTEXTE_ActuelleFemininApres
	 * r.enUS: CONTEXT_CurrentFemaleAfter
	 * r: CONTEXTE_TousMasculinPluriel
	 * r.enUS: CONTEXT_AllMalePlural
	 * r: CONTEXTE_ToutesFemininPluriel
	 * r.enUS: CONTEXT_AllFemalePlural
	 * r: CONTEXTE_AucunTrouveMasculinAvant
	 * r.enUS: CONTEXT_NoneFoundMaleBefore
	 * r: CONTEXTE_AucuneTrouveFemininAvant
	 * r.enUS: CONTEXT_NoneFemaleBefore
	 * r: CONTEXTE_AucunTrouveMasculinApres
	 * r.enUS: CONTEXT_NoneFoundMaleAfter
	 * r: CONTEXTE_AucuneTrouveFemininApres
	 * r.enUS: CONTEXT_NoneFemaleAfter
	 * r: CONTEXTE_DVoyelle
	 * r.enUS: CONTEXT_OfVowel
	 * r: CONTEXTE_DeConsonne
	 * r.enUS: CONTEXT_OfConsonant
	 * r: CONTEXTE_AdjectifPluriel
	 * r.enUS: CONTEXT_AdjectivePlural
	 */
	public String[] args;

	public String classAbsolutePath;

	public HashMap<String, String> appPaths = new HashMap<String, String>();

	public HashMap<String, SolrDocument> classDocs = new HashMap<String, SolrDocument>();

	public HashMap<String, ClassParts> classPartsGen = new HashMap<String, ClassParts>();

	public JavaProjectBuilder builder;

	@Override()
	protected void  _appPath() throws Exception, Exception {
		appPath = System.getenv("appPath"); 
		if(appPath == null)
			appPath = args[0]; 
	}

	protected void  _classAbsolutePath() throws Exception, Exception {
		classAbsolutePath = System.getenv("classAbsolutePath"); 
		if(classAbsolutePath == null)
			classAbsolutePath = args[1];
	}

	protected void  _appPaths() throws Exception, Exception {
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

	protected void  _classDocs() throws Exception, Exception {
	}

	protected void  _classPartsGen() throws Exception, Exception {
	}

	protected void  _builder() throws Exception, Exception { 
		builder = new JavaProjectBuilder();
		for(String sourcePath : allSourcePaths) {
			File sourceDir = new File(sourcePath);
			builder.addSourceFolder(sourceDir);
		}
	}

	public void  initWatchClassBase() throws Exception, Exception {
		initSiteConfig();
		_classAbsolutePath();
		_appPaths();
		_classDocs();
		_classPartsGen();
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
		JavaMethod method = classQdox.getMethodBySignature(methodName, paramsList, true);
		return method;
	}

	public JavaMethod obtainMethod(List<JavaClass> superClassAndMeQdox, String methodName, JavaClass...paramsArray) {
		ArrayList<JavaType> paramsList = new ArrayList<JavaType>();
		Collections.addAll(paramsList, paramsArray);
		for(JavaClass superClassQdox : superClassAndMeQdox) {
			JavaMethod method = superClassQdox.getMethodBySignature(methodName, paramsList);
			if(method != null)
				return method;
		}
		return null;
	}

	public JavaMethod obtainMethodAlone(JavaClass classQdox, String methodName, JavaClass...paramsArray) {
		ArrayList<JavaType> paramsList = new ArrayList<JavaType>();
		Collections.addAll(paramsList, paramsArray);
		JavaMethod method = classQdox.getMethodBySignature(methodName, paramsList, false);
		return method;
	}
}
