package org.computate.frFR.java;

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

/**   
 * nomCanonique.enUS: org.computate.enUS.java.WatchClassBase
 */   
public class RegarderClasseBase extends ConfigSite { 

	protected String[] args;

	/**
	 * var.enUS: _appPath
	 * r: appliChemin
	 * r.enUS: appPath
	 */ 
	@Override protected void _appliChemin() throws Exception {
		appliChemin = args[0]; 
	}

	/**
	 * var.enUS: absoluteClassPath
	 */ 
	protected String classeCheminAbsolu;
	/** 
	 * var.enUS: _absoluteClassPath
	 * r: classeCheminAbsolu
	 * r.enUS: absoluteClassPath
	 */  
	protected void _classeCheminAbsolu() throws Exception {
		classeCheminAbsolu = args[1];
	}
	
	/** 
	 * var.enUS: appPaths
	 */
	protected HashMap<String, String> appliChemins = new HashMap<String, String>(); 
	/** 
	 * var.enUS: _appPaths
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: langueNom
	 * r.enUS: languageName
	 * r: appliCheminLange
	 * r.enUS: appPathLanguage
	 * r: appliNom
	 * r.enUS: appName
	 * r: appliCheminLangue
	 * r.enUS: appPathLanguage
	 * r: appliChemin
	 * r.enUS: appPath
	 */  
	protected void _appliChemins() throws Exception {
		for(String langueNom : autresLangues) { 
			String appliCheminLangue = config.getString(appliNom + ".appliChemin_" + langueNom); 
			if(StringUtils.isEmpty(appliCheminLangue)) {
				appliChemins.put(langueNom, appliChemin);
			}
			else {
				appliChemins.put(langueNom, appliCheminLangue);
			}
		}
	}

	/**  
	 * var.enUS: classDocs
	 */
	protected HashMap<String, SolrDocument> classeDocs = new HashMap<String, SolrDocument>();
	/**
	 * var.enUS: _classDocs
	 */
	protected void _classeDocs() throws Exception {
	}

	/** 
	 * var.enUS: classParts
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 */
	protected HashMap<String, ClasseParts> classeParts = new HashMap<String, ClasseParts>();
	/**
	 * var.enUS: _classParts
	 */
	protected void _classeParts() throws Exception {
	}

	/**
	 * var.enUS: builder
	 */ 
	protected JavaProjectBuilder bricoleur;
	/**
	 * var.enUS: _builder
	 * r: bricoleur
	 * r.enUS: builder
	 * r: cheminSource
	 * r.enUS: sourcePath
	 * r: toutCheminsSource
	 * r.enUS: allSourcePaths
	 * r: repertoireSource
	 * r.enUS: sourceDir
	 */
	protected void _bricoleur() throws Exception { 
		bricoleur = new JavaProjectBuilder();
		for(String cheminSource : toutCheminsSource) {
			File repertoireSource = new File(cheminSource);
			bricoleur.addSourceFolder(repertoireSource);
		}
	} 


	/**
	 * var.enUS: initWatchClassBase
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: classeCheminAbsolu
	 * r.enUS: absoluteClassPath
	 * r: appliChemins
	 * r.enUS: appPaths
	 * r: classeDocs
	 * r.enUS: classDocs
	 * r: classeParts
	 * r.enUS: classParts
	 * r: bricoleur
	 * r.enUS: builder
	 */
	public void initRegarderClasseBase() throws Exception {
		initConfigSite();
		_classeCheminAbsolu();
		_appliChemins();
		_classeDocs();
		_classeParts();
		_bricoleur();
	} 

	////////////
	// etend //
	////////////
	
	/**
	 * var.enUS: extendClass
	 * param1.var.enUS: classQdox
	 * param2.var.enUS: canonicalName
	 * r: resultat
	 * r.enUS: result
	 * r:etendClasse
	 * r.enUS: extendClass
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 */
	protected boolean etendClasse(JavaClass classeQdox, String nomCanonique) {
		boolean resultat = etendClasse(nomCanonique, classeQdox);
		return resultat;
	}
	
	/**
	 * var.enUS: extendClass
	 * param1.var.enUS: canonicalNameSearch
	 * param2.var.enUS: canonicalNameActual
	 * r: bricoleur
	 * r.enUS: builder
	 * r:etendClasse
	 * r.enUS: extendClass
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: nomCanoniqueRecherche
	 * r.enUS: canonicalNameSearch
	 * r: nomCanoniqueActuel
	 * r.enUS: canonicalNameActual
	 */
	protected boolean etendClasse(String nomCanoniqueRecherche, String nomCanoniqueActuel) {
		JavaClass classeQdox = bricoleur.getClassByName(nomCanoniqueActuel);
		return etendClasse(nomCanoniqueRecherche, classeQdox);
	}

	/**
	 * var.enUS: extendClass
	 * param1.var.enUS: canonicalName
	 * param2.var.enUS: classQdox
	 * r: bricoleur
	 * r.enUS: builder
	 * r:etendClasse
	 * r.enUS: extendClass
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 */
	protected boolean etendClasse(String nomCanonique, JavaClass classeQdox) {
		if(nomCanonique != null && classeQdox != null) {
			if(classeQdox.getCanonicalName().equals(Object.class.getCanonicalName()))
				return false;
			if(classeQdox.getCanonicalName().equals(nomCanonique))
				return true;
			else if(!classeQdox.equals(classeQdox.getSuperClass()))
				return etendClasse(nomCanonique, classeQdox.getSuperJavaClass());
		}
		return false;
	}

	//////////////
	// contient //
	//////////////
	
	/**
	 * var.enUS: containsField
	 * param1.var.enUS: qdoxSuperClassesAndMe
	 * param2.var.enUS: fieldName
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: bricoleur
	 * r.enUS: builder
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: qdoxSuperClassesAndMe
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: contientChamp
	 * r.enUS: containsField
	 */
	public Boolean contientChamp(List<JavaClass> classesSuperQdoxEtMoi, String nomChamp, Class<?> c) {
		JavaClass classeQdox = bricoleur.getClassByName(c.getCanonicalName());
		Boolean o = contientChamp(classesSuperQdoxEtMoi, nomChamp, classeQdox);
		return o;
	} 
	
	/**
	 * var.enUS: containsField
	 * param1.var.enUS: qdoxSuperClassesAndMe
	 * param2.var.enUS: fieldName
	 * param3.var.enUS: paramsArray
	 * r: tableauParams
	 * r.enUS: paramsArray
	 * r: listeParams
	 * r.enUS: paramsList
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: qdoxSuperClassesAndMe
	 * r: nomChamp
	 * r.enUS: fieldName
	 * r: classeSuper
	 * r.enUS: superClass
	 * r: methodeQdox
	 * r.enUS: qdoxMethod
	 * r: champQdox
	 * r.enUS: qdoxField
	 */   
	public Boolean contientChamp(List<JavaClass> classesSuperQdoxEtMoi, String nomChamp, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		for(JavaClass classeSuper : classesSuperQdoxEtMoi) {
			JavaField champQdox = classeSuper.getFieldByName(nomChamp);
			JavaMethod methodeQdox = classeSuper.getMethod("_" + nomChamp, listeParams, false);
			Boolean o = champQdox != null || methodeQdox != null;
			if(o)
				return true;
		}
		return false;
	}

	/**
	 * var.enUS: containsMethodAlone
	 * param1.var.enUS: classQdox
	 * param2.var.enUS: methodName
	 * param3.var.enUS: paramsArray
	 * r: obtenirMethodeSeul
	 * r.enUS: obtainMethodAlone
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: tableauParams
	 * r.enUS: paramsArray
	 */
	public Boolean contientMethodeSeul(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		JavaMethod o = obtenirMethodeSeul(classeQdox, nomMethode, tableauParams);
		return o != null;
	}

	/**
	 * var.enUS: containsMethod
	 * param1.var.enUS: classQdox
	 * param2.var.enUS: methodName
	 * param3.var.enUS: paramsArray
	 * r: obtenirMethode
	 * r.enUS: obtainMethod
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: tableauParams
	 * r.enUS: paramsArray
	 */
	public Boolean contientMethode(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		JavaMethod o = obtenirMethode(classeQdox, nomMethode, tableauParams);
		return o != null;
	}

	/**
	 * var.enUS: containsAttribute
	 * param1.var.enUS: domainPackageName
	 * param2.var.enUS: attributeName
	 * param3.var.enUS: attributeClassQdox
	 * r: classeAttributQdox
	 * r.enUS: attributeClassQdox
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: nomAttribut
	 * r.enUS: attributeName
	 * r: attributQdox
	 * r.enUS: attributeQdox
	 * r: contientAttribut
	 * r.enUS: containsAttribute
	 */
	public Boolean contientAttribut(String nomEnsembleDomaine, String nomAttribut, JavaClass classeAttributQdox) {
		Boolean o = false;
		if(classeAttributQdox.getCanonicalName().startsWith(nomEnsembleDomaine.toString())) {
			JavaField attributQdox = classeAttributQdox.getFieldByName(nomAttribut);
			if(attributQdox == null) {
				o = contientAttribut(nomEnsembleDomaine, nomAttribut, classeAttributQdox.getSuperJavaClass());
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

	/////////////
	// obtenir //
	/////////////
	
	/**
	 * var.enUS: obtainMethod
	 * param1.var.enUS: classQdox
	 * param2.var.enUS: methodName
	 * param3.var.enUS: paramsArray
	 * r: listeParams
	 * r.enUS: paramsList
	 * r: tableauParams
	 * r.enUS: paramsArray
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: classeQdox
	 * r.enUS: classQdox
	 */
	public JavaMethod obtenirMethode(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		JavaMethod methode = classeQdox.getMethodBySignature(nomMethode, listeParams, true);
		return methode;
	}
	
	/**
	 * var.enUS: obtainMethod
	 * param1.var.enUS: superClassAndMeQdox
	 * param2.var.enUS: methodName
	 * param3.var.enUS: paramsArray
	 * r: listeParams
	 * r.enUS: paramsList
	 * r: tableauParams
	 * r.enUS: paramsArray
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: classesSuperQdoxEtMoi
	 * r.enUS: superClassAndMeQdox
	 * r: classesSuperQdox
	 * r.enUS: superClassQdox
	 */
	public JavaMethod obtenirMethode(List<JavaClass> classesSuperQdoxEtMoi, String nomMethode, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		for(JavaClass classeSuperQdox : classesSuperQdoxEtMoi) {
			JavaMethod methode = classeSuperQdox.getMethodBySignature(nomMethode, listeParams);
			if(methode != null)
				return methode;
		}
		return null;
	}
	
	/**
	 * var.enUS: obtainMethodAlone
	 * param1.var.enUS: classQdox
	 * param2.var.enUS: methodName
	 * param3.var.enUS: paramsArray
	 * r: listeParams
	 * r.enUS: paramsList
	 * r: tableauParams
	 * r.enUS: paramsArray
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: classeQdox
	 * r.enUS: classQdox
	 */
	public JavaMethod obtenirMethodeSeul(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		JavaMethod methode = classeQdox.getMethodBySignature(nomMethode, listeParams, false);
		return methode;
	}
}
