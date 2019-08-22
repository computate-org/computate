package org.computate.frFR.java;

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
 * NomCanonique.enUS: org.computate.enUS.java.WritePageClass
 * 
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */  
public class EcrirePageClasse extends EcrireApiClasse {

	/**
	 * Var.enUS: classPageSimpleName
	 */
	protected String classePageNomSimple;

	/**
	 * Var.enUS: classPageSuperSimpleName
	 */
	protected String classePageSuperNomSimple;

	/**
	 * Var.enUS: classGenPageSimpleName
	 */
	protected String classeGenPageNomSimple;

	/**
	 * Var.enUS: classAttributeSimpleNamePages
	 */
	protected List<String> classeAttribuerNomSimplePages;

	/**
	 * Var.enUS: pageCodeClassStart
	 * Param1.var.enUS: languageName
	 */
	public void pageCodeClasseDebut(String langueNom) throws Exception {
	}

	/**
	 * Var.enUS: pageCodeClassEnd
	 * Param1.var.enUS: languageName
	 * r: Ecrire: 
	 * r.enUS: Write: 
	 * r: auteurGenPageClasse
	 * r.enUS: writerGenPageClass
	 * r: auteurPageClasse
	 * r.enUS: writerPageClass
	 * r: auteurPageCss
	 * r.enUS: writerPageCss
	 * r: auteurPageJs
	 * r.enUS: writerPageJs
	 */
	public void pageCodeClasseFin(String langueNom) throws Exception {
	}

	/**
	 * Var.enUS: writeFormEntity
	 * Param2.var.enUS: classApiMethodMethod
	 * r: resultat
	 * r.enUS: result
	 * 
	 * r: classeApiMethodeMethode
	 * r.enUS: classApiMethodMethod
	 * r: rechercheLigneActuelRecherche
	 * r.enUS: searchRowActualSearch
	 * r: entiteDocumentSolr
	 * r.enUS: entitySolrDocument
	 * r: entiteHtmlLigne
	 * r.enUS: entityHtmlRow
	 * r: entiteHtml
	 * r.enUS: entityHtml
	 * r: rechercheLigneRecherche
	 * r.enUS: searchRowSearch
	 * r: rechercheLigneActuel
	 * r.enUS: searchRowActual
	 * r: entiteVarCapitalise
	 * r.enUS: entityVarCapitalized
	 * r: entiteNomAffichage
	 * r.enUS: entityDisplayName
	 * r: entiteDescription
	 * r.enUS: entityDescription
	 * r: enleverLueur
	 * r.enUS: removeGlow
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: entiteNomSimpleComplet
	 * r.enUS: entitySimpleNameComplete
	 * r: entiteNomSimple
	 * r.enUS: entitySimpleName
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: entiteMultiligne
	 * r.enUS: entityMultiline
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * 
	 * r: entiteDefinir
	 * r.enUS: entityDefine
	 * r: entiteAttribuerNomCanonique
	 * r.enUS: entityAttributeCanonicalName
	 * r: entiteAttribuerNomSimple
	 * r.enUS: entityAttributeSimpleName
	 * r: entiteAttribuerVarSuggere
	 * r.enUS: entityAttributeVarSuggest
	 * r: entiteAttribuerVar
	 * r.enUS: entityAttributeVar
	 * r: entiteAttribuer
	 * r.enUS: entityAttribute
	 * r: rechercheLigne
	 * r.enUS: searchRow
	 * r: Recherche
	 * r.enUS: Search
	 * r: valeur
	 * r.enUS: value
	 * r: DD-MM-YYYY
	 * r.enUS: MM/DD/YYYY
	 * r: dd-MM-yyyy
	 * r.enUS: MM/dd/yyyy
	 * r: "suggere"
	 * r.enUS: "suggest"
	 */
	public Boolean ecrireFormEntite(ToutEcrivain wForm, String classeApiMethodeMethode) {
		int tIndex = 0;
		Boolean resultat = false;

		if(entiteHtml) {
			if("Recherche".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelRecherche = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLigneRecherche != rechercheLigneActuelRecherche) {
					if(rechercheLigneRecherche != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLigneRecherche = rechercheLigneActuelRecherche;
					resultat = true;
				}
			}
			else if("POST".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPOST = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePOST != rechercheLigneActuelPOST) {
					if(rechercheLignePOST != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePOST = rechercheLigneActuelPOST;
					resultat = true;
				}
			}
			else if("PATCH".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPATCH = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePATCH != rechercheLigneActuelPATCH) {
					if(rechercheLignePATCH != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePATCH = rechercheLigneActuelPATCH;
					resultat = true;
				}
			}
			else if("Page".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPage = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePage != rechercheLigneActuelPage) {
					if(rechercheLignePage != -1)
						wForm.t(2).bgl("div");
					wForm.t(2).be("div").da("class", "w3-cell-row ").dfl();
					rechercheLignePage = rechercheLigneActuelPage;
					resultat = true;
				}
			}

			wForm.t(3).be("div").da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
			if(entiteModifier && (entiteDefinir || entiteAttribuer)) {

				if("Page".equals(classeApiMethodeMethode)) {
					tIndex = 1;
				}

				wForm.t(tIndex + 3).be("div").da("class", "w3-padding ").dfl();
				wForm.t(tIndex + 4).be("form").da("action", classeApiUri).da("id", "form", classeNomSimple, entiteVarCapitalise).da("style", "display: inline-block; ").da("onsubmit", "event.preventDefault(); return false; ").dfl();
				wForm.t(tIndex + 5).e("input").l();
				wForm.t(tIndex + 6).dal("type", "hidden");
				wForm.t(tIndex + 6).dal("name", str_valeur(langueNom), StringUtils.capitalize(entiteAttribuerVar));
				wForm.t(tIndex + 6).dal("class", str_valeur(langueNom), StringUtils.capitalize(entiteAttribuerVar), " ");
				wForm.t(tIndex + 6).l(".a(\"value\", ", str_requeteSite(langueNom), "_.get", str_Requete(langueNom), "Pk())");
				wForm.t(tIndex + 6).dfgl();
				wForm.t(tIndex + 4).bgl("form");
				wForm.t(tIndex + 4).be("form").da("action", classeApiUri).da("id", str_suggere(langueNom), classeNomSimple, entiteVarCapitalise).da("style", "display: inline-block; ").da("onsubmit", "event.preventDefault(); return false; ").dfl();
				wForm.t(tIndex + 5).be("div").da("class", "w3-card ").dfl();

				if(entiteAttribuer) {
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classeApiMethodeMethode, "_", entiteVar).da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.l();
	
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "text");
	
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 9).dal("placeholder", entiteNomAffichage);
					}
					if(entiteDescription != null) {
						wForm.t(tIndex + 9).dal("title", entiteDescription);
					}
	
					wForm.t(tIndex + 9).dal("class", str_valeur(langueNom), StringUtils.capitalize(entiteAttribuerVarSuggere), " ", "suggere", entiteVarCapitalise, " w3-input w3-border ");
					wForm.t(tIndex + 9).dal("name", "set", entiteVarCapitalise);
					wForm.t(tIndex + 9).dal("id", classeApiMethodeMethode, "_", entiteVar);
					wForm.t(tIndex + 9).dal("autocomplete", "off");
					wForm.t(tIndex + 9).dal("oninput", str_rechercher(langueNom), classeNomSimple, entiteVarCapitalise, "($('#' + ($(this).val() ? '", str_suggere(langueNom), "' : 'form') + '", classeNomSimple, entiteVarCapitalise, "'), $('#", "list", classeNomSimple, entiteVarCapitalise, "')); ");

					wForm.t(tIndex + 8).dfgl();
	
					wForm.l();
					wForm.t(tIndex + 7).bgl("div");
					wForm.t(tIndex + 6).bgl("div");
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell w3-left-align w3-cell-top ").dfl();
					wForm.t(tIndex + 8).be("ul").da("class", "w3-ul w3-hoverable ").da("id", "list", classeNomSimple, entiteVarCapitalise).dfl();
					wForm.t(tIndex + 8).bgl("ul");
					wForm.t(tIndex + 7).bgl("div");
				}
				else if("LocalDate".equals(entiteNomSimple)) {
					wForm.tl(tIndex + 6, entiteNomSimpleComplet, " val = o.get", entiteVarCapitalise, "();");
					l();
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classeApiMethodeMethode, "_", entiteVar).da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row  ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "text");
					wForm.t(tIndex + 9).dal("class", "w3-input w3-border datepicker ");
					wForm.t(tIndex + 9).dal("placeholder", str_DDDashMMDashYYYY(langueNom));
					wForm.t(tIndex + 9).dal("data-timeformat", str_DDDashMMDashYYYY(langueNom));
					wForm.t(tIndex + 9).dal("onclick", str_enleverLueur(langueNom), "($(this)); ");
					if(entiteDescription != null)
						wForm.t(tIndex + 9).dal("title", entiteDescription + " (", str_DDDashMMDashYYYY(langueNom), ")");
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
					if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 9).dal("class", "set", entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("name", "set", entiteVarCapitalise);
					}
					else {
						wForm.t(tIndex + 9).dal("class", str_valeur(langueNom), entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("name", entiteVar);
						wForm.t(tIndex + 9).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					if("Page".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 9).dal("onchange", "patch", classeNomSimple, "($('#", classeNomSimple, "Form'), $('#", entiteVar, "Form'), $(this)); ");
					}
					wForm.t(tIndex + 9).dal("type", "hidden");
					wForm.tl(tIndex + 9, ".a(\"value\", o.str", entiteVarCapitalise, "())");
					wForm.t(tIndex + 8).dfgl();
	
					if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 8).e("input").l();
						wForm.t(tIndex + 9).dal("class", "remove", entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("name", "remove", entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("type", "hidden");
						wForm.tl(tIndex + 9, ".a(\"value\", \"false\")");
						wForm.t(tIndex + 8).dfgl();
					}

					wForm.t(tIndex + 7).bgl("div");
				}
				else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
					wForm.tl(tIndex + 6, entiteNomSimpleComplet, " val = o.get", entiteVarCapitalise, "();");
					wForm.l();
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classeApiMethodeMethode, "_", entiteVar).da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "text");
					wForm.t(tIndex + 9).dal("class", "w3-input w3-border datepicker ");
					wForm.t(tIndex + 9).dal("placeholder", str_DDDashMMDashYYYY(langueNom));
					wForm.t(tIndex + 9).dal("data-timeformat", str_DDDashMMDashYYYY(langueNom));
					wForm.t(tIndex + 9).dal("onclick", str_enleverLueur(langueNom), "($(this)); ");
					if(entiteDescription != null)
						wForm.t(tIndex + 9).dal("title", entiteDescription + " (", str_DDDashMMDashYYYY(langueNom), ")");
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
	
					if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 9).dal("class", "set", entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("name", "set", entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					else {
						wForm.t(tIndex + 9).dal("class", str_valeur(langueNom), entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("name", entiteVar);
						wForm.t(tIndex + 9).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					if("Page".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 9).dal("onchange", "patch", classeNomSimple, "($('#", classeNomSimple, "Form'), $('#", entiteVar, "Form'), $(this)); ");
					}
	
					wForm.tl(tIndex + 9, ".a(\"value\", o.str", entiteVarCapitalise, "())");
					wForm.t(tIndex + 8).dfgl();
					wForm.t(tIndex + 9).bgl("div");
				}
				else if("LocalTime".equals(entiteNomSimple)) {
					wForm.tl(tIndex + 6, entiteNomSimpleComplet, " val = o.get", entiteVarCapitalise, "();");
					wForm.l();
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classeApiMethodeMethode, "_", entiteVar).da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "text");
					wForm.t(tIndex + 9).dal("class", "w3-input w3-border timepicker ");
					wForm.t(tIndex + 9).dal("placeholder", "HH:MM AM");
					wForm.t(tIndex + 9).dal("onclick", str_enleverLueur(langueNom), "($(this)); ");
					if(entiteDescription != null)
						wForm.t(tIndex + 9).da("title", entiteDescription + " (h'h'mm)");
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
	
					if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 9).dal("class", "set", entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("name", "set", entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					else {
						wForm.t(tIndex + 9).dal("class", str_valeur(langueNom), entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("name", entiteVar);
						wForm.t(tIndex + 9).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					if("Page".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 9).dal("onchange", "patch", classeNomSimple, "Val({ 'pk': $('#", classeNomSimple, "Form :input[name=\"pk\"]').val() }, 'set", entiteVarCapitalise, "', $(this).val(), $(this)); ");
					}
	
					wForm.tl(tIndex + 9, ".a(\"value\", val == null ? \"\" : o.str", entiteVarCapitalise, "())");
					wForm.t(tIndex + 8).dfgl();
					wForm.t(tIndex + 7).bgl("div");
				}
				else if("Boolean".equals(entiteNomSimple)) {
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "hidden");
					wForm.t(tIndex + 9).dal("name", entiteVar);
					wForm.t(tIndex + 9).dal("id", classeApiMethodeMethode, "_", entiteVar);
					wForm.t(tIndex + 9).dal("value", "false");
					wForm.t(tIndex + 8).dfgl();
					wForm.l();
					wForm.t(tIndex + 8).e("input").l();
					wForm.t(tIndex + 9).dal("type", "checkbox");
					wForm.t(tIndex + 9).dal("value", "true");
	
					if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 9).dal("class", "set", entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("name", "set", entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					else {
						wForm.t(tIndex + 9).dal("class", str_valeur(langueNom), entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("name", entiteVar);
						wForm.t(tIndex + 9).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					if("Page".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 9).dal("onchange", "patch", classeNomSimple, "Val({ 'pk': $('#", classeNomSimple, "Form :input[name=\"pk\"]').val() }, 'set", entiteVarCapitalise, "', $(this).val(), $(this)); ");
					}
					wForm.tl(tIndex + 9, ";");
	
					wForm.tl(tIndex + 9, "if(o.get", entiteVarCapitalise, "() != null && o.get", entiteVarCapitalise, "())");
					wForm.t(tIndex + 10).a("checked", "checked").l(";");
					wForm.t(tIndex + 8).fgl();
					wForm.l();
					wForm.t(tIndex + 7).bgl("div");
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classeApiMethodeMethode, "_", entiteVar).da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
				}
				else {
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
						wForm.t(tIndex + 7).e("label").da("for", classeApiMethodeMethode, "_", entiteVar).da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
						wForm.t(tIndex + 6).bgl("div");
					}
					wForm.t(tIndex + 6).be("div").da("class", "w3-cell-row w3-padding ").dfl();
					wForm.t(tIndex + 7).be("div").da("class", "w3-cell ").dfl();
					wForm.l();
	
					if(entiteMultiligne)
						wForm.t(tIndex + 8).e("textarea").l();
					else {
						wForm.t(tIndex + 8).e("input").l();
						wForm.t(tIndex + 9).dal("type", "text");
					}
	
					if(entiteNomAffichage != null) {
						wForm.t(tIndex + 9).dal("placeholder", entiteNomAffichage);
					}
					if(entiteDescription != null) {
						wForm.t(tIndex + 9).dal("title", entiteDescription);
					}
	
					if("Page".equals(classeApiMethodeMethode) || "PATCH".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 9).dal("class", "set", entiteVarCapitalise, " w3-input w3-border ");
						wForm.t(tIndex + 9).dal("name", "set", entiteVarCapitalise);
						wForm.t(tIndex + 9).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					else {
						wForm.t(tIndex + 9).dal("class", str_valeur(langueNom), entiteVarCapitalise, " w3-input w3-border ");
						wForm.t(tIndex + 9).dal("name", entiteVar);
						wForm.t(tIndex + 9).dal("id", classeApiMethodeMethode, "_", entiteVar);
					}
					if("Page".equals(classeApiMethodeMethode)) {
						wForm.t(tIndex + 9).dal("onclick", str_enleverLueur(langueNom), "($(this)); ");
						wForm.t(tIndex + 9).dal("onchange", "patch", classeNomSimple, "Val({ 'pk': $('#", classeNomSimple, "Form :input[name=\"pk\"]').val() }, 'set", entiteVarCapitalise, "', $(this).val(), $(this)); ");
					}
	
					if(entiteMultiligne) {
						wForm.t(tIndex + 8).df();
						wForm.s(".sx(o.str", entiteVarCapitalise, "())");
					}
					else {
						wForm.tl(tIndex + 9, ".a(\"value\", o.str", entiteVarCapitalise, "())");
					}
	
					if(entiteMultiligne)
						wForm.dgl("textarea");
					else
						wForm.t(tIndex + 8).dfgl();
	
					wForm.l();
					wForm.t(tIndex + 7).bgl("div");
				}
				if(!entiteAttribuer && entiteModifier && "Page".equals(classeApiMethodeMethode)) {

					wForm.t(tIndex + 7).be("div").da("class", "w3-cell w3-left-align w3-cell-top ").dfl();
					wForm.t(tIndex + 8).be("button").l();
					wForm.t(tIndex + 9).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-", contexteCouleur, " ");
					wForm.t(tIndex + 8).dal("onclick", str_enleverLueur(langueNom), "($('#", classeApiMethodeMethode, "_", entiteVar, "')); $('#", classeApiMethodeMethode, "_", entiteVar, "').val(null); patch", classeNomSimple, "Val({ 'pk': $('#", classeNomSimple, "Form :input[name=\"pk\"]').val() }, 'set", entiteVarCapitalise, "', null, $('#", classeApiMethodeMethode, "_", entiteVar, "')); ");
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
				if("Page".equals(classeApiMethodeMethode)) {
					tIndex = 1;
				}

				wForm.t(tIndex + 3).be("div").da("class", "w3-padding ").dfl();
				wForm.t(tIndex + 4).be("div").da("class", "w3-card ").dfl();

				if(entiteNomAffichage != null) {
					wForm.t(tIndex + 5).be("div").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
					wForm.t(tIndex + 6).e("label").da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
					wForm.t(tIndex + 5).bgl("div");
				}
				wForm.t(tIndex + 5).be("div").da("class", "w3-cell-row  ").dfl();
				wForm.t(tIndex + 6).be("div").da("class", "w3-cell ").dfl();
				wForm.t(tIndex + 7).be("div").da("class", "w3-rest ").dfl();
				wForm.t(tIndex + 8).e("span").df().s(".sx(o.str", entiteVarCapitalise, "())").dgl("span");
				wForm.t(tIndex + 7).bgl("div");
				wForm.t(tIndex + 6).bgl("div");
				wForm.t(tIndex + 5).bgl("div");
				wForm.t(tIndex + 4).bgl("div");
				wForm.t(tIndex + 3).bgl("div");
			}

//			if().da("class", objets).da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
			wForm.t(3).bgl("div");
		}
		return resultat;
	}

	/**
	 * Var.enUS: pageCodeClass
	 * Param1.var.enUS: languageName
	 * r: Ecrire: 
	 * r.enUS: Write: 
	 * r: auteurGenPageClasse
	 * r.enUS: writerGenPageClass
	 * r: auteurPageClasse
	 * r.enUS: writerPageClass
	 * r: auteurPageCss
	 * r.enUS: writerPageCss
	 * r: auteurPageJs
	 * r.enUS: writerPageJs
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: classeImportationsGenPage
	 * r.enUS: classImportsGenPage
	 * r: classeImportation
	 * r.enUS: classImport
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: entiteVarCapitalise
	 * r.enUS: entityVarCapitalized
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: entiteHtmlLigne
	 * r.enUS: entityHtmlRow
	 * r: entiteHtmlColonne
	 * r.enUS: entityHtmlColumn
	 * r: entiteDescription
	 * r.enUS: entityDescription
	 * r: entiteNomAffichage
	 * r.enUS: entityDisplayName
	 * r: entiteMultiligne
	 * r.enUS: entityMultiline
	 * r: entiteDefinir
	 * r.enUS: entityDefine
	 * r: entiteClassesSuperEtMoiSansGen
	 * r.enUS: entitySuperClassesAndMeWithoutGen
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: contexteCouleur
	 * r.enUS: contextColor
	 * r: classePageNomEnsemble
	 * r.enUS: classPagePackageName
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: classePageCheminGen
	 * r.enUS: classPagePathGen
	 * r: classePageCheminCss
	 * r.enUS: classPagePathCss
	 * r: classePageCheminJs
	 * r.enUS: classPagePathJs
	 * r: classePageUriMethodeLangue
	 * r.enUS: classPageUriMethodLanguage
	 * r: classePageUriMethode
	 * r.enUS: classPageUriMethod
	 * r: classePageLangueNom
	 * r.enUS: classPageLanguageName
	 * r: classePageNomSimple
	 * r.enUS: classPageSimpleName
	 * r: classePageChemin
	 * r.enUS: classPagePath
	 * r: classePageMethode
	 * r.enUS: classPageMethod
	 * r: classePageSuperNomSimple
	 * r.enUS: classPageSuperSimpleName
	 * r: classeGenPageNomSimple
	 * r.enUS: classGenPageSimpleName
	 * r: classePageNomCanonique
	 * r.enUS: classPageCanonicalName
	 * r: contexteImageLargeur
	 * r.enUS: contextImageWidth
	 * r: contexteImageHauteur
	 * r.enUS: contextImageHeight
	 * r: contexteVideoId
	 * r.enUS: contextVideoId
	 * r: contexteAdjectifPluriel
	 * r.enUS: contextAdjectivePlural
	 * r: contexteAdjectifVar
	 * r.enUS: contextAdjectiveVar
	 * r: contexteNomAdjectifSingulier
	 * r.enUS: contextNameAdjectiveSingular
	 * r: contexteNomAdjectifPluriel
	 * r.enUS: contextNameAdjectivePlural
	 * r: contexteTousNom
	 * r.enUS: contextAllName
	 * r: contexteCeNom
	 * r.enUS: contextThisName
	 * r: contexteAdjectif
	 * r.enUS: contextAdjective
	 * r: classePageFichierGen
	 * r.enUS: classPageFileGen
	 * r: classePageFichierCss
	 * r.enUS: classPageFileCss
	 * r: classePageFichierJs
	 * r.enUS: classPageFileJs
	 * r: classePageFichier
	 * r.enUS: classPageFile
	 * r: auteurPageGenClasse
	 * r.enUS: writerPageGenClass
	 * r: classeAttribuerNomSimplePage
	 * r.enUS: classAttributeSimpleNamePage
	 * r: wEntites
	 * r.enUS: wEntities
	 * r: partEstEntite
	 * r.enUS: partIsEntity
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: entiteHtmlCellule
	 * r.enUS: entityHtmlCell
	 * r: rechercheReponse
	 * r.enUS: searchResponse
	 * r: rechercheListe
	 * r.enUS: searchList
	 * r: entiteDocumentSolr
	 * r.enUS: entitySolrDocument
	 * r: entiteNomSimpleGenerique
	 * r.enUS: entitySimpleNameGeneric
	 * r: entiteNomSimpleComplet
	 * r.enUS: entitySimpleNameComplete
	 * r: ecrireFormEntite
	 * r.enUS: writeFormEntity
	 * r: classePageSimple
	 * r.enUS: classPageSimple
	 * r: toutesLangues
	 * r.enUS: allLanguages
	 * r: pageLangueNom
	 * r.enUS: pageLanguageName
	 * r: contexteDescription
	 * r.enUS: contextDescription
	 * r: classeImage
	 * r.enUS: classImage
	 * r: pageImageLargeur
	 * r.enUS: pageImageWidth
	 * r: pageImageHauteur
	 * r.enUS: pageImageHeight
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: fqClassesSuperEtMoi
	 * r.enUS: fqSuperClassesAndMe
	 * r: classeApiOperationIdMethode
	 * r.enUS: classApiOperationIdMethod
	 * r: classeApiUriMethode
	 * r.enUS: classApiUriMethod
	 * r: classeApiTypeMediaMethode
	 * r.enUS: classApiMediaTypeMethod
	 * r: classeApiMethodeMethode
	 * r.enUS: classApiMethodMethod
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeMethodeVar
	 * r.enUS: classMethodVar
	 * r: entiteTexte
	 * r.enUS: entityText
	 * r: entiteSuggere
	 * r.enUS: entitySuggested
	 * r: wSuggere
	 * r.enUS: wSuggest
	 * r: entiteLangue
	 * r.enUS: entityLanguage
	 * r: contexteRechercherTousNomPar
	 * r.enUS: contextSearchAllNameBy
	 * r: contexteRechercherTousNom
	 * r.enUS: contextSearchAllName
	 * r: valeurIndexe
	 * r.enUS: valueIndexed
	 * r: paramNom
	 * r.enUS: paramName
	 * r: paramValeursObjet
	 * r.enUS: paramValuesObject
	 * r: paramObjet
	 * r.enUS: paramObject
	 * r: RegarderClasseBase
	 * r.enUS: WatchClassBase
	 * r: RegarderClasse
	 * r.enUS: WatchClass
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: appliNom
	 * r.enUS: appName
	 * r: regarderClasse
	 * r.enUS: watchClass
	 * 
	 * r: langueNom
	 * r.enUS: languageName
	 * r: entiteIndexe
	 * r.enUS: entityIndexed
	 * r: entiteStocke
	 * r.enUS: entityStored
	 * r: entiteDefinir
	 * r.enUS: entityDefine
	 * r: entiteCouverture
	 * r.enUS: entityWrap
	 * r: Couverture
	 * r.enUS: Wrap
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: entiteHtml
	 * r.enUS: entityHtml
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: contexteUnNom
	 * r.enUS: contextAName
	 * r: classeEntiteVars
	 * r.enUS: classEntityVars
	 * r: contexteIconeGroupe
	 * r.enUS: contextIconGroup
	 * r: contexteIconeNom
	 * r.enUS: contextIconName
	 * r: contexteCe
	 * r.enUS: contextThis
	 * r: contexteCeNom
	 * r.enUS: contextThisName
	 * r: contexteUn
	 * r.enUS: contextA
	 * r: contexteUnNom
	 * r.enUS: contextAName
	 * r: contexteLeNom
	 * r.enUS: contextTheName
	 * r: contexteNomSingulier
	 * r.enUS: contextNameSingular
	 * r: contexteNomPluriel
	 * r.enUS: contextNamePlural
	 * r: contexteNomActuel
	 * r.enUS: contextActualName
	 * r: contexteTous
	 * r.enUS: contextAll
	 * r: contexteTousNom
	 * r.enUS: contextAllName
	 * r: contexteLesNoms
	 * r.enUS: contextTheNames
	 * r: contexteAucunNomTrouve
	 * r.enUS: contextNoNameFound
	 * r: contexteNomVar
	 * r.enUS: contextNameVar
	 * r: contexteDeNom
	 * r.enUS: contextOfName
	 * r: contexteNom
	 * r.enUS: contextName
	 * r: UnNomAdjectif
	 * r.enUS: ANameAdjective
	 * r: LeNomAdjectif
	 * r.enUS: TheNameAdjective
	 * r: AdjectifAvant
	 * r.enUS: AdjectiveBefore
	 * r: NomAdjectifSingulier
	 * r.enUS: NameAdjectiveSingular
	 * r: NomAdjectifPluriel
	 * r.enUS: NameAdjectivePlural
	 * r: PlurielNomAdjectif
	 * r.enUS: PluralNameAdjective
	 * r: SingulierNomAdjectif
	 * r.enUS: SingularNameAdjective
	 * r: LeNomAdjectif
	 * r.enUS: TheAdjectiveName
	 * r: UnNomAdjectif
	 * r.enUS: AnAdjectiveName
	 * r: AdjectifVar
	 * r.enUS: AdjectiveVar
	 * r: Adjectif
	 * r.enUS: Adjective
	 * r: CONTEXTE_UnMasculinVoyelle
	 * r.enUS: CONTEXT_AMaleVowel
	 * r: CONTEXTE_UnFemininVoyelle
	 * r.enUS: CONTEXT_AFemaleVowel
	 * r: CONTEXTE_UnMasculinConsonne
	 * r.enUS: CONTEXT_AMaleConsonant
	 * r: CONTEXTE_UnFemininConsonne
	 * r.enUS: CONTEXT_AFemaleConsonant
	 * r: CONTEXTE_CetMasculinVoyelle
	 * r.enUS: CONTEXT_ThisMaleVowel
	 * r: CONTEXTE_CetteFemininVoyelle
	 * r.enUS: CONTEXT_ThisFemaleVowel
	 * r: CONTEXTE_CeMasculinConsonne
	 * r.enUS: CONTEXT_ThisMaleConsonant
	 * r: CONTEXTE_CetteFemininConsonne
	 * r.enUS: CONTEXT_ThisFemaleConsonant
	 * r: CONTEXTE_CesPluriel
	 * r.enUS: CONTEXT_ThesePlural
	 * r: CONTEXTE_LMasculinVoyelle
	 * r.enUS: CONTEXT_TheMaleVowel
	 * r: CONTEXTE_LFemininVoyelle
	 * r.enUS: CONTEXT_TheFemaleVowel
	 * r: CONTEXTE_LeMasculinConsonne
	 * r.enUS: CONTEXT_TheMaleConsonant
	 * r: CONTEXTE_LaFemininConsonne
	 * r.enUS: CONTEXT_TheFemaleConsonant
	 * r: CONTEXTE_LesPluriel
	 * r.enUS: CONTEXT_ThePlural
	 * r: CONTEXTE_ActuelMasculinAvant
	 * r.enUS: CONTEXT_CurrentMaleBefore
	 * r: CONTEXTE_ActuelleFemininAvant
	 * r.enUS: CONTEXT_CurrentFemaleBefore
	 * r: CONTEXTE_ActuelMasculinApres
	 * r.enUS: CONTEXT_CurrentMaleAfter
	 * r: CONTEXTE_ActuelleFemininApres
	 * r.enUS: CONTEXT_CurrentFemaleAfter
	 * r: CONTEXTE_TousMasculinPluriel
	 * r.enUS: CONTEXT_AllMalePlural
	 * r: CONTEXTE_ToutesFemininPluriel
	 * r.enUS: CONTEXT_AllFemalePlural
	 * r: CONTEXTE_AucunTrouveMasculinAvant
	 * r.enUS: CONTEXT_NoneFoundMaleBefore
	 * r: CONTEXTE_AucuneTrouveFemininAvant
	 * r.enUS: CONTEXT_NoneFemaleBefore
	 * r: CONTEXTE_AucunTrouveMasculinApres
	 * r.enUS: CONTEXT_NoneFoundMaleAfter
	 * r: CONTEXTE_AucuneTrouveFemininApres
	 * r.enUS: CONTEXT_NoneFemaleAfter
	 * r: CONTEXTE_DVoyelle
	 * r.enUS: CONTEXT_OfVowel
	 * r: CONTEXTE_DeConsonne
	 * r.enUS: CONTEXT_OfConsonant
	 * r: CONTEXTE_AdjectifPluriel
	 * r.enUS: CONTEXT_AdjectivePlural
	 * r: rechercheLigneActuel
	 * r.enUS: searchRowActual
	 * r: rechercheLigne
	 * r.enUS: searchRow
	 * r: entiteNomSimple
	 * r.enUS: entitySimpleName
	 * r: entiteNomSimpleGenerique
	 * r.enUS: entitySimpleNameGeneric
	 * r: entiteNomSimpleComplet
	 * r.enUS: entitySimpleNameComplete
	 * r: methodeTitre
	 * r.enUS: methodTitle
	 * r: DD-MM-YYYY
	 * r.enUS: MM-DD-YYYY
	 * r: dd/MM/yyyy
	 * r.enUS: MM/dd/yyyy
	 * r: h'h'mm
	 * r.enUS: h:mm a
	 * r: fr-FR
	 * r.enUS: en-US
	 * r: contexteIconeClassesCss
	 * r.enUS: contextIconCssClasses
	 * r: pageTitre
	 * r.enUS: pageTitle
	 * r: PageTitre
	 * r.enUS: PageTitle
	 * r: contexteH1
	 * r.enUS: contextH1
	 * r: contexteH2
	 * r.enUS: contextH2
	 * r: contexteH3
	 * r.enUS: contextH3
	 * r: contexteTitre
	 * r.enUS: contextTitle
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: formulaireFiltres
	 * r.enUS: formFilters
	 * r: formulaireValeurs
	 * r.enUS: formValues
	 * r: FormulaireFiltres
	 * r.enUS: FormFilters
	 * r: FormValeurs
	 * r.enUS: FormValues
	 * r: "Rechercher "
	 * r.enUS: "Search "
	 * r: operationRequete
	 * r.enUS: operationRequest
	 * r: OperationRequete
	 * r.enUS: OperationRequest
	 * r: entiteAttribuerOperationIdPATCH
	 * r.enUS: entityAttributeOperationIdPATCH
	 * r: entiteAttribuerOperationIdRecherche
	 * r.enUS: entityAttributeOperationIdSearch
	 * 
	 * r: liste
	 * r.enUS: list
	 * r: plusiers
	 * r.enUS: multiple
	 * r: Créer 
	 * r.enUS: Create 
	 * r: Modifier 
	 * r.enUS: Modify 
	 * r: Remplacer 
	 * r.enUS: Replace 
	 * r: Supprimer 
	 * r.enUS: Delete 
	 * r: valeur
	 * r.enUS: value
	 * r: filtre
	 * r.enUS: filter
	 * r: Recherche
	 * r.enUS: Search
	 * r: Entite
	 * r.enUS: Entity
	 * r: plusiers 
	 * r.enUS: multiple 
	 * r: resultat
	 * r.enUS: result
	 * r: methode
	 * r.enUS: method
	 * r: recherche
	 * r.enUS: search
	 * r: Court
	 * r.enUS: Short
	 */ 
	public void pageCodeClasse(String langueNom) throws Exception {
		for(String classePageMethode : classeApiMethodes) {

			String classePageCheminGen = (String)classeDoc.get("classePageCheminGen" + classePageMethode  + "_" + langueNom + "_stored_string");
			String classePageChemin = (String)classeDoc.get("classePageChemin" + classePageMethode  + "_" + langueNom + "_stored_string");
			String classePageCheminCss = (String)classeDoc.get("classePageCheminCss" + classePageMethode  + "_" + langueNom + "_stored_string");
			String classePageCheminJs = (String)classeDoc.get("classePageCheminJs" + classePageMethode  + "_" + langueNom + "_stored_string");
			String classePageUriMethode = (String)classeDoc.get("classeApiUri" + classePageMethode + "_" + langueNom + "_stored_string");
			String classePageLangueNom = (String)classeDoc.get("classePageLangueNom" + classePageMethode + "_" + langueNom + "_stored_string");

			classePageNomSimple = (String)classeDoc.get("classePageNomSimple" + classePageMethode  + "_" + langueNom + "_stored_string");
			classePageSuperNomSimple = (String)classeDoc.get("classePageSuperNomSimple" + classePageMethode  + "_" + langueNom + "_stored_string");
			classeGenPageNomSimple = (String)classeDoc.get("classeGenPageNomSimple" + classePageMethode  + "_" + langueNom + "_stored_string");
			String classePageNomCanonique = (String)classeDoc.get("classePageNomCanonique" + classePageMethode  + "_" + langueNom + "_stored_string");
			classeAttribuerNomSimplePages = (List<String>)classeDoc.get("classeAttribuerNomSimplePages_" + langueNom + "_stored_strings");
	
			if(classePageCheminGen != null && StringUtils.equals(classePageLangueNom, langueNom)) {

				contexteImageLargeur = (Integer)classeDoc.get("contexteImageLargeur" + "_" + langueNom + "_stored_int");
				contexteImageHauteur = (Integer)classeDoc.get("contexteImageHauteur" + "_" + langueNom + "_stored_int");
				contexteVideoId = (String)classeDoc.get("contexteVideoId" + "_" + langueNom + "_stored_string");
				contexteUnNom = (String)classeDoc.get("contexteUnNom" + "_" + langueNom + "_stored_string");
				contexteNomSingulier = (String)classeDoc.get("contexteNomSingulier" + "_" + langueNom + "_stored_string");
				contexteNomPluriel = (String)classeDoc.get("contexteNomPluriel" + "_" + langueNom + "_stored_string");
				contexteNomVar = (String)classeDoc.get("contexteNomVar" + "_" + langueNom + "_stored_string");
				contexteAdjectif = (String)classeDoc.get("contexteAdjectif" + "_" + langueNom + "_stored_string");
				contexteAdjectifPluriel = (String)classeDoc.get("contexteAdjectifPluriel" + "_" + langueNom + "_stored_string");
				contexteAdjectifVar = (String)classeDoc.get("contexteAdjectifVar" + "_" + langueNom + "_stored_string");
				contexteNomAdjectifSingulier = (String)classeDoc.get("contexteNomAdjectifSingulier" + "_" + langueNom + "_stored_string");
				contexteNomAdjectifPluriel = (String)classeDoc.get("contexteNomAdjectifPluriel" + "_" + langueNom + "_stored_string");
				contexteCe = (String)classeDoc.get("contexteCe" + "_" + langueNom + "_stored_string");
				contexteUn = (String)classeDoc.get("contexteUn" + "_" + langueNom + "_stored_string");
				contexteNomActuel = (String)classeDoc.get("contexteNomActuel" + "_" + langueNom + "_stored_string");
				contexteTousNom = (String)classeDoc.get("contexteTousNom" + "_" + langueNom + "_stored_string");
				contexteLesNoms = (String)classeDoc.get("contexteLesNoms" + "_" + langueNom + "_stored_string");
				contexteTitre = (String)classeDoc.get("contexteTitre" + "_" + langueNom + "_stored_string");
				contexteH1 = (String)classeDoc.get("contexteH1" + "_" + langueNom + "_stored_string");
				contexteH2 = (String)classeDoc.get("contexteH2" + "_" + langueNom + "_stored_string");
				contexteH3 = (String)classeDoc.get("contexteH3" + "_" + langueNom + "_stored_string");
				contexteAucunNomTrouve = (String)classeDoc.get("contexteAucunNomTrouve" + "_" + langueNom + "_stored_string");
				contexteUnNomAdjectif = (String)classeDoc.get("contexteUnNomAdjectif" + "_" + langueNom + "_stored_string");
				contexteCeNom = (String)classeDoc.get("contexteCeNom" + "_" + langueNom + "_stored_string");
				contexteLeNom = (String)classeDoc.get("contexteLeNom" + "_" + langueNom + "_stored_string");
				contexteDeNom = (String)classeDoc.get("contexteDeNom" + "_" + langueNom + "_stored_string");
			
				File classePageFichierGen = null;
				File classePageFichier = null;
				File classePageFichierCss = null;
				File classePageFichierJs = null;

				if(classePageCheminGen != null)
					classePageFichierGen = new File(classePageCheminGen);
				if(classePageChemin != null)
					classePageFichier = new File(classePageChemin);
				if(classePageCheminCss != null)
					classePageFichierCss = new File(classePageCheminCss);
				if(classePageCheminJs != null)
					classePageFichierJs = new File(classePageCheminJs);
			
				ToutEcrivain auteurPageGenClasse = null;
				ToutEcrivain auteurPageClasse = null;
				ToutEcrivain auteurPageCss = null;
				ToutEcrivain auteurPageJs = null;

				if(classePageFichierGen != null)
					auteurPageGenClasse = ToutEcrivain.create(classePageFichierGen);
				if(classePageFichier != null && !classePageFichier.exists())
					auteurPageClasse = ToutEcrivain.create(classePageFichier);
				if(classePageFichierCss != null) {
					classePageFichierCss.getParentFile().mkdirs();
					auteurPageCss = ToutEcrivain.create(classePageFichierCss);
				}
				if(classePageFichierJs != null) {
					classePageFichierJs.getParentFile().mkdirs();
					auteurPageJs = ToutEcrivain.create(classePageFichierJs);
				}

				Boolean pageH1 = false;
				Boolean pageH2 = false;
				Boolean pageH3 = false;
				Boolean pageTitre = false;
	
				ToutEcrivain wRecherche = ToutEcrivain.create();
				ToutEcrivain wPOST = ToutEcrivain.create();
				ToutEcrivain wPATCH = ToutEcrivain.create();
				ToutEcrivain wSuggere = ToutEcrivain.create();
				ToutEcrivain wTh = ToutEcrivain.create();
				ToutEcrivain wTd = ToutEcrivain.create();
				ToutEcrivain wFormRecherche = ToutEcrivain.create();
				ToutEcrivain wFormPOST = ToutEcrivain.create();
				ToutEcrivain wFormPage = ToutEcrivain.create();
				ToutEcrivain wFormPATCH = ToutEcrivain.create();
				ToutEcrivain wEntites = ToutEcrivain.create();
				ToutEcrivain wJsInit = ToutEcrivain.create();
	
				o = auteurPageGenClasse;
				{
					SolrQuery rechercheSolr = new SolrQuery();   
					rechercheSolr.setQuery("*:*");
					rechercheSolr.setRows(1000000);
					String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
					rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
					rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
//					rechercheSolr.addFilterQuery("entiteHtmlLigne_indexed_int:[* TO *]");
					rechercheSolr.addSort("entiteHtmlLigne_indexed_int", ORDER.asc);
					rechercheSolr.addSort("entiteHtmlCellule_indexed_int", ORDER.asc);
					QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
					SolrDocumentList rechercheListe = rechercheReponse.getResults();

					rechercheLignes = rechercheSolr.getRows();

					rechercheLigneRecherche = -1;
					rechercheLignePOST = -1;
					rechercheLignePATCH = -1;
					rechercheLignePage = -1;

					List<String> pageVars = Arrays.asList("pageH1", "pageH2", "pageH3", "pageTitre");
	
					if(rechercheListe.size() > 0) {
						Boolean resultatFormPOST = false; 
						Boolean resultatFormPage = false; 
						Boolean resultatFormPATCH = false; 
						Boolean resultatFormRecherche = false; 

						for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
							for(Integer j = 0; j < rechercheListe.size(); j++) {
								entiteDocumentSolr = rechercheListe.get(j);
								entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNom + "_stored_string");
								entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + langueNom + "_stored_string");
								entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + langueNom + "_stored_string");
								entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
								entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
								entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
								entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
								entiteHtmlLigne = (Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int");
								entiteCouverture = (Boolean)entiteDocumentSolr.get("entiteCouverture_stored_boolean");
								entiteHtml = (Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean");
								entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
								entiteStocke = (Boolean)entiteDocumentSolr.get("entiteStocke_stored_boolean");
								entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
								entiteModifier = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteModifier_stored_boolean"));
								entiteDefinir = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteDefinir_stored_boolean"));
								entiteAttribuer = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteAttribuer_stored_boolean"));
								entiteSuggere = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean"));
								entiteAttribuerNomSimple = (String)entiteDocumentSolr.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string");
								entiteAttribuerVar = (String)entiteDocumentSolr.get("entiteAttribuerVar_" + langueNom + "_stored_string");
								entiteAttribuerVarSuggere = (String)entiteDocumentSolr.get("entiteAttribuerVarSuggere_" + langueNom + "_stored_string");
								entiteAttribuerOperationIdPATCH = (String)entiteDocumentSolr.get("entiteAttribuerOperationIdPATCH_" + langueNom + "_stored_string");
								entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + str_Recherche(langueNom) + "_" + langueNom + "_stored_string");
	
								if(pageVars.contains(entiteVar)) {
									if(entiteCouverture) {
										if("pageH1".equals(entiteVar)) {
											pageH1 = true;
										}
										else if("pageH2".equals(entiteVar)) {
											pageH2 = true;
										}
										else if("pageH3".equals(entiteVar)) {
											pageH3 = true;
										}
										else if("pageTitre".equals(entiteVar)) {
											pageTitre = true;
										}
										else {
											wEntites.l();
											wEntites.t(1, "@Override protected void _", entiteVar, "(");
											if(entiteCouverture)
												wEntites.s(classePartsCouverture.nomSimple(langueNom), "<", entiteNomSimpleComplet, "> c");
											else
												wEntites.s(entiteNomSimpleComplet, " o");
											wEntites.l(") {");
											wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), " != null)");
											wEntites.tl(3, "c.o(", StringUtils.uncapitalize(classeNomSimple), ".get", entiteVarCapitalise, "()", ");");
											wEntites.tl(1, "}");
										}
									}
								}
	
								if(entiteHtml) {
									if(entiteHtmlLigne != null) {
										if(ecrireFormEntite(wFormPOST, "POST"))
											resultatFormPOST = true;
										if(ecrireFormEntite(wFormPage, "Page"))
											resultatFormPage = true;
									}
									if(entiteStocke) {
										if(ecrireFormEntite(wFormPATCH, "PATCH"))
											resultatFormPATCH = true;
									}
									if(entiteIndexe) {
										if(ecrireFormEntite(wFormRecherche, "Recherche"))
											resultatFormRecherche = true;
									}
								}
								if(entiteAttribuer) {
									wJsInit.tl(2, "tl(1, ", q(str_rechercher(langueNom), classeNomSimple, entiteVarCapitalise, "($('#", "form", classeNomSimple, entiteVarCapitalise, "'), $('#", "list", classeNomSimple, entiteVarCapitalise, "')); "), ");");
								}
							}
							rechercheSolr.setStart(i.intValue() + rechercheLignes);
							rechercheReponse = clientSolrComputate.query(rechercheSolr);
							rechercheListe = rechercheReponse.getResults();
						}
						if(resultatFormPOST)
							wFormPOST.t(2).bgl("div");
						if(resultatFormPage)
							wFormPage.t(2).bgl("div");
						if(resultatFormPATCH)
							wFormPATCH.t(2).bgl("div");
						if(resultatFormRecherche)
							wFormRecherche.t(2).bgl("div");
					}
				}
	
				wEntites.l();
				wEntites.tl(1, "@Override protected void _pageH1(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				if(pageH1) {
					wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), " != null && ", StringUtils.uncapitalize(classeNomSimple), ".getPageH1() != null)");
					wEntites.tl(3, "c.o(", StringUtils.uncapitalize(classeNomSimple), ".getPageH1()", ");");
					wEntites.tl(2, "else if(", StringUtils.uncapitalize(classeNomSimple), " != null)");
				} else {
					wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), " != null)");
				}
				if(contexteH1 != null)
					wEntites.tl(3, "c.o(", q(contexteH1), ");");
				else
					wEntites.tl(3, "c.o(", q(contexteUnNom), ");");
				if(!classePageSimple) {
					wEntites.tl(2, "else if(", str_liste(langueNom), classeNomSimple, " == null || ", str_liste(langueNom), classeNomSimple, ".size() == 0)");
					wEntites.tl(3, "c.o(", q(contexteAucunNomTrouve), ");");
				}
				if(contexteH1 != null) {
					wEntites.tl(2, "else");
					wEntites.tl(3, "c.o(", q(contexteH1), ");");
				}
				wEntites.tl(1, "}");
	
				wEntites.l();
				wEntites.tl(1, "@Override protected void _pageH2(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				if(pageH2) {
					wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), " != null && ", StringUtils.uncapitalize(classeNomSimple), ".getPageH2() != null)");
					wEntites.tl(3, "c.o(", StringUtils.uncapitalize(classeNomSimple), ".getPageH2()", ");");
					if(contexteH2 != null) {
						wEntites.tl(2, "else");
						wEntites.tl(3, "c.o(", q(contexteH2), ");");
					}
				} else {
					wEntites.tl(2, "c.o(", q(contexteH2), ");");
				}
				wEntites.tl(1, "}");
	
				wEntites.l();
				wEntites.tl(1, "@Override protected void _pageH3(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				if(pageH3) {
					wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), " != null && ", StringUtils.uncapitalize(classeNomSimple), ".getPageH3() != null)");
					wEntites.tl(3, "c.o(", StringUtils.uncapitalize(classeNomSimple), ".getPageH3()", ");");
					if(contexteH3 != null) {
						wEntites.tl(2, "else");
						wEntites.tl(3, "c.o(", q(contexteH3), ");");
					}
				} else {
					wEntites.tl(2, "c.o(", q(contexteH3), ");");
				}
				wEntites.tl(1, "}");
	
				wEntites.l();
				wEntites.tl(1, "@Override protected void _page", str_Titre(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				if(pageTitre) {
					wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), " != null && ", StringUtils.uncapitalize(classeNomSimple), ".getPage", str_Titre(langueNom), "() != null)");
					wEntites.tl(3, "c.o(", StringUtils.uncapitalize(classeNomSimple), ".getPage", str_Titre(langueNom), "()", ");");
					wEntites.tl(2, "else if(", StringUtils.uncapitalize(classeNomSimple), " != null)");
				} else {
					wEntites.tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), " != null)");
				}
				wEntites.tl(3, "c.o(", q(contexteTitre), ");");
				if(!classePageSimple) {
					wEntites.tl(2, "else if(", str_liste(langueNom), classeNomSimple, " == null || ", str_liste(langueNom), classeNomSimple, ".size() == 0)");
					wEntites.tl(3, "c.o(", q(contexteAucunNomTrouve), ");");
				}
				if(contexteTitre != null) {
					wEntites.tl(2, "else");
					wEntites.tl(3, "c.o(", q(contexteTitre), ");");
				}
				wEntites.tl(1, "}");
	
				wEntites.l();
				wEntites.tl(1, "@Override protected void _pageUri(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				wEntites.tl(2, "c.o(", q(classePageUriMethode), ");");
				wEntites.tl(1, "}");
				for(String pageLangueNom : toutesLangues) {
					if(!StringUtils.equals(classePageLangueNom, pageLangueNom)) {
						String classePageUriMethodeLangue = (String)classeDoc.get(StringUtils.replace("classeApiUri" + classePageMethode + "_stored_string", StringUtils.capitalize(classePageLangueNom), StringUtils.capitalize(pageLangueNom)));
						
						if(classePageUriMethodeLangue != null) {
							wEntites.l();
							wEntites.tl(1, "@Override protected void _pageUri", StringUtils.capitalize(pageLangueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
							wEntites.tl(2, "c.o(", q(classePageUriMethodeLangue), ");");
							wEntites.tl(1, "}");
						}
					}
				}
		
				if(auteurPageClasse != null) {
					auteurPageClasse.l("package ", classeNomEnsemble, ";");
					auteurPageClasse.l();
					auteurPageClasse.l("/**");
					auteurPageClasse.l(" * ", str_Traduire(langueNom), ": false");
					auteurPageClasse.l(" **/");
					auteurPageClasse.s("public class ", classePageNomSimple);
					auteurPageClasse.s(" extends ", classePageNomSimple, "Gen<", classeGenPageNomSimple, ">");
					auteurPageClasse.l(" {");
					auteurPageClasse.tl(0, "}");
				}

				l("package ", classeNomEnsemble, ";");
				l();
				if(classeImportationsGenPage.size() > 0) { 
					for(String classeImportation : classeImportationsGenPage) {
						l("import ", classeImportation, ";");
					}
					l();
				}
		
				tl(0, "");
//				ecrireCommentaire(classeCommentaire, 0); 
				l("/**");
				l(" * ", str_Traduire(langueNom), ": false");
				l(" **/");
				s("public class ", classeGenPageNomSimple);
				s(" extends ", classeGenPageNomSimple, "Gen");
				s("<", classePageSuperNomSimple, ">");
				l(" {");
				if(!classePageSimple) {
					l();
					tl(1, "/**");
					tl(1, " * {@inheritDoc}");
					tl(1, " * ");
					tl(1, " **/");
					tl(1, "protected void _", str_liste(langueNom), classeNomSimple, "(", classePartsCouverture.nomSimple(langueNom), "<", str_ListeRecherche(langueNom), "<", classeNomSimple, ">> c) {");
					tl(1, "}");
				}
				l();
				tl(1, "protected void _", StringUtils.uncapitalize(classeNomSimple), "(", "", classePartsCouverture.nomSimple(langueNom), "<", classeNomSimple, "> c", ") {");
				if(classePageSimple) {
					tl(2, "c.o(new ", classeNomSimple, "());");
				} else {
					tl(2, "if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1)");
					tl(3, "c.o(", str_liste(langueNom), classeNomSimple, ".get(0)", ");");
				}
				tl(1, "}");
				s(wEntites);
	
				if(contexteDescription != null) {
					l();
					tl(1, "@Override protected void _pageDescription(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
					tl(3, "c.o(", q(contexteDescription), ");");
					tl(1, "}");
				}
	
				if(classeImage != null) {
					l();
					tl(1, "@Override protected void _pageImageUri(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
					tl(3, "c.o(", q("/png", classePageUriMethode, "-999.png"), ");");
					tl(1, "}");
				}
	
				if(contexteImageLargeur != null) {
					l();
					tl(1, "@Override protected void _pageImage", str_Largeur(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
					tl(3, "c.o(", contexteImageLargeur, ");");
					tl(1, "}");
				}
	
				if(contexteImageHauteur != null) {
					l();
					tl(1, "@Override protected void _pageImage", str_Hauteur(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
					tl(3, "c.o(", contexteImageHauteur, ");");
					tl(1, "}");
				}
	
				if(StringUtils.isNotBlank(contexteVideoId)) {
					l();
					tl(1, "@Override protected void _pageVideoId(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
					tl(3, "c.o(", q(contexteVideoId), ");");
					tl(1, "}");
				}
	
				if(StringUtils.isNotBlank(contexteIconeGroupe)) {
					l();
					tl(1, "@Override protected void _", str_contexte(langueNom), str_Icone(langueNom), str_Groupe(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
					tl(3, "c.o(", q(contexteIconeGroupe), ");");
					tl(1, "}");
				}
	
				if(StringUtils.isNotBlank(contexteIconeNom)) {
					l();
					tl(1, "@Override protected void _", str_contexte(langueNom), str_Icone(langueNom), str_Nom(langueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
					tl(3, "c.o(", q(contexteIconeNom), ");");
					tl(1, "}");
				}
	
				l();
				tl(1, "@Override public void ", str_initLoin(langueNom), classeGenPageNomSimple, "() {");
				tl(2, "init", classeGenPageNomSimple, "();");
				tl(2, "super.", str_initLoin(langueNom), classePartsMiseEnPage.nomSimple(langueNom), "();");
				tl(1, "}");
				l();
				tl(1, "@Override public void htmlScripts", classeGenPageNomSimple, "() {");
				t(2).l("e(\"script\").a(\"src\", ", str_statiqueUrlBase(langueNom), ", \"/js/", langueNom, "/", classePageNomSimple, ".js\").f().g(\"script\");");
				if(classeAttribuerNomSimplePages != null) {
					for(String classeAttribuerNomSimplePage : classeAttribuerNomSimplePages) {
						t(2).l("e(\"script\").a(\"src\", ", str_statiqueUrlBase(langueNom), ", \"/js/", langueNom, "/", classeAttribuerNomSimplePage, ".js\").f().g(\"script\");");
					}
				}
				tl(1, "}");
	
				if(StringUtils.isNotBlank(classeApiUri)) {
					l();
					tl(1, "protected void _pageUri", classeNomSimple, "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
					tl(3, "c.o(", q(classePageUriMethode), ");");
					tl(1, "}");
				}
	
				{
					SolrQuery rechercheSolr = new SolrQuery();   
					rechercheSolr.setQuery("*:*");
					rechercheSolr.setRows(1000000);
					String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
					rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
					rechercheSolr.addFilterQuery("classeNomCanonique_" + this.langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
					rechercheSolr.addFilterQuery("entiteHtmlColonne_indexed_double:[* TO *]");
					rechercheSolr.addSort("entiteHtmlColonne_indexed_double", ORDER.asc);
					QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
					SolrDocumentList rechercheListe = rechercheReponse.getResults();
					Integer rechercheLignes = rechercheSolr.getRows();
					Integer rechercheLigne = -1;
					Integer rechercheLigneActuel;
		
					if(rechercheListe.size() > 0) {
						for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
							for(Integer j = 0; j < rechercheListe.size(); j++) {
								SolrDocument entiteDocumentSolr = rechercheListe.get(j);
								String entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNom + "_stored_string");
								String entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + langueNom + "_stored_string");
								String entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + langueNom + "_stored_string");
								String entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
								String entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
								String entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
								String entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
								Boolean entiteHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean"));
								Boolean entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
								Boolean entiteHighlighting = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHighlighting_stored_boolean"));
								if(entiteHtml) {
									String jsVal = ".val()";
									if("Boolean".equals(entiteNomSimple)) {
										jsVal = ".prop('checked')";
									}
		
									wTh.tl(6, "e(\"th\").f().sx(", q(entiteNomAffichage), ").g(\"th\");");
		
									wTd.tl(7, "{ e(\"td\").f();");
									wTd.tl(8, "{ e(\"a\").a(\"href\", uri).f();");
									wTd.tl(9, "sx(o.get", entiteVarCapitalise, "());");
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
							rechercheSolr.setStart(i.intValue() + rechercheLignes);
							rechercheReponse = clientSolrComputate.query(rechercheSolr);
							rechercheListe = rechercheReponse.getResults();
						}
					}
				}
	
				{
					SolrQuery rechercheSolr = new SolrQuery();   
					rechercheSolr.setQuery("*:*");
					rechercheSolr.setRows(1000000);
					String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
					rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
					rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
					rechercheSolr.addSort("entiteHtmlLigne_indexed_int", ORDER.asc);
					rechercheSolr.addSort("entiteHtmlCellule_indexed_int", ORDER.asc);
					QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
					SolrDocumentList rechercheListe = rechercheReponse.getResults();
					Integer rechercheLignes = rechercheSolr.getRows();
					Integer rechercheLigne = -1;
					Integer rechercheLigneActuel;
		
					if(rechercheListe.size() > 0) {
						for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
							for(Integer j = 0; j < rechercheListe.size(); j++) {
								entiteDocumentSolr = rechercheListe.get(j);
								entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNom + "_stored_string");
								entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + langueNom + "_stored_string");
								entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + langueNom + "_stored_string");
								entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
								entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
								entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
								entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
								entiteHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean"));
								entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
								entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
								entiteSuggere = (Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean");
								entiteNomSimpleVertxJson = (String)entiteDocumentSolr.get("entiteNomSimpleVertxJson_stored_string");
								String jsVal = ".val()";
								if("Boolean".equals(entiteNomSimple)) {
									jsVal = ".prop('checked')";
								}

								if(entiteIndexe) {
		
									wRecherche.l();
									wRecherche.tl(1, "var ", str_filtre(langueNom), entiteVarCapitalise, " = $", str_formulaireFiltres(langueNom), ".find('.", str_valeur(langueNom), entiteVarCapitalise, "')", jsVal, ";");

									if("Boolean".equals(entiteNomSimple))
										wRecherche.tl(1, "if(", str_filtre(langueNom), entiteVarCapitalise, " != null && ", str_filtre(langueNom), entiteVarCapitalise, " === true)");
									else
										wRecherche.tl(1, "if(", str_filtre(langueNom), entiteVarCapitalise, " != null && ", str_filtre(langueNom), entiteVarCapitalise, " !== '')");

									wRecherche.tl(2, str_filtres(langueNom), ".push({ name: '", (entiteSuggere ? "q" : "fq"), "', value: '", entiteVar, ":' + ", str_filtre(langueNom), entiteVarCapitalise, " });");
								}

								if(entiteHtml) {
									String valPrefixe;
									String valSuffixe;
									if("Double".equals(entiteNomSimpleVertxJson)) {
										valPrefixe = "parseDouble(";
										valSuffixe = ")";
									}
									else if("Integer".equals(entiteNomSimpleVertxJson)) {
										valPrefixe = "parseInt(";
										valSuffixe = ")";
									}
									else { 
										valPrefixe = "";
										valSuffixe = "";
									}
		
									wPOST.l();
									wPOST.tl(1, "var ", str_valeur(langueNom), entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.", str_valeur(langueNom), entiteVarCapitalise, "')", jsVal, ";");
									wPOST.tl(1, "if(", str_valeur(langueNom), entiteVarCapitalise, " != null && ", str_valeur(langueNom), entiteVarCapitalise, " !== '')");
									wPOST.tl(2, str_valeurs(langueNom), "['", entiteVar, "'] = ", valPrefixe, str_valeur(langueNom), entiteVarCapitalise, ";");
		
									wPATCH.l();

									wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", str_formulaireFiltres(langueNom), ".find('.remove", entiteVarCapitalise, "').val() === 'true';");
									wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : $", str_formulaireValeurs(langueNom), ".find('.set", entiteVarCapitalise, "')", jsVal, ";");
									wPATCH.tl(1, "if(remove", entiteVarCapitalise, " || set", entiteVarCapitalise, " != null && set", entiteVarCapitalise, " !== '')");
									wPATCH.tl(2, str_valeurs(langueNom), "['set", entiteVarCapitalise, "'] = ", valPrefixe, "set", entiteVarCapitalise, valSuffixe, ";");
									wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.add", entiteVarCapitalise, "')", jsVal, ";");
									wPATCH.tl(1, "if(add", entiteVarCapitalise, " != null && add", entiteVarCapitalise, " !== '')");
									wPATCH.tl(2, str_valeurs(langueNom), "['add", entiteVarCapitalise, "'] = ", valPrefixe, "add", entiteVarCapitalise, valSuffixe, ";");
									wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", str_formulaireValeurs(langueNom), ".find('.remove", entiteVarCapitalise, "')", jsVal, ";");
									wPATCH.tl(1, "if(remove", entiteVarCapitalise, " != null && remove", entiteVarCapitalise, " !== '')");
									wPATCH.tl(2, str_valeurs(langueNom), "['remove", entiteVarCapitalise, "'] = ", valPrefixe, "remove", entiteVarCapitalise, valSuffixe, ";");
								}
							}
							rechercheSolr.setStart(i.intValue() + rechercheLignes);
							rechercheReponse = clientSolrComputate.query(rechercheSolr);
							rechercheListe = rechercheReponse.getResults();
						}
					}
				}
	
				l();
				if(!classePageSimple) {
					tl(1, "@Override public void htmlScript", classeGenPageNomSimple, "() {");
					for(String classeApiMethode : classeApiMethodes) {
						String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");
		
						if("application/json".equals(classeApiTypeMediaMethode)) {
							Boolean methodePOST = classeApiMethodeMethode.equals("POST");
							Boolean methodeGET = classeApiMethode.contains("GET");
							Boolean methodePUT = classeApiMethodeMethode.equals("PUT");
							Boolean methodePATCH = classeApiMethodeMethode.equals("PATCH");
							Boolean methodeDELETE = classeApiMethodeMethode.equals("DELETE");
							Boolean methodeRecherche = classeApiMethode.contains(str_Recherche(langueNom));
		
							auteurPageJs.l();
							auteurPageJs.tl(0, "// ", classeApiMethode, " //");
							auteurPageJs.l();
//							auteurPageJs.l("/**");
//							if(methodePATCH) {
//							auteurPageJs.l(" * Modifier un ou plusiers ", contexteNomPluriel, " sans valuers qui change, ");
//							auteurPageJs.l(" * ou changer des valeurs pour un ou plusiers ", contexteLeNom, ". ");
//							auteurPageJs.l(" * @param params: [ \"q=*:*\", \"fq=pk:1\", \"sort=pk asc\", \"rows=1\", \"fl=pk\" ]");
//							auteurPageJs.l(" *        Une liste des opérations de recherche sur des ", contexteNomPluriel, " ");
//							auteurPageJs.l(" *        pour rechercher \"q=*:*\", filtrer \"fq=pk:1\", trier \"sort=pk desc\", ");
//							auteurPageJs.l(" *        limiter les résultats \"rows=1\", ou limiter les valeurs \"fl=pk\". ");
//							auteurPageJs.l(" * @param valeurs Noms des champs et valeurs à changer selon les filtres fq. ");
//							auteurPageJs.l(" *           Example: { pk: 1 }");
//							}
//							auteurPageJs.l(" */");
							auteurPageJs.t(0, "function ", classeApiOperationIdMethode, "(");
							if(methodePOST)
								auteurPageJs.s("$", str_formulaireValeurs(langueNom));
							else if(methodePUT)
								auteurPageJs.s("pk, $", str_formulaireValeurs(langueNom));
							else if(methodePATCH)
								auteurPageJs.s("$", str_formulaireFiltres(langueNom), ", $", str_formulaireValeurs(langueNom));
							else if(methodeRecherche) {
								auteurPageJs.s("$", str_formulaireFiltres(langueNom), "");
								auteurPageJs.s(", success");
								auteurPageJs.s(", error");
							}
							else if(methodeGET || methodeDELETE)
								auteurPageJs.s("pk");
		
							auteurPageJs.l(") {");
							if(methodePOST) {
								auteurPageJs.tl(1, "var ", str_valeurs(langueNom), " = {};");
								auteurPageJs.s(wPOST);
								auteurPageJs.l();
							}
							else if(methodePUT) {
								auteurPageJs.tl(1, "var ", str_valeurs(langueNom), " = {};");
								auteurPageJs.s(wPOST);
								auteurPageJs.l();
							}
							else if(methodePATCH) {
								auteurPageJs.tl(1, "var ", str_filtres(langueNom), " = ", classeApiOperationIdMethode,str_Filtres(langueNom), "();");
								auteurPageJs.l();
								auteurPageJs.tl(1, "var ", str_valeurs(langueNom), " = {};");
								auteurPageJs.s(wPATCH);
								auteurPageJs.l();
							}
							else if(methodeRecherche) {
								auteurPageJs.tl(1, "var ", str_filtres(langueNom), " = ", classeApiOperationIdMethode,str_Filtres(langueNom), "($", str_formulaireFiltres(langueNom), ");");
								auteurPageJs.tl(1, "if(success == null)");
								auteurPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {};");
								auteurPageJs.tl(1, "if(error == null)");
								auteurPageJs.tl(2, "error = function( jqXhr, textStatus, errorThrown ) {};");
								auteurPageJs.l();
							}
		
							if(methodePATCH) {
								auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", str_valeurs(langueNom), ");");
							}
							else {
								auteurPageJs.tl(1, "$.ajax({");
			
								if(methodeGET || methodeDELETE || methodePUT)
									auteurPageJs.tl(2, "url: '", StringUtils.replace(classeApiUriMethode, "{id}", "' + id"));
								else if(methodePATCH || methodeRecherche)
									auteurPageJs.tl(2, "url: '", classeApiUriMethode, "?' + $.param(", str_filtres(langueNom), ")");
								else
									auteurPageJs.tl(2, "url: '", classeApiUriMethode, "'");
			
								auteurPageJs.tl(2, ", dataType: 'json'");
								auteurPageJs.tl(2, ", type: '", classeApiMethodeMethode, "'");
								auteurPageJs.tl(2, ", contentType: 'application/json; charset=utf-8'");
								if(!"GET".equals(classeApiMethodeMethode) || "DELETE".equals(classeApiMethodeMethode))
									auteurPageJs.tl(2, ", data: JSON.stringify(", str_valeurs(langueNom), ")");
								auteurPageJs.tl(2, ", success: success");
								auteurPageJs.tl(2, ", error: error");
//								auteurPageJs.tl(2, ", success: function( data, textStatus, jQxhr ) {");
//								auteurPageJs.tl(3, "$.each( ", str_valeurs(langueNom), ", function( key, value ) {");
//								auteurPageJs.tl(4, "$", str_formulaireValeurs(langueNom), ".find('.' + key).removeClass('", str_lueur(langueNom), str_Erreur(langueNom), "');");
//								auteurPageJs.tl(4, "$", str_formulaireValeurs(langueNom), ".find('.' + key).addClass('", str_lueur(langueNom), str_Succes(langueNom), "');");
//								auteurPageJs.tl(3, "});");
//								auteurPageJs.tl(2, "}");
//								auteurPageJs.tl(2, ", error: function( jqXhr, textStatus, errorThrown ) {");
//								auteurPageJs.tl(3, "$.each( ", str_valeurs(langueNom), ", function( key, value ) {");
//								auteurPageJs.tl(4, "$", str_formulaireValeurs(langueNom), ".find('.' + key).removeClass('", str_lueur(langueNom), str_Succes(langueNom), "');");
//								auteurPageJs.tl(4, "$", str_formulaireValeurs(langueNom), ".find('.' + key).addClass('", str_lueur(langueNom), str_Erreur(langueNom), "');");
//								auteurPageJs.tl(3, "});");
//								auteurPageJs.tl(2, "}");
								auteurPageJs.tl(1, "});");
							}
							auteurPageJs.l("}");

							if(methodePATCH || methodeRecherche) {
								auteurPageJs.l();
								auteurPageJs.tl(0, "function ", classeApiOperationIdMethode,str_Filtres(langueNom), "($", str_formulaireFiltres(langueNom), ") {");
								auteurPageJs.tl(1, "var ", str_filtres(langueNom), " = [];");
								auteurPageJs.s(wRecherche);
								auteurPageJs.tl(1, "return ", str_filtres(langueNom), ";");
								auteurPageJs.tl(0, "}");
							}
							if(methodePATCH) {
								auteurPageJs.l();
								auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Val(", str_filtres(langueNom), ", v, val, $", str_formulaireValeur(langueNom), ") {");
								auteurPageJs.tl(1, "var json = {};");
								auteurPageJs.tl(1, "json[v] = val;");
								auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", str_filtres(langueNom), ", json, $", str_formulaireValeur(langueNom), " == null ? $", str_formulaireValeur(langueNom), " : [$", str_formulaireValeur(langueNom), "]);");
								auteurPageJs.l("}");
								auteurPageJs.l();
								auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(", str_filtres(langueNom), ", ", str_valeurs(langueNom), ", $", str_formulaireValeurs(langueNom), ") {");
								auteurPageJs.tl(1, "$.ajax({");
								auteurPageJs.tl(2, "url: '", classeApiUriMethode, "?' + $.param(", str_filtres(langueNom), ")");
								auteurPageJs.tl(2, ", dataType: 'json'");
								auteurPageJs.tl(2, ", type: '", classeApiMethodeMethode, "'");
								auteurPageJs.tl(2, ", contentType: 'application/json; charset=utf-8'");
								auteurPageJs.tl(2, ", data: JSON.stringify(", str_valeurs(langueNom), ")");
								auteurPageJs.tl(2, ", success: function( data, textStatus, jQxhr ) {");
								auteurPageJs.tl(3, "if($", str_formulaireValeurs(langueNom), " != null) {");
								auteurPageJs.tl(4, "for(var i = 0; i < $", str_formulaireValeurs(langueNom), ".length; i++) {");
								auteurPageJs.tl(5, "$", str_formulaireValeur(langueNom), " = $", str_formulaireValeurs(langueNom), "[i];");
								auteurPageJs.tl(5, "$", str_formulaireValeur(langueNom), ".removeClass('", str_lueur(langueNom), str_Erreur(langueNom), "');");
								auteurPageJs.tl(5, "$", str_formulaireValeur(langueNom), ".addClass('", str_lueur(langueNom), str_Succes(langueNom), "');");
								auteurPageJs.tl(4, "}");
								auteurPageJs.tl(3, "}");
								auteurPageJs.tl(2, "}");
								auteurPageJs.tl(2, ", error: function( jqXhr, textStatus, errorThrown ) {");
								auteurPageJs.tl(3, "if($", str_formulaireValeurs(langueNom), " != null) {");
								auteurPageJs.tl(4, "for(var i = 0; i < $", str_formulaireValeurs(langueNom), ".length; i++) {");
								auteurPageJs.tl(5, "$", str_formulaireValeur(langueNom), " = $", str_formulaireValeurs(langueNom), "[i];");
								auteurPageJs.tl(5, "$", str_formulaireValeur(langueNom), ".removeClass('", str_lueur(langueNom), str_Succes(langueNom), "');");
								auteurPageJs.tl(5, "$", str_formulaireValeur(langueNom), ".addClass('", str_lueur(langueNom), str_Erreur(langueNom), "');");
								auteurPageJs.tl(4, "}");
								auteurPageJs.tl(3, "}");
								auteurPageJs.tl(2, "}");
								auteurPageJs.tl(1, "});");
								auteurPageJs.l("}");
							}
							if(methodeRecherche) {
								SolrQuery rechercheSolr = new SolrQuery();   
								rechercheSolr.setQuery("*:*");
								rechercheSolr.setRows(1000000);
								String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
								rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
								rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
								rechercheSolr.addFilterQuery("(entiteSuggere_indexed_boolean:true OR entiteAttribuer_indexed_boolean:true)");
								QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
								SolrDocumentList rechercheListe = rechercheReponse.getResults();
			
								rechercheLignes = rechercheSolr.getRows();
				
								if(rechercheListe.size() > 0) {
			
									for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
										for(Integer j = 0; j < rechercheListe.size(); j++) {
											entiteDocumentSolr = rechercheListe.get(j);
											entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNom + "_stored_string");
											entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + langueNom + "_stored_string");
											entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + langueNom + "_stored_string");
											entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
											entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
											entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
											entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
											entiteHtmlLigne = (Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int");
											entiteCouverture = (Boolean)entiteDocumentSolr.get("entiteCouverture_stored_boolean");
											entiteHtml = (Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean");
											entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
											entiteStocke = (Boolean)entiteDocumentSolr.get("entiteStocke_stored_boolean");
											entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
											entiteModifier = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteModifier_stored_boolean"));
											entiteDefinir = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteDefinir_stored_boolean"));
											entiteAttribuer = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteAttribuer_stored_boolean"));
											entiteSuggere = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean"));
											entiteAttribuerNomSimple = (String)entiteDocumentSolr.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string");
											entiteAttribuerVar = (String)entiteDocumentSolr.get("entiteAttribuerVar_" + langueNom + "_stored_string");
											entiteAttribuerVarSuggere = (String)entiteDocumentSolr.get("entiteAttribuerVarSuggere_" + langueNom + "_stored_string");
											entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + str_Recherche(langueNom) + "_" + langueNom + "_stored_string");
											entiteAttribuerOperationIdPATCH = (String)entiteDocumentSolr.get("entiteAttribuerOperationIdPATCH_" + langueNom + "_stored_string");
				
											if(entiteSuggere) {
												auteurPageJs.l();
												auteurPageJs.tl(0, "function ", str_rechercher(langueNom), classeNomSimple, entiteVarCapitalise, "($", str_formulaireFiltres(langueNom), ", $list) {");
												auteurPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
//												auteurPageJs.tl(2, "$list.empty();");
//												auteurPageJs.tl(2, "$.each(data['list'], function(o) {");
//												auteurPageJs.tl(3, "var $i = $('<i>').attr('class', 'far fa-something ');");
//												auteurPageJs.tl(3, "var $span = $('<span>').attr('class', '').text();");
//												auteurPageJs.tl(3, "var $a = $('<a>').attr('href', '...');");
//												auteurPageJs.tl(3, "$a.append($i);");
//												auteurPageJs.tl(3, "$a.append($span);");
//												auteurPageJs.tl(3, "var $input = $('<input>').attr('class', 'w3-check ').attr('onchange', '", entiteAttribuerOperationIdPATCH, "($(this).parent()); ').attr('onclick', '", str_enleverLueur(langueNom), "($(this)); ').attr('type', 'checkbox').attr('checked', 'checked').attr('name', '...').attr('value', '...');");
//												auteurPageJs.tl(3, "var $li = $('<li>');");
//												auteurPageJs.tl(3, "$li.append($input);");
//												auteurPageJs.tl(3, "$li.append($a);");
//												auteurPageJs.tl(3, "$list.append($li);");
//												auteurPageJs.tl(2, "});");
												auteurPageJs.tl(1, "};");
												auteurPageJs.tl(1, "error = function( jqXhr, textStatus, errorThrown ) {};");
												auteurPageJs.tl(1, classeApiOperationIdMethode, "($", str_formulaireFiltres(langueNom), ", success, error);");
												auteurPageJs.tl(0, "}");
											}
											else if(entiteAttribuer) {
												auteurPageJs.l();
												auteurPageJs.tl(0, "function ", str_rechercher(langueNom), classeNomSimple, entiteVarCapitalise, "($", str_formulaireFiltres(langueNom), ", $list) {");
												auteurPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
												auteurPageJs.tl(2, "$list.empty();");
												auteurPageJs.tl(2, "$.each(data['list'], function(o) {");
												auteurPageJs.tl(3, "var $i = $('<i>').attr('class', 'far fa-something ');");
												auteurPageJs.tl(3, "var $span = $('<span>').attr('class', '').text();");
												auteurPageJs.tl(3, "var $a = $('<a>').attr('href', '...');");
												auteurPageJs.tl(3, "$a.append($i);");
												auteurPageJs.tl(3, "$a.append($span);");
												auteurPageJs.tl(3, "var $input = $('<input>').attr('class', 'w3-check ').attr('onchange', '", entiteAttribuerOperationIdPATCH, "($(this).parent()); ').attr('onclick', '", str_enleverLueur(langueNom), "($(this)); ').attr('type', 'checkbox').attr('checked', 'checked').attr('name', '...').attr('value', '...');");
												auteurPageJs.tl(3, "var $li = $('<li>');");
												auteurPageJs.tl(3, "$li.append($input);");
												auteurPageJs.tl(3, "$li.append($a);");
												auteurPageJs.tl(3, "$list.append($li);");
												auteurPageJs.tl(2, "});");
												auteurPageJs.tl(1, "};");
												auteurPageJs.tl(1, "error = function( jqXhr, textStatus, errorThrown ) {};");
												auteurPageJs.tl(1, entiteAttribuerOperationIdRecherche, "($", str_formulaireFiltres(langueNom), ", success, error);");
												auteurPageJs.tl(0, "}");
											}
										}
										rechercheSolr.setStart(i.intValue() + rechercheLignes);
										rechercheReponse = clientSolrComputate.query(rechercheSolr);
										rechercheListe = rechercheReponse.getResults();
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
					tl(1, "public void htmlFormPage", classeNomSimple, "(", classeNomSimple, " o) {");
					s(wFormPage);
					tl(1, "}");
					l();
					tl(1, "public void htmlFormPOST", classeNomSimple, "(", classeNomSimple, " o) {");
					s(wFormPOST);
					tl(1, "}");
					l();
					tl(1, "public void htmlFormPATCH", classeNomSimple, "(", classeNomSimple, " o) {");
					s(wFormPATCH);
					tl(1, "}");
					l();
					tl(1, "public void htmlForm", str_Recherche(langueNom), classeNomSimple, "(", classeNomSimple, " o) {");
					s(wFormRecherche);
					tl(1, "}");
				}
				l();
				tl(1, "@Override public void htmlBody", classeGenPageNomSimple, "() {");
				if(classePageSimple) {
					l();
					tl(2, "if(StringUtils.isNotBlank(pageH1)) {");
					t(3).be("h1").dfl();
					tl(4, "if(", str_contexteIconeClassesCss(langueNom), " != null)");
					tl(5, "e(\"i\").a(\"class\", ", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \").f().g(\"i\");");
		
					if(classeEntiteVars != null && classeEntiteVars.contains("pageH1"))
						t(4).e("span").da("class", " ").df().s(".sx(pageH1)").dgl("span");
					else
						t(4).e("span").da("class", " ").df().dsxq(contexteUnNom).dgl("span");
		
					t(3).bgl("h1");
					tl(2, "}");
		
					if(classeEntiteVars != null && classeEntiteVars.contains("pageH2")) {
						tl(2, "if(StringUtils.isNotBlank(pageH1)) {");
						t(3).be("h2").dfl();
						t(4).e("span").da("class", " ").df().s(".sx(pageH2)").dgl("span");
						t(3).bgl("h2");
						tl(2, "}");
					}
		
					if(classeEntiteVars != null && classeEntiteVars.contains("pageH3")) {
						tl(2, "if(StringUtils.isNotBlank(pageH3)) {");
						t(3).be("h3").dfl();
						t(4).e("span").da("class", " ").df().s(".sx(pageH3)").dgl("span");
						t(3).bgl("h3");
						tl(2, "}");
					}
		
					if(contexteVideoId != null) {
						t(2).be("div").da("class", "site-video-box ").dfl();
							t(3).e("iframe").da("class", "site-video-embed ").da("width", "560").da("height", "315").s(".a(\"src\", pageVideoUrlEmbed)").da("frameborder", "0").da("allow", "autoplay; encrypted-media").da("allowfullscreen", "").df().dgl("iframe");
						t(2).bgl("div"); 
					}
					if(classeMethodeVars.contains("htmlBody")) {
						l();
						tl(2, "if(", StringUtils.uncapitalize(classeNomSimple), " != null)");
						tl(3, StringUtils.uncapitalize(classeNomSimple), ".htmlBody();");
					}
					l();
				} else {
					l();
					tl(2, "OperationRequest ", str_operationRequete(langueNom), " = ", str_requeteSite(langueNom), "_.get", str_OperationRequete(langueNom), "();");
					tl(2, "JsonObject params = ", str_operationRequete(langueNom), ".getParams();");
					tl(2, "if(", str_liste(langueNom), classeNomSimple, " == null || ", str_liste(langueNom), classeNomSimple, ".size() == 0) {");
//					t(3).l("// contexteAucunNomTrouve : ", contexteAucunNomTrouve);
					l();
					t(3).be("h1").dfl();
					tl(4, "if(", str_contexteIconeClassesCss(langueNom), " != null)");
					tl(5, "e(\"i\").a(\"class\", ", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \").f().g(\"i\");");
					t(4).e("span").da("class", " ").df().dsxq(contexteAucunNomTrouve).dgl("span");
					t(3).bgl("h1");
//					tl(2, "} else if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\") && params.getJsonObject(\"query\").getJsonArray(\"fq\") == null) {");
					tl(2, "} else if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\")) {");
//					t(3).l("// contexteUnNom : ", contexteUnNom);
					tl(3, "if(pageH1 != null) {");
					t(4).be("h1").dfl();
					tl(5, "if(", str_contexteIconeClassesCss(langueNom), " != null)");
					tl(6, "e(\"i\").a(\"class\", ", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \").f().g(\"i\");");
		
					if(classeEntiteVars != null && classeEntiteVars.contains("pageH1"))
						t(5).e("span").da("class", " ").df().s(".sx(pageH1)").dgl("span");
					else
						t(5).e("span").da("class", " ").df().dsxq(contexteUnNom).dgl("span");
		
					t(4).bgl("h1");
					tl(4, classeNomSimple, " o = ", str_liste(langueNom), classeNomSimple, ".get(0);");
					tl(4, str_requeteSite(langueNom), "_.set", str_Requete(langueNom), "Pk(o.getPk());");

					tl(3, "}");
		
					if(classeEntiteVars != null && classeEntiteVars.contains("pageH2")) {
						tl(3, "if(pageH2 != null) {");
						t(4).be("h2").dfl();
						t(5).e("span").da("class", " ").df().s(".sx(pageH2)").dgl("span");
						t(4).bgl("h2");
						tl(3, "}");
					}
		
					if(classeEntiteVars != null && classeEntiteVars.contains("pageH3")) {
						tl(3, "if(pageH3 != null) {");
						t(4).be("h3").dfl();
						t(5).e("span").da("class", " ").df().s(".sx(pageH3)").dgl("span");
						t(4).bgl("h3");
						tl(3, "}");
					}
		
					tl(2, "} else {");
//					t(3).l("// contexteNomPluriel : plusiers ", contexteNomPluriel);
					l();
					t(3).be("h1").dfl();
					tl(4, "if(", str_contexteIconeClassesCss(langueNom), " != null)");
					tl(5, "e(\"i\").a(\"class\", ", str_contexteIconeClassesCss(langueNom), " + \" site-menu-icon \").f().g(\"i\");");
					t(4).e("span").da("class", " ").df().dsxq(contexteNomPluriel).dgl("span");
					t(3).bgl("h1");
					t(3).be("table").da("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").dfl();
					t(4).be("thead").dfl();
					t(5).be("tr").dfl();
					s(wTh);
					t(5).bgl("tr");
					t(4).bgl("thead");
					t(4).be("tbody").dfl();
					tl(5, "Map<String, Map<String, List<String>>> highlighting = ", str_liste(langueNom), classeNomSimple, ".getQueryResponse().getHighlighting();");
					tl(5, "for(int i = 0; i < ", str_liste(langueNom), classeNomSimple, ".size(); i++) {");
					tl(6, classeNomSimple, " o = ", str_liste(langueNom), classeNomSimple, ".getList().get(i);");
					tl(6, "Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());");
					tl(6, "List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));");
					tl(6, "String uri = ", classeEntiteVars.contains("pageUri") ? "o.getPageUri()" : q(classePageUriMethode, "/") + " + o.getPk()", ";");
					tl(6, "{ e(\"tr\").f();");
					s(wTd);
					tl(6, "} g(\"tr\");");
					tl(5, "}");
					t(4).bgl("tbody");
					t(3).bgl("table");
					tl(2, "}");
		
					{
						// Formulaires de recherche
						SolrQuery rechercheSolr = new SolrQuery();   
						rechercheSolr.setQuery("*:*");
						rechercheSolr.setRows(1000000);
						String fqClassesSuperEtMoi = "(" + entiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
						rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
						rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
						rechercheSolr.addFilterQuery("entiteTexte_indexed_boolean:true");
						QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
						SolrDocumentList rechercheListe = rechercheReponse.getResults();
						Integer rechercheLignes = rechercheSolr.getRows();
						Integer rechercheLigne = -1;
						Integer rechercheLigneActuel;
			
						if(rechercheListe.size() > 0) {
							for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
								for(Integer j = 0; j < rechercheListe.size(); j++) {
									SolrDocument entiteDocumentSolr = rechercheListe.get(j);
									String entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNom + "_stored_string");
									String entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + langueNom + "_stored_string");
									String entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
									String entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
									String entiteLangue = (String)entiteDocumentSolr.get("entiteLangue_stored_string");
									Boolean entiteSuggere = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean"));

									if(entiteLangue == null || StringUtils.equals(entiteLangue, langueNom)) {
										l();
										t(2).be("div").da("class", "").dfl();
										t(3).be("form")
											.da("id", classeNomSimple, "Form")
											.da("style", "display: inline-block; ")
											.da("method", "GET")
											.da("action", classePageUriMethode)
											.da("onsubmit", "event.preventDefault(); " + str_rechercher(langueNom) + "($('#" + str_recherche(langueNom) + entiteVarCapitalise + "')); return false; ")
											.dfl();
										t(4).be("div").da("class", "w3-bar ").dfl();
	//									t(5).e("label").da("for", "recherche", entiteVarCapitalise).da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
										t(5).e("input").dal("type", "text");
										if(contexteTousNom != null) {
											if(entiteNomAffichage != null) {
												t(6).dal("placeholder", contexteRechercherTousNomPar + entiteNomAffichage);
											}
											else {
												t(6).dal("placeholder", contexteRechercherTousNom);
											}
										}

										if(entiteNomAffichage != null) {
											t(6).dal("title", entiteDescription);
										}
										else {
											t(6).dal("title", entiteDescription);
										}

										t(6).dal("class", str_recherche(langueNom), entiteVarCapitalise, " w3-input w3-border w3-bar-item ");
										t(6).dal("name", entiteVar);
										t(6).da("id", str_recherche(langueNom), entiteVarCapitalise).l(";");
										tl(5, str_operationRequete(langueNom), ".getParams().getJsonObject(\"query\").forEach(param", str_Requete(langueNom), " -> {");
										tl(6, "String entiteVar = null;");
										tl(6, "String ", str_valeurs(langueNom), str_Indexe(langueNom), " = null;");
										tl(6, "String param", str_Nom(langueNom), " = param", str_Requete(langueNom), ".getKey();");
										tl(6, "Object param", str_ValeursObjet(langueNom), " = param", str_Requete(langueNom), ".getValue();");
										tl(6, "JsonArray param", str_Objets(langueNom), " = param", str_ValeursObjet(langueNom), " instanceof JsonArray ? (JsonArray)param", str_ValeursObjet(langueNom), " : new JsonArray().add(param", str_ValeursObjet(langueNom), ");");
										l();
										tl(6, "try {");
										tl(7, "for(Object param", str_Objet(langueNom), " : param", str_Objets(langueNom), ") {");
										tl(8, "switch(paramNom) {");
								
										tl(9, "case \"q\":");
										tl(10, "entiteVar = StringUtils.trim(StringUtils.substringBefore((String)param", str_Objet(langueNom), ", \":\"));");
										tl(10, str_valeur(langueNom), str_Indexe(langueNom), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", str_Objet(langueNom), ", \":\")), \"UTF-8\");");

										tl(10, "if(\"", entiteVar, "\".equals(entiteVar))");
										tl(11, "a(\"value\", URLDecoder.decode(", str_valeur(langueNom), str_Indexe(langueNom), ", \"UTF-8\"));");
										tl(8, "}");
										tl(7, "}");
										tl(6, "} catch(Exception e) {");
										tl(7, "ExceptionUtils.rethrow(e);");
										tl(6, "}");
										tl(5, "});");
										t(5).fgl();
					//					if("Page".equals(classeApiMethodeMethode)) {
					//						wForm.t(tIndex + 5).dal("onchange", "patch", classeNomSimple, "($('#", classeNomSimple, "Form'), $('#", entiteVar, "Form')); ");
					//					}
	
										t(5).be("button").l();
										t(6).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-", contexteCouleur, " ");
										t(6).dfl();
										t(6).e("i").da("class", "fas fa-search ").df().dgl("i");
										t(5).bgl("button");
										t(4).bgl("div");
										t(3).bgl("form");
										t(2).bgl("div");
									}
								}
								rechercheSolr.setStart(i.intValue() + rechercheLignes);
								rechercheReponse = clientSolrComputate.query(rechercheSolr);
								rechercheListe = rechercheReponse.getResults();
							}
						}
					}

					// singulier part 2
					l();
//					tl(2, "if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\") && params.getJsonObject(\"query\").getJsonArray(\"fq\") == null) {");
					tl(2, "if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1 && params.getJsonObject(\"query\").getString(\"q\").equals(\"*:*\")) {");
					t(3).l(classeNomSimple, " o = ", str_liste(langueNom), classeNomSimple, ".first();");
					l();
					t(3).be("div").da("class", "").dfl();
					if(classeVarClePrimaire != null) {
						l();
						tl(4, "if(o.get", StringUtils.capitalize(classeVarClePrimaire), "() != null) {");
						t(5).be("form").da("action", classeApiUri).da("id", classeNomSimple, "Form").da("style", "display: inline-block; ").da("onsubmit", "event.preventDefault(); return false; ").dfl();
						t(6).e("input").l();
						t(6).dal("name", classeVarClePrimaire);
						t(6).dal("class", str_valeur(langueNom), StringUtils.capitalize(classeVarClePrimaire));
						t(6).dal("type", "hidden");
						tl(6, ".a(\"value\", o.get", StringUtils.capitalize(classeVarClePrimaire), "())");
						t(6).dfgl();
						t(5).bgl("form");
						tl(5, "htmlFormPage", classeNomSimple, "(o);");
						tl(4, "}");
					}
					if(classeMethodeVars.contains("htmlBody")) {
						l();
						tl(4, "if(o != null)");
						tl(5, "o.htmlBody();");
					}
					l();
					t(3).bgl("div");
					tl(2, "}");
		
					// formulaires
					tl(2, "htmlBodyForms", classeGenPageNomSimple, "();");
					tl(1, "}");
					l();
					tl(1, "public void htmlBodyForms", classeGenPageNomSimple, "() {");
					if(!classePageSimple) {
						t(2).e("div").dfl();
						l();
						for(String classeApiMethode : classeApiMethodes) {
							String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
							String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
							String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
							String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");
			
							if("application/json".equals(classeApiTypeMediaMethode) && !"GET".equals(classeApiMethodeMethode)) {
								Integer tab = classeApiMethodeMethode.contains("PATCH") || classeApiMethodeMethode.contains("DELETE") || classeApiMethodeMethode.contains("POST") ? 0 : 1;
								String methodeTitreFiltres = null;
								String methodeTitreValeurs = null;
			
								if("POST".equals(classeApiMethodeMethode)) {
									methodeTitreValeurs = str_Creer_(langueNom) + contexteUnNom;
								}
								else if("PUT".equals(classeApiMethodeMethode)) {
									methodeTitreFiltres = str_Rechercher_des_(langueNom) + contexteUnNom;
									methodeTitreValeurs = str_Remplacer_(langueNom) + contexteLeNom;
								}
								else if("PATCH".equals(classeApiMethodeMethode)) {
									methodeTitreFiltres = str_Rechercher_des_(langueNom) + contexteUnNom;
									methodeTitreValeurs = str_Modifier_des_(langueNom) + contexteNomPluriel;
								}
								else if("DELETE".equals(classeApiMethodeMethode)) {
									methodeTitreFiltres = str_Rechercher_des_(langueNom) + contexteUnNom;
									methodeTitreValeurs = str_Supprimer_des_(langueNom) + contexteNomPluriel;
								}
			
			
								l();
								if(tab > 0)
									tl(2, "if(", str_liste(langueNom), classeNomSimple, " != null && ", str_liste(langueNom), classeNomSimple, ".size() == 1) {");
								t(2 + tab).e("button").l();
								t(3 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " ");
								t(3 + tab).dal("onclick", "$('#", classeApiOperationIdMethode, str_Modale(langueNom), "').show(); ");
								t(3 + tab).df().dsxq(methodeTitreValeurs).l();
								t(2 + tab).dgl("button");
								{ t(2 + tab).be("div").da("id", classeApiOperationIdMethode, str_Modale(langueNom)).da("class", "w3-modal ").dfl();
									{ t(3 + tab).be("div").da("class", "w3-modal-content w3-card-4 ").dfl();
										{ t(4 + tab).be("header").da("class", "w3-container w3-", contexteCouleur, " ").dfl();
											t(5 + tab).e("span").da("class", "w3-button w3-display-topright ").da("onclick", "$('#", classeApiOperationIdMethode, str_Modale(langueNom), "').hide(); ").df().dsxq("×").dgl("span");
											t(5 + tab).e("h2").da("class", "").df().dsxq(methodeTitreValeurs).dgl("h2");
										} t(4 + tab).bgl("header");
			
										{ t(4 + tab).be("div").da("class", "w3-container ").dfl();
											tl(5+ tab, classeNomSimple, " o = new ", classeNomSimple, "();");
											if("PATCH".equals(classeApiMethodeMethode)) {
												l();
												t(5 + tab).l("// ", str_FormulaireFiltres(langueNom), " ", classeApiMethodeMethode);
												{ t(5 + tab).be("form").da("action", classeApiUri).da("id", classeApiOperationIdMethode, str_FormulaireFiltres(langueNom)).da("onsubmit", "event.preventDefault(); return false; ").dfl();
												tl(6 + tab, "htmlForm", str_Recherche(langueNom), classeNomSimple, "(o);");
												} t(5 + tab).bgl("form");
												t(5 + tab).e("button").l();
												t(6 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " ");
				
												tl(6 + tab, ".a(\"onclick\", \"", str_recherche(langueNom), classeNomSimple, "($('#", classeApiOperationIdMethode, str_FormulaireFiltres(langueNom), "')); \")");
				
												t(6 + tab).df().dsxq(methodeTitreFiltres).l();
												t(5 + tab).dgl("button");
												l();
												
												l();
												t(5 + tab).l("// ", str_FormulaireValeurs(classePageLangueNom), " ", classeApiMethodeMethode);
												{ t(5 + tab).be("form").da("action", classeApiUri).da("id", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom)).da("onsubmit", "event.preventDefault(); return false; ").dfl();
			
												if("DELETE".equals(classeApiMethodeMethode))
													tl(6 + tab, "htmlFormPATCH", classeNomSimple, "(o);");
												else if("PUT".equals(classeApiMethodeMethode))
													tl(6 + tab, "htmlFormPOST", classeNomSimple, "(o);");
												else
													tl(6 + tab, "htmlForm", classeApiMethodeMethode, classeNomSimple, "(o);");
			
												} t(5 + tab).bgl("form");
												t(5 + tab).e("button").l();
												t(6 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " ");
				
												if("POST".equals(classeApiMethodeMethode))
													tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \")");
												else if("PATCH".equals(classeApiMethodeMethode))
													tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, str_FormulaireFiltres(langueNom), "'), $('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "')); \")");
												else if("PUT".equals(classeApiMethodeMethode))
													tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.getPk(), \", $('#", classeApiOperationIdMethode, "Form')); \")");
												else if(tab > 0)
													tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.getPk(), \"); \")");
												else
													t(6 + tab).dal("onclick", classeApiOperationIdMethode, "(); ");
				
												t(6 + tab).df().dsxq(methodeTitreValeurs).l();
												t(5 + tab).dgl("button");
												l();
											}
											else {
												l();
												t(5 + tab).l("// Form ", classeApiMethodeMethode);
												{ t(5 + tab).be("form").da("action", classeApiUri).da("id", classeApiOperationIdMethode, "Form").da("onsubmit", "event.preventDefault(); return false; ").dfl();
			
												if("DELETE".equals(classeApiMethodeMethode))
													tl(6 + tab, "htmlFormPATCH", classeNomSimple, "(o);");
												else if("PUT".equals(classeApiMethodeMethode))
													tl(6 + tab, "htmlFormPOST", classeNomSimple, "(o);");
												else
													tl(6 + tab, "htmlForm", classeApiMethodeMethode, classeNomSimple, "(o);");
			
												} t(5 + tab).bgl("form");
												t(5 + tab).e("button").l();
												t(6 + tab).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " ");
				
				//								tl(6 + tab, ".a(\"onclick\", \"alert(JSON.stringify($('#", classeApiOperationIdMethode, "Form').serializeArray().reduce(function(a, x) { a[x.name] = x.value; return a; }, {}))); \")");
				//								tl(6 + tab, ".a(\"onclick\", \"alert(JSON.stringify($('#", classeApiOperationIdMethode, "Form').serializeObject())); \")");
				
												if("POST".equals(classeApiMethodeMethode))
													tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \")");
												else if("PATCH".equals(classeApiMethodeMethode))
													tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, str_FormulaireFiltres(langueNom), "'), $('#", classeApiOperationIdMethode, str_FormulaireValeurs(classePageLangueNom), "')); \")");
												else if("PUT".equals(classeApiMethodeMethode))
													tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.getPk(), \", $('#", classeApiOperationIdMethode, "Form')); \")");
												else if(tab > 0)
													tl(6 + tab, ".a(\"onclick\", \"", classeApiOperationIdMethode, "(\", o.getPk(), \"); \")");
												else
													t(6 + tab).dal("onclick", classeApiOperationIdMethode, "(); ");
				
												t(6 + tab).df().dsxq(methodeTitreValeurs).l();
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
				if(classeMethodeVars.contains("htmlBody" + str_Court(langueNom))) {
					tl(1, "@Override public void htmlBodyCourt", classeGenPageNomSimple, "() {");
					l();
					tl(2, StringUtils.uncapitalize(classeNomSimple), ".htmlBody" + str_Court(langueNom) + "();");
					tl(1, "}");
				}
	
				tl(0, "}");
	
				wTh.flushClose();

				auteurPageGenClasse.flushClose();
				System.out.println(str_Ecrire(langueNom) + ": " + classePageCheminGen); 
				if(auteurPageClasse != null) {
					auteurPageClasse.flushClose();
					System.out.println(str_Ecrire(langueNom) + ": " + classePageChemin); 
				}
				auteurPageCss.flushClose();
				System.out.println(str_Ecrire(langueNom) + ": " + classePageCheminCss); 
				auteurPageJs.flushClose();
				System.out.println(str_Ecrire(langueNom) + ": " + classePageCheminJs); 

				{
					RegarderClasse regarderClasse = new RegarderClasse();
					regarderClasse.appliChemin = appliChemin;
					regarderClasse.classeCheminAbsolu = classePageChemin;
					regarderClasse.appliNom = appliNom;
					regarderClasse.initRegarderClasseBase(); 
//					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom);
					RegarderClasse.regarderClasse(regarderClasse, langueNom);
				}

				{
					RegarderClasse regarderClasse = new RegarderClasse();
					regarderClasse.appliChemin = appliChemin;
					regarderClasse.classeCheminAbsolu = classePageCheminGen;
					regarderClasse.appliNom = appliNom;
					regarderClasse.initRegarderClasseBase(); 
//					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom);
					RegarderClasse.regarderClasse(regarderClasse, langueNom);
				}
	
	//		auteurGenPageClasse.close();
			}
		}
	}
}
