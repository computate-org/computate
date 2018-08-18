package org.computate.frFR.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;
import org.computate.frFR.config.ConfigSite;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaType;

/**     
 * classeNomCanonique_enUS: org.computate.enUS.java.WatchClassBase
 */  
public class RegarderClasseBase extends ConfigSite {    

	protected String[] args;

	@Override protected void _appliChemin() throws Exception {
		appliChemin = args[0]; 
	}

	protected String classeCheminAbsolu;
	protected void _classeCheminAbsolu() throws Exception {
		classeCheminAbsolu = args[1];
	}
	
	protected HashMap<String, String> appliChemins = new HashMap<String, String>(); 
	protected void _appliChemins() throws Exception {
		for(String langueNom : autresLangues) {  
			String appliCheminLangue = config.getString(appliNom + ".appliChemin_" + langueNom); 
			System.out.println("appliCheminLangue " + langueNom + ": " + appliCheminLangue);
			if(StringUtils.isEmpty(appliCheminLangue)) {
				appliChemins.put(langueNom, appliChemin);
			}
			else {
				appliChemins.put(langueNom, appliCheminLangue);
			}
		}
	}

	protected HashMap<String, SolrDocument> classeDocs = new HashMap<String, SolrDocument>();
	protected void _classeDocs() throws Exception {
	}

	protected JavaProjectBuilder bricoleur;
	protected void _bricoleur() throws Exception {
		bricoleur = new JavaProjectBuilder();
		for(String cheminSource : toutCheminsSource) {
			File répertoireSource = new File(cheminSource);
			bricoleur.addSourceFolder(répertoireSource);
		}
	} 


	public void initRegarderClasseBase() throws Exception {
		initConfigSite();
		_classeCheminAbsolu();
		_appliChemins();
		_classeDocs();
		_bricoleur();
	}

	////////////
	// etend //
	////////////
	
	protected boolean etendClasse(JavaClass classeQdox, String nomCanonique) {
//		for(JavaClass classeSuperQdox : classesSuperQdox) {
//			if(classeSuperQdox.getCanonicalName().equals(nomCanonique))
//				return true;
//		}
		boolean resultat = etendClasse(nomCanonique, classeQdox);
		return resultat;
	}
	protected boolean etendClasse(String nomCanoniqueRecherche, String nomCanoniqueActuel) {
		JavaClass classeQdox = bricoleur.getClassByName(nomCanoniqueActuel);
		return etendClasse(nomCanoniqueRecherche, classeQdox);
	}
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
	
	public Boolean contientChamp(List<JavaClass> classesSuperQdoxEtMoi, String nomChamp, Class<?> c) {
		JavaClass classeQdox = bricoleur.getClassByName(c.getCanonicalName());
		Boolean o = contientChamp(classesSuperQdoxEtMoi, nomChamp, classeQdox);
		return o;
	} 
	
	public Boolean contientChamp(List<JavaClass> classesSuperQdoxEtMoi, String nomChamp, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		for(JavaClass classeSuper : classesSuperQdoxEtMoi) {
			JavaField champRequeteSite = classeSuper.getFieldByName(nomChamp);
			JavaMethod methodeRequeteSite = classeSuper.getMethod("_" + nomChamp, listeParams, false);
			Boolean o = champRequeteSite != null || methodeRequeteSite != null;
			if(o)
				return true;
		}
		return false;
	}

	public Boolean contientMethodeSeul(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		JavaMethod o = obtenirMethodeSeul(classeQdox, nomMethode, tableauParams);
		return o != null;
	}

	public Boolean contientMethode(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		JavaMethod o = obtenirMethode(classeQdox, nomMethode, tableauParams);
		return o != null;
	}
	
	public Boolean contientAttribut(String nomEnsembleDomaine, String nomAttribut, JavaClass classeAttribut) {
		Boolean resultat = false;
		if(classeAttribut.getCanonicalName().startsWith(nomEnsembleDomaine.toString())) {
			JavaField attribut = classeAttribut.getFieldByName(nomAttribut);
			if(attribut == null) {
				resultat = contientAttribut(nomEnsembleDomaine, nomAttribut, classeAttribut.getSuperJavaClass());
			}
			else {
				resultat = true;
			}
		}
		else {
			resultat = false;
		}
		return resultat;
	}

	/////////////
	// obtenir //
	/////////////
	
	public JavaMethod obtenirMethode(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		JavaMethod methode = classeQdox.getMethodBySignature(nomMethode, listeParams, true);
		return methode;
	}
	
	public JavaMethod obtenirMethode(List<JavaClass> classesSuperQdoxEtMoi, String nomMethode, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		for(JavaClass classeSuper : classesSuperQdoxEtMoi) {
			JavaMethod methode = classeSuper.getMethodBySignature(nomMethode, listeParams);
			if(methode != null)
				return methode;
		}
		return null;
	}
	
	public JavaMethod obtenirMethodeSeul(JavaClass classeQdox, String nomMethode, JavaClass...tableauParams) {
		ArrayList<JavaType> listeParams = new ArrayList<JavaType>();
		Collections.addAll(listeParams, tableauParams);
		JavaMethod methode = classeQdox.getMethodBySignature(nomMethode, listeParams, false);
		return methode;
	}
}
