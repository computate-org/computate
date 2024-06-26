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
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaType;

import io.vertx.core.json.JsonObject;

/**   
 * NomCanonique.enUS: org.computate.enUS.java.WatchClassBase
 */   
public class RegarderClasseBase extends ConfigSite {

	public String[] args;
	public void setArgs(String[] args) {
		this.args = args;
	}

	/**
	 * Description: A helper method for generating a URL friendly unique ID for this object
	 */
	public String genererId(String s) {
		if(s != null) {
			s = Normalizer.normalize(s, Normalizer.Form.NFD);
			s = StringUtils.lowerCase(s);
			s = StringUtils.trim(s);
			s = StringUtils.replacePattern(s, "\\s{1,}", "-");
			s = StringUtils.replacePattern(s, "[^\\w-]", "");
			s = StringUtils.replacePattern(s, "-{2,}", "-");
		}

		return s;
	}

	/**
	 * Var.enUS: _sitePath
	 * r: siteChemin
	 * r.enUS: sitePath
	 */ 
	@Override protected void _siteChemin() throws Exception {
		if(siteChemin == null) {
			siteChemin = config.getString(langueConfigGlobale.getString(ConfigCles.var_SITE_SRC));
			if(siteChemin == null)
				siteChemin = args[0]; 
		}
	}

	/**
	 * Var.enUS: _sitePathVertx
	 * r: siteCheminVertx
	 * r.enUS: sitePathVertx
	 */ 
	@Override protected void _siteCheminVertx() throws Exception {
		if(siteCheminVertx == null) {
			siteCheminVertx = config.getString(langueConfigGlobale.getString(ConfigCles.var_COMPUTATE_VERTX_SRC)); 
		}
	}

	/**
	 * Var.enUS: classAbsolutePath
	 */ 
	public String classeCheminAbsolu;
	/** 
	 * Var.enUS: _classAbsolutePath
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 */  
	protected void _classeCheminAbsolu() throws Exception {
		if(classeCheminAbsolu == null) {
			classeCheminAbsolu = config.getString(langueConfigGlobale.getString(ConfigCles.var_CLASSE_CHEMIN_ABSOLU)); 
			if(classeCheminAbsolu == null)
				classeCheminAbsolu = args[1];
		}
	}
	
	/** 
	 * Var.enUS: sitePaths
	 */
	public LinkedHashMap<String, String> siteChemins = new LinkedHashMap<String, String>(); 
	/** 
	 * Var.enUS: _sitePaths
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: langueNom
	 * r.enUS: languageName
	 * r: siteCheminLange
	 * r.enUS: sitePathLanguage
	 * r: siteNom
	 * r.enUS: siteName
	 * r: siteCheminLangue
	 * r.enUS: sitePathLanguage
	 * r: siteChemin
	 * r.enUS: sitePath
	 */  
	protected void _siteChemins() throws Exception {
		for(String langueNom : toutesLangues) { 
			String siteCheminLangue = config.getString(langueConfigGlobale.getString(ConfigCles.var_SITE_SRC) + "_" + langueNom); 
			if(StringUtils.isEmpty(siteCheminLangue)) {
				siteChemins.put(langueNom, siteChemin);
			}
			else {
				siteChemins.put(langueNom, siteCheminLangue);
			}
		}
	}
	
	/** 
	 * Var.enUS: sitePathsVertx
	 */
	public LinkedHashMap<String, String> siteCheminsVertx = new LinkedHashMap<String, String>(); 
	/** 
	 * Var.enUS: _sitePathsVertx
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: langueNom
	 * r.enUS: languageName
	 * r: siteCheminVertxLangue
	 * r.enUS: sitePathVertxLanguage
	 * r: siteNom
	 * r.enUS: siteName
	 * r: siteChemin
	 * r.enUS: sitePath
	 */  
	protected void _siteCheminsVertx() throws Exception {
		for(String langueNom : toutesLangues) { 
			String siteCheminVertxLangue = config.getString(langueConfigGlobale.getString(ConfigCles.var_COMPUTATE_VERTX_SRC) + "_" + langueNom); 
			if(StringUtils.isEmpty(siteCheminVertxLangue)) {
				siteCheminsVertx.put(langueNom, siteCheminVertx);
			}
			else {
				siteCheminsVertx.put(langueNom, siteCheminVertxLangue);
			}
		}
	}

	/**  
	 * Var.enUS: classDocs
	 */
	public LinkedHashMap<String, SolrDocument> classeDocs = new LinkedHashMap<String, SolrDocument>();
	/**
	 * Var.enUS: _classDocs
	 */
	protected void _classeDocs() throws Exception {
	}

	/** 
	 * Var.enUS: classPartsGen
	 * r: ClasseParts
	 * r.enUS: ClassParts
	 */
	public LinkedHashMap<String, ClasseParts> classePartsGen = new LinkedHashMap<String, ClasseParts>();
	/**
	 * Var.enUS: _classPartsGen
	 */
	protected void _classePartsGen() throws Exception {
	}

	/**
	 * Var.enUS: builder
	 */ 
	public JavaProjectBuilder bricoleur;
	/**
	 * Var.enUS: _builder
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
	 * Var.enUS: initWatchClassBase
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: siteChemins
	 * r.enUS: sitePaths
	 * r: siteCheminsVertx
	 * r.enUS: sitePathsVertx
	 * r: classeDocs
	 * r.enUS: classDocs
	 * r: classeParts
	 * r.enUS: classParts
	 * r: bricoleur
	 * r.enUS: builder
	 */
	public void initRegarderClasseBase(String classeLangueNom, JsonObject classeLangueConfig) throws Exception {
		this.classeLangueNom = classeLangueNom;
		this.classeLangueConfig = classeLangueConfig;

		initConfigSite();
		_classeCheminAbsolu();
		_siteChemin();
		_siteChemins();
		_siteCheminVertx();
		_siteCheminsVertx();
		_classeDocs();
		_classePartsGen();
		_bricoleur();
	} 

	////////////
	// etend //
	////////////
	
	/**
	 * Var.enUS: extendClass
	 * Param1.var.enUS: classQdox
	 * Param2.var.enUS: canonicalName
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
	 * Var.enUS: extendClass
	 * Param1.var.enUS: canonicalNameSearch
	 * Param2.var.enUS: canonicalNameActual
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
	 * Var.enUS: extendClass
	 * Param1.var.enUS: canonicalName
	 * Param2.var.enUS: classQdox
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
	 * Var.enUS: containsField
	 * Param1.var.enUS: qdoxSuperClassesAndMe
	 * Param2.var.enUS: fieldName
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
	 * Var.enUS: containsField
	 * Param1.var.enUS: qdoxSuperClassesAndMe
	 * Param2.var.enUS: fieldName
	 * Param3.var.enUS: paramsArray
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
	 * Var.enUS: containsMethodAlone
	 * Param1.var.enUS: classQdox
	 * Param2.var.enUS: methodName
	 * Param3.var.enUS: paramsArray
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
	 * Var.enUS: containsMethod
	 * Param1.var.enUS: classQdox
	 * Param2.var.enUS: methodName
	 * Param3.var.enUS: paramsArray
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
	 * Var.enUS: containsAttribute
	 * Param1.var.enUS: domainPackageName
	 * Param2.var.enUS: attributeName
	 * Param3.var.enUS: attributeClassQdox
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
	 * Var.enUS: obtainMethod
	 * Param1.var.enUS: classQdox
	 * Param2.var.enUS: methodName
	 * Param3.var.enUS: paramsArray
	 * r: listeParams
	 * r.enUS: paramsList
	 * r: tableauParams
	 * r.enUS: paramsArray
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: methode
	 * r.enUS: method
	 */
	public JavaMethod obtenirMethode(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		JavaMethod methode = classeQdox.getMethodBySignature(nomMethode, listeParams, true);
		return methode;
	}
	
	/**
	 * Var.enUS: obtainMethod
	 * Param1.var.enUS: superClassAndMeQdox
	 * Param2.var.enUS: methodName
	 * Param3.var.enUS: paramsArray
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
	 * r: methode
	 * r.enUS: method
	 * r: classeSuperQdox
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
	 * Var.enUS: obtainMethodAlone
	 * Param1.var.enUS: classQdox
	 * Param2.var.enUS: methodName
	 * Param3.var.enUS: paramsArray
	 * r: listeParams
	 * r.enUS: paramsList
	 * r: tableauParams
	 * r.enUS: paramsArray
	 * r: nomMethode
	 * r.enUS: methodName
	 * r: classeQdox
	 * r.enUS: classQdox
	 * r: methode
	 * r.enUS: method
	 */
	public JavaMethod obtenirMethodeSeul(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		JavaMethod methode = classeQdox.getMethodBySignature(nomMethode, listeParams, false);
		return methode;
	}
}
