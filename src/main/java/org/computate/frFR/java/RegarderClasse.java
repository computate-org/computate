package org.computate.frFR.java;

import java.io.File;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrInputDocument;
import org.computate.enUS.java.IndexClass;
import org.computate.enUS.java.WatchClass;
import org.junit.Test;

/**
 * nomCanonique.enUS: org.computate.enUS.java.WatchClass
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
	 */ 
	public static void main(String[] args) throws Exception {   
		RegarderClasse regarderClasse = new RegarderClasse();
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
	 * r: indexerClasse
	 * r.enUS: indexClass
	 * r: ecrireGenClasses
	 * r.enUS: writeGenClasses
	 * r: ecrireClasse
	 * r.enUS: writeClass
	 * r: classeDoc
	 * r.enUS: classDoc
	 */   
	public static void regarderClasse(RegarderClasse regarderClasse) throws Exception {
		System.out.println("cheminAbsolu : " + regarderClasse.classeCheminAbsolu);

		if(new File(regarderClasse.classeCheminAbsolu).isFile()) {
			SolrInputDocument classeDoc = new SolrInputDocument();
//			classeDoc.addField("id", regarderClasse.classeCheminAbsolu);  
			regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu, classeDoc);
//			for(String langueNom : regarderClasse.autresLangues) {
//				if(!StringUtils.equals(langueNom, regarderClasse.langueNom)) {
//					if("enUS".equals(langueNom))
//						regarderClasse.enUSWatchClass.indexClass(regarderClasse.classeCheminAbsolu, classeDoc);
//				}
//			}
			for(String langueNom : regarderClasse.toutesLangues) {
//				if("enUS".equals(langueNom))
//					regarderClasse.enUSWatchClass.writeGenClasses(regarderClasse.classeCheminAbsolu, langueNom);
//				if("frFR".equals(langueNom))
//					regarderClasse.frFRRegarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom);
				regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom);
			}
			for(String langueNom : regarderClasse.autresLangues) {
				if(!StringUtils.equals(langueNom, regarderClasse.langueNom)) {
//					if("enUS".equals(langueNom))
//						regarderClasse.enUSWatchClass.writeClass(regarderClasse.classeCheminAbsolu, langueNom);
					regarderClasse.ecrireClasse(regarderClasse.classeCheminAbsolu, langueNom);
				}
			}
		}
	}

	/**
	 * r: RegarderClasse
	 * r.enUS: WatchClass
	 * r: regarderClasse
	 * r.enUS: watchClass
	 * r: appliNom
	 * r.enUS: appName
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 */
	@Test public void testStuff() throws Exception {
//		String appliNom = "computate";
//		String appliChemin = "/usr/local/src/" + appliNom;
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/RegarderClasseBase.java";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/couverture/Couverture.java";
//		String classeCheminAbsolu = appliChemin + "/src/main/java/org/computate/frFR/java/RegarderRepertoire.java";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/EcrireGenClasse.java";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/EcrireToutesClasses.java";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/EcrireClasse.java";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/StringPrintWriter.java";
//		String appliNom = "computate.org";
//		String appliChemin = "/usr/local/src/computate.org";
//		String classeCheminAbsolu = "/usr/local/src/computate.org/src/main/java/org/computate/frFR/site/cours/c000/config/ConfigSite.java";
//		String classeCheminAbsolu = "/usr/local/src/computate.org/src/main/java/org/computate/frFR/site/cours/c000/contexte/SiteContexte.java";
//		String appliNom = "computate-cardiac";
//		String appliChemin = "/usr/local/src/" + appliNom;
//		String classeCheminAbsolu = appliChemin + "/src/main/java/org/computate/frFR/cardiaque/requete/RequeteSite.java";
//		String classeCheminAbsolu = appliChemin + "/src/main/java/org/computate/frFR/cardiaque/config/ConfigSite.java";
//		String classeCheminAbsolu = appliChemin + "/src/main/java/org/computate/frFR/cardiaque/warfarin/CalculInr.java";
//		String classeCheminAbsolu = appliChemin + "/src/main/java/org/computate/frFR/cardiaque/cluster/Cluster.java";
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
//		String classeCheminAbsolu = appliChemin + "/src/main/java/com/citi/commonwealth/payments/integration/rplid/service/AutpayI18n.java";
//		String classeCheminAbsolu = appliChemin + "/src/main/java/com/citi/enUS/commonwealth/payments/integration/rplid/service/AutopayI18n.java";
//		String classeCheminAbsolu = appliChemin + "/src/main/java/com/citi/commonwealth/promotions/model/AdjustmentReasonCodes.java";
//		String classeCheminAbsolu = appliChemin + "/src/main/java/com/citi/commonwealth/promotions/model/EligibleAdjustmentType.java";
//		String classeCheminAbsolu = appliChemin + "/src/main/java/com/citi/commonwealth/promotions/model/Adjustment.java";
		String[] args = ArrayUtils.toArray(appliChemin, classeCheminAbsolu);
		RegarderClasse regarderClasse = new RegarderClasse();
		regarderClasse.args = args;
		regarderClasse.appliNom = appliNom;
		regarderClasse.appliChemin = appliChemin;
		regarderClasse.initRegarderClasseBase(); 
		regarderClasse(regarderClasse);
	}
}
