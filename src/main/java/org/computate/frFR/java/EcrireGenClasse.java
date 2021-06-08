package org.computate.frFR.java;      

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

import com.fasterxml.jackson.annotation.JsonFormat;

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
	protected ToutEcrivain wStaticSolr;
	protected ToutEcrivain wStaticSolrStr;
	protected ToutEcrivain wStaticSolrFq;

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

	/**
	 * Var.enUS: wApiEntities
	 */
	protected ToutEcrivain wApiEntites;

	/**
	 * Var.enUS: wPageEntities
	 */
	protected ToutEcrivain wPageEntites;

	protected ToutEcrivain wPageGet;

	protected ToutEcrivain wHashCode;

	protected ToutEcrivain wRequeteApi;

	protected ToutEcrivain wToString;

	protected ToutEcrivain wEquals;

	/**
	 * Var.enUS: entityVar
	 */
	protected String entiteVar;

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

	/**
	 * Var.enUS: entityWrap
	 */
	protected Boolean entiteCouverture;

	/**
	 * Var.enUS: entityInitialized
	 */
	protected Boolean entiteInitialise;

	/**
	 * Var.enUS: entityInitDeep
	 */
	protected Boolean entiteInitLoin;
	
	/**
	 * Var.enUS: writerGenClass
	 */
	protected ToutEcrivain auteurGenClasse;

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

	/**
	 * Var.enUS: entityIndexed
	 */
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

	/**
	 * Var.enUS: entityStored
	 */
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
	ToutEcrivain wVarRecherche;
	ToutEcrivain wVarSuggere;
	
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
		wStaticSolr = ToutEcrivain.create();
		wStaticSolrStr = ToutEcrivain.create();
		wStaticSolrFq = ToutEcrivain.create();
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
		wApiEntites = ToutEcrivain.create();
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
		wVarRecherche = ToutEcrivain.create();
		wVarSuggere = ToutEcrivain.create();
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
	 * r: dejaInitialise
	 * r.enUS: alreadyInitialized
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
	public void genCodeInitLoin(String langueNom) throws Exception {
		if(BooleanUtils.isTrue(classeInitLoin) && classePartsRequeteSite != null) {
			wInitLoin.l(); 
			wInitLoin.tl(1, "//////////////");
			wInitLoin.tl(1, "// ", str_initLoin(langueNom), " //");
			wInitLoin.tl(1, "//////////////");
			wInitLoin.l(); 
			wInitLoin.tl(1, "protected boolean ", str_dejaInitialise(langueNom), classeNomSimple, " = false;");

			wInitLoin.l();
			if(classePromesse) {
				wInitLoin.tl(1, "public Future<Void> ", str_promesseLoin(langueNom), classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_) {");
				if(classeContientRequeteSite)
					wInitLoin.tl(2, "set", str_RequeteSite(langueNom), "_(", str_requeteSite(langueNom), "_);");
				wInitLoin.tl(2, "if(!", str_dejaInitialise(langueNom), classeNomSimple, ") {");
				wInitLoin.tl(3, str_dejaInitialise(langueNom), classeNomSimple, " = true;");
				wInitLoin.tl(3, "return ", str_promesseLoin(langueNom), classeNomSimple, "();");
				wInitLoin.tl(2, "} else {");
				wInitLoin.tl(3, "return Future.succeededFuture();");
				wInitLoin.tl(2, "}");
				wInitLoin.tl(1, "}");
			} else {
				wInitLoin.t(1, "public ", classeNomSimple, " ", str_initLoin(langueNom), classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_)");
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
					wInitLoin.tl(2, "set", str_RequeteSite(langueNom), "_(", str_requeteSite(langueNom), "_);");
				wInitLoin.tl(2, "if(!", str_dejaInitialise(langueNom), classeNomSimple, ") {");
				wInitLoin.tl(3, str_dejaInitialise(langueNom), classeNomSimple, " = true;");
				wInitLoin.tl(3, str_initLoin(langueNom), classeNomSimple, "();");
				wInitLoin.tl(2, "}");
				wInitLoin.tl(2, "return (", classeNomSimple, ")this;");
				wInitLoin.tl(1, "}");
			}

			if(classePromesse) {
				wInitLoin.l();
				wInitLoin.tl(1, "public Future<Void> ", str_promesseLoin(langueNom), classeNomSimple, "() {");
				wInitLoin.tl(2, "Promise<Void> promise = Promise.promise();");
				wInitLoin.tl(2, "Promise<Void> promise2 = Promise.promise();");
				wInitLoin.tl(2, str_promesse(langueNom), classeNomSimple, "(promise2);");
				wInitLoin.tl(2, "promise2.future().onSuccess(a -> {");
				if(BooleanUtils.isTrue(classeEtendBase)) {
					wInitLoin.tl(3, "super.", str_promesseLoin(langueNom), classeNomSimpleSuperGenerique, "(", str_requeteSite(langueNom), "_).onSuccess(b -> {");
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
				wInitLoin.t(1, "public void ", str_initLoin(langueNom), classeNomSimple, "()");
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
					wInitLoin.tl(2, "super.", str_initLoin(langueNom), classeNomSimpleSuperGenerique, "(", str_requeteSite(langueNom), "_);");
				wInitLoin.tl(1, "}");
			}

			wInitLoin.l();
			if(classePromesse) {
				wInitLoin.tl(1, "public Future<Void> ", str_promesse(langueNom), classeNomSimple, "(Promise<Void> promise) {");
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
	public void genCodeRequeteSite(String langueNom) throws Exception {
		if(BooleanUtils.isTrue(classeContientRequeteSite) && classePartsRequeteSite != null) {
			o = wRequeteSite;
			l(); 
			tl(1, "/////////////////");
			tl(1, "// ", str_requeteSite(langueNom), " //");
			tl(1, "/////////////////");
			l(); 
			tl(1, "public void ", str_requeteSite(langueNom), classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_) {");
			if(BooleanUtils.isTrue(classeEtendBase)) 
				tl(3, "super.", str_requeteSite(langueNom), classeNomSimpleSuperGenerique, "(", str_requeteSite(langueNom), "_);");
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
	public void genCodeObtenir(String langueNom) throws Exception {
		o = wObtenir;
		if(classeInitLoin && (classeEtendBase || classeEstBase)) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", str_obtenir(langueNom), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			l("public Object ", str_obtenir(langueNom), str_PourClasse(langueNom), "(String var) {");
			tl(2, "String[] vars = StringUtils.split(var, \".\");");
			tl(2, "Object o = null;");
			tl(2, "for(String v : vars) {");
			tl(3, "if(o == null)");
			tl(4, "o = ", str_obtenir(langueNom), classeNomSimple, "(v);");
			tl(3, "else if(o instanceof ", classePartsCluster.nomSimple(langueNom), ") {");
			tl(4, classePartsCluster.nomSimple(langueNom), " ", StringUtils.uncapitalize(classePartsCluster.nomSimple(langueNom)), " = (", classePartsCluster.nomSimple(langueNom), ")o;");
			tl(4, "o = ", StringUtils.uncapitalize(classePartsCluster.nomSimple(langueNom)), ".", str_obtenir(langueNom), str_PourClasse(langueNom), "(v);");
			tl(3, "}");
			tl(3, "else if(o instanceof Map) {");
			tl(4, "Map<?, ?> map = (Map<?, ?>)o;");
			tl(4, "o = map.get(v);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o;");
			tl(1, "}");
			tl(1, "public Object ", str_obtenir(langueNom), classeNomSimple, "(String var) {");
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
	public void genCodeAttribuer(String langueNom) throws Exception {
		o = wAttribuer;
		if(classeInitLoin && (classeEtendBase || classeEstBase)) {
			l(); 
			tl(1, "///////////////");
			tl(1, "// ", str_attribuer(langueNom), " //");
			tl(1, "///////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			s("public boolean ", str_attribuer(langueNom), str_PourClasse(langueNom), "(String var, Object val)");
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
			tl(4, "o = ", str_attribuer(langueNom), classeNomSimple + "(v, val);");
			tl(3, "else if(o instanceof ", classePartsCluster.nomSimple(langueNom), ") {");
			tl(4, classePartsCluster.nomSimple(langueNom), " ", StringUtils.uncapitalize(classePartsCluster.nomSimple(langueNom)), " = (", classePartsCluster.nomSimple(langueNom), ")o;");
			tl(4, "o = ", StringUtils.uncapitalize(classePartsCluster.nomSimple(langueNom)), ".", str_attribuer(langueNom), str_PourClasse(langueNom), "(v, val);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object ", str_attribuer(langueNom), classeNomSimple, "(String var, Object val) {");
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
	public void genCodePut(String langueNom) throws Exception {
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
			l("public void ", str_put(langueNom), str_PourClasse(langueNom), "(JsonObject requeteJson) {");
			tl(2, "Set<String> vars = requeteJson.fieldNames();");
			tl(2, "for(String var : vars) {");
			tl(3, "put", classeNomSimple + "(requeteJson, var);");
			tl(2, "}");
			tl(1, "}");
			l();
			t(1);
			if(!classeEstBase)
				s("@Override ");
			l("public Boolean ", str_put(langueNom), classeNomSimple, "(JsonObject requeteJson, String var) {");
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
	 * r: solrDocument
	 * r.enUS: solrDocument
	 * 
	 * r: peupler
	 * r.enUS: populate
	 * r: sauvegardes
	 * r.enUS: saves
	 */
	public void genCodePeupler(String langueNom) throws Exception {
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
	public void genCodeClasseDebut(String langueNom) throws Exception {
		o = auteurGenClasse;

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
			tl(0, " * <br/><a href=\"", solrUrlComputate, "/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(classeNomCanonique), "&fq=classeEtendGen_indexed_boolean:true\">", str_Trouver_la_classe_(langueNom), entiteVar, str__dans_Solr(langueNom), ". </a>");
			tl(0, " * <br/>");
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
		if(classeMotsCles.contains(str_classeNomSimple(langueNom) + "Verticle")) {
			ToutEcrivain auteurSqlDrop = ToutEcrivain.create();

			l();
			l("/*");
			{
				SolrQuery rechercheSolr1 = new SolrQuery();
				rechercheSolr1.setQuery("*:*");
				rechercheSolr1.setRows(1000);
				rechercheSolr1.addFilterQuery("appliNom_indexed_string:" + ClientUtils.escapeQueryChars(appliNom));
				rechercheSolr1.addFilterQuery("partEstClasse_indexed_boolean:true");
				rechercheSolr1.addFilterQuery("classeSauvegarde_indexed_boolean:true");
				rechercheSolr1.addSort("sqlSort_indexed_int", ORDER.asc);
	
				QueryResponse reponseRecherche1 = clientSolrComputate.query(rechercheSolr1);
				SolrDocumentList listeRecherche1 = reponseRecherche1.getResults();
				for(Integer i = 0; i < listeRecherche1.size(); i++) {
					SolrDocument doc1 = listeRecherche1.get(i);
	
					List<String> classeEntiteClassesSuperEtMoiSansGen = (List<String>)doc1.get("entiteClassesSuperEtMoiSansGen_stored_strings");
					String fqClassesSuperEtMoi = "(" + classeEntiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
	
					SolrQuery rechercheSolr2 = new SolrQuery();   
					rechercheSolr2.setQuery("*:*");
					rechercheSolr2.setRows(1000);
					rechercheSolr2.addSort("classeEstBase_indexed_boolean", ORDER.desc);
					rechercheSolr2.addSort("partNumero_indexed_int", ORDER.asc);
					rechercheSolr2.addFilterQuery("appliNom_indexed_string:" + ClientUtils.escapeQueryChars(appliNom));
					rechercheSolr2.addFilterQuery("classeNomCanonique_" + langueNom + "_indexed_string:" + fqClassesSuperEtMoi);
					rechercheSolr2.addFilterQuery("partEstEntite_indexed_boolean:true");
					rechercheSolr2.addFilterQuery("-entiteTypeJson_indexed_string:array");
					rechercheSolr2.addFilterQuery("(entiteAttribuer_indexed_boolean:true OR entiteDefinir_indexed_boolean:true OR entiteClePrimaire_indexed_boolean:true)");
	
					QueryResponse reponseRecherche2 = clientSolrComputate.query(rechercheSolr2);
					SolrDocumentList listeRecherche2 = reponseRecherche2.getResults();
	
					String classeNomSimple = (String)doc1.get("classeNomSimple_" + langueNom + "_stored_string");
					auteurSqlDrop.l("DROP TABLE ", classeNomSimple, " CASCADE;");
					l("CREATE TABLE ", classeNomSimple, "(");
					for(Integer j = 0; j < listeRecherche2.size(); j++) {
						SolrDocument doc2 = listeRecherche2.get(j);
						if(doc2.get("entiteAttribuerTypeJson_stored_string") != null && (((String)doc2.get("entiteVar_" + langueNom + "_stored_string")).compareTo((String)doc2.get("entiteAttribuerVar_" + langueNom + "_stored_string")) < 0 || "array".equals(doc2.get("entiteAttribuerTypeJson_stored_string"))) || doc2.get("entiteAttribuerTypeJson_stored_string") == null) {
							t(1);
							if(j > 0)
								s(", ");
							s(doc2.get("entiteVar_" + langueNom + "_stored_string"), " ", doc2.get("entiteTypeSql_stored_string"));
							if(doc2.get("entiteAttribuerTypeJson_stored_string") != null)
								s(" references ", (String)doc2.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string"), "(pk)");
							l();
						}
					}
					tl(1, ");");
				}
			}

			{
				SolrQuery rechercheSolr2 = new SolrQuery();   
				rechercheSolr2.setQuery("*:*");
				rechercheSolr2.setRows(1000);
				rechercheSolr2.addFilterQuery("appliNom_indexed_string:" + ClientUtils.escapeQueryChars(appliNom));
				rechercheSolr2.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr2.addFilterQuery("entiteTypeJson_indexed_string:array");
				rechercheSolr2.addFilterQuery("entiteAttribuerTypeJson_indexed_string:array");
	
				QueryResponse reponseRecherche2 = clientSolrComputate.query(rechercheSolr2);
				SolrDocumentList listeRecherche2 = reponseRecherche2.getResults();
	
				for(Integer j = 0; j < listeRecherche2.size(); j++) {
					SolrDocument doc2 = listeRecherche2.get(j);
					String var = (String)doc2.get("entiteVar_" + langueNom + "_stored_string");
					String varAttribuer = (String)doc2.get("entiteAttribuerVar_" + langueNom + "_stored_string");

					if(var.compareTo(varAttribuer) < 0) {
						String c1 = (String)doc2.get("classeNomSimple_" + langueNom + "_stored_string");
						String c2 = (String)doc2.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string");

						auteurSqlDrop.l("DROP TABLE ", c1, StringUtils.capitalize(var), "_", c2, StringUtils.capitalize(varAttribuer), " CASCADE;");
						l("CREATE TABLE ", c1, StringUtils.capitalize(var), "_", c2, StringUtils.capitalize(varAttribuer), "(");
						tl(1, "pk bigserial primary key");
						tl(1, ", pk1 bigint references ", c1, "(pk)");
						tl(1, ", pk2 bigint references ", c2, "(pk)");
						tl(1, ");");
					}
				}
			}
			l();
			s(auteurSqlDrop);
			l("*/");
			l();
		}

//		if(classeSauvegarde) {
		tl(1, "protected static final Logger LOG = LoggerFactory.getLogger(", classeNomSimple, ".class);");
//		}
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
					t(1, "public static final String ", classeValVarAncien, " = ");
					for(int k = 1; k <= classeValVarNumero; k++) {
						if(k > 1)
							s(" + ");
						s(classeValVarAncien, k);
					}
					l(";");
					classeValVarNumero = 0;
				}

				if(StringUtils.equals(langueNom, classeValLangue)) {
					classeValVarNumero++;
					tl(1, "public static final String ", classeValVar, classeValVarNumero, " = \"", escapeJava(classeValValeur), "\";");
					if(!classeVals.getEmpty())
						classeVals.s(", ");
					classeVals.s(classeValVar, classeValVarNumero);
				}

				classeValVarAncien = classeValVar;
				classeValVarLangueAncien = classeValVarLangue;
			}
			if(StringUtils.equals(langueNom, classeValLangue)) {
				classeValVarAncien = classeValVar;
				classeValVarLangueAncien = classeValVarLangue;
				classeValVar = null;
	
				if(classeValVarAncien != null && !StringUtils.equals(classeValVar, classeValVarLangueAncien)) {
					t(1, "public static final String ", classeValVarAncien, " = ");
					for(int k = 1; k <= classeValVarNumero; k++) {
						if(k > 1)
							s(" + ");
						s(classeValVarAncien, k);
					}
					l(";");
					classeValVarNumero = 0;
				}
			}
		}

		if(classeRoleLiresTrouves || classeRolesTrouves) {
			l();
			tl(1, "public static final List<String> ROLES = Arrays.asList(\"", StringUtils.join(classeRoles, "\", \""), "\");");
			tl(1, "public static final List<String> ROLE_READS = Arrays.asList(\"", StringUtils.join(classeRoleLires, "\", \""), "\");");
		}
		
		//////////////
		// Contexte //
		//////////////
		
		if(classeContexte) {

			contexteCouleur = (String)classeDoc.get("contexteCouleur_stored_string");
			contexteIconeGroupe = (String)classeDoc.get("contexteIconeGroupe_stored_string");
			contexteIconeNom = (String)classeDoc.get("contexteIconeNom_stored_string");
			contexteRows = (Integer)classeDoc.get("contexteRows_stored_int");

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
			contexteRechercherTousNomPar = (String)classeDoc.get("contexteRechercherTousNomPar" + "_" + langueNom + "_stored_string");
			contexteRechercherTousNom = (String)classeDoc.get("contexteRechercherTousNom" + "_" + langueNom + "_stored_string");
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
			
			l();
			if(contexteUnNom != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_UnNom(langueNom), " = ", q(contexteUnNom), ";");
			
			if(contexteCe != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_Ce(langueNom), " = ", q(contexteCe), ";");
			
			if(contexteCeNom != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_CeNom(langueNom), " = ", q(contexteCeNom), ";");
			
			if(contexteUn != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_Un(langueNom), " = ", q(contexteUn), ";");
			
			if(contexteLeNom != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_LeNom(langueNom), " = ", q(contexteLeNom), ";");
			
			if(contexteNomSingulier != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_NomSingulier(langueNom), " = ", q(contexteNomSingulier), ";");
			
			if(contexteNomPluriel != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_NomPluriel(langueNom), " = ", q(contexteNomPluriel), ";");
			
			if(contexteNomActuel != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_NomActuel(langueNom), " = ", q(contexteNomActuel), ";");
			
			if(contexteTous != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_Tous(langueNom), " = ", q(contexteTous), ";");
			
			if(contexteTousNom != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_TousNom(langueNom), " = ", q(contexteTousNom), ";");
			
			if(contexteRechercherTousNomPar != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_RechercherTousNomPar(langueNom), " = ", q(contexteRechercherTousNomPar), ";");
			
			if(contexteRechercherTousNom != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_RechercherTousNom(langueNom), " = ", q(contexteRechercherTousNom), ";");
			
			if(contexteH1 != null)
				tl(1, "public static final String ", classeNomSimple, "_H1 = ", q(contexteH1), ";");
			
			if(contexteH2 != null)
				tl(1, "public static final String ", classeNomSimple, "_H2 = ", q(contexteH2), ";");
			
			if(contexteH3 != null)
				tl(1, "public static final String ", classeNomSimple, "_H3 = ", q(contexteH3), ";");
			
			if(contexteTitre != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_Titre(langueNom), " = ", q(contexteTitre), ";");
			
			if(contexteLesNoms != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_LesNoms(langueNom), " = ", q(contexteLesNoms), ";");
			
			if(contexteAucunNomTrouve != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_AucunNomTrouve(langueNom), " = ", q(contexteAucunNomTrouve), ";");
			
			if(contexteNomVar != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_NomVar(langueNom), " = ", q(contexteNomVar), ";");
			
			if(contexteDeNom != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_DeNom(langueNom), " = ", q(contexteDeNom), ";");
			
			if(contexteAdjectif != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_Adjectif(langueNom), " = ", q(contexteAdjectif), ";");
			
			if(contexteAdjectifPluriel != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_AdjectifPluriel(langueNom), " = ", q(contexteAdjectifPluriel), ";");
			
			if(contexteAdjectifVar != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_AdjectifVar(langueNom), " = ", q(contexteAdjectifVar), ";");
			
			if(contexteUnNomAdjectif != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_UnNomAdjectif(langueNom), " = ", q(contexteUnNomAdjectif), ";");
			
			if(contexteNomAdjectifSingulier != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_NomAdjectifSingulier(langueNom), " = ", q(contexteNomAdjectifSingulier), ";");
			
			if(contexteNomAdjectifPluriel != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_NomAdjectifPluriel(langueNom), " = ", q(contexteNomAdjectifPluriel), ";");
			
			if(contexteCouleur != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_Couleur(langueNom), " = ", q(contexteCouleur), ";");
			
			if(contexteIconeGroupe != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_IconeGroupe(langueNom), " = ", q(contexteIconeGroupe), ";");
			
			if(contexteIconeNom != null)
				tl(1, "public static final String ", classeNomSimple, "_", str_IconeNom(langueNom), " = ", q(contexteIconeNom), ";");
			
			if(contexteRows != null)
				tl(1, "public static final Integer ", classeNomSimple, "_", str_Lignes(langueNom), " = ", contexteRows, ";");
		}

		for(String classePageMethode : classeApiMethodes) {

			String classePageUriMethode = (String)classeDoc.get("classeApiUri" + classePageMethode + "_stored_string");
			String classePageNomSimple = (String)doc.get("classePageNomSimple" + classePageMethode  + "_stored_string");
			String classePageCheminGen = (String)classeDoc.get("classePageCheminGen" + classePageMethode  + "_stored_string");
	
			if(classePageNomSimple != null) {
				if(classePageUriMethode != null) {
					tl(1, "public static final String ", classePageNomSimple, "_Uri", " = ", q(classePageUriMethode), ";");
				}
				if(classePageCheminGen != null) {
					tl(1, "public static final String ", classePageNomSimple, "_ImageUri", " = ", q("/png", classePageUriMethode, "-999.png"), ";");
				}
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
	public void genCodeConstructeur(String langueNom) throws Exception {
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
	public void genCodeMethode(String langueNom) throws Exception {

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
					{
						String[] parts = splitByCharacterTypeCamelCase(methodeValVar);
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

//									methodeValsEcrivain.t(1);
								if(StringUtils.equalsAny(element, HTML_ELEMENTS)) {
									html = true;

									String css = methodeVar;
									for(Integer r = 0; r <= xmlPart; r++) {
										String s = parts[r];
										css += s;
									}
									css += " ";

									String cssNumero = numero == null ? "" : (StringUtils.substringBeforeLast(StringUtils.substringBeforeLast(css, numero.toString()), "0") + (numero % 2 == 0 ? " even " : " odd "));

									if(numero == null)
										numero = 1;

									if(methodeXmlPile.size() < (xmlPart + 1)) {
										if("i".equals(element))
											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", ", methodeVar, methodeValVar, methodeValVarNumero, ", \" site-menu-icon ", css, cssNumero, "\").f();");
										else if("a".equals(element))
											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").a(\"href\", ", methodeVar, methodeValVar, methodeValVarNumero, ").f();");
										else if("br".equals(element))
											methodeValsEcrivain.tl(2 + xmlPart, "e(\"", element, "\").fg();");
										else if("td".equals(element))
											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" w3-mobile ", css, cssNumero, "\").f();");
										else
											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").f();");

										if(!"br".equals(element)) {
											methodeXmlPile.push(element);
											methodeNumeroPile.push(numero);
											xmlPart++;
										}
									}
									else if(StringUtils.equals(element, methodeXmlPile.get(xmlPart)) && numero.equals(methodeNumeroPile.get(xmlPart))) {
										xmlPart++;
									}
									else {
										while(methodeXmlPile.size() > xmlPart) {
											methodeValsEcrivain.tl(1 + methodeXmlPile.size(), "} g(\"", methodeXmlPile.peek(), "\");");
											methodeXmlPile.pop();
											methodeNumeroPile.pop();
										}
										if("i".equals(element))
											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", ", methodeVar, methodeValVar, methodeValVarNumero, ", \" site-menu-icon ", css, cssNumero, "\").f();");
										else if("a".equals(element))
											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").a(\"href\", ", methodeVar, methodeValVar, methodeValVarNumero, ").f();");
										else if("br".equals(element))
											methodeValsEcrivain.tl(2 + xmlPart, "e(\"", element, "\").fg();");
										else if("td".equals(element))
											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" w3-mobile ", css, cssNumero, "\").f();");
										else
											methodeValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").f();");

										if(!"br".equals(element)) {
											methodeXmlPile.push(element);
											methodeNumeroPile.push(numero);
											xmlPart++;
										}
									}
								}
							}
						}
						if(html && !"i".equals(methodeXmlPile.peek())) {
							Integer p = methodeXmlPile.size();
							if(StringUtils.isEmpty(methodeValCode)) {
								methodeValsEcrivain.tl(2 + p, "sx(", methodeVar, methodeValVar, methodeValVarNumero, ");");
							}
							else {
								if(classeEntiteVars.contains("utilisateurId"))
									methodeValsEcrivain.tl(2 + p, "sx(utilisateurId == null ? ", methodeVar, methodeValVar, methodeValVarNumero, " : ", methodeValCode, ");");
								else
									methodeValsEcrivain.tl(2 + p, "sx(", str_requeteSite(langueNom), "_.getUtilisateurId() == null ? ", methodeVar, methodeValVar, methodeValVarNumero, " : ", methodeValCode, ");");
							}
						}
					}
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

			for(int q = methodeXmlPile.size() - 1; q >= 0; q--) {
				methodeValsEcrivain.tl(2 + q, "} g(\"", methodeXmlPile.get(q), "\");");
				methodeXmlPile.pop();
			}
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
	 * r: dejaInitialise
	 * r.enUS: alreadyInitialized
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
	 * r: solrDocument
	 * r.enUS: solrDocument
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
	public void genCodeEntite(String langueNom) throws Exception {
		String entiteVar = (String)doc.get("entiteVar_" + langueNom + "_stored_string");
		String entiteSuffixeType = (String)doc.get("entiteSuffixeType_stored_string");
		String entiteVarCapitalise = (String)doc.get("entiteVarCapitalise_" + langueNom + "_stored_string");
		String entiteAttribuerVarSuggere = (String)doc.get("entiteAttribuerVarSuggere_" + langueNom + "_stored_string");
		String entiteNomCanonique = (String)doc.get("entiteNomCanonique_" + langueNom + "_stored_string");
		String entiteNomCanoniqueGenerique = (String)doc.get("entiteNomCanoniqueGenerique_" + langueNom + "_stored_string");
		String entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
		String entiteNomSimpleGenerique = (String)doc.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
		String entiteNomSimpleCompletGenerique = (String)doc.get("entiteNomSimpleCompletGenerique_" + langueNom + "_stored_string");
		String entiteNomSimple = (String)doc.get("entiteNomSimple_" + langueNom + "_stored_string");
		String entiteCommentaire = (String)doc.get("entiteCommentaire_" + langueNom + "_stored_string");
		String entiteVarParam = (String)doc.get("entiteVarParam_" + langueNom + "_stored_string");
		String entiteAttribuerTypeJson = (String)doc.get("entiteAttribuerTypeJson_stored_string");
		Boolean entiteCouverture = (Boolean)doc.get("entiteCouverture_stored_boolean");
		Boolean entitePromesse = (Boolean)doc.get("entitePromesse_stored_boolean");
		Boolean entiteInitialise = (Boolean)doc.get("entiteInitialise_stored_boolean");
		Boolean entiteInitLoin = (Boolean)doc.get("entiteInitLoin_stored_boolean");
		Boolean entiteFacetsTrouves = Optional.ofNullable((Boolean)doc.get("entiteFacetsTrouves_stored_boolean")).orElse(false);
		List<String> methodeExceptionsNomSimpleComplet = (List<String>)doc.get("methodeExceptionsNomSimpleComplet_stored_strings");
		List<String> entiteFacets = Optional.ofNullable((List<String>)doc.get("entiteFacets_stored_strings")).orElse(Arrays.asList());

		if(entiteNomCanonique != null) {
	//		String entiteVarCleUniqueActuel = (String)doc.get("entiteVarCleUnique_stored_string");
	//		if(StringUtils.isNotEmpty(entiteVarCleUniqueActuel))
	//			entiteVarCleUnique = entiteVarCleUniqueActuel;
			String entiteSolrNomCanonique = (String)doc.get("entiteSolrNomCanonique_stored_string");
			String entiteSolrNomSimple = (String)doc.get("entiteSolrNomSimple_stored_string");
			String entiteNomSimpleVertxJson = (String)doc.get("entiteNomSimpleVertxJson_stored_string");
			String entiteNomCanoniqueVertxJson = (String)doc.get("entiteNomCanoniqueVertxJson_stored_string");
			String entiteListeNomSimpleVertxJson = (String)doc.get("entiteListeNomSimpleVertxJson_stored_string");
			String entiteListeNomCanoniqueVertxJson = (String)doc.get("entiteListeNomCanoniqueVertxJson_stored_string");
	
			Boolean entiteExact = (Boolean)doc.get("entiteExact_stored_boolean");
			Boolean entiteClePrimaire = (Boolean)doc.get("entiteClePrimaire_stored_boolean");
			Boolean entiteCleUnique = (Boolean)doc.get("entiteCleUnique_stored_boolean");
			Boolean entiteCrypte = (Boolean)doc.get("entiteCrypte_stored_boolean");
			Boolean entiteSuggere = (Boolean)doc.get("entiteSuggere_stored_boolean");
			Boolean entiteSauvegarde = (Boolean)doc.get("entiteSauvegarde_stored_boolean");
			Boolean entiteIndexe = (Boolean)doc.get("entiteIndexe_stored_boolean");
			Boolean entiteStocke = (Boolean)doc.get("entiteStocke_stored_boolean");
			Boolean entiteTexte = (Boolean)doc.get("entiteTexte_stored_boolean");
			String entiteLangue = (String)doc.get("entiteLangue_stored_string");
			Boolean entiteIncremente = (Boolean)doc.get("entiteIncremente_stored_boolean");
			Boolean entiteIgnorer = (Boolean)doc.get("entiteIgnorer_stored_boolean");
			Boolean entiteSetTrim = (Boolean)doc.get("entiteSetTrim_stored_boolean");
			Boolean entiteSetLower = (Boolean)doc.get("entiteSetLower_stored_boolean");
			Boolean entiteSetUpper = (Boolean)doc.get("entiteSetUpper_stored_boolean");
			Boolean entiteDeclarer = (Boolean)doc.get("entiteDeclarer_stored_boolean");
			Boolean entiteRechercher = (Boolean)doc.get("entiteRechercher_stored_boolean");
			Boolean entiteAttribuer = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuer_stored_boolean"));
			String entiteAttribuerNomCanonique = (String)doc.get("entiteAttribuerNomCanonique_" + langueNom + "_stored_string");
			String entiteAttribuerNomSimple = (String)doc.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string");
			String entiteAttribuerNomCanoniqueGenApiServiceImpl = (String)doc.get("entiteAttribuerNomCanoniqueGenApiServiceImpl_" + langueNom + "_stored_string");
			String entiteAttribuerNomSimpleGenApiServiceImpl = (String)doc.get("entiteAttribuerNomSimpleGenApiServiceImpl_" + langueNom + "_stored_string");
			String entiteAttribuerNomSimpleApiServiceImpl = (String)doc.get("entiteAttribuerNomSimpleApiServiceImpl_" + langueNom + "_stored_string");
			String entiteAttribuerVar = (String)doc.get("entiteAttribuerVar_" + langueNom + "_stored_string");
			String entiteAttribuerVarUrlId = (String)doc.get("entiteAttribuerVarUrlId_" + langueNom + "_stored_string");
			String entiteAttribuerVarUrlPk = (String)doc.get("entiteAttribuerVarUrlPk_" + langueNom + "_stored_string");
			String entiteAttribuerVarId = (String)doc.get("entiteAttribuerVarId_" + langueNom + "_stored_string");
			String entiteAttribuerVarTitre = (String)doc.get("entiteAttribuerVarTitre_" + langueNom + "_stored_string");
			String entiteAttribuerVarDescription = (String)doc.get("entiteAttribuerVarDescription_" + langueNom + "_stored_string");
			String entiteAttribuerVarImageUrl = (String)doc.get("entiteAttribuerVarImageUrl_" + langueNom + "_stored_string");
			Boolean entiteAttribuerUtilisateurEcrire = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuerUtilisateurEcrire_stored_boolean"));
			Boolean entiteAttribuerSessionEcrire = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuerSessionEcrire_stored_boolean"));
			Boolean entiteAttribuerPublicLire = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuerPublicLire_stored_boolean"));
			List<String> entiteAttribuerClasseRoles = (List<String>)doc.get("entiteAttribuerClasseRoles_stored_strings");
			List<String> entiteAttribuerClasseRolesLangue = (List<String>)doc.get("entiteAttribuerClasseRolesLangue_stored_strings");
			Boolean entiteAjouter = (Boolean)doc.get("entiteAjouter_stored_boolean");
			Boolean entiteSupprimer = (Boolean)doc.get("entiteSupprimer_stored_boolean");
			Boolean entiteModifier = (Boolean)doc.get("entiteModifier_stored_boolean");
			Boolean entiteRecharger = (Boolean)doc.get("entiteRecharger_stored_boolean");
			Boolean entiteMultiligne = (Boolean)doc.get("entiteMultiligne_stored_boolean");
			Boolean entiteSignature = (Boolean)doc.get("entiteSignature_stored_boolean");
			String entiteImageBase64Url = (String)doc.get("entiteImageBase64Url_" + langueNom + "_stored_string");
			Boolean entiteCles = (Boolean)doc.get("entiteCles_stored_boolean");
			Boolean entiteIndexeOuStocke = (Boolean)doc.get("entiteIndexeOuStocke_stored_boolean");
			Boolean entiteDefinir = (Boolean)doc.get("entiteDefinir_stored_boolean");
			Boolean entiteContientRequeteSite = BooleanUtils.isTrue((Boolean)doc.get("entiteContientRequeteSite_stored_boolean"));
			String entiteListeTypeJson = (String)doc.get("entiteListeTypeJson_stored_string");
			entiteAttribuerContexteUnNom = (String)doc.get("entiteAttribuerContexteUnNom_" + langueNom + "_stored_string");
			entiteAttribuerContexteNomPluriel = (String)doc.get("entiteAttribuerContexteNomPluriel_" + langueNom + "_stored_string");
			entiteAttribuerContexteCouleur = (String)doc.get("entiteAttribuerContexteCouleur_stored_string");
			entiteAttribuerContexteIconeGroupe = (String)doc.get("entiteAttribuerContexteIconeGroupe_stored_string");
			entiteAttribuerContexteIconeNom = (String)doc.get("entiteAttribuerContexteIconeNom_stored_string");
			entiteAttribuerPageUri = (String)doc.get("entiteAttribuerPageUri_" + langueNom + "_stored_string");
			entiteTypeJson = (String)doc.get("entiteTypeJson_stored_string");
	
			String entiteNomAffichage = (String)doc.get("entiteNomAffichage_" + langueNom + "_stored_string");
			String entiteHtmlTooltip = (String)doc.get("entiteHtmlTooltip_" + langueNom + "_stored_string");
			Boolean entiteHtml = (Boolean)doc.get("entiteHtml_stored_boolean");

			entiteClassesSuperEtMoiSansGen = (List<String>)doc.get("entiteClassesSuperEtMoiSansGen_stored_strings");
	
			List<String> entiteMethodesAvantVisibilite = (List<String>)doc.get("entiteMethodesAvantVisibilite_stored_strings");
			List<String> entiteMethodesAvantVar = (List<String>)doc.get("entiteMethodesAvantVar_" + langueNom + "_stored_strings");
			List<String> entiteMethodesAvantParamVar = (List<String>)doc.get("entiteMethodesAvantParamVar_" + langueNom + "_stored_strings");
			List<String> entiteMethodesAvantParamNomSimple = (List<String>)doc.get("entiteMethodesAvantParamNomSimple_" + langueNom + "_stored_strings");
			List<Boolean> entiteMethodesAvantNomParam = (List<Boolean>)doc.get("entiteMethodesAvantNomParam_stored_booleans");
			List<Boolean> entiteMethodesAvantEcrire = (List<Boolean>)doc.get("entiteMethodesAvantEcrire_stored_booleans");
	
			List<String> entiteMethodesApresVisibilite = (List<String>)doc.get("entiteMethodesApresVisibilite_stored_strings");
			List<String> entiteMethodesApresVar = (List<String>)doc.get("entiteMethodesApresVar_" + langueNom + "_stored_strings");
			List<String> entiteMethodesApresParamVar = (List<String>)doc.get("entiteMethodesApresParamVar_" + langueNom + "_stored_strings");
			List<String> entiteMethodesApresParamNomSimple = (List<String>)doc.get("entiteMethodesApresParamNomSimple_" + langueNom + "_stored_strings");
			List<Boolean> entiteMethodesApresNomParam = (List<Boolean>)doc.get("entiteMethodesApresNomParam_stored_booleans");
			List<Boolean> entiteMethodesApresEcrire = (List<Boolean>)doc.get("entiteMethodesApresEcrire_stored_booleans");

			List<String> entiteEcrireMethodes = (List<String>)doc.get("entiteEcrireMethodes_stored_strings");
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
			List<String> entiteValsVar = (List<String>)doc.get("entiteValsVar_stored_strings");
			List<String> entiteValsLangue = (List<String>)doc.get("entiteValsLangue_stored_strings");
			List<String> entiteValsCode = (List<String>)doc.get("entiteValsCode_stored_strings");
			List<String> entiteValsValeur = (List<String>)doc.get("entiteValsValeur_stored_strings");
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
				for(int j = 0; j < entiteValsVar.size(); j++) {
					entiteValVar = entiteValsVar.get(j);
					entiteValLangue = entiteValsLangue.get(j);
					if(StringUtils.isBlank(entiteValLangue))
						entiteValLangue = langueNom;
					entiteValVarLangue = entiteValVar + entiteValLangue;
					entiteValCode = entiteValsCode.get(j);
					entiteValValeur = entiteValsValeur.get(j);
	
					Integer xmlPart = 0;
					if(!StringUtils.equals(entiteValVarLangue, entiteValVarLangueAncien) && (StringUtils.equals(entiteValVarLangueAncien, entiteValVarAncien + langueNom))) {
						t(1, "public static final String ", entiteVar, entiteValVarAncien, " = ");
						for(int k = 1; k <= entiteValVarNumero; k++) {
							if(k > 1)
								s(" + ");
							s(entiteVar, entiteValVarAncien, k);
						}
						l(";");
						entiteValVarNumero = 0;
					}
	
					if(StringUtils.equals(langueNom, entiteValLangue)) {
						entiteValVarNumero++;
						tl(1, "public static final String ", entiteVar, entiteValVar, entiteValVarNumero, " = \"", escapeJava(entiteValValeur), "\";");
						if(!classeVals.getEmpty())
							classeVals.s(", ");
						classeVals.s(entiteVar, entiteValVar, entiteValVarNumero);
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
												entiteValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", ", entiteVar, entiteValVar, entiteValVarNumero, ", \" site-menu-icon ", css, cssNumero, "\").f();");
											else if("a".equals(element))
												entiteValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").a(\"href\", ", entiteVar, entiteValVar, entiteValVarNumero, ").f();");
											else if("br".equals(element))
												entiteValsEcrivain.tl(2 + xmlPart, "e(\"", element, "\").fg();");
											else if("td".equals(element))
												entiteValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" w3-mobile ", css, cssNumero, "\").f();");
											else
												entiteValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").f();");

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
												entiteValsEcrivain.tl(1 + entiteXmlPile.size(), "} g(\"", entiteXmlPile.peek(), "\");");
												entiteXmlPile.pop();
												entiteNumeroPile.pop();
											}
											if("i".equals(element))
												entiteValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", ", entiteVar, entiteValVar, entiteValVarNumero, ", \" site-menu-icon ", css, cssNumero, "\").f();");
											else if("a".equals(element))
												entiteValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").a(\"href\", ", entiteVar, entiteValVar, entiteValVarNumero, ").f();");
											else if("br".equals(element))
												entiteValsEcrivain.tl(2 + xmlPart, "e(\"", element, "\").fg();");
											else if("td".equals(element))
												entiteValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" w3-mobile ", css, cssNumero, "\").f();");
											else
												entiteValsEcrivain.tl(2 + xmlPart, "{ e(\"", element, "\").a(\"class\", \" ", css, cssNumero, "\").f();");

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
									entiteValsEcrivain.tl(2 + p, "sx(", entiteVar, entiteValVar, entiteValVarNumero, ");");
								}
								else {
									if(classeEntiteVars.contains("utilisateurId"))
										entiteValsEcrivain.tl(2 + p, "sx(utilisateurId == null ? ", entiteVar, entiteValVar, entiteValVarNumero, " : ", entiteValCode, ");");
									else
										entiteValsEcrivain.tl(2 + p, "sx(", str_requeteSite(langueNom), "_.getUtilisateurId() == null ? ", entiteVar, entiteValVar, entiteValVarNumero, " : ", entiteValCode, ");");
								}
							}
						}
					}
	
					entiteValVarAncien = entiteValVar;
					entiteValVarLangueAncien = entiteValVarLangue;
				}
				if(StringUtils.equals(langueNom, entiteValLangue)) {
					entiteValVarAncien = entiteValVar;
					entiteValVarLangueAncien = entiteValVarLangue;
					entiteValVar = null;
		
					if(entiteValVarAncien != null && !StringUtils.equals(entiteValVar, entiteValVarLangueAncien)) {
						t(1, "public static final String ", entiteVar, entiteValVarAncien, " = ");
						for(int k = 1; k <= entiteValVarNumero; k++) {
							if(k > 1)
								s(" + ");
							s(entiteVar, entiteValVarAncien, k);
						}
						l(";");
						entiteValVarNumero = 0;
					}
				}
				l();

				for(int q = entiteXmlPile.size() - 1; q >= 0; q--) {
					entiteValsEcrivain.tl(2 + q, "} g(\"", entiteXmlPile.get(q), "\");");
					entiteXmlPile.pop();
				}
			}

			if(ecrireCommentaire) {
				t(1, "/**");
				t(1);
					s(str_L_entité_(langueNom), entiteVar);
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
					tl(1, " *\t", "Il est construit avant d'être initialisé avec le constructeur par défaut ", entiteNomSimpleComplet, "(). ");
				}
				tl(1, " */");
			}

			if(entiteIgnorer)
				tl(1, "@JsonIgnore");
			else if("LocalDate".equals(entiteNomSimple)) {
				tl(1, "@JsonProperty");
				tl(1, "@JsonDeserialize(using = ToStringSerializer.class)");
				tl(1, "@JsonSerialize(using = ToStringSerializer.class)");
				tl(1, "@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = \"yyyy-MM-dd\")");
			}
			else if("LocalTime".equals(entiteNomSimple)) {
				tl(1, "@JsonProperty");
				tl(1, "@JsonDeserialize(using = ToStringSerializer.class)");
				tl(1, "@JsonSerialize(using = ToStringSerializer.class)");
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

				tl(1, "@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=\"yyyy-MM-dd'T'HH:mm:ss.SSS'['VV']'\")");
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
			} else if(entiteNomSimpleGenerique != null) {
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
	
			tl(1, "@JsonIgnore");
			t(1, "public ", classePartsCouverture.nomSimple(langueNom), "<", entiteNomSimpleComplet, "> ", entiteVar, classePartsCouverture.nomSimple(langueNom));
			l(" = new ", classePartsCouverture.nomSimple(langueNom), "<", entiteNomSimpleComplet, ">().var(\"", entiteVar, "\").o(", entiteVar, ");");
	
			// Methode underscore //
			l();
			if(ecrireCommentaire) {
				t(1, "/**");
				t(1);
				s("<br/>", str_L_entité_(langueNom), entiteVar);
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
					tl(1, " * ", str__est_défini_comme_null_avant_d_être_initialisé__(langueNom));
				}
				else {
					tl(1, " * ", str_Il_est_construit_avant_d_être_initialisé_avec_le_constructeur_par_défaut_(langueNom), entiteNomSimpleComplet, "(). ");
				}
		
				// Lien vers Solr //
				tl(1, " * <br/><a href=\"", solrUrlComputate, "/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(classeNomCanonique), "&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(entiteVar), "\">", str_Trouver_l_entité_(langueNom), entiteVar, str__dans_Solr(langueNom), "</a>");
				tl(1, " * <br/>");
		
				if(entiteCouverture) {
					tl(1, " * @param ", entiteVarParam, str__est_pour_envelopper_une_valeur_à_assigner_à_cette_entité_lors_de_l_initialisation__(langueNom));
				}
				else {
					tl(1, " * @param ", entiteVar, str__est_l_entité_déjà_construit__(langueNom));
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
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");

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
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
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
				tl(2, "Long l = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "if(l != null)");
				tl(3, "add", entiteVarCapitalise, "(l);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleGenerique, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
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
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
				tl(2, "return Boolean.parseBoolean(o);");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter Integer //
			if(StringUtils.equals(entiteNomCanonique, Integer.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
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
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
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
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
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
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
				tl(2, "if(NumberUtils.isParsable(o))");
				tl(3, "return Long.parseLong(o);");
				tl(2, "return null;");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter BigDecimal //
			if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
				tl(2, "o = StringUtils.removeAll(o, \"[^\\\\d\\\\.]\");");
				tl(2, "if(NumberUtils.isParsable(o))");
				tl(3, "return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);");
				tl(2, "return null;");
				tl(1, "}");
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Double o) {");
				tl(3, "this.", entiteVar, " = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Integer o) {");
				tl(3, "this.", entiteVar, " = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
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
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
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
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
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
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
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
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				if(ecrireCommentaire) {
					tl(1, "/** Example: 2011-12-03+01:00 **/");
				}
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
				tl(2, "return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);");
				tl(1, "}");
				if(classeContientRequeteSite) {
				tl(1, "@JsonIgnore");
					tl(1, "public void set", entiteVarCapitalise, "(Date o) {");
					tl(2, "this.", entiteVar, " = o == null ? null : o.toInstant().atZone(ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_siteZone(langueNom), ")))).toLocalDate();");
					tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
					tl(1, "}");
				}
				staticSet = true;
			}
	
			// Setter ZonedDateTime //
			if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Instant o) {");
				tl(2, "this.", entiteVar, " = o == null ? null : ZonedDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				if(ecrireCommentaire) {
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				}
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ", o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
				tl(2, "if(StringUtils.endsWith(o, \"Z\"))");
				tl(3, "return o == null ? null : Instant.parse(o).atZone(ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))).truncatedTo(ChronoUnit.MILLIS);");
				tl(2, "else");
				tl(3, "return o == null ? null : ZonedDateTime.parse(o, DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'['VV']'\")).truncatedTo(ChronoUnit.MILLIS);");
				tl(1, "}");
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Date o) {");
				tl(2, "this.", entiteVar, " = o == null ? null : ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))).truncatedTo(ChronoUnit.MILLIS);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				staticSet = true;
			}
	
			// Setter LocalDateTime //
			if(StringUtils.equals(entiteNomCanonique, LocalDateTime.class.getCanonicalName())) {
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Instant o) {");
				tl(2, "this.", entiteVar, " = o == null ? null : LocalDateTime.from(o).truncatedTo(ChronoUnit.MILLIS);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				if(ecrireCommentaire) {
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				}
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, o);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
				tl(2, "return o == null ? null : LocalDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME).truncatedTo(ChronoUnit.MILLIS);");
				tl(1, "}");
				tl(1, "@JsonIgnore");
				tl(1, "public void set", entiteVarCapitalise, "(Date o) {");
				tl(2, "this.", entiteVar, " = o == null ? null : LocalDateTime.ofInstant(o.toInstant(), ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))).truncatedTo(ChronoUnit.MILLIS);");
				tl(2, "this.", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), " = true;");
				tl(1, "}");
				staticSet = true;
			}

			if(!staticSet && !entiteNomSimpleComplet.contains("<DEV>")) {
				if((StringUtils.equalsAny(entiteNomSimple, "List", "ArrayList", "Stack"))) {
					tl(1, "public static ", entiteNomSimpleGenerique, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
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
					tl(1, "public static ", entiteNomSimpleComplet, " staticSet", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
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
				tl(2, "if(o != null && !", entiteVar, ".contains(o))");
				tl(3, "this.", entiteVar, ".add(o);");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
		
				// Setter String //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, String.class.getCanonicalName())) {
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
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Boolean.class.getCanonicalName())) {
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
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Integer.class.getCanonicalName())) {
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
				if(StringUtils.equals(entiteNomCanoniqueGenerique, BigDecimal.class.getCanonicalName())) {
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
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Float.class.getCanonicalName())) {
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
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Double.class.getCanonicalName())) {
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
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Long.class.getCanonicalName())) {
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
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Timestamp.class.getCanonicalName())) {
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
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Date.class.getCanonicalName())) {
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
					tl(2, entiteNomSimpleCompletGenerique, " p = Date.from(LocalDateTime.parse(o, DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))).toInstant());");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter LocalDate //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, LocalDate.class.getCanonicalName())) {
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
					tl(2, entiteNomSimpleCompletGenerique, " p = o.toInstant().atZone(ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))).toLocalDate();");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter ZonedDateTime //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, ZonedDateTime.class.getCanonicalName())) {
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
					tl(2, entiteNomSimpleCompletGenerique, " p = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), ")));");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter LocalDateTime //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, LocalDateTime.class.getCanonicalName())) {
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
					tl(2, entiteNomSimpleCompletGenerique, " p = LocalDateTime.ofInstant(o.toInstant(), ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), ")));");
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
				tl(1, "protected Future<", entiteNomSimpleComplet, "> ", entiteVar, str_Promesse(langueNom), "() {");
				tl(2, "Promise<", entiteNomSimpleComplet, "> promise = Promise.promise();");
	
				tl(2, "if(!", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), ") {");
				tl(3, "Promise<", entiteNomSimpleComplet, "> promise2 = Promise.promise();");
				tl(3, "_", entiteVar, "(promise2);");
				tl(3, "promise2.future().onSuccess(o -> {");
				if(entiteInitLoin && entiteInitialise) {
					tl(4, "if(o != null && ", entiteVar, " == null) {");
					tl(5, "o.", str_promesseLoin(langueNom), str_PourClasse(langueNom), "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ").onSuccess(a -> {");
					tl(6, "set", entiteVarCapitalise, "(o);");
					tl(6, entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), "(true);");
					tl(6, "promise.complete(o);");
					tl(5, "}).onFailure(ex -> {");
					tl(6, "promise.fail(ex);");
					tl(5, "});");
					tl(4, "} else {");
					tl(5, entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), "(true);");
					tl(5, "promise.complete(o);");
					tl(4, "}");
				}
				else {
					tl(4, "promise.complete(o);");
				}
				tl(3, "}).onFailure(ex -> {");
				tl(4, "promise.fail(ex);");
				tl(3, "});");
				tl(2, "} else {");
				tl(3, "promise.complete();");
				tl(2, "}");
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
	
				tl(2, "if(!", entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), ") {");
				if(entiteCouverture) {
					tl(3, "_", entiteVar, "(", entiteVar, classePartsCouverture.nomSimple(langueNom), ");");
					tl(3, "if(", entiteVar, " == null)");
					tl(4, "set", entiteVarCapitalise, "(", entiteVar, classePartsCouverture.nomSimple(langueNom), ".o);");
					tl(3, entiteVar, classePartsCouverture.nomSimple(langueNom), ".o(null);");
				}
				else {
					tl(3, "_", entiteVar, "(", entiteVar, ");");
				}
				tl(2, "}");
				if(entiteInitLoin && entiteInitialise) {
					if(entiteCouverture) {
						tl(2, "if(", entiteVar, " != null)");
						tl(3, entiteVar, ".", str_initLoin(langueNom), str_PourClasse(langueNom), "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ");");
					}
					else {
						tl(2, entiteVar, ".", str_initLoin(langueNom), str_PourClasse(langueNom), "(", classeContientRequeteSite ? (str_requeteSite(langueNom) + "_") : "null", ");");
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
	
				tl(2, entiteVar, classePartsCouverture.nomSimple(langueNom), ".", str_dejaInitialise(langueNom), "(true);");
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
						tl(1, "public static ", entiteSolrNomSimple, " staticSolr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : Date.from(o.toInstant());");
						tl(1, "}");
					}
					else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSolr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : Date.from(o.toInstant());");
						tl(1, "}");
					}
					else if(entiteNomCanonique.toString().equals(LocalTime.class.getCanonicalName())) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSolr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : o.format(DateTimeFormatter.ISO_LOCAL_TIME);");
						tl(1, "}");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSolr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : Date.from(o.atZone(ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))).toInstant().atZone(ZoneId.of(\"Z\")).toInstant());");
						tl(1, "}");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSolr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))).toInstant().atZone(ZoneId.of(\"Z\")).toInstant());");
						tl(1, "}");
					}
					else if(entiteNomSimple.toString().equals("BigDecimal")) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSolr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o == null ? null : o.doubleValue();");
						tl(1, "}");
					}
					else if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
						tl(1, "public static ", StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteSolrNomSimple, "<"), ">"), " staticSolr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteNomSimpleGenerique, " o) {");
						tl(2, "return o;");
						tl(1, "}");
					}
					else if("java.util.Set".equals(entiteNomCanonique) || "java.util.HashSet".equals(entiteNomCanonique)) {
						tl(1, "public static ", entiteSolrNomSimple, " staticSolr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return new ArrayList<>(o);");
						tl(1, "}");
					}
					else {
						tl(1, "public static ", entiteSolrNomSimple, " staticSolr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteNomSimpleComplet, " o) {");
						tl(2, "return o;");
						tl(1, "}");
					}
				}
				else {
					tl(1, "public static Object staticSolr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteNomSimpleComplet, " o) {");
					tl(2, "return null;");
					tl(1, "}");
				}
	
				l();
				if(classeContientRequeteSite && entiteNomSimple != null && entiteSolrNomCanonique != null) {
					if(entiteNomSimple.equals("Timestamp")) {
						tl(1, "public static String staticSolrStr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteSolrNomSimple, " o) {");
						tl(2, "return \"\\\"\" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + \"\\\"\";");
						tl(1, "}");
					}
					else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(1, "public static String staticSolrStr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteSolrNomSimple, " o) {");
						tl(2, "return \"\\\"\" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + \"\\\"\";");
						tl(1, "}");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(1, "public static String staticSolrStr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteSolrNomSimple, " o) {");
						tl(2, "return \"\\\"\" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + \"\\\"\";");
						tl(1, "}");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(1, "public static String staticSolrStr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteSolrNomSimple, " o) {");
		//				tl(3, "document.addField(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_DATE_TIME.format(", entiteVar, ".atStartOfDay(ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
						tl(2, "return \"\\\"\" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + \"\\\"\";");
						tl(1, "}");
					}
					else if(entiteSolrNomCanonique.toString().equals("String")) {
						tl(1, "public static String staticSolrStr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteSolrNomSimple, " o) {");
						tl(2, "return o;");
						tl(1, "}");
					}
					else if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
						tl(1, "public static String staticSolrStr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteSolrNomSimple, "<"), ">"), " o) {");
						tl(2, "return o == null ? null : o.toString();");
						tl(1, "}");
					}
					else {
						tl(1, "public static String staticSolrStr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, ", entiteSolrNomSimple, " o) {");
						tl(2, "return o == null ? null : o.toString();");
						tl(1, "}");
					}
				}
				else {
					tl(1, "public static String staticSolrStr", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, Object o) {");
					tl(2, "return null;");
					tl(1, "}");
				}
	
				l();
				tl(1, "public static String staticSolrFq", entiteVarCapitalise, "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o) {");
				tl(2, "return ", classeNomSimple, ".staticSolrStr", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, ", classeNomSimple, ".staticSolr", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, o)));");
				tl(1, "}");
			}

			//////////
			// htm //
			//////////
			if(classeContientRequeteSite && entiteNomSimple != null && entiteSolrNomCanonique != null) {
	
				//////////
				// solr //
				//////////
				l();
				tl(1, "public ", entiteSolrNomSimple, " solr", entiteVarCapitalise, "() {");
					if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
						tl(2, "List<", entiteNomSimpleGenerique, "> l = new ArrayList<", entiteNomSimpleGenerique, ">();");
						tl(2, "for(", entiteNomSimpleGenerique, " o : ", entiteVar, ") {");
						tl(3, "l.add(", classeNomSimple, ".staticSolr", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, o));");
						tl(2, "}");
						tl(2, "return l;");
					}
					else {
						tl(2, "return ", classeNomSimple, ".staticSolr", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, ", entiteVar, ");");
					}
				tl(1, "}");
	
				/////////
				// str //
				/////////
				l();
				tl(1, "public String str", entiteVarCapitalise, "() {");
				if(VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanonique)) {
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".format(DateTimeFormatter.ofPattern(\"", str_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz_VV(langueNom), "\", Locale.forLanguageTag(\"", str_frDashFR(langueNom), "\")));");
				}
				else if(VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanonique)) {
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".format(DateTimeFormatter.ofPattern(\"", str_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz(langueNom), "\", Locale.forLanguageTag(\"", str_frDashFR(langueNom), "\")));");
				}
				else if(VAL_nomCanoniqueLocalDate.equals(entiteNomCanonique)) {
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".format(DateTimeFormatter.ofPattern(\"", str_EEE_d_MMM_yyyy(langueNom), "\", Locale.forLanguageTag(\"", str_frDashFR(langueNom), "\")));");
				}
				else if(VAL_nomCanoniqueLocalTime.equals(entiteNomCanonique)) {
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".format(DateTimeFormatter.ofPattern(\"", str_HAposhAposmm(langueNom), "\", Locale.forLanguageTag(\"", str_frDashFR(langueNom), "\")));");
				}
				else if(VAL_nomCanoniqueBigDecimal.equals(entiteNomCanonique)) {
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".setScale(2, RoundingMode.HALF_UP).toString();");
				}
				else if(VAL_nomCanoniqueString.equals(entiteNomCanonique))
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ";");
				else
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".toString();");
				tl(1, "}");
	
				/////////
				// sql //
				/////////
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
	
				//////////
				// json //
				//////////
				l();
				tl(1, "public String json", entiteVarCapitalise, "() {");
				if(VAL_nomCanoniqueZonedDateTime.equals(entiteNomCanonique)) {
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".format(DateTimeFormatter.ISO_DATE_TIME);");
				}
				else if(VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanonique)) {
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".format(DateTimeFormatter.ISO_DATE_TIME);");
				}
				else if(VAL_nomCanoniqueLocalDate.equals(entiteNomCanonique)) {
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".format(DateTimeFormatter.ISO_DATE);");
				}
				else if(VAL_nomCanoniqueLocalTime.equals(entiteNomCanonique)) {
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".format(DateTimeFormatter.ISO_TIME);");
				}

				else if(VAL_nomCanoniqueString.equals(entiteNomCanonique))
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ";");
				else
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".toString();");
				tl(1, "}");
//	
//				//////////////////
//				// nomAffichage //
//				//////////////////
//				l();
//				tl(1, "public String nomAffichage", entiteVarCapitalise, "() {");
//				tl(2, "return ", entiteNomAffichage == null ? "null" : "\"" + escapeJava(entiteNomAffichage) + "\"", ";");
//				tl(1, "}");
	
				if(classePage) {
					/////////////////
					// htmTooltip //
					/////////////////
					l();
					tl(1, "public String htmTooltip", entiteVarCapitalise, "() {");
					tl(2, "return ", entiteHtmlTooltip == null ? "null" : "\"" + escapeJava(entiteHtmlTooltip) + "\"", ";");
					tl(1, "}");
		
					/////////
					// htm //
					/////////
		
					l();
					tl(1, "public String htm", entiteVarCapitalise, "() {");
					tl(2, "return ", entiteVar, " == null ? \"\" : StringEscapeUtils.escapeHtml4(str", entiteVarCapitalise, "());");
					tl(1, "}");
		
					if(entiteVarCapitalise != null && classeIndexe && entiteSolrNomCanonique != null) {
			
						int tIndex = 0;
						Boolean resultat = false;
				
						if(entiteHtml && classeVarClePrimaire != null) {
		
	//						String classeApiMethodeMethode = "PATCH";
							String classePrefixe = "";
							if(classeEstBase) {
								classePrefixe = "s.";
							}
		
							///////////
							// input //
							///////////
				
							l();
							tl(1, "public void input", entiteVarCapitalise, "(String ", str_classeApiMethodeMethode(langueNom), ") {");
							tl(2, classeNomSimple, " s = (", classeNomSimple, ")this;");
				
							if(entiteModifier && (entiteDefinir || entiteAttribuer)) {
	
								if(classeUtilisateurEcrire && classeSessionEcrire) {
									t(tIndex + 2).s(classePrefixe, "if(").l();
									t(tIndex + 4).s(classePrefixe, str_utilisateur(langueNom), str_Cle(langueNom), "s.contains(", str_requeteSite(langueNom), "_.get", str_Utilisateur(langueNom), str_Cle(langueNom), "())").l();
									t(tIndex + 4).s(classePrefixe, "|| Objects.equals(sessionId, ", str_requeteSite(langueNom), "_.getSessionId())").l();
									t(tIndex + 4).s(classePrefixe, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ROLES)").l();
									t(tIndex + 4).s(classePrefixe, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ROLES)").l();
									t(tIndex + 2).s(classePrefixe, ") {").l();
								}
								else if(classePublicLire) {
									tl(tIndex + 2, "if(");
									tl(tIndex + 4, "CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ROLES)");
									tl(tIndex + 4, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ROLES)");
									tl(tIndex + 4, ") {");
								}
								else if(classeUtilisateurEcrire) {
									if(classeRolesTrouves || classeRoleLiresTrouves) {
										tl(tIndex + 2, "if(");
										tl(tIndex + 4, "CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ROLES)");
										tl(tIndex + 4, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ROLES)");
										tl(tIndex + 4, ") {");
									}
									else {
										t(tIndex + 2).s(classePrefixe, "if(", str_utilisateur(langueNom), str_Cle(langueNom), "s.contains(", str_requeteSite(langueNom), "_.get", str_Utilisateur(langueNom), str_Cle(langueNom), "())) {").l();
									}
								}
								else if(classeSessionEcrire) {
									t(tIndex + 2).s(classePrefixe, "if(Objects.equals(sessionId, ", str_requeteSite(langueNom), "_.getSessionId()) {").l();
								}
								else if(classeRolesTrouves || classeRoleLiresTrouves) {
									tl(tIndex + 2, "if(");
									tl(tIndex + 4, "CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ROLES)");
									tl(tIndex + 4, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ROLES)");
									tl(tIndex + 4, ") {");
								}
								else {
									t(tIndex + 2).s(classePrefixe, "{").l();
								}
	
								if(entiteAttribuer) {
									t(tIndex + 3).s(classePrefixe, "e(\"i\")").da("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").df().dgl("i");
	
									t(tIndex + 3).l("if(\"", str_PUTCopie(langueNom), "\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
									t(tIndex + 4).l(classePrefixe, "{ e(\"div\").f();");
									t(tIndex + 5).s(classePrefixe, "e(\"input\")").l();
									t(tIndex + 6).dal("type", "checkbox");
									t(tIndex + 6).l(".a(\"id\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "_", str_vider(langueNom), "\")");
									t(tIndex + 6).l(".a(\"class\", \"", entiteVar, "_", str_vider(langueNom), " \")");
									t(tIndex + 6).l(".fg();");
									t(tIndex + 5).s(classePrefixe, "e(\"label\")").da("for", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "_", str_vider(langueNom)).df().dsxq(str_vider(langueNom)).dgl("label");
									t(tIndex + 4).l(classePrefixe, "} g(\"div\");");
									t(tIndex + 3).l("}");
	
									t(tIndex + 3).s(classePrefixe, "e(\"input\")").l();
									t(tIndex + 4).dal("type", "text");
					
									if(entiteNomAffichage != null) {
										t(tIndex + 4).dal("placeholder", entiteNomAffichage);
									}
									if(entiteDescription != null) {
										t(tIndex + 4).dal("title", entiteDescription);
									}
					
	//								t(tIndex + 4).s(classePrefixe).s(".a(\"class\", \"", "set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\");");
									t(tIndex + 4).dal("class", str_valeur(langueNom), StringUtils.capitalize(entiteAttribuerVarSuggere), " ", str_suggere(langueNom), entiteVarCapitalise, " w3-input w3-border w3-cell w3-cell-middle ");
									t(tIndex + 4).dal("name", "set", entiteVarCapitalise);
									t(tIndex + 4).l(".a(\"id\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")");
									t(tIndex + 4).l(".a(\"autocomplete\", \"", "off\");");
									t(tIndex + 4).s("a(\"oninput\", \"", str_suggere(langueNom), classeNomSimple, entiteVarCapitalise, "($(this).val() ? [ { 'name': 'q', 'value': '", entiteAttribuerVarSuggere, ":' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", classeVarClePrimaire, entiteAttribuerVarUrlPk == null ? "" : "," + entiteAttribuerVarUrlPk, entiteAttribuerVarTitre == null ? "" : "," + entiteAttribuerVarTitre, "' } ] : [");
									s("\", ", classeVarClePrimaire, " == null ? \"\" : \"{'name':'fq','value':'", entiteAttribuerVar, ":\" + ", classeVarClePrimaire, " + \"'}\", \"");
									l("], $('#", "list", classeNomSimple, entiteVarCapitalise, "_\", ", str_classeApiMethodeMethode(langueNom), ", \"'), \", ", classeVarClePrimaire, ", \"); \");");
									l();
				
									t(tIndex + 4).fgl();
									l();
								}
								else if("LocalDate".equals(entiteNomSimple)) {
									t(tIndex + 3).s(classePrefixe, "e(\"input\")").l();
									t(tIndex + 5).dal("type", "text");
									t(tIndex + 5).s(classePrefixe).s(".a(\"class\", \"w3-input w3-border datepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\")");
									t(tIndex + 5).dal("placeholder", str_DDDashMMDashYYYY(langueNom));
									t(tIndex + 5).dal("data-timeformat", str_ddDashMMDashyyyy(langueNom));
									t(tIndex + 5).l(".a(\"id\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")");
									if(entiteDescription != null)
										t(tIndex + 5).dal("title", entiteDescription + " (", str_DDDashMMDashYYYY(langueNom), ")");
									tl(tIndex + 5, ".a(\"value\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", str_ddDashMMDashyyyy(langueNom), "\").format(", entiteVar, "));");
									t(tIndex + 3).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
									t(tIndex + 4).l("a(\"onclick\", \"", str_enleverLueur(langueNom), "($(this)); \");");
									t(tIndex + 4).s("a(\"onchange\", \"");
										s("var t = moment(this.value, '", str_DDDashMMDashYYYY(langueNom), "'); ");
										s("if(t) { ");
											s("var s = t.format('YYYY-MM-DD'); ");
											s("patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeVarClePrimaire, ", \"' }], 'set", entiteVarCapitalise, "', s, function() { ", str_ajouterLueur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }, function() { ", str_ajouterErreur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }); ");
										s("} ");
									l("\");");
									t(tIndex + 3).l("}");
									tl(tIndex + 3, "fg();");
								}
								else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
									t(tIndex + 3).s(classePrefixe, "e(\"input\")").l();
									t(tIndex + 5).dal("type", "text");
									t(tIndex + 5).s(classePrefixe).s(".a(\"class\", \"w3-input w3-border datepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\")");
									t(tIndex + 5).dal("placeholder", str_DDDashMMDashYYYY_HHColonMM(langueNom));
									t(tIndex + 5).dal("data-timeformat", str_ddDashMMDashyyyy(langueNom));
									t(tIndex + 5).l(".a(\"id\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")");
									if(entiteDescription != null)
										t(tIndex + 5).dal("title", entiteDescription + " (", str_DDDashMMDashYYYY(langueNom), ")");
									tl(tIndex + 4, ".a(\"value\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", str_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz_VV(langueNom), "\").format(", entiteVar, "));");
									t(tIndex + 3).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
									t(tIndex + 4).l("a(\"onclick\", \"", str_enleverLueur(langueNom), "($(this)); \");");
									t(tIndex + 4).s("a(\"onchange\", \"");
										s("var t = moment(this.value, '", str_DDDashMMDashYYYY(langueNom), "'); ");
										s("if(t) { ");
											s("var s = t.format('YYYY-MM-DD'); ");
											s("patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeVarClePrimaire, ", \"' }], 'set", entiteVarCapitalise, "', s, function() { ", str_ajouterLueur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }, function() { ", str_ajouterErreur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }); ");
										s("} ");
									l("\");");
									t(tIndex + 3).l("}");
									tl(tIndex + 3, "fg();");
								}
								else if("LocalTime".equals(entiteNomSimple)) {
									t(tIndex + 3).s(classePrefixe, "e(\"input\")").l();
									t(tIndex + 5).dal("type", "text");
									t(tIndex + 5).s(classePrefixe).s(".a(\"class\", \"w3-input w3-border timepicker set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\")");
									t(tIndex + 5).dal("placeholder", str_HHColonMM(langueNom));
									t(tIndex + 5).l(".a(\"id\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")");
									if(entiteDescription != null)
										t(tIndex + 5).da("title", entiteDescription + " (", str_HAposhAposmm(langueNom), ")");
									tl(tIndex + 5, ".a(\"value\", ", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", str_HAposhAposmm(langueNom), "\").format(", entiteVar, "));");
									t(tIndex + 3).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
									t(tIndex + 4).l("a(\"onclick\", \"", str_enleverLueur(langueNom), "($(this)); \");");
									t(tIndex + 4).s("a(\"onchange\", \"");
										s("var t = moment(this.value, '", str_HAposhAposmm(langueNom), "'); ");
										s("if(t) { ");
											s("var s = t.format('HH:mm'); ");
											s("patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeVarClePrimaire, ", \"' }], 'set", entiteVarCapitalise, "', s, function() { ", str_ajouterLueur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }, function() { ", str_ajouterErreur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }); ");
										s("} ");
									l("\");");
									t(tIndex + 3).l("}");
									tl(tIndex + 3, "fg();");
								}
								else if("Boolean".equals(entiteNomSimple)) {
									t(tIndex + 3).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
									t(tIndex + 4).s(classePrefixe, "e(\"input\")").l();
									t(tIndex + 5).dal("type", "checkbox");
									t(tIndex + 5).l(".a(\"id\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")");
									t(tIndex + 5).da("value", "true").l(";");
									t(tIndex + 3).l("} else {");
									t(tIndex + 4).s(classePrefixe, "e(\"select\")").l();
									t(tIndex + 5).l(".a(\"id\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\");");
									t(tIndex + 3).l("}");
					
									t(tIndex + 3).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ") || \"PATCH\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
										t(tIndex + 4).s(classePrefixe).s("a(\"class\", \"set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\");");
										t(tIndex + 4).s(classePrefixe).a("name", "set", entiteVarCapitalise).l(";");
									t(tIndex + 3).l("} else {");
										t(tIndex + 4).s(classePrefixe).s("a(\"class\", \"", str_valeur(langueNom), entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\");");
										t(tIndex + 4).s(classePrefixe).a("name", entiteVar).l(";");
									t(tIndex + 3).l("}");
									t(tIndex + 3).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
										t(tIndex + 4).s(classePrefixe).s("a(\"onchange\", \"patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeVarClePrimaire, ", \"' }], 'set", entiteVarCapitalise, "', $(this).prop('checked'), function() { ", str_ajouterLueur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }, function() { ", str_ajouterErreur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }); \")").l(";");
									t(tIndex + 3).l("}");
					
									t(tIndex + 3).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
									tl(tIndex + 4, "if(get", entiteVarCapitalise, "() != null && get", entiteVarCapitalise, "())");
									t(tIndex + 5).s(classePrefixe).a("checked", "checked").l(";");
									t(tIndex + 4).s(classePrefixe).fgl();
									t(tIndex + 3).l("} else {");
									t(tIndex + 4).s(classePrefixe, "f();").l();
									t(tIndex + 4).s(classePrefixe, "e(\"option\").a(\"value\", \"\").a(\"selected\", \"selected\").f().g(\"option\");").l();
									t(tIndex + 4).s(classePrefixe, "e(\"option\").a(\"value\", \"true\").f().sx(\"true\").g(\"option\");").l();
									t(tIndex + 4).s(classePrefixe, "e(\"option\").a(\"value\", \"false\").f().sx(\"false\").g(\"option\");").l();
									t(tIndex + 4).s(classePrefixe, "g(\"select\");").l();
									t(tIndex + 3).l("}");
									l();
								}
								else if(entiteImageBase64Url != null) {
									t(tIndex + 3).s(classePrefixe, "e(\"div\").a(\"class\", \"imageBase64Div1", classeNomSimple, "_", entiteVar, "\").a(\"id\", \"imageBase64Div1", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "\").f();").l();
	
									t(tIndex + 4).s(classePrefixe, "e(\"h5\").f().sx(\"", str_Télécharger_image(langueNom), "\").g(\"h5\"); ").l();
									t(tIndex + 4).s(classePrefixe, "e(\"form\").a(\"method\", \"POST\").a(\"enctype\", \"multipart/form-data\").a(\"action\", \"", entiteImageBase64Url, "\").a(\"class\", \"\").f();").l();
									t(tIndex + 4).s(classePrefixe, "e(\"input\").a(\"type\", \"hidden\").a(\"name\", \"", classeVarClePrimaire, "\").a(\"value\", ", classeVarClePrimaire, ").fg(); ").l();
									t(tIndex + 4).s(classePrefixe, "e(\"input\").a(\"type\", \"hidden\").a(\"name\", \"", str_classeNomSimple(langueNom), "\").a(\"value\", \"", classeNomSimple, "\").fg(); ").l();
									t(tIndex + 4).s(classePrefixe, "e(\"input\").a(\"name\", \"", str_fichier(langueNom), "\").a(\"type\", \"file\").a(\"onchange\", \"$.ajax({ type: 'POST', enctype: 'multipart/form-data', url: '", entiteImageBase64Url, "', data: new FormData(this.form), processData: false, contentType: false}); \").fg(); ").l();
									t(tIndex + 4).s(classePrefixe, "g(\"form\");").l();
	
									t(tIndex + 4).s(classePrefixe, "e(\"img\").a(\"id\", \"imageBase64Img", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "\");").l();
									t(tIndex + 5).s(classePrefixe, "a(\"class\", \"img", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-image ").l("\");");
									t(tIndex + 5).s(classePrefixe, "a(\"src\", StringUtils.isBlank(", entiteVar, ") ? \"data:image/png;base64,\" : ", entiteVar, ").a(\"alt\", \"\");").l();
									t(tIndex + 4).s(classePrefixe, "fg();").l();
	
									t(tIndex + 3).s(classePrefixe, "g(\"div\");").l();
								}
								else if(BooleanUtils.isTrue(entiteSignature)) {
									t(tIndex + 3).s(classePrefixe, "e(\"div\").a(\"class\", \"signatureDiv1", classeNomSimple, "_", entiteVar, " signatureInput", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, "\").a(\"id\", \"signatureDiv1", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "\").f();").l();
	
									t(tIndex + 4).s(classePrefixe, "e(\"div\").a(\"id\", \"signatureInput", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "\");").l();
									t(tIndex + 5).s(classePrefixe, "a(\"style\", \"display: \", StringUtils.isBlank(", entiteVar, ") ? \"block\" : \"none\", \"; \");").l();
									t(tIndex + 4).s(classePrefixe, "f().g(\"div\");").l();
	
									t(tIndex + 4).s(classePrefixe, "e(\"img\").a(\"id\", \"signatureImg", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "\");").l();
									t(tIndex + 5).s(classePrefixe, "a(\"class\", \"signatureImg", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " \");").l();
									t(tIndex + 5).s(classePrefixe, "a(\"src\", StringUtils.isBlank(", entiteVar, ") ? \"data:image/png;base64,\" : ", entiteVar, ").a(\"alt\", \"\");").l();
									t(tIndex + 5).s(classePrefixe, "a(\"style\", \"padding: 10px; display: \", StringUtils.isBlank(", entiteVar, ") ? \"none\" : \"block\", \"; \");").l();
									t(tIndex + 4).s(classePrefixe, "fg();").l();
	
									t(tIndex + 3).s(classePrefixe, "g(\"div\");").l();
									t(tIndex + 3).s(classePrefixe, "e(\"div\").a(\"id\", \"signatureDiv2", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "\").f();").l();
	
									t(tIndex + 4).s(classePrefixe, "e(\"button\").a(\"id\", \"signatureButton", str_Vider(langueNom), classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "\");").l();
									t(tIndex + 5).s(classePrefixe, "a(\"class\", \"w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin \");").l();
									t(tIndex + 5).s(classePrefixe, "s(\" onclick=\", \"\\\"",  "\");").l();
									t(tIndex + 6).s(classePrefixe, "s(\"$('#signatureInput", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "').show(); ", "\");").l();
									t(tIndex + 6).s(classePrefixe, "s(\"$('#signatureImg", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "').hide(); ", "\");").l();
									t(tIndex + 6).s(classePrefixe, "s(\"", str_enleverLueur(langueNom), "($('#signatureInput", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "')); ", "\");").l();
									t(tIndex + 6).s(classePrefixe).s("s(\"patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeVarClePrimaire, ", \"' }], 'set", entiteVarCapitalise, "', null); \")").l(";");
									t(tIndex + 6).s(classePrefixe, "s(\"if($('#signatureInput", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "')) { ", "\");").l();
									t(tIndex + 6).s(classePrefixe, "s(\"$('#signatureInput", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "').jSignature('reset'); ", "\");").l();
									t(tIndex + 6).s(classePrefixe, "s(\" } else { ", "\");").l();
									t(tIndex + 6).s(classePrefixe, "s(\"$('#signatureInput", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "').jSignature({'height':200}); ", "\");").l();
									t(tIndex + 6).s(classePrefixe, "s(\" } ", "\");").l();
									t(tIndex + 5).s(classePrefixe, "s(\"\\\"",  "\");").l();
									t(tIndex + 5).s(classePrefixe, "f().sx(\"", str_Vider(langueNom), "\");").l();
									t(tIndex + 4).s(classePrefixe, "g(\"button\");").l();
	//
	//								t(tIndex + 4).s(classePrefixe, "e(\"button\").a(\"id\", \"signatureButton", str_Valider(langueNom), classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "\");").l();
	//								t(tIndex + 5).s(classePrefixe, "a(\"class\", \"w3-btn w3-round w3-border w3-border-black w3-section w3-ripple w3-padding w3-margin \");").l();
	//								t(tIndex + 5).s(classePrefixe, "s(\" onclick=\", \"\\\"",  "\");").l();
	//								t(tIndex + 6).s(classePrefixe, "s(\"var src = $('#signatureInput", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVar, "').jSignature('getData', 'default'); ", "\"); ").l();
	//								t(tIndex + 6).s(classePrefixe).s("s(\"patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeVarClePrimaire, ", \"' }], 'set", entiteVarCapitalise, "', src); \")").l(";");
	//								t(tIndex + 5).s(classePrefixe, "s(\"\\\"",  "\");").l();
	//								t(tIndex + 5).s(classePrefixe, "f().sx(\"", str_ValiderLaSignature(langueNom), "\");").l();
	//								t(tIndex + 4).s(classePrefixe, "g(\"button\");").l();
	
									t(tIndex + 3).s(classePrefixe, "g(\"div\");").l();
	
								}
								else {
									if(entiteMultiligne)
										t(tIndex + 3).s(classePrefixe, "e(\"textarea\")").l();
									else {
										t(tIndex + 3).s(classePrefixe, "e(\"input\")").l();
										t(tIndex + 4).dal("type", "text");
									}
					
									if(entiteNomAffichage != null) {
										t(tIndex + 4).dal("placeholder", entiteNomAffichage);
									}
									if(entiteDescription != null) {
										t(tIndex + 4).dal("title", entiteDescription);
									}
									t(tIndex + 4).s(".a(\"id\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")").l(";");
	
									t(tIndex + 4).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ") || \"PATCH\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
										t(tIndex + 5).s(classePrefixe).s("a(\"class\", \"set", entiteVarCapitalise, " class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\");");
										t(tIndex + 5).s(classePrefixe).a("name", "set", entiteVarCapitalise).l(";");
									t(tIndex + 4).l("} else {");
										t(tIndex + 5).s(classePrefixe).s("a(\"class\", \"", str_valeur(langueNom), entiteVarCapitalise, " w3-input w3-border class", classeNomSimple, " input", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\");");
										t(tIndex + 5).s(classePrefixe).a("name", entiteVar).l(";");
									t(tIndex + 4).l("}");
									t(tIndex + 4).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
										t(tIndex + 5).s(classePrefixe).a("onclick", str_enleverLueur(langueNom), "($(this)); ").l(";");
										t(tIndex + 5).s(classePrefixe).s("a(\"onchange\", \"patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeVarClePrimaire, ", \"' }], 'set", entiteVarCapitalise, "', $(this).val(), function() { ", str_ajouterLueur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }, function() { ", str_ajouterErreur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }); \")").l(";");
									t(tIndex + 4).l("}");
					
									if(entiteMultiligne) {
										t(tIndex + 3).s("f()");
										s(".sx(str", entiteVarCapitalise, "())");
									}
									else {
										t(tIndex + 4).s(classePrefixe).s("a(\"value\", str", entiteVarCapitalise, "())").l();
									}
					
									if(entiteMultiligne)
										dgl("textarea");
									else
										t(tIndex + 3).dfgl();
					
									l();
								}
	
								if(entiteAttribuer) {
									t(tIndex + 2).s("} else {").l();
								}
								else if(classeUtilisateurEcrire && classeSessionEcrire || classePublicLire) {
									t(tIndex + 2).s("} else {").l();
									t(tIndex + 3).s(classePrefixe).s("e(\"span\").a(\"class\", \"var", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " \").f()").s(".sx(htm", entiteVarCapitalise, "())").l(".g(\"span\");");
								}
								else if(classeUtilisateurEcrire) {
									if(classeRolesTrouves || classeRoleLiresTrouves) {
										t(tIndex + 2).s("} else {").l();
										t(tIndex + 4).s(classePrefixe).s("e(\"span\").a(\"class\", \"var", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " \").f()").s(".sx(htm", entiteVarCapitalise, "())").l(".g(\"span\");");
									}
									else {
										t(tIndex + 2).s("} else {").l();
										t(tIndex + 3).s(classePrefixe).s("e(\"span\").a(\"class\", \"var", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " \").f()").s(".sx(htm", entiteVarCapitalise, "())").l(".g(\"span\");");
									}
								}
								else if(classeSessionEcrire) {
									t(tIndex + 2).s("} else {").l();
									t(tIndex + 3).s(classePrefixe).s("e(\"span\").a(\"class\", \"var", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " \").f()").s(".sx(htm", entiteVarCapitalise, "())").l(".g(\"span\");");
								}
								else if(classeRolesTrouves || classeRoleLiresTrouves) {
									t(tIndex + 2).s("} else {").l();
									tl(tIndex + 3, "if(");
									tl(tIndex + 5, "CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ROLES)");
									tl(tIndex + 5, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ROLES)");
									if(classeRoleLiresTrouves) {
										tl(tIndex + 5, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ROLE_READS)");
										tl(tIndex + 5, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ROLE_READS)");
									}
									tl(tIndex + 4, ") {");
									t(tIndex + 4).s(classePrefixe).s("e(\"span\").a(\"class\", \"var", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " \").f()").s(".sx(htm", entiteVarCapitalise, "())").l(".g(\"span\");");
									tl(tIndex + 3, "}");
								}
								else {
	//								t(tIndex + 3).s(classePrefixe, "sx(htm", entiteVarCapitalise, "());").l();
								}
								t(tIndex + 2).s("}").l();
							}
							else if(!entiteModifier) {
								t(tIndex + 2).s(classePrefixe).s("e(\"span\").a(\"class\", \"var", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " \").f()").s(".sx(htm", entiteVarCapitalise, "())").l(".g(\"span\");");
							}
							tl(1, "}");  
	
							/////////
							// htm //
							/////////
				
							l();
							tl(1, "public void htm", entiteVarCapitalise, "(String ", str_classeApiMethodeMethode(langueNom), ") {");
							tl(2, classeNomSimple, " s = (", classeNomSimple, ")this;");
							t(2).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell w3-cell-top w3-center w3-mobile ").dfl();
							if(entiteHtml && (entiteDefinir || entiteAttribuer)) {
				
								t(tIndex + 3).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-padding ").dfl();
	//							t(tIndex + 4).s("{ ", classePrefixe, "e(\"span\")").da("id", "form", classeNomSimple, entiteVarCapitalise).dfl();
	//							t(tIndex + 5).s(classePrefixe, "e(\"input\")").l();
	//							t(tIndex + 6).dal("type", "hidden");
	//							t(tIndex + 6).dal("name", str_valeur(langueNom), StringUtils.capitalize(entiteAttribuerVar));
	//							t(tIndex + 6).dal("class", str_valeur(langueNom), StringUtils.capitalize(entiteAttribuerVar), " ");
	//							t(tIndex + 6).l(".a(\"value\", ", classeVarClePrimaire, ")");
	//							t(tIndex + 6).dfgl();
	//							t(tIndex + 4).l("} ", classePrefixe, "g(\"span\");");
				//				t(tIndex + 4).s("{ ", classePrefixe, "e(\"form\")").da("action", classeApiUri).da("id", str_suggere(langueNom), classeNomSimple, entiteVarCapitalise).da("style", "display: inline-block; width: 100%; ").da("onsubmit", "event.preventDefault(); return false; ").dfl();
								t(tIndex + 4).s("{ ", classePrefixe, "e(\"div\")").s(".a(\"id\", \"", str_suggere(langueNom), "\", ", str_classeApiMethodeMethode(langueNom), ", \"", classeNomSimple, entiteVarCapitalise, "\")").dfl();
								t(tIndex + 5).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-card ").dfl();
				
								if(entiteAttribuer) {
									if(entiteNomAffichage != null) {
										t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row ").dfl();
										t(tIndex + 7).s("{ ", classePrefixe, "e(\"a\")").s(".a(\"href\", \"", entiteAttribuerPageUri, "?fq=", entiteAttribuerVar, ":\", ", classeVarClePrimaire, ")").da("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-", entiteAttribuerContexteCouleur, " w3-hover-", entiteAttribuerContexteCouleur, " ").dfl();
										if(entiteAttribuerContexteIconeGroupe != null && entiteAttribuerContexteIconeNom != null)
											t(tIndex + 8).s(classePrefixe, "e(\"i\")").da("class", "fa", StringUtils.substring(entiteAttribuerContexteIconeGroupe, 0, 1), " fa-", entiteAttribuerContexteIconeNom, " ").df().dgl("i");
										t(tIndex + 8).sxqscl(entiteNomAffichage);
										t(tIndex + 7).l("} ", classePrefixe, "g(\"a\");");
										t(tIndex + 6).l("} ", classePrefixe, "g(\"div\");");
									}
									t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row ").dfl();
									t(tIndex + 7).s("{ ", classePrefixe, "e(\"h5\")").da("class", "w3-cell ").dfl();
									t(tIndex + 8).sxqscl(str_relier(langueNom), " ", entiteListeTypeJson == null ? entiteAttribuerContexteUnNom : entiteAttribuerContexteNomPluriel, " ", str_a(langueNom), " ", contexteCeNom);
									t(tIndex + 7).l("} ", classePrefixe, "g(\"h5\");");
									t(tIndex + 6).l("} ", classePrefixe, "g(\"div\");");
									t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-padding ").dfl();
									t(tIndex + 7).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell ").dfl();
									t(tIndex + 8).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row ").dfl();
									l();
					
									t(tIndex + 8).l("input", entiteVarCapitalise, "(", str_classeApiMethodeMethode(langueNom), ");");
									t(tIndex + 8).l("} ", classePrefixe, "g(\"div\");");
									t(tIndex + 7).l("} ", classePrefixe, "g(\"div\");");
									t(tIndex + 6).l("} ", classePrefixe, "g(\"div\");");
									t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-padding ").dfl();
									t(tIndex + 7).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell w3-left-align w3-cell-top ").dfl();
									t(tIndex + 8).s("{ ", classePrefixe, "e(\"ul\")").da("class", "w3-ul w3-hoverable ").s(".a(\"id\", \"list", classeNomSimple, entiteVarCapitalise, "_\", ", str_classeApiMethodeMethode(langueNom), ")").dfl();
									t(tIndex + 8).l("} ", classePrefixe, "g(\"ul\");");
	
	
									if(entiteAttribuerUtilisateurEcrire && entiteAttribuerSessionEcrire) {
										t(tIndex + 8).s(classePrefixe, "{").l();
									}
									else if(entiteAttribuerUtilisateurEcrire) {
										if(entiteAttribuerClasseRoles != null && entiteAttribuerClasseRoles.size() > 0) {
											tl(tIndex + 8, "if(");
											tl(tIndex + 10, "CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ", entiteAttribuerNomSimple, ".ROLES)");
											tl(tIndex + 10, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ", entiteAttribuerNomSimple, ".ROLES)");
											tl(tIndex + 10, ") {");
										}
										else {
											t(tIndex + 8).s(classePrefixe, "if(", str_utilisateur(langueNom), str_Cle(langueNom), "s.contains(", str_requeteSite(langueNom), "_.get", str_Utilisateur(langueNom), str_Cle(langueNom), "())) {").l();
										}
									}
									else if(entiteAttribuerSessionEcrire) {
										t(tIndex + 8).s(classePrefixe, "if(Objects.equals(sessionId, ", str_requeteSite(langueNom), "_.getSessionId()) {").l();
									}
									else {
										if(classeRolesTrouves || classeRoleLiresTrouves) {
											tl(tIndex + 8, "if(");
											tl(tIndex + 10, "CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ", entiteAttribuerNomSimple, ".ROLES)");
											tl(tIndex + 10, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ", entiteAttribuerNomSimple, ".ROLES)");
											tl(tIndex + 10, ") {");
										}
										else {
											t(tIndex + 8).s(classePrefixe, "{").l();
										}
									}
	
									t(tIndex + 9).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
									t(tIndex + 10).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row ").dfl();
									t(tIndex + 11).s(classePrefixe, "e(\"button\")").l();
									t(tIndex + 12).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", entiteAttribuerContexteCouleur, " ");
									t(tIndex + 12).l(".a(\"id\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "_", str_ajouter(langueNom), "\")");
				
									if("array".equals(entiteAttribuerTypeJson))
										t(tIndex + 12).s(".a(\"onclick\", \"$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = '", str_Envoi(langueNom), "…'; post", entiteAttribuerNomSimple, "Vals({ ", entiteAttribuerVar, ": [ \\\"\", ", classeVarClePrimaire, ", \"\\\" ] }");
									else
										t(tIndex + 12).s(".a(\"onclick\", \"$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = '", str_Envoi(langueNom), "…'; post", entiteAttribuerNomSimple, "Vals({ ", entiteAttribuerVar, ": \\\"\", ", classeVarClePrimaire, ", \"\\\" }");
									s(", function() {}");
									s(", function() { ", str_ajouterErreur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"", entiteVar, "')); });");
									s("\")");
									l();
				
									t(tIndex + 12).df().dsxq(str_ajouter(langueNom), " ", entiteAttribuerContexteUnNom).l();
									t(tIndex + 11).dgl("button");
									t(tIndex + 10).l("} ", classePrefixe, "g(\"div\");");
									t(tIndex + 9).s("}").l();
	
									t(tIndex + 8).s("}").l();
	
									t(tIndex + 7).l("} ", classePrefixe, "g(\"div\");");
								}
								else if("LocalDate".equals(entiteNomSimple)) {
									if(entiteNomAffichage != null) {
										t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
										t(tIndex + 7).s(classePrefixe, "e(\"label\")").s(".a(\"for\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")").da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
										t(tIndex + 6).l("} ", classePrefixe, "g(\"div\");");
									}
									t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row  ").dfl();
									t(tIndex + 7).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell ").dfl();
									t(tIndex + 8).l("input", entiteVarCapitalise, "(", str_classeApiMethodeMethode(langueNom), ");");
									t(tIndex + 7).l("} ", classePrefixe, "g(\"div\");");
								}
								else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
									if(entiteNomAffichage != null) {
										t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
										t(tIndex + 7).s(classePrefixe, "e(\"label\")").s(".a(\"for\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")").da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
										t(tIndex + 6).l("} ", classePrefixe, "g(\"div\");");
									}
									t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-padding ").dfl();
									t(tIndex + 7).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell ").dfl();
									t(tIndex + 8).l("input", entiteVarCapitalise, "(", str_classeApiMethodeMethode(langueNom), ");");
									t(tIndex + 7).l("} ", classePrefixe, "g(\"div\");");
								}
								else if("LocalTime".equals(entiteNomSimple)) {
									if(entiteNomAffichage != null) {
										t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
										t(tIndex + 7).s(classePrefixe, "e(\"label\")").s(".a(\"for\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")").da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
										t(tIndex + 6).l("} ", classePrefixe, "g(\"div\");");
									}
									t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-padding ").dfl();
									t(tIndex + 7).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell ").dfl();
									t(tIndex + 8).l("input", entiteVarCapitalise, "(", str_classeApiMethodeMethode(langueNom), ");");
									t(tIndex + 7).l("} ", classePrefixe, "g(\"div\");");
								}
								else if("Boolean".equals(entiteNomSimple)) {
									if(entiteNomAffichage != null) {
										t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
										t(tIndex + 7).s(classePrefixe, "e(\"label\")").s(".a(\"for\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")").da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
										t(tIndex + 6).l("} ", classePrefixe, "g(\"div\");");
									}
									t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-padding ").dfl();
									t(tIndex + 7).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell ").dfl();
									l();
									t(tIndex + 8).l("input", entiteVarCapitalise, "(", str_classeApiMethodeMethode(langueNom), ");");
									t(tIndex + 7).l("} ", classePrefixe, "g(\"div\");");
								}
								else {
									if(entiteNomAffichage != null) {
										t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
										t(tIndex + 7).s(classePrefixe, "e(\"label\")").s(".a(\"for\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "\")").da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
										t(tIndex + 6).l("} ", classePrefixe, "g(\"div\");");
									}
									t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-padding ").dfl();
									t(tIndex + 7).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell ").dfl();
									l();
					
									t(tIndex + 8).l("input", entiteVarCapitalise, "(", str_classeApiMethodeMethode(langueNom), ");");
									t(tIndex + 7).l("} ", classePrefixe, "g(\"div\");");
								}
								if(!entiteAttribuer && entiteModifier && !"Boolean".equals(entiteNomSimple)) {
	
									if(classeUtilisateurEcrire && classeSessionEcrire) {
										t(tIndex + 7).s(classePrefixe, "if(").l();
										t(tIndex + 9).s(classePrefixe, str_utilisateur(langueNom), str_Cle(langueNom), "s.contains(", str_requeteSite(langueNom), "_.get", str_Utilisateur(langueNom), str_Cle(langueNom), "())").l();
										t(tIndex + 9).s(classePrefixe, "|| Objects.equals(sessionId, ", str_requeteSite(langueNom), "_.getSessionId())").l();
										t(tIndex + 9).s(classePrefixe, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ROLES)").l();
										t(tIndex + 9).s(classePrefixe, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ROLES)").l();
										t(tIndex + 7).s(classePrefixe, ") {").l();
									}
									else if(classeUtilisateurEcrire) {
										if(classeRolesTrouves) {
											tl(tIndex + 7, "if(");
											tl(tIndex + 9, "CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ROLES)");
											tl(tIndex + 9, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ROLES)");
											tl(tIndex + 9, ") {");
										}
										else {
											t(tIndex + 7).s(classePrefixe, "if(", str_utilisateur(langueNom), str_Cle(langueNom), "s.contains(", str_requeteSite(langueNom), "_.get", str_Utilisateur(langueNom), str_Cle(langueNom), "())) {").l();
										}
									}
									else if(classeSessionEcrire) {
										t(tIndex + 7).s(classePrefixe, "if(Objects.equals(sessionId, ", str_requeteSite(langueNom), "_.getSessionId()) {").l();
									}
									else if(classeRolesTrouves) {
										tl(tIndex + 7, "if(");
										tl(tIndex + 9, "CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRessource(langueNom), "(), ROLES)");
										tl(tIndex + 9, "|| CollectionUtils.containsAny(", str_requeteSite(langueNom), "_.get", str_UtilisateurRolesRoyaume(langueNom), "(), ROLES)");
										tl(tIndex + 9, ") {");
									}
									else {
										t(tIndex + 7).s(classePrefixe, "{").l();
									}
				
									t(tIndex + 8).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
	
										t(tIndex + 9).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell w3-left-align w3-cell-top ").dfl();
										t(tIndex + 10).s("{ ", classePrefixe, "e(\"button\")").l();
										t(tIndex + 11).dal("tabindex", "-1");
										t(tIndex + 11).dal("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-", contexteCouleur, " ");
										t(tIndex + 10).l(".a(\"onclick\", \"", str_enleverLueur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); $('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "').val(null); patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:' + $('#", classeNomSimple, "Form :input[name=", classeVarClePrimaire, "]').val() }], 'set", entiteVarCapitalise, "', null, function() { ", str_ajouterLueur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }, function() { ", str_ajouterErreur(langueNom), "($('#\", ", str_classeApiMethodeMethode(langueNom), ", \"_", entiteVar, "')); }); \")");
										t(tIndex + 11).dfl();
										t(tIndex + 11).s(classePrefixe, "e(\"i\")").da("class", "far fa-eraser ").df().dgl("i");
										t(tIndex + 10).l("} ", classePrefixe, "g(\"button\");");
										t(tIndex + 9).l("} ", classePrefixe, "g(\"div\");");
	
									t(tIndex + 8).l("}");
	
									t(tIndex + 7).s("}").l();
								}
				
								t(tIndex + 6).l("} ", classePrefixe, "g(\"div\");");
								t(tIndex + 5).l("} ", classePrefixe, "g(\"div\");");
								t(tIndex + 4).l("} ", classePrefixe, "g(\"div\");");
								t(tIndex + 3).l("} ", classePrefixe, "g(\"div\");");
							}
							else if(!(entiteAttribuer)) {
				
								t(tIndex + 3).l("if(\"Page\".equals(", str_classeApiMethodeMethode(langueNom), ")) {");
					
									t(tIndex + 4).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-padding ").dfl();
									t(tIndex + 5).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-card ").dfl();
					
									if(entiteNomAffichage != null) {
										t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row w3-", contexteCouleur, " ").dfl();
										t(tIndex + 7).s(classePrefixe, "e(\"label\")").da("class", "").df().dsxq(entiteNomAffichage).dgl("label");
										t(tIndex + 6).l("} ", classePrefixe, "g(\"div\");");
									}
									t(tIndex + 6).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell-row  ").dfl();
									t(tIndex + 7).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-cell ").dfl();
									t(tIndex + 8).s("{ ", classePrefixe, "e(\"div\")").da("class", "w3-rest ").dfl();
									if(StringUtils.equals(classeVarClePrimaire, entiteVar) && classeVarUrlPk != null) {
										t(tIndex + 9).s(classePrefixe, "e(\"a\").a(\"href\", ", classeVarUrlPk, ")").df().s(".sx(str", entiteVarCapitalise, "())").dgl("a");
									} else if(StringUtils.equals(classeVarId, entiteVar) && classeVarUrlId != null) {
										t(tIndex + 9).s(classePrefixe, "e(\"a\").a(\"href\", ", classeVarUrlId, ")").df().s(".sx(str", entiteVarCapitalise, "())").dgl("a");
									} else {
										t(tIndex + 9).s(classePrefixe, "e(\"span\").a(\"class\", \"var", classeNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " \").f()").s(".sx(str", entiteVarCapitalise, "())").l(".g(\"span\");");
									}
									t(tIndex + 8).l("} ", classePrefixe, "g(\"div\");");
									t(tIndex + 7).l("} ", classePrefixe, "g(\"div\");");
									t(tIndex + 6).l("} ", classePrefixe, "g(\"div\");");
									t(tIndex + 5).l("} ", classePrefixe, "g(\"div\");");
									t(tIndex + 4).l("} ", classePrefixe, "g(\"div\");");
								t(tIndex + 3).l("}");
							}
				
				//			if().da("class", objets).da("class", "w3-cell w3-cell-middle w3-center w3-mobile ").dfl();
							t(2).l("} ", classePrefixe, "g(\"div\");");
							tl(1, "}");  
						}
					}
				}
	
				for(String classeEcrireMethode : new String[] { "htmlBody" }) {
					if(entiteEcrireMethodes.contains(classeEcrireMethode)) {
						if(entiteNomSimpleCompletGenerique == null && "htmlBody".equals(classeEcrireMethode)) {
							tl(1, "public void ", classeEcrireMethode, entiteVarCapitalise, "(", entiteNomSimpleComplet, " o) {");
							if(classePartsPagePart != null && entiteClassesSuperEtMoiSansGen.contains(classePartsPagePart.nomCanonique(langueNom))) {
								// do stuff here. 
								if(!entiteValsEcrivain.getEmpty()) {
									s(entiteValsEcrivain);
								}
							}
							tl(1, "}");
							tl(1, "public void ", classeEcrireMethode, entiteVarCapitalise, "() {");
							tl(2, entiteVar, ".html", str_Avant(langueNom), "();");
							tl(2, classeEcrireMethode, entiteVarCapitalise, "(", entiteVar, ");");
							tl(2, entiteVar, ".html", str_Apres(langueNom), "();");
							tl(1, "}");
						}
					}
				}
			}

			if(entiteSolrNomSimple != null) {

				///////////////
				// staticSet //
				///////////////
	
				wStaticSet.tl(2, "case \"", entiteVar, "\":");
				wStaticSet.tl(3, "return ", classeNomSimple, ".staticSet", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, o);");
	
				////////////////
				// staticSolr //
				////////////////
	
				wStaticSolr.tl(2, "case \"", entiteVar, "\":");
				if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
					wStaticSolr.tl(3, "return ", classeNomSimple, ".staticSolr", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, (", entiteNomSimpleGenerique, ")o);");
				}
				else {
					wStaticSolr.tl(3, "return ", classeNomSimple, ".staticSolr", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, (", entiteNomSimpleComplet, ")o);");
				}
	
				///////////////////
				// staticSolrStr //
				///////////////////
	
				wStaticSolrStr.tl(2, "case \"", entiteVar, "\":");
				if("java.util.List".equals(entiteNomCanonique) || "java.util.ArrayList".equals(entiteNomCanonique)) {
					wStaticSolrStr.tl(3, "return ", classeNomSimple, ".staticSolrStr", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, (", entiteSolrNomSimple == null ? "Object" : StringUtils.substringBeforeLast(StringUtils.substringAfter(entiteSolrNomSimple, "<"), ">"), ")o);");
				}
				else {
					wStaticSolrStr.tl(3, "return ", classeNomSimple, ".staticSolrStr", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, (", entiteSolrNomSimple == null ? "Object" : entiteSolrNomSimple, ")o);");
				}
	
				//////////////////
				// staticSolrFq //
				//////////////////
	
				wStaticSolrFq.tl(2, "case \"", entiteVar, "\":");
				wStaticSolrFq.tl(3, "return ", classeNomSimple, ".staticSolrFq", entiteVarCapitalise, "(", str_requeteSite(langueNom), "_, o);");
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
				wInitLoin.tl(3, entiteVar, str_Promesse(langueNom), "().onSuccess(", entiteVar, " -> {");
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
				tl(3, entiteVar, ".set", str_RequeteSite(langueNom), "_(", str_requeteSite(langueNom), "_);");
			}
	
			/////////////////
			// codeIndexer //
			/////////////////
			o = wIndexer;
			if(classeIndexe && entiteIndexeOuStocke) {
				tl(2, "if(", entiteVar, " != null) {");
				if(StringUtils.isNotEmpty(classeVarCleUnique) && entiteCleUnique) {
					// cleUnique
					tl(3, "document.addField(\"", classeVarCleUnique, "\", ", entiteVar, ");");
				}
				if(entiteCrypte) {
					// crypte
					tl(3, "String valCrypte = requeteSite.crypterStr(", entiteVar, ");");
					tl(3, "document.addField(\"", entiteVar, "_encrypted", entiteSuffixeType, "\"", "valCrypte);");
				}
				if(entiteIncremente) {
					// crypte
					tl(3, "document.addField(\"", entiteVar, "_incremented", "\", new java.util.HashMap<String, ", entiteNomSimple, ">() {{ put(\"inc\"", ("Long".equals(entiteNomSimple.toString()) ? "1L" : "1"), "); }});");
				}
				if(entiteSuggere) {
					// suggere
					if(entiteNomSimple.equals("Timestamp")) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
					}
					else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_DATE_TIME.format(", entiteVar, "));");
					}
					else if(entiteNomCanonique.toString().equals(LocalTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_TIME.format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_DATE_TIME.format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", "\", DateTimeFormatter.ISO_DATE_TIME.format(", entiteVar, ".atStartOfDay(ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
					}
					else {
						tl(3, "document.addField(\"", entiteVar, "_suggested", "\", ", entiteVar, ");");
					}
				}
				if(entiteTexte) {
					if(entiteLangue == null)
						entiteLangue = langueNom;
					if("frFR".equals(entiteLangue) || "esES".equals(entiteLangue)) {
						if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
							tl(3, "for(", entiteNomSimpleCompletGenerique, " o : ", entiteVar, ") {");
							tl(4, "document.addField(\"", entiteVar, "_text_", entiteLangue, "\", o", "String".equals(entiteNomSimpleCompletGenerique) ? "" : ".toString()", ");");
							tl(3, "}");
						}
						else {
							tl(3, "document.addField(\"", entiteVar, "_text_", entiteLangue, "\", ", entiteVar, "", "String".equals(entiteNomSimpleCompletGenerique) ? "" : ".toString()", ");");
						}
					}
					else {
						if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
							tl(3, "for(", entiteNomSimpleCompletGenerique, " o : ", entiteVar, ") {");
							tl(4, "document.addField(\"", entiteVar, "_text_enUS\", o", "String".equals(entiteNomSimpleCompletGenerique) ? "" : ".toString()", ");");
							tl(3, "}");
						}
						else {
							tl(3, "document.addField(\"", entiteVar, "_text_enUS", "\", ", entiteVar, "", "String".equals(entiteNomSimpleCompletGenerique) ? "" : ".toString()", ");");
						}
					}
				}
	
				if(entiteNomSimple != null && entiteIndexe && !entiteSuggere) {
					// indexe
					if(entiteNomSimple.equals("Chaine")) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", ", entiteVar, ");");
					}
					else if(entiteNomSimple.equals("Timestamp")) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
					}
					else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(", entiteVar, ".toInstant(), ZoneId.of(\"UTC\"))));");
					}
					else if(entiteNomCanonique.toString().equals(LocalTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"HH:mm\").format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(", entiteVar, ".atStartOfDay(ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
					}
					else if(entiteNomSimple.toString().equals("BigDecimal")) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", ", entiteVar, ".doubleValue());");
					}
					else if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
						tl(3, "for(", entiteNomCanoniqueGenerique, " o : ", entiteVar, ") {");
						tl(4, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", o);");
						tl(3, "}");
					}
					else {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", ", entiteVar, ");");
					}
				}
	
				if(entiteStocke) {
					// stocke
					if(entiteNomSimple.equals("Chaine")) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", ", entiteVar, ");");
					}
					else if(entiteNomSimple.equals("Timestamp")) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
					}
					else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(ZonedDateTime.ofInstant(", entiteVar, ".toInstant(), ZoneId.of(\"UTC\"))));");
					}
					else if(entiteNomCanonique.toString().equals(LocalTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"HH:mm\").format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\").format(", entiteVar, ".atStartOfDay(ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))).toInstant().atZone(ZoneId.of(\"Z\"))));");
					}
					else if(entiteNomSimple.toString().equals("BigDecimal")) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", ", entiteVar, ".doubleValue());");
					}
					else if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList") || entiteNomSimple.equals("Set") || entiteNomSimple.equals("HashSet")) {
						tl(3, "for(", entiteNomCanoniqueGenerique, " o : ", entiteVar, ") {");
						tl(4, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", o);");
						tl(3, "}");
					}
					else {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", ", entiteVar, ");");
					}
				}
				tl(2, "}");
			}

			if(classeIndexe) {
				if(entiteIndexe) {
					wVarIndexe.tl(3, "case \"", entiteVar, "\":");
					if(entiteSuggere)
						wVarIndexe.tl(4, "return \"", entiteVar, "_suggested", "\";");
					else if(entiteTexte && entiteLangue != null)
						wVarIndexe.tl(4, "return \"", entiteVar, "_text_" + entiteLangue, "\";");
					else
						wVarIndexe.tl(4, "return \"", entiteVar, "_indexed", entiteSuffixeType, "\";");
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

			if(entiteFacetsTrouves) {
				for(String entiteFacet : entiteFacets) {
					if("terms".equals(entiteFacet))
						wFacets.tl(3, str_listeRecherche(langueNom), ".add(\"json.facet\", \"{", entiteFacet, "_", entiteVar, ":{terms:{field:", entiteVar, "_indexed", entiteSuffixeType, "}}}\");");
					else
						wFacets.tl(3, str_listeRecherche(langueNom), ".add(\"json.facet\", \"{", entiteFacet, "_", entiteVar, ":'", entiteFacet, "(", entiteVar, "_indexed", entiteSuffixeType, ")'}\");");
				}
			}

			if(entiteAttribuer && !classesNomSimpleFacetFor.contains(entiteAttribuerNomSimple)) {
				wIndexerFacetFor.l();
				wIndexerFacetFor.tl(5, "if(\"", entiteAttribuerNomSimple, "\".equals(", str_classeNomSimple(langueNom), "2) && ", classeVarClePrimaire, "2 != null) {");
				wIndexerFacetFor.tl(6, str_ListeRecherche(langueNom), "<", entiteAttribuerNomSimple, "> ", str_listeRecherche(langueNom), "2 = new ", str_ListeRecherche(langueNom), "<", entiteAttribuerNomSimple, ">();");
				wIndexerFacetFor.tl(6, str_listeRecherche(langueNom), "2.set", str_Stocker(langueNom), "(true);");
				wIndexerFacetFor.tl(6, str_listeRecherche(langueNom), "2.setQuery(\"*:*\");");
				wIndexerFacetFor.tl(6, str_listeRecherche(langueNom), "2.setC(", entiteAttribuerNomSimple, ".class);");
				wIndexerFacetFor.tl(6, str_listeRecherche(langueNom), "2.addFilterQuery(\"", classeVarClePrimaire, "_indexed_long:\" + ", classeVarClePrimaire, "2);");
				wIndexerFacetFor.tl(6, str_listeRecherche(langueNom), "2.setRows(1);");
				wIndexerFacetFor.tl(6, "futures.add(Future.future(promise2 -> {");
				wIndexerFacetFor.tl(7, str_listeRecherche(langueNom), "2.", str_promesseLoin(langueNom), str_ListeRecherche(langueNom), "(", str_requeteSite(langueNom), ").onSuccess(b -> {");
				wIndexerFacetFor.tl(8, entiteAttribuerNomSimple, " o2 = ", str_listeRecherche(langueNom), "2.getList().stream().findFirst().orElse(null);");
				wIndexerFacetFor.tl(8, "if(o2 != null) {");
				wIndexerFacetFor.tl(9, "JsonObject params = new JsonObject();");
				wIndexerFacetFor.tl(9, "params.put(\"body\", new JsonObject());");
				wIndexerFacetFor.tl(9, "params.put(\"cookie\", new JsonObject());");
				wIndexerFacetFor.tl(9, "params.put(\"path\", new JsonObject());");
				wIndexerFacetFor.tl(9, "params.put(\"query\", new JsonObject().put(\"q\", \"*:*\").put(\"fq\", new JsonArray().add(\"pk:\" + pk2)));");
				wIndexerFacetFor.tl(9, "JsonObject context = new JsonObject().put(\"params\", params).put(\"user\", ", str_requeteSite(langueNom), ".get", str_PrincipalJson(langueNom), "());");
				wIndexerFacetFor.tl(9, "JsonObject json = new JsonObject().put(\"context\", context);");
				wIndexerFacetFor.tl(9, "eventBus.request(\"", appliNom, "-", langueNom, "-", entiteAttribuerNomSimple, "\", json, new DeliveryOptions().addHeader(\"action\", \"patch", entiteAttribuerNomSimple, "\")).onSuccess(c -> {");
				wIndexerFacetFor.tl(10, "promise2.complete();");
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
					tl(5, "o", classeNomSimple, ".set", entiteVarCapitalise, "((", entiteNomSimpleComplet, ")val);");
				}
				tl(4, "if(!", str_sauvegardes(langueNom), ".contains(\"", entiteVar, "\"))");
				tl(5, str_sauvegardes(langueNom), ".add(\"", entiteVar, "\");");
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
							wAttribuerSql.s("SELECT ", classeVarClePrimaire, " as pk2, '", entiteVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						} else {
							wAttribuerSql.s("SELECT ", classeVarClePrimaire, " as pk1, '", entiteVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
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
							wAttribuerSql.s("SELECT ", entiteVar, " as pk2, '", entiteVar, "' from ", classeNomSimple, " where ", classeVarClePrimaire, "=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						} else {
							wAttribuerSql.s("SELECT ", entiteVar, " as pk1, '", entiteVar, "' from ", classeNomSimple, " where ", classeVarClePrimaire, "=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						}
					} else {
						if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
							wAttribuerSql.s("SELECT ", entiteVar, " as pk2, '", entiteVar, "' from ", classeNomSimple, " where ", classeVarClePrimaire, "=$" + wAttribuerSqlNum);
							if(!wAttribuerSqlParams.getEmpty())
								wAttribuerSqlParams.s(", ");
							wAttribuerSqlParams.s("pk");
							wAttribuerSqlNum++;
						} else {
							wAttribuerSql.s("SELECT ", classeVarClePrimaire, " as pk1, '", entiteVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
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
//							o.s("2SELECT ", entiteAttribuerVar, " as pk1, ", classeVarClePrimaire, " as pk2, '", entiteVar, "', '", entiteAttribuerVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
//					}
//					else {
//						if("array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson))
//							o.s("3SELECT pk1, pk2, '", entiteAttribuerVar, "', '", entiteVar, "' from ", entiteAttribuerNomSimple, entiteAttribuerVar, "_", classeNomSimple, entiteVar, " where pk2=$" + wAttribuerSqlNum);
//						else
//							o.s("4SELECT ", classeVarClePrimaire, " as pk1, ", entiteAttribuerVar, " as pk2, '", entiteAttribuerVar, "', '", entiteVar, "' from ", entiteAttribuerNomSimple, " where pk=$" + wAttribuerSqlNum);
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
//							o.s(" as pk1, ", classeVarClePrimaire, " as pk2, '", entiteVar, "', '", entiteAttribuerVar, "' from ", entiteAttribuerNomSimple, " where ", entiteAttribuerVar, "=$" + wAttribuerSqlNum);
//						}
//					}
//					else {
//						if("array".equals(entiteAttribuerTypeJson) && "array".equals(entiteTypeJson))
//							o.s("7SELECT pk1, pk2, '", entiteAttribuerVar, "', '", entiteVar, "' from ", entiteAttribuerNomSimple, entiteAttribuerVar, "_", classeNomSimple, entiteVar, " where pk2=$" + wAttribuerSqlNum);
//						else {
//							o.s("8SELECT ", classeVarClePrimaire, " as pk1, $", wAttribuerSqlNum);
//							wAttribuerSqlNum++;
//							o.s(" as pk2, '", entiteAttribuerVar, "', '", entiteVar, "' from ", entiteAttribuerNomSimple, " where pk=$" + wAttribuerSqlNum);
//						}
//					}
//					wAttribuerSqlNum++;
//				}
			}	
	
			/////////////
			// ", str_definir(langueNom), " //
			/////////////

			o = wDefinir;
			if(classeIndexe && (BooleanUtils.isTrue(entiteDefinir) || BooleanUtils.isTrue(entiteAttribuer) && !"array".equals(entiteTypeJson))) {
					tl(3, "case \"", entiteVar.toLowerCase(), "\":");
					if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
						tl(4, "if(val != null)");
						tl(5, "add", entiteVarCapitalise, "(val);");
						tl(4, "if(!", str_sauvegardes(langueNom), ".contains(\"", entiteVar, "\"))");
						tl(5, "", str_sauvegardes(langueNom), ".add(\"", entiteVar, "\");");
					}
					else {
						tl(4, "if(val != null)");
						tl(5, "set", entiteVarCapitalise, "(val);");
						tl(4, str_sauvegardes(langueNom), ".add(\"", entiteVar, "\");");
					}
					tl(4, "return val;");
			}	

			o = wDefinirObjet;
			if(classeIndexe && BooleanUtils.isTrue(entiteDefinir) || BooleanUtils.isTrue(entiteAttribuer) && !"array".equals(entiteTypeJson)) {
					tl(3, "case \"", entiteVar.toLowerCase(), "\":");
					if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
						tl(4, "if(val instanceof ", entiteNomSimpleComplet, ")");
						tl(5, "add", entiteVarCapitalise, "((", entiteNomSimpleComplet, ")val);");
						tl(4, "if(!", str_sauvegardes(langueNom), ".contains(\"", entiteVar, "\"))");
						tl(5, "", str_sauvegardes(langueNom), ".add(\"", entiteVar, "\");");
					}
					else {
						tl(4, "if(val instanceof ", entiteNomSimpleComplet, ")");
						tl(5, "set", entiteVarCapitalise, "((", entiteNomSimpleComplet, ")val);");
						if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
							tl(4, "else if(val instanceof OffsetDateTime)");
							tl(5, "set", entiteVarCapitalise, "(((OffsetDateTime)val).atZoneSameInstant(ZoneId.of(", str_requeteSite(langueNom), "_.get", str_Config(langueNom), "().getString(", classePartsConfigCles.nomSimple(langueNom), ".", str_SITE_ZONE(langueNom), "))));");
						}
						tl(4, str_sauvegardes(langueNom), ".add(\"", entiteVar, "\");");
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
						tl(3, "if(", str_sauvegardes(langueNom), ".contains(\"", entiteVar, "\")) {");
						tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_suggested", "\");");
						tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
						tl(3, "}");
					}
//					else if(entiteIncremente) {
//						tl(3, "if(", str_sauvegardes(langueNom), ".contains(\"", entiteVar, "\")) {");
//						tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_incremented", "\");");
//						tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//						tl(3, "}");
//					}
//					else if(entiteCleUnique) {
					else if(entiteCleUnique) {
						tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "\");");
						tl(3, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
					}
					else if(entiteClePrimaire) {
						tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_stored", entiteSuffixeType, "\");");
						tl(3, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
					}
					else if(entiteCrypte) {
						tl(3, "if(", str_sauvegardes(langueNom), ".contains(\"", entiteVar, "\")) {");
						if(siteCrypte)
							tl(4, entiteSolrNomSimple, " ", entiteVar, " = requeteSite.decrypterStr((", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_encrypted", entiteSuffixeType, "\"));");
						else
							tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_encrypted", entiteSuffixeType, "\");");
						tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
						tl(3, "}");
					}
					else if(entiteAttribuer) {
						tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_stored", entiteSuffixeType, "\");");
						tl(3, "if(", entiteVar, " != null)");
						if(StringUtils.contains(entiteSolrNomCanonique, "<"))
							tl(4, "o", classeNomSimple, ".", entiteVar, ".addAll(", entiteVar, ");");
						else
							tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
					}
					else {
						tl(3, "if(", str_sauvegardes(langueNom), ".contains(\"", entiteVar, "\")) {");
						tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_stored", entiteSuffixeType, "\");");
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
			if(entiteCrypte || entiteStocke || entiteCleUnique || entiteSuggere || entiteIncremente || entiteTexte) {

//				if(entiteTexte) {
//					if("frFR".equals(langueNom) || "esES".equals(langueNom))
//						tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_text_", langueNom, "\");");
//					else
//						tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_text_enUS\");");
//					tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//				}
				if(entiteSuggere) {
					tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_suggested", "\");");
					tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
				}
//				else if(entiteIncremente) {
//					tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_incremented", "\");");
//					tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
//				}
//				else if(entiteCleUnique) {
				else if(entiteCleUnique) {
					tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "\");");
					tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
				}
				else if(entiteCrypte) {
					if(siteCrypte)
						tl(2, entiteSolrNomSimple, " ", entiteVar, " = requeteSite.decrypterStr((", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_encrypted", entiteSuffixeType, "\"));");
					else
						tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_encrypted", entiteSuffixeType, "\");");
					tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
				}
				else {
					if(StringUtils.contains(entiteSolrNomCanonique, "<")) {
						if(entiteCouverture) {
							tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(Optional.ofNullable(solrDocument.get(\"", entiteVar, "_stored", entiteSuffixeType, "\")).map(v -> v.toString()).orElse(null));");
						}
						else {
							tl(2, "Optional.ofNullable((List<?>)solrDocument.get(\"", entiteVar, "_stored", entiteSuffixeType, "\")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {");
							tl(3, "o", classeNomSimple, ".add", entiteVarCapitalise, "(v.toString());");
							tl(2, "});");
						}
					}
					else {
						tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(Optional.ofNullable(solrDocument.get(\"", entiteVar, "_stored", entiteSuffixeType, "\")).map(v -> v.toString()).orElse(null));");
					}
				}

			}
	
			/////////////////
			// codeApiChamps //
			/////////////////
			o = wApiEntites;
			tl(1, "public static final String VAR_", entiteVar, " = \"", entiteVar, "\";");
	//		if(classeIndexe) {
	//			if(entiteIndexe)
	//				tl(1, "public static final String ENTITE_VAR_INDEXE_", entiteVar, " = \"", entiteVar, "_indexed", entiteSuffixeType, "\";");
	//			if(entiteStocke)
	//				tl(1, "public static final String ENTITE_VAR_STOCKE_", entiteVar, " = \"", entiteVar, "_stored", entiteSuffixeType, "\";");
	//			if(entiteCrypte)
	//				tl(1, "public static final String ENTITE_VAR_CRYPTE_", entiteVar, " = \"", entiteVar, "_encrypted", entiteSuffixeType, "\";");
	//		}
	//		if(entiteAttribuer)
	//			tl(1, "public static final String ENTITE_VAR_", entiteVar, "_ATTRIBUER_", entiteAttribuerNomSimple, "_", entiteAttribuerVar, " = \"", entiteAttribuerVar, "\";");
	
			if(entiteDefinir || entiteAttribuer || entiteIndexe || entiteStocke) {
		
				//////////////////
				// requeteApi //
				//////////////////
		
				wRequeteApi.tl(3, "if(!Objects.equals(", entiteVar, ", original.get", entiteVarCapitalise, "()))");
				wRequeteApi.tl(4, str_requeteApi(langueNom), ".addVars(\"", entiteVar, "\");");
		
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
		
				wToString.tl(2, "sb.append( \"" + (entiteIndice > 0 ? ", " : "") + entiteVar + ": " + ("String".equals(entiteNomSimpleComplet) ? "\\\"" : "") + "\" ).append(" + entiteVar + "" + ("String".equals(entiteNomSimpleComplet) ? ").append( \"\\\"\" " : "") + ");");
		
				entiteIndice++;
			}
		}
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
	 * r: dejaInitialise
	 * r.enUS: alreadyInitialized
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
	 * r: solrDocument
	 * r.enUS: solrDocument
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
	 * r: appliChemin
	 * r.enUS: appPath
	 * r: configChemin
	 * r.enUS: configPath
	 * r: siteUrlBase
	 * r.enUS: siteBaseUrl
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: classePageCheminGen
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
	public void genCodeClasseFin(String langueNom) throws Exception {

		//////////////////
		// codeInitLoin //
		//////////////////
		if(classeInitLoin && classePartsRequeteSite != null) {
//			wInitLoin.tl(3, "", dejaInitialise(langueNom), "", classeNomSimple, " = true;");
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
					wInitLoin.l("public Future<Void> ", str_promesseLoin(langueNom), str_PourClasse(langueNom), "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_) {");
					wInitLoin.tl(2, "return ", str_promesseLoin(langueNom), classeNomSimple, "(", str_requeteSite(langueNom), "_);");
					wInitLoin.tl(1, "}");  
				} else {
					wInitLoin.s("public void ", str_initLoin(langueNom), str_PourClasse(langueNom), "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_)");
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
					wInitLoin.tl(2, str_initLoin(langueNom), classeNomSimple, "(", str_requeteSite(langueNom), "_);");
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
			tl(1, "public void ", str_requeteSite(langueNom), str_PourClasse(langueNom), "(", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_) {");
			tl(2, str_requeteSite(langueNom), classeNomSimple, "(", str_requeteSite(langueNom), "_);");
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
				tl(4, "return super.", str_obtenir(langueNom), classeNomSimpleSuperGenerique, "(var);");

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
				tl(4, "return super.", str_attribuer(langueNom), classeNomSimpleSuperGenerique, "(var, val);");

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
		wApiEntites.flushClose();
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

		if(classeInitLoin && (classeEtendBase || classeEstBase)) {
			l(); 
			tl(1, "///////////////");
			tl(1, "// staticSet //");
			tl(1, "///////////////");
			tl(0);
			t(1);
			s("public static Object staticSet", str_PourClasse(langueNom), "(String ", str_entite(langueNom), "Var, ", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o)");
			l(" {");
			tl(2, "return staticSet", classeNomSimple, "(", str_entite(langueNom), "Var,  ", str_requeteSite(langueNom), "_, o);");
			tl(1, "}");
			t(1);
			s("public static Object staticSet", classeNomSimple, "(String ", str_entite(langueNom), "Var, ", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o)");
			l(" {");
			tl(2, "switch(", str_entite(langueNom), "Var) {");
			s(wStaticSet.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSet", classeNomSimpleSuperGenerique, "(", str_entite(langueNom), "Var,  ", str_requeteSite(langueNom), "_, o);");

			tl(2, "}");
			tl(1, "}");

			l(); 
			tl(1, "////////////////");
			tl(1, "// staticSolr //");
			tl(1, "////////////////");
			tl(0);
			t(1);
			s("public static Object staticSolr", str_PourClasse(langueNom), "(String ", str_entite(langueNom), "Var, ", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, Object o)");
			l(" {");
			tl(2, "return staticSolr", classeNomSimple, "(", str_entite(langueNom), "Var,  ", str_requeteSite(langueNom), "_, o);");
			tl(1, "}");
			t(1);
			s("public static Object staticSolr", classeNomSimple, "(String ", str_entite(langueNom), "Var, ", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, Object o)");
			l(" {");
			tl(2, "switch(", str_entite(langueNom), "Var) {");
			s(wStaticSolr.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSolr", classeNomSimpleSuperGenerique, "(", str_entite(langueNom), "Var,  ", str_requeteSite(langueNom), "_, o);");

			tl(2, "}");
			tl(1, "}");

			l(); 
			tl(1, "///////////////////");
			tl(1, "// staticSolrStr //");
			tl(1, "///////////////////");
			tl(0);
			t(1);
			s("public static String staticSolrStr", str_PourClasse(langueNom), "(String ", str_entite(langueNom), "Var, ", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, Object o)");
			l(" {");
			tl(2, "return staticSolrStr", classeNomSimple, "(", str_entite(langueNom), "Var,  ", str_requeteSite(langueNom), "_, o);");
			tl(1, "}");
			t(1);
			s("public static String staticSolrStr", classeNomSimple, "(String ", str_entite(langueNom), "Var, ", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, Object o)");
			l(" {");
			tl(2, "switch(", str_entite(langueNom), "Var) {");
			s(wStaticSolrStr.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSolrStr", classeNomSimpleSuperGenerique, "(", str_entite(langueNom), "Var,  ", str_requeteSite(langueNom), "_, o);");

			tl(2, "}");
			tl(1, "}");

			l(); 
			tl(1, "//////////////////");
			tl(1, "// staticSolrFq //");
			tl(1, "//////////////////");
			tl(0);
			t(1);
			s("public static String staticSolrFq", str_PourClasse(langueNom), "(String ", str_entite(langueNom), "Var, ", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o)");
			l(" {");
			tl(2, "return staticSolrFq", classeNomSimple, "(", str_entite(langueNom), "Var,  ", str_requeteSite(langueNom), "_, o);");
			tl(1, "}");
			t(1);
			s("public static String staticSolrFq", classeNomSimple, "(String ", str_entite(langueNom), "Var, ", classePartsRequeteSite.nomSimple(langueNom), " ", str_requeteSite(langueNom), "_, String o)");
			l(" {");
			tl(2, "switch(", str_entite(langueNom), "Var) {");
			s(wStaticSolrFq.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".staticSolrFq", classeNomSimpleSuperGenerique, "(", str_entite(langueNom), "Var,  ", str_requeteSite(langueNom), "_, o);");

			tl(2, "}");
			tl(1, "}");
		}

		if(classeInitLoin && (classeEtendBase || classeEstBase)) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", str_definir(langueNom), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			s("public boolean ", str_definir(langueNom), str_PourClasse(langueNom), "(String var, String val)");
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
			tl(5, "o = ", str_definir(langueNom), "", classeNomSimple, "(v, val);");
			tl(4, "else if(o instanceof ", classePartsCluster.nomSimple(langueNom), ") {");
			tl(5, classePartsCluster.nomSimple(langueNom), " o", classePartsCluster.nomSimple(langueNom), " = (", classePartsCluster.nomSimple(langueNom), ")o;");
			tl(5, "o = o", classePartsCluster.nomSimple(langueNom), ".", str_definir(langueNom), str_PourClasse(langueNom), "(v, val);");
			tl(4, "}");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object ", str_definir(langueNom), "", classeNomSimple, "(String var, String val) {");
			tl(2, "switch(var.toLowerCase()) {");
			s(wDefinir.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return super.", str_definir(langueNom), "", classeNomSimpleSuperGenerique, "(var, val);");

			tl(2, "}");
			tl(1, "}");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			s("public boolean ", str_definir(langueNom), str_PourClasse(langueNom), "(String var, Object val)");
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
			tl(5, "o = ", str_definir(langueNom), "", classeNomSimple, "(v, val);");
			tl(4, "else if(o instanceof ", classePartsCluster.nomSimple(langueNom), ") {");
			tl(5, classePartsCluster.nomSimple(langueNom), " o", classePartsCluster.nomSimple(langueNom), " = (", classePartsCluster.nomSimple(langueNom), ")o;");
			tl(5, "o = o", classePartsCluster.nomSimple(langueNom), ".", str_definir(langueNom), str_PourClasse(langueNom), "(v, val);");
			tl(4, "}");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object ", str_definir(langueNom), "", classeNomSimple, "(String var, Object val) {");
			tl(2, "switch(var.toLowerCase()) {");
			s(wDefinirObjet.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return super.", str_definir(langueNom), "", classeNomSimpleSuperGenerique, "(var, val);");

			tl(2, "}");
			tl(1, "}");
		}

//		if(classeSauvegarde) {
//			l(); 
//			tl(1, "/////////////////");
//			tl(1, "// ", str_sauvegardes(langueNom), " //");
//			tl(1, "/////////////////");
//			tl(0);
//			tl(1, "protected List<String> ", str_sauvegardes(langueNom), " = new ArrayList<String>();");
//		}

		/////////////////
		// codePeupler //
		/////////////////
		if(classeIndexe) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", str_peupler(langueNom), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(BooleanUtils.isTrue(classeEtendBase))
				s("@Override ");
			l("public void ", str_peupler(langueNom), str_PourClasse(langueNom), "(SolrDocument solrDocument) {");
			tl(2, str_peupler(langueNom), classeNomSimple, "(solrDocument);");
			tl(1, "}");
			tl(1, "public void ", str_peupler(langueNom), classeNomSimple, "(SolrDocument solrDocument) {");
			tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
			tl(2, "", str_sauvegardes(langueNom), " = (List<String>)solrDocument.get(\"", str_sauvegardes(langueNom), "_stored_strings\");");
			tl(2, "if(", str_sauvegardes(langueNom), " != null) {");
			s(wPeupler.toString());
			tl(2, "}");
			if(BooleanUtils.isTrue(classeEtendBase)) {
				tl(0);
				tl(2, "super.", str_peupler(langueNom), classeNomSimpleSuperGenerique, "(solrDocument);");
			}

			tl(1, "}");
		}	

		/////////////////
		// codeIndexer //
		/////////////////
		if(classeImage) {
			l(); 
			tl(1, "///////////");
			tl(1, "// image //");
			tl(1, "///////////");
			tl(0);
			tl(1, "public static void image() {");
			tl(2, "try {");
			tl(3, "DefaultExecutor executeur = new DefaultExecutor();");
			for(String classePageMethode : classeApiMethodes) {
	
				String classePageCheminGen = (String)classeDoc.get("classePageCheminGen" + classePageMethode  + "_stored_string");
				String classePageChemin = (String)classeDoc.get("classePageChemin" + classePageMethode  + "_stored_string");
				String classePageCheminCss = (String)classeDoc.get("classePageCheminCss" + classePageMethode  + "_stored_string");
				String classePageCheminJs = (String)classeDoc.get("classePageCheminJs" + classePageMethode  + "_stored_string");
				String classePageUriMethode = (String)classeDoc.get("classeApiUri" + classePageMethode + "_stored_string");
				String classePageLangueNom = (String)classeDoc.get("classePageLangueNom" + classePageMethode + "_stored_string");
				String classePageNomSimple = (String)classeDoc.get("classePageNomSimple" + classePageMethode  + "_stored_string");
		
				if(classePageCheminGen != null) {
			
					tl(3, "{");
					tl(4, "new File(\"", appliChemin, "-static/png", StringUtils.substringBeforeLast(classePageUriMethode, "/"), "\").mkdirs();");
					tl(4, "executeur.execute(CommandLine.parse(\"/usr/bin/CutyCapt --min-height=200 --url=", siteUrlBase, classePageUriMethode, "?pageRecapituler=true --out=", appliChemin, "-static/png", classePageUriMethode, "-999.png\"));");
					tl(4, "BufferedImage img = ImageIO.read(new File(\"", appliChemin, "-static/png", classePageUriMethode, "-999.png\"));");
					tl(4, "System.out.println(\"", classePageNomSimple, "\");");
					tl(4, "System.out.println(\" * ImageLargeur.", classePageLangueNom, ": \" + img.getWidth());");
					tl(4, "System.out.println(\" * ImageHauteur.", classePageLangueNom, ": \" + img.getHeight());");
					tl(3, "}");
				}
			}
			tl(2, "} catch(Exception e) {");
			tl(3, "ExceptionUtils.rethrow(e);");
			tl(2, "}");
			tl(1, "}");
		}
		if(classeIndexe && classePartsRequeteSite != null) {

			tl(0);
			tl(1, "public void ", str_indexer(langueNom), classeNomSimple, "(SolrInputDocument document) {");
			s(wIndexer.toString());
			if(classeEtendBase && !classeEstBase) {
				tl(2, "super.", str_indexer(langueNom), "", classeNomSimpleSuperGenerique, "(document);");
				tl(0);
			}
			l("\t}");

			/////////
			// var //
			/////////
			l();
			tl(1, "public static String var", str_Indexe(langueNom), classeNomSimple, "(String ", str_entite(langueNom), "Var) {");
			tl(2, "switch(", str_entite(langueNom), "Var) {");
			s(wVarIndexe);
			tl(3, "default:");
			if(classeEstBase)
//				tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", str_nest_pas_une_entite_indexe(langueNom), ". \", ", str_entite(langueNom), "Var));");
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".var", str_Indexe(langueNom), classeNomSimpleSuperGenerique, "(", str_entite(langueNom), "Var);");
			tl(2, "}");
			tl(1, "}");

			l();
			tl(1, "public static String var", str_Recherche(langueNom), classeNomSimple, "(String ", str_entite(langueNom), "Var) {");
			tl(2, "switch(", str_entite(langueNom), "Var) {");
			s(wVarRecherche);
			s(wVarSuggere);
			tl(3, "default:");
			if(classeEstBase)
//				tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", str_nest_pas_une_entite_indexe(langueNom), ". \", ", str_entite(langueNom), "Var));");
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".var", str_Recherche(langueNom), classeNomSimpleSuperGenerique, "(", str_entite(langueNom), "Var);");
			tl(2, "}");
			tl(1, "}");

			l();
			tl(1, "public static String var", str_Suggere(langueNom), classeNomSimple, "(String ", str_entite(langueNom), "Var) {");
			tl(2, "switch(", str_entite(langueNom), "Var) {");
			s(wVarSuggere);
			tl(3, "default:");
			if(classeEstBase)
//				tl(4, "throw new RuntimeException(String.format(\"\\\"%s\\\" ", str_nest_pas_une_entite_indexe(langueNom), ". \", ", str_entite(langueNom), "Var));");
				tl(4, "return null;");
			else
				tl(4, "return ", classeNomSimpleSuperGenerique, ".var", str_Suggere(langueNom), classeNomSimpleSuperGenerique, "(", str_entite(langueNom), "Var);");
			tl(2, "}");
			tl(1, "}");
		}

		/////////////////
		// codeStocker //
		/////////////////
		if(classeIndexe) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// ", str_stocker(langueNom), " //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(BooleanUtils.isTrue(classeEtendBase))
				s("@Override ");
			l("public void ", str_stocker(langueNom), str_PourClasse(langueNom), "(SolrDocument solrDocument) {");
			if(classeIndexe) {
			tl(2, str_stocker(langueNom), classeNomSimple, "(solrDocument);");
			}
			tl(1, "}");
			tl(1, "public void ", str_stocker(langueNom), classeNomSimple, "(SolrDocument solrDocument) {");
			tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
			l();
			s(wStocker.toString());
			if(BooleanUtils.isTrue(classeEtendBase)) {
				tl(0);
				tl(2, "super.", str_stocker(langueNom), classeNomSimpleSuperGenerique, "(solrDocument);");
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

		if(classeContientRequeteSite) {
			if(classePartsRequeteSite != null) {
				//////////////////
				// requeteApi //
				//////////////////
				l(); 
				tl(1, "//////////////////");
				tl(1, "// ", str_requeteApi(langueNom), " //");
				tl(1, "//////////////////");
				l();
				tl(1, "public void ", str_requeteApi(langueNom), classeNomSimple, "() {");
				tl(2, str_RequeteApi(langueNom), " ", str_requeteApi(langueNom), " = Optional.ofNullable(", str_requeteSite(langueNom), "_).map(", classePartsRequeteSite.nomSimple(langueNom), "::get", str_RequeteApi(langueNom), "_).orElse(null);");
				tl(2, "Object o = Optional.ofNullable(", str_requeteApi(langueNom), ").map(", str_RequeteApi(langueNom), "::getOriginal).orElse(null);");
				tl(2, "if(o != null && o instanceof ", classeNomSimple, ") {");
				tl(3, classeNomSimple, " original = (", classeNomSimple, ")o;");
				s(wRequeteApi.toString());
				if(BooleanUtils.isTrue(classeEtendBase)) {
					tl(3, "super.", str_requeteApi(langueNom), classeNomSimpleSuperGenerique, "();");
				}
				tl(2, "}");
				tl(1, "}");
			}
			else {
				System.err.println(String.format("%s %s %s %s %s. ", str_classe(langueNom), str_RequeteSite(langueNom), str_manquante(langueNom), str_dans(langueNom), cheminSrcMainJava));
			}
		}

		//////////////
		// hashCode //
		//////////////
		l(); 
		tl(1, "//////////////");
		tl(1, "// hashCode //");
		tl(1, "//////////////");
		l();
		tl(1, "@Override public int hashCode() {");
		t(2, "return Objects.hash(");
		if(BooleanUtils.isTrue(classeEtendBase)) {
			s("super.hashCode()");
			if(entiteIndice > 0)
				s(", ");
		}
		s(wHashCode.toString());
		l(");");
		tl(1, "}");

		////////////
		// equals //
		////////////
		l(); 
		tl(1, "////////////");
		tl(1, "// equals //");
		tl(1, "////////////");
		l();
		tl(1, "@Override public boolean equals(Object o) {");
		tl(2, "if(this == o)");
		tl(3, "return true;");
		tl(2, "if(!(o instanceof ", classeNomSimple, "))");
		tl(3, "return false;");
		tl(2, classeNomSimple, " that = (", classeNomSimple, ")o;");
		t(2, "return ");
		if(BooleanUtils.isTrue(classeEtendBase)) {
			s("super.equals(o)");
			if(entiteIndice > 0) {
				l();
				t(4, "&& ");
			}
		}
		s(wEquals.toString());
		if(!BooleanUtils.isTrue(classeEtendBase) && entiteIndice == 0)
			s("true");
		l(";");
		tl(1, "}");

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
			tl(2, "sb.append(super.toString() + \"\\n\");");
		tl(2, "sb.append(\"", classeNomSimple, " { \");");
		s(wToString.toString());
		tl(2, "sb.append(\" }\");");
		tl(2, "return sb.toString();");
		tl(1, "}");

		if(!classeVals.getEmpty()) {
			l();
			tl(1, "public static final String[] ", classeNomSimple, "Vals", " = new String[] { ", classeVals, " };");
		}

		l();
		s(wApiEntites);

		l("}"); 

		System.out.println("Ecrire: " + classeCheminGen); 
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
