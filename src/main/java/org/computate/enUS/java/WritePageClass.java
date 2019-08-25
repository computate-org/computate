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

	protected List<String> classAttributeSimpleNamePages;

	public void  pageCodeClassStart(String languageName) throws Exception, Exception {
	}

	public void  pageCodeClassEnd(String languageName) throws Exception, Exception {
	}

	public Boolean writeFormEntity(String languageName, AllWriter wForm, String classApiMethodMethod) {
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
			if(entiteModifier && (entityDefine || entityAttribute)) {

				if("Page".equals(classApiMethodMethod)) {
					tIndex = 1;
				}

				wForm.t(tIndex + 3).be("div").da("class", "w3-padding ").dfl();
				wForm.t(tIndex + 4).be("form").da("action", classApiUri).da("id", "form", classSimpleName, entityVarCapitalized).da("style", "display: inline-block; ").da("onsubmit", "event.preventDefault(); return false; ").dfl();
				wForm.t(tIndex + 5).e("input").l();
				wForm.t(tIndex + 6).dal("type", "hidden");
				wForm.t(tIndex + 6).dal("name", str_value(langueNom), StringUtils.capitalize(entityAttributeVar));
				wForm.t(tIndex + 6).dal("class", str_value(langueNom), StringUtils.capitalize(entityAttributeVar), " ");
				wForm.t(tIndex + 6).l(".a(\"value\", ", str_requeteSite(langueNom), "_.get", str_Requete(langueNom), "Pk())");
				wForm.t(tIndex + 6).dfgl();
				wForm.t(tIndex + 4).bgl("form");
				wForm.t(tIndex + 4).be("form").da("action", classApiUri).da("id", str_suggere(langueNom), classSimpleName, entityVarCapitalized).da("style", "display: inline-block; ").da("onsubmit", "event.preventDefault(); return false; ").dfl();
				wForm.t(tIndex + 5).be("div").da("class", "w3-card ").dfl();

				if(entityAttribute) {
					if(entityDisplayName != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classApiMethodMethod, "_", entityVar).da("class", "").df().dsxq(entityDisplayName).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.l();
	
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "text");
	
					if(entityDisplayName != null) {
						wForm.t(tIndex + 9).dal("placeholder", entityDisplayName);
					}
					if(entityDescription != null) {
						wForm.t(tIndex + 9).dal("title", entityDescription);
					}
	
					wForm.t(tIndex + 9).dal("class", str_value(langueNom), StringUtils.capitalize(entityAttributeVarSuggest), " ", "suggest", entityVarCapitalized, " w3-input w3-border ");
					wForm.t(tIndex + 9).dal("name", "set", entityVarCapitalized);
					wForm.t(tIndex + 9).dal("id", classApiMethodMethod, "_", entityVar);
					wForm.t(tIndex + 9).dal("autocomplete", "off");
					wForm.t(tIndex + 9).dal("oninput", str_rechercher(langueNom), classSimpleName, entityVarCapitalized, "($('#' + ($(this).val() ? '", str_suggere(langueNom), "' : 'form') + '", classSimpleName, entityVarCapitalized, "'), $('#", "list", classSimpleName, entityVarCapitalized, "')); ");

					wForm.t(tIndex + 8).dfgl();
	
					wForm.l();
					wForm.t(tIndex + 7).bgl("div");
					wForm.t(tIndex + 6).bgl("div");
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell w3-left-align w3-cell-top ").dfl();
					wForm.t(tIndex + 8).be("ul").da("class", "w3-ul w3-hoverable ").da("id", "list", classSimpleName, entityVarCapitalized).dfl();
					wForm.t(tIndex + 8).bgl("ul");
					wForm.t(tIndex + 7).bgl("div");
				}
				else if("LocalDate".equals(entitySimpleName)) {
					wForm.tl(tIndex + 6, entitySimpleNameComplete, " val = o.get", entityVarCapitalized, "();");
					l();
					if(entityDisplayName != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classApiMethodMethod, "_", entityVar).da("class", "").df().dsxq(entityDisplayName).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row  ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "text");
					wForm.t(tIndex + 9).dal("class", "w3-input w3-border datepicker ");
					wForm.t(tIndex + 9).dal("placeholder", str_DDDashMMDashYYYY(langueNom));
					wForm.t(tIndex + 9).dal("data-timeformat", str_DDDashMMDashYYYY(langueNom));
					wForm.t(tIndex + 9).dal("onclick", str_removeGlow(langueNom), "($(this)); ");
					if(entityDescription != null)
						wForm.t(tIndex + 9).dal("title", entityDescription + " (", str_DDDashMMDashYYYY(langueNom), ")");
					wForm.tl(tIndex + 9, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"", str_ddDashMMDashyyyy(langueNom), "\", Locale.forLanguageTag(\"", str_frDashFR(langueNom), "\")).format(val))");
					wForm.t(tIndex + 9).s(".a(\"onchange\", \"");
						wForm.s("var t = moment(this.value, '", str_DDDashMMDashYYYY(langueNom), "'); ");
						wForm.s("if(t) { ");
							wForm.s("var s = t.format('YYYY/MM/DD'); ");
							wForm.s("$(this).next().val(s); ");
							wForm.s("$(this).next().trigger('change'); ");
						wForm.s("} ");
					wForm.l("\")");
					wForm.tl(tIndex + 9, ".fg();");
	
					wForm.t(tIndex + 8).e("input").l();
					if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 9).dal("class", "set", entityVarCapitalized);
						wForm.t(tIndex + 9).dal("name", "set", entityVarCapitalized);
					}
					else {
						wForm.t(tIndex + 9).dal("class", str_value(langueNom), entityVarCapitalized);
						wForm.t(tIndex + 9).dal("name", entityVar);
						wForm.t(tIndex + 9).dal("id", classApiMethodMethod, "_", entityVar);
					}
					if("Page".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 9).dal("onchange", "patch", classSimpleName, "($('#", classSimpleName, "Form'), $('#", entityVar, "Form'), $(this)); ");
					}
					wForm.t(tIndex + 9).dal("type", "hidden");
					wForm.tl(tIndex + 9, ".a(\"value\", o.str", entityVarCapitalized, "())");
					wForm.t(tIndex + 8).dfgl();
	
					if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 8).e("input").l();
						wForm.t(tIndex + 9).dal("class", "remove", entityVarCapitalized);
						wForm.t(tIndex + 9).dal("name", "remove", entityVarCapitalized);
						wForm.t(tIndex + 9).dal("type", "hidden");
						wForm.tl(tIndex + 9, ".a(\"value\", \"false\")");
						wForm.t(tIndex + 8).dfgl();
					}

					wForm.t(tIndex + 7).bgl("div");
				}
				else if("LocalDateTime".equals(entitySimpleName) || "ZonedDateTime".equals(entitySimpleName)) {
					wForm.tl(tIndex + 6, entitySimpleNameComplete, " val = o.get", entityVarCapitalized, "();");
					wForm.l();
					if(entityDisplayName != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classApiMethodMethod, "_", entityVar).da("class", "").df().dsxq(entityDisplayName).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "text");
					wForm.t(tIndex + 9).dal("class", "w3-input w3-border datepicker ");
					wForm.t(tIndex + 9).dal("placeholder", str_DDDashMMDashYYYY(langueNom));
					wForm.t(tIndex + 9).dal("data-timeformat", str_DDDashMMDashYYYY(langueNom));
					wForm.t(tIndex + 9).dal("onclick", str_removeGlow(langueNom), "($(this)); ");
					if(entityDescription != null)
						wForm.t(tIndex + 9).dal("title", entityDescription + " (", str_DDDashMMDashYYYY(langueNom), ")");
					wForm.tl(tIndex + 9, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"dd/MM/yyyy\", Locale.forLanguageTag(\"", str_frDashFR(langueNom), "\")).format(val))");
					wForm.t(tIndex + 9).s(".a(\"onchange\", \"");
						wForm.s("var t = moment(this.value, '", str_DDDashMMDashYYYY(langueNom), "'); ");
						wForm.s("if(t) { ");
							wForm.s("var s = t.format('YYYY/MM/DD'); ");
							wForm.s("$(this).next().val(s); ");
							wForm.s("$(this).next().trigger('change'); ");
						wForm.s("} ");
					wForm.l("\")");
					wForm.tl(tIndex + 9, ".fg();");
	
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "hidden");
	
					if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 9).dal("class", "set", entityVarCapitalized);
						wForm.t(tIndex + 9).dal("name", "set", entityVarCapitalized);
						wForm.t(tIndex + 9).dal("id", classApiMethodMethod, "_", entityVar);
					}
					else {
						wForm.t(tIndex + 9).dal("class", str_value(langueNom), entityVarCapitalized);
						wForm.t(tIndex + 9).dal("name", entityVar);
						wForm.t(tIndex + 9).dal("id", classApiMethodMethod, "_", entityVar);
					}
					if("Page".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 9).dal("onchange", "patch", classSimpleName, "($('#", classSimpleName, "Form'), $('#", entityVar, "Form'), $(this)); ");
					}
	
					wForm.tl(tIndex + 9, ".a(\"value\", o.str", entityVarCapitalized, "())");
					wForm.t(tIndex + 8).dfgl();
					wForm.t(tIndex + 9).bgl("div");
				}
				else if("LocalTime".equals(entitySimpleName)) {
					wForm.tl(tIndex + 6, entitySimpleNameComplete, " val = o.get", entityVarCapitalized, "();");
					wForm.l();
					if(entityDisplayName != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classApiMethodMethod, "_", entityVar).da("class", "").df().dsxq(entityDisplayName).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "text");
					wForm.t(tIndex + 9).dal("class", "w3-input w3-border timepicker ");
					wForm.t(tIndex + 9).dal("placeholder", "HH:MM AM");
					wForm.t(tIndex + 9).dal("onclick", str_removeGlow(langueNom), "($(this)); ");
					if(entityDescription != null)
						wForm.t(tIndex + 9).da("title", entityDescription + " (h'h'mm)");
					wForm.tl(tIndex + 9, ".a(\"value\", val == null ? \"\" : DateTimeFormatter.ofPattern(\"dd/MM/yyyy\", Locale.forLanguageTag(\"", str_frDashFR(langueNom), "\")).format(val))");
					wForm.t(tIndex + 9).s(".a(\"onchange\", \"");
						wForm.s("var t = parseTime(this.value); ");
						wForm.s("if(t) { ");
							wForm.s("var s = dateFormat(t, \"'h'MM\"); ");
							wForm.s("$(this).next().val(s); ");
							wForm.s("$(this).next().trigger('change'); ");
						wForm.s("} ");
					wForm.l("\")");
					wForm.tl(tIndex + 9, ".fg();");
	
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "hidden");
	
					if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 9).dal("class", "set", entityVarCapitalized);
						wForm.t(tIndex + 9).dal("name", "set", entityVarCapitalized);
						wForm.t(tIndex + 9).dal("id", classApiMethodMethod, "_", entityVar);
					}
					else {
						wForm.t(tIndex + 9).dal("class", str_value(langueNom), entityVarCapitalized);
						wForm.t(tIndex + 9).dal("name", entityVar);
						wForm.t(tIndex + 9).dal("id", classApiMethodMethod, "_", entityVar);
					}
					if("Page".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 9).dal("onchange", "patch", classSimpleName, "Val({ 'pk': $('#", classSimpleName, "Form :input[name=\"pk\"]').val() }, 'set", entityVarCapitalized, "', $(this).val(), $(this)); ");
					}
	
					wForm.tl(tIndex + 9, ".a(\"value\", val == null ? \"\" : o.str", entityVarCapitalized, "())");
					wForm.t(tIndex + 8).dfgl();
					wForm.t(tIndex + 7).bgl("div");
				}
				else if("Boolean".equals(entitySimpleName)) {
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "hidden");
					wForm.t(tIndex + 9).dal("name", entityVar);
					wForm.t(tIndex + 9).dal("id", classApiMethodMethod, "_", entityVar);
					wForm.t(tIndex + 9).dal("value", "false");
					wForm.t(tIndex + 8).dfgl();
					wForm.l();
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "checkbox");
					wForm.t(tIndex + 9).dal("value", "true");
	
					if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 9).dal("class", "set", entityVarCapitalized);
						wForm.t(tIndex + 9).dal("name", "set", entityVarCapitalized);
						wForm.t(tIndex + 9).dal("id", classApiMethodMethod, "_", entityVar);
					}
					else {
						wForm.t(tIndex + 9).dal("class", str_value(langueNom), entityVarCapitalized);
						wForm.t(tIndex + 9).dal("name", entityVar);
						wForm.t(tIndex + 9).dal("id", classApiMethodMethod, "_", entityVar);
					}
					if("Page".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 9).dal("onchange", "patch", classSimpleName, "Val({ 'pk': $('#", classSimpleName, "Form :input[name=\"pk\"]').val() }, 'set", entityVarCapitalized, "', $(this).val(), $(this)); ");
					}
					wForm.tl(tIndex + 9, ";");
	
					wForm.tl(tIndex + 9, "if(o.get", entityVarCapitalized, "() != null && o.get", entityVarCapitalized, "())");
					wForm.t(tIndex + 10).a("checked", "checked").l(";");
					wForm.t(tIndex + 8).fgl();
					wForm.l();
					wForm.t(tIndex + 7).bgl("div");
					if(entityDisplayName != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classApiMethodMethod, "_", entityVar).da("class", "").df().dsxq(entityDisplayName).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
				}
				else {
					if(entityDisplayName != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classApiMethodMethod, "_", entityVar).da("class", "").df().dsxq(entityDisplayName).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.l();
	
					if(entityMultiline)
						wForm.t(tIndex + 8).e("textarea").l();
					else {
						wForm.t(tIndex + 8).e("input").l();
						wForm.t(tIndex + 9).dal("type", "text");
					}
	
					if(entityDisplayName != null) {
						wForm.t(tIndex + 9).dal("placeholder", entityDisplayName);
					}
					if(entityDescription != null) {
						wForm.t(tIndex + 9).dal("title", entityDescription);
					}
	
					if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 9).dal("class", "set", entityVarCapitalized, " w3-input w3-border ");
						wForm.t(tIndex + 9).dal("name", "set", entityVarCapitalized);
						wForm.t(tIndex + 9).dal("id", classApiMethodMethod, "_", entityVar);
					}
					else {
						wForm.t(tIndex + 9).dal("class", str_value(langueNom), entityVarCapitalized, " w3-input w3-border ");
						wForm.t(tIndex + 9).dal("name", entityVar);
						wForm.t(tIndex + 9).dal("id", classApiMethodMethod, "_", entityVar);
					}
					if("Page".equals(classApiMethodMethod)) {
						wForm.t(tIndex + 9).dal("onclick", str_removeGlow(langueNom), "($(this)); ");
						wForm.t(tIndex + 9).dal("onchange", "patch", classSimpleName, "Val({ 'pk': $('#", classSimpleName, "Form :input[name=\"pk\"]').val() }, 'set", entityVarCapitalized, "', $(this).val(), $(this)); ");
					}
	
					if(entityMultiline) {
						wForm.t(tIndex + 8).df();
						wForm.s(".sx(o.str", entityVarCapitalized, "())");
					}
					else {
						wForm.tl(tIndex + 9, ".a(\"value\", o.str", entityVarCapitalized, "())");
					}
	
					if(entityMultiline)
						wForm.dgl("textarea");
					else
						wForm.t(tIndex + 8).dfgl();
	
					wForm.l();
					wForm.t(tIndex + 7).bgl("div");
				}
				if(!entityAttribute && entiteModifier && "Page".equals(classApiMethodMethod)) {

					wForm.t(tIndex + 7).be("div").da("class", "w3-cell w3-left-align w3-cell-top ").dfl();
					wForm.t(tIndex + 8).be("button").l();
					wForm.t(tIndex + 9).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-", contexteCouleur, " ");
					wForm.t(tIndex + 8).dal("onclick", str_removeGlow(langueNom), "($('#", classApiMethodMethod, "_", entityVar, "')); $('#", classApiMethodMethod, "_", entityVar, "').val(null); patch", classSimpleName, "Val({ 'pk': $('#", classSimpleName, "Form :input[name=\"pk\"]').val() }, 'set", entityVarCapitalized, "', null, $('#", classApiMethodMethod, "_", entityVar, "')); ");
					wForm.t(tIndex + 9).dfl();
					wForm.t(tIndex + 9).e("i").da("class", "far fa-eraser ").df().dgl("i");
					wForm.t(tIndex + 8).bgl("button");
					wForm.t(tIndex + 7).bgl("div");
				}

				wForm.t(tIndex + 6).bgl("div");
				wForm.t(tIndex + 5).bgl("div");
				wForm.t(tIndex + 4).bgl("form");
				wForm.t(tIndex + 3).bgl("div");
			}
			else {

				// entiteModifier: false
				if("Page".equals(classApiMethodMethod)) {
					tIndex = 1;
				}

				wForm.t(tIndex + 3).be("div").da("class", "w3-padding ").dfl();
				wForm.t(tIndex + 4).be("div").da("class", "w3-card ").dfl();

				if(entityDisplayName != null) {
					wForm.t(tIndex + 5).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
					wForm.t(tIndex + 6).e("label").da("class", "").df().dsxq(entityDisplayName).dgl("label");
					wForm.t(tIndex + 5).bgl("div");
				}
				wForm.t(tIndex + 5).be("div").da("class", "w3-cell-row  ").dfl();
				wForm.t(tIndex + 6).be("div").da("class", "w3-cell ").dfl();
				wForm.t(tIndex + 7).be("div").da("class", "w3-rest ").dfl();
				wForm.t(tIndex + 8).e("span").df().s(".sx(o.str", entityVarCapitalized, "())").dgl("span");
				wForm.t(tIndex + 7).bgl("div");
				wForm.t(tIndex + 6).bgl("div");
				wForm.t(tIndex + 5).bgl("div");
				wForm.t(tIndex + 4).bgl("div");
				wForm.t(tIndex + 3).bgl("div");
			}

//			if().da("class", objets).da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
			wForm.t(3).bgl("div");
		}
		return result;
	}

	public void  pageCodeClass(String languageName) throws Exception, Exception {
		for(String classPageMethod : classApiMethods) {

			String classPagePathGen = (String)classDoc.get("classPagePathGen" + classPageMethod  + "_" + languageName + "_stored_string");
			String classPagePath = (String)classDoc.get("classPagePath" + classPageMethod  + "_" + languageName + "_stored_string");
			String classPagePathCss = (String)classDoc.get("classPagePathCss" + classPageMethod  + "_" + languageName + "_stored_string");
			String classPagePathJs = (String)classDoc.get("classPagePathJs" + classPageMethod  + "_" + languageName + "_stored_string");
			String classPageUriMethod = (String)classDoc.get("classApiUri" + classPageMethod + "_" + languageName + "_stored_string");
			String classPageLanguageName = (String)classDoc.get("classPageLanguageName" + classPageMethod + "_" + languageName + "_stored_string");

			classPageSimpleName = (String)classDoc.get("classPageSimpleName" + classPageMethod  + "_" + languageName + "_stored_string");
			classPageSuperSimpleName = (String)classDoc.get("classPageSuperSimpleName" + classPageMethod  + "_" + languageName + "_stored_string");
			classGenPageSimpleName = (String)classDoc.get("classGenPageSimpleName" + classPageMethod  + "_" + languageName + "_stored_string");
			String classPageCanonicalName = (String)classDoc.get("classPageCanonicalName" + classPageMethod  + "_" + languageName + "_stored_string");
			classAttributeSimpleNamePages = (List<String>)classDoc.get("classAttributeSimpleNamePages_" + languageName + "_stored_strings");
	
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
				if(classPageFileCss != null) {
					classPageFileCss.getParentFile().mkdirs();
					writerPageCss = AllWriter.create(classPageFileCss);
				}
				if(classPageFileJs != null) {
					classPageFileJs.getParentFile().mkdirs();
					writerPageJs = AllWriter.create(classPageFileJs);
				}

				Boolean pageH1 = false;
				Boolean pageH2 = false;
				Boolean pageH3 = false;
				Boolean pageTitle = false;
	
				AllWriter wSearch = AllWriter.create();
				AllWriter wPOST = AllWriter.create();
				AllWriter wPATCH = AllWriter.create();
				AllWriter wSuggest = AllWriter.create();
				AllWriter wTh = AllWriter.create();
				AllWriter wTd = AllWriter.create();
				AllWriter wFormSearch = AllWriter.create();
				AllWriter wFormPOST = AllWriter.create();
				AllWriter wFormPage = AllWriter.create();
				AllWriter wFormPATCH = AllWriter.create();
				AllWriter wEntities = AllWriter.create();
				AllWriter wJsInit = AllWriter.create();
	
				o = writerPageGenClass;
				{
					SolrQuery solrSearch = new SolrQuery();   
					solrSearch.setQuery("*:*");
					solrSearch.setRows(1000000);
					String fqSuperClassesAndMe = "(" + entitySuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
					solrSearch.addFilterQuery("partIsEntity_indexed_boolean:true");
					solrSearch.addFilterQuery("classCanonicalName_" + languageActualName + "_indexed_string:" + fqSuperClassesAndMe);
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
								entityWrap = (Boolean)entitySolrDocument.get("entityWrap_stored_boolean");
								entityHtml = (Boolean)entitySolrDocument.get("entityHtml_stored_boolean");
								entityIndexed = (Boolean)entitySolrDocument.get("entityIndexed_stored_boolean");
								entityStored = (Boolean)entitySolrDocument.get("entityStored_stored_boolean");
								entityMultiline = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entityMultiline_stored_boolean"));
								entiteModify = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entiteModifier_stored_boolean"));
								entityDefine = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entityDefine_stored_boolean"));
								entiteAttribuer = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entiteAttribuer_stored_boolean"));
								entitySuggested = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entitySuggested_stored_boolean"));
								entiteAttribuerNomSimple = (String)entitySolrDocument.get("entiteAttribuerNomSimple_" + languageName + "_stored_string");
								entiteAttribuerVar = (String)entitySolrDocument.get("entiteAttribuerVar_" + languageName + "_stored_string");
								entiteAttribuerVarSuggere = (String)entitySolrDocument.get("entiteAttribuerVarSuggere_" + languageName + "_stored_string");
								entityAttributeOperationIdPATCH = (String)entitySolrDocument.get("entityAttributeOperationIdPATCH_" + languageName + "_stored_string");
								entityAttributeOperationIdSearch = (String)entitySolrDocument.get("entiteAttribuerOperationId" + str_Search(languageName) + "_" + languageName + "_stored_string");
	
								if(pageVars.contains(entityVar)) {
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
												wEntities.s(classePartsWrap.nomSimple(languageName), "<", entitySimpleNameComplete, "> c");
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
										if(writeFormEntity(languageName, wFormPOST, "POST"))
											resultFormPOST = true;
										if(writeFormEntity(languageName, wFormPage, "Page"))
											resultFormPage = true;
									}
									if(entityStored) {
										if(writeFormEntity(languageName, wFormPATCH, "PATCH"))
											resultFormPATCH = true;
									}
									if(entityIndexed) {
										if(writeFormEntity(languageName, wFormSearch, "Search"))
											resultFormSearch = true;
									}
								}
								if(entiteAttribuer) {
									wJsInit.tl(2, "tl(1, ", q(str_searchr(languageName), classSimpleName, entityVarCapitalized, "($('#", "form", classSimpleName, entityVarCapitalized, "'), $('#", "list", classSimpleName, entityVarCapitalized, "')); "), ");");
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
				wEntities.tl(1, "@Override protected void _pageH1(", classePartsWrap.nomSimple(languageName), "<String> c) {");
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
					wEntities.tl(2, "else if(", str_list(languageName), classSimpleName, " == null || ", str_list(languageName), classSimpleName, ".size() == 0)");
					wEntities.tl(3, "c.o(", q(contextNoNameFound), ");");
				}
				if(contextH1 != null) {
					wEntities.tl(2, "else");
					wEntities.tl(3, "c.o(", q(contextH1), ");");
				}
				wEntities.tl(1, "}");
	
				wEntities.l();
				wEntities.tl(1, "@Override protected void _pageH2(", classePartsWrap.nomSimple(languageName), "<String> c) {");
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
				wEntities.tl(1, "@Override protected void _pageH3(", classePartsWrap.nomSimple(languageName), "<String> c) {");
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
				wEntities.tl(1, "@Override protected void _page", str_Titre(languageName), "(", classePartsWrap.nomSimple(languageName), "<String> c) {");
				if(pageTitle) {
					wEntities.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null && ", StringUtils.uncapitalize(classSimpleName), ".getPage", str_Titre(languageName), "() != null)");
					wEntities.tl(3, "c.o(", StringUtils.uncapitalize(classSimpleName), ".getPage", str_Titre(languageName), "()", ");");
					wEntities.tl(2, "else if(", StringUtils.uncapitalize(classSimpleName), " != null)");
				} else {
					wEntities.tl(2, "if(", StringUtils.uncapitalize(classSimpleName), " != null)");
				}
				wEntities.tl(3, "c.o(", q(contextTitle), ");");
				if(!classPageSimple) {
					wEntities.tl(2, "else if(", str_list(languageName), classSimpleName, " == null || ", str_list(languageName), classSimpleName, ".size() == 0)");
					wEntities.tl(3, "c.o(", q(contextNoNameFound), ");");
				}
				if(contextTitle != null) {
					wEntities.tl(2, "else");
					wEntities.tl(3, "c.o(", q(contextTitle), ");");
				}
				wEntities.tl(1, "}");
	
				wEntities.l();
				wEntities.tl(1, "@Override protected void _pageUri(", classePartsWrap.nomSimple(languageName), "<String> c) {");
				wEntities.tl(2, "c.o(", q(classPageUriMethod), ");");
				wEntities.tl(1, "}");
				for(String pageLanguageName : allLanguages) {
					if(!StringUtils.equals(classPageLanguageName, pageLanguageName)) {
						String classPageUriMethodLanguage = (String)classDoc.get(StringUtils.replace("classApiUri" + classPageMethod + "_stored_string", StringUtils.capitalize(classPageLanguageName), StringUtils.capitalize(pageLanguageName)));
						
						if(classPageUriMethodLanguage != null) {
							wEntities.l();
							wEntities.tl(1, "@Override protected void _pageUri", StringUtils.capitalize(pageLanguageName), "(", classePartsWrap.nomSimple(languageName), "<String> c) {");
							wEntities.tl(2, "c.o(", q(classPageUriMethodLanguage), ");");
							wEntities.tl(1, "}");
						}
					}
				}
		
				if(writerPageClass != null) {
					writerPageClass.l("package ", classPackageName, ";");
					writerPageClass.l();
					writerPageClass.l("/**");
					writerPageClass.l(" * ", str_Traduire(languageName), ": false");
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
				l(" * ", str_Traduire(languageName), ": false");
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
					tl(1, "protected void _", str_list(languageName), classSimpleName, "(", classePartsWrap.nomSimple(languageName), "<", str_SearchList(languageName), "<", classSimpleName, ">> c) {");
					tl(1, "}");
				}
				l();
				tl(1, "protected void _", StringUtils.uncapitalize(classSimpleName), "(", "", classePartsWrap.nomSimple(languageName), "<", classSimpleName, "> c", ") {");
				if(classPageSimple) {
					tl(2, "c.o(new ", classSimpleName, "());");
				} else {
					tl(2, "if(", str_list(languageName), classSimpleName, " != null && ", str_list(languageName), classSimpleName, ".size() == 1)");
					tl(3, "c.o(", str_list(languageName), classSimpleName, ".get(0)", ");");
				}
				tl(1, "}");
				s(wEntities);
	
				if(contextDescription != null) {
					l();
					tl(1, "@Override protected void _pageDescription(", classePartsWrap.nomSimple(languageName), "<String> c) {");
					tl(3, "c.o(", q(contextDescription), ");");
					tl(1, "}");
				}
	
				if(classImage != null) {
					l();
					tl(1, "@Override protected void _pageImageUri(", classePartsWrap.nomSimple(languageName), "<String> c) {");
					tl(3, "c.o(", q("/png", classPageUriMethod, "-999.png"), ");");
					tl(1, "}");
				}
	
				if(contextImageWidth != null) {
					l();
					tl(1, "@Override protected void _pageImage", str_Largeur(languageName), "(", classePartsWrap.nomSimple(languageName), "<Integer> c) {");
					tl(3, "c.o(", contextImageWidth, ");");
					tl(1, "}");
				}
	
				if(contextImageHeight != null) {
					l();
					tl(1, "@Override protected void _pageImage", str_Hauteur(languageName), "(", classePartsWrap.nomSimple(languageName), "<Integer> c) {");
					tl(3, "c.o(", contextImageHeight, ");");
					tl(1, "}");
				}
	
				if(StringUtils.isNotBlank(contextVideoId)) {
					l();
					tl(1, "@Override protected void _pageVideoId(", classePartsWrap.nomSimple(languageName), "<String> c) {");
					tl(3, "c.o(", q(contextVideoId), ");");
					tl(1, "}");
				}
	
				if(StringUtils.isNotBlank(contextIconGroup)) {
					l();
					tl(1, "@Override protected void _", str_contexte(languageName), str_Icone(languageName), str_Groupe(languageName), "(", classePartsWrap.nomSimple(languageName), "<String> c) {");
					tl(3, "c.o(", q(contextIconGroup), ");");
					tl(1, "}");
				}
	
				if(StringUtils.isNotBlank(contextIconName)) {
					l();
					tl(1, "@Override protected void _", str_contexte(languageName), str_Icone(languageName), str_Nom(languageName), "(", classePartsWrap.nomSimple(languageName), "<String> c) {");
					tl(3, "c.o(", q(contextIconName), ");");
					tl(1, "}");
				}
	
				l();
				tl(1, "@Override public void ", str_initDeep(languageName), classGenPageSimpleName, "() {");
				tl(2, "init", classGenPageSimpleName, "();");
				tl(2, "super.", str_initDeep(languageName), classePartsPageLayout.nomSimple(languageName), "();");
				tl(1, "}");
				l();
				tl(1, "@Override public void htmlScripts", classGenPageSimpleName, "() {");
				t(2).l("e(\"script\").a(\"src\", ", str_statiqueUrlBase(languageName), ", \"/js/", languageName, "/", classPageSimpleName, ".js\").f().g(\"script\");");
				if(classAttributeSimpleNamePages != null) {
					for(String classAttributeSimpleNamePage : classAttributeSimpleNamePages) {
						t(2).l("e(\"script\").a(\"src\", ", str_statiqueUrlBase(languageName), ", \"/js/", languageName, "/", classAttributeSimpleNamePage, ".js\").f().g(\"script\");");
					}
				}
				tl(1, "}");
	
				if(StringUtils.isNotBlank(classApiUri)) {
					l();
					tl(1, "protected void _pageUri", classSimpleName, "(", classePartsWrap.nomSimple(languageName), "<String> c) {");
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
					solrSearch.addFilterQuery("classCanonicalName_" + languageActualName + "_indexed_string:" + fqSuperClassesAndMe);
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
								entitySuggested = (Boolean)entitySolrDocument.get("entitySuggested_stored_boolean");
								entitySimpleNameVertxJson = (String)entitySolrDocument.get("entitySimpleNameVertxJson_stored_string");
								String jsVal = ".val()";
								if("Boolean".equals(entitySimpleName)) {
									jsVal = ".prop('checked')";
								}

								if(entityIndexed) {
		
									wSearch.l();
									wSearch.tl(1, "var ", str_filter(languageName), entityVarCapitalized, " = $", str_formFilters(languageName), ".find('.", str_value(languageName), entityVarCapitalized, "')", jsVal, ";");

									if("Boolean".equals(entitySimpleName))
										wSearch.tl(1, "if(", str_filter(languageName), entityVarCapitalized, " != null && ", str_filter(languageName), entityVarCapitalized, " === true)");
									else
										wSearch.tl(1, "if(", str_filter(languageName), entityVarCapitalized, " != null && ", str_filter(languageName), entityVarCapitalized, " !== '')");

									wSearch.tl(2, str_filters(languageName), ".push({ name: '", (entitySuggested ? "q" : "fq"), "', value: '", entityVar, ":' + ", str_filter(languageName), entityVarCapitalized, " });");
								}

								if(entityHtml) {
									String valPrefixe;
									String valSuffixe;
									if("Double".equals(entitySimpleNameVertxJson)) {
										valPrefixe = "parseDouble(";
										valSuffixe = ")";
									}
									else if("Integer".equals(entitySimpleNameVertxJson)) {
										valPrefixe = "parseInt(";
										valSuffixe = ")";
									}
									else { 
										valPrefixe = "";
										valSuffixe = "";
									}
		
									wPOST.l();
									wPOST.tl(1, "var ", str_value(languageName), entityVarCapitalized, " = $", str_formValues(languageName), ".find('.", str_value(languageName), entityVarCapitalized, "')", jsVal, ";");
									wPOST.tl(1, "if(", str_value(languageName), entityVarCapitalized, " != null && ", str_value(languageName), entityVarCapitalized, " !== '')");
									wPOST.tl(2, str_values(languageName), "['", entityVar, "'] = ", valPrefixe, str_value(languageName), entityVarCapitalized, ";");
		
									wPATCH.l();

									wPATCH.tl(1, "var remove", entityVarCapitalized, " = $", str_formFilters(languageName), ".find('.remove", entityVarCapitalized, "').val() === 'true';");
									wPATCH.tl(1, "var set", entityVarCapitalized, " = remove", entityVarCapitalized, " ? null : $", str_formValues(languageName), ".find('.set", entityVarCapitalized, "')", jsVal, ";");
									wPATCH.tl(1, "if(remove", entityVarCapitalized, " || set", entityVarCapitalized, " != null && set", entityVarCapitalized, " !== '')");
									wPATCH.tl(2, str_values(languageName), "['set", entityVarCapitalized, "'] = ", valPrefixe, "set", entityVarCapitalized, valSuffixe, ";");
									wPATCH.tl(1, "var add", entityVarCapitalized, " = $", str_formValues(languageName), ".find('.add", entityVarCapitalized, "')", jsVal, ";");
									wPATCH.tl(1, "if(add", entityVarCapitalized, " != null && add", entityVarCapitalized, " !== '')");
									wPATCH.tl(2, str_values(languageName), "['add", entityVarCapitalized, "'] = ", valPrefixe, "add", entityVarCapitalized, valSuffixe, ";");
									wPATCH.tl(1, "var remove", entityVarCapitalized, " = $", str_formValues(languageName), ".find('.remove", entityVarCapitalized, "')", jsVal, ";");
									wPATCH.tl(1, "if(remove", entityVarCapitalized, " != null && remove", entityVarCapitalized, " !== '')");
									wPATCH.tl(2, str_values(languageName), "['remove", entityVarCapitalized, "'] = ", valPrefixe, "remove", entityVarCapitalized, valSuffixe, ";");
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
						String classApiOperationIdMethod = (String)classDoc.get("classeApiOperationId" + classApiMethod + "_" + languageName + "_stored_string");
						String classApiUriMethod = (String)classDoc.get("classApiUri" + classApiMethod + "_" + languageName + "_stored_string");
						String classApiMediaTypeMethod = (String)classDoc.get("classeApiTypeMedia200" + classApiMethod + "_" + languageName + "_stored_string");
						String classApiMethodMethod = (String)classDoc.get("classApiMethod" + classApiMethod + "_" + languageName + "_stored_string");
		
						if("application/json".equals(classApiMediaTypeMethod)) {
							Boolean methodPOST = classApiMethodMethod.equals("POST");
							Boolean methodGET = classApiMethod.contains("GET");
							Boolean methodPUT = classApiMethodMethod.equals("PUT");
							Boolean methodPATCH = classApiMethodMethod.equals("PATCH");
							Boolean methodDELETE = classApiMethodMethod.equals("DELETE");
							Boolean methodSearch = classApiMethod.contains(str_Search(languageName));
		
							writerPageJs.l();
							writerPageJs.tl(0, "// ", classApiMethod, " //");
							writerPageJs.l();
//							writerPageJs.l("/**");
//							if(methodPATCH) {
//							writerPageJs.l(" * Modify un ou multiple ", contextNamePlural, " sans valuers qui change, ");
//							writerPageJs.l(" * ou changer des values pour un ou multiple ", contextTheName, ". ");
//							writerPageJs.l(" * @param params: [ \"q=*:*\", \"fq=pk:1\", \"sort=pk asc\", \"rows=1\", \"fl=pk\" ]");
//							writerPageJs.l(" *        Une list des opérations de search sur des ", contextNamePlural, " ");
//							writerPageJs.l(" *        pour searchr \"q=*:*\", filterr \"fq=pk:1\", trier \"sort=pk desc\", ");
//							writerPageJs.l(" *        limiter les résultats \"rows=1\", ou limiter les values \"fl=pk\". ");
//							writerPageJs.l(" * @param values Noms des champs et values à changer selon les filters fq. ");
//							writerPageJs.l(" *           Example: { pk: 1 }");
//							}
//							writerPageJs.l(" */");
							writerPageJs.t(0, "function ", classApiOperationIdMethod, "(");
							if(methodPOST)
								writerPageJs.s("$", str_formValues(languageName));
							else if(methodPUT)
								writerPageJs.s("pk, $", str_formValues(languageName));
							else if(methodPATCH)
								writerPageJs.s("$", str_formFilters(languageName), ", $", str_formValues(languageName), ", success, error");
							else if(methodSearch) {
								writerPageJs.s("$", str_formFilters(languageName), "");
								writerPageJs.s(", success");
								writerPageJs.s(", error");
							}
							else if(methodGET || methodDELETE)
								writerPageJs.s("pk");
		
							writerPageJs.l(") {");
							if(methodPOST) {
								writerPageJs.tl(1, "var ", str_values(languageName), " = {};");
								writerPageJs.s(wPOST);
								writerPageJs.l();
							}
							else if(methodPUT) {
								writerPageJs.tl(1, "var ", str_values(languageName), " = {};");
								writerPageJs.s(wPOST);
								writerPageJs.l();
							}
							else if(methodPATCH) {
								writerPageJs.tl(1, "var ", str_filters(languageName), " = ", classApiOperationIdMethod,str_Filtres(languageName), "($", str_formFilters(languageName), ");");
								writerPageJs.l();
								writerPageJs.tl(1, "var ", str_values(languageName), " = {};");
								writerPageJs.s(wPATCH);
								writerPageJs.l();
							}
							else if(methodSearch) {
								writerPageJs.tl(1, "var ", str_filters(languageName), " = ", classApiOperationIdMethod,str_Filtres(languageName), "($", str_formFilters(languageName), ");");
								writerPageJs.tl(1, "if(success == null)");
								writerPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {};");
								writerPageJs.tl(1, "if(error == null)");
								writerPageJs.tl(2, "error = function( jqXhr, textStatus, errorThrown ) {};");
								writerPageJs.l();
							}
		
							if(methodPATCH) {
//								writerPageJs.tl(1, classApiOperationIdMethod, "Vals(", str_values(languageName), ", $", str_formValues(languageName), ", success, error);");
								writerPageJs.tl(1, classApiOperationIdMethod, "Vals(", str_filters(languageName), ", ", str_values(languageName), ", success, error);");
							}
							else {
								writerPageJs.tl(1, "$.ajax({");
			
								if(methodGET || methodDELETE || methodPUT)
									writerPageJs.tl(2, "url: '", StringUtils.replace(classApiUriMethod, "{id}", "' + id"));
								else if(methodPATCH || methodSearch)
									writerPageJs.tl(2, "url: '", classApiUriMethod, "?' + $.param(", str_filters(languageName), ")");
								else
									writerPageJs.tl(2, "url: '", classApiUriMethod, "'");
			
								writerPageJs.tl(2, ", dataType: 'json'");
								writerPageJs.tl(2, ", type: '", classApiMethodMethod, "'");
								writerPageJs.tl(2, ", contentType: 'application/json; charset=utf-8'");
								if(!"GET".equals(classApiMethodMethod) || "DELETE".equals(classApiMethodMethod))
									writerPageJs.tl(2, ", data: JSON.stringify(", str_values(languageName), ")");
								writerPageJs.tl(2, ", success: success");
								writerPageJs.tl(2, ", error: error");
//								writerPageJs.tl(2, ", success: function( data, textStatus, jQxhr ) {");
//								writerPageJs.tl(3, "$.each( ", str_values(languageName), ", function( key, value ) {");
//								writerPageJs.tl(4, "$", str_formValues(languageName), ".find('.' + key).removeClass('", str_lueur(languageName), str_Erreur(languageName), "');");
//								writerPageJs.tl(4, "$", str_formValues(languageName), ".find('.' + key).addClass('", str_lueur(languageName), str_Succes(languageName), "');");
//								writerPageJs.tl(3, "});");
//								writerPageJs.tl(2, "}");
//								writerPageJs.tl(2, ", error: function( jqXhr, textStatus, errorThrown ) {");
//								writerPageJs.tl(3, "$.each( ", str_values(languageName), ", function( key, value ) {");
//								writerPageJs.tl(4, "$", str_formValues(languageName), ".find('.' + key).removeClass('", str_lueur(languageName), str_Succes(languageName), "');");
//								writerPageJs.tl(4, "$", str_formValues(languageName), ".find('.' + key).addClass('", str_lueur(languageName), str_Erreur(languageName), "');");
//								writerPageJs.tl(3, "});");
//								writerPageJs.tl(2, "}");
								writerPageJs.tl(1, "});");
							}
							writerPageJs.l("}");

							if(methodPATCH || methodSearch) {
								writerPageJs.l();
								writerPageJs.tl(0, "function ", classApiOperationIdMethod,str_Filtres(languageName), "($", str_formFilters(languageName), ") {");
								writerPageJs.tl(1, "var ", str_filters(languageName), " = [];");
								writerPageJs.s(wSearch);
								writerPageJs.tl(1, "return ", str_filters(languageName), ";");
								writerPageJs.tl(0, "}");
							}
							if(methodPATCH) {
								writerPageJs.l();
								writerPageJs.tl(0, "function ", classApiOperationIdMethod, "Val(", str_filters(languageName), ", v, val, $", str_formulaireValeur(languageName), ") {");
								writerPageJs.tl(1, "var json = {};");
								writerPageJs.tl(1, "json[v] = val;");
//								writerPageJs.tl(1, classApiOperationIdMethod, "Vals(", str_filters(languageName), ", json, $", str_formulaireValeur(languageName), " == null ? $", str_formulaireValeur(languageName), " : [$", str_formulaireValeur(languageName), "]);");
								writerPageJs.tl(1, classApiOperationIdMethod, "Vals(", str_filters(languageName), ", json);");
								writerPageJs.l("}");
								writerPageJs.l();
//								writerPageJs.tl(0, "function ", classApiOperationIdMethod, "Vals(", str_filters(languageName), ", ", str_values(languageName), ", $", str_formValues(languageName), ", success, error) {");
								writerPageJs.tl(0, "function ", classApiOperationIdMethod, "Vals(", str_filters(languageName), ", ", str_values(languageName), ", success, error) {");
								writerPageJs.tl(1, "$.ajax({");
								writerPageJs.tl(2, "url: '", classApiUriMethod, "?' + $.param(", str_filters(languageName), ")");
								writerPageJs.tl(2, ", dataType: 'json'");
								writerPageJs.tl(2, ", type: '", classApiMethodMethod, "'");
								writerPageJs.tl(2, ", contentType: 'application/json; charset=utf-8'");
								writerPageJs.tl(2, ", data: JSON.stringify(", str_values(languageName), ")");
								writerPageJs.tl(2, ", success: success");
								writerPageJs.tl(2, ", error: error");
//								writerPageJs.tl(2, ", success: function( data, textStatus, jQxhr ) {");
//								writerPageJs.tl(3, "if($", str_formValues(languageName), " != null) {");
//								writerPageJs.tl(4, "for(var i = 0; i < $", str_formValues(languageName), ".length; i++) {");
//								writerPageJs.tl(5, "$", str_formulaireValeur(languageName), " = $", str_formValues(languageName), "[i];");
//								writerPageJs.tl(5, "$", str_formulaireValeur(languageName), ".removeClass('", str_lueur(languageName), str_Erreur(languageName), "');");
//								writerPageJs.tl(5, "$", str_formulaireValeur(languageName), ".addClass('", str_lueur(languageName), str_Succes(languageName), "');");
//								writerPageJs.tl(4, "}");
//								writerPageJs.tl(3, "}");
//								writerPageJs.tl(2, "}");
//								writerPageJs.tl(2, ", error: function( jqXhr, textStatus, errorThrown ) {");
//								writerPageJs.tl(3, "if($", str_formValues(languageName), " != null) {");
//								writerPageJs.tl(4, "for(var i = 0; i < $", str_formValues(languageName), ".length; i++) {");
//								writerPageJs.tl(5, "$", str_formulaireValeur(languageName), " = $", str_formValues(languageName), "[i];");
//								writerPageJs.tl(5, "$", str_formulaireValeur(languageName), ".removeClass('", str_lueur(languageName), str_Succes(languageName), "');");
//								writerPageJs.tl(5, "$", str_formulaireValeur(languageName), ".addClass('", str_lueur(languageName), str_Erreur(languageName), "');");
//								writerPageJs.tl(4, "}");
//								writerPageJs.tl(3, "}");
//								writerPageJs.tl(2, "}");
								writerPageJs.tl(1, "});");
								writerPageJs.l("}");
							}
							if(methodSearch) {
								SolrQuery solrSearch = new SolrQuery();   
								solrSearch.setQuery("*:*");
								solrSearch.setRows(1000000);
								String fqSuperClassesAndMe = "(" + entitySuperClassesAndMeWithoutGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
								solrSearch.addFilterQuery("partIsEntity_indexed_boolean:true");
								solrSearch.addFilterQuery("classCanonicalName_" + languageActualName + "_indexed_string:" + fqSuperClassesAndMe);
								solrSearch.addFilterQuery("(entitySuggested_indexed_boolean:true OR entiteAttribuer_indexed_boolean:true)");
								QueryResponse searchResponse = solrClientComputate.query(solrSearch);
								SolrDocumentList searchList = searchResponse.getResults();
			
								searchRows = solrSearch.getRows();
				
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
											entityHtmlRow = (Integer)entitySolrDocument.get("entityHtmlRow_stored_int");
											entityWrap = (Boolean)entitySolrDocument.get("entityWrap_stored_boolean");
											entityHtml = (Boolean)entitySolrDocument.get("entityHtml_stored_boolean");
											entityIndexed = (Boolean)entitySolrDocument.get("entityIndexed_stored_boolean");
											entityStored = (Boolean)entitySolrDocument.get("entityStored_stored_boolean");
											entityMultiline = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entityMultiline_stored_boolean"));
											entiteModify = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entiteModifier_stored_boolean"));
											entityDefine = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entityDefine_stored_boolean"));
											entiteAttribuer = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entiteAttribuer_stored_boolean"));
											entitySuggested = BooleanUtils.isTrue((Boolean)entitySolrDocument.get("entitySuggested_stored_boolean"));
											entiteAttribuerNomSimple = (String)entitySolrDocument.get("entiteAttribuerNomSimple_" + languageName + "_stored_string");
											entiteAttribuerVar = (String)entitySolrDocument.get("entiteAttribuerVar_" + languageName + "_stored_string");
											entiteAttribuerVarUrl = (String)entitySolrDocument.get("entiteAttribuerVarUrl_" + languageName + "_stored_string");
											entiteAttribuerVarTitre = (String)entitySolrDocument.get("entiteAttribuerVarTitre_" + languageName + "_stored_string");
											entiteAttribuerVarDescription = (String)entitySolrDocument.get("entiteAttribuerVarDescription_" + languageName + "_stored_string");
											entiteAttribuerVarImageUrl = (String)entitySolrDocument.get("entiteAttribuerVarImageUrl_" + languageName + "_stored_string");
											entiteAttribuerVarSuggere = (String)entitySolrDocument.get("entiteAttribuerVarSuggere_" + languageName + "_stored_string");
											entityAttributeOperationIdSearch = (String)entitySolrDocument.get("entiteAttribuerOperationId" + str_Search(languageName) + "_" + languageName + "_stored_string");
											entityAttributeOperationIdPATCH = (String)entitySolrDocument.get("entityAttributeOperationIdPATCH_" + languageName + "_stored_string");
											entityOperationIdPATCH = (String)entitySolrDocument.get("entityOperationIdPATCH_" + languageName + "_stored_string");
											entityJsonType = (String)entitySolrDocument.get("entityJsonType_stored_string");
											entityAttributeJsonType = (String)entitySolrDocument.get("entityAttributeJsonType_stored_string");
				
											if(entitySuggested) {
												writerPageJs.l();
												writerPageJs.tl(0, "function ", str_searchr(languageName), classSimpleName, entityVarCapitalized, "($", str_formFilters(languageName), ", $list) {");
												writerPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
//												writerPageJs.tl(2, "$list.empty();");
//												writerPageJs.tl(2, "$.each(data['list'], function(o) {");
//												writerPageJs.tl(3, "var $i = $('<i>').attr('class', 'far fa-something ');");
//												writerPageJs.tl(3, "var $span = $('<span>').attr('class', '').text();");
//												writerPageJs.tl(3, "var $a = $('<a>').attr('href', '...');");
//												writerPageJs.tl(3, "$a.append($i);");
//												writerPageJs.tl(3, "$a.append($span);");
//												writerPageJs.tl(3, "var $input = $('<input>').attr('class', 'w3-check ').attr('onchange', '", entityAttributeOperationIdPATCH, "($(this).parent()); ').attr('onclick', '", str_enleverLueur(languageName), "($(this)); ').attr('type', 'checkbox').attr('checked', 'checked').attr('name', '...').attr('value', '...');");
//												writerPageJs.tl(3, "var $li = $('<li>');");
//												writerPageJs.tl(3, "$li.append($input);");
//												writerPageJs.tl(3, "$li.append($a);");
//												writerPageJs.tl(3, "$list.append($li);");
//												writerPageJs.tl(2, "});");
												writerPageJs.tl(1, "};");
												writerPageJs.tl(1, "error = function( jqXhr, textStatus, errorThrown ) {};");
												writerPageJs.tl(1, classApiOperationIdMethod, "($", str_formFilters(languageName), ", success, error);");
												writerPageJs.tl(0, "}");
											}
											else if(entiteAttribuer) {
												writerPageJs.l();
												writerPageJs.tl(0, "function ", str_searchr(languageName), classSimpleName, entityVarCapitalized, "($", str_formFilters(languageName), ", $list) {");
												writerPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
												writerPageJs.tl(2, "$list.empty();");
												writerPageJs.tl(2, "$.each(data['list'], function(i, o) {");
												writerPageJs.tl(3, "var $i = $('<i>').attr('class', 'far fa-something ');");
												writerPageJs.t(3, "var $span = $('<span>').attr('class', '').text(");
												if(entiteAttribuerVarTitre != null)
													writerPageJs.s("o['", entiteAttribuerVarTitre, "']");
												writerPageJs.l(");");

												if(entiteAttribuerVarUrl != null)
													writerPageJs.tl(3, "var $a = $('<a>').attr('href', o['", entiteAttribuerVarUrl, "']);");
												else
													writerPageJs.tl(3, "var $a = $('<span>');");

												writerPageJs.tl(3, "$a.append($i);");
												writerPageJs.tl(3, "$a.append($span);");
												writerPageJs.tl(3, "var pk = parseInt($('#", classSimpleName, "Form :input[name=\"pk\"]').val());");
												writerPageJs.tl(3, "var val = o['", entiteAttribuerVar, "'];");
												writerPageJs.tl(3, "var checked = Array.isArray(val) ? val.includes(pk) : val == pk;");
												writerPageJs.tl(3, "var $input = $('<input>');");
												writerPageJs.tl(3, "$input.attr('class', 'w3-check ');");

												if("array".equals(entityJsonType)) {
													writerPageJs.t(4, "$input.attr('onchange', \"", entityOperationIdPATCH, "Vals({ 'fq': 'pk:\" + pk + \"' }, { [($(this).prop('checked') ? 'add' : 'remove') + '", entityVarCapitalized, "']: \" + o['pk'] + \" }");
												}
												else {
													writerPageJs.t(3, "$input.attr('onchange', \"", entityOperationIdPATCH, "Vals({ 'fq': 'pk:\" + pk + \"' }, { '", "set", entityVarCapitalized, "': ($(this).prop('checked') ? \" + o['pk'] + \" : null ) }");
												}

												if("array".equals(entityAttributeJsonType)) {
													writerPageJs.s(", function() { ", entityAttributeOperationIdPATCH, "Vals({ 'fq': 'pk:\" + o['pk'] + \"' }, {}, function() {}, function() {}); }");
												}
												else {
													writerPageJs.s(", function() { ", entityAttributeOperationIdPATCH, "Vals({ 'fq': 'pk:\" + o['pk'] + \"' }, {}, function() {}, function() {}); }");
												}
												writerPageJs.l(", function() {} ); \");");

												writerPageJs.tl(3, "$input.attr('onclick', '", str_enleverLueur(languageName), "($(this)); ');");
												writerPageJs.tl(3, "$input.attr('type', 'checkbox');");
												writerPageJs.tl(3, "if(checked)");
												writerPageJs.tl(4, "$input.attr('checked', 'checked');");
												writerPageJs.tl(3, "var $li = $('<li>');");
												writerPageJs.tl(3, "$li.append($input);");
												writerPageJs.tl(3, "$li.append($a);");
												writerPageJs.tl(3, "$list.append($li);");
												writerPageJs.tl(2, "});");
												writerPageJs.tl(1, "};");
												writerPageJs.tl(1, "error = function( jqXhr, textStatus, errorThrown ) {};");
												writerPageJs.tl(1, entityAttributeOperationIdSearch, "($", str_formFilters(languageName), ", success, error);");
												writerPageJs.tl(0, "}");
											}
										}
										solrSearch.setStart(i.intValue() + searchRows);
										searchResponse = solrClientComputate.query(solrSearch);
										searchList = searchResponse.getResults();
									}
								}
							}
						}
					}
					tl(2, "l(\"$(document).ready(function() {\");");
					s(wJsInit);
					tl(2, "l(\"});\");");
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
					tl(1, "public void htmlForm", str_Search(languageName), classSimpleName, "(", classSimpleName, " o) {");
					s(wFormSearch);
					tl(1, "}");
				}
				l();
				tl(1, "@Override public void htmlBody", classGenPageSimpleName, "() {");
				if(classPageSimple) {
					l();
					tl(2, "if(StringUtils.isNotBlank(pageH1)) {");
					t(3).be("h1").dfl();
					tl(4, "if(", str_contextIconCssClasses(languageName), " != null)");
					tl(5, "e(\"i\").a(\"class\", ", str_contextIconCssClasses(languageName), " + \" site-menu-icon \").f().g(\"i\");");
		
					if(classEntityVars != null && classEntityVars.contains("pageH1"))
						t(4).e("span").da("class", " ").df().s(".sx(pageH1)").dgl("span");
					else
						t(4).e("span").da("class", " ").df().dsxq(contextAName).dgl("span");
		
					t(3).bgl("h1");
					tl(2, "}");
		
					if(classEntityVars != null && classEntityVars.contains("pageH2")) {
						tl(2, "if(StringUtils.isNotBlank(pageH1)) {");
						t(3).be("h2").dfl();
						t(4).e("span").da("class", " ").df().s(".sx(pageH2)").dgl("span");
						t(3).bgl("h2");
						tl(2, "}");
					}
		
					if(classEntityVars != null && classEntityVars.contains("pageH3")) {
						tl(2, "if(StringUtils.isNotBlank(pageH3)) {");
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
					tl(2, "OperationRequest ", str_operationRequest(languageName), " = ", str_requeteSite(languageName), "_.get", str_OperationRequest(languageName), "();");
					tl(2, "JsonObject params = ", str_operationRequest(languageName), ".getParams();");
					tl(2, "if(", str_list(languageName), classSimpleName, " == null || ", str_list(languageName), classSimpleName, ".size() == 0) {");
//					t(3).l("// contextNoNameFound : ", contextNoNameFound);
					l();
					t(3).be("h1").dfl();
					tl(4, "if(", str_contextIconCssClasses(languageName), " != null)");
					tl(5, "e(\"i\").a(\"class\", ", str_contextIconCssClasses(languageName), " + \" site-menu-icon \").f().g(\"i\");");
					t(4).e("span").da("class", " ").df().dsxq(contextNoNameFound).dgl("span");
					t(3).bgl("h1");
//					tl(2, "} else if(", str_list(languageName), classSimpleName, " != null && ", str_list(languageName), classSimpleName, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\") && params.getJsonObject(\"query\").getJsonArray(\"fq\") == null) {");
					tl(2, "} else if(", str_list(languageName), classSimpleName, " != null && ", str_list(languageName), classSimpleName, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\")) {");
//					t(3).l("// contextAName : ", contextAName);
					tl(3, "if(pageH1 != null) {");
					t(4).be("h1").dfl();
					tl(5, "if(", str_contextIconCssClasses(languageName), " != null)");
					tl(6, "e(\"i\").a(\"class\", ", str_contextIconCssClasses(languageName), " + \" site-menu-icon \").f().g(\"i\");");
		
					if(classEntityVars != null && classEntityVars.contains("pageH1"))
						t(5).e("span").da("class", " ").df().s(".sx(pageH1)").dgl("span");
					else
						t(5).e("span").da("class", " ").df().dsxq(contextAName).dgl("span");
		
					t(4).bgl("h1");
					tl(4, classSimpleName, " o = ", str_list(languageName), classSimpleName, ".get(0);");
					tl(4, str_requeteSite(languageName), "_.set", str_Requete(languageName), "Pk(o.getPk());");

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
//					t(3).l("// contextNamePlural : multiple ", contextNamePlural);
					l();
					t(3).be("h1").dfl();
					tl(4, "if(", str_contextIconCssClasses(languageName), " != null)");
					tl(5, "e(\"i\").a(\"class\", ", str_contextIconCssClasses(languageName), " + \" site-menu-icon \").f().g(\"i\");");
					t(4).e("span").da("class", " ").df().dsxq(contextNamePlural).dgl("span");
					t(3).bgl("h1");
					t(3).be("table").da("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").dfl();
					t(4).be("thead").dfl();
					t(5).be("tr").dfl();
					s(wTh);
					t(5).bgl("tr");
					t(4).bgl("thead");
					t(4).be("tbody").dfl();
					tl(5, "Map<String, Map<String, List<String>>> highlighting = ", str_list(languageName), classSimpleName, ".getQueryResponse().getHighlighting();");
					tl(5, "for(int i = 0; i < ", str_list(languageName), classSimpleName, ".size(); i++) {");
					tl(6, classSimpleName, " o = ", str_list(languageName), classSimpleName, ".getList().get(i);");
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
											.da("onsubmit", "event.preventDefault(); " + str_searchr(languageName) + "($('#" + str_search(languageName) + entityVarCapitalized + "')); return false; ")
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

										t(6).dal("class", str_search(languageName), entityVarCapitalized, " w3-input w3-border w3-bar-item ");
										t(6).dal("name", entityVar);
										t(6).da("id", str_search(languageName), entityVarCapitalized).l(";");
										tl(5, str_operationRequest(languageName), ".getParams().getJsonObject(\"query\").forEach(param", str_Requete(languageName), " -> {");
										tl(6, "String entityVar = null;");
										tl(6, "String ", str_values(languageName), str_Indexe(languageName), " = null;");
										tl(6, "String param", str_Nom(languageName), " = param", str_Requete(languageName), ".getKey();");
										tl(6, "Object param", str_ValeursObjet(languageName), " = param", str_Requete(languageName), ".getValue();");
										tl(6, "JsonArray param", str_Objets(languageName), " = param", str_ValeursObjet(languageName), " instanceof JsonArray ? (JsonArray)param", str_ValeursObjet(languageName), " : new JsonArray().add(param", str_ValeursObjet(languageName), ");");
										l();
										tl(6, "try {");
										tl(7, "for(Object param", str_Objet(languageName), " : param", str_Objets(languageName), ") {");
										tl(8, "switch(paramName) {");
								
										tl(9, "case \"q\":");
										tl(10, "entityVar = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(languageName), ", \":\"));");
										tl(10, str_value(languageName), str_Indexe(languageName), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(languageName), ", \":\")), \"UTF-8\");");

										tl(10, "if(\"", entityVar, "\".equals(entityVar))");
										tl(11, "a(\"value\", URLDecoder.decode(", str_value(languageName), str_Indexe(languageName), ", \"UTF-8\"));");
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
//					tl(2, "if(", str_list(languageName), classSimpleName, " != null && ", str_list(languageName), classSimpleName, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\") && params.getJsonObject(\"query\").getJsonArray(\"fq\") == null) {");
					tl(2, "if(", str_list(languageName), classSimpleName, " != null && ", str_list(languageName), classSimpleName, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\")) {");
					t(3).l(classSimpleName, " o = ", str_list(languageName), classSimpleName, ".first();");
					l();
					t(3).be("div").da("class", "").dfl();
					if(classVarPrimaryKey != null) {
						l();
						tl(4, "if(o.get", StringUtils.capitalize(classVarPrimaryKey), "() != null) {");
						t(5).be("form").da("action", classApiUri).da("id", classSimpleName, "Form").da("style", "display: inline-block; ").da("onsubmit", "event.preventDefault(); return false; ").dfl();
						t(6).e("input").l();
						t(6).dal("name", classVarPrimaryKey);
						t(6).dal("class", str_value(languageName), StringUtils.capitalize(classVarPrimaryKey));
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
					tl(2, "htmlBodyForms", classGenPageSimpleName, "();");
					tl(1, "}");
					l();
					tl(1, "public void htmlBodyForms", classGenPageSimpleName, "() {");
					if(!classPageSimple) {
						t(2).e("div").dfl();
						l();
						for(String classApiMethod : classApiMethods) {
							String classApiOperationIdMethod = (String)classDoc.get("classeApiOperationId" + classApiMethod + "_" + languageName + "_stored_string");
							String classApiUriMethod = (String)classDoc.get("classApiUri" + classApiMethod + "_" + languageName + "_stored_string");
							String classApiMediaTypeMethod = (String)classDoc.get("classeApiTypeMedia200" + classApiMethod + "_" + languageName + "_stored_string");
							String classApiMethodMethod = (String)classDoc.get("classApiMethod" + classApiMethod + "_" + languageName + "_stored_string");
			
							if("application/json".equals(classApiMediaTypeMethod) && !"GET".equals(classApiMethodMethod)) {
								Integer tab = classApiMethodMethod.contains("PATCH") || classApiMethodMethod.contains("DELETE") || classApiMethodMethod.contains("POST") ? 0 : 1;
								String methodTitleFiltres = null;
								String methodTitleValeurs = null;
			
								if("POST".equals(classApiMethodMethod)) {
									methodTitleValeurs = str_Creer_(languageName) + contextAName;
								}
								else if("PUT".equals(classApiMethodMethod)) {
									methodTitleFiltres = str_Searchr_des_(languageName) + contextAName;
									methodTitleValeurs = str_Remplacer_(languageName) + contextTheName;
								}
								else if("PATCH".equals(classApiMethodMethod)) {
									methodTitleFiltres = str_Searchr_des_(languageName) + contextAName;
									methodTitleValeurs = str_Modifier_des_(languageName) + contextNamePlural;
								}
								else if("DELETE".equals(classApiMethodMethod)) {
									methodTitleFiltres = str_Searchr_des_(languageName) + contextAName;
									methodTitleValeurs = str_Supprimer_des_(languageName) + contextNamePlural;
								}
			
			
								l();
								if(tab > 0)
									tl(2, "if(", str_list(languageName), classSimpleName, " != null && ", str_list(languageName), classSimpleName, ".size() == 1) {");
								t(2 + tab).e("button").l();
								t(3 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contextColor, " ");
								t(3 + tab).dal("onclick", "$('#", classApiOperationIdMethod, str_Modale(languageName), "').show(); ");
								t(3 + tab).df().dsxq(methodTitleValeurs).l();
								t(2 + tab).dgl("button");
								{ t(2 + tab).be("div").da("id", classApiOperationIdMethod, str_Modale(languageName)).da("class", "w3-modal ").dfl();
									{ t(3 + tab).be("div").da("class", "w3-modal-content w3-card-4 ").dfl();
										{ t(4 + tab).be("header").da("class", "w3-container w3-", contextColor, " ").dfl();
											t(5 + tab).e("span").da("class", "w3-button w3-display-topright ").da("onclick", "$('#", classApiOperationIdMethod, str_Modale(languageName), "').hide(); ").df().dsxq("×").dgl("span");
											t(5 + tab).e("h2").da("class", "").df().dsxq(methodTitleValeurs).dgl("h2");
										} t(4 + tab).bgl("header");
			
										{ t(4 + tab).be("div").da("class", "w3-container ").dfl();
											tl(5+ tab, classSimpleName, " o = new ", classSimpleName, "();");
											if("PATCH".equals(classApiMethodMethod)) {
												l();
												t(5 + tab).l("// ", str_FormFilters(languageName), " ", classApiMethodMethod);
												{ t(5 + tab).be("form").da("action", classApiUri).da("id", classApiOperationIdMethod, str_FormFilters(languageName)).da("onsubmit", "event.preventDefault(); return false; ").dfl();
												tl(6 + tab, "htmlForm", str_Search(languageName), classSimpleName, "(o);");
												} t(5 + tab).bgl("form");
												t(5 + tab).e("button").l();
												t(6 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contextColor, " ");
				
												tl(6 + tab, ".a(\"onclick\", \"", str_search(languageName), classSimpleName, "($('#", classApiOperationIdMethod, str_FormFilters(languageName), "')); \")");
				
												t(6 + tab).df().dsxq(methodTitleFiltres).l();
												t(5 + tab).dgl("button");
												l();
												
												l();
												t(5 + tab).l("// ", str_FormulaireValeurs(classPageLanguageName), " ", classApiMethodMethod);
												{ t(5 + tab).be("form").da("action", classApiUri).da("id", classApiOperationIdMethod, str_FormulaireValeurs(classPageLanguageName)).da("onsubmit", "event.preventDefault(); return false; ").dfl();
			
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
													tl(6 + tab, ".a(\"onclick\", \"", classApiOperationIdMethod, "($('#", classApiOperationIdMethod, str_FormFilters(languageName), "'), $('#", classApiOperationIdMethod, str_FormulaireValeurs(classPageLanguageName), "')); \")");
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
												{ t(5 + tab).be("form").da("action", classApiUri).da("id", classApiOperationIdMethod, "Form").da("onsubmit", "event.preventDefault(); return false; ").dfl();
			
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
													tl(6 + tab, ".a(\"onclick\", \"", classApiOperationIdMethod, "($('#", classApiOperationIdMethod, str_FormFilters(languageName), "'), $('#", classApiOperationIdMethod, str_FormulaireValeurs(classPageLanguageName), "')); \")");
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
				if(classMethodVars.contains("htmlBody" + str_Short(languageName))) {
					tl(1, "@Override public void htmlBodyShort", classGenPageSimpleName, "() {");
					l();
					tl(2, StringUtils.uncapitalize(classSimpleName), ".htmlBody" + str_Short(languageName) + "();");
					tl(1, "}");
				}
	
				tl(0, "}");
	
				wTh.flushClose();

				writerPageGenClass.flushClose();
				System.out.println(str_Ecrire(languageName) + ": " + classPagePathGen); 
				if(writerPageClass != null) {
					writerPageClass.flushClose();
					System.out.println(str_Ecrire(languageName) + ": " + classPagePath); 
				}
				writerPageCss.flushClose();
				System.out.println(str_Ecrire(languageName) + ": " + classPagePathCss); 
				writerPageJs.flushClose();
				System.out.println(str_Ecrire(languageName) + ": " + classPagePathJs); 

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
