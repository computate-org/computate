package org.computate.frFR.java; 

import java.util.List;

import org.apache.commons.lang3.StringUtils;

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
	 * Var.enUS: classApiMethods
	 */
	protected List<String> classeApiMethodes;

	/**
	 * Var.enUS: classEntityVars
	 */ 
	protected List<String> classeEntiteVars;

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
	 * Param1.var.enUS: languageName
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
					auteurGenApiService.s("JsonObject objetJson, ");
				auteurGenApiService.l("OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);");
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
	 * Var.enUS: writeGenApiServiceImpl
	 * Param1.var.enUS: languageName
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
					s("JsonObject objetJson, ");
				l("OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");

				if(classeApiMethode.contains("POST")) {
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete, objetJson);");
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
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete, objetJson);");
					tl(2, "Future<OperationResponse> etapesFutures = sql", classeNomSimple, "(requeteSite).compose(a -> ");
					tl(3, "recherche", classeNomSimple, "(requeteSite).compose(liste", classeNomSimple, "-> ");
					tl(4, "liste", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ")");
					tl(3, ")");
					tl(2, ");");
				}
				else if(classeApiMethode.contains("Recherche")) {
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete);");
					tl(2, "Future<OperationResponse> etapesFutures = ", classeApiOperationIdMethode, "(requeteSite).compose(liste", classeNomSimple, " -> ");
					tl(3, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ")");
					tl(2, ");");
				}
				else if(classeApiMethode.contains("GET")) {
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete);");
					tl(2, "Future<OperationResponse> etapesFutures = recherche", classeNomSimple, "(requeteSite).compose(liste", classeNomSimple, " -> ");
					tl(3, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ")");
					tl(2, ");");
				}
				else if(classeApiMethode.contains("PUT")) {
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete, objetJson);");
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
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete);");
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
					tl(1, "public Future<ListeRecherche<", classeNomSimple, ">> ", classeApiOperationIdMethode, "(RequeteSite requeteSite) {");
					tl(2, "try {");
					tl(3, "OperationRequest operationRequete = requeteSite.getOperationRequete();");
					tl(3, "String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
					tl(3, "String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(", q(",\\s*"), ");");
					tl(3, "ListeRecherche<", classeNomSimple, "> listeRecherche = new ListeRecherche<", classeNomSimple, ">();");
					tl(3, "listeRecherche.setQuery(\"*:*\");");
					tl(3, "listeRecherche.setC(", classeNomSimple, ".class);");
					tl(3, "listeRecherche.setRows(1000000);");
					tl(3, "if(entiteListe != null)");
					tl(3, "listeRecherche.setFields(entiteListe);");
					tl(3, "listeRecherche.addSort(\"partNumero_indexed_int\", ORDER.asc);");
					tl(3, "operationRequete.getParams().getJsonObject(\"query\").forEach(paramRequete -> {");
					tl(4, "String entiteVar = null;");
					tl(4, "String valeurIndexe = null;");
					tl(4, "String varIndexe = null;");
					tl(4, "String valeurTri = null;");
					tl(4, "Integer rechercheDebut = null;");
					tl(4, "Integer rechercheNum = null;");
					tl(4, "String paramNom = paramRequete.getKey();");
					tl(4, "Object paramValeursObjet = paramRequete.getValue();");
					tl(4, "JsonArray paramObjets = paramValeursObjet instanceof JsonArray ? (JsonArray)paramValeursObjet : new JsonArray().add(paramValeursObjet);");
					l();
					tl(4, "for(Object paramObjet : paramObjets) {");
					tl(5, "switch(paramNom) {");
			
					tl(6, "case \"q\":");
					tl(7, "entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, \":\"));");
					tl(7, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, \":\"));");
					tl(7, "varIndexe = \"*\".equals(entiteVar) ? entiteVar : varIndexe", classeNomSimple, "(entiteVar);");
					tl(7, "listeRecherche.setQuery(varIndexe + \":\" + (\"*\".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));");
					tl(7, "break;");
			
					tl(6, "case \"fq\":");
					tl(7, "entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, \":\"));");
					tl(7, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, \":\"));");
					tl(7, "varIndexe = varIndexe", classeNomSimple, "(entiteVar);");
					tl(7, "listeRecherche.addFilterQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
					tl(7, "break;");
			
					tl(6, "case \"sort\":");
					tl(7, "entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, \" \"));");
					tl(7, "valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, \" \"));");
					tl(7, "varIndexe = varIndexe", classeNomSimple, "(entiteVar);");
					tl(7, "listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));");
					tl(7, "break;");
			
					tl(6, "case \"fl\":");
					tl(7, "entiteVar = StringUtils.trim((String)paramObjet);");
					tl(7, "varIndexe = varIndexe", classeNomSimple, "(entiteVar);");
					tl(7, "listeRecherche.addField(varIndexe);");
					tl(7, "break;");
			
					tl(6, "case \"start\":");
					tl(7, "rechercheDebut = (Integer)paramObjet;");
					tl(7, "listeRecherche.setStart(rechercheDebut);");
					tl(7, "break;");
			
					tl(6, "case \"rows\":");
					tl(7, "rechercheNum = (Integer)paramObjet;");
					tl(7, "listeRecherche.setRows(rechercheNum);");
					tl(7, "break;");
			
					tl(5, "}");
			
					tl(4, "}");
					tl(3, "});");
					tl(3, "listeRecherche.initLoinPourClasse(requeteSite);");
					tl(3, "return Future.succeededFuture(listeRecherche);");
					tl(2, "} catch(Exception e) {");
					tl(3, "ExceptionUtils.printRootCauseStackTrace(e);");
					tl(3, "return Future.failedFuture(e);");
					tl(2, "}");
					tl(1, "}");
				}
				if(classeApiMethode.contains("POST")) {
					l();
					tl(1, "public Future<", classeNomSimple, "> creer", classeApiMethode, classeNomSimple, "(RequeteSite requeteSite) {");
					tl(2, "Future<", classeNomSimple, "> future = Future.future();");
					tl(2, "try {");
					tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(3, "String utilisateurId = requeteSite.getUtilisateurId();");
					l();
					tl(3, "connexionSql.queryWithParams(");
					tl(5, "SiteContexte.SQL_creer");
					tl(5, ", new JsonArray(Arrays.asList(", classeNomSimple, ".class.getCanonicalName(), utilisateurId))");
					tl(5, ", creerAsync");
					tl(3, "-> {");
					tl(4, "JsonArray patchLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);");
					tl(4, "Long ", classeVarClePrimaire, " = patchLigne.getLong(0);");
					tl(4, classeNomSimple, " o = new ", classeNomSimple, "();");
					tl(4, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
					tl(4, "o.initLoin", classeNomSimple, "(requeteSite);");
					tl(4, "future.complete(o);");
					tl(3, "});");
					tl(3, "return future;");
					tl(2, "} catch(Exception e) {");
					tl(3, "ExceptionUtils.printRootCauseStackTrace(e);");
					tl(3, "return Future.failedFuture(e);");
					tl(2, "}");
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
					tl(2, "try {");
					tl(3, "RequeteSite requeteSite = o.getRequeteSite_();");
					tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
					tl(3, "JsonObject requeteJson = requeteSite.getObjetJson();");
					tl(3, "StringBuilder patchSql = new StringBuilder();");
					tl(3, "List<Object> patchSqlParams = new ArrayList<Object>();");
					tl(3, "Set<String> methodeNoms = requeteJson.fieldNames();");
					l();
					tl(3, "for(String methodeNom : methodeNoms) {");
					tl(4, "switch(methodeNom) {");
					s(wApiGenererPatch.toString());
					tl(4, "}");
					tl(3, "}");
					tl(3, "connexionSql.queryWithParams(");
					tl(5, "patchSql.toString()");
					tl(5, ", new JsonArray(patchSqlParams)");
					tl(5, ", patchAsync");
					tl(3, "-> {");
					tl(4, "future.complete();");
					tl(3, "});");
					tl(3, "return future;");
					tl(2, "} catch(Exception e) {");
					tl(3, "ExceptionUtils.printRootCauseStackTrace(e);");
					tl(3, "return Future.failedFuture(e);");
					tl(2, "}");
					tl(1, "}");
				}
				if(classeApiMethode.contains("PUT")) {
					l();
					tl(1, "public Future<", classeNomSimple, "> remplacer", classeApiMethode, classeNomSimple, "(RequeteSite requeteSite) {");
					tl(2, "Future<", classeNomSimple, "> future = Future.future();");
					tl(2, "try {");
					tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(3, "String utilisateurId = requeteSite.getUtilisateurId();");
					tl(3, "Long pk = requeteSite.getRequetePk();");
					l();
					tl(3, "connexionSql.queryWithParams(");
					tl(5, "SiteContexte.SQL_vider");
					tl(5, ", new JsonArray(Arrays.asList(pk, ", classeNomSimple, ".class.getCanonicalName(), pk, pk, pk))");
					tl(5, ", remplacerAsync");
					tl(3, "-> {");
					tl(4, classeNomSimple, " o = new ", classeNomSimple, "();");
					tl(4, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
					tl(4, "future.complete(o);");
					tl(3, "});");
					tl(3, "return future;");
					tl(2, "} catch(Exception e) {");
					tl(3, "ExceptionUtils.printRootCauseStackTrace(e);");
					tl(3, "return Future.failedFuture(e);");
					tl(2, "}");
					tl(1, "}");
				}
				if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT")) {
					l();
					tl(1, "public Future<Void> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o) {");
					tl(2, "Future<Void> future = Future.future();");
					tl(2, "try {");
					tl(3, "RequeteSite requeteSite = o.getRequeteSite_();");
					tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
					tl(3, "JsonObject jsonObject = requeteSite.getObjetJson();");
					tl(3, "StringBuilder postSql = new StringBuilder();");
					tl(3, "List<Object> postSqlParams = new ArrayList<Object>();");
					l();
					tl(3, "if(jsonObject != null) {");
					tl(4, "Set<String> entiteVars = jsonObject.fieldNames();");
					tl(4, "for(String entiteVar : entiteVars) {");
					tl(5, "switch(entiteVar) {");
					s(wApiGenererPost.toString());
					tl(5, "}");
					tl(4, "}");
					tl(3, "}");
					tl(3, "connexionSql.queryWithParams(");
					tl(5, "postSql.toString()");
					tl(5, ", new JsonArray(postSqlParams)");
					tl(5, ", postAsync");
					tl(3, "-> {");
					tl(4, "future.complete();");
					tl(3, "});");
					tl(3, "return future;");
					tl(2, "} catch(Exception e) {");
					tl(3, "ExceptionUtils.printRootCauseStackTrace(e);");
					tl(3, "return Future.failedFuture(e);");
					tl(2, "}");
					tl(1, "}");
				}
				if(classeApiMethode.contains("GET")) {
				}
				if(classeApiMethode.contains("DELETE")) {
					l();
					tl(1, "public Future<Void> supprimer", classeApiMethode, classeNomSimple, "(RequeteSite requeteSite) {");
					tl(2, "Future<Void> future = Future.future();");
					tl(2, "try {");
					tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(3, "String utilisateurId = requeteSite.getUtilisateurId();");
					tl(3, "Long pk = requeteSite.getRequetePk();");
					l();
					tl(3, "connexionSql.queryWithParams(");
					tl(5, "SiteContexte.SQL_supprimer");
					tl(5, ", new JsonArray(Arrays.asList(pk, ", classeNomSimple, ".class.getCanonicalName(), pk, pk, pk, pk))");
					tl(5, ", supprimerAsync");
					tl(3, "-> {");
					tl(4, "future.complete();");
					tl(3, "});");
					tl(3, "return future;");
					tl(2, "} catch(Exception e) {");
					tl(3, "ExceptionUtils.printRootCauseStackTrace(e);");
					tl(3, "return Future.failedFuture(e);");
					tl(2, "}");
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

				tl(2, "try {");
				tl(3, "Buffer buffer = Buffer.buffer();");

				if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT")) {
					tl(3, "RequeteSite requeteSite = o.getRequeteSite_();");
				}
				else if(classeApiMethode.contains("Recherche")) {
					tl(3, "RequeteSite requeteSite = liste", classeNomSimple, ".getRequeteSite_();");
				}
				else {
				}

				t(3, "ToutEcrivain w = ToutEcrivain.creer(");
				if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT"))
					s("o.getRequeteSite_()");
				else if(classeApiMethode.contains("DELETE"))
					s("requeteSite");
				else
					s("liste", classeNomSimple, ".getRequeteSite_()");
				l(", buffer);");


				if(classeApiMethode.contains("GET")) {
					tl(3, "SolrDocumentList documentsSolr = liste", classeNomSimple, ".getSolrDocumentList();");
					l();
				}
				if(classeApiMethode.contains("Recherche")) {
				}

				if(classeApiMethode.contains("Recherche") || classeApiMethode.contains("GET")) {
				}
				else if(classeApiMethode.contains("DELETE")) {
				}

				if(classeApiMethode.contains("Recherche")) {
					if(classePageNomCanoniqueMethode != null) {
						tl(3, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
						tl(3, "page.setPageUrl(\"", siteUrlBase, classeApiUri, "\");");
						tl(3, "SolrDocument pageDocumentSolr = new SolrDocument();");
						l();
						tl(3, "pageDocumentSolr.setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
						tl(3, "page.setPageDocumentSolr(pageDocumentSolr);");
						tl(3, "page.setW(w);");
						tl(3, "page.setListe", classeNomSimple, "(liste", classeNomSimple, ");");
						tl(3, "page.initLoin", classePageNomSimpleMethode, "(requeteSite);");
						tl(3, "page.html();");
					}
					else {
						tl(3, "QueryResponse reponseRecherche = liste", classeNomSimple, ".getQueryResponse();");
						tl(3, "SolrDocumentList documentsSolr = liste", classeNomSimple, ".getSolrDocumentList();");
						tl(3, "Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());");
						tl(3, "Long millisTransmission = reponseRecherche.getElapsedTime();");
						tl(3, "Long numCommence = reponseRecherche.getResults().getStart();");
						tl(3, "Long numTrouve = reponseRecherche.getResults().getNumFound();");
						tl(3, "Integer numRetourne = reponseRecherche.getResults().size();");
						tl(3, "String tempsRecherche = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));");
						tl(3, "String tempsTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
						tl(3, "Exception exceptionRecherche = reponseRecherche.getException();");
						l();
						tl(3, "w.l(\"{\");");
						tl(3, "w.tl(1, ", q(q("numCommence"), ": "), ", numCommence);");
						tl(3, "w.tl(1, ", q(", ", q("numTrouve"), ": "), ", numTrouve);");
						tl(3, "w.tl(1, ", q(", ", q("numRetourne"), ": "), ", numRetourne);");
						tl(3, "w.tl(1, ", q(", ", q("tempsRecherche"), ": "), ", w.q(tempsRecherche));");
						tl(3, "w.tl(1, ", q(", ", q("tempsTransmission"), ": "), ", w.q(tempsTransmission));");
						tl(3, "w.tl(1, ", q(", ", q("liste"), ": ["), ");");
						tl(3, "for(int i = 0; i < documentsSolr.size(); i++) {");
						tl(4, "SolrDocument documentSolr = documentsSolr.get(i);");
						tl(4, "Object entiteValeur;");
						tl(4, "Integer entiteNumero = 0;");
	//					tl(4, "List<String> champNoms = new ArrayList<>(documentSolr.getFieldNames());");
						l();
						tl(4, "w.t(2);");
						tl(4, "if(i > 0)");
						tl(5, "w.s(", q(", "), ");");
						tl(4, "w.s(", q("{"), ");");
	//					tl(4, "for(int j = 0; j < champNoms.size(); j++) {");
	//					tl(5, "String entiteVarStocke = champNoms.get(j);");
	//					tl(5, "List<Object> entiteValeurs = new ArrayList<>(documentSolr.getFieldValues(entiteVarStocke));");
						s(wApiGenererGet.toString());
	//					tl(4, "}");
						l();
						tl(4, "w.tl(2, ", q("}"), ");");
						tl(3, "}");
						tl(3, "w.tl(1, ", q("]"), ");");
						tl(3, "if(exceptionRecherche != null) {");
						tl(4, "w.tl(1, ", q(", ", q("exceptionRecherche"), ": "), ", w.q(exceptionRecherche.getMessage()));");
						tl(3, "}");
						tl(3, "w.l(\"}\");");
					}
				}
				if(classeApiMethode.contains("GET")) {
					if(classePageNomCanoniqueMethode != null) {
						tl(3, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
						tl(3, "page.setPageUrl(\"", siteUrlBase, classeApiUri, "\");");
						tl(3, "SolrDocument pageDocumentSolr = new SolrDocument();");
						tl(3, "RequeteSite requeteSite = liste", classeNomSimple, ".getRequeteSite_();");
						l();
						tl(3, "pageDocumentSolr.setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
						tl(3, "page.setPageDocumentSolr(pageDocumentSolr);");
						tl(3, "page.setW(w);");
						tl(3, "page.initLoin", classePageNomSimpleMethode, "(requeteSite);");
						tl(3, "page.html();");
					}
					else {
						tl(3, "if(documentsSolr.size() > 0) {");
						tl(4, "SolrDocument documentSolr = documentsSolr.get(0);");
						tl(4, "Object entiteValeur;");
						tl(4, "Integer entiteNumero = 0;");
						l();
						tl(4, "w.l(", q("{"), ");");
	//					tl(4, "for(int j = 0; j < champNoms.size(); j++) {");
	//					tl(5, "String entiteVarStocke = champNoms.get(j);");
	//					tl(5, "List<Object> entiteValeurs = new ArrayList<>(documentSolr.getFieldValues(entiteVarStocke));");
						s(wApiGenererGet.toString());
	//					tl(4, "}");
						l();
						tl(4, "w.l(", q("}"), ");");
						tl(3, "}");
					}
				}

				if((classeApiMethode.contains("GET") || classeApiMethode.contains("Recherche")) && classePageNomCanoniqueMethode != null) {
					tl(3, "return Future.succeededFuture(new OperationResponse(200, \"OK\", buffer, new CaseInsensitiveHeaders()));");
				}
				else {
					tl(3, "return Future.succeededFuture(OperationResponse.completedWithJson(buffer));");
				}

				tl(2, "} catch(Exception e) {");
				tl(3, "ExceptionUtils.printRootCauseStackTrace(e);");
				tl(3, "return Future.failedFuture(e);");
				tl(2, "}");
				tl(1, "}");
			}
	
			s(wApiEntites.toString());
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
			tl(2, "try {");
			tl(3, "SQLClient clientSql = requeteSite.getSiteContexte_().getClientSql();");
			l();
			tl(3, "clientSql.getConnection(sqlAsync -> {");
			tl(4, "if(sqlAsync.succeeded()) {");
			tl(5, "requeteSite.setConnexionSql(sqlAsync.result());");
			tl(5, "future.complete();");
			tl(4, "}");
			tl(3, "});");
			tl(3, "return future;");
			tl(2, "} catch(Exception e) {");
			tl(3, "ExceptionUtils.printRootCauseStackTrace(e);");
			tl(3, "return Future.failedFuture(e);");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "// Partagé //");
			l();
	//		tl(1, "public RequeteSite genererRequeteSitePour", classeNomSimple, "(SiteContexte siteContexte, RoutingContext contexteItineraire) {");
			tl(1, "public RequeteSite genererRequeteSitePour", classeNomSimple, "(SiteContexte siteContexte, OperationRequest operationRequete) {");
			tl(2, "return genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete, null);");
			tl(1, "}");
			l();
			tl(1, "public RequeteSite genererRequeteSitePour", classeNomSimple, "(SiteContexte siteContexte, OperationRequest operationRequete, JsonObject objetJson) {");
			tl(2, "Vertx vertx = siteContexte.getVertx();");
			tl(2, "RequeteSite requeteSite = new RequeteSite();");
			tl(2, "requeteSite.setObjetJson(objetJson);");
			tl(2, "requeteSite.setVertx(vertx);");
	//		tl(2, "requeteSite.setContexteItineraire(contexteItineraire);");
			tl(2, "requeteSite.setSiteContexte_(siteContexte);");
			tl(2, "requeteSite.setConfigSite_(siteContexte.getConfigSite());");
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
			tl(2, "try {");
			tl(3, "RequeteSite requeteSite = o.getRequeteSite_();");
			tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
			tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			tl(3, "connexionSql.queryWithParams(");
			tl(5, "SiteContexte.SQL_definir");
			tl(5, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, "))");
			tl(5, ", definirAsync");
			tl(3, "-> {");
			tl(4, "for(JsonArray definition : definirAsync.result().getResults()) {");
			tl(5, "o.definirPourClasse(definition.getString(0), definition.getString(1));");
			tl(4, "}");
			tl(4, "future.complete();");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "ExceptionUtils.printRootCauseStackTrace(e);");
			tl(3, "return Future.failedFuture(e);");
			tl(2, "}");
			tl(2, "return future;");
			tl(1, "}");
			l();
			tl(1, "public Future<Void> attribuer", classeNomSimple, "(", classeNomSimple, " o) {");
			tl(2, "Future<Void> future = Future.future();");
			tl(2, "try {");
			tl(3, "RequeteSite requeteSite = o.getRequeteSite_();");
			tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
			tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			tl(3, "connexionSql.queryWithParams(");
			tl(5, "SiteContexte.SQL_attribuer");
			tl(5, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, "))");
			tl(5, ", attribuerAsync");
			tl(3, "-> {");
			tl(4, "if(attribuerAsync.result() != null) {");
			tl(5, "for(JsonArray definition : attribuerAsync.result().getResults()) {");
			tl(6, "o.attribuerPourClasse(definition.getString(0), definition.getString(1));");
			tl(5, "}");
			tl(4, "}");
			tl(4, "future.complete();");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "ExceptionUtils.printRootCauseStackTrace(e);");
			tl(3, "return Future.failedFuture(e);");
			tl(2, "}");
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
			tl(3, "ExceptionUtils.printRootCauseStackTrace(e);");
			tl(3, "return Future.failedFuture(e);");
			tl(2, "}");
			tl(2, "return future;");
			tl(1, "}");
	
			tl(0, "}");

			auteurGenApiServiceImpl.flushClose();
			System.out.println("Ecrire: " + classeCheminGenApiServiceImpl); 
		}
	}
}
