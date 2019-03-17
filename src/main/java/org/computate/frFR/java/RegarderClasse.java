package org.computate.frFR.java;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrInputDocument;
import org.computate.enUS.java.WatchClass;

/**
 * NomCanonique.enUS: org.computate.enUS.java.WatchClass
 */     
public class RegarderClasse extends EcrireToutesClasses {

	public RegarderClasse frFRRegarderClasse;
	public WatchClass enUSWatchClass;

	/**
	 * r: initRegarderClasseBase
	 * r.enUS: initWatchClassBase
	 * r: RegarderClasse regarderClasse
	 * r.enUS: WatchClass watchClass
	 * r: new RegarderClasse
	 * r.enUS: new WatchClass
	 * r: regarderClasse
	 * r.enUS: watchClass
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: appliNom
	 * r.enUS: appName
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: Erreur pendant traiterEvenements.
	 * r.enUS: Error during initWatchClassBase.
	 * r: "frFR"
	 * r.enUS: "enUS"
	 */ 
	public static void main(String[] args) throws Exception {   
		RegarderClasse regarderClasse = new RegarderClasse();
		String classeLangueNom = "frFR";
		try {
			regarderClasse.args = args;
			regarderClasse.initRegarderClasseBase(); 
//			try {
//				if(ArrayUtils.contains(regarderClasse.autresLangues, "enUS")) {
//					regarderClasse.enUSWatchClass = new WatchClass();
//					regarderClasse.enUSWatchClass.args = args;
//					regarderClasse.enUSWatchClass.appName = regarderClasse.appliNom;
//					regarderClasse.enUSWatchClass.appPath = regarderClasse.appliChemin;
//					regarderClasse.enUSWatchClass.initWatchClassBase();
//				}
//			} catch (Exception e) {
//				System.err.println(e.getMessage());
//			}
//			try {
//				if(ArrayUtils.contains(regarderClasse.autresLangues, "frFR")) {
//					regarderClasse.frFRRegarderClasse = new RegarderClasse();
//					regarderClasse.frFRRegarderClasse.args = args;
//					regarderClasse.frFRRegarderClasse.appliNom = regarderClasse.appliNom;
//					regarderClasse.frFRRegarderClasse.appliChemin = regarderClasse.appliChemin;
//					regarderClasse.frFRRegarderClasse.initRegarderClasseBase();
//				}
//			} catch (Exception e) {
//				System.err.println(e.getMessage());
//			}
		}
		catch(Exception e) {
			System.err.println("Erreur pendant traiterEvenements. ");
			System.err.println(ExceptionUtils.getStackTrace(e));
		}
		regarderClasse(regarderClasse, classeLangueNom);
	}
	
	/**
	 * Var.enUS: watchClass
	 * Param1.var.enUS: watchClass
	 * r: regarderClasse
	 * r.enUS: watchClass
	 * r: cheminAbsolu
	 * r.enUS: absolutePath
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: langueNom
	 * r.enUS: languageName
	 * r: toutesLangues
	 * r.enUS: allLanguages
	 * r: autresLangues
	 * r.enUS: otherLanguages
	 * r: indexerClasse
	 * r.enUS: indexClass
	 * r: ecrireGenClasses
	 * r.enUS: writeGenClasses
	 * r: ecrireClasse
	 * r.enUS: writeClass
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: classeTraduire
	 * r.enUS: classTranslate
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 */   
	public static void regarderClasse(RegarderClasse regarderClasse, String classeLangueNom) throws Exception {
		System.out.println("cheminAbsolu : " + regarderClasse.classeCheminAbsolu);

		if(new File(regarderClasse.classeCheminAbsolu).isFile()) {
			SolrInputDocument classeDoc = new SolrInputDocument();
//			classeDoc.addField("id", regarderClasse.classeCheminAbsolu);  
			regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu, classeDoc, classeLangueNom);
//			for(String langueNom : regarderClasse.autresLangues) {
//				if(!StringUtils.equals(langueNom, regarderClasse.langueNom)) {
//					if("enUS".equals(langueNom))
//						regarderClasse.enUSWatchClass.indexClass(regarderClasse.classeCheminAbsolu, classeDoc);
//				}
//			}
			Boolean classeTraduire = (Boolean)classeDoc.get("classeTraduire_indexed_boolean").getValue();
			for(String langueNom : regarderClasse.autresLangues) {
				if(!StringUtils.equals(langueNom, regarderClasse.langueNom)) {
//					if("enUS".equals(langueNom))
//						regarderClasse.enUSWatchClass.writeClass(regarderClasse.classeCheminAbsolu, langueNom);
					if(classeTraduire || StringUtils.equals(classeLangueNom, langueNom))
						regarderClasse.ecrireClasse(regarderClasse.classeCheminAbsolu, langueNom);
				}
			}
			for(String langueNom : regarderClasse.toutesLangues) {
//				if("enUS".equals(langueNom))
//					regarderClasse.enUSWatchClass.writeGenClasses(regarderClasse.classeCheminAbsolu, langueNom);
//				if("frFR".equals(langueNom))
//					regarderClasse.frFRRegarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom);
				if(classeTraduire || StringUtils.equals(classeLangueNom, langueNom))
					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, classeLangueNom, langueNom);
			}
		}
	}
}
