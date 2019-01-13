package org.computate.enUS.java;

import org.apache.commons.lang3.StringUtils;

/**	
 *	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 **/
public class WriteApiClass extends WriteGenClass {

	protected AllWriter writerApiPackageInfo;

	protected AllWriter writerApiServiceImpl;

	protected AllWriter writerGenApiServiceImpl;

	protected AllWriter writerGenApiService;

	protected String classSimpleNameApiPackageInfo;

	protected String classSimpleNameGenApiServiceImpl;

	protected String classSimpleNameApiServiceImpl;

	protected String classSimpleNameGenApiService;

	protected String classCanonicalNameApiPackageInfo;

	protected String classCanonicalNameGenApiServiceImpl;

	protected String classCanonicalNameApiServiceImpl;

	protected String classCanonicalNameGenApiService;

	public void  apiCodeClassBegin(String languageName) throws Exception, Exception {
		o = writerGenApiServiceImpl;
		l("package ", classPackageName, ";");
		l();
		if(classImportsGenApi.size() > 0) { 
			for(String classImport : classImportsGenApi) {
				l("import ", classImport, ";");
			}
			l();
		}

		tl(0, "");
		writeComment(classComment, 0); 
		s("public class ", classSimpleNameApiGen);
//		l(" extends HttpServlet {");
		s(" implements ", classSimpleName, "ApiService");
		l(" {");
		l();
		tl(1, "private static final Logger LOGGER = LoggerFactory.getLogger(", classSimpleNameApiGen, ".class);");
		l();
		tl(1, "private static final String SERVICE_ADDRESS = \"", classSimpleNameApiServiceImpl, "\";");
		l();
		tl(1, "protected SiteContexte siteContexte;");
		l();
		tl(1, "public ", classSimpleNameApiGen, "(SiteContexte siteContexte) {");
		tl(2, "this.siteContexte = siteContexte;");
		tl(2, classSimpleNameApiServiceImpl, "Service service = ", classSimpleNameApiServiceImpl, "Service.createProxy(siteContexte.getVertx(), SERVICE_ADDRESS);");
		tl(1, "}");
//		l();
//		tl(1, "public static final String VAL_nomCanonique", classSimpleName, " = \"", classCanonicalName, "\";");
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

	public void  apiCodeClassEnd(String languageName) throws Exception, Exception {

		///////////////////////////
		// auteurApiEnsembleInfo //
		///////////////////////////

		auteurApiEnsembleInfo.l("@ModuleGen(name=\"", classSimpleName, "Api", "\", groupPackage=\"", classeNomEnsemble, "\")");
		auteurApiEnsembleInfo.l("package ", classeNomEnsemble, ";");
		auteurApiEnsembleInfo.l();
		auteurApiEnsembleInfo.l("import io.vertx.codegen.annotations.ModuleGen;");

		/////////////////////////
		// writerGenApiService //
		/////////////////////////

		writerGenApiService.l("package ", classeNomEnsemble, ";");
		writerGenApiService.l();
		writerGenApiService.l("import ", classePartsSiteContext(langueNom).nomCanonique);
		writerGenApiService.l("import ", classeNomEnsemble, ".", classSimpleName, "ApiServiceVertxEBProxy;");
		writerGenApiService.l("import io.vertx.codegen.annotations.ProxyGen;");
		writerGenApiService.l("import io.vertx.core.AsyncResult;");
		writerGenApiService.l("import io.vertx.core.Handler;");
		writerGenApiService.l("import io.vertx.core.Vertx;");
		writerGenApiService.l("import io.vertx.core.json.JsonObject;");
		writerGenApiService.l("import io.vertx.ext.web.api.OperationRequest;");
		writerGenApiService.l("import io.vertx.ext.web.api.OperationResponse;");
		writerGenApiService.l();
		writerGenApiService.l("@ProxyGen");
		writerGenApiService.s("public interface ", classSimpleNameGenApiServiceImpl);
		writerGenApiService.l();
		writerGenApiService.tl(1, "// A factory method to create an instance and a proxy. ");
		writerGenApiService.tl(1, "static ", classSimpleNameGenApiService, " create(SiteContext siteContext, Vertx vertx) {");
		writerGenApiService.tl(2, "return new ", classSimpleNameApiServiceImpl, "(siteContext);");
		writerGenApiService.tl(1, "}");
		writerGenApiService.l();
		writerGenApiService.tl(1, "// A factory method to create an instance and a proxy. ");
		writerGenApiService.tl(1, "static ", classSimpleNameGenApiService, " createProxy(Vertx vertx, String address) {");
		writerGenApiService.tl(2, "return new ", classSimpleNameGenApiService, "(vertx, address);");
		writerGenApiService.tl(1, "}");
		writerGenApiService.l();
		writerGenApiService.tl(1, "public void search", classSimpleName, "(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> resultHandler);");
		writerGenApiService.tl(1, "public void post", classSimpleName, "(JsonObject json, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> resultHandler);");
		writerGenApiService.tl(1, "public void patch", classSimpleName, "(JsonObject json, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> resultHandler);");
		writerGenApiService.tl(1, "public void get", classSimpleName, "(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> resultHandler);");
		writerGenApiService.tl(1, "public void put", classSimpleName, "(JsonObject json, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> resultHandler);");
		writerGenApiService.tl(1, "public void delete", classSimpleName, "(JsonObject json, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> resultHandler);");
		writerGenApiService.tl(0, "}");

		/////////////////////////////
		// writerGenApiServiceImpl //
		/////////////////////////////

		o = writerGenApiServiceImpl;

		s(wApiEntities.toString());
		l();
		tl(1, "public void handleGet", classSimpleName, "(SiteContext siteContext) {");
//		tl(2, "Router siteRouteur = siteContext.getSiteRouteur();");
		tl(2, "OpenAPI3RouterFactory usineRouteur = siteContext.getUsineRouteur();");

//		tl(2, "siteRouteur.get(\"", classeApiUri, "\").handler(rc -> {");
		l();
//		tl(2, "HTTPRequestValidationHandler gestionnaireValidation = HTTPRequestValidationHandler.create();");
//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"q\", ParameterTypeValidator.createStringTypeValidator(\"[^:]+:.*\", \"*:*\"), false, false);");
//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"fq\", ParameterTypeValidator.createStringTypeValidator(\"[^:]+:.*\", null), false, false);");
//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"sort\", ParameterTypeValidator.createStringTypeValidator(\"[^:]+:.*\", null), false, false);");
//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"fl\", ParameterTypeValidator.createStringTypeValidator(\"[^:]+:.*\", null), false, false);");
//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"start\", ParameterTypeValidator.createIntegerTypeValidator(null, 0D, null, 0), false, false);");
//		tl(2, "gestionnaireValidation.addQueryParamWithCustomTypeValidator(\"rows\", ParameterTypeValidator.createIntegerTypeValidator(null, 1D, null, 10), false, false);");
//		l();
//		tl(2, "siteRouteur.get(\"", classeApiUri, "\")");
//		tl(4, ".handler(gestionnaireValidation)");
//		tl(4, ".handler(rc -> {");
		tl(2, "usineRouteur.addHandlerByOperationId(\"get", classSimpleName, "\", contexteItineraire -> {");
		Integer tBase = 0;
		if(classRolesFound && classRoles != null) {
			String requeteRole = classRoles.get(0);
			tBase = 6;
			tl(3, "eventHandler.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
			tl(4, "try {");
			tl(5, "if (authRes.result() == Boolean.TRUE) {");
		}
		else {
			tBase = 4;
			tl(3, "try {");
		}
		l();
		tl(tBase, "contexteItineraire.response().putHeader(\"content-type\", \"application/json\").setChunked(true);");
		tl(tBase, "RequeteSite requeteSite = genererRequeteSitePour", classSimpleName, "(siteContext);");
		tl(tBase, "SolrQuery searchSolr = requeteSite.getRechercheSolr();");
		tl(tBase, "SolrDocumentList resultatsRecherche = requeteSite.getReponseRecherche().getResults();");
		tl(tBase, "Integer searchLignes = searchSolr.getRows();");
		l();
		tl(tBase, "genererGetDebut", classSimpleName, "(requeteSite);");
		tl(tBase, "for(long i = resultatsRecherche.getStart(); i < resultatsRecherche.getNumFound(); i+=searchLignes) {");
		tl(tBase + 1, "for(int j = 0; j < resultatsRecherche.size(); j++) {");
		tl(tBase + 2, "long resultatIndice = i + j;");
		tl(tBase + 2, "SolrDocument documentSolr = resultatsRecherche.get(j);");
		tl(tBase + 2, "ResultatRecherche resultatRecherche = new ResultatRecherche();");
		tl(tBase + 2, "resultatRecherche.setRequeteSite_(requeteSite);");
		tl(tBase + 2, "resultatRecherche.setDocumentSolr(documentSolr);");
		tl(tBase + 2, "resultatRecherche.setResultatIndice(resultatIndice);");
		tl(tBase + 2, "genererGetIndividuel", classSimpleName, "(resultatRecherche);");
		tl(tBase + 1, "}");
		tl(tBase, "}");
		tl(tBase, "genererGetFin", classSimpleName, "(requeteSite);");
		tl(tBase, "requeteSite.getReponseServeur().end();");
		if(classRolesFound && classRoles != null) {
			tl(5, "}");
			tl(5, "else {");
			tl(6, "contexteItineraire.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end();");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "LOGGER.error(\"Error: \", e.getMessage());");
			tl(5, "contexteItineraire.fail(e);");
			tl(4, "}");
			tl(3, "});");
		}
		else {
			tl(3, "} catch(Exception e) {");
			tl(4, "LOGGER.error(\"Error: \", e.getMessage());");
			tl(4, "contexteItineraire.fail(e);");
			tl(3, "}");
		}
		tl(2, "});");
		tl(2, "usineRouteur.addFailureHandlerByOperationId(\"get", classSimpleName, "\", contexteItineraire -> {");
		tl(3, "Throwable failure = contexteItineraire.failure();");
		tl(3, "if (failure instanceof ValidationException) {");
		tl(4, "String validationErrorMessage = failure.getMessage();");
		tl(4, "LOGGER.error(\"Error: \", validationErrorMessage);");
		tl(4, "contexteItineraire.fail(failure);");
		tl(3, "}");
		tl(2, "});");
		tl(1, "}");
		l();
		tl(1, "public void genererGetDebut", classSimpleName, "(RequeteSite requeteSite) {");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
		tl(2, "QueryResponse reponseRecherche = requeteSite.getReponseRecherche();");
		tl(2, "reponseServeur.write(\"{\\n\");");
		tl(2, "Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());");
		tl(2, "Long millisTransmission = reponseRecherche.getElapsedTime();");
		tl(2, "Long numCommence = reponseRecherche.getResults().getStart();");
		tl(2, "Long numTrouve = reponseRecherche.getResults().getNumFound();");
		tl(2, "Integer numRetourne = reponseRecherche.getResults().size();");
		tl(2, "String tempsRecherche = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));");
		tl(2, "String tempsTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
		tl(2, "Exception exceptionRecherche = reponseRecherche.getException();");
		l();
		tl(2, "reponseServeur.write(\"\\t\\\"numCommence\\\": \");");
		tl(2, "reponseServeur.write(numCommence.toString());");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"numTrouve\\\": \");");
		tl(2, "reponseServeur.write(numTrouve.toString());");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"numRetourne\\\": \");");
		tl(2, "reponseServeur.write(numRetourne.toString());");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"tempsRecherche\\\": \\\"\");");
		tl(2, "reponseServeur.write(tempsRecherche);");
		tl(2, "reponseServeur.write(\"\\\"\");");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"tempsTransmission\\\": \\\"\");");
		tl(2, "reponseServeur.write(tempsTransmission);");
		tl(2, "reponseServeur.write(\"\\\"\");");
		l();
		tl(2, "if(exceptionRecherche != null) {");
		tl(3, "reponseServeur.write(\",\\n\\t\\\"exceptionRecherche\\\": \\\"\");");
		tl(3, "reponseServeur.write(exceptionRecherche.getMessage());");
		tl(3, "reponseServeur.write(\"\\\"\");");
		tl(2, "}");
		l();
		tl(2, "reponseServeur.write(\",\\n\\t\\\"resultats\\\": [\\n\");");
		tl(1, "}");
		l();
		tl(1, "public void genererGetIndividuel", classSimpleName, "(ResultatRecherche resultatRecherche) throws Exception {");
		tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
		tl(2, "SolrDocument documentSolr = resultatRecherche.getDocumentSolr();");
		tl(2, "Long resultatIndice = resultatRecherche.getResultatIndice();");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
		tl(2, "reponseServeur.write(\"\\t\\t\");");
		tl(2, "if(resultatIndice > 0)");
		tl(3, "reponseServeur.write(\", \");");
		tl(2, "reponseServeur.write(\"{\\n\");");
		tl(2, "Collection<String> champNoms = documentSolr.getFieldNames();");
		tl(2, "Integer j = 0;");
		tl(2, "for(String champNomStocke : champNoms) {");
		tl(3, "Collection<Object> champValeurs = documentSolr.getFieldValues(champNomStocke);");
		tl(3, "j = genererGet", classSimpleName, "(j, resultatRecherche, champNomStocke, champValeurs);");
		tl(2, "}");
		tl(2, "reponseServeur.write(\"\\t\\t}\\n\");");
		tl(1, "}");
		l();
		tl(1, "public void genererGetFin", classSimpleName, "(RequeteSite requeteSite) {");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//		tl(2, "if(exceptionRecherche != null) {");
//		l();
//		tl(4, "reponseServeur.write(\"\\t\\t}\\n\");");
//		tl(3, "}");
//		tl(2, "}");
		tl(2, "reponseServeur.write(\"\\t]\\n\");");
		tl(2, "reponseServeur.write(\"}\\n\");");
		tl(1, "}");
//		tl(1, "@Override protected void doGet(HttpServerRequest requeteServeur, HttpServerResponse reponseServeur) throws ServletException, IOException {");
		l();
		tl(1, "public String varIndexe", classSimpleName, "(String entityVar) throws Exception {");
		tl(2, "switch(entityVar) {");
		s(wApiGet.toString());
		tl(3, "default:");
		tl(4, "throw new Exception(String.format(\"\\\"%s\\\" n'est pas une entité indexé. \", entityVar));");
		tl(2, "}");
		tl(1, "}");
		l();
		tl(1, "public Future<ListeRecherche<", classSimpleName, ">> search", classSimpleName, "(RequeteSite requeteSite) {");
		tl(2, "String entityVar = null;");
		tl(2, "String valeurIndexe = null;");
		tl(2, "String varIndexe = null;");
		tl(2, "String valeurTri = null;");
		tl(2, "Integer searchDebut = null;");
		tl(2, "Integer searchNum = null;");
		tl(2, "ListeRecherche<", classSimpleName, "> listeRecherche = new ListeRecherche<", classSimpleName, ">();");
		tl(2, "listeRecherche.setQuery(\"*:*\");");
		tl(2, "listeRecherche.setRows(1000000);");
		tl(2, "listeRecherche.addSort(\"partNumero_indexed_int\", ORDER.asc);");
		tl(2, "List<NameValuePair> pairesNomValeur = URLEncodedUtils.parse(requeteSite.getRequeteServeur().query(), Charset.forName(\"UTF-8\"));");
		tl(2, "for(NameValuePair paireNomValeur : pairesNomValeur) {");
		tl(3, "String paireNom = paireNomValeur.getName();");
		tl(3, "String paireValeur = paireNomValeur.getValue();");
		tl(3, "try {");
		tl(4, "switch(paireNom) {");

		tl(5, "case \"q\":");
		tl(6, "entityVar = StringUtils.trim(StringUtils.substringBefore(paireValeur, \":\"));");
		tl(6, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paireValeur, \":\"));");
		tl(6, "varIndexe = varIndexe", classSimpleName, "(entityVar);");
		tl(6, "listeRecherche.setQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
		tl(6, "break;");

		tl(5, "case \"fq\":");
		tl(6, "entityVar = StringUtils.trim(StringUtils.substringBefore(paireValeur, \":\"));");
		tl(6, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paireValeur, \":\"));");
		tl(6, "varIndexe = varIndexe", classSimpleName, "(entityVar);");
		tl(6, "listeRecherche.addFilterQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
		tl(6, "break;");

		tl(5, "case \"sort\":");
		tl(6, "entityVar = StringUtils.trim(StringUtils.substringBefore(paireValeur, \" \"));");
		tl(6, "valeurTri = StringUtils.trim(StringUtils.substringAfter(paireValeur, \" \"));");
		tl(6, "varIndexe = varIndexe", classSimpleName, "(entityVar);");
		tl(6, "listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));");
		tl(6, "break;");

		tl(5, "case \"fl\":");
		tl(6, "entityVar = StringUtils.trim(paireValeur);");
		tl(6, "varIndexe = varIndexe", classSimpleName, "(entityVar);");
		tl(6, "listeRecherche.addField(varIndexe);");
		tl(6, "break;");

		tl(5, "case \"start\":");
		tl(6, "searchDebut = Integer.parseInt(paireValeur);");
		tl(6, "listeRecherche.setStart(searchDebut);");
		tl(6, "break;");

		tl(5, "case \"rows\":");
		tl(6, "searchNum = Integer.parseInt(paireValeur);");
		tl(6, "listeRecherche.setRows(searchNum);");
		tl(6, "break;");

		tl(4, "}");
		tl(3, "} catch(Exception e) {");
		tl(4, "return Future.failedFuture(e);");
		tl(3, "}");

		tl(2, "}");
		tl(2, "listeRecherche.initLoinPourClasse(requeteSite);");
		tl(2, "return Future.succeededFuture(listeRecherche);");
		tl(1, "}");
		l();
//		tl(1, "public RequeteSite genererRequeteSitePour", classSimpleName, "(SiteContext siteContext, RoutingContext contexteItineraire) throws Exception {");
		tl(1, "public RequeteSite genererRequeteSitePour", classSimpleName, "(SiteContext siteContext) throws Exception {");
		tl(2, "Vertx vertx = siteContext.getVertx();");
		tl(2, "RequeteSite requeteSite = new RequeteSite();");
		tl(2, "requeteSite.setVertx(vertx);");
//		tl(2, "requeteSite.setContexteItineraire(contexteItineraire);");
		tl(2, "requeteSite.setSiteContext_(siteContext);");
		tl(2, "requeteSite.initLoinRequeteSite(requeteSite);");
		l();

		tl(2, "UtilisateurSite utilisateurSite = new UtilisateurSite();");
		tl(2, "utilisateurSite.initLoinUtilisateurSite(requeteSite);");
		tl(2, "requeteSite.setUtilisateurSite(utilisateurSite);");
		tl(2, "utilisateurSite.setRequeteSite_(requeteSite);");

		tl(2, "return requeteSite;");
		tl(1, "}");
		l();
//		tl(1, "public Integer genererGet", classSimpleName, "(Integer j, PrintWriter ecrivain, String entityVarStocke, Collection<Object> champValeurs) throws Exception {");
		tl(1, "public Integer genererGet", classSimpleName, "(Integer j, ResultatRecherche resultatRecherche, String entityVarStocke, Collection<Object> champValeurs) throws Exception {");
		tl(2, "RequeteSite requeteSite = resultatRecherche.getRequeteSite_();");
		tl(2, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
		tl(2, "if(!champValeurs.isEmpty()) {");
		tl(3, "Object champValeur = champValeurs.iterator().next();");
		tl(3, "if(champValeur != null) {");
		s(wApiGenerateGet.toString());
		tl(3, "}");
		tl(2, "}");
		tl(2, "return j;");
		tl(1, "}");
//
//		//////////
//		// POST //
//		//////////
//		l();
////		tl(1, "protected void handlePost", classSimpleName, "(SiteContext siteContext) {");
//		tl(1, "protected void post", classSimpleName, "(SiteContext siteContext) {");
////		tl(2, "Router siteRouteur = siteContext.getSiteRouteur();");
//		tl(2, "OpenAPI3RouterFactory usineRouteur = siteContext.getUsineRouteur();");
//
////		tl(2, "siteRouteur.get(\"", classeApiUri, "\").handler(rc -> {");
//		tl(2, "usineRouteur.addHandlerByOperationId(\"post", classSimpleName, "\", contexteItineraire -> {");
//		tBase = 0;
//		if(classRolesFound && classRoles != null) {
//			String requeteRole = classRoles.get(0);
//			tBase = 6;
//			tl(3, "eventHandler.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
//			tl(4, "try {");
//			tl(5, "if (authRes.result() == Boolean.TRUE) {");
//		}
//		else {
//			tBase = 4;
//			tl(3, "try {");
//		}
//		tl(tBase + 0, "RequeteSite requeteSite = genererRequeteSitePour", classSimpleName, "(siteContext);");
//		tl(tBase + 0, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//		tl(tBase + 0, "QueryResponse reponseRecherche = requeteSite.getReponseRecherche();");
//		tl(tBase + 0, "JsonObject requeteJson = contexteItineraire.getBodyAsJson();");
//		tl(tBase + 0, "SQLClient clientSql = requeteSite.getSiteContext_().getClientSql();");
//		l();
//		tl(tBase + 0, "clientSql.getConnection(resultatAsync -> {");
//		tl(tBase + 1, "if(resultatAsync.succeeded()) {");
//		tl(tBase + 2, "LocalDateTime modifie = java.time.LocalDateTime.now();");
//		tl(tBase + 2, "String horodatageStr = Timestamp.valueOf(modifie).toString();");
//		tl(tBase + 2, "String utilisateurId = requeteSite.getUtilisateurId();");
//		tl(tBase + 2, "SQLConnection connexionSql = resultatAsync.result();");
//		l();
//		tl(tBase + 2, "connexionSql.queryWithParams(");
//		tl(tBase + 4, "SiteContext.SQL_create");
//		tl(tBase + 4, ", new JsonArray(Arrays.asList(VAL_nomCanonique", classSimpleName, ", utilisateurId))");
//		tl(tBase + 4, ", asyncCreer");
//		tl(tBase + 4, "-> {");
//		tl(tBase + 3, "if(asyncCreer.succeeded()) {");
//		tl(tBase + 4, "List<Object> postSqlParams = Arrays.asList();");
//		tl(tBase + 4, "JsonArray postLigne = asyncCreer.result().getResults().stream().findFirst().orElseGet(() -> null);");
//		tl(tBase + 4, "Long postPk = postLigne.getLong(0);");
//		tl(tBase + 4, "StringBuilder postSql = new StringBuilder();");
//		tl(tBase + 4, "postSqlParams = new ArrayList<Object>();");
//		tl(tBase + 4, "Set<String> entityVars = requeteJson.fieldNames();");
//		tl(tBase + 4, "for(String entityVar : entityVars) {");
//		tl(tBase + 5, "switch(entityVar) {");
//		s(wApiGeneratePost.toString());
//		tl(tBase + 5, "}");
//		tl(tBase + 4, "}");
//		tl(tBase + 4, "connexionSql.queryWithParams(postSql.toString(), new JsonArray(postSqlParams), asyncParams -> {");
//		tl(tBase + 5, "connexionSql.close();");
//		tl(tBase + 5, "if(asyncParams.succeeded()) {");
//		tl(tBase + 6, classSimpleName, " o = new ", classSimpleName, "();");
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
////		tl(tBase, "genererPostDebut", classSimpleName, "(requeteSite);");
////		tl(tBase, classSimpleName, " nouveau", classSimpleName, " = new ", classSimpleName, "();");
////		tl(tBase, "nouveau", classSimpleName, ".initLoin", classSimpleName, "(requeteSite);");
////		tl(tBase, "nouveau", classSimpleName, ".peupler", classSimpleName, "();");
////		tl(tBase, "post", classSimpleName, "();");
////		tl(tBase, "genererPostFin", classSimpleName, "(requeteSite);");
//		tl(tBase, "requeteSite.getReponseServeur().end();");
//		l();
//		l();
//		tl(tBase + 0, "reponseServeur.write(\"\\t]\\n\");");
//		tl(tBase + 0, "reponseServeur.write(\"}\\n\");");
//		if(classRolesFound && classRoles != null) {
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

		//////////
		// POST //
		//////////
		l();
		tl(1, "@Override");
		tl(1, "public void post", classSimpleName, "(JsonObject document, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {");
		tBase = 0;
		if(classRolesFound && classRoles != null) {
			String requeteRole = classRoles.get(0);
			tBase = 4;
			tl(2, "operationRequest.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
			tl(3, "if (authRes.result() == Boolean.TRUE) {");
		}
		else {
			tBase = 2;
		}
		tl(tBase + 0, "try {");
		tl(tBase + 1, "RequeteSite requeteSite = genererRequeteSitePour", classSimpleName, "(siteContext);");
		tl(tBase + 1, "Future<OperationResponse> etapesFutures = sql", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 2, "a -> create", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 3, "cluster -> definir", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(");
		tl(tBase + 4, "c -> attribuer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(");
		tl(tBase + 5, "d -> indexer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(");
		tl(tBase + 6, "operationResponse -> postJson", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ")");
		tl(tBase + 5, ")");
		tl(tBase + 4, ")");
		tl(tBase + 3, ")");
		tl(tBase + 2, ")");
		tl(tBase + 1, ");");
		tl(tBase + 1, "etapesFutures.setHandler(eventHandler);");
		tl(tBase + 0, "} catch(Exception e) {");
		tl(tBase + 1, "eventHandler.handle(Future.failedFuture(e));");
		tl(tBase + 0, "}");
		if(classRolesFound && classRoles != null) {
			tl(3, "}");
			tl(3, "else {");
			tl(4, "contexteItineraire.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end();");
			tl(3, "}");
			tl(2, "});");
		}
		else {
		}
		tl(1, "}");

		///////////
		// PATCH //
		///////////
//		l();
//		tl(1, "protected void patch", classSimpleName, "Old(SiteContext siteContext) {");
////		tl(2, "Router siteRouteur = siteContext.getSiteRouteur();");
//		tl(2, "OpenAPI3RouterFactory usineRouteur = siteContext.getUsineRouteur();");

//		tl(2, "siteRouteur.get(\"", classeApiUri, "\").handler(rc -> {");
//		tl(2, "usineRouteur.addHandlerByOperationId(\"patch", classSimpleName, "\", contexteItineraire -> {");
//		tl(2, "});");
//		tl(1, "}");
		l();
		tl(1, "@Override");
		tl(1, "public void patch", classSimpleName, "(JsonObject document, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {");
//		tl(2, "Router siteRouteur = siteContext.getSiteRouteur();");

//		tl(2, "siteRouteur.get(\"", classeApiUri, "\").handler(rc -> {");
		tBase = 0;
		if(classRolesFound && classRoles != null) {
			String requeteRole = classRoles.get(0);
			tBase = 4;
			tl(2, "operationRequest.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
			tl(3, "if (authRes.result() == Boolean.TRUE) {");
		}
		else {
			tBase = 2;
		}
		tl(tBase + 0, "try {");
		tl(tBase + 1, "RequeteSite requeteSite = genererRequeteSitePour", classSimpleName, "(siteContext);");
//		tl(tBase + 1, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//		tl(tBase + 1, "QueryResponse reponseRecherche = requeteSite.getReponseRecherche();");
		tl(tBase + 1, "Future<OperationResponse> etapesFutures = sql", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 2, "a -> search", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 3, "liste", classSimpleName, " -> patchListe", classSimpleName, "(liste", classSimpleName, ")");
		tl(tBase + 2, ")");
		tl(tBase + 1, ");");
		tl(tBase + 1, "etapesFutures.setHandler(eventHandler);");
//		tl(tBase + 1, "requeteSite.getReponseServeur().end();");
//		l();
//		tl(tBase + 1, "reponseServeur.write(\"\\t]\\n\");");
//		tl(tBase + 1, "reponseServeur.write(\"}\\n\");");
		tl(tBase + 0, "} catch(Exception e) {");
		tl(tBase + 1, "eventHandler.handle(Future.failedFuture(e));");
		tl(tBase + 0, "}");
		if(classRolesFound && classRoles != null) {
			tl(3, "}");
			tl(3, "else {");
			tl(4, "contexteItineraire.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end();");
			tl(3, "}");
			tl(2, "});");
		}
		else {
		}
		tl(1, "}");

		////////////
		// Erreur //
		////////////
//		l();
//		tl(1, "public void genererErreur(RequeteSite requeteSite, Exception e) {");
//		tl(2, "e.printStackTrace();");
//		tl(2, "try {");
//		tl(3, "MimeMessage message = new MimeMessage(requeteSite.SiteContext_.sessionCourriel);");
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
		tl(1, "public Future<Void> sql", classSimpleName, "(RequeteSite requeteSite) {");
		tl(2, "Future<Void> future = Future.future();");
		tl(2, "SQLClient clientSql = requeteSite.getSiteContext_().getClientSql();");
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
		tl(1, "public Future<", classSimpleName, "> create", classSimpleName, "(RequeteSite requeteSite) {");
		tl(2, "Future<", classSimpleName, "> future = Future.future();");
		tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
		tl(2, "String utilisateurId = requeteSite.getUtilisateurId();");
		l();
		tl(2, "connexionSql.queryWithParams(");
		tl(4, "SiteContext.SQL_create");
		tl(4, ", new JsonArray(Arrays.asList(", classSimpleName, ".class.getCanonicalName(), utilisateurId))");
		tl(4, ", createAsync");
		tl(2, "-> {");
		tl(3, "JsonArray patchLigne = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);");
		tl(3, "Long ", classVarPrimaryKey, " = patchLigne.getLong(0);");
		tl(3, classSimpleName, " o = new ", classSimpleName, "();");
		tl(3, "o.set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, ");");
		tl(3, "future.complete(o);");
		tl(2, "});");
		tl(2, "return future;");
		tl(1, "}");
		l();
		tl(1, "public Future<Void> post", classSimpleName, "(", classSimpleName, " o) {");
		tl(2, "Future<Void> future = Future.future();");
		tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
		tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
		tl(2, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
		tl(2, "RoutingContext contexteItineraire = requeteSite.getContexteItineraire();");
		tl(2, "JsonObject jsonObject = contexteItineraire.getBodyAsJson();");
		tl(2, "StringBuilder postSql = new StringBuilder();");
		tl(2, "List<Object> postSqlParams = new ArrayList<Object>();");
		tl(2, "Set<String> entityVars = jsonObject.fieldNames();");
		l();
		tl(2, "for(String entityVar : entityVars) {");
		tl(3, "switch(entityVar) {");
		s(wApiGeneratePost.toString());
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
		l();
		tl(1, "public Future<OperationResponse> patchListe", classSimpleName, "(ListeRecherche<", classSimpleName, "> liste", classSimpleName, ") {");
		tl(2, "List<Future> futures = new ArrayList<>();");
		tl(2, "liste", classSimpleName, ".getList().forEach(o -> {");
		tl(3, "futures.add(");
		tl(4, "patch", classSimpleName, "(o).compose(");
		tl(5, "b -> indexer", classSimpleName, "(o)");
		tl(4, ")");
		tl(3, ");");
		tl(2, "});");
		tl(2, "CompositeFuture compositeFuture = CompositeFuture.all(futures).setHandler(ar -> {");
		tl(3, "if(ar.succeeded()) {");
		tl(4, "patchJsonCluster(listeCluster);");
//		tl(4, "future.complete();");
		tl(3, "} else {");
		tl(3, "}");
		tl(2, "});");
		tl(2, "Future<OperationResponse> future = Future.future().compose(");
		tl(3, "a -> compositeFuture.compose(");
		tl(4, "b -> patchJsonCluster(listeCluster)");
		tl(3, ")");
		tl(2, ");");
		tl(2, "return future;");
		tl(1, "}");
		l();
		tl(1, "public Future<Void> patch", classSimpleName, "(", classSimpleName, " o) {");
		tl(2, "Future<Void> future = Future.future();");
		tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
		tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
		tl(2, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
		tl(2, "RoutingContext contexteItineraire = requeteSite.getContexteItineraire();");
		tl(2, "JsonObject requeteJson = contexteItineraire.getBodyAsJson();");
		tl(2, "StringBuilder patchSql = new StringBuilder();");
		tl(2, "List<Object> patchSqlParams = new ArrayList<Object>();");
		tl(2, "Set<String> methodeNoms = requeteJson.fieldNames();");
		l();
		tl(2, "for(String methodeNom : methodeNoms) {");
		tl(3, "switch(methodeNom) {");
		s(wApiGeneratePatch.toString());
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
		l();
		tl(1, "public Future<Void> definir", classSimpleName, "(", classSimpleName, " o) {");
		tl(2, "Future<Void> future = Future.future();");
		tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
		tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
		tl(2, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
		tl(2, "connexionSql.queryWithParams(");
		tl(4, "SiteContext.SQL_definir");
		tl(4, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, "))");
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
		tl(1, "public Future<Void> attribuer", classSimpleName, "(", classSimpleName, " o) {");
		tl(2, "Future<Void> future = Future.future();");
		tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
		tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
		tl(2, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
		tl(2, "connexionSql.queryWithParams(");
		tl(4, "SiteContext.SQL_attribuer");
		tl(4, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, "))");
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
		tl(1, "public Future<Void> indexer", classSimpleName, "(", classSimpleName, " o) {");
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
		l();
		tl(1, "public Future<OperationResponse> postJson", classSimpleName, "(", classSimpleName, " o) {");
		tl(2, "Buffer buffer = Buffer.buffer();");
		tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
		tl(2, "return Future.succeededFuture(OperationResponse.completedWithJson(buffer));");
		tl(1, "}");
		l();
		tl(1, "public Future<OperationResponse> patchJson", classSimpleName, "(ListeRecherche<", classSimpleName, "> liste", classSimpleName, ") {");
		tl(2, "Buffer buffer = Buffer.buffer();");
		tl(2, "return Future.succeededFuture(OperationResponse.completedWithJson(buffer));");
		tl(1, "}");

		tl(0, "}");

		System.out.println("Write: " + classPathGenApiServiceImpl); 
		writerGenApiServiceImpl.flushClose();
	}
}
