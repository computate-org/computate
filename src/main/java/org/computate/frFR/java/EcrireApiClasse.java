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
	 * r.enUS: operationRequest
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
	 * r: documentSolr
	 * r.enUS: solrDocument
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
			if(classePartsSiteContexte == null)
				throw new Exception("Ajouter une classe avec le commentaire: MotCle: classeNomSimpleSiteContexte");
			auteurGenApiService.l("import ", classePartsSiteContexte.documentSolr.get("classeNomCanonique_" + classeLangueNom + "_stored_string"), ";");
//			auteurGenApiService.l("import ", classeNomEnsemble, ".", classeNomSimple, "ApiServiceVertxEBProxy;");
			auteurGenApiService.l("import io.vertx.codegen.annotations.ProxyGen;");
			auteurGenApiService.l("import io.vertx.ext.web.api.generator.WebApiServiceGen;");
			auteurGenApiService.l("import io.vertx.serviceproxy.ServiceBinder;");
			auteurGenApiService.l("import io.vertx.core.AsyncResult;");
			auteurGenApiService.l("import io.vertx.core.Handler;");
			auteurGenApiService.l("import io.vertx.core.Vertx;");
			auteurGenApiService.l("import io.vertx.core.json.JsonObject;");
			auteurGenApiService.l("import io.vertx.core.json.JsonArray;");
			auteurGenApiService.l("import io.vertx.ext.web.api.OperationRequest;");
			auteurGenApiService.l("import io.vertx.ext.web.api.OperationResponse;");
			auteurGenApiService.l();
			auteurGenApiService.l("/**");
			auteurGenApiService.l(" * ", str_Traduire(classeLangueNom), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueGenApiServiceLangue = (String)classeDoc.get("classeNomCanoniqueGenApiService_" + langueNom + "_stored_string");
				auteurGenApiService.l(" * ", str_classeNomCanonique(classeLangueNom), ".", langueNom, ": ", classeNomCanoniqueGenApiServiceLangue);
			}
			auteurGenApiService.l(" * Gen: false");
			auteurGenApiService.l(" **/");
			auteurGenApiService.l("@WebApiServiceGen");
			auteurGenApiService.l("@ProxyGen");
			auteurGenApiService.s("public interface ", classeNomSimpleGenApiService, " {");
			auteurGenApiService.l();
//			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");
			auteurGenApiService.tl(1, "static void ", str_enregistrer(classeLangueNom), "Service(", classePartsSiteContexte.nomSimple(classeLangueNom), " ", str_siteContexte(classeLangueNom), ", Vertx vertx) {");
			auteurGenApiService.tl(2, "new ServiceBinder(vertx).setAddress(", q(appliNom, "-", classeLangueNom, "-", classeNomSimple), ").register(", classeNomSimpleGenApiService, ".class, new ", classeNomSimpleApiServiceImpl, "(", str_siteContexte(classeLangueNom), "));");
			auteurGenApiService.tl(1, "}");
			auteurGenApiService.l();
//			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");
			auteurGenApiService.tl(1, "static ", classeNomSimpleGenApiService, " ", str_creer(classeLangueNom), "(", classePartsSiteContexte.nomSimple(classeLangueNom), " ", str_siteContexte(classeLangueNom), ", Vertx vertx) {");
			auteurGenApiService.tl(2, "return new ", classeNomSimpleApiServiceImpl, "(", str_siteContexte(classeLangueNom), ");");
			auteurGenApiService.tl(1, "}");
			auteurGenApiService.l();
			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");
			auteurGenApiService.tl(1, "static ", classeNomSimpleGenApiService, " ", str_creer(classeLangueNom), "Proxy(Vertx vertx, String ", str_addresse(classeLangueNom), ") {");
			auteurGenApiService.tl(2, "return new ", classeNomSimpleGenApiService, "VertxEBProxy(vertx, ", str_addresse(classeLangueNom), ");");
			auteurGenApiService.tl(1, "}");
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
						auteurGenApiService.l("OperationRequest ", str_operationRequete(classeLangueNom), ", Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ");");
					}
	
					auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "(");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						auteurGenApiService.s("JsonObject body, ");
					auteurGenApiService.l("OperationRequest ", str_operationRequete(classeLangueNom), ", Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ");");
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
	 * r: documentSolr
	 * r.enUS: solrDocument
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
			auteurApiServiceImpl.l("import ", classePartsSiteContexte.documentSolr.get("classeNomCanonique_" + classeLangueNom + "_stored_string"), ";");
//			auteurGenApiService.l("import ", classeNomEnsemble, ".", classeNomSimple, "ApiServiceVertxEBProxy;");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.l("/**");
			auteurApiServiceImpl.l(" * ", str_Traduire(classeLangueNom), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueApiServiceImplLangue = (String)classeDoc.get("classeNomCanoniqueApiServiceImpl_" + langueNom + "_stored_string");
				l(" * ", str_classeNomCanonique(classeLangueNom), ".", langueNom, ": ", classeNomCanoniqueApiServiceImplLangue);
			}
			auteurApiServiceImpl.l(" **/");
			auteurApiServiceImpl.l("public class ", classeNomSimpleApiServiceImpl, " extends ", classeNomSimpleGenApiServiceImpl, " {");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.tl(1, "public ", classeNomSimpleApiServiceImpl, "(", classePartsSiteContexte.nomSimple(classeLangueNom), " ", str_siteContexte(classeLangueNom), ") {");
			auteurApiServiceImpl.tl(2, "super(", str_siteContexte(classeLangueNom), ");");
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
	 * r.enUS: operationRequest
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
	 * r: OperationRequete
	 * r.enUS: OperationRequest
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
	public void ecrireGenApiServiceImpl(String classeLangueNom) throws Exception {
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

			ToutEcrivain wPks = ToutEcrivain.create();

			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
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
							entiteAttribuerNomSimple = (String)doc.get("entiteAttribuerNomSimple_" + classeLangueNom + "_stored_string");
							entiteDefinir = (Boolean)doc.get("entiteDefinir_stored_boolean");
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
							entiteNomCanonique = (String)doc.get("entiteNomCanonique_" + classeLangueNom + "_stored_string");
							entiteNomCanoniqueGenerique = (String)doc.get("entiteNomCanoniqueGenerique_" + classeLangueNom + "_stored_string");
							entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + classeLangueNom + "_stored_string");
							entiteNomSimpleCompletGenerique = (String)doc.get("entiteNomSimpleCompletGenerique_" + classeLangueNom + "_stored_string");
							entiteNomSimple = (String)doc.get("entiteNomSimple_" + classeLangueNom + "_stored_string");

							//////////
							// wPks //
							//////////

							if(entiteAttribuer) {
								if(entiteListeTypeJson == null) {
									wPks.tl(3, "if(o.get", entiteVarCapitalise, "() != null) {");
									wPks.tl(4, "if(!pks.contains(o.get", entiteVarCapitalise, "())) {");
									wPks.tl(5, "pks.add(o.get", entiteVarCapitalise, "());");
									wPks.tl(5, "classes.add(\"", entiteAttribuerNomSimple, "\");");
									wPks.tl(4, "}");
									wPks.tl(3, "}");
								}
								else {
									wPks.tl(3, "for(Long pk : o.get", entiteVarCapitalise, "()) {");
									wPks.tl(4, "if(!pks.contains(pk)) {");
									wPks.tl(5, "pks.add(pk);");
									wPks.tl(5, "classes.add(\"", entiteAttribuerNomSimple, "\");");
									wPks.tl(4, "}");
									wPks.tl(3, "}");
								}
							}
	
							/////////////////
							// codeApiGet //
							/////////////////

							if(classeIndexe && entiteIndexe) {
								wApiGet.tl(3, "case \"", entiteVar, "\":");
								wApiGet.tl(4, "return \"", entiteVar, "_indexed", entiteSuffixeType, "\";");
							}
							
							///////////////////////
							// codeApiGenererGet //
							///////////////////////
							o = wApiGenererGet;
							if(classeIndexe && entiteStocke) {
				//				tl(4, "if(", q(entiteVar, "_stored", entiteSuffixeType), ".equals(entiteVarStocke)) {");
								if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList, VAL_nomCanoniqueSet, VAL_nomCanoniqueHashSet)) {
									if(VAL_nomCanoniqueBoolean.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Boolean)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueDate.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueTimestamp.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ").toInstant().atZone(ZoneId.systemDefault()).toZonedDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueLocalDate.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Long)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(BigDecimal.valueOf((Double)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Double)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Float)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)) {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Integer)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else {
										l();
										tl(4, "{");
										tl(5, entiteNomSimpleComplet, " ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
										tl(5, "w.t(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entiteVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".size(); k++) {");
										tl(6, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Valeurs(classeLangueNom), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(((String)", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), "));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
								}
								else {
									l();
									tl(4, "", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " = o.get", entiteVarCapitalise, "();");
				//					tl(4, "entiteValeur = Optional.ofNullable(", str_documentSolr(langueNom), ".getFieldValues(", q(entiteVar, "_stored", entiteSuffixeType), ")).map(Collection<Object>::stream).orElseGet(Stream::empty).findFirst().orElse(null);");
				//					tl(4, "entiteValeur = ", str_documentSolr(langueNom), ".getFieldValues(", q(entiteVar, "_stored", entiteSuffixeType), ").stream().findFirst().orElse(null);");
				//					tl(5, "entiteValeur = ", str_documentSolr(langueNom), ".getFieldValues(", q(entiteVar, "_stored", entiteSuffixeType), ").stream().findFirst().orElse(null);");
									tl(4, "if(", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), " != null)");
									if (VAL_nomCanoniqueBoolean.equals(entiteSolrNomCanonique)) {
				//						tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
				
										// tomorrow put this line everywhere. 
										tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ");");
									} else if (VAL_nomCanoniqueDate.equals(entiteSolrNomCanonique)) {
										if (VAL_nomCanoniqueTimestamp.equals(entiteNomCanonique)) {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), "));");
										} else if (VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanonique)) {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toZonedDateTime());");
											tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), "));");
										} else if (VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanonique)) {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), "));");
										} else if (VAL_nomCanoniqueLocalDate.equals(entiteNomCanonique)) {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());");
											tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), "));");
										} else {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entiteValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), "));");
										}
									} else if (VAL_nomCanoniqueLong.equals(entiteSolrNomCanonique)) {
				//						tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
										tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ");");
									} else if (VAL_nomCanoniqueDouble.equals(entiteSolrNomCanonique)) {
										if (VAL_nomCanoniqueBigDecimal.equals(entiteNomCanonique)) {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
											tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ");");
										}
										else {
				//							tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
											tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ");");
										}
									} else if (VAL_nomCanoniqueFloat.equals(entiteSolrNomCanonique)) {
				//						tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
										tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ");");
									} else if (VAL_nomCanoniqueInteger.equals(entiteSolrNomCanonique)) {
				//						tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
										tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", ", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), ");");
									}
									else {
				//						tl(5, "Object entiteStr = entiteValeur == null ? ", q("null"), " : entiteValeur;");
										tl(5, "w.tl(3, ", str_entite(classeLangueNom), "", str_Numero(classeLangueNom), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entiteVar), ": "), ", w.qjs(", str_entite(classeLangueNom), "", str_Valeur(classeLangueNom), "));");
									}
								}
				//				tl(3, ");");
				//				tl(3, "}");
							}
					
							////////////////////////
							// codeApiGenererPatch //
							////////////////////////
							o = wApiGenererPatch;

							if(classeSauvegarde) {
								Integer tBase = 3;
								if(BooleanUtils.isTrue(entiteAttribuer)) {
									if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
						
										if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
											tl(tBase + 2, "case \"add", entiteVarCapitalise, "\":");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", Long.parseLong(", str_requete(classeLangueNom), "Json.get", entiteListeNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "))", "));");
											tl(tBase + 3, "break;");
					
											tl(tBase + 2, "case \"addAll", entiteVarCapitalise, "\":");
											tl(tBase + 3, entiteNomSimpleVertxJson, " addAll", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), " = ", str_requete(classeLangueNom), "Json.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), ");");
											tl(tBase + 3, "for(Integer i = 0; i <  addAll", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), ".size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", addAll", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), ".get", entiteListeNomSimpleVertxJson, "(i)", "));");
											tl(tBase + 3, "}");
											tl(tBase + 3, "break;");
						
											tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
											tl(tBase + 3, entiteNomSimpleVertxJson, " set", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), " = ", str_requete(classeLangueNom), "Json.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), ");");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_clearA1);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), "));");
					
											tl(tBase + 3, "for(Integer i = 0; i <  set", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), ".size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", set", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), ".get", entiteListeNomSimpleVertxJson, "(i)", "));");
											tl(tBase + 3, "}");
											tl(tBase + 3, "break;");
							
											tl(tBase + 2, "case \"remove", entiteVarCapitalise, "\":");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", Long.parseLong(", str_requete(classeLangueNom), "Json.getString(", str_methodeNom(classeLangueNom), "))));");
											tl(tBase + 3, "break;");
										}
										else {
											tl(tBase + 2, "case \"add", entiteVarCapitalise, "\":");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteAttribuerVar), ", Long.parseLong(", str_requete(classeLangueNom), "Json.get", entiteListeNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), ")), ", q(entiteVar), ", ", classeVarClePrimaire, "));");
											tl(tBase + 3, "break;");
					
											tl(tBase + 2, "case \"addAll", entiteVarCapitalise, "\":");
											tl(tBase + 3, entiteNomSimpleVertxJson, " addAll", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), " = ", str_requete(classeLangueNom), "Json.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), ");");
											tl(tBase + 3, "for(Integer i = 0; i <  addAll", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), ".size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setA2);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entiteAttribuerVar), ", ", "addAll", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), ".get", entiteListeNomSimpleVertxJson, "(i), ", q(entiteVar), ", ", classeVarClePrimaire, "));");
											tl(tBase + 3, "}");
											tl(tBase + 3, "break;");
						
											tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
											tl(tBase + 3, entiteNomSimpleVertxJson, " set", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), " = ", str_requete(classeLangueNom), "Json.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), ");");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_clearA2);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteAttribuerVar), ", Long.parseLong(", str_requete(classeLangueNom), "Json.get", entiteListeNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "))", ", ", q(entiteVar), ", ", classeVarClePrimaire, "));");
					
											tl(tBase + 3, "for(Integer i = 0; i <  set", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), ".size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setA2);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entiteAttribuerVar), ", set", entiteVarCapitalise, "", str_Valeurs(classeLangueNom), ".get", entiteListeNomSimpleVertxJson, "(i), ", q(entiteVar), ", ", classeVarClePrimaire, "));");
											tl(tBase + 3, "}");
											tl(tBase + 3, "break;");
							
											tl(tBase + 2, "case \"remove", entiteVarCapitalise, "\":");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteAttribuerVar), ", Long.parseLong(", str_requete(classeLangueNom), "Json.getString(", str_methodeNom(classeLangueNom), "))", ", ", q(entiteVar), ", ", classeVarClePrimaire, "));");
											tl(tBase + 3, "break;");
										}
									}
									else {
						
										tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
										if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
											tl(tBase + 3, "o2.set", entiteVarCapitalise, "(", str_requete(classeLangueNom), "Json.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setA1);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", o2.get", entiteVarCapitalise, "()));");
										}
										else {
											tl(tBase + 3, "o2.set", entiteVarCapitalise, "(", str_requete(classeLangueNom), "Json.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setA2);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteAttribuerVar), ", o2.get", entiteVarCapitalise, "()", ", ", q(entiteVar), ", ", classeVarClePrimaire, "));");
										}
										tl(tBase + 3, "break;");
						
										tl(tBase + 2, "case \"remove", entiteVarCapitalise, "\":");
										if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
											tl(tBase + 3, "o2.set", entiteVarCapitalise, "(", str_requete(classeLangueNom), "Json.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", ", classeVarClePrimaire, ", ", q(entiteAttribuerVar), ", o2.get", entiteVarCapitalise, "()));");
										}
										else {
											tl(tBase + 3, "o2.set", entiteVarCapitalise, "(", str_requete(classeLangueNom), "Json.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
											tl(tBase + 3, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entiteAttribuerVar), ", o2.get", entiteVarCapitalise, "()", ", ", q(entiteVar), ", ", classeVarClePrimaire, "));");
										}
										tl(tBase + 3, "break;");
									}
						
								}
								else if(BooleanUtils.isTrue(entiteDefinir)) {
									if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
						
										tl(tBase + 2, "case \"add", entiteVarCapitalise, "\":");
										tl(tBase + 3, "", str_requete(classeLangueNom), "Json.getJsonArray(", str_methodeNom(classeLangueNom), ").forEach((v) -> {");
										tl(tBase + 4, "o2.add", entiteVarCapitalise, "((", entiteListeNomSimpleVertxJson, ")v);");
										tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_addA);");
										tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entiteVar), ", o2.get", entiteVarCapitalise, "()", ", ", classeVarClePrimaire, "));");
										tl(tBase + 3, "});");
						
										tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
										tl(tBase + 3, "o2.get", entiteVarCapitalise, "().clear();");
										tl(tBase + 3, "", str_requete(classeLangueNom), "Json.getJsonArray(", str_methodeNom(classeLangueNom), ").forEach((v) -> {");
										tl(tBase + 4, "o2.add", entiteVarCapitalise, "((", entiteListeNomSimpleVertxJson, ")v);");
										tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD);");
										tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(\"", entiteVar, "\", o2.json", entiteVarCapitalise, "(), ", classeVarClePrimaire, "));");
										tl(tBase + 3, "});");
									}
									else {
						
										tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
										tl(tBase + 3, "if(", str_requete(classeLangueNom), "Json.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), ") == null) {");
										tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_removeD);");
										tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", classeVarClePrimaire, ", \"", entiteVar, "\"));");
										tl(tBase + 3, "} else {");
										tl(tBase + 4, "o2.set", entiteVarCapitalise, "(", str_requete(classeLangueNom), "Json.get", entiteNomSimpleVertxJson, "(", str_methodeNom(classeLangueNom), "));");
										tl(tBase + 4, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_setD);");
										tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(\"", entiteVar, "\", o2.json", entiteVarCapitalise, "(), ", classeVarClePrimaire, "));");
										tl(tBase + 3, "}");
									}
						
									tl(tBase + 3, "break;");
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
	
			o = auteurGenApiServiceImpl;
			tl(0, "");
			l("/**");
			l(" * ", str_Traduire(classeLangueNom), ": false");
			for(String langueNom : classeAutresLangues) {
				String classeNomCanoniqueGenApiServiceImplLangue = (String)classeDoc.get("classeNomCanoniqueGenApiServiceImpl_" + langueNom + "_stored_string");
				l(" * ", str_classeNomCanonique(classeLangueNom), ".", langueNom, ": ", classeNomCanoniqueGenApiServiceImplLangue);
			}
			l(" **/");
			s("public class ", classeNomSimpleGenApiServiceImpl);
			s(" implements ", classeNomSimpleGenApiService);
			l(" {");
			l();
			tl(1, "protected static final Logger LOGGER = LoggerFactory.getLogger(", classeNomSimpleGenApiServiceImpl, ".class);");
			l();
			tl(1, "protected static final String SERVICE_ADDRESS = \"", classeNomSimpleApiServiceImpl, "\";");
			l();
			tl(1, "protected ", classePartsSiteContexte.nomSimple(classeLangueNom), " ", str_siteContexte(classeLangueNom), ";");
			l();
			tl(1, "public ", classeNomSimpleGenApiServiceImpl, "(", classePartsSiteContexte.nomSimple(classeLangueNom), " ", str_siteContexte(classeLangueNom), ") {");
			tl(2, "this.", str_siteContexte(classeLangueNom), " = ", str_siteContexte(classeLangueNom), ";");
//			tl(2, classeNomSimpleGenApiService, " service = ", classeNomSimpleGenApiService, ".", str_creer(langueNom), "Proxy(", str_siteContexte(langueNom), ".getVertx(), SERVICE_ADDRESS);");
			tl(1, "}");

			for(String classeApiMethode : classeApiMethodes) {
				String classePageNomCanoniqueMethode = (String)classeDoc.get("classePageNomCanonique" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classePageNomSimpleMethode = (String)classeDoc.get("classePageNomSimple" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
				String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia" + classeApiMethode + "_" + classeLangueNom + "_stored_string");
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
						l("OperationRequest ", str_operationRequete(classeLangueNom), ", Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, classeApiOperationIdMethode, "(", str_operationRequete(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(1, "}");
					}
					l();
					tl(1, "@Override");
					t(1, "public void ", classeApiOperationIdMethode, "(");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						s("JsonObject body, ");
					l("OperationRequest ", str_operationRequete(classeLangueNom), ", Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
	
					if(classeApiMethode.contains("POST")) {
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_generer(classeLangueNom), "", classePartsRequeteSite.nomSimple(classeLangueNom), "", str_Pour(classeLangueNom), "", classeNomSimple, "(", str_siteContexte(classeLangueNom), ", ", str_operationRequete(classeLangueNom), ", body);");
						tl(3, "sql", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "", str_creer(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(7, str_requeteApi(classeLangueNom), ".setRows(1);");
						tl(7, str_requeteApi(classeLangueNom), ".setNumFound(1L);");
						tl(7, str_requeteApi(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsRequeteApi.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
						tl(7, str_requeteSite(classeLangueNom), ".set", str_RequeteApi(classeLangueNom), "_(", str_requeteApi(classeLangueNom), ");");
						tl(7, classeNomSimple, " ", StringUtils.uncapitalize(classeNomSimple), " = b.result();");
						tl(7, "sql", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "", str_definir(classeLangueNom), "", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "", str_attribuer(classeLangueNom), "", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", e -> {");
						tl(12, "if(e.succeeded()) {");
						tl(13, "", str_indexer(classeLangueNom), "", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", f -> {");
						tl(14, "if(f.succeeded()) {");
						tl(15, "", str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", g -> {");
						tl(16, "if(f.succeeded()) {");
						tl(17, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(17, "", str_connexionSql(classeLangueNom), ".commit(h -> {");
						tl(18, "if(a.succeeded()) {");
						tl(19, "", str_connexionSql(classeLangueNom), ".close(i -> {");
						tl(20, "if(a.succeeded()) {");
						tl(21, str_requeteApi(classeLangueNom), classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ");");
						tl(21, StringUtils.uncapitalize(classeNomSimple), ".", str_requeteApi(classeLangueNom), classeNomSimple, "();");
						tl(21, str_requeteSite(classeLangueNom), ".getVertx().eventBus().publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", str_requeteApi(classeLangueNom), ").toString());");
						tl(21, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(g.result()));");
						tl(20, "} else {");
						tl(21, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", i);");
						tl(20, "}");
						tl(19, "});");
						tl(18, "} else {");
						tl(19, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", h);");
						tl(18, "}");
						tl(17, "});");
						tl(16, "} else {");
						tl(17, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", g);");
						tl(16, "}");
						tl(15, "});");
						tl(14, "} else {");
						tl(15, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", f);");
						tl(14, "}");
						tl(13, "});");
						tl(12, "} else {");
						tl(13, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", e);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} else {");
						tl(11, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_erreur(classeLangueNom), "", classeNomSimple, "(null, ", str_gestionnaireEvenements(classeLangueNom), ", Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classeApiMethode.contains("PATCH")) {
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_generer(classeLangueNom), "", classePartsRequeteSite.nomSimple(classeLangueNom), "", str_Pour(classeLangueNom), "", classeNomSimple, "(", str_siteContexte(classeLangueNom), ", ", str_operationRequete(classeLangueNom), ", body);");
						tl(3, "sql", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "", str_utilisateur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(7, str_connexionSql(classeLangueNom), ".close(c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "", str_recherche(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", false, true, ", "null", ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple, " = d.result();");
						if(classeVarModifie == null) {
							tl(11, "String dt = DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of(\"UTC\")).minusNanos(1000));");
						}
						else {
							tl(11, "SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(", str_liste(classeLangueNom), "", classeNomSimple, ".getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get(\"facets\")).orElse(null);");
							tl(11, "Date date = null;");
							tl(11, "if(facets != null)");
							tl(12, "date = (Date)facets.get(\"max_", classeVarModifie, "\");");
							tl(11, "String dt;");
							tl(11, "if(date == null)");
							tl(12, "dt = DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of(\"UTC\")).minusNanos(1000));");
							tl(11, "else");
							tl(12, "dt = DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(\"UTC\")));");
							tl(11, str_liste(classeLangueNom), classeNomSimple, ".addFilterQuery(String.format(\"", classeVarModifie, "_indexed_date:[* TO %s]\", dt));");
						}
						l();
						tl(11, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(11, str_requeteApi(classeLangueNom), ".setRows(", str_liste(classeLangueNom), classeNomSimple, ".getRows());");
						tl(11, str_requeteApi(classeLangueNom), ".setNumFound(Optional.ofNullable(", str_liste(classeLangueNom), classeNomSimple, ".getQueryResponse()).map(QueryResponse::getResults).map(SolrDocumentList::getNumFound).orElse(new Long(", str_liste(classeLangueNom), classeNomSimple, ".size())));");
						tl(11, str_requeteApi(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsRequeteApi.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
						tl(11, str_requeteSite(classeLangueNom), ".set", str_RequeteApi(classeLangueNom), "_(", str_requeteApi(classeLangueNom), ");");
						tl(11, "if(", str_liste(classeLangueNom), classeNomSimple, ".size() == 1) {");
						tl(12, classeNomSimple, " o = ", str_liste(classeLangueNom), classeNomSimple, ".get(0);");
						tl(12, str_requeteApi(classeLangueNom), ".setPk(o.getPk());");
						tl(12, str_requeteApi(classeLangueNom), ".setOriginal(o);");
						tl(12, str_requeteApi(classeLangueNom), classeNomSimple, "(o);");
						tl(11, "}");
						tl(11, "WorkerExecutor ", str_executeurTravailleur(classeLangueNom), " = ", str_siteContexte(classeLangueNom), ".get", str_ExecuteurTravailleur(classeLangueNom), "();");
						tl(11, str_executeurTravailleur(classeLangueNom), ".executeBlocking(");
						tl(12, "blockingCodeHandler -> {");
						tl(13, "sql", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", e -> {");
						tl(14, "if(e.succeeded()) {");
						tl(15, "try {");
						tl(16, str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_liste(classeLangueNom), "", classeNomSimple, ", dt, f -> {");
						tl(17, "if(f.succeeded()) {");
						tl(18, "SQLConnection ", str_connexionSql(classeLangueNom), "2 = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(18, "if(", str_connexionSql(classeLangueNom), "2 == null) {");
						tl(19, "blockingCodeHandler.handle(Future.succeededFuture(f.result()));");
						tl(18, "} else {");
						tl(19, str_connexionSql(classeLangueNom), "2.commit(g -> {");
						tl(20, "if(f.succeeded()) {");
						tl(21, str_connexionSql(classeLangueNom), "2.close(h -> {");
						tl(22, "if(g.succeeded()) {");
						tl(23, "blockingCodeHandler.handle(Future.succeededFuture(h.result()));");
						tl(22, "} else {");
						tl(23, "blockingCodeHandler.handle(Future.failedFuture(h.cause()));");
						tl(22, "}");
						tl(21, "});");
						tl(20, "} else {");
						tl(21, "blockingCodeHandler.handle(Future.failedFuture(g.cause()));");
						tl(20, "}");
						tl(19, "});");
						tl(18, "}");
						tl(17, "} else {");
						tl(18, "blockingCodeHandler.handle(Future.failedFuture(f.cause()));");
						tl(17, "}");
						tl(16, "});");
						tl(15, "} catch(Exception ex) {");
						tl(16, "blockingCodeHandler.handle(Future.failedFuture(ex));");
						tl(15, "}");
						tl(14, "} else {");
						tl(15, "blockingCodeHandler.handle(Future.failedFuture(e.cause()));");
						tl(14, "}");
						tl(13, "});");
						tl(12, "}, resultHandler -> {");
//						tl(13, "LOGGER.info(String.format(\"{}\", JsonObject.mapFrom(", str_requeteApi(langueNom), ")));");
						tl(12, "}");
						tl(11, ");");
						tl(11, str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(10, "} else {");
						tl(11, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_erreur(classeLangueNom), "", classeNomSimple, "(null, ", str_gestionnaireEvenements(classeLangueNom), ", Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classeApiMethode.contains(str_Recherche(classeLangueNom))) {
//						if(classePageNomSimpleMethode == null) {
//							tl(2, "try {");
//							tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), " = ", str_generer(langueNom), "", classePartsRequeteSite.nomSimple(langueNom), "", str_Pour(langueNom), "", classeNomSimple, "(", str_siteContexte(langueNom), ", ", str_operationRequete(langueNom), ");");
//							tl(3, "", str_recherche(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", false, true, ", "null", ", a -> {");
//							tl(4, "if(a.succeeded()) {");
//							tl(5, classePartsListeRecherche.nomSimple(langueNom), "<", classeNomSimple, "> ", str_liste(langueNom), "", classeNomSimple, " = a.result();");
//							tl(5, "", str_reponse(langueNom), "200", classeApiMethode, classeNomSimple, "(", str_liste(langueNom), "", classeNomSimple, ", b -> {");
//							tl(6, "if(b.succeeded()) {");
//							tl(7, "", str_gestionnaireEvenements(langueNom), ".handle(Future.succeededFuture(b.result()));");
//							tl(6, "} else {");
//							tl(7, "", str_erreur(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", ", str_gestionnaireEvenements(langueNom), ", b);");
//							tl(6, "}");
//							tl(5, "});");
//							tl(4, "} else {");
//							tl(5, "", str_erreur(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", ", str_gestionnaireEvenements(langueNom), ", a);");
//							tl(4, "}");
//							tl(3, "});");
//							tl(2, "} catch(Exception e) {");
//							tl(3, "", str_erreur(langueNom), "", classeNomSimple, "(null, ", str_gestionnaireEvenements(langueNom), ", Future.failedFuture(e));");
//							tl(2, "}");
//						}
//						else {
							tl(2, "try {");
							tl(3, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_generer(classeLangueNom), "", classePartsRequeteSite.nomSimple(classeLangueNom), "", str_Pour(classeLangueNom), "", classeNomSimple, "(", str_siteContexte(classeLangueNom), ", ", str_operationRequete(classeLangueNom), ");");
							tl(3, "sql", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", a -> {");
							tl(4, "if(a.succeeded()) {");
							tl(5, "", str_utilisateur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", b -> {");
							tl(6, "if(b.succeeded()) {");
							tl(7, "", str_recherche(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", false, true, ", q(classeApiUriMethode), ", c -> {");
							tl(8, "if(c.succeeded()) {");
							tl(9, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), "", classeNomSimple, " = c.result();");
							tl(9, "", str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_liste(classeLangueNom), "", classeNomSimple, ", d -> {");
							tl(10, "if(d.succeeded()) {");
							tl(11, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
							tl(11, "if(", str_connexionSql(classeLangueNom), " == null) {");
							tl(12, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
							tl(11, "} else {");
							tl(12, "", str_connexionSql(classeLangueNom), ".commit(e -> {");
							tl(13, "if(e.succeeded()) {");
							tl(14, "", str_connexionSql(classeLangueNom), ".close(f -> {");
							tl(15, "if(f.succeeded()) {");
							tl(16, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
							tl(15, "} else {");
							tl(16, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", f);");
							tl(15, "}");
							tl(14, "});");
							tl(13, "} else {");
							tl(14, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
							tl(13, "}");
							tl(12, "});");
							tl(11, "}");
							tl(10, "} else {");
							tl(11, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", d);");
							tl(10, "}");
							tl(9, "});");
							tl(8, "} else {");
							tl(9, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
							tl(8, "}");
							tl(7, "});");
							tl(6, "} else {");
							tl(7, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", b);");
							tl(6, "}");
							tl(5, "});");
							tl(4, "} else {");
							tl(5, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
							tl(4, "}");
							tl(3, "});");
							tl(2, "} catch(Exception e) {");
							tl(3, "", str_erreur(classeLangueNom), "", classeNomSimple, "(null, ", str_gestionnaireEvenements(classeLangueNom), ", Future.failedFuture(e));");
							tl(2, "}");
//						}
					}
					else if(classeApiMethode.contains("GET")) {
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_generer(classeLangueNom), "", classePartsRequeteSite.nomSimple(classeLangueNom), "", str_Pour(classeLangueNom), "", classeNomSimple, "(", str_siteContexte(classeLangueNom), ", ", str_operationRequete(classeLangueNom), ");");
						tl(3, "sql", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "", str_utilisateur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, "", str_recherche(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", false, true, ", "null", ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), "", classeNomSimple, " = c.result();");
						tl(9, "", str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_liste(classeLangueNom), "", classeNomSimple, ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(11, "if(", str_connexionSql(classeLangueNom), " == null) {");
						tl(12, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
						tl(11, "} else {");
						tl(12, "", str_connexionSql(classeLangueNom), ".commit(e -> {");
						tl(13, "if(e.succeeded()) {");
						tl(14, "", str_connexionSql(classeLangueNom), ".close(f -> {");
						tl(15, "if(f.succeeded()) {");
						tl(16, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
						tl(15, "} else {");
						tl(16, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", f);");
						tl(15, "}");
						tl(14, "});");
						tl(13, "} else {");
						tl(14, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
						tl(13, "}");
						tl(12, "});");
						tl(11, "}");
						tl(10, "} else {");
						tl(11, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_erreur(classeLangueNom), "", classeNomSimple, "(null, ", str_gestionnaireEvenements(classeLangueNom), ", Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classeApiMethode.contains("PUT")) {
//						tl(2, "try {");
//						tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), " = ", str_generer(langueNom), "", classePartsRequeteSite.nomSimple(langueNom), "", str_Pour(langueNom), "", classeNomSimple, "(", str_siteContexte(langueNom), ", ", str_operationRequete(langueNom), ", body);");
//						tl(3, "sql", classeNomSimple, "(", str_requeteSite(langueNom), ", a -> {");
//						tl(4, "if(a.succeeded()) {");
//						tl(5, "remplacer", classeApiMethode, classeNomSimple, "(", str_requeteSite(langueNom), ", b -> {");
//						tl(6, "if(b.succeeded()) {");
//						tl(7, classeNomSimple, " ", StringUtils.uncapitalize(classeNomSimple), " = b.result();");
//						tl(7, "sql", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", c -> {");
//						tl(8, "if(c.succeeded()) {");
//						tl(9, "", str_definir(langueNom), "", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", d -> {");
//						tl(10, "if(d.succeeded()) {");
//						tl(11, "", str_attribuer(langueNom), "", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", e -> {");
//						tl(12, "if(e.succeeded()) {");
//						tl(13, "", str_indexer(langueNom), "", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", f -> {");
//						tl(14, "if(f.succeeded()) {");
//						tl(15, "", str_reponse(langueNom), "200", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", g -> {");
//						tl(16, "if(g.succeeded()) {");
//						tl(17, "SQLConnection ", str_connexionSql(langueNom), " = ", str_requeteSite(langueNom), ".get", str_ConnexionSql(langueNom), "();");
//						tl(17, "", str_connexionSql(langueNom), ".commit(h -> {");
//						tl(18, "if(a.succeeded()) {");
//						tl(19, "", str_connexionSql(langueNom), ".close(i -> {");
//						tl(20, "if(a.succeeded()) {");
//						tl(21, "", str_gestionnaireEvenements(langueNom), ".handle(Future.succeededFuture(g.result()));");
//						tl(20, "} else {");
//						tl(21, "", str_erreur(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", ", str_gestionnaireEvenements(langueNom), ", i);");
//						tl(20, "}");
//						tl(19, "});");
//						tl(18, "} else {");
//						tl(19, "", str_erreur(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", ", str_gestionnaireEvenements(langueNom), ", h);");
//						tl(18, "}");
//						tl(17, "});");
//						tl(16, "} else {");
//						tl(17, "", str_erreur(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", ", str_gestionnaireEvenements(langueNom), ", g);");
//						tl(16, "}");
//						tl(15, "});");
//						tl(14, "} else {");
//						tl(15, "", str_erreur(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", ", str_gestionnaireEvenements(langueNom), ", f);");
//						tl(14, "}");
//						tl(13, "});");
//						tl(12, "} else {");
//						tl(13, "", str_erreur(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", ", str_gestionnaireEvenements(langueNom), ", e);");
//						tl(12, "}");
//						tl(11, "});");
//						tl(10, "} else {");
//						tl(11, "", str_erreur(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", ", str_gestionnaireEvenements(langueNom), ", d);");
//						tl(10, "}");
//						tl(9, "});");
//						tl(8, "} else {");
//						tl(9, "", str_erreur(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", ", str_gestionnaireEvenements(langueNom), ", c);");
//						tl(8, "}");
//						tl(7, "});");
//						tl(6, "} else {");
//						tl(7, "", str_erreur(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", ", str_gestionnaireEvenements(langueNom), ", b);");
//						tl(6, "}");
//						tl(5, "});");
//						tl(4, "} else {");
//						tl(5, "", str_erreur(langueNom), "", classeNomSimple, "(", str_requeteSite(langueNom), ", ", str_gestionnaireEvenements(langueNom), ", a);");
//						tl(4, "}");
//						tl(3, "});");
//						tl(2, "} catch(Exception e) {");
//						tl(3, "", str_erreur(langueNom), "", classeNomSimple, "(null, ", str_gestionnaireEvenements(langueNom), ", Future.failedFuture(e));");
//						tl(2, "}");

						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_generer(classeLangueNom), "", classePartsRequeteSite.nomSimple(classeLangueNom), "", str_Pour(classeLangueNom), "", classeNomSimple, "(", str_siteContexte(classeLangueNom), ", ", str_operationRequete(classeLangueNom), ", body);");
						tl(3, "sql", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "", str_utilisateur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(7, str_connexionSql(classeLangueNom), ".close(c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "", str_recherche(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", false, true, ", "null", ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple, " = d.result();");
						tl(11, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = new ", classePartsRequeteApi.nomSimple(classeLangueNom), "();");
						tl(11, str_requeteApi(classeLangueNom), ".setRows(", str_liste(classeLangueNom), classeNomSimple, ".getRows());");
						tl(11, str_requeteApi(classeLangueNom), ".setNumFound(Optional.ofNullable(", str_liste(classeLangueNom), classeNomSimple, ".getQueryResponse()).map(QueryResponse::getResults).map(SolrDocumentList::getNumFound).orElse(new Long(", str_liste(classeLangueNom), classeNomSimple, ".size())));");
						tl(11, str_requeteApi(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsRequeteApi.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
						tl(11, str_requeteSite(classeLangueNom), ".set", str_RequeteApi(classeLangueNom), "_(", str_requeteApi(classeLangueNom), ");");
						tl(11, "WorkerExecutor ", str_executeurTravailleur(classeLangueNom), " = ", str_siteContexte(classeLangueNom), ".get", str_ExecuteurTravailleur(classeLangueNom), "();");
						tl(11, str_executeurTravailleur(classeLangueNom), ".executeBlocking(");
						tl(12, "blockingCodeHandler -> {");
						tl(13, "sql", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", e -> {");
						tl(14, "if(e.succeeded()) {");
						tl(15, "try {");
						tl(16, str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_liste(classeLangueNom), "", classeNomSimple, classeApiMethode.contains("PATCH") ? ", dt" : "", ", f -> {");
						tl(17, "if(f.succeeded()) {");
						tl(18, "SQLConnection ", str_connexionSql(classeLangueNom), "2 = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(18, "if(", str_connexionSql(classeLangueNom), "2 == null) {");
						tl(19, "blockingCodeHandler.handle(Future.succeededFuture(f.result()));");
						tl(18, "} else {");
						tl(19, str_connexionSql(classeLangueNom), "2.commit(g -> {");
						tl(20, "if(f.succeeded()) {");
						tl(21, str_connexionSql(classeLangueNom), "2.close(h -> {");
						tl(22, "if(g.succeeded()) {");
						tl(23, "blockingCodeHandler.handle(Future.succeededFuture(h.result()));");
						tl(22, "} else {");
						tl(23, "blockingCodeHandler.handle(Future.failedFuture(h.cause()));");
						tl(22, "}");
						tl(21, "});");
						tl(20, "} else {");
						tl(21, "blockingCodeHandler.handle(Future.failedFuture(g.cause()));");
						tl(20, "}");
						tl(19, "});");
						tl(18, "}");
						tl(17, "} else {");
						tl(18, "blockingCodeHandler.handle(Future.failedFuture(f.cause()));");
						tl(17, "}");
						tl(16, "});");
						tl(15, "} catch(Exception ex) {");
						tl(16, "blockingCodeHandler.handle(Future.failedFuture(ex));");
						tl(15, "}");
						tl(14, "} else {");
						tl(15, "blockingCodeHandler.handle(Future.failedFuture(e.cause()));");
						tl(14, "}");
						tl(13, "});");
						tl(12, "}, resultHandler -> {");
//						tl(13, "LOGGER.info(String.format(\"{}\", JsonObject.mapFrom(", str_requeteApi(langueNom), ")));");
						tl(12, "}");
						tl(11, ");");
						tl(11, str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(10, "} else {");
						tl(11, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_erreur(classeLangueNom), "", classeNomSimple, "(null, ", str_gestionnaireEvenements(classeLangueNom), ", Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classeApiMethode.contains("DELETE")) {
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_generer(classeLangueNom), "", classePartsRequeteSite.nomSimple(classeLangueNom), "", str_Pour(classeLangueNom), "", classeNomSimple, "(", str_siteContexte(classeLangueNom), ", ", str_operationRequete(classeLangueNom), ");");
						tl(3, "sql", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "", str_recherche(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", false, true, ", "null", ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), "", classeNomSimple, " = b.result();");
						tl(7, "", str_supprimer(classeLangueNom), "", classeApiMethode, classeNomSimple, "(", str_requeteSite(classeLangueNom), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "", str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_requeteSite(classeLangueNom), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(11, "if(", str_connexionSql(classeLangueNom), " == null) {");
						tl(12, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
						tl(11, "} else {");
						tl(12, "", str_connexionSql(classeLangueNom), ".commit(e -> {");
						tl(13, "if(e.succeeded()) {");
						tl(14, "", str_connexionSql(classeLangueNom), ".close(f -> {");
						tl(15, "if(f.succeeded()) {");
						tl(16, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
						tl(15, "} else {");
						tl(16, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", f);");
						tl(15, "}");
						tl(14, "});");
						tl(13, "} else {");
						tl(14, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
						tl(13, "}");
						tl(12, "});");
						tl(11, "}");
						tl(10, "} else {");
						tl(11, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "", str_erreur(classeLangueNom), "", classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_erreur(classeLangueNom), "", classeNomSimple, "(null, ", str_gestionnaireEvenements(classeLangueNom), ", Future.failedFuture(e));");
						tl(2, "}");
					}
					tl(1, "}");
	
					if(classeApiMethode.contains(str_Recherche(classeLangueNom))) {
	//					l();
	//					tl(1, "public Future<OperationResponse> ", str_liste(langueNom), str_Recherche(langueNom), classeNomSimple, classePartsListeRecherche.nomSimple(langueNom), "(<", classeNomSimple, "> ", str_liste(langueNom), "", classeNomSimple, ") {");
	//					tl(2, "List<Future> futures = new ArrayList<>();");
	//					tl(2, "", str_liste(langueNom), "", classeNomSimple, ".getList().forEach(o -> {");
	//					tl(3, "futures.add(");
	//					tl(4, "sqlPATCH", classeNomSimple, "(o).compose(");
	//					tl(5, "b -> ", str_indexer(langueNom), "", classeNomSimple, "(o)");
	//					tl(4, ")");
	//					tl(3, ");");
	//					tl(2, "});");
	//					tl(2, "Future<OperationResponse> future = CompositeFuture.all(futures).compose( a -> ");
	//					tl(3, "", str_reponse(langueNom), "200Recherche", classeNomSimple, "(", str_liste(langueNom), "", classeNomSimple, ")");
	//					tl(2, ");");
	//					tl(2, "return future;");
	//					tl(1, "}");
					}
					if(classeApiMethode.contains("PATCH")) {
						l();
						tl(1, "public void ", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple, classeApiMethode.contains("PATCH") ? ", String dt" : "", ", Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_liste(classeLangueNom), classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_();");
						tl(2, str_liste(classeLangueNom), classeNomSimple, ".getList().forEach(o -> {");
						tl(3, "futures.add(");
						tl(4, "future", classeApiMethode, classeNomSimple, "(", classeApiMethode.contains("PATCH") ? "o" : (str_requeteSite(classeLangueNom) + ", JsonObject.mapFrom(o)"), ", a -> {");
						tl(5, "if(a.succeeded()) {");
						tl(5, "} else {");
						tl(6, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(5, "}");
						tl(4, "})");
						tl(3, ");");
						tl(2, "});");
						tl(2, "CompositeFuture.all(futures).setHandler( a -> {");
						tl(3, "if(a.succeeded()) {");
						tl(4, str_requeteApi(classeLangueNom), ".setNumPATCH(", str_requeteApi(classeLangueNom), ".getNumPATCH() + ", str_liste(classeLangueNom), classeNomSimple, ".size());");
						tl(4, "if(", str_liste(classeLangueNom), classeNomSimple, ".next(", classeApiMethode.contains("PATCH") ? "dt" : "" , ")) {");
						tl(5, str_requeteSite(classeLangueNom), ".getVertx().eventBus().publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", str_requeteApi(classeLangueNom), ").toString());");
						tl(5, str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_liste(classeLangueNom), "", classeNomSimple, classeApiMethode.contains("PATCH") ? ", dt" : "", ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(4, "} else {");
						tl(5, str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(4, "}");
						tl(3, "} else {");
						tl(4, str_erreur(classeLangueNom), classeNomSimple, "(", str_liste(classeLangueNom), "", classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_(), ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(3, "}");
						tl(2, "});");
						tl(1, "}");
					}
					if(classeApiMethode.contains("PUT")) {
						l();
						tl(1, "public void ", str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), ", ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), classeNomSimple, classeApiMethode.contains("PATCH") ? ", String dt" : "", ", Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_liste(classeLangueNom), classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_();");
						tl(2, "JsonArray jsonArray = Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "()).map(o -> o.getJsonArray(\"list\")).orElse(new JsonArray());");
						tl(2, "if(jsonArray.size() == 0) {");
						tl(3, str_liste(classeLangueNom), classeNomSimple, ".getList().forEach(o -> {");
						tl(4, "futures.add(");
						tl(5, "future", classeApiMethode, classeNomSimple, "(", classeApiMethode.contains("PATCH") ? "o" : (str_requeteSite(classeLangueNom) + ", JsonObject.mapFrom(o)"), ", a -> {");
						tl(6, "if(a.succeeded()) {");
						tl(6, "} else {");
						tl(7, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(6, "}");
						tl(5, "})");
						tl(4, ");");
						tl(3, "});");
						tl(3, "CompositeFuture.all(futures).setHandler( a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, str_requeteApi(classeLangueNom), ".setNumPATCH(", str_requeteApi(classeLangueNom), ".getNumPATCH() + ", str_liste(classeLangueNom), classeNomSimple, ".size());");
						tl(5, "if(", str_liste(classeLangueNom), classeNomSimple, ".next(", classeApiMethode.contains("PATCH") ? "dt" : "" , ")) {");
						tl(6, str_requeteSite(classeLangueNom), ".getVertx().eventBus().publish(\"websocket", classeNomSimple, "\", JsonObject.mapFrom(", str_requeteApi(classeLangueNom), ").toString());");
						tl(6, str_liste(classeLangueNom), classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_liste(classeLangueNom), "", classeNomSimple, classeApiMethode.contains("PATCH") ? ", dt" : "", ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(5, "} else {");
						tl(6, str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(5, "}");
						tl(4, "} else {");
						tl(5, str_erreur(classeLangueNom), classeNomSimple, "(", str_liste(classeLangueNom), "", classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_(), ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} else {");
						tl(3, "jsonArray.forEach(o -> {");
						tl(4, "JsonObject jsonObject = (JsonObject)o;");
						tl(4, "futures.add(");
						tl(5, "future", classeApiMethode, classeNomSimple, "(", str_requeteSite(classeLangueNom), ", jsonObject, a -> {");
						tl(6, "if(a.succeeded()) {");
						tl(6, "} else {");
						tl(7, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(6, "}");
						tl(5, "})");
						tl(4, ");");
						tl(3, "});");
						tl(3, "CompositeFuture.all(futures).setHandler( a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, str_requeteApi(classeLangueNom), ".setNumPATCH(", str_requeteApi(classeLangueNom), ".getNumPATCH() + jsonArray.size());");
						tl(5, str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(", str_requeteApi(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ");");
						tl(4, "} else {");
						tl(5, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteApi(classeLangueNom), ".get", str_RequeteSite(classeLangueNom), "_(), ", str_gestionnaireEvenements(classeLangueNom), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("PATCH")) {
						l();
						tl(1, "public Future<", classeNomSimple, "> future", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o,  Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "Promise<", classeNomSimple, "> promise = Promise.promise();");
						tl(2, "try {");
						tl(3, "sql", classeApiMethode, classeNomSimple, "(o, a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, classeNomSimple, " ", StringUtils.uncapitalize(classeNomSimple), " = a.result();");
						tl(5, str_definir(classeLangueNom), classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, str_attribuer(classeLangueNom), classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, str_indexer(classeLangueNom), classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, str_requeteApi(classeLangueNom), classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ");");
						tl(11, StringUtils.uncapitalize(classeNomSimple), ".", str_requeteApi(classeLangueNom), classeNomSimple, "();");
						tl(11, "promise.complete(o);");
						tl(11, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(d.result()));");
						tl(10, "} else {");
						tl(11, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(d.cause()));");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(c.cause()));");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(b.cause()));");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(a.cause()));");
						tl(4, "}");
						tl(3, "});");
						tl(3, "return promise.future();");
						tl(2, "} catch(Exception e) {");
						tl(3, "return Future.failedFuture(e);");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("PUT")) {
						l();
						tl(1, "public Future<", classeNomSimple, "> future", classeApiMethode, classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", JsonObject jsonObject,  Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "jsonObject.put(\"", str_sauvegardes(classeLangueNom), "\", Optional.ofNullable(jsonObject.getJsonArray(\"", str_sauvegardes(classeLangueNom), "\")).orElse(new JsonArray()));");
						tl(2, "JsonObject jsonPatch = Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "()).map(o -> o.getJsonObject(\"patch\")).orElse(new JsonObject());");
						tl(2, "jsonPatch.stream().forEach(o -> {");
						tl(3, "jsonObject.put(o.getKey(), o.getValue());");
						tl(3, "jsonObject.getJsonArray(\"", str_sauvegardes(classeLangueNom), "\").add(o.getKey());");
						tl(2, "});");
						tl(2, "Promise<", classeNomSimple, "> promise = Promise.promise();");
						tl(2, "try {");
						tl(3, str_creer(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, classeNomSimple, " ", StringUtils.uncapitalize(classeNomSimple), " = a.result();");
						tl(5, "sql", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", jsonObject, b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, str_definir(classeLangueNom), classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, str_attribuer(classeLangueNom), classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, str_indexer(classeLangueNom), classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", e -> {");
						tl(12, "if(e.succeeded()) {");
						tl(13, str_requeteApi(classeLangueNom), classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ");");
						tl(13, StringUtils.uncapitalize(classeNomSimple), ".", str_requeteApi(classeLangueNom), classeNomSimple, "();");
						tl(13, "promise.complete(", StringUtils.uncapitalize(classeNomSimple), ");");
						tl(13, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(e.result()));");
						tl(12, "} else {");
						tl(13, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e.cause()));");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} else {");
						tl(11, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(d.cause()));");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(c.cause()));");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(b.cause()));");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(a.cause()));");
						tl(4, "}");
						tl(3, "});");
						tl(3, "return promise.future();");
						tl(2, "} catch(Exception e) {");
						tl(3, "return Future.failedFuture(e);");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("PATCH")) {
						l();
						tl(1, "public void sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<", classeNomSimple, ">> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "try {");
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
						tl(3, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
						tl(3, "JsonObject ", str_requete(classeLangueNom), "Json = ", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "();");
						tl(3, "StringBuilder patchSql = new StringBuilder();");
						tl(3, "List<Object> patchSqlParams = new ArrayList<Object>();");
						tl(3, "Set<String> ", str_methodeNoms(classeLangueNom), " = ", str_requete(classeLangueNom), "Json.fieldNames();");
						tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
						l();
						tl(3, "patchSql.append(", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_", str_modifier(classeLangueNom), ");");
						tl(3, "patchSqlParams.addAll(Arrays.asList(pk, ", q(classeNomCanonique), "));");
						tl(3, "for(String ", str_methodeNom(classeLangueNom), " : ", str_methodeNoms(classeLangueNom), ") {");
						tl(4, "switch(", str_methodeNom(classeLangueNom), ") {");
						s(wApiGenererPatch.toString());
						tl(4, "}");
						tl(3, "}");
						tl(3, "", str_connexionSql(classeLangueNom), ".queryWithParams(");
						tl(5, "patchSql.toString()");
						tl(5, ", new JsonArray(patchSqlParams)");
						tl(5, ", patchAsync");
						tl(3, "-> {");
						tl(4, "if(patchAsync.succeeded()) {");
						tl(5, classeNomSimple, " o3 = new ", classeNomSimple, "();");
						tl(5, "o3.set", str_RequeteSite(classeLangueNom), "_(o.get", str_RequeteSite(classeLangueNom), "_());");
						tl(5, "o3.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(o3));");
						tl(4, "} else {");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(new Exception(patchAsync.cause())));");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("PUT")) {
						l();
						tl(1, "public void remplacer", classeApiMethode, classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Handler<AsyncResult<", classeNomSimple, ">> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "try {");
						tl(3, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(3, "String ", str_utilisateur(classeLangueNom), "Id = ", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "Id();");
						tl(3, "Long pk = ", str_requeteSite(classeLangueNom), ".get", str_Requete(classeLangueNom), "Pk();");
						l();
						tl(3, "", str_connexionSql(classeLangueNom), ".queryWithParams(");
						tl(5, "", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_", str_vider(classeLangueNom));
						tl(5, ", new JsonArray(Arrays.asList(pk, ", classeNomSimple, ".class.getCanonicalName(), pk, pk, pk))");
						tl(5, ", remplacerAsync");
						tl(3, "-> {");
						tl(4, classeNomSimple, " o = new ", classeNomSimple, "();");
						tl(4, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
						tl(4, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(o));");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("POST")) {
						l();
						tl(1, "public void sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
						tl(3, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
						tl(3, "JsonObject jsonObject = ", str_requeteSite(classeLangueNom), ".get", str_ObjetJson(classeLangueNom), "();");
						tl(3, "StringBuilder postSql = new StringBuilder();");
						tl(3, "List<Object> postSqlParams = new ArrayList<Object>();");
						l();
						tl(3, "if(jsonObject != null) {");
						tl(4, "Set<String> ", str_entite(classeLangueNom), "Vars = jsonObject.fieldNames();");
						tl(4, "for(String ", str_entite(classeLangueNom), "Var : ", str_entite(classeLangueNom), "Vars) {");
						tl(5, "switch(", str_entite(classeLangueNom), "Var) {");
						s(wApiGenererPost.toString());
						tl(5, "}");
						tl(4, "}");
						tl(3, "}");
						tl(3, "", str_connexionSql(classeLangueNom), ".queryWithParams(");
						tl(5, "postSql.toString()");
						tl(5, ", new JsonArray(postSqlParams)");
						tl(5, ", postAsync");
						tl(3, "-> {");

						tl(4, "if(postAsync.succeeded()) {");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
						tl(4, "} else {");
						tl(5, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(new Exception(postAsync.cause())));");
						tl(4, "}");

						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("PUT")) {
						l();
						tl(1, "public void sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "try {");
						tl(3, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
						tl(3, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
						tl(3, "StringBuilder postSql = new StringBuilder();");
						tl(3, "List<Object> postSqlParams = new ArrayList<Object>();");
						l();
						tl(3, "if(jsonObject != null) {");
						tl(4, "JsonArray ", str_entite(classeLangueNom), "Vars = jsonObject.getJsonArray(\"", classeVarSauvegardes, "\");");
						tl(4, "for(Integer i = 0; i < ", str_entite(classeLangueNom), "Vars.size(); i++) {");
						tl(5, "String ", str_entite(classeLangueNom), "Var = ", str_entite(classeLangueNom), "Vars.getString(i);");
						tl(5, "switch(", str_entite(classeLangueNom), "Var) {");
						s(wApiGenererPost.toString());
						tl(5, "}");
						tl(4, "}");
						tl(3, "}");
						tl(3, "", str_connexionSql(classeLangueNom), ".queryWithParams(");
						tl(5, "postSql.toString()");
						tl(5, ", new JsonArray(postSqlParams)");
						tl(5, ", postAsync");
						tl(3, "-> {");

						tl(4, "if(postAsync.succeeded()) {");
						tl(5, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
						tl(4, "} else {");
						tl(5, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(new Exception(postAsync.cause())));");
						tl(4, "}");

						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classeApiMethode.contains("GET")) {
					}
					if(classeApiMethode.contains("DELETE")) {
						l();
						tl(1, "public void ", str_supprimer(classeLangueNom), "", classeApiMethode, classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
						tl(2, "try {");
						tl(3, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
						tl(3, "String ", str_utilisateur(classeLangueNom), "Id = ", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "Id();");
						tl(3, "Long pk = ", str_requeteSite(classeLangueNom), ".get", str_Requete(classeLangueNom), "Pk();");
						l();
						tl(3, "", str_connexionSql(classeLangueNom), ".queryWithParams(");
						tl(5, "", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_", str_supprimer(classeLangueNom), "");
						tl(5, ", new JsonArray(Arrays.asList(pk, ", classeNomSimple, ".class.getCanonicalName(), pk, pk, pk, pk))");
						tl(5, ", ", str_supprimer(classeLangueNom), "Async");
						tl(3, "-> {");
						tl(4, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					l();
					t(1, "public void ", str_reponse(classeLangueNom), "200", classeApiMethode, classeNomSimple, "(");
	
					if(classeApiMethode.contains("POST"))
						s(classeNomSimple, " o");
					else if(classeApiMethode.contains("PUT"))
						s(classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom));
					else if(classeApiMethode.contains("DELETE"))
						s(classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), "");
					else if(classeApiMethode.contains("PATCH"))
						s(classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), "");
					else
						s(classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_liste(classeLangueNom), "", classeNomSimple);
	
					l(", Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
	
					tl(2, "try {");
//					tl(3, "JsonObject json = new JsonObject();");
	
					if(classeApiMethode.contains("POST")) {
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
					}
					else if(classeApiMethode.contains("PATCH")) {
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_requeteApi(classeLangueNom), ".get", str_RequeteSite(classeLangueNom), "_();");
					}
					else if(classeApiMethode.contains(str_Recherche(classeLangueNom)) || classeApiMethode.contains("GET")) {
						tl(3, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_liste(classeLangueNom), "", classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_();");
					}
					else {
					}
	
//					t(3, "", classePartsToutEcrivain.nomSimple(langueNom), " w = ", classePartsToutEcrivain.nomSimple(langueNom), ".", str_creer(langueNom), "(");
//					if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT"))
//						s("o.get", str_RequeteSite(langueNom), "_()");
//					else if(classeApiMethode.contains("DELETE"))
//						s("", str_requeteSite(langueNom), "");
//					else
//						s("", str_liste(langueNom), "", classeNomSimple, ".get", str_RequeteSite(langueNom), "_()");
//					l(", json);");
//					tl(3, "", str_requeteSite(langueNom), ".setW(w);");
//
//					if(classeApiMethode.contains("PATCH"))
//						tl(3, "buffer.appendString(\"{}\");");
	
	
					if(classeApiMethode.contains("GET")) {
						tl(3, "SolrDocumentList ", str_documentsSolr(classeLangueNom), " = ", str_liste(classeLangueNom), "", classeNomSimple, ".getSolrDocumentList();");
						l();
					}
					if(classeApiMethode.contains(str_Recherche(classeLangueNom))) {
					}
	
					if(classeApiMethode.contains(str_Recherche(classeLangueNom)) || classeApiMethode.contains("GET")) {
					}
					else if(classeApiMethode.contains("DELETE")) {
					}
	
					if(classeApiMethode.contains(str_Recherche(classeLangueNom))) {
						if(classePageNomCanoniqueMethode != null) {
							tl(3, "Buffer buffer = Buffer.buffer();");
							t(3, classePartsToutEcrivain.nomSimple(classeLangueNom), " w = ", classePartsToutEcrivain.nomSimple(classeLangueNom), ".", str_creer(classeLangueNom), "(");
							s("", str_liste(classeLangueNom), "", classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_()");
							l(", buffer);");
							tl(3, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
//							tl(3, "page.setPageUrl(\"", siteUrlBase, classeApiUri, "\");");
							tl(3, "SolrDocument page", str_DocumentSolr(classeLangueNom), " = new SolrDocument();");
							tl(3, "CaseInsensitiveHeaders ", str_requeteEnTetes(classeLangueNom), " = new CaseInsensitiveHeaders();");
							tl(3, str_requeteSite(classeLangueNom), ".set", str_RequeteEnTetes(classeLangueNom), "(", str_requeteEnTetes(classeLangueNom), ");");
							l();
							tl(3, "page", str_DocumentSolr(classeLangueNom), ".setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
							tl(3, "page.setPage", str_DocumentSolr(classeLangueNom), "(page", str_DocumentSolr(classeLangueNom), ");");
							tl(3, "page.setW(w);");
							tl(3, "if(", str_liste(classeLangueNom), classeNomSimple, ".size() == 1)");
							tl(4, str_requeteSite(classeLangueNom), ".set", str_Requete(classeLangueNom), StringUtils.capitalize(classeVarClePrimaire), "(", str_liste(classeLangueNom), classeNomSimple, ".get(0).get", StringUtils.capitalize(classeVarClePrimaire), "()", ");");
							tl(3, str_requeteSite(classeLangueNom), ".setW(w);");
							if(!classePageSimple)
								tl(3, "page.set", str_Liste(classeLangueNom), "", classeNomSimple, "(", str_liste(classeLangueNom), "", classeNomSimple, ");");
							tl(3, "page.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ");");
							tl(3, "page.", str_initLoin(classeLangueNom), "", classePageNomSimpleMethode, "(", str_requeteSite(classeLangueNom), ");");
							tl(3, "page.html();");
						}
						else {
							tl(3, "QueryResponse ", str_reponse(classeLangueNom), "", str_Recherche(classeLangueNom), " = ", str_liste(classeLangueNom), "", classeNomSimple, ".getQueryResponse();");
							tl(3, "SolrDocumentList ", str_documentsSolr(classeLangueNom), " = ", str_liste(classeLangueNom), "", classeNomSimple, ".getSolrDocumentList();");
							tl(3, "Long ", str_millisRecherche(classeLangueNom), " = Long.valueOf(", str_reponse(classeLangueNom), "", str_Recherche(classeLangueNom), ".getQTime());");
							tl(3, "Long ", str_millisTransmission(classeLangueNom), " = ", str_reponse(classeLangueNom), "", str_Recherche(classeLangueNom), ".getElapsedTime();");
							tl(3, "Long ", str_numCommence(classeLangueNom), " = ", str_reponse(classeLangueNom), "", str_Recherche(classeLangueNom), ".getResults().getStart();");
							tl(3, "Long ", str_numTrouve(classeLangueNom), " = ", str_reponse(classeLangueNom), "", str_Recherche(classeLangueNom), ".getResults().getNumFound();");
							tl(3, "Integer ", str_numRetourne(classeLangueNom), " = ", str_reponse(classeLangueNom), "", str_Recherche(classeLangueNom), ".getResults().size();");
							tl(3, "String ", str_tempsRecherche(classeLangueNom), " = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(", str_millisRecherche(classeLangueNom), "), TimeUnit.MILLISECONDS.toMillis(", str_millisRecherche(classeLangueNom), ") - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(", str_millisRecherche(classeLangueNom), ")));");
							tl(3, "String ", str_tempsTransmission(classeLangueNom), " = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(", str_millisTransmission(classeLangueNom), "), TimeUnit.MILLISECONDS.toMillis(", str_millisTransmission(classeLangueNom), ") - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(", str_millisTransmission(classeLangueNom), ")));");
							tl(3, "Exception exception", str_Recherche(classeLangueNom), " = ", str_reponse(classeLangueNom), "", str_Recherche(classeLangueNom), ".getException();");
							l();
							tl(3, "JsonObject json = new JsonObject();");
							tl(3, "json.put(", q(str_numCommence(classeLangueNom)), ", ", str_numCommence(classeLangueNom), ");");
							tl(3, "json.put(", q(str_numTrouve(classeLangueNom)), ", ", str_numTrouve(classeLangueNom), ");");
							tl(3, "json.put(", q(str_numRetourne(classeLangueNom)), ", ", str_numRetourne(classeLangueNom), ");");
							tl(3, "json.put(", q(str_tempsRecherche(classeLangueNom)), ", ", str_tempsRecherche(classeLangueNom), ");");
							tl(3, "json.put(", q(str_tempsTransmission(classeLangueNom)), ", ", str_tempsTransmission(classeLangueNom), ");");
							tl(3, "JsonArray l = new JsonArray();");
							tl(3, str_liste(classeLangueNom), classeNomSimple, ".getList().stream().forEach(o -> {");
							tl(4, "JsonObject json2 = JsonObject.mapFrom(o);");
							tl(4, "List<String> fls = ", str_liste(classeLangueNom), classeNomSimple, ".getFields();");
							tl(4, "if(fls.size() > 0) {");
							tl(5, "Set<String> fieldNames = new HashSet<String>();");
							tl(5, "fieldNames.addAll(json2.fieldNames());");
							tl(5, "for(String fieldName : fieldNames) {");
							tl(6, "if(!fls.contains(fieldName))");
							tl(7, "json2.remove(fieldName);");
							tl(5, "}");
							tl(4, "}");
							tl(4, "l.add(json2);");
							tl(3, "});");
							tl(3, "json.put(", q(str_liste(classeLangueNom)), ", l);");
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
							tl(3, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = ", str_liste(classeLangueNom), "", classeNomSimple, ".get", str_RequeteSite(classeLangueNom), "_();");
							tl(3, "CaseInsensitiveHeaders ", str_requeteEnTetes(classeLangueNom), " = new CaseInsensitiveHeaders();");
							tl(3, str_requeteSite(classeLangueNom), ".set", str_RequeteEnTetes(classeLangueNom), "(", str_requeteEnTetes(classeLangueNom), ");");
							l();
							tl(3, "page", str_DocumentSolr(classeLangueNom), ".setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
							tl(3, "page.setPage", str_DocumentSolr(classeLangueNom), "(page", str_DocumentSolr(classeLangueNom), ");");
							tl(3, "page.setW(w);");
							tl(3, str_requeteSite(classeLangueNom), ".setW(w);");
							tl(3, "page.", str_initLoin(classeLangueNom), "", classePageNomSimpleMethode, "(", str_requeteSite(classeLangueNom), ");");
							tl(3, "page.html();");
						}
						else {
//							tl(3, "if(", str_liste(langueNom), "", classeNomSimple, ".size() > 0) {");
//							tl(4, "SolrDocument ", str_documentSolr(langueNom), " = ", str_documentsSolr(langueNom), ".get(0);");
//							tl(4, classeNomSimple, " o = ", str_liste(langueNom), "", classeNomSimple, ".get(0);");
//							tl(4, "Object ", str_entite(langueNom), "", str_Valeur(langueNom), ";");
//							tl(4, "Integer ", str_entite(langueNom), "", str_Numero(langueNom), " = 0;");
//							l();
//							tl(4, "w.l(", q("{"), ");");
//		//					tl(4, "for(int j = 0; j < champNoms.size(); j++) {");
//		//					tl(5, "String ", str_entite(langueNom), "VarStocke = champNoms.get(j);");
//		//					tl(5, "List<Object> ", str_entite(langueNom), "", str_Valeurs(langueNom), " = new ArrayList<>(", str_documentSolr(langueNom), ".getFieldValues(", str_entite(langueNom), "VarStocke));");
//							s(wApiGenererGet.toString());
//		//					tl(4, "}");
//							l();
//							tl(4, "w.l(", q("}"), ");");
							tl(3, "JsonObject json = JsonObject.mapFrom(", str_liste(classeLangueNom), "", classeNomSimple, ".getList().stream().findFirst().orElse(null));");
//							tl(3, "}");
						}
					}
					else if(classeApiMethode.contains("POST")) {
						tl(3, "JsonObject json = JsonObject.mapFrom(o);");
					}
					else if(classeApiMethode.contains("PATCH")) {
						tl(3, "JsonObject json = JsonObject.mapFrom(", str_requeteApi(classeLangueNom), ");");
					}
					else if(classeApiMethode.contains("DELETE")) {
						tl(3, "JsonObject json = new JsonObject();");
					}
	
					if((classeApiMethode.contains("GET") || classeApiMethode.contains(str_Recherche(classeLangueNom))) && classePageNomCanoniqueMethode != null) {
						tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(new OperationResponse(200, \"OK\", buffer, ", str_requeteEnTetes(classeLangueNom), ")));");
					}
					else if(classeApiMethode.contains("PUT")) {
						tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(", str_requeteApi(classeLangueNom), "))));");
					}
					else {
						tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(OperationResponse.completedWithJson(Optional.ofNullable(json).orElse(new JsonObject()))));");
					}
	
					tl(2, "} catch(Exception e) {");
					tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
			}
	
			s(wApiEntites.toString());
			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
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
			l();
			tl(1, "// Partagé //");
			if(classeApiMethodes.contains("PATCH") || classeApiMethodes.contains("PUT")) {
				l();
				tl(1, "public void ", str_creer(classeLangueNom), classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Handler<AsyncResult<", classeNomSimple, ">> ", str_gestionnaireEvenements(classeLangueNom), ") {");
				tl(2, "try {");
				tl(3, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
				tl(3, "String ", str_utilisateur(classeLangueNom), "Id = ", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "Id();");
				l();
				tl(3, "", str_connexionSql(classeLangueNom), ".queryWithParams(");
				tl(5, "", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_", str_creer(classeLangueNom), "");
				tl(5, ", new JsonArray(Arrays.asList(", classeNomSimple, ".class.getCanonicalName(), ", str_utilisateur(classeLangueNom), "Id))");
				tl(5, ", ", str_creer(classeLangueNom), "Async");
				tl(3, "-> {");
				tl(4, "JsonArray ", str_creer(classeLangueNom), str_Ligne(classeLangueNom), " = ", str_creer(classeLangueNom), "Async.result().getResults().stream().findFirst().orElseGet(() -> null);");
				tl(4, "Long ", classeVarClePrimaire, " = ", str_creer(classeLangueNom), "", str_Ligne(classeLangueNom), ".getLong(0);");
				tl(4, classeNomSimple, " o = new ", classeNomSimple, "();");
				tl(4, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
				tl(4, "o.set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ");");
				tl(4, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(o));");
				tl(3, "});");
				tl(2, "} catch(Exception e) {");
				tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
				tl(2, "}");
				tl(1, "}");
				l();
				tl(1, "public void ", str_requeteApi(classeLangueNom), classeNomSimple, "(", classeNomSimple, " o) {");
				tl(2, classePartsRequeteApi.nomSimple(classeLangueNom), " ", str_requeteApi(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_().get", str_RequeteApi(classeLangueNom), "_();");
				tl(2, "if(", str_requeteApi(classeLangueNom), " != null) {");
				tl(3, "List<Long> pks = ", str_requeteApi(classeLangueNom), ".getPks();");
				tl(3, "List<String> classes = ", str_requeteApi(classeLangueNom), ".getClasses();");
				s(wPks);
				tl(2, "}");
				tl(1, "}");
			}
			l();
			tl(1, "public void ", str_erreur(classeLangueNom), "", classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ", AsyncResult<?> ", str_resultat(classeLangueNom), "Async) {");
			tl(2, "Throwable e = ", str_resultat(classeLangueNom), "Async.cause();");
			tl(2, "ExceptionUtils.printRootCauseStackTrace(e);");
			tl(2, "OperationResponse ", str_reponse(classeLangueNom), "Operation = new OperationResponse(400, \"BAD REQUEST\", ");
			tl(3, "Buffer.buffer().appendString(");
			tl(4, "new JsonObject() {{");
			tl(5, "put(\"", str_erreur(classeLangueNom), "\", new JsonObject() {{");
			tl(5, "put(\"message\", e.getMessage());");
			tl(5, "}});");
			tl(4, "}}.encodePrettily()");
			tl(3, ")");
			tl(3, ", new CaseInsensitiveHeaders()");
			tl(2, ");");
	
			tl(2, classePartsConfigSite.nomSimple(classeLangueNom), " ", str_configSite(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConfigSite(classeLangueNom), "_();");
			tl(2, classePartsSiteContexte.nomSimple(classeLangueNom), " ", str_siteContexte(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_SiteContexte(classeLangueNom), "_();");
			tl(2, "MailClient mailClient = ", str_siteContexte(classeLangueNom), ".getMailClient();");
			tl(2, "MailMessage message = new MailMessage();");
			tl(2, "message.setFrom(", str_configSite(classeLangueNom), ".get", str_MailDe(classeLangueNom), "());");
			tl(2, "message.setTo(", str_configSite(classeLangueNom), ".get", str_MailAdmin(classeLangueNom), "());");
			tl(2, "message.setText(ExceptionUtils.getStackTrace(e));");
			tl(2, "message.setSubject(String.format(", str_configSite(classeLangueNom), ".get", str_SiteUrlBase(classeLangueNom), "() + \" \" + e.getMessage()));");
			tl(2, "WorkerExecutor workerExecutor = ", str_siteContexte(classeLangueNom), ".get", str_ExecuteurTravailleur(classeLangueNom), "();");
			tl(2, "workerExecutor.executeBlocking(");
			tl(3, "blockingCodeHandler -> {");
			tl(4, "mailClient.sendMail(message, result -> {");
			tl(5, "if (result.succeeded()) {");
			tl(6, "LOGGER.info(result.result());");
			tl(5, "} else {");
			tl(6, "LOGGER.error(result.cause());");
			tl(5, "}");
			tl(4, "});");
			tl(3, "}, resultHandler -> {");
			tl(3, "}");
			tl(2, ");");
			tl(2, "if(", str_requeteSite(classeLangueNom), " != null) {");
			tl(3, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
			tl(3, "if(", str_connexionSql(classeLangueNom), " != null) {");
			tl(4, "", str_connexionSql(classeLangueNom), ".rollback(a -> {");
			tl(5, "if(a.succeeded()) {");
			tl(6, "", str_connexionSql(classeLangueNom), ".close(b -> {");
			tl(7, "if(a.succeeded()) {");
			tl(8, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_reponse(classeLangueNom), "Operation));");
			tl(7, "} else {");
			tl(8, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_reponse(classeLangueNom), "Operation));");
			tl(7, "}");
			tl(6, "});");
			tl(5, "} else {");
			tl(6, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_reponse(classeLangueNom), "Operation));");
			tl(5, "}");
			tl(4, "});");
			tl(3, "} else {");
			tl(4, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_reponse(classeLangueNom), "Operation));");
			tl(3, "}");
			tl(2, "} else {");
			tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_reponse(classeLangueNom), "Operation));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void sql", classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			tl(2, "try {");
			tl(3, "SQLClient ", str_clientSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_SiteContexte(classeLangueNom), "_().get", str_ClientSql(classeLangueNom), "();");
			l();
			tl(3, "if(", str_clientSql(classeLangueNom), " == null) {");
			tl(4, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(3, "} else {");
			tl(4, "", str_clientSql(classeLangueNom), ".getConnection(sqlAsync -> {");
			tl(5, "if(sqlAsync.succeeded()) {");
			tl(6, "SQLConnection ", str_connexionSql(classeLangueNom), " = sqlAsync.result();");
			tl(6, "", str_connexionSql(classeLangueNom), ".setAutoCommit(false, a -> {");
			tl(7, "if(a.succeeded()) {");
			tl(8, "", str_requeteSite(classeLangueNom), ".set", str_ConnexionSql(classeLangueNom), "(", str_connexionSql(classeLangueNom), ");");
			tl(8, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(7, "} else {");
			tl(8, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(a.cause()));");
			tl(7, "}");
			tl(6, "});");
			tl(5, "} else {");
			tl(6, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(new Exception(sqlAsync.cause())));");
			tl(5, "}");
			tl(4, "});");
			tl(3, "}");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
	//		tl(1, "public ", classePartsRequeteSite.nomSimple(langueNom), " ", str_generer(langueNom), "", classePartsRequeteSite.nomSimple(langueNom), "", str_Pour(langueNom), "", classeNomSimple, "(", classePartsSiteContexte.nomSimple(langueNom), " ", str_siteContexte(langueNom), ", RoutingContext contexteItineraire) {");
			tl(1, "public ", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_generer(classeLangueNom), "", classePartsRequeteSite.nomSimple(classeLangueNom), "", str_Pour(classeLangueNom), "", classeNomSimple, "(", classePartsSiteContexte.nomSimple(classeLangueNom), " ", str_siteContexte(classeLangueNom), ", OperationRequest ", str_operationRequete(classeLangueNom), ") {");
			tl(2, "return ", str_generer(classeLangueNom), "", classePartsRequeteSite.nomSimple(classeLangueNom), "", str_Pour(classeLangueNom), "", classeNomSimple, "(", str_siteContexte(classeLangueNom), ", ", str_operationRequete(classeLangueNom), ", null);");
			tl(1, "}");
			l();
			tl(1, "public ", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_generer(classeLangueNom), "", classePartsRequeteSite.nomSimple(classeLangueNom), "", str_Pour(classeLangueNom), "", classeNomSimple, "(", classePartsSiteContexte.nomSimple(classeLangueNom), " ", str_siteContexte(classeLangueNom), ", OperationRequest ", str_operationRequete(classeLangueNom), ", JsonObject body) {");
			tl(2, "Vertx vertx = ", str_siteContexte(classeLangueNom), ".getVertx();");
			tl(2, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = new ", classePartsRequeteSite.nomSimple(classeLangueNom), "();");
			tl(2, "", str_requeteSite(classeLangueNom), ".set", str_ObjetJson(classeLangueNom), "(body);");
			tl(2, "", str_requeteSite(classeLangueNom), ".setVertx(vertx);");
	//		tl(2, "", str_requeteSite(langueNom), ".setContexteItineraire(contexteItineraire);");
			tl(2, "", str_requeteSite(classeLangueNom), ".set", str_SiteContexte(classeLangueNom), "_(", str_siteContexte(classeLangueNom), ");");
			tl(2, "", str_requeteSite(classeLangueNom), ".set", classePartsConfigSite.nomSimple(classeLangueNom), "_(", str_siteContexte(classeLangueNom), ".get", classePartsConfigSite.nomSimple(classeLangueNom), "());");
			tl(2, "", str_requeteSite(classeLangueNom), ".set", str_OperationRequete(classeLangueNom), "(", str_operationRequete(classeLangueNom), ");");
			tl(2, "", str_requeteSite(classeLangueNom), ".", str_initLoin(classeLangueNom), "", classePartsRequeteSite.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
			l();
			tl(2, "return ", str_requeteSite(classeLangueNom), ";");
			tl(1, "}");
			l();
			tl(1, "public void ", str_utilisateur(classeLangueNom), "", classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			tl(2, "try {");
			tl(3, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
			tl(3, "String ", str_utilisateur(classeLangueNom), "Id = ", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "Id();");
			tl(3, "if(", str_utilisateur(classeLangueNom), "Id == null) {");
			tl(4, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(3, "} else {");
			tl(4, str_connexionSql(classeLangueNom), ".queryWithParams(");
			tl(6, classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_selectC");
			tl(6, ", new JsonArray(Arrays.asList(", q(classePartsUtilisateurSite.nomCanonique(classeLangueNom)), ", ", str_utilisateur(classeLangueNom), "Id))");
			tl(6, ", selectCAsync");
			tl(4, "-> {");
			tl(5, "if(selectCAsync.succeeded()) {");
			tl(6, "try {");
			tl(7, "JsonArray ", str_utilisateur(classeLangueNom), str_Valeurs(classeLangueNom), " = selectCAsync.result().getResults().stream().findFirst().orElse(null);");
			tl(7, classePartsUtilisateurSite.nomSimple(classeLangueNom), StringUtils.capitalize(classeLangueNom), "GenApiServiceImpl ", str_utilisateur(classeLangueNom), "Service = new ", classePartsUtilisateurSite.nomSimple(classeLangueNom), StringUtils.capitalize(classeLangueNom), "GenApiServiceImpl(", str_siteContexte(classeLangueNom), ");");
			tl(7, "if(", str_utilisateur(classeLangueNom), str_Valeurs(classeLangueNom), " == null) {");
//					tl(4, str_entite(langueNom), str_Valeur(langueNom), " = Optional.ofNullable(", str_documentSolr(langueNom), ".getFieldValues(", q(", str_entite(langueNom), "Var, "_stored", ", str_entite(langueNom), "SuffixeType), ")).map(Collection<Object>::stream).orElseGet(Stream::empty).findFirst().orElse(null);");
			tl(8, "JsonObject ", str_utilisateur(classeLangueNom), "Vertx = ", str_requeteSite(classeLangueNom), ".get", str_OperationRequete(classeLangueNom), "().getUser();");
			tl(8, "JsonObject ", str_principalJson(classeLangueNom), " = KeycloakHelper.parseToken(", str_utilisateur(classeLangueNom), "Vertx.getString(\"access_token\"));");
			l();
			tl(8, "JsonObject jsonObject = new JsonObject();");
			tl(8, "jsonObject.put(\"", str_utilisateur(classeLangueNom), str_Nom(classeLangueNom), "\", ", str_principalJson(classeLangueNom), ".getString(\"preferred_username\"));");
			tl(8, "jsonObject.put(\"", str_utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "\", ", str_principalJson(classeLangueNom), ".getString(\"given_name\"));");
			tl(8, "jsonObject.put(\"", str_utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "\", ", str_principalJson(classeLangueNom), ".getString(\"family_name\"));");
			tl(8, "jsonObject.put(\"", str_utilisateur(classeLangueNom), "Id\", ", str_principalJson(classeLangueNom), ".getString(\"sub\"));");
			tl(8, str_utilisateur(classeLangueNom), classeNomSimple, str_Definir(classeLangueNom), "(", str_requeteSite(classeNomSimple), ", jsonObject);");
			l();
			tl(8, str_RequeteSite(classeLangueNom), StringUtils.capitalize(classeLangueNom), " ", str_requeteSite(classeLangueNom), "2 = new ", str_RequeteSite(classeLangueNom), StringUtils.capitalize(classeLangueNom), "();");
			tl(8, str_requeteSite(classeLangueNom), "2.set", str_ConnexionSql(classeLangueNom), "(", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "());");
			tl(8, str_requeteSite(classeLangueNom), "2.set", str_ObjetJson(classeLangueNom), "(jsonObject);");
			tl(8, str_requeteSite(classeLangueNom), "2.setVertx(", str_requeteSite(classeLangueNom), ".getVertx());");
			tl(8, str_requeteSite(classeLangueNom), "2.set", str_SiteContexte(classeLangueNom), "_(", str_siteContexte(classeLangueNom), ");");
			tl(8, str_requeteSite(classeLangueNom), "2.set", classePartsConfigSite.nomSimple(classeLangueNom), "_(", str_siteContexte(classeLangueNom), ".get", classePartsConfigSite.nomSimple(classeLangueNom), "());");
//			tl(8, str_requeteSite(classeLangueNom), "2.set", str_OperationRequete(classeLangueNom), "(", str_operationRequete(classeLangueNom), ");");
			tl(8, str_requeteSite(classeLangueNom), "2.set", str_Utilisateur(classeLangueNom), "Id(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "Id());");
			tl(8, str_requeteSite(classeLangueNom), "2.", str_initLoin(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
			l();
			tl(8, str_utilisateur(classeLangueNom), "Service.", str_creer(classeLangueNom), str_UtilisateurSite(classeNomSimple), "(", str_requeteSite(classeLangueNom), "2, b -> {");
			tl(9, "if(b.succeeded()) {");
			tl(10, str_UtilisateurSite(classeNomSimple), " ", str_utilisateurSite(classeNomSimple), " = b.result();");
			tl(10, str_utilisateur(classeLangueNom), "Service.", "sql", "POST", str_UtilisateurSite(classeNomSimple), "(", str_utilisateurSite(classeNomSimple), ", c -> {");
			tl(11, "if(c.succeeded()) {");
			tl(12, str_utilisateur(classeLangueNom), "Service.", str_definir(classeLangueNom), str_UtilisateurSite(classeNomSimple), "(", str_utilisateurSite(classeNomSimple), ", d -> {");
			tl(13, "if(d.succeeded()) {");
			tl(14, str_utilisateur(classeLangueNom), "Service.", str_attribuer(classeLangueNom), str_UtilisateurSite(classeNomSimple), "(", str_utilisateurSite(classeNomSimple), ", e -> {");
			tl(15, "if(e.succeeded()) {");
			tl(16, str_utilisateur(classeLangueNom), "Service.", str_indexer(classeLangueNom), str_UtilisateurSite(classeNomSimple), "(", str_utilisateurSite(classeNomSimple), ", f -> {");
			tl(17, "if(f.succeeded()) {");
			tl(18, str_requeteSite(classeLangueNom), ".set", classePartsUtilisateurSite.nomSimple(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), ");");
			tl(18, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), "", str_Nom(classeLangueNom), "(", str_principalJson(classeLangueNom), ".getString(\"preferred_username\"));");
			tl(18, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), "", str_Prenom(classeLangueNom), "(", str_principalJson(classeLangueNom), ".getString(\"given_name\"));");
			tl(18, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), "", str_NomFamille(classeLangueNom), "(", str_principalJson(classeLangueNom), ".getString(\"family_name\"));");
			tl(18, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), "Id(", str_principalJson(classeLangueNom), ".getString(\"sub\"));");
			tl(18, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(17, "} else {");
			tl(18, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", f);");
			tl(17, "}");
			tl(16, "});");
			tl(15, "} else {");
			tl(16, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", e);");
			tl(15, "}");
			tl(14, "});");
			tl(13, "} else {");
			tl(14, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", d);");
			tl(13, "}");
			tl(12, "});");
			tl(11, "} else {");
			tl(12, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
			tl(11, "}");
			tl(10, "});");
			tl(9, "} else {");
			tl(10, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", b);");
			tl(9, "}");
			tl(8, "});");
			tl(7, "} else {");
			tl(8, "Long ", classeVarClePrimaire, str_Utilisateur(classeLangueNom), " = ", str_utilisateur(classeLangueNom), str_Valeurs(classeLangueNom), ".getLong(0);");
			tl(8, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classePartsUtilisateurSite.nomSimple(classeLangueNom), "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classePartsUtilisateurSite.nomSimple(classeLangueNom), ">();");
			tl(8, str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(true);");
			tl(8, str_listeRecherche(classeLangueNom), ".setC(", classePartsUtilisateurSite.nomSimple(classeLangueNom), ".class);");
			tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_utilisateur(classeLangueNom), "Id_indexed_string:\" + ClientUtils.escapeQueryChars(", str_utilisateur(classeLangueNom), "Id));");
			tl(8, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"pk_indexed_long:\" + ", classeVarClePrimaire, str_Utilisateur(classeLangueNom), ");");
			tl(8, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), classePartsListeRecherche.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");

			tl(8, classePartsUtilisateurSite.nomSimple(classeLangueNom), " ", str_utilisateurSite(classeLangueNom), "1 = ", str_listeRecherche(classeLangueNom), ".getList().stream().findFirst().orElse(null);");
			l();
			tl(8, "JsonObject ", str_utilisateur(classeLangueNom), "Vertx = ", str_requeteSite(classeLangueNom), ".get", str_OperationRequete(classeLangueNom), "().getUser();");
			tl(8, "JsonObject ", str_principalJson(classeLangueNom), " = KeycloakHelper.parseToken(", str_utilisateur(classeLangueNom), "Vertx.getString(\"access_token\"));");
			l();
			tl(8, "JsonObject jsonObject = Optional.ofNullable(", str_utilisateurSite(classeLangueNom), "1).map(u -> JsonObject.mapFrom(u)).orElse(new JsonObject());");
			tl(8, "jsonObject.put(\"", str_utilisateur(classeLangueNom), str_Nom(classeLangueNom), "\", ", str_principalJson(classeLangueNom), ".getString(\"preferred_username\"));");
			tl(8, "jsonObject.put(\"", str_utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "\", ", str_principalJson(classeLangueNom), ".getString(\"given_name\"));");
			tl(8, "jsonObject.put(\"", str_utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "\", ", str_principalJson(classeLangueNom), ".getString(\"family_name\"));");
			tl(8, "jsonObject.put(\"", str_utilisateur(classeLangueNom), "Id\", ", str_principalJson(classeLangueNom), ".getString(\"sub\"));");
			tl(8, "Boolean ", str_definir(classeLangueNom), " = ", str_utilisateur(classeLangueNom), classeNomSimple, str_Definir(classeLangueNom), "(", str_requeteSite(classeNomSimple), ", jsonObject);");
			tl(8, "if(", str_definir(classeLangueNom), ") {");
			tl(9, classePartsUtilisateurSite.nomSimple(classeLangueNom), " ", str_utilisateurSite(classeLangueNom), ";");
			tl(9, "if(", str_utilisateurSite(classeLangueNom), "1 == null) {");
			tl(10, str_utilisateurSite(classeLangueNom), " = new ", classePartsUtilisateurSite.nomSimple(classeLangueNom), "();");
			tl(10, str_utilisateurSite(classeLangueNom), ".set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, str_Utilisateur(classeLangueNom), ");");
			tl(10, str_utilisateurSite(classeLangueNom), ".set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), ");");
			tl(9, "} else {");
			tl(10, str_utilisateurSite(classeLangueNom), " = ", str_utilisateurSite(classeLangueNom), "1;");
			tl(9, "}");
			l();
			tl(9, str_RequeteSite(classeLangueNom), StringUtils.capitalize(classeLangueNom), " ", str_requeteSite(classeLangueNom), "2 = new ", str_RequeteSite(classeLangueNom), StringUtils.capitalize(classeLangueNom), "();");
			tl(9, str_requeteSite(classeLangueNom), "2.set", str_ConnexionSql(classeLangueNom), "(", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "());");
			tl(9, str_requeteSite(classeLangueNom), "2.set", str_ObjetJson(classeLangueNom), "(jsonObject);");
			tl(9, str_requeteSite(classeLangueNom), "2.setVertx(", str_requeteSite(classeLangueNom), ".getVertx());");
			tl(9, str_requeteSite(classeLangueNom), "2.set", str_SiteContexte(classeLangueNom), "_(", str_siteContexte(classeLangueNom), ");");
			tl(9, str_requeteSite(classeLangueNom), "2.set", classePartsConfigSite.nomSimple(classeLangueNom), "_(", str_siteContexte(classeLangueNom), ".get", classePartsConfigSite.nomSimple(classeLangueNom), "());");
//			tl(9, str_requeteSite(classeLangueNom), "2.set", str_OperationRequete(classeLangueNom), "(", str_operationRequete(classeLangueNom), ");");
			tl(9, str_requeteSite(classeLangueNom), "2.set", str_Utilisateur(classeLangueNom), "Id(", str_requeteSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "Id());");
			tl(9, str_requeteSite(classeLangueNom), "2.", str_initLoin(classeLangueNom), classePartsRequeteSite.nomSimple(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
			tl(9, str_utilisateurSite(classeLangueNom), ".set", str_RequeteSite(classeLangueNom), "_(", str_requeteSite(classeLangueNom), "2);");
			l();
			tl(9, str_utilisateur(classeLangueNom), "Service.", "sql", "PATCH", str_UtilisateurSite(classeNomSimple), "(", str_utilisateurSite(classeNomSimple), ", c -> {");
			tl(10, "if(c.succeeded()) {");
			tl(11, str_utilisateur(classeLangueNom), "Service.", str_definir(classeLangueNom), str_UtilisateurSite(classeNomSimple), "(", str_utilisateurSite(classeNomSimple), ", d -> {");
			tl(12, "if(d.succeeded()) {");
			tl(13, str_utilisateur(classeLangueNom), "Service.", str_attribuer(classeLangueNom), str_UtilisateurSite(classeNomSimple), "(", str_utilisateurSite(classeNomSimple), ", e -> {");
			tl(14, "if(e.succeeded()) {");
			tl(15, str_utilisateur(classeLangueNom), "Service.", str_indexer(classeLangueNom), str_UtilisateurSite(classeNomSimple), "(", str_utilisateurSite(classeNomSimple), ", f -> {");
			tl(16, "if(f.succeeded()) {");
			tl(17, str_requeteSite(classeLangueNom), ".set", classePartsUtilisateurSite.nomSimple(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), ");");
			tl(17, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Nom(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Nom(classeLangueNom), "());");
			tl(17, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "());");
			tl(17, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "());");
			tl(17, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), "Id(", str_utilisateurSite(classeLangueNom), ".get", str_Utilisateur(classeLangueNom), "Id());");
			tl(17, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(16, "} else {");
			tl(17, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", f);");
			tl(16, "}");
			tl(15, "});");
			tl(14, "} else {");
			tl(15, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", e);");
			tl(14, "}");
			tl(13, "});");
			tl(12, "} else {");
			tl(13, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", d);");
			tl(12, "}");
			tl(11, "});");
			tl(10, "} else {");
			tl(11, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), ", ", str_gestionnaireEvenements(classeLangueNom), ", c);");
			tl(10, "}");
			tl(9, "});");
			tl(8, "} else {");
			tl(9, str_requeteSite(classeLangueNom), ".set", classePartsUtilisateurSite.nomSimple(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "1);");
			tl(9, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Nom(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "1.get", str_Utilisateur(classeLangueNom), str_Nom(classeLangueNom), "());");
			tl(9, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "1.get", str_Utilisateur(classeLangueNom), str_Prenom(classeLangueNom), "());");
			tl(9, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "(", str_utilisateurSite(classeLangueNom), "1.get", str_Utilisateur(classeLangueNom), str_NomFamille(classeLangueNom), "());");
			tl(9, str_requeteSite(classeLangueNom), ".set", str_Utilisateur(classeLangueNom), "Id(", str_utilisateurSite(classeLangueNom), "1.get", str_Utilisateur(classeLangueNom), "Id());");
			tl(9, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(8, "}");
			tl(7, "}");
			tl(6, "} catch(Exception e) {");
			tl(7, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(6, "}");
			tl(5, "} else {");
			tl(6, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(new Exception(selectCAsync.cause())));");
			tl(5, "}");
			tl(4, "});");
			tl(3, "}");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public Boolean ", str_utilisateur(classeLangueNom), classeNomSimple, str_Definir(classeLangueNom), "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeNomSimple), ", JsonObject jsonObject) {");
			tl(2, "return true;");
			tl(1, "}");
			l();
			tl(1, "public void ", str_recherche(classeLangueNom), "", classeNomSimple, "(", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), ", Boolean ", str_peupler(classeLangueNom), ", Boolean ", str_stocker(classeLangueNom), ", String ", str_classeApiUriMethode(classeLangueNom), ", Handler<AsyncResult<", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, ">>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			tl(2, "try {");
			tl(3, "OperationRequest ", str_operationRequete(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_OperationRequete(classeLangueNom), "();");
			tl(3, "String ", str_entite(classeLangueNom), "", str_Liste(classeLangueNom), "Str = ", str_requeteSite(classeLangueNom), ".get", str_OperationRequete(classeLangueNom), "().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
			tl(3, "String[] ", str_entite(classeLangueNom), "", str_Liste(classeLangueNom), " = ", str_entite(classeLangueNom), "", str_Liste(classeLangueNom), "Str == null ? null : ", str_entite(classeLangueNom), "", str_Liste(classeLangueNom), "Str.split(", q(",\\s*"), ");");
			tl(3, classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", classePartsListeRecherche.nomSimple(classeLangueNom), "<", classeNomSimple, ">();");
			tl(3, "", str_listeRecherche(classeLangueNom), ".set", str_Peupler(classeLangueNom), "(", str_peupler(classeLangueNom), ");");
			tl(3, "", str_listeRecherche(classeLangueNom), ".set", str_Stocker(classeLangueNom), "(", str_stocker(classeLangueNom), ");");
			tl(3, "", str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
			tl(3, "", str_listeRecherche(classeLangueNom), ".setC(", classeNomSimple, ".class);");
			tl(3, "if(", str_entite(classeLangueNom), "", str_Liste(classeLangueNom), " != null)");
			tl(4, "", str_listeRecherche(classeLangueNom), ".addFields(", str_entite(classeLangueNom), "", str_Liste(classeLangueNom), ");");
//			tl(3, "", str_liste(langueNom), "", str_Recherche(langueNom), ".addSort(\"", str_archive(langueNom), "_indexed_boolean\", ORDER.asc);");
//			tl(3, "", str_liste(langueNom), "", str_Recherche(langueNom), ".addSort(\"", str_supprime(langueNom), "_indexed_boolean\", ORDER.asc);");
//			tl(3, "", str_liste(langueNom), "", str_Recherche(langueNom), ".addFilterQuery(\"", str_classeNomsCanoniques(langueNom), "_indexed_strings:\" + ClientUtils.escapeQueryChars(", q(classeNomCanonique), "));");
//			if(classeFiltresTrouves && classeFiltres.contains("utilisateurId"))
//				tl(3, "", str_liste(langueNom), "", str_Recherche(langueNom), ".addFilterQuery(\"", str_utilisateur(langueNom), "Id_indexed_string:\" + ClientUtils.escapeQueryChars(", str_requeteSite(langueNom), ".get", str_Utilisateur(langueNom), "Id()));");
			if(classeVarModifie != null)
				tl(3, str_listeRecherche(classeLangueNom), ".add(\"json.facet\", \"{max_", classeVarModifie, ":'max(", classeVarModifie, "_indexed_date)'}\");");
			s(wFacets);
//			tl(3, classePartsUtilisateurSite.nomSimple(langueNom), " ", str_utilisateurSite(langueNom), " = ", str_requeteSite(langueNom), ".get", classePartsUtilisateurSite.nomSimple(langueNom), "();");
//			tl(3, "if(", str_utilisateurSite(langueNom), " != null && !", str_utilisateurSite(langueNom), ".get", str_VoirSupprime(langueNom), "())");
//			tl(4, "", str_liste(langueNom), "", str_Recherche(langueNom), ".addFilterQuery(\"", str_supprime(langueNom), "_indexed_boolean:false\");");
//			tl(3, "if(", str_utilisateurSite(langueNom), " != null && !", str_utilisateurSite(langueNom), ".get", str_VoirArchive(langueNom), "())");
//			tl(4, "", str_liste(langueNom), "", str_Recherche(langueNom), ".addFilterQuery(\"", str_archive(langueNom), "_indexed_boolean:false\");");
			l();
			tl(3, "String id = ", str_operationRequete(classeLangueNom), ".getParams().getJsonObject(\"path\").getString(\"id\");");
			tl(3, "if(", classeVarCleUnique, " != null) {");
			tl(4, "", str_listeRecherche(classeLangueNom), ".addFilterQuery(\"(", classeVarCleUnique, ":\" + ClientUtils.escapeQueryChars(id) + \" OR ", classeVarId, "_indexed_string:\" + ClientUtils.escapeQueryChars(id) + \")\");");
			tl(3, "}");
			if(classeRoleSession) {
				l();
				tl(3, "List<String> roles = Arrays.asList(\"", StringUtils.join(classeRoles, "\", \""), "\");");
				tl(3, "if(");
				tl(5, "!CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRessource(classeLangueNom), "(), roles)");
				tl(5, "&& !CollectionUtils.containsAny(", str_requeteSite(classeLangueNom), ".get", str_UtilisateurRolesRoyaume(classeLangueNom), "(), roles)");
				tl(5, ") {");
				tl(4, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"sessionId_indexed_string:\" + ClientUtils.escapeQueryChars(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".getSessionId()).orElse(\"-----\")));");
				tl(3, "}");
			}
			l();
			tl(3, "", str_operationRequete(classeLangueNom), ".getParams().getJsonObject(\"query\").forEach(param", str_Requete(classeLangueNom), " -> {");
			tl(4, "String ", str_entite(classeLangueNom), "Var = null;");
			tl(4, "String ", str_valeur(classeLangueNom), "", str_Indexe(classeLangueNom), " = null;");
			tl(4, "String var", str_Indexe(classeLangueNom), " = null;");
			tl(4, "String ", str_valeur(classeLangueNom), "", str_Tri(classeLangueNom), " = null;");
			tl(4, "Integer ", str_recherche(classeLangueNom), "", str_Debut(classeLangueNom), " = null;");
			tl(4, "Integer ", str_recherche(classeLangueNom), "Num = null;");
			tl(4, "String param", str_Nom(classeLangueNom), " = param", str_Requete(classeLangueNom), ".getKey();");
			tl(4, "Object param", str_Valeurs(classeLangueNom), "", str_Objet(classeLangueNom), " = param", str_Requete(classeLangueNom), ".getValue();");
			tl(4, "JsonArray param", str_Objets(classeLangueNom), " = param", str_Valeurs(classeLangueNom), "", str_Objet(classeLangueNom), " instanceof JsonArray ? (JsonArray)param", str_Valeurs(classeLangueNom), "", str_Objet(classeLangueNom), " : new JsonArray().add(param", str_Valeurs(classeLangueNom), "", str_Objet(classeLangueNom), ");");
			l();
			tl(4, "try {");
			tl(5, "for(Object param", str_Objet(classeLangueNom), " : param", str_Objets(classeLangueNom), ") {");
			tl(6, "switch(param", str_Nom(classeLangueNom), ") {");
	
			tl(7, "case \"q\":");
			tl(8, "", str_entite(classeLangueNom), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(classeLangueNom), ", \":\"));");
			tl(8, "var", str_Indexe(classeLangueNom), " = \"*\".equals(", str_entite(classeLangueNom), "Var) ? ", str_entite(classeLangueNom), "Var : ", classeNomSimple, ".var", str_Recherche(classeLangueNom), "", classeNomSimple, "(", str_entite(classeLangueNom), "Var);");
			tl(8, "", str_valeur(classeLangueNom), "", str_Indexe(classeLangueNom), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(classeLangueNom), ", \":\")), \"UTF-8\");");
			tl(8, "", str_valeur(classeLangueNom), "", str_Indexe(classeLangueNom), " = StringUtils.isEmpty(", str_valeur(classeLangueNom), "", str_Indexe(classeLangueNom), ") ? \"*\" : ", str_valeur(classeLangueNom), "", str_Indexe(classeLangueNom), ";");
//			tl(8, "if(StringUtils.isEmpty(", str_valeur(langueNom), "", str_Indexe(langueNom), ")) {");
//			tl(9, "", str_valeur(langueNom), "", str_Indexe(langueNom), " = ", str_entite(langueNom), "Var;");
//			tl(9, "", str_entite(langueNom), "Var = \"*\";");
//			tl(8, "}");
			tl(8, "", str_listeRecherche(classeLangueNom), ".setQuery(var", str_Indexe(classeLangueNom), " + \":\" + (\"*\".equals(", str_valeur(classeLangueNom), "", str_Indexe(classeLangueNom), ") ? ", str_valeur(classeLangueNom), "", str_Indexe(classeLangueNom), " : ClientUtils.escapeQueryChars(", str_valeur(classeLangueNom), "", str_Indexe(classeLangueNom), ")));");
			tl(8, "if(!\"*\".equals(", str_entite(classeLangueNom), "Var)) {");
			tl(9, "", str_listeRecherche(classeLangueNom), ".setHighlight(true);");
			tl(9, "", str_listeRecherche(classeLangueNom), ".setHighlightSnippets(3);");
			tl(9, "", str_listeRecherche(classeLangueNom), ".addHighlightField(var", str_Indexe(classeLangueNom), ");");
			tl(9, "", str_listeRecherche(classeLangueNom), ".setParam(\"hl.encoder\", \"html\");");
			tl(8, "}");
			tl(8, "break;");
	
			tl(7, "case \"fq\":");
			tl(8, "", str_entite(classeLangueNom), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(classeLangueNom), ", \":\"));");
			tl(8, "", str_valeur(classeLangueNom), "", str_Indexe(classeLangueNom), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(classeLangueNom), ", \":\")), \"UTF-8\");");
			tl(8, "var", str_Indexe(classeLangueNom), " = ", classeNomSimple, ".var", str_Indexe(classeLangueNom), classeNomSimple, "(", str_entite(classeLangueNom), "Var);");
			tl(8, "", str_listeRecherche(classeLangueNom), ".addFilterQuery(var", str_Indexe(classeLangueNom), " + \":\" + ClientUtils.escapeQueryChars(", str_valeur(classeLangueNom), "", str_Indexe(classeLangueNom), "));");
			tl(8, "break;");
	
			tl(7, "case \"sort\":");
			tl(8, "", str_entite(classeLangueNom), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(classeLangueNom), ", \" \"));");
			tl(8, "", str_valeur(classeLangueNom), "", str_Tri(classeLangueNom), " = StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(classeLangueNom), ", \" \"));");
			tl(8, "var", str_Indexe(classeLangueNom), " = ", classeNomSimple, ".var", str_Indexe(classeLangueNom), classeNomSimple, "(", str_entite(classeLangueNom), "Var);");
			tl(8, "", str_listeRecherche(classeLangueNom), ".addSort(var", str_Indexe(classeLangueNom), ", ORDER.valueOf(", str_valeur(classeLangueNom), "", str_Tri(classeLangueNom), "));");
			tl(8, "break;");
//	
//			tl(7, "case \"fl\":");
//			tl(8, "", str_entite(langueNom), "Var = StringUtils.trim((String)param", str_Objet(langueNom), ");");
//			tl(8, "var", str_Indexe(langueNom), " = ", classeNomSimple, ".var", str_Indexe(langueNom), classeNomSimple, "(", str_entite(langueNom), "Var);");
//			tl(8, "", str_liste(langueNom), "", str_Recherche(langueNom), ".addField(var", str_Indexe(langueNom), ");");
//			tl(8, "break;");
	
			tl(7, "case \"start\":");
			tl(8, "", str_recherche(classeLangueNom), "", str_Debut(classeLangueNom), " = (Integer)param", str_Objet(classeLangueNom), ";");
			tl(8, "", str_listeRecherche(classeLangueNom), ".setStart(", str_recherche(classeLangueNom), "", str_Debut(classeLangueNom), ");");
			tl(8, "break;");
	
			tl(7, "case \"rows\":");
			tl(8, "", str_recherche(classeLangueNom), "Num = (Integer)param", str_Objet(classeLangueNom), ";");
			tl(8, "", str_listeRecherche(classeLangueNom), ".setRows(", str_recherche(classeLangueNom), "Num);");
			tl(8, "break;");
	
			tl(6, "}");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(4, "}");

			tl(3, "});");
			if(classeVarCree != null) {
				tl(3, "if(", str_listeRecherche(classeLangueNom), ".getSorts().size() == 0) {");
				if(classeTrisVar != null && classeTrisVar.size() > 0) {
					for(int i = 0; i < classeTrisVar.size(); i++) {
						String classeTriVar = classeTrisVar.get(i);
						String classeTriOrdre = classeTrisOrdre.get(i);
						String classeTriSuffixeType = classeTrisSuffixeType.get(i);
						tl(4, str_listeRecherche(classeLangueNom), ".addSort(\"", classeTriVar, "_indexed", classeTriSuffixeType, "\", ORDER.", classeTriOrdre, ");");
					}
				}
				else {
					tl(4, str_listeRecherche(classeLangueNom), ".addSort(\"", classeVarCree, "_indexed_date\", ORDER.desc);");
				}
				tl(3, "}");
			}
			tl(3, "", str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), "", str_PourClasse(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
			tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture(", str_listeRecherche(classeLangueNom), "));");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void ", str_definir(classeLangueNom), "", classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			tl(2, "try {");
			tl(3, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
			tl(3, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
			tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			tl(3, "", str_connexionSql(classeLangueNom), ".queryWithParams(");
			tl(5, "", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_", str_definir(classeLangueNom), "");
			tl(5, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, ", ", classeVarClePrimaire, ", ", classeVarClePrimaire, "))");
			tl(5, ", ", str_definir(classeLangueNom), "Async");
			tl(3, "-> {");
			tl(4, "if(", str_definir(classeLangueNom), "Async.succeeded()) {");
			tl(5, "try {");
			tl(6, "for(JsonArray definition : ", str_definir(classeLangueNom), "Async.result().getResults()) {");
			tl(7, "try {");
			tl(8, "o.", str_definir(classeLangueNom), "", str_PourClasse(classeLangueNom), "(definition.getString(0), definition.getString(1));");
			tl(7, "} catch(Exception e) {");
			tl(8, "LOGGER.error(e);");
			tl(7, "}");
			tl(6, "}");
			tl(6, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(5, "} catch(Exception e) {");
			tl(6, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(5, "}");
			tl(4, "} else {");
			tl(5, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(new Exception(", str_definir(classeLangueNom), "Async.cause())));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void ", str_attribuer(classeLangueNom), "", classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			tl(2, "try {");
			tl(3, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
			tl(3, "SQLConnection ", str_connexionSql(classeLangueNom), " = ", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "();");
			tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			tl(3, "", str_connexionSql(classeLangueNom), ".queryWithParams(");
			tl(5, "", classePartsSiteContexte.nomSimple(classeLangueNom), ".SQL_", str_attribuer(classeLangueNom), "");
			tl(5, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, ", ", classeVarClePrimaire, "))");
			tl(5, ", ", str_attribuer(classeLangueNom), "Async");
			tl(3, "-> {");
			tl(4, "try {");
			tl(5, "if(", str_attribuer(classeLangueNom), "Async.succeeded()) {");
			tl(6, "if(", str_attribuer(classeLangueNom), "Async.result() != null) {");
			tl(7, "for(JsonArray definition : ", str_attribuer(classeLangueNom), "Async.result().getResults()) {");
			tl(8, "if(pk.equals(definition.getLong(0)))");
			tl(9, "o.", str_attribuer(classeLangueNom), "", str_PourClasse(classeLangueNom), "(definition.getString(2), definition.getLong(1));");
			tl(8, "else");
			tl(9, "o.", str_attribuer(classeLangueNom), "", str_PourClasse(classeLangueNom), "(definition.getString(3), definition.getLong(0));");
			tl(7, "}");
			tl(6, "}");
			tl(6, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(5, "} else {");
			tl(6, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(new Exception(", str_attribuer(classeLangueNom), "Async.cause())));");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void ", str_indexer(classeLangueNom), "", classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> ", str_gestionnaireEvenements(classeLangueNom), ") {");
			tl(2, "", classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), " = o.get", str_RequeteSite(classeLangueNom), "_();");
			tl(2, "try {");
			tl(3, "o.", str_initLoin(classeLangueNom), "", str_PourClasse(classeLangueNom), "(", str_requeteSite(classeLangueNom), ");");
			tl(3, "o.", str_indexer(classeLangueNom), "", str_PourClasse(classeLangueNom), "();");
			tl(3, "if(BooleanUtils.isFalse(Optional.ofNullable(", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_()).map(", classePartsRequeteApi.nomSimple(classeLangueNom), "::getEmpty).orElse(null))) {");
			tl(4, classePartsRequeteSite.nomSimple(classeLangueNom), " ", str_requeteSite(classeLangueNom), "2 = ", str_generer(classeLangueNom), "", classePartsRequeteSite.nomSimple(classeLangueNom), "", str_Pour(classeLangueNom), "", classeNomSimple, "(", str_siteContexte(classeLangueNom), ", ", str_requeteSite(classeLangueNom), ".get", str_OperationRequete(classeLangueNom), "(), new JsonObject());");
			tl(4, str_requeteSite(classeLangueNom), "2.set", str_ConnexionSql(classeLangueNom), "(", str_requeteSite(classeLangueNom), ".get", str_ConnexionSql(classeLangueNom), "());");
			tl(4, str_ListeRecherche(classeLangueNom), "<", classeNomSimple, "> ", str_listeRecherche(classeLangueNom), " = new ", str_ListeRecherche(classeLangueNom), "<", classeNomSimple, ">();");
			tl(4, str_listeRecherche(classeLangueNom), ".set", str_Peupler(classeLangueNom), "(true);");
			tl(4, str_listeRecherche(classeLangueNom), ".setQuery(\"*:*\");");
			tl(4, str_listeRecherche(classeLangueNom), ".setC(", classeNomSimple, ".class);");
			tl(4, str_listeRecherche(classeLangueNom), ".addFilterQuery(\"", str_modifie(classeLangueNom), "_indexed_date:[\" + DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(", str_requeteSite(classeLangueNom), ".get", str_RequeteApi(classeLangueNom), "_().get", str_Cree(classeLangueNom), "()", ".toInstant(), ZoneId.of(\"UTC\"))) + \" TO *]\");");
			s(wIndexerFacetAdd);
			tl(4, str_listeRecherche(classeLangueNom), ".setRows(1000);");
			tl(4, str_listeRecherche(classeLangueNom), ".", str_initLoin(classeLangueNom), str_ListeRecherche(classeLangueNom), "(", str_requeteSite(classeLangueNom), "2);");
			tl(4, "List<Future> futures = new ArrayList<>();");
			s(wIndexerFacetFor);
			l();
			tl(4, "CompositeFuture.all(futures).setHandler(a -> {");
			tl(5, "if(a.succeeded()) {");
			tl(6, "LOGGER.info(\"", str_Recharger(classeLangueNom), " ", str_relations(classeLangueNom), " ", str_a_réussi(classeLangueNom), ". \");");
			tl(6, classeNomSimpleGenApiServiceImpl, " service = new ", classeNomSimpleGenApiServiceImpl, "(", str_requeteSite(classeLangueNom), "2.get", str_SiteContexte(classeLangueNom), "_());");
			tl(6, "List<Future> futures2 = new ArrayList<>();");
			tl(6, "for(", classeNomSimple, " o2 : ", str_listeRecherche(classeLangueNom), ".getList()) {");
			tl(7, "futures2.add(");
			tl(8, "service.futurePATCH", classeNomSimple, "(o2, b -> {");
			tl(9, "if(b.succeeded()) {");
			tl(10, "LOGGER.info(String.format(\"", classeNomSimple, " %s ", str_rechargé(classeLangueNom), ". \", o2.get", StringUtils.capitalize(classeVarClePrimaire), "()));");
			tl(9, "} else {");
			tl(10, "LOGGER.info(String.format(\"", classeNomSimple, " %s ", str_a_échoué(classeLangueNom), ". \", o2.get", StringUtils.capitalize(classeVarClePrimaire), "()));");
			tl(10, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(b.cause()));");
			tl(9, "}");
			tl(8, "})");
			tl(7, ");");
			tl(6, "}");
			l();
			tl(6, "CompositeFuture.all(futures2).setHandler(b -> {");
			tl(7, "if(b.succeeded()) {");
			tl(8, "LOGGER.info(\"", str_Recharger(classeLangueNom), " ", classeNomSimple, " ", str_a_réussi(classeLangueNom), ". \");");
			tl(8, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(7, "} else {");
			tl(8, "LOGGER.error(\"", str_Recharger(classeLangueNom), " ", str_relations(classeLangueNom), " ", str_a_échoué(classeLangueNom), ". \", b.cause());");
			tl(8, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), "2, ", str_gestionnaireEvenements(classeLangueNom), ", b);");
			tl(7, "}");
			tl(6, "});");
			tl(5, "} else {");
			tl(6, "LOGGER.error(\"", str_Recharger(classeLangueNom), " ", str_relations(classeLangueNom), " ", str_a_échoué(classeLangueNom), ". \", a.cause());");
			tl(6, str_erreur(classeLangueNom), classeNomSimple, "(", str_requeteSite(classeLangueNom), "2, ", str_gestionnaireEvenements(classeLangueNom), ", a);");
			tl(5, "}");
			tl(4, "});");


			tl(3, "} else {");
			tl(4, str_gestionnaireEvenements(classeLangueNom), ".handle(Future.succeededFuture());");
			tl(3, "}");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_gestionnaireEvenements(classeLangueNom), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
	
			tl(0, "}");

			auteurGenApiServiceImpl.flushClose();
			System.out.println("Ecrire: " + classeCheminGenApiServiceImpl); 
		}
	}
}
