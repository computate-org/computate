package org.computate.frFR.java;      

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;

/**  
 * nomCanonique.enUS: org.computate.enUS.java.WriteGenClass
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 */  
public class EcrireGenClasse extends EcrireClasse { 

	/**
	 * var.enUS: classDirPathGen
	 */
	protected String classeCheminRepertoireGen;

	/**
	 * var.enUS: classPathGen
	 */
	protected String classeCheminGen;

	/**
	 * var.enUS: classPathApiPackageInfo
	 */
	protected String classeCheminApiEnsembleInfo;

	/**
	 * var.enUS: classPathGenApiServiceImpl
	 */
	protected String classeCheminGenApiServiceImpl;

	/**
	 * var.enUS: classPathApiServiceImpl
	 */
	protected String classeCheminApiServiceImpl;

	/**
	 * var.enUS: classPathGenApiService
	 */
	protected String classeCheminGenApiService;

	/**
	 * var.enUS: classPathPageGen
	 */
	protected String classeCheminPageGen;

	/**
	 * var.enUS: classDirGen
	 */
	protected File classeRepertoireGen;

	/**
	 * var.enUS: classFileGen
	 */
	protected File classeFichierGen;

	/**
	 * var.enUS: classFileApiPackageInfo
	 */
	protected File classeFichierApiEnsembleInfo;

	/**
	 * var.enUS: classFileGenApiServiceImpl
	 */
	protected File classeFichierGenApiServiceImpl;

	/**
	 * var.enUS: classFileApiServiceImpl
	 */
	protected File classeFichierApiServiceImpl;

	/**
	 * var.enUS: classFileGenApiService
	 */
	protected File classeFichierGenApiService;

	/**
	 * var.enUS: classFilePage
	 */
	protected File classeFichierPage;

	protected StringBuilder s = new StringBuilder();
			
	protected SolrDocument doc;

	/**
	 * var.enUS: classCanonicalName
	 */
	protected String classeNomCanonique;

	/**
	 * var.enUS: classSimpleNameGen
	 */
	protected String classeNomSimpleGen;

	/**
	 * var.enUS: classSuperSimpleName
	 */
	protected String classeNomSimpleSuper;

	/**
	 * var.enUS: classSuperSimpleNameGeneric
	 */ 
	protected String classeNomSimpleSuperGenerique;

	/**
	 * var.enUS: classSuperCanonicalNameGeneric
	 */
	protected String classeNomCanoniqueSuperGenerique;

	/**
	 * var.enUS: classPackageName
	 */
	protected String classeNomEnsemble;

	/**
	 * var.enUS: classSimpleName
	 */
	protected String classeNomSimple;

	/**
	 * var.enUS: classSuperCanonicalName
	 */
	protected String classeNomCanoniqueSuper;

	/**
	 * var.enUS: classPageUri
	 */
	protected String classePageUri;

	/**
	 * var.enUS: classApiUri
	 */
	protected String classeApiUri;

	/**
	 * var.enUS: classComment
	 */
	protected String classeCommentaire;

	/**
	 * var.enUS: classVarPrimaryKey
	 */
	protected String classeVarClePrimaire;

	/**
	 * var.enUS: classVarUniqueKey
	 */
	protected String classeVarCleUnique;

	/**
	 * var.enUS: classImportsGen
	 */
	protected List<String> classeImportationsGen;

	/**
	 * var.enUS: classInitDeepExceptions
	 */
	protected List<String> classeInitLoinExceptions;

	/**
	 * var.enUS: classImportsGenApi
	 */
	protected List<String> classeImportationsGenApi;

	/**
	 * var.enUS: classImportsGenPage
	 */ 
	protected List<String> classeImportationsGenPage;

	/**
	 * var.enUS: classParameterTypeNames
	 */
	protected List<String> classeParametreTypeNoms;

	/**
	 * var.enUS: classSuperParameterTypeNames
	 */
	protected List<String> classeSuperParametreTypeNoms;

	/**
	 * var.enUS: classExtendsGen
	 */
	protected Boolean classeEtendGen;

	/**
	 * var.enUS: classBaseExtendsGen
	 */
	protected Boolean classeBaseEtendGen;

	/**
	 * var.enUS: classInitDeep
	 */
	protected Boolean classeInitLoin;

	/**
	 * var.enUS: classIndexed
	 */
	protected Boolean classeIndexe;

	/**
	 * var.enUS: classExtendsBase
	 */
	protected Boolean classeEtendBase;

	/**
	 * var.enUS: classIsBase
	 */
	protected Boolean classeEstBase;

	/**
	 * var.enUS: classSaved
	 */
	protected Boolean classeSauvegarde;

	/**
	 * var.enUS: classModel
	 */
	protected Boolean classeModele;

	/**
	 * var.enUS: classApi
	 */
	protected Boolean classeApi;

	/**
	 * var.enUS: classPage
	 */
	protected Boolean classePage;

	/**
	 * var.enUS: classRolesFound
	 */
	protected Boolean classeRolesTrouve;

	/**
	 * var.enUS: classRoles
	 */
	protected List<String> classeRoles;

	/**
	 * var.enUS: wInitDeep
	 */
	protected ToutEcrivain wInitLoin;

	/**
	 * var.enUS: wSiteRequest
	 */
	protected ToutEcrivain wRequeteSite;

	/**
	 * var.enUS: wIndex
	 */
	protected ToutEcrivain wIndexer;

	/**
	 * var.enUS: wObtain
	 */
	protected ToutEcrivain wObtenir;

	/**
	 * var.enUS: wAttribute
	 */
	protected ToutEcrivain wAttribuer;

	/**
	 * var.enUS: wPut
	 */ 
	protected ToutEcrivain wPut;

	/**
	 * var.enUS: wPopulate
	 */
	protected ToutEcrivain wPeupler;

	/**
	 * var.enUS: wExists
	 */
	protected ToutEcrivain wExiste;

	/**
	 * var.enUS: wSaves
	 */
	protected ToutEcrivain wSauvegardes;

	/**
	 * var.enUS: wDefine
	 */
	protected ToutEcrivain wDefinir;

	protected ToutEcrivain wApiGet;

	/**
	 * var.enUS: wApiGenerateGet
	 */
	protected ToutEcrivain wApiGenererGet;

	/**
	 * var.enUS: wApiGeneratePost
	 */
	protected ToutEcrivain wApiGenererPost;

	/**
	 * var.enUS: wApiGeneratePut
	 */
	protected ToutEcrivain wApiGenererPut;

	/**
	 * var.enUS: wApiGeneratePatch
	 */
	protected ToutEcrivain wApiGenererPatch;

	/**
	 * var.enUS: wApiEntities
	 */
	protected ToutEcrivain wApiEntites;

	/**
	 * var.enUS: wPageEntities
	 */
	protected ToutEcrivain wPageEntites;

	protected ToutEcrivain wPageGet;

	protected ToutEcrivain wHashCode;

	protected ToutEcrivain wToString;

	protected ToutEcrivain wEquals;

	/**
	 * var.enUS: entityVar
	 */
	protected String entiteVar;

	/**
	 * var.enUS: entityVarCapitalized
	 */
	protected String entiteVarCapitalise;

	/**
	 * var.enUS: entityCanonicalName
	 */
	protected String entiteNomCanonique;

	/**
	 * var.enUS: entityCanonicalNameGeneric
	 */
	protected String entiteNomCanoniqueGenerique;

	/**
	 * var.enUS: entitySimpleNameComplete
	 */
	protected String entiteNomSimpleComplet;

	/**
	 * var.enUS: entitySimpleNameCompleteGeneric
	 */
	protected String entiteNomSimpleCompletGenerique;

	/**
	 * var.enUS: entitySimpleName
	 */
	protected String entiteNomSimple;

	/**
	 * var.enUS: entityComment
	 */
	protected String entiteCommentaire;

	/**
	 * var.enUS: entityVarParam
	 */
	protected String entiteVarParam;

	/**
	 * var.enUS: entityWrap
	 */
	protected Boolean entiteCouverture;

	/**
	 * var.enUS: entityInitialized
	 */
	protected Boolean entiteInitialise;

	/**
	 * var.enUS: entityInitDeep
	 */
	protected Boolean entiteInitLoin;
	
	/**
	 * var.enUS: writerGenClass
	 */
	protected ToutEcrivain auteurGenClasse;

	/**
	 * var.enUS: entityIndex
	 */
	protected Integer entiteIndice;

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
		wPageGet = ToutEcrivain.create();
		wHashCode = ToutEcrivain.create();
		wToString = ToutEcrivain.create();
		wEquals = ToutEcrivain.create();
	}

	/**
	 * var.enUS: genCodeInitDeep
	 * param1.var.enUS: languageName
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
			wInitLoin.t(1, "public ", classeNomSimple, " initLoin", classeNomSimple, "(RequeteSite requeteSite)");
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
//						if(contientRequeteSite && !StringUtils.equals(classeNomSimple, "RequeteSite"))
//							tl(2, "((", classeNomSimple, ")this).setRequeteSite_(requeteSite);");
			wInitLoin.tl(2, "setRequeteSite_(requeteSite);");
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
	 * var.enUS: genCodeSiteRequest
	 * param1.var.enUS: languageName
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
			tl(1, "public void requeteSite", classeNomSimple, "(RequeteSite requeteSite) {");
			if(BooleanUtils.isTrue(classeEtendBase)) 
				tl(3, "super.requeteSite", classeNomSimpleSuperGenerique, "(requeteSite);");
		}
	}

	/**
	 * var.enUS: genCodeIndex
	 * param1.var.enUS: languageName
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
	 * 
	 * r: ClientSolr
	 * r.enUS: SolrClient
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
			tl(1, "//public void indexer", classeNomSimple, "() throws Exception {");
			tl(2, "//RequeteSite requeteSite = new RequeteSite();");
			tl(2, "//requeteSite.initLoinRequeteSite();");
			tl(2, "//SiteContexte siteContexte = new SiteContexte();");
			tl(2, "//siteContexte.initLoinSiteContexte();");
			tl(2, "//siteContexte.setRequeteSite_(requeteSite);");
			tl(2, "//requeteSite.setSiteContexte_(siteContexte);");
			tl(2, "//requeteSite", classeNomSimple, "(requeteSite);");
			tl(2, "//initLoin", classeNomSimple, "(requeteSite);");
			tl(2, "//indexer", classeNomSimple, "();");
			tl(1, "//}");
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
			tl(1, "public void indexer", classeNomSimple, "() throws Exception {");
			tl(2, "SolrInputDocument document = new SolrInputDocument();");
			tl(2, "indexer", classeNomSimple, "(document);");
//			if(classeSauvegarde)
//				tl(2, "document.addField(\"sauvegardes", classeNomSimple, "_stored_strings\", sauvegardes", classeNomSimple, ");");
			tl(2, "SolrClient clientSolr = requeteSite_.getSiteContexte_().getClientSolr();");
			tl(2, "clientSolr.add(document);");
			tl(2, "clientSolr.commit();");
			l("\t}");

			tl(0);
			tl(1, "public void indexer", classeNomSimple, "(SolrInputDocument document) throws Exception {");
		}
	}

	/**
	 * var.enUS: genCodeObtain
	 * param1.var.enUS: languageName
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
	 * var.enUS: genCodeAttribute
	 * param1.var.enUS: languageName
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
	 * var.enUS: genCodePut
	 * param1.var.enUS: languageName
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
	 * var.enUS: genCodePopulate
	 * param1.var.enUS: languageName
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
	 * var.enUS: genCodeExists
	 * param1.var.enUS: languageName
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
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite_.SiteContexte.sourceDonnees);");
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
//			tl(5, "connectionSql.queryWithParams(SiteContexte.SQL_existe, new JsonArray().add(pk)), chercher -> {");
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
	 * var.enUS: genCodeSaves
	 * param1.var.enUS: languageName
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
//			l("public void sauvegardesPourClasse(RequeteSite requeteSite) throws Exception {");
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite.SiteContexte.sourceDonnees);");
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
	 * var.enUS: genCodeClassBegin
	 * param1.var.enUS: languageName
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
	 * r: classeValsVar
	 * r.enUS: classValsVar
	 * r: classeValsLangue
	 * r.enUS: classValsLanguage
	 * r: classeValsValeur
	 * r.enUS: classValsValue
	 * r: classeValVar
	 * r.enUS: classValVar
	 * r: classeValLangue
	 * r.enUS: classValLanguage
	 * r: classeValValeur
	 * r.enUS: classValValue
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
			for(int j = 0; j < classeValsVar.size(); j++) {
				String classeValVar = classeValsVar.get(j);
				String classeValLangue = classeValsLangue.get(j);
				String classeValValeur = classeValsValeur.get(j);

				if(StringUtils.equals(langueNom, classeValLangue)) {
					tl(1, "public static final String ", classeValVar, " = \"", StringEscapeUtils.escapeJava(classeValValeur), "\";");
				}
			}
		}
	}

	/**
	 * var.enUS: genCodeEntity
	 * param1.var.enUS: languageName
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
	 * r: entiteOptionsTrouve
	 * r.enUS: entityOptionsFound
	 * r: entiteOptionsVar
	 * r.enUS: entityOptionsVar
	 * r: entiteOptionsLangue
	 * r.enUS: entityOptionsLanguage
	 * r: entiteOptionsValeur
	 * r.enUS: entityOptionsValue
	 * r: entiteOptionVar
	 * r.enUS: entityOptionVar
	 * r: entiteOptionLangue
	 * r.enUS: entityOptionLanguage
	 * r: entiteOptionValeur
	 * r.enUS: entityOptionValue
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
	 * r: champValeur
	 * r.enUS: fieldValue
	 * r: wApiGenererPost
	 * r.enUS: wApiGeneratePost
	 * r: classeRolesTrouve
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
	
			o = auteurGenClasse;
	
			l();
			String ligneCommentaire = "\t///" + String.join("", Collections.nCopies(entiteVar.length(), "/")) + "///";
			l(ligneCommentaire);
			tl(1, "// ", entiteVar, " //");
			l(ligneCommentaire);
			l();
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
				tl(2, "if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))");
				tl(3, "this.", entiteVar, " = Boolean.parseBoolean(o);");
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
			if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
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
		
				// Setter Boolean //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Boolean.class.getCanonicalName())) {
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, "if(org.apache.commons.lang3.BooleanUtils.isTrue(org.apache.commons.lang3.BooleanUtils.toBoolean(o)))");
					tl(3, entiteNomSimpleCompletGenerique, " p = Boolean.parseBoolean(o);");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter Integer //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Integer.class.getCanonicalName())) {
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
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = Timestamp.valueOf((LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME)));");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter Date //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, Date.class.getCanonicalName())) {
					tl(1, "/** Example: 2011-12-03T10:15:30+01:00 **/");
					tl(1, "public ", classeNomSimple, " add", entiteVarCapitalise, "(String o) {");
					tl(2, entiteNomSimpleCompletGenerique, " p = Date.from(LocalDateTime.parse(o, DateTimeFormatter.ISO_OFFSET_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant());");
					tl(2, "add", entiteVarCapitalise, "(p);");
					tl(2, "return (", classeNomSimple, ")this;");
					tl(1, "}");
				}
		
				// Setter LocalDate //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, LocalDate.class.getCanonicalName())) {
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
		
				// Setter LocalDateTime //
				if(StringUtils.equals(entiteNomCanoniqueGenerique, LocalDateTime.class.getCanonicalName())) {
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
			if(entiteInitialise) {
	
				if(entiteMethodesAvantVar != null && entiteMethodesAvantVar.size() > 0) {
					for(int j = 0; j < entiteMethodesAvantVar.size(); j++) {
						String entiteMethodeAvantVisibilite = entiteMethodesAvantVisibilite.get(j);
						String entiteMethodeAvantVar = entiteMethodesAvantVar.get(j);
						String entiteMethodeAvantParamVar = entiteMethodesAvantParamVar.get(j);
						String entiteMethodeAvantParamNomSimple = entiteMethodesAvantParamNomSimple.get(j);
						Boolean entiteMethodeAvantNomParam = entiteMethodesAvantNomParam.get(j);
						Boolean entiteMethodeAvantEcrire = entiteMethodesAvantEcrire.get(j);
	
						if(BooleanUtils.isTrue(entiteMethodeAvantEcrire)) {
							t(1, "((", classeNomSimple, ")this).", entiteMethodeAvantVisibilite, " abstract void ", entiteMethodeAvantVar, "(", entiteMethodeAvantParamNomSimple, " ", entiteMethodeAvantParamVar);
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
				if(entiteInitLoin) {
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
				else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
					tl(2, "return ", entiteVar, " == null ? null : Date.from(", entiteVar, ".atZone(ZoneId.systemDefault()).toInstant());");
				}
				else if(entiteNomSimple.toString().equals("LocalDate")) {
					tl(2, "return ", entiteVar, " == null ? null : Date.from(", entiteVar, ".atStartOfDay(ZoneId.systemDefault()).toInstant());");
				}
				else if(entiteNomSimple.toString().equals("BigDecimal")) {
					tl(2, "return ", entiteVar, " == null ? null : ", entiteVar, ".doubleValue();");
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
				tl(2, "return ", entiteNomAffichage == null ? "null" : "\"" + StringEscapeUtils.escapeJava(entiteNomAffichage) + "\"", ";");
				tl(1, "}");
	
				/////////////////
				// htmTooltip //
				/////////////////
				l();
				tl(1, "public String htmTooltip", entiteVarCapitalise, "() {");
				tl(2, "return ", entiteHtmlTooltip == null ? "null" : "\"" + StringEscapeUtils.escapeJava(entiteHtmlTooltip) + "\"", ";");
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
						tl(4, "r.s(\"<span class=\\\"w3-text w3-tag site-tooltip \\\">", StringEscapeUtils.escapeJava(entiteHtmlTooltip), "</span>\");");
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
			if(entiteInitialise) {
				wInitLoin.tl(2, entiteVar, "Init();");
			}
	
	
			/////////////////////
			// codeRequeteSite //
			/////////////////////
			if(classeInitLoin && entiteInitLoin) {
				o = wRequeteSite;
				tl(2, entiteVar, ".setRequeteSite_(requeteSite);");
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
						tl(3, "document.addField(\"", entiteVar, "_suggested", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.systemDefault())));");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ", java.time.OffsetDateTime.now().getOffset(), ZoneId.systemDefault())));");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entiteVar, "_suggested", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entiteVar, ".atStartOfDay(ZoneId.systemDefault())));");
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
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.systemDefault())));");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ", java.time.OffsetDateTime.now().getOffset(), ZoneId.systemDefault())));");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entiteVar, "_indexed", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entiteVar, ".atStartOfDay(ZoneId.systemDefault())));");
					}
					else if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList")) {
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
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ".toLocalDateTime(), java.time.OffsetDateTime.now().getOffset(), ZoneId.systemDefault())));");
					}
					else if(entiteNomCanonique.toString().equals(LocalDateTime.class.getCanonicalName())) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(java.time.ZonedDateTime.ofInstant(", entiteVar, ", java.time.OffsetDateTime.now().getOffset(), ZoneId.systemDefault())));");
					}
					else if(entiteNomSimple.toString().equals("LocalDate")) {
						tl(3, "document.addField(\"", entiteVar, "_stored", entiteSuffixeType, "\", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(", entiteVar, ".atStartOfDay(ZoneId.systemDefault())));");
					}
					else if(entiteNomSimple.equals("List") || entiteNomSimple.equals("ArrayList")) {
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
						tl(2, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
						tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_suggested", entiteSuffixeType, "\");");
						tl(3, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
						tl(2, "}");
					}
					else if(entiteIncremente) {
						tl(2, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
						tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_incremented", entiteSuffixeType, "\");");
						tl(3, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
						tl(2, "}");
					}
					else if(entiteCleUnique) {
						tl(2, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
						tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_stored", entiteSuffixeType, "\");");
						tl(3, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
						tl(2, "}");
					}
					else if(entiteCrypte) {
						tl(2, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
						if(siteCrypte)
							tl(3, entiteSolrNomSimple, " ", entiteVar, " = requeteSite.decrypterStr((", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_encrypted", entiteSuffixeType, "\"));");
						else
							tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_encrypted", entiteSuffixeType, "\");");
						tl(3, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
						tl(2, "}");
					}
					else {
						tl(2, "if(sauvegardes", classeNomSimple, ".contains(\"", entiteVar, "\")) {");
						tl(3, entiteSolrNomSimple, " ", entiteVar, " = (", entiteSolrNomSimple, ")solrDocument.get(\"", entiteVar, "_stored", entiteSuffixeType, "\");");
						tl(3, "if(", entiteVar, " != null)");
						if(StringUtils.contains(entiteSolrNomCanonique, "<"))
							tl(4, "o", classeNomSimple, ".", entiteVar, ".addAll(", entiteVar, ");");
						else
							tl(4, "o", classeNomSimple, ".set", entiteVarCapitalise, "(", entiteVar, ");");
						tl(2, "}");
					}
	
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
	
			/////////////////
			// codeApiGet //
			/////////////////
			o = wApiGet;
			if(classeIndexe && entiteIndexe) {
				tl(3, "case \"", entiteVar, "\":");
				tl(4, "return \"", entiteVar, "_indexed", entiteSuffixeType, "\";");
			}
	
			///////////////////////
			// codeApiGenererGet //
			///////////////////////
			o = wApiGenererGet;
			if(classeIndexe && entiteStocke) {
				tl(4, "if(\"", entiteVar, "\".equals(entiteVarStocke)) {");
				if (VAL_nomCanoniqueBoolean.equals(entiteSolrNomCanonique)) {
					tl(5, "if(j > 0)");
					tl(6, "reponseServeur.write(\", \");");
					tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \");");
					tl(5, "reponseServeur.write(((Boolean)champValeur).toString());");
					tl(5, "reponseServeur.write(\"\\n\");");
					tl(5, "j++;");
					tl(5, "return j;");
				} else if (VAL_nomCanoniqueDate.equals(entiteSolrNomCanonique)) {
					if (VAL_nomCanoniqueTimestamp.equals(entiteNomCanonique)) {
						tl(5, "if(j > 0)");
						tl(6, "reponseServeur.write(\", \");");
						tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \\\"\");");
						tl(5, "reponseServeur.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)champValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
						tl(5, "reponseServeur.write(\"\\\"\\n\");");
						tl(5, "j++;");
						tl(5, "return j;");
					} else if (VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanonique)) {
						tl(5, "if(j > 0)");
						tl(6, "reponseServeur.write(\", \");");
						tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \\\"\");");
						tl(5, "reponseServeur.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)champValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
						tl(5, "reponseServeur.write(\"\\\"\\n\");");
						tl(5, "j++;");
						tl(5, "return j;");
					} else if (VAL_nomCanoniqueLocalDate.equals(entiteNomCanonique)) {
						tl(5, "if(j > 0)");
						tl(6, "reponseServeur.write(\", \");");
						tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \\\"\");");
						tl(5, "reponseServeur.write(DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)champValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));");
						tl(5, "reponseServeur.write(\"\\\"\\n\");");
						tl(5, "j++;");
						tl(5, "return j;");
					} else {
						tl(5, "if(j > 0)");
						tl(6, "reponseServeur.write(\", \");");
						tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \\\"\");");
						tl(5, "reponseServeur.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)champValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
						tl(5, "reponseServeur.write(\"\\\"\\n\");");
						tl(5, "j++;");
						tl(5, "return j;");
					}
				} else if (VAL_nomCanoniqueLong.equals(entiteSolrNomCanonique)) {
					tl(5, "if(j > 0)");
					tl(6, "reponseServeur.write(\", \");");
					tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \");");
					tl(5, "reponseServeur.write(((Long)champValeur).toString());");
					tl(5, "reponseServeur.write(\"\\n\");");
					tl(5, "j++;");
					tl(5, "return j;");
				} else if (VAL_nomCanoniqueDouble.equals(entiteSolrNomCanonique)) {
					if (VAL_nomCanoniqueBigDecimal.equals(entiteNomCanonique)) {
						tl(5, "if(j > 0)");
						tl(6, "reponseServeur.write(\", \");");
						tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \");");
						tl(5, "reponseServeur.write(BigDecimal.valueOf((Double)champValeur).toString());");
						tl(5, "reponseServeur.write(\"\\n\");");
						tl(5, "j++;");
						tl(5, "return j;");
					}
					else {
						tl(5, "if(j > 0)");
						tl(6, "reponseServeur.write(\", \");");
						tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \");");
						tl(5, "reponseServeur.write(((Double)champValeur).toString());");
						tl(5, "reponseServeur.write(\"\\n\");");
						tl(5, "j++;");
						tl(5, "return j;");
					}
				} else if (VAL_nomCanoniqueFloat.equals(entiteSolrNomCanonique)) {
					tl(5, "if(j > 0)");
					tl(6, "reponseServeur.write(\", \");");
					tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \");");
					tl(5, "reponseServeur.write(((Float)champValeur).toString());");
					tl(5, "reponseServeur.write(\"\\n\");");
					tl(5, "j++;");
					tl(5, "return j;");
				} else if (VAL_nomCanoniqueInteger.equals(entiteSolrNomCanonique)) {
					tl(5, "if(j > 0)");
					tl(6, "reponseServeur.write(\", \");");
					tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \");");
					tl(5, "reponseServeur.write(((Integer)champValeur).toString());");
					tl(5, "reponseServeur.write(\"\\n\");");
					tl(5, "j++;");
					tl(5, "return j;");
				} else {
					if(StringUtils.equalsAny(entiteNomCanonique, VAL_nomCanoniqueList, VAL_nomCanoniqueArrayList)) {
						if(VAL_nomCanoniqueBoolean.equals(entiteNomCanoniqueGenerique)) {
							tl(5, "if(j > 0)");
							tl(6, "reponseServeur.write(\", \");");
							tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \");");
							tl(5, "int k = 0;");
							tl(5, "while(champValeur != null) {");
							tl(6, "if(k > 0)");
							tl(7, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(((Boolean)champValeur).toString());");
							tl(6, "champValeur = champValeurs.iterator().next();");
							tl(5, "}");
							tl(5, "reponseServeur.write(VAL_guillmetsFin);");
							tl(5, "j++;");
							tl(5, "return j;");
						}
						else if(VAL_nomCanoniqueDate.equals(entiteNomCanoniqueGenerique)) {
							tl(5, "if(j > 0)");
							tl(6, "reponseServeur.write(\", \");");
							tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \");");
							tl(5, "int k = 0;");
							tl(5, "while(champValeur != null) {");
							tl(6, "if(k > 0)");
							tl(7, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(VAL_citation);");
							tl(6, "reponseServeur.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)champValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
							tl(6, "reponseServeur.write(VAL_citation);");
							tl(6, "champValeur = champValeurs.iterator().next();");
							tl(5, "}");
							tl(5, "reponseServeur.write(VAL_guillmetsFin);");
							tl(5, "j++;");
							tl(5, "return j;");
						}
						else if(VAL_nomCanoniqueTimestamp.equals(entiteNomCanoniqueGenerique)) {
							tl(5, "if(j > 0)");
							tl(6, "reponseServeur.write(\", \");");
							tl(5, "reponseServeur.write(VAL_citation);");
							tl(5, "reponseServeur.write(\"", entiteVar, "\");");
							tl(5, "reponseServeur.write(VAL_citationDeuxPointsEspaceGuillmets);");
							tl(5, "int k = 0;");
							tl(5, "while(champValeur != null) {");
							tl(6, "if(k > 0)");
							tl(7, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(VAL_citation);");
							tl(6, "reponseServeur.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)champValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
							tl(6, "reponseServeur.write(VAL_citation);");
							tl(6, "champValeur = champValeurs.iterator().next();");
							tl(5, "}");
							tl(5, "reponseServeur.write(VAL_guillmetsFin);");
							tl(5, "j++;");
							tl(5, "return j;");
						}
						else if(VAL_nomCanoniqueLocalDateTime.equals(entiteNomCanoniqueGenerique)) {
							tl(5, "if(j > 0)");
							tl(6, "reponseServeur.write(\", \");");
							tl(5, "reponseServeur.write(VAL_citation);");
							tl(5, "reponseServeur.write(\"", entiteVar, "\");");
							tl(5, "reponseServeur.write(VAL_citationDeuxPointsEspaceGuillmets);");
							tl(5, "int k = 0;");
							tl(5, "while(champValeur != null) {");
							tl(6, "if(k > 0)");
							tl(7, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(VAL_citation);");
							tl(6, "reponseServeur.write(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(((Date)champValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));");
							tl(6, "reponseServeur.write(VAL_citation);");
							tl(6, "champValeur = champValeurs.iterator().next();");
							tl(5, "}");
							tl(5, "reponseServeur.write(VAL_guillmetsFin);");
							tl(5, "j++;");
							tl(5, "return j;");
						}
						else if(VAL_nomCanoniqueLocalDate.equals(entiteNomCanoniqueGenerique)) {
							tl(5, "if(j > 0)");
							tl(6, "reponseServeur.write(\", \");");
							tl(5, "reponseServeur.write(VAL_citation);");
							tl(5, "reponseServeur.write(\"", entiteVar, "\");");
							tl(5, "reponseServeur.write(VAL_citationDeuxPointsEspaceGuillmets);");
							tl(5, "int k = 0;");
							tl(5, "while(champValeur != null) {");
							tl(6, "if(k > 0)");
							tl(7, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(VAL_citation);");
							tl(6, "reponseServeur.write(DateTimeFormatter.ISO_OFFSET_DATE.format(((Date)champValeur).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));");
							tl(6, "reponseServeur.write(VAL_citation);");
							tl(6, "champValeur = champValeurs.iterator().next();");
							tl(5, "}");
							tl(5, "reponseServeur.write(VAL_guillmetsFin);");
							tl(5, "j++;");
							tl(5, "return j;");
						}
						else if(VAL_nomCanoniqueLong.equals(entiteNomCanoniqueGenerique)) {
							tl(5, "if(j > 0)");
							tl(6, "reponseServeur.write(\", \");");
							tl(5, "reponseServeur.write(VAL_citation);");
							tl(5, "reponseServeur.write(\"", entiteVar, "\");");
							tl(5, "reponseServeur.write(VAL_citationDeuxPointsEspaceGuillmets);");
							tl(5, "int k = 0;");
							tl(5, "while(champValeur != null) {");
							tl(6, "if(k > 0)");
							tl(7, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(((Long)champValeur).toString());");
							tl(6, "champValeur = champValeurs.iterator().next();");
							tl(5, "}");
							tl(5, "reponseServeur.write(VAL_guillmetsFin);");
							tl(5, "j++;");
							tl(5, "return j;");
						}
						else if(VAL_nomCanoniqueBigDecimal.equals(entiteNomCanoniqueGenerique)) {
							tl(5, "if(j > 0)");
							tl(6, "reponseServeur.write(\", \");");
							tl(5, "reponseServeur.write(VAL_citation);");
							tl(5, "reponseServeur.write(\"", entiteVar, "\");");
							tl(5, "reponseServeur.write(VAL_citationDeuxPointsEspaceGuillmets);");
							tl(5, "int k = 0;");
							tl(5, "while(champValeur != null) {");
							tl(6, "if(k > 0)");
							tl(7, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(BigDecimal.valueOf((Double)champValeur).toString());");
							tl(6, "champValeur = champValeurs.iterator().next();");
							tl(5, "}");
							tl(5, "reponseServeur.write(VAL_guillmetsFin);");
							tl(5, "j++;");
							tl(5, "return j;");
						}
						else if(VAL_nomCanoniqueDouble.equals(entiteNomCanoniqueGenerique)) {
							tl(5, "if(j > 0)");
							tl(6, "reponseServeur.write(\", \");");
							tl(5, "reponseServeur.write(VAL_citation);");
							tl(5, "reponseServeur.write(\"", entiteVar, "\");");
							tl(5, "reponseServeur.write(VAL_citationDeuxPointsEspaceGuillmets);");
							tl(5, "int k = 0;");
							tl(5, "while(champValeur != null) {");
							tl(6, "if(k > 0)");
							tl(7, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(((Double)champValeur).toString());");
							tl(6, "champValeur = champValeurs.iterator().next();");
							tl(5, "}");
							tl(5, "reponseServeur.write(VAL_guillmetsFin);");
							tl(5, "j++;");
							tl(5, "return j;");
						}
						else if(VAL_nomCanoniqueFloat.equals(entiteNomCanoniqueGenerique)) {
							tl(5, "if(j > 0)");
							tl(6, "reponseServeur.write(\", \");");
							tl(5, "reponseServeur.write(VAL_citation);");
							tl(5, "reponseServeur.write(\"", entiteVar, "\");");
							tl(5, "reponseServeur.write(VAL_citationDeuxPointsEspaceGuillmets);");
							tl(5, "int k = 0;");
							tl(5, "while(champValeur != null) {");
							tl(6, "if(k > 0)");
							tl(7, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(((Float)champValeur).toString());");
							tl(6, "champValeur = champValeurs.iterator().next();");
							tl(5, "}");
							tl(5, "reponseServeur.write(VAL_guillmetsFin);");
							tl(5, "j++;");
							tl(5, "return j;");
						}
						else if(VAL_nomCanoniqueInteger.equals(entiteNomCanoniqueGenerique)) {
							tl(5, "if(j > 0)");
							tl(6, "reponseServeur.write(\", \");");
							tl(5, "reponseServeur.write(VAL_citation);");
							tl(5, "reponseServeur.write(\"", entiteVar, "\");");
							tl(5, "reponseServeur.write(VAL_citationDeuxPointsEspaceGuillmets);");
							tl(5, "int k = 0;");
							tl(5, "while(champValeur != null) {");
							tl(6, "if(k > 0)");
							tl(7, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(((Integer)champValeur).toString());");
							tl(6, "champValeur = champValeurs.iterator().next();");
							tl(5, "}");
							tl(5, "reponseServeur.write(VAL_guillmetsFin);");
							tl(5, "j++;");
							tl(5, "return j;");
						}
						else {
							tl(5, "if(j > 0)");
							tl(6, "reponseServeur.write(\", \");");
							tl(5, "reponseServeur.write(VAL_citation);");
							tl(5, "reponseServeur.write(\"", entiteVar, "\");");
							tl(5, "reponseServeur.write(VAL_citationDeuxPointsEspaceGuillmets);");
							tl(5, "int k = 0;");
							tl(5, "while(champValeur != null) {");
							tl(6, "if(k > 0)");
							tl(7, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(\", \");");
							tl(6, "reponseServeur.write(VAL_citation);");
							tl(6, "reponseServeur.write(((String)champValeur));");
							tl(6, "reponseServeur.write(VAL_citation);");
							tl(6, "champValeur = champValeurs.iterator().next();");
							tl(5, "}");
							tl(5, "reponseServeur.write(VAL_guillmetsFin);");
							tl(5, "j++;");
							tl(5, "return j;");
						}
					}
					else {
						tl(5, "if(j > 0)");
						tl(6, "reponseServeur.write(\", \");");
						tl(5, "reponseServeur.write(\"\\\"", entiteVar, "\\\": \\\"\");");
						tl(5, "reponseServeur.write(Json.encode((String)champValeurs.iterator().next()));");
						tl(5, "reponseServeur.write(\"\\\"\\n\");");
						tl(5, "j++;");
						tl(5, "return j;");
					}
				}
				tl(4, "}");
			}
	
			////////////////////////
			// codeApiGenererPost //
			////////////////////////
			o = wApiGenererPost;
	
			Integer tBase = 2;
	//		if(classeRolesTrouve) {
	//			tBase = 6;
	//		}
	//		else {
	//			tBase = 4;
	//		}
			if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
				tl(tBase + 2, "case \"", entiteVar, "\":");
				tl(tBase + 3, "postSql.append(SiteContexte.SQL_setP);");
				tl(tBase + 3, "postSqlParams.addAll(Arrays.asList(\"", entiteVar, "\", jsonObject.get", entiteNomSimpleVertxJson, "(entiteVar), ", classeVarClePrimaire, "));");
				tl(tBase + 3, "break;");
			}	
			if(classeSauvegarde && BooleanUtils.isTrue(entiteAttribuer)) {
				tl(tBase + 2, "case \"", entiteVar, "\":");
				tl(tBase + 3, "postSql.append(SiteContexte.SQL_addA);");
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
			if(classeRolesTrouve) {
				tBase = 6;
			}
			else {
				tBase = 4;
			}
			if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
				tl(tBase + 6, "case \"", entiteVar, "\":");
				tl(tBase + 7, "putSql.append(SiteContexte.SQL_setP);");
				tl(tBase + 7, "putSqlParams.addAll(Arrays.asList(\"", entiteVar, "\", requeteJson.get", entiteNomSimpleVertxJson, "(entiteVar), putPk));");
				tl(tBase + 7, "break;");
			}	
	
			////////////////////////
			// codeApiGenererPatch //
			////////////////////////
			o = wApiGenererPatch;
	
			tBase = 2;
	//		if(classeRolesTrouve) {
	//			tBase = 6;
	//		}
	//		else {
	//			tBase = 4;
	//		}
			if(classeSauvegarde && BooleanUtils.isTrue(entiteDefinir)) {
				if(BooleanUtils.isTrue(entiteAttribuer)) {
					if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
		
						if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
							tl(tBase + 2, "case \"add", entiteVarCapitalise, "\":");
							tl(tBase + 3, "patchSql.append(SiteContexte.SQL_addA);");
							tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(");
							tl(tBase + 5, "ENTITE_VAR_", entiteVar);
							tl(tBase + 5, ", ", classeVarClePrimaire);
							tl(tBase + 5, ", ENTITE_VAR_", entiteVar, "_ATTRIBUER_", entiteAttribuerNomSimple, "_", entiteAttribuerVar, "");
							tl(tBase + 5, ", requeteJson.get", entiteListeNomSimpleVertxJson, "(methodeNom)");
							tl(tBase + 5, "));");
	
							tl(tBase + 2, "case \"addAll", entiteVarCapitalise, "\":");
							tl(tBase + 3, entiteNomSimpleVertxJson, " addAll", entiteVarCapitalise, "Valeurs = requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom);");
							tl(tBase + 3, "for(Integer i = 0; i <  addAll", entiteVarCapitalise, "Valeurs.size(); i++) {");
							tl(tBase + 4, "patchSql.append(SiteContexte.SQL_addA);");
							tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(");
							tl(tBase + 5, "ENTITE_VAR_", entiteVar);
							tl(tBase + 5, ", ", classeVarClePrimaire);
							tl(tBase + 5, ", ENTITE_VAR_", entiteVar, "_ATTRIBUER_", entiteAttribuerNomSimple, "_", entiteAttribuerVar, "");
							tl(tBase + 5, ", addAll", entiteVarCapitalise, "Valeurs.get", entiteListeNomSimpleVertxJson, "(i)");
							tl(tBase + 5, "));");
							tl(tBase + 3, "}");
		
							tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
							tl(tBase + 3, entiteNomSimpleVertxJson, " set", entiteVarCapitalise, "Valeurs = requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom);");
							tl(tBase + 3, "patchSql.append(SiteContexte.SQL_clearA1);");
							tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(");
							tl(tBase + 6, "ENTITE_VAR_", entiteVar);
							tl(tBase + 6, ", ", classeVarClePrimaire);
							tl(tBase + 6, ", ENTITE_VAR_", entiteVar, "_ATTRIBUER_", entiteAttribuerNomSimple, "_", entiteAttribuerVar, "");
							tl(tBase + 6, ", requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom)");
							tl(tBase + 6, "));");
	
							tl(tBase + 3, "for(Integer i = 0; i <  set", entiteVarCapitalise, "Valeurs.size(); i++) {");
							tl(tBase + 4, "patchSql.append(SiteContexte.SQL_addA);");
							tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(");
							tl(tBase + 6, "ENTITE_VAR_", entiteVar);
							tl(tBase + 6, ", ", classeVarClePrimaire);
							tl(tBase + 6, ", ENTITE_VAR_", entiteVar, "_ATTRIBUER_", entiteAttribuerNomSimple, "_", entiteAttribuerVar, "");
							tl(tBase + 6, ", set", entiteVarCapitalise, "Valeurs.get", entiteListeNomSimpleVertxJson, "(i)");
							tl(tBase + 6, "));");
							tl(tBase + 3, "}");
						}
						else {
							tl(tBase + 2, "case \"add", entiteVarCapitalise, "\":");
							tl(tBase + 3, "patchSql.append(SiteContexte.SQL_addA);");
							tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(");
							tl(tBase + 5, "ENTITE_VAR_", entiteVar);
							tl(tBase + 5, ", ", classeVarClePrimaire);
							tl(tBase + 5, ", ENTITE_VAR_", entiteVar, "_ATTRIBUER_", entiteAttribuerNomSimple, "_", entiteAttribuerVar, "");
							tl(tBase + 5, ", requeteJson.get", entiteListeNomSimpleVertxJson, "(methodeNom)");
							tl(tBase + 5, "));");
	
							tl(tBase + 2, "case \"addAll", entiteVarCapitalise, "\":");
							tl(tBase + 3, entiteNomSimpleVertxJson, " addAll", entiteVarCapitalise, "Valeurs = requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom);");
							tl(tBase + 3, "for(Integer i = 0; i <  addAll", entiteVarCapitalise, "Valeurs.size(); i++) {");
							tl(tBase + 4, "patchSql.append(SiteContexte.SQL_setA2);");
							tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(");
							tl(tBase + 6, "ENTITE_VAR_", entiteVar, "_ATTRIBUER_", entiteAttribuerNomSimple, "_", entiteAttribuerVar, "");
							tl(tBase + 6, ", addAll", entiteVarCapitalise, "Valeurs.get", entiteListeNomSimpleVertxJson, "(i)");
							tl(tBase + 6, ", ENTITE_VAR_", entiteVar);
							tl(tBase + 6, ", ", classeVarClePrimaire);
							tl(tBase + 6, "));");
							tl(tBase + 3, "}");
		
							tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
							tl(tBase + 3, entiteNomSimpleVertxJson, " set", entiteVarCapitalise, "Valeurs = requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom);");
							tl(tBase + 3, "patchSql.append(SiteContexte.SQL_clearA2);");
							tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(");
							tl(tBase + 5, "ENTITE_VAR_", entiteVar, "_ATTRIBUER_", entiteAttribuerNomSimple, "_", entiteAttribuerVar, "");
							tl(tBase + 5, ", requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom)");
							tl(tBase + 5, ", ENTITE_VAR_", entiteVar);
							tl(tBase + 5, ", ", classeVarClePrimaire);
							tl(tBase + 5, "));");
	
							tl(tBase + 3, "for(Integer i = 0; i <  set", entiteVarCapitalise, "Valeurs.size(); i++) {");
							tl(tBase + 4, "patchSql.append(SiteContexte.SQL_setA2);");
							tl(tBase + 4, "patchSqlParams.addAll(Arrays.asList(");
							tl(tBase + 6, "ENTITE_VAR_", entiteVar, "_ATTRIBUER_", entiteAttribuerNomSimple, "_", entiteAttribuerVar, "");
							tl(tBase + 6, ", set", entiteVarCapitalise, "Valeurs.get", entiteListeNomSimpleVertxJson, "(i)");
							tl(tBase + 6, ", ENTITE_VAR_", entiteVar);
							tl(tBase + 6, ", ", classeVarClePrimaire);
							tl(tBase + 6, "));");
							tl(tBase + 3, "}");
						}
					}
					else {
		
						tl(tBase + 6, "case \"set", entiteVarCapitalise, "\":");
						if(StringUtils.compare(entiteVar, entiteAttribuerVar) <= 0) {
							tl(tBase + 3, "patchSql.append(SiteContexte.SQL_setA1);");
							tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(");
							tl(tBase + 5, "ENTITE_VAR_", entiteVar);
							tl(tBase + 5, ", ", classeVarClePrimaire);
							tl(tBase + 5, ", ENTITE_VAR_", entiteVar, "_ATTRIBUER_", entiteAttribuerNomSimple, "_", entiteAttribuerVar, "");
							tl(tBase + 5, ", requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom)");
						}
						else {
							tl(tBase + 3, "patchSql.append(SiteContexte.SQL_setA2);");
							tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(");
							tl(tBase + 5, "ENTITE_VAR_", entiteVar, "_ATTRIBUER_", entiteAttribuerNomSimple, "_", entiteAttribuerVar, "");
							tl(tBase + 5, ", requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom)");
							tl(tBase + 5, ", ENTITE_VAR_", entiteVar);
							tl(tBase + 5, ", ", classeVarClePrimaire);
						}
						tl(tBase + 5, "));");
					}
		
					tl(tBase + 7, "break;");
				}
				else if(BooleanUtils.isTrue(entiteDefinir)) {
					if(StringUtils.equals(entiteNomCanonique, List.class.getCanonicalName()) || StringUtils.equals(entiteNomCanonique, ArrayList.class.getCanonicalName())) {
		
						tl(tBase + 6, "case \"add", entiteVarCapitalise, "\":");
						tl(tBase + 3, "patchSql.append(SiteContexte.SQL_addA);");
						tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(");
						tl(tBase + 5, "ENTITE_VAR_", entiteVar);
						tl(tBase + 5, ", requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom)");
						tl(tBase + 5, ", ", classeVarClePrimaire);
						tl(tBase + 5, "));");
		
						tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
						tl(tBase + 3, "patchSql.append(SiteContexte.SQL_setP);");
						tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(\"", entiteVar, "\", requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom), ", classeVarClePrimaire, "));");
					}
					else {
		
						tl(tBase + 2, "case \"set", entiteVarCapitalise, "\":");
						tl(tBase + 3, "patchSql.append(SiteContexte.SQL_setP);");
						tl(tBase + 3, "patchSqlParams.addAll(Arrays.asList(\"", entiteVar, "\", requeteJson.get", entiteNomSimpleVertxJson, "(methodeNom), ", classeVarClePrimaire, "));");
					}
		
					tl(tBase + 3, "break;");
				}
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
	 * var.enUS: genCodeClassEnd
	 * param1.var.enUS: languageName
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
	 * r: wPeupler
	 * r.enUS: wPopulate
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
	 * 
	 * r: sauvegarder
	 * r.enUS: save
	 * r: peupler
	 * r.enUS: populate
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
				wInitLoin.s("public void initLoinPourClasse(RequeteSite requeteSite)");
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
				wInitLoin.tl(2, "initLoin", classeNomSimple, "(requeteSite);");
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
			tl(1, "public void requeteSitePourClasse(RequeteSite requeteSite) {");
			tl(2, "requeteSite", classeNomSimple, "(requeteSite);");
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
				tl(2, "RequeteSite requeteSite = new RequeteSite();");
				tl(2, "requeteSite.initLoinRequeteSite();");
				tl(2, "SiteContexte siteContexte = new SiteContexte();");
				tl(2, "siteContexte.initLoinSiteContexte();");
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
		wExiste.flushClose();
		wSauvegardes.flushClose();
		wDefinir.flushClose();
		wApiEntites.flushClose();
		wApiGet.flushClose();
		wApiGenererGet.flushClose();
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
			s(wPeupler.toString());
			if(BooleanUtils.isTrue(classeEtendBase)) {
				tl(0);
				tl(2, "super.peupler", classeNomSimpleSuperGenerique, "(solrDocument);");
			}

			tl(1, "}");
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
//			l("public void sauvegarderPourClasse(RequeteSite requeteSite) throws Exception {");
//			tl(2, QueryRunner.class.getCanonicalName(), " coureur = new ", QueryRunner.class.getCanonicalName(), "(requeteSite.SiteContexte.sourceDonnees);");
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
//			tl(1, "public void sauvegarder", classeNomSimple, "(RequeteSite requeteSite, String sqlInsertP, String sqlInsertA, String sqlDeleteP, String sqlDeleteA, ", ArrayListHandler.class.getCanonicalName(), " gestionnaireListe, ", QueryRunner.class.getCanonicalName(), " coureur) throws Exception {");
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
