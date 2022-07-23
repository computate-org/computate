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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.service.ServiceRequest;

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
			auteurGenApiService.l("import io.vertx.ext.web.templ.handlebars.HandlebarsTemplateEngine;");
			auteurGenApiService.l("import io.vertx.core.WorkerExecutor;");
			auteurGenApiService.l("import io.vertx.pgclient.PgPool;");
			if(activerOpenIdConnect) {
				auteurGenApiService.l("import io.vertx.ext.auth.oauth2.OAuth2Auth;");
				auteurGenApiService.l("import io.vertx.ext.auth.authorization.AuthorizationProvider;");
			}
			auteurGenApiService.l();
			auteurGenApiService.l("/**");
			String hackathonMission = (String)classeDoc.get("hackathonMissionGenApiService_stored_string");
			String hackathonColumn = (String)classeDoc.get("hackathonColumnGenApiService_stored_string");
			String hackathonLabels = (String)classeDoc.get("hackathonLabelsGenApiService_stored_string");
			if(hackathonMission != null)
				auteurGenApiService.l(String.format(" * Map.hackathonMission: %s", hackathonMission));
			if(hackathonColumn != null)
				auteurGenApiService.l(String.format(" * Map.hackathonColumn: %s", hackathonColumn));
			if(hackathonLabels != null)
				auteurGenApiService.l(String.format(" * Map.hackathonLabels: %s", hackathonLabels));
			auteurGenApiService.l(" * ", classeLangueConfig.getString(ConfigCles.var_Traduire), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueGenApiServiceLangue = (String)classeDoc.get("classeNomCanoniqueGenApiService_" + langueNom + "_stored_string");
				auteurGenApiService.l(" * ", classeLangueConfig.getString(ConfigCles.var_NomCanonique), ".", langueNom, ": ", classeNomCanoniqueGenApiServiceLangue);
			}
			auteurGenApiService.l(" * Gen: false");
			auteurGenApiService.l(" **/");
			auteurGenApiService.l("@WebApiServiceGen");
			auteurGenApiService.l("@ProxyGen");
			auteurGenApiService.s("public interface ", classeNomSimpleGenApiService, " {");
			auteurGenApiService.l();
//			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");

			l();
			l();
			tl(8, "protected EventBus eventBus;");
			l();
			tl(8, "protected JsonObject config;");
			l();
			tl(8, "protected WorkerExecutor ", classeLangueConfig.getString(ConfigCles.var_executeurTravailleur), ";");
			l();
			tl(8, "protected PgPool pgPool;");
			l();
			tl(8, "protected WebClient ", classeLangueConfig.getString(ConfigCles.var_clientWeb), ";");

			auteurGenApiService.tl(1, "static void ", classeLangueConfig.getString(ConfigCles.var_enregistrer), "Service(EventBus eventBus, JsonObject config, WorkerExecutor ", classeLangueConfig.getString(ConfigCles.var_executeurTravailleur), ", PgPool pgPool, WebClient ", classeLangueConfig.getString(ConfigCles.var_clientWeb), activerOpenIdConnect ? ", OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider" : "", classePage ? ", HandlebarsTemplateEngine templateEngine" : "", ", Vertx vertx) {");
			auteurGenApiService.tl(2, "new ServiceBinder(vertx).setAddress(", q(siteNom, "-", classeLangueNom, "-", classeNomSimple), ").register(", classeNomSimpleGenApiService, ".class, new ", classeNomSimpleApiServiceImpl, "(eventBus, config, ", classeLangueConfig.getString(ConfigCles.var_executeurTravailleur), ", pgPool, ", classeLangueConfig.getString(ConfigCles.var_clientWeb), activerOpenIdConnect ? ", oauth2AuthenticationProvider, authorizationProvider" : "", classePage ? ", templateEngine" : "", "));");
			auteurGenApiService.tl(1, "}");
			auteurGenApiService.l();
			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classePageNomCanoniqueMethode = (String)classeDoc.get("classePageNomCanonique" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiTypeMediaRequeteMethode = (String)classeDoc.get("classeApiTypeMediaRequete" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classePageLangueNom = (String)classeDoc.get("classePageLangueNom" + classeApiMethode + "_" + classeLangueNom + "_stored_string");

				if(classePageLangueNom == null || classePageLangueNom.equals(classeLangueNom)) {
					if(classePageNomCanoniqueMethode != null) {
						auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "Id(");
						if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
							auteurGenApiService.s("" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ");
						auteurGenApiService.l("ServiceRequest ", classeLangueConfig.getString(ConfigCles.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ");");
					}
	
					auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "(");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						auteurGenApiService.s("" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ");
					auteurGenApiService.l("ServiceRequest ", classeLangueConfig.getString(ConfigCles.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ");");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUTImport", classeLangueConfig.getString(ConfigCles.var_PUTFusion), "PATCH")) {
						auteurGenApiService.tl(1, "public void ", classeApiOperationIdMethode, "Future(" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ServiceRequest ", classeLangueConfig.getString(ConfigCles.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ");");
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
			auteurApiServiceImpl.l("import io.vertx.core.eventbus.EventBus;");
			auteurApiServiceImpl.l("import io.vertx.core.WorkerExecutor;");
			auteurApiServiceImpl.l("import io.vertx.core.json.JsonObject;");
			auteurApiServiceImpl.l("import io.vertx.pgclient.PgPool;");
			if(classePage)
				auteurApiServiceImpl.l("import io.vertx.ext.web.templ.handlebars.HandlebarsTemplateEngine;");
//			auteurGenApiService.l("import ", classeNomEnsemble, ".", classeNomSimple, "ApiServiceVertxEBProxy;");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.l("/**");
			String hackathonMission = (String)classeDoc.get("hackathonMissionApiServiceImpl_stored_string");
			String hackathonColumn = (String)classeDoc.get("hackathonColumnApiServiceImpl_stored_string");
			String hackathonLabels = (String)classeDoc.get("hackathonLabelsApiServiceImpl_stored_string");
			if(hackathonMission != null)
				auteurApiServiceImpl.l(String.format(" * Map.hackathonMission: %s", hackathonMission));
			if(hackathonColumn != null)
				auteurApiServiceImpl.l(String.format(" * Map.hackathonColumn: %s", hackathonColumn));
			if(hackathonLabels != null)
				auteurApiServiceImpl.l(String.format(" * Map.hackathonLabels: %s", hackathonLabels));
			auteurApiServiceImpl.l(" * ", classeLangueConfig.getString(ConfigCles.var_Traduire), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueApiServiceImplLangue = (String)classeDoc.get("classeNomCanoniqueApiServiceImpl_" + langueNom + "_stored_string");
				auteurApiServiceImpl.l(" * ", classeLangueConfig.getString(ConfigCles.var_NomCanonique), ".", langueNom, ": ", classeNomCanoniqueApiServiceImplLangue);
			}
			auteurApiServiceImpl.l(" **/");
			auteurApiServiceImpl.l("public class ", classeNomSimpleApiServiceImpl, " extends ", classeNomSimpleGenApiServiceImpl, " {");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.tl(1, "public ", classeNomSimpleApiServiceImpl, "(EventBus eventBus, JsonObject config, WorkerExecutor ", classeLangueConfig.getString(ConfigCles.var_executeurTravailleur), ", PgPool pgPool, WebClient ", classeLangueConfig.getString(ConfigCles.var_clientWeb), activerOpenIdConnect ? ", OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider" : "", classePage ? ", HandlebarsTemplateEngine templateEngine" : "", ") {");
			auteurApiServiceImpl.tl(2, "super(eventBus, config, ", classeLangueConfig.getString(ConfigCles.var_executeurTravailleur), ", pgPool, ", classeLangueConfig.getString(ConfigCles.var_clientWeb), activerOpenIdConnect ? ", oauth2AuthenticationProvider, authorizationProvider" : "", classePage ? ", templateEngine" : "", ");");
			auteurApiServiceImpl.tl(1, "}");
			auteurApiServiceImpl.l("}");

			auteurApiServiceImpl.flushClose();
		}
	}

	/** 
	 * Var.enUS: writeGenApiServiceImpl
	 * Param1.var.enUS: languageName
	 * 
	 * r: entiteNomSimpleVertxJson
	 * r.enUS: entitySimpleNameVertxJson
	 * r: entiteNomCanoniqueVertxJson
	 * r.enUS: entityCanonicalNameVertxJson
	 * r: classeCheminGenApiServiceImpl
	 * r.enUS: classPathGenApiServiceImpl
	 * r: classeCheminApiServiceImpl
	 * r.enUS: classPathApiServiceImpl
	 * r: classeCheminGenApiService
	 * r.enUS: classPathGenApiService
	 * r: entiteClassesSuperEtMoiSansGen
	 * r.enUS: entitySuperClassesAndMeWithoutGen
	 * r: partEstEntite
	 * r.enUS: partIsEntity
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: rechercheListe
	 * r.enUS: searchList
	 * r: rechercheLignes
	 * r.enUS: searchLines
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: entiteVarCapitalise
	 * r.enUS: entityVarCapitalized
	 * r: entiteAttribuerVar
	 * r.enUS: entityAttributeVar
	 * r: entiteAttribuer
	 * r.enUS: entityAttribute
	 * r: entiteDefinir
	 * r.enUS: entityDefine
	 * r: entiteSuffixeType
	 * r.enUS: entityTypeSuffix
	 * r: entiteIndexe
	 * r.enUS: entityIndexed
	 * r: entiteStocke
	 * r.enUS: entityStored
	 * r: entiteSolrNomCanonique
	 * r.enUS: entitySolrCanonicalName
	 * r: entiteSolrNomSimple
	 * r.enUS: entitySolrSimpleName
	 * r: entiteListeNomSimpleVertxJson
	 * r.enUS: entityListSimpleNameVertxJson
	 * r: entiteListeNomCanoniqueVertxJson
	 * r.enUS: entityListCanonicalNameVertxJson
	 * r: classeIndexe
	 * r.enUS: classIndexed
	 * r: entiteValeur
	 * r.enUS: entityValue
	 * r: entiteNumero
	 * r.enUS: entityNumber
	 * r: entiteStr
	 * r.enUS: entityStr
	 * r: classeSauvegarde
	 * r.enUS: classSaved
	 * r: requeteJson
	 * r.enUS: requestJson
	 * r: methodeNom
	 * r.enUS: methodName
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
	 * r: classePageNomSimpleMethode
	 * r.enUS: classPageSimpleNameMethod
	 * r: classePageNomCanonique
	 * r.enUS: classPageCanonicalName
	 * r: classePageNomSimple
	 * r.enUS: classPageSimpleName
	 * r: classeApiTypeMedia
	 * r.enUS: classApiMediaType
	 * r: classePageLangueNom
	 * r.enUS: classPageLanguageName
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: "Recherche"
	 * r.enUS: "Search"
	 * r: "supprimer"
	 * r.enUS: "delete"
	 * r: creerLigne
	 * r.enUS: createRow
	 * r: definirAsync
	 * r.enUS: defineAsync
	 * r: definirPourClasse
	 * r.enUS: defineForClass
	 * r: initLoinPourClasse
	 * r.enUS: initDeepForClass
	 * r: indexerPourClasse
	 * r.enUS: indexForClass
	 * r: classePageSimple
	 * r.enUS: classPageSimple
	 * r: classeApiUriMethode
	 * r.enUS: classApiUriMethod
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: millisRecherche
	 * r.enUS: millisSearch
	 * r: numCommence
	 * r.enUS: numStart
	 * r: numTrouve
	 * r.enUS: numFound
	 * r: numRetourne
	 * r.enUS: numReturned
	 * r: tempsRecherche
	 * r.enUS: timeSearch
	 * r: tempsTransmission
	 * r.enUS: timeTransmission
	 * r: exceptionRecherche
	 * r.enUS: exceptionSearch
	 * r: champNom
	 * r.enUS: fieldName
	 * r: entiteVarStocke
	 * r.enUS: entityVarStored
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 * r: PageDocumentSolr
	 * r.enUS: PageSolrDocument
	 * r: wVarIndexe
	 * r.enUS: wVarIndexed
	 * r: wVarRecherche
	 * r.enUS: wVarSearched
	 * r: wVarSuggere
	 * r.enUS: wVarSuggested
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: entiteTexte
	 * r.enUS: entityText
	 * r: entiteLangue
	 * r.enUS: entityLanguage
	 * r: entiteSuggere
	 * r.enUS: entitySuggested
	 * r: n'est pas une entité indexé.
	 * r.enUS: is not an indexed entity.
	 * r: entiteListeStr
	 * r.enUS: entityListStr
	 * r: entiteListe
	 * r.enUS: entityList
	 * r: classeVarCleUnique
	 * r.enUS: classVarUniqueKey
	 * r: classeNomsCanoniques
	 * r.enUS: classCanonicalNames
	 * r: valeurIndexe
	 * r.enUS: valueIndexed
	 * r: rechercheDebut
	 * r.enUS: searchStart
	 * r: valeurTri
	 * r.enUS: valueSort
	 * 
	 * r: auteurGenApiServiceImpl
	 * r.enUS: writerGenApiServiceImpl
	 * r: auteurApiServiceImpl
	 * r.enUS: writerApiServiceImpl
	 * r: auteurGenApiService
	 * r.enUS: writerGenApiService
	 * 
	 * r: wApiEntites
	 * r.enUS: wApiEntities
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * 
	 * r: classeRolesTrouves
	 * r.enUS: classRolesFound
	 * r: classeRoles
	 * r.enUS: classRoles
	 * 
	 * r: classeFiltresTrouves
	 * r.enUS: classFiltersFound
	 * r: classeFiltre
	 * r.enUS: classFilter
	 * 
	 * r: wApiGenererGet
	 * r.enUS: wApiGenerateGet
	 * r: wApiGenererPatch
	 * r.enUS: wApiGeneratePatch
	 * r: classeCheminApiGen
	 * r.enUS: classPathApiGen
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: gestionnaireEvenements
	 * r.enUS: eventHandler
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: wApiGenererPost
	 * r.enUS: wApiGeneratePost
	 * r: // Une méthode d'usine pour créer une instance et un proxy. 
	 * r.enUS: // A factory method to create an instance and a proxy. 
	 * r: creer
	 * r.enUS: create
	 * r: addresse
	 * r.enUS: address
	 * r: operationRequete
	 * r.enUS: serviceRequest
	 * r: gestionnaireResultat
	 * r.enUS: resultHandler
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: classeImportationsGenApi
	 * r.enUS: classImportsGenApi
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeApiOperationIdMethode
	 * r.enUS: classApiOperationIdMethod
	 * r: classeApiOperationId
	 * r.enUS: classApiOperationId
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: paramRequete
	 * r.enUS: queryParam
	 * r: paramObjet
	 * r.enUS: paramObject
	 * r: paramNom
	 * r.enUS paramName
	 * r: paramObjet
	 * r.enUS: paramObject
	 * r: paramValeursObjet
	 * r.enUS: paramValuesObject
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
	 * r: classePageNomSimpleMethode
	 * r.enUS: classPageSimpleNameMethod
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: objetJson
	 * r.enUS: jsonObject
	 * r: ObjetJson
	 * r.enUS: JsonObject
	 * r: siteUrlBase
	 * r.enUS: siteBaseUrl
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * r: connexionSql
	 * r.enUS: sqlConnection
	 * r: reponseOperation
	 * r.enUS: operationResponse
	 * r: utilisateurValeur
	 * r.enUS: userValue
	 * r: utilisateurPk
	 * r.enUS: userPk
	 * r: UtilisateurId
	 * r.enUS: UserId
	 * r: utilisateurId
	 * r.enUS: userId
	 * 
	 * r: resultatAsync
	 * r.enUS: asyncResult
	 * r: varIndexe
	 * r.enUS: varIndexed
	 * r: varRecherche
	 * r.enUS: varSearched
	 * r: varSuggere
	 * r.enUS: varSuggested
	 * r: entiteNomSimpleCompletGenerique
	 * r.enUS: entitySimpleNameCompleteGeneric
	 * r: entiteNomSimpleComplet
	 * r.enUS: entitySimpleNameComplete
	 * r: entiteNomCanoniqueComplet
	 * r.enUS: entityCanonicalNameComplete
	 * r: entiteNomSimpleGenerique
	 * r.enUS: entitySimpleNameGeneric
	 * r: entiteNomCanoniqueGenerique
	 * r.enUS: entityCanonicalNameGeneric
	 * r: entiteNomSimple
	 * r.enUS: entitySimpleName
	 * r: entiteNomCanonique
	 * r.enUS: entityCanonicalName
	 * r: ClientSql
	 * r.enUS: SqlClient
	 * r: clientSql
	 * r.enUS: sqlClient
	 * r: RequeteService
	 * r.enUS: ServiceRequest
	 * r: classePartsUtilisateurSite
	 * r.enUS: classPartsSiteUser
	 * r: classePartsRequeteApi
	 * r.enUS: classPartsApiRequest
	 * 
	 * r: UtilisateurPrenom
	 * r.enUS: UserFirstName
	 * r: UtilisateurNomFamille
	 * r.enUS: UserLastName
	 * r: UtilisateurNom
	 * r.enUS: UserName
	 * r: UtilisateurId
	 * r.enUS: UserId
	 * r: UtilisateurSite
	 * r.enUS: SiteUser
	 * r: RequeteApi
	 * r.enUS: ApiRequest
	 * r: Utilisateur
	 * r.enUS: User
	 * r: utilisateur
	 * r.enUS: user
	 * r: Partagé
	 * r.enUS: Shared
	 * r: documentsSolr
	 * r.enUS: solrDocuments
	 * r: documentSolr
	 * r.enUS: solrDocument
	 * r: supprimer
	 * r.enUS: delete
	 * r: SQL_vider
	 * r.enUS: SQL_clear
	 * r: RequetePk
	 * r.enUS: RequestPk
	 * r: remplacer
	 * r.enUS: replace
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: erreur
	 * r.enUS: error
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 * r: liste
	 * r.enUS: list
	 * r: ConnexionSql
	 * r.enUS: SqlConnection
	 * r: generer
	 * r.enUS: generate
	 * r: "Pour"
	 * r.enUS: "For"
	 * r: Traduire
	 * r.enUS: Translate
	 * r: nomSimple
	 * r.enUS: simpleName
	 * r: Ecrire: 
	 * r.enUS: Write: 
	 * r: recherche
	 * r.enUS: search
	 * r: auteurApi
	 * r.enUS: writerApi
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: EnsembleInfo
	 * r.enUS: PackageInfo
	 * r: creer
	 * r.enUS: create
	 * r: reponse
	 * r.enUS: response
	 * r: Liste
	 * r.enUS: List
	 * r: definir
	 * r.enUS: define
	 * r: peupler
	 * r.enUS: populate
	 * r: stocker
	 * r.enUS: store
	 * r: Peupler
	 * r.enUS: Populate
	 * r: Stocker
	 * r.enUS: Store
	 * r: archive
	 * r.enUS: archived
	 * r: supprime
	 * r.enUS: deleted
	 * r: Archive
	 * r.enUS: Archived
	 * r: Supprime
	 * r.enUS: Deleted
	 * r: attribuer
	 * r.enUS: attribute
	 * r: indexer
	 * r.enUS: index
	 * 
	 * r: classCanonicalNames_
	 * r.enUS: classeNomsCanoniques_
	 * r: archived_
	 * r.enUS: archive_
	 * r: deleted_
	 * r.enUS: supprime_
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
				String fqClassesSuperEtMoi = "(" + classeEntiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
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
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Boolean)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueDate.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueTimestamp.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toInstant().atZone(ZoneId.systemDefault()).toZonedDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueLocalDate.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Long)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniquePoint.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(Point.valueOf((Double)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueVertxJsonObject.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ".toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(BigDecimal.valueOf((Double)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Double)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Float)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Integer)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".size(); k++) {");
										tl(9, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeurs), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(((String)", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), "));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
								}
								else {
									l();
									tl(7, classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " = o.get", entiteVarCapitalise, "();");
									tl(7, "if(", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), " != null)");
									if (VAL_nomCanoniqueBoolean.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ");");
									} else if (VAL_nomCanoniqueDate.equals(entiteSolrNomCanonique)) {
										if (VAL_nomCanoniqueTimestamp.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), "));");
										} else if (VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), "));");
										} else if (VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), "));");
										} else if (VAL_nomCanoniqueLocalDate.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), "));");
										} else {
											tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), "));");
										}
									} else if (VAL_nomCanoniqueLong.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ");");
									} else if (VAL_nomCanoniquePoint.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ");");
									} else if (VAL_nomCanoniqueVertxJsonObject.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ");");
									} else if (VAL_nomCanoniqueDouble.equals(entiteSolrNomCanonique)) {
										if (VAL_nomCanoniqueBigDecimal.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ");");
										}
										else {
											tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ");");
										}
									} else if (VAL_nomCanoniqueFloat.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ");");
									} else if (VAL_nomCanoniqueInteger.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), ");");
									}
									else {
										tl(8, "w.tl(3, ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Numero), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Valeur), "));");
									}
								}
							}
//	
//							Integer tBase = 3;

							////////////////////////
							// codeApiGenererPost //
							////////////////////////
							o = wApiGenererPost;
					
							if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
								tl(5, "case ", classeNomSimple, ".VAR_", entiteVar, ":");
								tl(6, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var));");
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
									tl(6, "Optional.ofNullable(jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {");
								else
									tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).ifPresent(val -> {");
								if(StringUtils.compare(entiteVar, entiteAttribuerVar) < 0) {
									if("array".equals(entiteTypeJson) && "array".equals(entiteAttribuerTypeJson)) {
										// list, list, <
										tl(7, "futures2.add(Future.future(promise2 -> {");
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
										tl(9, "if(!pks.contains(pk2)) {");
										tl(10, "pks.add(pk2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").insertInto(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").values(pk, pk2).onSuccess(a -> {");
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
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
										tl(9, "if(!pks.contains(pk2)) {");
										tl(10, "pks.add(pk2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk).onSuccess(a -> {");
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
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
										tl(9, "if(!pks.contains(pk2)) {");
										tl(10, "pks.add(pk2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", classeNomSimple, ".class, pk).set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, pk2).onSuccess(a -> {");
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
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
										tl(9, "if(!pks.contains(pk2)) {");
										tl(10, "pks.add(pk2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", classeNomSimple, ".class, pk).set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, pk2).onSuccess(a -> {");
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
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
										tl(9, "if(!pks.contains(pk2)) {");
										tl(10, "pks.add(pk2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").insertInto(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").values(pk2, pk).onSuccess(a -> {");
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
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
										tl(9, "if(!pks.contains(pk2)) {");
										tl(10, "pks.add(pk2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk).onSuccess(a -> {");
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
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
										tl(9, "if(!pks.contains(pk2)) {");
										tl(10, "pks.add(pk2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", classeNomSimple, ".class, pk).set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, pk2).onSuccess(a -> {");
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
										tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
										tl(9, "if(!pks.contains(pk2)) {");
										tl(10, "pks.add(pk2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk).onSuccess(a -> {");
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
								tl(6, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var));");
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
								if(entiteListeTypeJson == null) {
									if(StringUtils.compare(entiteVar, entiteAttribuerVar) < 0) {
										tl(6, "{");
										tl(7, "Long l = Long.parseLong(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var));");
										tl(7, "if(l != null) {");
										if(!"array".equals(entiteAttribuerTypeJson)) {
											// no list, no list, <
											tl(8, "if(bParams.size() > 0) {");
											tl(9, "bSql.append(\", \");");
											tl(8, "}");
											tl(8, "bSql.append(", classeNomSimple, ".VAR_", entiteVar, " + \"=$\" + num);");
											tl(8, "num++;");
											tl(8, "bParams.add(l);");
										} else if(!"array".equals(entiteTypeJson)) {
											// no list, list, <
											tl(8, "if(bParams.size() > 0) {");
											tl(9, "bSql.append(\", \");");
											tl(8, "}");
											tl(8, "bSql.append(", classeNomSimple, ".VAR_", entiteVar, " + \"=$\" + num);");
											tl(8, "num++;");
											tl(8, "bParams.add(l);");
										}
										tl(7, "}");
										tl(6, "}");
									}
									else {
										tl(6, "{");
										tl(7, "Long l = Long.parseLong(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var));");
										tl(7, "if(l != null) {");
										if(!"array".equals(entiteAttribuerTypeJson)) {
											// no list, no list, >
											tl(8, "futures.add(Future.future(a -> {");
											tl(9, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE pk=$2\")");
											tl(11,".execute(Tuple.of(", classeVarClePrimaire, ", l)).onSuccess(b -> {");
											tl(10,"a.handle(Future.succeededFuture());");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "RuntimeException ex2 = new RuntimeException(\"", classeLangueConfig.getString(ConfigCles.var_valeur), " ", classeNomSimple, ".", entiteVar, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), "\", ex);");
											tl(10, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex2);");
											tl(10, "a.handle(Future.failedFuture(ex2));");
											tl(9, "});");
											tl(8, "}));");
										} else if(!"array".equals(entiteTypeJson)) {
											// no list, list, >
											tl(8, "if(bParams.size() > 0) {");
											tl(9, "bSql.append(\", \");");
											tl(8, "}");
											tl(8, "bSql.append(", classeNomSimple, ".VAR_", entiteVar, " + \"=$\" + num);");
											tl(8, "num++;");
											tl(8, "bParams.add(l);");
										}
										tl(7, "}");
										tl(6, "}");
									}
								}
								else {
									if(StringUtils.compare(entiteVar, entiteAttribuerVar) < 0) {
										tl(6, "{");
										tl(7, "for(Long l : Optional.ofNullable(jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).orElse(new JsonArray()).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {");
										tl(8, "futures.add(Future.future(a -> {");
										if("array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
											// list, list, <
											tl(9, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(\"INSERT INTO ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "(pk1, pk2) values($1, $2)\")");
											tl(11,".execute(Tuple.of(", classeVarClePrimaire, ", l)");
										} else if(!"array".equals(entiteAttribuerTypeJson)) {
											// list, no list, <
											tl(9, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
											tl(11,".execute(Tuple.of(", classeVarClePrimaire, ", l)");
										}
										tl(11, ").onSuccess(b -> {");
										tl(10, "a.handle(Future.succeededFuture());");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "RuntimeException ex2 = new RuntimeException(\"", classeLangueConfig.getString(ConfigCles.var_valeur), " ", classeNomSimple, ".", entiteVar, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), "\", ex);");
										tl(10, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex2);");
										tl(10, "a.handle(Future.failedFuture(ex2));");
										tl(9, "});");
										tl(8, "}));");
										tl(8, "if(!pks.contains(l)) {");
										tl(9, "pks.add(l);");
										tl(9, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(8, "}");
										tl(7, "}");
										tl(6, "}");
									}
									else {
										tl(6, "{");
										tl(7, "for(Long l : Optional.ofNullable(jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).orElse(new JsonArray()).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {");
										tl(8, "futures.add(Future.future(a -> {");
										if("array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
											// list, list >
											tl(9, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(\"INSERT INTO ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), "(pk1, pk2) values($1, $2)\")");
											tl(11,".execute(Tuple.of(l, ", classeVarClePrimaire, ")");
										} else if(!"array".equals(entiteAttribuerTypeJson)) {
											// list, no list, >
											tl(9, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
											tl(11,".execute(Tuple.of(", classeVarClePrimaire, ", l)");
										}
										tl(11, ").onSuccess(b -> {");
										tl(10, "a.handle(Future.succeededFuture());");
										tl(9, "}).onFailure(ex -> {");
										tl(10, "RuntimeException ex2 = new RuntimeException(\"", classeLangueConfig.getString(ConfigCles.var_valeur), " ", classeNomSimple, ".", entiteVar, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), "\", ex);");
										tl(10, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex2);");
										tl(10, "a.handle(Future.failedFuture(ex2));");
										tl(9, "});");
										tl(8, "}));");
										tl(8, "if(!pks.contains(l)) {");
										tl(9, "pks.add(l);");
										tl(9, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(8, "}");
										tl(7, "}");
										tl(6, "}");
									}
								}
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

									if(StringUtils.compare(entiteVar, entiteAttribuerVar) < 0) {
										if("array".equals(entiteTypeJson) && "array".equals(entiteAttribuerTypeJson)) {
											// list, list, <
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, "JsonArray set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").insertInto(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").values(pk, pk2).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "Optional.ofNullable(o.get", entiteVarCapitalise, "()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".contains(oVal.toString())).forEach(pk2 -> {");
											tl(7, "if(!pks.contains(pk2)) {");
											tl(8, "pks.add(pk2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").deleteFrom(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").where(pk, pk2).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"addAll", entiteVarCapitalise, "\":");
											tl(6, "JsonArray addAll", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "addAll", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").insertInto(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").values(pk, pk2).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").insertInto(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").values(pk, pk2).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).map(val -> Long.parseLong(val)).ifPresent(pk2 -> {");
											tl(7, "if(!pks.contains(pk2)) {");
											tl(8, "pks.add(pk2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").deleteFrom(", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ").where(pk, pk2).onSuccess(a -> {");
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
											tl(6, "JsonArray set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "Optional.ofNullable(o.get", entiteVarCapitalise, "()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".contains(oVal.toString())).forEach(pk2 -> {");
											tl(7, "if(!pks.contains(pk2)) {");
											tl(8, "pks.add(pk2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).setToNull(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk2).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"addAll", entiteVarCapitalise, "\":");
											tl(6, "JsonArray addAll", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "addAll", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).map(val -> Long.parseLong(val)).ifPresent(pk2 -> {");
											tl(7, "if(!pks.contains(pk2)) {");
											tl(8, "pks.add(pk2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).setToNull(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk2).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures1.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", classeNomSimple, ".class, pk).set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, pk2).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).map(val -> Long.parseLong(val)).ifPresent(pk2 -> {");
											tl(7, "if(!pks.contains(pk2)) {");
											tl(8, "pks.add(pk2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", classeNomSimple, ".class, pk).setToNull(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, pk2).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures1.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", classeNomSimple, ".class, pk).set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, pk2).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).map(val -> Long.parseLong(val)).ifPresent(pk2 -> {");
											tl(7, "if(!pks.contains(pk2)) {");
											tl(8, "pks.add(pk2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", classeNomSimple, ".class, pk).setToNull(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, pk2).onSuccess(a -> {");
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
											tl(6, "JsonArray set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").insertInto(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").values(pk, pk2).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "Optional.ofNullable(o.get", entiteVarCapitalise, "()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".contains(oVal.toString())).forEach(pk2 -> {");
											tl(7, "if(!pks.contains(pk2)) {");
											tl(8, "pks.add(pk2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").deleteFrom(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").where(pk2, pk).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"addAll", entiteVarCapitalise, "\":");
											tl(6, "JsonArray addAll", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "addAll", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").insertInto(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").values(pk, pk2).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").insertInto(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").values(pk, pk2).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).map(val -> Long.parseLong(val)).ifPresent(pk2 -> {");
											tl(7, "if(!pks.contains(pk2)) {");
											tl(8, "pks.add(pk2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").deleteFrom(", entiteAttribuerNomSimple, ".class, ", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, ", classeNomSimple, ".VAR_", entiteVar, ").where(pk2, pk).onSuccess(a -> {");
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
											tl(6, "JsonArray set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk).onSuccess(a -> {");
											tl(10, "promise2.complete();");
											tl(9, "}).onFailure(ex -> {");
											tl(10, "promise2.fail(ex);");
											tl(9, "});");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "Optional.ofNullable(o.get", entiteVarCapitalise, "()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !set", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".contains(oVal.toString())).forEach(pk2 -> {");
											tl(7, "if(!pks.contains(pk2)) {");
											tl(8, "pks.add(pk2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).setToNull(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk2).onSuccess(a -> {");
											tl(9, "promise2.complete();");
											tl(8, "}).onFailure(ex -> {");
											tl(9, "promise2.fail(ex);");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "});");
											tl(6, "break;");
											tl(5, "case \"addAll", entiteVarCapitalise, "\":");
											tl(6, "JsonArray addAll", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), " = Optional.ofNullable(jsonObject.getJsonArray(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).orElse(new JsonArray());");
											tl(6, "addAll", entiteVarCapitalise, classeLangueConfig.getString(ConfigCles.var_Valeurs), ".stream().map(oVal -> oVal.toString()).forEach(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).map(val -> Long.parseLong(val)).ifPresent(pk2 -> {");
											tl(7, "if(!pks.contains(pk2)) {");
											tl(8, "pks.add(pk2);");
											tl(8, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(7, "}");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).setToNull(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk2).onSuccess(a -> {");
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
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures1.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", classeNomSimple, ".class, pk).set(", classeNomSimple, ".VAR_", entiteVar, ", ", entiteAttribuerNomSimple, ".class, pk2).onSuccess(a -> {");
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
										} else {
											// no list, no list, >
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, "Optional.ofNullable(jsonObject.getString(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)).ifPresent(val -> {");
											tl(7, "futures2.add(Future.future(promise2 -> {");
											tl(8, "search(siteRequest).query(", entiteAttribuerNomSimple, ".class, val, inheritPk).onSuccess(pk2 -> {");
											tl(9, "if(!pks.contains(pk2)) {");
											tl(10, "pks.add(pk2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(9, "sql(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").update(", entiteAttribuerNomSimple, ".class, pk2).set(", entiteAttribuerNomSimple, ".VAR_", entiteAttribuerVar, ", ", classeNomSimple, ".class, pk).onSuccess(a -> {");
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
										}
									}
								}
								else if(BooleanUtils.isTrue(entiteDefinir) && BooleanUtils.isTrue(entiteModifier)) {
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
									}
									else {
						
										tl(5, "case \"set", entiteVarCapitalise, "\":");
										tl(7, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var));");
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
	 * Var.enUS: writeGenApiServiceImpl
	 * Param1.var.enUS: languageName
	 * 
	 * r: entiteNomSimpleVertxJson
	 * r.enUS: entitySimpleNameVertxJson
	 * r: entiteNomCanoniqueVertxJson
	 * r.enUS: entityCanonicalNameVertxJson
	 * r: classeCheminGenApiServiceImpl
	 * r.enUS: classPathGenApiServiceImpl
	 * r: classeCheminApiServiceImpl
	 * r.enUS: classPathApiServiceImpl
	 * r: classeCheminGenApiService
	 * r.enUS: classPathGenApiService
	 * r: entiteClassesSuperEtMoiSansGen
	 * r.enUS: entitySuperClassesAndMeWithoutGen
	 * r: partEstEntite
	 * r.enUS: partIsEntity
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: rechercheListe
	 * r.enUS: searchList
	 * r: rechercheLignes
	 * r.enUS: searchLines
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: entiteVarCapitalise
	 * r.enUS: entityVarCapitalized
	 * r: entiteAttribuerVar
	 * r.enUS: entityAttributeVar
	 * r: entiteAttribuer
	 * r.enUS: entityAttribute
	 * r: entiteDefinir
	 * r.enUS: entityDefine
	 * r: entiteSuffixeType
	 * r.enUS: entityTypeSuffix
	 * r: entiteIndexe
	 * r.enUS: entityIndexed
	 * r: entiteStocke
	 * r.enUS: entityStored
	 * r: entiteSolrNomCanonique
	 * r.enUS: entitySolrCanonicalName
	 * r: entiteSolrNomSimple
	 * r.enUS: entitySolrSimpleName
	 * r: entiteListeNomSimpleVertxJson
	 * r.enUS: entityListSimpleNameVertxJson
	 * r: entiteListeNomCanoniqueVertxJson
	 * r.enUS: entityListCanonicalNameVertxJson
	 * r: classeIndexe
	 * r.enUS: classIndexed
	 * r: entiteValeur
	 * r.enUS: entityValue
	 * r: entiteNumero
	 * r.enUS: entityNumber
	 * r: entiteStr
	 * r.enUS: entityStr
	 * r: classeSauvegarde
	 * r.enUS: classSaved
	 * r: requeteJson
	 * r.enUS: requestJson
	 * r: methodeNom
	 * r.enUS: methodName
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
	 * r: classePageNomSimpleMethode
	 * r.enUS: classPageSimpleNameMethod
	 * r: classePageNomCanonique
	 * r.enUS: classPageCanonicalName
	 * r: classePageNomSimple
	 * r.enUS: classPageSimpleName
	 * r: classeApiTypeMedia
	 * r.enUS: classApiMediaType
	 * r: classePageLangueNom
	 * r.enUS: classPageLanguageName
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: "Recherche"
	 * r.enUS: "Search"
	 * r: "supprimer"
	 * r.enUS: "delete"
	 * r: creerLigne
	 * r.enUS: createRow
	 * r: definirAsync
	 * r.enUS: defineAsync
	 * r: definirPourClasse
	 * r.enUS: defineForClass
	 * r: initLoinPourClasse
	 * r.enUS: initDeepForClass
	 * r: indexerPourClasse
	 * r.enUS: indexForClass
	 * r: classePageSimple
	 * r.enUS: classPageSimple
	 * r: classeApiUriMethode
	 * r.enUS: classApiUriMethod
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: millisRecherche
	 * r.enUS: millisSearch
	 * r: numCommence
	 * r.enUS: numStart
	 * r: numTrouve
	 * r.enUS: numFound
	 * r: numRetourne
	 * r.enUS: numReturned
	 * r: tempsRecherche
	 * r.enUS: timeSearch
	 * r: tempsTransmission
	 * r.enUS: timeTransmission
	 * r: exceptionRecherche
	 * r.enUS: exceptionSearch
	 * r: champNom
	 * r.enUS: fieldName
	 * r: entiteVarStocke
	 * r.enUS: entityVarStored
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 * r: PageDocumentSolr
	 * r.enUS: PageSolrDocument
	 * r: wVarIndexe
	 * r.enUS: wVarIndexed
	 * r: wVarRecherche
	 * r.enUS: wVarSearched
	 * r: wVarSuggere
	 * r.enUS: wVarSuggested
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: entiteTexte
	 * r.enUS: entityText
	 * r: entiteLangue
	 * r.enUS: entityLanguage
	 * r: entiteSuggere
	 * r.enUS: entitySuggested
	 * r: n'est pas une entité indexé.
	 * r.enUS: is not an indexed entity.
	 * r: entiteListeStr
	 * r.enUS: entityListStr
	 * r: entiteListe
	 * r.enUS: entityList
	 * r: classeVarCleUnique
	 * r.enUS: classVarUniqueKey
	 * r: classeNomsCanoniques
	 * r.enUS: classCanonicalNames
	 * r: valeurIndexe
	 * r.enUS: valueIndexed
	 * r: rechercheDebut
	 * r.enUS: searchStart
	 * r: valeurTri
	 * r.enUS: valueSort
	 * 
	 * r: auteurGenApiServiceImpl
	 * r.enUS: writerGenApiServiceImpl
	 * r: auteurApiServiceImpl
	 * r.enUS: writerApiServiceImpl
	 * r: auteurGenApiService
	 * r.enUS: writerGenApiService
	 * 
	 * r: wApiEntites
	 * r.enUS: wApiEntities
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * 
	 * r: classeRolesTrouves
	 * r.enUS: classRolesFound
	 * r: classeRoles
	 * r.enUS: classRoles
	 * 
	 * r: classeFiltresTrouves
	 * r.enUS: classFiltersFound
	 * r: classeFiltre
	 * r.enUS: classFilter
	 * 
	 * r: wApiGenererGet
	 * r.enUS: wApiGenerateGet
	 * r: wApiGenererPatch
	 * r.enUS: wApiGeneratePatch
	 * r: classeCheminApiGen
	 * r.enUS: classPathApiGen
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: gestionnaireEvenements
	 * r.enUS: eventHandler
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: wApiGenererPost
	 * r.enUS: wApiGeneratePost
	 * r: // Une méthode d'usine pour créer une instance et un proxy. 
	 * r.enUS: // A factory method to create an instance and a proxy. 
	 * r: creer
	 * r.enUS: create
	 * r: addresse
	 * r.enUS: address
	 * r: operationRequete
	 * r.enUS: serviceRequest
	 * r: gestionnaireResultat
	 * r.enUS: resultHandler
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: classeImportationsGenApi
	 * r.enUS: classImportsGenApi
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeApiOperationIdMethode
	 * r.enUS: classApiOperationIdMethod
	 * r: classeApiOperationId
	 * r.enUS: classApiOperationId
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: paramRequete
	 * r.enUS: queryParam
	 * r: paramObjet
	 * r.enUS: paramObject
	 * r: paramNom
	 * r.enUS paramName
	 * r: paramObjet
	 * r.enUS: paramObject
	 * r: paramValeursObjet
	 * r.enUS: paramValuesObject
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
	 * r: classePageNomSimpleMethode
	 * r.enUS: classPageSimpleNameMethod
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: objetJson
	 * r.enUS: jsonObject
	 * r: ObjetJson
	 * r.enUS: JsonObject
	 * r: siteUrlBase
	 * r.enUS: siteBaseUrl
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * r: connexionSql
	 * r.enUS: sqlConnection
	 * r: reponseOperation
	 * r.enUS: operationResponse
	 * r: utilisateurValeur
	 * r.enUS: userValue
	 * r: utilisateurPk
	 * r.enUS: userPk
	 * r: UtilisateurId
	 * r.enUS: UserId
	 * r: utilisateurId
	 * r.enUS: userId
	 * 
	 * r: resultatAsync
	 * r.enUS: asyncResult
	 * r: varIndexe
	 * r.enUS: varIndexed
	 * r: varRecherche
	 * r.enUS: varSearched
	 * r: varSuggere
	 * r.enUS: varSuggested
	 * r: entiteNomSimpleCompletGenerique
	 * r.enUS: entitySimpleNameCompleteGeneric
	 * r: entiteNomSimpleComplet
	 * r.enUS: entitySimpleNameComplete
	 * r: entiteNomCanoniqueComplet
	 * r.enUS: entityCanonicalNameComplete
	 * r: entiteNomSimpleGenerique
	 * r.enUS: entitySimpleNameGeneric
	 * r: entiteNomCanoniqueGenerique
	 * r.enUS: entityCanonicalNameGeneric
	 * r: entiteNomSimple
	 * r.enUS: entitySimpleName
	 * r: entiteNomCanonique
	 * r.enUS: entityCanonicalName
	 * r: ClientSql
	 * r.enUS: SqlClient
	 * r: clientSql
	 * r.enUS: sqlClient
	 * r: RequeteService
	 * r.enUS: ServiceRequest
	 * r: classePartsUtilisateurSite
	 * r.enUS: classPartsSiteUser
	 * r: classePartsRequeteApi
	 * r.enUS: classPartsApiRequest
	 * 
	 * r: UtilisateurPrenom
	 * r.enUS: UserFirstName
	 * r: UtilisateurNomFamille
	 * r.enUS: UserLastName
	 * r: UtilisateurNom
	 * r.enUS: UserName
	 * r: UtilisateurId
	 * r.enUS: UserId
	 * r: UtilisateurSite
	 * r.enUS: SiteUser
	 * r: RequeteApi
	 * r.enUS: ApiRequest
	 * r: Utilisateur
	 * r.enUS: User
	 * r: utilisateur
	 * r.enUS: user
	 * r: Partagé
	 * r.enUS: Shared
	 * r: documentsSolr
	 * r.enUS: solrDocuments
	 * r: documentSolr
	 * r.enUS: solrDocument
	 * r: supprimer
	 * r.enUS: delete
	 * r: SQL_vider
	 * r.enUS: SQL_clear
	 * r: RequetePk
	 * r.enUS: RequestPk
	 * r: remplacer
	 * r.enUS: replace
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: erreur
	 * r.enUS: error
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 * r: liste
	 * r.enUS: list
	 * r: ConnexionSql
	 * r.enUS: SqlConnection
	 * r: generer
	 * r.enUS: generate
	 * r: "Pour"
	 * r.enUS: "For"
	 * r: Traduire
	 * r.enUS: Translate
	 * r: nomSimple
	 * r.enUS: simpleName
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: Ecrire: 
	 * r.enUS: Write: 
	 * r: recherche
	 * r.enUS: search
	 * r: auteurApi
	 * r.enUS: writerApi
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: EnsembleInfo
	 * r.enUS: PackageInfo
	 * r: creer
	 * r.enUS: create
	 * r: reponse
	 * r.enUS: response
	 * r: Liste
	 * r.enUS: List
	 * r: definir
	 * r.enUS: define
	 * r: peupler
	 * r.enUS: populate
	 * r: stocker
	 * r.enUS: store
	 * r: Peupler
	 * r.enUS: Populate
	 * r: Stocker
	 * r.enUS: Store
	 * r: archive
	 * r.enUS: archived
	 * r: supprime
	 * r.enUS: deleted
	 * r: Archive
	 * r.enUS: Archived
	 * r: Supprime
	 * r.enUS: Deleted
	 * r: attribuer
	 * r.enUS: attribute
	 * r: indexer
	 * r.enUS: index
	 * 
	 * r: classCanonicalNames_
	 * r.enUS: classeNomsCanoniques_
	 * r: archived_
	 * r.enUS: archive_
	 * r: deleted_
	 * r.enUS: supprime_
	 */ 
	public void ecrireGenApiServiceImpl2(String classeLangueNom) throws Exception {
		classeAutresLangues = ArrayUtils.removeAllOccurences(toutesLangues, classeLangueNom);

		if(auteurGenApiServiceImpl != null) {
			o = auteurGenApiServiceImpl;
			l();
			l("/**");
			String hackathonMission = (String)classeDoc.get("hackathonMissionGenApiServiceImpl_stored_string");
			String hackathonColumn = (String)classeDoc.get("hackathonColumnGenApiServiceImpl_stored_string");
			String hackathonLabels = (String)classeDoc.get("hackathonLabelsGenApiServiceImpl_stored_string");
			if(hackathonMission != null)
				l(String.format(" * Map.hackathonMission: %s", hackathonMission));
			if(hackathonColumn != null)
				l(String.format(" * Map.hackathonColumn: %s", hackathonColumn));
			if(hackathonLabels != null)
				l(String.format(" * Map.hackathonLabels: %s", hackathonLabels));
			l(" * ", classeLangueConfig.getString(ConfigCles.var_Traduire), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueGenApiServiceImplLangue = (String)classeDoc.get("classeNomCanoniqueGenApiServiceImpl_" + langueNom + "_stored_string");
				l(" * ", classeLangueConfig.getString(ConfigCles.var_NomCanonique), ".", langueNom, ": ", classeNomCanoniqueGenApiServiceImplLangue);
			}
			l(" **/");
			s("public class ", classeNomSimpleGenApiServiceImpl, " extends ", classePartsBaseApiServiceImpl.nomSimple(langueNom));
			s(" implements ", classeNomSimpleGenApiService);
			l(" {");
			l();
			tl(1, "protected static final Logger LOG = LoggerFactory.getLogger(", classeNomSimpleGenApiServiceImpl, ".class);");
			l();
			tl(1, "public ", classeNomSimpleGenApiServiceImpl, "(EventBus eventBus, JsonObject config, WorkerExecutor ", classeLangueConfig.getString(ConfigCles.var_executeurTravailleur), ", PgPool pgPool, WebClient ", classeLangueConfig.getString(ConfigCles.var_clientWeb), activerOpenIdConnect ? ", OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider" : "", classePage ? ", HandlebarsTemplateEngine templateEngine" : "", ") {");
			tl(2, "super(eventBus, config, ", classeLangueConfig.getString(ConfigCles.var_executeurTravailleur), ", pgPool, ", classeLangueConfig.getString(ConfigCles.var_clientWeb), activerOpenIdConnect ? ", oauth2AuthenticationProvider, authorizationProvider" : "", classePage ? ", templateEngine" : "", ");");
			tl(1, "}");

			for(String classeApiMethode : classeApiMethodes) {
				String classePageNomCanoniqueMethode = (String)classeDoc.get("classePageNomCanonique" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classePageNomSimpleMethode = (String)classeDoc.get("classePageNomSimple" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiTypeMedia200Methode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiTypeMediaRequeteMethode = (String)classeDoc.get("classeApiTypeMediaRequete" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classePageLangueNom = (String)classeDoc.get("classePageLangueNom" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				if(classePageLangueNom == null || classePageLangueNom.equals(classeLangueNom)) {
					l();
					tl(1, "// ", classeApiMethode, " //");
					if(classePageNomCanoniqueMethode != null) {
						l();
						tl(1, "@Override");
						t(1, "public void ", classeApiOperationIdMethode, "Id(");
						if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
							s("JsonObject body, ");
						l("ServiceRequest ", classeLangueConfig.getString(ConfigCles.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ") {");
						tl(2, classeApiOperationIdMethode, "(", classeLangueConfig.getString(ConfigCles.var_requeteService), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ");");
						tl(1, "}");
					}

					/////////
					// API //
					/////////
					l();
					tl(1, "@Override");
					t(1, "public void ", classeApiOperationIdMethode, "(");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						s("" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ");
					l("ServiceRequest ", classeLangueConfig.getString(ConfigCles.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ") {");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						tl(2, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_démarré), ". \"));");
	
					tl(2, classeLangueConfig.getString(ConfigCles.var_utilisateur), "(", classeLangueConfig.getString(ConfigCles.var_requeteService), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class, \"", siteNom, "-", classeLangueNom, "-", classePartsUtilisateurSite.nomSimple(classeLangueNom), "\", \"post", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", \"patch", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\").onSuccess(", classeLangueConfig.getString(ConfigCles.var_requeteSite), " -> {");
					tl(3, "try {");
					if(StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") && StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".setJsonObject(body);");
//					tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteUri), "(Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteService), ".getExtra()).map(extra -> extra.getString(\"uri\")).orElse(null));");
//					tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteMethode), "(Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteService), ".getExtra()).map(extra -> extra.getString(\"method\")).orElse(null));");
					if(
							StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH") 
								&& !(classeRoleSession || classeRoleUtilisateur || classeRoleChacun)
								&& (
								classeRoles.size() > 0
							)
							|| !StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH") && (
								BooleanUtils.isNotTrue(classeRoleSession) 
								&& BooleanUtils.isNotTrue(classePublicLire) 
								&& BooleanUtils.isNotTrue(classeRoleUtilisateur) 
								&& BooleanUtils.isNotTrue(classeRoleChacun) 
								&& ( classeRoles.size() > 0 || classeRoleLires.size() > 0)
							)
							) {
						l();
						tl(4, "List<String> roles = Optional.ofNullable(config.getValue(", classePartsConfigCles.nomSimple(classeLangueNom), ".", classeLangueConfig.getString(ConfigCles.var_AUTH_ROLES_REQUIS), " + \"_", classeNomSimple, "\")).map(v -> v instanceof JsonArray ? (JsonArray)v : new JsonArray(v.toString())).orElse(new JsonArray()).getList();");
						if(StringUtils.containsAny(classeApiMethode, "GET", classeLangueConfig.getString(ConfigCles.var_Recherche))) {
							tl(4, "List<String> ", classeLangueConfig.getString(ConfigCles.var_roleLires), " = Arrays.asList(\"", StringUtils.join(classeRoleLires, "\", \""), "\");");
						}
						tl(4, "if(");
						tl(6, "!CollectionUtils.containsAny(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "(), roles)");
						tl(6, "&& !CollectionUtils.containsAny(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "(), roles)");
						if(StringUtils.containsAny(classeApiMethode, "GET", classeLangueConfig.getString(ConfigCles.var_Recherche))) {
							tl(6, "&& !CollectionUtils.containsAny(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "(), ", classeLangueConfig.getString(ConfigCles.var_roleLires), ")");
							tl(6, "&& !CollectionUtils.containsAny(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "(), ", classeLangueConfig.getString(ConfigCles.var_roleLires), ")");
						}
						tl(6, ") {");
						tl(5, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(");
						tl(6, "new ServiceResponse(401, \"UNAUTHORIZED\", ");
						tl(7, "Buffer.buffer().appendString(");
						tl(8, "new JsonObject()");
						tl(9, ".put(\"errorCode\", \"401\")");
						tl(9, ".put(\"errorMessage\", \"", classeLangueConfig.getString(ConfigCles.str_roles_requis), "\" + String.join(\", \", roles))");
						tl(9, ".encodePrettily()");
						tl(8, "), MultiMap.caseInsensitiveMultiMap()");
						tl(6, ")");
						tl(5, "));");
						tl(4, "} else {");
					}
					else {
						tl(4, "{");
					}

					if(classeApiMethode.contains("POST")) {

						tl(5, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(5, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setRows(1L);");
						tl(5, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumFound(1L);");
						tl(5, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(0L);");
						tl(5, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".", classeLangueConfig.getString(ConfigCles.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
						tl(5, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ");");
						tl(5, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").toString());");
						tl(5, "JsonObject params = new JsonObject();");
						tl(5, "params.put(\"body\", ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getJsonObject());");
						tl(5, "params.put(\"path\", new JsonObject());");
						tl(5, "params.put(\"cookie\", new JsonObject());");
						tl(5, "params.put(\"header\", new JsonObject());");
						tl(5, "params.put(\"form\", new JsonObject());");
						tl(5, "JsonObject query = new JsonObject();");
						tl(5, "Boolean softCommit = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getBoolean(\"softCommit\")).orElse(null);");
						tl(5, "Integer commitWithin = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getInteger(\"commitWithin\")).orElse(null);");
						tl(5, "if(softCommit == null && commitWithin == null)");
						tl(6, "softCommit = true;");
						tl(5, "if(softCommit != null)");
						tl(6, "query.put(\"softCommit\", softCommit);");
						tl(5, "if(commitWithin != null)");
						tl(6, "query.put(\"commitWithin\", commitWithin);");
						tl(5, "params.put(\"query\", query);");
						tl(5, "JsonObject context = new JsonObject().put(\"params\", params).put(\"user\", ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getUserPrincipal());");
						tl(5, "JsonObject json = new JsonObject().put(\"context\", context);");
						tl(5, "eventBus.request(\"", siteNom, "-", classeLangueNom, "-", classeNomSimple, "\", json, new DeliveryOptions().addHeader(\"action\", \"", classeApiOperationIdMethode, "Future\")).onSuccess(a -> {");
						tl(6, "JsonObject responseMessage = (JsonObject)a.body();");
						tl(6, "JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString(\"payload\"))));");
						if(classeModele)
							tl(6, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setPk(Long.parseLong(responseBody.getString(\"pk\")));");
						tl(6, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));");
						tl(6, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_réussi), ". \"));");
						tl(5, "}).onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(6, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(5, "});");
					}
					else if(classeApiMethode.contains("PATCH")) {
//							tl(5, classeLangueConfig.getString(ConfigCles.var_requeteService), ".getParams().getJsonObject(\"query\").put(\"rows\", 100);");
						tl(5, classeLangueConfig.getString(ConfigCles.var_rechercher), classeApiClasseNomSimple, classeLangueConfig.getString(ConfigCles.var_Liste), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", (classeModele ? "false, true" : "true, false"), ", true).onSuccess(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, " -> {");
						tl(6, "try {");
						Integer tBase;
						if(activerRoleAdmin) {
							tBase = 1;
							tl(7, "List<String> roles2 = Optional.ofNullable(config.getValue(", classePartsConfigCles.nomSimple(classeLangueNom), ".", classeLangueConfig.getString(ConfigCles.var_AUTH_ROLES_ADMIN), ")).map(v -> v instanceof JsonArray ? (JsonArray)v : new JsonArray(v.toString())).orElse(new JsonArray()).getList();");
							tl(7, "if(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".getResponse().getResponse().getNumFound() > 1");
							tl(9, "&& !CollectionUtils.containsAny(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "(), roles2)");
							tl(9, "&& !CollectionUtils.containsAny(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "(), roles2)");
							tl(9, ") {");
							tl(8, "String message = String.format(\"", classeLangueConfig.getString(ConfigCles.str_roles_requis), "\" + String.join(\", \", roles2));");
							tl(8, "LOG.error(message);");
							tl(8, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", new RuntimeException(message));");
							tl(7, "} else {");
							l();
						} else {
							tBase = 0;
						}
						tl(tBase + 7, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(tBase + 7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setRows(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".getRequest().getRows());");
						tl(tBase + 7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumFound(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".getResponse().getResponse().getNumFound());");
						tl(tBase + 7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(0L);");
						tl(tBase + 7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".", classeLangueConfig.getString(ConfigCles.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
						tl(tBase + 7, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ");");
						tl(tBase + 7, "if(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ".getNumFound() == 1L)");
						tl(tBase + 8, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setOriginal(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".first());");
						if(classeModele)
							tl(tBase + 7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setPk(Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".first()).map(o2 -> o2.getPk()).orElse(null));");
						tl(tBase + 7, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").toString());");
						l();
						tl(tBase + 7, classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ", ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ").onSuccess(e -> {");
						tl(tBase + 8, classeLangueConfig.getString(ConfigCles.var_reponse), "200", classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(", classeLangueConfig.getString(ConfigCles.var_reponse), " -> {");
						tl(tBase + 9, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_réussi), ". \"));");
						tl(tBase + 9, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", classeLangueConfig.getString(ConfigCles.var_reponse), "));");
						tl(tBase + 8, "}).onFailure(ex -> {");
						tl(tBase + 9, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(tBase + 9, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(tBase + 8, "});");
						tl(tBase + 7, "}).onFailure(ex -> {");
						tl(tBase + 8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(tBase + 8, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(tBase + 7, "});");
						if(activerRoleAdmin) {
							tl(7, "}");
						}
						tl(6, "} catch(Exception ex) {");
						tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(7, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(6, "}");
						tl(5, "}).onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(6, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(5, "});");
					}
					else if(classeApiMethode.contains(classeLangueConfig.getString(ConfigCles.var_Recherche))) {
						tl(5, classeLangueConfig.getString(ConfigCles.var_rechercher), classeApiClasseNomSimple, classeLangueConfig.getString(ConfigCles.var_Liste), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", false, true, false).onSuccess(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, " -> {");
						tl(6, classeLangueConfig.getString(ConfigCles.var_reponse), "200", classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ").onSuccess(", classeLangueConfig.getString(ConfigCles.var_reponse), " -> {");
						tl(7, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", classeLangueConfig.getString(ConfigCles.var_reponse), "));");
						tl(7, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_réussi), ". \"));");
						tl(6, "}).onFailure(ex -> {");
						tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(7, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(6, "});");
						tl(5, "}).onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(6, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(5, "});");
					}
					else if(classeApiMethode.contains("GET")) {
						tl(5, classeLangueConfig.getString(ConfigCles.var_rechercher), classeApiClasseNomSimple, classeLangueConfig.getString(ConfigCles.var_Liste), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", false, true, false).onSuccess(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, " -> {");
						tl(6, classeLangueConfig.getString(ConfigCles.var_reponse), "200", classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ").onSuccess(", classeLangueConfig.getString(ConfigCles.var_reponse), " -> {");
						tl(7, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", classeLangueConfig.getString(ConfigCles.var_reponse), "));");
						tl(7, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_réussi), ". \"));");
						tl(6, "}).onFailure(ex -> {");
						tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(7, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(6, "});");
						tl(5, "}).onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(6, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(5, "});");
					}
					else if(classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTCopie))) {
						tl(5, classeLangueConfig.getString(ConfigCles.var_reponse), "200", classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(", classeLangueConfig.getString(ConfigCles.var_reponse), " -> {");
						tl(6, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", classeLangueConfig.getString(ConfigCles.var_reponse), "));");
						tl(6, classeLangueConfig.getString(ConfigCles.var_rechercher), classeApiClasseNomSimple, classeLangueConfig.getString(ConfigCles.var_Liste), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", false, true, true).onSuccess(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, " -> {");
						tl(7, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setRows(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".getRequest().getRows());");
						tl(7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumFound(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".getResponse().getResponse().getNumFound());");
						tl(7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(0L);");
						tl(7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".", classeLangueConfig.getString(ConfigCles.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
						tl(7, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ");");
						tl(7, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").toString());");
						tl(7, classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ", ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ").onSuccess(e -> {");
						tl(8, classeLangueConfig.getString(ConfigCles.var_reponse), "200", classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(f -> {");
						tl(9, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_réussi), ". \"));");
						tl(9, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", classeLangueConfig.getString(ConfigCles.var_reponse), "));");
						tl(8, "}).onFailure(ex -> {");
						tl(9, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(9, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(8, "});");
						tl(7, "}).onFailure(ex -> {");
						tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(8, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(7, "});");
						tl(6, "}).onFailure(ex -> {");
						tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(7, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(6, "});");
						tl(5, "}).onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(6, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(5, "});");
					}
					else if(classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
						tl(5, "try {");
						tl(6, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(6, "JsonArray jsonArray = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ObjetJson), "()).map(o -> o.getJsonArray(\"list\")).orElse(new JsonArray());");
						tl(6, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setRows(Long.valueOf(jsonArray.size()));");
						tl(6, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumFound(Long.valueOf(jsonArray.size()));");
						tl(6, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(0L);");
						tl(6, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".", classeLangueConfig.getString(ConfigCles.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
						tl(6, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ");");
						tl(6, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").toString());");
						tl(6, "vars", classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(d -> {");
						tl(7, classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ", ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(e -> {");
						tl(8, classeLangueConfig.getString(ConfigCles.var_reponse), "200", classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(", classeLangueConfig.getString(ConfigCles.var_reponse), " -> {");
						tl(9, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_réussi), ". \"));");
						tl(9, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", classeLangueConfig.getString(ConfigCles.var_reponse), "));");
						tl(8, "}).onFailure(ex -> {");
						tl(9, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(9, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(8, "});");
						tl(7, "}).onFailure(ex -> {");
						tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(8, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(7, "});");
						tl(6, "}).onFailure(ex -> {");
						tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(7, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(6, "});");
						tl(5, "} catch(Exception ex) {");
						tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(6, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(5, "}");
					}
					else {
						tl(5, classeLangueConfig.getString(ConfigCles.var_reponse), "200", classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(", classeLangueConfig.getString(ConfigCles.var_reponse), " -> {");
						tl(6, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(", classeLangueConfig.getString(ConfigCles.var_reponse), "));");
						tl(6, "LOG.debug(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_réussi), ". \"));");
						tl(5, "}).onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \", ex));");
						tl(6, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(5, "});");
					}

					tl(4, "}");
					tl(3, "} catch(Exception ex) {");
					tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
					tl(4, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
					tl(3, "}");
					tl(2, "}).onFailure(ex -> {");
					if(activerOpenIdConnect) {
						tl(3, "if(\"Inactive Token\".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), \"invalid_grant:\")) {");
						tl(4, "try {");
						tl(5, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(new ServiceResponse(302, \"Found\", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, \"/", classeLangueConfig.getString(ConfigCles.var_deconnexion), "?redirect_uri=\" + URLEncoder.encode(serviceRequest.getExtra().getString(\"uri\"), \"UTF-8\")))));");
						tl(4, "} catch(Exception ex2) {");
						tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \", ex2));");
						tl(5, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex2);");
						tl(4, "}");
						tl(3, "} else {");
						tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(4, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(3, "}");
					} else {
						tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(3, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
					}
					tl(2, "});");
					tl(1, "}");
					l();
	
					///////////
					// Liste //
					///////////
					if(classeApiMethode.contains("PATCH")) {
						l();
						tl(1, "public Future<Void> ", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, "(", classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ") {");
						tl(2, "Promise<Void> promise = Promise.promise();");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class);");
						tl(2, classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".getList().forEach(o -> {");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), "2 = ", classeLangueConfig.getString(ConfigCles.var_generer), classeLangueConfig.getString(ConfigCles.var_RequeteSite), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_Utilisateur), "(), ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getUserPrincipal(), ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "(), ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getJsonObject(), ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class);");
						tl(3, "o.set", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), "2);");
						tl(3, classeLangueConfig.getString(ConfigCles.var_requeteSite), "2.set", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_());");
						tl(3, "futures.add(Future.future(promise1 -> {");
						tl(4, classeApiOperationIdMethode, "Future(o, false).onSuccess(a -> {");
						tl(5, "promise1.complete();");
						tl(4, "}).onFailure(ex -> {");
						tl(5, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(5, "promise1.fail(ex);");
						tl(4, "});");
						tl(3, "}));");
						tl(2, "});");
						tl(2, "CompositeFuture.all(futures).onSuccess( a -> {");
						tl(3, "if(", classeLangueConfig.getString(ConfigCles.var_requeteApi), " != null) {");
						tl(4, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ".getNumPATCH() + ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".getResponse().getResponse().getDocs().size());");
						tl(4, "if(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ".getNumFound() == 1L)");
						tl(5, classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".first().", classeLangueConfig.getString(ConfigCles.var_requeteApi), classeNomSimple, "();");
						tl(4, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").toString());");
						tl(3, "}");
						tl(3, classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".next().onSuccess(", classeLangueConfig.getString(ConfigCles.var_suivant), " -> {");
						tl(4, "if(", classeLangueConfig.getString(ConfigCles.var_suivant), ") {");
						tl(5, classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ", ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ").onSuccess(b -> {");
						tl(6, "promise.complete();");
						tl(5, "}).onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(6, "promise.fail(ex);");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "promise.complete();");
						tl(4, "}");
						tl(3, "}).onFailure(ex -> {");
						tl(4, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(4, "promise.fail(ex);");
						tl(3, "});");
						tl(2, "}).onFailure(ex -> {");
						tl(3, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(3, "promise.fail(ex);");
						tl(2, "});");
						tl(2, "return promise.future();");
						tl(1, "}");
					}

					if(classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTCopie))) {
						l();
						tl(1, "public Future<Void> ", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, "(", classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ") {");
						tl(2, "Promise<Void> promise = Promise.promise();");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class);");
						tl(2, classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".getList().forEach(o -> {");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), "2 = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".copy();");
						tl(3, classeLangueConfig.getString(ConfigCles.var_requeteSite), "2.set", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_());");
						tl(3, "o.set", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), "2);");
						tl(3, "futures.add(");
						tl(4, classeApiOperationIdMethode, "Future(", classeApiMethode.contains("PATCH") ? "o" : (classeLangueConfig.getString(ConfigCles.var_requeteSite) + "2, JsonObject.mapFrom(o))"), ".onFailure(ex -> {");
						tl(5, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(5, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", null, ex);");
						tl(4, "})");
						tl(3, ");");
						tl(2, "});");
						tl(2, "CompositeFuture.all(futures).onSuccess(a -> {");
						tl(3, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ".getNumPATCH() + ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".size());");
						tl(3, classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".next().onSuccess(", classeLangueConfig.getString(ConfigCles.var_suivant), " -> {");
						tl(4, "if(", classeLangueConfig.getString(ConfigCles.var_suivant), ") {");
						tl(5, classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ", ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ").onSuccess(b -> {");
						tl(6, "promise.complete();");
						tl(5, "}).onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(6, "promise.fail(ex);");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "promise.complete();");
						tl(4, "}");
						tl(3, "}).onFailure(ex -> {");
						tl(4, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(4, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class), null, ex);");
						tl(3, "});");
						tl(2, "}).onFailure(ex -> {");
						tl(3, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(3, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class), null, ex);");
						tl(2, "});");
						tl(2, "return promise.future();");
						tl(1, "}");
					}
					else if(classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
						l();
						tl(1, "public Future<Void> ", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, "(", classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ") {");
						tl(2, "Promise<Void> promise = Promise.promise();");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, "JsonArray jsonArray = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ObjetJson), "()).map(o -> o.getJsonArray(\"list\")).orElse(new JsonArray());");
						tl(2, "try {");
						tl(3, "jsonArray.forEach(obj -> {");
						tl(4, "futures.add(Future.future(promise1 -> {");
						tl(5, "JsonObject params = new JsonObject();");
						tl(5, "params.put(\"body\", obj);");
						tl(5, "params.put(\"path\", new JsonObject());");
						tl(5, "params.put(\"cookie\", new JsonObject());");
						tl(5, "params.put(\"header\", new JsonObject());");
						tl(5, "params.put(\"form\", new JsonObject());");
						tl(5, "JsonObject query = new JsonObject();");
						tl(5, "Boolean softCommit = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getBoolean(\"softCommit\")).orElse(null);");
						tl(5, "Integer commitWithin = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getInteger(\"commitWithin\")).orElse(null);");
						tl(5, "if(softCommit == null && commitWithin == null)");
						tl(6, "softCommit = true;");
						tl(5, "if(softCommit != null)");
						tl(6, "query.put(\"softCommit\", softCommit);");
						tl(5, "if(commitWithin != null)");
						tl(6, "query.put(\"commitWithin\", commitWithin);");
						tl(5, "params.put(\"query\", query);");
						tl(5, "JsonObject context = new JsonObject().put(\"params\", params).put(\"user\", ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getUserPrincipal());");
						tl(5, "JsonObject json = new JsonObject().put(\"context\", context);");
						tl(5, "eventBus.request(\"", siteNom, "-", classeLangueNom, "-", classeNomSimple, "\", json, new DeliveryOptions().addHeader(\"action\", \"", classeApiOperationIdMethode, "Future\")).onSuccess(a -> {");
						tl(6, "promise1.complete();");
						tl(5, "}).onFailure(ex -> {");
						tl(6, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(6, "promise1.fail(ex);");
						tl(5, "});");
						tl(4, "}));");
						tl(3, "});");
						tl(3, "CompositeFuture.all(futures).onSuccess(a -> {");
						tl(4, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ".getNumPATCH() + 1);");
						tl(4, "promise.complete();");
						tl(3, "}).onFailure(ex -> {");
						tl(4, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(4, "promise.fail(ex);");
						tl(3, "});");
						tl(2, "} catch(Exception ex) {");
						tl(3, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_liste), classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
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
						tl(1, "public void ", classeApiOperationIdMethode, "Future(" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ServiceRequest ", classeLangueConfig.getString(ConfigCles.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ") {");
						tl(2, classeLangueConfig.getString(ConfigCles.var_utilisateur), "(", classeLangueConfig.getString(ConfigCles.var_requeteService), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class, \"", siteNom, "-", classeLangueNom, "-", classePartsUtilisateurSite.nomSimple(classeLangueNom), "\", \"post", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", \"patch", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\").onSuccess(", classeLangueConfig.getString(ConfigCles.var_requeteSite), " -> {");
						tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(3, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setRows(1L);");
						tl(3, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumFound(1L);");
						tl(3, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(0L);");
						tl(3, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".", classeLangueConfig.getString(ConfigCles.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
						tl(3, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ");");
						tl(3, "if(Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteService), ".getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getJsonArray(\"var\")).orElse(new JsonArray()).stream().filter(s -> \"", classeLangueConfig.getString(ConfigCles.var_recharger), ":false\".equals(s)).count() > 0L) {");
						tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_Requete), "Vars().put( \"", classeLangueConfig.getString(ConfigCles.var_recharger), "\", \"false\" );");
						tl(3, "}");
						tl(3, classeApiOperationIdMethode, "Future(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", false).onSuccess(o -> {");
						tl(4, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));");
						tl(3, "}).onFailure(ex -> {");
						tl(4, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
						tl(3, "});");
						tl(2, "}).onFailure(ex -> {");
						if(activerOpenIdConnect) {
							tl(3, "if(\"Inactive Token\".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), \"invalid_grant:\")) {");
							tl(4, "try {");
							tl(5, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(new ServiceResponse(302, \"Found\", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, \"/", classeLangueConfig.getString(ConfigCles.var_deconnexion), "?redirect_uri=\" + URLEncoder.encode(serviceRequest.getExtra().getString(\"uri\"), \"UTF-8\")))));");
							tl(4, "} catch(Exception ex2) {");
							tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \", ex2));");
							tl(5, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex2);");
							tl(4, "}");
							tl(3, "} else {");
							tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(4, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
							tl(3, "}");
						} else {
							tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(3, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						}
						tl(2, "});");
						tl(1, "}");
					}
					else if(StringUtils.contains(classeApiMethode, "PATCH")) {
						l();
						tl(1, "@Override");
						tl(1, "public void ", classeApiOperationIdMethode, "Future(" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ServiceRequest ", classeLangueConfig.getString(ConfigCles.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ") {");
						tl(2, classeLangueConfig.getString(ConfigCles.var_utilisateur), "(", classeLangueConfig.getString(ConfigCles.var_requeteService), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class, \"", siteNom, "-", classeLangueNom, "-", classePartsUtilisateurSite.nomSimple(classeLangueNom), "\", \"post", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", \"patch", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\").onSuccess(", classeLangueConfig.getString(ConfigCles.var_requeteSite), " -> {");
						tl(3, "try {");
						tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".setJsonObject(body);");
						tl(4, classeLangueConfig.getString(ConfigCles.var_requeteService), ".getParams().getJsonObject(\"query\").put(\"rows\", 1);");
						tl(4, classeLangueConfig.getString(ConfigCles.var_rechercher), classeApiClasseNomSimple, classeLangueConfig.getString(ConfigCles.var_Liste), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", false, true, true).onSuccess(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, " -> {");
						tl(5, "try {");
						tl(6, classeNomSimple, " o = ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".first();");
						tl(6, "if(o != null && ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".getResponse().getResponse().getNumFound() == 1) {");
						tl(7, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setRows(1L);");
						tl(7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumFound(1L);");
						tl(7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(0L);");
						tl(7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".", classeLangueConfig.getString(ConfigCles.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
						tl(7, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ");");
						tl(7, "if(Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteService), ".getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getJsonArray(\"var\")).orElse(new JsonArray()).stream().filter(s -> \"", classeLangueConfig.getString(ConfigCles.var_recharger), ":false\".equals(s)).count() > 0L) {");
						tl(8, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_Requete), "Vars().put( \"", classeLangueConfig.getString(ConfigCles.var_recharger), "\", \"false\" );");
						tl(7, "}");
						tl(7, "if(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ".getNumFound() == 1L)");
						tl(8, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setOriginal(o);");
						if(classeModele)
							tl(7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setPk(Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ".first()).map(o2 -> o2.getPk()).orElse(null));");
						tl(7, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").toString());");
						tl(7, classeApiOperationIdMethode, "Future(o, false).onSuccess(a -> {");
						tl(8, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));");
						tl(7, "}).onFailure(ex -> {");
						tl(8, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));");
						tl(6, "}");
						tl(5, "} catch(Exception ex) {");
						tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(6, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(5, "}");
						tl(4, "}).onFailure(ex -> {");
						tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(5, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(4, "});");
						tl(3, "} catch(Exception ex) {");
						tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(4, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(3, "}");
						tl(2, "}).onFailure(ex -> {");
						tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(3, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						tl(2, "});");
						tl(1, "}");
					}
					else if(StringUtils.containsAny(classeApiMethode, classeLangueConfig.getString(ConfigCles.var_PUTFusion), "PUTImport")) {
						l();
						tl(1, "@Override");
						tl(1, "public void ", classeApiOperationIdMethode, "Future(" , StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json") ? "JsonObject" : "String", " body, ServiceRequest ", classeLangueConfig.getString(ConfigCles.var_requeteService), ", Handler<AsyncResult<ServiceResponse>> ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ") {");
						tl(2, classeLangueConfig.getString(ConfigCles.var_utilisateur), "(", classeLangueConfig.getString(ConfigCles.var_requeteService), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), ".class, ", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class, \"", siteNom, "-", classeLangueNom, "-", classePartsUtilisateurSite.nomSimple(classeLangueNom), "\", \"post", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\", \"patch", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future\").onSuccess(", classeLangueConfig.getString(ConfigCles.var_requeteSite), " -> {");
						tl(3, "try {");
						if(StringUtils.equals(classeApiTypeMediaRequeteMethode, "application/json")) {
							tl(4, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
							tl(4, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setRows(1L);");
							tl(4, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumFound(1L);");
							tl(4, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(0L);");
							tl(4, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".", classeLangueConfig.getString(ConfigCles.var_initLoin), classePartsRequeteApi.nomSimple(classeLangueNom), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
							tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ");");
							if(classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
								tl(4, "body.put(\"", classeVarInheritClePrimaire, "\", body.getValue(\"", classeVarClePrimaire, "\"));");
							}
							tl(4, "if(Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteService), ".getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getJsonArray(\"var\")).orElse(new JsonArray()).stream().filter(s -> \"", classeLangueConfig.getString(ConfigCles.var_recharger), ":false\".equals(s)).count() > 0L) {");
							tl(5, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_Requete), "Vars().put( \"", classeLangueConfig.getString(ConfigCles.var_recharger), "\", \"false\" );");
							tl(4, "}");
							l();
							tl(4, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, ">();");
							tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".set", classeLangueConfig.getString(ConfigCles.var_Stocker), "(true);");
							tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".q(\"*:*\");");
							tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".setC(", classeNomSimple, ".class);");
							if(activerSupprime)
								tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".fq(\"", classeLangueConfig.getString(ConfigCles.var_supprime), "_docvalues_boolean:false\");");
							if(activerArchive)
								tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".fq(\"", classeLangueConfig.getString(ConfigCles.var_archive), "_docvalues_boolean:false\");");
							tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".fq(\"", classeApiMethode.equals("PUTImport") ? classeVarInheritClePrimaire + "_docvalues_string" : classeVarClePrimaire + "_docvalues_long", ":\" + SearchTool.escapeQueryChars(body.getString(", classeNomSimple, ".VAR_", classeModele ? classeVarClePrimaire : classeVarCleUnique, ")));");
							tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".", classeLangueConfig.getString(ConfigCles.var_promesseLoin), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(a -> {");
							tl(5, "try {");
							tl(6, "if(", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".size() >= 1) {");
							tl(7, classeNomSimple, " o = ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".getList().stream().findFirst().orElse(null);");
							tl(7, classeNomSimple, " o2 = new ", classeNomSimple, "();");
							tl(7, "o2.set", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
							tl(7, "JsonObject body2 = new JsonObject();");
							tl(7, "for(String f : body.fieldNames()) {");
							tl(8, "Object bodyVal = body.getValue(f);");
							tl(8, "if(bodyVal instanceof JsonArray) {");
							tl(9, "JsonArray bodyVals = (JsonArray)bodyVal;");
							tl(9, "Collection<?> vals = (Collection<?>)o.", classeLangueConfig.getString(ConfigCles.var_obtenir), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(f);");
							tl(9, "if(bodyVals.size() == vals.size()) {");
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
							tl(10, "if(!match) {");
							tl(11, "body2.put(\"set\" + StringUtils.capitalize(f), bodyVal);");
							tl(10, "}");
							tl(9, "} else {");
							tl(10, "body2.put(\"set\" + StringUtils.capitalize(f), bodyVal);");
							tl(9, "}");
							tl(8, "} else {");
							tl(9, "o2.", classeLangueConfig.getString(ConfigCles.var_definir), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(f, bodyVal);");
							tl(9, "o2.", classeLangueConfig.getString(ConfigCles.var_attribuer), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(f, bodyVal);");
							tl(9, "if(!StringUtils.containsAny(f, \"", classeVarClePrimaire, "\", \"", classeLangueConfig.getString(ConfigCles.var_cree), "\", \"set", classeLangueConfig.getString(ConfigCles.var_Cree), "\") && !Objects.equals(o.", classeLangueConfig.getString(ConfigCles.var_obtenir), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(f), o2.", classeLangueConfig.getString(ConfigCles.var_obtenir), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(f)))");
							tl(10, "body2.put(\"set\" + StringUtils.capitalize(f), bodyVal);");
							tl(8, "}");
							tl(7, "}");
							tl(7, "for(String f : Optional.ofNullable(o.get", classeLangueConfig.getString(ConfigCles.var_Sauvegardes), "()).orElse(new ArrayList<>())) {");
							tl(8, "if(!body.fieldNames().contains(f)) {");
							tl(9, "if(!StringUtils.containsAny(f, \"", classeVarClePrimaire, "\", \"", classeLangueConfig.getString(ConfigCles.var_cree), "\", \"set", classeLangueConfig.getString(ConfigCles.var_Cree), "\") && !Objects.equals(o.", classeLangueConfig.getString(ConfigCles.var_obtenir), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(f), o2.", classeLangueConfig.getString(ConfigCles.var_obtenir), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(f)))");
							tl(10, "body2.putNull(\"set\" + StringUtils.capitalize(f));");
							tl(8, "}");
							tl(7, "}");
							tl(7, "if(body2.size() > 0) {");
							tl(8, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ObjetJson), "(body2);");
							tl(8, "patch", classeNomSimple, "Future(", (classeModele ? "o" : "o2"), ", ", classeApiMethode.equals("PUTImport"), ").onSuccess(b -> {");
							tl(9, "LOG.info(\"Import ", classeNomSimple, " {} ", classeLangueConfig.getString(ConfigCles.str_a_réussi), ", ", classeLangueConfig.getString(ConfigCles.var_modifie), " ", classeNomSimple, ". \", body.getValue(", classeNomSimple, ".VAR_", classeModele ? classeVarClePrimaire : classeVarCleUnique, "));");
							tl(9, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture());");
							tl(8, "}).onFailure(ex -> {");
							tl(9, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(9, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
							tl(8, "});");
							tl(7, "} else {");
							tl(8, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture());");
							tl(7, "}");
							tl(6, "} else {");
							tl(7, "post", classeNomSimple, "Future(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeApiMethode.equals("PUTImport"), ").onSuccess(b -> {");
							tl(8, "LOG.info(\"Import ", classeNomSimple, " {} ", classeLangueConfig.getString(ConfigCles.str_a_réussi), ", ", classeLangueConfig.getString(ConfigCles.str_créé_nouveau), " ", classeNomSimple, ". \", body.getValue(", classeNomSimple, ".VAR_", classeModele ? classeVarClePrimaire : classeVarCleUnique, "));");
							tl(8, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture());");
							tl(7, "}).onFailure(ex -> {");
							tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(8, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
							tl(7, "});");
							tl(6, "}");
							tl(5, "} catch(Exception ex) {");
							tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(6, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
							tl(5, "}");
							tl(4, "}).onFailure(ex -> {");
							tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(5, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
							tl(4, "});");
						} else {
							tl(4, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(ServiceResponse.completedWithPlainText(Buffer.buffer())));");
						}
						tl(3, "} catch(Exception ex) {");
						tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(4, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.failedFuture(ex));");
						tl(3, "}");
						tl(2, "}).onFailure(ex -> {");
						if(activerOpenIdConnect) {
							tl(3, "if(\"Inactive Token\".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), \"invalid_grant:\")) {");
							tl(4, "try {");
							tl(5, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(new ServiceResponse(302, \"Found\", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, \"/", classeLangueConfig.getString(ConfigCles.var_deconnexion), "?redirect_uri=\" + URLEncoder.encode(serviceRequest.getExtra().getString(\"uri\"), \"UTF-8\")))));");
							tl(4, "} catch(Exception ex2) {");
							tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \", ex2));");
							tl(5, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex2);");
							tl(4, "}");
							tl(3, "} else {");
							tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(4, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
							tl(3, "}");
						} else {
							tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(3, classeLangueConfig.getString(ConfigCles.var_erreur), "(null, ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", ex);");
						}
						tl(2, "});");
						tl(1, "}");
					}

					if(StringUtils.containsAny(classeApiMethode, "POST", classeLangueConfig.getString(ConfigCles.var_PUTCopie), "PATCH")) {
						l();
						t(1, "public Future<", classeApiClasseNomSimple, "> ", classeApiOperationIdMethode, "Future(");
						if(StringUtils.contains(classeApiMethode, "POST"))
							s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", Boolean inheritPk");
						else if(StringUtils.contains(classeApiMethode, "PUT"))
							s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", JsonObject jsonObject");
						else if(StringUtils.contains(classeApiMethode, "PATCH"))
							s(classeNomSimple, " o, Boolean inheritPk");
						else
							s(classeNomSimple, " ", uncapitalizeClasseNomSimple);
						l(") {");
						if(StringUtils.contains(classeApiMethode, "PATCH"))
							tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = o.get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
						else if(!StringUtils.containsAny(classeApiMethode, "POST", "PUT"))
							tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = ", uncapitalizeClasseNomSimple, ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
						tl(2, "Promise<", classeApiClasseNomSimple, "> promise = Promise.promise();");
						l();
						tl(2, "try {");
	
						if(classeApiMethode.contains("POST")) {
							if(classeModele) {
								tl(3, "pgPool.withTransaction(", classeLangueConfig.getString(ConfigCles.var_connexionSql), " -> {");
								tl(4, "Promise<", classeApiClasseNomSimple, "> promise1 = Promise.promise();");
								tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "(", classeLangueConfig.getString(ConfigCles.var_connexionSql), ");");
								tl(4, classeLangueConfig.getString(ConfigCles.var_creer), classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(", uncapitalizeClasseNomSimple, " -> {");
								tl(5, "sql", classeApiMethode, classeNomSimple, "(", uncapitalizeClasseNomSimple, ", inheritPk).onSuccess(b -> {");
								tl(6, classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(c -> {");
								tl(7, classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(d -> {");
								tl(8, classeLangueConfig.getString(ConfigCles.var_indexer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(e -> {");
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
								tl(3, "}).onSuccess(a -> {");
								tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "(null);");
								tl(3, "}).onFailure(ex -> {");
								tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "(null);");
								tl(4, "promise.fail(ex);");
								tl(3, "}).compose(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, "Promise<", classeApiClasseNomSimple, "> promise2 = Promise.promise();");
								tl(4, classeLangueConfig.getString(ConfigCles.var_recharger), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(a -> {");
								tl(5, "try {");
								tl(6, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_();");
								tl(6, "if(", classeLangueConfig.getString(ConfigCles.var_requeteApi), " != null) {");
								tl(7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ".getNumPATCH() + 1);");
								tl(7, uncapitalizeClasseNomSimple, ".", classeLangueConfig.getString(ConfigCles.var_requeteApi), classeNomSimple, "();");
								tl(7, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").toString());");
								tl(6, "}");
								tl(6, "promise2.complete(", uncapitalizeClasseNomSimple, ");");
								tl(5, "} catch(Exception ex) {");
								tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
								tl(6, "promise.fail(ex);");
								tl(5, "}");
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
								tl(3, classeLangueConfig.getString(ConfigCles.var_creer), classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", false).onSuccess(c -> {");
								tl(5, classeLangueConfig.getString(ConfigCles.var_indexer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(e -> {");
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
						}
						else if(classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTCopie))) {
							l();
							tl(3, "jsonObject.put(\"", classeLangueConfig.getString(ConfigCles.var_sauvegardes), "\", Optional.ofNullable(jsonObject.getJsonArray(\"", classeLangueConfig.getString(ConfigCles.var_sauvegardes), "\")).orElse(new JsonArray()));");
							tl(3, "JsonObject jsonPatch = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ObjetJson), "()).map(o -> o.getJsonObject(\"patch\")).orElse(new JsonObject());");
							tl(3, "jsonPatch.stream().forEach(o -> {");
							tl(4, "if(o.getValue() == null)");
							tl(5, "jsonObject.remove(o.getKey());");
							tl(4, "else");
							tl(5, "jsonObject.put(o.getKey(), o.getValue());");
							tl(4, "if(!jsonObject.getJsonArray(\"", classeLangueConfig.getString(ConfigCles.var_sauvegardes), "\").contains(o.getKey()))");
							tl(5, "jsonObject.getJsonArray(\"", classeLangueConfig.getString(ConfigCles.var_sauvegardes), "\").add(o.getKey());");
							tl(3, "});");
							l();
							tl(3, "pgPool.withTransaction(", classeLangueConfig.getString(ConfigCles.var_connexionSql), " -> {");
							tl(4, "Promise<", classeApiClasseNomSimple, "> promise1 = Promise.promise();");
							tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "(", classeLangueConfig.getString(ConfigCles.var_connexionSql), ");");

							tl(4, classeLangueConfig.getString(ConfigCles.var_creer), classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(", uncapitalizeClasseNomSimple, " -> {");
							tl(5, "sql", classeApiMethode, classeNomSimple, "(", uncapitalizeClasseNomSimple, ", jsonObject).onSuccess(b -> {");
							tl(6, classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(c -> {");
							tl(7, classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(d -> {");
							tl(8, classeLangueConfig.getString(ConfigCles.var_indexer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(e -> {");
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
								tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "(null);");
								tl(3, "}).onFailure(ex -> {");
								tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "(null);");
								tl(4, "promise.fail(ex);");
								tl(3, "}).compose(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, "Promise<", classeApiClasseNomSimple, "> promise2 = Promise.promise();");
								tl(4, classeLangueConfig.getString(ConfigCles.var_recharger), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(a -> {");
								tl(5, "try {");
								tl(6, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_();");
								tl(6, "if(", classeLangueConfig.getString(ConfigCles.var_requeteApi), " != null) {");
								tl(7, classeLangueConfig.getString(ConfigCles.var_requeteApi), ".setNumPATCH(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ".getNumPATCH() + 1);");
								tl(7, uncapitalizeClasseNomSimple, ".", classeLangueConfig.getString(ConfigCles.var_requeteApi), classeNomSimple, "();");
								tl(7, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").toString());");
								tl(6, "}");
								tl(6, "promise2.complete(", uncapitalizeClasseNomSimple, ");");
								tl(5, "} catch(Exception ex) {");
								tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
								tl(6, "promise.fail(ex);");
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
								tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "(null);");
								tl(4, "promise.complete(o);");
								tl(3, "}).onFailure(ex -> {");
								tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "(null);");
								tl(4, "promise.fail(ex);");
							}
							tl(3, "});");
						}
						else if(classeApiMethode.contains("PATCH")) {
							tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_();");
							if(classeModele) {
								tl(3, "Promise<", classeApiClasseNomSimple, "> promise1 = Promise.promise();");
								tl(3, "pgPool.withTransaction(", classeLangueConfig.getString(ConfigCles.var_connexionSql), " -> {");
								tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "(", classeLangueConfig.getString(ConfigCles.var_connexionSql), ");");
								tl(4, "sql", classeApiMethode, classeNomSimple, "(o, inheritPk).onSuccess(", uncapitalizeClasseNomSimple, " -> {");
								tl(5, classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(c -> {");
								tl(6, classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(d -> {");
								tl(7, classeLangueConfig.getString(ConfigCles.var_indexer), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(e -> {");
								tl(8, "promise1.complete(", uncapitalizeClasseNomSimple, ");");
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
								tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "(null);");
								tl(3, "}).onFailure(ex -> {");
								tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "(null);");
								tl(4, "promise.fail(ex);");
								tl(3, "}).compose(", uncapitalizeClasseNomSimple, " -> {");
								tl(4, "Promise<", classeApiClasseNomSimple, "> promise2 = Promise.promise();");
								tl(4, classeLangueConfig.getString(ConfigCles.var_recharger), classeNomSimple, "(", uncapitalizeClasseNomSimple, ").onSuccess(a -> {");
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
								tl(3, classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, "(o, true).onSuccess(c -> {");
								tl(4, classeLangueConfig.getString(ConfigCles.var_indexer), classeNomSimple, "(o).onSuccess(e -> {");
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
						tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
						tl(3, "promise.fail(ex);");
						tl(2, "}");
						tl(2, "return promise.future();");
						tl(1, "}");
					}

					/////////
					// SQL //
					/////////
					if(classeModele) {
						if(classeApiMethode.contains("PATCH")) {
							l();
							tl(1, "public Future<", classeApiClasseNomSimple, "> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, Boolean inheritPk) {");
							tl(2, "Promise<", classeApiClasseNomSimple, "> promise = Promise.promise();");
							tl(2, "try {");
							tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = o.get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
							tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_();");
							tl(3, "List<Long> pks = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").map(r -> r.getPks()).orElse(new ArrayList<>());");
							tl(3, "List<String> classes = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
							tl(3, "SqlConnection ", classeLangueConfig.getString(ConfigCles.var_connexionSql), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "();");
							tl(3, "Integer num = 1;");
							tl(3, "StringBuilder bSql = new StringBuilder(\"UPDATE ", classeNomSimple, " SET \");");
							tl(3, "List<Object> bParams = new ArrayList<Object>();");
							tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
							tl(3, "JsonObject jsonObject = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ObjetJson), "();");
							tl(3, "Set<String> ", classeLangueConfig.getString(ConfigCles.var_methodeNoms), " = jsonObject.fieldNames();");
							tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
							tl(3, "o2.set", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
							tl(3, "List<Future> futures1 = new ArrayList<>();");
							tl(3, "List<Future> futures2 = new ArrayList<>();");
							l();
							tl(3, "for(String ", classeLangueConfig.getString(ConfigCles.var_entite), "Var : ", classeLangueConfig.getString(ConfigCles.var_methodeNoms), ") {");
							tl(4, "switch(", classeLangueConfig.getString(ConfigCles.var_entite), "Var) {");
							s(wApiGenererPatch.toString());
							tl(4, "}");
							tl(3, "}");
							tl(3, "bSql.append(\" WHERE ", classeVarClePrimaire, "=$\" + num);");
							tl(3, "if(bParams.size() > 0) {");
							tl(4, "bParams.add(", classeVarClePrimaire, ");");
							tl(4, "num++;");
							tl(4, "futures2.add(0, Future.future(a -> {");
							tl(5, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(bSql.toString())");
							tl(7, ".execute(Tuple.tuple(bParams)");
							tl(7, ").onSuccess(b -> {");
							tl(6, "a.handle(Future.succeededFuture());");
							tl(5, "}).onFailure(ex -> {");
							tl(6, "RuntimeException ex2 = new RuntimeException(\"", classeLangueConfig.getString(ConfigCles.var_valeur), " ", classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), "\", ex);");
							tl(6, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex2);");
							tl(6, "a.handle(Future.failedFuture(ex2));");
							tl(5, "});");
							tl(4, "}));");
							tl(3, "}");
							tl(3, "CompositeFuture.all(futures1).onSuccess(a -> {");
							tl(4, "CompositeFuture.all(futures2).onSuccess(b -> {");
							tl(5, classeNomSimple, " o3 = new ", classeNomSimple, "();");
							tl(5, "o3.set", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(o.get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_());");
							tl(5, "o3.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
							tl(5, "promise.complete(o3);");
							tl(4, "}).onFailure(ex -> {");
							tl(5, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(5, "promise.fail(ex);");
							tl(4, "});");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(4, "promise.fail(ex);");
							tl(3, "});");
							tl(2, "} catch(Exception ex) {");
							tl(3, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(3, "promise.fail(ex);");
							tl(2, "}");
							tl(2, "return promise.future();");
							tl(1, "}");
						}
						if(classeApiMethode.contains("POST")) {
							l();
							tl(1, "public Future<Void> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, Boolean inheritPk) {");
							tl(2, "Promise<Void> promise = Promise.promise();");
							tl(2, "try {");
							tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = o.get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
							tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_();");
							tl(3, "List<Long> pks = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").map(r -> r.getPks()).orElse(new ArrayList<>());");
							tl(3, "List<String> classes = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
							tl(3, "SqlConnection ", classeLangueConfig.getString(ConfigCles.var_connexionSql), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "();");
							tl(3, "Integer num = 1;");
							tl(3, "StringBuilder bSql = new StringBuilder(\"UPDATE ", classeNomSimple, " SET \");");
							tl(3, "List<Object> bParams = new ArrayList<Object>();");
							tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
							tl(3, "JsonObject jsonObject = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ObjetJson), "();");
							tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
							tl(3, "o2.set", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
							tl(3, "List<Future> futures1 = new ArrayList<>();");
							tl(3, "List<Future> futures2 = new ArrayList<>();");
							if(activerSessionId) {
								l();
								tl(3, "if(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_SessionId), "() != null) {");
								tl(4, "if(bParams.size() > 0) {");
								tl(5, "bSql.append(\", \");");
								tl(4, "}");
								tl(4, "bSql.append(\"", classeLangueConfig.getString(ConfigCles.var_sessionId), "=$\" + num);");
								tl(4, "num++;");
								tl(4, "bParams.add(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_SessionId), "());");
								tl(3, "}");
							}
							if(activerUtilisateurCle) {
								tl(3, "if(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurCle), "() != null) {");
								tl(4, "if(bParams.size() > 0) {");
								tl(5, "bSql.append(\", \");");
								tl(4, "}");
								tl(4, "bSql.append(\"", classeLangueConfig.getString(ConfigCles.var_utilisateurCle), "=$\" + num);");
								tl(4, "num++;");
								tl(4, "bParams.add(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurCle), "());");
								tl(3, "}");
							}
							if(classeNomCanonique.equals(classePartsUtilisateurSite.nomCanonique(classeLangueNom))) {
								tl(3, "if(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurId), "() != null) {");
								tl(4, "if(bParams.size() > 0) {");
								tl(5, "bSql.append(\", \");");
								tl(4, "}");
								tl(4, "bSql.append(\"", classeLangueConfig.getString(ConfigCles.var_utilisateurId), "=$\" + num);");
								tl(4, "num++;");
								tl(4, "bParams.add(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurId), "());");
								tl(3, "}");
							}
							l();
							tl(3, "if(jsonObject != null) {");
							tl(4, "Set<String> ", classeLangueConfig.getString(ConfigCles.var_entite), "Vars = jsonObject.fieldNames();");
							tl(4, "for(String ", classeLangueConfig.getString(ConfigCles.var_entite), "Var : ", classeLangueConfig.getString(ConfigCles.var_entite), "Vars) {");
							tl(5, "switch(", classeLangueConfig.getString(ConfigCles.var_entite), "Var) {");
							s(wApiGenererPost.toString());
							tl(5, "}");
							tl(4, "}");
							tl(3, "}");
							tl(3, "bSql.append(\" WHERE ", classeVarClePrimaire, "=$\" + num);");
							tl(3, "if(bParams.size() > 0) {");
							tl(3, "bParams.add(", classeVarClePrimaire, ");");
							tl(3, "num++;");
							tl(4, "futures2.add(0, Future.future(a -> {");
							tl(5, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(bSql.toString())");
							tl(7, ".execute(Tuple.tuple(bParams)");
							tl(7, ").onSuccess(b -> {");
							tl(6, "a.handle(Future.succeededFuture());");
							tl(5, "}).onFailure(ex -> {");
							tl(6, "RuntimeException ex2 = new RuntimeException(\"", classeLangueConfig.getString(ConfigCles.var_valeur), " ", classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), "\", ex);");
							tl(6, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex2);");
							tl(6, "a.handle(Future.failedFuture(ex2));");
							tl(5, "});");
							tl(4, "}));");
							tl(3, "}");
							tl(3, "CompositeFuture.all(futures1).onSuccess(a -> {");
							tl(4, "CompositeFuture.all(futures2).onSuccess(b -> {");
							tl(5, "promise.complete();");
							tl(4, "}).onFailure(ex -> {");
							tl(5, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(5, "promise.fail(ex);");
							tl(4, "});");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(4, "promise.fail(ex);");
							tl(3, "});");
							tl(2, "} catch(Exception ex) {");
							tl(3, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(3, "promise.fail(ex);");
							tl(2, "}");
							tl(2, "return promise.future();");
							tl(1, "}");
						}
						if(classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTCopie))) {
							l();
							tl(1, "public Future<Void> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, JsonObject jsonObject) {");
							tl(2, "Promise<Void> promise = Promise.promise();");
							tl(2, "try {");
							tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = o.get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
							tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_();");
							tl(3, "List<Long> pks = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").map(r -> r.getPks()).orElse(new ArrayList<>());");
							tl(3, "List<String> classes = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
							tl(3, "SqlConnection ", classeLangueConfig.getString(ConfigCles.var_connexionSql), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "();");
							tl(3, "Integer num = 1;");
							tl(3, "StringBuilder bSql = new StringBuilder(\"UPDATE ", classeNomSimple, " SET \");");
							tl(3, "List<Object> bParams = new ArrayList<Object>();");
							tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
							tl(3, "o2.set", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
							tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
							tl(3, "List<Future> futures = new ArrayList<>();");
							l();
							tl(3, "if(jsonObject != null) {");
							tl(4, "JsonArray ", classeLangueConfig.getString(ConfigCles.var_entite), "Vars = jsonObject.getJsonArray(\"", classeVarSauvegardes, "\");");
							tl(4, "for(Integer i = 0; i < ", classeLangueConfig.getString(ConfigCles.var_entite), "Vars.size(); i++) {");
							tl(5, "String ", classeLangueConfig.getString(ConfigCles.var_entite), "Var = ", classeLangueConfig.getString(ConfigCles.var_entite), "Vars.getString(i);");
							tl(5, "switch(", classeLangueConfig.getString(ConfigCles.var_entite), "Var) {");
							if(classeApiMethode.equals("PUTImport"))
								s(wApiGenererPutImport.toString());
							else if(classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTFusion)))
								s(wApiGenererPutFusion.toString());
							else if(classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTCopie)))
								s(wApiGenererPutCopie.toString());
							tl(5, "}");
							tl(4, "}");
							tl(3, "}");
							tl(3, "bSql.append(\" WHERE ", classeVarClePrimaire, "=$\" + num);");
							tl(3, "if(bParams.size() > 0) {");
							tl(3, "bParams.add(", classeVarClePrimaire, ");");
							tl(3, "num++;");
							tl(4, "futures.add(Future.future(a -> {");
							tl(5, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(bSql.toString())");
							tl(7, ".execute(Tuple.tuple(bParams)");
							tl(7, ").onSuccess(b -> {");
							tl(6, "a.handle(Future.succeededFuture());");
							tl(5, "}).onFailure(ex -> {");
							tl(6, "RuntimeException ex2 = new RuntimeException(\"", classeLangueConfig.getString(ConfigCles.var_valeur), " ", classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), "\", ex);");
							tl(6, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex2);");
							tl(6, "a.handle(Future.failedFuture(ex2));");
							tl(5, "});");
							tl(4, "}));");
							tl(3, "}");
							tl(3, "CompositeFuture.all(futures).onSuccess(a -> {");
							tl(4, "promise.complete();");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
							tl(4, "promise.fail(ex);");
							tl(3, "});");
							tl(2, "} catch(Exception ex) {");
							tl(3, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
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
						tl(1, "public void ", classeApiOperationIdMethode, classeLangueConfig.getString(ConfigCles.var_Page), "Init(", classeNomSimple, classeLangueConfig.getString(ConfigCles.var_Page), " page, ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_liste), classeNomSimple, ") {");
						tl(1, "}");
					}
//
//					/////////////
//					// Reponse //
//					/////////////
//					if (!classeApiMethode.equals("PUTImport") && !classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTFusion))){
//						String var;
//						String type;
//						if(classeApiMethode.contains("POST")) {
//							type = classeNomSimple;
//							var = uncapitalizeClasseNomSimple;
//						}
//						else if(classeApiMethode.contains("PATCH") || classeApiMethode.equals("PUTImport") || classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTFusion)) || classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTCopie))) {
//							type = classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom);
//							var = classeLangueConfig.getString(ConfigCles.var_requeteSite);
//						}
//						else if(classeApiMethode.contains("GET") || classeApiMethode.contains(classeLangueConfig.getString(ConfigCles.var_Recherche))) {
//							type = classePartsListeRecherche.nomSimple(classeLangueNom) + "<" + classeNomSimple + ">";
//							var = classeLangueConfig.getString(ConfigCles.var_liste) + classeNomSimple;
//						}
//						else {
//							type = classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom);
//							var = classeLangueConfig.getString(ConfigCles.var_requeteSite);
//						}
//
//						t(1, "public void ", classeApiOperationIdMethode, classeLangueConfig.getString(ConfigCles.var_Reponse), "(");
//						s(type, " ", var);
//						l(", Handler<AsyncResult<ServiceResponse>> ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ") {");
//						if(classeApiMethode.contains("POST") || classeApiMethode.contains("GET") || classeApiMethode.contains(classeLangueConfig.getString(ConfigCles.var_Recherche)))
//							tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = ", var, ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
//
//						tl(2, "try {");
//						if("text/html".equals(classeApiTypeMedia200Methode)) {
//							tl(3, "Buffer buffer = Buffer.buffer();");
//							if(classePartsToutEcrivain == null)
//								throw new RuntimeException(String.format("%s %s %s %s %s. ", classeLangueConfig.getString(ConfigCles.var_classe), classeLangueConfig.getString(ConfigCles.var_ToutEcrivain), classeLangueConfig.getString(ConfigCles.var_manquante), classeLangueConfig.getString(ConfigCles.var_dans), cheminSrcMainJava));
//							tl(3, classePartsToutEcrivain.nomSimple(classeLangueNom), " w = ", classePartsToutEcrivain.nomSimple(classeLangueNom), ".", classeLangueConfig.getString(ConfigCles.var_creer), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", buffer);");
//							tl(3, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".setW(w);");
//						}
//
//						tl(3, classeLangueConfig.getString(ConfigCles.var_reponse), "200", classeApiMethode, classeNomSimple, "(", var, ", a -> {");
//						tl(4, "if(a.succeeded()) {");
//						tl(5, classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ".handle(Future.succeededFuture(a.result()));");
//						tl(4, "} else {");
//						tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, classeLangueConfig.getString(ConfigCles.var_Reponse), " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \", a.cause()));");
//						tl(5, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_gestionnaireEvenements), ", a);");
//						tl(4, "}");
//						tl(3, "});");
//						tl(2, "} catch(Exception ex) {");
//						tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, classeLangueConfig.getString(ConfigCles.var_Reponse), " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
//						tl(3, classeLangueConfig.getString(ConfigCles.var_erreur), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", null, ex);");
//						tl(2, "}");
//						tl(1, "}");
//					}

					/////////////////
					// Reponse 200 //
					/////////////////
					if(classePageNomCanoniqueMethode != null) {
						l();
						tl(1, "public String ", classeLangueConfig.getString(ConfigCles.var_template), classeApiMethode, classeNomSimple, "() {");
						tl(2, "return Optional.ofNullable(config.getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".TEMPLATE_PATH)).orElse(\"templates\") + \"/", classeLangueNom, "/", classeNomSimple, classeLangueConfig.getString(ConfigCles.var_Page), "\";");
						t(1, "}");
					}
					l();
					t(1, "public Future<ServiceResponse> ", classeLangueConfig.getString(ConfigCles.var_reponse), "200", classeApiMethode, classeNomSimple, "(");
	
					if(classeApiMethode.contains("POST"))
						s(classeNomSimple, " o");
					else if(classeApiMethode.equals("PUTImport") || classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTFusion)) || classeApiMethode.contains("DELETE"))
						s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite));
					else if(classeApiMethode.contains("PUT"))
						s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite));
					else if(classeApiMethode.contains("PATCH"))
						s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite));
					else if(classeApiMethode.contains("GET") || classeApiMethode.contains(classeLangueConfig.getString(ConfigCles.var_Recherche)))
						s(classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple);
					else
						s(classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite));
	
					l(") {");
					tl(2, "Promise<ServiceResponse> promise = Promise.promise();");
					tl(2, "try {");
	
					if(classeApiMethode.contains("POST")) {
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = o.get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
					}
					else if(classeApiMethode.equals("PUTImport") || classeApiMethode.equals(classeLangueConfig.getString(ConfigCles.var_PUTFusion))) {
					}
					else if(classeApiMethode.contains("PATCH") || classeApiMethode.contains("PUT")) {
					}
					else if(classeApiMethode.contains(classeLangueConfig.getString(ConfigCles.var_Recherche)) || classeApiMethode.contains("GET")) {
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = ", classeLangueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class);");
					}
					else {
					}
	
	
					if(classeApiMethode.contains(classeLangueConfig.getString(ConfigCles.var_Recherche))) {
						if(classePageNomCanoniqueMethode != null) {
							if(classePartsToutEcrivain == null)
								throw new RuntimeException(String.format("%s %s %s %s %s. ", classeLangueConfig.getString(ConfigCles.var_classe), classeLangueConfig.getString(ConfigCles.var_ToutEcrivain), classeLangueConfig.getString(ConfigCles.var_manquante), classeLangueConfig.getString(ConfigCles.var_dans), cheminSrcMainJava));
							tl(3, classeNomSimple, classeLangueConfig.getString(ConfigCles.var_Page), " page = new ", classeNomSimple, classeLangueConfig.getString(ConfigCles.var_Page), "();");
							tl(3, "MultiMap ", classeLangueConfig.getString(ConfigCles.var_requeteEnTetes), " = MultiMap.caseInsensitiveMultiMap();");
							tl(3, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteEnTetes), "(", classeLangueConfig.getString(ConfigCles.var_requeteEnTetes), ");");
							l();
							if(classeModele) {
								tl(3, "if(", classeLangueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".size() == 1)");
								tl(4, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_Requete), StringUtils.capitalize(classeVarClePrimaire), "(", classeLangueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".get(0).get", StringUtils.capitalize(classeVarClePrimaire), "()", ");");
							}
							if(!classePageSimple)
								tl(3, "page.set", classeLangueConfig.getString(ConfigCles.var_ListeRecherche), classeApiClasseNomSimple, "_(", classeLangueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ");");
							tl(3, "page.set", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
							tl(3, "page.", classeLangueConfig.getString(ConfigCles.var_promesseLoin), classeNomSimple, classeLangueConfig.getString(ConfigCles.var_Page), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(a -> {");
							tl(4, "JsonObject json = JsonObject.mapFrom(page);");
							tl(4, "json.put(", classePartsConfigCles.nomSimple(langueNom), ".STATIC_BASE_URL, config.getString(", classePartsConfigCles.nomSimple(langueNom), ".STATIC_BASE_URL));");
							tl(4, "json.put(", classePartsConfigCles.nomSimple(langueNom), ".GITHUB_ORG, config.getString(", classePartsConfigCles.nomSimple(langueNom), ".GITHUB_ORG));");
							tl(4, "json.put(", classePartsConfigCles.nomSimple(langueNom), ".SITE_NAME, config.getString(", classePartsConfigCles.nomSimple(langueNom), ".SITE_NAME));");
							tl(4, "json.put(", classePartsConfigCles.nomSimple(langueNom), ".SITE_DISPLAY_NAME, config.getString(", classePartsConfigCles.nomSimple(langueNom), ".SITE_DISPLAY_NAME));");
							tl(4, "json.put(", classePartsConfigCles.nomSimple(langueNom), ".PROJECT_POWERED_BY_URL, config.getString(", classePartsConfigCles.nomSimple(langueNom), ".PROJECT_POWERED_BY_URL));");
							tl(4, "json.put(", classePartsConfigCles.nomSimple(langueNom), ".PROJECT_POWERED_BY_NAME, config.getString(", classePartsConfigCles.nomSimple(langueNom), ".PROJECT_POWERED_BY_NAME));");
							tl(4, "json.put(", classePartsConfigCles.nomSimple(langueNom), ".PROJECT_POWERED_BY_IMAGE_URI, config.getString(", classePartsConfigCles.nomSimple(langueNom), ".PROJECT_POWERED_BY_IMAGE_URI));");
							tl(4, "templateEngine.render(json, ", classeLangueConfig.getString(ConfigCles.var_template), classeApiMethode, classeNomSimple, "()).onSuccess(buffer -> {");
						}
						else {
							tl(3, "List<String> fls = ", classeLangueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getRequest().getFields();");
							tl(3, "JsonObject json = new JsonObject();");
							tl(3, "JsonArray l = new JsonArray();");
							tl(3, classeLangueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getList().stream().forEach(o -> {");
							tl(4, "JsonObject json2 = JsonObject.mapFrom(o);");
							tl(4, "if(fls.size() > 0) {");
							tl(5, "Set<String> fieldNames = new HashSet<String>();");
							tl(5, "for(String fieldName : json2.fieldNames()) {");
							tl(6, "String v = ", classeNomSimple, ".var", classeLangueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(fieldName);");
							tl(6, "if(v != null)");
							tl(7, "fieldNames.add(", classeNomSimple, ".var", classeLangueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(fieldName));");
							tl(5, "}");
							tl(5, "if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals(\"", classeLangueConfig.getString(ConfigCles.var_sauvegardes), "_docvalues_strings\")) {");
							tl(6, "fieldNames.removeAll(Optional.ofNullable(json2.getJsonArray(\"", classeLangueConfig.getString(ConfigCles.var_sauvegardes), "_docvalues_strings\")).orElse(new JsonArray()).stream().map(s -> s.toString()).collect(Collectors.toList()));");
							tl(6, "fieldNames.remove(\"", classeVarClePrimaire, "_docvalues_long\");");
							tl(6, "fieldNames.remove(\"", classeLangueConfig.getString(ConfigCles.var_cree), "_docvalues_date\");");
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
							tl(3, "json.put(", q(classeLangueConfig.getString(ConfigCles.var_liste)), ", l);");
							tl(3, "response200Search(", classeLangueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getRequest(), ", classeLangueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getResponse(), json);");
						}
					}
					else if(classeApiMethode.contains("GET")) {
						if(classePageNomCanoniqueMethode != null) {
							tl(3, classeNomSimple, classeLangueConfig.getString(ConfigCles.var_Page), " page = new ", classeNomSimple, classeLangueConfig.getString(ConfigCles.var_Page), "();");
//							tl(3, "page.setPageUrl(\"", siteUrlBase, classeApiUri, "\");");
							tl(3, "SolrDocument page", classeLangueConfig.getString(ConfigCles.var_DocumentSolr), " = new SolrDocument();");
							tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = ", classeLangueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class);");
							tl(3, "MultiMap ", classeLangueConfig.getString(ConfigCles.var_requeteEnTetes), " = MultiMap.caseInsensitiveMultiMap();");
							tl(3, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteEnTetes), "(", classeLangueConfig.getString(ConfigCles.var_requeteEnTetes), ");");
							l();
							tl(3, "page", classeLangueConfig.getString(ConfigCles.var_DocumentSolr), ".setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
							tl(3, "page.setPage", classeLangueConfig.getString(ConfigCles.var_DocumentSolr), "(page", classeLangueConfig.getString(ConfigCles.var_DocumentSolr), ");");
							tl(3, "page.setW(w);");
							tl(3, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".setW(w);");
							tl(3, "page.", classeLangueConfig.getString(ConfigCles.var_promesseLoin), classePageNomSimpleMethode, "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(a -> {");
							tl(4, "page.html();");
						}
						else {
							tl(3, "JsonObject json = JsonObject.mapFrom(", classeLangueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getList().stream().findFirst().orElse(null));");
						}
					}
					else if(classeApiMethode.contains("POST")) {
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
	
					if((classeApiMethode.contains("GET") || classeApiMethode.contains(classeLangueConfig.getString(ConfigCles.var_Recherche))) && classePageNomCanoniqueMethode != null) {
						tl(5, "promise.complete(new ServiceResponse(200, \"OK\", buffer, ", classeLangueConfig.getString(ConfigCles.var_requeteEnTetes), "));");
						tl(4, "}).onFailure(ex -> {");
						tl(5, "promise.fail(ex);");
						tl(4, "});");
						tl(3, "}).onFailure(ex -> {");
						tl(4, "promise.fail(ex);");
						tl(3, "});");
					}
					else {
						tl(3, "promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));");
					}
	
					tl(2, "} catch(Exception ex) {");
						tl(3, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_reponse), "200", classeApiMethode, classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
					tl(3, "promise.fail(ex);");
					tl(2, "}");
					tl(2, "return promise.future();");
					tl(1, "}");
					if(classeApiMethode.contains(classeLangueConfig.getString(ConfigCles.var_Recherche)) && classePageNomCanoniqueMethode == null) {
						tl(1, "public void ", classeLangueConfig.getString(ConfigCles.var_reponse), "Pivot", classeApiMethode, classeNomSimple, "(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {");
						tl(2, "if(pivots != null) {");
						tl(3, "for(SolrResponse.Pivot pivotField : pivots) {");
						tl(4, "String ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Indexe), " = pivotField.getField();");
						tl(4, "String ", classeLangueConfig.getString(ConfigCles.var_entite), "Var = StringUtils.substringBefore(", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Indexe), ", \"_docvalues_\");");
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
						tl(5, classeLangueConfig.getString(ConfigCles.var_reponse), "Pivot", classeApiMethode, classeNomSimple, "(pivotFields2, pivotArray2);");
						tl(4, "}");
						tl(3, "}");
						tl(2, "}");
						tl(1, "}");
					}
				}
			}
	
			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + classeEntiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
				QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList rechercheListe = rechercheReponse.getResults();
				Integer rechercheLignes = rechercheSolr.getRows();
	
				if(rechercheListe.size() > 0) {
					for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
						for(Integer j = 0; j < rechercheListe.size(); j++) {
							SolrDocument entiteDocumentSolr = rechercheListe.get(j);
							entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + classeLangueNom + "_stored_string");
							entiteVarCapitalise = (String)doc.get("entiteVarCapitalise_" + classeLangueNom + "_stored_string");
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
	}

	/** 
	 * Var.enUS: writeGenApiServiceImpl
	 * Param1.var.enUS: languageName
	 * 
	 * r: entiteNomSimpleVertxJson
	 * r.enUS: entitySimpleNameVertxJson
	 * r: entiteNomCanoniqueVertxJson
	 * r.enUS: entityCanonicalNameVertxJson
	 * r: classeCheminGenApiServiceImpl
	 * r.enUS: classPathGenApiServiceImpl
	 * r: classeCheminApiServiceImpl
	 * r.enUS: classPathApiServiceImpl
	 * r: classeCheminGenApiService
	 * r.enUS: classPathGenApiService
	 * r: entiteClassesSuperEtMoiSansGen
	 * r.enUS: entitySuperClassesAndMeWithoutGen
	 * r: partEstEntite
	 * r.enUS: partIsEntity
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: rechercheListe
	 * r.enUS: searchList
	 * r: rechercheLignes
	 * r.enUS: searchLines
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: entiteVarCapitalise
	 * r.enUS: entityVarCapitalized
	 * r: entiteAttribuerVar
	 * r.enUS: entityAttributeVar
	 * r: entiteAttribuer
	 * r.enUS: entityAttribute
	 * r: entiteDefinir
	 * r.enUS: entityDefine
	 * r: entiteSuffixeType
	 * r.enUS: entityTypeSuffix
	 * r: entiteIndexe
	 * r.enUS: entityIndexed
	 * r: entiteStocke
	 * r.enUS: entityStored
	 * r: entiteSolrNomCanonique
	 * r.enUS: entitySolrCanonicalName
	 * r: entiteSolrNomSimple
	 * r.enUS: entitySolrSimpleName
	 * r: entiteListeNomSimpleVertxJson
	 * r.enUS: entityListSimpleNameVertxJson
	 * r: entiteListeNomCanoniqueVertxJson
	 * r.enUS: entityListCanonicalNameVertxJson
	 * r: classeIndexe
	 * r.enUS: classIndexed
	 * r: entiteValeur
	 * r.enUS: entityValue
	 * r: entiteNumero
	 * r.enUS: entityNumber
	 * r: entiteStr
	 * r.enUS: entityStr
	 * r: classeSauvegarde
	 * r.enUS: classSaved
	 * r: requeteJson
	 * r.enUS: requestJson
	 * r: methodeNom
	 * r.enUS: methodName
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
	 * r: classePageNomSimpleMethode
	 * r.enUS: classPageSimpleNameMethod
	 * r: classePageNomCanonique
	 * r.enUS: classPageCanonicalName
	 * r: classePageNomSimple
	 * r.enUS: classPageSimpleName
	 * r: classeApiTypeMedia
	 * r.enUS: classApiMediaType
	 * r: classePageLangueNom
	 * r.enUS: classPageLanguageName
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: "Recherche"
	 * r.enUS: "Search"
	 * r: "supprimer"
	 * r.enUS: "delete"
	 * r: creerLigne
	 * r.enUS: createRow
	 * r: definirAsync
	 * r.enUS: defineAsync
	 * r: definirPourClasse
	 * r.enUS: defineForClass
	 * r: initLoinPourClasse
	 * r.enUS: initDeepForClass
	 * r: indexerPourClasse
	 * r.enUS: indexForClass
	 * r: classePageSimple
	 * r.enUS: classPageSimple
	 * r: classeApiUriMethode
	 * r.enUS: classApiUriMethod
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: millisRecherche
	 * r.enUS: millisSearch
	 * r: numCommence
	 * r.enUS: numStart
	 * r: numTrouve
	 * r.enUS: numFound
	 * r: numRetourne
	 * r.enUS: numReturned
	 * r: tempsRecherche
	 * r.enUS: timeSearch
	 * r: tempsTransmission
	 * r.enUS: timeTransmission
	 * r: exceptionRecherche
	 * r.enUS: exceptionSearch
	 * r: champNom
	 * r.enUS: fieldName
	 * r: entiteVarStocke
	 * r.enUS: entityVarStored
	 * r: pageDocumentSolr
	 * r.enUS: pageSolrDocument
	 * r: PageDocumentSolr
	 * r.enUS: PageSolrDocument
	 * r: wVarIndexe
	 * r.enUS: wVarIndexed
	 * r: wVarRecherche
	 * r.enUS: wVarSearched
	 * r: wVarSuggere
	 * r.enUS: wVarSuggested
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: entiteTexte
	 * r.enUS: entityText
	 * r: entiteLangue
	 * r.enUS: entityLanguage
	 * r: entiteSuggere
	 * r.enUS: entitySuggested
	 * r: n'est pas une entité indexé.
	 * r.enUS: is not an indexed entity.
	 * r: entiteListeStr
	 * r.enUS: entityListStr
	 * r: entiteListe
	 * r.enUS: entityList
	 * r: classeVarCleUnique
	 * r.enUS: classVarUniqueKey
	 * r: classeNomsCanoniques
	 * r.enUS: classCanonicalNames
	 * r: valeurIndexe
	 * r.enUS: valueIndexed
	 * r: rechercheDebut
	 * r.enUS: searchStart
	 * r: valeurTri
	 * r.enUS: valueSort
	 * 
	 * r: auteurGenApiServiceImpl
	 * r.enUS: writerGenApiServiceImpl
	 * r: auteurApiServiceImpl
	 * r.enUS: writerApiServiceImpl
	 * r: auteurGenApiService
	 * r.enUS: writerGenApiService
	 * 
	 * r: wApiEntites
	 * r.enUS: wApiEntities
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * 
	 * r: classeRolesTrouves
	 * r.enUS: classRolesFound
	 * r: classeRoles
	 * r.enUS: classRoles
	 * 
	 * r: classeFiltresTrouves
	 * r.enUS: classFiltersFound
	 * r: classeFiltre
	 * r.enUS: classFilter
	 * 
	 * r: wApiGenererGet
	 * r.enUS: wApiGenerateGet
	 * r: wApiGenererPatch
	 * r.enUS: wApiGeneratePatch
	 * r: classeCheminApiGen
	 * r.enUS: classPathApiGen
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: gestionnaireEvenements
	 * r.enUS: eventHandler
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: wApiGenererPost
	 * r.enUS: wApiGeneratePost
	 * r: // Une méthode d'usine pour créer une instance et un proxy. 
	 * r.enUS: // A factory method to create an instance and a proxy. 
	 * r: creer
	 * r.enUS: create
	 * r: addresse
	 * r.enUS: address
	 * r: operationRequete
	 * r.enUS: serviceRequest
	 * r: gestionnaireResultat
	 * r.enUS: resultHandler
	 * r: nomEnsembleDomaine
	 * r.enUS: domainPackageName
	 * r: classeImportationsGenApi
	 * r.enUS: classImportsGenApi
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeApiOperationIdMethode
	 * r.enUS: classApiOperationIdMethod
	 * r: classeApiOperationId
	 * r.enUS: classApiOperationId
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: paramRequete
	 * r.enUS: queryParam
	 * r: paramObjet
	 * r.enUS: paramObject
	 * r: paramNom
	 * r.enUS paramName
	 * r: paramObjet
	 * r.enUS: paramObject
	 * r: paramValeursObjet
	 * r.enUS: paramValuesObject
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
	 * r: classePageNomSimpleMethode
	 * r.enUS: classPageSimpleNameMethod
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: objetJson
	 * r.enUS: jsonObject
	 * r: ObjetJson
	 * r.enUS: JsonObject
	 * r: siteUrlBase
	 * r.enUS: siteBaseUrl
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * r: connexionSql
	 * r.enUS: sqlConnection
	 * r: reponseOperation
	 * r.enUS: operationResponse
	 * r: utilisateurValeur
	 * r.enUS: userValue
	 * r: utilisateurPk
	 * r.enUS: userPk
	 * r: UtilisateurId
	 * r.enUS: UserId
	 * r: utilisateurId
	 * r.enUS: userId
	 * 
	 * r: resultatAsync
	 * r.enUS: asyncResult
	 * r: varIndexe
	 * r.enUS: varIndexed
	 * r: varRecherche
	 * r.enUS: varSearched
	 * r: varSuggere
	 * r.enUS: varSuggested
	 * r: entiteNomSimpleCompletGenerique
	 * r.enUS: entitySimpleNameCompleteGeneric
	 * r: entiteNomSimpleComplet
	 * r.enUS: entitySimpleNameComplete
	 * r: entiteNomCanoniqueComplet
	 * r.enUS: entityCanonicalNameComplete
	 * r: entiteNomSimpleGenerique
	 * r.enUS: entitySimpleNameGeneric
	 * r: entiteNomCanoniqueGenerique
	 * r.enUS: entityCanonicalNameGeneric
	 * r: entiteNomSimple
	 * r.enUS: entitySimpleName
	 * r: entiteNomCanonique
	 * r.enUS: entityCanonicalName
	 * r: ClientSql
	 * r.enUS: SqlClient
	 * r: clientSql
	 * r.enUS: sqlClient
	 * r: RequeteService
	 * r.enUS: ServiceRequest
	 * r: classePartsUtilisateurSite
	 * r.enUS: classPartsSiteUser
	 * r: classePartsRequeteApi
	 * r.enUS: classPartsApiRequest
	 * 
	 * r: UtilisateurPrenom
	 * r.enUS: UserFirstName
	 * r: UtilisateurNomFamille
	 * r.enUS: UserLastName
	 * r: UtilisateurNom
	 * r.enUS: UserName
	 * r: UtilisateurId
	 * r.enUS: UserId
	 * r: UtilisateurSite
	 * r.enUS: SiteUser
	 * r: RequeteApi
	 * r.enUS: ApiRequest
	 * r: Utilisateur
	 * r.enUS: User
	 * r: utilisateur
	 * r.enUS: user
	 * r: Partagé
	 * r.enUS: Shared
	 * r: documentsSolr
	 * r.enUS: solrDocuments
	 * r: documentSolr
	 * r.enUS: solrDocument
	 * r: supprimer
	 * r.enUS: delete
	 * r: SQL_vider
	 * r.enUS: SQL_clear
	 * r: RequetePk
	 * r.enUS: RequestPk
	 * r: remplacer
	 * r.enUS: replace
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: erreur
	 * r.enUS: error
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 * r: liste
	 * r.enUS: list
	 * r: ConnexionSql
	 * r.enUS: SqlConnection
	 * r: generer
	 * r.enUS: generate
	 * r: "Pour"
	 * r.enUS: "For"
	 * r: Traduire
	 * r.enUS: Translate
	 * r: nomSimple
	 * r.enUS: simpleName
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: Ecrire: 
	 * r.enUS: Write: 
	 * r: recherche
	 * r.enUS: search
	 * r: auteurApi
	 * r.enUS: writerApi
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * r: EnsembleInfo
	 * r.enUS: PackageInfo
	 * r: creer
	 * r.enUS: create
	 * r: reponse
	 * r.enUS: response
	 * r: Liste
	 * r.enUS: List
	 * r: definir
	 * r.enUS: define
	 * r: peupler
	 * r.enUS: populate
	 * r: stocker
	 * r.enUS: store
	 * r: Peupler
	 * r.enUS: Populate
	 * r: Stocker
	 * r.enUS: Store
	 * r: archive
	 * r.enUS: archived
	 * r: supprime
	 * r.enUS: deleted
	 * r: Archive
	 * r.enUS: Archived
	 * r: Supprime
	 * r.enUS: Deleted
	 * r: attribuer
	 * r.enUS: attribute
	 * r: indexer
	 * r.enUS: index
	 * 
	 * r: classCanonicalNames_
	 * r.enUS: classeNomsCanoniques_
	 * r: archived_
	 * r.enUS: archive_
	 * r: deleted_
	 * r.enUS: supprime_
	 */ 
	public void ecrireGenApiServiceImpl3(String classeLangueNom) throws Exception {
		classeAutresLangues = ArrayUtils.removeAllOccurences(toutesLangues, classeLangueNom);

		if(auteurGenApiServiceImpl != null) {
			l();
			tl(1, "// General //");

			if(classeApiMethodes.contains("POST") || classeApiMethodes.contains("PATCH") || classeApiMethodes.contains("PUT")) {
				l();
				tl(1, "public Future<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_creer), classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ") {");
				tl(2, "Promise<", classeApiClasseNomSimple, "> promise = Promise.promise();");
				tl(2, "try {");
				if(classeModele) {
					tl(3, "SqlConnection ", classeLangueConfig.getString(ConfigCles.var_connexionSql), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "();");
					tl(3, "String ", classeLangueConfig.getString(ConfigCles.var_utilisateur), "Id = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_Utilisateur), "Id();");
					tl(3, "Long ", classeLangueConfig.getString(ConfigCles.var_utilisateurCle), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurCle), "();");
					tl(3, "ZonedDateTime ", classeLangueConfig.getString(ConfigCles.var_cree), " = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ObjetJson), "()).map(j -> j.getString(\"", classeLangueConfig.getString(ConfigCles.var_cree), "\")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SITE_ZONE))));");
					l();
					if(activerUtilisateurCle) {
						tl(3, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(\"INSERT INTO ", classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_cree), ", ", classeLangueConfig.getString(ConfigCles.var_utilisateurCle), ") VALUES($1, $2) RETURNING ", classeVarClePrimaire, "\")");
						tl(5, ".collecting(Collectors.toList())");
						tl(5,".execute(Tuple.of(", classeLangueConfig.getString(ConfigCles.var_cree), ".toOffsetDateTime(), ", classeLangueConfig.getString(ConfigCles.var_utilisateurCle), ")).onSuccess(", classeLangueConfig.getString(ConfigCles.var_resultat), " -> {");
					} else {
						tl(3, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(\"INSERT INTO ", classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_cree), ") VALUES($1) RETURNING ", classeVarClePrimaire, "\")");
						tl(5, ".collecting(Collectors.toList())");
						tl(5,".execute(Tuple.of(", classeLangueConfig.getString(ConfigCles.var_cree), ".toOffsetDateTime())).onSuccess(", classeLangueConfig.getString(ConfigCles.var_resultat), " -> {");
					}
					tl(4, "Row ", classeLangueConfig.getString(ConfigCles.var_creer), classeLangueConfig.getString(ConfigCles.var_Ligne), " = ", classeLangueConfig.getString(ConfigCles.var_resultat), ".value().stream().findFirst().orElseGet(() -> null);");
					tl(4, "Long ", classeVarClePrimaire, " = ", classeLangueConfig.getString(ConfigCles.var_creer), classeLangueConfig.getString(ConfigCles.var_Ligne), ".getLong(0);");
					tl(4, classeNomSimple, " o = new ", classeNomSimple, "();");
					tl(4, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
					tl(4, "o.set", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
					tl(4, "promise.complete(o);");
					tl(3, "}).onFailure(ex -> {");
					tl(4, "RuntimeException ex2 = new RuntimeException(ex);");
					tl(4, "LOG.error(\"", classeLangueConfig.getString(ConfigCles.var_creer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \", ex2);");
					tl(4, "promise.fail(ex2);");
					tl(3, "});");
				} else {
					tl(3, classeNomSimple, " o = new ", classeNomSimple, "();");
					tl(3, "o.set", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
					tl(3, "promise.complete(o);");
					
				}
				tl(2, "} catch(Exception ex) {");
				tl(3, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_creer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
				tl(3, "promise.fail(ex);");
				tl(2, "}");
				tl(2, "return promise.future();");
				tl(1, "}");
			}
			l();
			tl(1, "public void ", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Q(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", String ", classeLangueConfig.getString(ConfigCles.var_entite), "Var, String ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ", String ", "var", classeLangueConfig.getString(ConfigCles.var_Indexe), ") {");
			tl(2, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".q(var", classeLangueConfig.getString(ConfigCles.var_Indexe), " + \":\" + (\"*\".equals(", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ") ? ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), " : SearchTool.escapeQueryChars(", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ")));");
			tl(2, "if(!\"*\".equals(", classeLangueConfig.getString(ConfigCles.var_entite), "Var)) {");
//			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".setHighlight(true);");
//			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".setHighlightSnippets(3);");
//			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".addHighlightField(var", classeLangueConfig.getString(ConfigCles.var_Indexe), ");");
//			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".setParam(\"hl.encoder\", \"html\");");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public String ", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Fq(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", String ", classeLangueConfig.getString(ConfigCles.var_entite), "Var, String ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ", String var", classeLangueConfig.getString(ConfigCles.var_Indexe), ") {");
			tl(2, "if(var", classeLangueConfig.getString(ConfigCles.var_Indexe), " == null)");
			tl(3, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", classeLangueConfig.getString(ConfigCles.str_nest_pas_une_entite_indexe), ". \", ", classeLangueConfig.getString(ConfigCles.var_entite), "Var));");
			tl(2, "if(StringUtils.startsWith(", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ", \"[\")) {");
			tl(3, "String[] fqs = StringUtils.substringBefore(StringUtils.substringAfter(", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ", \"[\"), \"]\").split(\" TO \");");
			tl(3, "if(fqs.length != 2)");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" invalid range query. \", ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), "));");
			tl(3, "String fq1 = fqs[0].equals(\"*\") ? fqs[0] : ", classeNomSimple, ".staticSearchFq", classeLangueConfig.getString(ConfigCles.var_PourClasse), "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var, ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class), fqs[0]);");
			tl(3, "String fq2 = fqs[1].equals(\"*\") ? fqs[1] : ", classeNomSimple, ".staticSearchFq", classeLangueConfig.getString(ConfigCles.var_PourClasse), "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var, ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class), fqs[1]);");
			tl(3, " return var", classeLangueConfig.getString(ConfigCles.var_Indexe), " + \":[\" + fq1 + \" TO \" + fq2 + \"]\";");
			tl(2, "} else {");
			tl(3, "return var", classeLangueConfig.getString(ConfigCles.var_Indexe), " + \":\" + SearchTool.escapeQueryChars(", classeNomSimple, ".staticSearchFq", classeLangueConfig.getString(ConfigCles.var_PourClasse), "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var, ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class), ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ")).replace(\"\\\\\", \"\\\\\\\\\");");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void ", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Sort(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", String ", classeLangueConfig.getString(ConfigCles.var_entite), "Var, String ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ", String ", "var", classeLangueConfig.getString(ConfigCles.var_Indexe), ") {");
			tl(2, "if(var", classeLangueConfig.getString(ConfigCles.var_Indexe), " == null)");
			tl(3, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", classeLangueConfig.getString(ConfigCles.str_nest_pas_une_entite_indexe), ". \", ", classeLangueConfig.getString(ConfigCles.var_entite), "Var));");
			tl(2, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".sort(var", classeLangueConfig.getString(ConfigCles.var_Indexe), ", ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ");");
			tl(1, "}");
			l();
			tl(1, "public void ", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Rows(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", Long ", classeLangueConfig.getString(ConfigCles.var_valeur), "Rows) {");
			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".rows(", classeLangueConfig.getString(ConfigCles.var_valeur), "Rows != null ? ", classeLangueConfig.getString(ConfigCles.var_valeur), "Rows : 10L);");
			tl(1, "}");
			l();
			tl(1, "public void ", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Start(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", Long ", classeLangueConfig.getString(ConfigCles.var_valeur), "Start) {");
			tl(2, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".start(", classeLangueConfig.getString(ConfigCles.var_valeur), "Start);");
			tl(1, "}");
			l();
			tl(1, "public void ", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Var(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", String var, String ", classeLangueConfig.getString(ConfigCles.var_valeur), ") {");
			tl(2, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classePartsRequeteSite.nomSimple(classeLangueNom), ".class).get", classeLangueConfig.getString(ConfigCles.var_Requete), "Vars().put(var, ", classeLangueConfig.getString(ConfigCles.var_valeur), ");");
			tl(1, "}");
			l();
			tl(1, "public void ", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Uri(", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ") {");
			tl(1, "}");
			l();
			//////////
			// vars //
			//////////
			tl(1, "public Future<ServiceResponse> vars", classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ") {");
			tl(2, "Promise<ServiceResponse> promise = Promise.promise();");
			tl(2, "try {");
			tl(3, "ServiceRequest ", classeLangueConfig.getString(ConfigCles.var_requeteService), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "();");
			l();
			tl(3, classeLangueConfig.getString(ConfigCles.var_requeteService), ".getParams().getJsonObject(\"query\").stream().filter(param", classeLangueConfig.getString(ConfigCles.var_Requete), " -> \"var\".equals(param", classeLangueConfig.getString(ConfigCles.var_Requete), ".getKey()) && param", classeLangueConfig.getString(ConfigCles.var_Requete), ".getValue() != null).findFirst().ifPresent(param", classeLangueConfig.getString(ConfigCles.var_Requete), " -> {");
			tl(4, "String ", classeLangueConfig.getString(ConfigCles.var_entite), "Var = null;");
			tl(4, "String ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), " = null;");
			tl(4, "Object param", classeLangueConfig.getString(ConfigCles.var_Valeurs), classeLangueConfig.getString(ConfigCles.var_Objet), " = param", classeLangueConfig.getString(ConfigCles.var_Requete), ".getValue();");
			tl(4, "JsonArray param", classeLangueConfig.getString(ConfigCles.var_Objets), " = param", classeLangueConfig.getString(ConfigCles.var_Valeurs), classeLangueConfig.getString(ConfigCles.var_Objet), " instanceof JsonArray ? (JsonArray)param", classeLangueConfig.getString(ConfigCles.var_Valeurs), classeLangueConfig.getString(ConfigCles.var_Objet), " : new JsonArray().add(param", classeLangueConfig.getString(ConfigCles.var_Valeurs), classeLangueConfig.getString(ConfigCles.var_Objet), ");");
			l();
			tl(4, "try {");
			tl(5, "for(Object param", classeLangueConfig.getString(ConfigCles.var_Objet), " : param", classeLangueConfig.getString(ConfigCles.var_Objets), ") {");
			tl(6, classeLangueConfig.getString(ConfigCles.var_entite), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ", \":\"));");
			tl(6, classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ", \":\")), \"UTF-8\");");
			tl(6, classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_Requete), "Vars().put(", classeLangueConfig.getString(ConfigCles.var_entite), "Var, ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ");");
			tl(5, "}");
			tl(4, "} catch(Exception ex) {");
			tl(5, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
			tl(5, "promise.fail(ex);");
			tl(4, "}");

			tl(3, "});");
			tl(3, "promise.complete();");
			tl(2, "} catch(Exception ex) {");
			tl(3, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
			tl(3, "promise.fail(ex);");
			tl(2, "}");
			tl(2, "return promise.future();");
			tl(1, "}");
			///////////////
			// recherche //
			///////////////
			l();
			tl(1, "public Future<", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, ">> ", classeLangueConfig.getString(ConfigCles.var_rechercher), classeApiClasseNomSimple, classeLangueConfig.getString(ConfigCles.var_Liste), "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", Boolean ", classeLangueConfig.getString(ConfigCles.var_peupler), ", Boolean ", classeLangueConfig.getString(ConfigCles.var_stocker), ", Boolean ", classeLangueConfig.getString(ConfigCles.var_modifier), ") {");
			tl(2, "Promise<", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, ">> promise = Promise.promise();");
			tl(2, "try {");
			tl(3, "ServiceRequest ", classeLangueConfig.getString(ConfigCles.var_requeteService), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "();");
			tl(3, "String ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Liste), "Str = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
			tl(3, "String[] ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Liste), " = ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Liste), "Str == null ? null : ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Liste), "Str.split(", q(",\\s*"), ");");
			tl(3, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, ">();");
			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".set", classeLangueConfig.getString(ConfigCles.var_Peupler), "(", classeLangueConfig.getString(ConfigCles.var_peupler), ");");
			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".set", classeLangueConfig.getString(ConfigCles.var_Stocker), "(", classeLangueConfig.getString(ConfigCles.var_stocker), ");");
			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".q(\"*:*\");");
			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".setC(", classeApiClasseNomSimple, ".class);");
			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".set", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ");");
			tl(3, "if(", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Liste), " != null) {");
			tl(4, "for(String v : ", classeLangueConfig.getString(ConfigCles.var_entite), classeLangueConfig.getString(ConfigCles.var_Liste), ") {");
			tl(5, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".fl(", classeNomSimple, ".var", classeLangueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(v));");
			tl(4, "}");
			tl(3, "}");
			s(wFacets);
			if(classeVarCleUnique != null) {
				l();
				tl(3, "String id = ", classeLangueConfig.getString(ConfigCles.var_requeteService), ".getParams().getJsonObject(\"path\").getString(\"id\");");
				tl(3, "if(", classeVarCleUnique, " != null && NumberUtils.isCreatable(", classeVarCleUnique, ")) {");
				tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".fq(\"(", classeVarClePrimaire, "_docvalues_long:\" + SearchTool.escapeQueryChars(id) + \" OR ", classeVarId, "_docvalues_string:\" + SearchTool.escapeQueryChars(id) + \")\");");
				tl(3, "} else if(", classeVarCleUnique, " != null) {");
				tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".fq(\"", classeVarId, "_docvalues_string:\" + SearchTool.escapeQueryChars(id));");
				tl(3, "}");
			}
			if(classeRoles.size() > 0 && (classeRoleSession || classeRoleUtilisateur)) {
				l();
				tl(3, "List<String> roles = Optional.ofNullable(config.getValue(", classePartsConfigCles.nomSimple(classeLangueNom), ".", classeLangueConfig.getString(ConfigCles.var_AUTH_ROLES_REQUIS), " + \"_", classeNomSimple, "\")).map(v -> v instanceof JsonArray ? (JsonArray)v : new JsonArray(v.toString())).orElse(new JsonArray()).getList();");
				tl(3, "List<String> ", classeLangueConfig.getString(ConfigCles.var_roleLires), " = Arrays.asList(\"", StringUtils.join(classeRoleLires, "\", \""), "\");");
				tl(3, "if(");
				tl(5, "!CollectionUtils.containsAny(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "(), roles)");
				tl(5, "&& !CollectionUtils.containsAny(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "(), roles)");
				tl(5, "&& (", classeLangueConfig.getString(ConfigCles.var_modifier), " || !CollectionUtils.containsAny(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "(), ", classeLangueConfig.getString(ConfigCles.var_roleLires), "))");
				tl(5, "&& (", classeLangueConfig.getString(ConfigCles.var_modifier), " || !CollectionUtils.containsAny(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "(), ", classeLangueConfig.getString(ConfigCles.var_roleLires), "))");
				tl(5, ") {");
				tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".fq(\"sessionId_docvalues_string:\" + SearchTool.escapeQueryChars(Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_docvalues_string:\" + SearchTool.escapeQueryChars(Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getSessionId", classeLangueConfig.getString(ConfigCles.var_Avant), "()).orElse(\"-----\"))");
				tl(6, "+ \" OR ", classeLangueConfig.getString(ConfigCles.var_utilisateur), classeLangueConfig.getString(ConfigCles.var_Cle), "s_docvalues_longs:\" + Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_Utilisateur), classeLangueConfig.getString(ConfigCles.var_Cle), "()).orElse(0L));");
				tl(3, "}");
			} else if(classeRoleSession || classeRoleUtilisateur) {
				l();
				tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".fq(\"sessionId_docvalues_string:\" + SearchTool.escapeQueryChars(Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_docvalues_string:\" + SearchTool.escapeQueryChars(Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getSessionId", classeLangueConfig.getString(ConfigCles.var_Avant), "()).orElse(\"-----\"))");
				tl(4, "+ \" OR ", classeLangueConfig.getString(ConfigCles.var_utilisateur), classeLangueConfig.getString(ConfigCles.var_Cle), "s_docvalues_longs:\" + Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_Utilisateur), classeLangueConfig.getString(ConfigCles.var_Cle), "()).orElse(0L));");
			}
			l();
			tl(3, classeLangueConfig.getString(ConfigCles.var_requeteService), ".getParams().getJsonObject(\"query\").forEach(param", classeLangueConfig.getString(ConfigCles.var_Requete), " -> {");
			tl(4, "String ", classeLangueConfig.getString(ConfigCles.var_entite), "Var = null;");
			tl(4, "String ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), " = null;");
			tl(4, "String var", classeLangueConfig.getString(ConfigCles.var_Indexe), " = null;");
			tl(4, "String ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Tri), " = null;");
			tl(4, "Long ", classeLangueConfig.getString(ConfigCles.var_valeur), "Start = null;");
			tl(4, "Long ", classeLangueConfig.getString(ConfigCles.var_valeur), "Rows = null;");
			tl(4, "String ", classeLangueConfig.getString(ConfigCles.var_valeur), "CursorMark = null;");
			tl(4, "String param", classeLangueConfig.getString(ConfigCles.var_Nom), " = param", classeLangueConfig.getString(ConfigCles.var_Requete), ".getKey();");
			tl(4, "Object param", classeLangueConfig.getString(ConfigCles.var_Valeurs), classeLangueConfig.getString(ConfigCles.var_Objet), " = param", classeLangueConfig.getString(ConfigCles.var_Requete), ".getValue();");
			tl(4, "JsonArray param", classeLangueConfig.getString(ConfigCles.var_Objets), " = param", classeLangueConfig.getString(ConfigCles.var_Valeurs), classeLangueConfig.getString(ConfigCles.var_Objet), " instanceof JsonArray ? (JsonArray)param", classeLangueConfig.getString(ConfigCles.var_Valeurs), classeLangueConfig.getString(ConfigCles.var_Objet), " : new JsonArray().add(param", classeLangueConfig.getString(ConfigCles.var_Valeurs), classeLangueConfig.getString(ConfigCles.var_Objet), ");");
			l();
			tl(4, "try {");
	
			tl(5, "if(param", classeLangueConfig.getString(ConfigCles.var_Valeurs), classeLangueConfig.getString(ConfigCles.var_Objet), " != null && \"facet.pivot\".equals(param", classeLangueConfig.getString(ConfigCles.var_Nom), ")) {");
			tl(6, "Matcher mFacetPivot = Pattern.compile(\"(?:(\\\\{![^\\\\}]+\\\\}))?(.*)\").matcher(StringUtils.join(param", classeLangueConfig.getString(ConfigCles.var_Objets), ".getList().toArray(), \",\"));");
			tl(6, "boolean foundFacetPivot = mFacetPivot.find();");
			tl(6, "if(foundFacetPivot) {");
			tl(7, "String solrLocalParams = mFacetPivot.group(1);");
			tl(7, "String[] ", classeLangueConfig.getString(ConfigCles.var_entite), "Vars = mFacetPivot.group(2).trim().split(\",\");");
			tl(7, "String[] vars", classeLangueConfig.getString(ConfigCles.var_Indexe), " = new String[", classeLangueConfig.getString(ConfigCles.var_entite), "Vars.length];");
			tl(7, "for(Integer i = 0; i < ", classeLangueConfig.getString(ConfigCles.var_entite), "Vars.length; i++) {");
			tl(8, classeLangueConfig.getString(ConfigCles.var_entite), "Var = ", classeLangueConfig.getString(ConfigCles.var_entite), "Vars[i];");
			tl(8, "vars", classeLangueConfig.getString(ConfigCles.var_Indexe), "[i] = ", classeNomSimple, ".var", classeLangueConfig.getString(ConfigCles.var_Indexe), "", classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var);");
			tl(7, "}");
			tl(7, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".facetPivot((solrLocalParams == null ? \"\" : solrLocalParams) + StringUtils.join(vars", classeLangueConfig.getString(ConfigCles.var_Indexe), ", \",\"));");
			tl(6, "}");
			tl(5, "} else if(param", classeLangueConfig.getString(ConfigCles.var_Valeurs), classeLangueConfig.getString(ConfigCles.var_Objet), " != null) {");

			tl(6, "for(Object param", classeLangueConfig.getString(ConfigCles.var_Objet), " : param", classeLangueConfig.getString(ConfigCles.var_Objets), ") {");
			tl(7, "switch(param", classeLangueConfig.getString(ConfigCles.var_Nom), ") {");
	
			tl(8, "case \"q\":");
			tl(9, "Matcher mQ = Pattern.compile(\"(\\\\w+):(.+?(?=(\\\\)|\\\\s+OR\\\\s+|\\\\s+AND\\\\s+|\\\\^|$)))\").matcher((String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ");");
			tl(9, "boolean foundQ = mQ.find();");
			tl(9, "if(foundQ) {");
			tl(10, "StringBuffer sb = new StringBuffer();");
			tl(10, "while(foundQ) {");
			tl(11, classeLangueConfig.getString(ConfigCles.var_entite), "Var = mQ.group(1).trim();");
			tl(11, classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), " = mQ.group(2).trim();");
			tl(11, "var", classeLangueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", classeLangueConfig.getString(ConfigCles.var_Indexe), "", classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var);");
			tl(11, "String ", classeLangueConfig.getString(ConfigCles.var_entite), "Q = ", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Fq(", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", ", classeLangueConfig.getString(ConfigCles.var_entite), "Var, ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ", ", "var", classeLangueConfig.getString(ConfigCles.var_Indexe), ");");
			tl(11, "mQ.appendReplacement(sb, ", classeLangueConfig.getString(ConfigCles.var_entite), "Q);");
			tl(11, "foundQ = mQ.find();");
			tl(10, "}");
			tl(10, "mQ.appendTail(sb);");
			tl(10, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".q(sb.toString());");
			tl(9, "}");
			tl(9, "break;");
	
			tl(8, "case \"fq\":");
			tl(9, "Matcher mFq = Pattern.compile(\"(\\\\w+):(.+?(?=(\\\\)|\\\\s+OR\\\\s+|\\\\s+AND\\\\s+|$)))\").matcher((String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ");");
			tl(9, "boolean foundFq = mFq.find();");
			tl(9, "if(foundFq) {");
			tl(10, "StringBuffer sb = new StringBuffer();");
			tl(10, "while(foundFq) {");
			tl(11, classeLangueConfig.getString(ConfigCles.var_entite), "Var = mFq.group(1).trim();");
			tl(11, classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), " = mFq.group(2).trim();");
			tl(11, "var", classeLangueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", classeLangueConfig.getString(ConfigCles.var_Indexe), "", classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var);");
			tl(11, "String ", classeLangueConfig.getString(ConfigCles.var_entite), "Fq = ", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Fq(", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", ", classeLangueConfig.getString(ConfigCles.var_entite), "Var, ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ", ", "var", classeLangueConfig.getString(ConfigCles.var_Indexe), ");");
			tl(11, "mFq.appendReplacement(sb, ", classeLangueConfig.getString(ConfigCles.var_entite), "Fq);");
			tl(11, "foundFq = mFq.find();");
			tl(10, "}");
			tl(10, "mFq.appendTail(sb);");
			tl(10, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".fq(sb.toString());");
			tl(9, "}");
			tl(9, "break;");

			tl(8, "case \"sort\":");
			tl(9, classeLangueConfig.getString(ConfigCles.var_entite), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ", \" \"));");
			tl(9, classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), " = StringUtils.trim(StringUtils.substringAfter((String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ", \" \"));");
			tl(9, "var", classeLangueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", classeLangueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var);");
			tl(9, classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Sort(", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", ", classeLangueConfig.getString(ConfigCles.var_entite), "Var, ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ", ", "var", classeLangueConfig.getString(ConfigCles.var_Indexe), ");");
			tl(9, "break;");
//	
//			tl(8, "case \"fl\":");
//			tl(9, langueConfig.getString(ConfigCles.var_entite), "Var = StringUtils.trim((String)param", langueConfig.getString(ConfigCles.var_Objet), ");");
//			tl(9, "var", langueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", langueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(", langueConfig.getString(ConfigCles.var_entite), "Var);");
//			tl(9, langueConfig.getString(ConfigCles.var_liste), langueConfig.getString(ConfigCles.var_Recherche), ".addField(var", langueConfig.getString(ConfigCles.var_Indexe), ");");
//			tl(9, "break;");
	
			tl(8, "case \"start\":");
			tl(9, classeLangueConfig.getString(ConfigCles.var_valeur), "Start = param", classeLangueConfig.getString(ConfigCles.var_Objet), " instanceof Long ? (Long)param", classeLangueConfig.getString(ConfigCles.var_Objet), " : Long.parseLong(param", classeLangueConfig.getString(ConfigCles.var_Objet), ".toString());");
			tl(9, classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Start(", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", ", classeLangueConfig.getString(ConfigCles.var_valeur), "Start);");
			tl(9, "break;");
	
			tl(8, "case \"rows\":");
			tl(9, classeLangueConfig.getString(ConfigCles.var_valeur), "Rows = param", classeLangueConfig.getString(ConfigCles.var_Objet), " instanceof Long ? (Long)param", classeLangueConfig.getString(ConfigCles.var_Objet), " : Long.parseLong(param", classeLangueConfig.getString(ConfigCles.var_Objet), ".toString());");
			tl(9, classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Rows(", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", ", classeLangueConfig.getString(ConfigCles.var_valeur), "Rows);");
			tl(9, "break;");
	
			tl(8, "case \"stats\":");
			tl(9, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".stats((Boolean)param", classeLangueConfig.getString(ConfigCles.var_Objet), ");");
			tl(9, "break;");
	
			tl(8, "case \"stats.field\":");
			tl(9, classeLangueConfig.getString(ConfigCles.var_entite), "Var = (String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ";");
			tl(9, "var", classeLangueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", classeLangueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var);");
			tl(9, "if(var", classeLangueConfig.getString(ConfigCles.var_Indexe), " != null)");
			tl(10, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".statsField(var", classeLangueConfig.getString(ConfigCles.var_Indexe), ");");
			tl(9, "break;");
	
			tl(8, "case \"facet\":");
			tl(9, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".facet((Boolean)param", classeLangueConfig.getString(ConfigCles.var_Objet), ");");
			tl(9, "break;");
	
			tl(8, "case \"facet.range.start\":");
			tl(9, "String startMathStr = (String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ";");
			tl(9, "Date start = SearchTool.parseMath(startMathStr);");
			tl(9, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".facetRangeStart(start.toInstant().toString());");
			tl(9, "break;");
	
			tl(8, "case \"facet.range.end\":");
			tl(9, "String endMathStr = (String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ";");
			tl(9, "Date end = SearchTool.parseMath(endMathStr);");
			tl(9, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".facetRangeEnd(end.toInstant().toString());");
			tl(9, "break;");
	
			tl(8, "case \"facet.range.gap\":");
			tl(9, "String gap = (String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ";");
			tl(9, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".facetRangeGap(gap);");
			tl(9, "break;");
	
			tl(8, "case \"facet.range\":");
			tl(9, "Matcher mFacetRange = Pattern.compile(\"(?:(\\\\{![^\\\\}]+\\\\}))?(.*)\").matcher((String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ");");
			tl(9, "boolean foundFacetRange = mFacetRange.find();");
			tl(9, "if(foundFacetRange) {");
			tl(10, "String solrLocalParams = mFacetRange.group(1);");
			tl(10, classeLangueConfig.getString(ConfigCles.var_entite), "Var = mFacetRange.group(2).trim();");
			tl(10, "var", classeLangueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", classeLangueConfig.getString(ConfigCles.var_Indexe), "", classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var);");
			tl(10, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".facetRange((solrLocalParams == null ? \"\" : solrLocalParams) + var", classeLangueConfig.getString(ConfigCles.var_Indexe), ");");
			tl(9, "}");
			tl(9, "break;");
	
			tl(8, "case \"facet.field\":");
			tl(9, classeLangueConfig.getString(ConfigCles.var_entite), "Var = (String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ";");
			tl(9, "var", classeLangueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", classeLangueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(", classeLangueConfig.getString(ConfigCles.var_entite), "Var);");
			tl(9, "if(var", classeLangueConfig.getString(ConfigCles.var_Indexe), " != null)");
			tl(10, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".facetField(var", classeLangueConfig.getString(ConfigCles.var_Indexe), ");");
			tl(9, "break;");
	
			tl(8, "case \"var\":");
			tl(9, classeLangueConfig.getString(ConfigCles.var_entite), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ", \":\"));");
			tl(9, classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ", \":\")), \"UTF-8\");");
			tl(9, classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Var(", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ", ", classeLangueConfig.getString(ConfigCles.var_entite), "Var, ", classeLangueConfig.getString(ConfigCles.var_valeur), classeLangueConfig.getString(ConfigCles.var_Indexe), ");");
			tl(9, "break;");
	
			tl(8, "case \"cursorMark\":");
			tl(9, classeLangueConfig.getString(ConfigCles.var_valeur), "CursorMark = (String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ";");
			tl(9, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".cursorMark((String)param", classeLangueConfig.getString(ConfigCles.var_Objet), ");");
			tl(9, "break;");
	
			tl(7, "}");
			tl(6, "}");
			tl(6, classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Uri(", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ");");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "ExceptionUtils.rethrow(e);");
			tl(4, "}");

			tl(3, "});");
			if(classeVarCree != null) {
				tl(3, "if(\"*:*\".equals(", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".getQuery()) && ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".getSorts().size() == 0) {");
				if(classeTrisVar != null && classeTrisVar.size() > 0) {
					for(int i = 0; i < classeTrisVar.size(); i++) {
						String classeTriVar = classeTrisVar.get(i);
						String classeTriOrdre = classeTrisOrdre.get(i);
						String classeTriSuffixeType = classeTrisSuffixeType.get(i);
						tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".sort(\"", classeTriVar, "_docvalues", classeTriSuffixeType, "\", \"", classeTriOrdre, "\");");
					}
				}
				else {
					tl(4, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".sort(\"", classeVarCree, "_docvalues_date\", \"desc\");");
				}
				tl(3, "}");
			}
			tl(3, classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "2(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", ", classeLangueConfig.getString(ConfigCles.var_peupler), ", ", classeLangueConfig.getString(ConfigCles.var_stocker), ", ", classeLangueConfig.getString(ConfigCles.var_modifier), ", ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ");");
			tl(3, classeLangueConfig.getString(ConfigCles.var_listeRecherche), ".", classeLangueConfig.getString(ConfigCles.var_promesseLoin), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(a -> {");
			tl(4, "promise.complete(", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ");");
			tl(3, "}).onFailure(ex -> {");
			tl(4, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
			tl(4, "promise.fail(ex);");
			tl(3, "});");
			tl(2, "} catch(Exception ex) {");
			tl(3, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
			tl(3, "promise.fail(ex);");
			tl(2, "}");
			tl(2, "return promise.future();");
			tl(1, "}");
			tl(1, "public void ", classeLangueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "2(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ", Boolean ", classeLangueConfig.getString(ConfigCles.var_peupler), ", Boolean ", classeLangueConfig.getString(ConfigCles.var_stocker), ", Boolean ", classeLangueConfig.getString(ConfigCles.var_modifier), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeApiClasseNomSimple, "> ", classeLangueConfig.getString(ConfigCles.var_listeRecherche), ") {");
			tl(1, "}");
		
			/////////////
			// definir //
			/////////////
			l();
			if(classeModele) {
				tl(1, "public Future<Void> ", classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, "(", classeNomSimple, " o) {");
				tl(2, "Promise<Void> promise = Promise.promise();");
				tl(2, "try {");
				tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = o.get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
				tl(3, "SqlConnection ", classeLangueConfig.getString(ConfigCles.var_connexionSql), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "();");
				tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
				tl(3, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(\"SELECT * FROM ", classeNomSimple, " WHERE ", classeVarClePrimaire, "=$1\")");
				tl(5, ".collecting(Collectors.toList())");
				tl(5, ".execute(Tuple.of(", classeVarClePrimaire, ")");
				tl(5, ").onSuccess(", classeLangueConfig.getString(ConfigCles.var_resultat), " -> {");
				tl(4, "try {");
				tl(5, "for(Row definition : ", classeLangueConfig.getString(ConfigCles.var_resultat), ".value()) {");
				tl(6, "for(Integer i = 0; i < definition.size(); i++) {");
				tl(7, "String columnName = definition.getColumnName(i);");
				tl(7, "Object columnValue = definition.getValue(i);");
				tl(7, "if(!\"", classeVarClePrimaire, "\".equals(columnName)) {");
				tl(8, "try {");
				tl(9, "o.", classeLangueConfig.getString(ConfigCles.var_definir), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(columnName, columnValue);");
				tl(8, "} catch(Exception e) {");
				tl(9, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), e);");
				tl(8, "}");
				tl(7, "}");
				tl(6, "}");
				tl(5, "}");
				tl(5, "promise.complete();");
				tl(4, "} catch(Exception ex) {");
				tl(5, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
				tl(5, "promise.fail(ex);");
				tl(4, "}");
				tl(3, "}).onFailure(ex -> {");
				tl(4, "RuntimeException ex2 = new RuntimeException(ex);");
				tl(4, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex2);");
				tl(4, "promise.fail(ex2);");
				tl(3, "});");
			} else {
				tl(1, "public Future<Void> ", classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, "(", classeNomSimple, " o, Boolean patch) {");
				tl(2, "Promise<Void> promise = Promise.promise();");
				tl(2, "try {");
				tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = o.get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
				tl(4, "try {");
				tl(5, "JsonObject jsonObject = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getJsonObject();");
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
				tl(8, "o.", classeLangueConfig.getString(ConfigCles.var_definir), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(columnName, columnValue);");
				tl(7, "} catch(Exception e) {");
				tl(8, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), e);");
				tl(7, "}");
				tl(6, "}");
				tl(5, "});");
				tl(5, "promise.complete();");
				tl(4, "} catch(Exception ex) {");
				tl(5, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
				tl(5, "promise.fail(ex);");
				tl(4, "}");
			}
			tl(2, "} catch(Exception ex) {");
			tl(3, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_definir), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
			tl(3, "promise.fail(ex);");
			tl(2, "}");
			tl(2, "return promise.future();");
			tl(1, "}");

			///////////////
			// attribuer //
			///////////////
			if(classeModele) {
				l();
				tl(1, "public Future<Void> ", classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, "(", classeNomSimple, " o) {");
				tl(2, "Promise<Void> promise = Promise.promise();");
				if(wAttribuerSql.getEmpty()) {
					tl(3, "promise.complete();");
				} else {
					tl(2, "try {");
					tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = o.get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
					tl(3, "SqlConnection ", classeLangueConfig.getString(ConfigCles.var_connexionSql), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ConnexionSql), "();");
					tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
					tl(3, classeLangueConfig.getString(ConfigCles.var_connexionSql), ".preparedQuery(\"", wAttribuerSql, "\")");
					tl(5, ".collecting(Collectors.toList())");
					tl(5, ".execute(Tuple.of(", wAttribuerSqlParams, ")");
					tl(5, ").onSuccess(", classeLangueConfig.getString(ConfigCles.var_resultat), " -> {");
					tl(4, "try {");
					tl(5, "if(", classeLangueConfig.getString(ConfigCles.var_resultat), " != null) {");
					tl(6, "for(Row definition : ", classeLangueConfig.getString(ConfigCles.var_resultat), ".value()) {");
					tl(7, "o.", classeLangueConfig.getString(ConfigCles.var_attribuer), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(definition.getString(1), definition.getLong(0));");
					tl(6, "}");
					tl(5, "}");
					tl(5, "promise.complete();");
					tl(4, "} catch(Exception ex) {");
					tl(5, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
					tl(5, "promise.fail(ex);");
					tl(4, "}");
					tl(3, "}).onFailure(ex -> {");
					tl(4, "RuntimeException ex2 = new RuntimeException(ex);");
					tl(4, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex2);");
					tl(4, "promise.fail(ex2);");
					tl(3, "});");
					tl(2, "} catch(Exception ex) {");
					tl(3, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
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
				tl(1, "public Future<Void> ", classeLangueConfig.getString(ConfigCles.var_indexer), classeNomSimple, "(", classeNomSimple, " o) {");
				tl(2, "Promise<Void> promise = Promise.promise();");
				tl(2, "try {");
				tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = o.get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
				tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_();");
				tl(3, "o.", classeLangueConfig.getString(ConfigCles.var_promesseLoin), classeLangueConfig.getString(ConfigCles.var_PourClasse), "(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(a -> {");
				tl(4, "JsonObject json = new JsonObject();");
				tl(4, "JsonObject add = new JsonObject();");
				tl(4, "json.put(\"add\", add);");
				tl(4, "JsonObject doc = new JsonObject();");
				tl(4, "add.put(\"doc\", doc);");
				tl(4, "o.", classeLangueConfig.getString(ConfigCles.var_indexer), classeNomSimple, "(doc);");
				tl(4, "String solrHostName = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_HOST_NAME);");
				tl(4, "Integer solrPort = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getConfig().getInteger(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_PORT);");
				tl(4, "String solrCollection = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getConfig().getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".SOLR_COLLECTION);");
				tl(4, "Boolean softCommit = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getBoolean(\"softCommit\")).orElse(null);");
				tl(4, "Integer commitWithin = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getInteger(\"commitWithin\")).orElse(null);");
				tl(5, "if(softCommit == null && commitWithin == null)");
				tl(6, "softCommit = true;");
				tl(5, "else if(softCommit == null)");
				tl(6, "softCommit = false;");
				tl(4, "String solrRequestUri = String.format(\"/solr/%s/update%s%s%s\", solrCollection, \"?overwrite=true&wt=json\", softCommit ? \"&softCommit=true\" : \"\", commitWithin != null ? (\"&commitWithin=\" + commitWithin) : \"\");");
				tl(4, classeLangueConfig.getString(ConfigCles.var_clientWeb), ".post(solrPort, solrHostName, solrRequestUri).putHeader(\"Content-Type\", \"application/json\").expect(ResponsePredicate.SC_OK).sendBuffer(json.toBuffer()).onSuccess(b -> {");
				tl(5, "promise.complete();");
				tl(4, "}).onFailure(ex -> {");
				tl(5, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_indexer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), new RuntimeException(ex));");
				tl(5, "promise.fail(ex);");
				tl(4, "});");
				tl(3, "}).onFailure(ex -> {");
				tl(4, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_indexer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
				tl(4, "promise.fail(ex);");
				tl(3, "});");
				tl(2, "} catch(Exception ex) {");
				tl(3, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_indexer), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
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
				tl(1, "public Future<Void> ", classeLangueConfig.getString(ConfigCles.var_recharger), classeNomSimple, "(", classeNomSimple, " o) {");
				tl(2, "Promise<Void> promise = Promise.promise();");
				tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteSite), " = o.get", classeLangueConfig.getString(ConfigCles.var_RequeteSite), "_();");
				tl(2, "try {");
				tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", classeLangueConfig.getString(ConfigCles.var_requeteApi), " = ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteApi), "_();");
				tl(3, "List<Long> pks = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").map(r -> r.getPks()).orElse(new ArrayList<>());");
				tl(3, "List<String> classes = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteApi), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
				tl(3, "Boolean ", classeLangueConfig.getString(ConfigCles.var_recharger), " = !\"false\".equals(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_Requete), "Vars().get(\"", classeLangueConfig.getString(ConfigCles.var_recharger), "\"));");
				tl(3, "if(", classeLangueConfig.getString(ConfigCles.var_recharger), " && !Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_ObjetJson), "()).map(JsonObject::isEmpty).orElse(true)) {");
				tl(4, "List<Future> futures = new ArrayList<>();");
				l();
				tl(4, "for(int i=0; i < pks.size(); i++) {");
				tl(5, "Long ", classeVarClePrimaire, "2 = pks.get(i);");
				tl(5, "String ", classeLangueConfig.getString(ConfigCles.var_classeNomSimple), "2 = classes.get(i);");
				s(wIndexerFacetFor);
				tl(4, "}");
				l();
				if(classePartsUtilisateurSite != null && classePartsUtilisateurSite.nomSimple(classeLangueNom).equals(classeNomSimple)) {
					tl(4, "promise.complete();");
				} else {
					tl(4, "CompositeFuture.all(futures).onSuccess(b -> {");
					tl(5, "JsonObject params = new JsonObject();");
					tl(5, "params.put(\"body\", new JsonObject());");
					tl(5, "params.put(\"cookie\", new JsonObject());");
					tl(5, "params.put(\"header\", new JsonObject());");
					tl(5, "params.put(\"form\", new JsonObject());");
					tl(5, "params.put(\"path\", new JsonObject());");
					tl(5, "JsonObject query = new JsonObject();");
					tl(5, "Boolean softCommit = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getBoolean(\"softCommit\")).orElse(null);");
					tl(5, "Integer commitWithin = Optional.ofNullable(", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".get", classeLangueConfig.getString(ConfigCles.var_RequeteService), "().getParams()).map(p -> p.getJsonObject(\"query\")).map( q -> q.getInteger(\"commitWithin\")).orElse(null);");
					tl(5, "if(softCommit == null && commitWithin == null)");
					tl(6, "softCommit = true;");
					tl(5, "if(softCommit != null)");
					tl(6, "query.put(\"softCommit\", softCommit);");
					tl(5, "if(commitWithin != null)");
					tl(6, "query.put(\"commitWithin\", commitWithin);");
					tl(5, "query.put(\"q\", \"*:*\").put(\"fq\", new JsonArray().add(\"pk:\" + o.getPk())).put(\"var\", new JsonArray().add(\"refresh:false\"));");
					tl(5, "params.put(\"query\", query);");
					tl(5, "JsonObject context = new JsonObject().put(\"params\", params).put(\"user\", ", classeLangueConfig.getString(ConfigCles.var_requeteSite), ".getUserPrincipal());");
					tl(5, "JsonObject json = new JsonObject().put(\"context\", context);");
					tl(5, "eventBus.request(\"", siteNom, "-", classeLangueNom, "-", classeNomSimple, "\", json, new DeliveryOptions().addHeader(\"action\", \"patch", classeNomSimple, "Future\")).onSuccess(c -> {");
					tl(6, "JsonObject responseMessage = (JsonObject)c.body();");
					tl(6, "Integer statusCode = responseMessage.getInteger(\"statusCode\");");
					tl(6, "if(statusCode.equals(200))");
					tl(7, "promise.complete();");
					tl(6, "else");
					tl(7, "promise.fail(new RuntimeException(responseMessage.getString(\"statusMessage\")));");
					tl(5, "}).onFailure(ex -> {");
					tl(6, "LOG.error(\"", classeLangueConfig.getString(ConfigCles.var_Recharger), " ", classeLangueConfig.getString(ConfigCles.var_relations), " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \", ex);");
					tl(6, "promise.fail(ex);");
					tl(5, "});");
					tl(4, "}).onFailure(ex -> {");
					tl(5, "LOG.error(\"", classeLangueConfig.getString(ConfigCles.var_Recharger), " ", classeLangueConfig.getString(ConfigCles.var_relations), " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \", ex);");
					tl(5, "promise.fail(ex);");
					tl(4, "});");
				}
	
	
				tl(3, "} else {");
				tl(4, "promise.complete();");
				tl(3, "}");
				tl(2, "} catch(Exception ex) {");
				tl(3, "LOG.error(String.format(\"", classeLangueConfig.getString(ConfigCles.var_recharger), classeNomSimple, " ", classeLangueConfig.getString(ConfigCles.str_a_échoué), ". \"), ex);");
				tl(3, "promise.fail(ex);");
				tl(2, "}");
				tl(2, "return promise.future();");
				tl(1, "}");
			}
	
			tl(0, "}");

			auteurGenApiServiceImpl.flushClose();
			System.out.println(classeLangueConfig.getString(ConfigCles.var_Ecrire) + ": " + classeCheminGenApiServiceImpl); 
		}
	}
}
