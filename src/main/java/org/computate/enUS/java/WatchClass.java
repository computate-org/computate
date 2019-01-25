package org.computate.enUS.java;

import java.io.File;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrInputDocument;
import org.computate.enUS.java.IndexClass;
import org.computate.enUS.java.WatchClass;
import org.junit.Test;

public class WatchClass extends WriteAllClasses {

	public WatchClass frFRRegarderClasse;

	public WatchClass enUSWatchClass;

	public static void  main(String[] args) throws Exception, Exception {   
		WatchClass watchClass = new WatchClass();
		try {
			watchClass.args = args;
			watchClass.initWatchClassBase(); 
//			try {
//				if(ArrayUtils.contains(watchClass.otherLanguages, "enUS")) {
//					watchClass.enUSWatchClass = new WatchClass();
//					watchClass.enUSWatchClass.args = args;
//					watchClass.enUSWatchClass.appName = watchClass.appName;
//					watchClass.enUSWatchClass.appPath = watchClass.appPath;
//					watchClass.enUSWatchClass.initWatchClassBase();
//				}
//			} catch (Exception e) {
//				System.err.println(e.getMessage());
//			}
//			try {
//				if(ArrayUtils.contains(watchClass.otherLanguages, "frFR")) {
//					watchClass.frFRRegarderClasse = new WatchClass();
//					watchClass.frFRRegarderClasse.args = args;
//					watchClass.frFRRegarderClasse.appName = watchClass.appName;
//					watchClass.frFRRegarderClasse.appPath = watchClass.appPath;
//					watchClass.frFRRegarderClasse.initWatchClassBase();
//				}
//			} catch (Exception e) {
//				System.err.println(e.getMessage());
//			}
		}
		catch(Exception e) {
			System.err.println("Error during initWatchClassBase. ");
			System.err.println(ExceptionUtils.getStackTrace(e));
		}
		watchClass(watchClass);
	}

	public static void  watchClass(WatchClass watchClass) throws Exception, Exception {
		System.out.println("absolutePath : " + watchClass.classAbsolutePath);

		if(new File(watchClass.classAbsolutePath).isFile()) {
			SolrInputDocument classDoc = new SolrInputDocument();
//			classDoc.addField("id", watchClass.classAbsolutePath);  
			watchClass.indexClass(watchClass.classAbsolutePath, classDoc);
//			for(String languageName : watchClass.otherLanguages) {
//				if(!StringUtils.equals(languageName, watchClass.languageName)) {
//					if("enUS".equals(languageName))
//						watchClass.enUSWatchClass.indexClass(watchClass.classAbsolutePath, classDoc);
//				}
//			}
			for(String languageName : watchClass.allLanguages) {
//				if("enUS".equals(languageName))
//					watchClass.enUSWatchClass.writeGenClasses(watchClass.classAbsolutePath, languageName);
//				if("frFR".equals(languageName))
//					watchClass.frFRRegarderClasse.writeGenClasses(watchClass.classAbsolutePath, languageName);
				watchClass.writeGenClasses(watchClass.classAbsolutePath, languageName);
			}
			for(String languageName : watchClass.otherLanguages) {
				if(!StringUtils.equals(languageName, watchClass.languageName)) {
//					if("enUS".equals(languageName))
//						watchClass.enUSWatchClass.writeClass(watchClass.classAbsolutePath, languageName);
					watchClass.writeClass(watchClass.classAbsolutePath, languageName);
				}
			}
		}
	}

	@Test()
	public void  testStuff() throws Exception, Exception {
//		String appName = "computate";
//		String appPath = "/usr/local/src/" + appName;
//		String classAbsolutePath = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/WatchClassBase.java";
//		String classAbsolutePath = "/usr/local/src/computate/src/main/java/org/computate/frFR/couverture/Couverture.java";
//		String classAbsolutePath = appPath + "/src/main/java/org/computate/frFR/java/RegarderRepertoire.java";
//		String classAbsolutePath = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/EcrireGenClasse.java";
//		String classAbsolutePath = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/EcrireToutesClasses.java";
//		String classAbsolutePath = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/EcrireClasse.java";
//		String classAbsolutePath = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/StringPrintWriter.java";
//		String appName = "computate.org";
//		String appPath = "/usr/local/src/computate.org";
//		String classAbsolutePath = "/usr/local/src/computate.org/src/main/java/org/computate/frFR/site/cours/c000/config/ConfigSite.java";
//		String classAbsolutePath = "/usr/local/src/computate.org/src/main/java/org/computate/frFR/site/cours/c000/contexte/SiteContexte.java";
//		String appName = "computate-cardiac";
//		String appPath = "/usr/local/src/" + appName;
//		String classAbsolutePath = appPath + "/src/main/java/org/computate/frFR/cardiaque/requete/RequeteSite.java";
//		String classAbsolutePath = appPath + "/src/main/java/org/computate/frFR/cardiaque/config/ConfigSite.java";
//		String classAbsolutePath = appPath + "/src/main/java/org/computate/frFR/cardiaque/warfarin/CalculInr.java";
//		String classAbsolutePath = appPath + "/src/main/java/org/computate/frFR/cardiaque/cluster/Cluster.java";
//		String appName = "computate-scolaire";
//		String appPath = "/usr/local/src/computate-scolaire";
//		String classAbsolutePath = "/usr/local/src/computate-scolaire/src/main/java/org/computate/frFR/scolaire/client/org/ClientOrg.java";
//		String classAbsolutePath = "/usr/local/src/computate-scolaire/src/main/java/org/computate/frFR/scolaire/ecole/EcoleScolaire.java";
//		String appName = "vertx-art";
//		String appPath = "/usr/local/src/vertx-art";
//		String classAbsolutePath = "/usr/local/src/vertx-art/src/main/java/org/computate/frFR/vertx/art/moisson/MoissoneurOai.java";
		String appName = "citi-architect";
		String appPath = "/home/ctate/workspace-citi/" + appName;
//		String classAbsolutePath = appPath + "/src/main/java/com/citi/architect/team/promotions/RepoCommon.java";
//		String classAbsolutePath = appPath + "/src/main/java/com/citi/architect/repo/TeamRepo.java";
//		String classAbsolutePath = appPath + "/src/main/java/com/citi/architect/team/promotions/TeamPromotions.java";
//		String classAbsolutePath = appPath + "/src/main/java/com/citi/architect/product/automation/AutomationBase.java";
//		String classAbsolutePath = appPath + "/src/main/java/com/citi/commonwealth/promotions/model/Adjustment.java";
//		String classAbsolutePath = appPath + "/src/main/java/com/citi/commonwealth/payments/integration/rplid/service/AutpayI18n.java";
//		String classAbsolutePath = appPath + "/src/main/java/com/citi/enUS/commonwealth/payments/integration/rplid/service/AutopayI18n.java";
//		String classAbsolutePath = appPath + "/src/main/java/com/citi/commonwealth/promotions/model/AdjustmentReasonCodes.java";
		String classAbsolutePath = appPath + "/src/main/java/com/citi/commonwealth/promotions/model/EligibleAdjustmentType.java";
//		String classAbsolutePath = appPath + "/src/main/java/com/citi/commonwealth/promotions/model/Adjustment.java";
		String[] args = ArrayUtils.toArray(appPath, classAbsolutePath);
		WatchClass watchClass = new WatchClass();
		watchClass.args = args;
		watchClass.appName = appName;
		watchClass.appPath = appPath;
		watchClass.initWatchClassBase(); 
		watchClass(watchClass);
	}
}
