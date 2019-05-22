package org.computate.enUS.java;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**	
 *	For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 **/
public class WritePageClass extends WriteApiClass {

	protected String classPageSimpleName;

	protected String classPageSuperSimpleName;

	protected String classGenPageSimpleName;

	public void  pageCodeClassStart(String languageName) throws Exception, Exception {
	}

	public void  pageCodeClassEnd(String languageName) throws Exception, Exception {
	}

	public Boolean writeFormEntity(AllWriter wForm, String classApiMethodMethod) {
		int tIndex = 0;
		Boolean result = false;

		if(entityHtml) {
			if("Search".equals(classApiMethodMethod)) {
				searchRowActualSearch = ObjectUtils.defaultIfNull((Integer)entitySolrDocument.get("entityHtmlRow_stored_int"), 0);
				if(searchRowSearch != searchRowActualSearch) {
					if(searchRowSearch != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					searchRowSearch = searchRowActualSearch;
					result = true;
				}
			}
			else if("POST".equals(classApiMethodMethod)) {
				searchRowActualPOST = ObjectUtils.defaultIfNull((Integer)entitySolrDocument.get("entityHtmlRow_stored_int"), 0);
				if(searchRowPOST != searchRowActualPOST) {
					if(searchRowPOST != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					searchRowPOST = searchRowActualPOST;
					result = true;
				}
			}
			else if("PATCH".equals(classApiMethodMethod)) {
				searchRowActualPATCH = ObjectUtils.defaultIfNull((Integer)entitySolrDocument.get("entityHtmlRow_stored_int"), 0);
				if(searchRowPATCH != searchRowActualPATCH) {
					if(searchRowPATCH != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					searchRowPATCH = searchRowActualPATCH;
					result = true;
				}
			}
			else if("Page".equals(classApiMethodMethod)) {
				searchRowActualPage = ObjectUtils.defaultIfNull((Integer)entitySolrDocument.get("entityHtmlRow_stored_int"), 0);
				if(searchRowPage != searchRowActualPage) {
					if(searchRowPage != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					searchRowPage = searchRowActualPage;
					result = true;
				}
			}

			wForm.t(3).be("div").da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
			if(entityDefine) {

				// entityDefine: true

				if("Page".equals(classApiMethodMethod)) {
					tIndex = 1;
					wForm.t(tIndex + 3).be("form").da("action", classeApiUri).da("id", entityVar, "Form").da("style", "display: inline-block; ").dfl();
				}
				if("LocalDate".equals(entitySimpleName)) {
					wForm.tl(tIndex + 4, entitySimpleNameComplete, " val = o.get", entityVarCapitalized, "();");
					l();
					if(entityDisplayName != null) {
						wForm.t(tIndex + 4).e("label").da("for", classApiMethodMethod, "_", entityVar).da("class", "").df().dsxq(entityDisplayName).dgl("label");
					}
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "text");
					wForm.t(tIndex + 5).dal("class", "w3-input w3-border datepicker ");
					wForm.t(tIndex + 5).dal("placeholder", "DD-MM-YYYY");
					wForm.t(tIndex + 5).dal("data-timeformat", "DD-MM-YYYY");
					wForm.t(tIndex + 5).dal("onclick", "removeGlow($(this)); ");
					if(entityDescription != null)
						wForm.t(tIndex + 5).dal("title", entityDescription + " (DD-MM-YYYY)");
					wForm.tl(tIndex + 5, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"dd/MM/yyyy\", Locale.forLanguageTag(\"fr-FR\")).format(val))");
					wForm.t(tIndex + 5).s(".a(\"onchange\", \"");
						wForm.s("var t = moment(this.value, 'DD-MM-YYYY'); ");
						wForm.s("if(t) { ");
							wForm.s("var s = t.format('YYYY-MM-DD'); ");
							wForm.s("$(this).next().val(s); ");
							wForm.s("$(this).next().trigger('change'); ");
						wForm.s("} ");
					wForm.l("\")");
					wForm.tl(tIndex + 5, ".fg();");
	
					wForm.t(tIndex + 4).e("input").l();
	
					if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 5).dal("class", "set", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("name", "set", entityVarCapitalized);
					}
					else {
						wForm.t(tIndex + 5).dal("class", "value", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("name", entityVar);
						wForm.t(tIndex + 5).dal("id", classApiMethodMethod, "_", entityVar);
					}
					if("Page".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 5).dal("onchange", "patch", classSimpleName, "($('#", classSimpleName, "Form'), $('#", entityVar, "Form')); ");
					}
	
					wForm.t(tIndex + 5).dal("type", "hidden");
					wForm.tl(tIndex + 5, ".a(\"value\", o.str", entityVarCapitalized, "())");
					wForm.t(tIndex + 4).dfgl();
				}
				else if("LocalDateTime".equals(entitySimpleName) || "ZonedDateTime".equals(entitySimpleName)) {
					wForm.tl(tIndex + 4, entitySimpleNameComplete, " val = o.get", entityVarCapitalized, "();");
					wForm.l();
					if(entityDisplayName != null) {
						wForm.t(tIndex + 4).e("label").da("for", classApiMethodMethod, "_", entityVar).da("class", "").df().dsxq(entityDisplayName).dgl("label");
					}
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "text");
					wForm.t(tIndex + 5).dal("class", "w3-input w3-border datepicker ");
					wForm.t(tIndex + 5).dal("placeholder", "DD-MM-YYYY");
					wForm.t(tIndex + 5).dal("data-timeformat", "DD-MM-YYYY");
					wForm.t(tIndex + 5).dal("onclick", "removeGlow($(this)); ");
					if(entityDescription != null)
						wForm.t(tIndex + 5).dal("title", entityDescription + " (DD-MM-YYYY)");
					wForm.tl(tIndex + 5, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"dd/MM/yyyy\", Locale.forLanguageTag(\"fr-FR\")).format(val))");
					wForm.t(tIndex + 5).s(".a(\"onchange\", \"");
						wForm.s("var t = moment(this.value, 'DD-MM-YYYY'); ");
						wForm.s("if(t) { ");
							wForm.s("var s = t.format('YYYY-MM-DD'); ");
							wForm.s("$(this).next().val(s); ");
							wForm.s("$(this).next().trigger('change'); ");
						wForm.s("} ");
					wForm.l("\")");
					wForm.tl(tIndex + 5, ".fg();");
	
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "hidden");
	
					if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 5).dal("class", "set", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("name", "set", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("id", classApiMethodMethod, "_", entityVar);
					}
					else {
						wForm.t(tIndex + 5).dal("class", "value", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("name", entityVar);
						wForm.t(tIndex + 5).dal("id", classApiMethodMethod, "_", entityVar);
					}
					if("Page".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 5).dal("onchange", "patch", classSimpleName, "($('#", classSimpleName, "Form'), $('#", entityVar, "Form')); ");
					}
	
					wForm.tl(tIndex + 5, ".a(\"value\", o.str", entityVarCapitalized, "())");
					wForm.t(tIndex + 4).dfgl();
				}
				else if("LocalTime".equals(entitySimpleName)) {
					wForm.tl(tIndex + 4, entitySimpleNameComplete, " val = o.get", entityVarCapitalized, "();");
					wForm.l();
					if(entityDisplayName != null) {
						wForm.t(tIndex + 4).e("label").da("for", classApiMethodMethod, "_", entityVar).da("class", "").df().dsxq(entityDisplayName).dgl("label");
					}
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "text");
					wForm.t(tIndex + 5).dal("class", "w3-input w3-border timepicker ");
					wForm.t(tIndex + 5).dal("placeholder", "HH:MM AM");
					wForm.t(tIndex + 5).dal("onclick", "removeGlow($(this)); ");
					if(entityDescription != null)
						wForm.t(tIndex + 5).da("title", entityDescription + " (h'h'mm)");
					wForm.tl(tIndex + 5, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"dd/MM/yyyy\", Locale.forLanguageTag(\"fr-FR\")).format(val))");
					wForm.t(tIndex + 5).s(".a(\"onchange\", \"");
						wForm.s("var t = parseTime(this.value); ");
						wForm.s("if(t) { ");
							wForm.s("var s = dateFormat(t, \"'h'MM\"); ");
							wForm.s("$(this).next().val(s); ");
							wForm.s("$(this).next().trigger('change'); ");
						wForm.s("} ");
					wForm.l("\")");
					wForm.tl(tIndex + 5, ".fg();");
	
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "hidden");
	
					if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 5).dal("class", "set", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("name", "set", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("id", classApiMethodMethod, "_", entityVar);
					}
					else {
						wForm.t(tIndex + 5).dal("class", "value", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("name", entityVar);
						wForm.t(tIndex + 5).dal("id", classApiMethodMethod, "_", entityVar);
					}
					if("Page".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 5).dal("onchange", "patch", classSimpleName, "($('#", classSimpleName, "Form'), $('#", entityVar, "Form')); ");
					}
	
					wForm.tl(tIndex + 5, ".a(\"value\", val == null ? \"\" : o.str", entityVarCapitalized, "())");
					wForm.t(tIndex + 4).dfgl();
				}
				else if("Boolean".equals(entitySimpleName)) {
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "hidden");
					wForm.t(tIndex + 5).dal("name", entityVar);
					wForm.t(tIndex + 5).dal("id", classApiMethodMethod, "_", entityVar);
					wForm.t(tIndex + 5).dal("value", "false");
					wForm.t(tIndex + 4).dfgl();
					wForm.l();
					wForm.t(tIndex + 4).e("input").l();
					wForm.t(tIndex + 5).dal("type", "checkbox");
					wForm.t(tIndex + 5).dal("value", "true");
	
					if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 5).dal("class", "set", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("name", "set", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("id", classApiMethodMethod, "_", entityVar);
					}
					else {
						wForm.t(tIndex + 5).dal("class", "value", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("name", entityVar);
						wForm.t(tIndex + 5).dal("id", classApiMethodMethod, "_", entityVar);
					}
					if("Page".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 5).dal("onchange", "patch", classSimpleName, "($('#", classSimpleName, "Form'), $('#", entityVar, "Form')); ");
					}
					wForm.tl(tIndex + 5, ";");
	
					wForm.tl(tIndex + 5, "if(o.get", entityVarCapitalized, "() != null && o.get", entityVarCapitalized, "())");
					wForm.t(tIndex + 6).a("checked", "checked").l(";");
					wForm.t(tIndex + 4).fgl();
					wForm.l();
					if(entityDisplayName != null) {
						wForm.t(tIndex + 4).e("label").da("for", classApiMethodMethod, "_", entityVar).da("class", "").df().dsxq(entityDisplayName).dgl("label");
					}
				}
				else {
					if(entityDisplayName != null) {
						wForm.t(tIndex + 4).e("label").da("for", classApiMethodMethod, "_", entityVar).da("class", "").df().dsxq(entityDisplayName).dgl("label");
					}
					wForm.l();
	
					if(entityMultiline)
						wForm.t(tIndex + 4).e("textarea").l();
					else
						wForm.t(tIndex + 4).e("input").l().t(tIndex + 5).dal("type", "text");
	
					if(entityDisplayName != null) {
						wForm.t(tIndex + 5).dal("placeholder", entityDisplayName);
					}
					if(entityDescription != null) {
						wForm.t(tIndex + 5).dal("title", entityDescription);
					}
	
					if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 5).dal("class", "set", entityVarCapitalized, " w3-input w3-border ");
						wForm.t(tIndex + 5).dal("name", "set", entityVarCapitalized);
						wForm.t(tIndex + 5).dal("id", classApiMethodMethod, "_", entityVar);
					}
					else {
						wForm.t(tIndex + 5).dal("class", "value", entityVarCapitalized, " w3-input w3-border ");
						wForm.t(tIndex + 5).dal("name", entityVar);
						wForm.t(tIndex + 5).dal("id", classApiMethodMethod, "_", entityVar);
					}
					if("Page".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 5).dal("onchange", "patch", classSimpleName, "($('#", classSimpleName, "Form'), $('#", entityVar, "Form')); ");
					}
	
					if(entityMultiline) {
						wForm.t(tIndex + 4).df();
						wForm.s(".sx(o.str", entityVarCapitalized, "())");
					}
					else {
						wForm.tl(tIndex + 5, ".a(\"value\", o.str", entityVarCapitalized, "())");
					}
	
					if(entityMultiline)
						wForm.dgl("textarea");
					else
						wForm.t(tIndex + 4).dfgl();
	
					wForm.l();
				}
				if("Page".equals(classApiMethodMethod)) {
					wForm.t(tIndex + 3).bgl("form");
				}
			}
			else {

				// entityDefine: false

				wForm.t(tIndex + 4).be("div").da("class", "").dfl();
				wForm.t(tIndex + 5).e("label").da("class", "").df().dsxq(entityDisplayName).dgl("label");
				wForm.t(tIndex + 4).bgl("div");
				wForm.t(tIndex + 4).be("div").da("class", "").dfl();
				wForm.t(tIndex + 5).e("span").df().s(".sx(o.str", entityVarCapitalized, "())").dgl("span");
				wForm.t(tIndex + 4).bgl("div");
			}

//			if().da("class", objets).da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
			wForm.t(3).bgl("div");
		}
		return result;
	}

	public void  pageCodeClass(String languageName) throws Exception, Exception {
		for(String classPageMethod : classApiMethods) {

			String classPagePathGen = (String)doc.get("classPagePathGen" + classPageMethod  + "_stored_string");
			String classPagePath = (String)doc.get("classPagePath" + classPageMethod  + "_stored_string");
			String classPagePathCss = (String)doc.get("classPagePathCss" + classPageMethod  + "_stored_string");
			String classPagePathJs = (String)doc.get("classPagePathJs" + classPageMethod  + "_stored_string");
			String classPageUriMethod = (String)classDoc.get("classApiUri" + classPageMethod + "_stored_string");
			String classPageLanguageName = (String)classDoc.get("classPageLanguageName" + classPageMethod + "_stored_string");

			classPageSimpleName = (String)doc.get("classPageSimpleName" + classPageMethod  + "_stored_string");
			classPageSuperSimpleName = (String)doc.get("classPageSuperSimpleName" + classPageMethod  + "_stored_string");
			classGenPageSimpleName = (String)doc.get("classGenPageSimpleName" + classPageMethod  + "_stored_string");
			String classPageCanonicalName = (String)doc.get("classPageCanonicalName" + classPageMethod  + "_stored_string");
	
			if(classPagePathGen != null && StringUtils.equals(classPageLanguageName, languageName)) {

				contextImageWidth = (Integer)classDoc.get("contextImageWidth" + "_" + languageName + "_stored_int");
				contextImageHeight = (Integer)classDoc.get("contextImageHeight" + "_" + languageName + "_stored_int");
				contextVideoId = (String)classDoc.get("contextVideoId" + "_" + languageName + "_stored_string");
				contextAName = (String)classDoc.get("contextAName" + "_" + languageName + "_stored_string");
				contextNameSingular = (String)classDoc.get("contextNameSingular" + "_" + languageName + "_stored_string");
				contextNamePlural = (String)classDoc.get("contextNamePlural" + "_" + languageName + "_stored_string");
				contextNameVar = (String)classDoc.get("contextNameVar" + "_" + languageName + "_stored_string");
				contextAdjective = (String)classDoc.get("contextAdjective" + "_" + languageName + "_stored_string");
				contextAdjectivePlural = (String)classDoc.get("contextAdjectivePlural" + "_" + languageName + "_stored_string");
				contextAdjectiveVar = (String)classDoc.get("contextAdjectiveVar" + "_" + languageName + "_stored_string");
				contextNameAdjectiveSingular = (String)classDoc.get("contextNameAdjectiveSingular" + "_" + languageName + "_stored_string");
				contextNameAdjectivePlural = (String)classDoc.get("contextNameAdjectivePlural" + "_" + languageName + "_stored_string");
				contextThis = (String)classDoc.get("contextThis" + "_" + languageName + "_stored_string");
				contextA = (String)classDoc.get("contextA" + "_" + languageName + "_stored_string");
				contextActualName = (String)classDoc.get("contextActualName" + "_" + languageName + "_stored_string");
				contextAllName = (String)classDoc.get("contextAllName" + "_" + languageName + "_stored_string");
				contextTheNames = (String)classDoc.get("contextTheNames" + "_" + languageName + "_stored_string");
				contextTitle = (String)classDoc.get("contextTitle" + "_" + languageName + "_stored_string");
				contextH1 = (String)classDoc.get("contextH1" + "_" + languageName + "_stored_string");
				contextH2 = (String)classDoc.get("contextH2" + "_" + languageName + "_stored_string");
				contextH3 = (String)classDoc.get("contextH3" + "_" + languageName + "_stored_string");
				contextNoNameFound = (String)classDoc.get("contextNoNameFound" + "_" + languageName + "_stored_string");
				contextANameAdjective = (String)classDoc.get("contextANameAdjective" + "_" + languageName + "_stored_string");
				contextThisName = (String)classDoc.get("contextThisName" + "_" + languageName + "_stored_string");
				contextTheName = (String)classDoc.get("contextTheName" + "_" + languageName + "_stored_string");
				contextOfName = (String)classDoc.get("contextOfName" + "_" + languageName + "_stored_string");
			
				File classPageFileGen = null;
				File classPageFile = null;
				File classPageFileCss = null;
				File classPageFileJs = null;

				if(classPagePathGen != null)
					classPageFileGen = new File(classPagePathGen);
				if(classPagePath != null)
					classPageFile = new File(classPagePath);
				if(classPagePathCss != null)
					classPageFileCss = new File(classPagePathCss);
				if(classPagePathJs != null)
					classPageFileJs = new File(classPagePathJs);
			
				AllWriter writerPageGenClass = null;
				AllWriter writerPageClass = null;
				AllWriter writerPageCss = null;
				AllWriter writerPageJs = null;

				if(classPageFileGen != null)
					writerPageGenClass = AllWriter.create(classPageFileGen);
				if(classPageFile != null && !classPageFile.exists())
					writerPageClass = AllWriter.create(classPageFile);
				if(classPageFileCss != null)
					writerPageCss = AllWriter.create(classPageFileCss);
				if(classPageFileJs != null)
					writerPageJs = AllWriter.create(classPageFileJs);

				Boolean pageH1 = false;
				Boolean pageH2 = false;
				Boolean pageH3 = false;
				Boolean pageTitle = false;
	
				AllWriter wSearch = AllWriter.create();
				AllWriter wPOST = AllWriter.create();
				AllWriter wPATCH = AllWriter.create();
				AllWriter wTh = AllWriter.create();
				AllWriter wTd = AllWriter.create();
				AllWriter wFormSearch = AllWriter.create();
				AllWriter wFormPOST = AllWriter.create();
				AllWriter wFormPage = AllWriter.create();
				AllWriter wFormPATCH = AllWriter.create();
				AllWriter wEntities = AllWriter.create();
	
				o = writerPageGenClass;
				{
					SolrQuery solrSearch = new SolrQuery();   
					solrSearch.setQuery("*:*");
					solrSearch.setRows(1000000);
					String fqSuperClassesAndMe = "(" + entitySuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
					solrSearch.addFilterQuery("partIsEntity_indexed_boolean:true");
					solrSearch.addFilterQuery("classCanonicalName_" + languageName + "_indexed_string:" + fqSuperClassesAndMe);
//					solrSearch.addFilterQuery("entityHtmlRow_indexed_int:[* TO *]");
					solrSearch.addSort("entityHtmlRow_indexed_int", ORDER.asc);
					solrSearch.addSort("entityHtmlCell_indexed_int", ORDER.asc);
					QueryResponse searchResponse = solrClientComputate.query(solrSearch);
					SolrDocumentList searchList = searchResponse.getResults();

					searchRows = solrSearch.getRows();

					searchRowSearch = -1;
					searchRowPOST = -1;
					searchRowPATCH = -1;
					searchRowPage = -1;

					List<String> pageVars = Arrays.asList("pageH1", "pageH2", "pageH3", "pageTitle");
	
					if(searchList.size() > 0) {
						Boolean resultFormPOST = false; 
						Boolean resultFormPage = false; 
						Boolean resultFormPATCH = false; 
						Boolean resultFormSearch = false; 

						for(Long i = searchList.getStart(); i < searchList.getNumFound(); i+=searchRows) {
							for(Integer j = 0; j < searchList.size(); j++) {
								entitySolrDocument = searchList.get(j);
								entityVar = (String)entitySolrDocument.get("entityVar_" + languageName + "_stored_string");
								entityVarCapitalized = (String)entitySolrDocument.get("entityVarCapitalized_" + languageName + "_stored_string");
								entitySimpleName = (String)entitySolrDocument.get("entitySimpleName_" + languageName + "_stored_string");
								entitySimpleNameGeneric = (String)entitySolrDocument.get("entitySimpleNameGeneric_" + languageName + "_stored_string");
								entitySimpleNameComplete = (String)entitySolrDocument.get("entitySimpleNameComplete_" + languageName + "_stored_string");
								entityDescription = (String)entitySolrDocument.get("entityDescription_" + languageName + "_stored_string");
								entityDisplayName = (String)entitySolrDocument.get("entityDisplayName_" + languageName + "_stored_string");
								entityHtmlRow = (Integer)entitySolrDocument.get("entityHtmlRow_stored_int");
								entityHtml = (Boolean)entitySolrDocument.get("entityHtml_stored_boolean");
								entityIndexed = (Boolean)entitySolrDocument.get("entityIndexed_stored_boolean");
								entityStored = (Boolean)entitySolrDocument.get("entityStored_stored_boolean");
								entityMultiline = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entityMultiline_stored_boolean"));
								entityDefine = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entityDefine_stored_boolean"));
	
								if(entityHtmlRow != null && pageVars.contains(entityVar)) {
									if(entityWrap) {
										if("pageH1".equals(entityVar)) {
											pageH1 = true;
										}
										else if("pageH2".equals(entityVar)) {
											pageH2 = true;
										}
										else if("pageH3".equals(entityVar)) {
											pageH3 = true;
										}
										else if("pageTitle".equals(entityVar)) {
											pageTitle = true;
										}
										else {
											wEntities.l();
											wEntities.t(1, "@Override protected void _", entityVar, "(");
											if(entityWrap)
												wEntities.s("Wrap<", entitySimpleNameComplete, "> c");
											else
												wEntities.s(entitySimpleNameComplete, " o");
											wEntities.l(") {");
											wEntities.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null)");
											wEntities.tl(3, "c.o(", StringUtils.uncapitalize(classSimpleName), ".get", entityVarCapitalized, "()", ");");
											wEntities.tl(1, "}");
										}
									}
								}
	
								if(entityHtml) {
									if(entityHtmlRow != null) {
										if(writeFormEntity(wFormPOST, "POST"))
											resultFormPOST = true;
										if(writeFormEntity(wFormPage, "Page"))
											resultFormPage = true;
									}
									if(entityStored) {
										if(writeFormEntity(wFormPATCH, "PATCH"))
											resultFormPATCH = true;
									}
									if(entityIndexed) {
										if(writeFormEntity(wFormSearch, "Search"))
											resultFormSearch = true;
									}
								}
							}
							solrSearch.setStart(i.intValue() + searchRows);
							searchResponse = solrClientComputate.query(solrSearch);
							searchList = searchResponse.getResults();
						}
						if(resultFormPOST)
							wFormPOST.t(2).bgl("div");
						if(resultFormPage)
							wFormPage.t(2).bgl("div");
						if(resultFormPATCH)
							wFormPATCH.t(2).bgl("div");
						if(resultFormSearch)
							wFormSearch.t(2).bgl("div");
					}
				}
	
				wEntities.l();
				wEntities.tl(1, "@Override protected void _pageH1(Wrap<String> c) {");
				if(pageH1) {
					wEntities.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null && ", StringUtils.uncapitalize(classSimpleName), ".getPageH1() != null)");
					wEntities.tl(3, "c.o(", StringUtils.uncapitalize(classSimpleName), ".getPageH1()", ");");
					wEntities.tl(2, "else if(", StringUtils.uncapitalize(classSimpleName), " != null)");
				} else {
					wEntities.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null)");
				}
				if(contextH1 != null)
					wEntities.tl(3, "c.o(", q(contextH1), ");");
				else
					wEntities.tl(3, "c.o(", q(contextAName), ");");
				if(!classPageSimple) {
					wEntities.tl(2, "else if(list", classSimpleName, " == null || list", classSimpleName, ".size() == 0)");
					wEntities.tl(3, "c.o(", q(contextNoNameFound), ");");
				}
				if(contextH1 != null) {
					wEntities.tl(2, "else");
					wEntities.tl(3, "c.o(", q(contextH1), ");");
				}
				wEntities.tl(1, "}");
	
				wEntities.l();
				wEntities.tl(1, "@Override protected void _pageH2(Wrap<String> c) {");
				if(pageH2) {
					wEntities.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null && ", StringUtils.uncapitalize(classSimpleName), ".getPageH2() != null)");
					wEntities.tl(3, "c.o(", StringUtils.uncapitalize(classSimpleName), ".getPageH2()", ");");
					if(contextH2 != null) {
						wEntities.tl(2, "else");
						wEntities.tl(3, "c.o(", q(contextH2), ");");
					}
				} else {
					wEntities.tl(2, "c.o(", q(contextH2), ");");
				}
				wEntities.tl(1, "}");
	
				wEntities.l();
				wEntities.tl(1, "@Override protected void _pageH3(Wrap<String> c) {");
				if(pageH3) {
					wEntities.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null && ", StringUtils.uncapitalize(classSimpleName), ".getPageH3() != null)");
					wEntities.tl(3, "c.o(", StringUtils.uncapitalize(classSimpleName), ".getPageH3()", ");");
					if(contextH3 != null) {
						wEntities.tl(2, "else");
						wEntities.tl(3, "c.o(", q(contextH3), ");");
					}
				} else {
					wEntities.tl(2, "c.o(", q(contextH3), ");");
				}
				wEntities.tl(1, "}");
	
				wEntities.l();
				wEntities.tl(1, "@Override protected void _pageTitle(Wrap<String> c) {");
				if(pageTitle) {
					wEntities.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null && ", StringUtils.uncapitalize(classSimpleName), ".getPageTitle() != null)");
					wEntities.tl(3, "c.o(", StringUtils.uncapitalize(classSimpleName), ".getPageTitle()", ");");
					wEntities.tl(2, "else if(", StringUtils.uncapitalize(classSimpleName), " != null)");
				} else {
					wEntities.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null)");
				}
				wEntities.tl(3, "c.o(", q(contextTitle), ");");
				if(!classPageSimple) {
					wEntities.tl(2, "else if(list", classSimpleName, " == null || list", classSimpleName, ".size() == 0)");
					wEntities.tl(3, "c.o(", q(contextNoNameFound), ");");
				}
				if(contextTitle != null) {
					wEntities.tl(2, "else");
					wEntities.tl(3, "c.o(", q(contextTitle), ");");
				}
				wEntities.tl(1, "}");
	
				wEntities.l();
				wEntities.tl(1, "@Override protected void _pageUri(Wrap<String> c) {");
				wEntities.tl(2, "c.o(", q(classPageUriMethod), ");");
				wEntities.tl(1, "}");
				for(String pageLanguageName : allLanguages) {
					if(!StringUtils.equals(classPageLanguageName, pageLanguageName)) {
						String classPageUriMethodLanguage = (String)classDoc.get(StringUtils.replace("classApiUri" + classPageMethod + "_stored_string", StringUtils.capitalize(classPageLanguageName), StringUtils.capitalize(pageLanguageName)));
						
						if(classPageUriMethodLanguage != null) {
							wEntities.l();
							wEntities.tl(1, "@Override protected void _pageUri", StringUtils.capitalize(pageLanguageName), "(Wrap<String> c) {");
							wEntities.tl(2, "c.o(", q(classPageUriMethodLanguage), ");");
							wEntities.tl(1, "}");
						}
					}
				}
		
				if(writerPageClass != null) {
					writerPageClass.l("package ", classPackageName, ";");
					writerPageClass.l();
					writerPageClass.l("/**");
					writerPageClass.l(" * Traduire: false");
					writerPageClass.l(" **/");
					writerPageClass.s("public class ", classPageSimpleName);
					writerPageClass.s(" extends ", classPageSimpleName, "Gen<", classGenPageSimpleName, ">");
					writerPageClass.l(" {");
					writerPageClass.tl(0, "}");
				}

				l("package ", classPackageName, ";");
				l();
				if(classImportsGenPage.size() > 0) { 
					for(String classImport : classImportsGenPage) {
						l("import ", classImport, ";");
					}
					l();
				}
		
				tl(0, "");
//				writeComment(classComment, 0); 
				l("/**");
				l(" * Traduire: false");
				l(" **/");
				s("public class ", classGenPageSimpleName);
				s(" extends ", classGenPageSimpleName, "Gen");
				s("<", classPageSuperSimpleName, ">");
				l(" {");
				if(!classPageSimple) {
					l();
					tl(1, "/**");
					tl(1, " * {@inheritDoc}");
					tl(1, " * ");
					tl(1, " **/");
					tl(1, "protected void _list", classSimpleName, "(Wrap<SearchList<", classSimpleName, ">> c) {");
					tl(1, "}");
				}
				l();
				tl(1, "protected void _", StringUtils.uncapitalize(classSimpleName), "(", "Wrap<", classSimpleName, "> c", ") {");
				if(classPageSimple) {
					tl(2, "c.o(new ", classSimpleName, "());");
				} else {
					tl(2, "if(list", classSimpleName, " != null && list", classSimpleName, ".size() == 1)");
					tl(3, "c.o(list", classSimpleName, ".get(0)", ");");
				}
				tl(1, "}");
				s(wEntities);
	
				if(contextDescription != null) {
					l();
					tl(1, "@Override protected void _pageDescription(Wrap<String> c) {");
					tl(3, "c.o(", q(contextDescription), ");");
					tl(1, "}");
				}
	
				if(classImage != null) {
					l();
					tl(1, "@Override protected void _pageImageUri(Wrap<String> c) {");
					tl(3, "c.o(", q("/png", classPageUriMethod, "-999.png"), ");");
					tl(1, "}");
				}
	
				if(contextImageWidth != null) {
					l();
					tl(1, "@Override protected void _pageImageWidth(Wrap<Integer> c) {");
					tl(3, "c.o(", contextImageWidth, ");");
					tl(1, "}");
				}
	
				if(contextImageHeight != null) {
					l();
					tl(1, "@Override protected void _pageImageHeight(Wrap<Integer> c) {");
					tl(3, "c.o(", contextImageHeight, ");");
					tl(1, "}");
				}
	
				if(StringUtils.isNotBlank(contextVideoId)) {
					l();
					tl(1, "@Override protected void _pageVideoId(Wrap<String> c) {");
					tl(3, "c.o(", q(contextVideoId), ");");
					tl(1, "}");
				}
	
				if(StringUtils.isNotBlank(contextIconGroup)) {
					l();
					tl(1, "@Override protected void _contextIconGroup(Wrap<String> c) {");
					tl(3, "c.o(", q(contextIconGroup), ");");
					tl(1, "}");
				}
	
				if(StringUtils.isNotBlank(contextIconName)) {
					l();
					tl(1, "@Override protected void _contextIconName(Wrap<String> c) {");
					tl(3, "c.o(", q(contextIconName), ");");
					tl(1, "}");
				}
	
				l();
				tl(1, "@Override public void initDeep", classGenPageSimpleName, "() {");
				tl(2, "init", classGenPageSimpleName, "();");
				tl(2, "super.initDeepPageLayout();");
				tl(1, "}");
				l();
				tl(1, "@Override public void htmlScripts", classGenPageSimpleName, "() {");
				t(2).e("script").da("src", "/static/js/", classPageSimpleName, ".js").df().dgl("script");
				tl(1, "}");
	
				if(StringUtils.isNotBlank(classApiUri)) {
					l();
					tl(1, "protected void _pageUri", classSimpleName, "(Wrap<String> c) {");
					tl(3, "c.o(", q(classPageUriMethod), ");");
					tl(1, "}");
				}
	
				{
					SolrQuery solrSearch = new SolrQuery();   
					solrSearch.setQuery("*:*");
					solrSearch.setRows(1000000);
					String fqSuperClassesAndMe = "(" + entitySuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
					solrSearch.addFilterQuery("partIsEntity_indexed_boolean:true");
					solrSearch.addFilterQuery("classCanonicalName_" + this.languageActualName + "_indexed_string:" + fqSuperClassesAndMe);
					solrSearch.addFilterQuery("entityHtmlColumn_indexed_double:[* TO *]");
					solrSearch.addSort("entityHtmlColumn_indexed_double", ORDER.asc);
					QueryResponse searchResponse = solrClientComputate.query(solrSearch);
					SolrDocumentList searchList = searchResponse.getResults();
					Integer searchRows = solrSearch.getRows();
					Integer searchRow = -1;
					Integer searchRowActual;
		
					if(searchList.size() > 0) {
						for(Long i = searchList.getStart(); i < searchList.getNumFound(); i+=searchRows) {
							for(Integer j = 0; j < searchList.size(); j++) {
								SolrDocument entitySolrDocument = searchList.get(j);
								String entityVar = (String)entitySolrDocument.get("entityVar_" + languageName + "_stored_string");
								String entityVarCapitalized = (String)entitySolrDocument.get("entityVarCapitalized_" + languageName + "_stored_string");
								String entitySimpleName = (String)entitySolrDocument.get("entitySimpleName_" + languageName + "_stored_string");
								String entitySimpleNameGeneric = (String)entitySolrDocument.get("entitySimpleNameGeneric_" + languageName + "_stored_string");
								String entitySimpleNameComplete = (String)entitySolrDocument.get("entitySimpleNameComplete_" + languageName + "_stored_string");
								String entityDescription = (String)entitySolrDocument.get("entityDescription_" + languageName + "_stored_string");
								String entityDisplayName = (String)entitySolrDocument.get("entityDisplayName_" + languageName + "_stored_string");
								Boolean entityHtml = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entityHtml_stored_boolean"));
								Boolean entityMultiline = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entityMultiline_stored_boolean"));
								Boolean entiteHighlighting = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entiteHighlighting_stored_boolean"));
								if(entityHtml) {
									String jsVal = ".val()";
									if("Boolean".equals(entitySimpleName)) {
										jsVal = ".prop('checked')";
									}
		
									wTh.tl(6, "e(\"th\").f().sx(", q(entityDisplayName), ").g(\"th\");");
		
									wTd.tl(7, "{ e(\"td\").f();");
									wTd.tl(8, "{ e(\"a\").a(\"href\", uri).f();");
									wTd.tl(9, "sx(o.get", entityVarCapitalized, "());");
									wTd.tl(8, "} g(\"a\");");
									if(entiteHighlighting) {
										wTd.tl(8, "if(highlightList != null) {");
										wTd.t(9).be("div").da("class", "site-highlight ").dfl();
											wTd.t(10).sscl("StringUtils.join(highlightList, \" ... \")");
										wTd.t(9).bgl("div");
										wTd.tl(8, "}");
									}
									wTd.tl(7, "} g(\"td\");");
								}
							}
							solrSearch.setStart(i.intValue() + searchRows);
							searchResponse = solrClientComputate.query(solrSearch);
							searchList = searchResponse.getResults();
						}
					}
				}
	
				{
					SolrQuery solrSearch = new SolrQuery();   
					solrSearch.setQuery("*:*");
					solrSearch.setRows(1000000);
					String fqSuperClassesAndMe = "(" + entitySuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
					solrSearch.addFilterQuery("partIsEntity_indexed_boolean:true");
					solrSearch.addFilterQuery("classCanonicalName_" + languageName + "_indexed_string:" + fqSuperClassesAndMe);
					solrSearch.addSort("entityHtmlRow_indexed_int", ORDER.asc);
					solrSearch.addSort("entityHtmlCell_indexed_int", ORDER.asc);
					QueryResponse searchResponse = solrClientComputate.query(solrSearch);
					SolrDocumentList searchList = searchResponse.getResults();
					Integer searchRows = solrSearch.getRows();
					Integer searchRow = -1;
					Integer searchRowActual;
		
					if(searchList.size() > 0) {
						for(Long i = searchList.getStart(); i < searchList.getNumFound(); i+=searchRows) {
							for(Integer j = 0; j < searchList.size(); j++) {
								entitySolrDocument = searchList.get(j);
								entityVar = (String)entitySolrDocument.get("entityVar_" + languageName + "_stored_string");
								entityVarCapitalized = (String)entitySolrDocument.get("entityVarCapitalized_" + languageName + "_stored_string");
								entitySimpleName = (String)entitySolrDocument.get("entitySimpleName_" + languageName + "_stored_string");
								entitySimpleNameGeneric = (String)entitySolrDocument.get("entitySimpleNameGeneric_" + languageName + "_stored_string");
								entitySimpleNameComplete = (String)entitySolrDocument.get("entitySimpleNameComplete_" + languageName + "_stored_string");
								entityDescription = (String)entitySolrDocument.get("entityDescription_" + languageName + "_stored_string");
								entityDisplayName = (String)entitySolrDocument.get("entityDisplayName_" + languageName + "_stored_string");
								entityHtml = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entityHtml_stored_boolean"));
								entityMultiline = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entityMultiline_stored_boolean"));
								entityIndexed = (Boolean)entitySolrDocument.get("entityIndexed_stored_boolean");
								String jsVal = ".val()";
								if("Boolean".equals(entitySimpleName)) {
									jsVal = ".prop('checked')";
								}

								if(entityIndexed) {
		
									wSearch.l();
									wSearch.tl(1, "var filter", entityVarCapitalized, " = $formFilters.find('.value", entityVarCapitalized, "')", jsVal, ";");

									if("Boolean".equals(entitySimpleName))
										wSearch.tl(1, "if(filter", entityVarCapitalized, " != null && filter", entityVarCapitalized, " === true)");
									else
										wSearch.tl(1, "if(filter", entityVarCapitalized, " != null && filter", entityVarCapitalized, " !== '')");

									wSearch.tl(2, "filters.push({ name: 'fq', value: '", entityVar, ":' + filter", entityVarCapitalized, " });");
								}

								if(entityHtml) {
		
									wPOST.l();
									wPOST.tl(1, "var value", entityVarCapitalized, " = $formValues.find('.value", entityVarCapitalized, "')", jsVal, ";");
									wPOST.tl(1, "if(value", entityVarCapitalized, " != null && value", entityVarCapitalized, " !== '')");
									wPOST.tl(2, "values['", entityVar, "'] = value", entityVarCapitalized, ";");
		
									wPATCH.l();
									wPATCH.tl(1, "var set", entityVarCapitalized, " = $formValues.find('.set", entityVarCapitalized, "')", jsVal, ";");
									wPATCH.tl(1, "if(set", entityVarCapitalized, " != null && set", entityVarCapitalized, " !== '')");
									wPATCH.tl(2, "values['set", entityVarCapitalized, "'] = set", entityVarCapitalized, ";");
									wPATCH.tl(1, "var add", entityVarCapitalized, " = $formValues.find('.add", entityVarCapitalized, "')", jsVal, ";");
									wPATCH.tl(1, "if(add", entityVarCapitalized, " != null && add", entityVarCapitalized, " !== '')");
									wPATCH.tl(2, "values['add", entityVarCapitalized, "'] = add", entityVarCapitalized, ";");
									wPATCH.tl(1, "var remove", entityVarCapitalized, " = $formValues.find('.remove", entityVarCapitalized, "')", jsVal, ";");
									wPATCH.tl(1, "if(remove", entityVarCapitalized, " != null && remove", entityVarCapitalized, " !== '')");
									wPATCH.tl(2, "values['remove", entityVarCapitalized, "'] = remove", entityVarCapitalized, ";");
								}
							}
							solrSearch.setStart(i.intValue() + searchRows);
							searchResponse = solrClientComputate.query(solrSearch);
							searchList = searchResponse.getResults();
						}
					}
				}
	
				l();
				if(!classPageSimple) {
					tl(1, "@Override public void htmlScript", classGenPageSimpleName, "() {");
					for(String classApiMethod : classApiMethods) {
						String classApiOperationIdMethod = (String)classDoc.get("classeApiOperationId" + classApiMethod + "_stored_string");
						String classApiUriMethod = (String)classDoc.get("classApiUri" + classApiMethod + "_stored_string");
						String classApiMediaTypeMethod = (String)classDoc.get("classeApiTypeMedia200" + classApiMethod + "_stored_string");
						String classApiMethodMethod = (String)classDoc.get("classApiMethod" + classApiMethod + "_stored_string");
		
						if("application/json".equals(classApiMediaTypeMethod)) {
							Boolean methodPOST = classApiMethodMethod.equals("POST");
							Boolean methodGET = classApiMethod.contains("GET");
							Boolean methodPUT = classApiMethodMethod.equals("PUT");
							Boolean methodPATCH = classApiMethodMethod.equals("PATCH");
							Boolean methodDELETE = classApiMethodMethod.equals("DELETE");
							Boolean methodSearch = classApiMethod.contains("Search");
		
							writerPageJs.l();
							writerPageJs.tl(0, "// ", classApiMethod, " //");
							writerPageJs.l();
							writerPageJs.l("/**");
							if(methodPATCH) {
							writerPageJs.l(" * Modify un ou multiple ", contextNamePlural, " sans valuers qui change, ");
							writerPageJs.l(" * ou changer des values pour un ou multiple ", contextTheName, ". ");
							writerPageJs.l(" * @param params: [ \"q=*:*\", \"fq=pk:1\", \"sort=pk asc\", \"rows=1\", \"fl=pk\" ]");
							writerPageJs.l(" *        Une list des opérations de search sur des ", contextNamePlural, " ");
							writerPageJs.l(" *        pour searchr \"q=*:*\", filterr \"fq=pk:1\", trier \"sort=pk desc\", ");
							writerPageJs.l(" *        limiter les résultats \"rows=1\", ou limiter les values \"fl=pk\". ");
							writerPageJs.l(" * @param values Noms des champs et values à changer selon les filters fq. ");
							writerPageJs.l(" *           Example: { pk: 1 }");
							}
							writerPageJs.l(" */");
							writerPageJs.t(0, "function ", classApiOperationIdMethod, "(");
							if(methodPOST)
								writerPageJs.s("$formValues");
							else if(methodPUT)
								writerPageJs.s("pk, $formValues");
							else if(methodPATCH)
								writerPageJs.s("$formFilters, $formValues");
							else if(methodSearch)
								writerPageJs.s("$formFilters");
							else if(methodGET || methodDELETE)
								writerPageJs.s("pk");
		
							writerPageJs.l(") {");
							if(methodPOST) {
								writerPageJs.tl(1, "var values = {};");
								writerPageJs.s(wPOST);
								writerPageJs.l();
							}
							else if(methodPUT) {
								writerPageJs.tl(1, "var values = {};");
								writerPageJs.s(wPOST);
								writerPageJs.l();
							}
							else if(methodPATCH) {
								writerPageJs.tl(1, "var filters = [];");
								writerPageJs.s(wSearch);
								writerPageJs.l();
								writerPageJs.tl(1, "var values = {};");
								writerPageJs.s(wPATCH);
								writerPageJs.l();
							}
							else if(methodSearch) {
								writerPageJs.tl(1, "var filters = [];");
								writerPageJs.s(wSearch);
							}
		
							writerPageJs.tl(1, "$.ajax({");
		
							if(methodGET || methodDELETE || methodPUT)
								writerPageJs.tl(2, "url: '", StringUtils.replace(classApiUriMethod, "{id}", "' + id"));
							else if(methodPATCH || methodSearch)
								writerPageJs.tl(2, "url: '", classApiUriMethod, "?' + $.param(filters)");
							else
								writerPageJs.tl(2, "url: '", classApiUriMethod, "'");
		
							writerPageJs.tl(2, ", dataType: 'json'");
							writerPageJs.tl(2, ", type: '", classApiMethodMethod, "'");
							writerPageJs.tl(2, ", contentType: 'application/json; charset=utf-8'");
							if(!"GET".equals(classApiMethodMethod) || "DELETE".equals(classApiMethodMethod))
								writerPageJs.tl(2, ", data: JSON.stringify(values)");
							writerPageJs.tl(2, ", success: function( data, textStatus, jQxhr ) {");
							writerPageJs.tl(2, "}");
							writerPageJs.tl(2, ", error: function( jqXhr, textStatus, errorThrown ) {");
							writerPageJs.tl(2, "}");
							writerPageJs.tl(1, "});");
							writerPageJs.l("}");
						}
						
					}
					tl(1, "}");
					l();
					tl(1, "public void htmlFormPage", classSimpleName, "(", classSimpleName, " o) {");
					s(wFormPage);
					tl(1, "}");
					l();
					tl(1, "public void htmlFormPOST", classSimpleName, "(", classSimpleName, " o) {");
					s(wFormPOST);
					tl(1, "}");
					l();
					tl(1, "public void htmlFormPATCH", classSimpleName, "(", classSimpleName, " o) {");
					s(wFormPATCH);
					tl(1, "}");
					l();
					tl(1, "public void htmlFormSearch", classSimpleName, "(", classSimpleName, " o) {");
					s(wFormSearch);
					tl(1, "}");
				}
				l();
				tl(1, "@Override public void htmlBody", classGenPageSimpleName, "() {");
				if(classPageSimple) {
					l();
					tl(2, "if(pageH1 != null) {");
					t(3).be("h1").dfl();
					tl(4, "if(contextIconCssClasses != null)");
					tl(5, "e(\"i\").a(\"class\", contextIconCssClasses + \" site-menu-icon \").f().g(\"i\");");
		
					if(classEntityVars != null && classEntityVars.contains("pageH1"))
						t(4).e("span").da("class", " ").df().s(".sx(pageH1)").dgl("span");
					else
						t(4).e("span").da("class", " ").df().dsxq(contextAName).dgl("span");
		
					t(3).bgl("h1");
					tl(2, "}");
		
					if(classEntityVars != null && classEntityVars.contains("pageH2")) {
						tl(2, "if(pageH2 != null) {");
						t(3).be("h2").dfl();
						t(4).e("span").da("class", " ").df().s(".sx(pageH2)").dgl("span");
						t(3).bgl("h2");
						tl(2, "}");
					}
		
					if(classEntityVars != null && classEntityVars.contains("pageH3")) {
						tl(2, "if(pageH3 != null) {");
						t(3).be("h3").dfl();
						t(4).e("span").da("class", " ").df().s(".sx(pageH3)").dgl("span");
						t(3).bgl("h3");
						tl(2, "}");
					}
		
					if(contextVideoId != null) {
						t(2).be("div").da("class", "site-video-box ").dfl();
							t(3).e("iframe").da("class", "site-video-embed ").da("width", "560").da("height", "315").s(".a(\"src\", pageVideoUrlEmbed)").da("frameborder", "0").da("allow", "autoplay; encrypted-media").da("allowfullscreen", "").df().dgl("iframe");
						t(2).bgl("div"); 
					}
					if(classMethodVars.contains("htmlBody")) {
						l();
						tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null)");
						tl(3, StringUtils.uncapitalize(classSimpleName), ".htmlBody();");
					}
					l();
				} else {
					l();
					tl(2, "OperationRequest operationRequest = requeteSite_.getOperationRequest();");
					tl(2, "JsonObject params = operationRequest.getParams();");
					tl(2, "if(list", classSimpleName, " == null || list", classSimpleName, ".size() == 0) {");
					t(3).l("// contextNoNameFound : ", contextNoNameFound);
					l();
					t(3).be("h1").dfl();
					tl(4, "if(contextIconCssClasses != null)");
					tl(5, "e(\"i\").a(\"class\", contextIconCssClasses + \" site-menu-icon \").f().g(\"i\");");
					t(4).e("span").da("class", " ").df().dsxq(contextNoNameFound).dgl("span");
					t(3).bgl("h1");
					tl(2, "} else if(list", classSimpleName, " != null && list", classSimpleName, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\") && params.getJsonObject(\"query\").getJsonArray(\"fq\") == null) {");
					t(3).l("// contextAName : ", contextAName);
					tl(3, "if(pageH1 != null) {");
					t(4).be("h1").dfl();
					tl(5, "if(contextIconCssClasses != null)");
					tl(6, "e(\"i\").a(\"class\", contextIconCssClasses + \" site-menu-icon \").f().g(\"i\");");
		
					if(classEntityVars != null && classEntityVars.contains("pageH1"))
						t(5).e("span").da("class", " ").df().s(".sx(pageH1)").dgl("span");
					else
						t(5).e("span").da("class", " ").df().dsxq(contextAName).dgl("span");
		
					t(4).bgl("h1");
					tl(4, classSimpleName, " o = list", classSimpleName, ".get(0);");

					tl(3, "}");
		
					if(classEntityVars != null && classEntityVars.contains("pageH2")) {
						tl(3, "if(pageH2 != null) {");
						t(4).be("h2").dfl();
						t(5).e("span").da("class", " ").df().s(".sx(pageH2)").dgl("span");
						t(4).bgl("h2");
						tl(3, "}");
					}
		
					if(classEntityVars != null && classEntityVars.contains("pageH3")) {
						tl(3, "if(pageH3 != null) {");
						t(4).be("h3").dfl();
						t(5).e("span").da("class", " ").df().s(".sx(pageH3)").dgl("span");
						t(4).bgl("h3");
						tl(3, "}");
					}
		
					tl(2, "} else {");
					t(3).l("// contextNamePlural : multiple ", contextNamePlural);
					l();
					t(3).be("h1").dfl();
					tl(4, "if(contextIconCssClasses != null)");
					tl(5, "e(\"i\").a(\"class\", contextIconCssClasses + \" site-menu-icon \").f().g(\"i\");");
					t(4).e("span").da("class", " ").df().dsxq(contextNamePlural).dgl("span");
					t(3).bgl("h1");
					t(3).be("table").da("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").dfl();
					t(4).be("thead").dfl();
					t(5).be("tr").dfl();
					s(wTh);
					t(5).bgl("tr");
					t(4).bgl("thead");
					t(4).be("tbody").dfl();
					tl(5, "Map<String, Map<String, List<String>>> highlighting = list", classSimpleName, ".getQueryResponse().getHighlighting();");
					tl(5, "for(int i = 0; i < list", classSimpleName, ".size(); i++) {");
					tl(6, classSimpleName, " o = list", classSimpleName, ".getList().get(i);");
					tl(6, "Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());");
					tl(6, "List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));");
					tl(6, "String uri = ", classEntityVars.contains("pageUri") ? "o.getPageUri()" : q(classPageUriMethod, "/") + " + o.getPk()", ";");
					tl(6, "{ e(\"tr\").f();");
					s(wTd);
					tl(6, "} g(\"tr\");");
					tl(5, "}");
					t(4).bgl("tbody");
					t(3).bgl("table");
					tl(2, "}");
		
					{
						// Formulaires de search
						SolrQuery solrSearch = new SolrQuery();   
						solrSearch.setQuery("*:*");
						solrSearch.setRows(1000000);
						String fqSuperClassesAndMe = "(" + entitySuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
						solrSearch.addFilterQuery("partIsEntity_indexed_boolean:true");
						solrSearch.addFilterQuery("classCanonicalName_" + languageActualName + "_indexed_string:" + fqSuperClassesAndMe);
						solrSearch.addFilterQuery("entityText_indexed_boolean:true");
						QueryResponse searchResponse = solrClientComputate.query(solrSearch);
						SolrDocumentList searchList = searchResponse.getResults();
						Integer searchRows = solrSearch.getRows();
						Integer searchRow = -1;
						Integer searchRowActual;
			
						if(searchList.size() > 0) {
							for(Long i = searchList.getStart(); i < searchList.getNumFound(); i+=searchRows) {
								for(Integer j = 0; j < searchList.size(); j++) {
									SolrDocument entitySolrDocument = searchList.get(j);
									String entityVar = (String)entitySolrDocument.get("entityVar_" + languageName + "_stored_string");
									String entityVarCapitalized = (String)entitySolrDocument.get("entityVarCapitalized_" + languageName + "_stored_string");
									String entityDescription = (String)entitySolrDocument.get("entityDescription_" + languageName + "_stored_string");
									String entityDisplayName = (String)entitySolrDocument.get("entityDisplayName_" + languageName + "_stored_string");
									String entityLanguage = (String)entitySolrDocument.get("entityLanguage_stored_string");
									Boolean entitySuggested = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entitySuggested_stored_boolean"));

									if(entityLanguage == null || StringUtils.equals(entityLanguage, languageName)) {
										l();
										t(2).be("div").da("class", "").dfl();
										t(3).be("form")
											.da("id", classSimpleName, "Form")
											.da("style", "display: inline-block; ")
											.da("method", "GET")
											.da("action", classPageUriMethod)
											.da("onsubmit", "event.preventDefault(); searchr($('#search" + entityVarCapitalized + "')); return false; ")
											.dfl();
										t(4).be("div").da("class", "w3-bar ").dfl();
	//									t(5).e("label").da("for", "search", entityVarCapitalized).da("class", "").df().dsxq(entityDisplayName).dgl("label");
										t(5).e("input").dal("type", "text");
										if(contextAllName != null) {
											if(entityDisplayName != null) {
												t(6).dal("placeholder", contextSearchAllNameBy + entityDisplayName);
											}
											else {
												t(6).dal("placeholder", contextSearchAllName);
											}
										}

										if(entityDisplayName != null) {
											t(6).dal("title", entityDescription);
										}
										else {
											t(6).dal("title", entityDescription);
										}

										t(6).dal("class", "search", entityVarCapitalized, " w3-input w3-border w3-bar-item ");
										t(6).dal("name", entityVar);
										t(6).da("id", "search", entityVarCapitalized).l(";");
										tl(5, "operationRequest.getParams().getJsonObject(\"query\").forEach(paramRequete -> {");
										tl(6, "String entityVar = null;");
										tl(6, "String valueIndexed = null;");
										tl(6, "String paramName = paramRequete.getKey();");
										tl(6, "Object paramValuesObject = paramRequete.getValue();");
										tl(6, "JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);");
										l();
										tl(6, "try {");
										tl(7, "for(Object paramObject : paramObjects) {");
										tl(8, "switch(paramName) {");
								
										tl(9, "case \"q\":");
										tl(10, "entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, \":\"));");
										tl(10, "valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, \":\")), \"UTF-8\");");

										tl(10, "if(\"", entityVar, "\".equals(entityVar))");
										tl(11, "a(\"value\", URLDecoder.decode(valueIndexed, \"UTF-8\"));");
										tl(8, "}");
										tl(7, "}");
										tl(6, "} catch(Exception e) {");
										tl(7, "ExceptionUtils.rethrow(e);");
										tl(6, "}");
										tl(5, "});");
										t(5).fgl();
					//					if("Page".equals(classApiMethodMethod)) {
					//						wForm.t(tIndex + 5).dal("onchange", "patch", classSimpleName, "($('#", classSimpleName, "Form'), $('#", entityVar, "Form')); ");
					//					}
	
										t(5).be("button").l();
										t(6).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-", contextColor, " ");
										t(6).dfl();
										t(6).e("i").da("class", "fas fa-search ").df().dgl("i");
										t(5).bgl("button");
										t(4).bgl("div");
										t(3).bgl("form");
										t(2).bgl("div");
									}
								}
								solrSearch.setStart(i.intValue() + searchRows);
								searchResponse = solrClientComputate.query(solrSearch);
								searchList = searchResponse.getResults();
							}
						}
					}

					// singulier part 2
					l();
					tl(2, "if(list", classSimpleName, " != null && list", classSimpleName, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\") && params.getJsonObject(\"query\").getJsonArray(\"fq\") == null) {");
					t(3).l(classSimpleName, " o = list", classSimpleName, ".first();");
					l();
					t(3).be("div").da("class", "w3-card w3-margin w3-padding w3-margin-top w3-show w3-white ").dfl();
					if(classVarPrimaryKey != null) {
						l();
						tl(4, "if(o.get", StringUtils.capitalize(classVarPrimaryKey), "() != null) {");
						t(5).be("form").da("action", classApiUri).da("id", classSimpleName, "Form").da("style", "display: inline-block; ").dfl();
						t(6).e("input").l();
						t(6).dal("name", classVarPrimaryKey);
						t(6).dal("class", "value", StringUtils.capitalize(classVarPrimaryKey));
						t(6).dal("type", "hidden");
						tl(6, ".a(\"value\", o.get", StringUtils.capitalize(classVarPrimaryKey), "())");
						t(6).dfgl();
						t(5).bgl("form");
						tl(5, "htmlFormPage", classSimpleName, "(o);");
						tl(4, "}");
					}
					if(classMethodVars.contains("htmlBody")) {
						l();
						tl(4, "if(o != null)");
						tl(5, "o.htmlBody();");
					}
					l();
					t(3).bgl("div");
					tl(2, "}");
		
					// formulaires
					if(!classPageSimple) {
						t(2).e("div").dfl();
						l();
						for(String classApiMethod : classApiMethods) {
							String classApiOperationIdMethod = (String)classDoc.get("classeApiOperationId" + classApiMethod + "_stored_string");
							String classApiUriMethod = (String)classDoc.get("classApiUri" + classApiMethod + "_stored_string");
							String classApiMediaTypeMethod = (String)classDoc.get("classeApiTypeMedia200" + classApiMethod + "_stored_string");
							String classApiMethodMethod = (String)classDoc.get("classApiMethod" + classApiMethod + "_stored_string");
			
							if("application/json".equals(classApiMediaTypeMethod) && !"GET".equals(classApiMethodMethod)) {
								Integer tab = classApiMethodMethod.contains("PATCH") || classApiMethodMethod.contains("DELETE") || classApiMethodMethod.contains("POST") ? 0 : 1;
								String methodTitleFiltres = null;
								String methodTitleValeurs = null;
			
								if("POST".equals(classApiMethodMethod)) {
									methodTitleValeurs = "Create " + contextAName;
								}
								else if("PUT".equals(classApiMethodMethod)) {
									methodTitleFiltres = "Searchr des " + contextAName;
									methodTitleValeurs = "Replace " + contextTheName;
								}
								else if("PATCH".equals(classApiMethodMethod)) {
									methodTitleFiltres = "Searchr des " + contextAName;
									methodTitleValeurs = "Modify des " + contextNamePlural;
								}
								else if("DELETE".equals(classApiMethodMethod)) {
									methodTitleFiltres = "Searchr des " + contextAName;
									methodTitleValeurs = "Delete des " + contextNamePlural;
								}
			
			
								l();
								if(tab > 0)
									tl(2, "if(list", classSimpleName, " != null && list", classSimpleName, ".size() == 1) {");
								t(2 + tab).e("button").l();
								t(3 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contextColor, " ");
								t(3 + tab).dal("onclick", "$('#", classApiOperationIdMethod, "Modale').show(); ");
								t(3 + tab).df().dsxq(methodTitleValeurs).l();
								t(2 + tab).dgl("button");
								{ t(2 + tab).be("div").da("id", classApiOperationIdMethod, "Modale").da("class", "w3-modal ").dfl();
									{ t(3 + tab).be("div").da("class", "w3-modal-content w3-card-4 ").dfl();
										{ t(4 + tab).be("header").da("class", "w3-container w3-", contextColor, " ").dfl();
											t(5 + tab).e("span").da("class", "w3-button w3-display-topright ").da("onclick", "$('#", classApiOperationIdMethod, "Modale').hide(); ").df().dsxq("×").dgl("span");
											t(5 + tab).e("h2").da("class", "").df().dsxq(methodTitleValeurs).dgl("h2");
										} t(4 + tab).bgl("header");
			
										{ t(4 + tab).be("div").da("class", "w3-container ").dfl();
											tl(5+ tab, classSimpleName, " o = new ", classSimpleName, "();");
											if("PATCH".equals(classApiMethodMethod)) {
												l();
												t(5 + tab).l("// FormFilters ", classApiMethodMethod);
												{ t(5 + tab).be("form").da("action", classApiUri).da("id", classApiOperationIdMethod, "FormFilters").dfl();
												tl(6 + tab, "htmlFormSearch", classSimpleName, "(o);");
												} t(5 + tab).bgl("form");
												t(5 + tab).e("button").l();
												t(6 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contextColor, " ");
				
												tl(6 + tab, ".a(\"onclick\", \"search", classSimpleName, "($('#", classApiOperationIdMethod, "FormFilters')); \")");
				
												t(6 + tab).df().dsxq(methodTitleFiltres).l();
												t(5 + tab).dgl("button");
												l();
												
												l();
												t(5 + tab).l("// FormValues ", classApiMethodMethod);
												{ t(5 + tab).be("form").da("action", classApiUri).da("id", classApiOperationIdMethod, "FormValues").dfl();
			
												if("DELETE".equals(classApiMethodMethod))
													tl(6 + tab, "htmlFormPATCH", classSimpleName, "(o);");
												else if("PUT".equals(classApiMethodMethod))
													tl(6 + tab, "htmlFormPOST", classSimpleName, "(o);");
												else
													tl(6 + tab, "htmlForm", classApiMethodMethod, classSimpleName, "(o);");
			
												} t(5 + tab).bgl("form");
												t(5 + tab).e("button").l();
												t(6 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contextColor, " ");
				
												if("POST".equals(classApiMethodMethod))
													tl(6 + tab, ".a(\"onclick\", \"", classApiOperationIdMethod, "($('#", classApiOperationIdMethod, "Form')); \")");
												else if("PATCH".equals(classApiMethodMethod))
													tl(6 + tab, ".a(\"onclick\", \"", classApiOperationIdMethod, "($('#", classApiOperationIdMethod, "FormFilters'), $('#", classApiOperationIdMethod, "FormValues')); \")");
												else if("PUT".equals(classApiMethodMethod))
													tl(6 + tab, ".a(\"onclick\", \"", classApiOperationIdMethod, "(\", o.getPk(), \", $('#", classApiOperationIdMethod, "Form')); \")");
												else if(tab > 0)
													tl(6 + tab, ".a(\"onclick\", \"", classApiOperationIdMethod, "(\", o.getPk(), \"); \")");
												else
													t(6 + tab).dal("onclick", classApiOperationIdMethod, "(); ");
				
												t(6 + tab).df().dsxq(methodTitleValeurs).l();
												t(5 + tab).dgl("button");
												l();
											}
											else {
												l();
												t(5 + tab).l("// Form ", classApiMethodMethod);
												{ t(5 + tab).be("form").da("action", classApiUri).da("id", classApiOperationIdMethod, "Form").dfl();
			
												if("DELETE".equals(classApiMethodMethod))
													tl(6 + tab, "htmlFormPATCH", classSimpleName, "(o);");
												else if("PUT".equals(classApiMethodMethod))
													tl(6 + tab, "htmlFormPOST", classSimpleName, "(o);");
												else
													tl(6 + tab, "htmlForm", classApiMethodMethod, classSimpleName, "(o);");
			
												} t(5 + tab).bgl("form");
												t(5 + tab).e("button").l();
												t(6 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contextColor, " ");
				
				//								tl(6 + tab, ".a(\"onclick\", \"alert(JSON.stringify($('#", classApiOperationIdMethod, "Form').serializeArray().reduce(function(a, x) { a[x.name] = x.value; return a; }, {}))); \")");
				//								tl(6 + tab, ".a(\"onclick\", \"alert(JSON.stringify($('#", classApiOperationIdMethod, "Form').serializeObject())); \")");
				
												if("POST".equals(classApiMethodMethod))
													tl(6 + tab, ".a(\"onclick\", \"", classApiOperationIdMethod, "($('#", classApiOperationIdMethod, "Form')); \")");
												else if("PATCH".equals(classApiMethodMethod))
													tl(6 + tab, ".a(\"onclick\", \"", classApiOperationIdMethod, "($('#", classApiOperationIdMethod, "FormFilters'), $('#", classApiOperationIdMethod, "FormValues')); \")");
												else if("PUT".equals(classApiMethodMethod))
													tl(6 + tab, ".a(\"onclick\", \"", classApiOperationIdMethod, "(\", o.getPk(), \", $('#", classApiOperationIdMethod, "Form')); \")");
												else if(tab > 0)
													tl(6 + tab, ".a(\"onclick\", \"", classApiOperationIdMethod, "(\", o.getPk(), \"); \")");
												else
													t(6 + tab).dal("onclick", classApiOperationIdMethod, "(); ");
				
												t(6 + tab).df().dsxq(methodTitleValeurs).l();
												t(5 + tab).dgl("button");
												l();
											}
										} t(4 + tab).bgl("div");
									} t(3 + tab).bgl("div");
								} t(2 + tab).bgl("div");
			
								l();
								if(tab > 0)
									tl(2, "}");
							}
						}
						t(2).gl("div");
					}
				}
				tl(1, "}");

				l();
				if(classMethodVars.contains("htmlBodyShort")) {
					tl(1, "@Override public void htmlBodyShort", classGenPageSimpleName, "() {");
					l();
					tl(2, StringUtils.uncapitalize(classSimpleName), ".htmlBodyShort();");
					tl(1, "}");
				}
	
				tl(0, "}");
	
				wTh.flushClose();

				writerPageGenClass.flushClose();
				System.out.println("Write: " + classPagePathGen); 
				if(writerPageClass != null) {
					writerPageClass.flushClose();
					System.out.println("Write: " + classPagePath); 
				}
				writerPageCss.flushClose();
				System.out.println("Write: " + classPagePathCss); 
				writerPageJs.flushClose();
				System.out.println("Write: " + classPagePathJs); 

				{
					WatchClass watchClass = new WatchClass();
					watchClass.appPath = appPath;
					watchClass.classAbsolutePath = classPagePath;
					watchClass.appName = appName;
					watchClass.initWatchClassBase(); 
//					watchClass.ecrireGenClasses(watchClass.classAbsolutePath, languageName, languageName);
					WatchClass.watchClass(watchClass, languageName);
				}

				{
					WatchClass watchClass = new WatchClass();
					watchClass.appPath = appPath;
					watchClass.classAbsolutePath = classPagePathGen;
					watchClass.appName = appName;
					watchClass.initWatchClassBase(); 
//					watchClass.ecrireGenClasses(watchClass.classAbsolutePath, languageName, languageName);
					WatchClass.watchClass(watchClass, languageName);
				}
	
	//		writerGenPageClass.close();
			}
		}
	}
}
