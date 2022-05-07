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

import org.apache.commons.configuration2.YAMLConfiguration;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	protected SolrDocument classeDoc;
			
	protected SolrDocument doc;

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

	protected String uncapitalizeClasseNomSimple;

	/**
	 * Var.enUS: classSuperCanonicalName
	 */
	protected String classeNomCanoniqueSuper;

	/**
	 * Var.enUS: classApiUri
	 */
	protected String classeApiUri;

	/**
	 * Var.enUS: classComment
	 */
	protected String classeCommentaire;

	protected String classeVarClePrimaire;

	protected String classeVarInheritClePrimaire;

	/**
	 */
	protected String classeVarSauvegardes;

	/**
	 * Var.enUS: classVarId
	 */
	protected String classeVarId;

	/**
	 * Var.enUS: classVarUniqueKey
	 */
	protected String classeVarCleUnique;

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

	protected List<String> classeMotsCles;

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

	protected List<String> classeEntiteClassesSuperEtMoiSansGen;

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

	/**
	 * Var.enUS: classBaseExtendsGen
	 */
	protected Boolean classeBaseEtendGen;

	/**
	 * Var.enUS: classInitDeep
	 */
	protected Boolean classeInitLoin;

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

	/**
	 * Var.enUS: classApi
	 */
	protected Boolean classeApi;

	/**
	 * Var.enUS: classPage
	 */
	protected Boolean classePage;
	protected String classePageNomSimple;
	protected String classePageSuperNomSimple;
	protected String classeApiClasseNomSimple;
	protected String classeGenPageNomSimple;
	protected List<String> classeAttribuerNomSimplePages;
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

	/**
	 * Var.enUS: classRolesFound
	 */
	protected Boolean classeRolesTrouves;

	/**
	 * Var.enUS: classRoles
	 */
	protected List<String> classeRoles;

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
	 * Var.enUS: classApiMethods
	 */
	protected List<String> classeApiMethodes;

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

	/**
	 * Var.enUS: wAttribute
	 */
	protected ToutEcrivain wAttribuer;

	protected ToutEcrivain wAttribuerSql;

	protected ToutEcrivain wAttribuerSqlParams;

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

	protected ToutEcrivain wApiGet;

	/**
	 * Var.enUS: wApiGenerateGet
	 */
	protected ToutEcrivain wApiGenererGet;

	/**
	 * Var.enUS: wApiGeneratePost
	 */
	protected ToutEcrivain wApiAvantPost;

	protected ToutEcrivain wApiGenererPost;

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
	protected ToutEcrivain wHtmlColonneMethode;
	protected ToutEcrivain wHtmlLigneMethode;
	protected ToutEcrivain wHtmlCelluleMethode;
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
	protected String entiteVar;
	protected String entiteVarUrl;

	/**
	 * Var.enUS: entityTypeSuffix
	 */
	protected String entiteSuffixeType;

	/**
	 * Var.enUS: entityVarCapitalized
	 */
	protected String entiteVarCapitalise;

	/**
	 * Var.enUS: entityAttribute
	 */
	Boolean entiteAttribuer;

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
	 * Var.enUS: entityAttributeVarUrlId
	 */
	String entiteAttribuerVarUrlId;

	/**
	 * Var.enUS: entityAttributeVarUrlPk
	 */
	String entiteAttribuerVarUrlPk;

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
	protected ToutEcrivain auteurPageGenClasse = null;
	protected ToutEcrivain auteurPageClasse = null;
	protected ToutEcrivain auteurPageCss = null;
	protected ToutEcrivain auteurPageJs = null;
	protected ToutEcrivain auteurPageHbs = null;
	protected ToutEcrivain auteurGenPageHbs = null;
	protected ToutEcrivain auteurGenPageHbsEntite = null;
	protected ToutEcrivain auteurWebsocket = null;

	/**
	 * Var.enUS: entityIndex
	 */
	protected Integer entiteIndice;

	/**
	 * Var.enUS: contextAName
	 */
	protected String contexteUnNom;

	/**
	 * Var.enUS: contextThis
	 */
	protected String contexteCe;

	/**
	 * Var.enUS: contextThisName
	 */
	protected String contexteCeNom;

	/**
	 * Var.enUS: contextA
	 */
	protected String contexteUn;

	/**
	 * Var.enUS: contextTheName
	 */
	protected String contexteLeNom;

	/**
	 * Var.enUS: contextNameSingular
	 */
	protected String contexteNomSingulier;

	/**
	 * Var.enUS: contextNamePlural
	 */
	protected String contexteNomPluriel;

	/**
	 * Var.enUS: contextActualName
	 */
	protected String contexteNomActuel;

	/**
	 * Var.enUS: contextAll
	 */
	protected String contexteTous;

	/**
	 * Var.enUS: contextAllName
	 */
	protected String contexteTousNom;

	/**
	 * Var.enUS: contextSearchAllNameBy
	 */
	protected String contexteRechercherTousNomPar;

	/**
	 * Var.enUS: contextSearchAllName
	 */
	protected String contexteRechercherTousNom;

	/**
	 * Var.enUS: contextH1
	 */
	protected String contexteH1;

	/**
	 * Var.enUS: contextH2
	 */
	protected String contexteH2;

	/**
	 * Var.enUS: contextH3
	 */
	protected String contexteH3;

	/**
	 * Var.enUS: contextTitle
	 */
	protected String contexteTitre;

	/**
	 * Var.enUS: contextTheNames
	 */
	protected String contexteLesNoms;

	/**
	 * Var.enUS: contextNoNameFound
	 */
	protected String contexteAucunNomTrouve;

	/**
	 * Var.enUS: contextNameVar
	 */
	protected String contexteNomVar;

	/**
	 * Var.enUS: contextOfName
	 */
	protected String contexteDeNom;

	protected String classeVarTitre;

	protected String classeVarSuggere;

	protected String classeVarTexte;

	protected String classeVarUrlId;

	protected String classeVarUrlPk;

	protected String classeVarH1;

	protected String classeVarH2;

	protected String classeVarH3;

	/**
	 * Var.enUS: contextAdjective
	 */
	protected String contexteAdjectif;

	/**
	 * Var.enUS: contextAdjectivePlural
	 */
	protected String contexteAdjectifPluriel;

	/**
	 * Var.enUS: contextAdjectiveVar
	 */
	protected String contexteAdjectifVar;

	/**
	 * Var.enUS: contextANameAdjective
	 */
	protected String contexteUnNomAdjectif;

	/**
	 * Var.enUS: contextNameAdjectiveSingular
	 */
	protected String contexteNomAdjectifSingulier;

	/**
	 * Var.enUS: contextNameAdjectivePlural
	 */
	protected String contexteNomAdjectifPluriel;

	/**
	 * Var.enUS: contextColor
	 */
	protected String contexteCouleur;

	/**
	 * Var.enUS: contextIconGroup
	 */
	protected String contexteIconeGroupe;

	/**
	 * Var.enUS: contextIconName
	 */
	protected String contexteIconeNom;

	/**
	 * Var.enUS: contextRows
	 */
	protected Integer contexteRows;

	protected String contexteUri;

	/**
	 * Var.enUS: contextDescription
	 */
	protected String contexteDescription;

	/**
	 * Var.enUS: contextImageWidth
	 */
	protected Integer contexteImageLargeur;

	/**
	 * Var.enUS: contextImageHeight
	 */
	protected Integer contexteImageHauteur;

	/**
	 * Var.enUS: contextVideoId
	 */
	protected String contexteVideoId;

	/**
	 * Var.enUS: classContext
	 */
	protected Boolean classeContexte;

	Boolean entiteHighlighting;

	/**
	 * Var.enUS: entityHtml
	 */
	Boolean entiteHtml;

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

	String entiteAttribuerContexteIconeGroupe;

	String entiteAttribuerContexteIconeNom;

	List<String> classeTrisVar;

	List<String> classeTrisOrdre;

	List<String> classeTrisSuffixeType;

	List<String> entiteAttribuerTrisVar;

	List<String> entiteAttribuerTrisSuffixeType;

	/**
	 * Var.enUS: entityMultiline
	 */
	Boolean entiteMultiligne;

	/**
	 * Var.enUS: entitySignature
	 */
	Boolean entiteSignature;

	/**
	 * Var.enUS: entityDisplayName
	 */
	String entiteNomAffichage;

	/**
	 * Var.enUS: entityDescription
	 */
	String entiteDescription;

	Integer entiteHtmlLigne;
	Integer entiteHtmlCellule;
	Integer entiteLongeurMin;
	Integer entiteLongeurMax;
	Integer entiteMin;
	Integer entiteMax;

	Boolean entiteIndexe;

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

	/**
	 * Var.enUS: searchRowSearch
	 */
	Integer rechercheLigneRecherche;

	/**
	 * Var.enUS: searchRowActualSearch
	 */
	Integer rechercheLigneActuelRecherche;

	/**
	 * Var.enUS: searchRowPOST
	 */
	Integer rechercheLignePOST;

	Integer rechercheLignePUTImport;

	Integer rechercheLignePUTCopie;

	Integer rechercheLignePUTFusion;

	/**
	 * Var.enUS: searchRowActualPOST
	 */
	Integer rechercheLigneActuelPOST;

	Integer rechercheLigneActuelPUTImport;

	Integer rechercheLigneActuelPUTCopie;

	Integer rechercheLigneActuelPUTFusion;

	/**
	 * Var.enUS: searchRowPATCH
	 */
	Integer rechercheLignePATCH;

	/**
	 * Var.enUS: searchRowActualPATCH
	 */
	Integer rechercheLigneActuelPATCH;

	/**
	 * Var.enUS: searchRowPage
	 */
	Integer rechercheLignePage;

	/**
	 * Var.enUS: searchRowActualPage
	 */
	Integer rechercheLigneActuelPage;

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

	String entiteHtmlTooltip;

	Integer entiteHtmlColonne;

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

	String classePageCheminCss;

	String classeGenPageChemin;

	String classePageCheminHbs;

	String classeGenPageCheminHbs;

	String classePageUriMethode;

	String classePageLangueNom;

	String classePageCheminJs;

	String uncapitalizeClasseApiClasseNomSimple;

	String classePageNomCanonique;

	YAMLConfiguration classePageLangueConfig;

	ToutEcrivain wRecherche;
	ToutEcrivain wVarsFqJs;
	ToutEcrivain wPOST;
	ToutEcrivain wPUTImport;
	ToutEcrivain wPUTFusion;
	ToutEcrivain wPUTCopie;
	ToutEcrivain wPATCH;
	ToutEcrivain wSuggere;
	ToutEcrivain wGetters;
	ToutEcrivain wTh;
	ToutEcrivain wTd;
	ToutEcrivain wFoot;
	ToutEcrivain wFormRecherche;
	ToutEcrivain wFormPOST;
	ToutEcrivain wFormPUTImport;
	ToutEcrivain wFormPUTFusion;
	ToutEcrivain wFormPUTCopie;
	ToutEcrivain wFormPage;
	ToutEcrivain wFormPATCH;
	ToutEcrivain wJsInit;
	ToutEcrivain wWebsocket;
	ToutEcrivain wWebsocketInput;
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
		wAttribuerSqlParams = ToutEcrivain.create();
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
		wHtmlColonneMethode = ToutEcrivain.create();
		wHtmlLigneMethode = ToutEcrivain.create();
		wHtmlCelluleMethode = ToutEcrivain.create();
		wLongeurMinMethode = ToutEcrivain.create();
		wLongeurMaxMethode = ToutEcrivain.create();
		wMinMethode = ToutEcrivain.create();
		wMaxMethode = ToutEcrivain.create();

		wPageEntites = ToutEcrivain.create();
		wApiGet = ToutEcrivain.create();
		wApiGenererGet = ToutEcrivain.create();
		wApiAvantPost = ToutEcrivain.create();
		wApiGenererPost = ToutEcrivain.create();
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
	public void genCodeInitLoin(String langueNom, YAMLConfiguration langueConfig) throws Exception {
		if(BooleanUtils.isTrue(classeInitLoin) && classePartsRequeteSite != null) {
			wInitLoin.l(); 
			wInitLoin.tl(1, "//////////////");
			wInitLoin.tl(1, "// ", langueConfig.getString(ConfigCles.var_initLoin), " //");
			wInitLoin.tl(1, "//////////////");

			wInitLoin.l();
			if(classePromesse) {
				wInitLoin.tl(1, "public Future<Void> ", langueConfig.getString(ConfigCles.var_promesseLoin), classeNomSimple, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_) {");
				if(classeContientRequeteSite)
					wInitLoin.tl(2, "set", langueConfig.getString(ConfigCles.var_RequeteSite), "_(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
				wInitLoin.tl(2, "return ", langueConfig.getString(ConfigCles.var_promesseLoin), classeNomSimple, "();");
				wInitLoin.tl(1, "}");
			} else {
				wInitLoin.t(1, "public ", classeNomSimple, " ", langueConfig.getString(ConfigCles.var_initLoin), classeNomSimple, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_)");
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
					wInitLoin.tl(2, "set", langueConfig.getString(ConfigCles.var_RequeteSite), "_(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
				wInitLoin.tl(2, langueConfig.getString(ConfigCles.var_initLoin), classeNomSimple, "();");
				wInitLoin.tl(2, "return (", classeNomSimple, ")this;");
				wInitLoin.tl(1, "}");
			}

			if(classePromesse) {
				wInitLoin.l();
				wInitLoin.tl(1, "public Future<Void> ", langueConfig.getString(ConfigCles.var_promesseLoin), classeNomSimple, "() {");
				wInitLoin.tl(2, "Promise<Void> promise = Promise.promise();");
				wInitLoin.tl(2, "Promise<Void> promise2 = Promise.promise();");
				wInitLoin.tl(2, langueConfig.getString(ConfigCles.var_promesse), classeNomSimple, "(promise2);");
				wInitLoin.tl(2, "promise2.future().onSuccess(a -> {");
				if(BooleanUtils.isTrue(classeEtendBase)) {
					wInitLoin.tl(3, "super.", langueConfig.getString(ConfigCles.var_promesseLoin), classeNomSimpleSuperGenerique, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_).onSuccess(b -> {");
					wInitLoin.tl(4, "promise.complete();");
					wInitLoin.tl(3, "}).onFailure(ex -> {");
					wInitLoin.tl(4, "promise.fail(ex);");
					wInitLoin.tl(3, "});");
				} else {
					wInitLoin.tl(3, "promise.complete();");
				}
				wInitLoin.tl(2, "}).onFailure(ex -> {");
				wInitLoin.tl(3, "promise.fail(ex);");
				wInitLoin.tl(2, "});");
				wInitLoin.tl(2, "return promise.future();");
				wInitLoin.tl(1, "}");
			} else {
				wInitLoin.l();
				wInitLoin.t(1, "public void ", langueConfig.getString(ConfigCles.var_initLoin), classeNomSimple, "()");
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
				wInitLoin.tl(2, "init", classeNomSimple, "();");
				if(BooleanUtils.isTrue(classeEtendBase)) 
					wInitLoin.tl(2, "super.", langueConfig.getString(ConfigCles.var_initLoin), classeNomSimpleSuperGenerique, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
				wInitLoin.tl(1, "}");
			}

			wInitLoin.l();
			if(classePromesse) {
				wInitLoin.tl(1, "public Future<Void> ", langueConfig.getString(ConfigCles.var_promesse), classeNomSimple, "(Promise<Void> promise) {");
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
	public void genCodeRequeteSite(String langueNom, YAMLConfiguration langueConfig) throws Exception {
		if(BooleanUtils.isTrue(classeContientRequeteSite) && classePartsRequeteSite != null) {
			o = wRequeteSite;
			l(); 
			tl(1, "/////////////////");
			tl(1, "// ", langueConfig.getString(ConfigCles.var_requeteSite), " //");
			tl(1, "/////////////////");
			l(); 
			tl(1, "public void ", langueConfig.getString(ConfigCles.var_requeteSite), classeNomSimple, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_) {");
			if(BooleanUtils.isTrue(classeEtendBase)) 
				tl(3, "super.", langueConfig.getString(ConfigCles.var_requeteSite), classeNomSimpleSuperGenerique, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
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
	public void genCodeObtenir(String langueNom, YAMLConfiguration langueConfig) throws Exception {
		o = wObtenir;
		if(classeInitLoin && (classeEtendBase || classeEstBase)) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", langueConfig.getString(ConfigCles.var_obtenir), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			l("public Object ", langueConfig.getString(ConfigCles.var_obtenir), langueConfig.getString(ConfigCles.var_PourClasse), "(String var) {");
			tl(2, "String[] vars = StringUtils.split(var, \".\");");
			tl(2, "Object o = null;");
			tl(2, "for(String v : vars) {");
			tl(3, "if(o == null)");
			tl(4, "o = ", langueConfig.getString(ConfigCles.var_obtenir), classeNomSimple, "(v);");
			if(activerVertx && classePartsModeleBase != null) {
				tl(3, "else if(o instanceof ", classePartsModeleBase.nomSimple(langueNom), ") {");
				tl(4, classePartsModeleBase.nomSimple(langueNom), " ", StringUtils.uncapitalize(classePartsModeleBase.nomSimple(langueNom)), " = (", classePartsModeleBase.nomSimple(langueNom), ")o;");
				tl(4, "o = ", StringUtils.uncapitalize(classePartsModeleBase.nomSimple(langueNom)), ".", langueConfig.getString(ConfigCles.var_obtenir), langueConfig.getString(ConfigCles.var_PourClasse), "(v);");
				tl(3, "}");
			}
			tl(3, "else if(o instanceof Map) {");
			tl(4, "Map<?, ?> map = (Map<?, ?>)o;");
			tl(4, "o = map.get(v);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o;");
			tl(1, "}");
			tl(1, "public Object ", langueConfig.getString(ConfigCles.var_obtenir), classeNomSimple, "(String var) {");
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
	public void genCodeAttribuer(String langueNom, YAMLConfiguration langueConfig) throws Exception {
		o = wAttribuer;
		if(classeInitLoin && (classeEtendBase || classeEstBase)) {
			l(); 
			tl(1, "///////////////");
			tl(1, "// ", langueConfig.getString(ConfigCles.var_attribuer), " //");
			tl(1, "///////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			s("public boolean ", langueConfig.getString(ConfigCles.var_attribuer), langueConfig.getString(ConfigCles.var_PourClasse), "(String var, Object val)");
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
			tl(4, "o = ", langueConfig.getString(ConfigCles.var_attribuer), classeNomSimple + "(v, val);");
			if(activerVertx && classePartsModeleBase != null) {
				tl(3, "else if(o instanceof ", classePartsModeleBase.nomSimple(langueNom), ") {");
				tl(4, classePartsModeleBase.nomSimple(langueNom), " ", StringUtils.uncapitalize(classePartsModeleBase.nomSimple(langueNom)), " = (", classePartsModeleBase.nomSimple(langueNom), ")o;");
				tl(4, "o = ", StringUtils.uncapitalize(classePartsModeleBase.nomSimple(langueNom)), ".", langueConfig.getString(ConfigCles.var_attribuer), langueConfig.getString(ConfigCles.var_PourClasse), "(v, val);");
				tl(3, "}");
			}
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object ", langueConfig.getString(ConfigCles.var_attribuer), classeNomSimple, "(String var, Object val) {");
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
	public void genCodePut(String langueNom, YAMLConfiguration langueConfig) throws Exception {
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
			l("public void ", langueConfig.getString(ConfigCles.var_put), langueConfig.getString(ConfigCles.var_PourClasse), "(JsonObject requeteJson) {");
			tl(2, "Set<String> vars = requeteJson.fieldNames();");
			tl(2, "for(String var : vars) {");
			tl(3, "put", classeNomSimple + "(requeteJson, var);");
			tl(2, "}");
			tl(1, "}");
			l();
			t(1);
			if(!classeEstBase)
				s("@Override ");
			l("public Boolean ", langueConfig.getString(ConfigCles.var_put), classeNomSimple, "(JsonObject requeteJson, String var) {");
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
	public void genCodePeupler(String langueNom, YAMLConfiguration langueConfig) throws Exception {
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
	 * r: contexteNomAdjectifSingulier
	 * r.enUS: contextNameAdjectiveSingular
	 * r: contexteAdjectifPluriel
	 * r.enUS: contextAdjectivePlural
	 * r: contexteNomAdjectifPluriel
	 * r.enUS: contextNameAdjectivePlural
	 * r: contexteTitre
	 * r.enUS: contextTitle
	 * r: contexteCeNom
	 * r.enUS: contextThisName
	 * r: contexteCe
	 * r.enUS: contextThis
	 * r: contexteUnNom
	 * r.enUS: contextAName
	 * r: contexteUn
	 * r.enUS: contextA
	 * r: contexteLeNom
	 * r.enUS: contextTheName
	 * r: contexteLesNoms
	 * r.enUS: contextTheNames
	 * r: contexteCree
	 * r.enUS: contextCreated
	 * r: contexteModifie
	 * r.enUS: contextModified
	 * r: contexteNomSingulier
	 * r.enUS: contextNameSingular
	 * r: contexteNomPluriel
	 * r.enUS: contextNamePlural
	 * r: contexteNomActuel
	 * r.enUS: contextActualName
	 * r: contexteTousNom
	 * r.enUS: contextAllName
	 * r: contexteRechercherTousNomPar
	 * r.enUS: contextSearchAllNameBy
	 * r: contexteRechercherTousNom
	 * r.enUS: contextSearchAllName
	 * r: LesNom
	 * r.enUS: TheName
	 * r: contexteTous
	 * r.enUS: contextAll
	 * r: contexteAucunNomTrouve
	 * r.enUS: contextNoNameFound
	 * r: contexteNomVar
	 * r.enUS: contextNameVar
	 * r: contexteDeNom
	 * r.enUS: contextOfName
	 * r: contexteNom
	 * r.enUS: contextName
	 * r: contexteCouleur
	 * r.enUS: contextColor
	 * r: contexteIconeGroupe
	 * r.enUS: contextIconGroup
	 * r: contexteIconeNom
	 * r.enUS: contextIconName
	 * r: contexteRows
	 * r.enUS: contextRows
	 * r: contexteVideoId
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
	 * r: contexte
	 * r.enUS: context
	 */
	public void genCodeClasseDebut(String langueNom, YAMLConfiguration langueConfig) throws Exception {
		o = auteurGenClasse;

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
		if(ecrireCommentaire) {
			l("/**\t");
			ecrireCommentairePart(classeCommentaire, 0); 
			String hackathonMission = (String)classeDoc.get("hackathonMissionGen_stored_string");
			String hackathonColumn = (String)classeDoc.get("hackathonColumnGen_stored_string");
			String hackathonLabels = (String)classeDoc.get("hackathonLabelsGen_stored_string");
			if(hackathonMission != null)
				l(String.format(" * Map.hackathonMission: %s", hackathonMission));
			if(hackathonColumn != null)
				l(String.format(" * Map.hackathonColumn: %s", hackathonColumn));
			if(hackathonLabels != null)
				l(String.format(" * Map.hackathonLabels: %s", hackathonLabels));
			tl(0, " * <br><a href=\"", solrUrlComputate, "/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(classeNomCanonique), "\">", langueConfig.getString(ConfigCles.str_Trouver_la_classe_), classeNomSimple, langueConfig.getString(ConfigCles.str__dans_Solr), ". </a>");
			tl(0, " * <br><br>", langueConfig.getString(ConfigCles.str_Supprimer_), langueConfig.getString(ConfigCles.str_la_classe_), classeNomSimple, langueConfig.getString(ConfigCles.str__dans_Solr), ". ");
			tl(0, " * <br><pre>curl '", solrUrlComputate, "/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(classeNomCanonique), "&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'</pre>");
			tl(0, " * <br>", langueConfig.getString(ConfigCles.str_Supprimer_), langueConfig.getString(ConfigCles.str_l_ensemble_), classeNomEnsemble, langueConfig.getString(ConfigCles.str__dans_Solr), ". ");
			tl(0, " * <br><pre>curl '", solrUrlComputate, "/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(classeNomEnsemble), "&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'</pre>");
			tl(0, " * <br>", langueConfig.getString(ConfigCles.str_Supprimer_), langueConfig.getString(ConfigCles.str_le_projet_), siteNom, langueConfig.getString(ConfigCles.str__dans_Solr), ". ");
			tl(0, " * <br><pre>curl '", solrUrlComputate, "/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:", ClientUtils.escapeQueryChars(siteNom), "&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'</pre>");
			tl(0, " * <br>");
			l(" **/");  
		}
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
		List<String> classeValsVar = (List<String>)doc.get("classeValsVar_stored_strings");
		List<String> classeValsLangue = (List<String>)doc.get("classeValsLangue_stored_strings");
		List<String> classeValsValeur = (List<String>)doc.get("classeValsValeur_stored_strings");
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
	
				contexteVideoId = (String)classeDoc.get("contexteVideoId" + "_" + langueNom2 + "_stored_string");
				contexteUri = (String)classeDoc.get("contexteUri" + "_" + langueNom2 + "_stored_string");
				contexteDescription = (String)classeDoc.get("contexteDescription" + "_" + langueNom2 + "_stored_string");
				contexteUnNom = (String)classeDoc.get("contexteUnNom" + "_" + langueNom2 + "_stored_string");
				contexteNomSingulier = (String)classeDoc.get("contexteNomSingulier" + "_" + langueNom2 + "_stored_string");
				contexteNomPluriel = (String)classeDoc.get("contexteNomPluriel" + "_" + langueNom2 + "_stored_string");
				contexteNomVar = (String)classeDoc.get("contexteNomVar" + "_" + langueNom2 + "_stored_string");
				contexteAdjectif = (String)classeDoc.get("contexteAdjectif" + "_" + langueNom2 + "_stored_string");
				contexteAdjectifPluriel = (String)classeDoc.get("contexteAdjectifPluriel" + "_" + langueNom2 + "_stored_string");
				contexteAdjectifVar = (String)classeDoc.get("contexteAdjectifVar" + "_" + langueNom2 + "_stored_string");
				contexteNomAdjectifSingulier = (String)classeDoc.get("contexteNomAdjectifSingulier" + "_" + langueNom2 + "_stored_string");
				contexteNomAdjectifPluriel = (String)classeDoc.get("contexteNomAdjectifPluriel" + "_" + langueNom2 + "_stored_string");
				contexteCe = (String)classeDoc.get("contexteCe" + "_" + langueNom2 + "_stored_string");
				contexteUn = (String)classeDoc.get("contexteUn" + "_" + langueNom2 + "_stored_string");
				contexteNomActuel = (String)classeDoc.get("contexteNomActuel" + "_" + langueNom2 + "_stored_string");
				contexteTousNom = (String)classeDoc.get("contexteTousNom" + "_" + langueNom2 + "_stored_string");
				contexteRechercherTousNomPar = (String)classeDoc.get("contexteRechercherTousNomPar" + "_" + langueNom2 + "_stored_string");
				contexteRechercherTousNom = (String)classeDoc.get("contexteRechercherTousNom" + "_" + langueNom2 + "_stored_string");
				contexteLesNoms = (String)classeDoc.get("contexteLesNoms" + "_" + langueNom2 + "_stored_string");
				contexteTitre = (String)classeDoc.get("contexteTitre" + "_" + langueNom2 + "_stored_string");
				contexteH1 = (String)classeDoc.get("contexteH1" + "_" + langueNom2 + "_stored_string");
				contexteH2 = (String)classeDoc.get("contexteH2" + "_" + langueNom2 + "_stored_string");
				contexteH3 = (String)classeDoc.get("contexteH3" + "_" + langueNom2 + "_stored_string");
				contexteAucunNomTrouve = (String)classeDoc.get("contexteAucunNomTrouve" + "_" + langueNom2 + "_stored_string");
				contexteUnNomAdjectif = (String)classeDoc.get("contexteUnNomAdjectif" + "_" + langueNom2 + "_stored_string");
				contexteCeNom = (String)classeDoc.get("contexteCeNom" + "_" + langueNom2 + "_stored_string");
				contexteLeNom = (String)classeDoc.get("contexteLeNom" + "_" + langueNom2 + "_stored_string");
				contexteDeNom = (String)classeDoc.get("contexteDeNom" + "_" + langueNom2 + "_stored_string");

				if(contexteUri != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_Uri), "_", langueNom2, " = ", q(contexteUri), ";");

				if(contexteDescription != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_Description), "_", langueNom2, " = ", q(contexteDescription), ";");

				if(contexteUnNom != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_UnNom), "_", langueNom2, " = ", q(contexteUnNom), ";");
				
				if(contexteCe != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_Ce), "_", langueNom2, " = ", q(contexteCe), ";");
				
				if(contexteCeNom != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_CeNom), "_", langueNom2, " = ", q(contexteCeNom), ";");
				
				if(contexteUn != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_Un), "_", langueNom2, " = ", q(contexteUn), ";");
				
				if(contexteLeNom != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_LeNom), "_", langueNom2, " = ", q(contexteLeNom), ";");
				
				if(contexteNomSingulier != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_NomSingulier), "_", langueNom2, " = ", q(contexteNomSingulier), ";");
				
				if(contexteNomPluriel != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_NomPluriel), "_", langueNom2, " = ", q(contexteNomPluriel), ";");
				
				if(contexteNomActuel != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_NomActuel), "_", langueNom2, " = ", q(contexteNomActuel), ";");
				
				if(contexteTous != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_Tous), "_", langueNom2, " = ", q(contexteTous), ";");
				
				if(contexteTousNom != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_TousNom), "_", langueNom2, " = ", q(contexteTousNom), ";");
				
				if(contexteRechercherTousNomPar != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_RechercherTousNomPar), "_", langueNom2, " = ", q(contexteRechercherTousNomPar), ";");
				
				if(contexteRechercherTousNom != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_RechercherTousNom), "_", langueNom2, " = ", q(contexteRechercherTousNom), ";");
				
				if(contexteH1 != null)
					tl(1, "public static final String ", classeNomSimple, "_H1", "_", langueNom2, " = ", q(contexteH1), ";");
				
				if(contexteH2 != null)
					tl(1, "public static final String ", classeNomSimple, "_H2", "_", langueNom2, " = ", q(contexteH2), ";");
				
				if(contexteH3 != null)
					tl(1, "public static final String ", classeNomSimple, "_H3", "_", langueNom2, " = ", q(contexteH3), ";");
				
				if(contexteTitre != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_Titre), "_", langueNom2, " = ", q(contexteTitre), ";");
				
				if(contexteLesNoms != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_LesNoms), "_", langueNom2, " = ", q(contexteLesNoms), ";");
				
				if(contexteAucunNomTrouve != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_AucunNomTrouve), "_", langueNom2, " = ", q(contexteAucunNomTrouve), ";");
				
				if(contexteNomVar != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_NomVar), "_", langueNom2, " = ", q(contexteNomVar), ";");
				
				if(contexteDeNom != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_DeNom), "_", langueNom2, " = ", q(contexteDeNom), ";");
				
				if(contexteAdjectif != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_Adjectif), "_", langueNom2, " = ", q(contexteAdjectif), ";");
				
				if(contexteAdjectifPluriel != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_AdjectifPluriel), "_", langueNom2, " = ", q(contexteAdjectifPluriel), ";");
				
				if(contexteAdjectifVar != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_AdjectifVar), "_", langueNom2, " = ", q(contexteAdjectifVar), ";");
				
				if(contexteUnNomAdjectif != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_UnNomAdjectif), "_", langueNom2, " = ", q(contexteUnNomAdjectif), ";");
				
				if(contexteNomAdjectifSingulier != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_NomAdjectifSingulier), "_", langueNom2, " = ", q(contexteNomAdjectifSingulier), ";");
				
				if(contexteNomAdjectifPluriel != null)
					tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_NomAdjectifPluriel), "_", langueNom2, " = ", q(contexteNomAdjectifPluriel), ";");

				List<String> classeApiMethodes = Optional.ofNullable((List<String>)doc.get("classeApiMethodes_" + langueNom2 + "_stored_strings")).orElse(Arrays.asList());
				for(String classePageMethode : classeApiMethodes) {
					String classePageUriMethode = (String)classeDoc.get("classeApiUri" + classePageMethode + "_" + langueNom2 + "_stored_string");
					if(classePageUriMethode != null) {
						if(classePageUriMethode != null) {
							tl(1, "public static final String ", classePageMethode, "_", langueNom2, "_Uri", " = ", q(classePageUriMethode), ";");
						}
						if(classePageUriMethode != null) {
							tl(1, "public static final String ", classePageMethode, "_", langueNom2, "_ImageUri", " = ", q("/png", classePageUriMethode, "-999.png"), ";");
						}
					}
				}
			}

			contexteCouleur = (String)classeDoc.get("contexteCouleur_stored_string");
			contexteIconeGroupe = (String)classeDoc.get("contexteIconeGroupe_stored_string");
			contexteIconeNom = (String)classeDoc.get("contexteIconeNom_stored_string");
			contexteRows = (Integer)classeDoc.get("contexteRows_stored_int");
			
			l();
			if(contexteCouleur != null)
				tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_Couleur), " = ", q(contexteCouleur), ";");
			
			if(contexteIconeGroupe != null)
				tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_IconeGroupe), " = ", q(contexteIconeGroupe), ";");
			
			if(contexteIconeNom != null)
				tl(1, "public static final String ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_IconeNom), " = ", q(contexteIconeNom), ";");
			
			if(contexteRows != null)
				tl(1, "public static final Integer ", classeNomSimple, "_", langueConfig.getString(ConfigCles.var_Lignes), " = ", contexteRows, ";");
		}

		if(classePage) {
			classeGenPageChemin = (String)classeDoc.get("classeGenPageChemin"  + "_" + langueNom + "_stored_string");
			classePageChemin = (String)classeDoc.get("classePageChemin"  + "_" + langueNom + "_stored_string");
			classePageCheminCss = (String)classeDoc.get("classePageCheminCss"  + "_" + langueNom + "_stored_string");
			classePageCheminJs = (String)classeDoc.get("classePageCheminJs"  + "_" + langueNom + "_stored_string");
			classePageCheminHbs = (String)classeDoc.get("classePageCheminHbs"  + "_" + langueNom + "_stored_string");
			classeGenPageCheminHbs = (String)classeDoc.get("classeGenPageCheminHbs"  + "_" + langueNom + "_stored_string");
		
			File classePageFichierGen = null;
			File classePageFichier = null;
			File classePageFichierCss = null;
			File classePageFichierJs = null;
			File classePageFichierHbs = null;
			File classeGenPageFichierHbs = null;

			if(classeGenPageChemin != null)
				classePageFichierGen = new File(classeGenPageChemin);
			if(classePageChemin != null)
				classePageFichier = new File(classePageChemin);
			if(classePageCheminCss != null)
				classePageFichierCss = new File(classePageCheminCss);
			if(classePageCheminJs != null)
				classePageFichierJs = new File(classePageCheminJs);
			if(classePageCheminHbs != null)
				classePageFichierHbs = new File(classePageCheminHbs);
			if(classeGenPageCheminHbs != null)
				classeGenPageFichierHbs = new File(classeGenPageCheminHbs);

			if(classePageFichierGen != null)
				auteurPageGenClasse = ToutEcrivain.create(classePageFichierGen);
			if(classePageFichier != null && (!classePageFichier.exists() || classePageFichier.length() == 0L))
				auteurPageClasse = ToutEcrivain.create(classePageFichier);
			if(classePageFichierCss != null) {
				classePageFichierCss.getParentFile().mkdirs();
				auteurPageCss = ToutEcrivain.create(classePageFichierCss);
			}
			if(classePageFichierJs != null) {
				classePageFichierJs.getParentFile().mkdirs();
				auteurPageJs = ToutEcrivain.create(classePageFichierJs);
			}
			if(classePageFichierHbs != null && (!classePageFichierHbs.exists() || classePageFichierHbs.length() == 0L)) {
				classePageFichierHbs.getParentFile().mkdirs();
				auteurPageHbs = ToutEcrivain.create(classePageFichierHbs);
			}
			if(classeGenPageFichierHbs != null) {
				classeGenPageFichierHbs.getParentFile().mkdirs();
				auteurGenPageHbs = ToutEcrivain.create(classeGenPageFichierHbs);
				auteurGenPageHbsEntite = ToutEcrivain.create();
			}
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
	public void genCodeConstructeur(String langueNom, YAMLConfiguration langueConfig) throws Exception {
		o = auteurGenClasse;
		String constructeurCodeSource = (String)doc.get("constructeurCodeSource_" + langueNom + "_stored_string");
		String constructeurCommentaire = (String)doc.get("constructeurCommentaire_" + langueNom + "_stored_string");
		List<String> constructeurExceptionsNomSimpleComplet = (List<String>)doc.get("constructeurExceptionsNomSimpleComplet_stored_strings");
		List<String> constructeurAnnotationsNomSimpleCompletListe = (List<String>)doc.get("constructeurAnnotationsNomSimpleComplet_" + langueNom + "_stored_strings");
		List<String> constructeurAnnotationsBlocCodeListe = (List<String>)doc.get("constructeurAnnotationsBlocCode_" + langueNom + "_stored_strings");

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
		if(BooleanUtils.isTrue((Boolean)doc.get("constructeurEstPublic_stored_boolean")))
			s("public ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructeurEstProtege_stored_boolean")))
			s("protected ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructeurEstPrive_stored_boolean")))
			s("private ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructeurEstStatique_stored_boolean")))
			s("static ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructeurEstFinale_stored_boolean")))
			s("final ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructeurEstAbstrait_stored_boolean")))
			s("abstract ");
		if(BooleanUtils.isTrue((Boolean)doc.get("constructeurEstNatif_stored_boolean")))
			s("native ");

		s(classeNomSimpleGen);
		s("(");
		List<String> constructeurParamsNomSimpleComplet = (List<String>)doc.get("constructeurParamsNomSimpleComplet_" + langueNom + "_stored_strings"); 
		List<String> constructeurParamsVar = (List<String>)doc.get("constructeurParamsVar_" + langueNom + "_stored_strings");
		List<Boolean> constructeurParamsArgsVariables = (List<Boolean>)doc.get("constructeurParamsArgsVariables_stored_booleans");
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
	public void genCodeMethode(String langueNom, YAMLConfiguration langueConfig) throws Exception {
		o = auteurGenClasse;

		String methodeVar = (String)doc.get("methodeVar_" + langueNom + "_stored_string");

		ToutEcrivain methodeValsEcrivain = ToutEcrivain.create();
		List<String> methodeValsVar = (List<String>)doc.get("methodeValsVar_stored_strings");
		List<String> methodeValsLangue = (List<String>)doc.get("methodeValsLangue_stored_strings");
		List<String> methodeValsCode = (List<String>)doc.get("methodeValsCode_stored_strings");
		List<String> methodeValsValeur = (List<String>)doc.get("methodeValsValeur_stored_strings");
		if(methodeValsVar != null && methodeValsLangue != null && methodeValsValeur != null) {
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
				methodeValCode = methodeValsCode == null ? "" : methodeValsCode.get(j);
				methodeValValeur = methodeValsValeur.get(j);

				Integer xmlPart = 0;
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
//					{
//						String[] parts = splitByCharacterTypeCamelCase(methodeValVar);
//						Boolean html = false;
//						for(Integer p = 0; p < parts.length; p++) {
//							String part = StringUtils.uncapitalize(parts[p]);
//
//							Matcher regex = Pattern.compile("^(\\w+?)(\\d*)$").matcher(part);
//							boolean trouve = regex.find();
//							if(trouve) {
//								String element = StringUtils.lowerCase(regex.group(1));
//								String numeroStr = regex.group(2);
//								Integer numero = StringUtils.isEmpty(numeroStr) ? null : Integer.parseInt(numeroStr);
//								if("h".equals(element)) {
//									element += numero;
//									numero = null;
//								}
//
////									methodeValsEcrivain.t(1);
//								if(StringUtils.equalsAny(element, HTML_ELEMENTS)) {
//									html = true;
//
//									String css = methodeVar;
//									for(Integer r = 0; r <= xmlPart; r++) {
//										String s = parts[r];
//										css += s;
//									}
//									css += " ";
//
//									String cssNumero = numero == null ? "" : (StringUtils.substringBeforeLast(StringUtils.substringBeforeLast(css, numero.toString()), "0") + (numero % 2 == 0 ? " even " : " odd "));
//
//									if(numero == null)
//										numero = 1;
//
//									if(methodeXmlPile.size() < (xmlPart + 1)) {
//										if("i".equals(element))
//											methodeValsEcrivain.tl(2 + xmlPart, "<", element, "class=\", ", methodeVar, methodeValVar, methodeValVarNumero, ", \" site-menu-icon ", css, cssNumero, "\">");
//										else if("a".equals(element))
//											methodeValsEcrivain.tl(2 + xmlPart, "<", element, "class=\", \" ", css, cssNumero, "\").a(\"href\", ", methodeVar, methodeValVar, methodeValVarNumero, ").f();");
//										else if("br".equals(element))
//											methodeValsEcrivain.tl(2 + xmlPart, "e(\"", element, "\").fg();");
//										else if("td".equals(element))
//											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" w3-mobile ", css, cssNumero, "\").f();");
//										else
//											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").f();");
//
//										if(!"br".equals(element)) {
//											methodeXmlPile.push(element);
//											methodeNumeroPile.push(numero);
//											xmlPart++;
//										}
//									}
//									else if(StringUtils.equals(element, methodeXmlPile.get(xmlPart)) && numero.equals(methodeNumeroPile.get(xmlPart))) {
//										xmlPart++;
//									}
//									else {
//										while(methodeXmlPile.size() > xmlPart) {
//											methodeValsEcrivain.tl(1 + methodeXmlPile.size(), "} g(\"", methodeXmlPile.peek(), "\");");
//											methodeXmlPile.pop();
//											methodeNumeroPile.pop();
//										}
//										if("i".equals(element))
//											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", ", methodeVar, methodeValVar, methodeValVarNumero, ", \" site-menu-icon ", css, cssNumero, "\").f();");
//										else if("a".equals(element))
//											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").a(\"href\", ", methodeVar, methodeValVar, methodeValVarNumero, ").f();");
//										else if("br".equals(element))
//											methodeValsEcrivain.tl(2 + xmlPart, "e(\"", element, "\").fg();");
//										else if("td".equals(element))
//											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" w3-mobile ", css, cssNumero, "\").f();");
//										else
//											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").f();");
//
//										if(!"br".equals(element)) {
//											methodeXmlPile.push(element);
//											methodeNumeroPile.push(numero);
//											xmlPart++;
//										}
//									}
//								}
//							}
//						}
//						if(html && !"i".equals(methodeXmlPile.peek())) {
//							Integer p = methodeXmlPile.size();
//							if(StringUtils.isEmpty(methodeValCode)) {
//								methodeValsEcrivain.tl(2 + p, "sx(", methodeVar, methodeValVar, methodeValVarNumero, ");");
//							}
//							else {
//								if(classeEntiteVars.contains("utilisateurId"))
//									methodeValsEcrivain.tl(2 + p, "sx(utilisateurId == null ? ", methodeVar, methodeValVar, methodeValVarNumero, " : ", methodeValCode, ");");
//								else
//									methodeValsEcrivain.tl(2 + p, "sx(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getUtilisateurId() == null ? ", methodeVar, methodeValVar, methodeValVarNumero, " : ", methodeValCode, ");");
//							}
//						}
//					}
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

//			for(int q = methodeXmlPile.size() - 1; q >= 0; q--) {
//				methodeValsEcrivain.tl(2 + q, "} g(\"", methodeXmlPile.get(q), "\");");
//				methodeXmlPile.pop();
//			}
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
	 * r: entiteClassesSuperEtMoiSansGen
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
	 * r: entiteAttribuerVarUrlId
	 * r.enUS: entityAttributeVarUrlId
	 * r: entiteAttribuerVarUrlPk
	 * r.enUS: entityAttributeVarUrlPk
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
	 * r: entiteHtmlTooltip
	 * r.enUS: entityHtmlTooltip
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
	public void genCodeEntite(String langueNom, YAMLConfiguration langueConfig) throws Exception {
		o = auteurGenClasse;
		entiteVar = (String)doc.get("entiteVar_" + langueNom + "_stored_string");
		entiteVarUrl = (String)doc.get("entiteVarUrl_" + langueNom + "_stored_string");
		entiteDescription = (String)doc.get("entiteDescription_" + langueNom + "_stored_string");
		entiteSuffixeType = (String)doc.get("entiteSuffixeType_stored_string");
		entiteVarCapitalise = (String)doc.get("entiteVarCapitalise_" + langueNom + "_stored_string");
		entiteAttribuerVarSuggere = (String)doc.get("entiteAttribuerVarSuggere_" + langueNom + "_stored_string");
		entiteNomCanonique = (String)doc.get("entiteNomCanonique_" + langueNom + "_stored_string");
		entiteNomCanoniqueGenerique = (String)doc.get("entiteNomCanoniqueGenerique_" + langueNom + "_stored_string");
		entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
		entiteNomSimpleGenerique = (String)doc.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
		entiteNomSimpleCompletGenerique = (String)doc.get("entiteNomSimpleCompletGenerique_" + langueNom + "_stored_string");
		entiteNomSimple = (String)doc.get("entiteNomSimple_" + langueNom + "_stored_string");
		entiteCommentaire = (String)doc.get("entiteCommentaire_" + langueNom + "_stored_string");
		entiteVarParam = (String)doc.get("entiteVarParam_" + langueNom + "_stored_string");
		entiteAttribuerTypeJson = (String)doc.get("entiteAttribuerTypeJson_stored_string");
		entiteCouverture = (Boolean)doc.get("entiteCouverture_stored_boolean");
		entitePromesse = (Boolean)doc.get("entitePromesse_stored_boolean");
		entiteInitialise = (Boolean)doc.get("entiteInitialise_stored_boolean");
		entiteInitLoin = (Boolean)doc.get("entiteInitLoin_stored_boolean");
		entiteFacetsTrouves = Optional.ofNullable((Boolean)doc.get("entiteFacetsTrouves_stored_boolean")).orElse(false);
		methodeExceptionsNomSimpleComplet = (List<String>)doc.get("methodeExceptionsNomSimpleComplet_stored_strings");
		entiteFacets = Optional.ofNullable((List<String>)doc.get("entiteFacets_stored_strings")).orElse(Arrays.asList());

		if(entiteNomCanonique != null) {
			entiteSolrNomCanonique = (String)doc.get("entiteSolrNomCanonique_stored_string");
			entiteSolrNomSimple = (String)doc.get("entiteSolrNomSimple_stored_string");
			entiteNomSimpleVertxJson = (String)doc.get("entiteNomSimpleVertxJson_stored_string");
			entiteNomCanoniqueVertxJson = (String)doc.get("entiteNomCanoniqueVertxJson_stored_string");
			entiteListeNomSimpleVertxJson = (String)doc.get("entiteListeNomSimpleVertxJson_stored_string");
			entiteListeNomCanoniqueVertxJson = (String)doc.get("entiteListeNomCanoniqueVertxJson_stored_string");

			entiteExact = (Boolean)doc.get("entiteExact_stored_boolean");
			entiteClePrimaire = (Boolean)doc.get("entiteClePrimaire_stored_boolean");
			entiteCleUnique = (Boolean)doc.get("entiteCleUnique_stored_boolean");
			entiteCrypte = (Boolean)doc.get("entiteCrypte_stored_boolean");
			entiteSuggere = (Boolean)doc.get("entiteSuggere_stored_boolean");
			entiteSauvegarde = (Boolean)doc.get("entiteSauvegarde_stored_boolean");
			entiteDocValues = (Boolean)doc.get("entiteDocValues_stored_boolean");
			entiteIndexe = (Boolean)doc.get("entiteIndexe_stored_boolean");
			entiteStocke = (Boolean)doc.get("entiteStocke_stored_boolean");
			entiteTexte = (Boolean)doc.get("entiteTexte_stored_boolean");
			entiteLangue = (String)doc.get("entiteLangue_stored_string");
			entiteIncremente = (Boolean)doc.get("entiteIncremente_stored_boolean");
			entiteIgnorer = (Boolean)doc.get("entiteIgnorer_stored_boolean");
			entiteSetTrim = (Boolean)doc.get("entiteSetTrim_stored_boolean");
			entiteSetLower = (Boolean)doc.get("entiteSetLower_stored_boolean");
			entiteSetUpper = (Boolean)doc.get("entiteSetUpper_stored_boolean");
			entiteDeclarer = (Boolean)doc.get("entiteDeclarer_stored_boolean");
			entiteRechercher = (Boolean)doc.get("entiteRechercher_stored_boolean");
			entiteAttribuer = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuer_stored_boolean"));
			entiteAttribuerNomCanonique = (String)doc.get("entiteAttribuerNomCanonique_" + langueNom + "_stored_string");
			entiteAttribuerNomSimple = (String)doc.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string");
			entiteAttribuerNomCanoniqueGenApiServiceImpl = (String)doc.get("entiteAttribuerNomCanoniqueGenApiServiceImpl_" + langueNom + "_stored_string");
			entiteAttribuerNomSimpleGenApiServiceImpl = (String)doc.get("entiteAttribuerNomSimpleGenApiServiceImpl_" + langueNom + "_stored_string");
			entiteAttribuerNomSimpleApiServiceImpl = (String)doc.get("entiteAttribuerNomSimpleApiServiceImpl_" + langueNom + "_stored_string");
			entiteAttribuerVar = (String)doc.get("entiteAttribuerVar_" + langueNom + "_stored_string");
			entiteAttribuerVarUrlId = (String)doc.get("entiteAttribuerVarUrlId_" + langueNom + "_stored_string");
			entiteAttribuerVarUrlPk = (String)doc.get("entiteAttribuerVarUrlPk_" + langueNom + "_stored_string");
			entiteAttribuerVarId = (String)doc.get("entiteAttribuerVarId_" + langueNom + "_stored_string");
			entiteAttribuerVarTitre = (String)doc.get("entiteAttribuerVarTitre_" + langueNom + "_stored_string");
			entiteAttribuerVarDescription = (String)doc.get("entiteAttribuerVarDescription_" + langueNom + "_stored_string");
			entiteAttribuerVarImageUrl = (String)doc.get("entiteAttribuerVarImageUrl_" + langueNom + "_stored_string");
			entiteAttribuerUtilisateurEcrire = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuerUtilisateurEcrire_stored_boolean"));
			entiteAttribuerSessionEcrire = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuerSessionEcrire_stored_boolean"));
			entiteAttribuerPublicLire = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuerPublicLire_stored_boolean"));
			entiteAttribuerClasseRoles = (List<String>)doc.get("entiteAttribuerClasseRoles_stored_strings");
			entiteAttribuerClasseRolesLangue = (List<String>)doc.get("entiteAttribuerClasseRolesLangue_stored_strings");
			entiteAjouter = (Boolean)doc.get("entiteAjouter_stored_boolean");
			entiteSupprimer = (Boolean)doc.get("entiteSupprimer_stored_boolean");
			entiteModifier = (Boolean)doc.get("entiteModifier_stored_boolean");
			entiteRecharger = (Boolean)doc.get("entiteRecharger_stored_boolean");
			entiteMultiligne = (Boolean)doc.get("entiteMultiligne_stored_boolean");
			entiteSignature = (Boolean)doc.get("entiteSignature_stored_boolean");
			entiteImageBase64Url = (String)doc.get("entiteImageBase64Url_" + langueNom + "_stored_string");
			entiteCles = (Boolean)doc.get("entiteCles_stored_boolean");
			entiteIndexeOuStocke = (Boolean)doc.get("entiteIndexeOuStocke_stored_boolean");
			entiteDefinir = (Boolean)doc.get("entiteDefinir_stored_boolean");
			entiteContientRequeteSite = BooleanUtils.isTrue((Boolean)doc.get("entiteContientRequeteSite_stored_boolean"));
			entiteListeTypeJson = (String)doc.get("entiteListeTypeJson_stored_string");
			entiteAttribuerContexteUnNom = (String)doc.get("entiteAttribuerContexteUnNom_" + langueNom + "_stored_string");
			entiteAttribuerContexteNomPluriel = (String)doc.get("entiteAttribuerContexteNomPluriel_" + langueNom + "_stored_string");
			entiteAttribuerContexteCouleur = (String)doc.get("entiteAttribuerContexteCouleur_stored_string");
			entiteAttribuerContexteIconeGroupe = (String)doc.get("entiteAttribuerContexteIconeGroupe_stored_string");
			entiteAttribuerContexteIconeNom = (String)doc.get("entiteAttribuerContexteIconeNom_stored_string");
			entiteAttribuerPageUri = (String)doc.get("entiteAttribuerPageUri_" + langueNom + "_stored_string");
			entiteTypeJson = (String)doc.get("entiteTypeJson_stored_string");
	
			entiteNomAffichage = (String)doc.get("entiteNomAffichage_" + langueNom + "_stored_string");
			entiteHtmlTooltip = (String)doc.get("entiteHtmlTooltip_" + langueNom + "_stored_string");
			entiteHtmlColonne = (Integer)doc.get("entiteHtmlColonne_stored_int");
			entiteHtmlLigne = (Integer)doc.get("entiteHtmlLigne_stored_int");
			entiteHtmlCellule = (Integer)doc.get("entiteHtmlCellule_stored_int");
			entiteLongeurMin = (Integer)doc.get("entiteLongeurMin_stored_int");
			entiteLongeurMax = (Integer)doc.get("entiteLongeurMax_stored_int");
			entiteMin = (Integer)doc.get("entiteMin_stored_int");
			entiteMax = (Integer)doc.get("entiteMax_stored_int");
			entiteHtml = (Boolean)doc.get("entiteHtml_stored_boolean");

			entiteClassesSuperEtMoiSansGen = (List<String>)doc.get("entiteClassesSuperEtMoiSansGen_stored_strings");
	
			entiteMethodesAvantVisibilite = (List<String>)doc.get("entiteMethodesAvantVisibilite_stored_strings");
			entiteMethodesAvantVar = (List<String>)doc.get("entiteMethodesAvantVar_" + langueNom + "_stored_strings");
			entiteMethodesAvantParamVar = (List<String>)doc.get("entiteMethodesAvantParamVar_" + langueNom + "_stored_strings");
			entiteMethodesAvantParamNomSimple = (List<String>)doc.get("entiteMethodesAvantParamNomSimple_" + langueNom + "_stored_strings");
			entiteMethodesAvantNomParam = (List<Boolean>)doc.get("entiteMethodesAvantNomParam_stored_booleans");
			entiteMethodesAvantEcrire = (List<Boolean>)doc.get("entiteMethodesAvantEcrire_stored_booleans");
	
			entiteMethodesApresVisibilite = (List<String>)doc.get("entiteMethodesApresVisibilite_stored_strings");
			entiteMethodesApresVar = (List<String>)doc.get("entiteMethodesApresVar_" + langueNom + "_stored_strings");
			entiteMethodesApresParamVar = (List<String>)doc.get("entiteMethodesApresParamVar_" + langueNom + "_stored_strings");
			entiteMethodesApresParamNomSimple = (List<String>)doc.get("entiteMethodesApresParamNomSimple_" + langueNom + "_stored_strings");
			entiteMethodesApresNomParam = (List<Boolean>)doc.get("entiteMethodesApresNomParam_stored_booleans");
			entiteMethodesApresEcrire = (List<Boolean>)doc.get("entiteMethodesApresEcrire_stored_booleans");

			entiteEcrireMethodes = (List<String>)doc.get("entiteEcrireMethodes_stored_strings");
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
	
			o = auteurGenClasse;
	
			l();
			String ligneCommentaire = "\t///" + String.join("", Collections.nCopies(entiteVar.length(), "/")) + "///";
			l(ligneCommentaire);
			tl(1, "// ", entiteVar, " //");
			l(ligneCommentaire);
			l();

			ToutEcrivain entiteValsEcrivain = ToutEcrivain.create();

			entiteValsVar = (List<String>)doc.get("entiteValsVar_stored_strings");
			entiteValsLangue = (List<String>)doc.get("entiteValsLangue_stored_strings");
			entiteValsCode = (List<String>)doc.get("entiteValsCode_stored_strings");
			entiteValsValeur = (List<String>)doc.get("entiteValsValeur_stored_strings");
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
						entiteValCode = entiteValsCode == null ? "" : entiteValsCode.get(j);
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
										entiteValsEcrivain.tl(2 + p, "{{#if ", classeLangueConfig.getString(ConfigCles.var_utilisateurId), "}}", entiteVar, entiteValVar, entiteValVarNumero, "{{else}}", entiteValCode, "{{/if}}");
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
				t(1, "/**");
			s(entiteValsEcrivain);
				t(1);
					s(langueConfig.getString(ConfigCles.str_L_entité_), entiteVar);
				l();
		
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
					tl(1, " *\t", langueConfig.getString(ConfigCles.str_Il_est_construit_avant_d_être_initialisé_avec_le_constructeur_par_défaut), ". ");
				}
				tl(1, " */");
			}

			if(entiteIgnorer)
				tl(1, "@JsonIgnore");
			else if("JsonArray".equals(entiteNomSimple)) {
				tl(1, "@JsonProperty");
			}
			else if("JsonObject".equals(entiteNomSimple)) {
				tl(1, "@JsonProperty");
			}
			else if("Point".equals(entiteNomSimple)) {
				tl(1, "@JsonProperty");
				tl(1, "@JsonDeserialize(using = ", classePartsPointDeserializer.nomSimple(langueNom), ".class)");
				tl(1, "@JsonSerialize(using = ", classePartsPointSerializer.nomSimple(langueNom), ".class)");
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
			} else if("array".equals(entiteTypeJson)) {
				tl(1, "@JsonProperty");
				tl(1, "@JsonFormat(shape = JsonFormat.Shape.ARRAY)");
			} else {
				tl(1, "@JsonProperty");
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
				t(1, "/**");
				t(1);
				s("<br>", langueConfig.getString(ConfigCles.str_L_entité_), entiteVar);
				l();
		
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
					tl(1, " * ", langueConfig.getString(ConfigCles.str__est_défini_comme_null_avant_d_être_initialisé__));
				}
				else {
					tl(1, " * ", langueConfig.getString(ConfigCles.str_Il_est_construit_avant_d_être_initialisé_avec_le_constructeur_par_défaut), ". ");
				}
		
				// Lien vers Solr //
				tl(1, " * <br><a href=\"", solrUrlComputate, "/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(classeNomCanonique), "&fq=entiteVar_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(entiteVar), "\">", langueConfig.getString(ConfigCles.str_Trouver_l_entité_), entiteVar, langueConfig.getString(ConfigCles.str__dans_Solr), "</a>");
				tl(1, " * <br>");
		
				if(entiteCouverture) {
					tl(1, " * @param ", entiteVarParam, langueConfig.getString(ConfigCles.str__est_pour_envelopper_une_valeur_à_assigner_à_cette_entité_lors_de_l_initialisation__));
				}
				else {
					tl(1, " * @param ", entiteVarParam, langueConfig.getString(ConfigCles.str__est_l_entité_déjà_construit__));
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
			t(1, "protected abstract void");
			s(" _", entiteVar);
			s("(");
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
			Boolean staticSet = false;
	
			if(StringUtils.equals(entiteNomCanonique, String.class.getCanonicalName())) {
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");

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
				tl(2, "this.", entiteVar, " = ", entiteVar, ";");
				tl(1, "}");
			}
	//
	//						l();
	//						tl(1, "public ", entiteNomSimpleComplet, " ", entiteVar, "() {");
	//						tl(2, "return get", entiteVarCapitalise, "();");
	//						tl(1, "}");
	
			// Setter List //
			if((StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName())) && StringUtils.equals(entiteNomCanoniqueGenerique, Long.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "Long l = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(2, "if(l != null)");
				tl(3, "add", entiteVarCapitalise, "(l);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleGenerique, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "if(NumberUtils.isParsable(o))");
				tl(3, "return Long.parseLong(o);");
				tl(2, "return null;");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter Boolean //
			if(StringUtils.equals(entiteNomCanonique, Boolean.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "return Boolean.parseBoolean(o);");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter Integer //
			if(StringUtils.equals(entiteNomCanonique, Integer.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "if(NumberUtils.isParsable(o))");
				tl(3, "return Integer.parseInt(o);");
				tl(2, "return null;");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter Float //
			if(StringUtils.equals(entiteNomCanonique, Float.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "if(NumberUtils.isParsable(o))");
				tl(3, "return Float.parseFloat(o);");
				tl(2, "return null;");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter Double //
			if(StringUtils.equals(entiteNomCanonique, Double.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "if(NumberUtils.isParsable(o))");
				tl(3, "return Double.parseDouble(o);");
				tl(2, "return null;");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter Long //
			if(StringUtils.equals(entiteNomCanonique, Long.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "if(NumberUtils.isParsable(o))");
				tl(3, "return Long.parseLong(o);");
				tl(2, "return null;");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter Point //
			if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniquePoint)) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "if(o != null) {");
				tl(3, "String[] vals = o.split(\",\");");
				tl(3, "if(vals.length == 2 && NumberUtils.isParsable(vals[0]) && NumberUtils.isParsable(vals[1]))");
				tl(4, "return new Point(Double.parseDouble(vals[0]), Double.parseDouble(vals[1]));");
				tl(2, "}");
				tl(2, "return null;");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter JsonObject //
			if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "if(o != null) {");
				tl(4, "return new JsonObject(o);");
				tl(2, "}");
				tl(2, "return null;");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter BigDecimal //
			if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "o = StringUtils.removeAll(o, \"[^\\\\d\\\\.]\");");
				tl(2, "if(NumberUtils.isParsable(o))");
				tl(3, "return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);");
				tl(2, "return null;");
				tl(1, "}");
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Double o) {");
				tl(3, "this.", entiteVar, " = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);");
				tl(1, "}");
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Integer o) {");
				tl(3, "this.", entiteVar, " = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);");
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
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "return Timestamp.valueOf((LocalDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME)));");
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
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "return Date.from(LocalDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.of(\"Z\")).toInstant());");
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
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "try {");
				tl(3, "return o == null ? null : LocalTime.parse(o, DateTimeFormatter.ISO_TIME);");
				tl(2, "} catch(Exception e) {");
				tl(2, "}");
				tl(2, "return null;");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter LocalDate //
			if(StringUtils.equals(entiteNomCanonique, LocalDate.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Instant o) {");
				tl(2, "this.", entiteVar, " = o == null ? null : LocalDate.from(o);");
				tl(1, "}");
				if(ecrireCommentaire) {
					tl(1, "/** Example: 2011-12-03+01:00 **/");
				}
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "if(o != null) {");
				tl(3, "if(o.contains(\"T\")) {");
				tl(4, "return java.time.LocalDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toLocalDate();");
				tl(3, "} else {");
				tl(4, "return LocalDate.parse(o, DateTimeFormatter.ISO_DATE);");
				tl(3, "}");
				tl(2, "}");
				tl(2, "return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);");
				tl(1, "}");
				if(classeContientRequeteSite) {
				tl(1, "@JsonIgnore");
					tl(1, "public void set", entiteVarCapitalise, "(Date o) {");
					tl(2, "this.", entiteVar, " = o == null ? null : o.toInstant().atZone(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toLocalDate();");
					tl(1, "}");
				}
				staticSet = true;
			}
	
			// Setter ZonedDateTime //
			if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Instant o) {");
				tl(2, "this.", entiteVar, " = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);");
				tl(1, "}");
				if(ecrireCommentaire) {
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				}
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ", o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "if(StringUtils.endsWith(o, \"]\"))");
				tl(3, "return o == null ? null : ZonedDateTime.parse(o, ", classePartsZonedDateTimeSerializer.nomSimple(langueNom), ".ZONED_DATE_TIME_FORMATTER);");
				tl(2, "if(StringUtils.endsWith(o, \"Z\"))");
				tl(3, "return o == null ? null : Instant.parse(o).atZone(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).truncatedTo(ChronoUnit.MILLIS);");
				tl(2, "else");
				tl(3, "return o == null ? null : ZonedDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME).truncatedTo(ChronoUnit.MILLIS);");
				tl(1, "}");
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Date o) {");
				tl(2, "this.", entiteVar, " = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).truncatedTo(ChronoUnit.MILLIS);");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter LocalDateTime //
			if(StringUtils.equals(entiteNomCanonique, LocalDateTime.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Instant o) {");
				tl(2, "this.", entiteVar, " = o == null ? null : LocalDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);");
				tl(1, "}");
				if(ecrireCommentaire) {
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				}
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_, o);");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "return o == null ? null : LocalDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME).truncatedTo(ChronoUnit.MILLIS);");
				tl(1, "}");
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Date o) {");
				tl(2, "this.", entiteVar, " = o == null ? null : LocalDateTime.ofInstant(o.toInstant(), ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).truncatedTo(ChronoUnit.MILLIS);");
				tl(1, "}");
				staticSet = true;
			}

			if(!staticSet && !entiteNomSimpleComplet.contains("<DEV>")) {
				if((StringUtils.equalsAny(entiteNomSimple, "List", "ArrayList", "Stack"))) {
					tl(1, "public static ", entiteNomSimpleGenerique, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
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
					tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
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
			}
	
			// Ajouter //
			if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, Set.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, HashSet.class.getCanonicalName())) {
				tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(", entiteNomSimpleCompletGenerique, "...objets) {");
				tl(2, "for(", entiteNomSimpleCompletGenerique, " o : objets) {");
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
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(1, "}");
				}
		
				// Setter Boolean //
				if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, Boolean.class.getCanonicalName())) {
					tl(1, "@JsonIgnore");
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
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
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
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
					tl(1, "@JsonIgnore");
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Double o = objets.getDouble(i);");
					tl(3, "add", entiteVarCapitalise, "(new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));");
					tl(2, "}");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, "if(NumberUtils.isParsable(o)) {");
					tl(3, entiteNomSimpleCompletGenerique, " p = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);");
					tl(3, "add", entiteVarCapitalise, "(p);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter Float //
				if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, Float.class.getCanonicalName())) {
					tl(1, "@JsonIgnore");
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
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
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
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
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
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
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(1, "}");
					if(ecrireCommentaire) {
						tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					}
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = Timestamp.valueOf((LocalDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME)));");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter Date //
				if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, Date.class.getCanonicalName())) {
					tl(1, "@JsonIgnore");
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(1, "}");
					if(ecrireCommentaire) {
						tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					}
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = Date.from(LocalDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toInstant());");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter LocalDate //
				if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, LocalDate.class.getCanonicalName())) {
					tl(1, "@JsonIgnore");
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
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
					tl(2, entiteNomSimpleCompletGenerique, " p = o.toInstant().atZone(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toLocalDate();");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter ZonedDateTime //
				if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, ZonedDateTime.class.getCanonicalName())) {
					tl(1, "@JsonIgnore");
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(1, "}");
					if(ecrireCommentaire) {
						tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					}
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = ZonedDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME);");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(Date o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), ")));");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter LocalDateTime //
				if((activerVertx || activerQuarkus) && StringUtils.equals(entiteNomCanoniqueGenerique, LocalDateTime.class.getCanonicalName())) {
					tl(1, "@JsonIgnore");
					tl(1, "public void set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(1, "}");
					if(ecrireCommentaire) {
						tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					}
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = LocalDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME);");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(Date o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = LocalDateTime.ofInstant(o.toInstant(), ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), ")));");
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
				tl(1, "protected Future<", entiteNomSimpleComplet, "> ", entiteVar, langueConfig.getString(ConfigCles.var_Promesse), "() {");
				tl(2, "Promise<", entiteNomSimpleComplet, "> promise = Promise.promise();");
	
				tl(2, "Promise<", entiteNomSimpleComplet, "> promise2 = Promise.promise();");
				tl(2, "_", entiteVar, "(promise2);");
				tl(2, "promise2.future().onSuccess(o -> {");
				if(entiteInitLoin && entiteInitialise) {
					tl(3, "if(o != null && ", entiteVar, " == null) {");
					tl(4, "o.", langueConfig.getString(ConfigCles.var_promesseLoin), langueConfig.getString(ConfigCles.var_PourClasse), "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ").onSuccess(a -> {");
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
					tl(3, "_", entiteVar, "(", entiteVar, classePartsCouverture.nomSimple(langueNom), ");");
					tl(3, "set", entiteVarCapitalise, "(", entiteVar, classePartsCouverture.nomSimple(langueNom), ".o);");
					tl(2, "}");
				}
				else {
					tl(2, "_", entiteVar, "(", entiteVar, ");");
				}
				if(entiteInitLoin && entiteInitialise) {
					if(entiteCouverture) {
						tl(2, "if(", entiteVar, " != null)");
						tl(3, entiteVar, ".", langueConfig.getString(ConfigCles.var_initLoin), langueConfig.getString(ConfigCles.var_PourClasse), "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ");");
					}
					else {
						tl(2, entiteVar, ".", langueConfig.getString(ConfigCles.var_initLoin), langueConfig.getString(ConfigCles.var_PourClasse), "(", classeContientRequeteSite ? (langueConfig.getString(ConfigCles.var_requeteSite) + "_") : "null", ");");
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

			if(entiteSolrNomSimple != null) {
				l();
				if(classeContientRequeteSite && entiteNomSimple != null && entiteSolrNomCanonique != null) {
					if(entiteNomSimple.equals("Timestamp")) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : Date.from(o.toInstant());");
						tl(1, "}");
					}
					else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : Date.from(o.toInstant());");
						tl(1, "}");
					}
					else if(entiteNomCanonique.toString().equals(LocalTime.class.getCanonicalName())) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);");
						tl(1, "}");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : Date.from(o.atZone(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\")).toInstant());");
						tl(1, "}");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\")).toInstant());");
						tl(1, "}");
					}
					else if(entiteNomSimple.toString().equals("Point")) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o;");
						tl(1, "}");
					}
					else if(entiteNomSimple.toString().equals("JsonObject")) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o;");
						tl(1, "}");
					}
					else if(entiteNomSimple.toString().equals("BigDecimal")) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : o.doubleValue();");
						tl(1, "}");
					}
					else if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
						tl(1, "public static ", StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteSolrNomSimple, "<"), ">"), " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleGenerique, " o) {");
						tl(2, "return o;");
						tl(1, "}");
					}
					else if("java.util.Set".equals(entiteNomCanonique) || "java.util.HashSet".equals(entiteNomCanonique)) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return new ArrayList<>(o);");
						tl(1, "}");
					}
					else {
						tl(1, "public static ", entiteSolrNomSimple, " staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o;");
						tl(1, "}");
					}
				}
				else {
					tl(1, "public static Object staticSearch", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteNomSimpleComplet, " o) {");
					tl(2, "return null;");
					tl(1, "}");
				}
	
				l();
				if(classeContientRequeteSite && entiteNomSimple != null && entiteSolrNomCanonique != null) {
					if(entiteNomSimple.equals("Timestamp")) {
						tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
						tl(2, "return \"\\\"\" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + \"\\\"\";");
						tl(1, "}");
					}
					else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
						tl(2, "return \"\\\"\" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + \"\\\"\";");
						tl(1, "}");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
						tl(2, "return \"\\\"\" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + \"\\\"\";");
						tl(1, "}");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
		//				tl(3, "doc.put(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_DATE_TIME.format(", entiteVar, ".atStartOfDay(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
						tl(2, "return \"\\\"\" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + \"\\\"\";");
						tl(1, "}");
					}
					else if(entiteSolrNomCanonique.toString().equals("String")) {
						tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
						tl(2, "return o;");
						tl(1, "}");
					}
					else if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
						tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteSolrNomSimple, "<"), ">"), " o) {");
						tl(2, "return o == null ? null : o.toString();");
						tl(1, "}");
					}
					else {
						tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", entiteSolrNomSimple, " o) {");
						tl(2, "return o == null ? null : o.toString();");
						tl(1, "}");
					}
				}
				else {
					tl(1, "public static String staticSearchStr", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, Object o) {");
					tl(2, "return null;");
					tl(1, "}");
				}
	
				l();
				tl(1, "public static String staticSearchFq", entiteVarCapitalise, "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o) {");
				tl(2, "return ", classeNomSimple, ".staticSearchStr", entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_, o)));");
				tl(1, "}");
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
					}
					else {
						tl(1, "public ", entiteNomSimpleComplet, " sql", entiteVarCapitalise, "() {");
						tl(2, "return ", entiteVar, ";");
						tl(1, "}");
					}
				}
			}

			if(classePage && entiteVarCapitalise != null && classeIndexe && entiteSolrNomCanonique != null) {

				int tIndex = 0;
				Boolean resultat = false;

				if(entiteHtml && (classeVarClePrimaire != null || !classeModele)) {

//						String classeApiMethodeMethode = "PATCH";
					String classePrefixe = "";
					if(classeEstBase) {
						classePrefixe = "s.";
					}

					genCodeEntiteHtm(langueNom, langueConfig);
				}
			}

			if(entiteSolrNomSimple != null) {

				///////////////
				// staticSet //
				///////////////
	
				wStaticSet.tl(2, "case \"", entiteVar, "\":");
				wStaticSet.tl(3, "return ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_, o);");
	
				////////////////
				// staticSearch //
				////////////////
	
				wstaticSearch.tl(2, "case \"", entiteVar, "\":");
				if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
					wstaticSearch.tl(3, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_, (", entiteNomSimpleGenerique, ")o);");
				}
				else {
					wstaticSearch.tl(3, "return ", classeNomSimple, ".staticSearch", entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_, (", entiteNomSimpleComplet, ")o);");
				}
	
				///////////////////
				// staticSearchStr //
				///////////////////
	
				wstaticSearchStr.tl(2, "case \"", entiteVar, "\":");
				if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
					wstaticSearchStr.tl(3, "return ", classeNomSimple, ".staticSearchStr", entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_, (", entiteSolrNomSimple == null ? "Object" : StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteSolrNomSimple, "<"), ">"), ")o);");
				}
				else {
					wstaticSearchStr.tl(3, "return ", classeNomSimple, ".staticSearchStr", entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_, (", entiteSolrNomSimple == null ? "Object" : entiteSolrNomSimple, ")o);");
				}
	
				//////////////////
				// staticSearchFq //
				//////////////////
	
				wstaticSearchFq.tl(2, "case \"", entiteVar, "\":");
				wstaticSearchFq.tl(3, "return ", classeNomSimple, ".staticSearchFq", entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_, o);");
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
				wInitLoin.tl(3, entiteVar, langueConfig.getString(ConfigCles.var_Promesse), "().onSuccess(", entiteVar, " -> {");
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
				tl(3, entiteVar, ".set", langueConfig.getString(ConfigCles.var_RequeteSite), "_(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
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
						tl(3, "doc.put(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
					}
					else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(3, "doc.put(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_DATE_TIME.format(", entiteVar, "));");
					}
					else if(entiteNomCanonique.toString().equals(LocalTime.class.getCanonicalName())) {
						tl(3, "doc.put(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_TIME.format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "doc.put(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_DATE_TIME.format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(3, "doc.put(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_DATE_TIME.format(", entiteVar, ".atStartOfDay(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
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
	
				if(entiteNomSimple != null && entiteIndexe && !entiteSuggere && !entiteCleUnique) {
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
						tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(", entiteVar, ".atStartOfDay(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
					}
					else if(entiteNomSimple.toString().equals("Point")) {
						tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", String.format(\"%s,%s\", ", entiteVar, ".getX(), ", entiteVar, ".getY()));");
					}
					else if(entiteNomSimple.toString().equals("JsonObject")) {
						tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", ", entiteVar, ".toString());");
					}
					else if(entiteNomSimple.toString().equals("BigDecimal")) {
						tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", ", entiteVar, ".doubleValue());");
					}
					else if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
						tl(3, "JsonArray l = new JsonArray();");
						tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\", l);");
						tl(3, "for(", entiteNomSimpleCompletGenerique, " o : ", entiteVar, ") {");
						tl(4, "l.add(o);");
						tl(3, "}");
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
						tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(", entiteVar, ".atStartOfDay(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
					}
					else if(entiteNomSimple.toString().equals("Point")) {
						tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", new Point(", entiteVar, ".getPoint1(), ", entiteVar, ".getPoint2()));");
					}
					else if(entiteNomSimple.toString().equals("JsonObject")) {
						tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", ", entiteVar, ".toString());");
					}
					else if(entiteNomSimple.toString().equals("BigDecimal")) {
						tl(3, "doc.put(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\", ", entiteVar, ".doubleValue());");
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
					} else {
						wVarStocke.tl(4, "return \"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe && entiteStocke ? "_indexedstored" : "_stored")), entiteSuffixeType, "\";");
					}
				}
				if(entiteIndexe) {
					wVarIndexe.tl(3, "case \"", entiteVar, "\":");
					if(StringUtils.isNotEmpty(classeVarCleUnique) && entiteCleUnique) {
						wVarIndexe.tl(4, "return \"", classeVarCleUnique, "\";");
					} else if(entiteSuggere)
						wVarIndexe.tl(4, "return \"", entiteVar, "_suggested", "\";");
					else if(entiteTexte && entiteLangue != null)
						wVarIndexe.tl(4, "return \"", entiteVar, "_text_" + entiteLangue, "\";");
					else {
						wVarIndexe.tl(4, "return \"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteStocke ? "_indexedstored" : "_indexed")), entiteSuffixeType, "\";");
					}
				}
				if(entiteTexte && entiteLangue != null) {
					wVarRecherche.tl(3, "case \"", entiteVar, "\":");
					wVarRecherche.tl(4, "return \"", entiteVar, "_text_" + entiteLangue, "\";");
				}
				if(entiteSuggere) {
					wVarSuggere.tl(3, "case \"", entiteVar, "\":");
					wVarSuggere.tl(4, "return \"", entiteVar, "_suggested", "\";");
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
				wIndexerFacetFor.tl(5, "if(\"", entiteAttribuerNomSimple, "\".equals(", langueConfig.getString(ConfigCles.var_classeNomSimple), "2) && ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "2 != null) {");
				wIndexerFacetFor.tl(6, langueConfig.getString(ConfigCles.var_ListeRecherche), "<", entiteAttribuerNomSimple, "> ", langueConfig.getString(ConfigCles.var_listeRecherche), "2 = new ", langueConfig.getString(ConfigCles.var_ListeRecherche), "<", entiteAttribuerNomSimple, ">();");
				wIndexerFacetFor.tl(6, langueConfig.getString(ConfigCles.var_listeRecherche), "2.set", langueConfig.getString(ConfigCles.var_Stocker), "(true);");
				wIndexerFacetFor.tl(6, langueConfig.getString(ConfigCles.var_listeRecherche), "2.q(\"*:*\");");
				wIndexerFacetFor.tl(6, langueConfig.getString(ConfigCles.var_listeRecherche), "2.setC(", entiteAttribuerNomSimple, ".class);");
				wIndexerFacetFor.tl(6, langueConfig.getString(ConfigCles.var_listeRecherche), "2.fq(\"", classeModele ? classeVarClePrimaire : classeVarCleUnique, "_docvalues_long:\" + ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "2);");
				wIndexerFacetFor.tl(6, langueConfig.getString(ConfigCles.var_listeRecherche), "2.rows(1L);");
				wIndexerFacetFor.tl(6, "futures.add(Future.future(promise2 -> {");
				wIndexerFacetFor.tl(7, langueConfig.getString(ConfigCles.var_listeRecherche), "2.", langueConfig.getString(ConfigCles.var_promesseLoin), langueConfig.getString(ConfigCles.var_ListeRecherche), "(", langueConfig.getString(ConfigCles.var_requeteSite), ").onSuccess(b -> {");
				wIndexerFacetFor.tl(8, entiteAttribuerNomSimple, " o2 = ", langueConfig.getString(ConfigCles.var_listeRecherche), "2.getList().stream().findFirst().orElse(null);");
				wIndexerFacetFor.tl(8, "if(o2 != null) {");
				wIndexerFacetFor.tl(9, "JsonObject params = new JsonObject();");
				wIndexerFacetFor.tl(9, "params.put(\"body\", new JsonObject());");
				wIndexerFacetFor.tl(9, "params.put(\"cookie\", new JsonObject());");
				wIndexerFacetFor.tl(9, "params.put(\"path\", new JsonObject());");
				wIndexerFacetFor.tl(9, "params.put(\"query\", new JsonObject().put(\"q\", \"*:*\").put(\"fq\", new JsonArray().add(\"pk:\" + pk2)));");
				wIndexerFacetFor.tl(9, "JsonObject context = new JsonObject().put(\"params\", params).put(\"user\", ", langueConfig.getString(ConfigCles.var_requeteSite), ".getUserPrincipal());");
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
					tl(5, "o", classeNomSimple, ".set", entiteVarCapitalise, "(val == null ? null : (NumberUtils.isCreatable(val.toString()) ? Long.parseLong(val.toString()) : -1));");
				}
				tl(4, "if(!", langueConfig.getString(ConfigCles.var_sauvegardes), ".contains(\"", entiteVar, "\"))");
				tl(5, langueConfig.getString(ConfigCles.var_sauvegardes), ".add(\"", entiteVar, "\");");
				tl(4, "return val;");
			}	

			o = wAttribuerSql;
			if((classeEtendBase || classeEstBase) && entiteAttribuer) {
				if(!wAttribuerSql.getEmpty())
					wAttribuerSql.s(" UNION ");
				if("array".equals(entiteTypeJson)) {
					if("array".equals(entiteAttribuerTypeJson)) {
						if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
							wAttribuerSql.s("SELECT pk2, '", entiteVar, "' from ", classeNomSimple, entiteVar, "_", entiteAttribuerNomSimple, entiteAttribuerVar, " where pk1=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						} else {
							wAttribuerSql.s("SELECT pk1, '", entiteVar, "' from ", entiteAttribuerNomSimple, entiteAttribuerVar, "_", classeNomSimple, entiteVar, " where pk2=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						}
					} else {
						if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
							wAttribuerSql.s("SELECT ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " as pk2, '", entiteVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						} else {
							wAttribuerSql.s("SELECT ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " as pk1, '", entiteVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						}
					}
				}
				else {
					if("array".equals(entiteAttribuerTypeJson)) {
						if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
							wAttribuerSql.s("SELECT ", entiteVar, " as pk2, '", entiteVar, "' from ", classeNomSimple, " where ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						} else {
							wAttribuerSql.s("SELECT ", entiteVar, " as pk1, '", entiteVar, "' from ", classeNomSimple, " where ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						}
					} else {
						if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
							wAttribuerSql.s("SELECT ", entiteVar, " as pk2, '", entiteVar, "' from ", classeNomSimple, " where ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						} else {
							wAttribuerSql.s("SELECT ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " as pk1, '", entiteVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						}
					}
				}

//				if("array".equals(entiteTypeJson)) {
//					if(!o.getEmpty())
//						o.s(" UNION ");
//					if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
//						if("array".equals(entiteAttribuerTypeJson))
//							o.s("1SELECT pk1, pk2, '", entiteVar, "', '", entiteAttribuerVar, "' from ", classeNomSimple, entiteVar, "_", entiteAttribuerNomSimple, entiteAttribuerVar, " where pk1=$" + wAttribuerSqlNum);
//						else
//							o.s("2SELECT ", entiteAttribuerVar, " as pk1, ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " as pk2, '", entiteVar, "', '", entiteAttribuerVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
//					}
//					else {
//						if("array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson))
//							o.s("3SELECT pk1, pk2, '", entiteAttribuerVar, "', '", entiteVar, "' from ", entiteAttribuerNomSimple, entiteAttribuerVar, "_", classeNomSimple, entiteVar, " where pk2=$" + wAttribuerSqlNum);
//						else
//							o.s("4SELECT ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " as pk1, ", entiteAttribuerVar, " as pk2, '", entiteAttribuerVar, "', '", entiteVar, "' from ", entiteAttribuerNomSimple, " where pk=$" + wAttribuerSqlNum);
//					}
//					wAttribuerSqlNum++;
//				}
//				else {
//					if(!o.getEmpty())
//						o.s(" UNION ");
//					if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
//						if("array".equals(entiteAttribuerTypeJson))
//							o.s("5SELECT pk1, pk2, '", entiteVar, "', '", entiteAttribuerVar, "' from ", classeNomSimple, entiteVar, "_", entiteAttribuerNomSimple, entiteAttribuerVar, " where pk1=$" + wAttribuerSqlNum);
//						else {
//							o.s("6SELECT $", wAttribuerSqlNum);
//							wAttribuerSqlNum++;
//							o.s(" as pk1, ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " as pk2, '", entiteVar, "', '", entiteAttribuerVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
//						}
//					}
//					else {
//						if("array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson))
//							o.s("7SELECT pk1, pk2, '", entiteAttribuerVar, "', '", entiteVar, "' from ", entiteAttribuerNomSimple, entiteAttribuerVar, "_", classeNomSimple, entiteVar, " where pk2=$" + wAttribuerSqlNum);
//						else {
//							o.s("8SELECT ", classeModele ? classeVarClePrimaire : classeVarCleUnique, " as pk1, $", wAttribuerSqlNum);
//							wAttribuerSqlNum++;
//							o.s(" as pk2, '", entiteAttribuerVar, "', '", entiteVar, "' from ", entiteAttribuerNomSimple, " where pk=$" + wAttribuerSqlNum);
//						}
//					}
//					wAttribuerSqlNum++;
//				}
			}	
	
			/////////////
			// definir //
			/////////////

			o = wDefinir;
			if(classeIndexe && (BooleanUtils.isTrue(entiteDefinir) || BooleanUtils.isTrue(entiteAttribuer) && !"array".equals(entiteTypeJson))) {
					tl(3, "case \"", entiteVar.toLowerCase(), "\":");
					if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
						tl(4, "if(val != null)");
						tl(5, "add", entiteVarCapitalise, "(val);");
						tl(4, "if(!", langueConfig.getString(ConfigCles.var_sauvegardes), ".contains(\"", entiteVar, "\"))");
						tl(5, "", langueConfig.getString(ConfigCles.var_sauvegardes), ".add(\"", entiteVar, "\");");
					}
					else {
						tl(4, "if(val != null)");
						tl(5, "set", entiteVarCapitalise, "(val);");
						tl(4, langueConfig.getString(ConfigCles.var_sauvegardes), ".add(\"", entiteVar, "\");");
					}
					tl(4, "return val;");
			}	

			o = wDefinirObjet;
			if(classeIndexe && BooleanUtils.isTrue(entiteDefinir) || BooleanUtils.isTrue(entiteAttribuer) && !"array".equals(entiteTypeJson)) {
					tl(3, "case \"", entiteVar.toLowerCase(), "\":");
					if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
						tl(4, "if(val instanceof ", entiteNomSimpleComplet, ")");
						tl(5, "add", entiteVarCapitalise, "((", entiteNomSimpleComplet, ")val);");
						tl(4, "if(!", langueConfig.getString(ConfigCles.var_sauvegardes), ".contains(\"", entiteVar, "\"))");
						tl(5, "", langueConfig.getString(ConfigCles.var_sauvegardes), ".add(\"", entiteVar, "\");");
					}
					else {
						if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniquePoint)) {
							tl(4, "if(val instanceof String)");
							tl(5, "set", entiteVarCapitalise, "((String)val);");
							tl(4, "else if(val instanceof Point)");
							tl(5, "set", entiteVarCapitalise, "((Point)val);");
						} else if(StringUtils.equals(entiteNomCanonique, VAL_nomCanoniqueVertxJsonObject)) {
							tl(4, "if(val instanceof String)");
							tl(5, "set", entiteVarCapitalise, "((String)val);");
							tl(4, "else if(val instanceof JsonObject)");
							tl(5, "set", entiteVarCapitalise, "((JsonObject)val);");
						} else if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())) {
							tl(4, "if(val instanceof String)");
							tl(5, "set", entiteVarCapitalise, "((String)val);");
							tl(4, "else if(val instanceof Number)");
							tl(5, "set", entiteVarCapitalise, "(new BigDecimal(((Number)val).doubleValue()));");
						} else {
							tl(4, "if(val instanceof ", entiteNomSimpleComplet, ")");
							tl(5, "set", entiteVarCapitalise, "((", entiteNomSimpleComplet, ")val);");
							if(!StringUtils.equals(entiteNomCanonique, String.class.getCanonicalName())) {
								tl(4, "else if(val instanceof String)");
								tl(5, "set", entiteVarCapitalise, "((String)val);");
							}
						}
						if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
							tl(4, "else if(val instanceof OffsetDateTime)");
							tl(5, "set", entiteVarCapitalise, "(((OffsetDateTime)val).atZoneSameInstant(ZoneId.of(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Config), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_SITE_ZONE), "))));");
						}
						tl(4, langueConfig.getString(ConfigCles.var_sauvegardes), ".add(\"", entiteVar, "\");");
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
			if(classeSauvegarde) {
	//							String nomChamp = entiteVar.toString();
	//							String varCrypte = entiteVarCrypte.toString();
	//							String varStocke = entiteVarStocke.toString();
	//							String varSuggere = entiteVarSuggere.toString();
	//							String varIncremente = entiteVarIncremente.toString();
	//							String varCleUnique = entiteVarCleUniqueActuel.toString();
				if(entiteCrypte || entiteStocke || entiteCleUnique || entiteSuggere || entiteIncremente) {
					tl(0);
	
					if(entiteSuggere) {
						tl(3, "if(", langueConfig.getString(ConfigCles.var_sauvegardes), ".contains(\"", entiteVar, "\")) {");
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
						tl(3, "if(", langueConfig.getString(ConfigCles.var_sauvegardes), ".contains(\"", entiteVar, "\")) {");
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
						tl(3, "if(", langueConfig.getString(ConfigCles.var_sauvegardes), ".contains(\"", entiteVar, "\")) {");
						tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\");");
						if(StringUtils.contains(entiteSolrNomCanonique, "<")) {
							if(entiteCouverture) {
								tl(4, "if(", entiteVar, " != null)");
								tl(5, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
							}
							else {
								tl(4, "if(", entiteVar, " != null)");
								tl(5, "o", classeNomSimple, ".", entiteVar, ".addAll(", entiteVar, ");");
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

//				if(entiteTexte) {
//					if("frFR".equals(langueNom) || "esES".equals(langueNom))
//						tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "_text_", langueNom, "\");");
//					else
//						tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "_text_enUS\");");
//					tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//				}
				if(entiteSuggere) {
					tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")doc.get(\"", entiteVar, "_suggested", "\");");
					tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
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
						tl(2, "Optional.ofNullable((List<?>)doc.get(\"", entiteVar, (entiteDocValues ? "_docvalues" : (entiteIndexe ? "_indexedstored" : "_stored")), entiteSuffixeType, "\")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {");
						tl(3, "o", classeNomSimple, ".add", entiteVarCapitalise, "(v.toString());");
						tl(2, "});");
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
					&& !langueConfig.getString(ConfigCles.var_supprime).equals(entiteVar) 
					&& !langueConfig.getString(ConfigCles.var_archive).equals(entiteVar) 
					&& !langueConfig.getString(ConfigCles.var_classeNomsCanoniques).equals(entiteVar)
					&& !langueConfig.getString(ConfigCles.var_classeNomCanonique).equals(entiteVar)
					&& !langueConfig.getString(ConfigCles.var_classeNomSimple).equals(entiteVar)
					&& !langueConfig.getString(ConfigCles.var_sessionId).equals(entiteVar)
					&& !langueConfig.getString(ConfigCles.var_utilisateurCle).equals(entiteVar)
					&& !langueConfig.getString(ConfigCles.var_sauvegardes).equals(entiteVar)
					) {
				wVarsFq.tl(2, "vars.add(VAR_", entiteVar, ");");
				if(!entiteAttribuer && (
						entiteNomSimple.endsWith("DateTime")
						||entiteNomSimple.equals("Date")
						||entiteNomSimple.equals("Timestamp")
						||entiteNomSimple.equals("BigDecimal")
						||entiteNomSimple.equals("Point")
						||entiteNomSimple.equals("JsonObject")
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

			wNomAffichageStatic.tl(1, "public static final String ", langueConfig.getString(ConfigCles.var_NOM_AFFICHAGE), "_", entiteVar, " = \"", entiteNomAffichage, "\";");

			if(entiteDescription != null) {
				wDescriptionMethode.tl(2, "case VAR_", entiteVar, ":");
				wDescriptionMethode.tl(3, "return \"", entiteDescription, "\";");
			}
			if(entiteNomSimple != null) {
				wClasseNomSimpleMethode.tl(2, "case VAR_", entiteVar, ":");
				wClasseNomSimpleMethode.tl(3, "return \"", entiteNomSimple, "\";");
			}
			if(entiteHtmlColonne != null) {
				wHtmlColonneMethode.tl(2, "case VAR_", entiteVar, ":");
				wHtmlColonneMethode.tl(3, "return ", entiteHtmlColonne, ";");
			}
			if(entiteHtmlLigne != null) {
				wHtmlLigneMethode.tl(2, "case VAR_", entiteVar, ":");
				wHtmlLigneMethode.tl(3, "return ", entiteHtmlLigne, ";");
			}
			if(entiteHtmlCellule != null) {
				wHtmlCelluleMethode.tl(2, "case VAR_", entiteVar, ":");
				wHtmlCelluleMethode.tl(3, "return ", entiteHtmlCellule, ";");
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
			wNomAffichageMethode.tl(3, "return ", langueConfig.getString(ConfigCles.var_NOM_AFFICHAGE), "_", entiteVar, ";");
	
			if(entiteDefinir || entiteAttribuer || entiteIndexe || entiteStocke) {
		
				//////////////////
				// requeteApi //
				//////////////////
		
				wRequeteApi.tl(3, "if(!Objects.equals(", entiteVar, ", original.get", entiteVarCapitalise, "()))");
				wRequeteApi.tl(4, langueConfig.getString(ConfigCles.var_requeteApi), ".addVars(\"", entiteVar, "\");");
		
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

	/////////
	// htm //
	/////////

	public void genCodeEntiteHtm(String langueNom, YAMLConfiguration langueConfig) throws Exception {
		ToutEcrivain oAncien = o;
		o = auteurGenPageHbsEntite;

		l();
		l("{{#*inline \"htm", entiteVarCapitalise, "\"}}");
		tl(8, "<div class=\"w3-cell w3-cell-top w3-center w3-mobile \">");
		if(entiteHtml && (entiteDefinir || entiteAttribuer)) {

			tl(9, "<div class=\"w3-padding \">");
			tl(10, "<div id=\"", langueConfig.getString(ConfigCles.var_suggere), "{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}", classeNomSimple, entiteVarCapitalise, "\">");
			tl(11, "<div class=\"w3-card \">");

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
				tl(14, langueConfig.getString(ConfigCles.var_relier), " ", entiteListeTypeJson == null ? entiteAttribuerContexteUnNom : entiteAttribuerContexteNomPluriel, " ", langueConfig.getString(ConfigCles.var_a), " ", contexteCeNom);
				tl(13, "</h5>");
				tl(12, "</div>");
				tl(12, "<div class=\"w3-cell-row w3-padding \">");
				tl(13, "<div class=\"w3-cell \">");
				tl(14, "<div class=\"w3-cell-row \">");
				l();

				tl(14, "{{> \"input", entiteVarCapitalise, "\"}}");
				tl(14, "</div>");
				tl(13, "</div>");
				tl(12, "</div>");
				tl(12, "<div class=\"w3-cell-row w3-padding \">");
				tl(13, "<div class=\"w3-cell w3-left-align w3-cell-top \">");
				tl(14, "<ul class=\"w3-ul w3-hoverable \" id=\"list", classeNomSimple, entiteVarCapitalise, "_{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}\">");
				tl(14, "</ul>");


				if(entiteAttribuerUtilisateurEcrire && entiteAttribuerSessionEcrire) {
//					tl(14, "{");
				}
				else if(entiteAttribuerUtilisateurEcrire) {
					if(entiteAttribuerClasseRoles != null && entiteAttribuerClasseRoles.size() > 0) {
						tl(14, "{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolesRequis), "}}");
					}
					else {
						tl(14, "{{#ifContainsKeys ", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s}}");
//						tl(14, "if(", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s.contains(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Utilisateur), langueConfig.getString(ConfigCles.var_Cle), "())) {", );
					}
				}
				else if(entiteAttribuerSessionEcrire) {
					tl(14, "{{#ifContainsSessionId sessionId}}");
//					tl(14, "if(Objects.equals(sessionId, ", langueConfig.getString(ConfigCles.var_requeteSite), "_.getSessionId()) {", );
				}
				else {
					if(classeRolesTrouves || classeRoleLiresTrouves) {
						tl(14, "{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolesRequis), "}}");
					}
					else {
//						tl(14, "{");
					}
				}

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

				if(entiteAttribuerUtilisateurEcrire && entiteAttribuerSessionEcrire) {
				}
				else if(entiteAttribuerUtilisateurEcrire) {
					if(entiteAttribuerClasseRoles != null && entiteAttribuerClasseRoles.size() > 0) {
						tl(14, "{{/ifContainsAnyRoles}}");
					}
					else {
						tl(14, "{{/ifContainsKeys}}");
					}
				}
				else if(entiteAttribuerSessionEcrire) {
					tl(14, "{{/ifContainsSessionId}}");
				}
				else {
					if(classeRolesTrouves || classeRoleLiresTrouves) {
						tl(14, "{{/ifContainsAnyRoles}}");
					}
					else {
					}
				}

				tl(13, "</div>");
			}
			else if("LocalDate".equals(entiteNomSimple)) {
				if(entiteNomAffichage != null) {
					tl(12, "<div class=\"w3-cell-row w3-", contexteCouleur, "\">");
					tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
					tl(12, "</div>");
				}
				tl(12, "<div class=\"w3-cell-row  \">");
				tl(13, "<div class=\"w3-cell \">");
				tl(14, "{{> \"input", entiteVarCapitalise, "\"}}");
				tl(13, "</div>");
			}
			else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
				if(entiteNomAffichage != null) {
					tl(12, "<div class=\"w3-cell-row w3-", contexteCouleur, "\">");
					tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
					tl(12, "</div>");
				}
				tl(12, "<div class=\"w3-cell-row w3-padding \">");
				tl(13, "<div class=\"w3-cell \">");
				tl(14, "{{> \"input", entiteVarCapitalise, "\"}}");
				tl(13, "</div>");
			}
			else if("LocalTime".equals(entiteNomSimple)) {
				if(entiteNomAffichage != null) {
					tl(12, "<div class=\"w3-cell-row w3-", contexteCouleur, "\">");
					tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
					tl(12, "</div>");
				}
				tl(12, "<div class=\"w3-cell-row w3-padding \">");
				tl(13, "<div class=\"w3-cell \">");
				tl(14, "{{> \"input", entiteVarCapitalise, "\"}}");
				tl(13, "</div>");
			}
			else if("Boolean".equals(entiteNomSimple)) {
				if(entiteNomAffichage != null) {
					tl(12, "<div class=\"w3-cell-row w3-", contexteCouleur, "\">");
					tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
					tl(12, "</div>");
				}
				tl(12, "<div class=\"w3-cell-row w3-padding \">");
				tl(13, "<div class=\"w3-cell \">");
				tl(14, "{{> \"input", entiteVarCapitalise, "\"}}");
				tl(13, "</div>");
			}
			else {
				if(entiteNomAffichage != null) {
					tl(12, "<div class=\"w3-cell-row w3-", contexteCouleur, "\">");
					tl(13, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\">", entiteNomAffichage, "</label>");
					tl(12, "</div>");
				}
				tl(12, "<div class=\"w3-cell-row w3-padding \">");
				tl(13, "<div class=\"w3-cell \">");
				tl(14, "{{> \"input", entiteVarCapitalise, "\"}}");
				tl(13, "</div>");
			}
			if(!entiteAttribuer && entiteModifier && !"Boolean".equals(entiteNomSimple)) {

				if(classeUtilisateurEcrire && classeSessionEcrire) {
					tl(1, "{{#ifContainsKeysAnyRolesOrSessionId ", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), " ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolesRequis), " ", langueConfig.getString(ConfigCles.var_sessionId), " ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(ConfigCles.var_sessionId), "}}");
				}
				else if(classeUtilisateurEcrire) {
					if(classeRolesTrouves) {
						tl(1, "{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolesRequis), "}}");
					}
					else {
						tl(1, "{{#ifContainsKeys ", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s}}");
					}
				}
				else if(classeSessionEcrire) {
					tl(1, "{{#ifContainsSessionId sessionId}}");
				}
				else if(classeRolesTrouves) {
					tl(1, "{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolesRequis), "}}");
				}

				tl(2, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");

					tl(15, "<div class=\"w3-cell w3-left-align w3-cell-top \">");
					tl(16, "<button");
					tl(18, "tabindex=\"-1\"");
					tl(18, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-", contexteCouleur, " \"");
					tl(18, "onclick=\"", langueConfig.getString(ConfigCles.var_enleverLueur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); $('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "').val(null); patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: 'pk:' + $('#", classeNomSimple, "Form :input[name=", classeModele ? classeVarClePrimaire : classeVarCleUnique, "]').val() }], 'set", entiteVarCapitalise, "', null, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); \"");
					tl(18, ">");
					tl(17, "<i class=\"far fa-eraser \"></i>");
					tl(16, "</button>");
					tl(15, "</div>");

				tl(14, "{{/eq}}");

				if(classeUtilisateurEcrire && classeSessionEcrire) {
					tl(1, "{{/ifContainsKeysAnyRolesOrSessionId}}");
				}
				else if(classeUtilisateurEcrire) {
					if(classeRolesTrouves) {
						tl(1, "{{/ifContainsAnyRoles}}");
					}
					else {
						tl(1, "{{/ifContainsKeys}}");
					}
				}
				else if(classeSessionEcrire) {
					tl(1, "{{/ifContainsSessionId}}");
				}
				else if(classeRolesTrouves) {
					tl(1, "{{/ifContainsAnyRoles}}");
				}
			}

			tl(12, "</div>");
			tl(11, "</div>");
			tl(10, "</div>");
			tl(9, "</div>");
		}
		else if(!(entiteAttribuer)) {

			tl(9, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");

				tl(10, "<div class=\"w3-padding \">");
				tl(11, "<div class=\"w3-card \">");

				if(entiteNomAffichage != null) {
					tl(12, "<div class=\"w3-cell-row w3-", contexteCouleur, "\">");
					tl(13, "<label>", entiteNomAffichage, "</label>");
					tl(12, "</div>");
				}
				tl(12, "<div class=\"w3-cell-row  \">");
				tl(13, "<div class=\"w3-cell \">");
				tl(14, "<div class=\"w3-rest \">");
				tl(15, "<span class=\"\">");
				tl(16, "{{> \"input", entiteVarCapitalise, "\"}}");
				tl(15, "</span>");
				tl(14, "</div>");
				tl(13, "</div>");
				tl(12, "</div>");
				tl(11, "</div>");
				tl(10, "</div>");
			tl(1, "{{/eq}}");
		}

		tl(8, "</div>");
		l("{{/inline}}");  

		///////////
		// input //
		///////////

		l();
		tl(0, "{{#*inline \"input", entiteVarCapitalise, "\"}}");
		if(entiteModifier && (entiteDefinir || entiteAttribuer)) {

			if(classeUtilisateurEcrire && classeSessionEcrire) {
				tl(2, "{{#ifContainsKeysAnyRolesOrSessionId ", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), " ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolesRequis), " ", langueConfig.getString(ConfigCles.var_sessionId), " ", uncapitalizeClasseNomSimple, "_.", langueConfig.getString(ConfigCles.var_sessionId), "}}");
//				t(2, "if(");
//				t(4, langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s.contains(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Utilisateur), langueConfig.getString(ConfigCles.var_Cle), "())");
//				t(4, "|| Objects.equals(sessionId, ", langueConfig.getString(ConfigCles.var_requeteSite), "_.getSessionId())");
//				t(4, "|| CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "(), ROLES)");
//				t(4, "|| CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "(), ROLES)");
//				t(2, ") {");
			}
			else if(classePublicLire) {
				tl(2, "{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolesRequis), "}}");
//				tl(2, "if(");
//				tl(4, "CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "(), ROLES)");
//				tl(4, "|| CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "(), ROLES)");
//				tl(4, ") {");
			}
			else if(classeUtilisateurEcrire) {
				if(classeRolesTrouves || classeRoleLiresTrouves) {
					tl(2, "{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolesRequis), "}}");
//					tl(2, "if(");
//					tl(4, "CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "(), ROLES)");
//					tl(4, "|| CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "(), ROLES)");
//					tl(4, ") {");
				}
				else {
					tl(2, "{{#ifContainsKeys ", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s}}");
//					t(2, "if(", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s.contains(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Utilisateur), langueConfig.getString(ConfigCles.var_Cle), "())) {");
				}
			}
			else if(classeSessionEcrire) {
				tl(2, "{{#ifContainsSessionId sessionId}}");
//				t(2, "if(Objects.equals(sessionId, ", langueConfig.getString(ConfigCles.var_requeteSite), "_.getSessionId()) {");
			}
			else if(classeRolesTrouves || classeRoleLiresTrouves) {
				tl(2, "{{#ifContainsAnyRoles ", langueConfig.getString(ConfigCles.var_roles), " ", langueConfig.getString(ConfigCles.var_rolesRequis), "}}");
//				tl(2, "if(");
//				tl(4, "CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "(), ROLES)");
//				tl(4, "|| CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "(), ROLES)");
//				tl(4, ") {");
			}
			else {
//				t(2, "{");
			}

			if(entiteAttribuer) {
				tl(14, "<i class=\"far fa-search w3-xxlarge w3-cell w3-cell-middle \"></i>");
				tl(14, "{{#eq '", langueConfig.getString(ConfigCles.var_PUTCopie), "' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(15, "<div>");
				tl(16, "<input ");
				tl(17, "type=\"checkbox\"");
				tl(17, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), "\"");
				tl(17, "class=\"", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), " \"");
				tl(17, ">");
				tl(16, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), "\">", langueConfig.getString(ConfigCles.var_vider), "</label>");
				tl(15, "</div>");
				tl(14, "{{/eq}}");

				tl(14, "<input");
				tl(16, "type=\"text\"");

				if(entiteNomAffichage != null) {
					tl(16, "placeholder=\"", entiteNomAffichage, "\"");
				}
				if(entiteDescription != null) {
					t(16, "title=\"", entiteDescription, "\"");
				}

				tl(15, "class=\"", langueConfig.getString(ConfigCles.var_valeur), StringUtils.capitalize(entiteAttribuerVarSuggere), " ", langueConfig.getString(ConfigCles.var_suggere), entiteVarCapitalise, " w3-input w3-border w3-cell w3-cell-middle \"");
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
				tl(16, "class=\"w3-input w3-border set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				if(entiteDescription != null)
					tl(16, "title=\"", entiteDescription, " (", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), ")\"");
//				tl(5, "value=\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_ddDashMMDashyyyy), "\").format(", entiteVar, "));");
				tl(16, "value=\"{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}\"");
				tl(14, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				t(15, "onchange=\"");
					s("if(this.value) { ");
						s("patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: 'pk:{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', this.value, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); ");
					s("} ");
				l("\"");
				tl(14, "{{/eq}}");
				tl(14, "/>");
			}
			else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
				tl(14, "<input");
				tl(16, "type=\"text\"");
				tl(16, "class=\"w3-input w3-border datepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "placeholder=\"", langueConfig.getString(ConfigCles.str_DDDashMMDashYYYY_HHColonMM), "\"");
				tl(16, "data-timeformat=\"", langueConfig.getString(ConfigCles.var_ddDashMMDashyyyy), "\"");
				tl(16, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				if(entiteDescription != null)
					tl(16, "title=\"", entiteDescription, " (", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), ")\"");
//				tl(4, ".a(\"value\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz_VV), "\").format(", entiteVar, "));");
				tl(16, "value=\"{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}\"");
				tl(14, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(15, "onclick=\"", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); \";");
				t(15, "onchange=\"");
					s("var t = moment(this.value, '", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "'); ");
					s("if(t) { ");
						s("var s = t.format('YYYY-MM-DD'); ");
						s("patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: 'pk:{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', s, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); ");
					s("} ");
				l("\"");
				tl(14, "{{/eq}}");
				tl(14, "/>");
			}
			else if("LocalTime".equals(entiteNomSimple)) {
				tl(14, "<input");
				tl(16, "type=\"text\"");
				tl(16, "class=\"w3-input w3-border datepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "placeholder=\"", langueConfig.getString(ConfigCles.var_HHColonMM), "\"");
				tl(16, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

				tl(14, "<input");
				tl(16, "type=\"text\"");
				tl(16, "class=\", \"w3-input w3-border timepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "placeholder=\"", langueConfig.getString(ConfigCles.var_HHColonMM), "\"");
				tl(16, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				if(entiteDescription != null)
					tl(16, "title=\"", entiteDescription + " (", langueConfig.getString(ConfigCles.var_HAposhAposmm), ")\"");
				tl(16, "value=\"{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}\"");
				tl(14, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(15, "onclick=\"", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); \"");
				t(15, "onchange=\"");
					s("var t = moment(this.value, '", langueConfig.getString(ConfigCles.var_HAposhAposmm), "'); ");
					s("if(t) { ");
						s("var s = t.format('HH:mm'); ");
						s("patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: 'pk:{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', s, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); ");
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
				tl(15, "class=\"class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(15, "name=\"set", entiteVarCapitalise, "\"");
				tl(1, "{{else}}");
				tl(2, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(16, "class=\"class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "name=\"set", entiteVarCapitalise, "\"");
				tl(2, "{{else}}");
				tl(16, "class=\"set", entiteVarCapitalise, " ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "name=\"set", entiteVarCapitalise, "\"");
				tl(2, "{{/eq}}");
				tl(1, "{{/eq}}");
				tl(1, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
					tl(15, "onchange=\"patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: 'pk:{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', $(this).prop('checked'), function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); \"");
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
				tl(17, "patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: 'pk:{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', null); ");
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
					tl(15, "placeholder=\"", entiteNomAffichage, "\"");
				}
				if(entiteDescription != null) {
					tl(15, "title=\"", entiteDescription, "\"");
				}
				tl(15, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");

				tl(1, "{{#eq \"Page\" ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
					tl(16, "class=\"set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
					tl(16, "name=\"set", entiteVarCapitalise, "\"");
				tl(1, "{{else}}");
				tl(2, "{{#eq \"PATCH\" ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(16, "class=\"set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
				tl(16, "name=\"set", entiteVarCapitalise, "\"");
				tl(2, "{{else}}");
					tl(16, "class=\"", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " w3-input w3-border class", classeNomSimple, " input", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " w3-input w3-border \"");
					tl(16, "name=\"", entiteVar, "\"");
				tl(2, "{{/eq}}");
				tl(1, "{{/eq}}");
				tl(1, "{{#eq \"Page\" ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
					tl(16, "onclick=\"", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); \"");
					tl(16, "onchange=\"patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'softCommit', value: 'true' }, { name: 'fq', value: 'pk:{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}' }], 'set", entiteVarCapitalise, "', $(this).val(), function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "')); }); \"");
				tl(1, "{{/eq}}");

				if(entiteMultiligne) {
					tl(14, ">", "{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}");
				}
				else {
					tl(1, "{{#eq \"Page\" ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
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
				tl(15, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}", (entiteVarUrl == null ? "</span>" : "</a>"));
			}
			else if(classeUtilisateurEcrire) {
				if(classeRolesTrouves || classeRoleLiresTrouves) {
					tl(13, "{{else}}");
					tl(15, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
				}
				else {
					tl(13, "{{else}}");
					tl(14, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
				}
			}
			else if(classeSessionEcrire) {
				tl(13, "{{else}}");
				tl(14, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
			}
			else if(classeRolesTrouves || classeRoleLiresTrouves) {
					tl(13, "{{else}}");
				tl(14, "{{#ifContainsKeys ", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s}}");
//				tl(8, "if(");
//				tl(10, "CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "(), ROLES)");
//				tl(10, "|| CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "(), ROLES)");
//				if(classeRoleLiresTrouves) {
//					tl(10, "|| CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "(), ROLE_READS)");
//					tl(10, "|| CollectionUtils.containsAny(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "(), ROLE_READS)");
//				}
//				tl(9, ") {");
				tl(15, "<span class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}</span>");
//				tl(8, "}");
				tl(3, "{{/ifContainsKeys}}");
			}
			else {
//								tl(3, "sx(htm", entiteVarCapitalise, "());");
			}

			if(classeUtilisateurEcrire && classeSessionEcrire) {
				tl(2, "{{/ifContainsKeysAnyRolesOrSessionId}}");
			}
			else if(classePublicLire) {
				tl(2, "{{/ifContainsAnyRoles}}");
			}
			else if(classeUtilisateurEcrire) {
				if(classeRolesTrouves || classeRoleLiresTrouves) {
					tl(2, "{{/ifContainsAnyRoles}}");
				}
				else {
					tl(2, "{{/ifContainsKeys}}");
				}
			}
			else if(classeSessionEcrire) {
				tl(2, "{{/ifContainsSessionId}}");
			}
			else if(classeRolesTrouves || classeRoleLiresTrouves) {
				tl(2, "{{/ifContainsAnyRoles}}");
			}
		}
		else {
			tl(1, "{{#eq 'Page' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
			if(entiteAttribuer) {
				tl(14, "<i class=\"far fa-search w3-xxlarge w3-cell w3-cell-middle \"></i>");
				tl(14, "{{#eq '", langueConfig.getString(ConfigCles.var_PUTCopie), "' ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
				tl(15, "<div>");
				tl(16, "<input ");
				tl(17, "type=\"checkbox\"");
				tl(17, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), "\"");
				tl(17, "class=\"", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), " \"");
				tl(17, ">");
				tl(16, "<label for=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), "\">", langueConfig.getString(ConfigCles.var_vider), "</label>");
				tl(15, "</div>");
				tl(14, "{{/eq}}");

				tl(14, "<input");
				tl(16, "type=\"text\"");

				if(entiteNomAffichage != null) {
					tl(16, "placeholder=\"", entiteNomAffichage, "\"");
				}
				if(entiteDescription != null) {
					t(16, "title=\"", entiteDescription, "\"");
				}

				tl(15, "class=\"", langueConfig.getString(ConfigCles.var_valeur), StringUtils.capitalize(entiteAttribuerVarSuggere), " ", langueConfig.getString(ConfigCles.var_suggere), entiteVarCapitalise, " w3-input w3-border w3-cell w3-cell-middle \"");
				tl(15, "name=\"", "set", entiteVarCapitalise, "\"");
				tl(15, "id=\"{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}_", entiteVar, "\"");
				tl(15, "autocomplete=\"off\"");
				t(15, "oninput=\"", langueConfig.getString(ConfigCles.var_suggere), classeNomSimple, entiteVarCapitalise, "($(this).val() ? [ { 'name': 'q', 'value': '", entiteAttribuerVarSuggere, ":' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", langueConfig.getString(ConfigCles.var_classeNomCanonique), ",", entiteAttribuerVar, ",", classeVarClePrimaire, entiteAttribuerVarUrlPk == null ? "" : "," + entiteAttribuerVarUrlPk, entiteAttribuerVarTitre == null ? "" : "," + entiteAttribuerVarTitre, "' } ] : [");
				s("{{#if ", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}{'name':'fq','value':'", entiteAttribuerVar, ":{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}'}{{else}}{{/if}}");
				l("], $('#list", classeNomSimple, entiteVarCapitalise, "_{{", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}'), {{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}); \"");
				tl(15, "/>");
				l();
			} else if("LocalDateTime".equals(entiteNomSimple)) {
				tl(14, (entiteVarUrl == null ? "<span" : "<a href=\"{{ " + uncapitalizeClasseNomSimple + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \" title=\"{{formatLocalDateTime ", uncapitalizeClasseNomSimple, "_.", entiteVar, " 'EEEE MMMM d yyyy H:mm:ss.SSS zz VV' defaultLocaleId defaultZoneId}}\">{{formatZonedDateTime ", uncapitalizeClasseNomSimple, "_.", entiteVar, " 'EEE MMM d yyyy' defaultLocaleId defaultZoneId}}", (entiteVarUrl == null ? "</span>" : "</a>"));
			} else if("ZonedDateTime".equals(entiteNomSimple)) {
				tl(14, (entiteVarUrl == null ? "<span" : "<a href=\"{{ " + uncapitalizeClasseNomSimple + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \" title=\"{{formatZonedDateTime ", uncapitalizeClasseNomSimple, "_.", entiteVar, " 'EEEE MMMM d yyyy H:mm:ss.SSS zz VV' defaultLocaleId defaultZoneId}}\">{{formatZonedDateTime ", uncapitalizeClasseNomSimple, "_.", entiteVar, " 'EEE MMM d yyyy h:mm a zz' defaultLocaleId defaultZoneId}}", (entiteVarUrl == null ? "</span>" : "</a>"));
			} else {
				tl(14, (entiteVarUrl == null ? "<span" : "<a href=\"{{ " + uncapitalizeClasseNomSimple + "_." + entiteVarUrl + " }}\""), " class=\"var", classeNomSimple, "{{", classeModele ? classeVarClePrimaire : classeVarCleUnique, "}}", entiteVarCapitalise, " \">{{", uncapitalizeClasseNomSimple, "_.", entiteVar, "}}", (entiteVarUrl == null ? "</span>" : "</a>"));
			}
			tl(1, "{{/eq}}");
		}
		tl(0, "{{/inline}}");

		o = oAncien;
	}

	/**
	 * Var.enUS: genCodeClassEnd
	 * Param1.var.enUS: languageName
	 * r: classeInitLoinExceptionNomSimple
	 * r.enUS: classInitDeepExceptionSimpleName
	 * r: classeInitLoinExceptions
	 * r.enUS: classInitDeepExceptions
	 * r: classeInitLoinException
	 * r.enUS: classInitDeepException
	 * r: wInitLoin
	 * r.enUS: wInitDeep
	 * r: classeInitLoin
	 * r.enUS: classInitDeep
	 * r: classeNomSimpleSuperGenerique
	 * r.enUS: classSuperSimpleNameGeneric
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: PourClasse
	 * r.enUS: ForClass
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: requeteSitePourClasse
	 * r.enUS: siteRequestForClass
	 * r: wIndexer
	 * r.enUS: wIndex
	 * r: classeIndexe
	 * r.enUS: classIndexed
	 * r: classeEtendBase
	 * r.enUS: classExtendsBase
	 * r: classeEstBase
	 * r.enUS: classIsBase
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: wObtenir
	 * r.enUS: wObtain
	 * r: wAttribuer
	 * r.enUS: wAttribute
	 * r: classeSauvegarde
	 * r.enUS: classSaved
	 * r: wPeupler
	 * r.enUS: wPopulate
	 * r: wStocker
	 * r.enUS: wStore
	 * r: wSauvegarder
	 * r.enUS: wSave
	 * r: wApiEntites
	 * r.enUS: wApiEntities
	 * r: wApiGenererGet
	 * r.enUS: wApiGenerateGet
	 * r: wPageEntites
	 * r.enUS: wPageEntities
	 * r: auteurGenClasse
	 * r.enUS: writerGenClass
	 * r: wInitLoin
	 * r.enUS: wInitDeep
	 * r: wIndexer
	 * r.enUS: wIndex
	 * r: wObtenir
	 * r.enUS: wObtain
	 * r: wAttribuer
	 * r.enUS: wAttribute
	 * r: wDefinir
	 * r.enUS: wDefine
	 * r: wSauvegarder
	 * r.enUS: wSave
	 * r: classeCheminGen
	 * r.enUS: classPathGen
	 * r: classeVarCleUnique
	 * r.enUS: classVarUniqueKey
	 * r: entiteIndice
	 * r.enUS: entityIndex
	 * r: definirPourClasse
	 * r.enUS: defineForClass
	 * r: codeStocker
	 * r.enUS: codeStore
	 * r: siteEcrireMethode
	 * r.enUS: siteWriteMethod
	 * r: classeEcrireMethode
	 * r.enUS: classWriteMethod
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: strCommentaire
	 * r.enUS: strComment
	 * r: classeEcrireMethode
	 * r.enUS: classWriteMethod
	 * r: entiteEcrireMethode
	 * r.enUS: entityWriteMethod
	 * r: classeSuperEcrireMethode
	 * r.enUS: classSuperWriteMethod
	 * r: classeEcrireMethode
	 * r.enUS: classWriteMethod
	 * r: classeEcrireEcrivain
	 * r.enUS: classWriteWriter
	 * r: langueNom
	 * r.enUS: languageName
	 * r: nomSimple
	 * r.enUS: simpleName
	 * r: siteChemin
	 * r.enUS: sitePath
	 * r: configChemin
	 * r.enUS: configPath
	 * r: siteUrlBase
	 * r.enUS: siteBaseUrl
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: classeGenPageChemin
	 * r.enUS: classPagePathGen
	 * r: classePageCheminCss
	 * r.enUS: classPagePathCss
	 * r: classePageCheminJs
	 * r.enUS: classPagePathJs
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
	 * r: indexerPourClasse
	 * r.enUS: indexForClass
	 * r: stockerPourClasse
	 * r.enUS: storeForClass
	 * 
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: DocumentSolr
	 * r.enUS: SolrDocument
	 * r: pageRecapituler
	 * r.enUS: pageReview
	 * r: ImageLargeur
	 * r.enUS: ImageWidth
	 * r: ImageHauteur
	 * r.enUS: ImageHeight
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: sauvegarder
	 * r.enUS: save
	 * r: peupler
	 * r.enUS: populate
	 * r: stocker
	 * r.enUS: store
	 * r: requeteJson
	 * r.enUS: requestJson
	 * r: attribuer
	 * r.enUS: attribute
	 * r: obtenir
	 * r.enUS: obtain
	 * r: ClientSolr
	 * r.enUS: SolrClient
	 * r: clientSolr
	 * r.enUS: solrClient
	 * r.enUS: SiteConfig
	 * r: desindexer
	 * r.enUS: unindex
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: Ecrire: 
	 * r.enUS: Write: 
	 * r: classe
	 * r.enUS: class
	 * r: sauvegardes
	 * r.enUS: saves
	 * r: definir
	 * r.enUS: define
	 * r: executeur
	 * r.enUS: executor
	 * r: indexer
	 * r.enUS: index
	 * r: Avant
	 * r.enUS: Before
	 * r: Milieu
	 * r.enUS: Middle
	 * r: Apres
	 * r.enUS: After
	 */
	public void genCodeClasseFin(String langueNom, YAMLConfiguration langueConfig) throws Exception {

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
					wInitLoin.l("public Future<Void> ", langueConfig.getString(ConfigCles.var_promesseLoin), langueConfig.getString(ConfigCles.var_PourClasse), "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_) {");
					wInitLoin.tl(2, "return ", langueConfig.getString(ConfigCles.var_promesseLoin), classeNomSimple, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
					wInitLoin.tl(1, "}");  
				} else {
					wInitLoin.s("public void ", langueConfig.getString(ConfigCles.var_initLoin), langueConfig.getString(ConfigCles.var_PourClasse), "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_)");
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
					wInitLoin.tl(2, langueConfig.getString(ConfigCles.var_initLoin), classeNomSimple, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
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
			tl(1, "public void ", langueConfig.getString(ConfigCles.var_requeteSite), langueConfig.getString(ConfigCles.var_PourClasse), "(", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_) {");
			tl(2, langueConfig.getString(ConfigCles.var_requeteSite), classeNomSimple, "(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
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
				tl(4, "return super.", langueConfig.getString(ConfigCles.var_obtenir), classeNomSimpleSuperGenerique, "(var);");

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
				tl(4, "return super.", langueConfig.getString(ConfigCles.var_attribuer), classeNomSimpleSuperGenerique, "(var, val);");

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
		wClasseNomSimpleMethode.flushClose();
		wHtmlColonneMethode.flushClose();
		wHtmlLigneMethode.flushClose();
		wHtmlCelluleMethode.flushClose();
		wLongeurMinMethode.flushClose();
		wLongeurMaxMethode.flushClose();
		wMinMethode.flushClose();
		wMaxMethode.flushClose();
		wPageEntites.flushClose();
		wPageGet.flushClose();
		wHashCode.flushClose();
		wToString.flushClose();
		wEquals.flushClose();

		o = auteurGenClasse;

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
			s("public static Object staticSet", langueConfig.getString(ConfigCles.var_PourClasse), "(String ", langueConfig.getString(ConfigCles.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o)");
			l(" {");
			tl(2, "return staticSet", classeNomSimple, "(", langueConfig.getString(ConfigCles.var_entite), "Var,  ", langueConfig.getString(ConfigCles.var_requeteSite), "_, o);");
			tl(1, "}");
			t(1);
			s("public static Object staticSet", classeNomSimple, "(String ", langueConfig.getString(ConfigCles.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o)");
			l(" {");
			tl(2, "switch(", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			s(wStaticSet.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSet", classeNomSimpleSuperGenerique, "(", langueConfig.getString(ConfigCles.var_entite), "Var,  ", langueConfig.getString(ConfigCles.var_requeteSite), "_, o);");

			tl(2, "}");
			tl(1, "}");

			l(); 
			tl(1, "////////////////");
			tl(1, "// staticSearch //");
			tl(1, "////////////////");
			tl(0);
			t(1);
			s("public static Object staticSearch", langueConfig.getString(ConfigCles.var_PourClasse), "(String ", langueConfig.getString(ConfigCles.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, Object o)");
			l(" {");
			tl(2, "return staticSearch", classeNomSimple, "(", langueConfig.getString(ConfigCles.var_entite), "Var,  ", langueConfig.getString(ConfigCles.var_requeteSite), "_, o);");
			tl(1, "}");
			t(1);
			s("public static Object staticSearch", classeNomSimple, "(String ", langueConfig.getString(ConfigCles.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, Object o)");
			l(" {");
			tl(2, "switch(", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			s(wstaticSearch.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSearch", classeNomSimpleSuperGenerique, "(", langueConfig.getString(ConfigCles.var_entite), "Var,  ", langueConfig.getString(ConfigCles.var_requeteSite), "_, o);");

			tl(2, "}");
			tl(1, "}");

			l(); 
			tl(1, "///////////////////");
			tl(1, "// staticSearchStr //");
			tl(1, "///////////////////");
			tl(0);
			t(1);
			s("public static String staticSearchStr", langueConfig.getString(ConfigCles.var_PourClasse), "(String ", langueConfig.getString(ConfigCles.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, Object o)");
			l(" {");
			tl(2, "return staticSearchStr", classeNomSimple, "(", langueConfig.getString(ConfigCles.var_entite), "Var,  ", langueConfig.getString(ConfigCles.var_requeteSite), "_, o);");
			tl(1, "}");
			t(1);
			s("public static String staticSearchStr", classeNomSimple, "(String ", langueConfig.getString(ConfigCles.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, Object o)");
			l(" {");
			tl(2, "switch(", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			s(wstaticSearchStr.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSearchStr", classeNomSimpleSuperGenerique, "(", langueConfig.getString(ConfigCles.var_entite), "Var,  ", langueConfig.getString(ConfigCles.var_requeteSite), "_, o);");

			tl(2, "}");
			tl(1, "}");

			l(); 
			tl(1, "//////////////////");
			tl(1, "// staticSearchFq //");
			tl(1, "//////////////////");
			tl(0);
			t(1);
			s("public static String staticSearchFq", langueConfig.getString(ConfigCles.var_PourClasse), "(String ", langueConfig.getString(ConfigCles.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o)");
			l(" {");
			tl(2, "return staticSearchFq", classeNomSimple, "(", langueConfig.getString(ConfigCles.var_entite), "Var,  ", langueConfig.getString(ConfigCles.var_requeteSite), "_, o);");
			tl(1, "}");
			t(1);
			s("public static String staticSearchFq", classeNomSimple, "(String ", langueConfig.getString(ConfigCles.var_entite), "Var, ", classePartsRequeteSite.getEtendBase() ? classePartsRequeteSite.getNomSimpleSuperGenerique() : classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_, String o)");
			l(" {");
			tl(2, "switch(", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			s(wstaticSearchFq.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSearchFq", classeNomSimpleSuperGenerique, "(", langueConfig.getString(ConfigCles.var_entite), "Var,  ", langueConfig.getString(ConfigCles.var_requeteSite), "_, o);");

			tl(2, "}");
			tl(1, "}");
		}

		if((classeApi || classePartsModeleBase != null && classeEntiteClassesSuperEtMoiSansGen.contains(classePartsModeleBase.nomCanonique(langueNomGlobale))) && classeInitLoin && (classeEtendBase || classeEstBase)) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", langueConfig.getString(ConfigCles.var_definir), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			s("public boolean ", langueConfig.getString(ConfigCles.var_definir), langueConfig.getString(ConfigCles.var_PourClasse), "(String var, Object val)");
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
			tl(5, "o = ", langueConfig.getString(ConfigCles.var_definir), "", classeNomSimple, "(v, val);");
			if(activerVertx && classePartsModeleBase != null) {
				tl(4, "else if(o instanceof ", classePartsModeleBase.nomSimple(langueNom), ") {");
				tl(5, classePartsModeleBase.nomSimple(langueNom), " o", classePartsModeleBase.nomSimple(langueNom), " = (", classePartsModeleBase.nomSimple(langueNom), ")o;");
				tl(5, "o = o", classePartsModeleBase.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_definir), langueConfig.getString(ConfigCles.var_PourClasse), "(v, val);");
				tl(4, "}");
			}
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object ", langueConfig.getString(ConfigCles.var_definir), "", classeNomSimple, "(String var, Object val) {");
			tl(2, "switch(var.toLowerCase()) {");
			s(wDefinirObjet.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return super.", langueConfig.getString(ConfigCles.var_definir), "", classeNomSimpleSuperGenerique, "(var, val);");

			tl(2, "}");
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
			tl(1, "// ", langueConfig.getString(ConfigCles.var_peupler), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(BooleanUtils.isTrue(classeEtendBase))
				s("@Override ");
			l("public void ", langueConfig.getString(ConfigCles.var_peupler), langueConfig.getString(ConfigCles.var_PourClasse), "(SolrResponse.Doc doc) {");
			tl(2, langueConfig.getString(ConfigCles.var_peupler), classeNomSimple, "(doc);");
			tl(1, "}");
			tl(1, "public void ", langueConfig.getString(ConfigCles.var_peupler), classeNomSimple, "(SolrResponse.Doc doc) {");
			tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
			tl(2, "", langueConfig.getString(ConfigCles.var_sauvegardes), " = doc.get(\"", langueConfig.getString(ConfigCles.var_sauvegardes), "_docvalues_strings\");");
			tl(2, "if(", langueConfig.getString(ConfigCles.var_sauvegardes), " != null) {");
			s(wPeupler.toString());
			tl(2, "}");
			if(BooleanUtils.isTrue(classeEtendBase)) {
				tl(0);
				tl(2, "super.", langueConfig.getString(ConfigCles.var_peupler), classeNomSimpleSuperGenerique, "(doc);");
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
//			String classeGenPageChemin = (String)classeDoc.get("classeGenPageChemin" + classePageMethode  + "_stored_string");
//			String classePageChemin = (String)classeDoc.get("classePageChemin" + classePageMethode  + "_stored_string");
//			String classePageCheminCss = (String)classeDoc.get("classePageCheminCss" + classePageMethode  + "_stored_string");
//			String classePageCheminJs = (String)classeDoc.get("classePageCheminJs" + classePageMethode  + "_stored_string");
//			String classePageUriMethode = (String)classeDoc.get("classeApiUri" + classePageMethode + "_stored_string");
//			String classePageLangueNom = (String)classeDoc.get("classePageLangueNom" + classePageMethode + "_stored_string");
//			String classePageNomSimple = (String)classeDoc.get("classePageNomSimple" + classePageMethode  + "_stored_string");
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
			tl(1, "public void ", langueConfig.getString(ConfigCles.var_indexer), classeNomSimple, "(JsonObject doc) {");
			s(wIndexer.toString());
			if(classeEtendBase && !classeEstBase) {
				tl(2, "super.", langueConfig.getString(ConfigCles.var_indexer), "", classeNomSimpleSuperGenerique, "(doc);");
				tl(0);
			}
			l("\t}");

			/////////
			// var //
			/////////
			l();
			tl(1, "public static String var", langueConfig.getString(ConfigCles.var_Stocke), classeNomSimple, "(String ", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			tl(2, "switch(", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			s(wVarStocke);
			tl(3, "default:");
			if(classeEstBase)
//				tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", langueConfig.getString(ConfigCles.var_nest_pas_une_entite_Stocke), ". \", ", langueConfig.getString(ConfigCles.var_entite), "Var));");
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".var", langueConfig.getString(ConfigCles.var_Stocke), classeNomSimpleSuperGenerique, "(", langueConfig.getString(ConfigCles.var_entite), "Var);");
			tl(2, "}");
			tl(1, "}");
			l();
			tl(1, "public static String var", langueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(String ", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			tl(2, "switch(", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			s(wVarIndexe);
			tl(3, "default:");
			if(classeEstBase)
//				tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", langueConfig.getString(ConfigCles.var_nest_pas_une_entite_indexe), ". \", ", langueConfig.getString(ConfigCles.var_entite), "Var));");
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".var", langueConfig.getString(ConfigCles.var_Indexe), classeNomSimpleSuperGenerique, "(", langueConfig.getString(ConfigCles.var_entite), "Var);");
			tl(2, "}");
			tl(1, "}");

			l();
			tl(1, "public static String var", langueConfig.getString(ConfigCles.var_Recherche), classeNomSimple, "(String ", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			tl(2, "switch(", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			s(wVarRecherche);
			s(wVarSuggere);
			tl(3, "default:");
			if(classeEstBase)
//				tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", langueConfig.getString(ConfigCles.var_nest_pas_une_entite_indexe), ". \", ", langueConfig.getString(ConfigCles.var_entite), "Var));");
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".var", langueConfig.getString(ConfigCles.var_Recherche), classeNomSimpleSuperGenerique, "(", langueConfig.getString(ConfigCles.var_entite), "Var);");
			tl(2, "}");
			tl(1, "}");

			l();
			tl(1, "public static String var", langueConfig.getString(ConfigCles.var_Suggere), classeNomSimple, "(String ", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			tl(2, "switch(", langueConfig.getString(ConfigCles.var_entite), "Var) {");
			s(wVarSuggere);
			tl(3, "default:");
			if(classeEstBase)
//				tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", langueConfig.getString(ConfigCles.var_nest_pas_une_entite_indexe), ". \", ", langueConfig.getString(ConfigCles.var_entite), "Var));");
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".var", langueConfig.getString(ConfigCles.var_Suggere), classeNomSimpleSuperGenerique, "(", langueConfig.getString(ConfigCles.var_entite), "Var);");
			tl(2, "}");
			tl(1, "}");
		}

		/////////////////
		// codeStocker //
		/////////////////
		if(classeIndexe) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", langueConfig.getString(ConfigCles.var_stocker), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(BooleanUtils.isTrue(classeEtendBase))
				s("@Override ");
			l("public void ", langueConfig.getString(ConfigCles.var_stocker), langueConfig.getString(ConfigCles.var_PourClasse), "(SolrResponse.Doc doc) {");
			if(classeIndexe) {
			tl(2, langueConfig.getString(ConfigCles.var_stocker), classeNomSimple, "(doc);");
			}
			tl(1, "}");
			tl(1, "public void ", langueConfig.getString(ConfigCles.var_stocker), classeNomSimple, "(SolrResponse.Doc doc) {");
			tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
			l();
			s(wStocker.toString());
			if(BooleanUtils.isTrue(classeEtendBase)) {
				tl(0);
				tl(2, "super.", langueConfig.getString(ConfigCles.var_stocker), classeNomSimpleSuperGenerique, "(doc);");
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
				tl(1, "// ", langueConfig.getString(ConfigCles.var_requeteApi), " //");
				tl(1, "//////////////////");
				l();
				tl(1, "public void ", langueConfig.getString(ConfigCles.var_requeteApi), classeNomSimple, "() {");
				tl(2, langueConfig.getString(ConfigCles.var_RequeteApi), " ", langueConfig.getString(ConfigCles.var_requeteApi), " = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteSite), "_).map(r -> r.get", langueConfig.getString(ConfigCles.var_RequeteApi), "_()).orElse(null);");
				tl(2, "Object o = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteApi), ").map(", langueConfig.getString(ConfigCles.var_RequeteApi), "::getOriginal).orElse(null);");
				tl(2, "if(o != null && o instanceof ", classeNomSimple, ") {");
				tl(3, classeNomSimple, " original = (", classeNomSimple, ")o;");
				s(wRequeteApi.toString());
				if(BooleanUtils.isTrue(classeEtendBase)) {
					tl(3, "super.", langueConfig.getString(ConfigCles.var_requeteApi), classeNomSimpleSuperGenerique, "();");
				}
				tl(2, "}");
				tl(1, "}");
			}
			else {
				System.err.println(String.format("%s %s %s %s %s. ", langueConfig.getString(ConfigCles.var_classe), langueConfig.getString(ConfigCles.var_RequeteSite), langueConfig.getString(ConfigCles.var_manquante), langueConfig.getString(ConfigCles.var_dans), cheminSrcMainJava));
			}
		}
//
//		//////////////
//		// hashCode //
//		//////////////
//		l(); 
//		tl(1, "//////////////");
//		tl(1, "// hashCode //");
//		tl(1, "//////////////");
//		l();
//		tl(1, "@Override public int hashCode() {");
//		t(2, "return Objects.hash(");
//		if(BooleanUtils.isTrue(classeEtendBase)) {
//			s("super.hashCode()");
//			if(entiteIndice > 0)
//				s(", ");
//		}
//		s(wHashCode.toString());
//		l(");");
//		tl(1, "}");
//
//		////////////
//		// equals //
//		////////////
//		l(); 
//		tl(1, "////////////");
//		tl(1, "// equals //");
//		tl(1, "////////////");
//		l();
//		tl(1, "@Override public boolean equals(Object o) {");
//		tl(2, "if(this == o)");
//		tl(3, "return true;");
//		tl(2, "if(!(o instanceof ", classeNomSimple, "))");
//		tl(3, "return false;");
//		tl(2, classeNomSimple, " that = (", classeNomSimple, ")o;");
//		t(2, "return ");
//		if(BooleanUtils.isTrue(classeEtendBase)) {
//			s("super.equals(o)");
//			if(entiteIndice > 0) {
//				l();
//				t(4, "&& ");
//			}
//		}
//		s(wEquals.toString());
//		if(!BooleanUtils.isTrue(classeEtendBase) && entiteIndice == 0)
//			s("true");
//		l(";");
//		tl(1, "}");

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
		tl(1, "public static final String ", langueConfig.getString(ConfigCles.var_CLASSE_NOM_SIMPLE), " = \"", classeNomSimple, "\";");
		s(wVarsStatic);

		if(classeIndexe) {
			////////////
			// varsQ //
			////////////
	
			l();
			tl(1, "public static List<String> varsQ", langueConfig.getString(ConfigCles.var_PourClasse), "() {");
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
			tl(1, "public static List<String> varsFq", langueConfig.getString(ConfigCles.var_PourClasse), "() {");
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
			tl(1, "public static List<String> vars", langueConfig.getString(ConfigCles.var_Gamme), langueConfig.getString(ConfigCles.var_PourClasse), "() {");
			tl(2, "return ", classeNomSimple, ".vars", langueConfig.getString(ConfigCles.var_Gamme), classeNomSimple, "(new ArrayList<String>());");
			tl(1, "}");
			tl(1, "public static List<String> vars", langueConfig.getString(ConfigCles.var_Gamme), classeNomSimple, "(List<String> vars) {");
			s(wVarsGamme);
			if(!classeEstBase)
				tl(2, classeNomSimpleSuperGenerique, ".vars", langueConfig.getString(ConfigCles.var_Gamme), classeNomSimpleSuperGenerique, "(vars);");
			tl(2, "return vars;");
			tl(1, "}");
		}

		////////////////////////
		// nomAffichageStatic //
		////////////////////////

		l();
		s(wNomAffichageStatic);

		l();
		tl(1, "public static String ", langueConfig.getString(ConfigCles.var_nomAffichage), langueConfig.getString(ConfigCles.var_PourClasse), "(String var) {");
		tl(2, "return ", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_nomAffichage), classeNomSimple, "(var);");
		tl(1, "}");
		tl(1, "public static String ", langueConfig.getString(ConfigCles.var_nomAffichage), classeNomSimple, "(String var) {");
		tl(2, "switch(var) {");
		s(wNomAffichageMethode.toString());
		tl(2, "default:");

		if(classeEstBase)
			tl(3, "return null;");
		else
			tl(3, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(ConfigCles.var_nomAffichage), classeNomSimpleSuperGenerique, "(var);");
		tl(2, "}");
		tl(1, "}");

		if(classePage) {
	
			/////////////////
			// Description //
			/////////////////
			l();
			tl(1, "public static String ", langueConfig.getString(ConfigCles.var_description), classeNomSimple, "(String var) {");
			tl(2, "switch(var) {");
			s(wDescriptionMethode);
			tl(3, "default:");
			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(ConfigCles.var_description), classeNomSimpleSuperGenerique, "(var);");
			tl(2, "}");
			tl(1, "}");
	
			/////////////////////
			// ClasseNomSimple //
			/////////////////////
			l();
			tl(1, "public static String ", langueConfig.getString(ConfigCles.var_classeNomSimple), classeNomSimple, "(String var) {");
			tl(2, "switch(var) {");
			s(wClasseNomSimpleMethode);
			tl(3, "default:");
			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(ConfigCles.var_classeNomSimple), classeNomSimpleSuperGenerique, "(var);");
			tl(2, "}");
			tl(1, "}");
	
			/////////////////
			// HtmlColonne //
			/////////////////
			l();
			tl(1, "public static Integer ", langueConfig.getString(ConfigCles.var_htmlColonne), classeNomSimple, "(String var) {");
			tl(2, "switch(var) {");
			s(wHtmlColonneMethode);
			tl(3, "default:");
			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(ConfigCles.var_htmlColonne), classeNomSimpleSuperGenerique, "(var);");
			tl(2, "}");
			tl(1, "}");
	
			///////////////
			// HtmlLigne //
			///////////////
			l();
			tl(1, "public static Integer ", langueConfig.getString(ConfigCles.var_htmlLigne), classeNomSimple, "(String var) {");
			tl(2, "switch(var) {");
			s(wHtmlLigneMethode);
			tl(3, "default:");
			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(ConfigCles.var_htmlLigne), classeNomSimpleSuperGenerique, "(var);");
			tl(2, "}");
			tl(1, "}");
	
			/////////////////
			// HtmlCellule //
			/////////////////
			l();
			tl(1, "public static Integer ", langueConfig.getString(ConfigCles.var_htmlCellule), classeNomSimple, "(String var) {");
			tl(2, "switch(var) {");
			s(wHtmlCelluleMethode);
			tl(3, "default:");
			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(ConfigCles.var_htmlCellule), classeNomSimpleSuperGenerique, "(var);");
			tl(2, "}");
			tl(1, "}");
	
			////////////////
			// LongeurMin //
			////////////////
			l();
			tl(1, "public static Integer ", langueConfig.getString(ConfigCles.var_longeurMin), classeNomSimple, "(String var) {");
			tl(2, "switch(var) {");
			s(wLongeurMinMethode);
			tl(3, "default:");
			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(ConfigCles.var_longeurMin), classeNomSimpleSuperGenerique, "(var);");
			tl(2, "}");
			tl(1, "}");
	
			////////////////
			// LongeurMax //
			////////////////
			l();
			tl(1, "public static Integer ", langueConfig.getString(ConfigCles.var_longeurMax), classeNomSimple, "(String var) {");
			tl(2, "switch(var) {");
			s(wLongeurMaxMethode);
			tl(3, "default:");
			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(ConfigCles.var_longeurMax), classeNomSimpleSuperGenerique, "(var);");
			tl(2, "}");
			tl(1, "}");
	
			/////////
			// Max //
			/////////
			l();
			tl(1, "public static Integer ", langueConfig.getString(ConfigCles.var_max), classeNomSimple, "(String var) {");
			tl(2, "switch(var) {");
			s(wMaxMethode);
			tl(3, "default:");
			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(ConfigCles.var_max), classeNomSimpleSuperGenerique, "(var);");
			tl(2, "}");
			tl(1, "}");
	
			/////////
			// Min //
			/////////
			l();
			tl(1, "public static Integer ", langueConfig.getString(ConfigCles.var_min), classeNomSimple, "(String var) {");
			tl(2, "switch(var) {");
			s(wMinMethode);
			tl(3, "default:");
			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".", langueConfig.getString(ConfigCles.var_min), classeNomSimpleSuperGenerique, "(var);");
			tl(2, "}");
			tl(1, "}");
		}

		l("}"); 

		System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classeCheminGen); 
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
}
