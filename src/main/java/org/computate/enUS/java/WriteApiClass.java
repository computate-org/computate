package org.computate.enUS.java;

import java.net.URLDecoder;
import java.util.ArrayList;
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

	protected Boolean classPageSimple;

	public void  apiCodeClassBegin(String languageName) throws Exception, Exception {
	}

	public void  writeGenApiService(String languageName) throws Exception, Exception {
		if(writerGenApiService != null) {
			writerGenApiService.l("package ", classPackageName, ";");
			writerGenApiService.l();
			if(classPartsSiteContext == null)
				throw new Exception("Ajouter une classe avec le commentaire: MotCle: classSimpleNameSiteContext");
			writerGenApiService.l("import ", classPartsSiteContext.solrDocument.get("classCanonicalName_" + languageName + "_stored_string"), ";");
//			writerGenApiService.l("import ", classPackageName, ".", classSimpleName, "ApiServiceVertxEBProxy;");
			writerGenApiService.l("import io.vertx.codegen.annotations.ProxyGen;");
			writerGenApiService.l("import io.vertx.ext.web.api.generator.WebApiServiceGen;");
			writerGenApiService.l("import io.vertx.serviceproxy.ServiceBinder;");
			writerGenApiService.l("import io.vertx.core.AsyncResult;");
			writerGenApiService.l("import io.vertx.core.Handler;");
			writerGenApiService.l("import io.vertx.core.Vertx;");
			writerGenApiService.l("import io.vertx.core.json.JsonObject;");
			writerGenApiService.l("import io.vertx.core.json.JsonArray;");
			writerGenApiService.l("import io.vertx.ext.web.api.OperationRequest;");
			writerGenApiService.l("import io.vertx.ext.web.api.OperationResponse;");
			writerGenApiService.l();
			writerGenApiService.l("/**");
			writerGenApiService.l(" * ", str_Traduire(languageName), ": false");
			writerGenApiService.l(" * Gen: false");
			writerGenApiService.l(" **/");
			writerGenApiService.l("@WebApiServiceGen");
			writerGenApiService.l("@ProxyGen");
			writerGenApiService.s("public interface ", classSimpleNameGenApiService, " {");
			writerGenApiService.l();
//			writerGenApiService.tl(1, "// A factory method to create an instance and a proxy. ");
			writerGenApiService.tl(1, "static void ", str_enregistrer(languageName), "Service(", classPartsSiteContext.simpleName(languageName), " ", str_siteContext(languageName), ", Vertx vertx) {");
			writerGenApiService.tl(2, "new ServiceBinder(vertx).setAddress(", q(languageName, classSimpleName), ").register(", classSimpleNameGenApiService, ".class, new ", classSimpleNameApiServiceImpl, "(", str_siteContext(languageName), "));");
			writerGenApiService.tl(1, "}");
			writerGenApiService.l();
//			writerGenApiService.tl(1, "// A factory method to create an instance and a proxy. ");
			writerGenApiService.tl(1, "static ", classSimpleNameGenApiService, " ", str_creer(languageName), "(", classPartsSiteContext.simpleName(languageName), " ", str_siteContext(languageName), ", Vertx vertx) {");
			writerGenApiService.tl(2, "return new ", classSimpleNameApiServiceImpl, "(", str_siteContext(languageName), ");");
			writerGenApiService.tl(1, "}");
			writerGenApiService.l();
			writerGenApiService.tl(1, "// A factory method to create an instance and a proxy. ");
			writerGenApiService.tl(1, "static ", classSimpleNameGenApiService, " ", str_creer(languageName), "Proxy(Vertx vertx, String ", str_address(languageName), ") {");
			writerGenApiService.tl(2, "return new ", classSimpleNameGenApiService, "VertxEBProxy(vertx, ", str_address(languageName), ");");
			writerGenApiService.tl(1, "}");
			writerGenApiService.l();
			for(String classApiMethod : classApiMethods) {
				String classApiOperationIdMethod = (String)classDoc.get("classApiOperationId" + classApiMethod + "_" + languageName + "_stored_string");
				String classPageCanonicalNameMethod = (String)classDoc.get("classPageCanonicalName" + classApiMethod + "_" + languageName + "_stored_string");
				String classPageLanguageName = (String)classDoc.get("classPageLanguageName" + classApiMethod + "_" + languageName + "_stored_string");

				if(classPageLanguageName == null || classPageLanguageName.equals(languageName)) {
					if(classPageCanonicalNameMethod != null) {
						writerGenApiService.t(1, "public void ", classApiOperationIdMethod, "Id(");
						if(StringUtils.containsAny(classApiMethod, "POST", "PUT", "PATCH"))
							writerGenApiService.s("JsonObject body, ");
						writerGenApiService.l("OperationRequest ", str_operationRequest(languageName), ", Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ");");
					}
	
					writerGenApiService.t(1, "public void ", classApiOperationIdMethod, "(");
					if(StringUtils.containsAny(classApiMethod, "POST", "PUT", "PATCH"))
						writerGenApiService.s("JsonObject body, ");
					writerGenApiService.l("OperationRequest ", str_operationRequest(languageName), ", Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ");");
				}
			}
			writerGenApiService.tl(0, "}");

			writerGenApiService.flushClose();
		}
	}

	public void  writeApiServiceImpl(String languageName) throws Exception, Exception {
		if(writerApiServiceImpl != null) {
			writerApiServiceImpl.l("package ", classPackageName, ";");
			writerApiServiceImpl.l();
			writerApiServiceImpl.l("import ", classPartsSiteContext.solrDocument.get("classCanonicalName_" + languageName + "_stored_string"), ";");
//			auteurGenApiService.l("import ", classPackageName, ".", classSimpleName, "ApiServiceVertxEBProxy;");
			writerApiServiceImpl.l();
			writerApiServiceImpl.l("/**");
			writerApiServiceImpl.l(" * ", str_Translate(languageName), ": false");
			writerApiServiceImpl.l(" **/");
			writerApiServiceImpl.l("public class ", classSimpleNameApiServiceImpl, " extends ", classSimpleNameGenApiServiceImpl, " {");
			writerApiServiceImpl.l();
			writerApiServiceImpl.tl(1, "public ", classSimpleNameApiServiceImpl, "(", classPartsSiteContext.simpleName(languageName), " ", str_siteContext(languageName), ") {");
			writerApiServiceImpl.tl(2, "super(", str_siteContext(languageName), ");");
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
			if(classImportsGenApi.size() > 0) { 
				for(String classeImportation : classImportsGenApi) {
					l("import ", classeImportation, ";");
				}
				l();
			}

			{
				SolrQuery solrSearch = new SolrQuery();   
				solrSearch.setQuery("*:*");
				solrSearch.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + entitySuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				solrSearch.addFilterQuery("partIsEntity_indexed_boolean:true");
				solrSearch.addFilterQuery("classCanonicalName_" + languageActualName + "_indexed_string:" + fqClassesSuperEtMoi);
				QueryResponse searchReponse = solrClientComputate.query(solrSearch);
				SolrDocumentList searchList = searchReponse.getResults();
				Integer searchLines = solrSearch.getRows();
	
				if(searchList.size() > 0) {
					for(Long i = searchList.getStart(); i < searchList.getNumFound(); i+=searchLines) {
						for(Integer j = 0; j < searchList.size(); j++) {
							SolrDocument doc = searchList.get(j);
							entityVar = (String)doc.get("entityVar_" + languageName + "_stored_string");
							entityVarCapitalized = (String)doc.get("entityVarCapitalized_" + languageName + "_stored_string");
							entityAttribute = BooleanUtils.isTrue((Boolean)doc.get("entityAttribute_stored_boolean"));
							entityAttributeVar = (String)doc.get("entityAttributeVar_" + languageName + "_stored_string");
							entityDefine = (Boolean)doc.get("entityDefine_stored_boolean");
							entityTypeSuffix = (String)doc.get("entityTypeSuffix_stored_string");
							entityIndexed = (Boolean)doc.get("entityIndexed_stored_boolean");
							entityStored = (Boolean)doc.get("entityStored_stored_boolean");
							entitySolrCanonicalName = (String)doc.get("entitySolrCanonicalName_stored_string");
							entitySolrSimpleName = (String)doc.get("entitySolrSimpleName_stored_string");
							entitySimpleNameVertxJson = (String)doc.get("entitySimpleNameVertxJson_stored_string");
							entityCanonicalNameVertxJson = (String)doc.get("entityCanonicalNameVertxJson_stored_string");
							entityListSimpleNameVertxJson = (String)doc.get("entityListSimpleNameVertxJson_stored_string");
							entityListCanonicalNameVertxJson = (String)doc.get("entityListCanonicalNameVertxJson_stored_string");
							entityCanonicalName = (String)doc.get("entityCanonicalName_" + languageName + "_stored_string");
							entityCanonicalNameGeneric = (String)doc.get("entityCanonicalNameGeneric_" + languageName + "_stored_string");
							entitySimpleNameComplete = (String)doc.get("entitySimpleNameComplete_" + languageName + "_stored_string");
							entitySimpleNameCompleteGeneric = (String)doc.get("entitySimpleNameCompleteGeneric_" + languageName + "_stored_string");
							entitySimpleName = (String)doc.get("entitySimpleName_" + languageName + "_stored_string");
	
							/////////////////
							// codeApiGet //
							/////////////////

							if(classIndexed && entityIndexed) {
								wApiGet.tl(3, "case \"", entityVar, "\":");
								wApiGet.tl(4, "return \"", entityVar, "_indexed", entityTypeSuffix, "\";");
							}
							
							///////////////////////
							// codeApiGenererGet //
							///////////////////////
							o = wApiGenerateGet;
							if(classIndexed && entityStored) {
				//				tl(4, "if(", q(entityVar, "_stored", entityTypeSuffix), ".equals(entityVarStored)) {");
								if(StringUtils.equalsAny(entityCanonicalName, VAL_canonicalNameList, VAL_canonicalNameArrayList, VAL_canonicalNameSet, VAL_canonicalNameHashSet)) {
									if(VAL_canonicalNameBoolean.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Boolean)", str_entite(languageName), "", str_Valeur(languageName), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameDate.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(languageName), "", str_Valeur(languageName), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameTimestamp.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(languageName), "", str_Valeur(languageName), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameZonedDateTime.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(languageName), "", str_Valeur(languageName), ").toInstant().atZone(ZoneId.systemDefault()).toZonedDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameLocalDateTime.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)", str_entite(languageName), "", str_Valeur(languageName), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameLocalDate.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)", str_entite(languageName), "", str_Valeur(languageName), ").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameLong.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Long)", str_entite(languageName), "", str_Valeur(languageName), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameBigDecimal.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(BigDecimal.valueOf((Double)", str_entite(languageName), "", str_Valeur(languageName), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameDouble.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Double)", str_entite(languageName), "", str_Valeur(languageName), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameFloat.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Float)", str_entite(languageName), "", str_Valeur(languageName), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameInteger.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Integer)", str_entite(languageName), "", str_Valeur(languageName), ").toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " ", str_entite(languageName), "", str_Valeurs(languageName), " = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < ", str_entite(languageName), "", str_Valeurs(languageName), ".size(); k++) {");
										tl(6, "", str_entite(languageName), "", str_Valeur(languageName), " = ", str_entite(languageName), "", str_Valeurs(languageName), ".get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(((String)", str_entite(languageName), "", str_Valeur(languageName), "));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
								}
								else {
									l();
									tl(4, "", str_entite(languageName), "", str_Valeur(languageName), " = o.get", entityVarCapitalized, "();");
				//					tl(4, "entityValue = Optional.ofNullable(", str_solrDocument(languageName), ".getFieldValues(", q(entityVar, "_stored", entityTypeSuffix), ")).map(Collection<Object>::stream).orElseGet(Stream::empty).findFirst().orElse(null);");
				//					tl(4, "entityValue = ", str_solrDocument(languageName), ".getFieldValues(", q(entityVar, "_stored", entityTypeSuffix), ").stream().findFirst().orElse(null);");
				//					tl(5, "entityValue = ", str_solrDocument(languageName), ".getFieldValues(", q(entityVar, "_stored", entityTypeSuffix), ").stream().findFirst().orElse(null);");
									tl(4, "if(", str_entite(languageName), "", str_Valeur(languageName), " != null)");
									if (VAL_canonicalNameBoolean.equals(entitySolrCanonicalName)) {
				//						tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
				
										// tomorrow put this line everywhere. 
										tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", ", str_entite(languageName), "", str_Valeur(languageName), ");");
									} else if (VAL_canonicalNameDate.equals(entitySolrCanonicalName)) {
										if (VAL_canonicalNameTimestamp.equals(entityCanonicalName)) {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(", str_entite(languageName), "", str_Valeur(languageName), "));");
										} else if (VAL_canonicalNameZonedDateTime.equals(entityCanonicalName)) {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toZonedDateTime());");
											tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(", str_entite(languageName), "", str_Valeur(languageName), "));");
										} else if (VAL_canonicalNameLocalDateTime.equals(entityCanonicalName)) {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(", str_entite(languageName), "", str_Valeur(languageName), "));");
										} else if (VAL_canonicalNameLocalDate.equals(entityCanonicalName)) {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());");
											tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(", str_entite(languageName), "", str_Valeur(languageName), "));");
										} else {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(", str_entite(languageName), "", str_Valeur(languageName), "));");
										}
									} else if (VAL_canonicalNameLong.equals(entitySolrCanonicalName)) {
				//						tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
										tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", ", str_entite(languageName), "", str_Valeur(languageName), ");");
									} else if (VAL_canonicalNameDouble.equals(entitySolrCanonicalName)) {
										if (VAL_canonicalNameBigDecimal.equals(entityCanonicalName)) {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
											tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", ", str_entite(languageName), "", str_Valeur(languageName), ");");
										}
										else {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
											tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", ", str_entite(languageName), "", str_Valeur(languageName), ");");
										}
									} else if (VAL_canonicalNameFloat.equals(entitySolrCanonicalName)) {
				//						tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
										tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", ", str_entite(languageName), "", str_Valeur(languageName), ");");
									} else if (VAL_canonicalNameInteger.equals(entitySolrCanonicalName)) {
				//						tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
										tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", ", str_entite(languageName), "", str_Valeur(languageName), ");");
									}
									else {
				//						tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
										tl(5, "w.tl(3, ", str_entite(languageName), "", str_Numero(languageName), "++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(", str_entite(languageName), "", str_Valeur(languageName), "));");
									}
								}
				//				tl(3, ");");
				//				tl(3, "}");
							}
					
							////////////////////////
							// codeApiGenererPatch //
							////////////////////////
							o = wApiGeneratePatch;

							if(classSaved) {
								Integer tBase = 3;
								if(BooleanUtils.isTrue(entityAttribute)) {
									if(StringUtils.equals(entityCanonicalName, List.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName())) {
						
										if(StringUtils.compare(entityVar, entityAttributeVar) <= 0) {
											tl(tBase + 2, "case \"add", entityVarCapitalized, "\":");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_addA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", ", str_requete(languageName), "Json.get", entityListSimpleNameVertxJson, "(", str_methodName(languageName), ")", "));");
											tl(tBase + 3, "break;");
					
											tl(tBase + 2, "case \"addAll", entityVarCapitalized, "\":");
											tl(tBase + 3, entitySimpleNameVertxJson, " addAll", entityVarCapitalized, "", str_Valeurs(languageName), " = ", str_requete(languageName), "Json.get", entitySimpleNameVertxJson, "(", str_methodName(languageName), ");");
											tl(tBase + 3, "for(Integer i = 0; i <  addAll", entityVarCapitalized, "", str_Valeurs(languageName), ".size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_addA);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", addAll", entityVarCapitalized, "", str_Valeurs(languageName), ".get", entityListSimpleNameVertxJson, "(i)", "));");
											tl(tBase + 3, "}");
											tl(tBase + 3, "break;");
						
											tl(tBase + 2, "case \"set", entityVarCapitalized, "\":");
											tl(tBase + 3, entitySimpleNameVertxJson, " set", entityVarCapitalized, "", str_Valeurs(languageName), " = ", str_requete(languageName), "Json.get", entitySimpleNameVertxJson, "(", str_methodName(languageName), ");");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_clearA1);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", ", str_requete(languageName), "Json.get", entitySimpleNameVertxJson, "(", str_methodName(languageName), ")", "));");
					
											tl(tBase + 3, "for(Integer i = 0; i <  set", entityVarCapitalized, "", str_Valeurs(languageName), ".size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_addA);");
											tl(tBase + 4, "patchSqlParams.set(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", addAll", entityVarCapitalized, "", str_Valeurs(languageName), ".get", entityListSimpleNameVertxJson, "(i)", "));");
											tl(tBase + 3, "}");
											tl(tBase + 3, "break;");
							
											tl(tBase + 2, "case \"remove", entityVarCapitalized, "\":");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_removeA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", ", str_requete(languageName), "Json.getLong(", str_methodName(languageName), ")));");
											tl(tBase + 3, "break;");
										}
										else {
											tl(tBase + 2, "case \"add", entityVarCapitalized, "\":");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_addA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityAttributeVar), ", ", str_requete(languageName), "Json.get", entityListSimpleNameVertxJson, "(", str_methodName(languageName), "), ", q(entityVar), ", ", classVarPrimaryKey, "));");
											tl(tBase + 3, "break;");
					
											tl(tBase + 2, "case \"addAll", entityVarCapitalized, "\":");
											tl(tBase + 3, entitySimpleNameVertxJson, " addAll", entityVarCapitalized, "", str_Valeurs(languageName), " = ", str_requete(languageName), "Json.get", entitySimpleNameVertxJson, "(", str_methodName(languageName), ");");
											tl(tBase + 3, "for(Integer i = 0; i <  addAll", entityVarCapitalized, "", str_Valeurs(languageName), ".size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setA2);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entityAttributeVar), ", ", "addAll", entityVarCapitalized, "", str_Valeurs(languageName), ".get", entityListSimpleNameVertxJson, "(i), ", q(entityVar), ", ", classVarPrimaryKey, "));");
											tl(tBase + 3, "}");
											tl(tBase + 3, "break;");
						
											tl(tBase + 2, "case \"set", entityVarCapitalized, "\":");
											tl(tBase + 3, entitySimpleNameVertxJson, " set", entityVarCapitalized, "", str_Valeurs(languageName), " = ", str_requete(languageName), "Json.get", entitySimpleNameVertxJson, "(", str_methodName(languageName), ");");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_clearA2);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityAttributeVar), ", ", str_requete(languageName), "Json.get", entityListSimpleNameVertxJson, "(", str_methodName(languageName), ")", ", ", q(entityVar), ", ", classVarPrimaryKey, "));");
					
											tl(tBase + 3, "for(Integer i = 0; i <  set", entityVarCapitalized, "", str_Valeurs(languageName), ".size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setA2);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entityAttributeVar), ", set", entityVarCapitalized, "", str_Valeurs(languageName), ".get", entityListSimpleNameVertxJson, "(i), ", q(entityVar), ", ", classVarPrimaryKey, "));");
											tl(tBase + 3, "}");
											tl(tBase + 3, "break;");
							
											tl(tBase + 2, "case \"remove", entityVarCapitalized, "\":");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_removeA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityAttributeVar), ", ", str_requete(languageName), "Json.getLong(", str_methodName(languageName), ")", ", ", q(entityVar), ", ", classVarPrimaryKey, "));");
											tl(tBase + 3, "break;");
										}
									}
									else {
						
										tl(tBase + 2, "case \"set", entityVarCapitalized, "\":");
										if(StringUtils.compare(entityVar, entityAttributeVar) <= 0) {
											tl(tBase + 3, "o2.set", entityVarCapitalized, "(", str_requete(languageName), "Json.get", entitySimpleNameVertxJson, "(", str_methodName(languageName), "));");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setA1);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", o2.get", entityVarCapitalized, "()));");
										}
										else {
											tl(tBase + 3, "o2.set", entityVarCapitalized, "(", str_requete(languageName), "Json.get", entitySimpleNameVertxJson, "(", str_methodName(languageName), "));");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setA2);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityAttributeVar), ", o2.get", entityVarCapitalized, "()", ", ", q(entityVar), ", ", classVarPrimaryKey, "));");
										}
										tl(tBase + 3, "break;");
						
										tl(tBase + 2, "case \"remove", entityVarCapitalized, "\":");
										if(StringUtils.compare(entityVar, entityAttributeVar) <= 0) {
											tl(tBase + 3, "o2.set", entityVarCapitalized, "(", str_requete(languageName), "Json.get", entitySimpleNameVertxJson, "(", str_methodName(languageName), "));");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_removeA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", o2.get", entityVarCapitalized, "()));");
										}
										else {
											tl(tBase + 3, "o2.set", entityVarCapitalized, "(", str_requete(languageName), "Json.get", entitySimpleNameVertxJson, "(", str_methodName(languageName), "));");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_removeA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityAttributeVar), ", o2.get", entityVarCapitalized, "()", ", ", q(entityVar), ", ", classVarPrimaryKey, "));");
										}
										tl(tBase + 3, "break;");
									}
						
								}
								else if(BooleanUtils.isTrue(entityDefine)) {
									if(StringUtils.equals(entityCanonicalName, List.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName())) {
						
										tl(tBase + 2, "case \"add", entityVarCapitalized, "\":");
										tl(tBase + 3, "", str_requete(languageName), "Json.getJsonArray(", str_methodName(languageName), ").forEach((v) -> {");
										tl(tBase + 4, "o2.add", entityVarCapitalized, "((", entityListSimpleNameVertxJson, ")v);");
										tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_addA);");
										tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", o2.get", entityVarCapitalized, "()", ", ", classVarPrimaryKey, "));");
										tl(tBase + 3, "});");
						
										tl(tBase + 2, "case \"set", entityVarCapitalized, "\":");
										tl(tBase + 3, "o2.get", entityVarCapitalized, "().clear();");
										tl(tBase + 3, "", str_requete(languageName), "Json.getJsonArray(", str_methodName(languageName), ").forEach((v) -> {");
										tl(tBase + 4, "o2.add", entityVarCapitalized, "((", entityListSimpleNameVertxJson, ")v);");
										tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setD);");
										tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(\"", entityVar, "\", o2.get", entityVarCapitalized, "(), ", classVarPrimaryKey, "));");
										tl(tBase + 3, "});");
									}
									else {
						
										tl(tBase + 2, "case \"set", entityVarCapitalized, "\":");
										tl(tBase + 3, "o2.set", entityVarCapitalized, "(", str_requete(languageName), "Json.get", entitySimpleNameVertxJson, "(", str_methodName(languageName), "));");
										tl(tBase + 3, "if(o2.get", entityVarCapitalized, "() == null) {");
										tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_removeD);");
										tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", classVarPrimaryKey, ", \"", entityVar, "\"));");
										tl(tBase + 3, "} else {");
										tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setD);");
										tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(\"", entityVar, "\", o2.get", entityVarCapitalized, "(), ", classVarPrimaryKey, "));");
										tl(tBase + 3, "}");
									}
						
									tl(tBase + 3, "break;");
								}
							}	
						}
						solrSearch.setStart(i.intValue() + searchLines);
						searchReponse = solrClientComputate.query(solrSearch);
						searchList = searchReponse.getResults();
					}
				}
			}
			wApiGet.flushClose();
			wApiGenerateGet.flushClose();
	
			o = writerGenApiServiceImpl;
			tl(0, "");
			l("/**");
			l(" * ", str_Translate(languageName), ": false");
			l(" **/");
			s("public class ", classSimpleNameGenApiServiceImpl);
			s(" implements ", classSimpleNameGenApiService);
			l(" {");
			l();
			tl(1, "protected static final Logger LOGGER = LoggerFactory.getLogger(", classSimpleNameGenApiServiceImpl, ".class);");
			l();
			tl(1, "protected static final String SERVICE_ADDRESS = \"", classSimpleNameApiServiceImpl, "\";");
			l();
			tl(1, "protected ", classPartsSiteContext.simpleName(languageName), " ", str_siteContext(languageName), ";");
			l();
			tl(1, "public ", classSimpleNameGenApiServiceImpl, "(", classPartsSiteContext.simpleName(languageName), " ", str_siteContext(languageName), ") {");
			tl(2, "this.", str_siteContext(languageName), " = ", str_siteContext(languageName), ";");
			tl(2, classSimpleNameGenApiService, " service = ", classSimpleNameGenApiService, ".", str_create(languageName), "Proxy(", str_siteContext(languageName), ".getVertx(), SERVICE_ADDRESS);");
			tl(1, "}");

			for(String classApiMethod : classApiMethods) {
				String classPageCanonicalNameMethod = (String)classDoc.get("classPageCanonicalName" + classApiMethod + "_" + languageName + "_stored_string");
				String classPageSimpleNameMethod = (String)classDoc.get("classPageSimpleName" + classApiMethod + "_" + languageName + "_stored_string");
				String classApiOperationIdMethod = (String)classDoc.get("classApiOperationId" + classApiMethod + "_" + languageName + "_stored_string");
				String classApiUriMethod = (String)classDoc.get("classApiUri" + classApiMethod + "_" + languageName + "_stored_string");
				String classApiMediaTypeMethode = (String)classDoc.get("classApiMediaType" + classApiMethod + "_" + languageName + "_stored_string");
				String classPageLanguageName = (String)classDoc.get("classPageLanguageName" + classApiMethod + "_" + languageName + "_stored_string");
				if(classPageLanguageName == null || classPageLanguageName.equals(languageName)) {
					l();
					tl(1, "// ", classApiMethod, " //");
					if(classPageCanonicalNameMethod != null) {
						l();
						tl(1, "@Override");
						t(1, "public void ", classApiOperationIdMethod, "Id(");
						if(StringUtils.containsAny(classApiMethod, "POST", "PUT", "PATCH"))
							s("JsonObject body, ");
						l("OperationRequest ", str_operationRequest(languageName), ", Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
						tl(2, classApiOperationIdMethod, "(", str_operationRequest(languageName), ", ", str_eventHandler(languageName), ");");
						tl(1, "}");
					}
					l();
					tl(1, "@Override");
					t(1, "public void ", classApiOperationIdMethod, "(");
					if(StringUtils.containsAny(classApiMethod, "POST", "PUT", "PATCH"))
						s("JsonObject body, ");
					l("OperationRequest ", str_operationRequest(languageName), ", Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
	
					if(classApiMethod.contains("POST")) {
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = ", str_generate(languageName), "", classPartsSiteRequest.simpleName(languageName), "", str_Pour(languageName), "", classSimpleName, "(", str_siteContext(languageName), ", ", str_operationRequest(languageName), ", body);");
						tl(3, "sql", classSimpleName, "(", str_siteRequest(languageName), ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "", str_create(languageName), "", classApiMethod, classSimpleName, "(", str_siteRequest(languageName), ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, classSimpleName, " ", StringUtils.uncapitalize(classSimpleName), " = b.result();");
						tl(7, "sql", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "", str_define(languageName), "", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "", str_attribute(languageName), "", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", e -> {");
						tl(12, "if(e.succeeded()) {");
						tl(13, "", str_index(languageName), "", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", f -> {");
						tl(14, "if(f.succeeded()) {");
						tl(15, "", str_response(languageName), "200", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", g -> {");
						tl(16, "if(f.succeeded()) {");
						tl(17, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
						tl(17, "", str_sqlConnection(languageName), ".commit(h -> {");
						tl(18, "if(a.succeeded()) {");
						tl(19, "", str_sqlConnection(languageName), ".close(i -> {");
						tl(20, "if(a.succeeded()) {");
						tl(21, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(g.result()));");
						tl(20, "} else {");
						tl(21, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", i);");
						tl(20, "}");
						tl(19, "});");
						tl(18, "} else {");
						tl(19, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", h);");
						tl(18, "}");
						tl(17, "});");
						tl(16, "} else {");
						tl(17, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", g);");
						tl(16, "}");
						tl(15, "});");
						tl(14, "} else {");
						tl(15, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", f);");
						tl(14, "}");
						tl(13, "});");
						tl(12, "} else {");
						tl(13, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", e);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} else {");
						tl(11, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_error(languageName), "", classSimpleName, "(null, ", str_eventHandler(languageName), ", Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classApiMethod.contains("PATCH")) {
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = ", str_generate(languageName), "", classPartsSiteRequest.simpleName(languageName), "", str_Pour(languageName), "", classSimpleName, "(", str_siteContext(languageName), ", ", str_operationRequest(languageName), ", body);");
						tl(3, "sql", classSimpleName, "(", str_siteRequest(languageName), ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "", str_user(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, "", str_search(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", false, true, ", "null", ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, classPartsSearchList.simpleName(languageName), "<", classSimpleName, "> ", str_list(languageName), "", classSimpleName, " = c.result();");
						tl(9, "", str_list(languageName), "", classApiMethod, classSimpleName, "(", str_list(languageName), "", classSimpleName, ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
						tl(11, "if(", str_sqlConnection(languageName), " == null) {");
						tl(12, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(d.result()));");
						tl(11, "} else {");
						tl(12, "", str_sqlConnection(languageName), ".commit(e -> {");
						tl(13, "if(e.succeeded()) {");
						tl(14, "", str_sqlConnection(languageName), ".close(f -> {");
						tl(15, "if(f.succeeded()) {");
						tl(16, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(d.result()));");
						tl(15, "} else {");
						tl(16, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", f);");
						tl(15, "}");
						tl(14, "});");
						tl(13, "} else {");
						tl(14, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", e);");
						tl(13, "}");
						tl(12, "});");
						tl(11, "}");
						tl(10, "} else {");
						tl(11, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_error(languageName), "", classSimpleName, "(null, ", str_eventHandler(languageName), ", Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classApiMethod.contains(str_Recherche(languageName))) {
						if(classPageSimpleNameMethod == null) {
							tl(2, "try {");
							tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = ", str_generate(languageName), "", classPartsSiteRequest.simpleName(languageName), "", str_Pour(languageName), "", classSimpleName, "(", str_siteContext(languageName), ", ", str_operationRequest(languageName), ");");
							tl(3, "", str_search(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", false, true, ", "null", ", a -> {");
							tl(4, "if(a.succeeded()) {");
							tl(5, classPartsSearchList.simpleName(languageName), "<", classSimpleName, "> ", str_list(languageName), "", classSimpleName, " = a.result();");
							tl(5, "", str_response(languageName), "200", classApiMethod, classSimpleName, "(", str_list(languageName), "", classSimpleName, ", b -> {");
							tl(6, "if(b.succeeded()) {");
							tl(7, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(b.result()));");
							tl(6, "} else {");
							tl(7, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", b);");
							tl(6, "}");
							tl(5, "});");
							tl(4, "} else {");
							tl(5, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", a);");
							tl(4, "}");
							tl(3, "});");
							tl(2, "} catch(Exception e) {");
							tl(3, "", str_error(languageName), "", classSimpleName, "(null, ", str_eventHandler(languageName), ", Future.failedFuture(e));");
							tl(2, "}");
						}
						else {
							tl(2, "try {");
							tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = ", str_generate(languageName), "", classPartsSiteRequest.simpleName(languageName), "", str_Pour(languageName), "", classSimpleName, "(", str_siteContext(languageName), ", ", str_operationRequest(languageName), ");");
							tl(3, "sql", classSimpleName, "(", str_siteRequest(languageName), ", a -> {");
							tl(4, "if(a.succeeded()) {");
							tl(5, "", str_user(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", b -> {");
							tl(6, "if(b.succeeded()) {");
							tl(7, "", str_search(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", false, true, ", q(classApiUriMethod), ", c -> {");
							tl(8, "if(c.succeeded()) {");
							tl(9, classPartsSearchList.simpleName(languageName), "<", classSimpleName, "> ", str_list(languageName), "", classSimpleName, " = c.result();");
							tl(9, "", str_response(languageName), "200", classApiMethod, classSimpleName, "(", str_list(languageName), "", classSimpleName, ", d -> {");
							tl(10, "if(d.succeeded()) {");
							tl(11, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
							tl(11, "if(", str_sqlConnection(languageName), " == null) {");
							tl(12, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(d.result()));");
							tl(11, "} else {");
							tl(12, "", str_sqlConnection(languageName), ".commit(e -> {");
							tl(13, "if(e.succeeded()) {");
							tl(14, "", str_sqlConnection(languageName), ".close(f -> {");
							tl(15, "if(f.succeeded()) {");
							tl(16, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(d.result()));");
							tl(15, "} else {");
							tl(16, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", f);");
							tl(15, "}");
							tl(14, "});");
							tl(13, "} else {");
							tl(14, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", e);");
							tl(13, "}");
							tl(12, "});");
							tl(11, "}");
							tl(10, "} else {");
							tl(11, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", d);");
							tl(10, "}");
							tl(9, "});");
							tl(8, "} else {");
							tl(9, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", c);");
							tl(8, "}");
							tl(7, "});");
							tl(6, "} else {");
							tl(7, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", b);");
							tl(6, "}");
							tl(5, "});");
							tl(4, "} else {");
							tl(5, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", a);");
							tl(4, "}");
							tl(3, "});");
							tl(2, "} catch(Exception e) {");
							tl(3, "", str_error(languageName), "", classSimpleName, "(null, ", str_eventHandler(languageName), ", Future.failedFuture(e));");
							tl(2, "}");
						}
					}
					else if(classApiMethod.contains("GET")) {
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = ", str_generate(languageName), "", classPartsSiteRequest.simpleName(languageName), "", str_Pour(languageName), "", classSimpleName, "(", str_siteContext(languageName), ", ", str_operationRequest(languageName), ");");
						tl(3, "", str_search(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", false, true, ", "null", ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, classPartsSearchList.simpleName(languageName), "<", classSimpleName, "> ", str_list(languageName), "", classSimpleName, " = a.result();");
						tl(5, "", str_response(languageName), "200", classApiMethod, classSimpleName, "(", str_list(languageName), "", classSimpleName, ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(b.result()));");
						tl(6, "} else {");
						tl(7, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_error(languageName), "", classSimpleName, "(null, ", str_eventHandler(languageName), ", Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classApiMethod.contains("PUT")) {
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = ", str_generate(languageName), "", classPartsSiteRequest.simpleName(languageName), "", str_Pour(languageName), "", classSimpleName, "(", str_siteContext(languageName), ", ", str_operationRequest(languageName), ", body);");
						tl(3, "sql", classSimpleName, "(", str_siteRequest(languageName), ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "replace", classApiMethod, classSimpleName, "(", str_siteRequest(languageName), ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, classSimpleName, " ", StringUtils.uncapitalize(classSimpleName), " = b.result();");
						tl(7, "sql", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "", str_define(languageName), "", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "", str_attribute(languageName), "", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", e -> {");
						tl(12, "if(e.succeeded()) {");
						tl(13, "", str_index(languageName), "", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", f -> {");
						tl(14, "if(f.succeeded()) {");
						tl(15, "", str_response(languageName), "200", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", g -> {");
						tl(16, "if(g.succeeded()) {");
						tl(17, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
						tl(17, "", str_sqlConnection(languageName), ".commit(h -> {");
						tl(18, "if(a.succeeded()) {");
						tl(19, "", str_sqlConnection(languageName), ".close(i -> {");
						tl(20, "if(a.succeeded()) {");
						tl(21, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(g.result()));");
						tl(20, "} else {");
						tl(21, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", i);");
						tl(20, "}");
						tl(19, "});");
						tl(18, "} else {");
						tl(19, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", h);");
						tl(18, "}");
						tl(17, "});");
						tl(16, "} else {");
						tl(17, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", g);");
						tl(16, "}");
						tl(15, "});");
						tl(14, "} else {");
						tl(15, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", f);");
						tl(14, "}");
						tl(13, "});");
						tl(12, "} else {");
						tl(13, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", e);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} else {");
						tl(11, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_error(languageName), "", classSimpleName, "(null, ", str_eventHandler(languageName), ", Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classApiMethod.contains("DELETE")) {
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = ", str_generate(languageName), "", classPartsSiteRequest.simpleName(languageName), "", str_Pour(languageName), "", classSimpleName, "(", str_siteContext(languageName), ", ", str_operationRequest(languageName), ");");
						tl(3, "sql", classSimpleName, "(", str_siteRequest(languageName), ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "", str_search(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", false, true, ", "null", ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, classPartsSearchList.simpleName(languageName), "<", classSimpleName, "> ", str_list(languageName), "", classSimpleName, " = b.result();");
						tl(7, "", str_delete(languageName), "", classApiMethod, classSimpleName, "(", str_siteRequest(languageName), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "", str_response(languageName), "200", classApiMethod, classSimpleName, "(", str_siteRequest(languageName), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
						tl(11, "if(", str_sqlConnection(languageName), " == null) {");
						tl(12, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(d.result()));");
						tl(11, "} else {");
						tl(12, "", str_sqlConnection(languageName), ".commit(e -> {");
						tl(13, "if(e.succeeded()) {");
						tl(14, "", str_sqlConnection(languageName), ".close(f -> {");
						tl(15, "if(f.succeeded()) {");
						tl(16, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(d.result()));");
						tl(15, "} else {");
						tl(16, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", f);");
						tl(15, "}");
						tl(14, "});");
						tl(13, "} else {");
						tl(14, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", e);");
						tl(13, "}");
						tl(12, "});");
						tl(11, "}");
						tl(10, "} else {");
						tl(11, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "", str_error(languageName), "", classSimpleName, "(", str_siteRequest(languageName), ", ", str_eventHandler(languageName), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_error(languageName), "", classSimpleName, "(null, ", str_eventHandler(languageName), ", Future.failedFuture(e));");
						tl(2, "}");
					}
					tl(1, "}");
	
					if(classApiMethod.contains(str_Recherche(languageName))) {
	//					l();
	//					tl(1, "public Future<OperationResponse> ", str_list(languageName), str_Recherche(languageName), classSimpleName, classPartsSearchList.simpleName(languageName), "(<", classSimpleName, "> ", str_list(languageName), "", classSimpleName, ") {");
	//					tl(2, "List<Future> futures = new ArrayList<>();");
	//					tl(2, "", str_list(languageName), "", classSimpleName, ".getList().forEach(o -> {");
	//					tl(3, "futures.add(");
	//					tl(4, "sqlPATCH", classSimpleName, "(o).compose(");
	//					tl(5, "b -> ", str_index(languageName), "", classSimpleName, "(o)");
	//					tl(4, ")");
	//					tl(3, ");");
	//					tl(2, "});");
	//					tl(2, "Future<OperationResponse> future = CompositeFuture.all(futures).compose( a -> ");
	//					tl(3, "", str_response(languageName), "200Recherche", classSimpleName, "(", str_list(languageName), "", classSimpleName, ")");
	//					tl(2, ");");
	//					tl(2, "return future;");
	//					tl(1, "}");
					}
					if(classApiMethod.contains("POST")) {
						l();
						tl(1, "public void ", str_create(languageName), "", classApiMethod, classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), ", Handler<AsyncResult<", classSimpleName, ">> ", str_eventHandler(languageName), ") {");
						tl(2, "try {");
						tl(3, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
						tl(3, "String ", str_user(languageName), "Id = ", str_siteRequest(languageName), ".get", str_User(languageName), "Id();");
						l();
						tl(3, "", str_sqlConnection(languageName), ".queryWithParams(");
						tl(5, "", classPartsSiteContext.simpleName(languageName), ".SQL_", str_create(languageName), "");
						tl(5, ", new JsonArray(Arrays.asList(", classSimpleName, ".class.getCanonicalName(), ", str_user(languageName), "Id))");
						tl(5, ", ", str_create(languageName), "Async");
						tl(3, "-> {");
						tl(4, "JsonArray ", str_create(languageName), "", str_Ligne(languageName), " = ", str_create(languageName), "Async.result().getResults().stream().findFirst().orElseGet(() -> null);");
						tl(4, "Long ", classVarPrimaryKey, " = ", str_create(languageName), "", str_Ligne(languageName), ".getLong(0);");
						tl(4, classSimpleName, " o = new ", classSimpleName, "();");
						tl(4, "o.set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, ");");
						tl(4, "o.set", str_SiteRequest(languageName), "_(", str_siteRequest(languageName), ");");
						tl(4, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(o));");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classApiMethod.contains("PATCH")) {
						l();
						tl(1, "public void ", str_list(languageName), "", classApiMethod, classSimpleName, "(", classPartsSearchList.simpleName(languageName), "<", classSimpleName, "> ", str_list(languageName), "", classSimpleName, ", Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, str_list(languageName), classSimpleName, ".getList().forEach(o -> {");
						tl(3, "futures.add(");
						tl(4, "future", classApiMethod, classSimpleName, "(o, ", str_eventHandler(languageName), ")");
						tl(3, ");");
						tl(2, "});");
						tl(2, "CompositeFuture.all(futures).setHandler( a -> {");
						tl(3, "if(a.succeeded()) {");
						tl(4, "", str_response(languageName), "200", classApiMethod, classSimpleName, "(", str_list(languageName), "", classSimpleName, ", ", str_eventHandler(languageName), ");");
						tl(3, "} else {");
						tl(4, "", str_error(languageName), "", classSimpleName, "(", str_list(languageName), "", classSimpleName, ".get", str_SiteRequest(languageName), "_(), ", str_eventHandler(languageName), ", a);");
						tl(3, "}");
						tl(2, "});");
						tl(1, "}");
						l();
						tl(1, "public Future<", classSimpleName, "> future", classApiMethod, classSimpleName, "(", classSimpleName, " o,  Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
						tl(2, "Future<", classSimpleName, "> future = Future.future();");
						tl(2, "try {");
						tl(3, "sql", classApiMethod, classSimpleName, "(o, a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, classSimpleName, " ", StringUtils.uncapitalize(classSimpleName), " = a.result();");
						tl(5, str_define(languageName), classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, str_attribute(languageName), classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, str_index(languageName), classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "future.complete(o);");
						tl(11, str_eventHandler(languageName), ".handle(Future.succeededFuture(d.result()));");
						tl(10, "} else {");
						tl(11, "", str_error(languageName), "", classSimpleName, "(o.get", str_SiteRequest(languageName), "_(), ", str_eventHandler(languageName), ", d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, str_error(languageName), "", classSimpleName, "(o.get", str_SiteRequest(languageName), "_(), ", str_eventHandler(languageName), ", c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, str_error(languageName), "", classSimpleName, "(o.get", str_SiteRequest(languageName), "_(), ", str_eventHandler(languageName), ", b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, str_error(languageName), "", classSimpleName, "(o.get", str_SiteRequest(languageName), "_(), ", str_eventHandler(languageName), ", a);");
						tl(4, "}");
						tl(3, "});");
						tl(3, "return future;");
						tl(2, "} catch(Exception e) {");
						tl(3, "return Future.failedFuture(e);");
						tl(2, "}");
						tl(1, "}");
						l();
						tl(1, "public void sql", classApiMethod, classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<", classSimpleName, ">> ", str_eventHandler(languageName), ") {");
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = o.get", str_SiteRequest(languageName), "_();");
						tl(3, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
						tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
						tl(3, "JsonObject ", str_requete(languageName), "Json = ", str_siteRequest(languageName), ".get", str_JsonObject(languageName), "();");
						tl(3, "StringBuilder patchSql = new StringBuilder();");
						tl(3, "List<Object> patchSqlParams = new ArrayList<Object>();");
						tl(3, "Set<String> ", str_methodNames(languageName), " = ", str_requete(languageName), "Json.fieldNames();");
						tl(3, classSimpleName, " o2 = new ", classSimpleName, "();");
						l();
						tl(3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_", str_modifier(languageName), ");");
						tl(3, "patchSqlParams.addAll(Arrays.asList(pk, ", q(classCanonicalName), "));");
						tl(3, "for(String ", str_methodName(languageName), " : ", str_methodNames(languageName), ") {");
						tl(4, "switch(", str_methodName(languageName), ") {");
						s(wApiGeneratePatch.toString());
						tl(4, "}");
						tl(3, "}");
						tl(3, "", str_sqlConnection(languageName), ".updateWithParams(");
						tl(5, "patchSql.toString()");
						tl(5, ", new JsonArray(patchSqlParams)");
						tl(5, ", patchAsync");
						tl(3, "-> {");
						tl(4, classSimpleName, " o3 = new ", classSimpleName, "();");
						tl(4, "o3.set", str_SiteRequest(languageName), "_(o.get", str_SiteRequest(languageName), "_());");
						tl(4, "o3.set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, ");");
						tl(4, str_eventHandler(languageName), ".handle(Future.succeededFuture(o3));");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classApiMethod.contains("PUT")) {
						l();
						tl(1, "public void replace", classApiMethod, classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), ", Handler<AsyncResult<", classSimpleName, ">> ", str_eventHandler(languageName), ") {");
						tl(2, "try {");
						tl(3, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
						tl(3, "String ", str_user(languageName), "Id = ", str_siteRequest(languageName), ".get", str_User(languageName), "Id();");
						tl(3, "Long pk = ", str_siteRequest(languageName), ".get", str_Requete(languageName), "Pk();");
						l();
						tl(3, "", str_sqlConnection(languageName), ".updateWithParams(");
						tl(5, "", classPartsSiteContext.simpleName(languageName), ".SQL_clear");
						tl(5, ", new JsonArray(Arrays.asList(pk, ", classSimpleName, ".class.getCanonicalName(), pk, pk, pk))");
						tl(5, ", replaceAsync");
						tl(3, "-> {");
						tl(4, classSimpleName, " o = new ", classSimpleName, "();");
						tl(4, "o.set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, ");");
						tl(4, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(o));");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classApiMethod.contains("POST") || classApiMethod.contains("PUT")) {
						l();
						tl(1, "public void sql", classApiMethod, classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = o.get", str_SiteRequest(languageName), "_();");
						tl(3, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
						tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
						tl(3, "JsonObject jsonObject = ", str_siteRequest(languageName), ".get", str_JsonObject(languageName), "();");
						tl(3, "StringBuilder postSql = new StringBuilder();");
						tl(3, "List<Object> postSqlParams = new ArrayList<Object>();");
						l();
						tl(3, "if(jsonObject != null) {");
						tl(4, "Set<String> ", str_entite(languageName), "Vars = jsonObject.fieldNames();");
						tl(4, "for(String ", str_entite(languageName), "Var : ", str_entite(languageName), "Vars) {");
						tl(5, "switch(", str_entite(languageName), "Var) {");
						s(wApiGeneratePost.toString());
						tl(5, "}");
						tl(4, "}");
						tl(3, "}");
						tl(3, "", str_sqlConnection(languageName), ".updateWithParams(");
						tl(5, "postSql.toString()");
						tl(5, ", new JsonArray(postSqlParams)");
						tl(5, ", postAsync");
						tl(3, "-> {");
						tl(4, "", str_eventHandler(languageName), ".handle(Future.succeededFuture());");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classApiMethod.contains("GET")) {
					}
					if(classApiMethod.contains("DELETE")) {
						l();
						tl(1, "public void ", str_delete(languageName), "", classApiMethod, classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), ", Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
						tl(2, "try {");
						tl(3, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
						tl(3, "String ", str_user(languageName), "Id = ", str_siteRequest(languageName), ".get", str_User(languageName), "Id();");
						tl(3, "Long pk = ", str_siteRequest(languageName), ".get", str_Requete(languageName), "Pk();");
						l();
						tl(3, "", str_sqlConnection(languageName), ".updateWithParams(");
						tl(5, "", classPartsSiteContext.simpleName(languageName), ".SQL_", str_delete(languageName), "");
						tl(5, ", new JsonArray(Arrays.asList(pk, ", classSimpleName, ".class.getCanonicalName(), pk, pk, pk, pk))");
						tl(5, ", ", str_delete(languageName), "Async");
						tl(3, "-> {");
						tl(4, "", str_eventHandler(languageName), ".handle(Future.succeededFuture());");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					l();
					t(1, "public void ", str_response(languageName), "200", classApiMethod, classSimpleName, "(");
	
					if(classApiMethod.contains("POST") || classApiMethod.contains("PUT"))
						s(classSimpleName, " o");
					else if(classApiMethod.contains("DELETE"))
						s("", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), "");
					else
						s(classPartsSearchList.simpleName(languageName), "<", classSimpleName, "> ", str_list(languageName), "", classSimpleName);
	
					l(", Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
	
					tl(2, "try {");
//					tl(3, "JsonObject json = new JsonObject();");
	
					if(classApiMethod.contains("POST") || classApiMethod.contains("PUT")) {
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = o.get", str_SiteRequest(languageName), "_();");
					}
					else if(classApiMethod.contains(str_Recherche(languageName)) || classApiMethod.contains("PATCH") || classApiMethod.contains("GET")) {
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = ", str_list(languageName), "", classSimpleName, ".get", str_SiteRequest(languageName), "_();");
					}
					else {
					}
	
//					t(3, "", classPartsAllWriter.simpleName(languageName), " w = ", classPartsAllWriter.simpleName(languageName), ".", str_create(languageName), "(");
//					if(classApiMethod.contains("POST") || classApiMethod.contains("PUT"))
//						s("o.get", str_SiteRequest(languageName), "_()");
//					else if(classApiMethod.contains("DELETE"))
//						s("", str_siteRequest(languageName), "");
//					else
//						s("", str_list(languageName), "", classSimpleName, ".get", str_SiteRequest(languageName), "_()");
//					l(", json);");
//					tl(3, "", str_siteRequest(languageName), ".setW(w);");
//
//					if(classApiMethod.contains("PATCH"))
//						tl(3, "buffer.appendString(\"{}\");");
	
	
					if(classApiMethod.contains("GET")) {
						tl(3, "SolrDocumentList ", str_solrDocuments(languageName), " = ", str_list(languageName), "", classSimpleName, ".getSolrDocumentList();");
						l();
					}
					if(classApiMethod.contains(str_Recherche(languageName))) {
					}
	
					if(classApiMethod.contains(str_Recherche(languageName)) || classApiMethod.contains("GET")) {
					}
					else if(classApiMethod.contains("DELETE")) {
					}
	
					if(classApiMethod.contains(str_Recherche(languageName))) {
						if(classPageCanonicalNameMethod != null) {
							tl(3, "Buffer buffer = Buffer.buffer();");
							t(3, classPartsAllWriter.simpleName(languageName), " w = ", classPartsAllWriter.simpleName(languageName), ".", str_create(languageName), "(");
							s("", str_list(languageName), "", classSimpleName, ".get", str_SiteRequest(languageName), "_()");
							l(", buffer);");
							tl(3, classPageSimpleNameMethod, " page = new ", classPageSimpleNameMethod, "();");
//							tl(3, "page.setPageUrl(\"", siteBaseUrl, classApiUri, "\");");
							tl(3, "SolrDocument page", str_DocumentSolr(languageName), " = new SolrDocument();");
							l();
							tl(3, "page", str_DocumentSolr(languageName), ".setField(", q("pageUri_frFR_stored_string"), ", ", q(classApiUriMethod), ");");
							tl(3, "page.setPage", str_DocumentSolr(languageName), "(page", str_DocumentSolr(languageName), ");");
							tl(3, "page.setW(w);");
							if(!classPageSimple)
								tl(3, "page.set", str_List(languageName), "", classSimpleName, "(", str_list(languageName), "", classSimpleName, ");");
							tl(3, "page.", str_initDeep(languageName), "", classPageSimpleNameMethod, "(", str_siteRequest(languageName), ");");
							tl(3, "page.html();");
						}
						else {
							tl(3, "QueryResponse ", str_response(languageName), "", str_Recherche(languageName), " = ", str_list(languageName), "", classSimpleName, ".getQueryResponse();");
							tl(3, "SolrDocumentList ", str_solrDocuments(languageName), " = ", str_list(languageName), "", classSimpleName, ".getSolrDocumentList();");
							tl(3, "Long ", str_millisSearch(languageName), " = Long.valueOf(", str_response(languageName), "", str_Recherche(languageName), ".getQTime());");
							tl(3, "Long ", str_millisTransmission(languageName), " = ", str_response(languageName), "", str_Recherche(languageName), ".getElapsedTime();");
							tl(3, "Long ", str_numStart(languageName), " = ", str_response(languageName), "", str_Recherche(languageName), ".getResults().getStart();");
							tl(3, "Long ", str_numFound(languageName), " = ", str_response(languageName), "", str_Recherche(languageName), ".getResults().getNumFound();");
							tl(3, "Integer ", str_numReturned(languageName), " = ", str_response(languageName), "", str_Recherche(languageName), ".getResults().size();");
							tl(3, "String ", str_timeSearch(languageName), " = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(", str_millisSearch(languageName), "), TimeUnit.MILLISECONDS.toMillis(", str_millisSearch(languageName), ") - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(", str_millisSearch(languageName), ")));");
							tl(3, "String ", str_timeTransmission(languageName), " = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(", str_millisTransmission(languageName), "), TimeUnit.MILLISECONDS.toMillis(", str_millisTransmission(languageName), ") - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(", str_millisTransmission(languageName), ")));");
							tl(3, "Exception exception", str_Recherche(languageName), " = ", str_response(languageName), "", str_Recherche(languageName), ".getException();");
							l();
							tl(3, "JsonObject json = new JsonObject();");
							tl(3, "json.put(", q(str_numStart(languageName)), ", ", str_numStart(languageName), ");");
							tl(3, "json.put(", q(str_numFound(languageName)), ", ", str_numFound(languageName), ");");
							tl(3, "json.put(", q(str_numReturned(languageName)), ", ", str_numReturned(languageName), ");");
							tl(3, "json.put(", q(str_timeSearch(languageName)), ", ", str_timeSearch(languageName), ");");
							tl(3, "json.put(", q(str_timeTransmission(languageName)), ", ", str_timeTransmission(languageName), ");");
							tl(3, "JsonArray l = new JsonArray();");
							tl(3, str_list(languageName), classSimpleName, ".getList().stream().forEach(o -> {");
							tl(4, "l.add(JsonObject.mapFrom(o));");
							tl(3, "});");
							tl(3, "json.put(", q(str_list(languageName)), ", l);");
							tl(3, "if(exception", str_Recherche(languageName), " != null) {");
							tl(4, "json.put(", q("exception", str_Recherche(languageName)), ", exception", str_Recherche(languageName), ".getMessage());");
							tl(3, "}");
						}
					}
					else if(classApiMethod.contains("GET")) {
						if(classPageCanonicalNameMethod != null) {
							tl(3, classPageSimpleNameMethod, " page = new ", classPageSimpleNameMethod, "();");
//							tl(3, "page.setPageUrl(\"", siteBaseUrl, classApiUri, "\");");
							tl(3, "SolrDocument page", str_DocumentSolr(languageName), " = new SolrDocument();");
							tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = ", str_list(languageName), "", classSimpleName, ".get", str_SiteRequest(languageName), "_();");
							l();
							tl(3, "page", str_DocumentSolr(languageName), ".setField(", q("pageUri_frFR_stored_string"), ", ", q(classApiUriMethod), ");");
							tl(3, "page.setPage", str_DocumentSolr(languageName), "(page", str_DocumentSolr(languageName), ");");
							tl(3, "page.setW(w);");
							tl(3, "page.", str_initDeep(languageName), "", classPageSimpleNameMethod, "(", str_siteRequest(languageName), ");");
							tl(3, "page.html();");
						}
						else {
//							tl(3, "if(", str_list(languageName), "", classSimpleName, ".size() > 0) {");
//							tl(4, "SolrDocument ", str_solrDocument(languageName), " = ", str_solrDocuments(languageName), ".get(0);");
//							tl(4, classSimpleName, " o = ", str_list(languageName), "", classSimpleName, ".get(0);");
//							tl(4, "Object ", str_entite(languageName), "", str_Valeur(languageName), ";");
//							tl(4, "Integer ", str_entite(languageName), "", str_Numero(languageName), " = 0;");
//							l();
//							tl(4, "w.l(", q("{"), ");");
//		//					tl(4, "for(int j = 0; j < fieldNames.size(); j++) {");
//		//					tl(5, "String ", str_entite(languageName), "VarStocke = fieldNames.get(j);");
//		//					tl(5, "List<Object> ", str_entite(languageName), "", str_Valeurs(languageName), " = new ArrayList<>(", str_solrDocument(languageName), ".getFieldValues(", str_entite(languageName), "VarStocke));");
//							s(wApiGenerateGet.toString());
//		//					tl(4, "}");
//							l();
//							tl(4, "w.l(", q("}"), ");");
							tl(3, "JsonObject json = JsonObject.mapFrom(", str_list(languageName), "", classSimpleName, ".get(0));");
//							tl(3, "}");
						}
					}
					else if(classApiMethod.contains("POST")) {
						tl(3, "JsonObject json = new JsonObject();");
					}
					else if(classApiMethod.contains("PATCH")) {
						tl(3, "JsonObject json = new JsonObject();");
					}
					else if(classApiMethod.contains("DELETE")) {
						tl(3, "JsonObject json = new JsonObject();");
					}
	
					if((classApiMethod.contains("GET") || classApiMethod.contains(str_Recherche(languageName))) && classPageCanonicalNameMethod != null) {
						tl(3, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(new OperationResponse(200, \"OK\", buffer, new CaseInsensitiveHeaders())));");
					}
					else {
						tl(3, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));");
					}
	
					tl(2, "} catch(Exception e) {");
					tl(3, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
					tl(2, "}");
					tl(1, "}");
				}
			}
	
			s(wApiEntities.toString());

			AllWriter wVarIndexed = AllWriter.create();
			AllWriter wVarSearched = AllWriter.create();
			AllWriter wVarSuggested = AllWriter.create();
			{
				SolrQuery solrSearch = new SolrQuery();   
				solrSearch.setQuery("*:*");
				solrSearch.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + entitySuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				solrSearch.addFilterQuery("partIsEntity_indexed_boolean:true");
				solrSearch.addFilterQuery("classCanonicalName_" + languageActualName + "_indexed_string:" + fqClassesSuperEtMoi);
				QueryResponse searchReponse = solrClientComputate.query(solrSearch);
				SolrDocumentList searchList = searchReponse.getResults();
				Integer searchLines = solrSearch.getRows();
	
				if(searchList.size() > 0) {
					for(Long i = searchList.getStart(); i < searchList.getNumFound(); i+=searchLines) {
						for(Integer j = 0; j < searchList.size(); j++) {
							SolrDocument entiteDocumentSolr = searchList.get(j);
							entityVar = (String)entiteDocumentSolr.get("entityVar_" + languageName + "_stored_string");
							entityTypeSuffix = (String)entiteDocumentSolr.get("entityTypeSuffix_stored_string");
							entityIndexed = (Boolean)entiteDocumentSolr.get("entityIndexed_stored_boolean");
							entityText = (Boolean)entiteDocumentSolr.get("entityText_stored_boolean");
							entityLanguage = (String)entiteDocumentSolr.get("entityLanguage_stored_string");
							entitySuggested = (Boolean)entiteDocumentSolr.get("entitySuggested_stored_boolean");

							if(classIndexed) {
								if(entityIndexed) {
									wVarIndexed.tl(3, "case \"", entityVar, "\":");
									wVarIndexed.tl(4, "return \"", entityVar, "_indexed", entityTypeSuffix, "\";");
								}
								if(entityText && entityLanguage != null) {
									wVarSearched.tl(3, "case \"", entityVar, "\":");
									wVarSearched.tl(4, "return \"", entityVar, "_text_" + entityLanguage, "\";");
								}
								if(entitySuggested) {
									wVarSuggested.tl(3, "case \"", entityVar, "\":");
									wVarSuggested.tl(4, "return \"", entityVar, "_suggested", "\";");
								}
							}
						}
						solrSearch.setStart(i.intValue() + searchLines);
						searchReponse = solrClientComputate.query(solrSearch);
						searchList = searchReponse.getResults();
					}
				}
			}

			l();
			tl(1, "public String var", str_Indexe(languageName), "", classSimpleName, "(String ", str_entite(languageName), "Var) {");
			tl(2, "switch(", str_entite(languageName), "Var) {");
			s(wVarIndexed);
			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", str_nest_pas_une_entite_indexe(languageName), ". \", ", str_entite(languageName), "Var));");
			tl(2, "}");
			tl(1, "}");

			l();
			tl(1, "public String var", str_Recherche(languageName), "", classSimpleName, "(String ", str_entite(languageName), "Var) {");
			tl(2, "switch(", str_entite(languageName), "Var) {");
			s(wVarSearched);
			s(wVarSuggested);
			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", str_nest_pas_une_entite_indexe(languageName), ". \", ", str_entite(languageName), "Var));");
			tl(2, "}");
			tl(1, "}");

			l();
			tl(1, "public String varSuggested", classSimpleName, "(String ", str_entite(languageName), "Var) {");
			tl(2, "switch(", str_entite(languageName), "Var) {");
			s(wVarSuggested);
			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", str_nest_pas_une_entite_indexe(languageName), ". \", ", str_entite(languageName), "Var));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "// Shared //");
			l();
			tl(1, "public void ", str_error(languageName), "", classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), ", Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ", AsyncResult<?> ", str_resultat(languageName), "Async) {");
			tl(2, "Throwable e = ", str_resultat(languageName), "Async.cause();");
			tl(2, "ExceptionUtils.printRootCauseStackTrace(e);");
			tl(2, "OperationResponse ", str_response(languageName), "Operation = new OperationResponse(400, \"BAD REQUEST\", ");
			tl(3, "Buffer.buffer().appendString(");
			tl(4, "new JsonObject() {{");
			tl(5, "put(\"", str_error(languageName), "\", new JsonObject() {{");
			tl(5, "put(\"message\", e.getMessage());");
			tl(5, "}});");
			tl(4, "}}.encodePrettily()");
			tl(3, ")");
			tl(3, ", new CaseInsensitiveHeaders()");
			tl(2, ");");
			tl(2, "if(", str_siteRequest(languageName), " != null) {");
			tl(3, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
			tl(3, "if(", str_sqlConnection(languageName), " != null) {");
			tl(4, "", str_sqlConnection(languageName), ".rollback(a -> {");
			tl(5, "if(a.succeeded()) {");
			tl(6, "", str_sqlConnection(languageName), ".close(b -> {");
			tl(7, "if(a.succeeded()) {");
			tl(8, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(", str_response(languageName), "Operation));");
			tl(7, "} else {");
			tl(8, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(", str_response(languageName), "Operation));");
			tl(7, "}");
			tl(6, "});");
			tl(5, "} else {");
			tl(6, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(", str_response(languageName), "Operation));");
			tl(5, "}");
			tl(4, "});");
			tl(3, "} else {");
			tl(4, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(", str_response(languageName), "Operation));");
			tl(3, "}");
			tl(2, "} else {");
			tl(3, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(", str_response(languageName), "Operation));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void sql", classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), ", Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
			tl(2, "try {");
			tl(3, "SQLClient ", str_sqlClient(languageName), " = ", str_siteRequest(languageName), ".get", str_SiteContext(languageName), "_().get", str_SqlClient(languageName), "();");
			l();
			tl(3, "if(", str_sqlClient(languageName), " == null) {");
			tl(4, "", str_eventHandler(languageName), ".handle(Future.succeededFuture());");
			tl(3, "} else {");
			tl(4, "", str_sqlClient(languageName), ".getConnection(sqlAsync -> {");
			tl(5, "if(sqlAsync.succeeded()) {");
			tl(6, "SQLConnection ", str_sqlConnection(languageName), " = sqlAsync.result();");
			tl(6, "", str_sqlConnection(languageName), ".setAutoCommit(false, a -> {");
			tl(7, "if(a.succeeded()) {");
			tl(8, "", str_siteRequest(languageName), ".set", str_SqlConnection(languageName), "(", str_sqlConnection(languageName), ");");
			tl(8, "", str_eventHandler(languageName), ".handle(Future.succeededFuture());");
			tl(7, "} else {");
			tl(8, "", str_eventHandler(languageName), ".handle(Future.failedFuture(a.cause()));");
			tl(7, "}");
			tl(6, "});");
			tl(5, "} else {");
			tl(6, "", str_eventHandler(languageName), ".handle(Future.failedFuture(sqlAsync.cause()));");
			tl(5, "}");
			tl(4, "});");
			tl(3, "}");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
	//		tl(1, "public ", classPartsSiteRequest.simpleName(languageName), " ", str_generate(languageName), "", classPartsSiteRequest.simpleName(languageName), "", str_Pour(languageName), "", classSimpleName, "(", classPartsSiteContext.simpleName(languageName), " ", str_siteContext(languageName), ", RoutingContext contexteItineraire) {");
			tl(1, "public ", classPartsSiteRequest.simpleName(languageName), " ", str_generate(languageName), "", classPartsSiteRequest.simpleName(languageName), "", str_Pour(languageName), "", classSimpleName, "(", classPartsSiteContext.simpleName(languageName), " ", str_siteContext(languageName), ", OperationRequest ", str_operationRequest(languageName), ") {");
			tl(2, "return ", str_generate(languageName), "", classPartsSiteRequest.simpleName(languageName), "", str_Pour(languageName), "", classSimpleName, "(", str_siteContext(languageName), ", ", str_operationRequest(languageName), ", null);");
			tl(1, "}");
			l();
			tl(1, "public ", classPartsSiteRequest.simpleName(languageName), " ", str_generate(languageName), "", classPartsSiteRequest.simpleName(languageName), "", str_Pour(languageName), "", classSimpleName, "(", classPartsSiteContext.simpleName(languageName), " ", str_siteContext(languageName), ", OperationRequest ", str_operationRequest(languageName), ", JsonObject body) {");
			tl(2, "Vertx vertx = ", str_siteContext(languageName), ".getVertx();");
			tl(2, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = new ", classPartsSiteRequest.simpleName(languageName), "();");
			tl(2, "", str_siteRequest(languageName), ".set", str_JsonObject(languageName), "(body);");
			tl(2, "", str_siteRequest(languageName), ".setVertx(vertx);");
	//		tl(2, "", str_siteRequest(languageName), ".setContexteItineraire(contexteItineraire);");
			tl(2, "", str_siteRequest(languageName), ".set", str_SiteContext(languageName), "_(", str_siteContext(languageName), ");");
			tl(2, "", str_siteRequest(languageName), ".set", classPartsSiteConfig.simpleName(languageName), "_(", str_siteContext(languageName), ".get", classPartsSiteConfig.simpleName(languageName), "());");
			tl(2, "", str_siteRequest(languageName), ".set", str_OperationRequest(languageName), "(", str_operationRequest(languageName), ");");
			tl(2, "", str_siteRequest(languageName), ".", str_initDeep(languageName), "", classPartsSiteRequest.simpleName(languageName), "(", str_siteRequest(languageName), ");");
			l();
			tl(2, "return ", str_siteRequest(languageName), ";");
			tl(1, "}");
			l();
			tl(1, "public void ", str_user(languageName), "", classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), ", Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
			tl(2, "try {");
			tl(3, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
			tl(3, "String ", str_user(languageName), "Id = ", str_siteRequest(languageName), ".get", str_User(languageName), "Id();");
			tl(3, "if(", str_user(languageName), "Id == null) {");
			tl(4, "", str_eventHandler(languageName), ".handle(Future.succeededFuture());");
			tl(3, "} else {");
			tl(4, "", str_sqlConnection(languageName), ".queryWithParams(");
			tl(6, "", classPartsSiteContext.simpleName(languageName), ".SQL_selectC");
			tl(6, ", new JsonArray(Arrays.asList(", q(classPartsSiteUser.canonicalName(languageName)), ", ", str_user(languageName), "Id))");
			tl(6, ", selectCAsync");
			tl(4, "-> {");
			tl(5, "if(selectCAsync.succeeded()) {");
//					tl(4, "", str_entite(languageName), "", str_Valeur(languageName), " = Optional.ofNullable(", str_solrDocument(languageName), ".getFieldValues(", q(", str_entite(languageName), "Var, "_stored", ", str_entite(languageName), "SuffixeType), ")).map(Collection<Object>::stream).orElseGet(Stream::empty).findFirst().orElse(null);");
			tl(6, "JsonArray ", str_user(languageName), "", str_Valeurs(languageName), " = selectCAsync.result().getResults().stream().findFirst().orElse(null);");
			tl(6, "if(", str_user(languageName), "", str_Valeurs(languageName), " == null) {");
			tl(7, "", str_sqlConnection(languageName), ".queryWithParams(");
			tl(9, "", classPartsSiteContext.simpleName(languageName), ".SQL_", str_create(languageName), "");
			tl(9, ", new JsonArray(Arrays.asList(", q(classPartsSiteUser.canonicalName(languageName)), ", ", str_user(languageName), "Id))");
			tl(9, ", ", str_create(languageName), "Async");
			tl(7, "-> {");
			tl(8, "JsonArray ", str_create(languageName), "", str_Ligne(languageName), " = ", str_create(languageName), "Async.result().getResults().stream().findFirst().orElseGet(() -> null);");
			tl(8, "Long ", classVarPrimaryKey, "", str_User(languageName), " = ", str_create(languageName), "", str_Ligne(languageName), ".getLong(0);");
			tl(8, classPartsSiteUser.simpleName(languageName), " ", str_userSite(languageName), " = new ", classPartsSiteUser.simpleName(languageName), "();");
			tl(8, "", str_userSite(languageName), ".set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, "", str_User(languageName), ");");
			l();
			tl(8, "", str_sqlConnection(languageName), ".queryWithParams(");
			tl(10, "", classPartsSiteContext.simpleName(languageName), ".SQL_", str_define(languageName), "");
			tl(10, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, "", str_User(languageName), ", ", classVarPrimaryKey, "", str_User(languageName), ", ", classVarPrimaryKey, "", str_User(languageName), "))");
			tl(10, ", ", str_define(languageName), "Async");
			tl(8, "-> {");
			tl(9, "if(", str_define(languageName), "Async.succeeded()) {");
			tl(10, "try {");
			tl(11, "for(JsonArray definition : ", str_define(languageName), "Async.result().getResults()) {");
			tl(12, "", str_userSite(languageName), ".", str_define(languageName), "", str_PourClasse(languageName), "(definition.getString(0), definition.getString(1));");
			tl(11, "}");
			tl(11, "JsonObject ", str_user(languageName), "Vertx = ", str_siteRequest(languageName), ".get", str_OperationRequest(languageName), "().getUser();");
			tl(11, "JsonObject ", str_principalJson(languageName), " = KeycloakHelper.parseToken(", str_user(languageName), "Vertx.getString(\"access_token\"));");
			tl(11, "", str_userSite(languageName), ".set", str_User(languageName), "", str_Nom(languageName), "(", str_principalJson(languageName), ".getString(\"preferred_username\"));");
			tl(11, "", str_userSite(languageName), ".set", str_User(languageName), "", str_Prenom(languageName), "(", str_principalJson(languageName), ".getString(\"given_name\"));");
			tl(11, "", str_userSite(languageName), ".set", str_User(languageName), "", str_NomFamille(languageName), "(", str_principalJson(languageName), ".getString(\"family_name\"));");
			tl(11, "", str_userSite(languageName), ".set", str_User(languageName), "Id(", str_principalJson(languageName), ".getString(\"sub\"));");
			tl(11, "", str_userSite(languageName), ".", str_initDeep(languageName), "", str_PourClasse(languageName), "(", str_siteRequest(languageName), ");");
			tl(11, "", str_userSite(languageName), ".", str_index(languageName), "", str_PourClasse(languageName), "();");
			tl(11, "", str_siteRequest(languageName), ".set", classPartsSiteUser.simpleName(languageName), "(", str_userSite(languageName), ");");
			tl(11, "", str_siteRequest(languageName), ".set", str_User(languageName), "", str_Nom(languageName), "(", str_principalJson(languageName), ".getString(\"preferred_username\"));");
			tl(11, "", str_siteRequest(languageName), ".set", str_User(languageName), "", str_Prenom(languageName), "(", str_principalJson(languageName), ".getString(\"given_name\"));");
			tl(11, "", str_siteRequest(languageName), ".set", str_User(languageName), "", str_NomFamille(languageName), "(", str_principalJson(languageName), ".getString(\"family_name\"));");
			tl(11, "", str_siteRequest(languageName), ".set", str_User(languageName), "Id(", str_principalJson(languageName), ".getString(\"sub\"));");
			tl(11, "", str_eventHandler(languageName), ".handle(Future.succeededFuture());");
			tl(10, "} catch(Exception e) {");
			tl(11, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
			tl(10, "}");
			tl(9, "} else {");
			tl(10, "", str_eventHandler(languageName), ".handle(Future.failedFuture(", str_define(languageName), "Async.cause()));");
			tl(9, "}");
			tl(8, "});");

			tl(7, "});");
			tl(6, "} else {");
			tl(7, "Long ", classVarPrimaryKey, "", str_User(languageName), " = ", str_user(languageName), "", str_Valeurs(languageName), ".getLong(0);");
			tl(7, classPartsSiteUser.simpleName(languageName), " ", str_userSite(languageName), " = new ", classPartsSiteUser.simpleName(languageName), "();");
			tl(7, "", str_userSite(languageName), ".set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, "", str_User(languageName), ");");
			l();
			tl(7, "", str_sqlConnection(languageName), ".queryWithParams(");
			tl(9, "", classPartsSiteContext.simpleName(languageName), ".SQL_", str_define(languageName), "");
			tl(9, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, "", str_User(languageName), ", ", classVarPrimaryKey, "", str_User(languageName), ", ", classVarPrimaryKey, "", str_User(languageName), "))");
			tl(9, ", ", str_define(languageName), "Async");
			tl(7, "-> {");
			tl(8, "if(", str_define(languageName), "Async.succeeded()) {");
			tl(9, "for(JsonArray definition : ", str_define(languageName), "Async.result().getResults()) {");
			tl(10, "", str_userSite(languageName), ".", str_define(languageName), "", str_PourClasse(languageName), "(definition.getString(0), definition.getString(1));");
			tl(9, "}");
			tl(9, "JsonObject ", str_user(languageName), "Vertx = ", str_siteRequest(languageName), ".get", str_OperationRequest(languageName), "().getUser();");
			tl(9, "JsonObject ", str_principalJson(languageName), " = KeycloakHelper.parseToken(", str_user(languageName), "Vertx.getString(\"access_token\"));");
			tl(9, "", str_userSite(languageName), ".set", str_User(languageName), "", str_Nom(languageName), "(", str_principalJson(languageName), ".getString(\"preferred_username\"));");
			tl(9, "", str_userSite(languageName), ".set", str_User(languageName), "", str_Prenom(languageName), "(", str_principalJson(languageName), ".getString(\"given_name\"));");
			tl(9, "", str_userSite(languageName), ".set", str_User(languageName), "", str_NomFamille(languageName), "(", str_principalJson(languageName), ".getString(\"family_name\"));");
			tl(9, "", str_userSite(languageName), ".set", str_User(languageName), "Id(", str_principalJson(languageName), ".getString(\"sub\"));");
			tl(9, "", str_userSite(languageName), ".", str_initDeep(languageName), "", str_PourClasse(languageName), "(", str_siteRequest(languageName), ");");
			tl(9, "", str_userSite(languageName), ".", str_index(languageName), "", str_PourClasse(languageName), "();");
			tl(9, "", str_siteRequest(languageName), ".set", classPartsSiteUser.simpleName(languageName), "(", str_userSite(languageName), ");");
			tl(9, "", str_siteRequest(languageName), ".set", str_User(languageName), "", str_Nom(languageName), "(", str_principalJson(languageName), ".getString(\"preferred_username\"));");
			tl(9, "", str_siteRequest(languageName), ".set", str_User(languageName), "", str_Prenom(languageName), "(", str_principalJson(languageName), ".getString(\"given_name\"));");
			tl(9, "", str_siteRequest(languageName), ".set", str_User(languageName), "", str_NomFamille(languageName), "(", str_principalJson(languageName), ".getString(\"family_name\"));");
			tl(9, "", str_siteRequest(languageName), ".set", str_User(languageName), "Id(", str_principalJson(languageName), ".getString(\"sub\"));");
			tl(9, "", str_eventHandler(languageName), ".handle(Future.succeededFuture());");
			tl(8, "} else {");
			tl(9, "", str_eventHandler(languageName), ".handle(Future.failedFuture(", str_define(languageName), "Async.cause()));");
			tl(8, "}");
			tl(7, "});");

			tl(6, "}");
			tl(5, "} else {");
			tl(6, "", str_eventHandler(languageName), ".handle(Future.failedFuture(selectCAsync.cause()));");
			tl(5, "}");
			tl(4, "});");
			tl(3, "}");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void ", str_search(languageName), "", classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), ", Boolean ", str_populate(languageName), ", Boolean ", str_store(languageName), ", String ", str_classApiUriMethod(languageName), ", Handler<AsyncResult<", classPartsSearchList.simpleName(languageName), "<", classSimpleName, ">>> ", str_eventHandler(languageName), ") {");
			tl(2, "try {");
			tl(3, "OperationRequest ", str_operationRequest(languageName), " = ", str_siteRequest(languageName), ".get", str_OperationRequest(languageName), "();");
			tl(3, "String ", str_entite(languageName), "", str_List(languageName), "Str = ", str_siteRequest(languageName), ".get", str_OperationRequest(languageName), "().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
			tl(3, "String[] ", str_entite(languageName), "", str_List(languageName), " = ", str_entite(languageName), "", str_List(languageName), "Str == null ? null : ", str_entite(languageName), "", str_List(languageName), "Str.split(", q(",\\s*"), ");");
			tl(3, classPartsSearchList.simpleName(languageName), "<", classSimpleName, "> ", str_list(languageName), "", str_Recherche(languageName), " = new ", classPartsSearchList.simpleName(languageName), "<", classSimpleName, ">();");
			tl(3, "", str_list(languageName), "", str_Recherche(languageName), ".set", str_Populate(languageName), "(", str_populate(languageName), ");");
			tl(3, "", str_list(languageName), "", str_Recherche(languageName), ".set", str_Store(languageName), "(", str_store(languageName), ");");
			tl(3, "", str_list(languageName), "", str_Recherche(languageName), ".setQuery(\"*:*\");");
			tl(3, "", str_list(languageName), "", str_Recherche(languageName), ".setC(", classSimpleName, ".class);");
			tl(3, "if(", str_entite(languageName), "", str_List(languageName), " != null)");
			tl(3, "", str_list(languageName), "", str_Recherche(languageName), ".setFields(", str_entite(languageName), "", str_List(languageName), ");");
			tl(3, "", str_list(languageName), "", str_Recherche(languageName), ".addSort(\"", str_archived(languageName), "_indexed_boolean\", ORDER.asc);");
			tl(3, "", str_list(languageName), "", str_Recherche(languageName), ".addSort(\"", str_deleted(languageName), "_indexed_boolean\", ORDER.asc);");
			tl(3, "", str_list(languageName), "", str_Recherche(languageName), ".addFilterQuery(\"", str_classCanonicalNames(languageName), "_indexed_strings:\" + ClientUtils.escapeQueryChars(", q(classCanonicalName), "));");
			if(classFiltersFound && classFilters.contains("userId"))
				tl(3, "", str_list(languageName), "", str_Recherche(languageName), ".addFilterQuery(\"", str_user(languageName), "Id_indexed_string:\" + ClientUtils.escapeQueryChars(", str_siteRequest(languageName), ".get", str_User(languageName), "Id()));");
			tl(3, classPartsSiteUser.simpleName(languageName), " ", str_userSite(languageName), " = ", str_siteRequest(languageName), ".get", classPartsSiteUser.simpleName(languageName), "();");
			tl(3, "if(", str_userSite(languageName), " != null && !", str_userSite(languageName), ".get", str_VoirDeleted(languageName), "())");
			tl(4, "", str_list(languageName), "", str_Recherche(languageName), ".addFilterQuery(\"", str_deleted(languageName), "_indexed_boolean:false\");");
			tl(3, "if(", str_userSite(languageName), " != null && !", str_userSite(languageName), ".get", str_VoirArchived(languageName), "())");
			tl(4, "", str_list(languageName), "", str_Recherche(languageName), ".addFilterQuery(\"", str_archived(languageName), "_indexed_boolean:false\");");
			l();
			tl(3, "String id = ", str_operationRequest(languageName), ".getParams().getJsonObject(\"path\").getString(\"id\");");
			tl(3, "if(", classVarUniqueKey, " != null) {");
			tl(4, "", str_list(languageName), "", str_Recherche(languageName), ".addFilterQuery(\"", classeVarId, "_indexed_string:\" + ClientUtils.escapeQueryChars(id));");
			tl(3, "}");
			l();
			tl(3, "", str_operationRequest(languageName), ".getParams().getJsonObject(\"query\").forEach(param", str_Requete(languageName), " -> {");
			tl(4, "String ", str_entite(languageName), "Var = null;");
			tl(4, "String ", str_valeur(languageName), "", str_Indexe(languageName), " = null;");
			tl(4, "String var", str_Indexe(languageName), " = null;");
			tl(4, "String ", str_valeur(languageName), "", str_Tri(languageName), " = null;");
			tl(4, "Integer ", str_search(languageName), "", str_Debut(languageName), " = null;");
			tl(4, "Integer ", str_search(languageName), "Num = null;");
			tl(4, "String param", str_Nom(languageName), " = param", str_Requete(languageName), ".getKey();");
			tl(4, "Object param", str_Valeurs(languageName), "", str_Objet(languageName), " = param", str_Requete(languageName), ".getValue();");
			tl(4, "JsonArray param", str_Objets(languageName), " = param", str_Valeurs(languageName), "", str_Objet(languageName), " instanceof JsonArray ? (JsonArray)param", str_Valeurs(languageName), "", str_Objet(languageName), " : new JsonArray().add(param", str_Valeurs(languageName), "", str_Objet(languageName), ");");
			l();
			tl(4, "try {");
			tl(5, "for(Object param", str_Objet(languageName), " : param", str_Objets(languageName), ") {");
			tl(6, "switch(param", str_Nom(languageName), ") {");
	
			tl(7, "case \"q\":");
			tl(8, "", str_entite(languageName), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(languageName), ", \":\"));");
			tl(8, "var", str_Indexe(languageName), " = \"*\".equals(", str_entite(languageName), "Var) ? ", str_entite(languageName), "Var : var", str_Recherche(languageName), "", classSimpleName, "(", str_entite(languageName), "Var);");
			tl(8, "", str_valeur(languageName), "", str_Indexe(languageName), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(languageName), ", \":\")), \"UTF-8\");");
			tl(8, "", str_valeur(languageName), "", str_Indexe(languageName), " = StringUtils.isEmpty(", str_valeur(languageName), "", str_Indexe(languageName), ") ? \"*\" : ", str_valeur(languageName), "", str_Indexe(languageName), ";");
//			tl(8, "if(StringUtils.isEmpty(", str_valeur(languageName), "", str_Indexe(languageName), ")) {");
//			tl(9, "", str_valeur(languageName), "", str_Indexe(languageName), " = ", str_entite(languageName), "Var;");
//			tl(9, "", str_entite(languageName), "Var = \"*\";");
//			tl(8, "}");
			tl(8, "", str_list(languageName), "", str_Recherche(languageName), ".setQuery(var", str_Indexe(languageName), " + \":\" + (\"*\".equals(", str_valeur(languageName), "", str_Indexe(languageName), ") ? ", str_valeur(languageName), "", str_Indexe(languageName), " : ClientUtils.escapeQueryChars(", str_valeur(languageName), "", str_Indexe(languageName), ")));");
			tl(8, "if(!\"*\".equals(", str_entite(languageName), "Var)) {");
			tl(9, "", str_list(languageName), "", str_Recherche(languageName), ".setHighlight(true);");
			tl(9, "", str_list(languageName), "", str_Recherche(languageName), ".setHighlightSnippets(3);");
			tl(9, "", str_list(languageName), "", str_Recherche(languageName), ".addHighlightField(var", str_Indexe(languageName), ");");
			tl(9, "", str_list(languageName), "", str_Recherche(languageName), ".setParam(\"hl.encoder\", \"html\");");
			tl(8, "}");
			tl(8, "break;");
	
			tl(7, "case \"fq\":");
			tl(8, "", str_entite(languageName), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(languageName), ", \":\"));");
			tl(8, "", str_valeur(languageName), "", str_Indexe(languageName), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(languageName), ", \":\")), \"UTF-8\");");
			tl(8, "var", str_Indexe(languageName), " = var", str_Indexe(languageName), "", classSimpleName, "(", str_entite(languageName), "Var);");
			tl(8, "", str_list(languageName), "", str_Recherche(languageName), ".addFilterQuery(var", str_Indexe(languageName), " + \":\" + ClientUtils.escapeQueryChars(", str_valeur(languageName), "", str_Indexe(languageName), "));");
			tl(8, "break;");
	
			tl(7, "case \"sort\":");
			tl(8, "", str_entite(languageName), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(languageName), ", \" \"));");
			tl(8, "", str_valeur(languageName), "", str_Tri(languageName), " = StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(languageName), ", \" \"));");
			tl(8, "var", str_Indexe(languageName), " = var", str_Indexe(languageName), "", classSimpleName, "(", str_entite(languageName), "Var);");
			tl(8, "", str_list(languageName), "", str_Recherche(languageName), ".addSort(var", str_Indexe(languageName), ", ORDER.valueOf(", str_valeur(languageName), "", str_Tri(languageName), "));");
			tl(8, "break;");
	
			tl(7, "case \"fl\":");
			tl(8, "", str_entite(languageName), "Var = StringUtils.trim((String)param", str_Objet(languageName), ");");
			tl(8, "var", str_Indexe(languageName), " = var", str_Indexe(languageName), "", classSimpleName, "(", str_entite(languageName), "Var);");
			tl(8, "", str_list(languageName), "", str_Recherche(languageName), ".addField(var", str_Indexe(languageName), ");");
			tl(8, "break;");
	
			tl(7, "case \"start\":");
			tl(8, "", str_search(languageName), "", str_Debut(languageName), " = (Integer)param", str_Objet(languageName), ";");
			tl(8, "", str_list(languageName), "", str_Recherche(languageName), ".setStart(", str_search(languageName), "", str_Debut(languageName), ");");
			tl(8, "break;");
	
			tl(7, "case \"rows\":");
			tl(8, "", str_search(languageName), "Num = (Integer)param", str_Objet(languageName), ";");
			tl(8, "", str_list(languageName), "", str_Recherche(languageName), ".setRows(", str_search(languageName), "Num);");
			tl(8, "break;");
	
			tl(6, "}");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
			tl(4, "}");

			tl(3, "});");
			tl(3, "", str_list(languageName), "", str_Recherche(languageName), ".", str_initDeep(languageName), "", str_PourClasse(languageName), "(", str_siteRequest(languageName), ");");
			tl(3, "", str_eventHandler(languageName), ".handle(Future.succeededFuture(", str_list(languageName), "", str_Recherche(languageName), "));");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void ", str_define(languageName), "", classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
			tl(2, "try {");
			tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = o.get", str_SiteRequest(languageName), "_();");
			tl(3, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
			tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
			tl(3, "", str_sqlConnection(languageName), ".queryWithParams(");
			tl(5, "", classPartsSiteContext.simpleName(languageName), ".SQL_", str_define(languageName), "");
			tl(5, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, ", ", classVarPrimaryKey, ", ", classVarPrimaryKey, "))");
			tl(5, ", ", str_define(languageName), "Async");
			tl(3, "-> {");
			tl(4, "if(", str_define(languageName), "Async.succeeded()) {");
			tl(5, "try {");
			tl(6, "for(JsonArray definition : ", str_define(languageName), "Async.result().getResults()) {");
			tl(7, "o.", str_define(languageName), "", str_PourClasse(languageName), "(definition.getString(0), definition.getString(1));");
			tl(6, "}");
			tl(6, str_eventHandler(languageName), ".handle(Future.succeededFuture());");
			tl(5, "} catch(Exception e) {");
			tl(6, str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
			tl(5, "}");
			tl(4, "} else {");
			tl(5, "", str_eventHandler(languageName), ".handle(Future.failedFuture(", str_define(languageName), "Async.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void ", str_attribute(languageName), "", classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
			tl(2, "try {");
			tl(3, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = o.get", str_SiteRequest(languageName), "_();");
			tl(3, "SQLConnection ", str_sqlConnection(languageName), " = ", str_siteRequest(languageName), ".get", str_SqlConnection(languageName), "();");
			tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
			tl(3, "", str_sqlConnection(languageName), ".queryWithParams(");
			tl(5, "", classPartsSiteContext.simpleName(languageName), ".SQL_", str_attribute(languageName), "");
			tl(5, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, ", ", classVarPrimaryKey, "))");
			tl(5, ", ", str_attribute(languageName), "Async");
			tl(3, "-> {");
			tl(4, "try {");
			tl(5, "if(", str_attribute(languageName), "Async.succeeded()) {");
			tl(6, "if(", str_attribute(languageName), "Async.result() != null) {");
			tl(7, "for(JsonArray definition : ", str_attribute(languageName), "Async.result().getResults()) {");
			tl(8, "if(pk.equals(definition.getLong(0)))");
			tl(9, "o.", str_attribute(languageName), "", str_PourClasse(languageName), "(definition.getString(2), definition.getLong(1));");
			tl(8, "else");
			tl(9, "o.", str_attribute(languageName), "", str_PourClasse(languageName), "(definition.getString(3), definition.getLong(0));");
			tl(7, "}");
			tl(6, "}");
			tl(6, "", str_eventHandler(languageName), ".handle(Future.succeededFuture());");
			tl(5, "} else {");
			tl(6, "", str_eventHandler(languageName), ".handle(Future.failedFuture(", str_attribute(languageName), "Async.cause()));");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void ", str_index(languageName), "", classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> ", str_eventHandler(languageName), ") {");
			tl(2, "", classPartsSiteRequest.simpleName(languageName), " ", str_siteRequest(languageName), " = o.get", str_SiteRequest(languageName), "_();");
			tl(2, "try {");
			tl(3, "o.", str_initDeep(languageName), "", str_PourClasse(languageName), "(", str_siteRequest(languageName), ");");
			tl(3, "o.", str_index(languageName), "", str_PourClasse(languageName), "();");
			tl(3, "", str_eventHandler(languageName), ".handle(Future.succeededFuture());");
			tl(2, "} catch(Exception e) {");
			tl(3, "", str_eventHandler(languageName), ".handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
	
			tl(0, "}");

			writerGenApiServiceImpl.flushClose();
			System.out.println("Write: " + classPathGenApiServiceImpl); 
		}
	}
}
