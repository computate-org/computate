package org.computate.frFR.java;      

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.JavaUnicodeEscaper;
import org.apache.commons.text.translate.LookupTranslator;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;

/**  
 * NomCanonique.enUS: org.computate.enUS.java.WriteGenClass
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */  
public class EcrireGenClasse extends EcrireClasse { 

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

	/**
	 * Var.enUS: classVarPrimaryKey
	 */
	protected String classeVarClePrimaire;

	/**
	 * Var.enUS: classVarUniqueKey
	 */
	protected String classeVarCleUnique;

	/**
	 * Var.enUS: classImportsGen
	 */
	protected List<String> classeImportationsGen;

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

	/**
	 * Var.enUS: entitySuperClassesAndMeWithoutGen
	 */
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
	 * Var.enUS: classIndexed
	 */
	protected Boolean classeIndexe;

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
	 * Var.enUS: classRolesFound
	 */
	protected Boolean classeRolesTrouves;

	/**
	 * Var.enUS: classRoles
	 */
	protected List<String> classeRoles;

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
	 * Var.enUS: classMethodeVars
	 */  
	protected List<String> classeMethodeVars;

	/**
	 * Var.enUS: wInitDeep
	 */
	protected ToutEcrivain wInitLoin;

	/**
	 * Var.enUS: wSiteRequest
	 */
	protected ToutEcrivain wRequeteSite;

	/**
	 * Var.enUS: wIndex
	 */
	protected ToutEcrivain wIndexer;

	/**
	 * Var.enUS: wObtain
	 */
	protected ToutEcrivain wObtenir;

	/**
	 * Var.enUS: wAttribute
	 */
	protected ToutEcrivain wAttribuer;

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
	 * Var.enUS: wExists
	 */
	protected ToutEcrivain wExiste;

	/**
	 * Var.enUS: wSaves
	 */
	protected ToutEcrivain wSauvegardes;

	/**
	 * Var.enUS: wDefine
	 */
	protected ToutEcrivain wDefinir;

	protected ToutEcrivain wApiGet;

	/**
	 * Var.enUS: wApiGenerateGet
	 */
	protected ToutEcrivain wApiGenererGet;

	/**
	 * Var.enUS: wApiGeneratePost
	 */
	protected ToutEcrivain wApiGenererPost;

	/**
	 * Var.enUS: wApiGeneratePut
	 */
	protected ToutEcrivain wApiGenererPut;

	/**
	 * Var.enUS: wApiGeneratePatch
	 */
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

	/**
	 * Var.enUS: entityAttributeVar
	 */
	String entiteAttribuerVar;

	/**
	 * Var.enUS: entityDefine
	 */
	Boolean entiteDefinir;

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
	 * Var.enUS: contextNoneNameFound
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
	 * Var.enUS: contextVideoId
	 */
	protected String contexteVideoId;

	/**
	 * Var.enUS: classContext
	 */
	protected Boolean classeContexte;

	/**
	 * Var.enUS: entityHtml
	 */
	Boolean entiteHtml;

	/**
	 * Var.enUS: entityModify
	 */
	Boolean entiteModifier;

	/**
	 * Var.enUS: entityMultiline
	 */
	Boolean entiteMultiligne;

	/**
	 * Var.enUS: entityDisplayName
	 */
	String entiteNomAffichage;

	/**
	 * Var.enUS: entityDescription
	 */
	String entiteDescription;

	/**
	 * Var.enUS: entityHtmlLine
	 */
	Integer entiteHtmlLigne;

	/**
	 * Var.enUS: entityIndexed
	 */
	Boolean entiteIndexe;

	/**
	 * Var.enUS: entityStored
	 */
	Boolean entiteStocke;

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
	 * Var.enUS: searchLines
	 */
	Integer rechercheLignes;

	/**
	 * Var.enUS: searchLineSearch
	 */
	Integer rechercheLigneRecherche;

	/**
	 * Var.enUS: searchLineActualSearch
	 */
	Integer rechercheLigneActuelRecherche;

	/**
	 * Var.enUS: searchLinePOST
	 */
	Integer rechercheLignePOST;

	/**
	 * Var.enUS: searchLineActualPOST
	 */
	Integer rechercheLigneActuelPOST;

	/**
	 * Var.enUS: searchLinePATCH
	 */
	Integer rechercheLignePATCH;

	/**
	 * Var.enUS: searchLineActualPATCH
	 */
	Integer rechercheLigneActuelPATCH;

	/**
	 * Var.enUS: searchLinePage
	 */
	Integer rechercheLignePage;

	/**
	 * Var.enUS: searchLineActualPage
	 */
	Integer rechercheLigneActuelPage;
	
	/** 
	 * r: wInitLoin
	 * r.enUS: wInitDeep
	 * r: wRequeteSite
	 * r.enUS: wSiteRequest
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
	 * r: wExiste
	 * r.enUS: wExists
	 * r: wDefinir
	 * r.enUS: wDefine
	 * r: wSauvegardes
	 * r.enUS: wSaves
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
		wRequeteSite = ToutEcrivain.create();
		wIndexer = ToutEcrivain.create();
		wObtenir = ToutEcrivain.create();
		wAttribuer = ToutEcrivain.create();
		wPut = ToutEcrivain.create();
		wPeupler = ToutEcrivain.create();
		wStocker = ToutEcrivain.create();
		wSauvegardes = ToutEcrivain.create();
		wExiste = ToutEcrivain.create();
		wDefinir = ToutEcrivain.create();
		wApiEntites = ToutEcrivain.create();
		wPageEntites = ToutEcrivain.create();
		wApiGet = ToutEcrivain.create();
		wApiGenererGet = ToutEcrivain.create();
		wApiGenererPost = ToutEcrivain.create();
		wApiGenererPut = ToutEcrivain.create();
		wApiGenererPatch = ToutEcrivain.create();
		wPageHtmlSingulier = ToutEcrivain.create();
		wPageGet = ToutEcrivain.create();
		wHashCode = ToutEcrivain.create();
		wToString = ToutEcrivain.create();
		wEquals = ToutEcrivain.create();
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
	 * r: initLoinPourClasse
	 * r.enUS: initDeepForClass
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
	 */
	public void genCodeInitLoin(String langueNom) throws Exception {
		if(BooleanUtils.isTrue(classeInitLoin)) {
			wInitLoin.l(); 
			wInitLoin.tl(1, "//////////////");
			wInitLoin.tl(1, "// initLoin //");
			wInitLoin.tl(1, "//////////////");
			wInitLoin.l(); 
			wInitLoin.tl(1, "protected boolean dejaInitialise", classeNomSimple, " = false;");
			wInitLoin.l();
			wInitLoin.t(1, "public ", classeNomSimple, " initLoin", classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite_)");
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
			wInitLoin.tl(2, "setRequeteSite_(requeteSite_);");
			wInitLoin.tl(2, "if(!dejaInitialise", classeNomSimple, ") {");
			wInitLoin.tl(3, "dejaInitialise", classeNomSimple, " = true;");
			wInitLoin.tl(3, "initLoin", classeNomSimple, "();");
			wInitLoin.tl(2, "}");
			wInitLoin.tl(2, "return (", classeNomSimple, ")this;");
			wInitLoin.tl(1, "}");
			wInitLoin.l();
			wInitLoin.t(1, "public void initLoin", classeNomSimple, "()");
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
			if(BooleanUtils.isTrue(classeEtendBase)) 
				wInitLoin.tl(2, "super.initLoin", classeNomSimpleSuperGenerique, "(requeteSite_);");
			wInitLoin.tl(2, "init", classeNomSimple, "();");
			wInitLoin.tl(1, "}");
			wInitLoin.l();
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
	 */
	public void genCodeRequeteSite(String langueNom) throws Exception {
		if(BooleanUtils.isTrue(classeInitLoin)) {
			o = wRequeteSite;
			l(); 
			tl(1, "/////////////////");
			tl(1, "// requeteSite //");
			tl(1, "/////////////////");
			l(); 
			tl(1, "public void requeteSite", classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite_) {");
			if(BooleanUtils.isTrue(classeEtendBase)) 
				tl(3, "super.requeteSite", classeNomSimpleSuperGenerique, "(requeteSite_);");
		}
	}

	/**
	 * Var.enUS: genCodeIndex
	 * Param1.var.enUS: languageName
	 * r: wIndexer
	 * r.enUS: wIndex
	 * r: classeIndexe
	 * r.enUS: classIndexed
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: classeEtendBase
	 * r.enUS: classExtendsBase
	 * r: classeEstBase
	 * r.enUS: classIsBase
	 * r: indexerPourClasse
	 * r.enUS: indexForClass
	 * r: classeSauvegarde
	 * r.enUS: classSaved
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: configChemin
	 * r.enUS: configPath
	 * 
	 * r: getClientSolr
	 * r.enUS: getSolrClient
	 * r: clientSolr
	 * r.enUS: solrClient
	 * r: indexer
	 * r.enUS: index
	 * r: initLoin
	 * r.enUS: initDeep
	 */
	public void genCodeIndexer(String langueNom) throws Exception {
		o = wIndexer;
		if(classeIndexe) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// indexer //");
			tl(1, "/////////////");
			tl(0);
			tl(1, "public static void indexer() {");
			tl(2, "try {");
			tl(3, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = new ", classePartsRequeteSite.nomSimple(langueNom), "();");
			tl(3, "requeteSite.initLoin", classePartsRequeteSite.nomSimple(langueNom), "();");
			tl(3, classePartsSiteContexte.nomSimple(langueNom), " siteContexte = new ", classePartsSiteContexte.nomSimple(langueNom), "();");
			tl(3, "siteContexte.getConfigSite().setConfigChemin(", q(configChemin), ");");
			tl(3, "siteContexte.initLoin", classePartsSiteContexte.nomSimple(langueNom), "();");
			tl(3, "siteContexte.setRequeteSite_(requeteSite);");
			tl(3, "requeteSite.setSiteContexte_(siteContexte);");
			tl(3, classeNomSimple, " o = new ", classeNomSimple, "();");
			tl(3, "o.requeteSite", classeNomSimple, "(requeteSite);");
			tl(3, "o.initLoin", classeNomSimple, "(requeteSite);");
			tl(3, "o.indexer", classeNomSimple, "();");
			tl(2, "} catch(Exception e) {");
			tl(3, "ExceptionUtils.rethrow(e);");
			tl(2, "}");
			tl(1, "}");
			tl(0);
			if(classeEtendBase || classeEstBase) {
				tl(0);
				t(1);
				if(!classeEstBase)
					s("@Override ");
				l("public void indexerPourClasse() throws Exception {");
				tl(2, "indexer", classeNomSimple, "();");
				tl(1, "}");
				tl(0);
				t(1);
				if(!classeEstBase)
					s("@Override ");
				l("public void indexerPourClasse(SolrInputDocument document) throws Exception {");
				tl(2, "indexer", classeNomSimple, "(document);");
				tl(1, "}");
			}
			l();
			tl(1, "public void indexer", classeNomSimple, "(SolrClient clientSolr) throws Exception {");
			tl(2, "SolrInputDocument document = new SolrInputDocument();");
			tl(2, "indexer", classeNomSimple, "(document);");
			tl(2, "clientSolr.add(document);");
			tl(2, "clientSolr.commit();");
			l("\t}");
			l();
			tl(1, "public void indexer", classeNomSimple, "() throws Exception {");
			tl(2, "SolrInputDocument document = new SolrInputDocument();");
			tl(2, "indexer", classeNomSimple, "(document);");
			tl(2, "SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();");
			tl(2, "clientSolr.add(document);");
			tl(2, "clientSolr.commit();");
			l("\t}");

			tl(0);
			tl(1, "public void indexer", classeNomSimple, "(SolrInputDocument document) throws Exception {");
			if(classeSauvegarde) {
				tl(2, "if(sauvegardes", classeNomSimple, " != null)");
				tl(3, "document.addField(\"sauvegardes", classeNomSimple, "_stored_strings\", sauvegardes", classeNomSimple, ");");
				l();
			}
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
			tl(1, "// obtenir //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			l("public Object obtenirPourClasse(String var) throws Exception {");
			tl(2, "String[] vars = StringUtils.split(var, \".\");");
			tl(2, "Object o = null;");
			tl(2, "for(String v : vars) {");
			tl(3, "if(o == null)");
			tl(4, "o = obtenir", classeNomSimple, "(v);");
			tl(3, "else if(o instanceof Cluster) {");
			tl(4, "Cluster cluster = (Cluster)o;");
			tl(4, "o = cluster.obtenirPourClasse(v);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o;");
			tl(1, "}");
			tl(1, "public Object obtenir", classeNomSimple, "(String var) throws Exception {");
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
			tl(1, "// attribuer //");
			tl(1, "///////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			s("public boolean attribuerPourClasse(String var, Object val)");
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
			tl(4, "o = attribuer", classeNomSimple + "(v, val);");
			tl(3, "else if(o instanceof Cluster) {");
			tl(4, "Cluster cluster = (Cluster)o;");
			tl(4, "o = cluster.attribuerPourClasse(v, val);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object attribuer", classeNomSimple, "(String var, Object val) {");
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
			l("public void putPourClasse(JsonObject requeteJson) throws Exception {");
			tl(2, "Set<String> vars = requeteJson.fieldNames();");
			tl(2, "for(String var : vars) {");
			tl(3, "put", classeNomSimple + "(requeteJson, var);");
			tl(2, "}");
			tl(1, "}");
			l();
			t(1);
			if(!classeEstBase)
				s("@Override ");
			l("public Boolean put", classeNomSimple, "(JsonObject requeteJson, String var) throws Exception {");
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
	 * Var.enUS: genCodeExists
	 * Param1.var.enUS: languageName
	 * r: wExiste
	 * r.enUS: wExists
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeSauvegarde
	 * r.enUS: classSaved
	 * r: existePourClasse
	 * r.enUS: existsForClass
	 * r: getRequeteServeur
	 * r.enUS: getServerRequest
	 * r: utilisateurId
	 * r.enUS: userId
	 * r: nomCanonique
	 * r.enUS: canonicalName
	 * 
	 * r: existe
	 * r.enUS: exists
	 */
	public void genCodeExiste(String langueNom) throws Exception {
		o = wExiste;
		if(classeSauvegarde) {
//			l(); 
//			tl(1, "////////////");
//			tl(1, "// existe //");
//			tl(1, "////////////");
//			tl(0);
//			t(1);
//			if(!classeNomSimple.equals("Cluster"))
//				s("@Override ");
//			l("public Boolean existePourClasse() throws Exception {");
//			tl(2, "String pkStr = requeteSite_.getRequeteServeur().getParam(\"pk\");");
//			tl(2, "Long pk = ", StringUtils.class.getCanonicalName(), ".isNumeric(pkStr) ? Long.parseLong(pkStr) : null;");
//			tl(2, "Boolean existe = existePourClasse(pk);");
//			tl(2, "return existe;");
//			tl(1, "}");
//			t(1);
//			if(!classeNomSimple.equals("Cluster"))
//				s("@Override ");
//			l("public Boolean existePourClasse(Long pk) throws Exception {");
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite_.", classePartsSiteContexte.nomSimple(langueNom), ".sourceDonnees);");
//			tl(2, ArrayListHandler.class.getCanonicalName(), " gestionnaireListe = new ", ArrayListHandler.class.getCanonicalName(), "();");
//			tl(2, "utilisateurId = requeteSite_.utilisateurId;");
//			tl(2, "this.pk = pk;");
//			tl(2, "String nomCanonique = getClass().getCanonicalName();");
//			tl(2, "Boolean existe = false;");
//			tl(2);
//			tl(2, "if(pk == null) {");
//			tl(3, "String sql = \"select pk from c where c.id_utilisateur=? and c.nom_canonique=?\";");
//			tl(3, "SQLClient clientSql = requeteSite_.getSiteContexte_().getClientSql();");
//			tl(3, "clientSql.getConnection(ar -> {");
//			tl(4, "if(ar.failed()) {");
//			tl(5, "LOGGER.error(\"Impossible d'ouvrir une connexion à la base de données. \", ar.cause());");
//			tl(5, "future.fail(ar.cause());");
//			tl(4, "} else {");
//			tl(5, "SQLConnection connexionSql = ar.result();");
//			tl(5, "connectionSql.queryWithParams(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_existe, new JsonArray().add(pk)), chercher -> {");
//			tl(6, "connexionSql.close();");
//			tl(6, "if(chercher.succeeded()) {");
//			tl(7, "JsonArray ligneDonnees = chercher.result().getResults().stream().findFirst().orElseGet(() -> null);");
//			tl(7, "if(ligneDonnees != null) {");
//			tl(8, "Long pkDonnees = ligneDonnees.getLong(0);");
//			tl(8, "if(ligneDonnees != null && ligne) {");
//			tl(9, "");
//			tl(8, "}");
//			tl(7, "}");
//			tl(6, "}");
//			tl(5, "});");
//			tl(4, "}");
//			tl(3, "});");
////			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*select count(*) from objet where objet.id_utilisateur=*/, requeteSite_.utilisateurId /* and objet.nom_canonique=*/, nomCanonique);");
//			tl(7, "existe = resultats.size() > 0;");
//			tl(7, "if(existe) {");
//			tl(8, "pk = (Long)resultats.get(0)[0];");
//			tl(8, "setPk(pk);");
//			tl(7, "}");
//			tl(2, "}");
//			tl(2, "else {");
//			tl(3, "String sql = \"select count(*) from objet where objet.pk=? and objet.id_utilisateur=? and objet.nom_canonique=?\";");
//			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*select count(*) from objet where objet.pk=*/, pk /* and objet.id_utilisateur=*/, requeteSite_.utilisateurId /* and objet.nom_canonique=*/, nomCanonique);");
//			tl(3, "existe = ((Long)resultats.get(0)[0]) > 0L;");
//
//			tl(2, "}");
//			tl(2, "return existe;");
//			tl(1, "}");
		}
	}

    /**
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
        return ESCAPE_JAVA.translate(input);
    }

	/**
	 * Var.enUS: genCodeSaves
	 * Param1.var.enUS: languageName
	 * r: wSauvegardes
	 * r.enUS: wSaves
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeSauvegarde
	 * r.enUS: classSaved
	 * 
	 * r: definirPourClasse
	 * r.enUS: defineForClass
	 * r: sauvegardesPourClasse
	 * r.enUS: savesForClass
	 * r: sauvegardes
	 * r.enUS: saves
	 */
	public void genCodeSauvegardes(String langueNom) throws Exception {
		o = wSauvegardes;
		if(classeSauvegarde) {
			l(); 
			tl(1, "/////////////////");
			tl(1, "// sauvegardes //");
			tl(1, "/////////////////");
			tl(0);
			tl(1, "protected List<String> sauvegardes", classeNomSimple, " = new ArrayList<String>();");
//			t(1);
//			if(!classeNomSimple.equals("Cluster"))
//				s("@Override ");
//			l("public void sauvegardesPourClasse(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite) throws Exception {");
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite.", classePartsSiteContexte.nomSimple(langueNom), ".sourceDonnees);");
//			tl(2, ArrayListHandler.class.getCanonicalName(), " gestionnaireListe = new ", ArrayListHandler.class.getCanonicalName(), "();");
//
//			tl(2);
//			tl(2, "if(pk != null) {");
//			tl(3, "String sql = \"select cree, modifie from objet where objet.pk=?\";");
//			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*select cree, modifie from objet where objet.pk=*/, pk);");
//			tl(3, "if(resultats.size() > 0) {");
//			tl(4, "cree((Date)resultats.get(0)[0]);");
//			tl(4, "modifie((Date)resultats.get(0)[1]);");
//			tl(3, "}");
//
//			t(3, "sql = \"select chemin, valeur from p where p.pk_objet=? ");
//			s("union select champ2, pk2::text from a where a.pk1=? ");
//			s("union select champ1, pk1::text from a where a.pk2=? ");
//			l("\";");
//			tl(3, "resultats = coureur.query(sql, gestionnaireListe /*select chemin, valeur from p where p.pk_objet=*/, pk, pk, pk);");
//			tl(3, "for(Object[] objets : resultats) {");
//			tl(4, "String chemin = (String)objets[0];");
//			tl(4, "String valeur = requeteSite.decrypterStr((String)objets[1]);");
//			tl(4, "definirPourClasse(chemin, valeur);");
//			tl(4, "sauvegardes", classeNomSimple, ".add(chemin);");
//			tl(3, "}");
//			tl(2, "}");
//			tl(1, "}");
		}
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
	 * 
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
	 * r: LesNom
	 * r.enUS: TheName
	 * r: contexteTous
	 * r.enUS: contextAll
	 * r: contexteAucunNomTrouve
	 * r.enUS: contextNoneNameFound
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
		l("/**\t");
		ecrireCommentairePart(classeCommentaire, 0); 
		tl(0, " * <br/><a href=\"", urlSolrComputate, "/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(classeNomCanonique), "&fq=classeEtendGen_indexed_boolean:true\">Trouver la classe ", entiteVar, " dans Solr</a>");
		tl(0, " * <br/>");
		l(" **/");  
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
		if(classeSauvegarde) {
			tl(1, "private static final Logger LOGGER = LoggerFactory.getLogger(", classeNomSimple, ".class);");
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
		
		//////////////
		// Contexte //
		//////////////
		
		if(classeContexte) {

			contexteCouleur = (String)classeDoc.get("contexteCouleur_stored_string");
			contexteIconeGroupe = (String)classeDoc.get("contexteIconeGroupe_stored_string");
			contexteIconeNom = (String)classeDoc.get("contexteIconeNom_stored_string");

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
			
			l();
			if(contexteUnNom != null)
				tl(1, "public static final String ", classeNomSimple, "_UnNom = ", q(contexteUnNom), ";");
			
			if(contexteCe != null)
				tl(1, "public static final String ", classeNomSimple, "_Ce = ", q(contexteCe), ";");
			
			if(contexteCeNom != null)
				tl(1, "public static final String ", classeNomSimple, "_CeNom = ", q(contexteCeNom), ";");
			
			if(contexteUn != null)
				tl(1, "public static final String ", classeNomSimple, "_Un = ", q(contexteUn), ";");
			
			if(contexteLeNom != null)
				tl(1, "public static final String ", classeNomSimple, "_LeNom = ", q(contexteLeNom), ";");
			
			if(contexteNomSingulier != null)
				tl(1, "public static final String ", classeNomSimple, "_NomSingulier = ", q(contexteNomSingulier), ";");
			
			if(contexteNomPluriel != null)
				tl(1, "public static final String ", classeNomSimple, "_NomPluriel = ", q(contexteNomPluriel), ";");
			
			if(contexteNomActuel != null)
				tl(1, "public static final String ", classeNomSimple, "_NomActuel = ", q(contexteNomActuel), ";");
			
			if(contexteTous != null)
				tl(1, "public static final String ", classeNomSimple, "_Tous = ", q(contexteTous), ";");
			
			if(contexteTousNom != null)
				tl(1, "public static final String ", classeNomSimple, "_TousNom = ", q(contexteTousNom), ";");
			
			if(contexteH1 != null)
				tl(1, "public static final String ", classeNomSimple, "_H1 = ", q(contexteH1), ";");
			
			if(contexteH2 != null)
				tl(1, "public static final String ", classeNomSimple, "_H2 = ", q(contexteH2), ";");
			
			if(contexteH3 != null)
				tl(1, "public static final String ", classeNomSimple, "_H3 = ", q(contexteH3), ";");
			
			if(contexteTitre != null)
				tl(1, "public static final String ", classeNomSimple, "_Titre = ", q(contexteTitre), ";");
			
			if(contexteLesNoms != null)
				tl(1, "public static final String ", classeNomSimple, "_LesNoms = ", q(contexteLesNoms), ";");
			
			if(contexteAucunNomTrouve != null)
				tl(1, "public static final String ", classeNomSimple, "_AucunNomTrouve = ", q(contexteAucunNomTrouve), ";");
			
			if(contexteNomVar != null)
				tl(1, "public static final String ", classeNomSimple, "_NomVar = ", q(contexteNomVar), ";");
			
			if(contexteDeNom != null)
				tl(1, "public static final String ", classeNomSimple, "_DeNom = ", q(contexteDeNom), ";");
			
			if(contexteAdjectif != null)
				tl(1, "public static final String ", classeNomSimple, "_Adjectif = ", q(contexteAdjectif), ";");
			
			if(contexteAdjectifPluriel != null)
				tl(1, "public static final String ", classeNomSimple, "_AdjectifPluriel = ", q(contexteAdjectifPluriel), ";");
			
			if(contexteAdjectifVar != null)
				tl(1, "public static final String ", classeNomSimple, "_AdjectifVar = ", q(contexteAdjectifVar), ";");
			
			if(contexteUnNomAdjectif != null)
				tl(1, "public static final String ", classeNomSimple, "_UnNomAdjectif = ", q(contexteUnNomAdjectif), ";");
			
			if(contexteNomAdjectifSingulier != null)
				tl(1, "public static final String ", classeNomSimple, "_NomAdjectifSingulier = ", q(contexteNomAdjectifSingulier), ";");
			
			if(contexteNomAdjectifPluriel != null)
				tl(1, "public static final String ", classeNomSimple, "_NomAdjectifPluriel = ", q(contexteNomAdjectifPluriel), ";");
			
			if(contexteCouleur != null)
				tl(1, "public static final String ", classeNomSimple, "_Couleur = ", q(contexteCouleur), ";");
			
			if(contexteIconeGroupe != null)
				tl(1, "public static final String ", classeNomSimple, "_IconeGroupe = ", q(contexteIconeGroupe), ";");
			
			if(contexteIconeNom != null)
				tl(1, "public static final String ", classeNomSimple, "_IconeNom = ", q(contexteIconeNom), ";");
		}

	}

	/**
	 * Var.enUS: genCodeConstructor
	 * Param1.var.enUS: languageName
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
	 * r: "L'entité « "
	 * r.enUS: "The entity \\" "
	 * r: " »"
	 * r.enUS: " \\""
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
	 * r: entiteCles
	 * r.enUS: entityKeys
	 * r: entiteAttribuer
	 * r.enUS: entityAttribute
	 * r: entiteAttribuerNomCanonique
	 * r.enUS: entityAttributeCanonicalName
	 * r: entiteAttribuerNomSimple
	 * r.enUS: entityAttributeSimpleName
	 * r: entiteAttribuerVar
	 * r.enUS: entityAttributeVar
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
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: wApiGenererPatch
	 * r.enUS: wApiGeneratePatch
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 * r: initLoinPourClasse
	 * r.enUS: initDeepForClass
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
	 * 
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
	 */   
	public void genCodeEntite(String langueNom) throws Exception {
		String entiteVar = (String)doc.get("entiteVar_" + langueNom + "_stored_string");
		String entiteSuffixeType = (String)doc.get("entiteSuffixeType_stored_string");
		String entiteVarCapitalise = (String)doc.get("entiteVarCapitalise_" + langueNom + "_stored_string");
		String entiteNomCanonique = (String)doc.get("entiteNomCanonique_" + langueNom + "_stored_string");
		String entiteNomCanoniqueGenerique = (String)doc.get("entiteNomCanoniqueGenerique_" + langueNom + "_stored_string");
		String entiteNomSimpleComplet = (String)doc.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
		String entiteNomSimpleCompletGenerique = (String)doc.get("entiteNomSimpleCompletGenerique_" + langueNom + "_stored_string");
		String entiteNomSimple = (String)doc.get("entiteNomSimple_" + langueNom + "_stored_string");
		String entiteCommentaire = (String)doc.get("entiteCommentaire_" + langueNom + "_stored_string");
		String entiteVarParam = (String)doc.get("entiteVarParam_" + langueNom + "_stored_string");
		Boolean entiteCouverture = (Boolean)doc.get("entiteCouverture_stored_boolean");
		Boolean entiteInitialise = (Boolean)doc.get("entiteInitialise_stored_boolean");
		Boolean entiteInitLoin = (Boolean)doc.get("entiteInitLoin_stored_boolean");
		List<String> methodeExceptionsNomSimpleComplet = (List<String>)doc.get("methodeExceptionsNomSimpleComplet_stored_strings");

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
			Boolean entitetexte = (Boolean)doc.get("entitetexte_stored_boolean");
			Boolean entiteIncremente = (Boolean)doc.get("entiteIncremente_stored_boolean");
			Boolean entiteIgnorer = (Boolean)doc.get("entiteIgnorer_stored_boolean");
			Boolean entiteDeclarer = (Boolean)doc.get("entiteDeclarer_stored_boolean");
			Boolean entiteRechercher = (Boolean)doc.get("entiteRechercher_stored_boolean");
			Boolean entiteAttribuer = BooleanUtils.isTrue((Boolean)doc.get("entiteAttribuer_stored_boolean"));
			String entiteAttribuerNomCanonique = (String)doc.get("entiteAttribuerNomCanonique_" + langueNom + "_stored_string");
			String entiteAttribuerNomSimple = (String)doc.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string");
			String entiteAttribuerVar = (String)doc.get("entiteAttribuerVar_" + langueNom + "_stored_string");
			Boolean entiteAjouter = (Boolean)doc.get("entiteAjouter_stored_boolean");
			Boolean entiteSupprimer = (Boolean)doc.get("entiteSupprimer_stored_boolean");
			Boolean entiteModifier = (Boolean)doc.get("entiteModifier_stored_boolean");
			Boolean entiteRecharger = (Boolean)doc.get("entiteRecharger_stored_boolean");
			Boolean entiteMultiligne = (Boolean)doc.get("entiteMultiligne_stored_boolean");
			Boolean entiteCles = (Boolean)doc.get("entiteCles_stored_boolean");
			Boolean entiteIndexeOuStocke = (Boolean)doc.get("entiteIndexeOuStocke_stored_boolean");
			Boolean entiteDefinir = (Boolean)doc.get("entiteDefinir_stored_boolean");
	
			String entiteNomAffichage = (String)doc.get("entiteNomAffichage_" + langueNom + "_stored_string");
			String entiteHtmlTooltip = (String)doc.get("entiteHtmlTooltip_" + langueNom + "_stored_string");
			Boolean entiteHtml = (Boolean)doc.get("entiteHtml_" + langueNom + "_stored_boolean");
	
			List<String> entiteMethodesAvantVisibilite = (List<String>)doc.get("entiteMethodesAvantVisibilite_stored_strings");
			List<String> entiteMethodesAvantVar = (List<String>)doc.get("entiteMethodesAvantVar_stored_strings");
			List<String> entiteMethodesAvantParamVar = (List<String>)doc.get("entiteMethodesAvantParamVar_stored_strings");
			List<String> entiteMethodesAvantParamNomSimple = (List<String>)doc.get("entiteMethodesAvantParamNomSimple_stored_strings");
			List<Boolean> entiteMethodesAvantNomParam = (List<Boolean>)doc.get("entiteMethodesAvantNomParam_stored_booleans");
			List<Boolean> entiteMethodesAvantEcrire = (List<Boolean>)doc.get("entiteMethodesAvantEcrire_stored_booleans");
	
			List<String> entiteMethodesApresVisibilite = (List<String>)doc.get("entiteMethodesApresVisibilite_stored_strings");
			List<String> entiteMethodesApresVar = (List<String>)doc.get("entiteMethodesApresVar_stored_strings");
			List<String> entiteMethodesApresParamVar = (List<String>)doc.get("entiteMethodesApresParamVar_stored_strings");
			List<String> entiteMethodesApresParamNomSimple = (List<String>)doc.get("entiteMethodesApresParamNomSimple_stored_strings");
			List<Boolean> entiteMethodesApresNomParam = (List<Boolean>)doc.get("entiteMethodesApresNomParam_stored_booleans");
			List<Boolean> entiteMethodesApresEcrire = (List<Boolean>)doc.get("entiteMethodesApresEcrire_stored_booleans");

			List<String> entiteEcrireMethodes = (List<String>)doc.get("entiteEcrireMethodes_stored_strings");
			if(entiteEcrireMethodes == null)
				entiteEcrireMethodes = new ArrayList<>();
			for(int i = 0; i < classeEcrireMethodes.size(); i++) {
				String classeEcrireMethode = classeEcrireMethodes.get(i);
				if(entiteEcrireMethodes.contains(classeEcrireMethode)) {
					ToutEcrivain w = classeEcrireEcrivains.get(i);
					String var = classeEcrireMethode + entiteVarCapitalise;
					if(classeMethodeVars.contains(var))
						w.tl(2, "((", classeNomSimple, ")this).", var, "();");
					else
						w.tl(2, entiteVar, ".", classeEcrireMethode, "();");
				}
			}
	
			o = auteurGenClasse;
	
			l();
			String ligneCommentaire = "\t///" + String.join("", Collections.nCopies(entiteVar.length(), "/")) + "///";
			l(ligneCommentaire);
			tl(1, "// ", entiteVar, " //");
			l(ligneCommentaire);
			l();

			List<String> entiteValsVar = (List<String>)doc.get("entiteValsVar_stored_strings");
			List<String> entiteValsLangue = (List<String>)doc.get("entiteValsLangue_stored_strings");
			List<String> entiteValsValeur = (List<String>)doc.get("entiteValsValeur_stored_strings");
			if(entiteValsVar != null && entiteValsLangue != null && entiteValsValeur != null) {
				String entiteValVarAncien = null;
				Integer entiteValVarNumero = 0;
				String entiteValVar = null;
				String entiteValLangue = null;
				String entiteValVarLangue = null;
				String entiteValVarLangueAncien = null;
				String entiteValValeur = null;
	
				for(int j = 0; j < entiteValsVar.size(); j++) {
					entiteValVar = entiteValsVar.get(j);
					entiteValLangue = entiteValsLangue.get(j);
					entiteValVarLangue = entiteValVar + entiteValLangue;
					entiteValValeur = entiteValsValeur.get(j);
	
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
			}

			t(1, "/**");
			t(1);
				s("L'entité « ", entiteVar, " »");
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
	
			t(1, "protected ", entiteNomSimpleComplet, " ", entiteVar);
			if(!entiteCouverture) {
				if("java.util.List".equals(entiteNomCanonique)) {
					s(" = new java.util.ArrayList<");
					s(entiteNomCanoniqueGenerique);
					s(">()");
				}
				else if("java.util.Map".equals(entiteNomCanonique)) {
					s(" = new java.util.HashMap<");
					s(entiteNomCanoniqueGenerique);
					s(">()");
				}
				else if("java.util.Set".equals(entiteNomCanonique)) {
					s(" = new java.util.HashSet<");
					s(entiteNomCanoniqueGenerique);
					s(">()");
				}
				else {
					s(" = new ", entiteNomSimpleComplet, "()");
				}
			}
			l(";");
	
			t(1, "public Couverture<", entiteNomSimpleComplet, "> ", entiteVar, "Couverture");
			l(" = new Couverture<", entiteNomSimpleComplet, ">().p(this).c(", entiteNomSimple, ".class).var(\"", entiteVar, "\").o(", entiteVar, ");");
	
			// Methode underscore //
			l();
			t(1, "/**");
			t(1);
			s("<br/>", "L'entité « ", entiteVar, " »");
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
				tl(1, " * ", " est défini comme null avant d'être initialisé. ");
			}
			else {
				tl(1, " * ", "Il est construit avant d'être initialisé avec le constructeur par défaut ", entiteNomSimpleComplet, "(). ");
			}
	
			// Lien vers Solr //
			tl(1, " * <br/><a href=\"", urlSolrComputate, "/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(classeNomCanonique), "&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_", langueNom, "_indexed_string:", ClientUtils.escapeQueryChars(entiteVar), "\">Trouver l'entité ", entiteVar, " dans Solr</a>");
			tl(1, " * <br/>");
	
			if(entiteCouverture) {
				tl(1, " * @param ", entiteVarParam, " est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. ");
			}
			else {
				tl(1, " * @param ", entiteVar, " est l'entité déjà construit. ");
			}
	//		if(methodeExceptionsNomSimpleComplet != null && methodeExceptionsNomSimpleComplet.size() > 0) {
	//
	//			for(int i = 0; i < methodeExceptionsNomSimpleComplet.size(); i++) {
	//				String methodeExceptionNomSimpleComplet = methodeExceptionsNomSimpleComplet.get(i);
	//				tl(1, " * @throws ", methodeExceptionNomSimpleComplet);
	//			}
	//		}
			tl(1, " **/");
			t(1, "protected abstract void");
			s(" _", entiteVar);
			s("(");
			if(entiteCouverture) {
				s("Couverture<", entiteNomSimpleComplet, "> ", entiteVarParam);
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
	//						tl(1, "public ", classeNomSimple, " ", entiteVar, "(", entiteNomSimpleComplet, " ", entiteVarParam, ") throws Exception {");
	//						tl(2, "set", entiteVarCapitalise, "(", entiteVarParam, ");");
	//						tl(2, "return (", classeNomSimple, ")this;");
	//						tl(1, "}");
	
			l();
			tl(1, "public ", entiteNomSimpleComplet, " get", entiteVarCapitalise, "() {");
			tl(2, "return ", entiteVar, ";");
			tl(1, "}");
	
			l();
			tl(1, "public void set", entiteVarCapitalise, "(", entiteNomSimpleComplet, " ", entiteVar, ") {");
			tl(2, "this.", entiteVar, " = ", entiteVar, ";");
			tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
			tl(1, "}");
	//
	//						l();
	//						tl(1, "public ", entiteNomSimpleComplet, " ", entiteVar, "() throws Exception {");
	//						tl(2, "return get", entiteVarCapitalise, "();");
	//						tl(1, "}");
	
			// Setter List //
			if(StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName()) && StringUtils.equals(entiteNomCanoniqueGenerique, Long.class.getCanonicalName())) {
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
				tl(3, "Long l = Long.parseLong(o);");
				tl(3, "add", entiteVarCapitalise, "(l);");
				tl(2, "}");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter Boolean //
			if(StringUtils.equals(entiteNomCanonique, Boolean.class.getCanonicalName())) {
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = Boolean.parseBoolean(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter Integer //
			if(StringUtils.equals(entiteNomCanonique, Integer.class.getCanonicalName())) {
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
				tl(3, "this.", entiteVar, " = Integer.parseInt(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter Float //
			if(StringUtils.equals(entiteNomCanonique, Float.class.getCanonicalName())) {
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
				tl(3, "this.", entiteVar, " = Float.parseFloat(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter Double //
			if(StringUtils.equals(entiteNomCanonique, Double.class.getCanonicalName())) {
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
				tl(3, "this.", entiteVar, " = Double.parseDouble(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter Long //
			if(StringUtils.equals(entiteNomCanonique, Long.class.getCanonicalName())) {
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
				tl(3, "this.", entiteVar, " = Long.parseLong(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter Long //
			if(StringUtils.equals(entiteNomSimple, "Chaine")) {
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, entiteVar, ".s(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter BigDecimal //
			if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())) {
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o))");
				tl(3, "this.", entiteVar, " = new BigDecimal(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(Double o) {");
				tl(3, "this.", entiteVar, " = new BigDecimal(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(Integer o) {");
				tl(3, "this.", entiteVar, " = new BigDecimal(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter Timestamp //
			if(StringUtils.equals(entiteNomCanonique, Timestamp.class.getCanonicalName())) {
				tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = Timestamp.valueOf((LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME)));");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter Date //
			if(StringUtils.equals(entiteNomCanonique, Date.class.getCanonicalName())) {
				tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = Date.from(LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant());");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter LocalDate //
			if(StringUtils.equals(entiteNomCanonique, LocalDate.class.getCanonicalName())) {
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(Instant o) {");
				tl(2, "this.", entiteVar, " = LocalDate.from(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
				tl(1, "/** Example: 2011-12-03+01:00 **/");
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = LocalDate.parse(o, DateTimeFormatter.ISO_OFFSET_DATE);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(Date o) {");
				tl(2, "this.", entiteVar, " = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter ZonedDateTime //
			if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(Instant o) {");
				tl(2, "this.", entiteVar, " = ZonedDateTime.from(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
				tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = ZonedDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(Date o) {");
				tl(2, "this.", entiteVar, " = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
			}
	
			// Setter LocalDateTime //
			if(StringUtils.equals(entiteNomCanonique, LocalDateTime.class.getCanonicalName())) {
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(Instant o) {");
				tl(2, "this.", entiteVar, " = LocalDateTime.from(o);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
				tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(String o) {");
				tl(2, "this.", entiteVar, " = LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
				tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(Date o) {");
				tl(2, "this.", entiteVar, " = LocalDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());");
				tl(2, "this.", entiteVar, "Couverture.dejaInitialise = true;");
				tl(2, "return (", classeNomSimple, ")this;");
				tl(1, "}");
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
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter Boolean //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Boolean.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = Boolean.parseBoolean(o);");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter Integer //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Integer.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
					tl(3, entiteNomSimpleCompletGenerique, " p = Integer.parseInt(o);");
					tl(3, "add", entiteVarCapitalise, "(p);");
					tl(3, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter BigDecimal //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, BigDecimal.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Double o = objets.getDouble(i);");
					tl(3, "add", entiteVarCapitalise, "(new BigDecimal(o));");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
					tl(3, entiteNomSimpleCompletGenerique, " p = new BigDecimal(o);");
					tl(3, "add", entiteVarCapitalise, "(p);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter Float //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Float.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
					tl(3, entiteNomSimpleCompletGenerique, " p = Float.parseFloat(o);");
					tl(3, "add", entiteVarCapitalise, "(p);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter Double //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Double.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
					tl(3, entiteNomSimpleCompletGenerique, " p = Double.parseDouble(o);");
					tl(3, "add", entiteVarCapitalise, "(p);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter Long //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Long.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, entiteNomSimpleCompletGenerique, " o = objets.get", entiteNomSimpleCompletGenerique, "(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, "if(org.apache.commons.lang3.math.NumberUtils.isCreatable(o)) {");
					tl(3, entiteNomSimpleCompletGenerique, " p = Long.parseLong(o);");
					tl(3, "add", entiteVarCapitalise, "(p);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter Timestamp //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Timestamp.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = Timestamp.valueOf((LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME)));");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter Date //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Date.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = Date.from(LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant());");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter LocalDate //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, LocalDate.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "/** Example: 2011-12-03+01:00 **/");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = LocalDate.parse(o, DateTimeFormatter.ISO_OFFSET_DATE);");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(Date o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = o.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter ZonedDateTime //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, ZonedDateTime.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = ZonedDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(Date o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = ZonedDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter LocalDateTime //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, LocalDateTime.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " set", entiteVarCapitalise, "(JsonArray objets) {");
					tl(2, entiteVar, ".clear();");
					tl(2, "for(int i = 0; i < objets.size(); i++) {");
					tl(3, "Instant o = objets.getInstant(i);");
					tl(3, "add", entiteVarCapitalise, "(o);");
					tl(2, "}");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME);");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(Date o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = LocalDateTime.ofInstant(o.toInstant(), ZoneId.systemDefault());");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
			}
	
			// Initialise //
			if(entiteInitLoin) {
	
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
	
				tl(2, "if(!", entiteVar, "Couverture.dejaInitialise) {");
				if(entiteCouverture) {
					tl(3, "_", entiteVar, "(", entiteVar, "Couverture);");
					tl(3, "if(", entiteVar, " == null)");
					tl(4, "set", entiteVarCapitalise, "(", entiteVar, "Couverture.o);");
				}
				else {
					tl(3, "_", entiteVar, "(", entiteVar, ");");
				}
				tl(2, "}");
	
				// initLoin
	
	//						if(initLoin && nomCanonique.enUS().startsWith(classe.nomEnsembleDomaine.enUS())) {
				if(entiteInitialise) {
					if(entiteCouverture) {
						tl(2, "if(", entiteVar, " != null)");
						tl(3, entiteVar, ".initLoinPourClasse(requeteSite_);");
					}
					else {
						tl(2, entiteVar, ".initLoinPourClasse(requeteSite_);");
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
	
				tl(2, entiteVar, "Couverture.dejaInitialise(true);");
				tl(2, "return (", classeNomSimple, ")this;");
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
			}
	
			//////////
			// htm //
			//////////
			if(entiteNomSimple != null && entiteSolrNomCanonique != null) {
	
				//////////
				// solr //
				//////////
				l();
				tl(1, "public ", entiteSolrNomSimple, " solr", entiteVarCapitalise, "() {");
				if(entiteNomSimple.equals("Chaine")) {
					tl(2, "return ", entiteVar, " == null ? null : ", entiteVar, ".toString();");
				}
				else if(entiteNomSimple.equals("Timestamp")) {
					tl(2, "return ", entiteVar, " == null ? null : Date.from(", entiteVar, ".toInstant());");
				}
				else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
					tl(2, "return ", entiteVar, " == null ? null : Date.from(", entiteVar, ".toInstant());");
				}
				else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
					tl(2, "return ", entiteVar, " == null ? null : Date.from(", entiteVar, ".atZone(ZoneId.systemDefault()).toInstant());");
				}
				else if(entiteNomSimple.toString().equals("LocalDate")) {
					tl(2, "return ", entiteVar, " == null ? null : Date.from(", entiteVar, ".atStartOfDay(ZoneId.systemDefault()).toInstant());");
				}
				else if(entiteNomSimple.toString().equals("BigDecimal")) {
					tl(2, "return ", entiteVar, " == null ? null : ", entiteVar, ".doubleValue();");
				}
				else if("java.util.List".equals(entiteNomCanonique)) {
					tl(2, "return ", entiteVar, ";");
				}
				else if("java.util.Set".equals(entiteNomCanonique) || "java.util.HashSet".equals(entiteNomCanonique)) {
					tl(2, "return new ArrayList<>(", entiteVar, ");");
				}
				else {
					tl(2, "return ", entiteVar, ";");
				}
				tl(1, "}");
	
				/////////
				// str //
				/////////
				l();
				tl(1, "public String str", entiteVarCapitalise, "() {");
				if(VAL_nomCanoniqueString.equals(entiteNomCanonique))
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ";");
				else
					tl(2, "return ", entiteVar, " == null ? \"\" : ", entiteVar, ".toString();");
				tl(1, "}");
	
				//////////////////
				// nomAffichage //
				//////////////////
				l();
				tl(1, "public String nomAffichage", entiteVarCapitalise, "() {");
				tl(2, "return ", entiteNomAffichage == null ? "null" : "\"" + escapeJava(entiteNomAffichage) + "\"", ";");
				tl(1, "}");
	
				/////////////////
				// htmTooltip //
				/////////////////
				l();
				tl(1, "public String htmTooltip", entiteVarCapitalise, "() {");
				tl(2, "return ", entiteHtmlTooltip == null ? "null" : "\"" + escapeJava(entiteHtmlTooltip) + "\"", ";");
				tl(1, "}");
	
				//////////
				// htm //
				//////////
	
				l();
				tl(1, "public String htm", entiteVarCapitalise, "() {");
				tl(2, "return ", entiteVar, " == null ? \"\" : StringEscapeUtils.escapeHtml4(str", entiteVarCapitalise, "());");
				tl(1, "}");
	
				if(entiteVarCapitalise != null && classeSauvegarde && entiteSolrNomCanonique != null) {
					l();
					tl(1, "public void htm", entiteVarCapitalise, "(ToutEcrivain r, Boolean patchDroits) {");
					tl(2, "if(", classeVarClePrimaire, "!= null) {");
					tl(3, "r.s(\"<div id=\\\"patch", classeNomSimple, "\", str", StringUtils.capitalize(classeVarClePrimaire), "(), \"", entiteVarCapitalise, "\\\">\");");
					tl(3, "if(patchDroits) {");
					tl(4, "r.l();");
					tl(4, "r.l(\"	<script>//<![CDATA[\");");
					tl(4, "r.l(\"		function patch", classeNomSimple, "\", str", StringUtils.capitalize(classeVarClePrimaire), "(), \"", entiteVarCapitalise, "() {\");");
					tl(4, "r.l(\"			$.ajax({\");");
					tl(4, "r.l(\"				url: '", classeApiUri, "?fq=", classeVarClePrimaire, ":\", str", StringUtils.capitalize(classeVarClePrimaire), "(), \"',\");");
					tl(4, "r.l(\"				dataType: 'json',\");");
					tl(4, "r.l(\"				type: 'patch',\");");
					tl(4, "r.l(\"				contentType: 'application/json',\");");
					tl(4, "r.l(\"				processData: false,\");");
					tl(4, "r.l(\"				success: function( data, textStatus, jQxhr ) {\");");
					tl(4, "r.l(\"					\");");
					tl(4, "r.l(\"				},\");");
					tl(4, "r.l(\"				error: function( jqXhr, textStatus, errorThrown ) {\");");
					tl(4, "r.l(\"					\");");
					tl(4, "r.l(\"				},\");");
					tl(4, "r.l(\"				data: {\\\"set", entiteVarCapitalise, "\\\": this.value },\");");
					tl(4, "r.l(\"				\");");
					tl(4, "r.l(\"			});\");");
					tl(4, "r.l(\"		}\");");
					tl(4, "r.l(\"	//]]></script>\");");
					tl(4, "r.l(\"	<div class=\\\"\\\">\");");
					tl(4, "r.l(\"		<label class=\\\"w3-tooltip \\\">\");");
					tl(4, "r.l(\"			<span>\", StringEscapeUtils.escapeHtml4(nomAffichage", entiteVarCapitalise, "()), \"</span>\");");
					tl(4, "r.s(\"			<input\");"); {
						tl(7, "r.s(\" name=\\\"", entiteVar, "\\\"\");");
						tl(7, "r.s(\" value=\\\"\", htm", entiteVarCapitalise, "(), \"\\\");\");");
						tl(7, "r.s(\" onchange=\\\"\\\"\");");
						tl(7, "r.l(\"/>\");");
					}
					if(entiteHtmlTooltip != null)
						tl(4, "r.s(\"<span class=\\\"w3-text w3-tag site-tooltip \\\">", escapeJava(entiteHtmlTooltip), "</span>\");");
					tl(4, "r.l(\"		</label>\");");
					tl(4, "r.l(\"	</div>\");");
					tl(3, "} else {");
					tl(4, "r.s(htm", entiteVarCapitalise, "());");
					tl(3, "}");
					tl(3, "r.l(\"</div>\");");
					tl(2, "}");
					tl(1, "}");
				}
			}
	
			////////////////////
			// codeIninitLoin //
			////////////////////
			if(entiteInitLoin) {
				wInitLoin.tl(2, entiteVar, "Init();");
			}
	
	
			/////////////////////
			// codeRequeteSite //
			/////////////////////
			if(classeInitLoin && entiteInitialise) {
				o = wRequeteSite;
				tl(2, entiteVar, ".setRequeteSite_(requeteSite_);");
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
					tl(3, "document.addField(\"", entiteVar, "_incremented", entiteSuffixeType, "\", new java.util.HashMap<String, ", entiteNomSimple, ">() {{ put(\"inc\"", ("Long".equals(entiteNomSimple.toString()) ? "1L" : "1"), "); }});");
				}
				if(entiteSuggere) {
					// suggere
					if(entiteNomSimple.equals("Chaine")) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", entiteSuffixeType, "\", ", entiteVar, ");");
					}
					else if(entiteNomSimple.equals("Timestamp")) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
					}
					else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entiteVar, "));");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entiteVar, ".atStartOfDay(ZoneId.of(\"Z\"))));");
					}
					else {
						tl(3, "document.addField(\"", entiteVar, "_suggested", entiteSuffixeType, "\", ", entiteVar, ");");
					}
				}
	
				if(entiteNomSimple != null && entiteIndexe) {
					// indexe
					if(entiteNomSimple.equals("Chaine")) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", ", entiteVar, ");");
					}
					else if(entiteNomSimple.equals("Timestamp")) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
					}
					else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(ZonedDateTime.ofInstant(", entiteVar, ".toInstant(), ZoneId.of(\"UTC\"))));");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(", entiteVar, ".atStartOfDay(ZoneId.of(\"Z\"))));");
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
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.of(\"Z\"))));");
					}
					else if(entiteNomCanonique.toString().equals(ZonedDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(ZonedDateTime.ofInstant(", entiteVar, ".toInstant(), ZoneId.of(\"UTC\"))));");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(", entiteVar, ".atOffset(ZoneOffset.UTC)));");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss'Z'\").format(", entiteVar, ".atStartOfDay(ZoneId.of(\"Z\"))));");
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
				if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName()))
					tl(4, "o", classeNomSimple, ".add", entiteVarCapitalise, "((", entiteNomSimpleCompletGenerique, ")val);");
				else
					tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "((", entiteNomSimpleComplet, ")val);");
				tl(4, "return val;");
			}	
	
			/////////////
			// definir //
			/////////////
			o = wDefinir;
			
			if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
					tl(3, "case \"", entiteVar, "\":");
					if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
						tl(4, "add", entiteVarCapitalise, "(val);");
						tl(4, "if(!sauvegardes", classeNomSimple, ".contains(var))");
						tl(5, "sauvegardes", classeNomSimple, ".add(var);");
					}
					else {
						tl(4, "set", entiteVarCapitalise, "(val);");
						tl(4, "sauvegardes", classeNomSimple, ".add(var);");
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
						tl(3, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
						tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_suggested", entiteSuffixeType, "\");");
						tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
						tl(3, "}");
					}
					else if(entiteIncremente) {
						tl(3, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
						tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_incremented", entiteSuffixeType, "\");");
						tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
						tl(3, "}");
					}
					else if(entiteCleUnique) {
						tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "\");");
						tl(3, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
					}
					else if(entiteClePrimaire) {
						tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_stored", entiteSuffixeType, "\");");
						tl(3, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
					}
					else if(entiteCrypte) {
						tl(3, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
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
						tl(3, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
						tl(4, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_stored", entiteSuffixeType, "\");");
						tl(4, "if(", entiteVar, " != null)");
						if(StringUtils.contains(entiteSolrNomCanonique, "<"))
							tl(5, "o", classeNomSimple, ".", entiteVar, ".addAll(", entiteVar, ");");
						else
							tl(5, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
						tl(3, "}");
					}
	
				}
			}	
	
			/////////////////
			// codeStocker //
			/////////////////
			o = wStocker;
			if(entiteCrypte || entiteStocke || entiteCleUnique || entiteSuggere || entiteIncremente) {
				tl(0);

				if(entiteSuggere) {
					tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_suggested", entiteSuffixeType, "\");");
					tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
				}
				else if(entiteIncremente) {
					tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_incremented", entiteSuffixeType, "\");");
					tl(2, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
				}
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
					tl(2, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_stored", entiteSuffixeType, "\");");
					tl(2, "if(", entiteVar, " != null)");
					if(StringUtils.contains(entiteSolrNomCanonique, "<"))
						tl(3, "o", classeNomSimple, ".", entiteVar, ".addAll(", entiteVar, ");");
					else
						tl(3, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
				}

			}
	
			/////////////////
			// codeApiChamps //
			/////////////////
			o = wApiEntites;
	//		l();
	//		tl(1, "public static final String ENTITE_VAR_", entiteVar, " = \"", entiteVar, "\";");
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
	
			////////////////////////
			// codeApiGenererPost //
			////////////////////////
			o = wApiGenererPost;
	
			Integer tBase = 3;
	//		if(classeRolesTrouves) {
	//			tBase = 6;
	//		}
	//		else {
	//			tBase = 4;
	//		}
			if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
				tl(tBase + 2, "case \"", entiteVar, "\":");
				tl(tBase + 3, "postSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_setD);");
				tl(tBase + 3, "postSqlParams.addAll(Arrays.asList(\"", entiteVar, "\", jsonObject.get", entiteNomSimpleVertxJson, "(entiteVar), ", classeVarClePrimaire, "));");
				tl(tBase + 3, "break;");
			}	
			if(classeSauvegarde && BooleanUtils.isTrue(entiteAttribuer)) {
				tl(tBase + 2, "case \"", entiteVar, "\":");
				tl(tBase + 3, "postSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_addA);");
				if(StringUtils.compare(entiteVar, entiteAttribuerVar) < 0)
					tl(tBase + 3, "postSqlParams.addAll(Arrays.asList(\"", entiteVar, "\", jsonObject.getLong(entiteVar), \"", entiteAttribuerVar, "\", ", classeVarClePrimaire, "));");
				else
					tl(tBase + 3, "postSqlParams.addAll(Arrays.asList(\"", entiteAttribuerVar, "\", ", classeVarClePrimaire, ", \"", entiteVar, "\", jsonObject.getLong(entiteVar)));");
				tl(tBase + 3, "break;");
			}	
	
			///////////////////////
			// codeApiGenererPut //
			///////////////////////
			o = wApiGenererPut;
	
			tBase = 0;
			if(classeRolesTrouves) {
				tBase = 6;
			}
			else {
				tBase = 4;
			}
			if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
				tl(tBase + 6, "case \"", entiteVar, "\":");
				tl(tBase + 7, "putSql.append(", classePartsSiteContexte.nomSimple(langueNom), ".SQL_setD);");
				tl(tBase + 7, "putSqlParams.addAll(Arrays.asList(\"", entiteVar, "\", requeteJson.get", entiteNomSimpleVertxJson, "(entiteVar), putPk));");
				tl(tBase + 7, "break;");
			}	
	
			if(entiteDefinir) {
		
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
	 * r: initLoinPourClasse
	 * r.enUS: initDeepForClass
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
	 * r: wExiste
	 * r.enUS: wExists
	 * r: wSauvegardes
	 * r.enUS: wSaves
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
	 * r: wExiste
	 * r.enUS: wExists
	 * r: wSauvegardes
	 * r.enUS: wSaves
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
	 * 
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
	 * r: SiteContexte
	 * r.enUS: SiteContext
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
	 */
	public void genCodeClasseFin(String langueNom) throws Exception {
		//////////////////
		// codeInitLoin //
		//////////////////
		if(classeInitLoin) {
//			wInitLoin.tl(3, "dejaInitialise", classeNomSimple, " = true;");
			wInitLoin.tl(1, "}");
			if(classeInitLoin) {
				wInitLoin.l();
				wInitLoin.t(1);
				if(classeEtendBase)
					wInitLoin.s("@Override ");
				wInitLoin.s("public void initLoinPourClasse(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite_)");
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
				wInitLoin.tl(2, "initLoin", classeNomSimple, "(requeteSite_);");
				wInitLoin.tl(1, "}");  
			}
		}

		/////////////////////
		// codeRequeteSite //
		/////////////////////
		if(classeInitLoin) {
			o = wRequeteSite;
			tl(1, "}");
			l();
			tl(1, "public void requeteSitePourClasse(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite_) {");
			tl(2, "requeteSite", classeNomSimple, "(requeteSite_);");
			tl(1, "}");  
		}

		/////////////////
		// codeIndexer //
		/////////////////
		o = wIndexer;
		if(classeIndexe) {
			if(classeEtendBase && !classeEstBase) {
				tl(2, "super.indexer", classeNomSimpleSuperGenerique, "(document);");
				tl(0);
			}
			l("\t}");

			if(StringUtils.isNotEmpty(classeVarCleUnique)) {
				tl(0);
				tl(1, "public void desindexer", classeNomSimple, "() throws Exception {");
				tl(2, "", classePartsRequeteSite.nomSimple(langueNom), " requeteSite = new ", classePartsRequeteSite.nomSimple(langueNom), "();");
				tl(2, "requeteSite.initLoin", classePartsRequeteSite.nomSimple(langueNom), "();");
				tl(2, classePartsSiteContexte.nomSimple(langueNom), " siteContexte = new ", classePartsSiteContexte.nomSimple(langueNom), "();");
				tl(2, "siteContexte.initLoin", classePartsSiteContexte.nomSimple(langueNom), "();");
				tl(2, "siteContexte.setRequeteSite_(requeteSite);");
				tl(2, "requeteSite.setSiteContexte_(siteContexte);");
				tl(2, "requeteSite.setConfigSite_(siteContexte.getConfigSite());");
				tl(2, "initLoin", classeNomSimple, "(siteContexte.getRequeteSite_());");
				tl(2, "SolrClient clientSolr = siteContexte.getClientSolr();");
				tl(2, "clientSolr.deleteById(", classeVarCleUnique, ".toString());");
				tl(2, "clientSolr.commit();");
				tl(1, "}");
			}
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
				tl(4, "return super.obtenir", classeNomSimpleSuperGenerique, "(var);");

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
				tl(4, "return super.attribuer", classeNomSimpleSuperGenerique, "(var, val);");

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
		wExiste.flushClose();
		wSauvegardes.flushClose();
		wDefinir.flushClose();
		wApiEntites.flushClose();
		wPageEntites.flushClose();
		wPageGet.flushClose();
		wHashCode.flushClose();
		wToString.flushClose();
		wEquals.flushClose();

		o = auteurGenClasse;

		s(wInitLoin.toString());
		s(wRequeteSite.toString());
		s(wIndexer.toString());
		s(wObtenir.toString());
		s(wAttribuer.toString());


		if(classeInitLoin && (classeEtendBase || classeEstBase)) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// definir //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(!classeEstBase)
				s("@Override ");
			s("public boolean definirPourClasse(String var, String val)");
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
			tl(5, "o = definir", classeNomSimple, "(v, val);");
			tl(4, "else if(o instanceof Cluster) {");
			tl(5, "Cluster cluster = (Cluster)o;");
			tl(5, "o = cluster.definirPourClasse(v, val);");
			tl(4, "}");
			tl(3, "}");
			tl(2, "}");
			tl(2, "return o != null;");
			tl(1, "}");
			tl(1, "public Object definir", classeNomSimple, "(String var, String val) {");
			tl(2, "switch(var) {");
			s(wDefinir.toString());
			tl(3, "default:");

			if(classeEstBase)
				tl(4, "return null;");
			else
				tl(4, "return super.definir", classeNomSimpleSuperGenerique, "(var, val);");

			tl(2, "}");
			tl(1, "}");
		}

		if(classeSauvegarde) {
			l(); 
			tl(1, "/////////////////");
			tl(1, "// sauvegardes //");
			tl(1, "/////////////////");
			tl(0);
			tl(1, "protected List<String> sauvegardes", classeNomSimple, " = new ArrayList<String>();");
		}

		/////////////////
		// codePeupler //
		/////////////////
		if(classeSauvegarde) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// peupler //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(BooleanUtils.isTrue(classeEtendBase))
				s("@Override ");
			l("public void peuplerPourClasse(SolrDocument solrDocument) {");
			if(classeSauvegarde) {
			tl(2, "peupler", classeNomSimple, "(solrDocument);");
			}
			tl(1, "}");
			tl(1, "public void peupler", classeNomSimple, "(SolrDocument solrDocument) {");
			tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
			tl(2, "sauvegardes", classeNomSimple, " = (List<String>)solrDocument.get(\"sauvegardes", classeNomSimple, "_stored_strings\");");
			tl(2, "if(sauvegardes", classeNomSimple, " != null) {");
			s(wPeupler.toString());
			tl(2, "}");
			if(BooleanUtils.isTrue(classeEtendBase)) {
				tl(0);
				tl(2, "super.peupler", classeNomSimpleSuperGenerique, "(solrDocument);");
			}

			tl(1, "}");
		}	

		/////////////////
		// codeStocker //
		/////////////////
		if(classeSauvegarde) {
			l(); 
			tl(1, "/////////////");
			tl(1, "// stocker //");
			tl(1, "/////////////");
			tl(0);
			t(1);
			if(BooleanUtils.isTrue(classeEtendBase))
				s("@Override ");
			l("public void stockerPourClasse(SolrDocument solrDocument) {");
			if(classeSauvegarde) {
			tl(2, "stocker", classeNomSimple, "(solrDocument);");
			}
			tl(1, "}");
			tl(1, "public void stocker", classeNomSimple, "(SolrDocument solrDocument) {");
			tl(2, classeNomSimple, " o", classeNomSimple, " = (", classeNomSimple, ")this;");
			s(wStocker.toString());
			if(BooleanUtils.isTrue(classeEtendBase)) {
				tl(0);
				tl(2, "super.stocker", classeNomSimpleSuperGenerique, "(solrDocument);");
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
					tl(2, "super.", classeEcrireMethode, classeNomSimpleSuperGenerique, "();");
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
	//				tl(1, "public void ", siteEcrireMethode, "Avant() {");
	//				tl(2, siteEcrireMethode, classeNomSimple, "Avant();");
	//				if(BooleanUtils.isTrue(classeEtendBase)) {
	//					tl(2, "super.", siteEcrireMethode, classeNomSimpleSuperGenerique, "Avant();");
	//				}
	//				tl(1, "}");
	//				tl(1, "public void ", siteEcrireMethode, "Milieu() {");
	//				tl(2, siteEcrireMethode, classeNomSimple, "Milieu();");
	//				if(BooleanUtils.isTrue(classeEtendBase)) {
	//					tl(2, "super.", siteEcrireMethode, classeNomSimpleSuperGenerique, "Milieu();");
	//				}
	//				tl(1, "}");
	//				tl(1, "public void ", siteEcrireMethode, "Apres() {");
	//				tl(2, siteEcrireMethode, classeNomSimple, "Apres();");
	//				if(BooleanUtils.isTrue(classeEtendBase)) {
	//					tl(2, "super.", siteEcrireMethode, classeNomSimpleSuperGenerique, "Apres();");
	//				}
	//				tl(1, "}");
	//				tl(1, "public void ", siteEcrireMethode, classeNomSimple, "Avant() {");
	////				s(wStocker.toString());
	//				tl(1, "}");
	//				tl(1, "public void ", siteEcrireMethode, classeNomSimple, "Milieu() {");
	////				s(wStocker.toString());
	//				tl(1, "}");
	//				tl(1, "public void ", siteEcrireMethode, classeNomSimple, "Apres() {");
	////				s(wStocker.toString());
	//				tl(1, "}");
			}
		}

//		s(wExiste.toString());

		/////////////////////
		// codeSauvegarder //
		/////////////////////
//		if(classeSauvegarde) {
//			l(); 
//			tl(1, "/////////////////");
//			tl(1, "// sauvegarder //");
//			tl(1, "/////////////////");
//			tl(0);
//			t(1);
//			if(classeEtendBase)
//				s("@Override ");
//			l("public void sauvegarderPourClasse(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite_) throws Exception {");
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite.", classePartsSiteContexte.nomSimple(langueNom), ".sourceDonnees);");
//			tl(2, ArrayListHandler.class.getCanonicalName(), " gestionnaireListe = new ", ArrayListHandler.class.getCanonicalName(), "();");
//			tl(2, "String pkStr = requeteSite_.getRequeteServeur().getParam(\"pk\");");
//			tl(2, "pk = ", StringUtils.class.getCanonicalName(), ".isNumeric(pkStr) ? Long.parseLong(pkStr) : null;");
//			tl(2, "utilisateurId = requeteSite.utilisateurId;");
//			tl(2, "String nomCanonique = getClass().getCanonicalName();");
//			tl(2, "modifie = ", LocalDateTime.class.getCanonicalName(), ".now();");
//			tl(2, Timestamp.class.getCanonicalName(), " horodatage = java.sql.Timestamp.valueOf(modifie);");
//
//			tl(2);
//			tl(2, "if(pk == null) {");
//			tl(3, "String sql = \"insert into objet(nom_canonique, id_utilisateur, cree, modifie) values(?, ?, ?, ?) returning pk\";");
//			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.insert(sql, gestionnaireListe /*insert into objet(nom_canonique, id_utilisateur, cree, modifie) values(*/, nomCanonique, requeteSite.utilisateurId, horodatage, horodatage /*) returning pk, cree*/);");
//			tl(3, "pk = (Long)resultats.get(0)[0];");
//			tl(3, "cree = modifie;");
//			tl(2, "}");
//			tl(2, "else {");
//			tl(3, "String sql = \"update objet set modifie=? where objet.pk=? and objet.id_utilisateur=? and objet.nom_canonique=? returning cree\";");
//			tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sql, gestionnaireListe /*update objet set modifie=*/, horodatage /* where objet.pk=*/, pk /* and objet.id_utilisateur=*/, requeteSite.utilisateurId /* and objet.nom_canonique=*/, nomCanonique /* returning cree*/);");
//			tl(3, "if(resultats.size() == 0)");
//			t(4, "throw new Exception(\"");
//			s("L'objet avec le pk \" + pk + \" et nom canonique \" + pk + \" pour utilisateur \" + requeteSite.utilisateurId + \" \" + requeteSite.utilisateurNom + \" n'existe pas dejà. ");
//			l("\");");
//			tl(3, "horodatage = (java.sql.Timestamp)resultats.get(0)[0];");
//			tl(3, "cree = ", LocalDateTime.class.getCanonicalName(), ".from(horodatage.toLocalDateTime());");
//			tl(2, "}");
////						tl(0);
////						tl(2, "{");
////						tl(3, "String sqlSelectP = \"select chemin, valeur from p where p.pk_objet=?\";");
////						tl(3, List.class.getCanonicalName(), "<Object[]> resultats = coureur.query(sqlSelectP, gestionnaireListe /*select chemin, valeur from p where p.pk_objet=*/, pk);");
////						tl(3, "for(Object[] objets : resultats) {");
////						tl(4, "String chemin = (String)objets[0];");
////						if(coursCrypte)
////							tl(4, "String valeur = requeteSite.decrypterStr((String)objets[1]);");
////						else
////							tl(4, "String valeur = (String)objets[1];");
////						tl(4, "definir(chemin, valeur);");
////						tl(4, "sauvegardes", classeNomSimple, ".add(chemin);");
////						tl(3, "}");
////						tl(2, "}");
//			tl(0);
////						tl(2, "{");
//			tl(2, "String sqlInsertP = \"insert into p(chemin, valeur, pk_objet) values(?, ?, ?) on conflict(chemin, pk_objet) do update set valeur=? where p.chemin=? and p.pk_objet=?\";");
//			tl(2, "String sqlInsertA = \"insert into a(champ1, pk1, champ2, pk2) values(?, ?, ?, ?) on conflict  do nothing\";");
//			tl(2, "String sqlDeleteP = \"delete from p where chemin=? and pk_objet=?\";");
//			tl(2, "String sqlDeleteA = \"delete from a where champ1=? and pk1=? and champ2=? and pk2=?\";");
//			tl(2, "sauvegarder", classeNomSimple, "(requeteSite, sqlInsertP, sqlInsertA, sqlDeleteP, sqlDeleteA, gestionnaireListe, coureur);");
////						tl(2, "}");
//			tl(1, "}");
//			tl(1, "public void sauvegarder", classeNomSimple, "(", classePartsRequeteSite.nomSimple(langueNom), " requeteSite, String sqlInsertP, String sqlInsertA, String sqlDeleteP, String sqlDeleteA, ", ArrayListHandler.class.getCanonicalName(), " gestionnaireListe, ", QueryRunner.class.getCanonicalName(), " coureur) throws Exception {");
//			s(wSauvegarder.toString());
//			if(classeEtendBase) {
//				tl(0);
//				tl(2, "super.sauvegarder", classeNomSimpleSuperGenerique + "(requeteSite, sqlInsertP, sqlInsertA, sqlDeleteP, sqlDeleteA, gestionnaireListe, coureur);");
//			}
//			tl(1, "}");
//		}	

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
		tl(2, "sb.append(\"", classeNomSimple, " {\");");
		s(wToString.toString());
		tl(2, "sb.append(\" }\");");
		tl(2, "return sb.toString();");
		tl(1, "}");

		l("}"); 

		System.out.println("Ecrire: " + classeCheminGen); 
		auteurGenClasse.flushClose();
	}  
}
