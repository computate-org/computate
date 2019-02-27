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
					writerGenApiService.s("JsonObject body, ");
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
				String classApiUriMethode = (String)classDoc.get("classApiUri" + classApiMethod + "_frFR_stored_string");
				String classeApiTypeMediaMethode = (String)classDoc.get("classeApiTypeMedia" + classApiMethod + "_stored_string");
				l();
				tl(1, "// ", classApiMethod, " //");
				l();
				tl(1, "@Override");
				t(1, "public void ", classApiOperationIdMethod, "(");
				if(StringUtils.containsAny(classApiMethod, "POST", "PUT", "PATCH"))
					s("JsonObject body, ");
				l("OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {");

				if(classApiMethod.contains("POST")) {
					tl(2, "SiteRequest siteRequest = genererSiteRequestPour", classSimpleName, "(siteContext, operationRequest, body);");
					tl(2, "sql", classSimpleName, "(siteRequest, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "create", classApiMethod, classSimpleName, "(siteRequest, b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, classSimpleName, " ", StringUtils.uncapitalize(classSimpleName), " = b.result();");
					tl(6, "sql", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", c -> {");
					tl(7, "if(c.succeeded()) {");
					tl(8, "definir", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", d -> {");
					tl(9, "if(d.succeeded()) {");
					tl(10, "attribuer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", e -> {");
					tl(11, "if(e.succeeded()) {");
					tl(12, "indexer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", f -> {");
					tl(13, "if(f.succeeded()) {");
					tl(14, "response200", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", g -> {");
					tl(15, "if(f.succeeded()) {");
					tl(16, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
					tl(16, "sqlConnection.commit(h -> {");
					tl(17, "if(a.succeeded()) {");
					tl(18, "sqlConnection.close(i -> {");
					tl(19, "if(a.succeeded()) {");
					tl(20, "eventHandler.handle(Future.succeededFuture(g.result()));");
					tl(19, "} else {");
					tl(20, "erreur", classSimpleName, "(siteRequest, eventHandler, i);");
					tl(19, "}");
					tl(18, "});");
					tl(17, "} else {");
					tl(18, "erreur", classSimpleName, "(siteRequest, eventHandler, h);");
					tl(17, "}");
					tl(16, "});");
					tl(15, "} else {");
					tl(16, "erreur", classSimpleName, "(siteRequest, eventHandler, g);");
					tl(15, "}");
					tl(14, "});");
					tl(13, "} else {");
					tl(14, "erreur", classSimpleName, "(siteRequest, eventHandler, f);");
					tl(13, "}");
					tl(12, "});");
					tl(11, "} else {");
					tl(12, "erreur", classSimpleName, "(siteRequest, eventHandler, e);");
					tl(11, "}");
					tl(10, "});");
					tl(9, "} else {");
					tl(10, "erreur", classSimpleName, "(siteRequest, eventHandler, d);");
					tl(9, "}");
					tl(8, "});");
					tl(7, "} else {");
					tl(8, "erreur", classSimpleName, "(siteRequest, eventHandler, c);");
					tl(7, "}");
					tl(6, "});");
					tl(5, "} else {");
					tl(6, "erreur", classSimpleName, "(siteRequest, eventHandler, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classSimpleName, "(siteRequest, eventHandler, a);");
					tl(3, "}");
					tl(2, "});");
				}
				else if(classApiMethod.contains("PATCH")) {
					tl(2, "SiteRequest siteRequest = genererSiteRequestPour", classSimpleName, "(siteContext, operationRequest, body);");
					tl(2, "sql", classSimpleName, "(siteRequest, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "search", classSimpleName, "(siteRequest, b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, "ListeRecherche<", classSimpleName, "> liste", classSimpleName, " = b.result();");
					tl(6, "liste", classApiMethod, classSimpleName, "(liste", classSimpleName, ", c -> {");
					tl(7, "if(c.succeeded()) {");
					tl(8, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
					tl(8, "sqlConnection.commit(d -> {");
					tl(9, "if(a.succeeded()) {");
					tl(10, "sqlConnection.close(e -> {");
					tl(11, "if(a.succeeded()) {");
					tl(12, "eventHandler.handle(Future.succeededFuture(c.result()));");
					tl(11, "} else {");
					tl(12, "erreur", classSimpleName, "(siteRequest, eventHandler, e);");
					tl(11, "}");
					tl(10, "});");
					tl(9, "} else {");
					tl(10, "erreur", classSimpleName, "(siteRequest, eventHandler, d);");
					tl(9, "}");
					tl(8, "});");
					tl(7, "} else {");
					tl(8, "erreur", classSimpleName, "(siteRequest, eventHandler, c);");
					tl(7, "}");
					tl(6, "});");
					tl(5, "} else {");
					tl(6, "erreur", classSimpleName, "(siteRequest, eventHandler, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classSimpleName, "(siteRequest, eventHandler, a);");
					tl(3, "}");
					tl(2, "});");
				}
				else if(classApiMethod.contains("Recherche")) {
					tl(2, "SiteRequest siteRequest = genererSiteRequestPour", classSimpleName, "(siteContext, operationRequest);");
					tl(2, classApiOperationIdMethod, "(siteRequest, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "ListeRecherche<", classSimpleName, "> liste", classSimpleName, " = a.result();");
					tl(4, "response200", classApiMethod, classSimpleName, "(liste", classSimpleName, ", b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, "eventHandler.handle(Future.succeededFuture(b.result()));");
					tl(5, "} else {");
					tl(6, "erreur", classSimpleName, "(siteRequest, eventHandler, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classSimpleName, "(siteRequest, eventHandler, a);");
					tl(3, "}");
					tl(2, "});");
				}
				else if(classApiMethod.contains("GET")) {
					tl(2, "SiteRequest siteRequest = genererSiteRequestPour", classSimpleName, "(siteContext, operationRequest);");
					tl(2, "search", classSimpleName, "(siteRequest, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "ListeRecherche<", classSimpleName, "> liste", classSimpleName, " = a.result();");
					tl(4, "response200", classApiMethod, classSimpleName, "(liste", classSimpleName, ", b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, "eventHandler.handle(Future.succeededFuture(b.result()));");
					tl(5, "} else {");
					tl(6, "erreur", classSimpleName, "(siteRequest, eventHandler, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classSimpleName, "(siteRequest, eventHandler, a);");
					tl(3, "}");
					tl(2, "});");
				}
				else if(classApiMethod.contains("PUT")) {
					tl(2, "SiteRequest siteRequest = genererSiteRequestPour", classSimpleName, "(siteContext, operationRequest, body);");
					tl(2, "sql", classSimpleName, "(siteRequest, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "remplacer", classApiMethod, classSimpleName, "(siteRequest, b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, classSimpleName, " ", StringUtils.uncapitalize(classSimpleName), " = b.result();");
					tl(6, "sql", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", c -> {");
					tl(7, "if(c.succeeded()) {");
					tl(8, "definir", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", d -> {");
					tl(9, "if(d.succeeded()) {");
					tl(10, "attribuer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", e -> {");
					tl(11, "if(e.succeeded()) {");
					tl(12, "indexer", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", f -> {");
					tl(13, "if(f.succeeded()) {");
					tl(14, "response200", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", g -> {");
					tl(15, "if(g.succeeded()) {");
					tl(16, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
					tl(16, "sqlConnection.commit(h -> {");
					tl(17, "if(a.succeeded()) {");
					tl(18, "sqlConnection.close(i -> {");
					tl(19, "if(a.succeeded()) {");
					tl(20, "eventHandler.handle(Future.succeededFuture(g.result()));");
					tl(19, "} else {");
					tl(20, "erreur", classSimpleName, "(siteRequest, eventHandler, i);");
					tl(19, "}");
					tl(18, "});");
					tl(17, "} else {");
					tl(18, "erreur", classSimpleName, "(siteRequest, eventHandler, h);");
					tl(17, "}");
					tl(16, "});");
					tl(15, "} else {");
					tl(16, "erreur", classSimpleName, "(siteRequest, eventHandler, g);");
					tl(15, "}");
					tl(14, "});");
					tl(13, "} else {");
					tl(14, "erreur", classSimpleName, "(siteRequest, eventHandler, f);");
					tl(13, "}");
					tl(12, "});");
					tl(11, "} else {");
					tl(12, "erreur", classSimpleName, "(siteRequest, eventHandler, e);");
					tl(11, "}");
					tl(10, "});");
					tl(9, "} else {");
					tl(10, "erreur", classSimpleName, "(siteRequest, eventHandler, d);");
					tl(9, "}");
					tl(8, "});");
					tl(7, "} else {");
					tl(8, "erreur", classSimpleName, "(siteRequest, eventHandler, c);");
					tl(7, "}");
					tl(6, "});");
					tl(5, "} else {");
					tl(6, "erreur", classSimpleName, "(siteRequest, eventHandler, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classSimpleName, "(siteRequest, eventHandler, a);");
					tl(3, "}");
					tl(2, "});");
				}
				else if(classApiMethod.contains("DELETE")) {
					tl(2, "SiteRequest siteRequest = genererSiteRequestPour", classSimpleName, "(siteContext, operationRequest);");
					tl(2, "sql", classSimpleName, "(siteRequest, a -> {");
					tl(3, "if(a.succeeded()) {");
					tl(4, "search", classSimpleName, "(siteRequest, b -> {");
					tl(5, "if(b.succeeded()) {");
					tl(6, "ListeRecherche<", classSimpleName, "> liste", classSimpleName, " = b.result();");
					tl(6, "supprimer", classApiMethod, classSimpleName, "(siteRequest, c -> {");
					tl(7, "if(c.succeeded()) {");
					tl(8, "response200", classApiMethod, classSimpleName, "(siteRequest, d -> {");
					tl(9, "if(d.succeeded()) {");
					tl(10, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
					tl(10, "sqlConnection.commit(e -> {");
					tl(11, "if(a.succeeded()) {");
					tl(12, "sqlConnection.close(f -> {");
					tl(13, "if(a.succeeded()) {");
					tl(14, "eventHandler.handle(Future.succeededFuture(d.result()));");
					tl(13, "} else {");
					tl(14, "erreur", classSimpleName, "(siteRequest, eventHandler, f);");
					tl(13, "}");
					tl(12, "});");
					tl(11, "} else {");
					tl(12, "erreur", classSimpleName, "(siteRequest, eventHandler, e);");
					tl(11, "}");
					tl(10, "});");
					tl(9, "} else {");
					tl(10, "erreur", classSimpleName, "(siteRequest, eventHandler, d);");
					tl(9, "}");
					tl(8, "});");
					tl(7, "} else {");
					tl(8, "erreur", classSimpleName, "(siteRequest, eventHandler, c);");
					tl(7, "}");
					tl(6, "});");
					tl(5, "} else {");
					tl(6, "erreur", classSimpleName, "(siteRequest, eventHandler, b);");
					tl(5, "}");
					tl(4, "});");
					tl(3, "} else {");
					tl(4, "erreur", classSimpleName, "(siteRequest, eventHandler, a);");
					tl(3, "}");
					tl(2, "});");
				}
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
					tl(1, "public void ", classApiOperationIdMethod, "(SiteRequest siteRequest, Handler<AsyncResult<ListeRecherche<", classSimpleName, ">>> eventHandler) {");
					tl(2, "try {");
					tl(3, "OperationRequest operationRequest = siteRequest.getOperationRequete();");
					tl(3, "String entiteListeStr = siteRequest.getOperationRequete().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
					tl(3, "String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(", q(",\\s*"), ");");
					tl(3, "ListeRecherche<", classSimpleName, "> listeRecherche = new ListeRecherche<", classSimpleName, ">();");
					tl(3, "listeRecherche.setQuery(\"*:*\");");
					tl(3, "listeRecherche.setC(", classSimpleName, ".class);");
					tl(3, "listeRecherche.setRows(1000000);");
					tl(3, "if(entiteListe != null)");
					tl(3, "listeRecherche.setFields(entiteListe);");
					tl(3, "listeRecherche.addSort(\"partNumero_indexed_int\", ORDER.asc);");
					tl(3, "operationRequest.getParams().getJsonObject(\"query\").forEach(queryParam -> {");
					tl(4, "String entityVar = null;");
					tl(4, "String valeurIndexe = null;");
					tl(4, "String varIndexe = null;");
					tl(4, "String valeurTri = null;");
					tl(4, "Integer searchDebut = null;");
					tl(4, "Integer searchNum = null;");
					tl(4, "String paramNom = queryParam.getKey();");
					tl(4, "Object paramValuesObject = queryParam.getValue();");
					tl(4, "JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);");
					l();
					tl(4, "for(Object paramObject : paramObjects) {");
					tl(5, "switch(paramNom) {");
			
					tl(6, "case \"q\":");
					tl(7, "entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, \":\"));");
					tl(7, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObject, \":\"));");
					tl(7, "varIndexe = \"*\".equals(entityVar) ? entityVar : varIndexe", classSimpleName, "(entityVar);");
					tl(7, "listeRecherche.setQuery(varIndexe + \":\" + (\"*\".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));");
					tl(7, "break;");
			
					tl(6, "case \"fq\":");
					tl(7, "entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, \":\"));");
					tl(7, "valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObject, \":\"));");
					tl(7, "varIndexe = varIndexe", classSimpleName, "(entityVar);");
					tl(7, "listeRecherche.addFilterQuery(varIndexe + \":\" + ClientUtils.escapeQueryChars(valeurIndexe));");
					tl(7, "break;");
			
					tl(6, "case \"sort\":");
					tl(7, "entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, \" \"));");
					tl(7, "valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObject, \" \"));");
					tl(7, "varIndexe = varIndexe", classSimpleName, "(entityVar);");
					tl(7, "listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));");
					tl(7, "break;");
			
					tl(6, "case \"fl\":");
					tl(7, "entityVar = StringUtils.trim((String)paramObject);");
					tl(7, "varIndexe = varIndexe", classSimpleName, "(entityVar);");
					tl(7, "listeRecherche.addField(varIndexe);");
					tl(7, "break;");
			
					tl(6, "case \"start\":");
					tl(7, "searchDebut = (Integer)paramObject;");
					tl(7, "listeRecherche.setStart(searchDebut);");
					tl(7, "break;");
			
					tl(6, "case \"rows\":");
					tl(7, "searchNum = (Integer)paramObject;");
					tl(7, "listeRecherche.setRows(searchNum);");
					tl(7, "break;");
			
					tl(5, "}");
			
					tl(4, "}");
					tl(3, "});");
					tl(3, "listeRecherche.initLoinPourClasse(siteRequest);");
					tl(3, "eventHandler.handle(Future.succeededFuture(listeRecherche));");
					tl(2, "} catch(Exception e) {");
					tl(3, "eventHandler.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
				if(classApiMethod.contains("POST")) {
					l();
					tl(1, "public void create", classApiMethod, classSimpleName, "(SiteRequest siteRequest, Handler<AsyncResult<", classSimpleName, ">> eventHandler) {");
					tl(2, "try {");
					tl(3, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
					tl(3, "String utilisateurId = siteRequest.getUtilisateurId();");
					l();
					tl(3, "sqlConnection.queryWithParams(");
					tl(5, "SiteContext.SQL_create");
					tl(5, ", new JsonArray(Arrays.asList(", classSimpleName, ".class.getCanonicalName(), utilisateurId))");
					tl(5, ", createAsync");
					tl(3, "-> {");
					tl(4, "JsonArray patchLigne = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);");
					tl(4, "Long ", classVarPrimaryKey, " = patchLigne.getLong(0);");
					tl(4, classSimpleName, " o = new ", classSimpleName, "();");
					tl(4, "o.set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, ");");
					tl(4, "o.initLoin", classSimpleName, "(siteRequest);");
					tl(4, "eventHandler.handle(Future.succeededFuture(o));");
					tl(3, "});");
					tl(2, "} catch(Exception e) {");
					tl(3, "eventHandler.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
				if(classApiMethod.contains("PATCH")) {
					l();
					tl(1, "public Future<OperationResponse> liste", classApiMethod, classSimpleName, "(ListeRecherche<", classSimpleName, "> liste", classSimpleName, ", Handler<AsyncResult<OperationResponse>> eventHandler) {");
//					tl(2, "List<Future> futures = new ArrayList<>();");
//					tl(2, "liste", classSimpleName, ".getList().forEach(o -> {");
//					tl(3, "futures.add(");
//					tl(4, "sql", classApiMethod, classSimpleName, "(o).compose(");
//					tl(5, "b -> indexer", classSimpleName, "(o)");
//					tl(4, ")");
//					tl(3, ");");
//					tl(2, "});");
//					tl(2, "Future<OperationResponse> future = CompositeFuture.all(futures).compose( a -> ");
//					tl(3, "response200", classApiMethod, classSimpleName, "(liste", classSimpleName, ")");
//					tl(2, ");");
//					tl(2, "return future;");
					tl(2, "return null;");
					tl(1, "}");
					l();
					tl(1, "public void sql", classApiMethod, classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> eventHandler) {");
					tl(2, "try {");
					tl(3, "SiteRequest siteRequest = o.getSiteRequest_();");
					tl(3, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
					tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
					tl(3, "JsonObject requeteJson = siteRequest.getJsonObject();");
					tl(3, "StringBuilder patchSql = new StringBuilder();");
					tl(3, "List<Object> patchSqlParams = new ArrayList<Object>();");
					tl(3, "Set<String> methodeNoms = requeteJson.fieldNames();");
					l();
					tl(3, "for(String methodeNom : methodeNoms) {");
					tl(4, "switch(methodeNom) {");
					s(wApiGeneratePatch.toString());
					tl(4, "}");
					tl(3, "}");
					tl(3, "sqlConnection.queryWithParams(");
					tl(5, "patchSql.toString()");
					tl(5, ", new JsonArray(patchSqlParams)");
					tl(5, ", patchAsync");
					tl(3, "-> {");
					tl(4, "eventHandler.handle(Future.succeededFuture());");
					tl(3, "});");
					tl(2, "} catch(Exception e) {");
					tl(3, "eventHandler.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
				if(classApiMethod.contains("PUT")) {
					l();
					tl(1, "public void remplacer", classApiMethod, classSimpleName, "(SiteRequest siteRequest, Handler<AsyncResult<", classSimpleName, ">> eventHandler) {");
					tl(2, "try {");
					tl(3, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
					tl(3, "String utilisateurId = siteRequest.getUtilisateurId();");
					tl(3, "Long pk = siteRequest.getRequetePk();");
					l();
					tl(3, "sqlConnection.queryWithParams(");
					tl(5, "SiteContext.SQL_vider");
					tl(5, ", new JsonArray(Arrays.asList(pk, ", classSimpleName, ".class.getCanonicalName(), pk, pk, pk))");
					tl(5, ", remplacerAsync");
					tl(3, "-> {");
					tl(4, classSimpleName, " o = new ", classSimpleName, "();");
					tl(4, "o.set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, ");");
					tl(4, "eventHandler.handle(Future.succeededFuture(o));");
					tl(3, "});");
					tl(2, "} catch(Exception e) {");
					tl(3, "eventHandler.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
				if(classApiMethod.contains("POST") || classApiMethod.contains("PUT")) {
					l();
					tl(1, "public void sql", classApiMethod, classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> eventHandler) {");
					tl(2, "try {");
					tl(3, "SiteRequest siteRequest = o.getSiteRequest_();");
					tl(3, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
					tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
					tl(3, "JsonObject jsonObject = siteRequest.getJsonObject();");
					tl(3, "StringBuilder postSql = new StringBuilder();");
					tl(3, "List<Object> postSqlParams = new ArrayList<Object>();");
					l();
					tl(3, "if(jsonObject != null) {");
					tl(4, "Set<String> entityVars = jsonObject.fieldNames();");
					tl(4, "for(String entityVar : entityVars) {");
					tl(5, "switch(entityVar) {");
					s(wApiGeneratePost.toString());
					tl(5, "}");
					tl(4, "}");
					tl(3, "}");
					tl(3, "sqlConnection.queryWithParams(");
					tl(5, "postSql.toString()");
					tl(5, ", new JsonArray(postSqlParams)");
					tl(5, ", postAsync");
					tl(3, "-> {");
					tl(4, "eventHandler.handle(Future.succeededFuture());");
					tl(3, "});");
					tl(2, "} catch(Exception e) {");
					tl(3, "eventHandler.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
				if(classApiMethod.contains("GET")) {
				}
				if(classApiMethod.contains("DELETE")) {
					l();
					tl(1, "public void supprimer", classApiMethod, classSimpleName, "(SiteRequest siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {");
					tl(2, "try {");
					tl(3, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
					tl(3, "String utilisateurId = siteRequest.getUtilisateurId();");
					tl(3, "Long pk = siteRequest.getRequetePk();");
					l();
					tl(3, "sqlConnection.queryWithParams(");
					tl(5, "SiteContext.SQL_supprimer");
					tl(5, ", new JsonArray(Arrays.asList(pk, ", classSimpleName, ".class.getCanonicalName(), pk, pk, pk, pk))");
					tl(5, ", supprimerAsync");
					tl(3, "-> {");
					tl(4, "eventHandler.handle(Future.succeededFuture());");
					tl(3, "});");
					tl(2, "} catch(Exception e) {");
					tl(3, "eventHandler.handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
				l();
				t(1, "public void response200", classApiMethod, classSimpleName, "(");

				if(classApiMethod.contains("POST") || classApiMethod.contains("PUT"))
					s(classSimpleName, " o");
				else if(classApiMethod.contains("DELETE"))
					s("SiteRequest siteRequest");
				else
					s("ListeRecherche<", classSimpleName, "> liste", classSimpleName);

				l(", Handler<AsyncResult<OperationResponse>> eventHandler) {");

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
						tl(3, "page.setPageUrl(\"", siteBaseUrl, classApiUri, "\");");
						tl(3, "SolrDocument pageDocumentSolr = new SolrDocument();");
						l();
						tl(3, "pageDocumentSolr.setField(", q("pageUri_frFR_stored_string"), ", ", q(classApiUriMethode), ");");
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
						tl(4, classSimpleName, " o = liste", classSimpleName, ".getList().get(i);");
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
						tl(3, "page.setPageUrl(\"", siteBaseUrl, classApiUri, "\");");
						tl(3, "SolrDocument pageDocumentSolr = new SolrDocument();");
						tl(3, "SiteRequest siteRequest = liste", classSimpleName, ".getSiteRequest_();");
						l();
						tl(3, "pageDocumentSolr.setField(", q("pageUri_frFR_stored_string"), ", ", q(classApiUriMethode), ");");
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
					tl(3, "eventHandler.handle(Future.succeededFuture(new OperationResponse(200, \"OK\", buffer, new CaseInsensitiveHeaders())));");
				}
				else {
					tl(3, "eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));");
				}

				tl(2, "} catch(Exception e) {");
				tl(3, "eventHandler.handle(Future.failedFuture(e));");
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
			tl(1, "// Partagé //");
			l();
			tl(1, "public void erreur", classSimpleName, "(SiteRequest siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultatAsync) {");
			tl(2, "Throwable e = resultatAsync.cause();");
			tl(2, "ExceptionUtils.printRootCauseStackTrace(e);");
			tl(2, "OperationResponse operationResponse = new OperationResponse(400, \"BAD REQUEST\", ");
			tl(3, "Buffer.buffer().appendString(");
			tl(4, "new JsonObject() {{");
			tl(5, "put(\"erreur\", new JsonObject() {{");
			tl(5, "put(\"message\", e.getMessage());");
			tl(5, "}});");
			tl(4, "}}.encodePrettily()");
			tl(3, ")");
			tl(3, ", new CaseInsensitiveHeaders()");
			tl(2, ");");
			tl(2, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
			tl(2, "if(sqlConnection != null) {");
			tl(3, "sqlConnection.rollback(a -> {");
			tl(4, "if(a.succeeded()) {");
			tl(5, "sqlConnection.close(b -> {");
			tl(6, "if(a.succeeded()) {");
			tl(7, "eventHandler.handle(Future.succeededFuture(operationResponse));");
			tl(6, "} else {");
			tl(7, "eventHandler.handle(Future.succeededFuture(operationResponse));");
			tl(6, "}");
			tl(5, "});");
			tl(4, "} else {");
			tl(5, "eventHandler.handle(Future.succeededFuture(operationResponse));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} else {");
			tl(3, "eventHandler.handle(Future.succeededFuture(operationResponse));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void sql", classSimpleName, "(SiteRequest siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {");
			tl(2, "try {");
			tl(3, "SQLClient clientSql = siteRequest.getSiteContext_().getClientSql();");
			l();
			tl(3, "clientSql.getConnection(sqlAsync -> {");
			tl(4, "if(sqlAsync.succeeded()) {");
			tl(5, "SQLConnection sqlConnection = sqlAsync.result();");
			tl(5, "sqlConnection.setAutoCommit(false, a -> {");
			tl(6, "if(a.succeeded()) {");
			tl(7, "siteRequest.setConnexionSql(sqlConnection);");
			tl(7, "eventHandler.handle(Future.succeededFuture());");
			tl(6, "} else {");
			tl(7, "eventHandler.handle(Future.failedFuture(a.cause()));");
			tl(6, "}");
			tl(5, "});");
			tl(4, "} else {");
			tl(5, "eventHandler.handle(Future.failedFuture(sqlAsync.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "eventHandler.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
	//		tl(1, "public SiteRequest genererSiteRequestPour", classSimpleName, "(SiteContext siteContext, RoutingContext contexteItineraire) {");
			tl(1, "public SiteRequest genererSiteRequestPour", classSimpleName, "(SiteContext siteContext, OperationRequest operationRequest) {");
			tl(2, "return genererSiteRequestPour", classSimpleName, "(siteContext, operationRequest, null);");
			tl(1, "}");
			l();
			tl(1, "public SiteRequest genererSiteRequestPour", classSimpleName, "(SiteContext siteContext, OperationRequest operationRequest, JsonObject body) {");
			tl(2, "Vertx vertx = siteContext.getVertx();");
			tl(2, "SiteRequest siteRequest = new SiteRequest();");
			tl(2, "siteRequest.setJsonObject(body);");
			tl(2, "siteRequest.setVertx(vertx);");
	//		tl(2, "siteRequest.setContexteItineraire(contexteItineraire);");
			tl(2, "siteRequest.setSiteContext_(siteContext);");
			tl(2, "siteRequest.setSiteConfig_(siteContext.getSiteConfig());");
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
			tl(1, "public void definir", classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> eventHandler) {");
			tl(2, "try {");
			tl(3, "SiteRequest siteRequest = o.getSiteRequest_();");
			tl(3, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
			tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
			tl(3, "sqlConnection.queryWithParams(");
			tl(5, "SiteContext.SQL_definir");
			tl(5, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, "))");
			tl(5, ", definirAsync");
			tl(3, "-> {");
			tl(4, "if(definirAsync.succeeded()) {");
			tl(5, "for(JsonArray definition : definirAsync.result().getResults()) {");
			tl(6, "o.definirPourClasse(definition.getString(0), definition.getString(1));");
			tl(5, "}");
			tl(5, "eventHandler.handle(Future.succeededFuture());");
			tl(4, "} else {");
			tl(5, "eventHandler.handle(Future.failedFuture(definirAsync.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "eventHandler.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void attribuer", classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> eventHandler) {");
			tl(2, "try {");
			tl(3, "SiteRequest siteRequest = o.getSiteRequest_();");
			tl(3, "SQLConnection sqlConnection = siteRequest.getConnexionSql();");
			tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
			tl(3, "sqlConnection.queryWithParams(");
			tl(5, "SiteContext.SQL_attribuer");
			tl(5, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, ", ", classVarPrimaryKey, "))");
			tl(5, ", attribuerAsync");
			tl(3, "-> {");
			tl(4, "if(attribuerAsync.succeeded()) {");
			tl(5, "if(attribuerAsync.result() != null) {");
			tl(6, "for(JsonArray definition : attribuerAsync.result().getResults()) {");
			tl(7, "o.attribuerPourClasse(definition.getString(0), definition.getString(1));");
			tl(6, "}");
			tl(5, "}");
			tl(5, "eventHandler.handle(Future.succeededFuture());");
			tl(4, "} else {");
			tl(5, "eventHandler.handle(Future.failedFuture(attribuerAsync.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "eventHandler.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void indexer", classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> eventHandler) {");
			tl(2, "SiteRequest siteRequest = o.getSiteRequest_();");
			tl(2, "try {");
			tl(3, "o.initLoinPourClasse(siteRequest);");
			tl(3, "o.indexerPourClasse();");
			tl(3, "eventHandler.handle(Future.succeededFuture());");
			tl(2, "} catch(Exception e) {");
			tl(3, "eventHandler.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
	
			tl(0, "}");

			writerGenApiServiceImpl.flushClose();
			System.out.println("Write: " + classPathGenApiServiceImpl); 
		}
	}
}
