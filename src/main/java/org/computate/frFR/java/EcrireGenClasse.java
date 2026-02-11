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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.computate.i18n.I18n;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**  
 * NomCanonique.enUS: org.computate.enUS.java.WriteGenClass
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */  
public class EcrireGenClasse extends EcrireClasse { 

  protected static final Logger LOG = LoggerFactory.getLogger(EcrireClasse.class);

  public static final String[] HTML_ELEMENTS = new String[] { "div", "span", "a", "ul", "ol", "li", "p", "h1", "h2", "h3", "h4", "h5", "h6", "i", "table", "tbody", "thead", "tr", "td", "th", "pre", "code", "br", "dd", "dt" };

  /**
   * Var.enUS: classDirPathGen
   */
  protected String classeCheminRepertoireGen;

  /**
   * Var.enUS: classPathGen
   */
  protected String classeCheminGen;

  /**
   * Var.enUS: classPathApiPackageInfo
   */
  protected String classeCheminApiEnsembleInfo;

  /**
   * Var.enUS: classPathGenApiServiceImpl
   * 
   */
  protected String classeCheminGenApiServiceImpl;

  /**
   * Var.enUS: classPathApiServiceImpl
   */
  protected String classeCheminApiServiceImpl;

  /**
   * Var.enUS: classPathGenApiService
   */
  protected String classeCheminGenApiService;

  /**
   * Var.enUS: classDirGen
   */
  protected File classeRepertoireGen;

  /**
   * Var.enUS: classFileGen
   */
  protected File classeFichierGen;

  /**
   * Var.enUS: classFileApiPackageInfo
   */
  protected File classeFichierApiEnsembleInfo;

  /**
   * Var.enUS: classFileGenApiServiceImpl
   */
  protected File classeFichierGenApiServiceImpl;

  /**
   * Var.enUS: classFileApiServiceImpl
   */
  protected File classeFichierApiServiceImpl;

  /**
   * Var.enUS: classFileGenApiService
   */
  protected File classeFichierGenApiService;

  protected StringBuilder s = new StringBuilder();
      
  /**
   * Var.enUS: classDoc
   */
  protected JsonObject classeDoc;
      
  protected JsonObject doc;

  /**
   * Var.enUS: classCanonicalName
   */
  protected String classeNomCanonique;

  /**
   * Var.enUS: classSimpleNameGen
   */
  protected String classeNomSimpleGen;

  /**
   * Var.enUS: classSuperSimpleName
   */
  protected String classeNomSimpleSuper;
  protected String classeNomSimpleBaseGenLangue;

  /**
   * Var.enUS: classSuperSimpleNameGeneric
   */ 
  protected String classeNomSimpleSuperGenerique;

  /**
   * Var.enUS: classSuperCanonicalNameGeneric
   */
  protected String classeNomCanoniqueSuperGenerique;

  /**
   * Var.enUS: classPackageName
   */
  protected String classeNomEnsemble;

  protected String classeDroitAuteur;

  /**
   * Var.enUS: classSimpleName
   */
  protected String classeNomSimple;
  protected String classeAuthRessource;
  protected String classePageTemplate;
  protected String classeGenPageTemplate;
  protected String classePageSuperTemplate;

  protected String uncapitalizeClasseNomSimple;

  /**
   * Var.enUS: classSuperCanonicalName
   */
  protected String classeNomCanoniqueSuper;

  protected String classeCommentaire;
  protected String classeCommentaireLangue;

  protected String classeVarClePrimaire;
  protected String classeVarClePrimaireSuffixeSolr;

  protected String classeVarInheritClePrimaire;
  protected String classeVarInheritClePrimaireCapitalise;

  protected String classeVarZone;

  protected String classeVarEmplacement;
  protected String classeVarEmplacementCouleur;
  protected String classeVarEmplacementTitre;
  protected String classeVarEmplacementUrl;
  protected String classeEmplacementSvg;
  protected Integer classeEmplacementRayon;
  protected String classeVarEmplacementSvg;
  protected String classeVarEmplacementRayon;

  protected String classeVarAire;
  protected String classeVarAireCouleur;
  protected String classeVarAireTitre;
  protected String classeVarAireUrl;

  /**
   */
  protected String classeVarSauvegardes;

  protected List<String> classeRessourcesAutorisation;
  protected String classeVarId;
  protected String classeVarIdSuffixeSolr;
  protected String classeVarNomSuffixeSolr;

  protected String classeVarCleUnique;
  protected String classeVarClePrimaireUnique;

  /**
   * Var.enUS: classVarModified
   */
  protected String classeVarModifie;

  /**
   * Var.enUS: classVarCreated
   */
  protected String classeVarCree;

  /**
   * Var.enUS: classImportsGen
   */
  protected List<String> classeImportationsGen;

  protected Boolean classeMotsClesTrouves;
  protected List<String> classeMotsCles;
  protected List<String> classeFacetsDefaut;

  /**
   * Var.enUS: classInitDeepExceptions
   */
  protected List<String> classeInitLoinExceptions;

  /**
   * Var.enUS: classImportsGenApi
   */
  protected List<String> classeImportationsGenApi;

  /**
   * Var.enUS: classImportsGenPage
   */ 
  protected List<String> classeImportationsGenPage;

  /**
   * Var.enUS: classParameterTypeNames
   */
  protected List<String> classeParametreTypeNoms;

  /**
   * Var.enUS: classSuperParameterTypeNames
   */
  protected List<String> classeSuperParametreTypeNoms;

  protected List<String> classesSuperEtMoiSansGen;
  protected List<String> entiteClassesSuperEtMoiSansGen;

  /**
   * Var.enUS: classSuperWriteMethods
   */
  protected List<String> classeSuperEcrireMethodes;

  /**
   * Var.enUS: classWriteMethods
   */
  protected List<String> classeEcrireMethodes;

  /**
   * Var.enUS: classWriteWriters
   */
  protected List<ToutEcrivain> classeEcrireEcrivains;

  protected Boolean classePromesse;

  /**
   * Var.enUS: classExtendsGen
   */
  protected Boolean classeEtendGen;
  protected Boolean classeEstModeleBase;
  protected Boolean classeEstResultatBase;
  protected Boolean classeEtendModeleBase;
  protected Boolean classeEtendResultatBase;

  /**
   * Var.enUS: classBaseExtendsGen
   */
  protected Boolean classeBaseEtendGen;

  /**
   * Var.enUS: classInitDeep
   */
  protected Boolean classeInitLoin;
  protected Boolean classeInitLoinAvant;

  /**
   * Var.enUS: classContainsSiteRequest
   */
  protected Boolean classeContientRequeteSite;

  /**
   * Var.enUS: classIndexed
   */
  protected Boolean classeIndexe;

  /**
   * Var.enUS: classImage
   */
  protected Boolean classeImage;

  /**
   * Var.enUS: classExtendsBase
   */
  protected Boolean classeEtendBase;

  /**
   * Var.enUS: classIsBase
   */
  protected Boolean classeEstBase;

  /**
   * Var.enUS: classSaved
   */
  protected Boolean classeSauvegarde;

  /**
   * Var.enUS: classModel
   */
  protected Boolean classeModele;

  protected Boolean classeApi;
  protected String classeApiUri;
  protected String classeApiUriPageRecherche;
  protected String classeApiUriPageEdition;
  protected String classeApiTag;

  /**
   * Var.enUS: classPage
   */
  protected Boolean classePage;
  protected String classePageNomSimple;
  protected String classePageNomSimpleMethode;
  protected String classePageNomCanoniqueMethode;
  protected String classeApiOperationIdMethode;
  protected String classeApiUriMethode;
  protected String classeApiMethodeMethode;
  protected String classeApiTypeMedia200Methode;
  protected String classeApiTypeMediaRequeteMethode;
  protected String classePageTemplateMethode;
  protected String classeVarUriTemplateMethode;
  protected Boolean classePageAvecTemplateMethode;
  protected String classePageSuperNomSimple;
  protected String classePageSuperNomCanonique;
  protected String classeApiClasseNomSimple;
  protected String classeGenPageNomSimple;
  protected List<String> classeAttribuerNomSimplePages;
  protected List<String> classeAttribuerPageUriJs;
  protected List<String> classeAttribuerNomSimples;
  protected List<String> classePageCheminsGen = new ArrayList<>();

  /**
   * Var.enUS: classRoleSession
   */
  protected Boolean classeRoleSession;

  protected Boolean classeRoleUtilisateur;
  protected Boolean classeRoleChacun;
  protected Boolean classeSessionEcrire;
  protected Boolean classeUtilisateurEcrire;
  protected Boolean classePublicEcrire;
  protected Boolean classeSessionLire;
  protected Boolean classeUtilisateurLire;
  protected Boolean classePublicLire;
  protected Boolean classePageRecherchePublicLire;

  protected Boolean classeAuth;
  protected Boolean classeRolesTrouves;

  protected List<String> classeRoles;
  protected List<String> classeRolesLangue;

  /**
   * Var.enUS: classRoleLiresFound
   */
  protected Boolean classeRoleLiresTrouves;

  /**
   * Var.enUS: classRoleLires
   */
  protected List<String> classeRoleLires;

  /**
   * Var.enUS: classFiltersFound
   */
  protected Boolean classeFiltresTrouves;

  /**
   * Var.enUS: classFilters
   */
  protected List<String> classeFiltres;

  /**
   * Var.enUS: classEntityVars
   */ 
  protected List<String> classeEntiteVars;

  /**
   * Var.enUS: classMethodVars
   */  
  protected List<String> classeMethodeVars;

  protected ToutEcrivain wInitLoin;
  protected ToutEcrivain wStaticSet;
  protected ToutEcrivain wstaticSearch;
  protected ToutEcrivain wstaticSearchStr;
  protected ToutEcrivain wstaticSearchFq;

  /**
   * Var.enUS: wSiteRequest
   */
  protected ToutEcrivain wRequeteSite;

  /**
   * Var.enUS: wIndex
   */
  protected ToutEcrivain wIndexer;

  protected ToutEcrivain wFacets;

  protected ToutEcrivain wIndexerFacetFor;

  protected List<String> classesNomSimpleFacetFor;

  /**
   * Var.enUS: wText
   */
  protected ToutEcrivain wTexte;

  /**
   * Var.enUS: wObtain
   */
  protected ToutEcrivain wObtenir;

  protected ToutEcrivain wAttribuer;

  protected ToutEcrivain wAttribuerSql;
  protected ToutEcrivain wAttribuerSqlVars;

  protected Integer wAttribuerSqlNum;

  /**
   * Var.enUS: wPut
   */ 
  protected ToutEcrivain wPut;

  /**
   * Var.enUS: wPopulate
   */
  protected ToutEcrivain wPeupler;

  /**
   * Var.enUS: wStore
   */
  protected ToutEcrivain wStocker;

  /**
   * Var.enUS: wDefine
   */
  protected ToutEcrivain wDefinir;

  protected ToutEcrivain wDefinirObjet;

  protected ToutEcrivain wPageTemplates;
  protected ToutEcrivain wApiGet;

  /**
   * Var.enUS: wApiGenerateGet
   */
  protected ToutEcrivain wApiGenererGet;

  /**
   * Var.enUS: wApiGeneratePost
   */
  protected ToutEcrivain wApiAvantPost;

  protected ToutEcrivain wApiSqlSelect;
  protected ToutEcrivain wApiGenererPost;

  protected ToutEcrivain wApiGenererDelete;

  protected ToutEcrivain wApiGenererPutImport;

  protected ToutEcrivain wApiGenererPutCopie;

  protected ToutEcrivain wApiGenererPutFusion;

  protected ToutEcrivain wApiAvantPatch;

  protected ToutEcrivain wApiGenererPatch;

  /**
   * Var.enUS: wPageHtmlSingular
   */
  protected ToutEcrivain wPageHtmlSingulier;

  protected ToutEcrivain wVarsStatic;
  protected ToutEcrivain wVarsQ;
  protected ToutEcrivain wVarsFq;
  protected ToutEcrivain wVarsGamme;
  protected ToutEcrivain wNomAffichageStatic;
  protected ToutEcrivain wNomAffichageMethode;
  protected ToutEcrivain wClasseNomSimpleMethode;
  protected ToutEcrivain wDescriptionMethode;
  protected ToutEcrivain wNgsiTypeMethode;
  protected ToutEcrivain wNgsiMethode;
  protected ToutEcrivain wHtmColonneMethode;
  protected ToutEcrivain wHtmLigneMethode;
  protected ToutEcrivain wHtmCelluleMethode;
  protected ToutEcrivain wLongeurMinMethode;
  protected ToutEcrivain wLongeurMaxMethode;
  protected ToutEcrivain wMinMethode;
  protected ToutEcrivain wMaxMethode;

  /**
   * Var.enUS: wPageEntities
   */
  protected ToutEcrivain wPageEntites;

  protected ToutEcrivain wPageGet;

  protected ToutEcrivain wHashCode;

  protected ToutEcrivain wRequeteApi;

  protected ToutEcrivain wToString;

  protected ToutEcrivain wEquals;
  protected ToutEcrivain auteurSqlDrop;
  protected ToutEcrivain auteurSqlCreate;

  /**
   * Var.enUS: entityVar
   */
  protected String methodeVar;
  protected String entiteVar;
  protected String entiteVarUrl;

  /**
   * Var.enUS: entityTypeSuffix
   */
  protected String entiteSuffixeType;
  protected String entiteSuffixeSolr;

  /**
   * Var.enUS: entityVarCapitalized
   */
  protected String entiteVarCapitalise;

  /**
   * Var.enUS: entityAttribute
   */
  Boolean entiteAttribuer;
  Boolean entiteAttribuerEtendModeleBase;
  Boolean entiteAttribuerEtendResultatBase;
  Boolean entiteAttribuerAttribuer;

  String entiteAttribuerNomSimple;
  String entiteAttribuerNomCanonique;

  Boolean entiteAttribuerUtilisateurEcrire;
  Boolean entiteAttribuerSessionEcrire;
  Boolean entiteAttribuerPublicLire;
  List<String> entiteAttribuerClasseRoles;
  List<String> entiteAttribuerClasseRolesLangue;

  /**
   * Var.enUS: entityAttributeVar
   */
  String entiteAttribuerVar;

  /**
   * Var.enUS: entityAttributeVarUrlPageAffichage
   */
  String entiteAttribuerVarUrlPageAffichage;

  /**
   * Var.enUS: entityAttributeVarUrlPageEdition
   */
  String entiteAttribuerVarUrlPageEdition;

  /**
   * Var.enUS: entityAttributeVarId
   */
  String entiteAttribuerVarId;

  /**
   * Var.enUS: entityAttributeVarTitle
   */
  String entiteAttribuerVarTitre;

  /**
   * Var.enUS: entityAttributeVarDescription
   */
  String entiteAttribuerVarDescription;

  /**
   * Var.enUS: entityAttributeVarImageUrl
   */
  String entiteAttribuerVarImageUrl;

  /**
   * Var.enUS: entityAttributeVarSuggest
   */
  String entiteAttribuerVarSuggere;

  /**
   * Var.enUS: entityDefine
   */
  Boolean entiteDefinir;

  String entiteImageBase64Url;

  /**
   * Var.enUS: entityCanonicalName
   */
  protected String entiteNomCanonique;

  /**
   * Var.enUS: entityCanonicalNameGeneric
   */
  protected String entiteNomCanoniqueGenerique;

  /**
   * Var.enUS: entitySimpleNameComplete
   */
  protected String entiteNomSimpleComplet;

  /**
   * Var.enUS: entitySimpleNameCompleteGeneric
   */
  protected String entiteNomSimpleCompletGenerique;

  /**
   * Var.enUS: entitySimpleName
   */
  protected String entiteNomSimple;

  /**
   * Var.enUS: entitySimpleNameGeneric
   */
  protected String entiteNomSimpleGenerique;

  /**
   * Var.enUS: entityComment
   */
  protected String entiteCommentaire;

  /**
   * Var.enUS: entityVarParam
   */
  protected String entiteVarParam;
  protected Boolean entiteCouverture;
  protected Boolean entitePromesse;

  /**
   * Var.enUS: entityInitialized
   */
  protected Boolean entiteInitialise;

  /**
   * Var.enUS: entityInitDeep
   */
  protected Boolean entiteInitLoin;
  
  protected ToutEcrivain auteurGenClasse;
  protected ToutEcrivain auteurGenClasseDebut;
  protected ToutEcrivain auteurGenClasseFin;
  protected ToutEcrivain auteurGenPageClasse = null;
  protected ToutEcrivain auteurPageClasse = null;
  protected ToutEcrivain auteurPageClasseActuel = null;
  protected ToutEcrivain auteurPageCss = null;
  protected ToutEcrivain auteurPageJs = null;
  protected ToutEcrivain auteurPageJsRecherche = null;
  protected ToutEcrivain auteurPageJsEdition = null;
  // protected ToutEcrivain auteurPageJinja = null;
  // protected ToutEcrivain auteurGenPageJinja = null;
  protected ToutEcrivain auteurEmplacementJinja = null;
  protected ToutEcrivain auteurBarreLateraleJinja = null;
  protected ToutEcrivain auteurBoutonsRechercheJinja = null;
  protected ToutEcrivain auteurBoutonsPaginationJinja = null;
  protected ToutEcrivain auteurBoutonsFormulaireRechercheJinja = null;
  protected ToutEcrivain auteurFormulaireRechercheJinja = null;
  protected ToutEcrivain auteurRechercheSuggereJinja = null;
  protected ToutEcrivain auteurPageRechercheJinja = null;
  protected ToutEcrivain auteurGenPageRechercheJinja = null;
  protected ToutEcrivain auteurPageEditionJinja = null;
  protected ToutEcrivain auteurGenPageEditionJinja = null;
  protected ToutEcrivain auteurGenPageJinjaEntite = null;
  protected ToutEcrivain auteurWebsocket = null;

  /**
   * Var.enUS: entityIndex
   */
  protected Integer entiteIndice;

  /**
   * Var.enUS: contextAName
   */
  protected String classeUnNom;

  /**
   * Var.enUS: contextThis
   */
  protected String classeCe;

  /**
   * Var.enUS: contextThisName
   */
  protected String classeCeNom;

  /**
   * Var.enUS: contextA
   */
  protected String classeUn;

  /**
   * Var.enUS: contextTheName
   */
  protected String classeLeNom;

  /**
   * Var.enUS: contextNameSingular
   */
  protected String classeNomSingulier;

  /**
   * Var.enUS: contextNamePlural
   */
  protected String classeNomPluriel;

  /**
   * Var.enUS: contextActualName
   */
  protected String classeNomActuel;

  /**
   * Var.enUS: contextAll
   */
  protected String classeTous;

  /**
   * Var.enUS: contextAllName
   */
  protected String classeTousNom;

  /**
   * Var.enUS: contextSearchAllNameBy
   */
  protected String classeRechercherTousNomPar;

  /**
   * Var.enUS: contextSearchAllName
   */
  protected String classeRechercherTousNom;

  /**
   * Var.enUS: contextH1
   */
  protected String classeH1;

  /**
   * Var.enUS: contextH2
   */
  protected String classeH2;

  /**
   * Var.enUS: contextH3
   */
  protected String classeH3;

  /**
   * Var.enUS: contextTitle
   */
  protected String classeTitre;

  protected String classeHtmInfobulle;
  protected String classeJsInfobulle;
  protected String classeHtmLegende;
  protected String classeJsLegende;
  protected String classeJsPATCH;
  protected String classeJsWebsocket;

  /**
   * Var.enUS: contextTheNames
   */
  protected String classeLesNoms;

  /**
   * Var.enUS: contextNoNameFound
   */
  protected String classeAucunNomTrouve;

  protected String classeNomVar;
  protected String classeNomApi;

  /**
   * Var.enUS: contextOfName
   */
  protected String classeDeNom;

  protected String classeVarTitre;
  protected String classeUriPageEdition;
  protected String classeStringFormatUrlPageEdition;
  protected String classeUriPageAffichage;
  protected String classeUriPageUtilisateur;
  protected String classeUriTelechargement;
  protected String classeStringFormatUrlPageAffichage;
  protected String classeStringFormatUrlPageUtilisateur;
  protected String classeStringFormatUrlTelechargement;
  protected String classeVarNom;
  protected String classeVarDescription;

  protected String classeVarSuggere;

  protected String classeVarTexte;

  protected String classeVarUrlPageAffichage;

  protected String classeVarUrlPageEdition;
  protected String classeVarUrlPageUtilisateur;
  protected String classeVarUrlTelechargement;

  protected String classeVarH1;

  protected String classeVarH2;

  protected String classeVarH3;

  protected Boolean classeFiware;
  protected String classeSmartDataDomain;
  protected String classeSmartDataSubModule;
  protected String classeSmartDataModel;

  /**
   * Var.enUS: contextAdjective
   */
  protected String classeAdjectif;

  /**
   * Var.enUS: contextAdjectivePlural
   */
  protected String classeAdjectifPluriel;

  /**
   * Var.enUS: contextAdjectiveVar
   */
  protected String classeAdjectifVar;

  /**
   * Var.enUS: contextANameAdjective
   */
  protected String classeUnNomAdjectif;

  /**
   * Var.enUS: contextNameAdjectiveSingular
   */
  protected String classeNomAdjectifSingulier;

  /**
   * Var.enUS: contextNameAdjectivePlural
   */
  protected String classeNomAdjectifPluriel;

  protected String classeCouleur;
  protected String classeEntiteCouleur;

  protected String classeIcone;

  protected Integer classeLignes;
  protected Integer classeOrdre;
  protected Integer classeOrdreSql;

  protected String classeUri;

  /**
   * Var.enUS: contextDescription
   */
  protected String classeDescription;

  /**
   * Var.enUS: contextImageWidth
   */
  protected Integer classeImageLargeur;

  /**
   * Var.enUS: contextImageHeight
   */
  protected Integer classeImageHauteur;

  /**
   * Var.enUS: contextVideoId
   */
  protected String classeVideoId;

  protected String classePageRechercheApiMethode;
  protected String classePageEditionApiMethode;
  protected String classePageAffichageApiMethode;
  protected String classePageUtilisateurApiMethode;

  /**
   * Var.enUS: classContext
   */
  protected Boolean classeContexte;

  Boolean entiteHighlighting;

  /**
   * Var.enUS: entityHtml
   */
  Boolean entiteHtml;
  Integer entitePrecision;
  String entiteEtape;
  String entiteModeDArrondi;

  /**
   * Var.enUS: entityModify
   */
  Boolean entiteModifier;

  /**
   * Var.enUS: entityJsonType
   */
  String entiteTypeJson;

  /**
   * Var.enUS: entityAttributeJsonType
   */
  String entiteAttribuerTypeJson;

  /**
   * Var.enUS: entityOperationIdPATCH
   */
  String entiteOperationIdPATCH;

  /**
   * Var.enUS: entityAttributeOperationIdPATCH
   */
  String entiteAttribuerOperationIdPATCH;

  /**
   * Var.enUS: entityAttributeOperationIdSearch
   */
  String entiteAttribuerOperationIdRecherche;

  String entiteAttribuerApiUri;

  String entiteAttribuerPageUri;

  /**
   * Var.enUS: entityAttributeContextAName
   */
  String entiteAttribuerContexteUnNom;

  String entiteAttribuerContexteNomPluriel;

  String entiteAttribuerContexteCouleur;

  String entiteAttribuerContexteIcone;

  List<String> classeTrisVar;

  List<String> classeTrisOrdre;

  List<String> classeTrisSuffixeType;

  List<String> entiteAttribuerTrisVar;

  List<String> entiteAttribuerTrisSuffixeType;

  /**
   * Var.enUS: entityMultiline
   */
  Boolean entiteMultiligne;
  Boolean entiteCouleur;
  List<String> entiteRadioValeurs;
  List<String> entiteRadioTextes;
  List<String> entiteOptionValeurs;
  List<String> entiteOptionTextes;

  /**
   * Var.enUS: entitySignature
   */
  Boolean entiteSignature;

  /**
   * Var.enUS: entityDisplayName
   */
  String entiteNomAffichage;
  String entiteFormatHtm;
  String entiteIcone;
  String entiteCookie;

  /**
   * Var.enUS: entityDescription
   */
  String entiteDescription;
  String entiteNgsiType;

  Integer entiteHtmLigne;
  Integer entiteHtmCellule;
  Integer entiteLongeurMin;
  Integer entiteLongeurMax;
  String entiteFiwareType;
  String entiteMin;
  String entiteMax;
  String entiteDefaut;
  Boolean entiteCacherPOST;
  Boolean entiteCacherPATCH;
  Boolean entiteCacherRecherche;
  String entiteRechercherMaxVarJsonArray;
  String entiteRechercherMaxVarValeur;
  String entiteRechercherMaxVar;
  String entiteRechercherMaxValeur;

  Boolean entiteIndexe;
  Boolean entiteRequis;

  /**
   * Var.enUS: entityText
   */
  Boolean entiteTexte;

  /**
   * Var.enUS: entityLanguage
   */
  String entiteLangue;

  String entiteListeTypeJson;

  /**
   * Var.enUS: entitySuggested
   */
  Boolean entiteSuggere;

  Boolean entiteDocValues;

  Boolean entiteStocke;

  Boolean entiteVarTitre;
  Boolean entiteLien;

  Boolean entiteVarH1;

  Boolean entiteVarH2;

  Boolean entiteVarH3;

  /**
   * Var.enUS: entitySolrCanonicalName
   */
  String entiteSolrNomCanonique;

  /**
   * Var.enUS: entitySolrSimpleName
   */
  String entiteSolrNomSimple;

  /**
   * Var.enUS: entitySimpleNameVertxJson
   */
  String entiteNomSimpleVertxJson;

  /**
   * Var.enUS: entityCanonicalNameVertxJson
   */
  String entiteNomCanoniqueVertxJson;

  /**
   * Var.enUS: entityListSimpleNameVertxJson
   */
  String entiteListeNomSimpleVertxJson;

  /**
   * Var.enUS: entityListCanonicalNameVertxJson
   */
  String entiteListeNomCanoniqueVertxJson;

  /**
   * Var.enUS: entitySolrDocument
   */
  SolrDocument entiteDocumentSolr;

  /**
   * Var.enUS: searchRows
   */
  Integer rechercheLignes;

  Map<String, Integer> rechercheLigneMap;
  Map<String, String> entiteHtmLigneTitreActuelMap;
  Map<String, Boolean> entiteHtmLigneVerticaleActuelMap;
  String entiteHtmLigneTitre;
  String entiteHtmLigneTitreOuvert;
  String entiteHtmLigneEnTeteExpression;
  Boolean entiteHtmLigneVerticale;

  /**
   * Var.enUS: classVals
   */
  ToutEcrivain classeVals;

  /**
   * Var.enUS: entityXmlStack
   */
  protected Stack<String> entiteXmlPile = new Stack<String>();

  /**
   * Var.enUS: entityNumberStack
   */
  protected Stack<Integer> entiteNumeroPile = new Stack<Integer>();

  /**
   * Var.enUS: methodXmlStack
   */
  protected Stack<String> methodeXmlPile = new Stack<String>();

  /**
   * Var.enUS: entityNumberStack
   */
  protected Stack<Integer> methodeNumeroPile = new Stack<Integer>();

  ToutEcrivain wVarIndexe;
  ToutEcrivain wVarStocke;
  ToutEcrivain wRechercheVar;
  ToutEcrivain wVarRecherche;
  ToutEcrivain wVarSuggere;

  Boolean entiteFacetsTrouves;

  List<String> methodeExceptionsNomSimpleComplet;

  List<String> entiteFacets;

  Boolean entiteExact;

  Boolean entiteClePrimaire;

  Boolean entiteCleUnique;

  Boolean entiteCrypte;

  Boolean entiteSauvegarde;

  Boolean entiteIncremente;

  Boolean entiteIgnorer;

  Boolean entiteSetTrim;

  Boolean entiteSetLower;

  Boolean entiteSetUpper;

  Boolean entiteDeclarer;

  Boolean entiteRechercher;

  String entiteAttribuerNomCanoniqueGenApiServiceImpl;

  String entiteAttribuerNomSimpleGenApiServiceImpl;

  String entiteAttribuerNomSimpleApiServiceImpl;

  Boolean entiteAjouter;

  Boolean entiteSupprimer;

  Boolean entiteRecharger;

  Boolean entiteCles;

  Boolean entiteIndexeOuStocke;

  boolean entiteContientRequeteSite;

  String entiteHtmInfobulle;

  Integer entiteHtmColonne;

  List<String> entiteMethodesAvantVisibilite;

  List<String> entiteMethodesAvantVar;

  List<String> entiteMethodesAvantParamVar;

  List<String> entiteMethodesAvantParamNomSimple;

  List<Boolean> entiteMethodesAvantEcrire;

  List<Boolean> entiteMethodesAvantNomParam;

  List<String> entiteMethodesApresVisibilite;

  List<String> entiteMethodesApresVar;

  List<String> entiteMethodesApresParamVar;

  List<String> entiteMethodesApresParamNomSimple;

  List<Boolean> entiteMethodesApresNomParam;

  List<Boolean> entiteMethodesApresEcrire;

  List<String> entiteEcrireMethodes;

  List<String> entiteValsVar;

  List<String> entiteValsLangue;

  List<String> entiteValsCode;

  List<String> entiteValsValeur;

  String classePageChemin;

  String classeGenPageChemin;

  String classePageEmplacementTemplate;
  String classePageEmplacementCheminJinja;

  String classePageBarreLateraleTemplate;
  String classePageBarreLateraleCheminJinja;

  String classePageBoutonsRechercheTemplate;
  String classePageBoutonsRechercheCheminJinja;

  String classePageBoutonsPaginationTemplate;
  String classePageBoutonsPaginationCheminJinja;

  String classePageBoutonsFormulaireRechercheTemplate;
  String classePageBoutonsFormulaireRechercheCheminJinja;

  String classePageFormulaireRechercheTemplate;
  String classePageFormulaireRechercheCheminJinja;

  String classePageRechercheSuggereTemplate;
  String classePageRechercheSuggereCheminJinja;

  String classePageRechercheTemplate;
  String classePageRechercheCheminJinja;

  String classeGenPageRechercheSuperTemplate;
  String classeGenPageRechercheTemplate;
  String classeGenPageRechercheCheminJinja;

  String classePageEditionTemplate;
  String classePageEditionCheminJinja;

  String classeGenPageEditionSuperTemplate;
  String classeGenPageEditionTemplate;
  String classeGenPageEditionCheminJinja;

  String classePageUriMethode;

  // String classePageLangueNom;

  String classePageUriCss;
  String classePageUriJs;
  String classePageUriJsRecherche;
  String classePageUriJsEdition;
  Boolean classePageAvecTemplate;

  String classePageCheminCss;
  String classePageCheminJs;
  String classePageCheminJsRecherche;
  String classePageCheminJsEdition;

  // String uncapitalizeClasseApiClasseNomSimple;
  String varResultat;

  String classePageNomCanonique;

  JsonObject classePageLangueConfig;

  List<String> classeValsVar;
  List<String> classeValsLangue;
  List<String> classeValsValeur;

  ToutEcrivain wRecherche;
  ToutEcrivain wVarsFqJs;
  ToutEcrivain wPOST;
  ToutEcrivain wPUTImport;
  ToutEcrivain wPUTFusion;
  ToutEcrivain wPUTCopie;
  ToutEcrivain wPATCH;
  ToutEcrivain wDELETE;
  ToutEcrivain wDELETEFiltre;
  ToutEcrivain wSuggere;
  // ToutEcrivain wGetters;
  ToutEcrivain wTh;
  ToutEcrivain wTd;
  ToutEcrivain wFoot;
  List<String> wClasseApiMethodeMethodes;
  List<ToutEcrivain> wForms;
  ToutEcrivain wFormRecherche;
  ToutEcrivain wFormPOST;
  ToutEcrivain wFormPUTImport;
  ToutEcrivain wFormPUTFusion;
  ToutEcrivain wFormPUTCopie;
  ToutEcrivain wFormPage;
  ToutEcrivain wFormPATCH;
  ToutEcrivain wFormDELETE;
  ToutEcrivain wFormDELETEFiltre;
  ToutEcrivain wStyle;
  ToutEcrivain wJsHtmInit;
  ToutEcrivain wJsHtmEditionInit;
  ToutEcrivain wJsRechercheInit;
  ToutEcrivain wJsEditionInit;
  ToutEcrivain wWebsocket;
  ToutEcrivain wWebsocketInput1;
  ToutEcrivain wWebsocketInput2;
  ToutEcrivain wWebsocketInput3;
  ToutEcrivain wPks;
  
  /** 
   * r: wInitLoin
   * r.enUS: wInitDeep
   * r: wRequeteSite
   * r.enUS: wSiteRequest
   * r: wTexte
   * r.enUS: wText
   * r: wIndexer
   * r.enUS: wIndex
   * r: wObtenir
   * r.enUS: wObtain
   * r: wAttribuer
   * r.enUS: wAttribute
   * r: wPeupler
   * r.enUS: wPopulate
   * r: wStocker
   * r.enUS: wStore
   * r: wDefinir
   * r.enUS: wDefine
   * r: wSauvegarder
   * r.enUS: wSave
   * r: wApiGenererGet
   * r.enUS: wApiGenerateGet
   * r: wApiGenererPost
   * r.enUS: wApiGeneratePost
   * r: wApiGenererPut
   * r.enUS: wApiGeneratePut
   * r: wApiGenererPatch
   * r.enUS: wApiGeneratePatch
   * r: wPageHtmlSingulier
   * r.enUS: wPageHtmlSingular
   * r: wApiEntites
   * r.enUS: wApiEntities
   * r: wPageEntites
   * r.enUS: wPageEntities
   * r: ToutEcrivain
   * r.enUS: AllWriter
   */  
  public void genCodeInit() throws Exception {

    wInitLoin = ToutEcrivain.create();
    wStaticSet = ToutEcrivain.create();
    wstaticSearch = ToutEcrivain.create();
    wstaticSearchStr = ToutEcrivain.create();
    wstaticSearchFq = ToutEcrivain.create();
    wRequeteSite = ToutEcrivain.create();
    wIndexer = ToutEcrivain.create();
    wFacets = ToutEcrivain.create();
    wIndexerFacetFor = ToutEcrivain.create();
    classesNomSimpleFacetFor = new ArrayList<>();
    wTexte = ToutEcrivain.create();
    wObtenir = ToutEcrivain.create();
    wAttribuer = ToutEcrivain.create();
    wAttribuerSql = ToutEcrivain.create();
    wAttribuerSqlVars = ToutEcrivain.create();
    wAttribuerSqlNum = 1;
    wPut = ToutEcrivain.create();
    wPeupler = ToutEcrivain.create();
    wStocker = ToutEcrivain.create();
    wDefinir = ToutEcrivain.create();
    wDefinirObjet = ToutEcrivain.create();
    wVarsStatic = ToutEcrivain.create();
    wVarsQ = ToutEcrivain.create();
    wVarsFq = ToutEcrivain.create();
    wVarsGamme = ToutEcrivain.create();
    wNomAffichageMethode = ToutEcrivain.create();
    wNomAffichageStatic = ToutEcrivain.create();

    wClasseNomSimpleMethode = ToutEcrivain.create();
    wDescriptionMethode = ToutEcrivain.create();
    wNgsiTypeMethode = ToutEcrivain.create();
    wNgsiMethode = ToutEcrivain.create();
    wHtmColonneMethode = ToutEcrivain.create();
    wHtmLigneMethode = ToutEcrivain.create();
    wHtmCelluleMethode = ToutEcrivain.create();
    wLongeurMinMethode = ToutEcrivain.create();
    wLongeurMaxMethode = ToutEcrivain.create();
    wMinMethode = ToutEcrivain.create();
    wMaxMethode = ToutEcrivain.create();

    wPageEntites = ToutEcrivain.create();
    wPageTemplates = ToutEcrivain.create();
    wApiGet = ToutEcrivain.create();
    wApiGenererGet = ToutEcrivain.create();
    wApiAvantPost = ToutEcrivain.create();
    wApiSqlSelect = ToutEcrivain.create();
    wApiGenererPost = ToutEcrivain.create();
    wApiGenererDelete = ToutEcrivain.create();
    wApiGenererPutImport = ToutEcrivain.create();
    wApiGenererPutCopie = ToutEcrivain.create();
    wApiGenererPutFusion = ToutEcrivain.create();
    wApiAvantPatch = ToutEcrivain.create();
    wApiGenererPatch = ToutEcrivain.create();
    wPageHtmlSingulier = ToutEcrivain.create();
    wPageGet = ToutEcrivain.create();
    wRequeteApi = ToutEcrivain.create();
    wHashCode = ToutEcrivain.create();
    wToString = ToutEcrivain.create();
    wEquals = ToutEcrivain.create();
    wVarIndexe = ToutEcrivain.create();
    wVarStocke = ToutEcrivain.create();
    wRechercheVar = ToutEcrivain.create();
    wVarRecherche = ToutEcrivain.create();
    wVarSuggere = ToutEcrivain.create();
    auteurSqlCreate = ToutEcrivain.create();
    auteurSqlDrop = ToutEcrivain.create();
  }

  /**
   * Var.enUS: genCodeInitDeep
   * Param1.var.enUS: languageName
   * r: classeInitLoinExceptionNomSimple
   * r.enUS: classInitDeepExceptionSimpleName
   * r: classeInitLoinExceptions
   * r.enUS: classInitDeepExceptions
   * r: classeInitLoinException
   * r.enUS: classInitDeepException
   * r: classeInitLoin
   * r.enUS: classInitDeep
   * r: wInitLoin
   * r.enUS: wInitDeep
   * r.enUS: wIndex
   * r: PourClasse
   * r.enUS: ForClass
   * r: initLoin
   * r.enUS: initDeep
   * r: RequeteSite
   * r.enUS: SiteRequest
   * r: requeteSite
   * r.enUS: siteRequest
   * r: classeNomSimpleSuperGenerique
   * r.enUS: classSuperSimpleNameGeneric
   * r: classeNomSimple
   * r.enUS: classSimpleName
   * r: classeEtendBase
   * r.enUS: classExtendsBase
   * 
   * r: langueNom
   * r.enUS: languageName
   * r: classeParts
   * r.enUS: classParts
   * r: nomSimple
   * r.enUS: simpleName
   */
  public void genCodeInitLoin(String langueNom, JsonObject langueConfig) throws Exception {
    if(BooleanUtils.isTrue(classeInitLoin) && classePartsRequeteSite != null) {
      wInitLoin.l(); 
      wInitLoin.tl(1, "//////////////");
      wInitLoin.tl(1, "// ", langueConfig.getString(I18n.var_initLoin), " //");
      wInitLoin.tl(1, "//////////////");

      wInitLoin.l();
      if(classePromesse) {
        wInitLoin.tl(1, "public Future<", classeNomSimpleSuper, "<DEV>> ", langueConfig.getString(I18n.var_promesseLoin), classeNomSimple, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_) {");
        if(classeContientRequeteSite)
          wInitLoin.tl(2, "set", langueConfig.getString(I18n.var_RequeteSite), "_(", langueConfig.getString(I18n.var_requeteSite), "_);");
        wInitLoin.tl(2, "return ", langueConfig.getString(I18n.var_promesseLoin), classeNomSimple, "();");
        wInitLoin.tl(1, "}");
      } else {
        wInitLoin.t(1, "public ", classeNomSimple, " ", langueConfig.getString(I18n.var_initLoin), classeNomSimple, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_)");
        if(classeInitLoinExceptions.size() > 0) {
          wInitLoin.s(" throws ");
          for(int i = 0; i < classeInitLoinExceptions.size(); i++) {
  String classeInitLoinException = classeInitLoinExceptions.get(i);
            String classeInitLoinExceptionNomSimple = StringUtils.substringAfterLast(classeInitLoinException, ".");
            if(i > 0)
              wInitLoin.s(", ");
            wInitLoin.s(classeInitLoinExceptionNomSimple);
          }
        }
        wInitLoin.l(" {");
  //						if(contient", classePartsRequeteSite.nomSimple(langueNom), " && !StringUtils.equals(classeNomSimple, "", classePartsRequeteSite.nomSimple(langueNom), ""))
  //							tl(2, "((", classeNomSimple, ")this).setRequeteSite_(requeteSite);");
        if(classeContientRequeteSite)
          wInitLoin.tl(2, "set", langueConfig.getString(I18n.var_RequeteSite), "_(", langueConfig.getString(I18n.var_requeteSite), "_);");
        wInitLoin.tl(2, langueConfig.getString(I18n.var_initLoin), classeNomSimple, "();");
        wInitLoin.tl(2, "return (", classeNomSimple, ")this;");
        wInitLoin.tl(1, "}");
      }

      if(classePromesse) {
        wInitLoin.l();
        wInitLoin.tl(1, "public Future<", classeNomSimpleSuper, "<DEV>> ", langueConfig.getString(I18n.var_promesseLoin), classeNomSimple, "() {");
        wInitLoin.tl(2, "Promise<", classeNomSimpleSuper, "<DEV>> promise = Promise.promise();");
        if(classeInitLoinAvant) {
          if(BooleanUtils.isTrue(classeEtendBase)) {
            wInitLoin.tl(2, "super.", langueConfig.getString(I18n.var_promesseLoin), classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_requeteSite), "_).onSuccess(a -> {");
            wInitLoin.tl(3, "Promise<Void> promise2 = Promise.promise();");
            wInitLoin.tl(3, langueConfig.getString(I18n.var_promesse), classeNomSimple, "(promise2);");
            wInitLoin.tl(3, "promise2.future().onSuccess(b -> {");
            wInitLoin.tl(4, "promise.complete(this);");
            wInitLoin.tl(3, "}).onFailure(ex -> {");
            wInitLoin.tl(4, "promise.fail(ex);");
            wInitLoin.tl(3, "});");
          } else {
            wInitLoin.tl(2, "promise2.future().onSuccess(a -> {");
            wInitLoin.tl(3, "promise.complete(this);");
          }
        } else {
          wInitLoin.tl(2, "Promise<Void> promise2 = Promise.promise();");
          wInitLoin.tl(2, langueConfig.getString(I18n.var_promesse), classeNomSimple, "(promise2);");
          wInitLoin.tl(2, "promise2.future().onSuccess(a -> {");
          if(BooleanUtils.isTrue(classeEtendBase)) {
            wInitLoin.tl(3, "super.", langueConfig.getString(I18n.var_promesseLoin), classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_requeteSite), "_).onSuccess(b -> {");
            wInitLoin.tl(4, "promise.complete(this);");
            wInitLoin.tl(3, "}).onFailure(ex -> {");
            wInitLoin.tl(4, "promise.fail(ex);");
            wInitLoin.tl(3, "});");
          } else {
            wInitLoin.tl(3, "promise.complete(this);");
          }
        }
        wInitLoin.tl(2, "}).onFailure(ex -> {");
        wInitLoin.tl(3, "promise.fail(ex);");
        wInitLoin.tl(2, "});");
        wInitLoin.tl(2, "return promise.future();");
        wInitLoin.tl(1, "}");
      } else {
        wInitLoin.l();
        wInitLoin.t(1, "public void ", langueConfig.getString(I18n.var_initLoin), classeNomSimple, "()");
        if(classeInitLoinExceptions.size() > 0) {
          wInitLoin.s(" throws ");
          for(int i = 0; i < classeInitLoinExceptions.size(); i++) {
            String classeInitLoinException = classeInitLoinExceptions.get(i);
            String classeInitLoinExceptionNomSimple = StringUtils.substringAfterLast(classeInitLoinException, ".");
            if(i > 0)
              wInitLoin.s(", ");
            wInitLoin.s(classeInitLoinExceptionNomSimple);
          }
        }
        wInitLoin.l(" {");
        if(classeInitLoinAvant) {
          if(BooleanUtils.isTrue(classeEtendBase)) 
            wInitLoin.tl(2, "super.", langueConfig.getString(I18n.var_initLoin), classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_requeteSite), "_);");
        }
        wInitLoin.tl(2, "init", classeNomSimple, "();");
        if(!classeInitLoinAvant) {
          if(BooleanUtils.isTrue(classeEtendBase)) 
            wInitLoin.tl(2, "super.", langueConfig.getString(I18n.var_initLoin), classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_requeteSite), "_);");
        }
        wInitLoin.tl(1, "}");
      }

      wInitLoin.l();
      if(classePromesse) {
        wInitLoin.tl(1, "public Future<Void> ", langueConfig.getString(I18n.var_promesse), classeNomSimple, "(Promise<Void> promise) {");
        wInitLoin.tl(2, "Future.future(a -> a.complete()).compose(a -> {");
        wInitLoin.tl(3, "Promise<Void> promise2 = Promise.promise();");
        wInitLoin.tl(3, "try {");
      } else {
        wInitLoin.t(1, "public void init", classeNomSimple, "()");
        if(classeInitLoinExceptions.size() > 0) {
          wInitLoin.s(" throws ");
          for(int i = 0; i < classeInitLoinExceptions.size(); i++) {
            String classeInitLoinException = classeInitLoinExceptions.get(i);
            String classeInitLoinExceptionNomSimple = StringUtils.substringAfterLast(classeInitLoinException, ".");
            if(i > 0)
              wInitLoin.s(", ");
            wInitLoin.s(classeInitLoinExceptionNomSimple);
          }
        }
        wInitLoin.l(" {");
      }
    }
  }

  /**
   * Var.enUS: genCodeSiteRequest
   * Param1.var.enUS: languageName
   * r: classeInitLoin
   * r.enUS: classInitDeep
   * r: RequeteSite
   * r.enUS: SiteRequest
   * r: requeteSite
   * r.enUS: siteRequest
   * r: wRequeteSite
   * r.enUS: wSiteRequest
   * r: classeNomSimpleSuperGenerique
   * r.enUS: classSuperSimpleNameGeneric
   * r: classeNomSimple
   * r.enUS: classSimpleName
   * r: classeEtendBase
   * r.enUS: classExtendsBase
   * 
   * r: classeParts
   * r.enUS: classParts
   * r: langueNom
   * r.enUS: languageName
   * r: nomSimple
   * r.enUS: simpleName
   */
  public void genCodeRequeteSite(String langueNom, JsonObject langueConfig) throws Exception {
    if(BooleanUtils.isTrue(classeContientRequeteSite) && classePartsRequeteSite != null) {
      o = wRequeteSite;
      l(); 
      tl(1, "/////////////////");
      tl(1, "// ", langueConfig.getString(I18n.var_requeteSite), " //");
      tl(1, "/////////////////");
      l(); 
      tl(1, "public void ", langueConfig.getString(I18n.var_requeteSite), classeNomSimple, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_) {");
      if(BooleanUtils.isTrue(classeEtendBase)) 
        tl(3, "super.", langueConfig.getString(I18n.var_requeteSite), classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_requeteSite), "_);");
    }
  }

  /**
   * Var.enUS: genCodeObtain
   * Param1.var.enUS: languageName
   * r: wObtenir
   * r.enUS: wObtain
   * r: classeInitLoin
   * r.enUS: classInitDeep
   * r: classeNomSimple
   * r.enUS: classSimpleName
   * r: classeEtendBase
   * r.enUS: classExtendsBase
   * r: classeEstBase
   * r.enUS: classIsBase
   * r: obtenirPourClasse
   * r.enUS: obtainForClass
   * 
   * r: obtenir
   * r.enUS: obtain
   */
  public void genCodeObtenir(String langueNom, JsonObject langueConfig) throws Exception {
    o = wObtenir;
    if(classeInitLoin && (classeEtendBase || classeEstBase)) {
      l(); 
      tl(1, "/////////////");
      tl(1, "// ", langueConfig.getString(I18n.var_obtenir), " //");
      tl(1, "/////////////");
      tl(0);
      t(1);
      if(!classeEstBase)
        s("@Override ");
      l("public Object ", langueConfig.getString(I18n.var_obtenir), langueConfig.getString(I18n.var_PourClasse), "(String var) {");
      tl(2, "String[] vars = StringUtils.split(var, \".\");");
      tl(2, "Object o = null;");
      tl(2, "for(String v : vars) {");
      tl(3, "if(o == null)");
      tl(4, "o = ", langueConfig.getString(I18n.var_obtenir), classeNomSimple, "(v);");
      if(activerVertx && classePartsModeleBase != null) {
        tl(3, "else if(o instanceof ", classePartsModeleBase.nomSimple(langueNom), ") {");
        tl(4, classePartsModeleBase.nomSimple(langueNom), " ", StringUtils.uncapitalize(classePartsModeleBase.nomSimple(langueNom)), " = (", classePartsModeleBase.nomSimple(langueNom), ")o;");
        tl(4, "o = ", StringUtils.uncapitalize(classePartsModeleBase.nomSimple(langueNom)), ".", langueConfig.getString(I18n.var_obtenir), langueConfig.getString(I18n.var_PourClasse), "(v);");
        tl(3, "}");
      }
      tl(3, "else if(o instanceof Map) {");
      tl(4, "Map<?, ?> map = (Map<?, ?>)o;");
      tl(4, "o = map.get(v);");
      tl(3, "}");
      tl(2, "}");
      tl(2, "return o;");
      tl(1, "}");
      tl(1, "public Object ", langueConfig.getString(I18n.var_obtenir), classeNomSimple, "(String var) {");
      tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
      tl(2, "switch(var) {");
    }
  }

  /**
   * Var.enUS: genCodeAttribute
   * Param1.var.enUS: languageName
   * r: wAttribuer
   * r.enUS: wAttribute
   * r: classeNomSimple
   * r.enUS: classSimpleName
   * r: classeInitLoin
   * r.enUS: classInitDeep
   * r: classeEtendBase
   * r.enUS: classExtendsBase
   * r: classeEstBase
   * r.enUS: classIsBase
   * r: obtenirPourClasse
   * r.enUS: obtainForClass
   * r: attribuerPourClasse
   * r.enUS: attributeForClass
   * 
   * r: attribuer
   * r.enUS: attribute
   */
  public void genCodeAttribuer(String langueNom, JsonObject langueConfig) throws Exception {
    o = wAttribuer;
    if(classeInitLoin && (classeEtendBase || classeEstBase)) {
      l(); 
      tl(1, "///////////////");
      tl(1, "// ", langueConfig.getString(I18n.var_attribuer), " //");
      tl(1, "///////////////");
      tl(0);
      t(1);
      if(!classeEstBase)
        s("@Override ");
      s("public boolean ", langueConfig.getString(I18n.var_attribuer), langueConfig.getString(I18n.var_PourClasse), "(String var, Object val)");
      if(classeInitLoinExceptions.size() > 0) {
        s(" throws ");
        for(int i = 0; i < classeInitLoinExceptions.size(); i++) {
          String classeInitLoinException = classeInitLoinExceptions.get(i);
          String classeInitLoinExceptionNomSimple = StringUtils.substringAfterLast(classeInitLoinException, ".");
          if(i > 0)
            s(", ");
          s(classeInitLoinExceptionNomSimple);
        }
      }
      l(" {");
      tl(2, "String[] vars = StringUtils.split(var, \".\");");
      tl(2, "Object o = null;");
      tl(2, "for(String v : vars) {");
      tl(3, "if(o == null)");
      tl(4, "o = ", langueConfig.getString(I18n.var_attribuer), classeNomSimple + "(v, val);");
      if(activerVertx && classePartsModeleBase != null) {
        tl(3, "else if(o instanceof ", classePartsModeleBase.nomSimple(langueNom), ") {");
        tl(4, classePartsModeleBase.nomSimple(langueNom), " ", StringUtils.uncapitalize(classePartsModeleBase.nomSimple(langueNom)), " = (", classePartsModeleBase.nomSimple(langueNom), ")o;");
        tl(4, "o = ", StringUtils.uncapitalize(classePartsModeleBase.nomSimple(langueNom)), ".", langueConfig.getString(I18n.var_attribuer), langueConfig.getString(I18n.var_PourClasse), "(v, val);");
        tl(3, "}");
      }
      tl(2, "}");
      tl(2, "return o != null;");
      tl(1, "}");
      tl(1, "public Object ", langueConfig.getString(I18n.var_attribuer), classeNomSimple, "(String var, Object val) {");
      tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
      tl(2, "switch(var) {");

    }
  }

  /**
   * Var.enUS: genCodePut
   * Param1.var.enUS: languageName
   * r: classeNomSimple
   * r.enUS: classSimpleName
   * r: classeSauvegarde
   * r.enUS: classSaved
   * r: classeInitLoin
   * r.enUS: classInitDeep
   * r: classeEtendBase
   * r.enUS: classExtendsBase
   * r: classeEstBase
   * r.enUS: classIsBase
   * r: putPourClasse
   * r.enUS: putForClass
   * 
   */
  public void genCodePut(String langueNom, JsonObject langueConfig) throws Exception {
    o = wPut;
    if(classeSauvegarde) {
//		if(classeInitLoin && (classeEtendBase || classeEstBase)) {
      l(); 
      tl(1, "/////////");
      tl(1, "// put //");
      tl(1, "/////////");
      l();
      t(1);
      if(!classeEstBase)
        s("@Override ");
      l("public void ", langueConfig.getString(I18n.var_put), langueConfig.getString(I18n.var_PourClasse), "(JsonObject requeteJson) {");
      tl(2, "Set<String> vars = requeteJson.fieldNames();");
      tl(2, "for(String var : vars) {");
      tl(3, "put", classeNomSimple + "(requeteJson, var);");
      tl(2, "}");
      tl(1, "}");
      l();
      t(1);
      if(!classeEstBase)
        s("@Override ");
      l("public Boolean ", langueConfig.getString(I18n.var_put), classeNomSimple, "(JsonObject requeteJson, String var) {");
      tl(2, "switch(var) {");
    }
  }

  /**
   * Var.enUS: genCodePopulate
   * Param1.var.enUS: languageName
   * r: wPeupler
   * r.enUS: wPopulate
   * r: classeNomSimple
   * r.enUS: classSimpleName
   * r: classeSauvegarde
   * r.enUS: classSaved
   * r: peuplerPourClasse
   * r.enUS: populateForClass
   * 
   * r: peupler
   * r.enUS: populate
   * r: sauvegardes
   * r.enUS: saves
   */
  public void genCodePeupler(String langueNom, JsonObject langueConfig) throws Exception {
    o = wPeupler;
    if(classeSauvegarde) {
    }
  }

    /**
     * Traduire: false
     * Translator object for escaping Java.
     *
     * While {@link #escapeJava(String)} is the expected method of use, this
     * object allows the Java escaping functionality to be used
     * as the foundation for a custom translator.
     */ 
    public static final CharSequenceTranslator ESCAPE_JAVA;
    static { 
        final Map<CharSequence, CharSequence> escapeJavaMap = new HashMap<>();
        escapeJavaMap.put("\"", "\\\"");
        escapeJavaMap.put("\\", "\\\\");
        ESCAPE_JAVA = new AggregateTranslator(
                new LookupTranslator(Collections.unmodifiableMap(escapeJavaMap)),
                new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE)
        );
    }

    // Java and JavaScript
    //--------------------------------------------------------------------------
    /**
     * <p>Escapes the characters in a {@code String} using Java String rules.</p>
     *
     * <p>Deals correctly with quotes and control-chars (tab, backslash, cr, ff, etc.) </p>
     *
     * <p>So a tab becomes the characters {@code '\\'} and
     * {@code 't'}.</p>
     *
     * <p>The only difference between Java strings and JavaScript strings
     * is that in JavaScript, a single quote and forward-slash (/) are escaped.</p>
     *
     * <p>Example:</p>
     * <pre>
     * input string: He didn't say, "Stop!"
     * output string: He didn't say, \"Stop!\"
     * </pre>
     *
     * @param input  String to escape values in, may be null
     * @return String with escaped values, {@code null} if null string input
     */
    public static final String escapeJava(final String input) {
        return org.computate.frFR.java.EcrireGenClasse.ESCAPE_JAVA.translate(input);
    }

  /**
   * Var.enUS: genCodeClassBegin
   * Param1.var.enUS: languageName
   * r: auteurGenClasse
   * r.enUS: writerGenClass
   * r: classeNomEnsemble
   * r.enUS: classPackageName
   * r: classeImportationsGen
   * r.enUS: classImportsGen
   * r: classeImportation
   * r.enUS: classImport
   * r: ecrireCommentairePart
   * r.enUS: writeCommentPart
   * r: classeCommentaire
   * r.enUS: classComment
   * r: urlSolrComputate
   * r.enUS: solrUrlComputate
   * r: partEstClasse
   * r.enUS: partIsClass
   * r: classeNomCanonique
   * r.enUS: classCanonicalName
   * r: langueNom
   * r.enUS: languageName
   * r: classeEtendGen
   * r.enUS: classExtendsGen
   * r: entiteVar
   * r.enUS: entityVar
   * r: Trouver la classe 
   * r.enUS: Find the class 
   * r: dans Solr
   * r.enUS: in Solr
   * r: classeNomSimpleGen
   * r.enUS: classSimpleNameGen
   * r: classeParametreTypeNoms
   * r.enUS: classParameterTypeNames
   * r: classeParametreTypeNom
   * r.enUS: classParameterTypeName
   * r: classeNomSimpleSuperGenerique
   * r.enUS: classSuperSimpleNameGeneric
   * r: classeSuperParametreTypeNoms
   * r.enUS: classSuperParameterTypeNames
   * r: classeSuperParametreTypeNom
   * r.enUS: classSuperParameterTypeName
   * r: classeSauvegarde
   * r.enUS: classSaved
   * r: classeNomSimple
   * r.enUS: classSimpleName
   * r: classeValVarNumero
   * r.enUS: classValVarNumber
   * 
   * r: _UnNom 
   * r.enUS: _AName 
   * r: _Ce 
   * r.enUS: _This 
   * r: _CeNom 
   * r.enUS: _ThisName 
   * r: _Un 
   * r.enUS: _A 
   * r: _LeNom 
   * r.enUS: _TheName 
   * r: _NomSingulier 
   * r.enUS: _NameSingular 
   * r: _NomPluriel 
   * r.enUS: _NamePlural 
   * r: _NomActuel 
   * r.enUS: _NameActual 
   * r: _TousNom 
   * r.enUS: _AllName 
   * r: _Tous 
   * r.enUS: _All 
   * r: _RechercherTousNomPar 
   * r.enUS: _SearchAllNameBy 
   * r: _RechercherTousNom 
   * r.enUS: _SearchAllName 
   * r: _Titre 
   * r.enUS: _Title 
   * r: _LesNoms 
   * r.enUS: _TheNames 
   * r: _AucunNomTrouve 
   * r.enUS: _NoNameFound 
   * r: _NomVar 
   * r.enUS: _NameVar 
   * r: _DeNom 
   * r.enUS: _OfName 
   * r: _AdjectifPluriel 
   * r.enUS: _AdjectivePlural 
   * r: _Adjectif 
   * r.enUS: _Adjective 
   * r: _Couleur 
   * r.enUS: _Color 
   * r: _IconeGroupe 
   * r.enUS: _IconGroup 
   * r: _IconeNom 
   * r.enUS: _IconName 
   * 
   * r: entiteValsEcrivain
   * r.enUS: entityValsWriter
   * r: entiteValsVar
   * r.enUS: entityValsVar
   * r: entiteValsLangue
   * r.enUS: entityValsLanguage
   * r: entiteValsValeur
   * r.enUS: entityValsValue
   * r: entiteValVarLangueAncien
   * r.enUS: entityValVarLanguageOld
   * r: entiteValVarLangue
   * r.enUS: entityValVarLanguage
   * r: entiteValVarAncien
   * r.enUS: entityValVarOld
   * r: entiteValVar
   * r.enUS: entityValVar
   * r: entiteValLangue
   * r.enUS: entityValLanguage
   * r: entiteValValeur
   * r.enUS: entityValValue
   * 
   * r: classeValsVar
   * r.enUS: classValsVar
   * r: classeValsLangue
   * r.enUS: classValsLanguage
   * r: classeValsValeur
   * r.enUS: classValsValue
   * r: classeValVarLangueAncien
   * r.enUS: classValVarLanguageOld
   * r: classeValVarLangue
   * r.enUS: classValVarLanguage
   * r: classeValVarAncien
   * r.enUS: classValVarOld
   * r: classeValVar
   * r.enUS: classValVar
   * r: classeValLangue
   * r.enUS: classValLanguage
   * r: classeValValeur
   * r.enUS: classValValue
   * r: classeVals
   * r.enUS: classVals
   * 
   * r: classeContexte
   * r.enUS: classContext
   * r: classeNomAdjectifSingulier
   * r.enUS: contextNameAdjectiveSingular
   * r: classeAdjectifPluriel
   * r.enUS: contextAdjectivePlural
   * r: classeNomAdjectifPluriel
   * r.enUS: contextNameAdjectivePlural
   * r: classeTitre
   * r.enUS: contextTitle
   * r: classeCeNom
   * r.enUS: contextThisName
   * r: classeCe
   * r.enUS: contextThis
   * r: classeUnNom
   * r.enUS: contextAName
   * r: classeUn
   * r.enUS: contextA
   * r: classeLeNom
   * r.enUS: contextTheName
   * r: classeLesNoms
   * r.enUS: contextTheNames
   * r: classeCree
   * r.enUS: contextCreated
   * r: classeModifie
   * r.enUS: contextModified
   * r: classeNomSingulier
   * r.enUS: contextNameSingular
   * r: classeNomPluriel
   * r.enUS: contextNamePlural
   * r: classeNomActuel
   * r.enUS: contextActualName
   * r: classeTousNom
   * r.enUS: contextAllName
   * r: classeRechercherTousNomPar
   * r.enUS: contextSearchAllNameBy
   * r: classeRechercherTousNom
   * r.enUS: contextSearchAllName
   * r: LesNom
   * r.enUS: TheName
   * r: classeTous
   * r.enUS: contextAll
   * r: classeAucunNomTrouve
   * r.enUS: contextNoNameFound
   * r: classeNomVar
   * r.enUS: contextNameVar
   * r: classeDeNom
   * r.enUS: contextOfName
   * r: classeNom
   * r.enUS: contextName
   * r: classeCouleur
   * r.enUS: contextColor
   * r: classeIconeGroupe
   * r.enUS: contextIconGroup
   * r: classeIconeNom
   * r.enUS: contextIconName
   * r: classeLignes
   * r.enUS: contextRows
   * r: classeVideoId
   * r.enUS: contextVideoId
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
   * r: classeDoc
   * r.enUS: classDoc
   * r: classeApiMethode
   * r.enUS: classApiMethod
   * r: classePageMethode
   * r.enUS: classPageMethod
   * r: classePageUriMethode
   * r.enUS: classPageUriMethod
   * r: classePageNomSimple
   * r.enUS: classPageSimpleName
   * r: classe
   * r.enUS: context
   */
  public void genCodeClasseDebut(String langueNom, JsonObject langueConfig) throws Exception {
    o = auteurGenClasseDebut;

    if(classeDroitAuteur != null)
      l(classeDroitAuteur);
    l("package ", classeNomEnsemble, ";");
    l();
    if(classeImportationsGen.size() > 0) { 
      for(String classeImportation : classeImportationsGen) {
        l("import ", classeImportation, ";");
      }
      l();
    }

    o = auteurGenClasseFin;
    s("public abstract class ", classeNomSimpleGen);
    if(classeParametreTypeNoms != null && classeParametreTypeNoms.size() > 0) {
      s("<");
      for(int j = 0; j < classeParametreTypeNoms.size(); j++) {
        String classeParametreTypeNom = classeParametreTypeNoms.get(j);
        if(j > 0)
          s(", ");
        s(classeParametreTypeNom);
      }
      s(">");
    }
    else {
      s("<DEV>");
    }
    if(classeNomSimpleSuperGenerique != null && !"java.lang.Object".equals(classeNomSimpleSuperGenerique) && !"DEV".equals(classeNomSimpleSuperGenerique)) {
      s(" extends ");
//						s(classeNomSimpleSuper);
      
      if(classeNomSimpleSuperGenerique != null) {
        s(classeNomSimpleSuperGenerique);
      }
//						else if(classeSuperParametreTypeNoms != null && classeSuperParametreTypeNoms.size() > 0) {
////							s("<");
//							for(int j = 0; j < classeSuperParametreTypeNoms.size(); j++) {
//								String classeSuperParametreTypeNom = classeSuperParametreTypeNoms.get(j);
//								if(i > 0)
//									s();
//								s(classeSuperParametreTypeNom);
//							}
////							s(">");
//						}	
    }
    s(" {\n");

    if(activerLog) {
      tl(1, "protected static final Logger LOG = LoggerFactory.getLogger(", classeNomSimple, ".class);");
    }
    if(classePartsVerticle != null && classeNomSimple.equals(classePartsVerticle.nomSimple(langueNom))) {
      l();
      tl(1, "public static final String ", langueConfig.getString(I18n.var_SITE_NOM), " = \"", siteNom, "\";");
    }
    if(classeValsVar != null && classeValsLangue != null && classeValsValeur != null) {
      String classeValVarAncien = null;
      Integer classeValVarNumero = 0;
      String classeValVar = null;
      String classeValLangue = null;
      String classeValVarLangue = null;
      String classeValVarLangueAncien = null;
      String classeValValeur = null;

      for(int j = 0; j < classeValsVar.size(); j++) {
        classeValVar = classeValsVar.get(j);
        classeValLangue = classeValsLangue.get(j);
        classeValVarLangue = classeValVar + classeValLangue;
        classeValValeur = classeValsValeur.get(j);

        if(!StringUtils.equals(classeValVarLangue, classeValVarLangueAncien) && (StringUtils.equals(classeValVarLangueAncien, classeValVarAncien + langueNom))) {
          t(1, "public static final String ", classeValVarAncien, "_", classeValLangue, " = ");
          for(int k = 1; k <= classeValVarNumero; k++) {
            if(k > 1)
              s(" + ");
            s(classeValVarAncien, k, "_", classeValLangue);
          }
          l(";");
          classeValVarNumero = 0;
        }

        if(StringUtils.equals(langueNom, classeValLangue)) {
          classeValVarNumero++;
          tl(1, "public static final String ", classeValVar, classeValVarNumero, "_", classeValLangue, " = \"", escapeJava(classeValValeur), "\";");
          if(!classeVals.getEmpty())
            classeVals.s(", ");
          classeVals.s(classeValVar, classeValVarNumero, "_", classeValLangue);
        }

        classeValVarAncien = classeValVar;
        classeValVarLangueAncien = classeValVarLangue;
      }
      if(StringUtils.equals(langueNom, classeValLangue)) {
        classeValVarAncien = classeValVar;
        classeValVarLangueAncien = classeValVarLangue;
        classeValVar = null;
  
        if(classeValVarAncien != null && !StringUtils.equals(classeValVar, classeValVarLangueAncien)) {
          t(1, "public static final String ", classeValVarAncien, "_", classeValLangue, " = ");
          for(int k = 1; k <= classeValVarNumero; k++) {
            if(k > 1)
              s(" + ");
            s(classeValVarAncien, k, "_", classeValLangue);
          }
          l(";");
          classeValVarNumero = 0;
        }
      }
    }
    
    //////////////
    // Contexte //
    //////////////
    
    if(classeContexte) {

      for(String langueNom2 : toutesLangues) {
      
        l();
  
        classeVideoId = classeDoc.getString("classeVideoId" + "_" + langueNom2 + "_stored_string");
        classeUri = classeDoc.getString("classeUri" + "_" + langueNom2 + "_stored_string");
        classeDescription = classeDoc.getString("classeDescription" + "_" + langueNom2 + "_stored_string");
        classeUnNom = classeDoc.getString("classeUnNom" + "_" + langueNom2 + "_stored_string");
        classeNomSingulier = classeDoc.getString("classeNomSingulier" + "_" + langueNom2 + "_stored_string");
        classeNomPluriel = classeDoc.getString("classeNomPluriel" + "_" + langueNom2 + "_stored_string");
        classeNomVar = classeDoc.getString("classeNomVar" + "_" + langueNom2 + "_stored_string");
        classeApiUri = classeDoc.getString("classeApiUri" + "_" + langueNom2 + "_stored_string");
        classeApiUriPageRecherche = classeDoc.getString("classeApiUri" + langueConfig.getString(I18n.var_PageRecherche) + "_" + langueNom2 + "_stored_string");
        classeApiUriPageEdition = classeDoc.getString("classeApiUri" + langueConfig.getString(I18n.var_PageEdition) + "_" + langueNom2 + "_stored_string");
        classeAdjectif = classeDoc.getString("classeAdjectif" + "_" + langueNom2 + "_stored_string");
        classeAdjectifPluriel = classeDoc.getString("classeAdjectifPluriel" + "_" + langueNom2 + "_stored_string");
        classeAdjectifVar = classeDoc.getString("classeAdjectifVar" + "_" + langueNom2 + "_stored_string");
        classeNomAdjectifSingulier = classeDoc.getString("classeNomAdjectifSingulier" + "_" + langueNom2 + "_stored_string");
        classeNomAdjectifPluriel = classeDoc.getString("classeNomAdjectifPluriel" + "_" + langueNom2 + "_stored_string");
        classeCe = classeDoc.getString("classeCe" + "_" + langueNom2 + "_stored_string");
        classeUn = classeDoc.getString("classeUn" + "_" + langueNom2 + "_stored_string");
        classeNomActuel = classeDoc.getString("classeNomActuel" + "_" + langueNom2 + "_stored_string");
        classeTousNom = classeDoc.getString("classeTousNom" + "_" + langueNom2 + "_stored_string");
        classeRechercherTousNomPar = classeDoc.getString("classeRechercherTousNomPar" + "_" + langueNom2 + "_stored_string");
        classeRechercherTousNom = classeDoc.getString("classeRechercherTousNom" + "_" + langueNom2 + "_stored_string");
        classeLesNoms = classeDoc.getString("classeLesNoms" + "_" + langueNom2 + "_stored_string");
        classeTitre = classeDoc.getString("classeTitre" + "_" + langueNom2 + "_stored_string");
        classeH1 = classeDoc.getString("classeH1" + "_" + langueNom2 + "_stored_string");
        classeH2 = classeDoc.getString("classeH2" + "_" + langueNom2 + "_stored_string");
        classeH3 = classeDoc.getString("classeH3" + "_" + langueNom2 + "_stored_string");
        classeAucunNomTrouve = classeDoc.getString("classeAucunNomTrouve" + "_" + langueNom2 + "_stored_string");
        classeUnNomAdjectif = classeDoc.getString("classeUnNomAdjectif" + "_" + langueNom2 + "_stored_string");
        classeCeNom = classeDoc.getString("classeCeNom" + "_" + langueNom2 + "_stored_string");
        classeLeNom = classeDoc.getString("classeLeNom" + "_" + langueNom2 + "_stored_string");
        classeDeNom = classeDoc.getString("classeDeNom" + "_" + langueNom2 + "_stored_string");

        if(classeUri != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_Uri), "_", langueNom2, " = ", q(classeUri), ";");

        if(classeDescription != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_Description), "_", langueNom2, " = ", q(classeDescription), ";");

        if(classeUnNom != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_UnNom), "_", langueNom2, " = ", q(classeUnNom), ";");
        
        if(classeCe != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_Ce), "_", langueNom2, " = ", q(classeCe), ";");
        
        if(classeCeNom != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_CeNom), "_", langueNom2, " = ", q(classeCeNom), ";");
        
        if(classeUn != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_Un), "_", langueNom2, " = ", q(classeUn), ";");
        
        if(classeLeNom != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_LeNom), "_", langueNom2, " = ", q(classeLeNom), ";");
        
        if(classeNomSingulier != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_NomSingulier), "_", langueNom2, " = ", q(classeNomSingulier), ";");
        
        if(classeNomPluriel != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_NomPluriel), "_", langueNom2, " = ", q(classeNomPluriel), ";");
        
        if(classeNomActuel != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_NomActuel), "_", langueNom2, " = ", q(classeNomActuel), ";");
        
        if(classeTous != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_Tous), "_", langueNom2, " = ", q(classeTous), ";");
        
        if(classeTousNom != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_TousNom), "_", langueNom2, " = ", q(classeTousNom), ";");
        
        if(classeRechercherTousNomPar != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_RechercherTousNomPar), "_", langueNom2, " = ", q(classeRechercherTousNomPar), ";");
        
        if(classeRechercherTousNom != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_RechercherTousNom), "_", langueNom2, " = ", q(classeRechercherTousNom), ";");
        
        if(classeH1 != null)
          tl(1, "public static final String H1", "_", langueNom2, " = ", q(classeH1), ";");
        
        if(classeH2 != null)
          tl(1, "public static final String H2", "_", langueNom2, " = ", q(classeH2), ";");
        
        if(classeH3 != null)
          tl(1, "public static final String H3", "_", langueNom2, " = ", q(classeH3), ";");
        
        if(classeTitre != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_Titre), "_", langueNom2, " = ", q(classeTitre), ";");
        
        if(classeLesNoms != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_LesNoms), "_", langueNom2, " = ", q(classeLesNoms), ";");
        
        if(classeAucunNomTrouve != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_AucunNomTrouve), "_", langueNom2, " = ", q(classeAucunNomTrouve), ";");
        
        if(classeNomVar != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_NomVar), "_", langueNom2, " = ", q(classeNomVar), ";");
        if(classeApiUri != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_ApiUri), "_", langueNom2, " = ", q(classeApiUri), ";");
        if(classeApiUriPageRecherche != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_ApiUri), langueConfig.getString(I18n.var_PageRecherche), "_", langueNom2, " = ", q(classeApiUriPageRecherche), ";");
        if(classeApiUriPageEdition != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_ApiUri), langueConfig.getString(I18n.var_PageEdition), "_", langueNom2, " = ", q(classeApiUriPageEdition), ";");
        
        if(classeDeNom != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_DeNom), "_", langueNom2, " = ", q(classeDeNom), ";");
        
        if(classeAdjectif != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_Adjectif), "_", langueNom2, " = ", q(classeAdjectif), ";");
        
        if(classeAdjectifPluriel != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_AdjectifPluriel), "_", langueNom2, " = ", q(classeAdjectifPluriel), ";");
        
        if(classeAdjectifVar != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_AdjectifVar), "_", langueNom2, " = ", q(classeAdjectifVar), ";");
        
        if(classeUnNomAdjectif != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_UnNomAdjectif), "_", langueNom2, " = ", q(classeUnNomAdjectif), ";");
        
        if(classeNomAdjectifSingulier != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_NomAdjectifSingulier), "_", langueNom2, " = ", q(classeNomAdjectifSingulier), ";");
        
        if(classeNomAdjectifPluriel != null)
          tl(1, "public static final String ", langueConfig.getString(I18n.var_NomAdjectifPluriel), "_", langueNom2, " = ", q(classeNomAdjectifPluriel), ";");

        List<String> classeApiMethodes = Optional.ofNullable(Optional.ofNullable(doc.getJsonArray("classeApiMethodes_" + langueNom2 + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList())).orElse(Arrays.asList());
        for(String classePageMethode : classeApiMethodes) {
          String classePageUriMethode = classeDoc.getString("classeApiUri" + classePageMethode + "_" + langueNom2 + "_stored_string");
          if(classePageUriMethode != null) {
            if(classePageUriMethode != null) {
              tl(1, "public static final String ", classePageMethode, "_", langueNom2, "_OpenApiUri", " = ", q(classePageUriMethode), ";");
              tl(1, "public static final String ", classePageMethode, "_", langueNom2, "_StringFormatUri", " = ", q(classePageUriMethode.replaceAll("\\{[^\\}]+\\}", "%s")), ";");
              tl(1, "public static final String ", classePageMethode, "_", langueNom2, "_StringFormatUrl", " = ", q("%s" + classePageUriMethode.replaceAll("\\{[^\\}]+\\}", "%s")), ";");
            }
            if(classePageUriMethode != null) {
              // tl(1, "public static final String ", classePageMethode, "_", langueNom2, "_ImageUri", " = ", q("/png", classePageUriMethode, "-999.png"), ";");
            }
          }
        }
      }

      classeCouleur = classeDoc.getString("classeCouleur_stored_string");
      classeIcone = classeDoc.getString("classeIcone_stored_string");
      classeLignes = (Integer)classeDoc.getInteger("classeLignes_stored_int");
      
      l();
      if(classeCouleur != null)
        tl(1, "public static final String ", langueConfig.getString(I18n.var_Couleur), " = ", q(classeCouleur), ";");
      
      if(classeIcone != null)
        tl(1, "public static final String ", langueConfig.getString(I18n.var_Icone), " = ", q(classeIcone), ";");
      
      if(classeLignes != null)
        tl(1, "public static final Integer ", langueConfig.getString(I18n.var_Lignes), " = ", classeLignes, ";");
    }
  }

  /**
   * Var.enUS: genCodeConstructor
   * Param1.var.enUS: languageName
   * r: constructeurExceptionsNomSimpleCompletListe
   * r.enUS: constructorExceptionsSimpleNameCompleteList
   * r: constructeurExceptionsNomSimpleComplet
   * r.enUS: constructorExceptionsSimpleNameComplete
   * r: constructeurAnnotationsNomSimpleCompletListe
   * r.enUS: constructorAnnotationsSimpleNameCompleteList
   * r: constructeurAnnotationsNomSimpleComplet
   * r.enUS: constructorAnnotationsSimpleNameComplete
   * r: constructeurAnnotationsBlocCodeListe
   * r.enUS: constructorAnnotationsCodeBlockList
   * r: constructeurAnnotationsBlocCode
   * r.enUS: constructorAnnotationsCodeBlock
   * r: constructeurParamVar
   * r.enUS: constructorParamVar
   * r: constructeurDoc
   * r.enUS: constructorDoc
   * r: constructeurQdox
   * r.enUS: constructorQdox
   * r: constructeurCommentaire
   * r.enUS: constructorComment
   * r: constructeurCle
   * r.enUS: constructorKey
   * r: constructeurCodeSourceLangue
   * r.enUS: constructorSourceCodeLanguage
   * r: constructeurCodeSource
   * r.enUS: constructorSourceCode
   * r: constructeurEstPublic
   * r.enUS: constructorIsPublic
   * r: constructeurEstProtege
   * r.enUS: constructorIsProtected
   * r: constructeurEstPrive
   * r.enUS: constructorIsPrivate
   * r: constructeurEstStatique
   * r.enUS: constructorIsStatic
   * r: constructeurEstFinale
   * r.enUS: constructorIsFinal
   * r: constructeurEstAbstrait
   * r.enUS: constructorIsAbstract
   * r: constructeurEstNatif
   * r.enUS: constructorIsNative
   * r: constructeurAnnotations
   * r.enUS: constructorAnnotations
   * r: constructeurAnnotationLangue
   * r.enUS: constructorAnnotationLanguage
   * r: constructeurParamsQdox
   * r.enUS: constructorParamsQdox
   * r: constructeurExceptionsQdox
   * r.enUS: constructorExceptionsQdox
   * r: constructeurParamNum
   * r.enUS: constructorParamNum
   * r: constructeurParamQdox
   * r.enUS: constructorParamQdox
   * r: constructeurParamsVarLangue
   * r.enUS: constructorParamsVarLanguage
   * r: constructeurParamVarLangue
   * r.enUS: constructorParamVarLanguage
   * r: constructeurParamsVarLangue
   * r.enUS: constructorParamsVar
   * r: constructeurParamsNomSimpleComplet
   * r.enUS: constructorParamsSimpleNameComplete
   * r: constructeurParamsVar
   * r.enUS: constructorParamsVar
   * r: constructeurParamsArgsVariables
   * r.enUS: constructorParamsVariableArgs
   * r: constructeurParamVarLangue
   * r.enUS: constructorParamVar
   * r: constructeurParamClassePartsLangue
   * r.enUS: constructorParamClassPartsLanguage
   * r: constructeurParamClasseParts
   * r.enUS: constructorParamClassParts
   * r: constructeurParamNomSimpleComplet
   * r.enUS: constructorParamSimpleNameComplete
   * r: constructeurParamVar
   * r.enUS: constructeurParamVar
   * r: constructeurParamArgsVariables
   * r.enUS: constructorParamVariableArgs
   * r: constructeurAnnotationBlocCode
   * r.enUS: constructorAnnotationCodeBlock
   * r: constructeurExceptionQdox
   * r.enUS: constructorExceptionQdox
   * r: constructeurExceptionNomSimpleComplet
   * r.enUS: constructorExceptionSimpleNameComplete
   * r: constructeurAnnotation
   * r.enUS: constructorAnnotation
   * r: partEstConstructeur
   * r.enUS: partIsConstructor
   * r: langueNom
   * r.enUS: languageName
   * r: ecrireCommentaire
   * r.enUS: writeComment
   * r: classeNomSimpleGen
   * r.enUS: classSimpleNameGen
   */ 
  public void genCodeConstructeur(String langueNom, JsonObject langueConfig) throws Exception {
    o = auteurGenClasseFin;
    String constructeurCodeSource = doc.getString("constructeurCodeSource_" + langueNom + "_stored_string");
    String constructeurCommentaire = doc.getString("constructeurCommentaire_" + langueNom + "_stored_string");
    List<String> constructeurExceptionsNomSimpleComplet = Optional.ofNullable(doc.getJsonArray("constructeurExceptionsNomSimpleComplet_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    List<String> constructeurAnnotationsNomSimpleCompletListe = Optional.ofNullable(doc.getJsonArray("constructeurAnnotationsNomSimpleComplet_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    List<String> constructeurAnnotationsBlocCodeListe = Optional.ofNullable(doc.getJsonArray("constructeurAnnotationsBlocCode_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());

    l(""); 
    ecrireCommentaire(constructeurCommentaire, 1);
    if(constructeurAnnotationsNomSimpleCompletListe != null && constructeurAnnotationsBlocCodeListe != null) {
      for(int j = 0; j < constructeurAnnotationsNomSimpleCompletListe.size(); j++) {
        String constructeurAnnotationNomSimpleComplet = constructeurAnnotationsNomSimpleCompletListe.get(j);
        String constructeurAnnotationBlocCode = constructeurAnnotationsBlocCodeListe.get(j);
        l("\t@", constructeurAnnotationNomSimpleComplet, constructeurAnnotationBlocCode, "");
      }
    }
    s("\t");
    if(BooleanUtils.isTrue(doc.getBoolean("constructeurEstPublic_stored_boolean")))
      s("public ");
    if(BooleanUtils.isTrue(doc.getBoolean("constructeurEstProtege_stored_boolean")))
      s("protected ");
    if(BooleanUtils.isTrue(doc.getBoolean("constructeurEstPrive_stored_boolean")))
      s("private ");
    if(BooleanUtils.isTrue(doc.getBoolean("constructeurEstStatique_stored_boolean")))
      s("static ");
    if(BooleanUtils.isTrue(doc.getBoolean("constructeurEstFinale_stored_boolean")))
      s("final ");
    if(BooleanUtils.isTrue(doc.getBoolean("constructeurEstAbstrait_stored_boolean")))
      s("abstract ");
    if(BooleanUtils.isTrue(doc.getBoolean("constructeurEstNatif_stored_boolean")))
      s("native ");

    s(classeNomSimpleGen);
    s("(");
    List<String> constructeurParamsNomSimpleComplet = Optional.ofNullable(doc.getJsonArray("constructeurParamsNomSimpleComplet_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList()); 
    List<String> constructeurParamsVar = Optional.ofNullable(doc.getJsonArray("constructeurParamsVar_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    List<Boolean> constructeurParamsArgsVariables = Optional.ofNullable(doc.getJsonArray("constructeurParamsArgsVariables_stored_booleans")).orElse(new JsonArray()).stream().map(v -> (Boolean)v).collect(Collectors.toList());
    if(constructeurParamsNomSimpleComplet != null && constructeurParamsVar != null && constructeurParamsNomSimpleComplet.size() == constructeurParamsVar.size()) {
      for(int j = 0; j < constructeurParamsVar.size(); j++) {
        String constructeurParamNomSimpleComplet = constructeurParamsNomSimpleComplet.get(j);
        String constructeurParamVar = constructeurParamsVar.get(j);
        Boolean constructeurParamArgsVariables = constructeurParamsArgsVariables.get(j);
        if(j > 0)
          s(", ");
        s(constructeurParamNomSimpleComplet);

        if(constructeurParamArgsVariables)
          s("...");
        else
          s(" ");

        s(constructeurParamVar);
      }
    }    
    s(")");
    if(constructeurExceptionsNomSimpleComplet != null && constructeurExceptionsNomSimpleComplet.size() > 0) {
      s(" throws ");
      for(int j = 0; j < constructeurExceptionsNomSimpleComplet.size(); j++) {
        String constructeurExceptionNomSimpleComplet = constructeurExceptionsNomSimpleComplet.get(j);
        if(j > 0)
          s(", ");
        s(constructeurExceptionNomSimpleComplet);
      }
    }
    s(" {");
    s(constructeurCodeSource);
    l("}");
  }


  /**
   * Var.enUS: genCodeEntity
   * Param1.var.enUS: languageName
   * 
   * r: methodeVar
   * r.enUS: methodVar
   * r: methodeValsEcrivain
   * r.enUS: methodValsWriter
   * r: methodeNumeroPile
   * r.enUS: methodNumberStack
   * r: methodeXmlPile
   * r.enUS: methodXmlStack
   * r: methodeValVarAncien
   * r.enUS: methodValVarOld
   * r: methodeValVarNumero
   * r.enUS: methodValVarNumber
   * r: methodeValVarLangueAncien
   * r.enUS: methodValVarLanguageOld
   * r: methodeValVarLangue
   * r.enUS: methodValVarLanguage
   * r: methodeValLangue
   * r.enUS: methodValLanguage
   * r: methodeValVar
   * r.enUS: methodValVar
   * r: methodeValsVar
   * r.enUS: methodValsVar
   * r: methodeValsLangue
   * r.enUS: methodValsLanguage
   * r: methodeValsValeur
   * r.enUS: methodValsValue
   * r: methodeValValeur
   * r.enUS: methodValValue
   **/
  public void genCodeMethode(String langueNom, JsonObject langueConfig) throws Exception {
    o = auteurGenClasseFin;

    methodeVar = doc.getString("methodeVar_" + langueNom + "_stored_string");

    List<String> methodeValsVar = Optional.ofNullable(doc.getJsonArray("methodeValsVar_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    List<String> methodeValsLangue = Optional.ofNullable(doc.getJsonArray("methodeValsLangue_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    List<String> methodeValsValeur = Optional.ofNullable(doc.getJsonArray("methodeValsValeur_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    if(methodeValsVar.size() > 0 && methodeValsLangue.size() > 0 && methodeValsValeur.size() > 0) {
      String methodeValVarAncien = null;
      Integer methodeValVarNumero = 0;
      String methodeValVar = null;
      String methodeValLangue = null;
      String methodeValVarLangue = null;
      String methodeValVarLangueAncien = null;
      String methodeValCode = null;
      String methodeValValeur = null;

      methodeXmlPile = new Stack<String>();
      methodeNumeroPile = new Stack<Integer>();
      for(int j = 0; j < methodeValsVar.size(); j++) {
        methodeValVar = methodeValsVar.get(j);
        methodeValLangue = methodeValsLangue.get(j);
        if(StringUtils.isBlank(methodeValLangue))
          methodeValLangue = langueNom;
        methodeValVarLangue = methodeValVar + methodeValLangue;
        methodeValValeur = methodeValsValeur.get(j);

        if(!StringUtils.equals(methodeValVarLangue, methodeValVarLangueAncien) && (StringUtils.equals(methodeValVarLangueAncien, methodeValVarAncien + langueNom))) {
          t(1, "public static final String ", methodeVar, methodeValVarAncien, " = ");
          for(int k = 1; k <= methodeValVarNumero; k++) {
            if(k > 1)
              s(" + ");
            s(methodeVar, methodeValVarAncien, k);
          }
          l(";");
          methodeValVarNumero = 0;
        }

        if(StringUtils.equals(langueNom, methodeValLangue)) {
          methodeValVarNumero++;
          tl(1, "public static final String ", methodeVar, methodeValVar, methodeValVarNumero, " = \"", escapeJava(methodeValValeur), "\";");
          if(!classeVals.getEmpty())
            classeVals.s(", ");
          classeVals.s(methodeVar, methodeValVar, methodeValVarNumero);
        }

        methodeValVarAncien = methodeValVar;
        methodeValVarLangueAncien = methodeValVarLangue;
      }
      if(StringUtils.equals(langueNom, methodeValLangue)) {
        methodeValVarAncien = methodeValVar;
        methodeValVarLangueAncien = methodeValVarLangue;
        methodeValVar = null;
  
        if(methodeValVarAncien != null && !StringUtils.equals(methodeValVar, methodeValVarLangueAncien)) {
          t(1, "public static final String ", methodeVar, methodeValVarAncien, " = ");
          for(int k = 1; k <= methodeValVarNumero; k++) {
            if(k > 1)
              s(" + ");
            s(methodeVar, methodeValVarAncien, k);
          }
          l(";");
          methodeValVarNumero = 0;
        }
      }
      l();
    }
  }

  /**
   * Var.enUS: genCodeEntity
   * Param1.var.enUS: languageName
   * 
   * r: methodeExceptionsNomSimpleComplet
   * r.enUS: methodExceptionsSimpleNameComplete
   * r: methodeExceptionNomSimpleComplet
   * r.enUS: methodExceptionSimpleNameComplete
   * r: cssNumero
   * r.enUS: cssNumber
   * r: numeroStr
   * r.enUS: numberStr
   * r: classeVal
   * r.enUS: classVal
   * r: entiteValsEcrivain
   * r.enUS: entityValsWriter
   * r: entiteNumeroPile
   * r.enUS: entityNumberStack
   * r: entiteXmlPile
   * r.enUS: entityXmlStack
   * r: entiteValVarAncien
   * r.enUS: entityValVarOld
   * r: entiteValVarNumero
   * r.enUS: entityValVarNumber
   * r: entiteValVarLangueAncien
   * r.enUS: entityValVarLanguageOld
   * r: entiteValVarLangue
   * r.enUS: entityValVarLanguage
   * r: entiteValLangue
   * r.enUS: entityValLanguage
   * r: entiteValVar
   * r.enUS: entityValVar
   * r: entiteValsVar
   * r.enUS: entityValsVar
   * r: entiteValsLangue
   * r.enUS: entityValsLanguage
   * r: entiteValsValeur
   * r.enUS: entityValsValue
   * r: entiteValValeur
   * r.enUS: entityValValue
   * r: classesSuperEtMoiSansGen
   * r.enUS: entitySuperClassesAndMeWithoutGen
   * r: entiteVarCapitalise
   * r.enUS: entityVarCapitalized
   * r: entiteInitialise
   * r.enUS: entityInitialized
   * r: entiteNomCanoniqueCompletGenerique
   * r.enUS: entityCanonicalNameCompleteGeneric
   * r: entiteNomCanoniqueComplet
   * r.enUS: entityCanonicalNameComplete
   * r: entiteNomCanoniqueGenerique
   * r.enUS: entityCanonicalNameGeneric
   * r: entiteNomCanonique
   * r.enUS: entityCanonicalName
   * r: entiteNomSimpleCompletGenerique
   * r.enUS: entitySimpleNameCompleteGeneric
   * r: entiteNomSimpleComplet
   * r.enUS: entitySimpleNameComplete
   * r: entiteNomSimpleGenerique
   * r.enUS: entitySimpleNameGeneric
   * r: entiteNomSimple
   * r.enUS: entitySimpleName
   * r: entiteNomCompletGenerique
   * r.enUS: entityCompleteNameGeneric
   * r: entiteCommentaire
   * r.enUS: entityComment
   * r: entiteCouverture
   * r.enUS: entityWrap
   * r: entiteInitLoin
   * r.enUS: entityInitDeep
   * r: methodeExceptionsNomSimpleComplet
   * r.enUS: methodExceptionsSimpleNameComplete
   * r: langueNom
   * r.enUS: languageName
   * r: auteurGenClasse
   * r.enUS: writerGenClass
   * r: ligneCommentaire
   * r.enUS: commentLine
   * r: " est défini comme null avant d'être initialisé. "
   * r.enUS: " is defined as null before being initialized. "
   * r: " est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. "
   * r.enUS: " is for wrapping a value to assign to this entity at initialization. "
   * r: "Il est construit avant d'être initialisé avec le constructeur par défaut "
   * r.enUS: "It is constructed before being initialized with the constructor by default "
   * r: " est l'entité déjà construit. "
   * r.enUS: " is the entity already constructed. "
   * r: " afin que toute exception lors de l'initialisation est gérée par le servlet. "
   * r.enUS: " so that every exception at initialization is managed by the servlet. "
   * r: entiteSolrNomCanonique
   * r.enUS: entitySolrCanonicalName
   * r: entiteSolrNomSimple
   * r.enUS: entitySolrSimpleName
   * r: entiteListeNomSimpleVertxJson
   * r.enUS: entityListSimpleNameVertxJson
   * r: entiteListeNomCanoniqueVertxJson
   * r.enUS: entityListCanonicalNameVertxJson
   * 
   * r: entiteMethodesAvantVisibilite
   * r.enUS: entityMethodsBeforeVisibility
   * r: entiteMethodesAvantVar
   * r.enUS: entityMethodsBeforeVar
   * r: entiteMethodesAvantParamVar
   * r.enUS: entityMethodsBeforeParamVar
   * r: entiteMethodesAvantParamNomSimple
   * r.enUS: entityMethodsBeforeSimpleName
   * r: entiteMethodesAvantNomParam
   * r.enUS: entityMethodsBeforeParamName
   * r: entiteMethodesAvantEcrire
   * r.enUS: entityMethodsBeforeWrite
   * r: entiteMethodesApresVisibilite
   * r.enUS: entityMethodsAfterVisibility
   * r: entiteMethodesApresVar
   * r.enUS: entityMethodsAfterVar
   * r: entiteMethodesApresParamVar
   * r.enUS: entityMethodsAfterParamVar
   * r: entiteMethodesApresParamNomSimple
   * r.enUS: entityMethodsAfterSimpleName
   * r: entiteMethodesApresNomParam
   * r.enUS: entityMethodsAfterParamName
   * r: entiteMethodesApresEcrire
   * r.enUS: entityMethodsAfterWrite
   * 
   * r: entiteMethodeAvantVisibilite
   * r.enUS: entityMethodBeforeVisibility
   * r: entiteMethodeAvantVar
   * r.enUS: entityMethodBeforeVar
   * r: entiteMethodeAvantParamVar
   * r.enUS: entityMethodBeforeParamVar
   * r: entiteMethodeAvantParamNomSimple
   * r.enUS: entityMethodBeforeSimpleName
   * r: entiteMethodeAvantNomParam
   * r.enUS: entityMethodBeforeParamName
   * r: entiteMethodeAvantEcrire
   * r.enUS: entityMethodBeforeWrite
   * r: entiteMethodeApresVisibilite
   * r.enUS: entityMethodAfterVisibility
   * r: entiteMethodeApresVar
   * r.enUS: entityMethodAfterVar
   * r: entiteMethodeApresParamVar
   * r.enUS: entityMethodAfterParamVar
   * r: entiteMethodeApresParamNomSimple
   * r.enUS: entityMethodAfterSimpleName
   * r: entiteMethodeApresNomParam
   * r.enUS: entityMethodAfterParamName
   * r: entiteMethodeApresEcrire
   * r.enUS: entityMethodAfterWrite
   * 
   * r: methodeParamsNomCanonique
   * r.enUS: methodParamCanonicalNames
   * r: methodeParamNomCanonique
   * r.enUS: methodParamCanonicalName
   * r: entiteMethodesAvant
   * r.enUS: entityMethodsBefore
   * r: entiteMethodesApres
   * r.enUS: entityMethodsAfter
   * r: entiteOptionsRecherche
   * r.enUS: entityOptionsSearch
   * r: entiteOptionsTrouves
   * r.enUS: entityOptionsFound
   * r: entiteOptionsVar
   * r.enUS: entityOptionsVar
   * r: entiteOptionsLangue
   * r.enUS: entityOptionsLanguage
   * r: entiteOptionsDescription
   * r.enUS: entityOptionsDescription
   * r: entiteOptionVar
   * r.enUS: entityOptionVar
   * r: entiteOptionLangue
   * r.enUS: entityOptionLanguage
   * r: entiteOptionDescription
   * r.enUS: entityOptionDescription
   * r: entiteOptions
   * r.enUS: entityOptions
   * r: entiteIndexeOuStocke
   * r.enUS: entityIndexedOrStored
   * r: entiteExact
   * r.enUS: entityExact
   * r: entiteClePrimaire
   * r.enUS: entityPrimaryKey
   * r: entiteCleUnique
   * r.enUS: entityUniqueKey
   * r: entiteCrypte
   * r.enUS: entityEncrypted
   * r: entiteSuggere
   * r.enUS: entitySuggested
   * r: entiteSauvegarde
   * r.enUS: entitySaved
   * r: entiteIndexe
   * r.enUS: entityIndexed
   * r: entiteIncremente
   * r.enUS: entityIncremented
   * r: entiteStocke
   * r.enUS: entityStored
   * r: entiteTexte
   * r.enUS: entityText
   * r: entiteLangue
   * r.enUS: entityLanguage
   * r: entiteNomAffichage
   * r.enUS: entityDisplayName
   * r: entiteIgnorer
   * r.enUS: entityIgnored
   * r: entiteDeclarer
   * r.enUS: entityDeclared
   * r: entiteRechercher
   * r.enUS: entitySearch
   * r: entiteAjouter
   * r.enUS: entityAdd
   * r: entiteSupprimer
   * r.enUS: entityDelete
   * r: entiteModifier
   * r.enUS: entityModify
   * r: entiteRecharger
   * r.enUS: entityRefresh
   * r: entiteMultiligne
   * r.enUS: entityMultiLine
   * r: entiteSignature
   * r.enUS: entitySignature
   * r: entiteCles
   * r.enUS: entityKeys
   * r: entiteAttribuerNomCanonique
   * r.enUS: entityAttributeCanonicalName
   * r: entiteAttribuerNomSimple
   * r.enUS: entityAttributeSimpleName
   * r: entiteAttribuerVarUrlPageAffichage
   * r.enUS: entityAttributeVarUrlPageAffichage
   * r: entiteAttribuerVarUrlPageEdition
   * r.enUS: entityAttributeVarUrlPageEdition
   * r: entiteAttribuerVarId
   * r.enUS: entityAttributeVarId
   * r: entiteAttribuerVarTitre
   * r.enUS: entityAttributeVarTitle
   * r: entiteAttribuerVarDescription
   * r.enUS: entityAttributeVarDescription
   * r: entiteAttribuerVarImageUrl
   * r.enUS: entityAttributeVarImageUrl
   * r: entiteAttribuerVar
   * r.enUS: entityAttributeVar
   * r: entiteAttribuer
   * r.enUS: entityAttribute
   * r: entiteAttribuer
   * r.enUS: entityAttribute
   * r: entiteDefinir
   * r.enUS: entityDefined
   * r: entiteHtmInfobulle
   * r.enUS: entityHtmInfobulle
   * r: urlSolrComputate
   * r.enUS: solrUrlComputate
   * r: partEstEntite
   * r.enUS: partIsEntity
   * r: classeNomCanonique
   * r.enUS: classCanonicalName
   * r: Trouver l'entité 
   * r.enUS: Find the entity 
   * r: dans Solr
   * r.enUS: in Solr
   * r: classeNomSimple
   * r.enUS: classSimpleName
   * r: definirPourClasse
   * r.enUS: defineForClass
   * r: classeSauvegarde
   * r.enUS: classSaved
   * r: classeVarClePrimaire
   * r.enUS: classVarPrimaryKey
   * r: classeVarCleUnique
   * r.enUS: classVarUniqueKey
   * r: classeApiUri
   * r.enUS: classApiUri
   * r: wInitLoin
   * r.enUS: wInitDeep
   * r: classeInitLoin
   * r.enUS: classInitDeep
   * r: wRequeteSite
   * r.enUS: wSiteRequest
   * r: wIndexer
   * r.enUS: wIndex
   * r: classeIndexe
   * r.enUS: classIndexed
   * r: entiteVarCrypte
   * r.enUS: entityVarEncrypted
   * r: valCrypte
   * r.enUS: valEncrypted
   * r: requeteSite
   * r.enUS: siteRequest
   * r: crypterStr
   * r.enUS: encryptStr
   * r: entiteVarIncremente
   * r.enUS: entityVarIncremented
   * r: entiteVarSuggere
   * r.enUS: entityVarSuggested
   * r: entiteVarIndexe
   * r.enUS: entityVarIndexed
   * r: entiteVarStocke
   * r.enUS: entityVarStored
   * r: entiteIndice
   * r.enUS: entityIndex
   * r: wObtenir
   * r.enUS: wObtain
   * r: wDefinir
   * r.enUS: wDefine
   * r: classeEtendBase
   * r.enUS: classExtendsBase
   * r: classeEstBase
   * r.enUS: classIsBase
   * r: wAttribuer
   * r.enUS: wAttribute
   * r: requeteJson
   * r.enUS: requestJson
   * r: wPeupler
   * r.enUS: wPopulate
   * r: wStocker
   * r.enUS: wStore
   * r: siteCrypte
   * r.enUS: siteEncrypted
   * r: wSauvegarder
   * r.enUS: wSave
   * r: wApiEntites
   * r.enUS: wApiEntities
   * r: ENTITE_VAR_INDEXE_
   * r.enUS: ENTITY_VAR_INDEXED_
   * r: ENTITE_VAR_STOCKE_
   * r.enUS: ENTITY_VAR_STORED_
   * r: ENTITE_VAR_CRYPTE_
   * r.enUS: ENTITY_VAR_ENCRYPTED_
   * r: ENTITE_VAR_
   * r.enUS: ENTITY_VAR_
   * r: _ATTRIBUER_
   * r.enUS: _ATTRIBUTE_
   * r: wApiGenererGet
   * r.enUS: wApiGenerateGet
   * r: reponseServeur
   * r.enUS: serverResponse
   * r: entiteValeur
   * r.enUS: fieldValue
   * r: wApiGenererPost
   * r.enUS: wApiGeneratePost
   * r: classeRolesTrouves
   * r.enUS: classRolesFound
   * r: wApiGenererPut
   * r.enUS: wApiGeneratePut
   * r: wApiGenererPatch
   * r.enUS: wApiGeneratePatch
   * r: classeEtendGen
   * r.enUS: classExtendsGen
   * r: PourClasse
   * r.enUS: ForClass
   * r: PourClasse
   * r.enUS: ForClass
   * r: initLoin
   * r.enUS: initDeep
   * r: ToutEcrivain
   * r.enUS: AllWriter
   * r: Droits
   * r.enUS: Rights
   * r: entiteSuffixeType
   * r.enUS: entityTypeSuffix
   * r: classeEcrireMethode
   * r.enUS: classWriteMethod
   * r: entiteEcrireMethode
   * r.enUS: entityWriteMethod
   * r: classeEcrireMethode
   * r.enUS: classWriteMethod
   * r: classeEcrireEcrivain
   * r.enUS: classWriteWriter
   * r: classeMethodeVar
   * r.enUS: classMethodVar
   * r: classeEntiteVars
   * r.enUS: classEntityVars
   * r: UtilisateurId
   * r.enUS: UserId
   * r: utilisateurId
   * r.enUS: userId
   * 
   * r: nomSimple
   * r.enUS: simpleName
   * r: classeParts
   * r.enUS: classParts
   * r: nomAffichage
   * r.enUS: displayName
   * r: ClientSolr
   * r.enUS: SolrClient
   * r: RequeteSite
   * r.enUS: SiteRequest
   * r: requete
   * r.enUS: request
   * r: entite
   * r.enUS: entity
   * r: champ
   * r.enUS: field
   * r: Chaine
   * r.enUS: Chain
   * r: clePrimaire
   * r.enUS: primaryKey
   * r: cleUnique
   * r.enUS: uniqueKey
   * r: entiteVar
   * r.enUS: entityVar
   * r: lignes
   * r.enUS: lines
   * r: ligne
   * r.enUS: line
   * r: premier
   * r.enUS: first
   * r: tabulations
   * r.enUS: tabs
   * r: nomCanonique
   * r.enUS: canonicalName
   * r: Couverture
   * r.enUS: Wrap
   * r: numero
   * r.enUS: number
   * r: sauvegardes
   * r.enUS: saves
   * r: definir
   * r.enUS: define
   */
  public void genCodeEntite(String langueNom, JsonObject langueConfig) throws Exception {
    o = auteurGenClasseFin;
    Integer partNumero = (Integer)doc.getInteger("partNumero_stored_int");
    classeNomSimple = doc.getString("classeNomSimple_" + langueNom + "_stored_string");
    classeNomCanonique = doc.getString("classeNomCanonique_" + langueNom + "_stored_string");
    entiteVar = doc.getString("entiteVar_" + langueNom + "_stored_string");
    entiteVarUrl = doc.getString("entiteVarUrl_" + langueNom + "_stored_string");
    entiteDescription = doc.getString("entiteDescription_" + langueNom + "_stored_string");
    entiteNgsiType = doc.getString("entiteNgsiType_stored_string");
    entiteSuffixeType = doc.getString("entiteSuffixeType_stored_string");
    entiteSuffixeSolr = doc.getString("entiteSuffixeSolr_stored_string");
    entiteVarCapitalise = doc.getString("entiteVarCapitalise_" + langueNom + "_stored_string");
    entiteAttribuerVarSuggere = doc.getString("entiteAttribuerVarSuggere_" + langueNom + "_stored_string");
    entiteNomCanonique = doc.getString("entiteNomCanonique_" + langueNom + "_stored_string");
    entiteNomCanoniqueGenerique = doc.getString("entiteNomCanoniqueGenerique_" + langueNom + "_stored_string");
    entiteNomSimpleComplet = doc.getString("entiteNomSimpleComplet_" + langueNom + "_stored_string");
    entiteNomSimpleGenerique = doc.getString("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
    entiteNomSimpleCompletGenerique = doc.getString("entiteNomSimpleCompletGenerique_" + langueNom + "_stored_string");
    entiteNomSimple = doc.getString("entiteNomSimple_" + langueNom + "_stored_string");
    entiteCommentaire = doc.getString("entiteCommentaire_" + langueNom + "_stored_string");
    entiteVarParam = doc.getString("entiteVarParam_" + langueNom + "_stored_string");
    entiteAttribuerTypeJson = doc.getString("entiteAttribuerTypeJson_stored_string");
    entiteCouverture = doc.getBoolean("entiteCouverture_stored_boolean");
    entitePromesse = doc.getBoolean("entitePromesse_stored_boolean");
    entiteInitialise = doc.getBoolean("entiteInitialise_stored_boolean");
    entiteInitLoin = doc.getBoolean("entiteInitLoin_stored_boolean");
    entiteFacetsTrouves = Optional.ofNullable(doc.getBoolean("entiteFacetsTrouves_stored_boolean")).orElse(false);
    methodeExceptionsNomSimpleComplet = Optional.ofNullable(doc.getJsonArray("methodeExceptionsNomSimpleComplet_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    entiteFacets = Optional.ofNullable(doc.getJsonArray("entiteFacets_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());

    Boolean entiteEstSubstitue = doc.getBoolean("entiteEstSubstitue_stored_boolean");
    if(entiteNomCanonique != null && !entiteEstSubstitue) {
      entiteSolrNomCanonique = doc.getString("entiteSolrNomCanonique_stored_string");
      entiteSolrNomSimple = doc.getString("entiteSolrNomSimple_stored_string");
      entiteNomSimpleVertxJson = doc.getString("entiteNomSimpleVertxJson_stored_string");
      entiteNomCanoniqueVertxJson = doc.getString("entiteNomCanoniqueVertxJson_stored_string");
      entiteListeNomSimpleVertxJson = doc.getString("entiteListeNomSimpleVertxJson_stored_string");
      entiteListeNomCanoniqueVertxJson = doc.getString("entiteListeNomCanoniqueVertxJson_stored_string");

      entiteExact = doc.getBoolean("entiteExact_stored_boolean");
      entiteClePrimaire = doc.getBoolean("entiteClePrimaire_stored_boolean");
      entiteCleUnique = doc.getBoolean("entiteCleUnique_stored_boolean");
      entiteCrypte = doc.getBoolean("entiteCrypte_stored_boolean");
      entiteSuggere = doc.getBoolean("entiteSuggere_stored_boolean");
      entiteSauvegarde = doc.getBoolean("entiteSauvegarde_stored_boolean");
      entiteDocValues = doc.getBoolean("entiteDocValues_stored_boolean");
      entiteIndexe = doc.getBoolean("entiteIndexe_stored_boolean");
      entiteStocke = doc.getBoolean("entiteStocke_stored_boolean");
      entiteTexte = doc.getBoolean("entiteTexte_stored_boolean");
      entiteLangue = doc.getString("entiteLangue_stored_string");
      entiteIncremente = doc.getBoolean("entiteIncremente_stored_boolean");
      entiteIgnorer = doc.getBoolean("entiteIgnorer_stored_boolean");
      entiteSetTrim = doc.getBoolean("entiteSetTrim_stored_boolean");
      entiteSetLower = doc.getBoolean("entiteSetLower_stored_boolean");
      entiteSetUpper = doc.getBoolean("entiteSetUpper_stored_boolean");
      entiteDeclarer = doc.getBoolean("entiteDeclarer_stored_boolean");
      entiteRechercher = doc.getBoolean("entiteRechercher_stored_boolean");
      entiteAttribuer = BooleanUtils.isTrue(doc.getBoolean("entiteAttribuer_stored_boolean"));
      entiteAttribuerEtendModeleBase = BooleanUtils.isTrue(doc.getBoolean("entiteAttribuerEtendModeleBase_stored_boolean"));
      entiteAttribuerEtendResultatBase = BooleanUtils.isTrue(doc.getBoolean("entiteAttribuerEtendResultatBase_stored_boolean"));
      entiteAttribuerNomCanonique = doc.getString("entiteAttribuerNomCanonique_" + langueNom + "_stored_string");
      entiteAttribuerNomSimple = doc.getString("entiteAttribuerNomSimple_" + langueNom + "_stored_string");
      entiteAttribuerNomCanoniqueGenApiServiceImpl = doc.getString("entiteAttribuerNomCanoniqueGenApiServiceImpl_" + langueNom + "_stored_string");
      entiteAttribuerNomSimpleGenApiServiceImpl = doc.getString("entiteAttribuerNomSimpleGenApiServiceImpl_" + langueNom + "_stored_string");
      entiteAttribuerNomSimpleApiServiceImpl = doc.getString("entiteAttribuerNomSimpleApiServiceImpl_" + langueNom + "_stored_string");
      entiteAttribuerVar = doc.getString("entiteAttribuerVar_" + langueNom + "_stored_string");
      entiteAttribuerVarUrlPageAffichage = doc.getString("entiteAttribuerVarUrlPageAffichage_" + langueNom + "_stored_string");
      entiteAttribuerVarUrlPageEdition = doc.getString("entiteAttribuerVarUrlPageEdition_" + langueNom + "_stored_string");
      entiteAttribuerVarId = doc.getString("entiteAttribuerVarId_" + langueNom + "_stored_string");
      entiteAttribuerVarTitre = doc.getString("entiteAttribuerVarTitre_" + langueNom + "_stored_string");
      entiteAttribuerVarDescription = doc.getString("entiteAttribuerVarDescription_" + langueNom + "_stored_string");
      entiteAttribuerVarImageUrl = doc.getString("entiteAttribuerVarImageUrl_" + langueNom + "_stored_string");
      entiteAttribuerUtilisateurEcrire = BooleanUtils.isTrue(doc.getBoolean("entiteAttribuerUtilisateurEcrire_stored_boolean"));
      entiteAttribuerSessionEcrire = BooleanUtils.isTrue(doc.getBoolean("entiteAttribuerSessionEcrire_stored_boolean"));
      entiteAttribuerPublicLire = BooleanUtils.isTrue(doc.getBoolean("entiteAttribuerPublicLire_stored_boolean"));
      entiteAttribuerClasseRoles = Optional.ofNullable(doc.getJsonArray("entiteAttribuerClasseRoles_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteAttribuerClasseRolesLangue = Optional.ofNullable(doc.getJsonArray("entiteAttribuerClasseRolesLangue_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteAjouter = doc.getBoolean("entiteAjouter_stored_boolean");
      entiteSupprimer = doc.getBoolean("entiteSupprimer_stored_boolean");
      entiteModifier = doc.getBoolean("entiteModifier_stored_boolean");
      entiteRecharger = doc.getBoolean("entiteRecharger_stored_boolean");
      entiteMultiligne = doc.getBoolean("entiteMultiligne_stored_boolean");
      entiteCouleur = doc.getBoolean("entiteCouleur_stored_boolean");
      entiteSignature = doc.getBoolean("entiteSignature_stored_boolean");
      entiteImageBase64Url = doc.getString("entiteImageBase64Url_" + langueNom + "_stored_string");
      entiteCles = doc.getBoolean("entiteCles_stored_boolean");
      entiteIndexeOuStocke = doc.getBoolean("entiteIndexeOuStocke_stored_boolean");
      entiteDefinir = doc.getBoolean("entiteDefinir_stored_boolean");
      entiteContientRequeteSite = BooleanUtils.isTrue(doc.getBoolean("entiteContientRequeteSite_stored_boolean"));
      entiteListeTypeJson = doc.getString("entiteListeTypeJson_stored_string");
      entiteAttribuerContexteUnNom = doc.getString("entiteAttribuerContexteUnNom_" + langueNom + "_stored_string");
      entiteAttribuerContexteNomPluriel = doc.getString("entiteAttribuerContexteNomPluriel_" + langueNom + "_stored_string");
      entiteAttribuerContexteCouleur = doc.getString("entiteAttribuerContexteCouleur_stored_string");
      entiteAttribuerContexteIcone = doc.getString("entiteAttribuerContexteIcone_stored_string");
      entiteAttribuerPageUri = doc.getString("entiteAttribuerPageUri_" + langueNom + "_stored_string");
      entiteTypeJson = doc.getString("entiteTypeJson_stored_string");
  
      entiteNomAffichage = doc.getString("entiteNomAffichage_" + langueNom + "_stored_string");
      entiteHtmInfobulle = doc.getString("entiteHtmInfobulle_" + langueNom + "_stored_string");
      entiteHtmColonne = doc.getInteger("entiteHtmColonne_stored_int");
      entiteHtmLigne = doc.getInteger("entiteHtmLigne_stored_int");
      entiteHtmLigneTitre = doc.getString("entiteHtmLigneTitre_" + langueNom + "_stored_string");
      entiteHtmLigneTitreOuvert = doc.getString("entiteHtmLigneTitre_" + langueNom + "_stored_string");
      entiteHtmLigneVerticale = doc.getBoolean("entiteHtmLigneVerticale_stored_boolean");
      entiteHtmLigneEnTeteExpression = doc.getString("entiteHtmLigneEnTeteExpression_stored_string");
      entiteHtmCellule = doc.getInteger("entiteHtmCellule_stored_int");
      entiteLongeurMin = doc.getInteger("entiteLongeurMin_stored_int");
      entiteLongeurMax = doc.getInteger("entiteLongeurMax_stored_int");
      entiteMin = doc.getString("entiteMin_stored_string");
      entiteMax = doc.getString("entiteMax_stored_string");
      entiteDefaut = doc.getString("entiteDefaut_stored_string");
      entiteRequis = doc.getBoolean("entiteRequis_stored_boolean");
      entiteHtml = doc.getBoolean("entiteHtml_stored_boolean");
      entiteModeDArrondi = doc.getString("entiteModeDArrondi_stored_string", "HALF_UP");
      entitePrecision = doc.getInteger("entitePrecision_stored_int", 2);
      entiteEtape = doc.getString("entitePrecision_stored_string");
      Boolean entiteEstListe = (StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()));

      Boolean entiteEstZonedDateTime = StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())
            || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, ZonedDateTime.class.getCanonicalName());

      entiteClassesSuperEtMoiSansGen = Optional.ofNullable(doc.getJsonArray("classesSuperEtMoiSansGen_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
  
      entiteMethodesAvantVisibilite = Optional.ofNullable(doc.getJsonArray("entiteMethodesAvantVisibilite_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteMethodesAvantVar = Optional.ofNullable(doc.getJsonArray("entiteMethodesAvantVar_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteMethodesAvantParamVar = Optional.ofNullable(doc.getJsonArray("entiteMethodesAvantParamVar_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteMethodesAvantParamNomSimple = Optional.ofNullable(doc.getJsonArray("entiteMethodesAvantParamNomSimple_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteMethodesAvantNomParam = Optional.ofNullable(doc.getJsonArray("entiteMethodesAvantNomParam_stored_booleans")).orElse(new JsonArray()).stream().map(v -> (Boolean)v).collect(Collectors.toList());
      entiteMethodesAvantEcrire = Optional.ofNullable(doc.getJsonArray("entiteMethodesAvantEcrire_stored_booleans")).orElse(new JsonArray()).stream().map(v -> (Boolean)v).collect(Collectors.toList());
  
      entiteMethodesApresVisibilite = Optional.ofNullable(doc.getJsonArray("entiteMethodesApresVisibilite_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteMethodesApresVar = Optional.ofNullable(doc.getJsonArray("entiteMethodesApresVar_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteMethodesApresParamVar = Optional.ofNullable(doc.getJsonArray("entiteMethodesApresParamVar_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteMethodesApresParamNomSimple = Optional.ofNullable(doc.getJsonArray("entiteMethodesApresParamNomSimple_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteMethodesApresNomParam = Optional.ofNullable(doc.getJsonArray("entiteMethodesApresNomParam_stored_booleans")).orElse(new JsonArray()).stream().map(v -> (Boolean)v).collect(Collectors.toList());
      entiteMethodesApresEcrire = Optional.ofNullable(doc.getJsonArray("entiteMethodesApresEcrire_stored_booleans")).orElse(new JsonArray()).stream().map(v -> (Boolean)v).collect(Collectors.toList());

      entiteEcrireMethodes = Optional.ofNullable(doc.getJsonArray("entiteEcrireMethodes_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      if(entiteEcrireMethodes == null)
        entiteEcrireMethodes = new ArrayList<>();
      if(classeEcrireMethodes != null) {
        for(int i = 0; i < classeEcrireMethodes.size(); i++) {
          String classeEcrireMethode = classeEcrireMethodes.get(i);
          if(entiteNomSimpleCompletGenerique == null && entiteEcrireMethodes.contains(classeEcrireMethode)) {
            ToutEcrivain w = classeEcrireEcrivains.get(i);
            String var = classeEcrireMethode + entiteVarCapitalise;
            if(classeMethodeVars.contains(var)) {
              w.tl(2, "((", classeNomSimple, ")this).", var, "();");
            }
            else {
              w.tl(2, "if(", entiteVar, " != null)");
              w.tl(3, entiteVar, ".", classeEcrireMethode, "();");
            }
          }
        }
      }
  
      o = auteurGenClasseFin;
      l();
      String ligneCommentaire = "\t///" + String.join("", Collections.nCopies(entiteVar.length(), "/")) + "///";
      l(ligneCommentaire);
      tl(1, "// ", entiteVar, " //");
      l(ligneCommentaire);
      l();

      ToutEcrivain entiteValsEcrivain = ToutEcrivain.create();

      entiteValsVar = Optional.ofNullable(doc.getJsonArray("entiteValsVar_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteValsLangue = Optional.ofNullable(doc.getJsonArray("entiteValsLangue_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteValsCode = Optional.ofNullable(doc.getJsonArray("entiteValsCode_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      entiteValsValeur = Optional.ofNullable(doc.getJsonArray("entiteValsValeur_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
      if(entiteValsVar != null && entiteValsLangue != null && entiteValsValeur != null) {
        String entiteValVarAncien = null;
        Integer entiteValVarNumero = 0;
        String entiteValVar = null;
        String entiteValLangue = null;
        String entiteValVarLangue = null;
        String entiteValVarLangueAncien = null;
        String entiteValCode = null;
        String entiteValValeur = null;
  
        entiteXmlPile = new Stack<String>();
        entiteNumeroPile = new Stack<Integer>();
        for(String langueNom2 : toutesLangues) {
          for(int j = 0; j < entiteValsVar.size(); j++) {
            entiteValVar = entiteValsVar.get(j);
            entiteValLangue = entiteValsLangue.get(j);
            if(StringUtils.isBlank(entiteValLangue))
              entiteValLangue = langueNom2;
            entiteValVarLangue = entiteValVar + entiteValLangue;
//						entiteValCode = entiteValsCode == null ? "" : entiteValsCode.get(j);
            entiteValValeur = entiteValsValeur.get(j);
    
            Integer xmlPart = 0;
            if(!StringUtils.equals(entiteValVarLangue, entiteValVarLangueAncien) && (StringUtils.equals(entiteValVarLangueAncien, entiteValVarAncien + langueNom2))) {
              t(1, "public static final String ", entiteVar, entiteValVarAncien, "_", langueNom2, " = ");
              for(int k = 1; k <= entiteValVarNumero; k++) {
                if(k > 1)
                  s(" + ");
                s(entiteVar, entiteValVarAncien, k, "_", langueNom2);
              }
              l(";");
              entiteValVarNumero = 0;
            }
    
            if(StringUtils.equals(langueNom2, entiteValLangue)) {
              entiteValVarNumero++;
              tl(1, "public static final String ", entiteVar, entiteValVar, entiteValVarNumero, "_", entiteValLangue, " = \"", escapeJava(entiteValValeur), "\";");
              if(!classeVals.getEmpty())
                classeVals.s(", ");
              classeVals.s(entiteVar, entiteValVar, entiteValVarNumero, "_", entiteValLangue);
              {
                String[] parts = splitByCharacterTypeCamelCase(entiteValVar);
                Boolean html = false;
                for(Integer p = 0; p < parts.length; p++) {
                  String part = StringUtils.uncapitalize(parts[p]);
  
                  Matcher regex = Pattern.compile("^(\\w+?)(\\d*)$").matcher(part);
                  boolean trouve = regex.find();
                  if(trouve) {
                    String element = StringUtils.lowerCase(regex.group(1));
                    String numeroStr = regex.group(2);
                    Integer numero = StringUtils.isEmpty(numeroStr) ? null : Integer.parseInt(numeroStr);
                    if("h".equals(element)) {
                      element += numero;
                      numero = null;
                    }
  
  //									entiteValsEcrivain.t(1);
                    if(StringUtils.equalsAny(element, HTML_ELEMENTS)) {
                      html = true;
  
                      String css = entiteVar;
                      for(Integer r = 0; r <= xmlPart; r++) {
                        String s = parts[r];
                        css += s;
                      }
                      css += " ";
  
                      String cssNumero = numero == null ? "" : (StringUtils.substringBeforeLast(StringUtils.substringBeforeLast(css, numero.toString()), "0") + (numero % 2 == 0 ? " even " : " odd "));
  
                      if(numero == null)
                        numero = 1;
  
                      if(entiteXmlPile.size() < (xmlPart + 1)) {
                        if("i".equals(element))
                          entiteValsEcrivain.tl(2 + xmlPart, "<", element, " class=\"", entiteVar, entiteValVar, entiteValVarNumero, " site-menu-icon ", css, cssNumero, " \">");
                        else if("a".equals(element))
                          entiteValsEcrivain.tl(2 + xmlPart, "<", element, " class=\"", css, cssNumero, " \" href=\"", entiteVar, entiteValVar, entiteValVarNumero, "\">");
                        else if("br".equals(element))
                          entiteValsEcrivain.tl(2 + xmlPart, "<", element, "/>");
                        else if("td".equals(element))
                          entiteValsEcrivain.tl(2 + xmlPart, "<", element, " class=\"w3-mobile ", css, cssNumero, " \">");
                        else
                          entiteValsEcrivain.tl(2 + xmlPart, "<", element, " class=\"", css, cssNumero, " \">");
  
                        if(!"br".equals(element)) {
                          entiteXmlPile.push(element);
                          entiteNumeroPile.push(numero);
                          xmlPart++;
                        }
                      }
                      else if(StringUtils.equals(element, entiteXmlPile.get(xmlPart)) && numero.equals(entiteNumeroPile.get(xmlPart))) {
                        xmlPart++;
                      }
                      else {
                        while(entiteXmlPile.size() > xmlPart) {
                          entiteValsEcrivain.tl(1 + entiteXmlPile.size(), "</", entiteXmlPile.peek(), ">");
                          entiteXmlPile.pop();
                          entiteNumeroPile.pop();
                        }
                        if("i".equals(element))
                          entiteValsEcrivain.tl(2 + xmlPart, "<", element, " class=\"", entiteVar, entiteValVar, entiteValVarNumero, " site-menu-icon ", css, cssNumero, " \">");
                        else if("a".equals(element))
                          entiteValsEcrivain.tl(2 + xmlPart, "<", element, " class=\"", css, cssNumero, " \" href=\"", entiteVar, entiteValVar, entiteValVarNumero, "\">");
                        else if("br".equals(element))
                          entiteValsEcrivain.tl(2 + xmlPart, "<", element, "/>");
                        else if("td".equals(element))
                          entiteValsEcrivain.tl(2 + xmlPart, "<", element, " class=\"w3-mobile ", css, cssNumero, " \">");
                        else
                          entiteValsEcrivain.tl(2 + xmlPart, "<", element, " class=\"", css, cssNumero, " \">");
  
                        if(!"br".equals(element)) {
                          entiteXmlPile.push(element);
                          entiteNumeroPile.push(numero);
                          xmlPart++;
                        }
                      }
                    }
                  }
                }
                if(html && !"i".equals(entiteXmlPile.peek())) {
                  Integer p = entiteXmlPile.size();
                  if(StringUtils.isEmpty(entiteValCode)) {
                    entiteValsEcrivain.tl(2 + p, "{{ ", classeNomSimple, "['", entiteVar, entiteValVar, entiteValVarNumero, "_{{ lang }}'] }}");
                  }
                  else {
                    entiteValsEcrivain.tl(2 + p, "{{#if ", i18nGlobale.getString(I18n.var_utilisateurId), "}}", entiteVar, entiteValVar, entiteValVarNumero, "{{else}}", entiteValCode, "{{/if}}");
//										if(classeEntiteVars.contains(classeLangueConfig.getString(ConfigCles.var_utilisateurId)))
//											entiteValsEcrivain.tl(2 + p, "sx(utilisateurId == null ? ", entiteVar, entiteValVar, entiteValVarNumero, " : ", entiteValCode, ");");
//										else
//											entiteValsEcrivain.tl(2 + p, "sx(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getUtilisateurId() == null ? ", entiteVar, entiteValVar, entiteValVarNumero, " : ", entiteValCode, ");");
                  }
                }
              }
            }
    
            entiteValVarAncien = entiteValVar;
            entiteValVarLangueAncien = entiteValVarLangue;
          }
          entiteValVarAncien = null;
          entiteValVarLangueAncien = null;
        }
        if(StringUtils.equals(langueNom, entiteValLangue)) {
          entiteValVarAncien = entiteValVar;
          entiteValVarLangueAncien = entiteValVarLangue;
          entiteValVar = null;
    
          if(entiteValVarAncien != null && !StringUtils.equals(entiteValVar, entiteValVarLangueAncien)) {
            t(1, "public static final String ", entiteVar, entiteValVarAncien, "_", entiteValLangue, " = ");
            for(int k = 1; k <= entiteValVarNumero; k++) {
              if(k > 1)
                s(" + ");
              s(entiteVar, entiteValVarAncien, k, "_", entiteValLangue);
            }
            l(";");
            entiteValVarNumero = 0;
          }
        }
        l();

        for(int q = entiteXmlPile.size() - 1; q >= 0; q--) {
          entiteValsEcrivain.tl(2 + q, "</", entiteXmlPile.get(q), ">");
          entiteXmlPile.pop();
        }
      }

      if(ecrireCommentaire) {
        tl(1, "/**");
      // s(entiteValsEcrivain);
        tl(1, " * ", langueConfig.getString(I18n.str_L_entité_), entiteVar);
    
        if(entiteCommentaire != null) {
          String[] lignes = entiteCommentaire.toString().split("\n");
          for(int j = 0; j < lignes.length; j++) {
            String ligne = lignes[j];
            if(!StringUtils.isEmpty(ligne)) {
              Boolean premier = j == 0;
              Integer tabulations = StringUtils.countMatches(ligne, "\t");
              if(!premier)
                t(1 + tabulations, " *\t");
              l(ligne.substring(tabulations));
            }
          }
        }
    
        if(entiteCouverture) {
          tl(1, " *\t", " is defined as null before being initialized. ");
        }
        else {
          tl(1, " *\t", langueConfig.getString(I18n.str_Il_est_construit_avant_d_être_initialisé_avec_le_constructeur_par_défaut), ". ");
        }
        tl(1, " */");
      }

      if(entiteIgnorer) {
        tl(1, "@JsonIgnore");
      } else {
        if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
          tl(1, "@JsonProperty");
          if("Point".equals(entiteNomSimpleGenerique)) {
            tl(1, "@JsonDeserialize(contentUsing = ", classePartsPointDeserializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonSerialize(contentUsing = ", classePartsPointSerializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonFormat(shape = JsonFormat.Shape.ARRAY)");
          }
          else if("Path".equals(entiteNomSimpleGenerique)) {
            tl(1, "@JsonDeserialize(contentUsing = ", classePartsPathDeserializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonSerialize(contentUsing = ", classePartsPathSerializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonFormat(shape = JsonFormat.Shape.ARRAY)");
          }
          else if("Polygon".equals(entiteNomSimpleGenerique)) {
            tl(1, "@JsonDeserialize(using = ", classePartsPolygonDeserializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonSerialize(using = ", classePartsPolygonSerializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonFormat(shape = JsonFormat.Shape.ARRAY)");
          }
          else if("LocalDate".equals(entiteNomSimpleGenerique)) {
            tl(1, "@JsonDeserialize(contentUsing = ", classePartsLocalDateDeserializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonSerialize(contentUsing = ", classePartsLocalDateSerializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonFormat(shape = JsonFormat.Shape.ARRAY, pattern = \"yyyy-MM-dd\")");
          }
          else if("LocalTime".equals(entiteNomSimpleGenerique)) {
            tl(1, "@JsonDeserialize(contentUsing = ", classePartsLocalTimeDeserializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonSerialize(contentUsing = ", classePartsLocalTimeSerializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonFormat(shape = JsonFormat.Shape.ARRAY, pattern = \"HH:mm:ss.SSS\")");
          }
          else if("ZonedDateTime".equals(entiteNomSimpleGenerique)) {
            if(classePartsZonedDateTimeDeserializer == null)
              tl(1, "@JsonDeserialize(contentUsing = ZonedDateTimeDeserializer.class)");
            else
              tl(1, "@JsonDeserialize(contentUsing = ", classePartsZonedDateTimeDeserializer.nomSimple(langueNom), ".class)");

            if(classePartsZonedDateTimeSerializer == null)
              tl(1, "@JsonSerialize(contentUsing = ToStringSerializer.class)");
            else
              tl(1, "@JsonSerialize(contentUsing = ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".class)");

            tl(1, "@JsonFormat(shape=JsonFormat.Shape.ARRAY, pattern=\"yyyy-MM-dd'T'HH:mm:ss.SSSV'['VV']'\")");
          } else {
            tl(1, "@JsonFormat(shape = JsonFormat.Shape.ARRAY)");
          }
        } else {
          if("JsonArray".equals(entiteNomSimple)) {
            tl(1, "@JsonProperty");
            tl(1, "@JsonDeserialize(using = ", classePartsJsonArrayDeserializer.nomSimple(langueNom), ".class)");
          }
          else if("JsonObject".equals(entiteNomSimple)) {
            tl(1, "@JsonProperty");
            tl(1, "@JsonDeserialize(using = ", classePartsJsonObjectDeserializer.nomSimple(langueNom), ".class)");
          }
          else if("Point".equals(entiteNomSimple)) {
            tl(1, "@JsonProperty");
            tl(1, "@JsonDeserialize(using = ", classePartsPointDeserializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonSerialize(using = ", classePartsPointSerializer.nomSimple(langueNom), ".class)");
          }
          else if("Path".equals(entiteNomSimple)) {
            tl(1, "@JsonProperty");
            tl(1, "@JsonDeserialize(using = ", classePartsPathDeserializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonSerialize(using = ", classePartsPathSerializer.nomSimple(langueNom), ".class)");
          }
          else if("Polygon".equals(entiteNomSimple)) {
            tl(1, "@JsonProperty");
            tl(1, "@JsonDeserialize(using = ", classePartsPolygonDeserializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonSerialize(using = ", classePartsPolygonSerializer.nomSimple(langueNom), ".class)");
          }
          else if("LocalDate".equals(entiteNomSimple)) {
            tl(1, "@JsonProperty");
            tl(1, "@JsonDeserialize(using = ", classePartsLocalDateDeserializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonSerialize(using = ", classePartsLocalDateSerializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd\")");
          }
          else if("LocalTime".equals(entiteNomSimple)) {
            tl(1, "@JsonProperty");
            tl(1, "@JsonDeserialize(using = ", classePartsLocalTimeDeserializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonSerialize(using = ", classePartsLocalTimeSerializer.nomSimple(langueNom), ".class)");
            tl(1, "@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"HH:mm:ss.SSS\")");
          }
          else if("ZonedDateTime".equals(entiteNomSimple)) {
            tl(1, "@JsonProperty");

            if(classePartsZonedDateTimeDeserializer == null)
              tl(1, "@JsonDeserialize(using = ZonedDateTimeDeserializer.class)");
            else
              tl(1, "@JsonDeserialize(using = ", classePartsZonedDateTimeDeserializer.nomSimple(langueNom), ".class)");

            if(classePartsZonedDateTimeSerializer == null)
              tl(1, "@JsonSerialize(using = ToStringSerializer.class)");
            else
              tl(1, "@JsonSerialize(using = ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".class)");

            tl(1, "@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=\"yyyy-MM-dd'T'HH:mm:ss.SSSV'['VV']'\")");
          }
          else if(!"java.lang.String".equals(entiteNomCanonique) && "string".equals(entiteTypeJson)) {
            tl(1, "@JsonProperty");
            tl(1, "@JsonSerialize(using = ToStringSerializer.class)");
          } else if("Long".equals(entiteNomSimpleGenerique) 
              || "Double".equals(entiteNomSimpleGenerique)
              || "Integer".equals(entiteNomSimpleGenerique)
              ) {
            tl(1, "@JsonProperty");
            tl(1, "@JsonFormat(shape = JsonFormat.Shape.ARRAY)");
            tl(1, "@JsonSerialize(contentUsing = ToStringSerializer.class)");
          } else {
            tl(1, "@JsonProperty");
          }
        }
      }

      tl(1, "@JsonInclude(Include.NON_NULL)");
      t(1, "protected ", entiteNomSimpleComplet, " ", entiteVar);
      if(!entiteCouverture) {
        if("java.util.List".equals(entiteNomCanonique)) {
          s(" = new ArrayList<");
          s(entiteNomSimpleGenerique);
          s(">()");
        }
        else if("java.util.Map".equals(entiteNomCanonique)) {
          s(" = new HashMap<");
          s(entiteNomSimpleGenerique);
          s(">()");
        }
        else if("java.util.Set".equals(entiteNomCanonique)) {
          s(" = new HashSet<");
          s(entiteNomSimpleGenerique);
          s(">()");
        }
        else {
          s(" = new ", entiteNomSimpleComplet, "()");
        }
      }
      l(";");
  
      // Methode underscore //
      l();
      if(ecrireCommentaire) {
        tl(1, "/**");
        tl(1, " * <br>", langueConfig.getString(I18n.str_L_entité_), entiteVar);
    
        if(entiteCommentaire != null) {
          String[] lignes = entiteCommentaire.toString().split("\n");
          for(int j = 0; j < lignes.length; j++) {
            String ligne = lignes[j];
            if(!StringUtils.isEmpty(ligne)) {
              Boolean premier = j == 0;
              Integer tabulations = StringUtils.countMatches(ligne, "\t");
              if(!premier)
                t(1 + tabulations, " *\t");
              l(ligne.substring(tabulations));
            }
          }
        }
    
        if(entiteCouverture) {
          tl(1, " * ", langueConfig.getString(I18n.str__est_défini_comme_null_avant_d_être_initialisé__));
        }
        else {
          tl(1, " * ", langueConfig.getString(I18n.str_Il_est_construit_avant_d_être_initialisé_avec_le_constructeur_par_défaut), ". ");
        }
    
        // Lien vers Solr //
        tl(1, " * <br><a href=\"https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(classeNomCanonique), "&fq=entiteVar_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(entiteVar), "\">", langueConfig.getString(I18n.str_Trouver_l_entité_), entiteVar, langueConfig.getString(I18n.str__dans_Solr), "</a>");
        tl(1, " * <br>");
    
        if(entiteCouverture) {
          tl(1, " * @param ", entiteVarParam, langueConfig.getString(I18n.str__est_pour_envelopper_une_valeur_à_assigner_à_cette_entité_lors_de_l_initialisation__));
        }
        else {
          tl(1, " * @param ", entiteVarParam, langueConfig.getString(I18n.str__est_l_entité_déjà_construit__));
        }
    //		if(methodeExceptionsNomSimpleComplet != null && methodeExceptionsNomSimpleComplet.size() > 0) {
    //
    //			for(int i = 0; i < methodeExceptionsNomSimpleComplet.size(); i++) {
    //				String methodeExceptionNomSimpleComplet = methodeExceptionsNomSimpleComplet.get(i);
    //				tl(1, " * @throws ", methodeExceptionNomSimpleComplet);
    //			}
    //		}
        tl(1, " **/");
      }
      t(1, "protected abstract void _", entiteVar, "(");

      if(entitePromesse) {
        s("Promise<", entiteNomSimpleComplet, "> ", entiteVarParam);
      }
      else if(entiteCouverture) {
        s(classePartsCouverture.nomSimple(langueNom), "<", entiteNomSimpleComplet, "> ", entiteVarParam);
      }
      else {
        s(entiteNomSimpleComplet, " ", entiteVarParam);
      }
      s(")");
      if(methodeExceptionsNomSimpleComplet != null && methodeExceptionsNomSimpleComplet.size() > 0) {
  
        s(" throws ");
        for(int i = 0; i < methodeExceptionsNomSimpleComplet.size(); i++) {
          String methodeExceptionNomSimpleComplet = methodeExceptionsNomSimpleComplet.get(i);
          if(i > 0) 
            s(", ");
          s(methodeExceptionNomSimpleComplet);
        }
      }
      l(";");
  
  //						l();
  //						tl(1, "public ", classeNomSimple, " ", entiteVar, "(", entiteNomSimpleComplet, " ", entiteVarParam, ") {");
  //						tl(2, "set", entiteVarCapitalise, "(", entiteVarParam, ");");
  //						tl(2, "return (", classeNomSimple, ")this;");
  //						tl(1, "}");
  
      l();
      tl(1, "public ", entiteNomSimpleComplet, " get", entiteVarCapitalise, "() {");
      tl(2, "return ", entiteVar, ";");
      tl(1, "}");
      if(StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon)) {
        tl(1, "public JsonObject geojson", entiteVarCapitalise, "() {");
        tl(2, "if(", entiteVar, " == null)");
        tl(3, "return null;");
        tl(2, "JsonArray coordinates = new JsonArray();");
        tl(2, "JsonObject json = new JsonObject().put(\"type\", \"Polygon\").put(\"coordinates\", coordinates);");
        tl(2, "for(", entiteNomSimpleCompletGenerique, " o : ", entiteVar, ") {");
        tl(3, "JsonArray coordinates2 = new JsonArray();");
        tl(3, "coordinates.add(coordinates2);");
        tl(3, "o.getPoints().forEach(point -> {");
        tl(4, "coordinates2.add(new JsonArray().add(point.getX()).add(point.getY()));");
        tl(3, "});");
        tl(2, "}");
        tl(2, "if(coordinates.size() == 0)");
        tl(3, "return null;");
        tl(2, "else");
        tl(3, "return json;");
        tl(1, "}");
      }
      Boolean staticSet = false;
  
      if(classePartsRequeteSite != null) {
        if(StringUtils.equals(entiteNomCanonique, String.class.getCanonicalName())
            || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, String.class.getCanonicalName())) {

          if(entiteEstListe) {
            l();
            tl(1, "public void set", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVar, ") {");
            tl(2, "this.", entiteVar, " = ", entiteVar, ";");
            tl(1, "}");
          }

          if(entiteEstListe)
            tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(String o) {");
          tl(2, entiteEstListe ? "String l = " : "this."+ entiteVar + " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          if(entiteEstListe) {
            tl(2, "if(l != null)");
            tl(3, "add", entiteVarCapitalise, "(l);");
          }
          tl(1, "}");
          tl(1, "public static ", entiteEstListe ? entiteNomSimpleCompletGenerique : entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
  
          if(entiteSetTrim && entiteSetLower)
            tl(2, "return StringUtils.trim(StringUtils.lowerCase(o));");
          else if(entiteSetTrim && entiteSetUpper)
            tl(2, "return StringUtils.trim(StringUtils.upperCase(o));");
          else if(entiteSetTrim)
            tl(2, "return StringUtils.trim(o);");
          else if(entiteSetLower)
            tl(2, "return StringUtils.lowerCase(o);");
          else if(entiteSetUpper)
            tl(2, "return StringUtils.upperCase(o);");
          else
            tl(2, "return o;");
  
          tl(1, "}");
          staticSet = true;
        }
        else {
          l();
          tl(1, "public void set", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVar, ") {");
          if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
            tl(2, "this.", entiteVar, " = Optional.ofNullable(", entiteVar, ").map(v -> v.truncatedTo(ChronoUnit.MILLIS)).orElse(null);");
          } else {
            tl(2, "this.", entiteVar, " = ", entiteVar, ";");
          }
          tl(1, "}");
        }
        
        // Setter Boolean //
        if(StringUtils.equals(entiteNomCanonique, Boolean.class.getCanonicalName())
            || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, Boolean.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(String o) {");
          tl(2, entiteEstListe ? "Boolean l = " : "this."+ entiteVar + " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          if(entiteEstListe) {
            tl(2, "if(l != null)");
            tl(3, "add", entiteVarCapitalise, "(l);");
          }
          tl(1, "}");
          tl(1, "public static ", entiteEstListe ? entiteNomSimpleCompletGenerique : entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "return Boolean.parseBoolean(o);");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter Integer //
        if(StringUtils.equals(entiteNomCanonique, Integer.class.getCanonicalName())
            || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, Integer.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(String o) {");
          tl(2, entiteEstListe ? "Integer l = " : "this."+ entiteVar + " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          if(entiteEstListe) {
            tl(2, "if(l != null)");
            tl(3, "add", entiteVarCapitalise, "(l);");
          }
          tl(1, "}");
          tl(1, "public static ", entiteEstListe ? entiteNomSimpleCompletGenerique : entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "if(NumberUtils.isParsable(o))");
          tl(3, "return Integer.parseInt(o);");
          tl(2, "return null;");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter Float //
        if(StringUtils.equals(entiteNomCanonique, Float.class.getCanonicalName())
            || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, Float.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(String o) {");
          tl(2, entiteEstListe ? "Float l = " : "this."+ entiteVar + " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          if(entiteEstListe) {
            tl(2, "if(l != null)");
            tl(3, "add", entiteVarCapitalise, "(l);");
          }
          tl(1, "}");
          tl(1, "public static ", entiteEstListe ? entiteNomSimpleCompletGenerique : entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "if(NumberUtils.isParsable(o))");
          tl(3, "return Float.parseFloat(o);");
          tl(2, "return null;");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter Double //
        if(StringUtils.equals(entiteNomCanonique, Double.class.getCanonicalName())
            || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, Double.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(String o) {");
          tl(2, entiteEstListe ? "Double l = " : "this."+ entiteVar + " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          if(entiteEstListe) {
            tl(2, "if(l != null)");
            tl(3, "add", entiteVarCapitalise, "(l);");
          }
          tl(1, "}");
          tl(1, "public static ", entiteEstListe ? entiteNomSimpleCompletGenerique : entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "if(NumberUtils.isParsable(o))");
          tl(3, "return Double.parseDouble(o);");
          tl(2, "return null;");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter BigDecimal //
        if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())
            || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, BigDecimal.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(String o) {");
          tl(2, entiteEstListe ? "BigDecimal l = " : "this."+ entiteVar + " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          if(entiteEstListe) {
            tl(2, "if(l != null)");
            tl(3, "add", entiteVarCapitalise, "(l);");
          }
          tl(1, "}");
          tl(1, "public static MathContext staticMathContext", entiteVarCapitalise, "() {");
          tl(2, "return new MathContext(", entitePrecision, ", RoundingMode.valueOf(\"", entiteModeDArrondi, "\"));");
          tl(1, "}");
          tl(1, "public static ", entiteEstListe ? entiteNomSimpleCompletGenerique : entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "o = StringUtils.removeAll(o, \"[^\\\\d\\\\.-]\");");
          tl(2, "if(NumberUtils.isParsable(o))");
          tl(3, "return new BigDecimal(o, staticMathContext", entiteVarCapitalise, "());");
          tl(2, "return null;");
          tl(1, "}");
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(Double o) {");
          tl(2, entiteEstListe ? "add" : "set", entiteVarCapitalise, "(new BigDecimal(o, staticMathContext", entiteVarCapitalise, "()));");
          tl(1, "}");
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(Integer o) {");
          tl(2, entiteEstListe ? "add" : "set", entiteVarCapitalise, "(new BigDecimal(o, staticMathContext", entiteVarCapitalise, "()));");
          tl(1, "}");
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(Number o) {");
          tl(2, entiteEstListe ? "add" : "set", entiteVarCapitalise, "(new BigDecimal(o.doubleValue(), staticMathContext", entiteVarCapitalise, "()));");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter Long //
        if(StringUtils.equals(entiteNomCanonique, Long.class.getCanonicalName())
            || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, Long.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(String o) {");
          tl(2, entiteEstListe ? "Long l = " : "this."+ entiteVar + " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          if(entiteEstListe) {
            tl(2, "if(l != null)");
            tl(3, "add", entiteVarCapitalise, "(l);");
          }
          tl(1, "}");
          tl(1, "public static ", entiteEstListe ? entiteNomSimpleCompletGenerique : entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "if(NumberUtils.isParsable(o))");
          tl(3, "return Long.parseLong(o);");
          tl(2, "return null;");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter Path //
        if(
            StringUtils.equals(entiteNomCanonique, VAL_nomCanoniquePoint) || StringUtils.equals(entiteNomCanonique, VAL_nomCanoniquePath) || StringUtils.equals(entiteNomCanonique, VAL_nomCanoniquePolygon)
            || StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePoint) || StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePath) || StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon)
            ) {
          tl(1, "@JsonIgnore");

          if(entiteNomCanoniqueGenerique == null) {
            tl(1, "public void set", entiteVarCapitalise, "(String o) {");
            tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
            tl(1, "}");
          }
          tl(1, "public static ", entiteNomCanoniqueGenerique == null ? entiteNomSimple : (StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon) ? entiteNomSimpleComplet : entiteNomSimpleGenerique), " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "if(o != null) {");
          tl(3, "try {");
          tl(4, entiteNomCanoniqueGenerique == null ? entiteNomSimple : (StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon) ? entiteNomSimpleComplet : entiteNomSimpleGenerique), " shape = null;");
          tl(4, "if(StringUtils.isNotBlank(o)) {");
          tl(5, "ObjectMapper objectMapper = new ObjectMapper();");
          tl(5, "SimpleModule module = new SimpleModule();");
          tl(5, "module.setDeserializerModifier(new BeanDeserializerModifier() {");
          tl(6, "@Override");
          tl(6, "public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {");
          tl(7, "if (beanDesc.getBeanClass() == ", entiteNomCanoniqueGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique, ".class) {");
          tl(8, "return new PgClient", entiteNomCanoniqueGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique, "Deserializer();");
          tl(7, "}");
          tl(7, "return deserializer;");
          tl(6, "}");
          tl(5, "});");
          tl(5, "objectMapper.registerModule(module);");
          tl(5, "shape = ", (StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon) ? "(" + entiteNomSimpleComplet + ")" : ""), "objectMapper.readValue(Json.encode(o), ", entiteNomCanoniqueGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique, ".class);");
          tl(4, "}");
          tl(4, "return shape;");
          tl(3, "} catch(Exception ex) {");
          tl(4, "LOG.error(String.format(\"Could not parse GeoJSON. %s: %s\", ex.getMessage(), o));");
          tl(3, "}");
          tl(2, "}");
          tl(2, "return null;");
          tl(1, "}");

          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonObject o) {");
          tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          tl(1, "}");
          tl(1, "public static ", entiteNomCanoniqueGenerique == null ? entiteNomSimple : StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon) ? entiteNomSimpleComplet : entiteNomSimpleGenerique, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, JsonObject o) {");
          tl(2, "if(o != null) {");
          tl(3, "try {");
          if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniquePoint) || StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePoint)) {
            tl(4, entiteNomCanoniqueGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique, " shape = new ", entiteNomCanoniqueGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique, "();");
            tl(4, "JsonArray coordinates = o.getJsonArray(\"coordinates\");");
            tl(4, "shape.setX(coordinates.getDouble(0));");
            tl(4, "shape.setY(coordinates.getDouble(1));");
          } else if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniquePath) || StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePath)) {
            tl(4, entiteNomCanoniqueGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique, " shape = new ", entiteNomCanoniqueGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique, "();");
            tl(4, "o.getJsonArray(\"coordinates\").stream().map(a -> (JsonArray)a).forEach(points -> {");
            tl(5, "shape.addPoint(new Point(Double.parseDouble(points.getString(0)), Double.parseDouble(points.getString(1))));");
            tl(4, "});");
          } else if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniquePolygon) || StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon)) {
            tl(4, entiteNomSimpleComplet, " shapes = new ArrayList<>();");
            tl(4, "o.getJsonArray(\"coordinates\").stream().map(a -> (JsonArray)a).forEach(g -> {");
            tl(5, entiteNomCanoniqueGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique, " shape = new ", entiteNomCanoniqueGenerique == null ? entiteNomSimple : entiteNomSimpleGenerique, "();");
            tl(5, "g.stream().map(a -> (JsonArray)a).forEach(points -> {");
            tl(6, "shape.addPoint(new Point(Double.parseDouble(points.getString(0)), Double.parseDouble(points.getString(1))));");
            tl(5, "});");
            tl(6, "shapes.add(shape);");
            tl(4, "});");
          }
          tl(4, "return shape", StringUtils.equals(entiteNomCanoniqueGenerique, VAL_nomCanoniquePolygon) ? "s" : "", ";");
          tl(3, "} catch(Exception ex) {");
          tl(4, "LOG.error(String.format(\"Could not parse GeoJSON. %s: %s\", ex.getMessage(), o));");
          tl(3, "}");
          tl(2, "}");
          tl(2, "return null;");
          tl(1, "}");
          staticSet = true;
        }
    
        if(classePartsRequeteSite != null) {
          // Setter JsonObject //
          if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
            tl(1, "@JsonIgnore");
            tl(1, "public void set", entiteVarCapitalise, "(String o) {");
            tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
            tl(1, "}");
            tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
            tl(2, "if(o != null) {");
            tl(4, "return new JsonObject(o);");
            tl(2, "}");
            tl(2, "return null;");
            tl(1, "}");
            staticSet = true;
          }
        }
        else {
          LOG.error(String.format("%s %s %s %s %s. ", langueConfig.getString(I18n.var_classe), langueConfig.getString(I18n.var_RequeteSite), langueConfig.getString(I18n.var_manquante), langueConfig.getString(I18n.var_dans), cheminSrcMainJava));
        }
    
        // Setter JsonArray //
        if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueVertxJsonArray)) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(String o) {");
          tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          tl(1, "}");
          tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "if(o != null) {");
          tl(4, "return new JsonArray(o);");
          tl(2, "}");
          tl(2, "return null;");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter Timestamp //
        if(StringUtils.equals(entiteNomCanonique, Timestamp.class.getCanonicalName())) {
          if(ecrireCommentaire) {
            tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
          }
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(String o) {");
          tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          tl(1, "}");
          tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "return Timestamp.valueOf((LocalDateTime.parse(o, ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER)));");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter Date //
        if(StringUtils.equals(entiteNomCanonique, Date.class.getCanonicalName())) {
          if(ecrireCommentaire) {
            tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
          }
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(String o) {");
          tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          tl(1, "}");
          tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "return Date.from(LocalDateTime.parse(o, ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER).atZone(ZoneId.of(\"Z\")).toInstant());");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter LocalTime //
        if(StringUtils.equals(entiteNomCanonique, LocalTime.class.getCanonicalName())) {
          if(ecrireCommentaire) {
            tl(1, "/** Example: 01:00 **/");
          }
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(String o) {");
          tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
          tl(1, "}");
          tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "try {");
          tl(3, "return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);");
          tl(2, "} catch(Exception e) {");
          tl(2, "}");
          tl(2, "return null;");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter LocalDate //
        if(StringUtils.equals(entiteNomCanonique, LocalDate.class.getCanonicalName())
            || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, LocalDate.class.getCanonicalName())) {
          if(!entiteEstListe) {
            tl(1, "@JsonIgnore");
            tl(1, "public void set", entiteVarCapitalise, "(Instant o) {");
            tl(2, "this.", entiteVar, " = o == null ? null : LocalDate.from(o);");
            tl(1, "}");
            if(ecrireCommentaire) {
              tl(1, "/** Example: 2011-12-03+01:00 **/");
            }
            tl(1, "@JsonIgnore");
            tl(1, "public void set", entiteVarCapitalise, "(String o) {");
            tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o);");
            tl(1, "}");
            if(classeContientRequeteSite) {
              tl(1, "@JsonIgnore");
              tl(1, "public void set", entiteVarCapitalise, "(Date o) {");
              tl(2, "this.", entiteVar, " = o == null ? null : o.toInstant().atZone(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).toLocalDate();");
              tl(1, "}");
            }
          }
          tl(1, "public static ", entiteNomSimpleCompletGenerique == null ? entiteNomSimpleComplet : entiteNomSimpleCompletGenerique, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "if(o != null) {");
          tl(3, "if(o.contains(\"T\")) {");
          tl(4, "return java.time.LocalDateTime.parse(o, ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER).atZone(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).toLocalDate();");
          tl(3, "} else {");
          tl(4, "return LocalDate.parse(o, DateTimeFormatter.ISO_DATE);");
          tl(3, "}");
          tl(2, "}");
          tl(2, "return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter ZonedDateTime //
        if(entiteEstZonedDateTime) {
          if(!entiteEstListe) {
            tl(1, "@JsonIgnore");
            tl(1, "public void set", entiteVarCapitalise, "(Instant o) {");
            tl(2, "this.", entiteVar, " = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);");
            tl(1, "}");
            if(ecrireCommentaire) {
              tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
            }
            tl(1, "@JsonIgnore");
            tl(1, "public void set", entiteVarCapitalise, "(String o) {");
            if(classeVarZone == null)
              tl(2, "ZoneId zoneId = Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_).map(r -> r.get", langueConfig.getString(I18n.var_Config), "()).map(config -> config.getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), ")).map(z -> ZoneId.of(z)).orElse(ZoneId.of(\"UTC\"));");
            else
              tl(2, "ZoneId zoneId = Optional.ofNullable(", classeVarZone, ").map(v -> ZoneId.of(v)).orElse(Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_).map(r -> r.get", langueConfig.getString(I18n.var_Config), "()).map(config -> config.getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), ")).map(z -> ZoneId.of(z)).orElse(ZoneId.of(\"UTC\")));");
            tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o, zoneId);");
            tl(1, "}");
            tl(1, "@JsonIgnore");
            tl(1, "public void set", entiteVarCapitalise, "(Date o) {");
            tl(2, "this.", entiteVar, " = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).truncatedTo(ChronoUnit.MILLIS);");
            tl(1, "}");
          }
          tl(1, "public static ", entiteNomSimpleCompletGenerique == null ? entiteNomSimpleComplet : entiteNomSimpleCompletGenerique, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o, ZoneId zoneId) {");
          tl(2, "if(StringUtils.endsWith(o, \"]\"))");
          tl(3, "return o == null ? null : ZonedDateTime.parse(o, ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".ZONED_DATE_TIME_FORMATTER);");
          tl(2, "else if(StringUtils.endsWith(o, \"Z\"))");
          tl(3, "return o == null ? null : Instant.parse(o).atZone(zoneId).truncatedTo(ChronoUnit.MILLIS);");
          tl(2, "else if(StringUtils.contains(o, \"T\"))");
          tl(3, "return o == null ? null : ZonedDateTime.parse(o, ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER).truncatedTo(ChronoUnit.MILLIS);");
          tl(2, "else");
          tl(3, "return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE).atStartOfDay(zoneId).truncatedTo(ChronoUnit.MILLIS);");
          tl(1, "}");
          staticSet = true;
        }
    
        // Setter LocalDateTime //
        if(StringUtils.equals(entiteNomCanonique, LocalDateTime.class.getCanonicalName())
            || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, LocalDateTime.class.getCanonicalName())) {
          if(!entiteEstListe) {
            tl(1, "@JsonIgnore");
            tl(1, "public void set", entiteVarCapitalise, "(Instant o) {");
            tl(2, "this.", entiteVar, " = o == null ? null : LocalDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);");
            tl(1, "}");
            if(ecrireCommentaire) {
              tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
            }
            tl(1, "@JsonIgnore");
            tl(1, "public void set", entiteNomSimpleCompletGenerique, "(String o) {");
            tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o);");
            tl(1, "}");
            tl(1, "@JsonIgnore");
            tl(1, "public void set", entiteVarCapitalise, "(Date o) {");
            tl(2, "this.", entiteVar, " = o == null ? null : LocalDateTime.ofInstant(o.toInstant(), ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).truncatedTo(ChronoUnit.MILLIS);");
            tl(1, "}");
          }
          tl(1, "public static ", entiteNomSimpleCompletGenerique == null ? entiteNomSimpleComplet : entiteNomSimpleCompletGenerique, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          tl(2, "return o == null ? null : LocalDateTime.parse(o, ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER).truncatedTo(ChronoUnit.MILLIS);");
          tl(1, "}");
          staticSet = true;
        }
  
        if(!staticSet && !entiteNomSimpleComplet.contains("<DEV>")) {
          if((StringUtils.equalsAny(entiteNomSimple, "List", "ArrayList", "Stack"))) {
            tl(1, "public static ", entiteNomSimpleGenerique, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
            if("String".equals(entiteNomSimpleGenerique)) {
              tl(2, "return o;");
            } else if(StringUtils.equals(entiteNomCanonique, Long.class.getCanonicalName())) {
              tl(2, "if(NumberUtils.isParsable(o))");
              tl(3, "return Long.parseLong(o);");
              tl(2, "return null;");
              staticSet = true;
            } else {
              tl(2, "return null;");
            }
            tl(1, "}");
          }
          else {
            if(classePartsRequeteSite != null) {
              tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
              if("String".equals(entiteNomSimpleGenerique)) {
                tl(2, "return o;");
              } else if(StringUtils.equals(entiteNomCanonique, Long.class.getCanonicalName())) {
                tl(2, "if(NumberUtils.isParsable(o))");
                tl(3, "return Long.parseLong(o);");
                tl(2, "return null;");
                staticSet = true;
              } else {
                tl(2, "return null;");
              }
              tl(1, "}");
            }
            else {
              LOG.error(String.format("%s %s %s %s %s. ", langueConfig.getString(I18n.var_classe), langueConfig.getString(I18n.var_RequeteSite), langueConfig.getString(I18n.var_manquante), langueConfig.getString(I18n.var_dans), cheminSrcMainJava));
            }
          }
        }
      }
      else {
        LOG.error(String.format("%s %s %s %s %s. ", langueConfig.getString(I18n.var_classe), langueConfig.getString(I18n.var_RequeteSite), langueConfig.getString(I18n.var_manquante), langueConfig.getString(I18n.var_dans), cheminSrcMainJava));
      }
  
      // Ajouter //
      if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, Set.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, HashSet.class.getCanonicalName())) {
        tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(", entiteNomSimpleCompletGenerique, "...objects) {");
        tl(2, "for(", entiteNomSimpleCompletGenerique, " o : objects) {");
        tl(3, "add", entiteVarCapitalise, "(o);");
        tl(2, "}");
        tl(2, "return (", classeNomSimple, ")this;");
        tl(1, "}");
        tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(", entiteNomSimpleCompletGenerique, " o) {");
        tl(2, "if(o != null)");
        tl(3, "this.", entiteVar, ".add(o);");
        tl(2, "return (", classeNomSimple, ")this;");
        tl(1, "}");
    
        // Setter String //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, String.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, entiteNomSimpleCompletGenerique, " o = objects.get", entiteNomSimpleCompletGenerique, "(i);");
          tl(3, "add", entiteVarCapitalise, "(o);");
          tl(2, "}");
          tl(1, "}");
        }
    
        // Setter Boolean //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, Boolean.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, entiteNomSimpleCompletGenerique, " o = objects.get", entiteNomSimpleCompletGenerique, "(i);");
          tl(3, "add", entiteVarCapitalise, "(o);");
          tl(2, "}");
          tl(1, "}");
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
          tl(2, entiteNomSimpleCompletGenerique, " p = Boolean.parseBoolean(o);");
          tl(2, "add", entiteVarCapitalise, "(p);");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
        }
    
        // Setter Integer //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, Integer.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, entiteListeNomSimpleVertxJson, " o = objects.get", entiteListeNomSimpleVertxJson, "(i);");
          tl(3, "set", entiteVarCapitalise, "(o);");
          tl(2, "}");
          tl(1, "}");
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
          tl(2, "if(NumberUtils.isParsable(o)) {");
          tl(3, entiteNomSimpleCompletGenerique, " p = Integer.parseInt(o);");
          tl(3, "add", entiteVarCapitalise, "(p);");
          tl(3, "}");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
        }
    
        // Setter BigDecimal //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, BigDecimal.class.getCanonicalName())) {
          tl(1, "public static MathContext staticMathContext", entiteVarCapitalise, "() {");
          tl(2, "return new MathContext(", entitePrecision, ", RoundingMode.valueOf(\"", entiteModeDArrondi, "\"));");
          tl(1, "}");
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, entiteListeNomSimpleVertxJson, " o = objects.get", entiteListeNomSimpleVertxJson, "(i);");
          tl(3, "add", entiteVarCapitalise, "(new BigDecimal(o, staticMathContext", entiteVarCapitalise, "()));");
          tl(2, "}");
          tl(1, "}");
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
          tl(2, "if(NumberUtils.isParsable(o)) {");
          tl(3, entiteNomSimpleCompletGenerique, " p = new BigDecimal(o, staticMathContext", entiteVarCapitalise, "());");
          tl(3, "add", entiteVarCapitalise, "(p);");
          tl(2, "}");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
        }
    
        // Setter Float //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, Float.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, entiteNomSimpleCompletGenerique, " o = objects.get", entiteNomSimpleCompletGenerique, "(i);");
          tl(3, "add", entiteVarCapitalise, "(o);");
          tl(2, "}");
          tl(1, "}");
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
          tl(2, "if(NumberUtils.isParsable(o)) {");
          tl(3, entiteNomSimpleCompletGenerique, " p = Float.parseFloat(o);");
          tl(3, "add", entiteVarCapitalise, "(p);");
          tl(2, "}");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
        }
    
        // Setter Double //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, Double.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, entiteListeNomSimpleVertxJson, " o = objects.get", entiteListeNomSimpleVertxJson, "(i);");
          tl(3, "set", entiteVarCapitalise, "(o);");
          tl(2, "}");
          tl(1, "}");
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
          tl(2, "if(NumberUtils.isParsable(o)) {");
          tl(3, entiteNomSimpleCompletGenerique, " p = Double.parseDouble(o);");
          tl(3, "add", entiteVarCapitalise, "(p);");
          tl(2, "}");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
        }
    
        // Setter Long //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, Long.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, entiteListeNomSimpleVertxJson, " o = objects.get", entiteListeNomSimpleVertxJson, "(i);");
          tl(3, "set", entiteVarCapitalise, "(o);");
          tl(2, "}");
          tl(1, "}");
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
          tl(2, "if(NumberUtils.isParsable(o)) {");
          tl(3, entiteNomSimpleCompletGenerique, " p = Long.parseLong(o);");
          tl(3, "add", entiteVarCapitalise, "(p);");
          tl(2, "}");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
        }
    
        // Setter Timestamp //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, Timestamp.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, "Instant o = objects.getInstant(i);");
          tl(3, "add", entiteVarCapitalise, "(o);");
          tl(2, "}");
          tl(1, "}");
          if(ecrireCommentaire) {
            tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
          }
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
          tl(2, entiteNomSimpleCompletGenerique, " p = Timestamp.valueOf((LocalDateTime.parse(o, ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER)));");
          tl(2, "add", entiteVarCapitalise, "(p);");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
        }
    
        // Setter Date //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, Date.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, "Instant o = objects.getInstant(i);");
          tl(3, "add", entiteVarCapitalise, "(o);");
          tl(2, "}");
          tl(1, "}");
          if(ecrireCommentaire) {
            tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
          }
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
          tl(2, entiteNomSimpleCompletGenerique, " p = Date.from(LocalDateTime.parse(o, ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER).atZone(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).toInstant());");
          tl(2, "add", entiteVarCapitalise, "(p);");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
        }
    
        // Setter LocalDate //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, LocalDate.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, "Instant o = objects.getInstant(i);");
          tl(3, "add", entiteVarCapitalise, "(o);");
          tl(2, "}");
          tl(1, "}");
          if(ecrireCommentaire) {
            tl(1, "/** Example: 2011-12-03+01:00 **/");
          }
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
          tl(2, entiteNomSimpleCompletGenerique, " p = LocalDate.parse(o, DateTimeFormatter.ISO_DATE);");
          tl(2, "add", entiteVarCapitalise, "(p);");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(Date o) {");
          tl(2, entiteNomSimpleCompletGenerique, " p = o.toInstant().atZone(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).toLocalDate();");
          tl(2, "add", entiteVarCapitalise, "(p);");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
        }
    
        // Setter ZonedDateTime //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, ZonedDateTime.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, "Instant o = objects.getInstant(i);");
          tl(3, "add", entiteVarCapitalise, "(o);");
          tl(2, "}");
          tl(1, "}");
          if(ecrireCommentaire) {
            tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
          }
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
          if(classeVarZone == null)
            tl(2, "ZoneId zoneId = Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_).map(r -> r.get", langueConfig.getString(I18n.var_Config), "()).map(config -> config.getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), ")).map(z -> ZoneId.of(z)).orElse(ZoneId.of(\"UTC\"));");
          else
            tl(2, "ZoneId zoneId = Optional.ofNullable(", classeVarZone, ").map(v -> ZoneId.of(v)).orElse(Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_).map(r -> r.get", langueConfig.getString(I18n.var_Config), "()).map(config -> config.getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), ")).map(z -> ZoneId.of(z)).orElse(ZoneId.of(\"UTC\")));");
          tl(2, entiteNomSimpleCompletGenerique, " p = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ", o, zoneId);");
          tl(2, "add", entiteVarCapitalise, "(p);");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(Instant o) {");
          tl(2, entiteNomSimpleCompletGenerique, " p = ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);");
          tl(2, "add", entiteVarCapitalise, "(p);");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(Date o) {");
          tl(2, entiteNomSimpleCompletGenerique, " p = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), ")));");
          tl(2, "add", entiteVarCapitalise, "(p);");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
        }
    
        // Setter LocalDateTime //
        if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, LocalDateTime.class.getCanonicalName())) {
          tl(1, "@JsonIgnore");
          tl(1, "public void set", entiteVarCapitalise, "(JsonArray objects) {");
          tl(2, entiteVar, ".clear();");
          tl(2, "if(objects == null)");
          tl(3, "return;");
          tl(2, "for(int i = 0; i < objects.size(); i++) {");
          tl(3, "Instant o = objects.getInstant(i);");
          tl(3, "add", entiteVarCapitalise, "(o);");
          tl(2, "}");
          tl(1, "}");
          if(ecrireCommentaire) {
            tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
          }
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
          tl(2, entiteNomSimpleCompletGenerique, " p = LocalDateTime.parse(o, ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER);");
          tl(2, "add", entiteVarCapitalise, "(p);");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
          tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(Date o) {");
          tl(2, entiteNomSimpleCompletGenerique, " p = LocalDateTime.ofInstant(o.toInstant(), ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), ")));");
          tl(2, "add", entiteVarCapitalise, "(p);");
          tl(2, "return (", classeNomSimple, ")this;");
          tl(1, "}");
        }
      }
  
      // Initialise //

      if(entiteMethodesAvantVar != null && entiteMethodesAvantVar.size() > 0) {
        for(int j = 0; j < entiteMethodesAvantVar.size(); j++) {
          String entiteMethodeAvantVisibilite = entiteMethodesAvantVisibilite.get(j);
          String entiteMethodeAvantVar = entiteMethodesAvantVar.get(j);
          String entiteMethodeAvantParamVar = entiteMethodesAvantParamVar.get(j);
          String entiteMethodeAvantParamNomSimple = entiteMethodesAvantParamNomSimple.get(j);
          Boolean entiteMethodeAvantNomParam = entiteMethodesAvantNomParam.get(j);
          Boolean entiteMethodeAvantEcrire = entiteMethodesAvantEcrire.get(j);

          if(BooleanUtils.isTrue(entiteMethodeAvantEcrire)) {
            t(1, entiteMethodeAvantVisibilite, " abstract void ", entiteMethodeAvantVar, "(", entiteMethodeAvantParamNomSimple, " ", entiteMethodeAvantParamVar);
            if(entiteMethodeAvantNomParam)
              s(", String entiteVar");
            l(");");
          }
        }
      }
  
      // Initialiser //
      if(entitePromesse) {
        tl(1, "protected Future<", entiteNomSimpleComplet, "> ", entiteVar, langueConfig.getString(I18n.var_Promesse), "() {");
        tl(2, "Promise<", entiteNomSimpleComplet, "> promise = Promise.promise();");
  
        tl(2, "Promise<", entiteNomSimpleComplet, "> promise2 = Promise.promise();");
        tl(2, "_", entiteVar, "(promise2);");
        tl(2, "promise2.future().onSuccess(o -> {");
        if(entiteInitLoin && entiteInitialise) {
          tl(3, "if(o != null && ", entiteVar, " == null) {");
          tl(4, "o.", langueConfig.getString(I18n.var_promesseLoin), langueConfig.getString(I18n.var_PourClasse), "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ").onSuccess(a -> {");
          tl(5, "set", entiteVarCapitalise, "(o);");
          tl(5, "promise.complete(o);");
          tl(4, "}).onFailure(ex -> {");
          tl(5, "promise.fail(ex);");
          tl(4, "});");
          tl(3, "} else {");
          tl(4, "promise.complete(o);");
          tl(3, "}");
        }
        else {
          tl(3, "set", entiteVarCapitalise, "(o);");
          tl(3, "promise.complete(o);");
        }
        tl(2, "}).onFailure(ex -> {");
        tl(3, "promise.fail(ex);");
        tl(2, "});");
        tl(2, "return promise.future();");
      } else {
        t(1, "protected ", classeNomSimple, " ", entiteVar, "Init()");
        if(classeInitLoinExceptions.size() > 0) {
          s(" throws ");
          for(int i = 0; i < classeInitLoinExceptions.size(); i++) {
            String classeInitLoinException = classeInitLoinExceptions.get(i);
            String classeInitLoinExceptionNomSimple = StringUtils.substringAfterLast(classeInitLoinException, ".");
            if(i > 0)
              s(", ");
            s(classeInitLoinExceptionNomSimple);
          }
        }
        l(" {");
  
        if(entiteNomCanoniqueGenerique == null && entiteMethodesAvantVar != null && entiteMethodesAvantVar.size() > 0) {
          tl(2, "if(", entiteVar, " != null) {");
          for(int j = 0; j < entiteMethodesAvantVar.size(); j++) {
            String entiteMethodeAvantVar = entiteMethodesAvantVar.get(j);
            Boolean entiteMethodeAvantNomParam = entiteMethodesAvantNomParam.get(j);
  
            t(3, "((", classeNomSimple, ")this).", entiteMethodeAvantVar, "(", entiteVar);
            if(entiteMethodeAvantNomParam)
              s(", \"", entiteVar, "\"");
            l(");");
          }
          tl(2, "}");
        }
  
        if(entiteCouverture) {
          t(2, classePartsCouverture.nomSimple(langueNom), "<", entiteNomSimpleComplet, "> ", entiteVar, classePartsCouverture.nomSimple(langueNom));
          l(" = new ", classePartsCouverture.nomSimple(langueNom), "<", entiteNomSimpleComplet, ">().var(\"", entiteVar, "\");");
          tl(2, "if(", entiteVar, " == null) {");
          if(entiteDefaut != null) {
            tl(3, "set", entiteVarCapitalise, "(\"", escapeJava(entiteDefaut), "\");");
          } else {
            tl(3, "_", entiteVar, "(", entiteVar, classePartsCouverture.nomSimple(langueNom), ");");
            tl(3, "Optional.ofNullable(", entiteVar, classePartsCouverture.nomSimple(langueNom), ".getO()).ifPresent(o -> {");
            tl(4, "set", entiteVarCapitalise, "(o);");
            tl(3, "});");
          }
          tl(2, "}");
        }
        else {
          tl(2, "_", entiteVar, "(", entiteVar, ");");
        }
        if(entiteInitLoin && entiteInitialise) {
          if(entiteCouverture) {
            tl(2, "if(", entiteVar, " != null)");
            tl(3, entiteVar, ".", langueConfig.getString(I18n.var_initLoin), langueConfig.getString(I18n.var_PourClasse), "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ");");
          }
          else {
            tl(2, entiteVar, ".", langueConfig.getString(I18n.var_initLoin), langueConfig.getString(I18n.var_PourClasse), "(", classeContientRequeteSite ? (langueConfig.getString(I18n.var_requeteSite) + "_") : "null", ");");
          }
        }
  
        if(entiteNomCanoniqueGenerique == null && entiteMethodesApresVar != null && entiteMethodesApresVar.size() > 0) {
          tl(2, "if(", entiteVar, " != null) {");
          for(int j = 0; j < entiteMethodesApresVar.size(); j++) {
            String entiteMethodeApresVisibilite = entiteMethodesApresVisibilite.get(j);
            String entiteMethodeApresVar = entiteMethodesApresVar.get(j);
            Boolean entiteMethodeApresNomParam = entiteMethodesApresNomParam.get(j);
  
            t(3, "((", classeNomSimple, ")this).", entiteMethodeApresVar, "(", entiteVar);
            if(entiteMethodeApresNomParam)
              s(", \"", entiteVar, "\"");
            l(");");
          }
          tl(2, "}");
        }
  
        tl(2, "return (", classeNomSimple, ")this;");
      }
      tl(1, "}");

      if(entiteMethodesApresVar != null) {
        for(int j = 0; j < entiteMethodesApresVar.size(); j++) {
          String entiteMethodeApresVisibilite = entiteMethodesApresVisibilite.get(j);
          String entiteMethodeApresVar = entiteMethodesApresVar.get(j);
          String entiteMethodeApresParamVar = entiteMethodesApresParamVar.get(j);
          String entiteMethodeApresParamNomSimple = entiteMethodesApresParamNomSimple.get(j);
          Boolean entiteMethodeApresNomParam = entiteMethodesApresNomParam.get(j);
          Boolean entiteMethodeApresEcrire = entiteMethodesAvantEcrire.get(j);

          if(BooleanUtils.isTrue(entiteMethodeApresEcrire)) {
            t(1, entiteMethodeApresVisibilite, " abstract void ", entiteMethodeApresVar, "(", entiteMethodeApresParamNomSimple, " ", entiteMethodeApresParamVar);
            if(entiteMethodeApresNomParam)
              s(", String entiteVar");
            l(");");
          }
        }
      }

      if(classePartsRequeteSite != null) {
        if(entiteSolrNomSimple != null) {
          l();
          if(classeContientRequeteSite && entiteNomSimple != null && entiteSolrNomCanonique != null) {
            if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
              String entiteSolrNomSimple2 = StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteSolrNomSimple, "<"), ">");
              if(entiteNomSimpleCompletGenerique.equals("Timestamp")) {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o == null ? null : ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));");
                tl(1, "}");
              }
              else if(entiteNomCanoniqueGenerique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o == null ? null : ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));");
                tl(1, "}");
              }
              else if(entiteNomCanoniqueGenerique.toString().equals(LocalTime.class.getCanonicalName())) {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);");
                tl(1, "}");
              }
              else if(entiteNomCanoniqueGenerique.toString().equals(LocalDateTime.class.getCanonicalName())) {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o == null ? null : Date.from(o.atZone(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\")).toInstant());");
                tl(1, "}");
              }
              else if(entiteNomSimpleCompletGenerique.toString().equals("LocalDate")) {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\")).toInstant());");
                tl(1, "}");
              }
              else if(entiteNomSimpleCompletGenerique.toString().equals("Point")) {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o;");
                tl(1, "}");
              }
              else if(entiteNomSimpleCompletGenerique.toString().equals("Path")) {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o;");
                tl(1, "}");
              }
              else if(entiteNomSimpleCompletGenerique.toString().equals("Polygon")) {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o;");
                tl(1, "}");
              }
              else if(entiteNomSimpleCompletGenerique.toString().equals("JsonObject")) {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o.toString();");
                tl(1, "}");
              }
              else if(entiteNomSimpleCompletGenerique.toString().equals("JsonArray")) {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o.toString();");
                tl(1, "}");
              }
              else if(entiteNomSimpleCompletGenerique.toString().equals("BigDecimal")) {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o == null ? null : o.doubleValue();");
                tl(1, "}");
              }
              else {
                tl(1, "public static ", entiteSolrNomSimple2, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleCompletGenerique, " o) {");
                tl(2, "return o;");
                tl(1, "}");
              }
            } else {
              if(entiteNomSimple.equals("Timestamp")) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o == null ? null : ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));");
                tl(1, "}");
              }
              else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o == null ? null : ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER.format(o.toInstant().atOffset(ZoneOffset.UTC));");
                tl(1, "}");
              }
              else if(entiteNomCanonique.toString().equals(LocalTime.class.getCanonicalName())) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);");
                tl(1, "}");
              }
              else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o == null ? null : Date.from(o.atZone(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\")).toInstant());");
                tl(1, "}");
              }
              else if(entiteNomSimple.toString().equals("LocalDate")) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\")).toInstant());");
                tl(1, "}");
              }
              else if(entiteNomSimple.toString().equals("Point")) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o;");
                tl(1, "}");
              }
              else if(entiteNomSimple.toString().equals("Path")) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o;");
                tl(1, "}");
              }
              else if(entiteNomSimple.toString().equals("Polygon")) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o;");
                tl(1, "}");
              }
              else if(entiteNomSimple.toString().equals("JsonObject")) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o.toString();");
                tl(1, "}");
              }
              else if(entiteNomSimple.toString().equals("JsonArray")) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o.toString();");
                tl(1, "}");
              }
              else if(entiteNomSimple.toString().equals("BigDecimal")) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o == null ? null : o.doubleValue();");
                tl(1, "}");
              }
              else if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
                tl(1, "public static ", StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteSolrNomSimple, "<"), ">"), " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleGenerique, " o) {");
                tl(2, "return o;");
                tl(1, "}");
              }
              else if("java.util.Set".equals(entiteNomCanonique) || "java.util.HashSet".equals(entiteNomCanonique)) {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return new ArrayList<>(o);");
                tl(1, "}");
              }
              else {
                tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
                tl(2, "return o;");
                tl(1, "}");
              }
            }
          }
          else {
            tl(1, "public static Object staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
            tl(2, "return null;");
            tl(1, "}");
          }
    
          l();
          if(classeContientRequeteSite && entiteNomSimple != null && entiteSolrNomCanonique != null) {
            if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
              String entiteSolrNomSimple2 = StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteSolrNomSimple, "<"), ">");
              if(entiteNomSimpleCompletGenerique.equals("Timestamp")) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteSolrNomSimple2, " o) {");
                tl(2, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o));");
                tl(1, "}");
              }
              else if(entiteNomCanoniqueGenerique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteSolrNomSimple2, " o) {");
                tl(2, "ZoneId zoneId = ZoneId.of(\"UTC\");");
                tl(2, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o, zoneId));");
                tl(1, "}");
              }
              else if(entiteNomCanoniqueGenerique.toString().equals(LocalDateTime.class.getCanonicalName())) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteSolrNomSimple2, " o) {");
                tl(2, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o));");
                tl(1, "}");
              }
              else if(entiteNomSimpleCompletGenerique.toString().equals("LocalDate")) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteSolrNomSimple2, " o) {");
      //					tl(3, "doc.put(\"", entiteVar, "_suggested", "\", ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER.format(", entiteVar, ".atStartOfDay(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
                tl(2, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o));");
                tl(1, "}");
              }
              else if(entiteNomSimpleCompletGenerique.toString().equals("Path")) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ",entiteSolrNomSimple2, " o) {");
                tl(2, "JsonArray pointsArray = new JsonArray();");
                tl(2, "o.getPoints().stream().map(point -> new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))).collect(Collectors.toList()).forEach(pointArray -> pointsArray.add(pointArray));");
                tl(2, "return new JsonObject().put(\"type\", \"LineString\").put(\"coordinates\", pointsArray).toString();");
                tl(1, "}");
              }
              else if(entiteNomSimpleCompletGenerique.toString().equals("Polygon")) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ",entiteSolrNomSimple2, " o) {");
                tl(2, "JsonArray pointsArray = new JsonArray();");
                tl(2, "o.getPoints().stream().map(point -> new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))).collect(Collectors.toList()).forEach(pointArray -> pointsArray.add(pointArray));");
                tl(2, "return new JsonObject().put(\"type\", \"LineString\").put(\"coordinates\", pointsArray).toString();");
                tl(1, "}");
              }
              else {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteSolrNomSimple2, " o) {");
                tl(2, "return o == null ? null : o.toString();");
                tl(1, "}");
              }
            } else {
              if(entiteNomSimple.equals("Timestamp")) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
                tl(2, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o));");
                tl(1, "}");
              }
              else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
                tl(2, "ZoneId zoneId = ZoneId.of(\"UTC\");");
                tl(2, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o, zoneId));");
                tl(1, "}");
              }
              else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
                tl(2, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o));");
                tl(1, "}");
              }
              else if(entiteNomSimple.toString().equals("LocalDate")) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
      //					tl(3, "doc.put(\"", entiteVar, "_suggested", "\", ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER.format(", entiteVar, ".atStartOfDay(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
                tl(2, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o));");
                tl(1, "}");
              }
              else if(entiteSolrNomCanonique.toString().equals("String")) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
                tl(2, "return o;");
                tl(1, "}");
              }
              else if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteSolrNomSimple, "<"), ">"), " o) {");
                tl(2, "return o == null ? null : o.toString();");
                tl(1, "}");
              }
              else if(entiteNomSimple.toString().equals("Path")) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ",entiteSolrNomSimple, " o) {");
                tl(2, "JsonArray pointsArray = new JsonArray();");
                tl(2, "o.getPoints().stream().map(point -> new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))).collect(Collectors.toList()).forEach(pointArray -> pointsArray.add(pointArray));");
                tl(2, "return new JsonObject().put(\"type\", \"LineString\").put(\"coordinates\", pointsArray).toString();");
                tl(1, "}");
              }
              else if(entiteNomSimple.toString().equals("Polygon")) {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ",entiteSolrNomSimple, " o) {");
                tl(2, "JsonArray pointsArray = new JsonArray();");
                tl(2, "o.getPoints().stream().map(point -> new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))).collect(Collectors.toList()).forEach(pointArray -> pointsArray.add(pointArray));");
                tl(2, "return new JsonObject().put(\"type\", \"LineString\").put(\"coordinates\", pointsArray).toString();");
                tl(1, "}");
              }
              else {
                tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
                tl(2, "return o == null ? null : o.toString();");
                tl(1, "}");
              }
            }
          }
          else {
            tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, Object o) {");
            tl(2, "return null;");
            tl(1, "}");
          }
    
          l();
          tl(1, "public static String staticSearchFq", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o) {");
          
          //STUFFNOW
          if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())
              || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, ZonedDateTime.class.getCanonicalName())) {
            tl(2, "ZoneId zoneId = ZoneId.of(\"UTC\");");
            tl(2, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o, zoneId)).toString();");
          } else if(VAL_nomCanoniquePolygon.equals(entiteNomCanoniqueGenerique)) {
            tl(2, "return o;");
          } else {
            tl(2, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o)).toString();");
          }
          tl(1, "}");
        }
      }
      else {
        LOG.error(String.format("%s %s %s %s %s. ", langueConfig.getString(I18n.var_classe), langueConfig.getString(I18n.var_RequeteSite), langueConfig.getString(I18n.var_manquante), langueConfig.getString(I18n.var_dans), cheminSrcMainJava));
      }

      //////////
      // htm //
      //////////
      if(classeContientRequeteSite && entiteNomSimple != null && entiteSolrNomCanonique != null) {
  
        /////////
        // sql //
        /////////
        if(entiteDefinir || entiteAttribuer) {
          l();
          if(VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanonique)) {
            tl(1, "public OffsetDateTime sql", entiteVarCapitalise, "() {");
            tl(2, "return ", entiteVar, " == null ? null : ", entiteVar, ".toOffsetDateTime();");
            tl(1, "}");
          } else if(VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)) {
            tl(1, "public Number[] sql", entiteVarCapitalise, "() {");
            tl(2, "return ", entiteVar, ".stream().map(v -> (Number)v).toArray(Number[]::new);");
            tl(1, "}");
          } else if(VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)) {
            tl(1, "public Number[] sql", entiteVarCapitalise, "() {");
            tl(2, "return ", entiteVar, ".stream().map(v -> (Number)v).toArray(Number[]::new);");
            tl(1, "}");
          } else if(VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)) {
            tl(1, "public Number[] sql", entiteVarCapitalise, "() {");
            tl(2, "return ", entiteVar, ".stream().map(v -> (Number)v).toArray(Number[]::new);");
            tl(1, "}");
          } else if(VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)) {
            tl(1, "public Number[] sql", entiteVarCapitalise, "() {");
            tl(2, "return ", entiteVar, ".stream().map(v -> (Number)v).toArray(Number[]::new);");
            tl(1, "}");
          } else if(VAL_nomCanoniqueString.equals(entiteNomCanoniqueGenerique)) {
            tl(1, "public String[] sql", entiteVarCapitalise, "() {");
            tl(2, "return ", entiteVar, ".stream().map(v -> (String)v).toArray(String[]::new);");
            tl(1, "}");
          } else if(VAL_nomCanoniquePolygon.equals(entiteNomCanoniqueGenerique)) {
            // STUFFNOW
            tl(1, "public String sql", entiteVarCapitalise, "() {");
            tl(2, "JsonArray coordinates = new JsonArray();");
            tl(2, "JsonObject json = new JsonObject().put(\"type\", \"Polygon\").put(\"coordinates\", coordinates);");
            tl(2, "for(", entiteNomSimpleCompletGenerique, " o : ", entiteVar, ") {");
            tl(3, "JsonArray coordinates2 = new JsonArray();");
            tl(3, "coordinates.add(coordinates2);");
            tl(3, "o.getPoints().forEach(point -> {");
            tl(4, "coordinates2.add(new JsonArray().add(point.getX()).add(point.getY()));");
            tl(3, "});");
            tl(2, "}");
            tl(2, "return json.toString();");
            tl(1, "}");
          } else {
            tl(1, "public ", entiteNomSimpleComplet, " sql", entiteVarCapitalise, "() {");
            tl(2, "return ", entiteVar, ";");
            tl(1, "}");
          }
        }
  
        //////////
        // json //
        //////////
        if(entiteDefinir || entiteAttribuer) {
          l();
          if(VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)
              || VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)
              || VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)
              || VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)
              || VAL_nomCanoniqueString.equals(entiteNomCanoniqueGenerique)
              ) {
            tl(1, "public static JsonArray staticJson", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVar, ") {");
            tl(2, "JsonArray a = new JsonArray();");
            tl(2, entiteVar, ".stream().forEach(v -> a.add(v.toString()));");
            tl(2, "return a;");
            tl(1, "}");
          } else if(VAL_nomCanoniquePolygon.equals(entiteNomCanoniqueGenerique)) {
            tl(1, "public static JsonObject staticJson", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVar, ") {");
            tl(2, "return Optional.ofNullable(", entiteVar, ").map(v -> VertxTool.toGeoJson(v)).orElse(null);");
            tl(1, "}");
          } else if(VAL_nomCanoniqueString.equals(entiteNomCanonique)
              || VAL_nomCanoniqueBoolean.equals(entiteNomCanonique)
              || VAL_nomCanoniqueVertxJsonArray.equals(entiteNomCanonique)
              || VAL_nomCanoniqueVertxJsonObject.equals(entiteNomCanonique)
              ) {
            tl(1, "public static ", entiteNomSimpleComplet, " staticJson", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVar, ") {");
            tl(2, "return ", entiteVar, ";");
            tl(1, "}");
          } else if(VAL_nomCanoniqueLocalDate.equals(entiteNomCanonique)) {
            tl(1, "public static String staticJson", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVar, ") {");
            tl(2, "return Optional.ofNullable(", entiteVar, ").map(v -> v.format(ComputateLocalDateSerializer.LOCAL_DATE_FORMATTER_YYYY_MM_DD)).orElse(null);");
            tl(1, "}");
          } else if(VAL_nomCanoniqueLocalTime.equals(entiteNomCanonique)) {
            tl(1, "public static String staticJson", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVar, ") {");
            tl(2, "return Optional.ofNullable(", entiteVar, ").map(v -> v.format(ComputateLocalTimeSerializer.LOCAL_TIME_FORMATTER_HH_MM_SS_SSS)).orElse(null);");
            tl(1, "}");
          } else if(VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanonique)) {
            tl(1, "public static String staticJson", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVar, ") {");
            tl(2, "return Optional.ofNullable(", entiteVar, ").map(v -> v.format(ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER)).orElse(null);");
            tl(1, "}");
          } else if(VAL_nomCanoniquePoint.equals(entiteNomCanonique)
              || VAL_nomCanoniquePath.equals(entiteNomCanonique)
              || VAL_nomCanoniquePolygon.equals(entiteNomCanonique)
              ) {
            tl(1, "public static JsonObject staticJson", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVar, ") {");
            tl(2, "return Optional.ofNullable(", entiteVar, ").map(v -> VertxTool.toGeoJson(v)).orElse(null);");
            tl(1, "}");
          } else {
            tl(1, "public static String staticJson", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVar, ") {");
            tl(2, "return Optional.ofNullable(", entiteVar, ").map(v -> v.toString()).orElse(null);");
            tl(1, "}");
          }
        }
      }

      if(entiteSolrNomSimple != null) {

        ///////////////
        // staticSet //
        ///////////////
  
        wStaticSet.tl(2, "case \"", entiteVar, "\":");
        if((
            StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())
            || entiteEstListe && StringUtils.equals(entiteNomCanoniqueGenerique, ZonedDateTime.class.getCanonicalName()))) {
          if(classeVarZone != null) {
            wStaticSet.tl(3, "return ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, v, Optional.ofNullable(o.get", StringUtils.capitalize(classeVarZone), "()).map(zoneId -> ZoneId.of(v)).orElse(Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_).map(r -> r.get", langueConfig.getString(I18n.var_Config), "()).map(config -> config.getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), ")).map(z -> ZoneId.of(z)).orElse(ZoneId.of(\"UTC\"))));");
          } else {
          }
        } else {
          wStaticSet.tl(3, "return ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, v);");
        }
  
        ////////////////
        // staticSearch //
        ////////////////
  
        wstaticSearch.tl(2, "case \"", entiteVar, "\":");
        if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
          wstaticSearch.tl(3, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, (", entiteNomSimpleGenerique, ")o);");
        }
        else {
          wstaticSearch.tl(3, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, (", entiteNomSimpleComplet, ")o);");
        }
  
        ///////////////////
        // staticSearchStr //
        ///////////////////
  
        wstaticSearchStr.tl(2, "case \"", entiteVar, "\":");
        if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
          wstaticSearchStr.tl(3, "return ", classeNomSimple, ".staticSearchStr", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, (", entiteSolrNomSimple == null ? "Object" : StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteSolrNomSimple, "<"), ">"), ")o);");
        }
        else {
          wstaticSearchStr.tl(3, "return ", classeNomSimple, ".staticSearchStr", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, (", entiteSolrNomSimple == null ? "Object" : entiteSolrNomSimple, ")o);");
        }
  
        //////////////////
        // staticSearchFq //
        //////////////////
  
        wstaticSearchFq.tl(2, "case \"", entiteVar, "\":");
        wstaticSearchFq.tl(3, "return ", classeNomSimple, ".staticSearchFq", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o);");
      }
  
      ////////////////////
      // codeInitLoin //
      ////////////////////
      if(entitePromesse) {
        wInitLoin.tl(4, "promise2.complete();");
        wInitLoin.tl(3, "} catch(Exception ex) {");
        wInitLoin.tl(4, "promise2.fail(ex);");
        wInitLoin.tl(3, "}");
        wInitLoin.tl(3, "return promise2.future();");
        wInitLoin.tl(2, "}).compose(a -> {");
        wInitLoin.tl(3, "Promise<Void> promise2 = Promise.promise();");
        wInitLoin.tl(3, entiteVar, langueConfig.getString(I18n.var_Promesse), "().onSuccess(", entiteVar, " -> {");
        wInitLoin.tl(4, "promise2.complete();");
        wInitLoin.tl(3, "}).onFailure(ex -> {");
        wInitLoin.tl(4, "promise2.fail(ex);");
        wInitLoin.tl(3, "});");
        wInitLoin.tl(3, "return promise2.future();");
        wInitLoin.tl(2, "}).compose(a -> {");
        wInitLoin.tl(3, "Promise<Void> promise2 = Promise.promise();");
        wInitLoin.tl(3, "try {");
      } else if(classePromesse) {
        wInitLoin.tl(4, entiteVar, "Init();");
      } else {
        wInitLoin.tl(4, entiteVar, "Init();");
      }
  
  
      /////////////////////
      // codeRequeteSite //
      /////////////////////
      if(classeContientRequeteSite && entiteContientRequeteSite && classeInitLoin && entiteInitialise) {
        o = wRequeteSite;
        tl(2, "if(", entiteVar, " != null)");
        tl(3, entiteVar, ".set", langueConfig.getString(I18n.var_RequeteSite), "_(", langueConfig.getString(I18n.var_requeteSite), "_);");
      }
  
      /////////////////
      // codeIndexer //
      /////////////////
      o = wIndexer;
      if(classeIndexe && entiteIndexeOuStocke) {
        tl(2, "if(", entiteVar, " != null) {");
        if(StringUtils.isNotEmpty(classeVarCleUnique) && entiteCleUnique) {
          // cleUnique
          tl(3, "doc.put(\"", classeVarCleUnique, "\", ", entiteVar, ");");
        }
        if(entiteCrypte) {
          // crypte
          tl(3, "String valCrypte = requeteSite.crypterStr(", entiteVar, ");");
          tl(3, "doc.put(\"", entiteVar, "_encrypted", entiteSuffixeType, "\"", "valCrypte);");
        }
        if(entiteIncremente) {
          // crypte
          tl(3, "doc.put(\"", entiteVar, "_incremented", "\", new java.util.HashMap<String, ", entiteNomSimple, ">() {{ put(\"inc\"", ("Long".equals(entiteNomSimple.toString()) ? "1L" : "1"), "); }});");
        }
        if(entiteSuggere) {
          // suggere
          if(entiteNomSimple.equals("Timestamp")) {
            tl(3, "doc.put(\"", entiteVar, "_suggested", "\", ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
          }
          else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
            tl(3, "doc.put(\"", entiteVar, "_suggested", "\", ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER.format(", entiteVar, "));");
          }
          else if(entiteNomCanonique.toString().equals(LocalTime.class.getCanonicalName())) {
            tl(3, "doc.put(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_TIME.format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
          }
          else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
            tl(3, "doc.put(\"", entiteVar, "_suggested", "\", ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER.format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
          }
          else if(entiteNomSimple.toString().equals("LocalDate")) {
            tl(3, "doc.put(\"", entiteVar, "_suggested", "\", ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".UTC_DATE_TIME_FORMATTER.format(", entiteVar, ".atStartOfDay(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
          }
          else {
            tl(3, "doc.put(\"", entiteVar, "_suggested", "\", ", entiteVar, ");");
          }
        }
        if(entiteTexte) {
          if(entiteLangue == null)
            entiteLangue = langueNom;
          if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
            tl(3, "JsonArray l = new JsonArray();");
            tl(3, "doc.put(\"", entiteVar, "_text_", entiteLangue, "\", l);");
            tl(3, "for(", entiteNomSimpleCompletGenerique, " o : ", entiteVar, ") {");
            tl(4, "l.add(o", "String".equals(entiteNomSimpleCompletGenerique) ? "" : ".toString()", ");");
            tl(3, "}");
          }
          else {
            tl(3, "doc.put(\"", entiteVar, "_text_", entiteLangue, "\", ", entiteVar, "", "String".equals(entiteNomSimpleCompletGenerique) ? "" : ".toString()", ");");
          }
        }
  
        if(entiteNomSimple != null && entiteIndexe && !entiteSuggere && !entiteCleUnique && !entiteTexte) {
          // indexe
          if(entiteNomSimple.equals("Timestamp")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
          }
          else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(", entiteVar, ".toInstant(), ZoneId.of(\"UTC\"))));");
          }
          else if(entiteNomCanonique.toString().equals(LocalTime.class.getCanonicalName())) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"HH:mm\").format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
          }
          else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
          }
          else if(entiteNomSimple.toString().equals("LocalDate")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(", entiteVar, ".atStartOfDay(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
          }
          else if(entiteNomSimple.toString().equals("Point")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", String.format(\"%s,%s\", ", entiteVar, ".getY(), ", entiteVar, ".getX()));");
          }
          else if(entiteNomSimple.toString().equals("Path")) {
            tl(3, "JsonArray pointsArray = new JsonArray();");
            tl(3, entiteVar, ".getPoints().stream().map(point -> new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))).collect(Collectors.toList()).forEach(pointArray -> pointsArray.add(pointArray));");
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", new JsonObject().put(\"type\", \"LineString\").put(\"coordinates\", pointsArray).toString());");
          }
          else if(entiteNomSimple.toString().equals("Polygon")) {
            tl(3, "JsonArray pointsArray = new JsonArray();");
            tl(3, entiteVar, ".getPoints().stream().map(point -> new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))).collect(Collectors.toList()).forEach(pointArray -> pointsArray.add(pointArray));");
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", new JsonObject().put(\"type\", \"LineString\").put(\"coordinates\", pointsArray).toString());");
          }
          else if(entiteNomSimple.toString().equals("JsonObject")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", ", entiteVar, ".encode());");
          }
          else if(entiteNomSimple.toString().equals("JsonArray")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", ", entiteVar, ".encode());");
          }
          else if(entiteNomSimple.toString().equals("BigDecimal")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", ", entiteVar, ".toPlainString());");
          }
          else if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
            if(entiteNomSimple.toString().equals("Point")) {
              tl(3, "JsonArray l = new JsonArray();");
              tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", l);");
              tl(3, "for(", entiteNomSimpleCompletGenerique, " o : ", entiteVar, ") {");
              tl(4, "l.add(o.toString());");
              tl(3, "}");
            } else if(entiteNomSimpleGenerique != null && entiteNomSimpleGenerique.equals("Path")) {
              tl(3, "JsonArray l = new JsonArray();");
              tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", l);");
              tl(3, "for(", entiteNomSimpleCompletGenerique, " o : ", entiteVar, ") {");
              tl(4, "JsonArray coordinates = new JsonArray();");
              tl(4, "o.getPoints().forEach(point -> {");
              tl(5, "coordinates.add(new JsonArray().add(point.getX()).add(point.getY()));");
              tl(4, "});");
              tl(4, "JsonObject json = new JsonObject().put(\"type\", \"Path\").put(\"coordinates\", coordinates);");
              tl(4, "l.add(json.toString());");
              tl(3, "}");
            } else if(entiteNomSimpleGenerique != null && entiteNomSimpleGenerique.equals("Polygon")) {
              // STUFFNOW
              tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", Optional.ofNullable(geojson", entiteVarCapitalise, "()).map(geojson -> geojson.toString()).orElse(null));");
              // tl(3, "JsonArray l = new JsonArray();");
              // tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", l);");
              // tl(3, "JsonArray coordinates = new JsonArray();");
              // tl(3, "JsonObject json = new JsonObject().put(\"type\", \"Polygon\").put(\"coordinates\", coordinates);");
              // tl(3, "for(", entiteNomSimpleCompletGenerique, " o : ", entiteVar, ") {");
              // tl(4, "JsonArray coordinates2 = new JsonArray();");
              // tl(4, "coordinates.add(coordinates2);");
              // tl(4, "o.getPoints().forEach(point -> {");
              // tl(5, "coordinates2.add(new JsonArray().add(point.getX()).add(point.getY()));");
              // tl(4, "});");
              // tl(4, "l.add(json.toString());");
              // tl(3, "}");
            } else {
              tl(3, "JsonArray l = new JsonArray();");
              tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", l);");
              tl(3, "for(", entiteNomSimpleCompletGenerique, " o : ", entiteVar, ") {");
              tl(4, "l.add(", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, o));");
              tl(3, "}");
            }
          }
          else {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", ", entiteVar, ");");
          }
        }
  
        if(!entiteIndexe && entiteStocke && !entiteCleUnique) {
          // stocke
          if(entiteNomSimple.equals("Timestamp")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
          }
          else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(", entiteVar, ".toInstant(), ZoneId.of(\"UTC\"))));");
          }
          else if(entiteNomCanonique.toString().equals(LocalTime.class.getCanonicalName())) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"HH:mm\").format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
          }
          else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
          }
          else if(entiteNomSimple.toString().equals("LocalDate")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(", entiteVar, ".atStartOfDay(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
          }
          else if(entiteNomSimple.toString().equals("Point")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", new Point(", entiteVar, ".getPoint1(), ", entiteVar, ".getPoint2()));");
          }
          else if(entiteNomSimple.toString().equals("Path")) {
            tl(3, "JsonArray pointsArray = new JsonArray();");
            tl(3, "path.getPoints().stream().map(point -> new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))).collect(Collectors.toList()).forEach(pointArray -> pointsArray.add(pointArray));");
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", new JsonObject().put(\"type\", \"LineString\").put(\"coordinates\", pointsArray).toString());");
          }
          else if(entiteNomSimple.toString().equals("Polygon")) {
            tl(3, "JsonArray pointsArray = new JsonArray();");
            tl(3, "path.getPoints().stream().map(point -> new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))).collect(Collectors.toList()).forEach(pointArray -> pointsArray.add(pointArray));");
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", new JsonObject().put(\"type\", \"LineString\").put(\"coordinates\", pointsArray).toString());");
          }
          else if(entiteNomSimple.toString().equals("JsonObject")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", ", entiteVar, ".toString());");
          }
          else if(entiteNomSimple.toString().equals("JsonArray")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", ", entiteVar, ".toString());");
          }
          else if(entiteNomSimple.toString().equals("BigDecimal")) {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", ", entiteVar, ".toPlainString());");
          }
          else if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
            tl(3, "for(", entiteNomCanoniqueGenerique, " o : ", entiteVar, ") {");
            tl(4, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", o);");
            tl(3, "}");
          }
          else {
            tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", ", entiteVar, ");");
          }
        }
        tl(2, "}");
      }

      if(classeIndexe) {
        if(entiteStocke || entiteDocValues) {
          wVarStocke.tl(3, "case \"", entiteVar, "\":");
          if(StringUtils.isNotEmpty(classeVarCleUnique) && entiteCleUnique) {
            wVarStocke.tl(4, "return \"", classeVarCleUnique, "\";");
            wRechercheVar.tl(3, "case \"", classeVarCleUnique, "\":");
          } else {
            wVarStocke.tl(4, "return \"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe && entiteStocke ? "_indexedstored" : "_stored")), entiteSuffixeType, "\";");
            if(!entiteDocValues && !entiteStocke) {
              wRechercheVar.tl(3, "case \"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe && entiteStocke ? "_indexedstored" : "_stored")), entiteSuffixeType, "\":");
              wRechercheVar.tl(4, "return \"", entiteVar, "\";");
            }
          }
        }
        if(entiteIndexe) {
          wVarIndexe.tl(3, "case \"", entiteVar, "\":");
          if(StringUtils.isNotEmpty(classeVarCleUnique) && entiteCleUnique) {
            wVarIndexe.tl(4, "return \"", classeVarCleUnique, "\";");
            wRechercheVar.tl(3, "case \"", classeVarCleUnique, "\":");
          } else if(entiteSuggere) {
            wVarIndexe.tl(4, "return \"", entiteVar, "_suggested\";");
            wRechercheVar.tl(3, "case \"", entiteVar, "_suggested\":");
          } else if(entiteTexte && entiteLangue != null) {
            wVarIndexe.tl(4, "return \"", entiteVar, "_text_" + entiteLangue, "\";");
            wRechercheVar.tl(3, "case \"", entiteVar, "_text_" + entiteLangue, "\":");
          } else {
            wVarIndexe.tl(4, "return \"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\";");
            wRechercheVar.tl(3, "case \"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\":");
          }
          wRechercheVar.tl(4, "return \"", entiteVar, "\";");
        }
        if(entiteTexte && entiteLangue != null) {
          wVarRecherche.tl(3, "case \"", entiteVar, "\":");
          wVarRecherche.tl(4, "return \"", entiteVar, "_text_" + entiteLangue, "\";");
        }
        if(entiteSuggere) {
          wVarSuggere.tl(3, "case \"", entiteVar, "\":");
          wVarSuggere.tl(4, "return \"", entiteVar, "_suggested\";");
        }
      }

//			if(entiteFacetsTrouves) {
//				for(String entiteFacet : entiteFacets) {
//					if("terms".equals(entiteFacet))
//						wFacets.tl(3, langueConfig.getString(ConfigCles.var_listeRecherche), ".add(\"json.facet\", \"{", entiteFacet, "_", entiteVar, ":{terms:{field:", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "}}}\");");
//					else
//						wFacets.tl(3, langueConfig.getString(ConfigCles.var_listeRecherche), ".add(\"json.facet\", \"{", entiteFacet, "_", entiteVar, ":'", entiteFacet, "(", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, ")'}\");");
//				}
//			}

      if(entiteAttribuer && !classesNomSimpleFacetFor.contains(entiteAttribuerNomSimple)) {
        wIndexerFacetFor.l();
        wIndexerFacetFor.tl(5, "if(\"", entiteAttribuerNomSimple, "\".equals(", langueConfig.getString(I18n.var_classeNomSimple), "2) && ", i18nGlobale.getString(I18n.var_solrId), "2 != null) {");
        wIndexerFacetFor.tl(6, langueConfig.getString(I18n.var_ListeRecherche), "<", entiteAttribuerNomSimple, "> ", langueConfig.getString(I18n.var_listeRecherche), "2 = new ", langueConfig.getString(I18n.var_ListeRecherche), "<", entiteAttribuerNomSimple, ">();");
        wIndexerFacetFor.tl(6, langueConfig.getString(I18n.var_listeRecherche), "2.set", langueConfig.getString(I18n.var_Stocker), "(true);");
        wIndexerFacetFor.tl(6, langueConfig.getString(I18n.var_listeRecherche), "2.q(\"*:*\");");
        wIndexerFacetFor.tl(6, langueConfig.getString(I18n.var_listeRecherche), "2.setC(", entiteAttribuerNomSimple, ".class);");
        wIndexerFacetFor.tl(6, langueConfig.getString(I18n.var_listeRecherche), "2.fq(\"", i18nGlobale.getString(I18n.var_solrId), ":\" + ", i18nGlobale.getString(I18n.var_solrId), "2);");
        wIndexerFacetFor.tl(6, langueConfig.getString(I18n.var_listeRecherche), "2.rows(1L);");
        wIndexerFacetFor.tl(6, "futures.add(Future.future(promise2 -> {");
        wIndexerFacetFor.tl(7, langueConfig.getString(I18n.var_listeRecherche), "2.", langueConfig.getString(I18n.var_promesseLoin), langueConfig.getString(I18n.var_ListeRecherche), "(", langueConfig.getString(I18n.var_requeteSite), ").onSuccess(b -> {");
        wIndexerFacetFor.tl(8, entiteAttribuerNomSimple, " o2 = ", langueConfig.getString(I18n.var_listeRecherche), "2.getList().stream().findFirst().orElse(null);");
        wIndexerFacetFor.tl(8, "if(o2 != null) {");
        wIndexerFacetFor.tl(9, "JsonObject params = new JsonObject();");
        wIndexerFacetFor.tl(9, "params.put(\"body\", new JsonObject());");
        wIndexerFacetFor.tl(9, "params.put(\"scopes\", ", i18nGlobale.getString(I18n.var_requeteSite), ".getScopes());");
        wIndexerFacetFor.tl(9, "params.put(\"cookie\", new JsonObject());");
        wIndexerFacetFor.tl(9, "params.put(\"path\", new JsonObject());");
        wIndexerFacetFor.tl(9, "params.put(\"query\", new JsonObject().put(\"q\", \"*:*\").put(\"fq\", new JsonArray().add(\"", i18nGlobale.getString(I18n.var_solrId), ":\" + ", i18nGlobale.getString(I18n.var_solrId), "2)).put(\"var\", new JsonArray().add(\"refresh:false\")));");
        wIndexerFacetFor.tl(9, "JsonObject context = new JsonObject().put(\"params\", params).put(\"user\", ", langueConfig.getString(I18n.var_requeteSite), ".getUserPrincipal());");
        wIndexerFacetFor.tl(9, "JsonObject json = new JsonObject().put(\"context\", context);");
        wIndexerFacetFor.tl(9, "eventBus.request(\"", siteNom, "-", langueNom, "-", entiteAttribuerNomSimple, "\", json, new DeliveryOptions().addHeader(\"action\", \"patch", entiteAttribuerNomSimple, "Future\")).onSuccess(c -> {");
        wIndexerFacetFor.tl(10, "JsonObject responseMessage = (JsonObject)c.body();");
        wIndexerFacetFor.tl(10, "Integer statusCode = responseMessage.getInteger(\"statusCode\");");
        wIndexerFacetFor.tl(10, "if(statusCode.equals(200))");
        wIndexerFacetFor.tl(11, "promise2.complete();");
        wIndexerFacetFor.tl(10, "else");
        wIndexerFacetFor.tl(11, "promise2.fail(new RuntimeException(responseMessage.getString(\"statusMessage\")));");
        wIndexerFacetFor.tl(9, "}).onFailure(ex -> {");
        wIndexerFacetFor.tl(10, "promise2.fail(ex);");
        wIndexerFacetFor.tl(9, "});");
        wIndexerFacetFor.tl(8, "}");
        wIndexerFacetFor.tl(7, "}).onFailure(ex -> {");
        wIndexerFacetFor.tl(8, "promise2.fail(ex);");
        wIndexerFacetFor.tl(7, "});");
        wIndexerFacetFor.tl(6, "}));");
        wIndexerFacetFor.tl(5, "}");
        classesNomSimpleFacetFor.add(entiteAttribuerNomSimple);
      }
  
      /////////////////
      // codeObtenir //
      /////////////////
      o = wObtenir;
      if(classeEtendBase || classeEstBase) {
        tl(3, "case \"", entiteVar, "\":");
        tl(4, "return o", classeNomSimple, ".", entiteVar, ";");
      }	
  
      ///////////////////
      // codeAttribuer //
      ///////////////////
      o = wAttribuer;
      if((classeEtendBase || classeEstBase) && entiteAttribuer) {
        tl(3, "case \"", entiteVar, "\":");
        if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
          tl(4, "o", classeNomSimple, ".add", entiteVarCapitalise, "((", entiteNomSimpleCompletGenerique, ")val);");
        } else {
          tl(4, "if(o", classeNomSimple, ".get", entiteVarCapitalise, "() == null)");
          tl(5, "o", classeNomSimple, ".set", entiteVarCapitalise, "(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));");
        }
        tl(4, "if(!", langueConfig.getString(I18n.var_sauvegardes), ".contains(\"", entiteVar, "\"))");
        tl(5, langueConfig.getString(I18n.var_sauvegardes), ".add(\"", entiteVar, "\");");
        tl(4, "return val;");
      }	

      o = wAttribuerSql;
      if((classeEtendBase || classeEstBase) && entiteAttribuer && entiteAttribuerEtendModeleBase) {
        if(!wAttribuerSql.getEmpty())
          wAttribuerSqlVars.s(", ");
        wAttribuerSqlVars.s("o.get", StringUtils.capitalize(entiteVar), "()");
        if(!wAttribuerSql.getEmpty())
          wAttribuerSql.s(" UNION ");
        if("array".equals(entiteTypeJson)) {
          if("array".equals(entiteAttribuerTypeJson)) {
            if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
              wAttribuerSql.s("SELECT ", entiteVar, "2, '", entiteVar, "' from ", classeNomSimple, entiteVar, "_", entiteAttribuerNomSimple, entiteAttribuerVar, " where ", entiteVar, "1=$" + wAttribuerSqlNum);
              wAttribuerSqlNum++;
            } else {
              wAttribuerSql.s("SELECT ", entiteAttribuerVar, ", '", entiteAttribuerVar, "' from ", entiteAttribuerNomSimple, entiteAttribuerVar, "_", classeNomSimple, entiteVar, " where ", entiteVar, "2=$" + wAttribuerSqlNum);
              wAttribuerSqlNum++;
            }
          } else {
            if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
              wAttribuerSql.s("SELECT ", entiteAttribuerVar, " as ", classeVarClePrimaire, "2, '", entiteAttribuerVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
              wAttribuerSqlNum++;
            } else {
              wAttribuerSql.s("SELECT ", entiteAttribuerVar, " as ", classeVarClePrimaire, "1, '", entiteAttribuerVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
              wAttribuerSqlNum++;
            }
          }
        }
        else {
          if("array".equals(entiteAttribuerTypeJson)) {
            if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
              wAttribuerSql.s("SELECT ", entiteAttribuerVar, " as ", classeVarClePrimaire, "2, '", entiteVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
              wAttribuerSqlNum++;
            } else {
              wAttribuerSql.s("SELECT ", entiteAttribuerVar, " as ", classeVarClePrimaire, "1, '", entiteVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
              wAttribuerSqlNum++;
            }
          } else {
            if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
              wAttribuerSql.s("SELECT ", entiteAttribuerVar, " as ", classeVarClePrimaire, "2, '", entiteVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
              wAttribuerSqlNum++;
            } else {
              wAttribuerSql.s("SELECT ", entiteAttribuerVar, " as ", classeVarClePrimaire, "1, '", entiteVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
              wAttribuerSqlNum++;
            }
          }
        }
      }	
  
      /////////////
      // definir //
      /////////////

      o = wDefinir;
      if(classeIndexe && (BooleanUtils.isTrue(entiteDefinir) || BooleanUtils.isTrue(entiteAttribuer) && !"array".equals(entiteTypeJson))) {
          tl(3, wDefinirObjet.getEmpty() ? "if" : "} else if", "(\"", entiteVar.toLowerCase(), "\".equals(varLower)) {");
          if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
            tl(4, "if(val != null) {");
            tl(5, "add", entiteVarCapitalise, "(val);");
            tl(4, "}");
            tl(4, "if(!", langueConfig.getString(I18n.var_sauvegardes), ".contains(\"", entiteVar, "\")) {");
            tl(5, "", langueConfig.getString(I18n.var_sauvegardes), ".add(\"", entiteVar, "\");");
            tl(4, "}");
          }
          else {
            tl(4, "if(val != null) {");
            tl(5, "set", entiteVarCapitalise, "(val);");
            tl(4, "}");
            tl(4, langueConfig.getString(I18n.var_sauvegardes), ".add(\"", entiteVar, "\");");
          }
          tl(4, "return val;");
      }	

      o = wDefinirObjet;
      if(classeIndexe && (BooleanUtils.isTrue(entiteDefinir) || BooleanUtils.isTrue(entiteClePrimaire)) || BooleanUtils.isTrue(entiteAttribuer) && !"array".equals(entiteTypeJson)) {
//					tl(3, "case \"", entiteVar.toLowerCase(), "\":");
          tl(3, wDefinirObjet.getEmpty() ? "if" : "} else if", "(\"", entiteVar.toLowerCase(), "\".equals(varLower)) {");
          if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
            tl(4, "if(val instanceof ", entiteNomSimple, "<?>) {");
            tl(5, "((", entiteNomSimpleComplet, ")val).stream().forEach(v -> add", entiteVarCapitalise, "(v));");
            tl(4, "} else if(val instanceof ", entiteNomSimpleGenerique, "[]) {");
            tl(5, "Arrays.asList((", entiteNomSimpleGenerique, "[])val).stream().forEach(v -> add", entiteVarCapitalise, "((", entiteNomSimpleGenerique, ")v));");
            if(VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)
                || VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)
                || VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)
                || VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)
                || VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)
                ) {
              tl(4, "} else if(val instanceof Number[]) {");
              tl(5, "Arrays.asList((Number[])val).stream().forEach(v -> add", entiteVarCapitalise, "(((Number)v).longValue()));");
            }
            if(VAL_nomCanoniquePolygon.equals(entiteNomCanoniqueGenerique)) {
              tl(4, "} else if(val instanceof JsonObject) {");
              tl(5, "Optional.ofNullable(staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, val.toString())).ifPresent(u -> u.stream().forEach(v -> add", entiteVarCapitalise, "(v)));");
              tl(4, "} else if(val instanceof String) {");
              tl(5, "Optional.ofNullable(staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, (String)val)).ifPresent(u -> u.stream().forEach(v -> add", entiteVarCapitalise, "(v)));");
            } else {
              tl(4, "} else if(val instanceof JsonArray) {");
              tl(5, "((JsonArray)val).stream().forEach(v -> add", entiteVarCapitalise, "(staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, v.toString())));");
            }
            tl(4, "}");
            tl(4, "if(!", langueConfig.getString(I18n.var_sauvegardes), ".contains(\"", entiteVar, "\")) {");
            tl(5, "", langueConfig.getString(I18n.var_sauvegardes), ".add(\"", entiteVar, "\");");
            tl(4, "}");
          }
          else {
            if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniquePoint)) {
              tl(4, "if(val instanceof String) {");
              tl(5, "set", entiteVarCapitalise, "((String)val);");
              tl(4, "} else if(val instanceof JsonObject) {");
              tl(5, "set", entiteVarCapitalise, "((JsonObject)val);");
              tl(4, "} else if(val instanceof Point) {");
              tl(5, "set", entiteVarCapitalise, "((Point)val);");
              tl(4, "} else if(val instanceof ", entiteNomSimple, ") {");
              tl(5, "set", entiteVarCapitalise, "((", entiteNomSimple, ")val);");
            } else if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
              tl(4, "if(val instanceof String) {");
              tl(5, "set", entiteVarCapitalise, "((String)val);");
              tl(4, "} else if(val instanceof JsonObject) {");
              tl(5, "set", entiteVarCapitalise, "((JsonObject)val);");
              tl(4, "} else if(val instanceof ", entiteNomSimple, ") {");
              tl(5, "set", entiteVarCapitalise, "((", entiteNomSimple, ")val);");
            } else if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueVertxJsonArray)) {
              tl(4, "if(val instanceof String) {");
              tl(5, "set", entiteVarCapitalise, "((String)val);");
              tl(4, "} else if(val instanceof JsonArray) {");
              tl(5, "set", entiteVarCapitalise, "((JsonArray)val);");
              tl(4, "} else if(val instanceof ", entiteNomSimple, ") {");
              tl(5, "set", entiteVarCapitalise, "((", entiteNomSimple, ")val);");
            } else if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())) {
              tl(4, "if(val instanceof String) {");
              tl(5, "set", entiteVarCapitalise, "((String)val);");
              tl(4, "} else if(val instanceof Number) {");
              tl(5, "set", entiteVarCapitalise, "(new BigDecimal(((Number)val).doubleValue()));");
              tl(4, "} else if(val instanceof ", entiteNomSimple, ") {");
              tl(5, "set", entiteVarCapitalise, "((", entiteNomSimple, ")val);");
            } else if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
              tl(4, "if(val instanceof String) {");
              tl(5, "set", entiteVarCapitalise, "((String)val);");
              tl(4, "} else if(val instanceof OffsetDateTime) {");
              tl(5, "set", entiteVarCapitalise, "(((OffsetDateTime)val).atZoneSameInstant(ZoneId.of(", langueConfig.getString(I18n.var_requeteSite), "_.get", langueConfig.getString(I18n.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), "))));");
              tl(4, "} else if(val instanceof ", entiteNomSimple, ") {");
              tl(5, "set", entiteVarCapitalise, "((", entiteNomSimple, ")val);");
            } else {
              tl(4, "if(val instanceof ", entiteNomSimpleComplet, ") {");
              tl(5, "set", entiteVarCapitalise, "((", entiteNomSimpleComplet, ")val);");
              if(!StringUtils.equals(entiteNomCanonique, String.class.getCanonicalName())) {
                tl(4, "} else {");
                tl(5, "set", entiteVarCapitalise, "(val == null ? null : val.toString());");
              }
            }
            tl(4, "}");
            tl(4, langueConfig.getString(I18n.var_sauvegardes), ".add(\"", entiteVar, "\");");
          }
          tl(4, "return val;");
      }	
  
      /////////////
      // codePut //
      /////////////
      o = wPut;
      if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
  //		if((classeEtendBase || classeEstBase) && BooleanUtils.isTrue(entiteDefinir)) {
  //							if(champ.contientSetterString) {
  //							if(entiteContientSetterString) {
          tl(3, "case \"", entiteVar, "\":");
          if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
            tl(4, "add", entiteVarCapitalise, "(requeteJson.get", entiteNomSimpleVertxJson, "(var));");
          }
          else {
            tl(4, "set", entiteVarCapitalise, "(requeteJson.get", entiteNomSimpleVertxJson, "(var));");
          }
          tl(4, "return true;");
  //							}
      }	
  
      /////////////////
      // codePeupler //
      /////////////////
      o = wPeupler;
      if(classeIndexe) {
  //							String nomChamp = entiteVar.toString();
  //							String varCrypte = entiteVarCrypte.toString();
  //							String varStocke = entiteVarStocke.toString();
  //							String varSuggere = entiteVarSuggere.toString();
  //							String varIncremente = entiteVarIncremente.toString();
  //							String varCleUnique = entiteVarCleUniqueActuel.toString();
        if(entiteCrypte || entiteStocke || entiteDocValues || entiteCleUnique || entiteSuggere || entiteIncremente) {
          tl(0);
  
          if(entiteSuggere) {
            tl(3, "if(", langueConfig.getString(I18n.var_sauvegardes), ".contains(\"", entiteVar, "\")) {");
            tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "_suggested", "\");");
            tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
            tl(3, "}");
          }
//					else if(entiteIncremente) {
//						tl(3, "if(", langueConfig.getString(ConfigCles.var_sauvegardes), ".contains(\"", entiteVar, "\")) {");
//						tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "_incremented", "\");");
//						tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//						tl(3, "}");
//					}
//					else if(entiteCleUnique) {
          else if(entiteCleUnique) {
            tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "\");");
            tl(3, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
          }
          else if(entiteClePrimaire) {
            tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\");");
            tl(3, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
          }
          else if(entiteCrypte) {
            tl(3, "if(", langueConfig.getString(I18n.var_sauvegardes), ".contains(\"", entiteVar, "\")) {");
            if(siteCrypte)
              tl(4, entiteSolrNomSimple, " ", entiteVar, " = requeteSite.decrypterStr((", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "_encrypted", entiteSuffixeType, "\"));");
            else
              tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "_encrypted", entiteSuffixeType, "\");");
            tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
            tl(3, "}");
          }
          else if(entiteAttribuer) {
            tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\");");
            tl(3, "if(", entiteVar, " != null)");
            if(StringUtils.contains(entiteSolrNomCanonique, "<"))
              tl(4, "o", classeNomSimple, ".", entiteVar, ".addAll(", entiteVar, ");");
            else
              tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
          }
          else {
            tl(3, "if(", langueConfig.getString(I18n.var_sauvegardes), ".contains(\"", entiteVar, "\")) {");
            tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\");");
            if(StringUtils.contains(entiteSolrNomCanonique, "<")) {
              if(entiteCouverture) {
                tl(4, "if(", entiteVar, " != null)");
                tl(5, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
              }
              else {
                if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
                  tl(4, "if(", entiteVar, " != null) {");
                  if(entiteEstZonedDateTime) {
                    if(classeVarZone == null)
                      tl(5, "ZoneId zoneId = Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_).map(r -> r.get", langueConfig.getString(I18n.var_Config), "()).map(config -> config.getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), ")).map(z -> ZoneId.of(z)).orElse(ZoneId.of(\"UTC\"));");
                    else
                      tl(5, "ZoneId zoneId = Optional.ofNullable(", classeVarZone, ").map(v -> ZoneId.of(v)).orElse(Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_).map(r -> r.get", langueConfig.getString(I18n.var_Config), "()).map(config -> config.getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), ")).map(z -> ZoneId.of(z)).orElse(ZoneId.of(\"UTC\")));");
                  }
                  tl(5, entiteVar, ".stream().forEach( v -> {");
                  if(entiteSolrNomSimple.endsWith("<String>")) {
                    tl(6, "o", classeNomSimple, ".", entiteVar, ".add(", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, v", entiteEstZonedDateTime ? ", zoneId" : "", "));");
                  } else {
                    tl(6, "o", classeNomSimple, ".", entiteVar, ".add(v);");
                  }
                  tl(5, "});");
                  tl(4, "}");
                } else {
                  tl(4, "if(", entiteVar, " != null)");
                  tl(5, "o", classeNomSimple, ".", entiteVar, ".addAll(", entiteVar, ");");
                }
              }
            }
            else {
              tl(4, "if(", entiteVar, " != null)");
              tl(5, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
            }
            tl(3, "}");
          }
  
        }
      }	
  
      /////////////////
      // codeStocker //
      /////////////////
      o = wStocker;
      if(entiteCrypte || entiteStocke || entiteDocValues || entiteCleUnique || entiteSuggere || entiteIncremente || entiteTexte) {

        if(entiteSuggere) {
          tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(Optional.ofNullable(doc.get(\"", entiteVar, "_suggested\")).map(v -> v.toString()).orElse(null));");
        }
//				else if(entiteIncremente) {
//					tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "_incremented", "\");");
//					tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//				}
//				else if(entiteCleUnique) {
        else if(entiteCleUnique) {
          tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "\");");
          tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
        }
        else if(entiteCrypte) {
          if(siteCrypte)
            tl(2, entiteSolrNomSimple, " ", entiteVar, " = requeteSite.decrypterStr((", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "_encrypted", entiteSuffixeType, "\"));");
          else
            tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "_encrypted", entiteSuffixeType, "\");");
          tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
        }
        else {
          if(StringUtils.contains(entiteSolrNomCanonique, "<")) {
            if(entitePromesse || entiteCouverture) {
              tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(new ArrayList<>());");
             }
            if(entiteEstZonedDateTime) {
              if(classeVarZone == null)
                tl(2, "ZoneId zoneId = Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_).map(r -> r.get", langueConfig.getString(I18n.var_Config), "()).map(config -> config.getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), ")).map(z -> ZoneId.of(z)).orElse(ZoneId.of(\"UTC\"));");
              else
                tl(2, "ZoneId zoneId = Optional.ofNullable(", classeVarZone, ").map(v -> ZoneId.of(v)).orElse(Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_).map(r -> r.get", langueConfig.getString(I18n.var_Config), "()).map(config -> config.getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_SITE_ZONE), ")).map(z -> ZoneId.of(z)).orElse(ZoneId.of(\"UTC\")));");
            }
            if(entiteTexte) {
              if("frFR".equals(langueNom) || "esES".equals(langueNom))
                tl(2, "Optional.ofNullable((List<?>)doc.get(\"", entiteVar, "_text_", langueNom, "\")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {");
              else
                tl(2, "Optional.ofNullable((List<?>)doc.get(\"", entiteVar, "_text_enUS\")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {");
              tl(3, "o", classeNomSimple, ".add", entiteVarCapitalise, "(", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), ", v.toString()", entiteEstZonedDateTime ? ", zoneId" : "", "));");
              tl(2, "});");
            }
            else if(VAL_nomCanoniquePolygon.equals(entiteNomCanoniqueGenerique)) {
              //STUFFNOW
              tl(2, "Optional.ofNullable((String)doc.get(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\")).ifPresent(val -> staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), "_, val.toString()).stream().forEach(v -> add", entiteVarCapitalise, "(v)));");
            }
            else {
              tl(2, "Optional.ofNullable((List<?>)doc.get(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {");
              tl(3, "o", classeNomSimple, ".add", entiteVarCapitalise, "(", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(I18n.var_requeteSite), ", v.toString()", entiteEstZonedDateTime ? ", zoneId" : "", "));");
              tl(2, "});");
            }
          }
          else {
            tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(Optional.ofNullable(doc.get(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\")).map(v -> v.toString()).orElse(null));");
          }
        }

      }
  
      //////////
      // Vars //
      //////////

      wVarsStatic.tl(1, "public static final String VAR_", entiteVar, " = \"", entiteVar, "\";");

      // varsQ //
      if(entiteTexte || entiteSuggere)
        wVarsQ.tl(2, "vars.add(VAR_", entiteVar, ");");
      // varsFq //
      else if(entiteIndexe 
          && entiteFacetsTrouves
          && !langueConfig.getString(I18n.var_sessionId).equals(entiteVar)
          && !langueConfig.getString(I18n.var_utilisateurCle).equals(entiteVar)
          && !langueConfig.getString(I18n.var_sauvegardes).equals(entiteVar)
          ) {
        wVarsFq.tl(2, "vars.add(VAR_", entiteVar, ");");
        if(!entiteAttribuer && (
            entiteNomSimple.endsWith("DateTime")
            ||entiteNomSimple.equals("Date")
            ||entiteNomSimple.equals("Timestamp")
            ||entiteNomSimple.equals("BigDecimal")
            ||entiteNomSimple.equals("Point")
            ||entiteNomSimple.equals("JsonObject")
            ||entiteNomSimple.equals("JsonArray")
            ||entiteNomSimple.equals("Integer")
            ||entiteNomSimple.equals("Double")
            ||entiteNomSimple.equals("Long")
          )) {
          wVarsGamme.tl(2, "vars.add(VAR_", entiteVar, ");");
        }
      }

      ////////////////////////
      // nomAffichageStatic //
      ////////////////////////

      wNomAffichageStatic.tl(1, "public static final String ", langueConfig.getString(I18n.var_NOM_AFFICHAGE), "_", entiteVar, " = \"", entiteNomAffichage, "\";");

      if(entiteDescription != null) {
        wDescriptionMethode.tl(2, "case VAR_", entiteVar, ":");
        wDescriptionMethode.tl(3, "return \"", StringEscapeUtils.escapeJava(entiteDescription), "\";");
      }
      if(entiteNgsiType != null) {
        wNgsiTypeMethode.tl(2, "case VAR_", entiteVar, ":");
        wNgsiTypeMethode.tl(3, "return \"", StringEscapeUtils.escapeJava(entiteNgsiType), "\";");

        wNgsiMethode.tl(2, "case VAR_", entiteVar, ":");
        if("Point".equals(entiteNomSimple)) {
          wNgsiMethode.tl(3, "return new JsonObject().put(\"type\", \"Point\").put(\"coordinates\", new JsonArray().add(Double.valueOf(o.get", entiteVarCapitalise, "().getX())).add(Double.valueOf(o.get", entiteVarCapitalise, "().getY())));");
        } else if("Path".equals(entiteNomSimple)) {
          wNgsiMethode.tl(3, "JsonArray pointsArray", entiteVarCapitalise, " = new JsonArray();");
          wNgsiMethode.tl(3, "o.get", entiteVarCapitalise, "().getPoints().stream().map(point -> new JsonArray().add(Double.valueOf(point.getX())).add(Double.valueOf(point.getY()))).collect(Collectors.toList()).forEach(pointArray -> pointsArray", entiteVarCapitalise, ".add(pointArray));");
          wNgsiMethode.tl(3, "return new JsonObject().put(\"type\", \"LineString\").put(\"coordinates\", pointsArray", entiteVarCapitalise, ");");
        } else if("Polygon".equals(entiteNomSimple)) {
          wNgsiMethode.tl(3, "return o.geojson", entiteVarCapitalise, "();");
        } else if("Polygon".equals(entiteNomSimpleGenerique)) {
          wNgsiMethode.tl(3, "return o.geojson", entiteVarCapitalise, "();");
        } else {
          wNgsiMethode.tl(3, "return o.get", entiteVarCapitalise, "();");
        }
      }
      if(entiteNomSimple != null) {
        wClasseNomSimpleMethode.tl(2, "case VAR_", entiteVar, ":");
        wClasseNomSimpleMethode.tl(3, "return \"", entiteNomSimple, "\";");
      }
      if(entiteHtmColonne != null) {
        wHtmColonneMethode.tl(2, "case VAR_", entiteVar, ":");
        wHtmColonneMethode.tl(3, "return ", entiteHtmColonne, ";");
      }
      if(entiteHtmLigne != null) {
        wHtmLigneMethode.tl(2, "case VAR_", entiteVar, ":");
        wHtmLigneMethode.tl(3, "return ", entiteHtmLigne, ";");
      }
      if(entiteHtmCellule != null) {
        wHtmCelluleMethode.tl(2, "case VAR_", entiteVar, ":");
        wHtmCelluleMethode.tl(3, "return ", entiteHtmCellule, ";");
      }
      if(entiteLongeurMin != null) {
        wLongeurMinMethode.tl(2, "case VAR_", entiteVar, ":");
        wLongeurMinMethode.tl(3, "return ", entiteLongeurMin, ";");
      }
      if(entiteLongeurMax != null) {
        wLongeurMaxMethode.tl(2, "case VAR_", entiteVar, ":");
        wLongeurMaxMethode.tl(3, "return ", entiteLongeurMax, ";");
      }
      if(entiteMin != null) {
        wMinMethode.tl(2, "case VAR_", entiteVar, ":");
        wMinMethode.tl(3, "return ", entiteMin, ";");
      }
      if(entiteMax != null) {
        wMaxMethode.tl(2, "case VAR_", entiteVar, ":");
        wMaxMethode.tl(3, "return ", entiteMax, ";");
      }

      wNomAffichageMethode.tl(2, "case VAR_", entiteVar, ":");
      wNomAffichageMethode.tl(3, "return ", langueConfig.getString(I18n.var_NOM_AFFICHAGE), "_", entiteVar, ";");
  
      if(entiteDefinir || entiteAttribuer || entiteIndexe || entiteStocke) {
    
        //////////////////
        // requeteApi //
        //////////////////
    
        if(entiteNomSimple.toString().equals("BigDecimal"))
          wRequeteApi.tl(3, "if(!Objects.equals(", entiteVar, ", original.get", entiteVarCapitalise, "()) && ", entiteVar, " != null && original.get", entiteVarCapitalise, "() != null && ", entiteVar, ".compareTo(original.get", entiteVarCapitalise, "()) != 0)");
        else
          wRequeteApi.tl(3, "if(!Objects.equals(", entiteVar, ", original.get", entiteVarCapitalise, "()))");
        wRequeteApi.tl(4, langueConfig.getString(I18n.var_requeteApi), ".addVars(\"", entiteVar, "\");");
    
        //////////////
        // hashCode //
        //////////////
    
        if(entiteIndice > 0) 
          wHashCode.s(", ");
        wHashCode.s(entiteVar);
    
        ////////////
        // equals //
        ////////////
    
        if(entiteIndice > 0) 
          wEquals.l().t(4, "&& ");
        wEquals.s("Objects.equals( " + entiteVar + ", that." + entiteVar + " )");
    
        //////////////
        // toString //
        //////////////
    
        wToString.tl(2, "sb.append(Optional.ofNullable(", entiteVar, ").map(v -> \"", entiteVar, ": ", ("String".equals(entiteNomSimpleComplet) ? "\\\"" : ""), "\" + v", ("String".equals(entiteNomSimpleComplet) ? " + \"\\\"\\n\" " : " + \"\\n\""), ").orElse(\"\"));");
    
        entiteIndice++;
      }
    }
  }

  /**
   */
  public void genCodeClasseFin(String langueNom, JsonObject langueConfig) throws Exception {

    //////////////////
    // codeInitLoin //
    //////////////////
    if(classeInitLoin && classePartsRequeteSite != null) {
      if(classePromesse) {
        wInitLoin.tl(4, "promise2.complete();");
        wInitLoin.tl(3, "} catch(Exception ex) {");
        wInitLoin.tl(4, "promise2.fail(ex);");
        wInitLoin.tl(3, "}");
        wInitLoin.tl(3, "return promise2.future();");
        wInitLoin.tl(2, "}).onSuccess(a -> {");
        wInitLoin.tl(3, "promise.complete();");
        wInitLoin.tl(2, "}).onFailure(ex -> {");
        wInitLoin.tl(3, "promise.fail(ex);");
        wInitLoin.tl(2, "});");
        wInitLoin.tl(2, "return promise.future();");
      }
      wInitLoin.tl(1, "}");
      if(classeInitLoin) {
        wInitLoin.l();
        wInitLoin.t(1);
        if(classeEtendBase)
          wInitLoin.s("@Override ");
        if(classePromesse) {
          wInitLoin.l("public Future<? extends ", classeNomSimpleSuper, "<DEV>> ", langueConfig.getString(I18n.var_promesseLoin), langueConfig.getString(I18n.var_PourClasse), "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_) {");
          wInitLoin.tl(2, "return ", langueConfig.getString(I18n.var_promesseLoin), classeNomSimple, "(", langueConfig.getString(I18n.var_requeteSite), "_);");
          wInitLoin.tl(1, "}");  
        } else {
          wInitLoin.s("public void ", langueConfig.getString(I18n.var_initLoin), langueConfig.getString(I18n.var_PourClasse), "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_)");
          if(classeInitLoinExceptions.size() > 0) {
            wInitLoin.s(" throws ");
            for(int i = 0; i < classeInitLoinExceptions.size(); i++) {
              String classeInitLoinException = classeInitLoinExceptions.get(i);
              String classeInitLoinExceptionNomSimple = StringUtils.substringAfterLast(classeInitLoinException, ".");
              if(i > 0)
                wInitLoin.s(", ");
              wInitLoin.s(classeInitLoinExceptionNomSimple);
            }
          }
          wInitLoin.l(" {");
          wInitLoin.tl(2, langueConfig.getString(I18n.var_initLoin), classeNomSimple, "(", langueConfig.getString(I18n.var_requeteSite), "_);");
          wInitLoin.tl(1, "}");  
        }
      }
    }

    /////////////////////
    // codeRequeteSite //
    /////////////////////
    if(classeContientRequeteSite && classeInitLoin && classePartsRequeteSite != null) {
      o = wRequeteSite;
      tl(1, "}");
      l();
      tl(1, "public void ", langueConfig.getString(I18n.var_requeteSite), langueConfig.getString(I18n.var_PourClasse), "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_) {");
      tl(2, langueConfig.getString(I18n.var_requeteSite), classeNomSimple, "(", langueConfig.getString(I18n.var_requeteSite), "_);");
      tl(1, "}");  
    }

    /////////////////
    // codeObtenir //
    /////////////////
    o = wObtenir;
    if(classeInitLoin && (classeEtendBase || classeEstBase)) {
      tl(3, "default:");

      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return super.", langueConfig.getString(I18n.var_obtenir), classeNomSimpleSuperGenerique, "(var);");

      tl(2, "}");
      tl(1, "}");
    }	

    ///////////////////
    // codeAttribuer //
    ///////////////////
    o = wAttribuer;
    if(classeInitLoin && (classeEtendBase || classeEstBase)) {
      tl(3, "default:");

      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return super.", langueConfig.getString(I18n.var_attribuer), classeNomSimpleSuperGenerique, "(var, val);");

      tl(2, "}");
      tl(1, "}");

    }	

    /////////////
    // codePut //
    /////////////
    o = wPut;
    if(classeSauvegarde) {
//		if(classeInitLoin && (classeEtendBase || classeEstBase)) {
      tl(3, "default:");

      if(classeEstBase)
        tl(4, "return false;");
      else
        tl(4, "return super.put", classeNomSimpleSuperGenerique, "(requeteJson, var);");

      tl(2, "}");
      tl(1, "}");
    }	

    wInitLoin.flushClose();
    wRequeteSite.flushClose();
    wIndexer.flushClose();
    wObtenir.flushClose();
    wAttribuer.flushClose();
    wPut.flushClose();
    wPeupler.flushClose();
    wStocker.flushClose();
    wDefinir.flushClose();
    wDefinirObjet.flushClose();
    wVarsStatic.flushClose();
    wVarsQ.flushClose();
    wVarsFq.flushClose();
    wVarsGamme.flushClose();
    wNomAffichageStatic.flushClose();
    wNomAffichageMethode.flushClose();
    wDescriptionMethode.flushClose();
    wNgsiTypeMethode.flushClose();
    wNgsiMethode.flushClose();
    wClasseNomSimpleMethode.flushClose();
    wHtmColonneMethode.flushClose();
    wHtmLigneMethode.flushClose();
    wHtmCelluleMethode.flushClose();
    wLongeurMinMethode.flushClose();
    wLongeurMaxMethode.flushClose();
    wMinMethode.flushClose();
    wMaxMethode.flushClose();
    wPageEntites.flushClose();
    wPageGet.flushClose();
    wHashCode.flushClose();
    wToString.flushClose();
    wEquals.flushClose();

    o = auteurGenClasseFin;

    if(BooleanUtils.isTrue(classeInitLoin) && classePartsRequeteSite != null) {
      s(wInitLoin.toString());
      s(wRequeteSite.toString());
      s(wObtenir.toString());
      s(wAttribuer.toString());
    }

    if(classePartsRequeteSite != null && classeInitLoin && (classeEtendBase || classeEstBase)) {
      l(); 
      tl(1, "///////////////");
      tl(1, "// staticSet //");
      tl(1, "///////////////");
      tl(0);
      t(1);
      s("public static Object staticSet", langueConfig.getString(I18n.var_PourClasse), "(String ", langueConfig.getString(I18n.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String v, ", classeNomSimple, " o)");
      l(" {");
      tl(2, "return staticSet", classeNomSimple, "(", langueConfig.getString(I18n.var_entite), "Var,  ", langueConfig.getString(I18n.var_requeteSite), "_, v, o);");
      tl(1, "}");
      t(1);
      s("public static Object staticSet", classeNomSimple, "(String ", langueConfig.getString(I18n.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String v, ", classeNomSimple, " o)");
      l(" {");
      tl(2, "switch(", langueConfig.getString(I18n.var_entite), "Var) {");
      s(wStaticSet.toString());
      tl(3, "default:");

      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSet", classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_entite), "Var,  ", langueConfig.getString(I18n.var_requeteSite), "_, v, o);");

      tl(2, "}");
      tl(1, "}");

      l(); 
      tl(1, "////////////////");
      tl(1, "// staticSearch //");
      tl(1, "////////////////");
      tl(0);
      t(1);
      s("public static Object staticSearch", langueConfig.getString(I18n.var_PourClasse), "(String ", langueConfig.getString(I18n.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, Object o)");
      l(" {");
      tl(2, "return staticSearch", classeNomSimple, "(", langueConfig.getString(I18n.var_entite), "Var,  ", langueConfig.getString(I18n.var_requeteSite), "_, o);");
      tl(1, "}");
      t(1);
      s("public static Object staticSearch", classeNomSimple, "(String ", langueConfig.getString(I18n.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, Object o)");
      l(" {");
      tl(2, "switch(", langueConfig.getString(I18n.var_entite), "Var) {");
      s(wstaticSearch.toString());
      tl(3, "default:");

      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSearch", classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_entite), "Var,  ", langueConfig.getString(I18n.var_requeteSite), "_, o);");

      tl(2, "}");
      tl(1, "}");

      l(); 
      tl(1, "///////////////////");
      tl(1, "// staticSearchStr //");
      tl(1, "///////////////////");
      tl(0);
      t(1);
      s("public static String staticSearchStr", langueConfig.getString(I18n.var_PourClasse), "(String ", langueConfig.getString(I18n.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, Object o)");
      l(" {");
      tl(2, "return staticSearchStr", classeNomSimple, "(", langueConfig.getString(I18n.var_entite), "Var,  ", langueConfig.getString(I18n.var_requeteSite), "_, o);");
      tl(1, "}");
      t(1);
      s("public static String staticSearchStr", classeNomSimple, "(String ", langueConfig.getString(I18n.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, Object o)");
      l(" {");
      tl(2, "switch(", langueConfig.getString(I18n.var_entite), "Var) {");
      s(wstaticSearchStr.toString());
      tl(3, "default:");

      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSearchStr", classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_entite), "Var,  ", langueConfig.getString(I18n.var_requeteSite), "_, o);");

      tl(2, "}");
      tl(1, "}");

      l(); 
      tl(1, "//////////////////");
      tl(1, "// staticSearchFq //");
      tl(1, "//////////////////");
      tl(0);
      t(1);
      s("public static String staticSearchFq", langueConfig.getString(I18n.var_PourClasse), "(String ", langueConfig.getString(I18n.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o)");
      l(" {");
      tl(2, "return staticSearchFq", classeNomSimple, "(", langueConfig.getString(I18n.var_entite), "Var,  ", langueConfig.getString(I18n.var_requeteSite), "_, o);");
      tl(1, "}");
      t(1);
      s("public static String staticSearchFq", classeNomSimple, "(String ", langueConfig.getString(I18n.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(I18n.var_requeteSite), "_, String o)");
      l(" {");
      tl(2, "switch(", langueConfig.getString(I18n.var_entite), "Var) {");
      s(wstaticSearchFq.toString());
      tl(3, "default:");

      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSearchFq", classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_entite), "Var,  ", langueConfig.getString(I18n.var_requeteSite), "_, o);");

      tl(2, "}");
      tl(1, "}");
    }

    if(classeApi 
        || (
            classePartsModeleBase != null && classesSuperEtMoiSansGen.contains(classePartsModeleBase.nomCanonique(langueNomGlobale))
            || classePartsResultatBase != null && classesSuperEtMoiSansGen.contains(classePartsResultatBase.nomCanonique(langueNomGlobale))
        ) 
        && classeInitLoin 
        && (classeEtendBase || classeEstBase)
        ) {
      l(); 
      tl(1, "/////////////");
      tl(1, "// ", langueConfig.getString(I18n.var_definir), " //");
      tl(1, "/////////////");
      tl(0);
      t(1);
      if(!classeEstBase)
        s("@Override ");
      s("public boolean ", langueConfig.getString(I18n.var_definir), langueConfig.getString(I18n.var_PourClasse), "(String var, Object val)");
      if(classeInitLoinExceptions.size() > 0) {
        s(" throws ");
        for(int i = 0; i < classeInitLoinExceptions.size(); i++) {
          String classeInitLoinException = classeInitLoinExceptions.get(i);
          String classeInitLoinExceptionNomSimple = StringUtils.substringAfterLast(classeInitLoinException, ".");
          if(i > 0)
            s(", ");
          s(classeInitLoinExceptionNomSimple);
        }
      }
      l(" {");
      tl(2, "String[] vars = StringUtils.split(var, \".\");");
      tl(2, "Object o = null;");
      tl(2, "if(val != null) {");
      tl(3, "for(String v : vars) {");
      tl(4, "if(o == null)");
      tl(5, "o = ", langueConfig.getString(I18n.var_definir), "", classeNomSimple, "(v, val);");
      if(activerVertx && classePartsModeleBase != null) {
        tl(4, "else if(o instanceof ", classePartsModeleBase.nomSimple(langueNom), ") {");
        tl(5, classePartsModeleBase.nomSimple(langueNom), " o", classePartsModeleBase.nomSimple(langueNom), " = (", classePartsModeleBase.nomSimple(langueNom), ")o;");
        tl(5, "o = o", classePartsModeleBase.nomSimple(langueNom), ".", langueConfig.getString(I18n.var_definir), langueConfig.getString(I18n.var_PourClasse), "(v, val);");
        tl(4, "}");
      }
      tl(3, "}");
      tl(2, "}");
      tl(2, "return o != null;");
      tl(1, "}");
      tl(1, "public Object ", langueConfig.getString(I18n.var_definir), "", classeNomSimple, "(String var, Object val) {");
//			tl(2, "switch(var.toLowerCase()) {");
      tl(2, "String varLower = var.toLowerCase();");
      s(wDefinirObjet.toString());
//			tl(3, "default:");
      if(!wDefinirObjet.getEmpty()) {
        tl(2, "} else {");
      }

      if(classeEstBase)
        tl(wDefinirObjet.getEmpty() ? 2 : 3, "return null;");
      else
        tl(wDefinirObjet.getEmpty() ? 2 : 3, "return super.", langueConfig.getString(I18n.var_definir), "", classeNomSimpleSuperGenerique, "(var, val);");

      if(!wDefinirObjet.getEmpty()) {
        tl(2, "}");
      }

      tl(1, "}");
    }

//		if(classeSauvegarde) {
//			l(); 
//			tl(1, "/////////////////");
//			tl(1, "// ", langueConfig.getString(ConfigCles.var_sauvegardes), " //");
//			tl(1, "/////////////////");
//			tl(0);
//			tl(1, "protected List<String> ", langueConfig.getString(ConfigCles.var_sauvegardes), " = new ArrayList<String>();");
//		}

    /////////////////
    // codePeupler //
    /////////////////
//		if((classeEtendBase || classePartsModeleBase != null && classePartsModeleBase.nomCanonique(langueNom).equals(classeNomCanonique)) && classeIndexe) {
    if(classeIndexe) {
      l(); 
      tl(1, "/////////////");
      tl(1, "// ", langueConfig.getString(I18n.var_peupler), " //");
      tl(1, "/////////////");
      tl(0);
      t(1);
      if(BooleanUtils.isTrue(classeEtendBase))
        s("@Override ");
      l("public void ", langueConfig.getString(I18n.var_peupler), langueConfig.getString(I18n.var_PourClasse), "(SolrResponse.Doc doc) {");
      tl(2, langueConfig.getString(I18n.var_peupler), classeNomSimple, "(doc);");
      tl(1, "}");
      tl(1, "public void ", langueConfig.getString(I18n.var_peupler), classeNomSimple, "(SolrResponse.Doc doc) {");
      tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
      tl(2, "", langueConfig.getString(I18n.var_sauvegardes), " = Optional.ofNullable((ArrayList<String>)doc.get(\"", langueConfig.getString(I18n.var_sauvegardes), "_docvalues_strings\")).orElse(new ArrayList<String>());");
      tl(2, "if(", langueConfig.getString(I18n.var_sauvegardes), " != null) {");
      s(wPeupler.toString());
      tl(2, "}");
      if(BooleanUtils.isTrue(classeEtendBase)) {
        tl(0);
        tl(2, "super.", langueConfig.getString(I18n.var_peupler), classeNomSimpleSuperGenerique, "(doc);");
      }

      tl(1, "}");
    }	

    /////////////////
    // codeIndexer //
    /////////////////
    if(classeImage) {
//			l(); 
//			tl(1, "///////////");
//			tl(1, "// image //");
//			tl(1, "///////////");
//			tl(0);
//			tl(1, "public static void image() {");
//			tl(2, "try {");
//			tl(3, "DefaultExecutor executeur = new DefaultExecutor();");
//			String classeGenPageChemin = classeDoc.getString("classeGenPageChemin" + classePageMethode  + "_stored_string");
//			String classePageChemin = classeDoc.getString("classePageChemin" + classePageMethode  + "_stored_string");
//			String classePageCheminCss = classeDoc.getString("classePageCheminCss" + classePageMethode  + "_stored_string");
//			String classePageCheminJs = classeDoc.getString("classePageCheminJs" + classePageMethode  + "_stored_string");
//			String classePageUriMethode = classeDoc.getString("classeApiUri" + classePageMethode + "_stored_string");
//			String classePageLangueNom = classeDoc.getString("classePageLangueNom" + classePageMethode + "_stored_string");
//			String classePageNomSimple = classeDoc.getString("classePageNomSimple" + classePageMethode  + "_stored_string");
//	
//			if(classeGenPageChemin != null) {
//		
//				tl(3, "{");
//				tl(4, "new File(\"", siteChemin, "-static/png", StringUtils.substringBeforeLast(classePageUriMethode, "/"), "\").mkdirs();");
//				tl(4, "executeur.execute(CommandLine.parse(\"/usr/bin/CutyCapt --min-height=200 --url=", siteUrlBase, classePageUriMethode, "?pageRecapituler=true --out=", siteChemin, "-static/png", classePageUriMethode, "-999.png\"));");
//				tl(4, "BufferedImage img = ImageIO.read(new File(\"", siteChemin, "-static/png", classePageUriMethode, "-999.png\"));");
//				tl(4, "System.out.println(\"", classePageNomSimple, "\");");
//				tl(4, "System.out.println(\" * ImageLargeur.", classePageLangueNom, ": \" + img.getWidth());");
//				tl(4, "System.out.println(\" * ImageHauteur.", classePageLangueNom, ": \" + img.getHeight());");
//				tl(3, "}");
//			}
//			tl(2, "} catch(Exception e) {");
//			tl(3, "ExceptionUtils.rethrow(e);");
//			tl(2, "}");
//			tl(1, "}");
    }
    if(classeIndexe && classePartsRequeteSite != null) {

      tl(0);
      tl(1, "public void ", langueConfig.getString(I18n.var_indexer), classeNomSimple, "(JsonObject doc) {");
      s(wIndexer.toString());
      if(classeEtendBase && !classeEstBase) {
        tl(2, "super.", langueConfig.getString(I18n.var_indexer), "", classeNomSimpleSuperGenerique, "(doc);");
        tl(0);
      }
      l("\t}");

      /////////
      // var //
      /////////
      l();
      tl(1, "public static String var", langueConfig.getString(I18n.var_Stocke), classeNomSimple, "(String ", langueConfig.getString(I18n.var_entite), "Var) {");
      tl(2, "switch(", langueConfig.getString(I18n.var_entite), "Var) {");
      s(wVarStocke);
      tl(3, "default:");
      if(classeEstBase)
//				tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", langueConfig.getString(ConfigCles.var_nest_pas_une_entite_Stocke), ". \", ", langueConfig.getString(ConfigCles.var_entite), "Var));");
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".var", langueConfig.getString(I18n.var_Stocke), classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_entite), "Var);");
      tl(2, "}");
      tl(1, "}");
      l();
      tl(1, "public static String var", langueConfig.getString(I18n.var_Indexe), classeNomSimple, "(String ", langueConfig.getString(I18n.var_entite), "Var) {");
      tl(2, "switch(", langueConfig.getString(I18n.var_entite), "Var) {");
      s(wVarIndexe);
      tl(3, "default:");
      if(classeEstBase)
//				tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", langueConfig.getString(ConfigCles.var_nest_pas_une_entite_indexe), ". \", ", langueConfig.getString(ConfigCles.var_entite), "Var));");
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".var", langueConfig.getString(I18n.var_Indexe), classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_entite), "Var);");
      tl(2, "}");
      tl(1, "}");
      l();
      tl(1, "public static String ", langueConfig.getString(I18n.var_recherche), "Var", classeNomSimple, "(String ", langueConfig.getString(I18n.var_recherche), "Var) {");
      tl(2, "switch(", langueConfig.getString(I18n.var_recherche), "Var) {");
      s(wRechercheVar);
      tl(3, "default:");
      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(I18n.var_recherche), "Var", classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_recherche), "Var);");
      tl(2, "}");
      tl(1, "}");

      l();
      tl(1, "public static String var", langueConfig.getString(I18n.var_Recherche), classeNomSimple, "(String ", langueConfig.getString(I18n.var_entite), "Var) {");
      tl(2, "switch(", langueConfig.getString(I18n.var_entite), "Var) {");
      s(wVarRecherche);
      s(wVarSuggere);
      tl(3, "default:");
      if(classeEstBase)
//				tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", langueConfig.getString(ConfigCles.var_nest_pas_une_entite_indexe), ". \", ", langueConfig.getString(ConfigCles.var_entite), "Var));");
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".var", langueConfig.getString(I18n.var_Recherche), classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_entite), "Var);");
      tl(2, "}");
      tl(1, "}");

      l();
      tl(1, "public static String var", langueConfig.getString(I18n.var_Suggere), classeNomSimple, "(String ", langueConfig.getString(I18n.var_entite), "Var) {");
      tl(2, "switch(", langueConfig.getString(I18n.var_entite), "Var) {");
      s(wVarSuggere);
      tl(3, "default:");
      if(classeEstBase)
//				tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", langueConfig.getString(ConfigCles.var_nest_pas_une_entite_indexe), ". \", ", langueConfig.getString(ConfigCles.var_entite), "Var));");
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".var", langueConfig.getString(I18n.var_Suggere), classeNomSimpleSuperGenerique, "(", langueConfig.getString(I18n.var_entite), "Var);");
      tl(2, "}");
      tl(1, "}");
    }

    /////////////////
    // codeStocker //
    /////////////////
    if(classeIndexe) {
      l(); 
      tl(1, "/////////////");
      tl(1, "// ", langueConfig.getString(I18n.var_stocker), " //");
      tl(1, "/////////////");
      tl(0);
      t(1);
      if(BooleanUtils.isTrue(classeEtendBase))
        s("@Override ");
      l("public void ", langueConfig.getString(I18n.var_stocker), langueConfig.getString(I18n.var_PourClasse), "(SolrResponse.Doc doc) {");
      if(classeIndexe) {
      tl(2, langueConfig.getString(I18n.var_stocker), classeNomSimple, "(doc);");
      }
      tl(1, "}");
      tl(1, "public void ", langueConfig.getString(I18n.var_stocker), classeNomSimple, "(SolrResponse.Doc doc) {");
      tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
      tl(2, classePartsRequeteSite.nomSimple(classeLangueNom), " ", i18nGlobale.getString(I18n.var_requeteSite), " = o", classeNomSimple, ".get", i18nGlobale.getString(I18n.var_RequeteSite), "_();");
      l();
      s(wStocker.toString());
      if(BooleanUtils.isTrue(classeEtendBase)) {
        tl(0);
        tl(2, "super.", langueConfig.getString(I18n.var_stocker), classeNomSimpleSuperGenerique, "(doc);");
      }

      tl(1, "}");
    }	

    if(classeEcrireMethodes != null) {
      for(int i = 0; i < classeEcrireMethodes.size(); i++) {
        String classeEcrireMethode = classeEcrireMethodes.get(i);
        l();
        String strCommentaire = "///" + String.join("", Collections.nCopies(classeEcrireMethode.length(), "/")) + "///";
        tl(1, strCommentaire);
        tl(1, "// ", classeEcrireMethode, " //");
        tl(1, strCommentaire);
        tl(0);
        t(1);

        if(classeSuperEcrireMethodes != null && classeSuperEcrireMethodes.contains(classeEcrireMethode)) {
          s("@Override ");
          l("public void ", classeEcrireMethode, "() {");
          tl(2, classeEcrireMethode, classeNomSimple, "();");
          tl(2, "super.", classeEcrireMethode, "();");
        }
        else {
          l("public void ", classeEcrireMethode, "() {");
          tl(2, classeEcrireMethode, classeNomSimple, "();");
        }

        tl(1, "}");
        l();
        tl(1, "public void ", classeEcrireMethode, classeNomSimple, "() {");
        s(classeEcrireEcrivains.get(i));
        tl(1, "}");
      }
    }

    if((classeModele || classeApi || classeIndexe) && classeContientRequeteSite) {
      if(classePartsRequeteSite != null) {
        //////////////////
        // requeteApi //
        //////////////////
        l(); 
        tl(1, "//////////////////");
        tl(1, "// ", langueConfig.getString(I18n.var_requeteApi), " //");
        tl(1, "//////////////////");
        l();
        tl(1, "public void ", langueConfig.getString(I18n.var_requeteApi), classeNomSimple, "() {");
        tl(2, langueConfig.getString(I18n.var_RequeteApi), " ", langueConfig.getString(I18n.var_requeteApi), " = Optional.ofNullable(", langueConfig.getString(I18n.var_requeteSite), "_).map(r -> r.get", langueConfig.getString(I18n.var_RequeteApi), "_()).orElse(null);");
        tl(2, "Object o = Optional.ofNullable(", langueConfig.getString(I18n.var_requeteApi), ").map(", langueConfig.getString(I18n.var_RequeteApi), "::getOriginal).orElse(null);");
        tl(2, "if(o != null && o instanceof ", classeNomSimple, ") {");
        tl(3, classeNomSimple, " original = (", classeNomSimple, ")o;");
        s(wRequeteApi.toString());
        if(BooleanUtils.isTrue(classeEtendBase)) {
          tl(3, "super.", langueConfig.getString(I18n.var_requeteApi), classeNomSimpleSuperGenerique, "();");
        }
        tl(2, "}");
        tl(1, "}");
      }
      else {
        LOG.error(String.format("%s %s %s %s %s. ", langueConfig.getString(I18n.var_classe), langueConfig.getString(I18n.var_RequeteSite), langueConfig.getString(I18n.var_manquante), langueConfig.getString(I18n.var_dans), cheminSrcMainJava));
      }
    }

    //////////////
    // toString //
    //////////////
    l(); 
    tl(1, "//////////////");
    tl(1, "// toString //");
    tl(1, "//////////////");
    l();
    tl(1, "@Override public String toString() {");
    tl(2, "StringBuilder sb = new StringBuilder();");
    if(BooleanUtils.isTrue(classeEtendBase)) 
      tl(2, "sb.append(super.toString());");
    s(wToString.toString());
    tl(2, "return sb.toString();");
    tl(1, "}");

    if(!classeVals.getEmpty()) {
      l();
      tl(1, "public static final String[] ", classeNomSimple, "Vals", " = new String[] { ", classeVals, " };");
    }

    l();
    tl(1, "public static final String ", langueConfig.getString(I18n.var_CLASSE_NOM_SIMPLE), " = \"", classeNomSimple, "\";");
    tl(1, "public static final String ", langueConfig.getString(I18n.var_CLASSE_NOM_CANONIQUE), " = \"", classeNomCanonique, "\";");
    tl(1, "public static final String ", langueConfig.getString(I18n.var_CLASSE_AUTH_RESSOURCE), " = \"", classeAuthRessource, "\";");
    if(classeApi) {
      tl(1, "public static final String ", i18nGlobale.getString(I18n.var_CLASSE_API_ADDRESSE), "_", classeNomSimple, " = \"", siteNom, "-", classeLangueNom, "-", classeNomSimple, "\";");
      tl(1, "public static String get", i18nGlobale.getString(I18n.var_ClasseApiAddresse), "() {");
      tl(2, "return ", i18nGlobale.getString(I18n.var_CLASSE_API_ADDRESSE), "_", classeNomSimple, ";");
      tl(1, "}");
    }
    s(wVarsStatic);

    if(classeIndexe) {
      ////////////
      // varsQ //
      ////////////
  
      l();
      tl(1, "public static List<String> varsQ", langueConfig.getString(I18n.var_PourClasse), "() {");
      tl(2, "return ", classeNomSimple, ".varsQ", classeNomSimple, "(new ArrayList<String>());");
      tl(1, "}");
      tl(1, "public static List<String> varsQ", classeNomSimple, "(List<String> vars) {");
      s(wVarsQ);
      if(!classeEstBase)
        tl(2, classeNomSimpleSuperGenerique, ".varsQ", classeNomSimpleSuperGenerique, "(vars);");
      tl(2, "return vars;");
      tl(1, "}");

      ////////////
      // varsFq //
      ////////////
  
      l();
      tl(1, "public static List<String> varsFq", langueConfig.getString(I18n.var_PourClasse), "() {");
      tl(2, "return ", classeNomSimple, ".varsFq", classeNomSimple, "(new ArrayList<String>());");
      tl(1, "}");
      tl(1, "public static List<String> varsFq", classeNomSimple, "(List<String> vars) {");
      s(wVarsFq);
      if(!classeEstBase)
        tl(2, classeNomSimpleSuperGenerique, ".varsFq", classeNomSimpleSuperGenerique, "(vars);");
      tl(2, "return vars;");
      tl(1, "}");

      ///////////////
      // varsGamme //
      ///////////////
  
      l();
      tl(1, "public static List<String> vars", langueConfig.getString(I18n.var_Gamme), langueConfig.getString(I18n.var_PourClasse), "() {");
      tl(2, "return ", classeNomSimple, ".vars", langueConfig.getString(I18n.var_Gamme), classeNomSimple, "(new ArrayList<String>());");
      tl(1, "}");
      tl(1, "public static List<String> vars", langueConfig.getString(I18n.var_Gamme), classeNomSimple, "(List<String> vars) {");
      s(wVarsGamme);
      if(!classeEstBase)
        tl(2, classeNomSimpleSuperGenerique, ".vars", langueConfig.getString(I18n.var_Gamme), classeNomSimpleSuperGenerique, "(vars);");
      tl(2, "return vars;");
      tl(1, "}");
    }

    ////////////////////////
    // nomAffichageStatic //
    ////////////////////////

    l();
    s(wNomAffichageStatic);

    //////////////////
    // idPourClasse //
    //////////////////
    if(classeVarId != null || classeEstModeleBase || classeEstResultatBase) {
      l();
      if(!classeEstBase)
        tl(1, "@Override");
      tl(1, "public String ", langueConfig.getString(I18n.var_id), langueConfig.getString(I18n.var_PourClasse), "() {");
      tl(2, "return ", classeVarId == null ? "null" : classeVarIdSuffixeSolr.endsWith("_string") ? classeVarId : ("Optional.ofNullable(" + classeVarId + ").map(o -> o.toString()).orElse(null)"), ";");
      tl(1, "}");
    }

    /////////////////////
    // titrePourClasse //
    /////////////////////
    if(classeVarTitre != null || classeEstModeleBase || classeEstResultatBase) {
      l();
      if(!classeEstBase)
        tl(1, "@Override");
      tl(1, "public String ", langueConfig.getString(I18n.var_titre), langueConfig.getString(I18n.var_PourClasse), "() {");
      tl(2, "return ", classeVarTitre == null ? "null" : classeVarTitre, ";");
      tl(1, "}");
    }

    ///////////////////
    // nomPourClasse //
    ///////////////////
    if(classeVarNom != null || classeEstModeleBase || classeEstResultatBase) {
      l();
      if(!classeEstBase)
        tl(1, "@Override");
      tl(1, "public String ", langueConfig.getString(I18n.var_nom), langueConfig.getString(I18n.var_PourClasse), "() {");
      tl(2, "return ", classeVarNom == null ? "null" : classeVarNomSuffixeSolr.endsWith("_string") ? classeVarNom : ("Optional.ofNullable(" + classeVarNom + ").map(o -> o.toString()).orElse(null)"), ";");
      tl(1, "}");
    }

    ///////////////////
    // classeNomAdjectifSingulierPourClasse //
    ///////////////////
    if(classeNomAdjectifSingulier != null || classeEstModeleBase || classeEstResultatBase) {
      l();
      if(!classeEstBase)
        tl(1, "@Override");
      tl(1, "public String ", langueConfig.getString(I18n.var_classeNomAdjectifSingulier), langueConfig.getString(I18n.var_PourClasse), "() {");
      tl(2, "return ", classeNomAdjectifSingulier == null ? "null" : String.format("%s.%s_%s", classeNomSimple, langueConfig.getString(I18n.var_NomAdjectifSingulier), langueNom), ";");
      tl(1, "}");
    }

    ///////////////////////////
    // descriptionPourClasse //
    ///////////////////////////
    if(classeVarDescription != null || classeEstModeleBase || classeEstResultatBase) {
      l();
      if(!classeEstBase)
        tl(1, "@Override");
      tl(1, "public String ", langueConfig.getString(I18n.var_description), langueConfig.getString(I18n.var_PourClasse), "() {");
      tl(2, "return ", classeVarDescription == null ? "null" : classeVarDescription, ";");
      tl(1, "}");
    }

    ///////////////////////
    // classePageEdition //
    ///////////////////////
    for(String langueNom2 : toutesLangues) {
      String classeUriPageEditionLangue = classeDoc.getString("classeUriPageEdition_" + langueNom2 + "_stored_string");
      String classeStringFormatUrlPageEditionLangue = classeUriPageEditionLangue == null ? null : classeUriPageEditionLangue.replaceAll("\\{[^\\}]+\\}", "%s");
      if(classeStringFormatUrlPageEditionLangue != null || classeEstModeleBase || classeEstResultatBase) {
        l();
        if(!classeEstBase)
          tl(1, "@Override");
        tl(1, "public String ", langueNom2, langueConfig.getString(I18n.var_StringFormatUrlPageEdition), langueConfig.getString(I18n.var_PourClasse), "() {");
        tl(2, "return ", classeStringFormatUrlPageEditionLangue == null ? "null" : "\"%s" + String.format("%s\"", classeStringFormatUrlPageEditionLangue), ";");
        tl(1, "}");
      }
    }

    /////////////////////////
    // classePageAffichage //
    /////////////////////////
    for(String langueNom2 : toutesLangues) {
      String classeUriPageAffichageLangue = classeDoc.getString("classeUriPageAffichage_" + langueNom2 + "_stored_string");
      String classeStringFormatUrlPageAffichageLangue = classeUriPageAffichageLangue == null ? null : classeUriPageAffichageLangue.replaceAll("\\{[^\\}]+\\}", "%s");
      if(classeStringFormatUrlPageAffichageLangue != null || classeEstModeleBase || classeEstResultatBase) {
        l();
        if(!classeEstBase)
          tl(1, "@Override");
        tl(1, "public String ", langueNom2, langueConfig.getString(I18n.var_StringFormatUrlPageAffichage), langueConfig.getString(I18n.var_PourClasse), "() {");
        tl(2, "return ", classeStringFormatUrlPageAffichageLangue == null ? "null" : "\"%s" + String.format("%s\"", classeStringFormatUrlPageAffichageLangue), ";");
        tl(1, "}");
      }
    }

    ///////////////////////////
    // classePageUtilisateur //
    ///////////////////////////
    for(String langueNom2 : toutesLangues) {
      String classeUriPageUtilisateurLangue = classeDoc.getString("classeUriPageUtilisateur_" + langueNom2 + "_stored_string");
      String classeStringFormatUrlPageUtilisateurLangue = classeUriPageUtilisateurLangue == null ? null : classeUriPageUtilisateurLangue.replaceAll("\\{[^\\}]+\\}", "%s");
      if(classeStringFormatUrlPageUtilisateurLangue != null || classeEstModeleBase || classeEstResultatBase) {
        l();
        if(!classeEstBase)
          tl(1, "@Override");
        tl(1, "public String ", langueNom2, langueConfig.getString(I18n.var_StringFormatUrlPageUtilisateur), langueConfig.getString(I18n.var_PourClasse), "() {");
        tl(2, "return ", classeStringFormatUrlPageUtilisateurLangue == null ? "null" : "\"%s" + String.format("%s\"", classeStringFormatUrlPageUtilisateurLangue), ";");
        tl(1, "}");
      }
    }

    //////////////////////////
    // classeTelechargement //
    //////////////////////////
    for(String langueNom2 : toutesLangues) {
      String classeUriTelechargementLangue = classeDoc.getString("classeUriTelechargement_" + langueNom2 + "_stored_string");
      String classeStringFormatUrlTelechargementLangue = classeUriTelechargementLangue == null ? null : classeUriTelechargementLangue.replaceAll("\\{[^\\}]+\\}", "%s");
      if(classeStringFormatUrlTelechargementLangue != null || classeEstModeleBase || classeEstResultatBase) {
        l();
        if(!classeEstBase)
          tl(1, "@Override");
        tl(1, "public String ", langueNom2, langueConfig.getString(I18n.var_StringFormatUrlTelechargement), langueConfig.getString(I18n.var_PourClasse), "() {");
        tl(2, "return ", classeStringFormatUrlTelechargementLangue == null ? "null" : "\"%s" + String.format("%s\"", classeStringFormatUrlTelechargementLangue), ";");
        tl(1, "}");
      }
    }

    ////////////////////////////
    // nomAffichagePourClasse //
    ////////////////////////////
    l();
    tl(1, "public static String ", langueConfig.getString(I18n.var_nomAffichage), langueConfig.getString(I18n.var_PourClasse), "(String var) {");
    tl(2, "return ", classeNomSimple, ".", langueConfig.getString(I18n.var_nomAffichage), classeNomSimple, "(var);");
    tl(1, "}");
    tl(1, "public static String ", langueConfig.getString(I18n.var_nomAffichage), classeNomSimple, "(String var) {");
    tl(2, "switch(var) {");
    s(wNomAffichageMethode.toString());
    tl(2, "default:");

    if(classeEstBase)
      tl(3, "return null;");
    else
      tl(3, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(I18n.var_nomAffichage), classeNomSimpleSuperGenerique, "(var);");
    tl(2, "}");
    tl(1, "}");

    if(classePage || classeEstModeleBase || classeEstResultatBase) {
  
      /////////////////
      // Description //
      /////////////////
      l();
      tl(1, "public static String ", langueConfig.getString(I18n.var_description), classeNomSimple, "(String var) {");
      tl(2, "if(var == null)");
      tl(3, "return null;");
      tl(2, "switch(var) {");
      s(wDescriptionMethode);
      tl(3, "default:");
      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(I18n.var_description), classeNomSimpleSuperGenerique, "(var);");
      tl(2, "}");
      tl(1, "}");
  
      /////////////////////
      // ClasseNomSimple //
      /////////////////////
      l();
      tl(1, "public static String ", langueConfig.getString(I18n.var_classeNomSimple), classeNomSimple, "(String var) {");
      tl(2, "switch(var) {");
      s(wClasseNomSimpleMethode);
      tl(3, "default:");
      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(I18n.var_classeNomSimple), classeNomSimpleSuperGenerique, "(var);");
      tl(2, "}");
      tl(1, "}");
  
      //////////////
      // NgsiType //
      //////////////
      if(classeFiware) {

        l();
        tl(1, "public static String ngsiType(String var) {");
        tl(2, "switch(var) {");
        s(wNgsiTypeMethode);
        tl(3, "default:");
        if(classeNomSimpleSuperGenerique.equals(classePartsModeleBase.nomSimple(langueNom)))
          tl(4, "return null;");
        else
          tl(4, "return ", classeNomSimpleSuperGenerique, ".", "ngsiType(var);");
        tl(2, "}");
        tl(1, "}");
        l();
        tl(1, "public static Object ngsi", classeNomSimple, "(String var, ", classeNomSimple, " o) {");
        tl(2, "switch(var) {");
        s(wNgsiMethode);
        tl(3, "default:");
        if(classeNomSimpleSuperGenerique.equals(classePartsModeleBase.nomSimple(langueNom)))
          tl(4, "return null;");
        else
          tl(4, "return ngsi", classeNomSimpleSuperGenerique, "(var, o);");
        tl(2, "}");
        tl(1, "}");
      }
  
      /////////////////
      // HtmColonne //
      /////////////////
      l();
      tl(1, "public static Integer ", langueConfig.getString(I18n.var_htmColonne), classeNomSimple, "(String var) {");
      tl(2, "switch(var) {");
      s(wHtmColonneMethode);
      tl(3, "default:");
      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(I18n.var_htmColonne), classeNomSimpleSuperGenerique, "(var);");
      tl(2, "}");
      tl(1, "}");
  
      ///////////////
      // HtmLigne //
      ///////////////
      l();
      tl(1, "public static Integer ", langueConfig.getString(I18n.var_htmLigne), classeNomSimple, "(String var) {");
      tl(2, "switch(var) {");
      s(wHtmLigneMethode);
      tl(3, "default:");
      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(I18n.var_htmLigne), classeNomSimpleSuperGenerique, "(var);");
      tl(2, "}");
      tl(1, "}");
  
      /////////////////
      // HtmCellule //
      /////////////////
      l();
      tl(1, "public static Integer ", langueConfig.getString(I18n.var_htmCellule), classeNomSimple, "(String var) {");
      tl(2, "switch(var) {");
      s(wHtmCelluleMethode);
      tl(3, "default:");
      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(I18n.var_htmCellule), classeNomSimpleSuperGenerique, "(var);");
      tl(2, "}");
      tl(1, "}");
  
      ////////////////
      // LongeurMin //
      ////////////////
      l();
      tl(1, "public static Integer ", langueConfig.getString(I18n.var_longeurMin), classeNomSimple, "(String var) {");
      tl(2, "switch(var) {");
      s(wLongeurMinMethode);
      tl(3, "default:");
      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(I18n.var_longeurMin), classeNomSimpleSuperGenerique, "(var);");
      tl(2, "}");
      tl(1, "}");
  
      ////////////////
      // LongeurMax //
      ////////////////
      l();
      tl(1, "public static Integer ", langueConfig.getString(I18n.var_longeurMax), classeNomSimple, "(String var) {");
      tl(2, "switch(var) {");
      s(wLongeurMaxMethode);
      tl(3, "default:");
      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(I18n.var_longeurMax), classeNomSimpleSuperGenerique, "(var);");
      tl(2, "}");
      tl(1, "}");
  
      /////////
      // Max //
      /////////
      l();
      tl(1, "public static Integer ", langueConfig.getString(I18n.var_max), classeNomSimple, "(String var) {");
      tl(2, "switch(var) {");
      s(wMaxMethode);
      tl(3, "default:");
      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(I18n.var_max), classeNomSimpleSuperGenerique, "(var);");
      tl(2, "}");
      tl(1, "}");
  
      /////////
      // Min //
      /////////
      l();
      tl(1, "public static Integer ", langueConfig.getString(I18n.var_min), classeNomSimple, "(String var) {");
      tl(2, "switch(var) {");
      s(wMinMethode);
      tl(3, "default:");
      if(classeEstBase)
        tl(4, "return null;");
      else
        tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(I18n.var_min), classeNomSimpleSuperGenerique, "(var);");
      tl(2, "}");
      tl(1, "}");
    }

    l("}"); 

    o = auteurGenClasseDebut; 
    if(ecrireCommentaire) {
      l("/**");
      ecrireCommentairePart(classeCommentaireLangue, 0); 
      // String hackathonMission = classeDoc.getString("hackathonMissionGen_stored_string");
      // String hackathonColumn = classeDoc.getString("hackathonColumnGen_stored_string");
      // String hackathonLabels = classeDoc.getString("hackathonLabelsGen_stored_string");
      // if(hackathonMission != null)
      // 	l(String.format(" * Map.hackathonMission: %s", hackathonMission));
      // if(hackathonColumn != null)
      // 	l(String.format(" * Map.hackathonColumn: %s", hackathonColumn));
      // if(hackathonLabels != null)
      // 	l(String.format(" * Map.hackathonLabels: %s", hackathonLabels));

      // DumperOptions options = new DumperOptions();
      // Writer writer = new StringWriter();
      // langueConfig.dump(writer, options);
      // ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
      // Object obj = yamlReader.readValue(writer.toString(), Object.class);
      // ObjectMapper jsonWriter = new ObjectMapper();
      // JsonObject langueConfigJson = new JsonObject(jsonWriter.writeValueAsString(obj));
      // // Todo
      ecrireClasseCommentaire(langueConfigGlobale, siteNom, langueNom);
      l(" **/");
    }

    auteurGenClasse.s(auteurGenClasseDebut);
    auteurGenClasse.s(auteurGenClasseFin);

//		System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classeCheminGen); 
    auteurGenClasse.flushClose();
  }  

    public String[] splitByCharacterTypeCamelCase(String str) {
        return splitByCharacterType(str, true);
    }

    public static String[] splitByCharacterType(String str, boolean camelCase) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        final char[] c = str.toCharArray();
        final List<String> list = new ArrayList<>();
        int tokenStart = 0;
        int currentType = Character.getType(c[tokenStart]);
        for (int pos = tokenStart + 1; pos < c.length; pos++) {
            final int type = Character.getType(c[pos]);
            if (type == currentType) {
                continue;
            }
            if (camelCase && type == Character.DECIMAL_DIGIT_NUMBER && (currentType == Character.LOWERCASE_LETTER || currentType == Character.UPPERCASE_LETTER)) {
              currentType = type;
                continue;
            }
            if (camelCase && type == Character.LOWERCASE_LETTER && currentType == Character.UPPERCASE_LETTER) {
                final int newTokenStart = pos - 1;
                if (newTokenStart != tokenStart) {
                    list.add(new String(c, tokenStart, newTokenStart - tokenStart));
                    tokenStart = newTokenStart;
                }
            } else {
                list.add(new String(c, tokenStart, pos - tokenStart));
                tokenStart = pos;
            }
            currentType = type;
        }
        list.add(new String(c, tokenStart, c.length - tokenStart));
        return list.toArray(new String[list.size()]);
    }

    public void ecrireClasseCommentaireChamp(String langueNom, JsonObject jsonObject, String champ1, String champ2, ToutEcrivain w, Boolean condition, Object... formatValues) {
    StringBuilder b = new StringBuilder();
    JsonObject jsonObject2 = jsonObject.getJsonObject(champ1);
    JsonObject jsonObjectLangue = jsonObject.getJsonObject(langueNom);
    if(jsonObjectLangue == null) {
      jsonObjectLangue = new JsonObject();
      jsonObject.put(langueNom, jsonObjectLangue);
    }
    String champ1Langue = Optional.ofNullable(jsonObject2.getString("nom")).orElse(champ1);
    JsonObject jsonObject2Langue = jsonObjectLangue.getJsonObject(champ1Langue);
    if(jsonObject2Langue == null) {
      jsonObject2Langue = new JsonObject();
      jsonObjectLangue.put(champ1Langue, jsonObject2Langue);
      jsonObject2Langue.put("nom", champ1Langue);
    }

    Arrays.asList(String.format(jsonObject2.getString(champ2), formatValues).split("\n")).stream().forEach(s -> {
      b.append(s).append("\n");
    });
    if(condition && !"01_commentaire".equals(champ1)) {
      if("suggere".equals(champ2) || "todo".equals(champ2))
        w.s("<li>", b.toString(), "</li>");
      else
        w.s(b.toString());
    }
    if(!"todo".equals(champ2))
      jsonObject2Langue.put(champ2, b.toString());
    
  }

  public void ecrireClasseCommentaire(JsonObject langueConfig, String siteNom, String langueNom) {
    ToutEcrivain wClasseTodos = ToutEcrivain.create();
    ToutEcrivain wClasseSuggere = ToutEcrivain.create();
    ToutEcrivain wClasseDescription = ToutEcrivain.create();

    JsonObject classe = langueConfig.getJsonObject("classe");
    JsonObject classeRef = classe.getJsonObject("ref");

    // Description

    {
      StringBuilder b = new StringBuilder();
      Arrays.asList(String.format(classe.getString("Description"), classeNomSimple, classeNomSimpleGen, classeNomSimpleSuperGenerique).split("\n")).stream().forEach(s -> {
        b.insert(0, s);
      });
      wClasseDescription.s(b.toString());
      classe.put("Description", b.toString());
    }

    ecrireClasseCommentaireChamp(langueNom, classeRef, "01_commentaire", "commentaire", wClasseDescription
        , true
        , classeNomSimpleGen
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "01_commentaire", "description", wClasseDescription
        , true
        , strCommentairePart(classeCommentaire, 0).trim(), classeNomSimple, classeNomSimpleGen, classeNomSimpleSuperGenerique
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "02_etend", "commentaire", wClasseDescription
        , true
        , classeNomSimpleGen
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "02_etend", "description", wClasseDescription
        , classeEtendGen
        , classeNomSimpleGen, "https://solr.apps-crc.testing", langueNom, ClientUtils.escapeQueryChars(classeNomCanonique), classeNomSimple
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "03_classeNomSimpleSuperGenerique", "commentaire", wClasseDescription
        , true
        , classeNomSimpleGen, classeNomSimpleSuperGenerique
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "03_classeNomSimpleSuperGenerique", "todo", wClasseTodos
        , classeNomSimpleSuperGenerique == null
        , classeNomSimple, classeNomSimpleGen, classeNomSimpleGen, classeNomSimpleGen, classeNomSimple, classeNomSimpleGen
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "03_classeNomSimpleSuperGenerique", "description", wClasseDescription
        , classeEtendGen
        , classeNomSimple, classeNomSimpleGen, classeNomSimpleSuperGenerique, classeNomSimpleGen, classeNomSimpleGen, classeNomSimpleSuperGenerique, classeNomSimple, classeNomSimpleGen, classeNomSimpleSuperGenerique
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "Api", "commentaire", wClasseDescription
        , true
        
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Api", "description", wClasseDescription
        , classeApi
        
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Api", "suggere", wClasseSuggere
        , classeNomSimpleSuperGenerique != null && !classeApi
        , classeNomSimple, classeNomSimpleGen, classeNomSimpleGen, classeNomSimpleGen, classeNomSimple, classeNomSimpleGen
        );

    List<String> classeApiMethodes = Optional.ofNullable(doc.getJsonArray("classeApiMethodes_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
    if(classeApiMethodes == null)
      classeApiMethodes = new ArrayList<>();
    for(Integer i = 0; i < classeApiMethodes.size(); i++) {
      String classeApiMethode = classeApiMethodes.get(i);
      ecrireClasseCommentaireChamp(langueNom, classeRef, "ApiMethode", "commentaire", wClasseDescription
          , true
          , classeApiMethode
          );
      ecrireClasseCommentaireChamp(langueNom, classeRef, "ApiMethode", "description", wClasseDescription
          , true
          , classeApiMethode, classeApiMethode
          );
    }

    ecrireClasseCommentaireChamp(langueNom, classeRef, "ApiTag", "commentaire", wClasseDescription
        , true
        , langueNom, true
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "ApiTag", "description", wClasseDescription
        , classeApiTag != null
        , classeApiTag, classeNomSimple, classeApiTag
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "ApiTag", "todo", wClasseTodos
        , classeApi && classeApiTag == null
        , langueNom, classeNomSimple, langueNom, classeNomSimple
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "ApiUri", "commentaire", wClasseDescription
        , true
        , langueNom, classeApiUri
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "ApiUri", "description", wClasseDescription
        , classeApiUri != null
        , classeApiUri, classeNomSimple, classeApiUri
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "ApiUri", "todo", wClasseTodos
        , classeApi && classeApiUri == null
        , langueNom, classeNomSimple
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "Couleur", "commentaire", wClasseDescription
        , true
        , classeCouleur
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Couleur", "description", wClasseDescription
        , classeCouleur != null
        , classeCouleur, classeNomSimple, classeCouleur, classeCouleur
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "Indexe", "commentaire", wClasseDescription
        , true
        
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Indexe", "description", wClasseDescription
        , classeIndexe
        
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Indexe", "todo", wClasseTodos
        , classeApi && !classeIndexe
        , classeNomSimple
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "InheritDoc", "commentaire", wClasseDescription
        , true
        
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "InheritDoc", "description", wClasseDescription
        , true
        , classeNomSimple, classeNomSimpleGen
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "InheritDoc", "suggere", wClasseSuggere
        , Optional.ofNullable(classeCommentaire).map(commentaire -> !commentaire.contains("{@inheritDoc}")).orElse(false)
        , classeNomSimpleGen, classeNomSimple
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "Lignes", "commentaire", wClasseDescription
        , true
        , classeLignes
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Lignes", "description", wClasseDescription
        , classeLignes != null
        , classeLignes, classeNomSimple, classeLignes
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Lignes", "suggere", wClasseSuggere
        , classeApi && classeLignes == null
        , classeNomSimple
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "Ordre", "commentaire", wClasseDescription
        , classeOrdre != null
        , classeOrdre
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Ordre", "description", wClasseDescription
        , classeOrdre != null
        , classeOrdre, classeOrdre
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Ordre", "suggere", wClasseSuggere
        , classeApi && classeOrdre == null
        
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "OrdreSql", "commentaire", wClasseDescription
        , classeOrdreSql != null
        , classeOrdreSql
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "OrdreSql", "description", wClasseDescription
        , classeOrdreSql != null
        , classeOrdreSql, classeOrdreSql
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "OrdreSql", "suggere", wClasseSuggere
        , classeModele && classeOrdreSql == null
        
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "Modele", "commentaire", wClasseDescription
        , true
        
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Modele", "description", wClasseDescription
        , classeModele
        
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Modele", "suggere", wClasseSuggere
        , classeApi && !classeModele
        , classeNomSimple, classeNomSimpleGen, classeNomSimpleGen, classeNomSimpleGen, classeNomSimple, classeNomSimpleGen
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "Page", "commentaire", wClasseDescription
        , true
        
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Page", "description", wClasseDescription
        , classePage
        , classePageNomCanonique
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "PageSuper", "commentaire", wClasseDescription
        , true
        , langueNom, classePageSuperNomSimple
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "PageSuper", "description", wClasseDescription
        , classePageSuperNomSimple != null
        , classePageSuperNomSimple, classePageSuperNomSimple, classePageNomCanonique, classePageSuperNomCanonique
        );

    ecrireClasseCommentaireChamp(langueNom, classeRef, "Promesse", "commentaire", wClasseDescription
        , true
        
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "Promesse", "description", wClasseDescription
        , classePromesse
        , classePromesse, classeNomSimple
        );

    for(Integer i = 0; i < classeRoles.size(); i++) {
      String classeRole = classeRoles.get(i);
      String classeRoleLangue = classeRolesLangue.get(i);

      if(langueNom.equals(classeRoleLangue)) {
        ecrireClasseCommentaireChamp(langueNom, classeRef, "Role", "commentaire", wClasseDescription
            , true
            , classeRoleLangue, classeRole
            );
        ecrireClasseCommentaireChamp(langueNom, classeRef, "Role", "description", wClasseDescription
            , true
            , classeRoleLangue, classeRole, classeRole, classeNomSimple, classeNomSimple, classeNomSimple, classeRole
            );
      }
    }

    ecrireClasseCommentaireChamp(langueNom, classeRef, "UnNom", "commentaire", wClasseDescription
        , true
        , langueNom, classeUnNom
        );
    ecrireClasseCommentaireChamp(langueNom, classeRef, "UnNom", "description", wClasseDescription
        , classeUnNom != null
        , langueNom, classeUnNom, classeNomSimple, classeUnNom
        );

    // Todos

    if(!wClasseTodos.getEmpty()) {
      StringBuilder b = new StringBuilder();
      Arrays.asList(String.format(classe.getString("Todo")).split("\n")).stream().forEach(s -> {
        b.insert(0, s);
      });

      if(o != null) {
        tl(0, " * <ol>");
        s(b.toString());
        tl(0, " * </ol>");
      }

      classe.put("Todo", b.toString());
    }

    // Suggere

    if(!wClasseSuggere.getEmpty()) {
      StringBuilder b = new StringBuilder();
      Arrays.asList(String.format(classe.getString("Suggere")).split("\n")).stream().forEach(s -> {
        b.insert(0, s);
      });
      if(o != null) {
        tl(0, " * <ol>");
        s(b.toString());
        tl(0, " * </ol>");
      }

      classe.put("Suggere", b.toString());
    }

    wClasseDescription.tl(0, "<p>");
    wClasseDescription.tl(0, langueConfig.getString(I18n.str_Supprimer_), langueConfig.getString(I18n.str_la_classe_), classeNomSimple, langueConfig.getString(I18n.str__dans_Solr), ": ");
    wClasseDescription.tl(0, "<pre>");
    wClasseDescription.tl(0, "curl -k 'https://solr.apps-crc.testing/solr/", solrCollectionComputate, "/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u \"admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)\" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(classeNomCanonique), "&lt;/query&gt;&lt;/delete&gt;'");
    wClasseDescription.tl(0, "</pre>");
    wClasseDescription.tl(0, "</p>");
    wClasseDescription.tl(0, "<p>");
    wClasseDescription.tl(0, langueConfig.getString(I18n.str_Supprimer_), langueConfig.getString(I18n.str_l_ensemble_), classeNomEnsemble, langueConfig.getString(I18n.str__dans_Solr), ": ");
    wClasseDescription.tl(0, "<pre>");
    wClasseDescription.tl(0, "curl -k 'https://solr.apps-crc.testing/solr/", solrCollectionComputate, "/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u \"admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)\" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(classeNomEnsemble), "&lt;/query&gt;&lt;/delete&gt;'");
    wClasseDescription.tl(0, "</pre>");
    wClasseDescription.tl(0, "</p>");
    wClasseDescription.tl(0, "<p>");
    wClasseDescription.tl(0, langueConfig.getString(I18n.str_Supprimer_), langueConfig.getString(I18n.str_le_projet_), siteNom, langueConfig.getString(I18n.str__dans_Solr), ": ");
    wClasseDescription.tl(0, "<pre>");
    wClasseDescription.tl(0, "curl -k 'https://solr.apps-crc.testing/solr/", solrCollectionComputate, "/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u \"admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)\" --data-raw '&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:", ClientUtils.escapeQueryChars(siteNom), "&lt;/query&gt;&lt;/delete&gt;'");
    wClasseDescription.tl(0, "</pre>");
    wClasseDescription.tl(0, "</p>");
    wClasseDescription.l(langueConfig.getString(I18n.str_Genere), ": true");

    if(o != null) {
      ecrireCommentairePart(wClasseTodos.toString(), 0); 
      ecrireCommentairePart(wClasseSuggere.toString(), 0); 
      ecrireCommentairePart(wClasseDescription.toString(), 0); 
    }
  }
}
