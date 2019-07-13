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
			writerGenApiService.l(" * Traduire: false");
			writerGenApiService.l(" * Gen: false");
			writerGenApiService.l(" **/");
			writerGenApiService.l("@WebApiServiceGen");
			writerGenApiService.l("@ProxyGen");
			writerGenApiService.s("public interface ", classSimpleNameGenApiService, " {");
			writerGenApiService.l();
			writerGenApiService.tl(1, "// A factory method to create an instance and a proxy. ");
			writerGenApiService.tl(1, "static void enregistrerService(", classPartsSiteContext.simpleName(languageName), " siteContext, Vertx vertx) {");
			writerGenApiService.tl(2, "new ServiceBinder(vertx).setAddress(", q(languageName, classSimpleName), ").register(", classSimpleNameGenApiService, ".class, new ", classSimpleNameApiServiceImpl, "(siteContext));");
			writerGenApiService.tl(1, "}");
			writerGenApiService.l();
			writerGenApiService.tl(1, "// A factory method to create an instance and a proxy. ");
			writerGenApiService.tl(1, "static ", classSimpleNameGenApiService, " creer(", classPartsSiteContext.simpleName(languageName), " siteContext, Vertx vertx) {");
			writerGenApiService.tl(2, "return new ", classSimpleNameApiServiceImpl, "(siteContext);");
			writerGenApiService.tl(1, "}");
			writerGenApiService.l();
			writerGenApiService.tl(1, "// A factory method to create an instance and a proxy. ");
			writerGenApiService.tl(1, "static ", classSimpleNameGenApiService, " creerProxy(Vertx vertx, String address) {");
			writerGenApiService.tl(2, "return new ", classSimpleNameGenApiService, "VertxEBProxy(vertx, address);");
			writerGenApiService.tl(1, "}");
			writerGenApiService.l();
			for(String classApiMethod : classApiMethods) {
				String classApiOperationIdMethod = (String)classDoc.get("classApiOperationId" + classApiMethod + "_stored_string");
				String classPageCanonicalNameMethod = (String)classDoc.get("classPageCanonicalName" + classApiMethod + "_stored_string");
				String classPageLanguageName = (String)classDoc.get("classPageLanguageName" + classApiMethod + "_stored_string");

				if(classPageLanguageName == null || classPageLanguageName.equals(languageName)) {
					if(classPageCanonicalNameMethod != null) {
						writerGenApiService.t(1, "public void ", classApiOperationIdMethod, "Id(");
						if(StringUtils.containsAny(classApiMethod, "POST", "PUT", "PATCH"))
							writerGenApiService.s("JsonObject body, ");
						writerGenApiService.l("OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);");
					}
	
					writerGenApiService.t(1, "public void ", classApiOperationIdMethod, "(");
					if(StringUtils.containsAny(classApiMethod, "POST", "PUT", "PATCH"))
						writerGenApiService.s("JsonObject body, ");
					writerGenApiService.l("OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler);");
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
			writerApiServiceImpl.l(" * Translate: false");
			writerApiServiceImpl.l(" **/");
			writerApiServiceImpl.l("public class ", classSimpleNameApiServiceImpl, " extends ", classSimpleNameGenApiServiceImpl, " {");
			writerApiServiceImpl.l();
			writerApiServiceImpl.tl(1, "public ", classSimpleNameApiServiceImpl, "(", classPartsSiteContext.simpleName(languageName), " siteContext) {");
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
				solrSearch.addFilterQuery("classCanonicalName_" + languageName + "_indexed_string:" + fqClassesSuperEtMoi);
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
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Boolean)entityValue).toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameDate.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameTimestamp.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameZonedDateTime.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toZonedDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameLocalDateTime.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameLocalDate.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameLong.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Long)entityValue).toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameBigDecimal.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(BigDecimal.valueOf((Double)entityValue).toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameDouble.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Double)entityValue).toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameFloat.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Float)entityValue).toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else if(VAL_canonicalNameInteger.equals(entityCanonicalNameGeneric)) {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(((Integer)entityValue).toString());");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
									else {
										l();
										tl(4, "{");
										tl(5, entitySimpleNameComplete, " entityValues = o.get", entityVarCapitalized, "();");
										tl(5, "w.t(3, entityNumber++ == 0 ? \"\" : \", \");");
										tl(5, "w.s(\"\\\"", entityVar, "\\\": [\");");
										tl(5, "for(int k = 0; k < entityValues.size(); k++) {");
										tl(6, "entityValue = entityValues.get(k);");
										tl(6, "if(k > 0)");
										tl(7, "w.s(\", \");");
										tl(6, "w.s(\"\\\"\");");
										tl(6, "w.s(((String)entityValue));");
										tl(6, "w.s(\"\\\"\");");
										tl(5, "}");
										tl(5, "w.l(\"]\");");
										tl(4, "}");
									}
								}
								else {
									l();
									tl(4, "entityValue = o.get", entityVarCapitalized, "();");
				//					tl(4, "entityValue = Optional.ofNullable(solrDocument.getFieldValues(", q(entityVar, "_stored", entityTypeSuffix), ")).map(Collection<Object>::stream).orElseGet(Stream::empty).findFirst().orElse(null);");
				//					tl(4, "entityValue = solrDocument.getFieldValues(", q(entityVar, "_stored", entityTypeSuffix), ").stream().findFirst().orElse(null);");
				//					tl(5, "entityValue = solrDocument.getFieldValues(", q(entityVar, "_stored", entityTypeSuffix), ").stream().findFirst().orElse(null);");
									tl(4, "if(entityValue != null)");
									if (VAL_canonicalNameBoolean.equals(entitySolrCanonicalName)) {
				//						tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
				
										// tomorrow put this line everywhere. 
										tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", entityValue);");
									} else if (VAL_canonicalNameDate.equals(entitySolrCanonicalName)) {
										if (VAL_canonicalNameTimestamp.equals(entityCanonicalName)) {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(entityValue));");
										} else if (VAL_canonicalNameZonedDateTime.equals(entityCanonicalName)) {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toZonedDateTime());");
											tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(entityValue));");
										} else if (VAL_canonicalNameLocalDateTime.equals(entityCanonicalName)) {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(entityValue));");
										} else if (VAL_canonicalNameLocalDate.equals(entityCanonicalName)) {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());");
											tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(entityValue));");
										} else {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)entityValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());");
											tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(entityValue));");
										}
									} else if (VAL_canonicalNameLong.equals(entitySolrCanonicalName)) {
				//						tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
										tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", entityValue);");
									} else if (VAL_canonicalNameDouble.equals(entitySolrCanonicalName)) {
										if (VAL_canonicalNameBigDecimal.equals(entityCanonicalName)) {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
											tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", entityValue);");
										}
										else {
				//							tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
											tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", entityValue);");
										}
									} else if (VAL_canonicalNameFloat.equals(entitySolrCanonicalName)) {
				//						tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
										tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", entityValue);");
									} else if (VAL_canonicalNameInteger.equals(entitySolrCanonicalName)) {
				//						tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
										tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", entityValue);");
									}
									else {
				//						tl(5, "Object entityStr = entityValue == null ? ", q("null"), " : entityValue;");
										tl(5, "w.tl(3, entityNumber++ == 0 ? ", q(), " : ", q(", "), ", ", q(q(entityVar), ": "), ", w.qjs(entityValue));");
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
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", requestJson.get", entityListSimpleNameVertxJson, "(methodName)", "));");
					
											tl(tBase + 2, "case \"addAll", entityVarCapitalized, "\":");
											tl(tBase + 3, entitySimpleNameVertxJson, " addAll", entityVarCapitalized, "Valeurs = requestJson.get", entitySimpleNameVertxJson, "(methodName);");
											tl(tBase + 3, "for(Integer i = 0; i <  addAll", entityVarCapitalized, "Valeurs.size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_addA);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", addAll", entityVarCapitalized, "Valeurs.get", entityListSimpleNameVertxJson, "(i)", "));");
											tl(tBase + 3, "}");
						
											tl(tBase + 2, "case \"set", entityVarCapitalized, "\":");
											tl(tBase + 3, entitySimpleNameVertxJson, " set", entityVarCapitalized, "Valeurs = requestJson.get", entitySimpleNameVertxJson, "(methodName);");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_clearA1);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", requestJson.get", entitySimpleNameVertxJson, "(methodName)", "));");
					
											tl(tBase + 3, "for(Integer i = 0; i <  set", entityVarCapitalized, "Valeurs.size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_addA);");
											tl(tBase + 4, "patchSqlParams.set(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", addAll", entityVarCapitalized, "Valeurs.get", entityListSimpleNameVertxJson, "(i)", "));");
											tl(tBase + 3, "}");
										}
										else {
											tl(tBase + 2, "case \"add", entityVarCapitalized, "\":");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_addA);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", requestJson.get", entityListSimpleNameVertxJson, "(methodName)", "));");
					
											tl(tBase + 2, "case \"addAll", entityVarCapitalized, "\":");
											tl(tBase + 3, entitySimpleNameVertxJson, " addAll", entityVarCapitalized, "Valeurs = requestJson.get", entitySimpleNameVertxJson, "(methodName);");
											tl(tBase + 3, "for(Integer i = 0; i <  addAll", entityVarCapitalized, "Valeurs.size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setA2);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entityAttributeVar), ", ", "addAll", entityVarCapitalized, "Valeurs.get", entityListSimpleNameVertxJson, "(i)", q(entityVar), ", ", classVarPrimaryKey, "));");
											tl(tBase + 3, "}");
						
											tl(tBase + 2, "case \"set", entityVarCapitalized, "\":");
											tl(tBase + 3, entitySimpleNameVertxJson, " set", entityVarCapitalized, "Valeurs = requestJson.get", entitySimpleNameVertxJson, "(methodName);");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_clearA2);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityAttributeVar), ", requestJson.get", entityListSimpleNameVertxJson, "(methodName)", ", ", q(entityVar), ", ", classVarPrimaryKey, "));");
					
											tl(tBase + 3, "for(Integer i = 0; i <  set", entityVarCapitalized, "Valeurs.size(); i++) {");
											tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setA2);");
											tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entityAttributeVar), ", set", entityVarCapitalized, "Valeurs.get", entityListSimpleNameVertxJson, "(i)", q(entityVar), ", ", classVarPrimaryKey, "));");
											tl(tBase + 3, "}");
										}
									}
									else {
						
										tl(tBase + 2, "case \"set", entityVarCapitalized, "\":");
										if(StringUtils.compare(entityVar, entityAttributeVar) <= 0) {
											tl(tBase + 3, "o2.set", entityVarCapitalized, "(requestJson.get", entitySimpleNameVertxJson, "(methodName));");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setA1);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", ", classVarPrimaryKey, ", ", q(entityAttributeVar), ", o2.get", entityVarCapitalized, "()));");
										}
										else {
											tl(tBase + 3, "o2.set", entityVarCapitalized, "(requestJson.get", entitySimpleNameVertxJson, "(methodName));");
											tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setA2);");
											tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(", q(entityAttributeVar), ", o2.get", entityVarCapitalized, "()", ", ", q(entityVar), ", ", classVarPrimaryKey, "));");
										}
									}
						
									tl(tBase + 7, "break;");
								}
								else if(BooleanUtils.isTrue(entityDefine)) {
									if(StringUtils.equals(entityCanonicalName, List.class.getCanonicalName()) || StringUtils.equals(entityCanonicalName, ArrayList.class.getCanonicalName())) {
						
										tl(tBase + 2, "case \"add", entityVarCapitalized, "\":");
										tl(tBase + 3, "requestJson.getJsonArray(methodName).forEach((v) -> {");
										tl(tBase + 4, "o2.add", entityVarCapitalized, "((", entityListSimpleNameVertxJson, ")v);");
										tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_addA);");
										tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(", q(entityVar), ", o2.get", entityVarCapitalized, "()", ", ", classVarPrimaryKey, "));");
										tl(tBase + 3, "});");
						
										tl(tBase + 2, "case \"set", entityVarCapitalized, "\":");
										tl(tBase + 3, "o2.get", entityVarCapitalized, "().clear();");
										tl(tBase + 3, "requestJson.getJsonArray(methodName).forEach((v) -> {");
										tl(tBase + 4, "o2.add", entityVarCapitalized, "((", entityListSimpleNameVertxJson, ")v);");
										tl(tBase + 4, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setD);");
										tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(\"", entityVar, "\", o2.get", entityVarCapitalized, "(), ", classVarPrimaryKey, "));");
										tl(tBase + 3, "});");
									}
									else {
						
										tl(tBase + 2, "case \"set", entityVarCapitalized, "\":");
										tl(tBase + 3, "o2.set", entityVarCapitalized, "(requestJson.get", entitySimpleNameVertxJson, "(methodName));");
										tl(tBase + 3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_setD);");
										tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(\"", entityVar, "\", o2.get", entityVarCapitalized, "(), ", classVarPrimaryKey, "));");
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
			l(" * Translate: false");
			l(" **/");
			s("public class ", classSimpleNameGenApiServiceImpl);
			s(" implements ", classSimpleNameGenApiService);
			l(" {");
			l();
			tl(1, "protected static final Logger LOGGER = LoggerFactory.getLogger(", classSimpleNameGenApiServiceImpl, ".class);");
			l();
			tl(1, "protected static final String SERVICE_ADDRESS = \"", classSimpleNameApiServiceImpl, "\";");
			l();
			tl(1, "protected ", classPartsSiteContext.simpleName(languageName), " siteContext;");
			l();
			tl(1, "public ", classSimpleNameGenApiServiceImpl, "(", classPartsSiteContext.simpleName(languageName), " siteContext) {");
			tl(2, "this.siteContext = siteContext;");
			tl(2, classSimpleNameGenApiService, " service = ", classSimpleNameGenApiService, ".createProxy(siteContext.getVertx(), SERVICE_ADDRESS);");
			tl(1, "}");

			for(String classApiMethod : classApiMethods) {
				String classPageCanonicalNameMethod = (String)classDoc.get("classPageCanonicalName" + classApiMethod + "_stored_string");
				String classPageSimpleNameMethod = (String)classDoc.get("classPageSimpleName" + classApiMethod + "_stored_string");
				String classApiOperationIdMethod = (String)classDoc.get("classApiOperationId" + classApiMethod + "_stored_string");
				String classApiUriMethod = (String)classDoc.get("classApiUri" + classApiMethod + "_stored_string");
				String classApiMediaTypeMethode = (String)classDoc.get("classApiMediaType" + classApiMethod + "_stored_string");
				String classPageLanguageName = (String)classDoc.get("classPageLanguageName" + classApiMethod + "_stored_string");
				if(classPageLanguageName == null || classPageLanguageName.equals(languageName)) {
					l();
					tl(1, "// ", classApiMethod, " //");
					if(classPageCanonicalNameMethod != null) {
						l();
						tl(1, "@Override");
						t(1, "public void ", classApiOperationIdMethod, "Id(");
						if(StringUtils.containsAny(classApiMethod, "POST", "PUT", "PATCH"))
							s("JsonObject body, ");
						l("OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {");
						tl(2, classApiOperationIdMethod, "(operationRequest, eventHandler);");
						tl(1, "}");
					}
					l();
					tl(1, "@Override");
					t(1, "public void ", classApiOperationIdMethod, "(");
					if(StringUtils.containsAny(classApiMethod, "POST", "PUT", "PATCH"))
						s("JsonObject body, ");
					l("OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {");
	
					if(classApiMethod.contains("POST")) {
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = generate", classPartsSiteRequest.simpleName(languageName), "For", classSimpleName, "(siteContext, operationRequest, body);");
						tl(3, "sql", classSimpleName, "(siteRequest, a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "create", classApiMethod, classSimpleName, "(siteRequest, b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, classSimpleName, " ", StringUtils.uncapitalize(classSimpleName), " = b.result();");
						tl(7, "sql", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "define", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "attribute", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", e -> {");
						tl(12, "if(e.succeeded()) {");
						tl(13, "index", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", f -> {");
						tl(14, "if(f.succeeded()) {");
						tl(15, "response200", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", g -> {");
						tl(16, "if(f.succeeded()) {");
						tl(17, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
						tl(17, "sqlConnection.commit(h -> {");
						tl(18, "if(a.succeeded()) {");
						tl(19, "sqlConnection.close(i -> {");
						tl(20, "if(a.succeeded()) {");
						tl(21, "eventHandler.handle(Future.succeededFuture(g.result()));");
						tl(20, "} else {");
						tl(21, "error", classSimpleName, "(siteRequest, eventHandler, i);");
						tl(20, "}");
						tl(19, "});");
						tl(18, "} else {");
						tl(19, "error", classSimpleName, "(siteRequest, eventHandler, h);");
						tl(18, "}");
						tl(17, "});");
						tl(16, "} else {");
						tl(17, "error", classSimpleName, "(siteRequest, eventHandler, g);");
						tl(16, "}");
						tl(15, "});");
						tl(14, "} else {");
						tl(15, "error", classSimpleName, "(siteRequest, eventHandler, f);");
						tl(14, "}");
						tl(13, "});");
						tl(12, "} else {");
						tl(13, "error", classSimpleName, "(siteRequest, eventHandler, e);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} else {");
						tl(11, "error", classSimpleName, "(siteRequest, eventHandler, d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "error", classSimpleName, "(siteRequest, eventHandler, c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "error", classSimpleName, "(siteRequest, eventHandler, b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "error", classSimpleName, "(siteRequest, eventHandler, a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "error", classSimpleName, "(null, eventHandler, Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classApiMethod.contains("PATCH")) {
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = generate", classPartsSiteRequest.simpleName(languageName), "For", classSimpleName, "(siteContext, operationRequest, body);");
						tl(3, "sql", classSimpleName, "(siteRequest, a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "user", classSimpleName, "(siteRequest, b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, "search", classSimpleName, "(siteRequest, false, true, ", "null", ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "SearchList<", classSimpleName, "> list", classSimpleName, " = c.result();");
						tl(9, "list", classApiMethod, classSimpleName, "(list", classSimpleName, ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
						tl(11, "if(sqlConnection == null) {");
						tl(12, "eventHandler.handle(Future.succeededFuture(d.result()));");
						tl(11, "} else {");
						tl(12, "sqlConnection.commit(e -> {");
						tl(13, "if(e.succeeded()) {");
						tl(14, "sqlConnection.close(f -> {");
						tl(15, "if(f.succeeded()) {");
						tl(16, "eventHandler.handle(Future.succeededFuture(d.result()));");
						tl(15, "} else {");
						tl(16, "error", classSimpleName, "(siteRequest, eventHandler, f);");
						tl(15, "}");
						tl(14, "});");
						tl(13, "} else {");
						tl(14, "error", classSimpleName, "(siteRequest, eventHandler, e);");
						tl(13, "}");
						tl(12, "});");
						tl(11, "}");
						tl(10, "} else {");
						tl(11, "error", classSimpleName, "(siteRequest, eventHandler, d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "error", classSimpleName, "(siteRequest, eventHandler, c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "error", classSimpleName, "(siteRequest, eventHandler, b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "error", classSimpleName, "(siteRequest, eventHandler, a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "error", classSimpleName, "(null, eventHandler, Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classApiMethod.contains("Search")) {
						if(classPageSimpleNameMethod == null) {
							tl(2, "try {");
							tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = generate", classPartsSiteRequest.simpleName(languageName), "For", classSimpleName, "(siteContext, operationRequest);");
							tl(3, "search", classSimpleName, "(siteRequest, false, true, ", "null", ", a -> {");
							tl(4, "if(a.succeeded()) {");
							tl(5, "SearchList<", classSimpleName, "> list", classSimpleName, " = a.result();");
							tl(5, "response200", classApiMethod, classSimpleName, "(list", classSimpleName, ", b -> {");
							tl(6, "if(b.succeeded()) {");
							tl(7, "eventHandler.handle(Future.succeededFuture(b.result()));");
							tl(6, "} else {");
							tl(7, "error", classSimpleName, "(siteRequest, eventHandler, b);");
							tl(6, "}");
							tl(5, "});");
							tl(4, "} else {");
							tl(5, "error", classSimpleName, "(siteRequest, eventHandler, a);");
							tl(4, "}");
							tl(3, "});");
							tl(2, "} catch(Exception e) {");
							tl(3, "error", classSimpleName, "(null, eventHandler, Future.failedFuture(e));");
							tl(2, "}");
						}
						else {
							tl(2, "try {");
							tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = generate", classPartsSiteRequest.simpleName(languageName), "For", classSimpleName, "(siteContext, operationRequest);");
							tl(3, "sql", classSimpleName, "(siteRequest, a -> {");
							tl(4, "if(a.succeeded()) {");
							tl(5, "user", classSimpleName, "(siteRequest, b -> {");
							tl(6, "if(b.succeeded()) {");
							tl(7, "search", classSimpleName, "(siteRequest, false, true, ", q(classApiUriMethod), ", c -> {");
							tl(8, "if(c.succeeded()) {");
							tl(9, "SearchList<", classSimpleName, "> list", classSimpleName, " = c.result();");
							tl(9, "response200", classApiMethod, classSimpleName, "(list", classSimpleName, ", d -> {");
							tl(10, "if(d.succeeded()) {");
							tl(11, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
							tl(11, "if(sqlConnection == null) {");
							tl(12, "eventHandler.handle(Future.succeededFuture(d.result()));");
							tl(11, "} else {");
							tl(12, "sqlConnection.commit(e -> {");
							tl(13, "if(e.succeeded()) {");
							tl(14, "sqlConnection.close(f -> {");
							tl(15, "if(f.succeeded()) {");
							tl(16, "eventHandler.handle(Future.succeededFuture(d.result()));");
							tl(15, "} else {");
							tl(16, "error", classSimpleName, "(siteRequest, eventHandler, f);");
							tl(15, "}");
							tl(14, "});");
							tl(13, "} else {");
							tl(14, "error", classSimpleName, "(siteRequest, eventHandler, e);");
							tl(13, "}");
							tl(12, "});");
							tl(11, "}");
							tl(10, "} else {");
							tl(11, "error", classSimpleName, "(siteRequest, eventHandler, d);");
							tl(10, "}");
							tl(9, "});");
							tl(8, "} else {");
							tl(9, "error", classSimpleName, "(siteRequest, eventHandler, c);");
							tl(8, "}");
							tl(7, "});");
							tl(6, "} else {");
							tl(7, "error", classSimpleName, "(siteRequest, eventHandler, b);");
							tl(6, "}");
							tl(5, "});");
							tl(4, "} else {");
							tl(5, "error", classSimpleName, "(siteRequest, eventHandler, a);");
							tl(4, "}");
							tl(3, "});");
							tl(2, "} catch(Exception e) {");
							tl(3, "error", classSimpleName, "(null, eventHandler, Future.failedFuture(e));");
							tl(2, "}");
						}
					}
					else if(classApiMethod.contains("GET")) {
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = generate", classPartsSiteRequest.simpleName(languageName), "For", classSimpleName, "(siteContext, operationRequest);");
						tl(3, "search", classSimpleName, "(siteRequest, false, true, ", "null", ", a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "SearchList<", classSimpleName, "> list", classSimpleName, " = a.result();");
						tl(5, "response200", classApiMethod, classSimpleName, "(list", classSimpleName, ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, "eventHandler.handle(Future.succeededFuture(b.result()));");
						tl(6, "} else {");
						tl(7, "error", classSimpleName, "(siteRequest, eventHandler, b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "error", classSimpleName, "(siteRequest, eventHandler, a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "error", classSimpleName, "(null, eventHandler, Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classApiMethod.contains("PUT")) {
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = generate", classPartsSiteRequest.simpleName(languageName), "For", classSimpleName, "(siteContext, operationRequest, body);");
						tl(3, "sql", classSimpleName, "(siteRequest, a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "replace", classApiMethod, classSimpleName, "(siteRequest, b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, classSimpleName, " ", StringUtils.uncapitalize(classSimpleName), " = b.result();");
						tl(7, "sql", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "define", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "attribute", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", e -> {");
						tl(12, "if(e.succeeded()) {");
						tl(13, "index", classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", f -> {");
						tl(14, "if(f.succeeded()) {");
						tl(15, "response200", classApiMethod, classSimpleName, "(", StringUtils.uncapitalize(classSimpleName), ", g -> {");
						tl(16, "if(g.succeeded()) {");
						tl(17, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
						tl(17, "sqlConnection.commit(h -> {");
						tl(18, "if(a.succeeded()) {");
						tl(19, "sqlConnection.close(i -> {");
						tl(20, "if(a.succeeded()) {");
						tl(21, "eventHandler.handle(Future.succeededFuture(g.result()));");
						tl(20, "} else {");
						tl(21, "error", classSimpleName, "(siteRequest, eventHandler, i);");
						tl(20, "}");
						tl(19, "});");
						tl(18, "} else {");
						tl(19, "error", classSimpleName, "(siteRequest, eventHandler, h);");
						tl(18, "}");
						tl(17, "});");
						tl(16, "} else {");
						tl(17, "error", classSimpleName, "(siteRequest, eventHandler, g);");
						tl(16, "}");
						tl(15, "});");
						tl(14, "} else {");
						tl(15, "error", classSimpleName, "(siteRequest, eventHandler, f);");
						tl(14, "}");
						tl(13, "});");
						tl(12, "} else {");
						tl(13, "error", classSimpleName, "(siteRequest, eventHandler, e);");
						tl(12, "}");
						tl(11, "});");
						tl(10, "} else {");
						tl(11, "error", classSimpleName, "(siteRequest, eventHandler, d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "error", classSimpleName, "(siteRequest, eventHandler, c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "error", classSimpleName, "(siteRequest, eventHandler, b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "error", classSimpleName, "(siteRequest, eventHandler, a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "error", classSimpleName, "(null, eventHandler, Future.failedFuture(e));");
						tl(2, "}");
					}
					else if(classApiMethod.contains("DELETE")) {
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = generate", classPartsSiteRequest.simpleName(languageName), "For", classSimpleName, "(siteContext, operationRequest);");
						tl(3, "sql", classSimpleName, "(siteRequest, a -> {");
						tl(4, "if(a.succeeded()) {");
						tl(5, "search", classSimpleName, "(siteRequest, false, true, ", "null", ", b -> {");
						tl(6, "if(b.succeeded()) {");
						tl(7, "SearchList<", classSimpleName, "> list", classSimpleName, " = b.result();");
						tl(7, "delete", classApiMethod, classSimpleName, "(siteRequest, c -> {");
						tl(8, "if(c.succeeded()) {");
						tl(9, "response200", classApiMethod, classSimpleName, "(siteRequest, d -> {");
						tl(10, "if(d.succeeded()) {");
						tl(11, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
						tl(11, "if(sqlConnection == null) {");
						tl(12, "eventHandler.handle(Future.succeededFuture(d.result()));");
						tl(11, "} else {");
						tl(12, "sqlConnection.commit(e -> {");
						tl(13, "if(e.succeeded()) {");
						tl(14, "sqlConnection.close(f -> {");
						tl(15, "if(f.succeeded()) {");
						tl(16, "eventHandler.handle(Future.succeededFuture(d.result()));");
						tl(15, "} else {");
						tl(16, "error", classSimpleName, "(siteRequest, eventHandler, f);");
						tl(15, "}");
						tl(14, "});");
						tl(13, "} else {");
						tl(14, "error", classSimpleName, "(siteRequest, eventHandler, e);");
						tl(13, "}");
						tl(12, "});");
						tl(11, "}");
						tl(10, "} else {");
						tl(11, "error", classSimpleName, "(siteRequest, eventHandler, d);");
						tl(10, "}");
						tl(9, "});");
						tl(8, "} else {");
						tl(9, "error", classSimpleName, "(siteRequest, eventHandler, c);");
						tl(8, "}");
						tl(7, "});");
						tl(6, "} else {");
						tl(7, "error", classSimpleName, "(siteRequest, eventHandler, b);");
						tl(6, "}");
						tl(5, "});");
						tl(4, "} else {");
						tl(5, "error", classSimpleName, "(siteRequest, eventHandler, a);");
						tl(4, "}");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "error", classSimpleName, "(null, eventHandler, Future.failedFuture(e));");
						tl(2, "}");
					}
					tl(1, "}");
	
					if(classApiMethod.contains("Search")) {
	//					l();
	//					tl(1, "public Future<OperationResponse> searchList", classSimpleName, "(SearchList<", classSimpleName, "> list", classSimpleName, ") {");
	//					tl(2, "List<Future> futures = new ArrayList<>();");
	//					tl(2, "list", classSimpleName, ".getList().forEach(o -> {");
	//					tl(3, "futures.add(");
	//					tl(4, "sqlPATCH", classSimpleName, "(o).compose(");
	//					tl(5, "b -> index", classSimpleName, "(o)");
	//					tl(4, ")");
	//					tl(3, ");");
	//					tl(2, "});");
	//					tl(2, "Future<OperationResponse> future = CompositeFuture.all(futures).compose( a -> ");
	//					tl(3, "response200Recherche", classSimpleName, "(list", classSimpleName, ")");
	//					tl(2, ");");
	//					tl(2, "return future;");
	//					tl(1, "}");
					}
					if(classApiMethod.contains("POST")) {
						l();
						tl(1, "public void create", classApiMethod, classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " siteRequest, Handler<AsyncResult<", classSimpleName, ">> eventHandler) {");
						tl(2, "try {");
						tl(3, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
						tl(3, "String userId = siteRequest.getUserId();");
						l();
						tl(3, "sqlConnection.queryWithParams(");
						tl(5, "", classPartsSiteContext.simpleName(languageName), ".SQL_create");
						tl(5, ", new JsonArray(Arrays.asList(", classSimpleName, ".class.getCanonicalName(), userId))");
						tl(5, ", createAsync");
						tl(3, "-> {");
						tl(4, "JsonArray createRow = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);");
						tl(4, "Long ", classVarPrimaryKey, " = createRow.getLong(0);");
						tl(4, classSimpleName, " o = new ", classSimpleName, "();");
						tl(4, "o.set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, ");");
						tl(4, "o.initDeep", classSimpleName, "(siteRequest);");
						tl(4, "eventHandler.handle(Future.succeededFuture(o));");
						tl(3, "});");
						tl(2, "} catch(Exception e) {");
						tl(3, "eventHandler.handle(Future.failedFuture(e));");
						tl(2, "}");
						tl(1, "}");
					}
					if(classApiMethod.contains("PATCH")) {
						l();
						tl(1, "public void list", classApiMethod, classSimpleName, "(SearchList<", classSimpleName, "> list", classSimpleName, ", Handler<AsyncResult<OperationResponse>> eventHandler) {");
						tl(2, "List<Future> futures = new ArrayList<>();");
						tl(2, "list", classSimpleName, ".getList().forEach(o -> {");
						tl(3, "futures.add(");
						tl(4, "sql", classApiMethod, classSimpleName, "(o).compose(");
						tl(5, "a -> define", classApiMethod, classSimpleName, "(a).compose(");
						tl(6, "b -> index", classApiMethod, classSimpleName, "(b)");
						tl(5, ")");
						tl(4, ")");
						tl(3, ");");
						tl(2, "});");
						tl(2, "CompositeFuture.all(futures).setHandler( a -> {");
						tl(3, "if(a.succeeded()) {");
						tl(4, "response200", classApiMethod, classSimpleName, "(list", classSimpleName, ", eventHandler);");
						tl(3, "} else {");
						tl(4, "error", classSimpleName, "(list", classSimpleName, ".getSiteRequest_(), eventHandler, a);");
						tl(3, "}");
						tl(2, "});");
						tl(1, "}");
						l();
						tl(1, "public Future<", classSimpleName, "> sql", classApiMethod, classSimpleName, "(", classSimpleName, " o) {");
						tl(2, "Future<", classSimpleName, "> future = Future.future();");
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = o.getSiteRequest_();");
						tl(3, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
						tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
						tl(3, "JsonObject requestJson = siteRequest.getJsonObject();");
						tl(3, "StringBuilder patchSql = new StringBuilder();");
						tl(3, "List<Object> patchSqlParams = new ArrayList<Object>();");
						tl(3, "Set<String> methodNames = requestJson.fieldNames();");
						tl(3, classSimpleName, " o2 = new ", classSimpleName, "();");
						l();
						tl(3, "patchSql.append(", classPartsSiteContext.simpleName(languageName), ".SQL_modifier);");
						tl(3, "patchSqlParams.addAll(Arrays.asList(pk, ", q(classCanonicalName), "));");
						tl(3, "for(String methodName : methodNames) {");
						tl(4, "switch(methodName) {");
						s(wApiGeneratePatch.toString());
						tl(4, "}");
						tl(3, "}");
						tl(3, "sqlConnection.queryWithParams(");
						tl(5, "patchSql.toString()");
						tl(5, ", new JsonArray(patchSqlParams)");
						tl(5, ", patchAsync");
						tl(3, "-> {");
						tl(4, "o2.setSiteRequest_(o.getSiteRequest_());");
						tl(4, "o2.set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, ");");
						tl(4, "future.complete(o2);");
						tl(3, "});");
						tl(3, "return future;");
						tl(2, "} catch(Exception e) {");
						tl(3, "return Future.failedFuture(e);");
						tl(2, "}");
						tl(1, "}");
						l();
						tl(1, "public Future<", classSimpleName, "> define", classApiMethod, classSimpleName, "(", classSimpleName, " o) {");
						tl(2, "Future<", classSimpleName, "> future = Future.future();");
						tl(2, "try {");
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = o.getSiteRequest_();");
						tl(3, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
						tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
						tl(3, "sqlConnection.queryWithParams(");
						tl(5, "", classPartsSiteContext.simpleName(languageName), ".SQL_define");
						tl(5, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, ", ", classVarPrimaryKey, ", ", classVarPrimaryKey, "))");
						tl(5, ", defineAsync");
						tl(3, "-> {");
						tl(4, "if(defineAsync.succeeded()) {");
						tl(5, "for(JsonArray definition : defineAsync.result().getResults()) {");
						tl(6, "o.defineForClass(definition.getString(0), definition.getString(1));");
						tl(5, "}");
						tl(5, "future.complete(o);");
						tl(4, "} else {");
						tl(3, "future.fail(defineAsync.cause());");
						tl(4, "}");
						tl(3, "});");
						tl(3, "return future;");
						tl(2, "} catch(Exception e) {");
						tl(3, "return Future.failedFuture(e);");
						tl(2, "}");
						tl(1, "}");
						l();
						tl(1, "public Future<Void> index", classApiMethod, classSimpleName, "(", classSimpleName, " o) {");
						tl(2, "Future<Void> future = Future.future();");
						tl(2, "try {");
						tl(3, "o.initDeepForClass(o.getSiteRequest_());");
						tl(3, "o.indexForClass();");
						tl(4, "future.complete();");
						tl(3, "return future;");
						tl(2, "} catch(Exception e) {");
						tl(3, "return Future.failedFuture(e);");
						tl(2, "}");
						tl(1, "}");
					}
					if(classApiMethod.contains("PUT")) {
						l();
						tl(1, "public void replace", classApiMethod, classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " siteRequest, Handler<AsyncResult<", classSimpleName, ">> eventHandler) {");
						tl(2, "try {");
						tl(3, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
						tl(3, "String userId = siteRequest.getUserId();");
						tl(3, "Long pk = siteRequest.getRequestPk();");
						l();
						tl(3, "sqlConnection.queryWithParams(");
						tl(5, "", classPartsSiteContext.simpleName(languageName), ".SQL_clear");
						tl(5, ", new JsonArray(Arrays.asList(pk, ", classSimpleName, ".class.getCanonicalName(), pk, pk, pk))");
						tl(5, ", replaceAsync");
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
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = o.getSiteRequest_();");
						tl(3, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
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
						tl(1, "public void delete", classApiMethod, classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {");
						tl(2, "try {");
						tl(3, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
						tl(3, "String userId = siteRequest.getUserId();");
						tl(3, "Long pk = siteRequest.getRequestPk();");
						l();
						tl(3, "sqlConnection.queryWithParams(");
						tl(5, "", classPartsSiteContext.simpleName(languageName), ".SQL_delete");
						tl(5, ", new JsonArray(Arrays.asList(pk, ", classSimpleName, ".class.getCanonicalName(), pk, pk, pk, pk))");
						tl(5, ", deleteAsync");
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
						s("", classPartsSiteRequest.simpleName(languageName), " siteRequest");
					else
						s("SearchList<", classSimpleName, "> list", classSimpleName);
	
					l(", Handler<AsyncResult<OperationResponse>> eventHandler) {");
	
					tl(2, "try {");
					tl(3, "Buffer buffer = Buffer.buffer();");
	
					if(classApiMethod.contains("POST") || classApiMethod.contains("PUT")) {
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = o.getSiteRequest_();");
					}
					else if(classApiMethod.contains("Search") || classApiMethod.contains("PATCH") || classApiMethod.contains("GET")) {
						tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = list", classSimpleName, ".getSiteRequest_();");
					}
					else {
					}
	
					t(3, "AllWriter w = AllWriter.create(");
					if(classApiMethod.contains("POST") || classApiMethod.contains("PUT"))
						s("o.getSiteRequest_()");
					else if(classApiMethod.contains("DELETE"))
						s("siteRequest");
					else
						s("list", classSimpleName, ".getSiteRequest_()");
					l(", buffer);");
					tl(3, "siteRequest.setW(w);");

					if(classApiMethod.contains("PATCH"))
						tl(3, "buffer.appendString(\"{}\");");
	
	
					if(classApiMethod.contains("GET")) {
						tl(3, "SolrDocumentList solrDocuments = list", classSimpleName, ".getSolrDocumentList();");
						l();
					}
					if(classApiMethod.contains("Search")) {
					}
	
					if(classApiMethod.contains("Search") || classApiMethod.contains("GET")) {
					}
					else if(classApiMethod.contains("DELETE")) {
					}
	
					if(classApiMethod.contains("Search")) {
						if(classPageCanonicalNameMethod != null) {
							tl(3, classPageSimpleNameMethod, " page = new ", classPageSimpleNameMethod, "();");
//							tl(3, "page.setPageUrl(\"", siteBaseUrl, classApiUri, "\");");
							tl(3, "SolrDocument pageSolrDocument = new SolrDocument();");
							l();
							tl(3, "pageSolrDocument.setField(", q("pageUri_frFR_stored_string"), ", ", q(classApiUriMethod), ");");
							tl(3, "page.setPageSolrDocument(pageSolrDocument);");
							tl(3, "page.setW(w);");
							if(!classPageSimple)
								tl(3, "page.setList", classSimpleName, "(list", classSimpleName, ");");
							tl(3, "page.initDeep", classPageSimpleNameMethod, "(siteRequest);");
							tl(3, "page.html();");
						}
						else {
							tl(3, "QueryResponse searchResponse = list", classSimpleName, ".getQueryResponse();");
							tl(3, "SolrDocumentList solrDocuments = list", classSimpleName, ".getSolrDocumentList();");
							tl(3, "Long millisSearch = Long.valueOf(searchResponse.getQTime());");
							tl(3, "Long millisTransmission = searchResponse.getElapsedTime();");
							tl(3, "Long numStart = searchResponse.getResults().getStart();");
							tl(3, "Long numFound = searchResponse.getResults().getNumFound();");
							tl(3, "Integer numReturned = searchResponse.getResults().size();");
							tl(3, "String timeSearch = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisSearch), TimeUnit.MILLISECONDS.toMillis(millisSearch) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisSearch)));");
							tl(3, "String timeTransmission = String.format(\"%d.%03d sec\", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));");
							tl(3, "Exception exceptionSearch = searchResponse.getException();");
							l();
							tl(3, "w.l(\"{\");");
							tl(3, "w.tl(1, ", q(q("numStart"), ": "), ", numStart);");
							tl(3, "w.tl(1, ", q(", ", q("numFound"), ": "), ", numFound);");
							tl(3, "w.tl(1, ", q(", ", q("numReturned"), ": "), ", numReturned);");
							tl(3, "w.tl(1, ", q(", ", q("timeSearch"), ": "), ", w.q(timeSearch));");
							tl(3, "w.tl(1, ", q(", ", q("timeTransmission"), ": "), ", w.q(timeTransmission));");
							tl(3, "w.tl(1, ", q(", ", q("list"), ": ["), ");");
							tl(3, "for(int i = 0; i < list", classSimpleName, ".size(); i++) {");
							tl(4, classSimpleName, " o = list", classSimpleName, ".getList().get(i);");
							tl(4, "Object entityValue;");
							tl(4, "Integer entityNumber = 0;");
		//					tl(4, "List<String> fieldNames = new ArrayList<>(solrDocument.getFieldNames());");
							l();
							tl(4, "w.t(2);");
							tl(4, "if(i > 0)");
							tl(5, "w.s(", q(", "), ");");
							tl(4, "w.l(", q("{"), ");");
		//					tl(4, "for(int j = 0; j < fieldNames.size(); j++) {");
		//					tl(5, "String entityVarStored = fieldNames.get(j);");
		//					tl(5, "List<Object> entityValues = new ArrayList<>(solrDocument.getFieldValues(entityVarStored));");
							s(wApiGenerateGet.toString());
		//					tl(4, "}");
							l();
							tl(4, "w.tl(2, ", q("}"), ");");
							tl(3, "}");
							tl(3, "w.tl(1, ", q("]"), ");");
							tl(3, "if(exceptionSearch != null) {");
							tl(4, "w.tl(1, ", q(", ", q("exceptionSearch"), ": "), ", w.q(exceptionSearch.getMessage()));");
							tl(3, "}");
							tl(3, "w.l(\"}\");");
						}
					}
					if(classApiMethod.contains("GET")) {
						if(classPageCanonicalNameMethod != null) {
							tl(3, classPageSimpleNameMethod, " page = new ", classPageSimpleNameMethod, "();");
//							tl(3, "page.setPageUrl(\"", siteBaseUrl, classApiUri, "\");");
							tl(3, "SolrDocument pageSolrDocument = new SolrDocument();");
							tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = list", classSimpleName, ".getSiteRequest_();");
							l();
							tl(3, "pageSolrDocument.setField(", q("pageUri_frFR_stored_string"), ", ", q(classApiUriMethod), ");");
							tl(3, "page.setPageSolrDocument(pageSolrDocument);");
							tl(3, "page.setW(w);");
							tl(3, "page.initDeep", classPageSimpleNameMethod, "(siteRequest);");
							tl(3, "page.html();");
						}
						else {
							tl(3, "if(list", classSimpleName, ".size() > 0) {");
							tl(4, "SolrDocument solrDocument = solrDocuments.get(0);");
							tl(4, classSimpleName, " o = list", classSimpleName, ".get(0);");
							tl(4, "Object entityValue;");
							tl(4, "Integer entityNumber = 0;");
							l();
							tl(4, "w.l(", q("{"), ");");
		//					tl(4, "for(int j = 0; j < fieldNames.size(); j++) {");
		//					tl(5, "String entityVarStored = fieldNames.get(j);");
		//					tl(5, "List<Object> entityValues = new ArrayList<>(solrDocument.getFieldValues(entityVarStored));");
							s(wApiGenerateGet.toString());
		//					tl(4, "}");
							l();
							tl(4, "w.l(", q("}"), ");");
							tl(3, "}");
						}
					}
	
					if((classApiMethod.contains("GET") || classApiMethod.contains("Search")) && classPageCanonicalNameMethod != null) {
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
							entityVar = (String)entiteDocumentSolr.get("entityVar_" + languageActualName + "_stored_string");
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
			tl(1, "public String varIndexed", classSimpleName, "(String entityVar) {");
			tl(2, "switch(entityVar) {");
			s(wVarIndexed);
			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" is not an indexed entity. \", entityVar));");
			tl(2, "}");
			tl(1, "}");

			l();
			tl(1, "public String varSearched", classSimpleName, "(String entityVar) {");
			tl(2, "switch(entityVar) {");
			s(wVarSearched);
			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" is not an indexed entity. \", entityVar));");
			tl(2, "}");
			tl(1, "}");

			l();
			tl(1, "public String varSuggested", classSimpleName, "(String entityVar) {");
			tl(2, "switch(entityVar) {");
			s(wVarSuggested);
			tl(3, "default:");
			tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" is not an indexed entity. \", entityVar));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "// Shared //");
			l();
			tl(1, "public void error", classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> asyncResult) {");
			tl(2, "Throwable e = asyncResult.cause();");
			tl(2, "ExceptionUtils.printRootCauseStackTrace(e);");
			tl(2, "OperationResponse operationResponse = new OperationResponse(400, \"BAD REQUEST\", ");
			tl(3, "Buffer.buffer().appendString(");
			tl(4, "new JsonObject() {{");
			tl(5, "put(\"error\", new JsonObject() {{");
			tl(5, "put(\"message\", e.getMessage());");
			tl(5, "}});");
			tl(4, "}}.encodePrettily()");
			tl(3, ")");
			tl(3, ", new CaseInsensitiveHeaders()");
			tl(2, ");");
			tl(2, "if(siteRequest != null) {");
			tl(3, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
			tl(3, "if(sqlConnection != null) {");
			tl(4, "sqlConnection.rollback(a -> {");
			tl(5, "if(a.succeeded()) {");
			tl(6, "sqlConnection.close(b -> {");
			tl(7, "if(a.succeeded()) {");
			tl(8, "eventHandler.handle(Future.succeededFuture(operationResponse));");
			tl(7, "} else {");
			tl(8, "eventHandler.handle(Future.succeededFuture(operationResponse));");
			tl(7, "}");
			tl(6, "});");
			tl(5, "} else {");
			tl(6, "eventHandler.handle(Future.succeededFuture(operationResponse));");
			tl(5, "}");
			tl(4, "});");
			tl(3, "} else {");
			tl(4, "eventHandler.handle(Future.succeededFuture(operationResponse));");
			tl(3, "}");
			tl(2, "} else {");
			tl(3, "eventHandler.handle(Future.succeededFuture(operationResponse));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void sql", classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {");
			tl(2, "try {");
			tl(3, "SQLClient sqlClient = siteRequest.getSiteContext_().getSqlClient();");
			l();
			tl(3, "if(sqlClient == null) {");
			tl(4, "eventHandler.handle(Future.succeededFuture());");
			tl(3, "} else {");
			tl(4, "sqlClient.getConnection(sqlAsync -> {");
			tl(5, "if(sqlAsync.succeeded()) {");
			tl(6, "SQLConnection sqlConnection = sqlAsync.result();");
			tl(6, "sqlConnection.setAutoCommit(false, a -> {");
			tl(7, "if(a.succeeded()) {");
			tl(8, "siteRequest.setSqlConnection(sqlConnection);");
			tl(8, "eventHandler.handle(Future.succeededFuture());");
			tl(7, "} else {");
			tl(8, "eventHandler.handle(Future.failedFuture(a.cause()));");
			tl(7, "}");
			tl(6, "});");
			tl(5, "} else {");
			tl(6, "eventHandler.handle(Future.failedFuture(sqlAsync.cause()));");
			tl(5, "}");
			tl(4, "});");
			tl(3, "}");
			tl(2, "} catch(Exception e) {");
			tl(3, "eventHandler.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
	//		tl(1, "public ", classPartsSiteRequest.simpleName(languageName), " generate", classPartsSiteRequest.simpleName(languageName), "For", classSimpleName, "(", classPartsSiteContext.simpleName(languageName), " siteContext, RoutingContext contexteItineraire) {");
			tl(1, "public ", classPartsSiteRequest.simpleName(languageName), " generate", classPartsSiteRequest.simpleName(languageName), "For", classSimpleName, "(", classPartsSiteContext.simpleName(languageName), " siteContext, OperationRequest operationRequest) {");
			tl(2, "return generate", classPartsSiteRequest.simpleName(languageName), "For", classSimpleName, "(siteContext, operationRequest, null);");
			tl(1, "}");
			l();
			tl(1, "public ", classPartsSiteRequest.simpleName(languageName), " generate", classPartsSiteRequest.simpleName(languageName), "For", classSimpleName, "(", classPartsSiteContext.simpleName(languageName), " siteContext, OperationRequest operationRequest, JsonObject body) {");
			tl(2, "Vertx vertx = siteContext.getVertx();");
			tl(2, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = new ", classPartsSiteRequest.simpleName(languageName), "();");
			tl(2, "siteRequest.setJsonObject(body);");
			tl(2, "siteRequest.setVertx(vertx);");
	//		tl(2, "siteRequest.setContexteItineraire(contexteItineraire);");
			tl(2, "siteRequest.setSiteContext_(siteContext);");
			tl(2, "siteRequest.setSiteConfig_(siteContext.getSiteConfig());");
			tl(2, "siteRequest.setOperationRequest(operationRequest);");
			tl(2, "siteRequest.initDeep", classPartsSiteRequest.simpleName(languageName), "(siteRequest);");
			l();
			tl(2, "return siteRequest;");
			tl(1, "}");
			l();
			tl(1, "public void user", classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {");
			tl(2, "try {");
			tl(3, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
			tl(3, "String userId = siteRequest.getUserId();");
			tl(3, "if(userId == null) {");
			tl(4, "eventHandler.handle(Future.succeededFuture());");
			tl(3, "} else {");
			tl(4, "sqlConnection.queryWithParams(");
			tl(6, "", classPartsSiteContext.simpleName(languageName), ".SQL_selectC");
			tl(6, ", new JsonArray(Arrays.asList(", q(classPartsSiteUser.canonicalName), ", userId))");
			tl(6, ", selectCAsync");
			tl(4, "-> {");
			tl(5, "if(selectCAsync.succeeded()) {");
//					tl(4, "entityValue = Optional.ofNullable(solrDocument.getFieldValues(", q(entityVar, "_stored", entityTypeSuffix), ")).map(Collection<Object>::stream).orElseGet(Stream::empty).findFirst().orElse(null);");
			tl(6, "JsonArray userValues = selectCAsync.result().getResults().stream().findFirst().orElse(null);");
			tl(6, "if(userValues == null) {");
			tl(7, "sqlConnection.queryWithParams(");
			tl(9, "", classPartsSiteContext.simpleName(languageName), ".SQL_create");
			tl(9, ", new JsonArray(Arrays.asList(", q(classPartsSiteUser.canonicalName), ", userId))");
			tl(9, ", createAsync");
			tl(7, "-> {");
			tl(8, "JsonArray createRow = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);");
			tl(8, "Long ", classVarPrimaryKey, "User = createRow.getLong(0);");
			tl(8, "SiteUser userSite = new SiteUser();");
			tl(8, "userSite.set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, "User);");
			l();
			tl(8, "sqlConnection.queryWithParams(");
			tl(10, "", classPartsSiteContext.simpleName(languageName), ".SQL_define");
			tl(10, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, "User, ", classVarPrimaryKey, "User, ", classVarPrimaryKey, "User))");
			tl(10, ", defineAsync");
			tl(8, "-> {");
			tl(9, "if(defineAsync.succeeded()) {");
			tl(10, "try {");
			tl(11, "for(JsonArray definition : defineAsync.result().getResults()) {");
			tl(12, "userSite.defineForClass(definition.getString(0), definition.getString(1));");
			tl(11, "}");
			tl(11, "JsonObject userVertx = siteRequest.getOperationRequest().getUser();");
			tl(11, "JsonObject principalJson = KeycloakHelper.parseToken(userVertx.getString(\"access_token\"));");
			tl(11, "userSite.setUserName(principalJson.getString(\"preferred_username\"));");
			tl(11, "userSite.setUserFirstName(principalJson.getString(\"given_name\"));");
			tl(11, "userSite.setUserLastName(principalJson.getString(\"family_name\"));");
			tl(11, "userSite.setUserId(principalJson.getString(\"sub\"));");
			tl(11, "userSite.initDeepForClass(siteRequest);");
			tl(11, "userSite.indexForClass();");
			tl(11, "siteRequest.setSiteUser(userSite);");
			tl(11, "siteRequest.setUserName(principalJson.getString(\"preferred_username\"));");
			tl(11, "siteRequest.setUserFirstName(principalJson.getString(\"given_name\"));");
			tl(11, "siteRequest.setUserLastName(principalJson.getString(\"family_name\"));");
			tl(11, "siteRequest.setUserId(principalJson.getString(\"sub\"));");
			tl(11, "eventHandler.handle(Future.succeededFuture());");
			tl(10, "} catch(Exception e) {");
			tl(11, "eventHandler.handle(Future.failedFuture(e));");
			tl(10, "}");
			tl(9, "} else {");
			tl(10, "eventHandler.handle(Future.failedFuture(defineAsync.cause()));");
			tl(9, "}");
			tl(8, "});");

			tl(7, "});");
			tl(6, "} else {");
			tl(7, "Long ", classVarPrimaryKey, "User = userValues.getLong(0);");
			tl(7, "SiteUser userSite = new SiteUser();");
			tl(7, "userSite.set", StringUtils.capitalize(classVarPrimaryKey), "(", classVarPrimaryKey, "User);");
			l();
			tl(7, "sqlConnection.queryWithParams(");
			tl(9, "", classPartsSiteContext.simpleName(languageName), ".SQL_define");
			tl(9, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, "User, ", classVarPrimaryKey, "User, ", classVarPrimaryKey, "User))");
			tl(9, ", defineAsync");
			tl(7, "-> {");
			tl(8, "if(defineAsync.succeeded()) {");
			tl(9, "for(JsonArray definition : defineAsync.result().getResults()) {");
			tl(10, "userSite.defineForClass(definition.getString(0), definition.getString(1));");
			tl(9, "}");
			tl(9, "JsonObject userVertx = siteRequest.getOperationRequest().getUser();");
			tl(9, "JsonObject principalJson = KeycloakHelper.parseToken(userVertx.getString(\"access_token\"));");
			tl(9, "userSite.setUserName(principalJson.getString(\"preferred_username\"));");
			tl(9, "userSite.setUserFirstName(principalJson.getString(\"given_name\"));");
			tl(9, "userSite.setUserLastName(principalJson.getString(\"family_name\"));");
			tl(9, "userSite.setUserId(principalJson.getString(\"sub\"));");
			tl(9, "userSite.initDeepForClass(siteRequest);");
			tl(9, "userSite.indexForClass();");
			tl(9, "siteRequest.setSiteUser(userSite);");
			tl(9, "siteRequest.setUserName(principalJson.getString(\"preferred_username\"));");
			tl(9, "siteRequest.setUserFirstName(principalJson.getString(\"given_name\"));");
			tl(9, "siteRequest.setUserLastName(principalJson.getString(\"family_name\"));");
			tl(9, "siteRequest.setUserId(principalJson.getString(\"sub\"));");
			tl(9, "eventHandler.handle(Future.succeededFuture());");
			tl(8, "} else {");
			tl(9, "eventHandler.handle(Future.failedFuture(defineAsync.cause()));");
			tl(8, "}");
			tl(7, "});");

			tl(6, "}");
			tl(5, "} else {");
			tl(6, "eventHandler.handle(Future.failedFuture(selectCAsync.cause()));");
			tl(5, "}");
			tl(4, "});");
			tl(3, "}");
			tl(2, "} catch(Exception e) {");
			tl(3, "eventHandler.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void search", classSimpleName, "(", classPartsSiteRequest.simpleName(languageName), " siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<", classSimpleName, ">>> eventHandler) {");
			tl(2, "try {");
			tl(3, "OperationRequest operationRequest = siteRequest.getOperationRequest();");
			tl(3, "String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject(", q("query"), ").getString(", q("fl"), ");");
			tl(3, "String[] entityList = entityListStr == null ? null : entityListStr.split(", q(",\\s*"), ");");
			tl(3, "SearchList<", classSimpleName, "> searchList = new SearchList<", classSimpleName, ">();");
			tl(3, "searchList.setPopulate(populate);");
			tl(3, "searchList.setStore(store);");
			tl(3, "searchList.setQuery(\"*:*\");");
			tl(3, "searchList.setC(", classSimpleName, ".class);");
			tl(3, "if(entityList != null)");
			tl(3, "searchList.setFields(entityList);");
			tl(3, "searchList.addSort(\"archived_indexed_boolean\", ORDER.asc);");
			tl(3, "searchList.addSort(\"deleted_indexed_boolean\", ORDER.asc);");
			tl(3, "searchList.addFilterQuery(\"classCanonicalNames_indexed_strings:\" + ClientUtils.escapeQueryChars(", q(classCanonicalName), "));");
			if(classFiltersFound && classFilters.contains("userId"))
				tl(3, "searchList.addFilterQuery(\"userId_indexed_string:\" + ClientUtils.escapeQueryChars(siteRequest.getUserId()));");
			tl(3, "SiteUser userSite = siteRequest.getSiteUser();");
			tl(3, "if(userSite != null && !userSite.getVoirDeleted())");
			tl(4, "searchList.addFilterQuery(\"deleted_indexed_boolean:false\");");
			tl(3, "if(userSite != null && !userSite.getVoirArchived())");
			tl(4, "searchList.addFilterQuery(\"archived_indexed_boolean:false\");");
			l();
			tl(3, "String pageUri = null;");
			tl(3, "String id = operationRequest.getParams().getJsonObject(\"path\").getString(\"id\");");
			tl(3, "if(", classVarUniqueKey, " != null) {");
			tl(4, "pageUri = classApiUriMethod + ", q("/"), " + id;");
			tl(4, "searchList.addFilterQuery(\"pageUri_indexed_string:\" + ClientUtils.escapeQueryChars(pageUri));");
			tl(3, "}");
			l();
			tl(3, "operationRequest.getParams().getJsonObject(\"query\").forEach(queryParam -> {");
			tl(4, "String entityVar = null;");
			tl(4, "String valueIndexed = null;");
			tl(4, "String varIndexed = null;");
			tl(4, "String valueSort = null;");
			tl(4, "Integer searchStart = null;");
			tl(4, "Integer searchNum = null;");
			tl(4, "String paramNom = queryParam.getKey();");
			tl(4, "Object paramValuesObject = queryParam.getValue();");
			tl(4, "JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);");
			l();
			tl(4, "try {");
			tl(5, "for(Object paramObject : paramObjects) {");
			tl(6, "switch(paramNom) {");
	
			tl(7, "case \"q\":");
			tl(8, "entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, \":\"));");
			tl(8, "varIndexed = \"*\".equals(entityVar) ? entityVar : varSearched", classSimpleName, "(entityVar);");
			tl(8, "valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, \":\")), \"UTF-8\");");
			tl(8, "valueIndexed = StringUtils.isEmpty(valueIndexed) ? \"*\" : valueIndexed;");
//			tl(8, "if(StringUtils.isEmpty(valueIndexed)) {");
//			tl(9, "valueIndexed = entityVar;");
//			tl(9, "entityVar = \"*\";");
//			tl(8, "}");
			tl(8, "searchList.setQuery(varIndexed + \":\" + (\"*\".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));");
			tl(8, "if(!\"*\".equals(entityVar)) {");
			tl(9, "searchList.setHighlight(true);");
			tl(9, "searchList.setHighlightSnippets(3);");
			tl(9, "searchList.addHighlightField(varIndexed);");
			tl(9, "searchList.setParam(\"hl.encoder\", \"html\");");
			tl(8, "}");
			tl(8, "break;");
	
			tl(7, "case \"fq\":");
			tl(8, "entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, \":\"));");
			tl(8, "valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, \":\")), \"UTF-8\");");
			tl(8, "varIndexed = varIndexed", classSimpleName, "(entityVar);");
			tl(8, "searchList.addFilterQuery(varIndexed + \":\" + ClientUtils.escapeQueryChars(valueIndexed));");
			tl(8, "break;");
	
			tl(7, "case \"sort\":");
			tl(8, "entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, \" \"));");
			tl(8, "valueSort = StringUtils.trim(StringUtils.substringAfter((String)paramObject, \" \"));");
			tl(8, "varIndexed = varIndexed", classSimpleName, "(entityVar);");
			tl(8, "searchList.addSort(varIndexed, ORDER.valueOf(valueSort));");
			tl(8, "break;");
	
			tl(7, "case \"fl\":");
			tl(8, "entityVar = StringUtils.trim((String)paramObject);");
			tl(8, "varIndexed = varIndexed", classSimpleName, "(entityVar);");
			tl(8, "searchList.addField(varIndexed);");
			tl(8, "break;");
	
			tl(7, "case \"start\":");
			tl(8, "searchStart = (Integer)paramObject;");
			tl(8, "searchList.setStart(searchStart);");
			tl(8, "break;");
	
			tl(7, "case \"rows\":");
			tl(8, "searchNum = (Integer)paramObject;");
			tl(8, "searchList.setRows(searchNum);");
			tl(8, "break;");
	
			tl(6, "}");
			tl(5, "}");
			tl(4, "} catch(Exception e) {");
			tl(5, "eventHandler.handle(Future.failedFuture(e));");
			tl(4, "}");

			tl(3, "});");
			tl(3, "searchList.initDeepForClass(siteRequest);");
			tl(3, "eventHandler.handle(Future.succeededFuture(searchList));");
			tl(2, "} catch(Exception e) {");
			tl(3, "eventHandler.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void define", classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> eventHandler) {");
			tl(2, "try {");
			tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = o.getSiteRequest_();");
			tl(3, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
			tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
			tl(3, "sqlConnection.queryWithParams(");
			tl(5, "", classPartsSiteContext.simpleName(languageName), ".SQL_define");
			tl(5, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, ", ", classVarPrimaryKey, ", ", classVarPrimaryKey, "))");
			tl(5, ", defineAsync");
			tl(3, "-> {");
			tl(4, "if(defineAsync.succeeded()) {");
			tl(5, "for(JsonArray definition : defineAsync.result().getResults()) {");
			tl(6, "o.defineForClass(definition.getString(0), definition.getString(1));");
			tl(5, "}");
			tl(5, "eventHandler.handle(Future.succeededFuture());");
			tl(4, "} else {");
			tl(5, "eventHandler.handle(Future.failedFuture(defineAsync.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "eventHandler.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void attribute", classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> eventHandler) {");
			tl(2, "try {");
			tl(3, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = o.getSiteRequest_();");
			tl(3, "SQLConnection sqlConnection = siteRequest.getSqlConnection();");
			tl(3, "Long ", classVarPrimaryKey, " = o.get", StringUtils.capitalize(classVarPrimaryKey), "();");
			tl(3, "sqlConnection.queryWithParams(");
			tl(5, "", classPartsSiteContext.simpleName(languageName), ".SQL_attribute");
			tl(5, ", new JsonArray(Arrays.asList(", classVarPrimaryKey, ", ", classVarPrimaryKey, "))");
			tl(5, ", attributeAsync");
			tl(3, "-> {");
			tl(4, "if(attributeAsync.succeeded()) {");
			tl(5, "if(attributeAsync.result() != null) {");
			tl(6, "for(JsonArray definition : attributeAsync.result().getResults()) {");
			tl(7, "o.attributePourClasse(definition.getString(0), definition.getString(1));");
			tl(6, "}");
			tl(5, "}");
			tl(5, "eventHandler.handle(Future.succeededFuture());");
			tl(4, "} else {");
			tl(5, "eventHandler.handle(Future.failedFuture(attributeAsync.cause()));");
			tl(4, "}");
			tl(3, "});");
			tl(2, "} catch(Exception e) {");
			tl(3, "eventHandler.handle(Future.failedFuture(e));");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public void index", classSimpleName, "(", classSimpleName, " o, Handler<AsyncResult<OperationResponse>> eventHandler) {");
			tl(2, "", classPartsSiteRequest.simpleName(languageName), " siteRequest = o.getSiteRequest_();");
			tl(2, "try {");
			tl(3, "o.initDeepForClass(siteRequest);");
			tl(3, "o.indexForClass();");
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
