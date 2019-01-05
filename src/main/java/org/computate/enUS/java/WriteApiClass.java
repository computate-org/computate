package org.computate.enUS.java;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.computate.frFR.cardiaque.cluster.Cluster;
import org.computate.frFR.cardiaque.recherche.ListeRecherche;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;

/**	
 *	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 **/
public class WriteApiClass extends WriteGenClass {

	protected StringPrintWriter writerApiGenClass;

	protected String classSimpleNameApi;

	protected String classSimpleNameApiGen;

	public void  apiCodeClassBegin(String languageName) throws Exception, Exception {
		o = writerApiGenClass;
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
		tl(1, "private static final String SERVICE_ADDRESS = \"", classSimpleNameApi, "\";");
		l();
		tl(1, "protected SiteContexte siteContexte;");
		l();
		tl(1, "public ", classSimpleNameApiGen, "(SiteContexte siteContexte) {");
		tl(2, "this.siteContexte = siteContexte;");
		tl(2, classSimpleNameApi, "Service service = ", classSimpleNameApi, "Service.createProxy(siteContexte.getVertx(), SERVICE_ADDRESS);");
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
		o = writerApiGenClass;

		s(wApiEntities.toString());
		l();
		tl(1, "public void handleGet", classSimpleName, "(SiteContexte siteContext) {");
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
		if(classRolesFound) {
			String requeteRole = classRoles.get(0);
			tBase = 6;
			tl(3, "contexteItineraire.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
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
		tl(tBase, "SolrQuery rechercheSolr = requeteSite.getRechercheSolr();");
		tl(tBase, "SolrDocumentList resultatsRecherche = requeteSite.getReponseRecherche().getResults();");
		tl(tBase, "Integer rechercheLignes = rechercheSolr.getRows();");
		l();
		tl(tBase, "genererGetDebut", classSimpleName, "(requeteSite);");
		tl(tBase, "for(long i = resultatsRecherche.getStart(); i < resultatsRecherche.getNumFound(); i+=rechercheLignes) {");
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
		if(classRolesFound) {
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
		tl(1, "public Future<ListeRecherche<", classSimpleName, ">> recherche", classSimpleName, "(RequeteSite requeteSite) {");
		tl(2, "String entityVar = null;");
		tl(2, "String valeurIndexe = null;");
		tl(2, "String varIndexe = null;");
		tl(2, "String valeurTri = null;");
		tl(2, "Integer rechercheDebut = null;");
		tl(2, "Integer rechercheNum = null;");
		tl(2, "ListeRecherche<", classSimpleName, "> listeRecherche = new ListeRecherche<", classSimpleName, ">();");
		tl(2, "listeRecherche.setQuery(\"*:*\");");
		tl(2, "listeRecherche.setRows(1000000);");
		tl(2, "listeRecherche.addSort(\"partNumero_indexed_int\", ORDER.asc);");
		tl(2, "MultiMap paramMap = requeteSite.getRequeteServeur().params();");
		tl(2, "for(String paramCle : paramMap.names()) {");
		tl(3, "List<String> paramValeurs = paramMap.getAll(paramCle);");
		tl(3, "for(String paramValeur : paramValeurs) {");
		tl(4, "try {");
		tl(5, "switch(paramCle) {");

		tl(6, "case \"q\":");
		tl(7, "entityVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, \":\"));");
		tl(7, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, \":\"));");
		tl(7, "varIndexe = varIndexe", classSimpleName, "(paramCle);");
		tl(7, "listeRecherche.setQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
		tl(7, "break;");

		tl(6, "case \"fq\":");
		tl(7, "entityVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, \":\"));");
		tl(7, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter(paramValeur, \":\"));");
		tl(7, "varIndexe = varIndexe", classSimpleName, "(paramCle);");
		tl(7, "listeRecherche.addFilterQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
		tl(7, "break;");

		tl(6, "case \"sort\":");
		tl(7, "entityVar = StringUtils.trim(StringUtils.substringBefore(paramValeur, \" \"));");
		tl(7, "valeurTri = StringUtils.trim(StringUtils.substringAfter(paramValeur, \" \"));");
		tl(7, "varIndexe = varIndexe", classSimpleName, "(paramCle);");
		tl(7, "listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));");
		tl(7, "break;");

		tl(6, "case \"fl\":");
		tl(7, "entityVar = StringUtils.trim(paramValeur);");
		tl(7, "varIndexe = varIndexe", classSimpleName, "(paramCle);");
		tl(7, "listeRecherche.addField(varIndexe);");
		tl(7, "break;");

		tl(6, "case \"start\":");
		tl(7, "rechercheDebut = Integer.parseInt(paramValeur);");
		tl(7, "listeRecherche.setStart(rechercheDebut);");
		tl(7, "break;");

		tl(6, "case \"rows\":");
		tl(7, "rechercheNum = Integer.parseInt(paramValeur);");
		tl(7, "listeRecherche.setRows(rechercheNum);");
		tl(7, "break;");

		tl(5, "}");
		tl(4, "} catch(Exception e) {");
		tl(5, "return Future.failedFuture(e);");
		tl(4, "}");

		tl(3, "}");
		tl(2, "}");
		tl(2, "listeRecherche.initLoinPourClasse(requeteSite);");
		tl(2, "return Future.succeededFuture(listeRecherche);");
		tl(1, "}");
		l();
//		tl(1, "public RequeteSite genererRequeteSitePour", classSimpleName, "(SiteContexte siteContext, RoutingContext contexteItineraire) throws Exception {");
		tl(1, "public RequeteSite genererRequeteSitePour", classSimpleName, "(SiteContexte siteContext) throws Exception {");
		tl(2, "Vertx vertx = siteContext.getVertx();");
		tl(2, "RequeteSite requeteSite = new RequeteSite();");
		tl(2, "requeteSite.setVertx(vertx);");
//		tl(2, "requeteSite.setContexteItineraire(contexteItineraire);");
		tl(2, "requeteSite.setSiteContexte_(siteContext);");
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
////		tl(1, "protected void handlePost", classSimpleName, "(SiteContexte siteContext) {");
//		tl(1, "protected void post", classSimpleName, "(SiteContexte siteContext) {");
////		tl(2, "Router siteRouteur = siteContext.getSiteRouteur();");
//		tl(2, "OpenAPI3RouterFactory usineRouteur = siteContext.getUsineRouteur();");
//
////		tl(2, "siteRouteur.get(\"", classeApiUri, "\").handler(rc -> {");
//		tl(2, "usineRouteur.addHandlerByOperationId(\"post", classSimpleName, "\", contexteItineraire -> {");
//		tBase = 0;
//		if(classRolesFound) {
//			String requeteRole = classRoles.get(0);
//			tBase = 6;
//			tl(3, "contexteItineraire.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
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
//		s(wApiGenererPost.toString());
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
//		if(classRolesFound) {
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
		tl(1, "public void post", classSimpleName, "(JsonObject document, Handler<AsyncResult<OperationResponse>> resultHandler) {");
		tBase = 0;
		if(classRolesFound) {
			String requeteRole = classRoles.get(0);
			tBase = 4;
			tl(2, "contexteItineraire.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
			tl(3, "if (authRes.result() == Boolean.TRUE) {");
		}
		else {
			tBase = 2;
		}
		tl(tBase + 0, "RequeteSite requeteSite;");
		tl(tBase + 0, "try {");
		tl(tBase + 1, "requeteSite = genererRequeteSitePour", classSimpleName, "(siteContext);");
		tl(tBase + 0, "} catch(Exception e) {");
		tl(tBase + 1, "resultHandler.handle(Future.failedFuture(e));");
		tl(tBase + 0, "}");
		tl(tBase + 0, "Future<OperationResponse> etapesFutures = sql", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 1, "a -> creer", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 2, "cluster -> definir", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(");
		tl(tBase + 3, "c -> attribuer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(");
		tl(tBase + 4, "d -> indexer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(");
		tl(tBase + 5, "operationResponse -> postJson", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ")");
		tl(tBase + 4, ")");
		tl(tBase + 3, ")");
		tl(tBase + 2, ")");
		tl(tBase + 1, ")");
		tl(tBase + 0, ");");
		tl(tBase + 0, "etapesFutures.setHandler(resultHandler);");
		if(classRolesFound) {
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
//		tl(1, "protected void patch", classSimpleName, "Old(SiteContexte siteContext) {");
////		tl(2, "Router siteRouteur = siteContext.getSiteRouteur();");
//		tl(2, "OpenAPI3RouterFactory usineRouteur = siteContext.getUsineRouteur();");

//		tl(2, "siteRouteur.get(\"", classeApiUri, "\").handler(rc -> {");
//		tl(2, "usineRouteur.addHandlerByOperationId(\"patch", classSimpleName, "\", contexteItineraire -> {");
//		tl(2, "});");
//		tl(1, "}");
		l();
		tl(1, "@Override");
		tl(1, "public void patch", classSimpleName, "(JsonObject document, Handler<AsyncResult<OperationResponse>> resultHandler) {");
//		tl(2, "Router siteRouteur = siteContext.getSiteRouteur();");

//		tl(2, "siteRouteur.get(\"", classeApiUri, "\").handler(rc -> {");
		tBase = 0;
		if(classRolesFound) {
			String requeteRole = classRoles.get(0);
			tBase = 4;
			tl(2, "contexteItineraire.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
			tl(3, "if (authRes.result() == Boolean.TRUE) {");
		}
		else {
			tBase = 2;
		}
		tl(tBase + 0, "RequeteSite requeteSite;");
		tl(tBase + 0, "try {");
		tl(tBase + 1, "requeteSite = genererRequeteSitePour", classSimpleName, "(siteContext);");
		tl(tBase + 0, "} catch(Exception e) {");
		tl(tBase + 1, "resultHandler.handle(Future.failedFuture(e));");
		tl(tBase + 0, "}");
//		tl(tBase + 0, "HttpServerResponse reponseServeur = requeteSite.getReponseServeur();");
//		tl(tBase + 0, "QueryResponse reponseRecherche = requeteSite.getReponseRecherche();");
		tl(tBase + 0, "Future<OperationResponse> etapesFutures = sql", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 1, "a -> recherche", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 2, "liste", classSimpleName, " -> patch", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 3, "c -> definir", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 4, "d -> attribuer", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 5, "e -> indexer", classSimpleName, "(requeteSite).compose(");
		tl(tBase + 6, "operationResponse -> patchJson", classSimpleName, "(requeteSite)");
		tl(tBase + 5, ")");
		tl(tBase + 4, ")");
		tl(tBase + 3, ")");
		tl(tBase + 2, ")");
		tl(tBase + 1, ")");
		tl(tBase + 0, ");");
		tl(tBase + 0, "etapesFutures.setHandler(resultHandler);");
//		tl(tBase + 0, "requeteSite.getReponseServeur().end();");
//		l();
//		tl(tBase + 0, "reponseServeur.write(\"\\t]\\n\");");
//		tl(tBase + 0, "reponseServeur.write(\"}\\n\");");
		if(classRolesFound) {
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
		tl(1, "public Future<Void> sql", classSimpleName, "(RequeteSite requeteSite) {");
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
		tl(1, "public Future<", classSimpleName, "> creer", classSimpleName, "(RequeteSite requeteSite) {");
		tl(2, "Future<", classSimpleName, "> future = Future.future();");
		tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
		tl(2, "String utilisateurId = requeteSite.getUtilisateurId();");
		l();
		tl(2, "connexionSql.queryWithParams(");
		tl(4, "SiteContexte.SQL_creer");
		tl(4, ", new JsonArray(Arrays.asList(", classSimpleName, ".class.getCanonicalName(), utilisateurId))");
		tl(4, ", creerAsync");
		tl(2, "-> {");
		tl(3, "JsonArray patchLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);");
		tl(3, "Long ", classeVarClePrimaire, " = patchLigne.getLong(0);");
		tl(3, classSimpleName, " o = new ", classSimpleName, "();");
		tl(3, "o.set", StringUtils.capitalize(classeVarClePrimaire), "(", classeVarClePrimaire, ");");
		tl(3, "future.complete(o);");
		tl(2, "});");
		tl(2, "return future;");
		tl(1, "}");
		l();
		tl(1, "public Future<Void> post", classSimpleName, "(", classSimpleName, " o) {");
		tl(2, "Future<Void> future = Future.future();");
		tl(2, "RequeteSite requeteSite = o.getRequeteSite_();");
		tl(2, "SQLConnection connexionSql = requeteSite.getConnexionSql();");
		tl(2, "Long ", classeVarClePrimaire, " = o.get", StringUtils.capitalize(classeVarClePrimaire), "();");
		tl(2, "RoutingContext contexteItineraire = requeteSite.getContexteItineraire();");
		tl(2, "JsonObject jsonObject = contexteItineraire.getBodyAsJson();");
		tl(2, "StringBuilder postSql = new StringBuilder();");
		tl(2, "List<Object> postSqlParams = new ArrayList<Object>();");
		tl(2, "Set<String> entityVars = jsonObject.fieldNames();");
		l();
		tl(2, "for(String entityVar : entityVars) {");
		tl(3, "switch(entityVar) {");
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
		l();
		tl(1, "public Future<Void> patchListe", classSimpleName, "(RequeteSite requeteSite, List<", classSimpleName, "> liste", classSimpleName, ") {");
		tl(2, "List<Future> futures = new ArrayList<>();");
		tl(2, "liste", classSimpleName, ".forEach(o -> { futures.add(indexer", classSimpleName, "(o)); });");
		tl(2, "CompositeFuture.all(futures).setHandler(ar -> {");
		tl(3, "if(ar.succeeded()) {");
		tl(4, "patchJsonCluster(listeCluster);");
		tl(4, "future.complete();");
		tl(3, "} else {");
		tl(3, "}");
		tl(2, "});");
		tl(1, "}");
		l();
		tl(1, "public Future<Void> patch", classSimpleName, "(RequeteSite requeteSite) {");
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
		tl(1, "public Future<Void> attribuer", classSimpleName, "(", classSimpleName, " o) {");
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
		tl(1, "public Future<OperationResponse> patchJson", classSimpleName, "(List<", classSimpleName, "> liste", classSimpleName, ") {");
		tl(2, "Buffer buffer = Buffer.buffer();");
		tl(2, "return Future.succeededFuture(OperationResponse.completedWithJson(buffer));");
		tl(1, "}");

		tl(0, "}");

		System.out.println("Write:" + classPathApiGen); 
		writerApiGenClass.flushClose();
	}
}
