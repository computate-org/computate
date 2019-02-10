package org.computate.enUS.java;

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

	protected List<String> classEntityVars;

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
				String classPageCanonicalNameMethod = (String)classDoc.get("classePageNomCanonique" + classApiMethod + "_frFR_stored_string");
				String classPageSimpleNameMethod = (String)classDoc.get("classePageNomSimple" + classApiMethod + "_frFR_stored_string");
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
					tl(2, "Future<OperationResponse> etapesFutures = ", classApiOperationIdMethod, "(siteRequest).compose(liste", classSimpleName, " -> ");
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
					tl(1, "public Future<ListeRecherche<", classSimpleName, ">> ", classApiOperationIdMethod, "(SiteRequest siteRequest) {");
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

				tl(2, "try {");
				tl(3, "Buffer buffer = Buffer.buffer();");

				if(classApiMethod.contains("POST") || classApiMethod.contains("PUT")) {
					tl(3, "SiteRequest siteRequest = o.getSiteRequest_();");
				}
				else if(classApiMethod.contains("Recherche")) {
					tl(3, "SiteRequest siteRequest = liste", classSimpleName, ".getSiteRequest_();");
				}
				else {
				}

				t(3, "AllWriter w = AllWriter.create(");
				if(classApiMethod.contains("POST") || classApiMethod.contains("PUT"))
					s("o.getSiteRequest_()");
				else if(classApiMethod.contains("DELETE"))
					s("siteRequest");
				else
					s("liste", classSimpleName, ".getSiteRequest_()");
				l(", buffer);");


				if(classApiMethod.contains("GET")) {
					tl(3, "SolrDocumentList documentsSolr = liste", classSimpleName, ".getSolrDocumentList();");
					l();
				}
				if(classApiMethod.contains("Recherche")) {
				}

				if(classApiMethod.contains("Recherche") || classApiMethod.contains("GET")) {
				}
				else if(classApiMethod.contains("DELETE")) {
				}

				if(classApiMethod.contains("Recherche")) {
					if(classPageCanonicalNameMethod != null) {
						tl(3, classPageSimpleNameMethod, " page = new ", classPageSimpleNameMethod, "();");
						tl(3, "SolrDocument pageDocumentSolr = new SolrDocument();");
						l();
						tl(3, "pageDocumentSolr.setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
						tl(3, "page.setPageDocumentSolr(pageDocumentSolr);");
						tl(3, "page.setW(w);");
						tl(3, "page.setListe", classSimpleName, "(liste", classSimpleName, ");");
						tl(3, "page.initLoin", classPageSimpleNameMethod, "(siteRequest);");
						tl(3, "page.html();");
					}
					else {
						tl(3, "QueryResponse responseRecherche = liste", classSimpleName, ".getQueryResponse();");
						tl(3, "SolrDocumentList documentsSolr = liste", classSimpleName, ".getSolrDocumentList();");
						tl(3, "Long millisRecherche = Long.valueOf(responseRecherche.getQTime());");
						tl(3, "Long millisTransmission = responseRecherche.getElapsedTime();");
						tl(3, "Long numCommence = responseRecherche.getResults().getStart();");
						tl(3, "Long numTrouve = responseRecherche.getResults().getNumFound();");
						tl(3, "Integer numRetourne = responseRecherche.getResults().size();");
						tl(3, "String tempsRecherche = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));");
						tl(3, "String tempsTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
						tl(3, "Exception exceptionRecherche = responseRecherche.getException();");
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
	//					tl(5, "String entityVarStocke = champNoms.get(j);");
	//					tl(5, "List<Object> entiteValeurs = new ArrayList<>(documentSolr.getFieldValues(entityVarStocke));");
						s(wApiGenerateGet.toString());
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
				if(classApiMethod.contains("GET")) {
					if(classPageCanonicalNameMethod != null) {
						tl(3, classPageSimpleNameMethod, " page = new ", classPageSimpleNameMethod, "();");
						tl(3, "SolrDocument pageDocumentSolr = new SolrDocument();");
						tl(3, "SiteRequest siteRequest = liste", classSimpleName, ".getSiteRequest_();");
						l();
						tl(3, "pageDocumentSolr.setField(", q("pageUri_frFR_stored_string"), ", ", q(classeApiUriMethode), ");");
						tl(3, "page.setPageDocumentSolr(pageDocumentSolr);");
						tl(3, "page.setW(w);");
						tl(3, "page.initLoin", classPageSimpleNameMethod, "(siteRequest);");
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
	//					tl(5, "String entityVarStocke = champNoms.get(j);");
	//					tl(5, "List<Object> entiteValeurs = new ArrayList<>(documentSolr.getFieldValues(entityVarStocke));");
						s(wApiGenerateGet.toString());
	//					tl(4, "}");
						l();
						tl(4, "w.l(", q("}"), ");");
						tl(3, "}");
					}
				}

				if((classApiMethod.contains("GET") || classApiMethod.contains("Recherche")) && classPageCanonicalNameMethod != null) {
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
	
			s(wApiEntities.toString());
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
