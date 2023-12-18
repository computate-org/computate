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

import org.apache.commons.configuration2.YAMLConfiguration;
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
	public Boolean ecrireFormEntite(String langueNom, YAMLConfiguration langueConfig, ToutEcrivain wForm, String classeApiMethodeMethode) {
		int tIndex = 0;
		Boolean resultat = false;

		if(entiteHtml) {
			if("POST".equals(classeApiMethodeMethode) && (
					entiteVar.equals(langueConfig.getString(ConfigCles.var_supprime))
					|| entiteVar.equals(langueConfig.getString(ConfigCles.var_archive))
					|| entiteVar.equals(langueConfig.getString(ConfigCles.var_cree))
					|| entiteAttribuer
					)) {
			} else {
				Boolean ecrireFin = false;
				Boolean nouveauLigne = false;

//				rechercheLigneActuel.put(classeApiMethodeMethode, ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmLigne_stored_int"), 0));
				Integer rechercheLigneActuel = Optional.ofNullable((Integer)entiteDocumentSolr.get("entiteHtmLigne_stored_int")).orElse(0);
				Integer rechercheLigne = Optional.ofNullable(rechercheLigneMap.get(classeApiMethodeMethode)).orElse(-1);
				if(rechercheLigne != null) {
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
						String entiteHtmLigneTitreActuel = null;
						if(ecrireFin) {
							if(entiteHtmLigneVerticaleAncien == null || !entiteHtmLigneVerticaleAncien) {
								wForm.tl(8, "<!-- entiteHtmLigneVerticaleAncien == null || !entiteHtmLigneVerticaleAncien -->");
								wForm.tl(7, "</div>");
							} else if(entiteHtmLigneVerticaleAncien != null && entiteHtmLigneVerticaleAncien) {
								wForm.tl(10, "<!-- entiteHtmLigneVerticaleAncien != null && entiteHtmLigneVerticaleAncien -->");
								wForm.tl(9, "</tbody>");
								wForm.tl(8, "</table>");
								wForm.tl(7, "</div>");
							}
						}
						if(BooleanUtils.isTrue(entiteHtmLigneVerticaleActuel)) {
							entiteHtmLigneVerticaleActuelMap.put(classeApiMethodeMethode, true);
							wForm.tl(7, "<!-- BooleanUtils.isTrue(entiteHtmLigneVerticale) -->");
							if(entiteHtmLigneTitre == null) {
								wForm.tl(7, "<div class=\"w3-padding ", langueConfig.getString(ConfigCles.var_HtmLigne), "\">");
							} else {
								wForm.t(7, "<div class=\"w3-padding ", langueConfig.getString(ConfigCles.var_HtmLigne), "\" id=\"").sx(genererId(entiteHtmLigneTitre)).l("\">");
							}
							wForm.tl(8, "<table class=\"w3-table-all \">");
							if(entiteHtmLigneTitre != null) {
								wForm.tl(9, "<thead>");
								wForm.tl(10, "<tr>");
								if("JsonArray".equals(entiteNomSimple)) {
									wForm.tl(10, "<th class=\"w3-", classeCouleur, " \">");
								} else {
									wForm.tl(10, "<th class=\"w3-", classeCouleur, " \" colspan=\"3\">");
								}
								wForm.t(11, "<span>");
								wForm.sx(entiteHtmLigneTitre);
								wForm.l("</span>");
								wForm.tl(10, "</th>");
								if("JsonArray".equals(entiteNomSimple)) {
									wForm.tl(10, "{{#each ", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}<th class=\"w3-", classeCouleur, " \">", entiteHtmLigneEnTeteExpression, "</th>{{/each}}");
								}
								wForm.tl(10, "</tr>");
								wForm.tl(9, "</thead>");
								entiteHtmLigneTitreActuel = entiteHtmLigneTitre;
								entiteHtmLigneTitreActuelMap.put(classeApiMethodeMethode, entiteHtmLigneTitreActuel);
							}
							wForm.tl(9, "<tbody>");
						} else {
							entiteHtmLigneVerticaleActuelMap.put(classeApiMethodeMethode, false);
							wForm.tl(7, "<!-- BooleanUtils.isFalse(entiteHtmLigneVerticale) -->");
							wForm.tl(7, "<div class=\"w3-cell-row ", langueConfig.getString(ConfigCles.var_HtmLigne), "\">");
						}
					}
				}

				if(classeUtilisateurEcrire && classeSessionEcrire) {
					wForm.l("{{#ifContainsKeysAnyRolesOrSessionId ", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), " ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolePourEcrire), " ", langueConfig.getString(ConfigCles.var_sessionId), " ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(ConfigCles.var_sessionId), "}}");
					wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"true\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"true\"}}");
					wForm.l("{{else}}");
					wForm.l("{{#ifContainsKeysAnyRolesOrSessionId ", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), " ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolePourLire), " ", langueConfig.getString(ConfigCles.var_sessionId), " ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(ConfigCles.var_sessionId), "}}");
					wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"false\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"true\"}}");
					wForm.l("{{else}}");
					wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"false\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"false\"}}");
					wForm.l("{{/ifContainsKeysAnyRolesOrSessionId}}");
					wForm.l("{{/ifContainsKeysAnyRolesOrSessionId}}");
				}
				else if(classePublicLire) {
					wForm.l("{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "}}");
					wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"true\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"true\"}}");
					wForm.l("{{else}}");
					wForm.l("{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
					wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"false\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"true\"}}");
					wForm.l("{{else}}");
					wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"false\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"false\"}}");
					wForm.l("{{/ifContainsAnyRoles}}");
					wForm.l("{{/ifContainsAnyRoles}}");
				}
				else if(classeUtilisateurEcrire) {
					if(classeRolesTrouves || classeRoleLiresTrouves) {
						wForm.l("{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "}}");
						wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"true\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"true\"}}");
						wForm.l("{{else}}");
						wForm.l("{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
						wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"false\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"true\"}}");
						wForm.l("{{else}}");
						wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"false\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"false\"}}");
						wForm.l("{{/ifContainsAnyRoles}}");
						wForm.l("{{/ifContainsAnyRoles}}");
					}
					else {
						wForm.l("{{#ifContainsKeys ", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s}}");
						wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"true\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"true\"}}");
						wForm.l("{{else}}");
						wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"false\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"false\"}}");
						wForm.l("{{/ifContainsKeys}}");
						wForm.l("{{/ifContainsKeys}}");
					}
				}
				else if(classeSessionEcrire) {
					wForm.l("{{#ifContainsSessionId sessionId}}");
					wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"true\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"true\"}}");
					wForm.l("{{else}}");
					wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"false\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"false\"}}");
					wForm.l("{{/ifContainsSessionId}}");
				}
				else if(classeRolesTrouves || classeRoleLiresTrouves) {
					wForm.l("{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "}}");
					wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"true\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"true\"}}");
					wForm.l("{{else}}");
					wForm.l("{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
					wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"false\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"true\"}}");
					wForm.l("{{else}}");
					wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool=\"false\" ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool=\"false\"}}");
					wForm.l("{{/ifContainsAnyRoles}}");
					wForm.l("{{/ifContainsAnyRoles}}");
				}
			}
		}
		return resultat;
	}

	/////////
	// htm //
	/////////

	public void genCodeEntiteHtm(String langueNom, YAMLConfiguration langueConfig, String classeApiMethodeMethode) throws Exception {
		ToutEcrivain oAncien = o;
		o = auteurGenPageHbsEntite;
		Boolean entiteHtmLigneVerticaleActuel = entiteHtmLigneVerticaleActuelMap.get(classeApiMethodeMethode);

		l();
		l("{{#*inline \"htm", entiteVarCapitalise, "\"}}");
		l("<!-- #*inline \"htm", entiteVarCapitalise, "\" -->");
		if(!entiteHtmLigneVerticaleActuel) {
			tl(8, "<div class=\"w3-cell w3-cell-top w3-center w3-mobile \">");
		}
		if(entiteHtml) {

			if(!entiteHtmLigneVerticaleActuel) {
				tl(9, "<div class=\"w3-padding \">");
				tl(10, "<div id=\"", langueConfig.getString(ConfigCles.var_suggere), "{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}", classeNomSimple, entiteVarCapitalise, "\">");
				tl(11, "<div class=\"w3-card \">");
			}

			if(entiteAttribuer) {
				if(entiteNomAffichage != null) {
					tl(12, "<div class=\"w3-cell-row \">");
					tl(13, "<a href=\"", entiteAttribuerPageUri, "?fq=", entiteAttribuerVar, ":{{", uncapitalizeClasseNomSimple, "_.", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}\" class=\"w3-cell w3-btn w3-center h4 w3-block h4 w3-", entiteAttribuerContexteCouleur, " w3-hover-", entiteAttribuerContexteCouleur, " \">");
					if(entiteAttribuerContexteIconeGroupe != null && entiteAttribuerContexteIconeNom != null)
						tl(14, "<i class=\"fa", StringUtils.substring(entiteAttribuerContexteIconeGroupe, 0, 1), " fa-", entiteAttribuerContexteIconeNom, " \"></i>");
					tl(14, entiteNomAffichage);
					tl(13, "</a>");
					tl(12, "</div>");
				}
				tl(12, "<div class=\"w3-cell-row \">");
				tl(13, "<h5 class=\"w3-cell \">");
				tl(14, "<i class=\"far fa-search w3-xxlarge w3-cell w3-cell-middle \"></i>");
				tl(14, langueConfig.getString(ConfigCles.var_relier), " ", entiteListeTypeJson == null ? entiteAttribuerContexteUnNom : entiteAttribuerContexteNomPluriel, " ", langueConfig.getString(ConfigCles.var_a), " ", classeCeNom);
				tl(13, "</h5>");
				tl(12, "</div>");
				tl(12, "<div class=\"w3-cell-row w3-padding \">");
				tl(13, "<div class=\"w3-cell \">");
				tl(14, "<div class=\"w3-cell-row \">");
				l();

				tl(14, "{{> \"input", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "=", langueConfig.getString(ConfigCles.var_rolePourEcrire), " ", langueConfig.getString(ConfigCles.var_rolePourLire), "=", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
				tl(14, "</div>");
				tl(13, "</div>");
				tl(12, "</div>");
				tl(12, "<div class=\"w3-cell-row w3-padding \">");
				tl(13, "<div class=\"w3-cell w3-left-align w3-cell-top \">");
				tl(14, "<ul class=\"w3-ul w3-hoverable \" id=\"list", classeNomSimple, entiteVarCapitalise, "_{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}\">");
				tl(14, "</ul>");


				tl(14, "{{#eq ", langueConfig.getString(ConfigCles.var_rolePourLire), "Bool \"true\"}}");

				tl(1, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(16, "<div class=\"w3-cell-row \">");
				tl(17, "<button");
				tl(18, " class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", entiteAttribuerContexteCouleur, " \"");
				tl(18, " id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_ajouter), "\"");

				if("array".equals(entiteAttribuerTypeJson))
					t(18, " onclick=\"$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = '", langueConfig.getString(ConfigCles.var_Envoi), "…'; post", entiteAttribuerNomSimple, "Vals({ ", entiteAttribuerVar, ": [ '{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' ] }");
				else
					t(18, " onclick=\"$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = '", langueConfig.getString(ConfigCles.var_Envoi), "…'; post", entiteAttribuerNomSimple, "Vals({ ", entiteAttribuerVar, ": '{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }");
				s(", function() { ");
				s("$('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_ajouter), "').disabled = false; ");
				s("$('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_ajouter), "').innerHTML = '", langueConfig.getString(ConfigCles.var_ajouter), " ", entiteAttribuerContexteUnNom, "';");
				s(" }");
				s(", function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}", entiteVar, "')); });");
				s("\"");
				l();

				tl(18, ">", langueConfig.getString(ConfigCles.var_ajouter), " ", entiteAttribuerContexteUnNom, "</button>");
				tl(16, "</div>");
				tl(1, "{{/eq}}");

				tl(14, "{{/eq}}");

				tl(13, "</div>");
				tl(12, "</div>");
			}
			else if("LocalDate".equals(entiteNomSimple)) {
				if(entiteHtmLigneVerticaleActuel) {
					tl(10, "<tr>");
					tl(12, "<td>");
					if(entiteNomAffichage != null) {
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
					}
					tl(12, "</td>");
					tl(12, "<td>");
				} else {
					if(entiteNomAffichage != null) {
						tl(12, "<div class=\"w3-cell-row w3-", classeCouleur, "\">");
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
						tl(12, "</div>");
					}
					tl(12, "<div class=\"w3-cell-row  \">");
					tl(13, "<div class=\"w3-cell \">");
				}
				tl(14, "{{> \"input", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "=", langueConfig.getString(ConfigCles.var_rolePourEcrire), " ", langueConfig.getString(ConfigCles.var_rolePourLire), "=", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
				if(entiteHtmLigneVerticaleActuel) {
					tl(12, "</td>");
					t(12, "<td>").sx(entiteDescription).l("</td>");
					tl(10, "</tr>");
				} else {
					tl(13, "</div>");
					tl(12, "</div>");
				}
			}
			else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
				if(entiteHtmLigneVerticaleActuel) {
					tl(10, "<tr>");
					tl(12, "<td>");
					if(entiteNomAffichage != null) {
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
					}
					tl(12, "</td>");
					tl(12, "<td>");
				} else {
					if(entiteNomAffichage != null) {
						tl(12, "<div class=\"w3-cell-row w3-", classeCouleur, "\">");
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
						tl(12, "</div>");
					}
					tl(12, "<div class=\"w3-cell-row w3-padding \">");
					tl(13, "<div class=\"w3-cell \">");
				}
				tl(14, "{{> \"input", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "=", langueConfig.getString(ConfigCles.var_rolePourEcrire), " ", langueConfig.getString(ConfigCles.var_rolePourLire), "=", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
				if(entiteHtmLigneVerticaleActuel) {
					tl(12, "</td>");
					t(12, "<td>").sx(entiteDescription).l("</td>");
					tl(10, "</tr>");
				} else {
					tl(13, "</div>");
					tl(12, "</div>");
				}
			}
			else if("LocalTime".equals(entiteNomSimple)) {
				if(entiteHtmLigneVerticaleActuel) {
					tl(10, "<tr>");
					tl(12, "<td>");
					if(entiteNomAffichage != null) {
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
					}
					tl(12, "</td>");
					tl(12, "<td>");
				} else {
					if(entiteNomAffichage != null) {
						tl(12, "<div class=\"w3-cell-row w3-", classeCouleur, "\">");
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
						tl(12, "</div>");
					}
					tl(12, "<div class=\"w3-cell-row w3-padding \">");
					tl(13, "<div class=\"w3-cell \">");
				}
				tl(14, "{{> \"input", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "=", langueConfig.getString(ConfigCles.var_rolePourEcrire), " ", langueConfig.getString(ConfigCles.var_rolePourLire), "=", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
				if(entiteHtmLigneVerticaleActuel) {
					tl(12, "</td>");
					t(12, "<td>").sx(entiteDescription).l("</td>");
					tl(10, "</tr>");
				} else {
					tl(13, "</div>");
					tl(12, "</div>");
				}
			}
			else if("Boolean".equals(entiteNomSimple)) {
				if(entiteHtmLigneVerticaleActuel) {
					tl(10, "<tr>");
					tl(12, "<td>");
					if(entiteNomAffichage != null) {
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
					}
					tl(12, "</td>");
					tl(12, "<td>");
				} else {
					if(entiteNomAffichage != null) {
						tl(12, "<div class=\"w3-cell-row w3-", classeCouleur, "\">");
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
						tl(12, "</div>");
					}
					tl(12, "<div class=\"w3-cell-row w3-padding \">");
					tl(13, "<div class=\"w3-cell \">");
				}
				tl(14, "{{> \"input", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "=", langueConfig.getString(ConfigCles.var_rolePourEcrire), " ", langueConfig.getString(ConfigCles.var_rolePourLire), "=", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
				if(entiteHtmLigneVerticaleActuel) {
					tl(12, "</td>");
					t(12, "<td>").sx(entiteDescription).l("</td>");
					tl(10, "</tr>");
				} else {
					tl(13, "</div>");
					tl(12, "</div>");
				}
			} else if("JsonArray".equals(entiteNomSimple)) {
				if(entiteHtmLigneVerticaleActuel) {
					tl(10, "<tr id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">");
					tl(12, "<td>");
					if(entiteNomAffichage != null) {
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
					}
					tl(12, "</td>");
				} else {
					if(entiteNomAffichage != null) {
						tl(12, "<div class=\"w3-cell-row w3-", classeCouleur, "\">");
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
						tl(12, "</div>");
					}
					tl(11, "<div class=\"w3-cell-row w3-padding \">");
					tl(13, "<div class=\"w3-cell \">");
				}
//				tl(14, "{{#each ", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}<td>{{.}}</td>{{/each}}");
				tl(14, "{{> \"input", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "=", langueConfig.getString(ConfigCles.var_rolePourEcrire), " ", langueConfig.getString(ConfigCles.var_rolePourLire), "=", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
				if(entiteHtmLigneVerticaleActuel) {
					tl(10, "</tr>");
				} else {
					tl(13, "</div>");
					tl(12, "</div>");
				}
			}
			else {
				if(entiteHtmLigneVerticaleActuel) {
					tl(10, "<tr>");
					tl(12, "<td>");
					if(entiteNomAffichage != null) {
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
					}
					tl(12, "</td>");
					tl(12, "<td>");
				} else {
					if(entiteNomAffichage != null) {
						tl(12, "<div class=\"w3-cell-row w3-", classeCouleur, "\">");
						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
						tl(12, "</div>");
					}
					tl(11, "<div class=\"w3-cell-row w3-padding \">");
					tl(13, "<div class=\"w3-cell \">");
				}
				tl(14, "{{> \"input", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "=", langueConfig.getString(ConfigCles.var_rolePourEcrire), " ", langueConfig.getString(ConfigCles.var_rolePourLire), "=", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
				if(entiteHtmLigneVerticaleActuel) {
					tl(12, "</td>");
					t(12, "<td>").sx(entiteDescription).l("</td>");
					tl(10, "</tr>");
				} else {
					tl(13, "</div>");
					tl(12, "</div>");
				}
			}
//			if(!entiteAttribuer && entiteModifier && !"Boolean".equals(entiteNomSimple)) {
//
//				tl(1, "{{#eq ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool \"true\"}}");
//
//				tl(2, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
//
//					tl(15, "<div class=\"w3-cell w3-left-align w3-cell-top \">");
//					tl(16, "<button");
//					tl(18, "tabindex=\"-1\"");
//					tl(18, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-", classeCouleur, " \"");
//					tl(18, "onclick=\"", langueConfig.getString(ConfigCles.var_enleverLueur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); $('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "').val(null); patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + $('#", classeNomSimple, "Form :input[name=", classeModele ? classeVarClePrimaire : classeVarCleUnique, "]').val() }], 'set", entiteVarCapitalise, "', null, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); \"");
//					tl(18, ">");
//					tl(17, "<i class=\"far fa-delete-left \"></i>");
//					tl(16, "</button>");
//					tl(15, "</div>");
//
//				tl(14, "{{/eq}}");
//
//				tl(1, "{{/eq}}");
//			}

			if(!entiteHtmLigneVerticaleActuel) {
				tl(11, "</div>");
				tl(10, "</div>");
				tl(9, "</div>");
			}
		}
//		else if(!(entiteAttribuer)) {
//
//			tl(9, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
//				if(entiteHtmLigneVerticaleActuel) {
//					tl(10, "<tr>");
//					tl(12, "<td>");
//					if(entiteNomAffichage != null) {
//						tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
//					}
//					tl(12, "</td>");
//					tl(12, "<td>");
//				} else {
//	
//					tl(10, "<div class=\"w3-padding \">");
//					tl(11, "<div class=\"w3-card \">");
//	
//					if(entiteNomAffichage != null) {
//						tl(12, "<div class=\"w3-cell-row w3-", classeCouleur, "\">");
//						tl(13, "<label>", entiteNomAffichage, "</label>");
//						tl(12, "</div>");
//					}
//					tl(12, "<div class=\"w3-cell-row  \">");
//					tl(13, "<div class=\"w3-cell \">");
//					tl(14, "<div class=\"w3-rest \">");
//					tl(15, "<span class=\"\">");
//				}
//					tl(16, "{{> \"input", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "=", langueConfig.getString(ConfigCles.var_rolePourEcrire), " ", langueConfig.getString(ConfigCles.var_rolePourLire), "=", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
//				if(entiteHtmLigneVerticaleActuel) {
//					tl(12, "</td>");
//					t(12, "<td>").sx(entiteDescription).l("</td>");
//					tl(10, "</tr>");
//				} else {
//					tl(15, "</span>");
//					tl(14, "</div>");
//					tl(13, "</div>");
//					tl(12, "</div>");
//					tl(11, "</div>");
//					tl(10, "</div>");
//				}
//			tl(1, "{{/eq}}");
//		}

		if(!entiteHtmLigneVerticaleActuel) {
			tl(8, "</div>");
		}
		l("{{/inline}}");  

		///////////
		// input //
		///////////

		l();
		tl(0, "{{#*inline \"input", entiteVarCapitalise, "\"}}");
		if(entiteModifier && (entiteDefinir || entiteAttribuer)) {

			tl(2, "{{#eq ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "Bool \"true\"}}");

			if(entiteAttribuer) {
				tl(14, "{{#eq '", langueConfig.getString(ConfigCles.var_PUTCopie), "' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(15, "<div>");
				tl(16, "<input ");
				tl(17, "type=\"checkbox\"");
				tl(17, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), "\"");
				tl(17, "class=\"", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), " ", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), " \"");
				tl(17, ">");
				tl(16, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), "\">", langueConfig.getString(ConfigCles.var_vider), "</label>");
				tl(15, "</div>");
				tl(14, "{{/eq}}");

				tl(14, "<input");
				tl(16, "type=\"text\"");

				if(entiteNomAffichage != null) {
					tl(16, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
				}
				if(entiteDescription != null) {
					t(16, "title=\"").sx(entiteDescription).s("\"");
				}

				tl(15, "class=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, " ", langueConfig.getString(ConfigCles.var_valeur), StringUtils.capitalize(entiteAttribuerVarSuggere), " ", langueConfig.getString(ConfigCles.var_suggere), entiteVarCapitalise, " w3-input w3-border w3-cell w3-cell-middle \"");
				tl(15, "name=\"", "set", entiteVarCapitalise, "\"");
				tl(15, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				tl(15, "autocomplete=\"off\"");
				t(15, "oninput=\"", langueConfig.getString(ConfigCles.var_suggere), classeNomSimple, entiteVarCapitalise, "($(this).val() ? [ { 'name': 'q', 'value': '", entiteAttribuerVarSuggere, ":' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", langueConfig.getString(ConfigCles.var_classeNomCanonique), ",", entiteAttribuerVar, ",", classeVarClePrimaire, entiteAttribuerVarUrlPk == null ? "" : "," + entiteAttribuerVarUrlPk, entiteAttribuerVarTitre == null ? "" : "," + entiteAttribuerVarTitre, "' } ] : [");
				s("{{#if ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}{'name':'fq','value':'", entiteAttribuerVar, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}'}{{else}}{{/if}}");
				l("], $('#list", classeNomSimple, entiteVarCapitalise, "_{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}'), {{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}); \"");
				tl(15, "/>");
				l();
			}
			else if("LocalDate".equals(entiteNomSimple)) {
				tl(14, "<input");
				tl(16, "type=\"date\"");
				tl(16, "class=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, " w3-input w3-border datepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				if(entiteDescription != null)
					tl(16, "title=\"", entiteDescription, " (", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), ")\"");
//				tl(5, "value=\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_ddDashMMDashyyyy), "\").format(", entiteVar, "));");
				tl(16, "value=\"{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}\"");
				tl(14, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				t(15, "onchange=\"");
					s("if(this.value) { ");
						s("patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', this.value, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('.{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('.{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); ");
					s("} ");
				l("\"");
				tl(14, "{{/eq}}");
				tl(14, "/>");
			}
			else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
				tl(14, "<input");
				tl(16, "type=\"text\"");
				tl(16, "class=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, " w3-input w3-border datetimepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "placeholder=\"", entiteDefaut == null ? langueConfig.getString(ConfigCles.str_ddDashMMDashyyyy_HHColonmm_VV) : entiteDefaut, "\"");
				tl(16, "data-timeformat=\"", langueConfig.getString(ConfigCles.str_ddDashMMDashyyyy_HHColonmm_VV), "\"");
				tl(16, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				if(entiteDescription != null)
					t(16, "title=\"").sx(entiteDescription).l(" (", langueConfig.getString(ConfigCles.str_ddDashMMDashyyyy_HHColonmm_VV), ")\"");
//				tl(4, ".a(\"value\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz_VV), "\").format(", entiteVar, "));");
				tl(16, "value=\"{{#if ", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}{{formatZonedDateTime ", uncapitalizeClasseNomSimple, "_.", entiteVar, " \"", langueConfig.getString(ConfigCles.str_ddDashMMDashyyyy_HHColonmm_VV), "\" defaultLocaleId defaultZoneId}}{{/if}}\"");
				tl(14, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(15, "onclick=\"", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); \";");
				t(15, "onchange=\"");
					s("var timeZone = this.value.split('[').pop().split(']')[0]; ");
					s("var t1 = moment(this.value.split('[')[0].trim(), '", langueConfig.getString(ConfigCles.str_DDDashMMDashYYYY_HHColonmm), "'); ");
					s("var t2 = moment.tz(this.value.split('[')[0].trim(), '", langueConfig.getString(ConfigCles.str_DDDashMMDashYYYY_HHColonmm), "', timeZone); ");
					s("var t3 = new Date(t1._d); ");
					s("t3.setTime(t1.toDate().getTime() + t2.toDate().getTime() - t1.toDate().getTime()); ");
					s("var t = moment(t3); ");
					s("if(t) { ");
						s("var s = t.tz(timeZone).format('YYYY-MM-DDTHH:mm:ss.000') + '[' + timeZone + ']'; ");
						s("patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', s, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('.{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('.{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); ");
					s("} ");
				l("\"");
				tl(14, "{{/eq}}");
				tl(14, "/>");
			}
			else if("LocalTime".equals(entiteNomSimple)) {
				tl(14, "<input");
				tl(16, "type=\"text\"");
				tl(16, "class=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, " w3-input w3-border timepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "placeholder=\"", langueConfig.getString(ConfigCles.var_HHColonMM), "\"");
				tl(16, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

				tl(14, "<input");
				tl(16, "type=\"text\"");
				tl(16, "class=\", \"w3-input w3-border timepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "placeholder=\"", langueConfig.getString(ConfigCles.var_HHColonMM), "\"");
				tl(16, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				if(entiteDescription != null)
					t(16, "title=\"").sx(entiteDescription).l(" (", langueConfig.getString(ConfigCles.var_HAposhAposmm), ")\"");
				tl(16, "value=\"{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}\"");
				tl(14, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(15, "onclick=\"", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); \"");
				t(15, "onchange=\"");
					s("var t = moment(this.value, '", langueConfig.getString(ConfigCles.var_HAposhAposmm), "'); ");
					s("if(t) { ");
						s("var s = t.format('HH:mm'); ");
						s("patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', s, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('.{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('.{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); ");
					s("} ");
				l("\"");
				tl(14, "{{/eq}}");
				tl(14, "/>");
			}
			else if("Boolean".equals(entiteNomSimple)) {
				tl(1, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(15, "<input");
				tl(16, "type=\"checkbox\"");
				tl(16, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				tl(16, "value=\"true\"");
				tl(1, "{{else}}");
				tl(14, "<select");
				tl(15, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				tl(1, "{{/eq}}");

				tl(1, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(15, "class=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(15, "name=\"set", entiteVarCapitalise, "\"");
				tl(1, "{{else}}");
				tl(2, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(16, "class=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "name=\"set", entiteVarCapitalise, "\"");
				tl(2, "{{else}}");
				tl(16, "class=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, " set", entiteVarCapitalise, " ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "name=\"set", entiteVarCapitalise, "\"");
				tl(2, "{{/eq}}");
				tl(1, "{{/eq}}");
				tl(1, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				t(15, "onchange=\"");
				if(
						entiteVar.equals(langueConfig.getString(ConfigCles.var_supprime))
						|| entiteVar.equals(langueConfig.getString(ConfigCles.var_archive))
						) {
					s("var confirmResponse = confirm('", langueConfig.getString(entiteVar.equals(langueConfig.getString(ConfigCles.var_supprime)) ? ConfigCles.str_confirmer_supprimer : ConfigCles.str_confirmer_archiver), "'); ");
					s("if(confirmResponse) { ");
					s("patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', $(this).prop('checked'), function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('.{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('.{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); ");
					s("}");
				} else {
					s("patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', $(this).prop('checked'), function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('.{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('.{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); ");
				}
				l("\"");
				tl(1, "{{/eq}}");

				tl(1, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(2, "{{#if ", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}");
				tl(16, "checked=\"checked\"");
				tl(2, "{{/if}}");
				tl(16, "/>");
				tl(1, "{{else}}");
				tl(15, ">");
				tl(15, "<option value=\"\" selected=\"selected\"></option>");
				tl(15, "<option value=\"true\">true</option>");
				tl(15, "<option value=\"false\">false</option>");
				tl(14, "</select>");
				tl(1, "{{/eq}}");
				l();
			}
			else if(entiteImageBase64Url != null) {
				tl(14, "<div class=\"imageBase64Div1", classeNomSimple, "_", entiteVar, "\" id=\"imageBase64Div1", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\">");

				tl(15, "<h5>", langueConfig.getString(ConfigCles.str_Télécharger_image), "</h5>");
				tl(15, "<form method=\"POST\" enctype=\"multipart/form-data\" action=\"", entiteImageBase64Url, "\" class=\"\">");
				tl(16, "<input type=\"hidden\" name=\"", classeModele ? classeVarClePrimaire : classeVarCleUnique, "\" value=\"{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}\"/>");
				tl(16, "<input type=\"hidden\" name=\"", langueConfig.getString(ConfigCles.var_classeNomSimple), "\" value=\"", classeNomSimple, "\"/>");
				tl(16, "<input name=\"", langueConfig.getString(ConfigCles.var_fichier), "\" type=\"file\" onchange=\"$.ajax({ type: 'POST', enctype: 'multipart/form-data', url: '", entiteImageBase64Url, "', data: new FormData(this.form), processData: false, contentType: false}); \"/>");
				tl(15, "</form>");

				tl(15, "<img id=\"imageBase64Img", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\");");
				tl(16, "class=\"img", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-image \"");
				tl(16, "src=\"{{#if ", entiteVar, "}}data:image/png;base64,{{else}}{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}{{/if}} alt=\"\"");
				tl(15, "/>");

				tl(14, "</div>");
			}
			else if(BooleanUtils.isTrue(entiteSignature)) {
				tl(14, "<div class=\"signatureDiv1", classeNomSimple, "_", entiteVar, " signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, "\" id=\"signatureDiv1", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\">");

				tl(15, "<div id=\"signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\"");
				tl(16, "style=\"display: {{#if ", entiteVar, "}}block{{else}}none{{/if}}\"");
				tl(15, "</div>");

				tl(15, "<img id=\"signatureImg", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\"");
				tl(16, "class=\"signatureImg", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, "\"");
				tl(16, "src=\"{{#if ", entiteVar, "}}data:image/png;base64{{else}}{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}\" alt=\"\"");
				tl(16, "style=\"padding: 10px; display: {{#if ", entiteVar, "}}none{{else}}block{{/if}}\"");
				tl(15, "/>");

				tl(14, "<div>");
				tl(14, "<div id=\"signatureDiv2", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\">");

				tl(15, "<button id=\"signatureButton", langueConfig.getString(ConfigCles.var_Vider), classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "\"");
				tl(16, "class=\"w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin \"");
				tl(16, "onclick=\"");
				tl(17, "$('#signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "').show(); ");
				tl(17, "$('#signatureImg", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "').hide(); ");
				tl(17, "", langueConfig.getString(ConfigCles.var_enleverLueur), "($('#signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "')); ");
				tl(17, "patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', null); ");
				tl(17, "if($('#signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "')) { ");
				tl(17, "$('#signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "').jSignature('reset'); ");
				tl(17, "} else { ");
				tl(17, "$('#signatureInput", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVar, "').jSignature({'height':200}); ");
				tl(17, "}");
				tl(16, "\"");
				tl(16, ">", langueConfig.getString(ConfigCles.var_Vider));
				tl(15, "</button>");

				tl(14, "</div>");

			}
			else {
				if(entiteMultiligne)
					tl(14, "<textarea");
				else {
					tl(14, "<input");
					tl(15, "type=\"text\"");
				}

				if(entiteNomAffichage != null) {
					tl(15, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
				}
				if(entiteDescription != null) {
					t(15, "title=\"").sx(entiteDescription).s("\"");
				}
				tl(15, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

				tl(1, "{{#eq \"Page\" ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
					tl(16, "class=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, " set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
					tl(16, "name=\"set", entiteVarCapitalise, "\"");
				tl(1, "{{else}}");
				tl(2, "{{#eq \"PATCH\" ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(16, "class=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, " set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "name=\"set", entiteVarCapitalise, "\"");
				tl(2, "{{else}}");
					tl(16, "{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\" class=\"", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " w3-input w3-border class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
					tl(16, "name=\"", entiteVar, "\"");
				tl(2, "{{/eq}}");
				tl(1, "{{/eq}}");
				tl(1, "{{#eq \"Page\" ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
					tl(16, "onclick=\"", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); \"");
					t(16, "onchange=\"patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }]");
					if(entiteListeTypeJson != null)
						s(", 'set", entiteVarCapitalise, "', $(this).val().replace('[','').replace(']','').split(/[ ,]+/)");
					else if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson))
						s(", 'set", entiteVarCapitalise, "', $(this).val() == '' ? null : JSON.parse($(this).val())");
					else
						s(", 'set", entiteVarCapitalise, "', $(this).val()");
					l(", function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); \"");
				tl(1, "{{/eq}}");

				if(entiteMultiligne) {
					tl(14, ">", "{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}");
				}
				else {
					tl(1, "{{#eq \"Page\" ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
					if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson))
						tl(15, "value=\"{{to", entiteNomSimpleVertxJson, "String ", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}\"");
					else
						tl(15, "value=\"{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}\"");
					tl(1, "{{/eq}}");
				}

				if(entiteMultiligne)
					s("</textarea>");
				else
					tl(14, "/>");

				l();
			}

			if(entiteAttribuer) {
				tl(13, "{{else}}");
			}
			else if(classeUtilisateurEcrire && classeSessionEcrire || classePublicLire) {
				tl(13, "{{else}}");
				tl(15, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}", (entiteVarUrl == null ? "</span>" : "</a>"));
			}
			else if(classeUtilisateurEcrire) {
				if(classeRolesTrouves || classeRoleLiresTrouves) {
					tl(13, "{{else}}");
					tl(15, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
				}
				else {
					tl(13, "{{else}}");
					tl(14, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
				}
			}
			else if(classeSessionEcrire) {
				tl(13, "{{else}}");
				tl(14, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
			}
			else if(classeRolesTrouves || classeRoleLiresTrouves) {
					tl(13, "{{else}}");
				tl(14, "{{#ifContainsKeys ", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s}}");
				tl(15, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
				tl(3, "{{/ifContainsKeys}}");
			}
			else {
//								tl(3, "sx(htm", entiteVarCapitalise, "());");
			}

			tl(2, "{{else}}");
				tl(14, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
			tl(2, "{{/eq}}");
		}
		else {
			tl(1, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
			if(entiteAttribuer) {
				tl(14, "{{#eq '", langueConfig.getString(ConfigCles.var_PUTCopie), "' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(15, "<div>");
				tl(16, "<input ");
				tl(17, "type=\"checkbox\"");
				tl(17, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), "\"");
				tl(17, "class=\"", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), " ", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), " \"");
				tl(17, ">");
				tl(16, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), "\">", langueConfig.getString(ConfigCles.var_vider), "</label>");
				tl(15, "</div>");
				tl(14, "{{/eq}}");

				tl(14, "<input");
				tl(16, "type=\"text\"");

				if(entiteNomAffichage != null) {
					tl(16, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
				}
				if(entiteDescription != null) {
					t(16, "title=\"").sx(entiteDescription).s("\"");
				}

				tl(15, "class=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, " ", langueConfig.getString(ConfigCles.var_valeur), StringUtils.capitalize(entiteAttribuerVarSuggere), " ", langueConfig.getString(ConfigCles.var_suggere), entiteVarCapitalise, " w3-input w3-border w3-cell w3-cell-middle \"");
				tl(15, "name=\"", "set", entiteVarCapitalise, "\"");
				tl(15, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				tl(15, "autocomplete=\"off\"");
				t(15, "oninput=\"", langueConfig.getString(ConfigCles.var_suggere), classeNomSimple, entiteVarCapitalise, "($(this).val() ? [ { 'name': 'q', 'value': '", entiteAttribuerVarSuggere, ":' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", langueConfig.getString(ConfigCles.var_classeNomCanonique), ",", entiteAttribuerVar, ",", classeVarClePrimaire, entiteAttribuerVarUrlPk == null ? "" : "," + entiteAttribuerVarUrlPk, entiteAttribuerVarTitre == null ? "" : "," + entiteAttribuerVarTitre, "' } ] : [");
				s("{{#if ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}{'name':'fq','value':'", entiteAttribuerVar, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}'}{{else}}{{/if}}");
				l("], $('#list", classeNomSimple, entiteVarCapitalise, "_{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}'), {{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}); \"");
				tl(15, "/>");
				l();
			} else if("LocalDateTime".equals(entiteNomSimple)) {
				tl(14, (entiteVarUrl == null ? "<span id=\"{{" + langueConfig.getString(ConfigCles.var_classeApiMethodeMethode) + "}}_" + entiteVar + "\"" : "<a href=\"{{ " + uncapitalizeClasseNomSimple + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \" title=\"{{formatLocalDateTime ", uncapitalizeClasseNomSimple, "_.", entiteVar, " 'EEEE MMMM d yyyy H:mm:ss.SSS zz VV' defaultLocaleId defaultZoneId}}\">{{formatZonedDateTime ", uncapitalizeClasseNomSimple, "_.", entiteVar, " 'EEE MMM d yyyy' defaultLocaleId defaultZoneId}}", (entiteVarUrl == null ? "</span>" : "</a>"));
			} else if("ZonedDateTime".equals(entiteNomSimple)) {
				tl(14, (entiteVarUrl == null ? "<span id=\"{{" + langueConfig.getString(ConfigCles.var_classeApiMethodeMethode) + "}}_" + entiteVar + "\"" : "<a href=\"{{ " + uncapitalizeClasseNomSimple + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \" title=\"{{formatZonedDateTime ", uncapitalizeClasseNomSimple, "_.", entiteVar, " 'EEEE MMMM d yyyy H:mm:ss.SSS zz VV' defaultLocaleId defaultZoneId}}\">{{formatZonedDateTime ", uncapitalizeClasseNomSimple, "_.", entiteVar, " 'EEE MMM d yyyy h:mm a zz' defaultLocaleId defaultZoneId}}", (entiteVarUrl == null ? "</span>" : "</a>"));
			} else {
				tl(14, (entiteVarUrl == null ? "<span id=\"{{" + langueConfig.getString(ConfigCles.var_classeApiMethodeMethode) + "}}_" + entiteVar + "\"" : "<a href=\"{{ " + uncapitalizeClasseNomSimple + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}", (entiteVarUrl == null ? "</span>" : "</a>"));
			}
			tl(1, "{{/eq}}");
		}
		tl(0, "{{/inline}}");

		o = oAncien;
	}

	public void pageCodeClasseJava(String langueNom, YAMLConfiguration langueConfig) throws Exception {

		classeVarClePrimaire = classeDoc.getString("classeVarClePrimaire"   + "_" + langueNom + "_stored_string");
		classeGenPageChemin = classeDoc.getString("classeGenPageChemin"   + "_" + langueNom + "_stored_string");
		classePageChemin = classeDoc.getString("classePageChemin"   + "_" + langueNom + "_stored_string");
		classePageCheminCss = classeDoc.getString("classePageCheminCss"   + "_" + langueNom + "_stored_string");
		classePageCheminJs = classeDoc.getString("classePageCheminJs"   + "_" + langueNom + "_stored_string");
		classePageCheminHbs = classeDoc.getString("classePageCheminHbs"   + "_" + langueNom + "_stored_string");
		classeGenPageCheminHbs = classeDoc.getString("classeGenPageCheminHbs"   + "_" + langueNom + "_stored_string");
		classeApiUri = classeDoc.getString("classeApiUri" + "_" + langueNom + "_stored_string");
		classePageUriMethode = classeDoc.getString("classeApiUri" + langueConfig.getString(ConfigCles.var_PageRecherche) + "_" + langueNom + "_stored_string");
		classePageLangueNom = classeDoc.getString("classePageLangueNom"  + "_" + langueNom + "_stored_string");
		classeModele = (Boolean)classeDoc.getBoolean("classeModele_stored_boolean");
		classePageLangueConfig = null;
		if(classePageLangueNom != null) {
			classePageLangueConfig = configurations.fileBased(YAMLConfiguration.class, String.format("%s/src/main/resources/org/computate/i18n/i18n_%s.yml", appComputate, classePageLangueNom));
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

			auteurWebsocket = ToutEcrivain.create();

			wRecherche = ToutEcrivain.create();
			wVarsFqJs = ToutEcrivain.create();
			wPOST = ToutEcrivain.create();
			wPUTImport = ToutEcrivain.create();
			wPUTFusion = ToutEcrivain.create();
			wPUTCopie = ToutEcrivain.create();
			wPATCH = ToutEcrivain.create();
			wSuggere = ToutEcrivain.create();
			wGetters = ToutEcrivain.create();
			wTh = ToutEcrivain.create();
			wTd = ToutEcrivain.create();
			wFoot = ToutEcrivain.create();

			auteurWebsocket.setTab("  ");
			wRecherche.setTab("  ");
			wVarsFqJs.setTab("  ");
			wPOST.setTab("  ");
			wPUTImport.setTab("  ");
			wPUTFusion.setTab("  ");
			wPUTCopie.setTab("  ");
			wPATCH.setTab("  ");

			wForms = new ArrayList<>();
			wClasseApiMethodeMethodes = new ArrayList<>();

			wFormRecherche = ToutEcrivain.create();
			wForms.add(wFormRecherche);
			wClasseApiMethodeMethodes.add(langueConfig.getString(ConfigCles.var_Recherche));

			wFormPOST = ToutEcrivain.create();
			wForms.add(wFormPOST);
			wClasseApiMethodeMethodes.add("POST");

			wFormPUTImport = ToutEcrivain.create();
			wForms.add(wFormPUTImport);
			wClasseApiMethodeMethodes.add("PUTImport");

			wFormPUTFusion = ToutEcrivain.create();
			wForms.add(wFormPUTFusion);
			wClasseApiMethodeMethodes.add(langueConfig.getString(ConfigCles.var_PUTFusion));

			wFormPUTCopie = ToutEcrivain.create();
			wForms.add(wFormPUTCopie);
			wClasseApiMethodeMethodes.add(langueConfig.getString(ConfigCles.var_PUTCopie));

			wFormPage = ToutEcrivain.create();
			wForms.add(wFormPage);
			wClasseApiMethodeMethodes.add("Page");

			wFormPATCH = ToutEcrivain.create();
			wForms.add(wFormPATCH);
			wClasseApiMethodeMethodes.add("PATCH");

			wJsInit = ToutEcrivain.create();
			wWebsocket = ToutEcrivain.create();
			wWebsocketInput1 = ToutEcrivain.create();
			wWebsocketInput2 = ToutEcrivain.create();
			wWebsocketInput3 = ToutEcrivain.create();
			wPks = ToutEcrivain.create();

			wWebsocket.setTab("  ");
			wWebsocketInput1.setTab("  ");
			wWebsocketInput2.setTab("  ");
			wWebsocketInput3.setTab("  ");
			wPks.setTab("  ");


			o = auteurGenPageHbs;

			o = auteurPageGenClasse;
			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + classesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
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
					Boolean resultatFormPUTImport = false; 
					Boolean resultatFormPUTFusion = false; 
					Boolean resultatFormPUTCopie = false; 
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
							entiteHtml = (Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean");
							entiteHtmLigne = (Integer)entiteDocumentSolr.get("entiteHtmLigne_stored_int");
							entiteHtmLigneTitre = (String)entiteDocumentSolr.get("entiteHtmLigneTitre_stored_string");
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
							entiteVarH1 = (Boolean)entiteDocumentSolr.get("entiteVarH1_stored_boolean");
							entiteVarH2 = (Boolean)entiteDocumentSolr.get("entiteVarH2_stored_boolean");
							entiteVarH3 = (Boolean)entiteDocumentSolr.get("entiteVarH3_stored_boolean");
							entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
							entiteModifier = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteModifier_stored_boolean"));
							entiteDefinir = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteDefinir_stored_boolean"));
							entiteAttribuer = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteAttribuer_stored_boolean"));
							entiteSignature = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSignature_stored_boolean"));
							entiteSuggere = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean"));
							entiteListeTypeJson = (String)entiteDocumentSolr.get("entiteListeTypeJson_stored_string");
							entiteAttribuerNomSimple = (String)entiteDocumentSolr.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string");
							entiteAttribuerVar = (String)entiteDocumentSolr.get("entiteAttribuerVar_" + langueNom + "_stored_string");
							entiteAttribuerVarSuggere = (String)entiteDocumentSolr.get("entiteAttribuerVarSuggere_" + langueNom + "_stored_string");
							entiteAttribuerOperationIdPATCH = (String)entiteDocumentSolr.get("entiteAttribuerOperationIdPATCH_" + langueNom + "_stored_string");
							entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + langueConfig.getString(ConfigCles.var_Recherche) + "_" + langueNom + "_stored_string");
							entiteAttribuerApiUri = (String)entiteDocumentSolr.get("entiteAttribuerApiUri_" + langueNom + "_stored_string");
							entiteAttribuerPageUri = (String)entiteDocumentSolr.get("entiteAttribuerPageUri_" + langueNom + "_stored_string");
							entiteAttribuerContexteUnNom = (String)entiteDocumentSolr.get("entiteAttribuerContexteUnNom_" + langueNom + "_stored_string");
							entiteAttribuerContexteNomPluriel = (String)entiteDocumentSolr.get("entiteAttribuerContexteNomPluriel_" + langueNom + "_stored_string");
							entiteAttribuerContexteCouleur = (String)entiteDocumentSolr.get("entiteAttribuerContexteCouleur_stored_string");
							entiteAttribuerContexteIconeGroupe = (String)entiteDocumentSolr.get("entiteAttribuerContexteIconeGroupe_stored_string");
							entiteAttribuerContexteIconeNom = (String)entiteDocumentSolr.get("entiteAttribuerContexteIconeNom_stored_string");
							entiteAttribuerTypeJson = (String)entiteDocumentSolr.get("entiteAttribuerTypeJson_stored_string");
							entiteImageBase64Url = (String)entiteDocumentSolr.get("entiteImageBase64Url_" + langueNom + "_stored_string");
							entiteNomSimpleVertxJson = (String)entiteDocumentSolr.get("entiteNomSimpleVertxJson_stored_string");

							wFormRecherche.l(entiteVar);
							if(entiteHtml) {
								if(entiteHtmCellule != null) {
									if(ecrireFormEntite(langueNom, langueConfig, wFormPage, "Page"))
										resultatFormPage = true;
									genCodeEntiteHtm(langueNom, langueConfig, "Page");
								}
								if(entiteHtmLigne != null && (entiteDefinir || entiteAttribuer)) {
//											if(ecrireFormEntite(langueNom, wFormPUTImport, "PUTImport"))
//												resultatFormPUTImport = true;
//											if(ecrireFormEntite(langueNom, wFormPUTFusion, langueConfig.getString(ConfigCles.var_PUTFusion)))
//												resultatFormPUTFusion = true;
									if(ecrireFormEntite(langueNom, langueConfig, wFormPOST, "POST"))
										resultatFormPOST = true;
									if(ecrireFormEntite(langueNom, langueConfig, wFormPUTCopie, langueConfig.getString(ConfigCles.var_PUTCopie)))
										resultatFormPUTCopie = true;
									if(ecrireFormEntite(langueNom, langueConfig, wFormPATCH, "PATCH"))
										resultatFormPATCH = true;
								}
								if(entiteIndexeOuStocke) {
									if(ecrireFormEntite(langueNom, langueConfig, wFormRecherche, langueConfig.getString(ConfigCles.var_Recherche)))
										resultatFormRecherche = true;
								}
							}
							if(entiteAttribuer) {
								wJsInit.tl(2, "{{#ifContainsAnyRoles roles ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "}}");
								wJsInit.tl(5, langueConfig.getString(ConfigCles.var_suggere), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], $('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, true);");
								wJsInit.tl(2, "{{else}}");
								wJsInit.tl(2, "{{#ifContainsAnyRoles roles ", langueConfig.getString(ConfigCles.var_rolePourLire), "}}");
								wJsInit.tl(5, langueConfig.getString(ConfigCles.var_suggere), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], $('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, true);");
								wJsInit.tl(2, "{{else}}");
								wJsInit.tl(5, langueConfig.getString(ConfigCles.var_suggere), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], $('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, false);");
								wJsInit.tl(2, "{{/ifContainsAnyRoles}}");
								wJsInit.tl(2, "{{/ifContainsAnyRoles}}");
//									wWebsocket.tl(2, "tl(2, \"", "await patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + \" + ", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Requete), StringUtils.capitalize(classeVarClePrimaire), "() + \" } ], {});\");");
								wPks.tl(2, "if(c == '", entiteAttribuerNomSimple, "')");
								wPks.tl(2, "await patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + pk2 } ], {});");
							}
							if(entiteSignature) {
								wJsInit.tl(2, "$('#signatureInput", classeNomSimple, "' + pk + '", entiteVar, "').jSignature({'height':200}).bind('change', function(e){ patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " }], 'set", entiteVarCapitalise, "', $('#signatureInput", classeNomSimple, "' + pk + '", entiteVar, "').jSignature('getData', 'default'));");
							}
							if(entiteDefinir || entiteAttribuer || entiteIndexeOuStocke) {
								wWebsocketInput3.l();
								if(entiteHtmLigneTitre != null) {
//									wWebsocketInput3.t(4, "$response.find(\"#").sx(genererId(entiteHtmLigneTitre)).s("\").replaceAll('#").sx(genererId(entiteHtmLigneTitre)).l("');");
//									wWebsocketInput3.l();
								}
								wWebsocketInput1.tl(4, "var input", entiteVarCapitalise, " = null;");
								wWebsocketInput2.tl(4, "if(vars.includes('", entiteVar, "'))");
								wWebsocketInput2.tl(5, "input", entiteVarCapitalise, " = $response.find('.Page_", entiteVar, "');");
								wWebsocketInput3.tl(4, "if(input", entiteVarCapitalise, ") {");
								wWebsocketInput3.tl(5, "input", entiteVarCapitalise, ".replaceAll('.Page_", entiteVar, "');");
								wWebsocketInput3.tl(5, langueConfig.getString(ConfigCles.var_ajouterLueur), "($('.Page_", entiteVar, "'));");
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
//									wWebsocketInput.tl(5, "$('.img", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//									wWebsocketInput.tl(6, "if(val !== $(this).attr('src'))");
//									wWebsocketInput.tl(7, "$(this).attr('src', val);");
//									wWebsocketInput.tl(5, "});");
//								}
//								if(entiteSignature) {
//									wWebsocketInput.tl(5, "if(val === undefined)");
//									wWebsocketInput.tl(6, "val = null;");
//									wWebsocketInput.tl(5, "$('.signatureInput", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//									wWebsocketInput.tl(6, "if(val !== $('.signatureImg", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').attr('src'))");
//									wWebsocketInput.tl(7, "$('.signatureImg", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').attr('src', val == null ? 'data:image/png;base64,' : val);");
//									wWebsocketInput.tl(6, langueConfig.getString(ConfigCles.var_ajouterLueur), "($('.signatureInput", classeNomSimple, "' + pk + '", entiteVarCapitalise, "'));");
//									wWebsocketInput.tl(5, "});");
//								}
//								wWebsocketInput.tl(5, "$('.input", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//								wWebsocketInput.tl(6, "if(val !== $(this).val())");
//								if(entiteNomSimple.startsWith("Json")) {
//									wWebsocketInput.tl(7, "$(this).val(JSON.stringify(val));");
//								} else {
//									wWebsocketInput.tl(7, "$(this).val(val);");
//								}
//								wWebsocketInput.tl(7, langueConfig.getString(ConfigCles.var_ajouterLueur), "($(this));");
//								wWebsocketInput.tl(5, "});");
//								wWebsocketInput.tl(5, "$('.var", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//								wWebsocketInput.tl(6, "if(val !== $(this).text())");
//								if(entiteNomSimple.startsWith("Json")) {
//									wWebsocketInput.tl(7, "$(this).val(JSON.stringify(val));");
//								} else {
//									wWebsocketInput.tl(7, "$(this).text(val);");
//								}
//								wWebsocketInput.tl(7, langueConfig.getString(ConfigCles.var_ajouterLueur), "($(this));");
//								wWebsocketInput.tl(5, "});");
//								wWebsocketInput.tl(4, "}");
							}
						}
						rechercheSolr.setStart(i.intValue() + rechercheLignes);
						rechercheReponse = clientSolrComputate.query(rechercheSolr);
						rechercheListe = rechercheReponse.getResults();
					}

					wWebsocket.tl(1, "var ", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, " = ", langueConfig.getString(ConfigCles.var_requeteApi), "['", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, "'];");
					wWebsocket.tl(1, "var ", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, "s = ", langueConfig.getString(ConfigCles.var_requeteApi), "['", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, "s'];");
					wWebsocket.tl(1, "var classes = ", langueConfig.getString(ConfigCles.var_requeteApi), "['classes'];");
					wWebsocket.tl(1, "var vars = ", langueConfig.getString(ConfigCles.var_requeteApi), "['vars'];");
					wWebsocket.tl(1, "var empty = ", langueConfig.getString(ConfigCles.var_requeteApi), "['empty'];");
					wWebsocket.l();
					wWebsocket.tl(1, "if(", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, " != null && vars.length > 0) {");
					wWebsocket.tl(2, "var queryParams = \"?\" + $(\".pageSearchVal\").get().filter(elem => elem.innerText.length > 0).map(elem => elem.innerText).join(\"&\");");
					wWebsocket.tl(2, "var uri = location.pathname + queryParams;");
					wWebsocket.tl(2, "$.get(uri, {}, function(data) {");
					wWebsocket.tl(3, "var $response = $(\"<html/>\").html(data);");
//					wWebsocket.tl(2, langueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Vals([ {name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + pk} ], function( data, textStatus, jQxhr ) {");
//					wWebsocket.tl(3, "var o = data['list'][0];");
//					wWebsocket.tl(3, "if(o != null) {");
					wWebsocket.s(wWebsocketInput1);
					wWebsocket.l();
					wWebsocket.s(wWebsocketInput2);
					wWebsocket.tl(4, langueConfig.getString(ConfigCles.var_jsWebsocket), classeNomSimple, "(", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, ", vars, $response);");
					wWebsocket.l();
					wWebsocket.tl(4, "window.", StringUtils.uncapitalize(classeNomSimple), " = JSON.parse($response.find('.", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Formulaire), " .", StringUtils.uncapitalize(classeNomSimple), "').val());");
					wWebsocket.l();
					wWebsocket.s(wWebsocketInput3);
//					wWebsocket.tl(3, "} else {");
//					wWebsocket.tl(4, "window.location.href = '", classePageUriMethode + "';");
//					wWebsocket.tl(3, "}");
					wWebsocket.tl(2, "});");
					wWebsocket.tl(1, "}");
//						wWebsocket.l();
//						wWebsocket.tl(1, "if(!empty) {");
//						wWebsocket.tl(2, "if(pks) {");
//						wWebsocket.tl(3, "for(i=0; i < pks.length; i++) {");
//						wWebsocket.tl(4, "var pk2 = pks[i];");
//						wWebsocket.tl(4, "var c = classes[i];");
//						wWebsocket.tl(4, "await window['patch' + c + 'Vals']( [ {name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + pk2} ], {});");
//						wWebsocket.tl(3, "}");
//						wWebsocket.tl(2, "}");
//						wWebsocket.tl(2, "if(pk)");
//						wWebsocket.tl(3, "await patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Vals( [ {name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + pk} ], {});");
//						wWebsocket.tl(1, "}");

					for(Integer i=0; i < wForms.size(); i++) {
						ToutEcrivain wForm = wForms.get(i);
						String wClasseApiMethodeMethode = wClasseApiMethodeMethodes.get(i);
						if(Optional.ofNullable(entiteHtmLigneVerticaleActuelMap.get(wClasseApiMethodeMethode)).orElse(false)) {
							wForm.tl(9, "</tbody>");
							wForm.tl(8, "</table>");
						}
					}
					if(resultatFormPOST)
						wFormPOST.tl(7, "</div>");
					if(resultatFormPUTImport)
						wFormPUTImport.tl(7, "</div>");
					if(resultatFormPUTFusion)
						wFormPUTFusion.tl(7, "</div>");
					if(resultatFormPUTCopie)
						wFormPUTCopie.tl(7, "</div>");
					if(resultatFormPage)
						wFormPage.tl(7, "</div>");
					if(resultatFormPATCH)
						wFormPATCH.tl(7, "</div>");
					if(resultatFormRecherche)
						wFormRecherche.tl(7, "</div>");
				}
			}
	
			if(auteurPageClasse != null) {
				if(classeDroitAuteur != null)
					auteurPageClasse.l(classeDroitAuteur);
				auteurPageClasse.l("package ", classeNomEnsemble, ";");
				auteurPageClasse.l();
				auteurPageClasse.l("/**");

				String hackathonMission = classeDoc.getString("hackathonMissionPage_stored_string");
				String hackathonColumn = classeDoc.getString("hackathonColumnPage_stored_string");
				String hackathonLabels = classeDoc.getString("hackathonLabelsPage_stored_string");
				if(hackathonMission != null)
					auteurPageClasse.l(String.format(" * Map.hackathonMission: %s", hackathonMission));
				if(hackathonColumn != null)
					auteurPageClasse.l(String.format(" * Map.hackathonColumn: %s", hackathonColumn));
				if(hackathonLabels != null)
					auteurPageClasse.l(String.format(" * Map.hackathonLabels: %s", hackathonLabels));

				String hackathonPageGenMission = classeDoc.getString("hackathonMissionPageGen_stored_string");
				String hackathonPageGenColumn = classeDoc.getString("hackathonColumnPageGen_stored_string");
				String hackathonPageGenLabels = classeDoc.getString("hackathonLabelsPageGen_stored_string");
				if(hackathonPageGenMission != null)
					auteurPageClasse.l(String.format(" * Map.hackathonMissionGen: %s", hackathonPageGenMission));
				if(hackathonPageGenColumn != null)
					auteurPageClasse.l(String.format(" * Map.hackathonColumnGen: %s", hackathonPageGenColumn));
				if(hackathonPageGenLabels != null)
					auteurPageClasse.l(String.format(" * Map.hackathonLabelsGen: %s", hackathonPageGenLabels));

				auteurPageClasse.l(" * ", langueConfig.getString(ConfigCles.var_Promesse), ": true");
				for(String langueNom2 : autresLangues) {
					YAMLConfiguration langueConfig2 = configurations.fileBased(YAMLConfiguration.class, String.format("%s/src/main/resources/org/computate/i18n/i18n_%s.yml", appComputate, classePageLangueNom));
					String classePageNomSimple2 = classeDoc.getString("classePageNomCanonique" + langueConfig2.getString(ConfigCles.var_PageRecherche)  + "_" + langueNom2 + "_stored_string");
					if(classePageNomSimple2 != null)
						auteurPageClasse.	l(" * ", langueConfig.getString(ConfigCles.var_NomCanonique), ".", langueNom2, ": ", classePageNomSimple2);
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
			{
				String hackathonMission = classeDoc.getString("hackathonMissionGenPage_stored_string");
				String hackathonColumn = classeDoc.getString("hackathonColumnGenPage_stored_string");
				String hackathonLabels = classeDoc.getString("hackathonLabelsGenPage_stored_string");
				if(hackathonMission != null)
					l(String.format(" * Map.hackathonMission: %s", hackathonMission));
				if(hackathonColumn != null)
					l(String.format(" * Map.hackathonColumn: %s", hackathonColumn));
				if(hackathonLabels != null)
					l(String.format(" * Map.hackathonLabels: %s", hackathonLabels));

				String hackathonGenPageGenMission = classeDoc.getString("hackathonMissionGenPageGen_stored_string");
				String hackathonGenPageGenColumn = classeDoc.getString("hackathonColumnGenPageGen_stored_string");
				String hackathonGenPageGenLabels = classeDoc.getString("hackathonLabelsGenPageGen_stored_string");
				if(hackathonGenPageGenMission != null)
					l(String.format(" * Map.hackathonMissionGen: %s", hackathonGenPageGenMission));
				if(hackathonGenPageGenColumn != null)
					l(String.format(" * Map.hackathonColumnGen: %s", hackathonGenPageGenColumn));
				if(hackathonGenPageGenLabels != null)
					l(String.format(" * Map.hackathonLabelsGen: %s", hackathonGenPageGenLabels));

			}
			l(" * ", langueConfig.getString(ConfigCles.var_Traduire), ": false");
			for(String langueNom2 : autresLangues) {
				YAMLConfiguration langueConfig2 = configurations.fileBased(YAMLConfiguration.class, String.format("%s/src/main/resources/org/computate/i18n/i18n_%s.yml", appComputate, classePageLangueNom));
				String classeGenPageNomSimple2 = classeDoc.getString("classeGenPageNomCanonique" + langueConfig2.getString(ConfigCles.var_PageRecherche)  + "_" + langueNom2 + "_stored_string");
				if(classeGenPageNomSimple2 != null)
					l(" * ", langueConfig.getString(ConfigCles.var_NomCanonique), ".", langueNom2, ": ", classeGenPageNomSimple2);
			}
			l(" * ", classeLangueConfig.getString(ConfigCles.str_Genere), ": true");
			l(" **/");
			s("public class ", classeGenPageNomSimple);
			s(" extends ", classeGenPageNomSimple, "Gen");
			s("<", (classePageSuperNomSimple == null ? "Object" : classePageSuperNomSimple), ">");
			l(" {");

			if(classePageSuperNomSimple == null) {
				l();
				tl(1, "/**");
				tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
				tl(1, "**/");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_requeteSite), "_(", "", classePartsCouverture.nomSimple(langueNom), "<", classePartsRequeteSite.nomSimple(langueNom), "> c", ") {");
				tl(1, "}");
			}

			if(!classePageSimple) {
				l();
				tl(1, "/**");
				tl(1, " * {@inheritDoc}");
				tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
				tl(1, " **/");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_(", classePartsCouverture.nomSimple(langueNom), "<", langueConfig.getString(ConfigCles.var_ListeRecherche), "<", classeApiClasseNomSimple, ">> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Reponse), "(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, "if(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_ != null)");
				tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(JsonObject.mapFrom(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse()).toString());");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _stats(", classePartsCouverture.nomSimple(langueNom), "<SolrResponse.Stats> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse().getStats());");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _facetCounts(", classePartsCouverture.nomSimple(langueNom), "<SolrResponse.FacetCounts> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse().getFacetCounts());");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pagination(JsonObject pagination) {");
				tl(2, "JsonArray pages = new JsonArray();");
				tl(2, "Long ", langueConfig.getString(ConfigCles.var_debut), " = ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getStart().longValue();");
				tl(2, "Long ", langueConfig.getString(ConfigCles.var_lignes), " = ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getRows().longValue();");
				tl(2, "Long ", langueConfig.getString(ConfigCles.var_numTrouve), " = ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse().getResponse().getNumFound().longValue();");
				tl(2, "Long ", langueConfig.getString(ConfigCles.var_debut), "Num = ", langueConfig.getString(ConfigCles.var_debut), " + 1L;");
				tl(2, "Long ", langueConfig.getString(ConfigCles.var_fin), "Num = ", langueConfig.getString(ConfigCles.var_debut), " + ", langueConfig.getString(ConfigCles.var_lignes), ";");
				tl(2, "Long floorMod = (", langueConfig.getString(ConfigCles.var_lignes), " == 0L ? 0L : Math.floorMod(", langueConfig.getString(ConfigCles.var_numTrouve), ", ", langueConfig.getString(ConfigCles.var_lignes), "));");
				tl(2, "Long ", langueConfig.getString(ConfigCles.var_dernier), " = (", langueConfig.getString(ConfigCles.var_lignes), " == 0L ? 0L : Math.floorDiv(", langueConfig.getString(ConfigCles.var_numTrouve), ", ", langueConfig.getString(ConfigCles.var_lignes), ") - (floorMod.equals(0L) ? 1L : 0L) * ", langueConfig.getString(ConfigCles.var_lignes), ");");
				tl(2, langueConfig.getString(ConfigCles.var_fin), "Num = ", langueConfig.getString(ConfigCles.var_fin), "Num < ", langueConfig.getString(ConfigCles.var_numTrouve), " ? ", langueConfig.getString(ConfigCles.var_fin), "Num : ", langueConfig.getString(ConfigCles.var_numTrouve), ";");
				tl(2, langueConfig.getString(ConfigCles.var_debut), "Num = ", langueConfig.getString(ConfigCles.var_numTrouve), " == 0L ? 0L : ", langueConfig.getString(ConfigCles.var_debut), "Num;");
				tl(2, "Long pagination", langueConfig.getString(ConfigCles.var_Debut), " = ", langueConfig.getString(ConfigCles.var_debut), " - 10L * ", langueConfig.getString(ConfigCles.var_lignes), ";");
				tl(2, "if(pagination", langueConfig.getString(ConfigCles.var_Debut), " < 0L)");
				tl(3, "pagination", langueConfig.getString(ConfigCles.var_Debut), " = 0L;");
				tl(2, "Long pagination", langueConfig.getString(ConfigCles.var_Fin), " = ", langueConfig.getString(ConfigCles.var_debut), " + 10L * ", langueConfig.getString(ConfigCles.var_lignes), ";");
				tl(2, "if(pagination", langueConfig.getString(ConfigCles.var_Fin), " > ", langueConfig.getString(ConfigCles.var_numTrouve), ")");
				tl(3, "pagination", langueConfig.getString(ConfigCles.var_Fin), " = ", langueConfig.getString(ConfigCles.var_numTrouve), ";");
				l();
				tl(2, "pagination.put(\"1L\", 1L);");
				tl(2, "pagination.put(\"0L\", 0L);");
				tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_debut), "\", ", langueConfig.getString(ConfigCles.var_debut), ");");
				tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_lignes), "\", ", langueConfig.getString(ConfigCles.var_lignes), ");");
				tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Precedent), "\", ", langueConfig.getString(ConfigCles.var_lignes), " / 2);");
				tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Prochaine), "\", ", langueConfig.getString(ConfigCles.var_lignes), " * 2);");
				tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_debut), "Num\", ", langueConfig.getString(ConfigCles.var_debut), "Num);");
				tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_fin), "Num\", ", langueConfig.getString(ConfigCles.var_fin), "Num);");
				tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_numTrouve), "\", ", langueConfig.getString(ConfigCles.var_numTrouve), ");");
				tl(2, "pagination.put(\"page", langueConfig.getString(ConfigCles.var_Debut), "\", new JsonObject().put(\"", langueConfig.getString(ConfigCles.var_debut), "\", 0L).put(\"page", langueConfig.getString(ConfigCles.var_Numero), "\", 1L));");
				tl(2, "if(", langueConfig.getString(ConfigCles.var_debut), " > 0L)");
				tl(3, "pagination.put(\"page", langueConfig.getString(ConfigCles.var_Precedent), "\", new JsonObject().put(\"", langueConfig.getString(ConfigCles.var_debut), "\", ", langueConfig.getString(ConfigCles.var_debut), " - ", langueConfig.getString(ConfigCles.var_lignes), ").put(\"page", langueConfig.getString(ConfigCles.var_Numero), "\", ", langueConfig.getString(ConfigCles.var_debut), " - ", langueConfig.getString(ConfigCles.var_lignes), " + 1L));");
				tl(2, "if(", langueConfig.getString(ConfigCles.var_debut), " + ", langueConfig.getString(ConfigCles.var_lignes), " < ", langueConfig.getString(ConfigCles.var_numTrouve), ")");
				tl(3, "pagination.put(\"page", langueConfig.getString(ConfigCles.var_Prochaine), "\", new JsonObject().put(\"", langueConfig.getString(ConfigCles.var_debut), "\", ", langueConfig.getString(ConfigCles.var_debut), " + ", langueConfig.getString(ConfigCles.var_lignes), ").put(\"page", langueConfig.getString(ConfigCles.var_Numero), "\", ", langueConfig.getString(ConfigCles.var_debut), " + ", langueConfig.getString(ConfigCles.var_lignes), " + 1L));");
				tl(2, "pagination.put(\"page", langueConfig.getString(ConfigCles.var_Fin), "\", new JsonObject().put(\"", langueConfig.getString(ConfigCles.var_debut), "\", ", langueConfig.getString(ConfigCles.var_dernier), ").put(\"page", langueConfig.getString(ConfigCles.var_Numero), "\", ", langueConfig.getString(ConfigCles.var_dernier), " + 1L));");
				tl(2, "pagination.put(\"pages\", pages);");
				tl(2, "for(Long i = pagination", langueConfig.getString(ConfigCles.var_Debut), "; i < pagination", langueConfig.getString(ConfigCles.var_Fin), "; i += ", langueConfig.getString(ConfigCles.var_lignes), ") {");
				tl(3, "Long page", langueConfig.getString(ConfigCles.var_Numero), " = Math.floorDiv(i, ", langueConfig.getString(ConfigCles.var_lignes), ") + 1L;");
				tl(3, "JsonObject page = new JsonObject();");
				tl(3, "page.put(\"page", langueConfig.getString(ConfigCles.var_Numero), "\", page", langueConfig.getString(ConfigCles.var_Numero), ");");
				tl(3, "page.put(\"", langueConfig.getString(ConfigCles.var_pageActuel), "\", ", langueConfig.getString(ConfigCles.var_debut), ".equals(i));");
				tl(3, "page.put(\"", langueConfig.getString(ConfigCles.var_debut), "\", i);");
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
				tl(2, classeNomSimple, ".varsQ", langueConfig.getString(ConfigCles.var_PourClasse), "().forEach(var -> {");
				tl(3, "JsonObject json = new JsonObject();");
				tl(3, "json.put(\"var\", var);");
				tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"val\", Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getQuery()).filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).map(s -> SearchTool.unescapeQueryChars(StringUtils.substringAfter(s, \":\"))).orElse(null));");
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
				tl(2, classeNomSimple, ".varsFq", langueConfig.getString(ConfigCles.var_PourClasse), "().forEach(var -> {");
				tl(3, "String var", langueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", langueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(var);");
				tl(3, "String var", langueConfig.getString(ConfigCles.var_Stocke), " = ", classeNomSimple, ".var", langueConfig.getString(ConfigCles.var_Stocke), classeNomSimple, "(var);");
				tl(3, "JsonObject json = new JsonObject();");
				tl(3, "json.put(\"var\", var);");
				tl(3, "json.put(\"var", langueConfig.getString(ConfigCles.var_Stocke), "\", var", langueConfig.getString(ConfigCles.var_Stocke), ");");
				tl(3, "json.put(\"var", langueConfig.getString(ConfigCles.var_Indexe), "\", var", langueConfig.getString(ConfigCles.var_Indexe), ");");
				tl(3, "String type = StringUtils.substringAfterLast(var", langueConfig.getString(ConfigCles.var_Indexe), ", \"_\");");
				tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"val\", ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getFilterQueries().stream().filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).findFirst().map(s -> SearchTool.unescapeQueryChars(StringUtils.substringAfter(s, \":\"))).orElse(null));");
				tl(3, "Optional.ofNullable(stats).map(s -> s.get(var", langueConfig.getString(ConfigCles.var_Indexe), ")).ifPresent(stat -> {");
				tl(4, "json.put(\"stats\", JsonObject.mapFrom(stat));");
				tl(3, "});");
	
				tl(3, "Optional.ofNullable(facetFields.get(var", langueConfig.getString(ConfigCles.var_Indexe), ")).ifPresent(facetField -> {");
				tl(4, "JsonObject facetJson = new JsonObject();");
				tl(4, "JsonObject counts = new JsonObject();");
				tl(4, "facetJson.put(\"var\", var);");
				tl(4, "facetField.getCounts().forEach((val, count) -> {");
				tl(5, "counts.put(val, count);");
				tl(4, "});");
				tl(4, "facetJson.put(\"counts\", counts);");
				tl(4, "json.put(\"facetField\", facetJson);");
				tl(3, "});");
	
				tl(3, "if(default", langueConfig.getString(ConfigCles.var_ListeChamps), "Vars.contains(var)) {");
				tl(4, "json.put(\"", langueConfig.getString(ConfigCles.var_listeChamps), "\", true);");
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
				tl(4, "json.put(\"", langueConfig.getString(ConfigCles.var_activer), langueConfig.getString(ConfigCles.var_Calendrier), "\", true);");
				tl(4, "setDefault", langueConfig.getString(ConfigCles.var_Gamme), langueConfig.getString(ConfigCles.var_Stats), "(json);");
				tl(3, "}");
				tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_activer), langueConfig.getString(ConfigCles.var_Stats), "\", !StringUtils.equalsAny(type, \"boolean\", \"location\"));");
				tl(3, "if(default", langueConfig.getString(ConfigCles.var_Stats), "Vars.contains(var)) {");
				tl(4, "SolrResponse.StatsField varStats = stats.get(var", langueConfig.getString(ConfigCles.var_Indexe), ");");
				tl(4, "if(varStats != null)");
				tl(5, "json.put(\"", langueConfig.getString(ConfigCles.var_stats), "\", varStats);");
				tl(3, "}");
	
				tl(3, "if(default", langueConfig.getString(ConfigCles.var_Pivot), "Vars.contains(var)) {");
				tl(4, "json.put(\"", langueConfig.getString(ConfigCles.var_pivot), "\", true);");
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
				tl(1, "protected void _vars", langueConfig.getString(ConfigCles.var_Gamme), "(JsonObject vars) {");
	//			tl(2, "Map<String, SolrResponse.Pivot> pivotFields = Optional.ofNullable(facetCounts).map(c -> c.getFacetPivot()).map(f -> f.getPivotMap()).orElse(new HashMap<String,SolrResponse.Pivot>());");
				tl(2, classeNomSimple, ".vars", langueConfig.getString(ConfigCles.var_Gamme), langueConfig.getString(ConfigCles.var_PourClasse), "().forEach(var -> {");
				tl(3, "String var", langueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", langueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(var);");
				tl(3, "JsonObject json = new JsonObject();");
				tl(3, "json.put(\"var\", var);");
				tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
				tl(3, "json.put(\"val\", ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getFilterQueries().stream().filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).findFirst().map(s -> SearchTool.unescapeQueryChars(StringUtils.substringAfter(s, \":\"))).orElse(null));");
	
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
				tl(2, "ServiceRequest ", langueConfig.getString(ConfigCles.var_requeteService), " = ", langueConfig.getString(ConfigCles.var_requeteSite), "_.getServiceRequest();");
				tl(2, "JsonObject params = ", langueConfig.getString(ConfigCles.var_requeteService), ".getParams();");
				l();
				tl(2, "JsonObject queryParams = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteService), ").map(ServiceRequest::getParams).map(or -> or.getJsonObject(\"query\")).orElse(new JsonObject());");
				tl(2, "Long num = ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse().getResponse().getNumFound().longValue();");
				tl(2, "String q = \"*:*\";");
				tl(2, "String q1 = \"", classeVarTexte, "\";");
				tl(2, "String q2 = \"\";");
				tl(2, "for(String param", langueConfig.getString(ConfigCles.var_Nom), " : queryParams.fieldNames()) {");
				tl(3, "String ", langueConfig.getString(ConfigCles.var_entite), "Var = null;");
				tl(3, "String ", langueConfig.getString(ConfigCles.var_valeur), langueConfig.getString(ConfigCles.var_Indexe), " = null;");
				tl(3, "Object param", langueConfig.getString(ConfigCles.var_ValeursObjet), " = queryParams.getValue(param", langueConfig.getString(ConfigCles.var_Nom), ");");
				tl(3, "JsonArray param", langueConfig.getString(ConfigCles.var_Objets), " = param", langueConfig.getString(ConfigCles.var_ValeursObjet), " instanceof JsonArray ? (JsonArray)param", langueConfig.getString(ConfigCles.var_ValeursObjet), " : new JsonArray().add(param", langueConfig.getString(ConfigCles.var_ValeursObjet), ");");
				l();
				tl(3, "try {");
				tl(4, "for(Object param", langueConfig.getString(ConfigCles.var_Objet), " : param", langueConfig.getString(ConfigCles.var_Objets), ") {");
				tl(5, "switch(param", langueConfig.getString(ConfigCles.var_Nom), ") {");
				tl(5, "case \"q\":");
				tl(6, "q = (String)param", langueConfig.getString(ConfigCles.var_Objet), ";");
				tl(6, langueConfig.getString(ConfigCles.var_entite), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", langueConfig.getString(ConfigCles.var_Objet), ", \":\"));");
				tl(6, langueConfig.getString(ConfigCles.var_valeur), langueConfig.getString(ConfigCles.var_Indexe), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", langueConfig.getString(ConfigCles.var_Objet), ", \":\")), \"UTF-8\");");
				tl(6, "q1 = ", langueConfig.getString(ConfigCles.var_entite), "Var.equals(\"*\") ? q1 : ", langueConfig.getString(ConfigCles.var_entite), "Var;");
				tl(6, "q2 = ", langueConfig.getString(ConfigCles.var_valeur), langueConfig.getString(ConfigCles.var_Indexe), ";");
				tl(6, "q = q1 + \":\" + q2;");
				tl(5, "}");
				tl(4, "}");
				tl(3, "} catch(Exception e) {");
				tl(4, "ExceptionUtils.rethrow(e);");
				tl(3, "}");
				tl(2, "}");
				tl(2, "query.put(\"q\", q);");
				l();
				tl(2, "Long rows1 = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getRows()).orElse(10L);");
				tl(2, "Long start1 = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getStart()).orElse(1L);");
				tl(2, "Long start2 = start1 - rows1;");
				tl(2, "Long start3 = start1 + rows1;");
				tl(2, "Long rows2 = rows1 / 2;");
				tl(2, "Long rows3 = rows1 * 2;");
				tl(2, "start2 = start2 < 0 ? 0 : start2;");
				tl(2, "JsonObject fqs = new JsonObject();");
				tl(2, "for(String fq : Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getFilterQueries()).orElse(Arrays.asList())) {");
				tl(3, "if(!StringUtils.contains(fq, \"(\")) {");
				tl(4, "String fq1 = ", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_recherche), "Var", classeNomSimple, "(StringUtils.substringBefore(fq, \":\"));");
				tl(4, "String fq2 = StringUtils.substringAfter(fq, \":\");");
				tl(4, "if(!StringUtils.startsWithAny(fq, \"", langueConfig.getString(ConfigCles.var_classeNomsCanoniques), "_\", \"", langueConfig.getString(ConfigCles.var_archive), "_\", \"", langueConfig.getString(ConfigCles.var_supprime), "_\", \"sessionId\", \"", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s\"))");
				tl(5, "fqs.put(fq1, new JsonObject().put(\"var\", fq1).put(\"val\", fq2).put(\"", langueConfig.getString(ConfigCles.var_nomAffichage), "\", ", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_nomAffichage), langueConfig.getString(ConfigCles.var_PourClasse), "(fq1)));");
				tl(4, "}");
				tl(3, "}");
				tl(2, "query.put(\"fq\", fqs);");
				l();
				tl(2, "JsonArray sorts = new JsonArray();");
				tl(2, "for(String sort : Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getSorts()).orElse(Arrays.asList())) {");
				tl(3, "String sort1 = ", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_recherche), "Var", classeNomSimple, "(StringUtils.substringBefore(sort, \" \"));");
				tl(3, "sorts.add(new JsonObject().put(\"var\", sort1).put(\"order\", StringUtils.substringAfter(sort, \" \")).put(\"", langueConfig.getString(ConfigCles.var_nomAffichage), "\", ", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_nomAffichage), langueConfig.getString(ConfigCles.var_PourClasse), "(sort1)));");
				tl(2, "}");
				tl(2, "query.put(\"sort\", sorts);");
				tl(1, "}");
				if(classePageSuperNomSimple != null && (classeEtendBase || !classeModele)) {
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultZoneId(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Requete), "Vars().get(VAR_defaultZoneId)).orElse(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".SITE_ZONE)));");
					tl(1, "}");
					l();
					tl(1, "/**");
					tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
					tl(1, " **/");
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultTimeZone(", classePartsCouverture.nomSimple(langueNom), "<ZoneId> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(ZoneId.of(defaultZoneId));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultLocaleId(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getRequestHeaders().get(\"Accept-Language\")).map(acceptLanguage -> StringUtils.substringBefore(acceptLanguage, \",\")).orElse(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".SITE_LOCALE)));");
					tl(1, "}");
					l();
					tl(1, "/**");
					tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
					tl(1, " **/");
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultLocale(", classePartsCouverture.nomSimple(langueNom), "<Locale> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Locale.forLanguageTag(defaultLocaleId));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _rows(", classePartsCouverture.nomSimple(langueNom), "<Long> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, "if(", langueConfig.getString(ConfigCles.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"rows\", null) != null)");
					tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getRows());");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _start(", classePartsCouverture.nomSimple(langueNom), "<Long> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, "if(", langueConfig.getString(ConfigCles.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"start\", null) != null)");
					tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getStart());");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _rangeGap(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, "if(", langueConfig.getString(ConfigCles.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"facet.range.gap\", null) != null)");
					tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeGap()).orElse(null));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _rangeEnd(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, "if(", langueConfig.getString(ConfigCles.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"facet.range.end\", null) != null)");
					tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeEnd()).map(s -> TimeTool.parseZonedDateTime(defaultTimeZone, s)).orElse(null));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _rangeStart(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, "if(", langueConfig.getString(ConfigCles.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"facet.range.start\", null) != null)");
					tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeStart()).map(s -> TimeTool.parseZonedDateTime(defaultTimeZone, s)).orElse(null));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultRangeGap(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(rangeGap).orElse(Optional.ofNullable(defaultRangeStats).map(s -> s.getString(\"defaultRangeGap\")).orElse(\"+1DAY\")));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultRangeEnd(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(rangeEnd).orElse(Optional.ofNullable(defaultRangeStats).map(s -> Instant.parse(s.getString(\"defaultRangeEnd\")).atZone(defaultTimeZone)).orElse(ZonedDateTime.now(defaultTimeZone).toLocalDate().atStartOfDay(defaultTimeZone).plusDays(1))));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultRangeStart(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(rangeStart).orElse(Optional.ofNullable(defaultRangeStats).map(s -> Instant.parse(s.getString(\"defaultRangeStart\")).atZone(defaultTimeZone)).orElse(defaultRangeEnd.minusDays(7).toLocalDate().atStartOfDay(defaultTimeZone))));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultRangeVar(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRanges()).orElse(Optional.ofNullable(defaultRangeStats).map(s -> Arrays.asList(s.getString(\"defaultRangeVar\"))).orElse(Arrays.asList())).stream().findFirst().map(v -> { if(v.contains(\"}\")) return StringUtils.substringBefore(StringUtils.substringAfterLast(v, \"}\"), \"_\"); else return ", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_recherche), "Var", classeNomSimple, "(v); }).orElse(\"", classeVarCree, "\"));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultFacetSort(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetSort()).orElse(\"index\"));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultFacetLimit(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetLimit()).orElse(1));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultFacetMinCount(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetMinCount()).orElse(1));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultPivotMinCount(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetPivotMinCount()).orElse(0));");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _DEFAULT_MAP_LOCATION(", classePartsCouverture.nomSimple(langueNom), "<JsonObject> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, "String pointStr = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Requete), "Vars().get(VAR_DEFAULT_MAP_LOCATION)).orElse(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".DEFAULT_MAP_LOCATION));");
					tl(2, "if(pointStr != null) {");
					tl(3, "String[] parts = pointStr.replace(\"[\", \"\").replace(\"]\", \"\").replace(\"\\\"\", \"\").split(\",\");");
					tl(3, "JsonObject point = new JsonObject().put(\"lat\", Double.parseDouble(parts[0])).put(\"lon\", Double.parseDouble(parts[1]));");
					tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(point);");
					tl(2, "}");
					tl(1, "}");
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _DEFAULT_MAP_ZOOM(", classePartsCouverture.nomSimple(langueNom), "<BigDecimal> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, "String s = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Requete), "Vars().get(VAR_DEFAULT_MAP_ZOOM)).orElse(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".DEFAULT_MAP_ZOOM));");
					tl(2, "if(s != null)");
					tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(new BigDecimal(s));");
					tl(1, "}");
				} else {
					l();
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(1, "protected void _defaultRangeStats(", classePartsCouverture.nomSimple(langueNom), "<JsonObject> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(1, "}");
				}
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _default", langueConfig.getString(ConfigCles.var_ListeChamps), "Vars(List<String> l) {");
				tl(2, "Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFields()).orElse(Arrays.asList()).forEach(var", langueConfig.getString(ConfigCles.var_Stocke), " -> {");
				tl(3, "String var", langueConfig.getString(ConfigCles.var_Stocke), "2 = var", langueConfig.getString(ConfigCles.var_Stocke), ";");
				tl(3, "if(StringUtils.contains(var", langueConfig.getString(ConfigCles.var_Stocke), "2, \"}\"))");
				tl(4, "var", langueConfig.getString(ConfigCles.var_Stocke), "2 = StringUtils.substringAfterLast(var", langueConfig.getString(ConfigCles.var_Stocke), "2, \"}\");");
				tl(3, "String[] parts = var", langueConfig.getString(ConfigCles.var_Stocke), "2.split(\",\");");
				tl(3, "for(String part : parts) {");
				tl(4, "if(StringUtils.isNotBlank(part)) {");
				tl(5, "String var = ", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_recherche), "Var", classeNomSimple, "(part);");
				tl(5, "if(StringUtils.isNotBlank(var))");
				tl(6, "l.add(var);");
				tl(4, "}");
				tl(3, "}");
				tl(2, "});");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _default", langueConfig.getString(ConfigCles.var_Stats), "Vars(List<String> l) {");
				tl(2, "Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getStatsFields()).orElse(Arrays.asList()).forEach(var", langueConfig.getString(ConfigCles.var_Indexe), " -> {");
				tl(3, "String var", langueConfig.getString(ConfigCles.var_Indexe), "2 = var", langueConfig.getString(ConfigCles.var_Indexe), ";");
				tl(3, "if(StringUtils.contains(var", langueConfig.getString(ConfigCles.var_Indexe), "2, \"}\"))");
				tl(4, "var", langueConfig.getString(ConfigCles.var_Indexe), "2 = StringUtils.substringAfterLast(var", langueConfig.getString(ConfigCles.var_Indexe), "2, \"}\");");
				tl(3, "String[] parts = var", langueConfig.getString(ConfigCles.var_Indexe), "2.split(\",\");");
				tl(3, "for(String part : parts) {");
				tl(4, "if(StringUtils.isNotBlank(part)) {");
				tl(5, "String var = ", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_recherche), "Var", classeNomSimple, "(part);");
				tl(5, "if(StringUtils.isNotBlank(var))");
				tl(6, "l.add(var);");
				tl(4, "}");
				tl(3, "}");
				tl(2, "});");
				tl(1, "}");
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _default", langueConfig.getString(ConfigCles.var_Pivot), "Vars(List<String> l) {");
				tl(2, "Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetPivots()).orElse(Arrays.asList()).forEach(facetPivot -> {");
				tl(3, "String facetPivot2 = facetPivot;");
				tl(3, "if(StringUtils.contains(facetPivot2, \"}\"))");
				tl(4, "facetPivot2 = StringUtils.substringAfterLast(facetPivot2, \"}\");");
				tl(3, "String[] parts = facetPivot2.split(\",\");");
				tl(3, "for(String part : parts) {");
				tl(4, "if(StringUtils.isNotBlank(part)) {");
				tl(5, "String var = ", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_recherche), "Var", classeNomSimple, "(part);");
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
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, "(JsonArray l) {");
				tl(2, "Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_).map(o -> o.get", langueConfig.getString(ConfigCles.var_Liste), "()).orElse(Arrays.asList()).stream().map(o -> JsonObject.mapFrom(o)).forEach(o -> l.add(o));");
				tl(1, "}");
				l();
				tl(1, "protected void _", uncapitalizeClasseApiClasseNomSimple, "Count(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_ == null ? 0 : ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.size());");
				tl(1, "}");
			}
			l();
			tl(1, "protected void _", uncapitalizeClasseApiClasseNomSimple, "_(", "", classePartsCouverture.nomSimple(langueNom), "<", classeApiClasseNomSimple, "> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
			if(classePageSimple) {
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(new ", classeApiClasseNomSimple, "());");
			} else {
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "Count == 1 && Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_RequeteService), "().getParams().getJsonObject(\"path\")).map(o -> o.getString(\"id\")).orElse(null) != null)");
				tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.get(0));");
			}
			tl(1, "}");
			if(!classePageSimple) {
				if(classeModele) {
					l();
					tl(1, "protected void _", classeModele ? classeVarClePrimaire : classeVarCleUnique, "(", classePartsCouverture.nomSimple(langueNom), "<Long> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
					tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "_ != null)");
					tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarClePrimaire), "());");
					tl(1, "}");
				}
				l();
				tl(1, "protected void _", classeVarCleUnique, "(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "_ != null)");
				tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarCleUnique), "());");
				tl(1, "}");
			}

			l();
			if(classePageSuperNomSimple != null) {
				tl(1, "@Override");
			} else {
				tl(1, "/**");
				tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
				tl(1, "**/");
			}
			tl(1, "protected void _promise", langueConfig.getString(ConfigCles.var_Avant), "(Promise<Void> promise) {");
			tl(2, "promise.complete();");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _", classePageLangueConfig.getString(ConfigCles.var_classeNomSimple), "(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
			tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(\"", classeApiClasseNomSimple, "\");");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _page", langueConfig.getString(ConfigCles.var_Titre), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
			if(classeVarTitre != null) {
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "_ != null && ", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarTitre), "() != null)");
				tl(3, "c.o(", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarTitre), "()", ");");
				tl(2, "else if(", uncapitalizeClasseApiClasseNomSimple, "_ != null)");
			} else {
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "_ != null)");
			}
			tl(3, "c.o(", q(classeTitre), ");");
			if(!classePageSimple) {
				tl(2, "else if(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_ == null || ", uncapitalizeClasseApiClasseNomSimple, "Count == 0)");
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
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _", langueConfig.getString(ConfigCles.var_roles), "(List<String> l) {");
			tl(2, "if(", langueConfig.getString(ConfigCles.var_requeteSite), "_ != null) {");
			tl(3, "l.addAll(Stream.concat(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "().stream(), ", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "().stream()).distinct().collect(Collectors.toList()));");
			tl(2, "}");
			tl(1, "}");
			if(classeRolesTrouves) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_rolePourEcrire), "(List<String> l) {");
				tl(2, "Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_AUTH_ROLE_REQUIS), " + \"_", classeApiClasseNomSimple, "\")).ifPresent(v -> {");
				tl(3, "l.add(v);");
				tl(2, "});");
				tl(1, "}");
			}
			if(classeRoleLiresTrouves) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_rolePourLire), "(List<String> l) {");
				tl(2, "Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_AUTH_ROLE_LIRE_REQUIS), " + \"_", classeApiClasseNomSimple, "\")).ifPresent(v -> {");
				tl(3, "l.add(v);");
				tl(2, "});");
				tl(2, "Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_AUTH_ROLE_REQUIS), " + \"_", classeApiClasseNomSimple, "\")).ifPresent(v -> {");
				tl(3, "l.add(v);");
				tl(2, "});");
				tl(1, "}");
			}

			l();
			if(classePageSuperNomSimple != null) {
				tl(1, "@Override");
			} else {
				tl(1, "/**");
				tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
				tl(1, "**/");
			}
			tl(1, "protected void _promise", langueConfig.getString(ConfigCles.var_Apres), "(Promise<Void> promise) {");
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
				tl(1, "protected void _pageImage", langueConfig.getString(ConfigCles.var_Largeur), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
				tl(3, "c.o(", classeImageLargeur, ");");
				tl(1, "}");
			}

			if(classeImageHauteur != null) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pageImage", langueConfig.getString(ConfigCles.var_Hauteur), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
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

			if(StringUtils.isNotBlank(classeIconeGroupe)) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_classe), langueConfig.getString(ConfigCles.var_Icone), langueConfig.getString(ConfigCles.var_Groupe), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(classeIconeGroupe), ");");
				tl(1, "}");
			}

			if(StringUtils.isNotBlank(classeIconeNom)) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_classe), langueConfig.getString(ConfigCles.var_Icone), langueConfig.getString(ConfigCles.var_Nom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(classeIconeNom), ");");
				tl(1, "}");
			}
//
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

	public void pageCodeClasseHbs(String langueNom, YAMLConfiguration langueConfig) throws Exception {

		if(classePageCheminsGen.contains(classeGenPageChemin) && classeGenPageChemin != null && StringUtils.equals(classePageLangueNom, langueNom)) {

			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + classesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
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
							Boolean entiteFacetsTrouves = Optional.ofNullable((Boolean)entiteDocumentSolr.get("entiteFacetsTrouves_stored_boolean")).orElse(false);
							List<String> entiteFacets = Optional.ofNullable((List<String>)entiteDocumentSolr.get("entiteFacets_stored_strings")).orElse(Arrays.asList());
							if(entiteHtml) {
								String jsVal = ".val()";
								if("Boolean".equals(entiteNomSimple)) {
									jsVal = ".prop('checked')";
								}
//	
//								wGetters.l();
//								wGetters.tl(1, "public Boolean get", langueConfig.getString(ConfigCles.var_Colonne), entiteVarCapitalise, "() {");
//								wGetters.tl(2, "return true;");
//								wGetters.tl(1, "}");

								wTh.tl(6, "<th>", entiteNomAffichage, "</th>");
	
//								wTd.tl(4, "{{#if get", langueConfig.getString(ConfigCles.var_Colonne), entiteVarCapitalise, "}}");
								wTd.tl(6, "<td>");
								wTd.tl(7, "<a href=\"{{", classeVarUrlPk, "}}\">");
								if(classeIconeGroupe != null && classeIconeNom != null && BooleanUtils.isTrue(entiteVarTitre))
									wTd.tl(8, "<i class=\"fa", StringUtils.substring(classeIconeGroupe, 0, 1), " fa-", classeIconeNom, " \"></i>");
								wTd.t(8, "<span class=\"white-space-pre-wrap \">");
								if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
									wTd.s("{{siteZonedDateTimeFormat ", entiteVar, " \"", entiteFormatHtm, "\" siteLocale}}");
								} else if(StringUtils.equals(entiteNomCanonique, LocalDate.class.getCanonicalName())) {
									wTd.s("{{siteLocalDateFormat ", entiteVar, " \"", entiteFormatHtm, "\" siteLocale}}");
								} else if(StringUtils.equals(entiteNomCanonique, LocalDateTime.class.getCanonicalName())) {
									wTd.s("{{siteLocalDateTimeFormat ", entiteVar, " \"", entiteFormatHtm, "\" siteLocale}}");
								} else if(StringUtils.equals(entiteNomCanonique, LocalTime.class.getCanonicalName())) {
									wTd.s("{{siteLocalTimeFormat ", entiteVar, " \"", entiteFormatHtm, "\" siteLocale}}");
								} else if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())) {
									wTd.s("{{siteNumberFormat ", entiteVar, " \"", entiteFormatHtm, "\" siteLocale}}");
								} else {
									wTd.s("{{", entiteVar, "}}");
								}
								wTd.l("</span>");
								wTd.tl(7, "</a>");
								if(entiteHighlighting) {
									wTd.tl(7, "{{#if highlightList}}");
									wTd.tl(8, "<div class=\"site-highlight \">");
										wTd.tl(9, "StringUtils.join(highlightList, \" ... \")");
									wTd.tl(8, "</div>");
									wTd.tl(7, "{{/if}}");
								}
								wTd.tl(6, "</td>");
//								wTd.tl(4, "{{/if}}");
							}

							wFoot.tl(3, "{{#if get", langueConfig.getString(ConfigCles.var_Colonne), entiteVarCapitalise, "}}");
							wFoot.tl(4, "<td>");
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
							wFoot.tl(4, "</td>");
							wFoot.tl(3, "{{/if}}");
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

//									String jsVal = ".val()";

							if(entiteIndexe) {
	
								wRecherche.l();
								if("Boolean".equals(entiteNomSimple)) {
									wRecherche.tl(2, "var $", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "Checkbox = $", langueConfig.getString(ConfigCles.var_formulaireFiltres), ".find('input.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "[type = \"checkbox\"]');");
									wRecherche.tl(2, "var $", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "Select = $", langueConfig.getString(ConfigCles.var_formulaireFiltres), ".find('select.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "');");
									wRecherche.tl(2, "var ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "Select.length ? $", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "Select.val() : $", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "Checkbox.prop('checked');");

									wRecherche.tl(2, "var ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "SelectVal = $", langueConfig.getString(ConfigCles.var_formulaireFiltres), ".find('select.", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "').val();");
									wRecherche.tl(2, "var ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " = null;");
									wRecherche.tl(2, "if(", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "SelectVal !== '')");
									wRecherche.tl(3, langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " = ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "SelectVal == 'true';");
								}
								else {
									wRecherche.tl(2, "var ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireFiltres), ".find('.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "').val();");
								}

								if("Boolean".equals(entiteNomSimple))
									wRecherche.tl(2, "if(", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " === true)");
								else
									wRecherche.tl(2, "if(", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " !== '')");

								wRecherche.tl(3, langueConfig.getString(ConfigCles.var_filtres), ".push({ name: '", (entiteSuggere ? "q" : "fq"), "', value: '", entiteVar, ":' + ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " });");

								wVarsFqJs.tl(1, "vars.push({ var: '", entiteVar, "', "
										, "var", langueConfig.getString(ConfigCles.var_Indexe), ": '", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "'"
										, ", ", langueConfig.getString(ConfigCles.var_nomAffichage), ": ", entiteNomAffichage == null ? "null" : "'" + entiteNomAffichage + "'", "});");
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
										wPOST.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "').val();");
										wPOST.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " !== '')");
										if("Boolean".equals(entiteNomSimple)) {
											wPOST.tl(2, "vals['", entiteVar, "'] = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " == 'true';");
										} else {
											wPOST.tl(2, "vals['", entiteVar, "'] = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ";");
										}
									}
									else {
										wPOST.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = [];");
										wPOST.tl(1, "$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('input.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ":checked').each(function(index) {");
										wPOST.tl(2, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ".push($(this).val());");
										wPOST.tl(1, "});");
										wPOST.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ".length > 0)");
										wPOST.tl(2, "vals['", entiteVar, "'] = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ";");
									}
								}
								else {
									wPOST.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "').val();");
									wPOST.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " !== '')");
									if("Boolean".equals(entiteNomSimple)) {
										wPOST.tl(2, "vals['", entiteVar, "'] = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " == 'true';");
									} else if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson)) {
										wPOST.tl(2, "vals['", entiteVar, "'] = JSON.parse(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ");");
									} else {
										wPOST.tl(2, "vals['", entiteVar, "'] = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ";");
									}
								}
	
								wPUTCopie.l();
								if(entiteAttribuer)
									wPUTCopie.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('input.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ":checked').val();");
								else
									wPUTCopie.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "').val();");
								if(entiteAttribuer) {
									wPUTCopie.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, langueConfig.getString(ConfigCles.var_Vider), " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('input.", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), ":checked').val();");
									wPUTCopie.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, langueConfig.getString(ConfigCles.var_Vider), " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, langueConfig.getString(ConfigCles.var_Vider), ")");
									wPUTCopie.tl(2, "vals['", entiteVar, "'] = null;");
									wPUTCopie.tl(1, "else if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ")");
									if(entiteListeTypeJson == null) {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									}
									else {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = [", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, "];");
									}
								} else {
									wPUTCopie.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " !== '')");
									if("Boolean".equals(entiteNomSimple)) {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, " == 'true';");
									} else if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson)) {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = JSON.parse(", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, ");");
									} else {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									}
								}
	
								wPATCH.l();
								if(entiteAttribuer)
									wPATCH.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('input.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ":checked').val();");
								else
									wPATCH.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "').val();");
								if(entiteAttribuer) {
									wPATCH.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " !== '')");
									if(entiteListeTypeJson == null) {
										wPATCH.tl(2, "vals['set", entiteVarCapitalise, "'] = ", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									} else {
										wPATCH.tl(2, "vals['add", entiteVarCapitalise, "'] = ", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									}
								} else {

									wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.remove", entiteVarCapitalise, "').val() === 'true';");

									if("Boolean".equals(entiteNomSimple)) {
										wPATCH.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "SelectVal = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('select.set", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "SelectVal != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "SelectVal !== '')");
										wPATCH.tl(2, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "SelectVal == 'true';");
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ";");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.add", entiteVarCapitalise, "').prop('checked');");
									}
									else if("LocalDate".equals(entiteNomSimple)) {
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.set", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.add", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "var setMoment = set", entiteVarCapitalise, valSuffixe, " ? moment(", valPrefixe, "set", entiteVarCapitalise, valSuffixe, ", '", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "') : null; ");
										wPATCH.tl(1, "var addMoment = add", entiteVarCapitalise, valSuffixe, " ? moment(", valPrefixe, "add", entiteVarCapitalise, valSuffixe, ", '", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "') : null; ");
										wPATCH.tl(1, "if(setMoment) { ");
											wPATCH.tl(2, "var s = setMoment.format('YYYY-MM-DD'); ");
											wPATCH.tl(2, "set", entiteVarCapitalise, " = s;");
										wPATCH.tl(1, "} ");
										wPATCH.tl(1, "if(addMoment) { ");
											wPATCH.tl(2, "var s = addMoment.format('YYYY-MM-DD'); ");
											wPATCH.tl(2, "add", entiteVarCapitalise, " = s;");
										wPATCH.tl(1, "} ");
									}
//												else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
//													t(tIndex + 3).s(classePrefixe, "<input").l();
//													t(tIndex + 5).dal("type", "text");
//													t(tIndex + 5).s(classePrefixe).s(" class=\"\"w3-input w3-border datepicker set", entiteVarCapitalise, " class", classeApiClasseNomSimple, " input", classeApiClasseNomSimple, "\", ", classeModele ? classeVarClePrimaire : classeVarCleUnique, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\"");
//													t(tIndex + 5).dal("placeholder", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY_HHColonMM));
//													t(tIndex + 5).dal("data-timeformat", langueConfig.getString(ConfigCles.var_ddDashMMDashyyyy));
//													t(tIndex + 5).l(" id=\"", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "\"");
//													if(entiteDescription != null)
//														t(tIndex + 5).dal("title", entiteDescription + " (", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), ")");
//													tl(tIndex + 4, " value=\"", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz_VV), "\".format(", entiteVar, "));");
//													t(tIndex + 3).l("if(\"Page\".equals(", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ")) {");
//													t(tIndex + 4).l("a(\"onclick\", \"", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); \");");
//													t(tIndex + 4).s("a(\"onchange\", \"");
//														s("var t = moment(this.value, '", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "'); ");
//														s("if(t) { ");
//															s("var s = t.format('YYYY-MM-DD'); ");
//															s("patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeModele ? classeVarClePrimaire : classeVarCleUnique, ", \"' }], 'set", entiteVarCapitalise, "', s, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#\", ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#\", ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "')); }); ");
//														s("} ");
//													l("\");");
//													t(tIndex + 3).l("}");
//													tl(tIndex + 3, "/>");
//												}
//												else if("LocalTime".equals(entiteNomSimple)) {
//													t(tIndex + 3).s(classePrefixe, "<input").l();
//													t(tIndex + 5).dal("type", "text");
//													t(tIndex + 5).s(classePrefixe).s(" class=\"\"w3-input w3-border timepicker set", entiteVarCapitalise, " class", classeApiClasseNomSimple, " input", classeApiClasseNomSimple, "\", ", classeModele ? classeVarClePrimaire : classeVarCleUnique, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\"");
//													t(tIndex + 5).dal("placeholder", langueConfig.getString(ConfigCles.var_HHColonMM));
//													t(tIndex + 5).l(" id=\"", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "\"");
//													if(entiteDescription != null)
//														t(tIndex + 5).da("title", entiteDescription + " (", langueConfig.getString(ConfigCles.var_HAposhAposmm), ")");
//													tl(tIndex + 5, " value=\"", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_HAposhAposmm), "\".format(", entiteVar, "));");
//													t(tIndex + 3).l("if(\"Page\".equals(", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ")) {");
//													t(tIndex + 4).l("a(\"onclick\", \"", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); \");");
//													t(tIndex + 4).s("a(\"onchange\", \"");
//														s("var t = moment(this.value, '", langueConfig.getString(ConfigCles.var_HAposhAposmm), "'); ");
//														s("if(t) { ");
//															s("var s = t.format('HH:mm'); ");
//															s("patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeModele ? classeVarClePrimaire : classeVarCleUnique, ", \"' }], 'set", entiteVarCapitalise, "', s, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#\", ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#\", ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "')); }); ");
//														s("} ");
//													l("\");");
//													t(tIndex + 3).l("}");
//													tl(tIndex + 3, "/>");
//												}
									else {
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.set", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.add", entiteVarCapitalise, "').val();");
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
										wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.remove", entiteVarCapitalise, "').prop('checked');");
									} else {
										wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.remove", entiteVarCapitalise, "').val();");
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

			if(auteurPageHbs != null) {
				o = auteurPageHbs;
				if((classeEtendBase || !classeModele && (classePartsModeleBase == null || !classeNomCanonique.equals(classePartsModeleBase.nomCanonique(langueNom)))) && auteurPageHbs != null) {
					String hackathonMission = classeDoc.getString("hackathonMissionPageHbs_stored_string");
					String hackathonColumn = classeDoc.getString("hackathonColumnPageHbs_stored_string");
					String hackathonLabels = classeDoc.getString("hackathonLabelsPageHbs_stored_string");
					if(hackathonMission != null || hackathonColumn != null || hackathonLabels != null) {
						l("<!--");
						if(hackathonMission != null)
							l(String.format("hackathonMission: %s", hackathonMission));
						if(hackathonColumn != null)
							l(String.format("hackathonColumn: %s", hackathonColumn));
						if(hackathonLabels != null)
							l(String.format("hackathonLabels: %s", hackathonLabels));
						l("-->");
					}
					l("{{#eq classSimpleName \"", classeNomSimple, "\"}}");
					l("{{#partial \"htmHead\"}}{{> htmHead", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmTitle\"}}{{> htmTitle", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmMeta\"}}{{> htmMeta", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmStyle\"}}{{> htmStyle", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmScripts\"}}{{> htmScripts", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmScript\"}}{{> htmScript", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"websocket\"}}{{> websocket", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"", langueConfig.getString(ConfigCles.var_jsWebsocket), "\"}}{{> ", langueConfig.getString(ConfigCles.var_jsWebsocket), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"", langueConfig.getString(ConfigCles.var_jsInfobulle), "\"}}{{> ", langueConfig.getString(ConfigCles.var_jsInfobulle), "", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"", langueConfig.getString(ConfigCles.var_htmInfobulle), "\"}}{{> ", langueConfig.getString(ConfigCles.var_htmInfobulle), "", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"", langueConfig.getString(ConfigCles.var_jsStyle), "\"}}{{> ", langueConfig.getString(ConfigCles.var_jsStyle), "", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBodySidebar\"}}{{> htmBodySidebar", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Debut), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Debut), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Milieu), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Milieu), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Fin), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Fin), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody\"}}{{> htmBody", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Recherche), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Recherche), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Filtres), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Filtres), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Gamme), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Gamme), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Pivot), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Pivot), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_ListeChamps), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_ListeChamps), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Stats), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Stats), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Menu), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Menu), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Graphique), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBodyCount0\"}}{{> htmBodyCount0", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBodyCount1", langueConfig.getString(ConfigCles.var_Tous), "\"}}{{> htmBodyCount1", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "}}{{/partial}}");
					if(classeNomCanonique.equals(classePartsUtilisateurSite.nomCanonique(classeLangueNom))) {
						l("{{#partial \"htmBodyCount", langueConfig.getString(ConfigCles.var_Tous), "\"}}");
						tl(0, "<div class=\"w3-margin-top \"");
						tl(1, "<h3>Manage username and password</h3>");
						tl(1, "<div class=\"w3-margin \">");
						tl(2, "<a target=\"_blank\" class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray \" href=\"{{ siteAuthUrl }}/realms/{{ siteAuthRealm }}/account\">Manage user profile</a>");
						tl(1, "</div>");
						tl(0, "</div>");
						l("{{/partial}}");
						l("{{#partial \"htmBodyCount1\"}}");
						tl(0, "<div class=\"w3-margin-top \"");
						tl(1, "<h3>Manage username and password</h3>");
						tl(1, "<div class=\"w3-margin \">");
						tl(2, "<a target=\"_blank\" class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray \" href=\"{{ siteAuthUrl }}/realms/{{ siteAuthRealm }}/account\">Manage user profile</a>");
						tl(1, "</div>");
						tl(0, "</div>");
						l("{{/partial}}");
					} else {
						l("{{#partial \"htmBodyCount1\"}}{{> htmBodyCount1", classePageNomSimple, "}}{{/partial}}");
					}
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Tous), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "\"}}{{> htm", langueConfig.getString(ConfigCles.var_Formulaire), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htm", langueConfig.getString(ConfigCles.var_Formulaires), "\"}}{{> htm", langueConfig.getString(ConfigCles.var_Formulaires), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Suggere), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Suggere), classePageNomSimple, "}}{{/partial}}");
					for(String classeApiMethode : classeApiMethodes) {
						String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");
		
						if(classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PageRecherche))) {
							l("{{#partial \"htm", langueConfig.getString(ConfigCles.var_Bouton), "_", classeApiOperationIdMethode, "\"}}{{> htm", langueConfig.getString(ConfigCles.var_Bouton), classePageNomSimple, "_", classeApiOperationIdMethode, " classApiMethodMethod=\"Page\"}}{{/partial}}");
							l("{{#partial \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "_", classeApiOperationIdMethode, "\"}}{{> htm", langueConfig.getString(ConfigCles.var_Formulaire), classePageNomSimple, "_", classeApiOperationIdMethode, " classApiMethodMethod=\"Page\"}}{{/partial}}");
							l("{{#partial \"htm", langueConfig.getString(ConfigCles.var_Formulaire), langueConfig.getString(ConfigCles.var_Bouton), "_", classeApiOperationIdMethode, "\"}}{{> htm", langueConfig.getString(ConfigCles.var_Formulaire), langueConfig.getString(ConfigCles.var_Bouton), classePageNomSimple, "_", classeApiOperationIdMethode, " classApiMethodMethod=\"Page\"}}{{/partial}}");
						} else if(classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTCopie)) || classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
							l("{{#partial \"htm", langueConfig.getString(ConfigCles.var_Bouton), "_", classeApiOperationIdMethode, "\"}}{{> htm", langueConfig.getString(ConfigCles.var_Bouton), classePageNomSimple, "_", classeApiOperationIdMethode, " classApiMethodMethod=\"", classeApiMethodeMethode, "\"}}{{/partial}}");
							l("{{#partial \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "_", classeApiOperationIdMethode, "\"}}{{> htm", langueConfig.getString(ConfigCles.var_Formulaire), classePageNomSimple, "_", classeApiOperationIdMethode, " classApiMethodMethod=\"", classeApiMethodeMethode, "\"}}{{/partial}}");
							l("{{#partial \"htm", langueConfig.getString(ConfigCles.var_Formulaire), langueConfig.getString(ConfigCles.var_Bouton), "_", classeApiOperationIdMethode, "\"}}{{> htm", langueConfig.getString(ConfigCles.var_Formulaire), langueConfig.getString(ConfigCles.var_Bouton), classePageNomSimple, "_", classeApiOperationIdMethode, " classApiMethodMethod=\"", classeApiMethodeMethode, "\"}}{{/partial}}");
						}
					}
					l("{{/eq}}");
				}
				l("{{> ", classeGenPageNomSimple, "}}");
			}

			o = auteurGenPageHbs;

			{
				String hackathonMission = classeDoc.getString("hackathonMissionGenPageHbs_stored_string");
				String hackathonColumn = classeDoc.getString("hackathonColumnGenPageHbs_stored_string");
				String hackathonLabels = classeDoc.getString("hackathonLabelsGenPageHbs_stored_string");
				if(hackathonMission != null || hackathonColumn != null || hackathonLabels != null) {
					l("<!--");
					if(hackathonMission != null)
						l(String.format("hackathonMission: %s", hackathonMission));
					if(hackathonColumn != null)
						l(String.format("hackathonColumn: %s", hackathonColumn));
					if(hackathonLabels != null)
						l(String.format("hackathonLabels: %s", hackathonLabels));
					l("-->");
				}
			}
			t(0, "{{#*inline \"htmTitle", classePageNomSimple, "\"}}");
			tl(2, "<!-- inline \"htmTitle", classePageNomSimple, "\" -->");
			t(2, "<title>");
				s("{{#if ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_}}");
				s("{{#eq ", uncapitalizeClasseApiClasseNomSimple, "Count int1}}");
				s("{{#if ", classeVarCleUnique, "}}");
				s(classeNomAdjectifSingulier);
				s("{{else}}");
				s(classeNomAdjectifSingulier);
				s("{{/if}}");
				s("{{else}}");
				s(classeAucunNomTrouve);
				s("{{/eq}}");
				s("{{else}}");
				s(classeNomAdjectifPluriel);
				s("{{/if}}");
			l("</title>");
			l("{{/inline}}");

			t(0, "{{#*inline \"htmMeta", classePageNomSimple, "\"}}");
			s("{{> \"htmMeta", classePageSuperNomSimple, "\"}}");
			l("{{/inline}}");

			t(0, "{{#*inline \"htmStyle", classePageNomSimple, "\"}}");
			s("{{> \"htmStyle", classePageSuperNomSimple, "\"}}");
			l("{{/inline}}");

			t(0, "{{#*inline \"htmScripts", classePageNomSimple, "\"}}");
			s("{{> \"htmScripts", classePageSuperNomSimple, "\"}}");
			tl(2, "<!-- inline \"htmScripts", classePageNomSimple, "\" -->");
			tl(2, "<script src=\"{{", langueConfig.getString(ConfigCles.var_statiqueUrlBase), "}}/js/", langueNom, "/", classePageNomSimple, ".js\"></script>");
			if(classeAttribuerNomSimplePages != null) {
				for(String classeAttribuerNomSimplePage : classeAttribuerNomSimplePages) {
					t(2).l("<script src=\"{{", langueConfig.getString(ConfigCles.var_statiqueUrlBase), "}}/js/", langueNom, "/", classeAttribuerNomSimplePage, ".js\"></script>");
				}
			}
			tl(0, "{{/inline}}");

			if(!classePageSimple) {

				String hackathonMission = classeDoc.getString("hackathonMissionPageJs_stored_string");
				String hackathonColumn = classeDoc.getString("hackathonColumnPageJs_stored_string");
				String hackathonLabels = classeDoc.getString("hackathonLabelsPageJs_stored_string");
				if(hackathonMission != null || hackathonColumn != null || hackathonLabels != null) {
					if(hackathonMission != null)
						auteurPageJs.l("// ", String.format("hackathonMission: %s", hackathonMission));
					if(hackathonColumn != null)
						auteurPageJs.l("// ", String.format("hackathonColumn: %s", hackathonColumn));
					if(hackathonLabels != null)
						auteurPageJs.l("// ", String.format("hackathonLabels: %s", hackathonLabels));
				}

				t(0, "{{#*inline \"htmScript", classePageNomSimple, "\"}}");
//				s("{{> \"htmScript", classePageSuperNomSimple, "\"}}");
				tl(2, "<!-- inline \"htmScript", classePageNomSimple, "\" -->");
				tl(2, "<script>");
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
						Boolean methodePUTFusion = classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTFusion));
						Boolean methodePUTCopie = classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTCopie));
						Boolean methodePATCH = classeApiMethodeMethode.equals("PATCH");
						Boolean methodeDELETE = classeApiMethodeMethode.equals("DELETE");
						Boolean methodeRecherche = classeApiMethode.contains(langueConfig.getString(ConfigCles.var_Recherche));
						auteurPageJs.l();
						auteurPageJs.tl(0, "// ", classeApiMethode, " //");
						auteurPageJs.l();
//							auteurPageJs.l("/**");
//							if(methodePATCH) {
//							auteurPageJs.l(" * Modifier un ou plusiers ", classeNomPluriel, " sans valuers qui change, ");
//							auteurPageJs.l(" * ou changer des valeurs pour un ou plusiers ", classeLeNom, ". ");
//							auteurPageJs.l(" * @param params: [ \"q=*:*\", \"fq=", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":1\", \"sort=", classeModele ? classeVarClePrimaire : classeVarCleUnique, " asc\", \"rows=1\", \"fl=", classeModele ? classeVarClePrimaire : classeVarCleUnique, "\" ]");
//							auteurPageJs.l(" *        Une liste des opérations de recherche sur des ", classeNomPluriel, " ");
//							auteurPageJs.l(" *        pour rechercher \"q=*:*\", filtrer \"fq=", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":1\", trier \"sort=", classeModele ? classeVarClePrimaire : classeVarCleUnique, " desc\", ");
//							auteurPageJs.l(" *        limiter les résultats \"rows=1\", ou limiter les valeurs \"fl=", classeModele ? classeVarClePrimaire : classeVarCleUnique, "\". ");
//							auteurPageJs.l(" * @param valeurs Noms des champs et valeurs à changer selon les filtres fq. ");
//							auteurPageJs.l(" *           Example: { ", classeModele ? classeVarClePrimaire : classeVarCleUnique, ": 1 }");
//							}
//							auteurPageJs.l(" */");
						auteurPageJs.t(0, "async function ", classeApiOperationIdMethode, "(");
						if(methodePOST) {
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireValeurs));
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						}
						else if(methodePUTImport) {
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ", ", classeModele ? classeVarClePrimaire : classeVarCleUnique, ", success, error");
						}
						else if(methodePUTFusion) {
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ", ", classeModele ? classeVarClePrimaire : classeVarCleUnique, ", success, error");
						}
						else if(methodePUTCopie) {
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ", ", classeModele ? classeVarClePrimaire : classeVarCleUnique, ", success, error");
						}
						else if(methodePATCH)
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireFiltres), ", $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ", ", classeModele ? classeVarClePrimaire : classeVarCleUnique, ", success, error");
						else if(methodeRecherche) {
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireFiltres), "");
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						}
						else if(methodeGET || methodeDELETE)
							auteurPageJs.s(classeVarClePrimaire);
	
						auteurPageJs.l(") {");
						if(methodePOST || methodePUTCopie) {
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.tl(1, "if(success == null) {");
							auteurPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {");
							auteurPageJs.tl(3, langueConfig.getString(ConfigCles.var_ajouterLueur), "($", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".next('button'));");
							if(classeVarUrlPk != null) {
								auteurPageJs.tl(3, "var url = data['", classeVarUrlPk, "'];");
								auteurPageJs.tl(3, "if(url)");
								auteurPageJs.tl(4, "window.location.href = url;");
							}
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.tl(1, "if(error == null) {");
							auteurPageJs.tl(2, "error = function( jqXhr, textStatus, errorThrown ) {");
							auteurPageJs.tl(3, langueConfig.getString(ConfigCles.var_ajouterErreur), "($", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".next('button'));");
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.s(wPOST);
							auteurPageJs.l();
						}
						else if(methodePUTImport) {
							auteurPageJs.tl(1, "var json = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", "PUTImport", "_", langueConfig.getString(ConfigCles.var_listeRecherche), "').val();");
							auteurPageJs.tl(1, "if(json != null && json !== '')");
							if(StringUtils.equals("application/json", classeApiTypeMediaRequete))
								auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(JSON.parse(json), success, error);");
							else
								auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(json, success, error);");
						}
						else if(methodePUTFusion) {
							auteurPageJs.tl(1, "var json = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", langueConfig.getString(ConfigCles.var_PUTFusion), "_", langueConfig.getString(ConfigCles.var_listeRecherche), "').val();");
							auteurPageJs.tl(1, "if(json != null && json !== '')");
							if(StringUtils.equals("application/json", classeApiTypeMediaRequete))
								auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(JSON.parse(json), success, error);");
							else
								auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(json, success, error);");
						}
						else if(methodePUTCopie) {
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.s(wPUTCopie);
							auteurPageJs.l();
						}
						else if(methodePATCH) {
							auteurPageJs.tl(1, "var ", langueConfig.getString(ConfigCles.var_filtres), " = ", classeApiOperationIdMethode,langueConfig.getString(ConfigCles.var_Filtres), "($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ");");
							auteurPageJs.l();
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.s(wPATCH);
							auteurPageJs.l();
						}
						else if(methodeRecherche) {
							auteurPageJs.tl(1, "var ", langueConfig.getString(ConfigCles.var_filtres), " = ", classeApiOperationIdMethode,langueConfig.getString(ConfigCles.var_Filtres), "($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ");");
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
							auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", classeModele ? classeVarClePrimaire : classeVarCleUnique, " == null ? $.deparam(window.location.search ? window.location.search.substring(1) : window.location.search) : [{name:'fq', value:'", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}], vals, success, error);");
						}
						else if(methodePUTImport) {
						}
						else if(methodePUTFusion) {
						}
						else if(methodePUTCopie) {
							auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", classeModele ? classeVarClePrimaire : classeVarCleUnique, " == null ? $.deparam(window.location.search ? window.location.search.substring(1) : window.location.search) : [{name:'fq', value:'", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":' + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}], vals, success, error);");
						}
						else if(methodeRecherche) {
							auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", langueConfig.getString(ConfigCles.var_filtres), ", success, error);");
						}
						else {
							auteurPageJs.tl(1, "$.ajax({");
		
							if(methodeGET || methodeDELETE || methodePUTCopie)
								auteurPageJs.tl(2, "url: '", StringUtils.replace(classeApiUriMethode, "{id}", "' + id"));
							else if(methodePATCH || methodeRecherche)
								auteurPageJs.tl(2, "url: '", classeApiUriMethode, "?' + $.param(", langueConfig.getString(ConfigCles.var_filtres), ")");
							else
								auteurPageJs.tl(2, "url: '", classeApiUriMethode, "'");
		
							auteurPageJs.tl(2, ", dataType: 'json'");
							auteurPageJs.tl(2, ", type: '", classeApiMethodeMethode, "'");
							auteurPageJs.tl(2, ", contentType: 'application/json; charset=utf-8'");
							if(!"GET".equals(classeApiMethodeMethode) || "DELETE".equals(classeApiMethodeMethode))
								auteurPageJs.tl(2, ", data: JSON.stringify(vals)");
							auteurPageJs.tl(2, ", success: success");
							auteurPageJs.tl(2, ", error: error");
//								auteurPageJs.tl(2, ", success: function( data, textStatus, jQxhr ) {");
//								auteurPageJs.tl(3, "$.each( vals, function( key, value ) {");
//								auteurPageJs.tl(4, "$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.' + key).removeClass('", langueConfig.getString(ConfigCles.var_lueur), langueConfig.getString(ConfigCles.var_Erreur), "');");
//								auteurPageJs.tl(4, "$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.' + key).addClass('", langueConfig.getString(ConfigCles.var_lueur), langueConfig.getString(ConfigCles.var_Succes), "');");
//								auteurPageJs.tl(3, "});");
//								auteurPageJs.tl(2, "}");
//								auteurPageJs.tl(2, ", error: function( jqXhr, textStatus, errorThrown ) {");
//								auteurPageJs.tl(3, "$.each( vals, function( key, value ) {");
//								auteurPageJs.tl(4, "$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.' + key).removeClass('", langueConfig.getString(ConfigCles.var_lueur), langueConfig.getString(ConfigCles.var_Succes), "');");
//								auteurPageJs.tl(4, "$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.' + key).addClass('", langueConfig.getString(ConfigCles.var_lueur), langueConfig.getString(ConfigCles.var_Erreur), "');");
//								auteurPageJs.tl(3, "});");
//								auteurPageJs.tl(2, "}");
							auteurPageJs.tl(1, "});");
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
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, langueConfig.getString(ConfigCles.var_Filtres), "($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ") {");
							auteurPageJs.tl(1, "var ", langueConfig.getString(ConfigCles.var_filtres), " = [];");
							auteurPageJs.tl(1, "if($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ") {");
							if(methodePATCH)
								auteurPageJs.tl(2, langueConfig.getString(ConfigCles.var_filtres), ".push({ name: 'softCommit', value: 'true' });");
							auteurPageJs.s(wRecherche);
							auteurPageJs.tl(1, "}");
							auteurPageJs.tl(1, "return ", langueConfig.getString(ConfigCles.var_filtres), ";");
							auteurPageJs.tl(0, "}");
						}
						if(methodePATCH) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Val(", langueConfig.getString(ConfigCles.var_filtres), ", v, val, success, error) {");
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.tl(1, "vals[v] = val;");
							auteurPageJs.tl(1, "", classeApiOperationIdMethode, "Vals(", langueConfig.getString(ConfigCles.var_filtres), ", vals, success, error);");
							auteurPageJs.l("}"); 
						}
						if(methodeRecherche) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(", langueConfig.getString(ConfigCles.var_filtres), ", success, error) {");
							if(classeLignes != null) {
								auteurPageJs.l();
//										auteurPageJs.tl(1, langueConfig.getString(ConfigCles.var_filtres), ".push({ name: 'rows', value: ", classeLignes, " });");
							}
							if(classeTrisVar != null) {
								auteurPageJs.l();
								for(Integer i = 0; i < classeTrisVar.size(); i++) {
									String classeTriVar = classeTrisVar.get(i);
									String classeTriOrdre = classeTrisOrdre.get(i);

									auteurPageJs.tl(1, langueConfig.getString(ConfigCles.var_filtres), ".push({ name: '", "sort", "', value: '", classeTriVar, " ", classeTriOrdre, "' });");
								}
							}
							auteurPageJs.tl(1, "$.ajax({");
							auteurPageJs.tl(2, "url: '", classeApiUriMethode, "?' + $.param(", langueConfig.getString(ConfigCles.var_filtres), ")");
						}
						if(methodePATCH || methodePUTCopie) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(", langueConfig.getString(ConfigCles.var_filtres), ", vals, success, error) {");
							auteurPageJs.tl(1, "$.ajax({");
							auteurPageJs.tl(2, "url: '", classeApiUriMethode, "?' + $.param(", langueConfig.getString(ConfigCles.var_filtres), ")");
						}
						if(methodePUTImport || methodePUTFusion) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(json, success, error) {");
							auteurPageJs.tl(1, "$.ajax({");
							auteurPageJs.tl(2, "url: '", classeApiUriMethode, "'");
						}
						if(methodePOST) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(vals, success, error) {");
							auteurPageJs.tl(1, "$.ajax({");
							auteurPageJs.tl(2, "url: '", classeApiUriMethode, "'");
						}
						if(methodePATCH || methodePUTCopie || methodePUTImport || methodePUTFusion || methodePOST || methodeRecherche) {
							auteurPageJs.tl(2, ", dataType: 'json'");
							auteurPageJs.tl(2, ", type: '", classeApiMethodeMethode, "'");
							auteurPageJs.tl(2, ", contentType: 'application/json; charset=utf-8'");
							if(methodePATCH || methodePOST) {
								auteurPageJs.tl(2, ", data: JSON.stringify(vals)");
							}
							if(methodePUTCopie) {
								auteurPageJs.tl(2, ", data: JSON.stringify({patch: vals})");
							}
							if(methodePUTImport || methodePUTFusion) {
								auteurPageJs.tl(2, ", data: JSON.stringify(json)");
							}
							auteurPageJs.tl(2, ", success: success");
							auteurPageJs.tl(2, ", error: error");
							auteurPageJs.tl(1, "});");
							auteurPageJs.l("}");
						}
						if(methodeRecherche) {

							SolrQuery rechercheSolr = new SolrQuery();   
							rechercheSolr.setQuery("*:*");
							rechercheSolr.setRows(1000000);
							String fqClassesSuperEtMoi = "(" + classesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
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
										entiteHtmLigne = (Integer)entiteDocumentSolr.get("entiteHtmLigne_stored_int");
										entiteHtmLigneTitre = (String)entiteDocumentSolr.get("entiteHtmLigneTitre_stored_string");
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
										entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + langueConfig.getString(ConfigCles.var_Recherche) + "_" + langueNom + "_stored_string");
										entiteAttribuerOperationIdPATCH = (String)entiteDocumentSolr.get("entiteAttribuerOperationIdPATCH_" + langueNom + "_stored_string");
										entiteOperationIdPATCH = (String)entiteDocumentSolr.get("entiteOperationIdPATCH_" + langueNom + "_stored_string");
										entiteTypeJson = (String)entiteDocumentSolr.get("entiteTypeJson_stored_string");
										entiteAttribuerTypeJson = (String)entiteDocumentSolr.get("entiteAttribuerTypeJson_stored_string");
										entiteAttribuerContexteIconeNom = (String)entiteDocumentSolr.get("entiteAttribuerContexteIconeNom_stored_string");
										entiteAttribuerTrisVar = (List<String>)entiteDocumentSolr.get("entiteAttribuerTrisVar_" + langueNom + "_stored_strings");
										entiteAttribuerTrisSuffixeType = (List<String>)entiteDocumentSolr.get("entiteAttribuerTrisSuffixeType_stored_strings");
										entiteAttribuerContexteUnNom = (String)entiteDocumentSolr.get("entiteAttribuerContexteUnNom_" + langueNom + "_stored_string");
										entiteAttribuerContexteNomPluriel = (String)entiteDocumentSolr.get("entiteAttribuerContexteNomPluriel_" + langueNom + "_stored_string");
			
										if(entiteSuggere) {
											auteurPageJs.l();
											auteurPageJs.tl(0, "function ", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ", $list) {");
											auteurPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
											auteurPageJs.tl(2, "$list.empty();");
											auteurPageJs.tl(2, "$.each(data['list'], function(i, o) {");
											auteurPageJs.tl(3, "var $i = $('<i>').attr('class', 'fa", StringUtils.substring(classeIconeGroupe, 0, 1), " fa-", classeIconeNom, " ');");
											auteurPageJs.t(3, "var $span = $('<span>').attr('class', '').text(");
											if(classeVarTitre != null)
												auteurPageJs.s("o['", classeVarTitre, "']");
											auteurPageJs.l(");");
											auteurPageJs.tl(3, "var $li = $('<li>');");
											auteurPageJs.tl(3, "var $a = $('<a>').attr('href', o['", classeVarUrlPk, "']);");
											auteurPageJs.tl(3, "$a.append($i);");
											auteurPageJs.tl(3, "$a.append($span);");
											auteurPageJs.tl(3, "$li.append($a);");
											auteurPageJs.tl(3, "$list.append($li);");
											auteurPageJs.tl(2, "});");
											auteurPageJs.tl(1, "};");
											auteurPageJs.tl(1, "error = function( jqXhr, textStatus, errorThrown ) {};");
											auteurPageJs.tl(1, "", langueConfig.getString(ConfigCles.var_rechercher), classeApiClasseNomSimple, "Vals($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ", success, error);");
											auteurPageJs.tl(0, "}");
										}
										else if(entiteAttribuer) {

											auteurPageJs.l();
											auteurPageJs.tl(0, "function ", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_filtres), ", $list, ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " = null, ", langueConfig.getString(ConfigCles.var_attribuer), "=true) {");
											auteurPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
											auteurPageJs.tl(2, "$list.empty();");
											auteurPageJs.tl(2, "$.each(data['list'], function(i, o) {");
											auteurPageJs.tl(3, "var $i = $('<i>').attr('class', 'fa", StringUtils.substring(entiteAttribuerContexteIconeGroupe, 0, 1), " fa-", entiteAttribuerContexteIconeNom, " ');");
											auteurPageJs.t(3, "var $span = $('<span>').attr('class', '').text(");
											if(entiteAttribuerVarTitre != null)
												auteurPageJs.s("o['", entiteAttribuerVarTitre, "']");
											auteurPageJs.l(");");

											if(entiteAttribuerVarUrlPk != null)
												auteurPageJs.tl(3, "var $a = $('<a>').attr('id', o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "']).attr('href', o['", entiteAttribuerVarUrlPk, "']);");
											else
												auteurPageJs.tl(3, "var $a = $('<span>');");

											auteurPageJs.tl(3, "$a.append($i);");
											auteurPageJs.tl(3, "$a.append($span);");
											auteurPageJs.tl(3, "var val = o['", entiteAttribuerVar, "'];");
											auteurPageJs.tl(3, "var checked = pk == null ? false : Array.isArray(val) ? val.includes(", classeModele ? classeVarClePrimaire : classeVarCleUnique, ".toString()) : val == ", classeModele ? classeVarClePrimaire : classeVarCleUnique, ";");
											auteurPageJs.tl(3, "var $input = $('<input>');");
											auteurPageJs.tl(3, "$input.attr('id', '", classeApiMethodeMethode, "_", entiteVar, "_' + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " + '_", entiteAttribuerVar, "_' + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "']);");
											auteurPageJs.tl(3, "$input.attr('value', o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "']);");
											auteurPageJs.tl(3, "$input.attr('class', '", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " w3-check ');");

											auteurPageJs.tl(3, "if(", classeModele ? classeVarClePrimaire : classeVarCleUnique, " != null) {");
											auteurPageJs.t(4, "$input.attr('onchange', \"var $input = $('#", classeApiMethodeMethode, "_", entiteVar, "_\" + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " + \"_", entiteAttribuerVar, "_\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"'); ");
											if("array".equals(entiteTypeJson)) {
												auteurPageJs.s("", entiteOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":\" + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " + \"' }], { [($input.prop('checked') ? 'add' : 'remove') + '", entiteVarCapitalise, "']: \\\"\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"\\\" }");
											}
											else {
												auteurPageJs.s("", entiteOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":\" + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " + \"' }], { [($input.prop('checked') ? 'set' : 'remove') + '", entiteVarCapitalise, "']: \\\"\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"\\\" }");
											}
											auteurPageJs.l(" ); \");");

											auteurPageJs.tl(4, "$input.attr('onclick', '", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); ');");
											auteurPageJs.tl(3, "}");

											auteurPageJs.tl(3, "$input.attr('type', 'checkbox');");
											auteurPageJs.tl(3, "if(checked)");
											auteurPageJs.tl(4, "$input.attr('checked', 'checked');");
											auteurPageJs.tl(3, "var $li = $('<li>');");
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
													auteurPageJs.tl(k, "$sort = $('<span>').attr('class', 'w3-text-grey ').attr('style', 'padding-right: 8px; ');");
													auteurPageJs.tl(k, "var $sortInput = $('<input>')");
													auteurPageJs.tl(k, "$sortInput.attr('class', 'w3-tiny ');");
													auteurPageJs.tl(k, "$sortInput.attr('style', 'width: 4em; ');");
													auteurPageJs.tl(k, "$sortInput.attr('id', \"", langueConfig.getString(ConfigCles.var_attribuer), "_\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"_", langueConfig.getString(ConfigCles.var_tri), "_", entiteAttribuerTriVar, "\");");
													auteurPageJs.tl(k, "$sortInput.attr('value', ", entiteAttribuerTriVar, ").attr('onchange', ");
													auteurPageJs.tl(k + 1, "\"$('#", classeApiClasseNomSimple, "Form :input[name=\\\"focusId\\\"]').val($(this).attr('id')); \"");
													auteurPageJs.tl(k + 1, "+ \"", entiteAttribuerOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"' }], { ['set", StringUtils.capitalize(entiteAttribuerTriVar), "']: $(this).val() ? $(this).val() : null }\"");
													auteurPageJs.tl(k + 2, "+ \", function() { \"");
													auteurPageJs.tl(k + 2, "+ \"}\"");
													auteurPageJs.tl(k + 2, "+ \", function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#", langueConfig.getString(ConfigCles.var_attribuer), "_\" + o['", classeModele ? classeVarClePrimaire : classeVarCleUnique, "'] + \"_", langueConfig.getString(ConfigCles.var_tri), "_", entiteAttribuerTriVar, "')); }\"");
													auteurPageJs.tl(k + 2, "+ \" ); \"); ");
													auteurPageJs.tl(k, "$sort.append($sortInput);");
													auteurPageJs.tl(k, "$li.append($sort);");
													if(entiteAttribuerTriVarAncien != null)
														auteurPageJs.tl(3, "}");

													entiteAttribuerTriVarAncien = entiteAttribuerTriVar;
												}
											}
											auteurPageJs.tl(3, "if(", langueConfig.getString(ConfigCles.var_attribuer), ")");
											auteurPageJs.tl(4, "$li.append($input);");
											auteurPageJs.tl(3, "$li.append($a);");
											auteurPageJs.tl(3, "$list.append($li);");
											auteurPageJs.tl(2, "});");
											auteurPageJs.tl(2, "var focusId = $('#", classeApiClasseNomSimple, "Form :input[name=\"focusId\"]').val();");
											auteurPageJs.tl(2, "if(focusId)");
											auteurPageJs.tl(3, "$('#' + focusId).parent().next().find('input').focus();");
											auteurPageJs.tl(1, "};");
											auteurPageJs.tl(1, "error = function( jqXhr, textStatus, errorThrown ) {};");
											auteurPageJs.tl(1, "", entiteAttribuerOperationIdRecherche, "Vals(", langueConfig.getString(ConfigCles.var_filtres), ", success, error);");
											auteurPageJs.tl(0, "}");

											auteurWebsocket.l();
											auteurWebsocket.tl(2, "window.eventBus.registerHandler('websocket", entiteAttribuerNomSimple, "', function (error, message) {");
//												auteurWebsocket.tl(3, "var json = JSON.parse(message['body']);");
//												auteurWebsocket.tl(3, "var id = json['id'];");
//												auteurWebsocket.tl(3, langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "($('#' + ($(this).val() ? '", langueConfig.getString(ConfigCles.var_suggere), "' : 'form') + '", classeApiClasseNomSimple, entiteVarCapitalise, "'), $('#", "list", classeApiClasseNomSimple, entiteVarCapitalise, "_", classeApiMethodeMethode, "'));");
											auteurWebsocket.tl(3, "$('.Page_", entiteVar, "').trigger('oninput');");
											auteurWebsocket.tl(3, "$('.Page_", entiteVar, "_", langueConfig.getString(ConfigCles.var_ajouter), "').text('", langueConfig.getString(ConfigCles.var_ajouter), " ", entiteAttribuerContexteUnNom, "');");
											auteurWebsocket.tl(3, "$('.Page_", entiteVar, "_", langueConfig.getString(ConfigCles.var_ajouter), "').removeClass('w3-disabled');");
											auteurWebsocket.tl(3, "$('.Page_", entiteVar, "_", langueConfig.getString(ConfigCles.var_ajouter), "').attr('disabled', false);");
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
				tl(3, "$('#site-calendar-box').accordion({ collapsible: true, active: false });");
				l("{{#if DEFAULT_MAP_LOCATION }}");
				tl(3, "window.DEFAULT_MAP_LOCATION = { lat: {{ DEFAULT_MAP_LOCATION.lat }}, lon: {{ DEFAULT_MAP_LOCATION.lon }} };");
				l("{{/if}}");
				l("{{#if DEFAULT_MAP_ZOOM }}");
				tl(3, "window.DEFAULT_MAP_ZOOM = {{ DEFAULT_MAP_ZOOM }};");
				l("{{/if}}");
				tl(3, "window.DEFAULT_ZONE_ID = '{{ defaultZoneId }}';");
				tl(3, "$(document).ready(function() {");
				tl(4, "document.onkeydown = function(evt) {");
				tl(5, "evt = evt || window.event;");
				tl(5, "var isEscape = false;");
				tl(5, "if ('key' in evt) {");
				tl(6, "isEscape = (evt.key === 'Escape' || evt.key === 'Esc');");
				tl(5, "} else {");
				tl(6, "isEscape = (evt.keyCode === 27);");
				tl(5, "}");
				tl(5, "if (isEscape) {");
				tl(6, "$('.w3-modal:visible').hide();");
				tl(5, "}");
				tl(4, "};");
				tl(4, "window.eventBus = new EventBus('/eventbus');");
				tl(4, "var pk = {{#if pk}}{{pk}}{{else}}null{{/if}};");
				tl(4, "if(pk != null) {");
				tl(5, "window.", StringUtils.uncapitalize(classeNomSimple), " = JSON.parse($('.", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Formulaire), " .", StringUtils.uncapitalize(classeNomSimple), "').val());");
				s(wJsInit);
				tl(4, "}");
				tl(5, "window.", langueConfig.getString(ConfigCles.var_liste), classeNomSimple, " = JSON.parse($('.", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Formulaire), " .", langueConfig.getString(ConfigCles.var_liste), classeNomSimple, "').val());");
				tl(4, "window.varsFq = JSON.parse('{{{toJsonObjectStringInApostrophes varsFq}}}');");
				tl(4, "window.varsRange = JSON.parse('{{{toJsonObjectStringInApostrophes varsRange}}}');");
				tl(4, "window.defaultRangeVar = '{{ defaultRangeVar }}';");
				tl(4, langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Graphique), classeNomSimple, "();");
				l();
				tl(4, "var calendarEl = document.getElementById('site-calendar');");
				tl(4, "var calendar = new FullCalendar.Calendar(calendarEl, {");
				tl(5, "initialView: 'dayGridMonth'");
				tl(4, "});");
				tl(4, "//calendar.render();");
				tl(0, "{{#block \"websocket\"}}{{/block}}");
				tl(3, "});");
				l();
				tl(3, "function ", langueConfig.getString(ConfigCles.var_jsWebsocket), classeNomSimple, "(", classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire, ", vars, $response) {");
				tl(0, "{{#block \"", langueConfig.getString(ConfigCles.var_jsWebsocket), "\"}}{{/block}}");
				tl(3, "}");
				l();
				tl(3, "function ", langueConfig.getString(ConfigCles.var_jsInfobulle), classeNomSimple, "(e, feature) {");
				tl(0, "{{#block \"", langueConfig.getString(ConfigCles.var_jsInfobulle), "\"}}{{/block}}");
				tl(3, "}");
				l();
				tl(3, "function ", langueConfig.getString(ConfigCles.var_htmInfobulle), classeNomSimple, "(feature, layer) {");
				tl(4, "return `{{#block \"", langueConfig.getString(ConfigCles.var_htmInfobulle), "\"}}{{/block}}`;");
				tl(3, "}");
				l();
				tl(3, "function ", langueConfig.getString(ConfigCles.var_jsStyle), classeNomSimple, "(feature) {");
				tl(0, "{{#block \"", langueConfig.getString(ConfigCles.var_jsStyle), "\"}}{{/block}}");
				tl(3, "}");
				tl(2, "</script>");
				tl(0, "{{/inline}}");

				tl(0, "{{#*inline \"websocket", classePageNomSimple, "\"}}");
				tl(4, "<!-- inline \"websocket", classePageNomSimple, "\" -->");
				tl(4, "websocket", classeApiClasseNomSimple, "(websocket", classeApiClasseNomSimple, "Inner);");
				tl(0, "{{/inline}}");
				tl(0, "{{#*inline \"", langueConfig.getString(ConfigCles.var_jsWebsocket), classePageNomSimple, "\"}}{{/inline}}");
				tl(0, "{{#*inline \"", langueConfig.getString(ConfigCles.var_jsInfobulle), classePageNomSimple, "\"}}{{/inline}}");
				tl(0, "{{#*inline \"", langueConfig.getString(ConfigCles.var_htmInfobulle), classePageNomSimple, "\"}}{{/inline}}");
				tl(0, "{{#*inline \"", langueConfig.getString(ConfigCles.var_jsStyle), classePageNomSimple, "\"}}");
				tl(4, "return {");
				tl(5, "radius: 8");
				tl(5, ", fillColor: '#ff7800'");
				tl(5, ", color: '#000'");
				tl(5, ", weight: 1");
				tl(5, ", opacity: 1");
				tl(5, ", fillOpacity: 0.8");
				tl(4, "};");
				tl(0, "{{/inline}}");
			}
			t(0, "{{#*inline \"htmUrl", classeApiClasseNomSimple, "\"}}");
			s("{{pageUri}}");
			s("?q={{query.q}}");
			s("&amp;rows={{#if rows}}{{rows}}{{else}}{{pagination.", langueConfig.getString(ConfigCles.var_lignes), "}}{{/if}}");
			s("&amp;rows={{#if start}}{{start}}{{else}}{{pagination.", langueConfig.getString(ConfigCles.var_debut), "}}{{/if}}");
			s("{{#each query.fq}}");
			s("{{#eq fq this}}");
			s("{{else}}");
			s("&fq={{fq}}:{{val}}");
			s("{{/eq}}");
			s("{{/each}}");
			s("{{#each query.sort}}");
			s("{{#eq sort this}}");
			s("{{else}}");
			s("&sort={{var}} {{order}}");
			s("{{/eq}}");
			s("{{/each}}");
			l("{{/inline}}");

			t(0, "{{#*inline \"htmHead", classePageNomSimple, "\"}}");
			s("{{#block \"htmTitle\"}}{{/block}}");
			s("{{#block \"htmMeta\"}}{{/block}}");
			s("{{#block \"htmStyle\"}}{{/block}}");
			s("{{#block \"htmScripts\"}}{{/block}}");
			s("{{#block \"htmScript\"}}{{/block}}");
			l("{{/inline}}");

			//////////////////////
			// htmBodyRecherche //
			//////////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Recherche), classePageNomSimple, "\"}}");
			tl(8, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Recherche), classePageNomSimple, "\" -->");
			tl(8, "<div>");
			tl(0, "{{#each varsQ}}");
			tl(9, "<div class=\"w3-cell-row \">");
			tl(10, "<div class=\"w3-cell w3-cell-top \">");
			t(11, "<label for=\"q", classeNomSimple, "_{{ @key }}\">");
			s("{{ ", langueConfig.getString(ConfigCles.var_nomAffichage), " }}");
			s("<sup class=\"w3-tiny \"> ({{ ", langueConfig.getString(ConfigCles.var_classeNomSimple), " }})</sup>");
			l("</label>");
			tl(10, "</div>");
			tl(9, "</div>");
			tl(9, "<div class=\"w3-cell-row \">");
			tl(10, "<div class=\"w3-cell w3-cell-top \">");
			t(11, "<input");
			s(" id=\"q", classeNomSimple, "_{{ @key }}\"");
			s(" placeholder=\"{{ displayName }}\"");
			s(" class=\"\"");
//			s(" onkeypress=\"qChange('", classeNomSimple, "', this); \"");

			if(classeVarSuggere == null)
				s(" onkeyup=\"qChange('", classeNomSimple, "', this); \"");
			else
				s(" {{#eq ../var '", classeVarSuggere, "' }}onkeyup{{else}}onchange{{/eq}}=\"qChange('", classeNomSimple, "', this); \"");

//			s(" onchange=\"qChange('", classeNomSimple, "', this); \"");
			s(" data-var=\"{{ var }}\"");
			s(" autocomplete=\"off=\"");
			l("/>");
			tl(11, "<div class=\"pageSearchVal w3-tiny \"></div>");
			tl(10, "</div>");
			tl(9, "</div>");
			tl(0, "{{/each}}");

			///////////
			// start //
			///////////

			tl(9, "<div class=\"w3-cell-row \">");
			tl(10, "<div class=\"w3-cell w3-cell-top \">");
			t(11, "<label for=\"q", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_Debut), "\">");
			s("", langueConfig.getString(ConfigCles.var_debut), "");
			s("<sup class=\"w3-tiny \"> (Long)</sup>");
			l("</label>");
			tl(10, "</div>");
			tl(9, "</div>");
			tl(9, "<div class=\"w3-cell-row \">");
			tl(10, "<div class=\"w3-cell w3-cell-top \">");
			t(11, "<input");
			s(" id=\"q", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_debut), "\"");
			s(" placeholder=\"{{ displayName }}\"");
			s(" class=\"\"");
			s(" onchange=\"paramChange('", classeNomSimple, "', this); \"");
			s(" data-var=\"start\"");
			s(" autocomplete=\"off=\"");
			s(" value=\"{{ start }}\"");
			l("/>");
			tl(11, "<div class=\"pageSearchVal w3-tiny \">start={{ start }}</div>");
			tl(10, "</div>");
			tl(9, "</div>");

			//////////
			// rows //
			//////////

			tl(9, "<div class=\"w3-cell-row \">");
			tl(10, "<div class=\"w3-cell w3-cell-top \">");
			t(11, "<label for=\"q", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_Lignes), "\">");
			s("", langueConfig.getString(ConfigCles.var_lignes), "");
			s("<sup class=\"w3-tiny \"> (Long)</sup>");
			l("</label>");
			tl(10, "</div>");
			tl(9, "</div>");
			tl(9, "<div class=\"w3-cell-row \">");
			tl(10, "<div class=\"w3-cell w3-cell-top \">");
			t(11, "<input");
			s(" id=\"q", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_lignes), "\"");
			s(" placeholder=\"{{ displayName }}\"");
			s(" class=\"\"");
			s(" onchange=\"paramChange('", classeNomSimple, "', this); \"");
			s(" data-var=\"rows\"");
			s(" autocomplete=\"off\"");
			s(" value=\"{{ rows }}\"");
			l("/>");
			tl(11, "<div class=\"pageSearchVal w3-tiny \">rows={{ rows }}</div>");
			tl(10, "</div>");
			tl(9, "</div>");

			tl(8, "</div>");
			l("{{/inline}}");

			////////////////////
			// htmBodyFiltres //
			////////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Filtres), classePageNomSimple, "\"}}");
			tl(8, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Filtres), classePageNomSimple, "\" -->");
			tl(8, "<div>");
			tl(0, "{{#each varsFq }}");
			tl(9, "<div class=\"\">");
			t(10, "<label for=\"fq", classeNomSimple, "_{{ @key }}\">");
			s("{{ ", langueConfig.getString(ConfigCles.var_nomAffichage), " }}");
			s("<sup class=\"w3-tiny \"> ({{ ", langueConfig.getString(ConfigCles.var_classeNomSimple), " }})</sup>");
			l("</label>");

			tl(10, "<div class=\"w3-cell-row \">");
			tl(11, "<div class=\"w3-cell w3-cell-top \">");
			t(12, "<button");
			s(" id=\"buttonFacet", classeNomSimple, "_{{ @key }}\"");
			s(" class=\"\"");
			s(" onclick=\"facetFieldChange('", classeNomSimple, "', this); \"");
			s(" title=\"", langueConfig.getString(ConfigCles.str_voir_valeurs), " ", "\"");
			s(" data-var=\"{{ var }}\"");
			s(" data-clear=\"{{#if facetField }}true{{else}}false{{/if}}\"");
			l("><i class=\"fas fa-list \"></i></button>");
			tl(11, "</div>");

			tl(11, "<div class=\"w3-cell w3-cell-top \">");
			t(12, "<input");
			s(" id=\"fq", classeNomSimple, "_{{ @key }}\"");
			s(" placeholder=\"{{ displayName }}\"");
			s(" class=\"\"");
//			s(" onkeypress=\"fqChange('", classeNomSimple, "', this); \"");

			s(" onchange=\"fqChange('", classeNomSimple, "', this); \"");

//			s(" onchange=\"fqChange('", classeNomSimple, "', this); \"");
			s(" data-var=\"{{ var }}\"");
			s(" autocomplete=\"off=\"");
			s(" value=\"{{ val }}\"");
			l("/>");
			tl(11, "</div>");
			tl(10, "</div>");

			tl(9, "</div>");
			t(9, "<div");
			s(" class=\"w3-tiny \"");
			l(">");
			t(10, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-fq", classeNomSimple, "_{{ @key }}\"");
			l(">{{#if val }}fq={{ var }}:{{encodeURIComponent val }}{{/if}}</div>");
			t(10, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-buttonFacet", classeNomSimple, "_{{ @key }}\"");
			l(">{{#if facetField.var }}facet.field={{ facetField.var }}{{/if}}</div>");

			t(10, "<ol");
			s(" class=\"pageFacetField w3-small pageFacetField", classeNomSimple, "_{{ @key }} \"");
			s(" id=\"pageFacetField", classeNomSimple, "_{{ @key }}\"");
			l(">");
			tl(0, "{{#each facetField.counts }}");
			t(11, "<li");
			s(" class=\"cursor-pointer \"");
			s(" data-class=\"", classeNomSimple, "\"");
			s(" data-var=\"{{ ../var }}\"");
			s(" data-val=\"{{ @key }}\"");
			s(" onclick=\"fqReplace('", classeNomSimple, "', this); \"");
			s(">");
			s("{{ @key }}");
			s(": ");
			s("{{ this }}");
			l("</li>");
			tl(0, "{{/each}}");
			tl(10, "</ol>");

			tl(9, "</div>");
			tl(0, "{{/each}}");
			tl(8, "</div>");
			
			l("{{/inline}}");

			//////////////////
			// htmBodyGamme //
			//////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Gamme), classePageNomSimple, "\"}}");
			tl(8, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Gamme), classePageNomSimple, "\" -->");

			tl(8, "<table class=\"w3-padding \">");
			tl(9, "<tr>");
			t(10, "<td");
			s(" class=\"w3-padding w3-tiny \"");
			s(" colspan=\"2\"");
			l(">");
			t(11, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-pageFacetRangeGap-", classeNomSimple, "\"");
			s(">{{#if rangeGap }}facet.range.gap={{encodeURIComponent rangeGap }}{{/if}}");
			l("</div>");
			t(11, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-pageFacetRangeStart-", classeNomSimple, "\"");
			s(">{{#if rangeStart }}facet.range.start={{encodeURIComponent rangeStart }}{{/if}}");
			l("</div>");
			t(11, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-pageFacetRangeEnd-", classeNomSimple, "\"");
			s(">{{#if rangeEnd }}facet.range.end={{encodeURIComponent rangeEnd }}{{/if}}");
			l("</div>");
			t(11, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-pageFacetRangeVar-", classeNomSimple, "\"");
			s(">{{#if defaultRangeVar }}facet.range={!tag=r1}{{encodeURIComponent defaultRangeVar }}{{/if}}");
			l("</div>");
			tl(10, "</td>");
			tl(9, "</tr>");
			tl(8, "</table>");

			tl(8, "<table class=\"w3-padding \">");
			tl(9, "<tr class=\"\">");
			tl(10, "<td class=\"\">");
			tl(11, "<span>Range Gap</span>");
			tl(10, "</td>");
			tl(10, "<td class=\"\">");
			t(11, "<select");
			s(" name=\"facet.range.gap\"");
			s(" id=\"pageFacetRangeGap-", classeNomSimple, "\"");
			s(" onchange=\"facet", langueConfig.getString(ConfigCles.var_Gamme), "GapChange('", classeNomSimple, "', this); \"");
			l(">");
			tl(12, "<option value=\"+1YEAR\"{{#eq defaultRangeGap '+1YEAR'}} selected=\"selected\"{{else}}{{/eq}}>Year</option>");
			tl(12, "<option value=\"+1MONTH\"{{#eq defaultRangeGap '+1MONTH'}} selected=\"selected\"{{else}}{{/eq}}>Month</option>");
			tl(12, "<option value=\"+1WEEK\"{{#eq defaultRangeGap '+1WEEK'}} selected=\"selected\"{{else}}{{/eq}}>Week</option>");
			tl(12, "<option value=\"+1DAY\"{{#eq defaultRangeGap '+1DAY'}} selected=\"selected\"{{else}}{{#if defaultRangeGap}}{{else}} selected=\"selected\"{{/if}}{{/eq}}>Day</option>");
			tl(12, "<option value=\"+1HOUR\"{{#eq defaultRangeGap '+1HOUR'}} selected=\"selected\"{{else}}{{/eq}}>Hour</option>");
			tl(12, "<option value=\"+1MINUTE\"{{#eq defaultRangeGap '+1MINUTE'}} selected=\"selected\"{{else}}{{/eq}}>Minute</option>");
			tl(12, "<option value=\"+1SECOND\"{{#eq defaultRangeGap '+1SECOND'}} selected=\"selected\"{{else}}{{/eq}}>Second</option>");
			tl(11, "</select>");
			tl(10, "</td>");
			tl(9, "</tr>");

			tl(9, "<tr class=\"\">");
			tl(10, "<td class=\"\" colspan=\"2\">");
			tl(11, "<span>Range Start</span>");
			tl(10, "</td>");
			tl(9, "</tr>");
			tl(9, "<tr class=\"\">");
			tl(10, "<td class=\"\" colspan=\"2\">");
			t(11, "<span>");
			s("<input type=\"datetime-local\"");
			s(" name=\"facetRangeStart\"");
			s(" id=\"pageFacetRangeStart-", classeNomSimple, "\"");
			s(" value=\"{{formatZonedDateTime defaultRangeStart \"yyyy-MM-dd'T'HH:mm\" defaultLocaleId defaultZoneId}}\"");
			s(" onclick=\"facet", langueConfig.getString(ConfigCles.var_Gamme), "StartChange('", classeNomSimple, "', this); \"");
			l("/></span>");
			tl(10, "</td>");
			tl(9, "</tr>");

			tl(9, "<tr class=\"\">");
			tl(10, "<td class=\"\" colspan=\"2\">");
			tl(11, "<span>Range End</span>");
			tl(10, "</td>");
			tl(9, "</tr>");
			tl(9, "<tr class=\"\">");
			tl(10, "<td class=\"\" colspan=\"2\">");
			t(11, "<span>");
			s("<input type=\"datetime-local\"");
			s(" name=\"facetRangeEnd\"");
			s(" id=\"pageFacetRangeEnd-", classeNomSimple, "\"");
			s(" value=\"{{formatZonedDateTime defaultRangeEnd \"yyyy-MM-dd'T'HH:mm\" defaultLocaleId defaultZoneId}}\"");
			s(" onclick=\"facet", langueConfig.getString(ConfigCles.var_Gamme), "EndChange('", classeNomSimple, "', this); \"");
			l("/></span>");
			tl(10, "</td>");
			tl(9, "</tr>");
			tl(8, "</table>");

			tl(8, "<table class=\"w3-padding \">");
			tl(0, "{{#each vars", langueConfig.getString(ConfigCles.var_Gamme), " }}");
			tl(9, "<tr class=\"\">");
			tl(10, "<td class=\"\">");
			t(11, "<span>");
			s("<input type=\"radio\"");
			s(" name=\"pageFacet", langueConfig.getString(ConfigCles.var_Gamme), "\"");
			s(" class=\"pageFacet", langueConfig.getString(ConfigCles.var_Gamme), " \"");
			s(" id=\"pageFacet", langueConfig.getString(ConfigCles.var_Gamme), classeNomSimple, "_{{ @key }}\"");
			s(" value=\"{{ var }}\"");
			s("{{#eq default", langueConfig.getString(ConfigCles.var_Gamme), "Var var }} checked=\"checked\"{{/eq}}");
			s(" onclick=\"facet", langueConfig.getString(ConfigCles.var_Gamme), "Change('", classeNomSimple, "', this); \"");
			l("/></span>");
			tl(10, "</td>");
			tl(10, "<td class=\"\">");
			tl(11, "<label for=\"pageFacet", langueConfig.getString(ConfigCles.var_Gamme), classeNomSimple, "_{{ @key }}\">{{ ", langueConfig.getString(ConfigCles.var_nomAffichage), " }}</label>");
			tl(10, "</td>");
			tl(9, "</tr>");
			tl(0, "{{/each}}");
			tl(8, "</table>");
			l("{{/inline}}");

			//////////////////
			// htmBodyPivot //
			//////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Pivot), classePageNomSimple, "\"}}");
			tl(8, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Pivot), classePageNomSimple, "\" -->");

			t(8, "<div");
			s(" style=\"display: none; \"");
			s(" class=\"w3-padding w3-tiny \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_Pivot), classeNomSimple, "Hidden\"");
			l(">");
			tl(0, "{{#each default", langueConfig.getString(ConfigCles.var_Pivot), "Vars }}");
			t(9, "<div");
			s(" class=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_Pivot), classeNomSimple, "Hidden \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_Pivot), classeNomSimple, "Hidden_{{ this }}\"");
			l(">{{ this }}</div>");
			tl(0, "{{/each}}");
			tl(8, "</div>");

			t(8, "<div");
			s(" class=\"w3-padding w3-tiny \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_Pivot), classeNomSimple, "\"");
			l(">");
			tl(0, "{{#if default", langueConfig.getString(ConfigCles.var_Pivot), "Vars }}");
			t(9, "<div");
			s(" class=\"pageSearchVal pageSearchVal-", langueConfig.getString(ConfigCles.var_Pivot), classeNomSimple, " \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_Pivot), classeNomSimple, "_1\"");
			s(">facet.pivot={!range=r1}");
			s("{{#each default", langueConfig.getString(ConfigCles.var_Pivot), "Vars }}");
			s("{{#if @index }},{{/if}}{{ this }}");
			s("{{/each}}");
			l("</div>");
			tl(0, "{{/if}}");
			tl(8, "</div>");

			tl(8, "<table class=\"w3-table \">");
			tl(0, "{{#each varsFq }}");
			tl(9, "<tr class=\"\">");
			tl(10, "<td class=\"\">");
			t(11, "<span>");
			s("<input type=\"checkbox\"");
			s(" name=\"pageFacetPivot\"");
			s(" class=\"pageFacetPivot \"");
			s(" id=\"pageFacetPivot", classeNomSimple, "_{{ @key }}\"");
			s(" value=\"{{ var }}\"");
			s("{{#if ", langueConfig.getString(ConfigCles.var_pivot), " }} checked=\"checked\"{{/if}}");
			s(" onclick=\"facetPivotChange('", classeNomSimple, "', this); \"");
			l("/></span>");
			tl(10, "</td>");
			tl(10, "<td class=\"w3-cell \">");
			tl(11, "<label for=\"pageFacetPivot", classeNomSimple, "_{{ @key }}\">{{ ", langueConfig.getString(ConfigCles.var_nomAffichage), " }}</label>");
			tl(10, "</td>");
			tl(9, "</tr>");
			tl(0, "{{/each}}");
			tl(8, "</table>");
			
			l("{{/inline}}");

			////////////////////////
			// htmBodyListeChamps //
			////////////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_ListeChamps), classePageNomSimple, "\"}}");
			tl(8, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_ListeChamps), classePageNomSimple, "\" -->");

			t(8, "<div");
			s(" style=\"display: none; \"");
			s(" class=\"w3-padding w3-tiny \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_ListeChamps), "", classeNomSimple, "Hidden\"");
			l(">");
			tl(0, "{{#each default", langueConfig.getString(ConfigCles.var_ListeChamps), "Vars }}");
			t(9, "<div");
			s(" class=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_ListeChamps), "", classeNomSimple, "Hidden \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_ListeChamps), "", classeNomSimple, "Hidden_{{ this }}\"");
			l(">{{ this }}</div>");
			tl(0, "{{/each}}");
			tl(8, "</div>");

			t(8, "<div");
			s(" class=\"w3-padding w3-tiny \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_ListeChamps), "", classeNomSimple, "\"");
			l(">");
			tl(0, "{{#if default", langueConfig.getString(ConfigCles.var_ListeChamps), "Vars }}");
			t(9, "<div");
			s(" class=\"pageSearchVal pageSearchVal-", langueConfig.getString(ConfigCles.var_ListeChamps), classeNomSimple, " \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_ListeChamps), "", classeNomSimple, "_1\"");
			s(">fl=");
			s("{{#each default", langueConfig.getString(ConfigCles.var_ListeChamps), "Vars }}");
			s("{{#if @index }},{{/if}}{{ this }}");
			s("{{/each}}");
			l("</div>");
			tl(0, "{{/if}}");
			tl(8, "</div>");

			tl(8, "<table class=\"w3-table \">");
			tl(0, "{{#each varsFq }}");
			tl(9, "<tr class=\"\">");
			tl(10, "<td class=\"\">");
			t(11, "<span>");
			s("<input type=\"checkbox\"");
			s(" name=\"page", langueConfig.getString(ConfigCles.var_ListeChamps), "\"");
			s(" class=\"page", langueConfig.getString(ConfigCles.var_ListeChamps), " \"");
			s(" id=\"page", langueConfig.getString(ConfigCles.var_ListeChamps), "", classeNomSimple, "_{{ @key }}\"");
			s(" value=\"{{ var }}\"");
			s("{{#if ", langueConfig.getString(ConfigCles.var_listeChamps), " }} checked=\"checked\"{{/if}}");
			s(" onclick=\"facet", langueConfig.getString(ConfigCles.var_ListeChamps), "Change('", classeNomSimple, "', this); \"");
			l("/></span>");
			tl(10, "</td>");
			tl(10, "<td class=\"w3-cell \">");
			tl(11, "<label for=\"page", langueConfig.getString(ConfigCles.var_ListeChamps), classeNomSimple, "_{{ @key }}\">{{ ", langueConfig.getString(ConfigCles.var_nomAffichage), " }}</label>");
			tl(10, "</td>");
			tl(9, "</tr>");
			tl(0, "{{/each}}");
			tl(8, "</table>");
			l("{{/inline}}");

			//////////////////
			// htmBodyStats //
			//////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Stats), classePageNomSimple, "\"}}");
			tl(8, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Stats), classePageNomSimple, "\" -->");

			t(8, "<div");
			s(" style=\"display: none; \"");
			s(" class=\"w3-padding w3-tiny \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_Stats), "", classeNomSimple, "Hidden\"");
			l(">");
			tl(0, "{{#each default", langueConfig.getString(ConfigCles.var_Stats), "Vars }}");
			t(9, "<div");
			s(" class=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_Stats), "", classeNomSimple, "Hidden \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_Stats), "", classeNomSimple, "Hidden_{{ this }}\"");
			l(">{{ this }}</div>");
			tl(0, "{{/each}}");
			tl(8, "</div>");

			t(8, "<div");
			s(" class=\"w3-padding w3-tiny \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_Stats), "", classeNomSimple, "\"");
			l(">");
			tl(0, "{{#if default", langueConfig.getString(ConfigCles.var_Stats), "Vars }}");
			s("{{#each default", langueConfig.getString(ConfigCles.var_Stats), "Vars }}");
			t(9, "<div");
			s(" class=\"pageSearchVal pageSearchVal-", langueConfig.getString(ConfigCles.var_Stats), classeNomSimple, "_{{ this }} \"");
			s(" id=\"pageSearchVal-", langueConfig.getString(ConfigCles.var_Stats), classeNomSimple, "_{{ this }}\"");
			s(">");
			s("stats.field={{ this }}");
			l("</div>");
			s("{{/each}}");
			tl(0, "{{/if}}");
			tl(8, "</div>");

			tl(8, "<table class=\"w3-table \">");
			tl(0, "{{#each varsFq }}");
			tl(0, "{{#if ", langueConfig.getString(ConfigCles.var_activer), langueConfig.getString(ConfigCles.var_Stats), " }}");
			tl(9, "<tr class=\"\">");
			tl(10, "<td class=\"\">");
			t(11, "<span>");
			s("<input type=\"checkbox\"");
			s(" name=\"page", langueConfig.getString(ConfigCles.var_Stats), "\"");
			s(" class=\"page", langueConfig.getString(ConfigCles.var_Stats), " \"");
			s(" id=\"page", langueConfig.getString(ConfigCles.var_Stats), "", classeNomSimple, "_{{ @key }}\"");
			s(" value=\"{{ var }}\"");
			s("{{#if ./", langueConfig.getString(ConfigCles.var_stats), " }} checked=\"checked\"{{/if}}");
			s(" onclick=\"facet", langueConfig.getString(ConfigCles.var_Stats), "Change('", classeNomSimple, "', this); \"");
			l("/></span>");
			tl(10, "</td>");
			tl(10, "<td class=\"w3-cell \">");
			tl(11, "<div>");
			tl(12, "<label for=\"page", langueConfig.getString(ConfigCles.var_Stats), classeNomSimple, "_{{ @key }}\">{{ ", langueConfig.getString(ConfigCles.var_nomAffichage), " }}</label>");
			tl(11, "</div>");

			t(11, "<div");
			s(" class=\"pageStatsField w3-small pageStatsField", classeNomSimple, "_{{ @key }} \"");
			s(" id=\"pageStatsField", classeNomSimple, "_{{ @key }}\"");
			l(">");
			tl(0, "{{#if ./stats }}");
			tl(0, "{{#each ./stats }}");
			tl(0, "{{#eq @key 'name' }}{{else}}");
			t(12, "<div");
			s(" data-class=\"", classeNomSimple, "\"");
			s(" data-var=\"{{ ../var }}\"");
			s(" data-val=\"{{ @key }}\"");
			s(">");
			s("{{ @key }}");
			s(": ");
			s("{{ this }}");
			l("</div>");
			tl(0, "{{/eq}}");
			tl(0, "{{/each}}");
			tl(0, "{{#if ./stats/max }}");
			t(9, "<div>");
			t(9, "<span> step </span>");
			t(9, "<input id=\"animate", langueConfig.getString(ConfigCles.var_Stats), "Step\" placeholder=\"step\" value=\"1\" style=\"width: 4em; \"/>");
			t(9, "<span> min </span>");
			t(9, "<input id=\"animate", langueConfig.getString(ConfigCles.var_Stats), "Min\" placeholder=\"min\" value=\"{{ ./stats/min }}\" style=\"width: 4em; \"/>");
			t(9, "<span> max </span>");
			t(9, "<input id=\"animate", langueConfig.getString(ConfigCles.var_Stats), "Max\" placeholder=\"max\" value=\"{{ ./stats/max }}\" style=\"width: 4em; \"/>");
			t(9, "<span> speed in seconds </span>");
			t(9, "<input id=\"animate", langueConfig.getString(ConfigCles.var_Stats), "Speed\" placeholder=\"speed\" value=\"1\" style=\"width: 4em; \"/>");
			t(9, "<button onclick=\"animate", langueConfig.getString(ConfigCles.var_Stats), "(); \">animate</button>");
			t(9, "</div>");
			tl(0, "{{/if}}");
			tl(0, "{{/if}}");
			tl(11, "</div>");

			tl(10, "</td>");
			tl(9, "</tr>");
			tl(0, "{{/if}}");
			tl(0, "{{/each}}");
			tl(8, "</table>");
			l("{{/inline}}");

			/////////////////
			// htmBodyMenu //
			/////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Menu), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Menu), classePageNomSimple, "\" -->");
			s("{{> \"htmBody", langueConfig.getString(ConfigCles.var_Menu), classePageSuperNomSimple, "\"}}");
			l("{{/inline}}");

			//////////////////////
			// htmBodyGraphique //
			//////////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), classePageNomSimple, "\" -->");
//			s("{{> \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), classePageSuperNomSimple, "\"}}");
			s("<div id=\"htmBody", langueConfig.getString(ConfigCles.var_Graphique), classePageSuperNomSimple, "\" class=\"w3-content htmBody", langueConfig.getString(ConfigCles.var_Graphique), " \"></div>");
			l("{{/inline}}");

			/////////////////////////////////
			// htmBodyGraphiqueEmplacement //
			/////////////////////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), classePageNomSimple, "\" -->");
//			s("{{> \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), classePageSuperNomSimple, "\"}}");
			s("<div id=\"htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), classePageSuperNomSimple, "\" class=\"w3-content htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), " \"></div>");
			l("{{/inline}}");

			///////////////////
			// htmBodyCount0 //
			///////////////////

			s("{{#*inline \"htmBodyCount0", classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBodyCount0", classePageNomSimple, "\" -->");
			tl(2, "<h1>");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-center w3-block w3-", classeCouleur, " w3-hover-", classeCouleur, "\">");
			tl(4, "{{#if ", langueConfig.getString(ConfigCles.var_classeIconeClassesCss), "}}");
			tl(5, "<i class=\"{{ ", langueConfig.getString(ConfigCles.var_classeIconeClassesCss), " }} site-menu-icon \"></i>");
			tl(4, "{{/if}}");
			tl(4, "<span class=\"\">", classeNomAdjectifPluriel, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");
			tl(2, "<h2>");
			tl(3, "<span class=\"w3-bar-item w3-padding w3-center w3-block w3-", classeCouleur, "\">");
			tl(4, "{{#if ", langueConfig.getString(ConfigCles.var_classeIconeClassesCss), "}}");
			tl(5, "<i class=\"{{ ", langueConfig.getString(ConfigCles.var_classeIconeClassesCss), " }}  site-menu-icon \"></i>");
			tl(4, "{{/if}}");
			tl(4, "<span class=\"\">", classeAucunNomTrouve, "</span>");
			tl(3, "</span>");
			tl(2, "</h2>");

			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), "\"}}{{/block}}");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), "\"}}{{/block}}");
			tl(1, "<div class=\"pageContent w3-content \">");
			tl(2, "<div id=\"site-calendar-box\">");
//			tl(3, "<h3 id=\"site-calendar-title\">Calendar</h3>");
			tl(3, "<div id=\"site-calendar\"><!-- // --></div>");
			tl(2, "</div>");

			l("{{/inline}}");

			///////////////////////
			// htmBodyCount1Tous //
			///////////////////////


			s("{{#*inline \"htmBodyCount1", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBodyCount1", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "\" -->");
			tl(2, "<h1 class=\"w3-center \">");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-block w3-", classeCouleur, " w3-hover-", classeCouleur, "\">");
			tl(3, "{{#if ", langueConfig.getString(ConfigCles.var_classeIconeClassesCss), "}}");
			tl(4, "<i class=\"{{ ", langueConfig.getString(ConfigCles.var_classeIconeClassesCss), " }} site-menu-icon \"></i>");
			tl(3, "{{/if}}");
			tl(4, "<span class=\"\">", classeNomAdjectifSingulier, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");
			tl(2, "<h2 class=\"w3-center \">");
			tl(3, "<span class=\"w3-bar-item w3-padding w3-center w3-block w3-", classeCouleur, "\">");
			tl(4, "<span class=\"\">{{", uncapitalizeClasseApiClasseNomSimple, "_.", langueConfig.getString(ConfigCles.var_objetTitre), "}}</span>");
			tl(3, "</span>");
			tl(2, "</h2>");

			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), "\"}}{{/block}}");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), "\"}}{{/block}}");
			tl(1, "<div class=\"pageContent w3-content \">");
			tl(2, "<div id=\"site-calendar-box\">");
//			tl(3, "<h3 id=\"site-calendar-title\">Calendar</h3>");
			tl(3, "<div id=\"site-calendar\"><!-- // --></div>");
			tl(2, "</div>");

			l("{{/inline}}");

			///////////////////
			// htmBodyCount1 //
			///////////////////
	
			s("{{#*inline \"htmBodyCount1", classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBodyCount1", classePageNomSimple, "\" -->");
			tl(2, "<h1>");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-center w3-block w3-", classeCouleur, " w3-hover-", classeCouleur, "\">");
			tl(1, "{{#if ", langueConfig.getString(ConfigCles.var_classeIconeClassesCss), "}}");
			tl(4, "<i class=\"{{ ", langueConfig.getString(ConfigCles.var_classeIconeClassesCss), " }} site-menu-icon \"></i>");
			tl(1, "{{/if}}");
			tl(4, "<span class=\"\">", classeNomAdjectifSingulier, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");
			tl(2, "<h2 class=\"w3-center \">");
			tl(3, "<span class=\"w3-bar-item w3-padding w3-center w3-block w3-", classeCouleur, "\">");
			tl(4, "<span class=\"\">{{", uncapitalizeClasseApiClasseNomSimple, "_.", langueConfig.getString(ConfigCles.var_objetTitre), "}}</span>");
			tl(3, "</span>");
			tl(2, "</h2>");

			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), "\"}}{{/block}}");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), "\"}}{{/block}}");
			tl(1, "<div class=\"pageContent w3-content \">");
			tl(2, "<div id=\"site-calendar-box\">");
//			tl(3, "<h3 id=\"site-calendar-title\">Calendar</h3>");
			tl(3, "<div id=\"site-calendar\"><!-- // --></div>");
			tl(2, "</div>");

			l("{{/inline}}");

			/////////////////
			// htmBodyTous //
			/////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "\" -->");
			tl(2, "<h1>");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-center w3-block w3-", classeCouleur, " w3-hover-", classeCouleur, "\">");
			tl(1, "{{#if ", langueConfig.getString(ConfigCles.var_classeIconeClassesCss), "}}");
			tl(4, "<i class=\"{{ ", langueConfig.getString(ConfigCles.var_classeIconeClassesCss), " }} site-menu-icon \"></i>");
			tl(1, "{{/if}}");
			tl(4, "<span class=\"\">", classeNomAdjectifPluriel, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");

			tl(2, "<div id=\"site-calendar-box\">");
//			tl(3, "<h3 id=\"site-calendar-title\">Calendar</h3>");
			tl(3, "<div id=\"site-calendar\"><!-- // --></div>");
			tl(2, "</div>");

			tl(2, "<div class=\"\">");
			tl(3, "<div>");
			tl(1, "{{#if pagination.page", langueConfig.getString(ConfigCles.var_Precedent), "}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.page", langueConfig.getString(ConfigCles.var_Precedent), ".", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{pagination.", langueConfig.getString(ConfigCles.var_lignes), "}}\">");
			tl(5, "<i class=\"fas fa-arrow-square-left \"></i>");
			tl(4, "</a>");
			tl(1, "{{else}}");
			tl(4, "<i class=\"fas fa-arrow-square-left w3-opacity \"></i>");
			tl(1, "{{/if}}");
			tl(1, "{{#gte pagination.", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Precedent), " pagination.1L}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{ pagination.", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Precedent), " }}\">");
			tl(5, "<i class=\"fas fa-minus-square \"></i>");
			tl(4, "</a>");
			tl(1, "{{else}}");
			tl(4, "<i class=\"fas fa-minus-square w3-opacity \"></i>");
			tl(1, "{{/gte}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{ pagination.", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Prochaine), " }}\">");
			tl(5, "<i class=\"fas fa-plus-square \"></i>");
			tl(4, "</a>");
			tl(1, "{{#if pagination.page", langueConfig.getString(ConfigCles.var_Prochaine), "}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.page", langueConfig.getString(ConfigCles.var_Prochaine), ".", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{pagination.", langueConfig.getString(ConfigCles.var_lignes), "}}\">");
			tl(5, "<i class=\"fas fa-arrow-square-right \"></i>");
			tl(4, "</a>");
			tl(1, "{{else}}");
			tl(4, "<i class=\"fas fa-arrow-square-right w3-opacity \"></i>");
			tl(1, "{{/if}}");
			tl(4, "<span>{{ pagination.", langueConfig.getString(ConfigCles.var_debut), "Num }} - {{ pagination.", langueConfig.getString(ConfigCles.var_fin), "Num }} ", langueConfig.getString(ConfigCles.var_de), " {{ pagination.", langueConfig.getString(ConfigCles.var_numTrouve), " }}</span>");
			tl(3, "</div>");
			tl(0, "{{> \"table1", classePageNomSimple, "\"}}");
			tl(2, "</div>");
			l("{{/inline}}");

			if(classeVarClePrimaire != null || !classeModele) {
				l("{{#*inline \"htm", langueConfig.getString(ConfigCles.var_Formulaire), classePageNomSimple, "\"}}");
				tl(2, "<!-- #*inline \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "\" -->");
				tl(2, "<form action=\"", classeApiUri, "\" id=\"", classeApiClasseNomSimple, "Form\" class=\"", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Formulaire), " \" style=\"\" onsubmit=\"event.preventDefault(); return false; \">");
				tl(0, "{{#if ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}");
				t(3, "<input");
				s(" name=\"", classeModele ? classeVarClePrimaire : classeVarCleUnique, "\"");
				s(" class=\"", langueConfig.getString(ConfigCles.var_valeur), StringUtils.capitalize(classeModele ? classeVarClePrimaire : classeVarCleUnique), "\"");
				s(" type=\"hidden\"");
				s(" value=\"{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}\"");
				l("/>");
				tl(0, "{{/if}}");
				t(3, "<input");
				s(" name=\"focusId\"");
				s(" type=\"hidden\"");
				l("/>");
				t(3, "<input");
				s(" name=\"", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Reponse), "\"");
				s(" id=\"", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Reponse), "\"");
				s(" class=\"", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Reponse), "\" ");
				s(" value='{{{toJsonObjectStringInApostrophes ", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Reponse), " }}}'");
				s(" type=\"hidden\"");
				l("/>");
				t(3, "<input");
				s(" name=\"", StringUtils.uncapitalize(classeNomSimple), "\"");
				s(" id=\"", StringUtils.uncapitalize(classeNomSimple), "\"");
				s(" class=\"", StringUtils.uncapitalize(classeNomSimple), "\" ");
				s(" value='{{{toJsonObjectStringInApostrophes ", StringUtils.uncapitalize(classeNomSimple), "_ }}}'");
				s(" type=\"hidden\"");
				l("/>");
				t(3, "<input");
				s(" name=\"", langueConfig.getString(ConfigCles.var_liste), classeNomSimple, "\"");
				s(" id=\"", langueConfig.getString(ConfigCles.var_liste), classeNomSimple, "\"");
				s(" class=\"", langueConfig.getString(ConfigCles.var_liste), classeNomSimple, "\" ");
				s(" value='{{{toJsonArrayStringInApostrophes ", langueConfig.getString(ConfigCles.var_liste), classeNomSimple, " }}}'");
				s(" type=\"hidden\"");
				l("/>");
				tl(2, "</form>");
				tl(0, "{{#if ", classeVarCleUnique, "}}");
				l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Bouton), "_", StringUtils.lowerCase(langueConfig.getString(ConfigCles.var_PageRecherche)), classeApiClasseNomSimple, "\"}}{{/block}}");
				l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "_", StringUtils.lowerCase(langueConfig.getString(ConfigCles.var_PageRecherche)), classeApiClasseNomSimple, "\"}}{{/block}}");
				tl(0, "{{/if}}");
				l("{{/inline}}");
			}

			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");

				if(classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PageRecherche)) || classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTCopie)) || classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
					String methodeTitreFiltres = null;
					String methodeTitreValeurs = null;

					if("POST".equals(classeApiMethodeMethode)) {
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Creer_) + classeUnNom;
					}
					else if("PUTImport".equals(classeApiMethode)) {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + classeUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Importer_) + classeNomPluriel;
					}
					else if(langueConfig.getString(ConfigCles.var_PUTFusion).equals(classeApiMethode)) {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + classeUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Fusionner_) + classeNomPluriel;
					}
					else if(langueConfig.getString(ConfigCles.var_PUTCopie).equals(classeApiMethode)) {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + classeUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Dupliquer_) + classeNomSingulier;
					}
					else if("PATCH".equals(classeApiMethodeMethode)) {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + classeUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Modifier_des_) + classeNomSingulier;
					}
					else if("DELETE".equals(classeApiMethodeMethode)) {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + classeUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Supprimer_) + classeNomSingulier;
					}
					else {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + classeUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Rechercher_) + classeNomPluriel;
					}

					l("{{#*inline \"htm", langueConfig.getString(ConfigCles.var_Bouton), classePageNomSimple, "_", classeApiOperationIdMethode, "\"}}");
					if(!classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PageRecherche))) {
						tl(2, "<button");
						tl(3, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", classeCouleur, " \"");
						tl(3, "onclick=\"$('#", classeApiOperationIdMethode, langueConfig.getString(ConfigCles.var_Modale), "').show(); \"");
						tl(3, ">");
	
							if(classeApiMethodeMethode.contains("POST"))
								tl(3, "<i class=\"fas fa-file-plus \"></i>");
							else if(classeApiMethodeMethode.contains("PATCH"))
								tl(3, "<i class=\"fas fa-edit \"></i>");
							else if(classeApiMethode.contains("PUTImport"))
								tl(3, "<i class=\"fas fa-file-import \"></i>");
							else if(classeApiMethode.contains(langueConfig.getString(ConfigCles.var_PUTFusion)))
								tl(3, "<i class=\"fas fa-code-merge \"></i>");
							else if(classeApiMethode.contains(langueConfig.getString(ConfigCles.var_PUTCopie)))
								tl(3, "<i class=\"fas fa-copy \"></i>");
	
							tl(3, methodeTitreValeurs);
						tl(2, "</button>");
					}
					l("{{/inline}}");
					l("{{#*inline \"htm", langueConfig.getString(ConfigCles.var_Formulaire), langueConfig.getString(ConfigCles.var_Bouton), classePageNomSimple, "_", classeApiOperationIdMethode, "\"}}");
					tl(6, "<button");
					tl(7, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-", classeCouleur, " \"");
					if("POST".equals(classeApiMethodeMethode))
						tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \"");
					else if("PATCH".equals(classeApiMethode))
						tl(7, "onclick=\"", classeApiOperationIdMethode, "(null, $('#", classeApiOperationIdMethode, classePageLangueConfig.getString(ConfigCles.var_FormulaireValeurs), "')); \"");
					else if("PUTImport".equals(classeApiMethode))
						tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, classePageLangueConfig.getString(ConfigCles.var_FormulaireValeurs), "')); \"");
					else if(langueConfig.getString(ConfigCles.var_PUTFusion).equals(classeApiMethode))
						tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, classePageLangueConfig.getString(ConfigCles.var_FormulaireValeurs), "'), ", classeModele ? "{{ " + classeVarClePrimaire + " }}" : "'{{ " + classeVarCleUnique + " }}'", "); \"");
					else if(langueConfig.getString(ConfigCles.var_PUTCopie).equals(classeApiMethode))
						tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, classePageLangueConfig.getString(ConfigCles.var_FormulaireValeurs), "'), ", classeModele ? "{{ " + classeVarClePrimaire + " }}" : "'{{ " + classeVarCleUnique + " }}'", "); \"");
					else
						tl(7, "onclick=\"", classeApiOperationIdMethode, "(); \"");

					tl(7, ">", methodeTitreValeurs, "</button>");
					l("{{/inline}}");
					l("{{#*inline \"htm", langueConfig.getString(ConfigCles.var_Formulaire), classePageNomSimple, "_", classeApiOperationIdMethode, "\"}}");
					{ tl(2, "<div id=\"", classeApiOperationIdMethode, langueConfig.getString(ConfigCles.var_Modale), "\" class=\"", classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PageRecherche)) ? "" : "w3-modal ", "\">");
						{ tl(3, "<div class=\"", classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PageRecherche)) ? "" : "w3-modal-content ", "\">");
							{ tl(4, "<div class=\"w3-card-4 \">");
								if(!langueConfig.getString(ConfigCles.var_PageRecherche).equals(classeApiMethode)) {
									{ tl(5, "<header class=\"w3-container w3-", classeCouleur, "\">");
										tl(1, "{{#eq \"Page\" ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
										tl(6, "<span class=\"w3-button w3-display-topright \" onclick=\"$('#", classeApiOperationIdMethode, langueConfig.getString(ConfigCles.var_Modale), "').hide(); \">×</span>");
										tl(1, "{{/eq}}");
										tl(6, "<h2 class=\"w3-padding \">", methodeTitreValeurs, "</h2>");
										if("POST".equals(classeApiMethode)) {
											tl(6, "<div class=\"\">", langueConfig.getString(ConfigCles.str_Vous_pouvez_remplacer_les_valeurs_par_defaut_ci_dessous), "</div>");
										}
									} tl(5, "</header>");
								}
	
								{ tl(5, "<div class=\"\" id=\"", classeApiOperationIdMethode, classePageLangueConfig.getString(ConfigCles.var_FormulaireValeurs), "\">");
//											TODO
//											tl(7+ tab, classeApiClasseNomSimple, " o = new ", classeApiClasseNomSimple, "();");
////											tl(7+ tab, "o.", langueConfig.getString(ConfigCles.var_initLoin), langueConfig.getString(ConfigCles.var_PourClasse), "(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
//											tl(7+ tab, "o.set", langueConfig.getString(ConfigCles.var_RequeteSite), "_(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
									if("PATCH".equals(classeApiMethode) || langueConfig.getString(ConfigCles.var_PUTFusion).equals(classeApiMethode) || langueConfig.getString(ConfigCles.var_PUTCopie).equals(classeApiMethode) || "PUTImport".equals(classeApiMethode)) {
	
										if("PUTImport".equals(classeApiMethode)) {
											tl(6, "<div class=\"w3-cell-row \">");
											tl(7, "<textarea");
											tl(8, "class=\"", "PUTImport_", langueConfig.getString(ConfigCles.var_listeRecherche), " w3-input w3-border \"");
											tl(8, "style=\"height: 300px; \"");
											tl(8, "placeholder=\"{ '", langueConfig.getString(ConfigCles.var_listeRecherche), "': [ { 'pk': ... , '", langueConfig.getString(ConfigCles.var_sauvegardes), "': [ ... ] }, ... ] }\"");
											tl(8, "></textarea>");
											tl(6, "</div>");
										} else if(langueConfig.getString(ConfigCles.var_PUTFusion).equals(classeApiMethode)) {
											tl(6, "<div class=\"w3-cell-row \">");
											tl(7, "<textarea");
											tl(8, "class=\"", "PUT", langueConfig.getString(ConfigCles.var_PUTFusion), "_", langueConfig.getString(ConfigCles.var_listeRecherche), " w3-input w3-border \"");
											tl(8, "style=\"height: 300px; \"");
											tl(8, "placeholder=\"{ '", langueConfig.getString(ConfigCles.var_listeRecherche), "': [ { 'pk': ... , '", langueConfig.getString(ConfigCles.var_sauvegardes), "': [ ... ] }, ... ] }\"");
											tl(8, "></textarea>");
											tl(6, "</div>");
										} else if(langueConfig.getString(ConfigCles.var_PUTCopie).equals(classeApiMethode)) {
											s(wFormPUTCopie);
										} else if("PATCH".equals(classeApiMethodeMethode)) {
											s(wFormPATCH);
										}
	
										l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), langueConfig.getString(ConfigCles.var_Bouton), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
									} else {
										{ tl(6, "<div id=\"", classeApiOperationIdMethode, "Form\">");
											if(!langueConfig.getString(ConfigCles.var_PageRecherche).equals(classeApiMethode)) {
												l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), langueConfig.getString(ConfigCles.var_Bouton), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
											}
		
											if("POST".equals(classeApiMethode)) {
												s(wFormPOST);
											} else if(langueConfig.getString(ConfigCles.var_Recherche).equals(classeApiMethode)) {
												s(wFormRecherche);
											} else {
												s(wFormPage);
											}
		
										} tl(6, "</div>");
										if(!langueConfig.getString(ConfigCles.var_PageRecherche).equals(classeApiMethode)) {
											l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), langueConfig.getString(ConfigCles.var_Bouton), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
										}
									}
								} tl(5, "</div>");
							} tl(4, "</div>");
						} tl(3, "</div>");
					} tl(2, "</div>");
					l("{{/inline}}");
				}
			}

//			l("{{#*inline \"htmBodyCount1", classePageNomSimple, "\"}}");
//			l("{{/inline}}");

			tl(0, "{{#*inline \"htmBodySidebar", classePageNomSimple, "\"}}");

			/////////////
			// sidebar //
			/////////////

			tl(4, "<div class=\"w3-dropdown-click w3-bar-block min-width-300px \">");
			tl(5, "<div class=\"min-width-300px \">");

			//////////////
			// bouton q //
			//////////////
			t(6, "<span");
			s(" title=\"", langueConfig.getString(ConfigCles.var_Recherche), "\"");
			s(" class=\"siteSidebarToggle w3-padding-small w3-hover-black w3-", classeCouleur, " \"");
			s(" onclick=\"$('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Stats), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_ListeChamps), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), "').toggle(); $('.siteSidebarToggle').removeClass('w3-black'); $('.siteSidebarToggle').addClass('w3-", classeCouleur, "'); $(this).addClass('w3-black'); $(this).removeClass('w3-", classeCouleur, "'); \"");
			s(">");
			s("<i class=\"fad fa-magnifying-glass hover-box-shadow w3-xlarge \"></i> ", langueConfig.getString(ConfigCles.var_Rechercher));
			l("</span>");

			///////////////
			// bouton fq //
			///////////////
			t(6, "<span");
			s(" title=\"", langueConfig.getString(ConfigCles.var_Filtres), "\"");
			s(" class=\"siteSidebarToggle w3-padding-small w3-hover-black w3-", classeCouleur, " \"");
			s(" onclick=\"$('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Stats), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_ListeChamps), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), "').toggle(); $('.siteSidebarToggle').removeClass('w3-black'); $('.siteSidebarToggle').addClass('w3-", classeCouleur, "'); $(this).addClass('w3-black'); $(this).removeClass('w3-", classeCouleur, "'); \"");
			s(">");
			s("<i class=\"fad fa-filters hover-box-shadow w3-xlarge \"></i> ", langueConfig.getString(ConfigCles.var_Filtres));
			l("</span>");

			//////////////////
			// bouton gamme //
			//////////////////
			t(6, "<span");
			s(" title=\"", langueConfig.getString(ConfigCles.var_Gamme), "\"");
			s(" class=\"siteSidebarToggle w3-padding-small w3-hover-black w3-", classeCouleur, " \"");
			s(" onclick=\"$('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Stats), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_ListeChamps), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), "').toggle(); $('.siteSidebarToggle').removeClass('w3-black'); $('.siteSidebarToggle').addClass('w3-", classeCouleur, "'); $(this).addClass('w3-black'); $(this).removeClass('w3-", classeCouleur, "'); \"");
			s(">");
			s("<i class=\"fad fa-calendar-range hover-box-shadow w3-xlarge \"></i> ", langueConfig.getString(ConfigCles.var_Gamme));
			l("</span>");

			//////////////////
			// bouton pivot //
			//////////////////
			t(6, "<span");
			s(" title=\"", langueConfig.getString(ConfigCles.var_Pivot), "\"");
			s(" class=\"siteSidebarToggle w3-padding-small w3-hover-black w3-", classeCouleur, " \"");
			s(" onclick=\"$('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Stats), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_ListeChamps), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), "').toggle(); $('.siteSidebarToggle').removeClass('w3-black'); $('.siteSidebarToggle').addClass('w3-", classeCouleur, "'); $(this).addClass('w3-black'); $(this).removeClass('w3-", classeCouleur, "'); \"");
			s(">");
			s("<i class=\"fad fa-table-pivot hover-box-shadow w3-xlarge \"></i> ", langueConfig.getString(ConfigCles.var_Pivot));
			l("</span>");

			/////////////////////////
			// bouton liste champs //
			/////////////////////////
			t(6, "<span");
			s(" title=\"", langueConfig.getString(ConfigCles.var_ListeChamps), "\"");
			s(" class=\"siteSidebarToggle w3-padding-small w3-hover-black w3-", classeCouleur, " \"");
			s(" onclick=\"$('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Stats), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_ListeChamps), "').toggle(); $('.siteSidebarToggle').removeClass('w3-black'); $('.siteSidebarToggle').addClass('w3-", classeCouleur, "'); $(this).addClass('w3-black'); $(this).removeClass('w3-", classeCouleur, "'); \"");
			s(">");
			s("<i class=\"fad fa-list-ul hover-box-shadow w3-xlarge \"></i> ", langueConfig.getString(ConfigCles.var_ListeChamps));
			l("</span>");

			//////////////////
			// bouton stats //
			//////////////////
			t(6, "<span");
			s(" title=\"", langueConfig.getString(ConfigCles.var_Stats), "\"");
			s(" class=\"siteSidebarToggle w3-padding-small w3-hover-black w3-", classeCouleur, " \"");
			s(" onclick=\"$('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_ListeChamps), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Stats), "').toggle(); $('.siteSidebarToggle').removeClass('w3-black'); $('.siteSidebarToggle').addClass('w3-", classeCouleur, "'); $(this).addClass('w3-black'); $(this).removeClass('w3-", classeCouleur, "'); \"");
			s(">");
			s("<i class=\"fad fa-chart-candlestick hover-box-shadow w3-xlarge \"></i> ", langueConfig.getString(ConfigCles.var_Stats));
			l("</span>");

			////////////////
			// bouton API //
			////////////////
			t(6, "<a");
			s(" title=\"", langueConfig.getString(ConfigCles.str_API), "\"");
			s(" class=\"siteSidebarToggle w3-padding-small w3-hover-black w3-", classeCouleur, " \"");
			s(" href=\"{{ apiUri }}{{#if pk }}/{{ pk }}{{else}}{{#if id }}/{{ id }}{{else}}{{ queryStr }}{{/if}}{{/if}}\"");
			s(">");
			s("<i class=\"fad fa-brackets-curly hover-box-shadow w3-xlarge \"></i> ", langueConfig.getString(ConfigCles.str_API));
			l("</a>");


			tl(5, "</div>");
			tl(5, "<div class=\" \">");


			///////////////
			// sidebar q //
			///////////////

			tl(6, "<div class=\"siteSidebarToggle siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), " w3-dropdown-content min-width-300px w3-white w3-border w3-border-", classeCouleur, " \" style=\"display: none; \">");
			tl(7, "<div class=\"w3-bar w3-", classeCouleur, " \">");
			tl(8, "<span class=\"w3-bar-item \">");
			t(8, "<i class=\"fad fa-magnifying-glass \"></i>");
			l(" ", langueConfig.getString(ConfigCles.var_Recherche), "</span>");
			tl(7, "</div>");
			tl(7, "<div class=\"w3-bar-block \">");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Recherche), "\"}}{{/block}}");
			tl(7, "</div>");
			tl(6, "</div>");

			////////////////
			// sidebar fq //
			////////////////

			tl(6, "<div  class=\"siteSidebarToggle siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), " w3-dropdown-content min-width-300px w3-white w3-border w3-border-", classeCouleur, " \" style=\"display: none; \">");
			tl(7, "<div class=\"w3-bar w3-", classeCouleur, " \">");
			tl(8, "<span class=\"w3-bar-item \">");
			t(8, "<i class=\"fad fa-filters \"></i>");
			l(" ", langueConfig.getString(ConfigCles.var_Filtres), "</span>");
			tl(7, "</div>");
			tl(7, "<div class=\"w3-bar-block \">");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Filtres), "\"}}{{/block}}");
			tl(7, "</div>");
			tl(6, "</div>");

			///////////////////
			// sidebar gamme //
			///////////////////

			tl(6, "<div  class=\"siteSidebarToggle siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), " w3-dropdown-content min-width-300px w3-white w3-border w3-border-", classeCouleur, " \" style=\"display: none; \">");
			tl(7, "<div class=\"w3-bar w3-", classeCouleur, "  \">");
			tl(8, "<span class=\"w3-bar-item \">");
			t(8, "<i class=\"fad fa-calendar-range \"></i>");
			l(" ", langueConfig.getString(ConfigCles.var_Gamme), "</span>");
			tl(7, "</div>");
			tl(7, "<div class=\"w3-bar-block \">");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Gamme), "\"}}{{/block}}");
			tl(7, "</div>");
			tl(6, "</div>");

			///////////////////
			// sidebar pivot //
			///////////////////

			tl(6, "<div  class=\"siteSidebarToggle siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), " w3-dropdown-content min-width-300px w3-white w3-border w3-border-", classeCouleur, " \" style=\"display: none; \">");
			tl(7, "<div class=\"w3-bar w3-", classeCouleur, " \">");
			tl(8, "<span class=\"w3-bar-item \">");
			t(8, "<i class=\"fad fa-table-pivot \"></i>");
			l(" ", langueConfig.getString(ConfigCles.var_Pivot), "</span>");
			tl(7, "</div>");
			tl(7, "<div class=\"w3-bar-block \">");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Pivot), "\"}}{{/block}}");
			tl(7, "</div>");
			tl(6, "</div>");

			//////////////////////////
			// sidebar liste champs //
			//////////////////////////

			tl(6, "<div  class=\"siteSidebarToggle siteSidebarToggle", langueConfig.getString(ConfigCles.var_ListeChamps), " w3-dropdown-content min-width-300px w3-white w3-border w3-border-", classeCouleur, " \" style=\"display: none; \">");
			tl(7, "<div class=\"w3-bar w3-", classeCouleur, " \">");
			tl(8, "<span class=\"w3-bar-item \">");
			t(8, "<i class=\"fad fa-list-ul \"></i>");
			l(" ", langueConfig.getString(ConfigCles.str_Liste_Champs), "</span>");
			tl(7, "</div>");
			tl(7, "<div class=\"w3-bar-block \">");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_ListeChamps), "\"}}{{/block}}");
			tl(7, "</div>");
			tl(6, "</div>");

			///////////////////
			// sidebar stats //
			///////////////////

			tl(6, "<div  class=\"siteSidebarToggle siteSidebarToggle", langueConfig.getString(ConfigCles.var_Stats), " w3-dropdown-content min-width-300px w3-white w3-border w3-border-", classeCouleur, " \" style=\"display: none; \">");
			tl(7, "<div class=\"w3-bar w3-", classeCouleur, " \">");
			tl(8, "<span class=\"w3-bar-item \">");
			t(8, "<i class=\"fad fa-chart-candlestick \"></i>");
			l(" ", langueConfig.getString(ConfigCles.str_Stats), "</span>");
			tl(7, "</div>");
			tl(7, "<div class=\"w3-bar-block \">");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Stats), "\"}}{{/block}}");
			tl(7, "</div>");
			tl(6, "</div>");


			tl(5, "</div>");
			tl(4, "</div>");

			tl(0, "{{/inline}}");

			/////////////
			// htmBody //
			/////////////

			tl(0, "{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Debut), classePageNomSimple, "\"}}");
			tl(0, "{{> \"htmBody", langueConfig.getString(ConfigCles.var_Debut), classePageSuperNomSimple, "\"}}");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Milieu), classePageNomSimple, "\"}}");
			tl(0, "{{> \"htmBody", langueConfig.getString(ConfigCles.var_Milieu), classePageSuperNomSimple, "\"}}");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Fin), classePageNomSimple, "\"}}");
			tl(0, "{{> \"htmBody", langueConfig.getString(ConfigCles.var_Fin), classePageSuperNomSimple, "\"}}");
			tl(0, "{{/inline}}");

			tl(0, "{{#*inline \"htmBody", classePageNomSimple, "\"}}");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Debut), "\"}}{{/block}}");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Milieu), "\"}}{{/block}}");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Fin), "\"}}{{/block}}");
			tl(0, "{{/inline}}");

			/////////////////
			// pageContent //
			/////////////////

			tl(0, "{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Milieu), classePageNomSimple, "\"}}");
			tl(2, "<div class=\"w3-bar w3-content \">");
			tl(0, "{{#block \"htmBodySidebar\"}}{{/block}}");
			tl(2, "</div>");

			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), "\"}}{{/block}}");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Graphique), "\"}}{{/block}}");
			tl(1, "<div class=\"pageContent w3-content \">");

			// htmBodyCount0 //
			tl(1, "{{#eq ", uncapitalizeClasseApiClasseNomSimple, "Count int0}}");
			tl(0, "{{#block \"htmBodyCount0\"}}{{/block}}");
			tl(1, "{{else}}");

			tl(2, "{{#eq ", uncapitalizeClasseApiClasseNomSimple, "Count int1}}");
			// htmBodyCount1 //
			tl(3, "{{#if ", classeVarCleUnique, "}}");
			tl(0, "{{#block \"htmBodyCount1\"}}{{/block}}");
			tl(3, "{{else}}");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Tous), "\"}}{{/block}}");
//			tl(0, "{{#block \"htmBodyCount1", langueConfig.getString(ConfigCles.var_Tous), "\"}}{{/block}}");
			tl(3, "{{/if}}");
			tl(2, "{{else}}");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Tous), "\"}}{{/block}}");
			tl(2, "{{/eq}}");
			tl(1, "{{/eq}}");
			tl(0, "{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "\"}}{{/block}}");
			if(classeMethodeVars.contains("htmBody")) {
				tl(6, "{{#if o}}");
				tl(7, "{{> \"htmBody\"}}");
				tl(6, "{{/if}}");
			}

			// formulaires
			if(!classePageSimple) {
				l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaires), "\"}}{{/block}}");
			}

			tl(1, "</div>");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"table1", classePageNomSimple, "\"}}");
			tl(2, "<div class=\"w3-responsive \">");
			tl(3, "<table class=\"w3-table w3-bordered w3-striped w3-border w3-hoverable \">");
			tl(0, "{{> table2", classePageNomSimple, "}}");
			tl(3, "</table>");
			tl(2, "</div>");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"table2", classePageNomSimple, "\"}}");
			tl(0, "{{> \"thead1", classePageNomSimple, "\"}}");
			tl(0, "{{> \"tbody1", classePageNomSimple, "\"}}");
			tl(0, "{{> \"tfoot1", classePageNomSimple, "\"}}");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"thead1", classePageNomSimple, "\"}}");
			tl(4, "<thead class=\"w3-", classeCouleur, " w3-hover-", classeCouleur, "\">");
			tl(0, "{{> thead2", classePageNomSimple, "}}");
			tl(4, "</thead>");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"thead2", classePageNomSimple, "\"}}");
			tl(3, "<tr>");
			s(wTh);
			tl(3, "</tr>");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"tbody1", classePageNomSimple, "\"}}");
			tl(0, "<!-- #*inline \"tbody1", classePageNomSimple, "\" -->");
			tl(4, "<tbody>");
			tl(0, "{{> tbody2", classePageNomSimple, "}}");
			tl(4, "</tbody>");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"tbody2", classePageNomSimple, "\"}}");
			tl(0, "<!-- #*inline \"tbody2", classePageNomSimple, "\" -->");
//				TODO
//				tl(2, "Map<String, Map<String, List<String>>> highlighting = ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getResponse().getHighlighting();");
			tl(2, "{{#each ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, "}}");
//				TODO
//				tl(3, classeApiClasseNomSimple, " o = ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getList().get(i);");
//				tl(3, "Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());");
//				tl(3, "List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));");
//				tl(3, "String uri = ", classeEntiteVars.contains("pageUri") ? "o.getPageUri()" : (q(classePageUriMethode, "/") + " + o.get" + StringUtils.capitalize(classeVarClePrimaire) + "()"), ";");
			tl(5, "<tr>");
			s(wTd);
			tl(5, "</tr>");
			tl(2, "{{/each}}");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"tfoot1", classePageNomSimple, "\"}}");
			tl(4, "<tfoot class=\"w3-", classeCouleur, " w3-hover-", classeCouleur, "\">");
			tl(0, "{{> tfoot2", classePageNomSimple, "}}");
			tl(4, "</tfoot>");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"tfoot2", classePageNomSimple, "\"}}");
			tl(3, "<tr>");
//				TODO
//				tl(3, "SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getResponse()).map(QueryResponse::getResponse).map(r -> r.get(\"facets\")).orElse(new SimpleOrderedMap());");
			s(wFoot);
			tl(3, "</tr>");
			tl(1, "{{/inline}}");
			s(wGetters);
			tl(0, "{{#*inline \"htm", langueConfig.getString(ConfigCles.var_Formulaires), classePageNomSimple, "\"}}");
			tl(0, "{{#ifContainsAnyRoles roles ", langueConfig.getString(ConfigCles.var_authRoleSuperAdmin), "}}");

			// refraîchir 1 //
			tl(2, "{{#eq ", uncapitalizeClasseApiClasseNomSimple, "Count int1}}");
			tl(2, "<button");
			tl(3, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", classeCouleur, " \"");
			tl(3, "id=\"", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(classeCe)), classeGenPageNomSimple, "\"");
			tl(3, "onclick=\"patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Vals( [ {name: 'fq', value: '", classeModele ? classeVarClePrimaire : classeVarCleUnique, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' } ], {}, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(classeCe)), classeGenPageNomSimple, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(classeCe)), classeGenPageNomSimple, "')); }); return false; \">");
			tl(3, "<i class=\"fas fa-sync-alt \"></i>");
			tl(3, langueConfig.getString(ConfigCles.var_recharger), " ", classeCeNom);
			tl(2, "</button>");
			tl(2, "{{/eq}}");

			tl(0, "{{/ifContainsAnyRoles}}");

			// formulaires //
			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");

				if(classeApiMethode.equals("POST")) {
					if(activerRoleAdmin) {
						l("{{#ifContainsAnyRoles roles ", langueConfig.getString(ConfigCles.var_authRoleAdmin), "}}");
					}
					l("{{#eq ", uncapitalizeClasseApiClasseNomSimple, "Count int1}}");
					l("{{#if ", classeVarCleUnique, "}}");
					l("{{else}}");
					l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Bouton), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
					l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
					l("{{/if}}");
					l("{{else}}");
					l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Bouton), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
					l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
					l("{{/eq}}");
					if(activerRoleAdmin) {
						l("{{/ifContainsAnyRoles}}");
					}
				} else if(classeApiMethode.equals("PATCH") || classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
					tl(0, "{{#ifContainsAnyRoles roles ", langueConfig.getString(ConfigCles.var_authRoleSuperAdmin), "}}");
					l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Bouton), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
					l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
					tl(0, "{{/ifContainsAnyRoles}}");
				} else if(classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTCopie))) {
					l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Bouton), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
					l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
				}
			}

			l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Suggere), "\"}}{{/block}}");
			tl(0, "{{/inline}}");

			if(!classePageSimple) {
				l("{{#*inline \"htm", langueConfig.getString(ConfigCles.var_Suggere), classePageNomSimple, "\"}}");

				tl(3, "{{#ifContainsAnyRoles roles ", langueConfig.getString(ConfigCles.var_rolePourEcrire), "}}");

				// recharger tous //
//						t(4).s("{{# if ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, " == null) {").l();
				tl(5, "<div class=\"\">");
				tl(6, "<button id=\"", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(classeTous)), classeGenPageNomSimple, "{{id}}\" class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", classeCouleur, " \"");
				tl(7, "onclick=\"patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Vals([], {}, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(classeTous)), classeGenPageNomSimple, "{{id}}')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(classeTous)), classeGenPageNomSimple, "{{id}}')); }); \"");
				tl(7, ">");
				tl(7, "<i class=\"fas fa-sync-alt \"></i>");
				tl(7, langueConfig.getString(ConfigCles.var_recharger), " ", classeTousNom);
				tl(6, "</button>");
				tl(5, "</div>");
//						t(4, "}").l();

				tl(3, "{{/ifContainsAnyRoles}}");

				tl(3, "<div class=\"w3-cell-row \">");
				tl(4, "<div class=\"w3-cell \">");
				tl(5, "<span>");
				tl(6, langueConfig.getString(ConfigCles.var_rechercher), " ", classeNomAdjectifPluriel, langueConfig.getString(ConfigCles.str_deuxPoints));
				tl(5, "</span>");
				tl(4, "</div>");
				tl(3, "</div>");
				tl(3, "<div class=\"w3-bar \">");

				tl(4, "<input");
				tl(5, "type=\"text\"");

				if(classeRechercherTousNom != null) {
					tl(5, "placeholder=\"", classeRechercherTousNom, "\"");
				}

				tl(5, "class=\"", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, " w3-input w3-border w3-bar-item \"");
				tl(5, "name=\"", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, "\"");
				tl(5, "id=\"", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, "{{id}}\"");
				tl(5, "autocomplete=\"off\"");
				tl(5, "oninput=\"", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, StringUtils.capitalize(classeVarSuggere), "( [ { 'name': 'q', 'value': '", classeVarSuggere, ":' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", langueConfig.getString(ConfigCles.var_classeNomCanonique), ",", classeVarClePrimaire, classeVarUrlPk == null ? "" : "," + classeVarUrlPk, classeVarTitre == null ? "" : "," + classeVarTitre, "' } ], $('#", langueConfig.getString(ConfigCles.var_suggere), "List", classeApiClasseNomSimple, "{{id}}'), {{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}; \"");
				tl(5, "onkeyup=\"if (event.keyCode === 13) { event.preventDefault(); window.location.href = '", classePageUriMethode + "?q={{query1}}:' + encodeURIComponent(this.value) + '{{fqs}}{{sorts}}&amp;rows={{start2}}&amp;rows={{rows1}}\"");
				tl(4, "{{#if ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, "}}");
				tl(5, "value=\"{{query2}}\"");
				tl(4, "{{/if}}");
				tl(4, "/>");
				tl(4, "<button");
				tl(5, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-", classeCouleur, " \"");
				tl(5, "onclick=\"window.location.href = '", classePageUriMethode + "?q=&quot;, query1, &quot;:' + encodeURIComponent(this.previousElementSibling.value) + '&quot;, fqs, sorts, &quot;&amp;rows=&quot;, start2, &quot;&amp;rows=&quot;, rows1, &quot;'; \"");
				tl(5, ">");
				tl(5, "<i class=\"fas fa-search \"></i>");
				tl(4, "</button>");

				tl(3, "<div>");
				tl(3, "<div class=\"w3-cell-row \">");
				tl(4, "<div class=\"w3-cell w3-left-align w3-cell-top \">");
				tl(5, "<ul class=\"w3-ul w3-hoverable \" id=\"", langueConfig.getString(ConfigCles.var_suggere), "List", classeApiClasseNomSimple, "{{id}}\">");
				tl(5, "</ul>");
				tl(4, "</div>");
				tl(3, "</div>");

				// voir tous //
				tl(3, "<div class=\"\">");
				tl(4, "<a href=\"", classePageUriMethode, "\" class=\"\">");
				if(classeIconeGroupe != null && classeIconeNom != null)
					tl(5, "<i class=\"fa", StringUtils.substring(classeIconeGroupe, 0, 1), " fa-", classeIconeNom, "\"></i>");
				tl(5, langueConfig.getString(ConfigCles.var_voir), " ", classeTousNom);
				tl(4, "</a>");
				tl(3, "</div>");

//				tl(2, "} catch(Exception e) {");
//				tl(3, "ExceptionUtils.rethrow(e);");
//				tl(2, "}");
				tl(0, "{{/inline}}");
				l("{{> ", classePageSuperNomSimple
						, "Object".equals(classeNomSimpleSuperGenerique) ? "" : (
								" " + StringUtils.uncapitalize(classeNomSimpleSuperGenerique) + "_=" + uncapitalizeClasseApiClasseNomSimple + "_"
								), "}}");

				if(classeMethodeVars.contains("htmBody" + langueConfig.getString(ConfigCles.var_Court))) {
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(0, "{{#*inline \"htmBodyCourt", classePageNomSimple, "\"}}");
					tl(2, uncapitalizeClasseApiClasseNomSimple, ".htmBody" + langueConfig.getString(ConfigCles.var_Court) + "();");
					tl(0, "{{/inline}}");
				}

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
				auteurPageJs.tl(3, "var pkPage = $('#", classeApiClasseNomSimple, "Form :input[name=", classeModele ? classeVarClePrimaire : classeVarCleUnique, "]').val();");
				auteurPageJs.tl(3, "var pks = json['pks'];");
				auteurPageJs.tl(3, "var empty = json['empty'];");
//					auteurPageJs.tl(3, "if(!empty) {");
				auteurPageJs.tl(3, "var numFound = parseInt(json['numFound']);");
				auteurPageJs.tl(3, "var numPATCH = parseInt(json['numPATCH']);");
				auteurPageJs.tl(3, "var percent = Math.floor( numPATCH / numFound * 100 ) + '%';");
				auteurPageJs.tl(3, "var $box = $('<div>').attr('class', 'w3-quarter box-' + id + ' ').attr('id', 'box-' + id).attr('data-numPATCH', numPATCH);");
				auteurPageJs.tl(3, "var $margin = $('<div>').attr('class', 'w3-margin ').attr('id', 'margin-' + id);");
				auteurPageJs.tl(3, "var $card = $('<div>').attr('class', 'w3-card w3-white ').attr('id', 'card-' + id);");
				auteurPageJs.tl(3, "var $header = $('<div>').attr('class', 'w3-container fa-", classeCouleur, " ').attr('id', 'header-' + id);");
				auteurPageJs.tl(3, "var $i = $('<i>').attr('class', 'fa", StringUtils.substring(classeIconeGroupe, 0, 1), " fa-", classeIconeNom, " w3-margin-right ').attr('id', 'icon-' + id);");
				auteurPageJs.tl(3, "var $headerSpan = $('<span>').attr('class', '').text('", langueConfig.getString(ConfigCles.var_modifier), " ", classeNomAdjectifPluriel, " ", langueConfig.getString(ConfigCles.var_dans), " ' + json.", langueConfig.getString(ConfigCles.var_tempsRestant), ");");
				auteurPageJs.tl(3, "var $x = $('<span>').attr('class', 'w3-button w3-display-topright ').attr('onclick', '$(\"#card-' + id + '\").hide(); ').attr('id', 'x-' + id);");
				auteurPageJs.tl(3, "var $body = $('<div>').attr('class', 'w3-container w3-padding ').attr('id', 'text-' + id);");
				auteurPageJs.tl(3, "var $bar = $('<div>').attr('class', 'w3-light-gray ').attr('id', 'bar-' + id);");
				auteurPageJs.tl(3, "var $progress = $('<div>').attr('class', 'w3-", classeCouleur, " ').attr('style', 'height: 24px; width: ' + percent + '; ').attr('id', 'progress-' + id).text(numPATCH + '/' + numFound);");
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
				auteurPageJs.tl(4, "var $old_box = $('.box-' + id);");
				auteurPageJs.tl(4, "if(!$old_box.size()) {");
				auteurPageJs.tl(5, "$('.top-box').append($box);");
				auteurPageJs.tl(4, "} else if($old_box && $old_box.attr('data-numPATCH') < numFound) {");
				auteurPageJs.tl(5, "$('.box-' + id).html($margin);");
				auteurPageJs.tl(4, "}");
				auteurPageJs.tl(3, "} else {");
				auteurPageJs.tl(4, "$('.box-' + id).remove();");
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

				auteurPageJs.tl(0, "async function websocket", classeApiClasseNomSimple, "Inner(", langueConfig.getString(ConfigCles.var_requeteApi), ") {");
				auteurPageJs.s(wWebsocket);
				auteurPageJs.tl(0, "}");
				auteurPageJs.l();
				auteurPageJs.tl(0, "function ", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Graphique), classeNomSimple, "(", langueConfig.getString(ConfigCles.var_requeteApi), ") {");
				auteurPageJs.tl(1, "var r = $('.", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Formulaire), " .", langueConfig.getString(ConfigCles.var_page), langueConfig.getString(ConfigCles.var_Reponse), "').val();");
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
				auteurPageJs.tl(4, "var pivot1Var", langueConfig.getString(ConfigCles.var_Indexe), " = pivot1Name;");
				auteurPageJs.tl(4, "if(pivot1Var", langueConfig.getString(ConfigCles.var_Indexe), ".includes(','))");
				auteurPageJs.tl(5, "pivot1Var", langueConfig.getString(ConfigCles.var_Indexe), " = pivot1Var", langueConfig.getString(ConfigCles.var_Indexe), ".substring(0, pivot1Var", langueConfig.getString(ConfigCles.var_Indexe), ".indexOf(','));");
				auteurPageJs.tl(4, "var pivot1VarObj = Object.values(window.varsFq).find(o => o.varIndexed === pivot1Var", langueConfig.getString(ConfigCles.var_Indexe), ");");
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
				auteurPageJs.tl(6, "var pivot2Var", langueConfig.getString(ConfigCles.var_Indexe), " = pivot1Map[pivot1Vals[0]].pivotMap[Object.keys(pivot1Map[pivot1Vals[0]].pivotMap)[0]].field;");
				auteurPageJs.tl(6, "var pivot2VarObj = Object.values(window.varsFq).find(o => o.varIndexed === pivot2Var", langueConfig.getString(ConfigCles.var_Indexe), ");");
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
				auteurPageJs.tl(4, "}");
				auteurPageJs.tl(4, "Plotly.react('htmBody", langueConfig.getString(ConfigCles.var_Graphique), classePageSuperNomSimple, "', data, layout);");
				auteurPageJs.tl(3, "}");
				auteurPageJs.tl(2, "}");

				// Maps
				if(classeVarEmplacement != null) {
					auteurPageJs.l();
					auteurPageJs.tl(2, "// ", langueConfig.getString(ConfigCles.var_Graphique), " ", langueConfig.getString(ConfigCles.var_Emplacement));
					auteurPageJs.tl(2, "function onEachFeature(feature, layer) {");
					auteurPageJs.tl(3, "let popupContent = ", langueConfig.getString(ConfigCles.var_htmInfobulle), classeNomSimple, "(feature, layer);");
					auteurPageJs.tl(3, "layer.bindPopup(popupContent);");
					auteurPageJs.tl(2, "};");
					auteurPageJs.tl(2, "if(window.map", classeNomSimple, ") {");
					auteurPageJs.tl(3, "window.geoJSONLayerGroup", classeNomSimple, ".clearLayers();");
					auteurPageJs.tl(3, "$.each( window.", langueConfig.getString(ConfigCles.var_liste), classeNomSimple, ", function(index, ", StringUtils.uncapitalize(classeNomSimple), ") {");
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
					auteurPageJs.tl(6, "}];");
					auteurPageJs.tl(6, "window.geoJSONLayerGroup", classeNomSimple, ".addLayer(L.geoJSON(features, {");
					auteurPageJs.tl(7, "onEachFeature: onEachFeature");
					auteurPageJs.tl(7, ", style: ", langueConfig.getString(ConfigCles.var_jsStyle), classeNomSimple, "");
					auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
					auteurPageJs.tl(8, "return L.circleMarker(latlng, ", langueConfig.getString(ConfigCles.var_jsStyle), classeNomSimple, "(feature));");
					auteurPageJs.tl(7, "}");
					auteurPageJs.tl(6, "}));");
					auteurPageJs.tl(5, "});");
					auteurPageJs.tl(4, "}");
					auteurPageJs.tl(3, "});");
					auteurPageJs.tl(2, "} else {");
					auteurPageJs.tl(3, "window.map", classeNomSimple, " = L.map('htmBody", langueConfig.getString(ConfigCles.var_Graphique), langueConfig.getString(ConfigCles.var_Emplacement), classePageSuperNomSimple, "');");
					auteurPageJs.tl(3, "var data = [];");
					auteurPageJs.tl(3, "var layout = {};");
					auteurPageJs.tl(3, "layout['showlegend'] = true;");
					auteurPageJs.tl(3, "layout['dragmode'] = 'zoom';");
					auteurPageJs.tl(3, "layout['uirevision'] = 'true';");
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
					auteurPageJs.tl(3, "window.geoJSONLayerGroup", classeNomSimple, " = L.geoJSON().addTo(window.map", classeNomSimple, ");");
					auteurPageJs.tl(3, "$.each( window.", langueConfig.getString(ConfigCles.var_liste), classeNomSimple, ", function(index, ", StringUtils.uncapitalize(classeNomSimple), ") {");
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
					auteurPageJs.tl(6, "}];");
					auteurPageJs.tl(6, "window.geoJSONLayerGroup", classeNomSimple, ".addLayer(L.geoJSON(features, {");
					auteurPageJs.tl(7, "onEachFeature: onEachFeature");
					auteurPageJs.tl(7, ", style: ", langueConfig.getString(ConfigCles.var_jsStyle), classeNomSimple, "");
					auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
					auteurPageJs.tl(8, "return L.circleMarker(latlng, ", langueConfig.getString(ConfigCles.var_jsStyle), classeNomSimple, "(feature));");
					auteurPageJs.tl(7, "}");
					auteurPageJs.tl(6, "}));");
					auteurPageJs.tl(5, "});");
					auteurPageJs.tl(4, "}");
					auteurPageJs.tl(3, "});");
					auteurPageJs.tl(3, "window.map", classeNomSimple, ".on('popupopen', function(e) {");
					auteurPageJs.tl(4, "var feature = e.popup._source.feature;");
					auteurPageJs.tl(4, langueConfig.getString(ConfigCles.var_jsInfobulle), classeNomSimple, "(e, feature);");
					auteurPageJs.tl(3, "});");
					auteurPageJs.tl(2, "}");
				}
				auteurPageJs.tl(1, "}");
				auteurPageJs.tl(0, "}");
				auteurPageJs.l();
				auteurPageJs.tl(0, "function animate", langueConfig.getString(ConfigCles.var_Stats), "() {");
				auteurPageJs.tl(1, "$('#pageSearchVal-fq", classeNomSimple, "_time').text('');");
				auteurPageJs.tl(1, "searchPage('", classeNomSimple, "', function() {");
				auteurPageJs.tl(2, "let speedRate = parseFloat($('#animate", langueConfig.getString(ConfigCles.var_Stats), "Speed').val()) * 1000;");
				auteurPageJs.tl(2, "let xStep = parseFloat($('#animate", langueConfig.getString(ConfigCles.var_Stats), "Step').val());");
				auteurPageJs.tl(2, "let xMin = parseFloat($('#animate", langueConfig.getString(ConfigCles.var_Stats), "Min').val());");
				auteurPageJs.tl(2, "let xMax = parseFloat($('#animate", langueConfig.getString(ConfigCles.var_Stats), "Max').val());");
				auteurPageJs.tl(2, "let x = xMin;");
				auteurPageJs.l();
				auteurPageJs.tl(2, "let animateInterval = window.setInterval(() => {");
				auteurPageJs.tl(3, "x = x + xStep;");
				auteurPageJs.tl(3, "if (x > xMax || x < 0) {");
				auteurPageJs.tl(4, "clearInterval(animateInterval);");
				auteurPageJs.tl(3, "}");
				auteurPageJs.tl(3, "$('#fq", classeNomSimple, "_time').val(x);");
				auteurPageJs.tl(3, "$('#fq", classeNomSimple, "_time').change();");
				auteurPageJs.tl(3, "searchPage('", classeNomSimple, "');");
				auteurPageJs.tl(2, "}, speedRate);");
				auteurPageJs.tl(1, "});");
				auteurPageJs.tl(0, "}");
			}
	
			o = auteurGenPageHbs;

			s(auteurGenPageHbsEntite);
	
			o = auteurPageGenClasse;

			tl(0, "}");

			wTh.flushClose();

			auteurPageGenClasse.flushClose();
//			System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classeGenPageChemin); 
			if(auteurPageClasse != null) {
				auteurPageClasse.flushClose();
//				System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classePageChemin); 
			}
			auteurPageCss.flushClose();
//			System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classePageCheminCss); 
			auteurPageJs.flushClose();
//			System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classePageCheminJs); 
			if(auteurPageHbs != null) {
				auteurPageHbs.flushClose();
//				System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classePageCheminHbs); 
			}
			auteurGenPageHbs.flushClose();
//			System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classeGenPageCheminHbs); 

			String siteCheminVertx = siteCheminsVertx.get(langueNom);
			String siteNomVertx = StringUtils.substringAfterLast(siteCheminVertx, "/");
			String cheminSrcGenJavaVertx = (siteCheminVertx == null ? siteChemin : siteCheminVertx) + "/src/gen/java";
			String cheminSrcMainJavaVertx = (siteCheminVertx == null ? siteChemin : siteCheminVertx) + "/src/main/java";
			String cheminSrcMainResourcesVertx = (siteCheminVertx == null ? siteChemin : siteCheminVertx) + "/src/main/resources";

			{
				RegarderClasse regarderClasse = new RegarderClasse();
				regarderClasse.siteChemin = siteCheminVertx;
				regarderClasse.siteNom = siteNomVertx;
				regarderClasse.classeCheminAbsolu = classePageChemin;
				regarderClasse.cheminSrcGenJava = cheminSrcGenJavaVertx;
				regarderClasse.cheminSrcMainJava = cheminSrcMainJavaVertx;
				regarderClasse.cheminSrcMainResources = cheminSrcMainResourcesVertx;
				regarderClasse.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
//					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom);
//					RegarderClasse.regarderClasse(regarderClasse, langueNom);
				SolrInputDocument classeDoc = new SolrInputDocument();
				regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu, classeDoc, langueNom);
				regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom, langueConfig);
			}

			{
				RegarderClasse regarderClasse = new RegarderClasse();
				regarderClasse.siteChemin = siteCheminVertx;
				regarderClasse.siteNom = siteNomVertx;
				regarderClasse.classeCheminAbsolu = classeGenPageChemin;
				regarderClasse.cheminSrcGenJava = cheminSrcGenJavaVertx;
				regarderClasse.cheminSrcMainJava = cheminSrcMainJavaVertx;
				regarderClasse.cheminSrcMainResources = cheminSrcMainResourcesVertx;
				regarderClasse.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
//					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom);
//					RegarderClasse.regarderClasse(regarderClasse, langueNom);
				SolrInputDocument classeDoc = new SolrInputDocument();
				regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu, classeDoc, langueNom);
				regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom, langueConfig);
			}
		}
	}
}
