package org.computate.frFR.java; 

import java.util.List;
import java.util.stream.Collectors;

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
	 * r: classePageNomCanoniqueMethode
	 * r.enUS: classPageCanonicalNameMethod
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
			auteurGenApiService.l("import io.vertx.serviceproxy.ServiceBinder;");
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
			auteurGenApiService.tl(1, "static void enregistrerService(SiteContexte siteContexte, Vertx vertx) {");
			auteurGenApiService.tl(2, "new ServiceBinder(vertx).setAddress(", q(classeNomSimple), ").register(", classeNomSimpleGenApiService, ".class, new ", classeNomSimpleApiServiceImpl, "(siteContexte));");
			auteurGenApiService.tl(1, "}");
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
				String classePageNomCanoniqueMethode = (String)classeDoc.get("classePageNomCanonique" + classeApiMethode + "_frFR_stored_string");

				if(classePageNomCanoniqueMethode != null) {
					auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "Id(");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						auteurGenApiService.s("JsonObject body, ");
					auteurGenApiService.l("OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);");
				}

				auteurGenApiService.t(1, "public void ", classeApiOperationIdMethode, "(");
				if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
					auteurGenApiService.s("JsonObject body, ");
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
	 * r: siteUrlBase
	 * r.enUS: siteBaseUrl
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * r: connexionSql
	 * r.enUS: sqlConnection
	 * r: reponseOperation
	 * r.enUS: operationResponse
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
				if(classePageNomCanoniqueMethode != null) {
					l();
					tl(1, "@Override");
					t(1, "public void ", classeApiOperationIdMethode, "Id(");
					if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
						s("JsonObject body, ");
					l("OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
					tl(2, classeApiOperationIdMethode, "(operationRequete, gestionnaireEvenements);");
					tl(1, "}");
				}
				l();
				tl(1, "@Override");
				t(1, "public void ", classeApiOperationIdMethode, "(");
				if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
					s("JsonObject body, ");
				l("OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");

				if(classeApiMethode.contains("POST")) {
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete, body);");
					tl(2, "sql", classeNomSimple, "(requeteSite, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "creer", classeApiMethode, classeNomSimple, "(requeteSite, b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, classeNomSimple, " ", StringUtils.uncapitalize(classeNomSimple), " = b.result();");
					tl(6, "sql", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", c -> {");
					tl(7, "if(c.succeeded()) {");
					tl(8, "definir", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", d -> {");
					tl(9, "if(d.succeeded()) {");
					tl(10, "attribuer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", e -> {");
					tl(11, "if(e.succeeded()) {");
					tl(12, "indexer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", f -> {");
					tl(13, "if(f.succeeded()) {");
					tl(14, "reponse200", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", g -> {");
					tl(15, "if(f.succeeded()) {");
					tl(16, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(16, "connexionSql.commit(h -> {");
					tl(17, "if(a.succeeded()) {");
					tl(18, "connexionSql.close(i -> {");
					tl(19, "if(a.succeeded()) {");
					tl(20, "gestionnaireEvenements.handle(Future.succeededFuture(g.result()));");
					tl(19, "} else {");
					tl(20, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, i);");
					tl(19, "}");
					tl(18, "});");
					tl(17, "} else {");
					tl(18, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, h);");
					tl(17, "}");
					tl(16, "});");
					tl(15, "} else {");
					tl(16, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, g);");
					tl(15, "}");
					tl(14, "});");
					tl(13, "} else {");
					tl(14, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, f);");
					tl(13, "}");
					tl(12, "});");
					tl(11, "} else {");
					tl(12, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, e);");
					tl(11, "}");
					tl(10, "});");
					tl(9, "} else {");
					tl(10, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, d);");
					tl(9, "}");
					tl(8, "});");
					tl(7, "} else {");
					tl(8, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, c);");
					tl(7, "}");
					tl(6, "});");
					tl(5, "} else {");
					tl(6, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
					tl(3, "}");
					tl(2, "});");
				}
				else if(classeApiMethode.contains("PATCH")) {
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete, body);");
					tl(2, "sql", classeNomSimple, "(requeteSite, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "recherche", classeNomSimple, "(requeteSite, false, true, b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, "ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, " = b.result();");
					tl(6, "liste", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ", c -> {");
					tl(7, "if(c.succeeded()) {");
					tl(8, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(8, "connexionSql.commit(d -> {");
					tl(9, "if(a.succeeded()) {");
					tl(10, "connexionSql.close(e -> {");
					tl(11, "if(a.succeeded()) {");
					tl(12, "gestionnaireEvenements.handle(Future.succeededFuture(c.result()));");
					tl(11, "} else {");
					tl(12, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, e);");
					tl(11, "}");
					tl(10, "});");
					tl(9, "} else {");
					tl(10, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, d);");
					tl(9, "}");
					tl(8, "});");
					tl(7, "} else {");
					tl(8, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, c);");
					tl(7, "}");
					tl(6, "});");
					tl(5, "} else {");
					tl(6, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
					tl(3, "}");
					tl(2, "});");
				}
				else if(classeApiMethode.contains("Recherche")) {
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete);");
					tl(2, classeApiOperationIdMethode, "(requeteSite, false, true, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, " = a.result();");
					tl(4, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ", b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, "gestionnaireEvenements.handle(Future.succeededFuture(b.result()));");
					tl(5, "} else {");
					tl(6, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
					tl(3, "}");
					tl(2, "});");
				}
				else if(classeApiMethode.contains("GET")) {
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete);");
					tl(2, "recherche", classeNomSimple, "(requeteSite, false, true, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, " = a.result();");
					tl(4, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ", b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, "gestionnaireEvenements.handle(Future.succeededFuture(b.result()));");
					tl(5, "} else {");
					tl(6, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
					tl(3, "}");
					tl(2, "});");
				}
				else if(classeApiMethode.contains("PUT")) {
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete, body);");
					tl(2, "sql", classeNomSimple, "(requeteSite, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "remplacer", classeApiMethode, classeNomSimple, "(requeteSite, b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, classeNomSimple, " ", StringUtils.uncapitalize(classeNomSimple), " = b.result();");
					tl(6, "sql", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", c -> {");
					tl(7, "if(c.succeeded()) {");
					tl(8, "definir", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", d -> {");
					tl(9, "if(d.succeeded()) {");
					tl(10, "attribuer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", e -> {");
					tl(11, "if(e.succeeded()) {");
					tl(12, "indexer", classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", f -> {");
					tl(13, "if(f.succeeded()) {");
					tl(14, "reponse200", classeApiMethode, classeNomSimple, "(", StringUtils.uncapitalize(classeNomSimple), ", g -> {");
					tl(15, "if(g.succeeded()) {");
					tl(16, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(16, "connexionSql.commit(h -> {");
					tl(17, "if(a.succeeded()) {");
					tl(18, "connexionSql.close(i -> {");
					tl(19, "if(a.succeeded()) {");
					tl(20, "gestionnaireEvenements.handle(Future.succeededFuture(g.result()));");
					tl(19, "} else {");
					tl(20, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, i);");
					tl(19, "}");
					tl(18, "});");
					tl(17, "} else {");
					tl(18, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, h);");
					tl(17, "}");
					tl(16, "});");
					tl(15, "} else {");
					tl(16, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, g);");
					tl(15, "}");
					tl(14, "});");
					tl(13, "} else {");
					tl(14, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, f);");
					tl(13, "}");
					tl(12, "});");
					tl(11, "} else {");
					tl(12, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, e);");
					tl(11, "}");
					tl(10, "});");
					tl(9, "} else {");
					tl(10, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, d);");
					tl(9, "}");
					tl(8, "});");
					tl(7, "} else {");
					tl(8, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, c);");
					tl(7, "}");
					tl(6, "});");
					tl(5, "} else {");
					tl(6, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
					tl(3, "}");
					tl(2, "});");
				}
				else if(classeApiMethode.contains("DELETE")) {
					tl(2, "RequeteSite requeteSite = genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete);");
					tl(2, "sql", classeNomSimple, "(requeteSite, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "recherche", classeNomSimple, "(requeteSite, false, true, b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, "ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, " = b.result();");
					tl(6, "supprimer", classeApiMethode, classeNomSimple, "(requeteSite, c -> {");
					tl(7, "if(c.succeeded()) {");
					tl(8, "reponse200", classeApiMethode, classeNomSimple, "(requeteSite, d -> {");
					tl(9, "if(d.succeeded()) {");
					tl(10, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(10, "connexionSql.commit(e -> {");
					tl(11, "if(a.succeeded()) {");
					tl(12, "connexionSql.close(f -> {");
					tl(13, "if(a.succeeded()) {");
					tl(14, "gestionnaireEvenements.handle(Future.succeededFuture(d.result()));");
					tl(13, "} else {");
					tl(14, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, f);");
					tl(13, "}");
					tl(12, "});");
					tl(11, "} else {");
					tl(12, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, e);");
					tl(11, "}");
					tl(10, "});");
					tl(9, "} else {");
					tl(10, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, d);");
					tl(9, "}");
					tl(8, "});");
					tl(7, "} else {");
					tl(8, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, c);");
					tl(7, "}");
					tl(6, "});");
					tl(5, "} else {");
					tl(6, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classeNomSimple, "(requeteSite, gestionnaireEvenements, a);");
					tl(3, "}");
					tl(2, "});");
				}
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
					tl(1, "public void ", classeApiOperationIdMethode, "(RequeteSite requeteSite, Boolean peupler, Boolean stocker, Handler<AsyncResult<ListeRecherche<", classeNomSimple, ">>> gestionnaireEvenements) {");
					tl(2, "try {");
					tl(3, "OperationRequest operationRequete = requeteSite.getOperationRequete();");
					tl(3, "String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
					tl(3, "String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(", q(",\\s*"), ");");
					tl(3, "ListeRecherche<", classeNomSimple, "> listeRecherche = new ListeRecherche<", classeNomSimple, ">();");
					tl(3, "listeRecherche.setPeupler(peupler);");
					tl(3, "listeRecherche.setStocker(stocker);");
					tl(3, "listeRecherche.setQuery(\"*:*\");");
					tl(3, "listeRecherche.setC(", classeNomSimple, ".class);");
					tl(3, "listeRecherche.setRows(1000000);");
					tl(3, "if(entiteListe != null)");
					tl(3, "listeRecherche.setFields(entiteListe);");
					tl(3, "listeRecherche.addSort(\"partNumero_indexed_int\", ORDER.asc);");
					l();
					tl(3, "String pageUri = null;");
					tl(3, "String id = operationRequete.getParams().getJsonObject(\"path\").getString(\"id\");");
					tl(3, "if(", classeVarCleUnique, " != null) {");
					tl(4, "pageUri = ", q(classeApiUriMethode, "/"), " + id;");
					tl(4, "listeRecherche.addFilterQuery(\"pageUri_indexed_string:\" + ClientUtils.escapeQueryChars(pageUri));");
					tl(3, "}");
					l();
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
					tl(3, "gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));");
					tl(2, "} catch(Exception e) {");
					tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
				if(classeApiMethode.contains("POST")) {
					l();
					tl(1, "public void creer", classeApiMethode, classeNomSimple, "(RequeteSite requeteSite, Handler<AsyncResult<", classeNomSimple, ">> gestionnaireEvenements) {");
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
					tl(4, "gestionnaireEvenements.handle(Future.succeededFuture(o));");
					tl(3, "});");
					tl(2, "} catch(Exception e) {");
					tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
				if(classeApiMethode.contains("PATCH")) {
					l();
					tl(1, "public void liste", classeApiMethode, classeNomSimple, "(ListeRecherche<", classeNomSimple, "> liste", classeNomSimple, ", Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
					tl(2, "List<Future> futures = new ArrayList<>();");
					tl(2, "liste", classeNomSimple, ".getList().forEach(o -> {");
					tl(3, "futures.add(");
					tl(4, "sql", classeApiMethode, classeNomSimple, "(o).compose(");
					tl(5, "a -> definir", classeApiMethode, classeNomSimple, "(a).compose(");
					tl(6, "b -> indexer", classeApiMethode, classeNomSimple, "(b)");
					tl(5, ")");
					tl(4, ")");
					tl(3, ");");
					tl(2, "});");
					tl(2, "CompositeFuture.all(futures).setHandler( a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "reponse200", classeApiMethode, classeNomSimple, "(liste", classeNomSimple, ", gestionnaireEvenements);");
					tl(3, "} else {");
					tl(4, "erreur", classeNomSimple, "(liste", classeNomSimple, ".getRequeteSite_(), gestionnaireEvenements, a);");
					tl(3, "}");
					tl(2, "});");
					tl(1, "}");
					l();
					tl(1, "public Future<", classeNomSimple, "> sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o) {");
					tl(2, "Future<", classeNomSimple, "> future = Future.future();");
					tl(2, "try {");
					tl(3, "RequeteSite requeteSite = o.getRequeteSite_();");
					tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
					tl(3, "JsonObject requeteJson = requeteSite.getObjetJson();");
					tl(3, "StringBuilder patchSql = new StringBuilder();");
					tl(3, "List<Object> patchSqlParams = new ArrayList<Object>();");
					tl(3, "Set<String> methodeNoms = requeteJson.fieldNames();");
					tl(3, classeNomSimple, " o2 = new ", classeNomSimple, "();");
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
					tl(4, "o2.setRequeteSite_(o.getRequeteSite_());");
					tl(4, "o2.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
					tl(4, "future.complete(o2);");
					tl(3, "});");
					tl(3, "return future;");
					tl(2, "} catch(Exception e) {");
					tl(3, "return Future.failedFuture(e);");
					tl(2, "}");
					tl(1, "}");
					l();
					tl(1, "public Future<", classeNomSimple, "> definir", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o) {");
					tl(2, "Future<", classeNomSimple, "> future = Future.future();");
					tl(2, "try {");
					tl(3, "RequeteSite requeteSite = o.getRequeteSite_();");
					tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
					tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
					tl(3, "connexionSql.queryWithParams(");
					tl(5, "SiteContexte.SQL_definir");
					tl(5, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, "))");
					tl(5, ", definirAsync");
					tl(3, "-> {");
					tl(4, "if(definirAsync.succeeded()) {");
					tl(5, "for(JsonArray definition : definirAsync.result().getResults()) {");
					tl(6, "o.definirPourClasse(definition.getString(0), definition.getString(1));");
					tl(5, "}");
					tl(5, "future.complete(o);");
					tl(4, "} else {");
					tl(3, "future.fail(definirAsync.cause());");
					tl(4, "}");
					tl(3, "});");
					tl(3, "return future;");
					tl(2, "} catch(Exception e) {");
					tl(3, "return Future.failedFuture(e);");
					tl(2, "}");
					tl(1, "}");
					l();
					tl(1, "public Future<Void> indexer", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o) {");
					tl(2, "Future<Void> future = Future.future();");
					tl(2, "try {");
					tl(3, "o.initLoinPourClasse(o.getRequeteSite_());");
					tl(3, "o.indexerPourClasse();");
					tl(4, "future.complete();");
					tl(3, "return future;");
					tl(2, "} catch(Exception e) {");
					tl(3, "return Future.failedFuture(e);");
					tl(2, "}");
					tl(1, "}");
				}
				if(classeApiMethode.contains("PUT")) {
					l();
					tl(1, "public void remplacer", classeApiMethode, classeNomSimple, "(RequeteSite requeteSite, Handler<AsyncResult<", classeNomSimple, ">> gestionnaireEvenements) {");
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
					tl(4, "gestionnaireEvenements.handle(Future.succeededFuture(o));");
					tl(3, "});");
					tl(2, "} catch(Exception e) {");
					tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
				if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT")) {
					l();
					tl(1, "public void sql", classeApiMethode, classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
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
					tl(4, "gestionnaireEvenements.handle(Future.succeededFuture());");
					tl(3, "});");
					tl(2, "} catch(Exception e) {");
					tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
				if(classeApiMethode.contains("GET")) {
				}
				if(classeApiMethode.contains("DELETE")) {
					l();
					tl(1, "public void supprimer", classeApiMethode, classeNomSimple, "(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
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
					tl(4, "gestionnaireEvenements.handle(Future.succeededFuture());");
					tl(3, "});");
					tl(2, "} catch(Exception e) {");
					tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
				l();
				t(1, "public void reponse200", classeApiMethode, classeNomSimple, "(");

				if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT"))
					s(classeNomSimple, " o");
				else if(classeApiMethode.contains("DELETE"))
					s("RequeteSite requeteSite");
				else
					s("ListeRecherche<", classeNomSimple, "> liste", classeNomSimple);

				l(", Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");

				tl(2, "try {");
				tl(3, "Buffer buffer = Buffer.buffer();");

				if(classeApiMethode.contains("POST") || classeApiMethode.contains("PUT")) {
					tl(3, "RequeteSite requeteSite = o.getRequeteSite_();");
				}
				else if(classeApiMethode.contains("Recherche") || classeApiMethode.contains("PATCH") || classeApiMethode.contains("GET")) {
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
				tl(3, "requeteSite.setW(w);");


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
						tl(3, "for(int i = 0; i < liste", classeNomSimple, ".size(); i++) {");
						tl(4, classeNomSimple, " o = liste", classeNomSimple, ".getList().get(i);");
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
						tl(3, "if(liste", classeNomSimple, ".size() > 0) {");
						tl(4, "SolrDocument documentSolr = documentsSolr.get(0);");
						tl(4, classeNomSimple, " o = liste", classeNomSimple, ".get(0);");
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
					tl(3, "gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, \"OK\", buffer, new CaseInsensitiveHeaders())));");
				}
				else {
					tl(3, "gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));");
				}

				tl(2, "} catch(Exception e) {");
				tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
				tl(2, "}");
				tl(1, "}");
			}
	
			s(wApiEntites.toString());

			l();
			tl(1, "public String varIndexe", classeNomSimple, "(String entiteVar) {");
			tl(2, "switch(entiteVar) {");
			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNom + "_indexed_string:" + fqClassesSuperEtMoi);
				QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList rechercheListe = rechercheReponse.getResults();
	
				if(rechercheListe.size() > 0) {
					for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
						for(Integer j = 0; j < rechercheListe.size(); j++) {
							SolrDocument entiteDocumentSolr = rechercheListe.get(j);
							entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNom + "_stored_string");
							entiteSuffixeType = (String)entiteDocumentSolr.get("entiteSuffixeType_stored_string");
							entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");

							if(classeIndexe && entiteIndexe) {
								tl(3, "case \"", entiteVar, "\":");
								tl(4, "return \"", entiteVar, "_indexed", entiteSuffixeType, "\";");
							}
						}
						rechercheSolr.setStart(i.intValue() + rechercheLignes);
						rechercheReponse = clientSolrComputate.query(rechercheSolr);
						rechercheListe = rechercheReponse.getResults();
					}
				}
			}

			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" n'est pas une entité indexé. \", entiteVar));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "// Partagé //");
			l();
			tl(1, "public void erreur", classeNomSimple, "(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {");
			tl(2, "Throwable e = resultatAsync.cause();");
			tl(2, "ExceptionUtils.printRootCauseStackTrace(e);");
			tl(2, "OperationResponse reponseOperation = new OperationResponse(400, \"BAD REQUEST\", ");
			tl(3, "Buffer.buffer().appendString(");
			tl(4, "new JsonObject() {{");
			tl(5, "put(\"erreur\", new JsonObject() {{");
			tl(5, "put(\"message\", e.getMessage());");
			tl(5, "}});");
			tl(4, "}}.encodePrettily()");
			tl(3, ")");
			tl(3, ", new CaseInsensitiveHeaders()");
			tl(2, ");");
			tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
			tl(2, "if(connexionSql != null) {");
			tl(3, "connexionSql.rollback(a -> {");
			tl(4, "if(a.succeeded()) {");
			tl(5, "connexionSql.close(b -> {");
			tl(6, "if(a.succeeded()) {");
			tl(7, "gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));");
			tl(6, "} else {");
			tl(7, "gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));");
			tl(6, "}");
			tl(5, "});");
			tl(4, "} else {");
			tl(5, "gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} else {");
			tl(3, "gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void sql", classeNomSimple, "(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
			tl(2, "try {");
			tl(3, "SQLClient clientSql = requeteSite.getSiteContexte_().getClientSql();");
			l();
			tl(3, "clientSql.getConnection(sqlAsync -> {");
			tl(4, "if(sqlAsync.succeeded()) {");
			tl(5, "SQLConnection connexionSql = sqlAsync.result();");
			tl(5, "connexionSql.setAutoCommit(false, a -> {");
			tl(6, "if(a.succeeded()) {");
			tl(7, "requeteSite.setConnexionSql(connexionSql);");
			tl(7, "gestionnaireEvenements.handle(Future.succeededFuture());");
			tl(6, "} else {");
			tl(7, "gestionnaireEvenements.handle(Future.failedFuture(a.cause()));");
			tl(6, "}");
			tl(5, "});");
			tl(4, "} else {");
			tl(5, "gestionnaireEvenements.handle(Future.failedFuture(sqlAsync.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
	//		tl(1, "public RequeteSite genererRequeteSitePour", classeNomSimple, "(SiteContexte siteContexte, RoutingContext contexteItineraire) {");
			tl(1, "public RequeteSite genererRequeteSitePour", classeNomSimple, "(SiteContexte siteContexte, OperationRequest operationRequete) {");
			tl(2, "return genererRequeteSitePour", classeNomSimple, "(siteContexte, operationRequete, null);");
			tl(1, "}");
			l();
			tl(1, "public RequeteSite genererRequeteSitePour", classeNomSimple, "(SiteContexte siteContexte, OperationRequest operationRequete, JsonObject body) {");
			tl(2, "Vertx vertx = siteContexte.getVertx();");
			tl(2, "RequeteSite requeteSite = new RequeteSite();");
			tl(2, "requeteSite.setObjetJson(body);");
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
			tl(1, "public void definir", classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
			tl(2, "try {");
			tl(3, "RequeteSite requeteSite = o.getRequeteSite_();");
			tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
			tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			tl(3, "connexionSql.queryWithParams(");
			tl(5, "SiteContexte.SQL_definir");
			tl(5, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, "))");
			tl(5, ", definirAsync");
			tl(3, "-> {");
			tl(4, "if(definirAsync.succeeded()) {");
			tl(5, "for(JsonArray definition : definirAsync.result().getResults()) {");
			tl(6, "o.definirPourClasse(definition.getString(0), definition.getString(1));");
			tl(5, "}");
			tl(5, "gestionnaireEvenements.handle(Future.succeededFuture());");
			tl(4, "} else {");
			tl(5, "gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void attribuer", classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
			tl(2, "try {");
			tl(3, "RequeteSite requeteSite = o.getRequeteSite_();");
			tl(3, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
			tl(3, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
			tl(3, "connexionSql.queryWithParams(");
			tl(5, "SiteContexte.SQL_attribuer");
			tl(5, ", new JsonArray(Arrays.asList(", classeVarClePrimaire, ", ", classeVarClePrimaire, "))");
			tl(5, ", attribuerAsync");
			tl(3, "-> {");
			tl(4, "if(attribuerAsync.succeeded()) {");
			tl(5, "if(attribuerAsync.result() != null) {");
			tl(6, "for(JsonArray definition : attribuerAsync.result().getResults()) {");
			tl(7, "o.attribuerPourClasse(definition.getString(0), definition.getString(1));");
			tl(6, "}");
			tl(5, "}");
			tl(5, "gestionnaireEvenements.handle(Future.succeededFuture());");
			tl(4, "} else {");
			tl(5, "gestionnaireEvenements.handle(Future.failedFuture(attribuerAsync.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void indexer", classeNomSimple, "(", classeNomSimple, " o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {");
			tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
			tl(2, "try {");
			tl(3, "o.initLoinPourClasse(requeteSite);");
			tl(3, "o.indexerPourClasse();");
			tl(3, "gestionnaireEvenements.handle(Future.succeededFuture());");
			tl(2, "} catch(Exception e) {");
			tl(3, "gestionnaireEvenements.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
	
			tl(0, "}");

			auteurGenApiServiceImpl.flushClose();
			System.out.println("Ecrire: " + classeCheminGenApiServiceImpl); 
		}
	}
}
