package org.computate.frFR.java;       

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**   
 * NomCanonique.enUS: org.computate.enUS.java.WriteAllClasses
 * 
 * enUS: For retrieving a Java class from Solr and writing the Java class to a file for each language. 
 * frFR: Pour récupérer une classe Java de Solr et écrire la classe Java dans un fichier pour chaque langue. 
 * InitLoin: false
 */   
public class EcrireToutesClasses extends EcrirePageClasse {        

	/**
	 * Var.enUS: writeGenClasses
	 * Param1.var.enUS: classAbsolutePath
	 * Param2.var.enUS: languageName
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: partNumero
	 * r.enUS: partNumber
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: langueNom
	 * r.enUS: languageName
	 * r: ecrireClasseGen
	 * r.enUS: writeGenClass
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 * r: ecrireGenClasses
	 * r.enUS: writeGenClasses
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 */   
	public void ecrireGenClasses(String classeCheminAbsolu, String langueNom) throws Exception { 

		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addFilterQuery("classeCheminAbsolu_indexed_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addFilterQuery("classeEtendGen_indexed_boolean:true");
		rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);

		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		ecrireGenClasses(reponseRecherche, langueNom);
	}

	/** 
	 * Var.enUS: writeGenClasses
	 * Param1.var.enUS: searchResponse
	 * Param2.var.enUS: languageName
	 * r: VAL_entiteCommentaireLigne1Part1
	 * r.enUS: VAL_entityCommentLine1Part1
	 * r: VAL_entiteCommentaireLigne1Part2
	 * r.enUS: VAL_entityCommentLine1Part2
	 * r: langueIndexe
	 * r.enUS: languageIndexed
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: reponseRecherche
	 * r.enUS: searchResponse
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: partNumero
	 * r.enUS: partNumber
	 * r: classeParametreTypeNoms
	 * r.enUS: classParameterTypeNames
	 * r: classeParametreTypeNom
	 * r.enUS: classParameterTypeName
	 * r: methodeParametreTypeNoms
	 * r.enUS: methodParameterTypeNames
	 * r: methodeParametreTypeNom
	 * r.enUS: methodParameterTypeName
	 * r: langueNom
	 * r.enUS: languageName
	 * r: classeSuperParametreTypeNoms
	 * r.enUS: classSuperParameterTypeNames
	 * r: classeParametreTypeNoms
	 * r.enUS: classTypeParameterNames
	 * r: classeImportations
	 * r.enUS: classImports
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: classeNomCanoniqueSuperGeneriqueLangue
	 * r.enUS: classSuperCanonicalNameGenericLanguage
	 * r: classeNomCanoniqueSuperGenerique
	 * r.enUS: classSuperCanonicalNameGeneric
	 * r: classeNomCanoniqueSuper
	 * r.enUS: classSuperCanonicalName
	 * r: classeNomCanoniqueGenLangue
	 * r.enUS: classCanonicalNameGenLanguage
	 * r: classeNomCanoniqueGen
	 * r.enUS: classCanonicalNameGen
	 * r: classeNomCanoniqueSuperDoc
	 * r.enUS: classSuperCanonicalNameDoc
	 * r: classeNomCanoniqueLangue
	 * r.enUS: classCanonicalNameLanguage
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: classeNomSimpleSuperGeneriqueLangue
	 * r.enUS: classSuperSimpleNameGenericLanguage
	 * r: classeNomSimpleSuperGenerique
	 * r.enUS: classSuperSimpleNameGeneric
	 * r: classeNomSimpleSuper
	 * r.enUS: classSuperSimpleName
	 * r: classeNomSimpleGenLangue
	 * r.enUS: classSimpleNameGenLanguage
	 * r: classeNomSimpleGen
	 * r.enUS: classSimpleNameGen
	 * r: classeNomSimpleSuperDoc
	 * r.enUS: classSuperSimpleNameDoc
	 * r: classeNomSimpleGenLangue
	 * r.enUS: classSimpleNameGenLanguage
	 * r: classeNomSimpleLangue
	 * r.enUS: classSimpleNameLanguage
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: classeEtendGen
	 * r.enUS: classExtendsGen
	 * r: classeCheminRepertoireGenLangue
	 * r.enUS: classDirPathGenLanguage
	 * r: classeCheminRepertoireGen
	 * r.enUS: classDirPathGen
	 * r: classeCheminRepertoireLangue
	 * r.enUS: classDirPathLanguage
	 * r: classeCheminRepertoire
	 * r.enUS: classDirPath
	 * r: classeChemin
	 * r.enUS: classPath
	 * r: classeRepertoire
	 * r.enUS: classDir
	 * r: classeFichier
	 * r.enUS: classFile
	 * r: classeImportation
	 * r.enUS: classImport
	 * r: classePartsSuperLangue
	 * r.enUS: classSuperPartsLanguage
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: classeSuperParametreTypeNom
	 * r.enUS: classSuperTypeParameterName
	 * r: partEstChamp
	 * r: partEstEntite
	 * r.enUS: partIsEntity
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: classeVarCleUnique
	 * r.enUS: classVarUniqueKey
	 * r: classePageUri
	 * r.enUS: classPageUri
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * r: classeBaseEtendGen
	 * r.enUS: classBaseExtendsGen
	 * r: classeEtendBase
	 * r.enUS: classExtendsBase
	 * r: classeEstBase
	 * r.enUS: classIsBase
	 * r: classeInitLoin
	 * r.enUS: classInitDeep
	 * r: classeSauvegarde
	 * r.enUS: classSaved
	 * r: classeIndexe
	 * r.enUS: classIndexed
	 * r: classeModele
	 * r.enUS: classModel
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: classeApi
	 * r.enUS: classApi
	 * r: classePage
	 * r.enUS: classPage
	 * r: classeRolesTrouves
	 * r.enUS: classRolesFound
	 * r: classeRoles
	 * r.enUS: classRoles
	 * r: auteurGenClasse
	 * r.enUS: writerGenClass
	 * r: auteurApiEnsembleInfo
	 * r.enUS: writerApiPackageInfo
	 * r: auteurGenApiServiceImpl
	 * r.enUS: writerGenApiServiceImpl
	 * r: auteurGenApiService
	 * r.enUS: writerGenApiService
	 * r: auteurApiServiceImpl
	 * r.enUS: writerApiServiceImpl
	 * r: auteurApiGenClasse
	 * r.enUS: writerApiGenClass
	 * r: auteurPageGenClasse
	 * r.enUS: writerPageGenClass
	 * r: auteurPageClasse
	 * r.enUS: writerPageClass
	 * r: auteurPageCss
	 * r.enUS: writerPageCss
	 * r: auteurPageJs
	 * r.enUS: writerPageJs
	 * r: genCodeInitLoin
	 * r.enUS: genCodeInitDeep
	 * r: genCodeRequeteSite
	 * r.enUS: genCodeSiteRequest
	 * r: genCodeIndexer
	 * r.enUS: genCodeIndex
	 * r: genCodeObtenir
	 * r.enUS: genCodeObtain
	 * r: genCodeAttribuer
	 * r.enUS: genCodeAttribute
	 * r: genCodePeupler
	 * r.enUS: genCodePopulate
	 * r: genCodeExiste
	 * r.enUS: genCodeExists
	 * r: genCodeSauvegardes
	 * r.enUS: genCodeSaves
	 * r: genCodeSauvegarder
	 * r.enUS: genCodeSave
	 * r: genCodeClasseDebut
	 * r.enUS: genCodeClassBegin
	 * r: genCodeEntite
	 * r.enUS: genCodeEntity
	 * r: partEstConstructeur
	 * r.enUS: partIsConstructor
	 * r: genCodeClasseFin
	 * r.enUS: genCodeClassEnd
	 * r: apiCodeClasseDebut
	 * r.enUS: apiCodeClassBegin
	 * r: apiCodeClasseDebut
	 * r.enUS: apiCodeClassBegin
	 * r: apiCodeClasseFin
	 * r.enUS: apiCodeClassEnd
	 * r: entiteIndice
	 * r.enUS: entityIndex
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: ecrireGenApiServiceImpl
	 * r.enUS: writeGenApiServiceImpl
	 * r: ecrireGenApiService
	 * r.enUS: writeGenApiService
	 * r: ecrireApiEnsembleInfo
	 * r.enUS: writeApiPackageInfo
	 * r: ecrireApiServiceImpl
	 * r.enUS: writeApiServiceImpl
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: genCodeConstructeur
	 * r.enUS: genCodeConstructor
	 * r: ecrireApi
	 * r.enUS: writeApi
	 * 
	 * r: EnsembleInfo
	 * r.enUS: PackageInfo
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: classe
	 * r.enUS: class
	 * 
	 * r: Ecrire:
	 * r.enUS: Write:
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 */  
	public void ecrireGenClasses(QueryResponse reponseRecherche, String langueNom) throws Exception { 
		SolrDocumentList listeRecherche = reponseRecherche.getResults();

		if(listeRecherche.size() > 0 && (langueIndexe || !StringUtils.equals(langueNom, this.langueNom))) {    
			for(int i = 0; i < listeRecherche.size(); i++) {
				doc = listeRecherche.get(i); 
				Integer partNumero = (Integer)doc.get("partNumero_stored_int");
				if(partNumero.equals(1)) {
					classeDoc = doc;

					classeCheminRepertoireGen = (String)doc.get("classeCheminRepertoireGen_" + langueNom + "_stored_string");
					classeCheminGen = (String)doc.get("classeCheminGen_" + langueNom + "_stored_string"); 
					classeCheminApiEnsembleInfo = (String)doc.get("classeCheminApiEnsembleInfo_" + langueNom + "_stored_string"); 
					classeCheminGenApiServiceImpl = (String)doc.get("classeCheminGenApiServiceImpl_" + langueNom + "_stored_string"); 
					classeCheminApiServiceImpl = (String)doc.get("classeCheminApiServiceImpl_" + langueNom + "_stored_string"); 
					classeCheminGenApiService = (String)doc.get("classeCheminGenApiService_" + langueNom + "_stored_string"); 
					classeCheminPageGen = (String)doc.get("classeCheminPageGen_" + langueNom + "_stored_string"); 
					classeCheminPage = (String)doc.get("classeCheminPage_" + langueNom + "_stored_string"); 
					classeCheminPageCss = (String)doc.get("classeCheminPageCss_" + langueNom + "_stored_string"); 
					classeCheminPageJs = (String)doc.get("classeCheminPageJs_" + langueNom + "_stored_string"); 

					classeRepertoireGen = new File(classeCheminRepertoireGen);
					classeRepertoireGen.mkdirs();

					classeFichierGen = new File(classeCheminGen);
					if(classeCheminApiEnsembleInfo != null)
						classeFichierApiEnsembleInfo = new File(classeCheminApiEnsembleInfo);
					if(classeCheminGenApiServiceImpl != null)
						classeFichierGenApiServiceImpl = new File(classeCheminGenApiServiceImpl);
					if(classeCheminApiServiceImpl != null)
						classeFichierApiServiceImpl = new File(classeCheminApiServiceImpl);
					if(classeCheminGenApiService != null)
						classeFichierGenApiService = new File(classeCheminGenApiService);
					if(classeCheminPageGen != null)
						classeFichierPageGen = new File(classeCheminPageGen);
					if(classeCheminPage != null)
						classeFichierPage = new File(classeCheminPage);
					if(classeCheminPageCss != null)
						classeFichierPageCss = new File(classeCheminPageCss);
					if(classeCheminPageJs != null)
						classeFichierPageJs = new File(classeCheminPageJs);

					o = ToutEcrivain.create(classeFichierGen);
					classeNomSimple = (String)doc.get("classeNomSimple_" + langueNom + "_stored_string");
					classeNomSimpleGen = (String)doc.get("classeNomSimpleGen_" + langueNom + "_stored_string");
					classeNomCanonique = (String)doc.get("classeNomCanonique_" + langueNom + "_stored_string");
					classeNomCanoniqueSuper = (String)doc.get("classeNomCanoniqueSuper_" + langueNom + "_stored_string");
					classeNomSimpleSuper = (String)doc.get("classeNomSimpleSuper_" + langueNom + "_stored_string");
					classeNomCanoniqueSuper = (String)doc.get("classeNomCanoniqueSuper_" + langueNom + "_stored_string");
					classeNomSimpleSuperGenerique = (String)doc.get("classeNomSimpleSuperGenerique_" + langueNom + "_stored_string");
					classeNomCanoniqueSuperGenerique = (String)doc.get("classeNomCanoniqueSuperGenerique_" + langueNom + "_stored_string");
					classeNomEnsemble = (String)doc.get("classeNomEnsemble_" + langueNom + "_stored_string");
					classeNomSimpleApiServiceImpl = (String)doc.get("classeNomSimpleApiServiceImpl_" + langueNom + "_stored_string");
					classeNomSimpleGenApiServiceImpl = (String)doc.get("classeNomSimpleGenApiServiceImpl_" + langueNom + "_stored_string");
					classeNomSimpleGenApiService = (String)doc.get("classeNomSimpleGenApiService_" + langueNom + "_stored_string");
					classeVarClePrimaire = (String)doc.get("classeVarClePrimaire_" + langueNom + "_stored_string");
					classeVarCleUnique = (String)doc.get("classeVarCleUnique_" + langueNom + "_stored_string");
					classePageUri = (String)doc.get("classePageUri_" + langueNom + "_stored_string");
					classeApiUri = (String)doc.get("classeApiUri_" + langueNom + "_stored_string");
					classeCommentaire = (String)doc.get("classeCommentaire_" + langueNom + "_stored_string");
					classeImportationsGen = (List<String>)doc.get("classeImportationsGen_" + langueNom + "_stored_strings");
					if(classeImportationsGen == null)
						classeImportationsGen = new ArrayList<String>();
					classeImportationsGenApi = (List<String>)doc.get("classeImportationsGenApi_" + langueNom + "_stored_strings");
					if(classeImportationsGenApi == null)
						classeImportationsGenApi = new ArrayList<String>();
					classeImportationsGenPage = (List<String>)doc.get("classeImportationsGenPage_" + langueNom + "_stored_strings");
					if(classeImportationsGenPage == null)
						classeImportationsGenPage = new ArrayList<String>();
					classeInitLoinExceptions = (List<String>)doc.get("classeInitLoinExceptions_stored_strings");
					if(classeInitLoinExceptions == null)
						classeInitLoinExceptions = new ArrayList<String>();
					classeParametreTypeNoms = (List<String>)doc.get("classeParametreTypeNoms_stored_strings");
					classeSuperParametreTypeNoms = (List<String>)doc.get("classeSuperParametreTypeNoms_stored_strings");
					classeEtendGen = (Boolean)doc.get("classeEtendGen_stored_boolean");
					classeBaseEtendGen = (Boolean)doc.get("classeBaseEtendGen_stored_boolean");
					classeEtendBase = (Boolean)doc.get("classeEtendBase_stored_boolean");
					classeEstBase = (Boolean)doc.get("classeEstBase_stored_boolean");
					classeInitLoin = (Boolean)doc.get("classeInitLoin_stored_boolean");
					classeSauvegarde = BooleanUtils.isTrue((Boolean)doc.get("classeSauvegarde_stored_boolean"));
					classeIndexe = BooleanUtils.isTrue((Boolean)doc.get("classeIndexe_stored_boolean"));
					classeModele = BooleanUtils.isTrue((Boolean)doc.get("classeModele_stored_boolean"));
					classeApi = BooleanUtils.isTrue((Boolean)doc.get("classeApi_stored_boolean"));
					classePage = BooleanUtils.isTrue((Boolean)doc.get("classePage_stored_boolean"));
					classeRolesTrouves = BooleanUtils.isTrue((Boolean)doc.get("classeRolesTrouves_stored_boolean"));
					classeRoles = (List<String>)doc.get("classeRoles_" + langueNom + "_stored_strings");
					classeApiMethodes = (List<String>)doc.get("classeApiMethodes_stored_strings");
					if(classeApiMethodes == null)
						classeApiMethodes = new ArrayList<>();
					entiteIndice = 0;   

					auteurGenClasse = ToutEcrivain.create(classeFichierGen);
					if(classeApi && ecrireApi) {
//						if(classeFichierApiEnsembleInfo != null && !classeFichierApiEnsembleInfo.exists())
//							auteurApiEnsembleInfo = ToutEcrivain.create(classeFichierApiEnsembleInfo);
						if(classeFichierGenApiServiceImpl != null)
							auteurGenApiServiceImpl = ToutEcrivain.create(classeFichierGenApiServiceImpl);
						if(classeFichierApiServiceImpl != null && !classeFichierApiServiceImpl.exists())
//						if(classeFichierApiServiceImpl != null)
							auteurApiServiceImpl = ToutEcrivain.create(classeFichierApiServiceImpl);
						if(classeFichierGenApiService != null)
							auteurGenApiService = ToutEcrivain.create(classeFichierGenApiService);
					}
//					auteurPageClasse = new PrintWriter(classeFichierPage);
					if(classePage) {
						if(classeFichierPage != null && !classeFichierPage.exists())
							auteurPageClasse = ToutEcrivain.create(classeFichierPage);
						if(classeFichierPageGen != null)
							auteurPageGenClasse = ToutEcrivain.create(classeFichierPageGen);
						if(classeFichierPageCss != null)
							auteurPageCss = ToutEcrivain.create(classeFichierPageCss);
						if(classeFichierPageJs != null)
							auteurPageJs = ToutEcrivain.create(classeFichierPageJs);
					}

					genCodeInit();
					o = auteurGenClasse;

					genCodeInitLoin(langueNom);
					genCodeRequeteSite(langueNom);
					genCodeIndexer(langueNom);
					genCodeObtenir(langueNom);
					genCodeAttribuer(langueNom);
					genCodePut(langueNom);
					genCodePeupler(langueNom);
					genCodeExiste(langueNom); 
					genCodeSauvegardes(langueNom);
					genCodeClasseDebut(langueNom);
					if(classeApi)
						apiCodeClasseDebut(langueNom);
					if(classePage)
						pageCodeClasseDebut(langueNom);
				} 
				else {
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stored_boolean");
	
					if(BooleanUtils.isTrue(partEstConstructeur)) {
						genCodeConstructeur(langueNom);
					}
					else if(BooleanUtils.isTrue(partEstEntite)) {
						genCodeEntite(langueNom);
					}
				}
			}
			if(o != null) {
				if(listeRecherche.size() > 0 && !StringUtils.equals(classeCheminAbsolu, classeCheminGen)) {
					genCodeClasseFin(langueNom);
					if(classeApi) {
//						ecrireApiEnsembleInfo(langueNom);
						ecrireGenApiService(langueNom);
						ecrireGenApiServiceImpl(langueNom);
						ecrireApiServiceImpl(langueNom);
					}
				}
			}
		} 
	}  
}
