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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.configuration2.YAMLConfiguration;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

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
	 * r: entiteHtmlLigne
	 * r.enUS: entityHtmlRow
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
	public Boolean ecrireFormEntite(String langueNom, YAMLConfiguration langueConfig, ToutEcrivain wForm, String classeApiMethodeMethode) {
		int tIndex = 0;
		Boolean resultat = false;

		if(entiteHtml) {
			if(langueConfig.getString(ConfigCles.var_Recherche).equals(classeApiMethodeMethode)) {
				rechercheLigneActuelRecherche = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLigneRecherche != rechercheLigneActuelRecherche) {
					if(rechercheLigneRecherche != -1)
						wForm.tl(7, "</div>");
					wForm.tl(7, "<div class=\"w3-cell-row \">");
					rechercheLigneRecherche = rechercheLigneActuelRecherche;
					resultat = true;
				}
			}
			else if("POST".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPOST = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePOST != rechercheLigneActuelPOST) {
					if(rechercheLignePOST != -1)
						wForm.tl(7, "</div>");
					wForm.tl(7, "<div class=\"w3-cell-row \">");
					rechercheLignePOST = rechercheLigneActuelPOST;
					resultat = true;
				}
			}
			else if("PUTImport".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPUTImport = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePUTImport != rechercheLigneActuelPUTImport) {
					if(rechercheLignePUTImport != -1)
						wForm.tl(7, "</div>");
					wForm.tl(7, "<div class=\"w3-cell-row \">");
					rechercheLignePUTImport = rechercheLigneActuelPUTImport;
					resultat = true;
				}
			}
			else if(langueConfig.getString(ConfigCles.var_PUTFusion).equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPUTFusion = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePUTFusion != rechercheLigneActuelPUTFusion) {
					if(rechercheLignePUTFusion != -1)
						wForm.tl(7, "</div>");
					wForm.tl(7, "<div class=\"w3-cell-row \">");
					rechercheLignePUTFusion = rechercheLigneActuelPUTFusion;
					resultat = true;
				}
			}
			else if(langueConfig.getString(ConfigCles.var_PUTCopie).equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPUTCopie = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePUTCopie != rechercheLigneActuelPUTCopie) {
					if(rechercheLignePUTCopie != -1)
						wForm.tl(7, "</div>");
					wForm.tl(7, "<div class=\"w3-cell-row \">");
					rechercheLignePUTCopie = rechercheLigneActuelPUTCopie;
					resultat = true;
				}
			}
			else if("PATCH".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPATCH = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePATCH != rechercheLigneActuelPATCH) {
					if(rechercheLignePATCH != -1)
						wForm.tl(7, "</div>");
					wForm.tl(7, "<div class=\"w3-cell-row \">");
					rechercheLignePATCH = rechercheLigneActuelPATCH;
					resultat = true;
				}
			}
			else if("Page".equals(classeApiMethodeMethode)) {
				rechercheLigneActuelPage = ObjectUtils.defaultIfNull((Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int"), 0);
				if(rechercheLignePage != rechercheLigneActuelPage) {
					if(rechercheLignePage != -1)
						wForm.tl(7, "</div>");
					wForm.tl(7, "<div class=\"w3-cell-row \">");
					rechercheLignePage = rechercheLigneActuelPage;
					resultat = true;
				}
			}
			
			wForm.l("{{> \"htm", entiteVarCapitalise, "\" ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "=\"", classeApiMethodeMethode, "\"}}");
		}
		return resultat;
	}

	/**
	 * Var.enUS: pageCodeClass
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
	 * r: MiseEnPage
	 * r.enUS: PageLayout
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 * r: classeNomEnsemble
	 * r.enUS: classPackageName
	 * r: classeImportationsGenPage
	 * r.enUS: classImportsGenPage
	 * r: classeImportation
	 * r.enUS: classImport
	 * r: classeCommentaire
	 * r.enUS: classComment
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: ecrireCommentaire
	 * r.enUS: writeComment
	 * r: clientSolrComputate
	 * r.enUS: solrClientComputate
	 * r: classeCheminAbsolu
	 * r.enUS: classAbsolutePath
	 * r: entiteVarCapitalise
	 * r.enUS: entityVarCapitalized
	 * r: entiteVar
	 * r.enUS: entityVar
	 * r: entiteHtmlLigne
	 * r.enUS: entityHtmlRow
	 * r: entiteHtmlColonne
	 * r.enUS: entityHtmlColumn
	 * r: entiteDescription
	 * r.enUS: entityDescription
	 * r: entiteNomAffichage
	 * r.enUS: entityDisplayName
	 * r: entiteMultiligne
	 * r.enUS: entityMultiline
	 * r: entiteDefinir
	 * r.enUS: entityDefine
	 * r: entiteClassesSuperEtMoiSansGen
	 * r.enUS: entitySuperClassesAndMeWithoutGen
	 * r: ToutEcrivain
	 * r.enUS: AllWriter
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: classeDoc
	 * r.enUS: classDoc
	 * r: contexteCouleur
	 * r.enUS: contextColor
	 * r: classePageNomEnsemble
	 * r.enUS: classPagePackageName
	 * r: classeApiMethodes
	 * r.enUS: classApiMethods
	 * r: classeGenPageChemin
	 * r.enUS: classPagePathGen
	 * r: classePageCheminCss
	 * r.enUS: classPagePathCss
	 * r: classePageCheminJs
	 * r.enUS: classPagePathJs
	 * r: classePageUriMethodeLangue
	 * r.enUS: classPageUriMethodLanguage
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
	 * r: classePageSuperNomSimple
	 * r.enUS: classPageSuperSimpleName
	 * r: classeGenPageNomSimple
	 * r.enUS: classGenPageSimpleName
	 * r: classePageNomCanonique
	 * r.enUS: classPageCanonicalName
	 * r: contexteImageLargeur
	 * r.enUS: contextImageWidth
	 * r: contexteImageHauteur
	 * r.enUS: contextImageHeight
	 * r: contexteVideoId
	 * r.enUS: contextVideoId
	 * r: contexteAdjectifPluriel
	 * r.enUS: contextAdjectivePlural
	 * r: contexteAdjectifVar
	 * r.enUS: contextAdjectiveVar
	 * r: contexteNomAdjectifSingulier
	 * r.enUS: contextNameAdjectiveSingular
	 * r: contexteNomAdjectifPluriel
	 * r.enUS: contextNameAdjectivePlural
	 * r: contexteTousNom
	 * r.enUS: contextAllName
	 * r: contexteCeNom
	 * r.enUS: contextThisName
	 * r: contexteAdjectif
	 * r.enUS: contextAdjective
	 * r: classePageFichierGen
	 * r.enUS: classPageFileGen
	 * r: classePageFichierCss
	 * r.enUS: classPageFileCss
	 * r: classePageFichierJs
	 * r.enUS: classPageFileJs
	 * r: classePageFichier
	 * r.enUS: classPageFile
	 * r: auteurPageGenClasse
	 * r.enUS: writerPageGenClass
	 * r: classeAttribuerNomSimplePage
	 * r.enUS: classAttributeSimpleNamePage
	 * r: wEntites
	 * r.enUS: wEntities
	 * r: partEstEntite
	 * r.enUS: partIsEntity
	 * r: classeNomCanonique
	 * r.enUS: classCanonicalName
	 * r: entiteHtmlCellule
	 * r.enUS: entityHtmlCell
	 * r: rechercheReponse
	 * r.enUS: searchResponse
	 * r: rechercheListe
	 * r.enUS: searchList
	 * r: entiteDocumentSolr
	 * r.enUS: entitySolrDocument
	 * r: entiteNomSimpleGenerique
	 * r.enUS: entitySimpleNameGeneric
	 * r: entiteNomSimpleComplet
	 * r.enUS: entitySimpleNameComplete
	 * r: ecrireFormEntite
	 * r.enUS: writeFormEntity
	 * r: classePageSimple
	 * r.enUS: classPageSimple
	 * r: toutesLangues
	 * r.enUS: allLanguages
	 * r: pageLangueNom
	 * r.enUS: pageLanguageName
	 * r: contexteDescription
	 * r.enUS: contextDescription
	 * r: classeImage
	 * r.enUS: classImage
	 * r: pageImageLargeur
	 * r.enUS: pageImageWidth
	 * r: pageImageHauteur
	 * r.enUS: pageImageHeight
	 * r: langueNomActuel
	 * r.enUS: languageActualName
	 * r: fqClassesSuperEtMoi
	 * r.enUS: fqSuperClassesAndMe
	 * r: classeApiOperationIdMethode
	 * r.enUS: classApiOperationIdMethod
	 * r: classeApiUriMethode
	 * r.enUS: classApiUriMethod
	 * r: classeApiTypeMediaMethode
	 * r.enUS: classApiMediaTypeMethod
	 * r: classeApiMethodeMethode
	 * r.enUS: classApiMethodMethod
	 * r: classeApiMethode
	 * r.enUS: classApiMethod
	 * r: classeMethodeVar
	 * r.enUS: classMethodVar
	 * r: entiteTexte
	 * r.enUS: entityText
	 * r: entiteSuggere
	 * r.enUS: entitySuggested
	 * r: wSuggere
	 * r.enUS: wSuggest
	 * r: entiteLangue
	 * r.enUS: entityLanguage
	 * r: contexteRechercherTousNomPar
	 * r.enUS: contextSearchAllNameBy
	 * r: contexteRechercherTousNom
	 * r.enUS: contextSearchAllName
	 * r: valeurIndexe
	 * r.enUS: valueIndexed
	 * r: paramNom
	 * r.enUS: paramName
	 * r: paramValeursObjet
	 * r.enUS: paramValuesObject
	 * r: paramObjet
	 * r.enUS: paramObject
	 * r: RegarderClasseBase
	 * r.enUS: WatchClassBase
	 * r: RegarderClasse
	 * r.enUS: WatchClass
	 * r: siteChemin
	 * r.enUS: sitePath
	 * r: siteNom
	 * r.enUS: siteName
	 * r: regarderClasse
	 * r.enUS: watchClass
	 * 
	 * r: langueNom
	 * r.enUS: languageName
	 * r: entiteIndexe
	 * r.enUS: entityIndexed
	 * r: entiteStocke
	 * r.enUS: entityStored
	 * r: entiteDefinir
	 * r.enUS: entityDefine
	 * r: entiteCouverture
	 * r.enUS: entityWrap
	 * r: Couverture
	 * r.enUS: Wrap
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: entiteHtml
	 * r.enUS: entityHtml
	 * r: rechercheSolr
	 * r.enUS: solrSearch
	 * r: contexteUnNom
	 * r.enUS: contextAName
	 * r: classeEntiteVars
	 * r.enUS: classEntityVars
	 * r: contexteIconeGroupe
	 * r.enUS: contextIconGroup
	 * r: contexteIconeNom
	 * r.enUS: contextIconName
	 * r: contexteCe
	 * r.enUS: contextThis
	 * r: contexteCeNom
	 * r.enUS: contextThisName
	 * r: contexteUn
	 * r.enUS: contextA
	 * r: contexteUnNom
	 * r.enUS: contextAName
	 * r: contexteLeNom
	 * r.enUS: contextTheName
	 * r: contexteNomSingulier
	 * r.enUS: contextNameSingular
	 * r: contexteNomPluriel
	 * r.enUS: contextNamePlural
	 * r: contexteNomActuel
	 * r.enUS: contextActualName
	 * r: contexteTous
	 * r.enUS: contextAll
	 * r: contexteTousNom
	 * r.enUS: contextAllName
	 * r: contexteLesNoms
	 * r.enUS: contextTheNames
	 * r: contexteAucunNomTrouve
	 * r.enUS: contextNoNameFound
	 * r: contexteNomVar
	 * r.enUS: contextNameVar
	 * r: contexteDeNom
	 * r.enUS: contextOfName
	 * r: contexteNom
	 * r.enUS: contextName
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
	 * r: CONTEXTE_UnMasculinVoyelle
	 * r.enUS: CONTEXT_AMaleVowel
	 * r: CONTEXTE_UnFemininVoyelle
	 * r.enUS: CONTEXT_AFemaleVowel
	 * r: CONTEXTE_UnMasculinConsonne
	 * r.enUS: CONTEXT_AMaleConsonant
	 * r: CONTEXTE_UnFemininConsonne
	 * r.enUS: CONTEXT_AFemaleConsonant
	 * r: CONTEXTE_CetMasculinVoyelle
	 * r.enUS: CONTEXT_ThisMaleVowel
	 * r: CONTEXTE_CetteFemininVoyelle
	 * r.enUS: CONTEXT_ThisFemaleVowel
	 * r: CONTEXTE_CeMasculinConsonne
	 * r.enUS: CONTEXT_ThisMaleConsonant
	 * r: CONTEXTE_CetteFemininConsonne
	 * r.enUS: CONTEXT_ThisFemaleConsonant
	 * r: CONTEXTE_CesPluriel
	 * r.enUS: CONTEXT_ThesePlural
	 * r: CONTEXTE_LMasculinVoyelle
	 * r.enUS: CONTEXT_TheMaleVowel
	 * r: CONTEXTE_LFemininVoyelle
	 * r.enUS: CONTEXT_TheFemaleVowel
	 * r: CONTEXTE_LeMasculinConsonne
	 * r.enUS: CONTEXT_TheMaleConsonant
	 * r: CONTEXTE_LaFemininConsonne
	 * r.enUS: CONTEXT_TheFemaleConsonant
	 * r: CONTEXTE_LesPluriel
	 * r.enUS: CONTEXT_ThePlural
	 * r: CONTEXTE_ActuelMasculinAvant
	 * r.enUS: CONTEXT_CurrentMaleBefore
	 * r: CONTEXTE_ActuelleFemininAvant
	 * r.enUS: CONTEXT_CurrentFemaleBefore
	 * r: CONTEXTE_ActuelMasculinApres
	 * r.enUS: CONTEXT_CurrentMaleAfter
	 * r: CONTEXTE_ActuelleFemininApres
	 * r.enUS: CONTEXT_CurrentFemaleAfter
	 * r: CONTEXTE_TousMasculinPluriel
	 * r.enUS: CONTEXT_AllMalePlural
	 * r: CONTEXTE_ToutesFemininPluriel
	 * r.enUS: CONTEXT_AllFemalePlural
	 * r: CONTEXTE_AucunTrouveMasculinAvant
	 * r.enUS: CONTEXT_NoneFoundMaleBefore
	 * r: CONTEXTE_AucuneTrouveFemininAvant
	 * r.enUS: CONTEXT_NoneFemaleBefore
	 * r: CONTEXTE_AucunTrouveMasculinApres
	 * r.enUS: CONTEXT_NoneFoundMaleAfter
	 * r: CONTEXTE_AucuneTrouveFemininApres
	 * r.enUS: CONTEXT_NoneFemaleAfter
	 * r: CONTEXTE_DVoyelle
	 * r.enUS: CONTEXT_OfVowel
	 * r: CONTEXTE_DeConsonne
	 * r.enUS: CONTEXT_OfConsonant
	 * r: CONTEXTE_AdjectifPluriel
	 * r.enUS: CONTEXT_AdjectivePlural
	 * r: rechercheLigneActuel
	 * r.enUS: searchRowActual
	 * r: rechercheLigne
	 * r.enUS: searchRow
	 * r: entiteNomSimple
	 * r.enUS: entitySimpleName
	 * r: entiteNomSimpleGenerique
	 * r.enUS: entitySimpleNameGeneric
	 * r: entiteNomSimpleComplet
	 * r.enUS: entitySimpleNameComplete
	 * r: methodeTitre
	 * r.enUS: methodTitle
	 * r: fr-FR
	 * r.enUS: en-US
	 * r: contexteIconeClassesCss
	 * r.enUS: contextIconCssClasses
	 * r: pageTitre
	 * r.enUS: pageTitle
	 * r: PageTitre
	 * r.enUS: PageTitle
	 * r: contexteH1
	 * r.enUS: contextH1
	 * r: contexteH2
	 * r.enUS: contextH2
	 * r: contexteH3
	 * r.enUS: contextH3
	 * r: contexteTitre
	 * r.enUS: contextTitle
	 * r: classeApiUri
	 * r.enUS: classApiUri
	 * r: classeVarClePrimaire
	 * r.enUS: classVarPrimaryKey
	 * r: formulaireFiltres
	 * r.enUS: formFilters
	 * r: formulaireValeurs
	 * r.enUS: formValues
	 * r: FormulaireFiltres
	 * r.enUS: FormFilters
	 * r: FormValeurs
	 * r.enUS: FormValues
	 * r: "Rechercher "
	 * r.enUS: "Search "
	 * r: operationRequete
	 * r.enUS: serviceRequest
	 * r: RequeteService
	 * r.enUS: ServiceRequest
	 * r: entiteAttribuerOperationIdPATCH
	 * r.enUS: entityAttributeOperationIdPATCH
	 * r: entiteAttribuerOperationIdRecherche
	 * r.enUS: entityAttributeOperationIdSearch
	 * r: entiteOperationIdPATCH
	 * r.enUS: entityOperationIdPATCH
	 * r: entiteAttribuerTypeJson
	 * r.enUS: entityAttributeJsonType
	 * r: entiteTypeJson
	 * r.enUS: entityJsonType
	 * 
	 * r: liste
	 * r.enUS: list
	 * r: plusiers
	 * r.enUS: multiple
	 * r: Créer 
	 * r.enUS: Create 
	 * r: Modifier 
	 * r.enUS: Modify 
	 * r: Remplacer 
	 * r.enUS: Replace 
	 * r: Supprimer 
	 * r.enUS: Delete 
	 * r: valeur
	 * r.enUS: value
	 * r: filtre
	 * r.enUS: filter
	 * r: Recherche
	 * r.enUS: Search
	 * r: Entite
	 * r.enUS: Entity
	 * r: plusiers 
	 * r.enUS: multiple 
	 * r: resultat
	 * r.enUS: result
	 * r: methode
	 * r.enUS: method
	 * r: recherche
	 * r.enUS: search
	 * r: Court
	 * r.enUS: Short
	 */ 
	public void pageCodeClasse(String langueNom, YAMLConfiguration langueConfig) throws Exception {

		String classeVarClePrimaire = (String)classeDoc.get("classeVarClePrimaire"   + "_" + langueNom + "_stored_string");
		String classeGenPageChemin = (String)classeDoc.get("classeGenPageChemin"   + "_" + langueNom + "_stored_string");
		String classePageChemin = (String)classeDoc.get("classePageChemin"   + "_" + langueNom + "_stored_string");
		String classePageCheminCss = (String)classeDoc.get("classePageCheminCss"   + "_" + langueNom + "_stored_string");
		String classePageCheminJs = (String)classeDoc.get("classePageCheminJs"   + "_" + langueNom + "_stored_string");
		String classePageCheminHbs = (String)classeDoc.get("classePageCheminHbs"   + "_" + langueNom + "_stored_string");
		String classeGenPageCheminHbs = (String)classeDoc.get("classeGenPageCheminHbs"   + "_" + langueNom + "_stored_string");
		String classePageUriMethode = (String)classeDoc.get("classeApiUri"  + "_" + langueNom + "_stored_string");
		String classePageLangueNom = (String)classeDoc.get("classePageLangueNom"  + "_" + langueNom + "_stored_string");
		Boolean classeModele = (Boolean)classeDoc.get("classeModele_stored_boolean");
		YAMLConfiguration classePageLangueConfig = null;
		if(classePageLangueNom != null) {
			classePageLangueConfig = configurations.fileBased(YAMLConfiguration.class, String.format("%s/src/main/resources/i18n/i18n_%s.yml", appComputate, classePageLangueNom));
		}

		classePageNomSimple = (String)classeDoc.get("classePageNomSimple"   + "_" + langueNom + "_stored_string");
		classePageSuperNomSimple = (String)classeDoc.get("classePageSuperNomSimple"   + "_" + langueNom + "_stored_string");
		classeApiClasseNomSimple = (String)classeDoc.get("classeApiClasseNomSimple"   + "_" + langueNom + "_stored_string");
		String uncapitalizeClasseApiClasseNomSimple = StringUtils.uncapitalize(classeApiClasseNomSimple);
		classeGenPageNomSimple = (String)classeDoc.get("classeGenPageNomSimple"   + "_" + langueNom + "_stored_string");
		String classePageNomCanonique = (String)classeDoc.get("classePageNomCanonique"   + "_" + langueNom + "_stored_string");
		classeAttribuerNomSimplePages = (List<String>)classeDoc.get("classeAttribuerNomSimplePages_" + langueNom + "_stored_strings");

		if(!classePageCheminsGen.contains(classeGenPageChemin) && classeGenPageChemin != null && StringUtils.equals(classePageLangueNom, langueNom)) {
			classePageCheminsGen.add(classeGenPageChemin);

			contexteImageLargeur = (Integer)classeDoc.get("contexteImageLargeur" + "_" + langueNom + "_stored_int");
			contexteImageHauteur = (Integer)classeDoc.get("contexteImageHauteur" + "_" + langueNom + "_stored_int");
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
			contexteTous = (String)classeDoc.get("contexteTous" + "_" + langueNom + "_stored_string");
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
			classeVarTitre = (String)classeDoc.get("classeVarTitre" + "_" + langueNom + "_stored_string");
			classeVarH1 = (String)classeDoc.get("classeVarH1" + "_" + langueNom + "_stored_string");
			classeVarH2 = (String)classeDoc.get("classeVarH2" + "_" + langueNom + "_stored_string");
			classeVarH3 = (String)classeDoc.get("classeVarH3" + "_" + langueNom + "_stored_string");
			classeVarUrlId = (String)classeDoc.get("classeVarUrlId" + "_" + langueNom + "_stored_string");
			classeVarUrlPk = (String)classeDoc.get("classeVarUrlPk" + "_" + langueNom + "_stored_string");
			classeVarSuggere = (String)classeDoc.get("classeVarSuggere" + "_" + langueNom + "_stored_string");
			classeVarTexte = (String)classeDoc.get("classeVarTexte" + "_" + langueNom + "_stored_string");

			auteurWebsocket = ToutEcrivain.create();

			ToutEcrivain wRecherche = ToutEcrivain.create();
			ToutEcrivain wPOST = ToutEcrivain.create();
			ToutEcrivain wPUTImport = ToutEcrivain.create();
			ToutEcrivain wPUTFusion = ToutEcrivain.create();
			ToutEcrivain wPUTCopie = ToutEcrivain.create();
			ToutEcrivain wPATCH = ToutEcrivain.create();
			ToutEcrivain wSuggere = ToutEcrivain.create();
			ToutEcrivain wGetters = ToutEcrivain.create();
			ToutEcrivain wTh = ToutEcrivain.create();
			ToutEcrivain wTd = ToutEcrivain.create();
			ToutEcrivain wFoot = ToutEcrivain.create();
			ToutEcrivain wFormRecherche = ToutEcrivain.create();
			ToutEcrivain wFormPOST = ToutEcrivain.create();
			ToutEcrivain wFormPUTImport = ToutEcrivain.create();
			ToutEcrivain wFormPUTFusion = ToutEcrivain.create();
			ToutEcrivain wFormPUTCopie = ToutEcrivain.create();
			ToutEcrivain wFormPage = ToutEcrivain.create();
			ToutEcrivain wFormPATCH = ToutEcrivain.create();
			ToutEcrivain wJsInit = ToutEcrivain.create();
			ToutEcrivain wWebsocket = ToutEcrivain.create();
			ToutEcrivain wWebsocketInput = ToutEcrivain.create();
			ToutEcrivain wPks = ToutEcrivain.create();


			o = auteurGenPageHbs;

			o = auteurPageGenClasse;
			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + classeEntiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
//					rechercheSolr.addFilterQuery("entiteHtmlLigne_indexed_int:[* TO *]");
				rechercheSolr.addSort("entiteHtmlLigne_indexed_int", ORDER.asc);
				rechercheSolr.addSort("entiteHtmlCellule_indexed_int", ORDER.asc);
				QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList rechercheListe = rechercheReponse.getResults();

				rechercheLignes = rechercheSolr.getRows();

				rechercheLigneRecherche = -1;
				rechercheLignePOST = -1;
				rechercheLignePUTImport = -1;
				rechercheLignePUTFusion = -1;
				rechercheLignePUTCopie = -1;
				rechercheLignePATCH = -1;
				rechercheLignePage = -1;

				List<String> pageVars = Arrays.asList("pageH1", "pageH2", "pageH3", "pageTitre");

				if(rechercheListe.size() > 0) {
					Boolean resultatFormPOST = false; 
					Boolean resultatFormPUTImport = false; 
					Boolean resultatFormPUTFusion = false; 
					Boolean resultatFormPUTCopie = false; 
					Boolean resultatFormPage = false; 
					Boolean resultatFormPATCH = false; 
					Boolean resultatFormRecherche = false; 

					for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
						for(Integer j = 0; j < rechercheListe.size(); j++) {
							entiteDocumentSolr = rechercheListe.get(j);
							entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNom + "_stored_string");
							entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + langueNom + "_stored_string");
							entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + langueNom + "_stored_string");
							entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
							entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
							entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
							entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
							entiteHtml = (Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean");
							entiteHtmlLigne = (Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int");
							entiteHtmlCellule = (Integer)entiteDocumentSolr.get("entiteHtmlCellule_stored_int");
							entiteCouverture = (Boolean)entiteDocumentSolr.get("entiteCouverture_stored_boolean");
							entiteHtml = (Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean");
							entiteDocValues = (Boolean)entiteDocumentSolr.get("entiteDocValues_stored_boolean");
							entiteIndexeOuStocke = (Boolean)entiteDocumentSolr.get("entiteIndexeOuStocke_stored_boolean");
							entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
							entiteStocke = (Boolean)entiteDocumentSolr.get("entiteStocke_stored_boolean");
							entiteVarTitre = (Boolean)entiteDocumentSolr.get("entiteVarTitre_stored_boolean");
							entiteVarH1 = (Boolean)entiteDocumentSolr.get("entiteVarH1_stored_boolean");
							entiteVarH2 = (Boolean)entiteDocumentSolr.get("entiteVarH2_stored_boolean");
							entiteVarH3 = (Boolean)entiteDocumentSolr.get("entiteVarH3_stored_boolean");
							entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
							entiteModifier = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteModifier_stored_boolean"));
							entiteDefinir = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteDefinir_stored_boolean"));
							entiteAttribuer = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteAttribuer_stored_boolean"));
							entiteSignature = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSignature_stored_boolean"));
							entiteSuggere = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean"));
							entiteListeTypeJson = (String)entiteDocumentSolr.get("entiteListeTypeJson_stored_string");
							entiteAttribuerNomSimple = (String)entiteDocumentSolr.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string");
							entiteAttribuerVar = (String)entiteDocumentSolr.get("entiteAttribuerVar_" + langueNom + "_stored_string");
							entiteAttribuerVarSuggere = (String)entiteDocumentSolr.get("entiteAttribuerVarSuggere_" + langueNom + "_stored_string");
							entiteAttribuerOperationIdPATCH = (String)entiteDocumentSolr.get("entiteAttribuerOperationIdPATCH_" + langueNom + "_stored_string");
							entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + langueConfig.getString(ConfigCles.var_Recherche) + "_" + langueNom + "_stored_string");
							entiteAttribuerApiUri = (String)entiteDocumentSolr.get("entiteAttribuerApiUri_" + langueNom + "_stored_string");
							entiteAttribuerPageUri = (String)entiteDocumentSolr.get("entiteAttribuerPageUri_" + langueNom + "_stored_string");
							entiteAttribuerContexteUnNom = (String)entiteDocumentSolr.get("entiteAttribuerContexteUnNom_" + langueNom + "_stored_string");
							entiteAttribuerContexteNomPluriel = (String)entiteDocumentSolr.get("entiteAttribuerContexteNomPluriel_" + langueNom + "_stored_string");
							entiteAttribuerContexteCouleur = (String)entiteDocumentSolr.get("entiteAttribuerContexteCouleur_stored_string");
							entiteAttribuerContexteIconeGroupe = (String)entiteDocumentSolr.get("entiteAttribuerContexteIconeGroupe_stored_string");
							entiteAttribuerContexteIconeNom = (String)entiteDocumentSolr.get("entiteAttribuerContexteIconeNom_stored_string");
							entiteAttribuerTypeJson = (String)entiteDocumentSolr.get("entiteAttribuerTypeJson_stored_string");
							entiteImageBase64Url = (String)entiteDocumentSolr.get("entiteImageBase64Url_" + langueNom + "_stored_string");

							if(entiteHtml) {
								if(entiteHtmlCellule != null) {
									if(ecrireFormEntite(langueNom, langueConfig, wFormPage, "Page"))
										resultatFormPage = true;
								}
								if(entiteHtmlLigne != null && (entiteDefinir || entiteAttribuer)) {
//											if(ecrireFormEntite(langueNom, wFormPUTImport, "PUTImport"))
//												resultatFormPUTImport = true;
//											if(ecrireFormEntite(langueNom, wFormPUTFusion, langueConfig.getString(ConfigCles.var_PUTFusion)))
//												resultatFormPUTFusion = true;
									if(ecrireFormEntite(langueNom, langueConfig, wFormPOST, "POST"))
										resultatFormPOST = true;
									if(ecrireFormEntite(langueNom, langueConfig, wFormPUTCopie, langueConfig.getString(ConfigCles.var_PUTCopie)))
										resultatFormPUTCopie = true;
									if(ecrireFormEntite(langueNom, langueConfig, wFormPATCH, "PATCH"))
										resultatFormPATCH = true;
								}
								if(entiteIndexeOuStocke) {
									if(ecrireFormEntite(langueNom, langueConfig, wFormRecherche, langueConfig.getString(ConfigCles.var_Recherche)))
										resultatFormRecherche = true;
								}
							}
							if(entiteAttribuer) {
								wJsInit.tl(2, "{{#ifContainsAnyRoles roles ", langueConfig.getString(ConfigCles.var_rolesRequis), "}}");
								wJsInit.tl(5, langueConfig.getString(ConfigCles.var_suggere), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], $('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, true);");
								wJsInit.tl(2, "{{else}}");
								wJsInit.tl(5, langueConfig.getString(ConfigCles.var_suggere), classeNomSimple, entiteVarCapitalise, "([{'name':'fq','value':'", entiteAttribuerVar, ":' + pk}], $('#", "list", classeNomSimple, entiteVarCapitalise, "_", "Page", "'), pk, false);");
								wJsInit.tl(2, "{{/ifContainsAnyRoles}}");
//									wWebsocket.tl(2, "tl(2, \"", "await patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + \" + ", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Requete), StringUtils.capitalize(classeVarClePrimaire), "() + \" } ], {});\");");
								wPks.tl(2, "if(c == '", entiteAttribuerNomSimple, "')");
								wPks.tl(2, "await patch", entiteAttribuerNomSimple, "Vals( [ {name: 'fq', value: '", entiteAttribuerVar, ":' + pk2 } ], {});");
							}
							if(entiteSignature) {
								wJsInit.tl(2, "$('#signatureInput", classeNomSimple, "' + pk + '", entiteVar, "').jSignature({'height':200}).bind('change', function(e){ patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Val([{ name: 'fq', value: 'pk:' + pk }], 'set", entiteVarCapitalise, "', $('#signatureInput", classeNomSimple, "' + pk + '", entiteVar, "').jSignature('getData', 'default'));");
							}
							if(entiteDefinir || entiteAttribuer || entiteIndexeOuStocke) {
								if("LocalDate".equals(entiteNomSimple)) {
									wWebsocketInput.tl(3, "var val = o['", entiteVar, "'];");
//									wWebsocketInput.tl(3, "if(val != null) {");
//									wWebsocketInput.tl(4, "var t = moment(val, 'YYYY-MM-DD');");
//									wWebsocketInput.tl(4, "if(t)");
//									wWebsocketInput.tl(5, "val = t.format('", classePageLangueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "');");
//									wWebsocketInput.tl(3, "}");
								}
								else if("LocalDateTime".equals(entiteNomSimple)) {
									wWebsocketInput.tl(3, "var val = o['", entiteVar, "'];");
//									wWebsocketInput.tl(3, "if(val != null) {");
//									wWebsocketInput.tl(4, "var t = moment(val, 'YYYY-MM-DD');");
//									wWebsocketInput.tl(4, "if(t)");
//									wWebsocketInput.tl(5, "val = t.format('", classePageLangueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "');");
//									wWebsocketInput.tl(3, "}");
								}
								else if("LocalTime".equals(entiteNomSimple)) {
									wWebsocketInput.tl(3, "var val = o['", entiteVar, "'];");
//									wWebsocketInput.tl(3, "if(val != null) {");
//									wWebsocketInput.tl(4, "var t = moment(val, 'HH:mm');");
//									wWebsocketInput.tl(4, "if(t)");
//									wWebsocketInput.tl(5, "val = t.format('", classePageLangueConfig.getString(ConfigCles.var_HAposhAposmm), "');");
//									wWebsocketInput.tl(3, "}");
								}
								else {
									wWebsocketInput.tl(3, "var val = o['", entiteVar, "'];");
								}
								wWebsocketInput.tl(3, "if(vars.includes('", entiteVar, "')) {");
								if(entiteImageBase64Url != null) {
									wWebsocketInput.tl(4, "if(val === undefined)");
									wWebsocketInput.tl(5, "val = null;");
									wWebsocketInput.tl(4, "$('.img", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
									wWebsocketInput.tl(5, "if(val !== $(this).attr('src'))");
									wWebsocketInput.tl(6, "$(this).attr('src', val);");
									wWebsocketInput.tl(4, "});");
								}
								if(entiteSignature) {
									wWebsocketInput.tl(4, "if(val === undefined)");
									wWebsocketInput.tl(5, "val = null;");
									wWebsocketInput.tl(4, "$('.signatureInput", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
									wWebsocketInput.tl(5, "if(val !== $('.signatureImg", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').attr('src'))");
									wWebsocketInput.tl(6, "$('.signatureImg", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').attr('src', val == null ? 'data:image/png;base64,' : val);");
									wWebsocketInput.tl(5, langueConfig.getString(ConfigCles.var_ajouterLueur), "($('.signatureInput", classeNomSimple, "' + pk + '", entiteVarCapitalise, "'));");
									wWebsocketInput.tl(4, "});");
								}
								wWebsocketInput.tl(4, "$('.input", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
								wWebsocketInput.tl(5, "if(val !== $(this).val())");
								wWebsocketInput.tl(6, "$(this).val(val);");
								wWebsocketInput.tl(4, "});");
								wWebsocketInput.tl(4, "$('.var", classeNomSimple, "' + pk + '", entiteVarCapitalise, "').each(function() {");
								wWebsocketInput.tl(5, "if(val !== $(this).text())");
								wWebsocketInput.tl(6, "$(this).text(val);");
								wWebsocketInput.tl(4, "});");
								wWebsocketInput.tl(4, langueConfig.getString(ConfigCles.var_ajouterLueur), "($('.input", classeNomSimple, "' + pk + '", entiteVarCapitalise, "'));");
								wWebsocketInput.tl(3, "}");
							}
						}
						rechercheSolr.setStart(i.intValue() + rechercheLignes);
						rechercheReponse = clientSolrComputate.query(rechercheSolr);
						rechercheListe = rechercheReponse.getResults();
					}

					wWebsocket.tl(1, "var pk = ", langueConfig.getString(ConfigCles.var_requeteApi), "['pk'];");
					wWebsocket.tl(1, "var pks = ", langueConfig.getString(ConfigCles.var_requeteApi), "['pks'];");
					wWebsocket.tl(1, "var classes = ", langueConfig.getString(ConfigCles.var_requeteApi), "['classes'];");
					wWebsocket.tl(1, "var vars = ", langueConfig.getString(ConfigCles.var_requeteApi), "['vars'];");
					wWebsocket.tl(1, "var empty = ", langueConfig.getString(ConfigCles.var_requeteApi), "['empty'];");
					wWebsocket.l();
					wWebsocket.tl(1, "if(pk != null) {");
					wWebsocket.tl(2, langueConfig.getString(ConfigCles.var_rechercher), classeNomSimple, "Vals([ {name: 'fq', value: '", classeVarClePrimaire, ":' + pk} ], function( data, textStatus, jQxhr ) {");
					wWebsocket.tl(3, "var o = data['list'][0];");
					wWebsocket.s(wWebsocketInput);
					wWebsocket.tl(2, "});");
					wWebsocket.tl(1, "}");
//						wWebsocket.l();
//						wWebsocket.tl(1, "if(!empty) {");
//						wWebsocket.tl(2, "if(pks) {");
//						wWebsocket.tl(3, "for(i=0; i < pks.length; i++) {");
//						wWebsocket.tl(4, "var pk2 = pks[i];");
//						wWebsocket.tl(4, "var c = classes[i];");
//						wWebsocket.tl(4, "await window['patch' + c + 'Vals']( [ {name: 'fq', value: '", classeVarClePrimaire, ":' + pk2} ], {});");
//						wWebsocket.tl(3, "}");
//						wWebsocket.tl(2, "}");
//						wWebsocket.tl(2, "if(pk)");
//						wWebsocket.tl(3, "await patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Vals( [ {name: 'fq', value: '", classeVarClePrimaire, ":' + pk} ], {});");
//						wWebsocket.tl(1, "}");

					if(resultatFormPOST)
						wFormPOST.tl(7, "</div>");
					if(resultatFormPUTImport)
						wFormPUTImport.tl(7, "</div>");
					if(resultatFormPUTFusion)
						wFormPUTFusion.tl(7, "</div>");
					if(resultatFormPUTCopie)
						wFormPUTCopie.tl(7, "</div>");
					if(resultatFormPage)
						wFormPage.tl(7, "</div>");
					if(resultatFormPATCH)
						wFormPATCH.tl(7, "</div>");
					if(resultatFormRecherche)
						wFormRecherche.tl(7, "</div>");
				}
			}
	
			if(auteurPageClasse != null) {
				if(classeDroitAuteur != null)
					auteurPageClasse.l(classeDroitAuteur);
				auteurPageClasse.l("package ", classeNomEnsemble, ";");
				auteurPageClasse.l();
				auteurPageClasse.l("/**");

				String hackathonMission = (String)classeDoc.get("hackathonMissionPage_stored_string");
				String hackathonColumn = (String)classeDoc.get("hackathonColumnPage_stored_string");
				String hackathonLabels = (String)classeDoc.get("hackathonLabelsPage_stored_string");
				if(hackathonMission != null)
					auteurPageClasse.l(String.format(" * Map.hackathonMission: %s", hackathonMission));
				if(hackathonColumn != null)
					auteurPageClasse.l(String.format(" * Map.hackathonColumn: %s", hackathonColumn));
				if(hackathonLabels != null)
					auteurPageClasse.l(String.format(" * Map.hackathonLabels: %s", hackathonLabels));

				String hackathonPageGenMission = (String)classeDoc.get("hackathonMissionPageGen_stored_string");
				String hackathonPageGenColumn = (String)classeDoc.get("hackathonColumnPageGen_stored_string");
				String hackathonPageGenLabels = (String)classeDoc.get("hackathonLabelsPageGen_stored_string");
				if(hackathonPageGenMission != null)
					auteurPageClasse.l(String.format(" * Map.hackathonMissionGen: %s", hackathonPageGenMission));
				if(hackathonPageGenColumn != null)
					auteurPageClasse.l(String.format(" * Map.hackathonColumnGen: %s", hackathonPageGenColumn));
				if(hackathonPageGenLabels != null)
					auteurPageClasse.l(String.format(" * Map.hackathonLabelsGen: %s", hackathonPageGenLabels));

				auteurPageClasse.l(" * ", langueConfig.getString(ConfigCles.var_Traduire), ": false");
				for(String langueNom2 : autresLangues) {
					YAMLConfiguration langueConfig2 = configurations.fileBased(YAMLConfiguration.class, String.format("%s/src/main/resources/i18n/i18n_%s.yml", appComputate, classePageLangueNom));
					String classePageNomSimple2 = (String)classeDoc.get("classePageNomCanonique" + langueConfig2.getString(ConfigCles.var_PageRecherche)  + "_" + langueNom2 + "_stored_string");
					if(classePageNomSimple2 != null)
						auteurPageClasse.	l(" * ", langueConfig.getString(ConfigCles.var_NomCanonique), ".", langueNom2, ": ", classePageNomSimple2);
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
			{
				String hackathonMission = (String)classeDoc.get("hackathonMissionGenPage_stored_string");
				String hackathonColumn = (String)classeDoc.get("hackathonColumnGenPage_stored_string");
				String hackathonLabels = (String)classeDoc.get("hackathonLabelsGenPage_stored_string");
				if(hackathonMission != null)
					l(String.format(" * Map.hackathonMission: %s", hackathonMission));
				if(hackathonColumn != null)
					l(String.format(" * Map.hackathonColumn: %s", hackathonColumn));
				if(hackathonLabels != null)
					l(String.format(" * Map.hackathonLabels: %s", hackathonLabels));

				String hackathonGenPageGenMission = (String)classeDoc.get("hackathonMissionGenPageGen_stored_string");
				String hackathonGenPageGenColumn = (String)classeDoc.get("hackathonColumnGenPageGen_stored_string");
				String hackathonGenPageGenLabels = (String)classeDoc.get("hackathonLabelsGenPageGen_stored_string");
				if(hackathonGenPageGenMission != null)
					l(String.format(" * Map.hackathonMissionGen: %s", hackathonGenPageGenMission));
				if(hackathonGenPageGenColumn != null)
					l(String.format(" * Map.hackathonColumnGen: %s", hackathonGenPageGenColumn));
				if(hackathonGenPageGenLabels != null)
					l(String.format(" * Map.hackathonLabelsGen: %s", hackathonGenPageGenLabels));

			}
			l(" * ", langueConfig.getString(ConfigCles.var_Traduire), ": false");
			for(String langueNom2 : autresLangues) {
				YAMLConfiguration langueConfig2 = configurations.fileBased(YAMLConfiguration.class, String.format("%s/src/main/resources/i18n/i18n_%s.yml", appComputate, classePageLangueNom));
				String classeGenPageNomSimple2 = (String)classeDoc.get("classeGenPageNomCanonique" + langueConfig2.getString(ConfigCles.var_PageRecherche)  + "_" + langueNom2 + "_stored_string");
				if(classeGenPageNomSimple2 != null)
					l(" * ", langueConfig.getString(ConfigCles.var_NomCanonique), ".", langueNom2, ": ", classeGenPageNomSimple2);
			}
			l(" **/");
			s("public class ", classeGenPageNomSimple);
			s(" extends ", classeGenPageNomSimple, "Gen");
			s("<", (classePageSuperNomSimple == null ? "Object" : classePageSuperNomSimple), ">");
			l(" {");

			if(classePageSuperNomSimple == null) {
				l();
				tl(1, "/**");
				tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
				tl(1, "**/");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_requeteSite), "_(", "", classePartsCouverture.nomSimple(langueNom), "<", classePartsRequeteSite.nomSimple(langueNom), "> c", ") {");
				tl(1, "}");
			}

			l();
			tl(1, "/**");
			tl(1, " * {@inheritDoc}");
			tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
			tl(1, " **/");
			tl(1, "protected void _", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_(", classePartsCouverture.nomSimple(langueNom), "<", langueConfig.getString(ConfigCles.var_ListeRecherche), "<", classeApiClasseNomSimple, ">> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
			tl(1, "}");
			if(classePageSuperNomSimple != null && classeEtendBase) {
				l();
				tl(1, "protected void _defaultZoneId(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_Requete), "Vars().get(VAR_defaultZoneId)).orElse(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".SITE_ZONE)));");
				tl(1, "}");
				l();
				tl(1, "/**");
				tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
				tl(1, " **/");
				tl(1, "protected void _defaultTimeZone(", classePartsCouverture.nomSimple(langueNom), "<ZoneId> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(ZoneId.of(defaultZoneId));");
				tl(1, "}");
				l();
				tl(1, "protected void _defaultLocaleId(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getRequestHeaders().get(\"Accept-Language\")).map(acceptLanguage -> StringUtils.substringBefore(acceptLanguage, \",\")).orElse(", langueConfig.getString(ConfigCles.var_requeteSite), "_.getConfig().getString(", classePartsConfigCles.nomSimple(langueNom), ".SITE_LOCALE)));");
				tl(1, "}");
				l();
				tl(1, "/**");
				tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
				tl(1, " **/");
				tl(1, "protected void _defaultLocale(", classePartsCouverture.nomSimple(langueNom), "<Locale> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Locale.forLanguageTag(defaultLocaleId));");
				tl(1, "}");
				l();
				tl(1, "protected void _defaultRangeGap(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeGap()).orElse(\"DAY\"));");
				tl(1, "}");
				l();
				tl(1, "protected void _defaultRangeEnd(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeStart()).map(s -> TimeTool.parseZonedDateTime(defaultTimeZone, s)).orElse(ZonedDateTime.now(defaultTimeZone)));");
				tl(1, "}");
				l();
				tl(1, "protected void _defaultRangeStart(", classePartsCouverture.nomSimple(langueNom), "<ZonedDateTime> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeEnd()).map(s -> TimeTool.parseZonedDateTime(defaultTimeZone, s)).orElse(defaultRangeEnd.minusDays(7).toLocalDate().atStartOfDay(defaultTimeZone)));");
				tl(1, "}");
				l();
				tl(1, "protected void _defaultRangeVar(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetRangeGap()).orElse(\"", classeVarCree, "\"));");
				tl(1, "}");
				l();
				tl(1, "protected void _defaultFacetSort(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetSort()).orElse(\"index\"));");
				tl(1, "}");
				l();
				tl(1, "protected void _defaultFacetLimit(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetLimit()).orElse(1));");
				tl(1, "}");
				l();
				tl(1, "protected void _defaultFacetMinCount(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetMinCount()).orElse(1));");
				tl(1, "}");
				l();
				tl(1, "protected void _defaultPivotMinCount(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetPivotMinCount()).orElse(0));");
				tl(1, "}");
			}
			l();
			if(classePageSuperNomSimple != null && classeEtendBase)
				tl(1, "@Override");
			tl(1, "protected void _defaultPivotVars(List<String> l) {");
			tl(2, "Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getFacetPivots()).orElse(Arrays.asList()).forEach(facetPivot -> {");
			tl(3, "String var = ", classeNomSimple, ".var", langueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(facetPivot);");
			tl(3, "if(var != null)");
			tl(4, "l.add(var);");
			tl(2, "});");
			tl(1, "}");
			l();
			tl(1, "/**");
			tl(1, " * {@inheritDoc}");
			tl(1, " **/");
			tl(1, "protected void _", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, "(JsonArray l) {");
			tl(2, "Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_).map(o -> o.get", langueConfig.getString(ConfigCles.var_Liste), "()).orElse(Arrays.asList()).stream().map(o -> JsonObject.mapFrom(o)).forEach(o -> l.add(o));");
			tl(1, "}");
			l();
			tl(1, "protected void _facetCounts(", classePartsCouverture.nomSimple(langueNom), "<SolrResponse.FacetCounts> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
			tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getQueryResponse().getFacetCounts());");
			tl(1, "}");
			l();
			tl(1, "protected void _", uncapitalizeClasseApiClasseNomSimple, "Count(", classePartsCouverture.nomSimple(langueNom), "<Integer> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
			tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_ == null ? 0 : ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.size());");
			tl(1, "}");
			l();
			tl(1, "protected void _", uncapitalizeClasseApiClasseNomSimple, "_(", "", classePartsCouverture.nomSimple(langueNom), "<", classeApiClasseNomSimple, "> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
			if(classePageSimple) {
				tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(new ", classeApiClasseNomSimple, "());");
			} else {
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "Count == 1)");
				tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.get(0));");
			}
			tl(1, "}");
			if(classeModele) {
				l();
				tl(1, "protected void _", classeVarClePrimaire, "(", classePartsCouverture.nomSimple(langueNom), "<Long> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "Count == 1)");
				tl(3, langueConfig.getString(ConfigCles.var_cVar), ".o(", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarClePrimaire), "());");
				tl(1, "}");
			}

			l();
			if(classePageSuperNomSimple != null) {
				tl(1, "@Override");
			} else {
				tl(1, "/**");
				tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
				tl(1, "**/");
			}
			tl(1, "protected void _promise", langueConfig.getString(ConfigCles.var_Avant), "(Promise<Void> promise) {");
			tl(2, "promise.complete();");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _", classePageLangueConfig.getString(ConfigCles.var_classeNomSimple), "(", classePartsCouverture.nomSimple(langueNom), "<String> ", langueConfig.getString(ConfigCles.var_cVar), ") {");
			tl(2, langueConfig.getString(ConfigCles.var_cVar), ".o(\"", classeApiClasseNomSimple, "\");");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _page", langueConfig.getString(ConfigCles.var_Titre), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
			if(classeVarTitre != null) {
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "_ != null && ", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarTitre), "() != null)");
				tl(3, "c.o(", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarTitre), "()", ");");
				tl(2, "else if(", uncapitalizeClasseApiClasseNomSimple, "_ != null)");
			} else {
				tl(2, "if(", uncapitalizeClasseApiClasseNomSimple, "_ != null)");
			}
			tl(3, "c.o(", q(contexteTitre), ");");
			if(!classePageSimple) {
				tl(2, "else if(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_ == null || ", uncapitalizeClasseApiClasseNomSimple, "Count == 0)");
				tl(3, "c.o(", q(contexteAucunNomTrouve), ");");
			}
			if(contexteTitre != null) {
				tl(2, "else");
				tl(3, "c.o(", q(contexteTitre), ");");
			}
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _pageUri(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
			tl(2, "c.o(", q(classePageUriMethode), ");");
			tl(1, "}");
			for(String pageLangueNom : toutesLangues) {
				if(!StringUtils.equals(classePageLangueNom, pageLangueNom)) {
					String classePageUriMethodeLangue = (String)classeDoc.get(StringUtils.replace("classeApiUri_stored_string", StringUtils.capitalize(classePageLangueNom), StringUtils.capitalize(pageLangueNom)));
					
					if(classePageUriMethodeLangue != null) {
						l();
						if(classePageSuperNomSimple != null)
							tl(1, "@Override");
						tl(1, "protected void _pageUri", StringUtils.capitalize(pageLangueNom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
						tl(2, "c.o(", q(classePageUriMethodeLangue), ");");
						tl(1, "}");
					}
				}
			}

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _", langueConfig.getString(ConfigCles.var_roles), "(List<String> l) {");
			tl(2, "if(", langueConfig.getString(ConfigCles.var_requeteSite), "_ != null) {");
			tl(3, "l.addAll(Stream.concat(", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRessource), "().stream(), ", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_UtilisateurRolesRoyaume), "().stream()).distinct().collect(Collectors.toList()));");
			tl(2, "}");
			tl(1, "}");
			if(classeRolesTrouves) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_rolesRequis), "(List<String> l) {");
				tl(2, "l.addAll(Optional.ofNullable(siteRequest_.getConfig().getJsonArray(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_AUTH_ROLES_REQUIS), " + \"_", classeApiClasseNomSimple, "\")).orElse(new JsonArray()).stream().map(o -> o.toString()).collect(Collectors.toList()));");
				tl(1, "}");
			}
			if(classeRoleLiresTrouves) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_rolesPourLires), "(List<String> l) {");
				tl(2, "l.addAll(Optional.ofNullable(siteRequest_.getConfig().getJsonArray(", classePartsConfigCles.nomSimple(langueNom), ".", langueConfig.getString(ConfigCles.var_AUTH_ROLES_LIRE_REQUIS), " + \"_", classeApiClasseNomSimple, "\")).orElse(new JsonArray()).stream().map(o -> o.toString()).collect(Collectors.toList()));");
				tl(1, "}");
			}

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _pagination(JsonObject pagination) {");
			tl(2, "JsonArray pages = new JsonArray();");
			tl(2, "Long ", langueConfig.getString(ConfigCles.var_debut), " = ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getStart().longValue();");
			tl(2, "Long ", langueConfig.getString(ConfigCles.var_lignes), " = ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getRows().longValue();");
			tl(2, "Long ", langueConfig.getString(ConfigCles.var_numTrouve), " = ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getQueryResponse().getResponse().getNumFound().longValue();");
			tl(2, "Long ", langueConfig.getString(ConfigCles.var_debut), "Num = ", langueConfig.getString(ConfigCles.var_debut), " + 1L;");
			tl(2, "Long ", langueConfig.getString(ConfigCles.var_fin), "Num = ", langueConfig.getString(ConfigCles.var_debut), " + ", langueConfig.getString(ConfigCles.var_lignes), ";");
			tl(2, "Long floorMod = Math.floorMod(", langueConfig.getString(ConfigCles.var_numTrouve), ", ", langueConfig.getString(ConfigCles.var_lignes), ");");
			tl(2, "Long ", langueConfig.getString(ConfigCles.var_dernier), " = Math.floorDiv(", langueConfig.getString(ConfigCles.var_numTrouve), ", ", langueConfig.getString(ConfigCles.var_lignes), ") - (floorMod.equals(0L) ? 1L : 0L) * ", langueConfig.getString(ConfigCles.var_lignes), ";");
			tl(2, langueConfig.getString(ConfigCles.var_fin), "Num = ", langueConfig.getString(ConfigCles.var_fin), "Num < ", langueConfig.getString(ConfigCles.var_numTrouve), " ? ", langueConfig.getString(ConfigCles.var_fin), "Num : ", langueConfig.getString(ConfigCles.var_numTrouve), ";");
			tl(2, langueConfig.getString(ConfigCles.var_debut), "Num = ", langueConfig.getString(ConfigCles.var_numTrouve), " == 0L ? 0L : ", langueConfig.getString(ConfigCles.var_debut), "Num;");
			tl(2, "Long pagination", langueConfig.getString(ConfigCles.var_Debut), " = ", langueConfig.getString(ConfigCles.var_debut), " - 10L * ", langueConfig.getString(ConfigCles.var_lignes), ";");
			tl(2, "if(pagination", langueConfig.getString(ConfigCles.var_Debut), " < 0L)");
			tl(3, "pagination", langueConfig.getString(ConfigCles.var_Debut), " = 0L;");
			tl(2, "Long pagination", langueConfig.getString(ConfigCles.var_Fin), " = ", langueConfig.getString(ConfigCles.var_debut), " + 10L * ", langueConfig.getString(ConfigCles.var_lignes), ";");
			tl(2, "if(pagination", langueConfig.getString(ConfigCles.var_Fin), " > ", langueConfig.getString(ConfigCles.var_numTrouve), ")");
			tl(3, "pagination", langueConfig.getString(ConfigCles.var_Fin), " = ", langueConfig.getString(ConfigCles.var_numTrouve), ";");
			l();
			tl(2, "pagination.put(\"1L\", 1L);");
			tl(2, "pagination.put(\"0L\", 0L);");
			tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_debut), "\", ", langueConfig.getString(ConfigCles.var_debut), ");");
			tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_lignes), "\", ", langueConfig.getString(ConfigCles.var_lignes), ");");
			tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Precedent), "\", ", langueConfig.getString(ConfigCles.var_lignes), " / 2);");
			tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Prochaine), "\", ", langueConfig.getString(ConfigCles.var_lignes), " * 2);");
			tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_debut), "Num\", ", langueConfig.getString(ConfigCles.var_debut), "Num);");
			tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_fin), "Num\", ", langueConfig.getString(ConfigCles.var_fin), "Num);");
			tl(2, "pagination.put(\"", langueConfig.getString(ConfigCles.var_numTrouve), "\", ", langueConfig.getString(ConfigCles.var_numTrouve), ");");
			tl(2, "pagination.put(\"page", langueConfig.getString(ConfigCles.var_Debut), "\", new JsonObject().put(\"", langueConfig.getString(ConfigCles.var_debut), "\", 0L).put(\"page", langueConfig.getString(ConfigCles.var_Numero), "\", 1L));");
			tl(2, "if(", langueConfig.getString(ConfigCles.var_debut), " > 0L)");
			tl(3, "pagination.put(\"page", langueConfig.getString(ConfigCles.var_Precedent), "\", new JsonObject().put(\"", langueConfig.getString(ConfigCles.var_debut), "\", ", langueConfig.getString(ConfigCles.var_debut), " - ", langueConfig.getString(ConfigCles.var_lignes), ").put(\"page", langueConfig.getString(ConfigCles.var_Numero), "\", ", langueConfig.getString(ConfigCles.var_debut), " - ", langueConfig.getString(ConfigCles.var_lignes), " + 1L));");
			tl(2, "if(", langueConfig.getString(ConfigCles.var_debut), " + ", langueConfig.getString(ConfigCles.var_lignes), " < ", langueConfig.getString(ConfigCles.var_numTrouve), ")");
			tl(3, "pagination.put(\"page", langueConfig.getString(ConfigCles.var_Prochaine), "\", new JsonObject().put(\"", langueConfig.getString(ConfigCles.var_debut), "\", ", langueConfig.getString(ConfigCles.var_debut), " + ", langueConfig.getString(ConfigCles.var_lignes), ").put(\"page", langueConfig.getString(ConfigCles.var_Numero), "\", ", langueConfig.getString(ConfigCles.var_debut), " + ", langueConfig.getString(ConfigCles.var_lignes), " + 1L));");
			tl(2, "pagination.put(\"page", langueConfig.getString(ConfigCles.var_Fin), "\", new JsonObject().put(\"", langueConfig.getString(ConfigCles.var_debut), "\", ", langueConfig.getString(ConfigCles.var_dernier), ").put(\"page", langueConfig.getString(ConfigCles.var_Numero), "\", ", langueConfig.getString(ConfigCles.var_dernier), " + 1L));");
			tl(2, "pagination.put(\"pages\", pages);");
			tl(2, "for(Long i = pagination", langueConfig.getString(ConfigCles.var_Debut), "; i < pagination", langueConfig.getString(ConfigCles.var_Fin), "; i += ", langueConfig.getString(ConfigCles.var_lignes), ") {");
			tl(3, "Long page", langueConfig.getString(ConfigCles.var_Numero), " = Math.floorDiv(i, ", langueConfig.getString(ConfigCles.var_lignes), ") + 1L;");
			tl(3, "JsonObject page = new JsonObject();");
			tl(3, "page.put(\"page", langueConfig.getString(ConfigCles.var_Numero), "\", page", langueConfig.getString(ConfigCles.var_Numero), ");");
			tl(3, "page.put(\"", langueConfig.getString(ConfigCles.var_pageActuel), "\", ", langueConfig.getString(ConfigCles.var_debut), ".equals(i));");
			tl(3, "page.put(\"", langueConfig.getString(ConfigCles.var_debut), "\", i);");
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
			tl(2, classeNomSimple, ".varsQ", langueConfig.getString(ConfigCles.var_PourClasse), "().forEach(var -> {");
			tl(3, "JsonObject json = new JsonObject();");
			tl(3, "json.put(\"var\", var);");
			tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
			tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
			tl(3, "json.put(\"val\", Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getQuery()).filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).map(s -> StringUtils.substringAfter(s, \":\")).orElse(null));");
			tl(3, "vars.put(var, json);");
			tl(2, "});");
			tl(1, "}");

			////////////
			// varsFq //
			////////////

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _varsFq(JsonObject vars) {");
			tl(2, "Map<String, SolrResponse.FacetField> facetFields = Optional.ofNullable(facetCounts).map(c -> c.getFacetFields()).map(f -> f.getFacets()).orElse(new HashMap<String,SolrResponse.FacetField>());");
			tl(2, classeNomSimple, ".varsFq", langueConfig.getString(ConfigCles.var_PourClasse), "().forEach(var -> {");
			tl(3, "String var", langueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", langueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(var);");
			tl(3, "JsonObject json = new JsonObject();");
			tl(3, "json.put(\"var\", var);");
			tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
			tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
			tl(3, "json.put(\"val\", ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getFilterQueries().stream().filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).findFirst().map(s -> StringUtils.substringAfter(s, \":\")).orElse(null));");

			tl(3, "Optional.ofNullable(facetFields.get(var", langueConfig.getString(ConfigCles.var_Indexe), ")).ifPresent(facetField -> {");
			tl(4, "JsonObject facetJson = new JsonObject();");
			tl(4, "JsonObject counts = new JsonObject();");
			tl(4, "facetJson.put(\"var\", var);");
			tl(4, "facetField.getCounts().forEach((val, count) -> {");
			tl(5, "counts.put(val, count);");
			tl(4, "});");
			tl(4, "facetJson.put(\"counts\", counts);");
			tl(4, "json.put(\"facetField\", facetJson);");
			tl(3, "});");

			tl(3, "vars.put(var, json);");
			tl(2, "});");
			tl(1, "}");

			///////////////
			// varsPivot //
			///////////////

			l();
			if(classePageSuperNomSimple != null)
				tl(1, "@Override");
			tl(1, "protected void _varsPivot(JsonObject vars) {");
			tl(2, "Map<String, SolrResponse.Pivot> pivotFields = Optional.ofNullable(facetCounts).map(c -> c.getFacetPivot()).map(f -> f.getPivotMap()).orElse(new HashMap<String,SolrResponse.Pivot>());");
			tl(2, classeNomSimple, ".varsFq", langueConfig.getString(ConfigCles.var_PourClasse), "().forEach(var -> {");
			tl(3, "String var", langueConfig.getString(ConfigCles.var_Indexe), " = ", classeNomSimple, ".var", langueConfig.getString(ConfigCles.var_Indexe), classeNomSimple, "(var);");
			tl(3, "JsonObject json = new JsonObject();");
			tl(3, "json.put(\"var\", var);");
			tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_nomAffichage), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_nomAffichage), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
			tl(3, "json.put(\"", langueConfig.getString(ConfigCles.var_classeNomSimple), "\", Optional.ofNullable(", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_classeNomSimple), classeNomSimple, "(var)).map(d -> StringUtils.isBlank(d) ? var : d).orElse(var));");
			tl(3, "json.put(\"val\", ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getRequest().getFilterQueries().stream().filter(fq -> fq.startsWith(", classeNomSimple, ".varIndexed", classeNomSimple, "(var) + \":\")).findFirst().map(s -> StringUtils.substringAfter(s, \":\")).orElse(null));");

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
			tl(2, "ServiceRequest ", langueConfig.getString(ConfigCles.var_requeteService), " = ", langueConfig.getString(ConfigCles.var_requeteSite), "_.getServiceRequest();");
			tl(2, "JsonObject params = ", langueConfig.getString(ConfigCles.var_requeteService), ".getParams();");
			l();
			tl(2, "JsonObject queryParams = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteService), ").map(ServiceRequest::getParams).map(or -> or.getJsonObject(\"query\")).orElse(new JsonObject());");
			tl(2, "Long num = ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_.getQueryResponse().getResponse().getNumFound().longValue();");
			tl(2, "String q = \"*:*\";");
			tl(2, "String q1 = \"", classeVarTexte, "\";");
			tl(2, "String q2 = \"\";");
			tl(2, "for(String param", langueConfig.getString(ConfigCles.var_Nom), " : queryParams.fieldNames()) {");
			tl(3, "String ", langueConfig.getString(ConfigCles.var_entite), "Var = null;");
			tl(3, "String ", langueConfig.getString(ConfigCles.var_valeur), langueConfig.getString(ConfigCles.var_Indexe), " = null;");
			tl(3, "Object param", langueConfig.getString(ConfigCles.var_ValeursObjet), " = queryParams.getValue(param", langueConfig.getString(ConfigCles.var_Nom), ");");
			tl(3, "JsonArray param", langueConfig.getString(ConfigCles.var_Objets), " = param", langueConfig.getString(ConfigCles.var_ValeursObjet), " instanceof JsonArray ? (JsonArray)param", langueConfig.getString(ConfigCles.var_ValeursObjet), " : new JsonArray().add(param", langueConfig.getString(ConfigCles.var_ValeursObjet), ");");
			l();
			tl(3, "try {");
			tl(4, "for(Object param", langueConfig.getString(ConfigCles.var_Objet), " : param", langueConfig.getString(ConfigCles.var_Objets), ") {");
			tl(5, "switch(param", langueConfig.getString(ConfigCles.var_Nom), ") {");
			tl(5, "case \"q\":");
			tl(6, "q = (String)param", langueConfig.getString(ConfigCles.var_Objet), ";");
			tl(6, langueConfig.getString(ConfigCles.var_entite), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", langueConfig.getString(ConfigCles.var_Objet), ", \":\"));");
			tl(6, langueConfig.getString(ConfigCles.var_valeur), langueConfig.getString(ConfigCles.var_Indexe), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", langueConfig.getString(ConfigCles.var_Objet), ", \":\")), \"UTF-8\");");
			tl(6, "q1 = ", langueConfig.getString(ConfigCles.var_entite), "Var.equals(\"*\") ? q1 : ", langueConfig.getString(ConfigCles.var_entite), "Var;");
			tl(6, "q2 = ", langueConfig.getString(ConfigCles.var_valeur), langueConfig.getString(ConfigCles.var_Indexe), ";");
			tl(6, "q = q1 + \":\" + q2;");
			tl(5, "}");
			tl(4, "}");
			tl(3, "} catch(Exception e) {");
			tl(4, "ExceptionUtils.rethrow(e);");
			tl(3, "}");
			tl(2, "}");
			tl(2, "query.put(\"q\", q);");
			l();
			tl(2, "Long rows1 = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getRows()).orElse(10L);");
			tl(2, "Long start1 = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getStart()).orElse(1L);");
			tl(2, "Long start2 = start1 - rows1;");
			tl(2, "Long start3 = start1 + rows1;");
			tl(2, "Long rows2 = rows1 / 2;");
			tl(2, "Long rows3 = rows1 * 2;");
			tl(2, "start2 = start2 < 0 ? 0 : start2;");
			tl(2, "JsonObject fqs = new JsonObject();");
			tl(2, "for(String fq : Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getFilterQueries()).orElse(Arrays.asList())) {");
			tl(3, "if(!StringUtils.contains(fq, \"(\")) {");
			tl(4, "String fq1 = StringUtils.substringBefore(fq, \"_\");");
			tl(4, "String fq2 = StringUtils.substringAfter(fq, \":\");");
			tl(4, "if(!StringUtils.startsWithAny(fq, \"", langueConfig.getString(ConfigCles.var_classeNomsCanoniques), "_\", \"", langueConfig.getString(ConfigCles.var_archive), "_\", \"", langueConfig.getString(ConfigCles.var_supprime), "_\", \"sessionId\", \"", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s\"))");
			tl(5, "fqs.put(fq1, new JsonObject().put(\"var\", fq1).put(\"val\", fq2).put(\"", langueConfig.getString(ConfigCles.var_nomAffichage), "\", ", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_nomAffichage), langueConfig.getString(ConfigCles.var_PourClasse), "(fq1)));");
			tl(4, "}");
			tl(3, "}");
			tl(2, "query.put(\"fq\", fqs);");
			l();
			tl(2, "JsonArray sorts = new JsonArray();");
			tl(2, "for(String sort : Optional.ofNullable(", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_).map(l -> l.getSorts()).orElse(Arrays.asList())) {");
			tl(3, "String sort1 = StringUtils.substringBefore(sort, \"_\");");
			tl(3, "sorts.add(new JsonObject().put(\"var\", sort1).put(\"order\", StringUtils.substringAfter(sort, \" \")).put(\"", langueConfig.getString(ConfigCles.var_nomAffichage), "\", ", classeNomSimple, ".", langueConfig.getString(ConfigCles.var_nomAffichage), langueConfig.getString(ConfigCles.var_PourClasse), "(sort1)));");
			tl(2, "}");
			tl(2, "query.put(\"sort\", sorts);");
			tl(1, "}");

			l();
			if(classePageSuperNomSimple != null) {
				tl(1, "@Override");
			} else {
				tl(1, "/**");
				tl(1, " * ", langueConfig.getString(ConfigCles.var_Ignorer), ": true");
				tl(1, "**/");
			}
			tl(1, "protected void _promise", langueConfig.getString(ConfigCles.var_Apres), "(Promise<Void> promise) {");
			tl(2, "promise.complete();");
			tl(1, "}");

			if(contexteDescription != null) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pageDescription(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(contexteDescription), ");");
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

			if(contexteImageLargeur != null) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pageImage", langueConfig.getString(ConfigCles.var_Largeur), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
				tl(3, "c.o(", contexteImageLargeur, ");");
				tl(1, "}");
			}

			if(contexteImageHauteur != null) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pageImage", langueConfig.getString(ConfigCles.var_Hauteur), "(", classePartsCouverture.nomSimple(langueNom), "<Integer> c) {");
				tl(3, "c.o(", contexteImageHauteur, ");");
				tl(1, "}");
			}

			if(StringUtils.isNotBlank(contexteVideoId)) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _pageVideoId(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(contexteVideoId), ");");
				tl(1, "}");
			}

			if(StringUtils.isNotBlank(contexteIconeGroupe)) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_contexte), langueConfig.getString(ConfigCles.var_Icone), langueConfig.getString(ConfigCles.var_Groupe), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(contexteIconeGroupe), ");");
				tl(1, "}");
			}

			if(StringUtils.isNotBlank(contexteIconeNom)) {
				l();
				if(classePageSuperNomSimple != null)
					tl(1, "@Override");
				tl(1, "protected void _", langueConfig.getString(ConfigCles.var_contexte), langueConfig.getString(ConfigCles.var_Icone), langueConfig.getString(ConfigCles.var_Nom), "(", classePartsCouverture.nomSimple(langueNom), "<String> c) {");
				tl(3, "c.o(", q(contexteIconeNom), ");");
				tl(1, "}");
			}
//
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

			{
				SolrQuery rechercheSolr = new SolrQuery();   
				rechercheSolr.setQuery("*:*");
				rechercheSolr.setRows(1000000);
				String fqClassesSuperEtMoi = "(" + classeEntiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + this.langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
				rechercheSolr.addFilterQuery("entiteHtmlColonne_indexed_int:[* TO *]");
				rechercheSolr.addSort("entiteHtmlColonne_indexed_int", ORDER.asc);
				QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList rechercheListe = rechercheReponse.getResults();
				Integer rechercheLignes = rechercheSolr.getRows();
				Integer rechercheLigne = -1;
				Integer rechercheLigneActuel;
	
				if(rechercheListe.size() > 0) {
					for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
						for(Integer j = 0; j < rechercheListe.size(); j++) {
							SolrDocument entiteDocumentSolr = rechercheListe.get(j);
							String entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNom + "_stored_string");
							String entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + langueNom + "_stored_string");
							String entiteSolrNomSimple = (String)entiteDocumentSolr.get("entiteSolrNomSimple_stored_string");
							String entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + langueNom + "_stored_string");
							String entiteNomCanonique = (String)entiteDocumentSolr.get("entiteNomCanonique_" + langueNom + "_stored_string");
							String entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
							String entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
							String entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
							String entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
							Boolean entiteHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean"));
							String entiteFormatHtm = (String)entiteDocumentSolr.get("entiteFormatHtm_stored_string");
							Boolean entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
							Boolean entiteHighlighting = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHighlighting_stored_boolean"));
							Boolean entiteVarTitre = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteVarTitre_stored_boolean"));
							Boolean entiteFacetsTrouves = Optional.ofNullable((Boolean)entiteDocumentSolr.get("entiteFacetsTrouves_stored_boolean")).orElse(false);
							List<String> entiteFacets = Optional.ofNullable((List<String>)entiteDocumentSolr.get("entiteFacets_stored_strings")).orElse(Arrays.asList());
							if(entiteHtml) {
								String jsVal = ".val()";
								if("Boolean".equals(entiteNomSimple)) {
									jsVal = ".prop('checked')";
								}
//	
//								wGetters.l();
//								wGetters.tl(1, "public Boolean get", langueConfig.getString(ConfigCles.var_Colonne), entiteVarCapitalise, "() {");
//								wGetters.tl(2, "return true;");
//								wGetters.tl(1, "}");

								wTh.tl(4, "<th>", entiteNomAffichage, "</th>");
	
//								wTd.tl(4, "{{#if get", langueConfig.getString(ConfigCles.var_Colonne), entiteVarCapitalise, "}}");
								wTd.tl(5, "<td>");
								wTd.tl(6, "<a href=\"{{", classeVarUrlPk, "}}\">");
								if(contexteIconeGroupe != null && contexteIconeNom != null && BooleanUtils.isTrue(entiteVarTitre))
									wTd.tl(7, "<i class=\"fa", StringUtils.substring(contexteIconeGroupe, 0, 1), " fa-", contexteIconeNom, " \"></i>");
								wTd.t(7, "<span class=\"white-space-pre-wrap \">");
								if(StringUtils.equals(entiteNomCanonique, ZonedDateTime.class.getCanonicalName())) {
									wTd.s("{{siteZonedDateTimeFormat ", entiteVar, " \"", entiteFormatHtm, "\" siteLocale}}");
								} else if(StringUtils.equals(entiteNomCanonique, LocalDate.class.getCanonicalName())) {
									wTd.s("{{siteLocalDateFormat ", entiteVar, " \"", entiteFormatHtm, "\" siteLocale}}");
								} else if(StringUtils.equals(entiteNomCanonique, LocalDateTime.class.getCanonicalName())) {
									wTd.s("{{siteLocalDateTimeFormat ", entiteVar, " \"", entiteFormatHtm, "\" siteLocale}}");
								} else if(StringUtils.equals(entiteNomCanonique, LocalTime.class.getCanonicalName())) {
									wTd.s("{{siteLocalTimeFormat ", entiteVar, " \"", entiteFormatHtm, "\" siteLocale}}");
								} else if(StringUtils.equals(entiteNomCanonique, BigDecimal.class.getCanonicalName())) {
									wTd.s("{{siteNumberFormat ", entiteVar, " \"", entiteFormatHtm, "\" siteLocale}}");
								} else {
									wTd.s("{{", entiteVar, "}}");
								}
								wTd.l("</span>");
								wTd.tl(6, "</a>");
								if(entiteHighlighting) {
									wTd.tl(6, "{{#if highlightList}}");
									wTd.tl(7, "<div class=\"site-highlight \">");
										wTd.tl(8, "StringUtils.join(highlightList, \" ... \")");
									wTd.t(7).bgl("</div>");
									wTd.tl(6, "{{/if}}");
								}
								wTd.tl(5, "</td>");
//								wTd.tl(4, "{{/if}}");
							}

							wFoot.tl(3, "{{#if get", langueConfig.getString(ConfigCles.var_Colonne), entiteVarCapitalise, "}}");
							wFoot.tl(4, "<td>");
							if(entiteFacetsTrouves) {
								for(String entiteFacet : entiteFacets) {
									if("sum".equals(entiteFacet)) {
		
										if("Double".equals(entiteSolrNomSimple))
											wFoot.tl(4, "BigDecimal ", entiteFacet, "_", entiteVar, " = Optional.ofNullable((", entiteSolrNomSimple, ")facets.get(\"", entiteFacet, "_", entiteVar, "\")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP));");
										else
											wFoot.tl(4, entiteSolrNomSimple, " ", entiteFacet, "_", entiteVar, " = Optional.ofNullable((", entiteSolrNomSimple, ")facets.get(\"", entiteFacet, "_", entiteVar, "\")).orElse(new ", entiteSolrNomSimple, "(0));");

										wFoot.tl(4, "<span class=\"\"font-weight-bold \">", entiteFacet, "_", entiteVar, "</span>");
									}
								}
							}
							wFoot.tl(4, "</td>");
							wFoot.tl(3, "{{/if}}");
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
				String fqClassesSuperEtMoi = "(" + classeEntiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
				rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
				rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
				rechercheSolr.addSort("entiteHtmlLigne_indexed_int", ORDER.asc);
				rechercheSolr.addSort("entiteHtmlCellule_indexed_int", ORDER.asc);
				QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
				SolrDocumentList rechercheListe = rechercheReponse.getResults();
				Integer rechercheLignes = rechercheSolr.getRows();
				Integer rechercheLigne = -1;
				Integer rechercheLigneActuel;
	
				if(rechercheListe.size() > 0) {
					for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
						for(Integer j = 0; j < rechercheListe.size(); j++) {
							entiteDocumentSolr = rechercheListe.get(j);
							entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNom + "_stored_string");
							entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + langueNom + "_stored_string");
							entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + langueNom + "_stored_string");
							entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
							entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
							entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
							entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
							entiteHtml = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean"));
							entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
							entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
							entiteSuggere = (Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean");
							entiteNomSimpleVertxJson = (String)entiteDocumentSolr.get("entiteNomSimpleVertxJson_stored_string");
							entiteAttribuer = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteAttribuer_stored_boolean"));
							entiteListeTypeJson = (String)entiteDocumentSolr.get("entiteListeTypeJson_stored_string");

//									String jsVal = ".val()";

							if(entiteIndexe) {
	
								wRecherche.l();
								if("Boolean".equals(entiteNomSimple)) {
									wRecherche.tl(2, "var $", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "Checkbox = $", langueConfig.getString(ConfigCles.var_formulaireFiltres), ".find('input.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "[type = \"checkbox\"]');");
									wRecherche.tl(2, "var $", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "Select = $", langueConfig.getString(ConfigCles.var_formulaireFiltres), ".find('select.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "');");
									wRecherche.tl(2, "var ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "Select.length ? $", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "Select.val() : $", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "Checkbox.prop('checked');");

									wRecherche.tl(2, "var ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "SelectVal = $", langueConfig.getString(ConfigCles.var_formulaireFiltres), ".find('select.", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "').val();");
									wRecherche.tl(2, "var ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " = null;");
									wRecherche.tl(2, "if(", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "SelectVal !== '')");
									wRecherche.tl(3, langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " = ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, "SelectVal == 'true';");
								}
								else {
									wRecherche.tl(2, "var ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireFiltres), ".find('.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "').val();");
								}

								if("Boolean".equals(entiteNomSimple))
									wRecherche.tl(2, "if(", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " === true)");
								else
									wRecherche.tl(2, "if(", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " !== '')");

								wRecherche.tl(3, langueConfig.getString(ConfigCles.var_filtres), ".push({ name: '", (entiteSuggere ? "q" : "fq"), "', value: '", entiteVar, ":' + ", langueConfig.getString(ConfigCles.var_filtre), entiteVarCapitalise, " });");
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
										wPOST.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "').val();");
										wPOST.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " !== '')");
										if("Boolean".equals(entiteNomSimple)) {
											wPOST.tl(2, "vals['", entiteVar, "'] = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " == 'true';");
										} else {
											wPOST.tl(2, "vals['", entiteVar, "'] = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ";");
										}
									}
									else {
										wPOST.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = [];");
										wPOST.tl(1, "$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('input.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ":checked').each(function(index) {");
										wPOST.tl(2, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ".push($(this).val());");
										wPOST.tl(1, "});");
										wPOST.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ".length > 0)");
										wPOST.tl(2, "vals['", entiteVar, "'] = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ";");
									}
								}
								else {
									wPOST.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "').val();");
									wPOST.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " !== '')");
									if("Boolean".equals(entiteNomSimple)) {
										wPOST.tl(2, "vals['", entiteVar, "'] = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " == 'true';");
									} else {
										wPOST.tl(2, "vals['", entiteVar, "'] = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ";");
									}
								}
	
								wPUTCopie.l();
								if(entiteAttribuer)
									wPUTCopie.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('input.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ":checked').val();");
								else
									wPUTCopie.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "').val();");
								if(entiteAttribuer) {
									wPUTCopie.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, langueConfig.getString(ConfigCles.var_Vider), " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('input.", entiteVar, "_", langueConfig.getString(ConfigCles.var_vider), ":checked').val();");
									wPUTCopie.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, langueConfig.getString(ConfigCles.var_Vider), " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, langueConfig.getString(ConfigCles.var_Vider), ")");
									wPUTCopie.tl(2, "vals['", entiteVar, "'] = null;");
									wPUTCopie.tl(1, "else if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ")");
									if(entiteListeTypeJson == null) {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									}
									else {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = [", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, "];");
									}
								} else {
									wPUTCopie.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " !== '')");
									if("Boolean".equals(entiteNomSimple)) {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, " == 'true';");
									} else {
										wPUTCopie.tl(2, "vals['", entiteVar, "'] = ", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									}
								}
	
								wPATCH.l();
								if(entiteAttribuer)
									wPATCH.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('input.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ":checked').val();");
								else
									wPATCH.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "').val();");
								if(entiteAttribuer) {
									wPATCH.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " !== '')");
									if(entiteListeTypeJson == null) {
										wPATCH.tl(2, "vals['set", entiteVarCapitalise, "'] = ", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									}
									else {
										wPATCH.tl(2, "vals['add", entiteVarCapitalise, "'] = ", valPrefixe, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, valSuffixe, ";");
									}
								} else {

									wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.remove", entiteVarCapitalise, "').val() === 'true';");

									if("Boolean".equals(entiteNomSimple)) {
										wPATCH.tl(1, "var ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "SelectVal = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('select.set", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "if(", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "SelectVal != null && ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "SelectVal !== '')");
										wPATCH.tl(2, langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " = ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, "SelectVal == 'true';");
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : ", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, ";");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.add", entiteVarCapitalise, "').prop('checked');");
									}
									else if("LocalDate".equals(entiteNomSimple)) {
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.set", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.add", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "var setMoment = set", entiteVarCapitalise, valSuffixe, " ? moment(", valPrefixe, "set", entiteVarCapitalise, valSuffixe, ", '", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "') : null; ");
										wPATCH.tl(1, "var addMoment = add", entiteVarCapitalise, valSuffixe, " ? moment(", valPrefixe, "add", entiteVarCapitalise, valSuffixe, ", '", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "') : null; ");
										wPATCH.tl(1, "if(setMoment) { ");
											wPATCH.tl(2, "var s = setMoment.format('YYYY-MM-DD'); ");
											wPATCH.tl(2, "set", entiteVarCapitalise, " = s;");
										wPATCH.tl(1, "} ");
										wPATCH.tl(1, "if(addMoment) { ");
											wPATCH.tl(2, "var s = addMoment.format('YYYY-MM-DD'); ");
											wPATCH.tl(2, "add", entiteVarCapitalise, " = s;");
										wPATCH.tl(1, "} ");
									}
//												else if("LocalDateTime".equals(entiteNomSimple) || "ZonedDateTime".equals(entiteNomSimple)) {
//													t(tIndex + 3).s(classePrefixe, "<input").l();
//													t(tIndex + 5).dal("type", "text");
//													t(tIndex + 5).s(classePrefixe).s(" class=\"\"w3-input w3-border datepicker set", entiteVarCapitalise, " class", classeApiClasseNomSimple, " input", classeApiClasseNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\"");
//													t(tIndex + 5).dal("placeholder", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY_HHColonMM));
//													t(tIndex + 5).dal("data-timeformat", langueConfig.getString(ConfigCles.var_ddDashMMDashyyyy));
//													t(tIndex + 5).l(" id=\"", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "\"");
//													if(entiteDescription != null)
//														t(tIndex + 5).dal("title", entiteDescription + " (", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), ")");
//													tl(tIndex + 4, " value=\"", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_EEE_d_MMM_yyyy_HAposhAposmmColonss_zz_VV), "\".format(", entiteVar, "));");
//													t(tIndex + 3).l("if(\"Page\".equals(", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ")) {");
//													t(tIndex + 4).l("a(\"onclick\", \"", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); \");");
//													t(tIndex + 4).s("a(\"onchange\", \"");
//														s("var t = moment(this.value, '", langueConfig.getString(ConfigCles.var_DDDashMMDashYYYY), "'); ");
//														s("if(t) { ");
//															s("var s = t.format('YYYY-MM-DD'); ");
//															s("patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeVarClePrimaire, ", \"' }], 'set", entiteVarCapitalise, "', s, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#\", ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#\", ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "')); }); ");
//														s("} ");
//													l("\");");
//													t(tIndex + 3).l("}");
//													tl(tIndex + 3, "/>");
//												}
//												else if("LocalTime".equals(entiteNomSimple)) {
//													t(tIndex + 3).s(classePrefixe, "<input").l();
//													t(tIndex + 5).dal("type", "text");
//													t(tIndex + 5).s(classePrefixe).s(" class=\"\"w3-input w3-border timepicker set", entiteVarCapitalise, " class", classeApiClasseNomSimple, " input", classeApiClasseNomSimple, "\", ", classeVarClePrimaire, ", \"", entiteVarCapitalise, " w3-input w3-border ").l("\"");
//													t(tIndex + 5).dal("placeholder", langueConfig.getString(ConfigCles.var_HHColonMM));
//													t(tIndex + 5).l(" id=\"", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "\"");
//													if(entiteDescription != null)
//														t(tIndex + 5).da("title", entiteDescription + " (", langueConfig.getString(ConfigCles.var_HAposhAposmm), ")");
//													tl(tIndex + 5, " value=\"", entiteVar, " == null ? \"\" : DateTimeFormatter.ofPattern(\"", langueConfig.getString(ConfigCles.var_HAposhAposmm), "\".format(", entiteVar, "));");
//													t(tIndex + 3).l("if(\"Page\".equals(", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ")) {");
//													t(tIndex + 4).l("a(\"onclick\", \"", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); \");");
//													t(tIndex + 4).s("a(\"onchange\", \"");
//														s("var t = moment(this.value, '", langueConfig.getString(ConfigCles.var_HAposhAposmm), "'); ");
//														s("if(t) { ");
//															s("var s = t.format('HH:mm'); ");
//															s("patch\", getClass().getSimpleName(), \"Val([{ name: 'fq', value: 'pk:\", ", classeVarClePrimaire, ", \"' }], 'set", entiteVarCapitalise, "', s, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#\", ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#\", ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), ", \"_", entiteVar, "')); }); ");
//														s("} ");
//													l("\");");
//													t(tIndex + 3).l("}");
//													tl(tIndex + 3, "/>");
//												}
									else {
										wPATCH.tl(1, "var set", entiteVarCapitalise, " = remove", entiteVarCapitalise, " ? null : $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.set", entiteVarCapitalise, "').val();");
										wPATCH.tl(1, "var add", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.add", entiteVarCapitalise, "').val();");
									}
									wPATCH.tl(1, "if(remove", entiteVarCapitalise, " || set", entiteVarCapitalise, " != null && set", entiteVarCapitalise, " !== '')");
									wPATCH.tl(2, "vals['set", entiteVarCapitalise, "'] = ", valPrefixe, "set", entiteVarCapitalise, valSuffixe, ";");
									wPATCH.tl(1, "if(add", entiteVarCapitalise, " != null && add", entiteVarCapitalise, " !== '')");
									wPATCH.tl(2, "vals['add", entiteVarCapitalise, "'] = ", valPrefixe, "add", entiteVarCapitalise, valSuffixe, ";");
									if("Boolean".equals(entiteNomSimple)) {
										wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.remove", entiteVarCapitalise, "').prop('checked');");
									} else {
										wPATCH.tl(1, "var remove", entiteVarCapitalise, " = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.remove", entiteVarCapitalise, "').val();");
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

			if(auteurPageHbs != null) {
				o = auteurPageHbs;
				if(classeEtendBase && auteurPageHbs != null) {
					String hackathonMission = (String)classeDoc.get("hackathonMissionPageHbs_stored_string");
					String hackathonColumn = (String)classeDoc.get("hackathonColumnPageHbs_stored_string");
					String hackathonLabels = (String)classeDoc.get("hackathonLabelsPageHbs_stored_string");
					if(hackathonMission != null || hackathonColumn != null || hackathonLabels != null) {
						l("<!--");
						if(hackathonMission != null)
							l(String.format("hackathonMission: %s", hackathonMission));
						if(hackathonColumn != null)
							l(String.format("hackathonColumn: %s", hackathonColumn));
						if(hackathonLabels != null)
							l(String.format("hackathonLabels: %s", hackathonLabels));
						l("-->");
					}
					l("{{#partial \"htmHead\"}}{{> htmHead", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmTitle\"}}{{> htmTitle", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmMeta\"}}{{> htmMeta", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmStyle\"}}{{> htmStyle", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmScripts\"}}{{> htmScripts", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmScript\"}}{{> htmScript", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Debut), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Debut), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Fin), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Fin), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody\"}}{{> htmBody", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Recherche), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Recherche), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Filtres), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Filtres), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Gamme), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Gamme), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Pivot), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Pivot), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBodyCount0\"}}{{> htmBodyCount0", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBodyCount1", langueConfig.getString(ConfigCles.var_Tous), "\"}}{{> htmBodyCount1", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBodyCount1\"}}{{> htmBodyCount1", classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Tous), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "\"}}{{> htm", langueConfig.getString(ConfigCles.var_Formulaire), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htm", langueConfig.getString(ConfigCles.var_Formulaires), "\"}}{{> htm", langueConfig.getString(ConfigCles.var_Formulaires), classePageNomSimple, "}}{{/partial}}");
					l("{{#partial \"htmBody", langueConfig.getString(ConfigCles.var_Suggere), "\"}}{{> htmBody", langueConfig.getString(ConfigCles.var_Suggere), classePageNomSimple, "}}{{/partial}}");
					for(String classeApiMethode : classeApiMethodes) {
						String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
						String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");
		
						if(classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PageRecherche)) || classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTCopie)) || classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
							l("{{#partial \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "_", classeApiOperationIdMethode, "\"}}{{> htm", langueConfig.getString(ConfigCles.var_Formulaire), classePageNomSimple, "_", classeApiOperationIdMethode, "}}{{/partial}}");
						}
					}
				}
				l("{{> ", classeGenPageNomSimple, "}}");
			}

			o = auteurGenPageHbs;

			{
				String hackathonMission = (String)classeDoc.get("hackathonMissionGenPageHbs_stored_string");
				String hackathonColumn = (String)classeDoc.get("hackathonColumnGenPageHbs_stored_string");
				String hackathonLabels = (String)classeDoc.get("hackathonLabelsGenPageHbs_stored_string");
				if(hackathonMission != null || hackathonColumn != null || hackathonLabels != null) {
					l("<!--");
					if(hackathonMission != null)
						l(String.format("hackathonMission: %s", hackathonMission));
					if(hackathonColumn != null)
						l(String.format("hackathonColumn: %s", hackathonColumn));
					if(hackathonLabels != null)
						l(String.format("hackathonLabels: %s", hackathonLabels));
					l("-->");
				}
			}
			t(0, "{{#*inline \"htmTitle", classePageNomSimple, "\"}}");
			tl(2, "<!-- inline \"htmTitle", classePageNomSimple, "\" -->");
			t(2, "<title>");
				s("{{#if ", langueConfig.getString(ConfigCles.var_listeRecherche), classeApiClasseNomSimple, "_}}");
				s("{{#eq ", uncapitalizeClasseApiClasseNomSimple, "Count int1}}");
				s("{{#eq params.query.q \"*:*\"}}");
				s(contexteNomAdjectifSingulier);
				s("{{else}}");
				s(contexteNomAdjectifSingulier);
				s("{{/eq}}");
				s("{{else}}");
				s(contexteAucunNomTrouve);
				s("{{/eq}}");
				s("{{else}}");
				s(contexteNomAdjectifPluriel);
				s("{{/if}}");
			l("</title>");
			l("{{/inline}}");

			t(0, "{{#*inline \"htmMeta", classePageNomSimple, "\"}}");
			s("{{> \"htmMeta", classePageSuperNomSimple, "\"}}");
			l("{{/inline}}");

			t(0, "{{#*inline \"htmStyle", classePageNomSimple, "\"}}");
			s("{{> \"htmStyle", classePageSuperNomSimple, "\"}}");
			l("{{/inline}}");

			t(0, "{{#*inline \"htmScripts", classePageNomSimple, "\"}}");
			s("{{> \"htmScripts", classePageSuperNomSimple, "\"}}");
			tl(2, "<!-- inline \"htmScripts", classePageNomSimple, "\" -->");
			tl(2, "<script src=\"{{", langueConfig.getString(ConfigCles.var_statiqueUrlBase), "}}/js/", langueNom, "/", classePageNomSimple, ".js\"></script>");
			if(classeAttribuerNomSimplePages != null) {
				for(String classeAttribuerNomSimplePage : classeAttribuerNomSimplePages) {
					t(2).l("<script src=\"{{", langueConfig.getString(ConfigCles.var_statiqueUrlBase), "}}/js/", langueNom, "/", classeAttribuerNomSimplePage, ".js\"></script>");
				}
			}
			tl(0, "{{/inline}}");

			if(!classePageSimple) {

				String hackathonMission = (String)classeDoc.get("hackathonMissionPageJs_stored_string");
				String hackathonColumn = (String)classeDoc.get("hackathonColumnPageJs_stored_string");
				String hackathonLabels = (String)classeDoc.get("hackathonLabelsPageJs_stored_string");
				if(hackathonMission != null || hackathonColumn != null || hackathonLabels != null) {
					if(hackathonMission != null)
						auteurPageJs.l("// ", String.format("hackathonMission: %s", hackathonMission));
					if(hackathonColumn != null)
						auteurPageJs.l("// ", String.format("hackathonColumn: %s", hackathonColumn));
					if(hackathonLabels != null)
						auteurPageJs.l("// ", String.format("hackathonLabels: %s", hackathonLabels));
				}

				t(0, "{{#*inline \"htmScript", classePageNomSimple, "\"}}");
//				s("{{> \"htmScript", classePageSuperNomSimple, "\"}}");
				tl(2, "<!-- inline \"htmScript", classePageNomSimple, "\" -->");
				tl(2, "<script>");
				for(String classeApiMethode : classeApiMethodes) {
					String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
					String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
					String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
					String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");
					List<String> classeTrisVar = (List<String>)classeDoc.get("classeTrisVar_" + langueNom + "_stored_strings");
	
					if("application/json".equals(classeApiTypeMediaMethode)) {
						Boolean methodePOST = classeApiMethodeMethode.equals("POST");
						Boolean methodeGET = classeApiMethode.contains("GET");
						Boolean methodePUTImport = classeApiMethode.equals("PUTImport");
						Boolean methodePUTFusion = classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTFusion));
						Boolean methodePUTCopie = classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTCopie));
						Boolean methodePATCH = classeApiMethodeMethode.equals("PATCH");
						Boolean methodeDELETE = classeApiMethodeMethode.equals("DELETE");
						Boolean methodeRecherche = classeApiMethode.contains(langueConfig.getString(ConfigCles.var_Recherche));
						auteurPageJs.l();
						auteurPageJs.tl(0, "// ", classeApiMethode, " //");
						auteurPageJs.l();
//							auteurPageJs.l("/**");
//							if(methodePATCH) {
//							auteurPageJs.l(" * Modifier un ou plusiers ", contexteNomPluriel, " sans valuers qui change, ");
//							auteurPageJs.l(" * ou changer des valeurs pour un ou plusiers ", contexteLeNom, ". ");
//							auteurPageJs.l(" * @param params: [ \"q=*:*\", \"fq=", classeVarClePrimaire, ":1\", \"sort=", classeVarClePrimaire, " asc\", \"rows=1\", \"fl=", classeVarClePrimaire, "\" ]");
//							auteurPageJs.l(" *        Une liste des opérations de recherche sur des ", contexteNomPluriel, " ");
//							auteurPageJs.l(" *        pour rechercher \"q=*:*\", filtrer \"fq=", classeVarClePrimaire, ":1\", trier \"sort=", classeVarClePrimaire, " desc\", ");
//							auteurPageJs.l(" *        limiter les résultats \"rows=1\", ou limiter les valeurs \"fl=", classeVarClePrimaire, "\". ");
//							auteurPageJs.l(" * @param valeurs Noms des champs et valeurs à changer selon les filtres fq. ");
//							auteurPageJs.l(" *           Example: { ", classeVarClePrimaire, ": 1 }");
//							}
//							auteurPageJs.l(" */");
						auteurPageJs.t(0, "async function ", classeApiOperationIdMethode, "(");
						if(methodePOST) {
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireValeurs));
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						}
						else if(methodePUTImport) {
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ", ", classeVarClePrimaire, ", success, error");
						}
						else if(methodePUTFusion) {
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ", ", classeVarClePrimaire, ", success, error");
						}
						else if(methodePUTCopie) {
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ", ", classeVarClePrimaire, ", success, error");
						}
						else if(methodePATCH)
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireFiltres), ", $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ", ", classeVarClePrimaire, ", success, error");
						else if(methodeRecherche) {
							auteurPageJs.s("$", langueConfig.getString(ConfigCles.var_formulaireFiltres), "");
							auteurPageJs.s(", success");
							auteurPageJs.s(", error");
						}
						else if(methodeGET || methodeDELETE)
							auteurPageJs.s(classeVarClePrimaire);
	
						auteurPageJs.l(") {");
						if(methodePOST) {
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.tl(1, "if(success == null) {");
							auteurPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {");
							auteurPageJs.tl(3, langueConfig.getString(ConfigCles.var_ajouterLueur), "($", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".next('button'));");
							if(classeVarUrlPk != null) {
								auteurPageJs.tl(3, "var url = data['", classeVarUrlPk, "'];");
								auteurPageJs.tl(3, "if(url)");
								auteurPageJs.tl(4, "window.location.href = url;");
							}
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.tl(1, "if(error == null) {");
							auteurPageJs.tl(2, "error = function( jqXhr, textStatus, errorThrown ) {");
							auteurPageJs.tl(3, langueConfig.getString(ConfigCles.var_ajouterErreur), "($", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".next('button'));");
							auteurPageJs.tl(2, "};");
							auteurPageJs.tl(1, "}");
							auteurPageJs.s(wPOST);
							auteurPageJs.l();
						}
						else if(methodePUTImport) {
							auteurPageJs.tl(1, "var json = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", "PUTImport", "_", langueConfig.getString(ConfigCles.var_listeRecherche), "').val();");
							auteurPageJs.tl(1, "if(json != null && json !== '')");
							auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(JSON.parse(json), success, error);");
						}
						else if(methodePUTFusion) {
							auteurPageJs.tl(1, "var json = $", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.", langueConfig.getString(ConfigCles.var_PUTFusion), "_", langueConfig.getString(ConfigCles.var_listeRecherche), "').val();");
							auteurPageJs.tl(1, "if(json != null && json !== '')");
							auteurPageJs.tl(2, classeApiOperationIdMethode, "Vals(JSON.parse(json), success, error);");
						}
						else if(methodePUTCopie) {
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.s(wPUTCopie);
							auteurPageJs.l();
						}
						else if(methodePATCH) {
							auteurPageJs.tl(1, "var ", langueConfig.getString(ConfigCles.var_filtres), " = ", classeApiOperationIdMethode,langueConfig.getString(ConfigCles.var_Filtres), "($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ");");
							auteurPageJs.l();
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.s(wPATCH);
							auteurPageJs.l();
						}
						else if(methodeRecherche) {
							auteurPageJs.tl(1, "var ", langueConfig.getString(ConfigCles.var_filtres), " = ", classeApiOperationIdMethode,langueConfig.getString(ConfigCles.var_Filtres), "($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ");");
							auteurPageJs.tl(1, "if(success == null)");
							auteurPageJs.tl(2, "success = function( data, textStatus, jQxhr ) {};");
							auteurPageJs.tl(1, "if(error == null)");
							auteurPageJs.tl(2, "error = function( jqXhr, textStatus, errorThrown ) {};");
							auteurPageJs.l();
						}
	
						if(methodePATCH) {
							auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", classeVarClePrimaire, " == null ? $.deparam(window.location.search ? window.location.search.substring(1) : window.location.search) : [{name:'fq', value:'", classeVarClePrimaire, ":' + ", classeVarClePrimaire, "}], vals, success, error);");
						}
						else if(methodePUTImport) {
						}
						else if(methodePUTFusion) {
						}
						else if(methodePUTCopie) {
							auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", classeVarClePrimaire, " == null ? $.deparam(window.location.search ? window.location.search.substring(1) : window.location.search) : [{name:'fq', value:'", classeVarClePrimaire, ":' + ", classeVarClePrimaire, "}], vals, success, error);");
						}
						else if(methodeRecherche) {
							auteurPageJs.tl(1, classeApiOperationIdMethode, "Vals(", langueConfig.getString(ConfigCles.var_filtres), ", success, error);");
						}
						else {
							auteurPageJs.tl(1, "$.ajax({");
		
							if(methodeGET || methodeDELETE || methodePUTCopie)
								auteurPageJs.tl(2, "url: '", StringUtils.replace(classeApiUriMethode, "{id}", "' + id"));
							else if(methodePATCH || methodeRecherche)
								auteurPageJs.tl(2, "url: '", classeApiUriMethode, "?' + $.param(", langueConfig.getString(ConfigCles.var_filtres), ")");
							else
								auteurPageJs.tl(2, "url: '", classeApiUriMethode, "'");
		
							auteurPageJs.tl(2, ", dataType: 'json'");
							auteurPageJs.tl(2, ", type: '", classeApiMethodeMethode, "'");
							auteurPageJs.tl(2, ", contentType: 'application/json; charset=utf-8'");
							if(!"GET".equals(classeApiMethodeMethode) || "DELETE".equals(classeApiMethodeMethode))
								auteurPageJs.tl(2, ", data: JSON.stringify(vals)");
							auteurPageJs.tl(2, ", success: success");
							auteurPageJs.tl(2, ", error: error");
//								auteurPageJs.tl(2, ", success: function( data, textStatus, jQxhr ) {");
//								auteurPageJs.tl(3, "$.each( vals, function( key, value ) {");
//								auteurPageJs.tl(4, "$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.' + key).removeClass('", langueConfig.getString(ConfigCles.var_lueur), langueConfig.getString(ConfigCles.var_Erreur), "');");
//								auteurPageJs.tl(4, "$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.' + key).addClass('", langueConfig.getString(ConfigCles.var_lueur), langueConfig.getString(ConfigCles.var_Succes), "');");
//								auteurPageJs.tl(3, "});");
//								auteurPageJs.tl(2, "}");
//								auteurPageJs.tl(2, ", error: function( jqXhr, textStatus, errorThrown ) {");
//								auteurPageJs.tl(3, "$.each( vals, function( key, value ) {");
//								auteurPageJs.tl(4, "$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.' + key).removeClass('", langueConfig.getString(ConfigCles.var_lueur), langueConfig.getString(ConfigCles.var_Succes), "');");
//								auteurPageJs.tl(4, "$", langueConfig.getString(ConfigCles.var_formulaireValeurs), ".find('.' + key).addClass('", langueConfig.getString(ConfigCles.var_lueur), langueConfig.getString(ConfigCles.var_Erreur), "');");
//								auteurPageJs.tl(3, "});");
//								auteurPageJs.tl(2, "}");
							auteurPageJs.tl(1, "});");
						}
						auteurPageJs.l("}");

						if(methodePATCH || methodeRecherche) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, langueConfig.getString(ConfigCles.var_Filtres), "($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ") {");
							auteurPageJs.tl(1, "var ", langueConfig.getString(ConfigCles.var_filtres), " = [];");
							auteurPageJs.tl(1, "if($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ") {");
							if(methodePATCH)
								auteurPageJs.tl(2, langueConfig.getString(ConfigCles.var_filtres), ".push({ name: 'softCommit', value: 'true' });");
							auteurPageJs.s(wRecherche);
							auteurPageJs.tl(1, "}");
							auteurPageJs.tl(1, "return ", langueConfig.getString(ConfigCles.var_filtres), ";");
							auteurPageJs.tl(0, "}");
						}
						if(methodePATCH) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Val(", langueConfig.getString(ConfigCles.var_filtres), ", v, val, success, error) {");
							auteurPageJs.tl(1, "var vals = {};");
							auteurPageJs.tl(1, "vals[v] = val;");
							auteurPageJs.tl(1, "", classeApiOperationIdMethode, "Vals(", langueConfig.getString(ConfigCles.var_filtres), ", vals, success, error);");
							auteurPageJs.l("}"); 
						}
						if(methodeRecherche) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(", langueConfig.getString(ConfigCles.var_filtres), ", success, error) {");
							if(contexteRows != null) {
								auteurPageJs.l();
//										auteurPageJs.tl(1, langueConfig.getString(ConfigCles.var_filtres), ".push({ name: 'rows', value: ", contexteRows, " });");
							}
							if(classeTrisVar != null) {
								auteurPageJs.l();
								for(Integer i = 0; i < classeTrisVar.size(); i++) {
									String classeTriVar = classeTrisVar.get(i);
									String classeTriOrdre = classeTrisOrdre.get(i);

									auteurPageJs.tl(1, langueConfig.getString(ConfigCles.var_filtres), ".push({ name: '", "sort", "', value: '", classeTriVar, " ", classeTriOrdre, "' });");
								}
							}
							auteurPageJs.tl(1, "$.ajax({");
							auteurPageJs.tl(2, "url: '", classeApiUriMethode, "?' + $.param(", langueConfig.getString(ConfigCles.var_filtres), ")");
						}
						if(methodePATCH || methodePUTCopie) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(", langueConfig.getString(ConfigCles.var_filtres), ", vals, success, error) {");
							auteurPageJs.tl(1, "$.ajax({");
							auteurPageJs.tl(2, "url: '", classeApiUriMethode, "?' + $.param(", langueConfig.getString(ConfigCles.var_filtres), ")");
						}
						if(methodePUTImport || methodePUTFusion) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(json, success, error) {");
							auteurPageJs.tl(1, "$.ajax({");
							auteurPageJs.tl(2, "url: '", classeApiUriMethode, "'");
						}
						if(methodePOST) {
							auteurPageJs.l();
							auteurPageJs.tl(0, "function ", classeApiOperationIdMethode, "Vals(vals, success, error) {");
							auteurPageJs.tl(1, "$.ajax({");
							auteurPageJs.tl(2, "url: '", classeApiUriMethode, "'");
						}
						if(methodePATCH || methodePUTCopie || methodePUTImport || methodePUTFusion || methodePOST || methodeRecherche) {
							auteurPageJs.tl(2, ", dataType: 'json'");
							auteurPageJs.tl(2, ", type: '", classeApiMethodeMethode, "'");
							auteurPageJs.tl(2, ", contentType: 'application/json; charset=utf-8'");
							if(methodePATCH || methodePOST) {
								auteurPageJs.tl(2, ", data: JSON.stringify(vals)");
							}
							if(methodePUTCopie) {
								auteurPageJs.tl(2, ", data: JSON.stringify({patch: vals})");
							}
							if(methodePUTImport || methodePUTFusion) {
								auteurPageJs.tl(2, ", data: JSON.stringify(json)");
							}
							auteurPageJs.tl(2, ", success: success");
							auteurPageJs.tl(2, ", error: error");
							auteurPageJs.tl(1, "});");
							auteurPageJs.l("}");
						}
						if(methodeRecherche) {

							SolrQuery rechercheSolr = new SolrQuery();   
							rechercheSolr.setQuery("*:*");
							rechercheSolr.setRows(1000000);
							String fqClassesSuperEtMoi = "(" + classeEntiteClassesSuperEtMoiSansGen.stream().map(c -> ClientUtils.escapeQueryChars(c)).collect(Collectors.joining(" OR ")) + ")";
							rechercheSolr.addFilterQuery("partEstEntite_indexed_boolean:true");
							rechercheSolr.addFilterQuery("classeNomCanonique_" + langueNomActuel + "_indexed_string:" + fqClassesSuperEtMoi);
							rechercheSolr.addFilterQuery("(entiteSuggere_indexed_boolean:true OR entiteAttribuer_indexed_boolean:true)");
							QueryResponse rechercheReponse = clientSolrComputate.query(rechercheSolr);
							SolrDocumentList rechercheListe = rechercheReponse.getResults();
		
							rechercheLignes = rechercheSolr.getRows();
			
							if(rechercheListe.size() > 0) {
		
								for(Long i = rechercheListe.getStart(); i < rechercheListe.getNumFound(); i+=rechercheLignes) {
									for(Integer j = 0; j < rechercheListe.size(); j++) {
										entiteDocumentSolr = rechercheListe.get(j);
										entiteVar = (String)entiteDocumentSolr.get("entiteVar_" + langueNom + "_stored_string");
										entiteVarCapitalise = (String)entiteDocumentSolr.get("entiteVarCapitalise_" + langueNom + "_stored_string");
										entiteNomSimple = (String)entiteDocumentSolr.get("entiteNomSimple_" + langueNom + "_stored_string");
										entiteNomSimpleGenerique = (String)entiteDocumentSolr.get("entiteNomSimpleGenerique_" + langueNom + "_stored_string");
										entiteNomSimpleComplet = (String)entiteDocumentSolr.get("entiteNomSimpleComplet_" + langueNom + "_stored_string");
										entiteDescription = (String)entiteDocumentSolr.get("entiteDescription_" + langueNom + "_stored_string");
										entiteNomAffichage = (String)entiteDocumentSolr.get("entiteNomAffichage_" + langueNom + "_stored_string");
										entiteHtmlLigne = (Integer)entiteDocumentSolr.get("entiteHtmlLigne_stored_int");
										entiteCouverture = (Boolean)entiteDocumentSolr.get("entiteCouverture_stored_boolean");
										entiteHtml = (Boolean)entiteDocumentSolr.get("entiteHtml_stored_boolean");
										entiteDocValues = (Boolean)entiteDocumentSolr.get("entiteDocValues_stored_boolean");
										entiteIndexe = (Boolean)entiteDocumentSolr.get("entiteIndexe_stored_boolean");
										entiteStocke = (Boolean)entiteDocumentSolr.get("entiteStocke_stored_boolean");
										entiteMultiligne = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteMultiligne_stored_boolean"));
										entiteModifier = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteModifier_stored_boolean"));
										entiteDefinir = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteDefinir_stored_boolean"));
										entiteAttribuer = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteAttribuer_stored_boolean"));
										entiteSuggere = BooleanUtils.isTrue((Boolean)entiteDocumentSolr.get("entiteSuggere_stored_boolean"));
										entiteAttribuerNomSimple = (String)entiteDocumentSolr.get("entiteAttribuerNomSimple_" + langueNom + "_stored_string");
										entiteAttribuerVar = (String)entiteDocumentSolr.get("entiteAttribuerVar_" + langueNom + "_stored_string");
										entiteAttribuerVarUrlId = (String)entiteDocumentSolr.get("entiteAttribuerVarUrlId_" + langueNom + "_stored_string");
										entiteAttribuerVarUrlPk = (String)entiteDocumentSolr.get("entiteAttribuerVarUrlPk_" + langueNom + "_stored_string");
										entiteAttribuerVarTitre = (String)entiteDocumentSolr.get("entiteAttribuerVarTitre_" + langueNom + "_stored_string");
										entiteAttribuerVarDescription = (String)entiteDocumentSolr.get("entiteAttribuerVarDescription_" + langueNom + "_stored_string");
										entiteAttribuerVarImageUrl = (String)entiteDocumentSolr.get("entiteAttribuerVarImageUrl_" + langueNom + "_stored_string");
										entiteAttribuerVarSuggere = (String)entiteDocumentSolr.get("entiteAttribuerVarSuggere_" + langueNom + "_stored_string");
										entiteAttribuerOperationIdRecherche = (String)entiteDocumentSolr.get("entiteAttribuerOperationId" + langueConfig.getString(ConfigCles.var_Recherche) + "_" + langueNom + "_stored_string");
										entiteAttribuerOperationIdPATCH = (String)entiteDocumentSolr.get("entiteAttribuerOperationIdPATCH_" + langueNom + "_stored_string");
										entiteOperationIdPATCH = (String)entiteDocumentSolr.get("entiteOperationIdPATCH_" + langueNom + "_stored_string");
										entiteTypeJson = (String)entiteDocumentSolr.get("entiteTypeJson_stored_string");
										entiteAttribuerTypeJson = (String)entiteDocumentSolr.get("entiteAttribuerTypeJson_stored_string");
										entiteAttribuerContexteIconeNom = (String)entiteDocumentSolr.get("entiteAttribuerContexteIconeNom_stored_string");
										entiteAttribuerTrisVar = (List<String>)entiteDocumentSolr.get("entiteAttribuerTrisVar_" + langueNom + "_stored_strings");
										entiteAttribuerTrisSuffixeType = (List<String>)entiteDocumentSolr.get("entiteAttribuerTrisSuffixeType_stored_strings");
										entiteAttribuerContexteUnNom = (String)entiteDocumentSolr.get("entiteAttribuerContexteUnNom_" + langueNom + "_stored_string");
										entiteAttribuerContexteNomPluriel = (String)entiteDocumentSolr.get("entiteAttribuerContexteNomPluriel_" + langueNom + "_stored_string");
			
										if(entiteSuggere) {
											auteurPageJs.l();
											auteurPageJs.tl(0, "function ", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ", $list) {");
											auteurPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
											auteurPageJs.tl(2, "$list.empty();");
											auteurPageJs.tl(2, "$.each(data['list'], function(i, o) {");
											auteurPageJs.tl(3, "var $i = $('<i>').attr('class', 'fa", StringUtils.substring(contexteIconeGroupe, 0, 1), " fa-", contexteIconeNom, " ');");
											auteurPageJs.t(3, "var $span = $('<span>').attr('class', '').text(");
											if(classeVarTitre != null)
												auteurPageJs.s("o['", classeVarTitre, "']");
											auteurPageJs.l(");");
											auteurPageJs.tl(3, "var $li = $('<li>');");
											auteurPageJs.tl(3, "var $a = $('<a>').attr('href', o['", classeVarUrlPk, "']);");
											auteurPageJs.tl(3, "$a.append($i);");
											auteurPageJs.tl(3, "$a.append($span);");
											auteurPageJs.tl(3, "$li.append($a);");
											auteurPageJs.tl(3, "$list.append($li);");
											auteurPageJs.tl(2, "});");
											auteurPageJs.tl(1, "};");
											auteurPageJs.tl(1, "error = function( jqXhr, textStatus, errorThrown ) {};");
											auteurPageJs.tl(1, "", langueConfig.getString(ConfigCles.var_rechercher), classeApiClasseNomSimple, "Vals($", langueConfig.getString(ConfigCles.var_formulaireFiltres), ", success, error);");
											auteurPageJs.tl(0, "}");
										}
										else if(entiteAttribuer) {

											auteurPageJs.l();
											auteurPageJs.tl(0, "function ", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "(", langueConfig.getString(ConfigCles.var_filtres), ", $list, ", classeVarClePrimaire, " = null, ", langueConfig.getString(ConfigCles.var_attribuer), "=true) {");
											auteurPageJs.tl(1, "success = function( data, textStatus, jQxhr ) {");
											auteurPageJs.tl(2, "$list.empty();");
											auteurPageJs.tl(2, "$.each(data['list'], function(i, o) {");
											auteurPageJs.tl(3, "var $i = $('<i>').attr('class', 'fa", StringUtils.substring(entiteAttribuerContexteIconeGroupe, 0, 1), " fa-", entiteAttribuerContexteIconeNom, " ');");
											auteurPageJs.t(3, "var $span = $('<span>').attr('class', '').text(");
											if(entiteAttribuerVarTitre != null)
												auteurPageJs.s("o['", entiteAttribuerVarTitre, "']");
											auteurPageJs.l(");");

											if(entiteAttribuerVarUrlPk != null)
												auteurPageJs.tl(3, "var $a = $('<a>').attr('id', o['", classeVarClePrimaire, "']).attr('href', o['", entiteAttribuerVarUrlPk, "']);");
											else
												auteurPageJs.tl(3, "var $a = $('<span>');");

											auteurPageJs.tl(3, "$a.append($i);");
											auteurPageJs.tl(3, "$a.append($span);");
											auteurPageJs.tl(3, "var val = o['", entiteAttribuerVar, "'];");
											auteurPageJs.tl(3, "var checked = pk == null ? false : Array.isArray(val) ? val.includes(", classeVarClePrimaire, ".toString()) : val == ", classeVarClePrimaire, ";");
											auteurPageJs.tl(3, "var $input = $('<input>');");
											auteurPageJs.tl(3, "$input.attr('id', '", classeApiMethodeMethode, "_", entiteVar, "_' + ", classeVarClePrimaire, " + '_", entiteAttribuerVar, "_' + o['", classeVarClePrimaire, "']);");
											auteurPageJs.tl(3, "$input.attr('value', o['", classeVarClePrimaire, "']);");
											auteurPageJs.tl(3, "$input.attr('class', '", langueConfig.getString(ConfigCles.var_valeur), entiteVarCapitalise, " w3-check ');");

											auteurPageJs.tl(3, "if(", classeVarClePrimaire, " != null) {");
											auteurPageJs.t(4, "$input.attr('onchange', \"var $input = $('#", classeApiMethodeMethode, "_", entiteVar, "_\" + ", classeVarClePrimaire, " + \"_", entiteAttribuerVar, "_\" + o['", classeVarClePrimaire, "'] + \"'); ");
											if("array".equals(entiteTypeJson)) {
												auteurPageJs.s("", entiteOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeVarClePrimaire, ":\" + ", classeVarClePrimaire, " + \"' }], { [($input.prop('checked') ? 'add' : 'remove') + '", entiteVarCapitalise, "']: \\\"\" + o['", classeVarClePrimaire, "'] + \"\\\" }");
											}
											else {
												auteurPageJs.s("", entiteOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeVarClePrimaire, ":\" + ", classeVarClePrimaire, " + \"' }], { [($input.prop('checked') ? 'set' : 'remove') + '", entiteVarCapitalise, "']: \\\"\" + o['", classeVarClePrimaire, "'] + \"\\\" }");
											}
											auteurPageJs.l(" ); \");");

											auteurPageJs.tl(4, "$input.attr('onclick', '", langueConfig.getString(ConfigCles.var_enleverLueur), "($(this)); ');");
											auteurPageJs.tl(3, "}");

											auteurPageJs.tl(3, "$input.attr('type', 'checkbox');");
											auteurPageJs.tl(3, "if(checked)");
											auteurPageJs.tl(4, "$input.attr('checked', 'checked');");
											auteurPageJs.tl(3, "var $li = $('<li>');");
											if(entiteAttribuerTrisVar != null && entiteAttribuerTrisSuffixeType != null && entiteAttribuerTrisSuffixeType.size() > 0 && "_double".equals(entiteAttribuerTrisSuffixeType.get(0))) {
												for(String entiteAttribuerTriVar : entiteAttribuerTrisVar) {
													auteurPageJs.tl(3, "var ", entiteAttribuerTriVar, " = o['", entiteAttribuerTriVar, "'];");
												}
												String entiteAttribuerTriVarAncien = null;
												Integer k = 3;
												for(String entiteAttribuerTriVar : entiteAttribuerTrisVar) {
													if(entiteAttribuerTriVarAncien != null)
														k = 4;

													auteurPageJs.l();
													if(entiteAttribuerTriVarAncien != null)
														auteurPageJs.tl(3, "if(", entiteAttribuerTriVarAncien, " != null) {");
													auteurPageJs.tl(k, "$sort = $('<span>').attr('class', 'w3-text-grey ').attr('style', 'padding-right: 8px; ');");
													auteurPageJs.tl(k, "var $sortInput = $('<input>')");
													auteurPageJs.tl(k, "$sortInput.attr('class', 'w3-tiny ');");
													auteurPageJs.tl(k, "$sortInput.attr('style', 'width: 4em; ');");
													auteurPageJs.tl(k, "$sortInput.attr('id', \"", langueConfig.getString(ConfigCles.var_attribuer), "_\" + o['", classeVarClePrimaire, "'] + \"_", langueConfig.getString(ConfigCles.var_tri), "_", entiteAttribuerTriVar, "\");");
													auteurPageJs.tl(k, "$sortInput.attr('value', ", entiteAttribuerTriVar, ").attr('onchange', ");
													auteurPageJs.tl(k + 1, "\"$('#", classeApiClasseNomSimple, "Form :input[name=\\\"focusId\\\"]').val($(this).attr('id')); \"");
													auteurPageJs.tl(k + 1, "+ \"", entiteAttribuerOperationIdPATCH, "Vals([{ name: 'fq', value: '", classeVarClePrimaire, ":\" + o['", classeVarClePrimaire, "'] + \"' }], { ['set", StringUtils.capitalize(entiteAttribuerTriVar), "']: $(this).val() ? $(this).val() : null }\"");
													auteurPageJs.tl(k + 2, "+ \", function() { \"");
													auteurPageJs.tl(k + 2, "+ \"}\"");
													auteurPageJs.tl(k + 2, "+ \", function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#", langueConfig.getString(ConfigCles.var_attribuer), "_\" + o['", classeVarClePrimaire, "'] + \"_", langueConfig.getString(ConfigCles.var_tri), "_", entiteAttribuerTriVar, "')); }\"");
													auteurPageJs.tl(k + 2, "+ \" ); \"); ");
													auteurPageJs.tl(k, "$sort.append($sortInput);");
													auteurPageJs.tl(k, "$li.append($sort);");
													if(entiteAttribuerTriVarAncien != null)
														auteurPageJs.tl(3, "}");

													entiteAttribuerTriVarAncien = entiteAttribuerTriVar;
												}
											}
											auteurPageJs.tl(3, "if(", langueConfig.getString(ConfigCles.var_attribuer), ")");
											auteurPageJs.tl(4, "$li.append($input);");
											auteurPageJs.tl(3, "$li.append($a);");
											auteurPageJs.tl(3, "$list.append($li);");
											auteurPageJs.tl(2, "});");
											auteurPageJs.tl(2, "var focusId = $('#", classeApiClasseNomSimple, "Form :input[name=\"focusId\"]').val();");
											auteurPageJs.tl(2, "if(focusId)");
											auteurPageJs.tl(3, "$('#' + focusId).parent().next().find('input').focus();");
											auteurPageJs.tl(1, "};");
											auteurPageJs.tl(1, "error = function( jqXhr, textStatus, errorThrown ) {};");
											auteurPageJs.tl(1, "", entiteAttribuerOperationIdRecherche, "Vals(", langueConfig.getString(ConfigCles.var_filtres), ", success, error);");
											auteurPageJs.tl(0, "}");

											auteurWebsocket.l();
											auteurWebsocket.tl(2, "window.eventBus.registerHandler('websocket", entiteAttribuerNomSimple, "', function (error, message) {");
//												auteurWebsocket.tl(3, "var json = JSON.parse(message['body']);");
//												auteurWebsocket.tl(3, "var id = json['id'];");
//												auteurWebsocket.tl(3, langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, entiteVarCapitalise, "($('#' + ($(this).val() ? '", langueConfig.getString(ConfigCles.var_suggere), "' : 'form') + '", classeApiClasseNomSimple, entiteVarCapitalise, "'), $('#", "list", classeApiClasseNomSimple, entiteVarCapitalise, "_", classeApiMethodeMethode, "'));");
											auteurWebsocket.tl(3, "$('#Page_", entiteVar, "').trigger('oninput');");
											auteurWebsocket.tl(3, "$('#Page_", entiteVar, "_", langueConfig.getString(ConfigCles.var_ajouter), "').text('", langueConfig.getString(ConfigCles.var_ajouter), " ", entiteAttribuerContexteUnNom, "');");
											auteurWebsocket.tl(3, "$('#Page_", entiteVar, "_", langueConfig.getString(ConfigCles.var_ajouter), "').removeClass('w3-disabled');");
											auteurWebsocket.tl(3, "$('#Page_", entiteVar, "_", langueConfig.getString(ConfigCles.var_ajouter), "').attr('disabled', false);");
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
				}
				tl(3, "$(document).ready(function() {");
				tl(4, "document.onkeydown = function(evt) {");
				tl(5, "evt = evt || window.event;");
				tl(5, "var isEscape = false;");
				tl(5, "if ('key' in evt) {");
				tl(6, "isEscape = (evt.key === 'Escape' || evt.key === 'Esc');");
				tl(5, "} else {");
				tl(6, "isEscape = (evt.keyCode === 27);");
				tl(5, "}");
				tl(5, "if (isEscape) {");
				tl(6, "$('.w3-modal:visible').hide();");
				tl(5, "}");
				tl(4, "};");
				tl(4, "window.eventBus = new EventBus('/eventbus');");
				tl(4, "var pk = {{#if pk}}{{pk}}{{else}}null{{/if}};");
				tl(4, "if(pk != null) {");
				s(wJsInit);
				tl(4, "}");
				tl(4, "websocket", classeApiClasseNomSimple, "(websocket", classeApiClasseNomSimple, "Inner);");
//					s(wWebsocket);
//					tl(2, "tl(1, ", q("});"), ");");
				tl(3, "});");
				tl(2, "</script>");
				tl(0, "{{/inline}}");
			}
			t(0, "{{#*inline \"htmUrl", classeApiClasseNomSimple, "\"}}");
			s("{{pageUri}}");
			s("?q={{query.q}}");
			s("&amp;rows={{#if rows}}{{rows}}{{else}}{{pagination.", langueConfig.getString(ConfigCles.var_lignes), "}}{{/if}}");
			s("&amp;rows={{#if start}}{{start}}{{else}}{{pagination.", langueConfig.getString(ConfigCles.var_debut), "}}{{/if}}");
			s("{{#each query.fq}}");
			s("{{#eq fq this}}");
			s("{{else}}");
			s("&fq={{fq}}:{{val}}");
			s("{{/eq}}");
			s("{{/each}}");
			s("{{#each query.sort}}");
			s("{{#eq sort this}}");
			s("{{else}}");
			s("&sort={{var}} {{order}}");
			s("{{/eq}}");
			s("{{/each}}");
			l("{{/inline}}");

			t(0, "{{#*inline \"htmHead", classePageNomSimple, "\"}}");
			s("{{> \"htmTitle", classePageNomSimple, "\"}}");
			s("{{> \"htmMeta", classePageNomSimple, "\"}}");
			s("{{> \"htmStyle", classePageNomSimple, "\"}}");
			s("{{> \"htmScripts", classePageNomSimple, "\"}}");
			s("{{> \"htmScript", classePageNomSimple, "\"}}");
			l("{{/inline}}");

			//////////////////////
			// htmBodyRecherche //
			//////////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Recherche), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Recherche), classePageNomSimple, "\" -->");
			tl(1, "<div>");
			tl(0, "{{#each varsQ}}");
			tl(2, "<div class=\"w3-padding \">");
			t(3, "<label for=\"fq", classeNomSimple, "_{{ @key }}\">");
			s("{{ ", langueConfig.getString(ConfigCles.var_nomAffichage), " }}");
			s("<sup class=\"w3-tiny \"> ({{ ", langueConfig.getString(ConfigCles.var_classeNomSimple), " }})</sup>");
			l("</label>");

			t(3, "<input");
			s(" id=\"fq", classeNomSimple, "_{{ @key }}\"");
			s(" placeholder=\"{{ displayName }}\"");
			s(" class=\"w3-input \"");
//			s(" onkeypress=\"qChange(this); \"");
			s(" onkeyup=\"qChange(this); \"");
//			s(" onchange=\"qChange(this); \"");
			s(" data-var=\"{{ var }}\"");
			s(" autocomplete=\"off=\"");
			l("/>");

			tl(3, "<div class=\"pageSearchVal w3-tiny \"></div>");
			tl(2, "</div>");
			tl(0, "{{/each}}");
			tl(1, "</div>");
			
			l("{{/inline}}");

			////////////////////
			// htmBodyFiltres //
			////////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Filtres), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Filtres), classePageNomSimple, "\" -->");
			tl(1, "<div>");
			tl(0, "{{#each varsFq }}");
			tl(2, "<div class=\"w3-padding \">");
			t(3, "<label for=\"fq", classeNomSimple, "_{{ @key }}\">");
			s("{{ ", langueConfig.getString(ConfigCles.var_nomAffichage), " }}");
			s("<sup class=\"w3-tiny \"> ({{ ", langueConfig.getString(ConfigCles.var_classeNomSimple), " }})</sup>");
			l("</label>");

			tl(3, "<div class=\"w3-cell-row \">");
			tl(4, "<div class=\"w3-cell w3-cell-top \">");
			t(5, "<button");
			s(" id=\"buttonFacet", classeNomSimple, "_{{ @key }}\"");
			s(" class=\"w3-button \"");
			s(" onclick=\"facetFieldChange(this); \"");
			s(" title=\"", langueConfig.getString(ConfigCles.str_voir_valeurs), " ", "\"");
			s(" data-var=\"{{ var }}\"");
			s(" data-clear=\"{{#if facetField }}true{{else}}false{{/if}}\"");
			l("><i class=\"fas fa-list \"></i></button>");
			tl(4, "</div>");

			tl(4, "<div class=\"w3-cell w3-cell-top \">");
			t(5, "<input");
			s(" id=\"fq", classeNomSimple, "_{{ @key }}\"");
			s(" placeholder=\"{{ displayName }}\"");
			s(" class=\"w3-input \"");
//			s(" onkeypress=\"fqChange(this); \"");
			s(" onkeyup=\"fqChange(this); \"");
//			s(" onchange=\"fqChange(this); \"");
			s(" data-var=\"{{ var }}\"");
			s(" autocomplete=\"off=\"");
			s(" value=\"{{ val }}\"");
			l("/>");
			tl(4, "</div>");
			tl(3, "</div>");

			tl(2, "</div>");
			t(2, "<div");
			s(" class=\"w3-padding w3-tiny \"");
			l(">");
			t(3, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-fq", classeNomSimple, "_{{ @key }}\"");
			l(">{{#if val }}fq={{ var }}:{{encodeURIComponent val }}{{/if}}</div>");
			t(3, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-buttonFacet", classeNomSimple, "_{{ @key }}\"");
			l(">{{#if facetField.var }}facet.field={{ facetField.var }}{{/if}}</div>");
			tl(2, "</div>");

			t(2, "<ol");
			s(" class=\"pageFacetField \"");
			s(" id=\"pageFacetField", classeNomSimple, "_{{ @key }}\"");
			l(">");
			tl(0, "{{#each facetField.counts }}");
			t(3, "<li");
			s(" class=\"\"");
			s(">");
			s("{{ @key }}");
			s(": ");
			s("{{ this }}");
			l("</li>");
			tl(0, "{{/each}}");
			tl(2, "</ol>");

			tl(0, "{{/each}}");
			tl(1, "</div>");
			
			l("{{/inline}}");

			//////////////////
			// htmBodyGamme //
			//////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Gamme), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Gamme), classePageNomSimple, "\" -->");

			tl(1, "<table class=\"w3-padding w3-table \">");
			tl(2, "<tr class=\"\">");
			tl(3, "<td class=\"\">");
			tl(3, "<span>Enable Range Graph</span>");
			tl(3, "</td>");
			tl(3, "<td class=\"\">");
			t(3, "<span>");
			s("<input type=\"checkbox\"");
			s(" name=\"rangeEnabled\"");
			s(" id=\"pageFacetRangeEnabled-", classeNomSimple, "\"");
			s(" value=\"{{#if facetCounts.facetRange }}true{{else}}false{{/if}}\"");
			s(" onclick=\"facet", langueConfig.getString(ConfigCles.var_Gamme), "Change(this, '", classeNomSimple, "'); \"");
			l("/></span>");
			tl(3, "</td>");
			tl(2, "</tr>");

			tl(2, "<tr class=\"\">");
			tl(3, "<td class=\"\">");
			tl(3, "<span>Range Gap</span>");
			tl(3, "</td>");
			tl(3, "<td class=\"\">");
			tl(4, "<select");
			s(" name=\"facet.range.gap\"");
			s(" id=\"pageFacetRangeGap-", classeNomSimple, "\"");
			s(" onchange=\"facet", langueConfig.getString(ConfigCles.var_Gamme), "Change(this, '", classeNomSimple, "'); \"");
			l(">");
			tl(5, "<option value=\"YEAR\"{{#eq defaultRangeGap 'YEAR'}} selected=\"selected\"{{else}}{{/eq}}>Year</option>");
			tl(5, "<option value=\"MONTH\"{{#eq defaultRangeGap 'MONTH'}} selected=\"selected\"{{else}}{{/eq}}>Month</option>");
			tl(5, "<option value=\"WEEK\"{{#eq defaultRangeGap 'WEEK'}} selected=\"selected\"{{else}}{{/eq}}>Week</option>");
			tl(5, "<option value=\"DAY\"{{#eq defaultRangeGap 'DAY'}} selected=\"selected\"{{else}}{{/eq}}>Day</option>");
			tl(5, "<option value=\"HOUR\"{{#eq defaultRangeGap 'HOUR'}} selected=\"selected\"{{else}}{{/eq}}>Hour</option>");
			tl(5, "<option value=\"MINUTE\"{{#eq defaultRangeGap 'MINUTE'}} selected=\"selected\"{{else}}{{/eq}}>Minute</option>");
			tl(5, "<option value=\"SECOND\"{{#eq defaultRangeGap 'SECOND'}} selected=\"selected\"{{else}}{{/eq}}>Second</option>");
			tl(4, "</select>");
			tl(3, "</td>");
			tl(2, "</tr>");

			tl(2, "<tr class=\"\">");
			tl(3, "<td class=\"\" colspan=\"2\">");
			tl(4, "<span>Range Start</span>");
			tl(3, "</td>");
			tl(2, "</tr>");
			tl(2, "<tr class=\"\">");
			tl(3, "<td class=\"\" colspan=\"2\">");
			t(3, "<span>");
			s("<input type=\"datetime-local\"");
			s(" name=\"facetRangeStart\"");
			s(" id=\"pageFacetRangeStart-", classeNomSimple, "\"");
			s(" value=\"{{formatZonedDateTime defaultRangeStart \"yyyy-MM-dd'T'HH:mm\" defaultLocaleId defaultZoneId}}\"");
			s(" onclick=\"facet", langueConfig.getString(ConfigCles.var_Gamme), "Change(this, '", classeNomSimple, "'); \"");
			l("/></span>");
			tl(3, "</td>");
			tl(2, "</tr>");

			tl(2, "<tr class=\"\">");
			tl(3, "<td class=\"\" colspan=\"2\">");
			tl(4, "<span>Range End</span>");
			tl(3, "</td>");
			tl(2, "</tr>");
			tl(2, "<tr class=\"\">");
			tl(3, "<td class=\"\" colspan=\"2\"");
			t(3, "<span>");
			s("<input type=\"datetime-local\"");
			s(" name=\"facetRangeEnd\"");
			s(" id=\"pageFacetRangeEnd-", classeNomSimple, "\"");
			s(" value=\"{{formatZonedDateTime defaultRangeEnd \"yyyy-MM-dd'T'HH:mm\" defaultLocaleId defaultZoneId}}\"");
			s(" onclick=\"facet", langueConfig.getString(ConfigCles.var_Gamme), "Change(this, '", classeNomSimple, "'); \"");
			l("/></span>");
			tl(3, "</td>");
			tl(2, "</tr>");

			t(2, "<tr>");
			t(3, "<td");
			s(" class=\"w3-padding w3-tiny \"");
			s(" colspan=\"2\"");
			l(">");
			t(4, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-pageFacetRangeGap-", classeNomSimple, "\"");
			s(">{{#if facetCounts.facetRange }}facet.range.gap={{ defaultRangeGap }}{{/if}}");
			l("</div>");
			t(4, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-pageFacetRangeStart-", classeNomSimple, "\"");
			s(">{{#if facetCounts.facetRange }}facet.range.start={{ defaultRangeStart }}{{/if}}");
			l("</div>");
			t(4, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-pageFacetRangeEnd-", classeNomSimple, "\"");
			s(">{{#if facetCounts.facetRange }}facet.range.end={{ defaultRangeEnd }}{{/if}}");
			l("</div>");
			t(4, "<div");
			s(" class=\"pageSearchVal \"");
			s(" id=\"pageSearchVal-pageFacetRangeVar-", classeNomSimple, "\"");
			s(">{{#if facetCounts.facetRange }}facet.range={{ defaultRangeVar }}{{/if}}");
			l("</div>");
			tl(3, "</td>");
			tl(2, "</tr>");
			tl(1, "</table>");

			tl(1, "<div class=\"w3-large font-weight-bold \">", langueConfig.getString(ConfigCles.var_gamme), "</div>");
			tl(1, "<table class=\"w3-table \">");
			tl(0, "{{#each vars", langueConfig.getString(ConfigCles.var_Gamme), " }}");
			tl(2, "<tr class=\"\">");
			tl(3, "<td class=\"\">");
			t(4, "<span>");
			s("<input type=\"checkbox\"");
			s(" name=\"pageFacetPivot\"");
			s(" class=\"pageFacetPivot \"");
			s(" id=\"pageFacetPivot", classeNomSimple, "_{{ @key }}\"");
			s(" value=\"{{ var }}\"");
			s(" onclick=\"facetPivotChange(this, '", classeNomSimple, "'); \"");
			l("/></span>");
			tl(3, "</td>");
			tl(3, "<td class=\"w3-cell \">");
			tl(3, "<label for=\"pageFacetPivot", classeNomSimple, "_{{ @key }}\">{{ ", langueConfig.getString(ConfigCles.var_nomAffichage), " }}</span>");
			tl(3, "</td>");
			tl(2, "</tr>");
			tl(0, "{{/each}}");
			tl(1, "</table>");

//			&facet.pivot={!range%3Dr1}eventId_docvalues_strings
//			&facet.pivot.mincount=0
//			&facet.sort=index
//			&facet=true
//			&facet.limit=1000
//			&facet.mincount=1
//			&facet.range.start=2022-02-23T04:05:23.921447Z
//			&facet.range.end=2022-03-02T04:05:23.920401Z
//			&facet.range={!tag%3Dr1}created_docvalues_date

			//////////////////
			// htmBodyPivot //
			//////////////////

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Pivot), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Pivot), classePageNomSimple, "\" -->");

			t(2, "<div");
			s(" class=\"w3-padding w3-tiny pageSearchVal \"");
			s(" id=\"pageSearchVal-pivot", classeNomSimple, "\"");
			l(">");
//			tl(0, "{{#each varsPivot }}");
//			t(3, "<div");
//			s(" class=\"pageSearchVal \"");
//			s(" id=\"pageSearchVal-pivot", classeNomSimple, "_{{ @key }}\"");
//			l(">{{ var }}</div>");
//			tl(0, "{{/each}}");
			tl(2, "</div>");

			tl(1, "<table class=\"w3-table \">");
			tl(0, "{{#each varsFq }}");
			tl(2, "<tr class=\"\">");
			tl(3, "<td class=\"\">");
			t(4, "<span>");
			s("<input type=\"checkbox\"");
			s(" name=\"pageFacetPivot\"");
			s(" class=\"pageFacetPivot \"");
			s(" id=\"pageFacetPivot", classeNomSimple, "_{{ @key }}\"");
			s(" value=\"{{ var }}\"");
			s(" onclick=\"facetPivotChange(this, '", classeNomSimple, "'); \"");
			l("/></span>");
			tl(3, "</td>");
			tl(3, "<td class=\"w3-cell \">");
			tl(3, "<label for=\"pageFacetPivot", classeNomSimple, "_{{ @key }}\">{{ ", langueConfig.getString(ConfigCles.var_nomAffichage), " }}</span>");
			tl(3, "</td>");
			tl(2, "</tr>");
			tl(0, "{{/each}}");
			tl(1, "</table>");

			t(2, "<div");
			s(" style=\"display: none; \"");
			s(" class=\"w3-padding w3-tiny \"");
			s(" id=\"pageSearchVal-hidden", classeNomSimple, "\"");
			l(">");
//			tl(0, "{{#each varsPivot }}");
//			t(3, "<div");
//			s(" class=\"pageSearchVal pageSearchVal-hidden", classeNomSimple, " \"");
//			s(" id=\"pageSearchVal-hidden", classeNomSimple, "_{{ @key }}\"");
//			l(">{{ var }}</div>");
//			tl(0, "{{/each}}");
			tl(2, "</div>");
			
			l("{{/inline}}");
			
			l("{{/inline}}");

			///////////////////
			// htmBodyCount0 //
			///////////////////

			s("{{#*inline \"htmBodyCount0", classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBodyCount0", classePageNomSimple, "\" -->");
			tl(2, "<h1>");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-center w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, "\">");
			tl(4, "{{#if ", langueConfig.getString(ConfigCles.var_contexteIconeClassesCss), "}}");
			tl(5, "<i class=\"{{ ", langueConfig.getString(ConfigCles.var_contexteIconeClassesCss), " }} site-menu-icon \"></i>");
			tl(4, "{{/if}}");
			tl(4, "<span class=\"\">", contexteNomAdjectifPluriel, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");
			tl(2, "<h2>");
			tl(3, "<span class=\"w3-bar-item w3-padding w3-center w3-block w3-", contexteCouleur, "\">");
			tl(4, "{{#if ", langueConfig.getString(ConfigCles.var_contexteIconeClassesCss), "}}");
			tl(5, "<i class=\"{{ ", langueConfig.getString(ConfigCles.var_contexteIconeClassesCss), " }}  site-menu-icon \"></i>");
			tl(4, "{{/if}}");
			tl(4, "<span class=\"\">", contexteAucunNomTrouve, "</span>");
			tl(3, "</span>");
			tl(2, "</h2>");
			l("{{/inline}}");

			s("{{#*inline \"htmBodyCount1", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBodyCount1", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "\" -->");
			tl(2, "<h1 class=\"w3-center \">");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, "\">");
			tl(3, "{{#if ", langueConfig.getString(ConfigCles.var_contexteIconeClassesCss), "}}");
			tl(4, "<i class=\"{{ ", langueConfig.getString(ConfigCles.var_contexteIconeClassesCss), " }} site-menu-icon \"></i>");
			tl(3, "{{/if}}");
			tl(4, "<span class=\"\">", contexteNomAdjectifSingulier, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");

			tl(2, "<h2 class=\"w3-center \">");
			tl(3, "<span class=\"w3-bar-item w3-padding w3-center w3-block w3-", contexteCouleur, "\">");
			tl(4, "<span class=\"\">{{", uncapitalizeClasseApiClasseNomSimple, "_.", langueConfig.getString(ConfigCles.var_objetTitre), "}}</span>");
			tl(3, "</span>");
			tl(2, "</h2>");

			if(classeVarClePrimaire != null) {
				tl(0, "{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "\"}}{{/block}}");
			}
			l("{{/inline}}");
	
			s("{{#*inline \"htmBodyCount1", classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBodyCount1", classePageNomSimple, "\" -->");
			tl(2, "<h1>");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-center w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, "\">");
			tl(1, "{{#if ", langueConfig.getString(ConfigCles.var_contexteIconeClassesCss), "}}");
			tl(4, "<i class=\"{{ ", langueConfig.getString(ConfigCles.var_contexteIconeClassesCss), " }} site-menu-icon \"></i>");
			tl(1, "{{/if}}");
			tl(4, "<span class=\"\">", contexteNomAdjectifPluriel, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");
			tl(2, "<div>");
			tl(1, "{{#if pagination.page", langueConfig.getString(ConfigCles.var_Precedent), "}}");
			tl(3, "<a href=\"{{pageUri}}?start={{pagination.page", langueConfig.getString(ConfigCles.var_Precedent), ".", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{pagination.", langueConfig.getString(ConfigCles.var_lignes), "}}\">");
			tl(4, "<i class=\"fas fa-arrow-square-left \"></i>");
			tl(3, "</a>");
			tl(1, "{{else}}");
			tl(3, "<i class=\"fas fa-arrow-square-left w3-opacity \"></i>");
			tl(1, "{{/if}}");
			tl(1, "{{#gte pagination.", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Precedent), " pagination.1L}}");
			tl(3, "<a href=\"{{pageUri}}?start={{pagination.", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{ pagination.", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Precedent), " }}\">");
			tl(4, "<i class=\"fas fa-minus-square \"></i>");
			tl(3, "</a>");
			tl(1, "{{else}}");
			tl(3, "<i class=\"fas fa-minus-square w3-opacity \"></i>");
			tl(1, "{{/gte}}");
			tl(3, "<a href=\"{{pageUri}}?start={{pagination.", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{ pagination.", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Prochaine), " }}\">");
			tl(4, "<i class=\"fas fa-plus-square \"></i>");
			tl(3, "</a>");
			tl(1, "{{#if pagination.page", langueConfig.getString(ConfigCles.var_Prochaine), "}}");
			tl(3, "<a href=\"{{pageUri}}?start={{pagination.page", langueConfig.getString(ConfigCles.var_Prochaine), ".", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{pagination.", langueConfig.getString(ConfigCles.var_lignes), "}}\">");
			tl(4, "<i class=\"fas fa-arrow-square-right \"></i>");
			tl(3, "</a>");
			tl(1, "{{else}}");
			tl(3, "<i class=\"fas fa-arrow-square-right w3-opacity \"></i>");
			tl(1, "{{/if}}");
			tl(3, "<span>{{ pagination.", langueConfig.getString(ConfigCles.var_debut), "Num }} - {{ pagination.", langueConfig.getString(ConfigCles.var_fin), "Num }} ", langueConfig.getString(ConfigCles.var_de), " {{ pagination.", langueConfig.getString(ConfigCles.var_numTrouve), " }}</span>");
			tl(2, "</div>");
			tl(0, "{{> \"table1", classePageNomSimple, "\"}}");
			l("{{/inline}}");

			s("{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "\"}}");
			tl(2, "<!-- #*inline \"htmBody", langueConfig.getString(ConfigCles.var_Tous), classePageNomSimple, "\" -->");
			tl(2, "<h1>");
			tl(3, "<a href=\"{{pageUri}}\" class=\"w3-bar-item w3-btn w3-center w3-block w3-", contexteCouleur, " w3-hover-", contexteCouleur, "\">");
			tl(1, "{{#if ", langueConfig.getString(ConfigCles.var_contexteIconeClassesCss), "}}");
			tl(4, "<i class=\"{{ ", langueConfig.getString(ConfigCles.var_contexteIconeClassesCss), " }} site-menu-icon \"></i>");
			tl(1, "{{/if}}");
			tl(4, "<span class=\"\">", contexteNomAdjectifPluriel, "</span>");
			tl(3, "</a>");
			tl(2, "</h1>");
			tl(2, "<div class=\"\">");
			tl(3, "<div>");
			tl(1, "{{#if pagination.page", langueConfig.getString(ConfigCles.var_Precedent), "}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.page", langueConfig.getString(ConfigCles.var_Precedent), ".", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{pagination.", langueConfig.getString(ConfigCles.var_lignes), "}}\">");
			tl(5, "<i class=\"fas fa-arrow-square-left \"></i>");
			tl(4, "</a>");
			tl(1, "{{else}}");
			tl(4, "<i class=\"fas fa-arrow-square-left w3-opacity \"></i>");
			tl(1, "{{/if}}");
			tl(1, "{{#gte pagination.", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Precedent), " pagination.1L}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{ pagination.", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Precedent), " }}\">");
			tl(5, "<i class=\"fas fa-minus-square \"></i>");
			tl(4, "</a>");
			tl(1, "{{else}}");
			tl(4, "<i class=\"fas fa-minus-square w3-opacity \"></i>");
			tl(1, "{{/gte}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{ pagination.", langueConfig.getString(ConfigCles.var_lignes), langueConfig.getString(ConfigCles.var_Prochaine), " }}\">");
			tl(5, "<i class=\"fas fa-plus-square \"></i>");
			tl(4, "</a>");
			tl(1, "{{#if pagination.page", langueConfig.getString(ConfigCles.var_Prochaine), "}}");
			tl(4, "<a href=\"{{pageUri}}?start={{pagination.page", langueConfig.getString(ConfigCles.var_Prochaine), ".", langueConfig.getString(ConfigCles.var_debut), "}}&amp;rows={{pagination.", langueConfig.getString(ConfigCles.var_lignes), "}}\">");
			tl(5, "<i class=\"fas fa-arrow-square-right \"></i>");
			tl(4, "</a>");
			tl(1, "{{else}}");
			tl(4, "<i class=\"fas fa-arrow-square-right w3-opacity \"></i>");
			tl(1, "{{/if}}");
			tl(4, "<span>{{ pagination.", langueConfig.getString(ConfigCles.var_debut), "Num }} - {{ pagination.", langueConfig.getString(ConfigCles.var_fin), "Num }} ", langueConfig.getString(ConfigCles.var_de), " {{ pagination.", langueConfig.getString(ConfigCles.var_numTrouve), " }}</span>");
			tl(3, "</div>");
			tl(0, "{{> \"table1", classePageNomSimple, "\"}}");
			tl(2, "</div>");
			l("{{/inline}}");

			if(classeVarClePrimaire != null) {
				s("{{#*inline \"htm", langueConfig.getString(ConfigCles.var_Formulaire), classePageNomSimple, "\"}}");
				tl(1, "{{#if ", classeVarClePrimaire, "}}");
				tl(2, "<!-- #*inline \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "\" -->");
				tl(2, "<form action=\"", classeApiUri, "\" id=\"", classeApiClasseNomSimple, "Form\" style=\"\" onsubmit=\"event.preventDefault(); return false; \">");
				t(3, "<input");
				s(" name=\"", classeVarClePrimaire, "\"");
				s(" class=\"", langueConfig.getString(ConfigCles.var_valeur), StringUtils.capitalize(classeVarClePrimaire), "\"");
				s(" type=\"hidden\"");
				s(" value=\"{{", classeVarClePrimaire, "}}\"");
				l("/>");
				t(3, "<input");
				s(" name=\"focusId\"");
				s(" type=\"hidden\"");
				l("/>");
				tl(2, "</form>");
				l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "_", StringUtils.lowerCase(langueConfig.getString(ConfigCles.var_PageRecherche)), classeApiClasseNomSimple, "\"}}{{/block}}");
				tl(1, "{{/if}}");
				l("{{/inline}}");
			}

			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");

				if(classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PageRecherche)) || classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTCopie)) || classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
					l("{{#*inline \"htm", langueConfig.getString(ConfigCles.var_Formulaire), classePageNomSimple, "_", classeApiOperationIdMethode, "\"}}");
					String methodeTitreFiltres = null;
					String methodeTitreValeurs = null;

					if("POST".equals(classeApiMethodeMethode)) {
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Creer_) + contexteUnNom;
					}
					else if("PUTImport".equals(classeApiMethode)) {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + contexteUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Importer_) + contexteNomPluriel;
					}
					else if(langueConfig.getString(ConfigCles.var_PUTFusion).equals(classeApiMethode)) {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + contexteUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Fusionner_) + contexteNomPluriel;
					}
					else if(langueConfig.getString(ConfigCles.var_PUTCopie).equals(classeApiMethode)) {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + contexteUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Dupliquer_) + contexteNomPluriel;
					}
					else if("PATCH".equals(classeApiMethodeMethode)) {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + contexteUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Modifier_des_) + contexteNomPluriel;
					}
					else if("DELETE".equals(classeApiMethodeMethode)) {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + contexteUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Supprimer_) + contexteNomPluriel;
					}
					else {
						methodeTitreFiltres = langueConfig.getString(ConfigCles.str_Rechercher_) + contexteUnNom;
						methodeTitreValeurs = langueConfig.getString(ConfigCles.str_Rechercher_) + contexteNomPluriel;
					}

					if(!classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PageRecherche))) {
						tl(2, "<button");
						tl(3, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " \"");
						tl(3, "onclick=\"$('#", classeApiOperationIdMethode, langueConfig.getString(ConfigCles.var_Modale), "').show(); \"");
						tl(3, ">");
	
							if(classeApiMethodeMethode.contains("POST"))
								tl(3, "<i class=\"fas fa-file-plus \"></i>");
							else if(classeApiMethodeMethode.contains("PATCH"))
								tl(3, "<i class=\"fas fa-edit \"></i>");
							else if(classeApiMethode.contains("PUTImport"))
								tl(3, "<i class=\"fas fa-file-import \"></i>");
							else if(classeApiMethode.contains(langueConfig.getString(ConfigCles.var_PUTFusion)))
								tl(3, "<i class=\"fas fa-code-merge \"></i>");
							else if(classeApiMethode.contains(langueConfig.getString(ConfigCles.var_PUTCopie)))
								tl(3, "<i class=\"fas fa-copy \"></i>");
	
							tl(3, methodeTitreValeurs);
						tl(2, "</button>");
					}
					{ tl(2, "<div id=\"", classeApiOperationIdMethode, langueConfig.getString(ConfigCles.var_Modale), "\" class=\"", classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PageRecherche)) ? "" : "w3-modal ", "\">");
						{ tl(3, "<div class=\"", classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PageRecherche)) ? "" : "w3-modal-content ", "\">");
							{ tl(4, "<div class=\"w3-card-4 \">");
								if(!langueConfig.getString(ConfigCles.var_PageRecherche).equals(classeApiMethode)) {
									{ tl(5, "<header class=\"w3-container w3-", contexteCouleur, "\">");
										tl(1, "{{#eq \"Page\" ", langueConfig.getString(ConfigCles.var_classeApiMethodeMethode), "}}");
										tl(6, "<span class=\"w3-button w3-display-topright \" onclick=\"$('#", classeApiOperationIdMethode, langueConfig.getString(ConfigCles.var_Modale), "').hide(); \">×</span>");
										tl(1, "{{/eq}}");
										tl(6, "<h2 class=\"w3-padding \">", methodeTitreValeurs, "</h2>");
									} tl(5, "</header>");
								}
	
								{ tl(5, "<div class=\"\" id=\"", classeApiOperationIdMethode, classePageLangueConfig.getString(ConfigCles.var_FormulaireValeurs), "\">");
//											TODO
//											tl(7+ tab, classeApiClasseNomSimple, " o = new ", classeApiClasseNomSimple, "();");
////											tl(7+ tab, "o.", langueConfig.getString(ConfigCles.var_initLoin), langueConfig.getString(ConfigCles.var_PourClasse), "(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
//											tl(7+ tab, "o.set", langueConfig.getString(ConfigCles.var_RequeteSite), "_(", langueConfig.getString(ConfigCles.var_requeteSite), "_);");
									if("PATCH".equals(classeApiMethode) || langueConfig.getString(ConfigCles.var_PUTFusion).equals(classeApiMethode) || langueConfig.getString(ConfigCles.var_PUTCopie).equals(classeApiMethode) || "PUTImport".equals(classeApiMethode)) {
	
										if("PUTImport".equals(classeApiMethode)) {
											tl(6, "<div class=\"w3-cell-row \">");
											tl(7, "<textarea");
											tl(8, "class=\"", "PUTImport_", langueConfig.getString(ConfigCles.var_listeRecherche), " w3-input w3-border \"");
											tl(8, "style=\"height: 300px; \"");
											tl(8, "placeholder=\"{ '", langueConfig.getString(ConfigCles.var_listeRecherche), "': [ { 'pk': ... , '", langueConfig.getString(ConfigCles.var_sauvegardes), "': [ ... ] }, ... ] }\"");
											tl(8, "></textarea>");
											tl(6, "</div>");
										} else if(langueConfig.getString(ConfigCles.var_PUTFusion).equals(classeApiMethode)) {
											tl(6, "<div class=\"w3-cell-row \">");
											tl(7, "<textarea");
											tl(8, "class=\"", "PUT", langueConfig.getString(ConfigCles.var_PUTFusion), "_", langueConfig.getString(ConfigCles.var_listeRecherche), " w3-input w3-border \"");
											tl(8, "style=\"height: 300px; \"");
											tl(8, "placeholder=\"{ '", langueConfig.getString(ConfigCles.var_listeRecherche), "': [ { 'pk': ... , '", langueConfig.getString(ConfigCles.var_sauvegardes), "': [ ... ] }, ... ] }\"");
											tl(8, "></textarea>");
											tl(6, "</div>");
										} else if(langueConfig.getString(ConfigCles.var_PUTCopie).equals(classeApiMethode)) {
											s(wFormPUTCopie);
										} else if("PATCH".equals(classeApiMethodeMethode)) {
											s(wFormPATCH);
										}
	
										tl(6, "<button");
										tl(7, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-", contexteCouleur, " \"");
		
										if("PATCH".equals(classeApiMethode))
											tl(7, "onclick=\"", classeApiOperationIdMethode, "(null, $('#", classeApiOperationIdMethode, classePageLangueConfig.getString(ConfigCles.var_FormulaireValeurs), "')); \"");
										else if("PUTImport".equals(classeApiMethode))
											tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, classePageLangueConfig.getString(ConfigCles.var_FormulaireValeurs), "')); \"");
										else if(langueConfig.getString(ConfigCles.var_PUTFusion).equals(classeApiMethode))
											tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, classePageLangueConfig.getString(ConfigCles.var_FormulaireValeurs), "')); \"");
										else if(langueConfig.getString(ConfigCles.var_PUTCopie).equals(classeApiMethode))
											tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, classePageLangueConfig.getString(ConfigCles.var_FormulaireValeurs), "')); \"");
										else
											tl(7, "onclick=\"", classeApiOperationIdMethode, "\"");
		
										tl(7, ">", methodeTitreValeurs, "</button>");
									} else {
										{ tl(6, "<div id=\"", classeApiOperationIdMethode, "Form\">");
	
										if("POST".equals(classeApiMethode)) {
											s(wFormPOST);
										} else if(langueConfig.getString(ConfigCles.var_Recherche).equals(classeApiMethode)) {
											s(wFormRecherche);
										} else {
											s(wFormPage);
										}
	
										} tl(6, "</div>");
										if(!langueConfig.getString(ConfigCles.var_PageRecherche).equals(classeApiMethode)) {
											tl(6, "<button");
											tl(7, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-", contexteCouleur, " \"");
											if("POST".equals(classeApiMethodeMethode))
												tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \"");
											else if("PATCH".equals(classeApiMethodeMethode))
												tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, langueConfig.getString(ConfigCles.var_FormulaireFiltres), "'), $('#", classeApiOperationIdMethode, classePageLangueConfig.getString(ConfigCles.var_FormulaireValeurs), "'), function() {}, function() {}); \"");
											else if("PUTImport".equals(classeApiMethode))
												tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \"");
											else if(langueConfig.getString(ConfigCles.var_PUTFusion).equals(classeApiMethode))
												tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form')); \"");
											else if(langueConfig.getString(ConfigCles.var_PUTCopie).equals(classeApiMethode))
												tl(7, "onclick=\"", classeApiOperationIdMethode, "($('#", classeApiOperationIdMethode, "Form'), \", ", uncapitalizeClasseApiClasseNomSimple, "_ == null ? \"null\" : ", uncapitalizeClasseApiClasseNomSimple, "_.get", StringUtils.capitalize(classeVarClePrimaire), "(), \"; \")");
											else if(classeApiMethodeMethode.contains("PATCH") || classeApiMethodeMethode.contains("POST") || classeApiMethodeMethode.contains("PUT"))
												tl(7, "onclick=\"", classeApiOperationIdMethode, "(\", o.get", StringUtils.capitalize(classeVarClePrimaire), "(), \"; \")");
											else
												tl(7, "onclick=\"", classeApiOperationIdMethode, "(); \"");
			
											tl(7, ">", methodeTitreValeurs, "</button>");
										}
									}
								} tl(5, "</div>");
							} tl(4, "</div>");
						} tl(3, "</div>");
					} tl(2, "</div>");
					l("{{/inline}}");
				}
			}

//			l("{{#*inline \"htmBodyCount1", classePageNomSimple, "\"}}");
//			l("{{/inline}}");

			tl(0, "{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Debut), classePageNomSimple, "\"}}");
			tl(0, "{{> \"htmBody", langueConfig.getString(ConfigCles.var_Debut), classePageSuperNomSimple, "\"}}");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"htmBody", langueConfig.getString(ConfigCles.var_Fin), classePageNomSimple, "\"}}");
			tl(0, "{{> \"htmBody", langueConfig.getString(ConfigCles.var_Fin), classePageSuperNomSimple, "\"}}");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"htmBody", classePageNomSimple, "\"}}");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Debut), "\"}}{{/block}}");

			///////////////
			// sidebar q //
			///////////////

			tl(0, "<div  class=\"siteSidebarToggle siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), " w3-sidebar w3-bar-block \" style=\"min-width: 300px; display: none; \">");
			tl(1, "<div class=\"w3-bar w3-", contexteCouleur, " \">");
			tl(2, "<span class=\"w3-bar-item w3-padding \">", langueConfig.getString(ConfigCles.var_Recherche), "</span>");
			tl(1, "</div>");
			tl(1, "<div class=\"w3-bar-block \">");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Recherche), "\"}}{{/block}}");
			tl(1, "</div>");
			tl(0, "</div>");

			////////////////
			// sidebar fa //
			////////////////

			tl(0, "<div  class=\"siteSidebarToggle siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), " w3-sidebar w3-bar-block \" style=\"min-width: 300px; display: none; \">");
			tl(1, "<div class=\"w3-bar w3-", contexteCouleur, " \">");
			tl(2, "<span class=\"w3-bar-item w3-padding \">", langueConfig.getString(ConfigCles.var_Filtres), "</span>");
			tl(1, "</div>");
			tl(1, "<div class=\"w3-bar-block \">");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Filtres), "\"}}{{/block}}");
			tl(1, "</div>");
			tl(0, "</div>");

			///////////////////
			// sidebar gamme //
			///////////////////

			tl(0, "<div  class=\"siteSidebarToggle siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), " w3-sidebar w3-bar-block \" style=\"min-width: 300px; display: none; \">");
			tl(1, "<div class=\"w3-bar w3-", contexteCouleur, " \">");
			tl(2, "<span class=\"w3-bar-item w3-padding \">", langueConfig.getString(ConfigCles.var_Gamme), "</span>");
			tl(1, "</div>");
			tl(1, "<div class=\"w3-bar-block \">");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Gamme), "\"}}{{/block}}");
			tl(1, "</div>");
			tl(0, "</div>");

			///////////////////
			// sidebar pivot //
			///////////////////

			tl(0, "<div  class=\"siteSidebarToggle siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), " w3-sidebar w3-bar-block \" style=\"min-width: 300px; display: none; \">");
			tl(1, "<div class=\"w3-bar w3-", contexteCouleur, " \">");
			tl(2, "<span class=\"w3-bar-item w3-padding \">", langueConfig.getString(ConfigCles.var_Pivot), "</span>");
			tl(1, "</div>");
			tl(1, "<div class=\"w3-bar-block \">");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Pivot), "\"}}{{/block}}");
			tl(1, "</div>");
			tl(0, "</div>");

			/////////////////
			// pageContent //
			/////////////////

			tl(0, "<div class=\"pageContent w3-content \">");

			t(1, "<div class=\"w3-display-topleft \">");

			//////////////
			// bouton q //
			//////////////
			t(2, "<span");
			s(" title=\"", langueConfig.getString(ConfigCles.var_Recherche), "\"");
			s(" class=\"w3-button w3-xlarge w3-", contexteCouleur, " \"");
			s(" onclick=\"$('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), "').toggle(); \"");
			s(">");
			s("<i class=\"fad fa-magnifying-glass \"></i>");
			l("</span>");

			///////////////
			// bouton fq //
			///////////////
			t(2, "<span");
			s(" title=\"", langueConfig.getString(ConfigCles.var_Filtres), "\"");
			s(" class=\"w3-button w3-xlarge w3-", contexteCouleur, " \"");
			s(" onclick=\"$('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), "').toggle(); \"");
			s(">");
			s("<i class=\"fad fa-filters \"></i>");
			l("</span>");

			//////////////////
			// bouton gamme //
			//////////////////
			t(2, "<span");
			s(" title=\"", langueConfig.getString(ConfigCles.var_Gamme), "\"");
			s(" class=\"w3-button w3-xlarge w3-", contexteCouleur, " \"");
			s(" onclick=\"$('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), "').toggle(); \"");
			s(">");
			s("<i class=\"fad fa-calendar-range \"></i>");
			l("</span>");

			//////////////////
			// bouton pivot //
			//////////////////
			t(2, "<span");
			s(" title=\"", langueConfig.getString(ConfigCles.var_Gamme), "\"");
			s(" class=\"w3-button w3-xlarge w3-", contexteCouleur, " \"");
			s(" onclick=\"$('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Recherche), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Filtres), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Gamme), "').hide(); $('.siteSidebarToggle", langueConfig.getString(ConfigCles.var_Pivot), "').toggle(); \"");
			s(">");
			s("<i class=\"fad fa-table-pivot \"></i>");
			l("</span>");

			l("</div>");

			// htmBodyCount0 //
			tl(1, "{{#eq ", uncapitalizeClasseApiClasseNomSimple, "Count int0}}");
			tl(0, "{{#block \"htmBodyCount0\"}}{{/block}}");
			tl(1, "{{else}}");

			tl(2, "{{#eq ", uncapitalizeClasseApiClasseNomSimple, "Count int1}}");
			// htmBodyCount1 //
			tl(3, "{{#eq params.query.q \"*:*\"}}");
			tl(0, "{{#block \"htmBodyCount1", langueConfig.getString(ConfigCles.var_Tous), "\"}}{{/block}}");
			tl(3, "{{else}}");
			tl(0, "{{#block \"htmBodyCount1\"}}{{/block}}");
			tl(3, "{{/eq}}");
			tl(2, "{{else}}");
			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Tous), "\"}}{{/block}}");
			tl(2, "{{/eq}}");
			tl(1, "{{/eq}}");
			if(classeMethodeVars.contains("htmBody")) {
				tl(6, "{{#if o}}");
				tl(7, "{{> \"htmBody\"}}");
				tl(6, "{{/if}}");
			}

			// formulaires
			if(!classePageSimple) {
				l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaires), "\"}}{{/block}}");
			}

			tl(0, "{{#block \"htmBody", langueConfig.getString(ConfigCles.var_Fin), "\"}}{{/block}}");
			tl(0, "</div>");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"table1", classePageNomSimple, "\"}}");
			tl(2, "<table class=\"w3-table w3-bordered w3-striped w3-border w3-hoverable \">");
			tl(3, "{{> table2", classePageNomSimple, "}}");
			tl(2, "</table>");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"table2", classePageNomSimple, "\"}}");
			tl(2, "{{> \"thead1", classePageNomSimple, "\"}}");
			tl(2, "{{> \"tbody1", classePageNomSimple, "\"}}");
			tl(2, "{{> \"tfoot1", classePageNomSimple, "\"}}");
			tl(1, "{{/inline}}");
			tl(0, "{{#*inline \"thead1", classePageNomSimple, "\"}}");
			tl(2, "<thead class=\"w3-", contexteCouleur, " w3-hover-", contexteCouleur, "\">");
			tl(3, "{{> thead2", classePageNomSimple, "}}");
			tl(2, "</thead>");
			tl(1, "{{/inline}}");
			tl(0, "{{#*inline \"thead2", classePageNomSimple, "\"}}");
			tl(3, "<tr>");
			s(wTh);
			tl(3, "</tr>");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"tbody1", classePageNomSimple, "\"}}");
			tl(2, "<tbody>");
			tl(3, "{{> tbody2", classePageNomSimple, "}}");
			tl(2, "</tbody>");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"tbody2", classePageNomSimple, "\"}}");
//				TODO
//				tl(2, "Map<String, Map<String, List<String>>> highlighting = ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getQueryResponse().getHighlighting();");
			tl(2, "{{#each ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, "}}");
//				TODO
//				tl(3, classeApiClasseNomSimple, " o = ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getList().get(i);");
//				tl(3, "Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());");
//				tl(3, "List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));");
//				tl(3, "String uri = ", classeEntiteVars.contains("pageUri") ? "o.getPageUri()" : (q(classePageUriMethode, "/") + " + o.get" + StringUtils.capitalize(classeVarClePrimaire) + "()"), ";");
			tl(3, "<tr>");
			s(wTd);
			tl(3, "</tr>");
			tl(2, "{{/each}}");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"tfoot1", classePageNomSimple, "\"}}");
			tl(2, "<tfoot class=\"w3-", contexteCouleur, " w3-hover-", contexteCouleur, "\">");
			tl(3, "{{> tfoot2", classePageNomSimple, "}}");
			tl(2, "</tfoot>");
			tl(0, "{{/inline}}");
			tl(0, "{{#*inline \"tfoot2", classePageNomSimple, "\"}}");
			tl(2, "<tr>");
//				TODO
//				tl(3, "SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ".getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get(\"facets\")).orElse(new SimpleOrderedMap());");
			s(wFoot);
			tl(2, "</tr>");
			tl(1, "{{/inline}}");
			s(wGetters);
			tl(0, "{{#*inline \"htm", langueConfig.getString(ConfigCles.var_Formulaires), classePageNomSimple, "\"}}");
			tl(1, "{{#ifContainsAnyRoles roles ", langueConfig.getString(ConfigCles.var_rolesRequis), "}}");

			// refraîchir 1 //
			tl(2, "{{#eq ", uncapitalizeClasseApiClasseNomSimple, "Count int1}}");
			tl(2, "<button");
			tl(3, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " \"");
			tl(3, "id=\"", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(contexteCe)), classeGenPageNomSimple, "\"");
			tl(3, "onclick=\"patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Vals( [ {name: 'fq', value: '", classeVarClePrimaire, ":{{", classeVarClePrimaire, "}}' } ], {}, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(contexteCe)), classeGenPageNomSimple, "')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(contexteCe)), classeGenPageNomSimple, "')); }); return false; \">");
			tl(3, "<i class=\"fas fa-sync-alt \"></i>");
			tl(3, langueConfig.getString(ConfigCles.var_recharger), " ", contexteCeNom);
			tl(2, "</button>");
			tl(2, "{{/eq}}");

			tl(1, "{{/ifContainsAnyRoles}}");

			// formulaires //
			if(activerRoleAdmin) {
				tl(1, "{{#ifContainsAnyRoles roles ", langueConfig.getString(ConfigCles.var_authRolesAdmin), "}}");
			}
			for(String classeApiMethode : classeApiMethodes) {
				String classeApiOperationIdMethode = (String)classeDoc.get("classeApiOperationId" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiUriMethode = (String)classeDoc.get("classeApiUri" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiTypeMediaMethode = (String)classeDoc.get("classeApiTypeMedia200" + classeApiMethode + "_" + langueNom + "_stored_string");
				String classeApiMethodeMethode = (String)classeDoc.get("classeApiMethode" + classeApiMethode + "_" + langueNom + "_stored_string");

				if(classeApiMethode.equals("PATCH") || classeApiMethode.equals("POST") || classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTCopie)) || classeApiMethode.equals(langueConfig.getString(ConfigCles.var_PUTFusion)) || classeApiMethode.equals("PUTImport")) {
					l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Formulaire), "_", classeApiOperationIdMethode, "\"}}{{/block}}");
				}
			}

			if(activerRoleAdmin) {
				tl(1, "{{/ifContainsAnyRoles}}");
			}
			l("{{#block \"htm", langueConfig.getString(ConfigCles.var_Suggere), "\"}}{{/block}}");
			tl(0, "{{/inline}}");

			if(!classePageSimple) {
//				TODO
//				tl(1, "/**");
//				if(StringUtils.equals(langueNomActuel, langueNom))
//				for(String langueNom2 : autresLangues) {
//					String classeGenPageNomSimple2 = (String)classeDoc.get("classeGenPageNomSimple" + langueConfig2.getString(ConfigCles.var_PageRecherche)  + "_" + langueNom2 + "_stored_string");
//					String classePageClasseNomSimple2 = (String)classeDoc.get("classeApiClasseNomSimple" + "_" + langueNom2 + "_stored_string");
//					String contexteTousNom2 = (String)classeDoc.get("contexteTousNom" + "_" + langueNom2 + "_stored_string");
//					String contexteNomAdjectifPluriel2 = (String)classeDoc.get("contexteNomAdjectifPluriel" + "_" + langueNom2 + "_stored_string");
//					String classePageUriMethode2 = (String)classeDoc.get("classeApiUri" + langueConfig2.getString(ConfigCles.var_PageRecherche) + "_" + langueNom2 + "_stored_string");
//					String classeVarSuggere2 = (String)classeDoc.get("classeVarSuggere" + "_" + langueNom2 + "_stored_string");
//					String classeVarTexte2 = (String)classeDoc.get("classeVarTexte" + "_" + langueNom2 + "_stored_string");
//
//					tl(1, " * Var.", langueNom2, ": htm", langueConfig2.getString(ConfigCles.var_Suggere), classeGenPageNomSimple2);
//					tl(1, " * r: \"", classePageUriMethode, "\"");
//					tl(1, " * r.", langueNom2, ": \"", classePageUriMethode2, "\"");
//					tl(1, " * r: \"", langueConfig.getString(ConfigCles.var_voir), " ", contexteTousNom, "\"");
//					tl(1, " * r.", langueNom2, ": \"", langueConfig2.getString(ConfigCles.var_voir), " ", contexteTousNom2, "\"");
//					tl(1, " * r: \"", langueConfig.getString(ConfigCles.var_recharger), classeGenPageNomSimple, "\"");
//					tl(1, " * r.", langueNom2, ": \"", langueConfig2.getString(ConfigCles.var_recharger), classeGenPageNomSimple2, "\"");
//					tl(1, " * r: \"", langueConfig.getString(ConfigCles.var_recharger), " ", contexteTousNom, "\"");
//					tl(1, " * r.", langueNom2, ": \"", langueConfig2.getString(ConfigCles.var_recharger), " ", contexteTousNom2, "\"");
//					tl(1, " * r: \"", langueConfig.getString(ConfigCles.var_rechercher), " ", contexteNomAdjectifPluriel, langueConfig.getString(ConfigCles.var_deuxPoints), "\"");
//					tl(1, " * r.", langueNom2, ": \"", langueConfig2.getString(ConfigCles.var_rechercher), " ", contexteNomAdjectifPluriel2, langueConfig2.getString(ConfigCles.var_deuxPoints), "\"");
//					tl(1, " * r: \"", langueConfig.getString(ConfigCles.var_suggere), "Form", classeApiClasseNomSimple, "\"");
//					tl(1, " * r.", langueNom2, ": \"", langueConfig2.getString(ConfigCles.var_suggere), "Form", classePageClasseNomSimple2, "\"");
//					tl(1, " * r: \"", langueConfig.getString(ConfigCles.var_rechercher), " ", contexteNomAdjectifPluriel, "\"");
//					tl(1, " * r.", langueNom2, ": \"", langueConfig2.getString(ConfigCles.var_rechercher), " ", contexteNomAdjectifPluriel2, "\"");
//					tl(1, " * r: \"", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, " w3-input w3-border w3-cell w3-cell-middle ", "\"");
//					tl(1, " * r.", langueNom2, ": \"", langueConfig2.getString(ConfigCles.var_suggere), classePageClasseNomSimple2, " w3-input w3-border w3-cell w3-cell-middle ", "\"");
//					tl(1, " * r: \"", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, "\"");
//					tl(1, " * r.", langueNom2, ": \"", langueConfig2.getString(ConfigCles.var_suggere), classePageClasseNomSimple2, "\"");
//
//					tl(1, " * r: ", "patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Vals", "");
//					tl(1, " * r.", langueNom2, ": ", "patch", classePageClasseNomSimple2, "Vals", "");
//					tl(1, " * r: ", langueConfig.getString(ConfigCles.var_ajouterLueur), "");
//					tl(1, " * r.", langueNom2, ": ", langueConfig2.getString(ConfigCles.var_ajouterLueur), "");
//					tl(1, " * r: ", langueConfig.getString(ConfigCles.var_recharger), classeGenPageNomSimple, "");
//					tl(1, " * r.", langueNom2, ": ", langueConfig2.getString(ConfigCles.var_recharger), classeGenPageNomSimple2, "");
//					tl(1, " * r: ", langueConfig.getString(ConfigCles.var_ajouterErreur), "");
//					tl(1, " * r.", langueNom2, ": ", langueConfig2.getString(ConfigCles.var_ajouterErreur), "");
//					tl(1, " * r: ", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, StringUtils.capitalize(classeVarSuggere));
//					tl(1, " * r.", langueNom2, ": ", langueConfig2.getString(ConfigCles.var_suggere), classePageClasseNomSimple2, StringUtils.capitalize(classeVarSuggere2));
//					tl(1, " * r: ", langueConfig.getString(ConfigCles.var_texte), classeApiClasseNomSimple, StringUtils.capitalize(classeVarTexte));
//					tl(1, " * r.", langueNom2, ": ", langueConfig2.getString(ConfigCles.var_texte), classePageClasseNomSimple2, StringUtils.capitalize(classeVarTexte2));
//					tl(1, " * r: ", "'", classeVarSuggere, ":'", "");
//					tl(1, " * r.", langueNom2, ": ", "'", classeVarSuggere2, ":'", "");
//					tl(1, " * r: ", "'", classeVarTexte, ":'", "");
//					tl(1, " * r.", langueNom2, ": ", "'", classeVarTexte2, ":'", "");
//					tl(1, " * r: ", "'#", langueConfig.getString(ConfigCles.var_suggere), "List", classeApiClasseNomSimple, "'", "");
//					tl(1, " * r.", langueNom2, ": ", "'#", langueConfig2.getString(ConfigCles.var_suggere), "List", classePageClasseNomSimple2, "'", "");
//					tl(1, " * r: \"", langueConfig.getString(ConfigCles.var_suggere), "List", classeApiClasseNomSimple, "\"");
//					tl(1, " * r.", langueNom2, ": \"", langueConfig2.getString(ConfigCles.var_suggere), "List", classePageClasseNomSimple2, "\"");
//				}
//				tl(1, "**/");
				l("{{#*inline \"htm", langueConfig.getString(ConfigCles.var_Suggere), classePageNomSimple, "\"}}");
//					tl(1, "public static void htm", langueConfig.getString(ConfigCles.var_Suggere), classeGenPageNomSimple, "(", classePartsMiseEnPage.nomSimple(langueNom), " p, String id, ", langueConfig.getString(ConfigCles.var_ListeRecherche), "<", classeApiClasseNomSimple, "> ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ") {");
//				tl(2, classePartsRequeteSite.nomSimple(langueNom), " ", langueConfig.getString(ConfigCles.var_requeteSite), "_ = p.get", langueConfig.getString(ConfigCles.var_RequeteSite), "_();");
//				TODO
//				tl(2, "try {");
//				tl(3, "ServiceRequest ", langueConfig.getString(ConfigCles.var_requeteService), " = ", langueConfig.getString(ConfigCles.var_requeteSite), "_.get", langueConfig.getString(ConfigCles.var_RequeteService), "();");
//				tl(3, "JsonObject queryParams = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_requeteService), ").map(ServiceRequest::getParams).map(or -> or.getJsonObject(\"query\")).orElse(new JsonObject());");
//				tl(3, "String q = \"*:*\";");
//				tl(3, "String query1 = \"", classeVarTexte, "\";");
//				tl(3, "String query2 = \"\";");
//				tl(3, "for(String param", langueConfig.getString(ConfigCles.var_Nom), " : queryParams.fieldNames()) {");
//				tl(4, "String ", langueConfig.getString(ConfigCles.var_entite), "Var = null;");
//				tl(4, "String ", langueConfig.getString(ConfigCles.var_valeur), langueConfig.getString(ConfigCles.var_Indexe), " = null;");
//				tl(4, "Object param", langueConfig.getString(ConfigCles.var_ValeursObjet), " = queryParams.getValue(param", langueConfig.getString(ConfigCles.var_Nom), ");");
//				tl(4, "JsonArray param", langueConfig.getString(ConfigCles.var_Objets), " = param", langueConfig.getString(ConfigCles.var_ValeursObjet), " instanceof JsonArray ? (JsonArray)param", langueConfig.getString(ConfigCles.var_ValeursObjet), " : new JsonArray().add(param", langueConfig.getString(ConfigCles.var_ValeursObjet), ");");
//				l();
//				tl(4, "try {");
//				tl(5, "for(Object param", langueConfig.getString(ConfigCles.var_Objet), " : param", langueConfig.getString(ConfigCles.var_Objets), ") {");
//				tl(6, "switch(param", langueConfig.getString(ConfigCles.var_Nom), ") {");
//		
//				tl(7, "case \"q\":");
//				tl(8, "q = (String)param", langueConfig.getString(ConfigCles.var_Objet), ";");
//				tl(8, langueConfig.getString(ConfigCles.var_entite), "Var = StringUtils.trim(StringUtils.substringBefore((String)param", langueConfig.getString(ConfigCles.var_Objet), ", \":\"));");
//				tl(8, langueConfig.getString(ConfigCles.var_valeur), langueConfig.getString(ConfigCles.var_Indexe), " = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)param", langueConfig.getString(ConfigCles.var_Objet), ", \":\")), \"UTF-8\");");
//				tl(8, "query1 = ", langueConfig.getString(ConfigCles.var_entite), "Var.equals(\"*\") ? query1 : ", langueConfig.getString(ConfigCles.var_entite), "Var;");
//				tl(8, "query2 = ", langueConfig.getString(ConfigCles.var_valeur), langueConfig.getString(ConfigCles.var_Indexe), ".equals(\"*\") ? \"\" : ", langueConfig.getString(ConfigCles.var_valeur), langueConfig.getString(ConfigCles.var_Indexe), ";");
//				tl(6, "}");
//				tl(5, "}");
//				tl(4, "} catch(Exception e) {");
//				tl(5, "ExceptionUtils.rethrow(e);");
//				tl(4, "}");
//				tl(3, "}");
//				l();
//				tl(3, "Integer rows1 = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ").map(l -> l.getRows()).orElse(10);");
//				tl(3, "Integer start1 = Optional.ofNullable(", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ").map(l -> l.getStart()).orElse(1);");
//				tl(3, "Integer start2 = start1 - rows1;");
//				tl(3, "Integer start3 = start1 + rows1;");
//				tl(3, "Integer rows2 = rows1 / 2;");
//				tl(3, "Integer rows3 = rows1 * 2;");
//				tl(3, "start2 = start2 < 0 ? 0 : start2;");
//				tl(3, "StringBuilder fqs = new StringBuilder();");
//				tl(3, "for(String fq : Optional.ofNullable(", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ").map(l -> l.getFilterQueries()).orElse(new String[0])) {");
//				tl(4, "if(!StringUtils.contains(fq, \"(\")) {");
//				tl(5, "String fq1 = StringUtils.substringBefore(fq, \"_\");");
//				tl(5, "String fq2 = StringUtils.substringAfter(fq, \":\");");
//				tl(5, "if(!StringUtils.startsWithAny(fq, \"", langueConfig.getString(ConfigCles.var_classeNomsCanoniques), "_\", \"", langueConfig.getString(ConfigCles.var_archive), "_\", \"", langueConfig.getString(ConfigCles.var_supprime), "_\", \"sessionId\", \"", langueConfig.getString(ConfigCles.var_utilisateur), langueConfig.getString(ConfigCles.var_Cle), "s\"))");
//				tl(6, "fqs.append(\"&fq=\").append(fq1).append(\":\").append(fq2);");
//				tl(4, "}");
//				tl(3, "}");
//				tl(3, "StringBuilder sorts = new StringBuilder();");
//				tl(3, "for(SortClause sort : Optional.ofNullable(", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, ").map(l -> l.getSorts()).orElse(Arrays.asList())) {");
//				tl(4, "sorts.append(\"&sort=\").append(StringUtils.substringBefore(sort.getItem(), \"_\")).append(\" \").append(sort.getOrder().name());");
//				tl(3, "}");
//				l();

				tl(3, "{{#ifContainsAnyRoles roles ", langueConfig.getString(ConfigCles.var_rolesRequis), "}}");

				// recharger tous //
//						t(4).s("{{# if ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, " == null) {").l();
				tl(5, "<div class=\"\">");
				tl(6, "<button id=\"", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(contexteTous)), classeGenPageNomSimple, "{{id}}\" class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-", contexteCouleur, " \"");
				tl(7, "onclick=\"patch{{", langueConfig.getString(ConfigCles.var_classeNomSimple), "}}Vals([], {}, function() { ", langueConfig.getString(ConfigCles.var_ajouterLueur), "($('#", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(contexteTous)), classeGenPageNomSimple, "{{id}}')); }, function() { ", langueConfig.getString(ConfigCles.var_ajouterErreur), "($('#", langueConfig.getString(ConfigCles.var_recharger), StringUtils.trim(StringUtils.capitalize(contexteTous)), classeGenPageNomSimple, "{{id}}')); }); \"");
				tl(7, ">");
				tl(7, "<i class=\"fas fa-sync-alt \"></i>");
				tl(7, langueConfig.getString(ConfigCles.var_recharger), " ", contexteTousNom);
				tl(6, "</button>");
				tl(5, "</div>");
//						t(4, "}").l();

				tl(3, "{{/ifContainsAnyRoles}}");

				tl(3, "<div class=\"w3-cell-row \">");
				tl(4, "<div class=\"w3-cell \">");
				tl(5, "<span>");
				tl(6, langueConfig.getString(ConfigCles.var_rechercher), " ", contexteNomAdjectifPluriel, langueConfig.getString(ConfigCles.str_deuxPoints));
				tl(5, "</span>");
				tl(4, "</div>");
				tl(3, "</div>");
				tl(3, "<div class=\"w3-bar \">");

				tl(4, "<input");
				tl(5, "type=\"text\"");

				if(contexteRechercherTousNom != null) {
					tl(5, "placeholder=\"", contexteRechercherTousNom, "\"");
				}

				tl(5, "class=\"", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, " w3-input w3-border w3-bar-item \"");
				tl(5, "name=\"", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, "\"");
				tl(5, "id=\"", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, "{{id}}\"");
				tl(5, "autocomplete=\"off\"");
				tl(5, "oninput=\"", langueConfig.getString(ConfigCles.var_suggere), classeApiClasseNomSimple, StringUtils.capitalize(classeVarSuggere), "( [ { 'name': 'q', 'value': '", classeVarSuggere, ":' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': '", langueConfig.getString(ConfigCles.var_classeNomCanonique), ",", classeVarClePrimaire, classeVarUrlPk == null ? "" : "," + classeVarUrlPk, classeVarTitre == null ? "" : "," + classeVarTitre, "' } ], $('#", langueConfig.getString(ConfigCles.var_suggere), "List", classeApiClasseNomSimple, "{{id}}'), {{", classeVarClePrimaire, "}}; \"");
				tl(5, "onkeyup=\"if (event.keyCode === 13) { event.preventDefault(); window.location.href = '", classePageUriMethode + "?q={{query1}}:' + encodeURIComponent(this.value) + '{{fqs}}{{sorts}}&amp;rows={{start2}}&amp;rows={{rows1}}\"");
				tl(4, "{{#if ", langueConfig.getString(ConfigCles.var_liste), classeApiClasseNomSimple, "}}");
				tl(5, "value=\"{{query2}}\"");
				tl(4, "{{/if}}");
				tl(4, "/>");
				tl(4, "<button");
				tl(5, "class=\"w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-", contexteCouleur, " \"");
				tl(5, "onclick=\"window.location.href = '", classePageUriMethode + "?q=&quot;, query1, &quot;:' + encodeURIComponent(this.previousElementSibling.value) + '&quot;, fqs, sorts, &quot;&amp;rows=&quot;, start2, &quot;&amp;rows=&quot;, rows1, &quot;'; \"");
				tl(5, ">");
				tl(5, "<i class=\"fas fa-search \"></i>");
				tl(4, "</button>");

				tl(3, "<div>");
				tl(3, "<div class=\"w3-cell-row \">");
				tl(4, "<div class=\"w3-cell w3-left-align w3-cell-top \">");
				tl(5, "<ul class=\"w3-ul w3-hoverable \" id=\"", langueConfig.getString(ConfigCles.var_suggere), "List", classeApiClasseNomSimple, "{{id}}\">");
				tl(5, "</ul>");
				tl(4, "</div>");
				tl(3, "</div>");

				// voir tous //
				tl(3, "<div class=\"\">");
				tl(4, "<a href=\"", classePageUriMethode, "\" class=\"\">");
				if(contexteIconeGroupe != null && contexteIconeNom != null)
					tl(5, "<i class=\"fa", StringUtils.substring(contexteIconeGroupe, 0, 1), " fa-", contexteIconeNom, "\"></i>");
				tl(5, langueConfig.getString(ConfigCles.var_voir), " ", contexteTousNom);
				tl(4, "</a>");
				tl(3, "</div>");

//				tl(2, "} catch(Exception e) {");
//				tl(3, "ExceptionUtils.rethrow(e);");
//				tl(2, "}");
				tl(0, "{{/inline}}");
				l("{{> ", classePageSuperNomSimple
						, "Object".equals(classeNomSimpleSuperGenerique) ? "" : (
								" " + StringUtils.uncapitalize(classeNomSimpleSuperGenerique) + "_=" + uncapitalizeClasseApiClasseNomSimple + "_"
								), "}}");

				if(classeMethodeVars.contains("htmBody" + langueConfig.getString(ConfigCles.var_Court))) {
					if(classePageSuperNomSimple != null)
						tl(1, "@Override");
					tl(0, "{{#*inline \"htmBodyCourt", classePageNomSimple, "\"}}");
					tl(2, uncapitalizeClasseApiClasseNomSimple, ".htmBody" + langueConfig.getString(ConfigCles.var_Court) + "();");
					tl(0, "{{/inline}}");
				}

				auteurWebsocket.flushClose();
				auteurPageJs.l();
				auteurPageJs.tl(0, "async function websocket", classeApiClasseNomSimple, "(success) {");
				auteurPageJs.tl(1, "window.eventBus.onopen = function () {");
				auteurPageJs.l();
				auteurPageJs.tl(2, "window.eventBus.registerHandler('websocket", classeApiClasseNomSimple, "', function (error, message) {");
				auteurPageJs.tl(3, "var json = JSON.parse(message['body']);");
				auteurPageJs.tl(3, "var id = json['id'];");
				auteurPageJs.tl(3, "var pk = json['pk'];");
				auteurPageJs.tl(3, "var pkPage = $('#", classeApiClasseNomSimple, "Form :input[name=", classeVarClePrimaire, "]').val();");
				auteurPageJs.tl(3, "var pks = json['pks'];");
				auteurPageJs.tl(3, "var empty = json['empty'];");
//					auteurPageJs.tl(3, "if(!empty) {");
				auteurPageJs.tl(3, "var numFound = parseInt(json['numFound']);");
				auteurPageJs.tl(3, "var numPATCH = parseInt(json['numPATCH']);");
				auteurPageJs.tl(3, "var percent = Math.floor( numPATCH / numFound * 100 ) + '%';");
				auteurPageJs.tl(3, "var $box = $('<div>').attr('class', 'w3-quarter box-' + id + ' ').attr('id', 'box-' + id).attr('data-numPATCH', numPATCH);");
				auteurPageJs.tl(3, "var $margin = $('<div>').attr('class', 'w3-margin ').attr('id', 'margin-' + id);");
				auteurPageJs.tl(3, "var $card = $('<div>').attr('class', 'w3-card w3-white ').attr('id', 'card-' + id);");
				auteurPageJs.tl(3, "var $header = $('<div>').attr('class', 'w3-container fa-", contexteCouleur, " ').attr('id', 'header-' + id);");
				auteurPageJs.tl(3, "var $i = $('<i>').attr('class', 'fa", StringUtils.substring(contexteIconeGroupe, 0, 1), " fa-", contexteIconeNom, " w3-margin-right ').attr('id', 'icon-' + id);");
				auteurPageJs.tl(3, "var $headerSpan = $('<span>').attr('class', '').text('", langueConfig.getString(ConfigCles.var_modifier), " ", contexteNomAdjectifPluriel, " ", langueConfig.getString(ConfigCles.var_dans), " ' + json.", langueConfig.getString(ConfigCles.var_tempsRestant), ");");
				auteurPageJs.tl(3, "var $x = $('<span>').attr('class', 'w3-button w3-display-topright ').attr('onclick', '$(\"#card-' + id + '\").hide(); ').attr('id', 'x-' + id);");
				auteurPageJs.tl(3, "var $body = $('<div>').attr('class', 'w3-container w3-padding ').attr('id', 'text-' + id);");
				auteurPageJs.tl(3, "var $bar = $('<div>').attr('class', 'w3-light-gray ').attr('id', 'bar-' + id);");
				auteurPageJs.tl(3, "var $progress = $('<div>').attr('class', 'w3-", contexteCouleur, " ').attr('style', 'height: 24px; width: ' + percent + '; ').attr('id', 'progress-' + id).text(numPATCH + '/' + numFound);");
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
				auteurPageJs.tl(4, "var $old_box = $('.box-' + id);");
				auteurPageJs.tl(4, "if(!$old_box.size()) {");
				auteurPageJs.tl(5, "$('.top-box').append($box);");
				auteurPageJs.tl(4, "} else if($old_box && $old_box.attr('data-numPATCH') < numFound) {");
				auteurPageJs.tl(5, "$('.box-' + id).html($margin);");
				auteurPageJs.tl(4, "}");
				auteurPageJs.tl(3, "} else {");
				auteurPageJs.tl(4, "$('.box-' + id).remove();");
				auteurPageJs.tl(3, "}");
				auteurPageJs.tl(3, "if(pk && pkPage && pk == pkPage) {");
				auteurPageJs.tl(4, "if(success)");
				auteurPageJs.tl(5, "success(json);");
				auteurPageJs.tl(3, "}");
//					auteurPageJs.tl(3, "}");
				auteurPageJs.tl(2, "});");
				auteurPageJs.s(auteurWebsocket);
				auteurPageJs.tl(1, "}");
				auteurPageJs.tl(0, "}");

				auteurPageJs.tl(0, "async function websocket", classeApiClasseNomSimple, "Inner(", langueConfig.getString(ConfigCles.var_requeteApi), ") {");
				auteurPageJs.s(wWebsocket);
				auteurPageJs.tl(0, "}");
			}
	
			o = auteurGenPageHbs;

			s(auteurGenPageHbsEntite);
	
			o = auteurPageGenClasse;

			tl(0, "}");

			wTh.flushClose();

			auteurPageGenClasse.flushClose();
			System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classeGenPageChemin); 
			if(auteurPageClasse != null) {
				auteurPageClasse.flushClose();
				System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classePageChemin); 
			}
			auteurPageCss.flushClose();
			System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classePageCheminCss); 
			auteurPageJs.flushClose();
			System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classePageCheminJs); 
			if(auteurPageHbs != null) {
				auteurPageHbs.flushClose();
				System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classePageCheminHbs); 
			}
			auteurGenPageHbs.flushClose();
			System.out.println(langueConfig.getString(ConfigCles.var_Ecrire) + ": " + classeGenPageCheminHbs); 

			String siteCheminVertx = siteCheminsVertx.get(langueNom);
			String siteNomVertx = StringUtils.substringAfterLast(siteCheminVertx, "/");
			String cheminSrcGenJavaVertx = (siteCheminVertx == null ? siteChemin : siteCheminVertx) + "/src/gen/java";
			String cheminSrcMainJavaVertx = (siteCheminVertx == null ? siteChemin : siteCheminVertx) + "/src/main/java";
			String cheminSrcMainResourcesVertx = (siteCheminVertx == null ? siteChemin : siteCheminVertx) + "/src/main/resources";

			{
				RegarderClasse regarderClasse = new RegarderClasse();
				regarderClasse.siteChemin = siteCheminVertx;
				regarderClasse.siteNom = siteNomVertx;
				regarderClasse.classeCheminAbsolu = classePageChemin;
				regarderClasse.cheminSrcGenJava = cheminSrcGenJavaVertx;
				regarderClasse.cheminSrcMainJava = cheminSrcMainJavaVertx;
				regarderClasse.cheminSrcMainResources = cheminSrcMainResourcesVertx;
				regarderClasse.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
//					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom);
//					RegarderClasse.regarderClasse(regarderClasse, langueNom);
				SolrInputDocument classeDoc = new SolrInputDocument();
				regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu, classeDoc, langueNom);
				regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom, langueConfig);
			}

			{
				RegarderClasse regarderClasse = new RegarderClasse();
				regarderClasse.siteChemin = siteCheminVertx;
				regarderClasse.siteNom = siteNomVertx;
				regarderClasse.classeCheminAbsolu = classeGenPageChemin;
				regarderClasse.cheminSrcGenJava = cheminSrcGenJavaVertx;
				regarderClasse.cheminSrcMainJava = cheminSrcMainJavaVertx;
				regarderClasse.cheminSrcMainResources = cheminSrcMainResourcesVertx;
				regarderClasse.initRegarderClasseBase(classeLangueNom, classeLangueConfig); 
//					regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom);
//					RegarderClasse.regarderClasse(regarderClasse, langueNom);
				SolrInputDocument classeDoc = new SolrInputDocument();
				regarderClasse.indexerClasse(regarderClasse.classeCheminAbsolu, classeDoc, langueNom);
				regarderClasse.ecrireGenClasses(regarderClasse.classeCheminAbsolu, langueNom, langueNom, langueConfig);
			}
		}
	}
}
