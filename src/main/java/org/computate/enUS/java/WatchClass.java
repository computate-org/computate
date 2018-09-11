package org.computate.enUS.java;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class WatchClass extends WriteGenClass {

	public static void  main(String[] args) throws Exception {   
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

	public static void  watchClass(WatchClass watchClass) throws Exception {
		System.out.println("absolutePath : " + watchClass.classAbsolutePath);

		SolrInputDocument classeDoc = watchClass.indexerClasse(watchClass.classAbsolutePath);
//		if("tout".equals(watchClass.languageName)) {
//			watchClass.ecrireClasseGen(watchClass.classAbsolutePath, watchClass.languageName);
			for(String languageName : watchClass.allLanguages) {
				watchClass.ecrireClasseGen(watchClass.classAbsolutePath, languageName);
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
	public void  testStuff() throws Exception {
		String appliNom = "computate";
		String appliChemin = "/usr/local/src/computate";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/RegarderClasseBase.java";
//		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/couverture/Couverture.java";
		String classeCheminAbsolu = "/usr/local/src/computate/src/main/java/org/computate/frFR/java/RegarderRepertoire.java";
		String[] args = ArrayUtils.toArray(appliChemin, classeCheminAbsolu);
		RegarderClasse regarderClasse = new RegarderClasse();
		regarderClasse.args = args;
		regarderClasse.appliNom = appliNom;
		regarderClasse.appliChemin = appliChemin;
		regarderClasse.initRegarderClasseBase(); 
		regarderClasse(regarderClasse);
	}
}
