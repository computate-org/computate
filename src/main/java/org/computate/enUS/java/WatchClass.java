package org.computate.enUS.java;

import java.io.File;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrInputDocument;
import org.computate.enUS.java.WatchClass;

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
}
