package org.computate.frFR.java; 

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**   
 * nomCanonique.enUS: org.computate.enUS.java.WriteApiClass
 * 
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */     
public class EcrireApiClasse extends EcrireGenClasse {   
//
//	/**
//	 * var.enUS: writerApiPackageInfo
//	 */
//	protected ToutEcrivain auteurApiEnsembleInfo;

	/**
	 * var.enUS: writerApiServiceImpl
	 */
	protected ToutEcrivain auteurApiServiceImpl;

	/**
	 * var.enUS: writerGenApiServiceImpl
	 */
	protected ToutEcrivain auteurGenApiServiceImpl;

	/**
	 * var.enUS: writerGenApiService
	 */  
	protected ToutEcrivain auteurGenApiService;

	/////////////////////
	// classeNomSimple //
	/////////////////////

	/**
	 * var.enUS: classSimpleNameApiPackageInfo
	 */
	protected String classeNomSimpleApiEnsembleInfo;

	/**
	 * var.enUS: classSimpleNameGenApiServiceImpl
	 */
	protected String classeNomSimpleGenApiServiceImpl;

	/**
	 * var.enUS: classSimpleNameApiServiceImpl
	 */
	protected String classeNomSimpleApiServiceImpl;

	/**
	 * var.enUS: classSimpleNameGenApiService
	 */
	protected String classeNomSimpleGenApiService;

	////////////////////////
	// classeNomCanonique //
	////////////////////////

	/**
	 * var.enUS: classCanonicalNameApiPackageInfo
	 */
	protected String classeNomCanoniqueApiEnsembleInfo;

	/**
	 * var.enUS: classCanonicalNameGenApiServiceImpl
	 */
	protected String classeNomCanoniqueGenApiServiceImpl;

	/**
	 * var.enUS: classCanonicalNameApiServiceImpl
	 */
	protected String classeNomCanoniqueApiServiceImpl;

	/**
	 * var.enUS: classCanonicalNameGenApiService
	 */
	protected String classeNomCanoniqueGenApiService;

	/**
	 * var.enUS: classApiMethods
	 */
	protected List<String> classeApiMethodes;

	/**
	 * var.enUS: apiCodeClassBegin
	 * param1.var.enUS: languageName
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
//		l();
//		tl(1, "public static final String VAL_nomCanonique", classeNomSimple, " = \"", classeNomCanonique, "\";");
//		tl(1, "public static final String VAL_virguleEspace = \", \";");
//		tl(1, "public static final String VAL_citation = \"\\\"\";");
//		tl(1, "public static final String VAL_citationDeuxPointsEspaceCitation = \"\\\": \\\"\";");
//		tl(1, "public static final String VAL_citationDeuxPointsEspace = \"\\\": \";");
//		tl(1, "public static final String VAL_citationLigne = \"\\\"\\n\";");
//		tl(1, "public static final String VAL_ligne = \"\\n\";");
//		tl(1, "public static final String VAL_citationVirguleEspaceCitation = \"\\\", \\\"\";");
//		tl(1, "public static final String VAL_citationDeuxPointsEspaceGuillmets = \"\\\": [\";");
//		tl(1, "public static final String VAL_guillmetsFin = \"]\";");
	}
//
//	/**
//	 * var.enUS: writeApiPackageInfo
//	 * param1.var.enUS: languageName
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
	 * var.enUS: writeGenApiService
	 * param1.var.enUS: languageName
	 * 
	 * r: auteurGenApiService
	 * r.enUS: writerGenApiService
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: operationRequete
	 * r.enUS: operationRequest
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
	 * 
	 * r: recherche
	 * r.enUS: search
	 **/ 
	public void ecrireGenApiService(String langueNom) throws Exception {
		if(auteurGenApiService != null) {
			auteurGenApiService.l("package ", classeNomEnsemble, ";");
			auteurGenApiService.l();
			auteurGenApiService.l("import ", classePartsSiteContexte.nomCanonique, ";");
//			auteurGenApiService.l("import ", classeNomEnsemble, ".", classeNomSimple, "ApiServiceVertxEBProxy;");
			auteurGenApiService.l("import io.vertx.codegen.annotations.ProxyGen;");
			auteurGenApiService.l("import io.vertx.ext.web.api.generator.WebApiServiceGen;");
			auteurGenApiService.l("import io.vertx.core.AsyncResult;");
			auteurGenApiService.l("import io.vertx.core.Handler;");
			auteurGenApiService.l("import io.vertx.core.Vertx;");
			auteurGenApiService.l("import io.vertx.core.json.JsonObject;");
			auteurGenApiService.l("import io.vertx.core.json.JsonArray;");
			auteurGenApiService.l("import io.vertx.ext.web.api.OperationRequest;");
			auteurGenApiService.l("import io.vertx.ext.web.api.OperationResponse;");
			auteurGenApiService.l();
			auteurGenApiService.l("@WebApiServiceGen");
			auteurGenApiService.l("@ProxyGen");
			auteurGenApiService.s("public interface ", classeNomSimpleGenApiService, " {");
			auteurGenApiService.l();
			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");
			auteurGenApiService.tl(1, "static ", classeNomSimpleGenApiService, " creer(SiteContexte siteContexte, Vertx vertx) {");
			auteurGenApiService.tl(2, "return new ", classeNomSimpleApiServiceImpl, "(siteContexte);");
			auteurGenApiService.tl(1, "}");
			auteurGenApiService.l();
			auteurGenApiService.tl(1, "// Une méthode d'usine pour créer une instance et un proxy. ");
			auteurGenApiService.tl(1, "static ", classeNomSimpleGenApiService, " creerProxy(Vertx vertx, String addresse) {");
			auteurGenApiService.tl(2, "return new ", classeNomSimpleGenApiService, "VertxEBProxy(vertx, addresse);");
			auteurGenApiService.tl(1, "}");
			auteurGenApiService.l();
			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_frFR_stored_string");

				auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "(");
				if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
					auteurGenApiService.s("JsonObject document, ");
				auteurGenApiService.l("OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);");
			}
			auteurGenApiService.tl(0, "}");

			auteurGenApiService.flushClose();
		}
	}

	/**
	 * var.enUS: writeApiServiceImpl
	 * param1.var.enUS: languageName
	 * 
	 * r: auteurApiServiceImpl
	 * r.enUS: writerApiServiceImpl
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: classeParts
	 * r.enUS: classParts
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * 
	 **/
	public void ecrireApiServiceImpl(String langueNom) throws Exception {
		if(auteurApiServiceImpl != null) {
			auteurApiServiceImpl.l("package ", classeNomEnsemble, ";");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.l("import ", classePartsSiteContexte.nomCanonique, ";");
//			auteurGenApiService.l("import ", classeNomEnsemble, ".", classeNomSimple, "ApiServiceVertxEBProxy;");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.l("public class ", classeNomSimpleApiServiceImpl, " extends ", classeNomSimpleGenApiServiceImpl, " {");
			auteurApiServiceImpl.l();
			auteurApiServiceImpl.tl(1, "public ", classeNomSimpleApiServiceImpl, "(SiteContexte siteContexte) {");
			auteurApiServiceImpl.tl(2, "super(siteContexte);");
			auteurApiServiceImpl.tl(1, "}");
			auteurApiServiceImpl.l("}");

			auteurApiServiceImpl.flushClose();
		}
	}

	/**
	 * var.enUS: writeGenApiServiceImpl
	 * param1.var.enUS: languageName
	 * 
	 * r: classeCheminGenApiServiceImpl
	 * r.enUS: classPathGenApiServiceImpl
	 * r: classeCheminApiServiceImpl
	 * r.enUS: classPathApiServiceImpl
	 * r: classeCheminGenApiService
	 * r.enUS: classPathGenApiService
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
	 * r: classeRolesTrouves
	 * r.enUS: classRolesFound
	 * r: classeRoles
	 * r.enUS: classRoles
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
	 * 
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
	 * 
	 */ 
	public void ecrireGenApiServiceImpl(String langueNom) throws Exception {

		if(auteurGenApiServiceImpl != null) {
			o = auteurGenApiServiceImpl;
	
			l("package ", classeNomEnsemble, ";");
			l();
			auteurGenApiServiceImpl.l("import ", classePartsToutEcrivain.nomCanonique, ";");
			if(classeImportationsGenApi.size() > 0) { 
				for(String classeImportation : classeImportationsGenApi) {
					l("import ", classeImportation, ";");
				}
				l();
			}
	
			tl(0, "");
			ecrireCommentaire(classeCommentaire, 0); 
			s("public class ", classeNomSimpleGenApiServiceImpl);
			s(" implements ", classeNomSimpleGenApiService);
			l(" {");
			l();
			tl(1, "private static final Logger LOGGER = LoggerFactory.getLogger(", classeNomSimpleGenApiServiceImpl, ".class);");
			l();
			tl(1, "private static final String SERVICE_ADDRESS = \"", classeNomSimpleApiServiceImpl, "\";");
			l();
			tl(1, "protected SiteContexte siteContexte;");
			l();
			tl(1, "public ", classeNomSimpleGenApiServiceImpl, "(SiteContexte siteContexte) {");
			tl(2, "this.siteContexte = siteContexte;");
			tl(2, classeNomSimpleGenApiService, " service = ", classeNomSimpleGenApiService, ".creerProxy(siteContexte.getVertx(), SERVICE_ADDRESS);");
			tl(1, "}");

			for(String classeApiMethode : classeApiMethodes) {
				String classePageNomCanoniqueMethode = (String)classeDoc.get("classePageNomCanonique" + classeApiMethode + "_frFR_stored_string");
				String classePageNomSimpleMethode = (String)classeDoc.get("classePageNomSimple" + classeApiMethode + "_frFR_stored_string");
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_frFR_stored_string");
				String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_frFR_stored_string");
				String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia" + classeApiMethode + "_stored_string");
				l();
				tl(1, "// ", classeApiMethode, " //");
				l();
				tl(1, "@Override");
				t(1, "public void ", classeApiOperationIdMethode, "(");
				if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
					s("JsonObject document, ");
				l("OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
				tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete);");

				if(classeApiMethode.contains("POST")) {
					tl(2, "Future<OperationResponse> etapesFutures = sql", classeNomSimple, "(requeteSite).compose(a -> ");
					tl(3, "creer", classeApiMethode, classeNomSimple, "(requeteSite).compose(", StringUtils.uncapitalize(classeNomSimple), " -> ");
					tl(4, "sql", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ").compose(c -> ");
					tl(5, "definir", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ").compose(d -> ");
					tl(6, "attribuer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ").compose(e -> ");
					tl(7, "indexer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ").compose(f -> ");
					tl(8, "reponse200", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ")");
					tl(7, ")");
					tl(6, ")");
					tl(5, ")");
					tl(4, ")");
					tl(3, ")");
					tl(2, ");");
				}
				else if(classeApiMethode.contains("PATCH")) {
					tl(2, "Future<OperationResponse> etapesFutures = sql", classeNomSimple, "(requeteSite).compose(a -> ");
					tl(3, "recherche", classeNomSimple, "(requeteSite).compose(liste", classeNomSimple, "-> ");
					tl(4, "liste", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ")");
					tl(3, ")");
					tl(2, ");");
				}
				else if(classeApiMethode.contains("Recherche")) {
					tl(2, "Future<OperationResponse> etapesFutures = recherche", classeNomSimple, "(requeteSite).compose(liste", classeNomSimple, " -> ");
					tl(3, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ")");
					tl(2, ");");
				}
				else if(classeApiMethode.contains("GET")) {
					tl(2, "Future<OperationResponse> etapesFutures = recherche", classeNomSimple, "(requeteSite).compose(liste", classeNomSimple, " -> ");
					tl(3, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ")");
					tl(2, ");");
				}
				else if(classeApiMethode.contains("PUT")) {
					tl(2, "Future<OperationResponse> etapesFutures = sql", classeNomSimple, "(requeteSite).compose(a -> ");
					tl(3, "remplacer", classeApiMethode, classeNomSimple, "(requeteSite).compose(", StringUtils.uncapitalize(classeNomSimple), " -> ");
					tl(4, "sql", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ").compose(c -> ");
					tl(5, "definir", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ").compose(d -> ");
					tl(6, "attribuer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ").compose(e -> ");
					tl(6, "indexer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ").compose(f -> ");
					tl(7, "reponse200", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ")");
					tl(7, ")");
					tl(6, ")");
					tl(5, ")");
					tl(4, ")");
					tl(3, ")");
					tl(2, ");");
				}
				else if(classeApiMethode.contains("DELETE")) {
					tl(2, "Future<OperationResponse> etapesFutures = sql", classeNomSimple, "(requeteSite).compose(a -> ");
					tl(3, "recherche", classeNomSimple, "(requeteSite).compose(", StringUtils.uncapitalize(classeNomSimple), " -> ");
					tl(4, "supprimer", classeApiMethode, classeNomSimple, "(requeteSite).compose(c -> ");
					tl(5, "reponse200", classeApiMethode, classeNomSimple, "(requeteSite)");
					tl(4, ")");
					tl(3, ")");
					tl(2, ");");
				}

				tl(2, "etapesFutures.setHandler(gestionnaireEvenements);");
				tl(1, "}");

				if(classeApiMethode.contains("Recherche")) {
//					l();
//					tl(1, "public Future<OperationResponse> listeRecherche", classeNomSimple, "(ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, ") {");
//					tl(2, "List<Future> futures = new ArrayList<>();");
//					tl(2, "liste", classeNomSimple, ".getList().forEach(o -> {");
//					tl(3, "futures.add(");
//					tl(4, "sqlPATCH", classeNomSimple, "(o).compose(");
//					tl(5, "b -> indexer", classeNomSimple, "(o)");
//					tl(4, ")");
//					tl(3, ");");
//					tl(2, "});");
//					tl(2, "Future<OperationResponse> future = CompositeFuture.all(futures).compose( a -> ");
//					tl(3, "reponse200Recherche", classeNomSimple, "(liste", classeNomSimple, ")");
//					tl(2, ");");
//					tl(2, "return future;");
//					tl(1, "}");
					l();
					tl(1, "public Future<ListeRecherche<", classeNomSimple, ">> recherche", classeNomSimple, "(RequeteSite requeteSite) {");
					tl(2, "OperationRequest operationRequete = requeteSite.getOperationRequete();");
					tl(2, "String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
					tl(2, "String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(", q(",\\s*"), ");");
					tl(2, "ListeRecherche<", classeNomSimple, "> listeRecherche = new ListeRecherche<", classeNomSimple, ">();");
					tl(2, "listeRecherche.setQuery(\"*:*\");");
					tl(2, "listeRecherche.setRows(1000000);");
					tl(2, "if(entiteListe != null)");
					tl(3, "listeRecherche.setFields(entiteListe);");
					tl(2, "listeRecherche.addSort(\"partNumero_indexed_int\", ORDER.asc);");
					tl(2, "operationRequete.getParams().getJsonObject(\"query\").forEach(paramRequete -> {");
					tl(3, "String entiteVar = null;");
					tl(3, "String valeurIndexe = null;");
					tl(3, "String varIndexe = null;");
					tl(3, "String valeurTri = null;");
					tl(3, "Integer rechercheDebut = null;");
					tl(3, "Integer rechercheNum = null;");
					tl(3, "String paramNom = paramRequete.getKey();");
					tl(3, "Object paramValeursObjet = paramRequete.getValue();");
					tl(3, "JsonArray paramObjets = paramValeursObjet instanceof JsonArray ? (JsonArray)paramValeursObjet : new JsonArray().add(paramValeursObjet);");
					l();
					tl(3, "for(Object paramObjet : paramObjets) {");
					tl(4, "switch(paramNom) {");
			
					tl(5, "case \"q\":");
					tl(6, "entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, \":\"));");
					tl(6, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, \":\"));");
					tl(6, "varIndexe = \"*\".equals(entiteVar) ? entiteVar : varIndexe", classeNomSimple, "(entiteVar);");
					tl(6, "listeRecherche.setQuery(varIndexe + \":\" + (\"*\".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));");
					tl(6, "break;");
			
					tl(5, "case \"fq\":");
					tl(6, "entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, \":\"));");
					tl(6, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, \":\"));");
					tl(6, "varIndexe = varIndexe", classeNomSimple, "(entiteVar);");
					tl(6, "listeRecherche.addFilterQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
					tl(6, "break;");
			
					tl(5, "case \"sort\":");
					tl(6, "entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, \" \"));");
					tl(6, "valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, \" \"));");
					tl(6, "varIndexe = varIndexe", classeNomSimple, "(entiteVar);");
					tl(6, "listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));");
					tl(6, "break;");
			
					tl(5, "case \"fl\":");
					tl(6, "entiteVar = StringUtils.trim((String)paramObjet);");
					tl(6, "varIndexe = varIndexe", classeNomSimple, "(entiteVar);");
					tl(6, "listeRecherche.addField(varIndexe);");
					tl(6, "break;");
			
					tl(5, "case \"start\":");
					tl(6, "rechercheDebut = (Integer)paramObjet;");
					tl(6, "listeRecherche.setStart(rechercheDebut);");
					tl(6, "break;");
			
					tl(5, "case \"rows\":");
					tl(6, "rechercheNum = (Integer)paramObjet;");
					tl(6, "listeRecherche.setRows(rechercheNum);");
					tl(6, "break;");
			
					tl(4, "}");
			
					tl(3, "}");
					tl(2, "});");
					tl(2, "listeRecherche.initLoinPourClasse(requeteSite);");
					tl(2, "return Future.succeededFuture(listeRecherche);");
					tl(1, "}");
				}
				if(classeApiMethode.contains("POST")) {
					l();
					tl(1, "public Future<", classeNomSimple, "> creer", classeApiMethode, classeNomSimple, "(RequeteSite requeteSite) {");
					tl(2, "Future<", classeNomSimple, "> future = Future.future();");
					tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(2, "String utilisateurId = requeteSite.getUtilisateurId();");
					l();
					tl(2, "connexionSql.queryWithParams(");
					tl(4, "SiteContexte.SQL_creer");
					tl(4, ", new JsonArray(Arrays.asList(", classeNomSimple, ".class.getCanonicalName(), utilisateurId))");
					tl(4, ", creerAsync");
					tl(2, "-> {");
					tl(3, "JsonArray patchLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);");
					tl(3, "Long ", classeVarClePrimaire, " = patchLigne.getLong(0);");
					tl(3, classeNomSimple, " o = new ", classeNomSimple, "();");
					tl(3, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
					tl(3, "future.complete(o);");
					tl(2, "});");
					tl(2, "return future;");
					tl(1, "}");
				}
				if(classeApiMethode.contains("PATCH")) {
					l();
					tl(1, "public Future<OperationResponse> liste", classeApiMethode, classeNomSimple, "(ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, ") {");
					tl(2, "List<Future> futures = new ArrayList<>();");
					tl(2, "liste", classeNomSimple, ".getList().forEach(o -> {");
					tl(3, "futures.add(");
					tl(4, "sql", classeApiMethode, classeNomSimple, "(o).compose(");
					tl(5, "b -> indexer", classeNomSimple, "(o)");
					tl(4, ")");
					tl(3, ");");
					tl(2, "});");
					tl(2, "Future<OperationResponse> future = CompositeFuture.all(futures).compose( a -> ");
					tl(3, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ")");
					tl(2, ");");
					tl(2, "return future;");
					tl(1, "}");
					l();
					tl(1, "public Future<Void> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o) {");
					tl(2, "Future<Void> future = Future.future();");
					tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
					tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(2, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
					tl(2, "RoutingContext contexteItineraire = requeteSite.getContexteItineraire();");
					tl(2, "JsonObject requeteJson = contexteItineraire.getBodyAsJson();");
					tl(2, "StringBuilder patchSql = new StringBuilder();");
					tl(2, "List<Object> patchSqlParams = new ArrayList<Object>();");
					tl(2, "Set<String> methodeNoms = requeteJson.fieldNames();");
					l();
					tl(2, "for(String methodeNom : methodeNoms) {");
					tl(3, "switch(methodeNom) {");
					s(wApiGenererPatch.toString());
					tl(3, "}");
					tl(2, "}");
					tl(2, "connexionSql.queryWithParams(");
					tl(4, "patchSql.toString()");
					tl(4, ", new JsonArray(patchSqlParams)");
					tl(4, ", patchAsync");
					tl(2, "-> {");
					tl(3, "future.complete();");
					tl(2, "});");
					tl(2, "return future;");
					tl(1, "}");
				}
				if(classeApiMethode.contains("PUT")) {
					l();
					tl(1, "public Future<", classeNomSimple, "> remplacer", classeApiMethode, classeNomSimple, "(RequeteSite requeteSite) {");
					tl(2, "Future<", classeNomSimple, "> future = Future.future();");
					tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(2, "String utilisateurId = requeteSite.getUtilisateurId();");
					tl(2, "Long pk = requeteSite.getRequetePk();");
					l();
					tl(2, "connexionSql.queryWithParams(");
					tl(4, "SiteContexte.SQL_vider");
					tl(4, ", new JsonArray(Arrays.asList(pk, ", classeNomSimple, ".class.getCanonicalName(), pk, pk, pk))");
					tl(4, ", remplacerAsync");
					tl(2, "-> {");
					tl(3, classeNomSimple, " o = new ", classeNomSimple, "();");
					tl(3, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
					tl(3, "future.complete(o);");
					tl(2, "});");
					tl(2, "return future;");
					tl(1, "}");
				}
				if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT")) {
					l();
					tl(1, "public Future<Void> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o) {");
					tl(2, "Future<Void> future = Future.future();");
					tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
					tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(2, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
					tl(2, "RoutingContext contexteItineraire = requeteSite.getContexteItineraire();");
					tl(2, "JsonObject jsonObject = contexteItineraire.getBodyAsJson();");
					tl(2, "StringBuilder postSql = new StringBuilder();");
					tl(2, "List<Object> postSqlParams = new ArrayList<Object>();");
					tl(2, "Set<String> entiteVars = jsonObject.fieldNames();");
					l();
					tl(2, "for(String entiteVar : entiteVars) {");
					tl(3, "switch(entiteVar) {");
					s(wApiGenererPost.toString());
					tl(3, "}");
					tl(2, "}");
					tl(2, "connexionSql.queryWithParams(");
					tl(4, "postSql.toString()");
					tl(4, ", new JsonArray(postSqlParams)");
					tl(4, ", postAsync");
					tl(2, "-> {");
					tl(3, "future.complete();");
					tl(2, "});");
					tl(2, "return future;");
					tl(1, "}");
				}
				if(classeApiMethode.contains("GET")) {
//					l();
//					tl(1, "public void genererGetDebut", classeNomSimple, "(RequeteSite requeteSite) {");
//					tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//					tl(2, "QueryResponse reponseRecherche = requeteSite.getReponseRecherche();");
//					tl(2, "reponseServeur.write(\"{\\n\");");
//					tl(2, "Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());");
//					tl(2, "Long millisTransmission = reponseRecherche.getElapsedTime();");
//					tl(2, "Long numCommence = reponseRecherche.getResults().getStart();");
//					tl(2, "Long numTrouve = reponseRecherche.getResults().getNumFound();");
//					tl(2, "Integer numRetourne = reponseRecherche.getResults().size();");
//					tl(2, "String tempsRecherche = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));");
//					tl(2, "String tempsTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
//					tl(2, "Exception exceptionRecherche = reponseRecherche.getException();");
//					l();
//					tl(2, "reponseServeur.write(\"\\t\\\"numCommence\\\": \");");
//					tl(2, "reponseServeur.write(numCommence.toString());");
//					l();
//					tl(2, "reponseServeur.write(\",\\n\\t\\\"numTrouve\\\": \");");
//					tl(2, "reponseServeur.write(numTrouve.toString());");
//					l();
//					tl(2, "reponseServeur.write(\",\\n\\t\\\"numRetourne\\\": \");");
//					tl(2, "reponseServeur.write(numRetourne.toString());");
//					l();
//					tl(2, "reponseServeur.write(\",\\n\\t\\\"tempsRecherche\\\": \\\"\");");
//					tl(2, "reponseServeur.write(tempsRecherche);");
//					tl(2, "reponseServeur.write(\"\\\"\");");
//					l();
//					tl(2, "reponseServeur.write(\",\\n\\t\\\"tempsTransmission\\\": \\\"\");");
//					tl(2, "reponseServeur.write(tempsTransmission);");
//					tl(2, "reponseServeur.write(\"\\\"\");");
//					l();
//					tl(2, "if(exceptionRecherche != null) {");
//					tl(3, "reponseServeur.write(\",\\n\\t\\\"exceptionRecherche\\\": \\\"\");");
//					tl(3, "reponseServeur.write(exceptionRecherche.getMessage());");
//					tl(3, "reponseServeur.write(\"\\\"\");");
//					tl(2, "}");
//					l();
//					tl(2, "reponseServeur.write(\",\\n\\t\\\"resultats\\\": [\\n\");");
//					tl(1, "}");
//					l();
//					tl(1, "public void genererGetIndividuel", classeNomSimple, "(ResultatRecherche resultatRecherche) {");
//					tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
//					tl(2, "SolrDocument documentSolr = resultatRecherche.getDocumentSolr();");
//					tl(2, "Long resultatIndice = resultatRecherche.getResultatIndice();");
//					tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//					tl(2, "reponseServeur.write(\"\\t\\t\");");
//					tl(2, "if(resultatIndice > 0)");
//					tl(3, "reponseServeur.write(\", \");");
//					tl(2, "reponseServeur.write(\"{\\n\");");
//					tl(2, "Collection<String> champNoms = documentSolr.getFieldNames();");
//					tl(2, "Integer j = 0;");
//					tl(2, "for(String champNomStocke : champNoms) {");
//					tl(3, "Collection<Object> entiteValeurs = documentSolr.getFieldValues(champNomStocke);");
//					tl(3, "j = genererGet", classeNomSimple, "(j, resultatRecherche, champNomStocke, entiteValeurs);");
//					tl(2, "}");
//					tl(2, "reponseServeur.write(\"\\t\\t}\\n\");");
//					tl(1, "}");
//					l();
//			//		tl(1, "public Integer genererGet", classeNomSimple, "(Integer j, PrintWriter ecrivain, String entiteVarStocke, Collection<Object> entiteValeurs) {");
//					tl(1, "public Integer genererGet", classeNomSimple, "(Integer j, ResultatRecherche resultatRecherche, String entiteVarStocke, Collection<Object> entiteValeurs) {");
//					tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
//					tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//					tl(2, "SolrDocument documentSolr = resultatRecherche.getDocumentSolr();;");
//					l();
//					tl(2, "if(documentSolr != null && !entiteValeurs.isEmpty()) {");
//					tl(3, "Object champValeur = entiteValeurs.iterator().next();");
//					tl(3, "Object entiteValeur;");
//					tl(3, "Integer entiteNumero = 0;");
//					l();
////					tl(3, "if(champValeur != null) {");
//					s(wApiGenererGet.toString());
////					tl(3, "}");
//					tl(2, "}");
//					tl(2, "return j;");
//					tl(1, "}");
//					l();
//					tl(1, "public void genererGetFin", classeNomSimple, "(RequeteSite requeteSite) {");
//					tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//			//		tl(2, "if(exceptionRecherche != null) {");
//			//		l();
//			//		tl(4, "reponseServeur.write(\"\\t\\t}\\n\");");
//			//		tl(3, "}");
//			//		tl(2, "}");
//					tl(2, "reponseServeur.write(\"\\t]\\n\");");
//					tl(2, "reponseServeur.write(\"}\\n\");");
//					tl(1, "}");
//			//		tl(1, "@Override protected void doGet(HttpServerRequest requeteServeur, HttpServerResponse reponseServeur) throws ServletException, IOException {");
				}
				if(classeApiMethode.contains("DELETE")) {
					l();
					tl(1, "public Future<Void> supprimer", classeApiMethode, classeNomSimple, "(RequeteSite requeteSite) {");
					tl(2, "Future<Void> future = Future.future();");
					tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(2, "String utilisateurId = requeteSite.getUtilisateurId();");
					tl(2, "Long pk = requeteSite.getRequetePk();");
					l();
					tl(2, "connexionSql.queryWithParams(");
					tl(4, "SiteContexte.SQL_supprimer");
					tl(4, ", new JsonArray(Arrays.asList(pk, ", classeNomSimple, ".class.getCanonicalName(), pk, pk, pk, pk))");
					tl(4, ", supprimerAsync");
					tl(2, "-> {");
					tl(3, "future.complete();");
					tl(2, "});");
					tl(2, "return future;");
					tl(1, "}");
				}
				l();
				t(1, "public Future<OperationResponse> reponse200", classeApiMethode, classeNomSimple, "(");

				if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT"))
					s(classeNomSimple, " o");
				else if(classeApiMethode.contains("DELETE"))
					s("RequeteSite requeteSite");
				else
					s("ListeRecherche<", classeNomSimple, "> liste", classeNomSimple);

				l(") {");

				tl(2, "Buffer buffer = Buffer.buffer();");

				if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT")) {
					tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
				}
				else if(classeApiMethode.contains("Recherche")) {
					tl(2, "RequeteSite requeteSite = liste", classeNomSimple, ".getRequeteSite_();");
				}
				else {
				}

				t(2, "ToutEcrivain w = ToutEcrivain.creer(");
				if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT"))
					s("o.getRequeteSite_()");
				else if(classeApiMethode.contains("DELETE"))
					s("requeteSite");
				else
					s("liste", classeNomSimple, ".getRequeteSite_()");
				l(", buffer);");


				if(classeApiMethode.contains("GET")) {
					tl(2, "SolrDocumentList documentsSolr = liste", classeNomSimple, ".getSolrDocumentList();");
					l();
				}
				if(classeApiMethode.contains("Recherche")) {
				}

				if(classeApiMethode.contains("Recherche") || classeApiMethode.contains("GET")) {
				}
				else if(classeApiMethode.contains("DELETE")) {
				}

				if(classeApiMethode.contains("Recherche")) {
					tl(2, "QueryResponse reponseRecherche = liste", classeNomSimple, ".getQueryResponse();");
					tl(2, "SolrDocumentList documentsSolr = liste", classeNomSimple, ".getSolrDocumentList();");
					tl(2, "Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());");
					tl(2, "Long millisTransmission = reponseRecherche.getElapsedTime();");
					tl(2, "Long numCommence = reponseRecherche.getResults().getStart();");
					tl(2, "Long numTrouve = reponseRecherche.getResults().getNumFound();");
					tl(2, "Integer numRetourne = reponseRecherche.getResults().size();");
					tl(2, "String tempsRecherche = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));");
					tl(2, "String tempsTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
					tl(2, "Exception exceptionRecherche = reponseRecherche.getException();");
					l();
					tl(2, "w.l(\"{\");");
					tl(2, "w.tl(1, ", q(q("numCommence"), ": "), ", numCommence);");
					tl(2, "w.tl(1, ", q(", ", q("numTrouve"), ": "), ", numTrouve);");
					tl(2, "w.tl(1, ", q(", ", q("numRetourne"), ": "), ", numRetourne);");
					tl(2, "w.tl(1, ", q(", ", q("tempsRecherche"), ": "), ", w.q(tempsRecherche));");
					tl(2, "w.tl(1, ", q(", ", q("tempsTransmission"), ": "), ", w.q(tempsTransmission));");
					tl(2, "w.tl(1, ", q(", ", q("liste"), ": ["), ");");
					tl(2, "for(int i = 0; i < documentsSolr.size(); i++) {");
					tl(3, "SolrDocument documentSolr = documentsSolr.get(i);");
					tl(3, "Object entiteValeur;");
					tl(3, "Integer entiteNumero = 0;");
//					tl(3, "List<String> champNoms = new ArrayList<>(documentSolr.getFieldNames());");
					l();
					tl(3, "w.t(2);");
					tl(3, "if(i > 0)");
					tl(4, "w.s(", q(", "), ");");
					tl(3, "w.s(", q("{"), ");");
//					tl(3, "for(int j = 0; j < champNoms.size(); j++) {");
//					tl(4, "String entiteVarStocke = champNoms.get(j);");
//					tl(4, "List<Object> entiteValeurs = new ArrayList<>(documentSolr.getFieldValues(entiteVarStocke));");
					s(wApiGenererGet.toString());
//					tl(3, "}");
					l();
					tl(3, "w.tl(2, ", q("}"), ");");
					tl(2, "}");
					tl(2, "w.tl(1, ", q("]"), ");");
					tl(2, "if(exceptionRecherche != null) {");
					tl(3, "w.tl(1, ", q(", ", q("exceptionRecherche"), ": "), ", w.q(exceptionRecherche.getMessage()));");
					tl(2, "}");
				}
				if(classeApiMethode.contains("GET")) {

//					if("text/html".equals(classeApiTypeMediaMethode)) {
					if(classePageNomCanoniqueMethode != null) {
						tl(2, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
//						tl(2, "page.setPageUri(", q(classeApiUriMethode), ");");
						tl(2, "RequeteSite requeteSite = liste", classeNomSimple, ".getRequeteSite_();");
						tl(2, "SolrDocument documentSolr = new SolrDocument();");
						tl(2, "documentSolr.setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
						tl(2, "page.setDocumentSolr(documentSolr);");
						tl(2, "page.initLoin", classePageNomSimpleMethode, "(requeteSite);");
					}
					else {
						tl(2, "if(documentsSolr.size() > 0) {");
						tl(3, "SolrDocument documentSolr = documentsSolr.get(0);");
						tl(3, "Object entiteValeur;");
						tl(3, "Integer entiteNumero = 0;");
						l();
						tl(3, "w.l(", q("{"), ");");
	//					tl(3, "for(int j = 0; j < champNoms.size(); j++) {");
	//					tl(4, "String entiteVarStocke = champNoms.get(j);");
	//					tl(4, "List<Object> entiteValeurs = new ArrayList<>(documentSolr.getFieldValues(entiteVarStocke));");
						s(wApiGenererGet.toString());
	//					tl(3, "}");
						l();
						tl(3, "w.l(", q("}"), ");");
						tl(2, "}");
					}
				}
				if(classeApiMethode.contains("Recherche")) {
					tl(2, "w.l(\"}\");");
				}
//					l();
//					tl(2, "w.l(\",\\n\\t\\\"resultats\\\": [\\n\");");
//					tl(1, "}");
//					l();
//					tl(1, "public void genererGetIndividuel", classeNomSimple, "(ResultatRecherche resultatRecherche) {");
//					tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
//					tl(2, "SolrDocument documentSolr = resultatRecherche.getDocumentSolr();");
//					tl(2, "Long resultatIndice = resultatRecherche.getResultatIndice();");
//					tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//					tl(2, "w.l(\"\\t\\t\");");
//					tl(2, "if(resultatIndice > 0)");
//					tl(3, "w.l(\", \");");
//					tl(2, "w.l(\"{\\n\");");
//					tl(2, "Collection<String> champNoms = documentSolr.getFieldNames();");
//					tl(2, "Integer j = 0;");
//					tl(2, "for(String champNomStocke : champNoms) {");
//					tl(3, "Collection<Object> entiteValeurs = documentSolr.getFieldValues(champNomStocke);");
//					tl(3, "j = genererGet", classeNomSimple, "(j, resultatRecherche, champNomStocke, entiteValeurs);");
//					tl(2, "}");
//					tl(2, "reponseServeur.write(\"\\t\\t}\\n\");");
//					tl(1, "}");
//					l();
//			//		tl(1, "public Integer genererGet", classeNomSimple, "(Integer j, PrintWriter ecrivain, String entiteVarStocke, Collection<Object> entiteValeurs) {");
//					tl(1, "public Integer genererGet", classeNomSimple, "(Integer j, ResultatRecherche resultatRecherche, String entiteVarStocke, Collection<Object> entiteValeurs) {");
//					tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
//					tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//					tl(2, "if(!entiteValeurs.isEmpty()) {");
//					tl(3, "Object champValeur = entiteValeurs.iterator().next();");
//					tl(3, "if(champValeur != null) {");
//					s(wApiGenererGet.toString());
//					tl(3, "}");
//					tl(2, "}");
//					tl(2, "return j;");

//				if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT"))
//					tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");

				tl(2, "return Future.succeededFuture(OperationResponse.completedWithJson(buffer));");
				tl(1, "}");
			}
	
			s(wApiEntites.toString());
//			l();
//			tl(1, "public void handleGet", classeNomSimple, "(SiteContexte siteContexte) {");
//	//		tl(2, "Router siteRouteur = siteContexte.getSiteRouteur();");
//			tl(2, "OpenAPI3RouterFactory usineRouteur = siteContexte.getUsineRouteur();");
//	
//	//		tl(2, "siteRouteur.get(\"", classeApiUri, "\").handler(rc -> {");
//			l();
//	//		tl(2, "HTTPRequestValidationHandler gestionnaireValidation = HTTPRequestValidationHandler.create();");
//	//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"q\", ParameterTypeValidator.createStringTypeValidator(\"[^:]+:.*\", \"*:*\"), false, false);");
//	//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"fq\", ParameterTypeValidator.createStringTypeValidator(\"[^:]+:.*\", null), false, false);");
//	//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"sort\", ParameterTypeValidator.createStringTypeValidator(\"[^:]+:.*\", null), false, false);");
//	//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"fl\", ParameterTypeValidator.createStringTypeValidator(\"[^:]+:.*\", null), false, false);");
//	//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"start\", ParameterTypeValidator.createIntegerTypeValidator(null, 0D, null, 0), false, false);");
//	//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"rows\", ParameterTypeValidator.createIntegerTypeValidator(null, 1D, null, 10), false, false);");
//	//		l();
//	//		tl(2, "siteRouteur.get(\"", classeApiUri, "\")");
//	//		tl(4, ".handler(gestionnaireValidation)");
//	//		tl(4, ".handler(rc -> {");
//			tl(2, "usineRouteur.addHandlerByOperationId(\"get", classeNomSimple, "\", contexteItineraire -> {");
//			Integer tBase = 0;
//			if(classeRolesTrouves && classeRoles != null) {
//				String requeteRole = classeRoles.get(0);
//				tBase = 6;
//				tl(3, "gestionnaireEvenements.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
//				tl(4, "try {");
//				tl(5, "if (authRes.result() == Boolean.TRUE) {");
//			}
//			else {
//				tBase = 4;
//				tl(3, "try {");
//			}
//			l();
//			tl(tBase, "contexteItineraire.response().putHeader(\"content-type\", \"application/json\").setChunked(true);");
//			tl(tBase, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte);");
//			tl(tBase, "SolrQuery rechercheSolr = requeteSite.getRechercheSolr();");
//			tl(tBase, "SolrDocumentList resultatsRecherche = requeteSite.getReponseRecherche().getResults();");
//			tl(tBase, "Integer rechercheLignes = rechercheSolr.getRows();");
//			l();
//			tl(tBase, "genererGetDebut", classeNomSimple, "(requeteSite);");
//			tl(tBase, "for(long i = resultatsRecherche.getStart(); i < resultatsRecherche.getNumFound(); i+=rechercheLignes) {");
//			tl(tBase + 1, "for(int j = 0; j < resultatsRecherche.size(); j++) {");
//			tl(tBase + 2, "long resultatIndice = i + j;");
//			tl(tBase + 2, "SolrDocument documentSolr = resultatsRecherche.get(j);");
//			tl(tBase + 2, "ResultatRecherche resultatRecherche = new ResultatRecherche();");
//			tl(tBase + 2, "resultatRecherche.setRequeteSite_(requeteSite);");
//			tl(tBase + 2, "resultatRecherche.setDocumentSolr(documentSolr);");
//			tl(tBase + 2, "resultatRecherche.setResultatIndice(resultatIndice);");
//			tl(tBase + 2, "genererGetIndividuel", classeNomSimple, "(resultatRecherche);");
//			tl(tBase + 1, "}");
//			tl(tBase, "}");
//			tl(tBase, "genererGetFin", classeNomSimple, "(requeteSite);");
//			tl(tBase, "requeteSite.getReponseServeur().end();");
//			if(classeRolesTrouves && classeRoles != null) {
//				tl(5, "}");
//				tl(5, "else {");
//				tl(6, "contexteItineraire.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end();");
//				tl(5, "}");
//				tl(4, "} catch(Exception e) {");
//				tl(5, "LOGGER.error(\"Error: \", e.getMessage());");
//				tl(5, "contexteItineraire.fail(e);");
//				tl(4, "}");
//				tl(3, "});");
//			}
//			else {
//				tl(3, "} catch(Exception e) {");
//				tl(4, "LOGGER.error(\"Error: \", e.getMessage());");
//				tl(4, "contexteItineraire.fail(e);");
//				tl(3, "}");
//			}
//			tl(2, "});");
//			tl(2, "usineRouteur.addFailureHandlerByOperationId(\"get", classeNomSimple, "\", contexteItineraire -> {");
//			tl(3, "Throwable failure = contexteItineraire.failure();");
//			tl(3, "if (failure instanceof ValidationException) {");
//			tl(4, "String validationErrorMessage = failure.getMessage();");
//			tl(4, "LOGGER.error(\"Error: \", validationErrorMessage);");
//			tl(4, "contexteItineraire.fail(failure);");
//			tl(3, "}");
//			tl(2, "});");
//			tl(1, "}");
	//
	//		//////////
	//		// POST //
	//		//////////
	//		l();
	////		tl(1, "protected void handlePost", classeNomSimple, "(SiteContexte siteContexte) {");
	//		tl(1, "protected void post", classeNomSimple, "(SiteContexte siteContexte) {");
	////		tl(2, "Router siteRouteur = siteContexte.getSiteRouteur();");
	//		tl(2, "OpenAPI3RouterFactory usineRouteur = siteContexte.getUsineRouteur();");
	//
	////		tl(2, "siteRouteur.get(\"", classeApiUri, "\").handler(rc -> {");
	//		tl(2, "usineRouteur.addHandlerByOperationId(\"post", classeNomSimple, "\", contexteItineraire -> {");
	//		tBase = 0;
	//		if(classeRolesTrouves && classeRoles != null) {
	//			String requeteRole = classeRoles.get(0);
	//			tBase = 6;
	//			tl(3, "gestionnaireEvenements.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
	//			tl(4, "try {");
	//			tl(5, "if (authRes.result() == Boolean.TRUE) {");
	//		}
	//		else {
	//			tBase = 4;
	//			tl(3, "try {");
	//		}
	//		tl(tBase + 0, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte);");
	//		tl(tBase + 0, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
	//		tl(tBase + 0, "QueryResponse reponseRecherche = requeteSite.getReponseRecherche();");
	//		tl(tBase + 0, "JsonObject requeteJson = contexteItineraire.getBodyAsJson();");
	//		tl(tBase + 0, "SQLClient clientSql = requeteSite.getSiteContexte_().getClientSql();");
	//		l();
	//		tl(tBase + 0, "clientSql.getConnection(resultatAsync -> {");
	//		tl(tBase + 1, "if(resultatAsync.succeeded()) {");
	//		tl(tBase + 2, "LocalDateTime modifie = java.time.LocalDateTime.now();");
	//		tl(tBase + 2, "String horodatageStr = Timestamp.valueOf(modifie).toString();");
	//		tl(tBase + 2, "String utilisateurId = requeteSite.getUtilisateurId();");
	//		tl(tBase + 2, "SQLConnection connexionSql = resultatAsync.result();");
	//		l();
	//		tl(tBase + 2, "connexionSql.queryWithParams(");
	//		tl(tBase + 4, "SiteContexte.SQL_creer");
	//		tl(tBase + 4, ", new JsonArray(Arrays.asList(VAL_nomCanonique", classeNomSimple, ", utilisateurId))");
	//		tl(tBase + 4, ", asyncCreer");
	//		tl(tBase + 4, "-> {");
	//		tl(tBase + 3, "if(asyncCreer.succeeded()) {");
	//		tl(tBase + 4, "List<Object> postSqlParams = Arrays.asList();");
	//		tl(tBase + 4, "JsonArray postLigne = asyncCreer.result().getResults().stream().findFirst().orElseGet(() -> null);");
	//		tl(tBase + 4, "Long postPk = postLigne.getLong(0);");
	//		tl(tBase + 4, "StringBuilder postSql = new StringBuilder();");
	//		tl(tBase + 4, "postSqlParams = new ArrayList<Object>();");
	//		tl(tBase + 4, "Set<String> entiteVars = requeteJson.fieldNames();");
	//		tl(tBase + 4, "for(String entiteVar : entiteVars) {");
	//		tl(tBase + 5, "switch(entiteVar) {");
	//		s(wApiGenererPost.toString());
	//		tl(tBase + 5, "}");
	//		tl(tBase + 4, "}");
	//		tl(tBase + 4, "connexionSql.queryWithParams(postSql.toString(), new JsonArray(postSqlParams), asyncParams -> {");
	//		tl(tBase + 5, "connexionSql.close();");
	//		tl(tBase + 5, "if(asyncParams.succeeded()) {");
	//		tl(tBase + 6, classeNomSimple, " o = new ", classeNomSimple, "();");
	////		tl(tBase + 6, "o.putPourClasse(requeteJson);");
	////		tl(tBase + 6, "o.sauvegarderPourClasse();");
	////		tl(tBase + 6, "o.sauvegardesPourClasse();");
	////		tl(tBase + 6, "o.initialiserLoinPourClasse();");
	////		tl(tBase + 6, "o.indexerPourClasse();");
	//		tl(tBase + 6, "requeteSite.setRequetePk(o.getPk());");
	//		l();
	//		tl(tBase + 5, "}");
	//		tl(tBase + 4, "});");
	//		tl(tBase + 3, "} else {");
	//		tl(tBase + 4, "connexionSql.close();");
	//		tl(tBase + 4, "contexteItineraire.fail(resultatAsync.cause());");
	//		tl(tBase + 3, "}");
	//		tl(tBase + 2, "});");
	//		tl(tBase + 1, "} else {");
	//		tl(tBase + 2, "LOGGER.error(\"Impossible d'ouvrir une connexion à la base de données. \", resultatAsync.cause());");
	//		tl(tBase + 2, "contexteItineraire.fail(resultatAsync.cause());");
	//		tl(tBase + 1, "}");
	//		tl(tBase + 0, "});");
	//		l();
	//		tl(tBase, "contexteItineraire.response().putHeader(\"content-type\", \"application/json\").setChunked(true);");
	//		l();
	////		tl(tBase, "genererPostDebut", classeNomSimple, "(requeteSite);");
	////		tl(tBase, classeNomSimple, " nouveau", classeNomSimple, " = new ", classeNomSimple, "();");
	////		tl(tBase, "nouveau", classeNomSimple, ".initLoin", classeNomSimple, "(requeteSite);");
	////		tl(tBase, "nouveau", classeNomSimple, ".peupler", classeNomSimple, "();");
	////		tl(tBase, "post", classeNomSimple, "();");
	////		tl(tBase, "genererPostFin", classeNomSimple, "(requeteSite);");
	//		tl(tBase, "requeteSite.getReponseServeur().end();");
	//		l();
	//		l();
	//		tl(tBase + 0, "reponseServeur.write(\"\\t]\\n\");");
	//		tl(tBase + 0, "reponseServeur.write(\"}\\n\");");
	//		if(classeRolesTrouves && classeRoles != null) {
	//			tl(5, "}");
	//			tl(5, "else {");
	//			tl(6, "contexteItineraire.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end();");
	//			tl(5, "}");
	//			tl(4, "} catch(Exception e) {");
	//			tl(5, "LOGGER.error(\"Error: \", e.getMessage());");
	//			tl(5, "contexteItineraire.fail(e);");
	//			tl(4, "}");
	//			tl(3, "});");
	//		}
	//		else {
	//			tl(3, "} catch(Exception e) {");
	//			tl(4, "LOGGER.error(\"Error: \", e.getMessage());");
	//			tl(4, "contexteItineraire.fail(e);");
	//			tl(3, "}");
	//		}
	//		tl(2, "});");
	//		tl(1, "}");
	
			////////////
			// Erreur //
			////////////
	//		l();
	//		tl(1, "public void genererErreur(RequeteSite requeteSite, Exception e) {");
	//		tl(2, "e.printStackTrace();");
	//		tl(2, "try {");
	//		tl(3, "MimeMessage message = new MimeMessage(requeteSite.SiteContexte_.sessionCourriel);");
	//		tl(3, "message.setFrom(new InternetAddress(requeteSite.configSite_.mailAdmin));");
	//		tl(3, "InternetAddress destinaires[] = new InternetAddress[1];");
	//		tl(3, "destinaires[0] = new InternetAddress(requeteSite.configSite_.mailAdmin);");
	//		tl(3, "message.setRecipients(Message.RecipientType.TO, destinaires);");
	//		tl(3, "String nomDomaine = requeteSite.configSite_.nomDomaine;");
	//		tl(3, "String sujet = nomDomaine + \" erreur \" + \" \" + requeteSite.utilisateurNom + \" \" + requeteSite.requeteServeur.getRequestURI();");
	//		tl(3, "String corps = ExceptionUtils.getStackTrace(e);");
	//		tl(3, "message.setSubject(sujet);");
	//		tl(3, "message.setContent(corps, \"text/plain\");");
	//		tl(3, "Transport.send(message);");
	//		tl(3, "String s = e.getMessage();");
	//		tl(3, "requeteSite.getReponseServeur().sendError(500, s);");
	//		tl(2, "} catch(Exception e2) {");
	//		tl(3, "e.printStackTrace();");
	//		tl(2, "}");
	//		tl(1, "}");
			l();
			tl(1, "public String varIndexe", classeNomSimple, "(String entiteVar) {");
			tl(2, "switch(entiteVar) {");
			s(wApiGet.toString());
			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" n'est pas une entité indexé. \", entiteVar));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public Future<Void> sql", classeNomSimple, "(RequeteSite requeteSite) {");
			tl(2, "Future<Void> future = Future.future();");
			tl(2, "SQLClient clientSql = requeteSite.getSiteContexte_().getClientSql();");
			l();
			tl(2, "clientSql.getConnection(sqlAsync -> {");
			tl(3, "if(sqlAsync.succeeded()) {");
			tl(4, "requeteSite.setConnexionSql(sqlAsync.result());");
			tl(4, "future.complete();");
			tl(3, "}");
			tl(2, "});");
			tl(2, "return future;");
			tl(1, "}");
			l();
			tl(1, "// Partagé //");
			l();
	//		tl(1, "public RequeteSite genererRequeteSitePour", classeNomSimple, "(SiteContexte siteContexte, RoutingContext contexteItineraire) {");
			tl(1, "public RequeteSite genererRequeteSitePour", classeNomSimple, "(SiteContexte siteContexte, OperationRequest operationRequete) {");
			tl(2, "Vertx vertx = siteContexte.getVertx();");
			tl(2, "RequeteSite requeteSite = new RequeteSite();");
			tl(2, "requeteSite.setVertx(vertx);");
	//		tl(2, "requeteSite.setContexteItineraire(contexteItineraire);");
			tl(2, "requeteSite.setSiteContexte_(siteContexte);");
			tl(2, "requeteSite.setOperationRequete(operationRequete);");
			tl(2, "requeteSite.initLoinRequeteSite(requeteSite);");
			l();
	
			tl(2, "UtilisateurSite utilisateurSite = new UtilisateurSite();");
			tl(2, "utilisateurSite.initLoinUtilisateurSite(requeteSite);");
			tl(2, "requeteSite.setUtilisateurSite(utilisateurSite);");
			tl(2, "utilisateurSite.setRequeteSite_(requeteSite);");
	
			tl(2, "return requeteSite;");
			tl(1, "}");
			l();
			tl(1, "public Future<Void> definir", classeNomSimple, "(", classeNomSimple, " o) {");
			tl(2, "Future<Void> future = Future.future();");
			tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
			tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
			tl(2, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			tl(2, "connexionSql.queryWithParams(");
			tl(4, "SiteContexte.SQL_definir");
			tl(4, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, "))");
			tl(4, ", definirAsync");
			tl(2, "-> {");
			tl(3, "for(JsonArray definition : definirAsync.result().getResults()) {");
			tl(4, "o.definirPourClasse(definition.getString(0), definition.getString(1));");
			tl(3, "}");
			tl(3, "future.complete();");
			tl(2, "});");
			tl(2, "return future;");
			tl(1, "}");
			l();
			tl(1, "public Future<Void> attribuer", classeNomSimple, "(", classeNomSimple, " o) {");
			tl(2, "Future<Void> future = Future.future();");
			tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
			tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
			tl(2, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			tl(2, "connexionSql.queryWithParams(");
			tl(4, "SiteContexte.SQL_attribuer");
			tl(4, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, "))");
			tl(4, ", attribuerAsync");
			tl(2, "-> {");
			tl(3, "for(JsonArray definition : attribuerAsync.result().getResults()) {");
			tl(4, "o.attribuerPourClasse(definition.getString(0), definition.getString(1));");
			tl(3, "}");
			tl(3, "future.complete();");
			tl(2, "});");
			tl(2, "return future;");
			tl(1, "}");
			l();
			tl(1, "public Future<Void> indexer", classeNomSimple, "(", classeNomSimple, " o) {");
			tl(2, "Future<Void> future = Future.future();");
			tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
			tl(2, "try {");
			tl(3, "o.initLoinPourClasse(requeteSite);");
			tl(3, "o.indexerPourClasse();");
			tl(3, "future.complete();");
			tl(2, "} catch(Exception e) {");
			tl(3, "requeteSite.getConnexionSql().close();");
			tl(3, "future.fail(e.getCause());");
			tl(2, "}");
			tl(2, "return future;");
			tl(1, "}");
	
			tl(0, "}");

			auteurGenApiServiceImpl.flushClose();
			System.out.println("Ecrire: " + classeCheminGenApiServiceImpl); 
		}
	}
}
