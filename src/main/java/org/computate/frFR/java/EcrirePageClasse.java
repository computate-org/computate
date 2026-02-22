/*
 * Copyright Computate Limited Liability Company in Utah, USA. 
 * SPDX-License-Identifier: AGPL-3.0
 * This program and the accompanying materials are made available under the
 * terms of the GNU AFFERO GENERAL PUBLIC LICENSE which is available at
 * 
 * https://www.gnu.org/licenses/agpl-3.0.html
 * 
 * You may not propagate or modify a covered work except as expressly provided 
 * under this License. Any attempt otherwise to propagate or modify it is void, 
 * and will automatically terminate your rights under this License (including 
 * any patent licenses granted under the third paragraph of section 11).
 */
package org.computate.frFR.java;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
import org.computate.i18n.I18n;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

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
  public Boolean ecrireFormEntite(String langueNom, JsonObject i18nClasse, JsonObject i18nPage, ToutEcrivain wForm, String classeApiMethodeMethode) {
    int tIndex = 0;
    Boolean resultat = false;

    if(entiteHtml
        && (!"POST".equals(classeApiMethodeMethode) || !entiteCacherPOST)
        && (!"PATCH".equals(classeApiMethodeMethode) || !entiteCacherPATCH)
        ) {
      Boolean ecrireFin = false;
      Boolean nouveauLigne = false;

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
  
      String entitePorteeAncien = Optional.ofNullable(entitePorteeActuelMap.get(classeApiMethodeMethode)).orElse(null);
      String entitePorteeActuel = Optional.ofNullable(entitePortee).orElse(entitePorteeAncien);
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
          if(entitePorteeAncien != null) {
            wForm.tl(6, "{%- endif %}");
            entitePorteeActuelMap.put(classeApiMethodeMethode, null);
          }
        }
        wForm.l();
        if(entitePortee != null) {
          wForm.tl(8, "{% if '", entitePortee, "' in ", i18nGlobale.getString(I18n.var_portees), " -%}");
          entitePorteeActuelMap.put(classeApiMethodeMethode, entitePorteeActuel);
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
              wForm.tl(10, "{% for o in ", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, "}}<th>", entiteHtmLigneEnTeteExpression, "</th>{% endfor %}");
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
      } else {
        // It's not a new HtmRow with Title. 
        if(entitePortee != null) {
          // There is a new entity scope. 
          if(entitePorteeAncien != null && !entitePorteeAncien.equals(entitePortee)) {
            // Close the previous scope. 
            wForm.tl(8, "{%- endif -%}");
          }
          wForm.l();
          wForm.tl(8, "{% if '", entitePortee, "' in ", i18nGlobale.getString(I18n.var_portees), " -%}");
          entitePorteeActuelMap.put(classeApiMethodeMethode, entitePortee);
        } else {
          // There is not a new entity scope. 
          if(entitePorteeAncien != null) {
            // Close the previous scope. 
            wForm.tl(8, "{%- endif -%}");
          }
          wForm.l();
          entitePorteeActuelMap.put(classeApiMethodeMethode, null);
        }
      }


      if(entiteRechercherMaxVarValeur != null) {
        wForm.tl(8, "{% if ", String.format("%s | selectattr('%s', 'equalto', '%s') | list | %s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar), " -%}");
      }
      if(classeUtilisateurEcrire && classeSessionEcrire) {
        wForm.tl(8, "{% if ", i18nClasse.getString(I18n.var_utilisateur), i18nClasse.getString(I18n.var_Cle), " in ", i18nGlobale.getString(I18n.var_resultat), ".", i18nClasse.getString(I18n.var_utilisateur), i18nClasse.getString(I18n.var_Cle), "s or \"PATCH\" in ", i18nClasse.getString(I18n.var_portees), " or ", i18nClasse.getString(I18n.var_sessionId), " == ", i18nGlobale.getString(I18n.var_resultat), ".", i18nClasse.getString(I18n.var_sessionId), " -%}");
        wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=true, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=true", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
        wForm.tl(8, "{% else -%}");
        wForm.tl(8, "{% if ", i18nClasse.getString(I18n.var_utilisateur), i18nClasse.getString(I18n.var_Cle), " in ", i18nGlobale.getString(I18n.var_resultat), ".", i18nClasse.getString(I18n.var_utilisateur), i18nClasse.getString(I18n.var_Cle), "s or \"GET\" in ", i18nClasse.getString(I18n.var_portees), " or ", i18nClasse.getString(I18n.var_sessionId), " == ", i18nGlobale.getString(I18n.var_resultat), ".", i18nClasse.getString(I18n.var_sessionId), " -%}");
        wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=false, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=true", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
        wForm.tl(8, "{% else -%}");
        wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=false, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=false", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
        wForm.tl(8, "{%- endif %}");
        wForm.tl(8, "{%- endif %}");
      }
      else if(classePublicLire) {
        wForm.tl(8, "{% if \"", classeApiMethodeMethode.equals("POST") ? "POST" : "PATCH", "\" in ", i18nClasse.getString(I18n.var_portees), " -%}");
        wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=true, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=true", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
        wForm.tl(8, "{% else -%}");
        wForm.tl(8, "{% if \"GET\" in ", i18nClasse.getString(I18n.var_portees), " -%}");
        wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=false, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=true", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
        wForm.tl(8, "{% else -%}");
        wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=false, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=false", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
        wForm.tl(8, "{%- endif %}");
        wForm.tl(8, "{%- endif %}");
      }
      else if(classeUtilisateurEcrire) {
        if(classeAuth) {
          wForm.tl(8, "{% if \"", classeApiMethodeMethode.equals("POST") ? "POST" : "PATCH", "\" in ", i18nClasse.getString(I18n.var_portees), " -%}");
          wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=true, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=true", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
          wForm.tl(8, "{% else -%}");
          wForm.tl(8, "{% if \"GET\" in ", i18nClasse.getString(I18n.var_portees), " -%}");
          wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=false, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=true", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
          wForm.tl(8, "{% else -%}");
          wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=false, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=false", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
          wForm.tl(8, "{%- endif %}");
          wForm.tl(8, "{%- endif %}");
        }
        else {
          wForm.tl(8, "{% if ", i18nClasse.getString(I18n.var_utilisateur), i18nClasse.getString(I18n.var_Cle), " in ", i18nGlobale.getString(I18n.var_resultat), ".", i18nClasse.getString(I18n.var_utilisateur), i18nClasse.getString(I18n.var_Cle), "s -%}");
          wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=true, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=true", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
          wForm.tl(8, "{% else -%}");
          wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=false, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=false", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
          wForm.tl(8, "{%- endif %}");
        }
      }
      else if(classeSessionEcrire) {
        wForm.tl(8, "{% if ", i18nClasse.getString(I18n.var_sessionId), " == ", i18nGlobale.getString(I18n.var_resultat), ".", i18nClasse.getString(I18n.var_sessionId), ") -}}");
        wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=true, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=true", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
        wForm.tl(8, "{% else -%}");
        wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=false, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=false", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
        wForm.tl(8, "{%- endif %}");
      }
      else if(classeAuth) {
        wForm.tl(8, "{% if \"", classeApiMethodeMethode.equals("POST") ? "POST" : "PATCH", "\" in ", i18nClasse.getString(I18n.var_portees), " -%}");
        wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=true, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=true", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
        wForm.tl(8, "{% else -%}");
        wForm.tl(8, "{% if \"GET\" in ", i18nClasse.getString(I18n.var_portees), " -%}");
        wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=false, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=true", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
        wForm.tl(8, "{% else -%}");
        wForm.tl(8, "{{ htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "='", classeApiMethodeMethode, "', ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=false, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=false", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + (entiteRechercherMaxVarValeur != null ? String.format("(%s | selectattr('%s', 'equalto', '%s') | list | first).%s", entiteRechercherMaxVarJsonArray, entiteRechercherMaxVarValeur, entiteRechercherMaxValeur, entiteRechercherMaxVar) : entiteMax), entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") }}");
        wForm.tl(8, "{%- endif %}");
        wForm.tl(8, "{%- endif %}");
      }
      if(entiteRechercherMaxVarValeur != null) {
        wForm.tl(8, "{%- endif %}");
      }
      // }
    }
    return resultat;
  }

  public void genCodeEntiteHtm(String langueNom, JsonObject i18nClasse, JsonObject i18nPage, String classeApiMethodeMethode) throws Exception {
    ToutEcrivain oAncien = o;
    o = auteurGenPageJinjaEntite;
    Boolean entiteHtmLigneVerticaleActuel = entiteHtmLigneVerticaleActuelMap.get(classeApiMethodeMethode);

    ///////////
    // input //
    ///////////

    
    tl(0, "{%- macro input", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), ", ", i18nClasse.getString(I18n.var_authPourEcrire), "=", i18nClasse.getString(I18n.var_authPourEcrire), ", ", i18nClasse.getString(I18n.var_authPourLire), "=", i18nClasse.getString(I18n.var_authPourLire), entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + entiteMax, entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") %}");
    // if(entiteModifier && (entiteDefinir || entiteAttribuer)) {

      // tl(0, "{%- if ", langueConfig.getString(I18n.var_authPourEcrire), "Bool == true %}");

      tl(1, "{%- if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
      tl(8, "<form class=\"subgrid-2col \" id=\"", i18nClasse.getString(I18n.var_Page), i18nClasse.getString(I18n.var_Formulaire), "_", entiteVar, "\">");
      tl(1, "{%- endif %}");
      if(entiteAttribuer) {
        // Attribuer //
        tl(1, "{%- if '", i18nClasse.getString(I18n.var_PUTCopie), "' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
        tl(9, "<div>");
        tl(10, "<", composantsWebPrefixe, "checkbox ");
        tl(12, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "_", i18nClasse.getString(I18n.var_vider), "\"");
        tl(12, "class=\"label-on-left ", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "_", i18nClasse.getString(I18n.var_vider), " ", entiteVar, "_", i18nClasse.getString(I18n.var_vider), " \"");
        tl(12, "hint=\"", i18nClasse.getString(I18n.var_vider), "\"");
        tl(12, ">");
        tl(11, i18nClasse.getString(I18n.var_vider));
        tl(10, "</", composantsWebPrefixe, "checkbox>");
        tl(9, "</div>");
        tl(1, "{%- endif %}");

        tl(9, "<", composantsWebPrefixe, "input");
        tl(11, "type=\"text\"");

        if(entiteNomAffichage != null) {
          tl(11, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
          tl(11, "label=\"", entiteNomAffichage, "\"");
        }
        if(entiteDescription != null) {
          t(11, "hint=\"").sx(entiteDescription).l("\"");
        }

        tl(11, "class=\"label-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " ", i18nClasse.getString(I18n.var_valeur), StringUtils.capitalize(entiteAttribuerVarSuggere), " ", i18nClasse.getString(I18n.var_suggere), entiteVarCapitalise, " \"");
        tl(11, "name=\"", "set", entiteVarCapitalise, "\"");
        tl(11, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
        tl(11, "data-list=\"list", classeNomSimple, entiteVarCapitalise, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}\"");
        tl(11, "data-method=\"{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}\"");
        tl(11, "autocomplete=\"off\"");
        tl(1, "{% if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " -%}");
        tl(11, "value=\"{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " | e }}\"");
        tl(1, "{% endif -%}");
        // Attribuer //
        // t(11, "oninput=\"", langueConfig.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "(this.value ? [ { 'name': 'q', 'value': '", entiteAttribuerVarSuggere, ":' + this.value }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", langueConfig.getString(I18n.var_classeNomCanonique), ",", entiteAttribuerVar, ",", classeVarClePrimaire, entiteAttribuerVarUrlPageEdition == null ? "" : "," + entiteAttribuerVarUrlPageEdition, entiteAttribuerVarTitre == null ? "" : "," + entiteAttribuerVarTitre, "' } ] : [");
        // s("{%- if ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " is defined %}{'name':'fq','value':'", entiteAttribuerVar, ":{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}'}{%- else %}{%- endif %}");
        // l("], document.querySelector('#list", classeNomSimple, entiteVarCapitalise, "_{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}'), '{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}'); \"");
        tl(9, ">");
        tl(7, "</", composantsWebPrefixe, "input>");
        l();
      }
      else if("LocalDate".equals(entiteNomSimple)) {
        tl(9, "<", composantsWebPrefixe, "input");
        tl(11, "type=\"date\"");

        if(entiteModifier || entiteAttribuer) {
          tl(11, "{% if ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool == true %}clearable{% else %}readonly{% endif %}");
        } else {
          tl(11, "readonly");
        }

        if(entiteNomAffichage != null) {
          tl(11, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
        }
        if(entiteDescription != null) {
          t(11, "hint=\"").sx(entiteDescription).l("\"");
        }
        if(entiteNomAffichage != null) {
          t(11, "label=\"").sx(entiteNomAffichage).l("\"");
        }

        tl(11, "class=\"label-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " datepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
        tl(11, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
        // if(entiteDescription != null)
        // 	tl(16, "title=\"", entiteDescription, " (", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), ")\"");
//				tl(5, "value=\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_ddDashMMDashyyyy), "\").format(", entiteVar, "));");
        tl(1, "{% if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " -%}");
        tl(11, "value=\"{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " | e }}\"");
        tl(1, "{% endif -%}");
        tl(11, "data-", classeVarId, "={{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " | tojson }}");
        if(classeVarZone != null)
          tl(11, "data-zone=\"{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarZone, " | e }}\"");
        else
          tl(11, "data-zone=\"{{ defaultZoneId }}\"");
        tl(11, ">");
        tl(9, "</", composantsWebPrefixe, "input>");
      }
      else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
        tl(9, "<", composantsWebPrefixe, "input");
        tl(11, "type=\"datetime-local\"");

        if(entiteModifier || entiteAttribuer) {
          tl(11, "{% if ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool == true %}clearable{% else %}readonly{% endif %}");
        } else {
          tl(11, "readonly");
        }

        if(entiteDescription != null) {
          t(11, "hint=\"").sx(entiteDescription).l("\"");
        }
        if(entiteNomAffichage != null) {
          if(classeVarZone == null)
            t(11, "label=\"").sx(entiteNomAffichage).l(" {{ formatZonedDateTime(", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, ", \"'['VV']'\", defaultLocaleId, defaultZoneId) | e }}\"");
          else
            t(11, "label=\"").sx(entiteNomAffichage).l(" {{ formatZonedDateTime(", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, ", \"'['VV']'\", defaultLocaleId, ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarZone, ") | e }}\"");
        }

        tl(11, "class=\"label-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " datetimepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
        tl(11, "placeholder=\"", entiteDefaut == null ? i18nPage.getString(I18n.str_ddDashMMDashyyyy_HHColonmm_VV) : entiteDefaut, "\"");
        tl(11, "data-timeformat=\"", i18nPage.getString(I18n.str_ddDashMMDashyyyy_HHColonmm_VV), "\"");
        tl(11, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
//				tl(4, ".a(\"value\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz_VV), "\").format(", entiteVar, "));");

        tl(1, "{% if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " -%}");
        if(classeVarZone == null)
          tl(11, "value=\"{%- if \"Page\" == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " and ", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " is defined %}{{ formatZonedDateTime(", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, ", \"yyyy-MM-dd'T'HH:mm\", defaultLocaleId, defaultZoneId) | e }}{%- endif %}\"");
        else
          tl(11, "value=\"{%- if \"Page\" == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " and ", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " is defined %}{{ formatZonedDateTime(", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, ", \"yyyy-MM-dd'T'HH:mm\", defaultLocaleId, ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarZone, ") | e }}{%- endif %}\"");
        tl(1, "{% endif -%}");
        tl(11, "data-", classeVarId, "={{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " | tojson }}");
        if(classeVarZone != null)
          tl(11, "data-zone=\"{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarZone, " | e }}\"");
        else
          tl(11, "data-zone=\"{{ defaultZoneId }}\"");
        tl(11, ">");
        tl(9, "</", composantsWebPrefixe, "input>");
      }
      else if("LocalTime".equals(entiteNomSimple)) {
        tl(11, "<", composantsWebPrefixe, "input");
        tl(11, "type=\"time\"");
        tl(11, "class=\"{{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " timepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
        tl(11, "placeholder=\"", i18nPage.getString(I18n.var_HHColonMM), "\"");
        tl(11, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

        if(entiteModifier || entiteAttribuer) {
          tl(11, "{% if ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool == true %}clearable{% else %}readonly{% endif %}");
        } else {
          tl(11, "readonly");
        }

        if(entiteDescription != null) {
          t(11, "hint=\"").sx(entiteDescription).l("\"");
        }
        if(entiteNomAffichage != null) {
          t(11, "label=\"").sx(entiteNomAffichage).l("\"");
        }
        tl(11, "></", composantsWebPrefixe, "input>");

        tl(9, "<", composantsWebPrefixe, "input");
        tl(11, "type=\"text\"");
        tl(11, "class=\"label-on-left timepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
        tl(11, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
        tl(1, "{% if \"Page\" == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " -%}");
        tl(11, "value=\"{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " | e }}\"");
        tl(1, "{% endif -%}");
        tl(11, "data-", classeVarId, "={{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " | tojson }}");
        if(classeVarZone != null)
          tl(11, "data-zone=\"{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarZone, " | e }}\"");
        else
          tl(11, "data-zone=\"{{ defaultZoneId }}\"");
        tl(11, "></", composantsWebPrefixe, "input>");
      }
      else if("Boolean".equals(entiteNomSimple)) {
        if( entiteVar.equals(i18nClasse.getString(I18n.var_archive))) {
          tl(1, "{%- if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
          // tl(1, "{%- if 'Page' == ", langueConfig.getString(I18n.var_classeApiMethodeMethode), " %}");
          tl(9, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", "");
          tl(11, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

          if(entiteNomAffichage != null) {
            tl(11, "placeholder=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
            tl(11, "label=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
          }
          if(entiteDescription != null) {
            t(11, "hint=\"").sx(entiteDescription).l("\"");
          }

          tl(11, "class=\"label-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
          tl(11, "name=\"set", entiteVarCapitalise, "\"");
          tl(11, "data-", classeVarId, "={{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " | tojson }}");
          tl(11, "data-val=\"{{ ", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " | e }}\"");
          t(11, ">");
          if(entiteNomAffichage != null) {
            sx(entiteNomAffichage);
          }
          l("</", composantsWebPrefixe, "button>");
          tl(1, "{%- else %}");
        }

        tl(1, "{% if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " -%}");
        tl(9, "<", composantsWebPrefixe, "checkbox");
        tl(11, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
        tl(11, "value=\"true\"");
        tl(1, "{%- else %}");
        tl(9, "<", composantsWebPrefixe, "select");
        if(entiteDefaut != null)
          t(11, "value=\"").sx(entiteDefaut).l("\"");
        tl(11, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
        tl(1, "{% endif -%}");

        if(entiteModifier || entiteAttribuer) {
          tl(11, "{% if ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool == true %}clearable{% else %}readonly{% endif %}");
        } else {
          tl(11, "readonly");
        }

        if(entiteNomAffichage != null) {
          tl(11, "placeholder=\"", entiteNomAffichage, "\"");
          tl(11, "label=\"", entiteNomAffichage, "\"");
        }
        if(entiteDescription != null) {
          t(11, "hint=\"").sx(entiteDescription).l("\"");
        }

        tl(1, "{%- if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
        tl(11, "class=\"label-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
        tl(11, "name=\"set", entiteVarCapitalise, "\"");
        tl(1, "{%- else %}");
        tl(2, "{%- if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
        tl(11, "class=\"label-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
        tl(11, "name=\"set", entiteVarCapitalise, "\"");
        tl(2, "{%- else %}");
        tl(11, "class=\"label-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " set", entiteVarCapitalise, " ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
        tl(11, "name=\"set", entiteVarCapitalise, "\"");
        tl(2, "{%- endif %}");
        tl(1, "{%- endif %}");

        tl(1, "{%- if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
        tl(2, "{%- if ", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " == true %}");
        tl(11, "checked");
        tl(2, "{%- endif %}");
        tl(11, "data-", classeVarId, "={{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " | tojson }}");
        t(11, ">");
        if(entiteNomAffichage != null) {
          sx(entiteNomAffichage);
        }
        l("</", composantsWebPrefixe, "checkbox>");
        tl(1, "{%- else %}");
        tl(11, ">");
        if(entiteDefaut == null)
          tl(10, "<", composantsWebPrefixe, "option value=\"\"></", composantsWebPrefixe, "option>");
        tl(10, "<", composantsWebPrefixe, "option value=\"true\">true</", composantsWebPrefixe, "option>");
        tl(10, "<", composantsWebPrefixe, "option value=\"false\">false</", composantsWebPrefixe, "option>");
        tl(9, "</", composantsWebPrefixe, "select>");
        tl(1, "{%- endif %}");
        if(entiteVar.equals(i18nClasse.getString(I18n.str_archive))) {
          tl(1, "{%- endif %}");
        }
      }
      else if(entiteImageBase64Url != null) {
        tl(9, "<div class=\"imageBase64Div1", classeNomSimple, "_", entiteVar, "\" id=\"imageBase64Div1", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "\">");

        tl(10, "<h5>", i18nPage.getString(I18n.str_Télécharger_image), "</h5>");
        tl(10, "<form method=\"POST\" enctype=\"multipart/form-data\" action=\"", entiteImageBase64Url, "\" class=\"\">");
        tl(11, "<input type=\"hidden\" name=\"", classeVarId, "\" value=\"{{", classeVarId, " | e }}\"/>");
        tl(11, "<input type=\"hidden\" name=\"", i18nClasse.getString(I18n.var_classeNomSimple), "\" value=\"", classeNomSimple, "\"/>");
        tl(11, "<", composantsWebPrefixe, "input name=\"", i18nClasse.getString(I18n.var_fichier), "\" type=\"file\" onchange=\"fetch('", entiteImageBase64Url, "', { method: 'POST', body: new FormData(this.form)}); \"></", composantsWebPrefixe, "input>");
        tl(10, "</form>");

        tl(10, "<img id=\"imageBase64Img", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "\");");
        tl(12, "class=\"img", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
        tl(12, "src=\"{%- if ", entiteVar, " is defined %}data:image/png;base64,{%- else %}{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, "}}{%- endif %} alt=\"\"");
        tl(12, "/>");

        tl(9, "</div>");
      }
      else if(BooleanUtils.isTrue(entiteSignature)) {
        tl(9, "<div class=\"signatureDiv1", classeNomSimple, "_", entiteVar, " signatureInput", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, "\" id=\"signatureDiv1", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "\">");

        tl(10, "<div id=\"signatureInput", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "\"");
        tl(12, "style=\"display: {%- if ", entiteVar, " is defined %}block{%- else %}none{%- endif %}\"");
        tl(10, "</div>");

        tl(10, "<img id=\"signatureImg", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "\"");
        tl(12, "class=\"signatureImg", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, "\"");
        tl(12, "src=\"{%- if ", entiteVar, " is defined %}data:image/png;base64{%- else %}{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, "}}\" alt=\"\"");
        tl(12, "style=\"padding: 10px; display: {%- if ", entiteVar, " is defined %}none{%- else %}block{%- endif %}\"");
        tl(12, "/>");

        tl(9, "<div>");
        tl(9, "<div id=\"signatureDiv2", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "\">");

        tl(10, "<button id=\"signatureButton", i18nClasse.getString(I18n.var_Vider), classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "\"");
        tl(12, "onclick=\"");
        tl(14, "document.querySelector('#signatureInput", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "').classList.remove('display-none'); ");
        tl(14, "document.querySelector('#signatureImg", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "').classList.add('display-none'); ");
        tl(14, i18nClasse.getString(I18n.var_enleverLueur), "(document.querySelector('#signatureInput", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "')); ");
        tl(14, "patch{{", i18nClasse.getString(I18n.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeVarId, ":{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}' }], 'set", entiteVarCapitalise, "', null, this); ");
        tl(14, "if(document.querySelector('#signatureInput", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "')) { ");
        tl(14, "document.querySelector('#signatureInput", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "').jSignature('reset'); ");
        tl(14, "} else { ");
        tl(14, "document.querySelector('#signatureInput", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVar, "').jSignature({'height':200}); ");
        tl(14, "}");
        tl(12, "\"");
        tl(12, ">", i18nPage.getString(I18n.str_Vider));
        tl(10, "</button>");

        tl(9, "</div>");

      }
      else {
        if(entiteLien) {
          tl(1, "{%- if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
          tl(9, "<div class=\"button-on-left \">");
          tl(10, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", "");
          tl(12, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

          if(entiteNomAffichage != null) {
            tl(12, "placeholder=\"[", entiteNomSimple, "] ", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
            tl(12, "label=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
          }
          if(entiteDescription != null) {
            t(12, "hint=\"").sx(entiteDescription).l("\"");
          }

          if(classeVarId != null) {
            tl(12, "class=\"button-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
          } else {
            tl(12, "class=\"button-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, "\"");
          }
          tl(12, "name=\"set", entiteVarCapitalise, "\"");
          tl(12, "href=\"{{ ", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " | e }}\"");
          tl(12, ">");
          if(entiteIcone != null) {
            tl(11, entiteIcone.replace("<i ", "<i slot=\"start\" "));
          }
          if(entiteNomAffichage != null) {
            t(11).sx(entiteNomAffichage).l();
          }
          tl(10, "</", composantsWebPrefixe, "button>");
          tl(9, "</div>");
          t(9, "<div class=\"button-description-on-right \">");
            sx(entiteDescription);
          l("</div>");
          tl(1, "{%- else %}");
        }
        if(entiteMultiligne)
          tl(9, "<", composantsWebPrefixe, "textarea resize=\"auto\"");
        else if(entiteRadioValeurs.size() > 0)
          tl(9, "<", composantsWebPrefixe, "radio-group");
        else if(entiteOptionValeurs.size() > 0)
          tl(9, "<", composantsWebPrefixe, "select");
        else if(entiteCouleur)
          tl(9, "<", composantsWebPrefixe, "color-picker opacity");
        else {
          tl(9, "<", composantsWebPrefixe, "input");
        }

        if(entiteModifier || entiteAttribuer) {
          tl(11, "{% if ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool == true %}clearable{% else %}readonly{% endif %}");
        } else {
          tl(11, "readonly");
        }

        if(entiteNomAffichage != null) {
          List<String> l = new ArrayList<>();
          if(entiteMin != null)
            l.add(entiteMin);
          if(entiteDefaut != null)
            l.add(entiteDefaut);
          if(entiteMax != null)
            l.add(entiteMax);
          tl(11, "placeholder=\"[", entiteNomSimple, "] ", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
          t(11, "label=\"").sx(entiteNomAffichage)
              // .sx(l.size() == 0 ? "" : l.stream().collect(Collectors.joining(", ", " (", ")")))
              .l("\"");
        }

        if(StringUtils.equalsAny(entiteFiwareType, "date", "datetime-local", "email", "number", "password", "search", "tel", "text", "time", "url"))
          tl(11, "type=\"", entiteFiwareType, "\"");
        if(StringUtils.equalsAny(entiteFiwareType, "number") && entiteEtape != null)
          tl(11, "step=\"", entiteEtape, "\"");

        if(entiteMin != null)
          tl(11, "min=\"{{ min }}\"");
        if(entiteMax != null)
          tl(11, "max=\"{{ max }}\"");
        if(entiteDescription != null) {
          t(11, "hint=\"").sx(entiteDescription).l("\"");
        }
        if(entiteRequis) {
          tl(11, "required");
        }
        tl(11, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

        tl(1, "{%- if \"Page\" == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
          tl(11, "data-", classeVarId, "={{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " | tojson }}");
          tl(11, "class=\"label-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
          tl(11, "name=\"set", entiteVarCapitalise, "\"");
        tl(1, "{%- else %}");
        tl(2, "{%- if \"PATCH\" == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
        tl(11, "class=\"label-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
        tl(11, "name=\"set", entiteVarCapitalise, "\"");
        tl(2, "{%- else %}");
          tl(11, "class=\"label-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
          tl(11, "name=\"", entiteVar, "\"");
        tl(2, "{%- endif %}");
        tl(1, "{%- endif %}");

        if(entiteMultiligne) {
          tl(0, "{% if \"Page\" == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " -%}");
          if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson))
            tl(11, "value=\"{{ to", entiteNomSimpleVertxJson, "String(", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, ") | e }}\"");
          else
            tl(11, "value=\"{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " | e }}\"");
          tl(0, "{% endif -%}");
          tl(11, ">");
        } else if(entiteCouleur) {
          tl(1, "{%- if \"Page\" == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
          tl(11, "value=\"{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " | e }}\"");
          tl(1, "{% endif -%}");
          tl(11, ">");
        } else {
          tl(1, "{% if \"Page\" == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " -%}");
          if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson))
            tl(11, "value=\"{{ to", entiteNomSimpleVertxJson, "String(", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, ") | e }}\"");
          else
            tl(11, "value=\"{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " | e }}\"");
          tl(1, "{% endif -%}");
        }

        if(entiteMultiligne) {
          s("</", composantsWebPrefixe, "textarea>");
        } else if(entiteRadioValeurs.size() > 0) {
          tl(11, ">");
          for(Integer i = 0; i < entiteRadioValeurs.size(); i++) {
            String valeur = entiteRadioValeurs.get(i);
            String texte = entiteRadioTextes.get(i);

            t(10, "<", composantsWebPrefixe, "radio value=\"");
            sx(valeur);
            s("\">");
            sx(texte);
            l("</", composantsWebPrefixe, "radio>");
          }
          tl(9, "</", composantsWebPrefixe, "radio-group>");
        } else if(entiteOptionValeurs.size() > 0) {
          tl(11, ">");
          for(Integer i = 0; i < entiteOptionValeurs.size(); i++) {
            String valeur = entiteOptionValeurs.get(i);
            String texte = entiteOptionTextes.get(i);

            t(10, "<", composantsWebPrefixe, "option value=\"");
            sx(valeur);
            s("\">");
            sx(texte);
            l("</", composantsWebPrefixe, "option>");
          }
          tl(9, "</", composantsWebPrefixe, "select>");
        } else if(entiteCouleur) {
          tl(9, "</", composantsWebPrefixe, "color-picker>");
        } else {
          tl(11, "></", composantsWebPrefixe, "input>");
        }

        l();
        if(entiteLien) {
          tl(1, "{%- endif %}");
        }
      }

      tl(1, "{%- if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
      tl(8, "</form>");
      tl(1, "{%- endif %}");

// 			if(entiteAttribuer) {
// 				// tl(1, "{%- else %}");
// 			}
// 			else if(classeUtilisateurEcrire && classeSessionEcrire || classePublicLire) {
// 				// tl(1, "{%- else %}");
// 				tl(8, "<span class=\"var", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, "}}", (entiteVarUrl == null ? "</span>" : "</a>"));
// 			}
// 			else if(classeUtilisateurEcrire) {
// 				if(classeRolesTrouves || classeRoleLiresTrouves) {
// 					// tl(1, "{%- else %}");
// 					tl(8, "<span class=\"var", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, "}}</span>");
// 				}
// 				else {
// 					// tl(1, "{%- else %}");
// 					tl(8, "<span class=\"var", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, "}}</span>");
// 				}
// 			}
// 			else if(classeSessionEcrire) {
// 				// tl(1, "{%- else %}");
// 				tl(8, "<span class=\"var", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, "}}</span>");
// 			}
// 			else if(classeRolesTrouves || classeRoleLiresTrouves) {
// 					// tl(1, "{%- else %}");
// 				tl(1, "{%- if ", langueConfig.getString(I18n.var_utilisateur), langueConfig.getString(I18n.var_Cle), " in ", i18nGlobale.getString(I18n.var_resultat), ".", langueConfig.getString(I18n.var_utilisateur), langueConfig.getString(I18n.var_Cle), "s %}");
// 				tl(8, "<span class=\"var", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, "}}</span>");
// 				tl(1, "{%- endif %}");
// 			}
// 			else {
// //								tl(3, "sx(htm", entiteVarCapitalise, "());");
// 			}

      // tl(0, "{%- else %}");
      // 	tl(6, "<span class=\"var", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, "}}</span>");
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
    // 		tl(9, "hint=\"", langueConfig.getString(I18n.var_vider), "\"");
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
    // 			t(8, "hint=\"").sx(entiteDescription).l("\"");
    // 		}
    // 		if(entiteNomAffichage != null) {
    // 			t(8, "label=\"").sx(entiteNomAffichage).l("\"");
    // 		}

    // 		tl(8, "class=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " ", langueConfig.getString(I18n.var_valeur), StringUtils.capitalize(entiteAttribuerVarSuggere), " ", langueConfig.getString(I18n.var_suggere), entiteVarCapitalise, " \"");
    // 		tl(8, "name=\"", "set", entiteVarCapitalise, "\"");
    // 		tl(8, "id=\"{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
    // 		tl(8, "autocomplete=\"off\"");
    // 		t(8, "oninput=\"", langueConfig.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "(this.value ? [ { 'name': 'q', 'value': '", entiteAttribuerVarSuggere, ":' + this.value }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", langueConfig.getString(I18n.var_classeNomCanonique), ",", entiteAttribuerVar, ",", classeVarClePrimaire, entiteAttribuerVarUrlPageEdition == null ? "" : "," + entiteAttribuerVarUrlPageEdition, entiteAttribuerVarTitre == null ? "" : "," + entiteAttribuerVarTitre, "' } ] : [");
    // 		s("{%- if ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " is defined %}{'name':'fq','value':'", entiteAttribuerVar, ":{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}'}{%- else %}{%- endif %}");
    // 		l("], document.querySelector('#list", classeNomSimple, entiteVarCapitalise, "_{{", langueConfig.getString(I18n.var_classeApiMethodeMethode), "}}'), {{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}); \"");
    // 		tl(8, "></", composantsWebPrefixe, "input>");
    // 		l();
    // 	} else if("LocalDateTime".equals(entiteNomSimple)) {
    // 		tl(6, (entiteVarUrl == null ? "<span id=\"{{" + langueConfig.getString(I18n.var_classeApiMethodeMethode) + "}}_" + entiteVar + "\"" : "<a href=\"{{ " + i18nGlobale.getString(I18n.var_resultat) + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \" title=\"{{ formatLocalDateTime(", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, ", 'EEEE MMMM d yyyy H:mm:ss.SSS zz VV', defaultLocaleId, defaultZoneId) }}\">{{ formatZonedDateTime(", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, ", 'EEE MMM d yyyy', defaultLocaleId, defaultZoneId) }}", (entiteVarUrl == null ? "</span>" : "</a>"));
    // 	} else if("ZonedDateTime".equals(entiteNomSimple)) {
    // 		tl(6, (entiteVarUrl == null ? "<span id=\"{{" + langueConfig.getString(I18n.var_classeApiMethodeMethode) + "}}_" + entiteVar + "\"" : "<a href=\"{{ " + i18nGlobale.getString(I18n.var_resultat) + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \" title=\"{{ formatZonedDateTime(", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, ", 'EEEE MMMM d yyyy H:mm:ss.SSS zz VV', defaultLocaleId, defaultZoneId) }}\">{{ formatZonedDateTime(", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, ", 'EEE MMM d yyyy h:mm a zz', defaultLocaleId, defaultZoneId) }}", (entiteVarUrl == null ? "</span>" : "</a>"));
    // 	} else {
    // 		tl(6, (entiteVarUrl == null ? "<span id=\"{{" + langueConfig.getString(I18n.var_classeApiMethodeMethode) + "}}_" + entiteVar + "\"" : "<a href=\"{{ " + i18nGlobale.getString(I18n.var_resultat) + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " var", classeNomSimple, entiteVarCapitalise, " \">{{", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, "}}", (entiteVarUrl == null ? "</span>" : "</a>"));
    // 	}
    // 	tl(0, "{%- endif %}");
    // }
    tl(0, "{%- endmacro %}");

    /////////
    // htm //
    /////////

    l();
    l("{%- macro htm", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), ", ", i18nClasse.getString(I18n.var_authPourEcrire), "Bool=false, ", i18nClasse.getString(I18n.var_authPourLire), "Bool=false", entiteMin == null ? "" : ", min=" + entiteMin, entiteMax == null ? "" : ", max=" + entiteMax, entiteDefaut == null ? "" : ", default=" + entiteDefaut, ") %}");
    if(entiteHtml) {

      if(entiteAttribuer) {
        // Attribuer //
        tl(8, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_authPourEcrire), "=", i18nClasse.getString(I18n.var_authPourEcrire), ", ", i18nClasse.getString(I18n.var_authPourLire), "=", i18nClasse.getString(I18n.var_authPourLire), ") }}");
        tl(8, "<div class=\"label-on-left-ul-label \">");
        tl(9, "<i class=\"{{ FONTAWESOME_STYLE }} fa-search \"></i>");
        tl(9, i18nClasse.getString(I18n.var_relier), " ", entiteListeTypeJson == null ? entiteAttribuerContexteUnNom : entiteAttribuerContexteNomPluriel, " ", i18nPage.getString(I18n.str_a), " ", classeCeNom);
        tl(8, "</div>");
        tl(8, "<ul class=\"label-on-left-ul \" id=\"list", classeNomSimple, entiteVarCapitalise, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}\">");
        tl(8, "</ul>");

        tl(8, "{%- if ", i18nClasse.getString(I18n.var_authPourLire), "Bool == true %}");

        tl(8, "{% if 'Page' == ", i18nClasse.getString(I18n.var_classeApiMethodeMethode), " %}");
        tl(8, "<div>");
        t(9, "{{ htmForm_post", entiteAttribuerNomSimple, "() }}");
        tl(9, "<", composantsWebPrefixe, "button");
        // DO STUFF
        tl(11, " id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "_", i18nClasse.getString(I18n.var_ajouter), "\"");

        t(11, " data-dialog=\"open post", entiteAttribuerNomSimple, "Dialog\"");
        tl(11, ">", i18nPage.getString(I18n.str_ajouter), " ", entiteAttribuerContexteUnNom, "</", composantsWebPrefixe, "button>");
        tl(9, "</div>");
        tl(8, "{%- endif %}");

        tl(8, "{%- endif %}");
      }
      else if("LocalDate".equals(entiteNomSimple)) {
        if(entiteHtmLigneVerticaleActuel) {
        }
        tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "=", i18nClasse.getString(I18n.var_classeApiMethodeMethode), ", ", i18nClasse.getString(I18n.var_authPourEcrire), "=", i18nClasse.getString(I18n.var_authPourEcrire), ", ", i18nClasse.getString(I18n.var_authPourLire), "=", i18nClasse.getString(I18n.var_authPourLire), ") }}");
      }
      else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
        tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "=", i18nClasse.getString(I18n.var_classeApiMethodeMethode), ", ", i18nClasse.getString(I18n.var_authPourEcrire), "=", i18nClasse.getString(I18n.var_authPourEcrire), ", ", i18nClasse.getString(I18n.var_authPourLire), "=", i18nClasse.getString(I18n.var_authPourLire), ") }}");
      }
      else if("LocalTime".equals(entiteNomSimple)) {
        tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "=", i18nClasse.getString(I18n.var_classeApiMethodeMethode), ", ", i18nClasse.getString(I18n.var_authPourEcrire), "=", i18nClasse.getString(I18n.var_authPourEcrire), ", ", i18nClasse.getString(I18n.var_authPourLire), "=", i18nClasse.getString(I18n.var_authPourLire), ") }}");
      }
      else if("Boolean".equals(entiteNomSimple)) {
        tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "=", i18nClasse.getString(I18n.var_classeApiMethodeMethode), ", ", i18nClasse.getString(I18n.var_authPourEcrire), "=", i18nClasse.getString(I18n.var_authPourEcrire), ", ", i18nClasse.getString(I18n.var_authPourLire), "=", i18nClasse.getString(I18n.var_authPourLire), ") }}");
      } else if("JsonArray".equals(entiteNomSimple)) {
        if(entiteHtmLigneEnTeteExpression == null) {
          tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "=", i18nClasse.getString(I18n.var_classeApiMethodeMethode), ", ", i18nClasse.getString(I18n.var_authPourEcrire), "=", i18nClasse.getString(I18n.var_authPourEcrire), ", ", i18nClasse.getString(I18n.var_authPourLire), "=", i18nClasse.getString(I18n.var_authPourLire), ") }}");
        } else {
          tl(14, "{%- for item in ", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " %}<td>{{ item }}</td>{%- endfor %}");
        }
      }
      else {
        tl(14, "{{ input", entiteVarCapitalise, classePageNomSimple, "(", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "=", i18nClasse.getString(I18n.var_classeApiMethodeMethode), ", ", i18nClasse.getString(I18n.var_authPourEcrire), "=", i18nClasse.getString(I18n.var_authPourEcrire), ", ", i18nClasse.getString(I18n.var_authPourLire), "=", i18nClasse.getString(I18n.var_authPourLire), entiteMin == null ? "" : ", min=min", entiteMax == null ? "" : ", max=max", entiteDefaut == null ? "" : ", default=default", ") }}");
      }
    }

    l("{%- endmacro %}");  

    o = oAncien;
  }

  public void pageVarsStaticInit(String classeLangueNom, String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {

    classeVarClePrimaire = classeDoc.getString("classeVarClePrimaire"   + "_" + classeLangueNom + "_stored_string");
    classeVarCleUnique = classeDoc.getString("classeVarCleUnique"   + "_" + classeLangueNom + "_stored_string");
    classeVarClePrimaireUnique = classeVarClePrimaire == null ? classeVarCleUnique : classeVarClePrimaire;
    classeGenPageChemin = classeDoc.getString("classeGenPageChemin"   + "_" + classeLangueNom + "_stored_string");
    classePageAvecTemplate = classeDoc.getBoolean("classePageAvecTemplate_stored_boolean");

    classePageChemin = classeDoc.getString("classePageChemin"   + "_" + classeLangueNom + "_stored_string");
    classePageUriCss = classeDoc.getString("classePageUriCss"   + "_" + langueNom + "_stored_string");
    classePageUriJs = classeDoc.getString("classePageUriJs"   + "_" + langueNom + "_stored_string");
    classePageUriJsRecherche = classeDoc.getString("classePageUriJsRecherche"   + "_" + langueNom + "_stored_string");
    classePageUriJsEdition = classeDoc.getString("classePageUriJsEdition"   + "_" + langueNom + "_stored_string");

    classePageCheminCss = classeDoc.getString("classePageCheminCss"   + "_" + langueNom + "_stored_string");
    classePageCheminJs = classeDoc.getString("classePageCheminJs"   + "_" + langueNom + "_stored_string");
    classePageCheminJsRecherche = classeDoc.getString("classePageCheminJsRecherche"   + "_" + langueNom + "_stored_string");
    classePageCheminJsEdition = classeDoc.getString("classePageCheminJsEdition"   + "_" + langueNom + "_stored_string");

    classeApiUri = classeDoc.getString("classeApiUri" + "_" + classeLangueNom + "_stored_string");
    classePageUriMethode = classeDoc.getString("classeApiUri" + i18nPage.getString(I18n.var_PageRecherche) + "_" + langueNom + "_stored_string");
    // classePageLangueNom = classeDoc.getString("classePageLangueNom"  + "_" + langueNom + "_stored_string");
    classeModele = (Boolean)classeDoc.getBoolean("classeModele_stored_boolean");
    // classePageLangueConfig = null;
    // if(classePageLangueNom != null) {
    //   classePageLangueConfig = langueConfig;
    // }

    classePageNomSimple = classeDoc.getString("classePageNomSimple"   + "_" + classeLangueNom + "_stored_string");
    classePageSuperNomSimple = classeDoc.getString("classePageSuperNomSimple"   + "_" + classeLangueNom + "_stored_string");
    classeApiClasseNomSimple = classeDoc.getString("classeApiClasseNomSimple"   + "_" + classeLangueNom + "_stored_string");
    varResultat = i18nGlobale.getString(I18n.var_resultat);
    // varResultat = StringUtils.uncapitalize(classeApiClasseNomSimple);
    classeGenPageNomSimple = classeDoc.getString("classeGenPageNomSimple"   + "_" + classeLangueNom + "_stored_string");
    classePageNomCanonique = classeDoc.getString("classePageNomCanonique"   + "_" + classeLangueNom + "_stored_string");
    classeAttribuerPageUriJs = Optional.ofNullable(classeDoc.getJsonArray("classeAttribuerPageUriJs_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    classeAttribuerPageUriMacros = Optional.ofNullable(classeDoc.getJsonArray("classeAttribuerPageUriMacros_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    classeAttribuerNomSimplePages = Optional.ofNullable(classeDoc.getJsonArray("classeAttribuerNomSimplePages_" + classeLangueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    classeAttribuerNomSimples = Optional.ofNullable(classeDoc.getJsonArray("classeAttribuerNomSimple_" + classeLangueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());

      classePageRechercheApiMethode = classeDoc.getString("classePageRechercheApiMethode_" + langueNom + "_stored_string");
      classePageEditionApiMethode = classeDoc.getString("classePageEditionApiMethode_" + langueNom + "_stored_string");
      classePageAffichageApiMethode = classeDoc.getString("classePageAffichageApiMethode_" + langueNom + "_stored_string");
      classePageEditionApiMethode = classeDoc.getString("classePageEditionApiMethode_" + langueNom + "_stored_string");
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
      classeRechercherTousNom = classeDoc.getString("classeRechercherTousNom" + "_" + langueNom + "_stored_string");
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
      classeVarUrlPageAffichage = classeDoc.getString("classeVarUrlPageAffichage" + "_" + langueNom + "_stored_string");
      classeVarUrlPageEdition = classeDoc.getString("classeVarUrlPageEdition" + "_" + langueNom + "_stored_string");
      classeVarUrlPageUtilisateur = classeDoc.getString("classeVarUrlPageUtilisateur" + "_" + langueNom + "_stored_string");
      classeVarUrlTelechargement = classeDoc.getString("classeVarUrlTelechargement" + "_" + langueNom + "_stored_string");
      classeVarSuggere = classeDoc.getString("classeVarSuggere" + "_" + classeLangueNom + "_stored_string");
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
      wClasseApiMethodeMethodes.add(i18nPage.getString(I18n.var_Recherche));

      wFormPOST = ToutEcrivain.create("  ");
      wForms.add(wFormPOST);
      wClasseApiMethodeMethodes.add("POST");

      wFormDELETE = ToutEcrivain.create("  ");
      wForms.add(wFormDELETE);
      wClasseApiMethodeMethodes.add("DELETE");

      wFormDELETEFiltre = ToutEcrivain.create("  ");
      wForms.add(wFormDELETEFiltre);
      wClasseApiMethodeMethodes.add("DELETE");

      wFormPUTImport = ToutEcrivain.create("  ");
      wForms.add(wFormPUTImport);
      wClasseApiMethodeMethodes.add("PUTImport");

      wFormPUTFusion = ToutEcrivain.create("  ");
      wForms.add(wFormPUTFusion);
      wClasseApiMethodeMethodes.add(i18nPage.getString(I18n.var_PUTFusion));

      wFormPUTCopie = ToutEcrivain.create("  ");
      wForms.add(wFormPUTCopie);
      wClasseApiMethodeMethodes.add(i18nPage.getString(I18n.var_PUTCopie));

      wFormPage = ToutEcrivain.create("  ");
      wForms.add(wFormPage);
      wClasseApiMethodeMethodes.add("Page");

      wFormPATCH = ToutEcrivain.create("  ");
      wForms.add(wFormPATCH);
      wClasseApiMethodeMethodes.add("PATCH");

      wJsHtmInit = ToutEcrivain.create("  ");
      wJsHtmEditionInit = ToutEcrivain.create("  ");
      wStyle = ToutEcrivain.create("  ");
      wJsEditionInit = ToutEcrivain.create("  ");
      wJsRechercheInit = ToutEcrivain.create("  ");
      wWebsocket = ToutEcrivain.create("  ");
      wWebsocketInput1 = ToutEcrivain.create("  ");
      wWebsocketInput2 = ToutEcrivain.create("  ");
      wWebsocketInput3 = ToutEcrivain.create("  ");
      wPks = ToutEcrivain.create("  ");

      classeGenPageChemin = classeDoc.getString("classeGenPageChemin"  + "_" + classeLangueNom + "_stored_string");
      classePageChemin = classeDoc.getString("classePageChemin"  + "_" + classeLangueNom + "_stored_string");
      classePageAvecTemplate = classeDoc.getBoolean("classePageAvecTemplate_stored_boolean");

      classePageUriCss = classeDoc.getString("classePageUriCss"  + "_" + langueNom + "_stored_string");
      classePageUriJs = classeDoc.getString("classePageUriJs"  + "_" + langueNom + "_stored_string");
      classePageUriJsRecherche = classeDoc.getString("classePageUriJsRecherche"  + "_" + langueNom + "_stored_string");
      classePageUriJsEdition = classeDoc.getString("classePageUriJsEdition"  + "_" + langueNom + "_stored_string");

      classePageCheminCss = classeDoc.getString("classePageCheminCss"  + "_" + langueNom + "_stored_string");
      classePageCheminJs = classeDoc.getString("classePageCheminJs"  + "_" + langueNom + "_stored_string");
      classePageCheminJsRecherche = classeDoc.getString("classePageCheminJsRecherche"  + "_" + langueNom + "_stored_string");
      classePageCheminJsEdition = classeDoc.getString("classePageCheminJsEdition"  + "_" + langueNom + "_stored_string");

      classePageEmplacementTemplate = classeDoc.getString("classePageEmplacementTemplate" + "_" + langueNom + "_stored_string");
      classePageEmplacementCheminJinja = classeDoc.getString("classePageEmplacementCheminJinja" + "_" + langueNom + "_stored_string");

      classePageBarreLateraleTemplate = classeDoc.getString("classePageBarreLateraleTemplate" + "_" + langueNom + "_stored_string");
      classePageBarreLateraleCheminJinja = classeDoc.getString("classePageBarreLateraleCheminJinja" + "_" + langueNom + "_stored_string");

      classePageBoutonsRechercheTemplate = classeDoc.getString("classePageBoutonsRechercheTemplate" + "_" + langueNom + "_stored_string");
      classePageBoutonsRechercheCheminJinja = classeDoc.getString("classePageBoutonsRechercheCheminJinja" + "_" + langueNom + "_stored_string");

      classePageBoutonsPaginationTemplate = classeDoc.getString("classePageBoutonsPaginationTemplate" + "_" + langueNom + "_stored_string");
      classePageBoutonsPaginationCheminJinja = classeDoc.getString("classePageBoutonsPaginationCheminJinja" + "_" + langueNom + "_stored_string");

      classePageMacrosFormulaireRechercheTemplate = classeDoc.getString("classePageMacrosFormulaireRechercheTemplate" + "_" + langueNom + "_stored_string");
      classePageMacrosFormulaireRechercheCheminJinja = classeDoc.getString("classePageMacrosFormulaireRechercheCheminJinja" + "_" + langueNom + "_stored_string");

      classePageBoutonsFormulaireRechercheTemplate = classeDoc.getString("classePageBoutonsFormulaireRechercheTemplate" + "_" + langueNom + "_stored_string");
      classePageBoutonsFormulaireRechercheCheminJinja = classeDoc.getString("classePageBoutonsFormulaireRechercheCheminJinja" + "_" + langueNom + "_stored_string");

      classePageFormulaireRechercheTemplate = classeDoc.getString("classePageFormulaireRechercheTemplate" + "_" + langueNom + "_stored_string");
      classePageFormulaireRechercheCheminJinja = classeDoc.getString("classePageFormulaireRechercheCheminJinja" + "_" + langueNom + "_stored_string");

      classePageRechercheSuggereTemplate = classeDoc.getString("classePageRechercheSuggereTemplate" + "_" + langueNom + "_stored_string");
      classePageRechercheSuggereCheminJinja = classeDoc.getString("classePageRechercheSuggereCheminJinja" + "_" + langueNom + "_stored_string");

      classePageRechercheTemplate = classeDoc.getString(String.format("classe%sTemplate", classePageRechercheApiMethode) + "_" + langueNom + "_stored_string");
      classePageRechercheCheminJinja = classeDoc.getString(String.format("classe%sCheminJinja", classePageRechercheApiMethode) + "_" + langueNom + "_stored_string");

      classeGenPageRechercheSuperTemplate = classeDoc.getString("classePageSuperPageTemplate" + "_" + langueNom + "_stored_string");
      classeGenPageRechercheTemplate = classeDoc.getString(String.format("classeGen%sTemplate", classePageRechercheApiMethode) + "_" + langueNom + "_stored_string");
      classeGenPageRechercheCheminJinja = classeDoc.getString(String.format("classeGen%sCheminJinja", classePageRechercheApiMethode) + "_" + langueNom + "_stored_string");

      classePageEditionTemplate = classeDoc.getString(String.format("classe%sTemplate", classePageEditionApiMethode) + "_" + langueNom + "_stored_string");
      classePageEditionCheminJinja = classeDoc.getString(String.format("classe%sCheminJinja", classePageEditionApiMethode) + "_" + langueNom + "_stored_string");

      classeGenPageEditionSuperTemplate = classeDoc.getString("classePageSuperPageTemplate" + "_" + langueNom + "_stored_string");
      classeGenPageEditionTemplate = classeDoc.getString(String.format("classeGen%sTemplate", classePageEditionApiMethode) + "_" + langueNom + "_stored_string");
      classeGenPageEditionCheminJinja = classeDoc.getString(String.format("classeGen%sCheminJinja", classePageEditionApiMethode) + "_" + langueNom + "_stored_string");
    
      File classeGenPageFichier = null;
      File classePageFichier = null;
      File classePageFichierCss = null;
      File classePageFichierJs = null;
      File classePageFichierJsRecherche = null;
      File classePageFichierJsEdition = null;
      File classePageEmplacementFichierJinja = null;
      File classePageBarreLateraleFichierJinja = null;
      File classePageBoutonsRechercheFichierJinja = null;
      File classePageBoutonsPaginationFichierJinja = null;
      File classePageMacrosFormulaireRechercheFichierJinja = null;
      File classePageBoutonsFormulaireRechercheFichierJinja = null;
      File classePageFormulaireRechercheFichierJinja = null;
      File classePageRechercheSuggereFichierJinja = null;
      File classePageRechercheFichierJinja = null;
      File classeGenPageRechercheFichierJinja = null;
      File classePageEditionFichierJinja = null;
      File classeGenPageEditionFichierJinja = null;

      if(classeGenPageChemin != null)
        classeGenPageFichier = new File(classeGenPageChemin);
      if(classePageChemin != null)
        classePageFichier = new File(classePageChemin);
      if(classePageCheminCss != null)
        classePageFichierCss = new File(classePageCheminCss);
      if(classePageCheminJs != null)
        classePageFichierJs = new File(classePageCheminJs);
      if(classePageCheminJsRecherche != null)
        classePageFichierJsRecherche = new File(classePageCheminJsRecherche);
      if(classePageCheminJsEdition != null)
        classePageFichierJsEdition = new File(classePageCheminJsEdition);

      if(classePageEmplacementCheminJinja != null)
        classePageEmplacementFichierJinja = new File(classePageEmplacementCheminJinja);
      if(classePageBarreLateraleCheminJinja != null)
        classePageBarreLateraleFichierJinja = new File(classePageBarreLateraleCheminJinja);

      if(classePageBoutonsRechercheCheminJinja != null)
        classePageBoutonsRechercheFichierJinja = new File(classePageBoutonsRechercheCheminJinja);

      if(classePageBoutonsPaginationCheminJinja != null)
        classePageBoutonsPaginationFichierJinja = new File(classePageBoutonsPaginationCheminJinja);

      if(classePageMacrosFormulaireRechercheCheminJinja != null)
        classePageMacrosFormulaireRechercheFichierJinja = new File(classePageMacrosFormulaireRechercheCheminJinja);

      if(classePageBoutonsFormulaireRechercheCheminJinja != null)
        classePageBoutonsFormulaireRechercheFichierJinja = new File(classePageBoutonsFormulaireRechercheCheminJinja);

      if(classePageFormulaireRechercheCheminJinja != null)
        classePageFormulaireRechercheFichierJinja = new File(classePageFormulaireRechercheCheminJinja);

      if(classePageRechercheSuggereCheminJinja != null)
        classePageRechercheSuggereFichierJinja = new File(classePageRechercheSuggereCheminJinja);

      if(classePageRechercheCheminJinja != null)
        classePageRechercheFichierJinja = new File(classePageRechercheCheminJinja);
      if(classeGenPageRechercheCheminJinja != null)
        classeGenPageRechercheFichierJinja = new File(classeGenPageRechercheCheminJinja);
      if(classePageEditionCheminJinja != null)
        classePageEditionFichierJinja = new File(classePageEditionCheminJinja);
      if(classeGenPageEditionCheminJinja != null)
        classeGenPageEditionFichierJinja = new File(classeGenPageEditionCheminJinja);

      if(classeGenPageFichier != null)
        auteurGenPageClasse = ToutEcrivain.create(classeGenPageFichier, "  ");
      if(classePageFichier != null && (!classePageFichier.exists() || classePageFichier.length() == 0L)) {
        auteurPageClasse = ToutEcrivain.create();
      }
      if(classePageFichierCss != null && (!classePageFichierCss.exists() || classePageFichierCss.length() == 0L)) {
        classePageFichierCss.getParentFile().mkdirs();
        auteurPageCss = ToutEcrivain.create(classePageFichierCss, "  ");
      }
      if(classePageFichierJs != null) {
        classePageFichierJs.getParentFile().mkdirs();
        auteurPageJs = ToutEcrivain.create(classePageFichierJs, "  ");
      }
      if(classePageFichierJsRecherche != null) {
        classePageFichierJsRecherche.getParentFile().mkdirs();
        auteurPageJsRecherche = ToutEcrivain.create(classePageFichierJsRecherche, "  ");
      }
      if(classePageFichierJsEdition != null) {
        classePageFichierJsEdition.getParentFile().mkdirs();
        auteurPageJsEdition = ToutEcrivain.create(classePageFichierJsEdition, "  ");
      }

      if(classePageEmplacementFichierJinja != null) {
        classeGenPageRechercheFichierJinja.getParentFile().mkdirs();
        auteurEmplacementJinja = ToutEcrivain.create(classePageEmplacementFichierJinja, "  ");
      }

      if(classePageBarreLateraleFichierJinja != null) {
        classeGenPageRechercheFichierJinja.getParentFile().mkdirs();
        auteurBarreLateraleJinja = ToutEcrivain.create(classePageBarreLateraleFichierJinja, "  ");
      }

      if(classePageBoutonsRechercheFichierJinja != null) {
        classeGenPageRechercheFichierJinja.getParentFile().mkdirs();
        auteurBoutonsRechercheJinja = ToutEcrivain.create(classePageBoutonsRechercheFichierJinja, "  ");
      }

      if(classePageBoutonsPaginationFichierJinja != null) {
        auteurBoutonsPaginationJinja = ToutEcrivain.create(classePageBoutonsPaginationFichierJinja, "  ");
      }

      if(classePageMacrosFormulaireRechercheFichierJinja != null) {
        classePageMacrosFormulaireRechercheFichierJinja.getParentFile().mkdirs();
        auteurMacrosFormulaireRechercheJinja = ToutEcrivain.create(classePageMacrosFormulaireRechercheFichierJinja, "  ");
      }

      if(classePageBoutonsFormulaireRechercheFichierJinja != null) {
        classePageBoutonsFormulaireRechercheFichierJinja.getParentFile().mkdirs();
        auteurBoutonsFormulaireRechercheJinja = ToutEcrivain.create(classePageBoutonsFormulaireRechercheFichierJinja, "  ");
      }

      if(classePageFormulaireRechercheFichierJinja != null) {
        classePageFormulaireRechercheFichierJinja.getParentFile().mkdirs();
        auteurFormulaireRechercheJinja = ToutEcrivain.create(classePageFormulaireRechercheFichierJinja, "  ");
      }

      if(classePageRechercheSuggereFichierJinja != null) {
        classeGenPageRechercheFichierJinja.getParentFile().mkdirs();
        auteurRechercheSuggereJinja = ToutEcrivain.create(classePageRechercheSuggereFichierJinja, "  ");
      }

      if(classePageRechercheFichierJinja != null && (!classePageRechercheFichierJinja.exists() || classePageRechercheFichierJinja.length() == 0L)) {
        classePageRechercheFichierJinja.getParentFile().mkdirs();
        auteurPageRechercheJinja = ToutEcrivain.create(classePageRechercheFichierJinja, "  ");
      }
      if(classeGenPageRechercheFichierJinja != null) {
        classeGenPageRechercheFichierJinja.getParentFile().mkdirs();
        auteurGenPageRechercheJinja = ToutEcrivain.create(classeGenPageRechercheFichierJinja, "  ");
        auteurGenPageJinjaEntite = ToutEcrivain.create("  ");
      }

      if(classePageEditionFichierJinja != null && (!classePageEditionFichierJinja.exists() || classePageEditionFichierJinja.length() == 0L)) {
        classePageEditionFichierJinja.getParentFile().mkdirs();
        auteurPageEditionJinja = ToutEcrivain.create(classePageEditionFichierJinja, "  ");
      }
      if(classeGenPageEditionFichierJinja != null) {
        classeGenPageEditionFichierJinja.getParentFile().mkdirs();
        auteurGenPageEditionJinja = ToutEcrivain.create(classeGenPageEditionFichierJinja, "  ");
      }

      {
        SolrQuery rechercheSolr = new SolrQuery();   
        rechercheSolr.setQuery("*:*");
        rechercheSolr.setRows(1000000);
        String fqClassesSuperEtMoi = "(" + classesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
        rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
        rechercheSolr.addFilterQuery("classeNomCanonique_" + classeLangueNom + "_indexed_string:" + fqClassesSuperEtMoi);
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
        entitePorteeActuelMap = new HashMap<String, String>();

        List<String> pageVars = Arrays.asList("pageH1", "pageH2", "pageH3", "pageTitre");

        if(rechercheListe.size() > 0) {
          Boolean resultatFormPOST = false; 
          Boolean resultatFormDELETE = false; 
          Boolean resultatFormDELETEFiltre = false; 
          Boolean resultatFormPUTImport = false; 
          Boolean resultatFormPUTFusion = false; 
          Boolean resultatFormPUTCopie = false; 
          Boolean resultatFormPage = false; 
          Boolean resultatFormPATCH = false; 
          Boolean resultatFormRecherche = false; 

          //STUFF2
          if(auteurGenPageEditionJinja != null) {
            o = auteurGenPageEditionJinja;
            for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
              List<SolrDocument> resultatsSubstitues = rechercheListe.stream().filter(o -> BooleanUtils.isTrue((Boolean)o.get("entiteEstSubstitue_stored_boolean"))).collect(Collectors.toList());
              List<SolrDocument> resultatsNormales = rechercheListe.stream().filter(o -> BooleanUtils.isFalse((Boolean)o.get("entiteEstSubstitue_stored_boolean"))).collect(Collectors.toList());

              for(Integer j = 0; j < resultatsNormales.size(); j++) {
                entiteDocumentSolr = rechercheListe.get(j);
                entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + classeLangueNom + "_stored_string");
                SolrDocument resultatSubstitue = resultatsSubstitues.stream().filter(o -> entiteVar.equals(o.get("entiteVar_" + classeLangueNom + "_stored_string"))).findFirst().orElse(null);
                if(resultatSubstitue != null) {
                  if(entiteDocumentSolr.equals(resultatSubstitue))
                    continue;
                  entiteDocumentSolr = resultatSubstitue;
                }

                entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + classeLangueNom + "_stored_string");
                entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + classeLangueNom + "_stored_string");
                entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + classeLangueNom + "_stored_string");
                entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + classeLangueNom + "_stored_string");
                entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
                entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
                entiteFiwareType = (String)entiteDocumentSolr.get("entiteFiwareType_stored_string");
                entiteEtape = (String)entiteDocumentSolr.get("entiteEtape_stored_string");
                entiteCacherPOST = (Boolean)entiteDocumentSolr.get("entiteCacherPOST_stored_boolean");
                entiteCacherPATCH = (Boolean)entiteDocumentSolr.get("entiteCacherPATCH_stored_boolean");
                entiteCacherRecherche = (Boolean)entiteDocumentSolr.get("entiteCacherRecherche_stored_boolean");
                entiteRechercherMaxVarJsonArray = (String)entiteDocumentSolr.get("entiteRechercherMaxVarJsonArray_stored_string");
                entiteRechercherMaxVarValeur = (String)entiteDocumentSolr.get("entiteRechercherMaxVarValeur_stored_string");
                entiteRechercherMaxVar = (String)entiteDocumentSolr.get("entiteRechercherMaxVar_stored_string");
                entiteRechercherMaxValeur = (String)entiteDocumentSolr.get("entiteRechercherMaxValeur_stored_string");
                entiteMin = (String)entiteDocumentSolr.get("entiteMin_stored_string");
                entiteMax = (String)entiteDocumentSolr.get("entiteMax_stored_string");
                entiteDefaut = (String)entiteDocumentSolr.get("entiteDefaut_stored_string");
                entiteIcone = (String)entiteDocumentSolr.get("entiteIcone_stored_string");
                entiteCookie = (String)entiteDocumentSolr.get("entiteCookie_stored_string");
                entiteHtml = (Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean");
                entiteHtmColonne = (Integer)entiteDocumentSolr.get("entiteHtmColonne_stored_int");
                entiteHtmLigne = (Integer)entiteDocumentSolr.get("entiteHtmLigne_stored_int");
                entitePortee = (String)entiteDocumentSolr.get("entitePortee_stored_string");
                entiteHtmLigneTitre = (String)entiteDocumentSolr.get("entiteHtmLigneTitre_" + langueNom + "_stored_string");
                entiteHtmLigneTitreOuvert = (String)entiteDocumentSolr.get("entiteHtmLigneTitreOuvert_" + langueNom + "_stored_string");
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
                entiteRadioValeurs = Optional.ofNullable((List<String>)entiteDocumentSolr.get("entiteRadioValeurs_stored_strings")).orElse(Arrays.asList());
                entiteRadioTextes = Optional.ofNullable((List<String>)entiteDocumentSolr.get("entiteRadioTextes_stored_strings")).orElse(Arrays.asList());
                entiteOptionValeurs = Optional.ofNullable((List<String>)entiteDocumentSolr.get("entiteOptionValeurs_stored_strings")).orElse(Arrays.asList());
                entiteOptionTextes = Optional.ofNullable((List<String>)entiteDocumentSolr.get("entiteOptionTextes_stored_strings")).orElse(Arrays.asList());
                entiteRecharger = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteRecharger_stored_boolean"));
                entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
                entiteCouleur = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteCouleur_stored_boolean"));
                entiteModifier = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteModifier_stored_boolean"));
                entiteDefinir = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteDefinir_stored_boolean"));
                entiteAttribuer = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteAttribuer_stored_boolean"));
                entiteSignature = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSignature_stored_boolean"));
                entiteTexte = (Boolean)entiteDocumentSolr.get("entiteTexte_stored_boolean");
                entiteSuggere = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean"));
                entiteRequis = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteRequis_stored_boolean"));
                entiteListeTypeJson = (String)entiteDocumentSolr.get("entiteListeTypeJson_stored_string");
                entiteAttribuerNomSimple = (String)entiteDocumentSolr.get("entiteAttribuerNomSimple_" + classeLangueNom + "_stored_string");
                entiteAttribuerVar = (String)entiteDocumentSolr.get("entiteAttribuerVar_" + classeLangueNom + "_stored_string");
                entiteAttribuerVarSuggere = (String)entiteDocumentSolr.get("entiteAttribuerVarSuggere_" + classeLangueNom + "_stored_string");
                entiteAttribuerVarUrlPageAffichage = (String)entiteDocumentSolr.get("entiteAttribuerVarUrlPageAffichage_" + langueNom + "_stored_string");
                entiteAttribuerVarUrlPageEdition = (String)entiteDocumentSolr.get("entiteAttribuerVarUrlPageEdition_" + langueNom + "_stored_string");
                entiteAttribuerVarTitre = (String)entiteDocumentSolr.get("entiteAttribuerVarTitre_" + langueNom + "_stored_string");
                entiteAttribuerVarDescription = (String)entiteDocumentSolr.get("entiteAttribuerVarDescription_" + langueNom + "_stored_string");
                entiteAttribuerVarImageUrl = (String)entiteDocumentSolr.get("entiteAttribuerVarImageUrl_" + classeLangueNom + "_stored_string");
                entiteAttribuerVarSuggere = (String)entiteDocumentSolr.get("entiteAttribuerVarSuggere_" + classeLangueNom + "_stored_string");
                entiteAttribuerOperationIdPATCH = (String)entiteDocumentSolr.get("entiteAttribuerOperationIdPATCH_" + classeLangueNom + "_stored_string");
                entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + i18nPage.getString(I18n.var_Recherche) + "_" + langueNom + "_stored_string");
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
                    // if(!entiteTexte && !entiteSuggere && entiteIndexe 
                    // 		&& entiteFacetsTrouves

                wFormRecherche.l(entiteVar);
                if(entiteHtml) {
                  if(entiteHtmCellule != null) {
                    if(ecrireFormEntite(langueNom, i18nClasse, i18nPage, wFormPage, "Page"))
                      resultatFormPage = true;
                    genCodeEntiteHtm(langueNom, i18nClasse, i18nPage, "Page");
                  // if(entiteHtmLigne != null && (entiteDefinir || entiteAttribuer)) {
                    if(ecrireFormEntite(langueNom, i18nClasse, i18nPage, wFormPOST, "POST"))
                      resultatFormPOST = true;
                    if(ecrireFormEntite(langueNom, i18nClasse, i18nPage, wFormDELETE, "DELETE"))
                      resultatFormDELETE = true;
                    if(ecrireFormEntite(langueNom, i18nClasse, i18nPage, wFormDELETE, "DELETEFiltre"))
                      resultatFormDELETEFiltre = true;
                    if(ecrireFormEntite(langueNom, i18nClasse, i18nPage, wFormPUTCopie, i18nPage.getString(I18n.var_PUTCopie)))
                      resultatFormPUTCopie = true;
                    if(ecrireFormEntite(langueNom, i18nClasse, i18nPage, wFormPATCH, "PATCH"))
                      resultatFormPATCH = true;
                  // if(entiteIndexeOuStocke) {
                    if(ecrireFormEntite(langueNom, i18nClasse, i18nPage, wFormRecherche, i18nPage.getString(I18n.var_Recherche)))
                      resultatFormRecherche = true;
                  }
                }
                if(entiteAttribuer) {
                  // Attribuer //
                  wJsHtmInit.tl(5, "function ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, "(event) {");
                  wJsHtmInit.tl(6, i18nClasse.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "(");
                  wJsHtmInit.tl(8, "event.target.value ? [ { 'name': 'q', 'value': '", entiteAttribuerVarSuggere, ":' + event.target.value ? event.target.value : '' }");
                  wJsHtmInit.tl(8, ", { 'name': 'rows', 'value': '10' }");
                  wJsHtmInit.t(8, ", { 'name': 'fl', 'value': '", i18nClasse.getString(I18n.var_classeNomCanonique), ",", entiteAttribuerVar, ",", classeVarClePrimaire, entiteAttribuerVarUrlPageEdition == null ? "" : "," + entiteAttribuerVarUrlPageEdition, entiteAttribuerVarTitre == null ? "" : "," + entiteAttribuerVarTitre, "' } ] : [");
                  wJsHtmInit.l("{%- if ", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " is defined %}{'name':'fq','value':'", entiteAttribuerVar, ":{{ ", i18nGlobale.getString(I18n.var_resultat), ".", entiteVar, " }}'}{%- else %}{%- endif %}]");
                  wJsHtmInit.tl(8, ", document.querySelector('#' + event.target.getAttribute('data-list'))");
                  wJsHtmInit.tl(8, ", null");
                  wJsHtmInit.tl(8, ", null");
                  wJsHtmInit.tl(8, ", true");
                  wJsHtmInit.tl(8, ", this");
                  wJsHtmInit.tl(8, ");");
                  wJsHtmInit.tl(5, "}");
                  wJsHtmInit.tl(5, "document.querySelector('#", classeNomSimple, "_POST_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "input', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");
                  wJsHtmInit.tl(5, "document.querySelector('#", classeNomSimple, "_POST_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "focus', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");
                  wJsHtmInit.tl(5, "document.querySelector('#", classeNomSimple, "_PATCH_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "input', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");
                  wJsHtmInit.tl(5, "document.querySelector('#", classeNomSimple, "_PATCH_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "focus', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");

                  wJsHtmEditionInit.tl(5, "function ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, "(event) {");
                  wJsHtmEditionInit.tl(6, i18nClasse.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "(");
                  wJsHtmEditionInit.tl(8, "[ { 'name': 'q', 'value': '", entiteAttribuerVarSuggere, ":' + event.target.value ? event.target.value : '' }");
                  wJsHtmEditionInit.tl(8, ", { 'name': 'rows', 'value': '10' }");
                  wJsHtmEditionInit.tl(8, ", { 'name': 'fl', 'value': '", i18nClasse.getString(I18n.var_classeNomCanonique), ",", entiteAttribuerVar, ",", classeVarClePrimaire, entiteAttribuerVarUrlPageEdition == null ? "" : "," + entiteAttribuerVarUrlPageEdition, entiteAttribuerVarTitre == null ? "" : "," + entiteAttribuerVarTitre, "' } ]");
                  wJsHtmEditionInit.tl(8, ", document.querySelector('#' + event.target.getAttribute('data-list'))");
                  wJsHtmEditionInit.tl(8, ", event.target.getAttribute('data-method') === '", i18nClasse.getString(I18n.var_Page), "' ? window.", varResultat, ".", classeVarId, " : null");
                  wJsHtmEditionInit.tl(8, ", event.target.getAttribute('data-method') === '", i18nClasse.getString(I18n.var_Page), "' ? window.", varResultat, ".", entiteVar, " : null");
                  wJsHtmEditionInit.tl(8, ", true");
                  wJsHtmEditionInit.tl(8, ");");
                  wJsHtmEditionInit.tl(5, "}");
                  wJsHtmEditionInit.tl(5, "document.querySelector('#", classeNomSimple, "_POST_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "input', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");
                  wJsHtmEditionInit.tl(5, "document.querySelector('#", classeNomSimple, "_POST_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "focus', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");
                  wJsHtmEditionInit.tl(5, "document.querySelector('#", classeNomSimple, "_PATCH_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "input', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");
                  wJsHtmEditionInit.tl(5, "document.querySelector('#", classeNomSimple, "_PATCH_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "focus', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");
                  wJsHtmEditionInit.tl(5, "document.querySelector('#", classeNomSimple, "_", i18nClasse.getString(I18n.var_Page), "_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "input', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");
                  wJsHtmEditionInit.tl(5, "document.querySelector('#", classeNomSimple, "_", i18nClasse.getString(I18n.var_Page), "_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "focus', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");
                  // DO STUFF
                  wJsHtmEditionInit.tl(5, "document.querySelector('#", entiteAttribuerNomSimple, "_POST_", classeVarId, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "input', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");
                  wJsHtmEditionInit.tl(5, "document.querySelector('#", entiteAttribuerNomSimple, "_POST_", classeVarId, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "focus', ", i18nGlobale.getString(I18n.var_attribuer), "_", entiteVar, ");");
                  wJsHtmEditionInit.tl(5, "{% if \"PATCH\" in ", i18nGlobale.getString(I18n.var_portees), " %}");
                  wJsHtmEditionInit.tl(5, i18nClasse.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + window.", varResultat, ".", entiteVar, "}], document.querySelector('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), window.", varResultat, ".", classeVarId, ", window.", varResultat, ".", entiteVar, ", true);");
                  wJsHtmEditionInit.tl(5, "{% else %}");
                  wJsHtmEditionInit.tl(5, "{% if \"GET\" in ", i18nGlobale.getString(I18n.var_portees), " %}");
                  wJsHtmEditionInit.tl(5, i18nClasse.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + window.", varResultat, ".", entiteVar, "}], document.querySelector('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), window.", varResultat, ".", classeVarId, ", window.", varResultat, ".", entiteVar, ", true);");
                  wJsHtmEditionInit.tl(5, "{% else %}");
                  wJsHtmEditionInit.tl(5, i18nClasse.getString(I18n.var_suggere), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + window.", varResultat, ".", entiteVar, "}], document.querySelector('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), window.", varResultat, ".", classeVarId, ", window.", varResultat, ".", entiteVar, ", false);");
                  wJsHtmEditionInit.tl(5, "{% endif %}");
                  wJsHtmEditionInit.tl(5, "{% endif %}");
                  // wJsInit.tl(4, ", event.currentTarget");
                  // wJsInit.tl(4, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterLueur), "(target); }");
                  // wJsInit.tl(4, ", function(", langueConfig.getString(I18n.var_reponse), ", target) { ", langueConfig.getString(I18n.var_ajouterErreur), "(target); }");
//										wWebsocket.tl(2, "tl(2, \"", "patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + \" + ", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Requete), StringUtils.capitalize(classeVarClePrimaire), "() + \" } ], {});\");");
                  wPks.tl(2, "if(c == '", entiteAttribuerNomSimple, "')");
                  wPks.tl(2, "patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + ", classeVarClePrimaire, "2 } ], {}");
                  wPks.tl(4, ");");
                } else if(entiteDefinir) {
                  if(entiteSignature) {
                    wJsEditionInit.tl(4, "document.querySelector('#signatureInput", classeNomSimple, "' + ", classeVarClePrimaire, " + '", entiteVar, "').jSignature({'height':200}).bind('change', function(e){ patch{{", i18nClasse.getString(I18n.var_classeNomSimple), "}}Val([{ name: 'fq', value: '", classeVarId, ":' + ", classeVarId, " }], 'set", entiteVarCapitalise, "', document.querySelector('#signatureInput", classeNomSimple, "' + ", classeVarClePrimaire, " + '", entiteVar, "').jSignature('getData', 'default'), this);");
                  } else {
                    wJsEditionInit.l();
                    wJsEditionInit.tl(5, "// PATCH ", entiteVar);
                    if(!entiteTexte && !entiteSuggere && entiteIndexe 
                        && entiteFacetsTrouves
                        && !i18nClasse.getString(I18n.var_sessionId).equals(entiteVar)
                        && !i18nClasse.getString(I18n.var_utilisateurCle).equals(entiteVar)
                        && !i18nClasse.getString(I18n.var_sauvegardes).equals(entiteVar)
                        ) {
                      wJsRechercheInit.tl(5, "document.querySelector('#fq", classeNomSimple, "_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change', (event) => {");
                      wJsRechercheInit.tl(6, "fqChange('", classeNomSimple, "', event.currentTarget, facetChange", classeNomSimple, "Success, facetChange", classeNomSimple, "Error);");
                      wJsRechercheInit.tl(5, "});");
                      wJsRechercheInit.tl(5, "document.querySelector('#buttonFacet", classeNomSimple, "_", entiteVar, "')?.addEventListener('click', (event) => {");
                      wJsRechercheInit.tl(6, "facetFieldChange('", classeNomSimple, "', event.currentTarget);");
                      wJsRechercheInit.tl(5, "});");
                      wJsRechercheInit.tl(5, "document.querySelector('#pageFacetPivot", classeNomSimple, "_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change', (event) => {");
                      wJsRechercheInit.tl(6, "facetPivotChange('", classeNomSimple, "', event.currentTarget);");
                      wJsRechercheInit.tl(5, "});");
                      wJsRechercheInit.tl(5, "document.querySelector('#pageFacetRangeGap", classeNomSimple, "_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change', (event) => {");
                      wJsRechercheInit.tl(6, "facetRangeGapChange('", classeNomSimple, "', event.currentTarget);");
                      wJsRechercheInit.tl(5, "});");
                      wJsRechercheInit.tl(5, "document.querySelector('#pageFacetRangeStart", classeNomSimple, "_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change', (event) => {");
                      wJsRechercheInit.tl(6, "facetRangeStartChange('", classeNomSimple, "', event.currentTarget);");
                      wJsRechercheInit.tl(5, "});");
                      wJsRechercheInit.tl(5, "document.querySelector('#pageFacetRangeEnd", classeNomSimple, "_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change', (event) => {");
                      wJsRechercheInit.tl(6, "facetRangeEndChange('", classeNomSimple, "', event.currentTarget);");
                      wJsRechercheInit.tl(5, "});");
                    }

                    if("Boolean".equals(entiteNomSimple) && entiteVar.equals(i18nClasse.getString(I18n.var_archive))) {
                      wJsEditionInit.tl(5, "document.querySelector('#", classeNomSimple, "_", i18nClasse.getString(I18n.var_Page), "_", entiteVar, "')?.addEventListener('click', (event) => {");
                      wJsEditionInit.tl(6, "const form = document.querySelector('#", i18nClasse.getString(I18n.var_Page), i18nClasse.getString(I18n.var_Formulaire), "_", entiteVar, "');");
                      wJsEditionInit.tl(6, "const ", i18nClasse.getString(I18n.var_valide), " = form.checkValidity();");
                      wJsEditionInit.tl(6, "if(", i18nClasse.getString(I18n.var_valide), ") {");
                      wJsEditionInit.tl(7, "var confirmResponse = confirm('", i18nPage.getString(I18n.str_confirmer_archiver), "'); ");
                      wJsEditionInit.tl(7, "if(confirmResponse) { ");
                      wJsEditionInit.tl(8, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeVarId, ":' + event.currentTarget.getAttribute('data-", classeVarId, "') }]");
                      wJsEditionInit.tl(10, ", 'set", entiteVarCapitalise, "', !(event.currentTarget.getAttribute('data-val') === 'true')");
                      wJsEditionInit.tl(10, ", event.currentTarget");
                      wJsEditionInit.tl(10, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterLueur), "(target); }");
                      wJsEditionInit.tl(10, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterErreur), "(target); }");
                      wJsEditionInit.tl(10, ");");
                      wJsEditionInit.tl(7, "}");
                      wJsEditionInit.tl(6, "}");
                      wJsEditionInit.tl(5, "});");
                    } else {
                      wJsEditionInit.tl(5, "document.querySelector('#", classeNomSimple, "_", i18nClasse.getString(I18n.var_Page), "_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change', (event) => {");
                      wJsEditionInit.tl(6, "const form = document.querySelector('#", i18nClasse.getString(I18n.var_Page), i18nClasse.getString(I18n.var_Formulaire), "_", entiteVar, "');");
                      wJsEditionInit.tl(6, "const ", i18nClasse.getString(I18n.var_valide), " = form.checkValidity();");
                      if(entiteCookie != null)
                        wJsEditionInit.tl(6, "document.cookie = \"", entiteCookie, "=\" + escape(event.currentTarget.value) + \"; path=/\";");
                      wJsEditionInit.tl(6, "if(", i18nClasse.getString(I18n.var_valide), ") {");
                      if(entiteListeTypeJson != null) {
                        wJsEditionInit.tl(7, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeVarId, ":' + event.currentTarget.getAttribute('data-", classeVarId, "') }]");
                        wJsEditionInit.tl(9, ", 'set", entiteVarCapitalise, "', event.currentTarget.value == '' ? null : JSON.parse(event.currentTarget.value)");
                        wJsEditionInit.tl(9, ", event.currentTarget");
                        wJsEditionInit.tl(9, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterLueur), "(target); }");
                        wJsEditionInit.tl(9, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterErreur), "(target); }");
                        wJsEditionInit.tl(9, ");");
                      } else if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson)) {
                        wJsEditionInit.tl(7, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeVarId, ":' + event.currentTarget.getAttribute('data-", classeVarId, "') }]");
                        wJsEditionInit.tl(9, ", 'set", entiteVarCapitalise, "', event.currentTarget.value == '' ? null : JSON.parse(event.currentTarget.value)");
                        wJsEditionInit.tl(9, ", event.currentTarget");
                        wJsEditionInit.tl(9, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterLueur), "(target); }");
                        wJsEditionInit.tl(9, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterErreur), "(target); }");
                        wJsEditionInit.tl(9, ");");
                      } else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
                        wJsEditionInit.tl(7, "var timeZone = event.currentTarget.getAttribute('data-zone');");
                        // wJsEditionInit.tl(7, "var timeZone = event.currentTarget.value.split('[').pop().split(']')[0];");
                        wJsEditionInit.tl(7, "var t1 = moment(event.currentTarget.value, \"YYYY-MM-DDTHH:mm\");");
                        wJsEditionInit.tl(7, "var t2 = moment.tz(event.currentTarget.value, \"YYYY-MM-DDTHH:mm\", timeZone);");
                        wJsEditionInit.tl(7, "var t3 = new Date(t1._d);");
                        wJsEditionInit.tl(7, "t3.setTime(t1.toDate().getTime() + t2.toDate().getTime() - t1.toDate().getTime());");
                        wJsEditionInit.tl(7, "var t = moment(t3);");
                        wJsEditionInit.tl(7, "if(t) {");
                        wJsEditionInit.tl(8, "var s = t.tz(timeZone).format('YYYY-MM-DDTHH:mm:ss.000') + '[' + timeZone + ']';");
                        wJsEditionInit.tl(8, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeVarId, ":' + event.currentTarget.getAttribute('data-", classeVarId, "') }]");
                        wJsEditionInit.tl(10, ", 'set", entiteVarCapitalise, "', s");
                        wJsEditionInit.tl(10, ", event.currentTarget");
                        wJsEditionInit.tl(10, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterLueur), "(target); }");
                        wJsEditionInit.tl(10, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterErreur), "(target); }");
                        wJsEditionInit.tl(10, ");");
                        wJsEditionInit.tl(7, "}");
                      } else if("LocalTime".equals(entiteNomSimple)) {
                        wJsEditionInit.tl(7, "var t = moment(this.value, '", i18nPage.getString(I18n.var_HAposhAposmm), "'); ");
                        wJsEditionInit.tl(7, "if(t) { ");
                        wJsEditionInit.tl(8, "var s = t.format('HH:mm'); ");
                        wJsEditionInit.tl(8, "patch{{", i18nClasse.getString(I18n.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeVarId, ":{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}' }], 'set", entiteVarCapitalise, "', s, this, function() { ", i18nClasse.getString(I18n.var_ajouterLueur), "(document.querySelector('.{{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", i18nClasse.getString(I18n.var_ajouterErreur), "(document.querySelector('.{{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); ");
                        wJsEditionInit.tl(10, ", 'set", entiteVarCapitalise, "', s");
                        wJsEditionInit.tl(10, ", event.currentTarget");
                        wJsEditionInit.tl(10, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterLueur), "(target); }");
                        wJsEditionInit.tl(10, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterErreur), "(target); }");
                        wJsEditionInit.tl(10, ");");
                        wJsEditionInit.tl(7, "} ");
                      } else if("Boolean".equals(entiteNomSimple)) {
                        wJsEditionInit.tl(7, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeVarId, ":' + event.currentTarget.getAttribute('data-", classeVarId, "') }]");
                        wJsEditionInit.tl(9, ", 'set", entiteVarCapitalise, "', event.currentTarget.checked");
                        wJsEditionInit.tl(9, ", event.currentTarget");
                        if(entiteRecharger) {
                          wJsEditionInit.tl(8, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) {");
                          wJsEditionInit.tl(9, i18nClasse.getString(I18n.var_ajouterLueur), "(target);");
                          wJsEditionInit.tl(9, "fetch('/refresh').then(response => {");
                          wJsEditionInit.tl(10, "response.text().then(text => {");
                          wJsEditionInit.tl(11, "window.location.reload();");
                          wJsEditionInit.tl(10, "});");
                          wJsEditionInit.tl(9, "});");
                          wJsEditionInit.tl(8, "}");
                        } else {
                          wJsEditionInit.tl(8, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterLueur), "(target); }");
                        }
                        wJsEditionInit.tl(9, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterErreur), "(target); }");
                        wJsEditionInit.tl(9, ");");
                      } else if(entiteImageBase64Url != null) {
                        // wJsInitEdition.tl(9, "<", composantsWebPrefixe, "input name=\"", langueConfig.getString(I18n.var_fichier), "\" type=\"file\" onchange=\"fetch('", entiteImageBase64Url, "', { method: 'POST', body: new FormData(this.form)}); \"></", composantsWebPrefixe, "input>");
                        // wJsInitEdition.tl(1, "fetch(");
    
                        // if(methodeGET || methodeDELETE || methodePUTCopie)
                        // 	wJsInitEdition.tl(2, "'", StringUtils.replace(classeApiUriMethode, "{id}", "' + id"));
                        // else if(methodePATCH || methodeRecherche)
                        // 	wJsInitEdition.tl(2, "'", classeApiUriMethode, "?' + ", i18nClasse.getString(I18n.var_filtres), ".map(function(m) { return m.name + '=' + encodeURIComponent(m.value) }).join('&')");
                        // else
                        // 	wJsInitEdition.tl(2, "'", classeApiUriMethode, "'");

                        // wJsInitEdition.tl(2, ", {");
                        // wJsInitEdition.tl(3, "headers: {'Content-Type':'application/json; charset=utf-8'}");
                        // if(!"GET".equals(classeApiMethodeMethode) || "DELETE".equals(classeApiMethodeMethode)) {
                        // 	wJsInitEdition.tl(3, ", method: '", classeApiMethodeMethode, "'");
                        // 	wJsInitEdition.tl(3, ", body: JSON.stringify(vals)");
                        // }
                        // wJsInitEdition.tl(2, "}).then(", i18nClasse.getString(I18n.var_reponse), " => {");
                        // wJsInitEdition.tl(3, "if(", i18nClasse.getString(I18n.var_reponse), ".ok)");
                        // wJsInitEdition.tl(4, "success(", i18nClasse.getString(I18n.var_reponse), ", target);");
                        // wJsInitEdition.tl(3, "else");
                        // wJsInitEdition.tl(4, "error(", i18nClasse.getString(I18n.var_reponse), ", target);");
                        // wJsInitEdition.tl(2, "})");
                        // wJsInitEdition.tl(2, ".catch(", i18nClasse.getString(I18n.var_reponse), " => error(", i18nClasse.getString(I18n.var_reponse), ", target));");
                      } else {
                        wJsEditionInit.tl(7, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeVarId, ":' + event.currentTarget.getAttribute('data-", classeVarId, "') }]");
                        wJsEditionInit.tl(9, ", 'set", entiteVarCapitalise, "', event.currentTarget.value");
                        wJsEditionInit.tl(9, ", event.currentTarget");

                        if(entiteRecharger) {
                          wJsEditionInit.tl(8, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) {");
                          wJsEditionInit.tl(9, i18nClasse.getString(I18n.var_ajouterLueur), "(target);");
                          wJsEditionInit.tl(9, "fetch('/refresh').then(response => {");
                          wJsEditionInit.tl(10, "response.text().then(text => {");
                          wJsEditionInit.tl(11, "window.location.reload();");
                          wJsEditionInit.tl(10, "});");
                          wJsEditionInit.tl(9, "});");
                          wJsEditionInit.tl(8, "}");
                        } else {
                          wJsEditionInit.tl(8, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterLueur), "(target); }");
                        }

                        wJsEditionInit.tl(9, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterErreur), "(target); }");
                        wJsEditionInit.tl(9, ");");
                      }
                      wJsEditionInit.tl(6, "}");
                      wJsEditionInit.tl(5, "});");
                    }

                    wJsEditionInit.tl(5, "document.querySelector('#", classeNomSimple, "_", i18nClasse.getString(I18n.var_Page), "_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "focus', (event) => {");
                    wJsEditionInit.tl(6, "", i18nClasse.getString(I18n.var_enleverLueur), "(event.currentTarget);");
                    wJsEditionInit.tl(5, "});");

                    wJsEditionInit.tl(5, "document.querySelector('#", classeNomSimple, "_", i18nClasse.getString(I18n.var_Page), "_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "blur', (event) => {");
                    wJsEditionInit.tl(6, "const form = document.querySelector('#", i18nClasse.getString(I18n.var_Page), i18nClasse.getString(I18n.var_Formulaire), "_", entiteVar, "');");
                    wJsEditionInit.tl(6, "const ", i18nClasse.getString(I18n.var_valide), " = form.reportValidity();");
                    wJsEditionInit.tl(5, "});");
                  }
                }

                if(entiteDefinir || entiteAttribuer || entiteIndexeOuStocke) {
                  wWebsocketInput3.l();
                  if(entiteHtmLigneTitre != null) {
//										wWebsocketInput3.t(4, "$response.querySelector(\"#").sx(genererId(entiteHtmLigneTitre)).s("\").replaceAll('#").sx(genererId(entiteHtmLigneTitre)).l("');");
//										wWebsocketInput3.l();
                  }
                  wWebsocketInput1.tl(4, "var input", entiteVarCapitalise, " = null;");
                  wWebsocketInput2.tl(4, "if(vars.includes('", entiteVar, "'))");
                  wWebsocketInput2.tl(5, "input", entiteVarCapitalise, " = $response.querySelector('.Page_", entiteVar, "');");
                  wWebsocketInput3.tl(4, "if(input", entiteVarCapitalise, ") {");
                  wWebsocketInput3.tl(5, "document.querySelectorAll('.Page_", entiteVar, "').forEach((item, index) => {");
                  wWebsocketInput3.tl(6, "if(typeof item.value !== 'undefined')");
                  wWebsocketInput3.tl(7, "item.value = input", entiteVarCapitalise, ".getAttribute('value');");
                  wWebsocketInput3.tl(6, "else");
                  wWebsocketInput3.tl(7, "item.textContent = input", entiteVarCapitalise, ".textContent;");
                  wWebsocketInput3.tl(5, "});");
                  wWebsocketInput3.tl(5, i18nClasse.getString(I18n.var_ajouterLueur), "(document.querySelector('.Page_", entiteVar, "'));");
                  wWebsocketInput3.tl(4, "}");
//									if("LocalDate".equals(entiteNomSimple)) {
//										wWebsocketInput.tl(4, "var val = o['", entiteVar, "'];");
////										wWebsocketInput.tl(3, "if(val != null) {");
////										wWebsocketInput.tl(4, "var t = moment(val, 'YYYY-MM-DD');");
////										wWebsocketInput.tl(4, "if(t)");
////										wWebsocketInput.tl(5, "val = t.format('", classePageLangueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "');");
////										wWebsocketInput.tl(3, "}");
//									}
//									else if("LocalDateTime".equals(entiteNomSimple)) {
//										wWebsocketInput.tl(4, "var val = o['", entiteVar, "'];");
////										wWebsocketInput.tl(3, "if(val != null) {");
////										wWebsocketInput.tl(4, "var t = moment(val, 'YYYY-MM-DD');");
////										wWebsocketInput.tl(4, "if(t)");
////										wWebsocketInput.tl(5, "val = t.format('", classePageLangueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "');");
////										wWebsocketInput.tl(3, "}");
//									}
//									else if("LocalTime".equals(entiteNomSimple)) {
//										wWebsocketInput.tl(4, "var val = o['", entiteVar, "'];");
////										wWebsocketInput.tl(3, "if(val != null) {");
////										wWebsocketInput.tl(4, "var t = moment(val, 'HH:mm');");
////										wWebsocketInput.tl(4, "if(t)");
////										wWebsocketInput.tl(5, "val = t.format('", classePageLangueConfig.getString(ConfigCles.var_HAposhAposmm), "');");
////										wWebsocketInput.tl(3, "}");
//									}
//									else {
//										wWebsocketInput.tl(4, "var val = o['", entiteVar, "'];");
//									}
//									wWebsocketInput.tl(4, "if(vars.includes('", entiteVar, "')) {");
//									if(entiteImageBase64Url != null) {
//										wWebsocketInput.tl(5, "if(val === undefined)");
//										wWebsocketInput.tl(6, "val = null;");
//										wWebsocketInput.tl(5, "document.querySelector('.img", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//										wWebsocketInput.tl(6, "if(val !== this.getAttribute('src'))");
//										wWebsocketInput.tl(7, "this.setAttribute('src', val);");
//										wWebsocketInput.tl(5, "});");
//									}
//									if(entiteSignature) {
//										wWebsocketInput.tl(5, "if(val === undefined)");
//										wWebsocketInput.tl(6, "val = null;");
//										wWebsocketInput.tl(5, "document.querySelector('.signatureInput", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//										wWebsocketInput.tl(6, "if(val !== document.querySelector('.signatureImg", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').getAttribute('src'))");
//										wWebsocketInput.tl(7, "document.querySelector('.signatureImg", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').setAttribute('src', val == null ? 'data:image/png;base64,' : val);");
//										wWebsocketInput.tl(6, langueConfig.getString(ConfigCles.var_ajouterLueur), "(document.querySelector('.signatureInput", classeNomSimple, "' + pk + '", entiteVarCapitalise, "'));");
//										wWebsocketInput.tl(5, "});");
//									}
//									wWebsocketInput.tl(5, "document.querySelector('.input", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//									wWebsocketInput.tl(6, "if(val !== this.value)");
//									if(entiteNomSimple.startsWith("Json")) {
//										wWebsocketInput.tl(7, "this.val(JSON.stringify(val));");
//									} else {
//										wWebsocketInput.tl(7, "this.val(val);");
//									}
//									wWebsocketInput.tl(7, langueConfig.getString(ConfigCles.var_ajouterLueur), "(this);");
//									wWebsocketInput.tl(5, "});");
//									wWebsocketInput.tl(5, "document.querySelector('.var", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
//									wWebsocketInput.tl(6, "if(val !== this.text())");
//									if(entiteNomSimple.startsWith("Json")) {
//										wWebsocketInput.tl(7, "this.val(JSON.stringify(val));");
//									} else {
//										wWebsocketInput.tl(7, "this.text(val);");
//									}
//									wWebsocketInput.tl(7, langueConfig.getString(ConfigCles.var_ajouterLueur), "(this);");
//									wWebsocketInput.tl(5, "});");
//									wWebsocketInput.tl(4, "}");
                }
              }
              rechercheSolr.setStart(i.intValue() + rechercheLignes);
              rechercheReponse = clientSolrComputate.query(rechercheSolr);
              rechercheListe = rechercheReponse.getResults();
            }
          }

          wWebsocket.tl(1, "var ", classeVarId, " = ", i18nClasse.getString(I18n.var_requeteApi), "['id'];");
          // wWebsocket.tl(1, "var ", classeVarClePrimaireUnique, "s = ", langueConfig.getString(I18n.var_requeteApi), "['", classeVarClePrimaireUnique, "s'];");
          wWebsocket.tl(1, "var classes = ", i18nClasse.getString(I18n.var_requeteApi), "['classes'];");
          wWebsocket.tl(1, "var vars = ", i18nClasse.getString(I18n.var_requeteApi), "['vars'];");
          wWebsocket.tl(1, "var empty = ", i18nClasse.getString(I18n.var_requeteApi), "['empty'];");
          wWebsocket.l();
          wWebsocket.tl(1, "if(", classeVarId, " != null && vars.length > 0) {");
          wWebsocket.tl(2, "var queryParams = \"?\" + Array.from(document.querySelectorAll(\".pageSearchVal\")).filter(elem => elem.innerText.length > 0).map(elem => elem.innerText).join(\"&\");");
          wWebsocket.tl(2, "var uri = location.pathname + queryParams;");
          wWebsocket.tl(2, "fetch(uri).then(response => {");
          wWebsocket.tl(3, "response.text().then(text => {");
          wWebsocket.tl(4, "var $response = new DOMParser().parseFromString(text, 'text/html');");
//					wWebsocket.tl(2, langueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Vals([ {name: 'fq', value: '", classeVarId, ":' + pk} ], function( data, textStatus, jQxhr ) {");
//					wWebsocket.tl(3, "var o = data['list'][0];");
//					wWebsocket.tl(3, "if(o != null) {");
          wWebsocket.s(wWebsocketInput1);
          wWebsocket.l();
          wWebsocket.s(wWebsocketInput2);
          wWebsocket.l();
          wWebsocket.tl(4, i18nClasse.getString(I18n.var_jsWebsocket), classeNomSimple, "(", classeVarId, ", vars, $response);");
          wWebsocket.tl(4, "window.", varResultat, " = JSON.parse($response.querySelector('.", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Formulaire), " .", varResultat, "')?.value);");
          wWebsocket.tl(4, "window.", i18nClasse.getString(I18n.var_liste), classeNomSimple, " = JSON.parse($response.querySelector('.", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Formulaire), " .", i18nClasse.getString(I18n.var_liste), classeNomSimple, "')?.value);");
          wWebsocket.l();
          wWebsocket.s(wWebsocketInput3);
          wWebsocket.l();
          wWebsocket.tl(5, i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Graphique), classeNomSimple, "();");
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
//						wWebsocket.tl(4, "window['patch' + c + 'Vals']( [ {name: 'fq', value: '", classeVarId, ":' + pk2} ], {});");
//						wWebsocket.tl(3, "}");
//						wWebsocket.tl(2, "}");
//						wWebsocket.tl(2, "if(pk)");
//						wWebsocket.tl(3, "patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Vals( [ {name: 'fq', value: '", classeVarId, ":' + pk} ], {});");
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
            if(entitePorteeActuelMap.get(classeApiMethodeMethode) != null) {
              wFormPOST.tl(6, "{%- endif -%}");
            }
          }
          if(resultatFormPUTImport) {
            wFormPUTImport.tl(7, "</div>");
            wFormPUTImport.tl(6, "</", composantsWebPrefixe, "details>");
            if(entitePorteeActuelMap.get(classeApiMethodeMethode) != null) {
              wFormPUTImport.tl(6, "{%- endif -%}");
            }
          }
          if(resultatFormPUTFusion) {
            wFormPUTFusion.tl(7, "</div>");
            wFormPUTFusion.tl(6, "</", composantsWebPrefixe, "details>");
            if(entitePorteeActuelMap.get(classeApiMethodeMethode) != null) {
              wFormPUTFusion.tl(6, "{%- endif -%}");
            }
          }
          if(resultatFormPUTCopie) {
            wFormPUTCopie.tl(7, "</div>");
            wFormPUTCopie.tl(6, "</", composantsWebPrefixe, "details>");
            if(entitePorteeActuelMap.get(classeApiMethodeMethode) != null) {
              wFormPUTCopie.tl(6, "{%- endif -%}");
            }
          }
          if(resultatFormPage) {
            wFormPage.tl(7, "</div>");
            wFormPage.tl(6, "</", composantsWebPrefixe, "details>");
            if(entitePorteeActuelMap.get(classeApiMethodeMethode) != null) {
              wFormPage.tl(6, "{%- endif -%}");
            }
          }
          if(resultatFormPATCH) {
            wFormPATCH.tl(7, "</div>");
            wFormPATCH.tl(6, "</", composantsWebPrefixe, "details>");
            if(entitePorteeActuelMap.get(classeApiMethodeMethode) != null) {
              wFormPATCH.tl(6, "{%- endif -%}");
            }
          }
          if(resultatFormDELETE) {
            wFormDELETE.tl(7, "</div>");
            wFormDELETE.tl(6, "</", composantsWebPrefixe, "details>");
            if(entitePorteeActuelMap.get(classeApiMethodeMethode) != null) {
              wFormDELETE.tl(6, "{%- endif -%}");
            }
          }
          if(resultatFormDELETEFiltre) {
            wFormDELETEFiltre.tl(7, "</div>");
            wFormDELETEFiltre.tl(6, "</", composantsWebPrefixe, "details>");
            if(entitePorteeActuelMap.get(classeApiMethodeMethode) != null) {
              wFormDELETEFiltre.tl(6, "{%- endif -%}");
            }
          }
          if(resultatFormRecherche) {
            wFormRecherche.tl(7, "</div>");
            wFormRecherche.tl(6, "</", composantsWebPrefixe, "details>");
            if(entitePorteeActuelMap.get(classeApiMethodeMethode) != null) {
              wFormRecherche.tl(6, "{%- endif -%}");
            }
          }
        }
      }
  
  }

  public void pageCodeClasseJava(String langueNom, JsonObject i18nPage) throws Exception {

    if(!classePageCheminsGen.contains(classeGenPageChemin) && classeGenPageChemin != null) {
      classePageCheminsGen.add(classeGenPageChemin);
      o = auteurGenPageClasse;
      if(auteurPageClasse != null) {
        if(classeDroitAuteur != null)
          auteurPageClasse.l(classeDroitAuteur);
        auteurPageClasse.l("package ", classeNomEnsemble, ";");
        auteurPageClasse.l();
        auteurPageClasse.l("/**");

        auteurPageClasse.l(" * ", i18nPage.getString(I18n.var_Promesse), ": true");
        for(String langueNom2 : autresLangues) {
          String classePageNomSimple2 = classeDoc.getString("classePageNomCanonique" + i18nPage.getString(I18n.var_PageRecherche)  + "_" + langueNom2 + "_stored_string");
          if(classePageNomSimple2 != null)
            auteurPageClasse.	l(" * ", i18nPage.getString(I18n.var_NomCanonique), ".", langueNom2, ": ", classePageNomSimple2);
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
      l(" * ", i18nPage.getString(I18n.var_Traduire), ": false");
      for(String langueNom2 : autresLangues) {
        String classeGenPageNomSimple2 = classeDoc.getString("classeGenPageNomCanonique" + i18nPage.getString(I18n.var_PageRecherche)  + "_" + langueNom2 + "_stored_string");
        if(classeGenPageNomSimple2 != null)
          l(" * ", i18nPage.getString(I18n.var_NomCanonique), ".", langueNom2, ": ", classeGenPageNomSimple2);
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
        tl(1, " * ", i18nPage.getString(I18n.var_Ignorer), ": true");
        tl(1, "**/");
        tl(1, "protected void _", i18nPage.getString(I18n.var_requeteSite), "_(", classePartsCouverture.nomSimple(langueNom), "<", classePartsRequeteSite.nomSimple(langueNom), "> c", ") {");
        tl(1, "}");
      }

      if(!classePageSimple) {
        l();
        tl(1, "/**");
        tl(1, " * {@inheritDoc}");
        tl(1, " * ", i18nPage.getString(I18n.var_Ignorer), ": true");
        tl(1, " **/");
        tl(1, "protected void _", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_(", classePartsCouverture.nomSimple(langueNom), "<", i18nPage.getString(I18n.var_ListeRecherche), "<", classeApiClasseNomSimple, ">> ", i18nPage.getString(I18n.var_cVar), ") {");
        tl(1, "}");
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _", i18nPage.getString(I18n.var_page), i18nPage.getString(I18n.var_Reponse), "(", classePartsCouverture.nomSimple(langueNom), "<String> ", i18nPage.getString(I18n.var_cVar), ") {");
        tl(2, "if(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_ != null)");
        tl(3, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse()).map(response -> JsonObject.mapFrom(response).toString()).orElse(null));");
        tl(1, "}");
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _stats(", classePartsCouverture.nomSimple(langueNom), "<SolrResponse.Stats> ", i18nPage.getString(I18n.var_cVar), ") {");
        tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse()).map(response -> response.getStats()).orElse(null));");
        tl(1, "}");
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _facetCounts(", classePartsCouverture.nomSimple(langueNom), "<SolrResponse.FacetCounts> ", i18nPage.getString(I18n.var_cVar), ") {");
        tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse()).map(response -> response.getFacetCounts()).orElse(null));");
        tl(1, "}");
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _pagination(JsonObject pagination) {");
        tl(2, "JsonArray pages = new JsonArray();");
        tl(2, "Long ", i18nPage.getString(I18n.var_debut), " = ", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getStart().longValue();");
        tl(2, "Long ", i18nPage.getString(I18n.var_lignes), " = ", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getRows().longValue();");
        tl(2, "Long ", i18nPage.getString(I18n.var_numTrouve), " = Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse()).map(response -> response.getResponse().getNumFound().longValue()).orElse(Long.valueOf(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getList().size()));");
        tl(2, "Long ", i18nPage.getString(I18n.var_debut), "Num = ", i18nPage.getString(I18n.var_debut), " + 1L;");
        tl(2, "Long ", i18nPage.getString(I18n.var_fin), "Num = ", i18nPage.getString(I18n.var_debut), " + ", i18nPage.getString(I18n.var_lignes), ";");
        tl(2, "Long floorMod = (", i18nPage.getString(I18n.var_lignes), " == 0L ? 0L : Math.floorMod(", i18nPage.getString(I18n.var_numTrouve), ", ", i18nPage.getString(I18n.var_lignes), "));");
        tl(2, "Long ", i18nPage.getString(I18n.var_dernier), " = (", i18nPage.getString(I18n.var_lignes), " == 0L ? 0L : Math.floorDiv(", i18nPage.getString(I18n.var_numTrouve), ", ", i18nPage.getString(I18n.var_lignes), ") - (floorMod.equals(0L) ? 1L : 0L) * ", i18nPage.getString(I18n.var_lignes), ");");
        tl(2, i18nPage.getString(I18n.var_fin), "Num = ", i18nPage.getString(I18n.var_fin), "Num < ", i18nPage.getString(I18n.var_numTrouve), " ? ", i18nPage.getString(I18n.var_fin), "Num : ", i18nPage.getString(I18n.var_numTrouve), ";");
        tl(2, i18nPage.getString(I18n.var_debut), "Num = ", i18nPage.getString(I18n.var_numTrouve), " == 0L ? 0L : ", i18nPage.getString(I18n.var_debut), "Num;");
        tl(2, "Long pagination", i18nPage.getString(I18n.var_Debut), " = ", i18nPage.getString(I18n.var_debut), " - 10L * ", i18nPage.getString(I18n.var_lignes), ";");
        tl(2, "if(pagination", i18nPage.getString(I18n.var_Debut), " < 0L)");
        tl(3, "pagination", i18nPage.getString(I18n.var_Debut), " = 0L;");
        tl(2, "Long pagination", i18nPage.getString(I18n.var_Fin), " = ", i18nPage.getString(I18n.var_debut), " + 10L * ", i18nPage.getString(I18n.var_lignes), ";");
        tl(2, "if(pagination", i18nPage.getString(I18n.var_Fin), " > ", i18nPage.getString(I18n.var_numTrouve), ")");
        tl(3, "pagination", i18nPage.getString(I18n.var_Fin), " = ", i18nPage.getString(I18n.var_numTrouve), ";");
        l();
        tl(2, "pagination.put(\"1L\", 1L);");
        tl(2, "pagination.put(\"0L\", 0L);");
        tl(2, "pagination.put(\"", i18nPage.getString(I18n.var_debut), "\", ", i18nPage.getString(I18n.var_debut), ");");
        tl(2, "pagination.put(\"", i18nPage.getString(I18n.var_lignes), "\", ", i18nPage.getString(I18n.var_lignes), ");");
        tl(2, "pagination.put(\"", i18nPage.getString(I18n.var_lignes), i18nPage.getString(I18n.var_Precedent), "\", ", i18nPage.getString(I18n.var_lignes), " / 2);");
        tl(2, "pagination.put(\"", i18nPage.getString(I18n.var_lignes), i18nPage.getString(I18n.var_Prochaine), "\", ", i18nPage.getString(I18n.var_lignes), " * 2);");
        tl(2, "pagination.put(\"", i18nPage.getString(I18n.var_debut), "Num\", ", i18nPage.getString(I18n.var_debut), "Num);");
        tl(2, "pagination.put(\"", i18nPage.getString(I18n.var_fin), "Num\", ", i18nPage.getString(I18n.var_fin), "Num);");
        tl(2, "pagination.put(\"", i18nPage.getString(I18n.var_numTrouve), "\", ", i18nPage.getString(I18n.var_numTrouve), ");");
        tl(2, "pagination.put(\"page", i18nPage.getString(I18n.var_Debut), "\", new JsonObject().put(\"", i18nPage.getString(I18n.var_debut), "\", 0L).put(\"page", i18nPage.getString(I18n.var_Numero), "\", 1L));");
        tl(2, "if(", i18nPage.getString(I18n.var_debut), " > 0L)");
        tl(3, "pagination.put(\"page", i18nPage.getString(I18n.var_Precedent), "\", new JsonObject().put(\"", i18nPage.getString(I18n.var_debut), "\", ", i18nPage.getString(I18n.var_debut), " - ", i18nPage.getString(I18n.var_lignes), ").put(\"page", i18nPage.getString(I18n.var_Numero), "\", ", i18nPage.getString(I18n.var_debut), " - ", i18nPage.getString(I18n.var_lignes), " + 1L));");
        tl(2, "if(", i18nPage.getString(I18n.var_debut), " + ", i18nPage.getString(I18n.var_lignes), " < ", i18nPage.getString(I18n.var_numTrouve), ")");
        tl(3, "pagination.put(\"page", i18nPage.getString(I18n.var_Prochaine), "\", new JsonObject().put(\"", i18nPage.getString(I18n.var_debut), "\", ", i18nPage.getString(I18n.var_debut), " + ", i18nPage.getString(I18n.var_lignes), ").put(\"page", i18nPage.getString(I18n.var_Numero), "\", ", i18nPage.getString(I18n.var_debut), " + ", i18nPage.getString(I18n.var_lignes), " + 1L));");
        tl(2, "pagination.put(\"page", i18nPage.getString(I18n.var_Fin), "\", new JsonObject().put(\"", i18nPage.getString(I18n.var_debut), "\", ", i18nPage.getString(I18n.var_dernier), ").put(\"page", i18nPage.getString(I18n.var_Numero), "\", ", i18nPage.getString(I18n.var_dernier), " + 1L));");
        tl(2, "pagination.put(\"pages\", pages);");
        tl(2, "for(Long i = pagination", i18nPage.getString(I18n.var_Debut), "; i < pagination", i18nPage.getString(I18n.var_Fin), "; i += ", i18nPage.getString(I18n.var_lignes), ") {");
        tl(3, "Long page", i18nPage.getString(I18n.var_Numero), " = Math.floorDiv(i, ", i18nPage.getString(I18n.var_lignes), ") + 1L;");
        tl(3, "JsonObject page = new JsonObject();");
        tl(3, "page.put(\"page", i18nPage.getString(I18n.var_Numero), "\", page", i18nPage.getString(I18n.var_Numero), ");");
        tl(3, "page.put(\"", i18nPage.getString(I18n.var_pageActuel), "\", ", i18nPage.getString(I18n.var_debut), ".equals(i));");
        tl(3, "page.put(\"", i18nPage.getString(I18n.var_debut), "\", i);");
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
        tl(2, classeNomSimple, ".varsQ", i18nPage.getString(I18n.var_PourClasse), "().forEach(var -> {");
        tl(3, "JsonObject json = new JsonObject();");
        tl(3, "json.put(\"var\", var);");
        tl(3, "json.put(\"", i18nPage.getString(I18n.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", i18nPage.getString(I18n.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
        tl(3, "json.put(\"", i18nPage.getString(I18n.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", i18nPage.getString(I18n.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
        tl(3, "json.put(\"val\", Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getQuery()).filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).map(s -> SearchTool.unescapeQueryChars(StringUtils.substringAfter(s, \":\"))).orElse(null));");
        tl(3, "vars.put(var, json);");
        tl(2, "});");
        tl(1, "}");
  
        ////////////
        // varsFq //
        ////////////
  
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _varsFqCount(", classePartsCouverture.nomSimple(langueNom), "<Integer> w) {");
        tl(1, "}");
  
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _varsFq(JsonObject vars) {");
        tl(2, "Map<String, SolrResponse.FacetField> facetFields = Optional.ofNullable(facetCounts).map(c -> c.getFacetFields()).map(f -> f.getFacets()).orElse(new HashMap<String,SolrResponse.FacetField>());");
        tl(2, "varsFqCount = 0;");
        tl(2, "for(String var : ", classeNomSimple, ".varsFq", i18nPage.getString(I18n.var_PourClasse), "()) {");
        tl(3, "String var", i18nPage.getString(I18n.var_Indexe), " = ", classeNomSimple, ".var", i18nPage.getString(I18n.var_Indexe), classeNomSimple, "(var);");
        tl(3, "String var", i18nPage.getString(I18n.var_Stocke), " = ", classeNomSimple, ".var", i18nPage.getString(I18n.var_Stocke), classeNomSimple, "(var);");
        tl(3, "JsonObject json = new JsonObject();");
        tl(3, "json.put(\"var\", var);");
        tl(3, "json.put(\"var", i18nPage.getString(I18n.var_Stocke), "\", var", i18nPage.getString(I18n.var_Stocke), ");");
        tl(3, "json.put(\"var", i18nPage.getString(I18n.var_Indexe), "\", var", i18nPage.getString(I18n.var_Indexe), ");");
        tl(3, "String type = StringUtils.substringAfterLast(var", i18nPage.getString(I18n.var_Indexe), ", \"_\");");
        tl(3, "json.put(\"", i18nPage.getString(I18n.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", i18nPage.getString(I18n.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
        tl(3, "json.put(\"", i18nPage.getString(I18n.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", i18nPage.getString(I18n.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
        tl(3, "Object v = ", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getFilterQueries().stream().filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).findFirst().map(s -> SearchTool.unescapeQueryChars(StringUtils.substringAfter(s, \":\"))).orElse(null);");
        tl(3, "if(v != null) {");
        tl(4, "Matcher mFq = Pattern.compile(\"(\\\\w+):(.+?(?=(\\\\)|\\\\s+OR\\\\s+|\\\\s+AND\\\\s+|$)))\").matcher(SearchTool.unescapeQueryChars((String)v));");
        tl(4, "StringBuffer sb = new StringBuffer();");
        tl(4, "while(mFq.find()) {");
        tl(5, "String entityVar = ", classeNomSimple, ".searchVar", classeNomSimple, "(varIndexed);");
        tl(5, "String valueIndexed = mFq.group(2).trim();");
        tl(5, "String entityFq = entityVar + \":\" + valueIndexed;");
        tl(5, "if(var.equals(entityVar))");
        tl(6, "mFq.appendReplacement(sb, valueIndexed);");
        tl(5, "else");
        tl(6, "mFq.appendReplacement(sb, entityFq);");
        tl(4, "}");
        tl(4, "if(!sb.isEmpty()) {");
        tl(5, "mFq.appendTail(sb);");
        tl(5, "json.put(\"val\", sb.toString());");
        tl(4, "}");
        tl(4, "varsFqCount++;");
        tl(3, "}");
        tl(3, "Optional.ofNullable(stats).map(s -> s.get(var", i18nPage.getString(I18n.var_Indexe), ")).ifPresent(stat -> {");
        tl(4, "json.put(\"stats\", JsonObject.mapFrom(stat));");
        tl(3, "});");
  
        tl(3, "Optional.ofNullable(facetFields.get(var", i18nPage.getString(I18n.var_Indexe), ")).ifPresent(facetField -> {");
        tl(4, "JsonObject facetJson = new JsonObject();");
        tl(4, "JsonObject counts = new JsonObject();");
        tl(4, "facetJson.put(\"var\", var);");
        tl(4, "facetField.getCounts().forEach((val, count) -> {");
        tl(5, "counts.put(val, count);");
        tl(4, "});");
        tl(4, "facetJson.put(\"counts\", counts);");
        tl(4, "json.put(\"facetField\", facetJson);");
        tl(3, "});");
  
        tl(3, "if(default", i18nPage.getString(I18n.var_ListeChamps), "Vars.contains(var)) {");
        tl(4, "json.put(\"", i18nPage.getString(I18n.var_listeChamps), "\", true);");
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
        tl(4, "json.put(\"", i18nPage.getString(I18n.var_activer), i18nPage.getString(I18n.var_Calendrier), "\", true);");
        tl(4, "setDefault", i18nPage.getString(I18n.var_Gamme), i18nPage.getString(I18n.var_Stats), "(json);");
        tl(3, "}");
        tl(3, "json.put(\"", i18nPage.getString(I18n.var_activer), i18nPage.getString(I18n.var_Stats), "\", !StringUtils.equalsAny(type, \"boolean\", \"location\"));");
        tl(3, "if(default", i18nPage.getString(I18n.var_Stats), "Vars.contains(var)) {");
        tl(4, "SolrResponse.StatsField varStats = stats.get(var", i18nPage.getString(I18n.var_Indexe), ");");
        tl(4, "if(varStats != null)");
        tl(5, "json.put(\"", i18nPage.getString(I18n.var_stats), "\", varStats);");
        tl(3, "}");
  
        tl(3, "if(default", i18nPage.getString(I18n.var_Pivot), "Vars.contains(var)) {");
        tl(4, "json.put(\"", i18nPage.getString(I18n.var_pivot), "\", true);");
        tl(3, "}");
  
        tl(3, "if(default", i18nPage.getString(I18n.var_Tri), "Vars.contains(String.format(\"%s asc\", var))) {");
        tl(4, "json.put(\"", i18nPage.getString(I18n.var_tri), "\", \"asc\");");
        tl(3, "} else if(default", i18nPage.getString(I18n.var_Tri), "Vars.contains(String.format(\"%s desc\", var))) {");
        tl(4, "json.put(\"", i18nPage.getString(I18n.var_tri), "\", \"desc\");");
        tl(3, "}");
  
        tl(3, "vars.put(var, json);");
        tl(2, "}");
        tl(1, "}");
  
        ///////////////
        // varsGamme //
        ///////////////
  
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _vars", i18nPage.getString(I18n.var_Gamme), "(JsonObject vars) {");
  //			tl(2, "Map<String, SolrResponse.Pivot> pivotFields = Optional.ofNullable(facetCounts).map(c -> c.getFacetPivot()).map(f -> f.getPivotMap()).orElse(new HashMap<String,SolrResponse.Pivot>());");
        tl(2, classeNomSimple, ".vars", i18nPage.getString(I18n.var_Gamme), i18nPage.getString(I18n.var_PourClasse), "().forEach(var -> {");
        tl(3, "String var", i18nPage.getString(I18n.var_Indexe), " = ", classeNomSimple, ".var", i18nPage.getString(I18n.var_Indexe), classeNomSimple, "(var);");
        tl(3, "JsonObject json = new JsonObject();");
        tl(3, "json.put(\"var\", var);");
        tl(3, "json.put(\"", i18nPage.getString(I18n.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", i18nPage.getString(I18n.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
        tl(3, "json.put(\"", i18nPage.getString(I18n.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", i18nPage.getString(I18n.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
        tl(3, "json.put(\"val\", ", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getFilterQueries().stream().filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).findFirst().map(s -> SearchTool.unescapeQueryChars(StringUtils.substringAfter(s, \":\"))).orElse(null));");
  
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
        tl(2, "ServiceRequest ", i18nPage.getString(I18n.var_requeteService), " = ", i18nPage.getString(I18n.var_requeteSite), "_.getServiceRequest();");
        tl(2, "JsonObject params = ", i18nPage.getString(I18n.var_requeteService), ".getParams();");
        l();
        tl(2, "JsonObject queryParams = Optional.ofNullable(", i18nPage.getString(I18n.var_requeteService), ").map(ServiceRequest::getParams).map(or -> or.getJsonObject(\"query\")).orElse(new JsonObject());");
        tl(2, "Long num = Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getResponse()).map(response -> response.getResponse().getNumFound().longValue()).orElse(Long.valueOf(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getList().size()));");
        tl(2, "String q = \"*:*\";");
        tl(2, "String q1 = \"", classeVarTexte, "\";");
        tl(2, "String q2 = \"\";");
        tl(2, "for(String param", i18nPage.getString(I18n.var_Nom), " : queryParams.fieldNames()) {");
        tl(3, "String ", i18nPage.getString(I18n.var_entite), "Var = null;");
        tl(3, "String ", i18nPage.getString(I18n.var_valeur), i18nPage.getString(I18n.var_Indexe), " = null;");
        tl(3, "Object param", i18nPage.getString(I18n.var_ValeursObjet), " = queryParams.getValue(param", i18nPage.getString(I18n.var_Nom), ");");
        tl(3, "JsonArray param", i18nPage.getString(I18n.var_Objets), " = param", i18nPage.getString(I18n.var_ValeursObjet), " instanceof JsonArray ? (JsonArray)param", i18nPage.getString(I18n.var_ValeursObjet), " : new JsonArray().add(param", i18nPage.getString(I18n.var_ValeursObjet), ");");
        l();
        tl(3, "try {");
        tl(4, "for(Object param", i18nPage.getString(I18n.var_Objet), " : param", i18nPage.getString(I18n.var_Objets), ") {");
        tl(5, "switch(param", i18nPage.getString(I18n.var_Nom), ") {");
        tl(5, "case \"q\":");
        tl(6, "q = (String)param", i18nPage.getString(I18n.var_Objet), ";");
        tl(6, i18nPage.getString(I18n.var_entite), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", i18nPage.getString(I18n.var_Objet), ", \":\"));");
        tl(6, i18nPage.getString(I18n.var_valeur), i18nPage.getString(I18n.var_Indexe), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", i18nPage.getString(I18n.var_Objet), ", \":\")), \"UTF-8\");");
        tl(6, "q1 = ", i18nPage.getString(I18n.var_entite), "Var.equals(\"*\") ? q1 : ", i18nPage.getString(I18n.var_entite), "Var;");
        tl(6, "q2 = ", i18nPage.getString(I18n.var_valeur), i18nPage.getString(I18n.var_Indexe), ";");
        tl(6, "q = q1 + \":\" + q2;");
        tl(5, "}");
        tl(4, "}");
        tl(3, "} catch(Exception e) {");
        tl(4, "ExceptionUtils.rethrow(e);");
        tl(3, "}");
        tl(2, "}");
        tl(2, "query.put(\"q\", q);");
        l();
        tl(2, "Long rows1 = Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getRows()).orElse(10L);");
        tl(2, "Long start1 = Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getStart()).orElse(1L);");
        tl(2, "Long start2 = start1 - rows1;");
        tl(2, "Long start3 = start1 + rows1;");
        tl(2, "Long rows2 = rows1 / 2;");
        tl(2, "Long rows3 = rows1 * 2;");
        tl(2, "start2 = start2 < 0 ? 0 : start2;");
        tl(2, "JsonObject fqs = new JsonObject();");
        tl(2, "for(String fq : Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getFilterQueries()).orElse(Arrays.asList())) {");
        tl(3, "if(!StringUtils.contains(fq, \"(\")) {");
        tl(4, "String fq1 = ", classeNomSimple, ".", i18nPage.getString(I18n.var_recherche), "Var", classeNomSimple, "(StringUtils.substringBefore(fq, \":\"));");
        tl(4, "String fq2 = StringUtils.substringAfter(fq, \":\");");
        tl(4, "if(!StringUtils.startsWithAny(fq, \"", i18nPage.getString(I18n.var_classeNomsCanoniques), "_\", \"", i18nPage.getString(I18n.var_archive), "_\", \"sessionId\", \"", i18nPage.getString(I18n.var_utilisateur), i18nPage.getString(I18n.var_Cle), "s\"))");
        tl(5, "fqs.put(fq1, new JsonObject().put(\"var\", fq1).put(\"val\", fq2).put(\"", i18nPage.getString(I18n.var_nomAffichage), "\", ", classeNomSimple, ".", i18nPage.getString(I18n.var_nomAffichage), i18nPage.getString(I18n.var_PourClasse), "(fq1)));");
        tl(4, "}");
        tl(3, "}");
        tl(2, "query.put(\"fq\", fqs);");
        l();
        tl(2, "JsonArray sorts = new JsonArray();");
        tl(2, "for(String sort : Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getSorts()).orElse(Arrays.asList())) {");
        tl(3, "String sort1 = ", classeNomSimple, ".", i18nPage.getString(I18n.var_recherche), "Var", classeNomSimple, "(StringUtils.substringBefore(sort, \" \"));");
        tl(3, "sorts.add(new JsonObject().put(\"var\", sort1).put(\"order\", StringUtils.substringAfter(sort, \" \")).put(\"", i18nPage.getString(I18n.var_nomAffichage), "\", ", classeNomSimple, ".", i18nPage.getString(I18n.var_nomAffichage), i18nPage.getString(I18n.var_PourClasse), "(sort1)));");
        tl(2, "}");
        tl(2, "query.put(\"sort\", sorts);");
        tl(1, "}");
        if(classePageSuperNomSimple != null && (classeEtendBase || !classeModele)) {
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultZoneId(", classePartsCouverture.nomSimple(langueNom), "<String> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_requeteSite), "_.get", i18nPage.getString(I18n.var_Requete), "Vars().get(VAR_defaultZoneId)).orElse(", i18nPage.getString(I18n.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".SITE_ZONE)));");
          tl(1, "}");
          l();
          tl(1, "/**");
          tl(1, " * ", i18nPage.getString(I18n.var_Ignorer), ": true");
          tl(1, " **/");
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultTimeZone(", classePartsCouverture.nomSimple(langueNom), "<ZoneId> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(ZoneId.of(defaultZoneId));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultLocaleId(", classePartsCouverture.nomSimple(langueNom), "<String> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_requeteSite), "_.getRequestHeaders().get(\"Accept-Language\")).map(acceptLanguage -> StringUtils.substringBefore(acceptLanguage, \",\")).orElse(", i18nPage.getString(I18n.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".SITE_LOCALE)));");
          tl(1, "}");
          l();
          tl(1, "/**");
          tl(1, " * ", i18nPage.getString(I18n.var_Ignorer), ": true");
          tl(1, " **/");
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultLocale(", classePartsCouverture.nomSimple(langueNom), "<Locale> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(Locale.forLanguageTag(defaultLocaleId));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _rows(", classePartsCouverture.nomSimple(langueNom), "<Long> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, "if(", i18nPage.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"rows\", null) != null)");
          tl(3, i18nPage.getString(I18n.var_cVar), ".o(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getRows());");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _start(", classePartsCouverture.nomSimple(langueNom), "<Long> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, "if(", i18nPage.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"start\", null) != null)");
          tl(3, i18nPage.getString(I18n.var_cVar), ".o(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getStart());");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _rangeGap(", classePartsCouverture.nomSimple(langueNom), "<String> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, "if(", i18nPage.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"facet.range.gap\", null) != null)");
          tl(3, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeGap()).orElse(null));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _rangeEnd(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, "if(", i18nPage.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"facet.range.end\", null) != null)");
          tl(3, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeEnd()).map(s -> TimeTool.parseZonedDateTime(defaultTimeZone, s)).orElse(null));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _rangeStart(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, "if(", i18nPage.getString(I18n.var_requeteService), ".getParams().getJsonObject(\"query\").getString(\"facet.range.start\", null) != null)");
          tl(3, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeStart()).map(s -> TimeTool.parseZonedDateTime(defaultTimeZone, s)).orElse(null));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultRangeGap(", classePartsCouverture.nomSimple(langueNom), "<String> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(rangeGap).orElse(Optional.ofNullable(defaultRangeStats).map(s -> s.getString(\"defaultRangeGap\")).orElse(\"+1HOUR\")));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultRangeEnd(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(rangeEnd).orElse(Optional.ofNullable(defaultRangeStats).map(s -> Instant.parse(s.getString(\"defaultRangeEnd\")).atZone(defaultTimeZone)).orElse(ZonedDateTime.now(defaultTimeZone).toLocalDate().atStartOfDay(defaultTimeZone).plusDays(1))));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultRangeStart(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(rangeStart).orElse(Optional.ofNullable(defaultRangeStats).map(s -> Instant.parse(s.getString(\"defaultRangeStart\")).atZone(defaultTimeZone)).orElse(defaultRangeEnd.minusDays(7).toLocalDate().atStartOfDay(defaultTimeZone))));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultRangeVar(", classePartsCouverture.nomSimple(langueNom), "<String> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRanges()).orElse(Optional.ofNullable(defaultRangeStats).map(s -> Arrays.asList(s.getString(\"defaultRangeVar\"))).orElse(Arrays.asList())).stream().findFirst().map(v -> { if(v.contains(\"}\")) return StringUtils.substringBefore(StringUtils.substringAfterLast(v, \"}\"), \"_\"); else return ", classeNomSimple, ".", i18nPage.getString(I18n.var_recherche), "Var", classeNomSimple, "(v); }).orElse(\"", classeVarCree, "\"));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultFacetSort(", classePartsCouverture.nomSimple(langueNom), "<String> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetSort()).orElse(\"index\"));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultFacetLimit(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetLimit()).orElse(1));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultFacetMinCount(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetMinCount()).orElse(1));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultPivotMinCount(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, i18nPage.getString(I18n.var_cVar), ".o(Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetPivotMinCount()).orElse(0));");
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _DEFAULT_MAP_LOCATION(", classePartsCouverture.nomSimple(langueNom), "<JsonObject> ", i18nPage.getString(I18n.var_cVar), ") {");
          if(classeVarEmplacement != null) {
            tl(2, "Point point = ", classeNomSimple, ".staticSet", StringUtils.capitalize(classeVarEmplacement), "(", i18nPage.getString(I18n.var_requeteSite), "_, Optional.ofNullable(", i18nPage.getString(I18n.var_requeteSite), "_.get", i18nPage.getString(I18n.var_Requete), "Vars().get(VAR_DEFAULT_MAP_LOCATION)).orElse(", i18nPage.getString(I18n.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".DEFAULT_MAP_LOCATION)));");
            tl(2, "w.o(new JsonObject().put(\"type\", \"Point\").put(\"coordinates\", new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))));");
          }
          tl(1, "}");
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _DEFAULT_MAP_ZOOM(", classePartsCouverture.nomSimple(langueNom), "<BigDecimal> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, "String s = Optional.ofNullable(", i18nPage.getString(I18n.var_requeteSite), "_.get", i18nPage.getString(I18n.var_Requete), "Vars().get(VAR_DEFAULT_MAP_ZOOM)).orElse(", i18nPage.getString(I18n.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".DEFAULT_MAP_ZOOM));");
          tl(2, "if(s != null)");
          tl(3, i18nPage.getString(I18n.var_cVar), ".o(new BigDecimal(s));");
          tl(1, "}");
        } else {
          l();
          if(classePageSuperNomSimple != null)
            tl(1, "@Override");
          tl(1, "protected void _defaultRangeStats(", classePartsCouverture.nomSimple(langueNom), "<JsonObject> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(1, "}");
        }
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _default", i18nPage.getString(I18n.var_Tri), "Vars(List<String> l) {");
        tl(2, "if(!", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getDefaultSort()) {");
        tl(3, "Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getSorts()).orElse(Arrays.asList()).forEach(var", i18nPage.getString(I18n.var_Tri), "Str -> {");
        tl(4, "String var", i18nPage.getString(I18n.var_Tri), i18nPage.getString(I18n.var_Parties), "[] = var", i18nPage.getString(I18n.var_Tri), "Str.split(\" \");");
        tl(4, "String var", i18nPage.getString(I18n.var_Tri), " = ", classeNomSimple, ".", i18nPage.getString(I18n.var_recherche), "Var", classeNomSimple, "(var", i18nPage.getString(I18n.var_Tri), i18nPage.getString(I18n.var_Parties), "[0]);");
        tl(4, "String var", i18nPage.getString(I18n.var_Tri), i18nPage.getString(I18n.var_Direction), " = var", i18nPage.getString(I18n.var_Tri), i18nPage.getString(I18n.var_Parties), "[1];");
        tl(4, "l.add(String.format(\"%s %s\", var", i18nPage.getString(I18n.var_Tri), ", var", i18nPage.getString(I18n.var_Tri), i18nPage.getString(I18n.var_Direction), "));");
        tl(3, "});");
        tl(2, "}");
        tl(1, "}");
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _default", i18nPage.getString(I18n.var_ListeChamps), "Vars(List<String> l) {");
        tl(2, "Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFields()).orElse(Arrays.asList()).forEach(var", i18nPage.getString(I18n.var_Stocke), " -> {");
        tl(3, "String var", i18nPage.getString(I18n.var_Stocke), "2 = var", i18nPage.getString(I18n.var_Stocke), ";");
        tl(3, "if(StringUtils.contains(var", i18nPage.getString(I18n.var_Stocke), "2, \"}\"))");
        tl(4, "var", i18nPage.getString(I18n.var_Stocke), "2 = StringUtils.substringAfterLast(var", i18nPage.getString(I18n.var_Stocke), "2, \"}\");");
        tl(3, "String[] parts = var", i18nPage.getString(I18n.var_Stocke), "2.split(\",\");");
        tl(3, "for(String part : parts) {");
        tl(4, "if(StringUtils.isNotBlank(part)) {");
        tl(5, "String var = ", classeNomSimple, ".", i18nPage.getString(I18n.var_recherche), "Var", classeNomSimple, "(part);");
        tl(5, "if(StringUtils.isNotBlank(var))");
        tl(6, "l.add(var);");
        tl(4, "}");
        tl(3, "}");
        tl(2, "});");
        tl(1, "}");
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _default", i18nPage.getString(I18n.var_Stats), "Vars(List<String> l) {");
        tl(2, "Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getStatsFields()).orElse(Arrays.asList()).forEach(var", i18nPage.getString(I18n.var_Indexe), " -> {");
        tl(3, "String var", i18nPage.getString(I18n.var_Indexe), "2 = var", i18nPage.getString(I18n.var_Indexe), ";");
        tl(3, "if(StringUtils.contains(var", i18nPage.getString(I18n.var_Indexe), "2, \"}\"))");
        tl(4, "var", i18nPage.getString(I18n.var_Indexe), "2 = StringUtils.substringAfterLast(var", i18nPage.getString(I18n.var_Indexe), "2, \"}\");");
        tl(3, "String[] parts = var", i18nPage.getString(I18n.var_Indexe), "2.split(\",\");");
        tl(3, "for(String part : parts) {");
        tl(4, "if(StringUtils.isNotBlank(part)) {");
        tl(5, "String var = ", classeNomSimple, ".", i18nPage.getString(I18n.var_recherche), "Var", classeNomSimple, "(part);");
        tl(5, "if(StringUtils.isNotBlank(var))");
        tl(6, "l.add(var);");
        tl(4, "}");
        tl(3, "}");
        tl(2, "});");
        tl(1, "}");
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _default", i18nPage.getString(I18n.var_Pivot), "Vars(List<String> l) {");
        tl(2, "Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetPivots()).orElse(Arrays.asList()).forEach(facetPivot -> {");
        tl(3, "String facetPivot2 = facetPivot;");
        tl(3, "if(StringUtils.contains(facetPivot2, \"}\"))");
        tl(4, "facetPivot2 = StringUtils.substringAfterLast(facetPivot2, \"}\");");
        tl(3, "String[] parts = facetPivot2.split(\",\");");
        tl(3, "for(String part : parts) {");
        tl(4, "if(StringUtils.isNotBlank(part)) {");
        tl(5, "String var = ", classeNomSimple, ".", i18nPage.getString(I18n.var_recherche), "Var", classeNomSimple, "(part);");
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
        tl(1, "protected void _", i18nPage.getString(I18n.var_liste), classeApiClasseNomSimple, "(JsonArray l) {");
        tl(2, "Optional.ofNullable(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_).map(o -> o.get", i18nPage.getString(I18n.var_Liste), "()).orElse(Arrays.asList()).stream().map(o -> JsonObject.mapFrom(o)).forEach(o -> l.add(o));");
        tl(1, "}");
        l();
        tl(1, "protected void _", varResultat, "Count(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", i18nPage.getString(I18n.var_cVar), ") {");
        tl(2, i18nPage.getString(I18n.var_cVar), ".o(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_ == null ? 0 : ", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.size());");
        tl(1, "}");
      }
      l();
      tl(1, "/**");
      tl(1, " * ", i18nGlobale.getString(I18n.var_Initialise), ": false");
      tl(1, "**/");
      tl(1, "protected void _", varResultat, "(", classePartsCouverture.nomSimple(langueNom), "<", classeApiClasseNomSimple, "> ", i18nPage.getString(I18n.var_cVar), ") {");
      if(classePageSimple) {
        tl(2, i18nPage.getString(I18n.var_cVar), ".o(new ", classeApiClasseNomSimple, "());");
      } else {
        tl(2, "if(", varResultat, "Count >= 1 && Optional.ofNullable(", i18nPage.getString(I18n.var_requeteSite), "_.get", i18nPage.getString(I18n.var_RequeteService), "().getParams().getJsonObject(\"path\")).map(o -> o.getString(\"", classeVarId, "\")).orElse(null) != null)");
        tl(3, i18nPage.getString(I18n.var_cVar), ".o(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_.get(0));");
      }
      tl(1, "}");
      if(!classePageSimple) {
        if(classeModele) {
          l();
          tl(1, "protected void _", classeVarClePrimaire, "(", classePartsCouverture.nomSimple(langueNom), "<Long> ", i18nPage.getString(I18n.var_cVar), ") {");
          tl(2, "if(", varResultat, " != null)");
          tl(3, i18nPage.getString(I18n.var_cVar), ".o(", varResultat, ".get", StringUtils.capitalize(classeVarClePrimaire), "());");
          tl(1, "}");
        }
        l();
        tl(1, "protected void _", classeVarCleUnique, "(", classePartsCouverture.nomSimple(langueNom), "<String> ", i18nPage.getString(I18n.var_cVar), ") {");
        tl(2, "if(", varResultat, " != null)");
        tl(3, i18nPage.getString(I18n.var_cVar), ".o(", varResultat, ".get", StringUtils.capitalize(classeVarCleUnique), "());");
        tl(1, "}");
      }

      l();
      if(classePageSuperNomSimple != null) {
        tl(1, "@Override");
      } else {
        tl(1, "/**");
        tl(1, " * ", i18nPage.getString(I18n.var_Ignorer), ": true");
        tl(1, "**/");
      }
      tl(1, "protected void _promise", i18nPage.getString(I18n.var_Avant), "(Promise<Void> promise) {");
      tl(2, "promise.complete();");
      tl(1, "}");

      l();
      if(classePageSuperNomSimple != null)
        tl(1, "@Override");
      tl(1, "protected void _", i18nPage.getString(I18n.var_classeNomSimple), "(", classePartsCouverture.nomSimple(langueNom), "<String> ", i18nPage.getString(I18n.var_cVar), ") {");
      tl(2, i18nPage.getString(I18n.var_cVar), ".o(\"", classeApiClasseNomSimple, "\");");
      tl(1, "}");

      l();
      if(classePageSuperNomSimple != null)
        tl(1, "@Override");
      tl(1, "protected void _page", i18nPage.getString(I18n.var_Titre), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
      if(classeVarTitre != null) {
        tl(2, "if(", varResultat, " != null && ", varResultat, ".get", StringUtils.capitalize(classeVarTitre), "() != null)");
        tl(3, "c.o(", varResultat, ".get", StringUtils.capitalize(classeVarTitre), "()", ");");
        tl(2, "else if(", varResultat, " != null)");
      } else {
        tl(2, "if(", varResultat, " != null)");
      }
      tl(3, "c.o(", q(classeTitre), ");");
      if(!classePageSimple) {
        tl(2, "else if(", i18nPage.getString(I18n.var_listeRecherche), classeApiClasseNomSimple, "_ == null || ", varResultat, "Count == 0)");
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
      tl(1, "protected void _", i18nPage.getString(I18n.var_classeTousNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> w) {");
      tl(2, "w.o(", q(classeTousNom), ");");
      tl(1, "}");

      l();
      if(classePageSuperNomSimple != null)
        tl(1, "@Override");
      String classeUriPageRechercheLangue = classeDoc.getString("classeUriPageRecherche_" + classeLangueNom + "_stored_string");
      tl(1, "protected void _pageUri(", classePartsCouverture.nomSimple(langueNom), "<String> w) {");
      tl(2, "if(\"", classeLangueNom, "\".equals(lang))");
      tl(3, "w.o(", q(classeUriPageRechercheLangue), ");");
      for(String pageLangueNom : autresLangues) {
        classeUriPageRechercheLangue = classeDoc.getString("classeUriPageRecherche_" + pageLangueNom + "_stored_string");
        if(classeUriPageRechercheLangue != null) {
          tl(2, "else if(\"", pageLangueNom, "\".equals(lang))");
          tl(3, "w.o(", q(classeUriPageRechercheLangue), ");");
        }
      }
      tl(1, "}");

      l();
      if(classePageSuperNomSimple != null)
        tl(1, "@Override");
      tl(1, "protected void _apiUri(", classePartsCouverture.nomSimple(langueNom), "<String> w) {");
      tl(2, "w.o(", q(classeApiUri), ");");
      tl(1, "}");

      l();
      if(classePageSuperNomSimple != null) {
        tl(1, "@Override");
      } else {
        tl(1, "/**");
        tl(1, " * ", i18nPage.getString(I18n.var_Ignorer), ": true");
        tl(1, "**/");
      }
      tl(1, "protected void _promise", i18nPage.getString(I18n.var_Apres), "(Promise<Void> promise) {");
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
        tl(1, "protected void _pageImage", i18nPage.getString(I18n.var_Largeur), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
        tl(3, "c.o(", classeImageLargeur, ");");
        tl(1, "}");
      }

      if(classeImageHauteur != null) {
        l();
        if(classePageSuperNomSimple != null)
          tl(1, "@Override");
        tl(1, "protected void _pageImage", i18nPage.getString(I18n.var_Hauteur), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
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
        tl(1, "protected void _", i18nPage.getString(I18n.var_classe), i18nPage.getString(I18n.var_Icone), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
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
  
      o = auteurGenPageClasse;

      tl(0, "}");

      auteurGenPageClasse.flushClose();
      if(auteurPageClasse != null) {
        Files.writeString(Path.of(classePageChemin), auteurPageClasse.toString());
      }
    }
  }

  public void pageCodeClasseJinja(String classeLangueNom, String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {
    classeVarId = classeDoc.getString("classeVarId_" + classeLangueNom + "_stored_string");

    if(auteurPageJsRecherche != null) {

      auteurPageJsRecherche.tl(0, "Promise.all([");
      auteurPageJsRecherche.tl(2, "customElements.whenDefined('", composantsWebPrefixe, "button')");
      auteurPageJsRecherche.tl(2, ", customElements.whenDefined('", composantsWebPrefixe, "input')");
      auteurPageJsRecherche.tl(2, ", customElements.whenDefined('", composantsWebPrefixe, "select')");
      auteurPageJsRecherche.tl(2, ", customElements.whenDefined('", composantsWebPrefixe, "radio')");
      auteurPageJsRecherche.tl(2, ", customElements.whenDefined('", composantsWebPrefixe, "checkbox')");
      auteurPageJsRecherche.tl(2, ", customElements.whenDefined('", composantsWebPrefixe, "dropdown')");
      auteurPageJsRecherche.tl(2, "]).then(() => {");

      auteurPageJsRecherche.l();
      auteurPageJsRecherche.tl(1, "document.querySelector('#pageFacet", i18nClasse.getString(I18n.var_Gamme), classeNomSimple, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change', (event) => {");
      auteurPageJsRecherche.tl(2, "facet", i18nClasse.getString(I18n.var_Gamme), "Change('", classeNomSimple, "', event.target.value);");
      auteurPageJsRecherche.tl(1, "});");
    }
    // if(classePageCheminsGen.contains(classeGenPageChemin) && classeGenPageChemin != null) {


      {
        SolrQuery rechercheSolr = new SolrQuery();
        rechercheSolr.setQuery("*:*");
        rechercheSolr.setRows(1000000);
        String fqClassesSuperEtMoi = "(" + classesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
        rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
        rechercheSolr.addFilterQuery("classeNomCanonique_" + classeLangueNom + "_indexed_string:" + fqClassesSuperEtMoi);
        rechercheSolr.addFilterQuery("entiteHtmColonne_indexed_int:[* TO *]");
        rechercheSolr.addSort("entiteHtmColonne_indexed_int", ORDER.asc);
        QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
        SolrDocumentList rechercheListe = rechercheReponse.getResults();
        Integer rechercheLignes = rechercheSolr.getRows();
        Integer rechercheLigne = -1;
        Integer rechercheLigneActuel;
  
        if(rechercheListe.size() > 0) {
          wStyle.tl(3, ":root {");
          wStyle.tl(4, "--site-results-number-of-columns: ", rechercheListe.size(), ";");
          wStyle.tl(3, "}");

          for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
            List<SolrDocument> resultatsSubstitues = rechercheListe.stream().filter(o -> BooleanUtils.isTrue((Boolean)o.get("entiteEstSubstitue_stored_boolean"))).collect(Collectors.toList());
            List<SolrDocument> resultatsNormales = rechercheListe.stream().filter(o -> BooleanUtils.isFalse((Boolean)o.get("entiteEstSubstitue_stored_boolean"))).collect(Collectors.toList());
            List<SolrDocument> resultatsCombines = new ArrayList<>();

            for(Integer j = 0; j < resultatsNormales.size(); j++) {
              entiteDocumentSolr = resultatsNormales.get(j);
              entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + classeLangueNom + "_stored_string");
              SolrDocument resultatSubstitue = resultatsSubstitues.stream().filter(o -> entiteVar.equals(o.get("entiteVar_" + classeLangueNom + "_stored_string"))).findFirst().orElse(null);
              if(resultatSubstitue != null) {
                entiteDocumentSolr = resultatSubstitue;
              }
              resultatsCombines.add(entiteDocumentSolr);
            }
            for(Integer j = 0; j < resultatsSubstitues.size(); j++) {
              entiteDocumentSolr = resultatsSubstitues.get(j);
              if(!resultatsCombines.contains(entiteDocumentSolr))
                resultatsCombines.add(entiteDocumentSolr);
            }
            resultatsCombines.stream().sorted(Comparator.comparing(classApiMethod -> doc.getInteger("entiteHtmColonne_indexed_int")));
            for(Integer j = 0; j < resultatsCombines.size(); j++) {
              entiteDocumentSolr = resultatsCombines.get(j);
              entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + classeLangueNom + "_stored_string");

              entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + classeLangueNom + "_stored_string");
              entiteSolrNomSimple = (String)entiteDocumentSolr.get("entiteSolrNomSimple_stored_string");
              entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + classeLangueNom + "_stored_string");
              entiteNomCanonique = (String)entiteDocumentSolr.get("entiteNomCanonique_" + classeLangueNom + "_stored_string");
              entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + classeLangueNom + "_stored_string");
              entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + classeLangueNom + "_stored_string");
              entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
              entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
              entiteFiwareType = (String)entiteDocumentSolr.get("entiteFiwareType_stored_string");
              entiteCacherPOST = (Boolean)entiteDocumentSolr.get("entiteCacherPOST_stored_boolean");
              entiteCacherPATCH = (Boolean)entiteDocumentSolr.get("entiteCacherPATCH_stored_boolean");
              entiteCacherRecherche = (Boolean)entiteDocumentSolr.get("entiteCacherRecherche_stored_boolean");
              entiteRechercherMaxVarJsonArray = (String)entiteDocumentSolr.get("entiteRechercherMaxVarJsonArray_stored_string");
              entiteRechercherMaxVarValeur = (String)entiteDocumentSolr.get("entiteRechercherMaxVarValeur_stored_string");
              entiteRechercherMaxVar = (String)entiteDocumentSolr.get("entiteRechercherMaxVar_stored_string");
              entiteRechercherMaxValeur = (String)entiteDocumentSolr.get("entiteRechercherMaxValeur_stored_string");
              entiteMin = (String)entiteDocumentSolr.get("entiteMin_stored_string");
              entiteMax = (String)entiteDocumentSolr.get("entiteMax_stored_string");
              entiteDefaut = (String)entiteDocumentSolr.get("entiteDefaut_stored_string");
              entiteIcone = (String)entiteDocumentSolr.get("entiteIcone_stored_string");
              entiteCookie = (String)entiteDocumentSolr.get("entiteCookie_stored_string");
              entiteHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean"));
              entiteHtmColonne = (Integer)entiteDocumentSolr.get("entiteHtmColonne_stored_int");
              entiteHtmLigne = (Integer)entiteDocumentSolr.get("entiteHtmLigne_stored_int");
              entitePortee = (String)entiteDocumentSolr.get("entitePortee_stored_string");
              entiteHtmLigneTitre = (String)entiteDocumentSolr.get("entiteHtmLigneTitre_" + langueNom + "_stored_string");
              entiteHtmLigneTitreOuvert = (String)entiteDocumentSolr.get("entiteHtmLigneTitreOuvert_" + langueNom + "_stored_string");
              entiteHtmLigneVerticale = (Boolean)entiteDocumentSolr.get("entiteHtmLigneVerticale_stored_boolean");
              entiteHtmLigneEnTeteExpression = (String)entiteDocumentSolr.get("entiteHtmLigneEnTeteExpression_stored_string");
              entiteHtmCellule = (Integer)entiteDocumentSolr.get("entiteHtmCellule_stored_int");
              entiteFormatHtm = (String)entiteDocumentSolr.get("entiteFormatHtm_stored_string");
              entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
              entiteCouleur = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteCouleur_stored_boolean"));
              entiteHighlighting = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHighlighting_stored_boolean"));
              entiteVarTitre = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteVarTitre_stored_boolean"));
              entiteLien = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteLien_stored_boolean"));
              entiteFacetsTrouves = Optional.ofNullable((Boolean)entiteDocumentSolr.get("entiteFacetsTrouves_stored_boolean")).orElse(false);
              entiteFacets = Optional.ofNullable((List<String>)entiteDocumentSolr.get("entiteFacets_stored_strings")).orElse(Arrays.asList());
              if(entiteHtml) {
                String jsVal = ".value";
                if("Boolean".equals(entiteNomSimple)) {
                  jsVal = ".checked";
                }
                if(entitePortee != null) {
                  wTh.tl(10, "{% if '", entitePortee, "' in ", i18nGlobale.getString(I18n.var_portees), " -%}");
                  wTd.tl(10, "{% if '", entitePortee, "' in ", i18nGlobale.getString(I18n.var_portees), " -%}");
                }
                if(entiteLien) {
                  wTh.tl(10, "<div></div>");
                } else {
                  // JS Tri //
                  wTh.tl(10, "<", composantsWebPrefixe, "dropdown id=\"htm", i18nGlobale.getString(I18n.var_ListeDeroulante), "-", entiteVar, "\">");
                  wTh.tl(11, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " slot=\"trigger\" caret>", entiteNomAffichage, "</", composantsWebPrefixe, "button>");
                  wTh.tl(11, "<", composantsWebPrefixe, "menu>");
                  wTh.tl(12, "<", composantsWebPrefixe, "menu-item onclick=\"var e = document.querySelector('#pageFacet", i18nClasse.getString(I18n.var_Tri), classeNomSimple, "_", entiteVar, "'); e.value = this.getAttribute('data-order'); document.querySelectorAll('.pageSearchVal-page", i18nClasse.getString(I18n.var_Tri), "-", classeNomSimple, "').forEach(e => e.remove()); e.dispatchEvent(new Event('change', {})); \" data-action=\"", i18nClasse.getString(I18n.var_tri), "\" data-order=\"asc\" id=\"htm", i18nGlobale.getString(I18n.var_ListeDeroulante), "-", entiteVar, "-asc\">");
                  wTh.tl(13, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-arrow-down-a-z\"></i>");
                  wTh.t(13).sx(String.format(i18nPage.getString(I18n.str_trier_par___croissante), entiteNomAffichage)).l();
                  wTh.tl(12, "</", composantsWebPrefixe, "menu-item>");
                  wTh.tl(12, "<", composantsWebPrefixe, "menu-item onclick=\"var e = document.querySelector('#pageFacet", i18nClasse.getString(I18n.var_Tri), classeNomSimple, "_", entiteVar, "'); e.value = this.getAttribute('data-order'); document.querySelectorAll('.pageSearchVal-page", i18nClasse.getString(I18n.var_Tri), "-", classeNomSimple, "').forEach(e => e.remove()); e.dispatchEvent(new Event('change', {})); \" data-action=\"", i18nClasse.getString(I18n.var_tri), "\" data-order=\"desc\" id=\"htm", i18nGlobale.getString(I18n.var_ListeDeroulante), "-", entiteVar, "-desc\">");
                  wTh.tl(13, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-arrow-down-z-a\"></i>");
                  wTh.t(13).sx(String.format(i18nPage.getString(I18n.str_trier_par___decroissante), entiteNomAffichage)).l();
                  wTh.tl(12, "</", composantsWebPrefixe, "menu-item>");
                  wTh.tl(11, "</", composantsWebPrefixe, "menu>");
                  wTh.tl(10, "</", composantsWebPrefixe, "dropdown>");
                }

                if(entiteLien) {
                  wTd.tl(10, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", "");
                  wTd.tl(11, "id=\"", classeNomSimple, "_{{", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

                  if(entiteNomAffichage != null) {
                    wTd.tl(11, "placeholder=\"[", entiteNomSimple, "] ", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
                    wTd.tl(11, "label=\"", entiteDefaut == null ? entiteNomAffichage : entiteDefaut, "\"");
                  }
                  if(entiteDescription != null) {
                    wTd.t(11, "title=\"").sx(entiteDescription).l("\"");
                  }

                  if(classeVarId != null) {
                    wTd.tl(11, "class=\"button-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " input", classeNomSimple, "{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}", entiteVarCapitalise, " \"");
                  } else {
                    wTd.tl(11, "class=\"button-on-left {{", classeNomSimple, "_", i18nClasse.getString(I18n.var_classeApiMethodeMethode), "}}_", entiteVar, " class", classeNomSimple, " \"");
                  }
                  wTd.tl(11, "name=\"set", entiteVarCapitalise, "\"");
                  wTd.tl(11, "href=\"{{ item.", entiteVar, " | e }}\"");
                  wTd.tl(11, ">");
                  if(entiteIcone != null) {
                    wTd.tl(12, entiteIcone.replace("<i ", "<i slot=\"start\" "));
                  }
                  if(entiteNomAffichage != null) {
                    wTd.t(12).sx(entiteNomAffichage).l();
                  }
                  wTd.tl(10, "</", composantsWebPrefixe, "button>");
                } else {
                  if(wTd.getEmpty()) {
                    wTd.tl(10, "<a href=\"{{ item.", classeVarUrlPageAffichage, " }}\">");
                    wTd.tl(11, classeIcone);
                  } else {
                    wTd.tl(10, "<a href=\"{{ item.", classeVarUrlPageAffichage, " }}\">");
                  }
                  wTd.t(11, "<span", entiteMultiligne ? " class=\"white-space-pre-wrap \"" : "", ">");
                  if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
                    wTd.l("<", composantsWebPrefixe, "format-date weekday=\"short\" month=\"short\" day=\"numeric\" year=\"numeric\" hour=\"numeric\" minute=\"numeric\" second=\"numeric\" time-zone-name=\"short\" date=\"{{ formatZonedDateTime(item.", entiteVar, ", \"yyyy-MM-dd'T'HH:mm:ss.SSSX\", defaultLocaleId, \"UTC\") }}\"></", composantsWebPrefixe, "format-date>");
                  } else if(StringUtils.equals(entiteNomCanonique, LocalDate.class.getCanonicalName())) {
                    wTd.l("{{ formatLocalDate(item.", entiteVar, ", \"", entiteFormatHtm, "\", defaultLocaleId) }}");
                  } else if(StringUtils.equals(entiteNomCanonique, LocalDateTime.class.getCanonicalName())) {
                    wTd.l("{{ formatLocalDateTime(item.", entiteVar, ", \"", entiteFormatHtm, "\", defaultLocaleId) }}");
                  } else if(StringUtils.equals(entiteNomCanonique, LocalTime.class.getCanonicalName())) {
                    wTd.l("{{ formatLocalTime(item.", entiteVar, ", \"", entiteFormatHtm, "\", defaultLocaleId) }}");
                  } else if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())) {
                    wTd.l("{{ formatNumber(item.", entiteVar, ", \"", entiteFormatHtm, "\", defaultLocaleId) }}");
                  } else {
                    wTd.s("{{ item.", entiteVar, " | e }}");
                  }
                  wTd.l("</span>");
                  wTd.tl(10, "</a>");
                  if(entiteHighlighting) {
                    wTd.tl(10, "{% if highlightList is defined %}");
                    wTd.tl(10, "<div class=\"site-highlight \">");
                      wTd.tl(11, "StringUtils.join(highlightList, \" ... \")");
                    wTd.tl(10, "</div>");
                    wTd.tl(10, "{% endif %}");
                  }
                }
                if(entitePortee != null) {
                  wTh.tl(10, "{%- endif %}");
                  wTd.tl(10, "{%- endif %}");
                }
              }

              wFoot.tl(7, "{% if get", i18nClasse.getString(I18n.var_Colonne), entiteVarCapitalise, " is defined %}");
              wFoot.tl(8, "<div>");
              if(entiteFacetsTrouves) {
                for(String entiteFacet : entiteFacets) {
                  if("sum".equals(entiteFacet)) {
    
                    if("Double".equals(entiteSolrNomSimple))
                      wFoot.tl(9, "BigDecimal ", entiteFacet, "_", entiteVar, " = Optional.ofNullable((", entiteSolrNomSimple, ")facets.get(\"", entiteFacet, "_", entiteVar, "\")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));");
                    else
                      wFoot.tl(9, entiteSolrNomSimple, " ", entiteFacet, "_", entiteVar, " = Optional.ofNullable((", entiteSolrNomSimple, ")facets.get(\"", entiteFacet, "_", entiteVar, "\")).orElse(new ", entiteSolrNomSimple, "(0));");

                    wFoot.tl(9, "<span class=\"\"font-weight-bold \">", entiteFacet, "_", entiteVar, "</span>");
                  }
                }
              }
              wFoot.tl(8, "</div>");
              wFoot.tl(7, "{% endif %}");
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
        rechercheSolr.addFilterQuery("classeNomCanonique_" + classeLangueNom + "_indexed_string:" + fqClassesSuperEtMoi);
        rechercheSolr.addSort("entiteHtmLigne_indexed_int", ORDER.asc);
        rechercheSolr.addSort("entiteHtmCellule_indexed_int", ORDER.asc);
        QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
        SolrDocumentList rechercheListe = rechercheReponse.getResults();
        Integer rechercheLignes = rechercheSolr.getRows();
        Integer rechercheLigne = -1;
        Integer rechercheLigneActuel;
  
        if(rechercheListe.size() > 0) {
          for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
            List<SolrDocument> resultatsSubstitues = rechercheListe.stream().filter(o -> BooleanUtils.isTrue((Boolean)o.get("entiteEstSubstitue_stored_boolean"))).collect(Collectors.toList());
            List<SolrDocument> resultatsNormales = rechercheListe.stream().filter(o -> BooleanUtils.isFalse((Boolean)o.get("entiteEstSubstitue_stored_boolean"))).collect(Collectors.toList());

            for(Integer j = 0; j < resultatsNormales.size(); j++) {
              entiteDocumentSolr = rechercheListe.get(j);
              entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + classeLangueNom + "_stored_string");
              SolrDocument resultatSubstitue = resultatsSubstitues.stream().filter(o -> entiteVar.equals(o.get("entiteVar_" + classeLangueNom + "_stored_string"))).findFirst().orElse(null);
              if(resultatSubstitue != null) {
                if(entiteDocumentSolr.equals(resultatSubstitue))
                  continue;
                entiteDocumentSolr = resultatSubstitue;
              }

              entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + classeLangueNom + "_stored_string");
              entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + classeLangueNom + "_stored_string");
              entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + classeLangueNom + "_stored_string");
              entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + classeLangueNom + "_stored_string");
              entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
              entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
              entiteFiwareType = (String)entiteDocumentSolr.get("entiteFiwareType_stored_string");
              entiteCacherPOST = (Boolean)entiteDocumentSolr.get("entiteCacherPOST_stored_boolean");
              entiteCacherPATCH = (Boolean)entiteDocumentSolr.get("entiteCacherPATCH_stored_boolean");
              entiteCacherRecherche = (Boolean)entiteDocumentSolr.get("entiteCacherRecherche_stored_boolean");
              entiteRechercherMaxVarJsonArray = (String)entiteDocumentSolr.get("entiteRechercherMaxVarJsonArray_stored_string");
              entiteRechercherMaxVarValeur = (String)entiteDocumentSolr.get("entiteRechercherMaxVarValeur_stored_string");
              entiteRechercherMaxVar = (String)entiteDocumentSolr.get("entiteRechercherMaxVar_stored_string");
              entiteRechercherMaxValeur = (String)entiteDocumentSolr.get("entiteRechercherMaxValeur_stored_string");
              entiteMin = (String)entiteDocumentSolr.get("entiteMin_stored_string");
              entiteMax = (String)entiteDocumentSolr.get("entiteMax_stored_string");
              entiteDefaut = (String)entiteDocumentSolr.get("entiteDefaut_stored_string");
              entiteIcone = (String)entiteDocumentSolr.get("entiteIcone_stored_string");
              entiteCookie = (String)entiteDocumentSolr.get("entiteCookie_stored_string");
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
                  wRecherche.tl(2, "var $", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, "Checkbox = $", i18nClasse.getString(I18n.var_formulaireFiltres), ".querySelector('input.", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "[type = \"checkbox\"]');");
                  wRecherche.tl(2, "var $", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, "Select = $", i18nClasse.getString(I18n.var_formulaireFiltres), ".querySelector('select.", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "');");
                  wRecherche.tl(2, "var ", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, "Select.length ? $", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, "Select.value : $", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, "Checkbox.checked;");

                  wRecherche.tl(2, "var ", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, "SelectVal = $", i18nClasse.getString(I18n.var_formulaireFiltres), ".querySelector('select.", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, "')?.value;");
                  wRecherche.tl(2, "var ", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, " = null;");
                  wRecherche.tl(2, "if(", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, "SelectVal !== '')");
                  wRecherche.tl(3, i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, " = ", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, "SelectVal == 'true';");
                }
                else {
                  wRecherche.tl(2, "var ", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_formulaireFiltres), ".querySelector('.", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "')?.value;");
                }

                if("Boolean".equals(entiteNomSimple))
                  wRecherche.tl(2, "if(", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, " != null && ", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, " === true)");
                else
                  wRecherche.tl(2, "if(", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, " != null && ", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, " !== '')");

                wRecherche.tl(3, i18nClasse.getString(I18n.var_filtres), ".push({ name: '", (entiteSuggere ? "q" : "fq"), "', value: '", entiteVar, ":' + ", i18nClasse.getString(I18n.var_filtre), entiteVarCapitalise, " });");

                wVarsFqJs.tl(1, "vars.push({ var: '", entiteVar, "', "
                    , "var", i18nClasse.getString(I18n.var_Indexe), ": '", entiteVar, (entiteDocValues != null && entiteDocValues ? "_docvalues" : (entiteStocke != null && entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "'"
                    , ", ", i18nClasse.getString(I18n.var_nomAffichage), ": ", entiteNomAffichage == null ? "null" : "'" + entiteNomAffichage + "'", "});");


                if(auteurPageJsRecherche != null) {
                  // JS Tri //
                  auteurPageJsRecherche.l();
                  auteurPageJsRecherche.tl(1, "document.querySelector('#pageSelect", i18nClasse.getString(I18n.var_Tri), classeNomSimple, "_", entiteVar, "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change', (event) => {");
                  auteurPageJsRecherche.tl(2, i18nClasse.getString(I18n.var_tri), "('", classeNomSimple, "', '", entiteVar, "', event.currentTarget.value);");
                  auteurPageJsRecherche.tl(1, "});");

                  // JS Stats //
                  auteurPageJsRecherche.l();
                  auteurPageJsRecherche.tl(1, "document.querySelector('#page", i18nClasse.getString(I18n.var_Stats), classeNomSimple, "_", entiteVar, "')?.addEventListener('", composantsWebPrefixe, "show', (event) => {");
                  auteurPageJsRecherche.tl(2, "facet", i18nClasse.getString(I18n.var_Stats), "Change('", classeNomSimple, "', '", entiteVar, "', true);");
                  auteurPageJsRecherche.tl(1, "});");
                  auteurPageJsRecherche.tl(1, "document.querySelector('#page", i18nClasse.getString(I18n.var_Stats), classeNomSimple, "_", entiteVar, "')?.addEventListener('", composantsWebPrefixe, "hide', (event) => {");
                  auteurPageJsRecherche.tl(2, "facet", i18nClasse.getString(I18n.var_Stats), "Change('", classeNomSimple, "', '", entiteVar, "', false);");
                  auteurPageJsRecherche.tl(1, "});");
                }
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
                    wPOST.tl(1, "var ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " = (Array.from($", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelectorAll('.", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "')).filter(e => e.checked == true).find(() => true) ?? null)?.value;");
                    wPOST.tl(1, "if(", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " != null && ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " !== '')");
                    if("Boolean".equals(entiteNomSimple)) {
                      wPOST.tl(2, "vals['", entiteVar, "'] = ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " == 'true';");
                    } else {
                      wPOST.tl(2, "vals['", entiteVar, "'] = ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, ";");
                    }
                  }
                  else {
                    wPOST.tl(1, "var ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " = [];");
                    wPOST.tl(1, "$", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelectorAll('input.", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, ":checked').forEach(function(index) {");
                    wPOST.tl(2, i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, ".push(this.value);");
                    wPOST.tl(1, "});");
                    wPOST.tl(1, "if(", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, ".length > 0)");
                    wPOST.tl(2, "vals['", entiteVar, "'] = ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, ";");
                  }
                } else {
                  wPOST.tl(1, "var ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "')?.value;");
                  wPOST.tl(1, "if(", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " != null && ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " !== '')");
                  if("Boolean".equals(entiteNomSimple)) {
                    wPOST.tl(2, "vals['", entiteVar, "'] = ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " == 'true';");
                  } else if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson)) {
                    wPOST.tl(2, "vals['", entiteVar, "'] = JSON.parse(", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, ");");
                  } else {
                    wPOST.tl(2, "vals['", entiteVar, "'] = ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, ";");
                  }
                }
  
                wPUTCopie.l();
                if(entiteAttribuer)
                  wPUTCopie.tl(1, "var ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('input.", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, ":checked')?.value;");
                else
                  wPUTCopie.tl(1, "var ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "')?.value;");
                if(entiteAttribuer) {
                  wPUTCopie.tl(1, "var ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, i18nClasse.getString(I18n.var_Vider), " = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('input.", entiteVar, "_", i18nClasse.getString(I18n.var_vider), ":checked')?.value;");
                  wPUTCopie.tl(1, "if(", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, i18nClasse.getString(I18n.var_Vider), " != null && ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, i18nClasse.getString(I18n.var_Vider), ")");
                  wPUTCopie.tl(2, "vals['", entiteVar, "'] = null;");
                  wPUTCopie.tl(1, "else if(", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " != null && ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, ")");
                  if(entiteListeTypeJson == null) {
                    wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, ";");
                  }
                  else {
                    wPUTCopie.tl(2, "vals['", entiteVar, "'] = [", valPrefixe, i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, "];");
                  }
                } else {
                  wPUTCopie.tl(1, "if(", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " != null && ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " !== '')");
                  if("Boolean".equals(entiteNomSimple)) {
                    wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, " == 'true';");
                  } else if("JsonArray".equals(entiteNomSimpleVertxJson) || "JsonObject".equals(entiteNomSimpleVertxJson)) {
                    wPUTCopie.tl(2, "vals['", entiteVar, "'] = JSON.parse(", valPrefixe, i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, ");");
                  } else {
                    wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, ";");
                  }
                }
  
                wPATCH.l();
                if(entiteAttribuer)
                  wPATCH.tl(1, "var ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " = (Array.from($", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelectorAll('.", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "')).filter(e => e.checked == true).find(() => true) ?? null)?.value;");
                else
                  wPATCH.tl(1, "var ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "')?.value;");
                if(entiteAttribuer) {
                  wPATCH.tl(1, "if(", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " != null && ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " !== '')");
                  if(entiteListeTypeJson == null) {
                    wPATCH.tl(2, "vals['set", entiteVarCapitalise, "'] = ", valPrefixe, i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, ";");
                  } else {
                    wPATCH.tl(2, "vals['add", entiteVarCapitalise, "'] = ", valPrefixe, i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, valSuffixe, ";");
                  }
                } else {

                  wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.remove", entiteVarCapitalise, "')?.value === 'true';");

                  if("Boolean".equals(entiteNomSimple)) {
                    wPATCH.tl(1, "if(", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " != null)");
                    wPATCH.tl(2, i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " = ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " === 'true';");
                    wPATCH.tl(1, "var ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('select.set", entiteVarCapitalise, "')?.value;");
                    wPATCH.tl(1, "if(", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal != null)");
                    wPATCH.tl(2, i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal = ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal === 'true';");
                    wPATCH.tl(1, "if(", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal != null && ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal !== '')");
                    wPATCH.tl(2, i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " = ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, "SelectVal == 'true';");
                    wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : ", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, ";");
                    wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.add", entiteVarCapitalise, "')?.checked;");
                  }
                  else if("LocalDate".equals(entiteNomSimple)) {
                    wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.set", entiteVarCapitalise, "')?.value;");
                    wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.add", entiteVarCapitalise, "')?.value;");
                    wPATCH.tl(1, "var setMoment = set", entiteVarCapitalise, valSuffixe, " ? moment(", valPrefixe, "set", entiteVarCapitalise, valSuffixe, ", '", i18nClasse.getString(I18n.var_DDDashMMDashYYYY), "') : null; ");
                    wPATCH.tl(1, "var addMoment = add", entiteVarCapitalise, valSuffixe, " ? moment(", valPrefixe, "add", entiteVarCapitalise, valSuffixe, ", '", i18nClasse.getString(I18n.var_DDDashMMDashYYYY), "') : null; ");
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
                    wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.set", entiteVarCapitalise, "')?.value;");
                    wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.add", entiteVarCapitalise, "')?.value;");
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
                    wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.remove", entiteVarCapitalise, "')?.checked;");
                  } else {
                    wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.remove", entiteVarCapitalise, "')?.value;");
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

      ecrirePageJs(langueNom, langueNom, i18nClasse, i18nPage);
      ecrirePageJsApiMethodes(langueNom, langueNom, i18nClasse, i18nPage);
      if(!classeLangueNom.equals(langueNom))
        ecrirePageJsApiMethodes(classeLangueNom, langueNom, i18nClasse, i18nPage);

      if(auteurGenPageEditionJinja != null) {
        ecrirePageEditionJinja(langueNom, i18nClasse, i18nPage);
      }

      if(classePageAvecTemplate) {
        //STUFF0
        //STUFF1

        ecrirePageEmplacement(langueNom, i18nClasse, i18nPage);
        ecrirePageBarreLaterale(langueNom, i18nClasse, i18nPage);
        ecrirePageBoutonsRecherche(langueNom, i18nClasse, i18nPage);

        if(auteurMacrosFormulaireRechercheJinja != null)
          auteurMacrosFormulaireRechercheJinja.s(auteurGenPageJinjaEntite);
        ecrirePageFormulaireRecherche(langueNom, langueNom, i18nClasse, i18nPage);
        if(!classeLangueNom.equals(langueNom))
          ecrirePageFormulaireRecherche(classeLangueNom, langueNom, i18nClasse, i18nPage);

        ecrirePageBoutonsPagination(langueNom, i18nClasse, i18nPage);
        ecrirePageRechercheJinja(langueNom, i18nClasse, i18nPage);
        //STUFF1
      }

      wTh.flushClose();

      if(auteurPageCss != null)
        auteurPageCss.flushClose();
      if(auteurPageJs != null)
        auteurPageJs.flushClose();
      if(auteurPageJsRecherche != null)
        auteurPageJsRecherche.flushClose();
      if(auteurPageJsEdition != null)
        auteurPageJsEdition.flushClose();

      if(auteurEmplacementJinja != null) {
        auteurEmplacementJinja.flushClose();
      }

      if(auteurBarreLateraleJinja != null) {
        auteurBarreLateraleJinja.flushClose();
      }

      if(auteurBoutonsRechercheJinja != null) {
        auteurBoutonsRechercheJinja.flushClose();
      }

      if(auteurBoutonsPaginationJinja != null) {
        auteurBoutonsPaginationJinja.flushClose();
      }

      if(auteurMacrosFormulaireRechercheJinja != null) {
        auteurMacrosFormulaireRechercheJinja.flushClose();
      }

      if(auteurBoutonsFormulaireRechercheJinja != null) {
        auteurBoutonsFormulaireRechercheJinja.flushClose();
      }

      if(auteurFormulaireRechercheJinja != null) {
        auteurFormulaireRechercheJinja.flushClose();
      }

      if(auteurRechercheSuggereJinja != null) {
        auteurRechercheSuggereJinja.flushClose();
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

      if(langueNom.equals(classeLangueNom)) {
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
          regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom, i18nClasse);
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
      }
    // }
  }

  public void ecrirePageHeadJinja(String langueNom, JsonObject i18nClasse, JsonObject i18nPage, Boolean edition) throws Exception {
      l();
      tl(0, "{%- block htmStyles", classePageSuperNomSimple, " %}");
      tl(0, "{{ super() }}");
      tl(2, "<link rel=\"stylesheet\" href=\"{{ ", i18nClasse.getString(I18n.var_statiqueUrlBase), " }}", classePageUriCss, "\"/>");
      tl(0, "{%- block htmStyles", classePageNomSimple, " %}");
      tl(0, "{%- endblock htmStyles", classePageNomSimple, " %}");
      tl(0, "{%- endblock htmStyles", classePageSuperNomSimple, " %}");
      l();
      tl(0, "{%- block htmStyle", classePageSuperNomSimple, " %}");
      s(wStyle);
      tl(0, "{{ super() }}");
      tl(0, "{%- block htmStyle", classePageNomSimple, " %}");
      tl(0, "{%- endblock htmStyle", classePageNomSimple, " %}");
      tl(0, "{%- endblock htmStyle", classePageSuperNomSimple, " %}");
      l();
      tl(0, "{%- block htmScripts", classePageSuperNomSimple, " %}");
      tl(0, "{{ super() }}");
      tl(0, "{%- block htmScripts", classePageNomSimple, " %}");
      if(classeApi) {
        tl(2, "<script src=\"{{", i18nClasse.getString(I18n.var_statiqueUrlBase), "}}", classePageUriJs, "\"></script>");
        tl(2, "<script type=\"module\" src=\"{{", i18nClasse.getString(I18n.var_statiqueUrlBase), "}}", classePageUriJsRecherche, "\"></script>");
        if(edition)
          tl(2, "<script type=\"module\" src=\"{{", i18nClasse.getString(I18n.var_statiqueUrlBase), "}}", classePageUriJsEdition, "\"></script>");
        if(classeAttribuerNomSimplePages != null) {
          for(String classeAttribuerPageUriJs : classeAttribuerPageUriJs) {
            t(2).l("<script src=\"{{", i18nClasse.getString(I18n.var_statiqueUrlBase), "}}", classeAttribuerPageUriJs, "\"></script>");
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
        tl(3, "function ", i18nClasse.getString(I18n.var_jsWebsocket), classeNomSimple, "(", classeVarId, ", vars, $response) {");
        tl(0, "{%- block ", i18nClasse.getString(I18n.var_jsWebsocket), classePageNomSimple, " %}{%- endblock %}");
        tl(3, "}");
        l();
        tl(3, "function ", i18nClasse.getString(I18n.var_jsInfobulle), classeNomSimple, "(e, feature) {");
        tl(0, "{%- block ", i18nClasse.getString(I18n.var_jsInfobulle), classePageNomSimple, " %}{%- endblock %}");
        tl(3, "}");
        l();
        tl(3, "function ", i18nClasse.getString(I18n.var_htmInfobulle), classeNomSimple, "(feature, layer) {");
        tl(4, "return `{%- block ", i18nClasse.getString(I18n.var_htmInfobulle), classePageNomSimple, " %}");
        s("<h3>");
        if(i18nClasse.getString(I18n.var_classeIconeClassesCss) != null)
          s("<i class=\"{{ ", i18nClasse.getString(I18n.var_classeIconeClassesCss), " }}  \"></i>");
        s("<a href=\"${quoteattr(feature.properties.", classeVarUrlPageEdition, ")}\">${feature.properties.", classeVarTitre, "}</a>");
        l("</h3>");
        l("{%- endblock ", i18nClasse.getString(I18n.var_htmInfobulle), classePageNomSimple, " %}`;");
        tl(3, "}");

        if(classeVarEmplacement != null || classeVarAire != null) {
          l();
          tl(3, "function ", i18nClasse.getString(I18n.var_jsLegende), classeNomSimple, "(map) {");
          tl(0, "{%- block ", i18nClasse.getString(I18n.var_jsLegende), classePageNomSimple, " %}");
          tl(4, "var div = L.DomUtil.create('div', 'info legend');");
          tl(4, "var htm = '';");
          tl(4, "window.", i18nClasse.getString(I18n.var_liste), classeNomSimple, ".forEach((", varResultat, ", index) => {");
          if(classeVarEmplacement != null) {
            tl(5, "if(", varResultat, ".", classeVarEmplacement, ") {");
            tl(6, "var shapes = [];");
            tl(6, "if(Array.isArray(", varResultat, ".", classeVarEmplacement, "))");
            tl(7, "shapes = shapes.concat(", varResultat, ".", classeVarEmplacement, ");");
            tl(6, "else");
            tl(7, "shapes.push(", varResultat, ".", classeVarEmplacement, ");");
            tl(6, "shapes.forEach(function(shape, index) {");
            tl(7, "htm += ", i18nClasse.getString(I18n.var_htmLegende), classeNomSimple, "(map, shape, ", varResultat, ", index, shapes.length);");
            tl(6, "});");
            tl(5, "}");
          }
          if(classeVarAire != null) {
            tl(5, "if(", varResultat, ".", classeVarAire, ") {");
            tl(6, "var shapes = [];");
            tl(6, "if(Array.isArray(", varResultat, ".", classeVarAire, "))");
            tl(7, "shapes = shapes.concat(", varResultat, ".", classeVarAire, ");");
            tl(6, "else");
            tl(7, "shapes.push(", varResultat, ".", classeVarAire, ");");
            tl(6, "shapes.forEach(function(shape, index) {");
            tl(7, "htm += ", i18nClasse.getString(I18n.var_htmLegende), classeNomSimple, "(map, shape, ", varResultat, ", index, shapes.length);");
            tl(6, "});");
            tl(5, "}");
          }
          tl(4, "});");
          tl(4, "div.innerHTML = htm;");
          tl(4, "return div;");
          tl(0, "{%- endblock ", i18nClasse.getString(I18n.var_jsLegende), classePageNomSimple, " %}");
          tl(3, "}");
          l();
          tl(3, "function ", i18nClasse.getString(I18n.var_htmLegende), classeNomSimple, "(map, shape, ", varResultat, ", index, count) {");
          tl(4, "var color = ", Optional.ofNullable(classeVarEmplacement != null ? classeVarEmplacementCouleur : classeVarAireCouleur).map(v -> varResultat + "." + v + "[index]").orElse("null"), ";");
          tl(4, "var title = ", Optional.ofNullable(classeVarEmplacement != null ? classeVarEmplacementTitre : classeVarAireTitre).map(v -> varResultat + "." + v + "[index]").orElse("null"), ";");
          tl(4, "var url = ", Optional.ofNullable(classeVarEmplacement != null ? classeVarEmplacementUrl : classeVarAireUrl).map(v -> varResultat + "." + v + "[index]").orElse("null"), ";");
          tl(4, "var htm = '';");
          tl(4, "htm += `<div class=\"cursor-pointer \" style=\"max-width: 400px; \">");
          tl(0, "`;");
          t(4, "htm += `");
          l("{%- block ", i18nClasse.getString(I18n.var_htmLegende), classePageNomSimple, " %}");
          tl(2, "<div title=\"${quoteattr(title)}\">");
          tl(2, "<div style=\"width: 20px; \">");
          tl(3, "<i class=\"{{ FONTAWESOME_STYLE }} fa-circle\" style=\"color: ${color}; \"></i>");
          tl(2, "</div>");
          tl(2, "<div class=\"text-overflow-ellipsis \">");
          tl(3, "<span class=\"\" data-", classeVarCleUnique, "=\"${", varResultat, ".", classeVarCleUnique, "}\" onclick=\"window.mapLayers[this.getAttribute('data-", classeVarCleUnique, "')].openPopup(); return false;\" href=\"\">${title}</span>");
          tl(2, "</div>");
          tl(2, "</div>");
          l("{%- endblock ", i18nClasse.getString(I18n.var_htmLegende), classePageNomSimple, " %}`;");
          tl(4, "htm += `");
          tl(0, "</div>");
          tl(0, "`;");
          tl(4, "return htm;");
          tl(3, "}");
          l();
          tl(3, "function ", i18nClasse.getString(I18n.var_jsStyle), classeNomSimple, "(feature) {");
          tl(0, "{%- block ", i18nClasse.getString(I18n.var_jsStyle), classePageNomSimple, " %}");
          tl(4, "if(feature.geometry.type == 'Point') {");
          tl(5, "return {");
          tl(6, "radius: 8");
          tl(6, ", fillColor: (feature.properties.", classeVarEmplacementCouleur, " && feature.properties.", classeVarEmplacementCouleur, "[feature.index] ? feature.properties.", classeVarEmplacementCouleur, "[feature.index] : (feature.properties.color ? feature.properties.color : '#000'))");
          tl(6, ", color: (feature.properties.", classeVarEmplacementCouleur, " && feature.properties.", classeVarEmplacementCouleur, "[feature.index] ? feature.properties.", classeVarEmplacementCouleur, "[feature.index] : (feature.properties.color ? feature.properties.color : '#000'))");
          tl(6, ", weight: 1");
          tl(6, ", opacity: 0.7");
          tl(6, ", fillOpacity: 0.7");

          tl(6, ", contextmenu: true");
          tl(6, ", contextmenuItems: [");
          tl(7, "{");
          tl(8, "text: 'Show coordinates'");
          tl(8, ", callback: function(event) {");
          tl(9, "alert(event2.target.getLatLngs());");
          tl(8, "}");
          tl(7, "}");
          tl(6, "]");

          tl(5, "};");
          tl(4, "} else if(feature.geometry.type == 'LineString') {");
          tl(5, "return {");
          tl(6, "color: (feature.properties.", classeVarEmplacementCouleur, " && feature.properties.", classeVarEmplacementCouleur, "[feature.index] ? feature.properties.", classeVarEmplacementCouleur, "[feature.index] : (feature.properties.color ? feature.properties.color : '#000'))");
          tl(6, ", weight: 5");
          tl(6, ", opacity: 0.7");
          tl(5, "};");
          tl(4, "} else {");
          tl(5, "return {");
          tl(6, " fillColor: (feature.properties.", classeVarEmplacementCouleur, " && feature.properties.", classeVarEmplacementCouleur, "[feature.index] ? feature.properties.", classeVarEmplacementCouleur, "[feature.index] : (feature.properties.color ? feature.properties.color : '#000'))");
          tl(6, ", color: (feature.properties.", classeVarEmplacementCouleur, " && feature.properties.", classeVarEmplacementCouleur, "[feature.index] ? feature.properties.", classeVarEmplacementCouleur, "[feature.index] : (feature.properties.color ? feature.properties.color : '#000'))");
          tl(6, ", weight: 3");
          tl(6, ", opacity: 0.7");
          tl(6, ", fillOpacity: 0.7");
          tl(5, "};");
          tl(4, "}");
          tl(0, "{%- endblock ", i18nClasse.getString(I18n.var_jsStyle), classePageNomSimple, " %}");
          tl(3, "}");
        }
        l();
        tl(3, "function facetChange", classeNomSimple, "Success(e) {");
        tl(4, "var l = e.querySelector('.", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Formulaire), " .", i18nClasse.getString(I18n.var_liste), classeNomSimple, "')?.value;");
        tl(4, "if(l)");
        tl(5, "window.", i18nClasse.getString(I18n.var_liste), classeNomSimple, " = JSON.parse(l);");
        tl(0, "{% block facetChange", classeNomSimple, "Success %}{%- endblock facet", classeNomSimple, "Success %}");
        tl(3, "}");
        tl(3, "function facetChange", classeNomSimple, "Error(e) {");
        tl(0, "{% block facetChange", classeNomSimple, "Error %}{%- endblock facet", classeNomSimple, "Error %}");
        tl(3, "}");
        l();
        tl(3, "function facet", i18nClasse.getString(I18n.var_GammeSimple), classeNomSimple, "Success(e) {");
        tl(0, "{% block facet", i18nClasse.getString(I18n.var_GammeSimple), classeNomSimple, "Success %}      facetChange", classeNomSimple, "Success(e);{% endblock facet", i18nClasse.getString(I18n.var_GammeSimple), classeNomSimple, "Success %}");
        tl(3, "}");
        tl(3, "function facet", i18nClasse.getString(I18n.var_GammeSimple), classeNomSimple, "Error(e) {");
        tl(0, "{% block facet", i18nClasse.getString(I18n.var_GammeSimple), classeNomSimple, "Error %}      facetChange", classeNomSimple, "Error(e);{% endblock facet", i18nClasse.getString(I18n.var_GammeSimple), classeNomSimple, "Error %}");
        tl(3, "}");
        tl(3, "function facet", i18nClasse.getString(I18n.var_GammeSimple), classeNomSimple, "(e) {");
        tl(4, "facet", i18nClasse.getString(I18n.var_GammeSimple), "(e, facet", i18nClasse.getString(I18n.var_GammeSimple), classeNomSimple, "Success, facet", i18nClasse.getString(I18n.var_GammeSimple), classeNomSimple, "Error);");
        tl(3, "}");
        l();
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, "Success(e) {");
        tl(0, "{% block facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, "Success %}      facetChange", classeNomSimple, "Success(e);{% endblock facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, "Success %}");
        tl(3, "}");
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, "Error(e) {");
        tl(0, "{% block facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, "Error %}      facetChange", classeNomSimple, "Error(e);{%- endblock facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, "Error %}");
        tl(3, "}");
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, "(e) {");
        tl(4, "facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone(e, facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, "Success, facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, "Error);");
        tl(3, "}");
        l();
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "GapChange", classeNomSimple, "Success(e) {");
        tl(0, "{% block facet", i18nClasse.getString(I18n.var_Gamme), "GapChange", classeNomSimple, "Success %}      facetChange", classeNomSimple, "Success(e);{% endblock facet", i18nClasse.getString(I18n.var_Gamme), "GapChange", classeNomSimple, "Success %}");
        tl(3, "}");
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "GapChange", classeNomSimple, "Error(e) {");
        tl(0, "{% block facet", i18nClasse.getString(I18n.var_Gamme), "GapChange", classeNomSimple, "Error %}      facetChange", classeNomSimple, "Error(e);{%- endblock facet", i18nClasse.getString(I18n.var_Gamme), "GapChange", classeNomSimple, "Error %}");
        tl(3, "}");
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "GapChange", classeNomSimple, "(", i18nClasse.getString(I18n.var_classeNomSimple), ", event) {");
        tl(4, "facet", i18nClasse.getString(I18n.var_Gamme), "GapChange(", i18nClasse.getString(I18n.var_classeNomSimple), ", event, facet", i18nClasse.getString(I18n.var_Gamme), "GapChange", classeNomSimple, "Success, facet", i18nClasse.getString(I18n.var_Gamme), "GapChange", classeNomSimple, "Error);");
        tl(3, "}");
        l();
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "StartChange", classeNomSimple, "Success(e) {");
        tl(0, "{% block facet", i18nClasse.getString(I18n.var_Gamme), "StartChange", classeNomSimple, "Success %}      facetChange", classeNomSimple, "Success(e);{% endblock facet", i18nClasse.getString(I18n.var_Gamme), "StartChange", classeNomSimple, "Success %}");
        tl(3, "}");
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "StartChange", classeNomSimple, "Error(e) {");
        tl(0, "{% block facet", i18nClasse.getString(I18n.var_Gamme), "StartChange", classeNomSimple, "Error %}      facetChange", classeNomSimple, "Error(e);{%- endblock facet", i18nClasse.getString(I18n.var_Gamme), "StartChange", classeNomSimple, "Error %}");
        tl(3, "}");
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "StartChange", classeNomSimple, "(", i18nClasse.getString(I18n.var_classeNomSimple), ", event) {");
        tl(4, "facet", i18nClasse.getString(I18n.var_Gamme), "StartChange(", i18nClasse.getString(I18n.var_classeNomSimple), ", event, facet", i18nClasse.getString(I18n.var_Gamme), "StartChange", classeNomSimple, "Success, facet", i18nClasse.getString(I18n.var_Gamme), "StartChange", classeNomSimple, "Error);");
        tl(3, "}");
        l();
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "EndChange", classeNomSimple, "Success(e) {");
        tl(0, "{% block facet", i18nClasse.getString(I18n.var_Gamme), "EndChange", classeNomSimple, "Success %}      facetChange", classeNomSimple, "Success(e);{% endblock facet", i18nClasse.getString(I18n.var_Gamme), "EndChange", classeNomSimple, "Success %}");
        tl(3, "}");
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "EndChange", classeNomSimple, "Error(e) {");
        tl(0, "{% block facet", i18nClasse.getString(I18n.var_Gamme), "EndChange", classeNomSimple, "Error %}      facetChange", classeNomSimple, "Error(e);{%- endblock facet", i18nClasse.getString(I18n.var_Gamme), "EndChange", classeNomSimple, "Error %}");
        tl(3, "}");
        tl(3, "function facet", i18nClasse.getString(I18n.var_Gamme), "EndChange", classeNomSimple, "(", i18nClasse.getString(I18n.var_classeNomSimple), ", event) {");
        tl(4, "facet", i18nClasse.getString(I18n.var_Gamme), "EndChange(", i18nClasse.getString(I18n.var_classeNomSimple), ", event, facet", i18nClasse.getString(I18n.var_Gamme), "EndChange", classeNomSimple, "Success, facet", i18nClasse.getString(I18n.var_Gamme), "EndChange", classeNomSimple, "Error);");
        tl(3, "}");
        tl(2, "</script>");
        tl(2, "<script type=\"module\">");

        // tl(3, "document.querySelector('#site-calendar-box').accordion({ collapsible: true, active: false });");
        l("{% if DEFAULT_MAP_LOCATION is defined %}");
        tl(3, "window.DEFAULT_MAP_LOCATION = { type: 'Point', coordinates: [ {{ DEFAULT_MAP_LOCATION.coordinates[0] }}, {{ DEFAULT_MAP_LOCATION.coordinates[1] }} ]};");
        l("{% endif %}");
        l("{% if DEFAULT_MAP_ZOOM is defined %}");
        tl(3, "window.DEFAULT_MAP_ZOOM = {{ DEFAULT_MAP_ZOOM }};");
        l("{% endif %}");
        tl(3, "window.DEFAULT_ZONE_ID = '{{ defaultZoneId }}';");
        tl(3, "Promise.all([");
        tl(4, "customElements.whenDefined('", composantsWebPrefixe, "button')");
        tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "input')");
        tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "select')");
        tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "radio')");
        tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "checkbox')");
        tl(4, ", customElements.whenDefined('", composantsWebPrefixe, "dropdown')");
        tl(3, "]).then(() => {");
        if(classeVarId != null) {
          l();
          tl(4, "var ", classeVarId, " = {% if ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " is defined %}{{ ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " | tojson }}{% else %}null{% endif %};");
          l();
          if(!edition) {
            s(wJsHtmInit);
          }
          tl(4, "if(", classeVarId, " == null) {");
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
          tl(5, "var o = document.querySelector('.", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Formulaire), " .", varResultat, "')?.value;");
          tl(5, "if(o)");
          tl(6, "window.", varResultat, " = JSON.parse(o);");
          tl(4, "{% if ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " is defined %}");
          if(edition) {
            s(wJsHtmEditionInit);
          }
          tl(4, "{% endif %}");
          tl(4, "}");
        }
        tl(4, "var l = document.querySelector('.", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Formulaire), " .", i18nClasse.getString(I18n.var_liste), classeNomSimple, "')?.value;");
        tl(4, "if(l)");
        tl(5, "window.", i18nClasse.getString(I18n.var_liste), classeNomSimple, " = JSON.parse(l);");
        tl(4, "window.varsFq = JSON.parse('{{ toJsonObjectStringInApostrophes(varsFq) }}');");
        tl(4, "window.varsRange = JSON.parse('{{ toJsonObjectStringInApostrophes(varsRange) }}');");
        tl(4, "window.defaultRangeVar = '{{ defaultRangeVar }}';");
        tl(4, "document.querySelector('#siteSidebarToggleRange')?.addEventListener('wa-after-hide', function(e) { document.querySelector('#pageFacetRangeTimeZonePopup').active = false; });");
        tl(4, "document.querySelector('#pageFacetRangeTimeZoneInput')?.addEventListener('input', facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, ");");
        tl(4, "document.querySelector('#pageFacetRangeTimeZoneInput')?.addEventListener('input', facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, ");");
        tl(4, "document.querySelector('#pageFacetRangeTimeZoneInput')?.addEventListener('focus', facet", i18nClasse.getString(I18n.var_Gamme), "TimeZone", classeNomSimple, ");");
        tl(4, "document.querySelector('#pageFacetSimpleRangeDropdown')?.addEventListener('wa-select', facet", i18nClasse.getString(I18n.var_GammeSimple), classeNomSimple, ");");
        tl(4, i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Graphique), classeNomSimple, "();");
        tl(4, "{% for key, value in varsQ.items() %}");
        l();
        tl(4, "document.querySelector('#q", classeNomSimple, "_{{ key }}')?.addEventListener('", composantsWebPrefixe, "{% if var == '", classeVarSuggere, "' %}keyup{% else %}", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change{% endif %}', event => {");
        tl(5, "qChange('", classeNomSimple, "', event.target, document.querySelector('#q", classeNomSimple, "Div_{{ key }}'));");
        tl(4, "});");
        tl(4, "{% endfor %}");

        l();
        tl(4, "document.querySelector('#q", classeNomSimple, "_", i18nClasse.getString(I18n.var_lignes), "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change', event => {");
        tl(5, "paramChange('", classeNomSimple, "', event.target, document.querySelector('#q", classeNomSimple, "Div_", i18nClasse.getString(I18n.var_lignes), "'));");
        tl(4, "});");

        l();
        tl(4, "document.querySelector('#q", classeNomSimple, "_", i18nClasse.getString(I18n.var_debut), "')?.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change', event => {");
        tl(5, "paramChange('", classeNomSimple, "', event.target, document.querySelector('#q", classeNomSimple, "Div_", i18nClasse.getString(I18n.var_debut), "'));");

        tl(4, "});");

        l();
        // tl(4, "var calendarEl = document.getElementById('site-calendar');");
        // tl(4, "var calendar = new FullCalendar.Calendar(calendarEl, {");
        // tl(5, "initialView: 'dayGridMonth'");
        // tl(4, "});");
        // tl(4, "//calendar.render();");
        for(String langueNomApi : toutesLangues) {
          List<String> classeApiMethodes = Optional.ofNullable(classeDoc.getJsonArray("classeApiMethodes_" + langueNomApi + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
          if(classeApiMethodes == null)
            classeApiMethodes = new ArrayList<>();
          for(String classeApiMethode : classeApiMethodes) {
            String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNomApi + "_stored_string");
            String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNomApi + "_stored_string");

            if(classeApiMethode.contains(i18nClasse.getString(I18n.var_PageRecherche)) || classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals("DELETE") || classeApiMethode.equals(i18nClasse.getString(I18n.var_DELETEFiltre)) || classeApiMethode.equals(i18nClasse.getString(I18n.var_PUTCopie)) || classeApiMethode.equals(i18nClasse.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {

              l();
              tl(4, "var submit", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, " = document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "')?.addEventListener('submit', event => {");
              tl(5, "event.preventDefault();");
              tl(5, "return false;");
              tl(4, "});");

              tl(4, "document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "')?.addEventListener('click', event => {");
              tl(5, "event.preventDefault();");
              if("POST".equals(classeApiMethodeMethode)) {
                tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "')");
                tl(5, ", document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "')");
                tl(5, ", (json, target) => {");
                tl(6, "window.location.href = json.", classeVarUrlPageEdition, ";");
                tl(5, "});");
              }
              else if("PATCH".equals(classeApiMethode))
                tl(5, classeApiOperationIdMethode, "(null, document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'), event.target.getAttribute('data-", classeVarId, "'));");
              else if("DELETE".equals(classeApiMethode))
                tl(5, classeApiOperationIdMethode, "(event.target, event.target.getAttribute('data-", classeVarId, "'));");
              else if(classeApiMethode.equals(i18nClasse.getString(I18n.var_DELETEFiltre)))
                tl(5, classeApiOperationIdMethode, "(event.target);");
              else if("PUTImport".equals(classeApiMethode))
                tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'));");
              else if(i18nClasse.getString(I18n.var_PUTFusion).equals(classeApiMethode))
                tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), ", classeModele ? "{{ " + classeVarClePrimaire + " }}" : "'{{ " + classeVarCleUnique + " }}', document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'));");
              else if(i18nClasse.getString(I18n.var_PUTCopie).equals(classeApiMethode))
                tl(5, classeApiOperationIdMethode, "(document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "'), ", classeModele ? "{{ " + classeVarClePrimaire + " }}" : "'{{ " + classeVarCleUnique + " }}', document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "'));");
              else
                tl(5, classeApiOperationIdMethode, "();");
              tl(5, "return false;");
              tl(4, "});");
            }
          }
        }
        for(String classeAttribuerNomSimple : classeAttribuerNomSimples) {
          l();
          // DO STUFF
          tl(4, "document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), i18nClasse.getString(I18n.var_Bouton), "_post", classeAttribuerNomSimple, "')?.addEventListener('click', event => {");
          tl(5, "event.preventDefault();");
          tl(5, "post", classeAttribuerNomSimple, "(document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), "_post", classeAttribuerNomSimple, "')");
          tl(7, ", document.querySelector('#htm", i18nClasse.getString(I18n.var_Formulaire), i18nClasse.getString(I18n.var_Bouton), "_post", classeAttribuerNomSimple, "')");
          tl(7, ", (json, target) => {");
          tl(8, i18nClasse.getString(I18n.var_ajouterLueur), "(target);");
          tl(7, "});");
          tl(4, "});");
        }
        tl(0, "{%- block htmScriptInit", classePageNomSimple, " %}{%- endblock htmScriptInit", classePageNomSimple, " %}");
        tl(3, "});");
        tl(2, "</script>");
        tl(0, "{%- endblock htmScript", classePageNomSimple, " %}");
        tl(0, "{%- endblock htmScript", classePageSuperNomSimple, " %}");

        l();
        tl(0, "{%- block websocket", classePageSuperNomSimple, " %}");
        tl(0, "{%- block websocket", classePageNomSimple, " %}");
        l("{% if ", varResultat, "Count > 0 %}");
        tl(4, "window.eventBus = new EventBus('/eventbus');");
        tl(4, "window.eventBus.enableReconnect(true);");
        tl(4, "websocket", classeApiClasseNomSimple, "(websocket", classeApiClasseNomSimple, "Inner);");
        l("{% endif %}");
        tl(0, "{%- endblock websocket", classePageNomSimple, " %}");
        tl(0, "{%- endblock websocket", classePageSuperNomSimple, " %}");
      }
      l();
      t(0, "{%- block htmUrl", classePageNomSimple, " %}");
      s("{{pageUri}}");
      s("?q={{query.q}}");
      s("&amp;rows={% if rows is defined %}{{rows}}{% else %}{{pagination.", i18nClasse.getString(I18n.var_lignes), "}}{% endif %}");
      s("&amp;rows={% if start is defined %}{{start}}{% else %}{{pagination.", i18nClasse.getString(I18n.var_debut), "}}{% endif %}");
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

  public void ecrirePageJsApiMethodes(String langueNomApi, String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {
    List<String> classeApiMethodes = Optional.ofNullable(classeDoc.getJsonArray("classeApiMethodes_" + langueNomApi + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    if(auteurPageJs != null) {
      if(classeApiMethodes == null)
        classeApiMethodes = new ArrayList<>();
      for(String classeApiMethode : classeApiMethodes) {
        String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNomApi + "_stored_string");
        String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNomApi + "_stored_string");
        String classeApiTypeMediaRequete = classeDoc.getString("classeApiTypeMediaRequete" + classeApiMethode + "_" + langueNomApi + "_stored_string");
        String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNomApi + "_stored_string");
        String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNomApi + "_stored_string");
        List<String> classeTrisVar = Optional.ofNullable(doc.getJsonArray("classeTrisVar_" + langueNomApi + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
  
        if("application/json".equals(classeApiTypeMediaMethode)) {
          Boolean methodePOST = classeApiMethodeMethode.equals("POST");
          Boolean methodeGET = classeApiMethode.contains("GET");
          Boolean methodePUTImport = classeApiMethode.equals("PUTImport");
          Boolean methodePUTFusion = classeApiMethode.equals(i18nClasse.getString(I18n.var_PUTFusion));
          Boolean methodePUTCopie = classeApiMethode.equals(i18nClasse.getString(I18n.var_PUTCopie));
          Boolean methodePATCH = classeApiMethodeMethode.equals("PATCH");
          Boolean methodeDELETE = classeApiMethode.equals("DELETE");
          Boolean methodeDELETEFiltre = classeApiMethode.equals(i18nClasse.getString(I18n.var_DELETEFiltre));
          Boolean methodeRecherche = classeApiMethode.contains(i18nClasse.getString(I18n.var_Recherche));
          auteurPageJs.l();
          auteurPageJs.tl(0, "// ", classeApiMethode, " //");
          auteurPageJs.l();
          auteurPageJs.t(0, "async function ", classeApiOperationIdMethode, "(");
          if(methodePOST) {
            auteurPageJs.s("$", i18nClasse.getString(I18n.var_formulaireValeurs));
            auteurPageJs.s(", target");
            auteurPageJs.s(", success");
            auteurPageJs.s(", error");
          } else if(methodeDELETE) {
            auteurPageJs.s("target");
            auteurPageJs.s(", ", classeVarId);
            auteurPageJs.s(", success");
            auteurPageJs.s(", error");
          } else if(methodeDELETEFiltre) {
            auteurPageJs.s("target");
            auteurPageJs.s(", success");
            auteurPageJs.s(", error");
          } else if(methodePUTImport) {
            auteurPageJs.s("$", i18nClasse.getString(I18n.var_formulaireValeurs));
            auteurPageJs.s(", target");
            auteurPageJs.s(", ", classeVarId);
            auteurPageJs.s(", success");
            auteurPageJs.s(", error");
          } else if(methodePUTFusion) {
            auteurPageJs.s("$", i18nClasse.getString(I18n.var_formulaireValeurs));
            auteurPageJs.s(", target");
            auteurPageJs.s(", ", classeVarId);
            auteurPageJs.s(", success");
            auteurPageJs.s(", error");
          } else if(methodePUTCopie) {
            auteurPageJs.s("$", i18nClasse.getString(I18n.var_formulaireValeurs));
            auteurPageJs.s(", target");
            auteurPageJs.s(", ", classeVarId);
            auteurPageJs.s(", success");
            auteurPageJs.s(", error");
          } else if(methodePATCH) {
            auteurPageJs.s("$", i18nClasse.getString(I18n.var_formulaireFiltres), ", $", i18nClasse.getString(I18n.var_formulaireValeurs));
            auteurPageJs.s(", target");
            auteurPageJs.s(", ", classeVarId);
            auteurPageJs.s(", success");
            auteurPageJs.s(", error");
          } else if(methodeRecherche) {
            auteurPageJs.s("$", i18nClasse.getString(I18n.var_formulaireFiltres), "");
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
            auteurPageJs.tl(3, i18nClasse.getString(I18n.var_ajouterLueur), "(target, jqXhr);");
            if(classeVarUrlPageEdition != null) {
              auteurPageJs.tl(3, "var url = data['", classeVarUrlPageEdition, "'];");
              auteurPageJs.tl(3, "if(url)");
              auteurPageJs.tl(4, "window.location.href = url;");
            }
            auteurPageJs.tl(2, "};");
            auteurPageJs.tl(1, "}");
            auteurPageJs.tl(1, "if(error == null) {");
            auteurPageJs.tl(2, "error = function( jqXhr, target2 ) {");
            auteurPageJs.tl(3, "if(jqXhr.status === 400) {");
            auteurPageJs.tl(4, "jqXhr.json().then((json) => {");
            auteurPageJs.tl(5, "if(json?.error?.message === 'Inactive Token') {");
            auteurPageJs.tl(6, "fetch('/refresh').then(refreshResponse => {");
            auteurPageJs.tl(7, "if(refreshResponse.ok) {");
            auteurPageJs.tl(8, i18nClasse.getString(I18n.var_ajouterErreur), "Json(target, jqXhr);");
            auteurPageJs.tl(7, "} else {");
            auteurPageJs.tl(8, i18nClasse.getString(I18n.var_ajouterErreur), "Json(target, jqXhr);");
            auteurPageJs.tl(7, "}");
            auteurPageJs.tl(6, "});");
            auteurPageJs.tl(5, "} else {");
            auteurPageJs.tl(6, i18nClasse.getString(I18n.var_ajouterErreur), "(target, jqXhr);");
            auteurPageJs.tl(5, "}");
            auteurPageJs.tl(4, "});");
            auteurPageJs.tl(3, "} else {");
            auteurPageJs.tl(4, i18nClasse.getString(I18n.var_ajouterErreur), "(target, jqXhr);");
            auteurPageJs.tl(3, "}");
            auteurPageJs.tl(2, "};");
            auteurPageJs.tl(1, "}");
            auteurPageJs.s(wPOST);
            auteurPageJs.l();
          } else if(methodeDELETE || methodeDELETEFiltre) {
            auteurPageJs.tl(1, "if(success == null) {");
            auteurPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {");
            auteurPageJs.tl(3, i18nClasse.getString(I18n.var_ajouterLueur), "(target, jqXhr);");
            if(classeVarUrlPageEdition != null) {
              auteurPageJs.tl(3, "var url = data['", classeVarUrlPageEdition, "'];");
              auteurPageJs.tl(3, "if(url)");
              auteurPageJs.tl(4, "window.location.href = url;");
            }
            auteurPageJs.tl(2, "};");
            auteurPageJs.tl(1, "}");
            auteurPageJs.tl(1, "if(error == null) {");
            auteurPageJs.tl(2, "error = function( jqXhr, target2 ) {");
            auteurPageJs.tl(3, "if(jqXhr.status === 400) {");
            auteurPageJs.tl(4, "jqXhr.json().then((json) => {");
            auteurPageJs.tl(5, "if(json?.error?.message === 'Inactive Token') {");
            auteurPageJs.tl(6, "fetch('/refresh').then(refreshResponse => {");
            auteurPageJs.tl(7, "if(refreshResponse.ok) {");
            auteurPageJs.tl(8, i18nClasse.getString(I18n.var_ajouterErreur), "Json(target, jqXhr);");
            auteurPageJs.tl(7, "} else {");
            auteurPageJs.tl(8, i18nClasse.getString(I18n.var_ajouterErreur), "Json(target, jqXhr);");
            auteurPageJs.tl(7, "}");
            auteurPageJs.tl(6, "});");
            auteurPageJs.tl(5, "} else {");
            auteurPageJs.tl(6, i18nClasse.getString(I18n.var_ajouterErreur), "(target, jqXhr);");
            auteurPageJs.tl(5, "}");
            auteurPageJs.tl(4, "});");
            auteurPageJs.tl(3, "} else {");
            auteurPageJs.tl(4, i18nClasse.getString(I18n.var_ajouterErreur), "(target, jqXhr);");
            auteurPageJs.tl(3, "}");
            auteurPageJs.tl(2, "};");
            auteurPageJs.tl(1, "}");
            auteurPageJs.l();
          } else if(methodePUTCopie) {
            auteurPageJs.tl(1, "var vals = {};");
            auteurPageJs.tl(1, "if(success == null) {");
            auteurPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {");
            auteurPageJs.tl(3, i18nClasse.getString(I18n.var_ajouterLueur), "(target, jqXhr);");
            if(classeVarUrlPageEdition != null) {
              auteurPageJs.tl(3, "var url = data['", classeVarUrlPageEdition, "'];");
              auteurPageJs.tl(3, "if(url)");
              auteurPageJs.tl(4, "window.location.href = url;");
            }
            auteurPageJs.tl(2, "};");
            auteurPageJs.tl(1, "}");
            auteurPageJs.tl(1, "if(error == null) {");
            auteurPageJs.tl(2, "error = function( jqXhr, target2 ) {");
            auteurPageJs.tl(3, "if(jqXhr.status === 400) {");
            auteurPageJs.tl(4, "jqXhr.json().then((json) => {");
            auteurPageJs.tl(5, "if(json?.error?.message === 'Inactive Token') {");
            auteurPageJs.tl(6, "fetch('/refresh').then(refreshResponse => {");
            auteurPageJs.tl(7, "if(refreshResponse.ok) {");
            auteurPageJs.tl(8, i18nClasse.getString(I18n.var_ajouterErreur), "Json(target, jqXhr);");
            auteurPageJs.tl(7, "} else {");
            auteurPageJs.tl(8, i18nClasse.getString(I18n.var_ajouterErreur), "Json(target, jqXhr);");
            auteurPageJs.tl(7, "}");
            auteurPageJs.tl(6, "});");
            auteurPageJs.tl(5, "} else {");
            auteurPageJs.tl(6, i18nClasse.getString(I18n.var_ajouterErreur), "(target, jqXhr);");
            auteurPageJs.tl(5, "}");
            auteurPageJs.tl(4, "});");
            auteurPageJs.tl(3, "} else {");
            auteurPageJs.tl(4, i18nClasse.getString(I18n.var_ajouterErreur), "(target, jqXhr);");
            auteurPageJs.tl(3, "}");
            auteurPageJs.tl(2, "};");
            auteurPageJs.tl(1, "}");
            auteurPageJs.s(wPUTCopie);
            auteurPageJs.l();
          } else if(methodePUTImport) {
            auteurPageJs.tl(1, "var json = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.", "PUTImport", "_", i18nClasse.getString(I18n.var_listeRecherche), "')?.value;");
            auteurPageJs.tl(1, "if(json != null && json !== '')");
            if(StringUtils.equals("application/json", classeApiTypeMediaRequete))
              auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(JSON.parse(json), target, success, error);");
            else
              auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(json, target, success, error);");
          } else if(methodePUTFusion) {
            auteurPageJs.tl(1, "var json = $", i18nClasse.getString(I18n.var_formulaireValeurs), ".querySelector('.", i18nClasse.getString(I18n.var_PUTFusion), "_", i18nClasse.getString(I18n.var_listeRecherche), "')?.value;");
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
            auteurPageJs.tl(1, "var ", i18nClasse.getString(I18n.var_filtres), " = ", classeApiOperationIdMethode,i18nClasse.getString(I18n.var_Filtres), "($", i18nClasse.getString(I18n.var_formulaireFiltres), ");");
            auteurPageJs.l();
            auteurPageJs.tl(1, "var vals = {};");
            auteurPageJs.s(wPATCH);
            auteurPageJs.l();
          } else if(methodeRecherche) {
            auteurPageJs.tl(1, "var ", i18nClasse.getString(I18n.var_filtres), " = ", classeApiOperationIdMethode,i18nClasse.getString(I18n.var_Filtres), "($", i18nClasse.getString(I18n.var_formulaireFiltres), ");");
            auteurPageJs.tl(1, "if(success == null)");
            auteurPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {};");
            auteurPageJs.tl(1, "if(error == null)");
            auteurPageJs.tl(2, "error = function( jqXhr, target2 ) {};");
            auteurPageJs.l();
          }
  
          if(methodePATCH) {
            if(classeJsPATCH != null) {
              auteurPageJs.l(classeJsPATCH);
              auteurPageJs.l();
            }
            auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", classeVarId, " == null ? deparam(window.location.search ? window.location.search.substring(1) : window.location.search) : [{name:'fq', value:'", classeVarId, ":' + ", classeVarId, "}], vals, target, success, error);");
          }
          else if(methodePUTImport) {
          }
          else if(methodePUTFusion) {
          }
          else if(methodePUTCopie) {
            auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", classeVarId, " == null ? deparam(window.location.search ? window.location.search.substring(1) : window.location.search) : [{name:'fq', value:'", classeVarId, ":' + ", classeVarId, "}], vals, target, success, error);");
          }
          else if(methodeRecherche) {
            auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", i18nClasse.getString(I18n.var_filtres), ", target, success, error);");
          }
          else {
            auteurPageJs.tl(1, "fetch(");
    
            if(methodeGET || methodePUTCopie)
              auteurPageJs.tl(2, "'", StringUtils.replace(classeApiUriMethode + "'", "{" + classeVarId + "}'", "' + " + classeVarId));
            else if(methodePATCH || methodeRecherche)
              auteurPageJs.tl(2, "'", classeApiUriMethode, "?' + ", i18nClasse.getString(I18n.var_filtres), ".map(function(m) { return m.name + '=' + encodeURIComponent(m.value) }).join('&')");
            else if(methodeDELETEFiltre)
              auteurPageJs.tl(2, "'", classeApiUri, "'");
            else if(methodeDELETE)
              auteurPageJs.tl(2, "'", classeApiUri, "/' + encodeURIComponent(", classeVarId, ")");
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
            auteurPageJs.tl(2, "}).then(", i18nClasse.getString(I18n.var_reponse), " => {");
            auteurPageJs.tl(3, "if(", i18nClasse.getString(I18n.var_reponse), ".ok) {");
            if("DELETE".equals(classeApiMethodeMethode)) {
              auteurPageJs.tl(4, "success(response, target);");
            } else {
              auteurPageJs.tl(4, i18nClasse.getString(I18n.var_reponse), ".json().then((json) => {");
              auteurPageJs.tl(5, "success(json, target);");
              auteurPageJs.tl(4, "})");
            }
            auteurPageJs.tl(3, "} else {");
            auteurPageJs.tl(4, "error(", i18nClasse.getString(I18n.var_reponse), ", target);");
            auteurPageJs.tl(3, "}");
            auteurPageJs.tl(2, "})");
            auteurPageJs.tl(2, ".catch(", i18nClasse.getString(I18n.var_reponse), " => error(", i18nClasse.getString(I18n.var_reponse), ", target));");
          }
          auteurPageJs.l("}");

          if(methodePATCH || methodeRecherche) {
//		  			auteurPageJs.l();
//		  			auteurPageJs.tl(0, "function varsFq", classeNomSimple, "() {");
//		  			auteurPageJs.tl(1, "var vars = [];");
//		  			auteurPageJs.s(wVarsFqJs);
//		  			auteurPageJs.tl(1, "return vars;");
//		  			auteurPageJs.tl(0, "}");

            auteurPageJs.l();
            auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, i18nClasse.getString(I18n.var_Filtres), "($", i18nClasse.getString(I18n.var_formulaireFiltres), ") {");
            auteurPageJs.tl(1, "var ", i18nClasse.getString(I18n.var_filtres), " = [];");
            auteurPageJs.tl(1, "if($", i18nClasse.getString(I18n.var_formulaireFiltres), ") {");
            if(methodePATCH)
              auteurPageJs.tl(2, i18nClasse.getString(I18n.var_filtres), ".push({ name: 'softCommit', value: 'true' });");
            auteurPageJs.s(wRecherche);
            auteurPageJs.tl(1, "}");
            auteurPageJs.tl(1, "return ", i18nClasse.getString(I18n.var_filtres), ";");
            auteurPageJs.tl(0, "}");
          }
          if(methodePATCH) {
            auteurPageJs.l();
            auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Val(", i18nClasse.getString(I18n.var_filtres), ", v, val, target, success, error) {");
            auteurPageJs.tl(1, "var vals = {};");
            auteurPageJs.tl(1, "vals[v] = val;");
            auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", i18nClasse.getString(I18n.var_filtres), ", vals, target, success, error);");
            auteurPageJs.l("}"); 
          }
          if(methodeRecherche) {
            auteurPageJs.l();
            auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(", i18nClasse.getString(I18n.var_filtres), ", target, success, error) {");
            if(classeLignes != null) {
              auteurPageJs.l();
//		  						auteurPageJs.tl(1, langueConfig.getString(ConfigCles.var_filtres), ".push({ name: 'rows', value: ", classeLignes, " });");
            }
            if(classeTrisVar != null) {
              auteurPageJs.l();
              for(Integer i = 0; i < classeTrisVar.size(); i++) {
                String classeTriVar = classeTrisVar.get(i);
                String classeTriOrdre = classeTrisOrdre.get(i);

                auteurPageJs.tl(1, i18nClasse.getString(I18n.var_filtres), ".push({ name: '", "sort", "', value: '", classeTriVar, " ", classeTriOrdre, "' });");
              }
            }
            auteurPageJs.tl(1, "fetch(");
            auteurPageJs.tl(2, "'", classeApiUriMethode, "?' + ", i18nClasse.getString(I18n.var_filtres), ".map(function(m) { return m.name + '=' + encodeURIComponent(m.value) }).join('&')");
          }
          if(methodePATCH || methodePUTCopie) {
            auteurPageJs.l();
            auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(", i18nClasse.getString(I18n.var_filtres), ", vals, target, success, error) {");
            auteurPageJs.tl(1, "fetch(");
            auteurPageJs.tl(2, "'", classeApiUriMethode, "?' + ", i18nClasse.getString(I18n.var_filtres), ".map(function(m) { return m.name + '=' + encodeURIComponent(m.value) }).join('&')");
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
            auteurPageJs.tl(2, "}).then(", i18nClasse.getString(I18n.var_reponse), " => {");
            auteurPageJs.tl(3, "if(", i18nClasse.getString(I18n.var_reponse), ".ok) {");
            if("DELETE".equals(classeApiMethodeMethode)) {
              auteurPageJs.tl(4, "success(response, target);");
            } else {
              auteurPageJs.tl(4, i18nClasse.getString(I18n.var_reponse), ".json().then((json) => {");
              auteurPageJs.tl(5, "success(json, target);");
              auteurPageJs.tl(4, "})");
            }
            auteurPageJs.tl(3, "} else {");
            auteurPageJs.tl(4, "error(", i18nClasse.getString(I18n.var_reponse), ", target);");
            auteurPageJs.tl(3, "}");
            auteurPageJs.tl(2, "})");
            auteurPageJs.tl(2, ".catch(", i18nClasse.getString(I18n.var_reponse), " => error(", i18nClasse.getString(I18n.var_reponse), ", target));");
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
            rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);
            QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
            SolrDocumentList rechercheListe = rechercheReponse.getResults();
    
            rechercheLignes = rechercheSolr.getRows();
    
            if(rechercheListe.size() > 0) {
    
              for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
                List<SolrDocument> resultatsSubstitues = rechercheListe.stream().filter(o -> BooleanUtils.isTrue((Boolean)o.get("entiteEstSubstitue_stored_boolean"))).collect(Collectors.toList());
                List<SolrDocument> resultatsNormales = rechercheListe.stream().filter(o -> BooleanUtils.isFalse((Boolean)o.get("entiteEstSubstitue_stored_boolean"))).collect(Collectors.toList());

                for(Integer j = 0; j < resultatsNormales.size(); j++) {
                  entiteDocumentSolr = rechercheListe.get(j);
                  entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNomApi + "_stored_string");
                  SolrDocument resultatSubstitue = resultatsSubstitues.stream().filter(o -> entiteVar.equals(o.get("entiteVar_" + langueNomApi + "_stored_string"))).findFirst().orElse(null);
                  if(resultatSubstitue != null) {
                    if(entiteDocumentSolr.equals(resultatSubstitue))
                      continue;
                    entiteDocumentSolr = resultatSubstitue;
                  }

                  entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + langueNomApi + "_stored_string");
                  entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + langueNomApi + "_stored_string");
                  entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + langueNomApi + "_stored_string");
                  entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + langueNomApi + "_stored_string");
                  entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
                  entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
                  entiteFiwareType = (String)entiteDocumentSolr.get("entiteFiwareType_stored_string");
                  entiteCacherPOST = (Boolean)entiteDocumentSolr.get("entiteCacherPOST_stored_boolean");
                  entiteCacherPATCH = (Boolean)entiteDocumentSolr.get("entiteCacherPATCH_stored_boolean");
                  entiteCacherRecherche = (Boolean)entiteDocumentSolr.get("entiteCacherRecherche_stored_boolean");
                  entiteRechercherMaxVarJsonArray = (String)entiteDocumentSolr.get("entiteRechercherMaxVarJsonArray_stored_string");
                  entiteRechercherMaxVarValeur = (String)entiteDocumentSolr.get("entiteRechercherMaxVarValeur_stored_string");
                  entiteRechercherMaxVar = (String)entiteDocumentSolr.get("entiteRechercherMaxVar_stored_string");
                  entiteRechercherMaxValeur = (String)entiteDocumentSolr.get("entiteRechercherMaxValeur_stored_string");
                  entiteMin = (String)entiteDocumentSolr.get("entiteMin_stored_string");
                  entiteMax = (String)entiteDocumentSolr.get("entiteMax_stored_string");
                  entiteDefaut = (String)entiteDocumentSolr.get("entiteDefaut_stored_string");
                  entiteIcone = (String)entiteDocumentSolr.get("entiteIcone_stored_string");
                  entiteCookie = (String)entiteDocumentSolr.get("entiteCookie_stored_string");
                  entiteHtmColonne = (Integer)entiteDocumentSolr.get("entiteHtmColonne_stored_int");
                  entiteHtmLigne = (Integer)entiteDocumentSolr.get("entiteHtmLigne_stored_int");
                  entitePortee = (String)entiteDocumentSolr.get("entitePortee_stored_string");
                  entiteHtmLigneTitre = (String)entiteDocumentSolr.get("entiteHtmLigneTitre_" + langueNom + "_stored_string");
                  entiteHtmLigneTitreOuvert = (String)entiteDocumentSolr.get("entiteHtmLigneTitreOuvert_" + langueNom + "_stored_string");
                  entiteHtmLigneVerticale = (Boolean)entiteDocumentSolr.get("entiteHtmLigneVerticale_stored_boolean");
                  entiteHtmLigneEnTeteExpression = (String)entiteDocumentSolr.get("entiteHtmLigneEnTeteExpression_stored_string");
                  entiteCouverture = (Boolean)entiteDocumentSolr.get("entiteCouverture_stored_boolean");
                  entiteHtml = (Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean");
                  entiteDocValues = (Boolean)entiteDocumentSolr.get("entiteDocValues_stored_boolean");
                  entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
                  entiteStocke = (Boolean)entiteDocumentSolr.get("entiteStocke_stored_boolean");
                  entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
                  entiteCouleur = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteCouleur_stored_boolean"));
                  entiteModifier = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteModifier_stored_boolean"));
                  entiteDefinir = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteDefinir_stored_boolean"));
                  entiteAttribuer = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteAttribuer_stored_boolean"));
                  entiteSuggere = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean"));
                  entiteAttribuerNomSimple = (String)entiteDocumentSolr.get("entiteAttribuerNomSimple_" + langueNomApi + "_stored_string");
                  entiteAttribuerVar = (String)entiteDocumentSolr.get("entiteAttribuerVar_" + langueNomApi + "_stored_string");
                  entiteAttribuerVarUrlPageAffichage = (String)entiteDocumentSolr.get("entiteAttribuerVarUrlPageAffichage_" + langueNom + "_stored_string");
                  entiteAttribuerVarUrlPageEdition = (String)entiteDocumentSolr.get("entiteAttribuerVarUrlPageEdition_" + langueNom + "_stored_string");
                  entiteAttribuerVarTitre = (String)entiteDocumentSolr.get("entiteAttribuerVarTitre_" + langueNom + "_stored_string");
                  entiteAttribuerVarDescription = (String)entiteDocumentSolr.get("entiteAttribuerVarDescription_" + langueNom + "_stored_string");
                  entiteAttribuerVarImageUrl = (String)entiteDocumentSolr.get("entiteAttribuerVarImageUrl_" + langueNomApi + "_stored_string");
                  entiteAttribuerVarSuggere = (String)entiteDocumentSolr.get("entiteAttribuerVarSuggere_" + langueNomApi + "_stored_string");
                  entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + i18nClasse.getString(I18n.var_Recherche) + "_" + langueNomApi + "_stored_string");
                  entiteAttribuerOperationIdPATCH = (String)entiteDocumentSolr.get("entiteAttribuerOperationIdPATCH_" + langueNomApi + "_stored_string");
                  entiteOperationIdPATCH = (String)entiteDocumentSolr.get("entiteOperationIdPATCH_" + langueNomApi + "_stored_string");
                  entiteTypeJson = (String)entiteDocumentSolr.get("entiteTypeJson_stored_string");
                  entiteAttribuerTypeJson = (String)entiteDocumentSolr.get("entiteAttribuerTypeJson_stored_string");
                  entiteAttribuerContexteIcone = (String)entiteDocumentSolr.get("entiteAttribuerContexteIcone_stored_string");
                  entiteAttribuerTrisVar = (List<String>)entiteDocumentSolr.get("entiteAttribuerTrisVar_" + langueNomApi + "_stored_strings");
                  entiteAttribuerTrisSuffixeType = (List<String>)entiteDocumentSolr.get("entiteAttribuerTrisSuffixeType_stored_strings");
                  entiteAttribuerContexteUnNom = (String)entiteDocumentSolr.get("entiteAttribuerContexteUnNom_" + langueNom + "_stored_string");
                  entiteAttribuerContexteNomPluriel = (String)entiteDocumentSolr.get("entiteAttribuerContexteNomPluriel_" + langueNom + "_stored_string");
    
                  if(entiteSuggere) {
                    auteurPageJs.l();
                    auteurPageJs.tl(0, "function ", i18nClasse.getString(I18n.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "($", i18nClasse.getString(I18n.var_formulaireFiltres), ", $list, target) {");
                    auteurPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
                    auteurPageJs.tl(2, "if($list) {");
                    auteurPageJs.tl(3, "$list.innerHTML = '';");
                    auteurPageJs.tl(3, "data['list'].forEach((o, i) => {");
                    auteurPageJs.tl(4, "var $i = document.querySelector('", classeIcone, "');");
                    auteurPageJs.tl(4, "var $span = document.createElement('span');");
                    auteurPageJs.tl(4, "$span.setAttribute('class', '');");
                    if(classeVarTitre != null)
                      auteurPageJs.tl(4, "$span.innerText = o['", classeVarTitre, "'];");
                    auteurPageJs.tl(4, "var $li = document.createElement('li');");
                    auteurPageJs.tl(4, "var $a = document.createElement('a').setAttribute('href', o['", classeVarUrlPageEdition, "']);");
                    auteurPageJs.tl(4, "$a.append($i);");
                    auteurPageJs.tl(4, "$a.append($span);");
                    auteurPageJs.tl(4, "$li.append($a);");
                    auteurPageJs.tl(4, "$list.append($li);");
                    auteurPageJs.tl(3, "});");
                    auteurPageJs.tl(2, "}");
                    auteurPageJs.tl(1, "};");
                    auteurPageJs.tl(1, "error = function( jqXhr, target2 ) {};");
                    auteurPageJs.tl(1, i18nClasse.getString(I18n.var_rechercher), classeApiClasseNomSimple, "Vals($", i18nClasse.getString(I18n.var_formulaireFiltres), ", target, success, error);");
                    auteurPageJs.tl(0, "}");
                  }
                  else if(entiteAttribuer) {
                    // Attribuer //
                    auteurPageJs.l();
                    auteurPageJs.tl(0, "function ", i18nClasse.getString(I18n.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "(", i18nClasse.getString(I18n.var_filtres), ", $list, ", classeVarId, " = null, ", entiteVar, " = null, ", i18nClasse.getString(I18n.var_attribuer), "=true, target) {");
                    auteurPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
                    auteurPageJs.tl(2, "if($list) {");
                    auteurPageJs.tl(3, "$list.innerHTML = '';");
                    auteurPageJs.tl(3, "data['list'].forEach((o, i) => {");
                    auteurPageJs.tl(4, "var iTemplate = document.createElement('template');");
                    auteurPageJs.tl(4, "iTemplate.innerHTML = '", entiteAttribuerContexteIcone, "';");
                    auteurPageJs.tl(4, "var $i = iTemplate.content;");
                    auteurPageJs.tl(4, "var $span = document.createElement('span');");
                    auteurPageJs.tl(4, "$span.setAttribute('class', '');");
                    auteurPageJs.t(4, "$span.innerText = ");
                    if(entiteAttribuerVarTitre != null)
                      auteurPageJs.s("o['", entiteAttribuerVarTitre, "']");
                    auteurPageJs.l(";");

                    if(entiteAttribuerVarUrlPageEdition != null) {
                      auteurPageJs.tl(4, "var $a = document.createElement('a');");
                      // auteurPageJs.tl(3, "$a.setAttribute('id', o['", entiteAttribuerVar, "']);");
                      auteurPageJs.tl(4, "$a.setAttribute('target', '_blank');");
                      auteurPageJs.tl(4, "$a.setAttribute('href', o['", entiteAttribuerVarUrlPageEdition, "']);");
                    } else {
                      auteurPageJs.tl(4, "var $a = document.createElement('span');");
                    }

                    auteurPageJs.tl(4, "$a.append($i);");
                    auteurPageJs.tl(4, "$a.append($span);");
                    auteurPageJs.tl(4, "var val = o['", entiteAttribuerVar, "'];");
                    if("array".equals(entiteAttribuerTypeJson)) {
                      auteurPageJs.tl(4, "var checked = val == null ? false : (val.includes(", classeVarId, ".toString()));");
                    } else {
                      auteurPageJs.tl(4, "var checked = val == null ? false : (", entiteVar, " != null && val === ", entiteVar, ".toString());");
                    }
                    auteurPageJs.tl(4, "var $input = document.createElement('", composantsWebPrefixe, "checkbox');");
                    auteurPageJs.tl(4, "$input.setAttribute('id', '", classeApiMethodeMethode, "_", entiteVar, "_' + ", classeVarId, " + '_", entiteAttribuerVar, "_' + o['", entiteAttribuerVar, "']);");
                    auteurPageJs.tl(4, "$input.setAttribute('name', '", entiteAttribuerVar, "');");
                    auteurPageJs.tl(4, "$input.setAttribute('value', o['", entiteAttribuerVar, "']);");
                    auteurPageJs.tl(4, "$input.setAttribute('class', '", i18nClasse.getString(I18n.var_valeur), entiteVarCapitalise, " ');");

                    auteurPageJs.tl(4, "if(", classeVarId, " != null) {");
                    auteurPageJs.tl(5, "$input.addEventListener('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "change', function(event) {");
                    if("array".equals(entiteTypeJson)) {
                      auteurPageJs.tl(6, entiteOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeVarId, ":' + ", classeVarId, " }], { [(event.target.checked ? 'add' : 'remove') + '", entiteVarCapitalise, "']: o['", entiteAttribuerVar, "'] }");
                    }
                    else {
                      auteurPageJs.tl(6, entiteOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeVarId, ":' + ", classeVarId, " }], { [(event.target.checked ? 'set' : 'remove') + '", entiteVarCapitalise, "']: o['", entiteAttribuerVar, "'] }");
                    }
                    auteurPageJs.tl(8, ", target");
                    auteurPageJs.tl(8, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) {");
                    auteurPageJs.tl(9, i18nClasse.getString(I18n.var_ajouterLueur), "(target);");
                    auteurPageJs.tl(9, i18nClasse.getString(I18n.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "(", i18nClasse.getString(I18n.var_filtres), ", $list, ", classeVarId, ", o['", entiteAttribuerVar, "'], ", i18nClasse.getString(I18n.var_attribuer), ", target);");
                    auteurPageJs.tl(8, "}");
                    auteurPageJs.tl(8, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterErreur), "(target); }");
                    auteurPageJs.tl(6, ");");
                    auteurPageJs.tl(5, "});");

                    // auteurPageJs.tl(4, "$input.setAttribute('onclick', '", i18nClasse.getString(I18n.var_enleverLueur), "(this); ');");
                    auteurPageJs.tl(4, "}");

                    auteurPageJs.tl(4, "if(checked)");
                    auteurPageJs.tl(5, "$input.setAttribute('checked', 'checked');");
                    auteurPageJs.tl(4, "var $li = document.createElement('li');");
                    if(entiteAttribuerTrisVar != null && entiteAttribuerTrisSuffixeType != null && entiteAttribuerTrisSuffixeType.size() > 0 && "_double".equals(entiteAttribuerTrisSuffixeType.get(0))) {
                      for(String entiteAttribuerTriVar : entiteAttribuerTrisVar) {
                        auteurPageJs.tl(4, "var ", entiteAttribuerTriVar, " = o['", entiteAttribuerTriVar, "'];");
                      }
                      String entiteAttribuerTriVarAncien = null;
                      Integer k = 4;
                      for(String entiteAttribuerTriVar : entiteAttribuerTrisVar) {
                        if(entiteAttribuerTriVarAncien != null)
                          k = 5;

                        auteurPageJs.l();
                        if(entiteAttribuerTriVarAncien != null)
                          auteurPageJs.tl(4, "if(", entiteAttribuerTriVarAncien, " != null) {");
                        auteurPageJs.tl(k, "$sort = document.createElement('span').setAttribute('style', 'padding-right: 8px; ');");
                        auteurPageJs.tl(k, "var $sortInput = document.createElement('", composantsWebPrefixe, "input')");
                        auteurPageJs.tl(k, "$sortInput.setAttribute('style', 'width: 4em; ');");
                        auteurPageJs.tl(k, "$sortInput.setAttribute('id', \"", i18nClasse.getString(I18n.var_attribuer), "_\" + o['", entiteAttribuerVar, "'] + \"_", i18nClasse.getString(I18n.var_tri), "_", entiteAttribuerTriVar, "\");");
                        auteurPageJs.tl(k, "$sortInput.setAttribute('value', ", entiteAttribuerTriVar, ").setAttribute('onchange', ");
                        auteurPageJs.tl(k + 1, "\"document.querySelector('#", classeApiClasseNomSimple, "Form :input[name=\\\"focusId\\\"]').value = this.getAttribute('id'); \"");
                        auteurPageJs.tl(k + 1, "+ \"", entiteAttribuerOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeVarId, ":\" + o['", entiteAttribuerVar, "'] + \"' }], { ['set", StringUtils.capitalize(entiteAttribuerTriVar), "']: this.value ? this.value : null }\"");
                        auteurPageJs.tl(k + 2, ", $input");
                        auteurPageJs.tl(k + 2, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterLueur), "(target); }");
                        auteurPageJs.tl(k + 2, ", function(", i18nClasse.getString(I18n.var_reponse), ", target) { ", i18nClasse.getString(I18n.var_ajouterErreur), "(target); }");
                        // auteurPageJs.tl(k + 2, "+ \", function() { \"");
                        // auteurPageJs.tl(k + 2, "+ \"}\"");
                        // auteurPageJs.tl(k + 2, "+ \", function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "(document.querySelector('#", langueConfig.getString(ConfigCles.var_attribuer), "_\" + o['", entiteAttribuerVar, "'] + \"_", langueConfig.getString(ConfigCles.var_tri), "_", entiteAttribuerTriVar, "')); }\"");
                        // auteurPageJs.tl(k + 2, "+ \" ); \"); ");
                        auteurPageJs.tl(k, "$sort.append($sortInput);");
                        auteurPageJs.tl(k, "$li.append($sort);");
                        if(entiteAttribuerTriVarAncien != null)
                          auteurPageJs.tl(4, "}");

                        entiteAttribuerTriVarAncien = entiteAttribuerTriVar;
                      }
                    }
                    auteurPageJs.tl(4, "if(", i18nClasse.getString(I18n.var_attribuer), ")");
                    auteurPageJs.tl(5, "$li.append($input);");
                    auteurPageJs.tl(4, "$li.append($a);");
                    auteurPageJs.tl(4, "$list.append($li);");
                    auteurPageJs.tl(3, "});");
                    auteurPageJs.tl(2, "}");
                    // auteurPageJs.tl(2, "var focusId = document.querySelector('#", classeApiClasseNomSimple, "Form :input[name=\"focusId\"]')?.value;");
                    // auteurPageJs.tl(2, "if(focusId)");
                    // auteurPageJs.tl(3, "document.querySelector('#' + focusId).parent().next().querySelector('", "pf-".equals(composantsWebPrefixe) ? "pf-" : "", "input').focus();");
                    auteurPageJs.tl(1, "};");
                    auteurPageJs.tl(1, "error = function( jqXhr, target2 ) {};");
                    auteurPageJs.tl(1, entiteAttribuerOperationIdRecherche, "Vals(", i18nClasse.getString(I18n.var_filtres), ", target, success, error);");
                    auteurPageJs.tl(0, "}");

                    auteurWebsocket.l();
                    auteurWebsocket.tl(2, "window.eventBus.registerHandler('websocket", entiteAttribuerNomSimple, "', function (error, message) {");
//		  								auteurWebsocket.tl(3, "var json = JSON.parse(message['body']);");
//		  								auteurWebsocket.tl(3, "var id = json['id'];");
//		  								auteurWebsocket.tl(3, langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "(document.querySelector('#' + (this.value ? '", langueConfig.getString(ConfigCles.var_suggere), "' : 'form') + '", classeApiClasseNomSimple, entiteVarCapitalise, "'), document.querySelector('#", "list", classeApiClasseNomSimple, entiteVarCapitalise, "_", classeApiMethodeMethode, "'));");
                    auteurWebsocket.tl(3, "document.querySelector('.Page_", entiteVar, "').trigger('oninput');");
                    auteurWebsocket.tl(3, "document.querySelector('.Page_", entiteVar, "_", i18nClasse.getString(I18n.var_ajouter), "').innerText = '", i18nClasse.getString(I18n.var_ajouter), " ", entiteAttribuerContexteUnNom, "';");
                    auteurWebsocket.tl(3, "document.querySelector('.Page_", entiteVar, "_", i18nClasse.getString(I18n.var_ajouter), "').classList.remove('w3-disabled');");
                    auteurWebsocket.tl(3, "document.querySelector('.Page_", entiteVar, "_", i18nClasse.getString(I18n.var_ajouter), "').setAttribute('disabled', false);");
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

        if(classeApiMethode.contains(i18nGlobale.getString(I18n.var_PageRecherche)) || classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals("DELETE") || classeApiMethode.equals(i18nClasse.getString(I18n.var_DELETEFiltre)) || classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTCopie)) || classeApiMethode.equals(i18nGlobale.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
          if(classeApiMethode.equals("DELETE")) {
            auteurPageJsRecherche.l();
            auteurPageJsRecherche.tl(1, "document.querySelector('#htm", i18nGlobale.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "')?.addEventListener('click', (event) => {");
            auteurPageJsRecherche.tl(2, "var confirmResponse = confirm('", i18nGlobale.getString(I18n.str_confirmer_supprimer), "'); ");
            auteurPageJsRecherche.tl(2, "if(confirmResponse) { ");
            auteurPageJsRecherche.tl(3, "var ", classeVarId, " =  event.currentTarget.getAttribute('data-", classeVarId, "');");
            auteurPageJsRecherche.tl(3, "delete", classeNomSimple, "(");
            auteurPageJsRecherche.tl(5, "event.currentTarget");
            auteurPageJsRecherche.tl(5, ", ", classeVarId);
            auteurPageJsRecherche.tl(5, ", function(", i18nGlobale.getString(I18n.var_reponse), ", target) { ", i18nGlobale.getString(I18n.var_ajouterLueur), "(target); }");
            auteurPageJsRecherche.tl(5, ", function(", i18nGlobale.getString(I18n.var_reponse), ", target) { ", i18nGlobale.getString(I18n.var_ajouterErreur), "(target); }");
            auteurPageJsRecherche.tl(5, ");");
            auteurPageJsRecherche.tl(2, "}");
            auteurPageJsRecherche.tl(1, "});");
          } else if(classeApiMethode.equals(i18nClasse.getString(I18n.var_DELETEFiltre))) {
            auteurPageJsRecherche.l();
            auteurPageJsRecherche.tl(1, "document.querySelector('#htm", i18nGlobale.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "')?.addEventListener('click', (event) => {");
            auteurPageJsRecherche.tl(2, "var confirmResponse = confirm('", i18nGlobale.getString(I18n.str_confirmer_supprimer), "'); ");
            auteurPageJsRecherche.tl(2, "if(confirmResponse) { ");
            auteurPageJsRecherche.tl(3, "delete", i18nGlobale.getString(I18n.var_filtre), classeNomSimple, "(");
            auteurPageJsRecherche.tl(5, "event.currentTarget");
            auteurPageJsRecherche.tl(5, ", function(", i18nGlobale.getString(I18n.var_reponse), ", target) { ", i18nGlobale.getString(I18n.var_ajouterLueur), "(target); }");
            auteurPageJsRecherche.tl(5, ", function(", i18nGlobale.getString(I18n.var_reponse), ", target) { ", i18nGlobale.getString(I18n.var_ajouterErreur), "(target); }");
            auteurPageJsRecherche.tl(5, ");");
            auteurPageJsRecherche.tl(2, "}");
            auteurPageJsRecherche.tl(1, "});");
          }
        }
      }
    }
  }

  public void ecrirePageJs(String langueNomApi, String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {
    if(auteurPageJs != null) {
      if(!classePageSimple) {
        auteurWebsocket.flushClose();
        auteurPageJs.l();
        auteurPageJs.tl(0, "async function websocket", classeApiClasseNomSimple, "(success) {");
        auteurPageJs.tl(1, "window.eventBus.onopen = function () {");
        auteurPageJs.l();
        auteurPageJs.tl(2, "window.eventBus.registerHandler('websocket", classeApiClasseNomSimple, "', function (error, message) {");
//				auteurPageJs.tl(3, langueConfig.getString(ConfigCles.var_rechercher), langueConfig.getString(ConfigCles.var_Page), "();");
        auteurPageJs.tl(3, "var json = JSON.parse(message['body']);");
        auteurPageJs.tl(3, "var ", classeVarId, " = json['id'];");
        auteurPageJs.tl(3, "var ", i18nGlobale.getString(I18n.var_solrIds), " = json['", i18nGlobale.getString(I18n.var_solrIds), "'];");
        auteurPageJs.tl(3, "var empty = json['empty'];");
//					auteurPageJs.tl(3, "if(!empty) {");
        auteurPageJs.tl(3, "var numFound = parseInt(json['numFound']);");
        auteurPageJs.tl(3, "var numPATCH = parseInt(json['numPATCH']);");
        auteurPageJs.tl(3, "var percent = Math.floor( numPATCH / numFound * 100 ) + '%';");
        auteurPageJs.tl(3, "var $box = document.createElement('div');");
        auteurPageJs.tl(3, "$box.setAttribute('class', 'w3-quarter box-' + ", classeVarId, " + ' ');");
        auteurPageJs.tl(3, "$box.setAttribute('id', 'box-' + ", classeVarId, ");");
        auteurPageJs.tl(3, "$box.setAttribute('data-numPATCH', numPATCH);");
        auteurPageJs.tl(3, "var $margin = document.createElement('div');");
        auteurPageJs.tl(3, "$margin.setAttribute('class', 'w3-margin ');");
        auteurPageJs.tl(3, "$margin.setAttribute('id', 'margin-' + ", classeVarId, ");");
        auteurPageJs.tl(3, "var $card = document.createElement('div');");
        auteurPageJs.tl(3, "$card.setAttribute('class', 'w3-card w3-white ');");
        auteurPageJs.tl(3, "$card.setAttribute('id', 'card-' + ", classeVarId, ");");

        auteurPageJs.tl(3, "var $header = document.createElement('div');");
        auteurPageJs.tl(3, "$header.setAttribute('class', 'w3-container fa-", classeCouleur, " ');");
        auteurPageJs.tl(3, "$header.setAttribute('id', 'header-' + ", classeVarId, ");");
        auteurPageJs.tl(3, "var iTemplate = document.createElement('template');");
        auteurPageJs.tl(3, "iTemplate.innerHTML = '", classeIcone, "';");
        auteurPageJs.tl(3, "var $i = iTemplate.content;");
        auteurPageJs.tl(3, "var $headerSpan = document.createElement('span');");
        auteurPageJs.tl(3, "$headerSpan.setAttribute('class', '');");
        auteurPageJs.tl(3, "$headerSpan.innerText = '", i18nClasse.getString(I18n.var_modifier), " ", classeNomAdjectifPluriel, " ", i18nClasse.getString(I18n.var_dans), " ' + json.", i18nClasse.getString(I18n.var_tempsRestant), ";");
        auteurPageJs.tl(3, "var $x = document.createElement('span');");
        auteurPageJs.tl(3, "$x.setAttribute('class', 'w3-button w3-display-topright ');");
        auteurPageJs.tl(3, "$x.setAttribute('onclick', 'document.querySelector(\"#card-' + ", classeVarId, " + '\");');");
        auteurPageJs.tl(3, "$x.classList.add(\"display-none\");");
        auteurPageJs.tl(3, "$x.setAttribute('id', 'x-' + ", classeVarId, ");");
        auteurPageJs.tl(3, "var $body = document.createElement('div');");
        auteurPageJs.tl(3, "$body.setAttribute('class', 'w3-container w3-padding ');");
        auteurPageJs.tl(3, "$body.setAttribute('id', 'text-' + ", classeVarId, ");");
        auteurPageJs.tl(3, "var $bar = document.createElement('div');");
        auteurPageJs.tl(3, "$bar.setAttribute('class', 'w3-light-gray ');");
        auteurPageJs.tl(3, "$bar.setAttribute('id', 'bar-' + ", classeVarId, ");");
        auteurPageJs.tl(3, "var $progress = document.createElement('div');");
        auteurPageJs.tl(3, "$progress.setAttribute('class', 'w3-", classeCouleur, " ');");
        auteurPageJs.tl(3, "$progress.setAttribute('style', 'height: 24px; width: ' + percent + '; ');");
        auteurPageJs.tl(3, "$progress.setAttribute('id', 'progress-' + ", classeVarId, ");");
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
        auteurPageJs.tl(4, "var $old_box = document.querySelector('.box-' + ", classeVarId, ");");
        // auteurPageJs.tl(4, "if(!$old_box.size()) {");
        // auteurPageJs.tl(5, "document.querySelector('.top-box').append($box);");
        // auteurPageJs.tl(4, "} else if($old_box && $old_box.getAttribute('data-numPATCH') < numFound) {");
        // auteurPageJs.tl(5, "document.querySelector('.box-' + id).html($margin);");
        // auteurPageJs.tl(4, "}");
        auteurPageJs.tl(3, "} else {");
        auteurPageJs.tl(4, "document.querySelector('.box-' + ", classeVarId, ")?.remove();");
        auteurPageJs.tl(3, "}");
        auteurPageJs.tl(3, "if(", classeVarId, ") {");
        auteurPageJs.tl(4, "if(success)");
        auteurPageJs.tl(5, "success(json);");
        auteurPageJs.tl(3, "}");
//					auteurPageJs.tl(3, "}");
        auteurPageJs.tl(2, "});");
        auteurPageJs.s(auteurWebsocket);
        auteurPageJs.tl(1, "}");
        auteurPageJs.tl(0, "}");

        auteurPageJs.tl(0, "async function websocket", classeApiClasseNomSimple, "Inner(", i18nClasse.getString(I18n.var_requeteApi), ") {");
        auteurPageJs.s(wWebsocket);
        auteurPageJs.tl(0, "}");
        auteurPageJs.l();
        auteurPageJs.tl(0, "function ", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Graphique), classeNomSimple, "(", i18nClasse.getString(I18n.var_requeteApi), ") {");
        auteurPageJs.tl(1, "var r = document.querySelector('.", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Formulaire), " .", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Reponse), "')?.value;");
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
        auteurPageJs.tl(4, "var pivot1Var", i18nClasse.getString(I18n.var_Indexe), " = pivot1Name;");
        auteurPageJs.tl(4, "if(pivot1Var", i18nClasse.getString(I18n.var_Indexe), ".includes(','))");
        auteurPageJs.tl(5, "pivot1Var", i18nClasse.getString(I18n.var_Indexe), " = pivot1Var", i18nClasse.getString(I18n.var_Indexe), ".substring(0, pivot1Var", i18nClasse.getString(I18n.var_Indexe), ".indexOf(','));");
        auteurPageJs.tl(4, "var pivot1VarObj = Object.values(window.varsFq).filter(o => o.varIndexed === pivot1Var", i18nClasse.getString(I18n.var_Indexe), ")[0];");
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
        auteurPageJs.tl(6, "var pivot2Var", i18nClasse.getString(I18n.var_Indexe), " = pivot1Map[pivot1Vals[0]].pivotMap[Object.keys(pivot1Map[pivot1Vals[0]].pivotMap)[0]].field;");
        auteurPageJs.tl(6, "var pivot2VarObj = Object.values(window.varsFq).filter(o => o.varIndexed === pivot2Var", i18nClasse.getString(I18n.var_Indexe), ")[0];");
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
        auteurPageJs.tl(5, "Plotly.react('htmBody", i18nClasse.getString(I18n.var_Graphique), classePageNomSimple, "', data, layout);");
        auteurPageJs.tl(4, "}");
        auteurPageJs.tl(3, "}");
        auteurPageJs.tl(2, "}");

        // Maps
        if(classeVarEmplacement != null || classeVarAire != null) {
          auteurPageJs.l();
          auteurPageJs.tl(2, "// ", i18nClasse.getString(I18n.var_Graphique), " ", i18nClasse.getString(I18n.var_Emplacement));
          auteurPageJs.tl(2, "window.mapLayers = {};");
          if(classeVarEmplacement != null) {
            auteurPageJs.tl(2, "window.bounds = null;");
            auteurPageJs.tl(2, "if(", i18nClasse.getString(I18n.var_liste), classeNomSimple, ".filter(o => o.", classeVarEmplacement, ")) {");
            auteurPageJs.tl(3, "window.bounds = L.latLngBounds(", i18nClasse.getString(I18n.var_liste), classeNomSimple, ".filter(o => o.", classeVarEmplacement, ").map((c) => {");
            auteurPageJs.tl(4, "return [c.", classeVarEmplacement, ".coordinates[1], c.", classeVarEmplacement, ".coordinates[0]];");
            auteurPageJs.tl(3, "}));");
            auteurPageJs.tl(2, "}");
          }
// const bounds = L.latLngBounds(data.geometry.coordinates.map((c) => { 
//   return [c[1], c[0]]; 
// }));
          auteurPageJs.tl(2, "function onEachFeature(feature, layer) {");
          auteurPageJs.tl(3, "let popupContent = ", i18nClasse.getString(I18n.var_htmInfobulle), classeNomSimple, "(feature, layer);");
          auteurPageJs.tl(3, "layer.bindPopup(popupContent);");
          auteurPageJs.tl(3, "window.mapLayers[feature.properties.id] = layer;");
          auteurPageJs.tl(2, "};");
          auteurPageJs.tl(2, "if(window.map", classeNomSimple, ") {");
          auteurPageJs.tl(3, "window.geoJSON", classeNomSimple, ".clearLayers();");
          auteurPageJs.tl(3, "window.", i18nClasse.getString(I18n.var_liste), classeNomSimple, ".forEach((", varResultat, ", index) => {");
          if(classeVarEmplacement != null) {
            auteurPageJs.tl(4, "if(", varResultat, ".", classeVarEmplacement, ") {");
            auteurPageJs.tl(5, "var shapes = [];");
            auteurPageJs.tl(5, "if(Array.isArray(", varResultat, ".", classeVarEmplacement, "))");
            auteurPageJs.tl(6, "shapes = shapes.concat(", varResultat, ".", classeVarEmplacement, ");");
            auteurPageJs.tl(5, "else");
            auteurPageJs.tl(6, "shapes.push(", varResultat, ".", classeVarEmplacement, ");");
            auteurPageJs.tl(5, "shapes.forEach(function(shape, index) {");
            auteurPageJs.tl(6, "var features = [{");
            auteurPageJs.tl(7, "\"type\": \"Feature\"");
            auteurPageJs.tl(7, ", \"properties\": ", varResultat);
            auteurPageJs.tl(7, ", \"geometry\": shape");
            auteurPageJs.tl(7, ", \"index\": index");
            auteurPageJs.tl(6, "}];");
            auteurPageJs.tl(6, "var layerGeoJson = L.geoJSON(features, {");
            auteurPageJs.tl(7, "onEachFeature: onEachFeature");
            auteurPageJs.tl(7, ", style: ", i18nClasse.getString(I18n.var_jsStyle), classeNomSimple, "");
            auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
            auteurPageJs.tl(8, "return L.circleMarker(latlng, ", i18nClasse.getString(I18n.var_jsStyle), classeNomSimple, "(feature));");
            auteurPageJs.tl(7, "}");
            auteurPageJs.tl(6, "});");
            auteurPageJs.tl(6, "window.geoJSON", classeNomSimple, ".addLayer(layerGeoJson);");
            if(classeEmplacementSvg != null || classeVarEmplacementSvg != null) {
              auteurPageJs.l();
              auteurPageJs.tl(6, "var layerSvg = L.geoJSON(features, {");
              auteurPageJs.tl(7, "onEachFeature: onEachFeature");
              auteurPageJs.tl(7, ", style: ", i18nClasse.getString(I18n.var_jsStyle), classeNomSimple, "");
              auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
              auteurPageJs.tl(8, "var svgElement = document.createElementNS('http://www.w3.org/2000/svg', 'svg');");
              auteurPageJs.tl(8, "svgElement.setAttribute('xmlns', 'http://www.w3.org/2000/svg');");
              if(classeVarEmplacementSvg != null) {
                auteurPageJs.tl(8, "svgElement.innerHTML = ", varResultat, ".", classeVarEmplacementSvg, ";");
              } else {
                auteurPageJs.tl(8, "svgElement.innerHTML = '", classeEmplacementSvg.replace("'", "&apos;"), "';");
              }
              auteurPageJs.tl(8, "svgElementBounds = L.latLng(", varResultat, ".", classeVarEmplacement, ".coordinates[1], ", varResultat, ".", classeVarEmplacement, ".coordinates[0]).toBounds(", classeVarEmplacementRayon != null ? varResultat + "." + classeVarEmplacementRayon : (classeEmplacementRayon != null ? classeEmplacementRayon : 100), ");");
              auteurPageJs.tl(8, "var layer = L.svgOverlay(svgElement, svgElementBounds, {");
              auteurPageJs.tl(9, "interactive: true");
              auteurPageJs.tl(8, "});");
              auteurPageJs.tl(8, "return layer;");
              auteurPageJs.tl(7, "}");
              auteurPageJs.tl(6, "});");
              auteurPageJs.tl(6, "window.geoJSON", classeNomSimple, ".addLayer(layerSvg);");
            }
            auteurPageJs.tl(5, "});");
            auteurPageJs.tl(4, "}");
          }
          if(classeVarAire != null) {
            auteurPageJs.tl(4, "if(", varResultat, ".", classeVarAire, ") {");
            auteurPageJs.tl(5, "var shapes = [];");
            auteurPageJs.tl(5, "if(Array.isArray(", varResultat, ".", classeVarAire, "))");
            auteurPageJs.tl(6, "shapes = shapes.concat(", varResultat, ".", classeVarAire, ");");
            auteurPageJs.tl(5, "else");
            auteurPageJs.tl(6, "shapes.push(", varResultat, ".", classeVarAire, ");");
            auteurPageJs.tl(5, "shapes.forEach(function(shape, index) {");
            auteurPageJs.tl(6, "var features = [{");
            auteurPageJs.tl(7, "\"type\": \"Feature\"");
            auteurPageJs.tl(7, ", \"properties\": ", varResultat);
            auteurPageJs.tl(7, ", \"geometry\": shape");
            auteurPageJs.tl(7, ", \"index\": index");
            auteurPageJs.tl(6, "}];");
            auteurPageJs.tl(6, "var layerGeoJson = L.geoJSON(features, {");
            auteurPageJs.tl(7, "onEachFeature: onEachFeature");
            auteurPageJs.tl(7, ", style: ", i18nClasse.getString(I18n.var_jsStyle), classeNomSimple, "");
            auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
            auteurPageJs.tl(8, "return L.circleMarker(latlng, ", i18nClasse.getString(I18n.var_jsStyle), classeNomSimple, "(feature));");
            auteurPageJs.tl(7, "}");
            auteurPageJs.tl(6, "});");
            auteurPageJs.tl(6, "window.geoJSON", classeNomSimple, ".addLayer(layerGeoJson);");
            auteurPageJs.tl(5, "});");
            auteurPageJs.tl(4, "}");
          }
          auteurPageJs.tl(3, "});");
          auteurPageJs.tl(2, "} else if(document.getElementById('htmBody", i18nClasse.getString(I18n.var_Graphique), i18nClasse.getString(I18n.var_Emplacement), classePageNomSimple, "')) {");
          auteurPageJs.tl(3, "window.map", classeNomSimple, " = L.map('htmBody", i18nClasse.getString(I18n.var_Graphique), i18nClasse.getString(I18n.var_Emplacement), classePageNomSimple, "', {");
          auteurPageJs.tl(4, "position: 'topright'");
          auteurPageJs.tl(4, ", zoomControl: true");
          auteurPageJs.tl(4, ", scrollWheelZoom: true");
          auteurPageJs.tl(4, ", closePopupOnClick: false");
          auteurPageJs.tl(4, ", contextmenu: true");
          auteurPageJs.tl(4, ", contextmenuWidth: 140");
          auteurPageJs.tl(4, ", contextmenuItems: [");
          auteurPageJs.tl(5, "{");
          auteurPageJs.tl(6, "text: 'Show coordinates'");
          auteurPageJs.tl(6, ", callback: function(event) {");
          auteurPageJs.tl(7, "alert(event.latlng);");
          auteurPageJs.tl(6, "}");
          auteurPageJs.tl(5, "}");
          auteurPageJs.tl(5, "]");
          auteurPageJs.tl(3, "});");
          auteurPageJs.tl(3, "window.map", classeNomSimple, ".zoomControl.setPosition('topright');");
          auteurPageJs.tl(3, "var data = [];");
          auteurPageJs.tl(3, "var layout = {};");
          auteurPageJs.tl(3, "layout['showlegend'] = true;");
          auteurPageJs.tl(3, "layout['dragmode'] = 'zoom';");
          auteurPageJs.tl(3, "layout['uirevision'] = 'true';");
          auteurPageJs.tl(3, "var legend = L.control({position: 'bottomright'});");
          auteurPageJs.tl(3, "legend.onAdd = ", i18nClasse.getString(I18n.var_jsLegende), classeNomSimple, ";");
          // auteurPageJs.tl(3, "legend.addTo(window.map", classeNomSimple, ");");
          auteurPageJs.tl(3, "L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {");
          auteurPageJs.tl(4, "maxZoom: 19,");
          auteurPageJs.tl(4, "attribution: '&copy; <a href=\"http://www.openstreetmap.org/copyright\">OpenStreetMap</a>'");
          auteurPageJs.tl(3, "}).addTo(window.map", classeNomSimple, ");");
          auteurPageJs.l();
          auteurPageJs.tl(3, "if(window.bounds && window['DEFAULT_MAP_ZOOM'] && window.bounds.getNorthEast()) {");
          auteurPageJs.tl(4, "if(", i18nClasse.getString(I18n.var_liste), classeNomSimple, ".length == 1) {");
          auteurPageJs.tl(5, "window.map", classeNomSimple, ".setView(window.bounds.getNorthEast(), window['DEFAULT_MAP_ZOOM']);");
          auteurPageJs.tl(4, "} else {");
          auteurPageJs.tl(5, "window.map", classeNomSimple, ".fitBounds(window.bounds);");
          auteurPageJs.tl(4, "}");
          auteurPageJs.tl(3, "} else {");
          auteurPageJs.tl(4, "if(window['DEFAULT_MAP_LOCATION'] && window['DEFAULT_MAP_ZOOM'])");
          auteurPageJs.tl(5, "window.map", classeNomSimple, ".setView([window['DEFAULT_MAP_LOCATION']['coordinates'][1], window['DEFAULT_MAP_LOCATION']['coordinates'][0]], window['DEFAULT_MAP_ZOOM']);");
          auteurPageJs.tl(4, "else if(window['DEFAULT_MAP_ZOOM'])");
          auteurPageJs.tl(5, "window.map", classeNomSimple, ".setView(null, window['DEFAULT_MAP_ZOOM']);");
          auteurPageJs.tl(4, "else if(window['DEFAULT_MAP_LOCATION'])");
          auteurPageJs.tl(5, "window.map", classeNomSimple, ".setView([window['DEFAULT_MAP_LOCATION']['coordinates'][1], window['DEFAULT_MAP_LOCATION']['coordinates'][0]]);");
          auteurPageJs.tl(3, "}");
          auteurPageJs.l();
          auteurPageJs.tl(3, "layout['margin'] = { r: 0, t: 0, b: 0, l: 0 };");
          auteurPageJs.tl(3, "window.geoJSON", classeNomSimple, " = L.geoJSON().addTo(window.map", classeNomSimple, ");");
          //auteurPageJs.tl(3, "window.geoJSONLayerGroup", classeNomSimple, " = L.LayerGroup();");
          auteurPageJs.tl(3, "window.", i18nClasse.getString(I18n.var_liste), classeNomSimple, ".forEach((", varResultat, ", index) => {");
          if(classeVarEmplacement != null) {
            auteurPageJs.tl(4, "if(", varResultat, ".", classeVarEmplacement, ") {");
            auteurPageJs.tl(5, "var shapes = [];");
            auteurPageJs.tl(5, "if(Array.isArray(", varResultat, ".", classeVarEmplacement, "))");
            auteurPageJs.tl(6, "shapes = shapes.concat(", varResultat, ".", classeVarEmplacement, ");");
            auteurPageJs.tl(5, "else");
            auteurPageJs.tl(6, "shapes.push(", varResultat, ".", classeVarEmplacement, ");");
            auteurPageJs.tl(5, "shapes.forEach(shape => {");
            auteurPageJs.tl(6, "var features = [{");
            auteurPageJs.tl(7, "\"type\": \"Feature\"");
            auteurPageJs.tl(7, ", \"properties\": ", varResultat);
            auteurPageJs.tl(7, ", \"geometry\": shape");
            auteurPageJs.tl(7, ", \"index\": index");
            auteurPageJs.tl(6, "}];");
            auteurPageJs.tl(6, "var layerGeoJson = L.geoJSON(features, {");
            auteurPageJs.tl(7, "onEachFeature: onEachFeature");
            auteurPageJs.tl(7, ", style: ", i18nClasse.getString(I18n.var_jsStyle), classeNomSimple, "");
            auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
            auteurPageJs.tl(8, "return L.circleMarker(latlng, ", i18nClasse.getString(I18n.var_jsStyle), classeNomSimple, "(feature));");
            auteurPageJs.tl(7, "}");
            auteurPageJs.tl(6, "});");
            auteurPageJs.tl(6, "window.geoJSON", classeNomSimple, ".addLayer(layerGeoJson);");
            if(classeEmplacementSvg != null || classeVarEmplacementSvg != null) {
              auteurPageJs.l();
              auteurPageJs.tl(6, "var layerSvg = L.geoJSON(features, {");
              auteurPageJs.tl(7, "onEachFeature: onEachFeature");
              auteurPageJs.tl(7, ", style: ", i18nClasse.getString(I18n.var_jsStyle), classeNomSimple, "");
              auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
              auteurPageJs.tl(8, "var svgElement = document.createElementNS('http://www.w3.org/2000/svg', 'svg');");
              auteurPageJs.tl(8, "svgElement.setAttribute('xmlns', 'http://www.w3.org/2000/svg');");
              if(classeVarEmplacementSvg != null) {
                auteurPageJs.tl(8, "svgElement.innerHTML = ", varResultat, ".", classeVarEmplacementSvg, ";");
              } else {
                auteurPageJs.tl(8, "svgElement.innerHTML = '", classeEmplacementSvg.replace("'", "&apos;"), "';");
              }
              auteurPageJs.tl(8, "svgElementBounds = L.latLng(", varResultat, ".", classeVarEmplacement, ".coordinates[1], ", varResultat, ".", classeVarEmplacement, ".coordinates[0]).toBounds(", classeVarEmplacementRayon != null ? varResultat + "." + classeVarEmplacementRayon : (classeEmplacementRayon != null ? classeEmplacementRayon : 100), ");");
              auteurPageJs.tl(8, "var layer = L.svgOverlay(svgElement, svgElementBounds, {");
              auteurPageJs.tl(9, "interactive: true");
              auteurPageJs.tl(8, "});");
              auteurPageJs.tl(8, "return layer;");
              auteurPageJs.tl(7, "}");
              auteurPageJs.tl(6, "});");
              auteurPageJs.tl(6, "window.geoJSON", classeNomSimple, ".addLayer(layerSvg);");
            }
            auteurPageJs.tl(5, "});");
            auteurPageJs.tl(4, "}");
          }
          if(classeVarAire != null) {
            auteurPageJs.tl(4, "if(", varResultat, ".", classeVarAire, ") {");
            auteurPageJs.tl(5, "var shapes = [];");
            auteurPageJs.tl(5, "if(Array.isArray(", varResultat, ".", classeVarAire, "))");
            auteurPageJs.tl(6, "shapes = shapes.concat(", varResultat, ".", classeVarAire, ");");
            auteurPageJs.tl(5, "else");
            auteurPageJs.tl(6, "shapes.push(", varResultat, ".", classeVarAire, ");");
            auteurPageJs.tl(5, "shapes.forEach(shape => {");
            auteurPageJs.tl(6, "var features = [{");
            auteurPageJs.tl(7, "\"type\": \"Feature\"");
            auteurPageJs.tl(7, ", \"properties\": ", varResultat);
            auteurPageJs.tl(7, ", \"geometry\": shape");
            auteurPageJs.tl(7, ", \"index\": index");
            auteurPageJs.tl(6, "}];");
            auteurPageJs.tl(6, "var layerGeoJson = L.geoJSON(features, {");
            auteurPageJs.tl(7, "onEachFeature: onEachFeature");
            auteurPageJs.tl(7, ", style: ", i18nClasse.getString(I18n.var_jsStyle), classeNomSimple, "");
            auteurPageJs.tl(7, ", pointToLayer: function(feature, latlng) {");
            auteurPageJs.tl(8, "return L.circleMarker(latlng, ", i18nClasse.getString(I18n.var_jsStyle), classeNomSimple, "(feature));");
            auteurPageJs.tl(7, "}");
            auteurPageJs.tl(6, "});");
            auteurPageJs.tl(6, "window.geoJSON", classeNomSimple, ".addLayer(layerGeoJson);");
            auteurPageJs.tl(5, "});");
            auteurPageJs.tl(4, "}");
          }
          auteurPageJs.tl(3, "});");
          auteurPageJs.tl(3, "window.map", classeNomSimple, ".on('popupopen', function(e) {");
          auteurPageJs.tl(4, "if(e.popup._source) {");
          auteurPageJs.tl(5, "var feature = e.popup._source.feature;");
          auteurPageJs.tl(5, i18nClasse.getString(I18n.var_jsInfobulle), classeNomSimple, "(e, feature);");
          auteurPageJs.tl(4, "}");
          auteurPageJs.tl(3, "});");
          auteurPageJs.tl(3, "const drawnItems = new L.FeatureGroup();");
          auteurPageJs.tl(3, "window.map", classeNomSimple, ".addLayer(drawnItems);");
          auteurPageJs.tl(3, "const drawControl = new L.Control.Draw({");
          auteurPageJs.tl(4, "position: 'topright'");
          auteurPageJs.tl(4, ", edit: {");
          auteurPageJs.tl(5, "featureGroup: drawnItems");
          auteurPageJs.tl(4, "}");
          auteurPageJs.tl(4, ", draw: {");
          auteurPageJs.tl(5, "polygon: true");
          auteurPageJs.tl(5, ", polyline: true");
          auteurPageJs.tl(5, ", rectangle: true");
          auteurPageJs.tl(5, ", circle: true");
          auteurPageJs.tl(5, ", marker: true");
          auteurPageJs.tl(4, "}");
          auteurPageJs.tl(3, "});");
          auteurPageJs.tl(3, "window.map", classeNomSimple, ".addControl(drawControl);");
          auteurPageJs.tl(3, "window.map", classeNomSimple, ".on(L.Draw.Event.CREATED, function (event) {");
          auteurPageJs.tl(4, "drawnItems.addLayer(event.layer);");

          auteurPageJs.tl(4, "var contextmenuItems = [];");
          if(classeVarEmplacement != null) {
            auteurPageJs.tl(4, "if(event.layerType == 'marker') {");
            auteurPageJs.tl(5, "contextmenuItems.push({");
            auteurPageJs.tl(6, "text: '", i18nPage.getString(I18n.str_Definir), " ", classeVarEmplacement, " ", i18nPage.getString(I18n.str_de), " ' + ", varResultat, ".", classeVarTitre);
            auteurPageJs.tl(6, ", callback: function(event2) {");
            auteurPageJs.tl(7, "patch", classeNomSimple, i18nClasse.getString(I18n.var_Emplacement), "(event.layer, "
                , "{ coordinates: [event.layer.getLatLng()['lng'], event.layer.getLatLng()['lat']], type: \"Point\" }"
                , ");");
            auteurPageJs.tl(6, "}");
            auteurPageJs.tl(5, "});");
            auteurPageJs.tl(4, "}");
          }
          if(classeVarAire != null) {
            auteurPageJs.tl(4, "if(event.layerType == 'polygon') {");
            auteurPageJs.tl(5, "contextmenuItems.push({");
            auteurPageJs.tl(6, "text: '", i18nPage.getString(I18n.str_Definir), " ", classeVarAire, " ", i18nPage.getString(I18n.str_de), " ' + ", varResultat, ".", classeVarTitre);
            auteurPageJs.tl(6, ", callback: function(event2) {");
            auteurPageJs.tl(7, "var latLngs = [];");
            auteurPageJs.tl(7, "event.layer.getLatLngs().forEach(ll1 => {");
            auteurPageJs.tl(8, "var latLngs1 = [];");
            auteurPageJs.tl(8, "ll1.forEach(ll2 => {");
            auteurPageJs.tl(9, "var latLngs2 = [ll2['lng'], ll2['lat']];");
            auteurPageJs.tl(9, "latLngs1.push(latLngs2);");
            auteurPageJs.tl(8, "});");
            auteurPageJs.tl(8, "latLngs.push(latLngs1);");
            auteurPageJs.tl(7, "});");
            auteurPageJs.tl(7, "patch", classeNomSimple, i18nClasse.getString(I18n.var_Aire), "(event.layer, "
                , "{ coordinates: latLngs, type: \"Polygon\" }"
                , ");");
            auteurPageJs.tl(6, "}");
            auteurPageJs.tl(5, "});");
            auteurPageJs.tl(4, "}");
            auteurPageJs.tl(4, "if(event.layerType == 'polyline') {");
            auteurPageJs.tl(5, "contextmenuItems.push({");
            auteurPageJs.tl(6, "text: '", i18nPage.getString(I18n.str_Definir), " ", classeVarAire, " ", i18nPage.getString(I18n.str_de), " ' + ", varResultat, ".", classeVarTitre);
            auteurPageJs.tl(6, ", callback: function(event2) {");
            auteurPageJs.tl(7, "var latLngs = [];");
            auteurPageJs.tl(7, "event.layer.getLatLngs().forEach(ll1 => {");
            auteurPageJs.tl(8, "var latLngs1 = [ll1['lng'], ll1['lat']];");
            auteurPageJs.tl(8, "latLngs.push(latLngs1);");
            auteurPageJs.tl(7, "});");
            auteurPageJs.tl(7, "patch", classeNomSimple, i18nClasse.getString(I18n.var_Aire), "(event.layer, "
                , "{ coordinates: latLngs, type: \"LineString\" }"
                , ");");
            auteurPageJs.tl(6, "}");
            auteurPageJs.tl(5, "});");
            auteurPageJs.tl(4, "}");
          }
          auteurPageJs.tl(4, "event.layer.bindContextMenu({");
          auteurPageJs.tl(5, "contextmenu: true");
          auteurPageJs.tl(5, ", contextmenuItems: contextmenuItems");
          auteurPageJs.tl(4, "});");
          auteurPageJs.tl(3, "});");
          auteurPageJs.tl(2, "}");
        }
        auteurPageJs.tl(1, "}");
        auteurPageJs.tl(0, "}");
        if(classeVarEmplacement != null) {
          auteurPageJs.tl(0, "function patch", classeNomSimple, i18nClasse.getString(I18n.var_Emplacement), "(target, ", classeVarEmplacement, ") {");
          auteurPageJs.tl(1, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeVarId, ":' + ", varResultat, ".", classeVarId, " }]");
          auteurPageJs.tl(3, ", 'set", StringUtils.capitalize(classeVarEmplacement), "', ", classeVarEmplacement);
          auteurPageJs.tl(3, ", target");
          auteurPageJs.tl(3, ", function(", i18nClasse.getString(I18n.var_reponse), ", e) { ", i18nClasse.getString(I18n.var_ajouterLueur), "(target); }");
          auteurPageJs.tl(3, ", function(", i18nClasse.getString(I18n.var_reponse), ", e) { ", i18nClasse.getString(I18n.var_ajouterErreur), "(target); }");
          auteurPageJs.tl(3, ");");
          auteurPageJs.tl(0, "}");
        }
        if(classeVarAire != null) {
          auteurPageJs.tl(0, "function patch", classeNomSimple, i18nClasse.getString(I18n.var_Aire), "(target, ", classeVarAire, ") {");
          auteurPageJs.tl(1, "patch", classeNomSimple, "Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: '", classeVarId, ":' + ", varResultat, ".", classeVarId, " }]");
          auteurPageJs.tl(3, ", 'set", StringUtils.capitalize(classeVarAire), "', ", classeVarAire);
          auteurPageJs.tl(3, ", target");
          auteurPageJs.tl(3, ", function(", i18nClasse.getString(I18n.var_reponse), ", e) { ", i18nClasse.getString(I18n.var_ajouterLueur), "(target); }");
          auteurPageJs.tl(3, ", function(", i18nClasse.getString(I18n.var_reponse), ", e) { ", i18nClasse.getString(I18n.var_ajouterErreur), "(target); }");
          auteurPageJs.tl(3, ");");
          auteurPageJs.tl(0, "}");
        }
        auteurPageJs.l();
        auteurPageJs.tl(0, "function animate", i18nClasse.getString(I18n.var_Stats), "() {");
        auteurPageJs.tl(1, "document.querySelector('#pageSearchVal-fq", classeNomSimple, "_time').innerText = '';");
        auteurPageJs.tl(1, "searchPage('", classeNomSimple, "', function() {");
        auteurPageJs.tl(2, "let speedRate = parseFloat(document.querySelector('#animate", i18nClasse.getString(I18n.var_Stats), "Speed')?.value) * 1000;");
        auteurPageJs.tl(2, "let xStep = parseFloat(document.querySelector('#animate", i18nClasse.getString(I18n.var_Stats), "Step')?.value);");
        auteurPageJs.tl(2, "let xMin = parseFloat(document.querySelector('#animate", i18nClasse.getString(I18n.var_Stats), "Min')?.value);");
        auteurPageJs.tl(2, "let xMax = parseFloat(document.querySelector('#animate", i18nClasse.getString(I18n.var_Stats), "Max')?.value);");
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

      auteurPageJsEdition.tl(0, "Promise.all([");
      auteurPageJsEdition.tl(2, "customElements.whenDefined('", composantsWebPrefixe, "button')");
      auteurPageJsEdition.tl(2, ", customElements.whenDefined('", composantsWebPrefixe, "input')");
      auteurPageJsEdition.tl(2, ", customElements.whenDefined('", composantsWebPrefixe, "select')");
      auteurPageJsEdition.tl(2, ", customElements.whenDefined('", composantsWebPrefixe, "radio')");
      auteurPageJsEdition.tl(2, ", customElements.whenDefined('", composantsWebPrefixe, "checkbox')");
      auteurPageJsEdition.tl(2, ", customElements.whenDefined('", composantsWebPrefixe, "dropdown')");
      auteurPageJsEdition.tl(2, "]).then(() => {");

      auteurPageJsEdition.s(wJsEditionInit);

      auteurPageJsRecherche.s(wJsRechercheInit);
      auteurPageJsRecherche.tl(0, "});");
      auteurPageJsEdition.tl(0, "});");
    }
  }

  public void ecrirePageRechercheSuggere(String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {
    ToutEcrivain oAncien = o;
    o = auteurRechercheSuggereJinja;

    if(!classePageSimple) {
      tl(0, "{%- block htm", i18nClasse.getString(I18n.var_Suggere), classePageNomSimple, " %}");


      tl(4, "<div>");
      tl(5, "<div>");
      tl(6, "<span>");
      tl(7, i18nClasse.getString(I18n.var_rechercher), " ", classeNomAdjectifPluriel, i18nPage.getString(I18n.str_deuxPoints));
      tl(6, "</span>");
      tl(5, "</div>");
      tl(4, "</div>");
      tl(4, "<div>");

      tl(5, "<", composantsWebPrefixe, "input");
      tl(7, "type=\"text\"");

      if(classeRechercherTousNom != null) {
        tl(7, "placeholder=\"", classeRechercherTousNom, "\"");
      }

      tl(7, "class=\"", i18nClasse.getString(I18n.var_suggere), classeApiClasseNomSimple, " \"");
      tl(7, "name=\"", i18nClasse.getString(I18n.var_suggere), classeApiClasseNomSimple, "\"");
      tl(7, "id=\"", i18nClasse.getString(I18n.var_suggere), classeApiClasseNomSimple, "{{ ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " | e }}\"");
      tl(7, "autocomplete=\"off\"");
      tl(7, "oninput=\"", i18nClasse.getString(I18n.var_suggere), classeApiClasseNomSimple, StringUtils.capitalize(classeVarSuggere), "( [ { 'name': 'q', 'value': '", classeVarSuggere, ":' + this.value ? this.value : '' }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", i18nClasse.getString(I18n.var_classeNomCanonique), ",", classeVarClePrimaire, classeVarUrlPageEdition == null ? "" : "," + classeVarUrlPageEdition, classeVarTitre == null ? "" : "," + classeVarTitre, "' } ], document.querySelector('#", i18nClasse.getString(I18n.var_suggere), "List", classeApiClasseNomSimple, "{{ ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " | e }}'), {{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}; \"");
      tl(7, "onkeyup=\"if (event.keyCode === 13) { event.preventDefault(); window.location.href = '", classePageUriMethode + "?q={{ query1 }}:' + encodeURIComponent(this.value) + '{{fqs}}{{sorts}}&amp;rows={{start2}}&amp;rows={{rows1}}\"");
      tl(1, "{% if ", i18nClasse.getString(I18n.var_liste), classeApiClasseNomSimple, " is defined %}");
      tl(7, "value=\"{{query2}}\"");
      tl(1, "{% endif %}");
      tl(7, ">");
      tl(5, "</", composantsWebPrefixe, "input>");
      tl(5, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", "");
      tl(7, "onclick=\"window.location.href = '", classePageUriMethode + "?q=&quot;, query1, &quot;:' + encodeURIComponent(this.previousElementSibling.value) + '&quot;, fqs, sorts, &quot;&amp;rows=&quot;, start2, &quot;&amp;rows=&quot;, rows1, &quot;'; \"");
      tl(7, ">");
      tl(6, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-search \"></i>");
      tl(5, "</", composantsWebPrefixe, "button>");

      tl(5, "<div>");
      tl(6, "<div>");
      tl(7, "<div>");
      tl(8, "<ul id=\"", i18nClasse.getString(I18n.var_suggere), "List", classeApiClasseNomSimple, "{{ ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " | e }}\">");
      tl(8, "</ul>");
      tl(7, "</div>");
      tl(6, "</div>");
      tl(5, "</div>");

      // voir tous //
      tl(5, "<div class=\"\">");
      tl(6, "<a href=\"", classePageUriMethode, "\" class=\"\">");
      if(classeIcone != null)
        tl(7, classeIcone);
      tl(7, i18nClasse.getString(I18n.var_voir), " ", classeTousNom);
      tl(6, "</a>");
      tl(5, "</div>");
      tl(4, "</div>");

      tl(0, "{%- endblock htm", i18nClasse.getString(I18n.var_Suggere), classePageNomSimple, " %}");
    }

    o = oAncien;
  }

  public void ecrirePageFormulaireRecherche(String langueNomApi, String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {
    ToutEcrivain oAncien = o;
    o = auteurMacrosFormulaireRechercheJinja;

    if(auteurMacrosFormulaireRechercheJinja != null) {
      List<String> classeApiMethodes = Optional.ofNullable(classeDoc.getJsonArray("classeApiMethodes_" + langueNomApi + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());

      if(classeApiMethodes == null)
        classeApiMethodes = new ArrayList<>();
      for(String classeApiMethode : classeApiMethodes) {
        String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNomApi + "_stored_string");
        String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNomApi + "_stored_string");
        String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNomApi + "_stored_string");
        String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNomApi + "_stored_string");

        if(classeApiMethode.contains(i18nClasse.getString(I18n.var_PageRecherche)) || classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals("DELETE") || classeApiMethode.equals(i18nClasse.getString(I18n.var_DELETEFiltre)) || classeApiMethode.equals(i18nClasse.getString(I18n.var_PUTCopie)) || classeApiMethode.equals(i18nClasse.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
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
          else if(i18nClasse.getString(I18n.var_PUTFusion).equals(classeApiMethode)) {
            methodeTitreCourt = i18nPage.getString(I18n.str_Fusionner);
            methodeTitreFiltres = i18nPage.getString(I18n.str_Fusionner_) + classeUnNom;
            methodeTitreValeurs = i18nPage.getString(I18n.str_Fusionner_) + classeNomPluriel;
          }
          else if(i18nClasse.getString(I18n.var_PUTCopie).equals(classeApiMethode)) {
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
          else if(classeApiMethode.equals(i18nClasse.getString(I18n.var_DELETEFiltre))) {
            methodeTitreCourt = i18nPage.getString(I18n.str_Supprimer);
            methodeTitreFiltres = i18nPage.getString(I18n.str_Supprimer_) + classeNomPluriel;
            methodeTitreValeurs = i18nPage.getString(I18n.str_Supprimer_) + classeNomPluriel;
          }
          else {
            methodeTitreCourt = i18nPage.getString(I18n.str_Rechercher);
            methodeTitreFiltres = i18nPage.getString(I18n.str_Rechercher_) + classeUnNom;
            methodeTitreValeurs = i18nPage.getString(I18n.str_Rechercher_) + classeNomPluriel;
          }

          if(!"GET".equals(classeApiMethodeMethode)) {
            o = auteurBoutonsFormulaireRechercheJinja;
            l();
            s("{%- macro htm", i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() %}");
            if(!classeApiMethode.contains(i18nClasse.getString(I18n.var_PageRecherche))) {
              s("<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", "");
              s(" id=\"htm", i18nGlobale.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "\"");
              if(!classeApiMethode.equals("DELETE") && !classeApiMethode.equals(i18nClasse.getString(I18n.var_DELETEFiltre))) {
              s("  data-dialog=\"open ", classeApiOperationIdMethode, i18nGlobale.getString(I18n.var_Dialogue), "\"");
              }
              s(" data-", classeVarId, "={{ ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " | tojson }}");
              s(">");
  
                if(classeApiMethodeMethode.contains("POST"))
                  s("<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-file-plus \"></i>");
                else if(classeApiMethodeMethode.contains("PATCH"))
                  s("<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-edit \"></i>");
                else if(classeApiMethodeMethode.contains("DELETE"))
                  s("<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-trash \"></i>");
                else if(classeApiMethode.contains("PUTImport"))
                  s("<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-file-import \"></i>");
                else if(classeApiMethode.contains(i18nClasse.getString(I18n.var_PUTFusion)))
                  s("<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-code-merge \"></i>");
                else if(classeApiMethode.contains(i18nClasse.getString(I18n.var_PUTCopie)))
                  s("<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-copy \"></i>");
  
                s(methodeTitreCourt);
              s("</", composantsWebPrefixe, "button>");
            }
            l("{%- endmacro %}");

            o = auteurMacrosFormulaireRechercheJinja;
            l();
            l("{%- macro htm", i18nClasse.getString(I18n.var_Formulaire), i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() %}");
            tl(5, "<", composantsWebPrefixe, "popup  auto-size=\"both\" placement=\"top\" id=\"alert", i18nGlobale.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "\" duration=\"5000\" closable class=\"", composantsWebPrefixe, "header-l \">");
            tl(6, "<", composantsWebPrefixe, "button slot=\"anchor\"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " slot=\"footer\" type=\"submit\"");
            tl(8, " id=\"htm", i18nClasse.getString(I18n.var_Formulaire), i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "\"");
            tl(8, ">", methodeTitreValeurs, "</", composantsWebPrefixe, "button>");
            tl(6, "<", composantsWebPrefixe, "badge class=\"alertPopup\" id=\"alertPopup", i18nGlobale.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "\"></", composantsWebPrefixe, "badge>");
            tl(5, "</", composantsWebPrefixe, "popup>");
            l("{%- endmacro %}");
          }

          l();
          l("{%- macro htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() %}");
          { tl(4, "<", classeApiMethode.contains(i18nClasse.getString(I18n.var_PageRecherche)) ? "div" : composantsWebPrefixe + "dialog", "wa-".equals(composantsWebPrefixe) ? " with-header with-footer style=\"--width: 50vw; \"" : "", " id=\"", classeApiOperationIdMethode, i18nClasse.getString(I18n.var_Dialogue), "\" label=\"", methodeTitreValeurs, "\">");
            { tl(5, "<", classeApiMethode.contains(i18nClasse.getString(I18n.var_PageRecherche)) ? "div" : "form", " id=\"htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "\" class=\"round-row primary-smart-border-radius \">");
              if(!i18nClasse.getString(I18n.var_PageRecherche).equals(classeApiMethode)) {
                if("POST".equals(classeApiMethode)) {
                  tl(6, "<div>", i18nPage.getString(I18n.str_Vous_pouvez_remplacer_les_valeurs_par_defaut_ci_dessous), "</div>");
                }
              }
  
              if("PATCH".equals(classeApiMethode) || i18nClasse.getString(I18n.var_PUTFusion).equals(classeApiMethode) || i18nClasse.getString(I18n.var_PUTCopie).equals(classeApiMethode) || "PUTImport".equals(classeApiMethode)) {
  
                if("PUTImport".equals(classeApiMethode)) {
                  tl(6, "<div>");
                  tl(7, "<", composantsWebPrefixe, "textarea");
                  tl(9, "class=\"", "PUTImport_", i18nClasse.getString(I18n.var_listeRecherche), " \"");
                  tl(9, "style=\"height: 300px; \"");
                  tl(9, "placeholder=\"{ '", i18nClasse.getString(I18n.var_listeRecherche), "': [ { '", classeVarId, "': ... , '", i18nClasse.getString(I18n.var_sauvegardes), "': [ ... ] }, ... ] }\"");
                  tl(9, ">");
                  tl(7, "</", composantsWebPrefixe, "textarea>");
                  tl(6, "</div>");
                } else if(i18nClasse.getString(I18n.var_PUTFusion).equals(classeApiMethode)) {
                  tl(6, "<div>");
                  tl(7, "<", composantsWebPrefixe, "textarea");
                  tl(9, "class=\"", "PUT", i18nClasse.getString(I18n.var_PUTFusion), "_", i18nClasse.getString(I18n.var_listeRecherche), " \"");
                  tl(9, "style=\"height: 300px; \"");
                  tl(9, "placeholder=\"{ '", i18nClasse.getString(I18n.var_listeRecherche), "': [ { '", classeVarId, "': ... , '", i18nClasse.getString(I18n.var_sauvegardes), "': [ ... ] }, ... ] }\"");
                  tl(9, ">");
                  tl(7, "</", composantsWebPrefixe, "textarea>");
                  tl(6, "</div>");
                } else if(i18nClasse.getString(I18n.var_PUTCopie).equals(classeApiMethode)) {
                  s(wFormPUTCopie);
                } else if("PATCH".equals(classeApiMethodeMethode)) {
                  s(wFormPATCH);
                }
  
              } else {
                if("POST".equals(classeApiMethode)) {
                  s(wFormPOST);
                } else if(i18nClasse.getString(I18n.var_Recherche).equals(classeApiMethode)) {
                  s(wFormRecherche);
                } if(classeApiMethode.contains(i18nClasse.getString(I18n.var_PageRecherche))) {
                  s(wFormPage);
                }
              }
            } tl(5, "</", classeApiMethode.contains(i18nClasse.getString(I18n.var_PageRecherche)) ? "div" : "form", ">");
            if(!classeApiMethode.contains(i18nClasse.getString(I18n.var_PageRecherche))) {
              tl(5, "{{ htm", i18nClasse.getString(I18n.var_Formulaire), i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
            }
          } tl(4, "</", classeApiMethode.contains(i18nClasse.getString(I18n.var_PageRecherche)) ? "div" : composantsWebPrefixe + "dialog", ">");
          l("{%- endmacro %}");
        }
      }

      o = auteurFormulaireRechercheJinja;
      tl(0, "{% include ", classePageMacrosFormulaireRechercheTemplate, " %}");
      for(String uriMacros : classeAttribuerPageUriMacros) {
        tl(0, "{% include ", uriMacros, " %}");
      }
      for(String classeApiMethode : classeApiMethodes) {
        String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNomApi + "_stored_string");
        String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNomApi + "_stored_string");
        String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNomApi + "_stored_string");
        String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNomApi + "_stored_string");

        if(classeApiMethode.equals("POST")) {
          tl(0, "{%- if 'POST' in ", i18nGlobale.getString(I18n.var_portees), " %}");
          tl(1, "{%- if ", varResultat, "Count >= 1 %}");
          tl(2, "{{ htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
          tl(1, "{%- else %}");
          tl(2, "{{ htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
          tl(1, "{%- endif %}");
          tl(0, "{%- endif %}");
        } else if(classeApiMethode.equals("DELETE") || classeApiMethode.equals(i18nClasse.getString(I18n.var_DELETEFiltre))) {
          tl(0, "{%- if 'DELETE' in ", i18nGlobale.getString(I18n.var_portees), " %}");
          tl(1, "{%- if ", varResultat, "Count >= 1 %}");
          tl(2, "{{ htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
          tl(1, "{%- else %}");
          tl(1, "{%- endif %}");
          tl(0, "{%- endif %}");
        } else if(classeApiMethode.equals("PATCH")) {
          tl(0, "{%- if 'DELETE' in ", i18nGlobale.getString(I18n.var_portees), " %}");
          tl(2, "{{ htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
          tl(1, "{%- endif %}");
          tl(0, "{%- endif %}");
        } else if(classeApiMethode.equals(i18nClasse.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
          tl(0, "{%- if ", i18nClasse.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
          tl(1, "{{ htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
          tl(0, "{%- endif %}");
        } else if(classeApiMethode.equals(i18nClasse.getString(I18n.var_PUTCopie))) {
          tl(0, "{%- if ", i18nClasse.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
          tl(0, "{{ htm", i18nClasse.getString(I18n.var_Formulaire), "_", classeApiOperationIdMethode, "() }}");
          tl(0, "{%- endif %}");
        }
      }

      o = auteurBoutonsFormulaireRechercheJinja;

      // formulaires //
      if(auteurBoutonsFormulaireRechercheJinja != null) {
        tl(5, "<div id=\"htm", i18nClasse.getString(I18n.var_FormulaireRecherche), "\">");
        tl(6, "<", composantsWebPrefixe, "scroller>");
        tl(7, "<", composantsWebPrefixe, "button-group>");

        tl(0, "{% if ", i18nClasse.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
        tl(1, "{% if \"PATCH\" in ", i18nGlobale.getString(I18n.var_portees), " %}");

        // recharger 1 //
        tl(2, "{% if ", varResultat, "Count >= 1 %}");
        // s("<", composantsWebPrefixe, "tooltip content=\"", i18nPage.getString(I18n.str_Recharger), " ", classeCeNom, "\">");
        tl(8, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " id=\"", i18nClasse.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeCe)), classeGenPageNomSimple, "\"");
        tl(10, " onclick=\"patch{{", i18nClasse.getString(I18n.var_classeNomSimple), "}}Vals( [ {name: 'fq', value: '", classeVarId, ":{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}' } ], {}, this, function() { ", i18nClasse.getString(I18n.var_ajouterLueur), "(document.querySelector('#", i18nClasse.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeCe)), classeGenPageNomSimple, "')); }, function() { ", i18nClasse.getString(I18n.var_ajouterErreur), "(document.querySelector('#", i18nClasse.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeCe)), classeGenPageNomSimple, "')); }); return false; \">");
        tl(9, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-sync-alt \"></i>");
        tl(9, i18nPage.getString(I18n.str_recharger));
        tl(8, "</", composantsWebPrefixe, "button>");
        // tl(9, "</", composantsWebPrefixe, "tooltip>");
        tl(2, "{% endif %}");

        // recharger tous //
        tl(2, "{% if ", varResultat, "Count > 0 %}");
        // tl(9, "<", composantsWebPrefixe, "tooltip content=\"", i18nPage.getString(I18n.str_Recharger), " ", classeTousNom, "\">");
        tl(8, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " id=\"", i18nClasse.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeTous)), classeGenPageNomSimple, "{{ ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " | e }}\"");
        tl(10, " onclick=\"patch{{", i18nClasse.getString(I18n.var_classeNomSimple), "}}Vals([], {}, this, function() { ", i18nClasse.getString(I18n.var_ajouterLueur), "(document.querySelector('#", i18nClasse.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeTous)), classeGenPageNomSimple, "{{ ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " | e }}')); }, function() { ", i18nClasse.getString(I18n.var_ajouterErreur), "(document.querySelector('#", i18nClasse.getString(I18n.var_recharger), StringUtils.trim(StringUtils.capitalize(classeTous)), classeGenPageNomSimple, "{{ ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " | e }}')); }); \"");
        tl(10, ">");
        tl(9, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-arrows-rotate\"></i>");
        tl(9, i18nPage.getString(I18n.str_recharger), " ", classeTous);
        tl(8, "</", composantsWebPrefixe, "button>");
        // tl(9, "</", composantsWebPrefixe, "tooltip>");
        tl(2, "{% endif %}");

        tl(1, "{% endif %}");
        tl(0, "{% endif %}");

        // formulaires //

        for(String classeApiMethode : classeApiMethodes) {
          String classeApiOperationIdMethode = classeDoc.getString("classeApiOperationId" + classeApiMethode + "_" + langueNomApi + "_stored_string");
          String classeApiUriMethode = classeDoc.getString("classeApiUri" + classeApiMethode + "_" + langueNomApi + "_stored_string");
          String classeApiTypeMediaMethode = classeDoc.getString("classeApiTypeMedia200" + classeApiMethode + "_" + langueNomApi + "_stored_string");
          String classeApiMethodeMethode = classeDoc.getString("classeApiMethode" + classeApiMethode + "_" + langueNomApi + "_stored_string");

          if(classeApiMethode.equals("POST")) {
            tl(0, "{%- if 'POST' in ", i18nGlobale.getString(I18n.var_portees), " %}");
            tl(1, "{%- if ", varResultat, "Count >= 1 %}");
            tl(2, "{{ htm", i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
            tl(1, "{%- else %}");
            tl(2, "{{ htm", i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
            tl(1, "{%- endif %}");
            tl(0, "{%- endif %}");
          } else if(classeApiMethode.equals("DELETE") || classeApiMethode.equals(i18nClasse.getString(I18n.var_DELETEFiltre))) {
            tl(0, "{%- if 'DELETE' in ", i18nGlobale.getString(I18n.var_portees), " %}");
            if(classeApiMethode.equals(i18nClasse.getString(I18n.var_DELETEFiltre))) {
              tl(1, "{%- if ", varResultat, " is not defined %}");
              tl(2, "{%- if ", i18nClasse.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
              tl(2, "{{ htm", i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
              tl(2, "{%- endif %}");
              tl(1, "{%- endif %}");
            } else {
              tl(1, "{%- if ", varResultat, " is defined %}");
              tl(2, "{{ htm", i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
              tl(1, "{%- endif %}");
            }
            tl(0, "{%- endif %}");
          } else if(classeApiMethode.equals("PATCH")) {
            tl(0, "{%- if 'PATCH' in ", i18nGlobale.getString(I18n.var_portees), " %}");
            tl(1, "{%- if ", varResultat, "Count > 0 %}");
            tl(2, "{%- if ", i18nClasse.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
            tl(2, "{{ htm", i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
            tl(2, "{%- endif %}");
            tl(1, "{%- endif %}");
            tl(0, "{%- endif %}");
          } else if(classeApiMethode.equals(i18nClasse.getString(I18n.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
            tl(0, "{%- if ", i18nClasse.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
            tl(1, "{{ htm", i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
            tl(0, "{%- endif %}");
          } else if(classeApiMethode.equals(i18nClasse.getString(I18n.var_PUTCopie))) {
            tl(0, "{%- if ", i18nClasse.getString(I18n.var_AUTH_PORTEE_SUPER_ADMIN), " in ", i18nGlobale.getString(I18n.var_portees), " %}");
            tl(0, "{{ htm", i18nClasse.getString(I18n.var_Bouton), "_", classeApiOperationIdMethode, "() }}");
            tl(0, "{%- endif %}");
          }
        }
        l();
        tl(7, "</", composantsWebPrefixe, "button-group>");
        tl(6, "</", composantsWebPrefixe, "scroller>");
        tl(5, "</div>");
      }
    }

    o = oAncien;
  }

  public void ecrirePageEmplacement(String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {
    ToutEcrivain oAncien = o;
    o = auteurEmplacementJinja;

    if(auteurEmplacementJinja != null && (classeVarEmplacement != null || classeVarAire != null)) {
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Emplacement), "", classePageNomSimple, " %}");
      tl(4, "<", composantsWebPrefixe, "details open>");
      tl(5, "<div slot=\"summary\">", String.format(i18nPage.getString(I18n.str_Cartes_des), classeNomAdjectifPluriel), "</div>");

      /////////////////////////////////
      // htmBodyGraphiqueEmplacement //
      /////////////////////////////////

      l();
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Graphique), i18nClasse.getString(I18n.var_Emplacement), classePageNomSimple, " %}");
      tl(0, "{% if ", varResultat, "Count > 0 %}");
      tl(4, "<div id=\"htmBody", i18nClasse.getString(I18n.var_Graphique), i18nClasse.getString(I18n.var_Emplacement), classePageNomSimple, "\" class=\"htmBody", i18nClasse.getString(I18n.var_Graphique), i18nClasse.getString(I18n.var_Emplacement), " \"></div>");
      tl(0, "{% endif %}");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Graphique), i18nClasse.getString(I18n.var_Emplacement), classePageNomSimple, " %}");

      //////////////////////
      // htmBodyGraphique //
      //////////////////////

      l();
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Graphique), classePageNomSimple, " %}");
      tl(4, "<div id=\"htmBody", i18nClasse.getString(I18n.var_Graphique), classePageNomSimple, "\" class=\"htmBody", i18nClasse.getString(I18n.var_Graphique), " \"></div>");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Graphique), classePageNomSimple, " %}");

      tl(4, "</", composantsWebPrefixe, "details>");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Emplacement), "", classePageNomSimple, " %}");
    }
  }

  public void ecrirePageBarreLaterale(String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {
    ToutEcrivain oAncien = o;
    o = auteurBarreLateraleJinja;

    /////////////////
    // pageContent //
    /////////////////

    if(auteurBarreLateraleJinja != null) {
      tl(4, "<div class=\"htmBody", i18nClasse.getString(I18n.var_BarreLaterale), "", classePageNomSimple, " \">");

      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_BarreLaterale), "", classePageNomSimple, " %}");

      /////////////
      // sidebar //
      /////////////

      ///////////////
      // sidebar q //
      ///////////////

      tl(5, "<", composantsWebPrefixe, "drawer with-header light-dismiss placement=\"end\" id=\"site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Recherche), "\">");
      tl(6, "<div slot=\"label\">");
      tl(7, "<i class=\"{{ FONTAWESOME_STYLE }} fa-magnifying-glass \"></i>");
      tl(7, i18nPage.getString(I18n.str_recherche));
      tl(6, "</div>");
      tl(6, "<div>");

      //////////////////////
      // htmBodyRecherche //
      //////////////////////

      tl(7, "{%- block htmBody", i18nClasse.getString(I18n.var_Recherche), classePageNomSimple, " %}");
      tl(7, "<div>");
      tl(8, "{% for key, value in varsQ.items() %}");
      tl(8, "<div>");
      tl(9, "<div>");
      t(10, "<label for=\"q", classeNomSimple, "_{{ key }}\">");
      s("{{ value.", i18nClasse.getString(I18n.var_nomAffichage), " }}");
      s("<sup> ({{ value.", i18nClasse.getString(I18n.var_classeNomSimple), " }})</sup>");
      l("</label>");
      tl(9, "</div>");
      tl(8, "</div>");
      tl(8, "<div>");
      tl(9, "<div>");
      t(10, "<", composantsWebPrefixe, "input");
      s(" id=\"q", classeNomSimple, "_{{ key }}\"");
      s(" placeholder=\"{{ displayName }}\"");
      s(" data-var=\"{{ key }}\"");
      s(" autocomplete=\"off=\"");
      l("></", composantsWebPrefixe, "input>");
      tl(10, "<div id=\"q", classeNomSimple, "Div_{{ key }}\" class=\"pageSearchVal \"></div>");
      tl(9, "</div>");
      tl(8, "</div>");
      tl(8, "{% endfor %}");

      ///////////
      // start //
      ///////////

      tl(8, "<div>");
      tl(9, "<div>");
      t(10, "<label for=\"q", classeNomSimple, "_", i18nClasse.getString(I18n.var_Debut), "\">");
      s("", i18nClasse.getString(I18n.var_debut), "");
      s("<sup> (Long)</sup>");
      l("</label>");
      tl(9, "</div>");
      tl(8, "</div>");
      tl(8, "<div>");
      tl(9, "<div>");
      t(10, "<", composantsWebPrefixe, "input");
      s(" id=\"q", classeNomSimple, "_", i18nClasse.getString(I18n.var_debut), "\"");
      s(" placeholder=\"{{ displayName }}\"");
      s(" class=\"\"");
      s(" data-var=\"start\"");
      s(" autocomplete=\"off=\"");
      s(" value=\"{{ start }}\"");
      l("></", composantsWebPrefixe, "input>");
      tl(10, "<div id=\"q", classeNomSimple, "Div_", i18nClasse.getString(I18n.var_debut), "\" class=\"pageSearchVal \">start={{ start }}</div>");
      tl(9, "</div>");
      tl(8, "</div>");

      //////////
      // rows //
      //////////

      tl(8, "<div>");
      tl(9, "<div>");
      t(10, "<label for=\"q", classeNomSimple, "_", i18nClasse.getString(I18n.var_Lignes), "\">");
      s("", i18nClasse.getString(I18n.var_lignes), "");
      s("<sup> (Long)</sup>");
      l("</label>");
      tl(9, "</div>");
      tl(8, "</div>");
      tl(8, "<div>");
      tl(9, "<div>");
      t(10, "<", composantsWebPrefixe, "input");
      s(" id=\"q", classeNomSimple, "_", i18nClasse.getString(I18n.var_lignes), "\"");
      s(" placeholder=\"{{ displayName }}\"");
      s(" class=\"\"");
      s(" data-var=\"rows\"");
      s(" autocomplete=\"off\"");
      s(" value=\"{{ rows }}\"");
      l("></", composantsWebPrefixe, "input>");
      tl(10, "<div id=\"q", classeNomSimple, "Div_", i18nClasse.getString(I18n.var_lignes), "\" class=\"pageSearchVal \">rows={{ rows }}</div>");
      tl(9, "</div>");
      tl(8, "</div>");

      tl(7, "</div>");
      tl(7, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Recherche), classePageNomSimple, " %}");
      tl(6, "</div>");
      tl(5, "</", composantsWebPrefixe, "drawer>");

      ////////////////
      // sidebar fq //
      ////////////////

      tl(5, "<", composantsWebPrefixe, "drawer with-header light-dismiss placement=\"end\" id=\"site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Filtres), "\">");
      tl(6, "<div slot=\"label\">");
      tl(7, "<span>");
      t(7, "<i class=\"{{ FONTAWESOME_STYLE }} fa-filters \"></i>");
      l(" ", i18nPage.getString(I18n.str_filtres), "</span>");
      tl(6, "</div>");
      tl(6, "<div>");

      ////////////////////
      // htmBodyFiltres //
      ////////////////////

      tl(7, "{%- block htmBody", i18nClasse.getString(I18n.var_Filtres), classePageNomSimple, " %}");
      tl(7, "<div class=\"flex flex-column gap-l \">");
      tl(8, "{% for key, value in varsFq.items() %}");
      tl(8, "<div>");
      tl(9, "<div>");
      t(10, "<label for=\"fq", classeNomSimple, "_{{ key }}\">");
      s("{{ value.", i18nClasse.getString(I18n.var_nomAffichage), " }}");
      s("<sup> ({{ value.", i18nClasse.getString(I18n.var_classeNomSimple), " }})</sup>");
      l("</label>");

        tl(10, "<", composantsWebPrefixe, "tooltip for=\"buttonFacet", classeNomSimple, "_{{ key }}\">", i18nPage.getString(I18n.str_voir_valeurs), "</", composantsWebPrefixe, "tooltip>");
      tl(10, "<div class=\"flex round-column pill-smart-border-radius \">");
      t(11, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", "");
      s(" id=\"buttonFacet", classeNomSimple, "_{{ key }}\"");
      s(" data-var=\"{{ value.var }}\"");
      s(" data-clear=\"{% if value.facetField is defined %}true{% else %}false{% endif %}\"");
      l("><i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-list \"></i></", composantsWebPrefixe, "button>");

      t(11, "<", composantsWebPrefixe, "input");
      s(" id=\"fq", classeNomSimple, "_{{ key }}\"");
      s(" placeholder=\"{{ value.displayName }}\"");
      s(" class=\"\"");

      s(" onchange=\"fqChange('", classeNomSimple, "', this, facetChange", classeNomSimple, "Success, facetChange", classeNomSimple, "Error); \"");

      s(" data-var=\"{{ value.var }}\"");
      s(" autocomplete=\"off=\"");
      s(" value=\"{{ value.val }}\"");
      l("></", composantsWebPrefixe, "input>");
      tl(10, "</div>");

      t(10, "<div");
      s(" class=\"pageSearchVal \"");
      s(" id=\"pageSearchVal-fq", classeNomSimple, "_{{ key }}\"");
      l(">{% if value.val is defined %}fq={{ value.var }}:{{ value.val | urlencode() }}{% endif %}</div>");
      t(10, "<div");
      s(" class=\"pageSearchVal \"");
      s(" id=\"pageSearchVal-buttonFacet", classeNomSimple, "_{{ key }}\"");
      l(">{% if value.facetField.var is defined %}facet.field={{ value.facetField.var }}{% endif %}</div>");

      tl(9, "</div>");
      tl(9, "<div>");

      t(10, "<div");
      s(" class=\"pageFacetField pageFacetField", classeNomSimple, "_{{ key }} \"");
      s(" id=\"pageFacetField", classeNomSimple, "_{{ key }}\"");
      l(">");
      tl(11, "{% for facetFieldKey, facetFieldValue in value.facetField.counts.items() %}");
      tl(11, "<div>");
      tl(12, "<", composantsWebPrefixe, "tooltip content=\"", String.format(i18nPage.getString(I18n.str_au_total_avec_), "{{ facetFieldValue | e }}", classeNomSingulier, "{{ value.facetField.var | e }}", "{{ facetFieldKey | e }}"), "\"></", composantsWebPrefixe, "tooltip>");
      t(12, "<div");
      s(" class=\"cursor-pointer raised-item \"");
      s(" data-class=\"", classeNomSimple, "\"");
      s(" data-var=\"{{ value.facetField.var }}\"");
      s(" data-val=\"{{ facetFieldKey }}\"");
      s(" onclick=\"if(document.querySelector('#fq", classeNomSimple, "_{{ key }}')?.value === '{{ facetFieldKey }}') this.setAttribute('data-val', ''); fqReplace('", classeNomSimple, "', this, facetChange", classeNomSimple, "Success, facetChange", classeNomSimple, "Error); \"");
      s(">");
      s("{{ facetFieldValue }}");
      s(": ");
      s("{{ facetFieldKey }}");
      l("</div>");
      tl(11, "</div>");
      tl(11, "{% endfor %}");
      tl(10, "</div>");

      tl(9, "</div>");
      tl(8, "</div>");
      tl(8, "{% endfor %}");
      tl(7, "</div>");
    
      tl(7, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Filtres), classePageNomSimple, " %}");
      tl(6, "</div>");
      tl(5, "</", composantsWebPrefixe, "drawer>");

      ////////////////
      // htmBodyTri //
      ////////////////

      //STUFF3
      tl(5, "<", composantsWebPrefixe, "drawer with-header light-dismiss placement=\"end\" id=\"site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Tri), "\">");
      tl(6, "<div slot=\"label\">");
      tl(7, "<i class=\"{{ FONTAWESOME_STYLE }} fa-arrow-down-a-z \"></i>");
      tl(7, i18nPage.getString(I18n.str_tri));
      tl(6, "</div>");
      tl(6, "<div>");

      tl(7, "{%- block htmBody", i18nClasse.getString(I18n.var_Tri), classePageNomSimple, " %}");

      // t(7, "<div");
      // s(" style=\"display: none; \"");
      // s(" id=\"pageSearchVal-page", i18nClasse.getString(I18n.var_Tri), "-", classeNomSimple, "Hidden\"");
      // l(">");
      // tl(7, "{% for key, value in varsFq.items() %}");
      // // tl(8, "{% for item in default", i18nClasse.getString(I18n.var_Tri), "Vars %}");
      // t(9, "<div");
      // s(" class=\"pageSearchVal-page", i18nClasse.getString(I18n.var_Tri), "-", classeNomSimple, "Hidden \"");
      // s(" id=\"pageSearchVal-page", i18nClasse.getString(I18n.var_Tri), "-", classeNomSimple, "Hidden_{{ key }}\"");
      // l(">{{ value }}</div>");
      // tl(8, "{% endfor %}");
      // tl(7, "</div>");

      t(7, "<div");
      s(" id=\"pageSearchVal-page", i18nClasse.getString(I18n.var_Tri), "-", classeNomSimple, "\"");
      l(">");
      // JS Tri //
      tl(7, "{% for key, value in varsFq.items() %}");
      tl(7, "{% if default", i18nClasse.getString(I18n.var_Tri), "Vars is defined and ((key + ' asc') in default", i18nClasse.getString(I18n.var_Tri), "Vars or (key + ' desc') in default", i18nClasse.getString(I18n.var_Tri), "Vars) %}");
      t(9, "<div");
      s(" class=\"pageSearchVal pageSearchVal-page", i18nClasse.getString(I18n.var_Tri), "-", classeNomSimple, " pageSearchVal-page", i18nClasse.getString(I18n.var_Tri), "-", classeNomSimple, "-{{ key }} \"");
      s(" id=\"pageSearchVal-page", i18nClasse.getString(I18n.var_Tri), "-", classeNomSimple, "-{{ key }}\"");
      s(">");
      // s("{% for item in default", i18nClasse.getString(I18n.var_Tri), "Vars %}");
      s("{% if default", i18nClasse.getString(I18n.var_Tri), "Vars is defined and (key + ' asc') in default", i18nClasse.getString(I18n.var_Tri), "Vars %}");
      s("sort={{ key }} asc");
      s("{% else %}");
      s("{% if default", i18nClasse.getString(I18n.var_Tri), "Vars is defined and (key + ' desc') in default", i18nClasse.getString(I18n.var_Tri), "Vars %}");
      s("sort={{ key }} desc");
      s("{% endif %}");
      s("{% endif %}");
      l("</div>");
      tl(7, "{% endif %}");
      tl(7, "{% endfor %}");
      tl(7, "</div>");

      tl(7, "<div>");
      tl(8, "{% for key, value in varsFq.items() %}");
      // JS Tri //
      tl(8, "<", composantsWebPrefixe, "details");
      tl(10, "summary=\"{{ value.", i18nClasse.getString(I18n.var_nomAffichage), " | e }}\"");
      tl(10, "class=\"pageDetails", i18nClasse.getString(I18n.var_Tri), " \"");
      tl(10, "id=\"pageDetails", i18nClasse.getString(I18n.var_Tri), classeNomSimple, "_{{ key }}\"");
      tl(12, "data-value=\"{{ value.var | e }}\"");
      tl(12, "{% if value.", i18nClasse.getString(I18n.var_tri), " is defined %}open{% endif %}");
      tl(10, ">");
      tl(9, "<", composantsWebPrefixe, "select with-clear");
      tl(11, "orientation=\"horizontal\"");
      tl(11, "class=\"pageSelect", i18nClasse.getString(I18n.var_Tri), " \"");
      tl(11, "id=\"pageSelect", i18nClasse.getString(I18n.var_Tri), classeNomSimple, "_{{ key }}\"");
      tl(11, "{% if value.", i18nClasse.getString(I18n.var_tri), " is defined %}value=\"{{ value.", i18nClasse.getString(I18n.var_tri), " | e }}\"{% endif %}");
      tl(11, "label={{ value.", i18nClasse.getString(I18n.var_nomAffichage), " | tojson }}");
      tl(11, "size=\"small\"");
      tl(11, ">");
      t(10, "<", composantsWebPrefixe, "option value=\"asc\">");
      s(i18nPage.getString(I18n.str_croissante));
      l("</", composantsWebPrefixe, "option>");
      t(10, "<", composantsWebPrefixe, "option value=\"desc\">");
      s(i18nPage.getString(I18n.str_decroissante));
      l("</", composantsWebPrefixe, "option>");
      tl(9, "</", composantsWebPrefixe, "select>");
      tl(8, "</", composantsWebPrefixe, "details>");
      tl(8, "{% endfor %}");
      tl(7, "</div>");
    
      tl(7, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Tri), classePageNomSimple, " %}");
      tl(6, "</div>");
      tl(5, "</", composantsWebPrefixe, "drawer>");

      ///////////////////
      // sidebar gamme //
      ///////////////////

      tl(5, "<", composantsWebPrefixe, "drawer with-header light-dismiss placement=\"end\" id=\"site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Gamme), "\">");
      tl(6, "<div slot=\"label\">");
      tl(7, "<span>");
      t(7, "<i class=\"{{ FONTAWESOME_STYLE }} fa-calendar-range \"></i>");
      l(" ", i18nPage.getString(I18n.str_gamme), "</span>");
      tl(6, "</div>");
      tl(6, "<div>");

      //////////////////
      // htmBodyGamme //
      //////////////////

      tl(7, "{%- block htmBody", i18nClasse.getString(I18n.var_Gamme), classePageNomSimple, " %}");

      tl(7, "<table>");
      tl(8, "<tr>");
      t(9, "<td");
      s(" colspan=\"2\"");
      l(">");
      t(10, "<div");
      s(" class=\"pageSearchVal \"");
      s(" id=\"pageSearchVal-pageFacetRangeTimeZone-", classeNomSimple, "\"");
      s(">{% if rangeGap is defined %}var=defaultZoneId:{{ defaultZoneId | urlencode() }}{% endif %}");
      l("</div>");
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

      tl(7, "<div>");
      tl(8, "<wa-popup placement=\"bottom-start\" id=\"pageFacetRangeTimeZonePopup\">");
      t(9, "<wa-input");
      s(" type=\"text\"");
      s(" slot=\"anchor\"");
      s(" placeholder=\"", i18nPage.getString(I18n.str_fuseau_horaire), "\"");
      s(" label=\"", i18nPage.getString(I18n.str_fuseau_horaire), "\"");
      s(" hint=\"", String.format(i18nPage.getString(I18n.str_Le_fuseau_horaire_description), classeCeNom), "\"");
      s(" id=\"pageFacetRangeTimeZoneInput\"");
      s(" data-popup=\"pageFacetRangeTimeZonePopup\"");
      s(" data-list=\"pageFacetRangeTimeZoneList\"");
      s(" data-classSimpleName=\"", classeNomSimple, "\"");
      s(" autocomplete=\"off\"");
      s(" value=\"{{ defaultZoneId | e }}\"");
      s(" size=\"medium\"");
      s(" appearance=\"outlined\"");
      s(">");
      l("</wa-input>");
      tl(9, "<div id=\"pageFacetRangeTimeZoneList\"></div>");
      tl(8, "</wa-popup>");
      tl(7, "</div>");
      tl(7, "<div>");
      tl(8, "<wa-dropdown id=\"pageFacetSimpleRangeDropdown\" data-classSimpleName=\"", classeNomSimple, "\">");
      tl(9, "<wa-button slot=\"trigger\" with-caret>", i18nPage.getString(I18n.str_gammes_simples), "</wa-button>");

      tl(9, "<wa-dropdown-item value=\"last-minutes\">");
      tl(10, i18nPage.getString(I18n.str_dernieres_minutes));
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-minutes-1-hours\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_dernieres_heures), 1));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-minutes-3-hours\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_dernieres_heures), 3));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-minutes-6-hours\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_dernieres_heures), 6));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-minutes-12-hours\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_dernieres_heures), 12));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-minutes-24-hours\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_dernieres_heures), 24));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-minutes-2-days\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_derniers_jours), 2));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-minutes-7-days\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_derniers_jours), 7));
      tl(10, "</wa-dropdown-item>");
      tl(9, "</wa-dropdown-item>");

      tl(9, "<wa-dropdown-item value=\"last-hours\">");
      tl(10, i18nPage.getString(I18n.str_dernieres_heures));
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-hours-1-hours\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_dernieres_heures), 1));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-hours-3-hours\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_dernieres_heures), 3));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-hours-6-hours\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_dernieres_heures), 6));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-hours-12-hours\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_dernieres_heures), 12));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-hours-24-hours\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_dernieres_heures), 24));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-hours-2-days\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_derniers_jours), 2));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-hours-7-days\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_derniers_jours), 7));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-hours-30-days\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_derniers_jours), 30));
      tl(10, "</wa-dropdown-item>");
      tl(9, "</wa-dropdown-item>");

      tl(9, "<wa-dropdown-item value=\"last-days\">");
      tl(10, i18nPage.getString(I18n.str_derniers_jours));
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-days-1-days\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_derniers_jours), 1));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-days-2-days\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_derniers_jours), 2));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-days-7-days\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_derniers_jours), 7));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-days-30-days\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_derniers_jours), 30));
      tl(10, "</wa-dropdown-item>");
      tl(10, "<wa-dropdown-item slot=\"submenu\" value=\"last-days-90-days\">");
      tl(10, String.format(i18nPage.getString(I18n.str_n_derniers_jours), 90));
      tl(10, "</wa-dropdown-item>");
      tl(9, "</wa-dropdown-item>");

      tl(8, "</wa-dropdown>");
      tl(7, "</div>");
      tl(7, "<table>");
      tl(8, "<tr class=\"\">");
      tl(9, "<td class=\"\">");
      tl(10, "<span>Range Gap</span>");
      tl(9, "</td>");
      tl(9, "<td class=\"\">");
      t(10, "<", composantsWebPrefixe, "select");
      s(" name=\"facet.range.gap\"");
      s(" id=\"pageSearchVal-pageFacetRangeGap-", classeNomSimple, "-input\"");
      s(" onchange=\"facet", i18nClasse.getString(I18n.var_Gamme), "GapChange", classeNomSimple, "('", classeNomSimple, "', this); \"");
      l(">");
      tl(11, "<", composantsWebPrefixe, "option value=\"+1YEAR\"{% if defaultRangeGap == '+1YEAR' %} selected=\"selected\"{% else %}{% endif %}>Year</", composantsWebPrefixe, "option>");
      tl(11, "<", composantsWebPrefixe, "option value=\"+1MONTH\"{% if defaultRangeGap == '+1MONTH' %} selected=\"selected\"{% else %}{% endif %}>Month</", composantsWebPrefixe, "option>");
      tl(11, "<", composantsWebPrefixe, "option value=\"+1WEEK\"{% if defaultRangeGap == '+1WEEK' %} selected=\"selected\"{% else %}{% endif %}>Week</", composantsWebPrefixe, "option>");
      tl(11, "<", composantsWebPrefixe, "option value=\"+1DAY\"{% if defaultRangeGap == '+1DAY' %} selected=\"selected\"{% else %}{% endif %}>Day</", composantsWebPrefixe, "option>");
      tl(11, "<", composantsWebPrefixe, "option value=\"+1HOUR\"{% if defaultRangeGap == '+1HOUR' %} selected=\"selected\"{% else %}{% if defaultRangeGap is defined %}{% else %} selected=\"selected\"{% endif %}{% endif %}>Hour</", composantsWebPrefixe, "option>");
      tl(11, "<", composantsWebPrefixe, "option value=\"+1MINUTE\"{% if defaultRangeGap == '+1MINUTE' %} selected=\"selected\"{% else %}{% endif %}>Minute</", composantsWebPrefixe, "option>");
      tl(11, "<", composantsWebPrefixe, "option value=\"+1SECOND\"{% if defaultRangeGap == '+1SECOND' %} selected=\"selected\"{% else %}{% endif %}>Second</", composantsWebPrefixe, "option>");
      tl(10, "</", composantsWebPrefixe, "select>");
      tl(9, "</td>");
      tl(8, "</tr>");

      tl(8, "<tr class=\"\">");
      tl(9, "<td class=\"\" colspan=\"2\">");
      tl(10, "<span>Range Start ({{ formatZonedDateTime(defaultRangeStart, \"VV\", defaultLocaleId, defaultZoneId) }})</span>");
      tl(9, "</td>");
      tl(8, "</tr>");
      tl(8, "<tr class=\"\">");
      tl(9, "<td class=\"\" colspan=\"2\">");
      t(10, "<span>");
      s("<", composantsWebPrefixe, "input type=\"datetime-local\"");
      s(" name=\"facetRangeStart\"");
      s(" id=\"pageSearchVal-pageFacetRangeStart-", classeNomSimple, "-input\"");
      s(" value=\"{{ formatZonedDateTime(defaultRangeStart, \"yyyy-MM-dd'T'HH:mm\", defaultLocaleId, defaultZoneId) }}\"");
      s(" onchange=\"facet", i18nClasse.getString(I18n.var_Gamme), "StartChange", classeNomSimple, "('", classeNomSimple, "', this, '{{ defaultZoneId }}'); \"");
      l("></", composantsWebPrefixe, "input></span>");
      tl(9, "</td>");
      tl(8, "</tr>");

      tl(8, "<tr class=\"\">");
      tl(9, "<td class=\"\" colspan=\"2\">");
      tl(10, "<span>Range End ({{ formatZonedDateTime(defaultRangeStart, \"VV\", defaultLocaleId, defaultZoneId) }})</span>");
      tl(9, "</td>");
      tl(8, "</tr>");
      tl(8, "<tr class=\"\">");
      tl(9, "<td class=\"\" colspan=\"2\">");
      t(10, "<span>");
      s("<", composantsWebPrefixe, "input type=\"datetime-local\"");
      s(" name=\"facetRangeEnd\"");
      s(" id=\"pageSearchVal-pageFacetRangeEnd-", classeNomSimple, "-input\"");
      s(" value=\"{{ formatZonedDateTime(defaultRangeEnd, \"yyyy-MM-dd'T'HH:mm\", defaultLocaleId, defaultZoneId) }}\"");
      s(" onchange=\"facet", i18nClasse.getString(I18n.var_Gamme), "EndChange", classeNomSimple, "('", classeNomSimple, "', this, '{{ defaultZoneId }}'); \"");
      l("></", composantsWebPrefixe, "input></span>");
      tl(9, "</td>");
      tl(8, "</tr>");
      tl(7, "</table>");

      t(7, "<", composantsWebPrefixe, "radio-group id=\"pageFacet", i18nClasse.getString(I18n.var_Gamme), classeNomSimple, "\">");
      tl(8, "{% for key, value in vars", i18nClasse.getString(I18n.var_Gamme), ".items() %}");
      t(8, "<", composantsWebPrefixe, "radio");
      s(" name=\"pageFacet", i18nClasse.getString(I18n.var_Gamme), "\"");
      s(" class=\"pageFacet", i18nClasse.getString(I18n.var_Gamme), " \"");
      s(" id=\"pageFacet", i18nClasse.getString(I18n.var_Gamme), classeNomSimple, "_{{ key }}\"");
      s(" value=\"{{ value.var }}\"");
      s("{% if default", i18nClasse.getString(I18n.var_Gamme), "Var == var %} checked=\"checked\"{% endif %}");
      l(">{{ value.displayName }}</", composantsWebPrefixe, "radio>");
      tl(8, "{% endfor %}");
      tl(7, "</", composantsWebPrefixe, "radio-group>");
      tl(7, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Gamme), classePageNomSimple, " %}");
      tl(6, "</div>");
      tl(5, "</", composantsWebPrefixe, "drawer>");

      ///////////////////
      // sidebar pivot //
      ///////////////////

      tl(5, "<", composantsWebPrefixe, "drawer with-header light-dismiss placement=\"end\" id=\"site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Pivot), "\">");
      tl(6, "<div slot=\"label\">");
      tl(7, "<span>");
      t(7, "<i class=\"{{ FONTAWESOME_STYLE }} fa-table-pivot \"></i>");
      l(" ", i18nPage.getString(I18n.str_pivot), "</span>");
      tl(6, "</div>");
      tl(6, "<div>");

      //////////////////
      // htmBodyPivot //
      //////////////////

      tl(7, "{%- block htmBody", i18nClasse.getString(I18n.var_Pivot), classePageNomSimple, " %}");

      t(7, "<div");
      s(" style=\"display: none; \"");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_Pivot), classeNomSimple, "Hidden\"");
      l(">");
      tl(8, "{% for item in default", i18nClasse.getString(I18n.var_Pivot), "Vars %}");
      t(9, "<div");
      s(" class=\"pageSearchVal-", i18nClasse.getString(I18n.var_Pivot), classeNomSimple, "Hidden \"");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_Pivot), classeNomSimple, "Hidden_{{ item }}\"");
      l(">{{ item }}</div>");
      tl(8, "{% endfor %}");
      tl(7, "</div>");

      t(7, "<div");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_Pivot), classeNomSimple, "\"");
      l(">");
      tl(8, "{% if default", i18nClasse.getString(I18n.var_Pivot), "Vars is defined and default", i18nClasse.getString(I18n.var_Pivot), "Vars.length > 0 %}");
      t(9, "<div");
      s(" class=\"pageSearchVal pageSearchVal-", i18nClasse.getString(I18n.var_Pivot), classeNomSimple, " \"");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_Pivot), classeNomSimple, "_1\"");
      s(">facet.pivot={!range=r1}");
      s("{% for item in default", i18nClasse.getString(I18n.var_Pivot), "Vars %}");
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
      s("{% if ", i18nClasse.getString(I18n.var_pivot), " is defined %} checked=\"checked\"{% endif %}");
      l(">{{ value.", i18nClasse.getString(I18n.var_nomAffichage), " }}</", composantsWebPrefixe, "checkbox>");
      tl(7, "</div>");
      tl(7, "{% endfor %}");
    
      tl(7, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Pivot), classePageNomSimple, " %}");
      tl(6, "</div>");
      tl(5, "</", composantsWebPrefixe, "drawer>");

      //////////////////////////
      // sidebar liste champs //
      //////////////////////////

      tl(5, "<", composantsWebPrefixe, "drawer with-header light-dismiss placement=\"end\" id=\"site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_ListeChamps), "\">");
      tl(6, "<div slot=\"label\">");
      tl(7, "<span>");
      t(7, "<i class=\"{{ FONTAWESOME_STYLE }} fa-list-ul \"></i>");
      l(" ", i18nPage.getString(I18n.str_liste_champs), "</span>");
      tl(6, "</div>");
      tl(6, "<div>");

      ////////////////////////
      // htmBodyListeChamps //
      ////////////////////////

      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_ListeChamps), classePageNomSimple, " %}");

      t(7, "<div");
      s(" style=\"display: none; \"");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_ListeChamps), classeNomSimple, "Hidden\"");
      l(">");
      tl(8, "{% for item in default", i18nClasse.getString(I18n.var_ListeChamps), "Vars %}");
      t(9, "<div");
      s(" class=\"pageSearchVal-", i18nClasse.getString(I18n.var_ListeChamps), classeNomSimple, "Hidden \"");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_ListeChamps), classeNomSimple, "Hidden_{{ item }}\"");
      l(">{{ item }}</div>");
      tl(8, "{% endfor %}");
      tl(7, "</div>");

      tl(7, "{% if default", i18nClasse.getString(I18n.var_ListeChamps), "Vars is defined and default", i18nClasse.getString(I18n.var_ListeChamps), "Vars.length > 0 %}");
      t(7, "<div");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_ListeChamps), classeNomSimple, "\"");
      l(">");
      tl(8, "{% if default", i18nClasse.getString(I18n.var_ListeChamps), "Vars is defined %}");
      t(9, "<div");
      s(" class=\"pageSearchVal pageSearchVal-", i18nClasse.getString(I18n.var_ListeChamps), classeNomSimple, " \"");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_ListeChamps), classeNomSimple, "_1\"");
      s(">fl=");
      s("{% for item in default", i18nClasse.getString(I18n.var_ListeChamps), "Vars %}");
      s("{% if loop.index > 0 %},{% endif %}{{ item }}");
      s("{% endfor %}");
      l("</div>");
      tl(8, "{% endif %}");
      tl(7, "</div>");
      tl(7, "{% endif %}");

      tl(7, "{% for key, value in varsFq.items() %}");
      tl(7, "<div>");
      t(8, "<", composantsWebPrefixe, "checkbox");
      s(" name=\"page", i18nClasse.getString(I18n.var_ListeChamps), "\"");
      s(" class=\"page", i18nClasse.getString(I18n.var_ListeChamps), " \"");
      s(" id=\"page", i18nClasse.getString(I18n.var_ListeChamps), classeNomSimple, "_{{ key }}\"");
      s(" value=\"{{ value.var }}\"");
      s("{% if ", i18nClasse.getString(I18n.var_listeChamps), " is defined %} checked=\"checked\"{% endif %}");
      s(" onclick=\"facet", i18nClasse.getString(I18n.var_ListeChamps), "Change('", classeNomSimple, "', value); \"");
      l(">{{ value.", i18nClasse.getString(I18n.var_nomAffichage), " }}</", composantsWebPrefixe, "checkbox>");
      tl(7, "</div>");
      tl(8, "{% endfor %}");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_ListeChamps), classePageNomSimple, " %}");
      tl(6, "</div>");
      tl(5, "</", composantsWebPrefixe, "drawer>");

      ///////////////////
      // sidebar stats //
      ///////////////////

      tl(5, "<", composantsWebPrefixe, "drawer with-header light-dismiss placement=\"end\" id=\"site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Stats), "\">");
      tl(6, "<div slot=\"label\">");
      tl(7, "<i class=\"{{ FONTAWESOME_STYLE }} fa-chart-candlestick \"></i>");
      tl(7, i18nPage.getString(I18n.str_stats));
      tl(6, "</div>");
      tl(6, "<div>");

      //////////////////
      // htmBodyStats //
      //////////////////

      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Stats), classePageNomSimple, " %}");

      t(7, "<div");
      s(" style=\"display: none; \"");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_Stats), classeNomSimple, "Hidden\"");
      l(">");
      tl(8, "{% for item in default", i18nClasse.getString(I18n.var_Stats), "Vars %}");
      t(9, "<div");
      s(" class=\"pageSearchVal-", i18nClasse.getString(I18n.var_Stats), classeNomSimple, "Hidden \"");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_Stats), classeNomSimple, "Hidden_{{ item }}\"");
      l(">{{ item }}</div>");
      tl(8, "{% endfor %}");
      tl(7, "</div>");

      t(7, "<div");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_Stats), classeNomSimple, "\"");
      l(">");
      tl(8, "{% if default", i18nClasse.getString(I18n.var_Stats), "Vars is defined %}");
      tl(9, "{% for item in default", i18nClasse.getString(I18n.var_Stats), "Vars %}");
      t(10, "<div");
      s(" class=\"pageSearchVal pageSearchVal-", i18nClasse.getString(I18n.var_Stats), classeNomSimple, "_{{ item }} \"");
      s(" id=\"pageSearchVal-", i18nClasse.getString(I18n.var_Stats), classeNomSimple, "_{{ item }}\"");
      s(">");
      s("stats.field={{ item }}");
      tl(10, "</div>");
      tl(9, "{% endfor %}");
      tl(8, "{% endif %}");
      tl(7, "</div>");

      tl(7, "<div>");
      tl(8, "{% for key, value in varsFq.items() %}");
      tl(9, "{% if value.", i18nClasse.getString(I18n.var_activer), i18nClasse.getString(I18n.var_Stats), " is defined %}");
      tl(10, "<", composantsWebPrefixe, "details");
      tl(12, "summary=\"{{ value.", i18nClasse.getString(I18n.var_nomAffichage), " | e }}\"");
      tl(12, "class=\"page", i18nClasse.getString(I18n.var_Stats), " \"");
      tl(12, "id=\"page", i18nClasse.getString(I18n.var_Stats), classeNomSimple, "_{{ key }}\"");
      tl(12, "data-value=\"{{ value.var | e }}\"");
      tl(12, "{% if value.", i18nClasse.getString(I18n.var_stats), " is defined %}open{% endif %}");
      tl(12, ">");

      t(11, "<div");
      s(" class=\"", composantsWebPrefixe, "stack pageStatsField pageStatsField", classeNomSimple, "_{{ key }} \"");
      s(" id=\"pageStatsField", classeNomSimple, "_{{ key }}\"");
      l(">");
      tl(12, "{% if value.stats is defined %}");
      tl(13, "{% for itemKey, itemValue in value.stats.items() %}");
      tl(14, "{% if itemKey == 'name' %}{% else %}");
      tl(15, "<div");
      tl(17, " class=\"", composantsWebPrefixe, "split \"");
      tl(17, " data-class=\"", classeNomSimple, "\"");
      tl(17, " data-var=\"{{ itemKey }}\"");
      tl(17, " data-val=\"{{ itemValue }}\"");
      tl(17, ">");
      tl(16, "<span>{{ itemKey | e }}</span>");
      tl(16, "<span>{{ itemValue | e }}</span>");
      tl(15, "</div>");
      tl(14, "{% endif %}");
      tl(13, "{% endfor %}");
      // tl(13, "{% if value.stats.max is defined %}");
      // tl(14, "<div>");
      // tl(15, "<span> step </span>");
      // tl(15, "<", composantsWebPrefixe, "input id=\"animate", i18nClasse.getString(I18n.var_Stats), "Step\" placeholder=\"step\" value=\"1\" style=\"width: 4em; \"></", composantsWebPrefixe, "input>");
      // tl(15, "<span> min </span>");
      // tl(15, "<", composantsWebPrefixe, "input id=\"animate", i18nClasse.getString(I18n.var_Stats), "Min\" placeholder=\"min\" value=\"{{ value.stats.min }}\" style=\"width: 4em; \"></", composantsWebPrefixe, "input>");
      // tl(15, "<span> max </span>");
      // tl(15, "<", composantsWebPrefixe, "input id=\"animate", i18nClasse.getString(I18n.var_Stats), "Max\" placeholder=\"max\" value=\"{{ value.stats.max }}\" style=\"width: 4em; \"></", composantsWebPrefixe, "input>");
      // tl(15, "<span> speed in seconds </span>");
      // tl(15, "<", composantsWebPrefixe, "input id=\"animate", i18nClasse.getString(I18n.var_Stats), "Speed\" placeholder=\"speed\" value=\"1\" style=\"width: 4em; \"></", composantsWebPrefixe, "input>");
      // tl(15, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " onclick=\"animate", i18nClasse.getString(I18n.var_Stats), "(); \">animate</", composantsWebPrefixe, "button>");
      // tl(14, "</div>");
      // tl(13, "{% endif %}");
      tl(12, "{% endif %}");
      tl(11, "</div>");

      tl(10, "</", composantsWebPrefixe, "details>");
      tl(9, "{% endif %}");
      tl(8, "{% endfor %}");
      tl(7, "</div>");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Stats), classePageNomSimple, " %}");
      tl(6, "</div>");
      tl(5, "</", composantsWebPrefixe, "drawer>");

      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_BarreLaterale), "", classePageNomSimple, " %}");
      tl(4, "</div>");
    }

    o = oAncien;
  }

  public void ecrirePageRechercheAucun(String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {

    ///////////////////
    // htmBodyCount0 //
    ///////////////////

    tl(4, "<div class=\"", composantsWebPrefixe, "stack \">");
    tl(5, "<", composantsWebPrefixe, "tooltip for=\"", i18nClasse.getString(I18n.var_retourner_a_), classeNomSimple, "\">", i18nPage.getString(I18n.str_retourner_a_), classeTousNom, "</", composantsWebPrefixe, "tooltip></", composantsWebPrefixe, "tooltip>");
    tl(5, "<", composantsWebPrefixe, "breadcrumb>");
    tl(6, "<", composantsWebPrefixe, "breadcrumb-item id=\"", i18nClasse.getString(I18n.var_retourner_a_), classeNomSimple, "\"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " href=\"{{ SITE_BASE_URL }}{{ pageUri }}\">");
    tl(7, classeIcone);
    tl(7, classeTousNom);
    tl(6, "</", composantsWebPrefixe, "breadcrumb-item>");
    tl(6, "<", composantsWebPrefixe, "breadcrumb-item>");
    t(7).sx(classeAucunNomTrouve).l();
    tl(6, "</", composantsWebPrefixe, "breadcrumb-item>");
    tl(5, "</", composantsWebPrefixe, "breadcrumb>");

    tl(5, "<div class=\"", composantsWebPrefixe, "stack ", composantsWebPrefixe, "align-items-center \">");
    tl(6, "<div class=\"", composantsWebPrefixe, "heading-3xl \">");
    tl(7, classeIcone);
    tl(6, "</div>");
    tl(6, "<span class=\"", composantsWebPrefixe, "heading-m \">", classeAucunNomTrouve, "</span>");
    tl(6, "<p class=\"", composantsWebPrefixe, "caption-l \">", classeDescription, "</p>");
    tl(5, "</div>");
    tl(4, "</div>");
  }

  public void ecrirePageBoutonsRecherche(String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {
    ToutEcrivain oAncien = o;
    o = auteurBoutonsRechercheJinja;

    if(auteurBoutonsRechercheJinja != null) {
      tl(5, "<", composantsWebPrefixe, "scroller>");
      tl(6, "<", composantsWebPrefixe, "button-group class=\"no-gradient\" id=\"htm", i18nClasse.getString(I18n.var_BoutonsRecherche), "\">");

      //////////////
      // bouton q //
      //////////////
      tl(7, "{% if ", classeVarCleUnique, " is not defined %}");
      // tl(5, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Recherche_avancee_pour_), classeNomAdjectifPluriel, "\">");
      tl(7, "<", composantsWebPrefixe, "button class=\"", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Rechercher), " ", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Rechercher), "_", classeNomSimple, " \"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " onclick=\"document.querySelector('#site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Recherche), "').open = true; \">");
      tl(8, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-magnifying-glass hover-box-shadow \"></i> ");
      tl(8, i18nPage.getString(I18n.str_rechercher));
      tl(7, "</", composantsWebPrefixe, "button>");
      // tl(5, "</", composantsWebPrefixe, "tooltip>");
      tl(7, "{% endif %}");

      ///////////////
      // bouton fq //
      ///////////////
      tl(7, "{% if ", classeVarCleUnique, " is not defined %}");
      // tl(5, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Filtres_et_nombres_de_facettes_pour_), classeNomAdjectifPluriel, "\">");
      tl(7, "<", composantsWebPrefixe, "button class=\"", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Filtres), " ", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Filtres), "_", classeNomSimple, " \"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " onclick=\"document.querySelector('#site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Filtres), "').open = true; \">");
      tl(8, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-filters hover-box-shadow \"></i> ");
      tl(8, i18nPage.getString(I18n.str_filtres));
      tl(7, "</", composantsWebPrefixe, "button>");
      // tl(5, "</", composantsWebPrefixe, "tooltip>");
      tl(7, "{% endif %}");

      ////////////////
      // bouton tri //
      ////////////////
      tl(7, "{% if ", classeVarCleUnique, " is not defined %}");
      // tl(5, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Filtres_et_nombres_de_facettes_pour_), classeNomAdjectifPluriel, "\">");
      tl(7, "<", composantsWebPrefixe, "button class=\"", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Tri), " ", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Tri), "_", classeNomSimple, " \"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " onclick=\"document.querySelector('#site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Tri), "').open = true; \">");
      tl(8, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-arrow-down-a-z hover-box-shadow \"></i> ");
      tl(8, i18nPage.getString(I18n.str_tri));
      tl(7, "</", composantsWebPrefixe, "button>");
      // tl(5, "</", composantsWebPrefixe, "tooltip>");
      tl(7, "{% endif %}");

      //////////////////
      // bouton gamme //
      //////////////////
      // tl(5, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Recherche_avancee_pour_), classeNomAdjectifPluriel, "\">");
      tl(7, "<", composantsWebPrefixe, "button class=\"", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Gamme), " ", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Gamme), "_", classeNomSimple, " \"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " onclick=\"document.querySelector('#site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Gamme), "').open = true; \">");
      tl(8, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-calendar-range hover-box-shadow \"></i> ");
      tl(8, i18nPage.getString(I18n.str_gamme));
      tl(7, "</", composantsWebPrefixe, "button>");
      // tl(5, "</", composantsWebPrefixe, "tooltip>");

      //////////////////
      // bouton pivot //
      //////////////////
      // // tl(5, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Recherche_avancee_pour_), classeNomAdjectifPluriel, "\">");
      // tl(7, "<", composantsWebPrefixe, "button class=\"", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Pivot), " ", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Pivot), "_", classeNomSimple, " \"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " onclick=\"document.querySelector('#site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Pivot), "').open = true; \">");
      // tl(8, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-table-pivot hover-box-shadow \"></i> ");
      // tl(8, i18nPage.getString(I18n.str_pivot));
      // tl(7, "</", composantsWebPrefixe, "button>");
      // // tl(5, "</", composantsWebPrefixe, "tooltip>");

      /////////////////////////
      // bouton liste champs //
      /////////////////////////
      // // tl(5, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Recherche_avancee_pour_), classeNomAdjectifPluriel, "\">");
      // tl(7, "<", composantsWebPrefixe, "button class=\"", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_ListeChamps), " ", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_ListeChamps), "_", classeNomSimple, " \"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " onclick=\"document.querySelector('#site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_ListeChamps), "').open = true; \">");
      // tl(8, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-list-ul hover-box-shadow \"></i> ");
      // tl(8, i18nPage.getString(I18n.str_liste_champs));
      // tl(7, "</", composantsWebPrefixe, "button>");
      // // tl(5, "</", composantsWebPrefixe, "tooltip>");

      //////////////////
      // bouton stats //
      //////////////////
      tl(7, "{% if ", classeVarCleUnique, " is not defined %}");
      // tl(5, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Recherche_avancee_pour_), classeNomAdjectifPluriel, "\">");
      tl(7, "<", composantsWebPrefixe, "button class=\"", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Stats), " ", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Stats), "_", classeNomSimple, " \"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " onclick=\"document.querySelector('#site", i18nClasse.getString(I18n.var_BarreLaterale), "Toggle", i18nClasse.getString(I18n.var_Stats), "').open = true; \">");
      tl(8, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-chart-candlestick hover-box-shadow \"></i> ");
      tl(8, i18nPage.getString(I18n.str_stats));
      tl(7, "</", composantsWebPrefixe, "button>");
      // tl(5, "</", composantsWebPrefixe, "tooltip>");
      tl(7, "{% endif %}");

      ////////////////
      // bouton API //
      ////////////////
      // tl(5, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", i18nPage.getString(I18n.str_Interroger_lAPI_REST_JSON_pour_), classeNomAdjectifPluriel, "\">");
      t(7, "<", composantsWebPrefixe, "button class=\"", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Api), " ", i18nClasse.getString(I18n.var_BoutonsRecherche), "_", i18nClasse.getString(I18n.var_Api), "_", classeNomSimple, " \"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", "");
      if(classeVarId != null) {
        s(" href=\"{{ apiUri }}{% if ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " is defined %}/{{ ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " }}{% else %}{{ queryStr }}{% endif %}\"");
      } else {
        s(" href=\"{{ apiUri }}{{ queryStr }}\"");
      }
      l(">");
      tl(8, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-brackets-curly hover-box-shadow \"></i> ");
      tl(8, i18nPage.getString(I18n.str_API));
      tl(7, "</", composantsWebPrefixe, "button>");
      // tl(5, "</", composantsWebPrefixe, "tooltip>");

      tl(6, "</", composantsWebPrefixe, "button-group>");
      tl(5, "</", composantsWebPrefixe, "scroller>");

      // tl(7, "<", composantsWebPrefixe, "button-group>");
      // tl(8, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", String.format(i18nPage.getString(I18n.str_Afficher_les_resultats_sous_forme_de_details), classeNomAdjectifPluriel), "\" pill>");
      // tl(9, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " pill id=\"grid-toggle-details\">");
      // tl(10, "<i class=\"fa-solid fa-list\"></i>");
      // tl(9, "</", composantsWebPrefixe, "button>");
      // tl(8, "</", composantsWebPrefixe, "tooltip>");
      // tl(8, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", String.format(i18nPage.getString(I18n.str_Afficher_les_resultats_sous_forme_de_liste), classeNomAdjectifPluriel), "\">");
      // tl(9, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " id=\"grid-toggle-list\">");
      // tl(10, "<i class=\"fa-solid fa-bars\"></i>");
      // tl(9, "</", composantsWebPrefixe, "button>");
      // tl(8, "</", composantsWebPrefixe, "tooltip>");
      // tl(8, "<", composantsWebPrefixe, "tooltip placement=\"top\" content=\"", String.format(i18nPage.getString(I18n.str_Afficher_les_resultats_sous_forme_de_cartes), classeNomAdjectifPluriel), "\" pill>");
      // tl(9, "<", composantsWebPrefixe, "button", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " pill id=\"grid-toggle-card\">");
      // tl(10, "<i class=\"fa-solid fa-grid\"></i>");
      // tl(9, "</", composantsWebPrefixe, "button>");
      // tl(8, "</", composantsWebPrefixe, "tooltip>");
      // tl(7, "</", composantsWebPrefixe, "button-group>");
    }

    o = oAncien;
  }

  public void ecrirePageBoutonsPagination(String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {
    ToutEcrivain oAncien = o;

    o = auteurBoutonsPaginationJinja;

    if(auteurBoutonsPaginationJinja != null) {
      l();

      tl(5, "<div class=\"", composantsWebPrefixe, "split \">");
      tl(6, "<", composantsWebPrefixe, "tooltip for=\"", i18nClasse.getString(I18n.var_BoutonsPagination), i18nClasse.getString(I18n.var_Precedent), "\" placement=\"top\"", "wa-".equals(composantsWebPrefixe) ? ">" : " content=\"", String.format(i18nPage.getString(I18n.str_Afficher_les_resultats_precedents), classeNomAdjectifPluriel), "", "wa-".equals(composantsWebPrefixe) ? "" : "\">", "</", composantsWebPrefixe, "tooltip>");
      tl(6, "<", composantsWebPrefixe, "tooltip for=\"", i18nClasse.getString(I18n.var_BoutonsPagination), i18nClasse.getString(I18n.var_Diminuer), "\" placement=\"top\"", "wa-".equals(composantsWebPrefixe) ? ">" : " content=\"", String.format(i18nPage.getString(I18n.str_Afficher_la_moitie_des_resultats), classeNomAdjectifPluriel), "", "wa-".equals(composantsWebPrefixe) ? "" : "\">", "</", composantsWebPrefixe, "tooltip>");
      tl(6, "<", composantsWebPrefixe, "tooltip for=\"", i18nClasse.getString(I18n.var_BoutonsPagination), i18nClasse.getString(I18n.var_Augmenter), "\" placement=\"top\"", "wa-".equals(composantsWebPrefixe) ? ">" : " content=\"", String.format(i18nPage.getString(I18n.str_Afficher_le_double_des_resultats), classeNomAdjectifPluriel), "", "wa-".equals(composantsWebPrefixe) ? "" : "\">", "</", composantsWebPrefixe, "tooltip>");
      tl(6, "<", composantsWebPrefixe, "tooltip for=\"", i18nClasse.getString(I18n.var_BoutonsPagination), i18nClasse.getString(I18n.var_Suivant), "\" placement=\"top\"", "wa-".equals(composantsWebPrefixe) ? ">" : " content=\"", String.format(i18nPage.getString(I18n.str_Afficher_les_resultats_suivants), classeNomAdjectifPluriel), "", "wa-".equals(composantsWebPrefixe) ? "" : "\">", "</", composantsWebPrefixe, "tooltip>");
      tl(6, "<div class=\"", composantsWebPrefixe, "caption-l \">{{ pagination.", i18nClasse.getString(I18n.var_debut), "Num }} – {{ pagination.", i18nClasse.getString(I18n.var_fin), "Num }} ", i18nPage.getString(I18n.var_de), " {{ pagination.", i18nClasse.getString(I18n.var_numTrouve), " }} ", classeNomPluriel, "</div>");
      tl(6, "<", composantsWebPrefixe, "scroller>");
      tl(7, "<", composantsWebPrefixe, "button-group size=\"small\" class=\"no-gradient ", composantsWebPrefixe, "cluster ", composantsWebPrefixe, "gap-xs \" id=\"htm", i18nClasse.getString(I18n.var_BoutonsPagination), "\">");

      t(8, "<", composantsWebPrefixe, "button id=\"", i18nClasse.getString(I18n.var_BoutonsPagination), i18nClasse.getString(I18n.var_Precedent), "\"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline");
      s("{% if pagination.page", i18nClasse.getString(I18n.var_Precedent), " is defined %}");
      s(" href=\"{{pageUri}}?start={{pagination.page", i18nClasse.getString(I18n.var_Precedent), ".", i18nClasse.getString(I18n.var_debut), "}}&amp;rows={{pagination.", i18nClasse.getString(I18n.var_lignes), "}}\"");
      s("{% else %}");
      s(" disabled");
      s("{% endif %}");
      l(">");
      tl(9, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-arrow-square-left \"></i>");
      tl(9, i18nPage.getString(I18n.str_precedent));
      tl(8, "</", composantsWebPrefixe, "button>");

      t(8, "<", composantsWebPrefixe, "button id=\"", i18nClasse.getString(I18n.var_BoutonsPagination), i18nClasse.getString(I18n.var_Diminuer), "\"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", "");
      s("{% if pagination.", i18nClasse.getString(I18n.var_lignes), i18nClasse.getString(I18n.var_Precedent), " >= pagination['1L'] %}");
      s(" href=\"{{pageUri}}?start={{pagination.", i18nClasse.getString(I18n.var_debut), "}}&amp;rows={{ pagination.", i18nClasse.getString(I18n.var_lignes), i18nClasse.getString(I18n.var_Precedent), " }}\"");
      s("{% else %}");
      s(" disabled");
      s("{% endif %}");
      l(">");
      tl(9, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-minus-square \"></i>");
      tl(9, i18nPage.getString(I18n.str_moins));
      tl(8, "</", composantsWebPrefixe, "button>");

      t(8, "<", composantsWebPrefixe, "button id=\"", i18nClasse.getString(I18n.var_BoutonsPagination), i18nClasse.getString(I18n.var_Augmenter), "\"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", "");
      s(" href=\"{{pageUri}}?start={{pagination.", i18nClasse.getString(I18n.var_debut), "}}&amp;rows={{ pagination.", i18nClasse.getString(I18n.var_lignes), i18nClasse.getString(I18n.var_Prochaine), " }}\"");
      l(">");
      tl(9, "<i slot=\"start\" class=\"{{ FONTAWESOME_STYLE }} fa-plus-square \"></i>");
      tl(9, i18nPage.getString(I18n.str_plus));
      tl(8, "</", composantsWebPrefixe, "button>");

      t(8, "<", composantsWebPrefixe, "button id=\"", i18nClasse.getString(I18n.var_BoutonsPagination), i18nClasse.getString(I18n.var_Suivant), "\"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline");
      s("{% if pagination.page", i18nClasse.getString(I18n.var_Prochaine), " is defined %}");
      s(" href=\"{{pageUri}}?start={{pagination.page", i18nClasse.getString(I18n.var_Prochaine), ".", i18nClasse.getString(I18n.var_debut), "}}&amp;rows={{pagination.", i18nClasse.getString(I18n.var_lignes), "}}\"");
      s("{% else %}");
      s(" disabled");
      s("{% endif %}");
      l(">");
      tl(9, i18nPage.getString(I18n.str_suivant));
      tl(9, "<i slot=\"suffix\" class=\"{{ FONTAWESOME_STYLE }} fa-arrow-square-right \"></i>");
      tl(8, "</", composantsWebPrefixe, "button>");
      tl(7, "</", composantsWebPrefixe, "button-group>");
      tl(6, "</", composantsWebPrefixe, "scroller>");
      tl(5, "</div>");
    }

    o = oAncien;
  }

  public void ecrirePageRechercheJinja(String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {

    if(auteurPageRechercheJinja != null) {
      o = auteurPageRechercheJinja;
      l("{% extends \"", classeGenPageRechercheTemplate, "\" %}");
    }

    o = auteurGenPageRechercheJinja;
    if(auteurGenPageRechercheJinja != null) {
      if(!classePageSimple) {
        l("{% extends \"", classeGenPageRechercheSuperTemplate, "\" %}");
      }

      l();
      tl(0, "{%- block htmTitle", classePageSuperNomSimple, " %}");
      tl(0, "{%- block htmTitle", classePageNomSimple, " %}");
      t(2, "<title>");
      s("{% if ", varResultat, "Count == 0 %}");
      s(classeAucunNomTrouve);
      s("{% else %}");
      s(classeNomAdjectifPluriel);
      s("{% endif %}");
      l("</title>");
      tl(0, "{%- endblock htmTitle", classePageNomSimple, " %}");
      tl(0, "{%- endblock htmTitle", classePageSuperNomSimple, " %}");
      ecrirePageHeadJinja(langueNom, i18nClasse, i18nPage, false);
      //STUFF0

      /////////////
      // htmBody //
      /////////////


      tl(0, "{%- if ", i18nClasse.getString(I18n.var_pageFilDAriane), " is not defined %}");
      tl(0, "{%- set ", i18nClasse.getString(I18n.var_pageFilDAriane), " %}");
      tl(7, "<", composantsWebPrefixe, "tooltip for=\"", i18nClasse.getString(I18n.var_retourner_a_), classeNomSimple, "\">", i18nPage.getString(I18n.str_retourner_a_), classeTousNom, "</", composantsWebPrefixe, "tooltip>");
      tl(7, "<", composantsWebPrefixe, "breadcrumb>");
      tl(8, "<", composantsWebPrefixe, "breadcrumb-item id=\"", i18nClasse.getString(I18n.var_retourner_a_), classeNomSimple, "\"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " href=\"{{ SITE_BASE_URL }}{{ pageUri }}\">");
      tl(9, classeIcone);
      tl(9, classeTousNom);
      tl(8, "</", composantsWebPrefixe, "breadcrumb-item>");
      tl(8, "<", composantsWebPrefixe, "breadcrumb-item>");
      t(9).sx(classeRechercherTousNom).l();
      tl(8, "</", composantsWebPrefixe, "breadcrumb-item>");
      tl(7, "</", composantsWebPrefixe, "breadcrumb>");
      tl(0, "{%- endset ", i18nClasse.getString(I18n.var_pageFilDAriane), " %}");
      tl(0, "{%- endif  %}");
      l();
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Debut), classePageSuperNomSimple, " %}");
      tl(0, "{{ super() }}");
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Debut), classePageNomSimple, " %}");

      // htmBodyCount0 //
      tl(0, "{% include ", classePageBarreLateraleTemplate, " %}");
      tl(0, "{% if ", varResultat, "Count == 0 %}");
      ecrirePageRechercheAucun(langueNom, i18nClasse, i18nPage);
      tl(0, "{% else %}");

      tl(0, "{% include ", classePageRechercheSuggereTemplate, " %}");

      tl(5, "<div class=\"pageContent \">");
      tl(6, "<form action=\"", classeApiUri, "\" id=\"", classeApiClasseNomSimple, "Form\" class=\"", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Formulaire), " \" onsubmit=\"event.preventDefault(); return false; \">");
      t(7, "<input");
      s(" name=\"focusId\"");
      s(" type=\"hidden\"");
      l("/>");
      t(7, "<input");
      s(" name=\"", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Reponse), "\"");
      s(" id=\"", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Reponse), "\"");
      s(" class=\"", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Reponse), "\" ");
      s(" value='{{ toJsonObjectStringInApostrophes(", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Reponse), ") }}'");
      s(" type=\"hidden\"");
      l("/>");
      t(7, "<input");
      s(" name=\"", i18nClasse.getString(I18n.var_liste), classeNomSimple, "\"");
      s(" id=\"", i18nClasse.getString(I18n.var_liste), classeNomSimple, "\"");
      s(" class=\"", i18nClasse.getString(I18n.var_liste), classeNomSimple, "\" ");
      s(" value='{{ toJsonArrayStringInApostrophes(", i18nClasse.getString(I18n.var_liste), classeNomSimple, ") }}'");
      s(" type=\"hidden\"");
      l("/>");
      tl(6, "</form>");

      tl(6, "<div id=\"site-calendar-box\">");
//		  tl(7, "<h3 id=\"site-calendar-title\">Calendar</h3>");
      tl(7, "<div id=\"site-calendar\"><!-- // --></div>");
      tl(6, "</div>");
      tl(6, "<div class=\"", composantsWebPrefixe, "stack ", composantsWebPrefixe, "gap-m \">");

      /////////////////
      // htmBodyTous //
      /////////////////

      // tl(6, "<", composantsWebPrefixe, "divider></", composantsWebPrefixe, "divider>");
      // tl(6, "{{ htm", i18nClasse.getString(I18n.var_BoutonsPagination), classePageNomSimple, "() }}");

      tl(0, "{% endif %}");
      tl(0, "{% include ", classePageFormulaireRechercheTemplate, " %}");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Debut), classePageNomSimple, " %}");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Debut), classePageSuperNomSimple, " %}");
      tl(0, "{%- if ", i18nClasse.getString(I18n.var_pageBoutonsRecherche), " is not defined %}");
      tl(0, "{%- set ", i18nClasse.getString(I18n.var_pageBoutonsRecherche), " %}");
      tl(0, "{% include ", classePageBoutonsRechercheTemplate, " %}");
      tl(0, "{%- endset ", i18nClasse.getString(I18n.var_pageBoutonsRecherche), " %}");
      tl(0, "{%- endif %}");
      tl(0, "{%- if ", i18nClasse.getString(I18n.var_pageBoutonsFormulaireRecherche), " is not defined %}");
      tl(0, "{%- set ", i18nClasse.getString(I18n.var_pageBoutonsFormulaireRecherche), " %}");
      tl(0, "{% include ", classePageBoutonsFormulaireRechercheTemplate, " %}");
      tl(0, "{%- endset ", i18nClasse.getString(I18n.var_pageBoutonsFormulaireRecherche), " %}");
      tl(0, "{%- endif %}");
      l();
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Milieu), classePageSuperNomSimple, " %}");
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Milieu), classePageNomSimple, " %}");
      tl(0, "{% if ", varResultat, "Count > 0 %}");
      tl(7, "<div class=\"", composantsWebPrefixe, "stack ", composantsWebPrefixe, "gap-0 \">");
      tl(0, "{% include ", classePageBoutonsPaginationTemplate, " %}");
      tl(8, "<div class=\"card-like-thing background-color-surface-border\" id=\"site-results-grid\">");
      tl(9, "<div>");
      s(wTh);
      tl(9, "</div>");
//		  	TODO
//		  	tl(2, "Map<String, Map<String, List<String>>> highlighting = ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getResponse().getHighlighting();");
      tl(9, "{% for item in ", i18nClasse.getString(I18n.var_liste), classeApiClasseNomSimple, "%}");
//		  	TODO
//		  	tl(3, classeApiClasseNomSimple, " o = ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getList().get(i);");
//		  	tl(3, "Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());");
//		  	tl(3, "List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));");
//		  	tl(3, "String uri = ", classeEntiteVars.contains("pageUri") ? "o.getPageUri()" : (q(classePageUriMethode, "/") + " + o.get" + StringUtils.capitalize(classeVarClePrimaire) + "()"), ";");
      tl(9, "<div>");
      // tl(6, "<a href=\"{{ item.", classeVarUrlPageAffichage, " }}\">");
      s(wTd);
      // tl(6, "</a>");
      tl(9, "</div>");
      tl(9, "{% endfor %}");
//		  	TODO
//		  	tl(3, "SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getResponse()).map(QueryResponse::getResponse).map(r -> r.get(\"facets\")).orElse(new SimpleOrderedMap());");
      s(wFoot);
      tl(8, "</div>");
      tl(0, "{% include ", classePageBoutonsPaginationTemplate, " %}");
      tl(7, "</div>");
      tl(0, "{% endif %}");

      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Milieu), classePageNomSimple, " %}");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Milieu), classePageSuperNomSimple, " %}");
      l();
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Fin), classePageSuperNomSimple, " %}");
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Fin), classePageNomSimple, " %}");
      tl(0, "{%- include ", classePageEmplacementTemplate, " %}");
      tl(6, "</div>");
      tl(5, "</div>");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Fin), classePageNomSimple, " %}");
      tl(1, "{{ super() }}");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Fin), classePageSuperNomSimple, " %}");
    }
  }

  public void ecrirePageEditionJinja(String langueNom, JsonObject i18nClasse, JsonObject i18nPage) throws Exception {

    if(auteurPageEditionJinja != null) {
      o = auteurPageEditionJinja;
      l("{% extends \"", classeGenPageEditionTemplate, "\" %}");
    }

    o = auteurGenPageEditionJinja;
    if(auteurGenPageEditionJinja != null) {
      if(!classePageSimple) {
        l("{% extends \"", classeGenPageEditionSuperTemplate, "\" %}");
      }

      tl(0, "{%- block htmTitle", classePageSuperNomSimple, " %}");
      tl(0, "{%- block htmTitle", classePageNomSimple, " %}");
      t(2, "<title>");
      //STUFF5
      s("{{ ", varResultat, ".", classeVarTitre, " }}");
      l("</title>");
      tl(0, "{%- endblock htmTitle", classePageNomSimple, " %}");
      tl(0, "{%- endblock htmTitle", classePageSuperNomSimple, " %}");
      ecrirePageHeadJinja(langueNom, i18nClasse, i18nPage, true);
      //STUFF0

      /////////////
      // htmBody //
      /////////////

      l();
      tl(0, "{%- if ", i18nClasse.getString(I18n.var_pageBoutonsRecherche), " is not defined %}");
      tl(0, "{%- set ", i18nClasse.getString(I18n.var_pageBoutonsRecherche), " %}");
      tl(0, "{% include ", classePageBoutonsRechercheTemplate, " %}");
      tl(0, "{%- endset ", i18nClasse.getString(I18n.var_pageBoutonsRecherche), " %}");
      tl(0, "{%- endif %}");
      tl(0, "{%- if ", i18nClasse.getString(I18n.var_pageBoutonsFormulaireRecherche), " is not defined %}");
      tl(0, "{%- set ", i18nClasse.getString(I18n.var_pageBoutonsFormulaireRecherche), " %}");
      tl(0, "{% include ", classePageBoutonsFormulaireRechercheTemplate, " %}");
      tl(0, "{%- endset ", i18nClasse.getString(I18n.var_pageBoutonsFormulaireRecherche), " %}");
      tl(0, "{%- endif %}");

      tl(0, "{%- if ", i18nClasse.getString(I18n.var_pageFilDAriane), " is not defined %}");
      tl(0, "{%- set ", i18nClasse.getString(I18n.var_pageFilDAriane), " %}");
      tl(7, "<", composantsWebPrefixe, "tooltip for=\"", i18nClasse.getString(I18n.var_retourner_a_), classeNomSimple, "\">", i18nPage.getString(I18n.str_retourner_a_), classeTousNom, "</", composantsWebPrefixe, "tooltip>");
      tl(7, "<", composantsWebPrefixe, "breadcrumb>");
      tl(8, "<", composantsWebPrefixe, "breadcrumb-item id=\"", i18nClasse.getString(I18n.var_retourner_a_), classeNomSimple, "\"", "wa-".equals(composantsWebPrefixe) ? " variant=\"brand\"" : " variant=\"primary\" outline", " href=\"{{ SITE_BASE_URL }}{{ pageUri }}\">");
      tl(9, classeIcone);
      tl(9, classeTousNom);
      tl(8, "</", composantsWebPrefixe, "breadcrumb-item>");
      tl(8, "<", composantsWebPrefixe, "breadcrumb-item>");
      t(9).sx(classeUnNom).l();
      tl(8, "</", composantsWebPrefixe, "breadcrumb-item>");
      tl(7, "</", composantsWebPrefixe, "breadcrumb>");
      tl(0, "{%- endset ", i18nClasse.getString(I18n.var_pageFilDAriane), " %}");
      tl(0, "{%- endif  %}");
      l();
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Debut), classePageSuperNomSimple, " %}");
      tl(2, "{{ super() }}");
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Debut), classePageNomSimple, " %}");

      tl(0, "{% if ", varResultat, "Count > 0 %}");

      tl(5, "<div class=\"", composantsWebPrefixe, "flank \">");

      t(6, "<h1 class=\"", i18nClasse.getString(I18n.var_Page), "_", classeVarTitre, "\">");
      s(classeIcone);
      s(" {{ ", varResultat, ".", classeVarTitre, " | e }}");
      l("</h1>");
      tl(5, "</div>");

      tl(0, "{% endif %}");
      tl(0, "{% include ", classePageFormulaireRechercheTemplate, " %}");

      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Debut), classePageNomSimple, " %}");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Debut), classePageSuperNomSimple, " %}");
      l();
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Milieu), classePageSuperNomSimple, " %}");
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Milieu), classePageNomSimple, " %}");

      tl(0, "{%- include ", classePageBarreLateraleTemplate, " %}");
      tl(4, "<div class=\"pageContent \">");
      // htmBodyCount0 //
      tl(0, "{% if ", varResultat, "Count == 0 %}");
      ecrirePageRechercheAucun(langueNom, i18nClasse, i18nPage);
      tl(0, "{% else %}");

      tl(0, "{%- include ", classePageRechercheSuggereTemplate, " %}");

      // htmBodyCount1 //

      ///////////////////
      // htmBodyCount1 //
      ///////////////////

      tl(0, "{% endif %}");

      tl(0, "{%- block htm", i18nClasse.getString(I18n.var_Formulaire), classePageNomSimple, " %}");
      tl(5, "<form action=\"", classeApiUri, "\" id=\"", classeApiClasseNomSimple, "Form\" class=\"", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Formulaire), " \" onsubmit=\"event.preventDefault(); return false; \">");
      tl(0, "{% if ", i18nClasse.getString(I18n.var_resultat), ".", classeVarId, " is defined %}");
      t(6, "<input");
      s(" name=\"", classeVarId, "\"");
      s(" class=\"", i18nClasse.getString(I18n.var_valeur), StringUtils.capitalize(classeVarId), "\"");
      s(" type=\"hidden\"");
      s(" value=\"{{ ", i18nGlobale.getString(I18n.var_resultat), ".", classeVarId, " }}\"");
      l("/>");
      tl(6, "{% endif %}");
      t(6, "<input");
      s(" name=\"focusId\"");
      s(" type=\"hidden\"");
      l("/>");
      t(6, "<input");
      s(" name=\"", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Reponse), "\"");
      s(" id=\"", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Reponse), "\"");
      s(" class=\"", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Reponse), "\" ");
      s(" value='{{ toJsonObjectStringInApostrophes(", i18nClasse.getString(I18n.var_page), i18nClasse.getString(I18n.var_Reponse), ") }}'");
      s(" type=\"hidden\"");
      l("/>");
      t(6, "<input");
      s(" name=\"", varResultat, "\"");
      s(" id=\"", varResultat, "\"");
      s(" class=\"", varResultat, "\" ");
      s(" value='{{ toJsonObjectStringInApostrophes(", varResultat, ") }}'");
      s(" type=\"hidden\"");
      l("/>");
      t(6, "<input");
      s(" name=\"", i18nClasse.getString(I18n.var_liste), classeNomSimple, "\"");
      s(" id=\"", i18nClasse.getString(I18n.var_liste), classeNomSimple, "\"");
      s(" class=\"", i18nClasse.getString(I18n.var_liste), classeNomSimple, "\" ");
      s(" value='{{ toJsonArrayStringInApostrophes(", i18nClasse.getString(I18n.var_liste), classeNomSimple, ") }}'");
      s(" type=\"hidden\"");
      l("/>");
      tl(5, "</form>");
      tl(0, "{%- if ", varResultat, "Count >= 1 %}");
      tl(5, "{{ htm", i18nClasse.getString(I18n.var_Formulaire), "_", StringUtils.lowerCase(classePageRechercheApiMethode), classeApiClasseNomSimple, "() }}");
      tl(0, "{% endif -%}");
      tl(0, "{%- endblock htm", i18nClasse.getString(I18n.var_Formulaire), classePageNomSimple, " %}");
      tl(4, "</div>");

      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Milieu), classePageNomSimple, " %}");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Milieu), classePageSuperNomSimple, " %}");
      l();
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Fin), classePageSuperNomSimple, " %}");
      tl(0, "{%- block htmBody", i18nClasse.getString(I18n.var_Fin), classePageNomSimple, " %}");
      tl(0, "{%- include ", classePageEmplacementTemplate, " %}");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Fin), classePageNomSimple, " %}");
      tl(3, "{{ super() }}");
      tl(0, "{%- endblock htmBody", i18nClasse.getString(I18n.var_Fin), classePageSuperNomSimple, " %}");
    }
  }
}
