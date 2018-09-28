package org.computate.frFR.java;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * nomCanonique.enUS: org.computate.enUS.java.WatchClass
 */     
public class RegarderClasse extends EcrireGenClasse {
	
	public RegarderClasse() {
	}

	/**
	 * r: initRegarderClasseBase
	 * r.enUS: initWatchClassBase
	 * r: RegarderClasse
	 * r.enUS: WatchClass
	 * r: regarderClasse
	 * r.enUS: watchClass
	 * r: Erreur pendant traiterEvenements.
	 * r.enUS: Error during initWatchClassBase.
	 */
	public static void main(String[] args) throws Exception {   
		RegarderClasse regarderClasse = new RegarderClasse();
		try {
			regarderClasse.args = args;
			regarderClasse.initRegarderClasseBase(); 
		}
		catch(Exception e) {
			System.err.println("Erreur pendant traiterEvenements. ");
			System.err.println(ExceptionUtils.getStackTrace(e));
		}
		regarderClasse(regarderClasse);
	}
	
	/**
	 * var.enUS: watchClass
	 * param1.var.enUS: watchClass
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
	 */ 
	public static void regarderClasse(RegarderClasse regarderClasse) throws Exception {
		System.out.println("cheminAbsolu : " + regarderClasse.classeCheminAbsolu);

		SolrInputDocument classeDoc = regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu);
//		if("tout".equals(regarderClasse.langueNom)) {
//			regarderClasse.ecrireClasseGen(regarderClasse.classeCheminAbsolu, regarderClasse.langueNom);
			for(String langueNom : regarderClasse.toutesLangues) {
				regarderClasse.ecrireClasseGen(regarderClasse.classeCheminAbsolu, langueNom);
			}
			for(String langueNom : regarderClasse.autresLangues) {
				if(!StringUtils.equals(langueNom, regarderClasse.langueNom))
					regarderClasse.ecrireClasse(regarderClasse.classeCheminAbsolu, langueNom);
			}
//		}
//		else {
//			regarderClasse.ecrireClasseGen(regarderClasse.classeCheminAbsolu, regarderClasse.langueNom);
//		}
	}

	@Test
	public void testStuff() throws Exception {
//		String appliNom = "computate";
//		String appliChemin = "/usr/local/src/computate";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/RegarderClasseBase.java";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/couverture/Couverture.java";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/RegarderRepertoire.java";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/EcrireGenClasse.java";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/EcrireClasse.java";
		String appliNom = "computate.org";
		String appliChemin = "/usr/local/src/computate.org";
//		String classeCheminAbsolu = "/usr/local/src/computate.org/src/main/java/org/computate/frFR/site/cours/c000/config/ConfigSite.java";
		String classeCheminAbsolu = "/usr/local/src/computate.org/src/main/java/org/computate/frFR/site/cours/c000/contexte/EcouteurContexte.java";
		String[] args = ArrayUtils.toArray(appliChemin, classeCheminAbsolu);
		RegarderClasse regarderClasse = new RegarderClasse();
		regarderClasse.args = args;
		regarderClasse.appliNom = appliNom;
		regarderClasse.appliChemin = appliChemin;
		regarderClasse.initRegarderClasseBase(); 
		regarderClasse(regarderClasse);
	}
}
