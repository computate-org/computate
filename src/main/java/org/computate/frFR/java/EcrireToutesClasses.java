package org.computate.frFR.java;       

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
	 * Param2.var.enUS: classLanguageName
	 * Param3.var.enUS: languageName
	 * r: classeLangueNom
	 * r.enUS: classLanguageName
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
	 * r: classeChemin
	 * r.enUS: classPath
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 */   
	public void ecrireGenClasses(String classeCheminAbsolu, String classeLangueNom, String langueNom) throws Exception { 

		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addFilterQuery("classeChemin_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addFilterQuery("classeEtendGen_indexed_boolean:true");
		rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);

		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		ecrireGenClasses(reponseRecherche, classeLangueNom, langueNom);
	}

	/** 
	 * Var.enUS: writeGenClasses
	 * Param1.var.enUS: searchResponse
	 * Param2.var.enUS: classLanguageName
	 * Param3.var.enUS: languageName
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
	 * r: entiteClassesSuperEtMoiSansGen
	 * r.enUS: entitySuperClassesAndMeWithoutGen
	 * r: partEstChamp
	 * r.enUS: partIsField
	 * r: partEstEntite
	 * r.enUS: partIsEntity
	 * r: partEstMethode
	 * r.enUS: partIsMethode
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: classeVarId
	 * r.enUS: classVarId
	 * r: classeVarModifie
	 * r.enUS: classVarModified
	 * r: classeVarCree
	 * r.enUS: classVarCreated
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
	 * r: classeContientRequeteSite
	 * r.enUS: classContainsSiteRequest
	 * r: classeSauvegarde
	 * r.enUS: classSaved
	 * r: classeIndexe
	 * r.enUS: classIndexed
	 * r: classeImage
	 * r.enUS: classImage
	 * r: classeModele
	 * r.enUS: classModel
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: classeApi
	 * r.enUS: classApi
	 * r: classePage
	 * r.enUS: classPage
	 * 
	 * r: classeRolesTrouves
	 * r.enUS: classRolesFound
	 * r: classeRoles
	 * r.enUS: classRoles
	 * r: classePageNomSimple
	 * r.enUS: classPageSimpleName
	 * r: classePageSuperNomSimple
	 * r.enUS: classPageSuperSimpleName
	 * r: classeGenPageNomSimple
	 * r.enUS: classGenPageSimpleName
	 * 
	 * r: classeFiltresTrouves
	 * r.enUS: classFiltersFound
	 * r: classeFiltres
	 * r.enUS: classFilters
	 * 
	 * r: classeEcrireMethode
	 * r.enUS: classWriteMethod
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
	 * r: auteurGenPageClasse
	 * r.enUS: writerGenPageClass
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
	 * r: genCodeMethode
	 * r.enUS: genCodeMethod
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
	 * r: pageCodeClasse
	 * r.enUS: pageCodeClass
	 * 
	 * r: contexteNomAdjectifSingulier
	 * r.enUS: contextNameAdjectiveSingular
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
	 * r: contexteNomSingulier
	 * r.enUS: contextNameSingular
	 * r: contexteNomPluriel
	 * r.enUS: contextNamePlural
	 * r: contexteNomActuel
	 * r.enUS: contextActualName
	 * r: contexteTousNom
	 * r.enUS: contextAllName
	 * r: contexteLesNoms
	 * r.enUS: contextTheName
	 * r: contexteTitre
	 * r.enUS: contextTitle
	 * r: contexteH1
	 * r.enUS: contextH1
	 * r: contexteH2
	 * r.enUS: contextH2
	 * r: contexteH3
	 * r.enUS: contextH3
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
	 * r: contexteImageWidth
	 * r.enUS: contextImageLargeur
	 * r: contexteImageHeight
	 * r.enUS: contextImageHauteur
	 * r: contexteVideoId
	 * r.enUS: contextVideoId
	 * r: contexteDescription
	 * r.enUS: contextDescription
	 * r: contexteImageLargeur
	 * r.enUS: contextImageWidth
	 * r: contexteImageHauteur
	 * r.enUS: contextImageHeight
	 * r: contexteRechercherTousNomPar
	 * r.enUS: contextSearchAllNameBy
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
	 * r: Couleur
	 * r.enUS: Color
	 * r: IconeGroupe
	 * r.enUS: IconGroup
	 * r: IconeNom
	 * r.enUS: IconName
	 * r: classeSuperEcrireMethodes
	 * r.enUS: classSuperWriteMethods
	 * r: classeEcrireMethodes
	 * r.enUS: classWriteMethods
	 * r: classeEcrireEcrivains
	 * r.enUS: classWriteWriters
	 * r: classeEntiteVars
	 * r.enUS: classEntityVars
	 * r: classeMethodeVars
	 * r.enUS: classMethodVars
	 * r: classeVals
	 * r.enUS: classVals
	 * 
	 * r: EnsembleInfo
	 * r.enUS: PackageInfo
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: classe
	 * r.enUS: class
	 * r: contexte
	 * r.enUS: context
	 * r: Contexte
	 * r.enUS: Context
	 * r: Entite
	 * r.enUS: Entity
	 * r: Pluriel
	 * r.enUS: Plural
	 * r: Singulier
	 * r.enUS: Singular
	 * 
	 * r: Ecrire:
	 * r.enUS: Write:
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 */  
	public void ecrireGenClasses(QueryResponse reponseRecherche, String classeLangueNom, String langueNom) throws Exception { 
		SolrDocumentList listeRecherche = reponseRecherche.getResults();

		if(listeRecherche.size() > 0 && (langueIndexe || !StringUtils.equals(langueNom, this.langueNom))) {    
			for(int i = 0; i < listeRecherche.size(); i++) {
				doc = listeRecherche.get(i); 
				Integer partNumero = (Integer)doc.get("partNumero_stored_int");
				if(partNumero.equals(1)) {
					classeDoc = doc;

					classeVals = ToutEcrivain.create();

					classeCheminRepertoireGen = (String)doc.get("classeCheminRepertoireGen_" + langueNom + "_stored_string");
					classeCheminGen = (String)doc.get("classeCheminGen_" + langueNom + "_stored_string"); 
					classeCheminApiEnsembleInfo = (String)doc.get("classeCheminApiEnsembleInfo_" + langueNom + "_stored_string"); 
					classeCheminGenApiServiceImpl = (String)doc.get("classeCheminGenApiServiceImpl_" + langueNom + "_stored_string"); 
					classeCheminApiServiceImpl = (String)doc.get("classeCheminApiServiceImpl_" + langueNom + "_stored_string"); 
					classeCheminGenApiService = (String)doc.get("classeCheminGenApiService_" + langueNom + "_stored_string"); 

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

					o = ToutEcrivain.create(classeFichierGen);
					classeNomSimple = (String)doc.get("classeNomSimple_" + langueNom + "_stored_string");
					uncapitalizeClasseNomSimple = StringUtils.uncapitalize(classeNomSimple);
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
					classeVarInheritClePrimaire = (String)doc.get("classeVarInheritClePrimaire_" + langueNom + "_stored_string");
					classeVarSauvegardes = (String)doc.get("classeVarSauvegardes_" + langueNom + "_stored_string");
					classeVarId = (String)doc.get("classeVarId_" + langueNom + "_stored_string");
					classeVarCleUnique = (String)doc.get("classeVarCleUnique_" + langueNom + "_stored_string");
					classeVarModifie = (String)doc.get("classeVarModifie_" + langueNom + "_stored_string");
					classeVarCree = (String)doc.get("classeVarCree_" + langueNom + "_stored_string");
					classeApiUri = (String)doc.get("classeApiUri_stored_string");
					classeCommentaire = (String)doc.get("classeCommentaire_" + langueNom + "_stored_string");
					classeMotsCles = (List<String>)doc.get("classeMotsCles_stored_strings");
					if(classeMotsCles == null)
						classeMotsCles = Arrays.asList();
					classeImportationsGen = (List<String>)doc.get("classeImportationsGen_" + langueNom + "_stored_strings");
					if(classeFichierGen.getAbsolutePath().equals("/usr/local/src/computate-scolaire/src/gen/java/org/computate/scolaire/enUS/design/DesignDisplayGenPageGen.java"))
						System.out.println("ID " + classeFichierGen.getAbsolutePath() + ": " + doc.get("id") + classeImportationsGen);
					classeSuperEcrireMethodes = (List<String>)doc.get("classeSuperEcrireMethodes_stored_strings");
					classeEcrireMethodes = (List<String>)doc.get("classeEcrireMethodes_stored_strings");
					classeEcrireEcrivains = new ArrayList<>();
					if(classeEcrireMethodes == null)
						classeEcrireMethodes = new ArrayList<>();
					for(String classeEcrireMethode : classeEcrireMethodes) {
						classeEcrireEcrivains.add(new ToutEcrivain().initDeepStringPrintWriter());
					}
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
					classeEntiteClassesSuperEtMoiSansGen = (List<String>)doc.get("entiteClassesSuperEtMoiSansGen_stored_strings");
					classePromesse = (Boolean)doc.get("classePromesse_stored_boolean");
					classeEtendGen = (Boolean)doc.get("classeEtendGen_stored_boolean");
					classeBaseEtendGen = (Boolean)doc.get("classeBaseEtendGen_stored_boolean");
					classeEtendBase = (Boolean)doc.get("classeEtendBase_stored_boolean");
					classeEstBase = (Boolean)doc.get("classeEstBase_stored_boolean");
					classeInitLoin = (Boolean)doc.get("classeInitLoin_stored_boolean");
					classeContientRequeteSite = (Boolean)doc.get("classeContientRequeteSite_stored_boolean");
					classeSauvegarde = BooleanUtils.isTrue((Boolean)doc.get("classeSauvegarde_stored_boolean"));
					classeIndexe = BooleanUtils.isTrue((Boolean)doc.get("classeIndexe_stored_boolean"));
					classeImage = BooleanUtils.isTrue((Boolean)doc.get("classeImage_stored_boolean"));
					classeModele = BooleanUtils.isTrue((Boolean)doc.get("classeModele_stored_boolean"));
					classeApi = BooleanUtils.isTrue((Boolean)doc.get("classeApi_stored_boolean"));
					classePage = BooleanUtils.isTrue((Boolean)doc.get("classePage_stored_boolean"));
					classePageSimple = BooleanUtils.isTrue((Boolean)doc.get("classePageSimple_stored_boolean"));
					classeRoleSession = (Boolean)doc.get("classeRoleSession_stored_boolean");
					classeRoleUtilisateur = (Boolean)doc.get("classeRoleUtilisateur_stored_boolean");
					classeRoleChacun = (Boolean)doc.get("classeRoleChacun_stored_boolean");

					classeSessionEcrire = (Boolean)doc.get("classeSessionEcrire_stored_boolean");
					classeUtilisateurEcrire = (Boolean)doc.get("classeUtilisateurEcrire_stored_boolean");
					classePublicEcrire = (Boolean)doc.get("classePublicEcrire_stored_boolean");
					classeSessionLire = (Boolean)doc.get("classeSessionLire_stored_boolean");
					classeUtilisateurLire = (Boolean)doc.get("classeUtilisateurLire_stored_boolean");
					classePublicLire = (Boolean)doc.get("classePublicLire_stored_boolean");

					classeRolesTrouves = BooleanUtils.isTrue((Boolean)doc.get("classeRolesTrouves_stored_boolean"));
					List<String> classeRolesTemp = (List<String>)doc.get("classeRoles_stored_strings");
					List<String> classeRolesLangue = (List<String>)doc.get("classeRolesLangue_stored_strings");
					classeRoles = new ArrayList<>();
					if(classeRolesTemp != null) {
						for(Integer j = 0; j < classeRolesTemp.size(); j++) {
							String classeRole = classeRolesTemp.get(j);
							String classeRoleLangue = classeRolesLangue.get(j);
							if(langueNom.equals(classeRoleLangue))
								classeRoles.add(classeRole);
						}
					}

					classeRoleLiresTrouves = BooleanUtils.isTrue((Boolean)doc.get("classeRoleLiresTrouves_stored_boolean"));
					List<String> classeRoleLiresTemp = (List<String>)doc.get("classeRoleLires_stored_strings");
					List<String> classeRoleLiresLangue = (List<String>)doc.get("classeRoleLiresLangue_stored_strings");
					classeRoleLires = new ArrayList<>();
					if(classeRoleLiresTemp != null) {
						for(Integer j = 0; j < classeRoleLiresTemp.size(); j++) {
							String classeRoleLire = classeRoleLiresTemp.get(j);
							String classeRoleLireLangue = classeRoleLiresLangue.get(j);
							if(langueNom.equals(classeRoleLireLangue))
								classeRoleLires.add(classeRoleLire);
						}
					}

					classeTrisVar = (List<String>)doc.get("classeTrisVar_" + langueNom + "_stored_strings");
					classeTrisOrdre = (List<String>)doc.get("classeTrisOrdre_stored_strings");
					classeTrisSuffixeType = (List<String>)doc.get("classeTrisSuffixeType_stored_strings");
					classeFiltresTrouves = BooleanUtils.isTrue((Boolean)doc.get("classeFiltresTrouves_stored_boolean"));
					classeFiltres = (List<String>)doc.get("classeFiltres_stored_strings");
					classeApiMethodes = (List<String>)doc.get("classeApiMethodes_" + langueNom + "_stored_strings");
					if(classeApiMethodes == null)
						classeApiMethodes = new ArrayList<>();
					classeEntiteVars = (List<String>)doc.get("classeEntiteVars_" + langueNom + "_stored_strings");
					if(classeEntiteVars == null)
						classeEntiteVars = new ArrayList<>();
					classeMethodeVars = (List<String>)doc.get("classeMethodeVars_" + langueNom + "_stored_strings");
					if(classeMethodeVars == null)
						classeMethodeVars = new ArrayList<>();
					entiteIndice = 0;   

					classeContexte = (Boolean)doc.get("classeContexte_stored_boolean");
					contexteCouleur = (String)doc.get("contexteCouleur_stored_string");
					contexteIconeGroupe = (String)doc.get("contexteIconeGroupe_stored_string");
					contexteIconeNom = (String)doc.get("contexteIconeNom_stored_string");
					contexteRows = (Integer)doc.get("contexteRows_stored_int");

					contexteDescription = (String)doc.get("contexteDescription" + "_" + langueNom + "_stored_string");
					contexteImageLargeur = (Integer)doc.get("contexteImageLargeur" + "_" + langueNom + "_stored_int");
					contexteImageHauteur = (Integer)doc.get("contexteImageHauteur" + "_" + langueNom + "_stored_int");
					contexteVideoId = (String)doc.get("contexteVideoId" + "_" + langueNom + "_stored_string");
					contexteUnNom = (String)doc.get("contexteUnNom" + "_" + langueNom + "_stored_string");
					contexteNomSingulier = (String)doc.get("contexteNomSingulier" + "_" + langueNom + "_stored_string");
					contexteNomPluriel = (String)doc.get("contexteNomPluriel" + "_" + langueNom + "_stored_string");
					contexteNomVar = (String)doc.get("contexteNomVar" + "_" + langueNom + "_stored_string");
					contexteAdjectif = (String)doc.get("contexteAdjectif" + "_" + langueNom + "_stored_string");
					contexteAdjectifPluriel = (String)doc.get("contexteAdjectifPluriel" + "_" + langueNom + "_stored_string");
					contexteAdjectifVar = (String)doc.get("contexteAdjectifVar" + "_" + langueNom + "_stored_string");
					contexteNomAdjectifSingulier = (String)doc.get("contexteNomAdjectifSingulier" + "_" + langueNom + "_stored_string");
					contexteNomAdjectifPluriel = (String)doc.get("contexteNomAdjectifPluriel" + "_" + langueNom + "_stored_string");
					contexteCe = (String)doc.get("contexteCe" + "_" + langueNom + "_stored_string");
					contexteUn = (String)doc.get("contexteUn" + "_" + langueNom + "_stored_string");
					contexteNomActuel = (String)doc.get("contexteNomActuel" + "_" + langueNom + "_stored_string");
					contexteTousNom = (String)doc.get("contexteTousNom" + "_" + langueNom + "_stored_string");
					contexteRechercherTousNomPar = (String)doc.get("contexteRechercherTousNomPar" + "_" + langueNom + "_stored_string");
					contexteLesNoms = (String)doc.get("contexteLesNoms" + "_" + langueNom + "_stored_string");
					contexteTitre = (String)doc.get("contexteTitre" + "_" + langueNom + "_stored_string");
					contexteH1 = (String)doc.get("contexteH1" + "_" + langueNom + "_stored_string");
					contexteH2 = (String)doc.get("contexteH2" + "_" + langueNom + "_stored_string");
					contexteH3 = (String)doc.get("contexteH3" + "_" + langueNom + "_stored_string");
					contexteAucunNomTrouve = (String)doc.get("contexteAucunNomTrouve" + "_" + langueNom + "_stored_string");
					contexteUnNomAdjectif = (String)doc.get("contexteUnNomAdjectif" + "_" + langueNom + "_stored_string");
					contexteCeNom = (String)doc.get("contexteCeNom" + "_" + langueNom + "_stored_string");
					contexteLeNom = (String)doc.get("contexteLeNom" + "_" + langueNom + "_stored_string");
					contexteDeNom = (String)doc.get("contexteDeNom" + "_" + langueNom + "_stored_string");

					if(classeApi && ecrireApi) {
						if(classeFichierGenApiServiceImpl != null) {
							classeFichierGenApiServiceImpl.getParentFile().mkdirs();
							auteurGenApiServiceImpl = ToutEcrivain.create(classeFichierGenApiServiceImpl);
						}
						if(classeFichierApiServiceImpl != null && (!classeFichierApiServiceImpl.exists() || classeFichierApiServiceImpl.length() == 0)) {
							classeFichierApiServiceImpl.getParentFile().mkdirs();
							auteurApiServiceImpl = ToutEcrivain.create(classeFichierApiServiceImpl);
						}
						if(classeFichierGenApiService != null) {
							classeFichierGenApiService.getParentFile().mkdirs();
							auteurGenApiService = ToutEcrivain.create(classeFichierGenApiService);
						}
					}
					genCodeInit();
//					if(StringUtils.equals(classeLangueNom, langueNom)) {
						auteurGenClasse = ToutEcrivain.create(classeFichierGen);
	
						o = auteurGenClasse;
	
						genCodeInitLoin(langueNom);
						genCodeRequeteSite(langueNom);
						genCodeObtenir(langueNom);
						genCodeAttribuer(langueNom);
						genCodePut(langueNom);
						genCodePeupler(langueNom);
						genCodeClasseDebut(langueNom);
//					}
					if(classeApi)
						apiCodeClasseDebut(langueNom);
					if(classePage)
//						pageCodeClasseDebut(langueNom);
						pageCodeClasse(langueNom);
				} 
				else {
					Boolean partEstConstructeur = (Boolean)doc.get("partEstConstructeur_stored_boolean");
					Boolean partEstEntite = (Boolean)doc.get("partEstEntite_stored_boolean");
					Boolean partEstMethode = (Boolean)doc.get("partEstMethode_stored_boolean");
	
//					if(StringUtils.equals(classeLangueNom, langueNom)) {
						if(BooleanUtils.isTrue(partEstConstructeur)) {
							genCodeConstructeur(langueNom);
						}
						else if(BooleanUtils.isTrue(partEstMethode)) {
							genCodeMethode(langueNom);
						}
						else if(BooleanUtils.isTrue(partEstEntite)) {
							genCodeEntite(langueNom);
						}
//					}
				}
			}
			if(o != null) {
				if(listeRecherche.size() > 0 && !StringUtils.equals(classeCheminAbsolu, classeCheminGen)) {
//					if(StringUtils.equals(classeLangueNom, langueNom)) {
						genCodeClasseFin(langueNom);
//					}
					if(classeApi) {
//						ecrireApiEnsembleInfo(langueNom);
						ecrireGenApiService(langueNom);
						ecrireGenApiServiceImpl1(langueNom);
						ecrireGenApiServiceImpl2(langueNom);
						ecrireGenApiServiceImpl3(langueNom);
						ecrireApiServiceImpl(langueNom);
					}
				}
			}
		} 
	}  
}
