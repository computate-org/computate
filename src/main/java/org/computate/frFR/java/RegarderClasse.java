/*
 * Copyright Computate Limited Liability Company in Utah, USA. 
 * SPDX-License-Identifier: AGPL-3.0
 * This program and the accompanying materials are made available under the
 * terms of the GNU AFFERO GENERAL PUBLIC LICENSE which is available at
 * 
 * https://www.gnu.org/licenses/agpl-3.0.html
 * 
 * You may not propagate or modify a covered work except as expressly provided 
 * under this License. Any attempt otherwise to propagate or modify it is void, 
 * and will automatically terminate your rights under this License (including 
 * any patent licenses granted under the third paragraph of section 11).
 */
package org.computate.frFR.java; 

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.common.SolrInputDocument;
import org.yaml.snakeyaml.Yaml;

import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.JinjavaConfig;

import io.vertx.core.json.JsonObject;

import org.computate.i18n.I18n;

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
	 * r: siteNom
	 * r.enUS: siteName
	 * r: siteChemin
	 * r.enUS: sitePath
	 * r: Erreur pendant traiterEvenements.
	 * r.enUS: Error during initWatchClassBase.
	 * r: "frFR"
	 * r.enUS: "enUS"
	 */ 
	public static void main(String[] args) throws Exception {   
		try {
			RegarderClasse regarderClasse = new RegarderClasse();
			String classeLangueNom = StringUtils.defaultString(System.getenv("SITE_LANG"), "frFR");
			String appComputate = System.getenv("COMPUTATE_SRC");
			String appComputateVertx = System.getenv("COMPUTATE_VERTX_SRC");

			Jinjava jinjava = ConfigSite.getJinjava();
			JsonObject classeLangueConfig = ConfigSite.getLangueConfigGlobale(jinjava, appComputateVertx, classeLangueNom);
			JsonObject siteConfig = ConfigSite.getConfiguration(jinjava, classeLangueConfig);

			regarderClasse.args = args;
			regarderClasse.initRegarderClasseBase(classeLangueNom, classeLangueConfig);
			SolrInputDocument classeDoc = regarderClasse(classeLangueConfig, siteConfig, regarderClasse, classeLangueNom);
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
//						System.out.println(String.format(classeLangueConfig.getString(ConfigCles.str_chemin_absolu), classeNomSimple, siteUrlBase, id));
						regarderClasse2.indexerClasse(classeCheminGen, classeDoc2, classeLangueNom);
					}
					catch(Exception ex) {
						LOG.error("Erreur pendant traiterEvenements. ", ex);
					}
				}
				if(classePageChemin != null) {
					String classePageGenChemin = classePageChemin.replace("/src/main/java", "/src/gen/java").replace(".java", "Gen.java");

					RegarderClasse regarderClasse2 = new RegarderClasse();
					try {
						regarderClasse2.args = args;
						regarderClasse2.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
						SolrInputDocument classeDoc2 = new SolrInputDocument();
//						System.out.println(classeLangueConfig.getString(ConfigCles.str_chemin_absolu) + " : " + classePageGenChemin);
						regarderClasse2.indexerClasse(classePageGenChemin, classeDoc2, classeLangueNom);
					}
					catch(Exception ex) {
						LOG.error("Erreur pendant traiterEvenements. ", ex);
					}
				}
				if(classeGenPageChemin != null) {
					String classeGenPageGenChemin = classeGenPageChemin.replace("/src/main/java", "/src/gen/java").replace(".java", "Gen.java");

					RegarderClasse regarderClasse2 = new RegarderClasse();
					try {
						regarderClasse2.args = args;
						regarderClasse2.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
						SolrInputDocument classeDoc2 = new SolrInputDocument();
//						System.out.println(classeLangueConfig.getString(ConfigCles.str_chemin_absolu) + " : " + classeGenPageGenChemin);
						regarderClasse2.indexerClasse(classeGenPageGenChemin, classeDoc2, classeLangueNom);
					}
					catch(Exception ex) {
						LOG.error("Erreur pendant traiterEvenements. ", ex);
					}
				}
			}
		}
		catch(Exception ex) {
			LOG.error("Erreur pendant traiterEvenements. ", ex);
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
	public static SolrInputDocument regarderClasse(JsonObject classeLangueConfig, JsonObject siteConfig, RegarderClasse regarderClasse, String classeLangueNom) throws Exception {
		String appComputate = System.getenv("COMPUTATE_SRC");
		String appComputateVertx = System.getenv("COMPUTATE_VERTX_SRC");

		if(new File(regarderClasse.classeCheminAbsolu).isFile() && regarderClasse.classeCheminAbsolu.endsWith(".java")) {
			SolrInputDocument classeDoc = new SolrInputDocument();
//			classeDoc.addField(solrId, regarderClasse.classeCheminAbsolu);  
			regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu, classeDoc, classeLangueNom);
			String classeNomSimple = Optional.ofNullable(classeDoc.get("classeNomSimple_enUS_stored_string")).map(o -> (String)o.getValue()).orElse(null);
//			Date classeModifie = Optional.ofNullable(classeDoc.get("modifiee_stored_date")).map(o -> (Date)o.getValue()).orElse(null);
			String siteUrlBase = siteConfig.getString(classeLangueConfig.getString(I18n.var_SITE_URL_BASE));
//			String siteLangue = siteConfig.getString(classeLangueConfig.getString(ConfigCles.var_LANGUE_NOM));
//			String siteZoneStr = siteConfig.getString(classeLangueConfig.getString(ConfigCles.var_SITE_ZONE));
//			ZoneId siteZone = ZoneId.of(siteZoneStr);
//			String classeModifieStr = ZonedDateTime.ofInstant(classeModifie.toInstant(), siteZone).format(ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(siteConfig.getString(ComputateConfigKeys.SITE_ZONE))));
			Boolean classeTraduire = Optional.ofNullable(classeDoc.get("classeTraduire_stored_boolean")).map(o -> (Boolean)o.getValue()).orElse(null);
			if(classeTraduire) {
				String url = String.format(classeLangueConfig.getString(I18n.str_chemin_absolu_url), siteUrlBase, classeNomSimple);
				// String log = String.format(classeLangueConfig.getString(I18n.str_chemin_absolu), classeNomSimple);
				// LOG.info(log);
			}
//			for(String langueNom : regarderClasse.autresLangues) {
//				if(!StringUtils.equals(langueNom, regarderClasse.langueNom)) {
//					if("enUS".equals(langueNom))
//						regarderClasse.enUSWatchClass.indexClass(regarderClasse.classeCheminAbsolu, classeDoc);
//				}
//			}
//			Boolean classeTraduire = (Boolean)classeDoc.get("classeTraduire_indexed_boolean").getValue();
//						regarderClasse.ecrireClasse(regarderClasse.classeCheminAbsolu, langueNom);
//			for(String langueNom : regarderClasse.autresLangues) {
//				if(!StringUtils.equals(langueNom, regarderClasse.langueNom)) {
////					if("enUS".equals(langueNom))
////						regarderClasse.enUSWatchClass.writeClass(regarderClasse.classeCheminAbsolu, langueNom);
//					if(classeTraduire || StringUtils.equals(classeLangueNom, langueNom))
//						regarderClasse.ecrireClasse(regarderClasse.classeCheminAbsolu, langueNom);
//				}
//			}
//			for(String langueNom : regarderClasse.toutesLangues) {
//				YAMLConfiguration langueConfig = configurations.fileBased(YAMLConfiguration.class, String.format("%s/src/main/resources/org/computate/i18n/i18n_%s.yaml", appComputate, langueNom));
////				if("enUS".equals(langueNom))
////					regarderClasse.enUSWatchClass.writeGenClasses(regarderClasse.classeCheminAbsolu, langueNom);
////				if("frFR".equals(langueNom))
////					regarderClasse.frFRRegarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom);
//				if(classeTraduire || StringUtils.equals(classeLangueNom, langueNom))
//					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, classeLangueNom, langueNom, langueConfig);
//			}
			regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, classeLangueNom, classeLangueNom, classeLangueConfig);
			return classeDoc;
		}
		return null;
	}
}
