/*
 * Copyright (c) 2018-2022 Computate Limited Liability Company in Utah, USA. 
 *
 * This program and the accompanying materials are made available under the
 * terms of the GNU GENERAL PUBLIC LICENSE Version 3 which is available at
 * 
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 * 
 * You may not propagate or modify a covered work except as expressly provided 
 * under this License. Any attempt otherwise to propagate or modify it is void, 
 * and will automatically terminate your rights under this License (including 
 * any patent licenses granted under the third paragraph of section 11).
 */
package org.computate.frFR.java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import org.computate.i18n.I18n;

/**   
 * NomCanonique.enUS: org.computate.enUS.java.WritePageClass
 * 
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */  
public class EcrirePageClasse extends EcrireApiClasse {

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
	 * Param1.var.enUS: languageName
	 * Param3.var.enUS: classApiMethodMethod
	 * r: resultat
	 * r.enUS: result
	 * 
	 * r: classeApiMethodeMethode
	 * r.enUS: classApiMethodMethod
	 * r: rechercheLigneActuelRecherche
	 * r.enUS: searchRowActualSearch
	 * r: entiteDocumentSolr
	 * r.enUS: entitySolrDocument
	 * r: entiteHtmLigne
	 * r.enUS: entityHtmRow
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
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: enleverLueur
	 * r.enUS: removeGlow
	 * r: ajouterErreur
	 * r.enUS: addError
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
	 * r: "suggere"
	 * r.enUS: "suggest"
	 */
	public Boolean ecrireFormEntite(String langueNom, JsonObject langueConfig, ToutEcrivain wForm, String classeApiMethodeMethode) {
		int tIndex = 0;
		Boolean resultat = false;

		if(entiteHtml) {
			// if("POST".equals(classeApiMethodeMethode) && (
			// 		entiteVar.equals(langueConfig.getString(I18n.var_supprime))
			// 		|| entiteVar.equals(langueConfig.getString(I18n.var_archive))
			// 		|| entiteVar.equals(langueConfig.getString(I18n.var_cree))
			// 		|| entiteAttribuer
			// 		)) {
			// } else {
				Boolean ecrireFin = false;
				Boolean nouveauLigne = false;

//				rechercheLigneActuel.put(classeApiMethodeMethode, ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmLigne_stored_int"), 0));
				Integer rechercheLigneActuel = Optional.ofNullable((Integer)entiteDocumentSolr.get("entiteHtmLigne_stored_int")).orElse(0);
				Integer rechercheLigne = Optional.ofNullable(rechercheLigneMap.get(classeApiMethodeMethode)).orElse(-1);
				if(rechercheLigne != rechercheLigneActuel) {
					nouveauLigne = true;
					resultat = true;
					rechercheLigneMap.put(classeApiMethodeMethode, rechercheLigneActuel);
					if(rechercheLigne != -1) {
						ecrireFin = true;
					}
				}
	
				if(nouveauLigne) {
					Boolean entiteHtmLigneVerticaleAncien = entiteHtmLigneVerticaleActuelMap.get(classeApiMethodeMethode);
					Boolean entiteHtmLigneVerticaleActuel = Optional.ofNullable(entiteHtmLigneVerticale).orElse(entiteHtmLigneVerticaleAncien);
					String entiteHtmLigneTitreAncien = Optional.ofNullable(entiteHtmLigneTitreActuelMap.get(classeApiMethodeMethode)).orElse("-1");
					String entiteHtmLigneTitreActuel = Optional.ofNullable(entiteHtmLigneTitre).orElse(entiteHtmLigneTitreAncien);
					if(ecrireFin) {
						if(entiteHtmLigneVerticaleAncien == null || !entiteHtmLigneVerticaleAncien) {
							// wForm.tl(8, "<!-- entiteHtmLigneVerticaleAncien == null || !entiteHtmLigneVerticaleAncien -->");
							wForm.tl(7, "</div>");
							if(entiteHtmLigneTitre != null && !entiteHtmLigneTitre.equals(entiteHtmLigneTitreAncien))
								wForm.tl(6, "</", composantsWebPrefixe, "details>");
						} else if(entiteHtmLigneVerticaleAncien != null && entiteHtmLigneVerticaleAncien) {
							// wForm.tl(10, "<!-- entiteHtmLigneVerticaleAncien != null && entiteHtmLigneVerticaleAncien -->");
							// wForm.tl(9, "</tbody>");
							// wForm.tl(8, "</table>");
							wForm.tl(7, "</div>");
							if(entiteHtmLigneTitre != null && !entiteHtmLigneTitre.equals(entiteHtmLigneTitreAncien))
								wForm.tl(6, "</", composantsWebPrefixe, "details>");
						}
					}
					if(BooleanUtils.isTrue(entiteHtmLigneVerticaleActuel)) {
						entiteHtmLigneVerticaleActuelMap.put(classeApiMethodeMethode, true);
						// wForm.tl(7, "<!-- BooleanUtils.isTrue(entiteHtmLigneVerticale) -->");
						if(!entiteHtmLigneTitreAncien.equals(entiteHtmLigneTitreActuel)) {
							wForm.t(6, "<", composantsWebPrefixe, "details ", entiteHtmLigneTitreOuvert == null ? "" : "open ", "class=\"", i18nGlobale.getString(I18n.var_HtmLigne), "\" id=\"").sx(genererId(entiteHtmLigneTitre)).l("\">");
							wForm.tl(7, "<div slot=\"summary\">");
							wForm.t(8, "<span>");
							wForm.sx(entiteHtmLigneTitre);
							wForm.l("</span>");
							wForm.tl(7, "</div>");
							wForm.tl(7, "<div class=\"grid-with-subgrid-2col \">");
						} else {
							wForm.tl(7, "<div class=\"grid-with-subgrid-2col \">");
						}
						// wForm.tl(8, "<table class=\"w3-table-all \">");
						if(entiteHtmLigneTitre != null) {
							// wForm.tl(9, "<thead>");
							// wForm.tl(10, "<tr>");
							// if("JsonArray".equals(entiteNomSimple)) {
							// 	wForm.tl(10, "<th>");
							// } else {
							// 	wForm.tl(10, "<th colspan=\"3\">");
							// }
							// wForm.t(11, "<span>");
							// wForm.sx(entiteHtmLigneTitre);
							// wForm.l("</span>");
							// wForm.tl(10, "</th>");
							if("JsonArray".equals(entiteNomSimple)) {
								wForm.tl(10, "{% for o in ", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}<th>", entiteHtmLigneEnTeteExpression, "</th>{% endfor %}");
							}
							// wForm.tl(10, "</tr>");
							// wForm.tl(9, "</thead>");
							entiteHtmLigneTitreActuel = entiteHtmLigneTitre;
						}
						entiteHtmLigneTitreActuelMap.put(classeApiMethodeMethode, entiteHtmLigneTitreActuel);
						// wForm.tl(9, "<tbody>");
					} else {
						entiteHtmLigneVerticaleActuelMap.put(classeApiMethodeMethode, false);
						if(!entiteHtmLigneTitreAncien.equals(entiteHtmLigneTitreActuel)) {
							wForm.t(6, "<", composantsWebPrefixe, "details ", entiteHtmLigneTitreOuvert == null ? "" : "open ", "class=\"", i18nGlobale.getString(I18n.var_HtmLigne), "\" id=\"").sx(genererId(entiteHtmLigneTitre)).l("\">");
							wForm.tl(7, "<div slot=\"summary\">");
							wForm.t(8, "<span>");
							wForm.sx(entiteHtmLigneTitre);
							wForm.l("</span>");
							wForm.tl(7, "</div>");
							wForm.tl(7, "<div class=\"grid-with-subgrid-2col \">");
						} else {
							wForm.tl(7, "<div class=\"grid-with-subgrid-2col \">");
						}
						entiteHtmLigneTitreActuelMap.put(classeApiMethodeMethode, entiteHtmLigneTitreActuel);
					}
				}

				if(classeUtilisateurEcrire && classeSessionEcrire) {
					wForm.l();
					wForm.tl(7, "{%- if ", langueConfig.getString(I18n.var_utilisateur), langueConfig.getString(I18n.var_Cle), " in ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(I18n.var_utilisateur), langueConfig.getString(I18n.var_Cle), "s or \"PATCH\" in ", langueConfig.getString(I18n.var_portees), " or ", langueConfig.getString(I18n.var_sessionId), " == ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(I18n.var_sessionId), " %}");
					wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=true, ", langueConfig.getString(I18n.var_authPourLire), "Bool=true) }}");
					wForm.tl(7, "{%- else %}");
					wForm.tl(8, "{%- if ", langueConfig.getString(I18n.var_utilisateur), langueConfig.getString(I18n.var_Cle), " in ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(I18n.var_utilisateur), langueConfig.getString(I18n.var_Cle), "s or \"GET\" in ", langueConfig.getString(I18n.var_portees), " or ", langueConfig.getString(I18n.var_sessionId), " == ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(I18n.var_sessionId), " %}");
					wForm.tl(9, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=false, ", langueConfig.getString(I18n.var_authPourLire), "Bool=true) }}");
					wForm.tl(8, "{%- else %}");
					wForm.tl(9, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=false, ", langueConfig.getString(I18n.var_authPourLire), "Bool=false) }}");
					wForm.tl(8, "{%- endif %}");
					wForm.tl(7, "{%- endif %}");
				}
				else if(classePublicLire) {
					wForm.l();
					wForm.tl(7, "{%- if \"PATCH\" in ", langueConfig.getString(I18n.var_portees), " %}");
					wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=true, ", langueConfig.getString(I18n.var_authPourLire), "Bool=true) }}");
					wForm.tl(7, "{%- else %}");
					wForm.tl(8, "{%- if \"GET\" in ", langueConfig.getString(I18n.var_portees), " %}");
					wForm.tl(9, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=false, ", langueConfig.getString(I18n.var_authPourLire), "Bool=true) }}");
					wForm.tl(8, "{%- else %}");
					wForm.tl(9, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=false, ", langueConfig.getString(I18n.var_authPourLire), "Bool=false) }}");
					wForm.tl(8, "{%- endif %}");
					wForm.tl(7, "{%- endif %}");
				}
				else if(classeUtilisateurEcrire) {
					if(classeAuth) {
						wForm.l();
						wForm.tl(7, "{%- if \"PATCH\" in ", langueConfig.getString(I18n.var_portees), " %}");
						wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=true, ", langueConfig.getString(I18n.var_authPourLire), "Bool=true) }}");
						wForm.tl(7, "{%- else %}");
						wForm.tl(8, "{%- if \"GET\" in ", langueConfig.getString(I18n.var_portees), " %}");
						wForm.tl(9, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=false, ", langueConfig.getString(I18n.var_authPourLire), "Bool=true) }}");
						wForm.tl(8, "{%- else %}");
						wForm.tl(9, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=false, ", langueConfig.getString(I18n.var_authPourLire), "Bool=false) }}");
						wForm.tl(8, "{%- endif %}");
						wForm.tl(7, "{%- endif %}");
					}
					else {
						wForm.l();
						wForm.tl(7, "{%- if ", langueConfig.getString(I18n.var_utilisateur), langueConfig.getString(I18n.var_Cle), " in ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(I18n.var_utilisateur), langueConfig.getString(I18n.var_Cle), "s %}");
						wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=true, ", langueConfig.getString(I18n.var_authPourLire), "Bool=true) }}");
						wForm.tl(7, "{%- else %}");
						wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=false, ", langueConfig.getString(I18n.var_authPourLire), "Bool=false) }}");
						wForm.tl(7, "{%- endif %}");
					}
				}
				else if(classeSessionEcrire) {
					wForm.l();
					wForm.tl(7, "{%- if ", langueConfig.getString(I18n.var_sessionId), " == ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(I18n.var_sessionId), ") }}");
					wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=true, ", langueConfig.getString(I18n.var_authPourLire), "Bool=true) }}");
					wForm.tl(7, "{%- else %}");
					wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=false, ", langueConfig.getString(I18n.var_authPourLire), "Bool=false) }}");
					wForm.tl(7, "{%- endif %}");
				}
				else if(classeAuth) {
					wForm.l();
					wForm.tl(7, "{%- if \"PATCH\" in ", langueConfig.getString(I18n.var_portees), " %}");
					wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=true, ", langueConfig.getString(I18n.var_authPourLire), "Bool=true) }}");
					wForm.tl(7, "{%- else %}");
					wForm.tl(8, "{%- if \"GET\" in ", langueConfig.getString(I18n.var_portees), " %}");
					wForm.tl(9, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=false, ", langueConfig.getString(I18n.var_authPourLire), "Bool=true) }}");
					wForm.tl(8, "{%- else %}");
					wForm.tl(9, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=false, ", langueConfig.getString(I18n.var_authPourLire), "Bool=false) }}");
					wForm.tl(8, "{%- endif %}");
					wForm.tl(7, "{%- endif %}");
				}
			// }
		}
		return resultat;
	}

	public void genCodeEntiteHtm(String langueNom, JsonObject langueConfig, String classeApiMethodeMethode) throws Exception {
		ToutEcrivain oAncien = o;
		o = auteurGenPageJinjaEntite;
		Boolean entiteHtmLigneVerticaleActuel = entiteHtmLigneVerticaleActuelMap.get(classeApiMethodeMethode);

		///////////
		// input //
		///////////

		
		tl(0, "{%- macro input", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), ", ", langueConfig.getString(I18n.var_authPourEcrire), "=", langueConfig.getString(I18n.var_authPourEcrire), ", ", langueConfig.getString(I18n.var_authPourLire), "=", langueConfig.getString(I18n.var_authPourLire), ") %}");
		// if(entiteModifier && (entiteDefinir || entiteAttribuer)) {

			// tl(0, "{%- if ", langueConfig.getString(I18n.var_authPourEcrire), "Bool == true %}");

			tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
			tl(8, "<form class=\"subgrid-2col \" id=\"", langueConfig.getString(I18n.var_Page), langueConfig.getString(I18n.var_Formulaire), "_", entiteVar, "\">");
			tl(1, "{%- endif %}");
			if(entiteAttribuer) {
				tl(1, "{%- if '", langueConfig.getString(I18n.var_PUTCopie), "' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
				tl(9, "<div>");
				tl(10, "<", composantsWebPrefixe, "checkbox ");
				tl(12, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(I18n.var_vider), "\"");
				tl(12, "class=\"label-on-left ", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(I18n.var_vider), " ", entiteVar, "_", langueConfig.getString(I18n.var_vider), " \"");
				tl(12, "help-text=\"", langueConfig.getString(I18n.var_vider), "\"");
				tl(12, ">");
				tl(11, langueConfig.getString(I18n.var_vider));
				tl(10, "</", composantsWebPrefixe, "checkbox>");
				tl(9, "</div>");
				tl(1, "{%- endif %}");

				tl(9, "<", composantsWebPrefixe, "input");
				tl(11, "type=\"text\"");

				if(entiteNomAffichage != null) {
					tl(11, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
				}
				if(entiteDescription != null) {
					t(11, "help-text=\"").sx(entiteDescription).l("\"");
				}

				tl(11, "class=\"label-on-left {{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " ", langueConfig.getString(I18n.var_valeur), StringUtils.capitalize(entiteAttribuerVarSuggere), " ", langueConfig.getString(I18n.var_suggere), entiteVarCapitalise, " \"");
				tl(11, "name=\"", "set", entiteVarCapitalise, "\"");
				tl(11, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				tl(11, "autocomplete=\"off\"");
				t(11, "oninput=\"", langueConfig.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "(this.value ? [ { 'name': 'q', 'value': '", entiteAttribuerVarSuggere, ":' + this.value }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", langueConfig.getString(I18n.var_classeNomCanonique), ",", entiteAttribuerVar, ",", classeVarClePrimaire, entiteAttribuerVarUrlPk == null ? "" : "," + entiteAttribuerVarUrlPk, entiteAttribuerVarTitre == null ? "" : "," + entiteAttribuerVarTitre, "' } ] : [");
				s("{%- if ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " is defined %}{'name':'fq','value':'", entiteAttribuerVar, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}'}{%- else %}{%- endif %}");
				l("], document.querySelector('#list", classeNomSimple, entiteVarCapitalise, "_{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}'), {{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}); \"");
				tl(9, ">");
				tl(7, "</", composantsWebPrefixe, "input>");
				l();
			}
			else if("LocalDate".equals(entiteNomSimple)) {
				tl(9, "<", composantsWebPrefixe, "input");
				tl(11, "type=\"date\"");

				if(entiteModifier || entiteAttribuer) {
					tl(11, "{% if ", langueConfig.getString(I18n.var_authPourEcrire), "Bool == true %}clearable{% else %}readonly{% endif %}");
				} else {
					tl(11, "readonly");
				}

				if(entiteNomAffichage != null) {
					tl(11, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
				}
				if(entiteDescription != null) {
					t(11, "help-text=\"").sx(entiteDescription).l("\"");
				}
				if(entiteNomAffichage != null) {
					t(11, "label=\"").sx(entiteNomAffichage).l("\"");
				}

				tl(11, "class=\"label-on-left {{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " datepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
				tl(11, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				// if(entiteDescription != null)
				// 	tl(16, "title=\"", entiteDescription, " (", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), ")\"");
//				tl(5, "value=\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_ddDashMMDashyyyy), "\").format(", entiteVar, "));");
				tl(11, "value=\"{{", uncapitalizeClasseNomSimple, "_.", entiteVar, " | e }}\"");
				tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
				tl(1, "{%- endif %}");
				tl(11, "data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "=\"{{ ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " }}\"");
				tl(11, ">");
				tl(9, "</", composantsWebPrefixe, "input>");
			}
			else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
				tl(9, "<", composantsWebPrefixe, "input");
				tl(11, "type=\"text\"");

				if(entiteModifier || entiteAttribuer) {
					tl(11, "{% if ", langueConfig.getString(I18n.var_authPourEcrire), "Bool == true %}clearable{% else %}readonly{% endif %}");
				} else {
					tl(11, "readonly");
				}

				if(entiteNomAffichage != null) {
					tl(11, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
				}
				if(entiteDescription != null) {
					t(11, "help-text=\"").sx(entiteDescription).l("\"");
				}
				if(entiteNomAffichage != null) {
					t(11, "label=\"").sx(entiteNomAffichage).l("\"");
				}

				tl(11, "class=\"label-on-left {{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " datetimepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
				tl(11, "placeholder=\"", entiteDefaut == null ? langueConfig.getString(I18n.str_ddDashMMDashyyyy_HHColonmm_VV) : entiteDefaut, "\"");
				tl(11, "data-timeformat=\"", langueConfig.getString(I18n.str_ddDashMMDashyyyy_HHColonmm_VV), "\"");
				tl(11, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
//				tl(4, ".a(\"value\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz_VV), "\").format(", entiteVar, "));");
				tl(11, "value=\"{%- if ", uncapitalizeClasseNomSimple, "_.", entiteVar, " is defined %}{{ formatZonedDateTime(", uncapitalizeClasseNomSimple, "_.", entiteVar, ", \"", langueConfig.getString(I18n.str_ddDashMMDashyyyy_HHColonmm_VV), "\", defaultLocaleId, defaultZoneId) | e }}{%- endif %}\"");
				tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
				tl(1, "{%- endif %}");
				tl(11, "data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "=\"{{ ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " }}\"");
				tl(11, ">");
				tl(9, "</", composantsWebPrefixe, "input>");
			}
			else if("LocalTime".equals(entiteNomSimple)) {
				tl(11, "<", composantsWebPrefixe, "input");
				tl(11, "type=\"text\"");
				tl(11, "class=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " timepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
				tl(11, "placeholder=\"", langueConfig.getString(I18n.var_HHColonMM), "\"");
				tl(11, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

				if(entiteModifier || entiteAttribuer) {
					tl(11, "{% if ", langueConfig.getString(I18n.var_authPourEcrire), "Bool == true %}clearable{% else %}readonly{% endif %}");
				} else {
					tl(11, "readonly");
				}

				if(entiteNomAffichage != null) {
					tl(11, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
				}
				if(entiteDescription != null) {
					t(11, "help-text=\"").sx(entiteDescription).l("\"");
				}
				if(entiteNomAffichage != null) {
					t(11, "label=\"").sx(entiteNomAffichage).l("\"");
				}
				tl(11, "></", composantsWebPrefixe, "input>");

				tl(9, "<", composantsWebPrefixe, "input");
				tl(11, "type=\"text\"");
				tl(11, "class=\"label-on-left timepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
				tl(11, "placeholder=\"", langueConfig.getString(I18n.var_HHColonMM), "\"");
				tl(11, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				tl(11, "value=\"{{", uncapitalizeClasseNomSimple, "_.", entiteVar, " | e }}\"");
				tl(11, "data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "=\"{{ ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " }}\"");
				tl(11, "></", composantsWebPrefixe, "input>");
			}
			else if("Boolean".equals(entiteNomSimple)) {
				if( entiteVar.equals(langueConfig.getString(I18n.var_archive))) {
					tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
					// tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
					tl(9, "<", composantsWebPrefixe, "button");
					tl(11, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

					if(entiteNomAffichage != null) {
						tl(11, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
						tl(11, "label=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
					}
					if(entiteDescription != null) {
						t(11, "help-text=\"").sx(entiteDescription).l("\"");
					}

					tl(11, "class=\"label-on-left {{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
					tl(11, "name=\"set", entiteVarCapitalise, "\"");
					tl(11, "data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "=\"{{ ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " }}\"");
					tl(11, "data-val=\"{{ ", uncapitalizeClasseNomSimple, "_.", entiteVar, " | e }}\"");
					t(11, ">");
					if(entiteNomAffichage != null) {
						sx(entiteNomAffichage);
					}
					l("</", composantsWebPrefixe, "button>");
					tl(1, "{%- else %}");
				}

				tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
				tl(9, "<", composantsWebPrefixe, "checkbox");
				tl(11, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				tl(11, "value=\"true\"");
				tl(1, "{%- else %}");
				tl(9, "<", composantsWebPrefixe, "select");
				tl(11, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				tl(1, "{%- endif %}");

				if(entiteModifier || entiteAttribuer) {
					tl(11, "{% if ", langueConfig.getString(I18n.var_authPourEcrire), "Bool == true %}clearable{% else %}readonly{% endif %}");
				} else {
					tl(11, "readonly");
				}

				if(entiteNomAffichage != null) {
					tl(11, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
					tl(11, "label=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
				}
				if(entiteDescription != null) {
					t(11, "help-text=\"").sx(entiteDescription).l("\"");
				}

				tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
				tl(11, "class=\"label-on-left {{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
				tl(11, "name=\"set", entiteVarCapitalise, "\"");
				tl(1, "{%- else %}");
				tl(2, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
				tl(11, "class=\"label-on-left {{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
				tl(11, "name=\"set", entiteVarCapitalise, "\"");
				tl(2, "{%- else %}");
				tl(11, "class=\"label-on-left {{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " set", entiteVarCapitalise, " ", langueConfig.getString(I18n.var_valeur), entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
				tl(11, "name=\"set", entiteVarCapitalise, "\"");
				tl(2, "{%- endif %}");
				tl(1, "{%- endif %}");

				tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
				tl(2, "{%- if ", uncapitalizeClasseNomSimple, "_.", entiteVar, " == true %}");
				tl(11, "checked");
				tl(2, "{%- endif %}");
				tl(11, "data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "=\"{{ ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " }}\"");
				t(11, ">");
				if(entiteNomAffichage != null) {
					sx(entiteNomAffichage);
				}
				l("</", composantsWebPrefixe, "checkbox>");
				tl(1, "{%- else %}");
				tl(11, ">");
				tl(10, "<", composantsWebPrefixe, "option value=\"\" selected=\"selected\"></", composantsWebPrefixe, "option>");
				tl(10, "<", composantsWebPrefixe, "option value=\"true\">true</", composantsWebPrefixe, "option>");
				tl(10, "<", composantsWebPrefixe, "option value=\"false\">false</", composantsWebPrefixe, "option>");
				tl(9, "</", composantsWebPrefixe, "select>");
				tl(1, "{%- endif %}");
				if(entiteVar.equals(langueConfig.getString(I18n.var_archive))) {
					tl(1, "{%- endif %}");
				}
			}
			else if(entiteImageBase64Url != null) {
				tl(9, "<div class=\"imageBase64Div1", classeNomSimple, "_", entiteVar, "\" id=\"imageBase64Div1", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\">");

				tl(10, "<h5>", langueConfig.getString(I18n.str_Télécharger_image), "</h5>");
				tl(10, "<form method=\"POST\" enctype=\"multipart/form-data\" action=\"", entiteImageBase64Url, "\" class=\"\">");
				tl(11, "<input type=\"hidden\" name=\"", classeModele ? classeVarClePrimaire : classeVarCleUnique, "\" value=\"{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, " | e }}\"/>");
				tl(11, "<input type=\"hidden\" name=\"", langueConfig.getString(I18n.var_classeNomSimple), "\" value=\"", classeNomSimple, "\"/>");
				tl(11, "<", composantsWebPrefixe, "input name=\"", langueConfig.getString(I18n.var_fichier), "\" type=\"file\" onchange=\"fetch('", entiteImageBase64Url, "', { method: 'POST', body: new FormData(this.form)}); \"></", composantsWebPrefixe, "input>");
				tl(10, "</form>");

				tl(10, "<img id=\"imageBase64Img", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\");");
				tl(12, "class=\"img", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
				tl(12, "src=\"{%- if ", entiteVar, " is defined %}data:image/png;base64,{%- else %}{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}{%- endif %} alt=\"\"");
				tl(12, "/>");

				tl(9, "</div>");
			}
			else if(BooleanUtils.isTrue(entiteSignature)) {
				tl(9, "<div class=\"signatureDiv1", classeNomSimple, "_", entiteVar, " signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, "\" id=\"signatureDiv1", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\">");

				tl(10, "<div id=\"signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\"");
				tl(12, "style=\"display: {%- if ", entiteVar, " is defined %}block{%- else %}none{%- endif %}\"");
				tl(10, "</div>");

				tl(10, "<img id=\"signatureImg", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\"");
				tl(12, "class=\"signatureImg", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, "\"");
				tl(12, "src=\"{%- if ", entiteVar, " is defined %}data:image/png;base64{%- else %}{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}\" alt=\"\"");
				tl(12, "style=\"padding: 10px; display: {%- if ", entiteVar, " is defined %}none{%- else %}block{%- endif %}\"");
				tl(12, "/>");

				tl(9, "<div>");
				tl(9, "<div id=\"signatureDiv2", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\">");

				tl(10, "<button id=\"signatureButton", langueConfig.getString(I18n.var_Vider), classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\"");
				tl(12, "onclick=\"");
				tl(14, "document.querySelector('#signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "').classList.remove('display-none'); ");
				tl(14, "document.querySelector('#signatureImg", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "').classList.add('display-none'); ");
				tl(14, langueConfig.getString(I18n.var_enleverLueur), "(document.querySelector('#signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "')); ");
				tl(14, "patch{{", langueConfig.getString(I18n.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', null, this); ");
				tl(14, "if(document.querySelector('#signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "')) { ");
				tl(14, "document.querySelector('#signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "').jSignature('reset'); ");
				tl(14, "} else { ");
				tl(14, "document.querySelector('#signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "').jSignature({'height':200}); ");
				tl(14, "}");
				tl(12, "\"");
				tl(12, ">", langueConfig.getString(I18n.var_Vider));
				tl(10, "</button>");

				tl(9, "</div>");

			}
			else {
				if(entiteLien) {
					tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
					tl(9, "<", composantsWebPrefixe, "button");
					tl(11, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

					if(entiteNomAffichage != null) {
						tl(11, "placeholder=\"[", entiteNomSimple, "] ", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
						tl(11, "label=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
					}
					if(entiteDescription != null) {
						t(11, "help-text=\"").sx(entiteDescription).l("\"");
					}

					tl(11, "class=\"button-on-left {{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
					tl(11, "name=\"set", entiteVarCapitalise, "\"");
					tl(11, "href=\"{{ ", uncapitalizeClasseNomSimple, "_.", entiteVar, " | e }}\"");
					t(11, ">");
					if(entiteNomAffichage != null) {
						sx(entiteNomAffichage);
					}
					l("</", composantsWebPrefixe, "button>");
					t(11, "<div class=\"button-description-on-right \">");
						sx(entiteDescription);
					l("</div>");
					tl(1, "{%- else %}");
				}
				if(entiteMultiligne)
					tl(9, "<", composantsWebPrefixe, "textarea resize=\"auto\"");
				else {
					tl(9, "<", composantsWebPrefixe, "input");
				}

				if(entiteModifier || entiteAttribuer) {
					tl(11, "{% if ", langueConfig.getString(I18n.var_authPourEcrire), "Bool == true %}clearable{% else %}readonly{% endif %}");
				} else {
					tl(11, "readonly");
				}

				if(entiteNomAffichage != null) {
					tl(11, "placeholder=\"[", entiteNomSimple, "] ", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
					tl(11, "label=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
				}
				if(entiteDescription != null) {
					tl(11, "help-text=\"").sx(entiteDescription).l("\"");
				}
				if(entiteRequis) {
					tl(11, "required");
				}
				tl(11, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

				tl(1, "{%- if \"Page\" == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
					tl(11, "data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "=\"{{ ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " }}\"");
					tl(11, "class=\"label-on-left {{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
					tl(11, "name=\"set", entiteVarCapitalise, "\"");
				tl(1, "{%- else %}");
				tl(2, "{%- if \"PATCH\" == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
				tl(11, "class=\"label-on-left {{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
				tl(11, "name=\"set", entiteVarCapitalise, "\"");
				tl(2, "{%- else %}");
					tl(11, "class=\"label-on-left {{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " ", langueConfig.getString(I18n.var_valeur), entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \"");
					tl(11, "name=\"", entiteVar, "\"");
				tl(2, "{%- endif %}");
				tl(1, "{%- endif %}");

				if(entiteMultiligne) {
					tl(0, "{%- if \"Page\" == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
					if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson))
						tl(11, "value=\"{{ to", entiteNomSimpleVertxJson, "String(", uncapitalizeClasseNomSimple, "_.", entiteVar, ") | e }}\"");
					else
						tl(11, "value=\"{{", uncapitalizeClasseNomSimple, "_.", entiteVar, " | e }}\"");
					tl(0, "{%- endif %}");
					tl(11, ">");
				}
				else {
					tl(1, "{%- if \"Page\" == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
					if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson))
						tl(11, "value=\"{{ to", entiteNomSimpleVertxJson, "String(", uncapitalizeClasseNomSimple, "_.", entiteVar, ") | e }}\"");
					else
						tl(11, "value=\"{{", uncapitalizeClasseNomSimple, "_.", entiteVar, " | e }}\"");
					tl(1, "{%- endif %}");
				}

				if(entiteMultiligne)
					s("</", composantsWebPrefixe, "textarea>");
				else
					tl(11, "></", composantsWebPrefixe, "input>");

				l();
				if(entiteLien) {
					tl(1, "{%- endif %}");
				}
			}

			tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
			tl(8, "</form>");
			tl(1, "{%- endif %}");

// 			if(entiteAttribuer) {
// 				// tl(1, "{%- else %}");
// 			}
// 			else if(classeUtilisateurEcrire && classeSessionEcrire || classePublicLire) {
// 				// tl(1, "{%- else %}");
// 				tl(8, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}", (entiteVarUrl == null ? "</span>" : "</a>"));
// 			}
// 			else if(classeUtilisateurEcrire) {
// 				if(classeRolesTrouves || classeRoleLiresTrouves) {
// 					// tl(1, "{%- else %}");
// 					tl(8, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
// 				}
// 				else {
// 					// tl(1, "{%- else %}");
// 					tl(8, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
// 				}
// 			}
// 			else if(classeSessionEcrire) {
// 				// tl(1, "{%- else %}");
// 				tl(8, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
// 			}
// 			else if(classeRolesTrouves || classeRoleLiresTrouves) {
// 					// tl(1, "{%- else %}");
// 				tl(1, "{%- if ", langueConfig.getString(I18n.var_utilisateur), langueConfig.getString(I18n.var_Cle), " in ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(I18n.var_utilisateur), langueConfig.getString(I18n.var_Cle), "s %}");
// 				tl(8, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
// 				tl(1, "{%- endif %}");
// 			}
// 			else {
// //								tl(3, "sx(htm", entiteVarCapitalise, "());");
// 			}

			// tl(0, "{%- else %}");
			// 	tl(6, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
			// tl(0, "{%- endif %}");
		// }
		// else {
		// 	tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
		// 	if(entiteAttribuer) {
		// 		tl(2, "{%- if '", langueConfig.getString(I18n.var_PUTCopie), "' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
		// 		tl(6, "<div>");
		// 		tl(7, "<", composantsWebPrefixe, "checkbox ");
		// 		tl(9, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(I18n.var_vider), "\"");
		// 		tl(9, "class=\"", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(I18n.var_vider), " ", entiteVar, "_", langueConfig.getString(I18n.var_vider), " \"");
		// 		tl(9, "help-text=\"", langueConfig.getString(I18n.var_vider), "\"");
		// 		tl(9, ">");
		// 		tl(8, langueConfig.getString(I18n.var_vider));
		// 		tl(7, "</", composantsWebPrefixe, "checkbox>");
		// 		tl(6, "</div>");
		// 		tl(2, "{%- endif %}");

		// 		tl(6, "<", composantsWebPrefixe, "input");
		// 		tl(8, "type=\"text\"");

		// 		if(entiteNomAffichage != null) {
		// 			tl(8, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
		// 		}
		// 		if(entiteDescription != null) {
		// 			t(8, "help-text=\"").sx(entiteDescription).l("\"");
		// 		}
		// 		if(entiteNomAffichage != null) {
		// 			t(8, "label=\"").sx(entiteNomAffichage).l("\"");
		// 		}

		// 		tl(8, "class=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " ", langueConfig.getString(I18n.var_valeur), StringUtils.capitalize(entiteAttribuerVarSuggere), " ", langueConfig.getString(I18n.var_suggere), entiteVarCapitalise, " \"");
		// 		tl(8, "name=\"", "set", entiteVarCapitalise, "\"");
		// 		tl(8, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
		// 		tl(8, "autocomplete=\"off\"");
		// 		t(8, "oninput=\"", langueConfig.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "(this.value ? [ { 'name': 'q', 'value': '", entiteAttribuerVarSuggere, ":' + this.value }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", langueConfig.getString(I18n.var_classeNomCanonique), ",", entiteAttribuerVar, ",", classeVarClePrimaire, entiteAttribuerVarUrlPk == null ? "" : "," + entiteAttribuerVarUrlPk, entiteAttribuerVarTitre == null ? "" : "," + entiteAttribuerVarTitre, "' } ] : [");
		// 		s("{%- if ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " is defined %}{'name':'fq','value':'", entiteAttribuerVar, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}'}{%- else %}{%- endif %}");
		// 		l("], document.querySelector('#list", classeNomSimple, entiteVarCapitalise, "_{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}'), {{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}); \"");
		// 		tl(8, "></", composantsWebPrefixe, "input>");
		// 		l();
		// 	} else if("LocalDateTime".equals(entiteNomSimple)) {
		// 		tl(6, (entiteVarUrl == null ? "<span id=\"{{" + langueConfig.getString(I18n.var_classeApiMethodeMethode) + "}}_" + entiteVar + "\"" : "<a href=\"{{ " + uncapitalizeClasseNomSimple + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \" title=\"{{ formatLocalDateTime(", uncapitalizeClasseNomSimple, "_.", entiteVar, ", 'EEEE MMMM d yyyy H:mm:ss.SSS zz VV', defaultLocaleId, defaultZoneId) }}\">{{ formatZonedDateTime(", uncapitalizeClasseNomSimple, "_.", entiteVar, ", 'EEE MMM d yyyy', defaultLocaleId, defaultZoneId) }}", (entiteVarUrl == null ? "</span>" : "</a>"));
		// 	} else if("ZonedDateTime".equals(entiteNomSimple)) {
		// 		tl(6, (entiteVarUrl == null ? "<span id=\"{{" + langueConfig.getString(I18n.var_classeApiMethodeMethode) + "}}_" + entiteVar + "\"" : "<a href=\"{{ " + uncapitalizeClasseNomSimple + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \" title=\"{{ formatZonedDateTime(", uncapitalizeClasseNomSimple, "_.", entiteVar, ", 'EEEE MMMM d yyyy H:mm:ss.SSS zz VV', defaultLocaleId, defaultZoneId) }}\">{{ formatZonedDateTime(", uncapitalizeClasseNomSimple, "_.", entiteVar, ", 'EEE MMM d yyyy h:mm a zz', defaultLocaleId, defaultZoneId) }}", (entiteVarUrl == null ? "</span>" : "</a>"));
		// 	} else {
		// 		tl(6, (entiteVarUrl == null ? "<span id=\"{{" + langueConfig.getString(I18n.var_classeApiMethodeMethode) + "}}_" + entiteVar + "\"" : "<a href=\"{{ " + uncapitalizeClasseNomSimple + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}", (entiteVarUrl == null ? "</span>" : "</a>"));
		// 	}
		// 	tl(0, "{%- endif %}");
		// }
		tl(0, "{%- endmacro %}");

		/////////
		// htm //
		/////////

		l();
		l("{%- macro htm", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), ", ", langueConfig.getString(I18n.var_authPourEcrire), "Bool=false, ", langueConfig.getString(I18n.var_authPourLire), "Bool=false) %}");
		if(entiteHtml) {

			if(entiteAttribuer) {
				if(entiteNomAffichage != null) {
					tl(12, "<div>");
					tl(13, "<a href=\"", entiteAttribuerPageUri, "?fq=", entiteAttribuerVar, ":{{", uncapitalizeClasseNomSimple, "_.", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}\">");
					if(entiteAttribuerContexteIcone != null)
						tl(14, entiteAttribuerContexteIcone);
					tl(14, entiteNomAffichage);
					tl(13, "</a>");
					tl(12, "</div>");
				}
				tl(12, "<div>");
				tl(13, "<h5>");
				tl(14, "<i class=\"far fa-search \"></i>");
				tl(14, langueConfig.getString(I18n.var_relier), " ", entiteListeTypeJson == null ? entiteAttribuerContexteUnNom : entiteAttribuerContexteNomPluriel, " ", langueConfig.getString(I18n.var_a), " ", classeCeNom);
				tl(13, "</h5>");
				tl(12, "</div>");
				tl(12, "<div>");
				tl(13, "<div>");
				tl(14, "<div>");
				l();

				tl(14, "{{ input", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_authPourEcrire), "=", langueConfig.getString(I18n.var_authPourEcrire), ", ", langueConfig.getString(I18n.var_authPourLire), "=", langueConfig.getString(I18n.var_authPourLire), "}}");
				tl(14, "</div>");
				tl(13, "</div>");
				tl(12, "</div>");
				tl(12, "<div>");
				tl(13, "<div>");

				tl(14, "<ul id=\"list", classeNomSimple, entiteVarCapitalise, "_{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}\">");
				tl(14, "</ul>");


				tl(14, "{%- if ", langueConfig.getString(I18n.var_authPourLire), "Bool == true %}");

				tl(1, "{{ if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
				tl(16, "<div>");
				tl(17, "<button");
				tl(18, " id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(I18n.var_ajouter), "\"");

				if("array".equals(entiteAttribuerTypeJson))
					t(18, " onclick=\"this.classList.add('w3-disabled'); this.disabled = true; this.innerHTML = '", langueConfig.getString(I18n.var_Envoi), "…'; post", entiteAttribuerNomSimple, "Vals({ ", entiteAttribuerVar, ": [ '{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' ] }");
				else
					t(18, " onclick=\"this.classList.add('w3-disabled'); this.disabled = true; this.innerHTML = '", langueConfig.getString(I18n.var_Envoi), "…'; post", entiteAttribuerNomSimple, "Vals({ ", entiteAttribuerVar, ": '{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }");
				s(", this");
				s(", function(", langueConfig.getString(I18n.var_reponse), ", target) { ");
				s("document.querySelector('#{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(I18n.var_ajouter), "').disabled = false; ");
				s("document.querySelector('#{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(I18n.var_ajouter), "').innerHTML = '", langueConfig.getString(I18n.var_ajouter), " ", entiteAttribuerContexteUnNom, "';");
				s(" }");
				s(", this, function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterErreur), "(document.querySelector('#{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}", entiteVar, "')); });");
				s("\"");
				l();

				tl(18, ">", langueConfig.getString(I18n.var_ajouter), " ", entiteAttribuerContexteUnNom, "</button>");
				tl(16, "</div>");
				tl(1, "{%- endif %}");

				tl(14, "{%- endif %}");

				tl(13, "</div>");
				tl(12, "</div>");
			}
			else if("LocalDate".equals(entiteNomSimple)) {
				if(entiteHtmLigneVerticaleActuel) {
				}
				tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "=", langueConfig.getString(I18n.var_classeApiMethodeMethode), ", ", langueConfig.getString(I18n.var_authPourEcrire), "=", langueConfig.getString(I18n.var_authPourEcrire), ", ", langueConfig.getString(I18n.var_authPourLire), "=", langueConfig.getString(I18n.var_authPourLire), ") }}");
			}
			else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
				tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "=", langueConfig.getString(I18n.var_classeApiMethodeMethode), ", ", langueConfig.getString(I18n.var_authPourEcrire), "=", langueConfig.getString(I18n.var_authPourEcrire), ", ", langueConfig.getString(I18n.var_authPourLire), "=", langueConfig.getString(I18n.var_authPourLire), ") }}");
			}
			else if("LocalTime".equals(entiteNomSimple)) {
				tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "=", langueConfig.getString(I18n.var_classeApiMethodeMethode), ", ", langueConfig.getString(I18n.var_authPourEcrire), "=", langueConfig.getString(I18n.var_authPourEcrire), ", ", langueConfig.getString(I18n.var_authPourLire), "=", langueConfig.getString(I18n.var_authPourLire), ") }}");
			}
			else if("Boolean".equals(entiteNomSimple)) {
				tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "=", langueConfig.getString(I18n.var_classeApiMethodeMethode), ", ", langueConfig.getString(I18n.var_authPourEcrire), "=", langueConfig.getString(I18n.var_authPourEcrire), ", ", langueConfig.getString(I18n.var_authPourLire), "=", langueConfig.getString(I18n.var_authPourLire), ") }}");
			} else if("JsonArray".equals(entiteNomSimple)) {
				if(entiteHtmLigneEnTeteExpression == null) {
					tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "=", langueConfig.getString(I18n.var_classeApiMethodeMethode), ", ", langueConfig.getString(I18n.var_authPourEcrire), "=", langueConfig.getString(I18n.var_authPourEcrire), ", ", langueConfig.getString(I18n.var_authPourLire), "=", langueConfig.getString(I18n.var_authPourLire), ") }}");
				} else {
					tl(14, "{%- for item in ", uncapitalizeClasseNomSimple, "_.", entiteVar, " %}<td>{{ item }}</td>{%- endfor %}");
				}
			}
			else {
				tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", langueConfig.getString(I18n.var_classeApiMethodeMethode), "=", langueConfig.getString(I18n.var_classeApiMethodeMethode), ", ", langueConfig.getString(I18n.var_authPourEcrire), "=", langueConfig.getString(I18n.var_authPourEcrire), ", ", langueConfig.getString(I18n.var_authPourLire), "=", langueConfig.getString(I18n.var_authPourLire), ") }}");
			}
		}

		l("{%- endmacro %}");  

		o = oAncien;
	}

	public void pageCodeClasseJava(String langueNom, JsonObject langueConfig) throws Exception {

		classeVarClePrimaire = classeDoc.getString("classeVarClePrimaire"   + "_" + langueNom + "_stored_string");
		classeGenPageChemin = classeDoc.getString("classeGenPageChemin"   + "_" + langueNom + "_stored_string");
		classePageChemin = classeDoc.getString("classePageChemin"   + "_" + langueNom + "_stored_string");
		classePageCheminCss = classeDoc.getString("classePageCheminCss"   + "_" + langueNom + "_stored_string");
		classePageCheminJs = classeDoc.getString("classePageCheminJs"   + "_" + langueNom + "_stored_string");
		classePageCheminJsModule = classeDoc.getString("classePageCheminJsModule"   + "_" + langueNom + "_stored_string");
		classeApiUri = classeDoc.getString("classeApiUri" + "_" + langueNom + "_stored_string");
		classePageUriMethode = classeDoc.getString("classeApiUri" + langueConfig.getString(I18n.var_PageRecherche) + "_" + langueNom + "_stored_string");
		classePageLangueNom = classeDoc.getString("classePageLangueNom"  + "_" + langueNom + "_stored_string");
		classeModele = (Boolean)classeDoc.getBoolean("classeModele_stored_boolean");
		classePageLangueConfig = null;
		if(classePageLangueNom != null) {
			classePageLangueConfig = langueConfig;
		}

		classePageNomSimple = classeDoc.getString("classePageNomSimple"   + "_" + langueNom + "_stored_string");
		classePageSuperNomSimple = classeDoc.getString("classePageSuperNomSimple"   + "_" + langueNom + "_stored_string");
		classeApiClasseNomSimple = classeDoc.getString("classeApiClasseNomSimple"   + "_" + langueNom + "_stored_string");
		uncapitalizeClasseApiClasseNomSimple = StringUtils.uncapitalize(classeApiClasseNomSimple);
		classeGenPageNomSimple = classeDoc.getString("classeGenPageNomSimple"   + "_" + langueNom + "_stored_string");
		classePageNomCanonique = classeDoc.getString("classePageNomCanonique"   + "_" + langueNom + "_stored_string");
		classeAttribuerNomSimplePages = Optional.ofNullable(classeDoc.getJsonArray("classeAttribuerNomSimplePages_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeAttribuerNomSimples = Optional.ofNullable(classeDoc.getJsonArray("classeAttribuerNomSimple_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());

		if(!classePageCheminsGen.contains(classeGenPageChemin) && classeGenPageChemin != null && StringUtils.equals(classePageLangueNom, langueNom)) {
			classePageCheminsGen.add(classeGenPageChemin);

			classeImageLargeur = (Integer)classeDoc.getInteger("classeImageLargeur" + "_" + langueNom + "_stored_int");
			classeImageHauteur = (Integer)classeDoc.getInteger("classeImageHauteur" + "_" + langueNom + "_stored_int");
			classeVideoId = classeDoc.getString("classeVideoId" + "_" + langueNom + "_stored_string");
			classeUnNom = classeDoc.getString("classeUnNom" + "_" + langueNom + "_stored_string");
			classeNomSingulier = classeDoc.getString("classeNomSingulier" + "_" + langueNom + "_stored_string");
			classeNomPluriel = classeDoc.getString("classeNomPluriel" + "_" + langueNom + "_stored_string");
			classeNomVar = classeDoc.getString("classeNomVar" + "_" + langueNom + "_stored_string");
			classeAdjectif = classeDoc.getString("classeAdjectif" + "_" + langueNom + "_stored_string");
			classeAdjectifPluriel = classeDoc.getString("classeAdjectifPluriel" + "_" + langueNom + "_stored_string");
			classeAdjectifVar = classeDoc.getString("classeAdjectifVar" + "_" + langueNom + "_stored_string");
			classeNomAdjectifSingulier = classeDoc.getString("classeNomAdjectifSingulier" + "_" + langueNom + "_stored_string");
			classeNomAdjectifPluriel = classeDoc.getString("classeNomAdjectifPluriel" + "_" + langueNom + "_stored_string");
			classeCe = classeDoc.getString("classeCe" + "_" + langueNom + "_stored_string");
			classeUn = classeDoc.getString("classeUn" + "_" + langueNom + "_stored_string");
			classeNomActuel = classeDoc.getString("classeNomActuel" + "_" + langueNom + "_stored_string");
			classeTous = classeDoc.getString("classeTous" + "_" + langueNom + "_stored_string");
			classeTousNom = classeDoc.getString("classeTousNom" + "_" + langueNom + "_stored_string");
			classeLesNoms = classeDoc.getString("classeLesNoms" + "_" + langueNom + "_stored_string");
			classeTitre = classeDoc.getString("classeTitre" + "_" + langueNom + "_stored_string");
			classeHtmInfobulle = classeDoc.getString("classeHtmInfobulle" + "_" + langueNom + "_stored_string");
			classeJsInfobulle = classeDoc.getString("classeJsInfobulle" + "_" + langueNom + "_stored_string");
			classeHtmLegende = classeDoc.getString("classeHtmLegende" + "_" + langueNom + "_stored_string");
			classeJsLegende = classeDoc.getString("classeJsLegende" + "_" + langueNom + "_stored_string");
			classeJsPATCH = classeDoc.getString("classeJsPATCH" + "_" + langueNom + "_stored_string");
			classeJsWebsocket = classeDoc.getString("classeJsWebsocket" + "_" + langueNom + "_stored_string");
			classeH1 = classeDoc.getString("classeH1" + "_" + langueNom + "_stored_string");
			classeH2 = classeDoc.getString("classeH2" + "_" + langueNom + "_stored_string");
			classeH3 = classeDoc.getString("classeH3" + "_" + langueNom + "_stored_string");
			classeAucunNomTrouve = classeDoc.getString("classeAucunNomTrouve" + "_" + langueNom + "_stored_string");
			classeUnNomAdjectif = classeDoc.getString("classeUnNomAdjectif" + "_" + langueNom + "_stored_string");
			classeCeNom = classeDoc.getString("classeCeNom" + "_" + langueNom + "_stored_string");
			classeLeNom = classeDoc.getString("classeLeNom" + "_" + langueNom + "_stored_string");
			classeDeNom = classeDoc.getString("classeDeNom" + "_" + langueNom + "_stored_string");
			classeVarTitre = classeDoc.getString("classeVarTitre" + "_" + langueNom + "_stored_string");
			classeVarH1 = classeDoc.getString("classeVarH1" + "_" + langueNom + "_stored_string");
			classeVarH2 = classeDoc.getString("classeVarH2" + "_" + langueNom + "_stored_string");
			classeVarH3 = classeDoc.getString("classeVarH3" + "_" + langueNom + "_stored_string");
			classeVarUrlId = classeDoc.getString("classeVarUrlId" + "_" + langueNom + "_stored_string");
			classeVarUrlPk = classeDoc.getString("classeVarUrlPk" + "_" + langueNom + "_stored_string");
			classeVarSuggere = classeDoc.getString("classeVarSuggere" + "_" + langueNom + "_stored_string");
			classeVarTexte = classeDoc.getString("classeVarTexte" + "_" + langueNom + "_stored_string");

			auteurWebsocket = ToutEcrivain.create("  ");

			wRecherche = ToutEcrivain.create("  ");
			wVarsFqJs = ToutEcrivain.create("  ");
			wPOST = ToutEcrivain.create("  ");
			wPUTImport = ToutEcrivain.create("  ");
			wPUTFusion = ToutEcrivain.create("  ");
			wPUTCopie = ToutEcrivain.create("  ");
			wPATCH = ToutEcrivain.create("  ");
			wSuggere = ToutEcrivain.create("  ");
			wTh = ToutEcrivain.create("  ");
			wTd = ToutEcrivain.create("  ");
			wFoot = ToutEcrivain.create("  ");


			wForms = new ArrayList<>();
			wClasseApiMethodeMethodes = new ArrayList<>();

			wFormRecherche = ToutEcrivain.create("  ");
			wForms.add(wFormRecherche);
			wClasseApiMethodeMethodes.add(langueConfig.getString(I18n.var_Recherche));

			wFormPOST = ToutEcrivain.create("  ");
			wForms.add(wFormPOST);
			wClasseApiMethodeMethodes.add("POST");

			wFormDELETE = ToutEcrivain.create("  ");
			wForms.add(wFormDELETE);
			wClasseApiMethodeMethodes.add("DELETE");

			wFormPUTImport = ToutEcrivain.create("  ");
			wForms.add(wFormPUTImport);
			wClasseApiMethodeMethodes.add("PUTImport");

			wFormPUTFusion = ToutEcrivain.create("  ");
			wForms.add(wFormPUTFusion);
			wClasseApiMethodeMethodes.add(langueConfig.getString(I18n.var_PUTFusion));

			wFormPUTCopie = ToutEcrivain.create("  ");
			wForms.add(wFormPUTCopie);
			wClasseApiMethodeMethodes.add(langueConfig.getString(I18n.var_PUTCopie));

			wFormPage = ToutEcrivain.create("  ");
			wForms.add(wFormPage);
			wClasseApiMethodeMethodes.add("Page");

			wFormPATCH = ToutEcrivain.create("  ");
			wForms.add(wFormPATCH);
			wClasseApiMethodeMethodes.add("PATCH");

			wJsInit = ToutEcrivain.create("  ");
			wJsModuleInit = ToutEcrivain.create("  ");
			wWebsocket = ToutEcrivain.create("  ");
			wWebsocketInput1 = ToutEcrivain.create("  ");
			wWebsocketInput2 = ToutEcrivain.create("  ");
			wWebsocketInput3 = ToutEcrivain.create("  ");
			wPks = ToutEcrivain.create("  ");


			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + classesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr.addFilterQuery("entiteEstSubstitue_indexed_boolean:false");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
//					rechercheSolr.addFilterQuery("entiteHtmLigne_indexed_int:[* TO *]");
				rechercheSolr.addSort("entiteHtmLigne_indexed_int", ORDER.asc);
				rechercheSolr.addSort("entiteHtmCellule_indexed_int", ORDER.asc);
				rechercheSolr.add("stats", "true");
				rechercheSolr.add("stats.field", "entiteHtmLigne_indexed_int");
				QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList rechercheListe = rechercheReponse.getResults();

				rechercheLignes = rechercheSolr.getRows();

//				rechercheLigneActuel = new HashMap<String, Integer>();
				rechercheLigneMap = new HashMap<String, Integer>();
				entiteHtmLigneTitreActuelMap = new HashMap<String, String>();
				entiteHtmLigneVerticaleActuelMap = new HashMap<String, Boolean>();

				List<String> pageVars = Arrays.asList("pageH1", "pageH2", "pageH3", "pageTitre");

				if(rechercheListe.size() > 0) {
					Boolean resultatFormPOST = false; 
					Boolean resultatFormDELETE = false; 
					Boolean resultatFormPUTImport = false; 
					Boolean resultatFormPUTFusion = false; 
					Boolean resultatFormPUTCopie = false; 
					Boolean resultatFormPage = false; 
					Boolean resultatFormPATCH = false; 
					Boolean resultatFormRecherche = false; 

					//STUFF2
					o = auteurGenPageEditionJinja;
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
							entiteHtml = (Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean");
							entiteHtmLigne = (Integer)entiteDocumentSolr.get("entiteHtmLigne_stored_int");
							entiteHtmLigneTitre = (String)entiteDocumentSolr.get("entiteHtmLigneTitre_stored_string");
							entiteHtmLigneTitreOuvert = (String)entiteDocumentSolr.get("entiteHtmLigneTitreOuvert_stored_string");
							entiteHtmLigneVerticale = (Boolean)entiteDocumentSolr.get("entiteHtmLigneVerticale_stored_boolean");
							entiteHtmLigneEnTeteExpression = (String)entiteDocumentSolr.get("entiteHtmLigneEnTeteExpression_stored_string");
							entiteHtmCellule = (Integer)entiteDocumentSolr.get("entiteHtmCellule_stored_int");
							entiteCouverture = (Boolean)entiteDocumentSolr.get("entiteCouverture_stored_boolean");
							entiteHtml = (Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean");
							entiteDocValues = (Boolean)entiteDocumentSolr.get("entiteDocValues_stored_boolean");
							entiteIndexeOuStocke = (Boolean)entiteDocumentSolr.get("entiteIndexeOuStocke_stored_boolean");
							entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
							entiteStocke = (Boolean)entiteDocumentSolr.get("entiteStocke_stored_boolean");
							entiteVarTitre = (Boolean)entiteDocumentSolr.get("entiteVarTitre_stored_boolean");
							entiteLien = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteLien_stored_boolean"));
							entiteVarH1 = (Boolean)entiteDocumentSolr.get("entiteVarH1_stored_boolean");
							entiteVarH2 = (Boolean)entiteDocumentSolr.get("entiteVarH2_stored_boolean");
							entiteVarH3 = (Boolean)entiteDocumentSolr.get("entiteVarH3_stored_boolean");
							entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
							entiteModifier = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteModifier_stored_boolean"));
							entiteDefinir = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteDefinir_stored_boolean"));
							entiteAttribuer = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteAttribuer_stored_boolean"));
							entiteSignature = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSignature_stored_boolean"));
							entiteSuggere = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean"));
							entiteRequis = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteRequis_stored_boolean"));
							entiteListeTypeJson = (String)entiteDocumentSolr.get("entiteListeTypeJson_stored_string");
							entiteAttribuerNomSimple = (String)entiteDocumentSolr.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string");
							entiteAttribuerVar = (String)entiteDocumentSolr.get("entiteAttribuerVar_" + langueNom + "_stored_string");
							entiteAttribuerVarSuggere = (String)entiteDocumentSolr.get("entiteAttribuerVarSuggere_" + langueNom + "_stored_string");
							entiteAttribuerOperationIdPATCH = (String)entiteDocumentSolr.get("entiteAttribuerOperationIdPATCH_" + langueNom + "_stored_string");
							entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + langueConfig.getString(I18n.var_Recherche) + "_" + langueNom + "_stored_string");
							entiteAttribuerApiUri = (String)entiteDocumentSolr.get("entiteAttribuerApiUri_" + langueNom + "_stored_string");
							entiteAttribuerPageUri = (String)entiteDocumentSolr.get("entiteAttribuerPageUri_" + langueNom + "_stored_string");
							entiteAttribuerContexteUnNom = (String)entiteDocumentSolr.get("entiteAttribuerContexteUnNom_" + langueNom + "_stored_string");
							entiteAttribuerContexteNomPluriel = (String)entiteDocumentSolr.get("entiteAttribuerContexteNomPluriel_" + langueNom + "_stored_string");
							entiteAttribuerContexteCouleur = (String)entiteDocumentSolr.get("entiteAttribuerContexteCouleur_stored_string");
							entiteAttribuerContexteIcone = (String)entiteDocumentSolr.get("entiteAttribuerContexteIcone_stored_string");
							entiteAttribuerTypeJson = (String)entiteDocumentSolr.get("entiteAttribuerTypeJson_stored_string");
							entiteImageBase64Url = (String)entiteDocumentSolr.get("entiteImageBase64Url_" + langueNom + "_stored_string");
							entiteNomSimpleVertxJson = (String)entiteDocumentSolr.get("entiteNomSimpleVertxJson_stored_string");
							entiteFacetsTrouves = Optional.ofNullable((Boolean)entiteDocumentSolr.get("entiteFacetsTrouves_stored_boolean")).orElse(false);

							wFormRecherche.l(entiteVar);
							if(entiteHtml) {
								if(entiteHtmCellule != null) {
									if(ecrireFormEntite(langueNom, langueConfig, wFormPage, "Page"))
										resultatFormPage = true;
									genCodeEntiteHtm(langueNom, langueConfig, "Page");
								// if(entiteHtmLigne != null && (entiteDefinir || entiteAttribuer)) {
									if(ecrireFormEntite(langueNom, langueConfig, wFormPOST, "POST"))
										resultatFormPOST = true;
									if(ecrireFormEntite(langueNom, langueConfig, wFormDELETE, "DELETE"))
										resultatFormDELETE = true;
									if(ecrireFormEntite(langueNom, langueConfig, wFormPUTCopie, langueConfig.getString(I18n.var_PUTCopie)))
										resultatFormPUTCopie = true;
									if(ecrireFormEntite(langueNom, langueConfig, wFormPATCH, "PATCH"))
										resultatFormPATCH = true;
								// if(entiteIndexeOuStocke) {
									if(ecrireFormEntite(langueNom, langueConfig, wFormRecherche, langueConfig.getString(I18n.var_Recherche)))
										resultatFormRecherche = true;
								}
							}
							if(entiteAttribuer) {
								wJsModuleInit.tl(2, "{% if \"PATCH\" in ", i18nGlobale.getString(I18n.var_portees), " %}");
								wJsModuleInit.tl(5, langueConfig.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], document.querySelector('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, true);");
								wJsModuleInit.tl(2, "{% else %}");
								wJsModuleInit.tl(2, "{% if \"GET\" in ", i18nGlobale.getString(I18n.var_portees), " %}");
								wJsModuleInit.tl(5, langueConfig.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], document.querySelector('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, true);");
								wJsModuleInit.tl(2, "{% else %}");
								wJsModuleInit.tl(5, langueConfig.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], document.querySelector('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, false);");
								wJsModuleInit.tl(2, "{% endif %}");
								wJsModuleInit.tl(2, "{% endif %}");
//									wWebsocket.tl(2, "tl(2, \"", "patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + \" + ", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Requete), StringUtils.capitalize(classeVarClePrimaire), "() + \" } ], {});\");");
								wPks.tl(2, "if(c == '", entiteAttribuerNomSimple, "')");
								wPks.tl(2, "patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + pk2 } ], {}");
								wJsModuleInit.tl(4, ", event.currentTarget");
								wJsModuleInit.tl(4, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterLueur), "(target); }");
								wJsModuleInit.tl(4, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterErreur), "(target); }");
								wPks.tl(4, ");");
							} else if(entiteDefinir) {
								if(entiteSignature) {
									wJsModuleInit.tl(4, "document.querySelector('#signatureInput", classeNomSimple, "' + pk + '", entiteVar, "').jSignature({'height':200}).bind('change', function(e){ patch{{", langueConfig.getString(I18n.var_classeNomSimple), "}}Val([{ name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " }], 'set", entiteVarCapitalise, "', document.querySelector('#signatureInput", classeNomSimple, "' + pk + '", entiteVar, "').jSignature('getData', 'default'), this);");
								} else {
									wJsModuleInit.l();
									wJsModuleInit.tl(5, "// PATCH ", entiteVar);
									if(!entiteTexte && !entiteSuggere && entiteIndexe 
											&& entiteFacetsTrouves
											&& !langueConfig.getString(I18n.var_sessionId).equals(entiteVar)
											&& !langueConfig.getString(I18n.var_utilisateurCle).equals(entiteVar)
											&& !langueConfig.getString(I18n.var_sauvegardes).equals(entiteVar)
											) {
										wJsModuleInit.tl(5, "document.querySelector('#fq", classeNomSimple, "_", entiteVar, "')?.addEventListener('", composantsWebPrefixe, "change', (event) => {");
										wJsModuleInit.tl(6, "fqChange('", classeNomSimple, "', event.target);");
										wJsModuleInit.tl(5, "});");
										wJsModuleInit.tl(5, "document.querySelector('#buttonFacet", classeNomSimple, "_", entiteVar, "')?.addEventListener('click', (event) => {");
										wJsModuleInit.tl(6, "facetFieldChange('", classeNomSimple, "', event.target);");
										wJsModuleInit.tl(5, "});");
										wJsModuleInit.tl(5, "document.querySelector('#pageFacetPivot", classeNomSimple, "_", entiteVar, "')?.addEventListener('", composantsWebPrefixe, "change', (event) => {");
										wJsModuleInit.tl(6, "facetPivotChange('", classeNomSimple, "', event.target);");
										wJsModuleInit.tl(5, "});");
									}

									if("Boolean".equals(entiteNomSimple) && entiteVar.equals(langueConfig.getString(I18n.var_archive))) {
										wJsModuleInit.tl(5, "document.querySelector('#", langueConfig.getString(I18n.var_Page), "_", entiteVar, "')?.addEventListener('click', (event) => {");
										wJsModuleInit.tl(6, "const form = document.querySelector('#", langueConfig.getString(I18n.var_Page), langueConfig.getString(I18n.var_Formulaire), "_", entiteVar, "');");
										wJsModuleInit.tl(6, "const ", langueConfig.getString(I18n.var_valide), " = form.checkValidity();");
										wJsModuleInit.tl(6, "if(", langueConfig.getString(I18n.var_valide), ") {");
										wJsModuleInit.tl(7, "var confirmResponse = confirm('", langueConfig.getString(I18n.str_confirmer_archiver), "'); ");
										wJsModuleInit.tl(7, "if(confirmResponse) { ");
										wJsModuleInit.tl(8, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + event.currentTarget.getAttribute('data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "') }]");
										wJsModuleInit.tl(10, ", 'set", entiteVarCapitalise, "', !(event.currentTarget.getAttribute('data-val') === 'true')");
										wJsModuleInit.tl(10, ", event.currentTarget");
										wJsModuleInit.tl(10, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterLueur), "(target); }");
										wJsModuleInit.tl(10, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterErreur), "(target); }");
										wJsModuleInit.tl(10, ");");
										wJsModuleInit.tl(7, "}");
										wJsModuleInit.tl(6, "}");
										wJsModuleInit.tl(5, "});");
									} else {
										wJsModuleInit.tl(5, "document.querySelector('#", langueConfig.getString(I18n.var_Page), "_", entiteVar, "')?.addEventListener('", composantsWebPrefixe, "change', (event) => {");
										wJsModuleInit.tl(6, "const form = document.querySelector('#", langueConfig.getString(I18n.var_Page), langueConfig.getString(I18n.var_Formulaire), "_", entiteVar, "');");
										wJsModuleInit.tl(6, "const ", langueConfig.getString(I18n.var_valide), " = form.checkValidity();");
										wJsModuleInit.tl(6, "if(", langueConfig.getString(I18n.var_valide), ") {");
										if(entiteListeTypeJson != null) {
											wJsModuleInit.tl(7, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + event.currentTarget.getAttribute('data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "') }]");
											wJsModuleInit.tl(9, ", 'set", entiteVarCapitalise, "', event.currentTarget.value.replace('[','').replace(']','').split(/[ ,]+/)");
											wJsModuleInit.tl(9, ", event.currentTarget");
											wJsModuleInit.tl(9, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterLueur), "(target); }");
											wJsModuleInit.tl(9, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterErreur), "(target); }");
											wJsModuleInit.tl(9, ");");
										} else if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson)) {
											wJsModuleInit.tl(7, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + event.currentTarget.getAttribute('data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "') }]");
											wJsModuleInit.tl(9, ", 'set", entiteVarCapitalise, "', event.currentTarget.value == '' ? null : JSON.parse(event.currentTarget.value)");
											wJsModuleInit.tl(9, ", event.currentTarget");
											wJsModuleInit.tl(9, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterLueur), "(target); }");
											wJsModuleInit.tl(9, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterErreur), "(target); }");
											wJsModuleInit.tl(9, ");");
										} else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
											wJsModuleInit.tl(7, "var timeZone = event.currentTarget.value.split('[').pop().split(']')[0];");
											wJsModuleInit.tl(7, "var t1 = moment(event.currentTarget.value.split('[')[0].trim(), '", langueConfig.getString(I18n.str_DDDashMMDashYYYY_HHColonmm), "');");
											wJsModuleInit.tl(7, "var t2 = moment.tz(event.currentTarget.value.split('[')[0].trim(), '", langueConfig.getString(I18n.str_DDDashMMDashYYYY_HHColonmm), "', timeZone);");
											wJsModuleInit.tl(7, "var t3 = new Date(t1._d);");
											wJsModuleInit.tl(7, "t3.setTime(t1.toDate().getTime() + t2.toDate().getTime() - t1.toDate().getTime());");
											wJsModuleInit.tl(7, "var t = moment(t3);");
											wJsModuleInit.tl(7, "if(t) {");
											wJsModuleInit.tl(8, "var s = t.tz(timeZone).format('YYYY-MM-DDTHH:mm:ss.000') + '[' + timeZone + ']';");
											wJsModuleInit.tl(8, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + event.currentTarget.getAttribute('data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "') }]");
											wJsModuleInit.tl(10, ", 'set", entiteVarCapitalise, "', s");
											wJsModuleInit.tl(10, ", event.currentTarget");
											wJsModuleInit.tl(10, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterLueur), "(target); }");
											wJsModuleInit.tl(10, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterErreur), "(target); }");
											wJsModuleInit.tl(10, ");");
											wJsModuleInit.tl(7, "}");
										} else if("LocalTime".equals(entiteNomSimple)) {
											wJsModuleInit.tl(7, "var t = moment(this.value, '", langueConfig.getString(I18n.var_HAposhAposmm), "'); ");
											wJsModuleInit.tl(7, "if(t) { ");
											wJsModuleInit.tl(8, "var s = t.format('HH:mm'); ");
											wJsModuleInit.tl(8, "patch{{", langueConfig.getString(I18n.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', s, this, function() { ", langueConfig.getString(I18n.var_ajouterLueur), "(document.querySelector('.{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(I18n.var_ajouterErreur), "(document.querySelector('.{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); ");
											wJsModuleInit.tl(10, ", 'set", entiteVarCapitalise, "', s");
											wJsModuleInit.tl(10, ", event.currentTarget");
											wJsModuleInit.tl(10, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterLueur), "(target); }");
											wJsModuleInit.tl(10, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterErreur), "(target); }");
											wJsModuleInit.tl(10, ");");
											wJsModuleInit.tl(7, "} ");
										} else if("Boolean".equals(entiteNomSimple)) {
											wJsModuleInit.tl(7, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + event.currentTarget.getAttribute('data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "') }]");
											wJsModuleInit.tl(9, ", 'set", entiteVarCapitalise, "', event.currentTarget.checked");
											wJsModuleInit.tl(9, ", event.currentTarget");
											wJsModuleInit.tl(9, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterLueur), "(target); }");
											wJsModuleInit.tl(9, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterErreur), "(target); }");
											wJsModuleInit.tl(9, ");");
										} else if(entiteImageBase64Url != null) {
											// wJsInitModule.tl(9, "<", composantsWebPrefixe, "input name=\"", langueConfig.getString(I18n.var_fichier), "\" type=\"file\" onchange=\"fetch('", entiteImageBase64Url, "', { method: 'POST', body: new FormData(this.form)}); \"></", composantsWebPrefixe, "input>");
											// wJsInitModule.tl(1, "fetch(");
		
											// if(methodeGET || methodeDELETE || methodePUTCopie)
											// 	wJsInitModule.tl(2, "'", StringUtils.replace(classeApiUriMethode, "{id}", "' + id"));
											// else if(methodePATCH || methodeRecherche)
											// 	wJsInitModule.tl(2, "'", classeApiUriMethode, "?' + ", i18nPage.getString(I18n.var_filtres), ".map(function(m) { return m.name + '=' + encodeURIComponent(m.value) }).join('&')");
											// else
											// 	wJsInitModule.tl(2, "'", classeApiUriMethode, "'");

											// wJsInitModule.tl(2, ", {");
											// wJsInitModule.tl(3, "headers: {'Content-Type':'application/json; charset=utf-8'}");
											// if(!"GET".equals(classeApiMethodeMethode) || "DELETE".equals(classeApiMethodeMethode)) {
											// 	wJsInitModule.tl(3, ", method: '", classeApiMethodeMethode, "'");
											// 	wJsInitModule.tl(3, ", body: JSON.stringify(vals)");
											// }
											// wJsInitModule.tl(2, "}).then(", i18nPage.getString(I18n.var_reponse), " => {");
											// wJsInitModule.tl(3, "if(", i18nPage.getString(I18n.var_reponse), ".ok)");
											// wJsInitModule.tl(4, "success(", i18nPage.getString(I18n.var_reponse), ", target);");
											// wJsInitModule.tl(3, "else");
											// wJsInitModule.tl(4, "error(", i18nPage.getString(I18n.var_reponse), ", target);");
											// wJsInitModule.tl(2, "})");
											// wJsInitModule.tl(2, ".catch(", i18nPage.getString(I18n.var_reponse), " => error(", i18nPage.getString(I18n.var_reponse), ", target));");
										} else {
											wJsModuleInit.tl(7, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + event.currentTarget.getAttribute('data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "') }]");
											wJsModuleInit.tl(9, ", 'set", entiteVarCapitalise, "', event.currentTarget.value");
											wJsModuleInit.tl(9, ", event.currentTarget");
											wJsModuleInit.tl(9, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterLueur), "(target); }");
											wJsModuleInit.tl(9, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterErreur), "(target); }");
											wJsModuleInit.tl(9, ");");
										}
										wJsModuleInit.tl(6, "}");
										wJsModuleInit.tl(5, "});");
									}

									wJsModuleInit.tl(5, "document.querySelector('#", langueConfig.getString(I18n.var_Page), "_", entiteVar, "')?.addEventListener('", composantsWebPrefixe, "focus', (event) => {");
									wJsModuleInit.tl(6, "", langueConfig.getString(I18n.var_enleverLueur), "(event.currentTarget);");
									wJsModuleInit.tl(5, "});");

									wJsModuleInit.tl(5, "document.querySelector('#", langueConfig.getString(I18n.var_Page), "_", entiteVar, "')?.addEventListener('", composantsWebPrefixe, "blur', (event) => {");
									wJsModuleInit.tl(6, "const form = document.querySelector('#", langueConfig.getString(I18n.var_Page), langueConfig.getString(I18n.var_Formulaire), "_", entiteVar, "');");
									wJsModuleInit.tl(6, "const ", langueConfig.getString(I18n.var_valide), " = form.reportValidity();");
									wJsModuleInit.tl(5, "});");
								}
							}

							if(entiteDefinir || entiteAttribuer || entiteIndexeOuStocke) {
								wWebsocketInput3.l();
								if(entiteHtmLigneTitre != null) {
//									wWebsocketInput3.t(4, "$response.querySelector(\"#").sx(genererId(entiteHtmLigneTitre)).s("\").replaceAll('#").sx(genererId(entiteHtmLigneTitre)).l("');");
//									wWebsocketInput3.l();
								}
								wWebsocketInput1.tl(4, "var input", entiteVarCapitalise, " = null;");
								wWebsocketInput2.tl(4, "if(vars.includes('", entiteVar, "'))");
								wWebsocketInput2.tl(5, "input", entiteVarCapitalise, " = $response.querySelector('#Page_", entiteVar, "');");
								wWebsocketInput3.tl(4, "if(input", entiteVarCapitalise, ") {");
								wWebsocketInput3.tl(5, "document.querySelectorAll('#Page_", entiteVar, "').forEach((item, index) => {");
								wWebsocketInput3.tl(6, "item.setAttribute('value', input", entiteVarCapitalise, ".getAttribute('value'));");
								wWebsocketInput3.tl(5, "});");
								wWebsocketInput3.tl(5, langueConfig.getString(I18n.var_ajouterLueur), "(document.querySelector('#Page_", entiteVar, "'));");
								wWebsocketInput3.tl(4, "}");
//								if("LocalDate".equals(entiteNomSimple)) {
//									wWebsocketInput.tl(4, "var val = o['", entiteVar, "'];");
////									wWebsocketInput.tl(3, "if(val != null) {");
////									wWebsocketInput.tl(4, "var t = moment(val, 'YYYY-MM-DD');");
////									wWebsocketInput.tl(4, "if(t)");
////									wWebsocketInput.tl(5, "val = t.format('", classePageLangueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "');");
////									wWebsocketInput.tl(3, "}");
//								}
//								else if("LocalDateTime".equals(entiteNomSimple)) {
//									wWebsocketInput.tl(4, "var val = o['", entiteVar, "'];");
////									wWebsocketInput.tl(3, "if(val != null) {");
////									wWebsocketInput.tl(4, "var t = moment(val, 'YYYY-MM-DD');");
////									wWebsocketInput.tl(4, "if(t)");
////									wWebsocketInput.tl(5, "val = t.format('", classePageLangueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "');");
////									wWebsocketInput.tl(3, "}");
//								}
//								else if("LocalTime".equals(entiteNomSimple)) {
//									wWebsocketInput.tl(4, "var val = o['", entiteVar, "'];");
////									wWebsocketInput.tl(3, "if(val != null) {");
////									wWebsocketInput.tl(4, "var t = moment(val, 'HH:mm');");
////									wWebsocketInput.tl(4, "if(t)");
////									wWebsocketInput.tl(5, "val = t.format('", classePageLangueConfig.getString(ConfigCles.var_HAposhAposmm), "');");
////									wWebsocketInput.tl(3, "}");
//								}
//								else {
//									wWebsocketInput.tl(4, "var val = o['", entiteVar, "'];");
//								}
//								wWebsocketInput.tl(4, "if(vars.includes('", entiteVar, "')) {");
//								if(entiteImageBase64Url != null) {
//									wWebsocketInput.tl(5, "if(val === undefined)");
//									wWebsocketInput.tl(6, "val = null;");
//									wWebsocketInput.tl(5, "document.querySelector('.img", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//									wWebsocketInput.tl(6, "if(val !== this.getAttribute('src'))");
//									wWebsocketInput.tl(7, "this.setAttribute('src', val);");
//									wWebsocketInput.tl(5, "});");
//								}
//								if(entiteSignature) {
//									wWebsocketInput.tl(5, "if(val === undefined)");
//									wWebsocketInput.tl(6, "val = null;");
//									wWebsocketInput.tl(5, "document.querySelector('.signatureInput", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//									wWebsocketInput.tl(6, "if(val !== document.querySelector('.signatureImg", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').getAttribute('src'))");
//									wWebsocketInput.tl(7, "document.querySelector('.signatureImg", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').setAttribute('src', val == null ? 'data:image/png;base64,' : val);");
//									wWebsocketInput.tl(6, langueConfig.getString(ConfigCles.var_ajouterLueur), "(document.querySelector('.signatureInput", classeNomSimple, "' + pk + '", entiteVarCapitalise, "'));");
//									wWebsocketInput.tl(5, "});");
//								}
//								wWebsocketInput.tl(5, "document.querySelector('.input", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//								wWebsocketInput.tl(6, "if(val !== this.value)");
//								if(entiteNomSimple.startsWith("Json")) {
//									wWebsocketInput.tl(7, "this.val(JSON.stringify(val));");
//								} else {
//									wWebsocketInput.tl(7, "this.val(val);");
//								}
//								wWebsocketInput.tl(7, langueConfig.getString(ConfigCles.var_ajouterLueur), "(this);");
//								wWebsocketInput.tl(5, "});");
//								wWebsocketInput.tl(5, "document.querySelector('.var", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//								wWebsocketInput.tl(6, "if(val !== this.text())");
//								if(entiteNomSimple.startsWith("Json")) {
//									wWebsocketInput.tl(7, "this.val(JSON.stringify(val));");
//								} else {
//									wWebsocketInput.tl(7, "this.text(val);");
//								}
//								wWebsocketInput.tl(7, langueConfig.getString(ConfigCles.var_ajouterLueur), "(this);");
//								wWebsocketInput.tl(5, "});");
//								wWebsocketInput.tl(4, "}");
							}
						}
						rechercheSolr.setStart(i.intValue() + rechercheLignes);
						rechercheReponse = clientSolrComputate.query(rechercheSolr);
						rechercheListe = rechercheReponse.getResults();
					}

					wWebsocket.tl(1, "var ", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, " = ", langueConfig.getString(I18n.var_requeteApi), "['", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, "'];");
					wWebsocket.tl(1, "var ", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, "s = ", langueConfig.getString(I18n.var_requeteApi), "['", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, "s'];");
					wWebsocket.tl(1, "var classes = ", langueConfig.getString(I18n.var_requeteApi), "['classes'];");
					wWebsocket.tl(1, "var vars = ", langueConfig.getString(I18n.var_requeteApi), "['vars'];");
					wWebsocket.tl(1, "var empty = ", langueConfig.getString(I18n.var_requeteApi), "['empty'];");
					wWebsocket.l();
					wWebsocket.tl(1, "if(", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, " != null && vars.length > 0) {");
					wWebsocket.tl(2, "var queryParams = \"?\" + Array.from(document.querySelectorAll(\".pageSearchVal\")).filter(elem => elem.innerText.length > 0).map(elem => elem.innerText).join(\"&\");");
					wWebsocket.tl(2, "var uri = location.pathname + queryParams;");
					wWebsocket.tl(2, "fetch(uri).then(response => {");
					wWebsocket.tl(3, "response.text().then(text => {");
					wWebsocket.tl(4, "var $response = new DOMParser().parseFromString(text, 'text/html');");
//					wWebsocket.tl(2, langueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Vals([ {name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + pk} ], function( data, textStatus, jQxhr ) {");
//					wWebsocket.tl(3, "var o = data['list'][0];");
//					wWebsocket.tl(3, "if(o != null) {");
					wWebsocket.s(wWebsocketInput1);
					wWebsocket.l();
					wWebsocket.s(wWebsocketInput2);
					wWebsocket.tl(5, langueConfig.getString(I18n.var_jsWebsocket), classeNomSimple, "(", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, ", vars, $response);");
					wWebsocket.l();
					wWebsocket.tl(5, "window.", StringUtils.uncapitalize(classeNomSimple), " = JSON.parse($response.querySelector('.", langueConfig.getString(I18n.var_page), langueConfig.getString(I18n.var_Formulaire), " .", StringUtils.uncapitalize(classeNomSimple), "')?.value);");
					wWebsocket.tl(5, "window.", langueConfig.getString(I18n.var_liste), classeNomSimple, " = JSON.parse($response.querySelector('.", langueConfig.getString(I18n.var_page), langueConfig.getString(I18n.var_Formulaire), " .", langueConfig.getString(I18n.var_liste), classeNomSimple, "')?.value);");
					wWebsocket.l();
					wWebsocket.s(wWebsocketInput3);
					wWebsocket.l();
					wWebsocket.tl(5, langueConfig.getString(I18n.var_page), langueConfig.getString(I18n.var_Graphique), classeNomSimple, "();");
//					wWebsocket.tl(3, "} else {");
//					wWebsocket.tl(4, "window.location.href = '", classePageUriMethode + "';");
//					wWebsocket.tl(3, "}");
					wWebsocket.tl(3, "});");
					wWebsocket.tl(2, "});");
					wWebsocket.tl(1, "}");
//						wWebsocket.l();
//						wWebsocket.tl(1, "if(!empty) {");
//						wWebsocket.tl(2, "if(pks) {");
//						wWebsocket.tl(3, "for(i=0; i < pks.length; i++) {");
//						wWebsocket.tl(4, "var pk2 = pks[i];");
//						wWebsocket.tl(4, "var c = classes[i];");
//						wWebsocket.tl(4, "window['patch' + c + 'Vals']( [ {name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + pk2} ], {});");
//						wWebsocket.tl(3, "}");
//						wWebsocket.tl(2, "}");
//						wWebsocket.tl(2, "if(pk)");
//						wWebsocket.tl(3, "patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Vals( [ {name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + pk} ], {});");
//						wWebsocket.tl(1, "}");

					for(Integer i=0; i < wForms.size(); i++) {
						ToutEcrivain wForm = wForms.get(i);
						String wClasseApiMethodeMethode = wClasseApiMethodeMethodes.get(i);
						if(Optional.ofNullable(entiteHtmLigneVerticaleActuelMap.get(wClasseApiMethodeMethode)).orElse(false)) {
							// wForm.tl(9, "</tbody>");
							// wForm.tl(8, "</table>");
						}
					}
					if(resultatFormPOST) {
						wFormPOST.tl(7, "</div>");
						wFormPOST.tl(6, "</", composantsWebPrefixe, "details>");
					}
					if(resultatFormPUTImport) {
						wFormPUTImport.tl(7, "</div>");
						wFormPUTImport.tl(6, "</", composantsWebPrefixe, "details>");
					}
					if(resultatFormPUTFusion) {
						wFormPUTFusion.tl(7, "</div>");
						wFormPUTFusion.tl(6, "</", composantsWebPrefixe, "details>");
					}
					if(resultatFormPUTCopie) {
						wFormPUTCopie.tl(7, "</div>");
						wFormPUTCopie.tl(6, "</", composantsWebPrefixe, "details>");
					}
					if(resultatFormPage) {
						wFormPage.tl(7, "</div>");
						wFormPage.tl(6, "</", composantsWebPrefixe, "details>");
					}
					if(resultatFormPATCH) {
						wFormPATCH.tl(7, "</div>");
						wFormPATCH.tl(6, "</", composantsWebPrefixe, "details>");
					}
					if(resultatFormDELETE) {
						wFormDELETE.tl(7, "</div>");
						wFormDELETE.tl(6, "</", composantsWebPrefixe, "details>");
					}
					if(resultatFormRecherche) {
						wFormRecherche.tl(7, "</div>");
						wFormRecherche.tl(6, "</", composantsWebPrefixe, "details>");
					}
				}
			}
	
			o = auteurGenPageClasse;

			if(auteurPageClasse != null) {
				if(classeDroitAuteur != null)
					auteurPageClasse.l(classeDroitAuteur);
				auteurPageClasse.l("package ", classeNomEnsemble, ";");
				auteurPageClasse.l();
				auteurPageClasse.l("/**");

				auteurPageClasse.l(" * ", langueConfig.getString(I18n.var_Promesse), ": true");
				for(String langueNom2 : autresLangues) {
					String classePageNomSimple2 = classeDoc.getString("classePageNomCanonique" + langueConfig.getString(I18n.var_PageRecherche)  + "_" + langueNom2 + "_stored_string");
					if(classePageNomSimple2 != null)
						auteurPageClasse.	l(" * ", langueConfig.getString(I18n.var_NomCanonique), ".", langueNom2, ": ", classePageNomSimple2);
				}
				auteurPageClasse.l(" **/");
				auteurPageClasse.s("public class ", classePageNomSimple);
				auteurPageClasse.s(" extends ", classePageNomSimple, "Gen<", classeGenPageNomSimple, ">");
				auteurPageClasse.l(" {");
				auteurPageClasse.tl(0, "}");
			}

			if(classeDroitAuteur != null)
				l(classeDroitAuteur);
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
			l(" * ", langueConfig.getString(I18n.var_Traduire), ": false");
			for(String langueNom2 : autresLangues) {
				String classeGenPageNomSimple2 = classeDoc.getString("classeGenPageNomCanonique" + langueConfig.getString(I18n.var_PageRecherche)  + "_" + langueNom2 + "_stored_string");
				if(classeGenPageNomSimple2 != null)
					l(" * ", langueConfig.getString(I18n.var_NomCanonique), ".", langueNom2, ": ", classeGenPageNomSimple2);
			}
			l(" * ", i18nGlobale.getString(I18n.str_Genere), ": true");
			l(" **/");
			s("public class ", classeGenPageNomSimple);
			s(" extends ", classeGenPageNomSimple, "Gen");
			s("<", (classePageSuperNomSimple == null ? "Object" : classePageSuperNomSimple), ">");
			l(" {");

			if(classePageSuperNomSimple == null) {
				l();
				tl(1, "/**");
				tl(1, " * ", langueConfig.getString(I18n.var_Ignorer), ": true");
				tl(1, "**/");
				tl(1, "protected void _", langueConfig.getString(I18n.var_requeteSite), "_(", classePartsCouverture.nomSimple(langueNom), "<", classePartsRequeteSite.nomSimple(langueNom), "> c", ") {");
				tl(1, "}");
			}

			if(!classePageSimple) {
				l();
				tl(1, "/**");
				tl(1, " * {@inheritDoc}");
				tl(1, " * ", langueConfig.getString(I18n.var_Ignorer), ": true");
				tl(1, " **/");
				tl(1, "protected void _", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_(", classePartsCouverture.nomSimple(langueNom), "<", langueConfig.getString(I18n.var_ListeRecherche), "<", classeApiClasseNomSimple, ">> ", langueConfig.getString(I18n.var_cVar), ") {");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", langueConfig.getString(I18n.var_page), langueConfig.getString(I18n.var_Reponse), "(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(I18n.var_cVar), ") {");
				tl(2, "if(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_ != null)");
				tl(3, langueConfig.getString(I18n.var_cVar), ".o(JsonObject.mapFrom(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse()).toString());");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _stats(", classePartsCouverture.nomSimple(langueNom), "<SolrResponse.Stats> ", langueConfig.getString(I18n.var_cVar), ") {");
				tl(2, langueConfig.getString(I18n.var_cVar), ".o(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse().getStats());");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _facetCounts(", classePartsCouverture.nomSimple(langueNom), "<SolrResponse.FacetCounts> ", langueConfig.getString(I18n.var_cVar), ") {");
				tl(2, langueConfig.getString(I18n.var_cVar), ".o(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse().getFacetCounts());");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pagination(JsonObject pagination) {");
				tl(2, "JsonArray pages = new JsonArray();");
				tl(2, "Long ", langueConfig.getString(I18n.var_debut), " = ", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getStart().longValue();");
				tl(2, "Long ", langueConfig.getString(I18n.var_lignes), " = ", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getRows().longValue();");
				tl(2, "Long ", langueConfig.getString(I18n.var_numTrouve), " = ", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse().getResponse().getNumFound().longValue();");
				tl(2, "Long ", langueConfig.getString(I18n.var_debut), "Num = ", langueConfig.getString(I18n.var_debut), " + 1L;");
				tl(2, "Long ", langueConfig.getString(I18n.var_fin), "Num = ", langueConfig.getString(I18n.var_debut), " + ", langueConfig.getString(I18n.var_lignes), ";");
				tl(2, "Long floorMod = (", langueConfig.getString(I18n.var_lignes), " == 0L ? 0L : Math.floorMod(", langueConfig.getString(I18n.var_numTrouve), ", ", langueConfig.getString(I18n.var_lignes), "));");
				tl(2, "Long ", langueConfig.getString(I18n.var_dernier), " = (", langueConfig.getString(I18n.var_lignes), " == 0L ? 0L : Math.floorDiv(", langueConfig.getString(I18n.var_numTrouve), ", ", langueConfig.getString(I18n.var_lignes), ") - (floorMod.equals(0L) ? 1L : 0L) * ", langueConfig.getString(I18n.var_lignes), ");");
				tl(2, langueConfig.getString(I18n.var_fin), "Num = ", langueConfig.getString(I18n.var_fin), "Num < ", langueConfig.getString(I18n.var_numTrouve), " ? ", langueConfig.getString(I18n.var_fin), "Num : ", langueConfig.getString(I18n.var_numTrouve), ";");
				tl(2, langueConfig.getString(I18n.var_debut), "Num = ", langueConfig.getString(I18n.var_numTrouve), " == 0L ? 0L : ", langueConfig.getString(I18n.var_debut), "Num;");
				tl(2, "Long pagination", langueConfig.getString(I18n.var_Debut), " = ", langueConfig.getString(I18n.var_debut), " - 10L * ", langueConfig.getString(I18n.var_lignes), ";");
				tl(2, "if(pagination", langueConfig.getString(I18n.var_Debut), " < 0L)");
				tl(3, "pagination", langueConfig.getString(I18n.var_Debut), " = 0L;");
				tl(2, "Long pagination", langueConfig.getString(I18n.var_Fin), " = ", langueConfig.getString(I18n.var_debut), " + 10L * ", langueConfig.getString(I18n.var_lignes), ";");
				tl(2, "if(pagination", langueConfig.getString(I18n.var_Fin), " > ", langueConfig.getString(I18n.var_numTrouve), ")");
				tl(3, "pagination", langueConfig.getString(I18n.var_Fin), " = ", langueConfig.getString(I18n.var_numTrouve), ";");
				l();
				tl(2, "pagination.put(\"1L\", 1L);");
				tl(2, "pagination.put(\"0L\", 0L);");
				tl(2, "pagination.put(\"", langueConfig.getString(I18n.var_debut), "\", ", langueConfig.getString(I18n.var_debut), ");");
				tl(2, "pagination.put(\"", langueConfig.getString(I18n.var_lignes), "\", ", langueConfig.getString(I18n.var_lignes), ");");
				tl(2, "pagination.put(\"", langueConfig.getString(I18n.var_lignes), langueConfig.getString(I18n.var_Precedent), "\", ", langueConfig.getString(I18n.var_lignes), " / 2);");
				tl(2, "pagination.put(\"", langueConfig.getString(I18n.var_lignes), langueConfig.getString(I18n.var_Prochaine), "\", ", langueConfig.getString(I18n.var_lignes), " * 2);");
				tl(2, "pagination.put(\"", langueConfig.getString(I18n.var_debut), "Num\", ", langueConfig.getString(I18n.var_debut), "Num);");
				tl(2, "pagination.put(\"", langueConfig.getString(I18n.var_fin), "Num\", ", langueConfig.getString(I18n.var_fin), "Num);");
				tl(2, "pagination.put(\"", langueConfig.getString(I18n.var_numTrouve), "\", ", langueConfig.getString(I18n.var_numTrouve), ");");
				tl(2, "pagination.put(\"page", langueConfig.getString(I18n.var_Debut), "\", new JsonObject().put(\"", langueConfig.getString(I18n.var_debut), "\", 0L).put(\"page", langueConfig.getString(I18n.var_Numero), "\", 1L));");
				tl(2, "if(", langueConfig.getString(I18n.var_debut), " > 0L)");
				tl(3, "pagination.put(\"page", langueConfig.getString(I18n.var_Precedent), "\", new JsonObject().put(\"", langueConfig.getString(I18n.var_debut), "\", ", langueConfig.getString(I18n.var_debut), " - ", langueConfig.getString(I18n.var_lignes), ").put(\"page", langueConfig.getString(I18n.var_Numero), "\", ", langueConfig.getString(I18n.var_debut), " - ", langueConfig.getString(I18n.var_lignes), " + 1L));");
				tl(2, "if(", langueConfig.getString(I18n.var_debut), " + ", langueConfig.getString(I18n.var_lignes), " < ", langueConfig.getString(I18n.var_numTrouve), ")");
				tl(3, "pagination.put(\"page", langueConfig.getString(I18n.var_Prochaine), "\", new JsonObject().put(\"", langueConfig.getString(I18n.var_debut), "\", ", langueConfig.getString(I18n.var_debut), " + ", langueConfig.getString(I18n.var_lignes), ").put(\"page", langueConfig.getString(I18n.var_Numero), "\", ", langueConfig.getString(I18n.var_debut), " + ", langueConfig.getString(I18n.var_lignes), " + 1L));");
				tl(2, "pagination.put(\"page", langueConfig.getString(I18n.var_Fin), "\", new JsonObject().put(\"", langueConfig.getString(I18n.var_debut), "\", ", langueConfig.getString(I18n.var_dernier), ").put(\"page", langueConfig.getString(I18n.var_Numero), "\", ", langueConfig.getString(I18n.var_dernier), " + 1L));");
				tl(2, "pagination.put(\"pages\", pages);");
				tl(2, "for(Long i = pagination", langueConfig.getString(I18n.var_Debut), "; i < pagination", langueConfig.getString(I18n.var_Fin), "; i += ", langueConfig.getString(I18n.var_lignes), ") {");
				tl(3, "Long page", langueConfig.getString(I18n.var_Numero), " = Math.floorDiv(i, ", langueConfig.getString(I18n.var_lignes), ") + 1L;");
				tl(3, "JsonObject page = new JsonObject();");
				tl(3, "page.put(\"page", langueConfig.getString(I18n.var_Numero), "\", page", langueConfig.getString(I18n.var_Numero), ");");
				tl(3, "page.put(\"", langueConfig.getString(I18n.var_pageActuel), "\", ", langueConfig.getString(I18n.var_debut), ".equals(i));");
				tl(3, "page.put(\"", langueConfig.getString(I18n.var_debut), "\", i);");
				tl(3, "pages.add(page);");
				tl(2, "}");
				tl(1, "}");
	
				////////////
				// varsQ //
				////////////
	
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _varsQ(JsonObject vars) {");
				tl(2, classeNomSimple, ".varsQ", langueConfig.getString(I18n.var_PourClasse), "().forEach(var -> {");
				tl(3, "JsonObject json = new JsonObject();");
				tl(3, "json.put(\"var\", var);");
				tl(3, "json.put(\"", langueConfig.getString(I18n.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(I18n.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"", langueConfig.getString(I18n.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(I18n.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"val\", Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getQuery()).filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).map(s -> SearchTool.unescapeQueryChars(StringUtils.substringAfter(s, \":\"))).orElse(null));");
				tl(3, "vars.put(var, json);");
				tl(2, "});");
				tl(1, "}");
	
				////////////
				// varsFq //
				////////////
	
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _varsFq(JsonObject vars) {");
				tl(2, "Map<String, SolrResponse.FacetField> facetFields = Optional.ofNullable(facetCounts).map(c -> c.getFacetFields()).map(f -> f.getFacets()).orElse(new HashMap<String,SolrResponse.FacetField>());");
				tl(2, classeNomSimple, ".varsFq", langueConfig.getString(I18n.var_PourClasse), "().forEach(var -> {");
				tl(3, "String var", langueConfig.getString(I18n.var_Indexe), " = ", classeNomSimple, ".var", langueConfig.getString(I18n.var_Indexe), classeNomSimple, "(var);");
				tl(3, "String var", langueConfig.getString(I18n.var_Stocke), " = ", classeNomSimple, ".var", langueConfig.getString(I18n.var_Stocke), classeNomSimple, "(var);");
				tl(3, "JsonObject json = new JsonObject();");
				tl(3, "json.put(\"var\", var);");
				tl(3, "json.put(\"var", langueConfig.getString(I18n.var_Stocke), "\", var", langueConfig.getString(I18n.var_Stocke), ");");
				tl(3, "json.put(\"var", langueConfig.getString(I18n.var_Indexe), "\", var", langueConfig.getString(I18n.var_Indexe), ");");
				tl(3, "String type = StringUtils.substringAfterLast(var", langueConfig.getString(I18n.var_Indexe), ", \"_\");");
				tl(3, "json.put(\"", langueConfig.getString(I18n.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(I18n.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"", langueConfig.getString(I18n.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(I18n.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"val\", ", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getFilterQueries().stream().filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).findFirst().map(s -> SearchTool.unescapeQueryChars(StringUtils.substringAfter(s, \":\"))).orElse(null));");
				tl(3, "Optional.ofNullable(stats).map(s -> s.get(var", langueConfig.getString(I18n.var_Indexe), ")).ifPresent(stat -> {");
				tl(4, "json.put(\"stats\", JsonObject.mapFrom(stat));");
				tl(3, "});");
	
				tl(3, "Optional.ofNullable(facetFields.get(var", langueConfig.getString(I18n.var_Indexe), ")).ifPresent(facetField -> {");
				tl(4, "JsonObject facetJson = new JsonObject();");
				tl(4, "JsonObject counts = new JsonObject();");
				tl(4, "facetJson.put(\"var\", var);");
				tl(4, "facetField.getCounts().forEach((val, count) -> {");
				tl(5, "counts.put(val, count);");
				tl(4, "});");
				tl(4, "facetJson.put(\"counts\", counts);");
				tl(4, "json.put(\"facetField\", facetJson);");
				tl(3, "});");
	
				tl(3, "if(default", langueConfig.getString(I18n.var_ListeChamps), "Vars.contains(var)) {");
				tl(4, "json.put(\"", langueConfig.getString(I18n.var_listeChamps), "\", true);");
				tl(3, "}");
	
				tl(3, "if(StringUtils.equalsAny(type, \"date\") && json.containsKey(\"stats\")) {");
				tl(4, "JsonObject stats = json.getJsonObject(\"stats\");");
				tl(4, "Instant min = Optional.ofNullable(stats.getString(\"min\")).map(val -> Instant.parse(val.toString())).orElse(Instant.now());");
				tl(4, "Instant max = Optional.ofNullable(stats.getString(\"max\")).map(val -> Instant.parse(val.toString())).orElse(Instant.now());");
				tl(4, "if(min.equals(max)) {");
				tl(5, "min = min.minus(1, ChronoUnit.DAYS);");
				tl(5, "max = max.plus(2, ChronoUnit.DAYS);");
				tl(4, "}");
				tl(4, "Duration duration = Duration.between(min, max);");
				tl(4, "String gap = \"DAY\";");
				tl(4, "if(duration.toDays() >= 365)");
				tl(5, "gap = \"YEAR\";");
				tl(4, "else if(duration.toDays() >= 28)");
				tl(5, "gap = \"MONTH\";");
				tl(4, "else if(duration.toDays() >= 1)");
				tl(5, "gap = \"DAY\";");
				tl(4, "else if(duration.toHours() >= 1)");
				tl(5, "gap = \"HOUR\";");
				tl(4, "else if(duration.toMinutes() >= 1)");
				tl(5, "gap = \"MINUTE\";");
				tl(4, "else if(duration.toMillis() >= 1000)");
				tl(5, "gap = \"SECOND\";");
				tl(4, "else if(duration.toMillis() >= 1)");
				tl(5, "gap = \"MILLI\";");
				tl(4, "json.put(\"defaultRangeGap\", String.format(\"+1%s\", gap));");
				tl(4, "json.put(\"defaultRangeEnd\", max.toString());");
				tl(4, "json.put(\"defaultRangeStart\", min.toString());");
				tl(4, "json.put(\"", langueConfig.getString(I18n.var_activer), langueConfig.getString(I18n.var_Calendrier), "\", true);");
				tl(4, "setDefault", langueConfig.getString(I18n.var_Gamme), langueConfig.getString(I18n.var_Stats), "(json);");
				tl(3, "}");
				tl(3, "json.put(\"", langueConfig.getString(I18n.var_activer), langueConfig.getString(I18n.var_Stats), "\", !StringUtils.equalsAny(type, \"boolean\", \"location\"));");
				tl(3, "if(default", langueConfig.getString(I18n.var_Stats), "Vars.contains(var)) {");
				tl(4, "SolrResponse.StatsField varStats = stats.get(var", langueConfig.getString(I18n.var_Indexe), ");");
				tl(4, "if(varStats != null)");
				tl(5, "json.put(\"", langueConfig.getString(I18n.var_stats), "\", varStats);");
				tl(3, "}");
	
				tl(3, "if(default", langueConfig.getString(I18n.var_Pivot), "Vars.contains(var)) {");
				tl(4, "json.put(\"", langueConfig.getString(I18n.var_pivot), "\", true);");
				tl(3, "}");
	
				tl(3, "vars.put(var, json);");
				tl(2, "});");
				tl(1, "}");
	
				///////////////
				// varsGamme //
				///////////////
	
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _vars", langueConfig.getString(I18n.var_Gamme), "(JsonObject vars) {");
	//			tl(2, "Map<String, SolrResponse.Pivot> pivotFields = Optional.ofNullable(facetCounts).map(c -> c.getFacetPivot()).map(f -> f.getPivotMap()).orElse(new HashMap<String,SolrResponse.Pivot>());");
				tl(2, classeNomSimple, ".vars", langueConfig.getString(I18n.var_Gamme), langueConfig.getString(I18n.var_PourClasse), "().forEach(var -> {");
				tl(3, "String var", langueConfig.getString(I18n.var_Indexe), " = ", classeNomSimple, ".var", langueConfig.getString(I18n.var_Indexe), classeNomSimple, "(var);");
				tl(3, "JsonObject json = new JsonObject();");
				tl(3, "json.put(\"var\", var);");
				tl(3, "json.put(\"", langueConfig.getString(I18n.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(I18n.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"", langueConfig.getString(I18n.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(I18n.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"val\", ", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getFilterQueries().stream().filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).findFirst().map(s -> SearchTool.unescapeQueryChars(StringUtils.substringAfter(s, \":\"))).orElse(null));");
	
	//			tl(3, "Optional.ofNullable(facetFields.get(var", langueConfig.getString(ConfigCles.var_Indexe), ")).ifPresent(facetField -> {");
	//			tl(4, "JsonObject facetJson = new JsonObject();");
	//			tl(4, "JsonObject counts = new JsonObject();");
	//			tl(4, "facetJson.put(\"var\", var);");
	//			tl(4, "facetField.getCounts().forEach((val, count) -> {");
	//			tl(5, "counts.put(val, count);");
	//			tl(4, "});");
	//			tl(4, "facetJson.put(\"counts\", counts);");
	//			tl(4, "json.put(\"facetField\", facetJson);");
	//			tl(3, "});");
	
				tl(3, "vars.put(var, json);");
				tl(2, "});");
				tl(1, "}");
	
				///////////
				// query //
				///////////
	
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _query(JsonObject query) {");
				tl(2, "ServiceRequest ", langueConfig.getString(I18n.var_requeteService), " = ", langueConfig.getString(I18n.var_requeteSite), "_.getServiceRequest();");
				tl(2, "JsonObject params = ", langueConfig.getString(I18n.var_requeteService), ".getParams();");
				l();
				tl(2, "JsonObject queryParams = Optional.ofNullable(", langueConfig.getString(I18n.var_requeteService), ").map(ServiceRequest::getParams).map(or -> or.getJsonObject(\"query\")).orElse(new JsonObject());");
				tl(2, "Long num = ", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse().getResponse().getNumFound().longValue();");
				tl(2, "String q = \"*:*\";");
				tl(2, "String q1 = \"", classeVarTexte, "\";");
				tl(2, "String q2 = \"\";");
				tl(2, "for(String param", langueConfig.getString(I18n.var_Nom), " : queryParams.fieldNames()) {");
				tl(3, "String ", langueConfig.getString(I18n.var_entite), "Var = null;");
				tl(3, "String ", langueConfig.getString(I18n.var_valeur), langueConfig.getString(I18n.var_Indexe), " = null;");
				tl(3, "Object param", langueConfig.getString(I18n.var_ValeursObjet), " = queryParams.getValue(param", langueConfig.getString(I18n.var_Nom), ");");
				tl(3, "JsonArray param", langueConfig.getString(I18n.var_Objets), " = param", langueConfig.getString(I18n.var_ValeursObjet), " instanceof JsonArray ? (JsonArray)param", langueConfig.getString(I18n.var_ValeursObjet), " : new JsonArray().add(param", langueConfig.getString(I18n.var_ValeursObjet), ");");
				l();
				tl(3, "try {");
				tl(4, "for(Object param", langueConfig.getString(I18n.var_Objet), " : param", langueConfig.getString(I18n.var_Objets), ") {");
				tl(5, "switch(param", langueConfig.getString(I18n.var_Nom), ") {");
				tl(5, "case \"q\":");
				tl(6, "q = (String)param", langueConfig.getString(I18n.var_Objet), ";");
				tl(6, langueConfig.getString(I18n.var_entite), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", langueConfig.getString(I18n.var_Objet), ", \":\"));");
				tl(6, langueConfig.getString(I18n.var_valeur), langueConfig.getString(I18n.var_Indexe), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", langueConfig.getString(I18n.var_Objet), ", \":\")), \"UTF-8\");");
				tl(6, "q1 = ", langueConfig.getString(I18n.var_entite), "Var.equals(\"*\") ? q1 : ", langueConfig.getString(I18n.var_entite), "Var;");
				tl(6, "q2 = ", langueConfig.getString(I18n.var_valeur), langueConfig.getString(I18n.var_Indexe), ";");
				tl(6, "q = q1 + \":\" + q2;");
				tl(5, "}");
				tl(4, "}");
				tl(3, "} catch(Exception e) {");
				tl(4, "ExceptionUtils.rethrow(e);");
				tl(3, "}");
				tl(2, "}");
				tl(2, "query.put(\"q\", q);");
				l();
				tl(2, "Long rows1 = Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getRows()).orElse(10L);");
				tl(2, "Long start1 = Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getStart()).orElse(1L);");
				tl(2, "Long start2 = start1 - rows1;");
				tl(2, "Long start3 = start1 + rows1;");
				tl(2, "Long rows2 = rows1 / 2;");
				tl(2, "Long rows3 = rows1 * 2;");
				tl(2, "start2 = start2 < 0 ? 0 : start2;");
				tl(2, "JsonObject fqs = new JsonObject();");
				tl(2, "for(String fq : Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getFilterQueries()).orElse(Arrays.asList())) {");
				tl(3, "if(!StringUtils.contains(fq, \"(\")) {");
				tl(4, "String fq1 = ", classeNomSimple, ".", langueConfig.getString(I18n.var_recherche), "Var", classeNomSimple, "(StringUtils.substringBefore(fq, \":\"));");
				tl(4, "String fq2 = StringUtils.substringAfter(fq, \":\");");
				tl(4, "if(!StringUtils.startsWithAny(fq, \"", langueConfig.getString(I18n.var_classeNomsCanoniques), "_\", \"", langueConfig.getString(I18n.var_archive), "_\", \"sessionId\", \"", langueConfig.getString(I18n.var_utilisateur), langueConfig.getString(I18n.var_Cle), "s\"))");
				tl(5, "fqs.put(fq1, new JsonObject().put(\"var\", fq1).put(\"val\", fq2).put(\"", langueConfig.getString(I18n.var_nomAffichage), "\", ", classeNomSimple, ".", langueConfig.getString(I18n.var_nomAffichage), langueConfig.getString(I18n.var_PourClasse), "(fq1)));");
				tl(4, "}");
				tl(3, "}");
				tl(2, "query.put(\"fq\", fqs);");
				l();
				tl(2, "JsonArray sorts = new JsonArray();");
				tl(2, "for(String sort : Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getSorts()).orElse(Arrays.asList())) {");
				tl(3, "String sort1 = ", classeNomSimple, ".", langueConfig.getString(I18n.var_recherche), "Var", classeNomSimple, "(StringUtils.substringBefore(sort, \" \"));");
				tl(3, "sorts.add(new JsonObject().put(\"var\", sort1).put(\"order\", StringUtils.substringAfter(sort, \" \")).put(\"", langueConfig.getString(I18n.var_nomAffichage), "\", ", classeNomSimple, ".", langueConfig.getString(I18n.var_nomAffichage), langueConfig.getString(I18n.var_PourClasse), "(sort1)));");
				tl(2, "}");
				tl(2, "query.put(\"sort\", sorts);");
				tl(1, "}");
				if(classePageSuperNomSimple != null && (classeEtendBase || !classeModele)) {
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultZoneId(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Requete), "Vars().get(VAR_defaultZoneId)).orElse(", langueConfig.getString(I18n.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".SITE_ZONE)));");
					tl(1, "}");
					l();
					tl(1, "/**");
					tl(1, " * ", langueConfig.getString(I18n.var_Ignorer), ": true");
					tl(1, " **/");
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultTimeZone(", classePartsCouverture.nomSimple(langueNom), "<ZoneId> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(ZoneId.of(defaultZoneId));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultLocaleId(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_.getRequestHeaders().get(\"Accept-Language\")).map(acceptLanguage -> StringUtils.substringBefore(acceptLanguage, \",\")).orElse(", langueConfig.getString(I18n.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".SITE_LOCALE)));");
					tl(1, "}");
					l();
					tl(1, "/**");
					tl(1, " * ", langueConfig.getString(I18n.var_Ignorer), ": true");
					tl(1, " **/");
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultLocale(", classePartsCouverture.nomSimple(langueNom), "<Locale> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(Locale.forLanguageTag(defaultLocaleId));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _rows(", classePartsCouverture.nomSimple(langueNom), "<Long> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, "if(", langueConfig.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"rows\", null) != null)");
					tl(3, langueConfig.getString(I18n.var_cVar), ".o(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getRows());");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _start(", classePartsCouverture.nomSimple(langueNom), "<Long> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, "if(", langueConfig.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"start\", null) != null)");
					tl(3, langueConfig.getString(I18n.var_cVar), ".o(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getStart());");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _rangeGap(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, "if(", langueConfig.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"facet.range.gap\", null) != null)");
					tl(3, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeGap()).orElse(null));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _rangeEnd(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, "if(", langueConfig.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"facet.range.end\", null) != null)");
					tl(3, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeEnd()).map(s -> TimeTool.parseZonedDateTime(defaultTimeZone, s)).orElse(null));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _rangeStart(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, "if(", langueConfig.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"facet.range.start\", null) != null)");
					tl(3, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeStart()).map(s -> TimeTool.parseZonedDateTime(defaultTimeZone, s)).orElse(null));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultRangeGap(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(rangeGap).orElse(Optional.ofNullable(defaultRangeStats).map(s -> s.getString(\"defaultRangeGap\")).orElse(\"+1DAY\")));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultRangeEnd(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(rangeEnd).orElse(Optional.ofNullable(defaultRangeStats).map(s -> Instant.parse(s.getString(\"defaultRangeEnd\")).atZone(defaultTimeZone)).orElse(ZonedDateTime.now(defaultTimeZone).toLocalDate().atStartOfDay(defaultTimeZone).plusDays(1))));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultRangeStart(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(rangeStart).orElse(Optional.ofNullable(defaultRangeStats).map(s -> Instant.parse(s.getString(\"defaultRangeStart\")).atZone(defaultTimeZone)).orElse(defaultRangeEnd.minusDays(7).toLocalDate().atStartOfDay(defaultTimeZone))));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultRangeVar(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRanges()).orElse(Optional.ofNullable(defaultRangeStats).map(s -> Arrays.asList(s.getString(\"defaultRangeVar\"))).orElse(Arrays.asList())).stream().findFirst().map(v -> { if(v.contains(\"}\")) return StringUtils.substringBefore(StringUtils.substringAfterLast(v, \"}\"), \"_\"); else return ", classeNomSimple, ".", langueConfig.getString(I18n.var_recherche), "Var", classeNomSimple, "(v); }).orElse(\"", classeVarCree, "\"));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultFacetSort(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetSort()).orElse(\"index\"));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultFacetLimit(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetLimit()).orElse(1));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultFacetMinCount(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetMinCount()).orElse(1));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultPivotMinCount(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, langueConfig.getString(I18n.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetPivotMinCount()).orElse(0));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _DEFAULT_MAP_LOCATION(", classePartsCouverture.nomSimple(langueNom), "<JsonObject> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, "String pointStr = Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Requete), "Vars().get(VAR_DEFAULT_MAP_LOCATION)).orElse(", langueConfig.getString(I18n.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".DEFAULT_MAP_LOCATION));");
					tl(2, "if(pointStr != null) {");
					tl(3, "String[] parts = pointStr.replace(\"[\", \"\").replace(\"]\", \"\").replace(\"\\\"\", \"\").split(\",\");");
					tl(3, "JsonObject point = new JsonObject().put(\"lat\", Double.parseDouble(parts[0])).put(\"lon\", Double.parseDouble(parts[1]));");
					tl(3, langueConfig.getString(I18n.var_cVar), ".o(point);");
					tl(2, "}");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _DEFAULT_MAP_ZOOM(", classePartsCouverture.nomSimple(langueNom), "<BigDecimal> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, "String s = Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Requete), "Vars().get(VAR_DEFAULT_MAP_ZOOM)).orElse(", langueConfig.getString(I18n.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".DEFAULT_MAP_ZOOM));");
					tl(2, "if(s != null)");
					tl(3, langueConfig.getString(I18n.var_cVar), ".o(new BigDecimal(s));");
					tl(1, "}");
				} else {
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultRangeStats(", classePartsCouverture.nomSimple(langueNom), "<JsonObject> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(1, "}");
				}
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _default", langueConfig.getString(I18n.var_ListeChamps), "Vars(List<String> l) {");
				tl(2, "Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFields()).orElse(Arrays.asList()).forEach(var", langueConfig.getString(I18n.var_Stocke), " -> {");
				tl(3, "String var", langueConfig.getString(I18n.var_Stocke), "2 = var", langueConfig.getString(I18n.var_Stocke), ";");
				tl(3, "if(StringUtils.contains(var", langueConfig.getString(I18n.var_Stocke), "2, \"}\"))");
				tl(4, "var", langueConfig.getString(I18n.var_Stocke), "2 = StringUtils.substringAfterLast(var", langueConfig.getString(I18n.var_Stocke), "2, \"}\");");
				tl(3, "String[] parts = var", langueConfig.getString(I18n.var_Stocke), "2.split(\",\");");
				tl(3, "for(String part : parts) {");
				tl(4, "if(StringUtils.isNotBlank(part)) {");
				tl(5, "String var = ", classeNomSimple, ".", langueConfig.getString(I18n.var_recherche), "Var", classeNomSimple, "(part);");
				tl(5, "if(StringUtils.isNotBlank(var))");
				tl(6, "l.add(var);");
				tl(4, "}");
				tl(3, "}");
				tl(2, "});");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _default", langueConfig.getString(I18n.var_Stats), "Vars(List<String> l) {");
				tl(2, "Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getStatsFields()).orElse(Arrays.asList()).forEach(var", langueConfig.getString(I18n.var_Indexe), " -> {");
				tl(3, "String var", langueConfig.getString(I18n.var_Indexe), "2 = var", langueConfig.getString(I18n.var_Indexe), ";");
				tl(3, "if(StringUtils.contains(var", langueConfig.getString(I18n.var_Indexe), "2, \"}\"))");
				tl(4, "var", langueConfig.getString(I18n.var_Indexe), "2 = StringUtils.substringAfterLast(var", langueConfig.getString(I18n.var_Indexe), "2, \"}\");");
				tl(3, "String[] parts = var", langueConfig.getString(I18n.var_Indexe), "2.split(\",\");");
				tl(3, "for(String part : parts) {");
				tl(4, "if(StringUtils.isNotBlank(part)) {");
				tl(5, "String var = ", classeNomSimple, ".", langueConfig.getString(I18n.var_recherche), "Var", classeNomSimple, "(part);");
				tl(5, "if(StringUtils.isNotBlank(var))");
				tl(6, "l.add(var);");
				tl(4, "}");
				tl(3, "}");
				tl(2, "});");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _default", langueConfig.getString(I18n.var_Pivot), "Vars(List<String> l) {");
				tl(2, "Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetPivots()).orElse(Arrays.asList()).forEach(facetPivot -> {");
				tl(3, "String facetPivot2 = facetPivot;");
				tl(3, "if(StringUtils.contains(facetPivot2, \"}\"))");
				tl(4, "facetPivot2 = StringUtils.substringAfterLast(facetPivot2, \"}\");");
				tl(3, "String[] parts = facetPivot2.split(\",\");");
				tl(3, "for(String part : parts) {");
				tl(4, "if(StringUtils.isNotBlank(part)) {");
				tl(5, "String var = ", classeNomSimple, ".", langueConfig.getString(I18n.var_recherche), "Var", classeNomSimple, "(part);");
				tl(5, "if(StringUtils.isNotBlank(var))");
				tl(6, "l.add(var);");
				tl(4, "}");
				tl(3, "}");
				tl(2, "});");
				tl(1, "}");
				l();
				tl(1, "/**");
				tl(1, " * {@inheritDoc}");
				tl(1, " **/");
				tl(1, "protected void _", langueConfig.getString(I18n.var_liste), classeApiClasseNomSimple, "(JsonArray l) {");
				tl(2, "Optional.ofNullable(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_).map(o -> o.get", langueConfig.getString(I18n.var_Liste), "()).orElse(Arrays.asList()).stream().map(o -> JsonObject.mapFrom(o)).forEach(o -> l.add(o));");
				tl(1, "}");
				l();
				tl(1, "protected void _", uncapitalizeClasseApiClasseNomSimple, "Count(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(I18n.var_cVar), ") {");
				tl(2, langueConfig.getString(I18n.var_cVar), ".o(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_ == null ? 0 : ", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.size());");
				tl(1, "}");
			}
			l();
			tl(1, "protected void _", uncapitalizeClasseApiClasseNomSimple, "_(", classePartsCouverture.nomSimple(langueNom), "<", classeApiClasseNomSimple, "> ", langueConfig.getString(I18n.var_cVar), ") {");
			if(classePageSimple) {
				tl(2, langueConfig.getString(I18n.var_cVar), ".o(new ", classeApiClasseNomSimple, "());");
			} else {
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "Count == 1 && Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_RequeteService), "().getParams().getJsonObject(\"path\")).map(o -> o.getString(\"id\")).orElse(null) != null)");
				tl(3, langueConfig.getString(I18n.var_cVar), ".o(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.get(0));");
			}
			tl(1, "}");
			if(!classePageSimple) {
				if(classeModele) {
					l();
					tl(1, "protected void _", classeModele ? classeVarClePrimaire : classeVarCleUnique, "(", classePartsCouverture.nomSimple(langueNom), "<Long> ", langueConfig.getString(I18n.var_cVar), ") {");
					tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "_ != null)");
					tl(3, langueConfig.getString(I18n.var_cVar), ".o(", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarClePrimaire), "());");
					tl(1, "}");
				}
				l();
				tl(1, "protected void _", classeVarCleUnique, "(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(I18n.var_cVar), ") {");
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "_ != null)");
				tl(3, langueConfig.getString(I18n.var_cVar), ".o(", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarCleUnique), "());");
				tl(1, "}");
			}

			l();
			if(classePageSuperNomSimple != null) {
				tl(1, "@Override");
			} else {
				tl(1, "/**");
				tl(1, " * ", langueConfig.getString(I18n.var_Ignorer), ": true");
				tl(1, "**/");
			}
			tl(1, "protected void _promise", langueConfig.getString(I18n.var_Avant), "(Promise<Void> promise) {");
			tl(2, "promise.complete();");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _", classePageLangueConfig.getString(I18n.var_classeNomSimple), "(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(I18n.var_cVar), ") {");
			tl(2, langueConfig.getString(I18n.var_cVar), ".o(\"", classeApiClasseNomSimple, "\");");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _page", langueConfig.getString(I18n.var_Titre), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
			if(classeVarTitre != null) {
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "_ != null && ", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarTitre), "() != null)");
				tl(3, "c.o(", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarTitre), "()", ");");
				tl(2, "else if(", uncapitalizeClasseApiClasseNomSimple, "_ != null)");
			} else {
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "_ != null)");
			}
			tl(3, "c.o(", q(classeTitre), ");");
			if(!classePageSimple) {
				tl(2, "else if(", langueConfig.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_ == null || ", uncapitalizeClasseApiClasseNomSimple, "Count == 0)");
				tl(3, "c.o(", q(classeAucunNomTrouve), ");");
			}
			if(classeTitre != null) {
				tl(2, "else");
				tl(3, "c.o(", q(classeTitre), ");");
			}
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _pageUri(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
			tl(2, "c.o(", q(classePageUriMethode), ");");
			tl(1, "}");
			for(String pageLangueNom : toutesLangues) {
				if(!StringUtils.equals(classePageLangueNom, pageLangueNom)) {
					String classePageUriMethodeLangue = classeDoc.getString(StringUtils.replace("classeApiUri_stored_string", StringUtils.capitalize(classePageLangueNom), StringUtils.capitalize(pageLangueNom)));
					
					if(classePageUriMethodeLangue != null) {
						l();
						if(classePageSuperNomSimple != null)
							tl(1, "@Override");
						tl(1, "protected void _pageUri", StringUtils.capitalize(pageLangueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
						tl(2, "c.o(", q(classePageUriMethodeLangue), ");");
						tl(1, "}");
					}
				}
			}

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _apiUri(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
			tl(2, "c.o(", q(classeApiUri), ");");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null) {
				tl(1, "@Override");
			} else {
				tl(1, "/**");
				tl(1, " * ", langueConfig.getString(I18n.var_Ignorer), ": true");
				tl(1, "**/");
			}
			tl(1, "protected void _promise", langueConfig.getString(I18n.var_Apres), "(Promise<Void> promise) {");
			tl(2, "promise.complete();");
			tl(1, "}");

			if(classeDescription != null) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pageDescription(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(classeDescription), ");");
				tl(1, "}");
			}

			if(classeImage != null) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pageImageUri(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q("/png", classePageUriMethode, "-999.png"), ");");
				tl(1, "}");
			}

			if(classeImageLargeur != null) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pageImage", langueConfig.getString(I18n.var_Largeur), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
				tl(3, "c.o(", classeImageLargeur, ");");
				tl(1, "}");
			}

			if(classeImageHauteur != null) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pageImage", langueConfig.getString(I18n.var_Hauteur), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
				tl(3, "c.o(", classeImageHauteur, ");");
				tl(1, "}");
			}

			if(StringUtils.isNotBlank(classeVideoId)) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pageVideoId(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(classeVideoId), ");");
				tl(1, "}");
			}

			if(StringUtils.isNotBlank(classeIcone)) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", langueConfig.getString(I18n.var_classe), langueConfig.getString(I18n.var_Icone), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(classeIcone), ");");
				tl(1, "}");
			}

//			l();
//			if(classePageSuperNomSimple != null)
//				tl(1, "@Override");
//			tl(1, "public void ", langueConfig.getString(ConfigCles.var_promesseLoin), classeGenPageNomSimple, "() {");
//			tl(2, langueConfig.getString(ConfigCles.var_promesseLoin), classeGenPageNomSimple, "();");
////				tl(2, "super.", langueConfig.getString(ConfigCles.langueConfig.getString(ConfigCles.var_initLoin), classePartsMiseEnPage.nomSimple), "();");
//			tl(1, "}");

			if(StringUtils.isNotBlank(classeApiUri)) {
				l();
				tl(1, "protected void _pageUri", classeApiClasseNomSimple, "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(classePageUriMethode), ");");
				tl(1, "}");
			}
		}
	}

	public void pageCodeClasseJinja(String langueNom, JsonObject i18nPage) throws Exception {

		if(classePageCheminsGen.contains(classeGenPageChemin) && classeGenPageChemin != null && StringUtils.equals(classePageLangueNom, langueNom)) {

			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + classesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				// rechercheSolr.addFilterQuery("entiteEstSubstitue_indexed_boolean:false");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + this.langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
				rechercheSolr.addFilterQuery("entiteHtmColonne_indexed_int:[* TO *]");
				rechercheSolr.addSort("entiteHtmColonne_indexed_int", ORDER.asc);
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
							String entiteSolrNomSimple = (String)entiteDocumentSolr.get("entiteSolrNomSimple_stored_string");
							String entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + langueNom + "_stored_string");
							String entiteNomCanonique = (String)entiteDocumentSolr.get("entiteNomCanonique_" + langueNom + "_stored_string");
							String entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
							String entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
							String entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
							String entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
							Boolean entiteHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean"));
							String entiteFormatHtm = (String)entiteDocumentSolr.get("entiteFormatHtm_stored_string");
							Boolean entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
							Boolean entiteHighlighting = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHighlighting_stored_boolean"));
							Boolean entiteVarTitre = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteVarTitre_stored_boolean"));
							Boolean entiteLien = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteLien_stored_boolean"));
							Boolean entiteFacetsTrouves = Optional.ofNullable((Boolean)entiteDocumentSolr.get("entiteFacetsTrouves_stored_boolean")).orElse(false);
							List<String> entiteFacets = Optional.ofNullable((List<String>)entiteDocumentSolr.get("entiteFacets_stored_strings")).orElse(Arrays.asList());
							if(entiteHtml) {
								String jsVal = ".value";
								if("Boolean".equals(entiteNomSimple)) {
									jsVal = ".checked";
								}
								wTh.tl(7, "<", composantsWebPrefixe, "dropdown>");
								wTh.tl(8, "<", composantsWebPrefixe, "button slot=\"trigger\" caret>", entiteNomAffichage, "</", composantsWebPrefixe, "button>");
								wTh.tl(8, "<", composantsWebPrefixe, "menu>");
								wTh.tl(9, "<", composantsWebPrefixe, "menu-item>");
								wTh.tl(10, "<i class=\"fa-solid fa-arrow-down-a-z\"></i>");
								wTh.t(10).sx(String.format(i18nPage.getString(I18n.str_trier_par___croissante), entiteNomAffichage)).l();
								wTh.tl(9, "</", composantsWebPrefixe, "menu-item>");
								wTh.tl(9, "<", composantsWebPrefixe, "menu-item>");
								wTh.tl(10, "<i class=\"fa-solid fa-arrow-down-z-a\"></i>");
								wTh.t(10).sx(String.format(i18nPage.getString(I18n.str_trier_par___decroissante), entiteNomAffichage)).l();
								wTh.tl(9, "</", composantsWebPrefixe, "menu-item>");
								wTh.tl(8, "</", composantsWebPrefixe, "menu>");
								wTh.tl(7, "</", composantsWebPrefixe, "dropdown>");
								if(wTd.getEmpty()) {
									wTd.t(8, "<div>");
									wTd.tl(9, classeIcone);
								} else {
									wTd.t(8, "<div>");
								}

								if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
									wTd.s("<", composantsWebPrefixe, "format-date weekday=\"short\" month=\"short\" day=\"numeric\" year=\"numeric\" hour=\"numeric\" minute=\"numeric\" second=\"numeric\" time-zone-name=\"short\" date=\"{{ formatZonedDateTime(item.", entiteVar, ", \"yyyy-MM-dd'T'HH:mm:ss.SSSX\", defaultLocaleId, \"UTC\") }}\"></", composantsWebPrefixe, "format-date>");
								} else if(StringUtils.equals(entiteNomCanonique, LocalDate.class.getCanonicalName())) {
									wTd.s("{{ formatLocalDate(item.", entiteVar, ", \"", entiteFormatHtm, "\", defaultLocaleId) }}");
								} else if(StringUtils.equals(entiteNomCanonique, LocalDateTime.class.getCanonicalName())) {
									wTd.s("{{ formatLocalDateTime(item.", entiteVar, ", \"", entiteFormatHtm, "\", defaultLocaleId) }}");
								} else if(StringUtils.equals(entiteNomCanonique, LocalTime.class.getCanonicalName())) {
									wTd.s("{{ formatLocalTime(item.", entiteVar, ", \"", entiteFormatHtm, "\", defaultLocaleId) }}");
								} else if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())) {
									wTd.s("{{ formatNumber(item.", entiteVar, ", \"", entiteFormatHtm, "\", defaultLocaleId) }}");
								} else {
									wTd.s("{{ item.", entiteVar, " }}");
								}
								wTd.l("</div>");
								if(entiteHighlighting) {
									wTd.tl(8, "{% if highlightList is defined %}");
									wTd.tl(8, "<div class=\"site-highlight \">");
										wTd.tl(9, "StringUtils.join(highlightList, \" ... \")");
									wTd.tl(8, "</div>");
									wTd.tl(8, "{% endif %}");
								}
							}

							wFoot.tl(11, "{% if get", i18nPage.getString(I18n.var_Colonne), entiteVarCapitalise, " is defined %}");
							wFoot.tl(12, "<div>");
							if(entiteFacetsTrouves) {
								for(String entiteFacet : entiteFacets) {
									if("sum".equals(entiteFacet)) {
		
										if("Double".equals(entiteSolrNomSimple))
											wFoot.tl(4, "BigDecimal ", entiteFacet, "_", entiteVar, " = Optional.ofNullable((", entiteSolrNomSimple, ")facets.get(\"", entiteFacet, "_", entiteVar, "\")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));");
										else
											wFoot.tl(4, entiteSolrNomSimple, " ", entiteFacet, "_", entiteVar, " = Optional.ofNullable((", entiteSolrNomSimple, ")facets.get(\"", entiteFacet, "_", entiteVar, "\")).orElse(new ", entiteSolrNomSimple, "(0));");

										wFoot.tl(4, "<span class=\"\"font-weight-bold \">", entiteFacet, "_", entiteVar, "</span>");
									}
								}
							}
							wFoot.tl(12, "</div>");
							wFoot.tl(11, "{% endif %}");
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
				String fqClassesSuperEtMoi = "(" + classesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr.addFilterQuery("entiteEstSubstitue_indexed_boolean:false");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
				rechercheSolr.addSort("entiteHtmLigne_indexed_int", ORDER.asc);
				rechercheSolr.addSort("entiteHtmCellule_indexed_int", ORDER.asc);
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
							entiteAttribuer = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteAttribuer_stored_boolean"));
							entiteListeTypeJson = (String)entiteDocumentSolr.get("entiteListeTypeJson_stored_string");

							if(entiteIndexe) {
	
								wRecherche.l();
								if("Boolean".equals(entiteNomSimple)) {
									wRecherche.tl(2, "var $", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, "Checkbox = $", i18nPage.getString(I18n.var_formulaireFiltres), ".querySelector('input.", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "[type = \"checkbox\"]');");
									wRecherche.tl(2, "var $", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, "Select = $", i18nPage.getString(I18n.var_formulaireFiltres), ".querySelector('select.", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "');");
									wRecherche.tl(2, "var ", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, "Select.length ? $", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, "Select.value : $", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, "Checkbox.checked;");

									wRecherche.tl(2, "var ", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, "SelectVal = $", i18nPage.getString(I18n.var_formulaireFiltres), ".querySelector('select.", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, "')?.value;");
									wRecherche.tl(2, "var ", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, " = null;");
									wRecherche.tl(2, "if(", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, "SelectVal !== '')");
									wRecherche.tl(3, i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, " = ", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, "SelectVal == 'true';");
								}
								else {
									wRecherche.tl(2, "var ", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireFiltres), ".querySelector('.", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "')?.value;");
								}

								if("Boolean".equals(entiteNomSimple))
									wRecherche.tl(2, "if(", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, " != null && ", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, " === true)");
								else
									wRecherche.tl(2, "if(", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, " != null && ", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, " !== '')");

								wRecherche.tl(3, i18nPage.getString(I18n.var_filtres), ".push({ name: '", (entiteSuggere ? "q" : "fq"), "', value: '", entiteVar, ":' + ", i18nPage.getString(I18n.var_filtre), entiteVarCapitalise, " });");

								wVarsFqJs.tl(1, "vars.push({ var: '", entiteVar, "', "
										, "var", i18nPage.getString(I18n.var_Indexe), ": '", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "'"
										, ", ", i18nPage.getString(I18n.var_nomAffichage), ": ", entiteNomAffichage == null ? "null" : "'" + entiteNomAffichage + "'", "});");
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
								if(entiteAttribuer) {
									if(entiteListeTypeJson == null) {
										wPOST.tl(1, "var ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "')?.value;");
										wPOST.tl(1, "if(", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " != null && ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " !== '')");
										if("Boolean".equals(entiteNomSimple)) {
											wPOST.tl(2, "vals['", entiteVar, "'] = ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " == 'true';");
										} else {
											wPOST.tl(2, "vals['", entiteVar, "'] = ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, ";");
										}
									}
									else {
										wPOST.tl(1, "var ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " = [];");
										wPOST.tl(1, "$", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('input.", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, ":checked').each(function(index) {");
										wPOST.tl(2, i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, ".push(this.value);");
										wPOST.tl(1, "});");
										wPOST.tl(1, "if(", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, ".length > 0)");
										wPOST.tl(2, "vals['", entiteVar, "'] = ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, ";");
									}
								} else {
									wPOST.tl(1, "var ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "')?.value;");
									wPOST.tl(1, "if(", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " != null && ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " !== '')");
									if("Boolean".equals(entiteNomSimple)) {
										wPOST.tl(2, "vals['", entiteVar, "'] = ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " == 'true';");
									} else if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson)) {
										wPOST.tl(2, "vals['", entiteVar, "'] = JSON.parse(", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, ");");
									} else {
										wPOST.tl(2, "vals['", entiteVar, "'] = ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, ";");
									}
								}
	
								wPUTCopie.l();
								if(entiteAttribuer)
									wPUTCopie.tl(1, "var ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('input.", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, ":checked')?.value;");
								else
									wPUTCopie.tl(1, "var ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "')?.value;");
								if(entiteAttribuer) {
									wPUTCopie.tl(1, "var ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, i18nPage.getString(I18n.var_Vider), " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('input.", entiteVar, "_", i18nPage.getString(I18n.var_vider), ":checked')?.value;");
									wPUTCopie.tl(1, "if(", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, i18nPage.getString(I18n.var_Vider), " != null && ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, i18nPage.getString(I18n.var_Vider), ")");
									wPUTCopie.tl(2, "vals['", entiteVar, "'] = null;");
									wPUTCopie.tl(1, "else if(", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " != null && ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, ")");
									if(entiteListeTypeJson == null) {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									}
									else {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = [", valPrefixe, i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, "];");
									}
								} else {
									wPUTCopie.tl(1, "if(", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " != null && ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " !== '')");
									if("Boolean".equals(entiteNomSimple)) {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, " == 'true';");
									} else if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson)) {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = JSON.parse(", valPrefixe, i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, ");");
									} else {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									}
								}
	
								wPATCH.l();
								if(entiteAttribuer)
									wPATCH.tl(1, "var ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('input.", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, ":checked')?.value;");
								else
									wPATCH.tl(1, "var ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "')?.value;");
								if(entiteAttribuer) {
									wPATCH.tl(1, "if(", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " != null && ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " !== '')");
									if(entiteListeTypeJson == null) {
										wPATCH.tl(2, "vals['set", entiteVarCapitalise, "'] = ", valPrefixe, i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									} else {
										wPATCH.tl(2, "vals['add", entiteVarCapitalise, "'] = ", valPrefixe, i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									}
								} else {

									wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.remove", entiteVarCapitalise, "')?.value === 'true';");

									if("Boolean".equals(entiteNomSimple)) {
										wPATCH.tl(1, "if(", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " != null)");
										wPATCH.tl(2, i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " = ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " === 'true';");
										wPATCH.tl(1, "var ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('select.set", entiteVarCapitalise, "')?.value;");
										wPATCH.tl(1, "if(", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal != null)");
										wPATCH.tl(2, i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal = ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal === 'true';");
										wPATCH.tl(1, "if(", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal != null && ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal !== '')");
										wPATCH.tl(2, i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " = ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal == 'true';");
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : ", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, ";");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.add", entiteVarCapitalise, "')?.checked;");
									}
									else if("LocalDate".equals(entiteNomSimple)) {
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.set", entiteVarCapitalise, "')?.value;");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.add", entiteVarCapitalise, "')?.value;");
										wPATCH.tl(1, "var setMoment = set", entiteVarCapitalise, valSuffixe, " ? moment(", valPrefixe, "set", entiteVarCapitalise, valSuffixe, ", '", i18nPage.getString(I18n.var_DDDashMMDashYYYY), "') : null; ");
										wPATCH.tl(1, "var addMoment = add", entiteVarCapitalise, valSuffixe, " ? moment(", valPrefixe, "add", entiteVarCapitalise, valSuffixe, ", '", i18nPage.getString(I18n.var_DDDashMMDashYYYY), "') : null; ");
										wPATCH.tl(1, "if(setMoment) { ");
											wPATCH.tl(2, "var s = setMoment.format('YYYY-MM-DD'); ");
											wPATCH.tl(2, "set", entiteVarCapitalise, " = s;");
										wPATCH.tl(1, "} ");
										wPATCH.tl(1, "if(addMoment) { ");
											wPATCH.tl(2, "var s = addMoment.format('YYYY-MM-DD'); ");
											wPATCH.tl(2, "add", entiteVarCapitalise, " = s;");
										wPATCH.tl(1, "} ");
									}
									else {
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.set", entiteVarCapitalise, "')?.value;");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.add", entiteVarCapitalise, "')?.value;");
									}
									wPATCH.tl(1, "if(remove", entiteVarCapitalise, " || set", entiteVarCapitalise, " != null && set", entiteVarCapitalise, " !== '')");
									if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson)) {
										wPATCH.tl(2, "vals['set", entiteVarCapitalise, "'] = JSON.parse(", valPrefixe, "set", entiteVarCapitalise, valSuffixe, ");");
									} else {
										wPATCH.tl(2, "vals['set", entiteVarCapitalise, "'] = ", valPrefixe, "set", entiteVarCapitalise, valSuffixe, ";");
									}
									wPATCH.tl(1, "if(add", entiteVarCapitalise, " != null && add", entiteVarCapitalise, " !== '')");
									wPATCH.tl(2, "vals['add", entiteVarCapitalise, "'] = ", valPrefixe, "add", entiteVarCapitalise, valSuffixe, ";");
									if("Boolean".equals(entiteNomSimple)) {
										wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.remove", entiteVarCapitalise, "')?.checked;");
									} else {
										wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.remove", entiteVarCapitalise, "')?.value;");
									}
									wPATCH.tl(1, "if(remove", entiteVarCapitalise, " != null && remove", entiteVarCapitalise, " !== '')");
									wPATCH.tl(2, "vals['remove", entiteVarCapitalise, "'] = ", valPrefixe, "remove", entiteVarCapitalise, valSuffixe, ";");
								}
							} 
						}
						rechercheSolr.setStart(i.intValue() + rechercheLignes);
						rechercheReponse = clientSolrComputate.query(rechercheSolr);
						rechercheListe = rechercheReponse.getResults();
					}
				}
			}

			/////////////////
			// htmBodyMenu //
			/////////////////

			ecrirePageJs(langueNom, i18nPage);
			// ecrirePageEditionJinja(langueNom, i18nPage);
			// ecrirePageRechercheJinja(langueNom, i18nPage);

			//STUFF0
			o = auteurGenPageEditionJinja;
			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");

				if(classeApiMethode.equals(i18nPage.getString(I18n.var_PageRecherche)) || classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals("DELETE") || classeApiMethode.equals(i18nPage.getString(I18n.var_PUTCopie)) || classeApiMethode.equals(i18nPage.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
					String methodeTitreFiltres = null;
					String methodeTitreValeurs = null;
					String methodeTitreCourt = null;

					if("POST".equals(classeApiMethodeMethode)) {
						methodeTitreCourt = i18nPage.getString(I18n.str_Creer);
						methodeTitreValeurs = i18nPage.getString(I18n.str_Creer_) + classeUnNom;
					}
					else if("PUTImport".equals(classeApiMethode)) {
						methodeTitreCourt = i18nPage.getString(I18n.str_Importer);
						methodeTitreFiltres = i18nPage.getString(I18n.str_Importer_) + classeUnNom;
						methodeTitreValeurs = i18nPage.getString(I18n.str_Importer_) + classeNomPluriel;
					}
					else if(i18nPage.getString(I18n.var_PUTFusion).equals(classeApiMethode)) {
						methodeTitreCourt = i18nPage.getString(I18n.str_Fusionner);
						methodeTitreFiltres = i18nPage.getString(I18n.str_Fusionner_) + classeUnNom;
						methodeTitreValeurs = i18nPage.getString(I18n.str_Fusionner_) + classeNomPluriel;
					}
					else if(i18nPage.getString(I18n.var_PUTCopie).equals(classeApiMethode)) {
						methodeTitreCourt = i18nPage.getString(I18n.str_Dupliquer);
						methodeTitreFiltres = i18nPage.getString(I18n.str_Dupliquer_) + classeUnNom;
						methodeTitreValeurs = i18nPage.getString(I18n.str_Dupliquer_) + classeNomSingulier;
					}
					else if("PATCH".equals(classeApiMethodeMethode)) {
						methodeTitreCourt = i18nPage.getString(I18n.str_Modifier);
						methodeTitreFiltres = i18nPage.getString(I18n.str_Modifier_des_) + classeUnNom;
						methodeTitreValeurs = i18nPage.getString(I18n.str_Modifier_des_) + classeNomSingulier;
					}
					else if("DELETE".equals(classeApiMethodeMethode)) {
						methodeTitreCourt = i18nPage.getString(I18n.str_Supprimer);
						methodeTitreFiltres = i18nPage.getString(I18n.str_Supprimer_) + classeUnNom;
						methodeTitreValeurs = i18nPage.getString(I18n.str_Supprimer_) + classeNomSingulier;
					}
					else {
						methodeTitreCourt = i18nPage.getString(I18n.str_Rechercher);
						methodeTitreFiltres = i18nPage.getString(I18n.str_Rechercher_) + classeUnNom;
						methodeTitreValeurs = i18nPage.getString(I18n.str_Rechercher_) + classeNomPluriel;
					}

					l();
					s("{%- macro htm", i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() %}");
					if(!classeApiMethode.equals(i18nPage.getString(I18n.var_PageRecherche))) {
						s("<", composantsWebPrefixe, "button");
						s(" id=\"htm", i18nGlobale.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "\"");
						s(" data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "=\"{{ ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " }}\"");
						s(">");
	
							if(classeApiMethodeMethode.contains("POST"))
								s("<i slot=\"prefix\" class=\"fas fa-file-plus \"></i>");
							else if(classeApiMethodeMethode.contains("PATCH"))
								s("<i slot=\"prefix\" class=\"fas fa-edit \"></i>");
							else if(classeApiMethodeMethode.contains("DELETE"))
								s("<i slot=\"prefix\" class=\"fas fa-trash \"></i>");
							else if(classeApiMethode.contains("PUTImport"))
								s("<i slot=\"prefix\" class=\"fas fa-file-import \"></i>");
							else if(classeApiMethode.contains(i18nPage.getString(I18n.var_PUTFusion)))
								s("<i slot=\"prefix\" class=\"fas fa-code-merge \"></i>");
							else if(classeApiMethode.contains(i18nPage.getString(I18n.var_PUTCopie)))
								s("<i slot=\"prefix\" class=\"fas fa-copy \"></i>");
	
							s(methodeTitreCourt);
						s("</", composantsWebPrefixe, "button>");
					}
					l("{%- endmacro %}");

					l();
					l("{%- macro htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() %}");
					tl(6, "<", composantsWebPrefixe, "button slot=\"footer\" type=\"submit\" variant=\"primary\"");
					tl(8, "id=\"htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "\"");
					tl(8, ">", methodeTitreValeurs, "</", composantsWebPrefixe, "button>");
					l("{%- endmacro %}");

					l();
					l("{%- macro htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() %}");
					{ tl(4, "<", classeApiMethode.equals(i18nPage.getString(I18n.var_PageRecherche)) ? "div" : "", composantsWebPrefixe, "dialog", " id=\"", classeApiOperationIdMethode, i18nPage.getString(I18n.var_Dialogue), "\" label=\"", methodeTitreValeurs, "\">");
						{ tl(5, "<", classeApiMethode.equals(i18nPage.getString(I18n.var_PageRecherche)) ? "div" : "form", " id=\"htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "\" class=\"round-first-and-last-row-x-large \">");
							if(!i18nPage.getString(I18n.var_PageRecherche).equals(classeApiMethode)) {
								if("POST".equals(classeApiMethode)) {
									tl(6, "<div class=\"\">", i18nPage.getString(I18n.str_Vous_pouvez_remplacer_les_valeurs_par_defaut_ci_dessous), "</div>");
								}
							}
	
							if("PATCH".equals(classeApiMethode) || i18nPage.getString(I18n.var_PUTFusion).equals(classeApiMethode) || i18nPage.getString(I18n.var_PUTCopie).equals(classeApiMethode) || "PUTImport".equals(classeApiMethode)) {
	
								if("PUTImport".equals(classeApiMethode)) {
									tl(6, "<div>");
									tl(7, "<", composantsWebPrefixe, "textarea");
									tl(9, "class=\"", "PUTImport_", i18nPage.getString(I18n.var_listeRecherche), " \"");
									tl(9, "style=\"height: 300px; \"");
									tl(9, "placeholder=\"{ '", i18nPage.getString(I18n.var_listeRecherche), "': [ { 'pk': ... , '", i18nPage.getString(I18n.var_sauvegardes), "': [ ... ] }, ... ] }\"");
									tl(9, ">");
									tl(7, "</", composantsWebPrefixe, "textarea>");
									tl(6, "</div>");
								} else if(i18nPage.getString(I18n.var_PUTFusion).equals(classeApiMethode)) {
									tl(6, "<div>");
									tl(7, "<", composantsWebPrefixe, "textarea");
									tl(9, "class=\"", "PUT", i18nPage.getString(I18n.var_PUTFusion), "_", i18nPage.getString(I18n.var_listeRecherche), " \"");
									tl(9, "style=\"height: 300px; \"");
									tl(9, "placeholder=\"{ '", i18nPage.getString(I18n.var_listeRecherche), "': [ { 'pk': ... , '", i18nPage.getString(I18n.var_sauvegardes), "': [ ... ] }, ... ] }\"");
									tl(9, ">");
									tl(7, "</", composantsWebPrefixe, "textarea>");
									tl(6, "</div>");
								} else if(i18nPage.getString(I18n.var_PUTCopie).equals(classeApiMethode)) {
									s(wFormPUTCopie);
								} else if("PATCH".equals(classeApiMethodeMethode)) {
									s(wFormPATCH);
								}
	
							} else {
								if("POST".equals(classeApiMethode)) {
									s(wFormPOST);
								} else if(i18nPage.getString(I18n.var_Recherche).equals(classeApiMethode)) {
									s(wFormRecherche);
								} if(i18nPage.getString(I18n.var_PageRecherche).equals(classeApiMethode)) {
									s(wFormPage);
								}
							}
						} tl(5, "</", classeApiMethode.equals(i18nPage.getString(I18n.var_PageRecherche)) ? "div" : "form", ">");
						if(!classeApiMethode.equals(i18nPage.getString(I18n.var_PageRecherche))) {
							tl(5, "{{ htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
						}
					} tl(4, "</", classeApiMethode.equals(i18nPage.getString(I18n.var_PageRecherche)) ? "div" : "", composantsWebPrefixe, "dialog", ">");
					l("{%- endmacro %}");
				}
			}

			//STUFF0
			//STUFF1
	
			ecrirePageRechercheJinja(langueNom, i18nPage);
			ecrirePageEditionJinja(langueNom, i18nPage);
	
			o = auteurGenPageClasse;

			tl(0, "}");

			wTh.flushClose();

			auteurGenPageClasse.flushClose();
			if(auteurPageClasse != null) {
				auteurPageClasse.flushClose();
			}
			auteurPageCss.flushClose();
			auteurPageJs.flushClose();
			auteurPageJsModule.flushClose();

			if(auteurBarreLateraleJinja != null) {
				auteurBarreLateraleJinja.flushClose();
			}

			if(auteurPageRechercheJinja != null) {
				auteurPageRechercheJinja.flushClose();
			}
			if(auteurGenPageRechercheJinja != null) {
				auteurGenPageRechercheJinja.flushClose();
			}
			if(auteurPageEditionJinja != null) {
				auteurPageEditionJinja.flushClose();
			}
			if(auteurGenPageEditionJinja != null) {
				auteurGenPageEditionJinja.flushClose();
			}
			if(auteurPageAffichageJinja != null) {
				auteurPageAffichageJinja.flushClose();
			}
			if(auteurPageUtilisateurJinja != null) {
				auteurPageUtilisateurJinja.flushClose();
			}

			{
				RegarderClasse regarderClasse = new RegarderClasse();
				regarderClasse.siteChemin = siteChemin;
				regarderClasse.siteNom = siteNom;
				regarderClasse.classeCheminAbsolu = classePageChemin;
				regarderClasse.cheminSrcGenJava = cheminSrcGenJava;
				regarderClasse.cheminSrcMainJava = cheminSrcMainJava;
				regarderClasse.cheminSrcMainResources = cheminSrcMainResources;
				regarderClasse.initRegarderClasseBase(classeLangueNom, i18nGlobale); 
				SolrInputDocument classeDoc = new SolrInputDocument();
				regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu, classeDoc, langueNom);
				regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom, i18nPage);
			}

			{
				RegarderClasse regarderClasse = new RegarderClasse();
				regarderClasse.siteChemin = siteChemin;
				regarderClasse.siteNom = siteNom;
				regarderClasse.classeCheminAbsolu = classeGenPageChemin;
				regarderClasse.cheminSrcGenJava = cheminSrcGenJava;
				regarderClasse.cheminSrcMainJava = cheminSrcMainJava;
				regarderClasse.cheminSrcMainResources = cheminSrcMainResources;
				regarderClasse.initRegarderClasseBase(classeLangueNom, i18nGlobale); 
				SolrInputDocument classeDoc = new SolrInputDocument();
				regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu, classeDoc, langueNom);
				regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom, i18nPage);
			}
			//STUFF1
		}
	}

	public void ecrirePageHeadJinja(String langueNom, JsonObject i18nPage) throws Exception {
			l();
			tl(0, "{%- block htmStyles", classePageSuperNomSimple, " %}");
			tl(0, "{{ super() }}");
			tl(0, "{%- block htmStyles", classePageNomSimple, " %}");
			tl(0, "{%- endblock htmStyles", classePageNomSimple, " %}");
			tl(0, "{%- endblock htmStyles", classePageSuperNomSimple, " %}");
			l();
			tl(0, "{%- block htmStyle", classePageSuperNomSimple, " %}");
			tl(0, "{{ super() }}");
			tl(0, "{%- block htmStyle", classePageNomSimple, " %}");
			tl(0, "{%- endblock htmStyle", classePageNomSimple, " %}");
			tl(0, "{%- endblock htmStyle", classePageSuperNomSimple, " %}");
			l();
			tl(0, "{%- block htmScripts", classePageSuperNomSimple, " %}");
			tl(0, "{{ super() }}");
			tl(0, "{%- block htmScripts", classePageNomSimple, " %}");
			if(classeApi) {
				tl(2, "<script src=\"{{", i18nPage.getString(I18n.var_statiqueUrlBase), "}}/js/", langueNom, "/", classePageNomSimple, ".js\"></script>");
				tl(2, "<script type=\"module\" src=\"{{", i18nPage.getString(I18n.var_statiqueUrlBase), "}}/js/", langueNom, "/", classePageNomSimple, "Module.js\"></script>");
				if(classeAttribuerNomSimplePages != null) {
					for(String classeAttribuerNomSimplePage : classeAttribuerNomSimplePages) {
						t(2).l("<script src=\"{{", i18nPage.getString(I18n.var_statiqueUrlBase), "}}/js/", langueNom, "/", classeAttribuerNomSimplePage, ".js\"></script>");
					}
				}
			}
			tl(0, "{%- endblock htmScripts", classePageNomSimple, " %}");
			tl(0, "{%- endblock htmScripts", classePageSuperNomSimple, " %}");

			if(!classePageSimple) {

				l();
				tl(0, "{%- block htmScript", classePageSuperNomSimple, " %}");
				tl(0, "{%- block htmScript", classePageNomSimple, " %}");
				tl(2, "<script>");
				l();
				tl(3, "function ", i18nPage.getString(I18n.var_jsWebsocket), classeNomSimple, "(", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, ", vars, $response) {");
				tl(0, "{%- block ", i18nPage.getString(I18n.var_jsWebsocket), classePageNomSimple, " %}{%- endblock %}");
				tl(3, "}");
				l();
				tl(3, "function ", i18nPage.getString(I18n.var_jsInfobulle), classeNomSimple, "(e, feature) {");
				tl(0, "{%- block ", i18nPage.getString(I18n.var_jsInfobulle), classePageNomSimple, " %}{%- endblock %}");
				tl(3, "}");
				l();
				tl(3, "function ", i18nPage.getString(I18n.var_htmInfobulle), classeNomSimple, "(feature, layer) {");
				tl(4, "return `{%- block ", i18nPage.getString(I18n.var_htmInfobulle), classePageNomSimple, " %}");
				s("<h3>");
				if(i18nPage.getString(I18n.var_classeIconeClassesCss) != null)
					s("<i class=\"{{ ", i18nPage.getString(I18n.var_classeIconeClassesCss), " }}  \"></i>");
				s("<a href=\"${quoteattr(feature.properties.pageUrlPk)}\">${feature.properties.objectTitle}</a>");
				l("</h3>");
				l("{%- endblock ", i18nPage.getString(I18n.var_htmInfobulle), classePageNomSimple, " %}`;");
				tl(3, "}");

				if(classeVarEmplacement != null || classeVarAire != null) {
					l();
					tl(3, "function ", i18nPage.getString(I18n.var_jsLegende), classeNomSimple, "(map) {");
					tl(0, "{%- block ", i18nPage.getString(I18n.var_jsLegende), classePageNomSimple, " %}");
					tl(4, "var div = L.DomUtil.create('div', 'info legend');");
					tl(4, "var htm = '';");
					tl(4, "window.", i18nPage.getString(I18n.var_liste), classeNomSimple, ".forEach((", StringUtils.uncapitalize(classeNomSimple), ", index) => {");
					if(classeVarEmplacement != null) {
						tl(5, "if(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, ") {");
						tl(6, "var shapes = [];");
						tl(6, "if(Array.isArray(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, "))");
						tl(7, "shapes = shapes.concat(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, ");");
						tl(6, "else");
						tl(7, "shapes.push(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, ");");
						tl(6, "shapes.forEach(function(shape, index) {");
						tl(7, "htm += ", i18nPage.getString(I18n.var_htmLegende), classeNomSimple, "(map, shape, ", StringUtils.uncapitalize(classeNomSimple), ", index, shapes.length);");
						tl(6, "});");
						tl(5, "}");
					}
					if(classeVarAire != null) {
						tl(5, "if(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, ") {");
						tl(6, "var shapes = [];");
						tl(6, "if(Array.isArray(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, "))");
						tl(7, "shapes = shapes.concat(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, ");");
						tl(6, "else");
						tl(7, "shapes.push(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, ");");
						tl(6, "shapes.forEach(function(shape, index) {");
						tl(7, "htm += ", i18nPage.getString(I18n.var_htmLegende), classeNomSimple, "(map, shape, ", StringUtils.uncapitalize(classeNomSimple), ", index, shapes.length);");
						tl(6, "});");
						tl(5, "}");
					}
					tl(4, "});");
					tl(4, "div.innerHTML = htm;");
					tl(4, "return div;");
					tl(0, "{%- endblock ", i18nPage.getString(I18n.var_jsLegende), classePageNomSimple, " %}");
					tl(3, "}");
					l();
					tl(3, "function ", i18nPage.getString(I18n.var_htmLegende), classeNomSimple, "(map, shape, ", StringUtils.uncapitalize(classeNomSimple), ", index, count) {");
					tl(4, "var color = ", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement != null ? classeVarEmplacementCouleur : classeVarAireCouleur, "[index];");
					tl(4, "var title = ", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement != null ? classeVarEmplacementTitre : classeVarAireTitre, "[index];");
					tl(4, "var url = ", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement != null ? classeVarEmplacementUrl : classeVarAireUrl, "[index];");
					tl(4, "var htm = '';");
					tl(4, "htm += `<div class=\"cursor-pointer \" style=\"max-width: 400px; \">");
					tl(0, "`;");
					t(4, "htm += `");
					l("{%- block ", i18nPage.getString(I18n.var_htmLegende), classePageNomSimple, " %}");
					tl(2, "<div title=\"${quoteattr(title)}\">");
					tl(2, "<div style=\"width: 20px; \">");
					tl(3, "<i class=\"fa-light fa-circle\" style=\"color: ${color}; \"></i>");
					tl(2, "</div>");
					tl(2, "<div class=\"text-overflow-ellipsis \">");
					tl(3, "<span class=\"\" data-", classeVarCleUnique, "=\"${", uncapitalizeClasseApiClasseNomSimple, ".", classeVarCleUnique, "}\" onclick=\"window.mapLayers[this.getAttribute('data-", classeVarCleUnique, "')].openPopup(); return false;\" href=\"\">${title}</span>");
					tl(2, "</div>");
					tl(2, "</div>");
					l("{%- endblock ", i18nPage.getString(I18n.var_htmLegende), classePageNomSimple, " %}`;");
					tl(4, "htm += `");
					tl(0, "</div>");
					tl(0, "`;");
					tl(4, "return htm;");
					tl(3, "}");
					l();
					tl(3, "function ", i18nPage.getString(I18n.var_jsStyle), classeNomSimple, "(feature) {");
					tl(0, "{%- block ", i18nPage.getString(I18n.var_jsStyle), classePageNomSimple, " %}");
					tl(4, "if(feature.geometry.type == 'Point') {");
					tl(5, "return {");
					tl(6, "radius: 8");
					tl(6, ", fillColor: (feature.properties.areaServedColors && feature.properties.areaServedColors[feature.index] ? feature.properties.areaServedColors[feature.index] : (feature.properties.color ? feature.properties.color : '#000'))");
					tl(6, ", color: (feature.properties.areaServedColors && feature.properties.areaServedColors[feature.index] ? feature.properties.areaServedColors[feature.index] : (feature.properties.color ? feature.properties.color : '#000'))");
					tl(6, ", weight: 1");
					tl(6, ", opacity: 0.7");
					tl(6, ", fillOpacity: 0.7");
					tl(5, "};");
					tl(4, "} else if(feature.geometry.type == 'LineString') {");
					tl(5, "return {");
					tl(6, "color: (feature.properties.areaServedColors && feature.properties.areaServedColors[feature.index] ? feature.properties.areaServedColors[feature.index] : (feature.properties.color ? feature.properties.color : '#000'))");
					tl(6, ", weight: 5");
					tl(6, ", opacity: 0.7");
					tl(5, "};");
					tl(4, "} else {");
					tl(5, "return {");
					tl(6, " fillColor: (feature.properties.areaServedColors && feature.properties.areaServedColors[feature.index] ? feature.properties.areaServedColors[feature.index] : (feature.properties.color ? feature.properties.color : '#000'))");
					tl(6, ", color: (feature.properties.areaServedColors && feature.properties.areaServedColors[feature.index] ? feature.properties.areaServedColors[feature.index] : (feature.properties.color ? feature.properties.color : '#000'))");
					tl(6, ", weight: 3");
					tl(6, ", opacity: 0.7");
					tl(6, ", fillOpacity: 0.7");
					tl(5, "};");
					tl(4, "}");
					tl(0, "{%- endblock ", i18nPage.getString(I18n.var_jsStyle), classePageNomSimple, " %}");
					tl(3, "}");
				}
				tl(2, "</script>");
				tl(2, "<script type=\"module\">");

				// tl(3, "document.querySelector('#site-calendar-box').accordion({ collapsible: true, active: false });");
				l("{% if DEFAULT_MAP_LOCATION is defined %}");
				tl(3, "window.DEFAULT_MAP_LOCATION = { lat: {{ DEFAULT_MAP_LOCATION.lat }}, lon: {{ DEFAULT_MAP_LOCATION.lon }} };");
				l("{% endif %}");
				l("{% if DEFAULT_MAP_ZOOM is defined %}");
				tl(3, "window.DEFAULT_MAP_ZOOM = {{ DEFAULT_MAP_ZOOM }};");
				l("{% endif %}");
				tl(3, "window.DEFAULT_ZONE_ID = '{{ defaultZoneId }}';");
				tl(3, "Promise.all([");
				tl(4, "customElements.whenDefined('", composantsWebPrefixe, "button')");
				tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "input')");
				// tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "checkbox')");
				// tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "option')");
				// tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "select')");
				// tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "textarea')");
				tl(3, "]).then(() => {");
				l();
				tl(4, "var pk = {% if pk is defined %}{{pk}}{% else %}null{% endif %};");
				l();
				tl(4, "if(pk == null) {");
				tl(5, "document.querySelector('#grid-toggle-details')?.addEventListener('click', () => {");
				tl(6, "document.querySelector('#site-results-grid').classList.remove('grid-mode-list', 'grid-mode-card');");
				tl(6, "document.querySelector('#site-results-grid').classList.add('grid-mode-details');");
				tl(5, "});");
				tl(5, "document.querySelector('#grid-toggle-list')?.addEventListener('click', () => {");
				tl(6, "document.querySelector('#site-results-grid').classList.remove('grid-mode-details', 'grid-mode-card');");
				tl(6, "document.querySelector('#site-results-grid').classList.add('grid-mode-list');");
				tl(5, "});");
				tl(5, "document.querySelector('#grid-toggle-card')?.addEventListener('click', () => {");
				tl(6, "document.querySelector('#site-results-grid').classList.remove('grid-mode-details', 'grid-mode-list');");
				tl(6, "document.querySelector('#site-results-grid').classList.add('grid-mode-card');");
				tl(5, "});");
				tl(4, "} else {");
				tl(5, "window.", StringUtils.uncapitalize(classeNomSimple), " = JSON.parse(document.querySelector('.", i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Formulaire), " .", StringUtils.uncapitalize(classeNomSimple), "')?.value);");
				tl(4, "{% if ", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, " is defined %}");
				s(wJsInit);
				tl(4, "{% endif %}");
				tl(4, "}");
				tl(4, "window.", i18nPage.getString(I18n.var_liste), classeNomSimple, " = JSON.parse(document.querySelector('.", i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Formulaire), " .", i18nPage.getString(I18n.var_liste), classeNomSimple, "')?.value);");
				tl(4, "window.varsFq = JSON.parse('{{ toJsonObjectStringInApostrophes(varsFq) }}');");
				tl(4, "window.varsRange = JSON.parse('{{ toJsonObjectStringInApostrophes(varsRange) }}');");
				tl(4, "window.defaultRangeVar = '{{ defaultRangeVar }}';");
				tl(4, i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Graphique), classeNomSimple, "();");



				tl(4, "{% for key, value in varsQ.items() %}");
				l();
				tl(4, "document.querySelector('#q", classeNomSimple, "_{{ key }}')?.addEventListener('", composantsWebPrefixe, "{% if var == '", classeVarSuggere, "' %}keyup{% else %}change{% endif %}', event => {");
				tl(5, "qChange('", classeNomSimple, "', event.target, document.querySelector('#q", classeNomSimple, "Div_{{ key }}'));");
				tl(4, "});");
				tl(4, "{% endfor %}");

				l();
				tl(4, "document.querySelector('#q", classeNomSimple, "_", i18nPage.getString(I18n.var_lignes), "')?.addEventListener('", composantsWebPrefixe, "change', event => {");
				tl(5, "paramChange('", classeNomSimple, "', event.target, document.querySelector('#q", classeNomSimple, "Div_", i18nPage.getString(I18n.var_lignes), "'));");
				tl(4, "});");

				l();
				tl(4, "document.querySelector('#q", classeNomSimple, "_", i18nPage.getString(I18n.var_debut), "')?.addEventListener('", composantsWebPrefixe, "change', event => {");
				tl(5, "paramChange('", classeNomSimple, "', event.target, document.querySelector('#q", classeNomSimple, "Div_", i18nPage.getString(I18n.var_debut), "'));");
				tl(4, "});");

				l();
				// tl(4, "var calendarEl = document.getElementById('site-calendar');");
				// tl(4, "var calendar = new FullCalendar.Calendar(calendarEl, {");
				// tl(5, "initialView: 'dayGridMonth'");
				// tl(4, "});");
				// tl(4, "//calendar.render();");
				for(String classeApiMethode : classeApiMethodes) {
					String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
					String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
					String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
					String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");

					if(classeApiMethode.equals(i18nPage.getString(I18n.var_PageRecherche)) || classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals("DELETE") || classeApiMethode.equals(i18nPage.getString(I18n.var_PUTCopie)) || classeApiMethode.equals(i18nPage.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {

						l();
						tl(4, "var submit", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, " = document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "')?.addEventListener('submit', event => {");
						tl(5, "event.preventDefault();");
						// if("POST".equals(classeApiMethodeMethode))
						// 	tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'), (response, target) => { response.json().then((json) => { window.location.href = json.", classeVarUrlPk, "; }); });");
						// else if("PATCH".equals(classeApiMethode))
						// 	tl(5, classeApiOperationIdMethode, "(null, document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'));");
						// else if("PUTImport".equals(classeApiMethode))
						// 	tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'));");
						// else if(i18nPage.getString(I18n.var_PUTFusion).equals(classeApiMethode))
						// 	tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), ", classeModele ? "{{ " + classeVarClePrimaire + " }}" : "'{{ " + classeVarCleUnique + " }}', document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'));");
						// else if(i18nPage.getString(I18n.var_PUTCopie).equals(classeApiMethode))
						// 	tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), ", classeModele ? "{{ " + classeVarClePrimaire + " }}" : "'{{ " + classeVarCleUnique + " }}', document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'));");
						// else
						// 	tl(5, classeApiOperationIdMethode, "();");
						tl(5, "return false;");
						tl(4, "});");

						tl(4, "document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "')?.addEventListener('click', event => {");
						// tl(5, "event.preventDefault();");
						// tl(5, "document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "').dispatchEvent(new Event('submit'));");
						// tl(5, "return false;");
						tl(5, "event.preventDefault();");
						if("POST".equals(classeApiMethodeMethode))
							tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'), (response, target) => { response.json().then((json) => { window.location.href = json.", classeVarUrlPk, "; }); });");
						else if("PATCH".equals(classeApiMethode))
							tl(5, classeApiOperationIdMethode, "(null, document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'), event.target.getAttribute('data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'));");
						else if("DELETE".equals(classeApiMethode))
							tl(5, classeApiOperationIdMethode, "(null, document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'), event.target.getAttribute('data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'));");
						else if("PUTImport".equals(classeApiMethode))
							tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'));");
						else if(i18nPage.getString(I18n.var_PUTFusion).equals(classeApiMethode))
							tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), ", classeModele ? "{{ " + classeVarClePrimaire + " }}" : "'{{ " + classeVarCleUnique + " }}', document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'));");
						else if(i18nPage.getString(I18n.var_PUTCopie).equals(classeApiMethode))
							tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), ", classeModele ? "{{ " + classeVarClePrimaire + " }}" : "'{{ " + classeVarCleUnique + " }}', document.querySelector('#htm", i18nPage.getString(I18n.var_Formulaire), i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'));");
						else
							tl(5, classeApiOperationIdMethode, "();");
						tl(5, "return false;");
						tl(4, "});");
					}
				}
				tl(3, "});");
				tl(2, "</script>");
				tl(0, "{%- endblock htmScript", classePageNomSimple, " %}");
				tl(0, "{%- endblock htmScript", classePageSuperNomSimple, " %}");

				l();
				tl(0, "{%- block websocket", classePageSuperNomSimple, " %}");
				tl(0, "{%- block websocket", classePageNomSimple, " %}");
				tl(4, "window.eventBus = new EventBus('/eventbus');");
				tl(4, "window.eventBus.enableReconnect(true);");
				tl(4, "websocket", classeApiClasseNomSimple, "(websocket", classeApiClasseNomSimple, "Inner);");
				tl(0, "{%- endblock websocket", classePageNomSimple, " %}");
				tl(0, "{%- endblock websocket", classePageSuperNomSimple, " %}");
			}
			l();
			t(0, "{%- block htmUrl", classePageNomSimple, " %}");
			s("{{pageUri}}");
			s("?q={{query.q}}");
			s("&amp;rows={% if rows is defined %}{{rows}}{% else %}{{pagination.", i18nPage.getString(I18n.var_lignes), "}}{% endif %}");
			s("&amp;rows={% if start is defined %}{{start}}{% else %}{{pagination.", i18nPage.getString(I18n.var_debut), "}}{% endif %}");
			s("{% for item query.fq %}");
			s("{% if fq == item %}");
			s("{% else %}");
			s("&fq={{fq}}:{{val}}");
			s("{% endif %}");
			s("{% endfor %}");
			s("{% for item in query.sort %}");
			s("{% if sort == item %}");
			s("{% else %}");
			s("&sort={{var}} {{order}}");
			s("{% endif %}");
			s("{% endfor %}");
			l("{%- endblock htmUrl", classePageNomSimple, " %}");
	}

	public void ecrirePageJs(String langueNom, JsonObject i18nPage) throws Exception {
				for(String classeApiMethode : classeApiMethodes) {
					String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
					String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
					String classeApiTypeMediaRequete = classeDoc.getString("classeApiTypeMediaRequete" + classeApiMethode + "_" + langueNom + "_stored_string");
					String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
					String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");
					List<String> classeTrisVar = Optional.ofNullable(doc.getJsonArray("classeTrisVar_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
	
					if("application/json".equals(classeApiTypeMediaMethode)) {
						Boolean methodePOST = classeApiMethodeMethode.equals("POST");
						Boolean methodeGET = classeApiMethode.contains("GET");
						Boolean methodePUTImport = classeApiMethode.equals("PUTImport");
						Boolean methodePUTFusion = classeApiMethode.equals(i18nPage.getString(I18n.var_PUTFusion));
						Boolean methodePUTCopie = classeApiMethode.equals(i18nPage.getString(I18n.var_PUTCopie));
						Boolean methodePATCH = classeApiMethodeMethode.equals("PATCH");
						Boolean methodeDELETE = classeApiMethodeMethode.equals("DELETE");
						Boolean methodeRecherche = classeApiMethode.contains(i18nPage.getString(I18n.var_Recherche));
						auteurPageJs.l();
						auteurPageJs.tl(0, "// ", classeApiMethode, " //");
						auteurPageJs.l();
						auteurPageJs.t(0, "async function ", classeApiOperationIdMethode, "(");
						if(methodePOST) {
							auteurPageJs.s("$", i18nPage.getString(I18n.var_formulaireValeurs));
							auteurPageJs.s(", target");
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						} else if(methodeDELETE) {
							auteurPageJs.s("", i18nPage.getString(I18n.var_filtres));
							auteurPageJs.s(", target");
							auteurPageJs.s(", ", classeModele ? classeVarClePrimaire : classeVarCleUnique);
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						} else if(methodePUTImport) {
							auteurPageJs.s("$", i18nPage.getString(I18n.var_formulaireValeurs));
							auteurPageJs.s(", target");
							auteurPageJs.s(", ", classeModele ? classeVarClePrimaire : classeVarCleUnique);
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						} else if(methodePUTFusion) {
							auteurPageJs.s("$", i18nPage.getString(I18n.var_formulaireValeurs));
							auteurPageJs.s(", target");
							auteurPageJs.s(", ", classeModele ? classeVarClePrimaire : classeVarCleUnique);
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						} else if(methodePUTCopie) {
							auteurPageJs.s("$", i18nPage.getString(I18n.var_formulaireValeurs));
							auteurPageJs.s(", target");
							auteurPageJs.s(", ", classeModele ? classeVarClePrimaire : classeVarCleUnique);
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						} else if(methodePATCH) {
							auteurPageJs.s("$", i18nPage.getString(I18n.var_formulaireFiltres), ", $", i18nPage.getString(I18n.var_formulaireValeurs));
							auteurPageJs.s(", target");
							auteurPageJs.s(", ", classeModele ? classeVarClePrimaire : classeVarCleUnique);
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						} else if(methodeRecherche) {
							auteurPageJs.s("$", i18nPage.getString(I18n.var_formulaireFiltres), "");
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						} else if(methodeGET || methodeDELETE) {
							auteurPageJs.s(classeVarClePrimaire);
						}
	
						auteurPageJs.l(") {");
						if(methodePOST) {
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.tl(1, "if(success == null) {");
							auteurPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {");
							auteurPageJs.tl(3, i18nPage.getString(I18n.var_ajouterLueur), "(target);");
							if(classeVarUrlPk != null) {
								auteurPageJs.tl(3, "var url = data['", classeVarUrlPk, "'];");
								auteurPageJs.tl(3, "if(url)");
								auteurPageJs.tl(4, "window.location.href = url;");
							}
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.tl(1, "if(error == null) {");
							auteurPageJs.tl(2, "error = function( jqXhr, textStatus, errorThrown ) {");
							auteurPageJs.tl(3, i18nPage.getString(I18n.var_ajouterErreur), "(target);");
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.s(wPOST);
							auteurPageJs.l();
						} else if(methodeDELETE) {
							auteurPageJs.tl(1, "if(success == null) {");
							auteurPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {");
							auteurPageJs.tl(3, i18nPage.getString(I18n.var_ajouterLueur), "(target);");
							if(classeVarUrlPk != null) {
								auteurPageJs.tl(3, "var url = data['", classeVarUrlPk, "'];");
								auteurPageJs.tl(3, "if(url)");
								auteurPageJs.tl(4, "window.location.href = url;");
							}
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.tl(1, "if(error == null) {");
							auteurPageJs.tl(2, "error = function( jqXhr, textStatus, errorThrown ) {");
							auteurPageJs.tl(3, i18nPage.getString(I18n.var_ajouterErreur), "(target);");
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.l();
						} else if(methodePUTCopie) {
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.tl(1, "if(success == null) {");
							auteurPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {");
							auteurPageJs.tl(3, i18nPage.getString(I18n.var_ajouterLueur), "(target);");
							if(classeVarUrlPk != null) {
								auteurPageJs.tl(3, "var url = data['", classeVarUrlPk, "'];");
								auteurPageJs.tl(3, "if(url)");
								auteurPageJs.tl(4, "window.location.href = url;");
							}
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.tl(1, "if(error == null) {");
							auteurPageJs.tl(2, "error = function( jqXhr, textStatus, errorThrown ) {");
							auteurPageJs.tl(3, i18nPage.getString(I18n.var_ajouterErreur), "(target);");
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.s(wPUTCopie);
							auteurPageJs.l();
						} else if(methodePUTImport) {
							auteurPageJs.tl(1, "var json = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.", "PUTImport", "_", i18nPage.getString(I18n.var_listeRecherche), "')?.value;");
							auteurPageJs.tl(1, "if(json != null && json !== '')");
							if(StringUtils.equals("application/json", classeApiTypeMediaRequete))
								auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(JSON.parse(json), target, success, error);");
							else
								auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(json, target, success, error);");
						} else if(methodePUTFusion) {
							auteurPageJs.tl(1, "var json = $", i18nPage.getString(I18n.var_formulaireValeurs), ".querySelector('.", i18nPage.getString(I18n.var_PUTFusion), "_", i18nPage.getString(I18n.var_listeRecherche), "')?.value;");
							auteurPageJs.tl(1, "if(json != null && json !== '')");
							if(StringUtils.equals("application/json", classeApiTypeMediaRequete))
								auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(JSON.parse(json), target, success, error);");
							else
								auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(json, target, success, error);");
						// } else if(methodePUTCopie) {
						// 	auteurPageJs.tl(1, "var vals = {};");
						// 	auteurPageJs.s(wPUTCopie);
						// 	auteurPageJs.l();
						} else if(methodePATCH) {
							auteurPageJs.tl(1, "var ", i18nPage.getString(I18n.var_filtres), " = ", classeApiOperationIdMethode,i18nPage.getString(I18n.var_Filtres), "($", i18nPage.getString(I18n.var_formulaireFiltres), ");");
							auteurPageJs.l();
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.s(wPATCH);
							auteurPageJs.l();
						} else if(methodeRecherche) {
							auteurPageJs.tl(1, "var ", i18nPage.getString(I18n.var_filtres), " = ", classeApiOperationIdMethode,i18nPage.getString(I18n.var_Filtres), "($", i18nPage.getString(I18n.var_formulaireFiltres), ");");
							auteurPageJs.tl(1, "if(success == null)");
							auteurPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {};");
							auteurPageJs.tl(1, "if(error == null)");
							auteurPageJs.tl(2, "error = function( jqXhr, textStatus, errorThrown ) {};");
							auteurPageJs.l();
						}
	
						if(methodePATCH) {
							if(classeJsPATCH != null) {
								auteurPageJs.l(classeJsPATCH);
								auteurPageJs.l();
							}
							auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", classeModele ? classeVarClePrimaire : classeVarCleUnique, " == null ? deparam(window.location.search ? window.location.search.substring(1) : window.location.search) : [{name:'fq', value:'", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}], vals, target, success, error);");
						}
						else if(methodePUTImport) {
						}
						else if(methodePUTFusion) {
						}
						else if(methodePUTCopie) {
							auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", classeModele ? classeVarClePrimaire : classeVarCleUnique, " == null ? deparam(window.location.search ? window.location.search.substring(1) : window.location.search) : [{name:'fq', value:'", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}], vals, target, success, error);");
						}
						else if(methodeRecherche) {
							auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", i18nPage.getString(I18n.var_filtres), ", target, success, error);");
						}
						else {
							auteurPageJs.tl(1, "fetch(");
		
							if(methodeGET || methodePUTCopie)
								auteurPageJs.tl(2, "'", StringUtils.replace(classeApiUriMethode + "'", "{id}'", "' + id"));
							else if(methodePATCH || methodeDELETE || methodeRecherche)
								auteurPageJs.tl(2, "'", classeApiUriMethode, "?' + ", i18nPage.getString(I18n.var_filtres), ".map(function(m) { return m.name + '=' + encodeURIComponent(m.value) }).join('&')");
							else
								auteurPageJs.tl(2, "'", classeApiUriMethode, "'");

							auteurPageJs.tl(2, ", {");
							auteurPageJs.tl(3, "headers: {'Content-Type':'application/json; charset=utf-8'}");
							if(!"GET".equals(classeApiMethodeMethode)) {
								auteurPageJs.tl(3, ", method: '", classeApiMethodeMethode, "'");
								if(!"DELETE".equals(classeApiMethodeMethode)) {
									auteurPageJs.tl(3, ", body: JSON.stringify(vals)");
								}
							}
							auteurPageJs.tl(2, "}).then(", i18nPage.getString(I18n.var_reponse), " => {");
							auteurPageJs.tl(3, "if(", i18nPage.getString(I18n.var_reponse), ".ok)");
							auteurPageJs.tl(4, "success(", i18nPage.getString(I18n.var_reponse), ", target);");
							auteurPageJs.tl(3, "else");
							auteurPageJs.tl(4, "error(", i18nPage.getString(I18n.var_reponse), ", target);");
							auteurPageJs.tl(2, "})");
							auteurPageJs.tl(2, ".catch(", i18nPage.getString(I18n.var_reponse), " => error(", i18nPage.getString(I18n.var_reponse), ", target));");
						}
						auteurPageJs.l("}");

						if(methodePATCH || methodeRecherche) {
//							auteurPageJs.l();
//							auteurPageJs.tl(0, "function varsFq", classeNomSimple, "() {");
//							auteurPageJs.tl(1, "var vars = [];");
//							auteurPageJs.s(wVarsFqJs);
//							auteurPageJs.tl(1, "return vars;");
//							auteurPageJs.tl(0, "}");

							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, i18nPage.getString(I18n.var_Filtres), "($", i18nPage.getString(I18n.var_formulaireFiltres), ") {");
							auteurPageJs.tl(1, "var ", i18nPage.getString(I18n.var_filtres), " = [];");
							auteurPageJs.tl(1, "if($", i18nPage.getString(I18n.var_formulaireFiltres), ") {");
							if(methodePATCH)
								auteurPageJs.tl(2, i18nPage.getString(I18n.var_filtres), ".push({ name: 'softCommit', value: 'true' });");
							auteurPageJs.s(wRecherche);
							auteurPageJs.tl(1, "}");
							auteurPageJs.tl(1, "return ", i18nPage.getString(I18n.var_filtres), ";");
							auteurPageJs.tl(0, "}");
						}
						if(methodePATCH) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Val(", i18nPage.getString(I18n.var_filtres), ", v, val, target, success, error) {");
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.tl(1, "vals[v] = val;");
							auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", i18nPage.getString(I18n.var_filtres), ", vals, target, success, error);");
							auteurPageJs.l("}"); 
						}
						if(methodeRecherche) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(", i18nPage.getString(I18n.var_filtres), ", target, success, error) {");
							if(classeLignes != null) {
								auteurPageJs.l();
//										auteurPageJs.tl(1, langueConfig.getString(ConfigCles.var_filtres), ".push({ name: 'rows', value: ", classeLignes, " });");
							}
							if(classeTrisVar != null) {
								auteurPageJs.l();
								for(Integer i = 0; i < classeTrisVar.size(); i++) {
									String classeTriVar = classeTrisVar.get(i);
									String classeTriOrdre = classeTrisOrdre.get(i);

									auteurPageJs.tl(1, i18nPage.getString(I18n.var_filtres), ".push({ name: '", "sort", "', value: '", classeTriVar, " ", classeTriOrdre, "' });");
								}
							}
							auteurPageJs.tl(1, "fetch(");
							auteurPageJs.tl(2, "'", classeApiUriMethode, "?' + ", i18nPage.getString(I18n.var_filtres), ".map(function(m) { return m.name + '=' + encodeURIComponent(m.value) }).join('&')");
						}
						if(methodePATCH || methodePUTCopie) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(", i18nPage.getString(I18n.var_filtres), ", vals, target, success, error) {");
							auteurPageJs.tl(1, "fetch(");
							auteurPageJs.tl(2, "'", classeApiUriMethode, "?' + ", i18nPage.getString(I18n.var_filtres), ".map(function(m) { return m.name + '=' + encodeURIComponent(m.value) }).join('&')");
						}
						if(methodePUTImport || methodePUTFusion) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(json, target, success, error) {");
							auteurPageJs.tl(1, "fetch(");
							auteurPageJs.tl(2, "'", classeApiUriMethode, "'");
						}
						if(methodePOST) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(vals, target, success, error) {");
							auteurPageJs.tl(1, "fetch(");
							auteurPageJs.tl(2, "'", classeApiUriMethode, "'");
						}
						if(methodePATCH || methodePUTCopie || methodePUTImport || methodePUTFusion || methodePOST || methodeRecherche) {
							auteurPageJs.tl(2, ", {");
							auteurPageJs.tl(3, "headers: {'Content-Type':'application/json; charset=utf-8'}");

							if(methodePATCH || methodePOST) {
								auteurPageJs.tl(3, ", method: '", classeApiMethodeMethode, "'");
								auteurPageJs.tl(3, ", body: JSON.stringify(vals)");
							}
							if(methodePUTCopie) {
								auteurPageJs.tl(3, ", method: '", classeApiMethodeMethode, "'");
								auteurPageJs.tl(3, ", body: JSON.stringify({patch: vals})");
							}
							if(methodePUTImport || methodePUTFusion) {
								auteurPageJs.tl(3, ", method: '", classeApiMethodeMethode, "'");
								auteurPageJs.tl(3, ", body: JSON.stringify(json)");
							}
							auteurPageJs.tl(2, "}).then(", i18nPage.getString(I18n.var_reponse), " => {");
							auteurPageJs.tl(3, "if(", i18nPage.getString(I18n.var_reponse), ".ok)");
							auteurPageJs.tl(4, "success(", i18nPage.getString(I18n.var_reponse), ", target);");
							auteurPageJs.tl(3, "else");
							auteurPageJs.tl(4, "error(", i18nPage.getString(I18n.var_reponse), ", target);");
							auteurPageJs.tl(2, "})");
							auteurPageJs.tl(2, ".catch(", i18nPage.getString(I18n.var_reponse), " => error(", i18nPage.getString(I18n.var_reponse), ", target));");
							auteurPageJs.l("}");
						}
						if(methodeRecherche) {

							SolrQuery rechercheSolr = new SolrQuery();   
							rechercheSolr.setQuery("*:*");
							rechercheSolr.setRows(1000000);
							String fqClassesSuperEtMoi = "(" + classesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
							rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
							rechercheSolr.addFilterQuery("entiteEstSubstitue_indexed_boolean:false");
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
										entiteHtmLigne = (Integer)entiteDocumentSolr.get("entiteHtmLigne_stored_int");
										entiteHtmLigneTitre = (String)entiteDocumentSolr.get("entiteHtmLigneTitre_stored_string");
										entiteHtmLigneTitreOuvert = (String)entiteDocumentSolr.get("entiteHtmLigneTitreOuvert_stored_string");
										entiteHtmLigneVerticale = (Boolean)entiteDocumentSolr.get("entiteHtmLigneVerticale_stored_boolean");
										entiteHtmLigneEnTeteExpression = (String)entiteDocumentSolr.get("entiteHtmLigneEnTeteExpression_stored_string");
										entiteCouverture = (Boolean)entiteDocumentSolr.get("entiteCouverture_stored_boolean");
										entiteHtml = (Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean");
										entiteDocValues = (Boolean)entiteDocumentSolr.get("entiteDocValues_stored_boolean");
										entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
										entiteStocke = (Boolean)entiteDocumentSolr.get("entiteStocke_stored_boolean");
										entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
										entiteModifier = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteModifier_stored_boolean"));
										entiteDefinir = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteDefinir_stored_boolean"));
										entiteAttribuer = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteAttribuer_stored_boolean"));
										entiteSuggere = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean"));
										entiteAttribuerNomSimple = (String)entiteDocumentSolr.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string");
										entiteAttribuerVar = (String)entiteDocumentSolr.get("entiteAttribuerVar_" + langueNom + "_stored_string");
										entiteAttribuerVarUrlId = (String)entiteDocumentSolr.get("entiteAttribuerVarUrlId_" + langueNom + "_stored_string");
										entiteAttribuerVarUrlPk = (String)entiteDocumentSolr.get("entiteAttribuerVarUrlPk_" + langueNom + "_stored_string");
										entiteAttribuerVarTitre = (String)entiteDocumentSolr.get("entiteAttribuerVarTitre_" + langueNom + "_stored_string");
										entiteAttribuerVarDescription = (String)entiteDocumentSolr.get("entiteAttribuerVarDescription_" + langueNom + "_stored_string");
										entiteAttribuerVarImageUrl = (String)entiteDocumentSolr.get("entiteAttribuerVarImageUrl_" + langueNom + "_stored_string");
										entiteAttribuerVarSuggere = (String)entiteDocumentSolr.get("entiteAttribuerVarSuggere_" + langueNom + "_stored_string");
										entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + i18nPage.getString(I18n.var_Recherche) + "_" + langueNom + "_stored_string");
										entiteAttribuerOperationIdPATCH = (String)entiteDocumentSolr.get("entiteAttribuerOperationIdPATCH_" + langueNom + "_stored_string");
										entiteOperationIdPATCH = (String)entiteDocumentSolr.get("entiteOperationIdPATCH_" + langueNom + "_stored_string");
										entiteTypeJson = (String)entiteDocumentSolr.get("entiteTypeJson_stored_string");
										entiteAttribuerTypeJson = (String)entiteDocumentSolr.get("entiteAttribuerTypeJson_stored_string");
										entiteAttribuerContexteIcone = (String)entiteDocumentSolr.get("entiteAttribuerContexteIcone_stored_string");
										entiteAttribuerTrisVar = (List<String>)entiteDocumentSolr.get("entiteAttribuerTrisVar_" + langueNom + "_stored_strings");
										entiteAttribuerTrisSuffixeType = (List<String>)entiteDocumentSolr.get("entiteAttribuerTrisSuffixeType_stored_strings");
										entiteAttribuerContexteUnNom = (String)entiteDocumentSolr.get("entiteAttribuerContexteUnNom_" + langueNom + "_stored_string");
										entiteAttribuerContexteNomPluriel = (String)entiteDocumentSolr.get("entiteAttribuerContexteNomPluriel_" + langueNom + "_stored_string");
			
										if(entiteSuggere) {
											auteurPageJs.l();
											auteurPageJs.tl(0, "function ", i18nPage.getString(I18n.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "($", i18nPage.getString(I18n.var_formulaireFiltres), ", $list, target) {");
											auteurPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
											auteurPageJs.tl(2, "$list.empty();");
											auteurPageJs.tl(2, "data['list'].forEach((o, i) => {");
											auteurPageJs.tl(3, "var $i = document.querySelector('", classeIcone, "');");
											auteurPageJs.t(3, "var $span = document.createElement('span');");
											auteurPageJs.t(3, "$span.setAttribute('class', '');");
											auteurPageJs.t(3, "$span.innerText = ");
											if(classeVarTitre != null)
												auteurPageJs.s("o['", classeVarTitre, "']");
											auteurPageJs.l(";");
											auteurPageJs.tl(3, "var $li = document.createElement('li');");
											auteurPageJs.tl(3, "var $a = document.createElement('a').setAttribute('href', o['", classeVarUrlPk, "']);");
											auteurPageJs.tl(3, "$a.append($i);");
											auteurPageJs.tl(3, "$a.append($span);");
											auteurPageJs.tl(3, "$li.append($a);");
											auteurPageJs.tl(3, "$list.append($li);");
											auteurPageJs.tl(2, "});");
											auteurPageJs.tl(1, "};");
											auteurPageJs.tl(1, "error = function( jqXhr, textStatus, errorThrown ) {};");
											auteurPageJs.tl(1, i18nPage.getString(I18n.var_rechercher), classeApiClasseNomSimple, "Vals($", i18nPage.getString(I18n.var_formulaireFiltres), ", target, success, error);");
											auteurPageJs.tl(0, "}");
										}
										else if(entiteAttribuer) {

											auteurPageJs.l();
											auteurPageJs.tl(0, "function ", i18nPage.getString(I18n.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "(", i18nPage.getString(I18n.var_filtres), ", $list, ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " = null, ", i18nPage.getString(I18n.var_attribuer), "=true, target) {");
											auteurPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
											auteurPageJs.tl(2, "$list.empty();");
											auteurPageJs.tl(2, "data['list'].forEach((o, i) => {");
											auteurPageJs.tl(3, "var iTemplate = document.createElement('template');");
											auteurPageJs.tl(3, "iTemplate.innerHTML = '", entiteAttribuerContexteIcone, "';");
											auteurPageJs.tl(3, "var $i = iTemplate.content;");
											auteurPageJs.t(3, "var $span = document.createElement('span');");
											auteurPageJs.t(3, "$span.setAttribute('class', '');");
											auteurPageJs.t(3, "$span.innerText = ");
											if(entiteAttribuerVarTitre != null)
												auteurPageJs.s("o['", entiteAttribuerVarTitre, "']");
											auteurPageJs.l(";");

											if(entiteAttribuerVarUrlPk != null) {
												auteurPageJs.tl(3, "var $a = document.createElement('<a>');");
												auteurPageJs.tl(3, "$a.setAttribute('id', o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "']);");
												auteurPageJs.tl(3, "$a.setAttribute('href', o['", entiteAttribuerVarUrlPk, "']);");
											} else {
												auteurPageJs.tl(3, "var $a = document.createElement('span');");
											}

											auteurPageJs.tl(3, "$a.append($i);");
											auteurPageJs.tl(3, "$a.append($span);");
											auteurPageJs.tl(3, "var val = o['", entiteAttribuerVar, "'];");
											auteurPageJs.tl(3, "var checked = pk == null ? false : Array.isArray(val) ? val.includes(", classeModele ? classeVarClePrimaire : classeVarCleUnique, ".toString()) : val == ", classeModele ? classeVarClePrimaire : classeVarCleUnique, ";");
											auteurPageJs.tl(3, "var $input = document.createElement('", composantsWebPrefixe, "input');");
											auteurPageJs.tl(3, "$input.setAttribute('id', '", classeApiMethodeMethode, "_", entiteVar, "_' + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " + '_", entiteAttribuerVar, "_' + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "']);");
											auteurPageJs.tl(3, "$input.setAttribute('value', o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "']);");
											auteurPageJs.tl(3, "$input.setAttribute('class', '", i18nPage.getString(I18n.var_valeur), entiteVarCapitalise, " w3-check ');");

											auteurPageJs.tl(3, "if(", classeModele ? classeVarClePrimaire : classeVarCleUnique, " != null) {");
											auteurPageJs.t(4, "$input.setAttribute('onchange', \"var $input = document.querySelector('#", classeApiMethodeMethode, "_", entiteVar, "_\" + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " + \"_", entiteAttribuerVar, "_\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"'); ");
											if("array".equals(entiteTypeJson)) {
												auteurPageJs.s("", entiteOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":\" + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " + \"' }], { [($input.checked ? 'add' : 'remove') + '", entiteVarCapitalise, "']: \\\"\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"\\\" }");
											}
											else {
												auteurPageJs.s("", entiteOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":\" + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " + \"' }], { [($input.checked ? 'set' : 'remove') + '", entiteVarCapitalise, "']: \\\"\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"\\\" }");
											}
											auteurPageJs.tl(4, ", target");
											auteurPageJs.tl(4, ", function(", i18nPage.getString(I18n.var_reponse), ", target) { ", i18nPage.getString(I18n.var_ajouterLueur), "(target); }");
											auteurPageJs.tl(4, ", function(", i18nPage.getString(I18n.var_reponse), ", target) { ", i18nPage.getString(I18n.var_ajouterErreur), "(target); }");
											auteurPageJs.l(" ); \");");

											auteurPageJs.tl(4, "$input.setAttribute('onclick', '", i18nPage.getString(I18n.var_enleverLueur), "(this); ');");
											auteurPageJs.tl(3, "}");

											auteurPageJs.tl(3, "$input.setAttribute('type', 'checkbox');");
											auteurPageJs.tl(3, "if(checked)");
											auteurPageJs.tl(4, "$input.setAttribute('checked', 'checked');");
											auteurPageJs.tl(3, "var $li = document.createElement('li');");
											if(entiteAttribuerTrisVar != null && entiteAttribuerTrisSuffixeType != null && entiteAttribuerTrisSuffixeType.size() > 0 && "_double".equals(entiteAttribuerTrisSuffixeType.get(0))) {
												for(String entiteAttribuerTriVar : entiteAttribuerTrisVar) {
													auteurPageJs.tl(3, "var ", entiteAttribuerTriVar, " = o['", entiteAttribuerTriVar, "'];");
												}
												String entiteAttribuerTriVarAncien = null;
												Integer k = 3;
												for(String entiteAttribuerTriVar : entiteAttribuerTrisVar) {
													if(entiteAttribuerTriVarAncien != null)
														k = 4;

													auteurPageJs.l();
													if(entiteAttribuerTriVarAncien != null)
														auteurPageJs.tl(3, "if(", entiteAttribuerTriVarAncien, " != null) {");
													auteurPageJs.tl(k, "$sort = document.createElement('span').setAttribute('style', 'padding-right: 8px; ');");
													auteurPageJs.tl(k, "var $sortInput = document.createElement('", composantsWebPrefixe, "input')");
													auteurPageJs.tl(k, "$sortInput.setAttribute('style', 'width: 4em; ');");
													auteurPageJs.tl(k, "$sortInput.setAttribute('id', \"", i18nPage.getString(I18n.var_attribuer), "_\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"_", i18nPage.getString(I18n.var_tri), "_", entiteAttribuerTriVar, "\");");
													auteurPageJs.tl(k, "$sortInput.setAttribute('value', ", entiteAttribuerTriVar, ").setAttribute('onchange', ");
													auteurPageJs.tl(k + 1, "\"document.querySelector('#", classeApiClasseNomSimple, "Form :input[name=\\\"focusId\\\"]').value = this.getAttribute('id'); \"");
													auteurPageJs.tl(k + 1, "+ \"", entiteAttribuerOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"' }], { ['set", StringUtils.capitalize(entiteAttribuerTriVar), "']: this.value ? this.value : null }\"");
													auteurPageJs.tl(k + 2, ", $input");
													auteurPageJs.tl(k + 2, ", function(", i18nPage.getString(I18n.var_reponse), ", target) { ", i18nPage.getString(I18n.var_ajouterLueur), "(target); }");
													auteurPageJs.tl(k + 2, ", function(", i18nPage.getString(I18n.var_reponse), ", target) { ", i18nPage.getString(I18n.var_ajouterErreur), "(target); }");
													// auteurPageJs.tl(k + 2, "+ \", function() { \"");
													// auteurPageJs.tl(k + 2, "+ \"}\"");
													// auteurPageJs.tl(k + 2, "+ \", function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "(document.querySelector('#", langueConfig.getString(ConfigCles.var_attribuer), "_\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"_", langueConfig.getString(ConfigCles.var_tri), "_", entiteAttribuerTriVar, "')); }\"");
													// auteurPageJs.tl(k + 2, "+ \" ); \"); ");
													auteurPageJs.tl(k, "$sort.append($sortInput);");
													auteurPageJs.tl(k, "$li.append($sort);");
													if(entiteAttribuerTriVarAncien != null)
														auteurPageJs.tl(3, "}");

													entiteAttribuerTriVarAncien = entiteAttribuerTriVar;
												}
											}
											auteurPageJs.tl(3, "if(", i18nPage.getString(I18n.var_attribuer), ")");
											auteurPageJs.tl(4, "$li.append($input);");
											auteurPageJs.tl(3, "$li.append($a);");
											auteurPageJs.tl(3, "$list.append($li);");
											auteurPageJs.tl(2, "});");
											auteurPageJs.tl(2, "var focusId = document.querySelector('#", classeApiClasseNomSimple, "Form :input[name=\"focusId\"]')?.value;");
											auteurPageJs.tl(2, "if(focusId)");
											auteurPageJs.tl(3, "document.querySelector('#' + focusId).parent().next().querySelector('input').focus();");
											auteurPageJs.tl(1, "};");
											auteurPageJs.tl(1, "error = function( jqXhr, textStatus, errorThrown ) {};");
											auteurPageJs.tl(1, entiteAttribuerOperationIdRecherche, "Vals(", i18nPage.getString(I18n.var_filtres), ", $input, success, error);");
											auteurPageJs.tl(0, "}");

											auteurWebsocket.l();
											auteurWebsocket.tl(2, "window.eventBus.registerHandler('websocket", entiteAttribuerNomSimple, "', function (error, message) {");
//												auteurWebsocket.tl(3, "var json = JSON.parse(message['body']);");
//												auteurWebsocket.tl(3, "var id = json['id'];");
//												auteurWebsocket.tl(3, langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "(document.querySelector('#' + (this.value ? '", langueConfig.getString(ConfigCles.var_suggere), "' : 'form') + '", classeApiClasseNomSimple, entiteVarCapitalise, "'), document.querySelector('#", "list", classeApiClasseNomSimple, entiteVarCapitalise, "_", classeApiMethodeMethode, "'));");
											auteurWebsocket.tl(3, "document.querySelector('.Page_", entiteVar, "').trigger('oninput');");
											auteurWebsocket.tl(3, "document.querySelector('.Page_", entiteVar, "_", i18nPage.getString(I18n.var_ajouter), "').innerText = '", i18nPage.getString(I18n.var_ajouter), " ", entiteAttribuerContexteUnNom, "';");
											auteurWebsocket.tl(3, "document.querySelector('.Page_", entiteVar, "_", i18nPage.getString(I18n.var_ajouter), "').classList.remove('w3-disabled');");
											auteurWebsocket.tl(3, "document.querySelector('.Page_", entiteVar, "_", i18nPage.getString(I18n.var_ajouter), "').setAttribute('disabled', false);");
											auteurWebsocket.tl(2, "});");
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

			if(!classePageSimple) {
				auteurWebsocket.flushClose();
				auteurPageJs.l();
				auteurPageJs.tl(0, "async function websocket", classeApiClasseNomSimple, "(success) {");
				auteurPageJs.tl(1, "window.eventBus.onopen = function () {");
				auteurPageJs.l();
				auteurPageJs.tl(2, "window.eventBus.registerHandler('websocket", classeApiClasseNomSimple, "', function (error, message) {");
//				auteurPageJs.tl(3, langueConfig.getString(ConfigCles.var_rechercher), langueConfig.getString(ConfigCles.var_Page), "();");
				auteurPageJs.tl(3, "var json = JSON.parse(message['body']);");
				auteurPageJs.tl(3, "var id = json['id'];");
				auteurPageJs.tl(3, "var pk = json['pk'];");
				auteurPageJs.tl(3, "var pkPage = document.querySelector('#", i18nPage.getString(I18n.var_Page), "_", classeModele ? classeVarClePrimaire : classeVarCleUnique, "')?.value;");
				auteurPageJs.tl(3, "var pks = json['pks'];");
				auteurPageJs.tl(3, "var empty = json['empty'];");
//					auteurPageJs.tl(3, "if(!empty) {");
				auteurPageJs.tl(3, "var numFound = parseInt(json['numFound']);");
				auteurPageJs.tl(3, "var numPATCH = parseInt(json['numPATCH']);");
				auteurPageJs.tl(3, "var percent = Math.floor( numPATCH / numFound * 100 ) + '%';");
				auteurPageJs.tl(3, "var $box = document.createElement('div');");
				auteurPageJs.tl(3, "$box.setAttribute('class', 'w3-quarter box-' + id + ' ');");
				auteurPageJs.tl(3, "$box.setAttribute('id', 'box-' + id);");
				auteurPageJs.tl(3, "$box.setAttribute('data-numPATCH', numPATCH);");
				auteurPageJs.tl(3, "var $margin = document.createElement('div');");
				auteurPageJs.tl(3, "$margin.setAttribute('class', 'w3-margin ');");
				auteurPageJs.tl(3, "$margin.setAttribute('id', 'margin-' + id);");
				auteurPageJs.tl(3, "var $card = document.createElement('div');");
				auteurPageJs.tl(3, "$card.setAttribute('class', 'w3-card w3-white ');");
				auteurPageJs.tl(3, "$card.setAttribute('id', 'card-' + id);");

				auteurPageJs.tl(3, "var $header = document.createElement('div');");
				auteurPageJs.tl(3, "$header.setAttribute('class', 'w3-container fa-", classeCouleur, " ');");
				auteurPageJs.tl(3, "$header.setAttribute('id', 'header-' + id);");
				auteurPageJs.tl(3, "var iTemplate = document.createElement('template');");
				auteurPageJs.tl(3, "iTemplate.innerHTML = '", classeIcone, "';");
				auteurPageJs.tl(3, "var $i = iTemplate.content;");
				auteurPageJs.tl(3, "var $headerSpan = document.createElement('span');");
				auteurPageJs.tl(3, "$headerSpan.setAttribute('class', '');");
				auteurPageJs.tl(3, "$headerSpan.innerText = '", i18nPage.getString(I18n.var_modifier), " ", classeNomAdjectifPluriel, " ", i18nPage.getString(I18n.var_dans), " ' + json.", i18nPage.getString(I18n.var_tempsRestant), ";");
				auteurPageJs.tl(3, "var $x = document.createElement('span');");
				auteurPageJs.tl(3, "$x.setAttribute('class', 'w3-button w3-display-topright ');");
				auteurPageJs.tl(3, "$x.setAttribute('onclick', 'document.querySelector(\"#card-' + id + '\");');");
				auteurPageJs.tl(3, "$x.classList.add(\"display-none\");");
				auteurPageJs.tl(3, "$x.setAttribute('id', 'x-' + id);");
				auteurPageJs.tl(3, "var $body = document.createElement('div');");
				auteurPageJs.tl(3, "$body.setAttribute('class', 'w3-container w3-padding ');");
				auteurPageJs.tl(3, "$body.setAttribute('id', 'text-' + id);");
				auteurPageJs.tl(3, "var $bar = document.createElement('div');");
				auteurPageJs.tl(3, "$bar.setAttribute('class', 'w3-light-gray ');");
				auteurPageJs.tl(3, "$bar.setAttribute('id', 'bar-' + id);");
				auteurPageJs.tl(3, "var $progress = document.createElement('div');");
				auteurPageJs.tl(3, "$progress.setAttribute('class', 'w3-", classeCouleur, " ');");
				auteurPageJs.tl(3, "$progress.setAttribute('style', 'height: 24px; width: ' + percent + '; ');");
				auteurPageJs.tl(3, "$progress.setAttribute('id', 'progress-' + id);");
				auteurPageJs.tl(3, "$progress.innerText = numPATCH + '/' + numFound;");
				auteurPageJs.tl(3, "$card.append($header);");
				auteurPageJs.tl(3, "$header.append($i);");
				auteurPageJs.tl(3, "$header.append($headerSpan);");
				auteurPageJs.tl(3, "$header.append($x);");
				auteurPageJs.tl(3, "$body.append($bar);");
				auteurPageJs.tl(3, "$bar.append($progress);");
				auteurPageJs.tl(3, "$card.append($body);");
				auteurPageJs.tl(3, "$box.append($margin);");
				auteurPageJs.tl(3, "$margin.append($card);");
				auteurPageJs.tl(3, "if(numPATCH < numFound) {");
				auteurPageJs.tl(4, "var $old_box = document.querySelector('.box-' + id);");
				// auteurPageJs.tl(4, "if(!$old_box.size()) {");
				// auteurPageJs.tl(5, "document.querySelector('.top-box').append($box);");
				// auteurPageJs.tl(4, "} else if($old_box && $old_box.getAttribute('data-numPATCH') < numFound) {");
				// auteurPageJs.tl(5, "document.querySelector('.box-' + id).html($margin);");
				// auteurPageJs.tl(4, "}");
				auteurPageJs.tl(3, "} else {");
				auteurPageJs.tl(4, "document.querySelector('.box-' + id)?.remove();");
				auteurPageJs.tl(3, "}");
				auteurPageJs.tl(3, "if(pk && pkPage && pk == pkPage) {");
				auteurPageJs.tl(4, "if(success)");
				auteurPageJs.tl(5, "success(json);");
				auteurPageJs.tl(3, "}");
//					auteurPageJs.tl(3, "}");
				auteurPageJs.tl(2, "});");
				auteurPageJs.s(auteurWebsocket);
				auteurPageJs.tl(1, "}");
				auteurPageJs.tl(0, "}");

				auteurPageJs.tl(0, "async function websocket", classeApiClasseNomSimple, "Inner(", i18nPage.getString(I18n.var_requeteApi), ") {");
				auteurPageJs.s(wWebsocket);
				auteurPageJs.tl(0, "}");
				auteurPageJs.l();
				auteurPageJs.tl(0, "function ", i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Graphique), classeNomSimple, "(", i18nPage.getString(I18n.var_requeteApi), ") {");
				auteurPageJs.tl(1, "var r = document.querySelector('.", i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Formulaire), " .", i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Reponse), "')?.value;");
				auteurPageJs.tl(1, "if(r) {");
				auteurPageJs.tl(2, "var json = JSON.parse(r);");

				// Facet Graphs
				auteurPageJs.tl(2, "if(json['facetCounts']) {");
				auteurPageJs.tl(3, "var facetCounts = json.facetCounts;");
				auteurPageJs.tl(3, "if(facetCounts['facetPivot'] && facetCounts['facetRanges']) {");
				auteurPageJs.tl(4, "var numPivots = json.responseHeader.params['facet.pivot'].split(',').length;");
				auteurPageJs.tl(4, "var range = facetCounts.facetRanges.ranges[Object.keys(facetCounts.facetRanges.ranges)[0]];");
				auteurPageJs.tl(4, "var rangeName;");
				auteurPageJs.tl(4, "var rangeVar;");
				auteurPageJs.tl(4, "var rangeVarFq;");
				auteurPageJs.tl(4, "var rangeCounts;");
				auteurPageJs.tl(4, "var rangeVals;");
				auteurPageJs.tl(4, "if(range) {");
				auteurPageJs.tl(5, "rangeName = range.name;");
				auteurPageJs.tl(5, "rangeVar = rangeName.substring(0, rangeName.indexOf('_'));");
				auteurPageJs.tl(5, "rangeVarFq = window.varsFq[rangeVar];");
				auteurPageJs.tl(5, "rangeCounts = range.counts;");
				auteurPageJs.tl(5, "rangeVals = Object.keys(rangeCounts).map(key => key);");
				auteurPageJs.tl(4, "}");
				auteurPageJs.tl(4, "var pivot1Name = Object.keys(facetCounts.facetPivot.pivotMap)[0];");
				auteurPageJs.tl(4, "var pivot1Var", i18nPage.getString(I18n.var_Indexe), " = pivot1Name;");
				auteurPageJs.tl(4, "if(pivot1Var", i18nPage.getString(I18n.var_Indexe), ".includes(','))");
				auteurPageJs.tl(5, "pivot1Var", i18nPage.getString(I18n.var_Indexe), " = pivot1Var", i18nPage.getString(I18n.var_Indexe), ".substring(0, pivot1Var", i18nPage.getString(I18n.var_Indexe), ".indexOf(','));");
				auteurPageJs.tl(4, "var pivot1VarObj = Object.values(window.varsFq).filter(o => o.varIndexed === pivot1Var", i18nPage.getString(I18n.var_Indexe), ")[0];");
				auteurPageJs.tl(4, "var pivot1VarFq = pivot1VarObj ? pivot1VarObj.var : 'classSimpleName';");
				auteurPageJs.tl(4, "var pivot1Map = facetCounts.facetPivot.pivotMap[pivot1Name].pivotMap;");
				auteurPageJs.tl(4, "var pivot1Vals = Object.keys(pivot1Map);");
				auteurPageJs.tl(4, "var data = [];");
				auteurPageJs.tl(4, "var layout = {};");
				auteurPageJs.tl(4, "if(range) {");
				auteurPageJs.tl(5, "layout['title'] = '", classeNomAdjectifPluriel, "';");
				auteurPageJs.tl(5, "layout['xaxis'] = {");
				auteurPageJs.tl(6, "title: rangeVarFq.displayName");
				auteurPageJs.tl(5, "}");
				auteurPageJs.tl(5, "if(pivot1Vals.length > 0 && pivot1Map[pivot1Vals[0]].pivotMap && Object.keys(pivot1Map[pivot1Vals[0]].pivotMap).length > 0) {");
				auteurPageJs.tl(6, "var pivot2Var", i18nPage.getString(I18n.var_Indexe), " = pivot1Map[pivot1Vals[0]].pivotMap[Object.keys(pivot1Map[pivot1Vals[0]].pivotMap)[0]].field;");
				auteurPageJs.tl(6, "var pivot2VarObj = Object.values(window.varsFq).filter(o => o.varIndexed === pivot2Var", i18nPage.getString(I18n.var_Indexe), ")[0];");
				auteurPageJs.tl(6, "var pivot2VarFq = pivot2VarObj ? pivot2VarObj.var : 'classSimpleName';");
				auteurPageJs.tl(6, "layout['yaxis'] = {");
				auteurPageJs.tl(7, "title: pivot2VarObj.displayName");
				auteurPageJs.tl(6, "}");
				auteurPageJs.tl(6, "pivot1Vals.forEach((pivot1Val) => {");
				auteurPageJs.tl(7, "var pivot1 = pivot1Map[pivot1Val];");
				auteurPageJs.tl(7, "var pivot1Counts = pivot1.ranges[rangeName].counts;");
				auteurPageJs.tl(7, "var pivot2Map = pivot1.pivotMap;");
				auteurPageJs.tl(7, "var trace = {};");
				auteurPageJs.tl(7, "var facetField;");
				auteurPageJs.tl(7, "trace['showlegend'] = true;");
				auteurPageJs.tl(7, "trace['mode'] = 'lines+markers';");
				auteurPageJs.tl(7, "trace['name'] = pivot1Val;");
				auteurPageJs.tl(7, "trace['x'] = Object.keys(pivot1Counts).map(key => key);");
				auteurPageJs.tl(7, "if(pivot2Map) {");
				auteurPageJs.tl(8, "var xs = [];");
				auteurPageJs.tl(8, "var ys = [];");
				auteurPageJs.tl(8, "var pivot2Vals = Object.keys(pivot2Map);");
				auteurPageJs.tl(8, "pivot2Vals.forEach((pivot2Val) => {");
				auteurPageJs.tl(9, "var pivot2 = pivot2Map[pivot2Val];");
				auteurPageJs.tl(9, "var pivot2Counts = pivot2.ranges[rangeName].counts;");
				auteurPageJs.tl(9, "Object.entries(pivot2Counts).forEach(([key, count]) => {");
//				auteurPageJs.tl(10, "ys.push(parseInt(count) > 0 ? parseFloat(pivot2Val) : 0);");
				auteurPageJs.tl(10, "xs.push(key);");
				auteurPageJs.tl(10, "ys.push(parseFloat(pivot2Val));");
				auteurPageJs.tl(9, "});");
				auteurPageJs.tl(8, "});");
				auteurPageJs.tl(8, "trace['y'] = ys;");
				auteurPageJs.tl(8, "trace['x'] = xs;");
				auteurPageJs.tl(7, "} else {");
				auteurPageJs.tl(8, "trace['x'] = Object.keys(pivot1Counts).map(key => key);");
				auteurPageJs.tl(8, "trace['y'] = Object.entries(pivot1Counts).map(([key, count]) => count);");
//				auteurPageJs.tl(8, "var pivot2Map = pivot1.pivotMap;");
//				auteurPageJs.tl(8, "var pivot2Vals = Object.keys(pivot2Map);");
//				auteurPageJs.tl(8, "pivot2Vals.forEach((pivot2Val) => {");
//				auteurPageJs.tl(9, "var pivot2 = pivot2Map[pivot2Val];");
//				auteurPageJs.tl(9, "var pivot2Counts = pivot2.ranges[rangeName].counts;");
//				auteurPageJs.tl(9, "trace['x'] = Object.keys(pivot2Counts).map(key => key);");
//				auteurPageJs.tl(9, "trace['y'] = Object.entries(pivot2Counts).map((key, count) => count);");
//				auteurPageJs.tl(9, "trace['mode'] = 'lines';");
//				auteurPageJs.tl(8, "});");
				auteurPageJs.tl(7, "}");
				auteurPageJs.tl(7, "data.push(trace);");
				auteurPageJs.tl(6, "});");
				auteurPageJs.tl(5, "} else {");
				auteurPageJs.tl(6, "layout['yaxis'] = {");
				auteurPageJs.tl(7, "title: pivot1VarObj.displayName");
				auteurPageJs.tl(6, "}");
				auteurPageJs.tl(6, "pivot1Vals.forEach((pivot1Val) => {");
				auteurPageJs.tl(7, "var pivot1 = pivot1Map[pivot1Val];");
				auteurPageJs.tl(7, "var pivot1Counts = pivot1.ranges[rangeName].counts;");
				auteurPageJs.tl(7, "var pivot2Map = pivot1.pivotMap;");
				auteurPageJs.tl(7, "var trace = {};");
				auteurPageJs.tl(7, "var facetField;");
				auteurPageJs.tl(7, "trace['showlegend'] = true;");
				auteurPageJs.tl(7, "trace['mode'] = 'lines+markers';");
				auteurPageJs.tl(7, "trace['name'] = pivot1Val;");
				auteurPageJs.tl(7, "if(window.varsRange[window.defaultRangeVar].classSimpleName == 'ZonedDateTime') {");
				auteurPageJs.tl(8, "trace['x'] = Object.keys(pivot1Counts).map(key => moment.tz(key, Intl.DateTimeFormat().resolvedOptions().timeZone).format('YYYY-MM-DDTHH:mm:ss.SSSS'));");
				auteurPageJs.tl(7, "} else {");
				auteurPageJs.tl(8, "trace['x'] = Object.keys(pivot1Counts).map(key => key);");
				auteurPageJs.tl(7, "}");
				auteurPageJs.tl(7, "trace['y'] = Object.entries(pivot1Counts).map(([key, count]) => count);");
				auteurPageJs.tl(7, "data.push(trace);");
				auteurPageJs.tl(6, "});");
				auteurPageJs.tl(5, "}");
				auteurPageJs.tl(5, "Plotly.react('htmBody", i18nPage.getString(I18n.var_Graphique), classePageNomSimple, "', data, layout);");
				auteurPageJs.tl(4, "}");
				auteurPageJs.tl(3, "}");
				auteurPageJs.tl(2, "}");

				// Maps
				if(classeVarEmplacement != null || classeVarAire != null) {
					auteurPageJs.l();
					auteurPageJs.tl(2, "// ", i18nPage.getString(I18n.var_Graphique), " ", i18nPage.getString(I18n.var_Emplacement));
					auteurPageJs.tl(2, "window.mapLayers = {};");
					auteurPageJs.tl(2, "function onEachFeature(feature, layer) {");
					auteurPageJs.tl(3, "let popupContent = ", i18nPage.getString(I18n.var_htmInfobulle), classeNomSimple, "(feature, layer);");
					auteurPageJs.tl(3, "layer.bindPopup(popupContent);");
					auteurPageJs.tl(3, "window.mapLayers[feature.properties.id] = layer;");
					auteurPageJs.tl(2, "};");
					auteurPageJs.tl(2, "if(window.map", classeNomSimple, ") {");
					auteurPageJs.tl(3, "window.geoJSON", classeNomSimple, ".clearLayers();");
					auteurPageJs.tl(3, "window.", i18nPage.getString(I18n.var_liste), classeNomSimple, ".forEach((", StringUtils.uncapitalize(classeNomSimple), ", index) => {");
					if(classeVarEmplacement != null) {
						auteurPageJs.tl(4, "if(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, ") {");
						auteurPageJs.tl(5, "var shapes = [];");
						auteurPageJs.tl(5, "if(Array.isArray(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, "))");
						auteurPageJs.tl(6, "shapes = shapes.concat(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, ");");
						auteurPageJs.tl(5, "else");
						auteurPageJs.tl(6, "shapes.push(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, ");");
						auteurPageJs.tl(5, "shapes.forEach(function(shape, index) {");
						auteurPageJs.tl(6, "var features = [{");
						auteurPageJs.tl(7, "\"type\": \"Feature\"");
						auteurPageJs.tl(7, ", \"properties\": ", StringUtils.uncapitalize(classeNomSimple));
						auteurPageJs.tl(7, ", \"geometry\": shape");
						auteurPageJs.tl(7, ", \"index\": index");
						auteurPageJs.tl(6, "}];");
						auteurPageJs.tl(6, "var layer = L.geoJSON(features, {");
						auteurPageJs.tl(7, "onEachFeature: onEachFeature");
						auteurPageJs.tl(7, ", style: ", i18nPage.getString(I18n.var_jsStyle), classeNomSimple, "");
						auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
						auteurPageJs.tl(8, "return L.circleMarker(latlng, ", i18nPage.getString(I18n.var_jsStyle), classeNomSimple, "(feature));");
						auteurPageJs.tl(7, "}");
						auteurPageJs.tl(6, "});");
						auteurPageJs.tl(6, "window.geoJSON", classeNomSimple, ".addLayer(layer);");
						auteurPageJs.tl(5, "});");
						auteurPageJs.tl(4, "}");
					}
					if(classeVarAire != null) {
						auteurPageJs.tl(4, "if(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, ") {");
						auteurPageJs.tl(5, "var shapes = [];");
						auteurPageJs.tl(5, "if(Array.isArray(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, "))");
						auteurPageJs.tl(6, "shapes = shapes.concat(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, ");");
						auteurPageJs.tl(5, "else");
						auteurPageJs.tl(6, "shapes.push(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, ");");
						auteurPageJs.tl(5, "shapes.forEach(function(shape, index) {");
						auteurPageJs.tl(6, "var features = [{");
						auteurPageJs.tl(7, "\"type\": \"Feature\"");
						auteurPageJs.tl(7, ", \"properties\": ", StringUtils.uncapitalize(classeNomSimple));
						auteurPageJs.tl(7, ", \"geometry\": shape");
						auteurPageJs.tl(7, ", \"index\": index");
						auteurPageJs.tl(6, "}];");
						auteurPageJs.tl(6, "var layer = L.geoJSON(features, {");
						auteurPageJs.tl(7, "onEachFeature: onEachFeature");
						auteurPageJs.tl(7, ", style: ", i18nPage.getString(I18n.var_jsStyle), classeNomSimple, "");
						auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
						auteurPageJs.tl(8, "return L.circleMarker(latlng, ", i18nPage.getString(I18n.var_jsStyle), classeNomSimple, "(feature));");
						auteurPageJs.tl(7, "}");
						auteurPageJs.tl(6, "});");
						auteurPageJs.tl(6, "window.geoJSON", classeNomSimple, ".addLayer(layer);");
						auteurPageJs.tl(5, "});");
						auteurPageJs.tl(4, "}");
					}
					auteurPageJs.tl(3, "});");
					auteurPageJs.tl(2, "} else {");
					auteurPageJs.tl(3, "window.map", classeNomSimple, " = L.map('htmBody", i18nPage.getString(I18n.var_Graphique), i18nPage.getString(I18n.var_Emplacement), classePageNomSimple, "', {closePopupOnClick: false});");
					auteurPageJs.tl(3, "var data = [];");
					auteurPageJs.tl(3, "var layout = {};");
					auteurPageJs.tl(3, "layout['showlegend'] = true;");
					auteurPageJs.tl(3, "layout['dragmode'] = 'zoom';");
					auteurPageJs.tl(3, "layout['uirevision'] = 'true';");
					auteurPageJs.tl(3, "var legend = L.control({position: 'bottomright'});");
					auteurPageJs.tl(3, "legend.onAdd = ", i18nPage.getString(I18n.var_jsLegende), classeNomSimple, ";");
					auteurPageJs.tl(3, "legend.addTo(window.map", classeNomSimple, ");");
					auteurPageJs.tl(3, "L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {");
					auteurPageJs.tl(4, "maxZoom: 19,");
					auteurPageJs.tl(4, "attribution: '&copy; <a href=\"http://www.openstreetmap.org/copyright\">OpenStreetMap</a>'");
					auteurPageJs.tl(3, "}).addTo(window.map", classeNomSimple, ");");
					auteurPageJs.l();
					auteurPageJs.tl(3, "if(window['DEFAULT_MAP_LOCATION'] && window['DEFAULT_MAP_ZOOM'])");
					auteurPageJs.tl(4, "window.map", classeNomSimple, ".setView([window['DEFAULT_MAP_LOCATION']['lat'], window['DEFAULT_MAP_LOCATION']['lon']], window['DEFAULT_MAP_ZOOM']);");
					auteurPageJs.tl(3, "else if(window['DEFAULT_MAP_ZOOM'])");
					auteurPageJs.tl(4, "window.map", classeNomSimple, ".setView(null, window['DEFAULT_MAP_ZOOM']);");
					auteurPageJs.tl(3, "else if(window['DEFAULT_MAP_LOCATION'])");
					auteurPageJs.tl(4, "window.map", classeNomSimple, ".setView([window['DEFAULT_MAP_LOCATION']['lat'], window['DEFAULT_MAP_LOCATION']['lon']]);");
					auteurPageJs.l();
					auteurPageJs.tl(3, "layout['margin'] = { r: 0, t: 0, b: 0, l: 0 };");
					auteurPageJs.tl(3, "window.geoJSON", classeNomSimple, " = L.geoJSON().addTo(window.map", classeNomSimple, ");");
					//auteurPageJs.tl(3, "window.geoJSONLayerGroup", classeNomSimple, " = L.LayerGroup();");
					auteurPageJs.tl(3, "window.", i18nPage.getString(I18n.var_liste), classeNomSimple, ".forEach((", StringUtils.uncapitalize(classeNomSimple), ", index) => {");
					if(classeVarEmplacement != null) {
						auteurPageJs.tl(4, "if(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, ") {");
						auteurPageJs.tl(5, "var shapes = [];");
						auteurPageJs.tl(5, "if(Array.isArray(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, "))");
						auteurPageJs.tl(6, "shapes = shapes.concat(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, ");");
						auteurPageJs.tl(5, "else");
						auteurPageJs.tl(6, "shapes.push(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarEmplacement, ");");
						auteurPageJs.tl(5, "shapes.forEach(shape => {");
						auteurPageJs.tl(6, "var features = [{");
						auteurPageJs.tl(7, "\"type\": \"Feature\"");
						auteurPageJs.tl(7, ", \"properties\": ", StringUtils.uncapitalize(classeNomSimple));
						auteurPageJs.tl(7, ", \"geometry\": shape");
						auteurPageJs.tl(7, ", \"index\": index");
						auteurPageJs.tl(6, "}];");
						auteurPageJs.tl(6, "var layer = L.geoJSON(features, {");
						auteurPageJs.tl(7, "onEachFeature: onEachFeature");
						auteurPageJs.tl(7, ", style: ", i18nPage.getString(I18n.var_jsStyle), classeNomSimple, "");
						auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
						auteurPageJs.tl(8, "return L.circleMarker(latlng, ", i18nPage.getString(I18n.var_jsStyle), classeNomSimple, "(feature));");
						auteurPageJs.tl(7, "}");
						auteurPageJs.tl(6, "});");
						auteurPageJs.tl(6, "window.geoJSON", classeNomSimple, ".addLayer(layer);");
						auteurPageJs.tl(5, "});");
						auteurPageJs.tl(4, "}");
					}
					if(classeVarAire != null) {
						auteurPageJs.tl(4, "if(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, ") {");
						auteurPageJs.tl(5, "var shapes = [];");
						auteurPageJs.tl(5, "if(Array.isArray(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, "))");
						auteurPageJs.tl(6, "shapes = shapes.concat(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, ");");
						auteurPageJs.tl(5, "else");
						auteurPageJs.tl(6, "shapes.push(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, ");");
						auteurPageJs.tl(5, "shapes.forEach(shape => {");
						auteurPageJs.tl(6, "var features = [{");
						auteurPageJs.tl(7, "\"type\": \"Feature\"");
						auteurPageJs.tl(7, ", \"properties\": ", StringUtils.uncapitalize(classeNomSimple));
						auteurPageJs.tl(7, ", \"geometry\": shape");
						auteurPageJs.tl(7, ", \"index\": index");
						auteurPageJs.tl(6, "}];");
						auteurPageJs.tl(6, "var layer = L.geoJSON(features, {");
						auteurPageJs.tl(7, "onEachFeature: onEachFeature");
						auteurPageJs.tl(7, ", style: ", i18nPage.getString(I18n.var_jsStyle), classeNomSimple, "");
						auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
						auteurPageJs.tl(8, "return L.circleMarker(latlng, ", i18nPage.getString(I18n.var_jsStyle), classeNomSimple, "(feature));");
						auteurPageJs.tl(7, "}");
						auteurPageJs.tl(6, "});");
						auteurPageJs.tl(6, "window.geoJSON", classeNomSimple, ".addLayer(layer);");
						auteurPageJs.tl(5, "});");
						auteurPageJs.tl(4, "}");
					}
					auteurPageJs.tl(3, "});");
					auteurPageJs.tl(3, "window.map", classeNomSimple, ".on('contextmenu', function(e) {");
					auteurPageJs.tl(4, "var htm = '';");
					auteurPageJs.tl(4, "if(window.", i18nPage.getString(I18n.var_liste), classeNomSimple, ".length == 1) {");
					auteurPageJs.tl(5, "window.", i18nPage.getString(I18n.var_liste), classeNomSimple, ".forEach((", StringUtils.uncapitalize(classeNomSimple), ", index) => {");
					if(classeVarEmplacement != null) {
						auteurPageJs.tl(6, "htm += '<div><button"
								, " onclick=\"patch", i18nPage.getString(I18n.var_Emplacement), "(event.target, "
								, "{ &quot;coordinates&quot;: [ ' + e.latlng.lng + ', ' + e.latlng.lat + ' ], &quot;type&quot;: &quot;Point&quot; }"
								, ");\">"
								, i18nPage.getString(I18n.str_Definir), " ", classeVarEmplacement, " ", i18nPage.getString(I18n.str_de), " ' + ", StringUtils.uncapitalize(classeNomSimple), ".", classeVarTitre, " + '</button></div>';");
					}
					// if(classeVarAire != null) {
					// 	auteurPageJs.tl(4, "if(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, ") {");
					// 	auteurPageJs.tl(5, "if(!Array.isArray(", StringUtils.uncapitalize(classeNomSimple), ".", classeVarAire, ")) {");
					// 	auteurPageJs.tl(6, "htm += '<div><", composantsWebPrefixe, "button>Set ' + ", StringUtils.uncapitalize(classeNomSimple), " + ' of ' + ", StringUtils.uncapitalize(classeNomSimple), " + '</", composantsWebPrefixe, "button></div>'");
					// 	auteurPageJs.tl(5, "}");
					// 	auteurPageJs.tl(4, "}");
					// }
					auteurPageJs.tl(5, "});");
					auteurPageJs.tl(4, "}");
					auteurPageJs.tl(4, "var popup = L.popup()");
					auteurPageJs.tl(6, ".setLatLng(e.latlng)");
					auteurPageJs.tl(6, ".openOn(window.map", classeNomSimple, ")");
					auteurPageJs.tl(6, ".setContent(htm);");
					auteurPageJs.tl(3, "});");
					auteurPageJs.tl(3, "window.map", classeNomSimple, ".on('popupopen', function(e) {");
					auteurPageJs.tl(4, "if(e.popup._source) {");
					auteurPageJs.tl(5, "var feature = e.popup._source.feature;");
					auteurPageJs.tl(5, i18nPage.getString(I18n.var_jsInfobulle), classeNomSimple, "(e, feature);");
					auteurPageJs.tl(4, "}");
					auteurPageJs.tl(3, "});");
					auteurPageJs.tl(3, "const drawnItems = new L.FeatureGroup();");
					auteurPageJs.tl(3, "window.map", classeNomSimple, ".addLayer(drawnItems);");
					auteurPageJs.tl(3, "const drawControl = new L.Control.Draw({");
					auteurPageJs.tl(4, "edit: {");
					auteurPageJs.tl(5, "featureGroup: drawnItems");
					auteurPageJs.tl(4, "},");
					auteurPageJs.tl(4, "draw: {");
					auteurPageJs.tl(5, "polygon: true");
					auteurPageJs.tl(5, ", polygon: true");
					auteurPageJs.tl(5, ", polyline: true");
					auteurPageJs.tl(5, ", rectangle: true");
					auteurPageJs.tl(5, ", circle: true");
					auteurPageJs.tl(5, ", marker: true");
					auteurPageJs.tl(4, "}");
					auteurPageJs.tl(3, "});");
					auteurPageJs.tl(3, "window.map", classeNomSimple, ".addControl(drawControl);");
					auteurPageJs.tl(3, "window.map", classeNomSimple, ".on(L.Draw.Event.CREATED, function (event) {");
					auteurPageJs.tl(4, "drawnItems.addLayer(event.layer);");
					auteurPageJs.tl(3, "});");
					auteurPageJs.tl(2, "}");
				}
				auteurPageJs.tl(1, "}");
				auteurPageJs.tl(0, "}");
				if(classeVarEmplacement != null) {
					auteurPageJs.tl(0, "function patch", i18nPage.getString(I18n.var_Emplacement), "(target, ", classeVarEmplacement, ") {");
					auteurPageJs.tl(1, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + ", StringUtils.uncapitalize(classeNomSimple), ".", classeModele ? classeVarClePrimaire : classeVarCleUnique, " }]");
					auteurPageJs.tl(3, ", 'set", StringUtils.capitalize(classeVarEmplacement), "', ", classeVarEmplacement);
					auteurPageJs.tl(3, ", target");
					auteurPageJs.tl(3, ", function(", i18nPage.getString(I18n.var_reponse), ", e) { ", i18nPage.getString(I18n.var_ajouterLueur), "(target); }");
					auteurPageJs.tl(3, ", function(", i18nPage.getString(I18n.var_reponse), ", e) { ", i18nPage.getString(I18n.var_ajouterErreur), "(target); }");
					auteurPageJs.tl(3, ");");
					auteurPageJs.tl(0, "}");
				}
				auteurPageJs.l();
				auteurPageJs.tl(0, "function animate", i18nPage.getString(I18n.var_Stats), "() {");
				auteurPageJs.tl(1, "document.querySelector('#pageSearchVal-fq", classeNomSimple, "_time').innerText = '';");
				auteurPageJs.tl(1, "searchPage('", classeNomSimple, "', function() {");
				auteurPageJs.tl(2, "let speedRate = parseFloat(document.querySelector('#animate", i18nPage.getString(I18n.var_Stats), "Speed')?.value) * 1000;");
				auteurPageJs.tl(2, "let xStep = parseFloat(document.querySelector('#animate", i18nPage.getString(I18n.var_Stats), "Step')?.value);");
				auteurPageJs.tl(2, "let xMin = parseFloat(document.querySelector('#animate", i18nPage.getString(I18n.var_Stats), "Min')?.value);");
				auteurPageJs.tl(2, "let xMax = parseFloat(document.querySelector('#animate", i18nPage.getString(I18n.var_Stats), "Max')?.value);");
				auteurPageJs.tl(2, "let x = xMin;");
				auteurPageJs.l();
				auteurPageJs.tl(2, "let animateInterval = window.setInterval(() => {");
				auteurPageJs.tl(3, "x = x + xStep;");
				auteurPageJs.tl(3, "if (x > xMax || x < 0) {");
				auteurPageJs.tl(4, "clearInterval(animateInterval);");
				auteurPageJs.tl(3, "}");
				auteurPageJs.tl(3, "document.querySelector('#fq", classeNomSimple, "_time').value = x;");
				auteurPageJs.tl(3, "document.querySelector('#fq", classeNomSimple, "_time').onchange();");
				auteurPageJs.tl(3, "searchPage('", classeNomSimple, "');");
				auteurPageJs.tl(2, "}, speedRate);");
				auteurPageJs.tl(1, "});");
				auteurPageJs.tl(0, "}");
			}
			auteurPageJsModule.tl(0, "Promise.all([");
			auteurPageJsModule.tl(2, "customElements.whenDefined('", composantsWebPrefixe, "button')");
			auteurPageJsModule.tl(2, ", customElements.whenDefined('", composantsWebPrefixe, "input')");
			// auteurPageJsModule. tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "checkbox')");
			// auteurPageJsModule. tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "option')");
			// auteurPageJsModule. tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "select')");
			// auteurPageJsModule. tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "textarea')");
			auteurPageJsModule.tl(2, "]).then(() => {");

			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");

				if(classeApiMethode.equals(i18nGlobale.getString(I18n.var_PageRecherche)) || classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals("DELETE") || classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTCopie)) || classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
					if(classeApiMethode.equals("DELETE")) {
						auteurPageJsModule.l();
						auteurPageJsModule.tl(5, "document.querySelector('#htm", i18nGlobale.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "')?.addEventListener('click', (event) => {");
						auteurPageJsModule.tl(6, "var confirmResponse = confirm('", i18nGlobale.getString(I18n.str_confirmer_supprimer), "'); ");
						auteurPageJsModule.tl(6, "if(confirmResponse) { ");
						auteurPageJsModule.tl(7, "var ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " =  event.currentTarget.getAttribute('data-", classeModele ? classeVarClePrimaire : classeVarCleUnique, "');");
						auteurPageJsModule.tl(7, "delete", classeNomSimple, "([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " }]");
						auteurPageJsModule.tl(9, ", event.currentTarget");
						auteurPageJsModule.tl(9, ", ", classeModele ? classeVarClePrimaire : classeVarCleUnique);
						auteurPageJsModule.tl(9, ", function(", i18nGlobale.getString(I18n.var_reponse), ", target) { ", i18nGlobale.getString(I18n.var_ajouterLueur), "(target); }");
						auteurPageJsModule.tl(9, ", function(", i18nGlobale.getString(I18n.var_reponse), ", target) { ", i18nGlobale.getString(I18n.var_ajouterErreur), "(target); }");
						auteurPageJsModule.tl(9, ");");
						auteurPageJsModule.tl(6, "}");
						auteurPageJsModule.tl(5, "});");
					} else {
						auteurPageJsModule.l();
						auteurPageJsModule.tl(5, "document.querySelector('#htm", i18nGlobale.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "')?.addEventListener('click', (event) => {");
						auteurPageJsModule.tl(6, "document.querySelector('#", classeApiOperationIdMethode, i18nGlobale.getString(I18n.var_Dialogue), "').show();");
						auteurPageJsModule.tl(5, "});");
					}
				}
			}

			auteurPageJsModule.s(wJsModuleInit);
			auteurPageJsModule.tl(0, "});");
	}

	public void ecrirePageRechercheSuggere(String langueNom, JsonObject i18nPage) throws Exception {
			if(!classePageSimple) {
				tl(0, "{%- block htm", i18nPage.getString(I18n.var_Suggere), classePageNomSimple, " %}");


				tl(4, "<div>");
				tl(5, "<div>");
				tl(6, "<span>");
				tl(7, i18nPage.getString(I18n.var_rechercher), " ", classeNomAdjectifPluriel, i18nPage.getString(I18n.str_deuxPoints));
				tl(6, "</span>");
				tl(5, "</div>");
				tl(4, "</div>");
				tl(4, "<div>");

				tl(5, "<", composantsWebPrefixe, "input");
				tl(7, "type=\"text\"");

				if(classeRechercherTousNom != null) {
					tl(7, "placeholder=\"", classeRechercherTousNom, "\"");
				}

				tl(7, "class=\"", i18nPage.getString(I18n.var_suggere), classeApiClasseNomSimple, " \"");
				tl(7, "name=\"", i18nPage.getString(I18n.var_suggere), classeApiClasseNomSimple, "\"");
				tl(7, "id=\"", i18nPage.getString(I18n.var_suggere), classeApiClasseNomSimple, "{{id}}\"");
				tl(7, "autocomplete=\"off\"");
				tl(7, "oninput=\"", i18nPage.getString(I18n.var_suggere), classeApiClasseNomSimple, StringUtils.capitalize(classeVarSuggere), "( [ { 'name': 'q', 'value': '", classeVarSuggere, ":' + this.value }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", i18nPage.getString(I18n.var_classeNomCanonique), ",", classeVarClePrimaire, classeVarUrlPk == null ? "" : "," + classeVarUrlPk, classeVarTitre == null ? "" : "," + classeVarTitre, "' } ], document.querySelector('#", i18nPage.getString(I18n.var_suggere), "List", classeApiClasseNomSimple, "{{id}}'), {{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}; \"");
				tl(7, "onkeyup=\"if (event.keyCode === 13) { event.preventDefault(); window.location.href = '", classePageUriMethode + "?q={{ query1 }}:' + encodeURIComponent(this.value) + '{{fqs}}{{sorts}}&amp;rows={{start2}}&amp;rows={{rows1}}\"");
				tl(1, "{% if ", i18nPage.getString(I18n.var_liste), classeApiClasseNomSimple, " is defined %}");
				tl(7, "value=\"{{query2}}\"");
				tl(1, "{% endif %}");
				tl(7, ">");
				tl(5, "</", composantsWebPrefixe, "input>");
				tl(5, "<", composantsWebPrefixe, "button");
				tl(7, "onclick=\"window.location.href = '", classePageUriMethode + "?q=&quot;, query1, &quot;:' + encodeURIComponent(this.previousElementSibling.value) + '&quot;, fqs, sorts, &quot;&amp;rows=&quot;, start2, &quot;&amp;rows=&quot;, rows1, &quot;'; \"");
				tl(7, ">");
				tl(6, "<i class=\"fas fa-search \"></i>");
				tl(5, "</", composantsWebPrefixe, "button>");

				tl(5, "<div>");
				tl(6, "<div>");
				tl(7, "<div>");
				tl(8, "<ul id=\"", i18nPage.getString(I18n.var_suggere), "List", classeApiClasseNomSimple, "{{id}}\">");
				tl(8, "</ul>");
				tl(7, "</div>");
				tl(6, "</div>");
				tl(5, "</div>");

				// voir tous //
				tl(5, "<div class=\"\">");
				tl(6, "<a href=\"", classePageUriMethode, "\" class=\"\">");
				if(classeIcone != null)
					tl(7, classeIcone);
				tl(7, i18nPage.getString(I18n.var_voir), " ", classeTousNom);
				tl(6, "</a>");
				tl(5, "</div>");
				tl(4, "</div>");

				tl(0, "{%- endblock htm", i18nPage.getString(I18n.var_Suggere), classePageNomSimple, " %}");
			}
	}

	public void ecrirePageRechercheFormulaire(String langueNom, JsonObject i18nPage) throws Exception {

			// formulaires //

			tl(1, "{% if ", i18nPage.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
			tl(2, "{% if \"PATCH\" in ", i18nGlobale.getString(I18n.var_portees), " %}");

			tl(6, "<", composantsWebPrefixe, "divider></", composantsWebPrefixe, "divider>");

			tl(6, "<div id=\"htm", i18nPage.getString(I18n.var_Bouton), i18nPage.getString(I18n.var_Groupe), "\" class=\"round-first-and-last-column-pill \">");

			// recharger 1 //
			s("{% if ", uncapitalizeClasseApiClasseNomSimple, "Count == 1 %}");
			// s("<", composantsWebPrefixe, "tooltip content=\"", i18nPage.getString(I18n.str_Recharger), " ", classeCeNom, "\">");
			s("<", composantsWebPrefixe, "button id=\"", i18nPage.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeCe)), classeGenPageNomSimple, "\"");
			s(" onclick=\"patch{{", i18nPage.getString(I18n.var_classeNomSimple), "}}Vals( [ {name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' } ], {}, this, function() { ", i18nPage.getString(I18n.var_ajouterLueur), "(document.querySelector('#", i18nPage.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeCe)), classeGenPageNomSimple, "')); }, function() { ", i18nPage.getString(I18n.var_ajouterErreur), "(document.querySelector('#", i18nPage.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeCe)), classeGenPageNomSimple, "')); }); return false; \">");
			s("<i slot=\"prefix\" class=\"fas fa-sync-alt \"></i>");
			s(i18nPage.getString(I18n.var_recharger));
			s("</", composantsWebPrefixe, "button>");
			// s("</", composantsWebPrefixe, "tooltip>");
			s("{% endif %}");

			// recharger tous //
			s("{% if ", uncapitalizeClasseApiClasseNomSimple, "Count > 0 %}");
			// s("<", composantsWebPrefixe, "tooltip content=\"", i18nPage.getString(I18n.str_Recharger), " ", classeTousNom, "\">");
			s("<", composantsWebPrefixe, "button id=\"", i18nPage.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeTous)), classeGenPageNomSimple, "{{id}}\"");
			s(" onclick=\"patch{{", i18nPage.getString(I18n.var_classeNomSimple), "}}Vals([], {}, this, function() { ", i18nPage.getString(I18n.var_ajouterLueur), "(document.querySelector('#", i18nPage.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeTous)), classeGenPageNomSimple, "{{id}}')); }, function() { ", i18nPage.getString(I18n.var_ajouterErreur), "(document.querySelector('#", i18nPage.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeTous)), classeGenPageNomSimple, "{{id}}')); }); \"");
			s(">");
			s("<i slot=\"prefix\" class=\"fa-kit fa-solid-arrows-rotate-rotate\"></i>");
			s(i18nPage.getString(I18n.str_Recharger), " ", classeTous);
			s("</", composantsWebPrefixe, "button>");
			// s("</", composantsWebPrefixe, "tooltip>");
			s("{% endif %}");

			s("{% endif %}");
			s("{% endif %}");

			// formulaires //

			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");

				if(classeApiMethode.equals("POST")) {
					if(activerRoleAdmin) {
						s("{% if ", i18nPage.getString(I18n.var_AUTH_PORTEE_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
					}
					s("{%- if ", uncapitalizeClasseApiClasseNomSimple, "Count == 1 %}");
					s("{%- if ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " is defined %}");
					s("{{ htm", i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
					s("{%- else %}");
					s("{{ htm", i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
					s("{%- endif %}");
					s("{%- else %}");
					s("{{ htm", i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
					s("{%- endif %}");
					if(activerRoleAdmin) {
						s("{%- endif %}");
					}
				} else if(classeApiMethode.equals("DELETE")) {
					if(activerRoleAdmin) {
						s("{% if ", i18nPage.getString(I18n.var_AUTH_PORTEE_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
					}
					s("{%- if ", uncapitalizeClasseApiClasseNomSimple, "Count == 1 %}");
					s("{%- if ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " is defined %}");
					s("{{ htm", i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
					s("{%- else %}");
					s("{%- endif %}");
					s("{%- else %}");
					s("{%- endif %}");
					if(activerRoleAdmin) {
						s("{%- endif %}");
					}
				} else if(classeApiMethode.equals("PATCH")) {
					s("{%- if ", i18nPage.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
					s("{%- if ", uncapitalizeClasseApiClasseNomSimple, "Count > 0 %}");
					s("{{ htm", i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
					s("{%- endif %}");
					s("{%- endif %}");
				} else if(classeApiMethode.equals(i18nPage.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
					s("{%- if ", i18nPage.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
					s("{{ htm", i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
					s("{%- endif %}");
				} else if(classeApiMethode.equals(i18nPage.getString(I18n.var_PUTCopie))) {
					s("{{ htm", i18nPage.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
				}
			}
			tl(6, "</div>");

			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");

				if(classeApiMethode.equals("POST")) {
					if(activerRoleAdmin) {
						tl(1, "{% if ", i18nPage.getString(I18n.var_AUTH_PORTEE_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
					}
					tl(2, "{%- if ", uncapitalizeClasseApiClasseNomSimple, "Count == 1 %}");
					tl(3, "{%- if ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " is defined %}");
					tl(4, "{{ htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
					tl(3, "{%- else %}");
					tl(4, "{{ htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
					tl(3, "{%- endif %}");
					tl(2, "{%- else %}");
					tl(4, "{{ htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
					tl(2, "{%- endif %}");
					if(activerRoleAdmin) {
						tl(1, "{%- endif %}");
					}
				} else if(classeApiMethode.equals("DELETE")) {
					if(activerRoleAdmin) {
						tl(1, "{% if ", i18nPage.getString(I18n.var_AUTH_PORTEE_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
					}
					tl(2, "{%- if ", uncapitalizeClasseApiClasseNomSimple, "Count == 1 %}");
					tl(3, "{%- if ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " is defined %}");
					tl(4, "{{ htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
					tl(3, "{%- else %}");
					tl(3, "{%- endif %}");
					tl(2, "{%- else %}");
					tl(2, "{%- endif %}");
					if(activerRoleAdmin) {
						tl(1, "{%- endif %}");
					}
				} else if(classeApiMethode.equals("PATCH")) {
					tl(1, "{%- if ", i18nPage.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
					tl(2, "{%- if ", uncapitalizeClasseApiClasseNomSimple, "Count > 0 %}");
					tl(4, "{{ htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
					tl(2, "{%- endif %}");
					tl(1, "{%- endif %}");
				} else if(classeApiMethode.equals(i18nPage.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
					tl(1, "{%- if ", i18nPage.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
					tl(4, "{{ htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
					tl(1, "{%- endif %}");
				} else if(classeApiMethode.equals(i18nPage.getString(I18n.var_PUTCopie))) {
					tl(4, "{{ htm", i18nPage.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
				}
			}

	}

	public void ecrireBarreLaterale(String langueNom, JsonObject i18nPage) throws Exception {
		ToutEcrivain oAncien = o;
		o = auteurBarreLateraleJinja;

		/////////////////
		// pageContent //
		/////////////////

		tl(4, "<div class=\"htmBody", i18nPage.getString(I18n.var_BarreLaterale), "", classePageNomSimple, " \">");

		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_BarreLaterale), "", classePageNomSimple, " %}");

		/////////////
		// sidebar //
		/////////////

		///////////////
		// sidebar q //
		///////////////

		tl(5, "<", composantsWebPrefixe, "drawer placement=\"end\" id=\"site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_Recherche), "\">");
		tl(6, "<div>");
		tl(7, "<span>");
		t(7, "<i class=\"fad fa-magnifying-glass \"></i>");
		l(" ", i18nPage.getString(I18n.var_Recherche), "</span>");
		tl(6, "</div>");
		tl(6, "<div>");

		//////////////////////
		// htmBodyRecherche //
		//////////////////////

		tl(7, "{%- block htmBody", i18nPage.getString(I18n.var_Recherche), classePageNomSimple, " %}");
		tl(7, "<div>");
		tl(9, "{% for key, value in varsQ.items() %}");
		tl(9, "<div>");
		tl(10, "<div>");
		t(11, "<label for=\"q", classeNomSimple, "_{{ key }}\">");
		s("{{ value.", i18nPage.getString(I18n.var_nomAffichage), " }}");
		s("<sup> ({{ value.", i18nPage.getString(I18n.var_classeNomSimple), " }})</sup>");
		l("</label>");
		tl(10, "</div>");
		tl(9, "</div>");
		tl(9, "<div>");
		tl(10, "<div>");
		t(11, "<", composantsWebPrefixe, "input");
		s(" id=\"q", classeNomSimple, "_{{ key }}\"");
		s(" placeholder=\"{{ displayName }}\"");
		s(" data-var=\"{{ key }}\"");
		s(" autocomplete=\"off=\"");
		l("></", composantsWebPrefixe, "input>");
		tl(11, "<div id=\"q", classeNomSimple, "Div_{{ key }}\" class=\"pageSearchVal \"></div>");
		tl(10, "</div>");
		tl(9, "</div>");
		tl(9, "{% endfor %}");

		///////////
		// start //
		///////////

		tl(8, "<div>");
		tl(9, "<div>");
		t(10, "<label for=\"q", classeNomSimple, "_", i18nPage.getString(I18n.var_Debut), "\">");
		s("", i18nPage.getString(I18n.var_debut), "");
		s("<sup> (Long)</sup>");
		l("</label>");
		tl(9, "</div>");
		tl(8, "</div>");
		tl(8, "<div>");
		tl(9, "<div>");
		t(10, "<", composantsWebPrefixe, "input");
		s(" id=\"q", classeNomSimple, "_", i18nPage.getString(I18n.var_debut), "\"");
		s(" placeholder=\"{{ displayName }}\"");
		s(" class=\"\"");
		s(" data-var=\"start\"");
		s(" autocomplete=\"off=\"");
		s(" value=\"{{ start }}\"");
		l("></", composantsWebPrefixe, "input>");
		tl(10, "<div id=\"q", classeNomSimple, "Div_", i18nPage.getString(I18n.var_debut), "\" class=\"pageSearchVal \">start={{ start }}</div>");
		tl(9, "</div>");
		tl(8, "</div>");

		//////////
		// rows //
		//////////

		tl(8, "<div>");
		tl(9, "<div>");
		t(10, "<label for=\"q", classeNomSimple, "_", i18nPage.getString(I18n.var_Lignes), "\">");
		s("", i18nPage.getString(I18n.var_lignes), "");
		s("<sup> (Long)</sup>");
		l("</label>");
		tl(9, "</div>");
		tl(8, "</div>");
		tl(8, "<div>");
		tl(9, "<div>");
		t(10, "<", composantsWebPrefixe, "input");
		s(" id=\"q", classeNomSimple, "_", i18nPage.getString(I18n.var_lignes), "\"");
		s(" placeholder=\"{{ displayName }}\"");
		s(" class=\"\"");
		s(" data-var=\"rows\"");
		s(" autocomplete=\"off\"");
		s(" value=\"{{ rows }}\"");
		l("></", composantsWebPrefixe, "input>");
		tl(10, "<div id=\"q", classeNomSimple, "Div_", i18nPage.getString(I18n.var_lignes), "\" class=\"pageSearchVal \">rows={{ rows }}</div>");
		tl(9, "</div>");
		tl(8, "</div>");

		tl(7, "</div>");
		tl(7, "{%- endblock htmBody", i18nPage.getString(I18n.var_Recherche), classePageNomSimple, " %}");
		tl(6, "</div>");
		tl(5, "</", composantsWebPrefixe, "drawer>");

		////////////////
		// sidebar fq //
		////////////////

		tl(5, "<", composantsWebPrefixe, "drawer placement=\"end\" id=\"site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_Filtres), "\">");
		tl(6, "<div>");
		tl(7, "<span>");
		t(7, "<i class=\"fad fa-filters \"></i>");
		l(" ", i18nPage.getString(I18n.var_Filtres), "</span>");
		tl(6, "</div>");
		tl(6, "<div>");

		////////////////////
		// htmBodyFiltres //
		////////////////////

		tl(7, "{%- block htmBody", i18nPage.getString(I18n.var_Filtres), classePageNomSimple, " %}");
		tl(7, "<div>");
		tl(8, "{% for key, value in varsFq.items() %}");
		tl(9, "<div class=\"\">");
		t(10, "<label for=\"fq", classeNomSimple, "_{{ key }}\">");
		s("{{ value.", i18nPage.getString(I18n.var_nomAffichage), " }}");
		s("<sup> ({{ value.", i18nPage.getString(I18n.var_classeNomSimple), " }})</sup>");
		l("</label>");

		tl(10, "<div class=\"display-flex \">");
		t(11, "<", composantsWebPrefixe, "button");
		s(" id=\"buttonFacet", classeNomSimple, "_{{ key }}\"");
		s(" title=\"", i18nPage.getString(I18n.str_voir_valeurs), " ", "\"");
		s(" data-var=\"{{ value.var }}\"");
		s(" data-clear=\"{% if value.facetField is defined %}true{% else %}false{% endif %}\"");
		l("><i class=\"fas fa-list \"></i></", composantsWebPrefixe, "button>");

		t(11, "<", composantsWebPrefixe, "input");
		s(" id=\"fq", classeNomSimple, "_{{ key }}\"");
		s(" placeholder=\"{{ value.displayName }}\"");
		s(" class=\"\"");

		s(" onchange=\"fqChange('", classeNomSimple, "', value); \"");

		s(" data-var=\"{{ value.var }}\"");
		s(" autocomplete=\"off=\"");
		s(" value=\"{{ value.val }}\"");
		l("></", composantsWebPrefixe, "input>");
		tl(10, "</div>");

		tl(9, "</div>");
		t(9, "<div");
		s("");
		l(">");
		t(10, "<div");
		s(" class=\"pageSearchVal \"");
		s(" id=\"pageSearchVal-fq", classeNomSimple, "_{{ key }}\"");
		l(">{% if value.val is defined %}fq={{ value.var }}:{{ value.val | urlencode() }}{% endif %}</div>");
		t(10, "<div");
		s(" class=\"pageSearchVal \"");
		s(" id=\"pageSearchVal-buttonFacet", classeNomSimple, "_{{ key }}\"");
		l(">{% if value.facetField.var is defined %}facet.field={{ value.facetField.var }}{% endif %}</div>");

		t(10, "<div");
		s(" class=\"pageFacetField pageFacetField", classeNomSimple, "_{{ key }} \"");
		s(" id=\"pageFacetField", classeNomSimple, "_{{ key }}\"");
		l(">");
		tl(11, "{% for facetFieldKey, facetFieldValue in value.facetField.counts.items() %}");
		tl(11, "<", composantsWebPrefixe, "tooltip content=\"", String.format(i18nPage.getString(I18n.str_au_total_avec_), "{{ facetFieldValue | e }}", classeNomSingulier, "{{ value.facetField.var | e }}", "{{ facetFieldKey | e }}"), "\">");
		t(12, "<div");
		s(" class=\"cursor-pointer \"");
		s(" data-class=\"", classeNomSimple, "\"");
		s(" data-var=\"{{ value.facetField.var }}\"");
		s(" data-val=\"{{ facetFieldKey }}\"");
		s(" onclick=\"fqReplace('", classeNomSimple, "', this); \"");
		s(">");
		s("{{ facetFieldValue }}");
		s(": ");
		s("{{ facetFieldKey }}");
		l("</div>");
		tl(11, "</", composantsWebPrefixe, "tooltip>");
		tl(11, "{% endfor %}");
		tl(10, "</div>");

		tl(9, "</div>");
		tl(8, "{% endfor %}");
		tl(7, "</div>");
		
		tl(7, "{%- endblock htmBody", i18nPage.getString(I18n.var_Filtres), classePageNomSimple, " %}");
		tl(6, "</div>");
		tl(5, "</", composantsWebPrefixe, "drawer>");

		///////////////////
		// sidebar gamme //
		///////////////////

		tl(5, "<", composantsWebPrefixe, "drawer placement=\"end\" id=\"site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_Gamme), "\">");
		tl(6, "<div>");
		tl(7, "<span>");
		t(7, "<i class=\"fad fa-calendar-range \"></i>");
		l(" ", i18nPage.getString(I18n.var_Gamme), "</span>");
		tl(6, "</div>");
		tl(6, "<div>");

		//////////////////
		// htmBodyGamme //
		//////////////////

		tl(7, "{%- block htmBody", i18nPage.getString(I18n.var_Gamme), classePageNomSimple, " %}");

		tl(7, "<table>");
		tl(8, "<tr>");
		t(9, "<td");
		s(" colspan=\"2\"");
		l(">");
		t(10, "<div");
		s(" class=\"pageSearchVal \"");
		s(" id=\"pageSearchVal-pageFacetRangeGap-", classeNomSimple, "\"");
		s(">{% if rangeGap is defined %}facet.range.gap={{ rangeGap | urlencode() }}{% endif %}");
		l("</div>");
		t(10, "<div");
		s(" class=\"pageSearchVal \"");
		s(" id=\"pageSearchVal-pageFacetRangeStart-", classeNomSimple, "\"");
		s(">{% if rangeStart is defined %}facet.range.start={{ rangeStart | urlencode() }}{% endif %}");
		l("</div>");
		t(10, "<div");
		s(" class=\"pageSearchVal \"");
		s(" id=\"pageSearchVal-pageFacetRangeEnd-", classeNomSimple, "\"");
		s(">{% if rangeEnd is defined %}facet.range.end={{ rangeEnd | urlencode() }}{% endif %}");
		l("</div>");
		t(10, "<div");
		s(" class=\"pageSearchVal \"");
		s(" id=\"pageSearchVal-pageFacetRangeVar-", classeNomSimple, "\"");
		s(">{% if defaultRangeVar is defined %}facet.range={!tag=r1}{{ defaultRangeVar | urlencode() }}{% endif %}");
		l("</div>");
		tl(9, "</td>");
		tl(8, "</tr>");
		tl(7, "</table>");

		tl(7, "<table>");
		tl(8, "<tr class=\"\">");
		tl(9, "<td class=\"\">");
		tl(10, "<span>Range Gap</span>");
		tl(9, "</td>");
		tl(9, "<td class=\"\">");
		t(10, "<", composantsWebPrefixe, "select");
		s(" name=\"facet.range.gap\"");
		s(" id=\"pageFacetRangeGap-", classeNomSimple, "\"");
		s(" onchange=\"facet", i18nPage.getString(I18n.var_Gamme), "GapChange('", classeNomSimple, "', this); \"");
		l(">");
		tl(11, "<", composantsWebPrefixe, "option value=\"+1YEAR\"{% if defaultRangeGap == '+1YEAR' %} selected=\"selected\"{% else %}{% endif %}>Year</", composantsWebPrefixe, "option>");
		tl(11, "<", composantsWebPrefixe, "option value=\"+1MONTH\"{% if defaultRangeGap == '+1MONTH' %} selected=\"selected\"{% else %}{% endif %}>Month</", composantsWebPrefixe, "option>");
		tl(11, "<", composantsWebPrefixe, "option value=\"+1WEEK\"{% if defaultRangeGap == '+1WEEK' %} selected=\"selected\"{% else %}{% endif %}>Week</", composantsWebPrefixe, "option>");
		tl(11, "<", composantsWebPrefixe, "option value=\"+1DAY\"{% if defaultRangeGap == '+1DAY' %} selected=\"selected\"{% else %}{% if defaultRangeGap is defined %}{% else %} selected=\"selected\"{% endif %}{% endif %}>Day</", composantsWebPrefixe, "option>");
		tl(11, "<", composantsWebPrefixe, "option value=\"+1HOUR\"{% if defaultRangeGap == '+1HOUR' %} selected=\"selected\"{% else %}{% endif %}>Hour</", composantsWebPrefixe, "option>");
		tl(11, "<", composantsWebPrefixe, "option value=\"+1MINUTE\"{% if defaultRangeGap == '+1MINUTE' %} selected=\"selected\"{% else %}{% endif %}>Minute</", composantsWebPrefixe, "option>");
		tl(11, "<", composantsWebPrefixe, "option value=\"+1SECOND\"{% if defaultRangeGap == '+1SECOND' %} selected=\"selected\"{% else %}{% endif %}>Second</", composantsWebPrefixe, "option>");
		tl(10, "</", composantsWebPrefixe, "select>");
		tl(9, "</td>");
		tl(8, "</tr>");

		tl(8, "<tr class=\"\">");
		tl(9, "<td class=\"\" colspan=\"2\">");
		tl(10, "<span>Range Start</span>");
		tl(9, "</td>");
		tl(8, "</tr>");
		tl(8, "<tr class=\"\">");
		tl(9, "<td class=\"\" colspan=\"2\">");
		t(10, "<span>");
		s("<", composantsWebPrefixe, "input type=\"datetime-local\"");
		s(" name=\"facetRangeStart\"");
		s(" id=\"pageFacetRangeStart-", classeNomSimple, "\"");
		s(" value=\"{{ formatZonedDateTime(defaultRangeStart, \"yyyy-MM-dd'T'HH:mm\", defaultLocaleId, defaultZoneId) }}\"");
		s(" onclick=\"facet", i18nPage.getString(I18n.var_Gamme), "StartChange('", classeNomSimple, "', this); \"");
		l("></", composantsWebPrefixe, "input></span>");
		tl(9, "</td>");
		tl(8, "</tr>");

		tl(8, "<tr class=\"\">");
		tl(9, "<td class=\"\" colspan=\"2\">");
		tl(10, "<span>Range End</span>");
		tl(9, "</td>");
		tl(8, "</tr>");
		tl(8, "<tr class=\"\">");
		tl(9, "<td class=\"\" colspan=\"2\">");
		t(10, "<span>");
		s("<", composantsWebPrefixe, "input type=\"datetime-local\"");
		s(" name=\"facetRangeEnd\"");
		s(" id=\"pageFacetRangeEnd-", classeNomSimple, "\"");
		s(" value=\"{{ formatZonedDateTime(defaultRangeEnd, \"yyyy-MM-dd'T'HH:mm\", defaultLocaleId, defaultZoneId) }}\"");
		s(" onclick=\"facet", i18nPage.getString(I18n.var_Gamme), "EndChange('", classeNomSimple, "', this); \"");
		l("></", composantsWebPrefixe, "input></span>");
		tl(9, "</td>");
		tl(8, "</tr>");
		tl(7, "</table>");

		t(7, "<", composantsWebPrefixe, "radio-group>");
		tl(8, "{% for key, value in vars", i18nPage.getString(I18n.var_Gamme), ".items() %}");
		t(8, "<", composantsWebPrefixe, "radio");
		s(" name=\"pageFacet", i18nPage.getString(I18n.var_Gamme), "\"");
		s(" class=\"pageFacet", i18nPage.getString(I18n.var_Gamme), " \"");
		s(" id=\"pageFacet", i18nPage.getString(I18n.var_Gamme), classeNomSimple, "_{{ key }}\"");
		s(" value=\"{{ value.var }}\"");
		s("{% if default", i18nPage.getString(I18n.var_Gamme), "Var == var %} checked=\"checked\"{% endif %}");
		s(" onclick=\"facet", i18nPage.getString(I18n.var_Gamme), "Change('", classeNomSimple, "', value); \"");
		l(">{{ value.displayName }}</", composantsWebPrefixe, "radio>");
		tl(8, "{% endfor %}");
		tl(7, "</", composantsWebPrefixe, "radio-group>");
		tl(7, "{%- endblock htmBody", i18nPage.getString(I18n.var_Gamme), classePageNomSimple, " %}");
		tl(6, "</div>");
		tl(5, "</", composantsWebPrefixe, "drawer>");

		///////////////////
		// sidebar pivot //
		///////////////////

		tl(5, "<", composantsWebPrefixe, "drawer placement=\"end\" id=\"site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_Pivot), "\">");
		tl(6, "<div>");
		tl(7, "<span>");
		t(7, "<i class=\"fad fa-table-pivot \"></i>");
		l(" ", i18nPage.getString(I18n.var_Pivot), "</span>");
		tl(6, "</div>");
		tl(6, "<div>");

		//////////////////
		// htmBodyPivot //
		//////////////////

		tl(7, "{%- block htmBody", i18nPage.getString(I18n.var_Pivot), classePageNomSimple, " %}");

		t(7, "<div");
		s(" style=\"display: none; \"");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_Pivot), classeNomSimple, "Hidden\"");
		l(">");
		tl(8, "{% for item in default", i18nPage.getString(I18n.var_Pivot), "Vars %}");
		t(9, "<div");
		s(" class=\"pageSearchVal-", i18nPage.getString(I18n.var_Pivot), classeNomSimple, "Hidden \"");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_Pivot), classeNomSimple, "Hidden_{{ item }}\"");
		l(">{{ item }}</div>");
		tl(8, "{% endfor %}");
		tl(7, "</div>");

		t(7, "<div");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_Pivot), classeNomSimple, "\"");
		l(">");
		tl(8, "{% if default", i18nPage.getString(I18n.var_Pivot), "Vars is defined and default", i18nPage.getString(I18n.var_Pivot), "Vars.length > 0 %}");
		t(9, "<div");
		s(" class=\"pageSearchVal pageSearchVal-", i18nPage.getString(I18n.var_Pivot), classeNomSimple, " \"");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_Pivot), classeNomSimple, "_1\"");
		s(">facet.pivot={!range=r1}");
		s("{% for item in default", i18nPage.getString(I18n.var_Pivot), "Vars %}");
		s("{% if loop.index > 0 %},{% endif %}{{ item }}");
		s("{% endfor %}");
		l("</div>");
		tl(8, "{% endif %}");
		tl(7, "</div>");

		tl(7, "{% for key, value in varsFq.items() %}");
		tl(7, "<div>");
		t(8, "<", composantsWebPrefixe, "checkbox");
		s(" name=\"pageFacetPivot\"");
		s(" class=\"pageFacetPivot \"");
		s(" id=\"pageFacetPivot", classeNomSimple, "_{{ key }}\"");
		s(" value=\"{{ value.var }}\"");
		s("{% if ", i18nPage.getString(I18n.var_pivot), " is defined %} checked=\"checked\"{% endif %}");
		l(">{{ value.", i18nPage.getString(I18n.var_nomAffichage), " }}</", composantsWebPrefixe, "checkbox>");
		tl(7, "</div>");
		tl(7, "{% endfor %}");
		
		tl(7, "{%- endblock htmBody", i18nPage.getString(I18n.var_Pivot), classePageNomSimple, " %}");
		tl(6, "</div>");
		tl(5, "</", composantsWebPrefixe, "drawer>");

		//////////////////////////
		// sidebar liste champs //
		//////////////////////////

		tl(5, "<", composantsWebPrefixe, "drawer placement=\"end\" id=\"site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_ListeChamps), "\">");
		tl(6, "<div>");
		tl(7, "<span>");
		t(7, "<i class=\"fad fa-list-ul \"></i>");
		l(" ", i18nPage.getString(I18n.str_Liste_Champs), "</span>");
		tl(6, "</div>");
		tl(6, "<div>");

		////////////////////////
		// htmBodyListeChamps //
		////////////////////////

		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_ListeChamps), classePageNomSimple, " %}");

		tl(7, "<div");
		s(" style=\"display: none; \"");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_ListeChamps), classeNomSimple, "Hidden\"");
		l(">");
		tl(8, "{% for item in default", i18nPage.getString(I18n.var_ListeChamps), "Vars %}");
		t(9, "<div");
		s(" class=\"pageSearchVal-", i18nPage.getString(I18n.var_ListeChamps), classeNomSimple, "Hidden \"");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_ListeChamps), classeNomSimple, "Hidden_{{ item }}\"");
		l(">{{ item }}</div>");
		tl(8, "{% endfor %}");
		tl(7, "</div>");

		tl(7, "{% if default", i18nPage.getString(I18n.var_ListeChamps), "Vars is defined and default", i18nPage.getString(I18n.var_ListeChamps), "Vars.length > 0 %}");
		t(7, "<div");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_ListeChamps), classeNomSimple, "\"");
		l(">");
		tl(8, "{% if default", i18nPage.getString(I18n.var_ListeChamps), "Vars is defined %}");
		t(9, "<div");
		s(" class=\"pageSearchVal pageSearchVal-", i18nPage.getString(I18n.var_ListeChamps), classeNomSimple, " \"");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_ListeChamps), classeNomSimple, "_1\"");
		s(">fl=");
		s("{% for item in default", i18nPage.getString(I18n.var_ListeChamps), "Vars %}");
		s("{% if loop.index > 0 %},{% endif %}{{ item }}");
		s("{% endfor %}");
		l("</div>");
		tl(8, "{% endif %}");
		tl(7, "</div>");
		tl(7, "{% endif %}");

		tl(7, "{% for key, value in varsFq.items() %}");
		tl(7, "<div>");
		t(8, "<", composantsWebPrefixe, "checkbox");
		s(" name=\"page", i18nPage.getString(I18n.var_ListeChamps), "\"");
		s(" class=\"page", i18nPage.getString(I18n.var_ListeChamps), " \"");
		s(" id=\"page", i18nPage.getString(I18n.var_ListeChamps), classeNomSimple, "_{{ key }}\"");
		s(" value=\"{{ value.var }}\"");
		s("{% if ", i18nPage.getString(I18n.var_listeChamps), " is defined %} checked=\"checked\"{% endif %}");
		s(" onclick=\"facet", i18nPage.getString(I18n.var_ListeChamps), "Change('", classeNomSimple, "', value); \"");
		l(">{{ value.", i18nPage.getString(I18n.var_nomAffichage), " }}</", composantsWebPrefixe, "checkbox>");
		tl(7, "</div>");
		tl(8, "{% endfor %}");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_ListeChamps), classePageNomSimple, " %}");
		tl(6, "</div>");
		tl(5, "</", composantsWebPrefixe, "drawer>");

		///////////////////
		// sidebar stats //
		///////////////////

		tl(5, "<", composantsWebPrefixe, "drawer placement=\"end\" id=\"site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_Stats), "\">");
		tl(6, "<div>");
		tl(7, "<span>");
		t(8, "<i class=\"fad fa-chart-candlestick \"></i>");
		tl(8, " ", i18nPage.getString(I18n.str_Stats), "</span>");
		tl(6, "</div>");
		tl(6, "<div>");

		//////////////////
		// htmBodyStats //
		//////////////////

		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Stats), classePageNomSimple, " %}");

		tl(7, "<div");
		s(" style=\"display: none; \"");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_Stats), classeNomSimple, "Hidden\"");
		l(">");
		tl(8, "{% for item in default", i18nPage.getString(I18n.var_Stats), "Vars %}");
		t(9, "<div");
		s(" class=\"pageSearchVal-", i18nPage.getString(I18n.var_Stats), classeNomSimple, "Hidden \"");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_Stats), classeNomSimple, "Hidden_{{ item }}\"");
		l(">{{ item }}</div>");
		tl(8, "{% endfor %}");
		tl(7, "</div>");

		t(7, "<div");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_Stats), classeNomSimple, "\"");
		l(">");
		tl(8, "{% if default", i18nPage.getString(I18n.var_Stats), "Vars is defined %}");
		tl(9, "{% for item in default", i18nPage.getString(I18n.var_Stats), "Vars %}");
		t(10, "<div");
		s(" class=\"pageSearchVal pageSearchVal-", i18nPage.getString(I18n.var_Stats), classeNomSimple, "_{{ item }} \"");
		s(" id=\"pageSearchVal-", i18nPage.getString(I18n.var_Stats), classeNomSimple, "_{{ item }}\"");
		s(">");
		s("stats.field={{ item }}");
		tl(10, "</div>");
		tl(9, "{% endfor %}");
		tl(8, "{% endif %}");
		tl(7, "</div>");

		tl(7, "<table>");
		tl(8, "{% for key, value in varsFq.items() %}");
		tl(9, "{% if ", i18nPage.getString(I18n.var_activer), i18nPage.getString(I18n.var_Stats), " is defined %}");
		tl(10, "<tr class=\"\">");
		tl(11, "<td class=\"\">");
		t(12, "<span>");
		s("<", composantsWebPrefixe, "checkbox");
		s(" name=\"page", i18nPage.getString(I18n.var_Stats), "\"");
		s(" class=\"page", i18nPage.getString(I18n.var_Stats), " \"");
		s(" id=\"page", i18nPage.getString(I18n.var_Stats), classeNomSimple, "_{{ key }}\"");
		s(" value=\"{{ value.var }}\"");
		s("{% if value.", i18nPage.getString(I18n.var_stats), " is defined %} checked=\"checked\"{% endif %}");
		s(" onclick=\"facet", i18nPage.getString(I18n.var_Stats), "Change('", classeNomSimple, "', value); \"");
		l(">{{ value.", i18nPage.getString(I18n.var_nomAffichage), " }}</", composantsWebPrefixe, "checkbox></span>");
		tl(11, "</td>");
		tl(11, "<td>");
		tl(12, "<div>");
		tl(13, "<label for=\"page", i18nPage.getString(I18n.var_Stats), classeNomSimple, "_{{ key }}\">{{ value.", i18nPage.getString(I18n.var_nomAffichage), " }}</label>");
		tl(12, "</div>");

		t(12, "<div");
		s(" class=\"pageStatsField pageStatsField", classeNomSimple, "_{{ key }} \"");
		s(" id=\"pageStatsField", classeNomSimple, "_{{ key }}\"");
		l(">");
		tl(13, "{% if value.stats is defined %}");
		tl(14, "{% for item in value.stats %}");
		tl(15, "{% if item.key == 'name' %}{% else %}");
		t(16, "<div");
		s(" data-class=\"", classeNomSimple, "\"");
		s(" data-var=\"{{ item.var }}\"");
		s(" data-val=\"{{ item.key }}\"");
		s(">");
		s("{{ item.key }}");
		s(": ");
		s("{{ item }}");
		l("</div>");
		tl(15, "{% endif %}");
		tl(14, "{% endfor %}");
		tl(14, "{% if value.stats.max is defined %}");
		tl(15, "<div>");
		tl(16, "<span> step </span>");
		tl(16, "<", composantsWebPrefixe, "input id=\"animate", i18nPage.getString(I18n.var_Stats), "Step\" placeholder=\"step\" value=\"1\" style=\"width: 4em; \"></", composantsWebPrefixe, "input>");
		tl(16, "<span> min </span>");
		tl(16, "<", composantsWebPrefixe, "input id=\"animate", i18nPage.getString(I18n.var_Stats), "Min\" placeholder=\"min\" value=\"{{ value.stats.min }}\" style=\"width: 4em; \"></", composantsWebPrefixe, "input>");
		tl(16, "<span> max </span>");
		tl(16, "<", composantsWebPrefixe, "input id=\"animate", i18nPage.getString(I18n.var_Stats), "Max\" placeholder=\"max\" value=\"{{ value.stats.max }}\" style=\"width: 4em; \"></", composantsWebPrefixe, "input>");
		tl(16, "<span> speed in seconds </span>");
		tl(16, "<", composantsWebPrefixe, "input id=\"animate", i18nPage.getString(I18n.var_Stats), "Speed\" placeholder=\"speed\" value=\"1\" style=\"width: 4em; \"></", composantsWebPrefixe, "input>");
		tl(16, "<", composantsWebPrefixe, "button onclick=\"animate", i18nPage.getString(I18n.var_Stats), "(); \">animate</", composantsWebPrefixe, "button>");
		tl(15, "</div>");
		tl(14, "{% endif %}");
		tl(13, "{% endif %}");
		tl(12, "</div>");

		tl(11, "</td>");
		tl(10, "</tr>");
		tl(9, "{% endif %}");
		tl(8, "{% endfor %}");
		tl(7, "</table>");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Stats), classePageNomSimple, " %}");
		tl(6, "</div>");
		tl(5, "</", composantsWebPrefixe, "drawer>");

		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_BarreLaterale), "", classePageNomSimple, " %}");
		tl(4, "</div>");

		/////////////////////////////////
		// htmBodyGraphiqueEmplacement //
		/////////////////////////////////

		if(classeVarEmplacement != null) {
			l();
			tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Graphique), i18nPage.getString(I18n.var_Emplacement), classePageNomSimple, " %}");
			tl(4, "<div id=\"htmBody", i18nPage.getString(I18n.var_Graphique), i18nPage.getString(I18n.var_Emplacement), classePageNomSimple, "\" class=\"htmBody", i18nPage.getString(I18n.var_Graphique), i18nPage.getString(I18n.var_Emplacement), " \"></div>");
			tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Graphique), i18nPage.getString(I18n.var_Emplacement), classePageNomSimple, " %}");
		}

		//////////////////////
		// htmBodyGraphique //
		//////////////////////

		l();
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Graphique), classePageNomSimple, " %}");
		tl(4, "<div id=\"htmBody", i18nPage.getString(I18n.var_Graphique), classePageNomSimple, "\" class=\"htmBody", i18nPage.getString(I18n.var_Graphique), " \"></div>");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Graphique), classePageNomSimple, " %}");

		tl(4, "<div class=\"pageContent \">");
		tl(5, "<div id=\"site-calendar-box\">");
		tl(6, "<div id=\"site-calendar\"><!-- // --></div>");
		tl(5, "</div>");

		o = oAncien;
	}

	public void ecrirePageRechercheAucun(String langueNom, JsonObject i18nPage) throws Exception {

			///////////////////
			// htmBodyCount0 //
			///////////////////

			tl(4, "<div>");
			tl(5, "<", composantsWebPrefixe, "tooltip content=\"", i18nPage.getString(I18n.str_retourner_a_), classeTousNom, "\">");
			tl(6, "<", composantsWebPrefixe, "button href=\"{{ SITE_BASE_URL }}{{ pageUri }}\">");
			tl(7, "<i class=\"fa-solid fa-angle-left\"></i>");
			tl(7, classeTousNom);
			tl(6, "</", composantsWebPrefixe, "button>");
			tl(5, "</", composantsWebPrefixe, "tooltip>");
			tl(4, "</div>");
			tl(4, "<h1>");
			tl(5, classeIcone);
			tl(6, "<span>", classeNomAdjectifPluriel, "</span>");
			tl(4, "</h1>");

			tl(4, "{{ htm", i18nPage.getString(I18n.var_BoutonsRecherche), classePageNomSimple, "() }}");
			tl(6, "{{ htm", i18nPage.getString(I18n.var_Formulaires), classePageNomSimple, "() }}");
			tl(4, "<h2>");
			tl(5, "<span>");
			tl(1, "{% if ", i18nPage.getString(I18n.var_classeIconeClassesCss), " is defined %}");
			tl(6, "<i class=\"{{ ", i18nPage.getString(I18n.var_classeIconeClassesCss), " }}  site-menu-icon \"></i>");
			tl(1, "{% endif %}");
			tl(6, "<span class=\"\">", classeAucunNomTrouve, "</span>");
			tl(5, "</span>");
			tl(4, "</h2>");
	}

	public void ecrirePageRechercheBoutons(String langueNom, JsonObject i18nPage) throws Exception {
			tl(4, "<div id=\"htm", i18nPage.getString(I18n.var_BoutonsRecherche), "\" class=\"round-first-and-last-column-pill \">");

			//////////////
			// bouton q //
			//////////////
			// s("<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Recherche_avancee_pour_), classeNomAdjectifPluriel, "\">");
			s("<", composantsWebPrefixe, "button onclick=\"document.querySelector('#site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_Recherche), "').show(); \">");
			s("<i slot=\"prefix\" class=\"fad fa-magnifying-glass hover-box-shadow \"></i> ");
			s(i18nPage.getString(I18n.var_Rechercher));
			s("</", composantsWebPrefixe, "button>");
			// s("</", composantsWebPrefixe, "tooltip>");

			///////////////
			// bouton fq //
			///////////////
			// s("<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Filtres_et_nombres_de_facettes_pour_), classeNomAdjectifPluriel, "\">");
			s("<", composantsWebPrefixe, "button onclick=\"document.querySelector('#site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_Filtres), "').show(); \">");
			s("<i slot=\"prefix\" class=\"fad fa-filters hover-box-shadow \"></i> ");
			s(i18nPage.getString(I18n.var_Filtres));
			s("</", composantsWebPrefixe, "button>");
			// s("</", composantsWebPrefixe, "tooltip>");

			//////////////////
			// bouton gamme //
			//////////////////
			// s("<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Recherche_avancee_pour_), classeNomAdjectifPluriel, "\">");
			s("<", composantsWebPrefixe, "button onclick=\"document.querySelector('#site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_Gamme), "').show(); \">");
			s("<i slot=\"prefix\" class=\"fad fa-calendar-range hover-box-shadow \"></i> ");
			s(i18nPage.getString(I18n.var_Gamme));
			s("</", composantsWebPrefixe, "button>");
			// s("</", composantsWebPrefixe, "tooltip>");

			//////////////////
			// bouton pivot //
			//////////////////
			// s("<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Recherche_avancee_pour_), classeNomAdjectifPluriel, "\">");
			s("<", composantsWebPrefixe, "button onclick=\"document.querySelector('#site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_Pivot), "').show(); \">");
			s("<i slot=\"prefix\" class=\"fad fa-table-pivot hover-box-shadow \"></i> ");
			s(i18nPage.getString(I18n.var_Pivot));
			s("</", composantsWebPrefixe, "button>");
			// s("</", composantsWebPrefixe, "tooltip>");

			/////////////////////////
			// bouton liste champs //
			/////////////////////////
			// s("<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Recherche_avancee_pour_), classeNomAdjectifPluriel, "\">");
			s("<", composantsWebPrefixe, "button onclick=\"document.querySelector('#site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_ListeChamps), "').show(); \">");
			s("<i slot=\"prefix\" class=\"fad fa-list-ul hover-box-shadow \"></i> ");
			s(i18nPage.getString(I18n.var_ListeChamps));
			s("</", composantsWebPrefixe, "button>");
			// s("</", composantsWebPrefixe, "tooltip>");

			//////////////////
			// bouton stats //
			//////////////////
			// s("<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Recherche_avancee_pour_), classeNomAdjectifPluriel, "\">");
			s("<", composantsWebPrefixe, "button onclick=\"document.querySelector('#site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_Stats), "').show(); \">");
			s("<i slot=\"prefix\" class=\"fad fa-chart-candlestick hover-box-shadow \"></i> ");
			s(i18nPage.getString(I18n.var_Stats));
			s("</", composantsWebPrefixe, "button>");
			// s("</", composantsWebPrefixe, "tooltip>");

			////////////////
			// bouton API //
			////////////////
			// s("<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Interroger_lAPI_REST_JSON_pour_), classeNomAdjectifPluriel, "\">");
			s("<", composantsWebPrefixe, "button onclick=\"document.querySelector('#site", i18nPage.getString(I18n.var_BarreLaterale), "Toggle", i18nPage.getString(I18n.var_Api), "').show(); \"");
			s(" href=\"{{ apiUri }}{% if pk is defined %}/{{ pk }}{% else %}{% if id is defined %}/{{ id }}{% else %}{{ queryStr }}{% endif %}{% endif %}\"");
			s(">");
			s("<i slot=\"prefix\" class=\"fad fa-brackets-curly hover-box-shadow \"></i> ");
			s(i18nPage.getString(I18n.var_Api));
			s("</", composantsWebPrefixe, "button>");
			// s("</", composantsWebPrefixe, "tooltip>");

			tl(4, "</div>");
			l();
			tl(6, "<div id=\"htm", i18nPage.getString(I18n.var_BoutonsPagination), "\">");

			// tl(7, "<", composantsWebPrefixe, "button-group>");
			// tl(8, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", String.format(i18nPage.getString(I18n.str_Afficher_les_resultats_sous_forme_de_details), classeNomAdjectifPluriel), "\" pill>");
			// tl(9, "<", composantsWebPrefixe, "button pill id=\"grid-toggle-details\">");
			// tl(10, "<i class=\"fa-solid fa-list\"></i>");
			// tl(9, "</", composantsWebPrefixe, "button>");
			// tl(8, "</", composantsWebPrefixe, "tooltip>");
			// tl(8, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", String.format(i18nPage.getString(I18n.str_Afficher_les_resultats_sous_forme_de_liste), classeNomAdjectifPluriel), "\">");
			// tl(9, "<", composantsWebPrefixe, "button id=\"grid-toggle-list\">");
			// tl(10, "<i class=\"fa-solid fa-bars\"></i>");
			// tl(9, "</", composantsWebPrefixe, "button>");
			// tl(8, "</", composantsWebPrefixe, "tooltip>");
			// tl(8, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", String.format(i18nPage.getString(I18n.str_Afficher_les_resultats_sous_forme_de_cartes), classeNomAdjectifPluriel), "\" pill>");
			// tl(9, "<", composantsWebPrefixe, "button pill id=\"grid-toggle-card\">");
			// tl(10, "<i class=\"fa-solid fa-grid\"></i>");
			// tl(9, "</", composantsWebPrefixe, "button>");
			// tl(8, "</", composantsWebPrefixe, "tooltip>");
			// tl(7, "</", composantsWebPrefixe, "button-group>");

			tl(7, "<", composantsWebPrefixe, "button-group>");
			tl(8, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", String.format(i18nPage.getString(I18n.str_Afficher_les_resultats_precedents), classeNomAdjectifPluriel), "\">");
			t(9, "<", composantsWebPrefixe, "button pill");
			s("{% if pagination.page", i18nPage.getString(I18n.var_Precedent), " is defined %}");
			s(" href=\"{{pageUri}}?start={{pagination.page", i18nPage.getString(I18n.var_Precedent), ".", i18nPage.getString(I18n.var_debut), "}}&amp;rows={{pagination.", i18nPage.getString(I18n.var_lignes), "}}\"");
			s("{% else %}");
			s(" disabled");
			s("{% endif %}");
			l(">");
			tl(10, "<i slot=\"prefix\" class=\"fas fa-arrow-square-left \"></i>");
			tl(9, "</", composantsWebPrefixe, "button>");
			tl(8, "</", composantsWebPrefixe, "tooltip>");

			tl(8, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", String.format(i18nPage.getString(I18n.str_Afficher_la_moitie_des_resultats), classeNomAdjectifPluriel), "\">");
			t(9, "<", composantsWebPrefixe, "button");
			s("{% if pagination.", i18nPage.getString(I18n.var_lignes), i18nPage.getString(I18n.var_Precedent), " >= pagination['1L'] %}");
			s(" href=\"{{pageUri}}?start={{pagination.", i18nPage.getString(I18n.var_debut), "}}&amp;rows={{ pagination.", i18nPage.getString(I18n.var_lignes), i18nPage.getString(I18n.var_Precedent), " }}\"");
			s("{% else %}");
			s(" disabled");
			s("{% endif %}");
			l(">");
			tl(10, "<i slot=\"prefix\" class=\"fas fa-minus-square \"></i>");
			tl(9, "</", composantsWebPrefixe, "button>");
			tl(8, "</", composantsWebPrefixe, "tooltip>");

			tl(8, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", String.format(i18nPage.getString(I18n.str_Afficher_le_double_des_resultats), classeNomAdjectifPluriel), "\">");
			t(9, "<", composantsWebPrefixe, "button");
			s(" href=\"{{pageUri}}?start={{pagination.", i18nPage.getString(I18n.var_debut), "}}&amp;rows={{ pagination.", i18nPage.getString(I18n.var_lignes), i18nPage.getString(I18n.var_Prochaine), " }}\"");
			l(">");
			tl(10, "<i slot=\"prefix\" class=\"fas fa-plus-square \"></i>");
			tl(9, "</", composantsWebPrefixe, "button>");
			tl(8, "</", composantsWebPrefixe, "tooltip>");

			tl(8, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", String.format(i18nPage.getString(I18n.str_Afficher_les_resultats_suivants), classeNomAdjectifPluriel), "\">");
			t(9, "<", composantsWebPrefixe, "button pill");
			s("{% if pagination.page", i18nPage.getString(I18n.var_Prochaine), " is defined %}");
			s(" href=\"{{pageUri}}?start={{pagination.page", i18nPage.getString(I18n.var_Prochaine), ".", i18nPage.getString(I18n.var_debut), "}}&amp;rows={{pagination.", i18nPage.getString(I18n.var_lignes), "}}\"");
			s("{% else %}");
			s(" disabled");
			s("{% endif %}");
			l(">");
			tl(10, "<i slot=\"prefix\" class=\"fas fa-arrow-square-right \"></i>");
			tl(9, "</", composantsWebPrefixe, "button>");
			tl(8, "</", composantsWebPrefixe, "tooltip>");
			tl(7, "</", composantsWebPrefixe, "button-group>");
			tl(6, "<div>{{ pagination.", i18nPage.getString(I18n.var_debut), "Num }} – {{ pagination.", i18nPage.getString(I18n.var_fin), "Num }} ", i18nPage.getString(I18n.var_de), " {{ pagination.", i18nPage.getString(I18n.var_numTrouve), " }}</div>");
			tl(6, "</div>");
	}

	public void ecrirePageRechercheJinja(String langueNom, JsonObject i18nPage) throws Exception {

		if(auteurPageRechercheJinja != null) {
			o = auteurPageRechercheJinja;
			l("{% extends \"", classeGenPageRechercheTemplate, "\" %}");
		}

		o = auteurGenPageRechercheJinja;
		if(!classePageSimple) {
			l("{% extends \"", classeGenPageRechercheSuperTemplate, "\" %}");
		}

		l();
		tl(0, "{%- block htmTitle", classePageSuperNomSimple, " %}");
		tl(0, "{%- block htmTitle", classePageNomSimple, " %}");
		t(2, "<title>");
		s("{% if ", uncapitalizeClasseApiClasseNomSimple, "Count == 0 %}");
		s(classeAucunNomTrouve);
		s("{% else %}");
		s(classeNomAdjectifPluriel);
		s("{% endif %}");
		l("</title>");
		tl(0, "{%- endblock htmTitle", classePageNomSimple, " %}");
		tl(0, "{%- endblock htmTitle", classePageSuperNomSimple, " %}");
		ecrirePageHeadJinja(langueNom, i18nPage);
		//STUFF0

		/////////////
		// htmBody //
		/////////////

		l();
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Debut), classePageSuperNomSimple, " %}");
		tl(0, "{{ super() }}");
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Debut), classePageNomSimple, " %}");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Debut), classePageNomSimple, " %}");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Debut), classePageSuperNomSimple, " %}");
		l();
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Milieu), classePageSuperNomSimple, " %}");
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Milieu), classePageNomSimple, " %}");

		ecrireBarreLaterale(langueNom, i18nPage);
		tl(0, "{%- include ", classeBarreLateraleTemplate, " %}");

		ecrirePageRechercheBoutons(langueNom, i18nPage);
		ecrirePageRechercheFormulaire(langueNom, i18nPage);
		ecrirePageRechercheSuggere(langueNom, i18nPage);

		// htmBodyCount0 //
		tl(0, "{% if ", uncapitalizeClasseApiClasseNomSimple, "Count == 0 %}");
		ecrirePageRechercheAucun(langueNom, i18nPage);
		tl(0, "{% else %}");

		/////////////////
		// htmBodyTous //
		/////////////////

		tl(6, "<h1>");
		tl(7, "<a href=\"{{pageUri}}\">");
		tl(8, classeIcone);
		tl(8, "<span>", classeNomAdjectifPluriel, "</span>");
		tl(7, "</a>");
		tl(6, "</h1>");

		tl(6, "<div id=\"site-calendar-box\">");
//		tl(7, "<h3 id=\"site-calendar-title\">Calendar</h3>");
		tl(7, "<div id=\"site-calendar\"><!-- // --></div>");
		tl(6, "</div>");

		// formulaires
		tl(6, "{{ htm", i18nPage.getString(I18n.var_BoutonsRecherche), classePageNomSimple, "() }}");
		tl(6, "{{ htm", i18nPage.getString(I18n.var_Formulaires), classePageNomSimple, "() }}");
		tl(6, "<", composantsWebPrefixe, "divider></", composantsWebPrefixe, "divider>");
		tl(6, "{{ htm", i18nPage.getString(I18n.var_BoutonsPagination), classePageNomSimple, "() }}");
		tl(6, "<div class=\"grid-mode-details border-radius-x-large\" id=\"site-results-grid\">");
//			TODO
//			tl(2, "Map<String, Map<String, List<String>>> highlighting = ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getResponse().getHighlighting();");
		tl(7, "{% for item in ", i18nPage.getString(I18n.var_liste), classeApiClasseNomSimple, "%}");
//			TODO
//			tl(3, classeApiClasseNomSimple, " o = ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getList().get(i);");
//			tl(3, "Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());");
//			tl(3, "List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));");
//			tl(3, "String uri = ", classeEntiteVars.contains("pageUri") ? "o.getPageUri()" : (q(classePageUriMethode, "/") + " + o.get" + StringUtils.capitalize(classeVarClePrimaire) + "()"), ";");
		tl(7, "<a href=\"{{ item.", classeVarUrlPk, " }}\">");
		s(wTd);
		tl(7, "</a>");
		tl(7, "{% endfor %}");
//			TODO
//			tl(3, "SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getResponse()).map(QueryResponse::getResponse).map(r -> r.get(\"facets\")).orElse(new SimpleOrderedMap());");
		s(wFoot);
		tl(6, "</div>");

		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Milieu), classePageNomSimple, " %}");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Milieu), classePageSuperNomSimple, " %}");
		l();
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Fin), classePageSuperNomSimple, " %}");
		tl(3, "{{ super() }}");
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Fin), classePageNomSimple, " %}");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Fin), classePageNomSimple, " %}");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Fin), classePageSuperNomSimple, " %}");

		s(auteurGenPageJinjaEntite);
	}

	public void ecrirePageEditionJinja(String langueNom, JsonObject i18nPage) throws Exception {

		if(auteurPageEditionJinja != null) {
			o = auteurPageEditionJinja;
			l("{% extends \"", classeGenPageEditionTemplate, "\" %}");
		}

		o = auteurGenPageEditionJinja;
		if(!classePageSimple) {
			l("{% extends \"", classeGenPageEditionSuperTemplate, "\" %}");
		}

		tl(0, "{%- block htmTitle", classePageSuperNomSimple, " %}");
		tl(0, "{%- block htmTitle", classePageNomSimple, " %}");
		t(2, "<title>");
		s(classeNomAdjectifSingulier);
		l("</title>");
		tl(0, "{%- endblock htmTitle", classePageNomSimple, " %}");
		tl(0, "{%- endblock htmTitle", classePageSuperNomSimple, " %}");
		ecrirePageHeadJinja(langueNom, i18nPage);
		//STUFF0

		/////////////
		// htmBody //
		/////////////

		l();
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Debut), classePageSuperNomSimple, " %}");
		tl(2, "{{ super() }}");
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Debut), classePageNomSimple, " %}");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Debut), classePageNomSimple, " %}");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Debut), classePageSuperNomSimple, " %}");
		l();
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Milieu), classePageSuperNomSimple, " %}");
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Milieu), classePageNomSimple, " %}");

		ecrireBarreLaterale(langueNom, i18nPage);
		ecrirePageRechercheBoutons(langueNom, i18nPage);
		ecrirePageRechercheFormulaire(langueNom, i18nPage);
		ecrirePageRechercheSuggere(langueNom, i18nPage);

		// htmBodyCount0 //
		tl(0, "{% if ", uncapitalizeClasseApiClasseNomSimple, "Count == 0 %}");
		ecrirePageRechercheAucun(langueNom, i18nPage);
		tl(0, "{% else %}");

		tl(1, "{% if ", uncapitalizeClasseApiClasseNomSimple, "Count == 1 %}");
		// htmBodyCount1 //
		tl(2, "{% if ", classeVarCleUnique, " is defined %}");

		///////////////////
		// htmBodyCount1 //
		///////////////////
	
		tl(4, "<div>");
		tl(5, "<", composantsWebPrefixe, "tooltip content=\"", i18nPage.getString(I18n.str_retourner_a_), classeTousNom, "\">");
		tl(6, "<", composantsWebPrefixe, "button href=\"{{ SITE_BASE_URL }}{{ pageUri }}\">");
		tl(7, "<i class=\"fa-solid fa-angle-left\"></i>");
		tl(7, classeTousNom);
		tl(6, "</", composantsWebPrefixe, "button>");
		tl(5, "</", composantsWebPrefixe, "tooltip>");
		tl(4, "</div>");
		tl(4, "<h1>");
		tl(5, classeIcone);
		tl(5, "{{", uncapitalizeClasseApiClasseNomSimple, "_.", i18nPage.getString(I18n.var_objetTitre), "}}");
		tl(4, "</h1>");
		tl(4, "{{ htm", i18nPage.getString(I18n.var_BoutonsRecherche), classePageNomSimple, "() }}");
		tl(6, "{{ htm", i18nPage.getString(I18n.var_Formulaires), classePageNomSimple, "() }}");

		tl(2, "{% else %}");
		ecrirePageRechercheAucun(langueNom, i18nPage);
		tl(2, "{% endif %}");
		tl(1, "{% else %}");
		ecrirePageRechercheAucun(langueNom, i18nPage);
		tl(1, "{% endif %}");
		tl(0, "{% endif %}");

		if(classeVarClePrimaire != null || !classeModele) {
			tl(0, "{%- block htm", i18nPage.getString(I18n.var_Formulaire), classePageNomSimple, " %}");
			tl(4, "<form action=\"", classeApiUri, "\" id=\"", classeApiClasseNomSimple, "Form\" class=\"", i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Formulaire), " \" style=\"\" onsubmit=\"event.preventDefault(); return false; \">");
			tl(0, "{% if ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " is defined %}");
			t(5, "<input");
			s(" name=\"", classeModele ? classeVarClePrimaire : classeVarCleUnique, "\"");
			s(" class=\"", i18nPage.getString(I18n.var_valeur), StringUtils.capitalize(classeModele ? classeVarClePrimaire : classeVarCleUnique), "\"");
			s(" type=\"hidden\"");
			s(" value=\"{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}\"");
			l("/>");
			tl(5, "{% endif %}");
			t(5, "<input");
			s(" name=\"focusId\"");
			s(" type=\"hidden\"");
			l("/>");
			t(5, "<input");
			s(" name=\"", i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Reponse), "\"");
			s(" id=\"", i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Reponse), "\"");
			s(" class=\"", i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Reponse), "\" ");
			s(" value='{{ toJsonObjectStringInApostrophes(", i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Reponse), ") }}'");
			s(" type=\"hidden\"");
			l("/>");
			t(5, "<input");
			s(" name=\"", StringUtils.uncapitalize(classeNomSimple), "\"");
			s(" id=\"", StringUtils.uncapitalize(classeNomSimple), "\"");
			s(" class=\"", StringUtils.uncapitalize(classeNomSimple), "\" ");
			s(" value='{{ toJsonObjectStringInApostrophes(", StringUtils.uncapitalize(classeNomSimple), "_) }}'");
			s(" type=\"hidden\"");
			l("/>");
			t(5, "<input");
			s(" name=\"", i18nPage.getString(I18n.var_liste), classeNomSimple, "\"");
			s(" id=\"", i18nPage.getString(I18n.var_liste), classeNomSimple, "\"");
			s(" class=\"", i18nPage.getString(I18n.var_liste), classeNomSimple, "\" ");
			s(" value='{{ toJsonArrayStringInApostrophes(", i18nPage.getString(I18n.var_liste), classeNomSimple, ") }}'");
			s(" type=\"hidden\"");
			l("/>");
			tl(4, "</form>");
			tl(1, "{% if ", classeVarCleUnique, " is defined %}");
			tl(4, "{{ htm", i18nPage.getString(I18n.var_Bouton), "_", StringUtils.lowerCase(i18nPage.getString(I18n.var_PageRecherche)), classeApiClasseNomSimple, "() }}");
			tl(4, "{{ htm", i18nPage.getString(I18n.var_Formulaire), "_", StringUtils.lowerCase(i18nPage.getString(I18n.var_PageRecherche)), classeApiClasseNomSimple, "() }}");
			tl(1, "{% endif %}");
			tl(0, "{%- endblock htm", i18nPage.getString(I18n.var_Formulaire), classePageNomSimple, " %}");
		}

		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Milieu), classePageNomSimple, " %}");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Milieu), classePageSuperNomSimple, " %}");
		l();
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Fin), classePageSuperNomSimple, " %}");
		tl(3, "{{ super() }}");
		tl(0, "{%- block htmBody", i18nPage.getString(I18n.var_Fin), classePageNomSimple, " %}");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Fin), classePageNomSimple, " %}");
		tl(0, "{%- endblock htmBody", i18nPage.getString(I18n.var_Fin), classePageSuperNomSimple, " %}");

		s(auteurGenPageJinjaEntite);
	}
}
