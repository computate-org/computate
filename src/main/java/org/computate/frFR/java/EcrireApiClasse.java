package org.computate.frFR.java; 

import java.net.URLEncoder;
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
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: siteContexte
	 * r.enUS: siteContext
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
			auteurGenApiService.l("package ", classeNomEnsemble, ";");
			auteurGenApiService.l();
			auteurGenApiService.l("import org.apache.solr.client.solrj.SolrClient;");
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
			auteurGenApiService.l("import io.vertx.core.WorkerExecutor;");
			auteurGenApiService.l("import io.vertx.pgclient.PgPool;");
			if(activerOpenIdConnect) {
				auteurGenApiService.l("import io.vertx.ext.auth.oauth2.OAuth2Auth;");
				auteurGenApiService.l("import io.vertx.ext.auth.authorization.AuthorizationProvider;");
			}
			auteurGenApiService.l();
			auteurGenApiService.l("/**");
			auteurGenApiService.l(" * ", str_Traduire(classeLangueNom), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueGenApiServiceLangue = (String)classeDoc.get("classeNomCanoniqueGenApiService_" + langueNom + "_stored_string");
				auteurGenApiService.l(" * ", str_NomCanonique(classeLangueNom), ".", langueNom, ": ", classeNomCanoniqueGenApiServiceLangue);
			}
			auteurGenApiService.l(" * Gen: false");
			auteurGenApiService.l(" **/");
			auteurGenApiService.l("@WebApiServiceGen");
			auteurGenApiService.l("@ProxyGen");
			auteurGenApiService.s("public interface ", classeNomSimpleGenApiService, " {");
			auteurGenApiService.l();
//			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");

			l();
			tl(8, "protected EventBus eventBus;");
			l();
			tl(8, "protected JsonObject config;");
			l();
			tl(8, "protected WorkerExecutor ", str_executeurTravailleur(classeLangueNom), ";");
			l();
			tl(8, "protected PgPool pgPool;");
			l();
			tl(8, "protected SolrClient ", str_clientSolr(classeLangueNom), ";");

			auteurGenApiService.tl(1, "static void ", str_enregistrer(classeLangueNom), "Service(EventBus eventBus, JsonObject config, WorkerExecutor ", str_executeurTravailleur(classeLangueNom), ", PgPool pgPool, SolrClient ", str_clientSolr(classeLangueNom), activerOpenIdConnect ? ", OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider" : "", ", Vertx vertx) {");
			auteurGenApiService.tl(2, "new ServiceBinder(vertx).setAddress(", q(appliNom, "-", classeLangueNom, "-", classeNomSimple), ").register(", classeNomSimpleGenApiService, ".class, new ", classeNomSimpleApiServiceImpl, "(eventBus, config, ", str_executeurTravailleur(classeLangueNom), ", pgPool, ", str_clientSolr(classeLangueNom), activerOpenIdConnect ? ", oauth2AuthenticationProvider, authorizationProvider" : "", "));");
			auteurGenApiService.tl(1, "}");
//			auteurGenApiService.l();
////			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");
//			auteurGenApiService.tl(1, "static ", classeNomSimpleGenApiService, " ", str_creer(classeLangueNom), "(", classePartsSiteContexte.nomSimple(classeLangueNom), " ", str_siteContexte(classeLangueNom), ", Vertx vertx) {");
//			auteurGenApiService.tl(2, "return new ", classeNomSimpleApiServiceImpl, "(", str_siteContexte(classeLangueNom), ");");
//			auteurGenApiService.tl(1, "}");
//			auteurGenApiService.l();
//			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");
//			auteurGenApiService.tl(1, "static ", classeNomSimpleGenApiService, " ", str_creer(classeLangueNom), "Proxy(Vertx vertx, String ", str_addresse(classeLangueNom), ") {");
//			auteurGenApiService.tl(2, "return new ", classeNomSimpleGenApiService, "VertxEBProxy(vertx, ", str_addresse(classeLangueNom), ");");
//			auteurGenApiService.tl(1, "}");
			auteurGenApiService.l();
			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classePageNomCanoniqueMethode = (String)classeDoc.get("classePageNomCanonique" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classePageLangueNom = (String)classeDoc.get("classePageLangueNom" + classeApiMethode + "_" + classeLangueNom + "_stored_string");

				if(classePageLangueNom == null || classePageLangueNom.equals(classeLangueNom)) {
					if(classePageNomCanoniqueMethode != null) {
						auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "Id(");
						if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
							auteurGenApiService.s("JsonObject body, ");
						auteurGenApiService.l("ServiceRequest ", str_requeteService(classeLangueNom), ", Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ");");
					}
	
					auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "(");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						auteurGenApiService.s("JsonObject body, ");
					auteurGenApiService.l("ServiceRequest ", str_requeteService(classeLangueNom), ", Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ");");
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
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: siteContexte
	 * r.enUS: siteContext
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
			auteurApiServiceImpl.l("package ", classeNomEnsemble, ";");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.l("import org.apache.solr.client.solrj.SolrClient;");
			auteurApiServiceImpl.l("import io.vertx.core.eventbus.EventBus;");
			auteurApiServiceImpl.l("import io.vertx.core.WorkerExecutor;");
			auteurApiServiceImpl.l("import io.vertx.core.json.JsonObject;");
			auteurApiServiceImpl.l("import io.vertx.pgclient.PgPool;");
//			auteurGenApiService.l("import ", classeNomEnsemble, ".", classeNomSimple, "ApiServiceVertxEBProxy;");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.l("/**");
			auteurApiServiceImpl.l(" * ", str_Traduire(classeLangueNom), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueApiServiceImplLangue = (String)classeDoc.get("classeNomCanoniqueApiServiceImpl_" + langueNom + "_stored_string");
				auteurApiServiceImpl.l(" * ", str_NomCanonique(classeLangueNom), ".", langueNom, ": ", classeNomCanoniqueApiServiceImplLangue);
			}
			auteurApiServiceImpl.l(" **/");
			auteurApiServiceImpl.l("public class ", classeNomSimpleApiServiceImpl, " extends ", classeNomSimpleGenApiServiceImpl, " {");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.tl(1, "public ", classeNomSimpleApiServiceImpl, "(EventBus eventBus, JsonObject config, WorkerExecutor ", str_executeurTravailleur(classeLangueNom), ", PgPool pgPool, SolrClient ", str_clientSolr(classeLangueNom), activerOpenIdConnect ? ", OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider" : "", ") {");
			auteurApiServiceImpl.tl(2, "super(eventBus, config, ", str_executeurTravailleur(classeLangueNom), ", pgPool, ", str_clientSolr(classeLangueNom), activerOpenIdConnect ? ", oauth2AuthenticationProvider, authorizationProvider" : "", ");");
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
	public void ecrireGenApiServiceImpl1(String classeLangueNom) throws Exception {
		classeAutresLangues = ArrayUtils.removeAllOccurences(toutesLangues, classeLangueNom);

		if(auteurGenApiServiceImpl != null) {
			o = auteurGenApiServiceImpl;
	
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
								wApiGet.tl(6, "case \"", entiteVar, "\":");
								wApiGet.tl(7, "return \"", entiteVar, "_indexed", entiteSuffixeType, "\";");
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
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Boolean)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueDate.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueTimestamp.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ").toInstant().atZone(ZoneId.systemDefault()).toZonedDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueLocalDate.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Long)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(BigDecimal.valueOf((Double)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Double)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Float)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else if(VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(((Integer)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ").toString());");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
									else {
										l();
										tl(7, "{");
										tl(8, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(8, "w.t(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(8, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(8, "for(int k = 0; k < ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(9, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), str_Valeurs(classeLangueNom), ".get(k);");
										tl(9, "if(k > 0)");
										tl(10, "w.s(\", \");");
										tl(9, "w.s(\"\\\"\");");
										tl(9, "w.s(((String)", str_entite(classeLangueNom), str_Valeur(classeLangueNom), "));");
										tl(9, "w.s(\"\\\"\");");
										tl(8, "}");
										tl(8, "w.l(\"]\");");
										tl(7, "}");
									}
								}
								else {
									l();
									tl(7, str_entite(classeLangueNom), str_Valeur(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
									tl(7, "if(", str_entite(classeLangueNom), str_Valeur(classeLangueNom), " != null)");
									if (VAL_nomCanoniqueBoolean.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ");");
									} else if (VAL_nomCanoniqueDate.equals(entiteSolrNomCanonique)) {
										if (VAL_nomCanoniqueTimestamp.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), str_Valeur(classeLangueNom), "));");
										} else if (VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), str_Valeur(classeLangueNom), "));");
										} else if (VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), str_Valeur(classeLangueNom), "));");
										} else if (VAL_nomCanoniqueLocalDate.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), str_Valeur(classeLangueNom), "));");
										} else {
											tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), str_Valeur(classeLangueNom), "));");
										}
									} else if (VAL_nomCanoniqueLong.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ");");
									} else if (VAL_nomCanoniqueDouble.equals(entiteSolrNomCanonique)) {
										if (VAL_nomCanoniqueBigDecimal.equals(entiteNomCanonique)) {
											tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ");");
										}
										else {
											tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ");");
										}
									} else if (VAL_nomCanoniqueFloat.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ");");
									} else if (VAL_nomCanoniqueInteger.equals(entiteSolrNomCanonique)) {
										tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), str_Valeur(classeLangueNom), ");");
									}
									else {
										tl(8, "w.tl(3, ", str_entite(classeLangueNom), str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), str_Valeur(classeLangueNom), "));");
									}
								}
							}
//	
//							Integer tBase = 3;

							////////////////////////
							// codeApiGenererPost //
							////////////////////////
							o = wApiGenererPost;
					
							if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir) && BooleanUtils.isTrue(entiteModifier)) {
								tl(5, "case \"", entiteVar, "\":");
								if(sqlTables) {
									tl(6, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", str_entite(classeLangueNom), "Var));");
									tl(6, "if(bParams.size() > 0) {");
									tl(7, "bSql.append(\", \");");
									tl(6, "}");
									tl(6, "bSql.append(\"", entiteVar, "=$\" + num);");
									tl(6, "num++;");
									tl(6, "bParams.add(o2.sql", entiteVarCapitalise, "());");
								} else {
									tl(6, "futures.add(Future.future(a -> {");
									tl(7, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD)");
					
									tl(9, ".execute(Tuple.of(", classeVarClePrimaire, ", \"", entiteVar, "\", Optional.ofNullable(jsonObject.getValue(", str_entite(classeLangueNom), "Var)).map(s -> s.toString()).orElse(null))");
	
									tl(9, ", b");
									tl(7, "-> {");
									tl(8, "if(b.succeeded())");
									tl(9, "a.handle(Future.succeededFuture());");
									tl(8, "else");
									tl(9, "a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
									tl(7, "});");
									tl(6, "}));");
								}
				
								tl(6, "break;");
							}	
							if(classeSauvegarde && BooleanUtils.isTrue(entiteAttribuer)) {
								tl(5, "case \"", entiteVar, "\":");
								if(entiteListeTypeJson == null) {
									if(StringUtils.compare(entiteVar, entiteAttribuerVar) < 0) {
										tl(6, "{");
										tl(7, "Long l = Long.parseLong(jsonObject.getString(", str_entite(classeLangueNom), "Var));");
										tl(7, "if(l != null) {");
										tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
										tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
										tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
										tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
										if(activerSupprime)
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
										if(activerArchive)
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
										tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
										tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
										tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
										tl(8, "if(l2 != null) {");
										if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
											// no list, no list, <
											tl(9, "if(bParams.size() > 0) {");
											tl(10, "bSql.append(\", \");");
											tl(9, "}");
											tl(9, "bSql.append(\"", entiteVar, "=$\" + num);");
											tl(9, "num++;");
											tl(9, "bParams.add(l2);");
										} else if(sqlTables && !"array".equals(entiteTypeJson)) {
											// no list, list, <
											tl(9, "if(bParams.size() > 0) {");
											tl(10, "bSql.append(\", \");");
											tl(9, "}");
											tl(9, "bSql.append(\"", entiteVar, "=$\" + num);");
											tl(9, "num++;");
											tl(9, "bParams.add(l2);");
										} else {
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "(pk1, pk2) values($1, $2)\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", \"", entiteVar, "\", l2, \"", entiteAttribuerVar, "\")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
										}
										tl(9, "if(!pks.contains(l2)) {");
										tl(10, "pks.add(l2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(8, "}");
										tl(7, "}");
										tl(6, "}");
									}
									else {
										tl(6, "{");
										tl(7, "Long l = Long.parseLong(jsonObject.getString(", str_entite(classeLangueNom), "Var));");
										tl(7, "if(l != null) {");
										tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
										tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
										tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
										tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
										if(activerSupprime)
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
										if(activerArchive)
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
										tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
										tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
										tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
										tl(8, "if(l2 != null) {");
										if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
											// no list, no list, >
											tl(9, "futures.add(Future.future(a -> {");
											tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE pk=$2\")");
											tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
										} else if(sqlTables && !"array".equals(entiteTypeJson)) {
											// no list, list, >
											tl(9, "if(bParams.size() > 0) {");
											tl(10, "bSql.append(\", \");");
											tl(9, "}");
											tl(9, "bSql.append(\"", entiteVar, "=$\" + num);");
											tl(9, "num++;");
											tl(9, "bParams.add(l2);");
										} else {
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), "(pk1, pk2) values($1, $2)\")");
												tl(12,".execute(Tuple.of(l2, ", classeVarClePrimaire, ")");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
												tl(12,".execute(Tuple.of(l2, \"", entiteAttribuerVar, "\", ",  classeVarClePrimaire, ", \"", entiteVar, "\")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
										}
										tl(9, "if(!pks.contains(l2)) {");
										tl(10, "pks.add(l2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(8, "}");
										tl(7, "}");
										tl(6, "}");
									}
								}
								else {
									if(StringUtils.compare(entiteVar, entiteAttribuerVar) < 0) {
										tl(6, "for(Long l : Optional.ofNullable(jsonObject.getJsonArray(", str_entite(classeLangueNom), "Var)).orElse(new JsonArray()).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {");
										tl(7, "if(l != null) {");
										tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
										tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
										tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
										tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
										if(activerSupprime)
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
										if(activerArchive)
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
										tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
										tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
										tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
										tl(8, "if(l2 != null) {");
										tl(9, "futures.add(Future.future(a -> {");
										if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
											// list, list, <
											tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "(pk1, pk2) values($1, $2)\")");
											tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
										} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
											// list, no list, <
											tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
											tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
										} else {
											tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
											tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", \"", entiteVar, "\", l2, \"", entiteAttribuerVar, "\")");
										}
										tl(12,", b");
										tl(10, "-> {");
										tl(11, "if(b.succeeded())");
										tl(12,"a.handle(Future.succeededFuture());");
										tl(11, "else");
										tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
										tl(10, "});");
										tl(9, "}));");
										tl(9, "if(!pks.contains(l2)) {");
										tl(10, "pks.add(l2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(8, "}");
										tl(7, "}");
										tl(6, "}");
									}
									else {
										tl(6, "for(Long l : Optional.ofNullable(jsonObject.getJsonArray(", str_entite(classeLangueNom), "Var)).orElse(new JsonArray()).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {");
										tl(7, "if(l != null) {");
										tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
										tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
										tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
										tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
										if(activerSupprime)
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
										if(activerArchive)
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
										tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
										tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
										tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
										tl(8, "if(l2 != null) {");
										tl(9, "futures.add(Future.future(a -> {");
										if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
											// list, list >
											tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), "(pk1, pk2) values($1, $2)\")");
											tl(12,".execute(Tuple.of(l2, ", classeVarClePrimaire, ")");
										} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
											// list, no list, >
											tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
											tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
										} else {
											tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
											tl(12,".execute(Tuple.of(l2, \"", entiteAttribuerVar, "\", ", classeVarClePrimaire, ", \"", entiteVar, "\")");
										}
										tl(12,", b");
										tl(10, "-> {");
										tl(11, "if(b.succeeded())");
										tl(12,"a.handle(Future.succeededFuture());");
										tl(11, "else");
										tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
										tl(10, "});");
										tl(9, "}));");
										tl(9, "if(!pks.contains(l2)) {");
										tl(10, "pks.add(l2);");
										tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
										tl(9, "}");
										tl(8, "}");
										tl(7, "}");
										tl(6, "}");
									}
								}
								tl(6, "break;");
							}	
					
							////////////////////////////
							// codeApiGenererPutCopie //
							////////////////////////////
							o = wApiGenererPutCopie;
				
							if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir) && BooleanUtils.isTrue(entiteModifier)) {
								tl(5, "case \"", entiteVar, "\":");
								if(sqlTables) {
									tl(6, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", str_entite(classeLangueNom), "Var));");
									tl(6, "if(bParams.size() > 0) {");
									tl(7, "bSql.append(\", \");");
									tl(6, "}");
									tl(6, "bSql.append(\"", entiteVar, "=$\" + num);");
									tl(6, "num++;");
									tl(6, "bParams.add(o2.sql", entiteVarCapitalise, "());");
								} else {
									tl(6, "futures.add(Future.future(a -> {");
									tl(7, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD)");
					
									tl(9, ".execute(Tuple.of(", classeVarClePrimaire, ", \"", entiteVar, "\", Optional.ofNullable(jsonObject.getValue(", str_entite(classeLangueNom), "Var)).map(s -> s.toString()).orElse(null))");
	
									tl(9, ", b");
									tl(7, "-> {");
									tl(8, "if(b.succeeded())");
									tl(9, "a.handle(Future.succeededFuture());");
									tl(8, "else");
									tl(9, "a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
									tl(7, "});");
									tl(6, "}));");
								}
				
								tl(6, "break;");
							}	
							if(classeSauvegarde && BooleanUtils.isTrue(entiteAttribuer)) {
								tl(5, "case \"", entiteVar, "\":");
								if(entiteListeTypeJson == null) {
									if(StringUtils.compare(entiteVar, entiteAttribuerVar) < 0) {
										tl(6, "{");
										tl(7, "Long l = Long.parseLong(jsonObject.getString(", str_entite(classeLangueNom), "Var));");
										tl(7, "if(l != null) {");
										if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
											// no list, no list, <
											tl(8, "if(bParams.size() > 0) {");
											tl(9, "bSql.append(\", \");");
											tl(8, "}");
											tl(8, "bSql.append(\"", entiteVar, "=$\" + num);");
											tl(8, "num++;");
											tl(8, "bParams.add(l);");
										} else if(sqlTables && !"array".equals(entiteTypeJson)) {
											// no list, list, <
											tl(8, "if(bParams.size() > 0) {");
											tl(9, "bSql.append(\", \");");
											tl(8, "}");
											tl(8, "bSql.append(\"", entiteVar, "=$\" + num);");
											tl(8, "num++;");
											tl(8, "bParams.add(l);");
										} else {
											tl(7, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(9, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "(pk1, pk2) values($1, $2)\")");
												tl(11,".execute(Tuple.of(", classeVarClePrimaire, ", l)");
											} else {
												tl(9, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
												tl(11,".execute(Tuple.of(", classeVarClePrimaire, ", \"", entiteVar, "\", l, \"", entiteAttribuerVar, "\")");
											}
											tl(11,", b");
											tl(9, "-> {");
											tl(10, "if(b.succeeded())");
											tl(11,"a.handle(Future.succeededFuture());");
											tl(10, "else");
											tl(11,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(9, "});");
											tl(8, "}));");
										}
										tl(7, "}");
										tl(6, "}");
									}
									else {
										tl(6, "{");
										tl(7, "Long l = Long.parseLong(jsonObject.getString(", str_entite(classeLangueNom), "Var));");
										tl(7, "if(l != null) {");
										if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
											// no list, no list, >
											tl(8, "futures.add(Future.future(a -> {");
											tl(9, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE pk=$2\")");
											tl(11,".execute(Tuple.of(", classeVarClePrimaire, ", l)");
											tl(11,", b");
											tl(9, "-> {");
											tl(10, "if(b.succeeded())");
											tl(11,"a.handle(Future.succeededFuture());");
											tl(10, "else");
											tl(11,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(9, "});");
											tl(8, "}));");
										} else if(sqlTables && !"array".equals(entiteTypeJson)) {
											// no list, list, >
											tl(8, "if(bParams.size() > 0) {");
											tl(9, "bSql.append(\", \");");
											tl(8, "}");
											tl(8, "bSql.append(\"", entiteVar, "=$\" + num);");
											tl(8, "num++;");
											tl(8, "bParams.add(l);");
										} else {
											tl(8, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(9, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), "(pk1, pk2) values($1, $2)\")");
												tl(11,".execute(Tuple.of(l, ", classeVarClePrimaire, ")");
											} else {
												tl(9, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
												tl(11,".execute(Tuple.of(l, \"", entiteAttribuerVar, "\", ",  classeVarClePrimaire, ", \"", entiteVar, "\")");
											}
											tl(11,", b");
											tl(9, "-> {");
											tl(10, "if(b.succeeded())");
											tl(11,"a.handle(Future.succeededFuture());");
											tl(10, "else");
											tl(11,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(9, "});");
											tl(8, "}));");
										}
										tl(7, "}");
										tl(6, "}");
									}
								}
								else {
									if(StringUtils.compare(entiteVar, entiteAttribuerVar) < 0) {
										tl(6, "{");
										tl(7, "for(Long l : Optional.ofNullable(jsonObject.getJsonArray(", str_entite(classeLangueNom), "Var)).orElse(new JsonArray()).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {");
										tl(8, "futures.add(Future.future(a -> {");
										if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
											// list, list, <
											tl(9, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "(pk1, pk2) values($1, $2)\")");
											tl(11,".execute(Tuple.of(", classeVarClePrimaire, ", l)");
										} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
											// list, no list, <
											tl(9, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
											tl(11,".execute(Tuple.of(", classeVarClePrimaire, ", l)");
										} else {
											tl(9, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
											tl(11,".execute(Tuple.of(", classeVarClePrimaire, ", \"", entiteVar, "\", l, \"", entiteAttribuerVar, "\")");
										}
										tl(11, ", b");
										tl(9, "-> {");
										tl(10, "if(b.succeeded())");
										tl(11, "a.handle(Future.succeededFuture());");
										tl(10, "else");
										tl(11, "a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
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
										tl(7, "for(Long l : Optional.ofNullable(jsonObject.getJsonArray(", str_entite(classeLangueNom), "Var)).orElse(new JsonArray()).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {");
										tl(8, "futures.add(Future.future(a -> {");
										if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
											// list, list >
											tl(9, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), "(pk1, pk2) values($1, $2)\")");
											tl(11,".execute(Tuple.of(l, ", classeVarClePrimaire, ")");
										} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
											// list, no list, >
											tl(9, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
											tl(11,".execute(Tuple.of(", classeVarClePrimaire, ", l)");
										} else {
											tl(9, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
											tl(11,".execute(Tuple.of(l, \"", entiteAttribuerVar, "\", ", classeVarClePrimaire, ", \"", entiteVar, "\")");
										}
										tl(11, ", b");
										tl(9, "-> {");
										tl(10, "if(b.succeeded())");
										tl(11, "a.handle(Future.succeededFuture());");
										tl(10, "else");
										tl(11, "a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
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
						
										if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
											tl(5, "case \"add", entiteVarCapitalise, "\":");
											tl(6, "{");
											tl(7, "Long l = Long.parseLong(jsonObject.get", entiteListeNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(7, "if(l != null) {");
											tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(8, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(8, "if(");
												tl(10, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(10, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(10, ") {");
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(11, "+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(8, "}");
											}
											tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(8, "if(l2 != null && !o.get", StringUtils.capitalize(entiteVar), "().contains(l2)) {");
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "(pk1, pk2) values($1, $2)\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", ", q(entiteVar), ", l2, ", q(entiteAttribuerVar), ")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
											tl(9, "if(!pks.contains(l2)) {");
											tl(10, "pks.add(l2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
											tl(6, "break;");
					
											tl(5, "case \"addAll", entiteVarCapitalise, "\":");
											tl(6, entiteNomSimpleVertxJson, " addAll", entiteVarCapitalise, str_Valeurs(classeLangueNom), " = jsonObject.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), ");");
											tl(6, "if(addAll", entiteVarCapitalise, str_Valeurs(classeLangueNom), " != null) {");
											tl(7, "for(Integer i = 0; i <  addAll", entiteVarCapitalise, str_Valeurs(classeLangueNom), ".size(); i++) {");
											tl(8, "Long l = Long.parseLong(addAll", entiteVarCapitalise, str_Valeurs(classeLangueNom), ".get", entiteListeNomSimpleVertxJson, "(i));");
											tl(8, "if(l != null) {");
											tl(9, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(9, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(9, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(9, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(9, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(9, "if(");
												tl(11, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(11, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(11, ") {");
												tl(10, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(12,"+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(9, "}");
											}
											tl(9, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(9, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(9, "if(l2 != null && !o.get", StringUtils.capitalize(entiteVar), "().contains(l2)) {");
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "(pk1, pk2) values($1, $2)\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", ", q(entiteVar), ", l2, ", q(entiteAttribuerVar), ")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
											tl(10, "if(!pks.contains(l2)) {");
											tl(11, "pks.add(l2);");
											tl(11, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(10, "}");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
											tl(6, "break;");
						
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, entiteNomSimpleVertxJson, " set", entiteVarCapitalise, str_Valeurs(classeLangueNom), " = jsonObject.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), ");");
											tl(6, entiteNomSimpleVertxJson, " set", entiteVarCapitalise, str_Valeurs(classeLangueNom), "2 = new ", entiteNomSimpleVertxJson, "();");
											tl(6, "if(set", entiteVarCapitalise, str_Valeurs(classeLangueNom), " != null) {");
											tl(7, "for(Integer i = 0; i <  set", entiteVarCapitalise, str_Valeurs(classeLangueNom), ".size(); i++) {");
											tl(8, "Long l = Long.parseLong(set", entiteVarCapitalise, str_Valeurs(classeLangueNom), ".get", entiteListeNomSimpleVertxJson, "(i));");
											tl(8, "if(l != null) {");
											tl(9, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(9, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(9, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(9, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(9, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(9, "if(");
												tl(11, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(11, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(11, ") {");
												tl(10, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(12,"+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(9, "}");
											}
											tl(9, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(9, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(9, "if(l2 != null)");
											tl(10, "set", entiteVarCapitalise, str_Valeurs(classeLangueNom), "2.add(l2);");
											tl(9, "if(l2 != null && !o.get", StringUtils.capitalize(entiteVar), "().contains(l2)) {");
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "(pk1, pk2) values($1, $2)\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", ", q(entiteVar), ", l2, ", q(entiteAttribuerVar), ")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
											tl(10, "if(!pks.contains(l2)) {");
											tl(11, "pks.add(l2);");
											tl(11, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(10, "}");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
											tl(6, "if(o.get", entiteVarCapitalise, "() != null) {");
											tl(7, "for(Long l :  o.get", entiteVarCapitalise, "()) {");
											tl(8, "if(l != null && (set", entiteVarCapitalise, str_Valeurs(classeLangueNom), "2 == null || !set", entiteVarCapitalise, str_Valeurs(classeLangueNom), "2.contains(l))) {");
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"DELETE FROM ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), " WHERE pk1=$1 AND pk2=$2\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l)");
											} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=null WHERE ", classeVarClePrimaire, "=$1\")");
												tl(12,".execute(Tuple.of(l)");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeA)");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", ", q(entiteVar), ", l, ", q(entiteAttribuerVar), ")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
											tl(6, "break;");
							
											tl(5, "case \"remove", entiteVarCapitalise, "\":");
											tl(6, "{");
											tl(7, "Long l = Long.parseLong(jsonObject.getString(", str_methodeNom(classeLangueNom), "));");
											tl(7, "if(l != null) {");
											tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(8, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(8, "if(");
												tl(10, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(10, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(10, ") {");
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(11, "+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(8, "}");
											}
											tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(8, "if(l2 != null && o.get", StringUtils.capitalize(entiteVar), "().contains(l2)) {");
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"DELETE FROM ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), " WHERE pk1=$1 AND pk2=$2\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=null WHERE ", classeVarClePrimaire, "=$1\")");
												tl(12,".execute(Tuple.of(l2)");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeA)");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", ", q(entiteVar), ", l2, ", q(entiteAttribuerVar), ")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
											tl(9, "if(!pks.contains(l2)) {");
											tl(10, "pks.add(l2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
											tl(6, "break;");
										}
										else {
											tl(5, "case \"add", entiteVarCapitalise, "\":");
											tl(6, "{");
											tl(7, "Long l = Long.parseLong(jsonObject.get", entiteListeNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(7, "if(l != null) {");
											tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(8, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(8, "if(");
												tl(10, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(10, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(10, ") {");
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(11, "+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(8, "}");
											}
											tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(8, "if(l2 != null && !o.get", StringUtils.capitalize(entiteVar), "().contains(l2)) {");
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), "(pk1, pk2) values($1, $2)\")");
												tl(12,".execute(Tuple.of(l2, ", classeVarClePrimaire, ")");
											} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
												tl(12,".execute(Tuple.of(l2, ", q(entiteAttribuerVar), ", ", classeVarClePrimaire, ", ", q(entiteVar), ")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
											tl(9, "if(!pks.contains(l2)) {");
											tl(10, "pks.add(l2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
											tl(6, "break;");
					
											tl(5, "case \"addAll", entiteVarCapitalise, "\":");
											tl(6, entiteNomSimpleVertxJson, " addAll", entiteVarCapitalise, str_Valeurs(classeLangueNom), " = jsonObject.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), ");");
											tl(6, "if(addAll", entiteVarCapitalise, str_Valeurs(classeLangueNom), " != null) {");
											tl(7, "for(Integer i = 0; i <  addAll", entiteVarCapitalise, str_Valeurs(classeLangueNom), ".size(); i++) {");
											tl(8, "Long l = Long.parseLong(addAll", entiteVarCapitalise, str_Valeurs(classeLangueNom), ".get", entiteListeNomSimpleVertxJson, "(i));");
											tl(8, "if(l != null) {");
											tl(9, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(9, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(9, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(9, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(9, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(9, "if(");
												tl(11, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(11, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(11, ") {");
												tl(10, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(12,"+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(9, "}");
											}
											tl(9, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(9, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(9, "if(l2 != null && !o.get", StringUtils.capitalize(entiteVar), "().contains(l2)) {");
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), "(pk1, pk2) values($1, $2)\")");
												tl(12,".execute(Tuple.of(l2, ", classeVarClePrimaire, ")");
											} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
												tl(12,".execute(Tuple.of(l2, ", q(entiteAttribuerVar), ", ", classeVarClePrimaire, ", ", q(entiteVar), ")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
											tl(10, "if(!pks.contains(l2)) {");
											tl(11, "pks.add(l2);");
											tl(11, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(10, "}");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
											tl(6, "break;");
						
											tl(5, "case \"set", entiteVarCapitalise, "\":");
											tl(6, entiteNomSimpleVertxJson, " set", entiteVarCapitalise, str_Valeurs(classeLangueNom), " = jsonObject.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), ");");
											tl(6, entiteNomSimpleVertxJson, " set", entiteVarCapitalise, str_Valeurs(classeLangueNom), "2 = new ", entiteNomSimpleVertxJson, "();");
											tl(6, "if(set", entiteVarCapitalise, str_Valeurs(classeLangueNom), " != null) {");
											tl(7, "for(Integer i = 0; i <  set", entiteVarCapitalise, str_Valeurs(classeLangueNom), ".size(); i++) {");
											tl(8, "Long l = Long.parseLong(set", entiteVarCapitalise, str_Valeurs(classeLangueNom), ".get", entiteListeNomSimpleVertxJson, "(i));");
											tl(8, "if(l != null) {");
											tl(9, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(9, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(9, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(9, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(9, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(9, "if(");
												tl(11, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(11, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(11, ") {");
												tl(10, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(12,"+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(9, "}");
											}
											tl(9, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(9, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(9, "if(l2 != null)");
											tl(10, "set", entiteVarCapitalise, str_Valeurs(classeLangueNom), "2.add(l2);");
											tl(9, "if(l2 != null && !o.get", StringUtils.capitalize(entiteVar), "().contains(l2)) {");
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), "(pk1, pk2) values($1, $2)\")");
												tl(12,".execute(Tuple.of(l2, ", classeVarClePrimaire, ")");
											} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=$1 WHERE ", classeVarClePrimaire, "=$2\")");
												tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
												tl(12,".execute(Tuple.of(l2, ", q(entiteAttribuerVar), ", ", classeVarClePrimaire, ", ", q(entiteVar), ")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
											tl(10, "if(!pks.contains(l2)) {");
											tl(11, "pks.add(l2);");
											tl(11, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(10, "}");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
											tl(6, "if(o.get", entiteVarCapitalise, "() != null) {");
											tl(7, "for(Long l :  o.get", entiteVarCapitalise, "()) {");
											tl(8, "if(l != null && (set", entiteVarCapitalise, str_Valeurs(classeLangueNom), " == null || !set", entiteVarCapitalise, str_Valeurs(classeLangueNom), "2.contains(l))) {");
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"DELETE FROM ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), " WHERE pk1=$1 AND pk2=$2\")");
												tl(12,".execute(Tuple.of(l, ", classeVarClePrimaire, ")");
											} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=null WHERE ", classeVarClePrimaire, "=$1\")");
												tl(12,".execute(Tuple.of(l)");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeA)");
												tl(12,".execute(Tuple.of(l, ", q(entiteAttribuerVar), ", ", classeVarClePrimaire, ", ", q(entiteVar), ")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
											tl(6, "break;");
							
											tl(5, "case \"remove", entiteVarCapitalise, "\":");
											tl(6, "{");
											tl(7, "Long l = Long.parseLong(jsonObject.getString(", str_methodeNom(classeLangueNom), "));");
											tl(7, "if(l != null) {");
											tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(8, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(8, "if(");
												tl(10, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(10, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(10, ") {");
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(11, "+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(8, "}");
											}
											tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(8, "if(l2 != null && o.get", StringUtils.capitalize(entiteVar), "().contains(l2)) {");
											tl(9, "futures.add(Future.future(a -> {");
											if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"DELETE FROM ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), " WHERE pk1=$1 AND pk2=$2\")");
												tl(12,".execute(Tuple.of(l2, ", classeVarClePrimaire, ")");
											} else if(sqlTables && !"array".equals(entiteAttribuerTypeJson)) {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"UPDATE ", entiteAttribuerNomSimple, " SET ", entiteAttribuerVar, "=null WHERE ", classeVarClePrimaire, "=$1\")");
												tl(12,".execute(Tuple.of(l2)");
											} else {
												tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeA)");
												tl(12,".execute(Tuple.of(l2", ", ", q(entiteAttribuerVar), ", ", classeVarClePrimaire, ", ", q(entiteVar), ")");
											}
											tl(12,", b");
											tl(10, "-> {");
											tl(11, "if(b.succeeded())");
											tl(12,"a.handle(Future.succeededFuture());");
											tl(11, "else");
											tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(10, "});");
											tl(9, "}));");
											tl(9, "if(!pks.contains(l2)) {");
											tl(10, "pks.add(l2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
											tl(6, "break;");
										}
									}
									else {
						
										tl(5, "case \"set", entiteVarCapitalise, "\":");
										if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
											tl(6, "{");
											tl(7, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(7, "Long l = o2.get", entiteVarCapitalise, "();");
											tl(7, "if(l != null) {");
											tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(8, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(8, "if(");
												tl(10, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(10, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(10, ") {");
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(11, "+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(8, "}");
											}
											tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(8, "if(l2 != null && !l2.equals(o.get", StringUtils.capitalize(entiteVar), "())) {");
											if(sqlTables && !"array".equals(entiteTypeJson)) {
												tl(9, "o2.set", entiteVarCapitalise, "(l2);");
												tl(9, "if(bParams.size() > 0)");
												tl(10, "bSql.append(\", \");");
												tl(9, "bSql.append(\"", entiteVar, "=$\" + num);");
												tl(9, "num++;");
												tl(9, "bParams.add(l2);");
											} else {
												tl(9, "futures.add(Future.future(a -> {");
												if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
													tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "(pk1, pk2) values($1, $2)\")");
													tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
												} else {
													tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
													tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", ", q(entiteVar), ", l2, ", q(entiteAttribuerVar), ")");
												}
												tl(12,", b");
												tl(10, "-> {");
												tl(11, "if(b.succeeded())");
												tl(12,"a.handle(Future.succeededFuture());");
												tl(11, "else");
												tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
												tl(10, "});");
												tl(9, "}));");
											}
											tl(9, "if(!pks.contains(l2)) {");
											tl(10, "pks.add(l2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
										}
										else {
											tl(6, "{");
											tl(7, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(7, "Long l = o2.get", entiteVarCapitalise, "();");
											tl(7, "if(l != null && !l.equals(o.get", StringUtils.capitalize(entiteVar), "())) {");
											tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(8, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(8, "if(");
												tl(10, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(10, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(10, ") {");
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(11, "+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(8, "}");
											}
											tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(8, "if(l2 != null) {");
											if(sqlTables && !"array".equals(entiteTypeJson)) {
												tl(9, "o2.set", entiteVarCapitalise, "(l2);");
												tl(9, "if(bParams.size() > 0)");
												tl(10, "bSql.append(\", \");");
												tl(9, "bSql.append(\"", entiteVar, "=$\" + num);");
												tl(9, "num++;");
												tl(9, "bParams.add(l2);");
											} else {
												tl(9, "futures.add(Future.future(a -> {");
												if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
													tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), "(pk1, pk2) values($1, $2)\")");
													tl(12,".execute(l2, Tuple.of(", classeVarClePrimaire, ")");
												} else {
													tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA)");
													tl(12,".execute(Tuple.of(l2", ", ", q(entiteAttribuerVar), ", ", classeVarClePrimaire, ", ", q(entiteVar), ")");
												}
												tl(12,", b");
												tl(10, "-> {");
												tl(11, "if(b.succeeded())");
												tl(12,"a.handle(Future.succeededFuture());");
												tl(11, "else");
												tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
												tl(10, "});");
												tl(9, "}));");
											}
											tl(9, "if(!pks.contains(l2)) {");
											tl(10, "pks.add(l2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
										}
										tl(6, "break;");
						
										tl(5, "case \"remove", entiteVarCapitalise, "\":");
										if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
											tl(6, "{");
											tl(7, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(7, "Long l = o2.get", entiteVarCapitalise, "();");
											tl(7, "if(l != null) {");
											tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(8, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(8, "if(");
												tl(10, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(10, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(10, ") {");
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(11, "+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(8, "}");
											}
											tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(8, "if(l2 != null) {");
											if(sqlTables && !"array".equals(entiteTypeJson)) {
												tl(9, "o2.set", entiteVarCapitalise, "((Long)null);");
												tl(9, "if(bParams.size() > 0)");
												tl(10, "bSql.append(\", \");");
												tl(9, "bSql.append(\"", entiteVar, "=null\");");
											} else {
												tl(9, "futures.add(Future.future(a -> {");
												if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
													tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"DELETE FROM ", classeNomSimple, StringUtils.capitalize(entiteVar), "_", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), " WHERE pk1=$1 AND pk2=$2\")");
													tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", l2)");
												} else {
													tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeA)");
													tl(12,".execute(Tuple.of(", classeVarClePrimaire, ", ", q(entiteVar), ", l2, ", q(entiteAttribuerVar), ")");
												}
												tl(12,", b");
												tl(10, "-> {");
												tl(11, "if(b.succeeded())");
												tl(12,"a.handle(Future.succeededFuture());");
												tl(11, "else");
												tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
												tl(10, "});");
												tl(9, "}));");
											}
											tl(9, "if(!pks.contains(l2)) {");
											tl(10, "pks.add(l2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
										}
										else {
											tl(6, "{");
											tl(7, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(7, "Long l = o2.get", entiteVarCapitalise, "();");
											tl(7, "if(l != null) {");
											tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", entiteAttribuerNomSimple, ">();");
											tl(8, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
											tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
											tl(8, str_listeRecherche(classeLangueNom), ".setC(", entiteAttribuerNomSimple, ".class);");
											if(activerSupprime)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
											if(activerArchive)
												tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
											tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery((inheritPk ? \"", classeVarInheritClePrimaire, "\" : \"", classeVarClePrimaire, "\") + \"_indexed_long:\" + l);");
											if(entiteAttribuerSessionEcrire || entiteAttribuerUtilisateurEcrire) {
												l();
												tl(8, "List<String> roles = Arrays.asList(\"", StringUtils.join(entiteAttribuerClasseRoles, "\", \""), "\");");
												tl(8, "if(");
												tl(10, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
												tl(10, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
												tl(10, ") {");
												tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
												tl(11, "+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
												tl(8, "}");
											}
											tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
											tl(8, "Long l2 = Optional.ofNullable(", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);");
											tl(8, "if(l2 != null) {");
											if(sqlTables && !"array".equals(entiteTypeJson)) {
												tl(9, "o2.set", entiteVarCapitalise, "((Long)null);");
												tl(9, "if(bParams.size() > 0)");
												tl(10, "bSql.append(\", \");");
												tl(9, "bSql.append(\"", entiteVar, "=null\");");
											} else {
												tl(9, "futures.add(Future.future(a -> {");
												if(sqlTables && "array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson)) {
													tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(\"DELETE FROM ", entiteAttribuerNomSimple, StringUtils.capitalize(entiteAttribuerVar), "_", classeNomSimple, StringUtils.capitalize(entiteVar), " WHERE pk1=$1 AND pk2=$2\")");
													tl(12,".execute(Tuple.of(l2, ", classeVarClePrimaire, ")");
												} else {
													tl(10, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeA)");
													tl(12,".execute(Tuple.of(l2", ", ", q(entiteAttribuerVar), ", ", classeVarClePrimaire, ", ", q(entiteVar), ")");
												}
												tl(12,", b");
												tl(10, "-> {");
												tl(11, "if(b.succeeded())");
												tl(12,"a.handle(Future.succeededFuture());");
												tl(11, "else");
												tl(12,"a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
												tl(10, "});");
												tl(9, "}));");
											}
											tl(9, "if(!pks.contains(l2)) {");
											tl(10, "pks.add(l2);");
											tl(10, "classes.add(\"", entiteAttribuerNomSimple, "\");");
											tl(9, "}");
											tl(8, "}");
											tl(7, "}");
											tl(6, "}");
										}
										tl(6, "break;");
									}
						
								}
								else if(BooleanUtils.isTrue(entiteDefinir) && BooleanUtils.isTrue(entiteModifier)) {
									if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
//						
//										tl(5, "case \"add", entiteVarCapitalise, "\":");
//										tl(6, "jsonObject.getJsonArray(", str_methodeNom(classeLangueNom), ").forEach((v) -> {");
//										tl(7, "o2.add", entiteVarCapitalise, "((", entiteListeNomSimpleVertxJson, ")v);");
//										tl(7, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA);");
//										tl(7, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", o2.get", entiteVarCapitalise, "()", ", ", classeVarClePrimaire, "));");
//										tl(6, "});");
						
										tl(5, "case \"set", entiteVarCapitalise, "\":");
										tl(6, "o2.get", entiteVarCapitalise, "().clear();");
										tl(6, "jsonObject.getJsonArray(", str_methodeNom(classeLangueNom), ").forEach((v) -> {");
										tl(7, "o2.add", entiteVarCapitalise, "((", entiteListeNomSimpleVertxJson, ")v);");
										tl(7, "futures.add(Future.future(a -> {");
										tl(8, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD)");
										tl(10, ".execute(Tuple.of(", classeVarClePrimaire, ", \"", entiteVar, "\", o2.json", entiteVarCapitalise, "())");
										tl(10, ", b");
										tl(8, "-> {");
										tl(9, "if(b.succeeded())");
										tl(10, "a.handle(Future.succeededFuture());");
										tl(9, "else");
										tl(10, "a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
										tl(8, "});");
										tl(7, "}));");
										tl(6, "});");
									}
									else {
						
										tl(5, "case \"set", entiteVarCapitalise, "\":");
										if(sqlTables) {
											tl(7, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(7, "if(bParams.size() > 0)");
											tl(8, "bSql.append(\", \");");
											tl(7, "bSql.append(\"", entiteVar, "=$\" + num);");
											tl(7, "num++;");
											tl(7, "bParams.add(o2.sql", entiteVarCapitalise, "());");
										} else {
											tl(6, "if(jsonObject.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), ") == null) {");
											tl(7, "futures.add(Future.future(a -> {");
											tl(8, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeD)");
											tl(10, ".execute(Tuple.of(", classeVarClePrimaire, ", \"", entiteVar, "\")");
											tl(10, ", b");
											tl(8, "-> {");
											tl(9, "if(b.succeeded())");
											tl(10, "a.handle(Future.succeededFuture());");
											tl(9, "else");
											tl(10, "a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "} else {");
											tl(7, "o2.set", entiteVarCapitalise, "(jsonObject.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(7, "futures.add(Future.future(a -> {");
											tl(8, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD)");
											tl(10, ".execute(Tuple.of(", classeVarClePrimaire, ", \"", entiteVar, "\", o2.json", entiteVarCapitalise, "())");
											tl(10, ", b");
											tl(8, "-> {");
											tl(9, "if(b.succeeded())");
											tl(10, "a.handle(Future.succeededFuture());");
											tl(9, "else");
											tl(10, "a.handle(Future.failedFuture(new Exception(\"", str_valeur(classeLangueNom), " ", classeNomSimple, ".", entiteVar, " ", str_a_échoué(classeLangueNom), "\", b.cause())));");
											tl(8, "});");
											tl(7, "}));");
											tl(6, "}");
										}
									}
						
									tl(6, "break;");
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
			l(" * ", str_Traduire(classeLangueNom), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueGenApiServiceImplLangue = (String)classeDoc.get("classeNomCanoniqueGenApiServiceImpl_" + langueNom + "_stored_string");
				l(" * ", str_NomCanonique(classeLangueNom), ".", langueNom, ": ", classeNomCanoniqueGenApiServiceImplLangue);
			}
			l(" **/");
			s("public class ", classeNomSimpleGenApiServiceImpl);
			s(" implements ", classeNomSimpleGenApiService);
			l(" {");
			l();
			tl(1, "protected static final Logger LOG = LoggerFactory.getLogger(", classeNomSimpleGenApiServiceImpl, ".class);");
			l();
			tl(1, "protected static final String SERVICE_ADDRESS = \"", classeNomSimpleApiServiceImpl, "\";");
			l();
			tl(1, "protected EventBus eventBus;");
			l();
			tl(1, "protected JsonObject config;");
			l();
			tl(1, "protected WorkerExecutor ", str_executeurTravailleur(classeLangueNom), ";");
			l();
			tl(1, "protected PgPool pgPool;");
			l();
			tl(1, "protected SolrClient ", str_clientSolr(classeLangueNom), ";");
			if(activerOpenIdConnect) {
				l();
				tl(1, "protected OAuth2Auth oauth2AuthenticationProvider;");
				l();
				tl(1, "protected AuthorizationProvider authorizationProvider;");
			}
			l();
			tl(1, "public ", classeNomSimpleGenApiServiceImpl, "(EventBus eventBus, JsonObject config, WorkerExecutor ", str_executeurTravailleur(classeLangueNom), ", PgPool pgPool, SolrClient ", str_clientSolr(classeLangueNom), activerOpenIdConnect ? ", OAuth2Auth oauth2AuthenticationProvider, AuthorizationProvider authorizationProvider" : "", ") {");
			tl(2, "this.eventBus = eventBus;");
			tl(2, "this.config = config;");
			tl(2, "this.", str_executeurTravailleur(classeLangueNom), " = ", str_executeurTravailleur(classeLangueNom), ";");
			tl(2, "this.pgPool = pgPool;");
			tl(2, "this.", str_clientSolr(classeLangueNom), " = ", str_clientSolr(classeLangueNom), ";");
			tl(2, "this.oauth2AuthenticationProvider = oauth2AuthenticationProvider;");
			tl(2, "this.authorizationProvider = authorizationProvider;");
			tl(1, "}");

			for(String classeApiMethode : classeApiMethodes) {
				String classePageNomCanoniqueMethode = (String)classeDoc.get("classePageNomCanonique" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classePageNomSimpleMethode = (String)classeDoc.get("classePageNomSimple" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiTypeMedia200Methode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
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
						l("ServiceRequest ", str_requeteService(classeLangueNom), ", Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, classeApiOperationIdMethode, "(", str_requeteService(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(1, "}");
					}

					/////////
					// API //
					/////////
					l();
					tl(1, "@Override");
					t(1, "public void ", classeApiOperationIdMethode, "(");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						s("JsonObject body, ");
					l("ServiceRequest ", str_requeteService(classeLangueNom), ", Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						tl(2, "LOG.info(String.format(\"", classeApiOperationIdMethode, " ", str_a_démarré(classeLangueNom), ". \"));");
	
					tl(2, str_utilisateur(classeLangueNom), classeNomSimple, "(", str_requeteService(classeLangueNom), ", b -> {");
					tl(3, "if(b.succeeded()) {");
					tl(4, "try {");
					tl(5, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = b.result();");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						tl(5, str_requeteSite(classeLangueNom), ".setJsonObject(body);");
					tl(5, str_requeteSite(classeLangueNom), ".set", str_RequeteUri(classeLangueNom), "(", q(classeApiUriMethode), ");");
					tl(5, str_requeteSite(classeLangueNom), ".set", str_RequeteMethode(classeLangueNom), "(", q(classeApiMethode), ");");
					if(
							StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH") 
								&& !(classeRoleSession || classeRoleUtilisateur)
								&& (
								classeRoles.size() > 0
							)
							|| !StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH") && (
								BooleanUtils.isNotTrue(classeRoleSession) 
								&& BooleanUtils.isNotTrue(classePublicLire) 
								&& BooleanUtils.isNotTrue(classeRoleUtilisateur) 
								&& ( classeRoles.size() > 0 || classeRoleLires.size() > 0)
							)
							) {
						l();
						tl(5, "List<String> roles = Arrays.asList(\"", StringUtils.join(classeRoles, "\", \""), "\");");
						if(StringUtils.containsAny(classeApiMethode, "GET", str_Recherche(classeLangueNom))) {
							tl(5, "List<String> ", str_roleLires(classeLangueNom), " = Arrays.asList(\"", StringUtils.join(classeRoleLires, "\", \""), "\");");
						}
						tl(5, "if(");
						tl(7, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
						tl(7, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
						if(StringUtils.containsAny(classeApiMethode, "GET", str_Recherche(classeLangueNom))) {
							tl(7, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), ", str_roleLires(classeLangueNom), ")");
							tl(7, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), ", str_roleLires(classeLangueNom), ")");
						}
						tl(7, ") {");
						tl(6, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(");
						tl(7, "new ServiceResponse(401, \"UNAUTHORIZED\", ");
						tl(8, "Buffer.buffer().appendString(");
						tl(9, "new JsonObject()");
						tl(10, ".put(\"errorCode\", \"401\")");
						tl(10, ".put(\"errorMessage\", \"", str_roles_requis(classeLangueNom), "\" + String.join(\", \", roles))");
						tl(10, ".encodePrettily()");
						tl(9, "), MultiMap.caseInsensitiveMultiMap()");
						tl(7, ")");
						tl(6, "));");
						tl(5, "} else {");
					}
					else {
						tl(5, "{");
					}

					if(classeApiMethode.contains("POST")) {

						tl(6, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(6, str_requeteApi(classeLangueNom), ".setRows(1);");
						tl(6, str_requeteApi(classeLangueNom), ".setNumFound(1L);");
						tl(6, str_requeteApi(classeLangueNom), ".setNumPATCH(0L);");
						tl(6, str_requeteApi(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsRequeteApi.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
						tl(6, str_requeteSite(classeLangueNom), ".set", str_RequeteApi(classeLangueNom), "_(", str_requeteApi(classeLangueNom), ");");
						tl(6, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", str_requeteApi(classeLangueNom), ").toString());");

						tl(6, classeApiOperationIdMethode, "Future(", str_requeteSite(classeLangueNom), ", false).onSuccess(", uncapitalizeClasseNomSimple, " -> {");
						tl(7, str_requeteApi(classeLangueNom), ".setPk(", uncapitalizeClasseNomSimple, ".getPk());");
						tl(7, classeApiOperationIdMethode, str_Reponse(classeLangueNom), "(", uncapitalizeClasseNomSimple, ", d -> {");
						tl(8, "if(d.succeeded()) {");
						tl(9, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
						tl(9, "LOG.info(String.format(\"", classeApiOperationIdMethode, " ", str_a_réussi(classeLangueNom), ". \"));");
						tl(8, "} else {");
						tl(9, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", d.cause()));");
						tl(9, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", d);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "}).onFailure(ex -> {");
						tl(7, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", Future.failedFuture(ex)));");
						tl(7, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", Future.failedFuture(ex));");
						tl(6, "});");
					}
					else if(classeApiMethode.contains("PATCH")) {
						tl(6, classeApiOperationIdMethode, str_Reponse(classeLangueNom), "(", str_requeteSite(classeLangueNom), ", c -> {");
						tl(7, "if(c.succeeded()) {");
						tl(8, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(c.result()));");
						tl(8, str_executeurTravailleur(classeLangueNom), ".executeBlocking(");
						tl(9, "blockingCodeHandler -> {");
						tl(10, "try {");
						tl(11, str_recherche(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", false, true, true, ", q(classeApiUriMethode), ", ", q(classeApiMethode), ", d -> {");
						tl(12, "if(d.succeeded()) {");
						tl(13, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple, " = d.result();");
						l();
						Integer tBase;
						if(activerRoleAdmin) {
							tBase = 1;
							tl(13, "List<String> roles2 = Arrays.asList(\"SiteAdmin\");");
							tl(13, "if(", str_liste(classeLangueNom), classeNomSimple, ".getQueryResponse().getResults().getNumFound() > 1");
							tl(15, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles2)");
							tl(15, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles2)");
							tl(15, ") {");
							tl(14, "String message = String.format(\"", str_roles_requis(classeLangueNom), "\" + String.join(\", \", roles2));");
							tl(14, "LOG.error(message);");
							tl(14, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", Future.failedFuture(message));");
							tl(13, "} else {");
							l();
						} else {
							tBase = 0;
						}
						tl(tBase + 13, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(tBase + 13, str_requeteApi(classeLangueNom), ".setRows(", str_liste(classeLangueNom), classeNomSimple, ".getRows());");
						tl(tBase + 13, str_requeteApi(classeLangueNom), ".setNumFound(", str_liste(classeLangueNom), classeNomSimple, ".getQueryResponse().getResults().getNumFound());");
						tl(tBase + 13, str_requeteApi(classeLangueNom), ".setNumPATCH(0L);");
						tl(tBase + 13, str_requeteApi(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsRequeteApi.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
						tl(tBase + 13, str_requeteSite(classeLangueNom), ".set", str_RequeteApi(classeLangueNom), "_(", str_requeteApi(classeLangueNom), ");");
						tl(tBase + 13, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", str_requeteApi(classeLangueNom), ").toString());");
						if(classeVarModifie == null) {
							tl(tBase + 13, "String dt = DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of(\"UTC\")).minusNanos(1000));");
						}
						else {
							tl(tBase + 13, "SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(", str_liste(classeLangueNom), classeNomSimple, ".getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get(\"facets\")).orElse(null);");
							tl(tBase + 13, "Date date = null;");
							tl(tBase + 13, "if(facets != null)");
							tl(tBase + 13, "date = (Date)facets.get(\"max_", classeVarModifie, "\");");
							tl(tBase + 13, "String dt;");
							tl(tBase + 13, "if(date == null)");
							tl(tBase + 14, "dt = DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of(\"UTC\")).minusNanos(1000));");
							tl(tBase + 13, "else");
							tl(tBase + 14, "dt = DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(\"UTC\")));");
							tl(tBase + 13, str_liste(classeLangueNom), classeNomSimple, ".addFilterQuery(String.format(\"", classeVarModifie, "_indexed_date:[* TO %s]\", dt));");
						}
						l();
						tl(tBase + 13, "try {");
						tl(tBase + 14, str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_liste(classeLangueNom), classeNomSimple, classeApiMethode.contains("PATCH") ? ", dt" : "", ", e -> {");
						tl(tBase + 15, "if(e.succeeded()) {");
						tl(tBase + 16, classeApiOperationIdMethode, str_Reponse(classeLangueNom), "(", str_requeteSite(classeLangueNom), ", f -> {");
						tl(tBase + 17, "if(f.succeeded()) {");
						tl(tBase + 18, "LOG.info(String.format(\"", classeApiOperationIdMethode, " ", str_a_réussi(classeLangueNom), ". \"));");
						tl(tBase + 18, "blockingCodeHandler.handle(Future.succeededFuture(f.result()));");
						tl(tBase + 17, "} else {");
						tl(tBase + 18, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", f.cause()));");
						tl(tBase + 18, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, f);");
						tl(tBase + 17, "}");
						tl(tBase + 16, "});");
						tl(tBase + 15, "} else {");
						tl(tBase + 16, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", e.cause()));");
						tl(tBase + 16, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, e);");
						tl(tBase + 15, "}");
						tl(tBase + 14, "});");
						tl(tBase + 13, "} catch(Exception ex) {");
						tl(tBase + 14, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(tBase + 14, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
						tl(tBase + 13, "}");
						if(activerRoleAdmin) {
							tl(13, "}");
						}
						tl(12, "} else {");
						tl(13, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", d.cause()));");
						tl(13, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, d);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} catch(Exception ex) {");
						tl(11, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(11, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
						tl(10, "}");
						tl(9, "}, resultHandler -> {");
						tl(9, "}");
						tl(8, ");");
						tl(7, "} else {");
						tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", c.cause()));");
						tl(8, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
						tl(7, "}");
						tl(6, "});");
					}
					else if(classeApiMethode.contains(str_Recherche(classeLangueNom))) {
						tl(6, str_recherche(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", false, true, false, ", q(classeApiUriMethode), ", ", q(classeApiMethode), ", c -> {");
						tl(7, "if(c.succeeded()) {");
						tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple, " = c.result();");
						tl(8, classeApiOperationIdMethode, str_Reponse(classeLangueNom), "(", str_liste(classeLangueNom), classeNomSimple, ", d -> {");
						tl(9, "if(d.succeeded()) {");
						tl(10, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
						tl(10, "LOG.info(String.format(\"", classeApiOperationIdMethode, " ", str_a_réussi(classeLangueNom), ". \"));");
						tl(9, "} else {");
						tl(10, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", d.cause()));");
						tl(10, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", d);");
						tl(9, "}");
						tl(8, "});");
						tl(7, "} else {");
						tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", c.cause()));");
						tl(8, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
						tl(7, "}");
						tl(6, "});");
					}
					else if(classeApiMethode.contains("GET")) {
						tl(6, str_recherche(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", false, true, false, ", q(classeApiUriMethode), ", ", q(classeApiMethode), ", c -> {");
						tl(7, "if(c.succeeded()) {");
						tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple, " = c.result();");
						tl(8, classeApiOperationIdMethode, str_Reponse(classeLangueNom), "(", str_liste(classeLangueNom), classeNomSimple, ", d -> {");
						tl(9, "if(d.succeeded()) {");
						tl(10, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
						tl(10, "LOG.info(String.format(\"", classeApiOperationIdMethode, " ", str_a_réussi(classeLangueNom), ". \"));");
						tl(9, "} else {");
						tl(10, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", d.cause()));");
						tl(10, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", d);");
						tl(9, "}");
						tl(8, "});");
						tl(7, "} else {");
						tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", c.cause()));");
						tl(8, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
						tl(7, "}");
						tl(6, "});");
					}
					else if(classeApiMethode.equals(str_PUTCopie(classeLangueNom))) {
						tl(6, classeApiOperationIdMethode, str_Reponse(classeLangueNom), "(", str_requeteSite(classeLangueNom), ", c -> {");
						tl(7, "if(c.succeeded()) {");
						tl(8, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(c.result()));");
						tl(8, str_executeurTravailleur(classeLangueNom), ".executeBlocking(");
						tl(9, "blockingCodeHandler -> {");
						tl(10, "try {");
						tl(11, str_recherche(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", false, true, true, ", q(classeApiUriMethode), ", ", q(classeApiMethode), ", d -> {");
						tl(12, "if(d.succeeded()) {");
						tl(13, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple, " = d.result();");
						tl(13, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(13, str_requeteApi(classeLangueNom), ".setRows(", str_liste(classeLangueNom), classeNomSimple, ".getRows());");
						tl(13, str_requeteApi(classeLangueNom), ".setNumFound(", str_liste(classeLangueNom), classeNomSimple, ".getQueryResponse().getResults().getNumFound());");
						tl(13, str_requeteApi(classeLangueNom), ".setNumPATCH(0L);");
						tl(13, str_requeteApi(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsRequeteApi.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
						tl(13, str_requeteSite(classeLangueNom), ".set", str_RequeteApi(classeLangueNom), "_(", str_requeteApi(classeLangueNom), ");");
						tl(13, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", str_requeteApi(classeLangueNom), ").toString());");
						tl(13, "try {");
						tl(14, str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_liste(classeLangueNom), classeNomSimple, classeApiMethode.contains("PATCH") ? ", dt" : "", ", e -> {");
						tl(15, "if(e.succeeded()) {");
						tl(16, classeApiOperationIdMethode, str_Reponse(classeLangueNom), "(", str_requeteSite(classeLangueNom), ", f -> {");
						tl(17, "if(f.succeeded()) {");
						tl(18, "LOG.info(String.format(\"", classeApiOperationIdMethode, " ", str_a_réussi(classeLangueNom), ". \"));");
						tl(18, "blockingCodeHandler.handle(Future.succeededFuture(f.result()));");
						tl(17, "} else {");
						tl(18, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", f.cause()));");
						tl(18, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, f);");
						tl(17, "}");
						tl(16, "});");
						tl(15, "} else {");
						tl(16, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", e.cause()));");
						tl(16, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, e);");
						tl(15, "}");
						tl(14, "});");
						tl(13, "} catch(Exception ex) {");
						tl(14, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(14, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
						tl(13, "}");
						tl(12, "} else {");
						tl(13, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", d.cause()));");
						tl(13, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, d);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} catch(Exception ex) {");
						tl(11, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(11, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
						tl(10, "}");
						tl(9, "}, resultHandler -> {");
						tl(9, "}");
						tl(8, ");");
						tl(7, "} else {");
						tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", c.cause()));");
						tl(8, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
						tl(7, "}");
						tl(6, "});");
					}
					else if(classeApiMethode.equals(str_PUTFusion(classeLangueNom)) || classeApiMethode.equals("PUTImport")) {
						tl(6, str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_requeteSite(classeLangueNom), ", c -> {");
						tl(7, "if(c.succeeded()) {");
						tl(8, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(c.result()));");
						tl(8, str_executeurTravailleur(classeLangueNom), ".executeBlocking(");
						tl(9, "blockingCodeHandler -> {");
						tl(10, "try {");
						tl(11, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(11, "JsonArray jsonArray = Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "()).map(o -> o.getJsonArray(\"list\")).orElse(new JsonArray());");
						tl(11, str_requeteApi(classeLangueNom), ".setRows(jsonArray.size());");
						tl(11, str_requeteApi(classeLangueNom), ".setNumFound(new Integer(jsonArray.size()).longValue());");
						tl(11, str_requeteApi(classeLangueNom), ".setNumPATCH(0L);");
						tl(11, str_requeteApi(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsRequeteApi.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
						tl(11, str_requeteSite(classeLangueNom), ".set", str_RequeteApi(classeLangueNom), "_(", str_requeteApi(classeLangueNom), ");");
						tl(11, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", str_requeteApi(classeLangueNom), ").toString());");
						tl(11, "vars", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", d -> {");
						tl(12, "if(d.succeeded()) {");
						tl(13, str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_requeteSite(classeLangueNom), ").onSuccess(e -> {");
						tl(14, str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_requeteSite(classeLangueNom), ", f -> {");
						tl(15, "if(f.succeeded()) {");
						tl(16, "LOG.info(String.format(\"", classeApiOperationIdMethode, " ", str_a_réussi(classeLangueNom), ". \"));");
						tl(16, "blockingCodeHandler.handle(Future.succeededFuture(f.result()));");
						tl(15, "} else {");
						tl(16, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", f.cause()));");
						tl(16, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, f);");
						tl(15, "}");
						tl(14, "});");
						tl(13, "}).onFailure(ex -> {");
						tl(14, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(14, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
						tl(13, "});");
						tl(12, "} else {");
						tl(13, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", d.cause()));");
						tl(13, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, d);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} catch(Exception ex) {");
						tl(11, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(11, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
						tl(10, "}");
						tl(9, "}, resultHandler -> {");
						tl(9, "}");
						tl(8, ");");
						tl(7, "} else {");
						tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", c.cause()));");
						tl(8, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
						tl(7, "}");
						tl(6, "});");
					}
					else {
						tl(6, classeApiOperationIdMethode, str_Reponse(classeLangueNom), "(", str_requeteSite(classeLangueNom), ", c -> {");
						tl(7, "if(c.succeeded()) {");
						tl(8, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(c.result()));");
						tl(8, "LOG.info(String.format(\"", classeApiOperationIdMethode, " ", str_a_réussi(classeLangueNom), ". \"));");
						tl(7, "} else {");
						tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", c.cause()));");
						tl(8, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
						tl(7, "}");
						tl(6, "});");
					}

					tl(5, "}");
					tl(4, "} catch(Exception ex) {");
					tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", ex));");
					tl(5, str_erreur(classeLangueNom), classeNomSimple, "(null, ", str_gestionnaireEvenements(classeLangueNom), ", Future.failedFuture(ex));");
					tl(4, "}");
					tl(3, "} else {");
					if(activerOpenIdConnect) {
						tl(4, "if(\"Inactive Token\".equals(b.cause().getMessage())) {");
						tl(5, "try {");
						tl(6, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(new ServiceResponse(302, \"Found\", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, \"/", str_deconnexion(classeLangueNom), "?redirect_uri=\" + URLEncoder.encode(serviceRequest.getExtra().getString(\"uri\"), \"UTF-8\")))));");
						tl(5, "} catch(Exception ex) {");
						tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(6, str_erreur(classeLangueNom), classeNomSimple, "(null, ", str_gestionnaireEvenements(classeLangueNom), ", b);");
						tl(5, "}");
						tl(4, "} else {");
						tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", b.cause()));");
						tl(5, str_erreur(classeLangueNom), classeNomSimple, "(null, ", str_gestionnaireEvenements(classeLangueNom), ", b);");
						tl(4, "}");
					} else {
						tl(4, "LOG.error(String.format(\"", classeApiOperationIdMethode, " ", str_a_échoué(classeLangueNom), ". \", b.cause()));");
						tl(4, str_erreur(classeLangueNom), classeNomSimple, "(null, ", str_gestionnaireEvenements(classeLangueNom), ", b);");
					}
					tl(3, "}");
					tl(2, "});");
					tl(1, "}");
					l();
	
					///////////
					// Liste //
					///////////
					if(classeApiMethode.contains("PATCH")) {
						l();
						tl(1, "public void ", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple, classeApiMethode.contains("PATCH") ? ", String dt" : "", ", Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_liste(classeLangueNom), classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_();");
						tl(2, str_liste(classeLangueNom), classeNomSimple, ".getList().forEach(o -> {");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), "2 = ", str_generer(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), str_Pour(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "(), ", str_clientSolr(classeLangueNom), ", ", str_requeteSite(classeLangueNom), ".get", str_RequeteService(classeLangueNom), "(), ", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "());");
						tl(3, str_requeteSite(classeLangueNom), "2.set", str_RequeteApi(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_());");
						tl(3, "o.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), "2);");
						tl(3, "futures.add(");
						tl(4, classeApiOperationIdMethode, "Future(", classeApiMethode.contains("PATCH") ? "o" : (str_requeteSite(classeLangueNom) + "2, JsonObject.mapFrom(o)"), ", false).onFailure(ex -> {");
						tl(5, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), "2, ", str_gestionnaireEvenements(classeLangueNom), ", Future.failedFuture(ex));");
						tl(4, "})");
						tl(3, ");");
						tl(2, "});");
						tl(2, "CompositeFuture.all(futures).onComplete( a -> {");
						tl(3, "if(a.succeeded()) {");
						tl(4, "if(", str_liste(classeLangueNom), classeNomSimple, ".next(", classeApiMethode.contains("PATCH") ? "dt" : "" , ")) {");
						tl(5, str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_liste(classeLangueNom), classeNomSimple, classeApiMethode.contains("PATCH") ? ", dt" : "", ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(4, "} else {");
						tl(5, str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(4, "}");
						tl(3, "} else {");
						tl(4, "LOG.error(String.format(\"", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
						tl(4, str_erreur(classeLangueNom), classeNomSimple, "(", str_liste(classeLangueNom), classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_(), ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(3, "}");
						tl(2, "});");
						tl(1, "}");
					}

					if(classeApiMethode.equals(str_PUTCopie(classeLangueNom))) {
						l();
						tl(1, "public void ", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple, classeApiMethode.contains("PATCH") ? ", String dt" : "", ", Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_liste(classeLangueNom), classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_();");
						tl(2, str_liste(classeLangueNom), classeNomSimple, ".getList().forEach(o -> {");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), "2 = ", str_generer(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), str_Pour(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "(), ", str_clientSolr(classeLangueNom), ", ", str_requeteSite(classeLangueNom), ".get", str_RequeteService(classeLangueNom), "(), ", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "());");
						tl(3, str_requeteSite(classeLangueNom), "2.set", str_RequeteApi(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_());");
						tl(3, "o.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), "2);");
						tl(3, "futures.add(");
						tl(4, classeApiOperationIdMethode, "Future(", classeApiMethode.contains("PATCH") ? "o" : (str_requeteSite(classeLangueNom) + "2, JsonObject.mapFrom(o))"), ".onFailure(ex -> {");
						tl(5, "LOG.error(String.format(\"", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(5, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", Future.failedFuture(ex));");
						tl(4, "})");
						tl(3, ");");
						tl(2, "});");
						tl(2, "CompositeFuture.all(futures).onComplete( a -> {");
						tl(3, "if(a.succeeded()) {");
						tl(4, str_requeteApi(classeLangueNom), ".setNumPATCH(", str_requeteApi(classeLangueNom), ".getNumPATCH() + ", str_liste(classeLangueNom), classeNomSimple, ".size());");
						tl(4, "if(", str_liste(classeLangueNom), classeNomSimple, ".next(", classeApiMethode.contains("PATCH") ? "dt" : "" , ")) {");
						tl(5, str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_liste(classeLangueNom), classeNomSimple, classeApiMethode.contains("PATCH") ? ", dt" : "", ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(4, "} else {");
						tl(5, str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(4, "}");
						tl(3, "} else {");
						tl(4, "LOG.error(String.format(\"", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
						tl(4, str_erreur(classeLangueNom), classeNomSimple, "(", str_liste(classeLangueNom), classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_(), ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(3, "}");
						tl(2, "});");
						tl(1, "}");
					}
					else if(classeApiMethode.equals(str_PUTFusion(classeLangueNom)) || classeApiMethode.equals("PUTImport")) {
						l();
						tl(1, "public Future<Void> ", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), ", ", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ") {");
						tl(2, "Promise<Void> promise = Promise.promise();");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, "JsonArray jsonArray = Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "()).map(o -> o.getJsonArray(\"list\")).orElse(new JsonArray());");
						tl(2, "try {");
						tl(3, "jsonArray.forEach(obj -> {");
						tl(4, "JsonObject json = (JsonObject)obj;");
						if(classeApiMethode.equals(str_PUTFusion(classeLangueNom)) || classeApiMethode.equals("PUTImport")) {
							l();
							tl(4, "json.put(\"", classeVarInheritClePrimaire, "\", json.getValue(\"", classeVarClePrimaire, "\"));");
						}
						if(classeApiMethode.equals("PUTImport")) {
							l();
							tl(4, "json.put(\"", str_cree(classeLangueNom), "\", json.getValue(\"", str_cree(classeLangueNom), "\"));");
						}
						l();
						tl(4, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), "2 = ", str_generer(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), str_Pour(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "(), ", str_clientSolr(classeLangueNom), ", ", str_requeteSite(classeLangueNom), ".get", str_RequeteService(classeLangueNom), "(), json);");
						tl(4, str_requeteSite(classeLangueNom), "2.set", str_RequeteApi(classeLangueNom), "_(", str_requeteApi(classeLangueNom), ");");
						tl(4, str_requeteSite(classeLangueNom), "2.set", str_Requete(classeLangueNom), "Vars(", str_requeteSite(classeLangueNom), ".get", str_Requete(classeLangueNom), "Vars());");
						l();
						tl(4, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, ">();");
						tl(4, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
						tl(4, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
						tl(4, str_listeRecherche(classeLangueNom), ".setC(", classeNomSimple, ".class);");
						if(activerSupprime)
							tl(4, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_supprime(classeLangueNom), "_indexed_boolean:false\");");
						if(activerArchive)
							tl(4, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_archive(classeLangueNom), "_indexed_boolean:false\");");
						tl(4, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", classeApiMethode.equals("PUTImport") ? classeVarInheritClePrimaire : classeVarClePrimaire, "_indexed_long:\" + json.getString(\"", classeVarClePrimaire, "\"));");
						tl(4, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), str_PourClasse(classeLangueNom), "(", str_requeteSite(classeLangueNom), "2);");
						l();
						tl(4, "if(", str_listeRecherche(classeLangueNom), ".size() == 1) {");
						tl(5, classeNomSimple, " o = ", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null);");
						tl(5, "JsonObject json2 = new JsonObject();");
						tl(5, "for(String f : json.fieldNames()) {");
						tl(6, "json2.put(\"set\" + StringUtils.capitalize(f), json.getValue(f));");
						tl(5, "}");
						tl(5, "if(o != null) {");
						tl(6, "for(String f : Optional.ofNullable(o.get", str_Sauvegardes(classeLangueNom), "()).orElse(new ArrayList<>())) {");
						tl(7, "if(!json.fieldNames().contains(f))");
						tl(8, "json2.putNull(\"set\" + StringUtils.capitalize(f));");
						tl(6, "}");
						tl(6, str_requeteSite(classeLangueNom), "2.set", str_ObjetJson(classeLangueNom), "(json2);");
						tl(6, "futures.add(");
						tl(7, "patch", classeNomSimple, "Future(o, ", classeApiMethode.equals("PUTImport"), ").onFailure(ex -> {");
						tl(8, "LOG.error(String.format(\"", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(7, "})");
						tl(6, ");");
						tl(5, "}");
						tl(4, "} else {");
						tl(5, "futures.add(");
						tl(6, "post", classeNomSimple, "Future(", str_requeteSite(classeLangueNom), "2, ", classeApiMethode.equals("PUTImport"), ").onFailure(ex -> {");
						tl(7, "LOG.error(String.format(\"", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(6, "})");
						tl(5, ");");
						tl(4, "}");
						tl(3, "});");
						tl(3, "CompositeFuture.all(futures).onSuccess(a -> {");
						tl(4, str_requeteApi(classeLangueNom), ".setNumPATCH(", str_requeteApi(classeLangueNom), ".getNumPATCH() + 1);");
						tl(4, "promise.complete();");
						tl(3, "}).onFailure(ex -> {");
						tl(4, "LOG.error(String.format(\"", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(4, "promise.fail(ex);");
						tl(3, "});");
						tl(2, "} catch(Exception ex) {");
						tl(3, "LOG.error(String.format(\"", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(3, "promise.fail(ex);");
						tl(2, "}");
						tl(2, "return promise.future();");
						tl(1, "}");
					}

					////////////
					// Future //
					////////////
					if(StringUtils.containsAny(classeApiMethode, "POST", str_PUTCopie(classeLangueNom), "PATCH")) {
						l();
						t(1, "public Future<", classeNomSimple, "> ", classeApiOperationIdMethode, "Future(");
						if(StringUtils.contains(classeApiMethode, "POST"))
							s(classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Boolean inheritPk");
						else if(StringUtils.contains(classeApiMethode, "PUT"))
							s(classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", JsonObject jsonObject");
						else if(StringUtils.contains(classeApiMethode, "PATCH"))
							s(classeNomSimple, " o, Boolean inheritPk");
						else
							s(classeNomSimple, " ", uncapitalizeClasseNomSimple);
						l(") {");
						if(StringUtils.contains(classeApiMethode, "PATCH"))
							tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
						else if(!StringUtils.containsAny(classeApiMethode, "POST", "PUT"))
							tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", uncapitalizeClasseNomSimple, ".get", str_RequeteSite(classeLangueNom), "_();");
						tl(2, "Promise<", classeNomSimple, "> promise = Promise.promise();");
						l();
						tl(2, "try {");
	
						if(classeApiMethode.contains("POST")) {
							tl(3, "pgPool.withTransaction(", str_connexionSql(classeLangueNom), " -> {");
							tl(4, "Promise<", classeNomSimple, "> promise1 = Promise.promise();");
							tl(4, str_requeteSite(classeLangueNom), ".set", str_ConnexionSql(classeLangueNom), "(", str_connexionSql(classeLangueNom), ");");
							tl(4, str_creer(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", a -> {");
							tl(5, "if(a.succeeded()) {");
							tl(6, classeNomSimple, " ", uncapitalizeClasseNomSimple, " = a.result();");
							tl(6, "sql", classeApiMethode, classeNomSimple, "(", uncapitalizeClasseNomSimple, ", inheritPk, b -> {");
							tl(7, "if(b.succeeded()) {");
							tl(8, str_definir(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", c -> {");
							tl(9, "if(c.succeeded()) {");
							tl(10, str_attribuer(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", d -> {");
							tl(11, "if(d.succeeded()) {");
							tl(12, str_indexer(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", e -> {");
							tl(13, "if(e.succeeded()) {");
							tl(14, "promise1.complete(", uncapitalizeClasseNomSimple, ");");
							tl(13, "} else {");
							tl(14, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", e.cause()));");
							tl(14, "promise1.fail(e.cause());");
							tl(13, "}");
							tl(12, "});");
							tl(11, "} else {");
							tl(12, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", d.cause()));");
							tl(12, "promise1.fail(d.cause());");
							tl(11, "}");
							tl(10, "});");
							tl(9, "} else {");
							tl(10, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", c.cause()));");
							tl(10, "promise1.fail(c.cause());");
							tl(9, "}");
							tl(8, "});");
							tl(7, "} else {");
							tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", b.cause()));");
							tl(8, "promise1.fail(b.cause());");
							tl(7, "}");
							tl(6, "});");
							tl(5, "} else {");
							tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
							tl(6, "promise1.fail(a.cause());");
							tl(5, "}");
							tl(4, "});");
							tl(4, "return promise1.future();");
							tl(3, "}).onSuccess(a -> {");
							tl(4, str_requeteSite(classeLangueNom), ".set", str_ConnexionSql(classeLangueNom), "(null);");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "promise.fail(ex);");
							tl(4, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
							tl(3, "}).compose(", uncapitalizeClasseNomSimple, " -> {");
							tl(4, "Promise<", classeNomSimple, "> promise2 = Promise.promise();");
							tl(4, str_recharger(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", a -> {");
							tl(5, "if(a.succeeded()) {");
							tl(6, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_();");
							tl(6, "if(", str_requeteApi(classeLangueNom), " != null) {");
							tl(7, str_requeteApi(classeLangueNom), ".setNumPATCH(", str_requeteApi(classeLangueNom), ".getNumPATCH() + 1);");
							tl(7, uncapitalizeClasseNomSimple, ".", str_requeteApi(classeLangueNom), classeNomSimple, "();");
							tl(7, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", str_requeteApi(classeLangueNom), ").toString());");
							tl(6, "}");
							tl(6, "promise.complete(", uncapitalizeClasseNomSimple, ");");
							tl(5, "} else {");
							tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
							tl(6, "promise2.fail(a.cause());");
							tl(5, "}");
							tl(4, "});");
							tl(4, "return promise2.future();");
							tl(3, "}).onSuccess(", uncapitalizeClasseNomSimple, " -> {");
							tl(4, "promise.complete(", uncapitalizeClasseNomSimple, ");");
							tl(4, "LOG.info(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_réussi(classeLangueNom), ". \"));");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "promise.fail(ex);");
							tl(4, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, promise.future());");
							tl(3, "});");
						}
						else if(classeApiMethode.equals(str_PUTCopie(classeLangueNom))) {
							l();
							tl(3, "jsonObject.put(\"", str_sauvegardes(classeLangueNom), "\", Optional.ofNullable(jsonObject.getJsonArray(\"", str_sauvegardes(classeLangueNom), "\")).orElse(new JsonArray()));");
							tl(3, "JsonObject jsonPatch = Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "()).map(o -> o.getJsonObject(\"patch\")).orElse(new JsonObject());");
							tl(3, "jsonPatch.stream().forEach(o -> {");
							tl(4, "if(o.getValue() == null)");
							tl(5, "jsonObject.remove(o.getKey());");
							tl(4, "else");
							tl(5, "jsonObject.put(o.getKey(), o.getValue());");
							tl(4, "if(!jsonObject.getJsonArray(\"", str_sauvegardes(classeLangueNom), "\").contains(o.getKey()))");
							tl(5, "jsonObject.getJsonArray(\"", str_sauvegardes(classeLangueNom), "\").add(o.getKey());");
							tl(3, "});");
							l();
							tl(3, "pgPool.withTransaction(", str_connexionSql(classeLangueNom), " -> {");
							tl(4, "Promise<", classeNomSimple, "> promise1 = Promise.promise();");
							tl(4, str_requeteSite(classeLangueNom), ".set", str_ConnexionSql(classeLangueNom), "(", str_connexionSql(classeLangueNom), ");");

							tl(4, str_creer(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", a -> {");
							tl(5, "if(a.succeeded()) {");
							tl(6, classeNomSimple, " ", uncapitalizeClasseNomSimple, " = a.result();");
							tl(6, "sql", classeApiMethode, classeNomSimple, "(", uncapitalizeClasseNomSimple, ", jsonObject, b -> {");
							tl(7, "if(b.succeeded()) {");
							tl(8, str_definir(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", c -> {");
							tl(9, "if(c.succeeded()) {");
							tl(10, str_attribuer(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", d -> {");
							tl(11, "if(d.succeeded()) {");
							tl(12, str_indexer(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", e -> {");
							tl(13, "if(e.succeeded()) {");
							tl(14, "promise1.complete(", uncapitalizeClasseNomSimple, ");");
							tl(13, "} else {");
							tl(14, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", e.cause()));");
							tl(14, "promise1.fail(e.cause());");
							tl(13, "}");
							tl(12, "});");
							tl(11, "} else {");
							tl(12, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", d.cause()));");
							tl(12, "promise1.fail(d.cause());");
							tl(11, "}");
							tl(10, "});");
							tl(9, "} else {");
							tl(10, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", c.cause()));");
							tl(10, "promise1.fail(c.cause());");
							tl(9, "}");
							tl(8, "});");
							tl(7, "} else {");
							tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", b.cause()));");
							tl(8, "promise1.fail(b.cause());");
							tl(7, "}");
							tl(6, "});");
							tl(5, "} else {");
							tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
							tl(6, "promise1.fail(a.cause());");
							tl(5, "}");
							tl(4, "});");
							tl(4, "return promise1.future();");
							tl(3, "}).onSuccess(a -> {");
							tl(4, str_requeteSite(classeLangueNom), ".set", str_ConnexionSql(classeLangueNom), "(null);");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "promise.fail(ex);");
							tl(4, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
							tl(3, "}).compose(", uncapitalizeClasseNomSimple, " -> {");
							tl(4, "Promise<", classeNomSimple, "> promise2 = Promise.promise();");
							tl(4, str_recharger(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", a -> {");
							tl(5, "if(a.succeeded()) {");
							tl(6, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_();");
							tl(6, "if(", str_requeteApi(classeLangueNom), " != null) {");
							tl(7, str_requeteApi(classeLangueNom), ".setNumPATCH(", str_requeteApi(classeLangueNom), ".getNumPATCH() + 1);");
							tl(7, uncapitalizeClasseNomSimple, ".", str_requeteApi(classeLangueNom), classeNomSimple, "();");
							tl(7, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", str_requeteApi(classeLangueNom), ").toString());");
							tl(6, "}");
							tl(6, "promise.complete(", uncapitalizeClasseNomSimple, ");");
							tl(5, "} else {");
							tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
							tl(6, "promise2.fail(a.cause());");
							tl(5, "}");
							tl(4, "});");
							tl(4, "return promise2.future();");
							tl(3, "}).onSuccess(", uncapitalizeClasseNomSimple, " -> {");
							tl(4, "promise.complete(", uncapitalizeClasseNomSimple, ");");
							tl(4, "LOG.info(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_réussi(classeLangueNom), ". \"));");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "promise.fail(ex);");
							tl(4, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, promise.future());");
							tl(3, "});");
						}
						else if(classeApiMethode.contains("PATCH")) {
							tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_();");
							tl(3, "if(", str_requeteApi(classeLangueNom), " != null && ", str_requeteApi(classeLangueNom), ".getNumFound() == 1L) {");
							tl(4, str_requeteApi(classeLangueNom), ".setOriginal(o);");
							tl(4, str_requeteApi(classeLangueNom), ".setPk(o.getPk());");
							tl(3, "}");
							tl(3, "pgPool.withTransaction(", str_connexionSql(classeLangueNom), " -> {");
							tl(4, "Promise<", classeNomSimple, "> promise1 = Promise.promise();");
							tl(4, str_requeteSite(classeLangueNom), ".set", str_ConnexionSql(classeLangueNom), "(", str_connexionSql(classeLangueNom), ");");
							tl(4, "sql", classeApiMethode, classeNomSimple, "(o, inheritPk, a -> {");
							tl(5, "if(a.succeeded()) {");
							tl(6, classeNomSimple, " ", uncapitalizeClasseNomSimple, " = a.result();");
							tl(6, str_definir(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", c -> {");
							tl(7, "if(c.succeeded()) {");
							tl(8, str_attribuer(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", d -> {");
							tl(9, "if(d.succeeded()) {");
							tl(10, str_indexer(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", e -> {");
							tl(11, "if(e.succeeded()) {");
							tl(12, "promise1.complete(", uncapitalizeClasseNomSimple, ");");
							tl(11, "} else {");
							tl(12, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", e.cause()));");
							tl(12, "promise1.fail(e.cause());");
							tl(11, "}");
							tl(10, "});");
							tl(9, "} else {");
							tl(10, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", d.cause()));");
							tl(10, "promise1.fail(d.cause());");
							tl(9, "}");
							tl(8, "});");
							tl(7, "} else {");
							tl(8, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", c.cause()));");
							tl(8, "promise1.fail(c.cause());");
							tl(7, "}");
							tl(6, "});");
							tl(5, "} else {");
							tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
							tl(8, "promise1.fail(a.cause());");
							tl(5, "}");
							tl(4, "});");
							tl(4, "return promise1.future();");
							tl(3, "}).onSuccess(a -> {");
							tl(4, str_requeteSite(classeLangueNom), ".set", str_ConnexionSql(classeLangueNom), "(null);");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "promise.fail(ex);");
							tl(4, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
							tl(3, "}).compose(", uncapitalizeClasseNomSimple, " -> {");
							tl(4, "Promise<", classeNomSimple, "> promise2 = Promise.promise();");
							tl(4, str_recharger(classeLangueNom), classeNomSimple, "(", uncapitalizeClasseNomSimple, ", a -> {");
							tl(5, "if(a.succeeded()) {");
							tl(6, "if(", str_requeteApi(classeLangueNom), " != null) {");
							tl(7, str_requeteApi(classeLangueNom), ".setNumPATCH(", str_requeteApi(classeLangueNom), ".getNumPATCH() + 1);");
							tl(7, uncapitalizeClasseNomSimple, ".", str_requeteApi(classeLangueNom), classeNomSimple, "();");
							tl(7, "eventBus.publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", str_requeteApi(classeLangueNom), ").toString());");
							tl(6, "}");
							tl(6, "promise.complete(", uncapitalizeClasseNomSimple, ");");
							tl(5, "} else {");
							tl(6, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
							tl(6, "promise2.fail(a.cause());");
							tl(5, "}");
							tl(4, "});");
							tl(4, "return promise2.future();");
							tl(3, "}).onSuccess(", uncapitalizeClasseNomSimple, " -> {");
							tl(4, "promise.complete(", uncapitalizeClasseNomSimple, ");");
							tl(4, "LOG.info(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_réussi(classeLangueNom), ". \"));");
							tl(3, "}).onFailure(ex -> {");
							tl(4, "promise.fail(ex);");
							tl(4, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, promise.future());");
							tl(3, "});");
						}
						tl(2, "} catch(Exception ex) {");
						tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, "Future ", str_a_échoué(classeLangueNom), ". \"), ex);");
						tl(3, "promise.fail(ex);");
						tl(3, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, promise.future());");
						tl(2, "}");
						tl(2, "return promise.future();");
						tl(1, "}");
					}

					/////////
					// SQL //
					/////////
					if(classeApiMethode.contains("PATCH")) {
						l();
						tl(1, "public void sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, Boolean inheritPk, Handler<AsyncResult<", classeNomSimple, ">> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "try {");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
						tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_();");
						tl(3, "List<Long> pks = Optional.ofNullable(", str_requeteApi(classeLangueNom), ").map(r -> r.getPks()).orElse(new ArrayList<>());");
						tl(3, "List<String> classes = Optional.ofNullable(", str_requeteApi(classeLangueNom), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
						tl(3, "SqlConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(3, "Integer num = 1;");
						if(sqlTables) {
							tl(3, "StringBuilder bSql = new StringBuilder(\"UPDATE ", classeNomSimple, " SET \");");
							tl(3, "List<Object> bParams = new ArrayList<Object>();");
						}
						tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
						tl(3, "JsonObject jsonObject = ", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "();");
						tl(3, "Set<String> ", str_methodeNoms(classeLangueNom), " = jsonObject.fieldNames();");
						tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
						tl(3, "o2.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ");");
						tl(3, "List<Future> futures = new ArrayList<>();");
//						l();
//						if(!sqlTables) {
//							tl(3, "if(o.get", str_UtilisateurId(classeLangueNom), "() == null && ", str_requeteSite(classeLangueNom), ".get", str_UtilisateurId(classeLangueNom), "() != null) {");
//							tl(4, "futures.add(Future.future(a -> {");
//							tl(5, str_connexionSql(classePageLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD)");
//							tl(7, ".execute(Tuple.of(", classeVarClePrimaire, ", \"", str_utilisateurId(classeLangueNom), "\", ", str_requeteSite(classeLangueNom), ".get", str_UtilisateurId(classeLangueNom), "())");
//							tl(7, ", b");
//							tl(5, "-> {");
//							tl(6, "if(b.succeeded())");
//							tl(7, "a.handle(Future.succeededFuture());");
//							tl(6, "else");
//							tl(7, "a.handle(Future.failedFuture(b.cause()));");
//							tl(5, "});");
//							tl(4, "}));");
//							tl(3, "}");
//						}
//						tl(3, "if(o.get", str_UtilisateurCle(classeLangueNom), "() == null && ", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "() != null) {");
//						if(sqlTables) {
//							tl(4, "if(bParams.size() > 0)");
//							tl(5, "bSql.append(\", \");");
//							tl(4, "bSql.append(\"", str_utilisateurCle(classeLangueNom), "=$\" + num);");
//							tl(4, "num++;");
//							tl(4, "bParams.add(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "());");
//						} else {
//							tl(4, "futures.add(Future.future(a -> {");
//							tl(5, str_connexionSql(classePageLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD)");
//							tl(4, ".execute(Tuple.of(", classeVarClePrimaire, ", \"", str_utilisateurCle(classeLangueNom), "\", ", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "().toString())");
//							tl(7, ", b");
//							tl(5, "-> {");
//							tl(6, "if(b.succeeded())");
//							tl(7, "a.handle(Future.succeededFuture());");
//							tl(6, "else");
//							tl(7, "a.handle(Future.failedFuture(b.cause()));");
//							tl(5, "});");
//							tl(4, "}));");
//							if(classeEntiteVars.contains(str_utilisateurCles(classeLangueNom))) {
//								l();
//								tl(4, "JsonArray ", str_utilisateurCles(classeLangueNom), " = Optional.ofNullable(jsonObject.getJsonArray(\"add", str_UtilisateurCles(classeLangueNom), "\")).orElse(null);");
//								tl(4, "if(", str_utilisateurCles(classeLangueNom), " != null && !", str_utilisateurCles(classeLangueNom), ".contains(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "()))");
//								tl(5, str_utilisateurCles(classeLangueNom), ".add(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "().toString());");
//								tl(4, "else");
//								tl(5, "jsonObject.put(\"add", str_UtilisateurCles(classeLangueNom), "\", new JsonArray().add(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "().toString()));");
//							}
//						}
//						tl(3, "}");
						l();
						tl(3, "for(String ", str_methodeNom(classeLangueNom), " : ", str_methodeNoms(classeLangueNom), ") {");
						tl(4, "switch(", str_methodeNom(classeLangueNom), ") {");
						s(wApiGenererPatch.toString());
						tl(4, "}");
						tl(3, "}");
						if(sqlTables) {
							tl(3, "bSql.append(\" WHERE ", classeVarClePrimaire, "=$\" + num);");
							tl(3, "if(bParams.size() > 0) {");
							tl(4, "bParams.add(", classeVarClePrimaire, ");");
							tl(4, "num++;");
							tl(4, "futures.add(Future.future(a -> {");
							tl(5, str_connexionSql(classePageLangueNom), ".preparedQuery(bSql.toString())");
							tl(7, ".execute(Tuple.tuple(bParams)");
							tl(7, ", b");
							tl(5, "-> {");
							tl(6, "if(b.succeeded())");
							tl(7, "a.handle(Future.succeededFuture());");
							tl(6, "else");
							tl(7, "a.handle(Future.failedFuture(b.cause()));");
							tl(5, "});");
							tl(4, "}));");
							tl(3, "}");
						}
						tl(3, "CompositeFuture.all(futures).onComplete( a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, classeNomSimple, " o3 = new ", classeNomSimple, "();");
						tl(5, "o3.set", str_RequeteSite(classeLangueNom), "_(o.get", str_RequeteSite(classeLangueNom), "_());");
						tl(5, "o3.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(o3));");
						tl(4, "} else {");
//						tl(5, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(5, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(a.cause()));");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
						tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("POST")) {
						l();
						tl(1, "public void sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, Boolean inheritPk, Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "try {");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
						tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_();");
						tl(3, "List<Long> pks = Optional.ofNullable(", str_requeteApi(classeLangueNom), ").map(r -> r.getPks()).orElse(new ArrayList<>());");
						tl(3, "List<String> classes = Optional.ofNullable(", str_requeteApi(classeLangueNom), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
						tl(3, "SqlConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(3, "Integer num = 1;");
						if(sqlTables) {
							tl(3, "StringBuilder bSql = new StringBuilder(\"UPDATE ", classeNomSimple, " SET \");");
							tl(3, "List<Object> bParams = new ArrayList<Object>();");
						}
						tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
						tl(3, "JsonObject jsonObject = ", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "();");
						tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
						tl(3, "o2.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ");");
						tl(3, "List<Future> futures = new ArrayList<>();");
						if(activerSessionId) {
							l();
							tl(3, "if(", str_requeteSite(classeLangueNom), ".get", str_SessionId(classeLangueNom), "() != null) {");
							if(sqlTables) {
								tl(4, "if(bParams.size() > 0) {");
								tl(5, "bSql.append(\", \");");
								tl(4, "}");
								tl(4, "bSql.append(\"", str_sessionId(classeLangueNom), "=$\" + num);");
								tl(4, "num++;");
								tl(4, "bParams.add(", str_requeteSite(classeLangueNom), ".get", str_SessionId(classeLangueNom), "());");
							} else {
								tl(4, "futures.add(Future.future(a -> {");
								tl(5, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD)");
								tl(4, ".execute(Tuple.of(", classeVarClePrimaire, ", \"", str_sessionId(classeLangueNom), "\", ", str_requeteSite(classeLangueNom), ".get", str_SessionId(classeLangueNom), "())");
								tl(7, ", b");
								tl(5, "-> {");
								tl(6, "if(b.succeeded())");
								tl(7, "a.handle(Future.succeededFuture());");
								tl(6, "else");
								tl(7, "a.handle(Future.failedFuture(b.cause()));");
								tl(5, "});");
								tl(4, "}));");
							}
							tl(3, "}");
						}
						if(!sqlTables) {
							tl(3, "if(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurId(classeLangueNom), "() != null) {");
							tl(4, "if(bParams.size() > 0) {");
							tl(5, "bSql.append(\", \");");
							tl(4, "}");
							tl(4, "bSql.append(\"", str_utilisateurId(classeLangueNom), "=$\" + num);");
							tl(4, "num++;");
							tl(4, "bParams.add(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurId(classeLangueNom), "());");
							tl(4, "futures.add(Future.future(a -> {");
							tl(5, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD)");
							tl(4, ".execute(Tuple.of(", classeVarClePrimaire, ", \"", str_utilisateurId(classeLangueNom), "\", ", str_requeteSite(classeLangueNom), ".get", str_UtilisateurId(classeLangueNom), "())");
							tl(7, ", b");
							tl(5, "-> {");
							tl(6, "if(b.succeeded())");
							tl(7, "a.handle(Future.succeededFuture());");
							tl(6, "else");
							tl(7, "a.handle(Future.failedFuture(b.cause()));");
							tl(5, "});");
							tl(4, "}));");
							tl(3, "}");
						}
						if(activerUtilisateurCle) {
							tl(3, "if(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "() != null) {");
							if(sqlTables) {
								tl(4, "if(bParams.size() > 0) {");
								tl(5, "bSql.append(\", \");");
								tl(4, "}");
								tl(4, "bSql.append(\"", str_utilisateurCle(classeLangueNom), "=$\" + num);");
								tl(4, "num++;");
								tl(4, "bParams.add(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "());");
							} else {
								tl(4, "futures.add(Future.future(a -> {");
								tl(5, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD)");
								tl(4, ".execute(Tuple.of(", classeVarClePrimaire, ", \"", str_utilisateurCle(classeLangueNom), "\", ", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "().toString())");
								tl(7, ", b");
								tl(5, "-> {");
								tl(6, "if(b.succeeded())");
								tl(7, "a.handle(Future.succeededFuture());");
								tl(6, "else");
								tl(7, "a.handle(Future.failedFuture(b.cause()));");
								tl(5, "});");
								tl(4, "}));");
								if(classeEntiteVars.contains(str_utilisateurCles(classeLangueNom))) {
									l();
									tl(4, "JsonArray ", str_utilisateurCles(classeLangueNom), " = Optional.ofNullable(jsonObject.getJsonArray(\"", str_utilisateurCles(classeLangueNom), "\")).orElse(null);");
									tl(4, "if(", str_utilisateurCles(classeLangueNom), " != null && !", str_utilisateurCles(classeLangueNom), ".contains(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "()))");
									tl(5, str_utilisateurCles(classeLangueNom), ".add(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "().toString());");
									tl(4, "else");
									tl(5, "jsonObject.put(\"", str_utilisateurCles(classeLangueNom), "\", new JsonArray().add(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "().toString()));");
								}
							}
							tl(3, "}");
						}
						l();
						tl(3, "if(jsonObject != null) {");
						tl(4, "Set<String> ", str_entite(classeLangueNom), "Vars = jsonObject.fieldNames();");
						tl(4, "for(String ", str_entite(classeLangueNom), "Var : ", str_entite(classeLangueNom), "Vars) {");
						tl(5, "switch(", str_entite(classeLangueNom), "Var) {");
						s(wApiGenererPost.toString());
						tl(5, "}");
						tl(4, "}");
						tl(3, "}");
						if(sqlTables) {
							tl(3, "bSql.append(\" WHERE ", classeVarClePrimaire, "=$\" + num);");
							tl(3, "if(bParams.size() > 0) {");
							tl(3, "bParams.add(", classeVarClePrimaire, ");");
							tl(3, "num++;");
							tl(4, "futures.add(Future.future(a -> {");
							tl(5, str_connexionSql(classeLangueNom), ".preparedQuery(bSql.toString())");
							tl(7, ".execute(Tuple.tuple(bParams)");
							tl(7, ", b");
							tl(5, "-> {");
							tl(6, "if(b.succeeded())");
							tl(7, "a.handle(Future.succeededFuture());");
							tl(6, "else");
							tl(7, "a.handle(Future.failedFuture(b.cause()));");
							tl(5, "});");
							tl(4, "}));");
							tl(3, "}");
						}
						tl(3, "CompositeFuture.all(futures).onComplete( a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
						tl(4, "} else {");
						tl(5, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(a.cause()));");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
						tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.equals(str_PUTCopie(classeLangueNom))) {
						l();
						tl(1, "public void sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, JsonObject jsonObject, Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "try {");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
						tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_();");
						tl(3, "List<Long> pks = Optional.ofNullable(", str_requeteApi(classeLangueNom), ").map(r -> r.getPks()).orElse(new ArrayList<>());");
						tl(3, "List<String> classes = Optional.ofNullable(", str_requeteApi(classeLangueNom), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
						tl(3, "SqlConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(3, "Integer num = 1;");
						if(sqlTables) {
							tl(3, "StringBuilder bSql = new StringBuilder(\"UPDATE ", classeNomSimple, " SET \");");
							tl(3, "List<Object> bParams = new ArrayList<Object>();");
						}
						tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
						tl(3, "o2.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ");");
						tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
						tl(3, "List<Future> futures = new ArrayList<>();");
						l();
						tl(3, "if(jsonObject != null) {");
						tl(4, "JsonArray ", str_entite(classeLangueNom), "Vars = jsonObject.getJsonArray(\"", classeVarSauvegardes, "\");");
						tl(4, "for(Integer i = 0; i < ", str_entite(classeLangueNom), "Vars.size(); i++) {");
						tl(5, "String ", str_entite(classeLangueNom), "Var = ", str_entite(classeLangueNom), "Vars.getString(i);");
						tl(5, "switch(", str_entite(classeLangueNom), "Var) {");
						if(classeApiMethode.equals("PUTImport"))
							s(wApiGenererPutImport.toString());
						else if(classeApiMethode.equals(str_PUTFusion(classeLangueNom)))
							s(wApiGenererPutFusion.toString());
						else if(classeApiMethode.equals(str_PUTCopie(classeLangueNom)))
							s(wApiGenererPutCopie.toString());
						tl(5, "}");
						tl(4, "}");
						tl(3, "}");
						if(sqlTables) {
							tl(3, "bSql.append(\" WHERE ", classeVarClePrimaire, "=$\" + num);");
							tl(3, "if(bParams.size() > 0) {");
							tl(3, "bParams.add(", classeVarClePrimaire, ");");
							tl(3, "num++;");
							tl(4, "futures.add(Future.future(a -> {");
							tl(5, str_connexionSql(classeLangueNom), ".preparedQuery(bSql.toString())");
							tl(7, ".execute(Tuple.tuple(bParams)");
							tl(7, ", b");
							tl(5, "-> {");
							tl(6, "if(b.succeeded())");
							tl(7, "a.handle(Future.succeededFuture());");
							tl(6, "else");
							tl(7, "a.handle(Future.failedFuture(b.cause()));");
							tl(5, "});");
							tl(4, "}));");
							tl(3, "}");
						}
						tl(3, "CompositeFuture.all(futures).onComplete( a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
						tl(4, "} else {");
						tl(5, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(a.cause()));");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "LOG.error(String.format(\"sql", classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
						tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					l();

					//////////////
					// PageInit //
					//////////////

					if(classePageNomCanoniqueMethode != null) {
						tl(1, "public void ", classeApiOperationIdMethode, str_Page(classeLangueNom), "Init(", classePageNomSimpleMethode, " page, ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple, ") {");
						tl(1, "}");
					}

					/////////////
					// Reponse //
					/////////////
					if (!classeApiMethode.equals("PUTImport")){
						String var;
						String type;
						if(classeApiMethode.contains("POST")) {
							type = classeNomSimple;
							var = uncapitalizeClasseNomSimple;
						}
						else if(classeApiMethode.contains("PATCH") || classeApiMethode.equals("PUTImport") || classeApiMethode.equals(str_PUTFusion(classeLangueNom)) || classeApiMethode.equals(str_PUTCopie(classeLangueNom))) {
							type = classePartsRequeteSite.nomSimple(classeLangueNom);
							var = str_requeteSite(classeLangueNom);
						}
						else if(classeApiMethode.contains("GET") || classeApiMethode.contains(str_Recherche(classeLangueNom))) {
							type = classePartsListeRecherche.nomSimple(classeLangueNom) + "<" + classeNomSimple + ">";
							var = str_liste(classeLangueNom) + classeNomSimple;
						}
						else {
							type = classePartsRequeteSite.nomSimple(classeLangueNom);
							var = str_requeteSite(classeLangueNom);
						}

						t(1, "public void ", classeApiOperationIdMethode, str_Reponse(classeLangueNom), "(");
						s(type, " ", var);
						l(", Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						if(classeApiMethode.contains("POST") || classeApiMethode.contains("GET") || classeApiMethode.contains(str_Recherche(classeLangueNom)))
							tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", var, ".get", str_RequeteSite(classeLangueNom), "_();");

						tl(2, "try {");
						if("text/html".equals(classeApiTypeMedia200Methode)) {
							tl(3, "Buffer buffer = Buffer.buffer();");
							if(classePartsToutEcrivain == null)
								throw new RuntimeException(String.format("%s %s %s %s %s. ", str_classe(classeLangueNom), str_ToutEcrivain(classeLangueNom), str_manquante(classeLangueNom), str_dans(classeLangueNom), cheminSrcMainJava));
							tl(3, classePartsToutEcrivain.nomSimple(classeLangueNom), " w = ", classePartsToutEcrivain.nomSimple(classeLangueNom), ".", str_creer(classeLangueNom), "(", str_requeteSite(classeLangueNom), ", buffer);");
							tl(3, str_requeteSite(classeLangueNom), ".setW(w);");
//							tl(3, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
//							tl(3, "SolrDocument page", str_DocumentSolr(classeLangueNom), " = new SolrDocument();");
//							tl(3, "MultiMap ", str_requeteEnTetes(classeLangueNom), " = MultiMap.caseInsensitiveMultiMap();");
//							tl(3, str_requeteSite(classeLangueNom), ".set", str_RequeteEnTetes(classeLangueNom), "(", str_requeteEnTetes(classeLangueNom), ");");
//							l();
//							tl(3, "page", str_DocumentSolr(classeLangueNom), ".setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
//							tl(3, "page.setPage", str_DocumentSolr(classeLangueNom), "(page", str_DocumentSolr(classeLangueNom), ");");
//							tl(3, "page.setW(w);");
//							tl(3, "if(", str_liste(classeLangueNom), classeNomSimple, ".size() == 1)");
//							tl(4, str_requeteSite(classeLangueNom), ".set", str_Requete(classeLangueNom), StringUtils.capitalize(classeVarClePrimaire), "(", str_liste(classeLangueNom), classeNomSimple, ".get(0).get", StringUtils.capitalize(classeVarClePrimaire), "()", ");");
//							if(!classePageSimple)
//								tl(3, "page.set", str_Liste(classeLangueNom), classeNomSimple, "(", str_liste(classeLangueNom), classeNomSimple, ");");
//							tl(3, "page.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ");");
//							tl(3, "page.", str_initLoin(classeLangueNom), classePageNomSimpleMethode, "(", str_requeteSite(classeLangueNom), ");");
//							tl(3, "page.html();");
						}

						tl(3, str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", var, ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(a.result()));");
						tl(4, "} else {");
						tl(5, "LOG.error(String.format(\"", classeApiOperationIdMethode, str_Reponse(classeLangueNom), " ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
						tl(5, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception ex) {");
						tl(3, "LOG.error(String.format(\"", classeApiOperationIdMethode, str_Reponse(classeLangueNom), " ", str_a_échoué(classeLangueNom), ". \", ex));");
						tl(3, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
						tl(2, "}");
						tl(1, "}");
					}

					/////////////////
					// Reponse 200 //
					/////////////////
					t(1, "public void ", str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(");
	
					if(classeApiMethode.contains("POST"))
						s(classeNomSimple, " o");
					else if(classeApiMethode.equals("PUTImport") || classeApiMethode.equals(str_PUTFusion(classeLangueNom)) || classeApiMethode.contains("DELETE"))
						s(classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom));
					else if(classeApiMethode.contains("PUT"))
						s(classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom));
					else if(classeApiMethode.contains("PATCH"))
						s(classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom));
					else if(classeApiMethode.contains("GET") || classeApiMethode.contains(str_Recherche(classeLangueNom)))
						s(classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple);
					else
						s(classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom));
	
					l(", Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
	
					tl(2, "try {");
//					tl(3, "JsonObject json = new JsonObject();");
	
					if(classeApiMethode.contains("POST")) {
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
					}
					else if(classeApiMethode.equals("PUTImport") || classeApiMethode.equals(str_PUTFusion(classeLangueNom))) {
//						tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get" , str_RequeteApi(classeLangueNom), "_();");
					}
					else if(classeApiMethode.contains("PATCH") || classeApiMethode.contains("PUT")) {
//						tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get" , str_RequeteApi(classeLangueNom), "_();");
					}
					else if(classeApiMethode.contains(str_Recherche(classeLangueNom)) || classeApiMethode.contains("GET")) {
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_liste(classeLangueNom), classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_();");
					}
					else {
					}
	
	
					if(classeApiMethode.contains("GET")) {
						tl(3, "SolrDocumentList ", str_documentsSolr(classeLangueNom), " = ", str_liste(classeLangueNom), classeNomSimple, ".getSolrDocumentList();");
						l();
					}
					if(classeApiMethode.contains(str_Recherche(classeLangueNom))) {
						if(classePageNomCanoniqueMethode != null) {
							tl(3, "Buffer buffer = Buffer.buffer();");
							if(classePartsToutEcrivain == null)
								throw new RuntimeException(String.format("%s %s %s %s %s. ", str_classe(classeLangueNom), str_ToutEcrivain(classeLangueNom), str_manquante(classeLangueNom), str_dans(classeLangueNom), cheminSrcMainJava));
							t(3, classePartsToutEcrivain.nomSimple(classeLangueNom), " w = ", classePartsToutEcrivain.nomSimple(classeLangueNom), ".", str_creer(classeLangueNom), "(");
							s("", str_liste(classeLangueNom), classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_()");
							l(", buffer);");
							tl(3, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
//							tl(3, "page.setPageUrl(\"", siteUrlBase, classeApiUri, "\");");
							tl(3, "SolrDocument page", str_DocumentSolr(classeLangueNom), " = new SolrDocument();");
							tl(3, "MultiMap ", str_requeteEnTetes(classeLangueNom), " = MultiMap.caseInsensitiveMultiMap();");
							tl(3, str_requeteSite(classeLangueNom), ".set", str_RequeteEnTetes(classeLangueNom), "(", str_requeteEnTetes(classeLangueNom), ");");
							l();
							tl(3, "page", str_DocumentSolr(classeLangueNom), ".setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
							tl(3, "page.setPage", str_DocumentSolr(classeLangueNom), "(page", str_DocumentSolr(classeLangueNom), ");");
							tl(3, "page.setW(w);");
							tl(3, "if(", str_liste(classeLangueNom), classeNomSimple, ".size() == 1)");
							tl(4, str_requeteSite(classeLangueNom), ".set", str_Requete(classeLangueNom), StringUtils.capitalize(classeVarClePrimaire), "(", str_liste(classeLangueNom), classeNomSimple, ".get(0).get", StringUtils.capitalize(classeVarClePrimaire), "()", ");");
							tl(3, str_requeteSite(classeLangueNom), ".setW(w);");
							if(!classePageSimple)
								tl(3, "page.set", str_Liste(classeLangueNom), classeNomSimple, "(", str_liste(classeLangueNom), classeNomSimple, ");");
							tl(3, "page.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ");");
							tl(3, classeApiOperationIdMethode, str_Page(classeLangueNom), "Init(page, ", str_liste(classeLangueNom), classeNomSimple, ");");
							tl(3, "page.", str_initLoin(classeLangueNom), classePageNomSimpleMethode, "(", str_requeteSite(classeLangueNom), ");");
							tl(3, "page.html();");
						}
						else {
							tl(3, "QueryResponse ", str_reponse(classeLangueNom), str_Recherche(classeLangueNom), " = ", str_liste(classeLangueNom), classeNomSimple, ".getQueryResponse();");
							tl(3, "SolrDocumentList ", str_documentsSolr(classeLangueNom), " = ", str_liste(classeLangueNom), classeNomSimple, ".getSolrDocumentList();");
							tl(3, "Long ", str_millisRecherche(classeLangueNom), " = Long.valueOf(", str_reponse(classeLangueNom), str_Recherche(classeLangueNom), ".getQTime());");
							tl(3, "Long ", str_millisTransmission(classeLangueNom), " = ", str_reponse(classeLangueNom), str_Recherche(classeLangueNom), ".getElapsedTime();");
							tl(3, "Long ", str_numCommence(classeLangueNom), " = ", str_reponse(classeLangueNom), str_Recherche(classeLangueNom), ".getResults().getStart();");
							tl(3, "Long ", str_numTrouve(classeLangueNom), " = ", str_reponse(classeLangueNom), str_Recherche(classeLangueNom), ".getResults().getNumFound();");
							tl(3, "Integer ", str_numRetourne(classeLangueNom), " = ", str_reponse(classeLangueNom), str_Recherche(classeLangueNom), ".getResults().size();");
							tl(3, "String ", str_tempsRecherche(classeLangueNom), " = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(", str_millisRecherche(classeLangueNom), "), TimeUnit.MILLISECONDS.toMillis(", str_millisRecherche(classeLangueNom), ") - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(", str_millisRecherche(classeLangueNom), ")));");
							tl(3, "String ", str_tempsTransmission(classeLangueNom), " = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(", str_millisTransmission(classeLangueNom), "), TimeUnit.MILLISECONDS.toMillis(", str_millisTransmission(classeLangueNom), ") - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(", str_millisTransmission(classeLangueNom), ")));");
							tl(3, "Exception exception", str_Recherche(classeLangueNom), " = ", str_reponse(classeLangueNom), str_Recherche(classeLangueNom), ".getException();");
							tl(3, "List<String> fls = ", str_liste(classeLangueNom), classeNomSimple, ".getFields();");
							l();
							tl(3, "JsonObject json = new JsonObject();");
							tl(3, "json.put(", q(str_numCommence(classeLangueNom)), ", ", str_numCommence(classeLangueNom), ");");
							tl(3, "json.put(", q(str_numTrouve(classeLangueNom)), ", ", str_numTrouve(classeLangueNom), ");");
							tl(3, "json.put(", q(str_numRetourne(classeLangueNom)), ", ", str_numRetourne(classeLangueNom), ");");
							tl(3, "if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals(\"", str_sauvegardes(classeLangueNom), "\")) {");
							tl(4, "json.put(", q(str_tempsRecherche(classeLangueNom)), ", ", str_tempsRecherche(classeLangueNom), ");");
							tl(4, "json.put(", q(str_tempsTransmission(classeLangueNom)), ", ", str_tempsTransmission(classeLangueNom), ");");
							tl(3, "}");
							tl(3, "JsonArray l = new JsonArray();");
							tl(3, str_liste(classeLangueNom), classeNomSimple, ".getList().stream().forEach(o -> {");
							tl(4, "JsonObject json2 = JsonObject.mapFrom(o);");
							tl(4, "if(fls.size() > 0) {");
							tl(5, "Set<String> fieldNames = new HashSet<String>();");
							tl(5, "fieldNames.addAll(json2.fieldNames());");
							tl(5, "if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals(\"", str_sauvegardes(classeLangueNom), "\")) {");
							tl(6, "fieldNames.removeAll(Optional.ofNullable(json2.getJsonArray(\"", str_sauvegardes(classeLangueNom), "\")).orElse(new JsonArray()).stream().map(s -> s.toString()).collect(Collectors.toList()));");
							tl(6, "fieldNames.remove(\"", classeVarClePrimaire, "\");");
							tl(6, "fieldNames.remove(\"", str_cree(classeLangueNom), "\");");
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
							tl(3, "json.put(", q(str_liste(classeLangueNom)), ", l);");
							l();
							tl(3, "List<FacetField> facetFields = ", str_reponse(classeLangueNom), str_Recherche(classeLangueNom), ".getFacetFields();");
							tl(3, "if(facetFields != null) {");
							tl(4, "JsonObject facetFieldsJson = new JsonObject();");
							tl(4, "json.put(\"facet_fields\", facetFieldsJson);");
							tl(4, "for(FacetField facetField : facetFields) {");
							tl(5, "String facetFieldVar = StringUtils.substringBefore(facetField.getName(), \"_indexed_\");");
							tl(5, "JsonArray facetFieldCountsArray = new JsonArray();");
							tl(5, "facetFieldsJson.put(facetFieldVar, facetFieldCountsArray);");
							tl(5, "List<FacetField.Count> facetFieldValues = facetField.getValues();");
							tl(5, "for(Integer i = 0; i < facetFieldValues.size(); i+= 1) {");
							tl(6, "JsonObject countJson = new JsonObject();");
							tl(6, "FacetField.Count count = (FacetField.Count)facetFieldValues.get(i);");
							tl(6, "countJson.put(count.getName(), count.getCount());");
							tl(6, "facetFieldCountsArray.add(countJson);");
							tl(5, "}");
							tl(4, "}");
							tl(3, "}");
							l();
							tl(3, "List<RangeFacet> facetRanges = ", str_reponse(classeLangueNom), str_Recherche(classeLangueNom), ".getFacetRanges();");
							tl(3, "if(facetRanges != null) {");
							tl(4, "JsonObject rangeJson = new JsonObject();");
							tl(4, "json.put(\"facet_ranges\", rangeJson);");
							tl(4, "for(RangeFacet rangeFacet : facetRanges) {");
							tl(5, "JsonObject rangeFacetJson = new JsonObject();");
							tl(5, "String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), \"_indexed_\");");
							tl(5, "rangeJson.put(rangeFacetVar, rangeFacetJson);");
							tl(5, "JsonArray rangeFacetCountsList = new JsonArray();");
							tl(5, "rangeFacetJson.put(\"counts\", rangeFacetCountsList);");
							tl(5, "List<?> rangeFacetCounts = rangeFacet.getCounts();");
							tl(5, "for(Integer i = 0; i < rangeFacetCounts.size(); i+= 1) {");
							tl(6, "JsonObject countJson = new JsonObject();");
							tl(6, "RangeFacet.Count count = (RangeFacet.Count)rangeFacetCounts.get(i);");
							tl(6, "countJson.put(\"value\", count.getValue());");
							tl(6, "countJson.put(\"count\", count.getCount());");
							tl(6, "rangeFacetCountsList.add(countJson);");
							tl(5, "}");
							tl(4, "}");
							tl(3, "}");
							l();
							tl(3, "NamedList<List<PivotField>> facetPivot = ", str_reponse(classeLangueNom), str_Recherche(classeLangueNom), ".getFacetPivot();");
							tl(3, "if(facetPivot != null) {");
							tl(4, "JsonObject facetPivotJson = new JsonObject();");
							tl(4, "json.put(\"facet_pivot\", facetPivotJson);");
							tl(4, "Iterator<Entry<String, List<PivotField>>> facetPivotIterator = responseSearch.getFacetPivot().iterator();");
							tl(4, "while(facetPivotIterator.hasNext()) {");
							tl(5, "Entry<String, List<PivotField>> pivotEntry = facetPivotIterator.next();");
							tl(5, "List<PivotField> pivotFields = pivotEntry.getValue();");
							tl(5, "String[] vars", str_Indexe(classeLangueNom), " = pivotEntry.getKey().trim().split(\",\");");
							tl(5, "String[] ", str_entite(classeLangueNom), "Vars = new String[vars", str_Indexe(classeLangueNom), ".length];");
							tl(5, "for(Integer i = 0; i < ", str_entite(classeLangueNom), "Vars.length; i++) {");
							tl(6, "String ", str_entite(classeLangueNom), str_Indexe(classeLangueNom), " = vars", str_Indexe(classeLangueNom), "[i];");
							tl(6, str_entite(classeLangueNom), "Vars[i] = StringUtils.substringBefore(", str_entite(classeLangueNom), str_Indexe(classeLangueNom), ", \"_indexed_\");");
							tl(5, "}");
							tl(5, "JsonArray pivotArray = new JsonArray();");
							tl(5, "facetPivotJson.put(StringUtils.join(", str_entite(classeLangueNom), "Vars, \",\"), pivotArray);");
							tl(5, str_reponse(classeLangueNom), "Pivot", classeApiMethode, classeNomSimple, "(pivotFields, pivotArray);");
							tl(4, "}");
							tl(3, "}");
							tl(3, "if(exception", str_Recherche(classeLangueNom), " != null) {");
							tl(4, "json.put(", q("exception", str_Recherche(classeLangueNom)), ", exception", str_Recherche(classeLangueNom), ".getMessage());");
							tl(3, "}");
						}
					}
					else if(classeApiMethode.contains("GET")) {
						if(classePageNomCanoniqueMethode != null) {
							tl(3, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
//							tl(3, "page.setPageUrl(\"", siteUrlBase, classeApiUri, "\");");
							tl(3, "SolrDocument page", str_DocumentSolr(classeLangueNom), " = new SolrDocument();");
							tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_liste(classeLangueNom), classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_();");
							tl(3, "MultiMap ", str_requeteEnTetes(classeLangueNom), " = MultiMap.caseInsensitiveMultiMap();");
							tl(3, str_requeteSite(classeLangueNom), ".set", str_RequeteEnTetes(classeLangueNom), "(", str_requeteEnTetes(classeLangueNom), ");");
							l();
							tl(3, "page", str_DocumentSolr(classeLangueNom), ".setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
							tl(3, "page.setPage", str_DocumentSolr(classeLangueNom), "(page", str_DocumentSolr(classeLangueNom), ");");
							tl(3, "page.setW(w);");
							tl(3, str_requeteSite(classeLangueNom), ".setW(w);");
							tl(3, "page.", str_initLoin(classeLangueNom), classePageNomSimpleMethode, "(", str_requeteSite(classeLangueNom), ");");
							tl(3, "page.html();");
						}
						else {
							tl(3, "JsonObject json = JsonObject.mapFrom(", str_liste(classeLangueNom), classeNomSimple, ".getList().stream().findFirst().orElse(null));");
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
	
					if((classeApiMethode.contains("GET") || classeApiMethode.contains(str_Recherche(classeLangueNom))) && classePageNomCanoniqueMethode != null) {
						tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(new ServiceResponse(200, \"OK\", buffer, ", str_requeteEnTetes(classeLangueNom), ")));");
					}
					else {
						tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));");
					}
	
					tl(2, "} catch(Exception e) {");
						tl(3, "LOG.error(String.format(\"", str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
					tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
					if(classeApiMethode.contains(str_Recherche(classeLangueNom)) && classePageNomCanoniqueMethode == null) {
						tl(1, "public void ", str_reponse(classeLangueNom), "Pivot", classeApiMethode, classeNomSimple, "(List<PivotField> pivotFields, JsonArray pivotArray) {");
						tl(2, "for(PivotField pivotField : pivotFields) {");
						tl(3, "String ", str_entite(classeLangueNom), str_Indexe(classeLangueNom), " = pivotField.getField();");
						tl(3, "String ", str_entite(classeLangueNom), "Var = StringUtils.substringBefore(", str_entite(classeLangueNom), str_Indexe(classeLangueNom), ", \"_indexed_\");");
						tl(3, "JsonObject pivotJson = new JsonObject();");
						tl(3, "pivotArray.add(pivotJson);");
						tl(3, "pivotJson.put(\"field\", entityVar);");
						tl(3, "pivotJson.put(\"value\", pivotField.getValue());");
						tl(3, "pivotJson.put(\"count\", pivotField.getCount());");
						tl(3, "List<RangeFacet> pivotRanges = pivotField.getFacetRanges();");
						tl(3, "List<PivotField> pivotFields2 = pivotField.getPivot();");
						tl(3, "if(pivotRanges != null) {");
						tl(4, "JsonObject rangeJson = new JsonObject();");
						tl(4, "pivotJson.put(\"ranges\", rangeJson);");
						tl(4, "for(RangeFacet rangeFacet : pivotRanges) {");
						tl(5, "JsonObject rangeFacetJson = new JsonObject();");
						tl(5, "String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), \"_indexed_\");");
						tl(5, "rangeJson.put(rangeFacetVar, rangeFacetJson);");
						tl(5, "JsonArray rangeFacetCountsList = new JsonArray();");
						tl(5, "rangeFacetJson.put(\"counts\", rangeFacetCountsList);");
						tl(5, "List<?> rangeFacetCounts = rangeFacet.getCounts();");
						tl(5, "for(Integer i = 0; i < rangeFacetCounts.size(); i+= 1) {");
						tl(6, "JsonObject countJson = new JsonObject();");
						tl(6, "RangeFacet.Count count = (RangeFacet.Count)rangeFacetCounts.get(i);");
						tl(6, "countJson.put(\"value\", count.getValue());");
						tl(6, "countJson.put(\"count\", count.getCount());");
						tl(6, "rangeFacetCountsList.add(countJson);");
						tl(5, "}");
						tl(4, "}");
						tl(3, "}");
						tl(3, "if(pivotFields2 != null) {");
						tl(4, "JsonArray pivotArray2 = new JsonArray();");
						tl(4, "pivotJson.put(\"pivot\", pivotArray2);");
						tl(4, str_reponse(classeLangueNom), "Pivot", classeApiMethode, classeNomSimple, "(pivotFields2, pivotArray2);");
						tl(3, "}");
						tl(2, "}");
						tl(1, "}");
					}
				}
			}
	
			s(wApiEntites.toString());
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

			if(classeApiMethodes.contains("PATCH") || classeApiMethodes.contains("PUT")) {
				l();
				tl(1, "public void ", str_creer(classeLangueNom), classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Handler<AsyncResult<", classeNomSimple, ">> ", str_gestionnaireEvenements(classeLangueNom), ") {");
				tl(2, "try {");
				tl(3, "SqlConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
				tl(3, "String ", str_utilisateur(classeLangueNom), "Id = ", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "Id();");
				tl(3, "Long ", str_utilisateurCle(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_UtilisateurCle(classeLangueNom), "();");
				tl(3, "ZonedDateTime ", str_cree(classeLangueNom), " = Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "()).map(j -> j.getString(\"", str_cree(classeLangueNom), "\")).map(s -> ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of(config.getString(\"siteZone\"))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(\"siteZone\"))));");
				l();
				if(sqlTables) {
					if(activerUtilisateurCle) {
						tl(3, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", classeNomSimple, "(", str_cree(classeLangueNom), ", ", str_utilisateurCle(classeLangueNom), ") VALUES($1, $2) RETURNING ", classeVarClePrimaire, "\")");
						tl(5, ".collecting(Collectors.toList())");
						tl(5,".execute(Tuple.of(", str_cree(classeLangueNom), ".toOffsetDateTime(), ", str_utilisateurCle(classeLangueNom), ")");
					} else {
						tl(3, str_connexionSql(classeLangueNom), ".preparedQuery(\"INSERT INTO ", classeNomSimple, "(", str_cree(classeLangueNom), ") VALUES($1) RETURNING ", classeVarClePrimaire, "\")");
						tl(5, ".collecting(Collectors.toList())");
						tl(5,".execute(Tuple.of(", str_cree(classeLangueNom), ".toOffsetDateTime())");
					}
				}
				else {
					tl(3, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_", str_creer(classeLangueNom), ")");
					tl(5, ".collecting(Collectors.toList())");
					tl(5, ".execute(Tuple.of(", classeNomSimple, ".class.getCanonicalName(), ", str_utilisateur(classeLangueNom), "Id, ", str_cree(classeLangueNom), ".toOffsetDateTime())");
				}
				tl(5, ", ", str_creer(classeLangueNom), "Async");
				tl(3, "-> {");
				tl(4, "if(", str_creer(classeLangueNom), "Async.succeeded()) {");
				tl(5, "Row ", str_creer(classeLangueNom), str_Ligne(classeLangueNom), " = ", str_creer(classeLangueNom), "Async.result().value().stream().findFirst().orElseGet(() -> null);");
				tl(5, "Long ", classeVarClePrimaire, " = ", str_creer(classeLangueNom), str_Ligne(classeLangueNom), ".getLong(0);");
				tl(5, classeNomSimple, " o = new ", classeNomSimple, "();");
				tl(5, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
				tl(5, "o.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ");");
				tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(o));");
				tl(4, "} else {");
				tl(5, "LOG.error(String.format(\"", str_creer(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", ", str_creer(classeLangueNom), "Async.cause()));");
				tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(", str_creer(classeLangueNom), "Async.cause()));");
				tl(4, "}");
				tl(3, "});");
				tl(2, "} catch(Exception e) {");
				tl(3, "LOG.error(String.format(\"", str_creer(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
				tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
				tl(2, "}");
				tl(1, "}");
			}
			l();
			tl(1, "public void ", str_erreur(classeLangueNom), classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ", AsyncResult<?> ", str_resultat(classeLangueNom), "Async) {");
			tl(2, "Throwable e = ", str_resultat(classeLangueNom), "Async.cause();");
			tl(2, "JsonObject json = new JsonObject();");
			tl(2, "JsonObject json", str_Erreur(classeLangueNom), " = new JsonObject();");
			tl(2, "json.put(\"", str_erreur(classeLangueNom), "\", json", str_Erreur(classeLangueNom), ");");
			tl(2, "json", str_Erreur(classeLangueNom), ".put(\"message\", Optional.ofNullable(e).map(Throwable::getMessage).orElse(null));");
			tl(2, "if(", str_requeteSite(classeLangueNom), " != null) {");
			tl(3, "json", str_Erreur(classeLangueNom), ".put(\"", str_utilisateurNom(classeLangueNom), "\", ", str_requeteSite(classeLangueNom), ".get", str_UtilisateurNom(classeLangueNom), "());");
			tl(3, "json", str_Erreur(classeLangueNom), ".put(\"", str_utilisateurNomComplet(classeLangueNom), "\", ", str_requeteSite(classeLangueNom), ".get", str_UtilisateurNomComplet(classeLangueNom), "());");
			tl(3, "json", str_Erreur(classeLangueNom), ".put(\"", str_requeteUri(classeLangueNom), "\", ", str_requeteSite(classeLangueNom), ".get", str_RequeteUri(classeLangueNom), "());");
			tl(3, "json", str_Erreur(classeLangueNom), ".put(\"", str_requeteMethode(classeLangueNom), "\", ", str_requeteSite(classeLangueNom), ".get", str_RequeteMethode(classeLangueNom), "());");
			tl(3, "json", str_Erreur(classeLangueNom), ".put(\"params\", Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_RequeteService(classeLangueNom), "()).map(o -> o.getParams()).orElse(null));");
			tl(2, "}");
			tl(2, "LOG.error(\"", str_erreur(classeLangueNom), ": \", e);");
			tl(2, "ServiceResponse ", str_reponse(classeLangueNom), "Operation = new ServiceResponse(400, \"BAD REQUEST\", ");
			tl(4, "Buffer.buffer().appendString(json.encodePrettily())");
			tl(4, ", MultiMap.caseInsensitiveMultiMap().add(\"Content-Type\", \"application/json\")");
			tl(2, ");");
	
			tl(2, "if(", str_requeteSite(classeLangueNom), " != null) {");
			tl(3, "DeliveryOptions options = new DeliveryOptions();");
			tl(3, "options.addHeader(MailVerticle.MAIL_HEADER_SUBJECT, String.format(\"%s %s\", config.getString(", classePartsConfigCles.nomSimple(classeLangueNom), ".", str_SITE_URL_BASE(classeLangueNom), "), Optional.ofNullable(e).map(Throwable::getMessage).orElse(null)));");
			tl(3, "eventBus.publish(MailVerticle.MAIL_EVENTBUS_ADDRESS, String.format(\"%s\\n\\n%s\", json.encodePrettily(), ExceptionUtils.getStackTrace(e)));");
			tl(3, "if(", str_gestionnaireEvenements(classeLangueNom), " != null)");
			tl(4, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_reponse(classeLangueNom), "Operation));");
			tl(2, "} else {");
			tl(3, "if(", str_gestionnaireEvenements(classeLangueNom), " != null)");
			tl(4, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_reponse(classeLangueNom), "Operation));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public ", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_generer(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), str_Pour(classeLangueNom), classeNomSimple, "(User ", str_utilisateur(classeLangueNom), ", SolrClient ", str_clientSolr(classeLangueNom), ", ServiceRequest ", str_requeteService(classeLangueNom), ") {");
			tl(2, "return ", str_generer(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), str_Pour(classeLangueNom), classeNomSimple, "(", str_utilisateur(classeLangueNom), ", ", str_clientSolr(classeLangueNom), ", ", str_requeteService(classeLangueNom), ", null);");
			tl(1, "}");
			l();
			tl(1, "public ", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_generer(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), str_Pour(classeLangueNom), classeNomSimple, "(User ", str_utilisateur(classeLangueNom), ", SolrClient ", str_clientSolr(classeLangueNom), ", ServiceRequest ", str_requeteService(classeLangueNom), ", JsonObject body) {");
			tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = new ", classePartsRequeteSite.nomSimple(classeLangueNom), "();");
			tl(2, str_requeteSite(classeLangueNom), ".set", str_ClientSolr(classeLangueNom), "(", str_clientSolr(classeLangueNom), ");");
			tl(2, str_requeteSite(classeLangueNom), ".set", str_ObjetJson(classeLangueNom), "(body);");
			tl(2, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), "(", str_utilisateur(classeLangueNom), ");");
			tl(2, str_requeteSite(classeLangueNom), ".setConfig(config);");
			tl(2, str_requeteSite(classeLangueNom), ".set", str_RequeteService(classeLangueNom), "(", str_requeteService(classeLangueNom), ");");
			tl(2, str_requeteSite(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
			l();
			tl(2, "return ", str_requeteSite(classeLangueNom), ";");
			tl(1, "}");
			l();
			/////////////////
			// utilisateur //
			/////////////////
			tl(1, "public void ", str_utilisateur(classeLangueNom), classeNomSimple, "(ServiceRequest ", str_requeteService(classeLangueNom), ", Handler<AsyncResult<", classePartsRequeteSite.nomSimple(classeLangueNom), ">> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			tl(2, "try {");
			tl(3, "JsonObject ", str_utilisateur(classeLangueNom), "Json = ", str_requeteService(classeLangueNom), ".getUser();");
			tl(3, "if(", str_utilisateur(classeLangueNom), "Json == null) {");
			tl(4, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_generer(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), str_Pour(classeLangueNom), classeNomSimple, "(null, ", str_clientSolr(classeLangueNom), ", ", str_requeteService(classeLangueNom), ");");
			tl(4, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_requeteSite(classeLangueNom), "));");
			tl(3, "} else {");
			Integer tBase;
			if(activerOpenIdConnect) {
				tBase = 5;
				tl(4, "User token = User.create(", str_utilisateur(classeLangueNom), "Json);");
				tl(4, "oauth2AuthenticationProvider.authenticate(token.principal(), a -> {");
				tl(5, "if(a.succeeded()) {");
				tl(6, "User ", str_utilisateur(classeLangueNom), " = a.result();");
				tl(6, "authorizationProvider.getAuthorizations(", str_utilisateur(classeLangueNom), ", b -> {");
				tl(7, "if(b.succeeded()) {");
				tl(8, "try {");
				tl(tBase + 4, "JsonObject ", str_utilisateur(classeLangueNom), "Attributes = ", str_utilisateur(classeLangueNom), ".attributes();");
				tl(tBase + 4, "JsonObject accessToken = ", str_utilisateur(classeLangueNom), "Attributes.getJsonObject(\"accessToken\");");
				tl(tBase + 4, "String ", str_utilisateur(classeLangueNom), "Id = ", str_utilisateur(classeLangueNom), "Attributes.getString(\"sub\");");
			} else {
				tBase = 0;
				tl(tBase + 4, "User ", str_utilisateur(classeLangueNom), " = User.create(", str_utilisateur(classeLangueNom), "Json);");
				tl(tBase + 4, "String ", str_utilisateur(classeLangueNom), "Id = ", str_utilisateur(classeLangueNom), "Json.getString(\"sub\");");
			}
			tl(tBase + 4, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_generer(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), str_Pour(classeLangueNom), classeNomSimple, "(", str_utilisateur(classeLangueNom), ", ", str_clientSolr(classeLangueNom), ", ", str_requeteService(classeLangueNom), ");");

			tl(tBase + 4, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classePartsUtilisateurSite.nomSimple(classeLangueNom), "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classePartsUtilisateurSite.nomSimple(classeLangueNom), ">();");
			tl(tBase + 4, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
			tl(tBase + 4, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
			tl(tBase + 4, str_listeRecherche(classeLangueNom), ".setC(", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class);");
			tl(tBase + 4, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_utilisateur(classeLangueNom), "Id_indexed_string:\" + ClientUtils.escapeQueryChars(", str_utilisateur(classeLangueNom), "Id));");
			tl(tBase + 4, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
			tl(tBase + 4, classePartsUtilisateurSite.nomSimple(classeLangueNom), " ", str_utilisateurSite(classeLangueNom), "1 = ", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null);");
			tl(tBase + 4, classePartsUtilisateurSite.nomSimple(classeLangueNom), StringUtils.capitalize(classeLangueNom), "ApiServiceImpl ", str_utilisateur(classeLangueNom), "Service = new ", classePartsUtilisateurSite.nomSimple(classeLangueNom), StringUtils.capitalize(classeLangueNom), "ApiServiceImpl(eventBus, config, ", str_executeurTravailleur(classeLangueNom), ", pgPool, ", str_clientSolr(classeLangueNom), activerOpenIdConnect ? ", oauth2AuthenticationProvider, authorizationProvider" : "", ");");
			l();
			tl(tBase + 4, "if(", str_utilisateurSite(classeLangueNom), "1 == null) {");
			tl(tBase + 5, "JsonObject jsonObject = new JsonObject();");
			tl(tBase + 5, "jsonObject.put(\"", str_utilisateur(classeLangueNom), str_Nom(classeLangueNom), "\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"preferred_username\"));");
			tl(tBase + 5, "jsonObject.put(\"", str_utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"given_name\"));");
			tl(tBase + 5, "jsonObject.put(\"", str_utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"family_name\"));");
			tl(tBase + 5, "jsonObject.put(\"", str_utilisateur(classeLangueNom), str_NomComplet(classeLangueNom), "\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"name\"));");
			tl(tBase + 5, "jsonObject.put(\"", str_utilisateur(classeLangueNom), "Id\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"sub\"));");
			tl(tBase + 5, "jsonObject.put(\"", str_utilisateur(classeLangueNom), str_Mail(classeLangueNom), "\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"email\"));");
			tl(tBase + 5, str_utilisateur(classeLangueNom), classeNomSimple, str_Definir(classeLangueNom), "(", str_requeteSite(classeLangueNom), ", jsonObject, false);");
			l();
			tl(tBase + 5, str_RequeteSite(classeLangueNom), StringUtils.capitalize(classeLangueNom), " ", str_requeteSite(classeLangueNom), "2 = ", str_requeteSite(classeLangueNom), ".", str_copie(classeLangueNom), "();");
			tl(tBase + 5, str_requeteSite(classeLangueNom), "2.", str_initLoin(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
			l();
			tl(tBase + 5, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
			tl(tBase + 5, str_requeteApi(classeLangueNom), ".setRows(1);");
			tl(tBase + 5, str_requeteApi(classeLangueNom), ".setNumFound(1L);");
			tl(tBase + 5, str_requeteApi(classeLangueNom), ".setNumPATCH(0L);");
			tl(tBase + 5, str_requeteApi(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsRequeteApi.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), "2);");
			tl(tBase + 5, str_requeteSite(classeLangueNom), "2.set", str_RequeteApi(classeLangueNom), "_(", str_requeteApi(classeLangueNom), ");");
			l();
			tl(tBase + 5, str_utilisateur(classeLangueNom), "Service.post", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future(", str_requeteSite(classeLangueNom), "2, false).onSuccess(", str_utilisateurSite(classeLangueNom), " -> {");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", classePartsUtilisateurSite.nomSimple(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), ");");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Nom(classeLangueNom), "(", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"preferred_username\"));");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "(", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"given_name\"));");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "(", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"family_name\"));");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Mail(classeLangueNom), "(", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"email\"));");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), "Id(", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"sub\"));");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), ".get", StringUtils.capitalize(classeVarClePrimaire), "());");
			tl(tBase + 6, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_requeteSite(classeLangueNom), "));");
			tl(tBase + 5, "}).onFailure(ex -> {");
			tl(tBase + 6, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
			tl(tBase + 5, "});");
			tl(tBase + 4, "} else {");
//			tl(tBase + 5, "Long ", classeVarClePrimaire, str_Utilisateur(classeLangueNom), " = ", str_utilisateurSite(classeLangueNom), "1.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			tl(tBase + 5, "JsonObject jsonObject = new JsonObject();");
			tl(tBase + 5, "jsonObject.put(\"set", str_Utilisateur(classeLangueNom), str_Nom(classeLangueNom), "\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"preferred_username\"));");
			tl(tBase + 5, "jsonObject.put(\"set", str_Utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"given_name\"));");
			tl(tBase + 5, "jsonObject.put(\"set", str_Utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"family_name\"));");
			tl(tBase + 5, "jsonObject.put(\"set", str_Utilisateur(classeLangueNom), str_NomComplet(classeLangueNom), "\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"name\"));");
			if(customerProfileId1)
				tl(tBase + 5, "jsonObject.put(\"setCustomerProfileId1\", Optional.ofNullable(", str_utilisateurSite(classeLangueNom), "1).map(u -> u.getCustomerProfileId1()).orElse(null));");
			if(customerProfileId2)
				tl(tBase + 5, "jsonObject.put(\"setCustomerProfileId2\", Optional.ofNullable(", str_utilisateurSite(classeLangueNom), "1).map(u -> u.getCustomerProfileId2()).orElse(null));");
			if(customerProfileId3)
				tl(tBase + 5, "jsonObject.put(\"setCustomerProfileId3\", Optional.ofNullable(", str_utilisateurSite(classeLangueNom), "1).map(u -> u.getCustomerProfileId3()).orElse(null));");
			if(customerProfileId4)
				tl(tBase + 5, "jsonObject.put(\"setCustomerProfileId4\", Optional.ofNullable(", str_utilisateurSite(classeLangueNom), "1).map(u -> u.getCustomerProfileId4()).orElse(null));");
			if(customerProfileId5)
				tl(tBase + 5, "jsonObject.put(\"setCustomerProfileId5\", Optional.ofNullable(", str_utilisateurSite(classeLangueNom), "1).map(u -> u.getCustomerProfileId5()).orElse(null));");
			if(customerProfileId6)
				tl(tBase + 5, "jsonObject.put(\"setCustomerProfileId6\", Optional.ofNullable(", str_utilisateurSite(classeLangueNom), "1).map(u -> u.getCustomerProfileId6()).orElse(null));");
			if(customerProfileId7)
				tl(tBase + 5, "jsonObject.put(\"setCustomerProfileId7\", Optional.ofNullable(", str_utilisateurSite(classeLangueNom), "1).map(u -> u.getCustomerProfileId7()).orElse(null));");
			if(customerProfileId8)
				tl(tBase + 5, "jsonObject.put(\"setCustomerProfileId8\", Optional.ofNullable(", str_utilisateurSite(classeLangueNom), "1).map(u -> u.getCustomerProfileId8()).orElse(null));");
			if(customerProfileId9)
				tl(tBase + 5, "jsonObject.put(\"setCustomerProfileId9\", Optional.ofNullable(", str_utilisateurSite(classeLangueNom), "1).map(u -> u.getCustomerProfileId9()).orElse(null));");
			if(customerProfileId10)
				tl(tBase + 5, "jsonObject.put(\"setCustomerProfileId10\", Optional.ofNullable(", str_utilisateurSite(classeLangueNom), "1).map(u -> u.getCustomerProfileId10()).orElse(null));");
			tl(tBase + 5, "jsonObject.put(\"set", str_Utilisateur(classeLangueNom), "Id\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"sub\"));");
			tl(tBase + 5, "jsonObject.put(\"set", str_Utilisateur(classeLangueNom), str_Mail(classeLangueNom), "\", ", activerOpenIdConnect ? "accessToken" : str_utilisateur(classeLangueNom) + "Json", ".getString(\"email\"));");
			tl(tBase + 5, "Boolean ", str_definir(classeLangueNom), " = ", str_utilisateur(classeLangueNom), classeNomSimple, str_Definir(classeLangueNom), "(", str_requeteSite(classeLangueNom), ", jsonObject, true);");
			tl(tBase + 5, "if(", str_definir(classeLangueNom), ") {");
			l();
			tl(tBase + 6, str_RequeteSite(classeLangueNom), StringUtils.capitalize(classeLangueNom), " ", str_requeteSite(classeLangueNom), "2 = ", str_requeteSite(classeLangueNom), ".", str_copie(classeLangueNom), "();");
			tl(tBase + 6, str_requeteSite(classeLangueNom), "2.", str_initLoin(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
			tl(tBase + 6, str_utilisateurSite(classeLangueNom), "1.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), "2);");
			l();
			tl(tBase + 6, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
			tl(tBase + 6, str_requeteApi(classeLangueNom), ".setRows(1);");
			tl(tBase + 6, str_requeteApi(classeLangueNom), ".setNumFound(1L);");
			tl(tBase + 6, str_requeteApi(classeLangueNom), ".setNumPATCH(0L);");
			tl(tBase + 6, str_requeteApi(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsRequeteApi.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), "2);");
			tl(tBase + 6, str_requeteSite(classeLangueNom), "2.set", str_RequeteApi(classeLangueNom), "_(", str_requeteApi(classeLangueNom), ");");
			l();
			tl(tBase + 6, str_utilisateur(classeLangueNom), "Service.", "patch", classePartsUtilisateurSite.nomSimple(classeLangueNom), "Future(", str_utilisateurSite(classeLangueNom), "1, false).onSuccess(", str_utilisateurSite(classeLangueNom), "2 -> {");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", classePartsUtilisateurSite.nomSimple(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "2);");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Nom(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "2.get", str_Utilisateur(classeLangueNom), str_Nom(classeLangueNom), "());");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "2.get", str_Utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "());");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "2.get", str_Utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "());");
			if(!sqlTables)
				tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), "Id(", str_utilisateurSite(classeLangueNom), "2.get", str_Utilisateur(classeLangueNom), "Id());");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "2.get", StringUtils.capitalize(classeVarClePrimaire), "());");
			tl(tBase + 6, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_requeteSite(classeLangueNom), "));");
			tl(tBase + 6, "}).onFailure(ex -> {");
			tl(tBase + 6, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", null, Future.failedFuture(ex));");
			tl(tBase + 6, "});");
			tl(tBase + 5, "} else {");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", classePartsUtilisateurSite.nomSimple(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "1);");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Nom(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "1.get", str_Utilisateur(classeLangueNom), str_Nom(classeLangueNom), "());");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "1.get", str_Utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "());");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "1.get", str_Utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "());");
			if(!sqlTables)
				tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), "Id(", str_utilisateurSite(classeLangueNom), "1.get", str_Utilisateur(classeLangueNom), "Id());");
			tl(tBase + 6, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "1.get", StringUtils.capitalize(classeVarClePrimaire), "());");
			tl(tBase + 6, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_requeteSite(classeLangueNom), "));");
			tl(tBase + 5, "}");
			tl(tBase + 4, "}");
			if(activerOpenIdConnect) {
				tl(8, "} catch(Exception ex) {");
				tl(9, "LOG.error(String.format(\"", str_utilisateur(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), ex);");
				tl(9, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(ex));");
				tl(8, "}");
				tl(7, "} else {");
				tl(8, "LOG.error(String.format(\"", str_utilisateur(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", b.cause()));");
				tl(8, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(b.cause()));");
				tl(7, "}");
				tl(6, "});");
				tl(5, "} else {");
				tl(6, "oauth2AuthenticationProvider.refresh(token).onSuccess(", str_utilisateur(classeLangueNom), " -> {");
				tl(7, str_requeteService(classeLangueNom), ".setUser(", str_utilisateur(classeLangueNom), ".principal());");
				tl(7, str_utilisateur(classeLangueNom), classeNomSimple, "(", str_requeteService(classeLangueNom), ", c -> {");
				tl(8, "if(c.succeeded()) {");
				tl(9, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = c.result();");
				tl(9, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_requeteSite(classeLangueNom), "));");
				tl(8, "} else {");
				tl(9, "LOG.error(String.format(\"", str_utilisateur(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", c.cause()));");
				tl(9, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(c.cause()));");
				tl(8, "}");
				tl(7, "});");
				tl(6, "}).onFailure(ex -> {");
				tl(7, "LOG.error(String.format(\"", str_utilisateur(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", a.cause()));");
				tl(7, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(a.cause()));");
				tl(6, "});");
				tl(5, "}");
				tl(4, "});");
			} else {
			}
			tl(3, "}");
			tl(2, "} catch(Exception ex) {");
			tl(3, "LOG.error(String.format(\"", str_utilisateur(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), ex);");
			tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(ex));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public Boolean ", str_utilisateur(classeLangueNom), classeNomSimple, str_Definir(classeLangueNom), "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", JsonObject jsonObject, Boolean patch) {");
			tl(2, "if(patch) {");
			if(customerProfileId1) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId1\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId2) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId2\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId3) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId3\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId4) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId4\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId5) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId5\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId6) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId6\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId7) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId7\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId8) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId8\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId9) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId9\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId10) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId10\") == null)");
				tl(4, "return true;");
			}
			tl(3, "return false;");
			tl(2, "} else {");
			if(customerProfileId1) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId1\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId2) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId2\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId3) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId3\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId4) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId4\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId5) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId5\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId6) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId6\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId7) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId7\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId8) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId8\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId9) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId9\") == null)");
				tl(4, "return true;");
			}
			if(customerProfileId10) {
				tl(3, "if(jsonObject.getString(\"setCustomerProfileId10\") == null)");
				tl(4, "return true;");
			}
			tl(3, "return false;");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void ", str_recherche(classeLangueNom), classeNomSimple, "Q(String uri, String ", str_apiMethode(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), ", String ", str_entite(classeLangueNom), "Var, String ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ", String ", "var", str_Indexe(classeLangueNom), ") {");
			tl(2, str_listeRecherche(classeLangueNom), ".setQuery(var", str_Indexe(classeLangueNom), " + \":\" + (\"*\".equals(", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ") ? ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), " : ClientUtils.escapeQueryChars(", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ")));");
			tl(2, "if(!\"*\".equals(", str_entite(classeLangueNom), "Var)) {");
//			tl(3, str_listeRecherche(classeLangueNom), ".setHighlight(true);");
//			tl(3, str_listeRecherche(classeLangueNom), ".setHighlightSnippets(3);");
//			tl(3, str_listeRecherche(classeLangueNom), ".addHighlightField(var", str_Indexe(classeLangueNom), ");");
//			tl(3, str_listeRecherche(classeLangueNom), ".setParam(\"hl.encoder\", \"html\");");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public String ", str_recherche(classeLangueNom), classeNomSimple, "Fq(String uri, String ", str_apiMethode(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), ", String ", str_entite(classeLangueNom), "Var, String ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ", String var", str_Indexe(classeLangueNom), ") {");
			tl(2, "if(var", str_Indexe(classeLangueNom), " == null)");
			tl(3, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", str_nest_pas_une_entite_indexe(langueNom), ". \", ", str_entite(classeLangueNom), "Var));");
			tl(2, "if(StringUtils.startsWith(", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ", \"[\")) {");
			tl(3, "String[] fqs = StringUtils.substringBefore(StringUtils.substringAfter(", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ", \"[\"), \"]\").split(\" TO \");");
			tl(3, "if(fqs.length != 2)");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" invalid range query. \", ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), "));");
			tl(3, "String fq1 = fqs[0].equals(\"*\") ? fqs[0] : ", classeNomSimple, ".staticSolrFq", str_PourClasse(classeLangueNom), "(", str_entite(classeLangueNom), "Var, ", str_listeRecherche(classeLangueNom), ".get", str_RequeteSite(classeLangueNom), "_(), fqs[0]);");
			tl(3, "String fq2 = fqs[1].equals(\"*\") ? fqs[1] : ", classeNomSimple, ".staticSolrFq", str_PourClasse(classeLangueNom), "(", str_entite(classeLangueNom), "Var, ", str_listeRecherche(classeLangueNom), ".get", str_RequeteSite(classeLangueNom), "_(), fqs[1]);");
			tl(3, " return var", str_Indexe(classeLangueNom), " + \":[\" + fq1 + \" TO \" + fq2 + \"]\";");
			tl(2, "} else {");
			tl(3, "return var", str_Indexe(classeLangueNom), " + \":\" + ", classeNomSimple, ".staticSolrFq", str_PourClasse(classeLangueNom), "(", str_entite(classeLangueNom), "Var, ", str_listeRecherche(classeLangueNom), ".get", str_RequeteSite(classeLangueNom), "_(), ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ");");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void ", str_recherche(classeLangueNom), classeNomSimple, "Sort(String uri, String ", str_apiMethode(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), ", String ", str_entite(classeLangueNom), "Var, String ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ", String ", "var", str_Indexe(classeLangueNom), ") {");
			tl(2, "if(var", str_Indexe(classeLangueNom), " == null)");
			tl(3, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", str_nest_pas_une_entite_indexe(langueNom), ". \", ", str_entite(classeLangueNom), "Var));");
			tl(2, str_listeRecherche(classeLangueNom), ".addSort(var", str_Indexe(classeLangueNom), ", ORDER.valueOf(", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), "));");
			tl(1, "}");
			l();
			tl(1, "public void ", str_recherche(classeLangueNom), classeNomSimple, "Rows(String uri, String ", str_apiMethode(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), ", Integer ", str_valeur(classeLangueNom), "Rows) {");
			tl(3, str_listeRecherche(classeLangueNom), ".setRows(", str_apiMethode(classeLangueNom), " != null && ", str_apiMethode(classeLangueNom), ".contains(\"", str_Recherche(classeLangueNom), "\") ? ", str_valeur(classeLangueNom), "Rows : 10);");
			tl(1, "}");
			l();
			tl(1, "public void ", str_recherche(classeLangueNom), classeNomSimple, "Start(String uri, String ", str_apiMethode(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), ", Integer ", str_valeur(classeLangueNom), "Start) {");
			tl(2, str_listeRecherche(classeLangueNom), ".setStart(", str_valeur(classeLangueNom), "Start);");
			tl(1, "}");
			l();
			tl(1, "public void ", str_recherche(classeLangueNom), classeNomSimple, "Var(String uri, String ", str_apiMethode(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), ", String var, String ", str_valeur(classeLangueNom), ") {");
			tl(2, str_listeRecherche(classeLangueNom), ".get", str_RequeteSite(classeLangueNom), "_().get", str_Requete(classeLangueNom), "Vars().put(var, ", str_valeur(classeLangueNom), ");");
			tl(1, "}");
			l();
			tl(1, "public void ", str_recherche(classeLangueNom), classeNomSimple, "Uri(String uri, String ", str_apiMethode(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), ") {");
			tl(1, "}");
			l();
			//////////
			// vars //
			//////////
			tl(1, "public void vars", classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Handler<AsyncResult<", classePartsListeRecherche.nomSimple(classeLangueNom), "<ServiceResponse>>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			tl(2, "try {");
			tl(3, "ServiceRequest ", str_requeteService(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_RequeteService(classeLangueNom), "();");
			l();
			tl(3, str_requeteService(classeLangueNom), ".getParams().getJsonObject(\"query\").forEach(param", str_Requete(classeLangueNom), " -> {");
			tl(4, "String ", str_entite(classeLangueNom), "Var = null;");
			tl(4, "String ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), " = null;");
			tl(4, "String param", str_Nom(classeLangueNom), " = param", str_Requete(classeLangueNom), ".getKey();");
			tl(4, "Object param", str_Valeurs(classeLangueNom), str_Objet(classeLangueNom), " = param", str_Requete(classeLangueNom), ".getValue();");
			tl(4, "JsonArray param", str_Objets(classeLangueNom), " = param", str_Valeurs(classeLangueNom), str_Objet(classeLangueNom), " instanceof JsonArray ? (JsonArray)param", str_Valeurs(classeLangueNom), str_Objet(classeLangueNom), " : new JsonArray().add(param", str_Valeurs(classeLangueNom), str_Objet(classeLangueNom), ");");
			l();
			tl(4, "try {");
			tl(5, "for(Object param", str_Objet(classeLangueNom), " : param", str_Objets(classeLangueNom), ") {");
			tl(6, "switch(param", str_Nom(classeLangueNom), ") {");
	
			tl(7, "case \"var\":");
			tl(8, str_entite(classeLangueNom), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(classeLangueNom), ", \":\"));");
			tl(8, str_valeur(classeLangueNom), str_Indexe(classeLangueNom), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(classeLangueNom), ", \":\")), \"UTF-8\");");
			tl(8, str_requeteSite(classeLangueNom), ".get", str_Requete(classeLangueNom), "Vars().put(", str_entite(classeLangueNom), "Var, ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ");");
			tl(8, "break;");
	
			tl(6, "}");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "LOG.error(String.format(\"", str_recherche(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
			tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(4, "}");

			tl(3, "});");
			tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(2, "} catch(Exception e) {");
			tl(3, "LOG.error(String.format(\"", str_recherche(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
			tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			///////////////
			// recherche //
			///////////////
			l();
			tl(1, "public void ", str_recherche(classeLangueNom), classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Boolean ", str_peupler(classeLangueNom), ", Boolean ", str_stocker(classeLangueNom), ", Boolean ", str_modifier(classeLangueNom), ", String uri, String ", str_apiMethode(classeLangueNom), ", Handler<AsyncResult<", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, ">>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			tl(2, "try {");
			tl(3, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), " = ", str_recherche(classeLangueNom), classeNomSimple, str_Liste(classeLangueNom), "(", str_requeteSite(classeLangueNom), ", ", str_peupler(classeLangueNom), ", ", str_stocker(classeLangueNom), ", ", str_modifier(classeLangueNom), ", uri, ", str_apiMethode(classeLangueNom), ");");
			tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_listeRecherche(classeLangueNom), "));");
			tl(2, "} catch(Exception e) {");
			tl(3, "LOG.error(String.format(\"", str_recherche(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
			tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_recherche(classeLangueNom), classeNomSimple, str_Liste(classeLangueNom), "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Boolean ", str_peupler(classeLangueNom), ", Boolean ", str_stocker(classeLangueNom), ", Boolean ", str_modifier(classeLangueNom), ", String uri, String ", str_apiMethode(classeLangueNom), ") {");
			tl(2, "ServiceRequest ", str_requeteService(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_RequeteService(classeLangueNom), "();");
			tl(2, "String ", str_entite(classeLangueNom), str_Liste(classeLangueNom), "Str = ", str_requeteSite(classeLangueNom), ".get", str_RequeteService(classeLangueNom), "().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
			tl(2, "String[] ", str_entite(classeLangueNom), str_Liste(classeLangueNom), " = ", str_entite(classeLangueNom), str_Liste(classeLangueNom), "Str == null ? null : ", str_entite(classeLangueNom), str_Liste(classeLangueNom), "Str.split(", q(",\\s*"), ");");
			tl(2, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, ">();");
			tl(2, str_listeRecherche(classeLangueNom), ".set", str_Peupler(classeLangueNom), "(", str_peupler(classeLangueNom), ");");
			tl(2, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(", str_stocker(classeLangueNom), ");");
			tl(2, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
			tl(2, str_listeRecherche(classeLangueNom), ".setC(", classeNomSimple, ".class);");
			tl(2, str_listeRecherche(classeLangueNom), ".set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ");");
			tl(2, "if(", str_entite(classeLangueNom), str_Liste(classeLangueNom), " != null)");
			tl(3, str_listeRecherche(classeLangueNom), ".addFields(", str_entite(classeLangueNom), str_Liste(classeLangueNom), ");");
			if(classeVarModifie != null)
				tl(2, str_listeRecherche(classeLangueNom), ".add(\"json.facet\", \"{max_", classeVarModifie, ":'max(", classeVarModifie, "_indexed_date)'}\");");
			s(wFacets);
			l();
			tl(2, "String id = ", str_requeteService(classeLangueNom), ".getParams().getJsonObject(\"path\").getString(\"id\");");
			tl(2, "if(", classeVarCleUnique, " != null && NumberUtils.isCreatable(", classeVarCleUnique, ")) {");
			tl(3, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"(", classeVarClePrimaire, "_indexed_long:\" + ClientUtils.escapeQueryChars(id) + \" OR ", classeVarId, "_indexed_string:\" + ClientUtils.escapeQueryChars(id) + \")\");");
			tl(2, "} else if(", classeVarCleUnique, " != null) {");
			tl(3, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", classeVarId, "_indexed_string:\" + ClientUtils.escapeQueryChars(id));");
			tl(2, "}");
			if(classeRoleSession || classeRoleUtilisateur) {
				l();
				tl(2, "List<String> roles = Arrays.asList(\"", StringUtils.join(classeRoles, "\", \""), "\");");
				tl(2, "List<String> ", str_roleLires(classeLangueNom), " = Arrays.asList(\"", StringUtils.join(classeRoleLires, "\", \""), "\");");
				tl(2, "if(");
				tl(4, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
				tl(4, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
				tl(4, "&& (", str_modifier(classeLangueNom), " || !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), ", str_roleLires(classeLangueNom), "))");
				tl(4, "&& (", str_modifier(classeLangueNom), " || !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), ", str_roleLires(classeLangueNom), "))");
				tl(4, ") {");
				tl(3, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")) + \" OR \" + \"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId", str_Avant(classeLangueNom), "()).orElse(\"-----\"))");
				tl(5, "+ \" OR ", str_utilisateur(classeLangueNom), str_Cle(classeLangueNom), "s_indexed_longs:\" + Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Cle(classeLangueNom), "()).orElse(0L));");
				tl(2, "}");
			}
			l();
			tl(2, str_requeteService(classeLangueNom), ".getParams().getJsonObject(\"query\").forEach(param", str_Requete(classeLangueNom), " -> {");
			tl(3, "String ", str_entite(classeLangueNom), "Var = null;");
			tl(3, "String ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), " = null;");
			tl(3, "String var", str_Indexe(classeLangueNom), " = null;");
			tl(3, "String ", str_valeur(classeLangueNom), str_Tri(classeLangueNom), " = null;");
			tl(3, "Integer ", str_valeur(classeLangueNom), "Start = null;");
			tl(3, "Integer ", str_valeur(classeLangueNom), "Rows = null;");
			tl(3, "String param", str_Nom(classeLangueNom), " = param", str_Requete(classeLangueNom), ".getKey();");
			tl(3, "Object param", str_Valeurs(classeLangueNom), str_Objet(classeLangueNom), " = param", str_Requete(classeLangueNom), ".getValue();");
			tl(3, "JsonArray param", str_Objets(classeLangueNom), " = param", str_Valeurs(classeLangueNom), str_Objet(classeLangueNom), " instanceof JsonArray ? (JsonArray)param", str_Valeurs(classeLangueNom), str_Objet(classeLangueNom), " : new JsonArray().add(param", str_Valeurs(classeLangueNom), str_Objet(classeLangueNom), ");");
			l();
			tl(3, "try {");
	
			tl(4, "if(param", str_Valeurs(classeLangueNom), str_Objet(classeLangueNom), " != null && \"facet.pivot\".equals(param", str_Nom(classeLangueNom), ")) {");
			tl(5, "Matcher mFacetPivot = Pattern.compile(\"(?:(\\\\{![^\\\\}]+\\\\}))?(.*)\").matcher(StringUtils.join(param", str_Objets(classeLangueNom), ".getList().toArray(), \",\"));");
			tl(5, "boolean foundFacetPivot = mFacetPivot.find();");
			tl(5, "if(foundFacetPivot) {");
			tl(6, "String solrLocalParams = mFacetPivot.group(1);");
			tl(6, "String[] ", str_entite(classeLangueNom), "Vars = mFacetPivot.group(2).trim().split(\",\");");
			tl(6, "String[] vars", str_Indexe(classeLangueNom), " = new String[", str_entite(classeLangueNom), "Vars.length];");
			tl(6, "for(Integer i = 0; i < ", str_entite(classeLangueNom), "Vars.length; i++) {");
			tl(7, str_entite(classeLangueNom), "Var = ", str_entite(classeLangueNom), "Vars[i];");
			tl(7, "vars", str_Indexe(classeLangueNom), "[i] = ", classeNomSimple, ".var", str_Indexe(classeLangueNom), "", classeNomSimple, "(", str_entite(classeLangueNom), "Var);");
			tl(6, "}");
			tl(6, str_listeRecherche(classeLangueNom), ".add(\"facet.pivot\", (solrLocalParams == null ? \"\" : solrLocalParams) + StringUtils.join(vars", str_Indexe(classeLangueNom), ", \",\"));");
			tl(5, "}");
			tl(4, "} else if(param", str_Valeurs(classeLangueNom), str_Objet(classeLangueNom), " != null) {");

			tl(5, "for(Object param", str_Objet(classeLangueNom), " : param", str_Objets(classeLangueNom), ") {");
			tl(6, "switch(param", str_Nom(classeLangueNom), ") {");
	
			tl(7, "case \"q\":");
			tl(8, str_entite(classeLangueNom), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(classeLangueNom), ", \":\"));");
			tl(8, "var", str_Indexe(classeLangueNom), " = \"*\".equals(", str_entite(classeLangueNom), "Var) ? ", str_entite(classeLangueNom), "Var : ", classeNomSimple, ".var", str_Recherche(classeLangueNom), classeNomSimple, "(", str_entite(classeLangueNom), "Var);");
			tl(8, str_valeur(classeLangueNom), str_Indexe(classeLangueNom), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(classeLangueNom), ", \":\")), \"UTF-8\");");
			tl(8, str_valeur(classeLangueNom), str_Indexe(classeLangueNom), " = StringUtils.isEmpty(", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ") ? \"*\" : ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ";");
			tl(8, str_recherche(classeLangueNom), classeNomSimple, "Q(uri, ", str_apiMethode(classeLangueNom), ", ", str_listeRecherche(classeLangueNom), ", ", str_entite(classeLangueNom), "Var, ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ", ", "var", str_Indexe(classeLangueNom), ");");
			tl(8, "break;");
	
			tl(7, "case \"fq\":");
			tl(8, "Matcher mFq = Pattern.compile(\"(\\\\w+):(.+?(?=(\\\\)|\\\\s+OR\\\\s+|\\\\s+AND\\\\s+|$)))\").matcher((String)param", str_Objet(classeLangueNom), ");");
			tl(8, "boolean foundFq = mFq.find();");
			tl(8, "if(foundFq) {");
			tl(9, "StringBuffer sb = new StringBuffer();");
			tl(9, "while(foundFq) {");
			tl(10, str_entite(classeLangueNom), "Var = mFq.group(1).trim();");
			tl(10, str_valeur(classeLangueNom), str_Indexe(classeLangueNom), " = mFq.group(2).trim();");
			tl(10, "var", str_Indexe(classeLangueNom), " = ", classeNomSimple, ".var", str_Indexe(classeLangueNom), "", classeNomSimple, "(", str_entite(classeLangueNom), "Var);");
			tl(10, "String ", str_entite(classeLangueNom), "Fq = ", str_recherche(classeLangueNom), classeNomSimple, "Fq(uri, ", str_apiMethode(classeLangueNom), ", ", str_listeRecherche(classeLangueNom), ", ", str_entite(classeLangueNom), "Var, ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ", ", "var", str_Indexe(classeLangueNom), ");");
			tl(10, "mFq.appendReplacement(sb, ", str_entite(classeLangueNom), "Fq);");
			tl(10, "foundFq = mFq.find();");
			tl(9, "}");
			tl(9, "mFq.appendTail(sb);");
			tl(9, str_listeRecherche(classeLangueNom), ".addFilterQuery(sb.toString());");
			tl(8, "}");
			tl(8, "break;");

			tl(7, "case \"sort\":");
			tl(8, str_entite(classeLangueNom), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(classeLangueNom), ", \" \"));");
			tl(8, str_valeur(classeLangueNom), str_Indexe(classeLangueNom), " = StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(classeLangueNom), ", \" \"));");
			tl(8, "var", str_Indexe(classeLangueNom), " = ", classeNomSimple, ".var", str_Indexe(classeLangueNom), classeNomSimple, "(", str_entite(classeLangueNom), "Var);");
			tl(8, str_recherche(classeLangueNom), classeNomSimple, "Sort(uri, ", str_apiMethode(classeLangueNom), ", ", str_listeRecherche(classeLangueNom), ", ", str_entite(classeLangueNom), "Var, ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ", ", "var", str_Indexe(classeLangueNom), ");");
			tl(8, "break;");
//	
//			tl(7, "case \"fl\":");
//			tl(8, str_entite(langueNom), "Var = StringUtils.trim((String)param", str_Objet(langueNom), ");");
//			tl(8, "var", str_Indexe(langueNom), " = ", classeNomSimple, ".var", str_Indexe(langueNom), classeNomSimple, "(", str_entite(langueNom), "Var);");
//			tl(8, str_liste(langueNom), str_Recherche(langueNom), ".addField(var", str_Indexe(langueNom), ");");
//			tl(8, "break;");
	
			tl(7, "case \"start\":");
			tl(8, str_valeur(classeLangueNom), "Start = param", str_Objet(classeLangueNom), " instanceof Integer ? (Integer)param", str_Objet(classeLangueNom), " : Integer.parseInt(param", str_Objet(classeLangueNom), ".toString());");
			tl(8, str_recherche(classeLangueNom), classeNomSimple, "Start(uri, ", str_apiMethode(classeLangueNom), ", ", str_listeRecherche(classeLangueNom), ", ", str_valeur(classeLangueNom), "Start);");
			tl(8, "break;");
	
			tl(7, "case \"rows\":");
			tl(8, str_valeur(classeLangueNom), "Rows = param", str_Objet(classeLangueNom), " instanceof Integer ? (Integer)param", str_Objet(classeLangueNom), " : Integer.parseInt(param", str_Objet(classeLangueNom), ".toString());");
			tl(8, str_recherche(classeLangueNom), classeNomSimple, "Rows(uri, ", str_apiMethode(classeLangueNom), ", ", str_listeRecherche(classeLangueNom), ", ", str_valeur(classeLangueNom), "Rows);");
			tl(8, "break;");
	
			tl(7, "case \"facet\":");
			tl(8, str_listeRecherche(classeLangueNom), ".add(\"facet\", ((Boolean)param", str_Objet(classeLangueNom), ").toString());");
			tl(8, "break;");
	
			tl(7, "case \"facet.range.start\":");
			tl(8, "String startMathStr = (String)param", str_Objet(classeLangueNom), ";");
			tl(8, "Date start = DateMathParser.parseMath(null, startMathStr);");
			tl(8, str_listeRecherche(classeLangueNom), ".add(\"facet.range.start\", start.toInstant().toString());");
			tl(8, "break;");
	
			tl(7, "case \"facet.range.end\":");
			tl(8, "String endMathStr = (String)param", str_Objet(classeLangueNom), ";");
			tl(8, "Date end = DateMathParser.parseMath(null, endMathStr);");
			tl(8, str_listeRecherche(classeLangueNom), ".add(\"facet.range.end\", end.toInstant().toString());");
			tl(8, "break;");
	
			tl(7, "case \"facet.range.gap\":");
			tl(8, "String gap = (String)param", str_Objet(classeLangueNom), ";");
			tl(8, str_listeRecherche(classeLangueNom), ".add(\"facet.range.gap\", gap);");
			tl(8, "break;");
	
			tl(7, "case \"facet.range\":");
			tl(8, "Matcher mFacetRange = Pattern.compile(\"(?:(\\\\{![^\\\\}]+\\\\}))?(.*)\").matcher((String)param", str_Objet(classeLangueNom), ");");
			tl(8, "boolean foundFacetRange = mFacetRange.find();");
			tl(8, "if(foundFacetRange) {");
			tl(9, "String solrLocalParams = mFacetRange.group(1);");
			tl(9, str_entite(classeLangueNom), "Var = mFacetRange.group(2).trim();");
			tl(9, "var", str_Indexe(classeLangueNom), " = ", classeNomSimple, ".var", str_Indexe(classeLangueNom), "", classeNomSimple, "(", str_entite(classeLangueNom), "Var);");
			tl(9, str_listeRecherche(classeLangueNom), ".add(\"facet.range\", (solrLocalParams == null ? \"\" : solrLocalParams) + var", str_Indexe(classeLangueNom), ");");
			tl(8, "}");
			tl(8, "break;");
	
			tl(7, "case \"facet.field\":");
			tl(8, str_entite(classeLangueNom), "Var = (String)param", str_Objet(classeLangueNom), ";");
			tl(8, "var", str_Indexe(classeLangueNom), " = ", classeNomSimple, ".var", str_Indexe(classeLangueNom), classeNomSimple, "(", str_entite(classeLangueNom), "Var);");
			tl(8, "if(var", str_Indexe(classeLangueNom), " != null)");
			tl(9, str_listeRecherche(classeLangueNom), ".addFacetField(var", str_Indexe(classeLangueNom), ");");
			tl(8, "break;");
	
			tl(7, "case \"var\":");
			tl(8, str_entite(classeLangueNom), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(classeLangueNom), ", \":\"));");
			tl(8, str_valeur(classeLangueNom), str_Indexe(classeLangueNom), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(classeLangueNom), ", \":\")), \"UTF-8\");");
			tl(8, str_recherche(classeLangueNom), classeNomSimple, "Var(uri, ", str_apiMethode(classeLangueNom), ", ", str_listeRecherche(classeLangueNom), ", ", str_entite(classeLangueNom), "Var, ", str_valeur(classeLangueNom), str_Indexe(classeLangueNom), ");");
			tl(8, "break;");
	
			tl(6, "}");
			tl(5, "}");
			tl(5, str_recherche(classeLangueNom), classeNomSimple, "Uri(uri, ", str_apiMethode(classeLangueNom), ", ", str_listeRecherche(classeLangueNom), ");");
			tl(4, "}");
			tl(3, "} catch(Exception e) {");
			tl(4, "ExceptionUtils.rethrow(e);");
			tl(3, "}");

			tl(2, "});");
			if(classeVarCree != null) {
				tl(2, "if(\"*:*\".equals(", str_listeRecherche(classeLangueNom), ".getQuery()) && ", str_listeRecherche(classeLangueNom), ".getSorts().size() == 0) {");
				if(classeTrisVar != null && classeTrisVar.size() > 0) {
					for(int i = 0; i < classeTrisVar.size(); i++) {
						String classeTriVar = classeTrisVar.get(i);
						String classeTriOrdre = classeTrisOrdre.get(i);
						String classeTriSuffixeType = classeTrisSuffixeType.get(i);
						tl(3, str_listeRecherche(classeLangueNom), ".addSort(\"", classeTriVar, "_indexed", classeTriSuffixeType, "\", ORDER.", classeTriOrdre, ");");
					}
				}
				else {
					tl(3, str_listeRecherche(classeLangueNom), ".addSort(\"", classeVarCree, "_indexed_date\", ORDER.desc);");
				}
				tl(2, "}");
			}
			tl(2, str_recherche(classeLangueNom), classeNomSimple, "2(", str_requeteSite(classeLangueNom), ", ", str_peupler(classeLangueNom), ", ", str_stocker(classeLangueNom), ", ", str_modifier(classeLangueNom), ", uri, ", str_apiMethode(classeLangueNom), ", ", str_listeRecherche(classeLangueNom), ");");
			tl(2, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), str_PourClasse(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
			tl(2, "return ", str_listeRecherche(classeLangueNom), ";");
			tl(1, "}");
			tl(1, "public void ", str_recherche(classeLangueNom), classeNomSimple, "2(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Boolean ", str_peupler(classeLangueNom), ", Boolean ", str_stocker(classeLangueNom), ", Boolean ", str_modifier(classeLangueNom), ", String uri, String ", str_apiMethode(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), ") {");
			tl(1, "}");
			/////////////
			// definir //
			/////////////
			l();
			tl(1, "public void ", str_definir(classeLangueNom), classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			tl(2, "try {");
			tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
			tl(3, "SqlConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
			tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			if(sqlTables) {
				tl(3, str_connexionSql(classeLangueNom), ".preparedQuery(\"SELECT * FROM ", classeNomSimple, " WHERE ", classeVarClePrimaire, "=$1\")");
			} else {
				tl(3, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_", str_definir(classeLangueNom), ")");
			}
			tl(5, ".collecting(Collectors.toList())");
			tl(5, ".execute(Tuple.of(", classeVarClePrimaire, ")");
			tl(5, ", ", str_definir(classeLangueNom), "Async");
			tl(3, "-> {");
			tl(4, "if(", str_definir(classeLangueNom), "Async.succeeded()) {");
			tl(5, "try {");
			tl(6, "for(Row definition : ", str_definir(classeLangueNom), "Async.result().value()) {");
			if(sqlTables) {
				tl(7, "for(Integer i = 0; i < definition.size(); i++) {");
				tl(8, "String columnName = definition.getColumnName(i);");
				tl(8, "Object columnValue = definition.getValue(i);");
				tl(8, "if(!\"", classeVarClePrimaire, "\".equals(columnName)) {");
				tl(9, "try {");
				tl(10, "o.", str_definir(classeLangueNom), str_PourClasse(classeLangueNom), "(columnName, columnValue);");
				tl(9, "} catch(Exception e) {");
				tl(10, "LOG.error(String.format(\"", str_definir(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
				tl(9, "}");
				tl(8, "}");
				tl(7, "}");
			} else {
				tl(7, "try {");
				tl(8, "o.", str_definir(classeLangueNom), str_PourClasse(classeLangueNom), "(definition.getString(0), definition.getString(1));");
				tl(7, "} catch(Exception e) {");
				tl(8, "LOG.error(String.format(\"", str_definir(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
				tl(7, "}");
			}
			tl(6, "}");
			tl(6, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(5, "} catch(Exception e) {");
			tl(6, "LOG.error(String.format(\"", str_definir(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
			tl(6, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(5, "}");
			tl(4, "} else {");
			tl(5, "LOG.error(String.format(\"", str_definir(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", ", str_definir(classeLangueNom), "Async.cause()));");
			tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(", str_definir(classeLangueNom), "Async.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "LOG.error(String.format(\"", str_definir(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
			tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			///////////////
			// attribuer //
			///////////////
			tl(1, "public void ", str_attribuer(classeLangueNom), classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			if(wAttribuerSql.getEmpty()) {
				tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			} else {
				tl(2, "try {");
				tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
				tl(3, "SqlConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
				tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
				if(sqlTables) {
					tl(3, str_connexionSql(classeLangueNom), ".preparedQuery(\"", wAttribuerSql, "\")");
				} else {
					tl(3, str_connexionSql(classeLangueNom), ".preparedQuery(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_", str_attribuer(classeLangueNom), ")");
				}
				tl(5, ".collecting(Collectors.toList())");
				if(sqlTables) {
					tl(5, ".execute(Tuple.of(", wAttribuerSqlParams, ")");
				} else {
					tl(5, ".execute(Tuple.of(", classeVarClePrimaire, ", ", classeVarClePrimaire, ")");
				}
				tl(5, ", ", str_attribuer(classeLangueNom), "Async");
				tl(3, "-> {");
				tl(4, "try {");
				tl(5, "if(", str_attribuer(classeLangueNom), "Async.succeeded()) {");
				tl(6, "if(", str_attribuer(classeLangueNom), "Async.result() != null) {");
				tl(7, "for(Row definition : ", str_attribuer(classeLangueNom), "Async.result().value()) {");
				if(sqlTables) {
					tl(8, "o.", str_attribuer(classeLangueNom), str_PourClasse(classeLangueNom), "(definition.getString(1), definition.getLong(0));");
				}
				else {
					tl(8, "if(pk.equals(definition.getLong(0)))");
					tl(9, "o.", str_attribuer(classeLangueNom), str_PourClasse(classeLangueNom), "(definition.getString(2), definition.getLong(1));");
					tl(8, "else");
					tl(9, "o.", str_attribuer(classeLangueNom), str_PourClasse(classeLangueNom), "(definition.getString(3), definition.getLong(0));");
				}
				tl(7, "}");
				tl(6, "}");
				tl(6, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
				tl(5, "} else {");
				tl(6, "LOG.error(String.format(\"", str_attribuer(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \", ", str_attribuer(classeLangueNom), "Async.cause()));");
				tl(6, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(", str_attribuer(classeLangueNom), "Async.cause()));");
				tl(5, "}");
				tl(4, "} catch(Exception e) {");
				tl(5, "LOG.error(String.format(\"", str_attribuer(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
				tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
				tl(4, "}");
				tl(3, "});");
				tl(2, "} catch(Exception e) {");
				tl(3, "LOG.error(String.format(\"", str_attribuer(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
				tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
				tl(2, "}");
			}
			tl(1, "}");
			l();
			/////////////
			// indexer //
			/////////////
			tl(1, "public void ", str_indexer(classeLangueNom), classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
			tl(2, "try {");
			tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_();");
			tl(3, "List<Long> pks = Optional.ofNullable(", str_requeteApi(classeLangueNom), ").map(r -> r.getPks()).orElse(new ArrayList<>());");
			tl(3, "List<String> classes = Optional.ofNullable(", str_requeteApi(classeLangueNom), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
			tl(3, "o.", str_initLoin(classeLangueNom), str_PourClasse(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
			tl(3, "o.", str_indexer(classeLangueNom), str_PourClasse(classeLangueNom), "();");
			tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(2, "} catch(Exception e) {");
			tl(3, "LOG.error(String.format(\"", str_indexer(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
			tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			///////////////
			// recharger //
			///////////////
			if(classeApiMethodes.contains("PATCH")) {
				tl(1, "public void ", str_recharger(classeLangueNom), classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<ServiceResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
				tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
				tl(2, "try {");
				tl(3, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_();");
				tl(3, "List<Long> pks = Optional.ofNullable(", str_requeteApi(classeLangueNom), ").map(r -> r.getPks()).orElse(new ArrayList<>());");
				tl(3, "List<String> classes = Optional.ofNullable(", str_requeteApi(classeLangueNom), ").map(r -> r.getClasses()).orElse(new ArrayList<>());");
				tl(3, "Boolean ", str_recharger(classeLangueNom), " = !\"false\".equals(", str_requeteSite(classeLangueNom), ".get", str_Requete(classeLangueNom), "Vars().get(\"", str_recharger(classeLangueNom), "\"));");
				tl(3, "if(", str_recharger(classeLangueNom), " && !Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "()).map(JsonObject::isEmpty).orElse(true)) {");
				tl(4, str_ListeRecherche(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", str_ListeRecherche(classeLangueNom), "<", classeNomSimple, ">();");
				tl(4, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
				tl(4, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
				tl(4, str_listeRecherche(classeLangueNom), ".setC(", classeNomSimple, ".class);");
				tl(4, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_modifie(classeLangueNom), "_indexed_date:[\" + DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_().get", str_Cree(classeLangueNom), "()", ".toInstant(), ZoneId.of(\"UTC\"))) + \" TO *]\");");
				s(wIndexerFacetAdd);
				tl(4, str_listeRecherche(classeLangueNom), ".setRows(1000);");
				tl(4, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), str_ListeRecherche(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
				tl(4, "List<Future> futures = new ArrayList<>();");
				l();
				tl(4, "for(int i=0; i < pks.size(); i++) {");
				tl(5, "Long ", classeVarClePrimaire, "2 = pks.get(i);");
				tl(5, "String ", str_classeNomSimple(classeLangueNom), "2 = classes.get(i);");
				s(wIndexerFacetFor);
				tl(4, "}");
				l();
				tl(4, "CompositeFuture.all(futures).onComplete(a -> {");
				tl(5, "if(a.succeeded()) {");
	//			tl(6, "LOG.info(\"", str_Recharger(classeLangueNom), " ", str_relations(classeLangueNom), " ", str_a_réussi(classeLangueNom), ". \");");
				tl(6, classeNomSimpleApiServiceImpl, " service = new ", classeNomSimpleApiServiceImpl, "(eventBus, config, ", str_executeurTravailleur(classeLangueNom), ", pgPool, ", str_clientSolr(classeLangueNom), activerOpenIdConnect ? ", oauth2AuthenticationProvider, authorizationProvider" : "", ");");
				tl(6, "List<Future> futures2 = new ArrayList<>();");
				tl(6, "for(", classeNomSimple, " o2 : ", str_listeRecherche(classeLangueNom), ".getList()) {");
				tl(7, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), "2 = ", str_generer(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), str_Pour(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "(), ", str_clientSolr(classeLangueNom), ", ", str_requeteSite(classeLangueNom), ".get", str_RequeteService(classeLangueNom), "(), new JsonObject());");
				tl(7, "o2.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), "2);");
				tl(7, "futures2.add(");
				tl(8, "service.patch", classeNomSimple, "Future(o2, false).onFailure(ex -> {");
				tl(9, "LOG.error(String.format(\"", classeNomSimple, " %s ", str_a_échoué(classeLangueNom), ". \", o2.get", StringUtils.capitalize(classeVarClePrimaire), "()), ex);");
				tl(9, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(ex));");
				tl(8, "})");
				tl(7, ");");
				tl(6, "}");
				l();
				tl(6, "CompositeFuture.all(futures2).onComplete(b -> {");
				tl(7, "if(b.succeeded()) {");
	//			tl(8, "LOG.info(\"", str_Recharger(classeLangueNom), " ", classeNomSimple, " ", str_a_réussi(classeLangueNom), ". \");");
				tl(8, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
				tl(7, "} else {");
				tl(8, "LOG.error(\"", str_Recharger(classeLangueNom), " ", str_relations(classeLangueNom), " ", str_a_échoué(classeLangueNom), ". \", b.cause());");
				tl(8, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", b);");
				tl(7, "}");
				tl(6, "});");
				tl(5, "} else {");
				tl(6, "LOG.error(\"", str_Recharger(classeLangueNom), " ", str_relations(classeLangueNom), " ", str_a_échoué(classeLangueNom), ". \", a.cause());");
				tl(6, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
				tl(5, "}");
				tl(4, "});");
	
	
				tl(3, "} else {");
				tl(4, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
				tl(3, "}");
				tl(2, "} catch(Exception e) {");
				tl(3, "LOG.error(String.format(\"", str_recharger(classeLangueNom), classeNomSimple, " ", str_a_échoué(classeLangueNom), ". \"), e);");
				tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
				tl(2, "}");
				tl(1, "}");
			}
	
			tl(0, "}");

			auteurGenApiServiceImpl.flushClose();
			System.out.println("Ecrire: " + classeCheminGenApiServiceImpl); 
		}
	}
}
