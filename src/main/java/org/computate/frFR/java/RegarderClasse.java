package org.computate.frFR.java; 

import java.io.File;
import java.util.Optional;

import org.apache.commons.configuration2.YAMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrInputDocument;

/**
 * NomCanonique.enUS: org.computate.enUS.java.WatchClass
 */     
public class RegarderClasse extends EcrireToutesClasses {

	public RegarderClasse frFRRegarderClasse;

	/**
	 * r: initRegarderClasseBase
	 * r.enUS: initWatchClassBase
	 * r: RegarderClasse regarderClasse
	 * r.enUS: WatchClass watchClass
	 * r: new RegarderClasse
	 * r.enUS: new WatchClass
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
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
		String classeLangueNom = StringUtils.defaultString(System.getenv("APP_LANG"), "frFR");
		Configurations configurations = new Configurations();
		YAMLConfiguration classeLangueConfig = configurations.fileBased(YAMLConfiguration.class, String.format("classpath:/i18n/i18n_%s.yml", classeLangueNom));
		try {
			regarderClasse.args = args;
			regarderClasse.initRegarderClasseBase(classeLangueNom, classeLangueConfig);
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
		SolrInputDocument classeDoc = regarderClasse(classeLangueConfig, regarderClasse, classeLangueNom);
		if(classeDoc != null) {
			Boolean classeEtendGen = (Boolean)classeDoc.get("classeEtendGen_stored_boolean").getValue();
			String classeCheminGen = Optional.ofNullable(classeDoc.get("classeCheminGen_enUS_stored_string")).map(o -> (String)o.getValue()).orElse(null);
			String classePageChemin = Optional.ofNullable(classeDoc.get("classePageChemin_enUS_stored_string")).map(o -> (String)o.getValue()).orElse(null);
			String classeGenPageChemin = Optional.ofNullable(classeDoc.get("classeGenPageChemin_enUS_stored_string")).map(o -> (String)o.getValue()).orElse(null);
			if(classeEtendGen != null && classeCheminGen != null && classeEtendGen) {
				
				RegarderClasse regarderClasse2 = new RegarderClasse();
				try {
					regarderClasse2.args = args;
					regarderClasse2.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
					SolrInputDocument classeDoc2 = new SolrInputDocument();
					System.out.println(classeLangueConfig.getString(ConfigCles.str_chemin_absolu) + " : " + classeCheminGen);
					regarderClasse2.indexerClasse(classeCheminGen, classeDoc2, classeLangueNom);
				}
				catch(Exception e) {
					System.err.println("Erreur pendant traiterEvenements. ");
					System.err.println(ExceptionUtils.getStackTrace(e));
				}
			}
			if(classePageChemin != null) {
				String classePageGenChemin = classePageChemin.replace("/src/main/java", "/src/gen/java").replace(".java", "Gen.java");
				
				RegarderClasse regarderClasse2 = new RegarderClasse();
				try {
					regarderClasse2.args = args;
					regarderClasse2.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
					SolrInputDocument classeDoc2 = new SolrInputDocument();
					System.out.println(classeLangueConfig.getString(ConfigCles.str_chemin_absolu) + " : " + classePageGenChemin);
					regarderClasse2.indexerClasse(classePageGenChemin, classeDoc2, classeLangueNom);
				}
				catch(Exception e) {
					System.err.println("Erreur pendant traiterEvenements. ");
					System.err.println(ExceptionUtils.getStackTrace(e));
				}
			}
			if(classeGenPageChemin != null) {
				String classeGenPageGenChemin = classeGenPageChemin.replace("/src/main/java", "/src/gen/java").replace(".java", "Gen.java");
				
				RegarderClasse regarderClasse2 = new RegarderClasse();
				try {
					regarderClasse2.args = args;
					regarderClasse2.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
					SolrInputDocument classeDoc2 = new SolrInputDocument();
					System.out.println(classeLangueConfig.getString(ConfigCles.str_chemin_absolu) + " : " + classeGenPageGenChemin);
					regarderClasse2.indexerClasse(classeGenPageGenChemin, classeDoc2, classeLangueNom);
				}
				catch(Exception e) {
					System.err.println("Erreur pendant traiterEvenements. ");
					System.err.println(ExceptionUtils.getStackTrace(e));
				}
			}
		}
	}
	
	/**
	 * Var.enUS: watchClass
	 * Param1.var.enUS: watchClass
	 * Param2.var.enUS: classLanguageName
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
	public static SolrInputDocument regarderClasse(YAMLConfiguration classeLangueConfig, RegarderClasse regarderClasse, String classeLangueNom) throws Exception {

		if(new File(regarderClasse.classeCheminAbsolu).isFile() && regarderClasse.classeCheminAbsolu.endsWith(".java")) {
			System.out.println(classeLangueConfig.getString(ConfigCles.str_chemin_absolu) + " : " + regarderClasse.classeCheminAbsolu);
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
				YAMLConfiguration langueConfig = configurations.fileBased(YAMLConfiguration.class, String.format("i18n/i18n_%s.yml", langueNom));
//				if("enUS".equals(langueNom))
//					regarderClasse.enUSWatchClass.writeGenClasses(regarderClasse.classeCheminAbsolu, langueNom);
//				if("frFR".equals(langueNom))
//					regarderClasse.frFRRegarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom);
				if(classeTraduire || StringUtils.equals(classeLangueNom, langueNom))
					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, classeLangueNom, langueNom, langueConfig);
			}
			return classeDoc;
		}
		return null;
	}
}
