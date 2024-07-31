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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocumentList;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

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
	public void ecrireGenClasses(String classeCheminAbsolu, String classeLangueNom, String langueNom, JsonObject langueConfig) throws Exception { 

		SolrQuery rechercheSolr = new SolrQuery();   
		rechercheSolr.setQuery("*:*");
		rechercheSolr.setRows(1000000);
		rechercheSolr.addFilterQuery("classeChemin_" + classeLangueNom + "_indexed_string:" + ClientUtils.escapeQueryChars(classeCheminAbsolu));
		rechercheSolr.addFilterQuery("classeEtendGen_indexed_boolean:true");
		rechercheSolr.addSort("partNumero_indexed_int", ORDER.asc);

		QueryResponse reponseRecherche = clientSolrComputate.query(rechercheSolr);
		ecrireGenClasses(reponseRecherche, classeLangueNom, langueNom, langueConfig);
	}

	/** 
	 * Var.enUS: writeGenClasses
	 * Param1.var.enUS: searchResponse
	 * Param2.var.enUS: classLanguageName
	 * Param3.var.enUS: languageName
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 */  
	public void ecrireGenClasses(QueryResponse reponseRecherche, String classeLangueNom, String langueNom, JsonObject langueConfig) throws Exception { 
		SolrDocumentList listeRecherche = reponseRecherche.getResults();

		if(listeRecherche.size() > 0 && (langueIndexe || !StringUtils.equals(langueNom, this.langueNom))) {    
			for(int i = 0; i < listeRecherche.size(); i++) {
				doc = new JsonObject(listeRecherche.get(i).jsonStr());
				Integer partNumero = (Integer)doc.getInteger("partNumero_stored_int");
				if(partNumero.equals(1)) {
					ecrireGenClasse(doc, langueNom);

					classeDoc = doc;
					classeVals = ToutEcrivain.create();

					classeFichierGen = new File(classeCheminGen);
			
					classeRepertoireGen = new File(classeCheminRepertoireGen);
					classeRepertoireGen.mkdirs();
			
					o = ToutEcrivain.create(classeFichierGen);

					if(classeApi && ecrireApi) {
						if(classeFichierGenApiServiceImpl != null) {
							classeFichierGenApiServiceImpl.getParentFile().mkdirs();
							auteurGenApiServiceImpl = ToutEcrivain.create(classeFichierGenApiServiceImpl);
						}
						if(classeFichierApiServiceImpl != null && (!classeFichierApiServiceImpl.exists() || classeFichierApiServiceImpl.length() == 0L)) {
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
						auteurGenClasseDebut = ToutEcrivain.create();
						auteurGenClasseFin = ToutEcrivain.create();
						auteurGenClasse = ToutEcrivain.create(classeFichierGen);
	
						genCodeInitLoin(langueNom, langueConfig);
						genCodeRequeteSite(langueNom, langueConfig);
						genCodeObtenir(langueNom, langueConfig);
						genCodeAttribuer(langueNom, langueConfig);
						genCodePut(langueNom, langueConfig);
						genCodePeupler(langueNom, langueConfig);
						genCodeClasseDebut(langueNom, langueConfig);
//					}
					if(classeApi)
						apiCodeClasseDebut(langueNom);
				} 
				else {
					Boolean partEstConstructeur = (Boolean)doc.getBoolean("partEstConstructeur_stored_boolean");
					Boolean partEstEntite = (Boolean)doc.getBoolean("partEstEntite_stored_boolean");
					Boolean partEstMethode = (Boolean)doc.getBoolean("partEstMethode_stored_boolean");
	
//					if(StringUtils.equals(classeLangueNom, langueNom)) {
						if(BooleanUtils.isTrue(partEstConstructeur)) {
							try {
								genCodeConstructeur(langueNom, langueConfig);
							} catch(Exception ex) {
								throw new RuntimeException(String.format("%s %s %s", classeNomSimple, i18nGlobale.getString(I18n.var_constructeur), classeNomSimple), ex);
							}
						}
						else if(BooleanUtils.isTrue(partEstMethode)) {
							try {
								genCodeMethode(langueNom, langueConfig);
							} catch(Exception ex) {
								throw new RuntimeException(String.format("%s %s %s", classeNomSimple, i18nGlobale.getString(I18n.var_methode), methodeVar), ex);
							}
						}
						else if(BooleanUtils.isTrue(partEstEntite)) {
							try {
								genCodeEntite(langueNom, langueConfig);
							} catch(Exception ex) {
								throw new RuntimeException(String.format("%s %s %s", classeNomSimple, i18nGlobale.getString(I18n.var_entite), entiteVar), ex);
							}
						}
//					}
				}
			}
			if(o != null) {
				if(listeRecherche.size() > 0 && !StringUtils.equals(classeCheminAbsolu, classeCheminGen)) {
//					if(StringUtils.equals(classeLangueNom, langueNom)) {
						genCodeClasseFin(langueNom, langueConfig);
//					}
					if(classePage) {
						pageCodeClasseJava(langueNom, langueConfig);
						pageCodeClasseJinja(langueNom, langueConfig);
					}
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

	/** 
	 * Var.enUS: writeGenClasses
	 * Param1.var.enUS: searchResponse
	 * Param2.var.enUS: classLanguageName
	 * Param3.var.enUS: languageName
	 * frFR: Récupérer les enregistrements de la classe à partir du moteur de recherche, 
	 * frFR: traitez-les et écrivez-les dans des fichiers de classe pour chaque langue prise en charge. 
	 * enUS: Retrieve the records for the class from the search engine, 
	 * enUS: process them and write them into class files for each supported language. 
	 */  
	public void ecrireGenClasse(JsonObject doc, String langueNom) throws Exception { 

		classeCheminRepertoireGen = doc.getString("classeCheminRepertoireGen_" + langueNom + "_stored_string");
		classeCheminGen = doc.getString("classeCheminGen_" + langueNom + "_stored_string"); 
		classeCheminApiEnsembleInfo = doc.getString("classeCheminApiEnsembleInfo_" + langueNom + "_stored_string"); 
		classeCheminGenApiServiceImpl = doc.getString("classeCheminGenApiServiceImpl_" + langueNom + "_stored_string"); 
		classeCheminApiServiceImpl = doc.getString("classeCheminApiServiceImpl_" + langueNom + "_stored_string"); 
		classeCheminGenApiService = doc.getString("classeCheminGenApiService_" + langueNom + "_stored_string"); 

		if(classeCheminApiEnsembleInfo != null)
			classeFichierApiEnsembleInfo = new File(classeCheminApiEnsembleInfo);
		if(classeCheminGenApiServiceImpl != null)
			classeFichierGenApiServiceImpl = new File(classeCheminGenApiServiceImpl);
		if(classeCheminApiServiceImpl != null)
			classeFichierApiServiceImpl = new File(classeCheminApiServiceImpl);
		if(classeCheminGenApiService != null)
			classeFichierGenApiService = new File(classeCheminGenApiService);
		classeNomSimple = doc.getString("classeNomSimple_" + langueNom + "_stored_string");
		uncapitalizeClasseNomSimple = StringUtils.uncapitalize(classeNomSimple);
		classeNomSimpleGen = doc.getString("classeNomSimpleGen_" + langueNom + "_stored_string");
		classeNomCanonique = doc.getString("classeNomCanonique_" + langueNom + "_stored_string");
		classeNomCanoniqueSuper = doc.getString("classeNomCanoniqueSuper_" + langueNom + "_stored_string");
		classeNomSimpleSuper = doc.getString("classeNomSimpleSuper_" + langueNom + "_stored_string");
		classeNomCanoniqueSuper = doc.getString("classeNomCanoniqueSuper_" + langueNom + "_stored_string");
		classeNomSimpleSuperGenerique = doc.getString("classeNomSimpleSuperGenerique_" + langueNom + "_stored_string");
		classeNomCanoniqueSuperGenerique = doc.getString("classeNomCanoniqueSuperGenerique_" + langueNom + "_stored_string");
		classePageTemplate = doc.getString("classePageTemplate_" + langueNom + "_stored_string");
		classeGenPageTemplate = doc.getString("classeGenPageTemplate_" + langueNom + "_stored_string");
		classePageSuperTemplate = doc.getString("classePageSuperTemplate_" + langueNom + "_stored_string");
		classePageNomSimple = doc.getString("classePageNomSimple_" + langueNom + "_stored_string");
		classePageNomCanonique = doc.getString("classePageNomCanonique_" + langueNom + "_stored_string");
		classePageSuperNomSimple = doc.getString("classePageSuperNomSimple_" + langueNom + "_stored_string");
		classePageSuperNomCanonique = doc.getString("classePageSuperNomCanonique_" + langueNom + "_stored_string");
		classeDroitAuteur = doc.getString("classeDroitAuteur_stored_string");
		classeNomEnsemble = doc.getString("classeNomEnsemble_" + langueNom + "_stored_string");
		classeNomSimpleApiServiceImpl = doc.getString("classeNomSimpleApiServiceImpl_" + langueNom + "_stored_string");
		classeNomSimpleGenApiServiceImpl = doc.getString("classeNomSimpleGenApiServiceImpl_" + langueNom + "_stored_string");
		classeNomSimpleGenApiService = doc.getString("classeNomSimpleGenApiService_" + langueNom + "_stored_string");
		classeVarClePrimaire = doc.getString("classeVarClePrimaire_" + langueNom + "_stored_string");
		classeVarInheritClePrimaire = doc.getString("classeVarInheritClePrimaire_" + langueNom + "_stored_string");
		classeVarSauvegardes = doc.getString("classeVarSauvegardes_" + langueNom + "_stored_string");
		classeVarId = doc.getString("classeVarId_" + langueNom + "_stored_string");
		classeVarCleUnique = doc.getString("classeVarCleUnique_" + langueNom + "_stored_string");
		classeVarEmplacement = doc.getString("classeVarEmplacement_" + langueNom + "_stored_string");
		classeVarEmplacementCouleur = doc.getString("classeVarEmplacementCouleur_" + langueNom + "_stored_string");
		classeVarEmplacementTitre = doc.getString("classeVarEmplacementTitre_" + langueNom + "_stored_string");
		classeVarEmplacementUrl = doc.getString("classeVarEmplacementUrl_" + langueNom + "_stored_string");
		classeVarModifie = doc.getString("classeVarModifie_" + langueNom + "_stored_string");
		classeVarCree = doc.getString("classeVarCree_" + langueNom + "_stored_string");
		classeApiUri = doc.getString("classeApiUri_" + langueNom + "_stored_string");
		classeApiTag = doc.getString("classeApiTag_" + langueNom + "_stored_string");
		classeCommentaire = doc.getString("classeCommentaire_stored_string");
		classeCommentaireLangue = doc.getString("classeCommentaire_" + langueNom + "_stored_string");
		classeMotsClesTrouves = doc.getBoolean("classeMotsClesTrouves_stored_boolean");
		classeMotsCles = classeMotsClesTrouves ? Optional.ofNullable(doc.getJsonArray("classeMotsCles_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList()).stream().map(v -> (String)v).collect(Collectors.toList()) : Arrays.asList();
		if(classeMotsCles == null)
			classeMotsCles = Arrays.asList();
		classeImportationsGen = Optional.ofNullable(doc.getJsonArray("classeImportationsGen_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeSuperEcrireMethodes = Optional.ofNullable(doc.getJsonArray("classeSuperEcrireMethodes_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeEcrireMethodes = Optional.ofNullable(doc.getJsonArray("classeEcrireMethodes_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeEcrireEcrivains = new ArrayList<>();
		if(classeEcrireMethodes == null)
			classeEcrireMethodes = new ArrayList<>();
		for(String classeEcrireMethode : classeEcrireMethodes) {
			classeEcrireEcrivains.add(new ToutEcrivain().initDeepStringPrintWriter());
		}
		if(classeImportationsGen == null)
			classeImportationsGen = new ArrayList<String>();
		classeImportationsGenApi = Optional.ofNullable(doc.getJsonArray("classeImportationsGenApi_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		if(classeImportationsGenApi == null)
			classeImportationsGenApi = new ArrayList<String>();
		classeImportationsGenPage = Optional.ofNullable(doc.getJsonArray("classeImportationsGenPage_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		if(classeImportationsGenPage == null)
			classeImportationsGenPage = new ArrayList<String>();
		classeInitLoinExceptions = Optional.ofNullable(doc.getJsonArray("classeInitLoinExceptions_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		if(classeInitLoinExceptions == null)
			classeInitLoinExceptions = new ArrayList<String>();
		classeParametreTypeNoms = Optional.ofNullable(doc.getJsonArray("classeParametreTypeNoms_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeSuperParametreTypeNoms = Optional.ofNullable(doc.getJsonArray("classeSuperParametreTypeNoms_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classesSuperEtMoiSansGen = Optional.ofNullable(doc.getJsonArray("classesSuperEtMoiSansGen_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classePromesse = (Boolean)doc.getBoolean("classePromesse_stored_boolean");
		classeEtendGen = (Boolean)doc.getBoolean("classeEtendGen_stored_boolean");
		classeBaseEtendGen = (Boolean)doc.getBoolean("classeBaseEtendGen_stored_boolean");
		classeEtendBase = (Boolean)doc.getBoolean("classeEtendBase_stored_boolean");
		classeEstBase = (Boolean)doc.getBoolean("classeEstBase_stored_boolean");
		classeInitLoin = (Boolean)doc.getBoolean("classeInitLoin_stored_boolean");
		classeInitLoinAvant = (Boolean)doc.getBoolean("classeInitLoinAvant_stored_boolean");
		classeContientRequeteSite = (Boolean)doc.getBoolean("classeContientRequeteSite_stored_boolean");
		classeSauvegarde = BooleanUtils.isTrue((Boolean)doc.getBoolean("classeSauvegarde_stored_boolean"));
		classeIndexe = BooleanUtils.isTrue((Boolean)doc.getBoolean("classeIndexe_stored_boolean"));
		classeImage = BooleanUtils.isTrue((Boolean)doc.getBoolean("classeImage_stored_boolean"));
		classeModele = BooleanUtils.isTrue((Boolean)doc.getBoolean("classeModele_stored_boolean"));
		classeApi = BooleanUtils.isTrue((Boolean)doc.getBoolean("classeApi_stored_boolean"));
		classePage = BooleanUtils.isTrue((Boolean)doc.getBoolean("classePage_stored_boolean"));
		classeApiClasseNomSimple = (String)doc.getString("classeApiClasseNomSimple"   + "_" + langueNom + "_stored_string");
		classePageSimple = BooleanUtils.isTrue((Boolean)doc.getBoolean("classePageSimple_stored_boolean"));
		classeRoleSession = (Boolean)doc.getBoolean("classeRoleSession_stored_boolean");
		classeRoleUtilisateur = (Boolean)doc.getBoolean("classeRoleUtilisateur_stored_boolean");
		classeRoleChacun = (Boolean)doc.getBoolean("classeRoleChacun_stored_boolean");
		classePageTemplates = doc.getString("classePageTemplates_" + langueNom + "_stored_string");

		classeSessionEcrire = (Boolean)doc.getBoolean("classeSessionEcrire_stored_boolean");
		classeUtilisateurEcrire = (Boolean)doc.getBoolean("classeUtilisateurEcrire_stored_boolean");
		classePublicEcrire = (Boolean)doc.getBoolean("classePublicEcrire_stored_boolean");
		classeSessionLire = (Boolean)doc.getBoolean("classeSessionLire_stored_boolean");
		classeUtilisateurLire = (Boolean)doc.getBoolean("classeUtilisateurLire_stored_boolean");
		classePublicLire = (Boolean)doc.getBoolean("classePublicLire_stored_boolean");

		classeRolesTrouves = BooleanUtils.isTrue((Boolean)doc.getBoolean("classeRolesTrouves_stored_boolean"));
		List<String> classeRolesTemp = Optional.ofNullable(doc.getJsonArray("classeRoles_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		List<String> classeRolesLangue = Optional.ofNullable(doc.getJsonArray("classeRolesLangue_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		this.classeRoles = new ArrayList<>();
		this.classeRolesLangue = new ArrayList<>();
		if(classeRolesTemp != null) {
			for(Integer j = 0; j < classeRolesTemp.size(); j++) {
				String classeRole = classeRolesTemp.get(j);
				String classeRoleLangue = classeRolesLangue.get(j);
				if(langueNom.equals(classeRoleLangue)) {
					this.classeRoles.add(classeRole);
					this.classeRolesLangue.add(classeRoleLangue);
				}
			}
		}

		classeRoleLiresTrouves = BooleanUtils.isTrue((Boolean)doc.getBoolean("classeRoleLiresTrouves_stored_boolean"));
		List<String> classeRoleLiresTemp = Optional.ofNullable(doc.getJsonArray("classeRoleLires_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		List<String> classeRoleLiresLangue = Optional.ofNullable(doc.getJsonArray("classeRoleLiresLangue_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeRoleLires = new ArrayList<>();
		if(classeRoleLiresTemp != null) {
			for(Integer j = 0; j < classeRoleLiresTemp.size(); j++) {
				String classeRoleLire = classeRoleLiresTemp.get(j);
				String classeRoleLireLangue = classeRoleLiresLangue.get(j);
				if(langueNom.equals(classeRoleLireLangue))
					classeRoleLires.add(classeRoleLire);
			}
		}

		classeTrisVar = Optional.ofNullable(doc.getJsonArray("classeTrisVar_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeTrisOrdre = Optional.ofNullable(doc.getJsonArray("classeTrisOrdre_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeTrisSuffixeType = Optional.ofNullable(doc.getJsonArray("classeTrisSuffixeType_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeFiltresTrouves = BooleanUtils.isTrue((Boolean)doc.getBoolean("classeFiltresTrouves_stored_boolean"));
		classeFiltres = Optional.ofNullable(doc.getJsonArray("classeFiltres_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeApiMethodes = Optional.ofNullable(doc.getJsonArray("classeApiMethodes_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		if(classeApiMethodes == null)
			classeApiMethodes = new ArrayList<>();
		classeEntiteVars = Optional.ofNullable(doc.getJsonArray("classeEntiteVars_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		if(classeEntiteVars == null)
			classeEntiteVars = new ArrayList<>();
		classeMethodeVars = Optional.ofNullable(doc.getJsonArray("classeMethodeVars_" + langueNom + "_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		if(classeMethodeVars == null)
			classeMethodeVars = new ArrayList<>();
		entiteIndice = 0;   

		classeFiware = doc.getBoolean("classeFiware_stored_boolean");
		classeSmartDataDomain = doc.getString("classeSmartDataDomain_stored_string");
		classeSmartDataSubModule = doc.getString("classeSmartDataSubModule_stored_string");
		classeSmartDataModel = doc.getString("classeSmartDataModel_stored_string");

		classeContexte = (Boolean)doc.getBoolean("classeContexte_stored_boolean");
		classeCouleur = doc.getString("classeCouleur_stored_string");
		classeEntiteCouleur = doc.getString("classeEntiteCouleur_stored_string");
		classeIcone = doc.getString("classeIcone_stored_string");
		classeLignes = (Integer)doc.getInteger("classeLignes_stored_int");
		classeOrdre = (Integer)doc.getInteger("classeOrdre_stored_int");
		classeOrdreSql = (Integer)doc.getInteger("classeOrdreSql_stored_int");

		classeDescription = doc.getString("classeDescription" + "_" + langueNom + "_stored_string");
		classeImageLargeur = (Integer)doc.getInteger("classeImageLargeur" + "_" + langueNom + "_stored_int");
		classeImageHauteur = (Integer)doc.getInteger("classeImageHauteur" + "_" + langueNom + "_stored_int");
		classeVideoId = doc.getString("classeVideoId" + "_" + langueNom + "_stored_string");
		classeUnNom = doc.getString("classeUnNom" + "_" + langueNom + "_stored_string");
		classeNomSingulier = doc.getString("classeNomSingulier" + "_" + langueNom + "_stored_string");
		classeNomPluriel = doc.getString("classeNomPluriel" + "_" + langueNom + "_stored_string");
		classeNomVar = doc.getString("classeNomVar" + "_" + langueNom + "_stored_string");
		classeAdjectif = doc.getString("classeAdjectif" + "_" + langueNom + "_stored_string");
		classeAdjectifPluriel = doc.getString("classeAdjectifPluriel" + "_" + langueNom + "_stored_string");
		classeAdjectifVar = doc.getString("classeAdjectifVar" + "_" + langueNom + "_stored_string");
		classeNomAdjectifSingulier = doc.getString("classeNomAdjectifSingulier" + "_" + langueNom + "_stored_string");
		classeNomAdjectifPluriel = doc.getString("classeNomAdjectifPluriel" + "_" + langueNom + "_stored_string");
		classeCe = doc.getString("classeCe" + "_" + langueNom + "_stored_string");
		classeUn = doc.getString("classeUn" + "_" + langueNom + "_stored_string");
		classeNomActuel = doc.getString("classeNomActuel" + "_" + langueNom + "_stored_string");
		classeTousNom = doc.getString("classeTousNom" + "_" + langueNom + "_stored_string");
		classeRechercherTousNomPar = doc.getString("classeRechercherTousNomPar" + "_" + langueNom + "_stored_string");
		classeLesNoms = doc.getString("classeLesNoms" + "_" + langueNom + "_stored_string");
		classeTitre = doc.getString("classeTitre" + "_" + langueNom + "_stored_string");
		classeH1 = doc.getString("classeH1" + "_" + langueNom + "_stored_string");
		classeH2 = doc.getString("classeH2" + "_" + langueNom + "_stored_string");
		classeH3 = doc.getString("classeH3" + "_" + langueNom + "_stored_string");
		classeAucunNomTrouve = doc.getString("classeAucunNomTrouve" + "_" + langueNom + "_stored_string");
		classeUnNomAdjectif = doc.getString("classeUnNomAdjectif" + "_" + langueNom + "_stored_string");
		classeCeNom = doc.getString("classeCeNom" + "_" + langueNom + "_stored_string");
		classeLeNom = doc.getString("classeLeNom" + "_" + langueNom + "_stored_string");
		classeDeNom = doc.getString("classeDeNom" + "_" + langueNom + "_stored_string");

		classeValsVar = Optional.ofNullable(doc.getJsonArray("classeValsVar_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeValsLangue = Optional.ofNullable(doc.getJsonArray("classeValsLangue_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
		classeValsValeur = Optional.ofNullable(doc.getJsonArray("classeValsValeur_stored_strings")).orElse(new JsonArray()).stream().map(v -> (String)v).collect(Collectors.toList());
	}
}
