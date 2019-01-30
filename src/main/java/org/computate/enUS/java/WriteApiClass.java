package org.computate.enUS.java;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**	
 *	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 **/
public class WriteApiClass extends WriteGenClass {

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

	protected List<String> classApiMethods;

	public void  apiCodeClassBegin(String languageName) throws Exception, Exception {
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

	public void  writeGenApiService(String languageName) throws Exception, Exception {
		if(writerGenApiService != null) {
			writerGenApiService.l("package ", classPackageName, ";");
			writerGenApiService.l();
			writerGenApiService.l("import ", classPartsSiteContext.canonicalName, ";");
//			writerGenApiService.l("import ", classPackageName, ".", classSimpleName, "ApiServiceVertxEBProxy;");
			writerGenApiService.l("import io.vertx.codegen.annotations.ProxyGen;");
			writerGenApiService.l("import io.vertx.ext.web.api.generator.WebApiServiceGen;");
			writerGenApiService.l("import io.vertx.core.AsyncResult;");
			writerGenApiService.l("import io.vertx.core.Handler;");
			writerGenApiService.l("import io.vertx.core.Vertx;");
			writerGenApiService.l("import io.vertx.core.json.JsonObject;");
			writerGenApiService.l("import io.vertx.core.json.JsonArray;");
			writerGenApiService.l("import io.vertx.ext.web.api.OperationRequest;");
			writerGenApiService.l("import io.vertx.ext.web.api.OperationResponse;");
			writerGenApiService.l();
			writerGenApiService.l("@WebApiServiceGen");
			writerGenApiService.l("@ProxyGen");
			writerGenApiService.s("public interface ", classSimpleNameGenApiService, " {");
			writerGenApiService.l();
			writerGenApiService.tl(1, "// A factory method to create an instance and a proxy. ");
			writerGenApiService.tl(1, "static ", classSimpleNameGenApiService, " creer(SiteContext siteContext, Vertx vertx) {");
			writerGenApiService.tl(2, "return new ", classSimpleNameApiServiceImpl, "(siteContext);");
			writerGenApiService.tl(1, "}");
			writerGenApiService.l();
			writerGenApiService.tl(1, "// A factory method to create an instance and a proxy. ");
			writerGenApiService.tl(1, "static ", classSimpleNameGenApiService, " creerProxy(Vertx vertx, String addresse) {");
			writerGenApiService.tl(2, "return new ", classSimpleNameGenApiService, "VertxEBProxy(vertx, addresse);");
			writerGenApiService.tl(1, "}");
			writerGenApiService.l();
			for(String classeApiMethode : classApiMethods) {
				String classApiOperationIdMethod = (String)classDoc.get("classApiOperationId" + classeApiMethode + "_frFR_stored_string");

				writerGenApiService.t(1, "public void ", classApiOperationIdMethod, "(");
				if(StringUtils.containsAny(classeApiMethode, "POST", "PUT", "PATCH"))
					writerGenApiService.s("JsonObject document, ");
				writerGenApiService.l("OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements);");
			}
			writerGenApiService.tl(0, "}");

			writerGenApiService.flushClose();
		}
	}

	public void  writeApiServiceImpl(String languageName) throws Exception, Exception {
		if(writerApiServiceImpl != null) {
			writerApiServiceImpl.l("package ", classPackageName, ";");
			writerApiServiceImpl.l();
			writerApiServiceImpl.l("import ", classPartsSiteContext.canonicalName, ";");
//			auteurGenApiService.l("import ", classPackageName, ".", classSimpleName, "ApiServiceVertxEBProxy;");
			writerApiServiceImpl.l();
			writerApiServiceImpl.l("public class ", classSimpleNameApiServiceImpl, " extends ", classSimpleNameGenApiServiceImpl, " {");
			writerApiServiceImpl.l();
			writerApiServiceImpl.tl(1, "public ", classSimpleNameApiServiceImpl, "(SiteContext siteContext) {");
			writerApiServiceImpl.tl(2, "super(siteContext);");
			writerApiServiceImpl.tl(1, "}");
			writerApiServiceImpl.l("}");

			writerApiServiceImpl.flushClose();
		}
	}

	public void  writeGenApiServiceImpl(String languageName) throws Exception, Exception {

		if(writerGenApiServiceImpl != null) {
			o = writerGenApiServiceImpl;
	
			l("package ", classPackageName, ";");
			l();
			writerGenApiServiceImpl.l("import ", classPartsAllWriter.canonicalName, ";");
			if(classImportsGenApi.size() > 0) { 
				for(String classeImportation : classImportsGenApi) {
					l("import ", classeImportation, ";");
				}
				l();
			}
	
			tl(0, "");
			writeComment(classComment, 0); 
			s("public class ", classSimpleNameGenApiServiceImpl);
			s(" implements ", classSimpleNameGenApiService);
			l(" {");
			l();
			tl(1, "private static final Logger LOGGER = LoggerFactory.getLogger(", classSimpleNameGenApiServiceImpl, ".class);");
			l();
			tl(1, "private static final String SERVICE_ADDRESS = \"", classSimpleNameApiServiceImpl, "\";");
			l();
			tl(1, "protected SiteContext siteContext;");
			l();
			tl(1, "public ", classSimpleNameGenApiServiceImpl, "(SiteContext siteContext) {");
			tl(2, "this.siteContext = siteContext;");
			tl(2, classSimpleNameGenApiService, " service = ", classSimpleNameGenApiService, ".createProxy(siteContext.getVertx(), SERVICE_ADDRESS);");
			tl(1, "}");

			for(String classApiMethod : classApiMethods) {
				String classePageNomCanoniqueMethode = (String)classDoc.get("classePageNomCanonique" + classApiMethod + "_frFR_stored_string");
				String classePageNomSimpleMethode = (String)classDoc.get("classePageNomSimple" + classApiMethod + "_frFR_stored_string");
				String classApiOperationIdMethod = (String)classDoc.get("classApiOperationId" + classApiMethod + "_frFR_stored_string");
				String classeApiUriMethode = (String)classDoc.get("classeApiUri" + classApiMethod + "_frFR_stored_string");
				String classeApiTypeMediaMethode = (String)classDoc.get("classeApiTypeMedia" + classApiMethod + "_stored_string");
				l();
				tl(1, "// ", classApiMethod, " //");
				l();
				tl(1, "@Override");
				t(1, "public void ", classApiOperationIdMethod, "(");
				if(StringUtils.containsAny(classApiMethod, "POST", "PUT", "PATCH"))
					s("JsonObject document, ");
				l("OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {");
				tl(2, "SiteRequest siteRequest = genererSiteRequestPour", classSimpleName, "(siteContext, operationRequest);");

				if(classApiMethod.contains("POST")) {
					tl(2, "Future<OperationResponse> etapesFutures = sql", classSimpleName, "(siteRequest).compose(a -> ");
					tl(3, "create", classApiMethod, classSimpleName, "(siteRequest).compose(", StringUtils.uncapitalize(classSimpleName), " -> ");
					tl(4, "sql", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(c -> ");
					tl(5, "definir", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(d -> ");
					tl(6, "attribuer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(e -> ");
					tl(7, "indexer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(f -> ");
					tl(8, "response200", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ")");
					tl(7, ")");
					tl(6, ")");
					tl(5, ")");
					tl(4, ")");
					tl(3, ")");
					tl(2, ");");
				}
				else if(classApiMethod.contains("PATCH")) {
					tl(2, "Future<OperationResponse> etapesFutures = sql", classSimpleName, "(siteRequest).compose(a -> ");
					tl(3, "search", classSimpleName, "(siteRequest).compose(liste", classSimpleName, "-> ");
					tl(4, "liste", classApiMethod, classSimpleName, "(liste", classSimpleName, ")");
					tl(3, ")");
					tl(2, ");");
				}
				else if(classApiMethod.contains("Recherche")) {
					tl(2, "Future<OperationResponse> etapesFutures = search", classSimpleName, "(siteRequest).compose(liste", classSimpleName, " -> ");
					tl(3, "response200", classApiMethod, classSimpleName, "(liste", classSimpleName, ")");
					tl(2, ");");
				}
				else if(classApiMethod.contains("GET")) {
					tl(2, "Future<OperationResponse> etapesFutures = search", classSimpleName, "(siteRequest).compose(liste", classSimpleName, " -> ");
					tl(3, "response200", classApiMethod, classSimpleName, "(liste", classSimpleName, ")");
					tl(2, ");");
				}
				else if(classApiMethod.contains("PUT")) {
					tl(2, "Future<OperationResponse> etapesFutures = sql", classSimpleName, "(siteRequest).compose(a -> ");
					tl(3, "remplacer", classApiMethod, classSimpleName, "(siteRequest).compose(", StringUtils.uncapitalize(classSimpleName), " -> ");
					tl(4, "sql", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(c -> ");
					tl(5, "definir", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(d -> ");
					tl(6, "attribuer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(e -> ");
					tl(6, "indexer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ").compose(f -> ");
					tl(7, "response200", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ")");
					tl(7, ")");
					tl(6, ")");
					tl(5, ")");
					tl(4, ")");
					tl(3, ")");
					tl(2, ");");
				}
				else if(classApiMethod.contains("DELETE")) {
					tl(2, "Future<OperationResponse> etapesFutures = sql", classSimpleName, "(siteRequest).compose(a -> ");
					tl(3, "search", classSimpleName, "(siteRequest).compose(", StringUtils.uncapitalize(classSimpleName), " -> ");
					tl(4, "supprimer", classApiMethod, classSimpleName, "(siteRequest).compose(c -> ");
					tl(5, "response200", classApiMethod, classSimpleName, "(siteRequest)");
					tl(4, ")");
					tl(3, ")");
					tl(2, ");");
				}

				tl(2, "etapesFutures.setHandler(eventHandler);");
				tl(1, "}");

				if(classApiMethod.contains("Recherche")) {
//					l();
//					tl(1, "public Future<OperationResponse> listeRecherche", classSimpleName, "(ListeRecherche<", classSimpleName, "> liste", classSimpleName, ") {");
//					tl(2, "List<Future> futures = new ArrayList<>();");
//					tl(2, "liste", classSimpleName, ".getList().forEach(o -> {");
//					tl(3, "futures.add(");
//					tl(4, "sqlPATCH", classSimpleName, "(o).compose(");
//					tl(5, "b -> indexer", classSimpleName, "(o)");
//					tl(4, ")");
//					tl(3, ");");
//					tl(2, "});");
//					tl(2, "Future<OperationResponse> future = CompositeFuture.all(futures).compose( a -> ");
//					tl(3, "response200Recherche", classSimpleName, "(liste", classSimpleName, ")");
//					tl(2, ");");
//					tl(2, "return future;");
//					tl(1, "}");
					l();
					tl(1, "public Future<ListeRecherche<", classSimpleName, ">> search", classSimpleName, "(SiteRequest siteRequest) {");
					tl(2, "OperationRequest operationRequest = siteRequest.getOperationRequete();");
					tl(2, "String entiteListeStr = siteRequest.getOperationRequete().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
					tl(2, "String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(", q(",\\s*"), ");");
					tl(2, "ListeRecherche<", classSimpleName, "> listeRecherche = new ListeRecherche<", classSimpleName, ">();");
					tl(2, "listeRecherche.setQuery(\"*:*\");");
					tl(2, "listeRecherche.setRows(1000000);");
					tl(2, "if(entiteListe != null)");
					tl(3, "listeRecherche.setFields(entiteListe);");
					tl(2, "listeRecherche.addSort(\"partNumero_indexed_int\", ORDER.asc);");
					tl(2, "operationRequest.getParams().getJsonObject(\"query\").forEach(queryParam -> {");
					tl(3, "String entityVar = null;");
					tl(3, "String valeurIndexe = null;");
					tl(3, "String varIndexe = null;");
					tl(3, "String valeurTri = null;");
					tl(3, "Integer searchDebut = null;");
					tl(3, "Integer searchNum = null;");
					tl(3, "String paramNom = queryParam.getKey();");
					tl(3, "Object paramValuesObject = queryParam.getValue();");
					tl(3, "JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);");
					l();
					tl(3, "for(Object paramObject : paramObjects) {");
					tl(4, "switch(paramNom) {");
			
					tl(5, "case \"q\":");
					tl(6, "entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, \":\"));");
					tl(6, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObject, \":\"));");
					tl(6, "varIndexe = \"*\".equals(entityVar) ? entityVar : varIndexe", classSimpleName, "(entityVar);");
					tl(6, "listeRecherche.setQuery(varIndexe + \":\" + (\"*\".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));");
					tl(6, "break;");
			
					tl(5, "case \"fq\":");
					tl(6, "entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, \":\"));");
					tl(6, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObject, \":\"));");
					tl(6, "varIndexe = varIndexe", classSimpleName, "(entityVar);");
					tl(6, "listeRecherche.addFilterQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
					tl(6, "break;");
			
					tl(5, "case \"sort\":");
					tl(6, "entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, \" \"));");
					tl(6, "valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObject, \" \"));");
					tl(6, "varIndexe = varIndexe", classSimpleName, "(entityVar);");
					tl(6, "listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));");
					tl(6, "break;");
			
					tl(5, "case \"fl\":");
					tl(6, "entityVar = StringUtils.trim((String)paramObject);");
					tl(6, "varIndexe = varIndexe", classSimpleName, "(entityVar);");
					tl(6, "listeRecherche.addField(varIndexe);");
					tl(6, "break;");
			
					tl(5, "case \"start\":");
					tl(6, "searchDebut = (Integer)paramObject;");
					tl(6, "listeRecherche.setStart(searchDebut);");
					tl(6, "break;");
			
					tl(5, "case \"rows\":");
					tl(6, "searchNum = (Integer)paramObject;");
					tl(6, "listeRecherche.setRows(searchNum);");
					tl(6, "break;");
			
					tl(4, "}");
			
					tl(3, "}");
					tl(2, "});");
					tl(2, "listeRecherche.initLoinPourClasse(siteRequest);");
					tl(2, "return Future.succeededFuture(listeRecherche);");
					tl(1, "}");
				}
				if(classApiMethod.contains("POST")) {
					l();
					tl(1, "public Future<", classSimpleName, "> create", classApiMethod, classSimpleName, "(SiteRequest siteRequest) {");
					tl(2, "Future<", classSimpleName, "> future = Future.future();");
					tl(2, "SQLConnection connexionSql = siteRequest.getConnexionSql();");
					tl(2, "String utilisateurId = siteRequest.getUtilisateurId();");
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
				}
				if(classApiMethod.contains("PATCH")) {
					l();
					tl(1, "public Future<OperationResponse> liste", classApiMethod, classSimpleName, "(ListeRecherche<", classSimpleName, "> liste", classSimpleName, ") {");
					tl(2, "List<Future> futures = new ArrayList<>();");
					tl(2, "liste", classSimpleName, ".getList().forEach(o -> {");
					tl(3, "futures.add(");
					tl(4, "sql", classApiMethod, classSimpleName, "(o).compose(");
					tl(5, "b -> indexer", classSimpleName, "(o)");
					tl(4, ")");
					tl(3, ");");
					tl(2, "});");
					tl(2, "Future<OperationResponse> future = CompositeFuture.all(futures).compose( a -> ");
					tl(3, "response200", classApiMethod, classSimpleName, "(liste", classSimpleName, ")");
					tl(2, ");");
					tl(2, "return future;");
					tl(1, "}");
					l();
					tl(1, "public Future<Void> sql", classApiMethod, classSimpleName, "(", classSimpleName, " o) {");
					tl(2, "Future<Void> future = Future.future();");
					tl(2, "SiteRequest siteRequest = o.getSiteRequest_();");
					tl(2, "SQLConnection connexionSql = siteRequest.getConnexionSql();");
					tl(2, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
					tl(2, "RoutingContext contexteItineraire = siteRequest.getContexteItineraire();");
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
				}
				if(classApiMethod.contains("PUT")) {
					l();
					tl(1, "public Future<", classSimpleName, "> remplacer", classApiMethod, classSimpleName, "(SiteRequest siteRequest) {");
					tl(2, "Future<", classSimpleName, "> future = Future.future();");
					tl(2, "SQLConnection connexionSql = siteRequest.getConnexionSql();");
					tl(2, "String utilisateurId = siteRequest.getUtilisateurId();");
					tl(2, "Long pk = siteRequest.getRequetePk();");
					l();
					tl(2, "connexionSql.queryWithParams(");
					tl(4, "SiteContext.SQL_vider");
					tl(4, ", new JsonArray(Arrays.asList(pk, ", classSimpleName, ".class.getCanonicalName(), pk, pk, pk))");
					tl(4, ", remplacerAsync");
					tl(2, "-> {");
					tl(3, classSimpleName, " o = new ", classSimpleName, "();");
					tl(3, "o.set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, ");");
					tl(3, "future.complete(o);");
					tl(2, "});");
					tl(2, "return future;");
					tl(1, "}");
				}
				if(classApiMethod.contains("POST") || classApiMethod.contains("PUT")) {
					l();
					tl(1, "public Future<Void> sql", classApiMethod, classSimpleName, "(", classSimpleName, " o) {");
					tl(2, "Future<Void> future = Future.future();");
					tl(2, "SiteRequest siteRequest = o.getSiteRequest_();");
					tl(2, "SQLConnection connexionSql = siteRequest.getConnexionSql();");
					tl(2, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
					tl(2, "RoutingContext contexteItineraire = siteRequest.getContexteItineraire();");
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
				}
				if(classApiMethod.contains("GET")) {
//					l();
//					tl(1, "public void genererGetDebut", classSimpleName, "(SiteRequest siteRequest) {");
//					tl(2, "HttpServerResponse responseServeur = siteRequest.getReponseServeur();");
//					tl(2, "QueryResponse responseRecherche = siteRequest.getReponseRecherche();");
//					tl(2, "responseServeur.write(\"{\\n\");");
//					tl(2, "Long millisRecherche = Long.valueOf(responseRecherche.getQTime());");
//					tl(2, "Long millisTransmission = responseRecherche.getElapsedTime();");
//					tl(2, "Long numCommence = responseRecherche.getResults().getStart();");
//					tl(2, "Long numTrouve = responseRecherche.getResults().getNumFound();");
//					tl(2, "Integer numRetourne = responseRecherche.getResults().size();");
//					tl(2, "String tempsRecherche = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));");
//					tl(2, "String tempsTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
//					tl(2, "Exception exceptionRecherche = responseRecherche.getException();");
//					l();
//					tl(2, "responseServeur.write(\"\\t\\\"numCommence\\\": \");");
//					tl(2, "responseServeur.write(numCommence.toString());");
//					l();
//					tl(2, "responseServeur.write(\",\\n\\t\\\"numTrouve\\\": \");");
//					tl(2, "responseServeur.write(numTrouve.toString());");
//					l();
//					tl(2, "responseServeur.write(\",\\n\\t\\\"numRetourne\\\": \");");
//					tl(2, "responseServeur.write(numRetourne.toString());");
//					l();
//					tl(2, "responseServeur.write(\",\\n\\t\\\"tempsRecherche\\\": \\\"\");");
//					tl(2, "responseServeur.write(tempsRecherche);");
//					tl(2, "responseServeur.write(\"\\\"\");");
//					l();
//					tl(2, "responseServeur.write(\",\\n\\t\\\"tempsTransmission\\\": \\\"\");");
//					tl(2, "responseServeur.write(tempsTransmission);");
//					tl(2, "responseServeur.write(\"\\\"\");");
//					l();
//					tl(2, "if(exceptionRecherche != null) {");
//					tl(3, "responseServeur.write(\",\\n\\t\\\"exceptionRecherche\\\": \\\"\");");
//					tl(3, "responseServeur.write(exceptionRecherche.getMessage());");
//					tl(3, "responseServeur.write(\"\\\"\");");
//					tl(2, "}");
//					l();
//					tl(2, "responseServeur.write(\",\\n\\t\\\"resultats\\\": [\\n\");");
//					tl(1, "}");
//					l();
//					tl(1, "public void genererGetIndividuel", classSimpleName, "(ResultatRecherche resultatRecherche) {");
//					tl(2, "SiteRequest siteRequest = resultatRecherche.getSiteRequest_();");
//					tl(2, "SolrDocument documentSolr = resultatRecherche.getDocumentSolr();");
//					tl(2, "Long resultatIndice = resultatRecherche.getResultatIndice();");
//					tl(2, "HttpServerResponse responseServeur = siteRequest.getReponseServeur();");
//					tl(2, "responseServeur.write(\"\\t\\t\");");
//					tl(2, "if(resultatIndice > 0)");
//					tl(3, "responseServeur.write(\", \");");
//					tl(2, "responseServeur.write(\"{\\n\");");
//					tl(2, "Collection<String> champNoms = documentSolr.getFieldNames();");
//					tl(2, "Integer j = 0;");
//					tl(2, "for(String champNomStocke : champNoms) {");
//					tl(3, "Collection<Object> entiteValeurs = documentSolr.getFieldValues(champNomStocke);");
//					tl(3, "j = genererGet", classSimpleName, "(j, resultatRecherche, champNomStocke, entiteValeurs);");
//					tl(2, "}");
//					tl(2, "responseServeur.write(\"\\t\\t}\\n\");");
//					tl(1, "}");
//					l();
//			//		tl(1, "public Integer genererGet", classSimpleName, "(Integer j, PrintWriter ecrivain, String entityVarStocke, Collection<Object> entiteValeurs) {");
//					tl(1, "public Integer genererGet", classSimpleName, "(Integer j, ResultatRecherche resultatRecherche, String entityVarStocke, Collection<Object> entiteValeurs) {");
//					tl(2, "SiteRequest siteRequest = resultatRecherche.getSiteRequest_();");
//					tl(2, "HttpServerResponse responseServeur = siteRequest.getReponseServeur();");
//					tl(2, "SolrDocument documentSolr = resultatRecherche.getDocumentSolr();;");
//					l();
//					tl(2, "if(documentSolr != null && !entiteValeurs.isEmpty()) {");
//					tl(3, "Object champValeur = entiteValeurs.iterator().next();");
//					tl(3, "Object entiteValeur;");
//					tl(3, "Integer entiteNumero = 0;");
//					l();
////					tl(3, "if(champValeur != null) {");
//					s(wApiGenerateGet.toString());
////					tl(3, "}");
//					tl(2, "}");
//					tl(2, "return j;");
//					tl(1, "}");
//					l();
//					tl(1, "public void genererGetFin", classSimpleName, "(SiteRequest siteRequest) {");
//					tl(2, "HttpServerResponse responseServeur = siteRequest.getReponseServeur();");
//			//		tl(2, "if(exceptionRecherche != null) {");
//			//		l();
//			//		tl(4, "responseServeur.write(\"\\t\\t}\\n\");");
//			//		tl(3, "}");
//			//		tl(2, "}");
//					tl(2, "responseServeur.write(\"\\t]\\n\");");
//					tl(2, "responseServeur.write(\"}\\n\");");
//					tl(1, "}");
//			//		tl(1, "@Override protected void doGet(HttpServerRequest requeteServeur, HttpServerResponse responseServeur) throws ServletException, IOException {");
				}
				if(classApiMethod.contains("DELETE")) {
					l();
					tl(1, "public Future<Void> supprimer", classApiMethod, classSimpleName, "(SiteRequest siteRequest) {");
					tl(2, "Future<Void> future = Future.future();");
					tl(2, "SQLConnection connexionSql = siteRequest.getConnexionSql();");
					tl(2, "String utilisateurId = siteRequest.getUtilisateurId();");
					tl(2, "Long pk = siteRequest.getRequetePk();");
					l();
					tl(2, "connexionSql.queryWithParams(");
					tl(4, "SiteContext.SQL_supprimer");
					tl(4, ", new JsonArray(Arrays.asList(pk, ", classSimpleName, ".class.getCanonicalName(), pk, pk, pk, pk))");
					tl(4, ", supprimerAsync");
					tl(2, "-> {");
					tl(3, "future.complete();");
					tl(2, "});");
					tl(2, "return future;");
					tl(1, "}");
				}
				l();
				t(1, "public Future<OperationResponse> response200", classApiMethod, classSimpleName, "(");

				if(classApiMethod.contains("POST") || classApiMethod.contains("PUT"))
					s(classSimpleName, " o");
				else if(classApiMethod.contains("DELETE"))
					s("SiteRequest siteRequest");
				else
					s("ListeRecherche<", classSimpleName, "> liste", classSimpleName);

				l(") {");

				tl(2, "Buffer buffer = Buffer.buffer();");

				if(classApiMethod.contains("POST") || classApiMethod.contains("PUT")) {
					tl(2, "SiteRequest siteRequest = o.getSiteRequest_();");
				}
				else if(classApiMethod.contains("Recherche")) {
					tl(2, "SiteRequest siteRequest = liste", classSimpleName, ".getSiteRequest_();");
				}
				else {
				}

				t(2, "AllWriter w = AllWriter.create(");
				if(classApiMethod.contains("POST") || classApiMethod.contains("PUT"))
					s("o.getSiteRequest_()");
				else if(classApiMethod.contains("DELETE"))
					s("siteRequest");
				else
					s("liste", classSimpleName, ".getSiteRequest_()");
				l(", buffer);");


				if(classApiMethod.contains("GET")) {
					tl(2, "SolrDocumentList documentsSolr = liste", classSimpleName, ".getSolrDocumentList();");
					l();
				}
				if(classApiMethod.contains("Recherche")) {
				}

				if(classApiMethod.contains("Recherche") || classApiMethod.contains("GET")) {
				}
				else if(classApiMethod.contains("DELETE")) {
				}

				if(classApiMethod.contains("Recherche")) {
					tl(2, "QueryResponse responseRecherche = liste", classSimpleName, ".getQueryResponse();");
					tl(2, "SolrDocumentList documentsSolr = liste", classSimpleName, ".getSolrDocumentList();");
					tl(2, "Long millisRecherche = Long.valueOf(responseRecherche.getQTime());");
					tl(2, "Long millisTransmission = responseRecherche.getElapsedTime();");
					tl(2, "Long numCommence = responseRecherche.getResults().getStart();");
					tl(2, "Long numTrouve = responseRecherche.getResults().getNumFound();");
					tl(2, "Integer numRetourne = responseRecherche.getResults().size();");
					tl(2, "String tempsRecherche = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));");
					tl(2, "String tempsTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
					tl(2, "Exception exceptionRecherche = responseRecherche.getException();");
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
//					tl(4, "String entityVarStocke = champNoms.get(j);");
//					tl(4, "List<Object> entiteValeurs = new ArrayList<>(documentSolr.getFieldValues(entityVarStocke));");
					s(wApiGenerateGet.toString());
//					tl(3, "}");
					l();
					tl(3, "w.tl(2, ", q("}"), ");");
					tl(2, "}");
					tl(2, "w.tl(1, ", q("]"), ");");
					tl(2, "if(exceptionRecherche != null) {");
					tl(3, "w.tl(1, ", q(", ", q("exceptionRecherche"), ": "), ", w.q(exceptionRecherche.getMessage()));");
					tl(2, "}");
				}
				if(classApiMethod.contains("GET")) {

//					if("text/html".equals(classeApiTypeMediaMethode)) {
					if(classePageNomCanoniqueMethode != null) {
						tl(2, classePageNomSimpleMethode, " page = new ", classePageNomSimpleMethode, "();");
//						tl(2, "page.setPageUri(", q(classeApiUriMethode), ");");
						tl(2, "SiteRequest siteRequest = liste", classSimpleName, ".getSiteRequest_();");
						tl(2, "SolrDocument documentSolr = new SolrDocument();");
						tl(2, "documentSolr.setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
						tl(2, "page.setDocumentSolr(documentSolr);");
						tl(2, "page.initLoin", classePageNomSimpleMethode, "(siteRequest);");
					}
					else {
						tl(2, "if(documentsSolr.size() > 0) {");
						tl(3, "SolrDocument documentSolr = documentsSolr.get(0);");
						tl(3, "Object entiteValeur;");
						tl(3, "Integer entiteNumero = 0;");
						l();
						tl(3, "w.l(", q("{"), ");");
	//					tl(3, "for(int j = 0; j < champNoms.size(); j++) {");
	//					tl(4, "String entityVarStocke = champNoms.get(j);");
	//					tl(4, "List<Object> entiteValeurs = new ArrayList<>(documentSolr.getFieldValues(entityVarStocke));");
						s(wApiGenerateGet.toString());
	//					tl(3, "}");
						l();
						tl(3, "w.l(", q("}"), ");");
						tl(2, "}");
					}
				}
				if(classApiMethod.contains("Recherche")) {
					tl(2, "w.l(\"}\");");
				}
//					l();
//					tl(2, "w.l(\",\\n\\t\\\"resultats\\\": [\\n\");");
//					tl(1, "}");
//					l();
//					tl(1, "public void genererGetIndividuel", classSimpleName, "(ResultatRecherche resultatRecherche) {");
//					tl(2, "SiteRequest siteRequest = resultatRecherche.getSiteRequest_();");
//					tl(2, "SolrDocument documentSolr = resultatRecherche.getDocumentSolr();");
//					tl(2, "Long resultatIndice = resultatRecherche.getResultatIndice();");
//					tl(2, "HttpServerResponse responseServeur = siteRequest.getReponseServeur();");
//					tl(2, "w.l(\"\\t\\t\");");
//					tl(2, "if(resultatIndice > 0)");
//					tl(3, "w.l(\", \");");
//					tl(2, "w.l(\"{\\n\");");
//					tl(2, "Collection<String> champNoms = documentSolr.getFieldNames();");
//					tl(2, "Integer j = 0;");
//					tl(2, "for(String champNomStocke : champNoms) {");
//					tl(3, "Collection<Object> entiteValeurs = documentSolr.getFieldValues(champNomStocke);");
//					tl(3, "j = genererGet", classSimpleName, "(j, resultatRecherche, champNomStocke, entiteValeurs);");
//					tl(2, "}");
//					tl(2, "responseServeur.write(\"\\t\\t}\\n\");");
//					tl(1, "}");
//					l();
//			//		tl(1, "public Integer genererGet", classSimpleName, "(Integer j, PrintWriter ecrivain, String entityVarStocke, Collection<Object> entiteValeurs) {");
//					tl(1, "public Integer genererGet", classSimpleName, "(Integer j, ResultatRecherche resultatRecherche, String entityVarStocke, Collection<Object> entiteValeurs) {");
//					tl(2, "SiteRequest siteRequest = resultatRecherche.getSiteRequest_();");
//					tl(2, "HttpServerResponse responseServeur = siteRequest.getReponseServeur();");
//					tl(2, "if(!entiteValeurs.isEmpty()) {");
//					tl(3, "Object champValeur = entiteValeurs.iterator().next();");
//					tl(3, "if(champValeur != null) {");
//					s(wApiGenerateGet.toString());
//					tl(3, "}");
//					tl(2, "}");
//					tl(2, "return j;");

//				if(classApiMethod.contains("POST") || classApiMethod.contains("PUT"))
//					tl(2, "SiteRequest siteRequest = o.getSiteRequest_();");

				tl(2, "return Future.succeededFuture(OperationResponse.completedWithJson(buffer));");
				tl(1, "}");
			}
	
			s(wApiEntities.toString());
//			l();
//			tl(1, "public void handleGet", classSimpleName, "(SiteContext siteContext) {");
//	//		tl(2, "Router siteRouteur = siteContext.getSiteRouteur();");
//			tl(2, "OpenAPI3RouterFactory usineRouteur = siteContext.getUsineRouteur();");
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
//			tl(2, "usineRouteur.addHandlerByOperationId(\"get", classSimpleName, "\", contexteItineraire -> {");
//			Integer tBase = 0;
//			if(classRolesFound && classRoles != null) {
//				String requeteRole = classRoles.get(0);
//				tBase = 6;
//				tl(3, "eventHandler.user().isAuthorized(\"", requeteRole, "\", authRes -> {");
//				tl(4, "try {");
//				tl(5, "if (authRes.result() == Boolean.TRUE) {");
//			}
//			else {
//				tBase = 4;
//				tl(3, "try {");
//			}
//			l();
//			tl(tBase, "contexteItineraire.response().putHeader(\"content-type\", \"application/json\").setChunked(true);");
//			tl(tBase, "SiteRequest siteRequest = genererSiteRequestPour", classSimpleName, "(siteContext);");
//			tl(tBase, "SolrQuery searchSolr = siteRequest.getRechercheSolr();");
//			tl(tBase, "SolrDocumentList resultatsRecherche = siteRequest.getReponseRecherche().getResults();");
//			tl(tBase, "Integer searchLignes = searchSolr.getRows();");
//			l();
//			tl(tBase, "genererGetDebut", classSimpleName, "(siteRequest);");
//			tl(tBase, "for(long i = resultatsRecherche.getStart(); i < resultatsRecherche.getNumFound(); i+=searchLignes) {");
//			tl(tBase + 1, "for(int j = 0; j < resultatsRecherche.size(); j++) {");
//			tl(tBase + 2, "long resultatIndice = i + j;");
//			tl(tBase + 2, "SolrDocument documentSolr = resultatsRecherche.get(j);");
//			tl(tBase + 2, "ResultatRecherche resultatRecherche = new ResultatRecherche();");
//			tl(tBase + 2, "resultatRecherche.setSiteRequest_(siteRequest);");
//			tl(tBase + 2, "resultatRecherche.setDocumentSolr(documentSolr);");
//			tl(tBase + 2, "resultatRecherche.setResultatIndice(resultatIndice);");
//			tl(tBase + 2, "genererGetIndividuel", classSimpleName, "(resultatRecherche);");
//			tl(tBase + 1, "}");
//			tl(tBase, "}");
//			tl(tBase, "genererGetFin", classSimpleName, "(siteRequest);");
//			tl(tBase, "siteRequest.getReponseServeur().end();");
//			if(classRolesFound && classRoles != null) {
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
//			tl(2, "usineRouteur.addFailureHandlerByOperationId(\"get", classSimpleName, "\", contexteItineraire -> {");
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
	//		tl(tBase + 0, "SiteRequest siteRequest = genererSiteRequestPour", classSimpleName, "(siteContext);");
	//		tl(tBase + 0, "HttpServerResponse responseServeur = siteRequest.getReponseServeur();");
	//		tl(tBase + 0, "QueryResponse responseRecherche = siteRequest.getReponseRecherche();");
	//		tl(tBase + 0, "JsonObject requeteJson = contexteItineraire.getBodyAsJson();");
	//		tl(tBase + 0, "SQLClient clientSql = siteRequest.getSiteContext_().getClientSql();");
	//		l();
	//		tl(tBase + 0, "clientSql.getConnection(resultatAsync -> {");
	//		tl(tBase + 1, "if(resultatAsync.succeeded()) {");
	//		tl(tBase + 2, "LocalDateTime modifie = java.time.LocalDateTime.now();");
	//		tl(tBase + 2, "String horodatageStr = Timestamp.valueOf(modifie).toString();");
	//		tl(tBase + 2, "String utilisateurId = siteRequest.getUtilisateurId();");
	//		tl(tBase + 2, "SQLConnection connexionSql = resultatAsync.result();");
	//		l();
	//		tl(tBase + 2, "connexionSql.queryWithParams(");
	//		tl(tBase + 4, "SiteContext.SQL_create");
	//		tl(tBase + 4, ", new JsonArray(Arrays.asList(VAL_canonicalName", classSimpleName, ", utilisateurId))");
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
	//		tl(tBase + 6, "siteRequest.setRequetePk(o.getPk());");
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
	////		tl(tBase, "genererPostDebut", classSimpleName, "(siteRequest);");
	////		tl(tBase, classSimpleName, " nouveau", classSimpleName, " = new ", classSimpleName, "();");
	////		tl(tBase, "nouveau", classSimpleName, ".initLoin", classSimpleName, "(siteRequest);");
	////		tl(tBase, "nouveau", classSimpleName, ".peupler", classSimpleName, "();");
	////		tl(tBase, "post", classSimpleName, "();");
	////		tl(tBase, "genererPostFin", classSimpleName, "(siteRequest);");
	//		tl(tBase, "siteRequest.getReponseServeur().end();");
	//		l();
	//		l();
	//		tl(tBase + 0, "responseServeur.write(\"\\t]\\n\");");
	//		tl(tBase + 0, "responseServeur.write(\"}\\n\");");
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
	
			////////////
			// Erreur //
			////////////
	//		l();
	//		tl(1, "public void genererErreur(SiteRequest siteRequest, Exception e) {");
	//		tl(2, "e.printStackTrace();");
	//		tl(2, "try {");
	//		tl(3, "MimeMessage message = new MimeMessage(siteRequest.SiteContext_.sessionCourriel);");
	//		tl(3, "message.setFrom(new InternetAddress(siteRequest.configSite_.mailAdmin));");
	//		tl(3, "InternetAddress destinaires[] = new InternetAddress[1];");
	//		tl(3, "destinaires[0] = new InternetAddress(siteRequest.configSite_.mailAdmin);");
	//		tl(3, "message.setRecipients(Message.RecipientType.TO, destinaires);");
	//		tl(3, "String nomDomaine = siteRequest.configSite_.nomDomaine;");
	//		tl(3, "String sujet = nomDomaine + \" erreur \" + \" \" + siteRequest.utilisateurNom + \" \" + siteRequest.requeteServeur.getRequestURI();");
	//		tl(3, "String corps = ExceptionUtils.getStackTrace(e);");
	//		tl(3, "message.setSubject(sujet);");
	//		tl(3, "message.setContent(corps, \"text/plain\");");
	//		tl(3, "Transport.send(message);");
	//		tl(3, "String s = e.getMessage();");
	//		tl(3, "siteRequest.getReponseServeur().sendError(500, s);");
	//		tl(2, "} catch(Exception e2) {");
	//		tl(3, "e.printStackTrace();");
	//		tl(2, "}");
	//		tl(1, "}");
			l();
			tl(1, "public String varIndexe", classSimpleName, "(String entityVar) {");
			tl(2, "switch(entityVar) {");
			s(wApiGet.toString());
			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" n'est pas une entité indexé. \", entityVar));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public Future<Void> sql", classSimpleName, "(SiteRequest siteRequest) {");
			tl(2, "Future<Void> future = Future.future();");
			tl(2, "SQLClient clientSql = siteRequest.getSiteContext_().getClientSql();");
			l();
			tl(2, "clientSql.getConnection(sqlAsync -> {");
			tl(3, "if(sqlAsync.succeeded()) {");
			tl(4, "siteRequest.setConnexionSql(sqlAsync.result());");
			tl(4, "future.complete();");
			tl(3, "}");
			tl(2, "});");
			tl(2, "return future;");
			tl(1, "}");
			l();
			tl(1, "// Partagé //");
			l();
	//		tl(1, "public SiteRequest genererSiteRequestPour", classSimpleName, "(SiteContext siteContext, RoutingContext contexteItineraire) {");
			tl(1, "public SiteRequest genererSiteRequestPour", classSimpleName, "(SiteContext siteContext, OperationRequest operationRequest) {");
			tl(2, "Vertx vertx = siteContext.getVertx();");
			tl(2, "SiteRequest siteRequest = new SiteRequest();");
			tl(2, "siteRequest.setVertx(vertx);");
	//		tl(2, "siteRequest.setContexteItineraire(contexteItineraire);");
			tl(2, "siteRequest.setSiteContext_(siteContext);");
			tl(2, "siteRequest.setOperationRequete(operationRequest);");
			tl(2, "siteRequest.initLoinSiteRequest(siteRequest);");
			l();
	
			tl(2, "UtilisateurSite utilisateurSite = new UtilisateurSite();");
			tl(2, "utilisateurSite.initLoinUtilisateurSite(siteRequest);");
			tl(2, "siteRequest.setUtilisateurSite(utilisateurSite);");
			tl(2, "utilisateurSite.setSiteRequest_(siteRequest);");
	
			tl(2, "return siteRequest;");
			tl(1, "}");
			l();
			tl(1, "public Future<Void> definir", classSimpleName, "(", classSimpleName, " o) {");
			tl(2, "Future<Void> future = Future.future();");
			tl(2, "SiteRequest siteRequest = o.getSiteRequest_();");
			tl(2, "SQLConnection connexionSql = siteRequest.getConnexionSql();");
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
			tl(2, "SiteRequest siteRequest = o.getSiteRequest_();");
			tl(2, "SQLConnection connexionSql = siteRequest.getConnexionSql();");
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
			tl(2, "SiteRequest siteRequest = o.getSiteRequest_();");
			tl(2, "try {");
			tl(3, "o.initLoinPourClasse(siteRequest);");
			tl(3, "o.indexerPourClasse();");
			tl(3, "future.complete();");
			tl(2, "} catch(Exception e) {");
			tl(3, "siteRequest.getConnexionSql().close();");
			tl(3, "future.fail(e.getCause());");
			tl(2, "}");
			tl(2, "return future;");
			tl(1, "}");
	
			tl(0, "}");

			writerGenApiServiceImpl.flushClose();
			System.out.println("Write: " + classPathGenApiServiceImpl); 
		}
	}
}
