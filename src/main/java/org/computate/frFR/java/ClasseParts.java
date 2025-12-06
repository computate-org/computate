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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.thoughtworks.qdox.model.JavaClass;

/**
 * NomCanonique.enUS: org.computate.enUS.java.ClassParts
 */  
public class ClasseParts {

  public static final String SITE_JAVA_ENSEMBLE_COMPUTATE = "org.computate.";

  private String valeurGenerique;

  /**
   */
  private String nomCanonique;
  /**
   */
  public String nomCanonique(String langueNom) {
    if(langueNom == null || documentSolr == null)
      return nomCanonique;
    else
      return (String)documentSolr.get("classeNomCanonique_" + langueNom + "_stored_string");
  }

  /**
   */  
  private String nomCanoniqueComplet;
  /**
   */
  public String nomCanoniqueComplet(String langueNom) {
    StringBuilder b = new StringBuilder();

    if(langueNom == null || documentSolr == null) {
      b.append(nomCanonique);
    }
    else {
      b.append((String)documentSolr.get("classeNomCanonique_" + langueNom + "_stored_string"));
    }
    if(valeurGenerique != null) {
      b.append("<");
      for(int i = 0; i < classeParts.size(); i++) {
        ClasseParts classePart = classeParts.get(i);
        if(i > 0)
          b.append(", ");
        b.append(classePart.nomCanoniqueComplet(langueNom));
      }
      b.append(">");
    }

    return b.toString();
  }

  /**
   */
  private String nomSimple;
  /**
   */
  public String nomSimple(String langueNom) {
    if(langueNom == null || documentSolr == null)
      return nomSimple;
    else
      return (String)documentSolr.get("classeNomSimple_" + langueNom + "_stored_string");
  }
  /**
   */
  public String nomSimpleComplet(String langueNom) {
    StringBuilder b = new StringBuilder();

    if(langueNom == null || documentSolr == null) {
      b.append(nomSimple);
    }
    else {
      b.append((String)documentSolr.get("classeNomSimple_" + langueNom + "_stored_string"));
    }
    if(valeurGenerique != null) {
      b.append("<");
      for(int i = 0; i < classeParts.size(); i++) {
        ClasseParts classePart = classeParts.get(i);
        if(i > 0)
          b.append(", ");
        b.append(classePart.nomSimpleComplet(langueNom));
      }
      b.append(">");
    }

    return b.toString();
  }

  /**
   */
  public String nomCanoniqueGenerique(String langueNom) {
    StringBuilder b = new StringBuilder();

    if(valeurGenerique != null) {
      for(int i = 0; i < classeParts.size(); i++) {
        ClasseParts classePart = classeParts.get(i);
        if(i > 0)
          b.append(", ");
        b.append(classePart.nomCanoniqueComplet(langueNom));
      }
    }

    return b.toString();
  }

  /**
   */
  public String nomSimpleGenerique(String langueNom) {
    StringBuilder b = new StringBuilder();

    if(valeurGenerique != null) {
      for(int i = 0; i < classeParts.size(); i++) {
        ClasseParts classePart = classeParts.get(i);
        if(i > 0)
          b.append(", ");
        b.append(classePart.nomSimpleComplet(langueNom));
      }
    }

    return b.toString();
  }

  /**
   */
  private String nomCanoniqueGenerique;

  /**
   */
  private String nomSimpleComplet;

  /**
   */
  private String nomSimpleGenerique;

  /**
   */
  private String nomSimpleSuperGenerique;

  /**
   */
  private String nomCanoniqueSuperGenerique;

  /**
   */
  private Boolean etendGen = false;

  /**
   */
  private Boolean etendBase = false;

  /**
   */
  private List<ClasseParts> classeParts = new ArrayList<>();
  /**
   */
  private SolrDocument documentSolr;

  /**
   */
  private String langueNom;

  /**
   */
  public static SolrDocument documentSolr(ConfigSite configSite, String nomSimpleOuCanonique, String langueNom) throws Exception {
    SolrDocument doc = null;   
    Boolean contientPoint = nomSimpleOuCanonique.contains(".");
    if(!contientPoint || StringUtils.startsWith(nomSimpleOuCanonique, configSite.nomEnsembleDomaine) || StringUtils.startsWith(nomSimpleOuCanonique, SITE_JAVA_ENSEMBLE_COMPUTATE)) {
      SolrQuery rechercheSolr = new SolrQuery();   
      rechercheSolr.setQuery("*:*");
      rechercheSolr.setRows(1);

      if(contientPoint)
        rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimpleOuCanonique));
      else
        rechercheSolr.addFilterQuery("classeNomSimple_" + langueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(nomSimpleOuCanonique));

      rechercheSolr.addFilterQuery("partEstClasse_indexed_boolean:true");
      if(!contientPoint)
        rechercheSolr.addFilterQuery("nomEnsembleDomaine_indexed_string:" + ClientUtils.escapeQueryChars(configSite.nomEnsembleDomaine));
//			rechercheSolr.addFilterQuery("langueNom_indexed_string:" + ClientUtils.escapeQueryChars(langueNom));
      QueryResponse reponseRecherche = configSite.clientSolrComputate.query(rechercheSolr);
      SolrDocumentList listeRecherche = reponseRecherche.getResults();
      if(listeRecherche.size() > 0) { 
        doc = listeRecherche.get(0);
      }
    }
    return doc;
  }

  /**
   */
  public static ClasseParts initClasseParts(ConfigSite configSite, ClasseParts classeParts, String langueNom) throws Exception {
    ClasseParts o = initClasseParts(configSite, classeParts.nomCanoniqueComplet, langueNom);
    return o;
  } 

  /**
   */ 
  public static ClasseParts initClasseParts(ConfigSite configSite, JavaClass classeQdox, String langueNom) throws Exception {
    ClasseParts classeParts = new ClasseParts();
    String nomCanonique = StringUtils.replace(classeQdox.getCanonicalName(), "$", ".");
    String valeurGeneriqueCanoniqueAvant = StringUtils.replace(classeQdox.getGenericCanonicalName(), "$", ".");
    classeParts.initClasseParts2(configSite, valeurGeneriqueCanoniqueAvant, langueNom);
    return classeParts;
  } 

  public void initClasseParts2(ConfigSite configSite, String nomSimpleOuCanoniqueComplet, String langueNom) throws Exception {
    nomCanonique = nomSimpleOuCanoniqueComplet;
    nomCanoniqueGenerique = null;
    valeurGenerique = null;
    etendGen = false;

    if(StringUtils.contains(nomSimpleOuCanoniqueComplet, "<")) {
      nomCanonique = StringUtils.substringBefore(nomSimpleOuCanoniqueComplet, "<");
      valeurGenerique = StringUtils.substringAfter(StringUtils.substringBeforeLast(nomSimpleOuCanoniqueComplet, ">"), "<");
    }

    documentSolr = documentSolr(configSite, nomCanonique, langueNom);

    if(nomSimpleOuCanoniqueComplet != null && langueNom != null && (nomSimpleOuCanoniqueComplet.contains(langueNom) || documentSolr != null))
      this.langueNom = langueNom;

    if(documentSolr != null) {
      nomSimple = (String)documentSolr.get("classeNomSimple_" + langueNom + "_stored_string");
      etendGen = (Boolean)documentSolr.get("classeEtendGen_stored_boolean");
      etendBase = (Boolean)documentSolr.get("classeEtendBase_stored_boolean");
      setNomSimpleSuperGenerique((String)documentSolr.get("classeNomSimpleSuperGenerique_" + langueNom + "_stored_string"));
      setNomCanoniqueSuperGenerique((String)documentSolr.get("classeNomCanoniqueSuperGenerique_" + langueNom + "_stored_string"));
    } else {
      try {
        Class<?> c = Class.forName(nomCanonique);
        nomSimple = c.getSimpleName();
        Class<?> cSuper = c.getSuperclass();
        String cSuperNomSimple = cSuper.getSimpleName();
        if(StringUtils.endsWith(cSuperNomSimple, "Gen")) {
          etendGen = true;
          Class<?> cSuperGenerique = cSuper.getSuperclass();
          setNomSimpleSuperGenerique(cSuperGenerique.getSimpleName());
          setNomCanoniqueSuperGenerique(cSuperGenerique.getCanonicalName());
          
        }
      } catch (Exception e) {
      }
    }

    if(nomSimple == null) {
      nomSimple = StringUtils.substringAfterLast(nomCanonique, ".");
      if(StringUtils.isEmpty(nomSimple))
        nomSimple = nomCanonique;
    }
    nomCanoniqueComplet = nomCanonique;
    nomSimpleComplet = nomSimple;

    if(valeurGenerique != null) {
      Integer level = 0;
      StringBuilder b = new StringBuilder();
      String part;
      List<String> partsGenerique = new ArrayList<>();
      for(char c : valeurGenerique.toCharArray()) {
        if(c == ' ')
          continue;
        if(c == '<')
          level++;
        if(level == 0 && c == ',') {
          part = b.toString();
          if(StringUtils.isNotBlank(part))
            partsGenerique.add(part);
          b = new StringBuilder();
        }
        else {
          b.append(c);
        }
        if(c == '>')
          level--;
      }
      part = b.toString();
      if(StringUtils.isNotBlank(part))
        partsGenerique.add(part);

      nomCanoniqueGenerique = "";
      nomSimpleGenerique = "";
      for(int i = 0; i < partsGenerique.size(); i++) {
        String nomCanoniqueGeneriquePart = StringUtils.trim(partsGenerique.get(i));

        ClasseParts partClasseParts = ClasseParts.initClasseParts(configSite, nomCanoniqueGeneriquePart, langueNom);
        if(i > 0) {
          nomSimpleGenerique += ", ";
          nomCanoniqueGenerique += ", ";
        }
        classeParts.add(partClasseParts);
        nomSimpleGenerique += partClasseParts.nomSimpleComplet(langueNom);
        nomCanoniqueGenerique += partClasseParts.nomCanoniqueComplet(langueNom);
      }
      nomSimpleComplet = nomSimple + "<" + nomSimpleGenerique + ">";
      nomCanoniqueComplet = nomCanonique + "<" + nomCanoniqueGenerique + ">";
    }
  } 

  /**
   */
  public static ClasseParts initClasseParts(ConfigSite configSite, String nomCanoniqueComplet, String langueNom) throws Exception {
    ClasseParts classeParts = new ClasseParts();
    classeParts.initClasseParts2(configSite, nomCanoniqueComplet, langueNom);
    return classeParts;
  }

  /**
   */
  public static String concat(String...valeurs) throws Exception { 
    String o = Stream.of(valeurs).collect(Collectors.joining());
    return o;
  }  

  public String getValeurGenerique() {
    return valeurGenerique;
  }
  public void setValeurGenerique(String valeurGenerique) {
    this.valeurGenerique = valeurGenerique;
  }

  public Boolean getEtendBase() {
    return etendBase;
  }

  public void setEtendBase(Boolean etendBase) {
    this.etendBase = etendBase;
  }

  public Boolean getEtendGen() {
    return etendGen;
  }

  public void setEtendGen(Boolean etendGen) {
    this.etendGen = etendGen;
  }

  public SolrDocument getDocumentSolr() {
    return documentSolr;
  }

  public void setDocumentSolr(SolrDocument documentSolr) {
    this.documentSolr = documentSolr;
  }

  public String getLangueNom() {
    return langueNom;
  }

  public void setLangueNom(String langueNom) {
    this.langueNom = langueNom;
  }

  public String getNomCanoniqueSuperGenerique() {
    return nomCanoniqueSuperGenerique;
  }
  public void setNomCanoniqueSuperGenerique(String nomCanoniqueSuperGenerique) {
    this.nomCanoniqueSuperGenerique = nomCanoniqueSuperGenerique;
  }

  public String getNomSimpleSuperGenerique() {
    return nomSimpleSuperGenerique;
  }
  public void setNomSimpleSuperGenerique(String nomSimpleSuperGenerique) {
    this.nomSimpleSuperGenerique = nomSimpleSuperGenerique;
  }
  /**
   */
  @Override public String toString() {
    StringBuilder b = new StringBuilder();
    b.append("nomCanoniqueComplet: ").append(nomCanoniqueComplet).append("\n");
    b.append("nomCanonique: ").append(nomCanonique).append("\n");
    b.append("nomSimple: ").append(nomSimple).append("\n");
    b.append("nomCanoniqueGenerique: ").append(nomCanoniqueGenerique).append("\n");
    b.append("nomSimpleComplet: ").append(nomSimpleComplet).append("\n");
    b.append("nomSimpleGenerique: ").append(nomSimpleGenerique).append("\n");
    b.append("etendGen: ").append(etendGen).append("\n");
    b.append("langueNom: ").append(langueNom).append("\n");
    return b.toString();
  }
}
