package org.computate.enUS.java;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class WatchClass extends WriteAllClasses {

	public static void  main(String[] args) throws Exception, Exception {   
		WatchClass watchClass = new WatchClass();
		try {
			watchClass.args = args;
			watchClass.initWatchClassBase(); 
		}
		catch(Exception e) {
			System.err.println("Error during initWatchClassBase. ");
			System.err.println(ExceptionUtils.getStackTrace(e));
		}
		watchClass(watchClass);
	}

	public static void  watchClass(WatchClass watchClass) throws Exception, Exception {
		System.out.println("absolutePath : " + watchClass.classAbsolutePath);

		SolrInputDocument classeDoc = watchClass.indexerClasse(watchClass.classAbsolutePath);
//		if("tout".equals(watchClass.languageName)) {
//			watchClass.ecrireClasseGen(watchClass.classAbsolutePath, watchClass.languageName);
			for(String languageName : watchClass.allLanguages) {
				watchClass.ecrireGenClasses(watchClass.classAbsolutePath, languageName);
			}
			for(String languageName : watchClass.otherLanguages) {
				if(!StringUtils.equals(languageName, watchClass.languageName))
					watchClass.ecrireClasse(watchClass.classAbsolutePath, languageName);
			}
//		}
//		else {
//			watchClass.ecrireClasseGen(watchClass.classAbsolutePath, watchClass.languageName);
//		}
	}

	@Test()
	public void  testStuff() throws Exception, Exception {
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
		String classeCheminAbsolu = appliChemin + "/src/main/java/com/citi/architect/product/automation/AutomationBase.java";
		String[] args = ArrayUtils.toArray(appliChemin, classeCheminAbsolu);
		RegarderClasse regarderClasse = new RegarderClasse();
		regarderClasse.args = args;
		regarderClasse.appliNom = appliNom;
		regarderClasse.appliChemin = appliChemin;
		regarderClasse.initRegarderClasseBase(); 
		regarderClasse(regarderClasse);
	}
}
