package org.computate.frFR.java;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * nomCanonique.enUS: org.computate.enUS.java.WatchClass
 */     
public class RegarderClasse extends EcrireToutesClasses {
	
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
				regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom);
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
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/EcrireToutesClasses.java";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/EcrireClasse.java";
//		String appliNom = "computate.org";
//		String appliChemin = "/usr/local/src/computate.org";
//		String classeCheminAbsolu = "/usr/local/src/computate.org/src/main/java/org/computate/frFR/site/cours/c000/config/ConfigSite.java";
//		String classeCheminAbsolu = "/usr/local/src/computate.org/src/main/java/org/computate/frFR/site/cours/c000/contexte/SiteContexte.java";
//		String appliNom = "computate-cardiac";
//		String appliChemin = "/usr/local/src/computate-cardiac";
//		String classeCheminAbsolu = "/usr/local/src/computate-cardiac/src/main/java/org/computate/frFR/cardiaque/requete/RequeteSite.java";
//		String classeCheminAbsolu = "/usr/local/src/computate-cardiac/src/main/java/org/computate/frFR/cardiaque/config/ConfigSite.java";
//		String classeCheminAbsolu = "/usr/local/src/computate-cardiac/src/main/java/org/computate/frFR/cardiaque/warfarin/CalculInr.java";
//		String classeCheminAbsolu = "/usr/local/src/computate-cardiac/src/main/java/org/computate/frFR/cardiaque/cluster/Cluster.java";
//		String appliNom = "computate-scolaire";
//		String appliChemin = "/usr/local/src/computate-scolaire";
//		String classeCheminAbsolu = "/usr/local/src/computate-scolaire/src/main/java/org/computate/frFR/scolaire/client/org/ClientOrg.java";
//		String classeCheminAbsolu = "/usr/local/src/computate-scolaire/src/main/java/org/computate/frFR/scolaire/ecole/EcoleScolaire.java";
//		String appliNom = "vertx-art";
//		String appliChemin = "/usr/local/src/vertx-art";
//		String classeCheminAbsolu = "/usr/local/src/vertx-art/src/main/java/org/computate/frFR/vertx/art/moisson/MoissoneurOai.java";
		String appliNom = "citi-architect";
		String appliChemin = "/home/ctate/workspace-citi/" + appliNom;
//		String classeCheminAbsolu = appliChemin + "/src/main/java/com/citi/architect/team/promotions/RepoCommon.java";
//		String classeCheminAbsolu = appliChemin + "/src/main/java/com/citi/architect/repo/TeamRepo.java";
//		String classeCheminAbsolu = appliChemin + "/src/main/java/com/citi/architect/team/promotions/TeamPromotions.java";
//		String classeCheminAbsolu = appliChemin + "/src/main/java/com/citi/architect/product/automation/AutomationBase.java";
		String classeCheminAbsolu = appliChemin + "/src/main/java/com/citi/commonwealth/promotions/model/Adjustment.java";
		String[] args = ArrayUtils.toArray(appliChemin, classeCheminAbsolu);
		RegarderClasse regarderClasse = new RegarderClasse();
		regarderClasse.args = args;
		regarderClasse.appliNom = appliNom;
		regarderClasse.appliChemin = appliChemin;
		regarderClasse.initRegarderClasseBase(); 
		regarderClasse(regarderClasse);
	}
}
