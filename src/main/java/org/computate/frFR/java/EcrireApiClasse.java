/*
 * Copyright (c) 2018-2022 Computate Limited Liability Company in Utah, USA. 
 *
 * This program and the accompanying materials are made available under the
 * terms of the GNU GENERAL PUBLIC LICENSE Version 3 which is available at
 * 
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 * 
 * You may not propagate or modify a covered work except as expressly provided 
 * under this License. Any attempt otherwise to propagate or modify it is void, 
 * and will automatically terminate your rights under this License (including 
 * any patent licenses granted under the third paragraph of section 11).
 */
package org.computate.frFR.java; 

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.service.ServiceRequest;

import org.computate.i18n.I18n;

/**   
 * NomCanonique.enUS: org.computate.enUS.java.WriteApiClass
 * 
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */     
public class EcrireApiClasse extends EcrireGenClasse {   
//
//	/**
//	 * Var.enUS: writerApiPackageInfo
//	 */
//	protected ToutEcrivain auteurApiEnsembleInfo;

	/**
	 * Var.enUS: writerApiServiceImpl
	 */
	protected ToutEcrivain auteurApiServiceImpl;

	/**
	 * Var.enUS: writerGenApiServiceImpl
	 */
	protected ToutEcrivain auteurGenApiServiceImpl;

	/**
	 * Var.enUS: writerGenApiService
	 */  
	protected ToutEcrivain auteurGenApiService;

	/////////////////////
	// classeNomSimple //
	/////////////////////

	/**
	 * Var.enUS: classSimpleNameApiPackageInfo
	 */
	protected String classeNomSimpleApiEnsembleInfo;

	/**
	 * Var.enUS: classSimpleNameGenApiServiceImpl
	 */
	protected String classeNomSimpleGenApiServiceImpl;

	/**
	 * Var.enUS: classSimpleNameApiServiceImpl
	 */
	protected String classeNomSimpleApiServiceImpl;

	/**
	 * Var.enUS: classSimpleNameGenApiService
	 */
	protected String classeNomSimpleGenApiService;

	////////////////////////
	// classeNomCanonique //
	////////////////////////

	/**
	 * Var.enUS: classCanonicalNameApiPackageInfo
	 */
	protected String classeNomCanoniqueApiEnsembleInfo;

	/**
	 * Var.enUS: classCanonicalNameGenApiServiceImpl
	 */
	protected String classeNomCanoniqueGenApiServiceImpl;

	/**
	 * Var.enUS: classCanonicalNameApiServiceImpl
	 */
	protected String classeNomCanoniqueApiServiceImpl;

	/**
	 * Var.enUS: classCanonicalNameGenApiService
	 */
	protected String classeNomCanoniqueGenApiService;

	/**
	 * Var.enUS: classPageSimple
	 */
	protected Boolean classePageSimple;

	/**
	 * Var.enUS: apiCodeClassBegin
	 * Param1.var.enUS: languageName
	 * r: auteurGenApiServiceImpl
	 * r.enUS: writerGenApiServiceImpl
	 * r: auteurApiServiceImpl
	 * r.enUS: writerApiServiceImpl
	 * r: auteurGenApiService
	 * r.enUS: writerGenApiService
	 * r: auteurApiServiceGenClasse
	 * r.enUS: writerApiServiceGenClass
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: classeImporationsGenApi
	 * r.enUS: classImportsGenApi
	 * r: classeImportation
	 * r.enUS: classImport
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: EnsembleInfo
	 * r.enUS: PackageInfo
	 */
	public void apiCodeClasseDebut(String langueNom) throws Exception {
	}
//
//	/**
//	 * Var.enUS: writeApiPackageInfo
//	 * Param1.var.enUS: languageName
//	 * 
//	 * r: auteurApiEnsembleInfo
//	 * r.enUS: writerApiPackageInfo
//	 * r: classeNomSimple
//	 * r.enUS: classSimpleName
//	 * r: classeNomEnsemble
//	 * r.enUS: classPackageName
//	 **/
//	public void ecrireApiEnsembleInfo(String langueNom) throws Exception {
//		if(auteurApiEnsembleInfo != null) {
//			auteurApiEnsembleInfo.l("@ModuleGen(name=\"", classeNomSimple, "Api", "\", groupPackage=\"", classeNomEnsemble, "\")");
//			auteurApiEnsembleInfo.l("package ", classeNomEnsemble, ";");
//			auteurApiEnsembleInfo.l();
//			auteurApiEnsembleInfo.l("import io.vertx.codegen.annotations.ModuleGen;");
//
//			auteurApiEnsembleInfo.flushClose();
//		}
//	}

	/**
	 * Var.enUS: writeGenApiService
	 * Param1.var.enUS: classLanguageName
	 * 
	 * r: auteurGenApiService
	 * r.enUS: writerGenApiService
	 * r: classePageLangueNom
	 * r.enUS: classPageLanguageName
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
	 * r: classePageNomCanonique
	 * r.enUS: classPageCanonicalName
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: operationRequete
	 * r.enUS: serviceRequest
	 * r: gestionnaireEvenements
	 * r.enUS: eventHandler
	 * r: gestionnaireResultat
	 * r.enUS: resultHandler
	 * r: // Une méthode d'usine pour créer une instance et un proxy. 
	 * r.enUS: // A factory method to create an instance and a proxy. 
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: classeApiOperationIdMethode
	 * r.enUS: classApiOperationIdMethod
	 * r: classeApiOperationId
	 * r.enUS: classApiOperationId
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
	 * r: DocumentSolr
	 * r.enUS: SolrDocument
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: nomSimple
	 * r.enUS: simpleName
	 * r: classeNomCanoniqueGenApiService
	 * r.enUS: classCanonicalNameGenApiService
	 * 
	 * r: recherche
	 * r.enUS: search
	 * r: addresse
	 * r.enUS: address
	 **/ 
	public void ecrireGenApiService(String classeLangueNom) throws Exception {
		classeAutresLangues = ArrayUtils.removeAllOccurences(toutesLangues, classeLangueNom);
		if(auteurGenApiService != null) {
			if(classeDroitAuteur != null)
				auteurGenApiService.l(classeDroitAuteur);
			auteurGenApiService.l("package ", classeNomEnsemble, ";");
			auteurGenApiService.l();
			auteurGenApiService.l("import io.vertx.ext.web.client.WebClient;");
			auteurGenApiService.l("import io.vertx.codegen.annotations.ProxyGen;");
			auteurGenApiService.l("import io.vertx.serviceproxy.ServiceBinder;");
			auteurGenApiService.l("import io.vertx.core.AsyncResult;");
			auteurGenApiService.l("import io.vertx.core.eventbus.EventBus;");
			auteurGenApiService.l("import io.vertx.core.Handler;");
			auteurGenApiService.l("import io.vertx.core.Vertx;");
			auteurGenApiService.l("import io.vertx.core.json.JsonObject;");
			auteurGenApiService.l("import io.vertx.core.json.JsonArray;");
			auteurGenApiService.l("import io.vertx.ext.web.api.service.WebApiServiceGen;");
			auteurGenApiService.l("import io.vertx.ext.web.api.service.ServiceRequest;");
			auteurGenApiService.l("import io.vertx.ext.web.api.service.ServiceResponse;");
			auteurGenApiService.l("import com.hubspot.jinjava.Jinjava;");
			auteurGenApiService.l("import io.vertx.core.WorkerExecutor;");
			auteurGenApiService.l("import io.vertx.sqlclient.Pool;");
			auteurGenApiService.l("import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;");
			auteurGenApiService.l("import io.vertx.kafka.client.producer.KafkaProducer;");
			auteurGenApiService.l("import io.vertx.mqtt.MqttClient;");
			auteurGenApiService.l("import io.vertx.amqp.AmqpSender;");
			auteurGenApiService.l("import io.vertx.rabbitmq.RabbitMQClient;");
			if(activerOpenIdConnect) {
				auteurGenApiService.l("import io.vertx.ext.auth.oauth2.OAuth2Auth;");
				auteurGenApiService.l("import io.vertx.ext.auth.authorization.AuthorizationProvider;");
			}
			auteurGenApiService.l();
			auteurGenApiService.l("/**");
			auteurGenApiService.l(" * ", i18nGlobale.getString(I18n.var_Traduire), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueGenApiServiceLangue = classeDoc.getString("classeNomCanoniqueGenApiService_" + langueNom + "_stored_string");
				auteurGenApiService.l(" * ", i18nGlobale.getString(I18n.var_NomCanonique), ".", langueNom, ": ", classeNomCanoniqueGenApiServiceLangue);
			}
			auteurGenApiService.l(" * Gen: false");
			auteurGenApiService.l(" * ", i18nGlobale.getString(I18n.str_Genere), ": true");
			auteurGenApiService.l(" **/");
			auteurGenApiService.l("@WebApiServiceGen");
			auteurGenApiService.l("@ProxyGen");
			auteurGenApiService.s("public interface ", classeNomSimpleGenApiService, " {");
			auteurGenApiService.l();
//			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");

			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classePageNomCanoniqueMethode = classeDoc.getString("classePageNomCanonique" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiTypeMediaRequeteMethode = classeDoc.getString("classeApiTypeMediaRequete" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classePageLangueNom = classeDoc.getString("classePageLangueNom" + classeApiMethode + "_" + classeLangueNom + "_stored_string");

				if(classePageLangueNom == null || classePageLangueNom.equals(classeLangueNom)) {

					if(classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageEdition))
							|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageAffichage))
							|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageUtilisateur))
							|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_Telechargement))
							) {
						auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "(");
						auteurGenApiService.l("ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ");");
					} else {
						auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "(");
						if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH", "DELETE"))
							auteurGenApiService.s(StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ");
						auteurGenApiService.l("ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ");");
						if(StringUtils.containsAny(classeApiMethode, "POST", "PUTImport", i18nGlobale.getString(I18n.var_PUTFusion), "PATCH", "DELETE")) {
							auteurGenApiService.tl(1, "public void ", classeApiOperationIdMethode, "Future(" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ");");
						}
					}
				}
			}
			auteurGenApiService.tl(0, "}");

			auteurGenApiService.flushClose();
		}
	}

	/**
	 * Var.enUS: writeApiServiceImpl
	 * Param1.var.enUS: languageName
	 * 
	 * r: auteurApiServiceImpl
	 * r.enUS: writerApiServiceImpl
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: nomSimple
	 * r.enUS: simpleName
	 * r: DocumentSolr
	 * r.enUS: SolrDocument
	 * r: langueNom
	 * r.enUS: languageName
	 * 
	 * r: Traduire
	 * r.enUS: Translate
	 **/
	public void ecrireApiServiceImpl(String classeLangueNom) throws Exception {
		classeAutresLangues = ArrayUtils.removeAllOccurences(toutesLangues, classeLangueNom);
		if(auteurApiServiceImpl != null) {
			if(classeDroitAuteur != null)
				auteurApiServiceImpl.l(classeDroitAuteur);
			auteurApiServiceImpl.l("package ", classeNomEnsemble, ";");
			auteurApiServiceImpl.l();
			if(activerOpenIdConnect) {
				auteurApiServiceImpl.l("import io.vertx.ext.auth.authorization.AuthorizationProvider;");
				auteurApiServiceImpl.l("import io.vertx.ext.auth.oauth2.OAuth2Auth;");
			}
			auteurApiServiceImpl.l("import io.vertx.ext.web.client.WebClient;");
			auteurApiServiceImpl.l("import io.vertx.core.Vertx;");
			auteurApiServiceImpl.l("import io.vertx.core.WorkerExecutor;");
			auteurApiServiceImpl.l("import io.vertx.core.json.JsonObject;");
			auteurApiServiceImpl.l("import io.vertx.sqlclient.Pool;");
			auteurApiServiceImpl.l("import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;");
			auteurApiServiceImpl.l("import io.vertx.kafka.client.producer.KafkaProducer;");
			auteurApiServiceImpl.l("import io.vertx.mqtt.MqttClient;");
			auteurApiServiceImpl.l("import io.vertx.amqp.AmqpSender;");
			auteurApiServiceImpl.l("import io.vertx.rabbitmq.RabbitMQClient;");
			if(classePage)
				auteurApiServiceImpl.l("import com.hubspot.jinjava.Jinjava;");
//			auteurGenApiService.l("import ", classeNomEnsemble, ".", classeNomSimple, "ApiServiceVertxEBProxy;");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.l("/**");
			auteurApiServiceImpl.l(" * ", i18nGlobale.getString(I18n.var_Traduire), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueApiServiceImplLangue = classeDoc.getString("classeNomCanoniqueApiServiceImpl_" + langueNom + "_stored_string");
				auteurApiServiceImpl.l(" * ", i18nGlobale.getString(I18n.var_NomCanonique), ".", langueNom, ": ", classeNomCanoniqueApiServiceImplLangue);
			}
			auteurApiServiceImpl.l(" **/");
			auteurApiServiceImpl.l("public class ", classeNomSimpleApiServiceImpl, " extends ", classeNomSimpleGenApiServiceImpl, " {");
			auteurApiServiceImpl.l("}");

			auteurApiServiceImpl.flushClose();
		}
	}

	/** 
	 */ 
	public void ecrireGenApiServiceImpl1(String classeLangueNom) throws Exception {
		classeAutresLangues = ArrayUtils.removeAllOccurences(toutesLangues, classeLangueNom);

		if(auteurGenApiServiceImpl != null) {
			o = auteurGenApiServiceImpl;
	
			if(classeDroitAuteur != null)
				l(classeDroitAuteur);
			l("package ", classeNomEnsemble, ";");
			l();
			if(classeImportationsGenApi.size() > 0) { 
				for(String classeImportation : classeImportationsGenApi) {
					l("import ", classeImportation, ";");
				}
				l();
			}

			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + classesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr.addFilterQuery("entiteEstSubstitue_indexed_boolean:false");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
		    rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);
				QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList rechercheListe = rechercheReponse.getResults();
				Integer rechercheLignes = rechercheSolr.getRows();
	
				if(rechercheListe.size() > 0) {
					for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
						for(Integer j = 0; j < rechercheListe.size(); j++) {
							SolrDocument doc = rechercheListe.get(j);
							entiteVar = (String)doc.get("entiteVar_" + classeLangueNom + "_stored_string");
							entiteVarCapitalise = (String)doc.get("entiteVarCapitalise_" + classeLangueNom + "_stored_string");
							entiteAttribuer = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuer_stored_boolean"));
							entiteAttribuerAttribuer = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuerAttribuer_stored_boolean"));
							entiteAttribuerVar = (String)doc.get("entiteAttribuerVar_" + classeLangueNom + "_stored_string");
							entiteAttribuerTypeJson = (String)doc.get("entiteAttribuerTypeJson_stored_string");
							entiteAttribuerNomSimple = (String)doc.get("entiteAttribuerNomSimple_" + classeLangueNom + "_stored_string");
							entiteAttribuerNomCanonique = (String)doc.get("entiteAttribuerNomCanonique_" + classeLangueNom + "_stored_string");
							entiteAttribuerUtilisateurEcrire = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuerUtilisateurEcrire_stored_boolean"));
							entiteAttribuerSessionEcrire = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuerSessionEcrire_stored_boolean"));
							entiteAttribuerPublicLire = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuerPublicLire_stored_boolean"));
							entiteAttribuerClasseRoles = (List<String>)doc.get("entiteAttribuerClasseRoles_stored_strings");
							entiteAttribuerClasseRolesLangue = (List<String>)doc.get("entiteAttribuerClasseRolesLangue_stored_strings");
							entiteDefinir = (Boolean)doc.get("entiteDefinir_stored_boolean");
							entiteModifier = (Boolean)doc.get("entiteModifier_stored_boolean");
							entiteSuffixeType = (String)doc.get("entiteSuffixeType_stored_string");
							entiteSuffixeSolr = (String)doc.get("entiteSuffixeSolr_stored_string");
							entiteDocValues = (Boolean)doc.get("entiteDocValues_stored_boolean");
							entiteIndexe = (Boolean)doc.get("entiteIndexe_stored_boolean");
							entiteStocke = (Boolean)doc.get("entiteStocke_stored_boolean");
							entiteSolrNomCanonique = (String)doc.get("entiteSolrNomCanonique_stored_string");
							entiteSolrNomSimple = (String)doc.get("entiteSolrNomSimple_stored_string");
							entiteNomSimpleVertxJson = (String)doc.get("entiteNomSimpleVertxJson_stored_string");
							entiteNomCanoniqueVertxJson = (String)doc.get("entiteNomCanoniqueVertxJson_stored_string");
							entiteListeNomSimpleVertxJson = (String)doc.get("entiteListeNomSimpleVertxJson_stored_string");
							entiteListeNomCanoniqueVertxJson = (String)doc.get("entiteListeNomCanoniqueVertxJson_stored_string");
							entiteListeTypeJson = (String)doc.get("entiteListeTypeJson_stored_string");
							entiteTypeJson = (String)doc.get("entiteTypeJson_stored_string");
							entiteNomCanonique = (String)doc.get("entiteNomCanonique_" + classeLangueNom + "_stored_string");
							entiteNomCanoniqueGenerique = (String)doc.get("entiteNomCanoniqueGenerique_" + classeLangueNom + "_stored_string");
							entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + classeLangueNom + "_stored_string");
							entiteNomSimpleCompletGenerique = (String)doc.get("entiteNomSimpleCompletGenerique_" + classeLangueNom + "_stored_string");
							entiteNomSimple = (String)doc.get("entiteNomSimple_" + classeLangueNom + "_stored_string");
							Boolean entiteEstListe = (StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()));

							/////////////////////////
							// classePageTemplates //
							/////////////////////////

							if(classePageAvecTemplate && entiteDefinir) {
								if((
										StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())
										|| entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, ZonedDateTime.class.getCanonicalName()))) {
									if(classeVarZone != null) {
										wPageTemplates.tl(3, "page.persistForClass(", classeNomSimple, ".VAR_", entiteVar, ", ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", i18nGlobale.getString(I18n.var_requeteSite), "2, (String)", i18nGlobale.getString(I18n.var_resultat), ".get(", classeNomSimple, ".VAR_", entiteVar, "), Optional.ofNullable(page.get", StringUtils.capitalize(classeVarZone), "()).map(v -> ZoneId.of(v)).orElse(Optional.ofNullable(", langueConfigGlobale.getString(I18n.var_requeteSite), ").map(r -> r.get", langueConfigGlobale.getString(I18n.var_Config), "()).map(config -> config.getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfigGlobale.getString(I18n.var_SITE_ZONE), ")).map(z -> ZoneId.of(z)).orElse(ZoneId.of(\"UTC\")))));");
									} else {
										wPageTemplates.tl(3, "page.persistForClass(", classeNomSimple, ".VAR_", entiteVar, ", ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", i18nGlobale.getString(I18n.var_requeteSite), "2, (String)", i18nGlobale.getString(I18n.var_resultat), ".get(", classeNomSimple, ".VAR_", entiteVar, "), Optional.ofNullable(", langueConfigGlobale.getString(I18n.var_requeteSite), ").map(r -> r.get", langueConfigGlobale.getString(I18n.var_Config), "()).map(config -> config.getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfigGlobale.getString(I18n.var_SITE_ZONE), ")).map(z -> ZoneId.of(z)).orElse(ZoneId.of(\"UTC\"))));");
									}
								} else {
									wPageTemplates.tl(3, "page.persistForClass(", classeNomSimple, ".VAR_", entiteVar, ", ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", i18nGlobale.getString(I18n.var_requeteSite), "2, (String)", i18nGlobale.getString(I18n.var_resultat), ".get(", classeNomSimple, ".VAR_", entiteVar, ")));");
								}
								
							}
	
							/////////////////
							// codeApiGet //
							/////////////////

							if(classeIndexe && entiteIndexe) {
								wApiGet.tl(6, "case ", classeNomSimple, ".VAR_", entiteVar, ":");
								wApiGet.tl(7, "return ", classeNomSimple, ".VAR_", entiteVar, " + \"", (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\";");
							}
							
							///////////////////////
							// codeApiGenererGet //
							///////////////////////
							o = wApiGenererGet;
							if(classeIndexe && entiteStocke) {
				//				tl(7, "if(", q(entiteVar, "_stored", entiteSuffixeType), ".equals(entiteVarStocke)) {");
								if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList, VAL_nomCanoniqueSet, VAL_nomCanoniqueHashSet)) {
									if(VAL_nomCanoniqueBoolean.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Boolean)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueDate.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueTimestamp.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toInstant().atZone(ZoneId.systemDefault()).toZonedDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueLocalDate.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Long)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniquePoint.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(Point.valueOf((Double)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniquePath.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(Path.valueOf((Double)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniquePolygon.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(Polygon.valueOf((Double)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueVertxJsonObject.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ".toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueVertxJsonArray.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ".toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(BigDecimal.valueOf((Double)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Double)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Float)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Integer)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".size(); k++) {");
										tl(9, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(((String)", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), "));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
								}
								else {
									l();
									tl(7, i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " = o.get", entiteVarCapitalise, "();");
									tl(7, "if(", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), " != null)");
									if (VAL_nomCanoniqueBoolean.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ");");
									} else if (VAL_nomCanoniqueDate.equals(entiteSolrNomCanonique)) {
										if (VAL_nomCanoniqueTimestamp.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), "));");
										} else if (VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), "));");
										} else if (VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), "));");
										} else if (VAL_nomCanoniqueLocalDate.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), "));");
										} else {
											tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), "));");
										}
									} else if (VAL_nomCanoniqueLong.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ");");
									} else if (VAL_nomCanoniquePoint.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ");");
									} else if (VAL_nomCanoniquePath.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ");");
									} else if (VAL_nomCanoniquePolygon.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ");");
									} else if (VAL_nomCanoniqueVertxJsonObject.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ");");
									} else if (VAL_nomCanoniqueVertxJsonArray.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ");");
									} else if (VAL_nomCanoniqueDouble.equals(entiteSolrNomCanonique)) {
										if (VAL_nomCanoniqueBigDecimal.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ");");
										}
										else {
											tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ");");
										}
									} else if (VAL_nomCanoniqueFloat.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ");");
									} else if (VAL_nomCanoniqueInteger.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), ");");
									}
									else {
										tl(8, "w.tl(3, ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Valeur), "));");
									}
								}
							}
//	
//							Integer tBase = 3;

							///////////////////
							// wApiSqlSelect //
							///////////////////
							if(classeSauvegarde && BooleanUtils.isTrue(entiteAttribuer)) {
								if(!wApiSqlSelect.getEmpty())
									wApiSqlSelect.s(", ");
								wApiSqlSelect.s(entiteVar);
							} else if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
								if(!wApiSqlSelect.getEmpty())
									wApiSqlSelect.s(", ");
								if(VAL_nomCanoniquePolygon.equals(entiteNomCanoniqueGenerique))
									wApiSqlSelect.s("ST_AsGeoJSON(", entiteVar, ") as ", entiteVar);
								else
									wApiSqlSelect.s(entiteVar);
							}
							

							////////////////////////
							// codeApiGenererPost //
							////////////////////////
							o = wApiGenererPost;
					
							if(classeSauvegarde && BooleanUtils.isTrue(entiteAttribuer)) {
								tl(5, "case ", classeNomSimple, ".VAR_", entiteVar, ":");
								if("array".equals(entiteTypeJson))
									tl(6, "Optional.ofNullable(jsonObject.getJsonArray(", i18nGlobale.getString(I18n.var_entite), "Var)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {");
								else
									tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(val -> {");
								if(!entiteAttribuerAttribuer || StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
									if("array".equals(entiteTypeJson) && "array".equals(entiteAttribuerTypeJson)) {
										// list, list, <
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").insertInto(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").relateValues(", classeVarClePrimaire, ", ", classeVarClePrimaire, "2, ", i18nGlobale.getString(I18n.var_solrId), "2).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteTypeJson)) {
										// list, no list, <
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteAttribuerTypeJson)) {
										// no list, list, <
										tl(7, "futures1.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", i18nGlobale.getString(I18n.var_solrId), "2, val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else {
										// no list, no list, <
										tl(7, "futures1.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", i18nGlobale.getString(I18n.var_solrId), "2, val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									}
								} else {
									if("array".equals(entiteTypeJson) && "array".equals(entiteAttribuerTypeJson)) {
										// list, list, >
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").insertInto(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").relateValues(", classeVarClePrimaire, "2, ", classeVarClePrimaire, ", o.get", solrIdCapitalise, "()).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteTypeJson)) {
										// list, no list, >
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteAttribuerTypeJson)) {
										// no list, list, >
										tl(7, "futures1.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", i18nGlobale.getString(I18n.var_solrId), "2, val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else {
										// no list, no list, >
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									}
								}
								tl(6, "});");
								tl(6, "break;");
							} else if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
								tl(5, "case ", classeNomSimple, ".VAR_", entiteVar, ":");
								tl(6, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", i18nGlobale.getString(I18n.var_entite), "Var));");
								tl(6, "if(bParams.size() > 0) {");
								tl(7, "bSql.append(\", \");");
								tl(6, "}");
								tl(6, "bSql.append(", classeNomSimple, ".VAR_", entiteVar, " + \"=$\" + num);");
								tl(6, "num++;");
								tl(6, "bParams.add(o2.sql", entiteVarCapitalise, "());");
				
								tl(6, "break;");
							}	

							//////////////////////////
							// codeApiGenererDelete //
							//////////////////////////
							o = wApiGenererDelete;
					
							if(classeSauvegarde && BooleanUtils.isTrue(entiteAttribuer)) {
								tl(5, "case ", classeNomSimple, ".VAR_", entiteVar, ":");
								if("array".equals(entiteTypeJson))
									tl(6, "Optional.ofNullable(jsonObject.getJsonArray(", i18nGlobale.getString(I18n.var_entite), "Var)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {");
								else
									tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(val -> {");
								if(!entiteAttribuerAttribuer || StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
									if("array".equals(entiteTypeJson) && "array".equals(entiteAttribuerTypeJson)) {
										// list, list, <
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").deleteFrom(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").relateValues(", classeVarClePrimaire, ", ", classeVarClePrimaire, "2, ", i18nGlobale.getString(I18n.var_solrId), "2).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteTypeJson)) {
										// list, no list, <
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, null, null).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteAttribuerTypeJson)) {
										// no list, list, <
										tl(7, "futures1.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, null, null).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else {
										// no list, no list, <
										tl(7, "futures1.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, null, null).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									}
								} else {
									if("array".equals(entiteTypeJson) && "array".equals(entiteAttribuerTypeJson)) {
										// list, list, >
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").insertInto(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").relateValues(", classeVarClePrimaire, ", null, ", i18nGlobale.getString(I18n.var_solrId), "2).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteTypeJson)) {
										// list, no list, >
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, null, null).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteAttribuerTypeJson)) {
										// no list, list, >
										tl(7, "futures1.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, null, null).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else {
										// no list, no list, >
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, null, null).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									}
								}
								tl(6, "});");
								tl(6, "break;");
							}
					
							////////////////////////////
							// codeApiGenererPutCopie //
							////////////////////////////
							o = wApiGenererPutCopie;
				
							if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
								tl(5, "case ", classeNomSimple, ".VAR_", entiteVar, ":");
								tl(6, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", i18nGlobale.getString(I18n.var_entite), "Var));");
								tl(6, "if(bParams.size() > 0) {");
								tl(7, "bSql.append(\", \");");
								tl(6, "}");
								tl(6, "bSql.append(", classeNomSimple, ".VAR_", entiteVar, " + \"=$\" + num);");
								tl(6, "num++;");
								tl(6, "bParams.add(o2.sql", entiteVarCapitalise, "());");
				
								tl(6, "break;");
							}	
							if(classeSauvegarde && BooleanUtils.isTrue(entiteAttribuer)) {
								tl(5, "case ", classeNomSimple, ".VAR_", entiteVar, ":");
								if("array".equals(entiteTypeJson))
									tl(6, "Optional.ofNullable(jsonObject.getJsonArray(", i18nGlobale.getString(I18n.var_entite), "Var)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {");
								else
									tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(val -> {");
								if(!entiteAttribuerAttribuer || StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
									if("array".equals(entiteTypeJson) && "array".equals(entiteAttribuerTypeJson)) {
										// list, list, <
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").insertInto(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").relateValues(", classeVarClePrimaire, ", ", classeVarClePrimaire, "2, ", i18nGlobale.getString(I18n.var_solrId), "2).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteTypeJson)) {
										// list, no list, <
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteAttribuerTypeJson)) {
										// no list, list, <
										tl(7, "futures1.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", i18nGlobale.getString(I18n.var_solrId), "2, val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else {
										// no list, no list, <
										tl(7, "futures1.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", i18nGlobale.getString(I18n.var_solrId), "2, val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									}
								} else {
									if("array".equals(entiteTypeJson) && "array".equals(entiteAttribuerTypeJson)) {
										// list, list, >
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").insertInto(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").relateValues(", classeVarClePrimaire, "2, ", classeVarClePrimaire, ", o.get", solrIdCapitalise, "()).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteTypeJson)) {
										// list, no list, >
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else if("array".equals(entiteAttribuerTypeJson)) {
										// no list, list, >
										tl(7, "futures1.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", i18nGlobale.getString(I18n.var_solrId), "2, val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									} else {
										// no list, no list, >
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
										tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
										tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
										tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
										tl(10, "promise2.complete();");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "promise2.fail(ex);");
										tl(9, "});");
										tl(8, "}).onFailure(ex -> {");
										tl(9, "promise2.fail(ex);");
										tl(8, "});");
										tl(7, "}));");
									}
								}
								tl(6, "});");
								tl(6, "break;");
							}
					
							////////////////////////
							// codeApiGenererPatch //
							////////////////////////
							o = wApiGenererPatch;

							if(classeSauvegarde) {
								if(BooleanUtils.isTrue(entiteAttribuer)) {
									if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
									}

									if(!entiteAttribuerAttribuer || StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
										if("array".equals(entiteTypeJson) && "array".equals(entiteAttribuerTypeJson)) {
											// list, list, <
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, "JsonArray set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", i18nGlobale.getString(I18n.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").insertInto(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").relateValues(", classeVarClePrimaire, ", ", classeVarClePrimaire, "2, ", i18nGlobale.getString(I18n.var_solrId), "2).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "Optional.ofNullable(o.get", entiteVarCapitalise, "()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".contains(oVal.toString())).forEach(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(7, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(8, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").deleteFrom(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").where(", classeVarClePrimaire, ", ", classeVarClePrimaire, "2).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"addAll", entiteVarCapitalise, "\":");
											tl(6, "JsonArray addAll", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", i18nGlobale.getString(I18n.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "addAll", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").insertInto(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").relateValues(", classeVarClePrimaire, ", ", classeVarClePrimaire, "2, ", i18nGlobale.getString(I18n.var_solrId), "2).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"add", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").insertInto(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").relateValues(", classeVarClePrimaire, ", ", classeVarClePrimaire, "2, ", i18nGlobale.getString(I18n.var_solrId), "2).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"remove", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").deleteFrom(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").where(", classeVarClePrimaire, ", ", classeVarClePrimaire, "2).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
										} else if("array".equals(entiteTypeJson)) {
											// list, no list, <
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, "JsonArray set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", i18nGlobale.getString(I18n.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "Optional.ofNullable(o.get", entiteVarCapitalise, "()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".contains(oVal.toString())).forEach(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(8, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).setToNull(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeVarClePrimaire, "2).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"addAll", entiteVarCapitalise, "\":");
											tl(6, "JsonArray addAll", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", i18nGlobale.getString(I18n.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "addAll", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"add", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"remove", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).setToNull(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, null).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
										} else if("array".equals(entiteAttribuerTypeJson)) {
											// no list, list, <
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures1.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", i18nGlobale.getString(I18n.var_solrId), "2, val).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"remove", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").setToNull(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, null).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
										} else {
											// no list, no list, <
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures1.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", i18nGlobale.getString(I18n.var_solrId), "2, val).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"remove", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").setToNull(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, null).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
										}
									} else {
										if("array".equals(entiteTypeJson) && "array".equals(entiteAttribuerTypeJson)) {
											// list, list, >
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, "JsonArray set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", i18nGlobale.getString(I18n.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").insertInto(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").relateValues(", classeVarClePrimaire, ", ", classeVarClePrimaire, "2, ", i18nGlobale.getString(I18n.var_solrId), "2).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "Optional.ofNullable(o.get", entiteVarCapitalise, "()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".contains(oVal.toString())).forEach(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(8, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").deleteFrom(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").where(", classeVarClePrimaire, "2, ", classeVarClePrimaire, ").onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"addAll", entiteVarCapitalise, "\":");
											tl(6, "JsonArray addAll", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", i18nGlobale.getString(I18n.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "addAll", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").insertInto(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").relateValues(", classeVarClePrimaire, ", ", classeVarClePrimaire, "2, ", i18nGlobale.getString(I18n.var_solrId), "2).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"add", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").insertInto(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").relateValues(", classeVarClePrimaire, ", ", classeVarClePrimaire, "2, ", i18nGlobale.getString(I18n.var_solrId), "2).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"remove", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").deleteFrom(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").where(", classeVarClePrimaire, "2, ", classeVarClePrimaire, ").onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
										} else if("array".equals(entiteTypeJson)) {
											// list, no list, >
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, "JsonArray set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", i18nGlobale.getString(I18n.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "Optional.ofNullable(o.get", entiteVarCapitalise, "()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !set", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".contains(oVal.toString())).forEach(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(7, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(8, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).setToNull(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeVarClePrimaire, "2).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"addAll", entiteVarCapitalise, "\":");
											tl(6, "JsonArray addAll", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", i18nGlobale.getString(I18n.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "addAll", entiteVarCapitalise, i18nGlobale.getString(I18n.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"add", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"remove", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).setToNull(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, null).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
										} else if("array".equals(entiteAttribuerTypeJson)) {
											// no list, list, >
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures1.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", i18nGlobale.getString(I18n.var_solrId), "2, val).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"remove", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", classeNomSimple, ".class, ", classeVarClePrimaire, ").setToNull(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, null).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
										} else {
											// no list, no list, >
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), entiteAttribuerNomSimple, "(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, "), ", entiteAttribuerNomSimple, ".class, val).onSuccess(o3 -> {");
											tl(9, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = Optional.ofNullable(o3).map(o4 -> o4.get", solrIdCapitalise, "()).filter(", i18nGlobale.getString(I18n.var_solrId), "3 -> !", i18nGlobale.getString(I18n.var_solrIds), ".contains(", i18nGlobale.getString(I18n.var_solrId), "3)).orElse(null);");
											tl(9, "if(", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
											tl(10, i18nGlobale.getString(I18n.var_solrIds), ".add(", i18nGlobale.getString(I18n.var_solrId), "2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, o.get", StringUtils.capitalize(i18nGlobale.getString(I18n.var_solrId)), "(), val).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"remove", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", i18nGlobale.getString(I18n.var_entite), "Var)).ifPresent(", i18nGlobale.getString(I18n.var_solrId), "2 -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(9, "sql(", i18nGlobale.getString(I18n.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, ", classeVarClePrimaire, "2).setToNull(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, null).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
										}
									}
								}
								else if(BooleanUtils.isTrue(entiteDefinir)) {
									if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
//						
//										tl(5, "case \"set", entiteVarCapitalise, "\":");
//										tl(6, "o2.get", entiteVarCapitalise, "().clear();");
//										tl(6, "jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var).forEach((v) -> {");
//										tl(7, "o2.add", entiteVarCapitalise, "((", entiteListeNomSimpleVertxJson, ")v);");
//										tl(7, "futures.add(Future.future(a -> {");
//										tl(8, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD)");
//										tl(10, ".execute(Tuple.of(", classeVarClePrimaire, ", ", classeNomSimple, ".VAR_", entiteVar, ", o2.json", entiteVarCapitalise, "())");
//										tl(10, ", b");
//										tl(8, "-> {");
//										tl(9, "if(b.succeeded())");
//										tl(10, "a.handle(Future.succeededFuture());");
//										tl(9, "else");
//										tl(10, "a.handle(Future.failedFuture(new Exception(\"", classeLangueConfig.getString(ConfigCles.var_valeur), " ", classeNomSimple, ".", entiteVar, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), "\", b.cause())));");
//										tl(8, "});");
//										tl(7, "}));");
//										tl(6, "});");
//										tl(6, "break;");
						
										tl(5, "case \"set", entiteVarCapitalise, "\":");
										tl(7, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", i18nGlobale.getString(I18n.var_entite), "Var));");
										tl(7, "if(bParams.size() > 0)");
										tl(8, "bSql.append(\", \");");
										if(VAL_nomCanoniquePolygon.equals(entiteNomCanoniqueGenerique))
											tl(7, "bSql.append(String.format(\"%s=ST_GeomFromGeoJSON($%s)\", ", classeNomSimple, ".VAR_", entiteVar, ", num));");
										else
											tl(7, "bSql.append(", classeNomSimple, ".VAR_", entiteVar, " + \"=$\" + num);");
										tl(7, "num++;");
										tl(7, "bParams.add(o2.sql", entiteVarCapitalise, "());");
										tl(6, "break;");
									}
									else {
						
										tl(5, "case \"set", entiteVarCapitalise, "\":");
										tl(7, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", i18nGlobale.getString(I18n.var_entite), "Var));");
										tl(7, "if(bParams.size() > 0)");
										tl(8, "bSql.append(\", \");");
										tl(7, "bSql.append(", classeNomSimple, ".VAR_", entiteVar, " + \"=$\" + num);");
										tl(7, "num++;");
										tl(7, "bParams.add(o2.sql", entiteVarCapitalise, "());");
										tl(6, "break;");
									}
								}
							}	
						}
						rechercheSolr.setStart(i.intValue() + rechercheLignes);
						rechercheReponse = clientSolrComputate.query(rechercheSolr);
						rechercheListe = rechercheReponse.getResults();
					}
				}
			}
			wApiGet.flushClose();
			wApiGenererGet.flushClose();
		}
	}

	/** 
	 */ 
	public void ecrireGenApiServiceImpl2(String classeLangueNom) throws Exception {
		classeAutresLangues = ArrayUtils.removeAllOccurences(toutesLangues, classeLangueNom);

		if(auteurGenApiServiceImpl != null) {
			o = auteurGenApiServiceImpl;
			l();
			l("/**");
			l(" * ", i18nGlobale.getString(I18n.var_Traduire), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueGenApiServiceImplLangue = classeDoc.getString("classeNomCanoniqueGenApiServiceImpl_" + langueNom + "_stored_string");
				l(" * ", i18nGlobale.getString(I18n.var_NomCanonique), ".", langueNom, ": ", classeNomCanoniqueGenApiServiceImplLangue);
			}
			l(" * ", i18nGlobale.getString(I18n.str_Genere), ": true");
			l(" **/");
			s("public class ", classeNomSimpleGenApiServiceImpl, " extends ", classePartsBaseApiServiceImpl.nomSimple(langueNom));
			s(" implements ", classeNomSimpleGenApiService);
			l(" {");
			l();
			tl(1, "protected static final Logger LOG = LoggerFactory.getLogger(", classeNomSimpleGenApiServiceImpl, ".class);");

			for(String classeApiMethode : classeApiMethodes) {
				classePageNomCanoniqueMethode = classeDoc.getString("classePageNomCanonique" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				classePageNomSimpleMethode = classeDoc.getString("classePageNomSimple" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				classeApiTypeMedia200Methode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				classeApiTypeMediaRequeteMethode = classeDoc.getString("classeApiTypeMediaRequete" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				classePageLangueNom = classeDoc.getString("classePageLangueNom" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				classePageTemplateMethode = classeDoc.getString("classe" + classeApiMethode + "Template_" + classeLangueNom + "_stored_string");
				classePageAvecTemplateMethode = classeDoc.getBoolean("classePageAvecTemplate" + classeApiMethode + "_stored_boolean");

				if(classePageLangueNom == null || classePageLangueNom.equals(classeLangueNom)) {

					///////////////////
					// /modele/{id}: //
					///////////////////

					l();
					tl(1, "// ", classeApiMethode, " //");
					if(classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageEdition))
							|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageAffichage))
							|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageUtilisateur))
							|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_Telechargement))
							) {
						l();
						tl(1, "@Override");
						t(1, "public void ", classeApiOperationIdMethode, "(");
						if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH", "DELETE"))
							s("JsonObject body, ");
						l("ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ") {");
						tl(2, "Boolean ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), " = ", 
								!classeApiMethode.contains(i18nGlobale.getString(I18n.var_Telechargement))
								&& ( classePublicLire || classeRoleSession || classeRoleUtilisateur)
								, ";");
						tl(2, i18nGlobale.getString(I18n.var_utilisateur), "(", i18nGlobale.getString(I18n.var_requeteService), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".get", i18nGlobale.getString(I18n.var_ClasseApiAddresse), "(), \"post", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", \"patch", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), ").onSuccess(", i18nGlobale.getString(I18n.var_requeteSite), " -> {");
						if(
									classeAuth
									&& (BooleanUtils.isNotTrue(classePublicLire)
										|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageUtilisateur))
										|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_Telechargement))
										|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageEdition))
										)
								) {
							if(authPolitiqueGranulee) {
								tl(3, "String ", classeVarId, " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams().getJsonObject(\"path\").getString(\"", classeVarId, "\");");
								tl(3, "MultiMap form = MultiMap.caseInsensitiveMultiMap();");
								tl(3, "form.add(\"grant_type\", \"urn:ietf:params:oauth:grant-type:uma-ticket\");");
								tl(3, "form.add(\"audience\", config.getString(ComputateConfigKeys.AUTH_CLIENT));");
								tl(3, "form.add(\"response_mode\", \"permissions\");");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", config.getString(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_AUTH_PORTEE_ADMIN), ")));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", config.getString(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), ")));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", \"GET\"));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", \"POST\"));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", \"DELETE\"));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", \"PATCH\"));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", \"PUT\"));");
								tl(3, "if(", classeVarId, " != null)");
								tl(4, "form.add(\"permission\", String.format(\"%s-%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", ", classeVarId, ", \"", classeApiMethodeMethode, "\"));");
								if(classeRoleUtilisateur) {
									tl(3, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_PublicLire), "(", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), ");");
								}
								tl(3, "webClient.post(");
								tl(5, "config.getInteger(ComputateConfigKeys.AUTH_PORT)");
								tl(5, ", config.getString(ComputateConfigKeys.AUTH_HOST_NAME)");
								tl(5, ", config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)");
								tl(5, ")");
								tl(5, ".ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))");
								tl(5, ".putHeader(\"Authorization\", String.format(\"Bearer %s\", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString(\"access_token\")).orElse(\"\")))");
								tl(5, ".sendForm(form)");
								tl(5, ".expecting(HttpResponseExpectation.SC_OK)");
								tl(3, ".onComplete(authorizationDecisionResponse -> {");
								tl(4, "try {");
								tl(5, "HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();");
								tl(5, "JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray(\"scopes\")).orElse(new JsonArray());");
								if(classeRessourcesAutorisation.size() > 0) {
									tl(5, "if(!scopes.contains(\"", classeApiMethodeMethode, "\")) {");
									tl(6, "//");
									tl(6, "List<String> fqs = new ArrayList<>();");
									tl(6, "List<String> groups = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".getGroups()).orElse(new ArrayList<>());");
									for(String classeRessourceAutorisation : classeRessourcesAutorisation) {

										tl(6, "groups.stream().map(group -> {");
										tl(9, "Matcher mPermission = Pattern.compile(\"^/", StringUtils.substringBefore(classeRessourceAutorisation, "-"), "-(.*)-", classeApiMethodeMethode, "$\").matcher(group);");
										tl(9, "return mPermission.find() ? mPermission.group(1) : null;");
										tl(8, "}).filter(v -> v != null).forEach(", i18nGlobale.getString(I18n.var_valeur), " -> {");
										tl(9, "fqs.add(String.format(\"%s:%s\", \"", StringUtils.substringAfter(classeRessourceAutorisation, "-"), "\", ", i18nGlobale.getString(I18n.var_valeur), "));");
										tl(8, "});");
									}
									tl(6, "JsonObject authParams = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams();");
									tl(6, "JsonObject authQuery = authParams.getJsonObject(\"query\");");
									tl(6, "if(authQuery == null) {");
									tl(7, "authQuery = new JsonObject();");
									tl(7, "authParams.put(\"query\", authQuery);");
									tl(6, "}");
									tl(6, "JsonArray fq = authQuery.getJsonArray(\"fq\");");
									tl(6, "if(fq == null) {");
									tl(7, "fq = new JsonArray();");
									tl(7, "authQuery.put(\"fq\", fq);");
									tl(6, "}");
									tl(6, "if(fqs.size() > 0) {");
									tl(7, "fq.add(fqs.stream().collect(Collectors.joining(\" OR \")));");
									tl(7, "scopes.add(\"", classeApiMethodeMethode, "\");");
									tl(6, "}");
									tl(5, "}");
									tl(5, "{");
								} else {
									tl(5, "{");
								}
								tl(6, i18nGlobale.getString(I18n.var_requeteSite), ".setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));");
								tl(6, "List<String> scopes2 = ", i18nGlobale.getString(I18n.var_requeteSite), ".getScopes();");
								// if(classeRoleSession || classeRoleUtilisateur || classeRoleChacun) {
								// 	tl(6, "if(!scopes2.contains(\"POST\"))");
								// 	tl(7, "scopes2.add(\"POST\");");
								// 	tl(6, "if(!scopes2.contains(\"PATCH\"))");
								// 	tl(7, "scopes2.add(\"PATCH\");");
								// }
							} else {
								tl(3, "authorizationProvider.getAuthorizations(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Utilisateur), "()).onFailure(ex -> {");
								tl(4, "String msg = String.format(\"403 FORBIDDEN user %s to %s %s\", siteRequest.getUser().attributes().getJsonObject(\"accessToken\").getString(\"preferred_username\"), serviceRequest.getExtra().getString(\"method\"), serviceRequest.getExtra().getString(\"uri\"));");
								tl(4, "eventHandler.handle(Future.succeededFuture(");
								tl(5, "new ServiceResponse(403, \"FORBIDDEN\",");
								tl(6, "Buffer.buffer().appendString(");
								tl(7, "new JsonObject()");
								tl(8, ".put(\"errorCode\", \"403\")");
								tl(8, ".put(\"errorMessage\", msg)");
								tl(8, ".encodePrettily()");
								tl(7, "), MultiMap.caseInsensitiveMultiMap()");
								tl(5, ")");
								tl(4, "));");
								tl(3, "}).onSuccess(b -> {");
								tl(4, "if(");
								tl(6, "!Optional.ofNullable(config.getString(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_AUTH_ROLE_REQUIS), " + \"_", classeNomSimple, "\")).map(v -> RoleBasedAuthorization.create(v).match(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Utilisateur), "())).orElse(false)");
								tl(6, StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH", "DELETE") ? "||" : "&&", " !Optional.ofNullable(Optional.ofNullable(config.getString(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_AUTH_ROLE_LIRE_REQUIS), " + \"_", classeNomSimple, "\")).orElse(config.getString(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_AUTH_ROLE_REQUIS), " + \"_", classeNomSimple, "\"))).map(v -> RoleBasedAuthorization.create(v).match(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Utilisateur), "())).orElse(false)");
								tl(6, ") {");
								tl(5, "String msg = String.format(\"403 FORBIDDEN user %s to %s %s\", siteRequest.getUser().attributes().getJsonObject(\"accessToken\").getString(\"preferred_username\"), serviceRequest.getExtra().getString(\"method\"), serviceRequest.getExtra().getString(\"uri\"));");
								tl(5, "eventHandler.handle(Future.succeededFuture(");
								tl(6, "new ServiceResponse(403, \"FORBIDDEN\",");
								tl(7, "Buffer.buffer().appendString(");
								tl(8, "new JsonObject()");
								tl(9, ".put(\"errorCode\", \"403\")");
								tl(9, ".put(\"errorMessage\", msg)");
								tl(9, ".encodePrettily()");
								tl(8, "), MultiMap.caseInsensitiveMultiMap()");
								tl(6, ")");
								tl(5, "));");
								tl(4, "} else {");
								tl(5, "try {");
							}
						}
						tl(6, i18nGlobale.getString(I18n.var_rechercher), classeApiClasseNomSimple, i18nGlobale.getString(I18n.var_Liste), "(", i18nGlobale.getString(I18n.var_requeteSite), ", false, true, false).onSuccess(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, " -> {");
						tl(7, i18nGlobale.getString(I18n.var_reponse), "200", classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ").onSuccess(", i18nGlobale.getString(I18n.var_reponse), " -> {");
						tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", i18nGlobale.getString(I18n.var_reponse), "));");
						tl(8, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_réussi), ". \"));");
						tl(7, "}).onFailure(ex -> {");
						tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(8, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						tl(7, "});");
						tl(6, "}).onFailure(ex -> {");
						tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(7, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						tl(6, "});");

						if(
									classeAuth
									&& (BooleanUtils.isNotTrue(classePublicLire)
										|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageUtilisateur))
										|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_Telechargement))
										|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageEdition))
										)
								) {
							if(authPolitiqueGranulee) {
								tl(5, "}");
								tl(4, "} catch(Exception ex) {");
								tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(5, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
								tl(4, "}");
							} else {
								tl(5, "} catch(Exception ex) {");
								tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(6, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
								tl(5, "}");
								tl(4, "}");
							}
							tl(3, "});");
						}

						tl(2, "}).onFailure(ex -> {");
						if(activerOpenIdConnect) {
							tl(3, "if(\"Inactive Token\".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), \"invalid_grant:\")) {");
							tl(4, "try {");
							tl(5, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(new ServiceResponse(302, \"Found\", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, \"/", i18nGlobale.getString(I18n.var_deconnexion), "?redirect_uri=\" + URLEncoder.encode(serviceRequest.getExtra().getString(\"uri\"), \"UTF-8\")))));");
							tl(4, "} catch(Exception ex2) {");
							tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \", ex2));");
							tl(5, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex2);");
							tl(4, "}");
							tl(3, "} else if(StringUtils.startsWith(ex.getMessage(), \"401 UNAUTHORIZED \")) {");
							tl(4, "eventHandler.handle(Future.succeededFuture(");
							tl(5, "new ServiceResponse(401, \"UNAUTHORIZED\",");
							tl(6, "Buffer.buffer().appendString(");
							tl(7, "new JsonObject()");
							tl(8, ".put(\"errorCode\", \"401\")");
							tl(8, ".put(\"errorMessage\", \"SSO Resource Permission check returned DENY\")");
							tl(8, ".encodePrettily()");
							tl(7, "), MultiMap.caseInsensitiveMultiMap()");
							tl(7, ")");
							tl(5, "));");
							tl(3, "} else {");
							tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(4, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(3, "}");
						} else {
							tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(3, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						}
						tl(2, "});");
						tl(1, "}");
					} else {

						/////////
						// API //
						/////////
						l();
						tl(1, "@Override");
						t(1, "public void ", classeApiOperationIdMethode, "(");
						if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH", "DELETE"))
							s("" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ");
						l("ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ") {");
						if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH", "DELETE"))
							tl(2, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_démarré), ". \"));");
	
						if(classeRessourcesAutorisation.size() > 0 && classeApiMethode.equals(i18nGlobale.getString(I18n.var_PageRecherche))) {
							tl(2, "oauth2AuthenticationProvider.refresh(User.create(", i18nGlobale.getString(I18n.var_requeteService), ".getUser())).onSuccess(user -> {");
							tl(3, "serviceRequest.setUser(user.principal());");
						}
						tl(2, "Boolean ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), " = ", classePublicLire || classeRoleSession || classeRoleUtilisateur, ";");
						tl(2, i18nGlobale.getString(I18n.var_utilisateur), "(", i18nGlobale.getString(I18n.var_requeteService), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".get", i18nGlobale.getString(I18n.var_ClasseApiAddresse), "(), \"post", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", \"patch", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), ").onSuccess(", i18nGlobale.getString(I18n.var_requeteSite), " -> {");
						if(
									classeAuth
									&& BooleanUtils.isNotTrue(classePublicLire) || !StringUtils.equals(classeApiMethodeMethode, "GET")
								) {
							if(authPolitiqueGranulee) {
								if(classeVarId != null)
									tl(3, "String ", classeVarId, " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams().getJsonObject(\"path\").getString(\"", classeVarId, "\");");
								tl(3, "MultiMap form = MultiMap.caseInsensitiveMultiMap();");
								tl(3, "form.add(\"grant_type\", \"urn:ietf:params:oauth:grant-type:uma-ticket\");");
								tl(3, "form.add(\"audience\", config.getString(ComputateConfigKeys.AUTH_CLIENT));");
								tl(3, "form.add(\"response_mode\", \"permissions\");");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", config.getString(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_AUTH_PORTEE_ADMIN), ")));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", config.getString(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), ")));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", \"GET\"));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", \"POST\"));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", \"DELETE\"));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", \"PATCH\"));");
								tl(3, "form.add(\"permission\", String.format(\"%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", \"PUT\"));");
								if(classeVarId != null) {
									tl(3, "if(", classeVarId, " != null)");
									tl(4, "form.add(\"permission\", String.format(\"%s-%s#%s\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ", ", classeVarId, ", \"", classeApiMethodeMethode, "\"));");
								}
								if(classeRoleUtilisateur) {
									tl(3, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_PublicLire), "(", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), ");");
								}
								tl(3, "webClient.post(");
								tl(5, "config.getInteger(ComputateConfigKeys.AUTH_PORT)");
								tl(5, ", config.getString(ComputateConfigKeys.AUTH_HOST_NAME)");
								tl(5, ", config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)");
								tl(5, ")");
								tl(5, ".ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))");
								tl(5, ".putHeader(\"Authorization\", String.format(\"Bearer %s\", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString(\"access_token\")).orElse(\"\")))");
								tl(5, ".sendForm(form)");
								tl(5, ".expecting(HttpResponseExpectation.SC_OK)");
								tl(3, ".onComplete(authorizationDecisionResponse -> {");
								tl(4, "try {");
								tl(5, "HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();");
								tl(5, "JsonArray scopes = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray().stream().findFirst().map(decision -> ((JsonObject)decision).getJsonArray(\"scopes\")).orElse(new JsonArray());");
								if(classeRessourcesAutorisation.size() > 0) {
									tl(5, "if(!scopes.contains(\"", classeApiMethodeMethode, "\")) {");
									tl(6, "//");
									tl(6, "List<String> fqs = new ArrayList<>();");
									tl(6, "List<String> groups = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".getGroups()).orElse(new ArrayList<>());");
									for(String classeRessourceAutorisation : classeRessourcesAutorisation) {

										tl(6, "groups.stream().map(group -> {");
										tl(9, "Matcher mPermission = Pattern.compile(\"^/", StringUtils.substringBefore(classeRessourceAutorisation, "-"), "-(.*)-", classeApiMethodeMethode, "$\").matcher(group);");
										tl(9, "return mPermission.find() ? mPermission.group(1) : null;");
										tl(8, "}).filter(v -> v != null).forEach(", i18nGlobale.getString(I18n.var_valeur), " -> {");
										tl(9, "fqs.add(String.format(\"%s:%s\", \"", StringUtils.substringAfter(classeRessourceAutorisation, "-"), "\", ", i18nGlobale.getString(I18n.var_valeur), "));");
										tl(8, "});");
									}
									tl(6, "JsonObject authParams = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams();");
									tl(6, "JsonObject authQuery = authParams.getJsonObject(\"query\");");
									tl(6, "if(authQuery == null) {");
									tl(7, "authQuery = new JsonObject();");
									tl(7, "authParams.put(\"query\", authQuery);");
									tl(6, "}");
									tl(6, "JsonArray fq = authQuery.getJsonArray(\"fq\");");
									tl(6, "if(fq == null) {");
									tl(7, "fq = new JsonArray();");
									tl(7, "authQuery.put(\"fq\", fq);");
									tl(6, "}");
									tl(6, "if(fqs.size() > 0) {");
									tl(7, "fq.add(fqs.stream().collect(Collectors.joining(\" OR \")));");
									tl(7, "scopes.add(\"", classeApiMethodeMethode, "\");");
									tl(6, "}");
									tl(5, "}");
								}
								if(StringUtils.equals(classeApiMethodeMethode, "GET")) {
									tl(5, "{");
								} else {
									if(classeRoleUtilisateur) {
										tl(5, "scopes.add(\"GET\");");
										tl(5, "scopes.add(\"PATCH\");");
									}
									tl(5, "if(authorizationDecisionResponse.failed() && !scopes.contains(\"", classeApiMethodeMethode, "\")) {");
									tl(6, "String msg = String.format(\"403 FORBIDDEN user %s to %s %s\", siteRequest.getUser().attributes().getJsonObject(\"accessToken\").getString(\"preferred_username\"), serviceRequest.getExtra().getString(\"method\"), serviceRequest.getExtra().getString(\"uri\"));");
									tl(6, "eventHandler.handle(Future.succeededFuture(");
									tl(7, "new ServiceResponse(403, \"FORBIDDEN\",");
									tl(8, "Buffer.buffer().appendString(");
									tl(9, "new JsonObject()");
									tl(10, ".put(\"errorCode\", \"403\")");
									tl(10, ".put(\"errorMessage\", msg)");
									tl(10, ".encodePrettily()");
									tl(9, "), MultiMap.caseInsensitiveMultiMap()");
									tl(7, ")");
									tl(6, "));");
									tl(5, "} else {");
								}
								tl(6, i18nGlobale.getString(I18n.var_requeteSite), ".setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));");
								tl(6, "List<String> scopes2 = ", i18nGlobale.getString(I18n.var_requeteSite), ".getScopes();");
							} else {
								tl(3, "authorizationProvider.getAuthorizations(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Utilisateur), "()).onFailure(ex -> {");
								tl(4, "String msg = String.format(\"403 FORBIDDEN user %s to %s %s\", siteRequest.getUser().attributes().getJsonObject(\"accessToken\").getString(\"preferred_username\"), serviceRequest.getExtra().getString(\"method\"), serviceRequest.getExtra().getString(\"uri\"));");
								tl(4, "eventHandler.handle(Future.succeededFuture(");
								tl(5, "new ServiceResponse(403, \"FORBIDDEN\",");
								tl(6, "Buffer.buffer().appendString(");
								tl(7, "new JsonObject()");
								tl(8, ".put(\"errorCode\", \"403\")");
								tl(8, ".put(\"errorMessage\", msg)");
								tl(8, ".encodePrettily()");
								tl(7, "), MultiMap.caseInsensitiveMultiMap()");
								tl(5, ")");
								tl(4, "));");
								tl(3, "}).onSuccess(b -> {");
								tl(4, "if(");
								tl(6, "!Optional.ofNullable(config.getString(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_AUTH_ROLE_REQUIS), " + \"_", classeNomSimple, "\")).map(v -> RoleBasedAuthorization.create(v).match(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Utilisateur), "())).orElse(false)");
								tl(6, StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH", "DELETE") ? "||" : "&&", " !Optional.ofNullable(Optional.ofNullable(config.getString(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_AUTH_ROLE_LIRE_REQUIS), " + \"_", classeNomSimple, "\")).orElse(config.getString(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_AUTH_ROLE_REQUIS), " + \"_", classeNomSimple, "\"))).map(v -> RoleBasedAuthorization.create(v).match(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Utilisateur), "())).orElse(false)");
								tl(6, ") {");
								tl(5, "String msg = String.format(\"403 FORBIDDEN user %s to %s %s\", siteRequest.getUser().attributes().getJsonObject(\"accessToken\").getString(\"preferred_username\"), serviceRequest.getExtra().getString(\"method\"), serviceRequest.getExtra().getString(\"uri\"));");
								tl(5, "eventHandler.handle(Future.succeededFuture(");
								tl(6, "new ServiceResponse(403, \"FORBIDDEN\",");
								tl(7, "Buffer.buffer().appendString(");
								tl(8, "new JsonObject()");
								tl(9, ".put(\"errorCode\", \"403\")");
								tl(9, ".put(\"errorMessage\", msg)");
								tl(9, ".encodePrettily()");
								tl(8, "), MultiMap.caseInsensitiveMultiMap()");
								tl(6, ")");
								tl(5, "));");
								tl(4, "} else {");
								tl(5, "try {");
							}
						}

						if(classeApiMethode.contains("POST")) {

							//////////////
							// /modele: //
							//////////////

							tl(6, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
							tl(6, i18nGlobale.getString(I18n.var_requeteApi), ".setRows(1L);");
							tl(6, i18nGlobale.getString(I18n.var_requeteApi), ".setNumFound(1L);");
							tl(6, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(0L);");
							tl(6, i18nGlobale.getString(I18n.var_requeteApi), ".", i18nGlobale.getString(I18n.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", i18nGlobale.getString(I18n.var_requeteSite), ");");
							tl(6, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteApi), ");");
							tl(6, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
							tl(6, "JsonObject params = new JsonObject();");
							tl(6, "params.put(\"body\", ", i18nGlobale.getString(I18n.var_requeteSite), ".getJsonObject());");
							tl(6, "params.put(\"path\", new JsonObject());");
							tl(6, "params.put(\"cookie\", ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams().getJsonObject(\"cookie\"));");
							tl(6, "params.put(\"header\", ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams().getJsonObject(\"header\"));");
							tl(6, "params.put(\"form\", new JsonObject());");
							tl(6, "JsonObject query = new JsonObject();");
							tl(6, "Boolean softCommit = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getBoolean(\"softCommit\")).orElse(null);");
							tl(6, "Integer commitWithin = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getInteger(\"commitWithin\")).orElse(null);");
							tl(6, "if(softCommit == null && commitWithin == null)");
							tl(7, "softCommit = true;");
							tl(6, "if(softCommit != null)");
							tl(7, "query.put(\"softCommit\", softCommit);");
							tl(6, "if(commitWithin != null)");
							tl(7, "query.put(\"commitWithin\", commitWithin);");
							tl(6, "params.put(\"query\", query);");
							tl(6, "JsonObject context = new JsonObject().put(\"params\", params).put(\"user\", ", i18nGlobale.getString(I18n.var_requeteSite), ".getUserPrincipal());");
							tl(6, "JsonObject json = new JsonObject().put(\"context\", context);");
							tl(6, "eventBus.request(", classeNomSimple, ".get", i18nGlobale.getString(I18n.var_ClasseApiAddresse), "(), json, new DeliveryOptions().addHeader(\"action\", \"", classeApiOperationIdMethode, "Future\")).onSuccess(a -> {");
							tl(7, "JsonObject responseMessage = (JsonObject)a.body();");
							tl(7, "JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString(\"payload\"))));");
							if(classeModele)
								tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".set", i18nGlobale.getString(I18n.var_SolrId), "(responseBody.getString(", classeNomSimple, ".VAR_", solrId, "));");
							tl(7, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));");
							tl(7, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_réussi), ". \"));");
							tl(6, "}).onFailure(ex -> {");
							tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(7, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(6, "});");
						}
						else if(classeApiMethode.contains("DELETE")) {
							tl(6, i18nGlobale.getString(I18n.var_rechercher), classeApiClasseNomSimple, i18nGlobale.getString(I18n.var_Liste), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", (classeModele ? "false, true" : "true, false"), ", true).onSuccess(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, " -> {");
							tl(7, "try {");
							Integer tBase;
							tBase = 0;
							tl(tBase + 8, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
							tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteApi), ".setRows(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".getRequest().getRows());");
							tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteApi), ".setNumFound(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".getResponse().getResponse().getNumFound());");
							tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(0L);");
							tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteApi), ".", i18nGlobale.getString(I18n.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", i18nGlobale.getString(I18n.var_requeteSite), ");");
							tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteApi), ");");
							tl(tBase + 8, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumFound() == 1L)");
							tl(tBase + 9, i18nGlobale.getString(I18n.var_requeteApi), ".setOriginal(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first());");
							if(classeModele)
								tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteApi), ".set", i18nGlobale.getString(I18n.var_SolrId), "(Optional.ofNullable(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first()).map(o2 -> o2.get", solrIdCapitalise, "()).orElse(null));");
							tl(tBase + 8, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
							l();
							tl(tBase + 8, i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteApi), ", ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ").onSuccess(e -> {");
							tl(tBase + 9, i18nGlobale.getString(I18n.var_reponse), "200", classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(", i18nGlobale.getString(I18n.var_reponse), " -> {");
							tl(tBase + 10, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_réussi), ". \"));");
							tl(tBase + 10, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", i18nGlobale.getString(I18n.var_reponse), "));");
							tl(tBase + 9, "}).onFailure(ex -> {");
							tl(tBase + 10, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(tBase + 10, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(tBase + 9, "});");
							tl(tBase + 8, "}).onFailure(ex -> {");
							tl(tBase + 9, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(tBase + 9, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(tBase + 8, "});");
							tl(7, "} catch(Exception ex) {");
							tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(8, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(7, "}");
							tl(6, "}).onFailure(ex -> {");
							tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(7, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(6, "});");
						}
						else if(classeApiMethode.contains("PATCH")) {
							tl(6, i18nGlobale.getString(I18n.var_rechercher), classeApiClasseNomSimple, i18nGlobale.getString(I18n.var_Liste), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", (classeModele ? "false, true" : "true, false"), ", true).onSuccess(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, " -> {");
							tl(7, "try {");
							Integer tBase;
							tBase = 0;
							tl(tBase + 8, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
							tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteApi), ".setRows(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".getRequest().getRows());");
							tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteApi), ".setNumFound(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".getResponse().getResponse().getNumFound());");
							tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(0L);");
							tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteApi), ".", i18nGlobale.getString(I18n.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", i18nGlobale.getString(I18n.var_requeteSite), ");");
							tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteApi), ");");
							tl(tBase + 8, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumFound() == 1L)");
							tl(tBase + 9, i18nGlobale.getString(I18n.var_requeteApi), ".setOriginal(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first());");
							tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteApi), ".setId(Optional.ofNullable(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first()).map(o2 -> o2.get", StringUtils.capitalize(classeVarId), "().toString()).orElse(null));");
							if(classeModele)
								tl(tBase + 8, i18nGlobale.getString(I18n.var_requeteApi), ".set", i18nGlobale.getString(I18n.var_SolrId), "(Optional.ofNullable(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first()).map(o2 -> o2.get", solrIdCapitalise, "()).orElse(null));");
							tl(tBase + 8, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
							l();
							tl(tBase + 8, i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteApi), ", ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ").onSuccess(e -> {");
							tl(tBase + 9, i18nGlobale.getString(I18n.var_reponse), "200", classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(", i18nGlobale.getString(I18n.var_reponse), " -> {");
							tl(tBase + 10, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_réussi), ". \"));");
							tl(tBase + 10, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", i18nGlobale.getString(I18n.var_reponse), "));");
							tl(tBase + 9, "}).onFailure(ex -> {");
							tl(tBase + 10, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(tBase + 10, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(tBase + 9, "});");
							tl(tBase + 8, "}).onFailure(ex -> {");
							tl(tBase + 9, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(tBase + 9, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(tBase + 8, "});");
							tl(7, "} catch(Exception ex) {");
							tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(8, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(7, "}");
							tl(6, "}).onFailure(ex -> {");
							tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(7, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(6, "});");
						}
						else if(classePageAvecTemplateMethode || classeApiMethode.contains(i18nGlobale.getString(I18n.var_Recherche))) {
							tl(6, i18nGlobale.getString(I18n.var_rechercher), classeApiClasseNomSimple, i18nGlobale.getString(I18n.var_Liste), "(", i18nGlobale.getString(I18n.var_requeteSite), ", false, true, false).onSuccess(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, " -> {");
							tl(7, i18nGlobale.getString(I18n.var_reponse), "200", classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ").onSuccess(", i18nGlobale.getString(I18n.var_reponse), " -> {");
							tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", i18nGlobale.getString(I18n.var_reponse), "));");
							tl(8, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_réussi), ". \"));");
							tl(7, "}).onFailure(ex -> {");
							tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(8, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(7, "});");
							tl(6, "}).onFailure(ex -> {");
							tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(7, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(6, "});");
						}
						else if(classeApiMethode.contains("GET")) {
							tl(6, i18nGlobale.getString(I18n.var_rechercher), classeApiClasseNomSimple, i18nGlobale.getString(I18n.var_Liste), "(", i18nGlobale.getString(I18n.var_requeteSite), ", false, true, false).onSuccess(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, " -> {");
							tl(7, i18nGlobale.getString(I18n.var_reponse), "200", classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ").onSuccess(", i18nGlobale.getString(I18n.var_reponse), " -> {");
							tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", i18nGlobale.getString(I18n.var_reponse), "));");
							tl(8, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_réussi), ". \"));");
							tl(7, "}).onFailure(ex -> {");
							tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(8, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(7, "});");
							tl(6, "}).onFailure(ex -> {");
							tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(7, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(6, "});");
						}
						else if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTCopie))) {
							tl(6, i18nGlobale.getString(I18n.var_rechercher), classeApiClasseNomSimple, i18nGlobale.getString(I18n.var_Liste), "(", i18nGlobale.getString(I18n.var_requeteSite), ", false, true, true).onSuccess(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, " -> {");
							tl(7, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
							tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setRows(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".getRequest().getRows());");
							tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setNumFound(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".getResponse().getResponse().getNumFound());");
							tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(0L);");
							tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".", i18nGlobale.getString(I18n.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", i18nGlobale.getString(I18n.var_requeteSite), ");");
							tl(7, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteApi), ");");
							tl(7, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
							tl(7, i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteApi), ", ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ").onSuccess(o -> {");
							tl(8, i18nGlobale.getString(I18n.var_reponse), "200", classeApiMethode, classeNomSimple, "(o).onSuccess(", i18nGlobale.getString(I18n.var_reponse), " -> {");
							tl(9, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_réussi), ". \"));");
							tl(9, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", i18nGlobale.getString(I18n.var_reponse), "));");
							tl(8, "}).onFailure(ex -> {");
							tl(9, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(9, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(8, "});");
							tl(7, "}).onFailure(ex -> {");
							tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(8, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(7, "});");
							tl(6, "}).onFailure(ex -> {");
							tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(7, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(6, "});");
						}
						else if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
							tl(6, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
							tl(6, "JsonArray jsonArray = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ObjetJson), "()).map(o -> o.getJsonArray(\"list\")).orElse(new JsonArray());");
							tl(6, i18nGlobale.getString(I18n.var_requeteApi), ".setRows(Long.valueOf(jsonArray.size()));");
							tl(6, i18nGlobale.getString(I18n.var_requeteApi), ".setNumFound(Long.valueOf(jsonArray.size()));");
							tl(6, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(0L);");
							tl(6, i18nGlobale.getString(I18n.var_requeteApi), ".", i18nGlobale.getString(I18n.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", i18nGlobale.getString(I18n.var_requeteSite), ");");
							tl(6, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteApi), ");");
							tl(6, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
							tl(6, "vars", classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(d -> {");
							tl(7, i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteApi), ", ", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(e -> {");
							tl(8, i18nGlobale.getString(I18n.var_reponse), "200", classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(", i18nGlobale.getString(I18n.var_reponse), " -> {");
							tl(9, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_réussi), ". \"));");
							tl(9, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", i18nGlobale.getString(I18n.var_reponse), "));");
							tl(8, "}).onFailure(ex -> {");
							tl(9, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(9, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(8, "});");
							tl(7, "}).onFailure(ex -> {");
							tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(8, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(7, "});");
							tl(6, "}).onFailure(ex -> {");
							tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(7, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(6, "});");
						}
						else {
							tl(6, i18nGlobale.getString(I18n.var_reponse), "200", classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(", i18nGlobale.getString(I18n.var_reponse), " -> {");
							tl(7, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", i18nGlobale.getString(I18n.var_reponse), "));");
							tl(7, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_réussi), ". \"));");
							tl(6, "}).onFailure(ex -> {");
							tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \", ex));");
							tl(7, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(6, "});");
						}

						if(
									classeAuth
									&& BooleanUtils.isNotTrue(classePublicLire) || !StringUtils.equals(classeApiMethodeMethode, "GET")
								) {
							if(authPolitiqueGranulee) {
								tl(5, "}");
								tl(4, "} catch(Exception ex) {");
								tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(5, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
								tl(4, "}");
							} else {
								tl(5, "} catch(Exception ex) {");
								tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(6, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
								tl(5, "}");
								tl(4, "}");
							}
							tl(3, "});");
						}

						tl(2, "}).onFailure(ex -> {");
						if(activerOpenIdConnect) {
							tl(3, "if(\"Inactive Token\".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), \"invalid_grant:\")) {");
							tl(4, "try {");
							tl(5, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(new ServiceResponse(302, \"Found\", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, \"/", i18nGlobale.getString(I18n.var_deconnexion), "?redirect_uri=\" + URLEncoder.encode(serviceRequest.getExtra().getString(\"uri\"), \"UTF-8\")))));");
							tl(4, "} catch(Exception ex2) {");
							tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \", ex2));");
							tl(5, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex2);");
							tl(4, "}");
							tl(3, "} else if(StringUtils.startsWith(ex.getMessage(), \"401 UNAUTHORIZED \")) {");
							tl(4, "eventHandler.handle(Future.succeededFuture(");
							tl(5, "new ServiceResponse(401, \"UNAUTHORIZED\",");
							tl(6, "Buffer.buffer().appendString(");
							tl(7, "new JsonObject()");
							tl(8, ".put(\"errorCode\", \"401\")");
							tl(8, ".put(\"errorMessage\", \"SSO Resource Permission check returned DENY\")");
							tl(8, ".encodePrettily()");
							tl(7, "), MultiMap.caseInsensitiveMultiMap()");
							tl(7, ")");
							tl(5, "));");
							tl(3, "} else {");
							tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(4, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(3, "}");
						} else {
							tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(3, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						}
						tl(2, "});");
						if(classeRessourcesAutorisation.size() > 0 && classeApiMethode.equals(i18nGlobale.getString(I18n.var_PageRecherche))) {
							tl(2, "}).onFailure(ex -> {");
							if(activerOpenIdConnect) {
								tl(3, "if(\"Inactive Token\".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), \"invalid_grant:\")) {");
								tl(4, "try {");
								tl(5, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(new ServiceResponse(302, \"Found\", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, \"/", i18nGlobale.getString(I18n.var_deconnexion), "?redirect_uri=\" + URLEncoder.encode(serviceRequest.getExtra().getString(\"uri\"), \"UTF-8\")))));");
								tl(4, "} catch(Exception ex2) {");
								tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \", ex2));");
								tl(5, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex2);");
								tl(4, "}");
								tl(3, "} else if(StringUtils.startsWith(ex.getMessage(), \"401 UNAUTHORIZED \")) {");
								tl(4, "eventHandler.handle(Future.succeededFuture(");
								tl(5, "new ServiceResponse(401, \"UNAUTHORIZED\",");
								tl(6, "Buffer.buffer().appendString(");
								tl(7, "new JsonObject()");
								tl(8, ".put(\"errorCode\", \"401\")");
								tl(8, ".put(\"errorMessage\", \"SSO Resource Permission check returned DENY\")");
								tl(8, ".encodePrettily()");
								tl(7, "), MultiMap.caseInsensitiveMultiMap()");
								tl(7, ")");
								tl(5, "));");
								tl(3, "} else {");
								tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(4, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
								tl(3, "}");
							} else {
								tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(3, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							}
							tl(2, "});");
						}
						tl(1, "}");
					}
	
					///////////
					// Liste //
					///////////
					if(classeApiMethode.contains("PATCH") || classeApiMethode.contains("DELETE")) {
						l();
						tl(1, "public Future<Void> ", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, "(", classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ") {");
						tl(2, "Promise<Void> promise = Promise.promise();");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class);");
						tl(2, i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".getList().forEach(o -> {");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), "2 = ", i18nGlobale.getString(I18n.var_generer), i18nGlobale.getString(I18n.var_RequeteSite), "(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Utilisateur), "(), ", i18nGlobale.getString(I18n.var_requeteSite), ".getUserPrincipal(), ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "(), ", i18nGlobale.getString(I18n.var_requeteSite), ".getJsonObject(), ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class);");
						tl(3, i18nGlobale.getString(I18n.var_requeteSite), "2.setScopes(", i18nGlobale.getString(I18n.var_requeteSite), ".getScopes());");
						tl(3, "o.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), "2);");
						tl(3, i18nGlobale.getString(I18n.var_requeteSite), "2.set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_());");
						tl(3, "JsonObject jsonObject = JsonObject.mapFrom(o);");
						tl(3, classeNomSimple, " o2 = jsonObject.mapTo(", classeNomSimple, ".class);");
						tl(3, "o2.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), "2);");
						tl(3, "futures.add(Future.future(promise1 -> {");
						tl(4, classeApiOperationIdMethode, "Future(", classeApiMethode.contains("PATCH") ? "o2, false" : "o", ").onSuccess(a -> {");
						tl(5, "promise1.complete();");
						tl(4, "}).onFailure(ex -> {");
						tl(5, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(5, "promise1.fail(ex);");
						tl(4, "});");
						tl(3, "}));");
						tl(2, "});");
						tl(2, "CompositeFuture.all(futures).onSuccess( a -> {");
						tl(3, i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".next().onSuccess(", i18nGlobale.getString(I18n.var_suivant), " -> {");
						tl(4, "if(", i18nGlobale.getString(I18n.var_suivant), ") {");
						tl(5, i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteApi), ", ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ").onSuccess(b -> {");
						tl(6, "promise.complete();");
						tl(5, "}).onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(6, "promise.fail(ex);");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "promise.complete();");
						tl(4, "}");
						tl(3, "}).onFailure(ex -> {");
						tl(4, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(4, "promise.fail(ex);");
						tl(3, "});");
						tl(2, "}).onFailure(ex -> {");
						tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(3, "promise.fail(ex);");
						tl(2, "});");
						tl(2, "return promise.future();");
						tl(1, "}");
					}

					if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTCopie))) {
						l();
						tl(1, "public Future<", classeNomSimple, "> ", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, "(", classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ") {");
						tl(2, "Promise<", classeNomSimple, "> promise = Promise.promise();");
						tl(2, "if(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".getSize() == 1) {");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class);");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), "2 = ", i18nGlobale.getString(I18n.var_requeteSite), ".copy();");
						tl(3, i18nGlobale.getString(I18n.var_requeteSite), "2.set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_());");
						tl(3, classeNomSimple, " o = ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first();");
						tl(3, "o.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), "2);");
						tl(3, classeApiOperationIdMethode, "Future(", classeApiMethode.contains("PATCH") ? "o" : (i18nGlobale.getString(I18n.var_requeteSite) + "2, JsonObject.mapFrom(o))"), ".onSuccess(o2 -> {");
						tl(4, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumPATCH() + ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".size());");
						tl(4, "promise.complete(o2);");
						tl(3, "}).onFailure(ex -> {");
						tl(4, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(4, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class), null, ex);");
						tl(3, "});");
						tl(2, "} else {");
						tl(3, "List<Future> futures = new ArrayList<>();");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class);");
						tl(3, i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".getList().forEach(o -> {");
						tl(4, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), "2 = ", i18nGlobale.getString(I18n.var_requeteSite), ".copy();");
						tl(4, i18nGlobale.getString(I18n.var_requeteSite), "2.set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_());");
						tl(4, "o.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), "2);");
						tl(4, "futures.add(");
						tl(5, classeApiOperationIdMethode, "Future(", classeApiMethode.contains("PATCH") ? "o" : (i18nGlobale.getString(I18n.var_requeteSite) + "2, JsonObject.mapFrom(o))"), ".onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(6, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", null, ex);");
						tl(5, "})");
						tl(4, ");");
						tl(3, "});");
						tl(3, "CompositeFuture.all(futures).onSuccess(a -> {");
						tl(4, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumPATCH() + ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".size());");
						tl(4, i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".next().onSuccess(", i18nGlobale.getString(I18n.var_suivant), " -> {");
						tl(5, "if(", i18nGlobale.getString(I18n.var_suivant), ") {");
						tl(6, i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteApi), ", ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ").onSuccess(b -> {");
						tl(7, "promise.complete();");
						tl(6, "}).onFailure(ex -> {");
						tl(7, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(7, "promise.fail(ex);");
						tl(6, "});");
						tl(5, "} else {");
						tl(6, "promise.complete();");
						tl(5, "}");
						tl(4, "}).onFailure(ex -> {");
						tl(5, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(5, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class), null, ex);");
						tl(4, "});");
						tl(3, "}).onFailure(ex -> {");
						tl(4, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(4, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class), null, ex);");
						tl(3, "});");
						tl(2, "}");
						tl(2, "return promise.future();");
						tl(1, "}");
					}
					else if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
						l();
						tl(1, "public Future<Void> ", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, "(", classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), ") {");
						tl(2, "Promise<Void> promise = Promise.promise();");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, "JsonArray jsonArray = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ObjetJson), "()).map(o -> o.getJsonArray(\"list\")).orElse(new JsonArray());");
						tl(2, "try {");
						tl(3, "jsonArray.forEach(obj -> {");
						tl(4, "futures.add(Future.future(promise1 -> {");
						tl(5, "JsonObject params = new JsonObject();");
						tl(5, "params.put(\"body\", obj);");
						tl(5, "params.put(\"path\", new JsonObject());");
						tl(5, "params.put(\"cookie\", ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams().getJsonObject(\"cookie\"));");
						tl(5, "params.put(\"header\", ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams().getJsonObject(\"header\"));");
						tl(5, "params.put(\"form\", new JsonObject());");
						tl(5, "JsonObject query = new JsonObject();");
						tl(5, "Boolean softCommit = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getBoolean(\"softCommit\")).orElse(null);");
						tl(5, "Integer commitWithin = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getInteger(\"commitWithin\")).orElse(null);");
						tl(5, "if(softCommit == null && commitWithin == null)");
						tl(6, "softCommit = true;");
						tl(5, "if(softCommit != null)");
						tl(6, "query.put(\"softCommit\", softCommit);");
						tl(5, "if(commitWithin != null)");
						tl(6, "query.put(\"commitWithin\", commitWithin);");
						tl(5, "params.put(\"query\", query);");
						tl(5, "JsonObject context = new JsonObject().put(\"params\", params).put(\"user\", ", i18nGlobale.getString(I18n.var_requeteSite), ".getUserPrincipal());");
						tl(5, "JsonObject json = new JsonObject().put(\"context\", context);");
						tl(5, "eventBus.request(", classeNomSimple, ".get", i18nGlobale.getString(I18n.var_ClasseApiAddresse), "(), json, new DeliveryOptions().addHeader(\"action\", \"", classeApiOperationIdMethode, "Future\")).onSuccess(a -> {");
						tl(6, "promise1.complete();");
						tl(5, "}).onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(6, "promise1.fail(ex);");
						tl(5, "});");
						tl(4, "}));");
						tl(3, "});");
						tl(3, "CompositeFuture.all(futures).onSuccess(a -> {");
						tl(4, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumPATCH() + 1);");
						tl(4, "promise.complete();");
						tl(3, "}).onFailure(ex -> {");
						tl(4, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(4, "promise.fail(ex);");
						tl(3, "});");
						tl(2, "} catch(Exception ex) {");
						tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_liste), classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(3, "promise.fail(ex);");
						tl(2, "}");
						tl(2, "return promise.future();");
						tl(1, "}");
					}

					////////////
					// Future //
					////////////
					if(StringUtils.contains(classeApiMethode, "POST")) {
						l();
						tl(1, "@Override");
						tl(1, "public void ", classeApiOperationIdMethode, "Future(" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ") {");
						tl(2, "Boolean ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), " = ", classePublicLire || classeRoleSession || classeRoleUtilisateur, ";");
						tl(2, i18nGlobale.getString(I18n.var_utilisateur), "(", i18nGlobale.getString(I18n.var_requeteService), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".get", i18nGlobale.getString(I18n.var_ClasseApiAddresse), "(), \"post", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", \"patch", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), ").onSuccess(", i18nGlobale.getString(I18n.var_requeteSite), " -> {");
						tl(3, "try {");
						tl(4, "Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteService), ".getParams().getJsonArray(\"scopes\")).ifPresent(scopes -> {");
						tl(5, "scopes.stream().map(v -> v.toString()).forEach(scope -> {");
						tl(6, i18nGlobale.getString(I18n.var_requeteSite), ".addScopes(scope);");
						tl(5, "});");
						tl(4, "});");
						tl(4, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(4, i18nGlobale.getString(I18n.var_requeteApi), ".setRows(1L);");
						tl(4, i18nGlobale.getString(I18n.var_requeteApi), ".setNumFound(1L);");
						tl(4, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(0L);");
						tl(4, i18nGlobale.getString(I18n.var_requeteApi), ".", i18nGlobale.getString(I18n.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", i18nGlobale.getString(I18n.var_requeteSite), ");");
						tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteApi), ");");
						tl(4, "if(Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteService), ".getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getJsonArray(\"var\")).orElse(new JsonArray()).stream().filter(s -> \"", i18nGlobale.getString(I18n.var_recharger), ":false\".equals(s)).count() > 0L) {");
						tl(5, i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Requete), "Vars().put( \"", i18nGlobale.getString(I18n.var_recharger), "\", \"false\" );");
						tl(4, "}");
						tl(4, classeApiOperationIdMethode, "Future(", i18nGlobale.getString(I18n.var_requeteSite), ", false).onSuccess(o -> {");
						tl(5, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));");
						tl(4, "}).onFailure(ex -> {");
						tl(5, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
						tl(4, "});");
						tl(3, "} catch(Throwable ex) {");
						tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(4, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						tl(3, "}");
						tl(2, "}).onFailure(ex -> {");
						if(activerOpenIdConnect) {
							tl(3, "if(\"Inactive Token\".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), \"invalid_grant:\")) {");
							tl(4, "try {");
							tl(5, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(new ServiceResponse(302, \"Found\", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, \"/", i18nGlobale.getString(I18n.var_deconnexion), "?redirect_uri=\" + URLEncoder.encode(serviceRequest.getExtra().getString(\"uri\"), \"UTF-8\")))));");
							tl(4, "} catch(Exception ex2) {");
							tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \", ex2));");
							tl(5, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex2);");
							tl(4, "}");
							tl(3, "} else if(StringUtils.startsWith(ex.getMessage(), \"401 UNAUTHORIZED \")) {");
							tl(4, "eventHandler.handle(Future.succeededFuture(");
							tl(5, "new ServiceResponse(401, \"UNAUTHORIZED\",");
							tl(6, "Buffer.buffer().appendString(");
							tl(7, "new JsonObject()");
							tl(8, ".put(\"errorCode\", \"401\")");
							tl(8, ".put(\"errorMessage\", \"SSO Resource Permission check returned DENY\")");
							tl(8, ".encodePrettily()");
							tl(7, "), MultiMap.caseInsensitiveMultiMap()");
							tl(7, ")");
							tl(5, "));");
							tl(3, "} else {");
							tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(4, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(3, "}");
						} else {
							tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(3, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						}
						tl(2, "});");
						tl(1, "}");
					} else if(StringUtils.contains(classeApiMethode, "DELETE")) {
						l();
						tl(1, "@Override");
						tl(1, "public void ", classeApiOperationIdMethode, "Future(" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ") {");
						tl(2, "Boolean ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), " = ", classePublicLire || classeRoleSession || classeRoleUtilisateur, ";");
						tl(2, i18nGlobale.getString(I18n.var_utilisateur), "(", i18nGlobale.getString(I18n.var_requeteService), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".get", i18nGlobale.getString(I18n.var_ClasseApiAddresse), "(), \"post", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", \"patch", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), ").onSuccess(", i18nGlobale.getString(I18n.var_requeteSite), " -> {");
						tl(3, "try {");
						tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".setJsonObject(body);");
						tl(4, i18nGlobale.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").put(\"rows\", 1);");
						tl(4, "Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteService), ".getParams().getJsonArray(\"scopes\")).ifPresent(scopes -> {");
						tl(5, "scopes.stream().map(v -> v.toString()).forEach(scope -> {");
						tl(6, i18nGlobale.getString(I18n.var_requeteSite), ".addScopes(scope);");
						tl(5, "});");
						tl(4, "});");
						tl(4, i18nGlobale.getString(I18n.var_rechercher), classeApiClasseNomSimple, i18nGlobale.getString(I18n.var_Liste), "(", i18nGlobale.getString(I18n.var_requeteSite), ", false, true, true).onSuccess(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, " -> {");
						tl(5, "try {");
						tl(6, classeNomSimple, " o = ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first();");
						tl(6, "if(o != null && ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".getResponse().getResponse().getNumFound() == 1) {");
						tl(7, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setRows(1L);");
						tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setNumFound(1L);");
						tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(0L);");
						tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".", i18nGlobale.getString(I18n.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", i18nGlobale.getString(I18n.var_requeteSite), ");");
						tl(7, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteApi), ");");
						tl(7, "if(Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteService), ".getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getJsonArray(\"var\")).orElse(new JsonArray()).stream().filter(s -> \"", i18nGlobale.getString(I18n.var_recharger), ":false\".equals(s)).count() > 0L) {");
						tl(8, i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Requete), "Vars().put( \"", i18nGlobale.getString(I18n.var_recharger), "\", \"false\" );");
						tl(7, "}");
						tl(7, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumFound() == 1L)");
						tl(8, i18nGlobale.getString(I18n.var_requeteApi), ".setOriginal(o);");
						tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setId(Optional.ofNullable(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first()).map(o2 -> o2.get", StringUtils.capitalize(classeVarId), "().toString()).orElse(null));");
						if(classeModele)
							tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".set", i18nGlobale.getString(I18n.var_SolrId), "(Optional.ofNullable(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first()).map(o2 -> o2.get", solrIdCapitalise, "()).orElse(null));");
						tl(7, classeApiOperationIdMethode, "Future(o).onSuccess(o2 -> {");
						tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));");
						tl(7, "}).onFailure(ex -> {");
						tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));");
						tl(6, "}");
						tl(5, "} catch(Exception ex) {");
						tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(6, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						tl(5, "}");
						tl(4, "}).onFailure(ex -> {");
						tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(5, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						tl(4, "});");
						tl(3, "} catch(Exception ex) {");
						tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(4, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						tl(3, "}");
						tl(2, "}).onFailure(ex -> {");
						tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(3, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						tl(2, "});");
						tl(1, "}");
					} else if(StringUtils.contains(classeApiMethode, "PATCH")) {
						l();
						tl(1, "@Override");
						tl(1, "public void ", classeApiOperationIdMethode, "Future(" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ") {");
						tl(2, "Boolean ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), " = ", classePublicLire || classeRoleSession || classeRoleUtilisateur, ";");
						tl(2, i18nGlobale.getString(I18n.var_utilisateur), "(", i18nGlobale.getString(I18n.var_requeteService), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".get", i18nGlobale.getString(I18n.var_ClasseApiAddresse), "(), \"post", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", \"patch", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), ").onSuccess(", i18nGlobale.getString(I18n.var_requeteSite), " -> {");
						tl(3, "try {");
						tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".setJsonObject(body);");
						tl(4, i18nGlobale.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").put(\"rows\", 1);");
						tl(4, "Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteService), ".getParams().getJsonArray(\"scopes\")).ifPresent(scopes -> {");
						tl(5, "scopes.stream().map(v -> v.toString()).forEach(scope -> {");
						tl(6, i18nGlobale.getString(I18n.var_requeteSite), ".addScopes(scope);");
						tl(5, "});");
						tl(4, "});");
						tl(4, i18nGlobale.getString(I18n.var_rechercher), classeApiClasseNomSimple, i18nGlobale.getString(I18n.var_Liste), "(", i18nGlobale.getString(I18n.var_requeteSite), ", false, true, true).onSuccess(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, " -> {");
						tl(5, "try {");
						tl(6, classeNomSimple, " o = ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first();");
						tl(6, "if(o != null && ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".getResponse().getResponse().getNumFound() == 1) {");
						tl(7, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setRows(1L);");
						tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setNumFound(1L);");
						tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(0L);");
						tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".", i18nGlobale.getString(I18n.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", i18nGlobale.getString(I18n.var_requeteSite), ");");
						tl(7, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteApi), ");");
						tl(7, "if(Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteService), ".getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getJsonArray(\"var\")).orElse(new JsonArray()).stream().filter(s -> \"", i18nGlobale.getString(I18n.var_recharger), ":false\".equals(s)).count() > 0L) {");
						tl(8, i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Requete), "Vars().put( \"", i18nGlobale.getString(I18n.var_recharger), "\", \"false\" );");
						tl(7, "}");
						tl(7, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumFound() == 1L)");
						tl(8, i18nGlobale.getString(I18n.var_requeteApi), ".setOriginal(o);");
						tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setId(Optional.ofNullable(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first()).map(o2 -> o2.get", StringUtils.capitalize(classeVarId), "().toString()).orElse(null));");
						if(classeModele)
							tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".set", i18nGlobale.getString(I18n.var_SolrId), "(Optional.ofNullable(", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ".first()).map(o2 -> o2.get", solrIdCapitalise, "()).orElse(null));");
						tl(7, "JsonObject jsonObject = JsonObject.mapFrom(o);");
						tl(7, classeNomSimple, " o2 = jsonObject.mapTo(", classeNomSimple, ".class);");
						tl(7, "o2.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
						tl(7, classeApiOperationIdMethode, "Future(o2, false).onSuccess(o3 -> {");
						tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));");
						tl(7, "}).onFailure(ex -> {");
						tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));");
						tl(6, "}");
						tl(5, "} catch(Exception ex) {");
						tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(6, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						tl(5, "}");
						tl(4, "}).onFailure(ex -> {");
						tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(5, i18nGlobale.getString(I18n.var_erreur), "(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						tl(4, "});");
						tl(3, "} catch(Exception ex) {");
						tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(4, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						tl(3, "}");
						tl(2, "}).onFailure(ex -> {");
						tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(3, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						tl(2, "});");
						tl(1, "}");
					}
					else if(StringUtils.containsAny(classeApiMethode, i18nGlobale.getString(I18n.var_PUTFusion), "PUTImport")) {
						// putimportFuture //
						l();
						tl(1, "@Override");
						tl(1, "public void ", classeApiOperationIdMethode, "Future(" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ") {");
						tl(2, "Boolean ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), " = ", classePublicLire || classeRoleSession || classeRoleUtilisateur, ";");
						tl(2, i18nGlobale.getString(I18n.var_utilisateur), "(", i18nGlobale.getString(I18n.var_requeteService), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".get", i18nGlobale.getString(I18n.var_ClasseApiAddresse), "(), \"post", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", \"patch", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_PublicLire), ").onSuccess(", i18nGlobale.getString(I18n.var_requeteSite), " -> {");
						tl(3, "try {");
						if(StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json")) {
							tl(4, "Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteService), ".getParams().getJsonArray(\"scopes\")).ifPresent(scopes -> {");
							tl(5, "scopes.stream().map(v -> v.toString()).forEach(scope -> {");
							tl(6, i18nGlobale.getString(I18n.var_requeteSite), ".addScopes(scope);");
							tl(5, "});");
							tl(4, "});");
							tl(4, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
							tl(4, i18nGlobale.getString(I18n.var_requeteApi), ".setRows(1L);");
							tl(4, i18nGlobale.getString(I18n.var_requeteApi), ".setNumFound(1L);");
							tl(4, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(0L);");
							tl(4, i18nGlobale.getString(I18n.var_requeteApi), ".", i18nGlobale.getString(I18n.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", i18nGlobale.getString(I18n.var_requeteSite), ");");
							tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_RequeteApi), "_(", i18nGlobale.getString(I18n.var_requeteApi), ");");
							tl(4, "String ", classeVarInheritClePrimaire, " = Optional.ofNullable(body.getString(", classeNomSimple, ".VAR_", classeVarId, ")).orElse(body.getString(", classeNomSimple, ".VAR_", classeVarCleUnique, "));");
							// if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
							// 	tl(4, "body.put(\"", classeVarInheritClePrimaire, "\", ", classeVarInheritClePrimaire, ");");
							// 	if(!classeModele)
							// 		tl(4, "body.put(\"", classeVarInheritClePrimaire, "\", body.getValue(\"", classeVarCleUnique, "\"));");
							// }
							tl(4, "if(Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteService), ".getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getJsonArray(\"var\")).orElse(new JsonArray()).stream().filter(s -> \"", i18nGlobale.getString(I18n.var_recharger), ":false\".equals(s)).count() > 0L) {");
							tl(5, i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Requete), "Vars().put( \"", i18nGlobale.getString(I18n.var_recharger), "\", \"false\" );");
							tl(4, "}");

							if(classeEtendModeleBase) {
								// putImport etend ModeleBase
								tl(4, "pgPool.getConnection().onSuccess(", i18nGlobale.getString(I18n.var_connexionSql), " -> {");
								tl(5, "String sqlQuery = String.format(\"select * from %s WHERE ", classeVarId, "=$1\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ");");
								tl(5, i18nGlobale.getString(I18n.var_connexionSql), ".preparedQuery(sqlQuery)");
								tl(7, ".execute(Tuple.tuple(Arrays.asList(", classeVarId, "))");
								tl(7, ").onSuccess(", i18nGlobale.getString(I18n.var_resultat), " -> {");
								tl(6, i18nGlobale.getString(I18n.var_connexionSql), ".close().onSuccess(a -> {");
								tl(7, "try {");
								tl(8, "if(", i18nGlobale.getString(I18n.var_resultat), ".size() >= 1) {");
								tl(9, classeNomSimple, " o = new ", classeNomSimple, "();");
								tl(9, "o.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
								tl(9, "for(Row definition : ", i18nGlobale.getString(I18n.var_resultat), ".value()) {");
								tl(10, "for(Integer i = 0; i < definition.size(); i++) {");
								tl(11, "try {");
								tl(12, "String columnName = definition.getColumnName(i);");
								tl(12, "Object columnValue = definition.getValue(i);");
								tl(12, "o.", i18nGlobale.getString(I18n.var_definir), i18nGlobale.getString(I18n.var_PourClasse), "(columnName, columnValue);");
								tl(11, "} catch(Exception e) {");
								tl(12, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_definir), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), e);");
								tl(11, "}");
								tl(10, "}");
								tl(9, "}");
								tl(9, classeNomSimple, " o2 = new ", classeNomSimple, "();");
								tl(9, "o2.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
								tl(9, "JsonObject body2 = new JsonObject();");
								tl(9, "for(String f : body.fieldNames()) {");
								tl(10, "Object bodyVal = body.getValue(f);");
								tl(10, "if(bodyVal instanceof JsonArray) {");
								tl(11, "JsonArray bodyVals = (JsonArray)bodyVal;");
								tl(11, "Object valsObj = o.", i18nGlobale.getString(I18n.var_obtenir), i18nGlobale.getString(I18n.var_PourClasse), "(f);");
								tl(11, "Collection<?> vals = valsObj instanceof JsonArray ? ((JsonArray)valsObj).getList() : (Collection<?>)valsObj;");
								tl(11, "if(vals != null && bodyVals.size() == vals.size()) {");
								tl(12, "Boolean match = true;");
								tl(12, "for(Object val : vals) {");
								tl(13, "if(val != null) {");
								tl(14, "if(!bodyVals.contains(val.toString())) {");
								tl(15, "match = false;");
								tl(15, "break;");
								tl(14, "}");
								tl(13, "} else {");
								tl(14, "match = false;");
								tl(14, "break;");
								tl(13, "}");
								tl(12, "}");
								tl(12, "vals.clear();");
								tl(12, "body2.put(\"set\" + StringUtils.capitalize(f), bodyVal);");
								tl(11, "} else {");
								tl(12, "if(vals != null)");
								tl(13, "vals.clear();");
								tl(12, "body2.put(\"set\" + StringUtils.capitalize(f), bodyVal);");
								tl(11, "}");
								tl(10, "} else {");
								tl(11, "o2.", i18nGlobale.getString(I18n.var_definir), i18nGlobale.getString(I18n.var_PourClasse), "(f, bodyVal);");
								tl(11, "o2.", i18nGlobale.getString(I18n.var_attribuer), i18nGlobale.getString(I18n.var_PourClasse), "(f, bodyVal);");
								tl(11, "if(!StringUtils.containsAny(f, \"", classeVarId, "\", \"", i18nGlobale.getString(I18n.var_cree), "\", \"set", i18nGlobale.getString(I18n.var_Cree), "\") && !Objects.equals(o.", i18nGlobale.getString(I18n.var_obtenir), i18nGlobale.getString(I18n.var_PourClasse), "(f), o2.", i18nGlobale.getString(I18n.var_obtenir), i18nGlobale.getString(I18n.var_PourClasse), "(f)))");
								tl(12, "body2.put(\"set\" + StringUtils.capitalize(f), bodyVal);");
								tl(10, "}");
								tl(9, "}");
								tl(9, "for(String f : Optional.ofNullable(o.get", i18nGlobale.getString(I18n.var_Sauvegardes), "()).orElse(new ArrayList<>())) {");
								tl(10, "if(!body.fieldNames().contains(f)) {");
								tl(11, "if(!StringUtils.containsAny(f, \"", classeVarId, "\", \"", i18nGlobale.getString(I18n.var_cree), "\", \"set", i18nGlobale.getString(I18n.var_Cree), "\") && !Objects.equals(o.", i18nGlobale.getString(I18n.var_obtenir), i18nGlobale.getString(I18n.var_PourClasse), "(f), o2.", i18nGlobale.getString(I18n.var_obtenir), i18nGlobale.getString(I18n.var_PourClasse), "(f)))");
								tl(12, "body2.putNull(\"set\" + StringUtils.capitalize(f));");
								tl(10, "}");
								tl(9, "}");
								tl(9, "if(", i18nGlobale.getString(I18n.var_resultat), ".size() >= 1) {");
								tl(10, i18nGlobale.getString(I18n.var_requeteApi), ".setOriginal(o);");
								tl(10, i18nGlobale.getString(I18n.var_requeteApi), ".setId(Optional.ofNullable(o.get", StringUtils.capitalize(classeVarId), "()).map(v -> v.toString()).orElse(null));");
								if(classeModele)
									tl(10, i18nGlobale.getString(I18n.var_requeteApi), ".set", i18nGlobale.getString(I18n.var_SolrId), "(o.get", solrIdCapitalise, "());");
								tl(9, "}");
								tl(9, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ObjetJson), "(body2);");
								tl(9, "patch", classeNomSimple, "Future(", (classeModele ? "o" : "o2"), ", ", classeApiMethode.equals("PUTImport"), ").onSuccess(b -> {");
								tl(10, "LOG.debug(\"Import ", classeNomSimple, " {} ", i18nGlobale.getString(I18n.str_a_réussi), ", ", i18nGlobale.getString(I18n.var_modifie), " ", classeNomSimple, ". \", body.getValue(", classeNomSimple, ".VAR_", classeVarId, "));");
								tl(10, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture());");
								tl(9, "}).onFailure(ex -> {");
								tl(10, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(10, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
								tl(9, "});");
								tl(8, "} else {");
								tl(9, "post", classeNomSimple, "Future(", i18nGlobale.getString(I18n.var_requeteSite), ", ", classeApiMethode.equals("PUTImport"), ").onSuccess(b -> {");
								tl(10, "LOG.debug(\"Import ", classeNomSimple, " {} ", i18nGlobale.getString(I18n.str_a_réussi), ", ", i18nGlobale.getString(I18n.str_créé_nouveau), " ", classeNomSimple, ". \", body.getValue(", classeNomSimple, ".VAR_", classeVarId, "));");
								tl(10, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture());");
								tl(9, "}).onFailure(ex -> {");
								tl(10, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(10, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
								tl(9, "});");
								tl(8, "}");
								tl(7, "} catch(Exception ex) {");
								tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
								tl(7, "}");
								tl(6, "}).onFailure(ex -> {");
								tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(7, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
								tl(6, "});");
								tl(5, "}).onFailure(ex -> {");
								tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(6, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
								tl(5, "});");
								tl(4, "}).onFailure(ex -> {");
								tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(5, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
								tl(4, "});");
							} else {
								// putImport etend ResultatBase
								l();
								tl(4, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_listeRecherche), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, ">();");
								tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".set", i18nGlobale.getString(I18n.var_Stocker), "(true);");
								tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".q(\"*:*\");");
								tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".setC(", classeNomSimple, ".class);");
								if(activerArchive)
									tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".fq(\"", i18nGlobale.getString(I18n.var_archive), "_docvalues_boolean:false\");");
								tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".fq(\"", classeVarId, classeVarIdSuffixeSolr, ":\" + SearchTool.escapeQueryChars(", classeVarInheritClePrimaire, "));");
								tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".", i18nGlobale.getString(I18n.var_promesseLoin), i18nGlobale.getString(I18n.var_PourClasse), "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(a -> {");
								tl(5, "try {");
								tl(6, "if(", i18nGlobale.getString(I18n.var_listeRecherche), ".size() >= 1) {");
								tl(7, classeNomSimple, " o = ", i18nGlobale.getString(I18n.var_listeRecherche), ".getList().stream().findFirst().orElse(null);");
								tl(7, classeNomSimple, " o2 = new ", classeNomSimple, "();");
								tl(7, "o2.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
								tl(7, "JsonObject body2 = new JsonObject();");
								tl(7, "for(String f : body.fieldNames()) {");
								tl(8, "Object bodyVal = body.getValue(f);");
								tl(8, "if(bodyVal instanceof JsonArray) {");
								tl(9, "JsonArray bodyVals = (JsonArray)bodyVal;");
								tl(9, "Object valsObj = o.", i18nGlobale.getString(I18n.var_obtenir), i18nGlobale.getString(I18n.var_PourClasse), "(f);");
								tl(9, "Collection<?> vals = valsObj instanceof JsonArray ? ((JsonArray)valsObj).getList() : (Collection<?>)valsObj;");
								tl(9, "if(vals != null && bodyVals.size() == vals.size()) {");
								tl(10, "Boolean match = true;");
								tl(10, "for(Object val : vals) {");
								tl(11, "if(val != null) {");
								tl(12, "if(!bodyVals.contains(val.toString())) {");
								tl(13, "match = false;");
								tl(13, "break;");
								tl(12, "}");
								tl(11, "} else {");
								tl(12, "match = false;");
								tl(12, "break;");
								tl(11, "}");
								tl(10, "}");
								tl(10, "vals.clear();");
								tl(10, "body2.put(\"set\" + StringUtils.capitalize(f), bodyVal);");
								tl(9, "} else {");
								tl(10, "if(vals != null)");
								tl(11, "vals.clear();");
								tl(10, "body2.put(\"set\" + StringUtils.capitalize(f), bodyVal);");
								tl(9, "}");
								tl(8, "} else {");
								tl(9, "o2.", i18nGlobale.getString(I18n.var_definir), i18nGlobale.getString(I18n.var_PourClasse), "(f, bodyVal);");
								tl(9, "o2.", i18nGlobale.getString(I18n.var_attribuer), i18nGlobale.getString(I18n.var_PourClasse), "(f, bodyVal);");
								tl(9, "if(!StringUtils.containsAny(f, \"", classeVarId, "\", \"", i18nGlobale.getString(I18n.var_cree), "\", \"set", i18nGlobale.getString(I18n.var_Cree), "\") && !Objects.equals(o.", i18nGlobale.getString(I18n.var_obtenir), i18nGlobale.getString(I18n.var_PourClasse), "(f), o2.", i18nGlobale.getString(I18n.var_obtenir), i18nGlobale.getString(I18n.var_PourClasse), "(f)))");
								tl(10, "body2.put(\"set\" + StringUtils.capitalize(f), bodyVal);");
								tl(8, "}");
								tl(7, "}");
								tl(7, "for(String f : Optional.ofNullable(o.get", i18nGlobale.getString(I18n.var_Sauvegardes), "()).orElse(new ArrayList<>())) {");
								tl(8, "if(!body.fieldNames().contains(f)) {");
								tl(9, "if(!StringUtils.containsAny(f, \"", classeVarId, "\", \"", i18nGlobale.getString(I18n.var_cree), "\", \"set", i18nGlobale.getString(I18n.var_Cree), "\") && !Objects.equals(o.", i18nGlobale.getString(I18n.var_obtenir), i18nGlobale.getString(I18n.var_PourClasse), "(f), o2.", i18nGlobale.getString(I18n.var_obtenir), i18nGlobale.getString(I18n.var_PourClasse), "(f)))");
								tl(10, "body2.putNull(\"set\" + StringUtils.capitalize(f));");
								tl(8, "}");
								tl(7, "}");
								tl(7, "if(", i18nGlobale.getString(I18n.var_listeRecherche), ".size() == 1) {");
								tl(8, i18nGlobale.getString(I18n.var_requeteApi), ".setOriginal(o);");
								tl(8, i18nGlobale.getString(I18n.var_requeteApi), ".setId(Optional.ofNullable(o.get", StringUtils.capitalize(classeVarId), "()).map(v -> v.toString()).orElse(null));");
								if(classeModele)
									tl(8, i18nGlobale.getString(I18n.var_requeteApi), ".set", i18nGlobale.getString(I18n.var_SolrId), "(o.get", solrIdCapitalise, "());");
								tl(7, "}");
								tl(7, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ObjetJson), "(body2);");
								tl(7, "patch", classeNomSimple, "Future(", (classeModele ? "o" : "o2"), ", ", classeApiMethode.equals("PUTImport"), ").onSuccess(b -> {");
								tl(8, "LOG.debug(\"Import ", classeNomSimple, " {} ", i18nGlobale.getString(I18n.str_a_réussi), ", ", i18nGlobale.getString(I18n.var_modifie), " ", classeNomSimple, ". \", body.getValue(", classeNomSimple, ".VAR_", classeVarId, "));");
								tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture());");
								tl(7, "}).onFailure(ex -> {");
								tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
								tl(7, "});");
								tl(6, "} else {");
								tl(7, "post", classeNomSimple, "Future(", i18nGlobale.getString(I18n.var_requeteSite), ", ", classeApiMethode.equals("PUTImport"), ").onSuccess(b -> {");
								tl(8, "LOG.debug(\"Import ", classeNomSimple, " {} ", i18nGlobale.getString(I18n.str_a_réussi), ", ", i18nGlobale.getString(I18n.str_créé_nouveau), " ", classeNomSimple, ". \", body.getValue(", classeNomSimple, ".VAR_", classeVarId, "));");
								tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture());");
								tl(7, "}).onFailure(ex -> {");
								tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(8, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
								tl(7, "});");
								tl(6, "}");
								tl(5, "} catch(Exception ex) {");
								tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(6, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
								tl(5, "}");
								tl(4, "}).onFailure(ex -> {");
								tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(5, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
								tl(4, "});");
							}
						} else {
							tl(4, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithPlainText(Buffer.buffer())));");
						}
						tl(3, "} catch(Exception ex) {");
						tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(4, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
						tl(3, "}");
						tl(2, "}).onFailure(ex -> {");
						if(activerOpenIdConnect) {
							tl(3, "if(\"Inactive Token\".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), \"invalid_grant:\")) {");
							tl(4, "try {");
							tl(5, i18nGlobale.getString(I18n.var_gestionnaireEvenements), ".handle(Future.succeededFuture(new ServiceResponse(302, \"Found\", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, \"/", i18nGlobale.getString(I18n.var_deconnexion), "?redirect_uri=\" + URLEncoder.encode(serviceRequest.getExtra().getString(\"uri\"), \"UTF-8\")))));");
							tl(4, "} catch(Exception ex2) {");
							tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \", ex2));");
							tl(5, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex2);");
							tl(4, "}");
							tl(3, "} else if(StringUtils.startsWith(ex.getMessage(), \"401 UNAUTHORIZED \")) {");
							tl(4, "eventHandler.handle(Future.succeededFuture(");
							tl(5, "new ServiceResponse(401, \"UNAUTHORIZED\",");
							tl(6, "Buffer.buffer().appendString(");
							tl(7, "new JsonObject()");
							tl(8, ".put(\"errorCode\", \"401\")");
							tl(8, ".put(\"errorMessage\", \"SSO Resource Permission check returned DENY\")");
							tl(8, ".encodePrettily()");
							tl(7, "), MultiMap.caseInsensitiveMultiMap()");
							tl(7, ")");
							tl(5, "));");
							tl(3, "} else {");
							tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(4, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
							tl(3, "}");
						} else {
							tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(3, i18nGlobale.getString(I18n.var_erreur), "(null, ", i18nGlobale.getString(I18n.var_gestionnaireEvenements), ", ex);");
						}
						tl(2, "});");
						tl(1, "}");
					}

					if(StringUtils.containsAny(classeApiMethode, "POST", i18nGlobale.getString(I18n.var_PUTCopie), "PATCH", "DELETE")) {
						l();
						t(1, "public Future<", classeApiClasseNomSimple, "> ", classeApiOperationIdMethode, "Future(");
						if(StringUtils.contains(classeApiMethode, "POST"))
							s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), ", Boolean ", classeVarInheritClePrimaire, "");
						else if(StringUtils.contains(classeApiMethode, "DELETE"))
							s(classeNomSimple, " o");
						else if(StringUtils.contains(classeApiMethode, "PUT"))
							s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), ", JsonObject jsonObject");
						else if(StringUtils.contains(classeApiMethode, "PATCH"))
							s(classeNomSimple, " o, Boolean ", i18nGlobale.getString(I18n.var_inheritClePrimaire));
						else
							s(classeNomSimple, " ", uncapitalizeClasseNomSimple);
						l(") {");
						if(StringUtils.containsAny(classeApiMethode, "PATCH", "DELETE"))
							tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
						else if(!StringUtils.containsAny(classeApiMethode, "POST", "PUT"))
							tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = ", uncapitalizeClasseNomSimple, ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
						tl(2, "Promise<", classeApiClasseNomSimple, "> promise = Promise.promise();");
						l();
						tl(2, "try {");
	
						if(classeApiMethode.contains("POST")) {
							if(classeModele) {
								tl(3, "pgPool.withTransaction(", i18nGlobale.getString(I18n.var_connexionSql), " -> {");
								tl(4, "Promise<", classeApiClasseNomSimple, "> promise1 = Promise.promise();");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(", i18nGlobale.getString(I18n.var_connexionSql), ");");
								tl(4, "vars", classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(a -> {");
								tl(5, i18nGlobale.getString(I18n.var_creer), classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(", uncapitalizeClasseNomSimple, " -> {");
								tl(6, "sql", classeApiMethode, classeNomSimple, "(", uncapitalizeClasseNomSimple, ", ", classeVarInheritClePrimaire, ").onSuccess(b -> {");
								tl(7, i18nGlobale.getString(I18n.var_definir), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", false).onSuccess(c -> {");
								tl(8, i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(d -> {");
								tl(9, i18nGlobale.getString(I18n.var_indexer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(o2 -> {");
								tl(10, "promise1.complete(", uncapitalizeClasseNomSimple, ");");
								tl(9, "}).onFailure(ex -> {");
								tl(10, "promise1.fail(ex);");
								tl(9, "});");
								tl(8, "}).onFailure(ex -> {");
								tl(9, "promise1.fail(ex);");
								tl(8, "});");
								tl(7, "}).onFailure(ex -> {");
								tl(8, "promise1.fail(ex);");
								tl(7, "});");
								tl(6, "}).onFailure(ex -> {");
								tl(7, "promise1.fail(ex);");
								tl(6, "});");
								tl(5, "}).onFailure(ex -> {");
								tl(6, "promise1.fail(ex);");
								tl(5, "});");
								tl(4, "}).onFailure(ex -> {");
								tl(5, "promise1.fail(ex);");
								tl(4, "});");
								tl(4, "return promise1.future();");
								tl(3, "}).onSuccess(a -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(null);");
								tl(3, "}).onFailure(ex -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(null);");
								tl(4, "promise.fail(ex);");
								tl(3, "}).compose(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, "Promise<", classeApiClasseNomSimple, "> promise2 = Promise.promise();");
								tl(4, i18nGlobale.getString(I18n.var_recharger), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(a -> {");
								tl(5, "try {");
								tl(6, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
								tl(6, "if(", i18nGlobale.getString(I18n.var_requeteApi), " != null) {");
								tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumPATCH() + 1);");
								tl(7, uncapitalizeClasseNomSimple, ".", i18nGlobale.getString(I18n.var_requeteApi), classeNomSimple, "();");
								tl(7, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
								tl(6, "}");
								tl(6, "promise2.complete(", uncapitalizeClasseNomSimple, ");");
								tl(5, "} catch(Exception ex) {");
								tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(6, "promise2.fail(ex);");
								tl(5, "}");
								tl(4, "}).onFailure(ex -> {");
								tl(5, "promise2.fail(ex);");
								tl(4, "});");
								tl(4, "return promise2.future();");
								tl(3, "}).onSuccess(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, "try {");
								tl(5, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
								tl(5, "if(", i18nGlobale.getString(I18n.var_requeteApi), " != null) {");
								tl(6, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumPATCH() + 1);");
								tl(6, uncapitalizeClasseNomSimple, ".", i18nGlobale.getString(I18n.var_requeteApi), classeNomSimple, "();");
								tl(6, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
								tl(5, "}");
								tl(5, "promise.complete(", uncapitalizeClasseNomSimple, ");");
								tl(4, "} catch(Exception ex) {");
								tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(5, "promise.fail(ex);");
								tl(4, "}");
								tl(3, "}).onFailure(ex -> {");
								tl(4, "promise.fail(ex);");
								tl(3, "});");
							} else {
								tl(3, i18nGlobale.getString(I18n.var_creer), classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, i18nGlobale.getString(I18n.var_definir), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", false).onSuccess(c -> {");
								tl(5, i18nGlobale.getString(I18n.var_indexer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(o2 -> {");
								tl(6, "promise.complete(", uncapitalizeClasseNomSimple, ");");
								tl(5, "}).onFailure(ex -> {");
								tl(6, "promise.fail(ex);");
								tl(5, "});");
								tl(4, "}).onFailure(ex -> {");
								tl(5, "promise.fail(ex);");
								tl(4, "});");
								tl(3, "}).onFailure(ex -> {");
								tl(4, "promise.fail(ex);");
								tl(3, "});");
							}
						} else if(classeApiMethode.contains("DELETE")) {
							tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
							if(classeModele) {
								tl(3, "Promise<", classeApiClasseNomSimple, "> promise1 = Promise.promise();");
								tl(3, "pgPool.withTransaction(", i18nGlobale.getString(I18n.var_connexionSql), " -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(", i18nGlobale.getString(I18n.var_connexionSql), ");");
								tl(4, "vars", classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(a -> {");
								tl(5, "sql", classeApiMethode, classeNomSimple, "(o).onSuccess(", uncapitalizeClasseNomSimple, " -> {");
								tl(6, i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, "(o).onSuccess(d -> {");
								tl(7, i18nGlobale.getString(I18n.var_desindexer), classeNomSimple, "(o).onSuccess(o2 -> {");
								tl(8, "if(", i18nGlobale.getString(I18n.var_requeteApi), " != null) {");
								tl(9, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumPATCH() + 1);");
								tl(9, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumFound() == 1L && Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".getJsonObject()).map(json -> json.size() > 0).orElse(false)) {");
								tl(10, "o", classeSauvegarde ? "2" : "", ".", i18nGlobale.getString(I18n.var_requeteApi), classeNomSimple, "();");
								tl(10, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getVars().size() > 0)");
								tl(11, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
								tl(9, "}");
								tl(8, "}");
								tl(8, "promise1.complete();");
								tl(7, "}).onFailure(ex -> {");
								tl(8, "promise1.fail(ex);");
								tl(7, "});");
								tl(6, "}).onFailure(ex -> {");
								tl(7, "promise1.fail(ex);");
								tl(6, "});");
								tl(5, "}).onFailure(ex -> {");
								tl(6, "promise1.fail(ex);");
								tl(5, "});");
								tl(4, "}).onFailure(ex -> {");
								tl(5, "promise1.fail(ex);");
								tl(4, "});");
								tl(4, "return promise1.future();");
								tl(3, "}).onSuccess(a -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(null);");
								tl(3, "}).onFailure(ex -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(null);");
								tl(4, "promise.fail(ex);");
								tl(3, "}).compose(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, "Promise<", classeApiClasseNomSimple, "> promise2 = Promise.promise();");
								tl(4, i18nGlobale.getString(I18n.var_recharger), classeNomSimple, "(o).onSuccess(a -> {");
								tl(5, "promise2.complete(o);");
								tl(4, "}).onFailure(ex -> {");
								tl(5, "promise2.fail(ex);");
								tl(4, "});");
								tl(4, "return promise2.future();");
								tl(3, "}).onSuccess(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, "promise.complete(", uncapitalizeClasseNomSimple, ");");
								tl(3, "}).onFailure(ex -> {");
								tl(4, "promise.fail(ex);");
								tl(3, "});");
							} else {
								tl(3, i18nGlobale.getString(I18n.var_desindexer), classeNomSimple, "(o).onSuccess(e -> {");
								tl(4, "promise.complete(o);");
								tl(3, "}).onFailure(ex -> {");
								tl(4, "promise.fail(ex);");
								tl(3, "});");
							}
						}
						else if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTCopie))) {
							l();
							tl(3, "jsonObject.put(\"", i18nGlobale.getString(I18n.var_sauvegardes), "\", Optional.ofNullable(jsonObject.getJsonArray(\"", i18nGlobale.getString(I18n.var_sauvegardes), "\")).orElse(new JsonArray()));");
							tl(3, "JsonObject jsonPatch = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ObjetJson), "()).map(o -> o.getJsonObject(\"patch\")).orElse(new JsonObject());");
							tl(3, "jsonPatch.stream().forEach(o -> {");
							tl(4, "if(o.getValue() == null)");
							tl(5, "jsonObject.remove(o.getKey());");
							tl(4, "else");
							tl(5, "jsonObject.put(o.getKey(), o.getValue());");
							tl(4, "if(!jsonObject.getJsonArray(\"", i18nGlobale.getString(I18n.var_sauvegardes), "\").contains(o.getKey()))");
							tl(5, "jsonObject.getJsonArray(\"", i18nGlobale.getString(I18n.var_sauvegardes), "\").add(o.getKey());");
							tl(3, "});");
							l();
							tl(3, "pgPool.withTransaction(", i18nGlobale.getString(I18n.var_connexionSql), " -> {");
							tl(4, "Promise<", classeApiClasseNomSimple, "> promise1 = Promise.promise();");
							tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(", i18nGlobale.getString(I18n.var_connexionSql), ");");

							tl(4, i18nGlobale.getString(I18n.var_creer), classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(", uncapitalizeClasseNomSimple, " -> {");
							tl(5, "sql", classeApiMethode, classeNomSimple, "(", uncapitalizeClasseNomSimple, ", jsonObject).onSuccess(b -> {");
							tl(6, i18nGlobale.getString(I18n.var_definir), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", false).onSuccess(c -> {");
							tl(7, i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(d -> {");
							tl(8, i18nGlobale.getString(I18n.var_indexer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(o2 -> {");
							tl(9, "promise1.complete(", uncapitalizeClasseNomSimple, ");");
							tl(8, "}).onFailure(ex -> {");
							tl(9, "promise1.fail(ex);");
							tl(8, "});");
							tl(7, "}).onFailure(ex -> {");
							tl(8, "promise1.fail(ex);");
							tl(7, "});");
							tl(6, "}).onFailure(ex -> {");
							tl(7, "promise1.fail(ex);");
							tl(6, "});");
							tl(5, "}).onFailure(ex -> {");
							tl(6, "promise1.fail(ex);");
							tl(5, "});");
							tl(4, "}).onFailure(ex -> {");
							tl(5, "promise1.fail(ex);");
							tl(4, "});");
							tl(4, "return promise1.future();");
							if(classeModele) {
								tl(3, "}).onSuccess(a -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(null);");
								tl(3, "}).onFailure(ex -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(null);");
								tl(4, "promise.fail(ex);");
								tl(3, "}).compose(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, "Promise<", classeApiClasseNomSimple, "> promise2 = Promise.promise();");
								tl(4, i18nGlobale.getString(I18n.var_recharger), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(a -> {");
								tl(5, "try {");
								tl(6, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
								tl(6, "if(", i18nGlobale.getString(I18n.var_requeteApi), " != null) {");
								tl(7, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumPATCH() + 1);");
								tl(7, uncapitalizeClasseNomSimple, ".", i18nGlobale.getString(I18n.var_requeteApi), classeNomSimple, "();");
								tl(7, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
								tl(6, "}");
								tl(6, "promise2.complete(", uncapitalizeClasseNomSimple, ");");
								tl(5, "} catch(Exception ex) {");
								tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(6, "promise2.fail(ex);");
								tl(5, "}");
								tl(4, "}).onFailure(ex -> {");
								tl(5, "promise2.fail(ex);");
								tl(4, "});");
								tl(4, "return promise2.future();");
								tl(3, "}).onSuccess(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, "promise.complete(", uncapitalizeClasseNomSimple, ");");
								tl(3, "}).onFailure(ex -> {");
								tl(4, "promise.fail(ex);");
							} else {
								tl(3, "}).onSuccess(a -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(null);");
								tl(4, "promise.complete(o);");
								tl(3, "}).onFailure(ex -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(null);");
								tl(4, "promise.fail(ex);");
							}
							tl(3, "});");
						}
						else if(classeApiMethode.contains("PATCH")) {
							tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
							if(classeModele) {
								tl(3, "Promise<", classeApiClasseNomSimple, "> promise1 = Promise.promise();");
								tl(3, "pgPool.withTransaction(", i18nGlobale.getString(I18n.var_connexionSql), " -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(", i18nGlobale.getString(I18n.var_connexionSql), ");");
								tl(4, "vars", classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(a -> {");
								if(activerContextBroker && classeFiware) {
									tl(5, "JsonObject jsonObject = o.getSiteRequest_().getJsonObject();");
									tl(5, "if(config.getBoolean(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_ACTIVER_CONTEXT_BROKER_ENVOI), ")) {");
									tl(6, "ngsildGetEntity(o).compose(ngsildData -> {");
									tl(7, "Promise<JsonObject> promise2 = Promise.promise();");
									tl(7, "if(ngsildData == null) {");
									tl(8, "promise2.complete(jsonObject);");
									tl(7, "} else {");
									tl(8, "String setNgsildData = String.format(\"set%s\",StringUtils.capitalize(", classeNomSimple, ".VAR_ngsildData));");
									tl(8, "jsonObject.put(setNgsildData, ngsildData);");
									tl(8, "promise2.complete(jsonObject);");
									tl(7, "}");
									tl(7, "return promise2.future();");
									tl(6, "}).compose(ngsildData -> {");
									tl(7, "Promise<", classeApiClasseNomSimple, "> promise2 = Promise.promise();");
									tl(7, "sql", classeApiMethode, classeNomSimple, "(o, ", i18nGlobale.getString(I18n.var_inheritClePrimaire), ").onSuccess(", uncapitalizeClasseNomSimple, " -> {");
									tl(8, i18nGlobale.getString(I18n.var_definir), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", true).onSuccess(c -> {");
									tl(9, i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(d -> {");
									tl(10, i18nGlobale.getString(I18n.var_indexer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(o2 -> {");
									tl(11, "if(", i18nGlobale.getString(I18n.var_requeteApi), " != null) {");
									tl(12, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumPATCH() + 1);");
									tl(12, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumFound() == 1L && Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".getJsonObject()).map(json -> json.size() > 0).orElse(false)) {");
									tl(13, "o", classeSauvegarde ? "2" : "", ".", i18nGlobale.getString(I18n.var_requeteApi), classeNomSimple, "();");
									tl(13, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getVars().size() > 0)");
									tl(14, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
									tl(12, "}");
									tl(11, "}");
									tl(11, "promise2.complete(", uncapitalizeClasseNomSimple, ");");
									tl(10, "}).onFailure(ex -> {");
									tl(11, "promise2.fail(ex);");
									tl(10, "});");
									tl(9, "}).onFailure(ex -> {");
									tl(10, "promise2.fail(ex);");
									tl(9, "});");
									tl(8, "}).onFailure(ex -> {");
									tl(9, "promise2.fail(ex);");
									tl(8, "});");
									tl(7, "}).onFailure(ex -> {");
									tl(8, "promise2.fail(ex);");
									tl(7, "});");
									tl(7, "return promise2.future();");
									tl(6, "}).onSuccess(o2 -> {");
									tl(7, "promise1.complete(o2);");
									tl(6, "}).onFailure(ex -> {");
									tl(7, "promise1.fail(ex);");
									tl(6, "});");
									tl(5, "} else {");
									tl(6, "sql", classeApiMethode, classeNomSimple, "(o, ", i18nGlobale.getString(I18n.var_inheritClePrimaire), ").onSuccess(", uncapitalizeClasseNomSimple, " -> {");
									tl(7, i18nGlobale.getString(I18n.var_definir), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", true).onSuccess(c -> {");
									tl(8, i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(d -> {");
									tl(9, i18nGlobale.getString(I18n.var_indexer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(o2 -> {");
									tl(10, "if(", i18nGlobale.getString(I18n.var_requeteApi), " != null) {");
									tl(11, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumPATCH() + 1);");
									tl(11, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumFound() == 1L && Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".getJsonObject()).map(json -> json.size() > 0).orElse(false)) {");
									tl(12, "o", classeSauvegarde ? "2" : "", ".", i18nGlobale.getString(I18n.var_requeteApi), classeNomSimple, "();");
									tl(12, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getVars().size() > 0)");
									tl(13, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
									tl(11, "}");
									tl(10, "}");
									tl(10, "promise1.complete(", uncapitalizeClasseNomSimple, ");");
									tl(9, "}).onFailure(ex -> {");
									tl(10, "promise1.fail(ex);");
									tl(9, "});");
									tl(8, "}).onFailure(ex -> {");
									tl(9, "promise1.fail(ex);");
									tl(8, "});");
									tl(7, "}).onFailure(ex -> {");
									tl(8, "promise1.fail(ex);");
									tl(7, "});");
									tl(6, "}).onFailure(ex -> {");
									tl(7, "promise1.fail(ex);");
									tl(6, "});");
									tl(5, "}");
								} else {
									tl(5, "sql", classeApiMethode, classeNomSimple, "(o, ", i18nGlobale.getString(I18n.var_inheritClePrimaire), ").onSuccess(", uncapitalizeClasseNomSimple, " -> {");
									tl(6, i18nGlobale.getString(I18n.var_definir), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", true).onSuccess(c -> {");
									tl(7, i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(d -> {");
									tl(8, i18nGlobale.getString(I18n.var_indexer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(o2 -> {");
									tl(9, "if(", i18nGlobale.getString(I18n.var_requeteApi), " != null) {");
									tl(10, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumPATCH() + 1);");
									tl(10, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumFound() == 1L && Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".getJsonObject()).map(json -> json.size() > 0).orElse(false)) {");
									tl(11, "o", classeSauvegarde ? "2" : "", ".", i18nGlobale.getString(I18n.var_requeteApi), classeNomSimple, "();");
									tl(11, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getVars().size() > 0)");
									tl(12, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
									tl(10, "}");
									tl(9, "}");
									tl(9, "promise1.complete(", uncapitalizeClasseNomSimple, ");");
									tl(8, "}).onFailure(ex -> {");
									tl(9, "promise1.fail(ex);");
									tl(8, "});");
									tl(7, "}).onFailure(ex -> {");
									tl(8, "promise1.fail(ex);");
									tl(7, "});");
									tl(6, "}).onFailure(ex -> {");
									tl(7, "promise1.fail(ex);");
									tl(6, "});");
									tl(5, "}).onFailure(ex -> {");
									tl(6, "promise1.fail(ex);");
									tl(5, "});");
								}
								tl(4, "}).onFailure(ex -> {");
								tl(5, "promise1.fail(ex);");
								tl(4, "});");
								tl(4, "return promise1.future();");
								tl(3, "}).onSuccess(a -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(null);");
								tl(3, "}).onFailure(ex -> {");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_ConnexionSql), "(null);");
								tl(4, "promise.fail(ex);");
								tl(3, "}).compose(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, "Promise<", classeApiClasseNomSimple, "> promise2 = Promise.promise();");
								tl(4, i18nGlobale.getString(I18n.var_recharger), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(a -> {");
								tl(5, "promise2.complete(", uncapitalizeClasseNomSimple, ");");
								tl(4, "}).onFailure(ex -> {");
								tl(5, "promise2.fail(ex);");
								tl(4, "});");
								tl(4, "return promise2.future();");
								tl(3, "}).onSuccess(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, "promise.complete(", uncapitalizeClasseNomSimple, ");");
								tl(3, "}).onFailure(ex -> {");
								tl(4, "promise.fail(ex);");
								tl(3, "});");
							} else {
								tl(3, i18nGlobale.getString(I18n.var_definir), classeNomSimple, "(o, true).onSuccess(c -> {");
								tl(4, i18nGlobale.getString(I18n.var_indexer), classeNomSimple, "(o).onSuccess(e -> {");
								tl(5, "if(", i18nGlobale.getString(I18n.var_requeteApi), " != null) {");
								tl(6, i18nGlobale.getString(I18n.var_requeteApi), ".setNumPATCH(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumPATCH() + 1);");
								tl(6, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getNumFound() == 1L && Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".getJsonObject()).map(json -> json.size() > 0).orElse(false)) {");
								tl(7, "o", classeSauvegarde ? "2" : "", ".", i18nGlobale.getString(I18n.var_requeteApi), classeNomSimple, "();");
								tl(7, "if(", i18nGlobale.getString(I18n.var_requeteApi), ".getVars().size() > 0)");
								tl(8, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_requeteApi), ").toString());");
								tl(6, "}");
								tl(5, "}");
								tl(5, "promise.complete(o);");
								tl(4, "}).onFailure(ex -> {");
								tl(5, "promise.fail(ex);");
								tl(4, "});");
								tl(3, "}).onFailure(ex -> {");
								tl(4, "promise.fail(ex);");
								tl(3, "});");
							}
						}
						tl(2, "} catch(Exception ex) {");
						tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(3, "promise.fail(ex);");
						tl(2, "}");
						tl(2, "return promise.future();");
						tl(1, "}");
					}

					/////////
					// SQL //
					/////////
					if(classeModele) {
						if(classeApiMethode.contains("POST")) {
							l();
							tl(1, "public Future<", classeNomSimple, "> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, Boolean ", i18nGlobale.getString(I18n.var_inheritClePrimaire), ") {");
							tl(2, "Promise<", classeNomSimple, "> promise = Promise.promise();");
							tl(2, "try {");
							tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
							tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
							tl(3, "List<String> ", i18nGlobale.getString(I18n.var_solrIds), " = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteApi), ").map(r -> r.get", i18nGlobale.getString(I18n.var_SolrIds), "()).orElse(new ArrayList<>());");
							tl(3, "List<String> classes = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteApi), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
							tl(3, "SqlConnection ", i18nGlobale.getString(I18n.var_connexionSql), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ConnexionSql), "();");
							tl(3, "Integer num = 1;");
							tl(3, "StringBuilder bSql = new StringBuilder(\"UPDATE ", classeNomSimple, " SET \");");
							tl(3, "List<Object> bParams = new ArrayList<Object>();");
							tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
							tl(3, "JsonObject jsonObject = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ObjetJson), "();");
							tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
							tl(3, "o2.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
							tl(3, "List<Future> futures1 = new ArrayList<>();");
							tl(3, "List<Future> futures2 = new ArrayList<>();");
							if(activerSessionId) {
								l();
								tl(3, "if(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_SessionId), "() != null) {");
								tl(4, "if(bParams.size() > 0) {");
								tl(5, "bSql.append(\", \");");
								tl(4, "}");
								tl(4, "bSql.append(\"", i18nGlobale.getString(I18n.var_sessionId), "=$\" + num);");
								tl(4, "num++;");
								tl(4, "bParams.add(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_SessionId), "());");
								tl(3, "}");
							}
							if(activerUtilisateurCle) {
								tl(3, "if(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_UtilisateurCle), "() != null) {");
								tl(4, "if(bParams.size() > 0) {");
								tl(5, "bSql.append(\", \");");
								tl(4, "}");
								tl(4, "bSql.append(\"", i18nGlobale.getString(I18n.var_utilisateurCle), "=$\" + num);");
								tl(4, "num++;");
								tl(4, "bParams.add(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_UtilisateurCle), "());");
								tl(3, "}");
							}
							if(classeNomCanonique.equals(classePartsUtilisateurSite.nomCanonique(classeLangueNom))) {
								tl(3, "if(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_UtilisateurId), "() != null) {");
								tl(4, "if(bParams.size() > 0) {");
								tl(5, "bSql.append(\", \");");
								tl(4, "}");
								tl(4, "bSql.append(\"", i18nGlobale.getString(I18n.var_utilisateurId), "=$\" + num);");
								tl(4, "num++;");
								tl(4, "bParams.add(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_UtilisateurId), "());");
								tl(3, "}");
							}
							l();
							tl(3, "if(jsonObject != null) {");
							tl(4, "Set<String> ", i18nGlobale.getString(I18n.var_entite), "Vars = jsonObject.fieldNames();");
							tl(4, "for(String ", i18nGlobale.getString(I18n.var_entite), "Var : ", i18nGlobale.getString(I18n.var_entite), "Vars) {");
							tl(5, "switch(", i18nGlobale.getString(I18n.var_entite), "Var) {");
							s(wApiGenererPost.toString());
							tl(5, "}");
							tl(4, "}");
							tl(3, "}");
							tl(3, "bSql.append(\" WHERE ", classeVarClePrimaire, "=$\" + num);");
							tl(3, "if(bParams.size() > 0) {");
							tl(3, "bParams.add(", classeVarClePrimaire, ");");
							tl(3, "num++;");
							tl(4, "futures2.add(0, Future.future(a -> {");
							tl(5, i18nGlobale.getString(I18n.var_connexionSql), ".preparedQuery(bSql.toString())");
							tl(7, ".execute(Tuple.tuple(bParams)");
							tl(7, ").onSuccess(b -> {");
							tl(6, "a.handle(Future.succeededFuture());");
							tl(5, "}).onFailure(ex -> {");
							tl(6, "RuntimeException ex2 = new RuntimeException(\"", i18nGlobale.getString(I18n.var_valeur), " ", classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), "\", ex);");
							tl(6, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex2);");
							tl(6, "a.handle(Future.failedFuture(ex2));");
							tl(5, "});");
							tl(4, "}));");
							tl(3, "}");
							tl(3, "CompositeFuture.all(futures1).onSuccess(a -> {");
							tl(4, "CompositeFuture.all(futures2).onSuccess(b -> {");
							tl(5, "promise.complete(o2);");
							tl(4, "}).onFailure(ex -> {");
							tl(5, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(5, "promise.fail(ex);");
							tl(4, "});");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(4, "promise.fail(ex);");
							tl(3, "});");
							tl(2, "} catch(Exception ex) {");
							tl(3, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(3, "promise.fail(ex);");
							tl(2, "}");
							tl(2, "return promise.future();");
							tl(1, "}");
						} else if(classeApiMethode.contains("DELETE")) {
							l();
							tl(1, "public Future<Void> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o) {");
							tl(2, "Promise<Void> promise = Promise.promise();");
							tl(2, "try {");
							tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
							tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
							tl(3, "List<String> ", i18nGlobale.getString(I18n.var_solrIds), " = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteApi), ").map(r -> r.get", i18nGlobale.getString(I18n.var_SolrIds), "()).orElse(new ArrayList<>());");
							tl(3, "List<String> classes = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteApi), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
							tl(3, "SqlConnection ", i18nGlobale.getString(I18n.var_connexionSql), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ConnexionSql), "();");
							tl(3, "Integer num = 1;");
							tl(3, "StringBuilder bSql = new StringBuilder(\"DELETE FROM ", classeNomSimple, " \");");
							tl(3, "List<Object> bParams = new ArrayList<Object>();");
							tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
							tl(3, "JsonObject jsonObject = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ObjetJson), "();");
							tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
							tl(3, "o2.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
							tl(3, "List<Future> futures1 = new ArrayList<>();");
							tl(3, "List<Future> futures2 = new ArrayList<>();");
							l();
							tl(3, "if(jsonObject != null) {");
							tl(4, "Set<String> ", i18nGlobale.getString(I18n.var_entite), "Vars = jsonObject.fieldNames();");
							tl(4, "for(String ", i18nGlobale.getString(I18n.var_entite), "Var : ", i18nGlobale.getString(I18n.var_entite), "Vars) {");
							tl(5, "switch(", i18nGlobale.getString(I18n.var_entite), "Var) {");
							s(wApiGenererDelete.toString());
							tl(5, "}");
							tl(4, "}");
							tl(3, "}");
							tl(3, "bSql.append(\" WHERE ", classeVarClePrimaire, "=$\" + num);");
							tl(3, "bParams.add(", classeVarClePrimaire, ");");
							tl(3, "num++;");
							tl(3, "futures2.add(0, Future.future(a -> {");
							tl(4, i18nGlobale.getString(I18n.var_connexionSql), ".preparedQuery(bSql.toString())");
							tl(6, ".execute(Tuple.tuple(bParams)");
							tl(6, ").onSuccess(b -> {");
							tl(5, "a.handle(Future.succeededFuture());");
							tl(4, "}).onFailure(ex -> {");
							tl(5, "RuntimeException ex2 = new RuntimeException(\"", i18nGlobale.getString(I18n.var_valeur), " ", classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), "\", ex);");
							tl(5, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_desattribuer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex2);");
							tl(5, "a.handle(Future.failedFuture(ex2));");
							tl(4, "});");
							tl(3, "}));");
							tl(3, "CompositeFuture.all(futures1).onSuccess(a -> {");
							tl(4, "CompositeFuture.all(futures2).onSuccess(b -> {");
							if(activerContextBroker && classeFiware) {
								tl(5, "if(config.getBoolean(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_ACTIVER_CONTEXT_BROKER_ENVOI), ")) {");
								tl(6, "cbDeleteEntity(o).onSuccess(c -> {");
								tl(7, "promise.complete();");
								tl(6, "}).onFailure(ex -> {");
								tl(7, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
								tl(7, "promise.fail(ex);");
								tl(6, "});");
								tl(5, "} else {");
								tl(6, "promise.complete();");
								tl(5, "}");
							} else {
								tl(5, "promise.complete();");
							}
							tl(4, "}).onFailure(ex -> {");
							tl(5, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(5, "promise.fail(ex);");
							tl(4, "});");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(4, "promise.fail(ex);");
							tl(3, "});");
							tl(2, "} catch(Exception ex) {");
							tl(3, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(3, "promise.fail(ex);");
							tl(2, "}");
							tl(2, "return promise.future();");
							tl(1, "}");
						} else if(classeApiMethode.contains("PATCH")) {
							l();
							tl(1, "public Future<", classeApiClasseNomSimple, "> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, Boolean ", i18nGlobale.getString(I18n.var_inheritClePrimaire), ") {");
							tl(2, "Promise<", classeApiClasseNomSimple, "> promise = Promise.promise();");
							tl(2, "try {");
							tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
							tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
							tl(3, "List<String> ", i18nGlobale.getString(I18n.var_solrIds), " = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteApi), ").map(r -> r.get", i18nGlobale.getString(I18n.var_SolrIds), "()).orElse(new ArrayList<>());");
							tl(3, "List<String> classes = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteApi), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
							tl(3, "SqlConnection ", i18nGlobale.getString(I18n.var_connexionSql), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ConnexionSql), "();");
							tl(3, "Integer num = 1;");
							tl(3, "StringBuilder bSql = new StringBuilder(\"UPDATE ", classeNomSimple, " SET \");");
							tl(3, "List<Object> bParams = new ArrayList<Object>();");
							tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
							tl(3, "JsonObject jsonObject = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ObjetJson), "();");
							tl(3, "Set<String> ", i18nGlobale.getString(I18n.var_methodeNoms), " = jsonObject.fieldNames();");
							tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
							tl(3, "o2.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
							tl(3, "List<Future> futures1 = new ArrayList<>();");
							tl(3, "List<Future> futures2 = new ArrayList<>();");
							l();
							tl(3, "for(String ", i18nGlobale.getString(I18n.var_entite), "Var : ", i18nGlobale.getString(I18n.var_methodeNoms), ") {");
							tl(4, "switch(", i18nGlobale.getString(I18n.var_entite), "Var) {");
							s(wApiGenererPatch.toString());
							tl(4, "}");
							tl(3, "}");
							tl(3, "bSql.append(\" WHERE ", classeVarClePrimaire, "=$\" + num);");
							tl(3, "if(bParams.size() > 0) {");
							tl(4, "bParams.add(", classeVarClePrimaire, ");");
							tl(4, "num++;");
							tl(4, "futures2.add(0, Future.future(a -> {");
							tl(5, i18nGlobale.getString(I18n.var_connexionSql), ".preparedQuery(bSql.toString())");
							tl(7, ".execute(Tuple.tuple(bParams)");
							tl(7, ").onSuccess(b -> {");
							tl(6, "a.handle(Future.succeededFuture());");
							tl(5, "}).onFailure(ex -> {");
							tl(6, "RuntimeException ex2 = new RuntimeException(\"", i18nGlobale.getString(I18n.var_valeur), " ", classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), "\", ex);");
							tl(6, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex2);");
							tl(6, "a.handle(Future.failedFuture(ex2));");
							tl(5, "});");
							tl(4, "}));");
							tl(3, "}");
							tl(3, "CompositeFuture.all(futures1).onSuccess(a -> {");
							tl(4, "CompositeFuture.all(futures2).onSuccess(b -> {");
							tl(5, classeNomSimple, " o3 = new ", classeNomSimple, "();");
							tl(5, "o3.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_());");
							tl(5, "o3.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
							tl(5, "promise.complete(o3);");
							tl(4, "}).onFailure(ex -> {");
							tl(5, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(5, "promise.fail(ex);");
							tl(4, "});");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(4, "promise.fail(ex);");
							tl(3, "});");
							tl(2, "} catch(Exception ex) {");
							tl(3, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(3, "promise.fail(ex);");
							tl(2, "}");
							tl(2, "return promise.future();");
							tl(1, "}");
						} else if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTCopie))) {
							l();
							tl(1, "public Future<Void> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, JsonObject jsonObject) {");
							tl(2, "Promise<Void> promise = Promise.promise();");
							tl(2, "try {");
							tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
							tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
							tl(3, "List<String> ", i18nGlobale.getString(I18n.var_solrIds), " = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteApi), ").map(r -> r.get", i18nGlobale.getString(I18n.var_SolrIds), "()).orElse(new ArrayList<>());");
							tl(3, "List<String> classes = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteApi), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
							tl(3, "SqlConnection ", i18nGlobale.getString(I18n.var_connexionSql), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ConnexionSql), "();");
							tl(3, "Integer num = 1;");
							tl(3, "StringBuilder bSql = new StringBuilder(\"UPDATE ", classeNomSimple, " SET \");");
							tl(3, "List<Object> bParams = new ArrayList<Object>();");
							tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
							tl(3, "o2.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
							tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
							tl(3, "List<Future> futures1 = new ArrayList<>();");
							tl(3, "List<Future> futures2 = new ArrayList<>();");
							l();
							tl(3, "if(jsonObject != null) {");
							tl(4, "JsonArray ", i18nGlobale.getString(I18n.var_entite), "Vars = jsonObject.getJsonArray(\"", classeVarSauvegardes, "\");");
							tl(4, "for(Integer i = 0; i < ", i18nGlobale.getString(I18n.var_entite), "Vars.size(); i++) {");
							tl(5, "String ", i18nGlobale.getString(I18n.var_entite), "Var = ", i18nGlobale.getString(I18n.var_entite), "Vars.getString(i);");
							tl(5, "switch(", i18nGlobale.getString(I18n.var_entite), "Var) {");
							if(classeApiMethode.equals("PUTImport"))
								s(wApiGenererPutImport.toString());
							else if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTFusion)))
								s(wApiGenererPutFusion.toString());
							else if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTCopie)))
								s(wApiGenererPutCopie.toString());
							tl(5, "}");
							tl(4, "}");
							tl(3, "}");
							tl(3, "bSql.append(\" WHERE ", classeVarClePrimaire, "=$\" + num);");
							tl(3, "if(bParams.size() > 0) {");
							tl(3, "bParams.add(", classeVarClePrimaire, ");");
							tl(3, "num++;");
							tl(4, "futures2.add(0, Future.future(a -> {");
							tl(5, i18nGlobale.getString(I18n.var_connexionSql), ".preparedQuery(bSql.toString())");
							tl(7, ".execute(Tuple.tuple(bParams)");
							tl(7, ").onSuccess(b -> {");
							tl(6, "a.handle(Future.succeededFuture());");
							tl(5, "}).onFailure(ex -> {");
							tl(6, "RuntimeException ex2 = new RuntimeException(\"", i18nGlobale.getString(I18n.var_valeur), " ", classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), "\", ex);");
							tl(6, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex2);");
							tl(6, "a.handle(Future.failedFuture(ex2));");
							tl(5, "});");
							tl(4, "}));");
							tl(3, "}");
							tl(3, "CompositeFuture.all(futures1).onSuccess(a -> {");
							tl(4, "CompositeFuture.all(futures2).onSuccess(b -> {");
							tl(5, "promise.complete();");
							tl(4, "}).onFailure(ex -> {");
							tl(5, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(5, "promise.fail(ex);");
							tl(4, "});");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(4, "promise.fail(ex);");
							tl(3, "});");
							tl(2, "} catch(Exception ex) {");
							tl(3, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
							tl(3, "promise.fail(ex);");
							tl(2, "}");
							tl(2, "return promise.future();");
							tl(1, "}");
						}
					}

					//////////////
					// PageInit //
					//////////////

					if(classePageNomCanoniqueMethode != null) {
						l();
						tl(1, "public void ", classeApiOperationIdMethode, i18nGlobale.getString(I18n.var_Page), "Init(", classePageNomSimpleMethode, " page, ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_liste), classeNomSimple, ") {");
						tl(1, "}");
					}
					ecrireGenApiServiceImplReponse(classeLangueNom, classeApiMethode);
				}
			}
		}
	}
	public void ecrireGenApiServiceImplReponse(String classeLangueNom, String classeApiMethode) throws Exception {

					/////////////////
					// Reponse 200 //
					/////////////////
					if(classePageNomCanoniqueMethode != null) {
						l();
						tl(1, "public String ", i18nGlobale.getString(I18n.var_template), classeApiMethode, classeNomSimple, "(ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), ") {");
						//STUFF0
						if(classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageEdition))
								|| classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageRecherche))
								) {
							tl(2, "return \"", classePageTemplateMethode, "\";");
						} else {
							tl(2, "return String.format(\"", classePageTemplateMethode, "\", ", i18nGlobale.getString(I18n.var_requeteService), ".getExtra().getString(\"uri\").substring(1));");
						}
						t(1, "}");
					}
					l();
					t(1, "public Future<ServiceResponse> ", i18nGlobale.getString(I18n.var_reponse), "200", classeApiMethode, classeNomSimple, "(");
	
					if(classeApiMethode.contains("POST") || classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTCopie)))
						s(classeNomSimple, " o");
					else if(classeApiMethode.equals("PUTImport") || classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTFusion)) || classeApiMethode.contains("DELETE"))
						s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite));
					else if(classeApiMethode.contains("PUT"))
						s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite));
					else if(classeApiMethode.contains("PATCH") || classeApiMethode.contains("DELETE"))
						s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite));
					else if(classeApiMethode.contains("GET") || classePageAvecTemplateMethode || classeApiMethode.contains(i18nGlobale.getString(I18n.var_Recherche)) || classeApiMethode.contains(i18nGlobale.getString(I18n.var_Telechargement)))
						s(classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple);
					else
						s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite));
	
					l(") {");
					tl(2, "Promise<ServiceResponse> promise = Promise.promise();");
					tl(2, "try {");
	
					if(classeApiMethode.contains("POST")) {
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
					}
					else if(classeApiMethode.equals("PUTImport") || classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTFusion))) {
					}
					else if(classeApiMethode.contains("PATCH") || classeApiMethode.contains("DELETE") || classeApiMethode.contains("PUT")) {
					}
					else if(classePageAvecTemplateMethode || classeApiMethode.contains(i18nGlobale.getString(I18n.var_Recherche)) || classeApiMethode.contains("GET") || classeApiMethode.contains(i18nGlobale.getString(I18n.var_Telechargement))) {
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = ", i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple, ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class);");
					}
					else {
					}
	
	
					if(classePageAvecTemplateMethode || classeApiMethode.contains(i18nGlobale.getString(I18n.var_Recherche))) {
						if(classePageNomCanoniqueMethode != null) {
							if(classePartsToutEcrivain == null)
								throw new RuntimeException(String.format("%s %s %s %s %s. ", i18nGlobale.getString(I18n.var_classe), i18nGlobale.getString(I18n.var_ToutEcrivain), i18nGlobale.getString(I18n.var_manquante), i18nGlobale.getString(I18n.var_dans), cheminSrcMainJava));
							tl(3, "String pageTemplateUri = ", i18nGlobale.getString(I18n.var_template), classeApiMethode, classeNomSimple, "(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "());");
							tl(3, "String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);");
							tl(3, "Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);");
							tl(3, "String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName(\"UTF-8\"));");
							tl(3, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
							tl(3, "MultiMap ", i18nGlobale.getString(I18n.var_requeteEnTetes), " = MultiMap.caseInsensitiveMultiMap();");
							tl(3, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_RequeteEnTetes), "(", i18nGlobale.getString(I18n.var_requeteEnTetes), ");");
							l();
							if(classeModele) {
								tl(3, "if(", i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple, ".size() >= 1)");
								tl(4, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_Requete), StringUtils.capitalize(classeVarClePrimaire), "(", i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple, ".get(0).get", StringUtils.capitalize(classeVarClePrimaire), "()", ");");
							}
							if(!classePageSimple)
								tl(3, "page.set", i18nGlobale.getString(I18n.var_ListeRecherche), classeApiClasseNomSimple, "_(", i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple, ");");
							tl(3, "page.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
							tl(3, "page.set", i18nGlobale.getString(I18n.var_RequeteService), "(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "()", ");");
							tl(3, "page.setWebClient(webClient);");
							tl(3, "page.setVertx(vertx);");
							tl(3, "page.", i18nGlobale.getString(I18n.var_promesseLoin), classePageNomSimpleMethode, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(a -> {");
							tl(4, "try {");
							tl(5, "JsonObject ctx = ", classePartsConfigCles.nomSimple(classeLangueNom), ".getPageContext(config);");
							tl(5, "ctx.mergeIn(JsonObject.mapFrom(page));");
							tl(5, "String renderedTemplate = jinjava.render(template, ctx.getMap());");
							tl(5, "Buffer buffer = Buffer.buffer(renderedTemplate);");
						}
						else {
							tl(3, "List<String> fls = ", i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple, ".getRequest().getFields();");
							tl(3, "JsonObject json = new JsonObject();");
							tl(3, "JsonArray l = new JsonArray();");
							tl(3, i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple, ".getList().stream().forEach(o -> {");
							tl(4, "JsonObject json2 = JsonObject.mapFrom(o);");
							tl(4, "if(fls.size() > 0) {");
							tl(5, "Set<String> fieldNames = new HashSet<String>();");
							tl(5, "for(String fieldName : json2.fieldNames()) {");
							tl(6, "String v = ", classeNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), classeNomSimple, "(fieldName);");
							tl(6, "if(v != null)");
							tl(7, "fieldNames.add(", classeNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), classeNomSimple, "(fieldName));");
							tl(5, "}");
							tl(5, "if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals(\"", i18nGlobale.getString(I18n.var_sauvegardes), "_docvalues_strings\")) {");
							tl(6, "fieldNames.removeAll(Optional.ofNullable(json2.getJsonArray(\"", i18nGlobale.getString(I18n.var_sauvegardes), "_docvalues_strings\")).orElse(new JsonArray()).stream().map(s -> s.toString()).collect(Collectors.toList()));");
							tl(6, "fieldNames.remove(\"", classeVarClePrimaire, "_docvalues_long\");");
							tl(6, "fieldNames.remove(\"", i18nGlobale.getString(I18n.var_cree), "_docvalues_date\");");
							tl(5, "}");
							tl(5, "else if(fls.size() >= 1) {");
							tl(6, "fieldNames.removeAll(fls);");
							tl(5, "}");
							tl(5, "for(String fieldName : fieldNames) {");
							tl(6, "if(!fls.contains(fieldName))");
							tl(7, "json2.remove(fieldName);");
							tl(5, "}");
							tl(4, "}");
							tl(4, "l.add(json2);");
							tl(3, "});");
							tl(3, "json.put(", q(i18nGlobale.getString(I18n.var_liste)), ", l);");
							tl(3, "response200Search(", i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple, ".getRequest(), ", i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple, ".getResponse(), json);");
						}
					}
					else if(classeApiMethode.contains("GET")) {
						if(classePageNomCanoniqueMethode != null) {
							tl(3, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
							tl(3, "SolrDocument page", i18nGlobale.getString(I18n.var_DocumentSolr), " = new SolrDocument();");
							tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = ", i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple, ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class);");
							tl(3, "MultiMap ", i18nGlobale.getString(I18n.var_requeteEnTetes), " = MultiMap.caseInsensitiveMultiMap();");
							tl(3, i18nGlobale.getString(I18n.var_requeteSite), ".set", i18nGlobale.getString(I18n.var_RequeteEnTetes), "(", i18nGlobale.getString(I18n.var_requeteEnTetes), ");");
							l();
							tl(3, "page", i18nGlobale.getString(I18n.var_DocumentSolr), ".setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
							tl(3, "page.setPage", i18nGlobale.getString(I18n.var_DocumentSolr), "(page", i18nGlobale.getString(I18n.var_DocumentSolr), ");");
							tl(3, "page.setW(w);");
							tl(3, i18nGlobale.getString(I18n.var_requeteSite), ".setW(w);");
							tl(3, "page.", i18nGlobale.getString(I18n.var_promesseLoin), classePageNomSimpleMethode, "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(a -> {");
							tl(4, "page.html();");
						}
						else {
							tl(3, "JsonObject json = JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple, ".getList().stream().findFirst().orElse(null));");
						}
					}
					else if(classeApiMethode.contains("POST")) {
						tl(3, "JsonObject json = JsonObject.mapFrom(o);");
					}
					else if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTCopie))) {
						tl(3, "JsonObject json = JsonObject.mapFrom(o);");
					}
					else if(classeApiMethode.contains("PUT")) {
						tl(3, "JsonObject json = new JsonObject();");
					}
					else if(classeApiMethode.contains("PATCH")) {
						tl(3, "JsonObject json = new JsonObject();");
					}
					else if(classeApiMethode.contains("DELETE")) {
						tl(3, "JsonObject json = new JsonObject();");
					}
	
					if((classeApiMethode.contains("GET") || classePageAvecTemplateMethode || classeApiMethode.contains(i18nGlobale.getString(I18n.var_Recherche))) && classePageNomCanoniqueMethode != null) {
						tl(5, "promise.complete(new ServiceResponse(200, \"OK\", buffer, ", i18nGlobale.getString(I18n.var_requeteEnTetes), "));");
						tl(4, "} catch(Exception ex) {");
						tl(5, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_reponse), "200", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
						tl(5, "promise.fail(ex);");
						tl(4, "}");
						tl(3, "}).onFailure(ex -> {");
						tl(4, "promise.fail(ex);");
						tl(3, "});");
					}
					else if(classeApiMethode.contains(i18nGlobale.getString(I18n.var_Telechargement))) {
						tl(3, classeNomSimple, " o = ", i18nGlobale.getString(I18n.var_liste), classeApiClasseNomSimple, ".getList().stream().findFirst().orElse(null);");
						tl(3, "if(o == null) {");
						tl(4, "promise.complete(new ServiceResponse(403, \"FORBIDDEN\", Buffer.buffer(), null));");
						tl(3, "} else {");
						tl(4, "String uri = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getExtra().getString(\"uri\");");
						tl(4, "String ", i18nGlobale.getString(I18n.var_cheminTelechargement), " = String.format(\"%s%s.", StringUtils.substringAfterLast(classeApiTypeMedia200Methode, "/"), "\", config.getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".", i18nGlobale.getString(I18n.var_CHEMIN_TELECHARGEMENT), "), uri);");
						tl(4, "vertx.fileSystem().readFile(", i18nGlobale.getString(I18n.var_cheminTelechargement), ").onSuccess(buffer -> {");
						tl(5, "MultiMap headers = MultiMap.caseInsensitiveMultiMap()");
						tl(7, ".add(\"Content-Type\", \"", classeApiTypeMedia200Methode, "\")");
						tl(7, ".add(\"Content-Disposition\", \"attachment; filename=\\\"\" + o.get", StringUtils.capitalize(classeVarId), "() + \".", StringUtils.substringAfterLast(classeApiTypeMedia200Methode, "/"), "\\\"\");");
						tl(5, "promise.complete(new ServiceResponse(200, \"OK\", buffer, headers));");
						tl(4, "}).onFailure(ex -> {");
						tl(5, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.str_Impossible_de_trouver_le_telechargement), "\", ", i18nGlobale.getString(I18n.var_cheminTelechargement), "), ex);");
						tl(5, "promise.fail(ex);");
						tl(4, "});");
						tl(3, "}");
					} else {
						tl(3, "if(json == null) {");
						if(classeVarId == null) {
							tl(4, "String m = String.format(\"", i18nGlobale.getString(I18n.str_s_s_non_trouve), "\", \"", classeNomAdjectifSingulier, "\", null);");
						} else {
							tl(4, "String ", classeVarId, " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams().getJsonObject(\"path\").getString(\"", classeVarId, "\");");
							tl(4, "String m = String.format(\"", i18nGlobale.getString(I18n.str_s_s_non_trouve), "\", \"", classeNomAdjectifSingulier, "\", ", classeVarId, ");");
						}
						tl(4, "promise.complete(new ServiceResponse(404");
						tl(6, ", m");
						tl(6, ", Buffer.buffer(new JsonObject().put(\"", i18nGlobale.getString(I18n.var_message), "\", m).encodePrettily()), null));");
						tl(3, "} else {");
						tl(4, "promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));");
						tl(3, "}");
					}
	
					tl(2, "} catch(Exception ex) {");
						tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_reponse), "200", classeApiMethode, classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
					tl(3, "promise.fail(ex);");
					tl(2, "}");
					tl(2, "return promise.future();");
					tl(1, "}");
					if(classePageAvecTemplateMethode || classeApiMethode.contains(i18nGlobale.getString(I18n.var_Recherche)) && classePageNomCanoniqueMethode == null) {
						tl(1, "public void ", i18nGlobale.getString(I18n.var_reponse), "Pivot", classeApiMethode, classeNomSimple, "(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {");
						tl(2, "if(pivots != null) {");
						tl(3, "for(SolrResponse.Pivot pivotField : pivots) {");
						tl(4, "String ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Indexe), " = pivotField.getField();");
						tl(4, "String ", i18nGlobale.getString(I18n.var_entite), "Var = StringUtils.substringBefore(", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Indexe), ", \"_docvalues_\");");
						tl(4, "JsonObject pivotJson = new JsonObject();");
						tl(4, "pivotArray.add(pivotJson);");
						tl(4, "pivotJson.put(\"field\", entityVar);");
						tl(4, "pivotJson.put(\"value\", pivotField.getValue());");
						tl(4, "pivotJson.put(\"count\", pivotField.getCount());");
						tl(4, "Collection<SolrResponse.PivotRange> pivotRanges = pivotField.getRanges().values();");
						tl(4, "List<SolrResponse.Pivot> pivotFields2 = pivotField.getPivotList();");
						tl(4, "if(pivotRanges != null) {");
						tl(5, "JsonObject rangeJson = new JsonObject();");
						tl(5, "pivotJson.put(\"ranges\", rangeJson);");
						tl(5, "for(SolrResponse.PivotRange rangeFacet : pivotRanges) {");
						tl(6, "JsonObject rangeFacetJson = new JsonObject();");
						tl(6, "String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), \"_docvalues_\");");
						tl(6, "rangeJson.put(rangeFacetVar, rangeFacetJson);");
						tl(6, "JsonObject rangeFacetCountsObject = new JsonObject();");
						tl(6, "rangeFacetJson.put(\"counts\", rangeFacetCountsObject);");
						tl(6, "rangeFacet.getCounts().forEach((value, count) -> {");
						tl(7, "rangeFacetCountsObject.put(value, count);");
						tl(6, "});");
						tl(5, "}");
						tl(4, "}");
						tl(4, "if(pivotFields2 != null) {");
						tl(5, "JsonArray pivotArray2 = new JsonArray();");
						tl(5, "pivotJson.put(\"pivot\", pivotArray2);");
						tl(5, i18nGlobale.getString(I18n.var_reponse), "Pivot", classeApiMethode, classeNomSimple, "(pivotFields2, pivotArray2);");
						tl(4, "}");
						tl(3, "}");
						tl(2, "}");
						tl(1, "}");
					}
	}

	public void ecrireGenApiServiceImpl3(String classeLangueNom) throws Exception {
		classeAutresLangues = ArrayUtils.removeAllOccurences(toutesLangues, classeLangueNom);

		if(auteurGenApiServiceImpl != null) {
	
			SolrQuery rechercheSolr = new SolrQuery();   
			rechercheSolr.setQuery("*:*");
			rechercheSolr.setRows(1000000);
			String fqClassesSuperEtMoi = "(" + classesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
			rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
			rechercheSolr.addFilterQuery("entiteEstSubstitue_indexed_boolean:false");
			rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
		   rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);
			QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
			SolrDocumentList rechercheListe = rechercheReponse.getResults();
			Integer rechercheLignes = rechercheSolr.getRows();
	
			if(rechercheListe.size() > 0) {
				for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
					for(Integer j = 0; j < rechercheListe.size(); j++) {
						SolrDocument entiteDocumentSolr = rechercheListe.get(j);
						entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + classeLangueNom + "_stored_string");
						entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + classeLangueNom + "_stored_string");
						entiteSuffixeType = (String)entiteDocumentSolr.get("entiteSuffixeType_stored_string");
						entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
						entiteTexte = (Boolean)entiteDocumentSolr.get("entiteTexte_stored_boolean");
						entiteLangue = (String)entiteDocumentSolr.get("entiteLangue_stored_string");
						entiteSuggere = (Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean");
					}
					rechercheSolr.setStart(i.intValue() + rechercheLignes);
					rechercheReponse = clientSolrComputate.query(rechercheSolr);
					rechercheListe = rechercheReponse.getResults();
				}
			}
		}
	}

	/** 
	 */ 
	public void ecrireGenApiServiceImpl4(String classeLangueNom) throws Exception {
		classeAutresLangues = ArrayUtils.removeAllOccurences(toutesLangues, classeLangueNom);

		if(auteurGenApiServiceImpl != null) {
			l();
			tl(1, "// General //");

			if(classeApiMethodes.contains("POST") || classeApiMethodes.contains("PATCH") || classeApiMethodes.contains("PUT") || classeApiMethodes.contains("DELETE")) {
				l();
				tl(1, "public Future<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_creer), classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), ") {");
				tl(2, "Promise<", classeApiClasseNomSimple, "> promise = Promise.promise();");
				tl(2, "try {");
				if(classeModele) {
					tl(3, "SqlConnection ", i18nGlobale.getString(I18n.var_connexionSql), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ConnexionSql), "();");
					tl(3, "String ", i18nGlobale.getString(I18n.var_utilisateur), "Id = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Utilisateur), "Id();");
					tl(3, "Long ", i18nGlobale.getString(I18n.var_utilisateurCle), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_UtilisateurCle), "();");
					tl(3, "ZonedDateTime ", i18nGlobale.getString(I18n.var_cree), " = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ObjetJson), "()).map(j -> j.getString(\"", i18nGlobale.getString(I18n.var_cree), "\")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SITE_ZONE))));");
					l();
					if(activerUtilisateurCle) {
						tl(3, i18nGlobale.getString(I18n.var_connexionSql), ".preparedQuery(\"INSERT INTO ", classeNomSimple, "(", i18nGlobale.getString(I18n.var_cree), ", ", i18nGlobale.getString(I18n.var_utilisateurCle), ") VALUES($1, $2) RETURNING ", classeVarClePrimaire, "\")");
						tl(5, ".collecting(Collectors.toList())");
						tl(5,".execute(Tuple.of(", i18nGlobale.getString(I18n.var_cree), ".toOffsetDateTime(), ", i18nGlobale.getString(I18n.var_utilisateurCle), ")).onSuccess(", i18nGlobale.getString(I18n.var_resultat), " -> {");
					} else {
						tl(3, i18nGlobale.getString(I18n.var_connexionSql), ".preparedQuery(\"INSERT INTO ", classeNomSimple, "(", i18nGlobale.getString(I18n.var_cree), ") VALUES($1) RETURNING ", classeVarClePrimaire, "\")");
						tl(5, ".collecting(Collectors.toList())");
						tl(5,".execute(Tuple.of(", i18nGlobale.getString(I18n.var_cree), ".toOffsetDateTime())).onSuccess(", i18nGlobale.getString(I18n.var_resultat), " -> {");
					}
					tl(4, "Row ", i18nGlobale.getString(I18n.var_creer), i18nGlobale.getString(I18n.var_Ligne), " = ", i18nGlobale.getString(I18n.var_resultat), ".value().stream().findFirst().orElseGet(() -> null);");
					tl(4, "Long ", classeVarClePrimaire, " = ", i18nGlobale.getString(I18n.var_creer), i18nGlobale.getString(I18n.var_Ligne), ".getLong(0);");
					tl(4, classeNomSimple, " o = new ", classeNomSimple, "();");
					tl(4, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
					tl(4, "o.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
					tl(4, "promise.complete(o);");
					tl(3, "}).onFailure(ex -> {");
					tl(4, "RuntimeException ex2 = new RuntimeException(ex);");
					tl(4, "LOG.error(\"", i18nGlobale.getString(I18n.var_creer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \", ex2);");
					tl(4, "promise.fail(ex2);");
					tl(3, "});");
				} else {
					tl(3, classeNomSimple, " o = new ", classeNomSimple, "();");
					tl(3, "o.set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
					tl(3, "promise.complete(o);");
					
				}
				tl(2, "} catch(Exception ex) {");
				tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_creer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
				tl(3, "promise.fail(ex);");
				tl(2, "}");
				tl(2, "return promise.future();");
				tl(1, "}");
			}
			l();
			tl(1, "public void ", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Q(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_listeRecherche), ", String ", i18nGlobale.getString(I18n.var_entite), "Var, String ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ", String ", "var", i18nGlobale.getString(I18n.var_Indexe), ") {");
			tl(2, i18nGlobale.getString(I18n.var_listeRecherche), ".q(var", i18nGlobale.getString(I18n.var_Indexe), " + \":\" + (\"*\".equals(", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ") ? ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), " : SearchTool.escapeQueryChars(", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ")));");
			tl(2, "if(!\"*\".equals(", i18nGlobale.getString(I18n.var_entite), "Var)) {");
//			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".setHighlight(true);");
//			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".setHighlightSnippets(3);");
//			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".addHighlightField(var", classeLangueConfig.getString(ConfigCles.var_Indexe), ");");
//			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".setParam(\"hl.encoder\", \"html\");");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public String ", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Fq(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_listeRecherche), ", String ", i18nGlobale.getString(I18n.var_entite), "Var, String ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ", String var", i18nGlobale.getString(I18n.var_Indexe), ") {");
			tl(2, "if(var", i18nGlobale.getString(I18n.var_Indexe), " == null)");
			tl(3, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", i18nGlobale.getString(I18n.str_nest_pas_une_entite_indexe), ". \", ", i18nGlobale.getString(I18n.var_entite), "Var));");
			tl(2, "if(StringUtils.startsWith(", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ", \"[\")) {");
			tl(3, "String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ", \"]\"), \"[\").split(\" TO \");");
			tl(3, "if(fqs.length != 2)");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" invalid range query. \", ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), "));");
			tl(3, "String fq1 = fqs[0].equals(\"*\") ? fqs[0] : ", classeNomSimple, ".staticSearchFq", i18nGlobale.getString(I18n.var_PourClasse), "(", i18nGlobale.getString(I18n.var_entite), "Var, ", i18nGlobale.getString(I18n.var_listeRecherche), ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class), fqs[0]);");
			tl(3, "String fq2 = fqs[1].equals(\"*\") ? fqs[1] : ", classeNomSimple, ".staticSearchFq", i18nGlobale.getString(I18n.var_PourClasse), "(", i18nGlobale.getString(I18n.var_entite), "Var, ", i18nGlobale.getString(I18n.var_listeRecherche), ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class), fqs[1]);");
			tl(3, " return var", i18nGlobale.getString(I18n.var_Indexe), " + \":[\" + fq1 + \" TO \" + fq2 + \"]\";");
			tl(2, "} else {");
			tl(3, "return var", i18nGlobale.getString(I18n.var_Indexe), " + \":\" + SearchTool.escapeQueryChars(", classeNomSimple, ".staticSearchFq", i18nGlobale.getString(I18n.var_PourClasse), "(", i18nGlobale.getString(I18n.var_entite), "Var, ", i18nGlobale.getString(I18n.var_listeRecherche), ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class), ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ")).replace(\"\\\\\", \"\\\\\\\\\");");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void ", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Sort(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_listeRecherche), ", String ", i18nGlobale.getString(I18n.var_entite), "Var, String ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ", String ", "var", i18nGlobale.getString(I18n.var_Indexe), ") {");
			tl(2, "if(var", i18nGlobale.getString(I18n.var_Indexe), " == null)");
			tl(3, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", i18nGlobale.getString(I18n.str_nest_pas_une_entite_indexe), ". \", ", i18nGlobale.getString(I18n.var_entite), "Var));");
			tl(2, i18nGlobale.getString(I18n.var_listeRecherche), ".sort(var", i18nGlobale.getString(I18n.var_Indexe), ", ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ");");
			tl(1, "}");
			l();
			tl(1, "public void ", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Rows(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_listeRecherche), ", Long ", i18nGlobale.getString(I18n.var_valeur), "Rows) {");
			tl(3, i18nGlobale.getString(I18n.var_listeRecherche), ".rows(", i18nGlobale.getString(I18n.var_valeur), "Rows != null ? ", i18nGlobale.getString(I18n.var_valeur), "Rows : 10L);");
			tl(1, "}");
			l();
			tl(1, "public void ", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Start(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_listeRecherche), ", Long ", i18nGlobale.getString(I18n.var_valeur), "Start) {");
			tl(2, i18nGlobale.getString(I18n.var_listeRecherche), ".start(", i18nGlobale.getString(I18n.var_valeur), "Start);");
			tl(1, "}");
			l();
			tl(1, "public void ", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Var(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_listeRecherche), ", String var, String ", i18nGlobale.getString(I18n.var_valeur), ") {");
			tl(2, i18nGlobale.getString(I18n.var_listeRecherche), ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class).get", i18nGlobale.getString(I18n.var_Requete), "Vars().put(var, ", i18nGlobale.getString(I18n.var_valeur), ");");
			tl(1, "}");
			l();
			tl(1, "public void ", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Uri(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_listeRecherche), ") {");
			tl(1, "}");
			l();
			//////////
			// vars //
			//////////
			tl(1, "public Future<ServiceResponse> vars", classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), ") {");
			tl(2, "Promise<ServiceResponse> promise = Promise.promise();");
			tl(2, "try {");
			tl(3, "ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "();");
			l();
			tl(3, i18nGlobale.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").stream().filter(param", i18nGlobale.getString(I18n.var_Requete), " -> \"var\".equals(param", i18nGlobale.getString(I18n.var_Requete), ".getKey()) && param", i18nGlobale.getString(I18n.var_Requete), ".getValue() != null).findFirst().ifPresent(param", i18nGlobale.getString(I18n.var_Requete), " -> {");
			tl(4, "String ", i18nGlobale.getString(I18n.var_entite), "Var = null;");
			tl(4, "String ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), " = null;");
			tl(4, "Object param", i18nGlobale.getString(I18n.var_Valeurs), i18nGlobale.getString(I18n.var_Objet), " = param", i18nGlobale.getString(I18n.var_Requete), ".getValue();");
			tl(4, "JsonArray param", i18nGlobale.getString(I18n.var_Objets), " = param", i18nGlobale.getString(I18n.var_Valeurs), i18nGlobale.getString(I18n.var_Objet), " instanceof JsonArray ? (JsonArray)param", i18nGlobale.getString(I18n.var_Valeurs), i18nGlobale.getString(I18n.var_Objet), " : new JsonArray().add(param", i18nGlobale.getString(I18n.var_Valeurs), i18nGlobale.getString(I18n.var_Objet), ");");
			l();
			tl(4, "try {");
			tl(5, "for(Object param", i18nGlobale.getString(I18n.var_Objet), " : param", i18nGlobale.getString(I18n.var_Objets), ") {");
			tl(6, i18nGlobale.getString(I18n.var_entite), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", i18nGlobale.getString(I18n.var_Objet), ", \":\"));");
			tl(6, i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", i18nGlobale.getString(I18n.var_Objet), ", \":\")), \"UTF-8\");");
			tl(6, i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Requete), "Vars().put(", i18nGlobale.getString(I18n.var_entite), "Var, ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ");");
			tl(5, "}");
			tl(4, "} catch(Exception ex) {");
			tl(5, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
			tl(5, "promise.fail(ex);");
			tl(4, "}");

			tl(3, "});");
			tl(3, "promise.complete();");
			tl(2, "} catch(Exception ex) {");
			tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
			tl(3, "promise.fail(ex);");
			tl(2, "}");
			tl(2, "return promise.future();");
			tl(1, "}");
			///////////////
			// recherche //
			///////////////
			l();
			tl(1, "public Future<", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, ">> ", i18nGlobale.getString(I18n.var_rechercher), classeApiClasseNomSimple, i18nGlobale.getString(I18n.var_Liste), "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), ", Boolean ", i18nGlobale.getString(I18n.var_peupler), ", Boolean ", i18nGlobale.getString(I18n.var_stocker), ", Boolean ", i18nGlobale.getString(I18n.var_modifier), ") {");
			tl(2, "Promise<", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, ">> promise = Promise.promise();");
			tl(2, "try {");
			tl(3, "ServiceRequest ", i18nGlobale.getString(I18n.var_requeteService), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "();");
			tl(3, "String ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Liste), "Str = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
			tl(3, "String[] ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Liste), " = ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Liste), "Str == null ? null : ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Liste), "Str.split(", q(",\\s*"), ");");
			tl(3, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_listeRecherche), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, ">();");
			tl(3, "String facetRange = null;");
			tl(3, "Date facetRangeStart = null;");
			tl(3, "Date facetRangeEnd = null;");
			tl(3, "String facetRangeGap = null;");
			tl(3, "String statsField = null;");
			tl(3, "String statsFieldIndexed = null;");
			tl(3, i18nGlobale.getString(I18n.var_listeRecherche), ".set", i18nGlobale.getString(I18n.var_Peupler), "(", i18nGlobale.getString(I18n.var_peupler), ");");
			tl(3, i18nGlobale.getString(I18n.var_listeRecherche), ".set", i18nGlobale.getString(I18n.var_Stocker), "(", i18nGlobale.getString(I18n.var_stocker), ");");
			tl(3, i18nGlobale.getString(I18n.var_listeRecherche), ".q(\"*:*\");");
			tl(3, i18nGlobale.getString(I18n.var_listeRecherche), ".setC(", classeApiClasseNomSimple, ".class);");
			tl(3, i18nGlobale.getString(I18n.var_listeRecherche), ".set", i18nGlobale.getString(I18n.var_RequeteSite), "_(", i18nGlobale.getString(I18n.var_requeteSite), ");");
			tl(3, i18nGlobale.getString(I18n.var_listeRecherche), ".facetMinCount(1);");
			tl(3, "if(", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Liste), " != null) {");
			tl(4, "for(String v : ", i18nGlobale.getString(I18n.var_entite), i18nGlobale.getString(I18n.var_Liste), ") {");
			tl(5, i18nGlobale.getString(I18n.var_listeRecherche), ".fl(", classeNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), classeNomSimple, "(v));");
			tl(4, "}");
			tl(3, "}");
			s(wFacets);
			if(classeVarId != null) {
				l();
				tl(3, "String ", classeVarId, " = ", i18nGlobale.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"path\").getString(\"", classeVarId, "\");");
				if(classeModele) {
					tl(3, "if(", classeVarId, " != null) {");
					tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".fq(\"", classeVarId, classeVarIdSuffixeSolr, ":\" + SearchTool.escapeQueryChars(", classeVarId, "));");
					tl(3, "}");
				} else {
					tl(3, "if(", classeVarId, " != null) {");
					tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".fq(\"", classeVarId, classeVarIdSuffixeSolr, ":\" + SearchTool.escapeQueryChars(", classeVarId, "));");
					tl(3, "}");
				}
			}
			if(classeAuth && (classeRoleSession || classeRoleUtilisateur)) {
				l();
				tl(3, "if(!", i18nGlobale.getString(I18n.var_requeteSite), ".getScopes().contains(\"GET\")) {");
				tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".fq(\"sessionId_docvalues_string:\" + SearchTool.escapeQueryChars(Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".getSessionId()).orElse(\"\\\"-----\\\"\")) + \" OR \" + \"sessionId_docvalues_string:\" + SearchTool.escapeQueryChars(Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".getSessionId", i18nGlobale.getString(I18n.var_Avant), "()).orElse(\"\\\"-----\\\"\"))");
				tl(6, "+ \" OR ", i18nGlobale.getString(I18n.var_utilisateurId), "_docvalues_string:\" + Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_UtilisateurId), "()).orElse(\"\\\"-----\\\"\"));");
				tl(3, "}");
			} else if(classeRoleSession || classeRoleUtilisateur) {
				l();
				tl(3, i18nGlobale.getString(I18n.var_listeRecherche), ".fq(\"sessionId_docvalues_string:\" + SearchTool.escapeQueryChars(Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".getSessionId()).orElse(\"\\\"-----\\\"\")) + \" OR \" + \"sessionId_docvalues_string:\" + SearchTool.escapeQueryChars(Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".getSessionId", i18nGlobale.getString(I18n.var_Avant), "()).orElse(\"\\\"-----\\\"\"))");
				tl(6, "+ \" OR ", i18nGlobale.getString(I18n.var_utilisateurId), "_docvalues_string:\" + Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_UtilisateurId), "()).orElse(\"\\\"-----\\\"\"));");
			}
			l();
			tl(3, "for(String param", i18nGlobale.getString(I18n.var_Nom), " : ", i18nGlobale.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").fieldNames()) {");
			tl(4, "Object param", i18nGlobale.getString(I18n.var_Valeurs), i18nGlobale.getString(I18n.var_Objet), " = ", i18nGlobale.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").getValue(param", i18nGlobale.getString(I18n.var_Nom), ");");
			tl(4, "String ", i18nGlobale.getString(I18n.var_entite), "Var = null;");
			tl(4, "String ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), " = null;");
			tl(4, "String var", i18nGlobale.getString(I18n.var_Indexe), " = null;");
			tl(4, "String ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Tri), " = null;");
			tl(4, "Long ", i18nGlobale.getString(I18n.var_valeur), "Start = null;");
			tl(4, "Long ", i18nGlobale.getString(I18n.var_valeur), "Rows = null;");
			tl(4, "String ", i18nGlobale.getString(I18n.var_valeur), "CursorMark = null;");
			tl(4, "JsonArray param", i18nGlobale.getString(I18n.var_Objets), " = param", i18nGlobale.getString(I18n.var_Valeurs), i18nGlobale.getString(I18n.var_Objet), " instanceof JsonArray ? (JsonArray)param", i18nGlobale.getString(I18n.var_Valeurs), i18nGlobale.getString(I18n.var_Objet), " : new JsonArray().add(param", i18nGlobale.getString(I18n.var_Valeurs), i18nGlobale.getString(I18n.var_Objet), ");");
			l();
			tl(4, "try {");
	
			tl(5, "if(param", i18nGlobale.getString(I18n.var_Valeurs), i18nGlobale.getString(I18n.var_Objet), " != null && \"facet.pivot\".equals(param", i18nGlobale.getString(I18n.var_Nom), ")) {");
			tl(6, "Matcher mFacetPivot = Pattern.compile(\"(?:(\\\\{![^\\\\}]+\\\\}))?(.*)\").matcher(StringUtils.join(param", i18nGlobale.getString(I18n.var_Objets), ".getList().toArray(), \",\"));");
			tl(6, "if(mFacetPivot.find()) {");
			tl(7, "String solrLocalParams = mFacetPivot.group(1);");
			tl(7, "String[] ", i18nGlobale.getString(I18n.var_entite), "Vars = mFacetPivot.group(2).trim().split(\",\");");
			tl(7, "String[] vars", i18nGlobale.getString(I18n.var_Indexe), " = new String[", i18nGlobale.getString(I18n.var_entite), "Vars.length];");
			tl(7, "for(Integer i = 0; i < ", i18nGlobale.getString(I18n.var_entite), "Vars.length; i++) {");
			tl(8, i18nGlobale.getString(I18n.var_entite), "Var = ", i18nGlobale.getString(I18n.var_entite), "Vars[i];");
			tl(8, "vars", i18nGlobale.getString(I18n.var_Indexe), "[i] = ", classeNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), "", classeNomSimple, "(", i18nGlobale.getString(I18n.var_entite), "Var);");
			tl(7, "}");
			tl(7, i18nGlobale.getString(I18n.var_listeRecherche), ".facetPivot((solrLocalParams == null ? \"\" : solrLocalParams) + StringUtils.join(vars", i18nGlobale.getString(I18n.var_Indexe), ", \",\"));");
			tl(6, "}");
			tl(5, "} else if(param", i18nGlobale.getString(I18n.var_Valeurs), i18nGlobale.getString(I18n.var_Objet), " != null) {");

			tl(6, "for(Object param", i18nGlobale.getString(I18n.var_Objet), " : param", i18nGlobale.getString(I18n.var_Objets), ") {");
			tl(7, "if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"q\")) {");
			tl(8, "Matcher mQ = Pattern.compile(\"(\\\\w+):(.+?(?=(\\\\)|\\\\s+OR\\\\s+|\\\\s+AND\\\\s+|\\\\^|$)))\").matcher((String)param", i18nGlobale.getString(I18n.var_Objet), ");");
			tl(8, "StringBuffer sb = new StringBuffer();");
			tl(8, "while(mQ.find()) {");
			tl(9, i18nGlobale.getString(I18n.var_entite), "Var = mQ.group(1).trim();");
			tl(9, i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), " = mQ.group(2).trim();");
			tl(9, "var", i18nGlobale.getString(I18n.var_Indexe), " = ", classeNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), "", classeNomSimple, "(", i18nGlobale.getString(I18n.var_entite), "Var);");
			tl(9, "String ", i18nGlobale.getString(I18n.var_entite), "Q = ", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Fq(", i18nGlobale.getString(I18n.var_listeRecherche), ", ", i18nGlobale.getString(I18n.var_entite), "Var, ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ", ", "var", i18nGlobale.getString(I18n.var_Indexe), ");");
			tl(9, "mQ.appendReplacement(sb, ", i18nGlobale.getString(I18n.var_entite), "Q);");
			tl(8, "}");
			tl(8, "if(!sb.isEmpty()) {");
			tl(9, "mQ.appendTail(sb);");
			tl(9, i18nGlobale.getString(I18n.var_listeRecherche), ".q(sb.toString());");
			tl(8, "}");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"fq\")) {");
			tl(8, "Matcher mFq = Pattern.compile(\"(\\\\w+):(.+?(?=(\\\\)|\\\\s+OR\\\\s+|\\\\s+AND\\\\s+|$)))\").matcher((String)param", i18nGlobale.getString(I18n.var_Objet), ");");
			tl(9, "StringBuffer sb = new StringBuffer();");
			tl(8, "while(mFq.find()) {");
			tl(9, i18nGlobale.getString(I18n.var_entite), "Var = mFq.group(1).trim();");
			tl(9, i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), " = mFq.group(2).trim();");
			tl(9, "var", i18nGlobale.getString(I18n.var_Indexe), " = ", classeNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), "", classeNomSimple, "(", i18nGlobale.getString(I18n.var_entite), "Var);");
			tl(9, "String ", i18nGlobale.getString(I18n.var_entite), "Fq = ", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Fq(", i18nGlobale.getString(I18n.var_listeRecherche), ", ", i18nGlobale.getString(I18n.var_entite), "Var, ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ", ", "var", i18nGlobale.getString(I18n.var_Indexe), ");");
			tl(9, "mFq.appendReplacement(sb, ", i18nGlobale.getString(I18n.var_entite), "Fq);");
			tl(8, "}");
			tl(8, "if(!sb.isEmpty()) {");
			tl(9, "mFq.appendTail(sb);");
			tl(9, i18nGlobale.getString(I18n.var_listeRecherche), ".fq(sb.toString());");
			tl(8, "}");

			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"sort\")) {");
			tl(8, i18nGlobale.getString(I18n.var_entite), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", i18nGlobale.getString(I18n.var_Objet), ", \" \"));");
			tl(8, i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), " = StringUtils.trim(StringUtils.substringAfter((String)param", i18nGlobale.getString(I18n.var_Objet), ", \" \"));");
			tl(8, "var", i18nGlobale.getString(I18n.var_Indexe), " = ", classeNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), classeNomSimple, "(", i18nGlobale.getString(I18n.var_entite), "Var);");
			tl(8, i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Sort(", i18nGlobale.getString(I18n.var_listeRecherche), ", ", i18nGlobale.getString(I18n.var_entite), "Var, ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ", ", "var", i18nGlobale.getString(I18n.var_Indexe), ");");
//	
//			tl(7, "} else if(param", classeLangueConfig.getString(ConfigCles.var_Nom), ".equals( \"fl\")) {");
//			tl(8, langueConfig.getString(ConfigCles.var_entite), "Var = StringUtils.trim((String)param", langueConfig.getString(ConfigCles.var_Objet), ");");
//			tl(8, "var", langueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", langueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(", langueConfig.getString(ConfigCles.var_entite), "Var);");
//			tl(8, langueConfig.getString(ConfigCles.var_liste), langueConfig.getString(ConfigCles.var_Recherche), ".addField(var", langueConfig.getString(ConfigCles.var_Indexe), ");");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"start\")) {");
			tl(8, i18nGlobale.getString(I18n.var_valeur), "Start = param", i18nGlobale.getString(I18n.var_Objet), " instanceof Long ? (Long)param", i18nGlobale.getString(I18n.var_Objet), " : Long.parseLong(param", i18nGlobale.getString(I18n.var_Objet), ".toString());");
			tl(8, i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Start(", i18nGlobale.getString(I18n.var_listeRecherche), ", ", i18nGlobale.getString(I18n.var_valeur), "Start);");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"rows\")) {");
			tl(8, i18nGlobale.getString(I18n.var_valeur), "Rows = param", i18nGlobale.getString(I18n.var_Objet), " instanceof Long ? (Long)param", i18nGlobale.getString(I18n.var_Objet), " : Long.parseLong(param", i18nGlobale.getString(I18n.var_Objet), ".toString());");
			tl(8, i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Rows(", i18nGlobale.getString(I18n.var_listeRecherche), ", ", i18nGlobale.getString(I18n.var_valeur), "Rows);");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"stats\")) {");
			tl(8, i18nGlobale.getString(I18n.var_listeRecherche), ".stats((Boolean)param", i18nGlobale.getString(I18n.var_Objet), ");");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"stats.field\")) {");
			tl(8, "Matcher mStats = Pattern.compile(\"(?:(\\\\{![^\\\\}]+\\\\}))?(.*)\").matcher((String)param", i18nGlobale.getString(I18n.var_Objet), ");");
			tl(8, "if(mStats.find()) {");
			tl(9, "String solrLocalParams = mStats.group(1);");
			tl(9, i18nGlobale.getString(I18n.var_entite), "Var = mStats.group(2).trim();");
			tl(9, "var", i18nGlobale.getString(I18n.var_Indexe), " = ", classeNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), "", classeNomSimple, "(", i18nGlobale.getString(I18n.var_entite), "Var);");
			tl(9, i18nGlobale.getString(I18n.var_listeRecherche), ".statsField((solrLocalParams == null ? \"\" : solrLocalParams) + var", i18nGlobale.getString(I18n.var_Indexe), ");");
			tl(9, "statsField = ", i18nGlobale.getString(I18n.var_entite), "Var;");
			tl(9, "statsFieldIndexed = var", i18nGlobale.getString(I18n.var_Indexe), ";");
			tl(8, "}");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"facet\")) {");
			tl(8, i18nGlobale.getString(I18n.var_listeRecherche), ".facet((Boolean)param", i18nGlobale.getString(I18n.var_Objet), ");");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"facet.range.start\")) {");
			tl(8, "String startMathStr = (String)param", i18nGlobale.getString(I18n.var_Objet), ";");
			tl(8, "Date start = SearchTool.parseMath(startMathStr);");
			tl(8, i18nGlobale.getString(I18n.var_listeRecherche), ".facetRangeStart(start.toInstant().toString());");
			tl(8, "facetRangeStart = start;");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"facet.range.end\")) {");
			tl(8, "String endMathStr = (String)param", i18nGlobale.getString(I18n.var_Objet), ";");
			tl(8, "Date end = SearchTool.parseMath(endMathStr);");
			tl(8, i18nGlobale.getString(I18n.var_listeRecherche), ".facetRangeEnd(end.toInstant().toString());");
			tl(8, "facetRangeEnd = end;");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"facet.range.gap\")) {");
			tl(8, "String gap = (String)param", i18nGlobale.getString(I18n.var_Objet), ";");
			tl(8, i18nGlobale.getString(I18n.var_listeRecherche), ".facetRangeGap(gap);");
			tl(8, "facetRangeGap = gap;");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"facet.range\")) {");
			tl(8, "Matcher mFacetRange = Pattern.compile(\"(?:(\\\\{![^\\\\}]+\\\\}))?(.*)\").matcher((String)param", i18nGlobale.getString(I18n.var_Objet), ");");
			tl(8, "if(mFacetRange.find()) {");
			tl(9, "String solrLocalParams = mFacetRange.group(1);");
			tl(9, i18nGlobale.getString(I18n.var_entite), "Var = mFacetRange.group(2).trim();");
			tl(9, "var", i18nGlobale.getString(I18n.var_Indexe), " = ", classeNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), "", classeNomSimple, "(", i18nGlobale.getString(I18n.var_entite), "Var);");
			tl(9, i18nGlobale.getString(I18n.var_listeRecherche), ".facetRange((solrLocalParams == null ? \"\" : solrLocalParams) + var", i18nGlobale.getString(I18n.var_Indexe), ");");
			tl(9, "facetRange = ", i18nGlobale.getString(I18n.var_entite), "Var;");
			tl(8, "}");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"facet.field\")) {");
			tl(8, i18nGlobale.getString(I18n.var_entite), "Var = (String)param", i18nGlobale.getString(I18n.var_Objet), ";");
			tl(8, "var", i18nGlobale.getString(I18n.var_Indexe), " = ", classeNomSimple, ".var", i18nGlobale.getString(I18n.var_Indexe), classeNomSimple, "(", i18nGlobale.getString(I18n.var_entite), "Var);");
			tl(8, "if(var", i18nGlobale.getString(I18n.var_Indexe), " != null)");
			tl(9, i18nGlobale.getString(I18n.var_listeRecherche), ".facetField(var", i18nGlobale.getString(I18n.var_Indexe), ");");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"var\")) {");
			tl(8, i18nGlobale.getString(I18n.var_entite), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", i18nGlobale.getString(I18n.var_Objet), ", \":\"));");
			tl(8, i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", i18nGlobale.getString(I18n.var_Objet), ", \":\")), \"UTF-8\");");
			tl(8, i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Var(", i18nGlobale.getString(I18n.var_listeRecherche), ", ", i18nGlobale.getString(I18n.var_entite), "Var, ", i18nGlobale.getString(I18n.var_valeur), i18nGlobale.getString(I18n.var_Indexe), ");");
	
			tl(7, "} else if(param", i18nGlobale.getString(I18n.var_Nom), ".equals(\"cursorMark\")) {");
			tl(8, i18nGlobale.getString(I18n.var_valeur), "CursorMark = (String)param", i18nGlobale.getString(I18n.var_Objet), ";");
			tl(8, i18nGlobale.getString(I18n.var_listeRecherche), ".cursorMark((String)param", i18nGlobale.getString(I18n.var_Objet), ");");
	
			tl(7, "}");
			tl(6, "}");
			tl(6, i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "Uri(", i18nGlobale.getString(I18n.var_listeRecherche), ");");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "ExceptionUtils.rethrow(e);");
			tl(4, "}");

			tl(3, "}");
			if(classeVarCree != null) {
				tl(3, "if(\"*:*\".equals(", i18nGlobale.getString(I18n.var_listeRecherche), ".getQuery()) && ", i18nGlobale.getString(I18n.var_listeRecherche), ".getSorts().size() == 0) {");
				if(classeTrisVar != null && classeTrisVar.size() > 0) {
					for(int i = 0; i < classeTrisVar.size(); i++) {
						String classeTriVar = null;
						String classeTriOrdre = null;
						String classeTriSuffixeType = null;
						try {
							classeTriVar = classeTrisVar.get(i);
							classeTriOrdre = classeTrisOrdre.get(i);
							classeTriSuffixeType = classeTrisSuffixeType.get(i);
							tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".sort(\"", classeTriVar, "_docvalues", classeTriSuffixeType, "\", \"", classeTriOrdre, "\");");
						} catch(Throwable ex) {
							LOG.error(String.format(i18nGlobale.getString(I18n.str_la_variable_de_tri_nexiste_pas), classeTriVar, classeNomSimple), ex);
						}
					}
					tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".setDefaultSort(true);");
				}
				else {
					tl(4, i18nGlobale.getString(I18n.var_listeRecherche), ".sort(\"", classeVarCree, "_docvalues_date\", \"desc\");");
				}
				tl(3, "}");
			}
			tl(3, "String facetRange2 = facetRange;");
			tl(3, "Date facetRangeStart2 = facetRangeStart;");
			tl(3, "Date facetRangeEnd2 = facetRangeEnd;");
			tl(3, "String facetRangeGap2 = facetRangeGap;");
			tl(3, "String statsField2 = statsField;");
			tl(3, "String statsFieldIndexed2 = statsFieldIndexed;");
			tl(3, i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "2(", i18nGlobale.getString(I18n.var_requeteSite), ", ", i18nGlobale.getString(I18n.var_peupler), ", ", i18nGlobale.getString(I18n.var_stocker), ", ", i18nGlobale.getString(I18n.var_modifier), ", ", i18nGlobale.getString(I18n.var_listeRecherche), ");");
			tl(3, i18nGlobale.getString(I18n.var_listeRecherche), ".", i18nGlobale.getString(I18n.var_promesseLoin), i18nGlobale.getString(I18n.var_PourClasse), "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(", i18nGlobale.getString(I18n.var_listeRecherche), "2 -> {");
			tl(4, "if(facetRange2 != null && statsField2 != null && facetRange2.equals(statsField2)) {");
			tl(5, "StatsField stats = ", i18nGlobale.getString(I18n.var_listeRecherche), ".getResponse().getStats().getStatsFields().get(statsFieldIndexed2);");
			tl(5, "Instant min = Optional.ofNullable(stats.getMin()).map(val -> Instant.parse(val.toString())).orElse(Instant.now());");
			tl(5, "Instant max = Optional.ofNullable(stats.getMax()).map(val -> Instant.parse(val.toString())).orElse(Instant.now());");
			tl(5, "if(min.equals(max)) {");
			tl(6, "min = min.minus(1, ChronoUnit.DAYS);");
			tl(6, "max = max.plus(2, ChronoUnit.DAYS);");
			tl(5, "}");
			tl(5, "Duration duration = Duration.between(min, max);");
			tl(5, "String gap = \"DAY\";");
			tl(5, "if(duration.toDays() >= 365)");
			tl(6, "gap = \"YEAR\";");
			tl(5, "else if(duration.toDays() >= 28)");
			tl(6, "gap = \"MONTH\";");
			tl(5, "else if(duration.toDays() >= 1)");
			tl(6, "gap = \"DAY\";");
			tl(5, "else if(duration.toHours() >= 1)");
			tl(6, "gap = \"HOUR\";");
			tl(5, "else if(duration.toMinutes() >= 1)");
			tl(6, "gap = \"MINUTE\";");
			tl(5, "else if(duration.toMillis() >= 1000)");
			tl(6, "gap = \"SECOND\";");
			tl(5, "else if(duration.toMillis() >= 1)");
			tl(6, "gap = \"MILLI\";");
			l();
			tl(5, "if(facetRangeStart2 == null)");
			tl(6, i18nGlobale.getString(I18n.var_listeRecherche), ".facetRangeStart(min.toString());");
			tl(5, "if(facetRangeEnd2 == null)");
			tl(6, i18nGlobale.getString(I18n.var_listeRecherche), ".facetRangeEnd(max.toString());");
			tl(5, "if(facetRangeGap2 == null)");
			tl(6, i18nGlobale.getString(I18n.var_listeRecherche), ".facetRangeGap(String.format(\"+1%s\", gap));");
			tl(5, i18nGlobale.getString(I18n.var_listeRecherche), ".query().onSuccess(b -> {");
			tl(6, "promise.complete(", i18nGlobale.getString(I18n.var_listeRecherche), ");");
			tl(5, "}).onFailure(ex -> {");
			tl(6, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
			tl(6, "promise.fail(ex);");
			tl(5, "});");
			tl(4, "} else {");
			tl(5, "promise.complete(", i18nGlobale.getString(I18n.var_listeRecherche), ");");
			tl(4, "}");
			tl(3, "}).onFailure(ex -> {");
			tl(4, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
			tl(4, "promise.fail(ex);");
			tl(3, "});");
			tl(2, "} catch(Exception ex) {");
			tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
			tl(3, "promise.fail(ex);");
			tl(2, "}");
			tl(2, "return promise.future();");
			tl(1, "}");
			tl(1, "public void ", i18nGlobale.getString(I18n.var_rechercher), classeNomSimple, "2(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), ", Boolean ", i18nGlobale.getString(I18n.var_peupler), ", Boolean ", i18nGlobale.getString(I18n.var_stocker), ", Boolean ", i18nGlobale.getString(I18n.var_modifier), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", i18nGlobale.getString(I18n.var_listeRecherche), ") {");
			tl(1, "}");
		
			/////////////
			// definir //
			/////////////
			l();
			if(classeModele) {
				tl(1, "public Future<Void> ", i18nGlobale.getString(I18n.var_definir), classeNomSimple, "(", classeNomSimple, " o, Boolean patch) {");
				tl(2, "Promise<Void> promise = Promise.promise();");
				tl(2, "try {");
				tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
				tl(3, "SqlConnection ", i18nGlobale.getString(I18n.var_connexionSql), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ConnexionSql), "();");
				tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
				tl(3, i18nGlobale.getString(I18n.var_connexionSql), ".preparedQuery(\"SELECT ", wApiSqlSelect, " FROM ", classeNomSimple, " WHERE ", classeVarClePrimaire, "=$1\")");
				tl(5, ".collecting(Collectors.toList())");
				tl(5, ".execute(Tuple.of(", classeVarClePrimaire, ")");
				tl(5, ").onSuccess(", i18nGlobale.getString(I18n.var_resultat), " -> {");
				tl(4, "try {");
				tl(5, "for(Row definition : ", i18nGlobale.getString(I18n.var_resultat), ".value()) {");
				tl(6, "for(Integer i = 0; i < definition.size(); i++) {");
				tl(7, "String columnName = definition.getColumnName(i);");
				tl(7, "Object columnValue = definition.getValue(i);");
				tl(7, "if(!\"", classeVarClePrimaire, "\".equals(columnName)) {");
				tl(8, "try {");
				tl(9, "o.", i18nGlobale.getString(I18n.var_definir), i18nGlobale.getString(I18n.var_PourClasse), "(columnName, columnValue);");
				tl(8, "} catch(Exception e) {");
				tl(9, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_definir), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), e);");
				tl(8, "}");
				tl(7, "}");
				tl(6, "}");
				tl(5, "}");
				tl(5, "o.", i18nGlobale.getString(I18n.var_promesseLoin), i18nGlobale.getString(I18n.var_PourClasse), "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(a -> {");
				if(activerContextBroker && classeFiware) {
					tl(6, "if(config.getBoolean(ComputateConfigKeys.", i18nGlobale.getString(I18n.var_ACTIVER_CONTEXT_BROKER_ENVOI), ")) {");
					tl(7, "cbUpsertEntity(o, patch).onSuccess(b -> {");
					tl(8, "promise.complete();");
					tl(7, "}).onFailure(ex -> {");
					tl(8, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_definir), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
					tl(8, "promise.fail(ex);");
					tl(7, "});");
					tl(6, "} else {");
					tl(7, "promise.complete();");
					tl(6, "}");
				} else {
					tl(6, "promise.complete();");
				}
				tl(5, "}).onFailure(ex -> {");
				tl(6, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_definir), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
				tl(6, "promise.fail(ex);");
				tl(5, "});");
				tl(4, "} catch(Exception ex) {");
				tl(5, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_definir), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
				tl(5, "promise.fail(ex);");
				tl(4, "}");
				tl(3, "}).onFailure(ex -> {");
				tl(4, "RuntimeException ex2 = new RuntimeException(ex);");
				tl(4, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_definir), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex2);");
				tl(4, "promise.fail(ex2);");
				tl(3, "});");
			} else {
				tl(1, "public Future<Void> ", i18nGlobale.getString(I18n.var_definir), classeNomSimple, "(", classeNomSimple, " o, Boolean patch) {");
				tl(2, "Promise<Void> promise = Promise.promise();");
				tl(2, "try {");
				tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
				tl(4, "try {");
				tl(5, "JsonObject jsonObject = ", i18nGlobale.getString(I18n.var_requeteSite), ".getJsonObject();");
				tl(5, "jsonObject.forEach(definition -> {");
				tl(7, "String columnName;");
				tl(7, "Object columnValue;");
				tl(6, "if(patch && StringUtils.startsWith(definition.getKey(), \"set\")) {");
				tl(7, "columnName = StringUtils.uncapitalize(StringUtils.substringAfter(definition.getKey(), \"set\"));");
				tl(7, "columnValue = definition.getValue();");
				tl(6, "} else {");
				tl(7, "columnName = definition.getKey();");
				tl(7, "columnValue = definition.getValue();");
				tl(6, "}");
				tl(6, "if(!\"", classeVarClePrimaire, "\".equals(columnName)) {");
				tl(7, "try {");
				tl(8, "o.", i18nGlobale.getString(I18n.var_definir), i18nGlobale.getString(I18n.var_PourClasse), "(columnName, columnValue);");
				tl(7, "} catch(Exception e) {");
				tl(8, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_definir), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), e);");
				tl(7, "}");
				tl(6, "}");
				tl(5, "});");
				tl(5, "o.", i18nGlobale.getString(I18n.var_promesseLoin), i18nGlobale.getString(I18n.var_PourClasse), "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(a -> {");
				if(activerContextBroker && classeFiware) {
					tl(6, "cbUpsertEntity(o, patch).onSuccess(b -> {");
					tl(7, "promise.complete();");
					tl(6, "}).onFailure(ex -> {");
					tl(7, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_definir), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
					tl(7, "promise.fail(ex);");
					tl(6, "});");
				} else {
					tl(6, "promise.complete();");
				}
				tl(5, "}).onFailure(ex -> {");
				tl(6, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_definir), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
				tl(6, "promise.fail(ex);");
				tl(5, "});");
				tl(4, "} catch(Exception ex) {");
				tl(5, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_definir), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
				tl(5, "promise.fail(ex);");
				tl(4, "}");
			}
			tl(2, "} catch(Exception ex) {");
			tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_definir), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
			tl(3, "promise.fail(ex);");
			tl(2, "}");
			tl(2, "return promise.future();");
			tl(1, "}");
			if(activerContextBroker && classeFiware) {
				l();
				tl(1, "public Future<Void> cbUpsertEntity(", classeNomSimple, " o, Boolean patch) {");
				tl(2, "Promise<Void> promise = Promise.promise();");
				tl(2, "try {");

				tl(3, "ZonedDateTime observedAt = ZonedDateTime.now(ZoneId.of(\"UTC\"));");
				tl(3, "String observedAtStr = observedAt.format(ComputateZonedDateTimeSerializer.UTC_DATE_TIME_FORMATTER);");
				tl(3, "JsonArray entityArray = new JsonArray();");
				tl(3, "JsonObject entityBody = new JsonObject();");
				tl(3, "entityBody.put(\"@context\", config.getString(ComputateConfigKeys.CONTEXT_BROKER_CONTEXT));");
				tl(3, "entityBody.put(\"id\", o.getId());");
				tl(3, "entityBody.put(\"type\", ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ");");
				tl(3, "entityBody.put(\"NGSILD-Tenant\"");
				tl(5, ", new JsonObject()");
				tl(5, ".put(\"type\", \"Property\")");
				tl(5, ".put(\"value\", o.getNgsildTenant())");
				tl(5, ".put(\"observedAt\", observedAtStr)");
				tl(5, ");");
				tl(3, "entityBody.put(\"NGSILD-Path\"");
				tl(5, ", new JsonObject()");
				tl(5, ".put(\"type\", \"Property\")");
				tl(5, ".put(\"value\", o.getNgsildPath())");
				tl(5, ".put(\"observedAt\", observedAtStr)");
				tl(5, ");");
				l();
				tl(3, "List<String> vars = ", classeNomSimple, ".varsFqForClass();");
				tl(3, "for (String var : vars) {");
				tl(4, "String ngsiType = ", classeNomSimple, ".ngsiType(var);");
				tl(4, "String displayName = Optional.ofNullable(", classeNomSimple, ".displayName", classeNomSimple, "(var)).orElse(var);");
				tl(4, "if (ngsiType != null && displayName != null && !var.equals(\"id\") && !var.equals(\"ngsildData\")) {");
				tl(5, "Object value = o.obtainForClass(var);");
				tl(5, "if(value != null) {");
				tl(6, "Object ngsildVal = ", classeNomSimple, ".ngsi", classeNomSimple, "(var, o);");
				tl(6, "String ngsildType = ", classeNomSimple, ".ngsiType(var);");
				tl(6, "if(ngsildVal != null) {");
				tl(7, "entityBody.put(displayName");
				tl(9, ", new JsonObject()");
				tl(9, ".put(\"type\", ngsildType)");
				tl(9, ".put(\"value\", ngsildVal)");
				tl(9, ".put(\"observedAt\", observedAtStr)");
				tl(9, ");");
				tl(6, "}");
				tl(5, "}");
				tl(4, "}");
				tl(3, "}");
				tl(3, "entityArray.add(entityBody);");
				tl(3, "LOG.debug(entityArray.encodePrettily());");
				tl(3, "webClient.post(");
				tl(5, "Integer.parseInt(config.getString(ComputateConfigKeys.CONTEXT_BROKER_PORT))");
				tl(5, ", config.getString(ComputateConfigKeys.CONTEXT_BROKER_HOST_NAME)");
				tl(5, ", \"/ngsi-ld/v1/entityOperations/upsert/\"");
				tl(5, ")");
				tl(5, ".ssl(Boolean.parseBoolean(config.getString(ComputateConfigKeys.CONTEXT_BROKER_SSL)))");
				tl(5, ".putHeader(\"Content-Type\", \"application/ld+json\")");
				tl(5, ".putHeader(\"Fiware-Service\", o.getNgsildTenant())");
				tl(5, ".putHeader(\"Fiware-ServicePath\", o.getNgsildPath())");
				tl(5, ".putHeader(\"NGSILD-Tenant\", o.getNgsildTenant())");
				tl(5, ".putHeader(\"NGSILD-Path\", o.getNgsildPath())");
				tl(5, ".sendJson(entityArray)");
				tl(5, ".expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_CREATED)).onSuccess(b -> {");
				tl(4, "promise.complete();");
				tl(3, "}).onFailure(ex -> {");
				tl(4, "LOG.error(String.format(\"cbUpsertEntity failed. \"), ex);");
				tl(4, "promise.fail(ex);");
				tl(3, "});");
				tl(2, "} catch(Throwable ex) {");
				tl(3, "LOG.error(String.format(\"cbUpsertEntity failed. \"), ex);");
				tl(3, "promise.fail(ex);");
				tl(2, "}");
				tl(2, "return promise.future();");
				tl(1, "}");
				l();
				tl(1, "public Future<JsonObject> ngsildGetEntity(", classeNomSimple, " o) {");
				tl(2, "Promise<JsonObject> promise = Promise.promise();");
				tl(2, "try {");
				tl(3, "String entityName = o.getName();");
				tl(3, "String entityType = ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_NOM_SIMPLE), ";");
				tl(3, "String entityId = o.getId();");
				tl(3, "String ngsildUri = String.format(\"/ngsi-ld/v1/entities/%s\", urlEncode(entityId));");
				tl(3, "String ngsildContext = config.getString(ComputateConfigKeys.CONTEXT_BROKER_CONTEXT);");
				tl(3, "String link = String.format(\"<%s>; rel=\\\"http://www.w3.org/ns/json-ld#context\\\"; type=\\\"application/ld+json\\\"\", ngsildContext);");
				l();
				tl(3, "webClient.get(");
				tl(5, "Integer.parseInt(config.getString(ComputateConfigKeys.CONTEXT_BROKER_PORT))");
				tl(5, ", config.getString(ComputateConfigKeys.CONTEXT_BROKER_HOST_NAME)");
				tl(5, ", ngsildUri");
				tl(5, ")");
				tl(5, ".ssl(Boolean.parseBoolean(config.getString(ComputateConfigKeys.CONTEXT_BROKER_SSL)))");
				tl(5, ".putHeader(\"Content-Type\", \"application/ld+json\")");
				tl(5, ".putHeader(\"Fiware-Service\", o.getNgsildTenant())");
				tl(5, ".putHeader(\"Fiware-ServicePath\", o.getNgsildPath())");
				tl(5, ".putHeader(\"NGSILD-Tenant\", o.getNgsildTenant())");
				tl(5, ".putHeader(\"NGSILD-Path\", o.getNgsildPath())");
				tl(5, ".putHeader(\"Link\", link)");
				tl(5, ".putHeader(\"Accept\", \"*/*\")");
				tl(5, ".send()");
				tl(5, ".expecting(HttpResponseExpectation.SC_OK.or(HttpResponseExpectation.SC_NOT_FOUND)).onSuccess(entityResponse -> {");
				tl(4, "JsonObject entity = entityResponse.bodyAsJsonObject();");
				tl(4, "entity.remove(\"NGSILD data\");");
				tl(4, "promise.complete(entity);");
				tl(3, "}).onFailure(ex -> {");
				tl(4, "LOG.error(String.format(\"postIotServiceFuture failed. \"), ex);");
				tl(4, "promise.fail(ex);");
				tl(3, "});");
				tl(2, "} catch(Throwable ex) {");
				tl(3, "LOG.error(String.format(\"postIotServiceFuture failed. \"), ex);");
				tl(3, "promise.fail(ex);");
				tl(2, "}");
				tl(2, "return promise.future();");
				tl(1, "}");
				l();
				tl(1, "public Future<Void> cbDeleteEntity(", classeNomSimple, " o) {");
				tl(2, "Promise<Void> promise = Promise.promise();");
				tl(2, "try {");
				tl(3, "webClient.delete(");
				tl(5, "Integer.parseInt(config.getString(ComputateConfigKeys.CONTEXT_BROKER_PORT))");
				tl(5, ", config.getString(ComputateConfigKeys.CONTEXT_BROKER_HOST_NAME)");
				tl(5, ", String.format(\"/ngsi-ld/v1/entities/%s\", urlEncode(o.getId()))");
				tl(5, ")");
				tl(5, ".ssl(Boolean.parseBoolean(config.getString(ComputateConfigKeys.CONTEXT_BROKER_SSL)))");
				tl(5, ".putHeader(\"Content-Type\", \"application/ld+json\")");
				tl(5, ".putHeader(\"Fiware-Service\", o.getNgsildTenant())");
				tl(5, ".putHeader(\"Fiware-ServicePath\", o.getNgsildPath())");
				tl(5, ".putHeader(\"NGSILD-Tenant\", o.getNgsildTenant())");
				tl(5, ".putHeader(\"NGSILD-Path\", o.getNgsildPath())");
				tl(5, ".send()");
				tl(5, ".expecting(HttpResponseExpectation.SC_NO_CONTENT).onSuccess(b -> {");
				tl(4, "promise.complete();");
				tl(3, "}).onFailure(ex -> {");
				tl(4, "if(\"Response status code 404 is not equal to 204\".equals(ex.getMessage())) {");
				tl(5, "promise.complete();");
				tl(4, "} else {");
				tl(5, "LOG.error(String.format(\"cbDeleteEntity failed. \"), ex);");
				tl(5, "promise.fail(ex);");
				tl(4, "}");
				tl(3, "});");
				tl(2, "} catch(Throwable ex) {");
				tl(3, "LOG.error(String.format(\"cbDeleteEntity failed. \"), ex);");
				tl(3, "promise.fail(ex);");
				tl(2, "}");
				tl(2, "return promise.future();");
				tl(1, "}");
			}

			///////////////
			// attribuer //
			///////////////
			if(classeModele) {
				l();
				tl(1, "public Future<Void> ", i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, "(", classeNomSimple, " o) {");
				tl(2, "Promise<Void> promise = Promise.promise();");
				if(wAttribuerSql.getEmpty()) {
					tl(2, "promise.complete();");
				} else {
					tl(2, "try {");
					tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
					tl(3, "SqlConnection ", i18nGlobale.getString(I18n.var_connexionSql), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ConnexionSql), "();");
					tl(3, i18nGlobale.getString(I18n.var_connexionSql), ".preparedQuery(\"", wAttribuerSql, "\")");
					tl(5, ".collecting(Collectors.toList())");
					tl(5, ".execute(Tuple.of(", wAttribuerSqlVars, ")");
					tl(5, ").onSuccess(", i18nGlobale.getString(I18n.var_resultat), " -> {");
					tl(4, "try {");
					tl(5, "if(", i18nGlobale.getString(I18n.var_resultat), " != null) {");
					tl(6, "for(Row definition : ", i18nGlobale.getString(I18n.var_resultat), ".value()) {");
					tl(7, "o.", i18nGlobale.getString(I18n.var_attribuer), i18nGlobale.getString(I18n.var_PourClasse), "(definition.getString(1), definition.getValue(0));");
					tl(6, "}");
					tl(5, "}");
					tl(5, "promise.complete();");
					tl(4, "} catch(Exception ex) {");
					tl(5, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
					tl(5, "promise.fail(ex);");
					tl(4, "}");
					tl(3, "}).onFailure(ex -> {");
					tl(4, "RuntimeException ex2 = new RuntimeException(ex);");
					tl(4, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex2);");
					tl(4, "promise.fail(ex2);");
					tl(3, "});");
					tl(2, "} catch(Exception ex) {");
					tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_attribuer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
					tl(3, "promise.fail(ex);");
					tl(2, "}");
				}
				tl(2, "return promise.future();");
				tl(1, "}");
			}

			/////////////
			// indexer //
			/////////////
			if(classeIndexe) {
				l();
				tl(1, "public String ", i18nGlobale.getString(I18n.var_recherche), "Var(String var", i18nGlobale.getString(I18n.var_Indexe), ") {");
				tl(2, "return ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_recherche), "Var", classeNomSimple, "(var", i18nGlobale.getString(I18n.var_Indexe), ");");
				tl(1, "}");
				l();
				tl(1, "@Override");
				tl(1, "public String get", i18nGlobale.getString(I18n.var_ClasseApiAddresse), "() {");
				tl(2, "return ", classeNomSimple, ".", i18nGlobale.getString(I18n.var_CLASSE_API_ADDRESSE), "_", classeNomSimple, ";");
				tl(1, "}");
				l();
				tl(1, "public Future<", classeNomSimple, "> ", i18nGlobale.getString(I18n.var_indexer), classeNomSimple, "(", classeNomSimple, " o) {");
				tl(2, "Promise<", classeNomSimple, "> promise = Promise.promise();");
				tl(2, "try {");
				tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
				tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
				tl(3, "JsonObject json = new JsonObject();");
				tl(3, "JsonObject add = new JsonObject();");
				tl(3, "json.put(\"add\", add);");
				tl(3, "JsonObject doc = new JsonObject();");
				tl(3, "add.put(\"doc\", doc);");
				tl(3, "o.", i18nGlobale.getString(I18n.var_indexer), classeNomSimple, "(doc);");
				tl(3, "String solrUsername = ", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_USERNAME);");
				tl(3, "String solrPassword = ", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_PASSWORD);");
				tl(3, "String solrHostName = ", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_HOST_NAME);");
				tl(3, "Integer solrPort = Integer.parseInt(", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_PORT));");
				tl(3, "String solrCollection = ", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_COLLECTION);");
				tl(3, "Boolean solrSsl = Boolean.parseBoolean(", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_SSL));");
				tl(3, "Boolean softCommit = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getBoolean(\"softCommit\")).orElse(null);");
				tl(3, "Integer commitWithin = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getInteger(\"commitWithin\")).orElse(null);");
				tl(4, "if(softCommit == null && commitWithin == null)");
				tl(5, "softCommit = true;");
				tl(4, "else if(softCommit == null)");
				tl(5, "softCommit = false;");
				tl(3, "String solrRequestUri = String.format(\"/solr/%s/update%s%s%s\", solrCollection, \"?overwrite=true&wt=json\", softCommit ? \"&softCommit=true\" : \"\", commitWithin != null ? (\"&commitWithin=\" + commitWithin) : \"\");");
				tl(3, i18nGlobale.getString(I18n.var_clientWeb), ".post(solrPort, solrHostName, solrRequestUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).putHeader(\"Content-Type\", \"application/json\").sendBuffer(json.toBuffer()).expecting(HttpResponseExpectation.SC_OK).onSuccess(b -> {");
				tl(4, "promise.complete(o);");
				tl(3, "}).onFailure(ex -> {");
				tl(4, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_indexer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), new RuntimeException(ex));");
				tl(4, "promise.fail(ex);");
				tl(3, "});");
				tl(2, "} catch(Exception ex) {");
				tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_indexer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
				tl(3, "promise.fail(ex);");
				tl(2, "}");
				tl(2, "return promise.future();");
				tl(1, "}");

				////////////////
				// desindexer //
				////////////////
				l();
				tl(1, "public Future<", classeNomSimple, "> ", i18nGlobale.getString(I18n.var_desindexer), classeNomSimple, "(", classeNomSimple, " o) {");
				tl(2, "Promise<", classeNomSimple, "> promise = Promise.promise();");
				tl(2, "try {");
				tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
				tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
				tl(3, "o.", i18nGlobale.getString(I18n.var_promesseLoin), i18nGlobale.getString(I18n.var_PourClasse), "(", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(a -> {");
				tl(4, "JsonObject json = new JsonObject();");
				tl(4, "JsonObject delete = new JsonObject();");
				tl(4, "json.put(\"delete\", delete);");
				tl(4, "String query = String.format(\"filter(%s:%s)\", ", classeNomSimple, ".VAR_", solrId, ", o.", i18nGlobale.getString(I18n.var_obtenir), i18nGlobale.getString(I18n.var_PourClasse), "(", classeNomSimple, ".VAR_", solrId, "));");
				tl(4, "delete.put(\"query\", query);");
				tl(4, "String solrUsername = ", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_USERNAME);");
				tl(4, "String solrPassword = ", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_PASSWORD);");
				tl(4, "String solrHostName = ", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_HOST_NAME);");
				tl(4, "Integer solrPort = Integer.parseInt(", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_PORT));");
				tl(4, "String solrCollection = ", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_COLLECTION);");
				tl(4, "Boolean solrSsl = Boolean.parseBoolean(", i18nGlobale.getString(I18n.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_SSL));");
				tl(4, "Boolean softCommit = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getBoolean(\"softCommit\")).orElse(null);");
				tl(4, "Integer commitWithin = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getInteger(\"commitWithin\")).orElse(null);");
				tl(5, "if(softCommit == null && commitWithin == null)");
				tl(6, "softCommit = true;");
				tl(5, "else if(softCommit == null)");
				tl(6, "softCommit = false;");
				tl(4, "String solrRequestUri = String.format(\"/solr/%s/update%s%s%s\", solrCollection, \"?overwrite=true&wt=json\", softCommit ? \"&softCommit=true\" : \"\", commitWithin != null ? (\"&commitWithin=\" + commitWithin) : \"\");");
				tl(4, i18nGlobale.getString(I18n.var_clientWeb), ".post(solrPort, solrHostName, solrRequestUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).putHeader(\"Content-Type\", \"application/json\").sendBuffer(json.toBuffer()).expecting(HttpResponseExpectation.SC_OK).onSuccess(b -> {");
				tl(5, "promise.complete(o);");
				tl(4, "}).onFailure(ex -> {");
				tl(5, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_desindexer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), new RuntimeException(ex));");
				tl(5, "promise.fail(ex);");
				tl(4, "});");
				tl(3, "}).onFailure(ex -> {");
				tl(4, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_desindexer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
				tl(4, "promise.fail(ex);");
				tl(3, "});");
				tl(2, "} catch(Exception ex) {");
				tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_desindexer), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
				tl(3, "promise.fail(ex);");
				tl(2, "}");
				tl(2, "return promise.future();");
				tl(1, "}");
			}

			///////////////
			// recharger //
			///////////////
			if(classeApiMethodes.contains("PATCH") && classeModele) {
				l();
				tl(1, "public Future<Void> ", i18nGlobale.getString(I18n.var_recharger), classeNomSimple, "(", classeNomSimple, " o) {");
				tl(2, "Promise<Void> promise = Promise.promise();");
				tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o.get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
				tl(2, "try {");
				tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteApi), " = ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteApi), "_();");
				tl(3, "List<String> ", i18nGlobale.getString(I18n.var_solrIds), " = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteApi), ").map(r -> r.get", i18nGlobale.getString(I18n.var_SolrIds), "()).orElse(new ArrayList<>());");
				tl(3, "List<String> classes = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteApi), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
				tl(3, "Boolean ", i18nGlobale.getString(I18n.var_recharger), " = !\"false\".equals(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_Requete), "Vars().get(\"", i18nGlobale.getString(I18n.var_recharger), "\"));");
				tl(3, "if(", i18nGlobale.getString(I18n.var_recharger), " && !Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_ObjetJson), "()).map(JsonObject::isEmpty).orElse(true)) {");
				tl(4, "List<Future> futures = new ArrayList<>();");
				l();
				tl(4, "for(int i=0; i < ", i18nGlobale.getString(I18n.var_solrIds), ".size(); i++) {");
				tl(5, "String ", i18nGlobale.getString(I18n.var_solrId), "2 = ", i18nGlobale.getString(I18n.var_solrIds), ".get(i);");
				tl(5, "String ", i18nGlobale.getString(I18n.var_classeNomSimple), "2 = classes.get(i);");
				s(wIndexerFacetFor);
				tl(4, "}");
				l();
				if(classePartsUtilisateurSite != null && classePartsUtilisateurSite.nomSimple(classeLangueNom).equals(classeNomSimple)) {
					tl(4, "promise.complete();");
				} else {
					tl(4, "CompositeFuture.all(futures).onSuccess(b -> {");
					tl(5, "JsonObject params = new JsonObject();");
					tl(5, "params.put(\"body\", new JsonObject());");
					tl(5, "params.put(\"cookie\", ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams().getJsonObject(\"cookie\"));");
					tl(5, "params.put(\"header\", ", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams().getJsonObject(\"header\"));");
					tl(5, "params.put(\"form\", new JsonObject());");
					tl(5, "params.put(\"path\", new JsonObject());");
					tl(5, "JsonObject query = new JsonObject();");
					tl(5, "Boolean softCommit = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getBoolean(\"softCommit\")).orElse(null);");
					tl(5, "Integer commitWithin = Optional.ofNullable(", i18nGlobale.getString(I18n.var_requeteSite), ".get", i18nGlobale.getString(I18n.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getInteger(\"commitWithin\")).orElse(null);");
					tl(5, "if(softCommit == null && commitWithin == null)");
					tl(6, "softCommit = true;");
					tl(5, "if(softCommit != null)");
					tl(6, "query.put(\"softCommit\", softCommit);");
					tl(5, "if(commitWithin != null)");
					tl(6, "query.put(\"commitWithin\", commitWithin);");
					tl(5, "query.put(\"q\", \"*:*\").put(\"fq\", new JsonArray().add(\"", classeVarClePrimaire, ":\" + o.get", StringUtils.capitalize(classeVarClePrimaire), "())).put(\"var\", new JsonArray().add(\"refresh:false\"));");
					tl(5, "params.put(\"query\", query);");
					tl(5, "JsonObject context = new JsonObject().put(\"params\", params).put(\"user\", ", i18nGlobale.getString(I18n.var_requeteSite), ".getUserPrincipal());");
					tl(5, "JsonObject json = new JsonObject().put(\"context\", context);");
					tl(5, "eventBus.request(", classeNomSimple, ".get", i18nGlobale.getString(I18n.var_ClasseApiAddresse), "(), json, new DeliveryOptions().addHeader(\"action\", \"patch", classeNomSimple, "Future\")).onSuccess(c -> {");
					tl(6, "JsonObject responseMessage = (JsonObject)c.body();");
					tl(6, "Integer statusCode = responseMessage.getInteger(\"statusCode\");");
					tl(6, "if(statusCode.equals(200))");
					tl(7, "promise.complete();");
					tl(6, "else");
					tl(7, "promise.fail(new RuntimeException(responseMessage.getString(\"statusMessage\")));");
					tl(5, "}).onFailure(ex -> {");
					tl(6, "LOG.error(\"", i18nGlobale.getString(I18n.var_Recharger), " ", i18nGlobale.getString(I18n.var_relations), " ", i18nGlobale.getString(I18n.str_a_échoué), ". \", ex);");
					tl(6, "promise.fail(ex);");
					tl(5, "});");
					tl(4, "}).onFailure(ex -> {");
					tl(5, "LOG.error(\"", i18nGlobale.getString(I18n.var_Recharger), " ", i18nGlobale.getString(I18n.var_relations), " ", i18nGlobale.getString(I18n.str_a_échoué), ". \", ex);");
					tl(5, "promise.fail(ex);");
					tl(4, "});");
				}
	
	
				tl(3, "} else {");
				tl(4, "promise.complete();");
				tl(3, "}");
				tl(2, "} catch(Exception ex) {");
				tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_recharger), classeNomSimple, " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
				tl(3, "promise.fail(ex);");
				tl(2, "}");
				tl(2, "return promise.future();");
				tl(1, "}");
			}

			//////////////////////
			// genererCorpsPage //
			//////////////////////
			if(classePageAvecTemplate) {
				l();
				tl(1, "@Override");
				tl(1, "public Future<JsonObject> ", i18nGlobale.getString(I18n.var_genererCorpsPage), "(ComputateSiteRequest ", i18nGlobale.getString(I18n.var_requeteSite), ", Map<String, Object> ctx, String ", i18nGlobale.getString(I18n.var_templateChemin), ", String ", i18nGlobale.getString(I18n.var_classeNomSimple), ") {");
				tl(2, "Promise<JsonObject> promise = Promise.promise();");
				tl(2, "try {");
				tl(3, "Map<String, Object> ", i18nGlobale.getString(I18n.var_resultat), " = (Map<String, Object>)ctx.get(\"", i18nGlobale.getString(I18n.var_resultat), "\");");
				tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), "2 = (", classePartsRequeteSite.nomSimple(classeLangueNom), ")", i18nGlobale.getString(I18n.var_requeteSite), ";");
				tl(3, "String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);");
				tl(3, classeNomSimple, " page = new ", classeNomSimple, "();");
				tl(3, "page.set", i18nGlobale.getString(I18n.var_RequeteSite), "_((", i18nGlobale.getString(I18n.var_RequeteSite), ")", i18nGlobale.getString(I18n.var_requeteSite), ");");
				l();
				s(wPageTemplates);
				l();
				tl(3, "page.promiseDeepForClass((", i18nGlobale.getString(I18n.var_RequeteSite), ")", i18nGlobale.getString(I18n.var_requeteSite), ").onSuccess(a -> {");
				tl(4, "try {");
				// tl(5, "String uri = page.getUri();");
				tl(5, "JsonObject data = JsonObject.mapFrom(", i18nGlobale.getString(I18n.var_resultat), ");");
				// tl(5, "data.put(", classeNomSimple, ".VAR_", classeVarId, ", uri);");
				tl(5, "promise.complete(data);");
				tl(4, "} catch(Exception ex) {");
				tl(5, "LOG.error(String.format(importModelFail, classSimpleName), ex);");
				tl(5, "promise.fail(ex);");
				tl(4, "}");
				tl(3, "}).onFailure(ex -> {");
				tl(4, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_genererCorpsPage), " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
				tl(4, "promise.fail(ex);");
				tl(3, "});");
				tl(2, "} catch(Exception ex) {");
				tl(3, "LOG.error(String.format(\"", i18nGlobale.getString(I18n.var_genererCorpsPage), " ", i18nGlobale.getString(I18n.str_a_échoué), ". \"), ex);");
				tl(3, "promise.fail(ex);");
				tl(2, "}");
				tl(2, "return promise.future();");
				tl(1, "}");
			}
	
			tl(0, "}");

			auteurGenApiServiceImpl.flushClose();
//			System.out.println(classeLangueConfig.getString(ConfigCles.var_Ecrire) + ": " + classeCheminGenApiServiceImpl); 
		}
	}
}
